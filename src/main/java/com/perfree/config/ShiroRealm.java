package com.perfree.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.perfree.common.StringUtil;
import com.perfree.entity.User;
import com.perfree.service.UserService;

/**
 * ShiroRealm
 * @author Perfree
 */
public class ShiroRealm extends AuthorizingRealm{
	
    @Autowired
    private UserService userService;
    
    /**
     * 自定义授权
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//得到用户名称
		User shiroUser=new User();
		try {
			BeanUtils.copyProperties(principals.getPrimaryPrincipal(), shiroUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//从数据库或者缓存中获取角色数据
		/*User user = userService.getUserByAccount(shiroUser.getAccount());
		Set<Role> rolesSet = user.getRoles();
		Set<String> roles = new HashSet<>();
		for (Role role : rolesSet) {
			roles.add(role.getRoleName());
		}*/
		//创建simpleAuthorizationInfo
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		/*设置权限数据(后期可加)
		simpleAuthorizationInfo.setStringPermissions(permissions);*/
		
		//设置角色数据(后期可加)
		//simpleAuthorizationInfo.setRoles(roles);
		return simpleAuthorizationInfo;
	}
	
	/**
	 * 自定义认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		 UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		//从主体传过来的认证信息中,获得用户名
		String account = userToken.getUsername();
		//通过用户名到数据库中获取凭证
		User user = userService.getUserByAccount(account);
		//判断user是否为空
		if(user == null) {
			throw new UnknownAccountException("账户不存在");
		}
		//判断密码是否为空
		if(user.getPassword() == null) {
			throw new IncorrectCredentialsException("密码为空");
		}else {
			//md5加密加盐,放入token
			String md5Hash = new Md5Hash(userToken.getPassword(), user.getCredentialsSalt()).toString();
			userToken.setPassword(md5Hash.toCharArray());
		}
		String password = user.getPassword();
		user.setPassword(null);
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,password,getName());
		return simpleAuthenticationInfo;
	}
	
	
	/**
	 * 测试得到盐和加密后的密码
	 */
	public static void main(String[] args) {
		String uuid = StringUtil.getUUID();
		System.out.println("盐值: "+uuid);
		Md5Hash md5Hash = new Md5Hash("123456",uuid);
		System.out.println("加密后的密码: "+md5Hash.toString());
	}
}