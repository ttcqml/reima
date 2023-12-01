package com.micro.auth.service;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.micro.common.core.utils.ip.OkHttpUtil;
import com.micro.common.core.utils.sign.AES;
import com.micro.system.api.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.micro.common.core.constant.Constants;
import com.micro.common.core.constant.SecurityConstants;
import com.micro.common.core.constant.UserConstants;
import com.micro.common.core.domain.R;
import com.micro.common.core.enums.UserStatus;
import com.micro.common.core.exception.ServiceException;
import com.micro.common.core.utils.ServletUtils;
import com.micro.common.core.utils.StringUtils;
import com.micro.common.core.utils.ip.IpUtils;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.system.api.RemoteLogService;
import com.micro.system.api.RemoteUserService;
import com.micro.system.api.domain.SysLogininfor;
import com.micro.system.api.domain.SysUser;

/**
 * 登录校验方法
 * 
 * @author micro
 */
@Component
public class SysLoginService
{
    private static final Logger logger = LoggerFactory.getLogger(SysLoginService.class);

//    @Autowired
//    private WxMaService wxService;

    @Value(("${reima.wx.appId}"))
    private String appId;
    @Value(("${reima.wx.appSecret}"))
    private String appSecret;

    @Autowired
    private RemoteLogService remoteLogService;

    @Autowired
    private RemoteUserService remoteUserService;

    private JSONObject getSessionKeyOrOpenId(String code){
        if(StringUtils.isEmpty(code)){
            return null;
        }
        String tokenUrl = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
        tokenUrl = tokenUrl.replace("APPID",appId).replace("SECRET",appSecret).replace("JSCODE", code);
        String resultToken = OkHttpUtil.httpGet(tokenUrl);
        JSONObject jsonObject = JSONUtil.parseObj(resultToken);
        return jsonObject;
    }

    private String getSessionKey(String code){
        JSONObject jsonObject = getSessionKeyOrOpenId(code);
        if(null!=jsonObject){
            return jsonObject.getStr("session_key");
        }
        return null;
    }
    /**
     * 小程序登录
     */
    public LoginUser loginByMiniApp(WxLoginInfo wxLoginInfo){
        String code = wxLoginInfo.getCode();
        logger.info(">>>>code:{}",code);
        WxUserInfo userInfo = wxLoginInfo.getUserInfo();
        if(code == null || userInfo == null){
            throw new ServiceException("参数错误,登录失败");
        }
        String sessionKey = null;
        String openId = null;
        JSONObject jsonObject = getSessionKeyOrOpenId(code);
        if(null != jsonObject){
            openId = jsonObject.getStr("openid");
            sessionKey = jsonObject.getStr("session_key");
        }
        /*try {
            WxMaJscode2SessionResult result = this.wxService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        if (sessionKey == null || openId == null) {
            throw new ServiceException("获取参数错误,登录失败");
        }
        WxUserInfo wxUserInfo = wxLoginInfo.getUserInfo();
        wxUserInfo.setOpenId(openId);
        wxUserInfo.setSessionKey(sessionKey);
        wxUserInfo.setInviteMobile(wxLoginInfo.getInvitation());
        R<LoginUser> loginUserResult = remoteUserService.loginByMiniApp(wxUserInfo,SecurityConstants.INNER);
        if (R.FAIL == loginUserResult.getCode())
        {
            throw new ServiceException(loginUserResult.getMsg());
        }
        return loginUserResult.getData();
    }

    public LoginUser bindPhone(WxBindPhone wxBindPhone){
        Long userId = SecurityUtils.getUserId();
        R<LoginUser> loginUser = remoteUserService.getUserInfoById(userId, SecurityConstants.INNER);
        if (R.FAIL == loginUser.getCode())
        {
            throw new ServiceException(loginUser.getMsg());
        }
        if (StringUtils.isNull(loginUser) || StringUtils.isNull(loginUser.getData()))
        {
            recordLogininfor(userId+"", Constants.LOGIN_FAIL, "登录用户不存在");
            throw new ServiceException("登录用户：" + userId + " 不存在");
        }
        logger.info(">>>>bindPhone:{},{}", JSON.toJSONString(loginUser),JSON.toJSONString(wxBindPhone));

        /*WxMaPhoneNumberInfo phoneNumberInfo = this.wxService.getUserService().getPhoneNoInfo(loginUser.getData().getSysUser().getSessionKey(), wxBindPhone.getEncryptedData(), wxBindPhone.getIv());
        String phone = phoneNumberInfo.getPhoneNumber();
        if(StringUtils.isEmpty(phone)){
            throw new ServiceException("获取手机号信息失败");
        }*/

        // 1.解析加密数据
        String phoneNumber = null;
        String sessionKey = getSessionKey(wxBindPhone.getCode());
        JSONObject userencryptedData = AES.wxDecrypt(wxBindPhone.getEncryptedData(),StringUtils.isNotEmpty(sessionKey)?sessionKey:loginUser.getData().getSysUser().getSessionKey(), wxBindPhone.getIv());
        if (StringUtils.isNotBlank((String) userencryptedData.get("phoneNumber"))){
            phoneNumber = (String) userencryptedData.get("phoneNumber");
        }

        logger.info(">>>>bindPhone:{}",JSON.toJSONString(userencryptedData));
        WxUserPhone wxUserPhone = new WxUserPhone();
        wxUserPhone.setUserId(userId);
        wxUserPhone.setMobile(phoneNumber);
        R<?> bindResult = remoteUserService.bindPhone(wxUserPhone,SecurityConstants.INNER);
        if (R.FAIL == bindResult.getCode())
        {
            throw new ServiceException(bindResult.getMsg());
        }
        recordLogininfor(userId+"", Constants.BINDMOBILE, "绑定成功");
        return loginUser.getData();
    }

