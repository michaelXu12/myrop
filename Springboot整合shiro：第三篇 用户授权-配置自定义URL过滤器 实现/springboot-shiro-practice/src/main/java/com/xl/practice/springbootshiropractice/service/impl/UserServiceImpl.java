package com.xl.practice.springbootshiropractice.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xl.practice.springbootshiropractice.Mapper.UserMapper;
import com.xl.practice.springbootshiropractice.entity.User;
import com.xl.practice.springbootshiropractice.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User selectUserByName(String name) {
		return userMapper.selectUserByName(name);
	}

	@Override
	public Set<String> listPrivilege(String name) {
		return userMapper.listPrivilege(name);
	}

	@Override
	public Set<String> listRole(String name) {
		return userMapper.listRole(name);
	}

	@Override
	public boolean maintainable(String requestURI) {
		Set<String> privileges = userMapper.getAllPrivileges();
		if (privileges == null)
			return false;
		for (String p : privileges) {
			if (p.equals(requestURI))
				return true;
		}
		return false;
	}
}
