package com.itgd.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.io.IOException;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.StoryDTO;
import com.itgd.dto.HeadlinesTodayDTO;
import com.itgd.dto.HeadlinesTodayBean;
import com.itgd.dto.ProgrammeDTO;
import com.itgd.dto.AnchorDTO;
import com.itgd.utils.CommonFunctions;
import com.itgd.utils.Constants;

public class HeadlinesTodayHelper {
	
	
	public static void main(String[] args) {
		HeadlinesTodayHelper hlt = new HeadlinesTodayHelper();
		try {
			hlt.programmeChunkContent(87, 10);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String artids = "0";
	
	public static ArrayList getProgrammeSectionData() throws Exception	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmtSelect = null;
		ResultSet rsSelect = null;
		HeadlinesTodayBean obj = null;
		ArrayList getData = new ArrayList();
		int maxIdCount = 0;
		int ctr = 0;
		try {

			Dbconnection connect = null;
			//String sql = "SELECT jcat.image,jcat.id as iddd,jc.id, jc.title,jc.byline,jc.introtext,jc.sectionid,jc.catid,jc.kicker_image,jcat.name FROM jos_content jc, jos_categories jcat, jos_sections jsec where jsec.id=87 and jsec.websiteid=2 and jc.sectionid=jsec.id and jc.state=1 and jc.catid=jcat.id and jcat.id in(45,58,57,54,48,49,50,59,51,52,55,47,56,114)  and jc.id=(SELECT max(jc.id) FROM jos_content jc, jos_categories jcat, jos_sections jsec where jsec.id=87 and jsec.websiteid=2 and jc.sectionid=jsec.id and jc.state=1 and jc.catid=jcat.id and jcat.id in(iddd)  ) order by jcat.ordering desc, jc.id desc";
			String sqlSelect = "SELECT max(jc.id) as maxId FROM jos_content jc, jos_categories jcat, jos_sections jsec where jsec.id=87 and jsec.websiteid=2 and jc.sectionid=jsec.id and jc.state=1  and jcat.id in(45,58,57,54,48,49,50,59,51,52,55,47,56,114)  and jc.sectionid=jsec.id and jc.state=1 and jc.catid=jcat.id group by jcat.image order by jcat.ordering desc, jc.id desc";
			//System.out.println(sqlSelect);
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			pstmtSelect = connection.prepareStatement(sqlSelect);
			rsSelect = pstmtSelect.executeQuery();
			StringBuffer getMaxIds = new StringBuffer();
			rsSelect.last();
			maxIdCount = rsSelect.getRow();
			rsSelect.beforeFirst();
			
			while (rsSelect.next()) {
				getMaxIds.append(rsSelect.getString("maxId"));
				if(ctr < maxIdCount-1)
					getMaxIds.append(",");
				ctr++;
			}
			//int count=getMaxIds.lastIndexOf(",");
			//System.out.println(getMaxIds.toString() + "------------"+ count);
			
			String sql= "SELECT jcat.image,jcat.id as iddd,jc.id, jc.title,jc.byline,jc.introtext,jc.sectionid,jc.catid,jc.kicker_image,jcat.name FROM jos_content jc, jos_categories jcat, jos_sections jsec where jsec.id=87 and jsec.websiteid=2 and jc.sectionid=jsec.id and jc.state=1 and jc.catid=jcat.id and jc.id in ("+getMaxIds+") order by jcat.ordering desc, jc.id desc";
			//System.out.println("ssssss"+sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery(sql);
			
			while (rs.next()) {
				obj = new HeadlinesTodayBean();
				obj.setCategoryId(rs.getInt("catid"));
				obj.setCategoryName(rs.getString("name"));
				obj.setContentByLine(rs.getString("byline"));
				obj.setContentCategoryId(rs.getInt("catid"));
				obj.setContentId(rs.getInt("id"));
				obj.setContentIntroText(rs.getString("introtext"));
				obj.setContentKickerImage(rs.getString("image"));
				obj.setContentSectionId(rs.getInt("sectionid"));
				obj.setContentTitle(rs.getString("title"));
				
				getData.add(obj);
				
			}
		} catch (Exception e) {
			System.out.println("Exception in HeadlinesTodayHelper Class & in getProgrammesData method" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(rsSelect!=null)
				rsSelect.close();
			if(pstmtSelect!=null)
				pstmtSelect.close();
			if(connection!=null)
				connection.close();
		}
		return getData;
	}


public static ArrayList getProgrammesVideos(int contentId)	{
	Connection connection = null;
	Statement stmt = null;
	ResultSet rs;
	HeadlinesTodayBean obj;
	ArrayList getData = new ArrayList();
	
	
	try {

		Dbconnection connect = null;
		String sql = "SELECT id, VideoGallery_foldername, VideoGallery_filename, datecreated, contentid FROM jos_videogallerynames where contentid='"+contentId+"'";
		
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery(sql);
		//System.out.println(sql);
		while (rs.next()) {
			obj = new HeadlinesTodayBean();
			obj.setVideoGalleryFolderName(rs.getString("VideoGallery_foldername"));
			obj.setVideoGalleryFileName(rs.getString("VideoGallery_filename"));
		
			getData.add(obj);
			
		}
		rs.close();
		stmt.close();
		connection.close();
	} catch (Exception e) {
		System.out.println("Exception in HeadlinesTodayHelper Class & in getProgrammesVideos method" + e);
	}
	return getData;
}



public static ArrayList getAnchorsDetails() {
	Connection connection = null;
	PreparedStatement stmt = null;
	ResultSet rs;
	HeadlinesTodayBean obj;
	ArrayList getData = new ArrayList();
	
	try {
		
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		String sql = "SELECT jc.reporter_twitter_handle,jc.id,jc.title, jc.introtext,jc.sectionid ,jc.kicker_image,jc.large_kicker_image,jc.large_kicker_image_alt_text,jc.sef_url FROM jos_content jc,jos_sections jsec,jos_article_section jas where jsec.id=? and jas.section_id=jsec.id and jas.article_id=jc.id and jc.sectionid=jsec.id and jsec.websiteid=2 and jc.state=? order by jas.ordering  desc"; 
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, 88);
		stmt.setInt(2, 1);
		rs=stmt.executeQuery();
		
		while(rs.next()) {
			
			obj = new HeadlinesTodayBean();
			obj.setContentId(rs.getInt("id"));
			obj.setContentIntroText(rs.getString("introtext"));
			obj.setContentSefTitle(rs.getString("sef_url"));
			obj.setContentTitle(rs.getString("title"));
			obj.setContentKickerImage(rs.getString("kicker_image"));
			obj.setContentLargeKickerImage(rs.getString("large_kicker_image"));
			obj.setContentLargeKickerImageAltText(rs.getString("large_kicker_image_alt_text"));
			obj.setReporter_twitter_handle(rs.getString("reporter_twitter_handle")==null?"":rs.getString("reporter_twitter_handle"));
			
			getData.add(obj);
		}
		rs.close();
		stmt.close();
		connection.close();
	}
	catch(Exception e) {
		System.out.println("Exception in HeadlinesTodayHelper Class & in getAnchorsDetails method" + e);
	}

 return getData; 
 }




public static ArrayList getAnchorsDetails(int contentId) {
	Connection connection = null;
	PreparedStatement stmt = null;
	ResultSet rs;
	HeadlinesTodayBean obj;
	ArrayList getData = new ArrayList();
	
	try {
		
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		String sql = "SELECT jc.id,jc.title, jc.introtext,jc.fulltext as `fulltext`,jc.metadesc as metaDescription,jc.metakey as metaKeywords,jc.sectionid ,jc.large_kicker_image FROM jos_content jc, jos_sections jsec"; 
		sql=sql+" where jc.id=? and jsec.id=? and sectionid=jsec.id and jsec.websiteid=2 and jc.state=? ";
		stmt = connection.prepareStatement(sql);
		stmt.setInt(1, contentId);
		stmt.setInt(2, 88);
		stmt.setInt(3, 1);
		
		rs=stmt.executeQuery();
		
		while(rs.next()) {
			
			obj = new HeadlinesTodayBean();
			obj.setContentId(rs.getInt("id"));
			obj.setContentIntroText(rs.getString("introtext"));
			obj.setContentTitle(rs.getString("title"));
			obj.setContentLargeKickerImage(rs.getString("large_kicker_image"));
			obj.setContentFullText(rs.getString("fulltext"));
			obj.setMetaDescription(rs.getString("metaDescription"));
			obj.setMetaKeywords(rs.getString("metaKeywords"));
			getData.add(obj);
		}
		rs.close();
		stmt.close();
		connection.close();
	}
	catch(Exception e) {
		System.out.println("Exception in HeadlinesTodayHelper Class & in getAnchorsDetails method" + e);
	}

 return getData; 
 }


public static ArrayList getProgrammesForHomePage(int sectionId, int contentLimit) throws Exception {
	
	Connection connection = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	HeadlinesTodayBean obj = null; 
	ArrayList getData = new ArrayList();
	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		//String sql = "select distinct(c.id) as cid,DATE_FORMAT(c.modified, '%W, %M %e, %Y') as updated_time,DATE_FORMAT(c.modified,'%H:%i')as mtime, DATE_FORMAT(c.created,'%H:%i') as ctime,c.byline, c.city,jcat.id as catid, jcat.title as title, c.introtext,c.kicker_image_alt_text,c.kicker_image,c.kicker_image_width,c.kicker_image_height,c.title as art_name,c.catid as catid,c.id as art_id,c.link_photo,s.id as sec_id,ars.section_id from jos_sections s, jos_content c, jos_article_section ars,jos_categories jcat where s.id =87 and jcat.id=ars.cat_id and s.published=1 and  s.id=ars.section_id and  ars.article_id=c.id and c.state=1  order by ars.ordering desc limit 9";
		//String sql = "select distinct(c.id) as cid,DATE_FORMAT(c.publish_up, '%W, %M %e, %Y') as updated_time,c.byline, c.city, jcat.id as catid, jcat.title as title, c.introtext,c.kicker_image_alt_text,c.kicker_image,c.kicker_image_width, c.kicker_image_height,c.title as art_name,c.catid as catid from jos_sections s, jos_content c, jos_article_section ars,jos_categories jcat where s.id =87 and jcat.id=ars.cat_id and s.published=1 and  s.id=ars.section_id and  ars.article_id=c.id and c.state=1 order by ars.ordering desc limit 9";
		String sql = "select distinct(c.id) as cid,DATE_FORMAT(c.publish_up, '%a, %e %b %Y') as updated_time,c.byline, c.city, " +
				"jcat.id as catid, jcat.title as title, c.introtext,c.kicker_image_alt_text,c.kicker_image,c.kicker_image_width, " +
				"c.kicker_image_height,c.title as art_name,c.catid as catid from jos_sections s, jos_content c, jos_article_section ars," +
				"jos_categories jcat where ars.section_id ="+sectionId+" and s.id=ars.section_id and jcat.id=ars.cat_id and s.published=1 and " +
				"jcat.published=1 and ars.article_id=c.id and c.state=1 order by ars.ordering desc limit "+contentLimit;
		stmt = connection.prepareStatement(sql);
		rs = stmt.executeQuery(sql);
		//System.out.println(sql);
		while (rs.next()) {
			obj = new HeadlinesTodayBean();
			obj.setCategoryId(rs.getInt("catid"));
			obj.setCategoryName(rs.getString("title"));
			obj.setContentCategoryId(rs.getInt("catid"));
			obj.setContentId(rs.getInt("cid"));
			obj.setContentIntroText(rs.getString("introtext"));
			obj.setContentKickerImage(rs.getString("kicker_image"));
			obj.setContentTitle(rs.getString("art_name"));
			obj.setByLine(rs.getString("byline"));
			obj.setCity(rs.getString("city"));			
			obj.setCTime(rs.getString("updated_time"));
			getData.add(obj);
		}
	}
	catch(Exception e) {
		System.out.println("Exception in HeadlinesTodayHelper Class & in getProgrammesForHomePage method" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(connection!=null)
			connection.close();
	}
	return getData; 
}


public static ArrayList getTake5Data() throws Exception
{
	ArrayList take5 = new ArrayList();
	Connection connection = null;
	PreparedStatement stmt = null;
	ResultSet rs =null;
	HeadlinesTodayBean tk5;
	String latestids="0";
	int latestCtr = 0;

	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		String sql = "select c.id,c.title,c.introtext,c.large_kicker_image,c.sef_url,c.byline,c.city,c.id,DATE_FORMAT(c.modified,'%H:%i') as mtime,ars.section_id,ars.cat_id from jos_content c,jos_article_section ars,jos_videogallerynames v   where c.state=1 and c.large_kicker_image!='' and c.id=ars.article_id and c.article_hometop>0 and  c.id=v.contentid  and  c.article_hometop !='-1' group by c.id  order by c.article_hometop asc,ars.ordering desc limit 5";
		stmt = connection.prepareStatement(sql);
		rs=stmt.executeQuery();
		while(rs.next()) {
			if(latestCtr==0)
				latestids = "" + rs.getInt("id");
			else
				latestids += ", " + rs.getInt("id");

			tk5 = new HeadlinesTodayBean();
			tk5.setTake5_catid(rs.getInt("cat_id"));
			tk5.setTake5_id(rs.getInt("id"));
			tk5.setTake5_image(rs.getString("large_kicker_image"));
			tk5.setTake5_intro(rs.getString("introtext"));
			tk5.setTake5_sefURL(rs.getString("sef_url"));
			tk5.setTake5_title(rs.getString("title"));
			tk5.setTake5_byline(rs.getString("byline"));
			tk5.setTake5_city(rs.getString("city"));
			tk5.setTake5_date(rs.getString("mtime"));
			tk5.setTake5ContentIds(latestids);
			take5.add(tk5);
			latestCtr++; 

			artids += ", " + rs.getInt("id");
		}
		//System.out.println("artids--"+artids);
	}
	catch(Exception e) {
		System.out.println("Exception in getTakeFive method" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(stmt!=null)
			stmt.close();
		if(connection!=null)
			connection.close();
	}
	return  take5;
}

 /*public static ArrayList take5Related()
 {
	 ArrayList relatedTake5 = (ArrayList) CommonFunctions.relatedContent(artids);
	 return relatedTake5;
 }*/



public static ArrayList programmesSectionData() throws Exception	{
	Connection connection = null;
	String categoryIds = "";
	PreparedStatement pstmtSelect = null;
	ResultSet rsSelect = null;
	ProgrammeDTO hltDTO = null;
	ArrayList getData = null;
	StringBuffer getContentIds = null;
	String programmeMetaTitle = "";
	String programmeMetaKeywords = "";
	String programmeMetaDescription = "";
	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		String categorySelectQuery = "SELECT cat.id, sec.metatitle, sec.metakey, sec.metadesc FROM jos_categories cat, jos_sections sec where cat.section=sec.id and " +
				"sec.id="+Constants.HLT_PROGRAMMES_SECTIONID+" and sec.published=1 and cat.published=1 group by cat.id order by cat.ordering desc";
		pstmtSelect = connection.prepareStatement(categorySelectQuery);
		rsSelect = pstmtSelect.executeQuery();
		//System.out.println("categorySelectQuery--"+categorySelectQuery);
		while (rsSelect.next()) {
			if(categoryIds.trim().equals("")) {
				categoryIds = rsSelect.getString("id");
				programmeMetaTitle = rsSelect.getString("metatitle") == null ? "" : rsSelect.getString("metatitle");
				programmeMetaKeywords = rsSelect.getString("metakey") ==null ? "" : rsSelect.getString("metakey");
				programmeMetaDescription = rsSelect.getString("metadesc") ==null ? "" : rsSelect.getString("metadesc");

			} else {
				categoryIds = categoryIds + "," + rsSelect.getString("id");
			}
		}

		pstmtSelect = null;
		rsSelect = null;
		
		String[] catArray = null;
		String sqlSelect = "";
		if(categoryIds.length() > 0) {
			if(categoryIds.indexOf(',') > 0) {
				catArray = categoryIds.split(","); 
			} else {
				catArray = new String[1];
				catArray[0] = categoryIds; 
			}
			
			getContentIds = new StringBuffer();
			//System.out.println("categoryIds--"+categoryIds);
			for(int i=0; i < catArray.length; i++) {
				sqlSelect = "SELECT ja.article_id FROM jos_content jc, jos_article_section ja where" +
				" ja.cat_id = "+catArray[i]+" and jc.id=ja.article_id and jc.state=1 order by ja.ordering desc limit 1";
				//System.out.println("sqlSelect--"+sqlSelect);
				pstmtSelect = connection.prepareStatement(sqlSelect);
				rsSelect = pstmtSelect.executeQuery();
				while (rsSelect.next()) {
					if(i > 0)
						getContentIds.append(",");
					getContentIds.append(rsSelect.getString("article_id"));
					//System.out.println("getMaxIds--"+getContentIds);
				}
				sqlSelect = "";
				pstmtSelect = null;
				rsSelect = null;
			}

			String sql= "SELECT jc.id, jc.title, jc.sef_url as content_sefurl,jc.byline,jc.introtext,ja.section_id,jc.kicker_image,jc.kicker_image_alt_text,jcat.image,jcat.id as catid, jcat.name, jcat.sef_url as category_sefurl, jcat.params, jcat.header_image, jcat.telecasting_time" +
					" FROM jos_content jc, jos_article_section ja, jos_categories jcat where jc.id=ja.article_id " +
					"and ja.cat_id=jcat.id and jc.id in ("+getContentIds+") group by jc.id order by jcat.ordering desc, jc.id desc";
			pstmtSelect = connection.prepareStatement(sql);
			rsSelect = pstmtSelect.executeQuery(sql);
			//System.out.println("sql--"+sql);
			getData = new ArrayList();
			while (rsSelect.next()) {
				hltDTO = new ProgrammeDTO();
				hltDTO.setId(rsSelect.getInt("id"));
				hltDTO.setByline(rsSelect.getString("byline"));
				hltDTO.setDescription(rsSelect.getString("introtext"));
				hltDTO.setKickerImage(rsSelect.getString("image"));
				hltDTO.setSectionId(rsSelect.getInt("section_id"));
				hltDTO.setTitle(rsSelect.getString("title"));
				hltDTO.setSefTitle(rsSelect.getString("content_sefurl"));
				hltDTO.setCategoryId(rsSelect.getInt("catid"));
				hltDTO.setCategoryTitle(rsSelect.getString("name"));
				hltDTO.setCategorySefTitle(rsSelect.getString("category_sefurl"));
				hltDTO.setParams(rsSelect.getString("params"));
				hltDTO.setHeader_image(rsSelect.getString("header_image"));
				hltDTO.setTelecasting_time(rsSelect.getString("telecasting_time"));
				hltDTO.setMetaTitle(programmeMetaTitle);
				hltDTO.setMetaKeywords(programmeMetaKeywords);
				hltDTO.setMetaDescription(programmeMetaDescription);
				getData.add(hltDTO);
			}
		}
	} catch (Exception e) {
		System.out.println("Exception in HeadlinesTodayHelper Class programmesSectionData method" + e);
	} finally {
		if(rsSelect!=null)
			rsSelect.close();
		if(pstmtSelect!=null)
			pstmtSelect.close();
		if(connection!=null)
			connection.close();
		if(hltDTO!=null)
			hltDTO = null;
	}
	return getData;
}



