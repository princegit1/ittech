package com.itgd.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itgd.conn.Dbconnection; 
import com.itgd.dto.LatestContentDTO;
import com.itgd.dto.StoryDTO;
import com.itgd.dto.byLineDTO;
import com.itgd.utils.Constants;
import com.itgd.dto.RelatedContentDTO; 
import com.itgd.utils.CommonFunctions;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class StoryHelper {
	public static ArrayList storyDetail(int storyId, int publishedStatus) throws Exception
	{
    	//System.out.println("Story "+storyId + "----ststus--"+publishedStatus);

		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ArrayList storyData = new ArrayList();
		String storyStatus = "";
		if(publishedStatus==1)
			storyStatus = " and c.state=1 ";
		
	    int primaryCategoryLength = 0;
	    String primaryCategory = "0";
	    String primaryCategoryId[] = null;
		
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT c.story_highlights_flag,c.story_highlights,c.comment_question,c.id,c.reporter_twitter_handle,c.ismedium,c.id, c.issueid, c.primary_category, c.title, c.title_alias, title_magazine, c.sef_url, c.byline, c.city, c.courtesy, " +
	    			"c.images as imagepath, c.introtext, c.fulltext, c.strap_headline, c.metadesc, c.metakey, c.metatags, c.story_syndication, " +
	    			"date_format( c.created,'%M %e, %Y' ) AS crt, date_format( c.modified,'%M %e, %Y' ) AS mdate, date_format( c.created,'%Y-%m-%d' ) AS snd, c.article_icon, c.article_icon_img, " +
	    			"date_format( c.created,'%H:%i') AS ctime, date_format( c.modified,'%H:%i') AS mtime,  c.quotes, c.blurbs,	c.tables, c.article_icon, " +
	    			"c.article_icon_img, c.kicker_image, c.large_kicker_image, c.large_kicker_image_width, c.kicker_image_caption, c.large_kicker_image_alt_text, c.indiatoday_expert, c.google_standout, " +
	    			"c.kicker_image, c.mobile_image, c.commentbox_flag, c.extralarge_image, c.byline_modified_by,c.content_url,c.is_external,c.external_url  " +
	    			"FROM jos_content c, jos_article_section a WHERE c.id = ? and c.content_type='0' and a.article_id=c.id "+storyStatus+" group by c.id";
	    	System.out.println("Story Detail ->"+sql);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, storyId);
            //pstmt.setInt(2, storyStatus);
            rs = pstmt.executeQuery ();
	    	//System.out.println("Story Detail 111->"+sql);
			while (rs.next ()){
		        StoryDTO sDTO = new StoryDTO();
		        sDTO.setId(rs.getInt("id"));
		    	//System.out.println("Story Detail ->"+rs.getInt("id"));
		        sDTO.setLongDescription(rs.getString("fulltext"));
		        sDTO.setCommentQuestion(rs.getString("comment_question"));
		        sDTO.setTitleAlias(rs.getString("title_alias"));		       
		        sDTO.setTitleMagazine(rs.getString("title_magazine") == null ? "" : rs.getString("title_magazine"));		       
		        sDTO.setTitle(rs.getString("title"));
		        sDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline")); 
		        sDTO.setBylineModifiedBy(rs.getString("byline_modified_by") == null ? "" : rs.getString("byline_modified_by")); 
		        sDTO.setCity(rs.getString("city"));
		        sDTO.setShortDescription(rs.getString("introtext"));
		        sDTO.setStoryImage(rs.getString("imagepath") == null ? "" : rs.getString("imagepath"));
		        sDTO.setSefTitle(rs.getString("sef_url"));
		        sDTO.setCreatedDate(rs.getString("crt") == null ? "" : rs.getString("crt"));
		        sDTO.setUpdatedDate(rs.getString("mdate") == null ? "" : rs.getString("mdate"));
				sDTO.setCreatedTime(rs.getString("ctime") == null ? "" : rs.getString("ctime"));
				sDTO.setUpdatedTime(rs.getString("mtime") == null ? "" : rs.getString("mtime"));
		        sDTO.setSyndication(rs.getInt("story_syndication"));
		        sDTO.setSyndicationDate(rs.getString("snd") == null ? "" : rs.getString("snd"));
		        sDTO.setMetaKeyword(rs.getString("metakey"));
		        sDTO.setMetaDescription(rs.getString("metadesc"));
		        sDTO.setCourtesy(rs.getString("courtesy") == null ? "" : rs.getString("courtesy"));
		        sDTO.setBlurb(rs.getString("blurbs") == null ? "" : rs.getString("blurbs"));
		        sDTO.setTable(rs.getString("tables") == null ? "" : rs.getString("tables"));
		        sDTO.setQuote(rs.getString("quotes") == null ? "" : rs.getString("quotes"));
		        sDTO.setStoryIcon(rs.getInt("article_icon"));
		        sDTO.setStoryIconImage(rs.getString("article_icon_img") == null ? "" : rs.getString("article_icon_img"));
		        sDTO.setLargeKickerImage(rs.getString("large_kicker_image")== null?"":rs.getString("large_kicker_image"));
		        sDTO.setLargeKickerImageWidth(rs.getString("large_kicker_image_width") == null ? "0" : rs.getString("large_kicker_image_width"));
		        sDTO.setLargeKickerImageCaption(rs.getString("kicker_image_caption") == null ? "" : rs.getString("kicker_image_caption"));
		        sDTO.setLargeKickerImageAltText(rs.getString("large_kicker_image_alt_text") == null ? "" : rs.getString("large_kicker_image_alt_text"));
	        	sDTO.setPrimaryCategory(rs.getString("primary_category") == null ? "0" : rs.getString("primary_category"));
		        primaryCategory = rs.getString("primary_category");
	        	sDTO.setIssueId(rs.getInt("issueid"));
		        sDTO.setMetaTags(rs.getString("metatags") == null ? "" : rs.getString("metatags"));
		        sDTO.setExpertComment(rs.getString("indiatoday_expert") == null ? "" : rs.getString("indiatoday_expert"));
		        sDTO.setGoogleStandout(rs.getInt("google_standout"));
		        sDTO.setKickerImage(rs.getString("kicker_image")== null?"":rs.getString("kicker_image"));
		        sDTO.setSmallImage(rs.getString("mobile_image")== null?"":rs.getString("mobile_image"));
	        	sDTO.setCommentboxFlag(rs.getInt("commentbox_flag"));
		        sDTO.setExtraLargeImage(rs.getString("extralarge_image")== null?"":rs.getString("extralarge_image"));
				sDTO.setContentUrl(rs.getString("content_url")== null?"":rs.getString("content_url"));
		        sDTO.setExternalUrl(rs.getString("external_url")== null?"":rs.getString("external_url"));
		        sDTO.setIsExternal(rs.getInt("is_external"));
		        sDTO.setMedium(rs.getInt("ismedium"));
		        sDTO.setTwitterHandle(rs.getString("reporter_twitter_handle"));
		        sDTO.setStoryHighlightsFlag(rs.getInt("story_highlights_flag"));
		        sDTO.setStoryHighlights(rs.getString("story_highlights") == null ? "" : rs.getString("story_highlights"));
	        	
		    	//System.out.println("Story 1"+primaryCategory);
		    	try { 
			        if(primaryCategory != null && primaryCategory.trim().length() > 0 ){
				    	//System.out.println("Story 2");
					    if((primaryCategory.indexOf("#") > 0) && (primaryCategory.lastIndexOf("#") < primaryCategory.trim().length())) {
					    	primaryCategoryId = primaryCategory.split("#");
					    	primaryCategoryLength = primaryCategoryId.length;
					    	//System.out.println("PCAT length - "+pcatlength);
					    } else {
					    	primaryCategoryId = new String[1];
					    	primaryCategoryId[0] = primaryCategory;
					    }
				    	primaryCategoryLength = primaryCategoryId.length;
				    	String sql1 = "select js.id as jsid, js.title as jstitle, js.sef_url as jssefurl, js.template_path as jspath, js.header_image, js.middlenav_path, ";
				    	if(primaryCategoryLength==1) {
							sql1 = sql1 + " js.topnav_path, js.rightnav_path, js.bottomnav_path, js.leftnav_path " +
									"from jos_sections js where js.id="+primaryCategoryId[0];
				    	}
				    	if(primaryCategoryLength==2) {
							sql1 = sql1 + " jc.title as jctitle, jc.id as jcid, jc.sef_url as jcsefurl, jc.topnav_path, jc.rightnav_path, jc.bottomnav_path, jc.leftnav_path, jc.template_path as jcpath " +
									"from jos_categories jc, jos_sections js where jc.id="+primaryCategoryId[1]+" and jc.section=js.id";
				    	}
				    	if(primaryCategoryLength==3) {
							sql1 = sql1 + " jc.title as jctitle, jc.id as jcid, jc.sef_url as jcsefurl, jc.template_path as jcpath, jsc.title as jsctitle, jsc.id as jscid, jsc.sef_url as jscsefurl, jsc.topnav_path, jsc.rightnav_path, jsc.bottomnav_path, jsc.leftnav_path, jsc.template_path as jscpath " +
									"from jos_subcategories jsc, jos_sections js, jos_categories jc where jsc.id="+primaryCategoryId[2]+" and  jsc.category=jc.id and jsc.section=js.id";
				    	}
				    	if(primaryCategoryLength==4) {
							sql1 = sql1 + " jc.title as jctitle, jc.id as jcid, jc.sef_url as jcsefurl, jc.template_path as jcpath, jsc.title as jsctitle, jsc.id as jscid, jsc.sef_url as jscsefurl, jsc.template_path as jscpath, jssc.title as jssctitle, jssc.id as jsscid, jssc.sef_url as jsscsefurl, jssc.topnav_path, jssc.rightnav_path, jssc.bottomnav_path, jssc.leftnav_path, jssc.template_path as jsscpath " +
									"from jos_sub_subcategories jssc, jos_sections js, jos_categories jc, jos_subcategories jsc where jssc.id="+primaryCategoryId[3]+" and jssc.section=js.id and jssc.category=jc.id and jssc.sub_category=jsc.id";
				    	}
				    System.out.println("Story Primary Detail Nitin31 oct 15->"+sql1);
	
				    	pstmt1 = connection.prepareStatement(sql1);
				    	rs1 = pstmt1.executeQuery();
						while (rs1.next ()){
							//System.out.println("primaryCategoryLength ->"+primaryCategoryLength);
							sDTO.setSectionId(rs1.getInt("jsid"));
				        	sDTO.setSectionTitle(rs1.getString("jstitle"));
				        	sDTO.setSectionSefTitle(rs1.getString("jssefurl"));
							sDTO.setSectionPageURL(rs1.getString("jspath") == null ? "" : rs1.getString("jspath"));		
							sDTO.setHeaderImage(rs1.getString("header_image") == null ? "" : rs1.getString("header_image"));						
							sDTO.setMiddleNavigationPath(rs1.getString("middlenav_path") == null ? "" : rs1.getString("middlenav_path"));		
				        	
				        	if(primaryCategoryLength >= 2) {
					        	sDTO.setCategoryId(rs1.getInt("jcid"));
					        	sDTO.setCategoryTitle(rs1.getString("jctitle"));
					        	sDTO.setCategorySefTitle(rs1.getString("jcsefurl"));
								sDTO.setCategoryPageURL(rs1.getString("jcpath") == null ? "" : rs1.getString("jcpath"));		
					    	} 
				        	if(primaryCategoryLength >= 3) {
					        	sDTO.setSubCategoryId(rs1.getInt("jscid"));
					        	sDTO.setSubCategoryTitle(rs1.getString("jsctitle"));
					        	sDTO.setSubCategorySefTitle(rs1.getString("jscsefurl"));
								sDTO.setSubCategoryPageURL(rs1.getString("jscpath") == null ? "" : rs1.getString("jscpath"));		
					    	} 
				        	if(primaryCategoryLength == 4) {
					        	sDTO.setSubSubCategoryId(rs1.getInt("jsscid"));
					        	sDTO.setSubSubCategoryTitle(rs1.getString("jssctitle"));
					        	sDTO.setSubSubCategorySefTitle(rs1.getString("jsscsefurl"));
								sDTO.setSubSubCategoryPageURL(rs1.getString("jsscpath") == null ? "" : rs1.getString("jsscpath"));		
					    	}
				        	sDTO.setTopNavigationPath(rs1.getString("topnav_path") == null ? "" : rs1.getString("topnav_path"));
				        	sDTO.setRightNavigationPath(rs1.getString("rightnav_path") == null ? "" : rs1.getString("rightnav_path"));
				        	sDTO.setBottomNavigationPath(rs1.getString("bottomnav_path") == null ? "" : rs1.getString("bottomnav_path"));
				        	sDTO.setLeftNavigationPath(rs1.getString("leftnav_path") == null ? "" : rs1.getString("leftnav_path"));
						}
			        }
				} catch (Exception e) {
					System.out.println("StoryHelper articleDetail Primary Category Detail Exception is :" + e.toString());
				}
		        
		    	pstmt1 = null;
		    	rs1 = null;
		    	try {
			        if(rs.getInt("issueid") > 0){
				    	String sql2 = "SELECT id as issueid, title as issuetitle, sef_url as issuesefurl " +
				    			"FROM jos_magazine_categories where id="+rs.getInt("issueid");
				    	//System.out.println("Story Issue Detail ->"+sql2);
				    	pstmt1 = connection.prepareStatement(sql2);
				    	rs1 = pstmt1.executeQuery();
						while (rs1.next ()){
				        	sDTO.setIssueTitle(rs1.getString("issuetitle") == null ? "" : rs1.getString("issuetitle"));
				        	sDTO.setIssueSefTitle(rs1.getString("issuesefurl") == null ? "" : rs1.getString("issuesefurl"));
						}
			        }
				} catch (Exception e) {
					System.out.println("StoryHelper articleDetail Issue Detail Exception is :" + e.toString());
				}
		        sDTO.setPrimaryCategoryLength(primaryCategoryLength); 
		        storyData.add(sDTO);
			}


		} catch (Exception e) {
			System.out.println("StoryHelper articleDetail Exception is :" + e);
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
		return storyData;
	}

	public static ArrayList storyDetailToRedirect(int storyId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoryDTO aDTO = null;	
		ArrayList storyList = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT c.id, c.sef_url FROM jos_content c WHERE c.id = ?";
	    	//System.out.println("Story Detail storyDetailToRedirect->"+sql);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, storyId);
	    	rs = pstmt.executeQuery();
			while (rs.next ()){
		        StoryDTO artDTO = new StoryDTO();
		        artDTO.setId(rs.getInt("id"));
		        artDTO.setSefTitle(rs.getString("sef_url"));
		        storyList.add(artDTO);
			}
		} catch (Exception e) {
			System.out.println("StoryHelper storyDetailToRedirect Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return storyList;
	}
	
	public static String mosimageToimages(String fullbody, String images) throws Exception {
		
		String full1 = "";
		int pipepos = 0;
		int error = 0;
		int p = 0;
		int tokenCount = 0;
		Connection conn = null;
		Dbconnection connect = null;
		try {
			if ((images.trim().length()) > 0) {
				connect = Dbconnection.getInstance();
				conn = connect.getConnection();
				String[] bodyimages = (images.trim()).split("\n");
				tokenCount = bodyimages.length;
				
				for (int k = 0; k < tokenCount; k++) {
					bodyimages[k] = bodyimages[k].substring(0, bodyimages[k]
							.length());
					String[] image1 = new String[] { "", "", "", "", "", "", "", "" };
	
					if (bodyimages[k].indexOf("|") > 0) {
						bodyimages[k] = bodyimages[k].substring(0, bodyimages[k]
								.length());
						bodyimages[k] = bodyimages[k].replace("|", "#-#");
						image1 = bodyimages[k].split("#-#");
					} else {
						image1[0] = bodyimages[k];
					}
					
					String imageDiv = "";
					String captionDiv = "";
					String completeImage = "";
					String imagewidth = "";
					String imageHeight = "";
					String largeImage = "";
					String largeImageWidth = "";
					String largeImageHeight = "";
					String imageAlignment = "";
					String popupScrollbars="no";
					String popupResize="no";
					try {
						for (int k1 = 0; k1 < 1; k1++) {
							completeImage = "";
							if (k1 == 0) {
								imagewidth = "";
								imageHeight = "";
								PreparedStatement pstmt = null;
								ResultSet rs = null;
								try {
									//String sql = "SELECT width_thumb_name,height_thumb_name,mainimg_name,width_mainimg_name,height_mainimg_name from jos_mediaimages where thumb_name like'%"
									//	+ image1[0] + "%'";
									String sql = "SELECT width_thumb_name,height_thumb_name,mainimg_name,width_mainimg_name,height_mainimg_name from jos_mediaimages where thumb_name like'%"
											+ image1[0] + "'";
									pstmt = conn.prepareStatement(sql);
									rs = pstmt.executeQuery();
									
									if (rs.next()) {
										imagewidth = rs
												.getString("width_thumb_name");
										imageHeight = rs
												.getString("height_thumb_name");
										largeImage = rs.getString("mainimg_name");
										largeImageWidth = rs
												.getString("width_mainimg_name");
										largeImageHeight = rs
												.getString("height_mainimg_name");
									}
								} catch (Exception e) {
									//System.out.println("StoryHelper - mosimageToImage - Enlarge image exception -> " + e.toString());
								} finally {
									if(pstmt!=null)
										pstmt.close();
									if(rs!=null)
										rs.close();
								}
							}
							
							if (image1[3] != null && (image1[3].trim()).length() > 0)
								image1[3] = "0";
	
							if (image1[0] != null && (image1[0].trim()).length() > 0) {
								if(image1[1].equals("right")) {
									imageAlignment = "margin:2px 0px 2px 15px; ";
								} else {
									imageAlignment = "margin:2px 15px 2px 0px; ";
								}
									imageDiv = "<div style='border:0px solid #d7d7d7; " + imageAlignment + " width:"
										+ imagewidth
										+ "px; float:"
										+ image1[1]
										+ "'><div style='width:"
										+ imagewidth
										+ "px'><img src='" + Constants.STORY_IMG_PATH + image1[0]
										+ "' align='" + image1[1]
										+ "' alt='" + image1[2]
										+ "' title='" + image1[2]
										+ "' border='" + image1[3]
										+ "' valign='top' hspace='0' vspace='0' style='border:1px solid #d7d7d7; margin:2px 2px'></div>";
									
								if (image1[4] != null && (image1[4].trim()).length() > 0) {
									captionDiv = "<div class='caption' style='width:"
											+ imagewidth + "px; text-align:"
											+ image1[6] + "' valign='" + image1[5]
											+ "'>" + image1[4] + "</div>";
								}
								if(image1[5].equals("top")){
									completeImage = captionDiv + imageDiv; 
								}else {
									completeImage = imageDiv + captionDiv  ;
								}
								
								if (!largeImage.equals("")) {
									if(Integer.parseInt(largeImageWidth) > 800 || Integer.parseInt(largeImageHeight) > 700) {	         	
										popupScrollbars="yes";
										popupResize="yes";
										largeImageWidth = ""+(Integer.parseInt(largeImageWidth)+21);
										largeImageHeight = ""+(Integer.parseInt(largeImageHeight)+21);
									}
									completeImage = completeImage
											+ "<div class='clicktoenlarge' style='width:"
											+ imagewidth 
											+ "px; text-align:"
											+ image1[6]
											+ "' valign='"
											+ image1[5]
											+ "'><a href='#' onClick=\"javascript:window.open('"+Constants.SITE_URL+"story_image.jsp?img="
											+ largeImage 
											+ "&caption="
											+ image1[4]
											+ "','window','status=no,resize="+popupResize+",toolbar=no,scrollbars="+popupScrollbars+",width="
											+ largeImageWidth
											+ ",height=" + largeImageHeight
											+ "'); return false;\">Click here to Enlarge</a></div>";
								}
								completeImage = completeImage + "</DIV>";
							}
						}
						Pattern pa = Pattern.compile("\\{mosimage\\}");
						Matcher matcher = pa.matcher(fullbody);
						fullbody = matcher.replaceFirst(completeImage);
						full1 = fullbody;
					} catch (Exception ee) {
						full1 = fullbody;
						System.out.println("StoryHelper - mosimageToImage - image exception -> " + ee.toString());
					}
				}
	
			} else {
				full1 = fullbody;
			}
		} catch (Exception ee) {
			full1 = fullbody;
			System.out.println("StoryHelper - mosimageToImage : " + ee.toString());
		} finally {
			if(conn!=null)
				conn.close();
		}
		//System.out.println("full1-->"+full1);
		//full1 = full1.replace("{<table", "<table");
		//full1 = full1.replace("</table>}", "</table>");
		return full1;
	}

	public static ArrayList topstoriesStoryChunk(String primaryCatId, int contentCount, String contentIdToAvoid, int topCatId, int topCount) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LatestContentDTO tsDTO = null;	
		ArrayList dataList = new ArrayList();
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
	    
	    if(pcatids==null) {
			pcatids = new String[1];
    		pcatids[0] = "175";
	    }
	    if(pcatids[0].equals("4"))
	    	pcatids[0] = "175";

    	pcatlength = pcatids.length;

		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select c.id, c.title,c.sef_url, ";
	
			if(pcatlength==1)
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
			else if(pcatlength==2) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(pcatlength==3) 
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(pcatlength==4) 
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			//condition checked for India, Sports, Movies and Must Read
			if(pcatlength==1 && (primaryCatId.trim().equals("114") || primaryCatId.trim().equals("84") || primaryCatId.trim().equals("67") || primaryCatId.trim().equals("175") || primaryCatId.trim().equals("120"))) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}
			//condition checked for India#North
			if(pcatlength==2 && primaryCatId.trim().equals("114#141")) {
				sql = sql + " c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 30 DAY) and ";
			}
			
			sql = sql + "c.state=1 and c.id=jas.article_id "+contentIdsToCheck+" group by c.id order by jas.ordering desc limit "+contentCount;

			//System.out.println("Story Page top stories -> " + sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tsDTO = new LatestContentDTO();
				tsDTO.setId(rs.getInt("id"));				
				tsDTO.setTitle(rs.getString("title"));
				tsDTO.setSefTitle(rs.getString("sef_url"));
				tsDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				tsDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				tsDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl"));		
				tsDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
				tsDTO.setContentType("story");		
				dataList.add(tsDTO);
			}

			sql = "";
			pstmt=null;
			rs=null;

			if(topCatId!=0) {
				sql = "select c.id, c.title,c.sef_url, js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath " +
						"from jos_content c, jos_article_section jas, jos_sections js " +
						"where jas.section_id="+topCatId+" and jas.section_id=js.id and js.published=1 and c.state=1 and " +
						"c.id=jas.article_id and c.created >= DATE_SUB(CURRENT_DATE,INTERVAL 15 DAY) group by c.id order by jas.ordering desc limit "+topCount;
				//System.out.println("Top Stories ->"+sql);	
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tsDTO = new LatestContentDTO();
					tsDTO.setId(rs.getInt("id"));				
					tsDTO.setTitle(rs.getString("title"));
					tsDTO.setSefTitle(rs.getString("sef_url"));
					tsDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
					tsDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
					tsDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl"));		
					tsDTO.setCurrentCategoryPageURL(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
					tsDTO.setContentType("top");		
					dataList.add(tsDTO);
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

	public static ArrayList subLevelCategoryDetail(int countToFetch, int categoryId, String categoryIdToAvoid, String articleTemplate) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		LatestContentDTO aDTO = null;	
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
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();

			sql = "select a.id from ";
			if(articleTemplate.equals("section"))
				sql = sql + "jos_categories a, jos_sections b WHERE b.id="+categoryId+" and b.published=1 and a.section=b.id and a.published=1 ";
			else if(articleTemplate.equals("category")) 
				sql = sql + "jos_subcategories a, jos_categories b WHERE b.id="+categoryId+" and b.published=1 and b.id=a.category and a.published=1 ";
			else if(articleTemplate.equals("subcategory")) 
				sql = sql + "jos_sub_subcategories a, jos_subcategories b WHERE b.id="+categoryId+" and b.published=1 and b.id=a.sub_category and a.published=1 ";
			sql = sql + categoryCheck + " GROUP BY a.id order by a.ordering desc";
			if(countToFetch > 0)
				sql = sql + " limit "+countToFetch;	
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println("subLevelCategoryDetail ---->"+sql);
			
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
				//System.out.println("Sanjay subLevelCategoryDetail ---->"+sql);
				while (rs1.next()) {
					aDTO = new LatestContentDTO();
					aDTO.setCurrentCategoryId(rs1.getInt("id"));		
					aDTO.setCurrentCategoryTitle(rs1.getString("title"));		
					aDTO.setCurrentCategorySefTitle(rs1.getString("sef_url"));		
					aDTO.setCurrentCategoryPageURL(rs1.getString("template_path") == null ? "" : rs1.getString("template_path"));		
					dataList.add(aDTO);
				}
			}
		} catch (Exception e) {
			System.out.println("StoryHelper subLevelCategoryDetail Exception is :" + e);
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


	
	
	public static ArrayList templateDetail(int categoryId, String articleTemplate) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoryDTO aDTO = null;	
		ArrayList dataList = new ArrayList();
		int articleCtr = 0;
		String sql = "";
		
		String categoryTemplate = "section";
		if(articleTemplate.equals("section") || articleTemplate.equals("category") || articleTemplate.equals("subcategory") || articleTemplate.equals("subsubcategory")) {
			categoryTemplate = articleTemplate; 
		}
		//System.out.println("1.  ->"+countToFetch+"--3. ->"+categoryId+"--4. ->"+articleIdToAvoid+"--5. ->"+articleTemplate );

		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			
			sql = "select js.title as sectionname, js.id as sectionid, js.sef_url as sectionsefurl, js.template_path as jspath, ";
				sql = sql + "js.header_image as secheaderimage, js.middlenav_path as secmiddlenav_path, ";

			if(articleTemplate.equals("section"))
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.metatitle, js.metakey, js.metadesc, js.topnav_path, js.rightnav_path, js.bottomnav_path, js.leftnav_path from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+categoryId+" and jas.section_id=js.id and js.published=1 and ";
			else if(articleTemplate.equals("category")) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.metatitle, jc.metakey, jc.metadesc, jc.topnav_path, jc.rightnav_path, jc.bottomnav_path, jc.leftnav_path, jc.template_path as jcpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+categoryId+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(articleTemplate.equals("subcategory")) 
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.metatitle, jsc.metakey, jsc.metadesc,jc.title as catname, jc.id as catid, jc.sef_url as catsefurl, jc.template_path as jcpath, jsc.topnav_path, jsc.rightnav_path, jsc.bottomnav_path, jsc.leftnav_path, jsc.template_path as jscpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+categoryId+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(articleTemplate.equals("subsubcategory")) 
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.metatitle, jssc.metakey, jssc.metadesc,jc.title as catname, jc.id as catid, jc.sef_url as catsefurl,jsc.title as subcatname, jsc.id as subcatid, jsc.sef_url as subcatsefurl, jssc.topnav_path, jssc.rightnav_path, jssc.bottomnav_path, jssc.leftnav_path, jssc.template_path as jsscpath, jsc.template_path as jscpath, jc.template_path as jcpath " +
						"from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+categoryId+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			sql = sql + "c.state=1 and c.id=jas.article_id limit 1";

			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println("templateDetail---->"+sql);
			while (rs.next()) {
				aDTO = new StoryDTO();
				aDTO.setSectionId(rs.getInt("sectionid"));		
				aDTO.setSectionTitle(rs.getString("sectionname"));		
				aDTO.setSectionSefTitle(rs.getString("sectionsefurl"));		
				aDTO.setSectionPageURL(rs.getString("jspath") == null ? "" : rs.getString("jspath"));		
				aDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				aDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				aDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl"));		
				aDTO.setMetaTitle(rs.getString("metatitle"));		
				aDTO.setMetaKeyword(rs.getString("metakey"));		
				aDTO.setMetaDescription(rs.getString("metadesc"));	
				aDTO.setTopNavigationPath(rs.getString("topnav_path") == null ? "" : rs.getString("topnav_path"));		
				aDTO.setRightNavigationPath(rs.getString("rightnav_path") == null ? "" : rs.getString("rightnav_path"));		
				aDTO.setBottomNavigationPath(rs.getString("bottomnav_path") == null ? "" : rs.getString("bottomnav_path"));		
				aDTO.setLeftNavigationPath(rs.getString("leftnav_path") == null ? "" : rs.getString("leftnav_path"));		
				aDTO.setHeaderImage(rs.getString("secheaderimage") == null ? "" : rs.getString("secheaderimage"));						
				aDTO.setMiddleNavigationPath(rs.getString("secmiddlenav_path") == null ? "" : rs.getString("secmiddlenav_path"));		
				if(articleTemplate.equals("subcategory")){
					aDTO.setCategoryId(rs.getInt("catid"));		
					aDTO.setCategoryTitle(rs.getString("catname"));		
					aDTO.setCategorySefTitle(rs.getString("catsefurl"));
					aDTO.setCategoryPageURL(rs.getString("jcpath") == null ? "" : rs.getString("jcpath"));		
				}
				if(articleTemplate.equals("subsubcategory")){
					aDTO.setCategoryId(rs.getInt("catid"));		
					aDTO.setCategoryTitle(rs.getString("catname"));		
					aDTO.setCategorySefTitle(rs.getString("catsefurl"));		
					aDTO.setCategoryPageURL(rs.getString("jcpath") == null ? "" : rs.getString("jcpath"));		
					aDTO.setSubCategoryId(rs.getInt("subcatid"));		
					aDTO.setSubCategoryTitle(rs.getString("subcatname"));		
					aDTO.setSubCategorySefTitle(rs.getString("subcatsefurl"));		
					aDTO.setSubCategoryPageURL(rs.getString("jscpath") == null ? "" : rs.getString("jscpath"));		

				}
				dataList.add(aDTO);
				articleCtr++;
			}
		} catch (Exception e) {
			System.out.println("ArticleHelper latestArticle Exception is :" + e);
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
	public static String[] relatedVideoDetail(int videoId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String[] videoDetailArray = new String[4];
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			//sql = "SELECT VideoGallery_foldername, VideoGallery_filename, external_url FROM jos_videogallerynames where contentid="+videoId;
			sql = "SELECT c.mp4_flag, v.VideoGallery_foldername, v.VideoGallery_filename, v.external_url FROM jos_videogallerynames v, jos_content c where c.id="+videoId+" and c.id=v.contentid";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				videoDetailArray[0] = rs.getString("VideoGallery_foldername")==null?"":rs.getString("VideoGallery_foldername");		
				videoDetailArray[1] = rs.getString("VideoGallery_filename")==null?"":rs.getString("VideoGallery_filename");		
				videoDetailArray[2] = rs.getString("external_url")==null?"":rs.getString("external_url");		
				videoDetailArray[3] = ""+rs.getInt("mp4_flag");		
			}
		} catch (Exception e) {
			System.out.println("StoryHelper relatedVideoDetail Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return videoDetailArray;
	}

	public static String mosimageToimagesContent(String fullbody, String images) throws Exception {
		
		String full1 = "";
		int pipepos = 0;
		int error = 0;
		int p = 0;
		int tokenCount = 0;
		Connection conn = null;
		Dbconnection connect = null;

		try {
			if ((images.trim().length()) > 0) {
				connect = Dbconnection.getInstance();
				conn = connect.getConnection();
				String[] bodyimages = (images.trim()).split("\n");
				tokenCount = bodyimages.length;
				
				for (int k = 0; k < tokenCount; k++) {
					bodyimages[k] = bodyimages[k].substring(0, bodyimages[k].length());
					String[] image1 = new String[] { "", "", "", "", "", "", "", "", "", "", "", "", "" };
	
					if (bodyimages[k].indexOf("|") > 0) {
						bodyimages[k] = bodyimages[k].substring(0, bodyimages[k].length());
						bodyimages[k] = bodyimages[k].replace("|", "#-#");
						image1 = bodyimages[k].split("#-#");
					} else {
						image1[0] = bodyimages[k];
					}
					//System.out.println("Mosimage content length " + k + "- >" + image1.length);
					String imageDiv = "";
					String captionDiv = "";
					String completeImage = "";
					String imagewidth = "";
					String imageHeight = "";
					String largeImage = "";
					String largeImageWidth = "";
					String largeImageHeight = "";
					String imageAlignment = "";
					String popupScrollbars="no";
					String popupResize="no";
					try {
						for (int k1 = 0; k1 < 1; k1++) {
							completeImage = "";
							if (k1 == 0) {
								imagewidth = "";
								imageHeight = "";
								largeImage = image1[8] == null ? "" : image1[8];
								largeImageWidth = image1[9] == null ? "" : image1[9];
								largeImageHeight = image1[10] == null ? "" : image1[10];
								imagewidth = image1[11] == null ? "" : image1[11];
								imageHeight = image1[12] == null ? "" : image1[12];
							}
							
							if (image1[3] != null && (image1[3].trim()).length() > 0)
								image1[3] = "0";
	
							if (image1[0] != null && (image1[0].trim()).length() > 0) {
								if(image1[1].equals("right")) {
									imageAlignment = "margin:2px 0px 2px 15px; ";
								} else {
									imageAlignment = "margin:2px 15px 2px 0px; ";
								}
									imageDiv = "<div style='border:0px solid #d7d7d7; " + imageAlignment + " width:"
										+ imagewidth
										+ "px; float:"
										+ image1[1]
										+ "'><div style='width:"
										+ imagewidth
										+ "px'><img src='" + Constants.STORY_IMG_PATH + image1[0]
										+ "' align='" + image1[1]
										+ "' alt='" + image1[2]
										+ "' title='" + image1[2]
										+ "' border='" + image1[3]
										+ "' valign='top' hspace='0' vspace='0' style='border:1px solid #d7d7d7; margin:2px 2px'></div>";
									
								if (image1[4] != null && (image1[4].trim()).length() > 0) {
									captionDiv = "<div class='caption' style='width:"
											+ imagewidth + "px; text-align:"
											+ image1[6] + "' valign='" + image1[5]
											+ "'>" + image1[4] + "</div>";
								}
								if(image1[5].equals("top")){
									completeImage = captionDiv + imageDiv;
								}else {
									completeImage = imageDiv + captionDiv  ;
								}
								
								if (!largeImage.equals("")) {
									if(Integer.parseInt(largeImageWidth) > 800 || Integer.parseInt(largeImageHeight) > 700) {	         	
										popupScrollbars="yes";
										popupResize="yes";
										largeImageWidth = ""+(Integer.parseInt(largeImageWidth)+21);
										largeImageHeight = ""+(Integer.parseInt(largeImageHeight)+21);
									}
									completeImage = completeImage
											+ "<div class='clicktoenlarge' style='width:"
											+ imagewidth 
											+ "px; text-align:"
											+ image1[6]
											+ "' valign='"
											+ image1[5]
											+ "'><a href='#' onClick=\"javascript:window.open('"+Constants.SITE_URL+"story_image.jsp?img="
											+ largeImage 
											+ "&caption="
											+ image1[4]
											+ "','window','status=no,resize="+popupResize+",toolbar=no,scrollbars="+popupScrollbars+",width="
											+ largeImageWidth
											+ ",height=" + largeImageHeight
											+ "'); return false;\">Click here to Enlarge</a></div>";
								}
								completeImage = completeImage + "</DIV>";
							}
						}
						Pattern pa = Pattern.compile("\\{mosimage\\}");
						Matcher matcher = pa.matcher(fullbody);
						fullbody = matcher.replaceFirst(completeImage);
						full1 = fullbody;
					} catch (Exception ee) {
						full1 = fullbody;
						System.out.println("StoryHelper - mosimageToImage - image exception -> " + ee.toString());
					}
				}
	
			} else {
				full1 = fullbody;
			}
		} catch (Exception ee) {
			full1 = fullbody;
			System.out.println("StoryHelper - mosimageToImageContent : " + ee.toString());
		} finally {
			if(conn!=null)
				conn.close();
		}
		//System.out.println("full1-->"+full1);
		//full1 = full1.replace("{<table", "<table");
		//full1 = full1.replace("</table>}", "</table>");
		return full1;
	}

	public static ArrayList storySectionDetailToRedirect(int sectionId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoryDTO sDTO = null;	
		ArrayList storyList = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT c.id, c.sef_url FROM jos_sections c WHERE c.id = ?";
	    	//System.out.println("Story Detail storyDetailToRedirect->"+sql);
	    	pstmt = connection.prepareStatement(sql);
	        pstmt.setInt(1, sectionId);
	    	rs = pstmt.executeQuery();
			while (rs.next ()){
				sDTO = new StoryDTO();
				sDTO.setId(rs.getInt("id"));
				sDTO.setSefTitle(rs.getString("sef_url"));
		        storyList.add(sDTO);
			}
		} catch (Exception e) {
			System.out.println("StoryHelper storySectionDetailToRedirect Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return storyList;
	}
	
	public static String mosimageToimagesOpt(String fullbody, String images) throws Exception {
		String full1 = "";
		int pipepos = 0;
		int error = 0;
		int p = 0;
		int tokenCount = 0;
		Connection conn = null;
		Dbconnection connect = null;

		try {
			if ((images.trim().length()) > 0) {
				connect = Dbconnection.getInstance();
				conn = connect.getConnection();
				String[] bodyimages = (images.trim()).split("\n");
				tokenCount = bodyimages.length;
				
				for (int k = 0; k < tokenCount; k++) {
					bodyimages[k] = bodyimages[k].substring(0, bodyimages[k]
							.length());
					String[] image1 = new String[] { "", "", "", "", "", "", "", "" };
	
					if (bodyimages[k].indexOf("|") > 0) {
						bodyimages[k] = bodyimages[k].substring(0, bodyimages[k]
								.length());
						bodyimages[k] = bodyimages[k].replace("|", "#-#");
						image1 = bodyimages[k].split("#-#");
					} else {
						image1[0] = bodyimages[k];
					}
					
					String imageDiv = "";
					String captionDiv = "";
					String completeImage = "";
					String imagewidth = "";
					int imagewidthInt = 0;
					String divImagewidth = "";
					String imageHeight = "";
					String largeImage = "";
					String largeImageWidth = "";
					String largeImageHeight = "";
					String imageAlignment = "";
					String popupScrollbars="no";
					String popupResize="no";
					try {
						for (int k1 = 0; k1 < 1; k1++) {
							completeImage = "";
							if (k1 == 0) {
								imagewidth = "";
								imageHeight = "";
								PreparedStatement pstmt = null;
								ResultSet rs = null;
								try {
									//Original - String sql = "SELECT width_thumb_name,height_thumb_name,mainimg_name,width_mainimg_name,height_mainimg_name from jos_mediaimages where thumb_name like'%" + image1[0] + "'";
									String sql = "SELECT width_thumb_name,height_thumb_name,mainimg_name,width_mainimg_name,height_mainimg_name from jos_mediaimages where content_image_name = '" + image1[0] + "'";
									pstmt = conn.prepareStatement(sql);
									rs = pstmt.executeQuery();
									//System.out.println("Mos Image Opt function -> " + sql);		
									if (rs.next()) {
										imagewidth = rs.getString("width_thumb_name");										
										imageHeight = rs.getString("height_thumb_name");
										largeImage = rs.getString("mainimg_name");
										largeImageWidth = rs.getString("width_mainimg_name");
										largeImageHeight = rs.getString("height_mainimg_name");
									}
								} catch (Exception e) {
									System.out.println("StoryHelper - mosimageToImageOpt - Enlarge image exception -> " + e.toString());
								} finally {
									if(pstmt!=null)
										pstmt.close();
									if(rs!=null)
										rs.close();
								}
							}
							
							if(imagewidth!=null&&imagewidth.length()>0){
								
								imagewidthInt=Integer.parseInt(imagewidth);
							}
							
							if(imagewidthInt>450){
								imagewidth="100%";
								divImagewidth=imagewidth;
							}else{
								divImagewidth=imagewidth+"px";
								imagewidth=imagewidth+"px;margin-right:15px";
							}
							if (image1[3] != null && (image1[3].trim()).length() > 0)
								image1[3] = "0";
	
							if (image1[0] != null && (image1[0].trim()).length() > 0) {
								if(image1[1].equals("right")) {
									imageAlignment = "margin:0 ; ";
								} else {
									imageAlignment = "margin:0 ; ";
								}
						imageDiv = "<div class=\"storycentimg\" style='border:0px solid #d7d7d7; "+imageAlignment+" width:"+imagewidth+"; float:"+ image1[1]+"'><div style='width:"+ divImagewidth+ "'><img src='"+Constants.STORY_IMG_PATH + image1[0]
										+ "' align='"+image1[1]+"' alt='"+image1[2]+"' title='"+image1[2]+"'    style='border:1px solid #d7d7d7; margin:2px 0px'></div>";
									
								if (image1[4] != null && (image1[4].trim()).length() > 0) {
									
									
									captionDiv = "<h2 class='mos-caption' style='width:100%; text-align:"
											+ image1[6] + "'>" + image1[4] + "</h2>";
									
								}
								if(image1[5].equals("top")){
									completeImage = captionDiv + imageDiv;
								}else {
									completeImage = imageDiv + captionDiv  ;
								}
								
								if (!largeImage.equals("")) {
									if(Integer.parseInt(largeImageWidth) > 800 || Integer.parseInt(largeImageHeight) > 700) {	         	
										popupScrollbars="yes";
										popupResize="yes";
										largeImageWidth = ""+(Integer.parseInt(largeImageWidth)+21);
										largeImageHeight = ""+(Integer.parseInt(largeImageHeight)+21);
									}
									completeImage = completeImage
											+ "<div class='clicktoenlarge' style='width:"
											+ imagewidth 
											+ " text-align:"
											+ image1[6]
											+ "' valign='"
											+ image1[5]
											+ "'><a href='#' onClick=\"javascript:window.open('http://indiatoday.intoday.in/story_image.jsp?img="
											+ largeImage 
											+ "&caption="
											+ image1[4]
											+ "','window','status=no,resize="+popupResize+",toolbar=no,scrollbars="+popupScrollbars+",width="
											+ largeImageWidth
											+ ",height=" + largeImageHeight
											+ "'); return false;\">Click here to Enlarge</a></div>";
								}
								completeImage = completeImage + "</div>";
							}
						}
						Pattern pa = Pattern.compile("\\{mosimage\\}");
						Matcher matcher = pa.matcher(fullbody);
						fullbody = matcher.replaceFirst(completeImage);
						full1 = fullbody;
						//System.out.println("@@@-->"+completeImage);
					} catch (Exception ee) {
						full1 = fullbody;
						System.out.println("StoryHelper - mosimageToImage - image exception -> " + ee.toString());
					}
				}
			} else {
				full1 = fullbody;
			}
		} catch (Exception ee) {
			full1 = fullbody;
			System.out.println("StoryHelper - mosimageToImage : " + ee.toString());
		} finally {
			if(conn!=null)
				conn.close();
		}
		//System.out.println("full1-->"+full1);
		//full1 = full1.replace("{<table", "<table");
		//full1 = full1.replace("</table>}", "</table>");
		return full1;
	}	
	String relatedChunk(String fullbodytext, String relatedStroyId)
	{
		StringBuffer relatedSB = null;
		String relatedStr = "";
		ArrayList resultStoryRelated=null;
		String relatedtype = "";
		
		try {
		resultStoryRelated = (ArrayList) CommonFunctions.relatedContent(relatedStroyId, "text");
		if (resultStoryRelated != null && resultStoryRelated.size() > 0) {
			relatedSB = new StringBuffer();
			for (int ctr = 0; ctr < resultStoryRelated.size(); ctr++) {
				RelatedContentDTO rcDTO = (RelatedContentDTO) resultStoryRelated.get(ctr); 
				relatedtype = rcDTO.getRelatedType();
				if(ctr==0){
					relatedSB.append("<div class='leftspac'><div class='strbox'><h2>The Relateds</h2>");	
				}
				relatedSB.append("<div class='strinnerbox lft'>");	
				if(relatedtype.trim().equals("text")) {	
					if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {	 
						relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.storyURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\"><img align='left' width='170' height='120'src=\""+Constants.STORY_IMG_PATH+rcDTO.getRelatedThumbImage()+"\"></a>"); 
						relatedSB.append("<span class='st_white'><span class='small-story-icon sp_bg'></span></span>");       
					} 
					relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.storyURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\">"+rcDTO.getRelatedTitle()+"</a>");
				} else if(relatedtype.trim().equals("photo"))	{
						if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {	 
							relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.galleryURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\"><img align='left' width='170' height='120'  src=\""+Constants.GALLERY_IMG_PATH+rcDTO.getRelatedThumbImage()+"\"></a>");
						}
					relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.galleryURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\">"+rcDTO.getRelatedTitle()+"</a>");
					if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {
						relatedSB.append("<span class='st_white'><span class='small-image-icon sp_bg'></span></span>");
					}
				} else if(relatedtype.trim().equals("video"))	{
					if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {	 
						relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.videoURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\"><img align='left' width='170' height='120' src=\""+Constants.STORY_IMG_PATH+rcDTO.getRelatedThumbImage()+"\"></a>");
					} 
						relatedSB.append("<a href=\""+Constants.SITE_BASEPATH+CommonFunctions.videoURL(rcDTO.getRelatedContentSefTitle(), 1, rcDTO.getRelatedContentId())+"\">"+rcDTO.getRelatedTitle()+"</a>");
						if(rcDTO.getRelatedThumbImage()!=null && !rcDTO.getRelatedThumbImage().equals("")) {	 
							relatedSB.append("<span class='st_white'><span class='small-play-icon sp_bg'></span><span>");
						}
					}     
					relatedSB.append("</div>");	
				}
			relatedSB.append("</div></div>");
		}
		} catch (Exception ex) {
		     System.out.println("IT Story.jsp Related Exception->"+ex.getMessage());
			//System.out.println("IT Story Related Exception->"+ex.toString());
		}
		if(relatedSB.toString().trim().length() > 0) {
			relatedStr = relatedSB.toString().trim();
		}

		String htmlData = "";
		String completeHtmlData = "";
		String textFormat = "\\{relateds\\}";
		try {
				completeHtmlData = relatedStr;
				Pattern pa = Pattern.compile(textFormat);
				Matcher matcher = pa.matcher(fullbodytext); 
				fullbodytext = matcher.replaceFirst(completeHtmlData);
				htmlData=fullbodytext;
		} catch(Exception ee){
			//htmlData = "<font color=red><br>HTML Chunk eeerrr="+ee.toString()+"<>"+"</font>";
			htmlData = fullbodytext; 
		}
		return htmlData;  
	}
	String htmlChunks(String fullbodytext,String htmlText, String htmlFormat)
	{
		String htmlData = "";
		String completeHtmlData = "";
		htmlText=forRegex(htmlText); 
		String[] htmlDataArray = (htmlText.trim()).split("\\~");
		int noOfQuotes = htmlDataArray.length;
		String textFormat = htmlFormat.replace("{", "\\{").replace("}", "\\}");
		try {
			for(int k=0;k<noOfQuotes ;k++)	
			{
				completeHtmlData = htmlDataArray[k];
				Pattern pa = Pattern.compile(textFormat);
				Matcher matcher = pa.matcher(fullbodytext); 
				fullbodytext = matcher.replaceFirst(completeHtmlData);
				htmlData=fullbodytext;
			}
		} catch(Exception ee){
			//htmlData = "<font color=red><br>HTML Chunk eeerrr="+ee.toString()+"<>"+"</font>";
			htmlData = fullbodytext; 
		}
		return htmlData;  
	}
	public static String forRegex(String aRegexFragment){ 
    final StringBuilder result = new StringBuilder();
    final StringCharacterIterator iterator =   new StringCharacterIterator(aRegexFragment);
    char character =  iterator.current();
    while (character != CharacterIterator.DONE ){
      /*
       All literals need to have backslashes doubled.
      */
      if (character == '.') {
        result.append("\\.");
      } else if (character == '\\') {
        result.append("\\\\");
      } else if (character == '?') {
        result.append("\\?");
      } else if (character == '*') {
        result.append("\\*");
      } else if (character == '+') {
        result.append("\\+");
      } else if (character == '&') {
        result.append("\\&");
      } else if (character == ':') {
        result.append("\\:");
      } else if (character == '{') {
        result.append("\\{");
      } else if (character == '}') {
        result.append("\\}");
      } else if (character == '[') {
        result.append("\\[");
      } else if (character == ']') {
        result.append("\\]");
      } else if (character == '(') {
        result.append("\\(");
      } else if (character == ')') {
        result.append("\\)");
      } else if (character == '^') {
        result.append("\\^");
      } else if (character == '$') {
        result.append("\\$");
      } else {
        //the char is not a special one
        //add it to the result as is
        result.append(character);
      }
      character = iterator.next();
    }
    return result.toString();
  }
  public String byLineLinkCreater(String byLine){
		String doubleByLine[] = null;
		String doubleByLine1[] = null;
		StringBuffer sb= null;
		sb= new StringBuffer();
		String byLineWithLink="";
		String byLineWithInLink="";
		if (!byLine.trim().equals("") && byLine != null) {
			if (byLine.contains("/")) {
				doubleByLine = byLine.split("\\/");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					//System.out.println("with/@@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("/");
						sb.append(" /");
					}
				}
			} else if (byLine.contains(",")) {
				doubleByLine = byLine.split(",");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					// System.out.println("with , @@@@@@@@"+doubleByLine[cid]);
					if (!doubleByLine[cid].contains(" and "))
						//System.out.print(doubleByLine[cid]);
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.print(",");
						sb.append(" ,");
					}
					if (doubleByLine[cid].contains(" and ")) {
						doubleByLine1 = doubleByLine[cid].split(" and ");
						for (int cid1 = 0; cid1 < doubleByLine1.length; cid1++) {
							//System.out.print(doubleByLine1[cid1]);
							sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine1[cid1].replace(" ","-")+"/1.html\">"+doubleByLine1[cid1]+"</a>");
							if (cid1 != doubleByLine1.length - 1) {
								//System.out.print(" and ");
								sb.append(" and ");
							}
						}
					}
				}
			} else if (byLine.contains(" and ")) {
				doubleByLine = byLine.split("and");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					if (doubleByLine[cid].contains(" in ")) {
						doubleByLine[cid] = doubleByLine[cid].substring(0,
								doubleByLine[cid].indexOf(" in "));
					}
					//System.out.println("with and @@@@@@@@" + doubleByLine[cid]);
				sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("and");
						sb.append(" and");
					}
				}
			} else if (byLine.contains(" with ")) {
				doubleByLine = byLine.split("with");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					if (doubleByLine[cid].contains(" in ")) {
					
					byLineWithInLink=doubleByLine[cid];
						doubleByLine[cid] = doubleByLine[cid].substring(0,doubleByLine[cid].indexOf(" in "));
						
						//System.out.println("with-->"+doubleByLine[cid]);
						//System.out.println("with-->"+byLineWithInLink);
						byLineWithInLink=byLineWithInLink.substring(doubleByLine[cid].length()+1,byLineWithInLink.length());
						
						
					}
					//System.out.println("with with @@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a> "+byLineWithInLink+"");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("with");
						sb.append(" with");
					}
				}

			} else if (byLine.contains(" & ")) {
				doubleByLine = byLine.split("&");
				for (int cid = 0; cid < doubleByLine.length; cid++) {
					//System.out.println("with & @@@@@@@@" + doubleByLine[cid]);
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+doubleByLine[cid].replace(" ","-")+"/1.html\">"+doubleByLine[cid]+"</a>");
					if (cid != doubleByLine.length - 1) {
						//System.out.println("&");
						sb.append(" &");
					}
				}
			}
			else {
				if (byLine.contains(" in ")) {
					byLine = byLine.substring(0, byLine.indexOf(" in "));
					sb.append("<a href=\""+Constants.SITE_URL+"author/"+byLine.replace(" ","-")+"/1.html\">"+byLine+"</a>");
				}else{
				//System.out.println("only @@@@@@@@" + byLine);
				sb.append("<a href=\""+Constants.SITE_URL+"author/"+byLine.replace(" ","-")+"/1.html\">"+byLine+"</a>");
			}
			}

		}
		byLineWithLink=sb.toString();
	return byLineWithLink;
	}
	public String getProfileLink(String description, String metatags) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String[][] profileArray = null;
		String[][] metatagArray = null;
		String[] tempMetaTagsA = null;
		String sefTitle = "";
		int profileCount = 0;
		int ctr = 0;
		String thumbImage = "";
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			sql = "select id, headline, sef_url,kicker_image,thumb_image from jos_snippets where sectionid=171 and published=1 group by headline";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.last();
			profileCount = rs.getRow();
			profileArray = new String[profileCount][2];
			rs.beforeFirst();
			while (rs.next()) {
				profileArray[ctr][0] = rs.getString("headline") == null ? "" : rs.getString("headline");		
				sefTitle = rs.getString("sef_url") == null ? "" : rs.getString("sef_url");
				thumbImage = rs.getString("thumb_image") == null ? "" : rs.getString("thumb_image");
				if(sefTitle.indexOf(".html") >= 0) 
					sefTitle = sefTitle.replaceAll(".html", "");
					if(thumbImage!=null && !thumbImage.equals("")){
				profileArray[ctr][1] = "<a href=\""+Constants.SITE_URL+"people/"+sefTitle+"/"+rs.getInt("id")+".html\"><img class=\"pf_img\" src=\"http://media2.intoday.in/indiatoday/images/stories/"+thumbImage+"\" width=\"30\" height=\"30\" alt=\"\"/>"+profileArray[ctr][0]+"</a>";
				}else{
				profileArray[ctr][1] = "<a href=\""+Constants.SITE_URL+"people/"+sefTitle+"/"+rs.getInt("id")+".html\"><img class=\"pf_img\" src=\"\" width=\"30\" height=\"30\" alt=\"\"/>"+profileArray[ctr][0]+"</a>";
				}
				ctr++;
			}
			for(int i1=0; i1 < profileArray.length; i1++) {
				if(description.contains(profileArray[i1][0])) {
					description = description.replaceFirst(profileArray[i1][0], profileArray[i1][1]);
				}
			}
			


		} catch (Exception e) {
			//System.out.println("StoryHelper getProfileLink Exception is :" + e);
			
			System.out.println("StoryHelper getProfileLink Exception is :" + e.getMessage());
			
			
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return description;
	}
	public static ArrayList getBylineData(int storyId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		byLineDTO aDTO = null;	
		ArrayList storyList = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT jap.id,jap.author_name,jap.author_email,jap.profile_image,jap.designation,jap.full_description,jap.sef_url,jap.twitter_id,jap.facebook_id,jap.profile_type FROM `jos_content_author_relation` jar,`jos_author_profile` jap WHERE jar.content_id=? AND jar.author_id=jap.id AND jap.published='1' AND jar.published='1' AND jar.content_type='0' ORDER BY `ordering` DESC";	    	
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, storyId);
	    	rs = pstmt.executeQuery();
			while (rs.next ()){
				aDTO = new byLineDTO();
				aDTO.setId(rs.getInt("id"));
				aDTO.setAuthor_name(rs.getString("author_name"));
				aDTO.setAuthor_email(rs.getString("author_email"));
				aDTO.setProfile_image(rs.getString("profile_image"));
				aDTO.setDesignation(rs.getString("designation"));
				aDTO.setFull_description(rs.getString("full_description"));
				aDTO.setSef_url(rs.getString("sef_url"));
				aDTO.setTwitter_id(rs.getString("twitter_id"));
				aDTO.setFacebook_id(rs.getString("facebook_id"));
				aDTO.setProfile_type(rs.getString("profile_type"));
		        storyList.add(aDTO);
			}
		} catch (Exception e) {
			System.out.println("StoryHelper storybyline Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return storyList;
	}
} 
