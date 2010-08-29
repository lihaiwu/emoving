package net.zhoubian.app.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zhoubian.app.model.Chat;
import net.zhoubian.app.model.Location;
import net.zhoubian.app.model.User;
import net.zhoubian.app.service.ChatService;
import net.zhoubian.app.service.MapService;
import net.zhoubian.app.service.UserService;
import net.zhoubian.app.util.BinaryTree;
import net.zhoubian.app.util.GridUtil;
import net.zhoubian.app.util.Page;
import net.zhoubian.app.web.listener.CustomSSManager;

import org.apache.log4j.Logger;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.RealScriptSession;

@SuppressWarnings("serial")
public class ChatAction extends AbstractAction {
	private static Logger logger = Logger.getLogger(ChatAction.class);
	
	public static Map<String, TreeNode> onlineUsers = new TreeMap<String, TreeNode>();

	private Map<String, Object> infos = new HashMap<String, Object>();

	public static Map<Long, Chat> chats = new TreeMap<Long, Chat>();

	private Chat chat;

	private Page page;

	private ChatService chatService;
	
	private UserService userService;

	private MapService mapService;
	
	public static CustomSSManager customSSManager;
	
	public void setChatService(ChatService chatService) {
		this.chatService = chatService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setMapService(MapService mapService) {
		this.mapService = mapService;
	}


	private String jsonString;

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}


	private boolean success;

	private User user;

