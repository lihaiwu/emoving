package net.zhoubian.app.web.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
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
		return "loginsuccess";
	}
	public String logout(){
		String flag = request.getParameter("flag");
		request.getSession().invalidate();
		if(flag!=null && flag.equals("true")){
			return "login";
		}else{
			return "index";
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
		user.setDefaultLocationId(location.getId());
		user.setCurrentLocationId(user.getDefaultLocationId());
		user.setHomeLocationId(location.getId());
		userService.updateUser(user);
		return "cityindex";
	}
	public String goDefaultLocation(){
		User user = (User)request.getSession().getAttribute("user");
		user.setCurrentLocationId(user.getDefaultLocationId());
		userService.updateUser(user);
		Location location = mapService.findLocationsById(user.getCurrentLocationId());
		request.getSession().setAttribute("location", location);
		return "myzhoubian";
	}

	public String goPreviousLocation(){
		User user = (User)request.getSession().getAttribute("user");
		Location location = mapService.findLocationsById(user.getCurrentLocationId());
		request.getSession().setAttribute("location", location);
		return "myzhoubian";
	}
	public String goSpecificLocation(){
		String locationId = request.getParameter("locationId");
		User user = (User)request.getSession().getAttribute("user");
		user.setCurrentLocationId(Long.parseLong(locationId));
		userService.updateUser(user);
		Location location = mapService.findLocationsById(user.getCurrentLocationId());
		request.getSession().setAttribute("location", location);
		return "myzhoubian";
	}
	public String cityindex(){
		request.getSession().setAttribute("cityId", "432");
		request.getSession().setAttribute("cityName", "北京");
		return "cityindex";
	}
	public String getLocationsByUser(){
		User user = (User)request.getSession().getAttribute("user");
		List<Location> locations = mapService.findLocationsByUid(user.getUid());
		JSONArray jsonArray = JSONArray.fromObject(locations);
		logger.info(jsonArray);
		try{
			response.setContentType("text/json;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println(jsonArray);
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.toString());
		}
		return null;
	}
}
