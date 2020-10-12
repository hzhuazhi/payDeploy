package com.xn.system.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xn.common.constant.ManagerConstant;
import org.springframework.web.util.WebUtils;

import com.xn.common.controller.BaseController;
import com.xn.common.util.RequestUtil;
import com.xn.system.entity.Account;

/**
 * @author: yoko
 * @since 2016-06-24
 */
public class AdminLoginFilter extends BaseController implements Filter {

	private static Set<String> exceptURLs;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String path = httpRequest.getServletPath();
		System.out.println(path);
		Object loginAdmin = WebUtils.getSessionAttribute(httpRequest, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
		if (loginAdmin == null && !exceptURLs.contains(path)) {
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin-login.jsp");
			return;
		}
//		if (loginAdmin == null) {
//			if (RequestUtil.isAjax(httpRequest)) {
//				sendFailureMessage(httpResponse, "登录超时，请重新登录");
//				return;
//			}
//			httpResponse.sendRedirect(httpRequest.getContextPath() + "/jsp/admin-login");
//			return;
//		}
//
//		Account account = (Account) loginAdmin;
//		//超级管理员 admin 不受权限控制
//		if (account.getAccountId() == 1) {
//			chain.doFilter(request, response);
//			return;
//		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		exceptURLs = new HashSet<String>();
		exceptURLs.add("/admin/login.do");
		exceptURLs.add("/jsp/admin-login.jsp");
		exceptURLs.add("/adminfile/images/admin_logo.png");
		exceptURLs.add("/adminfile/images/open_icon.png");
		exceptURLs.add("/adminfile/images/close_icon.png");
		exceptURLs.add("/system/module/menuList.do");
		exceptURLs.add("/admin-login.jsp");
		exceptURLs.add("/channelreplenish/actionUpdateCheck.do");
		/*		exceptURLs.add("/admin/admin/admin-login");
		exceptURLs.add("/admin/");
		exceptURLs.add("/admin");
		exceptURLs.add("/admin/admin/index");
		exceptURLs.add("/admin/logistics/go/getListRule");
		exceptURLs.add("/admin/getVisit");*/
	}

	public void destroy() {

	}

}
