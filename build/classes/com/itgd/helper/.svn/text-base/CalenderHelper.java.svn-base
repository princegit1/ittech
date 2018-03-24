package com.itgd.helper;

import java.util.Calendar;
import java.util.HashMap;
import java.util.ArrayList;
import com.itgd.conn.Dbconnection;
import com.itgd.dto.SearchBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class CalenderHelper
{
  private int month;
  private int year;
  private int days[][];
  private int numberOfWeeks;
  private static HashMap months = new HashMap();

  private CalenderHelper()
  {
  }

  private CalenderHelper(int month, int year)
  {
    days = new int[6][7];
    numberOfWeeks = 0;
    this.month = month;
    this.year = year;
    buildWeeks();
  }

  public int getMonth()
  {
    return month;
  }

  public static CalenderHelper getMonth(int month, int year)
  {
    String key = String.valueOf((new StringBuffer(String.valueOf(month))).append("/").append(year));
    if (months.containsKey(key))
    {
      return (CalenderHelper) months.get(key);
    }
    else
    {
      CalenderHelper newMonth = new CalenderHelper(month, year);
      months.put(key, newMonth);
      return newMonth;
    }
  }

  private void buildWeeks()
  {
    Calendar c = Calendar.getInstance();
    c.setFirstDayOfWeek(1);
    c.set(year, month, 1);
    for (; c.get(2) == month; c.add(5, 1))
    {
      int weekNumber = c.get(4) - 1;
      int dayOfWeek = calculateDay(c.get(7));
      days[weekNumber][dayOfWeek] = c.get(5);
      numberOfWeeks = weekNumber;
    }
  }

  public int calculateDay(int day)
  {
    if (day == 1)
      return 0;
    if (day == 2)
      return 1;
    if (day == 3)
      return 2;
    if (day == 4)
      return 3;
    if (day == 5)
      return 4;
    if (day == 6)
      return 5;
    return day != 7 ? 7 : 6;
  }

  public int[][] getDays()
  {
    return days;
  }

  public int getNumberOfWeeks()
  {
    return numberOfWeeks + 1;
  }

  public int getYear()
  {
    return year;
  }
  
  public static ArrayList getCalenderData(String date, String issueType,int issueId,String contentType){
	  	ArrayList al = new ArrayList();
	  	Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		SearchBean sbean=null;
		String sql ="";
		String year="";
		year=Integer.toString(Calendar.getInstance().get(Calendar.YEAR));
		
		if(issueType.equals("magazine")){
			if(issueId==0){
				if(date.substring(0,date.indexOf("-")).equals(year)){
				sql="select  m.catid ,d.title as magtitle,c.sef_url,c.id,c.title,c.introtext, c.created,  ars.section_id, ars.cat_id from jos_content c, jos_sections s, jos_magazine_articles m , jos_article_section ars ,jos_magazine_categories d where ars.section_id=s.id and s.published=1 and c.id=m.article_id and ars.article_id=m.article_id and c.id=ars.article_id and m.catid=(SELECT max(A.ID) FROM  jos_magazine_categories A  where A.TITLE like '%"+year+"' and A.published=1 ORDER BY A.id ) and m.catid= d.id AND c.state=1  and s.issue_free=0 Group By ars.article_id order by c.created asc";
				}else{
				sql="select  m.catid ,d.title as magtitle,c.sef_url,c.id,c.title,c.introtext, c.created,  ars.section_id, ars.cat_id from jos_content c, jos_sections s, jos_magazine_articles m , jos_article_section ars ,jos_magazine_categories d where ars.section_id=s.id and s.published=1 and c.id=m.article_id and ars.article_id=m.article_id and c.id=ars.article_id and m.catid=(SELECT A.ID FROM  jos_magazine_categories A  where A.TITLE like '%"+date.substring(0,date.indexOf("-"))+"' and A.published=1 ORDER BY A.id  limit 1 ) and m.catid= d.id AND c.state=1  and s.issue_free=0 Group By ars.article_id order by c.created asc";	
				}
			}
						
			else{
				sql="select  m.catid ,d.title as magtitle,c.sef_url,c.id,c.title,c.introtext, c.created,  ars.section_id, ars.cat_id from jos_content c, jos_sections s, jos_magazine_articles m , jos_article_section ars ,jos_magazine_categories d where ars.section_id=s.id and s.published=1 and c.id=m.article_id and ars.article_id=m.article_id and c.id=ars.article_id and m.catid='"+issueId+"' and m.catid= d.id AND c.state=1  and s.issue_free=0 Group By ars.article_id order by c.created asc";
			}
		}else{
			if(contentType.equalsIgnoreCase("text"))
			sql = "select c.sef_url,c.id,c.title,c.title as magtitle ,c.introtext, c.created,  ars.section_id, ars.cat_id as catid from jos_content as c,jos_article_section as ars where c.id=ars.article_id and c.state=1 and ars.section_id not in(86,87,62,0,109) and c.issueid=0 and c.created > '"+date+" 00:00:00' and c.created < '"+date+"  23:59:59' GROUP BY c.id order by c.created asc";
			else
				sql = "select c.sef_url,c.id,c.title,c.title as magtitle ,c.introtext, c.created,  ars.section_id, ars.cat_id as catid from jos_content as c,jos_article_section as ars where c.id=ars.article_id and c.state=1 and ars.section_id  in(86,87) and c.issueid=0 and c.created > '"+date+" 00:00:00' and c.created < '"+date+"  23:59:59' GROUP BY c.id order by c.created asc";
		}
		
		
		try {
			conn = Dbconnection.getInstance().getConnection();				
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println(sql);
			while (rs.next()) {
				sbean=new SearchBean();
				sbean.setSefUrl(rs.getString("sef_url"));
				sbean.setId(rs.getString("id"));
				sbean.setTitle(rs.getString("title") == null ? "" : rs.getString("title"));
				sbean.setIntrotext(rs.getString("introtext") == null ? "" : rs.getString("introtext"));	
				sbean.setSectionName(rs.getString("magtitle"));	
				sbean.setSectionId(rs.getInt("section_id"));	
				sbean.setCatid(rs.getString("catid"));
				al.add(sbean);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException sqls) {
			System.out.println("Calender helper SQLException -> " + sqls);
		}
	  return al;
  }
  public static ArrayList getCalenderPhotoData(String date, String issueType,int issueId){
	  	ArrayList al = new ArrayList();
	  	Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		SearchBean sbean=null;
		String sql ="";
			
		sql="SELECT g.created,g.id,g.Gallery_name,g.Gallery_caption,g.photo_category,g.sef_url,g.thumb_image, p.title as photo_catname, p.sef_url as photocat_sefurl FROM jos_gallerynames g, jos_photocategories p where g.photo_category=p.id and p.published=1 and g.published=1 and g.created > '"+date+" 00:00:00' and g.created < '"+date+"  23:59:59' order by g.ordering desc ";
		
		try {
			conn = Dbconnection.getInstance().getConnection();				
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			System.out.println(sql);
			while (rs.next()) {
				sbean=new SearchBean();
				sbean.setSefUrl(rs.getString("sef_url"));
				sbean.setId(rs.getString("id"));
				sbean.setTitle(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
				sbean.setIntrotext(rs.getString("Gallery_caption") == null ? "" : rs.getString("Gallery_caption"));
				al.add(sbean);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException sqls) {
			System.out.println("Calender helper SQLException -> " + sqls);
		}
	  return al;
}
  public static ArrayList getIssueDate(int year){
	  	ArrayList al = new ArrayList();
	  	Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		SearchBean sbean=null;
		String sql ="";
		
		try {
			conn = Dbconnection.getInstance().getConnection();	
			//sql = "SELECT A.COVER_IMAGE,A.ID,A.TITLE FROM  jos_magazine_categories A  where A.TITLE like '%"+year+"' and A.published=1 ORDER BY A.id ";
			sql = "SELECT A.COVER_IMAGE,A.ID,A.TITLE FROM  jos_magazine_categories A  where A.TITLE like '%"+year+"' and A.published=1 ORDER BY A.ordering ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			//System.out.println("----->"+sql);
			while (rs.next()) {
				sbean=new SearchBean();			
				sbean.setPublishDate(rs.getString("title"));
				sbean.setPhotoCatid(rs.getString("COVER_IMAGE"));
				sbean.setId(rs.getString("ID"));
				al.add(sbean);
			}
			
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException sqls) {
			System.out.println("Calender helper SQLException -> " + sqls);
		}
	  return al;
}
  

}

