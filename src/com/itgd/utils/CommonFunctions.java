package com.itgd.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import javax.servlet.RequestDispatcher;
import com.itgd.conn.Dbconnection;
import com.itgd.dto.LatestContentDTO;
import com.itgd.dto.RelatedContentDTO;
import com.itgd.dto.StaticContentDTO;
import com.itgd.dto.LatestArticleDTO;
import com.itgd.dto.RelatedStoryBean;
import com.itgd.dto.StoryDTO;

public class CommonFunctions {
	//Functions for URL generation
	//to be used
	public static String storyURL(String contentSefTitle, int pageNum, int contentId) {
		String finalSefTitle = contentSefTitle;
		String contentUrl=null;
		try{
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");
		contentUrl = "story/" + finalSefTitle + "/" + pageNum + "/" + contentId + ".html";
		}catch(Exception e){} 
		return contentUrl;
	}
	public static String articleURL(String articleSefTitle, int pageNum, int articleId) {
		String finalSefTitle = articleSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String articleUrl = "story/" + finalSefTitle + "/" + pageNum + "/" + articleId + ".html";
		return articleUrl;
	}
	public static String storyPreviewURL(String contentSefTitle, int pageNum, int contentId) {
		String finalSefTitle = contentSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String contentUrl = "storypreview/" + finalSefTitle + "/" + pageNum + "/" + contentId + ".html";
		return contentUrl;
	}
	public static String articlePreviewURL(String articleSefTitle, int pageNum, int articleId) {
		String finalSefTitle = articleSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String articleUrl = "storypreview/" + finalSefTitle + "/" + pageNum + "/" + articleId + ".html";
		return articleUrl;
	}
	public static String sectionURL(String sectionSefTitle, int pageNum, int sectionId) {
		String sectionUrl = "section/" + sectionId + "/" + pageNum + "/" + sectionSefTitle;
		if(sectionSefTitle.indexOf(".html") < 0)
			sectionUrl = sectionUrl + ".html";
		return sectionUrl;
	}

	public static String categoryURL(String categorySefTitle, int pageNum, int categoryId) {
		String finalSefTitle = categorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String categoryUrl = "category/" + finalSefTitle + "/" + pageNum + "/" + categoryId + ".html";
		return categoryUrl;
	}

	public static String subCategoryURL(String subCategorySefTitle, int pageNum, int subCategoryId) {
		String finalSefTitle = subCategorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String subCategoryUrl = "subcategory/" + finalSefTitle + "/" + pageNum + "/" + subCategoryId + ".html";
		return subCategoryUrl;
	}

	public static String subSubCategoryURL(String subSubCategorySefTitle, int pageNum, int subSubCategoryId) {
		String finalSefTitle = subSubCategorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String subSubCategoryUrl = "subsubcategory/" + finalSefTitle + "/" + pageNum + "/" + subSubCategoryId + ".html";
		return subSubCategoryUrl;
	}

	public static String videoURL(String contentSefTitle, int pageNum, int contentId) {
		String finalSefTitle = contentSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String contentUrl = "video/" + finalSefTitle + "/" + pageNum + "/" + contentId + ".html";
		return contentUrl;
	}

	public static String videoListURL(String contentSefTitle, int pageNum, int contentId) {
		String finalSefTitle = contentSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String contentUrl = "videolist/" + finalSefTitle + "/" + pageNum + "/" + contentId + ".html";
		return contentUrl;
	}

	public static String videoSectionURL() {
		String contentUrl = "videos";
		return contentUrl;
	}
	
	public static String galleryURL(String contentSefTitle, int pageNum, int contentId) {
		String finalSefTitle = contentSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String contentUrl = "gallery/" + finalSefTitle + "/" + pageNum + "/" + contentId + ".html";
		return contentUrl;
	}

	public static String galleryListURL(String contentSefTitle, int pageNum, int contentId) {
		String finalSefTitle = contentSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String contentUrl = "gallerylist/" + finalSefTitle + "/" + pageNum + "/" + contentId + ".html";
		return contentUrl;
	}

	public static String gallerySectionURL() {
		String contentUrl = "galleries";
		return contentUrl;
	}

	public static String generalCommentURL(int pageNum) {
		String contentUrl = "generalcomment/" + pageNum;
		return contentUrl;
	}

	public static String commentListURL(int pageNum) {
		String contentUrl = "commentlist/" + pageNum;
		return contentUrl;
	}

	public static String commentURL(String contentType, int pageNum, int contentId) {
		String contentUrl = "comments";
		if(contentType.equals("photo"))
			contentUrl = "comment/photo/" + pageNum + "/"+contentId;
		else if(contentType.equals("video"))
			contentUrl = "comment/video/" + pageNum + "/"+contentId;
		else
			contentUrl = "comment/story/" + pageNum + "/"+contentId;

		return contentUrl;
	}
	
	public static String commentURL(String contentType, int pageNum, int contentId, int slideId) {
		String contentUrl = "comments";
		if(contentType.equals("photo") && slideId==0)
			contentUrl = "comment/photo/" + pageNum + "/"+contentId;
		else if (contentType.equals("photo") && slideId!=0)
			contentUrl = "comment/photo/" + pageNum + "/"+contentId+"/"+slideId;
		else if(contentType.equals("video"))
			contentUrl = "comment/video/" + pageNum + "/"+contentId;
		else
			contentUrl = "comment/story/" + pageNum + "/"+contentId;

		return contentUrl;
	}

	public static String issueURL(int issueId, String issueSefTitle) {
		String issueUrl = "";
		if(issueSefTitle.trim().length() > 0)
			issueUrl = "issue/" + issueSefTitle + "/" + issueId + ".html";
		else
			issueUrl = "Issue?issueId=" + issueId;
		
		return issueUrl;
	}
	
	public static String checkNull(String parameter)
	{
		return parameter == null ? "" : parameter;
	}
	
