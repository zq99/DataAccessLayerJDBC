# Java Data Access Layer (DAL) for SQL Server

This project is a an experiment to create a Data Access Layer in Java that interacts with SQL Server using JDBC.

The project has its own object model to retrieve data, so it converts to the ResultSet object to a custom DataTable object.

This DataTable object can then be used in applications, without having to persist a connection with the database or use the data immediately whilst a connection is open.

## Setup

You maye have to download the SQL Server driver for JDBC from here: https://docs.microsoft.com/en-us/sql/connect/jdbc/download-microsoft-jdbc-driver-for-sql-server?view=sql-server-ver15


