package net.zhoubian.app.service;

import java.util.List;

import net.zhoubian.app.model.TMessage;

public interface TMessageService {
	/**
	 * 批量保存聊天记录
	 * 
	 * @param msgs
	 */
	public void saveAll(List<TMessage> msgs);

}
