package com.xn.system.service;

import java.util.List;

import com.xn.common.service.BaseService;
import com.xn.system.entity.Module;
import com.xn.system.model.ModuleModel;

/**
 * @author:yoko
 * @since: 2016-06-29 15:04:55
 *
 */
public interface ModuleService<T> extends BaseService<T> {

	/**
	 * 获取父级菜单
	 * @param model
	 * @return
	 */
	List<Module> getParentModule(ModuleModel model);

	/**
	 * 
	 * @param roleId
	 * @return
	 */
	List<Module> getRoleModule(Integer roleId);

	/**
	 * 获取所有的权限列表
	 * @return
	 */
	List<Module> getAllModule(Integer type, Integer isAllowed, Integer roleId);

}
