package net.zhoubian.app.admin;

import java.util.List;

import org.apache.log4j.Logger;

import net.zhoubian.app.dao.CityDao;
import net.zhoubian.app.model.City;
import net.zhoubian.app.util.ContextUtil;
import net.zhoubian.app.util.PinyinAnalyzer;

public class AreaAdmin {

	private static Logger logger = Logger.getLogger(AreaAdmin.class);
	private static void analyzeCity(){
		CityDao cityDao = (CityDao) ContextUtil.getBean("cityDao");
		List<City> cities = cityDao.findAllObjects();
		for(City c : cities){
			c.setPinyin(PinyinAnalyzer.getPinyin(c.getCityName()));
			cityDao.saveOrUpdate(c);
			logger.info("---------cityName: " + c.getCityName() + ", pinyin: " + c.getPinyin());
		}
	}
	
	public static void main(String[] args) {
		logger.info("----analyse city pinyin");
		analyzeCity();
		
	}
}
