package com.xl.practice.springbootshiropractice.shiro;

import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.xl.practice.springbootshiropractice.service.UserService;
import com.xl.practice.springbootshiropractice.util.SpringContextUtils;

/**
 * 自定义的URL拦截器
 * 
 * @author Administrator
 *
 */
public class CustomisedURLPathMatchingFilter extends PathMatchingFilter {

	@Autowired
	UserService userService;
	
	
	@Override
	protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		if(null==userService) 
			userService = SpringContextUtils.getContext().getBean(UserService.class);
		// 获取请求的地址URI
		String requestURI = getPathWithinApplication(request);
		if (requestURI == null || requestURI.trim().equals(""))
			return false;
		// 截取掉URI中的“/”
		requestURI = requestURI.substring(1, requestURI.length());

		Subject subject = SecurityUtils.getSubject();
		/**
		 * 如果没有登录，就跳转到登录页面
		 */
		if (!subject.isAuthenticated()) {
			WebUtils.issueRedirect(request, response, "/login");
			return false;
		}
		/**
		 * 验证该路径有没有维护（即是数据库中是否配置了该权限），如果没有维护，一律放行(也可以改为一律不放行)
		 */
		boolean needInterceptor = userService.maintainable(requestURI);
		if (!needInterceptor) {
			return true;
		} else {
			boolean ownPrivilege = false;
			String name = subject.getPrincipal().toString();
			Set<String> currentUserPrivileges = userService.listPrivilege(name);
			if (currentUserPrivileges == null)
				return false;
			for (String p : currentUserPrivileges) {
				if (p.equals(requestURI)) {
					ownPrivilege = true;
					break;
				}
			}
			if (ownPrivilege) {
				return true;
			} else {
				UnauthorizedException ex = new UnauthorizedException("当前用户没有访问路径 " + requestURI + " 的权限");

				subject.getSession().setAttribute("ex", ex);

				WebUtils.issueRedirect(request, response, "/unauthorized");
				return false;
			}
		}
	}
}