	 public static boolean isInteger(String parameter) {
	        try { 
	             Integer.parseInt(parameter); 
	           } catch(NumberFormatException e) { 
	            return false; 
	        }
	        // only got here if we didn't return false
	        return true;
	 }
	public static String updatedTime() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String updatedTime = "";
		try {
			Dbconnection conn1 = null;
			String sql = "SELECT date_format(updated_time, '<b>%H:%i IST</b> | %a, %M %e, %Y') as updated_time FROM jos_updatetime order by id desc limit 1";
			conn1 = Dbconnection.getInstance();
			conn  = conn1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				updatedTime = rs.getString("updated_time");
			}
		} catch(SQLException sqls) {
			System.out.println("IT CommonFunctions.java - updatedTime function - Exception ->" + sqls);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}
		return updatedTime;
	}
	
	public static String updatedTimeWithoutDay() throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String updatedTime = "";
		try {
			Dbconnection conn1 = null;
			String sql = "SELECT date_format(updated_time, '<b>%H:%i IST</b> | %M %e, %Y') as updated_time FROM jos_updatetime order by id desc limit 1";
			conn1 = Dbconnection.getInstance();
			conn  = conn1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				updatedTime = rs.getString("updated_time");
			}
		} catch(SQLException sqls) {
			System.out.println("IT CommonFunctions.java - updatedTime function - Exception ->" + sqls);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}
		return updatedTime;
	}

	public static ArrayList breakingNews() throws Exception
	{
		Connection connection = null;
		Dbconnection connect = null;
		Statement stmt = null,stmt1 = null;
		ResultSet rs=null,rs1=null;
		LatestContentDTO breakingDTO = null;
		ArrayList breakingList = new ArrayList();
		String breakingLabel = "";
		try {
			String sql = "SELECT enabled, breaking_label, watch_live FROM jos_breakingnews";
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);			
			while(rs.next()) {
				if(rs.getInt("enabled")==1){
					breakingLabel = rs.getString("breaking_label")==null?"":rs.getString("breaking_label");	
					String sqlQuery = "select c.id, c.title, c.sef_url, date_format(c.created,'%H:%i IST') as news_time, c.`fulltext` " +
							"from jos_content c,jos_article_section a " +
							"where c.breaking_news=1 and c.id=a.article_id and c.state=1 order by a.ordering desc limit 1";
					//System.out.println("breaking sql ->" + sqlQuery);
					stmt1 = connection.createStatement();
					rs1 = stmt1.executeQuery(sqlQuery);				
					while (rs1.next()) {
						breakingDTO = new LatestContentDTO();
						breakingDTO.setId(rs1.getInt("id"));
						breakingDTO.setTitle(rs1.getString("title"));
						breakingDTO.setSefTitle(rs1.getString("sef_url")==null?"":rs1.getString("sef_url"));
						breakingDTO.setUpdatedTime(rs1.getString("news_time")==null?"":rs1.getString("news_time"));
						breakingDTO.setLongDescription(rs1.getString("fulltext")==null?"":rs1.getString("fulltext"));
						breakingDTO.setBreakingLabel(breakingLabel);
						breakingDTO.setWatchLive(rs.getInt("watch_live"));
						breakingList.add(breakingDTO);
					}
				}	
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java - breakingNews function - Exception ->" + e);
		}finally{
			if(stmt!=null)
				stmt.close();
			if(stmt1!=null)
				stmt1.close();
			if(rs!=null)
				rs.close();
			if(rs1!=null)
				rs1.close();
			if(connection!=null)
				connection.close();
		}
		return breakingList;
	}

	 public static ArrayList mostPopular(int sectionId, int vSectionId, int limit) throws Exception
		{
			Connection conn = null;
			ResultSet rs = null;
			PreparedStatement pstmt = null;

			ResultSet rs1 = null;
			PreparedStatement pstmt1 = null;
			
			ResultSet rs_com = null;
			PreparedStatement pstmt_com = null;

			LatestContentDTO lDTO = null;
			ArrayList mostPopularData = new ArrayList();
			int pcatlength = 0;
			String pcatids[] = null;
			String categoryId = "175";
			String sql=null;
			int commentCount=0;
			int featuredTab=1;

			try
			{
				Dbconnection connect = null;
				connect = Dbconnection.getInstance();
				conn  = connect.getConnection();
				sql = "SELECT mostpopular_featured FROM jos_breakingnews";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					featuredTab = rs.getInt("mostpopular_featured");
				}
				pstmt=null;
				rs=null;
				sql="";
				if(sectionId!=0) {
					sql = "SELECT c.id, c.title, c.sef_url, c.mobile_image, c.kicker_image_alt_text, c.primary_category	" +
							"FROM jos_content c, jos_article_section a, jos_sections s " +
							"WHERE c.id=a.article_id and a.section_id =? and s.id=a.section_id and c.state=1 and " +
							"s.published=1 group by a.id order by a.ordering desc limit ?";
					//System.out.println("MP Story->"+sql);
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1,sectionId);
					pstmt.setInt(2,limit);
					rs = pstmt.executeQuery();
					while (rs.next()){
						lDTO = new LatestContentDTO();
						lDTO.setId(rs.getInt("id"));
						lDTO.setTitle(rs.getString("title"));
						lDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
						lDTO.setThumbImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
						lDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
						lDTO.setContentType("story");
						lDTO.setFeatured(featuredTab);
						categoryId = rs.getString("primary_category");
					    if(categoryId.indexOf("#") > 0) {
					    	pcatids = categoryId.split("#");
						} else {
							pcatids = new String[1];
				    		pcatids[0] = categoryId;
						}
					    
					    if(pcatids==null) {
							pcatids = new String[1];
				    		pcatids[0] = "175";
					    }
				    	pcatlength = pcatids.length;
						String sql1 = "select ";
						if(pcatlength==1)
							sql1 = sql1 + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_sections js where js.id="+pcatids[0]+" and js.published=1";
						else if(pcatlength==2) 
							sql1 = sql1 + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_sections js where jc.id="+pcatids[1]+" and jc.published=1 and js.published=1 and js.id=jc.section";
						else if(pcatlength==3) 
							sql1 = sql1 + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_sections js, jos_categories jc where jsc.id="+pcatids[2]+" and jsc.published=1 and  jc.id=jsc.category and js.id=jsc.section and jc.published=1 and js.published=1";
						else if(pcatlength==4) 
							sql1 = sql1 + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_sections js, jos_categories jc, jos_subcategories jsc where jssc.id="+pcatids[3]+" and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jssc.sub_category=jsc.id and jc.id=jsc.category and js.id=jsc.section";

						pstmt1 = conn.prepareStatement(sql1);
						rs1 = pstmt1.executeQuery();
						while (rs1.next()) {
							lDTO.setCurrentCategoryId(rs1.getInt("curcatid"));		
							lDTO.setCurrentCategoryTitle(rs1.getString("curcatname"));		
							lDTO.setCurrentCategorySefTitle(rs1.getString("curcatsefurl") == null ? "" : rs1.getString("curcatsefurl"));		
							lDTO.setCurrentCategoryPageURL(rs1.getString("curcatpath") == null ? "" : rs1.getString("curcatpath"));		
							lDTO.setPrimaryCategoryLength(pcatlength);
						}
				    	
						try {
							String ssql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='story'";
							pstmt_com = conn.prepareStatement(ssql);
							rs_com = pstmt_com.executeQuery();
							while (rs_com.next()) {
								commentCount = rs_com.getInt("ccount");				
							}
						}catch(Exception c){
							System.out.println("CommonFunctions mostpopular-comment Exception is :" + c);
						}
						lDTO.setCommentCount(commentCount);

				    	mostPopularData.add(lDTO);
					}
					rs=null;
					pstmt=null;
					sql="";
					rs1=null;
					pstmt1=null;
					rs_com=null;
					pstmt_com=null;
				}
				
				if(vSectionId!=0) {
					sql="SELECT c.id, c.title, c.sef_url, c.mobile_image, c.kicker_image_alt_text, c.primary_category " +
							"FROM jos_content c,jos_article_section a,jos_videogallerynames v,jos_categories jcat " +
							"where a.cat_id=? and jcat.id=a.cat_id and c.id=a.article_id and c.id=v.contentid and c.state=1 " +
							"and jcat.published=1 group by a.article_id order by a.ordering desc limit ?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1,vSectionId);
					pstmt.setInt(2,limit);
					rs = pstmt.executeQuery();
					//System.out.println("MP Video->"+sql);
					while(rs.next()){
						lDTO = new LatestContentDTO();
						lDTO.setId(rs.getInt("id"));
						lDTO.setTitle(rs.getString("title"));
						lDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
						lDTO.setThumbImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
						lDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
						lDTO.setContentType("video");
						lDTO.setFeatured(featuredTab);
						categoryId = rs.getString("primary_category");
					    if(categoryId.indexOf("#") > 0) {
					    	pcatids = categoryId.split("#");
						} else {
							pcatids = new String[1];
				    		pcatids[0] = categoryId;
						}
					    
					    if(pcatids==null) {
							pcatids = new String[2];
				    		pcatids[0] = "86";
				    		pcatids[1] = "42";
					    }
				    	pcatlength = pcatids.length;
						String sql1 = "select ";
						if(pcatlength==1)
							sql1 = sql1 + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_sections js where js.id="+pcatids[0]+" and js.published=1";
						else if(pcatlength==2) 
							sql1 = sql1 + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_sections js where jc.id="+pcatids[1]+" and jc.published=1 and js.published=1 and js.id=jc.section";

						pstmt1 = conn.prepareStatement(sql1);
						rs1 = pstmt1.executeQuery();
						while (rs1.next()) {
							lDTO.setCurrentCategoryId(rs1.getInt("curcatid"));		
							lDTO.setCurrentCategoryTitle(rs1.getString("curcatname"));		
							lDTO.setCurrentCategorySefTitle(rs1.getString("curcatsefurl") == null ? "" : rs1.getString("curcatsefurl"));		
							lDTO.setCurrentCategoryPageURL(rs1.getString("curcatpath") == null ? "" : rs1.getString("curcatpath"));		
							lDTO.setPrimaryCategoryLength(pcatlength);
						}
						try {
							String ssql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='video'";
							pstmt_com = conn.prepareStatement(ssql);
							rs_com = pstmt_com.executeQuery();
							while (rs_com.next()) {
								commentCount = rs_com.getInt("ccount");				
							}
						}catch(Exception c){
							System.out.println("CommonFunctions mostpopular-comment Exception is :" + c);
						}
						lDTO.setCommentCount(commentCount);

						mostPopularData.add(lDTO);
					}
				}
			} catch (Exception e) {
				System.out.println("IT CommonFunctions.java - mostPopular function - Exception ->" + e);
			}finally{
				if(pstmt1!=null)
					pstmt1.close();
				if(rs1!=null)
					rs1.close();
				if(pstmt!=null)
					pstmt.close();
				if(rs!=null)
					rs.close();
				if(pstmt_com!=null)
					pstmt_com.close();
				if(rs_com!=null)
					rs_com.close();
				if(conn!=null)
					conn.close();
			}
		
	 return mostPopularData;
		}

	 public static ArrayList multimedia(int pSectionId, int vSectionId, int limit) throws Exception
	 {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LatestContentDTO lDTO = null;
		ArrayList mData = new ArrayList();
		String sql=null;
		int featuredTab=1;
		try
		{
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			conn  = connect.getConnection();
			try {
				sql = "SELECT multimedia_featured FROM jos_breakingnews";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					featuredTab = rs.getInt("multimedia_featured");
				}
				pstmt=null;
				rs=null;
				sql="";
				//sql ="SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image, gn.small_image, gn.photo_category FROM jos_gallerynames gn, jos_photocategories pc where gn.published=1 and gn.photo_category=pc.id and pc.published=1 group by gn.id order by gn.ordering desc limit ?";
				if(pSectionId==0)	
					sql ="SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image, gn.small_image FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs where pc.id=gs.cat_id and gn.id=gs.content_id and gn.published=1 and pc.published=1 group by gs.content_id order by gs.ordering desc limit ?";
				else
					sql ="SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image, gn.small_image FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs where gs.cat_id="+pSectionId+" and pc.id=gs.cat_id and gn.id=gs.content_id and gn.published=1 and pc.published=1 group by gs.content_id order by gs.ordering desc limit ?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,limit);
				rs = pstmt.executeQuery();
				while (rs.next()){
					lDTO = new LatestContentDTO();
					lDTO.setId(rs.getInt("id"));
					lDTO.setTitle(rs.getString("Gallery_caption")==null?"":rs.getString("Gallery_caption"));
					lDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
					lDTO.setThumbImage(rs.getString("thumb_image")==null?"":rs.getString("thumb_image"));
					lDTO.setThumbImageAltText(rs.getString("Gallery_caption")==null?"":rs.getString("Gallery_caption"));
					lDTO.setLargeImage(rs.getString("small_image")==null?"":rs.getString("small_image"));
					lDTO.setFeatured(featuredTab);
					lDTO.setContentType("photo");
					mData.add(lDTO);
				}
				rs=null;
				pstmt=null;
				sql="";
			} catch (Exception e) {
				System.out.println("IT CommonFunctions.java - multimedia function Gallery Query - Exception ->" + e);
			}	
			try {
				sql="select c.id,c.sef_url,c.title,c.mobile_image,c.kicker_image, c.kicker_image_alt_text " +
				"from jos_content c,jos_article_section a,jos_sections s " +
				"where a.section_id=? and c.id=a.article_id and s.id=a.section_id and c.state=1 and s.published=1 ";
				if(vSectionId==86) { 	
					sql = sql + " and c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 7 DAY) ";
				}
				sql = sql + " group by c.id order by a.ordering desc limit ?";

				//System.out.println(vSectionId + " - Multimedia Video CHunk Query -> " + sql);	
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1,vSectionId);
				pstmt.setInt(2,limit);
				rs = pstmt.executeQuery();
				while(rs.next()){
					lDTO = new LatestContentDTO();
					lDTO.setId(rs.getInt("id"));
					lDTO.setTitle(rs.getString("title"));
					lDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
					lDTO.setThumbImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
					lDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
					lDTO.setLargeImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
					lDTO.setFeatured(featuredTab);
					lDTO.setContentType("video");
					mData.add(lDTO);
				}
			} catch (Exception e) {
				System.out.println("IT CommonFunctions.java - multimedia function Video Query - Exception ->" + e);
			}	
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java - multimedia function - Exception ->" + e);
		}finally{
			if(pstmt!=null)
				pstmt.close();
			if(rs!=null)
				rs.close();
			if(conn!=null)
				conn.close();
		}
		return mData;
	}
	
	public static ArrayList latestArticleChunk(String categoryId, int contentCount, String contentIdToAvoid) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LatestContentDTO lcDTO = null;	
		ArrayList dataList = new ArrayList();
		String latestids="0";
		int latestCtr = 0;
		int pcatlength = 0;
		String pcatids[] = null;
	    if(categoryId.indexOf("#") > 0) {
	    	pcatids = categoryId.split("#");
		} else {
			pcatids = new String[1];
    		pcatids[0] = categoryId;
		}
	    
	    if(pcatids==null) {
			pcatids = new String[1];
    		pcatids[0] = "175";
	    }
    	pcatlength = pcatids.length;
		//System.out.println("categoryId-->"+categoryId+"------pcatlength-->"+pcatlength+"--contentIdToAvoid--"+contentIdToAvoid);
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select c.id, c.title,c.sef_url, c.introtext, c.extralarge_image, c.mobile_image as small_image, c.kicker_image,c.kicker_image_alt_text, ";
			if(pcatlength==1)
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
			else if(pcatlength==2) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(pcatlength==3) 
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(pcatlength==4) 
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			//condition checked for India, Sports, Movies and Must Read
			if(pcatlength==1 && (categoryId.trim().equals("114") || categoryId.trim().equals("84") || categoryId.trim().equals("67") || categoryId.trim().equals("175"))) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}
			//condition checked for India#North
			if(pcatlength==2 && categoryId.trim().equals("114#141")) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}

			if(contentIdToAvoid.equals("0"))
				sql = sql + "c.state=1 and c.id=jas.article_id group by c.id order by jas.ordering desc limit "+contentCount;
			else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				sql = sql + "c.state=1 and c.id=jas.article_id and c.id != "+contentIdToAvoid+" group by c.id order by jas.ordering desc limit "+contentCount;
			else
				sql = sql + "c.state=1 and c.id=jas.article_id and c.id not in ("+contentIdToAvoid+") group by c.id order by jas.ordering desc limit "+contentCount;

			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println("Latest Headlines including section page---->"+sql);
			while (rs.next()) {
				if(latestCtr==0)
					latestids = "" + rs.getInt("id");
				else
					latestids += ", " + rs.getInt("id");
					
				lcDTO = new LatestContentDTO();
				lcDTO.setId(rs.getInt("id"));				
				lcDTO.setTitle(rs.getString("title"));
				lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
				lcDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
				lcDTO.setExtraLargeImage(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
				lcDTO.setSmallImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
				lcDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				lcDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				lcDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				lcDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				lcDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl") == null ? "" : rs.getString("curcatsefurl"));		
				lcDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));
				
				lcDTO.setLatestIds(latestids);
				dataList.add(lcDTO);
				latestCtr++;
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java-latestArticleChunk-Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return dataList;
	}
	
	public static ArrayList latestGalleryChunk(int categoryId, int countToFetch, String galleryIdToAvoid) throws Exception
	{
		ArrayList latestGalleryData = new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_pc = null;
		LatestContentDTO lcDTO = null;
		String galleryCheck = "";
		String categoryCheck = "";
		String sql = "";
		
		if(!galleryIdToAvoid.equals("0")) {
			if(galleryIdToAvoid.indexOf(",") > 0)
				galleryCheck = "and gn.id not in ("+galleryIdToAvoid+")";
			else
				galleryCheck = "and gn.id != "+galleryIdToAvoid;
		}

		if(categoryId != 0)
			categoryCheck = " gs.cat_id = "+categoryId + " and ";
			
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			//sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.small_image, gn.photo_category, pc.title, pc.sef_url as cat_sefurl" +
				//	" FROM jos_gallerynames gn, jos_photocategories pc " +
					//" where gn.published=1 "+galleryCheck+" and gn.photo_category=pc.id and " +
						//	"pc.published=1" +categoryCheck+" group by gn.id order by gn.ordering desc limit "+countToFetch;
			sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.small_image, gn.photo_category, pc.title, pc.sef_url as cat_sefurl " +
					"FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs where "+categoryCheck+" gs.cat_id=pc.id and gn.id=gs.content_id and " +
					"gn.published=1 "+galleryCheck+" and pc.published=1 group by gs.content_id order by gs.ordering desc limit "+countToFetch;
			//System.out.print(sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lcDTO = new LatestContentDTO();
				lcDTO.setId(rs.getInt("id"));
				lcDTO.setTitle(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
				lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
				lcDTO.setCurrentCategoryId(rs.getInt("photo_category"));
				lcDTO.setCurrentCategoryTitle(rs.getString("title") == null ? "" : rs.getString("title"));
				lcDTO.setCurrentCategorySefTitle(rs.getString("cat_sefurl") == null ? "" : rs.getString("cat_sefurl"));
				lcDTO.setThumbImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
				lcDTO.setThumbImageAltText(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
				latestGalleryData.add(lcDTO);
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java-latestGalleryChunk-Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return latestGalleryData;
	}

	

	public static ArrayList topstoriesChunk(String primaryCatId, int contentCount, String contentIdToAvoid) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt_com = null;
		ResultSet rs_com = null;
		LatestContentDTO tsDTO = null;	
		ArrayList dataList = new ArrayList();
		String contentIdsToCheck = "";
		String contentIds = "";
		int commentCount=0;
		if(!contentIdToAvoid.equals("0")) {
			if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				contentIdsToCheck = " and c.id != "+contentIdToAvoid+" ";
			else
				contentIdsToCheck = " and c.id not in ("+contentIdToAvoid+") ";
		}
		int pcatlength = 0;
		String pcatids[] = null;
	    if(primaryCatId.indexOf("#") > 0) {
	    	pcatids = primaryCatId.split("#");
		} else {
			pcatids = new String[1];
    		pcatids[0] = primaryCatId;
		}
	    
	    if(pcatids==null) {
			pcatids = new String[1];
    		pcatids[0] = "175";
	    }
    	pcatlength = pcatids.length;

		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select c.id, c.title,c.sef_url, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, " +
					"c.introtext,c.strap_headline,c.mobile_image,DATE_FORMAT(c.modified,'%H:%i')as mtime,c.byline, c.city, c.large_kicker_image_alt_text, c.indiatoday_expert, c.medium_kicker_image, c.extralarge_image,  ";
	
			if(pcatlength==1)
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
			else if(pcatlength==2) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(pcatlength==3) 
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(pcatlength==4) 
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			//condition checked for India, Sports, Movies and Must Read
			if(pcatlength==1 && (primaryCatId.trim().equals("114") || primaryCatId.trim().equals("84") || primaryCatId.trim().equals("67") || primaryCatId.trim().equals("175"))) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}
			//condition checked for India#North
			if(pcatlength==2 && primaryCatId.trim().equals("114#141")) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}

			sql = sql + "c.state=1 and c.id=jas.article_id "+contentIdsToCheck+" group by c.id order by jas.ordering desc limit "+contentCount;

			pstmt = connection.prepareStatement(sql);
			//System.out.print("Top Stories->"+sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(contentIds.equals("")) {
					contentIds = "" + rs.getInt("id");
				} else {
					contentIds += ", " + rs.getInt("id");
				}
				tsDTO = new LatestContentDTO();
				tsDTO.setId(rs.getInt("id"));				
				tsDTO.setTitle(rs.getString("title"));
				tsDTO.setSefTitle(rs.getString("sef_url"));
				tsDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
				tsDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
				tsDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
				tsDTO.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));

				tsDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				tsDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				tsDTO.setLargeImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
				tsDTO.setLargeImageCaption(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
				tsDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
				tsDTO.setExpertComment(rs.getString("indiatoday_expert") == null ? "" : rs.getString("indiatoday_expert"));
				tsDTO.setMediumImage(rs.getString("medium_kicker_image")==null ? "" : rs.getString("medium_kicker_image"));
				tsDTO.setExtraLargeImage(rs.getString("extralarge_image")==null ? "" : rs.getString("extralarge_image"));
				tsDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				tsDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				tsDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl"));		
				tsDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));	
				tsDTO.setMobileImage(rs.getString("mobile_image") == null ? "" : rs.getString("mobile_image"));	
				tsDTO.setLatestIds(contentIds);
				try {
					sql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1";
					pstmt_com = connection.prepareStatement(sql);
					rs_com = pstmt_com.executeQuery();
					while (rs_com.next()) {
						commentCount = rs_com.getInt("ccount");				
					}
				}catch(Exception c){
					System.out.println("IT CommonFunctions topstories-comment Exception is :" + c);
				}
				tsDTO.setCommentCount(commentCount);
				dataList.add(tsDTO);
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions latestHeadlinesWGBy Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return dataList;
	}

	public static ArrayList relatedContent(String contentids, String contenttype) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		RelatedContentDTO rcDTO = null;
	    ArrayList relatedContent = new ArrayList();
	    if(contenttype.trim().length() == 0) {
	    	contenttype = "text";
	    }
	    //variable check to save query execution time to fetch Related for single story
	    String contentIdCondition = "";
	    if(!contentids.contains(","))
	    	contentIdCondition = "= "+contentids;
	    else
	    	contentIdCondition = "in ("+contentids+")";

		//System.out.println("Related Content - " + contentids + " -- contentIdCondition" + contentIdCondition);
		try {
			Dbconnection dbConn = Dbconnection.getInstance();
			conn  = dbConn.getConnection();
			String selectStmt = "SELECT c.kicker_image,r.article_id, r.related_article_id, r.related_title,  r.related_type, r.featured, c.sef_url" +
					" FROM jos_related_stories r,jos_content c , jos_article_section a " +
					"where r.article_id "+contentIdCondition+" and r.content_type = '"+contenttype+"' and r.related_type != 'photo' " +
					"and c.state =1 and c.id=r.related_article_id and c.id=a.article_id group by c.id " +
					"order by r.related_type, r.related_order";

			System.out.println("Related Query - " + selectStmt);
			pstmt = conn.prepareStatement(selectStmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rcDTO = new RelatedContentDTO();
				rcDTO.setContentId(rs.getInt("article_id"));
				rcDTO.setRelatedContentId(rs.getInt("related_article_id"));
				rcDTO.setRelatedContentSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
				rcDTO.setRelatedTitle(rs.getString("related_title")==null?"":rs.getString("related_title"));
				rcDTO.setRelatedType(rs.getString("related_type")==null?"":rs.getString("related_type"));
				rcDTO.setRelatedThumbImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
				rcDTO.setFeatured(rs.getInt("featured"));
				relatedContent.add(rcDTO);
			}
			rs = null;;
			pstmt = null;

			String selectStmtP = "SELECT c.small_image,r.article_id, r.related_article_id, r.related_title, r.related_type, r.featured, c.sef_url " +
					"FROM jos_related_stories r, jos_gallerynames c " +
					"where r.article_id "+contentIdCondition+" and r.content_type = '"+contenttype+"' and r.related_type = 'photo' and " +
					"c.published =1 and c.id=r.related_article_id group by c.id order by r.related_type, r.related_order"; 

			System.out.println("Related Query P - " + selectStmtP);
			pstmt = conn.prepareStatement(selectStmtP);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rcDTO = new RelatedContentDTO();
				rcDTO.setContentId(rs.getInt("article_id"));
				rcDTO.setRelatedContentId(rs.getInt("related_article_id"));
				rcDTO.setRelatedContentSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
				rcDTO.setRelatedTitle(rs.getString("related_title")==null?"":rs.getString("related_title"));
				rcDTO.setRelatedType(rs.getString("related_type")==null?"":rs.getString("related_type"));
				rcDTO.setRelatedThumbImage(rs.getString("small_image")==null?"":rs.getString("small_image"));
				rcDTO.setFeatured(rs.getInt("featured"));
				relatedContent.add(rcDTO);
			}
		} catch(SQLException sqls) {
			System.out.println("IT CommenFunctions Related Content SQLException (CommonFunctions.java) -> " + sqls);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return relatedContent;
	}

	public static ArrayList relatedContentTopStories(String contentids, String contenttype) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		RelatedContentDTO rcDTO = null;
		String selectStmtP1 = "";
	    ArrayList relatedContent = new ArrayList();
	    if(contenttype.trim().length() == 0) {
	    	contenttype = "text";
	    }
	    //variable check to save query execution time to fetch Related for single story
	    String contentIdCondition = "";
	    if(!contentids.contains(","))
	    	contentIdCondition = "= "+contentids;
	    else
	    	contentIdCondition = "in ("+contentids+")";

		//System.out.println("Related Content - " + contentids + " -- contentIdCondition" + contentIdCondition);
		try {
			Dbconnection dbConn = Dbconnection.getInstance();
			conn  = dbConn.getConnection();
			String selectStmt = "SELECT article_id, related_article_id, related_title, related_type, featured " +
					"FROM jos_related_stories where article_id "+contentIdCondition+" and " +
					"content_type = '"+contenttype+"' group by article_id, related_article_id order by related_order asc";

			//System.out.println("Related Query Top Stories- " + selectStmt);
			pstmt = conn.prepareStatement(selectStmt);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				rcDTO = new RelatedContentDTO();
				rcDTO.setContentId(rs.getInt("article_id"));
				rcDTO.setRelatedContentId(rs.getInt("related_article_id"));
				rcDTO.setRelatedTitle(rs.getString("related_title")==null?"":rs.getString("related_title"));
				rcDTO.setRelatedType(rs.getString("related_type")==null?"":rs.getString("related_type"));
				rcDTO.setFeatured(rs.getInt("featured"));

				try {
					if(rs.getString("related_type").equals("photo")) {
						selectStmtP1 = "SELECT sef_url FROM jos_gallerynames where id=?";
						//System.out.println("Related Query Gallery  - " + selectStmtP1);
						pstmt1 = conn.prepareStatement(selectStmtP1);
						pstmt1.setInt(1, rs.getInt("related_article_id"));
						rs1 = pstmt1.executeQuery();
						while (rs1.next()) {
							rcDTO.setRelatedContentSefTitle(rs1.getString("sef_url")==null?"":rs1.getString("sef_url"));
						}
					} else if(rs.getString("related_type").equals("text") || rs.getString("related_type").equals("video")) {
						selectStmtP1 = "SELECT sef_url FROM jos_content where id=?";
						//System.out.println("Related Query Text/Video  - " + selectStmtP1);
						pstmt1 = conn.prepareStatement(selectStmtP1);
						pstmt1.setInt(1, rs.getInt("related_article_id"));
						rs1 = pstmt1.executeQuery();
						while (rs1.next()) {
							rcDTO.setRelatedContentSefTitle(rs1.getString("sef_url")==null?"":rs1.getString("sef_url"));
						}
					}
				} catch(Exception ex) {
					System.out.println("relatedContentTopStories Content SQLException (CommonFunctions.java) -> " + ex.toString());
				} finally {
					if(rs1!=null)
						rs1.close();
					if(pstmt1!=null)
						pstmt1.close();
				}
				relatedContent.add(rcDTO);
			}
		} catch(SQLException sqls) {
			System.out.println("IT CommenFunctions Related Content SQLException (CommonFunctions.java) -> " + sqls);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();
		}
		return relatedContent;
	}

	public static ArrayList latestVideoChunk(String categoryId, int contentCount, String contentIdToAvoid) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LatestContentDTO lcDTO = null;	
		ArrayList dataList = new ArrayList();
		String latestids="0";
		int latestCtr = 0;
		int pcatlength = 0;
		String pcatids[] = null;
	    if(categoryId.indexOf("#") > 0) {
	    	pcatids = categoryId.split("#");
		} else {
			pcatids = new String[1];
    		pcatids[0] = categoryId;
		}
	    
	    if(pcatids==null) {
			pcatids = new String[1];
    		pcatids[0] = "86";
	    }
    	pcatlength = pcatids.length;
		//System.out.println("categoryId-->"+categoryId+"------pcatlength-->"+pcatlength+"--contentIdToAvoid--"+contentIdToAvoid);
		
	    String contentIdCondition = "";
	    if(!contentIdToAvoid.equals("0")) {
		    if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				contentIdCondition = " and c.id != "+contentIdToAvoid+" ";
			else
				contentIdCondition = " and c.id not in ("+contentIdToAvoid+") ";
		}
	    String videoCutoffCheck = "";
		if(pcatlength==1 && pcatids[0].equals("86"))
			videoCutoffCheck = " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+Constants.VIDEO_CUTOFF_DAYS_SECTION+" DAY) and ";
		else if(pcatlength==2 && pcatids[1].equals("42")) 
			videoCutoffCheck = " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+Constants.VIDEO_CUTOFF_DAYS+" DAY) and ";

		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			
			String sql = "select c.id, c.title,c.sef_url, c.kicker_image,c.kicker_image_alt_text, ";
			if(pcatlength==1)
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath " +
						"from jos_content c, jos_article_section jas, jos_sections js,jos_videogallerynames v where jas.section_id="+pcatids[0]+" and " +
								"jas.section_id=js.id and js.published=1 and " + videoCutoffCheck;
			else if(pcatlength==2) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath " +
						"from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js,jos_videogallerynames v where " +
						"jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and " + videoCutoffCheck;
	
				sql = sql + "c.state=1 and c.id=jas.article_id and c.id=v.contentid "+contentIdCondition+" group by jas.article_id order by jas.ordering desc limit "+contentCount;

			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println("Latest Videos---->"+sql);
			while (rs.next()) {
				if(latestCtr==0)
					latestids = "" + rs.getInt("id");
				else
					latestids += ", " + rs.getInt("id");
					
				lcDTO = new LatestContentDTO();
				lcDTO.setId(rs.getInt("id"));				
				lcDTO.setTitle(rs.getString("title"));
				lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
				lcDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				lcDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				lcDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				lcDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				lcDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl") == null ? "" : rs.getString("curcatsefurl"));		
				lcDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
				lcDTO.setLatestIds(latestids);
				dataList.add(lcDTO);
				latestCtr++;
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java-latestVideoChunk-Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return dataList;
	}

	public static ArrayList staticContent(String staticTitle) throws Exception {
		Connection connection = null;
		PreparedStatement pSelectStmt=null;
		ResultSet rs = null;
		StaticContentDTO staticDTO = null;
		ArrayList datalist = new ArrayList();
		//System.out.println("StaticContent parameterized Class for -> " + staticTitle);
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select title, introtext from jos_content where title = ? and state=1";
			pSelectStmt = connection.prepareStatement(sql);
			pSelectStmt.setString(1, staticTitle);
			rs = pSelectStmt.executeQuery();
			//System.out.println(sql);
			while (rs.next()) {
				staticDTO = new StaticContentDTO();
				staticDTO.setStaticContentTitle(rs.getString("title")==null?"":rs.getString("title"));
				staticDTO.setStaticContentText(rs.getString("introtext")==null?"":rs.getString("introtext"));
				datalist.add(staticDTO);
			}
		} catch (Exception e) {
			System.out.println("IT staticContent commonfunctions Exception is ;" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pSelectStmt!=null)
				pSelectStmt.close();
			if(connection!=null)
				connection.close();
		}
		return datalist;
	}
	
	public static ArrayList staticContent(String staticTitle, int staticContentSate) throws Exception {
		Connection connection = null;
		PreparedStatement pSelectStmt=null;
		ResultSet rs = null;
		StaticContentDTO staticDTO = null;
		ArrayList datalist = new ArrayList();
		//System.out.println("StaticContent parameterized Class for -> " + staticTitle);
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select title, introtext from jos_staticcontent where title = ? and state=?";
			pSelectStmt = connection.prepareStatement(sql);
			pSelectStmt.setString(1, staticTitle);
			pSelectStmt.setInt(2, staticContentSate);
			rs = pSelectStmt.executeQuery();
			System.out.println(sql);
			while (rs.next()) {
				staticDTO = new StaticContentDTO();
				staticDTO.setStaticContentTitle(rs.getString("title")==null?"":rs.getString("title"));
				staticDTO.setStaticContentText(rs.getString("introtext")==null?"":rs.getString("introtext"));
				datalist.add(staticDTO);
			}
		} catch (Exception e) {
			System.out.println("IT staticContent commonfunctions Exception is ;" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pSelectStmt!=null)
				pSelectStmt.close();
			if(connection!=null)
				connection.close();
		}
		return datalist;
	}

	public static String sectionInfo(String id, String templateName) throws Exception {
		Connection connection = null;
		PreparedStatement pSelectStmt=null;
		ResultSet rs = null;
		String sectionInfo = "";
		String sql = "";
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			if(id != null && id.trim().length() > 0) {
				if(templateName.equals("section")) {
					sql = "SELECT id, issue_free FROM jos_sections where id="+id;
				} else if(templateName.equals("category")) {
					sql = "SELECT sec.id, sec.issue_free FROM jos_categories cat, jos_sections sec where cat.id="+id+" and sec.id=cat.section";
				} else if(templateName.equals("subcategory")) {
					sql = "SELECT sec.id, sec.issue_free FROM jos_subcategories scat, jos_sections sec where scat.id="+id+" and sec.id=scat.section";
				} else if(templateName.equals("subsubcategory")) {
					sql = "SELECT sec.id, sec.issue_free FROM jos_sub_subcategories scat, jos_sections sec where scat.id="+id+" and sec.id=scat.section";
				} else if(templateName.equals("story")) {
					if(id.indexOf("#")>0)
						sql = "SELECT id, issue_free FROM jos_sections where id="+id.substring(0, id.indexOf("#"));
					else
						sql = "SELECT id, issue_free FROM jos_sections where id="+id;
				} else {
					sql = "SELECT id, issue_free FROM jos_sections where id=4";
				}
				pSelectStmt = connection.prepareStatement(sql);
				rs = pSelectStmt.executeQuery();
				while (rs.next()) {
					sectionInfo = rs.getString("id")+"#"+rs.getString("issue_free");
				}
			} else {
				sectionInfo="4#1";
			}
			
			//System.out.println("sectionInfo->id="+sectionInfo+"--template="+templateName+"--query="+sql);
		} catch (Exception e) {
			System.out.println("IT--sectionINfo - CommonFunctions Exception is ;" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pSelectStmt!=null)
				pSelectStmt.close();
			if(connection!=null)
				connection.close();
		}
		return sectionInfo;
	}
	
	
	//Depricated Functions used in old specials only
	public static ArrayList relatedContent(String contentids) throws Exception {
		//System.out.println("Related Content - " + contentids);
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;
		RelatedStoryBean rsbean=null;
	    ArrayList relateds = new ArrayList();

		//String updatedTime = "";
		try {
			Dbconnection conn1 = null;
			conn1 = Dbconnection.getInstance();
			conn  = conn1.getConnection();

			String selectStmt = "SELECT s.article_id, s.related_article_id, s.related_title, s.related_section_id, s.related_category, s.related_type, s.related_order,s.featured, c.sef_url as sef_url, sec.title as section, sec.sef_url as section_sefurl,c.article_icon,c.article_icon_img FROM jos_related_stories s,jos_content c ,jos_sections sec where  c.state=1 and sec.id=s.related_section_id  and s.article_id in("+contentids+ ") and c.id=s.related_article_id and related_type != 'photo' order by s.related_type, s.related_order";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectStmt);
			while (rs.next()) {
				rsbean = new RelatedStoryBean();
				rsbean.setArticleId(rs.getInt("article_id"));
				rsbean.setRelatedArticleId(rs.getInt("related_article_id"));
				rsbean.setRelatedTitle(rs.getString("related_title"));
				rsbean.setRelatedSection(rs.getInt("related_section_id"));
				rsbean.setRelatedSubSection(rs.getInt("related_category"));
				rsbean.setRelatedTypes(rs.getString("related_type"));
				rsbean.setRelatedOrder(rs.getInt("related_order"));
				rsbean.setSefUrl(rs.getString("sef_url"));
				rsbean.setRelatedSectionName(rs.getString("section"));
				rsbean.setRelatedSectionSefTitle(rs.getString("section_sefurl"));
				rsbean.setArticleIcon(rs.getInt("article_icon"));
				rsbean.setArticleIconImage(rs.getString("article_icon_img"));
				rsbean.setFeatured(rs.getInt("featured"));
				relateds.add(rsbean);
			}
			//System.out.println("Main content id(s) - " + contentids);
			rs = null;;
			stmt = null;

			String selectStmtP = "SELECT s.article_id, s.related_article_id, s.related_title, s.related_section_id, s.related_category, s.related_type, s.related_order,s.featured,  c.sef_url as sef_url, sec.title as section FROM jos_related_stories s,jos_gallerynames c ,jos_photocategories sec where  c.published=1 and sec.id=s.related_section_id  and s.article_id in("+contentids+ ") and c.id=s.related_article_id  and related_type = 'photo' order by s.related_type, s.related_order";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(selectStmtP);
			while (rs.next()) {
				rsbean = new RelatedStoryBean();
				rsbean.setArticleId(rs.getInt("article_id"));
				rsbean.setRelatedArticleId(rs.getInt("related_article_id"));
				rsbean.setRelatedTitle(rs.getString("related_title"));
				rsbean.setRelatedSection(rs.getInt("related_section_id"));
				rsbean.setRelatedSubSection(rs.getInt("related_category"));
				rsbean.setRelatedTypes(rs.getString("related_type"));
				rsbean.setRelatedOrder(rs.getInt("related_order"));
				rsbean.setSefUrl(rs.getString("sef_url"));
				rsbean.setRelatedSectionName(rs.getString("section"));
				rsbean.setFeatured(rs.getInt("featured"));
				relateds.add(rsbean);
			}
			//System.out.println("Main content id(s) - " + contentids);

			
			rs.close();
			stmt.close();
			conn.close();
		} catch(SQLException sqls) {
			System.out.println("IT--Related Content SQLException (CommonFunctions.java) -> " + sqls);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}
		//System.out.println("Sanjay");
		return relateds;
		
		
	}

	//to remove later once above functions used
	public static String articleURL(int articleId, String sectionSefTitle, String articleSefTitle, int pageNum) {
		String articleUrl = "Story/" + articleId + "/" + sectionSefTitle + "/" + articleSefTitle;
		if(pageNum > 1)
			articleUrl = articleUrl + "?page=" + pageNum;
		//System.out.println("articleUrl->"+articleUrl);
		return articleUrl;
	}


	public static String sectionURL(int sectionId, String sectionSefTitle, int pageNum) {
		String sectionUrl = "section/" + sectionId + "/" + pageNum + "/" + sectionSefTitle;
		if(sectionSefTitle.indexOf(".html") < 0)
			sectionUrl = sectionUrl + ".html";
		return sectionUrl;
	}

	public static String categoryURL(int categoryId, String categorySefTitle, int pageNum) {
		String finalSefTitle = categorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String categoryUrl = "category/" + finalSefTitle + "/" + pageNum + "/" + categoryId + ".html";
		return categoryUrl;
	}

	public static String subCategoryURL(int subCategoryId, String subCategorySefTitle, int pageNum) {
		String finalSefTitle = subCategorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String subCategoryUrl = "subcategory/" + finalSefTitle + "/" + pageNum + "/" + subCategoryId + ".html";
		return subCategoryUrl;
	}

	public static String subSubCategoryURL(int subSubCategoryId, String subSubCategorySefTitle, int pageNum) {
		String finalSefTitle = subSubCategorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String subSubCategoryUrl = "subsubcategory/" + finalSefTitle + "/" + pageNum + "/" + subSubCategoryId + ".html";
		return subSubCategoryUrl;
	}

	public static String galleryURL(int galleryId, int pageNum, String gallerySefTitle) {
		String finalSefTitle = gallerySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String galleryUrl = "gallery/" + finalSefTitle + "/" + pageNum + "/" + galleryId + ".html";
		return galleryUrl;
	}

	public static String galleryListURL(int galleryId, int pageNum, String gallerySefTitle) {
		String finalSefTitle = gallerySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String galleryListUrl = "gallerylist/" + finalSefTitle + "/" + pageNum + "/" + galleryId + ".html";
		return galleryListUrl;
	}
	
	public static ArrayList latestContentArticle(String categoryId, int contentCount, String contentIdToAvoid) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LatestArticleDTO laDTO = null;	
		ArrayList dataList = new ArrayList();
		String latestids="0";
		int latestCtr = 0;
		
		int pcatlength = 0;
		String pcatids[] = null;
		//categoryId = categoryId.replaceAll("#","~");
	    if(categoryId.indexOf("#") > 0) {
	    	pcatids = categoryId.split("#");
		} else {
			pcatids = new String[1];
    		pcatids[0] = categoryId;
		}
	    
	    if(pcatids==null) {
			pcatids = new String[1];
    		pcatids[0] = "175";
	    }
    	pcatlength = pcatids.length;
		//System.out.println("categoryId-->"+categoryId+"------pcatlength-->"+pcatlength+"--contentIdToAvoid--"+contentIdToAvoid);
    	

		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select c.id as articleId, c.title as art_title,c.sef_url as sef_url,c.article_icon,c.article_icon_img, " +
			"c.strap_headline,c.kicker_image,c.kicker_image_alt_text,c.mobile_image as small_image, c.introtext, c.large_kicker_image, c.kicker_image_caption, " +
			"c.byline, c.city, DATE_FORMAT(c.created,'%M %e, %Y') as created, DATE_FORMAT(c.modified,'%M %e, %Y') as modified, ";
			sql = sql + "js.title as sectionname, js.id as sectionid, js.sef_url as sectionsefurl, ";
	
			if(pcatlength==1)
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
			else if(pcatlength==2) 
				//sql = sql + "jc.title as sectionname, jc.id as sectionid, jc.sef_url as sectionsefurl from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(pcatlength==3) 
				//sql = sql + "jsc.title as sectionname, jsc.id as sectionid, jsc.sef_url as sectionsefurl from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.section_id="+pcatids[0]+" and jas.cat_id="+pcatids[1]+" and jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(pcatlength==4) 
				//sql = sql + "jssc.title as sectionname, jssc.id as sectionid, jssc.sef_url as sectionsefurl from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.section_id="+pcatids[0]+" and jas.cat_id="+pcatids[1]+" and jas.subcat_id="+pcatids[2]+" and jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			if(contentIdToAvoid.equals("0"))
				sql = sql + "c.state=1 and c.id=jas.article_id group by jas.article_id order by jas.ordering desc limit "+contentCount;
			else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				sql = sql + "c.state=1 and c.id=jas.article_id and c.id != "+contentIdToAvoid+" group by jas.article_id order by jas.ordering desc limit "+contentCount;
			else
				sql = sql + "c.state=1 and c.id=jas.article_id and c.id not in ("+contentIdToAvoid+") group by jas.article_id order by jas.ordering desc limit "+contentCount;

	    	
	    	//sql = sql + " group by c.id ORDER BY s.ordering DESC limit "+contentCount;
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println("Latest Headlines---->"+sql);
			while (rs.next()) {
				if(latestCtr==0)
					latestids = "" + rs.getInt("articleId");
				else
					latestids += ", " + rs.getInt("articleId");
					
				laDTO = new LatestArticleDTO();
				laDTO.setArticleId(rs.getInt("articleId"));				
				laDTO.setArticleTitle(rs.getString("art_title"));
				laDTO.setArticleSefTitle(rs.getString("sef_url"));
				laDTO.setSectionId(rs.getInt("sectionid"));		
				laDTO.setSectionTitle(rs.getString("sectionname"));		
				laDTO.setSectionSefTitle(rs.getString("sectionsefurl"));		
				laDTO.setArticleIcon(rs.getInt("article_icon"));
				laDTO.setArticleIconImage(rs.getString("article_icon_img"));
				laDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
				laDTO.setKickerImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				laDTO.setLargeKickerImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
				laDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text"));
				laDTO.setArticleDesc(rs.getString("introtext"));
				laDTO.setByline(rs.getString("byline"));
				laDTO.setCity(rs.getString("city"));
				laDTO.setCreatedDate(rs.getString("created"));
				laDTO.setUpdatedDate(rs.getString("modified"));
				laDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				laDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				laDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl"));		
				laDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
				laDTO.setSmallImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
				laDTO.setLargeKickerImageCaption(rs.getString("kicker_image_caption") == null ? "" : rs.getString("kicker_image_caption"));

				
				laDTO.setLatestids(latestids);
				dataList.add(laDTO);
				latestCtr++;
			}
		} catch (Exception e) {
			System.out.println("IT--CommonFunctions LatestHeadlines Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return dataList;
	}



	public static ArrayList mustSeeToDelete(int categoryId, int countToFetch, String galleryIdToAvoid) throws Exception
	{
		ArrayList mustSeeData = new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_pc = null;
		LatestContentDTO lcDTO = null;
		String galleryCheck = "";
		String sql = "";
		
		if(!galleryIdToAvoid.equals("0")) {
			if(galleryIdToAvoid.indexOf(",") > 0)
				galleryCheck = "and gn.id not in ("+galleryIdToAvoid+")";
			else
				galleryCheck = "and gn.id != "+galleryIdToAvoid;
		}

		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			if(categoryId==0) {
				sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image" +
					" FROM jos_gallerynames gn, jos_photocategories pc " +
					" where gn.featured_page=1 and gn.published=1 "+galleryCheck+" and pc.id=gn.photo_category and " +
							"pc.published=1 group by gn.id order by gn.featured_ordering desc limit "+countToFetch;
			} else {
				sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image" +
				" FROM jos_gallerynames gn, jos_photocategories pc " +
				" where gn.photo_category="+categoryId+" and gn.published=1 "+galleryCheck+" and pc.id=gn.photo_category and " +
						"pc.published=1 group by gn.id order by gn.ordering desc limit "+countToFetch;
			}
			//System.out.print("mustsee->"+sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lcDTO = new LatestContentDTO();
				lcDTO.setId(rs.getInt("id"));
				lcDTO.setTitle(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
				lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
				lcDTO.setThumbImage(rs.getString("thumb_image") == null ? "" : rs.getString("thumb_image"));
				lcDTO.setThumbImageAltText(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
				mustSeeData.add(lcDTO);
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java-mustSeeToDelete-Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return mustSeeData;
	}

	public static String LikeDislike(int articleId,String content_type )throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dbconnection connect = null;
		String LikeDislikeStr="";
		int upcount=0;
		int downcount=0;
		int totalcount=0;
		try {
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "";
			sql = "SELECT rating_sum,rating_count FROM jos_content_rating where content_id="+articleId+" and content_type ='"+content_type+"'";
			//System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.wasNull()) {
				upcount=0;
				downcount=0;
			}
			if (rs.next()) {
				upcount = rs.getInt("rating_sum");
				downcount = rs.getInt("rating_count");
			}
			totalcount=upcount+downcount;
			LikeDislikeStr=upcount+"-"+downcount+"-"+totalcount;
			//System.out.println("sss"+LikeDislikeStr);
		} catch(Exception e) {
			System.out.println("IT--Like-dislike Exception in common function is :" + e);
			//System.out.println("india today eee"+LikeDislikeStr);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return LikeDislikeStr;
	}

	public static String LikeDislike(int articleId,String content_type, String part_id )throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Dbconnection connect = null;
		String LikeDislikeStr="";
		int upcount=0;
		int downcount=0;
		int totalcount=0;
		try {
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "";
			sql = "SELECT rating_sum,rating_count FROM jos_content_rating where content_id="+articleId+" and content_type ='"+content_type+"' and highlight_sno ='"+part_id+"'";
			//System.out.println(sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.wasNull()) {
				upcount=0;
				downcount=0;
			}
			if (rs.next()) {
				upcount = rs.getInt("rating_sum");
				downcount = rs.getInt("rating_count");
			}
			totalcount=upcount+downcount;
			LikeDislikeStr=upcount+"-"+downcount+"-"+totalcount;
			//System.out.println("sss"+LikeDislikeStr);
		} catch(Exception e) {
			System.out.println("IT-- Like-dislike Exception in common function is :" + e);
			//System.out.println("india today eee"+LikeDislikeStr);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return LikeDislikeStr;
	}

	
public static String hltUpdatedTimetobeRemoved() throws Exception {
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs =null;

		String updatedTime = "";
		
		try {
			Dbconnection conn1 = null;
			String sql = "SELECT date_format(updated_time, '%W, %M %e, %Y | %H:%i IST') as updated_time FROM jos_updatetime order by id desc limit 1";
			conn1 = Dbconnection.getInstance();
			conn  = conn1.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				updatedTime = rs.getString("updated_time");
			}
			//System.out.println("Updated Time--->");
		} catch(Exception e) {
			System.out.println("IT time Exception in common function is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		}
		return updatedTime;
	}

public static ArrayList mustwatch(String categoryId, int contentCount, String contentIdToAvoid) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	LatestContentDTO lcDTO = null;	
	ArrayList dataList = new ArrayList();
	int pcatlength = 0;
	String pcatids[] = null;
    if(categoryId.indexOf("#") > 0) {
    	pcatids = categoryId.split("#");
	} else {
		pcatids = new String[1];
		pcatids[0] = categoryId;
	}
    
    if(pcatids==null) {
		pcatids = new String[1];
		pcatids[0] = "86";
    }
	pcatlength = pcatids.length;
	
    String contentIdCondition = "";
    if(!contentIdToAvoid.equals("0")) {
	    if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
			contentIdCondition = " and c.id != "+contentIdToAvoid+" ";
		else
			contentIdCondition = " and c.id not in ("+contentIdToAvoid+") ";
    }
	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		
		String sql = "select c.id, c.title,c.sef_url, c.mobile_image as small_image,c.kicker_image, c.kicker_image_alt_text, c.medium_kicker_image, ";
		//String sql = "select c.id, c.title,c.sef_url, c.mobile_image as small_image,c.kicker_image_alt_text, ";
		if(pcatlength==1)
			sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath " +
					"from jos_content c, jos_article_section jas, jos_sections js,jos_videogallerynames v where jas.section_id="+pcatids[0]+" and " +
							"jas.section_id=js.id and js.published=1 and ";
		else if(pcatlength==2) 
			sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath " +
					"from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js,jos_videogallerynames v where " +
					"jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";

			sql = sql + "c.state=1 and c.id=jas.article_id and c.id=v.contentid "+contentIdCondition+" group by jas.article_id order by jas.ordering desc limit "+contentCount;

		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		//System.out.println("Latest Videos---->"+sql);
		while (rs.next()) {
			lcDTO = new LatestContentDTO();
			lcDTO.setId(rs.getInt("id"));				
			lcDTO.setTitle(rs.getString("title"));
			lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
//			lcDTO.setThumbImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
			lcDTO.setThumbImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
			lcDTO.setSmallImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
			lcDTO.setMediumImage(rs.getString("medium_kicker_image")==null ? "" : rs.getString("medium_kicker_image"));
			lcDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
			lcDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
			lcDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
			lcDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl") == null ? "" : rs.getString("curcatsefurl"));		
			lcDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
			dataList.add(lcDTO);
		}
	} catch (Exception e) {
		System.out.println("IT CommonFunctions.java-latestVideoChunk-Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return dataList;
}

public static ArrayList gallerySlideShow(int galleryId, int countToFetch) throws Exception
{
	ArrayList jssData = new ArrayList();
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	LatestContentDTO lcDTO = null;
	String sql = "";
	
	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
			sql = "SELECT pg.photo_name, pg.image_alt_text, gn.id, gn.sef_url, gn.Gallery_name FROM jos_photogallery pg, jos_gallerynames gn" +
					" where gn.id=? and gn.published=1 and pg.enable=1 and gn.id=pg.gallery_id group by pg.id order by pg.ordering desc, pg.id desc limit ?";
		pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1,galleryId);
		pstmt.setInt(2,countToFetch);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			lcDTO = new LatestContentDTO();
			lcDTO.setId(rs.getInt("id"));
			lcDTO.setTitle(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
			lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			lcDTO.setThumbImage(rs.getString("photo_name") == null ? "" : rs.getString("photo_name"));
			lcDTO.setThumbImageAltText(rs.getString("image_alt_text") == null ? "" : rs.getString("image_alt_text"));
			jssData.add(lcDTO);
		}
	} catch (Exception e) {
		System.out.println("IT CommonFunctions.java-jQuerySlideShow-Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return jssData;
}

public static ArrayList staticContentAll(int staticContentState) throws Exception {
	Connection connection = null;
	PreparedStatement pSelectStmt=null;
	ResultSet rs = null;
	StaticContentDTO staticDTO = null;
	ArrayList datalist = new ArrayList();
	int featuredTab=1;
	String sql = "";
	//System.out.println("StaticContent parameterized Class for -> " + staticTitle);
	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		sql = "SELECT magazine_featured FROM jos_breakingnews";
		pSelectStmt = connection.prepareStatement(sql);
		rs = pSelectStmt.executeQuery();
		while (rs.next()) {
			featuredTab = rs.getInt("magazine_featured");
		}
		pSelectStmt=null;
		rs=null;
		if(staticContentState==1)
			sql = "select title, introtext from jos_staticcontent where state="+staticContentState;
		else
			sql = "select title, introtext from jos_staticcontent";
		pSelectStmt = connection.prepareStatement(sql);
		rs = pSelectStmt.executeQuery();
		System.out.println(sql);
		while (rs.next()) {
			staticDTO = new StaticContentDTO();
			staticDTO.setStaticContentTitle(rs.getString("title")==null?"":rs.getString("title"));
			staticDTO.setStaticContentText(rs.getString("introtext")==null?"":rs.getString("introtext"));			staticDTO.setStaticContentText(rs.getString("introtext")==null?"":rs.getString("introtext"));
			staticDTO.setFeatured(featuredTab);
			datalist.add(staticDTO);
		}
	} catch (Exception e) {
		System.out.println("IT--staticContentAll commonfunctions Exception is ;" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pSelectStmt!=null)
			pSelectStmt.close();
		if(connection!=null)
			connection.close();
	}
	return datalist;
}

public static ArrayList sliderChunk() throws Exception
{
	Connection conn = null;
	ResultSet rs = null;
	PreparedStatement pstmt = null;

	ResultSet rs1 = null;
	PreparedStatement pstmt1 = null;
	
	ResultSet rs_com = null;
	PreparedStatement pstmt_com = null;

	LatestContentDTO lDTO = null;
	ArrayList mostPopularData = new ArrayList();
	int pcatlength = 0;
	String pcatids[] = null;
	String categoryId = "175";
	String sql=null;
	int commentCount=0;
	
	String[] sliderContent = null;
	try
	{
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		conn  = connect.getConnection();
		sql = "SELECT slider_content FROM jos_breakingnews";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			sliderContent = rs.getString("slider_content").split("#");
		}
		pstmt=null;
		rs=null;
		sql="";
		if(sliderContent!=null && sliderContent.length>0) {
			for(int i=0; i < sliderContent.length; i++) {
			//System.out.println("sliderContent->"+sliderContent[i]);
			}
		}
		
		if(sliderContent!=null && sliderContent.length>0) {
			for(int i=0; i < sliderContent.length; i++) {
			//System.out.println("sliderContent->"+sliderContent[i]);
				if(sliderContent[i].substring(0,5).equals("story")) {
					sql = "SELECT c.id, c.title, c.sef_url, c.mobile_image, c.kicker_image_alt_text, c.primary_category	" +
							"FROM jos_content c, jos_article_section a, jos_sections s " +
							"WHERE c.id="+sliderContent[i].substring(5)+" and c.id=a.article_id and s.id=a.section_id and c.state=1 and " +
							"s.published=1 group by a.article_id";
					//System.out.println("Slider Story->"+sql);
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while (rs.next()){
						lDTO = new LatestContentDTO();
						lDTO.setId(rs.getInt("id"));
						lDTO.setTitle(rs.getString("title"));
						lDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
						lDTO.setThumbImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
						lDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
						lDTO.setContentType("story");
						categoryId = rs.getString("primary_category");
					    if(categoryId.indexOf("#") > 0) {
					    	pcatids = categoryId.split("#");
						} else {
							pcatids = new String[1];
				    		pcatids[0] = categoryId;
						}
					    
					    if(pcatids==null) {
							pcatids = new String[1];
				    		pcatids[0] = "175";
					    }
				    	pcatlength = pcatids.length;
						String sql1 = "select ";
						if(pcatlength==1)
							sql1 = sql1 + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_sections js where js.id="+pcatids[0]+" and js.published=1";
						else if(pcatlength==2) 
							sql1 = sql1 + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_sections js where jc.id="+pcatids[1]+" and jc.published=1 and js.published=1 and js.id=jc.section";
						else if(pcatlength==3) 
							sql1 = sql1 + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_sections js, jos_categories jc where jsc.id="+pcatids[2]+" and jsc.published=1 and  jc.id=jsc.category and js.id=jsc.section and jc.published=1 and js.published=1";
						else if(pcatlength==4) 
							sql1 = sql1 + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_sections js, jos_categories jc, jos_subcategories jsc where jssc.id="+pcatids[3]+" and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jssc.sub_category=jsc.id and jc.id=jsc.category and js.id=jsc.section";
	
						pstmt1 = conn.prepareStatement(sql1);
						rs1 = pstmt1.executeQuery();
						while (rs1.next()) {
							lDTO.setCurrentCategoryId(rs1.getInt("curcatid"));		
							lDTO.setCurrentCategoryTitle(rs1.getString("curcatname"));		
							lDTO.setCurrentCategorySefTitle(rs1.getString("curcatsefurl") == null ? "" : rs1.getString("curcatsefurl"));		
							lDTO.setCurrentCategoryPageURL(rs1.getString("curcatpath") == null ? "" : rs1.getString("curcatpath"));		
							lDTO.setPrimaryCategoryLength(pcatlength);
						}
				    	
						try {
							String ssql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='story'";
							pstmt_com = conn.prepareStatement(ssql);
							rs_com = pstmt_com.executeQuery();
							while (rs_com.next()) {
								commentCount = rs_com.getInt("ccount");				
							}
						}catch(Exception c){
							System.out.println("CommonFunctions mostpopular-comment Exception is :" + c);
						}
						lDTO.setCommentCount(commentCount);
	
				    	mostPopularData.add(lDTO);
					}
					rs=null;
					pstmt=null;
					sql="";
					rs1=null;
					pstmt1=null;
					rs_com=null;
					pstmt_com=null;
				}
				if(sliderContent[i].substring(0,5).equals("video")) {
					sql="SELECT c.id, c.title, c.sef_url, c.mobile_image, c.kicker_image_alt_text, c.primary_category " +
							"FROM jos_content c,jos_article_section a,jos_videogallerynames v,jos_categories jcat " +
							"where c.id="+sliderContent[i].substring(5)+" and jcat.id=a.cat_id and c.id=a.article_id and c.id=v.contentid and c.state=1 " +
							"and jcat.published=1 group by a.article_id";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					//System.out.println("Slider Video->"+sql);
					while(rs.next()){
						lDTO = new LatestContentDTO();
						lDTO.setId(rs.getInt("id"));
						lDTO.setTitle(rs.getString("title"));
						lDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
						lDTO.setThumbImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
						lDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
						lDTO.setContentType("video");
						categoryId = rs.getString("primary_category");
					    if(categoryId.indexOf("#") > 0) {
					    	pcatids = categoryId.split("#");
						} else {
							pcatids = new String[1];
				    		pcatids[0] = categoryId;
						}
					    
					    if(pcatids==null) {
							pcatids = new String[2];
				    		pcatids[0] = "86";
				    		pcatids[1] = "42";
					    }
				    	pcatlength = pcatids.length;
						String sql1 = "select ";
						if(pcatlength==1)
							sql1 = sql1 + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_sections js where jas.section_id="+pcatids[0]+" and js.published=1";
						else if(pcatlength==2) 
							sql1 = sql1 + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_sections js where jc.id="+pcatids[1]+" and jc.published=1 and js.published=1 and js.id=jc.section";

						pstmt1 = conn.prepareStatement(sql1);
						rs1 = pstmt1.executeQuery();
						while (rs1.next()) {
							lDTO.setCurrentCategoryId(rs1.getInt("curcatid"));		
							lDTO.setCurrentCategoryTitle(rs1.getString("curcatname"));		
							lDTO.setCurrentCategorySefTitle(rs1.getString("curcatsefurl") == null ? "" : rs1.getString("curcatsefurl"));		
							lDTO.setCurrentCategoryPageURL(rs1.getString("curcatpath") == null ? "" : rs1.getString("curcatpath"));		
							lDTO.setPrimaryCategoryLength(pcatlength);
						}
						try {
							String ssql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='video'";
							pstmt_com = conn.prepareStatement(ssql);
							rs_com = pstmt_com.executeQuery();
							while (rs_com.next()) {
								commentCount = rs_com.getInt("ccount");				
							}
						}catch(Exception c){
							System.out.println("CommonFunctions mostpopular-comment Exception is :" + c);
						}
						lDTO.setCommentCount(commentCount);

						mostPopularData.add(lDTO);
					}
					rs=null;
					pstmt=null;
					sql="";
					rs1=null;
					pstmt1=null;
					rs_com=null;
					pstmt_com=null;

				}
				if(sliderContent[i].substring(0,5).equals("photo")) {
					sql="SELECT gn.id, gn.Gallery_caption, gn.sef_url, gn.thumb_image, gn.primary_category " +
							"FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
							"where gn.id="+sliderContent[i].substring(5)+" and pc.id=gs.cat_id and " +
									"gn.id=gs.content_id and gn.published=1 and pc.published=1 group by gs.content_id";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					//System.out.println("Slider Video->"+sql);
					while(rs.next()){
						lDTO = new LatestContentDTO();
						lDTO.setId(rs.getInt("id"));
						lDTO.setTitle(rs.getString("Gallery_caption"));
						lDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
						lDTO.setThumbImage(rs.getString("thumb_image")==null?"":rs.getString("thumb_image"));
						lDTO.setThumbImageAltText(rs.getString("Gallery_caption")==null?"":rs.getString("Gallery_caption"));
						lDTO.setContentType("photo");
						categoryId = rs.getString("primary_category");
					    if(categoryId.indexOf("#") > 0) {
					    	pcatids = categoryId.split("#");
						} else {
							pcatids = new String[1];
				    		pcatids[0] = categoryId;
						}
					    
					    if(pcatids==null) {
							pcatids = new String[1];
				    		pcatids[0] = "3";
					    }
				    	pcatlength = pcatids.length;
						String sql1 = "select p.title as curcatname, p.id as curcatid, p.sef_url as curcatsefurl " +
								"from jos_photocategories p where p.id="+pcatids[pcatlength-1]+" and p.published=1";

						pstmt1 = conn.prepareStatement(sql1);
						rs1 = pstmt1.executeQuery();
						while (rs1.next()) {
							lDTO.setCurrentCategoryId(rs1.getInt("curcatid"));		
							lDTO.setCurrentCategoryTitle(rs1.getString("curcatname"));		
							lDTO.setCurrentCategorySefTitle(rs1.getString("curcatsefurl") == null ? "" : rs1.getString("curcatsefurl"));		
							lDTO.setPrimaryCategoryLength(pcatlength);
						}
						try {
							String ssql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='photo'";
							pstmt_com = conn.prepareStatement(ssql);
							rs_com = pstmt_com.executeQuery();
							while (rs_com.next()) {
								commentCount = rs_com.getInt("ccount");				
							}
						}catch(Exception c){
							System.out.println("CommonFunctions mostpopular-comment Exception is :" + c);
						}
						lDTO.setCommentCount(commentCount);

						mostPopularData.add(lDTO);
					}
				}

			}
		}

		
		
	} catch (Exception e) {
		System.out.println("IT CommonFunctions.java - mostPopular function - Exception ->" + e);
	}finally{
		if(pstmt1!=null)
			pstmt1.close();
		if(rs1!=null)
			rs1.close();
		if(pstmt!=null)
			pstmt.close();
		if(rs!=null)
			rs.close();
		if(pstmt_com!=null)
			pstmt_com.close();
		if(rs_com!=null)
			rs_com.close();
		if(conn!=null)
			conn.close();
	}

return mostPopularData;
}


