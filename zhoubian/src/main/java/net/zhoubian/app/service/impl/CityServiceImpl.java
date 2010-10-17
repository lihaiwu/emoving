package net.zhoubian.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.zhoubian.app.dao.CityDao;
import net.zhoubian.app.model.City;
import net.zhoubian.app.model.Province;
import net.zhoubian.app.service.CityService;

public class CityServiceImpl implements CityService{
	private CityDao cityDao;
	
	
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}
	/**
	 * 按省份来获取城市，key为省份名称，value为该省份对应的城市列表
	 * @return
	 */
	public Map<String,List<City>> getAllCitiesOrderByProvinceId(){
		List<City> lc = cityDao.find("from City city order by city.provinceId");
		List<Province> lp = cityDao.getAllProvinces();
		Iterator<City> iterator = lc.iterator();
		City c = iterator.next();
		Map<String,List<City>> result = new LinkedHashMap<String,List<City>>();
		for(int i=0;i<lp.size();i++){
			Province p = lp.get(i);
			List<City> cities = new ArrayList<City>();
			while(c.getProvinceId().equals(p.getProvinceId())){
				cities.add(c);
				if(iterator.hasNext()){
					c = iterator.next();
				}else{
					break;
				}
			}
			if(cities.size()>0){
				result.put(p.getProvinceName(), cities);
			}
		}
		return result;
	}
	/**
	 * 按照拼音首字母来获取省份，key为拼音首字母的大写，value为省份列表
	 * @return
	 */
	public Map<String,List<City>> getAllCitiesOrderByPinyin(){
		List<City> lc = cityDao.find("from City city order by city.pinyin");
		List<Province> lp = cityDao.getAllProvinces();
		String alphabet = "abcdefghijklmnopqrstuvwlyz";
		Iterator<City> iterator = lc.iterator();
		City c = iterator.next();
		Map<String,List<City>> result = new LinkedHashMap<String,List<City>>();
		for(int i=0;i<alphabet.length();i++){
			List<City> cities = new ArrayList<City>();
			char letter = alphabet.charAt(i);
			while(c.getPinyin().startsWith(""+letter)){
				cities.add(c);
				if(iterator.hasNext()){
					c = iterator.next();
				}else{
					break;
				}
			}
			if(cities.size()>0){
				result.put(""+Character.toUpperCase(letter), cities);
			}
		}
		return result;
	}
	/**
	 * 获取所有省份列表
	 * @return
	 */
	public List<Province> getAllProvinces(){
		return cityDao.getAllProvinces();
	}
}
