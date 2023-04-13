package top.hu.test.spring6_test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hu.test.spring6_test.config.RedisCache;
import top.hu.test.spring6_test.modules.sys.entity.User;
import top.hu.test.spring6_test.modules.sys.service.UserService;


@SpringBootTest
class Spring6TestApplicationTests {

    @Autowired
    private RedisCache redisCache;
    @Autowired
    private UserService userService;
    @Test
    void contextLoads() {
//        Random random = new Random();
//        int i = random.nextInt(10);
//        System.out.println(i);
        User user = userService.getUserInfoByName("张三");
        System.out.println("user= "+user);
    }
    @Test
    void shiroTest(){
        //1初始化获取securityManager
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //2获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //3创建token对象，web应用用户名密码从页面传递
        AuthenticationToken token = new UsernamePasswordToken("zhangsan","z3");
        //4完成登录
        try{
            subject.login(token);
            System.out.println("登录成功______"+token);
            //5判断角色
            boolean hasRole = subject.hasRole("role1");
            System.out.println("是否拥有此角色:"+hasRole);
            //6判断权限
            boolean permitted = subject.isPermitted("user:insert ");
            System.out.println("是否拥有此权限:"+permitted);
            //也可以用checkmission方法，但是没有返回值，没权限抛AuthentictionException
            try{
                subject.checkPermission("user:select");
            }catch (AuthenticationException authenticationException){
                authenticationException.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("登录失败");
        }

    }

    @Test
    void ShiroMd5(){
        //密码明文
        String password = "z3";
        //使用Md5加密
        Md5Hash md5Hash = new Md5Hash(password);
        System.out.println("md5加密= "+md5Hash);
        //带盐的MD5加密，盐就是在密码明文后拼接新的字符串，然后在进行加密
        Md5Hash md5Hash1 = new Md5Hash(password,"salt");
        System.out.println("带盐的md5加密= "+md5Hash1.toHex());
        //为了保证安全，避免别破解还可以多次迭代加密，保证数据安全
        Md5Hash md5Hash2 = new Md5Hash(password,"salt",4);
        System.out.println("4次带盐的md5加密= "+md5Hash2.toHex());
        //使用父类进行加密
        SimpleHash simpleHash = new SimpleHash(
                "MD5",password,"salt",4);
        System.out.println("父类带盐的4次加密= "+simpleHash.toHex());

    }

}
