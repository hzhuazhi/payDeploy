package com.xn.common.dao;

import java.util.List;

import com.xn.common.exception.ServiceException;
import com.xn.common.page.BasePage;

/**
 * 数据库基本操作
 * @author yoko
 * @date 2016-05-06
 * @param <T>
 */
public interface BaseDao<T> {

	//添加操作
	public void add(T t);

	//更新操作
	public void update(T t);

	//删除操作
	public void delete(Object id);

	//查询数据总条数
	public int queryByCount(BasePage page);

	//查询分页列表
	public List<T> queryByList(BasePage page);
	
	//查询列表-无分页
	public List<T> queryAllList() throws ServiceException;

	//根据ID查询
	public T queryById(Object id);
	
	//查询数据总条数-具体实体
	public int queryCount(T t);
	
	//根据条件查询
	public T queryByCondition(T t);
	
	//查询列表-无分页-有条件
	public List<T> queryAllList(T t) throws ServiceException;

	/**
	 * 更新实体:主要作用1.逻辑删除，2更新状态
	 *
	 * @param t
	 * @return
	 */
	public int manyOperation(T t);
	
	
}
