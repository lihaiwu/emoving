package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.User;

public abstract class UserDao extends AbstractDao<User, Integer>{

	public List<User> getUsers(){
		return this.getHibernateTemplate().find("from User");
	}
	public User getUserByLoginName(String loginName){
		List l = this.getHibernateTemplate().find("from User user where user.status=? and user.loginName=?",new Object[]{User.status_normal,loginName});
		if(l!=null && l.size()>0){
			return (User)l.get(0);
		}else{
			return null;
		}
	}
}
