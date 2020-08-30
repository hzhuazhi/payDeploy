package com.xn.system.dao;

import java.util.List;

import com.xn.common.dao.BaseDao;
import com.xn.system.entity.Module;
import com.xn.system.model.ModuleModel;

/**
 * 
 * ClassName: ModuleDao
 * @Description: TODO
 * @author yoko
 * @date 2016-7-26
 */
public interface ModuleDao<T> extends BaseDao<T> {

	/**
	 * 获取父级菜单
	 * @param model
	 * @return
	 */
	List<Module> getParentModule(ModuleModel model);

	/**
	 * 获取角色关联的模块
	 * @param model
	 * @return
	 */
	List<Module> getModuleList(ModuleModel model);

	/**
	 * 获取所有的权限列表
	 * @param model
	 * @return
	 */
	List<Module> getAllModule(ModuleModel model);

}
