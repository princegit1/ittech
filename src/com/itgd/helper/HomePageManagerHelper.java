package com.itgd.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.itgd.conn.Dbconnection;
import com.itgd.dto.HomePageManagerDTO;

public class HomePageManagerHelper {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		HomePageManagerHelper hm = new HomePageManagerHelper();
		try {
			hm.homePageManagerITChunkLeft(15, "0", 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList homePageManagerITChunkLeft(int contentCount, String contentIdToAvoid, int status) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HomePageManagerDTO lcDTO = null;	
		ArrayList dataList = new ArrayList();
		String contentStatus="";
		if(status==1) {
			contentStatus = " published=1 and ";
		}
	    
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select `content_highlighted`,`content_locked`,`section_name`,`section_url`,`chunk_display_type`,id, content_id, title, introtext, published, kicker_image, kicker_image_alt_text, large_kicker_image_alt_text, " +
					"mobile_image, medium_image, large_kicker_image, extralarge_image, ordering, content_type, sef_url, strap_headline, byline, modified,date_format(modified,'%Y-%m-%e' ) AS createddateYYMMDD, " +
					"comment_question, total_comments, primary_category, related_featuredvideo, external_url, `tables`, city, content_type_tag,content_display_color from jos_homepage_manager where "+contentStatus+" display_zone='home_left' ";

			if(contentIdToAvoid.equals("0"))
				sql = sql + " group by id order by ordering limit "+contentCount;
			else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				sql = sql + " and id != "+contentIdToAvoid+" group by id order by ordering limit "+contentCount;
			else
				sql = sql + " and id not in ("+contentIdToAvoid+") group by id order by ordering limit "+contentCount;

			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
		System.out.println("@@@@@@@@@@@-->"+sql);
			
			while (rs.next()) {
				lcDTO = new HomePageManagerDTO();
				lcDTO.setContentId(rs.getInt("content_id"));	
				lcDTO.setId(rs.getInt("id"));
				lcDTO.setTitle(rs.getString("title"));
				lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
				lcDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
				lcDTO.setDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
				lcDTO.setSmallImage(rs.getString("mobile_image") == null ? "" : rs.getString("mobile_image"));
				lcDTO.setMediumImage(rs.getString("medium_image") == null ? "" : rs.getString("medium_image"));
				lcDTO.setKickerImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				lcDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				lcDTO.setLargeImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
				lcDTO.setLargeImageAltText(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
				lcDTO.setExtraLargeImage(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
				lcDTO.setCommentQuestion(rs.getString("comment_question") == null ? "" : rs.getString("comment_question"));
				lcDTO.setCommentCount(rs.getInt("total_comments"));
				lcDTO.setContentType(rs.getInt("content_type"));
				lcDTO.setPrimaryCategory(rs.getString("primary_category") == null ? "" : rs.getString("primary_category"));
				lcDTO.setRelatedFeaturedVideo(rs.getInt("related_featuredvideo"));
				lcDTO.setExternalUrl(rs.getString("external_url") == null ? "" : rs.getString("external_url"));
				lcDTO.setTables(rs.getString("tables") == null ? "" : rs.getString("tables"));
				lcDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
				lcDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
				lcDTO.setContentTypeTag(rs.getString("content_type_tag") == null ? "" : rs.getString("content_type_tag"));
				lcDTO.setSection_name(rs.getString("section_name") == null ? "" : rs.getString("section_name"));
				lcDTO.setSection_url(rs.getString("section_url") == null ? "" : rs.getString("section_url"));
				lcDTO.setChunk_display_type(rs.getString("chunk_display_type") == null ? "" : rs.getString("chunk_display_type"));
				lcDTO.setContent_display_color(rs.getString("content_display_color") == null ? "" : rs.getString("content_display_color"));
				lcDTO.setContentHighlighted(rs.getInt("content_highlighted"));
				lcDTO.setContentLocked(rs.getInt("content_locked"));
				lcDTO.setUpdatedDate(rs.getString("createddateYYMMDD"));
				dataList.add(lcDTO);
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java-homePageManagerITChunk-Exception is :" + e);
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

	public static ArrayList homePageManagerITChunkRight(int contentCount, String contentIdToAvoid, int status) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HomePageManagerDTO lcDTO = null;	
		ArrayList dataList = new ArrayList();
		String contentStatus = "";
		if(status==1) {
			contentStatus = " published=1 and ";
		}
	    
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select id,  date_format( modified,'%M %e, %Y' ) AS mdate,date_format( modified,'%H:%i') AS mtime,content_id, title, short_introtext, introtext, published, kicker_image, kicker_image_alt_text, large_kicker_image_alt_text, " +
					"mobile_image, large_kicker_image, extralarge_image, ordering, content_type, sef_url, strap_headline, byline, modified, " +
					"comment_question, total_comments, primary_category, related_featuredvideo, external_url, `tables`, city, content_type_tag from jos_homepage_manager where "+contentStatus+" display_zone='home_right' ";

			if(contentIdToAvoid.equals("0"))
				sql = sql + " group by id order by right_ordering limit "+contentCount;
			else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				sql = sql + " and id != "+contentIdToAvoid+" group by id order by right_ordering limit "+contentCount;
			else
				sql = sql + " and id not in ("+contentIdToAvoid+") group by id order by right_ordering limit "+contentCount;
			//System.out.println("@@@@@@@@@@@@@"+sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				lcDTO = new HomePageManagerDTO();
				lcDTO.setContentId(rs.getInt("content_id"));				
				lcDTO.setTitle(rs.getString("title"));
				lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
				lcDTO.setShortDescription(rs.getString("short_introtext") == null ? "" : rs.getString("short_introtext"));
				lcDTO.setKickerImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
				lcDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
				lcDTO.setCommentQuestion(rs.getString("comment_question") == null ? "" : rs.getString("comment_question"));
				lcDTO.setCommentCount(rs.getInt("total_comments"));
				lcDTO.setContentType(rs.getInt("content_type"));
				lcDTO.setPrimaryCategory(rs.getString("primary_category") == null ? "" : rs.getString("primary_category"));
				lcDTO.setRelatedFeaturedVideo(rs.getInt("related_featuredvideo"));
				lcDTO.setExternalUrl(rs.getString("external_url") == null ? "" : rs.getString("external_url"));
				lcDTO.setTables(rs.getString("tables") == null ? "" : rs.getString("tables"));
				lcDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
				lcDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
				lcDTO.setContentTypeTag(rs.getString("content_type_tag") == null ? "" : rs.getString("content_type_tag"));
				lcDTO.setSmallImage(rs.getString("mobile_image") == null ? "" : rs.getString("mobile_image"));
				lcDTO.setLargeImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
				lcDTO.setLargeImageAltText(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
				lcDTO.setUpdatedDate(rs.getString("mdate"));
				lcDTO.setUpdatedTime(rs.getString("mtime"));
				dataList.add(lcDTO);
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java-homePageManagerITChunk-Exception is :" + e);
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

	public static ArrayList bigStory(int contentCount, String contentIdToAvoid, int isPublished) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt_com = null;
		ResultSet rs_com = null;
		HomePageManagerDTO tsDTO = null;	
		ArrayList dataList = null;
		String contentIdsToCheck = "";
		String contentIds = "";
		int commentCount=0;
		String highlightStoryId = "";
		String twitterHandler = "";
		String staticContentTitle = "";
		int scorecardFlag = 0;

		if(!contentIdToAvoid.equals("0")) {
			if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				contentIdsToCheck = " and c.id != "+contentIdToAvoid+" ";
			else
				contentIdsToCheck = " and c.id not in ("+contentIdToAvoid+") ";
		}

		String[] topstoryContent = null;
		int storyFormatState = 0;
		String sql = "";
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			sql = "SELECT topstory_content, highlight_story, twitter_handler, static_content, scorecard_flag FROM jos_itcontrolpannel ";
			if(isPublished==1) {
				sql = sql + " where topstory_state="+isPublished;
			}
			sql = sql + " limit 1";
			//System.out.println("sql 1 ->"+sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				storyFormatState = 1;
				if(rs.getString("topstory_content").contains("#")) {	
					topstoryContent = rs.getString("topstory_content").split("#");
				}
				highlightStoryId = rs.getString("highlight_story") == null ? "" : rs.getString("highlight_story");
				twitterHandler = rs.getString("twitter_handler") == null ? "" : rs.getString("twitter_handler");
				staticContentTitle = rs.getString("static_content") == null ? "" : rs.getString("static_content");
				scorecardFlag = rs.getInt("scorecard_flag");
			}
			pstmt=null;
			rs=null;
			sql="";
			//System.out.println("storyFormat->"+storyFormat);	
			if(storyFormatState==1) {
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
								sql = "SELECT c.id, c.title,c.sef_url, c.big_story_image,c.mobile_image as small_image, c.kicker_image_alt_text, c.extralarge_image, c.introtext," +
										"c.byline, c.city, c.large_kicker_image_alt_text, c.content_type_tag " +
										"FROM jos_content c, jos_article_section a, jos_sections s " +
										"WHERE c.id="+topstoryContent[i].substring(5)+" and c.id=a.article_id and s.id=a.section_id and c.state=1 and " +
										"s.published=1 group by a.article_id";
								//System.out.println("topstoriesChunkHomePage Story->"+sql);
								pstmt = connection.prepareStatement(sql);
								rs = pstmt.executeQuery();
								while (rs.next()){
									tsDTO = new HomePageManagerDTO();
									tsDTO.setId(rs.getInt("id"));				
									tsDTO.setTitle(rs.getString("title"));
									tsDTO.setSefTitle(rs.getString("sef_url"));
									tsDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
									tsDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
									tsDTO.setKickerImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
									tsDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
									tsDTO.setLargeImage(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
									tsDTO.setBigStoryImage(rs.getString("big_story_image") == null ? "" : rs.getString("big_story_image"));
									tsDTO.setLargeImageAltText(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
									tsDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
									tsDTO.setContentTypeTag(rs.getString("content_type_tag") == null ? "" : rs.getString("content_type_tag"));
									tsDTO.setContentType(0);
									tsDTO.setHighlightStoryId(highlightStoryId);
									tsDTO.setTwitterHandler(twitterHandler);
									tsDTO.setStaticContentTitle(staticContentTitle);
									tsDTO.setScorecardFlag(scorecardFlag);
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
								storyFormatState=0;
								System.out.println("HomePageManagerHelper bigConent Story Chunk Exception is :" + c);
							}
						}
						if(topstoryContent[i].substring(0,5).equals("video")) {
							try {
								sql = "SELECT c.id, c.title,c.sef_url,c.big_story_image, c.mobile_image as small_image, c.kicker_image_alt_text, c.extralarge_image, c.introtext, " +
								"c.byline, c.city, c.large_kicker_image_alt_text " +
										"FROM jos_content c,jos_article_section a,jos_videogallerynames v,jos_categories jcat " +
										"where c.id="+topstoryContent[i].substring(5)+" and jcat.id=a.cat_id and c.id=a.article_id and c.id=v.contentid and c.state=1 " +
										"and jcat.published=1 group by a.article_id";
								pstmt = connection.prepareStatement(sql);
								rs = pstmt.executeQuery();
								//System.out.println("topstoriesChunkHomePage Video->"+sql);
								while(rs.next()){
									tsDTO = new HomePageManagerDTO();
									tsDTO.setId(rs.getInt("id"));				
									tsDTO.setTitle(rs.getString("title"));
									tsDTO.setSefTitle(rs.getString("sef_url"));
									tsDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
									tsDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
									tsDTO.setKickerImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
									tsDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
									tsDTO.setLargeImage(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
									tsDTO.setBigStoryImage(rs.getString("big_story_image") == null ? "" : rs.getString("big_story_image"));
									tsDTO.setLargeImageAltText(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
									tsDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
									tsDTO.setContentType(1);
									tsDTO.setHighlightStoryId(highlightStoryId);
									tsDTO.setTwitterHandler(twitterHandler);
									tsDTO.setStaticContentTitle(staticContentTitle);
									tsDTO.setScorecardFlag(scorecardFlag);
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
								storyFormatState=0;
								System.out.println("HomePageManagerHelper bigConent Video Chunk Exception is :" + c);
							}
						}
						if(topstoryContent[i].substring(0,5).equals("photo")) {
							try {
								sql="SELECT gn.id, gn.Gallery_caption, gn.sef_url, gn.thumb_image,gn.small_image, extralarge_image, large_image_alttext, description " +
										"FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
										"where gn.id="+topstoryContent[i].substring(5)+" and pc.id=gs.cat_id and " +
												"gn.id=gs.content_id and gn.published=1 and pc.published=1 group by gs.content_id";
								pstmt = connection.prepareStatement(sql);
								rs = pstmt.executeQuery();
								//System.out.println("topstoriesChunkHomePage Photo->"+sql);
								while(rs.next()){
									tsDTO = new HomePageManagerDTO();
									tsDTO.setId(rs.getInt("id"));
									tsDTO.setTitle(rs.getString("Gallery_caption"));
									tsDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
									tsDTO.setKickerImage(rs.getString("thumb_image")==null?"":rs.getString("thumb_image"));
									tsDTO.setKickerImageAltText(rs.getString("Gallery_caption")==null?"":rs.getString("Gallery_caption"));
									tsDTO.setLargeImage(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
									tsDTO.setLargeImageAltText(rs.getString("large_image_alttext") == null ? "" : rs.getString("large_image_alttext"));
									tsDTO.setShortDescription(rs.getString("description") == null ? rs.getString("Gallery_caption") : rs.getString("description"));
									tsDTO.setContentType(2);
									tsDTO.setHighlightStoryId(highlightStoryId);
									tsDTO.setTwitterHandler(twitterHandler);
									tsDTO.setStaticContentTitle(staticContentTitle);
									tsDTO.setScorecardFlag(scorecardFlag);

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
								storyFormatState=0;
								System.out.println("HomePageManagerHelper bigConent Photo Chunk Exception is :" + c);
							}
						}
					}catch(Exception c){
						System.out.println("HomePageManagerHelper bigConent Chunk Exception is :" + c);
						storyFormatState = 0;
					}
					}
				}
			}
		} catch (Exception e) {
			System.out.println("CommonFunctions latestHeadlinesWGBy Exception is :" + e);
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

	public static int isLeadStoryExpanded() throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int leadStoryExpanded = 0;
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT leadstory_expand FROM jos_itcontrolpannel";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				leadStoryExpanded = rs.getInt("leadstory_expand");
			}
		} catch (Exception e) {
			System.out.println("IT HomePageManagerHelper-isLeadStoryExpanded Exception is :" + e.toString());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return leadStoryExpanded;
	}
	 public static ArrayList leadStoryAndSectionCardDetails()
     throws Exception
 {
     Connection connection=null;
     PreparedStatement pstmt=null;
     ResultSet rs=null;
     ArrayList dataList=null;
     int leadStoryExpanded = 0;
     dataList = new ArrayList();
     HomePageManagerDTO neDTO = null;
     try
     {
         Dbconnection connect = null;
         connect = Dbconnection.getInstance();
         connection = connect.getConnection();
         String sql = "SELECT artical_ids,leadstory_expand FROM jos_itcontrolpannel";
         pstmt = connection.prepareStatement(sql);
         rs = pstmt.executeQuery();
            
         while (rs.next()) {
             neDTO = new HomePageManagerDTO();
             neDTO.setLeadStory(rs.getInt("leadstory_expand"));
             neDTO.setSectionCardDetails(rs.getString("artical_ids") == null ? "" : rs.getString("artical_ids"));
             dataList.add(neDTO);
         }

         
     }
     catch(Exception e)
     {
         System.out.println((new StringBuilder("IT HomePageManagerHelper-isLeadStoryExpanded Exception is :")).append(e.toString()).toString());
     }finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
    
     return dataList;
 }
	 public static ArrayList homePageManagerITTop15(int contentCount, String contentIdToAvoid, int status) throws Exception
		{
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			HomePageManagerDTO lcDTO = null;	
			ArrayList dataList = new ArrayList();
			String contentStatus = "";
			if(status==1) {
				contentStatus = " published=1 and ";
			}
		    
			try {
				Dbconnection connect = null;
				connect = Dbconnection.getInstance();
				connection = connect.getConnection();
				String sql = "select id,`section_name`,`section_url`, date_format( modified,'%M %e, %Y' ) AS mdate,date_format( modified,'%H:%i') AS mtime,content_id, title, short_introtext, introtext, published, kicker_image, kicker_image_alt_text, large_kicker_image_alt_text, " +
						"mobile_image, large_kicker_image, extralarge_image, ordering, content_type, sef_url, strap_headline, byline, modified, " +
						"comment_question, total_comments, primary_category, related_featuredvideo, external_url, `tables`, city, content_type_tag from jos_homepage_manager where "+contentStatus+" display_zone='home_top' ";

				if(contentIdToAvoid.equals("0"))
					sql = sql + " group by id order by right_ordering limit "+contentCount;
				else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
					sql = sql + " and id != "+contentIdToAvoid+" group by id order by right_ordering limit "+contentCount;
				else
					sql = sql + " and id not in ("+contentIdToAvoid+") group by id order by right_ordering limit "+contentCount;
			//System.out.println("@@@@@@@@@@@@@"+sql);
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					lcDTO = new HomePageManagerDTO();
					lcDTO.setContentId(rs.getInt("content_id"));				
					lcDTO.setTitle(rs.getString("title"));
					lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
					lcDTO.setShortDescription(rs.getString("short_introtext") == null ? "" : rs.getString("short_introtext"));
					lcDTO.setKickerImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
					lcDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
					lcDTO.setCommentQuestion(rs.getString("comment_question") == null ? "" : rs.getString("comment_question"));
					lcDTO.setCommentCount(rs.getInt("total_comments"));
					lcDTO.setContentType(rs.getInt("content_type"));
					lcDTO.setPrimaryCategory(rs.getString("primary_category") == null ? "" : rs.getString("primary_category"));
					lcDTO.setRelatedFeaturedVideo(rs.getInt("related_featuredvideo"));
					lcDTO.setExternalUrl(rs.getString("external_url") == null ? "" : rs.getString("external_url"));
					lcDTO.setTables(rs.getString("tables") == null ? "" : rs.getString("tables"));
					lcDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
					lcDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
					lcDTO.setContentTypeTag(rs.getString("content_type_tag") == null ? "" : rs.getString("content_type_tag"));
					lcDTO.setSmallImage(rs.getString("mobile_image") == null ? "" : rs.getString("mobile_image"));
					lcDTO.setLargeImage(rs.getString("large_kicker_image") == null ? "" : rs.getString("large_kicker_image"));
					lcDTO.setLargeImageAltText(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
					lcDTO.setSection_name(rs.getString("section_name") == null ? "" : rs.getString("section_name"));
					lcDTO.setSection_url(rs.getString("section_url") == null ? "" : rs.getString("section_url"));
					lcDTO.setUpdatedDate(rs.getString("mdate"));
					lcDTO.setUpdatedTime(rs.getString("mtime"));
					dataList.add(lcDTO);
				}
			} catch (Exception e) {
				System.out.println("IT CommonFunctions.java-homePageManagerITChunk-Exception is :" + e);
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
	 public static ArrayList newsExpresso(int secid,int status) throws Exception
		{
			Connection connection = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			HomePageManagerDTO homePageMgrDto = null;	
			ArrayList dataList = new ArrayList();
			StringBuffer queryBuff = null;
			String sefUrl = null;
			String checkValue = null;
			try {
				Dbconnection connect = null;
				connect = Dbconnection.getInstance();
				connection = connect.getConnection();
				queryBuff = new StringBuffer("SELECT snippet.id, snippet.snippet_icon, snippet.strapheadline, snippet.headline, snippet.longheadline, snippet.main_text, ")
								.append("snippet.published, snippet.ordering, snippet.sef_url, content.kicker_image ")
								.append("FROM jos_snippets snippet LEFT JOIN jos_content content ON snippet.story_id = content.id WHERE snippet.sectionid= ? ");
				
				if(status==1) {
					queryBuff.append("AND snippet.published=1 AND snippet.strapheadline = CURRENT_DATE ORDER BY snippet.id, snippet.ordering");
				} else {
					queryBuff.append("AND snippet.strapheadline = CURRENT_DATE AND snippet.id >= (SELECT snippet_icon FROM jos_snippets WHERE sectionid=?")
					.append(" ORDER BY id DESC LIMIT 1) ORDER BY snippet.id, snippet.ordering");
				}
				//System.out.println("HomePageManagerHelper.newsExpresso("+secid+", "+status+"): queryBuff >>> "+queryBuff);
				pstmt = connection.prepareStatement(queryBuff.toString());
				pstmt.setInt(1, secid);
				
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					homePageMgrDto = new HomePageManagerDTO();
					checkValue = rs.getString("headline");
					homePageMgrDto.setTitle((checkValue==null?"":checkValue));
					
					checkValue = rs.getString("longheadline");
					homePageMgrDto.setDescription((checkValue==null?"":checkValue));//(checkValue==null?"":checkValue)
					
					checkValue = rs.getString("main_text");
					homePageMgrDto.setShortDescription((checkValue==null?"":checkValue));
					
					checkValue = rs.getString("strapheadline");
					homePageMgrDto.setStrapHeadline((checkValue==null?"":checkValue));
					
					checkValue = rs.getString("kicker_image");
					homePageMgrDto.setKickerImage((checkValue==null?"":checkValue));
					
					sefUrl = rs.getString("sef_url");
					if(sefUrl != null){
						homePageMgrDto.setSefTitle(sefUrl);
					}
									
					dataList.add(homePageMgrDto);
				}
			} catch (Exception e) {
				System.out.println("IT HomePageManagerHelper.java-newsExpresso-Exception is :" + e);
			} finally {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(connection!=null)
					connection.close();
			}
			//System.out.println("HomePageManagerHelper.newsExpresso():List size: "+dataList.size());
			return dataList;
		}

}