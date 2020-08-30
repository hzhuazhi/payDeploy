package com.xn.system.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xn.common.dao.BaseDao;
import com.xn.common.service.impl.BaseServiceImpl;
import com.xn.system.dao.RoleModuleRelDao;
import com.xn.system.service.RoleModuleRelService;

/**
 * @author:yoko
 * @since: 2016-06-27 10:15:58
 *
 */
@Service("roleModuleRelService")
public class RoleModuleRelServiceImpl<T> extends BaseServiceImpl<T> implements RoleModuleRelService<T> {

	@SuppressWarnings("unused")
	private final static Logger log= Logger.getLogger(RoleModuleRelServiceImpl.class);

	@Autowired
    private RoleModuleRelDao<T> roleModuleRelDao;
	
	@Override
	public BaseDao<T> getDao() {
		return roleModuleRelDao;
	}	

}