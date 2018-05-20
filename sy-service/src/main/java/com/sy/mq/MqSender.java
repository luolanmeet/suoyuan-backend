package com.sy.mq;

import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author cck
 */
@Slf4j
@Service
public class MqSender {
    
    final static String EXCHANGE_NAME = "msg.fanout";
    
    @Autowired
    private RabbitMessagingTemplate rabbitTemplate;
    
    /**
     * 给用户发送邮件
     * @param emial
     */
    public void userRegister(String msg) {
        
        log.info("send message to [{}]", msg);
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, "", msg);
    }
    
}
