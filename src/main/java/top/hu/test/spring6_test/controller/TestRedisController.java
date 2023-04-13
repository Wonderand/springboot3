package top.hu.test.spring6_test.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hu.test.spring6_test.common.R;
import top.hu.test.spring6_test.config.RedisCache;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestRedisController {

    @Autowired
    private RedisCache redisCache;

    private static final Logger logger = LoggerFactory.getLogger(TestRedisController.class);

    @RequestMapping("/getdata")
    public R getData(@RequestBody String username, String password, HttpSession session){

        logger.error(username+password);
        session.setAttribute("user",username);
        Map<String,Object> map = new HashMap<>();
        map.put("name","张三");
        map.put("age",12);
        if (!redisCache.getCacheMap("name").isEmpty()){
//            redisCache.setCacheMap("name",map);
            logger.warn("查出缓存中数据");
            return R.ok().put("obj",redisCache.getCacheMap("name"));
        }else {
            logger.error("缓存没有数据");
            redisCache.setCacheMap("name",map);
//            System.out.println(redisCache.getCacheMap("name"));
            return R.ok().put("obj",redisCache.getCacheMap("name")+username+password);
        }
//        return R.ok().put("Obj",map);
    }

}