	public static ArrayList programmeListData(int currentPageNum, int countToFetch, String programmeIdToAvoid, int programmeCategoryId) throws Exception 
	{
		ArrayList latestProgrammeData = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_pc = null;
		ProgrammeDTO pDTO = null;
		String programmeCheck = "";
		int programmeCount = 0;
		int startLimit = 0;
		String selectSql = "";
		startLimit = (currentPageNum-1)*countToFetch;
		int endLimit = countToFetch;
		
		if(!programmeIdToAvoid.equals("0")) {
			if(programmeIdToAvoid.indexOf(",") > 0)
				programmeCheck = " and jc.id not in ("+programmeIdToAvoid+")";
			else
				programmeCheck = " and jc.id != "+programmeIdToAvoid;
		}

		if(programmeCategoryId==42) { 	
			programmeCheck = programmeCheck + " and jc.created >= DATE_SUB(CURRENT_DATE,INTERVAL 7 DAY) ";

		}
		//System.out.println("programmeCheck-"+programmeCheck);
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			
			selectSql = "SELECT count(distinct jc.id) as contentcount " +
					"FROM jos_content jc, jos_article_section ja, jos_categories jcat, jos_sections jsec " + 
					"where ja.cat_id=? and jc.id=ja.article_id and jc.state=1 and ja.section_id=jsec.id and ja.cat_id=jcat.id and jcat.published=1 and jsec.published=1";
			if(programmeCategoryId==42) { 	
				selectSql = selectSql + " and jc.created >= DATE_SUB(CURRENT_DATE,INTERVAL 7 DAY) ";
			}
			pstmt = connection.prepareStatement(selectSql);
			pstmt.setInt(1, programmeCategoryId);
			rs = pstmt.executeQuery();
			while (rs.next()) { 
				programmeCount = rs.getInt("contentcount");
			}		
			//System.out.println("start->"+startLimit);
			//System.out.println("end->"+endLimit);
			//System.out.println("total->"+programmeCount);
			selectSql = "";
			pstmt = null;
			rs = null;
			selectSql = "SELECT date_format(jc.created,'%a, %e %b %Y' ) AS crt,jc.id,jc.title,jc.sef_url as content_sefurl, jc.byline,jc.introtext,jc.kicker_image,jc.primary_category," +
					"jc.kicker_image_alt_text ,ja.cat_id ,jcat.name, jcat.sef_url as category_sefurl, jcat.metakey, jcat.metadesc, jcat.metatitle,jcat.params,jcat.telecasting_time" +  
					//", jcat.topnav_path, jcat.rightnav_path, jcat.bottomnav_path, jcat.leftnav_path " +
					", jcat.image as catimage, jcat.description as catdescription FROM jos_content jc, jos_article_section ja, jos_categories jcat, jos_sections jsec " +
					"where jc.id=ja.article_id and jc.state=1 and ja.section_id=jsec.id and ja.cat_id=? and " +
					"ja.cat_id=jcat.id and jcat.published=1 and jsec.published=1 "+programmeCheck+" group by ja.article_id order by jc.id " +
					"desc limit ?,?";
			pstmt = connection.prepareStatement(selectSql);
			pstmt.setInt(1, programmeCategoryId);
			pstmt.setInt(2, startLimit);
			pstmt.setInt(3, endLimit);

			rs = pstmt.executeQuery();
			//System.out.println(selectSql);
			latestProgrammeData = new ArrayList();
			while (rs.next()) {
				pDTO = new ProgrammeDTO();
				pDTO.setId(rs.getInt("id"));
				pDTO.setByline(rs.getString("byline"));
				pDTO.setDescription(rs.getString("introtext"));
				pDTO.setKickerImage(rs.getString("kicker_image"));
				pDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				pDTO.setTitle(rs.getString("title"));
				pDTO.setSefTitle(rs.getString("content_sefurl"));
				pDTO.setCategoryId(rs.getInt("cat_id"));
				pDTO.setCategoryTitle(rs.getString("name"));
				pDTO.setCategorySefTitle(rs.getString("category_sefurl"));
				pDTO.setCreatedDate(rs.getString("crt"));
				pDTO.setMetaTitle(rs.getString("metatitle") == null ? "" : rs.getString("metatitle"));				
				pDTO.setMetaDescription(rs.getString("metadesc") == null ? "" : rs.getString("metadesc"));
				pDTO.setMetaKeywords(rs.getString("metakey") == null ? "" : rs.getString("metakey"));
				pDTO.setCategoryImage(rs.getString("catimage") == null ? "" : rs.getString("catimage"));
				pDTO.setCategoryDescription(rs.getString("catdescription") == null ? "" : rs.getString("catdescription"));
				pDTO.setParams(rs.getString("params"));
	        	pDTO.setTelecasting_time(rs.getString("telecasting_time"));
				//pDTO.setTopNavigationPath(rs.getString("topnav_path") == null ? "" : rs.getString("topnav_path"));		
				//pDTO.setRightNavigationPath(rs.getString("rightnav_path") == null ? "" : rs.getString("rightnav_path"));		
				//pDTO.setBottomNavigationPath(rs.getString("bottomnav_path") == null ? "" : rs.getString("bottomnav_path"));		
				//pDTO.setLeftNavigationPath(rs.getString("leftnav_path") == null ? "" : rs.getString("leftnav_path"));		
				pDTO.setContentCount(programmeCount);
				latestProgrammeData.add(pDTO); 
			}
		} catch (Exception e) {
			System.out.println("HeadlinesTodayHelper (programmeListData)->" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
			if(pDTO!=null)
				pDTO = null;
		}
		return latestProgrammeData;
	}

