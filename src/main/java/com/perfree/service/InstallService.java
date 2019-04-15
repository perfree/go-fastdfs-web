package com.perfree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfree.entity.User;
import com.perfree.mapper.UserMapper;

@Service
public class InstallService {

	@Autowired
	private UserMapper userMapper;
	
	public void saveUser(User user) {
		userMapper.saveUser(user);
	}

	
}
