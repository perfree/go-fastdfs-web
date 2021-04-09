package com.perfree.service;

import com.perfree.mapper.UserMapper;
import com.perfree.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private UserMapper userMapper;

    public boolean editUser(User user) {
        return userMapper.updateById(user) > 0;
    }
}
