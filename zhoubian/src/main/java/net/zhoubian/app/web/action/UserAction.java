package net.zhoubian.app.web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.zhoubian.app.model.User;
import net.zhoubian.app.service.UserService;

public class UserAction extends AbstractAction {

	private static final long serialVersionUID = -2893833894715924382L;
	private List<User> users = new ArrayList<User>();

	private UserService userService;
	
	private User user;
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
	public String preLogin(){
		return "login";
	}
	public String login(){
		String loginName = request.getParameter("loginName");
		String password = request.getParameter("password");
		User user = userService.getUserByLoginName(loginName);
		if(user==null){
			request.setAttribute("msg", "用户不存在!");
			return "login";
		}
		if(!user.getPassword().equals(password)){
			request.setAttribute("msg", "用户密码不正确!");
			return "login";
		}
		request.getSession().setAttribute("user", user);
		return "loginsuccess";
	}
	public String register(){
		return "register";
	}
	public String submitRegister(){
		user.setCreateTime(Calendar.getInstance().getTime());
		user.setUserType((short)1);
		user.setStatus((short)0);
		user.setGrade((1));
		userService.addUser(user);
		return "cityindex";
	}
}
