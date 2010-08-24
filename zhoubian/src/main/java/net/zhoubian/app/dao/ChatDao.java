package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.Chat;

public class ChatDao extends AbstractDao<Chat, Integer> {
	public void saveAll(final List<Chat> chats) {
		this.getHibernateTemplate().saveOrUpdateAll(chats);
	}

}
