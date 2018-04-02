package com.org.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.org.entity.UserBean;
import com.org.service.UserService;

@Controller
public class UserController {
	private UserService userService;
	@RequestMapping("insertUser")
	@ResponseBody
	public int insertUser(@RequestBody UserBean userBean){
		return userService.insertUser(userBean);
	}
	@RequestMapping("deleteUser")
	public int deleteUser(@RequestBody int id){
		return userService.deleteUserById(id);
	}
	@RequestMapping("updateUser")
	public int updateUser(@RequestBody UserBean userBean){
		return userService.updateUserById(userBean);
	}
	@RequestMapping("getAllUser")
	public ModelAndView getAllUser(){
		ModelAndView model = new ModelAndView("main");
		model.addObject("allUser", userService.getAllUser());
		return model;
	}
}
