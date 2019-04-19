package com.perfree.entity;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * User实体类
 * @author Perfree
 */
@Data
public class User implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;

	@NotBlank
	private String account;

	@NotBlank
	private String password;

	@NotBlank
	private String name;

	private String credentialsSalt;

	@NotBlank
	@Email
	private String email;

	private Integer peersId;

	private String createTime;

	private String updateTime;

	private Peers peers;
}
