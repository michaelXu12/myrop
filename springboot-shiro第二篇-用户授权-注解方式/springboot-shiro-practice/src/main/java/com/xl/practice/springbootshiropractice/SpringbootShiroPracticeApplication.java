package com.xl.practice.springbootshiropractice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xl.practice.springbootshiropractice.Mapper")
public class SpringbootShiroPracticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootShiroPracticeApplication.class, args);
	}

}
