package top.hu.test.spring6_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hu.test.spring6_test.service.GoodsServiceImpl;

@RestController
public class TestController {

    @Autowired
    private GoodsServiceImpl service;

    @GetMapping("/get")
    public String test(){
        service.get();
        return "test";
    }

    @GetMapping("/hello")
    public String hello(String name) {
        return "hello " + name;
    }
}
