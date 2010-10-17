package net.zhoubian.app.web.action;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.zhoubian.app.model.City;
import net.zhoubian.app.model.Location;
import net.zhoubian.app.model.User;
import net.zhoubian.app.service.CityService;
import net.zhoubian.app.service.MapService;
import net.zhoubian.app.util.GridUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MapAction extends AbstractAction{
	private static Log logger = LogFactory.getLog(MapAction.class);
	private MapService mapService;
	private CityService cityService;
	
	public void setMapService(MapService mapService) {
		this.mapService = mapService;
	}
	
	

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}



	public String addUserLocation(){
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
		User user = (User)request.getSession().getAttribute("user");
		location.setUid(user.getUid());
		location.setStatus(Location.status_valid);
		logger.info("location.getId() == "+location.getId());
		mapService.saveLocation(location);
		return mylocation();
	}
	public String mylocation(){
		User user = (User)request.getSession().getAttribute("user");
		if(user!=null){
			List<Location> l = mapService.findLocationsByUid(user.getUid());
			request.setAttribute("locations", l);
		}
		return "mylocation";
	}
	public String listCity(){
		Map<String,List<City>> provinceMap = cityService.getAllCitiesOrderByProvinceId();
		Map<String,List<City>> pinyinMap = cityService.getAllCitiesOrderByPinyin();
		request.setAttribute("provinceMap", provinceMap);
		request.setAttribute("pinyinMap", pinyinMap);
		return "citylist";
	}
}
