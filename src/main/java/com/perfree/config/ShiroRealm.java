package com.perfree.config;

import com.perfree.model.User;
import com.perfree.service.PeersService;
import com.perfree.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @description 自定义ShiroRealm
 * @author Perfree
 * @date 2021/3/23 14:48
 */
public class ShiroRealm extends AuthorizingRealm{
	
    @Autowired
    private UserService userService;

	@Autowired
	private PeersService peersService;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//得到用户名称
		User user = new User();
		try {
			BeanUtils.copyProperties(principals.getPrimaryPrincipal(), user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SimpleAuthorizationInfo();
	}
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	 	UsernamePasswordToken userToken = (UsernamePasswordToken) token;
		String account = userToken.getUsername();
		User user = userService.getUserByAccount(account);
		if(user == null) {
			throw new UnknownAccountException("账户不存在");
		}
		if(user.getPassword() == null) {
			throw new IncorrectCredentialsException("密码为空");
		}else {
			String md5Hash = new Md5Hash(userToken.getPassword(), user.getCredentialsSalt()).toString();
			userToken.setPassword(md5Hash.toCharArray());
		}
		String password = user.getPassword();
		user.setPassword(null);
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        request.getSession().setAttribute("peers", peersService.getById(user.getPeersId()));
		return new SimpleAuthenticationInfo(user,password,getName());
	}
	
}