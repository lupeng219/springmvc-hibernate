package com.lupeng.web.activeMq;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    private JmsTemplate jmsTemplate;
    
    /**
     * 向指定队列发送消息
     */
    public void sendMessage(Destination destination, final String msg) {
      System.out.println("向队列" + destination.toString() + "发送了消息------------" + msg);
      jmsTemplate.send(destination, new MessageCreator() {
        public Message createMessage(Session session) throws JMSException {
          return session.createTextMessage(msg);
        }
      });
    }
}