public static ArrayList topstoriesChunkHomePage(String primaryCatId, int contentCount, String contentIdToAvoid, int isPublished) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt_com = null;
	ResultSet rs_com = null;
	LatestContentDTO tsDTO = null;	
	ArrayList dataList = null;
	String contentIdsToCheck = "";
	String contentIds = "";
	int commentCount=0;
	if(!contentIdToAvoid.equals("0")) {
		if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
			contentIdsToCheck = " and c.id != "+contentIdToAvoid+" ";
		else
			contentIdsToCheck = " and c.id not in ("+contentIdToAvoid+") ";
	}
	int pcatlength = 0;
	String pcatids[] = null;

	int storyFormat = 0;
	String[] topstoryContent = null;
	int storyFormatState = 0;
	if(isPublished >=0 && isPublished <=1) {
		storyFormatState = isPublished;
	}
	String sql = "";
	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		sql = "SELECT topstory_format, topstory_state, topstory_content FROM jos_itcontrolpannel ";
		if(isPublished==1) {
			sql = sql + " where topstory_state="+storyFormatState;
		}
		sql = sql + " limit 1";
		//System.out.println("sql 1 ->"+sql);
		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			storyFormat = rs.getInt("topstory_format");
			storyFormatState = rs.getInt("topstory_state");
			if(rs.getString("topstory_content").contains("#")) {	
				topstoryContent = rs.getString("topstory_content").split("#");
			}
		}
		pstmt=null;
		rs=null;
		sql="";
		//System.out.println("storyFormat->"+storyFormat);	
		if(storyFormat==1) {
			dataList = new ArrayList();

			String categoryId = "";
			//if(topstoryContent!=null && topstoryContent.length>0) {
				//for(int i=0; i < topstoryContent.length; i++) {
				//System.out.println("topstoriesChunkHomePage->"+topstoryContent[i]);
				//}
			//}
			
			if(topstoryContent!=null && topstoryContent.length>0) {
				for(int i=0, tsCtr=0; i < topstoryContent.length && tsCtr < contentCount; i++, tsCtr++) {
				//System.out.println("START storyFormat->"+storyFormat + " --------------------"+ topstoryContent[i]);
				try {
					if(topstoryContent[i].substring(0,5).equals("story")) {
						try {
							sql = "SELECT c.id, c.title,c.sef_url, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, c.introtext,c.strap_headline, " +
									"DATE_FORMAT(c.modified,'%H:%i')as mtime,c.byline, c.city, c.large_kicker_image_alt_text, c.primary_category, c.indiatoday_expert " +
									"FROM jos_content c, jos_article_section a, jos_sections s " +
									"WHERE c.id="+topstoryContent[i].substring(5)+" and c.id=a.article_id and s.id=a.section_id and c.state=1 and " +
									"s.published=1 group by a.article_id";
							//System.out.println("topstoriesChunkHomePage Story->"+sql);
							pstmt = connection.prepareStatement(sql);
							rs = pstmt.executeQuery();
							rs.last();
							if(rs.getRow() <= 0) {
								storyFormat=0;
							}
							rs.beforeFirst();
							while (rs.next()){
								tsDTO = new LatestContentDTO();
								tsDTO.setId(rs.getInt("id"));				
								tsDTO.setTitle(rs.getString("title"));
								tsDTO.setSefTitle(rs.getString("sef_url"));
								tsDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
								tsDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
								tsDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
								tsDTO.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));
					
								tsDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
								tsDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
								tsDTO.setLargeImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
								tsDTO.setLargeImageCaption(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
								tsDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
								tsDTO.setExpertComment(rs.getString("indiatoday_expert") == null ? "" : rs.getString("indiatoday_expert"));
								tsDTO.setContentType("text");
						    	
								String ssql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='story'";
								//System.out.println("ssql 1 ->"+ssql);
								pstmt_com = connection.prepareStatement(ssql);
								rs_com = pstmt_com.executeQuery();
								while (rs_com.next()) {
									commentCount = rs_com.getInt("ccount");				
								}
								tsDTO.setCommentCount(commentCount);
			
						    	dataList.add(tsDTO);
							}
							rs=null;
							pstmt=null;
							sql="";
							rs_com=null;
							pstmt_com=null;
						}catch(Exception c){
							storyFormat=0;
							System.out.println("CommonFunctions topstoriesChunkHomePage Story Exception is :" + c);
						}
					}
					if(topstoryContent[i].substring(0,5).equals("video")) {
						try {
							sql = "SELECT c.id, c.title,c.sef_url, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, c.introtext,c.strap_headline, " +
							"DATE_FORMAT(c.modified,'%H:%i')as mtime,c.byline, c.city, c.large_kicker_image_alt_text, c.primary_category, c.indiatoday_expert " +
									"FROM jos_content c,jos_article_section a,jos_videogallerynames v,jos_categories jcat " +
									"where c.id="+topstoryContent[i].substring(5)+" and jcat.id=a.cat_id and c.id=a.article_id and c.id=v.contentid and c.state=1 " +
									"and jcat.published=1 group by a.article_id";
							pstmt = connection.prepareStatement(sql);
							rs = pstmt.executeQuery();
							rs.last();
							if(rs.getRow() <= 0) {
								storyFormat=0;
							}
							rs.beforeFirst();

							//System.out.println("topstoriesChunkHomePage Video->"+sql);
							while(rs.next()){
								tsDTO = new LatestContentDTO();
								tsDTO.setId(rs.getInt("id"));				
								tsDTO.setTitle(rs.getString("title"));
								tsDTO.setSefTitle(rs.getString("sef_url"));
								tsDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
								tsDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
								tsDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
								tsDTO.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));
					
								tsDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
								tsDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
								tsDTO.setLargeImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
								tsDTO.setLargeImageCaption(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
								tsDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
								tsDTO.setExpertComment(rs.getString("indiatoday_expert") == null ? "" : rs.getString("indiatoday_expert"));
								
								tsDTO.setContentType("video");
								String ssql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='video'";
								
								//System.out.println("ssql 1 ->"+ssql);
								pstmt_com = connection.prepareStatement(ssql);
								rs_com = pstmt_com.executeQuery();
								while (rs_com.next()) {
									commentCount = rs_com.getInt("ccount");				
								}
								tsDTO.setCommentCount(commentCount);
	
								dataList.add(tsDTO);
							}
							rs=null;
							pstmt=null;
							sql="";
							rs_com=null;
							pstmt_com=null;
						}catch(Exception c){
							storyFormat=0;
							System.out.println("CommonFunctions topstoriesChunkHomePage Video Exception is :" + c);
						}
					}
					if(topstoryContent[i].substring(0,5).equals("photo")) {
						try {
							sql="SELECT gn.id, gn.Gallery_caption, gn.sef_url, gn.thumb_image,gn.small_image, gn.primary_category, large_image, large_image_alttext, description " +
									"FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
									"where gn.id="+topstoryContent[i].substring(5)+" and pc.id=gs.cat_id and " +
											"gn.id=gs.content_id and gn.published=1 and pc.published=1 group by gs.content_id";
							pstmt = connection.prepareStatement(sql);
							rs = pstmt.executeQuery();
							rs.last();
							if(rs.getRow() <= 0) {
								storyFormat=0;
							}
							rs.beforeFirst();

							//System.out.println("topstoriesChunkHomePage Photo->"+sql);
							while(rs.next()){
								tsDTO = new LatestContentDTO();
								tsDTO.setId(rs.getInt("id"));
								tsDTO.setTitle(rs.getString("Gallery_caption"));
								tsDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
								tsDTO.setThumbImage(rs.getString("small_image")==null?"":rs.getString("small_image"));
								tsDTO.setThumbImageAltText(rs.getString("Gallery_caption")==null?"":rs.getString("Gallery_caption"));
								tsDTO.setLargeImage(rs.getString("large_image") == null ? "" : rs.getString("large_image"));
								tsDTO.setLargeImageCaption(rs.getString("large_image_alttext") == null ? "" : rs.getString("large_image_alttext"));
								tsDTO.setShortDescription(rs.getString("description") == null ? rs.getString("Gallery_caption") : rs.getString("description"));
								tsDTO.setExpertComment("");

								tsDTO.setContentType("photo");

								String ssql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='photo'";
								//System.out.println("ssql 1 ->"+ssql);
								pstmt_com = connection.prepareStatement(ssql);
								rs_com = pstmt_com.executeQuery();
								while (rs_com.next()) {
									commentCount = rs_com.getInt("ccount");				
								}
								tsDTO.setCommentCount(commentCount);
	
								dataList.add(tsDTO);
							}
						}catch(Exception c){
							storyFormat=0;
							System.out.println("CommonFunctions topstoriesChunkHomePage Photo Exception is :" + c);
						}
					}
				}catch(Exception c){
					System.out.println("CommonFunctions topstories - Manual Exception is :" + c);
					storyFormat = 0;
				}
				//System.out.println("END storyFormat->"+storyFormat + " --------------------"+ topstoryContent[i]);

				}
			}

		}
		if(storyFormat==0) {
			//System.out.println("SSSEND storyFormat->"+storyFormat);
			dataList = null;
			dataList = new ArrayList();
		    if(primaryCatId.indexOf("#") > 0) {
		    	pcatids = primaryCatId.split("#");
			} else {
				pcatids = new String[1];
				pcatids[0] = primaryCatId;
			}
		    
		    if(pcatids==null) {
				pcatids = new String[1];
				pcatids[0] = "120";
		    }
			pcatlength = pcatids.length;
			sql = "select c.id, c.title,c.sef_url, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, " +
					"c.introtext,c.strap_headline,DATE_FORMAT(c.modified,'%H:%i')as mtime,c.byline, c.city, c.large_kicker_image_alt_text, c.indiatoday_expert ";
	
			if(pcatlength==1)
				sql = sql + " from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
			else if(pcatlength==2) 
				sql = sql + " from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(pcatlength==3) 
				sql = sql + " from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(pcatlength==4) 
				sql = sql + " from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			sql = sql + "c.state=1 and c.id=jas.article_id "+contentIdsToCheck+" group by c.id order by jas.ordering desc limit "+contentCount;
	
			pstmt = connection.prepareStatement(sql);
			//System.out.print("Top Stories->"+sql);
	
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(contentIds.equals("")) {
					contentIds = "" + rs.getInt("id");
				} else {
					contentIds += ", " + rs.getInt("id");
				}
				tsDTO = new LatestContentDTO();
				tsDTO.setId(rs.getInt("id"));				
				tsDTO.setTitle(rs.getString("title"));
				tsDTO.setSefTitle(rs.getString("sef_url"));
				tsDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
				tsDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
				tsDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
				tsDTO.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));
	
				tsDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				tsDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				tsDTO.setLargeImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
				tsDTO.setLargeImageCaption(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
				tsDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
				tsDTO.setExpertComment(rs.getString("indiatoday_expert") == null ? "" : rs.getString("indiatoday_expert"));

				tsDTO.setLatestIds(contentIds);
				try {
					sql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1";
					pstmt_com = connection.prepareStatement(sql);
					rs_com = pstmt_com.executeQuery();
					while (rs_com.next()) {
						commentCount = rs_com.getInt("ccount");				
					}
				}catch(Exception c){
					System.out.println("CommonFunctions topstories-comment Exception is :" + c);
				}
				tsDTO.setCommentCount(commentCount);
				tsDTO.setContentType("text");
				dataList.add(tsDTO);
			}
		} 

	} catch (Exception e) {
		System.out.println("IT--CommonFunctions latestHeadlinesWGBy Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return dataList;
}

public static ArrayList relatedContentTopStoriesWithCount(String contentids, String contenttype, int countToFetch) throws Exception {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs1 = null;
	RelatedContentDTO rcDTO = null;
	String selectStmtP1 = "";
    ArrayList relatedContent = new ArrayList();
    if(contenttype.trim().length() == 0) {
    	contenttype = "text";
    }

    //variable check to save query execution time to fetch Related for single story
    String contentIdCondition = "";
    if(!contentids.contains(","))
    	contentIdCondition = "= "+contentids;
    else
    	contentIdCondition = "in ("+contentids+")";

	//System.out.println("Related Content - " + contentids + " -- contentIdCondition" + contentIdCondition);
	try {
		Dbconnection dbConn = Dbconnection.getInstance();
		conn  = dbConn.getConnection();
		String selectStmt = "SELECT article_id, related_article_id, related_title, related_type, featured " +
				"FROM jos_related_stories where article_id "+contentIdCondition+" and " +
				"content_type = '"+contenttype+"' group by article_id, related_article_id order by article_id, related_order asc ";
		if(countToFetch!=0) {
			selectStmt = selectStmt + " limit " + countToFetch;
		}

		System.out.println("Related Query Top Stories- " + selectStmt);
		pstmt = conn.prepareStatement(selectStmt);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			rcDTO = new RelatedContentDTO();
			rcDTO.setContentId(rs.getInt("article_id"));
			rcDTO.setRelatedContentId(rs.getInt("related_article_id"));
			rcDTO.setRelatedTitle(rs.getString("related_title")==null?"":rs.getString("related_title"));
			rcDTO.setRelatedType(rs.getString("related_type")==null?"":rs.getString("related_type"));
			rcDTO.setFeatured(rs.getInt("featured"));

			try {
				if(rs.getString("related_type").equals("photo")) {
					selectStmtP1 = "SELECT sef_url,thumb_image FROM jos_gallerynames where id=?";
					System.out.println("Related Query Gallery  - " + selectStmtP1);
					pstmt1 = conn.prepareStatement(selectStmtP1);
					pstmt1.setInt(1, rs.getInt("related_article_id"));
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						rcDTO.setRelatedContentSefTitle(rs1.getString("sef_url")==null?"":rs1.getString("sef_url"));
						rcDTO.setRelatedThumbImage(rs1.getString("thumb_image")==null?"":rs1.getString("thumb_image"));
					}
				} else if(rs.getString("related_type").equals("text") || rs.getString("related_type").equals("video")) {
					selectStmtP1 = "SELECT sef_url,mobile_image FROM jos_content where id=?";
					System.out.println("Related Query Text/Video  - " + selectStmtP1);
					pstmt1 = conn.prepareStatement(selectStmtP1);
					pstmt1.setInt(1, rs.getInt("related_article_id"));
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						rcDTO.setRelatedContentSefTitle(rs1.getString("sef_url")==null?"":rs1.getString("sef_url"));
						rcDTO.setRelatedThumbImage(rs1.getString("mobile_image")==null?"":rs1.getString("mobile_image"));
					}
				}
			} catch(Exception ex) {
				System.out.println("IT--relatedContentTopStories Content SQLException (CommonFunctions.java) -> " + ex.toString());
			} finally {
				if(rs1!=null)
					rs1.close();
				if(pstmt1!=null)
					pstmt1.close();
			}
			relatedContent.add(rcDTO);
		}
	} catch(SQLException sqls) {
		System.out.println("IT--relatedContentTopStoriesWithCount Content SQLException (CommonFunctions.java) -> " + sqls);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(conn!=null)
			conn.close();
	}
	return relatedContent;
}

