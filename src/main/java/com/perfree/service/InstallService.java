package com.perfree.service;

import com.perfree.common.AjaxResult;
import com.perfree.common.DateUtil;
import com.perfree.common.PropertiesUtil;
import com.perfree.common.StringUtil;
import com.perfree.entity.User;
import com.perfree.mapper.UserMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

/**
 * 安装操作Service
 * @author Perfree
 *
 */
@Service
public class InstallService {

	/** server地址配置key */
	private static String PROPERTY_NAME = "go.fastdfs.server.address";

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PropertiesUtil propertiesUtil;
	
	/**
	 * 保存新增用户信息
	 * @param user
	 */
	public AjaxResult install(User user,String server) {
		if(userMapper.getUserByAccount(user.getAccount()) != null){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"用户已存在!");
		}
		String uuid = StringUtil.getUUID();
		Md5Hash md5Hash = new Md5Hash(user.getPassword(),uuid);
		user.setPassword(md5Hash.toString());
		user.setCredentialsSalt(uuid);
		user.setCreateTime(DateUtil.getFormatDate(new Date()));
		userMapper.saveUser(user);
		Properties properties = new Properties();
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		try {
			//动态更新配置文件,将server地址写入
			fileInputStream = new FileInputStream(propertiesUtil.getProperties());
			fileOutputStream = new FileOutputStream(propertiesUtil.getProperties());
			properties.load(fileInputStream);
			properties.setProperty(PROPERTY_NAME, server);
			properties.store(fileOutputStream, "Update "+PROPERTY_NAME);
			return new AjaxResult(AjaxResult.AJAX_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(AjaxResult.AJAX_ERROR,"安装失败");
		}finally {
			if(fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fileOutputStream != null) {
				try {
					fileOutputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
}
