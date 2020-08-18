# Java Data Access Layer for SQL Server

This project is a basic example of how to create a Data Access Layer in Java that interacts with SQL Server using JDBC.

The project has its own object model (DataTable) to retrieve data from SQL Server.

This DataTable object can then be used in applications without having to persist a connection with the database.


## Requirements

You may have to download the SQL Server driver for JDBC from here: 
- https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15


## Connecting

You need to specify the database name and server in the class Credentials:

    final private static String server = "{ADD SERVER NAME HERE}";
    final private static String databaseName = "{ADD DATABASE NAME HERE}";

The layer object can be instantiated with one of two types of constructors.

The first constructor that can be used is one that accepts a SQLServerDataSource object from the JDBC library as a parameter.

    SQLServerDataSource sqlServerDataSource = Credentials.getSQLServerDataSource();
    DataAccessLayer dataAccessLayer = new DataAccessLayer(sqlServerDataSource);

The second type of constructor that can be used is a URL representing the connection string to the database.

    String connString = "jdbc:sqlserver://;servername=MY_SERVER_NAME;databaseName=MY_DATABASE_NAME;integratedSecurity=true";
    DataAccessLayer dataAccessLayer = new DataAccessLayer(connString);

The layer maintains responsibility for open and closing connections to the database.


## Data Object Model

The layer has it's own object model to describe the data it retrieves from SQL Server. The layer converts a ResultSet data object from JDBC to a custom DataTable object.

The DataTable object gives the data structure, and is easier to use than simply converting JDBC's ResultSet object into a List.

The hierarchy is as follows (from highest to lowest level):

DataTable -> DataRow -> DataCell


## Example

Here is a simple example of extracting data based upon a raw SQL query that is sent to the layer:

        SQLServerDataSource sqlServerDataSource = Credentials.getSQLServerDataSource();
        DataAccessLayer dataAccessLayer = new DataAccessLayer(sqlServerDataSource);
        DataTable dataTable = dataAccessLayer.getDataFromSQLStatement("SELECT * FROM Country");
        for(DataRow dataRow : dataTable.getDataRows()){
            System.out.println(dataRow.getValue("country_name"));
            System.out.println(dataRow.getValue("country_region"));
        }

## Testing

The project includes unit tests for the DataTable object model hierarchy as well as the Data Access Layer.

The tests for the Data Access Layer are reliant on the SQL Server objects created in this file: 

- sql_create_test_objects.sql

## Further Info

- JDBC Overview: https://docs.microsoft.com/en-us/sql/connect/jdbc/microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15
