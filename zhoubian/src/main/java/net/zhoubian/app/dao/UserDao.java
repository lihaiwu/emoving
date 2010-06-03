package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.User;

public abstract class UserDao extends AbstractDao<User, Integer>{

	public List<User> getUsers(){
		return this.getHibernateTemplate().find("from User");
	}
}
