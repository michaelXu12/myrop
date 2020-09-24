package com.xl.practice.springbootshiropractice.service.impl;

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
}
