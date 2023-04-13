//package top.hu.test.spring6_test.common;
//
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.realm.AuthenticatingRealm;
//import org.apache.shiro.util.ByteSource;
//
///**
// * shiro自定义认证类
// */
//public class MyRealm extends AuthenticatingRealm {
//
//    //自定义的登录认证方法，shiro的login方法底层会调用该类的认证方法进行认证
//    //需要配置自定义的realm生效，在ini文件中配置，在springboot中配置
//    //该方法只是获取进行对比的信息，认证逻辑还是按照shiro底层认证逻辑完成
//
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        //1获取身份信息
//        String principal = authenticationToken.getPrincipal().toString();
//        //2获取凭着信息
//        String password = new String((char[]) authenticationToken.getCredentials());
//        System.out.println("认证用户的信息: "+principal+"----------"+password);
//        //3获取数据库中用户存储的用户信息
//        if (principal.equals("zhangsan")){
//            //3.1数据库中存储的加盐4次迭代的密码
//            String pwdinfo = "c57657658de01300cc0461f7e4213e57";
//            AuthenticationInfo info = new SimpleAuthenticationInfo(authenticationToken.getPrincipal(),pwdinfo,
//                    ByteSource.Util.bytes("salt"),authenticationToken.getPrincipal().toString());
//
//            return info;
//        }
//        //4创建封装校验逻辑，封装数据返回
//
//        return null;
//    }
//}
