package net.zhoubian.app.web.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.zhoubian.app.model.Chat;
import net.zhoubian.app.model.Location;
import net.zhoubian.app.model.User;
import net.zhoubian.app.service.ChatService;
import net.zhoubian.app.service.MapService;
import net.zhoubian.app.service.UserService;
import net.zhoubian.app.util.GridUtil;
import net.zhoubian.app.util.Page;
import net.zhoubian.app.web.listener.CustomSSManager;
import net.zhoubian.app.web.listener.UserCounterListener;

import org.apache.log4j.Logger;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.extend.RealScriptSession;

@SuppressWarnings("serial")
public class ChatAction extends AbstractAction {
	private static Logger logger = Logger.getLogger(ChatAction.class);

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
	
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	private User user;

	/**
	 * 发送消息，在这个方法里面当聊天记录等于5条的时候自动保存到数据库，然后清空记录
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String addChat(String text, String sender, HttpServletRequest req) throws Exception {
		logger.debug("addChat:" + text);
		HttpSession httpSession = req.getSession();
		User user = (User) httpSession.getAttribute("user");
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
			
			ScriptBuffer sb = new ScriptBuffer().appendScript("receiveChats").appendScript("(").appendData(chat).appendScript(");");
			logger.debug("sb:" + sb);
			CustomSSManager.addScriptToRelatedUser(user, location, 4, true, sb);
			
//			List<Long> codes = GridUtil.getRelatedGridCode(location.getLatitude(), location.getLongitude(), 4);
//			if(codes == null){
//				codes = new ArrayList<Long>();
//				codes.add(new Long(GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude())));
//			}
//			BinaryTree.Node<String, ScriptSession> node = null;
//			Map<String, ScriptSession> data = null;
//			Map.Entry<String, ScriptSession> entry = null;
//			ScriptSession ss = null;
//			for(Long code:codes){
//				logger.debug("code:" + code);
//				node = CustomSSManager.bt.find(code);
//				data = node.getData();
//				Iterator<Map.Entry<String, ScriptSession>> it = data.entrySet().iterator();
//		        while (it.hasNext()) {
//		        	entry = it.next();
//		        	ss = entry.getValue();
//		        	logger.debug("ss:" + ss.getId());
//		        	if(!ss.isInvalidated()){
//		        		ss.addScript(new ScriptBuffer().appendScript("receiveChats").appendScript("(").appendData(chat).appendScript(");"));
//		        	}
//		        }
//			}
			
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
    	
		Location location = (Location) httpSession.getAttribute("location");
		
		ScriptBuffer sb = new ScriptBuffer().appendScript("removeUser").appendScript("(").appendData(user.getUid()).appendScript(");");
		CustomSSManager.addScriptToRelatedUser(user, location, 4, false, sb);
		return NONE;
	}
	
	public String chatIndex() {
		//直接到聊天室jsp页面，在CustomSSManager的sessionCreated里插入信息到二叉树里，并给相关用户发送登录js代码
		return SUCCESS;
	}

	public String addUser() {
		logger.debug("addUser");
		try {
			HttpSession httpSession = request.getSession();
			User user = (User) httpSession.getAttribute("user");
			String loginName = null;
			String password = null;
			if(null == user){
				loginName = this.getRequestParameter("loginName", "error");
				password = this.getRequestParameter("password", "error");
				logger.debug("loginName:" + loginName + " password:" + password);
				user = userService.getUserByLoginName(loginName);
				if(!user.getPassword().equals(password)){
					this.setErrorMessage("用户名密码错误");
	        		this.setSuccess(false);
					return SUCCESS;
				}
			}
			if(user != null){
				Set<User> users = (Set<User>) context.getAttribute(UserCounterListener.USERS_KEY);
	        	logger.debug("users:" + users);
	        	if (users != null && users.contains(user)) {
	        		this.setErrorMessage("用户已登录");
	        		this.setSuccess(false);
					return SUCCESS;
	        	}
	        	
				httpSession.setAttribute("user", user);
				Location location = mapService.findLocationsById(user.getCurrentLocationId());
				httpSession.setAttribute("location", location);
				
				//插入到二叉树
				Collection<RealScriptSession> col= customSSManager.getScriptSessionsByHttpSessionId(httpSession.getId());
				for(RealScriptSession old : col){
					logger.debug("col:" + old.getId());
				}
				
				String currentPage = request.getRequestURI();
				logger.debug("currentPage:" + currentPage);
				ScriptSession scriptSession = (ScriptSession) httpSession.getAttribute(CustomSSManager.CHAT_ROOM);
				if (scriptSession != null) {
					logger.debug("ss:" + scriptSession.getId());
				}
				scriptSession.setAttribute(CustomSSManager.HTTP_SESSION, httpSession);
				long  code = GridUtil.getOwnGridCode(location.getLatitude(), location.getLongitude());
				logger.debug("code:" + code);
				CustomSSManager.bt.insert(code, scriptSession.getId(), scriptSession);
				
				CustomSSManager.bt.printTree();
				
				//添加新进入聊天室用户到列表
				String tnjson = "";
	        	tnjson += "{id:'" + user.getUid() + "'";
	        	tnjson += ",leaf:true,iconCls:'icon-user',";
	        	tnjson += "text:'" + user.getLoginName() + "'}";
				ScriptBuffer sb = new ScriptBuffer().appendScript("addUser").appendScript("(").appendData(tnjson).appendScript(");");
				CustomSSManager.addScriptToRelatedUser(user, location, 4, false, sb);
				
				
				this.setSuccess(true);
				return SUCCESS;
			}else{
				this.setErrorMessage("此用户不存在");
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
		HttpSession httpSession = request.getSession();
		Location location = (Location) httpSession.getAttribute("location");
		this.jsonString = CustomSSManager.getRelatedUser(location, 4);
		
//		this.jsonString = "[";
//		for (Iterator<Map.Entry<String, TreeNode>> it = onlineUsers.entrySet().iterator(); it.hasNext();) {
//			Map.Entry<String, TreeNode> en = it.next();
//			TreeNode tn = en.getValue();
//			this.jsonString += "{id:'" + tn.getId() + "'";
//			this.jsonString += ",leaf:true,iconCls:'icon-user',";
//			this.jsonString += "text:'" + tn.getText() + "'}";
//			if (it.hasNext()) {
//				this.jsonString += ",";
//			}
//		}
//		this.jsonString += "]";
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