package com;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author cck
 */
@SpringBootTest(classes=com.Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestMQ {
    
    @Autowired
    private RabbitMessagingTemplate rabbitTemplate;
    
    @Test
    public void testSend() throws IOException {
        
        rabbitTemplate.convertAndSend("msg.fanout", "", "hello");
        System.in.read();
    }
    
    @Test
    public void testSend2() throws IOException {
        
        String msg = "{'name':'cck', 'address':'3409438184@qq.com'}";
        
        rabbitTemplate.convertAndSend("msg.fanout", "", msg);
        System.in.read();
    }
}
