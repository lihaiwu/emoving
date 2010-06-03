package net.zhoubian.app.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import net.zhoubian.app.util.PageHolder;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class AbstractDao<T, ID extends Serializable> extends
		HibernateDaoSupport implements Dao<T, ID> {
	
	private Class<T> tClass = null;
	private DataSource dataSource;

	protected Logger log = Logger.getLogger(this.getClass());

	public AbstractDao() {
		Class c = getClass();
		while (!(c.getGenericSuperclass() instanceof ParameterizedType)) {
			c = c.getSuperclass();
		}

		tClass = (Class<T>) ((ParameterizedType) c.getGenericSuperclass())
				.getActualTypeArguments()[0];

	}

	@SuppressWarnings("unchecked")
	public ID addObject(T t) {
		ID id = (ID) this.getHibernateTemplate().save(t);

		return id;
	}

	public List<T> findAllObjects() {
		return findByCriteria();
	}

	@SuppressWarnings("unchecked")
	public T getObjectByID(ID v) {
		T t = (T) this.getHibernateTemplate().get(tClass, v);

		return t;
	}

	public void removeObjectByID(ID v) {
		this.getHibernateTemplate().delete(getObjectByID(v));
	}

	public void updateObject(T t) {
		this.getHibernateTemplate().update(t);

	}

	public List<T> findByCriteria(Criterion... criterion) {
		return findByCriteria(null, criterion);
	}

	@SuppressWarnings("unchecked")
	public List<T> findByCriteria(Order order, Criterion... criterion) {

		DetachedCriteria crit = DetachedCriteria.forClass(tClass);
		if (order != null) {
			crit.addOrder(order);
		}
		for (Criterion c : criterion) {
			crit.add(c);
		}
		return this.getHibernateTemplate().findByCriteria(crit);
	}

	@SuppressWarnings("unchecked")
	public List<T> findPageByCriteria(Order order, int rowstart, int rowend,
			Criterion... criterion) {
		DetachedCriteria crit = DetachedCriteria.forClass(tClass);
		if (order != null) {
			crit.addOrder(order);
		}
		for (Criterion c : criterion) {
			crit.add(c);
		}

		return this.getHibernateTemplate().findByCriteria(crit, rowstart,
				rowend - rowstart);
	}

	public int getColumSum(String columnName, List<Criterion> criterion) {
		Criteria criteria = getSession().createCriteria(tClass);
		for (Criterion c : criterion) {
			criteria.add(c);
		}
		Object obj = criteria.setProjection(Projections.sum(columnName))
				.uniqueResult();
		Integer sum = (Integer) obj;
		return sum;
	}

	public Integer getTotalCount(List<Criterion> list) {
		Criteria criteria = getSession().createCriteria(tClass);
		for (Criterion c : list) {
			criteria.add(c);
		}

		Object obj = criteria.setProjection(Projections.rowCount())
				.uniqueResult();
		Integer count = (Integer) obj;
		return count;
	}

	public void saveOrUpdate(T t) {
		this.getHibernateTemplate().saveOrUpdate(t);
	}

	@SuppressWarnings("unchecked")
	public List<T> find(final String hql, final int rowStart, final int rowEnd) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql)
						.setMaxResults(rowEnd - rowStart).setFirstResult(
								rowStart).list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<T> find2(final String hql, final int first, final int pageSize) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql).setMaxResults(pageSize)
						.setFirstResult(first).list();
			}
		});
	}

	public List<T> find(final String hql) {
		return this.getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery(hql).list();
			}
		});
	}

	public int count(final String hql) {
		int amount = 0;
		Query query = this.getSession().createQuery(hql);
		if (!query.list().isEmpty()) {
			amount = ((Long) query.iterate().next()).intValue();
		}
		return amount;
	}

	public int count2(final String hql) {
		int amount = 0;
		Query query = this.getSession().createQuery(hql);
		if (!query.list().isEmpty()) {
			amount = (Integer) query.iterate().next();
		}
		return amount;
	}

	public void removeObjects(ID[] v) {
		for (ID id : v) {
			this.removeObjectByID(id);
		}

	}

	public List<T> findByCriteria(List<Criterion> list) {
		Criterion[] cris = new Criterion[0];
		cris = list.toArray(cris);
		return this.findByCriteria(cris);

	}

	public Class getObjectClass() {
		return this.tClass;
	}

	/**
	 * 鍒嗛〉鏀寔鐨勬煡璇�
	 * 
	 * @param hql
	 * @param pageHolder
	 * @return
	 */
	public List find(String hql, PageHolder pageHolder) {
		Session session = this.getSession();
		Query query = session.createQuery(hql);
		query.setMaxResults(pageHolder.getPageSize());
		query.setFirstResult(pageHolder.getFirstIndex());
		return query.list();

	}

	/**
	 * 鏌ヨ鎵�鏈夎褰曟�绘暟
	 * 
	 * @param hql
	 * @return
	 */
	public int getCount(String hql) {
		int amount = 0;

		hql = "select count(*) " + hql;
		Query query = this.getSession().createQuery(hql);

		List list = query.list();
		// return (Integer)list.get(0);
		if (!list.isEmpty()) {
			return (Integer) list.get(0);
		}
		return amount;
	}

	public long getCount2(String hql) {
		int amount = 0;

		hql = "select count(*) " + hql;
		Query query = this.getSession().createQuery(hql);

		List list = query.list();
		// return (Integer)list.get(0);
		if (!list.isEmpty()) {
			return (Long) list.get(0);
		}
		return amount;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * 
	 * @param sql
	 * @param page
	 * @author zhanghao3
	 * @return
	 */
	public PageHolder pagedQueryByJDBC(String sql, PageHolder page) {
		long count = getCountByJdbc(sql);

		StringBuffer paginationSQL = new StringBuffer();
		paginationSQL.append(sql);
		if (page.getFirstIndex() >= 0 && page.getPageSize() > 0) {
			paginationSQL.append(" limit " + page.getFirstIndex() + ","
					+ (page.getFirstIndex() + page.getPageSize()));
		}
		System.out.println("CURRENT SQL: " + paginationSQL.toString());

		List resultList = getJdbcTemplate().queryForList(
				paginationSQL.toString());

		PageHolder pageHolder = new PageHolder();
		pageHolder.setResultSize(Integer.parseInt(count + ""));
		pageHolder.setResultList(resultList);
		return pageHolder;
	}

	private long getCountByJdbc(String sql, Object... param) {
		long count;

		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT count(*) FROM ( ");
		buffer.append(sql);
		buffer.append(" ) totalTable ");

		count = getJdbcTemplate().queryForLong(buffer.toString());

		return count;
	}

	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
}
