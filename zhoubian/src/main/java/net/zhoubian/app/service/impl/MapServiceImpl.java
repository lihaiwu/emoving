package net.zhoubian.app.service.impl;

import net.zhoubian.app.dao.LocationDao;
import net.zhoubian.app.model.Location;
import net.zhoubian.app.service.MapService;

public class MapServiceImpl implements MapService{
	private LocationDao locationDao;
	
	public void setLocationDao(LocationDao locationDao) {
		this.locationDao = locationDao;
	}

	public void saveLocation(Location location){
		locationDao.addObject(location);
	}
}
