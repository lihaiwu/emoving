package net.zhoubian.app.web.action;

import java.util.ArrayList;
import java.util.List;

import net.zhoubian.app.model.User;
import net.zhoubian.app.service.UserService;

public class UserAction extends AbstractAction {

	private static final long serialVersionUID = -2893833894715924382L;
	private List<User> users = new ArrayList<User>();

	private UserService userService;

	public String list() {
		users = userService.getUsers();
		return SUCCESS;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
