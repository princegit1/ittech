package com.itgd.education.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;
import javax.servlet.RequestDispatcher;

import com.itgd.education.utils.DbConnection;
import com.itgd.education.dto.ContentListDTO;
import com.itgd.education.dto.StoryListDTO;
import com.itgd.education.dto.RelatedContentDTO;
import com.itgd.education.dto.StaticContentDTO;

public class CommonFunctions {

	//Functions for URL generation
	//to be used
	public static String storyURL(String contentSefTitle, int pageNum, int contentId) {
		String finalSefTitle = contentSefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String contentUrl = "story/" + finalSefTitle + "/" + pageNum + "/" + contentId + ".html";
		return contentUrl;
	}
	public static String sectionURL(String sectionSefTitle, int pageNum, int sectionId) {
		String sectionUrl = "section/" + sectionId + "/" + pageNum + "/" + sectionSefTitle;
		if(sectionSefTitle.indexOf(".html") < 0)
			sectionUrl = sectionUrl + ".html";
		return sectionUrl;
	}
	public static String sectionListURL(String sectionSefTitle, int pageNum, int sectionId) {
		String sectionUrl = "sectionlist/" + sectionId + "/" + pageNum + "/" + sectionSefTitle;
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
	public static String categoryListURL(String categorySefTitle, int pageNum, int categoryId) {
		String finalSefTitle = categorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String categoryUrl = "categorylist/" + finalSefTitle + "/" + pageNum + "/" + categoryId + ".html";
		return categoryUrl;
	}

	public static String subCategoryURL(String subCategorySefTitle, int pageNum, int subCategoryId) {
		String finalSefTitle = subCategorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String subCategoryUrl = "subcategory/" + finalSefTitle + "/" + pageNum + "/" + subCategoryId + ".html";
		return subCategoryUrl;
	}

	public static String subCategoryListURL(String subCategorySefTitle, int pageNum, int subCategoryId) {
		String finalSefTitle = subCategorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String subCategoryUrl = "subcategorylist/" + finalSefTitle + "/" + pageNum + "/" + subCategoryId + ".html";
		return subCategoryUrl;
	}
	public static String subSubCategoryURL(String subSubCategorySefTitle, int pageNum, int subSubCategoryId) {
		String finalSefTitle = subSubCategorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String subSubCategoryUrl = "subsubcategory/" + finalSefTitle + "/" + pageNum + "/" + subSubCategoryId + ".html";
		return subSubCategoryUrl;
	}
	public static String subSubCategoryListURL(String subSubCategorySefTitle, int pageNum, int subSubCategoryId) {
		String finalSefTitle = subSubCategorySefTitle;
		if(finalSefTitle.indexOf(".html") > 0)
			finalSefTitle = finalSefTitle.replaceAll(".html", "");

		String subSubCategoryUrl = "subsubcategorylist/" + finalSefTitle + "/" + pageNum + "/" + subSubCategoryId + ".html";
		return subSubCategoryUrl;
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

	
	
	
	
	
	
	
	public static String updatedTime() throws Exception {
		Connection conn = null;
		PreparedStatement selectStmt = null;
		ResultSet rs = null;
		String updatedTime = "";
		try {
			DbConnection dbconn = null;
			dbconn = DbConnection.getInstance("intoday");
			conn = dbconn.getConnection("intoday");
			String sql = "SELECT date_format(updated_time, '%M %e | %H:%i IST') as updated_time FROM jos_updatetime order by id desc limit 1";
			selectStmt = conn.prepareStatement(sql);
			rs = selectStmt.executeQuery();
			while (rs.next()) {
				updatedTime = rs.getString("updated_time");
			}
		} catch(SQLException sqls) {
			System.out.println("Breaking News SQLException -> " + sqls);
		}finally{
			if(selectStmt!=null)
				selectStmt.close();
			if(rs!=null)
				rs.close();
			if(conn!=null)
				conn.close();
		}
		return updatedTime;
	}
	
	public static ArrayList breakingNews() throws Exception
	{
		Connection connection = null;
		DbConnection connect = null;
		PreparedStatement pstmt = null,pstmt1 = null;
		ResultSet rs=null,rs1=null;
		ContentListDTO cDTO = null;
		ArrayList breakingList = new ArrayList();
		String breakingLabel = "";
		try {
			String sql = "SELECT enabled, breaking_label FROM jos_breakingnews";
			connect = DbConnection.getInstance("intoday");
			connection = connect.getConnection("intoday");
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				if(rs.getInt("enabled")==1){
					breakingLabel = rs.getString("breaking_label")==null?"":rs.getString("breaking_label");	
					String sqlQuery = "select c.id, c.title, c.sef_url, c.fulltext, date_format(c.created,'%H:%i IST') as news_time, c.`fulltext` " +
							"from jos_content c,jos_article_section a " +
							"where c.breaking_news=1 and c.id=a.article_id and c.state=1 order by a.ordering desc limit 1";
					//System.out.println("breaking sql ->" + sqlQuery);
					pstmt1 = connection.prepareStatement(sqlQuery);
					rs1 = pstmt1.executeQuery();				
					while (rs1.next()) {
						cDTO = new ContentListDTO();
						cDTO.setId(rs1.getInt("id"));
						cDTO.setTitle(rs1.getString("title"));
						cDTO.setSefTitle(rs1.getString("sef_url")==null?"":rs1.getString("sef_url"));
						cDTO.setUpdatedTime(rs1.getString("news_time")==null?"":rs1.getString("news_time"));
						cDTO.setDescription(rs1.getString("fulltext")==null?"":rs1.getString("fulltext"));
						cDTO.setContentType(breakingLabel);
						breakingList.add(cDTO);
					}
				}	
			}
		} catch (Exception e) {
			System.out.println("IT CommonFunctions.java - breakingNews function - Exception ->" + e);
		}finally{
			if(pstmt!=null)
				pstmt.close();
			if(pstmt1!=null)
				pstmt1.close();
			if(rs!=null)
				rs.close();
			if(rs1!=null)
				rs1.close();
			if(connection!=null)
				connection.close();
		}
		return breakingList;
	}

public static ArrayList latestStoriesChunk(String primaryCatId, int contentCount, String contentIdToAvoid) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ContentListDTO cDTO = null;
	ArrayList cAL = new ArrayList();
	String contentIdsToCheck = "";
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
	pcatlength = pcatids.length;
	int ctr=0;
	try {
		DbConnection connect = null;
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
		String sql = "select c.id, c.title,c.sef_url, c.byline, c.kicker_image, c.kicker_image_alt_text, c.introtext, date_format(c.created,'%M %e, %Y') AS cdate, date_format(c.modified,'%M %e, %Y') AS mdate, date_format(c.modified,'%H:%i') AS mtime, ";

		if(pcatlength==1)
			sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and c.id=jas.article_id and c.state=1 and jas.section_id=js.id and js.published=1 ";
		else if(pcatlength==2) 
			sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and c.id=jas.article_id and c.state=1 and jc.id=jas.cat_id  and jc.section=js.id and jc.published=1 and js.published=1 ";
		else if(pcatlength==3) 
			sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and c.state=1 and c.id=jas.article_id and jsc.id=jas.subcat_id and jsc.category=jc.id and jsc.section=js.id and jsc.published=1 and jc.published=1 and js.published=1 ";
		else if(pcatlength==4) 
			sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and c.state=1 and c.id=jas.article_id and jssc.id=jas.sub_subcat_id and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 ";

		sql = sql + " "+contentIdsToCheck+" and c.title not like '%href=%' group by c.id order by jas.ordering desc limit "+contentCount;
		//System.out.println("sql->"+sql);	
		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			cDTO = new ContentListDTO();
			cDTO.setId(rs.getInt("id"));
			cDTO.setTitle(rs.getString("title") == null ? "" : rs.getString("title"));
			cDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			cDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
			cDTO.setImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
			cDTO.setImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
			cDTO.setDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
			cDTO.setCurrentCategoryId(rs.getInt("curcatid"));
			cDTO.setCurrentCategoryTitle(rs.getString("curcatname") == null ? "" : rs.getString("curcatname"));
			cDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl") == null ? "" : rs.getString("curcatsefurl"));
			cDTO.setCurrentCategoryPath(rs.getString("template_path") == null ? "" : rs.getString("template_path"));

			cDTO.setCreatedDate(rs.getString("cdate") == null ? "" : rs.getString("cdate"));
			cDTO.setUpdatedDate(rs.getString("mdate") == null ? "" : rs.getString("mdate"));
			cDTO.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));
			cAL.add(cDTO);
			ctr++;
		}
	} catch (Exception e) {
		System.out.println("global_include.jsp (latestContent for "+primaryCatId+") Exception is :" + e);
		e.printStackTrace();
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return cAL;
}



