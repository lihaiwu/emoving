package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.Location;

public abstract class LocationDao extends AbstractDao<Location, Long>{

	public void saveAll(List<Location> locations){
		this.getHibernateTemplate().saveOrUpdateAll(locations);
	}
}
