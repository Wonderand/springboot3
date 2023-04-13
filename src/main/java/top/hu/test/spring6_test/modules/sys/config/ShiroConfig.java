package top.hu.test.spring6_test.modules.sys.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.hu.test.spring6_test.modules.sys.common.MyRealm;

@Configuration
public class ShiroConfig {

    @Autowired
    private MyRealm realm;

    @Bean
    public DefaultWebSecurityManager securityManager(MyRealm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        // [重点]解决报错 org.apache.shiro.UnavailableSecurityManagerException
        ThreadContext.bind(securityManager);
        return securityManager;
    }

    //配置securityManager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager(){
        //1创建DefaultWebSecurityManager对象
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //2创建加密对象，设置相关属性
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
            //2.1采用MD5加密
        matcher.setHashAlgorithmName("md5");
            //2.2迭代加密次数
        matcher.setHashIterations(4);
        //3将加密对象存储到myrealm中
        realm.setCredentialsMatcher(matcher);
        //4将MyRealm存入到DefaultWebSecurityManager对象
        defaultWebSecurityManager.setRealm(realm);
        //5返回
        return defaultWebSecurityManager;
    }
    //配置 Shiro 内置过滤器拦截范围
    @Bean
    public DefaultShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition definition = new
                DefaultShiroFilterChainDefinition();
        //设置不认证可以访问的资源

        definition.addPathDefinition("/myController/userLogin", "anon");
        definition.addPathDefinition("/login", "anon");
        //设置需要进行登录认证的拦截范围
        definition.addPathDefinition("/**", "authc");
        return definition;
    }

}
