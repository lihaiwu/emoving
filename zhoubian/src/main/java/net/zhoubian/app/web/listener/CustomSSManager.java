package net.zhoubian.app.web.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

import net.zhoubian.app.model.Location;
import net.zhoubian.app.model.User;
import net.zhoubian.app.util.BinaryTree;
import net.zhoubian.app.util.GridUtil;
import net.zhoubian.app.web.action.ChatAction;

import org.directwebremoting.Container;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.event.ScriptSessionEvent;
import org.directwebremoting.event.ScriptSessionListener;
import org.directwebremoting.extend.RealScriptSession;
import org.directwebremoting.impl.DefaultScriptSession;
import org.directwebremoting.impl.DefaultScriptSessionManager;

public class CustomSSManager extends DefaultScriptSessionManager implements org.directwebremoting.extend.InitializingBean {
	public static int count = 0;
	public static String CHAT_ROOM = "/zhoubian/chatIndex.do";
	public static BinaryTree<String, HttpSession> bt = new BinaryTree<String, HttpSession>();

	public CustomSSManager() {
		System.out.println("CustomSSManager()");
		count++;
		ChatAction.customSSManager = this;//将自己暴露ReverseAjax业务处理类

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
				
				
//				System.out.println("user:" + user);
				Collection<RealScriptSession> col= CustomSSManager.this.getScriptSessionsByHttpSessionId(httpSession.getId());
				for(RealScriptSession old : col){
					System.out.println("col:" + old.getId());
				}
				
				String currentPage = scriptSession.getPage();
				System.out.println("currentPage:" + currentPage);
				if(!CHAT_ROOM.equals(currentPage)){
					return;
				}
				ScriptSession ss = (ScriptSession) httpSession.getAttribute(CHAT_ROOM);
				if (ss != null) {
					DefaultScriptSession old = sessionMap.get(ss.getId());
                    if(old!=null){
                    	CustomSSManager.this.invalidate(old);
                    }
				}
				
				httpSession.setAttribute(CHAT_ROOM, scriptSession);
				if (user == null) {
//					scriptSession.invalidate();
					return;
				}
				Location location = (Location) httpSession.getAttribute("location");
				bt.insert(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude()), httpSession.getId(), httpSession);
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
				ScriptSession scriptSession = event.getSession();
				System.out.println("scriptSession:" + scriptSession.getId());
				User user = (User) httpSession.getAttribute("user");
				if(user == null){
					return;
				}
				ChatAction.onlineUsers.remove(user.getUid().toString());
				Location location = (Location) httpSession.getAttribute("location");
				
				List<Long> codes = GridUtil.getRelatedGridCode(location.getLatitude(), location.getLongitude(), 4);
				if(codes == null){
					codes = new ArrayList<Long>();
					codes.add(new Long(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude())));
				}
				BinaryTree.Node<String, HttpSession> node = null;
				Map<String, HttpSession> data = null;
				Map.Entry<String, HttpSession> entry = null;
				HttpSession hs = null;
				ScriptSession ss = null;
				for(Long code:codes){
					System.out.println("code:" + code);
					node = CustomSSManager.bt.find(code);
					data = node.getData();
					Iterator it = data.entrySet().iterator();
			        while (it.hasNext()) {
			        	entry = (Map.Entry<String, HttpSession>) it.next();
			        	hs = entry.getValue();
			        	ss = (ScriptSession) hs.getAttribute(CustomSSManager.CHAT_ROOM);
			        	System.out.println("hs:" + hs.getId() + " ss:" + ss.getId());
			        	ss.addScript(new ScriptBuffer().appendScript("removeUser").appendScript("(").appendData(user.getUid()).appendScript(");"));
			        }
				}
				
				System.out.println("sessionDestroyed end");
			}
		});
		
		System.out.println("----------------------");
	}
}
