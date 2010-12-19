package net.zhoubian.app.web.dwr;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.zhoubian.app.model.Category;
import net.zhoubian.app.model.Clazz;
import net.zhoubian.app.model.SubClass;
import net.zhoubian.app.web.cache.CategoryCache;

public class CategoryCombox {

	public Map<String, String> getClasses() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		for (Clazz c : CategoryCache.getClasses().values())
			result.put(c.getClassId(), c.getClassName());
		return result;
	}

	public Map<String, String> getSubclassByParent(String classId) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		Clazz clazz = CategoryCache.getClasses().get(classId);
		if (clazz == null)
			return result;
		List<SubClass> subclasses = clazz.getSubClasses();
		if (subclasses == null)
			return result;
		for (SubClass sub : subclasses)
			result.put(sub.getSubclassId(), sub.getSubclassName());
		return result;
	}

	public Map<String, String> getCategoryByParent(String subclassId) {
		Map<String, String> result = new LinkedHashMap<String, String>();
		SubClass sub = CategoryCache.getSubclasses().get(subclassId);
		if(sub == null)
			return result;
		List<Category> categories = sub.getCategories();
		if (categories == null)
			return result;
		for (Category c : categories)
			result.put(c.getCategoryId(), c.getCategoryName());
		return result;
	}
	public List<SubClass> getSubClassListByParent(String classId){
		Clazz clazz = CategoryCache.getClasses().get(classId);
		if (clazz == null)
			return null;
		List<SubClass> subclasses = clazz.getSubClasses();
		if (subclasses == null)
			return null;
		else
			return subclasses;
	}
	public List<Category> getCategoryListByParent(String categoryId, int length){
		List<Category> result = new ArrayList<Category>();
		SubClass sub = CategoryCache.getSubclasses().get(categoryId.substring(0,2));
		if(sub == null)
			return result;
		List<Category> categories = sub.getCategories();
		if(categories == null)
			return result;
		for(Category c : categories){
			if(c.getCategoryId().length()==length){
				result.add(c);
			}
		}
		return result;
	}
}
