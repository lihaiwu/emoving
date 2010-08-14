package net.zhoubian.app.config;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

/**
 * Test data source configuration.
 * 
 * @author upon
 */
public class DataSourceConfigTest extends SpringEnv {

    public void testLookupDataSource() {
        Object dataSource = getBean("dataSource");
        assertTrue(dataSource instanceof DataSource);
    }

    public void testGetAndReleaseConnection() throws SQLException {
        DataSource ds = (DataSource)getBean("dataSource");
        Connection conn = ds.getConnection();
        conn.close();
    }
}
