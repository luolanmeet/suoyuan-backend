package com.sy.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cck.User;
import com.sy.Application;
import com.sy.service.IUserService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes=Application.class)
public class UserServiceTest {
    
    @Autowired
    IUserService userService;
    
	@Test
	public void testLogin() {
	    User login = userService.login("akong@163.com", "kong");
	}

	@Test
	public void testRegister() {
		fail("Not yet implemented");
	}

	@Test
	public void testSet() {
	    fail("Not yet implemented");	    
	}

}