	/**
	 * 发送消息，在这个方法里面当聊天记录等于5条的时候自动保存到数据库，然后清空记录
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String addChat(String text, String sender, HttpServletRequest req) throws Exception {
		HttpSession httpSession = req.getSession();
		if (text != null) {
			chat = new Chat();
			chat.setDate(new Date());
			chat.setSender(sender);
			chat.setText(text);
			chats.put(chat.getId(), chat);
			if (chats.size() >= 5) {
				List list = new ArrayList();
				list.addAll(chats.values());
				chatService.saveAll(list);
				chats.clear();
			}

			WebContext wctx = WebContextFactory.get();
			String currentPage = wctx.getCurrentPage();
			logger.debug("currentPage:" + currentPage);
			logger.debug("ssid:" + req.getSession().getAttribute(currentPage));
			logger.debug("sessionid:" + req.getSession().getId());
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
				logger.debug("code:" + code);
				node = CustomSSManager.bt.find(code);
				data = node.getData();
				Iterator it = data.entrySet().iterator();
		        while (it.hasNext()) {
		        	entry = (Map.Entry<String, HttpSession>) it.next();
		        	hs = entry.getValue();
		        	if(hs == null){
		        		logger.debug("hs----------------");
		        	}
		        	ss = (ScriptSession) hs.getAttribute(CustomSSManager.CHAT_ROOM);
		        	logger.debug("hs:" + hs.getId() + " ss:" + ss.getId());
		        	ss.addScript(new ScriptBuffer().appendScript("receiveChats").appendScript("(").appendData(chat).appendScript(");"));
		        }
			}
			
//			s.addFunctionCall("receiveChats", chat);
		}
		return NONE;
	}

	public String findChatHistory() throws ParseException {
		
//		DetachedCriteria dc = DetachedCriteria.forClass(TMessage.class);
//		page.setResult(dc);
//		page = messageService.findByCriteria(page);
//		tmessageService.
		return SUCCESS;
	}
	
	public String login() {
		return SUCCESS;
	}
	
	public String logout() {
		logger.debug("logout()");
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		TreeNode tn = new TreeNode();
		tn.setId(user.getUid().toString());
		tn.setText(user.getName());
		logger.debug(tn.getText());
		onlineUsers.remove(user.getUid().toString());
		
		String tnjson = "";
    	tnjson += "{id:'" + tn.getId() + "'";
    	tnjson += ",leaf:true,iconCls:'icon-user',";
    	tnjson += "text:'" + tn.getText() + "'}";
    	
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
			logger.debug("code:" + code);
			node = CustomSSManager.bt.find(code);
			data = node.getData();
			Iterator it = data.entrySet().iterator();
	        while (it.hasNext()) {
	        	entry = (Map.Entry<String, HttpSession>) it.next();
	        	hs = entry.getValue();
	        	ss = (ScriptSession) hs.getAttribute(CustomSSManager.CHAT_ROOM);
	        	logger.debug("hs:" + hs.getId() + " ss:" + ss.getId());
	        	ss.addScript(new ScriptBuffer().appendScript("removeUser").appendScript("(").appendData(user.getUid()).appendScript(");"));
	        }
		}
		return NONE;
	}
	
	public String chatIndex() {
		HttpSession httpSession = request.getSession();
		User user = (User) httpSession.getAttribute("user");
		if(null != user){
			TreeNode tn = new TreeNode();
			tn.setId(user.getUid().toString());
			tn.setText(user.getName());
			logger.debug(tn.getText());
			onlineUsers.put(tn.getId(), tn);
		}
		return SUCCESS;
	}

	public String addUser() {
		logger.debug("addUser");
		try {
			HttpSession httpSession = request.getSession();
			String loginName = this.getRequestParameter("userName", "error");
			User user = userService.getUserByLoginName(loginName);
			if(user != null){
				TreeNode tn = new TreeNode();
				tn.setId(user.getUid().toString());
				tn.setText(user.getName());
				logger.debug(tn.getText());
				onlineUsers.put(tn.getId(), tn); // 用户下线,则从map中移除
				
				String tnjson = "";
	        	tnjson += "{id:'" + tn.getId() + "'";
	        	tnjson += ",leaf:true,iconCls:'icon-user',";
	        	tnjson += "text:'" + tn.getText() + "'}";
				
				request.getSession().setAttribute("user", user);
				Location location = mapService.findLocationsById(user.getCurrentLocationId());
				request.getSession().setAttribute("location", location);
				
				//插入到二叉树
				Collection<RealScriptSession> col= customSSManager.getScriptSessionsByHttpSessionId(httpSession.getId());
				for(RealScriptSession old : col){
					logger.debug("col:" + old.getId());
				}
				
				String currentPage = request.getRequestURI();
				logger.debug("currentPage:" + currentPage);
				ScriptSession ss = (ScriptSession) httpSession.getAttribute(CustomSSManager.CHAT_ROOM);
				if (ss != null) {
					logger.debug("ss:" + ss.getId());
				}
				long  code = GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude());
				logger.debug("code:" + code);
				CustomSSManager.bt.insert(code, httpSession.getId(), httpSession);
				
				CustomSSManager.bt.printTree();
				
				//添加新进入聊天室用户到列表
				List<Long> codes = GridUtil.getRelatedGridCode(location.getLatitude(), location.getLongitude(), 4);
				if(codes == null){
					codes = new ArrayList<Long>();
					codes.add(new Long(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude())));
				}
				BinaryTree.Node<String, HttpSession> node = null;
				Map<String, HttpSession> data = null;
				Map.Entry<String, HttpSession> entry = null;
				HttpSession hs = null;
				for(Long c:codes){
					logger.debug("code:" + c);
					node = CustomSSManager.bt.find(c);
					data = node.getData();
					Iterator it = data.entrySet().iterator();
			        while (it.hasNext()) {
			        	entry = (Map.Entry<String, HttpSession>) it.next();
			        	hs = entry.getValue();
			        	if(hs == null){
			        		logger.debug("hs----------------");
			        	}
			        	if(httpSession.getId().equals(hs.getId())){
			        		continue;
			        	}
			        	ss = (ScriptSession) hs.getAttribute(CustomSSManager.CHAT_ROOM);
			        	logger.debug("hs:" + hs.getId() + " ss:" + ss.getId());
			        	
						
			        	ss.addScript(new ScriptBuffer().appendScript("addUser").appendScript("(").appendData(tnjson).appendScript(");"));
			        }
				}
				
				
				this.setSuccess(true);
				return SUCCESS;
			}else{
				this.setSuccess(false);
				return SUCCESS;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	public String getOnlineUsers() {
		this.jsonString = "[";
		for (Iterator it = onlineUsers.entrySet().iterator(); it.hasNext();) {
			Map.Entry en = (Map.Entry) it.next();
			TreeNode tn = (TreeNode) en.getValue();
			this.jsonString += "{id:'" + tn.getId() + "'";
			this.jsonString += ",leaf:true,iconCls:'icon-user',";
			this.jsonString += "text:'" + tn.getText() + "'}";
			if (it.hasNext()) {
				this.jsonString += ",";
			}
		}
		this.jsonString += "]";
		logger.debug("jsonString:" + this.jsonString);
//		this.outJson(null);
		return SUCCESS;
	}

	public boolean isSuccess() {
		return success;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Map<String, Object> getInfos() {
		return infos;
	}

	public void setInfos(Map<String, Object> infos) {
		this.infos = infos;
	}


	public static class TreeNode {
		public String id;

		public String text;

		public String getId() {
			return this.id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getText() {
			return this.text;
		}

		public void setText(String text) {
			this.text = text;
		}
	}
}