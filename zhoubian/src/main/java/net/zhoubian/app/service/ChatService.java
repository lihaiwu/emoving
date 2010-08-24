package net.zhoubian.app.service;

import java.util.List;

import net.zhoubian.app.model.Chat;

public interface ChatService {
	/**
	 * 批量保存聊天记录
	 * 
	 * @param chats
	 */
	public void saveAll(List<Chat> chats);

}
