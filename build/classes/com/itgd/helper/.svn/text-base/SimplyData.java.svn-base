package com.itgd.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.*;

public class SimplyData {
	static int tot = 0;
	
	public static  ArrayList Issues(int catid,int pagenum)
	{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs;
		Simply issue;
		ArrayList dataList = new ArrayList();
		
		
		int limits = 0, limite = 7;
		limits = pagenum * 7;
		int totContent = count(catid);
		int numofPages = totContent / 7;

		if(totContent > 0){
		if(totContent % 7 > 0){
		numofPages += 1;
		}}
		System.out.println(numofPages);
		int StartLimit = limits;
		int EndLimit = limite;
		
		try {

			Dbconnection connect = null;
			String sql = "SELECT id FROM jos_magazine_categories where published=1 and (supplements_subsection ="+catid+" or also_thisweek like '%"+catid+"%') order by id desc limit "+StartLimit+","+EndLimit;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println(sql);
			while (rs.next()) {
				issue = new Simply();
				issue.setIssueId(rs.getInt("id"));
				issue.setNoofpages(numofPages);
				
				dataList.add(issue);
				
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Exception is Simply -- Issue ;" + e);
		}
		return dataList;
	}
	
	public static  ArrayList Issues(int catid,int pagenum, int countDisplay)
	{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs;
		Simply issue;
		ArrayList dataList = new ArrayList();
		
		
		int limits = 0, limite = countDisplay;
		limits = pagenum * countDisplay;
		int totContent = count(catid);
		int numofPages = totContent / countDisplay;

		if(totContent > 0){
		if(totContent % countDisplay > 0){
		numofPages += 1;
		}}
		//System.out.println(numofPages);
		int StartLimit = limits;
		int EndLimit = limite;
		
		try {

			Dbconnection connect = null;
			String sql = "SELECT id FROM jos_magazine_categories where published=1 and (supplements_subsection ="+catid+" or also_thisweek like '%"+catid+"%') order by id desc limit "+StartLimit+","+EndLimit;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			//System.out.println(sql);
			while (rs.next()) {
				issue = new Simply();
				issue.setIssueId(rs.getInt("id"));
				issue.setNoofpages(numofPages);
				
				dataList.add(issue);
				
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Exception is Simply -- Issue ;" + e);
		}
		return dataList;
	}
	public static ArrayList simple(int secId,int catId,int issueId)
	
	{
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs;
		Simply data;
		ArrayList dataList = new ArrayList();
		try {

			Dbconnection connect = null;
			String sql = "SELECT c.sef_url as sefURL,c.id,f.title as issueDate,j.name as supplementName,j.name as supplementName,j.image as supplimentTop,c.title as Headline,c.introtext as kicker,j.image,f.suppliment_image_url FROM jos_content c,jos_magazine_articles m,jos_magazine_categories f,jos_categories j where c.sectionid="+secId+" and c.catid="+catId+" and c.id=m.article_id and f.id=m.catid  and c.state=1 and j.id = c.catid and f.id="+issueId+" order by c.id desc";
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			//System.out.println(sql);
			while (rs.next()) {
				data = new Simply();
				data.setCoverImage(rs.getString("suppliment_image_url"));
				data.setHeadline(rs.getString("Headline"));
				data.setImage(rs.getString("image"));
				data.setIssueDate(rs.getString("issueDate"));
				data.setKicker(rs.getString("kicker"));
				data.setStoryId(rs.getInt("id"));
				data.setSefURL(rs.getString("sefURL"));
				
				dataList.add(data);
				
			}
			rs.close();
			stmt.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("Exception in Simply -- Issue ;" + e);
		}
		return dataList;
	}
	public static int count(int catid)
	{
		
		Connection countSimply = null;
		Statement stmtCount = null;
		ResultSet rsCount;
		try
		{
			Dbconnection ConnectCount = null;
			String sql = "SELECT count(id) as totcount FROM jos_magazine_categories where published=1 and (supplements_subsection ="+catid+" or also_thisweek like '%"+catid+"%') order by id desc";
			ConnectCount = Dbconnection.getInstance();
			countSimply  = ConnectCount.getConnection();
			stmtCount = countSimply.createStatement();
			rsCount = stmtCount.executeQuery(sql);
			//System.out.println(sql);
			if (rsCount.next())
			{
				tot = rsCount.getInt("totcount");
			}
			rsCount.close();
			stmtCount.close();
			countSimply.close();
		}
		catch (Exception e){
			
			System.out.println("Exception is: " + e);
		}
	return tot;

	}
	public static ArrayList meta(int id)
	{
		Connection con = null;
		Statement stmtCount = null;
		ResultSet rsCount;
		ArrayList top = new ArrayList();
		Simply datat;
		try
		{
			Dbconnection ConnectCount = null;
			String sql = "SELECT name,image,metatitle,metadesc,metakey FROM jos_categories where id="+id+"";
			ConnectCount = Dbconnection.getInstance();
			con  = ConnectCount.getConnection();
			stmtCount = con.createStatement();
			rsCount = stmtCount.executeQuery(sql);
			//System.out.println(sql);
			if (rsCount.next())
			{
				datat = new Simply();
				datat.setMetaDesc(rsCount.getString("metadesc"));
				datat.setMetaKey(rsCount.getString("metakey"));
				datat.setMetaTitle(rsCount.getString("metatitle"));
				datat.setSupplementName(rsCount.getString("name"));
				datat.setSupplementImage(rsCount.getString("image"));
				
				top.add(datat);
				
			}
			rsCount.close();
			stmtCount.close();
			con.close();
		}
		catch (Exception e){
			
			System.out.println("Exception is: " + e);
		}
	return top;
	}

}
