package com.perfree.service;

import com.perfree.common.AjaxResult;
import com.perfree.common.DateUtil;
import com.perfree.common.StringUtil;
import com.perfree.entity.Peers;
import com.perfree.entity.User;
import com.perfree.mapper.PeersMapper;
import com.perfree.mapper.UserMapper;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 安装操作Service
 * @author Perfree
 *
 */
@Service
public class InstallService {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private PeersMapper peersMapper;

	/**
	 * 保存新增用户信息
	 * @param user
	 */
	public AjaxResult install(User user,String name,String groupName,String server) {
		if(userMapper.getUserByAccount(user.getAccount()) != null){
			return new AjaxResult(AjaxResult.AJAX_ERROR,"用户已存在!");
		}
		String date = DateUtil.getFormatDate(new Date());
		//存储peers信息
		Peers peers = new Peers();
		peers.setName(name);
		peers.setGroupName(groupName);
		peers.setServerAddress(server);
		peers.setCreateTime(date);
		peersMapper.add(peers);
		//存储用户信息
		String uuid = StringUtil.getUUID();
		Md5Hash md5Hash = new Md5Hash(user.getPassword(),uuid);
		user.setPassword(md5Hash.toString());
		user.setCredentialsSalt(uuid);
		user.setCreateTime(date);
		user.setUpdateTime(date);
		user.setPeersId(peers.getId());
		userMapper.saveUser(user);
		return new AjaxResult(AjaxResult.AJAX_SUCCESS);
	}

	
}
