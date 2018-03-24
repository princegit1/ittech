package com.itgd.helper;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.ContentChunkDTO;
import com.itgd.dto.ContentTemplateDTO;
import com.itgd.dto.LatestContentDTO;

public class ContentTemplateHelper {
	
	public static void main(String[] args) {
		ContentTemplateHelper cth = new ContentTemplateHelper();
		try {
			//cth.latestStory("subcategory", 244, 1, 30, "0", "");
			
			cth.latestStory("category", 901, 1, 10, "0", "");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList latestStory(String categoryTemplate, int categoryId, int currentPageNum, int contentCount, String contentIdToAvoid, String contentOrderBy) throws Exception
	{
		System.out.println("ContentTemplateHelper.latestStory() template->"+categoryTemplate+" - id->"+categoryId+" - page->"+currentPageNum+" - count->"+contentCount+" - idToAvoid->"+contentIdToAvoid);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentTemplateDTO ctDTO = null;	
		ArrayList contentAL = null;
		int cutoffDays = 0;
		String cutoffCheck = "";
		String contentCheck = "";
		String sql = "";
		int vStartLimit = 0;
		int vEndLimit = 0;
		int totalContentCount=0;
		String sql_pc = "";
		PreparedStatement pstmt_pc = null;
		ResultSet rs_pc = null;
		String pcatid = "";
		int pcatlength = 0;
		String pcatidArray[] = null;
		String tempPrimaryCatTitle = "";
		String tempPrimaryCatURL = "";
		//Primary Category - Till 
		try {
			if((categoryTemplate.equals("section") || categoryTemplate.equals("category") || categoryTemplate.equals("subcategory") || categoryTemplate.equals("subsubcategory")) && categoryId > 0) {
				if(currentPageNum > 0) {
					vStartLimit = (currentPageNum-1)*contentCount;
				}
				vEndLimit = contentCount;
	            Dbconnection connect = null;
	            connect = Dbconnection.getInstance();
	            connection = connect.getConnection();
	    	    
            	if(categoryTemplate.equals("section")) {
					sql = "SELECT content_fetch_time FROM jos_sections where id="+categoryId;
				} else if(categoryTemplate.equals("category")) {
					sql = "SELECT content_fetch_time FROM jos_categories where id="+categoryId;
				} else if(categoryTemplate.equals("subcategory")) {
					sql = "SELECT content_fetch_time FROM jos_subcategories where id="+categoryId;
				} else if(categoryTemplate.equals("subsubcategory")) {
					sql = "SELECT content_fetch_time FROM jos_sub_subcategories where id="+categoryId;
				}            
				System.out.println("IT WAP ContentTemplateHelper.latestStory() ContentInterval Query->" + sql);
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cutoffDays = rs.getInt("content_fetch_time");
				}
				sql="";
				pstmt=null;
				rs=null;
				
				if(!contentIdToAvoid.equals("0")) {
	    			if(contentIdToAvoid.indexOf(",") > 0)
	    				contentCheck = " and c.id not in ("+contentIdToAvoid+") ";
	    			else
	    				contentCheck = " and c.id != "+contentIdToAvoid + " ";
	    		}
				if(cutoffDays > 0) {
					cutoffCheck = " and c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+cutoffDays+" DAY) ";
				}
				

				sql = "select count(distinct c.id) as totalCount ";
				if(categoryTemplate.equals("section"))
					sql = sql + " from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+categoryId+" and jas.section_id=js.id and js.published=1 and c.state=1 and c.id=jas.article_id "+contentCheck+cutoffCheck+ " order by jas.ordering desc";
				else if(categoryTemplate.equals("category")) 
					sql = sql + " from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+categoryId+" and jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and c.state=1 and c.id=jas.article_id "+contentCheck+cutoffCheck+ " order by jas.ordering desc ";
				else if(categoryTemplate.equals("subcategory")) 
					sql = sql + " from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc where jas.subcat_id="+categoryId+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id and jc.published=1 and js.published=1 and c.state=1 and c.id=jas.article_id "+contentCheck+cutoffCheck+ " order by jas.ordering desc";
				else if(categoryTemplate.equals("subsubcategory")) 
					sql = sql + " from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc where jas.sub_subcat_id="+categoryId+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and c.state=1 and c.id=jas.article_id "+contentCheck+cutoffCheck+ " order by jas.ordering";
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					totalContentCount = rs.getInt("totalCount");
				}
						
				System.out.println(sql+"@@@@"+totalContentCount);
				sql="";
				pstmt=null;
				rs=null;
	
				sql = "select c.id, c.title, c.sef_url, c.byline, c.city, c.introtext, c.extralarge_image, c.mobile_image, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, c.large_kicker_image_alt_text, c.is_external, date_format(c.created,'%M %e, %Y' ) AS createddate,date_format(c.created,'%Y-%m-%e' ) AS createddateYYMMDD, c.content_url, c.primary_category,c.medium_kicker_image, c.short_introtext, ";
				if(categoryTemplate.equals("section"))
					sql = sql + "js.title as sectitle, js.id as secid, js.sef_url as secsefurl, js.content_url as seccontenturl, js.title as curcattitle, js.id as curcatid, js.sef_url as curcatsefurl, " +
							"js.content_url as curcatcontenturl, js.metatitle, js.metakey, js.metadesc, js.topnav_path, js.rightnav_path, js.bottomnav_path, js.leftnav_path, js.header_image as header_image " +
							"from jos_content c, jos_article_section jas, jos_sections js where jas.section_id="+categoryId+" and jas.section_id=js.id " +
									"and js.published=1 and ";
				else if(categoryTemplate.equals("category")) 
					sql = sql + " js.title as sectitle, js.id as secid, js.sef_url as secsefurl, js.content_url as seccontenturl, " +
							"jc.title as cattitle, jc.id as catid, jc.sef_url as catsefurl, jc.content_url as catcontenturl, jc.title as curcattitle, jc.id as curcatid, jc.sef_url as curcatsefurl, jc.content_url as curcatcontenturl, jc.metatitle, jc.metakey, jc.metadesc, jc.topnav_path, jc.rightnav_path, jc.bottomnav_path, jc.leftnav_path, jc.header_image as header_image " +
							"from jos_categories jc, jos_content c, jos_article_section jas, jos_sections js where jas.cat_id="+categoryId+" and " +
									"jc.id=jas.cat_id and jc.published=1 and js.published=1 and jc.section=js.id and ";
				else if(categoryTemplate.equals("subcategory")) 
					sql = sql + "js.title as sectitle, js.id as secid, js.sef_url as secsefurl, js.content_url as seccontenturl, " +
					"jc.title as cattitle, jc.id as catid, jc.sef_url as catsefurl, jc.content_url as catcontenturl, jsc.title as subcattitle, jsc.id as subcatid, jsc.sef_url as subcatsefurl, jsc.content_url as subcatcontenturl, " +
					"jsc.title as curcattitle, jsc.id as curcatid, jsc.sef_url as curcatsefurl, jsc.content_url as curcatcontenturl, jsc.metatitle, jsc.metakey, jsc.metadesc, jsc.topnav_path, jsc.rightnav_path, jsc.bottomnav_path, jsc.leftnav_path " +
							"from jos_subcategories jsc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc " +
							"where jas.subcat_id="+categoryId+" and jsc.id=jas.subcat_id and jsc.published=1 and  jsc.category=jc.id and jsc.section=js.id " +
									"and jc.published=1 and js.published=1 and ";
				else if(categoryTemplate.equals("subsubcategory")) 
					sql = sql + "js.title as sectitle, js.id as secid, js.sef_url as secsefurl, js.content_url as seccontenturl, " +
					"jc.title as cattitle, jc.id as catid, jc.sef_url as catsefurl, jc.content_url as catcontenturl, jsc.title as subcattitle, " +
					"jsc.id as subcatid, jsc.sef_url as subcatsefurl, jsc.content_url as subcatcontenturl, jssc.title as subsubcattitle, " +
					"jssc.id as subsubcatid, jssc.sef_url as subsubcatsefurl, jssc.content_url as subsubcatcontenturl, jssc.title as curcattitle, jssc.id as curcatid, jssc.sef_url as curcatsefurl, jssc.content_url as curcatcontenturl, jssc.metatitle, jssc.metakey, jssc.metadesc, jssc.topnav_path, jssc.rightnav_path, jssc.bottomnav_path, jssc.leftnav_path " +
					"from jos_sub_subcategories jssc, jos_content c, jos_article_section jas, jos_sections js, jos_categories jc, jos_subcategories jsc " +
					"where jas.sub_subcat_id="+categoryId+" and jssc.id=jas.sub_subcat_id and jssc.published=1 and js.published=1 and jsc.published=1 " +
					"and jc.published=1 and jsc.id=jas.subcat_id and jc.id=jas.cat_id and js.id=jas.section_id and ";
		
				sql = sql + " c.state=1 and c.id=jas.article_id "+contentCheck+cutoffCheck+ "group by c.id";
				if(contentOrderBy.trim().equals("date")) {	
					sql = sql + " order by c.created ";
				} else {
					sql = sql + " order by jas.ordering ";
				}
				sql = sql + " desc limit " +vStartLimit + ", "+vEndLimit;

				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				contentAL = new ArrayList();
				System.out.print("IT WAP ContentTemplateHelper.latestStory() ContentFetch Query->" + sql);
				while (rs.next()) {
					ctDTO = new ContentTemplateDTO();
					ctDTO.setId(rs.getInt("id"));				
					ctDTO.setTitle(rs.getString("title"));
	            	ctDTO.setByline(rs.getString("byline") == null ? "" : rs.getString("byline"));
	            	ctDTO.setCity(rs.getString("city") == null ? "" : rs.getString("city"));
	                if(rs.getString("content_url") != null && rs.getString("content_url").trim().length() > 0) {
	                	ctDTO.setContentURL(rs.getString("content_url"));
	                } else {
	                	if(rs.getString("sef_url").trim().length() > 0) {
	                		ctDTO.setContentURL("story/" + rs.getString("sef_url").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
	                	} else {
	                		ctDTO.setContentURL("story/" + rs.getString("sef_url") + "/1/" + rs.getInt("id") + ".html");
	                	}
	                }
	                
	                ctDTO.setCreateddateYYMMDD(rs.getString("createddateYYMMDD") == null ? "" : rs.getString("createddateYYMMDD"));
	                ctDTO.setMediumKickerImage(rs.getString("medium_kicker_image") == null ? "" : rs.getString("medium_kicker_image"));
	                ctDTO.setCrispyKicker(rs.getString("short_introtext") == null ? "" : rs.getString("short_introtext"));
	            	ctDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
			        ctDTO.setCreatedDate(rs.getString("createddate") == null ? "" : rs.getString("createddate"));
	            	ctDTO.setSmallImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
	            	ctDTO.setKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
	            	ctDTO.setLargeImage(rs.getString("large_kicker_image")==null?"":rs.getString("large_kicker_image"));
	            	ctDTO.setLargeImageAltText(rs.getString("large_kicker_image_alt_text")==null?"":rs.getString("large_kicker_image_alt_text"));
	            	ctDTO.setExtraLargeImage(rs.getString("extralarge_image")==null?"":rs.getString("extralarge_image"));
	            	ctDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
	            	ctDTO.setIsExternalURL(rs.getInt("is_external"));
	                ctDTO.setMetaTitle(rs.getString("metatitle") == null ? "" : rs.getString("metatitle"));
	                ctDTO.setMetaKeyword(rs.getString("metakey") == null ? "" : rs.getString("metakey"));
	                ctDTO.setMetaDescription(rs.getString("metadesc") == null ? "" : rs.getString("metadesc"));
	            	ctDTO.setTopNavigationPath(rs.getString("topnav_path") == null ? "" : rs.getString("topnav_path"));		
	            	ctDTO.setRightNavigationPath(rs.getString("rightnav_path") == null ? "" : rs.getString("rightnav_path"));		
	            	ctDTO.setBottomNavigationPath(rs.getString("bottomnav_path") == null ? "" : rs.getString("bottomnav_path"));		
	            	ctDTO.setLeftNavigationPath(rs.getString("leftnav_path") == null ? "" : rs.getString("leftnav_path"));		
	                ctDTO.setCurrentCategoryId(rs.getInt("curcatid"));
	                ctDTO.setCurrentCategoryTitle(rs.getString("curcattitle") == null ? "" : rs.getString("curcattitle"));
	                pcatid = (rs.getString("primary_category")==null?"":rs.getString("primary_category"));

                    if(categoryTemplate.equals("section") || categoryTemplate.equals("category")) {
                    	ctDTO.setHeaderImage(rs.getString("header_image") == null ? "" : rs.getString("header_image"));
                    }
	                if(rs.getString("curcatcontenturl") != null && rs.getString("curcatcontenturl").trim().length() > 0) {
	                	ctDTO.setCurrentCategoryURL(rs.getString("curcatcontenturl"));
	                } else {
	                    if(categoryTemplate.equals("section")) {
		                	if(rs.getString("curcatsefurl").trim().length() > 0) { 
		                		ctDTO.setCurrentCategoryURL("section/" + rs.getInt("curcatid") + "/1/" + rs.getString("curcatsefurl").replaceAll(".html", "") + ".html");
		                	} else {
		                		ctDTO.setCurrentCategoryURL("section/" + rs.getInt("curcatid") + "/1/" + rs.getString("curcatsefurl") + ".html");
		                	}
	                    }
	                    if(categoryTemplate.equals("category")) {
		                	if(rs.getString("curcatsefurl").trim().length() > 0) { 
		                		ctDTO.setCurrentCategoryURL("category/" + rs.getString("curcatsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("curcatid") + ".html");
		                	} else {
		                		ctDTO.setCurrentCategoryURL("category/" + rs.getString("curcatsefurl") + "/1/" + rs.getInt("curcatid") + ".html");
		                	}
	                    }
	                    if(categoryTemplate.equals("subcategory")) {
		                	if(rs.getString("curcatsefurl").trim().length() > 0) { 
		                		ctDTO.setCurrentCategoryURL("subcategory/" + rs.getString("curcatsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("curcatid") + ".html");
		                	} else {
		                		ctDTO.setCurrentCategoryURL("subcategory/" + rs.getString("curcatsefurl") + "/1/" + rs.getInt("curcatid") + ".html");
		                	}
	                    }
	                    if(categoryTemplate.equals("subsubcategory")) {
		                	if(rs.getString("curcatsefurl").trim().length() > 0) { 
		                		ctDTO.setCurrentCategoryURL("subsubcategory/" + rs.getString("curcatsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("curcatid") + ".html");
		                	} else {
		                		ctDTO.setCurrentCategoryURL("subsubcategory/" + rs.getString("curcatsefurl") + "/1/" + rs.getInt("curcatid") + ".html");
		                	}	                    
		                }
	                }
	            	
	            	ctDTO.setSectionId(rs.getInt("secid"));
	                ctDTO.setSectionTitle(rs.getString("sectitle") == null ? "" : rs.getString("sectitle"));
	                if(rs.getString("seccontenturl") != null && rs.getString("seccontenturl").trim().length() > 0) {
	                	ctDTO.setSectionURL(rs.getString("seccontenturl"));
	                } else {
	                	if(rs.getString("secsefurl").trim().length() > 0) {
	                		ctDTO.setSectionURL("section/" + rs.getInt("id") + "/1/" + rs.getString("secsefurl").replaceAll(".html", "") + ".html");
	                	} else {
	                		ctDTO.setSectionURL("section/" + rs.getInt("id") + "/1/" + rs.getString("secsefurl") + ".html");
	                	}
	                }
                    if(categoryTemplate.equals("category") || categoryTemplate.equals("subcategory") || categoryTemplate.equals("subsubcategory")) {
		                ctDTO.setCategoryId(rs.getInt("catid"));
		                ctDTO.setCategoryTitle(rs.getString("cattitle") == null ? "" : rs.getString("cattitle"));
		                if(rs.getString("catcontenturl") != null && rs.getString("catcontenturl").trim().length() > 0) {
		                	ctDTO.setCategoryURL(rs.getString("catcontenturl"));
		                } else {
		                	if(rs.getString("catsefurl").trim().length() > 0) {
		                		ctDTO.setCategoryURL("category/" + rs.getString("catsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("catid") + ".html");
		                	} else {
		                		ctDTO.setCategoryURL("category/" + rs.getString("catsefurl") + "/1/" + rs.getInt("catid") + ".html");
		                	}
		                }
                    }
                    if(categoryTemplate.equals("subcategory") || categoryTemplate.equals("subsubcategory")) {
		                ctDTO.setSubCategoryId(rs.getInt("subcatid"));
		                ctDTO.setSubCategoryTitle(rs.getString("subcattitle") == null ? "" : rs.getString("subcattitle"));
		                if(rs.getString("subcatcontenturl") != null && rs.getString("subcatcontenturl").trim().length() > 0) {
		                	ctDTO.setSubCategoryURL(rs.getString("subcatcontenturl"));
		                } else {
		                	if(rs.getString("subcatsefurl").trim().length() > 0) { 
		                		ctDTO.setSubCategoryURL("subcategory/" + rs.getString("subcatsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("subcatid") + ".html");
		                	} else {
		                		ctDTO.setSubCategoryURL("subcategory/" + rs.getString("subcatsefurl") + "/1/" + rs.getInt("subcatid") + ".html");
		                	}
		                }
                    }
                    if(categoryTemplate.equals("subsubcategory")) {
		                ctDTO.setSubSubCategoryId(rs.getInt("subsubcatid"));
		                ctDTO.setSubSubCategoryTitle(rs.getString("subsubcattitle") == null ? "" : rs.getString("subsubcattitle"));
		                if(rs.getString("subsubcatcontenturl") != null && rs.getString("subsubcatcontenturl").trim().length() > 0) {
		                	ctDTO.setSubSubCategoryURL(rs.getString("subsubcatcontenturl"));
		                } else {
		                	if(rs.getString("subsubcatsefurl").trim().length() > 0) { 
		                		ctDTO.setSubSubCategoryURL("subsubcategory/" + rs.getString("subsubcatsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("subsubcatid") + ".html");
		                	} else {
		                		ctDTO.setSubSubCategoryURL("subsubcategory/" + rs.getString("subsubcatsefurl") + "/1/" + rs.getInt("subsubcatid") + ".html");
		                	}
		                }
                    }
                    ctDTO.setTotalContentCount(totalContentCount);
	                //Primary Category Title and its URL - From	
	                if(pcatid.trim().length() > 0) {	
	                	if(pcatid.indexOf("#") > 0) {
		        	    	pcatidArray = pcatid.split("#");
		        		} else {
		        			pcatidArray = new String[1];
		        			pcatidArray[0] = pcatid;
		        		}
	                	pcatlength = pcatidArray.length;
		    			sql_pc = "select id, title, sef_url, content_url ";
		    			if(pcatlength==1)
		    				sql_pc = sql_pc + " from jos_sections where id="+pcatidArray[0]+" and published=1";
		    			else if(pcatlength==2) 
		    				sql_pc = sql_pc + " from jos_categories where id="+pcatidArray[1]+" and published=1";
		    			else if(pcatlength==3) 
		    				sql_pc = sql_pc + " from jos_subcategories where id="+pcatidArray[2]+" and published=1";
		    			else if(pcatlength==4) 
		    				sql_pc = sql_pc + " from jos_sub_subcategories where id="+pcatidArray[3]+" and published=1";
		    			
		    			//System.out.println("primary-->"+sql_pc);
						//pstmt_pc = connection.prepareStatement(sql_pc);
						//rs_pc = pstmt_pc.executeQuery();
	
		    			pstmt_pc = connection.prepareStatement(sql_pc);
		    			rs_pc = pstmt_pc.executeQuery();
		    			while (rs_pc.next()) {
		    				tempPrimaryCatTitle = rs_pc.getString("title") == null ? "" : rs_pc.getString("title");
		    				tempPrimaryCatURL = rs_pc.getString("content_url") == null ? "" : rs_pc.getString("content_url");
		    				if(tempPrimaryCatURL.trim().length() == 0 && rs_pc.getString("sef_url").trim().length() > 0) {
								if(pcatlength == 1) {
									tempPrimaryCatURL = ("section/" + rs_pc.getInt("id") + "/1/" + rs_pc.getString("sef_url").replaceAll(".html", "") + ".html");
								}
								if(pcatlength == 2) {
									tempPrimaryCatURL = ("category/" + rs_pc.getString("sef_url").replaceAll(".html", "") + "/1/" + rs_pc.getInt("id") + ".html");
								}
								if(pcatlength == 3) {
									tempPrimaryCatURL = ("subcategory/" + rs_pc.getString("sef_url").replaceAll(".html", "") + "/1/" + rs_pc.getInt("id") + ".html");
								}
								if(pcatlength == 4) {
									tempPrimaryCatURL = ("subsubcategory/" + rs_pc.getString("sef_url").replaceAll(".html", "") + "/1/" + rs_pc.getInt("id") + ".html");
								}
							}
		    			}
	                }
    				ctDTO.setPrimaryCategoryTitle(tempPrimaryCatTitle.trim());
    				ctDTO.setPrimaryCategoryURL(tempPrimaryCatURL.trim());
	              
    				//System.out.println("primery category title"+tempPrimaryCatTitle);
    				//System.out.println("primery category url"+tempPrimaryCatURL);
    				
	            	contentAL.add(ctDTO);
				}
			}
		} catch (Exception e) {
			System.out.print("IT WEB ContentTemplateHelper.latestStory() Exception ->" + e.getMessage());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs_pc!=null)
				rs_pc.close();
			if(pstmt_pc!=null)
				pstmt_pc.close();
			if(connection!=null)
				connection.close();
			ctDTO=null;
			sql=null;
		}
		return contentAL;
	}
	
    public static ArrayList latestVideo(int categoryId, int currentPageNum, int contentCount, String contentIdToAvoid) throws Exception {
		//System.out.println("ContentTemplateHelper.latestVideo() id->"+categoryId+" - page->"+currentPageNum+" - count->"+contentCount+" - idToAvoid->"+contentIdToAvoid);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentTemplateDTO ctDTO = null;	
		ArrayList contentAL = null;
		int cutoffDays = 0;
		String cutoffCheck = "";
		String contentCheck = "";
		String sql = "";
		int vStartLimit = 0;
		int vEndLimit = 0;
        try {
			if(currentPageNum > 0) {
				vStartLimit = (currentPageNum-1)*contentCount;
			}
			vEndLimit = contentCount;
			if(!contentIdToAvoid.equals("0")) {
    			if(contentIdToAvoid.indexOf(",") > 0)
    				contentCheck = " and c.id not in ("+contentIdToAvoid+") ";
    			else
    				contentCheck = " and c.id != "+contentIdToAvoid + " ";
    		}

            Dbconnection connect = null;
            connect = Dbconnection.getInstance();
            connection = connect.getConnection();
			if(categoryId > 0) {
				sql = "SELECT content_fetch_time FROM jos_categories where id="+categoryId;
			} else {
				sql = "SELECT content_fetch_time FROM jos_sections where id=86";
			}
			System.out.print("IT WAP ContentTemplateHelper.latestVideo() ContentInterval Query->" + sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				cutoffDays = rs.getInt("content_fetch_time");
			}
			sql="";
			pstmt=null;
			rs=null;
			if(cutoffDays > 0) {
				cutoffCheck = " and c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+cutoffDays+" DAY) ";
			}

			if(categoryId > 0) {
				sql = "SELECT c.medium_kicker_image, c.short_introtext, c.id, c.title, c.sef_url, c.introtext, c.mobile_image, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, c.extralarge_image, " +
						"date_format(c.created,'%M %e, %Y' ) AS createddate, c.content_url, c.is_external, " +
						"cat.id as catid, cat.title as cattitle, cat.sef_url as catsefurl, cat.content_url as catcontenturl, cat.content_display_color, " +
						" cat.metatitle, cat.metakey, cat.metadesc, cat.topnav_path, cat.rightnav_path, cat.bottomnav_path, cat.leftnav_path, " +
						"s.id as secid, s.title as sectitle, s.sef_url as secsefurl, s.content_url as seccontenturl " +
						"FROM jos_content c, jos_article_section a, jos_categories cat, jos_sections s " +
            		"where c.id=a.article_id and a.cat_id="+categoryId+" and c.state=1 and a.cat_id=cat.id " +
            		"and s.published=1 and cat.published=1 and cat.section=s.id "+contentCheck+cutoffCheck+" and c.is_external=0 group by c.id " +
            				"order by a.ordering desc, a.article_id desc limit " +vStartLimit + ", "+vEndLimit;
			} else {
				sql = "SELECT c.medium_kicker_image, c.short_introtext, c.id, c.title, c.sef_url, c.introtext, c.mobile_image, c.kicker_image, c.kicker_image_alt_text, c.large_kicker_image, c.extralarge_image, " +
						"date_format(c.created,'%M %e, %Y' ) AS createddate, c.content_url, c.is_external, " +
						" s.metatitle, s.metakey, s.metadesc, s.topnav_path, s.rightnav_path, s.bottomnav_path, s.leftnav_path, " +
						"s.id as secid, s.title as sectitle, s.sef_url as secsefurl, s.content_url as seccontenturl, " +
						" s.content_display_color FROM jos_content c, jos_article_section a, jos_sections s " +
						"where c.id=a.article_id and a.section_id=86 and c.state=1 and a.section_id=s.id and " +
							"s.published=1 "+contentCheck+cutoffCheck+" and c.is_external=0 group by c.id order by a.ordering desc, a.article_id desc limit " +vStartLimit + ", "+vEndLimit;
			}
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
			System.out.print("nitin===>>IT WAP ContentTemplateHelper.latestVideo() ContentFetch Query->" + sql);
            contentAL = new ArrayList();
            while (rs.next()) {
            	ctDTO = new ContentTemplateDTO();
            	ctDTO.setId(rs.getInt("id"));
            	ctDTO.setTitle(rs.getString("title") == null ? "" : rs.getString("title"));
                if(rs.getString("content_url") != null && rs.getString("content_url").trim().length() > 0) {
                	ctDTO.setContentURL(rs.getString("content_url"));
                } else {
                	if(rs.getString("sef_url").trim().length() > 0) {
                		ctDTO.setContentURL("video/" + rs.getString("sef_url").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
                	} else {
                		ctDTO.setContentURL("video/" + rs.getString("sef_url") + "/1/" + rs.getInt("id") + ".html");
                	}
                }
                ctDTO.setMediumKickerImage(rs.getString("medium_kicker_image") == null ? "" : rs.getString("medium_kicker_image"));
                ctDTO.setCrispyKicker(rs.getString("short_introtext") == null ? "" : rs.getString("short_introtext"));
            	ctDTO.setShortDescription(rs.getString("introtext") == null ? "" : rs.getString("introtext"));
		        ctDTO.setCreatedDate(rs.getString("createddate") == null ? "" : rs.getString("createddate"));
            	ctDTO.setSmallImage(rs.getString("mobile_image")==null?"":rs.getString("mobile_image"));
            	ctDTO.setKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
            	ctDTO.setLargeImage(rs.getString("large_kicker_image")==null?"":rs.getString("large_kicker_image"));
            	ctDTO.setExtraLargeImage(rs.getString("extralarge_image")==null?"":rs.getString("extralarge_image"));
            	ctDTO.setKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
            	ctDTO.setIsExternalURL(rs.getInt("is_external"));
                ctDTO.setMetaTitle(rs.getString("metatitle") == null ? "" : rs.getString("metatitle"));
                ctDTO.setMetaKeyword(rs.getString("metakey") == null ? "" : rs.getString("metakey"));
                ctDTO.setMetaDescription(rs.getString("metadesc") == null ? "" : rs.getString("metadesc"));
            	ctDTO.setTopNavigationPath(rs.getString("topnav_path") == null ? "" : rs.getString("topnav_path"));		
            	ctDTO.setRightNavigationPath(rs.getString("rightnav_path") == null ? "" : rs.getString("rightnav_path"));		
            	ctDTO.setBottomNavigationPath(rs.getString("bottomnav_path") == null ? "" : rs.getString("bottomnav_path"));		
            	ctDTO.setLeftNavigationPath(rs.getString("leftnav_path") == null ? "" : rs.getString("leftnav_path"));		

            	ctDTO.setSectionId(rs.getInt("secid"));
                ctDTO.setSectionTitle(rs.getString("sectitle") == null ? "" : rs.getString("sectitle"));
                if(rs.getString("seccontenturl") != null && rs.getString("seccontenturl").trim().length() > 0) {
                	ctDTO.setSectionURL(rs.getString("seccontenturl"));
                } else {
               		ctDTO.setSectionURL("videos");
                } 
    			if(categoryId > 0) {
	                ctDTO.setCategoryId(rs.getInt("catid"));
	                ctDTO.setCategoryTitle(rs.getString("cattitle") == null ? "" : rs.getString("cattitle"));
	                if(rs.getString("catcontenturl") != null && rs.getString("catcontenturl").trim().length() > 0) {
	                	ctDTO.setCategoryURL(rs.getString("catcontenturl"));
	                } else {
	                	if(rs.getString("catsefurl").indexOf(".html") > 0) {
	                		ctDTO.setCategoryURL("videolist/" + rs.getString("catsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
	                	} else {
	                		ctDTO.setCategoryURL("videolist/" + rs.getString("catsefurl") + "/1/" + rs.getInt("id") + ".html");
	                	}
	                }
    			}
            	ctDTO.setContentDisplayColor(rs.getString("content_display_color")==null?"":rs.getString("content_display_color"));
                contentAL.add(ctDTO);
            }
        } catch (Exception e) {
			System.out.print("IT WAP ContentTemplateHelper.latestVideo() Exception->" + e.toString());
        } finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
			ctDTO=null;
			sql=null;
        }
        return contentAL;
    }

    public static ArrayList latestGallery (int categoryId, int currentPageNum, int contentCount, String contentIdToAvoid) throws Exception
    {
		//System.out.println("ContentTemplateHelper.latestGallery() id->"+categoryId+" - page->"+currentPageNum+" - count->"+contentCount+" - idToAvoid->"+contentIdToAvoid);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ContentTemplateDTO ctDTO = null;	
		ArrayList contentAL = null;
		int cutoffDays = 30;
		String cutoffCheck = "";
		String contentCheck = "";
		String sql = "";
		int vStartLimit = 0;
		int vEndLimit = 0;

    	try {
			if(currentPageNum > 0) {
				vStartLimit = (currentPageNum-1)*contentCount;
			}
			vEndLimit = contentCount;
			if(!contentIdToAvoid.equals("0")) {
    			if(contentIdToAvoid.indexOf(",") > 0)
    				contentCheck = " and gn.id not in ("+contentIdToAvoid+") ";
    			else
    				contentCheck = " and gn.id != "+contentIdToAvoid + " ";
    		}

			Dbconnection connect = null;
    		connect = Dbconnection.getInstance();
    		connection = connect.getConnection();
			if(categoryId > 0) {
				sql = "SELECT content_fetch_time FROM jos_photocategories where id="+categoryId;
				System.out.print("IT WAP ContentTemplateHelper.latestGallery() ContentInterval Query->" + sql);
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
    			sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image, gn.small_image, gn.large_image, gn.extralarge_image, gn.content_url " +
    				" FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
    				" where gn.featured_page=1 and gn.published=1 "+contentCheck+cutoffCheck+" and pc.id=gs.cat_id and gn.id=gs.content_id and " +
    						" pc.published=1 and gn.is_external=0 group by gn.id order by gn.featured_ordering desc limit " +vStartLimit + ", "+vEndLimit;
    		} else {
    			sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url, gn.thumb_image, gn.small_image, gn.large_image, gn.extralarge_image, gn.content_url " +
    			" ,pc.id as catid, pc.title as cattitle, pc.content_url as catcontenturl, pc.content_display_color as catcontentdisplaycolor " +
				" ,pc.metatitle, pc.metakey, pc.metadesc, pc.topnav_path, pc.rightnav_path, pc.bottomnav_path, pc.leftnav_path " +
    			" FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
    			" where gs.cat_id="+categoryId+" and pc.id=gs.cat_id and gn.id=gs.content_id and gn.published=1 "+contentCheck+cutoffCheck+" and " +
    					" pc.published=1 and gn.is_external=0 group by gn.id order by gn.ordering desc limit " +vStartLimit + ", "+vEndLimit;
    		}
			System.out.print("IT WAP ContentTemplateHelper.latestGallery() ContentFetch Query->" + sql);
    		pstmt = connection.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		contentAL = new ArrayList();
    		while (rs.next()) {
    			ctDTO = new ContentTemplateDTO();
    			ctDTO.setId(rs.getInt("id"));
    			ctDTO.setTitle(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
                if(rs.getString("content_url") != null && rs.getString("content_url").trim().length() > 0) {
                	ctDTO.setContentURL(rs.getString("content_url"));
                } else {
                	if(rs.getString("sef_url").indexOf(".html") > 0) {
                		ctDTO.setContentURL("gallery/" + rs.getString("sef_url").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
                	} else {
                		ctDTO.setContentURL("gallery/" + rs.getString("sef_url") + "/1/" + rs.getInt("id") + ".html");
                	}
                }
    			ctDTO.setSmallImage(rs.getString("thumb_image") == null ? "" : rs.getString("thumb_image"));
    			ctDTO.setKickerImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
    			ctDTO.setKickerImageAltText(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
    			ctDTO.setLargeImage(rs.getString("large_image") == null ? "" : rs.getString("large_image"));
    			ctDTO.setLargeImageAltText(rs.getString("Gallery_name") == null ? "" : rs.getString("Gallery_name"));
    			ctDTO.setExtraLargeImage(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
                ctDTO.setMetaTitle(rs.getString("metatitle") == null ? "" : rs.getString("metatitle"));
                ctDTO.setMetaKeyword(rs.getString("metakey") == null ? "" : rs.getString("metakey"));
                ctDTO.setMetaDescription(rs.getString("metadesc") == null ? "" : rs.getString("metadesc"));
    			
                ctDTO.setSectionTitle("Photos");
          		ctDTO.setSectionURL("galleries");
    			if(categoryId > 0) {
	                ctDTO.setCategoryId(rs.getInt("catid"));
	                ctDTO.setCategoryTitle(rs.getString("cattitle") == null ? "" : rs.getString("cattitle"));
	                if(rs.getString("catcontenturl") != null && rs.getString("catcontenturl").trim().length() > 0) {
	                	ctDTO.setCategoryURL(rs.getString("catcontenturl"));
	                } else {
	                	if(rs.getString("catsefurl").indexOf(".html") > 0) {
	                		ctDTO.setCategoryURL("gallerylist/" + rs.getString("catsefurl").replaceAll(".html", "") + "/1/" + rs.getInt("id") + ".html");
	                	} else {
	                		ctDTO.setCategoryURL("gallerylist/" + rs.getString("catsefurl") + "/1/" + rs.getInt("id") + ".html");
	                	}
	                }
	            	ctDTO.setTopNavigationPath(rs.getString("topnav_path") == null ? "" : rs.getString("topnav_path"));		
	            	ctDTO.setRightNavigationPath(rs.getString("rightnav_path") == null ? "" : rs.getString("rightnav_path"));		
	            	ctDTO.setBottomNavigationPath(rs.getString("bottomnav_path") == null ? "" : rs.getString("bottomnav_path"));		
	            	ctDTO.setLeftNavigationPath(rs.getString("leftnav_path") == null ? "" : rs.getString("leftnav_path"));		
    			}
    			
    			contentAL.add(ctDTO);
    		}
    	} catch (Exception e) {
			System.out.print("IT WAP ContentTemplateHelper.latestGallery() Exception->" + e.toString());
    	} finally {
    		if(rs!=null)
    			rs.close();
    		if(pstmt!=null)
    			pstmt.close();
    		if(connection!=null)
    			connection.close();
    		sql=null;
    		ctDTO=null;
    		
    	}
    	return contentAL;
    }
    
	public static ArrayList subLevelCategoryDetail(String categoryTemplate, int categoryId, int categoryCountToFetch, String categoryIdToAvoid) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		ContentTemplateDTO aDTO = null;	
		ArrayList dataList = null;
		String sql = "";
		String sql1 = "";
		String categoryCheck = "";
		
		if(categoryTemplate.equals("section") || categoryTemplate.equals("category") || categoryTemplate.equals("subcategory") || categoryTemplate.equals("subsubcategory")) {
			//System.out.println("1. ->"+categoryCountToFetch+"--3. ->"+categoryId+"--4. ->"+categoryIdToAvoid+"--5. ->"+categoryTemplate);
			if(categoryIdToAvoid.trim().length() > 0 && !categoryIdToAvoid.trim().equals("0")) {
				if(categoryIdToAvoid.indexOf(",") < 0)
					categoryCheck = " and a.id != "+categoryIdToAvoid;
				else if(categoryIdToAvoid.indexOf(",") > 0)
					categoryCheck = " and a.id not in ("+categoryIdToAvoid+")";
			}
			try {
				Dbconnection connect = null;
				connect = Dbconnection.getInstance();
				connection = connect.getConnection();
				sql = "select a.id from ";
				if(categoryTemplate.equals("section"))
					sql = sql + "jos_categories a, jos_sections b WHERE b.id="+categoryId+" and b.published=1 and a.section=b.id and a.published=1 ";
				else if(categoryTemplate.equals("category")) 
					sql = sql + "jos_subcategories a, jos_categories b WHERE b.id="+categoryId+" and b.published=1 and b.id=a.category and a.published=1 ";
				else if(categoryTemplate.equals("subcategory")) 
					sql = sql + "jos_sub_subcategories a, jos_subcategories b WHERE b.id="+categoryId+" and b.published=1 and b.id=a.sub_category and a.published=1 ";
				sql = sql + categoryCheck + " GROUP BY a.id order by a.ordering desc";
				if(categoryCountToFetch > 0)
					sql = sql + " limit "+categoryCountToFetch;	
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				//System.out.println("subLevelCategoryDetail ---->"+sql);
				dataList = new ArrayList();
				
				while (rs.next()) {
					sql1 = "SELECT b.id, b.title, b.sef_url, b.template_path, b.content_url from jos_content c, jos_article_section a, ";
					if(categoryTemplate.equals("section"))
						sql1 = sql1 + "jos_categories b  WHERE c.id=a.article_id and c.state=1 and a.cat_id = "+rs.getInt("id")+" and a.cat_id=b.id ";
					else if(categoryTemplate.equals("category")) 
						sql1 = sql1 + "jos_subcategories b  WHERE c.id=a.article_id and c.state=1 and a.subcat_id = "+rs.getInt("id")+" and a.subcat_id=b.id ";
					else if(categoryTemplate.equals("subcategory")) 
						sql1 = sql1 + "jos_sub_subcategories b  WHERE c.id=a.article_id and c.state=1 and a.sub_subcat_id = "+rs.getInt("id")+" and a.sub_subcat_id=b.id ";
					sql1 = sql1 + " order by b.ordering limit 1";
					pstmt1 = connection.prepareStatement(sql1);
					rs1 = pstmt1.executeQuery();
					//System.out.println("subLevelCategoryDetail ---->"+sql);
					while (rs1.next()) {
						aDTO = new ContentTemplateDTO();
						aDTO.setCurrentCategoryId(rs1.getInt("id"));		
						aDTO.setCurrentCategoryTitle(rs1.getString("title"));		
		                if(rs1.getString("content_url") != null && rs1.getString("content_url").trim().length() > 0) {
		                	aDTO.setCurrentCategoryURL(rs1.getString("content_url"));
		                } else {
		                    if(categoryTemplate.equals("section")) {
			                	if(rs1.getString("sef_url").trim().length() > 0) { 
			                		aDTO.setCurrentCategoryURL("section/" + rs1.getInt("id") + "/1/" + rs1.getString("sef_url").replaceAll(".html", "") + ".html");
			                	} else {
			                		aDTO.setCurrentCategoryURL("section/" + rs1.getInt("id") + "/1/" + rs1.getString("sef_url") + ".html");
			                	}
		                    }
		                    if(categoryTemplate.equals("category")) {
			                	if(rs1.getString("sef_url").trim().length() > 0) { 
			                		aDTO.setCurrentCategoryURL("category/" + rs1.getString("sef_url").replaceAll(".html", "") + "/1/" + rs1.getInt("id") + ".html");
			                	} else {
			                		aDTO.setCurrentCategoryURL("category/" + rs1.getString("sef_url") + "/1/" + rs1.getInt("id") + ".html");
			                	}
		                    }
		                    if(categoryTemplate.equals("subcategory")) {
			                	if(rs1.getString("sef_url").trim().length() > 0) { 
			                		aDTO.setCurrentCategoryURL("subcategory/" + rs1.getString("sef_url").replaceAll(".html", "") + "/1/" + rs1.getInt("id") + ".html");
			                	} else {
			                		aDTO.setCurrentCategoryURL("subcategory/" + rs1.getString("sef_url") + "/1/" + rs1.getInt("id") + ".html");
			                	}
		                    }
		                    if(categoryTemplate.equals("subsubcategory")) {
			                	if(rs1.getString("sef_url").trim().length() > 0) { 
			                		aDTO.setCurrentCategoryURL("subsubcategory/" + rs1.getString("sef_url").replaceAll(".html", "") + "/1/" + rs1.getInt("id") + ".html");
			                	} else {
			                		aDTO.setCurrentCategoryURL("subsubcategory/" + rs1.getString("sef_url") + "/1/" + rs1.getInt("id") + ".html");
			                	}	                    
			                }
		                }
						dataList.add(aDTO);
					}
				}
			} catch (Exception e) {
				System.out.println("ContentTemplateHelper subLevelCategoryDetail Exception is :" + e);
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
				sql=null;
				sql1=null;
				aDTO=null;


			}
		}
		return dataList;
	}
}