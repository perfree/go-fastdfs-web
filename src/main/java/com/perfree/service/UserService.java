package com.perfree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfree.entity.User;
import com.perfree.mapper.UserMapper;

/**
 * 用户Service
 * @author Perfree
 *
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return User
	 */
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	/**
	 * 根据账户获取用户信息
	 * @param account
	 * @return User
	 */
	public User getUserByAccount(String account) {
		// TODO Auto-generated method stub
		return null;
	}
}
