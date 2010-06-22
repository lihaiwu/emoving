package net.zhoubian.app.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;

import net.zhoubian.app.dao.KeywordDao;
import net.zhoubian.app.model.Keywords;
import net.zhoubian.app.model.SystemParameters;
import net.zhoubian.app.util.SystemProperties;
/**
 * 对搜索关键字进行索引任务
 * @author zhangsq3  2010-6-18上午10:56:55
 *
 */
public class KeywordIndexer extends Parser implements Indexer<Keywords> {

	private Keywords item;
	private List<Keywords> items = new ArrayList<Keywords>();
	private KeywordDao keywordDao;
	
	@SuppressWarnings("deprecation")
	public void index() {
		try {
			String path = SystemProperties.getProperty("keyword_index_path");
			if(path == null)
				throw new RuntimeException("no index path is found");
			IndexWriter writer = new IndexWriter("", this.getAnalyzer());
			Document doc = new Document();
			Field keyword = new Field("keyword", item.getKeyword(), Field.Store.YES,Field.Index.TOKENIZED);
			Field pinyin = new Field("keyword", item.getPinyin(), Field.Store.YES,Field.Index.TOKENIZED);
			Field pinyinAbstract = new Field("pinyinAbstract", item.getPinyinAbstract(), Field.Store.YES,Field.Index.TOKENIZED);
			doc.add(keyword);
			doc.add(pinyin);
			doc.add(pinyinAbstract);
			writer.addDocument(doc);
			writer.optimize();
			writer.close();
		} catch (CorruptIndexException e) {
			e.printStackTrace();
		} catch (LockObtainFailedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.item.setIndexStatus(Keywords.INDEXED);
		this.items.add(this.item);
	}

	public void putItem(Keywords item) {
		this.item = item;
	}

	public void saveChanges() {
		this.keywordDao.saveAll(this.items);
	}
	
	public void setKeywordDao(KeywordDao keywordDao) {
		this.keywordDao = keywordDao;
	}
	public KeywordDao getKeywordDao() {
		return keywordDao;
	}

}
