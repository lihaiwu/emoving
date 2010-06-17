package net.zhoubian.app.web.cache;

import java.util.HashMap;
import java.util.Map;

import net.zhoubian.app.model.SubClass;

public class CategoryCache {

	/**
	 * Class的ID<-->实体
	 */
	private static Map<String, net.zhoubian.app.model.Clazz> classes = new HashMap<String, net.zhoubian.app.model.Clazz>();
	private static Map<String,SubClass> subclasses = new HashMap<String, SubClass>();
	
	public static Map<String, net.zhoubian.app.model.Clazz> getClasses(){
		return classes;
	}
	
	public static Map<String, SubClass> getSubclasses(){
		return subclasses;
	}
	
	public synchronized static void putClass(String key, net.zhoubian.app.model.Clazz clazz){
		classes.put(key, clazz);
	}
	
	public synchronized static void putSubClass(String key, SubClass clazz){
		subclasses.put(key, clazz);
	}
}
