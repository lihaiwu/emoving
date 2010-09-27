package net.zhoubian.app.web.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.zhoubian.app.model.Location;
import net.zhoubian.app.model.User;
import net.zhoubian.app.service.MapService;
import net.zhoubian.app.service.UserService;
import net.zhoubian.app.util.GridUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class UserAction extends AbstractAction {

	private static final long serialVersionUID = -2893833894715924382L;
	private static Log logger = LogFactory.getLog(UserAction.class);
	private List<User> users = new ArrayList<User>();

	private UserService userService;
	
	private MapService mapService;
	
	public void setMapService(MapService mapService) {
		this.mapService = mapService;
	}
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
		if(request.getSession().getAttribute("user") != null){
			return "loginsuccess";
		}
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
		if(user.getCurrentLocationId()!=null){
			Location location = mapService.findLocationsById(user.getCurrentLocationId());
			request.getSession().setAttribute("location", location);
		}
		return "loginsuccess";
	}
	public String logout(){
		String flag = request.getParameter("flag");
		request.getSession().invalidate();
		if(flag!=null && flag.equals("true")){
			return "login";
		}else{
			return "cityindex";
		}
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
		String locationName = request.getParameter("locationName");
		String subLocType = request.getParameter("subLocType");
		String locationDesc = request.getParameter("locationDesc");
		String longitude = request.getParameter("lngX");
		String latitude = request.getParameter("latY");
		Location location = new Location();
		location.setLocationName(locationName);
		location.setSubLocType(Integer.parseInt(subLocType));
		location.setLocationDesc(locationDesc);
		location.setLocationType(location.USER_LOCATION);
		Date d = Calendar.getInstance().getTime();
		location.setCreateTime(d);
		location.setLongitude(Float.parseFloat(longitude));
		location.setLatitude(Float.parseFloat(latitude));
		location.setId(GridUtil.getOwnGridCode(location.getLatitude(),location.getLongitude()));
		location.setUid(user.getUid());
		location.setStatus(Location.status_valid);
		logger.info("location.getId() == "+location.getId());
		mapService.saveLocation(location);
		return "cityindex";
	}
}
