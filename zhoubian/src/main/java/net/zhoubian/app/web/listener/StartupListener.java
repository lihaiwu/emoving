package net.zhoubian.app.web.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.zhoubian.app.dao.ClassDao;
import net.zhoubian.app.dao.SubClassDao;
import net.zhoubian.app.model.Clazz;
import net.zhoubian.app.model.SubClass;
import net.zhoubian.app.web.cache.CategoryCache;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class StartupListener implements ServletContextListener{

	private static Logger logger = Logger.getLogger(StartupListener.class);
	
	public void contextDestroyed(ServletContextEvent event) {
		// TODO Auto-generated method stub

	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        
        //加载信息分类
        loadInfoCategories(ctx);		
	}

	private void loadInfoCategories(ApplicationContext ctx){
		logger.info("...loading information category");
		ClassDao classDao = (ClassDao)ctx.getBean("classDao");
		SubClassDao subclassDao = (SubClassDao)ctx.getBean("subclassDao");
		List<Clazz> clazzes = classDao.findAllClasses();
		for(Clazz clazz : clazzes){
			logger.info("......Clazz name:" + clazz.getClassName());
			List<SubClass> subclasses = classDao.loadSubClasses(clazz.getClassId());
			for(SubClass subclass : subclasses){
				logger.info(".........SubClass name:" + subclass.getSubclassName());
				subclass.setCategories(subclassDao.loadCategories(subclass.getSubclassId()));
				CategoryCache.putSubClass(subclass.getSubclassId(), subclass);
			}
			clazz.setSubClasses(subclasses);
			CategoryCache.putClass(clazz.getClassId(), clazz);
		}
	}
}
