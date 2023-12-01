package com.micro.auth.controller;

import javax.servlet.http.HttpServletRequest;

import com.micro.system.api.model.WxBindPhone;
import com.micro.system.api.model.WxLoginInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.micro.auth.form.LoginBody;
import com.micro.auth.form.RegisterBody;
import com.micro.auth.service.SysLoginService;
import com.micro.common.core.domain.R;
import com.micro.common.core.utils.JwtUtils;
import com.micro.common.core.utils.StringUtils;
import com.micro.common.security.auth.AuthUtil;
import com.micro.common.security.service.TokenService;
import com.micro.common.security.utils.SecurityUtils;
import com.micro.system.api.model.LoginUser;

/**
 * token 控制
 * 
 * @author micro
 */
@RestController
public class TokenController
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private SysLoginService sysLoginService;

    @PostMapping("signInByMiniApp")
    public R<?> loginByMiniApp(@RequestBody WxLoginInfo wxLoginInfo)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.loginByMiniApp(wxLoginInfo);
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @PostMapping("bindPhone")
    public R<?> bindPhone(@RequestBody WxBindPhone wxBindPhone)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.bindPhone(wxBindPhone);
        // 获取登录token
        return R.ok(userInfo);
    }

    @PostMapping("login")
    public R<?> login(@RequestBody LoginBody form)
    {
        // 用户登录
        LoginUser userInfo = sysLoginService.login(form.getUsername(), form.getPassword());
        // 获取登录token
        return R.ok(tokenService.createToken(userInfo));
    }

    @DeleteMapping("logout")
    public R<?> logout(HttpServletRequest request)
    {
        String token = SecurityUtils.getToken(request);
        if (StringUtils.isNotEmpty(token))
        {
            String username = JwtUtils.getUserName(token);
            // 删除用户缓存记录
            AuthUtil.logoutByToken(token);
            // 记录用户退出日志
            sysLoginService.logout(username);
        }
        return R.ok();
    }

    @PostMapping("refresh")
    public R<?> refresh(HttpServletRequest request)
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (StringUtils.isNotNull(loginUser))
        {
            // 刷新令牌有效期
            tokenService.refreshToken(loginUser);
            return R.ok();
        }
        return R.ok();
    }

    @PostMapping("register")
    public R<?> register(@RequestBody RegisterBody registerBody)
    {
        // 用户注册
        sysLoginService.register(registerBody.getUsername(), registerBody.getPassword());
        return R.ok();
    }
}
