package com.xl.practice.springbootshiropractice.Mapper;

import com.xl.practice.springbootshiropractice.entity.User;

public interface UserMapper {
	
	User selectUserByName(String name);
	
}
