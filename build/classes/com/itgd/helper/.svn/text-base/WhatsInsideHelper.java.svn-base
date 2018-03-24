package com.itgd.helper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.SQLException;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.WhatsInsideDTO;

public class WhatsInsideHelper {
	
	public static ArrayList getDetails() throws Exception	{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	WhatsInsideDTO obj = null;
	ArrayList getData = new ArrayList();
	try {
		Dbconnection connect = null;
		String sql = "SELECT js.id as secId,js.title as title,js.sef_url as section_sefurl, jc.id as categoryId FROM jos_sections js ,jos_categories jc " +
				"where js.websiteid=4 and jc.section=js.id and js.published=1 and jc.id=(SELECT jc.id  FROM jos_sections js ,jos_categories jc where js.websiteid=4 and jc.section=secId and jc.published=1 and js.published=1 group by jc.id  order by jc.ordering limit 1)order by js.ordering desc,jc.id desc";
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		//System.out.println(sql);
		while (rs.next()) {
				obj = new WhatsInsideDTO();
				obj.setSectionId(rs.getInt("secId"));
				obj.setSectionName(rs.getString("title"));
				obj.setSectionSefTitle(rs.getString("section_sefurl"));
				obj.setCategoryId(rs.getInt("categoryId"));
				getData.add(obj);
			}
		} catch (Exception e) {
			System.out.println("Exception in WhatsInsideHelper.java in ( getDetails ) Method=> " + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return getData;
	}
	
	public static ArrayList getCategoryTitleDetails(int sectionId,int catId, int noOfArticles) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WhatsInsideDTO obj = null;
		ArrayList getData = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			//String sql = "SELECT jcat.id,jcat.title,jcat.sef_url as category_sefurl,jcon.id as contentId,jcon.title as contentTitle,jcon.introtext,jcon.kicker_image," +
			//"jcon.sef_url from jos_categories jcat,jos_article_section jas,jos_content jcon where jcat.section=? and jcat.id=? and " +
			//"jcat.published=1 and jas.section_id=jcat.section and jas.cat_id=jcat.id and jas.article_id=jcon.id " +
			//"and jcon.state=1 group by jcon.id order by jas.ordering desc limit ?";
			String sql = "SELECT jcat.id,jcat.title,jcat.sef_url as category_sefurl,jsec.sef_url as section_sefurl, jcon.id as contentId,jcon.title as contentTitle,jcon.introtext,jcon.kicker_image," +
					"jcon.sef_url from jos_categories jcat,jos_article_section jas,jos_content jcon, jos_sections jsec where jcat.section=? and jcat.id=? and " +
					"jcat.published=1 and jas.section_id=jcat.section and jas.cat_id=jcat.id and jas.article_id=jcon.id and jsec.id=jcat.section and jsec.published=1 " +
					"and jcon.state=1 group by jcon.id order by jas.ordering desc limit ?";
			pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sectionId);
            pstmt.setInt(2, catId);
            pstmt.setInt(3, noOfArticles);
			rs = pstmt.executeQuery();
			//System.out.println(sql);
			while (rs.next()) {
					obj = new WhatsInsideDTO();
					obj.setCategoryId(rs.getInt("id"));
					obj.setCategoryName(rs.getString("title"));
					obj.setCategorySefTitle(rs.getString("category_sefurl"));
					obj.setSectionSefTitle(rs.getString("section_sefurl"));
					obj.setContentId(rs.getInt("contentId"));
					obj.setTitle(rs.getString("contentTitle"));
					obj.setKickerImage(rs.getString("kicker_image"));
					obj.setIntroText(rs.getString("introText"));
					obj.setSefURL(rs.getString("sef_url"));
					getData.add(obj);
				}
			} catch (Exception e) {
				System.out.println("Exception in WhatsInsideHelper.java in ( getCategoryTitleDetails ) Method=> " + e);
			} finally {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(connection!=null)
					connection.close();
				
			}
			return getData;
		}
	
	public static ArrayList getRelatedCategoryDetails(int sectionId,int displayCategoryLimit,int categoryId) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		WhatsInsideDTO obj = null;
		ArrayList getData = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT title,sef_url,id,section from jos_categories where section=? and id!=? and published=1 group by id order by ordering desc limit ?";
			pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, sectionId);
            pstmt.setInt(2, categoryId);
            pstmt.setInt(3, displayCategoryLimit);
			rs = pstmt.executeQuery();
			//System.out.println(sql);
			while (rs.next()) {
					obj = new WhatsInsideDTO();
					obj.setCategoryId(rs.getInt("id"));
					obj.setCategoryName(rs.getString("title"));
					obj.setCategorySefTitle(rs.getString("sef_url"));
					obj.setCategorySectionId(rs.getInt("section"));
					getData.add(obj);
			}
			} catch (Exception e) {
				System.out.println("Exception in WhatsInsideHelper.java in ( getRelatedCategoryDetails ) Method=> " + e);
			} finally {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(connection!=null)
					connection.close();
			}
			return getData;
		}
}