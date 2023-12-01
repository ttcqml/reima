package com.micro.reima.service;

import java.util.Map;

public interface IBizMessageService {

    /**
     * 发送模板消息
     */
    void sendMessage(String templateId, String openId, Map<String,Object> datas);

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
     *
     * 生日优惠券温馨提示内容：“reima 祝宝贝生日快乐，茁壮成长！7折优惠券已放入卡券包。”
     * 其他优惠券温馨提示内容：“尊敬的reima friends会员，恭喜您已获得xxxx（填入优惠券名称），记得使用哦。”
     */
    void sendCouponGetNotice(String openId,String couponName,String expireAt,String tips);
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
     *
     * 提示：您有优惠券即将过期。
     */
    void sendCouponExpNotice(String openId,String couponName,String expireAt,String tips);
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
     *
     * 提示：优惠券使用成功。
     */
    void sendCouponUseNotice(String openId,String couponName,String useAt,String tips);
    /**
     * 积分变更提醒
     * 模板ID _F2oWS3Ir7bavTOj6kmIHvwHBwcXAvK_sUg9BF4wmww
     * 模板编号 310
     * 标题 积分变更提醒
     * 类目 服饰内衣
     * 详细内容
     * 会员号 {{number11.DATA}}
     * 变更数量 {{character_string1.DATA}}
     * 变更原因 {{thing3.DATA}}
     * 积分余额 {{character_string2.DATA}}
     * 温馨提示 {{thing5.DATA}}
     *
     * 备注
     */
    void sendPointChangeNotice(String openId,String memberNo,String num,String reason,String balance,String tips);
}
