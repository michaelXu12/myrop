package com.xl.practice.springbootshiropractice.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xl.practice.springbootshiropractice.entity.User;
import com.xl.practice.springbootshiropractice.service.UserService;

@RestController
public class TestController {

	@Autowired
	UserService userService;

	/**
	 * 通过查询一条数据 验证springboot项目是能正常访问
	 * 
	 * @param name
	 * @param password
	 * @return
	 */
	@RequestMapping("/testP")
	public String testProject(String name, String password) {
		User user = null;
		try {
			user = userService.selectUserByName(name);
		} catch (Exception e) {
			e.printStackTrace();
			return "failed!!";
		}
		try {
			System.out.println(user.getName() + " " + user.getPassword() + " " + user.getRemark());
		} catch (Exception e) {
			e.printStackTrace();
			return "未查询到用户信息，请检查用户名name是否正确";
		}
		return "success!!!  用户名： " + user.getName() + " 密码： " + user.getPassword() + " 备注：  " + user.getRemark();
	}

	/**
	 * 这个方法只有注册了的用户才能看到 未注册的用户无法看到
	 * 
	 * @return
	 */
	@RequestMapping("/getSecret")
	public String getSecret() {
		String secretContent = "期中考试的答案";
		return secretContent;
	}
	
	/**
	 * 登录方法
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String name, String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		try {
			subject.login(token);
			Session session = subject.getSession();
			session.setAttribute("subject", subject);
			return "登录成功";
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return "登录失败！请检查用户名/密码是否正确";
		}
	}
	
	@RequestMapping("/notLogin")
	public String NotYetLogin() {
		
		return "您还未登录哦！";
	}
	
	/**
	 * @description 获得工资报酬 
	 * @return
	 */
//	@RequiresRoles("TEACHER")  //这个注解表示：该方法需要TEACHER这个角色才能访问
	@RequestMapping("/gainSalary")
	public String gainSalary() {
		return "请查收本月工资：6000 RMB";
	}
	
	/**
	 * 
	 * @return
	 */
//	@RequiresPermissions("PLAY_GAME") // 这个注解表示：该方法需要PLAY_GAME这个权限才能访问
	@RequestMapping("/playGame")
	public String playGame() {
		return "正在越塔中。。。";
	}
	
	/**
	 *   角色不对或无权限跳转的地址
	 * @return
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized() {
		return "。。。。。。。。角色不对，没有权限哦";
	}
	
	@RequestMapping("/DRINKING_WINE")
	public String drinkWine() {
		return "苍老师 有权限进来！drink_wine";
	}
	
	@RequestMapping("/SMOKING")
	public String smoking() {
		return "苍老师 有权限进来！ smoking";
	}
	
	@RequestMapping("/PLAY_GAME")
	public String game() {
		return "小炮同学 有权限进来！ play_game";
	}
	
	
}
