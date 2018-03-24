package com.itgd.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.ContentChunkDTO;
import com.itgd.exception.IndiaTodayHelperException;

public class ContentChunkHelper {
	
	public static void main(String[] args) {
		ContentChunkHelper cth = new ContentChunkHelper();
		try {
			//cth.latestGallery(0, 7, "0");
			//cth.latestVideo(0, 9, "");
			//latestStory("section", 114, 10, "0");
			String galleryIdToAvoid="0";
			//cth.latestGallery(131, 18, galleryIdToAvoid);
			
			cth.latestVideo(603, 19, ""); 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList latestStory(String categoryTemplate, int categoryId, int contentCount, String contentIdToAvoid) throws Exception
	{
		//System.out.println("ContentChunkHelper latestStory() id->"+categoryId+" - count->"+contentCount+" - idToAvoid->"+contentIdToAvoid);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentChunkDTO ccDTO = null;	
		ArrayList contentAL =null;
		int cutoffDays = 0;
		String cutoffCheck="" ;
		String contentCheck="" ;
		String sql="";
		
		try {
			if((categoryTemplate!=null) && (categoryTemplate.equals("section") || categoryTemplate.equals("category") || categoryTemplate.equals("subcategory") || categoryTemplate.equals("subsubcategory")) && (categoryId > 0)) {
	            Dbconnection connect = null;
	            connect = Dbconnection.getInstance();
	            connection = connect.getConnection();
	           //System.out.println("categoryId-->"+categoryId+"------pcatlength-->"+pcatlength+"--contentIdToAvoid--"+contentIdToAvoid);
            	if(categoryTemplate.equals("section")) {
					sql = "SELECT content_fetch_time FROM jos_sections where id="+categoryId;
				} else if(categoryTemplate.equals("category")) {
					sql = "SELECT content_fetch_time FROM jos_categories where id="+categoryId;
				} else if(categoryTemplate.equals("subcategory")) {
					sql = "SELECT content_fetch_time FROM jos_subcategories where id="+categoryId;
				} else if(categoryTemplate.equals("subsubcategory")) {
					sql = "SELECT content_fetch_time FROM jos_sub_subcategories where id="+categoryId;
				}            
				//System.out.println("IT WEB ContentChunkHelper.latestStory() ContentInterval Query->" + sql);
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cutoffDays = rs.getInt("content_fetch_time");
				}
				sql="";
				pstmt=null;
				rs=null;

				if(contentIdToAvoid.trim().length() > 0 && !contentIdToAvoid.trim().equals("0")) {
	    			if(contentIdToAvoid.indexOf(",") > 0)
	    				contentCheck = " and c.id not in ("+contentIdToAvoid+") ";
	    			else
	    				contentCheck = " and c.id != "+contentIdToAvoid + " ";
	    		}
				if(cutoffDays > 0) {
					cutoffCheck = " and c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+cutoffDays+" DAY) ";
				}
	
				sql = "select c.id, c.title, c.sef_url, c.introtext,c.medium_kicker_image, c.extralarge_image, c.mobile_image, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, c.is_external,c.content_type_tag, date_format(c.created,'%M %e, %Y' ) AS createddate,date_format(c.created,'%Y-%m-%d' ) AS createddateYYMMDD, c.content_url, ";
				if(categoryTemplate.equals("section"))
					sql = sql + "js.title as sectitle, js.id as secid, js.sef_url as secsefurl, js.content_url as seccontenturl, js.title as curcattitle, js.id as curcatid, js.sef_url as curcatsefurl, js.content_url as curcatcontenturl " +
							"from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+categoryId+" and jas.section_id=js.id " +
									"and js.published=1 and ";
				else if(categoryTemplate.equals("category")) 
					sql = sql + "js.title as sectitle, js.id as secid, js.sef_url as secsefurl, js.content_url as seccontenturl, " +
							"jc.title as curcattitle, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.content_url as curcatcontenturl " +
							"from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+categoryId+" and " +
									"jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
				else if(categoryTemplate.equals("subcategory")) 
					sql = sql + "js.title as sectitle, js.id as secid, js.sef_url as secsefurl, js.content_url as seccontenturl, " +
					"jsc.title as curcattitle, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jc.content_url as curcatcontenturl " +
							"from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc " +
							"where jas.subcat_id="+categoryId+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id " +
									"and jc.published=1 and js.published=1 and ";
				else if(categoryTemplate.equals("subsubcategory")) 
					sql = sql + "js.title as sectitle, js.id as secid, js.sef_url as secsefurl, js.content_url as seccontenturl, " +
					"jssc.title as curcattitle, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jc.content_url as curcatcontenturl " +
					"from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc " +
					"where jas.sub_subcat_id="+categoryId+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 " +
					"and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
		
				sql = sql + "c.state=1 and c.id=jas.article_id "+contentCheck+cutoffCheck+ "group by c.id order by jas.ordering desc limit "+contentCount;
	
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				 contentAL = new ArrayList();
				System.out.println("IT WEB ContentChunkHelper.latestStory() ContentFetch Query->" + sql);
				while (rs.next()) {
					ccDTO = new ContentChunkDTO();
					ccDTO.setId(rs.getInt("id"));				
					ccDTO.setTitle(rs.getString("title"));
	                if(rs.getString("content_url") != null && rs.getString("content_url").trim().length() > 0) {
	                	ccDTO.setContentURL(rs.getString("content_url"));
	                } else {
	                	if(rs.getString("sef_url").trim().length() > 0) {
	                		ccDTO.setContentURL("story/" + rs.getString("sef_url").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
	                	} else {
	                		ccDTO.setContentURL("story/" + rs.getString("sef_url") + "/1/" + rs.getInt("id") + ".html");
	                	}
	                }
	                ccDTO.setContent_type_tag(rs.getString("content_type_tag") == null ? "" : rs.getString("content_type_tag"));
	                ccDTO.setCreateddateYYMMDD(rs.getString("createddateYYMMDD") == null ? "" : rs.getString("createddateYYMMDD"));
	            	ccDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
			        ccDTO.setCreatedDate(rs.getString("createddate") == null ? "" : rs.getString("createddate"));
	            	ccDTO.setSmallImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
	            	ccDTO.setKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
	            	ccDTO.setMediumImage(rs.getString("medium_kicker_image")==null?"":rs.getString("medium_kicker_image"));
	            	ccDTO.setLargeImage(rs.getString("large_kicker_image")==null?"":rs.getString("large_kicker_image"));
	            	ccDTO.setExtraLargeImage(rs.getString("extralarge_image")==null?"":rs.getString("extralarge_image"));
	            	ccDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
	            	ccDTO.setIsExternalURL(rs.getInt("is_external"));
	            	ccDTO.setSectionId(rs.getInt("secid"));
	                ccDTO.setSectionTitle(rs.getString("sectitle") == null ? "" : rs.getString("sectitle"));
	                if(rs.getString("seccontenturl") != null && rs.getString("seccontenturl").trim().length() > 0) {
	                	ccDTO.setSectionURL(rs.getString("seccontenturl"));
	                } else {
	                	if(rs.getString("secsefurl").trim().length() > 0) {
	                		ccDTO.setSectionURL("section/" + rs.getInt("id") + "/1/" + rs.getString("secsefurl").replaceAll(".html", "") + ".html");
	                	} else {
	                		ccDTO.setSectionURL("section/" + rs.getInt("id") + "/1/" + rs.getString("secsefurl") + ".html");
	                	}
	                }
	                ccDTO.setCurrentCategoryId(rs.getInt("curcatid"));
	                ccDTO.setCurrentCategoryTitle(rs.getString("curcattitle") == null ? "" : rs.getString("curcattitle"));
	                if(rs.getString("curcatcontenturl") != null && rs.getString("curcatcontenturl").trim().length() > 0) {
	                	ccDTO.setCurrentCategoryURL(rs.getString("curcatcontenturl"));
	                } else {
	    				if(categoryTemplate.equals("section")) {
		                	if(rs.getString("curcatsefurl").trim().length() > 0) {
		                		ccDTO.setCurrentCategoryURL("section/" + rs.getInt("curcatid") + "/1/" + rs.getString("curcatsefurl").replaceAll(".html", "") + ".html");
		                	} else {
		                		ccDTO.setCurrentCategoryURL("section/" + rs.getInt("curcatid") + "/1/" + rs.getString("curcatsefurl") + ".html");
		                	}
	                    }
						if(categoryTemplate.equals("category")) { 
			                	if(rs.getString("curcatsefurl").trim().length() > 0) {
			                		ccDTO.setCurrentCategoryURL("category/" + rs.getString("curcatsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("curcatid") + ".html");
			                	} else {
			                		ccDTO.setCurrentCategoryURL("category/" + rs.getString("curcatsefurl") + "/1/" + rs.getInt("curcatid") + ".html");
			                	}
		                }
						if(categoryTemplate.equals("subcategory")) { 
		                	if(rs.getString("curcatsefurl").trim().length() > 0) {
		                		ccDTO.setCurrentCategoryURL("subcategory/" + rs.getString("curcatsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("curcatid") + ".html");
		                	} else {
		                		ccDTO.setCurrentCategoryURL("subcategory/" + rs.getString("curcatsefurl") + "/1/" + rs.getInt("curcatid") + ".html");
		                	}
		                }
						if(categoryTemplate.equals("subsubcategory")) { 
		                	if(rs.getString("curcatsefurl").trim().length() > 0) {
		                		ccDTO.setCurrentCategoryURL("subsubcategory/" + rs.getString("curcatsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("curcatid") + ".html");
		                	} else {
		                		ccDTO.setCurrentCategoryURL("subsubcategory/" + rs.getString("curcatsefurl") + "/1/" + rs.getInt("curcatid") + ".html");
		                	}
	                    }
	                }
	            	contentAL.add(ccDTO);
				}
			}
		} catch (Exception e) {
			//System.out.println("IT WEB ContentChunkHelper.latestStory() Exception ->" + sql);
			throw new IndiaTodayHelperException("IT WEB ContentChunkHelper.latestStory() Exception ->"+sql,e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
			sql=null;
			ccDTO=null;
			
		}
		return contentAL;
	}

	public static ArrayList latestVideo(int categoryId, int contentCount, String contentIdToAvoid) throws Exception {
		//System.out.println("ContentChunkHelper latestVideo() id->"+categoryId+" - count->"+contentCount+" - idToAvoid->"+contentIdToAvoid);
        ArrayList contentAL =null;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        ContentChunkDTO ccDTO = null;
		String contentCheck = "";
		String sql = "";
		int cutoffDays = 0;
		String cutoffCheck = "";

		try {
    		if(contentIdToAvoid.trim().length() > 0 && !contentIdToAvoid.trim().equals("0")) {
    			if(contentIdToAvoid.indexOf(",") > 0)
    				contentCheck = " and c.id not in ("+contentIdToAvoid+") ";
    			else
    				contentCheck = " and c.id != "+contentIdToAvoid + " ";
    		}
            Dbconnection connect = null;
            connect = Dbconnection.getInstance();
            connection = connect.getConnection();
            try {
				if(categoryId > 0) {
					sql = "SELECT content_fetch_time FROM jos_categories where id="+categoryId;
				} else {
					sql = "SELECT content_fetch_time FROM jos_sections where id=86";
				}
				System.out.println("IT WEB ContentChunkHelper.latestVideo() ContentInterval Query->" + sql);
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cutoffDays = rs.getInt("content_fetch_time");
				}
				if(cutoffDays > 0) {
					cutoffCheck = " and c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+cutoffDays+" DAY) ";
				}
				sql="";
				pstmt=null;
				rs=null;
            } catch (Exception ex) {
				System.out.println("IT WEB ContentChunkHelper.latestVideo() ContentInterval Exception->" + ex.toString());
            }
            
			if(categoryId > 0) {
				sql = "SELECT c.id, c.title, c.sef_url, c.introtext, c.mobile_image, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, c.extralarge_image, " +
						"date_format(c.created,'%a, %e %b %Y' ) AS createddate, c.content_url, c.is_external, " +
						"cat.id as catid, cat.title as cattitle, cat.sef_url as catsefurl, cat.content_url as catcontenturl, cat.content_display_color, " +
						"s.id as secid, s.title as sectitle, s.sef_url as secsefurl, s.content_url as seccontenturl " +
						"FROM jos_content c, jos_article_section a, jos_categories cat, jos_sections s, jos_videogallerynames v " +
            		"where c.id=a.article_id and a.article_id=v.contentid and a.cat_id="+categoryId+" and c.state=1 and a.cat_id=cat.id " +
            		"and s.published=1 and cat.published=1 and cat.section=s.id "+contentCheck+cutoffCheck+" group by c.id " +
            				"order by a.ordering desc, a.article_id desc limit "+contentCount;
			} else {
				sql = "SELECT c.id, c.title, c.sef_url, c.introtext, c.mobile_image, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, c.extralarge_image, " +
						"date_format(c.created,'%a, %e %b %Y' ) AS createddate, c.content_url, c.is_external,cat.id as catid, cat.title as cattitle, cat.sef_url as catsefurl, cat.content_url as catcontenturl, cat.content_display_color,  " +
						"s.id as secid, s.title as sectitle, s.sef_url as secsefurl, s.content_url as seccontenturl, " +
						" s.content_display_color FROM jos_content c, jos_categories cat, jos_article_section a, jos_sections s, jos_videogallerynames v " +
						"where c.id=a.article_id and a.article_id=v.contentid and a.section_id=86 and c.state=1 and a.cat_id=cat.id  and cat.published=1 and cat.section=s.id and a.section_id=s.id and " +
							"s.published=1 "+contentCheck+cutoffCheck+" group by c.id order by a.ordering desc, a.article_id desc limit "+contentCount;
			}
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
			System.out.println("IT WEB ContentChunkHelper.latestVideo() ContentFetch Query->" + sql);
            contentAL = new ArrayList();
            while (rs.next()) {
            	ccDTO = new ContentChunkDTO();
            	ccDTO.setId(rs.getInt("id"));
            	ccDTO.setTitle(rs.getString("title") == null ? "" : rs.getString("title"));
                if(rs.getString("content_url") != null && rs.getString("content_url").trim().length() > 0) {
                	ccDTO.setContentURL(rs.getString("content_url"));
                } else {
                	if(rs.getString("sef_url").trim().length() > 0) {
                		ccDTO.setContentURL("video/" + rs.getString("sef_url").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
                	}
                }
            	ccDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
		        ccDTO.setCreatedDate(rs.getString("createddate") == null ? "" : rs.getString("createddate"));
            	ccDTO.setSmallImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
            	ccDTO.setKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
            	ccDTO.setLargeImage(rs.getString("large_kicker_image")==null?"":rs.getString("large_kicker_image"));
            	ccDTO.setExtraLargeImage(rs.getString("extralarge_image")==null?"":rs.getString("extralarge_image"));
            	ccDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
            	ccDTO.setIsExternalURL(rs.getInt("is_external"));

            	ccDTO.setSectionId(rs.getInt("secid"));
                ccDTO.setSectionTitle(rs.getString("sectitle") == null ? "" : rs.getString("sectitle"));
                if(rs.getString("seccontenturl") != null && rs.getString("seccontenturl").trim().length() > 0) {
                	ccDTO.setSectionURL(rs.getString("seccontenturl"));
                } else {
               		ccDTO.setSectionURL("videos");
                }
    			//if(categoryId > 0) {
	                ccDTO.setCategoryId(rs.getInt("catid"));
	                ccDTO.setCategoryTitle(rs.getString("cattitle") == null ? "" : rs.getString("cattitle"));
	                if(rs.getString("catcontenturl") != null && rs.getString("catcontenturl").trim().length() > 0) {
	                	ccDTO.setCategoryURL(rs.getString("catcontenturl"));
	                } else {
	                	if(rs.getString("catsefurl").trim().length() > 0) {
	                		ccDTO.setCategoryURL("videolist/" + rs.getString("catsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
	                	} else {
	                		ccDTO.setCategoryURL("videolist/" + rs.getString("catsefurl") + "/1/" + rs.getInt("id") + ".html");
	                	}
	                }
    			//}
            	ccDTO.setContentDisplayColor(rs.getString("content_display_color")==null?"":rs.getString("content_display_color"));
                contentAL.add(ccDTO);
            }
        } catch (Exception e) {
			System.out.println("IT WEB ContentChunkHelper.latestVideo() Exception->" + e.toString());
        } finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
			sql=null;
			ccDTO=null;
			
			
        }
        return contentAL;
    }
    
	public static ArrayList latestGallery(int categoryId, int contentCount, String contentIdToAvoid) throws Exception
    {
		System.out.println("ContentChunkHelper latestGallery() id->"+categoryId+" - count->"+contentCount+" - idToAvoid->"+contentIdToAvoid);
    	ArrayList contentAL = null;
    	Connection connection = null;
    	PreparedStatement pstmt = null,pstmt_pc=null;
    	 ContentChunkDTO ccDTO = null;
    	ResultSet rs = null,rs_pc=null;
    	String contentCheck = "";
    	String sql = "";
    	String cutoffCheck = "";
		int cutoffDays = 20;
		
    	
    	try {
        	if(contentIdToAvoid.trim().length() > 0 && !contentIdToAvoid.trim().equals("0")) {
        		if(contentIdToAvoid.indexOf(",") > 0)
        			contentCheck = "and gn.id not in ("+contentIdToAvoid+")";
        		else
        			contentCheck = "and gn.id != "+contentIdToAvoid;
        	}
    		Dbconnection connect = null;
    		connect = Dbconnection.getInstance();
    		connection = connect.getConnection();
			if(categoryId > 0) {
				sql = "SELECT content_fetch_time FROM jos_photocategories where id="+categoryId;
				System.out.println("IT WEB ContentChunkHelper.latestGallery() ContentInterval Query->" + sql);
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cutoffDays = rs.getInt("content_fetch_time");
				}
				sql="";
				pstmt=null;
				rs=null;
			}
			if(cutoffDays > 0) {
				cutoffCheck = " and gn.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+cutoffDays+" DAY) ";
			}
    		if(categoryId==0) {
    			sql = "SELECT gn.description,gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image,gn.medium_image, gn.small_image, gn.large_image, gn.extralarge_image, gn.content_url, gn.is_external " +
    				" FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
    				" where gn.published=1 "+contentCheck+cutoffCheck+" and pc.id=gs.cat_id and gn.id=gs.content_id and " +
    						" pc.published=1 group by gn.id order by gn.featured_ordering desc limit "+contentCount;
    		} else {
    			sql = "SELECT gn.description,gn.id, gn.Gallery_name, gn.Gallery_caption,gn.medium_image, gn.sef_url, gn.thumb_image, gn.small_image, gn.large_image, gn.extralarge_image, gn.content_url, gn.is_external " +
    			" ,pc.id as catid, pc.title as cattitle, pc.content_url as catcontenturl, pc.content_display_color as catcontentdisplaycolor " +
    			" FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
    			" where gs.cat_id="+categoryId+" and pc.id=gs.cat_id and gn.id=gs.content_id and gn.published=1 "+contentCheck+cutoffCheck+" and " +
    					" pc.published=1 group by gn.id order by gn.ordering desc limit "+contentCount;
    		}
			System.out.println("IT WEB ContentChunkHelper.latestGallery() ContentFetch Query->" + sql);
    		pstmt = connection.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		contentAL=new ArrayList();
    		while (rs.next()) {
    			ccDTO = new ContentChunkDTO();
    			ccDTO.setId(rs.getInt("id"));
    			ccDTO.setTitle(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
                if(rs.getString("content_url") != null && rs.getString("content_url").trim().length() > 0) {
                	ccDTO.setContentURL(rs.getString("content_url"));
                } else {
                	if(rs.getString("sef_url").trim().length() > 0) {
                		ccDTO.setContentURL("gallery/" + rs.getString("sef_url").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
                	} else {
                		ccDTO.setContentURL("gallery/" + rs.getString("sef_url") + "/1/" + rs.getInt("id") + ".html");
                	}
                }
    			ccDTO.setSmallImage(rs.getString("thumb_image") == null ? "" : rs.getString("thumb_image"));
    			ccDTO.setMediumImage(rs.getString("medium_image") == null ? "" : rs.getString("medium_image"));
    			ccDTO.setKickerImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
    			ccDTO.setKickerImageAltText(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
    			ccDTO.setLargeImage(rs.getString("large_image") == null ? "" : rs.getString("large_image"));
    			ccDTO.setLargeImageAltText(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
    			ccDTO.setExtraLargeImage(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
            	ccDTO.setIsExternalURL(rs.getInt("is_external"));
            	ccDTO.setShortDescription(rs.getString("description") == null ? "" : rs.getString("description"));
    			
                ccDTO.setSectionTitle("Photos");
          		ccDTO.setSectionURL("galleries");
    			if(categoryId > 0) {
	                ccDTO.setCategoryId(rs.getInt("catid"));
	                ccDTO.setCategoryTitle(rs.getString("cattitle") == null ? "" : rs.getString("cattitle"));
	                if(rs.getString("catcontenturl") != null && rs.getString("catcontenturl").trim().length() > 0) {
	                	ccDTO.setCategoryURL(rs.getString("catcontenturl"));
	                } else {
	                	if(rs.getString("catsefurl").trim().length() > 0) {
	                		ccDTO.setCategoryURL("gallerylist/" + rs.getString("catsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
	                	} else {
	                		ccDTO.setCategoryURL("gallerylist/" + rs.getString("catsefurl") + "/1/" + rs.getInt("id") + ".html");
	                	}
	                }
    			}
    			try {
					sql = "SELECT count(pg.id) as photo_count FROM jos_photogallery pg where pg.enable=1 and pg.gallery_id="+rs.getInt("id");
					System.out.print("Last sql-->"+sql);
					pstmt_pc = connection.prepareStatement(sql);
					rs_pc = pstmt_pc.executeQuery();					
					while (rs_pc.next()) {
						ccDTO.setPhotoCount(rs_pc.getInt("photo_count"));
					}
				} catch (Exception pcEx) {
					System.out.println("latestGallery Photo Gallery Count - "+pcEx.toString());
				} finally {
					sql="";
					pstmt_pc=null;
					rs_pc=null;
				}
    			contentAL.add(ccDTO);
    		}
    	} catch (Exception e) {
			System.out.println("IT WEB ContentChunkHelper.latestGallery() Exception->" + e.toString());
    	} finally {
    		if(rs!=null)
    			rs.close();
    		if(pstmt!=null)
    			pstmt.close();
    		if(connection!=null)
    			connection.close();
    		sql=null;
    		ccDTO=null;
    	}
    	return contentAL;
    }

    public static ArrayList externalContentDetails(int contentCount, String contentIdToAvoid, int status,String webSite) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentChunkDTO ccDTO = null;	
		ArrayList dataList = new ArrayList();
		String contentStatus = "";
		if(status==1) {
			contentStatus = " status=1 and ";
		}
	   
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "select id,title,long_title,byline,city,small_image,small_image_alt_text,large_image,large_image_alt_text,short_description," +
					"description,content_url,category_id,category_title,category_url,created_datetime,content_id,featured " +
					"from external_content where "+contentStatus+"  ";
			if(contentIdToAvoid.equals("0"))
				sql = sql + " website='"+webSite+"' group by id order by ordering DESC limit "+contentCount;
			else if(!contentIdToAvoid.equals("0") && contentIdToAvoid.indexOf(",") < 0)
				sql = sql + " and website='"+webSite+"' and id != "+contentIdToAvoid+" group by content_id order by ordering DESC limit "+contentCount;
			else
				sql = sql + " and id not in ("+contentIdToAvoid+") group by content_id order by ordering DESC limit "+contentCount;
			//System.out.println("@@@@@@@@@@@@@"+sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				ccDTO = new ContentChunkDTO();
				ccDTO.setId(rs.getInt("content_id"));				
				ccDTO.setTitle(rs.getString("long_title"));
				ccDTO.setContentURL(rs.getString("content_url"));                
            	ccDTO.setShortDescription(rs.getString("short_description") == null ? "" : rs.getString("short_description"));
     	        ccDTO.setCreatedDate(rs.getString("created_datetime") == null ? "" : rs.getString("created_datetime"));
            	ccDTO.setKickerImage(rs.getString("small_image")==null?"":rs.getString("small_image"));
            	ccDTO.setLargeImage(rs.getString("large_image")==null?"":rs.getString("large_image"));
            	ccDTO.setKickerImageAltText(rs.getString("small_image_alt_text")==null?"":rs.getString("small_image_alt_text"));
            	ccDTO.setLargeImageAltText(rs.getString("large_image_alt_text")==null?"":rs.getString("large_image_alt_text"));
            	ccDTO.setIsExternalURL(rs.getInt("featured"));               
            	ccDTO.setCurrentCategoryId(rs.getInt("category_id"));
                ccDTO.setCurrentCategoryTitle(rs.getString("category_title") == null ? "" : rs.getString("category_title"));  
                ccDTO.setCurrentCategoryURL(rs.getString("category_url") == null ? "" : rs.getString("category_url")); 
				dataList.add(ccDTO);
			}
		} catch (Exception e) {
			System.out.println("IT externalContentDetails-Exception is :" + e);
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
    public static ArrayList getIssueDate(int limit){	
    	ArrayList al =null;
	  	Connection conn = null;
		Statement stmt = null;
		ResultSet rs=null;
		ContentChunkDTO ccDTO=null;
		String sql =null;
		al= new ArrayList();
		try {
			conn = Dbconnection.getInstance().getConnection();			
			sql = "SELECT A.COVER_IMAGE,A.ID,A.TITLE FROM jos_magazine_categories A  where  A.published=1 ORDER BY A.ordering DESC LIMIT "+ limit;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);			
			while (rs.next()) {
				ccDTO = new ContentChunkDTO();		
				ccDTO.setTitle(rs.getString("title"));
				ccDTO.setKickerImage(rs.getString("COVER_IMAGE"));
				ccDTO.setId(rs.getInt("ID"));
				al.add(ccDTO);
			}
		} catch(SQLException sqls) {
			System.out.println("Calender helper SQLException -> " + sqls);
		}finally{
			
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.toString();
			}
			
		}
	  return al;
}
}