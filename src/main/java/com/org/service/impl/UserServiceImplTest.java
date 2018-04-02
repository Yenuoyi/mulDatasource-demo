package com.org.service.impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.org.entity.UserBean;
import com.org.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = {"classpath*:spring-database.xml"}) 
public class UserServiceImplTest {
	@Autowired
	private UserService userService;
	@Test
	public void testInsertUser() {
		UserBean userBean = new UserBean();
		userBean.setName("yb");
		userBean.setPassword("123123");
		System.out.println(userService.insertUser(userBean));
	}

	@Test
	public void testDeleteUserById() {
		System.out.println(userService.deleteUserById(1));
	}

	@Test
	public void testUpdateUserById() {
		UserBean userBean = new UserBean();
		userBean.setName("yebing");
		userBean.setPassword("123123");
		userBean.setId(2);
		System.out.println(userService.updateUserById(userBean));
	}

	@Test
	public void testGetAllUser() {
		System.out.println(userService.getAllUser());
	}

}
