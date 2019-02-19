package com.perfree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfree.entity.User;
import com.perfree.mapper.UserMapper;

@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;

	public User getUser() {
		return userMapper.selectById(1);
	}
}
