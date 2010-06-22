package net.zhoubian.app.web.action;

import java.util.Map;

import net.zhoubian.app.model.Clazz;
import net.zhoubian.app.model.SubClass;
import net.zhoubian.app.web.cache.CategoryCache;

public class CategoryAction extends AbstractAction{

	private static final long serialVersionUID = 5889451494184615367L;

	private static Map<String, Clazz> classes = CategoryCache.getClasses();
	private static Map<String, SubClass> subclass = CategoryCache.getSubclasses();
	
	public String list(){
		
		return SUCCESS;
	}
	
	public Map<String, Clazz> getClasses() {
		return classes;
	}
	public Map<String, SubClass> getSubclass() {
		return subclass;
	}
}
