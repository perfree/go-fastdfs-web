package com.perfree.mapper;

import org.apache.ibatis.annotations.Select;

import com.perfree.entity.User;

/**
 * UserMapper接口
 * @author Perfree
 *
 */
public interface UserMapper{

	/**
	 * 根据id获取用户信息
	 * @param id
	 * @return User
	 */
	@Select("select * from t_user where id = #{id}")
	User getUserById(Integer id);

}
