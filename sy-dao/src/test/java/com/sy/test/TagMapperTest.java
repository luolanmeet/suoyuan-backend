package com.sy.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.sy.Application;
import com.sy.mapper.TagMapper;

/**
*
* @author cck
*/
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({ Application.class })
public class TagMapperTest {

    @Autowired
    TagMapper tagMapper;

    @Test
    public void testAdd() {
        
        tagMapper.save(8, "生活");
    }
    
    @Test
    public void testGet() {
        System.out.println(tagMapper.get());
    }
}
