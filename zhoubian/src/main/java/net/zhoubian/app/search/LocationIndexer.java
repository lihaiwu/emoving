package net.zhoubian.app.search;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.store.LockObtainFailedException;

import net.zhoubian.app.dao.LocationDao;
import net.zhoubian.app.model.Location;
import net.zhoubian.app.util.SystemProperties;

public class LocationIndexer extends Parser implements Indexer<Location>{
	private Location item;
	private List<Location> items;
	private LocationDao loactionDao;
	
	@SuppressWarnings("deprecation")
	public void index() {
		try {
			String path = SystemProperties.getProperty("location_index_path");
			if(path == null)
				throw new RuntimeException("no index path is found");
			IndexWriter writer = new IndexWriter("", this.getAnalyzer());
			Document doc = new Document();
			Field locationName = new Field("locationName", item.getLocationName(), Field.Store.YES,Field.Index.TOKENIZED);
			Field longitude = new Field("longitude", String.valueOf(item.getLongitude()), Field.Store.YES,Field.Index.NO);
			Field latitude = new Field("latitude", String.valueOf(item.getLatitude()), Field.Store.YES,Field.Index.NO);
			doc.add(locationName);
			doc.add(longitude);
			doc.add(latitude);
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
		this.item.setIndexStatus(Location.INDEXED);
		this.items.add(item);
	}

	public void putItem(Location item) {
		this.item = item;
	}

	public void saveChanges() {
		this.loactionDao.saveAll(items);
	}

	public void setLoactionDao(LocationDao loactionDao) {
		this.loactionDao = loactionDao;
	}
}
