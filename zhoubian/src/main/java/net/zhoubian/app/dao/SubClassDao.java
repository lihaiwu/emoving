package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.Category;
import net.zhoubian.app.model.SubClass;

public abstract class SubClassDao extends AbstractDao<SubClass, String> {

	public List<SubClass> findAllSubclasses(){
		return this.getHibernateTemplate().find("from SubClass c order by c.sort");
	}
	
	public List<Category> loadCategories(String subclassId){
		return this.getHibernateTemplate().find("from Category c where c.categoryId like ? order by c.sort", subclassId + "%");
	}
}
