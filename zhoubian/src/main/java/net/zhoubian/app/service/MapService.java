package net.zhoubian.app.service;

import java.util.List;

import net.zhoubian.app.model.Location;


public interface MapService {
	public void saveLocation(Location location);
	public List<Location> findLocationsByUid(long uid);
	public Location findLocationsById(long id);
}
