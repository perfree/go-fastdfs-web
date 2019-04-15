package com.perfree.mapper;

import org.apache.ibatis.annotations.Insert;
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

	/**
	 * 根据用户账户查询用户信息
	 * @param account
	 * @return
	 */
	@Select("select * from t_user where account = #{account}")
	User getUserByAccount(String account);

	@Insert("insert into t_user(account, password, name, credentialsSalt, email,createTime) values(#{account},#{password},#{name},#{credentialsSalt},#{email},#{createTime})")
	void saveUser(User user);

}
