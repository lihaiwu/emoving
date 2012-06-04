package net.zhoubian.app.service;

import java.util.List;

import net.zhoubian.app.model.Information;
import net.zhoubian.app.model.Tag;

public interface InfoService {
	/**
	 * 保存或者更新信息
	 * @param information
	 */
	public void saveOrUpdate(Information information);
	
	/**
	 * 根据细分小类ID查找对应的标签
	 * @param categoryId
	 * @return
	 */
	public List<Tag> getTagsByCategoryId(String categoryId);
	
	/**
	 * 根据ID查找信息
	 * @param id
	 * @return
	 */
	public Information getInfomationById(Long id);
}
