package com.company.datalayer;

import com.company.datalayer.model.DataTable;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import junit.framework.TestCase;

public class DataAccessLayerTest extends TestCase {

     /*
      these test use are based on the Country table from the test SQL scripts
      */

    private final SQLServerDataSource sqlServerDataSource;

    public DataAccessLayerTest(){
        sqlServerDataSource = Credentials.getSQLServerDataSource();
    }

    public void testGetDataFromSQLStatement() {
        DataAccessLayer dataAccessLayer = new DataAccessLayer(sqlServerDataSource);
        DataTable dataTable = dataAccessLayer.getDataFromSQLStatement("SELECT top 5 * FROM Country");
        assertTrue(dataTable.isPopulated());
        assertEquals(5,dataTable.getRowCount());
    }

    public void testExecuteSQLStatement() {
        DataAccessLayer dataAccessLayer = new DataAccessLayer(sqlServerDataSource);
        DataTable dataTable = dataAccessLayer.getDataFromSQLStatement("SELECT * FROM Country");
        assertTrue(dataTable.isPopulated());
        long count = dataTable.getRowCount();
        long expected = count + 1;
        dataAccessLayer.executeSQLStatement("insert into Country (country_name,country_region) VALUES ('country_test','region_test')");
        dataTable = dataAccessLayer.getDataFromSQLStatement("SELECT * FROM Country");
        assertEquals(expected,dataTable.getRowCount());
    }
}