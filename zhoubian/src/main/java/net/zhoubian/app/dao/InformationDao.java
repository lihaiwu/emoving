package net.zhoubian.app.dao;

import java.util.List;

import net.zhoubian.app.model.Information;
import net.zhoubian.app.model.Tag;

public abstract class InformationDao extends AbstractDao<Information, Long> {

	public void saveAll(List<Information> entities){
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}
	/**
	 * 根据细分小类返回该细分小类对应的标签
	 * @param categoryId
	 * @return
	 */
	public List<Tag> getTagsByCategoryId(String categoryId){
		return this.getHibernateTemplate().find("from Tag tag where tag.categoryId=?",categoryId);
	}
}
