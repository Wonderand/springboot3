package top.hu.test.spring6_test.modules.sys.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.hu.test.spring6_test.modules.sys.common.MyRealm;

@Controller
@RequestMapping("myController")
public class MyController {


    @GetMapping("userLogin/{name}/{pwd}")
    @ResponseBody
    public String userLogin(@PathVariable String name,@PathVariable String pwd){
        System.out.println("name="+name+"----------pwd="+pwd);
        MyRealm myRealm = new MyRealm();
        myRealm.test(name);
        //1获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //2封装请求数据到token
        AuthenticationToken token = new UsernamePasswordToken(name,pwd);
        //3调用login方法进行登录认证
        try{
            subject.login(token);
            return "登录成功";
        }catch (AuthenticationException e){
            e.printStackTrace();
            System.out.println("登录失败");
            return "登录失败";
        }
    }
}
