package com.org.service;

import org.apache.ibatis.annotations.Param;
import com.org.entity.UserBean;

public interface UserService {
	int insertUser(UserBean userBean);
	int deleteUserById(@Param("id")int id);
	int updateUserById(UserBean userBean);
	UserBean getAllUser();
}
