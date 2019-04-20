package com.perfree.config;

import com.perfree.entity.User;
import com.perfree.mapper.PeersMapper;
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

/**
 * ShiroRealm
 * @author Perfree
 */
public class ShiroRealm extends AuthorizingRealm{
	
    @Autowired
    private UserService userService;

	@Autowired
	private PeersMapper peersMapper;

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
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        request.getSession().setAttribute("peers",peersMapper.getPeersById(user.getPeersId()));
		SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user,password,getName());
		return simpleAuthenticationInfo;
	}
	
}