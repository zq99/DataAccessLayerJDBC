package com.company.datalayer;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class Credentials {

    /*******************************************************************
     *  You must enter the details of your server or database name here
     *******************************************************************/

    final private static String server = "{ADD SERVER NAME HERE}";
    final private static String databaseName = "{ADD DATABASE NAME HERE}";

    public static SQLServerDataSource getSQLServerDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setIntegratedSecurity(true);
        ds.setServerName(server);
        ds.setDatabaseName(databaseName);
        return ds;
    }
}
