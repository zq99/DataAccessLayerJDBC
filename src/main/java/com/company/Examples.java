package com.company;

import com.company.common.Iterator;
import com.company.datalayer.Credentials;
import com.company.datalayer.DataAccessLayer;
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
        DataTable rs = dataAccessLayer.getDataFromSQLStatement("SELECT * FROM Country");
        for(Iterator<?> iterator = rs.getIterator(); iterator.hasNext();){
            String row = iterator.next().toString();
            System.out.println(row);
        }
    }

}
