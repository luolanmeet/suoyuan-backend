package com.sy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.cck.Notify;
import com.sy.Application;
import com.sy.mapper.NotifyMapper;

/**
*
* @author cck
*/
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({ Application.class })
public class NotifyMapperTest {

    @Autowired
    NotifyMapper notifyMapper;

    @Test
    public void testAdd() {
        
        Notify notify = Notify.builder()
                .userId(12)
                .msg("cjc在‘要不要该不该’的话题下回复了你")
                .topicId(1)
                .build();
        notifyMapper.save(notify);
    }
    
    @Test
    public void testGet() {
        
        System.out.println(notifyMapper.get(12));
    }
    
    @Test
    public void testUpdateRead() {
        
        notifyMapper.updateRead(1);
        System.out.println(notifyMapper.get(12));
    }
    
    @Test
    public void getNoReadNum() {
        
        System.out.println(notifyMapper.getNoReadNum(12));
    }
}
