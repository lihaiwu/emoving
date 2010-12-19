package net.zhoubian.app.web.action;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.zhoubian.app.model.Tag;
import net.zhoubian.app.service.InfoService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InfoAction extends AbstractAction{
	private static Log logger = LogFactory.getLog(InfoAction.class);
	private InfoService infoService;
	
	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}

	public String add(){
		return "addinfo";
	}
	
	public String save(){
		
		return "infodetail";
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
		return "tagdiv";
	}
	
	public String selectLocation(){
		return "selectLocation";
	}
}
