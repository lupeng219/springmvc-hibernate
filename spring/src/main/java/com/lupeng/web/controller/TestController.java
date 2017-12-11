package com.lupeng.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
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
    public String test(HttpServletRequest request) {
        User user = new User();
        user.setName("lupeng");
        System.err.println(testService.savaUser(user));
        User users = testService.findUserById(1L);
        System.err.println(users.toString());
        redisCache.set("userId", 1);
        System.err.println(redisCache.get("userId"));
        testService.removeUserById(2L);
        request.setAttribute("user", users);
        return "index";
    }

    public static void main(String[] args) {
//        System.err.println(Math.random());
//        System.err.println(isUnique("abvb"));
    }

    public static boolean isUnique(String str){
        String s  = "fdsfsdfdsf";
        String[] ss = s.split(" ");
        System.err.println(ss);
        boolean[] charArr = new boolean[128];
        for(int i=0; i<str.length(); i++){
            int val = str.charAt(i);
            if(charArr[val])
                return false;
            charArr[val] = true;
        }
        
        return true;
    }
    public static int aplusb(int a, int b) {
        // write your code here, try to do it without arithmetic operators.
        if (a == 0)
            return b;
        if (b == 0)
            return a;
        int sum, i;
        i = a ^ b;
        sum = (a & b) << 1;
        return aplusb(sum, i);
    }

    static int aplusbs(int a, int b) {
        if ((a & b) == 0)
            return a | b;
        return aplusb(a ^ b, (a & b) << 1);
    }
}
