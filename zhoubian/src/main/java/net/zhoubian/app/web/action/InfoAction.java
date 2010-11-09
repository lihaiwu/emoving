package net.zhoubian.app.web.action;

import net.zhoubian.app.service.InfoService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoAction extends AbstractAction{
	private static Log logger = LogFactory.getLog(InfoAction.class);
	private InfoService infoService;
	
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public String add(){
		return "addinfo";
	}
}
