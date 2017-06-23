package com.lupeng.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lupeng.web.entity.User;
import com.lupeng.web.service.impl.TestService;


@Controller
@RequestMapping("/test")
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    
    @Autowired
    public TestService testService;
    
    @Resource(name = "redisTemplate")
    protected ValueOperations<String, Object> redisCache;
    
    @RequestMapping("test")
    public String test(HttpServletRequest request){
        User user = new User();
        user.setName("lupeng");
        System.err.println(testService.savaUser(user));
        User users =  testService.findUserById(1L);
        System.err.println(users);
        redisCache.set("userId",users.getId() );
        System.err.println(redisCache.get("userId"));
        request.setAttribute("user",users);
        return "index";
    }
    @RequestMapping("springTest")
    public String springTest(){
        System.err.println("22222222222");
        logger.info(testService.test()+"11111111");
        return null;
        }
    
}
