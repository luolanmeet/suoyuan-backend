package com.sy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.cck.Reply;
import com.sy.Application;
import com.sy.mapper.ReplyMapper;

/**
*
* @author cck
*/
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({ Application.class })
public class ReplyMapperTest {

    @Autowired
    ReplyMapper replyMapper;

    @Test
    public void testAdd() {
        
        Reply reply = Reply.builder()
                .topicId(1)
                .title("生活的意义是什么")
                .fromUserId(22)
                .nickname("cck")
                .avator("avatoravatoravatoravator")
                .content("good")
                .build();
        replyMapper.save(reply);
        
        String path = replyMapper.getPath(2);
        replyMapper.updatePath(reply.getId(), path + reply.getId() + "/");
    }
    
    @Test
    public void testGet() {
        System.out.println(replyMapper.getByTopicId(1));
    }
    
    @Test
    public void testGetByPath() {
        System.out.println(replyMapper.getByPath("1/2/"));
    }
}
