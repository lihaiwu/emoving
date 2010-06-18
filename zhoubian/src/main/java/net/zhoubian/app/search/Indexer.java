package net.zhoubian.app.search;

public interface Indexer<T> {

	public void index(T item);
}
