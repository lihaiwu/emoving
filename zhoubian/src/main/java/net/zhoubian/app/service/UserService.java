package net.zhoubian.app.service;

import java.util.List;

import net.zhoubian.app.model.User;

public interface UserService {

	public void addUser(User user);
	
	public List<User> getUsers();
	public User getUserByLoginName(String loginName);
}
