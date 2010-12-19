package net.zhoubian.app.service.impl;

import java.util.List;

import net.zhoubian.app.dao.InformationDao;
import net.zhoubian.app.model.Information;
import net.zhoubian.app.model.Tag;
import net.zhoubian.app.service.InfoService;

public class InfoServiceImpl implements InfoService{
	private InformationDao infoDao;

	public void setInfoDao(InformationDao infoDao) {
		this.infoDao = infoDao;
	}
	
	/**
	 * 保存或者更新信息
	 * @param information
	 */
	public void saveOrUpdate(Information information){
		infoDao.saveOrUpdate(information);
	}
	/**
	 * 根据细分小类ID查找对应的标签
	 * @param categoryId
	 * @return
	 */
	public List<Tag> getTagsByCategoryId(String categoryId){
		return infoDao.getTagsByCategoryId(categoryId);
	}
	
}