	public static ArrayList programmeDetail(int programmeId, int publishedStatus) throws Exception
	{
		//System.out.println("programmeId--"+programmeId);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ProgrammeDTO pDTO = null;	
		ArrayList programmeList = new ArrayList();
		String programmeStatus = "";
		if(publishedStatus==1)
			programmeStatus = " and c.state=1 ";
		
	    int primaryCategoryLength = 0;
	    String primaryCategory = ""+Constants.HLT_PROGRAMMES_SECTIONID;
	    String primaryCategoryId[] = null;
		
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT c.id, c.primary_category, c.kicker_image,c.extralarge_image,c.large_kicker_image,c.title, c.title_alias, c.sef_url, c.byline, c.city, c.courtesy, c.introtext, " +
					"c.sectionid, c.catid, date_format(c.created,'%a, %e %b %Y' ) AS crt, c.strap_headline, c.metakey,c.metadesc, c.mp4_flag, " +
					"DATE_FORMAT(c.publish_up, '%W, %M %e, %Y') as updated_time, date_format( c.created,'%Y-%m-%d' ) AS snd,v.VideoGallery_foldername,v.external_url,v.VideoGallery_filename " +
					"FROM jos_content c,jos_videogallerynames v, jos_article_section a " +
					"where c.id=? and c.id=a.article_id and c.id=v.contentid"+programmeStatus+" group by c.id";
	    	//System.out.println("Article Detail ->"+sql);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, programmeId);
	    	rs = pstmt.executeQuery ();
			while (rs.next ()){
		        pDTO = new ProgrammeDTO();
		        pDTO.setId(rs.getInt("id"));
		        pDTO.setTitle(rs.getString("title"));
		        pDTO.setTitleAlias(rs.getString("title_alias"));		       
		        pDTO.setByline(rs.getString("byline")); 
		        pDTO.setCity(rs.getString("city"));
		        pDTO.setDescription(rs.getString("introtext"));
		        pDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
		        pDTO.setSefTitle(rs.getString("sef_url"));
		        pDTO.setCreatedDate(rs.getString("crt") == null ? "" : rs.getString("crt"));
		        pDTO.setMetaKeywords(rs.getString("metakey"));
		        pDTO.setMetaDescription(rs.getString("metadesc"));
		        pDTO.setCourtesy(rs.getString("courtesy") == null ? "" : rs.getString("courtesy"));
	        	pDTO.setPrimaryCategory(rs.getString("primary_category") == null ? "0" : rs.getString("primary_category"));
		        primaryCategory = rs.getString("primary_category") == null ? "0" : rs.getString("primary_category");
		        pDTO.setVideoFileName(rs.getString("VideoGallery_filename") == null ? "" : rs.getString("VideoGallery_filename"));
		        pDTO.setVideoFolderName(rs.getString("VideoGallery_foldername") == null ? "" : rs.getString("VideoGallery_foldername"));
		        pDTO.setVideoFileNameExternal(rs.getString("external_url") == null ? "" : rs.getString("external_url"));
		        pDTO.setMp4VideoFlag(rs.getInt("mp4_flag"));		       
		        pDTO.setKickerImage(rs.getString("kicker_image")==null?"" : rs.getString("kicker_image"));
		        pDTO.setLargeKickerImage(rs.getString("large_kicker_image")==null?"" : rs.getString("large_kicker_image"));
		        pDTO.setModifiedDate(rs.getString("snd") == null ? "" : rs.getString("snd"));
		    	//System.out.println("Article 1"+primaryCategory);
		    	try { 
			        if(primaryCategory != null && primaryCategory.trim().length() > 0 ){
					    if((primaryCategory.indexOf("#") > 0) && (primaryCategory.lastIndexOf("#") < primaryCategory.trim().length())) {
					    	primaryCategoryId = primaryCategory.split("#");
					    	primaryCategoryLength = primaryCategoryId.length;
					    } else {
					    	primaryCategoryId = new String[1];
					    	primaryCategoryId[0] = primaryCategory;
					    }
				    	primaryCategoryLength = primaryCategoryId.length;
				    	String sql1 = "select js.id as jsid, js.title as jstitle, js.sef_url as jssefurl ";
				    	if(primaryCategoryLength==1) {
							sql1 = sql1 + "from jos_sections js where js.id="+primaryCategoryId[0];
				    	}
				    	if(primaryCategoryLength==2) {
							sql1 = sql1 + " ,jc.title as jctitle, jc.id as jcid, jc.sef_url as jcsefurl, jc.image as jcimage, jc.description as jcdescription " +
									"from jos_categories jc, jos_sections js where jc.id="+primaryCategoryId[1]+" and jc.section=js.id";
				    	}
				    	//System.out.println("Article Primary Detail ->"+sql1);
	
				    	pstmt1 = connection.prepareStatement(sql1);
				    	rs1 = pstmt1.executeQuery();
						while (rs1.next ()){
							//System.out.println("primaryCategoryLength ->"+primaryCategoryLength);
							pDTO.setSectionId(rs1.getInt("jsid"));
				        	pDTO.setSectionTitle(rs1.getString("jstitle"));
				        	pDTO.setSectionSefTitle(rs1.getString("jssefurl"));
				        	if(primaryCategoryLength >= 2) {
					        	pDTO.setCategoryId(rs1.getInt("jcid"));
					        	pDTO.setCategoryTitle(rs1.getString("jctitle"));
					        	pDTO.setCategorySefTitle(rs1.getString("jcsefurl"));
					        	pDTO.setCategoryImage(rs1.getString("jcimage"));
					        	pDTO.setCategoryDescription(rs1.getString("jcdescription"));
					    	} 
						}
			        }
				} catch (Exception e) {
					System.out.println("HeadlinesTodayHelper programmeDetail primary Category Detail Exception is :" + e.toString());
				}
		        
		        pDTO.setPrimaryCategoryLength(primaryCategoryLength); 
		        programmeList.add(pDTO);
			}
		} catch (Exception e) {
			System.out.println("HeadlinesTodayHelper programmeDetail Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs1!=null)
				rs1.close();
			if(pstmt1!=null)
				pstmt1.close();
			if(connection!=null)
				connection.close();
		}
		return programmeList;

	}

