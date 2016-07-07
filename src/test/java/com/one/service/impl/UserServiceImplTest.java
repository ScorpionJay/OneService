package com.one.service.impl;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.one.app.App;
import com.one.service.UserService;
import com.one.vo.UserVo;

/**
* @author	jay
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
public class UserServiceImplTest {

	private Logger log = LoggerFactory.getLogger(UserServiceImplTest.class);
	
	@Autowired
	UserService userService;
	
	@Test
	public void testgetByUsername() {
		log.info(userService.getByUsername("jay").toString());
	}

	@Test
	public void testFindAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddUser() {
		UserVo vo = new UserVo();
		vo.setUserName("admint123121311");
		vo.setPassword("");
		vo.setAuthorities("admin");
		vo.setEmail("admin@one.com");
		userService.addUser(vo);
	}

}
