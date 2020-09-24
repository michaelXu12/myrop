package com.xl.practice.springbootshiropractice.service;

import com.xl.practice.springbootshiropractice.entity.User;

public interface UserService {
	
	User selectUserByName(String name);
	
}
