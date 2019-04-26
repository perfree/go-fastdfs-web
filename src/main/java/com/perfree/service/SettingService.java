package com.perfree.service;

import com.perfree.entity.User;
import com.perfree.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SettingService {

    @Autowired
    private UserMapper userMapper;

    public boolean editUser(User user) {
        if(userMapper.updateUser(user) > 0){
            return true;
        }
        return false;
    }
}
