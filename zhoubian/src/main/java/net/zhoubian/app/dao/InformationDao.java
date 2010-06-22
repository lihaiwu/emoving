package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.Information;

public abstract class InformationDao extends AbstractDao<Information, Integer> {

	public void saveAll(List<Information> entities){
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}
}
