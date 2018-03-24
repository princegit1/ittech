package com.itgd.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.itgd.conn.Dbconnection;
import com.itgd.dto.*;
public class Search {
	
	public static ArrayList subsecIds = new ArrayList();
	public static ArrayList subsecNames = new ArrayList(); 
	public static int subsection(int secid)
	
	{
		
		int counter = 0;
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs;
		SearchBean sec;
		try {
			subsecIds.clear();
			subsecNames.clear();
			Dbconnection connect = null;
			String sql = "SELECT id,title FROM jos_categories where section='"+secid+"'";
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.print(sql);
			while (rs.next()) {
				subsecIds.add(new Integer(rs.getInt("id")));
				subsecNames.add(new String(rs.getString("title")));
				counter++;
				

			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Exception is ;;" + e);
		}
		
		return counter;
		
	}

	public static ArrayList getSubsecIds() {
		return subsecIds;
	}
	public static void setSubsecIds(ArrayList subsecIds) {
		subsecIds = subsecIds;
	}
	public static ArrayList getSubsecNames() {
		return subsecNames;
	}
	public static void setSubsecName(ArrayList subsecNames) {
		subsecNames = subsecNames;
	}

}
