package net.zhoubian.app.web.action;

import java.util.List;
import java.util.Map;

import net.zhoubian.app.model.City;
import net.zhoubian.app.web.cache.AreaCache;

public class AreaAction extends AbstractAction{

	private static final long serialVersionUID = -1491775725684555334L;

	private Map<String, List<City>> cityMap;
	
	public String listCities(){
		this.cityMap = AreaCache.getCityMap();
		return "switch";
	}
	
	public Map<String, List<City>> getCityMap() {
		return cityMap;
	}
}
