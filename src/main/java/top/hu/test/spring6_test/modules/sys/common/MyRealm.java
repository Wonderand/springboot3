package top.hu.test.spring6_test.modules.sys.common;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.hu.test.spring6_test.modules.sys.entity.User;
import top.hu.test.spring6_test.modules.sys.service.UserService;
import top.hu.test.spring6_test.modules.sys.service.impl.UserServieImpl;

/**
 * shiro自定义认证类
 */
@Component
public class MyRealm extends AuthorizingRealm {


    @Autowired
    private UserService userService = new UserServieImpl();

    public void test(String name){
        User userInfoByName = userService.getUserInfoByName(name);
        System.out.println("user= "+userInfoByName);

    }

    //自定义授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //自定义登录认证方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1获取用户身份信息
        String name = authenticationToken.getPrincipal().toString();

        //2调用业务层获取用户信息（数据库）
        User user = userService.getUserInfoByName(name);
        System.out.println("user= "+user);
        //3非空判断，将数据封装返回
        if (user!=null){
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
                    authenticationToken.getPrincipal(),
                    user.getPwd(),
                    ByteSource.Util.bytes("salt"),
                    authenticationToken.getPrincipal().toString()
            );
            return info;
        }

        return null;
    }


}