public static ArrayList mustSee(int categoryId, int countToFetch, String galleryIdToAvoid) throws Exception
{
	ArrayList mustSeeData = new ArrayList();
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ResultSet rs_pc = null;
	LatestContentDTO lcDTO = null;
	String galleryCheck = "";
	String sql = "";
	
	if(!galleryIdToAvoid.equals("0")) {
		if(galleryIdToAvoid.indexOf(",") > 0)
			galleryCheck = "and gn.id not in ("+galleryIdToAvoid+")";
		else
			galleryCheck = "and gn.id != "+galleryIdToAvoid;
	}

	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		if(categoryId==0) {
			sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image, gn.small_image, gn.medium_image, pc.sef_url as catsefurl  " +
				" FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
				" where gn.featured_page=1 and gn.published=1 "+galleryCheck+" and pc.id=gs.cat_id and gn.id=gs.content_id and " +
						" pc.published=1 group by gn.id order by gn.featured_ordering desc limit "+countToFetch;
		} else {
			sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image, gn.small_image, gn.medium_image, pc.sef_url as catsefurl " +
			" FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
			" where gs.cat_id="+categoryId+" and pc.id=gs.cat_id and gn.id=gs.content_id and gn.published=1 "+galleryCheck+" and " +
					" pc.published=1 group by gn.id order by gn.ordering desc limit "+countToFetch;
		}
		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		sql="";
		pstmt=null;
		while (rs.next()) {
			lcDTO = new LatestContentDTO();
			lcDTO.setId(rs.getInt("id"));
			lcDTO.setTitle(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
			lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			lcDTO.setThumbImage(rs.getString("thumb_image") == null ? "" : rs.getString("thumb_image"));
			lcDTO.setSmallImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
			lcDTO.setMediumImage(rs.getString("medium_image")==null ? "": rs.getString("medium_image"));
			lcDTO.setThumbImageAltText(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
			lcDTO.setCurrentCategorySefTitle(rs.getString("catsefurl")==null ? "" :rs.getString("catsefurl"));
			try {
				sql = "SELECT count(pg.id) as photo_count FROM jos_photogallery pg where pg.gallery_id="+rs.getInt("id")+" and pg.enable=1";
				pstmt = connection.prepareStatement(sql);
				rs_pc = pstmt.executeQuery();
				while (rs_pc.next()) {
					lcDTO.setContentCount(rs_pc.getInt("photo_count"));
				}
			} catch (Exception pcEx) {
				System.out.println("IT--mustSee Photo Gallery Count - "+pcEx.toString());
			} finally {
				sql="";
				pstmt=null;
				rs_pc=null;
			}
			mustSeeData.add(lcDTO);
		}
	} catch (Exception e) {
		System.out.println("IT CommonFunctions.java-mustsee-Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(rs_pc!=null)
			rs_pc.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return mustSeeData;
}

public static ArrayList latestStoryChunk(String categoryId, int contentCount, String contentIdToAvoid) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	LatestContentDTO lcDTO = null;	
	ArrayList dataList = new ArrayList();
	String latestids="0";
	int latestCtr = 0;
	int pcatlength = 0;
	String pcatids[] = null;
    if(categoryId.indexOf("#") > 0) {
    	pcatids = categoryId.split("#");
	} else {
		pcatids = new String[1];
		pcatids[0] = categoryId;
	}
    
    if(pcatids==null) {
		pcatids = new String[1];
		pcatids[0] = "175";
    }
	pcatlength = pcatids.length;
	//System.out.println("categoryId-->"+categoryId+"------pcatlength-->"+pcatlength+"--contentIdToAvoid--"+contentIdToAvoid);
	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		String sql = "select c.id, c.title,c.sef_url, c.short_introtext, c.mobile_image as small_image, c.kicker_image,c.kicker_image_alt_text, ";
		if(pcatlength==1)
			sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
		else if(pcatlength==2) 
			sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
		else if(pcatlength==3) 
			sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
		else if(pcatlength==4) 
			sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";

		if(contentIdToAvoid.equals("0"))
			sql = sql + "c.state=1 and c.id=jas.article_id group by c.id order by jas.ordering desc limit "+contentCount;
		else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
			sql = sql + "c.state=1 and c.id=jas.article_id and c.id != "+contentIdToAvoid+" group by c.id order by jas.ordering desc limit "+contentCount;
		else
			sql = sql + "c.state=1 and c.id=jas.article_id and c.id not in ("+contentIdToAvoid+") group by c.id order by jas.ordering desc limit "+contentCount;

		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		//System.out.println("Latest Headlines---->"+sql);
		while (rs.next()) {
			if(latestCtr==0)
				latestids = "" + rs.getInt("id");
			else
				latestids += ", " + rs.getInt("id");
				
			lcDTO = new LatestContentDTO();
			lcDTO.setId(rs.getInt("id"));				
			lcDTO.setTitle(rs.getString("title"));
			lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			lcDTO.setShortDescription(rs.getString("short_introtext") == null ? "" : rs.getString("short_introtext"));
			lcDTO.setSmallImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
			lcDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
			lcDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
			lcDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
			lcDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
			lcDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl") == null ? "" : rs.getString("curcatsefurl"));		
			lcDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
			lcDTO.setLatestIds(latestids);
			dataList.add(lcDTO);
			latestCtr++;
		}
	} catch (Exception e) {
		System.out.println("IT CommonFunctions.java-latestArticleChunk-Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return dataList;
}


public static ArrayList topstoriesChunk(String primaryCatId, int contentCount, String contentIdToAvoid, String ordering) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt_com = null;
		ResultSet rs_com = null;
		LatestContentDTO tsDTO = null;	
		ArrayList dataList = new ArrayList();
		String contentIdsToCheck = "";
		String contentIds = "";
		String ordering_base="order by "+ordering;
		int commentCount=0;
		if(!contentIdToAvoid.equals("0")) {
			if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				contentIdsToCheck = " and c.id != "+contentIdToAvoid+" ";
			else
				contentIdsToCheck = " and c.id not in ("+contentIdToAvoid+") ";
		}
		int pcatlength = 0;
		String pcatids[] = null;
	    if(primaryCatId.indexOf("#") > 0) {
	    	pcatids = primaryCatId.split("#");
		} else {
			pcatids = new String[1];
    		pcatids[0] = primaryCatId;
		}
	    
	    if(pcatids==null) {
			pcatids = new String[1];
    		pcatids[0] = "175";
	    }
    	pcatlength = pcatids.length;

		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select c.id, c.title,c.sef_url, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, " +
					"c.introtext,c.strap_headline,c.mobile_image,DATE_FORMAT(c.modified,'%H:%i')as mtime,c.byline, c.city, c.large_kicker_image_alt_text, c.indiatoday_expert, c.medium_kicker_image, c.extralarge_image,  ";
	
			if(pcatlength==1)
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
			else if(pcatlength==2) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(pcatlength==3) 
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(pcatlength==4) 
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			//condition checked for India, Sports, Movies and Must Read
			if(pcatlength==1 && (primaryCatId.trim().equals("114") || primaryCatId.trim().equals("84") || primaryCatId.trim().equals("67") || primaryCatId.trim().equals("175"))) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}
			//condition checked for India#North
			if(pcatlength==2 && primaryCatId.trim().equals("114#141")) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}
			
			sql = sql + "c.state=1 and c.id=jas.article_id "+contentIdsToCheck+" group by c.id "+ordering_base+" desc limit "+contentCount;
			
			pstmt = connection.prepareStatement(sql);
			//System.out.print("Top Stories->"+sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				if(contentIds.equals("")) {
					contentIds = "" + rs.getInt("id");
				} else {
					contentIds += ", " + rs.getInt("id");
				}
				tsDTO = new LatestContentDTO();
				tsDTO.setId(rs.getInt("id"));				
				tsDTO.setTitle(rs.getString("title"));
				tsDTO.setSefTitle(rs.getString("sef_url"));
				tsDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
				tsDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
				tsDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
				tsDTO.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));

				tsDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				tsDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				tsDTO.setLargeImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
				tsDTO.setLargeImageCaption(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
				tsDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
				tsDTO.setExpertComment(rs.getString("indiatoday_expert") == null ? "" : rs.getString("indiatoday_expert"));
				tsDTO.setMediumImage(rs.getString("medium_kicker_image")==null ? "" : rs.getString("medium_kicker_image"));
				tsDTO.setExtraLargeImage(rs.getString("extralarge_image")==null ? "" : rs.getString("extralarge_image"));
				tsDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				tsDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				tsDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl"));		
				tsDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));	
				tsDTO.setMobileImage(rs.getString("mobile_image") == null ? "" : rs.getString("mobile_image"));	
				tsDTO.setLatestIds(contentIds);
				try {
					sql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1";
					pstmt_com = connection.prepareStatement(sql);
					rs_com = pstmt_com.executeQuery();
					while (rs_com.next()) {
						commentCount = rs_com.getInt("ccount");				
					}
				}catch(Exception c){
					System.out.println("IT--CommonFunctions topstories-comment Exception is :" + c);
				}
				tsDTO.setCommentCount(commentCount);
				dataList.add(tsDTO);
			}
		} catch (Exception e) {
			System.out.println("IT--CommonFunctions latestHeadlinesWGBy Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return dataList;
	} 

public static ArrayList latestArticleChunk(String categoryId, int contentCount, String contentIdToAvoid, String ordering) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LatestContentDTO lcDTO = null;	
		ArrayList dataList = new ArrayList();
		String latestids="0";
		String ordering_base=" order by "+ordering;
		int latestCtr = 0;
		int pcatlength = 0;
		String pcatids[] = null;
	    if(categoryId.indexOf("#") > 0) {
	    	pcatids = categoryId.split("#");
		} else {
			pcatids = new String[1];
    		pcatids[0] = categoryId;
		}
	    
	    if(pcatids==null) {
			pcatids = new String[1];
    		pcatids[0] = "175";
	    }
    	pcatlength = pcatids.length;
		//System.out.println("categoryId-->"+categoryId+"------pcatlength-->"+pcatlength+"--contentIdToAvoid--"+contentIdToAvoid);
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select c.id, c.title,c.sef_url, c.introtext, c.mobile_image as small_image, c.kicker_image,c.kicker_image_alt_text, ";
			if(pcatlength==1)
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
			else if(pcatlength==2) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(pcatlength==3) 
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(pcatlength==4) 
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			//condition checked for India, Sports, Movies and Must Read
			if(pcatlength==1 && (categoryId.trim().equals("114") || categoryId.trim().equals("84") || categoryId.trim().equals("67") || categoryId.trim().equals("175"))) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}
			//condition checked for India#North
			if(pcatlength==2 && categoryId.trim().equals("114#141")) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}

			if(contentIdToAvoid.equals("0"))
				sql = sql + "c.state=1 and c.id=jas.article_id group by c.id "+ordering_base+" desc limit "+contentCount;
			else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				sql = sql + "c.state=1 and c.id=jas.article_id and c.id != "+contentIdToAvoid+" group by c.id "+ordering_base+" desc limit "+contentCount;
			else
				sql = sql + "c.state=1 and c.id=jas.article_id and c.id not in ("+contentIdToAvoid+") group by c.id "+ordering_base+" desc limit "+contentCount;
				
			//System.out.println("Latest Headlines including section page---->"+sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if(latestCtr==0)
					latestids = "" + rs.getInt("id");
				else
					latestids += ", " + rs.getInt("id");
					
				lcDTO = new LatestContentDTO();
				lcDTO.setId(rs.getInt("id"));				
				lcDTO.setTitle(rs.getString("title"));
				lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
				lcDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
				lcDTO.setSmallImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
				lcDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				lcDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				lcDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				lcDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				lcDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl") == null ? "" : rs.getString("curcatsefurl"));		
				lcDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
				lcDTO.setLatestIds(latestids);
				dataList.add(lcDTO);
				latestCtr++;
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java-latestArticleChunk-Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return dataList;
	}

