package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.City;
import net.zhoubian.app.model.Province;

public abstract class CityDao extends AbstractDao<City, String> {

	@SuppressWarnings("unchecked")
	public List<City> getByFirstChar(String s){
		return this.getHibernateTemplate().find("from City c where c.pinyin like ?", s + "%");
	}
	@SuppressWarnings("unchecked")
	public List<Province> getAllProvinces(){
		return this.getHibernateTemplate().find("from Province c order by provinceId");
	}
}
