package com.org.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.dao.UserDao;
import com.org.entity.UserBean;
import com.org.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public int insertUser(UserBean userBean) {
		// TODO Auto-generated method stub
		return userDao.insertUser(userBean);
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.deleteUserById(id);
	}

	@Override
	public int updateUserById(UserBean userBean) {
		// TODO Auto-generated method stub
		return userDao.updateUserById(userBean);
	}

	@Override
	public UserBean getAllUser() {
		// TODO Auto-generated method stub
		return userDao.getAllUser();
	}

}
