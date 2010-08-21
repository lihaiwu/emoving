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

import net.zhoubian.app.model.TMessage;
import net.zhoubian.app.model.User;
import net.zhoubian.app.service.TMessageService;
import net.zhoubian.app.util.Page;

import org.apache.log4j.Logger;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.ScriptProxy;
import org.hibernate.criterion.DetachedCriteria;

@SuppressWarnings("serial")
public class TMessageAction extends AbstractAction {
	private static Logger logger = Logger.getLogger(TMessageAction.class);
	
	public static Map<String, TreeNode> onlineUsers = new TreeMap<String, TreeNode>();

	private Map<String, Object> infos = new HashMap<String, Object>();

	public static Map<Long, TMessage> TMessages = new TreeMap<Long, TMessage>();

	private TMessage msg;

	private Page page;

	private TMessageService tmessageService;
	
	private String jsonString;

	public String getJsonString() {
		return jsonString;
	}

	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}

	public void setTmessageService(TMessageService tmessageService) {
		this.tmessageService = tmessageService;
	}


	private boolean success;

	private User user;

	/**
	 * 发送消息，在这个方法里面当聊天记录等于5条的时候自动保存到数据库，然后清空记录
	 * 
	 */
	@SuppressWarnings("unchecked")
	public String addTMessage(String text, String sender) throws Exception {
		if (text != null) {
			msg = new TMessage();
			msg.setDate(new Date());
			msg.setSender(sender);
			msg.setText(text);
			TMessages.put(msg.getId(), msg);
			if (TMessages.size() >= 5) {
				List list = new ArrayList();
				list.addAll(TMessages.values());
				tmessageService.saveAll(list);
				TMessages.clear();
			}

			WebContext wctx = WebContextFactory.get();
			String currentPage = wctx.getCurrentPage();
			logger.debug("currentPage:" + currentPage);
			Collection<ScriptSession> sessions = wctx.getScriptSessionsByPage(currentPage);
			for(ScriptSession ss:sessions){
				logger.debug("ss:" + ss.getId());
			}
			ScriptProxy s = new ScriptProxy(sessions);
			s.addFunctionCall("receiveTMessages", msg);
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

	public String addUser() {
		System.out.println("addUser");
		TreeNode tn = new TreeNode();
		tn.setId(java.util.UUID.randomUUID().toString());
		try {
			tn.setText(this.getRequestParameter("userName", null));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(tn.getText());
		onlineUsers.put(tn.getId(), tn); // 用户下线,则从map中移除
		
		this.setSuccess(true);
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
		System.out.println("jsonString:" + this.jsonString);
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