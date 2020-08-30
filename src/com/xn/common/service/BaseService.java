package com.xn.common.service;

import java.util.List;

import com.xn.common.exception.ServiceException;
import com.xn.common.page.BasePage;

/**
 * 业务接口基础类，所有service类的父类
 * @author yoko
 * @param <T>
 */
public interface BaseService<T> {

	//添加记录
	public void add(T t) throws ServiceException;

	//更新记录
	public void update(T t) throws ServiceException;

	//删除记录，支持批量删除
	public void delete(Object... ids) throws ServiceException;

	//查询总数
	public int queryByCount(BasePage page) throws ServiceException;

	//查询分页列表
	public List<T> queryByList(BasePage page) throws ServiceException;
	
	//查询列表-无分页
	public List<T> queryAllList() throws ServiceException;
	
	//查询列表-无分页-有条件
	public List<T> queryAllList(T t) throws ServiceException;
	
	//根据id查询记录
	public T queryById(Object id) throws ServiceException;
	
	//根据条件查询记录
	public T queryByCondition(T t) throws ServiceException;

	/**
	 * 更新实体:主要作用1.逻辑删除，2更新状态
	 *
	 * @param t
	 * @return
	 */
	public int manyOperation(T t);
	
}
