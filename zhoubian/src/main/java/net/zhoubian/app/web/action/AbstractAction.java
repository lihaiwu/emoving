package net.zhoubian.app.web.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public  class AbstractAction extends ActionSupport implements SessionAware, ServletResponseAware, ServletRequestAware,
ServletContextAware{

	protected HttpServletResponse response;

	protected HttpServletRequest request;

	protected ServletContext context;

	protected Map session;

	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	public void setServletRequest(HttpServletRequest request) {
		String msisdn2 = request.getHeader("x-up-calling-line-id");
		this.request = request;

	}

	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	public void setSession(Map session) {
		this.session = session;

	}

	
	/**
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 * @author zhanghao3
	 * @throws Exception
	 */
	public String getRequestParameter(String key,String defaultValue) throws Exception {
		Object obj = request.getParameter(key);
		if (obj == null) {
			return defaultValue;
		} else {
			String value = (String)obj;
			return value;
		}
	}
	
	protected void outJson(Object json){
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

