package com.perfree.mapper;

import com.perfree.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
	@Select("select * from t_user WHERE account = #{account}")
	@Results({
			@Result(id=true,column="id",property="id"),
			@Result(column="account",property="account"),
			@Result(column="password",property="password"),
			@Result(column="name",property="name"),
			@Result(column="credentialsSalt",property="credentialsSalt"),
			@Result(column="email",property="email"),
			@Result(column="createTime",property="createTime"),
			@Result(column="peersId",property="peers",
				one=@One(select="com.perfree.mapper.PeersMapper.getPeersById",fetchType= FetchType.EAGER))
	})
	User getUserByAccount(String account);

	/**
	 * 新增用户
	 * @param user
	 */
	@Insert("insert into t_user(account, password, name, credentialsSalt, email,createTime,updateTime,peersId) values(#{account},#{password},#{name},#{credentialsSalt},#{email},#{createTime},#{updateTime},#{peersId})")
	void saveUser(User user);

	/**
	 * 获取用户数量
	 * @return
	 */
	@Select("select count(1) from t_user")
	Long getUserCount();

	@Update("UPDATE t_user SET peersId=#{peersId},updateTime=#{updateTime} WHERE id=#{id}")
    long switchPeers(User user);
}
