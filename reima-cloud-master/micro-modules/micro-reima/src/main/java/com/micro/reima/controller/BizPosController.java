package com.micro.reima.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.micro.common.core.domain.PosResult;
import com.micro.common.core.domain.R;
import com.micro.common.core.exception.ServiceException;
import com.micro.common.core.utils.StringUtils;
import com.micro.common.core.utils.sign.MD5;
import com.micro.common.core.web.controller.BaseController;
import com.micro.common.core.web.page.PosTableDataInfo;
import com.micro.common.core.web.page.TableDataInfo;
import com.micro.common.security.service.TokenService;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.reima.model.bo.UpTokenChildForm;
import com.micro.reima.model.pos.*;
import com.micro.reima.model.vo.SimpleUserCouponVo;
import com.micro.reima.service.IBizOrderRecordService;
import com.micro.reima.service.IBizShuyunSignInfoService;
import com.micro.reima.service.IBizUserCouponService;
import com.micro.reima.service.IBizUserInfoService;
import com.micro.system.api.model.LoginUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * PosController
 * @author micro
 * @date 2021-12-11
 */
@Api(tags = "提供给POS接口")
@RestController
@RequestMapping("/pos")
@Slf4j
public class BizPosController extends BaseController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IBizUserInfoService bizUserInfoService;

    @Autowired
    private IBizUserCouponService bizUserCouponService;

    @Autowired
    private IBizOrderRecordService bizOrderRecordService;

    @Autowired
    private IBizShuyunSignInfoService bizShuyunSignInfoService;

    /**
     * 更新数云Token
     */
    @PostMapping("/callback/upToken")
    public Object shuyunSyncMemberUpToken(@RequestBody List<UpTokenChildForm> form){
        bizShuyunSignInfoService.upToken(form);
        return R.ok();
    }

    // 根据手机号查询会员信息
    @PostMapping("/getMemberinfobymobile")
    @ApiOperation("[POS]根据手机号查询会员信息")
    public PosResult<MemberInfoVo> getMemberinfobymobile(@RequestBody String str, HttpServletRequest request)
    {
        try {
            log.info(">>pos getMemberinfobymobile:{}", str);
            String verify = verify(str,request);
            MemberInfoBody body = JSON.parseObject(JSON.parseObject(str).getString("bu_content"),MemberInfoBody.class);
            if(null!=verify){
                return PosResult.fail(verify);
            }
            MemberInfoVo vo = bizUserInfoService.getMemberinfobymobile(getMobile(body.getMobile()));
            vo.setMember_code(getMemberCode(body.getMobile()));
            return PosResult.ok(vo);
        }catch (ServiceException e){
            log.error("getMemberinfobymobile:{}",e);
            return PosResult.fail(e.getMessage());
        }catch (Exception e){
            log.error("getMemberinfobymobile:{}",e);
            return PosResult.fail("网络异常");
        }
    }

    // 根据手机号获取优惠券列表
    @PostMapping("/queryMemberCouponList")
    @ApiOperation("[POS]根据手机号获取优惠券列表")
    public PosResult<PosTableDataInfo<List<MemberCouponVo>>> queryMemberCouponList(@RequestBody String str, HttpServletRequest request){
        try {
            log.info(">>pos queryMemberCouponList:{}", str);
            String verify = verify(str,request);
            if(null!=verify){
                return PosResult.fail(verify);
            }

            MemberCouponBody body = JSON.parseObject(JSON.parseObject(str).getString("bu_content"),MemberCouponBody.class);
            log.info(">>pos queryMemberCouponList:{}", body);
            String memberCode = body.getMember_code();
            body.setMember_code(getMobile(body.getMember_code()));
            startPage(body.getPage_no(),body.getPage_size());
            List<MemberCouponVo> vos = bizUserCouponService.queryMemberCouponList(body);
            if(!CollectionUtils.isEmpty(vos)){
                vos.forEach(p->p.setMember_code(memberCode));
            }
            return PosResult.ok(getDataTable(vos,body.getPage_size()));
        }catch (ServiceException e){
            log.error("queryMemberCouponList:{}",e);
            return PosResult.fail(e.getMessage());
        }catch (Exception e){
            log.error("queryMemberCouponList:{}",e);
            return PosResult.fail("网络异常");
        }
    }

    @PostMapping("/getCoupon")
    @ApiOperation("[POS]根据序列号获取优惠券")
    public PosResult<MemberCouponVo> getCoupon(@RequestBody String str, HttpServletRequest request){
        try {
            log.info(">>pos getCoupon:{}", str);
            String verify = verify(str,request);
            if(null!=verify){
                return PosResult.fail(verify);
            }
            GetCouponBody body = JSON.parseObject(JSON.parseObject(str).getString("bu_content"),GetCouponBody.class);
            MemberCouponVo vo = bizUserCouponService.queryMemberCoupon(body.getCoupon_serial_no());
            vo.setMember_code(getMemberCode(vo.getMember_code()));
            return PosResult.ok(vo);
        }catch (ServiceException e){
            log.error("getCoupon:{}",e);
            return PosResult.fail(e.getMessage());
        }catch (Exception e){
            log.error("getCoupon:{}",e);
            return PosResult.fail("网络异常");
        }
    }

    // 查询优惠券状态
    @PostMapping("/checkMemberCoupon")
    @ApiOperation("[POS]查询优惠券状态")
    public PosResult<CheckMemberCouponVo> checkMemberCoupon(@RequestBody String str, HttpServletRequest request)
    {
        try {
            log.info(">>pos checkMemberCoupon:{}", str);
            String verify = verify(str,request);
            if(null!=verify){
                return PosResult.fail(verify);
            }
            CheckMemberCouponBody body = JSON.parseObject(JSON.parseObject(str).getString("bu_content"),CheckMemberCouponBody.class);
            body.setMember_code(getMobile(body.getMember_code()));
            return PosResult.ok(bizUserCouponService.checkMemberCoupon(body));
        }catch (ServiceException e){
            log.error("checkMemberCoupon:{}",e);
            return PosResult.fail(e.getMessage());
        }catch (Exception e){
            log.error("checkMemberCoupon:{}",e);
            return PosResult.fail("网络异常");
        }
    }

    // 核销优惠券
    @PostMapping("/issueMemberCoupon")
    @ApiOperation("[POS]核销优惠券")
    public PosResult<IssueMemberCouponBodyVo> issueMemberCoupon(@RequestBody String str, HttpServletRequest request)
    {
        try {
            log.info(">>pos issueMemberCoupon:{}", str);
            String verify = verify(str,request);
            if(null!=verify){
                return PosResult.fail(verify);
            }
            IssueMemberCouponBody body = JSON.parseObject(JSON.parseObject(str).getString("bu_content"),IssueMemberCouponBody.class);
            body.setMember_code(getMobile(body.getMember_code()));
            return PosResult.ok(bizUserCouponService.issueMemberCoupon(body));
        }catch (ServiceException e){
            log.error("issueMemberCoupon:{}",e);
            return PosResult.fail(e.getMessage());
        }catch (Exception e){
            log.error("issueMemberCoupon:{}",e);
            return PosResult.fail("网络异常");
        }
    }

    // 接收订单数据
    @PostMapping("/createTransaction")
    @ApiOperation("[POS]接收订单数据")
    public PosResult<CreateTransactionVo> createTransaction(@RequestBody String str, HttpServletRequest request)
    {
        try {
            log.info(">>pos createTransaction:{}", str);
            String verify = verify(str,request);
            if(null!=verify){
                return PosResult.fail(verify);
            }
            CreateTransactionBody body = JSON.parseObject(JSON.parseObject(str).getString("bu_content"),CreateTransactionBody.class);
            body.setMember_code(getMobile(body.getMember_code()));
            return PosResult.ok(bizOrderRecordService.createTransaction(body));
        }catch (ServiceException e){
            log.error("createTransaction:{}",e);
            return PosResult.fail(e.getMessage());
        }catch (Exception e){
            log.error("createTransaction:{}",e);
            return PosResult.fail("网络异常");
        }
    }

    // 接收退单数据
    @PostMapping("/voidTransaction")
    @ApiOperation("[POS]接收退单数据")
    public PosResult<VoidTransactionVo> voidTransaction(@RequestBody String str, HttpServletRequest request)
    {

        try {
            log.info(">>pos voidTransaction:{}", str);
            String verify = verify(str,request);
            if(null!=verify){
                return PosResult.fail(verify);
            }
            VoidTransactionBody body = JSON.parseObject(JSON.parseObject(str).getString("bu_content"),VoidTransactionBody.class);
            return PosResult.ok(bizOrderRecordService.voidTransaction(body));
        }catch (ServiceException e){
            log.error("voidTransaction:{}",e);
            return PosResult.fail(e.getMessage());
        }catch (Exception e){
            log.error("voidTransaction:{}",e);
            return PosResult.fail("网络异常");
        }
    }

    @GetMapping("/security/oauth/token")
    @ApiOperation("[POS]获取/刷新token")
    public PosResult<LoginVo> posToken(@ApiParam(value = "Token类型", required = true) @RequestParam(defaultValue = "custom") String grant_type,
        @ApiParam(value = "登录名", required = false) @RequestParam(defaultValue = "") String login_name,
        @ApiParam(value = "登录秘钥", required = false) @RequestParam(defaultValue = "") String password,
        @ApiParam(value = "公司", required = false) @RequestParam(defaultValue = "") String company,
        @ApiParam(value = "刷新token", required = false) @RequestParam(defaultValue = "") String refresh_token)

    {
        if("custom".equals(grant_type)){
            if(StringUtils.isEmpty(login_name)||StringUtils.isEmpty(password)||StringUtils.isEmpty(company)){
                return PosResult.fail("参数异常");
            }
            return PosResult.ok(bizUserInfoService.posLogin(grant_type,login_name,password));
        }else if("refresh_token".equals(grant_type)){
            if(StringUtils.isEmpty(refresh_token)){
                return PosResult.fail("参数异常");
            }
            return PosResult.ok(bizUserInfoService.refresToken(grant_type,refresh_token));
        }else{
            return PosResult.fail("grant_type不识别");
        }
    }
    private String verify(String requestBody,HttpServletRequest request){
        String token = request.getHeader("access_token");
        log.info("token:{}",token);
        LoginUser loginUser = tokenService.getLoginUser(token);
        if(null == loginUser){
            return "Token校验失败";
        }
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= 0)
        {
            return "Token失效,请重新获取Token";
        }
        String requestId = request.getHeader("request_id");
        String timestamp = request.getHeader("timestamp");
        // 按照以下模式“request_id&timestamp&requestBody&”进行连接后做MD5处理
        String signature = request.getHeader("signature");
        // 签名
        String signString = requestId+"&"+timestamp+"&"+requestBody+"&";
        log.info("signStrin:{},token:{}",signString,token);
        if(!signature.equals(DigestUtils.md5Hex(signString))){
            return "签名错误";
        }
        return null;
    }


    private String getMobile(String mobile){
        Pattern p = Pattern.compile("1\\d{10}");
        Matcher m = p.matcher(mobile);
        if(m.matches()){
            return mobile;
        }
        String result = bizUserInfoService.selectMobileByMemberCode(mobile);
        return StringUtils.isEmpty(result)?mobile:result;
    }

    private String getMemberCode(String mobile){
        Pattern p = Pattern.compile("1\\d{10}");
        Matcher m = p.matcher(mobile);
        if(m.matches()){
            String result = bizUserInfoService.selectMemberCodeByMobile(mobile);
            return StringUtils.isEmpty(result)?mobile:result;
        }
        return mobile;
    }

}
