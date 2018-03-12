package com.sy.test;

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
			.build();
		
		userMapper.add(user);
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
	
}
