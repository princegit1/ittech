package com.itgd.education.utils;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class DbConnection
{
    private static DbConnection instance;
    Connection conn;

    private DbConnection(String website)
    {
        init(website);
    }

    public static synchronized DbConnection getInstance(String website)
    {
        if(instance == null)
            instance = new DbConnection(website);
        return instance;
    }

    private void init(String website)
    {
        ClassLoader classloader = (DbConnection.class).getClassLoader();
        URL url = classloader.getResource("db.properties");
        Properties properties = new Properties();
        try
        {
            java.io.InputStream inputstream = url.openStream();
            properties.load(inputstream);
            Class.forName(properties.getProperty("drivers"));
            conn = DriverManager.getConnection(properties.getProperty(website+".url"), properties.getProperty(website+".user"), properties.getProperty(website+".password"));
        }
        catch(IOException ioexception)
        {
            System.out.println(ioexception.toString());
        }
        catch(SQLException sqlexception)
        {
            System.out.println(sqlexception.toString());
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            System.out.println(classnotfoundexception.toString());
        }
    }

    public Connection getConnection(String website)
    {
		try
        {
			init(website);
        }
        catch(Exception sqlexception)
        {
			System.out.println(sqlexception.toString());
        }
        return conn;
    }
}