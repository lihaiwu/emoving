package net.zhoubian.app.web.listener;

import java.util.Collection;

import javax.servlet.http.HttpSession;

import net.zhoubian.app.model.User;
import net.zhoubian.app.util.BinaryTree;

import org.directwebremoting.Container;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.RealScriptSession;
import org.directwebremoting.impl.DefaultScriptSession;
import org.directwebremoting.impl.DefaultScriptSessionManager;

public class CustomSSManager extends DefaultScriptSessionManager implements org.directwebremoting.extend.InitializingBean {
	private BinaryTree bt = new BinaryTree();

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
				System.out.println("event.getSource():" + event.getSource());
				System.out.println("event.toString():" + event.toString());
				ScriptSession scriptSession = event.getSession(); // 获取新创建的SS
				HttpSession httpSession = WebContextFactory.get().getSession();// 获取构造SS的用户的HttpSession
				System.out.println("httpSession:" + httpSession.getId());
				User user = (User) httpSession.getAttribute("user");
				
//				if (user == null) {
//					scriptSession.invalidate();
//					httpSession.invalidate();
//					return;
//				}
//				System.out.println("user:" + user);
				Collection<RealScriptSession> col= CustomSSManager.this.getScriptSessionsByHttpSessionId(httpSession.getId());
				for(RealScriptSession old : col){
					System.out.println("col:" + old.getId());
				}
				
				String currentPage = scriptSession.getPage();
				System.out.println("currentPage:" + currentPage);
				String ssId = (String) httpSession.getAttribute(currentPage);
				System.out.println("ssId:" + ssId);
				if (ssId != null) {
					DefaultScriptSession old = sessionMap.get(ssId);
                    if(old!=null){
                    	CustomSSManager.this.invalidate(old);
                    }
				}
				
				httpSession.setAttribute(currentPage, scriptSession.getId());
				System.out.println("new:" + scriptSession.getId());
//				scriptSession.setAttribute("uid", user.getUid());// 此处将uid和scriptSession绑定
				System.out.println("sessionCreated end");
			}

			public void sessionDestroyed(ScriptSessionEvent event) {
				System.out.println("sessionDestroyed");
				HttpSession httpSession = WebContextFactory.get().getSession();
				System.out.println("httpSession:" + httpSession.getId());
				Collection<RealScriptSession> col= CustomSSManager.this.getScriptSessionsByHttpSessionId(httpSession.getId());
				for(RealScriptSession old : col){
					System.out.println("col:" + old.getId());
				}
				System.out.println("sessionDestroyed end");
			}
		});
		
		System.out.println("----------------------");
	}
}
