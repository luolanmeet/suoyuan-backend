package com.sy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.cck.Topic;
import com.sy.Application;
import com.sy.mapper.TopicMapper;

/**
*
* @author cck
*/
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({ Application.class })
public class TopicMapperTest {

    @Autowired
    TopicMapper topicMapper;

    @Test
    public void testAdd() {
        
        Topic topic = Topic.builder()
                .userId(6)
                .title("你不想说的话是什么")
                .tag("说话")
                .content("hello world")
                .build();
        topicMapper.save(topic);
    }
    
    @Test
    public void testGet() {
        System.out.println(topicMapper.get());
    }
    
    @Test
    public void testGetById() {
        System.out.println(topicMapper.getById(1));
    }
    
    @Test
    public void testGetByTag() {
        System.out.println(topicMapper.getByTag("说话"));
    }
}
