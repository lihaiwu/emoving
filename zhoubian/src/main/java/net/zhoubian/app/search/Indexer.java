package net.zhoubian.app.search;

public interface Indexer<T> {

	/**
	 * 设置索引的对象信息
	 * @param item
	 */
	public void putItem(T item);
	
	/**
	 * 执行索引任务
	 */
	public void index();
	
	/**
	 * 修改已经索引过的实体的状态
	 */
	public void saveChanges();
}
