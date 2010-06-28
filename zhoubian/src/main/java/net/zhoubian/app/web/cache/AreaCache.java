package net.zhoubian.app.web.cache;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.zhoubian.app.model.City;

public class AreaCache {

	private static Map<String, List<City>> cityMap = new LinkedHashMap<String, List<City>>();

	public static void put(String key, List<City> value) {
		cityMap.put(key, value);
	}

	public static Map<String, List<City>> getCityMap() {
		return cityMap;
	}
}
