package net.zhoubian.app.util;

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

}
