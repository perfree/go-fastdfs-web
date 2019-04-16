package com.perfree.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.perfree.entity.User;

/**
 * UserMapper接口,因数据表不多,故采用注解方式开发
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
	 * @return User
	 */
	@Select("select * from t_user where account = #{account}")
	User getUserByAccount(String account);

	/**
	 * 新增用户
	 * @param user
	 */
	@Insert("insert into t_user(account, password, name, credentialsSalt, email,createTime) values(#{account},#{password},#{name},#{credentialsSalt},#{email},#{createTime})")
	void saveUser(User user);

}
