package net.zhoubian.app.service.impl;

import java.util.List;

import net.zhoubian.app.dao.ChatDao;
import net.zhoubian.app.model.Chat;
import net.zhoubian.app.service.ChatService;

public class ChatServiceImpl implements ChatService {
	private ChatDao chatDao;

	public ChatDao getChatDao() {
		return chatDao;
	}

	public void setChatDao(ChatDao chatDao) {
		this.chatDao = chatDao;
	}

	public void saveAll(List<Chat> chats) {
		// TODO Auto-generated method stub
		chatDao.saveAll(chats);
	}
}
