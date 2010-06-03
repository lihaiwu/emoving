package net.zhoubian.app.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;


/**
 * 
 * DAO的抽象接口，定义DAO的基本方法
 */

public interface Dao<T,ID extends Serializable> {
	
	public Class getObjectClass();
	
	public ID addObject(T t) ;
	
	public void updateObject(T t) ;
	
	public T getObjectByID(ID v) ;
	
	public void removeObjectByID(ID v) ;
	
	public void removeObjects(ID[] v) ;
	

	public List<T> findAllObjects();

	public int getColumSum(String columnName, List<Criterion> criterion);

	public List<T> findPageByCriteria(Order order, final int rowstart, final int rowend, Criterion... criterion);

	public Integer getTotalCount(List<Criterion> list);

	public List<T> findByCriteria(Order order, Criterion... criterion);

	public List<T> findByCriteria(Criterion... criterion);
	
	public List<T> findByCriteria(List<Criterion> list);

	public void saveOrUpdate(T t);

	/**
	 * 分页支持的查询
	 */
	public List<T> find(String hql, int rowStart, int rowEnd);

	public List<T> find2(final String hql, final int first, final int pageSize);

	/**
	 * 查询所有记录总数
	 */
	public int count(String hql);

	

}

