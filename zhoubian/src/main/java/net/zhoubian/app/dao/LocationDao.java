package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.Location;

public abstract class LocationDao extends AbstractDao<Location, Long>{

	public void saveAll(List<Location> locations){
		this.getHibernateTemplate().saveOrUpdateAll(locations);
	}
	public List<Location> findLocationsByUid(long uid){
		return this.getHibernateTemplate().find("from Location location where location.status=? and location.uid=?",new Object[]{Location.status_valid,uid});
	}
}
