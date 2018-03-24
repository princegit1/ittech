package com.itgd.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.YearsAgoDTO;

public class YearsAgoHelper {
	public static ArrayList yearsAgoContent() throws Exception {
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		YearsAgoDTO yaDTO = null;
		ArrayList dataList = new ArrayList();
		try {

			Dbconnection connect = null;
			String sql = "SELECT _30yearsinit_name,_30yearsinit_title, _30yearsinit_caption FROM jos_30yearsinit where enable = 1 order by ordering desc";
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			//System.out.println(sql);
			while (rs.next())
			{
				yaDTO = new YearsAgoDTO();
				yaDTO.setImage(rs.getString("_30yearsinit_name")==null?"":rs.getString("_30yearsinit_name"));
				yaDTO.setTitle(rs.getString("_30yearsinit_title")==null?"":rs.getString("_30yearsinit_title"));
				yaDTO.setCaption(rs.getString("_30yearsinit_caption")==null?"":rs.getString("_30yearsinit_caption"));
				dataList.add(yaDTO);
			}
		} catch (Exception e) {
			System.out.println("yearsAgoContent - YearsAgoHelper Exception -> " + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(connection!=null)
				connection.close();
		}
		return dataList;
	}
}
