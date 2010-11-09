package net.zhoubian.app.service.impl;

import net.zhoubian.app.dao.InformationDao;
import net.zhoubian.app.service.InfoService;

public class InfoServiceImpl implements InfoService{
	private InformationDao infoDao;

	public void setInfoDao(InformationDao infoDao) {
		this.infoDao = infoDao;
	}
	
}
