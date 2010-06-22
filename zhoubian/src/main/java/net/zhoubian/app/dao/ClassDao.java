package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.Clazz;
import net.zhoubian.app.model.SubClass;

@SuppressWarnings("unchecked")
public abstract class ClassDao extends AbstractDao<Clazz, String> {
	
	public List<Clazz> findAllClasses(){
		return this.getHibernateTemplate().find("from Clazz a order by a.sort");
	}
	
	public List<SubClass> loadSubClasses(String classId){
		return this.getHibernateTemplate().find("from SubClass a where a.subclassId like ? order by a.sort", classId + "%");
	}
	
}
