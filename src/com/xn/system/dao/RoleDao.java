package com.xn.system.dao;

import java.util.List;

import com.xn.common.dao.BaseDao;
import com.xn.system.entity.Role;
import com.xn.system.model.RoleModel;

/**
 * @author :yoko
 * @since: 2016-06-07 11:23:51
 *
 */
public interface RoleDao<T> extends BaseDao<T> {

	/**
	 * 根据角色名查找角色。判断是否已存在
	 * @param roleName
	 * @return
	 */
	Role queryByName(String roleName);

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
