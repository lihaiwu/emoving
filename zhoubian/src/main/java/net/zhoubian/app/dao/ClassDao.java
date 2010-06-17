package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.SubClass;

public abstract class ClassDao extends AbstractDao<net.zhoubian.app.model.Clazz, String> {
	
	public List<SubClass> loadSubClasses(String classId){
		return this.getHibernateTemplate().find("from SubClass a where a.subclassId like ? order by a.sort", classId + "%");
	}
	
}
