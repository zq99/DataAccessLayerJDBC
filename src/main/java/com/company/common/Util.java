package com.company.common;

import java.net.URL;
import java.net.URLClassLoader;

@SuppressWarnings({"UnusedReturnValue", "SameParameterValue", "unused"})
public class Util {

    /*
    These are just methods you can use to help diagnose connection uses
    when trying to connect to SQL Server from java
     */

    public static void loadDriver() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void printClassPath() {
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        URL[] urls = ((URLClassLoader) cl).getURLs();
        for (URL url : urls) {
            System.out.println(url.getFile());
        }
    }
}