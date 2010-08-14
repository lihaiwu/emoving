package net.zhoubian.app.util;

import junit.framework.TestCase;

public class ParameterUtilsTest extends TestCase {
	public void testGetBaseUrl(){
		assertEquals("http://localhost:8080/zhoubian", ParameterUtils.getBaseUrl());
	}

}
