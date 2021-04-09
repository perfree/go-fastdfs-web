package com.perfree.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.perfree.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @description user用户表,Mapper接口
 * @author Perfree
 * @date 2021/3/22 14:59
 */
@Mapper
@Component
public interface UserMapper extends BaseMapper<User>  {
}
