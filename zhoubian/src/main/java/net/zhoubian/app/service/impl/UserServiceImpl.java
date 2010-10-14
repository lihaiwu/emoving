package net.zhoubian.app.service.impl;

import java.util.List;

import net.zhoubian.app.dao.UserDao;
import net.zhoubian.app.model.User;
import net.zhoubian.app.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userDao.saveOrUpdate(user);
	}

	public List<User> getUsers() {
		return userDao.getUsers();
	}
	public User getUserByLoginName(String loginName){
		return userDao.getUserByLoginName(loginName);
	}
	
	public void updateUser(User user){
		userDao.updateObject(user);
	}

}
