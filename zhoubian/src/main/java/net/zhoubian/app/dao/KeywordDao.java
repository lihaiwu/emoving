package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.Keywords;

public abstract class KeywordDao extends AbstractDao<Keywords, Integer>{

	public void saveAll(List<Keywords> entities){
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}
}
