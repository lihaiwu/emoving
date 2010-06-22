package net.zhoubian.app.search;

import java.util.ArrayList;
import java.util.List;

import net.zhoubian.app.dao.InformationDao;
import net.zhoubian.app.model.Information;
/**
 * 对“信息”进行索引任务
 * @author zhangsq3  2010-6-18上午10:57:42
 *
 */
public class InformationIndexer extends Parser implements Indexer<Information> {

	private Information item;
	private List<Information> items = new ArrayList<Information>();
	private InformationDao informationDao;
	
	public void index() {
		// TODO Auto-generated method stub
		
		this.item.setIndexStatus(Information.INDEXED);
		this.items.add(this.item);
	}

	public void putItem(Information item) {
		this.item = item;
	}

	public void saveChanges() {
		this.informationDao.saveAll(items);
	}

	public void setInformationDao(InformationDao informationDao) {
		this.informationDao = informationDao;
	}
	public InformationDao getInformationDao() {
		return informationDao;
	}
}
