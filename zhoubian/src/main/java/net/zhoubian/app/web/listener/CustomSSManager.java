package net.zhoubian.app.web.listener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.zhoubian.app.model.Location;
import net.zhoubian.app.model.User;
import net.zhoubian.app.util.BinaryTree;
import net.zhoubian.app.util.GridUtil;
import net.zhoubian.app.web.action.ChatAction;

import org.apache.log4j.Logger;
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
	private static Logger logger = Logger.getLogger(CustomSSManager.class);
	public static int count = 0;
	public static final String CHAT_ROOM = "/zhoubian/chatIndex.do";
	public static final String HTTP_SESSION = "HttpSession";
	public static BinaryTree<String, ScriptSession> bt = new BinaryTree<String, ScriptSession>();

	public CustomSSManager() {
		logger.debug("CustomSSManager()");
		logger.debug("this.getScriptSessionTimeout():" + this.getScriptSessionTimeout());
		logger.debug("scriptSessionCheckTime:" + this.scriptSessionCheckTime);
		count++;
		ChatAction.customSSManager = this;//将自己暴露ReverseAjax业务处理类
	}

	public void afterContainerSetup(Container container) {
		// TODO Auto-generated method stub
		logger.debug("afterContainerSetup");
		this.addScriptSessionListener(new ScriptSessionListener() {
			public void sessionCreated(ScriptSessionEvent event) {
				logger.debug("sessionCreated");
				ScriptSession scriptSession = event.getSession(); // 获取新创建的SS
				HttpSession httpSession = WebContextFactory.get().getSession();// 获取构造SS的用户的HttpSession
				logger.debug("httpSession:" + httpSession.getId() + "scriptSession:" + scriptSession.getId());
				User user = (User) httpSession.getAttribute("user");
				Location location = (Location) httpSession.getAttribute("location");
				
//				logger.debug("user:" + user);
				Collection<RealScriptSession> col= CustomSSManager.this.getScriptSessionsByHttpSessionId(httpSession.getId());
				for(RealScriptSession old : col){
					logger.debug("col:" + old.getId());
				}
				
				String currentPage = scriptSession.getPage();
				logger.debug("currentPage:" + currentPage);
				if(!CHAT_ROOM.equals(currentPage)){
					return;
				}
				ScriptSession ss = (ScriptSession) httpSession.getAttribute(CHAT_ROOM);
				if (ss != null) {
					DefaultScriptSession old = sessionMap.get(ss.getId());
                    if(old!=null){
                    	CustomSSManager.this.invalidate(old);
                    	if(location != null){
                    		bt.delete(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude()), old.getId());
                    	}
                    }
				}
				
				httpSession.setAttribute(CHAT_ROOM, scriptSession);
				if (user == null) {
//					scriptSession.invalidate();
					return;
				}
				
				scriptSession.setAttribute(HTTP_SESSION, httpSession);
				bt.insert(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude()), scriptSession.getId(), scriptSession);
				//添加新进入聊天室用户到列表
				String tnjson = "";
	        	tnjson += "{id:'" + user.getUid() + "'";
	        	tnjson += ",leaf:true,iconCls:'icon-user',";
	        	tnjson += "text:'" + user.getLoginName() + "'}";
				ScriptBuffer sb = new ScriptBuffer().appendScript("addUser").appendScript("(").appendData(tnjson).appendScript(");");
				CustomSSManager.addScriptToRelatedUser(user, location, 4, false, sb);
				logger.debug("new:" + scriptSession.getId());
				logger.debug("sessionCreated end");
			}

			public void sessionDestroyed(ScriptSessionEvent event) {
				logger.debug("sessionDestroyed");
				HttpSession httpSession = WebContextFactory.get().getSession();
				ScriptSession scriptSession = event.getSession();
				logger.debug("httpSession:" + httpSession.getId() + "scriptSession:" + scriptSession.getId());
				Collection<RealScriptSession> col= CustomSSManager.this.getScriptSessionsByHttpSessionId(httpSession.getId());
				for(RealScriptSession old : col){
					logger.debug("col:" + old.getId());
				}
				
				User user = (User) httpSession.getAttribute("user");
				if(user == null){
					return;
				}
				
				Location location = (Location) httpSession.getAttribute("location");
				
				
				ScriptBuffer sb = new ScriptBuffer().appendScript("removeUser").appendScript("(").appendData(user.getUid()).appendScript(");");
				addScriptToRelatedUser(user, location, 4, false, sb);
				logger.debug("sessionDestroyed end");
			}
		});
		
		logger.debug("----------------------");
	}
	
	public static void addScriptToRelatedUser(User user, Location location, int range, boolean includeSelf, ScriptBuffer sb){
		int maxDistance = 500;
		switch(range){
		case 1:
			maxDistance = 500;
			break;
		case 2:
			maxDistance = 1000;
			break;
		case 3:
			maxDistance = 2000;
		}
		List<Long> codes = GridUtil.getRelatedGridCode(location.getLatitude(), location.getLongitude(), range);
		if(codes == null){
			codes = new ArrayList<Long>();
		}
		codes.add(new Long(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude())));
		BinaryTree.Node<String, ScriptSession> node = null;
		Map<String, ScriptSession> data = null;
		Map.Entry<String, ScriptSession> entry = null;
		ScriptSession ss = null;
		HttpSession hs = null;
		Location loc = null;
		User u = null;
		for(Long code:codes){
			logger.debug("code:" + code);
			node = CustomSSManager.bt.find(code);
			data = node.getData();
			logger.debug("data:" + data);
			Iterator<Map.Entry<String, ScriptSession>> it = data.entrySet().iterator();
	        while (it.hasNext()) {
	        	entry = it.next();
	        	ss = entry.getValue();
	        	logger.debug("ss:" + ss.getId() + " invalidated?:" + ss.isInvalidated());
	        	if(!ss.isInvalidated()){
	        		hs = (HttpSession) ss.getAttribute(HTTP_SESSION);
	        		logger.debug("hs= " + hs.getId());
	        		u = (User) hs.getAttribute("user");
	        		logger.debug(u.getUid() + " == " + user.getUid());
	        		if(!includeSelf){
	        			if(u.getUid().longValue() == user.getUid().longValue()){
		        			continue;
		        		}
	        		}
	        		
	        		loc = (Location) hs.getAttribute("location");
	        		double latl = GridUtil.getLat1()*Math.abs((location.getLatitude()-loc.getLatitude()));
	        		double lngl = GridUtil.getLng1(location.getLatitude())*Math.abs(location.getLongitude() - loc.getLongitude());
	        		double distance = Math.sqrt(Math.pow(latl, 2) + Math.pow(lngl, 2));
	        		logger.debug("latl:" + latl + " lngl:" + lngl + " distance=" + distance);
	        		if(distance < maxDistance){
	        			ss.addScript(sb);
	        		}
	        	}
	        	
	        }
		}
	}
	
	public static String getRelatedUser(Location location, int range){
		String jsonString = "[";
		int maxDistance = 500;
		switch(range){
		case 1:
			maxDistance = 500;
			break;
		case 2:
			maxDistance = 1000;
			break;
		case 3:
			maxDistance = 2000;
		}
		List<Long> codes = GridUtil.getRelatedGridCode(location.getLatitude(), location.getLongitude(), range);
		if(codes == null){
			codes = new ArrayList<Long>();
			codes.add(new Long(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude())));
		}
		BinaryTree.Node<String, ScriptSession> node = null;
		Map<String, ScriptSession> data = null;
		Map.Entry<String, ScriptSession> entry = null;
		ScriptSession ss = null;
		HttpSession hs = null;
		Location loc = null;
		User user = null;
		for(Long code:codes){
			logger.debug("code:" + code);
			node = CustomSSManager.bt.find(code);
			data = node.getData();
			Iterator<Map.Entry<String, ScriptSession>> it = data.entrySet().iterator();
	        while (it.hasNext()) {
	        	entry = it.next();
	        	ss = entry.getValue();
	        	logger.debug(" ss:" + ss.getId());
	        	if(!ss.isInvalidated()){
	        		hs = (HttpSession) ss.getAttribute(HTTP_SESSION);
	        		loc = (Location) hs.getAttribute("location");
	        		user = (User) hs.getAttribute("user");
	        		double latl = GridUtil.getLat1()*Math.abs((location.getLatitude()-loc.getLatitude()));
	        		double lngl = GridUtil.getLng1(location.getLatitude())*Math.abs(location.getLongitude() - loc.getLongitude());
	        		double distance = Math.sqrt(Math.pow(latl, 2) + Math.pow(lngl, 2));
	        		logger.debug("latl:" + latl + " lngl:" + lngl + " distance=" + distance);
	        		if(distance < maxDistance){
	        			jsonString += "{id:'" + user.getUid() + "'";
	        			jsonString += ",leaf:true,iconCls:'icon-user',";
	        			jsonString += "text:'" + user.getLoginName() + "'}";
	        			jsonString += ",";
	        		}
	        	}
	        }
		}
		if(jsonString.length()>1){
			jsonString = jsonString.substring(0, jsonString.length()-1);
		}
		jsonString += "]";
		return jsonString;
	}
	
	public static String changeLocation(Location location, int range){
		String jsonString = "[";
		int maxDistance = 500;
		switch(range){
		case 1:
			maxDistance = 500;
			break;
		case 2:
			maxDistance = 1000;
			break;
		case 3:
			maxDistance = 2000;
		}
		List<Long> codes = GridUtil.getRelatedGridCode(location.getLatitude(), location.getLongitude(), range);
		if(codes == null){
			codes = new ArrayList<Long>();
			codes.add(new Long(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude())));
		}
		BinaryTree.Node<String, ScriptSession> node = null;
		Map<String, ScriptSession> data = null;
		Map.Entry<String, ScriptSession> entry = null;
		ScriptSession ss = null;
		HttpSession hs = null;
		Location loc = null;
		User user = null;
		for(Long code:codes){
			logger.debug("code:" + code);
			node = CustomSSManager.bt.find(code);
			data = node.getData();
			Iterator<Map.Entry<String, ScriptSession>> it = data.entrySet().iterator();
	        while (it.hasNext()) {
	        	entry = it.next();
	        	ss = entry.getValue();
	        	logger.debug(" ss:" + ss.getId());
	        	if(!ss.isInvalidated()){
	        		hs = (HttpSession) ss.getAttribute(HTTP_SESSION);
	        		loc = (Location) hs.getAttribute("location");
	        		user = (User) hs.getAttribute("user");
	        		double latl = GridUtil.getLat1()*Math.abs((location.getLatitude()-loc.getLatitude()));
	        		double lngl = GridUtil.getLng1(location.getLatitude())*Math.abs(location.getLongitude() - loc.getLongitude());
	        		double distance = Math.sqrt(Math.pow(latl, 2) + Math.pow(lngl, 2));
	        		logger.debug("latl:" + latl + " lngl:" + lngl + " distance=" + distance);
	        		if(distance < maxDistance){
	        			jsonString += "{id:'" + user.getUid() + "'";
	        			jsonString += ",leaf:true,iconCls:'icon-user',";
	        			jsonString += "text:'" + user.getLoginName() + "'}";
	        			jsonString += ",";
	        		}
	        	}
	        }
		}
		if(jsonString.length()>1){
			jsonString = jsonString.substring(0, jsonString.length()-1);
		}
		jsonString += "]";
		return jsonString;
	}
}