public static ArrayList latestStoriesTemplate(int catId, int contentCount, String contentIdToAvoid, int catLevel) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	StoryListDTO cDTO = null;
	ArrayList cAL = new ArrayList();
	String contentIdsToCheck = "";
	//System.out.println(catLevel);
	if(catLevel!=1 && catLevel!=2 && catLevel!=3 && catLevel!=4) {
		catLevel=1;
		catId=120;
	}

	if(!contentIdToAvoid.equals("0")) {
		if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
			contentIdsToCheck = " and c.id != "+contentIdToAvoid+" ";
		else
			contentIdsToCheck = " and c.id not in ("+contentIdToAvoid+") ";
	}
	int ctr=0;
	try {
		DbConnection connect = null;
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
		String sql = "select c.id, c.title,c.sef_url, c.mobile_image, c.kicker_image_alt_text, ";

		if(catLevel==1)
			sql = sql + "js.title as stitle, js.id as sid, js.sef_url as sseftitle, js.metatitle, js.metakey, js.metadesc " +
					"from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+catId+" and c.id=jas.article_id and c.state=1 and jas.section_id=js.id and js.published=1 ";
		else if(catLevel==2) 
			sql = sql + "js.title as stitle, js.id as sid, js.sef_url as sseftitle, jc.title as ctitle, jc.id as cid, jc.sef_url as cseftitle, jc.metatitle, jc.metakey, jc.metadesc " +
					"from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+catId+" and c.id=jas.article_id and c.state=1 and jc.id=jas.cat_id  and jc.section=js.id and jc.published=1 and js.published=1 ";
		else if(catLevel==3) 
			sql = sql + "js.title as stitle, js.id as sid, js.sef_url as sseftitle, jc.title as ctitle, jc.id as cid, jc.sef_url as cseftitle, " +
					"jsc.title as sctitle, jsc.id as scid, jsc.sef_url as scseftitle, jsc.metatitle, jsc.metakey, jsc.metadesc " +
					"from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+catId+" and c.state=1 and c.id=jas.article_id and jsc.id=jas.subcat_id and jsc.category=jc.id and jsc.section=js.id and jsc.published=1 and jc.published=1 and js.published=1 ";
		else if(catLevel==4) 
			sql = sql + "js.title as stitle, js.id as sid, js.sef_url as sseftitle, jc.title as ctitle, jc.id as cid, jc.sef_url as cseftitle, " +
					"jsc.title as sctitle, jsc.id as scid, jsc.sef_url as scseftitle, jssc.title as ssctitle, jssc.id as sscid, jssc.sef_url as sscseftitle, " +
					"jssc.metatitle, jssc.metakey, jssc.metadesc " +
					"from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc " +
					"where jas.sub_subcat_id="+catId+" and c.state=1 and c.id=jas.article_id and jssc.id=jas.sub_subcat_id and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 ";

		sql = sql + " "+contentIdsToCheck+" and c.title not like '%href=%' group by c.id order by jas.ordering desc limit "+contentCount;
		//System.out.println(sql);

		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();

		while (rs.next()) {
			cDTO = new StoryListDTO();
			cDTO.setId(rs.getInt("id"));
			cDTO.setTitle(rs.getString("title") == null ? "" : rs.getString("title"));
			cDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			cDTO.setImage(rs.getString("mobile_image") == null ? "" : rs.getString("mobile_image"));
			cDTO.setImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
			cDTO.setMetaTitle(rs.getString("metatitle") == null ? "" : rs.getString("metatitle"));
			cDTO.setMetaKeyword(rs.getString("metakey") == null ? "" : rs.getString("metakey"));
			cDTO.setMetaDescription(rs.getString("metadesc") == null ? "" : rs.getString("metadesc"));
			cDTO.setSectionId(rs.getInt("sid"));
			cDTO.setSectionTitle(rs.getString("stitle") == null ? "" : rs.getString("stitle"));
			cDTO.setSectionSefTitle(rs.getString("sseftitle") == null ? "" : rs.getString("sseftitle"));
			if(catLevel>=2) {
				cDTO.setCategoryId(rs.getInt("cid"));
				cDTO.setCategoryTitle(rs.getString("ctitle") == null ? "" : rs.getString("ctitle"));
				cDTO.setCategorySefTitle(rs.getString("cseftitle") == null ? "" : rs.getString("cseftitle"));
			}
			if(catLevel>=3) {
				cDTO.setSubCategoryId(rs.getInt("scid"));
				cDTO.setSubCategoryTitle(rs.getString("sctitle") == null ? "" : rs.getString("sctitle"));
				cDTO.setSubCategorySefTitle(rs.getString("scseftitle") == null ? "" : rs.getString("scseftitle"));
			}
			if(catLevel>=4) {
				cDTO.setSubSubCategoryId(rs.getInt("sscid"));
				cDTO.setSubSubCategoryTitle(rs.getString("ssctitle") == null ? "" : rs.getString("ssctitle"));
				cDTO.setSubSubCategorySefTitle(rs.getString("sscseftitle") == null ? "" : rs.getString("sscseftitle"));
			}
			cAL.add(cDTO);
			ctr++;
		}
	} catch (Exception e) {
		System.out.println("IT WAP CommonFunctions (latestContent for "+catId+") Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return cAL;
}



public static ArrayList relatedContent(String contentids, String contenttype, int countToFetch) throws Exception {
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
		DbConnection dbConn = DbConnection.getInstance("intoday");
		conn  = dbConn.getConnection("intoday");
		String selectStmt = "SELECT article_id, related_article_id, related_title, related_type, featured " +
				"FROM jos_related_stories where article_id "+contentIdCondition+" and " +
				"content_type = '"+contenttype+"' group by article_id, related_article_id order by article_id, content_type, related_order asc ";
		if(countToFetch!=0) {
			selectStmt = selectStmt + " limit " + countToFetch;
		}

		//System.out.println("Related Query - " + selectStmt);
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
					selectStmtP1 = "SELECT thumb_image, Gallery_name, sef_url FROM jos_gallerynames where id=?";
					//System.out.println("Related Query Gallery  - " + selectStmtP1);
					pstmt1 = conn.prepareStatement(selectStmtP1);
					pstmt1.setInt(1, rs.getInt("related_article_id"));
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						rcDTO.setRelatedContentSefTitle(rs1.getString("sef_url")==null?"":rs1.getString("sef_url"));
						rcDTO.setImage(rs1.getString("thumb_image")==null?"":rs1.getString("thumb_image"));
						rcDTO.setImageAltText(rs1.getString("Gallery_name")==null?"":rs1.getString("Gallery_name"));
					}
				} else if(rs.getString("related_type").equals("text") || rs.getString("related_type").equals("video")) {
					selectStmtP1 = "SELECT mobile_image, kicker_image_alt_text, sef_url FROM jos_content where id=?";
					//System.out.println("Related Query Text/Video  - " + selectStmtP1);
					pstmt1 = conn.prepareStatement(selectStmtP1);
					pstmt1.setInt(1, rs.getInt("related_article_id"));
					rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						rcDTO.setRelatedContentSefTitle(rs1.getString("sef_url")==null?"":rs1.getString("sef_url"));
						rcDTO.setImage(rs1.getString("mobile_image")==null?"":rs1.getString("mobile_image"));
						rcDTO.setImageAltText(rs1.getString("kicker_image_alt_text")==null?"":rs1.getString("kicker_image_alt_text"));
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
		System.out.println("relatedContent Content SQLException (CommonFunctions.java) -> " + sqls);
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


public static String LikeDislike(int articleId,String content_type )throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DbConnection connect = null;
	String LikeDislikeStr="";
	int upcount=0;
	int downcount=0;
	int totalcount=0;
	try {
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
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
		System.out.println("education today Story Like-dislike Exception in common function is :" + e);
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
	DbConnection connect = null;
	String LikeDislikeStr="";
	int upcount=0;
	int downcount=0;
	int totalcount=0;
	try {
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
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
		System.out.println("education today Story Like-dislike Exception in common function is :" + e);
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


public static String sectionInfo(String id, String templateName) throws Exception {
	Connection connection = null;
	PreparedStatement pSelectStmt=null;
	ResultSet rs = null;
	String sectionInfo = "";
	String sql = "";
	try {
		DbConnection connect = null;
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
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
	} catch (Exception e) {
		System.out.println("sectionInfo - CommonFunctions Exception is ;" + e);
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




public static ArrayList latestVideoChunk(String categoryId, int contentCount, String contentIdToAvoid) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ContentListDTO lcDTO = null;	
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
    if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
		contentIdCondition = " and c.id != "+contentIdToAvoid+" ";
	else
		contentIdCondition = " and c.id not in ("+contentIdToAvoid+") ";

	try {
		DbConnection connect = null;
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
		
		String sql = "select c.id, c.title,c.sef_url, c.mobile_image as small_image,c.kicker_image_alt_text, ";
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
			if(latestCtr==0)
				latestids = "" + rs.getInt("id");
			else
				latestids += ", " + rs.getInt("id");
				
			lcDTO = new ContentListDTO();
			lcDTO.setId(rs.getInt("id"));				
			lcDTO.setTitle(rs.getString("title"));
			lcDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			lcDTO.setImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
			lcDTO.setImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
			lcDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
			lcDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
			lcDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl") == null ? "" : rs.getString("curcatsefurl"));		
			lcDTO.setCurrentCategoryPath(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
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



public static ArrayList subLevelCategoryDetail(int countToFetch, int categoryId, String categoryIdToAvoid, String articleTemplate) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	PreparedStatement pstmt1 = null;
	ResultSet rs1 = null;
	ContentListDTO aDTO = null;	
	ArrayList dataList = new ArrayList();
	String categoryTemplate = "section";
	String sql = "";
	String sql1 = "";
	String categoryCheck = "";
	
	if(articleTemplate.equals("section") || articleTemplate.equals("category") || articleTemplate.equals("subcategory") || articleTemplate.equals("subsubcategory")) {
		categoryTemplate = articleTemplate; 
	}
	//System.out.println("1. ->"+countToFetch+"--3. ->"+categoryId+"--4. ->"+categoryIdToAvoid+"--5. ->"+articleTemplate );

	if(!categoryIdToAvoid.equals("0") && categoryIdToAvoid.indexOf(",") < 0)
		categoryCheck = " and a.id != "+categoryIdToAvoid;
	else if(categoryIdToAvoid.indexOf(",") > 0)
		categoryCheck = " and a.id not in ("+categoryIdToAvoid+")";
	else
		categoryCheck = "";
	try {
		DbConnection connect = null;
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");

		sql = "select a.id from ";
		if(articleTemplate.equals("section")) {
			sql = sql + "jos_categories a, jos_sections b WHERE b.id="+categoryId+" and b.published=1 and a.section=b.id and a.published=1 ";
		} else if(articleTemplate.equals("category")) { 
			sql = sql + "jos_subcategories a, jos_categories b WHERE b.id="+categoryId+" and b.published=1 and b.id=a.category and a.published=1 ";
		} else if(articleTemplate.equals("subcategory")) { 
			sql = sql + "jos_sub_subcategories a, jos_subcategories b WHERE b.id="+categoryId+" and b.published=1 and b.id=a.sub_category and a.published=1 ";
		}
		//Modified dated on 14-01-2015 on request of Megha/Aditi to change order as Alphabetical 
		if("category".equalsIgnoreCase(articleTemplate)){
			sql = sql + categoryCheck + " GROUP BY a.id order by a.title ASC";
		} else {
			sql = sql + categoryCheck + " GROUP BY a.id order by a.ordering desc";
		}
		if(countToFetch > 0)
			sql = sql + " limit "+countToFetch;	
		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		System.out.println("CommonFuctions.subLevelCategoryDetail() ---->"+sql);
		
		while (rs.next()) {
			sql1 = "SELECT b.id, b.title, b.sef_url, b.template_path from jos_content c, jos_article_section a, ";
			if(articleTemplate.equals("section"))
				sql1 = sql1 + "jos_categories b  WHERE c.id=a.article_id and c.state=1 and a.cat_id = "+rs.getInt("id")+" and a.cat_id=b.id ";
			else if(articleTemplate.equals("category")) 
				sql1 = sql1 + "jos_subcategories b  WHERE c.id=a.article_id and c.state=1 and a.subcat_id = "+rs.getInt("id")+" and a.subcat_id=b.id ";
			else if(articleTemplate.equals("subcategory")) 
				sql1 = sql1 + "jos_sub_subcategories b  WHERE c.id=a.article_id and c.state=1 and a.sub_subcat_id = "+rs.getInt("id")+" and a.sub_subcat_id=b.id ";
			sql1 = sql1 + " order by b.ordering limit 1";
			pstmt1 = connection.prepareStatement(sql1);
			rs1 = pstmt1.executeQuery();
			System.out.println("CommonFuctions.subLevelCategoryDetail() ---->"+sql);
			while (rs1.next()) {
				aDTO = new ContentListDTO();
				aDTO.setCurrentCategoryId(rs1.getInt("id"));		
				aDTO.setCurrentCategoryTitle(rs1.getString("title"));		
				aDTO.setCurrentCategorySefTitle(rs1.getString("sef_url"));		
				aDTO.setCurrentCategoryPath(rs1.getString("template_path") == null ? "" : rs1.getString("template_path"));		
				dataList.add(aDTO);
			}
		}
	} catch (Exception e) {
		System.out.println("CommonFunctions subLevelCategoryDetail Exception is :" + e);
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
	return dataList;
}

public static ArrayList staticContent(String staticTitle, int staticContentSate) throws Exception {
	System.out.println("CommonFunctions.staticContent("+staticTitle+", "+staticContentSate+") method started");
	Connection connection = null;
	PreparedStatement pSelectStmt=null;
	ResultSet rs = null;
	StaticContentDTO staticDTO = null;
	ArrayList datalist = new ArrayList();
	String staticContentSql = null;
	try {
		DbConnection connect = null;
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
		if(!"Education-Word".equalsIgnoreCase(staticTitle))
			staticContentSql = "SELECT title, introtext FROM jos_staticcontent WHERE title = ? AND state=? group by ?";
		else 
			staticContentSql = "SELECT title, introtext FROM jos_staticcontent WHERE title = ? AND state=? AND created = CURRENT_DATE group by ?";
		//staticContentSql = "SELECT title, introtext FROM jos_staticcontent WHERE title = ? AND state=? group by ?";
		
		System.out.println("CommonFunctions.staticContent("+staticTitle+", "+staticContentSate+"):staticContentSql:: "+staticContentSql);
		pSelectStmt = connection.prepareStatement(staticContentSql);
		pSelectStmt.setString(1, staticTitle);
		pSelectStmt.setInt(2, staticContentSate);
		pSelectStmt.setString(3, staticTitle);
		rs = pSelectStmt.executeQuery();
		while (rs.next()) {
			staticDTO = new StaticContentDTO();
			staticDTO.setStaticContentTitle(rs.getString("title")==null?"":rs.getString("title"));
			staticDTO.setStaticContentText(rs.getString("introtext")==null?"":rs.getString("introtext"));
			datalist.add(staticDTO);
		}
	} catch (Exception e) {
		System.out.println("staticContent commonfunctions Exception is ;" + e);
		e.printStackTrace();
	} finally {
		if(rs!=null)
			rs.close();
		if(pSelectStmt!=null)
			pSelectStmt.close();
		if(connection!=null)
			connection.close();
	}
	System.out.println("CommonFunctions.staticContent("+staticTitle+", "+staticContentSate+"):datalist:: "+datalist.size());
	return datalist;
}



public static ArrayList storiesAjaxChunk(String primaryCatId, int contentCount, String contentIdToAvoid) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ContentListDTO cDTO = null;
	ArrayList cAL = new ArrayList();
	String contentIdsToCheck = "";
	String limitClause = "";
	if(!contentIdToAvoid.equals("0")) {
		if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
			contentIdsToCheck = " and c.id != "+contentIdToAvoid+" ";
		else
			contentIdsToCheck = " and c.id not in ("+contentIdToAvoid+") ";
	}
	if(contentCount!=0) {
			limitClause = " limit " + contentCount;
	}

	try {
		DbConnection connect = null;
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
		String sql = "select c.id, c.title,c.sef_url from jos_content c, jos_article_section jas " +
				"where jas.subcat_id="+primaryCatId+" and c.state=1 and c.id=jas.article_id ";
		sql = sql + " "+contentIdsToCheck+" group by c.id order by jas.ordering desc "+limitClause;
		System.out.println("sql->"+sql);	
		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		while (rs.next()) {
			cDTO = new ContentListDTO();
			cDTO.setId(rs.getInt("id"));
			cDTO.setTitle(rs.getString("title") == null ? "" : rs.getString("title"));
			cDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			cAL.add(cDTO);
		}
	} catch (Exception e) {
		System.out.println("storiesAjaxChunk commonfunctions Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return cAL;
}


public static ArrayList storyListTemplate (int currentPageNum, int countToFetch, int categoryId, String articleIdToAvoid, String articleTemplate) throws Exception
{
	Connection connection = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	ContentListDTO cDTO = null;	
	ArrayList cAL = new ArrayList();
	int totalArticleCount = 0;
	String sql = "";
	int startLimit = 0;
	startLimit = (currentPageNum-1)*countToFetch;
	int endLimit = countToFetch;
	
	String categoryTemplate = "section";
	if(articleTemplate.equals("section") || articleTemplate.equals("category") || articleTemplate.equals("subcategory") || articleTemplate.equals("subsubcategory")) {
		categoryTemplate = articleTemplate; 
	}
	System.out.println("1. ->"+currentPageNum+"--2. ->"+countToFetch+"--3. ->"+categoryId+"--4. ->"+articleIdToAvoid+"--5. ->"+articleTemplate );

	try {
		DbConnection connect = null;
		connect = DbConnection.getInstance("intoday");
		connection = connect.getConnection("intoday");
		sql = "select count(distinct c.id) as totalcount ";
		if(articleTemplate.equals("section"))
			sql = sql + "from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+categoryId+" and jas.section_id=js.id and js.published=1 and ";
		else if(articleTemplate.equals("category")) 
			sql = sql + "from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+categoryId+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
		else if(articleTemplate.equals("subcategory")) 
			sql = sql + "from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+categoryId+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
		else if(articleTemplate.equals("subsubcategory")) 
			sql = sql + "from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+categoryId+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";

		sql = sql + "c.state=1 and c.id=jas.article_id";

		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		System.out.println("Latest Headlines count---->"+sql);
		while (rs.next()) {
			totalArticleCount = rs.getInt("totalcount");
		}
		sql = "";
		pstmt = null;
		rs = null;
		
		sql = "select c.id, c.title, c.sef_url, c.strap_headline, c.kicker_image, c.kicker_image_alt_text, c.introtext, " +
		"c.byline, c.city, DATE_FORMAT(c.created,'%M %e, %Y') as created, DATE_FORMAT(c.modified,'%M %e, %Y') as modified ";

		if(articleTemplate.equals("section"))
			sql = sql + " from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+categoryId+" and jas.section_id=js.id and js.published=1 and ";
		else if(articleTemplate.equals("category")) 
			sql = sql + " from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+categoryId+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
		else if(articleTemplate.equals("subcategory")) 
			sql = sql + " from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+categoryId+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
		else if(articleTemplate.equals("subsubcategory")) 
			sql = sql + "from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+categoryId+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";

		if(articleIdToAvoid.equals("0"))
			sql = sql + "c.state=1 and c.id=jas.article_id ";
		else if(!articleIdToAvoid.equals("0") && articleIdToAvoid.indexOf(",") < 0)
			sql = sql + "c.state=1 and c.id=jas.article_id and c.id != "+articleIdToAvoid;
		else
			sql = sql + "c.state=1 and c.id=jas.article_id and c.id not in ("+articleIdToAvoid+")";
		sql = sql + " group by jas.article_id order by jas.ordering desc limit "+startLimit + ", "+endLimit;

		pstmt = connection.prepareStatement(sql);
		rs = pstmt.executeQuery();
		System.out.println("Latest Headlines---->"+sql);
		while (rs.next()) {
			cDTO = new ContentListDTO();
			cDTO.setId(rs.getInt("id"));
			cDTO.setTitle(rs.getString("title") == null ? "" : rs.getString("title"));
			cDTO.setSefTitle(rs.getString("sef_url") == null ? "" : rs.getString("sef_url"));
			cDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
			cDTO.setImage(rs.getString("kicker_image") == null ? "" : rs.getString("kicker_image"));
			cDTO.setImageAltText(rs.getString("kicker_image_alt_text") == null ? "" : rs.getString("kicker_image_alt_text"));
			cDTO.setDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
			cDTO.setStrapHeadline(rs.getString("strap_headline") == null ? "" : rs.getString("strap_headline"));
			cDTO.setByline(rs.getString("byline"));
			cDTO.setCity(rs.getString("city"));
			cDTO.setCreatedDate(rs.getString("created"));
			cDTO.setUpdatedDate(rs.getString("modified"));
			cDTO.setContentCount(totalArticleCount);
			cAL.add(cDTO);
		}
	} catch (Exception e) {
		System.out.println("CommonFunctions storyListTemplate Exception is :" + e);
	} finally {
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(connection!=null)
			connection.close();
	}
	return cAL;
}

}
