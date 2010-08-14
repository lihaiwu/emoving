package net.zhoubian.app.config;

import junit.framework.TestCase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Super class for all test cases that needed spring enviroment. 
 * An xml file name as xxx.xml like xxx.class, and put with .class 
 * file together.
 * 
 * @author upon
 */
public abstract class SpringEnv extends TestCase {

    protected static ClassPathXmlApplicationContext context = null;
    protected static Log log = null;
    
    

    public final void initBeanFactory() {
        if(log==null) {
            log = LogFactory.getLog(getClass());
        }
        if(context==null) {
            String path = getClass().getName().replace('.', '/') + ".xml";
            System.out.println(path);
            context = new ClassPathXmlApplicationContext("classpath*:applicationContext*.xml");
        }
    }

    public static void destory() {
        log.info("destroy");
        if(context!=null) {
            context.close();
            context = null;
        }
    }

    protected final Object getBean(String id) {
        return context.getBean(id);
    }

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		initBeanFactory();
	}

	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
		destory();
	}

}
