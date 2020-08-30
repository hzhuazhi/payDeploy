package com.xn.system.service;

import java.util.List;

import com.xn.common.service.BaseService;
import com.xn.system.entity.Role;
import com.xn.system.model.RoleModel;

/**
 * @author: yoko
 * @since: 2016-06-07 11:23:51
 *
 */
public interface RoleService<T> extends BaseService<T> {

	/**
	 * 根据角色名查找，判断角色是否已存在
	 * @param bean
	 * @return
	 */
	Role queryByName(String roleName);

	/**
	 * 添加角色以及相关权限
	 * @param bean
	 * @param moduleIds
	 */
	void addRoleAndModule(Role bean, Integer[] moduleIds);

	/**
	 * 更新角色及权限
	 * @param bean
	 * @param moduleIds
	 */
	void updateRoleAndModule(Role bean, Integer[] moduleIds);

	/**
	 * 根据类型获取角色
	 * @param model
	 * @return
	 */
	List<Role> getRoleByType(RoleModel model);
	
	/**
	 * 查询所有数据不分页
	 */
    List<Role>  queryList();

}