public static ArrayList latestArticleChunkWithInterval(String categoryId, int contentCount, String contentIdToAvoid, int timeInterval) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	LatestContentDTO lcDTO = null;	
	ArrayList dataList = new ArrayList();
	String latestids="0";
	int latestCtr = 0;
	int pcatlength = 0;
	String pcatids[] = null;
    if(categoryId.indexOf("#") > 0) {
    	pcatids = categoryId.split("#");
	} else {
		pcatids = new String[1];
		pcatids[0] = categoryId;
	}
    
    if(pcatids==null) {
		pcatids = new String[1];
		pcatids[0] = "120";
    }
	pcatlength = pcatids.length;
	//System.out.println("categoryId-->"+categoryId+"------pcatlength-->"+pcatlength+"--contentIdToAvoid--"+contentIdToAvoid);
	try {
		Dbconnection connect = null;
		connect = Dbconnection.getInstance();
		connection = connect.getConnection();
		String sql = "select c.id, c.title,c.sef_url, c.introtext, c.extralarge_image, c.mobile_image as small_image, c.kicker_image,c.kicker_image_alt_text, ";
		if(pcatlength==1)
			sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
		else if(pcatlength==2) 
			sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
		else if(pcatlength==3) 
			sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
		else if(pcatlength==4) 
			sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";

		//condition checked for Time Interval to optimize the query
		if(timeInterval != 0) {
			sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+timeInterval+" DAY) and ";
		}

		if(contentIdToAvoid.equals("0"))
			sql = sql + "c.state=1 and c.id=jas.article_id group by c.id order by jas.ordering desc limit "+contentCount;
		else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
			sql = sql + "c.state=1 and c.id=jas.article_id and c.id != "+contentIdToAvoid+" group by c.id order by jas.ordering desc limit "+contentCount;
		else
			sql = sql + "c.state=1 and c.id=jas.article_id and c.id not in ("+contentIdToAvoid+") group by c.id order by jas.ordering desc limit "+contentCount;

		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		//System.out.println("Latest Headlines including Interval page---->"+sql);
		while (rs.next()) {
			if(latestCtr==0)
				latestids = "" + rs.getInt("id");
			else
				latestids += ", " + rs.getInt("id");
				
			lcDTO = new LatestContentDTO();
			lcDTO.setId(rs.getInt("id"));				
			lcDTO.setTitle(rs.getString("title"));
			lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			lcDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
			lcDTO.setExtraLargeImage(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
			lcDTO.setSmallImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
			lcDTO.setThumbImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
			lcDTO.setThumbImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
			lcDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
			lcDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
			lcDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl") == null ? "" : rs.getString("curcatsefurl"));		
			lcDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));
			
			lcDTO.setLatestIds(latestids);
			dataList.add(lcDTO);
			latestCtr++;
		}
	} catch (Exception e) {
		System.out.println("IT CommonFunctions.java-latestArticleChunkWithInterval-Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return dataList;
}


public static ArrayList getNextPreviousArticle(String categoryid, int contentId) throws Exception
{
	
	Connection pnconn = null;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	StoryDTO stdo =null;
	ArrayList moreContentData = new ArrayList();
	int pcatlength = 0;
	int contentIdOrdering = 0;
	int contentMaxOrdering = 0;
	
	String pcatids[] = null;
	categoryid = categoryid.replaceAll("~","#");
    if(categoryid.indexOf("#") > 0) {
    	pcatids = categoryid.split("#");
	} else {
		pcatids = new String[1];
		pcatids[0] = categoryid;
	}
	pcatlength = pcatids.length;
	String catIdCondition = "";
	if(pcatlength==1)
		catIdCondition = " where a.section_id=" + pcatids[0];
	else if(pcatlength==2) 
		catIdCondition = " where a.section_id=" + pcatids[0] + " and a.cat_id=" + pcatids[1];
	else if(pcatlength==3) 
		catIdCondition = " where a.section_id=" + pcatids[0] + " and a.cat_id=" + pcatids[1] + " and a.subcat_id=" + pcatids[2];
	else if(pcatlength==4) 
		catIdCondition = " where a.section_id=" + pcatids[0] + " and a.cat_id=" + pcatids[1] + " and a.subcat_id=" + pcatids[2] + " and a.sub_subcat_id=" + pcatids[3];

	try {			
		
		Dbconnection dbConn = Dbconnection.getInstance();
		pnconn = dbConn.getConnection();
		
		
		String sql = "select a.ordering, a.article_id from jos_content c, jos_article_section a ";
			sql = sql + catIdCondition;
			sql = sql + " and a.article_id="+contentId+" and a.article_id=c.id group by c.id";
		
		//System.out.println("Previous Next Content for specific content Query --> " + sql);
		pstmt = pnconn.prepareStatement(sql);
		rs = pstmt.executeQuery(sql);				
		while (rs.next()) {
			contentIdOrdering = rs.getInt("ordering");
		}
			
		if(contentIdOrdering>0) {
		  	sql="select c.id, c.title, c.introtext,c.sef_url, a.ordering from jos_content c,jos_article_section a ";
			sql = sql + catIdCondition;
  			//sql = sql + " and a.article_id=c.id and c.state=1 and a.section_id=s.id and a.ordering < "+contentIdOrdering+" group by a.article_id order by a.ordering desc limit "+numberOfContent;
  			sql = sql + " and a.article_id=c.id and c.state=1 and a.ordering < "+contentIdOrdering+" order by a.ordering desc limit 1";
		  	//System.out.println("Previous Content Query --> " + sql);
			pstmt = pnconn.prepareStatement(sql);
			rs = pstmt.executeQuery();				
			if(rs.next()) {
				stdo = new StoryDTO();				
				stdo.setCategoryId(rs.getInt("id"));
				//stdo.setSectionTitle(rs.getString("sectionName"));
				stdo.setTitle(rs.getString("title"));
				stdo.setCurrentCategoryPageURL(rs.getString("sef_url"));		
				stdo.setSefTitle("previous");
				stdo.setShortDescription(rs.getString("introtext"));
				moreContentData.add(stdo);					
				//System.out.println("Previous Content Query --> 11112");
			}
			rs=null;
			pstmt=null;
			
			//System.out.println("moreContentData -> " + moreContentData.size());		

		}
	  	sql="select c.id, c.title, c.sef_url,c.introtext, a.ordering from jos_content c,jos_article_section a ";
		sql = sql + catIdCondition;
			sql = sql + " and a.article_id=c.id and c.state=1 and a.ordering > "+contentIdOrdering+"  order by a.ordering limit 1";
	  	
		//System.out.println("Next Content Query --> " + sql);
		pstmt = pnconn.prepareStatement(sql);
		rs = pstmt.executeQuery();				
		if(rs.next()) {
			stdo = new StoryDTO();				
			stdo.setCategoryId(rs.getInt("id"));
			//stdo.setSectionTitle(rs.getString("sectionName"));
			stdo.setTitle(rs.getString("title"));
			stdo.setCurrentCategoryPageURL(rs.getString("sef_url"));		
			stdo.setSefTitle("next");
			stdo.setShortDescription(rs.getString("introtext"));
			moreContentData.add(stdo);
			
			
		}
	} catch (Exception e) {
		System.out.println("Previous Next Content SQLException (BT story.jsp) -> " + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(pnconn!=null)
			pnconn.close();
	}
	return moreContentData;
}

}

