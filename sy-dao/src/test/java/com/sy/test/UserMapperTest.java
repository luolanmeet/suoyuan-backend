package com.sy.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.cck.User;
import com.sy.Application;
import com.sy.mapper.UserMapper;

/**
 *
 * @author cck
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Import({ Application.class })
public class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void testAdd() {

        User user = User.builder()
            .email("994470425@qq.com")
            .password("cck")
            .nickname("Ryan")
            .avator("avator")
            .isOpen(1)
            .build();

        System.out.println(userMapper.save(user));
    }

    @Test
    public void testAdd2() {

        for (int i = 0; i < 10; i++) {

            User user = User.builder()
                    .email(i + ".com")
                    .password("a")
                    .nickname("Ryan")
                    .avator("avator")
                    .isOpen(1)
                    .build();
            userMapper.save(user);
        }

    }

    @Test
    public void testUpdate() {

        User user = User.builder()
                .id(1)
                .email("3409438184@qq.com")
                .password("cck")
                .nickname("Ryan")
                .build();

        System.out.println(userMapper.update(user));
    }

    @Test
    public void testGetById() {
        System.out.println(userMapper.getById(1));
    }

    @Test
    public void testGetByEmailAndPwd() {
        System.out.println(userMapper.getByEmailAndPwd("994470425@qq.com", "cck"));
    }

    @Test
    public void testIsExistEmail() {
        System.out.println(userMapper.isExistEmail("9944111701425@qq.com"));
    }

    @Test
    public void testGetRandomPic() {
        System.out.println(userMapper.getRandomPic());
    }

    @Test
    public void testGetOpenDirayUser() {
        System.out.println(userMapper.getOpenDirayUser(0D));
    }
    
    @Test
    public void testGetAvator() {
        
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(7);
        
        List<String> res = userMapper.getAvator(list);
        System.out.println(res);
    }
    
    @Test
    public void testGetAvator2() {
        
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(6);
        list.add(7);
        
        List<String> res = userMapper.getAvator(list);
        System.out.println(res);
    }
    
}
