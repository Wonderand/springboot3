package top.hu.test.spring6_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import top.hu.test.spring6_test.common.R;

@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(){

        return "login";
    }

    @RequestMapping("/index")
    public R test(){

        return R.ok().put("suess","登录成功");
    }
}
