package com.xn.system.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.xn.common.constant.ManagerConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xn.common.controller.BaseController;
import com.xn.common.util.HtmlUtil;
import com.xn.system.entity.Module;
import com.xn.system.entity.Role;
//import com.xn.system.enums.AccountType;
import com.xn.system.enums.IsAllowed;
import com.xn.system.model.RoleModel;
import com.xn.system.service.ModuleService;
import com.xn.system.service.RoleService;

/**
 * @author: yoko
 * @since: 2016-06-07 11:23:51
 *
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController {

	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger(RoleController.class);

	@Autowired
	private RoleService<Role> roleService;
	@Autowired
	private ModuleService<Module> moduleService;

	/**
	 * 获取页面
	 */
	@RequestMapping("/list")
	public String list() {
		return "system/role/list-role";
	}
	/**
	 * 获取新增页面
	 */
	@RequestMapping("/new-role")
	public String newRole(Model model) {
		model.addAttribute("adminMenu", moduleService.getAllModule(ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO, IsAllowed.YES.key, null));
		return "system/role/new-role";
	}

	/**
	 * 获取修改页面
	 */
	@RequestMapping("/update-role")
	public String updateRole(Model model, Integer roleId) {
		model.addAttribute("adminMenu", moduleService.getAllModule(ManagerConstant.PUBLIC_CONSTANT.SIZE_VALUE_ZERO, IsAllowed.YES.key, roleId));
		model.addAttribute("role", roleService.queryById(roleId));
		return "system/role/update-role";
	}

	/**
	 * 
	 * <p>Description:获取表格数据列表</p>
	 * @author yoko
	 * @since 2016-7-12 上午10:35:32
	 */
	@RequestMapping("/dataList")
	public void dataList(RoleModel model, HttpServletResponse response) throws Exception {
		List<Role> dataList = roleService.queryByList(model);
		HtmlUtil.writerJson(response, model.getPage(), dataList);
	}

	/**
	 * 添加角色以及相关权限
	 */
	@RequestMapping("/add")
	public void add(Role bean, Integer[] ids, HttpServletResponse response) throws Exception {
		try {
			Role role = roleService.queryByName(bean.getRoleName());
			if (role != null) {
				sendFailureMessage(response, "角色名已存在，请重新填写");
			} else {
				roleService.addRoleAndModule(bean, ids);
				sendSuccessMessage(response, "添加角色成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendFailureMessage(response, "添加角色失败，请稍后再试");
		}
	}

	/**
	 * 修改数据
	 */
	@RequestMapping("/update")
	public void update(Role bean, Integer[] ids, HttpServletResponse response) throws Exception {
		try {
			Role role = roleService.queryByName(bean.getRoleName());
			if (role != null && role.getRoleId() != bean.getRoleId()) {
				sendFailureMessage(response, "角色名已存在，请重新填写");
			} else {
				roleService.updateRoleAndModule(bean, ids);
				sendSuccessMessage(response, "编辑角色成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			sendFailureMessage(response, "编辑角色失败，请稍后重试");
		}
	}

	@RequestMapping("/getId")
	public void getId(Integer roleId, HttpServletResponse response) throws Exception {
		Map<String, Object> context = getRootMap();
		Role bean = roleService.queryById(roleId);
		if (bean == null) {
			sendFailureMessage(response, "没有找到对应的记录!");
			return;
		}
		sendSuccessMessage(response, "", bean);
	}

	@RequestMapping("/delete")
	public void delete(Role role, HttpServletResponse response) throws Exception {
		roleService.update(role);
		sendSuccessMessage(response, "删除成功");
	}

	/**
	 * 获取指定类型的所有角色
	 * 
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/getRole")
	public void getRoleByType(RoleModel model, HttpServletResponse response) throws Exception {
		List<Role> roles = roleService.getRoleByType(model);
		Map<String, Object> map = getRootMap();
		map.put("roles", roles);
		sendSuccessMessage(response, "", map);
	}
}