	public static ArrayList anchorsListData() throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AnchorDTO aDTO = null;
		ArrayList anchorAL = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT jc.id,jc.title, jc.sef_url, jc.introtext,jc.kicker_image,jc.kicker_image_alt_text, jsec.title, jsec.metatitle, jsec.metakey, jsec.metadesc " +
					"FROM jos_content jc,jos_sections jsec,jos_article_section jas where jsec.id=? and " +
					"jas.section_id=jsec.id and jas.article_id=jc.id and jc.state=1 order by jas.ordering desc"; 
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, Constants.HLT_ANCHORS_SECTIONID);
			rs=stmt.executeQuery();
			while(rs.next()) {
				aDTO = new AnchorDTO();
				aDTO.setId(rs.getInt("id"));
				aDTO.setDescription(rs.getString("introtext"));
				aDTO.setTitle(rs.getString("title"));
				aDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
				aDTO.setKickerImage(rs.getString("kicker_image"));
				aDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
				aDTO.setMetaTitle(rs.getString("metatitle")==null?"":rs.getString("metatitle")==null?"":rs.getString("metatitle"));
				aDTO.setMetaKeywords(rs.getString("metakey")==null?"":rs.getString("metakey")==null?"":rs.getString("metakey"));
				aDTO.setMetaDescription(rs.getString("metadesc")==null?"":rs.getString("metadesc")==null?"":rs.getString("metadesc"));
				anchorAL.add(aDTO);
			}
		}
		catch(Exception e) {
			System.out.println("HeadlinesTodayHelper anchorsListData Exception -> " + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(connection!=null)
				connection.close();
		}
	 return anchorAL; 
	 }

	public static ArrayList anchorDetail(int contentId, int publishedStatus) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		AnchorDTO aDTO = null;
		ArrayList anchorAL = new ArrayList();
		String anchorStatus = "";
		if(publishedStatus==1)
			anchorStatus = " and jc.state=1 ";

		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT jc.comment_question,jc.id,jc.title, jc.title_alias, jc.sef_url, jc.introtext,jc.fulltext," +
					"jc.metadesc,jc.metakey, jc.large_kicker_image,jc.kicker_image_alt_text " +
					"FROM jos_content jc, jos_sections jsec, jos_article_section jas " +
					"where jc.id=? and jc.id=jas.article_id and jas.section_id=jsec.id "+anchorStatus+" group by jc.id"; 
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, contentId);
			rs=stmt.executeQuery();
			while(rs.next()) {
				aDTO = new AnchorDTO();
				aDTO.setId(rs.getInt("id"));
				aDTO.setDescription(rs.getString("introtext"));
				aDTO.setTitle(rs.getString("title"));
				aDTO.setTitleAlias(rs.getString("title_alias"));
				aDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
				aDTO.setShowcatIds(rs.getString("comment_question")==null?"":rs.getString("comment_question"));
				aDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
				aDTO.setLargeKickerImage(rs.getString("large_kicker_image")==null?"":rs.getString("large_kicker_image"));
				aDTO.setBody(rs.getString("fulltext"));
				aDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
				aDTO.setMetaKeywords(rs.getString("metakey")==null?"":rs.getString("metakey"));
				aDTO.setMetaDescription(rs.getString("metadesc")==null?"":rs.getString("metadesc"));

				anchorAL.add(aDTO);
			}
		}
		catch(Exception e) {
			System.out.println("HeadlinesTodayHelper anchorDetail Exception -> " + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(connection!=null)
				connection.close();
		}
	 return anchorAL; 
	 }

	
	public static ArrayList latestArticle(int sectionId, int countToFetch, String idToAvoid) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HeadlinesTodayDTO laDTO = null;
		ArrayList laAL = new ArrayList();
		String idToCheck = "";
		String fetchedContentIds = "";
    	if(!idToAvoid.equals("0")) {
    		if(idToAvoid.indexOf(",") > 0) 
    			idToCheck = " and c.id not in ("+idToAvoid+") ";
    		else
    			idToCheck = " and c.id !="+idToAvoid+" ";
    	}
		
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT c.id, c.introtext, c.title, c.kicker_image, c.kicker_image_alt_text, c.sef_url, " +
					"s.id as secid, s.title as sectitle, s.sef_url as secsefurl FROM jos_content c, jos_sections s, jos_article_section a " +
					"WHERE a.section_id=? and c.id=a.article_id and a.section_id=s.id and c.state=1 and s.published=1 "+idToCheck+" group by c.id order by a.ordering desc limit ?"; 
			stmt = connection.prepareStatement(sql);
			stmt.setInt(1, sectionId);
			stmt.setInt(2, countToFetch);
			//System.out.println("sql-->"+sql);
			rs=stmt.executeQuery();
			//rs.last();
			//System.out.println("sql1-->"+rs.getRow());
			//rs.beforeFirst();
			while(rs.next()) {
				laDTO = new HeadlinesTodayDTO();
				laDTO.setId(rs.getInt("id"));
				laDTO.setDescription(rs.getString("introtext")==null?"":rs.getString("introtext"));
				laDTO.setTitle(rs.getString("title"));
				laDTO.setSefTitle(rs.getString("sef_url"));
				laDTO.setKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
				laDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
				laDTO.setSectionId(rs.getInt("secid"));
				laDTO.setSectionTitle(rs.getString("sectitle"));
				laDTO.setSectionSefTitle(rs.getString("secsefurl")==null?"":rs.getString("secsefurl"));
				if(fetchedContentIds.equals("")) {
					fetchedContentIds = ""+rs.getInt("id"); 
				} else {
					fetchedContentIds = fetchedContentIds + ", " +rs.getInt("id");
				}
				laDTO.setFetchedContentIds(fetchedContentIds);
				laAL.add(laDTO);
			}
		}
		catch(Exception e) {
			System.out.println("HeadlinesTodayHelper latestArticle Exception ->" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(connection!=null)
				connection.close();
		}
	 return laAL; 
	}

    public static ArrayList videoFileNameAL(String name) {
        ArrayList videofiles = new ArrayList();
        if(name.indexOf('|') == 0) {
        	name = name.substring(1, name.length());
        }
        if(name.lastIndexOf('|') == name.length()-1) {
        	name = name.substring(0, name.length()-1);
        }
    	String[] tokens = name.split("\\|");
        for (int i = 0; i < tokens.length; i++) {
        	if(tokens[i].trim().length() > 0)
        		videofiles.add(tokens[i]);
        }
        return videofiles;
    }

	
    public static ArrayList take5ChunkContent(int countToFetch) throws Exception
    {
    	ArrayList take5AL = new ArrayList();
    	Connection connection = null;
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
    	HeadlinesTodayDTO t5DTO = null;
    	String take5Ids="";
    	try {
    		Dbconnection connect = null;
    		connect = Dbconnection.getInstance();
    		connection = connect.getConnection();
    		String sql = "select c.id,c.title,c.introtext,c.large_kicker_image,c.kicker_image_alt_text, c.sef_url,c.byline,c.city,c.id," +
    				"DATE_FORMAT(c.modified,'%H:%i') as mtime,ars.section_id,ars.cat_id " +
    				"from jos_content c,jos_article_section ars,jos_videogallerynames v   " +
    				"where c.state=1 and c.large_kicker_image!='' and c.id=ars.article_id and c.article_hometop>0 and c.id=v.contentid " +
    				"group by c.id order by c.article_hometop asc,ars.ordering desc limit ?";
    		stmt = connection.prepareStatement(sql);
    		stmt.setInt(1, countToFetch);
    		rs=stmt.executeQuery();
    		while(rs.next()) {
    			if(take5Ids.equals(""))
    				take5Ids = "" + rs.getInt("id");
    			else
    				take5Ids += ", " + rs.getInt("id");

    			t5DTO = new HeadlinesTodayDTO();
    			t5DTO.setCategoryId(rs.getInt("cat_id"));
    			t5DTO.setId(rs.getInt("id"));
    			t5DTO.setLargeKickerImage(rs.getString("large_kicker_image"));
    			t5DTO.setKickerImageAltText(rs.getString("kicker_image_alt_text"));
    			t5DTO.setDescription(rs.getString("introtext"));
    			t5DTO.setSefTitle(rs.getString("sef_url"));
    			t5DTO.setTitle(rs.getString("title"));
    			t5DTO.setByline(rs.getString("byline"));
    			t5DTO.setCity(rs.getString("city"));
    			t5DTO.setCreatedTime(rs.getString("mtime"));
    			t5DTO.setFetchedContentIds(take5Ids);
    			take5AL.add(t5DTO);
    		}
    	}
    	catch(Exception e) {
    		System.out.println("HeadlinesTodayHelper take5Content Exception -> " + e);
    	} finally {
    		if(rs!=null)
    			rs.close();
    		if(stmt!=null)
    			stmt.close();
    		if(connection!=null)
    			connection.close();
    	}
    	return  take5AL;
    }

	//programme function overloaded to maintain uniformity
	public static String programmeURL(String programmeContentSefTitle, int pageNum, int programmeContentId) {
		String finalSefTitle = programmeContentSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");
	
		String programmeUrl = "programme/" + finalSefTitle + "/" + pageNum + "/" + programmeContentId + ".html";
		return programmeUrl;
	}
	
	public static String programmeListURL(String programmeSefTitle, int pageNum, int programmeId) {
		String finalSefTitle = programmeSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");
	
		String programmeListUrl = "programmelist/" + finalSefTitle + "/" + pageNum + "/" + programmeId + ".html";
		return programmeListUrl;
	}

	public static String programmesSectionURL() {
		String programmesUrl = "programmes";
		return programmesUrl;
	}

	public static String anchorsSectionURL() {
		String programmesUrl = "anchors";
		return programmesUrl;
	}
	public static String anchorURL(String anchorSefTitle, int pageNum, int anchorId) {
		String finalSefTitle = anchorSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");
	
		String anchorUrl = "anchor/" + finalSefTitle + "/" + pageNum + "/" + anchorId + ".html";
		return anchorUrl;
	}


	public static ArrayList programmeChunkContent(int sectionId, int contentLimit) throws Exception {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		HeadlinesTodayDTO pDTO = null;
		ArrayList pAL = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select c.reporter_twitter_handle,c.id, c.introtext,c.kicker_image_alt_text,c.kicker_image,DATE_FORMAT(c.publish_up, '%a, %e %b %Y') as updated_time," +
					" c.byline, c.city, c.title, c.sef_url, cat.id as catid, cat.title as cattitle, cat.sef_url as catsefurl,c.large_kicker_image,c.large_kicker_image_alt_text " +
					"from jos_content c, jos_article_section a,jos_sections s, jos_categories cat " +
					"where a.article_id=c.id and a.section_id="+sectionId+" and s.id=a.section_id and cat.id=a.cat_id and s.published=1 " +
					"and cat.published=1 and c.state=1 group by c.id order by a.ordering desc limit "+contentLimit;
			stmt = connection.prepareStatement(sql);
			//System.out.println("sdsadsdsadsad---"+sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				pDTO = new HeadlinesTodayDTO();
				pDTO.setId(rs.getInt("id"));
				pDTO.setDescription(rs.getString("introtext"));
				pDTO.setKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
				pDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
				pDTO.setLargeKickerImage(rs.getString("large_kicker_image")==null?"":rs.getString("large_kicker_image"));
				pDTO.setLargeKickerImagealtText(rs.getString("large_kicker_image_alt_text")==null?"":rs.getString("large_kicker_image_alt_text"));
				pDTO.setTitle(rs.getString("title"));
				pDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
				pDTO.setByline(rs.getString("byline")==null?"":rs.getString("byline"));
				pDTO.setCity(rs.getString("city")==null?"":rs.getString("city"));			
				pDTO.setCreatedDate(rs.getString("updated_time")==null?"":rs.getString("updated_time"));
				pDTO.setCategoryId(rs.getInt("catid"));
				pDTO.setCategoryTitle(rs.getString("cattitle"));
				pDTO.setCategorySefTitle(rs.getString("catsefurl"));
				pDTO.setReporter_twitter_handle(rs.getString("reporter_twitter_handle")==null?"":rs.getString("reporter_twitter_handle"));
				pAL.add(pDTO);
			}
		}
		catch(Exception e) {
			System.out.println("HeadlinesTodayHelper programmeChunkContent ->" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(connection!=null)
				connection.close();
		}
		return pAL; 
	}

	public static String articleDetailToRedirect(int articleId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strToReturn="";
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT c.id, c.sef_url FROM jos_content c WHERE c.id = ?";
	    	//System.out.println("Article Detail articleDetailToRedirect->"+sql);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, articleId);
	    	rs = pstmt.executeQuery(); 
			while (rs.next ()){
				strToReturn = rs.getString("sef_url") + "#" + rs.getInt("id");
			}
		} catch (Exception e) {
			System.out.println("HeadlinesTodayHelper articleDetailToRedirect Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return strToReturn;
	}


}

