package net.zhoubian.app.web.action;

import net.zhoubian.app.service.TalkService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TalkAction extends AbstractAction{
	private static Log logger = LogFactory.getLog(TalkAction.class);
	private TalkService talkService;
	
	public void setTalkService(TalkService talkService) {
		this.talkService = talkService;
	}

	public String index(){
		return "index";
	}
}
