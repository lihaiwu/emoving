package net.zhoubian.app.search;

import java.util.List;

public interface Searcher<T> {

	public List<T> search(String keyword);
}
