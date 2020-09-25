package com.xl.practice.springbootshiropractice.service;

import java.util.Set;

import com.xl.practice.springbootshiropractice.entity.User;

public interface UserService {

	/**
	 * 根据用户查询用户
	 * 
	 * @param name
	 * @return
	 */
	User selectUserByName(String name);

	/**
	 * 根据用户名查询对应的权限
	 * 
	 * @param name
	 * @return
	 */
	Set<String> listPrivilege(String name);

	/**
	 * 根据用户名查询对应的角色
	 * 
	 * @param name
	 * @return
	 */
	Set<String> listRole(String name);
	
	/**
	 *  请求的URI是否在维护
	 * @param requestURI
	 * @return
	 */
	boolean maintainable(String requestURI);

}
