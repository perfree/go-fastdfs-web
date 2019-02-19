package com.perfree.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("t_user")
public class User {

	private Integer id;
	private String account;
	private String password;
	private String name;
	private String credentialsSalt;
	private String email;
	private Date createTime;
}
