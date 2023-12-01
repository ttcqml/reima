package com.micro.reima.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.common.core.utils.StringUtils;
import com.micro.common.core.utils.ip.OkHttpUtil;
import com.micro.common.redis.service.RedisService;
import com.micro.reima.mapper.BizUserCouponMapper;
import com.micro.reima.model.bo.BizUserCouponNoticeVo;
import com.micro.reima.service.IBizMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class IBizMessageServiceImpl implements IBizMessageService {
    // 获取接口调用凭据 GET
    private String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    // 发送订阅消息 POST
    private String SEND_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=%s";
    // token key
    private String ACCESS_TOKEN_KEY = "WEIXIN_ACCESS_TOKEN_KEY_";

    // 优惠券到账通知
    public static String TEMPLATE_OF_COUPON_GET = "NOj47PO8aUoPfrBh4iMhvWP_uNyRfaFkcbchpX2mxHo";
    // 优惠券到期提醒
    public static String TEMPLATE_OF_COUPON_EXP = "73zoOrs8xcKdgqK5fwvAAA4W4yQv6IBRmyteCPdi4lo";
    // 优惠券使用成功通知
    public static String TEMPLATE_OF_COUPON_USE = "m3bltL0AzkxP2_9V0Sc7su94vQdXEIsgBwElaM8He7U";
    // 积分变更提醒
    public static String TEMPLATE_OF_POINT_CHANGE = "_F2oWS3Ir7bavTOj6kmIHlsTxtT9eNev85lkGgjKZ64";

    private String appId = "wx4634d543fd20ee8b";
    private String appSecret="88c59835acd788924e3b17d933a0a051";

    @Autowired
    private RedisService redisService;
    @Autowired
    private BizUserCouponMapper bizUserCouponMapper;

    /**
     * 优惠券到账通知
     * 模板ID NOj47PO8aUoPfrBh4iMhvWP_uNyRfaFkcbchpX2mxHo
     * 模板编号 36731
     * 标题 优惠券到账通知
     * 类目 服饰内衣
     * 详细内容
     * 优惠券名称 {{thing5.DATA}}
     * 优惠券有效期 {{time2.DATA}}
     * 温馨提示 {{thing1.DATA}}
     */
    @Override
    public void sendCouponGetNotice(String openId,String couponName,String expireAt,String tips){
        Map<String,Object> datas = new HashMap<>();
        datas.put("thing5",couponName);
        datas.put("time2",expireAt);
        datas.put("thing1",tips);
        sendMessage(TEMPLATE_OF_COUPON_GET,openId,datas);
    }
    /**
     * 优惠券到期提醒
     * 模板ID 73zoOrs8xcKdgqK5fwvAAA4W4yQv6IBRmyteCPdi4lo
     * 模板编号  1202
     * 标题 优惠券到期提醒
     * 类目 服饰内衣
     * 详细内容
     * 优惠券名称 {{thing5.DATA}}
     * 到期日 {{time8.DATA}}
     * 温馨提示 {{thing9.DATA}}
     */
    @Override
    public void sendCouponExpNotice(String openId,String couponName,String expireAt,String tips){
        Map<String,Object> datas = new HashMap<>();
        datas.put("thing5",couponName);
        datas.put("time8",expireAt);
        datas.put("thing9",tips);
        sendMessage(TEMPLATE_OF_COUPON_EXP,openId,datas);
    }
    /**
     * 优惠券使用成功通知
     * 模板ID m3bltL0AzkxP2_9V0Sc7su94vQdXEIsgBwElaM8He7U
     * 模板编号 7099
     * 标题 优惠券使用成功通知
     * 类目 服饰内衣
     * 详细内容
     * 卡券名称 {{thing3.DATA}}
     * 使用时间 {{time4.DATA}}
     * 温馨提示 {{thing5.DATA}}
     */
    @Override
    public void sendCouponUseNotice(String openId,String couponName,String useAt,String tips){
        Map<String,Object> datas = new HashMap<>();
        datas.put("thing3",couponName);
        datas.put("time4",useAt);
        datas.put("thing5",tips);
        sendMessage(TEMPLATE_OF_COUPON_USE,openId,datas);
    }
    /**
     * 模板ID _F2oWS3Ir7bavTOj6kmIHlsTxtT9eNev85lkGgjKZ64
     * 模板编号 310
     * 标题 积分变更提醒
     * 类目 服饰内衣
     * 操作人 R****777 2022-10-12 添加
     * 详细内容
     * 会员号 {{number11.DATA}}
     * 变更数量 {{character_string1.DATA}}
     * 积分余额 {{character_string2.DATA}}
     * 变更原因 {{thing3.DATA}}
     * 场景说明  积分变更提醒
     */
    @Override
    public void sendPointChangeNotice(String openId,String memberNo,String num,String reason,String balance,String tips){
        Map<String,Object> datas = new HashMap<>();
        datas.put("number11",memberNo);
        datas.put("character_string1",num);
        datas.put("thing3",reason);
        datas.put("character_string2",balance);
        //datas.put("thing5",tips);
        sendMessage(TEMPLATE_OF_POINT_CHANGE,openId,datas);
    }

    // 获取token
    private String getToken(){
        String token = redisService.getCacheObject(ACCESS_TOKEN_KEY);
        if(null == token){
            String result = OkHttpUtil.httpGet(String.format(GET_ACCESS_TOKEN,appId,appSecret));
            if(StringUtils.isNotEmpty(result)){
                JSONObject jsonObject = JSON.parseObject(result);
                if(null!=jsonObject&&StringUtils.isNotEmpty(jsonObject.getString("access_token"))){
                    token = jsonObject.getString("access_token");
                    redisService.setCacheObject(GET_ACCESS_TOKEN, token, 6600L, TimeUnit.SECONDS);
                }
            }
        }
        return token;
    }

    // 发送订阅消息
    public void sendMessage(String templateId,String openId,Map<String,Object> datas){
        try {
            log.error(">>>sendMessage:templateId:{},openId:{},datas:{}",templateId,openId,JSON.toJSONString(datas));
            String token = getToken();
            if(StringUtils.isEmpty(token)){
                return;
            }
            Map<String,Object> param = new HashMap<>();
            param.put("template_id",templateId);
            param.put("page","/pages/index/index");
            param.put("touser",openId);// ovLo_40Xm0HByvz99SWXmAWfitcA
            JSONObject data = new JSONObject();
            if(CollectionUtils.isEmpty(datas)){
                return;
            }
            for (String key:datas.keySet()){
                JSONObject val = new JSONObject();
                val.put("value",datas.get(key));
                data.put(key,val);
            }
            param.put("data",data);
            param.put("miniprogram_state","formal");//TODO trial为体验版；formal为正式版
            param.put("lang","zh_CN");
            String result = OkHttpUtil.httpPostJson(String.format(SEND_MESSAGE,getToken()),JSON.toJSONString(param));
            log.error(">>>sendMessage:templateId:{},openId:{},datas:{},result:{}",templateId,openId,JSON.toJSONString(datas),result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 优惠券到期通知
    @Scheduled(cron = "0 0 22 * * ?")
    protected void checkInviteUser(){
        List<BizUserCouponNoticeVo> vos = bizUserCouponMapper.selectBizUserCouponNoticeVos();
        if(CollectionUtils.isEmpty(vos)){
            return;
        }
        for (BizUserCouponNoticeVo vo:vos){
            sendCouponExpNotice(vo.getOpenId(),vo.getCouponName(),vo.getEndAt(),"您有优惠券即将过期。");
        }
    }
}
