package com.lupeng.web.controller;

import javax.annotation.Resource;
import javax.jms.Destination;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.lupeng.web.activeMq.ConsumerService;
import com.lupeng.web.activeMq.ProducerService;

@Controller
@RequestMapping("/mq")
public class ActivemqController {
    //队列名gzframe.demo
    @Resource(name="demoQueueDestination")
    private Destination demoQueueDestination;
    //队列消息生产者
    @Resource(name="producerService")
    private ProducerService producer;
    
   //队列消息消费者
    @Resource(name="consumerService")
    private ConsumerService consumer;
    
    @RequestMapping(value="/onsend",method=RequestMethod.GET)
    public String producer(@RequestParam("message") String message) {
        System.out.println("------------send to jms");
        ModelAndView mv = new ModelAndView();
        producer.sendMessage(demoQueueDestination, "SSS");
        return "login/login";
    }
}
