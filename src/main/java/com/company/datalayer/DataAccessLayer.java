package com.company.datalayer;

import com.company.datalayer.model.DataCell;
import com.company.datalayer.model.DataRow;
import com.company.datalayer.model.DataTable;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.jetbrains.annotations.NotNull;

import java.sql.*;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "unused"})
public class DataAccessLayer {

    private SQLServerDataSource sqlServerDataSource = null;
    private String connectionUrl;
    private final CONNECT_METHOD connect_method;
    private enum CONNECT_METHOD{
        SQL_DATA_SOURCE,
        URL
    }

    public DataAccessLayer(@NotNull SQLServerDataSource source) {
        this.sqlServerDataSource = source;
        this.connect_method = CONNECT_METHOD.SQL_DATA_SOURCE;
    }

    public DataAccessLayer(@NotNull String connectionUrl){
        this.connectionUrl = connectionUrl;
        this.connect_method = CONNECT_METHOD.URL;
    }

    private Connection getConnectionFromDataSource() {
        try {
            if(this.connect_method == CONNECT_METHOD.SQL_DATA_SOURCE) {
                return this.sqlServerDataSource.getConnection();
            }else if(this.connect_method == CONNECT_METHOD.URL){
                return DriverManager.getConnection(connectionUrl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DataTable getDataFromSQLStatement(String sql) {
        DataTable dataTable = new DataTable();
        try (Connection con = getConnectionFromDataSource()) {
            assert con != null;
            try (Statement stmt = con.createStatement()) {
                ResultSet rs = stmt.executeQuery(sql);
                dataTable = convertResultSetToDataTable(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataTable;
    }

    public boolean executeSQLStatement(String sql){
        boolean result = false;
        try(Connection con = getConnectionFromDataSource()) {
            assert con != null;
            try(Statement stmt = con.createStatement()){
                result = stmt.execute(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static void printResultSet(ResultSet rs) throws SQLException {
        printResultSet(rs, true);
    }

    public static void printResultSet(ResultSet rs, boolean includeHeaders) throws SQLException {
        if (rs != null) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    if (includeHeaders) {
                        System.out.print(columnValue + " [" + metaData.getColumnName(i) + "]");
                    } else {
                        System.out.print(columnValue);
                    }
                }
                System.out.println();
            }
        } else {
            System.out.println("result set is empty");
        }
    }

    public static DataTable convertResultSetToDataTable(ResultSet rs) throws SQLException {
        DataTable dataTable = new DataTable();
        if (rs != null) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                DataRow dataRow = new DataRow();
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i);
                    DataCell dataCell = new DataCell(metaData.getColumnName(i),columnValue);
                    dataRow.add(dataCell);
                }
                if (dataRow.isPopulated()) {
                    dataTable.addRow(dataRow);
                }
            }
        }
        return dataTable;
    }

    //TODO
    //add execute stored procedure
    //retrieve data from stored procedure
    //pass return values back and forth between layer and database
}
