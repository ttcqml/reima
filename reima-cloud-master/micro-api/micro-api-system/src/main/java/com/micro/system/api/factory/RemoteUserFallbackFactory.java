package com.micro.system.api.factory;

import com.micro.system.api.model.WxUserInfo;
import com.micro.system.api.model.WxUserPhone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;
import com.micro.common.core.domain.R;
import com.micro.system.api.RemoteUserService;
import com.micro.system.api.domain.SysUser;
import com.micro.system.api.model.LoginUser;

/**
 * 用户服务降级处理
 *
 * @author micro
 */
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService>
{
    private static final Logger log = LoggerFactory.getLogger(RemoteUserFallbackFactory.class);

    @Override
    public RemoteUserService create(Throwable throwable)
    {
        log.error("用户服务调用失败:{}", throwable.getMessage());
        return new RemoteUserService()
        {
            @Override
            public R<LoginUser> getUserInfo(String username, String source)
            {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<LoginUser> getUserInfoById(Long userId, String source) {
                return R.fail("获取用户失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> registerUserInfo(SysUser sysUser, String source)
            {
                return R.fail("注册用户失败:" + throwable.getMessage());
            }

            @Override
            public R<LoginUser> loginByMiniApp(WxUserInfo wxUserInfo, String source)
            {
                return R.fail("小程序登录失败:" + throwable.getMessage());
            }

            @Override
            public R<Boolean> bindPhone(WxUserPhone wxUserPhone, String source) {
                return R.fail("绑定手机号失败:" + throwable.getMessage());
            }
        };
    }
}
