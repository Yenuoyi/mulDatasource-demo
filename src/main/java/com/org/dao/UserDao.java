package com.org.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.org.entity.UserBean;

@Repository
public interface UserDao {
	int insertUser(UserBean userBean);
	int deleteUserById(@Param("id")int id);
	int updateUserById(UserBean userBean);
	UserBean getAllUser();
}
