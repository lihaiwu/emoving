package net.zhoubian.app.service.impl;

import java.util.List;

import net.zhoubian.app.dao.TMessageDao;
import net.zhoubian.app.model.TMessage;
import net.zhoubian.app.service.TMessageService;

public class TMessageServiceImpl implements TMessageService {
	private TMessageDao tmessageDao;

	public TMessageDao getTmessageDao() {
		return tmessageDao;
	}

	public void setTmessageDao(TMessageDao tmessageDao) {
		this.tmessageDao = tmessageDao;
	}

	public void saveAll(List<TMessage> msgs) {
		// TODO Auto-generated method stub
		tmessageDao.saveAll(msgs);
	}
}
