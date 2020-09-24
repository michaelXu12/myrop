package com.xl.practice.springbootshiropractice.shiro;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

@ControllerAdvice
public class PublicExceptionHandler {
	
	@ExceptionHandler({UnauthorizedException.class})
	public String processUnauthenticatedException(NativeWebRequest request,UnauthorizedException e) {
		
		return "redirect:unauthorized";
	}
}
