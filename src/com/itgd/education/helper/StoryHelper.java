package com.itgd.education.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.itgd.education.utils.DbConnection;
import com.itgd.education.dto.ContentListDTO;
import com.itgd.education.dto.StoryDTO;
import com.itgd.education.utils.Constants;

public class StoryHelper {
	public static ArrayList storyDetail(int storyId, int publishedStatus) throws Exception
	{
    	//System.out.println("Story "+storyId + "----ststus--"+publishedStatus);
		StoryDTO sDTO=null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ArrayList storyData = null;
		String storyStatus = "";
		if(publishedStatus==1)
			storyStatus = " and c.state=1 ";
	    int primaryCategoryLength = 0;
	    String primaryCategory = "0";
	    String primaryCategoryId[] = null;
		try {
			DbConnection connect = null;
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");
	    	String sql = "SELECT c.id, c.issueid, c.primary_category, c.title, c.title_alias, title_magazine, c.sef_url, c.byline, c.city, c.courtesy, " +
	    			"c.images as imagepath, c.introtext, c.fulltext, c.strap_headline, c.metadesc, c.metakey, c.metatags, c.story_syndication, " +
	    			"date_format( c.created,'%M %e, %Y' ) AS crt, date_format( c.modified,'%M %e, %Y' ) AS mdate, date_format( c.created,'%Y-%m-%e' ) AS snd, c.article_icon, c.article_icon_img, " +
	    			"date_format( c.created,'%H:%i') AS ctime, date_format( c.modified,'%H:%i') AS mtime,  c.quotes, c.blurbs,	c.tables, c.article_icon, " +
	    			"c.article_icon_img, c.kicker_image, c.large_kicker_image, c.large_kicker_image_width, c.kicker_image_caption, c.large_kicker_image_alt_text, indiatoday_expert, google_standout " +
	    			"FROM jos_content c, jos_article_section a WHERE c.id = ? and a.article_id=c.id "+storyStatus+" group by c.id";
	    	//System.out.println("Story Detail ->"+sql);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, storyId);
            //pstmt.setInt(2, storyStatus);
            rs = pstmt.executeQuery ();
	    	//System.out.println("Story Detail 111->");
            storyData = new ArrayList();
			while (rs.next ()){
		        sDTO = new StoryDTO();
		        sDTO.setId(rs.getInt("id"));
		    	//System.out.println("Story Detail ->"+rs.getInt("id"));
		        sDTO.setLongDescription(rs.getString("fulltext"));
		        sDTO.setTitleAlias(rs.getString("title_alias"));		       
		        sDTO.setTitleMagazine(rs.getString("title_magazine") == null ? "" : rs.getString("title_magazine"));		       
		        sDTO.setTitle(rs.getString("title"));
		        sDTO.setByline(rs.getString("byline")); 
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
				    	//System.out.println("Story Primary Detail ->"+sql1);
	
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
			sDTO=null;
		}
		return storyData;
	}

	public static ArrayList storyDetailToRedirect(int storyId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StoryDTO aDTO = null;	
		 StoryDTO artDTO =null;
		ArrayList storyList = new ArrayList();
		try {
			DbConnection connect = null;
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");
	    	String sql = "SELECT c.id, c.sef_url FROM jos_content c WHERE c.id = ?";
	    	//System.out.println("Story Detail storyDetailToRedirect->"+sql);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, storyId);
	    	rs = pstmt.executeQuery();
			while (rs.next ()){
		        artDTO = new StoryDTO();
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
		DbConnection connect = null;

		try {
			if ((images.trim().length()) > 0) {
				connect = DbConnection.getInstance("indiatoday");
				conn = connect.getConnection("indiatoday");
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
									System.out.println("StoryHelper - mosimageToImage - Enlarge image exception -> " + e.toString());
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
										+ "px'><img src='" + Constants.KICKER_IMG_PATH + image1[0]
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
		ContentListDTO tsDTO = null;	
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
    	pcatlength = pcatids.length;

		try {
			DbConnection connect = null;
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");
			String sql = "select c.id, c.title,c.sef_url, ";
	
			if(pcatlength==1)
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.template_path as curcatpath from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+pcatids[0]+" and jas.section_id=js.id and js.published=1 and ";
			else if(pcatlength==2) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.template_path as curcatpath from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+pcatids[1]+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(pcatlength==3) 
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.template_path as curcatpath from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+pcatids[2]+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(pcatlength==4) 
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.template_path as curcatpath from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+pcatids[3]+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
	
			sql = sql + "c.state=1 and c.id=jas.article_id "+contentIdsToCheck+" group by c.id order by jas.ordering desc limit "+contentCount;

			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				tsDTO = new ContentListDTO();
				tsDTO.setId(rs.getInt("id"));				
				tsDTO.setTitle(rs.getString("title"));
				tsDTO.setSefTitle(rs.getString("sef_url"));
				tsDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
				tsDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
				tsDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl"));		
				tsDTO.setCurrentCategoryPath(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
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
						"c.id=jas.article_id group by c.id order by jas.ordering desc limit "+topCount;
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					tsDTO = new ContentListDTO();
					tsDTO.setId(rs.getInt("id"));				
					tsDTO.setTitle(rs.getString("title"));
					tsDTO.setSefTitle(rs.getString("sef_url"));
					tsDTO.setCurrentCategoryId(rs.getInt("curcatid"));		
					tsDTO.setCurrentCategoryTitle(rs.getString("curcatname"));		
					tsDTO.setCurrentCategorySefTitle(rs.getString("curcatsefurl"));		
					tsDTO.setCurrentCategoryPath(rs.getString("curcatpath") == null ? "" : rs.getString("curcatpath"));		
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
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");

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
					aDTO = new ContentListDTO();
					aDTO.setCurrentCategoryId(rs1.getInt("id"));		
					aDTO.setCurrentCategoryTitle(rs1.getString("title"));		
					aDTO.setCurrentCategorySefTitle(rs1.getString("sef_url"));		
					aDTO.setCurrentCategoryPath(rs1.getString("template_path") == null ? "" : rs1.getString("template_path"));		
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
			DbConnection connect = null;
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");
			
			sql = "select js.title as sectionname, js.id as sectionid, js.sef_url as sectionsefurl, js.template_path as jspath, ";
				sql = sql + "js.header_image as secheaderimage, js.middlenav_path as secmiddlenav_path, ";

			if(articleTemplate.equals("section"))
				sql = sql + "js.title as curcatname, js.id as curcatid, js.sef_url as curcatsefurl, js.metatitle, js.metakey, js.metadesc, js.topnav_path, js.rightnav_path, js.bottomnav_path, js.leftnav_path, js.description from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+categoryId+" and jas.section_id=js.id and js.published=1 and ";
			else if(articleTemplate.equals("category")) 
				sql = sql + "jc.title as curcatname, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.metatitle, jc.metakey, jc.metadesc, jc.topnav_path, jc.rightnav_path, jc.bottomnav_path, jc.leftnav_path, jc.template_path as jcpath, jc.description from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+categoryId+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
			else if(articleTemplate.equals("subcategory")) 
				sql = sql + "jsc.title as curcatname, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.metatitle, jsc.metakey, jsc.metadesc,jc.title as catname, jc.id as catid, jc.sef_url as catsefurl, jc.template_path as jcpath, jsc.topnav_path, jsc.rightnav_path, jsc.bottomnav_path, jsc.leftnav_path, jsc.template_path as jscpath, jsc.description from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+categoryId+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and ";
			else if(articleTemplate.equals("subsubcategory")) 
				sql = sql + "jssc.title as curcatname, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.metatitle, jssc.metakey, jssc.metadesc,jc.title as catname, jc.id as catid, jc.sef_url as catsefurl,jsc.title as subcatname, jsc.id as subcatid, jsc.sef_url as subcatsefurl, jssc.topnav_path, jssc.rightnav_path, jssc.bottomnav_path, jssc.leftnav_path, jssc.template_path as jsscpath, jsc.template_path as jscpath, jc.template_path as jcpath, jssc.description " +
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
				aDTO.setShortDescription(rs.getString("description") == null ? "" : rs.getString("description"));	
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
		String[] videoDetailArray = new String[3];
		try {
			DbConnection connect = null;
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");
			sql = "SELECT VideoGallery_foldername, VideoGallery_filename, external_url FROM jos_videogallerynames where contentid="+videoId;
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				videoDetailArray[0] = rs.getString("VideoGallery_foldername")==null?"":rs.getString("VideoGallery_foldername");		
				videoDetailArray[1] = rs.getString("VideoGallery_filename")==null?"":rs.getString("VideoGallery_filename");		
				videoDetailArray[2] = rs.getString("external_url")==null?"":rs.getString("external_url");		
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

	
}
