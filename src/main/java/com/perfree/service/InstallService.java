package com.perfree.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perfree.entity.User;
import com.perfree.mapper.UserMapper;

/**
 * 安装操作Service
 * @author Perfree
 *
 */
@Service
public class InstallService {

	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 保存新增用户信息
	 * @param user
	 */
	public void saveUser(User user) {
		userMapper.saveUser(user);
	}

	
}
