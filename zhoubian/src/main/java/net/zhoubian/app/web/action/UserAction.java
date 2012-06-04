package net.zhoubian.app.web.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.zhoubian.app.model.Clazz;
import net.zhoubian.app.model.Location;
import net.zhoubian.app.model.User;
import net.zhoubian.app.service.MapService;
import net.zhoubian.app.service.UserService;
import net.zhoubian.app.util.GridUtil;
import net.zhoubian.app.web.cache.CategoryCache;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;

public class UserAction extends AbstractAction {

	private static final long serialVersionUID = -2893833894715924382L;
	private static Log logger = LogFactory.getLog(UserAction.class);
	private List<User> users = new ArrayList<User>();
	
	private static Map<String, Clazz> classes = CategoryCache.getClasses();

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

	public Map<String, Clazz> getClasses() {
		return classes;
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
	/**
	 * 新版的首页，包含登录表单
	 * @return
	 */
	public String index1(){
		return "index1";
	}
	/**
	 * 登录验证，并返回登录结果，浏览器收到登录结果后弹出窗口让用户选择具体位置
	 * @return
	 */
	public String login1(){
		return null;
	}
	/**
	 * 新版进入位置后的首页
	 * @return
	 */
	public String locationIndex(){
		request.getSession().setAttribute("cityId", "432");
		request.getSession().setAttribute("cityName", "北京");
		return "locationIndex";
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
			//request.setAttribute("msg", "用户不存在!");
			response.setStatus(response.SC_FORBIDDEN);
			this.outMsg("用户不存在！");
			return null;
		}
		if(!user.getPassword().equals(password)){
			//request.setAttribute("msg", "用户密码不正确!");
			response.setStatus(response.SC_FORBIDDEN);
			this.outMsg("用户密码不正确！");
			return null;
		}
		request.getSession().setAttribute("user", user);
		return "loginsuccess";
	}
	public String logout(){
//		String flag = request.getParameter("flag");
//		request.getSession().invalidate();
//		if(flag!=null && flag.equals("true")){
//			return "login";
//		}else{
//			return "index";
//		}
		request.getSession().invalidate();
		
		try{
			PrintWriter out = response.getWriter();
			out.print("<script type=\"text/javascript\">top.location=\""+request.getContextPath()+"/user_index1.do\";</script>");
			out.flush();
			out.close();
		}catch(Exception e){
			logger.error(e.getMessage());
			logger.error(e.getStackTrace());
		}
		//return "index";
		return null;
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
		//location.setId(GridUtil.getOwnGridCode(location.getLatitude(),location.getLongitude()));
		location.setId((long)(GridUtil.getOwnGridCode(location.getLatitude(),location.getLongitude())*1E10+Calendar.getInstance().getTimeInMillis()/1000));
		location.setUid(user.getUid());
		location.setStatus(Location.status_valid);
		logger.info("location.getId() == "+location.getId());
		int n = 0;
		boolean flag = true;
		while(flag){
			try{
				if(n>0){
					Thread.currentThread().sleep(1000);
				}
				mapService.saveLocation(location);
				flag = false;
			}catch(DataIntegrityViolationException e){
				n++;
				if(n>10){
					flag = false;
					logger.error("location id is duplicate, try times greater than 10");
					throw e;
				}
				//location.setId(GridUtil.getOwnGridCode(location.getLatitude(),location.getLongitude()));
				location.setId((long)(GridUtil.getOwnGridCode(location.getLatitude(),location.getLongitude())*1E10+Calendar.getInstance().getTimeInMillis()/1000));
				logger.warn("location id is duplicate, try again...");
				logger.warn(e.toString());
			}catch(InterruptedException ie){
				logger.error(ie.toString());
			}
		};
		user.setDefaultLocationId(location.getId());
		user.setCurrentLocationId(user.getDefaultLocationId());
		user.setHomeLocationId(location.getId());
		userService.updateUser(user);
		//return "cityindex";
		//return "locationIndex";
		return locationIndex();
	}
	public String mainWindow(){
		return "mainWindow";
	}
	public String goDefaultLocation(){
		User user = (User)request.getSession().getAttribute("user");
		user.setCurrentLocationId(user.getDefaultLocationId());
		userService.updateUser(user);
		Location location = mapService.findLocationsById(user.getCurrentLocationId());
		request.getSession().setAttribute("location", location);
		//return "myzhoubian";
		return locationIndex();
	}

	public String goPreviousLocation(){
		User user = (User)request.getSession().getAttribute("user");
		Location location = mapService.findLocationsById(user.getCurrentLocationId());
		request.getSession().setAttribute("location", location);
		//return "myzhoubian";
		return locationIndex();
	}
	public String goSpecificLocation(){
		String locationId = request.getParameter("locationId");
		User user = (User)request.getSession().getAttribute("user");
		user.setCurrentLocationId(Long.parseLong(locationId));
		userService.updateUser(user);
		Location location = mapService.findLocationsById(user.getCurrentLocationId());
		request.getSession().setAttribute("location", location);
		//return "myzhoubian";
		return locationIndex();
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
	public String listLocations(){
		User user = (User)request.getSession().getAttribute("user");
		List<Location> locations = mapService.findLocationsByUid(user.getUid());
		request.setAttribute("locations", locations);
		return "locationlist";
	}
}