    /**
     * 登录
     */
    public LoginUser login(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password))
        {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户/密码必须填写");
            throw new ServiceException("用户/密码必须填写");
        }
        // 密码如果不在指定范围内 错误
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码不在指定范围");
            throw new ServiceException("用户密码不在指定范围");
        }
        // 用户名不在指定范围内 错误
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户名不在指定范围");
            throw new ServiceException("用户名不在指定范围");
        }
        // 查询用户信息
        R<LoginUser> userResult = remoteUserService.getUserInfo(username, SecurityConstants.INNER);

        if (R.FAIL == userResult.getCode())
        {
            throw new ServiceException(userResult.getMsg());
        }

        if (StringUtils.isNull(userResult) || StringUtils.isNull(userResult.getData()))
        {
            recordLogininfor(username, Constants.LOGIN_FAIL, "登录用户不存在");
            throw new ServiceException("登录用户：" + username + " 不存在");
        }
        LoginUser userInfo = userResult.getData();
        SysUser user = userResult.getData().getSysUser();
        if (UserStatus.DELETED.getCode().equals(user.getDelFlag()))
        {
            recordLogininfor(username, Constants.LOGIN_FAIL, "对不起，您的账号已被删除");
            throw new ServiceException("对不起，您的账号：" + username + " 已被删除");
        }
        if (UserStatus.DISABLE.getCode().equals(user.getStatus()))
        {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户已停用，请联系管理员");
            throw new ServiceException("对不起，您的账号：" + username + " 已停用");
        }
        if (!SecurityUtils.matchesPassword(password, user.getPassword()))
        {
            recordLogininfor(username, Constants.LOGIN_FAIL, "用户密码错误");
            throw new ServiceException("用户不存在/密码错误");
        }
        recordLogininfor(username, Constants.LOGIN_SUCCESS, "登录成功");
        return userInfo;
    }

    public void logout(String loginName)
    {
        recordLogininfor(loginName, Constants.LOGOUT, "退出成功");
    }

    /**
     * 注册
     */
    public void register(String username, String password)
    {
        // 用户名或密码为空 错误
        if (StringUtils.isAnyBlank(username, password))
        {
            throw new ServiceException("用户/密码必须填写");
        }
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH)
        {
            throw new ServiceException("账户长度必须在2到20个字符之间");
        }
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH)
        {
            throw new ServiceException("密码长度必须在5到20个字符之间");
        }

        // 注册用户信息
        SysUser sysUser = new SysUser();
        sysUser.setUserName(username);
        sysUser.setNickName(username);
        sysUser.setPassword(SecurityUtils.encryptPassword(password));
        R<?> registerResult = remoteUserService.registerUserInfo(sysUser, SecurityConstants.INNER);

        if (R.FAIL == registerResult.getCode())
        {
            throw new ServiceException(registerResult.getMsg());
        }
        recordLogininfor(username, Constants.REGISTER, "注册成功");
    }

    /**
     * 记录登录信息
     * 
     * @param username 用户名
     * @param status 状态
     * @param message 消息内容
     * @return
     */
    public void recordLogininfor(String username, String status, String message)
    {
        SysLogininfor logininfor = new SysLogininfor();
        logininfor.setUserName(username);
        logininfor.setIpaddr(IpUtils.getIpAddr(ServletUtils.getRequest()));
        logininfor.setMsg(message);
        // 日志状态
        if (StringUtils.equalsAny(status, Constants.LOGIN_SUCCESS, Constants.LOGOUT, Constants.REGISTER))
        {
            logininfor.setStatus("0");
        }
        else if (Constants.LOGIN_FAIL.equals(status))
        {
            logininfor.setStatus("1");
        }
        remoteLogService.saveLogininfor(logininfor, SecurityConstants.INNER);
    }
}