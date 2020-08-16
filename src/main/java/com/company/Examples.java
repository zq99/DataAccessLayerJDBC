package com.company;

import com.company.common.Iterator;
import com.company.datalayer.Credentials;
import com.company.datalayer.DataAccessLayer;
import com.company.datalayer.model.DataRow;
import com.company.datalayer.model.DataTable;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Examples {

    /*
    This class for is just to show different ways to use the data access layer.
    For testing please refer to the unit tests /test/java
     */

    public static void getCountryList()  {
        SQLServerDataSource sqlServerDataSource = Credentials.getSQLServerDataSource();
        DataAccessLayer dataAccessLayer = new DataAccessLayer(sqlServerDataSource);
        DataTable dataTable = dataAccessLayer.getDataFromSQLStatement("SELECT * FROM Country");
        for(DataRow dataRow : dataTable.getDataRows()){
            System.out.println(dataRow.getValue("country_name"));
            System.out.println(dataRow.getValue("country_region"));
        }
    }

}
