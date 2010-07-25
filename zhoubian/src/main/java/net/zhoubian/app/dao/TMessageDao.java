package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.TMessage;

public class TMessageDao extends AbstractDao<TMessage, Integer> {
	public void saveAll(final List<TMessage> msgs) {
		this.getHibernateTemplate().saveOrUpdateAll(msgs);
	}

}
