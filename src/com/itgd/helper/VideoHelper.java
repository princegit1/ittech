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
import com.itgd.dto.VideoDTO;
import com.itgd.utils.Constants;
import com.itgd.dto.LatestContentDTO;
public class VideoHelper {
	
	public static void main(String[] args) {
		VideoHelper vv = new VideoHelper();
		try {
			vv.videoDetail(400016,1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList videoDetail(int videoId, int publishedStatus) throws Exception
	{
		//System.out.println("videoId-1-"+videoId);
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		VideoDTO vDTO = null;	
		ArrayList videoList = new ArrayList();
		String videoStatus = "";
		if(publishedStatus==1)
			videoStatus = " and c.state=1 ";
		
	    int primaryCategoryLength = 0;
	    String primaryCategory = "86";
	    String primaryCategoryId[] = null;
		PreparedStatement pstmt_com = null;
		ResultSet rs_com = null;
		int commentCount=0;
		
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT v.video_duration, c.id, c.primary_category, c.title, c.title_alias, c.sef_url, c.byline, c.city, c.courtesy, c.introtext, c.kicker_image, " +
					"c.extralarge_image,c.sectionid, c.catid, date_format(c.created,'%M %e, %Y' ) AS crt, date_format( c.created,'%Y-%m-%d' ) AS snd,date_format(c.created,'%Y-%m-%dT%H:%m:%S-05:30')AS created, c.strap_headline, c.metakey,c.metadesc, c.mp4_flag, c.large_kicker_image, " +
					"v.VideoGallery_foldername,v.external_url,v.VideoGallery_filename " +
					"FROM jos_content c,jos_videogallerynames v, jos_article_section a " +
					"where c.id=? and c.id=a.article_id and c.id=v.contentid"+videoStatus+" group by c.id";
	    	//System.out.println("Article Detail ->"+sql);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, videoId);
	    	rs = pstmt.executeQuery ();
			while (rs.next ()){
		        vDTO = new VideoDTO();
		        vDTO.setVideoId(rs.getInt("id"));
		        vDTO.setVideoTitle(rs.getString("title"));
		        vDTO.setVideoTitleAlias(rs.getString("title_alias"));		       
		        vDTO.setVideoByline(rs.getString("byline")); 
		        vDTO.setVideoCity(rs.getString("city"));
		        vDTO.setVideoDescription(rs.getString("introtext"));
		        vDTO.setVideoStrapHeadline(rs.getString("strap_headline"));
		        vDTO.setVideoSefTitle(rs.getString("sef_url"));
		        vDTO.setVideoCreatedDate(rs.getString("crt") == null ? "" : rs.getString("crt"));
		        vDTO.setVideoCreatedDateYYYMMDD(rs.getString("snd") == null ? "" : rs.getString("snd"));
		        vDTO.setMetaKeywords(rs.getString("metakey"));
		        vDTO.setMetaDescription(rs.getString("metadesc"));
		        vDTO.setVideoCourtesy(rs.getString("courtesy") == null ? "" : rs.getString("courtesy"));
	        	vDTO.setPrimaryCategory(rs.getString("primary_category") == null ? "0" : rs.getString("primary_category"));
		        primaryCategory = rs.getString("primary_category");
		        vDTO.setVideoFile(rs.getString("VideoGallery_filename") == null ? "" : rs.getString("VideoGallery_filename"));
		        vDTO.setVideoFolder(rs.getString("VideoGallery_foldername") == null ? "" : rs.getString("VideoGallery_foldername"));
		        vDTO.setVideoKickerImage(rs.getString("kicker_image")==null?"" : rs.getString("kicker_image"));
		        vDTO.setExtraLargeKickerImage(rs.getString("extralarge_image")==null?"" : rs.getString("extralarge_image"));
		        vDTO.setExternalVideoUrl(rs.getString("external_url") == null ? "" : rs.getString("external_url"));
		        vDTO.setMp4VideoFlag(rs.getInt("mp4_flag"));
		        vDTO.setVideoLargeKickerImage(rs.getString("large_kicker_image")==null?"" : rs.getString("large_kicker_image"));
		        vDTO.setVideoDuration(rs.getString("video_duration")==null?"" : rs.getString("video_duration"));
		        vDTO.setVideoUpdatedDate(rs.getString("created")==null?"" : rs.getString("created"));
		        
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
				    	//System.out.println("@@@-->"+primaryCategoryLength);
				    	String sql1 = "select js.id as jsid, js.title as jstitle, js.sef_url as jssefurl, js.template_path as jspath, ";
				    	if(primaryCategoryLength==1) {
							sql1 = sql1 + " js.topnav_path, js.rightnav_path, js.bottomnav_path, js.leftnav_path " +
									"from jos_sections js where js.id="+primaryCategoryId[0];
				    	}
				    	if(primaryCategoryLength==2) {
							sql1 = sql1 + " jc.title as jctitle, jc.id as jcid, jc.sef_url as jcsefurl, jc.topnav_path, jc.rightnav_path, jc.bottomnav_path, jc.leftnav_path, jc.template_path as jcpath, jc.header_image " +
									"from jos_categories jc, jos_sections js where jc.id="+primaryCategoryId[1]+" and jc.section=js.id";
				    	}
				    	//System.out.println("Article Primary Detail ->"+sql1);
	
				    	pstmt1 = connection.prepareStatement(sql1);
				    	rs1 = pstmt1.executeQuery();
						while (rs1.next ()){
							//System.out.println("primaryCategoryLength ->"+primaryCategoryLength);
							vDTO.setVideoSectionId(rs1.getInt("jsid"));
				        	vDTO.setVideoSectionTitle(rs1.getString("jstitle"));
				        	vDTO.setVideoSectionSefTitle(rs1.getString("jssefurl"));
							vDTO.setVideoSectionPageURL(rs1.getString("jspath") == null ? "" : rs1.getString("jspath"));
				        	if(primaryCategoryLength >= 2) {
					        	vDTO.setVideoCategoryId(rs1.getInt("jcid"));
					        	vDTO.setVideoCategoryTitle(rs1.getString("jctitle"));
					        	vDTO.setVideoCategorySefTitle(rs1.getString("jcsefurl"));
								vDTO.setVideoCategoryPageURL(rs1.getString("jcpath") == null ? "" : rs1.getString("jcpath"));		
								vDTO.setHeaderImage(rs1.getString("header_image") == null ? "" : rs1.getString("header_image"));
					    	} 
				        	
				        	vDTO.setTopNavigationPath(rs1.getString("topnav_path") == null ? "" : rs1.getString("topnav_path"));
				        	vDTO.setRightNavigationPath(rs1.getString("rightnav_path") == null ? "" : rs1.getString("rightnav_path"));
				        	vDTO.setBottomNavigationPath(rs1.getString("bottomnav_path") == null ? "" : rs1.getString("bottomnav_path"));
				        	vDTO.setLeftNavigationPath(rs1.getString("leftnav_path") == null ? "" : rs1.getString("leftnav_path"));
						}
			        }
				} catch (Exception e) {
					System.out.println("VideoHelper videoDetail Primary Category Detail Exception is :" + e.toString());
				}
		        
		        vDTO.setPrimaryCategoryLength(primaryCategoryLength); 
		        
				try {
					sql = "SELECT count(id) as ccount FROM jos_comments where article_id ="+rs.getInt("id")+" and state=1 and content_type='video'";
					pstmt_com = connection.prepareStatement(sql);
					rs_com = pstmt_com.executeQuery();
					while (rs_com.next()) {
						commentCount = rs_com.getInt("ccount");				
					}
				}catch(Exception c){
					System.out.println("VideoHelper topstories-comment Exception is :" + c);
				}
				vDTO.setVideoCommentCount(commentCount);

		        videoList.add(vDTO);
			}
		} catch (Exception e) {
			System.out.println("VideoHelper videoDetail Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(rs1!=null)
				rs1.close();
			if(pstmt1!=null)
				pstmt1.close();
			if(rs_com!=null)
				rs_com.close();
			if(pstmt_com!=null)
				pstmt_com.close();
			if(connection!=null)
				connection.close();
		}
		return videoList;

	}

/*    public static ArrayList filenames(String name) {
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
*/
    public static ArrayList videoFileNameAL(String name) {
        ArrayList videofiles = new ArrayList();
        if(name.indexOf('|') == 0) {
        	name = name.substring(1, name.length());
        }
        if(name.lastIndexOf('|') == name.length()-1) {
        	name = name.substring(0, name.length()-1);
        }
        String[] tokens = null;
        if(!name.equals("")&&name.contains("|")){
            tokens = name.split("\\|");
            for (int i = 0; i < tokens.length; i++) {
            	if(tokens[i].trim().length() > 0)
            		videofiles.add(tokens[i]);
            }
        } else {
        	videofiles.add(name);
        }
        return videofiles;
    }

    public static ArrayList videoCategories(int countToFetch, String idsToAvoid) throws Exception {
        ArrayList videoCategories = new ArrayList();
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VideoDTO vcDTO = null;
        String contentLimitClause = "";
        String idsToAvoidClause = "";
        if(countToFetch != 0)
        	contentLimitClause = " limit "+countToFetch;
        if(!idsToAvoid.equals("0")) {
        	if(idsToAvoid.indexOf(",") > 0)
        		idsToAvoidClause = " and cat.id not in ("+idsToAvoid+") ";
        	else
        		idsToAvoidClause = " and cat.id !="+idsToAvoid+" ";
        }

        try {
            Dbconnection connect = null;
            connect = Dbconnection.getInstance();
            connection = connect.getConnection();
            String sql = "SELECT cat.id, cat.title, cat.sef_url, cat.ordering FROM jos_sections s, jos_categories cat " +
            		"where cat.section="+Constants.VIDEO_SECTIONID+" and cat.section=s.id and s.published=1" +
            				" and cat.published=1 "+idsToAvoidClause+" group by cat.id order by cat.ordering desc "+contentLimitClause;
            //String sql = "SELECT cat.id, cat.title, cat.sef_url FROM jos_content c, jos_article_section a, " +
            //		"jos_sections s, jos_categories cat, jos_videogallerynames v where c.id=a.article_id and " +
            //		"c.state=1 and a.section_id="+Constants.VIDEO_SECTIONID+" and cat.id=a.cat_id and s.id=a.section_id and s.published=1 " +
            //		"and cat.section=s.id and cat.published=1 and c.id=v.contentid "+idsToAvoidClause+" group by a.cat_id order by cat.ordering "+contentLimitClause;

            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //System.out.print("videoCategories->"+sql);

            while (rs.next()) {
                vcDTO = new VideoDTO();
                vcDTO.setVideoCategoryId(rs.getInt("id"));
                vcDTO.setVideoCategoryTitle(rs.getString("title"));
                vcDTO.setVideoCategorySefTitle(rs.getString("sef_url"));
                videoCategories.add(vcDTO);
            }
        } catch (Exception e) {
            System.out.println("VideoHelper videoCategories Exception is ;" + e);
        } finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
        }
        return videoCategories;
    }
    
