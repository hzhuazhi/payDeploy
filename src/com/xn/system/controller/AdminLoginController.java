package com.xn.system.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xn.common.constant.ManagerConstant;
import com.xn.common.controller.BaseController;
import com.xn.common.enums.ManagerEnum;
import com.xn.common.util.BeanUtils;
import com.xn.tvdeploy.model.AccountApModel;
import com.xn.tvdeploy.model.AccountDpModel;
import com.xn.tvdeploy.model.AccountTpModel;
import com.xn.tvdeploy.model.AgentModel;
import com.xn.tvdeploy.service.AccountApService;
import com.xn.tvdeploy.service.AccountDpService;
import com.xn.tvdeploy.service.AccountTpService;
import com.xn.tvdeploy.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;


import com.xn.common.util.MD5;
import com.xn.system.entity.Account;
import com.xn.system.entity.Module;
import com.xn.system.model.AccountModel;
import com.xn.system.service.AccountService;
import com.xn.system.service.ModuleService;
/**
 * 后台管理员登录控制器
 * @author yoko
 * @createTime  2016-6-24 下午01:09:20
 */
@Controller
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {

	@ModelAttribute("headType")
	public String returnHeadType() {
		return "系统信息";
	}

	@Autowired
	private AccountService<Account> accountService;//内部账号


	@Autowired
	private AccountTpService<AccountTpModel> accountTpService;//渠道账号

	@Autowired
	private AgentService<AgentModel> agentService;//代理账号

	@RequestMapping(value = "/login")
	public String goLogina(Model model, HttpServletRequest request) {
		return "admin-login";
	}

	@RequestMapping(value = "/admin-login")
	public String goLoginn(Model model, HttpServletRequest request) {
		return "admin-login";
	}

	@RequestMapping(value = "/")
	public String goLogin(Model model, HttpServletRequest request) {
		return "admin-login";
	}

	@RequestMapping(value = "")
	public String goLoginaa(Model model, HttpServletRequest request) {
		return "admin-login";
	}

	@RequestMapping(value = "/welcome")
	public String goWelcome(Model model, HttpServletRequest request) {
		return "backstage-index";
	}

	/**
	 * 后台管理员登陆首页
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin/index", method = RequestMethod.GET)
	public String getToBackstageIndex(Model model, HttpServletRequest request) {
		return "backstage-index";
	}

	/**
	 * 管理员登陆
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String adminLogin(HttpServletRequest request, HttpServletResponse response, Account model) {
		long accountId = ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO;
		model.setPassWd(MD5.parseMD5(model.getPassWd()));
		//内部账号
		Account adminAccount = accountService.queryByCondition(model);
		if(adminAccount != null && adminAccount.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
			accountId = adminAccount.getId();
		}
		//渠道账号
		AccountTpModel accountTpModel = BeanUtils.copy(model, AccountTpModel.class);
		accountTpModel = accountTpService.queryByCondition(accountTpModel);
		if(accountTpModel != null && accountTpModel.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
			accountId = accountTpModel.getId();
			adminAccount = BeanUtils.copy(accountTpModel, Account.class);
		}
		//代理账号
		AgentModel agentModel = BeanUtils.copy(model, AgentModel.class);
		agentModel = agentService.queryByCondition(agentModel);
		if(agentModel != null && agentModel.getId() > ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO){
			accountId = agentModel.getId();
			adminAccount = BeanUtils.copy(agentModel, Account.class);
		}

		if (accountId <= ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO) {
			request.setAttribute("MSG", "账号或密码错误!");
			return "admin-login";
		}else{
			WebUtils.setSessionAttribute(request, ManagerConstant.PUBLIC_CONSTANT.ACCOUNT, adminAccount);
			WebUtils.setSessionAttribute(request, "accountNum", adminAccount.getAccountNum());
		}
		//获取该用户的权限
		//		List<Module> module = moduleService.getRoleModule(adminAccount.getRoleId());
		//		m.addAttribute("menuList", module);
		return "backstage-index";
	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String loginOut(HttpServletRequest request, HttpServletResponse response) {
		try {
			Account adminAccount = (Account) request.getSession().getAttribute(ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
			if (adminAccount != null) {
				request.getSession().removeAttribute(ManagerConstant.PUBLIC_CONSTANT.ACCOUNT);
			}
			return "admin-login";
		} catch (ClassCastException e) {
			return "bus-login";
		}
	}

	@RequestMapping(value = "/headlink/{type}", method = RequestMethod.GET)
	public String goHead(Model model, @PathVariable("type") int type) {
		String headType = null;
		String returnUrl = "/admin/welcome";
		switch (type) {
		case 0:
			headType = "用户管理";
			returnUrl = "/admin/user/go/list-user";
			break;
		case 1:
			headType = "订单管理";
			returnUrl = "/admin/area/go/list-area";
			break;
		case 2:
			headType = "产品管理";
			returnUrl = "/admin/product-type/go/list-product-type";
			break;
		case 3:
			headType = "网站管理";
			returnUrl = "/admin/admin/go/list-admin";
			break;

		}
		model.addAttribute("headType", headType);
		return "redirect:" + returnUrl;
	}

}
