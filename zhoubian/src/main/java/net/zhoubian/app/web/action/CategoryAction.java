package net.zhoubian.app.web.action;

import java.util.Map;

import net.zhoubian.app.model.Clazz;
import net.zhoubian.app.web.cache.CategoryCache;

public class CategoryAction extends AbstractAction{

	private static final long serialVersionUID = 5889451494184615367L;

	private static Map<String, Clazz> classes = CategoryCache.getClasses();
	
	public String list(){
		
		return SUCCESS;
	}
	
	public Map<String, Clazz> getClasses() {
		return classes;
	}
}