    public static ArrayList latestVideo(int currentPageNum, int countToFetch, String videoIdToAvoid, int categoryId) throws Exception {
        ArrayList videoList = new ArrayList();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VideoDTO vlDTO = null;
        
		ResultSet rs_pc = null;
		String videoCheck = "";
		String categoryCheck = "";
		int videoCount = 0;
		int vStartLimit = 0;
		String sql = "";
		vStartLimit = (currentPageNum-1)*countToFetch;
		int vEndLimit = countToFetch;
		String videoCutoffCheck = "";
		if(categoryId == 0) {
			videoCutoffCheck = " and c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+Constants.VIDEO_CUTOFF_DAYS_SECTION+" DAY) ";
		}
		if(categoryId == 42) {
			videoCutoffCheck = " and c.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+Constants.VIDEO_CUTOFF_DAYS+" DAY) ";
		}
		
		if(!videoIdToAvoid.equals("0")) {
			if(videoIdToAvoid.indexOf(",") > 0)
				videoCheck = " and c.id not in ("+videoIdToAvoid+") ";
			else
				videoCheck = " and c.id != "+videoIdToAvoid + " ";
		}

		if(categoryId != 0)
			categoryCheck = " and a.cat_id = "+categoryId;

        try {
            Dbconnection connect = null;
            connect = Dbconnection.getInstance();
            connection = connect.getConnection();
            
			if(categoryId>0) {
				sql = "SELECT count(distinct c.id) as videoCount FROM jos_content c, jos_article_section a, jos_categories cat, " +
						"jos_sections s, jos_videogallerynames v where c.id=a.article_id and a.cat_id="+categoryId+" and c.id=v.contentid " +
						"and c.state=1 and a.cat_id=cat.id and cat.published=1 and cat.section=s.id and s.published=1";
				sql = sql + videoCutoffCheck;
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					videoCount = rs.getInt("videoCount");
				}
			}		
			
			sql="";
			pstmt=null;
			rs=null;

			if(categoryId>0) {
				sql = "SELECT c.id, c.title, c.sef_url, c.introtext, c.kicker_image,c.large_kicker_image, c.kicker_image_alt_text, date_format(c.created,'%a, %e %b, %Y' ) AS createddate, v.VideoGallery_filename,v.external_url" +
            		" ,cat.id as catid, cat.title as cattitle, cat.sef_url as catsefurl,cat.metakey, cat.metadesc, cat.metatitle, cat.topnav_path" +
            		", cat.rightnav_path, cat.bottomnav_path, cat.leftnav_path, cat.template_path, cat.header_image " +
            		"FROM jos_content c, jos_article_section a, jos_categories cat, jos_sections s, jos_videogallerynames v " +
            		"where c.id=a.article_id and a.cat_id="+categoryId+" and c.id=v.contentid and c.state=1 and a.cat_id=cat.id " +
            		"and s.published=1 and cat.published=1 and cat.section=s.id "+videoCheck+videoCutoffCheck+" group by c.id order by a.ordering desc, a.article_id desc limit "+vStartLimit + ", "+vEndLimit;
			} else {
				sql = "SELECT c.id, c.title, c.sef_url, c.introtext,c.large_kicker_image, c.kicker_image, c.kicker_image_alt_text, date_format(c.created,'%a, %e %b, %Y' ) AS createddate, " +
						"v.VideoGallery_filename,v.external_url, s.id as catid, s.title as cattitle, s.sef_url as catsefurl, s.metakey, " +
						"s.metadesc, s.metatitle FROM jos_content c, jos_article_section a, jos_sections s, " +
						"jos_videogallerynames v where c.id=a.article_id and a.section_id="+Constants.VIDEO_SECTIONID+" and c.id=v.contentid " +
						"and c.state=1 and a.section_id=s.id and s.published=1 "+videoCheck+videoCutoffCheck+" group by c.id order by a.ordering desc, a.article_id desc limit "+vStartLimit + ", "+vEndLimit;
			}
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //System.out.print("video list ----->" + sql);

            while (rs.next()) {
            	vlDTO = new VideoDTO();
            	vlDTO.setVideoId(rs.getInt("id"));
            	vlDTO.setVideoKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
            	vlDTO.setVideoLargeKickerImage(rs.getString("large_kicker_image")==null?"":rs.getString("large_kicker_image"));            	
            	vlDTO.setVideoKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
            	vlDTO.setVideoDescription(rs.getString("introtext"));
            	vlDTO.setVideoTitle(rs.getString("title"));
            	vlDTO.setVideoCount(videoCount);
                vlDTO.setVideoFile(rs.getString("VideoGallery_filename")==null?"":rs.getString("VideoGallery_filename"));
                vlDTO.setVideoSefTitle(rs.getString("sef_url"));
                vlDTO.setExternalVideoUrl(rs.getString("external_url") == null ? "" : rs.getString("external_url"));

                vlDTO.setVideoCategoryId(rs.getInt("catid"));
                vlDTO.setVideoCategoryTitle(rs.getString("cattitle"));
                vlDTO.setVideoCategorySefTitle(rs.getString("catsefurl"));
                vlDTO.setMetaTitle(rs.getString("metatitle") == null ? "" : rs.getString("metatitle"));
                vlDTO.setMetaKeywords(rs.getString("metakey") == null ? "" : rs.getString("metakey"));
                vlDTO.setMetaDescription(rs.getString("metadesc") == null ? "" : rs.getString("metadesc"));
                if(categoryId>0) {
	                vlDTO.setTopNavigationPath(rs.getString("topnav_path") == null ? "" : rs.getString("topnav_path"));		
	                vlDTO.setRightNavigationPath(rs.getString("rightnav_path") == null ? "" : rs.getString("rightnav_path"));		
	                vlDTO.setBottomNavigationPath(rs.getString("bottomnav_path") == null ? "" : rs.getString("bottomnav_path"));		
	                vlDTO.setLeftNavigationPath(rs.getString("leftnav_path") == null ? "" : rs.getString("leftnav_path"));		
	                vlDTO.setHeaderImage(rs.getString("header_image") == null ? "" : rs.getString("header_image"));		
                }
		        vlDTO.setVideoCreatedDate(rs.getString("createddate") == null ? "" : rs.getString("createddate"));
                videoList.add(vlDTO);
            }

        } catch (Exception e) {
            System.out.println("VideoHelper videoList Exception is ;" + e);
        } finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
        }

        return videoList;
    }

	public static ArrayList videoDetailToRedirect(int videoId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VideoDTO vDTO = null;	
		ArrayList videoList = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT c.id, c.sef_url FROM jos_content c WHERE c.id = ?";
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, videoId);
	    	rs = pstmt.executeQuery();
			while (rs.next ()){
		        vDTO = new VideoDTO();
		        vDTO.setVideoId(rs.getInt("id"));
		        vDTO.setVideoSefTitle(rs.getString("sef_url"));
		        videoList.add(vDTO);
			}
		} catch (Exception e) {
			System.out.println("VideoHelper videoDetailToRedirect Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return videoList;
	}

	public static ArrayList videoListDetailToRedirect(int videoId) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		VideoDTO vDTO = null;	
		ArrayList videoList = new ArrayList();
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
	    	String sql = "SELECT c.id, c.sef_url FROM jos_categories c WHERE c.id= ?";
	    	//System.out.println("videoListDetaild sql->"+sql + "----videoId-."+videoId);
	    	pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, videoId);
	    	rs = pstmt.executeQuery();
			while (rs.next ()){
		        vDTO = new VideoDTO();
		        vDTO.setVideoCategoryId(rs.getInt("id"));
		        vDTO.setVideoCategorySefTitle(rs.getString("sef_url"));
		        videoList.add(vDTO);
			}
		} catch (Exception e) {
			System.out.println("VideoHelper videoListDetailToRedirect Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return videoList;
	}

    public static ArrayList latestVideoPlayPageChunk(int currentPageNum, int countToFetch, String videoIdToAvoid, int categoryId) throws Exception {
        ArrayList videoList = new ArrayList();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VideoDTO vlDTO = null;
        
		ResultSet rs_pc = null;
		String videoCheck = "";
		String categoryCheck = "";
		int videoCount = 0;
		int vStartLimit = 0;
		String sql = "";
		vStartLimit = (currentPageNum-1)*countToFetch;
		int vEndLimit = countToFetch;
		
		if(!videoIdToAvoid.equals("0")) {
			if(videoIdToAvoid.indexOf(",") > 0)
				videoCheck = " and c.id not in ("+videoIdToAvoid+") ";
			else
				videoCheck = " and c.id != "+videoIdToAvoid + " ";
		}

		if(categoryId != 0)
			categoryCheck = " and a.cat_id = "+categoryId;

        try {
            Dbconnection connect = null;
            connect = Dbconnection.getInstance();
            connection = connect.getConnection();
            
			if(categoryId>0) {
				sql = "SELECT c.id, c.title, c.sef_url, c.introtext, c.kicker_image, c.kicker_image_alt_text, v.VideoGallery_filename,v.external_url" +
            		" FROM jos_content c, jos_article_section a, jos_videogallerynames v where c.id=a.article_id and " +
            		"a.cat_id="+categoryId+" and c.id=v.contentid and c.state=1 "+videoCheck+" group by c.id order by a.ordering desc, a.article_id desc limit "+vStartLimit + ", "+vEndLimit;
			} else {
				sql = "SELECT c.id, c.title, c.sef_url, c.introtext, c.kicker_image, c.kicker_image_alt_text, v.VideoGallery_filename,v.external_url" +
						" FROM jos_content c, jos_article_section a, jos_sections s, jos_videogallerynames v " +
						"where c.id=a.article_id and a.section_id="+Constants.VIDEO_SECTIONID+" and c.id=v.contentid " +
						"and c.state=1 and a.section_id=s.id and s.published=1 "+videoCheck+" group by c.id order by a.ordering desc, a.article_id desc limit "+vStartLimit + ", "+vEndLimit;
			}
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //System.out.print("video list ----->" + sql);

            while (rs.next()) {
            	vlDTO = new VideoDTO();
            	vlDTO.setVideoId(rs.getInt("id"));
            	vlDTO.setVideoKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
            	vlDTO.setVideoKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
            	vlDTO.setVideoDescription(rs.getString("introtext"));
            	vlDTO.setVideoTitle(rs.getString("title"));
                vlDTO.setVideoFile(rs.getString("VideoGallery_filename")==null?"":rs.getString("VideoGallery_filename"));
                vlDTO.setVideoSefTitle(rs.getString("sef_url"));
                vlDTO.setExternalVideoUrl(rs.getString("external_url") == null ? "" : rs.getString("external_url"));

                videoList.add(vlDTO);
            }
        } catch (Exception e) {
            System.out.println("VideoHelper latest videp play chunk videoList Exception is ;" + e);
        } finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
        }

        return videoList;
    }

    public static ArrayList latestVideoPlayPageChunkWOGB(int currentPageNum, int countToFetch, String videoIdToAvoid, int categoryId) throws Exception {
        ArrayList videoList = new ArrayList();

        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        VideoDTO vlDTO = null;
        
		ResultSet rs_pc = null;
		String videoCheck = "";
		String categoryCheck = "";
		int videoCount = 0;
		int vStartLimit = 0;
		String sql = "";
		vStartLimit = (currentPageNum-1)*countToFetch;
		int vEndLimit = countToFetch;
		
		if(!videoIdToAvoid.equals("0")) {
			if(videoIdToAvoid.indexOf(",") > 0)
				videoCheck = " and c.id not in ("+videoIdToAvoid+") ";
			else
				videoCheck = " and c.id != "+videoIdToAvoid + " ";
		}

		if(categoryId != 0)
			categoryCheck = " and a.cat_id = "+categoryId;

        try {
            Dbconnection connect = null;
            connect = Dbconnection.getInstance();
            connection = connect.getConnection();
            
			if(categoryId>0) {
				sql = "SELECT c.id,date_format(c.created,'%a, %e %b %Y' ) AS createddate, c.title, c.sef_url, c.introtext, c.kicker_image, c.kicker_image_alt_text, v.VideoGallery_filename,v.external_url" +
            		" FROM jos_content c, jos_article_section a, jos_videogallerynames v where c.id=a.article_id and " +
            		"a.cat_id="+categoryId+" and c.id=v.contentid and c.state=1 "+videoCheck+" order by a.ordering desc, a.article_id desc limit "+vStartLimit + ", "+vEndLimit;
			} else {
				sql = "SELECT c.id, date_format(c.created,'%a, %e %b %Y' ) AS createddate,c.title, c.sef_url, c.introtext, c.kicker_image, c.kicker_image_alt_text, v.VideoGallery_filename,v.external_url" +
						" FROM jos_content c, jos_article_section a, jos_sections s, jos_videogallerynames v " +
						"where c.id=a.article_id and a.section_id="+Constants.VIDEO_SECTIONID+" and c.id=v.contentid " +
						"and c.state=1 and a.section_id=s.id and s.published=1 "+videoCheck+" order by a.ordering desc, a.article_id desc limit "+vStartLimit + ", "+vEndLimit;
			}
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
            //System.out.print("video list ----->" + sql);

            while (rs.next()) {
            	vlDTO = new VideoDTO();
            	vlDTO.setVideoId(rs.getInt("id"));
            	vlDTO.setVideoKickerImage(rs.getString("kicker_image")==null?"":rs.getString("kicker_image"));
            	vlDTO.setVideoKickerImageAltText(rs.getString("kicker_image_alt_text")==null?"":rs.getString("kicker_image_alt_text"));
            	vlDTO.setVideoDescription(rs.getString("introtext"));
            	vlDTO.setVideoTitle(rs.getString("title"));
                vlDTO.setVideoFile(rs.getString("VideoGallery_filename")==null?"":rs.getString("VideoGallery_filename"));
                vlDTO.setVideoSefTitle(rs.getString("sef_url"));
                vlDTO.setExternalVideoUrl(rs.getString("external_url") == null ? "" : rs.getString("external_url"));
                vlDTO.setVideoCreatedDate(rs.getString("createddate"));

                videoList.add(vlDTO);
            }
        } catch (Exception e) {
            System.out.println("VideoHelper latest videp play chunk videoList Exception is ;" + e);
        } finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
        }

        return videoList;
    }
    
	public static ArrayList subLevelCategoryDetail(int countToFetch, String categoryIdToAvoid) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		LatestContentDTO aDTO = null;	
		ArrayList dataList = new ArrayList();
		String sql = "";
		String sql1 = "";
		String categoryCheck = "";
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

			sql = "select a.id from jos_categories a, jos_sections b WHERE b.id="+Constants.VIDEO_SECTIONID+" and b.published=1 and a.section=b.id and a.published=1 ";
			sql = sql + categoryCheck + " GROUP BY a.id order by a.ordering desc";
			if(countToFetch > 0)
				sql = sql + " limit "+countToFetch;	
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println("subLevelCategoryDetail ---->"+sql);
			
			while (rs.next()) {
				sql1 = "SELECT b.id, b.title, b.sef_url, b.template_path from jos_content c, jos_article_section a, " +
						"jos_categories b  WHERE c.id=a.article_id and c.state=1 and a.cat_id = "+rs.getInt("id")+" and a.cat_id=b.id ";
				sql1 = sql1 + " order by b.ordering limit 1";
				pstmt1 = connection.prepareStatement(sql1);
				rs1 = pstmt1.executeQuery();
				//System.out.println("Sanjay subLevelCategoryDetail ---->"+sql);
				while (rs1.next()) {
					aDTO = new LatestContentDTO();
					aDTO.setCurrentCategoryId(rs1.getInt("id"));		
					aDTO.setCurrentCategoryTitle(rs1.getString("title"));		
					aDTO.setCurrentCategorySefTitle(rs1.getString("sef_url") == null ? "" : rs1.getString("sef_url"));		
					aDTO.setCurrentCategoryPageURL(rs1.getString("template_path") == null ? "" : rs1.getString("template_path"));		
					dataList.add(aDTO);
				}
			}
		} catch (Exception e) {
			System.out.println("VideoHelper subLevelCategoryDetail Exception is :" + e);
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

}