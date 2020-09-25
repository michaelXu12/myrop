package com.xl.practice.springbootshiropractice.shiro;

import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.codec.CodecException;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.xl.practice.springbootshiropractice.entity.User;
import com.xl.practice.springbootshiropractice.service.UserService;

public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 用户通过验证后，Shiro可通过该方法对登录用户进行授权 角色 或 权限, 只有先通过用户验证后才会走到用户授权这一步
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		// 获取登录用户名
		String name = (String) principalCollection.getPrimaryPrincipal();
		/**
		 * 获取当前登录用户的权限和角色
		 */
		Set<String> privileges = userService.listPrivilege(name);
		Set<String> roles = userService.listRole(name);
		// Shiro配置授权 信息
		SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
		/**
		 * 把通过service获取到的角色和权限放进去
		 */
		s.setStringPermissions(privileges);
		s.setRoles(roles);
		return s;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String name = token.getPrincipal().toString();
		// 获取数据库中的密码
		User user = userService.selectUserByName(name);
		String passwordInDB = user.getPassword();
		SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(name, passwordInDB, null, getName());
		return a;
	}

}