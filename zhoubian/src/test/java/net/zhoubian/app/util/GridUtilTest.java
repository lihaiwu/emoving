package net.zhoubian.app.util;

import java.util.List;

import junit.framework.TestCase;

public class GridUtilTest extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		GridUtil gridUtil = new GridUtil();
	}

	public void testGetOwnGridCode() {
		long code = GridUtil.getOwnGridCode(39.000000, 116.000000);
		System.out.println(code);
	}
	
	public void testGetRelatedGridCode(){
		List<Long> codes = GridUtil.getRelatedGridCode(116.000000, 39.000000, 4);
		for(int i=0;i<codes.size();i++){
			System.out.println(codes.get(i));
		}
	}
	
	public void testGetGrid(){
		LngLat[] lngLats = GridUtil.getGrid(39107927);
		System.out.println(lngLats[0].getLngX());
		System.out.println(lngLats[0].getLatY());
		System.out.println(lngLats[1].getLngX());
		System.out.println(lngLats[1].getLatY());
	}

}
