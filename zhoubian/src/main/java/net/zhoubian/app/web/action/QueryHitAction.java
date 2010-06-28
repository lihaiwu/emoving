package net.zhoubian.app.web.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import org.apache.log4j.Logger;


public class QueryHitAction extends AbstractAction{

	private static final long serialVersionUID = -6501531589948073715L;
	private static Logger logger = Logger.getLogger(QueryHitAction.class);
	
	public String getSuggestKeyword(){
//		FIXME search by lucene
		String prefix = request.getParameter("q"); 
		logger.error("====input keyword:" + prefix);
		 
		List<String> result = new ArrayList<String>();
		result.add("god");
		result.add("good");
		result.add("good morning");
		result.add("good afternoon");
		result.add("golden");
		JSONArray json = JSONArray.fromObject(result);
		this.outJson(json);
		return null;
	}
}
