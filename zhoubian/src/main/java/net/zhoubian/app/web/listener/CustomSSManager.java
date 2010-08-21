package net.zhoubian.app.web.listener;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import net.zhoubian.app.model.User;

import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.RealScriptSession;
import org.directwebremoting.impl.DefaultScriptSession;
import org.directwebremoting.impl.DefaultScriptSessionManager;

public class CustomSSManager extends DefaultScriptSessionManager implements org.directwebremoting.extend.InitializingBean {
	public static final String SS_ID = "DWR_ScriptSession_Id";

	public CustomSSManager() {
		System.out.println("CustomSSManager()");
		
		// ReqReverseAjax.manager=this;//将自己暴露ReverseAjax业务处理类

	}

	public void afterContainerSetup(Container container) {
		// TODO Auto-generated method stub
		System.out.println("afterContainerSetup");
		this.addScriptSessionListener(new ScriptSessionListener() {
			public void sessionCreated(ScriptSessionEvent event) {
				System.out.println("sessionCreated");
				ScriptSession scriptSession = event.getSession(); // 获取新创建的SS
				HttpSession httpSession = WebContextFactory.get().getSession();// 获取构造SS的用户的HttpSession
				System.out.println("httpSession:" + httpSession.getId());
				User user = (User) httpSession.getAttribute("user");
				
//				if (user == null) {
//					scriptSession.invalidate();
//					httpSession.invalidate();
//					return;
//				}
				System.out.println("user:" + user);
				String ssId = (String) httpSession.getAttribute(SS_ID);
				System.out.println("ssId:" + ssId);
				if (ssId != null) {
					DefaultScriptSession old=sessionMap.get(ssId);
                    if(old!=null)CustomSSManager.this.invalidate(old);
				}
				httpSession.setAttribute(SS_ID, scriptSession.getId());
//				scriptSession.setAttribute("uid", user.getUid());// 此处将uid和scriptSession绑定
			}

			public void sessionDestroyed(ScriptSessionEvent event) {
				System.out.println("sessionDestroyed");
				HttpSession httpSession = WebContextFactory.get().getSession();
				Collection<RealScriptSession> col= CustomSSManager.this.getScriptSessionsByHttpSessionId(httpSession.getId());
				for(RealScriptSession old : col){
					old.invalidate();
				}
			}
		});
		
		System.out.println("----------------------");
	}
}
