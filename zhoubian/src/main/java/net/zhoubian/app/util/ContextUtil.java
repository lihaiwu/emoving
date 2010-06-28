package net.zhoubian.app.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ContextUtil {

	private static ApplicationContext ctx = null;

	static {
		ctx = new ClassPathXmlApplicationContext(new String[] {
				"classpath:applicationContext-resources.xml",
				"classpath:applicationContext-dao.xml",
				"classpath:applicationContext-service.xml",
				"classpath:applicationContext-action.xml" });
	}

	public static ApplicationContext getContext() {
		return ctx;
	}

	public static Object getBean(String beanName) {
		return ctx.getBean(beanName);
	}
}
