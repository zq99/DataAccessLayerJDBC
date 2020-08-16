# Java Data Access Layer for SQL Server

This project is a an experiment to create a Data Access Layer in Java that interacts with SQL Server using JDBC.

The project has its own object model to retrieve data, so it converts to the ResultSet object to a custom DataTable object.

This DataTable object can then be used in applications, without having to persist a connection with the database or use the data immediately whilst a connection is open.


## Requirements

You may have to download the SQL Server driver for JDBC from here: 
- https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15


## Connection

You need to specify the database name and server in the class credentials:

    final private static String server = "{ADD SERVER NAME HERE}";
    final private static String databaseName = "{ADD DATABASE NAME HERE}";

The layer object can be instantiated with one of two types of constructurs.

The first constructor that can be used is a SQLServerDataSource object from the JDBC library.

The second type of constructor that can be used is URL representing the connection string to the database.

The layer maintains responsibility for open and closing connections to the database.


## Custom Object Model

The layer has it's own object model to model data it retrives from SQL Server

The hierarchy is as follows (from highest to lowest level):

DataTable -> DataRow -> DataCell


## Example

Here is a simple example of extracting data based upon a raw SQL query that is sent ot the layer:


        SQLServerDataSource sqlServerDataSource = Credentials.getSQLServerDataSource();
        DataAccessLayer dataAccessLayer = new DataAccessLayer(sqlServerDataSource);
        DataTable dataTable = dataAccessLayer.getDataFromSQLStatement("SELECT * FROM Country");
        for(Iterator<?> iterator = dataTable.getIterator(); iterator.hasNext();){
            String row = iterator.next().toString();
            System.out.println(row);
        }


