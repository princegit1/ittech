package com.itgd.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.itgd.conn.Dbconnection;
import com.itgd.dto.*;

public class Archive {
	static int tot = 0;
	
	public static int  count()
	{
		
		Connection countArchive = null;
		Statement stmtCount = null;
		ResultSet rsCount;
		try
		{
			Dbconnection ConnectCount = null;
			String sql = "SELECT count(id) as totcount from jos_magazine_categories where published=1 AND id!=(SELECT catid_default from jos_magazine_sections) order by ordering DESC";
			ConnectCount = Dbconnection.getInstance();
			countArchive  = ConnectCount.getConnection();
			stmtCount = countArchive.createStatement();
			rsCount = stmtCount.executeQuery(sql);
			System.out.println(sql);
			if (rsCount.next())
			{
				tot = rsCount.getInt("totcount");
			}
			rsCount.close();
			stmtCount.close();
			countArchive.close();
		}
		catch (Exception e){
			
			System.out.println("Exception is: " + e);
		}
	return tot;

	}
	
	public static ArrayList ArchiveList(int pagenum)
	{
		
		Connection archiveConn = null;
		Statement stmtArch = null;
		ResultSet rsArch;
		ArchiveBean issues;
		ArrayList ArchivesData = new ArrayList();
		
		
		int limits = 0, limite = 6;
		limits = pagenum * 6;
		int totContent = count();
		int numofPages = totContent / 6;

		if(totContent > 0){
		if(totContent % 6 > 0){
		numofPages += 1;
		}}
		System.out.println(numofPages);
		int StartLimit = limits;
		int EndLimit = limite;
		
		try
		{
			Dbconnection connectArch = null;
			String sql = "SELECT cover_image,id,title  from jos_magazine_categories where published=1 AND id!=(SELECT catid_default from jos_magazine_sections) order by ordering desc   limit "+StartLimit+","+EndLimit;
			connectArch = Dbconnection.getInstance();
			archiveConn  = connectArch.getConnection();
			stmtArch = archiveConn.createStatement();
			rsArch = stmtArch.executeQuery(sql);
			System.out.println(sql);
			while (rsArch.next()){
				issues = new ArchiveBean();
				issues.setCoverImage(rsArch.getString("cover_image"));
				issues.setIssueDate(rsArch.getString("title"));
				issues.setIssueId(rsArch.getInt("id"));
				issues.setIssueCount(numofPages);
				ArchivesData.add(issues);
			}
			rsArch.close();
			stmtArch.close();
			archiveConn.close();
		}
		catch (Exception e){
			
			System.out.println("Exception is: " + e);
		}
	
 return ArchivesData;
	}
	
}
