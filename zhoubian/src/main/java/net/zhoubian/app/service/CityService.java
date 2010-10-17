package net.zhoubian.app.service;

import java.util.List;
import java.util.Map;

import net.zhoubian.app.model.City;
import net.zhoubian.app.model.Province;

public interface CityService {
	/**
	 * 按省份来获取城市，key为省份名称，value为该省份对应的城市列表
	 * @return
	 */
	public Map<String,List<City>> getAllCitiesOrderByProvinceId();
	/**
	 * 按照拼音首字母来获取省份，key为拼音首字母的大写，value为省份列表
	 * @return
	 */
	public Map<String,List<City>> getAllCitiesOrderByPinyin();
	/**
	 * 获取所有省份列表
	 * @return
	 */
	public List<Province> getAllProvinces();
}
