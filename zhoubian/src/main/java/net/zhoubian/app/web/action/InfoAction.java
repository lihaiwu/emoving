package net.zhoubian.app.web.action;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.zhoubian.app.model.Information;
import net.zhoubian.app.model.Tag;
import net.zhoubian.app.service.InfoService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoAction extends AbstractAction{
	private static Log logger = LogFactory.getLog(InfoAction.class);
	private InfoService infoService;
	private Information info;
	
	public Information getInfo() {
		return info;
	}

	public void setInfo(Information info) {
		this.info = info;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public String add(){
		return "addinfo";
	}
	
	public String save(){
		Date d = Calendar.getInstance().getTime();
		info.setCreateTime(d);
		info.setUpdateTime(d);
		info.setState(info.STATUS_VALID);
		info.setIndexStatus(info.INDEXED);
		infoService.saveOrUpdate(info);
		request.setAttribute("readonly", "true");
		return "addinfo";
		//return "infodetail";
	}
	
	public String detail(){
		String infoId = request.getParameter("infoId");
		if(infoId == null){
			outError("信息不存在！",response);
			return null;
		}else{
			info = infoService.getInfomationById(Long.parseLong(infoId));
			if(info == null){
				outError("信息不存在！",response);
				return null;
			}
			return "addinfo";
		}
	}
	
	public String getTagDiv(){
		String categoryId = request.getParameter("categoryId");
		List<Tag> tags = infoService.getTagsByCategoryId(categoryId);
		for(int i=0;i<tags.size();i++){
			Tag tag = tags.get(i);
			if(tag.getTagSpec()!=null && !tag.getTagSpec().trim().equals("")){
				Map items = new LinkedHashMap<String,String>();
				JSONObject jo = JSONObject.fromObject(tag.getTagSpec());
				Iterator iterator = jo.keys();
				while(iterator.hasNext()){
					String key = (String) iterator.next();
					items.put(key, jo.get(key));
				}
				tag.setItems(items);
			}
		}
		request.setAttribute("tagList", tags);
		String infoId = request.getParameter("infoId");
		if(infoId!=null){
			info = infoService.getInfomationById(Long.parseLong(infoId));
			List<Short> tagValues = new ArrayList<Short>();
			for(int i=0;i<tags.size();i++){
				Tag tag = tags.get(i);
				try {
					Short value = (Short)info.getClass().getDeclaredMethod("get"+tag.getFieldName().substring(0,1).toUpperCase()+tag.getFieldName().substring(1)).invoke(info);
					tagValues.add(value);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.error(e.toString());
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					logger.error(e.toString());
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					logger.error(e.toString());
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			request.setAttribute("tagValues", tagValues);
		}
		return "tagdiv";
	}
	
	public String selectLocation(){
		return "selectLocation";
	}
}
