package com.itgd.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.itgd.conn.Dbconnection;
import com.itgd.dto.GalleryDTO;


public class GalleryHelper {
	
	public static void main(String[] args) {
		GalleryHelper cth = new GalleryHelper();
		try {
			//cth.latestGallery(0, 7, "0");
			//cth.latestVideo(0, 9, "");
			//latestStory("section", 114, 10, "0");
			String galleryIdToAvoid="0";
			int galleryCountDisplay = 45;
			int currentPageNo = 1;
			
			//cth.latestGallery(currentPageNo, galleryCountDisplay, "0", 131);
			
			int galleryId = 16736;
			int currentPhotoNo = 1;
			cth.photoDetails(galleryId, currentPhotoNo);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList photoDetails(int galleryId, int currentPhotoNo) throws Exception 
	{
		ArrayList photoList =new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;

		GalleryDTO photoDetails = null;
		int galleryPhotoCount = 0;
		int galleryCommentCount = 0;
	    int primaryCategoryLength = 0;
	    String primaryCategory = "0";
	    String primaryCategoryId[] = null;

	
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "";
			String sql1 = "";

			sql = "SELECT count(distinct pg.id) as photoCount FROM jos_photogallery pg, jos_gallerynames gn,jos_photocategories pc " +
	 		"where gn.id=? and gn.photo_category=pc.id and pg.gallery_id=gn.id and pg.enable=1 and pc.published=1 and " +
	 		"gn.published=1"; 
			pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, galleryId);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				galleryPhotoCount = rs.getInt("photoCount");
			}
			sql = "";
			pstmt = null;
			rs = null;

			sql = "SELECT count(id) as commentCount FROM jos_comments where galleryid=? and state=1"; 
			pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, galleryId);
			System.out.println("Photo Comment Count->"+sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				galleryCommentCount = rs.getInt("commentCount");
			}
			sql = "";
			pstmt = null;
			rs = null;

			sql = "SELECT gn.id,gn.Gallery_name,gn.strap_headline,gn.Gallery_caption,gn.sef_url as gallery_sefurl, gn.photo_category,gn.metakey as gallerykeywords,gn.metadesc as gallerydesc, gn.primary_category, " +
			 		"pg.id as photoid ,pg.photo_name,pg.strap_headline as imagestrap,pg.photo_caption,pg.photo_title,pg.photo_name_width, pg.photo_name_height, pg.image_alt_text, " +
			 		"pg.image_metakey,pg.image_title,pc.title as catname FROM jos_photogallery pg, jos_gallerynames gn,jos_photocategories pc " +
			 		"where gn.id=? and gn.photo_category=pc.id and pg.gallery_id=gn.id and pg.enable=1 and pc.published=1 and " +
			 		"gn.published=1 group by pg.id order by pg.ordering desc, pg.id desc limit ?, ?"; 
			pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, galleryId);
            pstmt.setInt(2, currentPhotoNo-1);
            pstmt.setInt(3, 1);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				photoDetails = new GalleryDTO();
				
				photoDetails.setPhotoId(rs.getInt("photoid"));
				photoDetails.setPhotoTitle(rs.getString("photo_name"));
				photoDetails.setStrapHeadline(rs.getString("strap_headline")==null?"":rs.getString("strap_headline"));
				photoDetails.setPhotoAltText(rs.getString("image_alt_text")==null?"":rs.getString("image_alt_text"));
				photoDetails.setPhotoCaption(rs.getString("photo_title")==null?"":rs.getString("photo_title"));
				photoDetails.setPhotoMetaKeywords(rs.getString("image_metakey")==null?"":rs.getString("image_metakey"));
				photoDetails.setPhotoSource(rs.getString("photo_caption")==null?"":rs.getString("photo_caption"));
				photoDetails.setImageStrapHeadline(rs.getString("imagestrap")==null?"":rs.getString("imagestrap"));
				photoDetails.setPhotoWidth(rs.getInt("photo_name_width"));
				photoDetails.setPhotoHeight(rs.getInt("photo_name_height"));
				
				photoDetails.setGalleryId(rs.getInt("id"));				
				photoDetails.setGalleryTitle(rs.getString("Gallery_name"));
				photoDetails.setGalleryTitleAlias(rs.getString("Gallery_caption"));
				photoDetails.setGallerySefTitle(rs.getString("gallery_sefurl"));
				photoDetails.setMetaKeywords(rs.getString("gallerykeywords")==null?"":rs.getString("gallerykeywords"));
				photoDetails.setMetaDescription(rs.getString("gallerydesc")==null?"":rs.getString("gallerydesc"));
				
				photoDetails.setPhotoCount(galleryPhotoCount);
				photoDetails.setCurrentPhotoNum(currentPhotoNo);
				photoDetails.setGalleryCommentCount(galleryCommentCount);
				photoDetails.setImageTitle(rs.getString("image_title")==null?"":rs.getString("image_title"));
				
				primaryCategory = rs.getString("primary_category") == null ? "" : rs.getString("primary_category");
		    	try { 
			        if(primaryCategory != null && primaryCategory.trim().length() > 0 ){
					    if((primaryCategory.indexOf("#") > 0) && (primaryCategory.lastIndexOf("#") < primaryCategory.trim().length())) {
					    	primaryCategoryId = primaryCategory.split("#");
					    	primaryCategoryLength = primaryCategoryId.length;
					    	//System.out.println("PCAT length - "+pcatlength);
					    } else {
					    	primaryCategoryId = new String[1];
					    	primaryCategoryId[0] = primaryCategory;
					    }
				    	primaryCategoryLength = primaryCategoryId.length;
				    	if(primaryCategoryLength==2) {
				    		sql1 = "SELECT pc.id, pc.title,pc.sef_url, pc.topnav_path, pc.rightnav_path, pc.bottomnav_path, pc.leftnav_path, pc.header_image " +
				    				"FROM jos_photocategories pc where pc.id="+primaryCategoryId[1]+" and pc.published=1";
				    	}
				    	//System.out.println("Story Primary Detail ->"+sql1);
	
				    	pstmt1 = connection.prepareStatement(sql1);
				    	rs1 = pstmt1.executeQuery();
						while (rs1.next ()){
							//System.out.println("primaryCategoryLength ->"+primaryCategoryLength);
				        	if(primaryCategoryLength >= 2) {
								photoDetails.setCategoryId(rs1.getInt("id"));
								photoDetails.setCategoryTitle(rs1.getString("title"));
								photoDetails.setCategorySefTitle(rs1.getString("sef_url"));
					    	} 
							photoDetails.setTopNavigationPath(rs1.getString("topnav_path") == null ? "" : rs1.getString("topnav_path"));		
							photoDetails.setRightNavigationPath(rs1.getString("rightnav_path") == null ? "" : rs1.getString("rightnav_path"));		
							photoDetails.setBottomNavigationPath(rs1.getString("bottomnav_path") == null ? "" : rs1.getString("bottomnav_path"));		
							photoDetails.setLeftNavigationPath(rs1.getString("leftnav_path") == null ? "" : rs1.getString("leftnav_path"));		
							photoDetails.setHeaderImage(rs1.getString("header_image") == null ? "" : rs1.getString("header_image"));		
						}
			        }
				} catch (Exception e) {
					System.out.println("GalleryHelper photoDetail Primary Category Detail Exception is :" + e.toString());
				}

				photoList.add(photoDetails);
			}
			
		} catch (Exception e) {
			System.out.println("GalleryHelper - photoDetails Exception is -->" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return photoList;
	}
	
	public static ArrayList latestGallery(int currentPageNum, int countToFetch, String galleryIdToAvoid, int categoryId) throws Exception
	{
		ArrayList latestGalleryData = new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_pc = null;
		GalleryDTO galleryDTO = null;
		String galleryCheck = "";
		String categoryCheck = "";
		int galleryCount = 0;
		int gStartLimit = 0;
		String sql = "";
		gStartLimit = (currentPageNum-1)*countToFetch;
		int gEndLimit = countToFetch;
		
		if(!galleryIdToAvoid.equals("0")) {
			if(galleryIdToAvoid.indexOf(",") > 0)
				galleryCheck = "and gn.id not in ("+galleryIdToAvoid+")";
			else
				galleryCheck = "and gn.id != "+galleryIdToAvoid;
		}

		if(categoryId != 0) {
			//categoryCheck = " and gn.photo_category = "+categoryId;
			categoryCheck = " gs.cat_id = "+categoryId + " and ";
		}
			
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			//String sql = "SELECT id as thumbid,Gallery_name,small_image,photo_category,sef_url FROM jos_gallerynames where published=1 and id not in ('"+id+"') order by ordering desc limit 3 ";

			if(categoryId>0) {
				//sql = "SELECT count(id) as galleryCount FROM jos_gallerynames where photo_category = "+categoryId+" and published=1";
				sql = "SELECT count(distinct gs.content_id) as galleryCount FROM jos_gallerynames gn, jos_gallery_section gs where gn.id=gs.content_id and gs.cat_id = "+categoryId+" and published=1 group by gs.cat_id";
				System.out.print("---->"+sql);
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					galleryCount = rs.getInt("galleryCount");
				}
			}		
			
			sql="";
			pstmt=null;
			rs=null;
			/*sql = "SELECT gn.id, gn.Gallery_name, gn.sef_url as gallery_sefurl, gn.small_image, gn.photo_category, pc.title" +
			", pc.sef_url as cat_sefurl, pc.metakey as pcmetakey, pc.metadesc as pcmetadesc, pc.metatitle as pcmetatitle, " +
			"count(pg.id) as photo_count, pc.topnav_path, pc.rightnav_path, pc.bottomnav_path, pc.leftnav_path " +
			"FROM jos_gallerynames gn, jos_photocategories pc, jos_photogallery pg " +
			"where gn.published=1 "+galleryCheck+" and gn.photo_category=pc.id and pc.published=1 and pg.enable=1 and " +
					"pg.gallery_id=gn.id "+categoryCheck+" group by gn.id order by gn.ordering desc limit "+gStartLimit + ", "+gEndLimit;
			*/		
			//sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url as gallery_sefurl, gn.small_image, gn.photo_category, pc.title" +
				//	", pc.sef_url as cat_sefurl, pc.metakey as pcmetakey, pc.metadesc as pcmetadesc, pc.metatitle as pcmetatitle, " +
				//	"pc.topnav_path, pc.rightnav_path, pc.bottomnav_path, pc.leftnav_path " +
				//	"FROM jos_gallerynames gn, jos_photocategories pc " +
				//	"where gn.published=1 "+galleryCheck+" and gn.photo_category=pc.id and " +
				//			"pc.published=1" +categoryCheck+" group by gn.id order by gn.ordering desc limit "+gStartLimit + ", "+gEndLimit;
			sql = "SELECT gn.extralarge_image,gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url as gallery_sefurl, gn.small_image, gn.photo_category, " +
					"pc.title , pc.sef_url as cat_sefurl, pc.metakey as pcmetakey, pc.metadesc as pcmetadesc, " +
					"pc.metatitle as pcmetatitle, pc.topnav_path, pc.rightnav_path, pc.bottomnav_path, pc.leftnav_path, pc.header_image " +
					"FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
					"where " +categoryCheck+" pc.id=gs.cat_id and gn.id=gs.content_id and gn.published=1 "+galleryCheck+" and pc.published=1 " +
					"group by gn.id order by gs.ordering desc limit "+gStartLimit + ", "+gEndLimit;

			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.print(sql);
			sql="";
			pstmt=null;
			while (rs.next()) {
				galleryDTO = new GalleryDTO();
				galleryDTO.setExtralarge_image(rs.getString("extralarge_image") == null ? "" : rs.getString("extralarge_image"));
				galleryDTO.setCategoryId(rs.getInt("photo_category"));
				galleryDTO.setCategoryTitle(rs.getString("title"));
				galleryDTO.setCategorySefTitle(rs.getString("cat_sefurl"));
				galleryDTO.setGalleryThumbPhoto(rs.getString("small_image"));
				galleryDTO.setGalleryTitle(rs.getString("Gallery_name"));
				galleryDTO.setGalleryTitleAlias(rs.getString("Gallery_caption"));
				galleryDTO.setGalleryId(rs.getInt("id"));
				galleryDTO.setGallerySefTitle(rs.getString("gallery_sefurl"));
				galleryDTO.setCategoryMetaTitle(rs.getString("pcmetatitle") == null ? "" : rs.getString("pcmetatitle"));
				galleryDTO.setCategoryMetaKeywords(rs.getString("pcmetakey") == null ? "" : rs.getString("pcmetakey"));
				galleryDTO.setCategoryMetaDescription(rs.getString("pcmetadesc") == null ? "" : rs.getString("pcmetadesc"));
				galleryDTO.setTopNavigationPath(rs.getString("topnav_path") == null ? "" : rs.getString("topnav_path"));		
				galleryDTO.setRightNavigationPath(rs.getString("rightnav_path") == null ? "" : rs.getString("rightnav_path"));		
				galleryDTO.setBottomNavigationPath(rs.getString("bottomnav_path") == null ? "" : rs.getString("bottomnav_path"));		
				galleryDTO.setLeftNavigationPath(rs.getString("leftnav_path") == null ? "" : rs.getString("leftnav_path"));		
				galleryDTO.setHeaderImage(rs.getString("header_image") == null ? "" : rs.getString("header_image"));		

				//galleryDTO.setPhotoCount(rs.getInt("photo_count"));
				try {
					sql = "SELECT count(pg.id) as photo_count FROM jos_photogallery pg where pg.enable=1 and pg.gallery_id="+rs.getInt("id");
					
					System.out.print("Last SQL-->"+sql);
					pstmt = connection.prepareStatement(sql);
					rs_pc = pstmt.executeQuery();
					//System.out.print("PC----"+sql);
					while (rs_pc.next()) {
						galleryDTO.setPhotoCount(rs_pc.getInt("photo_count"));
					}
				} catch (Exception pcEx) {
					System.out.println("latestGallery Photo Gallery Count - "+pcEx.toString());
				} finally {
					sql="";
					pstmt=null;
					rs_pc=null;
				}

				//galleryDTO.setPhotoCount(0);

				galleryDTO.setGalleryCount(galleryCount);
				latestGalleryData.add(galleryDTO);
			}
		} catch (Exception e) {
			System.out.println("GalleryHelper - latestGallery Exception is ;" + e);
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
	
	public static ArrayList latestCategory(int categoryLimit) throws Exception
	{
		ArrayList categoryData = new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GalleryDTO categoryDTO = null;
		
		try {

			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			//String sql = "SELECT id,title,sef_url, metakey, metadesc FROM jos_photocategories where published=1 order by ordering desc limit "+categoryLimit;
			String sql = "select pc.id, pc.title, pc.sef_url, pc.metatitle, pc.metakey, pc.metadesc, count(gn.id) as gallerycount from jos_photocategories pc, jos_gallerynames gn, jos_gallery_section gs where pc.id=gs.cat_id and gn.id=gs.content_id and pc.published=1 and gn.published=1 group by pc.id order by pc.ordering desc limit "+categoryLimit;

			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.print(sql);
			while (rs.next()) {
				categoryDTO = new GalleryDTO();
				categoryDTO.setCategoryId(rs.getInt("id"));
				categoryDTO.setCategoryTitle(rs.getString("title"));			
				categoryDTO.setCategorySefTitle(rs.getString("sef_url"));
				categoryDTO.setCategoryMetaTitle(rs.getString("metatitle") == null ? "" : rs.getString("metatitle"));
				categoryDTO.setCategoryMetaKeywords(rs.getString("metakey") == null ? "" : rs.getString("metakey"));
				categoryDTO.setCategoryMetaDescription(rs.getString("metadesc") == null ? "" : rs.getString("metadesc"));
				categoryDTO.setGalleryCount(rs.getInt("gallerycount"));
				categoryData.add(categoryDTO);
			}
		} catch (Exception e) {
			System.out.println("GalleryHelper - latestCategory Exception is ;" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		
		return categoryData;
	}

	public static ArrayList galleryDetailToRedirect(int galleryId) throws Exception 
	{
		ArrayList photoList =new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GalleryDTO photoDetails = null;
	
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT gn.id,gn.sef_url as gallery_sefurl FROM jos_gallerynames gn where gn.id=?"; 
			pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, galleryId);
			//System.out.println(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				photoDetails = new GalleryDTO();
				photoDetails.setGalleryId(rs.getInt("id"));				
				photoDetails.setGallerySefTitle(rs.getString("gallery_sefurl"));
				photoList.add(photoDetails);
			}
		} catch (Exception e) {
			System.out.println("GalleryHelper - galleryDetailToRedirect Exception is -->" + e.toString());
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return photoList;
	}

	public static ArrayList photogallerySmallImages(int galleryId) throws Exception 
	{
		ArrayList photoList =new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GalleryDTO photoDetails = null;
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "SELECT pg.id as photoid, pg.photo_small_name,pg.image_alt_text, pg.photo_name_width, pg.photo_name_height " +
			 		" FROM jos_photogallery pg where pg.gallery_id=? and pg.enable=1 group by pg.id order by pg.ordering desc, pg.id desc"; 
			pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, galleryId);
			//System.out.println(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				photoDetails = new GalleryDTO();
				photoDetails.setPhotoId(rs.getInt("photoid"));
				photoDetails.setPhotoTitle(rs.getString("photo_small_name")==null?"":rs.getString("photo_small_name"));
				photoDetails.setPhotoAltText(rs.getString("image_alt_text")==null?"":rs.getString("image_alt_text"));
				photoDetails.setPhotoWidth(rs.getInt("photo_name_width"));
				photoDetails.setPhotoHeight(rs.getInt("photo_name_height"));
				photoList.add(photoDetails);
			}
		} catch (Exception e) {
			System.out.println("GalleryHelper - photogalleryDetails Exception is -->" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return photoList;
	}

	public static ArrayList photoDetailsAjax(int galleryId, int currentPhotoNo) throws Exception 
	{
		ArrayList photoList =new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GalleryDTO photoDetails = null;
	
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			String sql = "";

			sql = "SELECT gn.id,gn.Gallery_name,gn.Gallery_caption,gn.sef_url as gallery_sefurl, gn.photo_category,gn.metakey as gallerykeywords,gn.metadesc as gallerydesc, gn.primary_category, " +
			 		"pg.id as photoid ,pg.photo_name,pg.photo_caption,pg.photo_title,pg.photo_name_width, pg.photo_name_height, pg.image_alt_text, " +
			 		"pg.image_metakey,pg.image_title,pc.title as catname FROM jos_photogallery pg, jos_gallerynames gn,jos_photocategories pc " +
			 		"where gn.id=? and gn.photo_category=pc.id and pg.gallery_id=gn.id and pg.enable=1 and pc.published=1 and " +
			 		"gn.published=1 group by pg.id order by pg.ordering desc, pg.id desc limit ?, ?"; 
			pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, galleryId);
            pstmt.setInt(2, currentPhotoNo-1);
            pstmt.setInt(3, 1);
			//System.out.println("photoDetailsAjax->"+sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				photoDetails = new GalleryDTO();
				photoDetails.setPhotoId(rs.getInt("photoid"));
				photoDetails.setPhotoTitle(rs.getString("photo_name"));
				photoDetails.setPhotoAltText(rs.getString("image_alt_text")==null?"":rs.getString("image_alt_text"));
				photoDetails.setPhotoCaption(rs.getString("photo_title")==null?"":rs.getString("photo_title"));
				photoDetails.setPhotoMetaKeywords(rs.getString("image_metakey")==null?"":rs.getString("image_metakey"));
				photoDetails.setPhotoSource(rs.getString("photo_caption")==null?"":rs.getString("photo_caption"));
				photoDetails.setPhotoWidth(rs.getInt("photo_name_width"));
				photoDetails.setPhotoHeight(rs.getInt("photo_name_height"));
				
				photoDetails.setGalleryId(rs.getInt("id"));				
				photoDetails.setGalleryTitle(rs.getString("Gallery_name"));
				photoDetails.setGalleryTitleAlias(rs.getString("Gallery_caption"));
				photoDetails.setGallerySefTitle(rs.getString("gallery_sefurl"));
				photoDetails.setMetaKeywords(rs.getString("gallerykeywords")==null?"":rs.getString("gallerykeywords"));
				photoDetails.setMetaDescription(rs.getString("gallerydesc")==null?"":rs.getString("gallerydesc"));
				photoDetails.setCurrentPhotoNum(currentPhotoNo);
				photoDetails.setImageTitle(rs.getString("image_title")==null?"":rs.getString("image_title"));
				photoList.add(photoDetails);
			}
			
		} catch (Exception e) {
			System.out.println("GalleryHelper - photoDetailsAjax Exception is -->" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return photoList;
	}


	public static ArrayList latestGalleryChunk(int currentPageNum, int countToFetch, String galleryIdToAvoid, int categoryId, int timeInterval) throws Exception
	{
		ArrayList latestGalleryData = new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_pc = null;
		GalleryDTO galleryDTO = null;
		String galleryCheck = "";
		String categoryCheck = "";
		int galleryCount = 0;
		int gStartLimit = 0;
		String sql = "";
		gStartLimit = (currentPageNum-1)*countToFetch;
		int gEndLimit = countToFetch;
		String intervalCheck = "";
		if(!galleryIdToAvoid.equals("0")) {
			if(galleryIdToAvoid.indexOf(",") > 0)
				galleryCheck = "and gn.id not in ("+galleryIdToAvoid+")";
			else
				galleryCheck = "and gn.id != "+galleryIdToAvoid;
		}

		if(categoryId != 0) {
			categoryCheck = " gs.cat_id = "+categoryId + " and ";
		}
		if(timeInterval != 0) {
			intervalCheck = " and gn.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+timeInterval+" DAY) ";
		}

			
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();

			if(categoryId>0) {
				sql = "SELECT count(distinct gs.content_id) as galleryCount FROM jos_gallerynames gn, jos_gallery_section gs where gn.id=gs.content_id and gs.cat_id = "+categoryId+" and published=1 group by gs.cat_id";
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					galleryCount = rs.getInt("galleryCount");
				}
			}		
			
			sql="";
			pstmt=null;
			rs=null;
			sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url as gallery_sefurl, gn.small_image, gn.photo_category, " +
					"pc.title , pc.sef_url as cat_sefurl " +
					"FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
					"where " +categoryCheck+" pc.id=gs.cat_id and gn.id=gs.content_id and gn.published=1 "+galleryCheck+" and pc.published=1 " + intervalCheck +
					"group by gn.id order by gs.ordering desc limit "+gStartLimit + ", "+gEndLimit;
			//System.out.println("latestGalleryChunk->"+sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			sql="";
			pstmt=null;
			while (rs.next()) {
				galleryDTO = new GalleryDTO();
				galleryDTO.setCategoryId(rs.getInt("photo_category"));
				galleryDTO.setCategoryTitle(rs.getString("title"));
				galleryDTO.setCategorySefTitle(rs.getString("cat_sefurl"));
				galleryDTO.setGalleryThumbPhoto(rs.getString("small_image"));
				galleryDTO.setGalleryTitle(rs.getString("Gallery_name"));
				galleryDTO.setGalleryTitleAlias(rs.getString("Gallery_caption"));
				galleryDTO.setGalleryId(rs.getInt("id"));
				galleryDTO.setGallerySefTitle(rs.getString("gallery_sefurl"));

				try {
					sql = "SELECT count(pg.id) as photo_count FROM jos_photogallery pg where pg.enable=1 and pg.gallery_id="+rs.getInt("id");
					pstmt = connection.prepareStatement(sql);
					rs_pc = pstmt.executeQuery();
					while (rs_pc.next()) {
						galleryDTO.setPhotoCount(rs_pc.getInt("photo_count"));
					}
				} catch (Exception pcEx) {
					System.out.println("latestGalleryChunk Photo Gallery Count - "+pcEx.toString());
				} finally {
					sql="";
					pstmt=null;
					rs_pc=null;
				}

				galleryDTO.setGalleryCount(galleryCount);
				latestGalleryData.add(galleryDTO);
			}
		} catch (Exception e) {
			System.out.println("GalleryHelper - latestGalleryChunk Exception is ;" + e);
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

	public static ArrayList latestGalleryChunkWoPhotoCount(int currentPageNum, int countToFetch, String galleryIdToAvoid, int categoryId, int timeInterval) throws Exception
	{
		ArrayList latestGalleryData = new ArrayList();
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GalleryDTO galleryDTO = null;
		String galleryCheck = "";
		String categoryCheck = "";
		int gStartLimit = 0;
		String sql = "";
		gStartLimit = (currentPageNum-1)*countToFetch;
		int gEndLimit = countToFetch;
		String intervalCheck = "";
		if(!galleryIdToAvoid.equals("0")) {
			if(galleryIdToAvoid.indexOf(",") > 0)
				galleryCheck = "and gn.id not in ("+galleryIdToAvoid+")";
			else
				galleryCheck = "and gn.id != "+galleryIdToAvoid;
		}

		if(categoryId != 0) {
			categoryCheck = " gs.cat_id = "+categoryId + " and ";
		}
		if(timeInterval != 0) {
			intervalCheck = " and gn.created >= DATE_SUB(CURRENT_DATE,INTERVAL "+timeInterval+" DAY) ";
		}
			
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();

			sql = "SELECT gn.id, gn.Gallery_name, gn.Gallery_caption, gn.sef_url as gallery_sefurl, gn.small_image, gn.photo_category, " +
					"pc.title , pc.sef_url as cat_sefurl " +
					"FROM jos_gallerynames gn, jos_photocategories pc, jos_gallery_section gs " +
					"where " +categoryCheck+" pc.id=gs.cat_id and gn.id=gs.content_id and gn.published=1 "+galleryCheck+" and pc.published=1 " + intervalCheck +
					"group by gn.id order by gs.ordering desc limit "+gStartLimit + ", "+gEndLimit;
			//System.out.println("latestGalleryChunk->"+sql);
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			sql="";
			pstmt=null;
			while (rs.next()) {
				galleryDTO = new GalleryDTO();
				galleryDTO.setCategoryId(rs.getInt("photo_category"));
				galleryDTO.setCategoryTitle(rs.getString("title"));
				galleryDTO.setCategorySefTitle(rs.getString("cat_sefurl"));
				galleryDTO.setGalleryThumbPhoto(rs.getString("small_image"));
				galleryDTO.setGalleryTitle(rs.getString("Gallery_name"));
				galleryDTO.setGalleryTitleAlias(rs.getString("Gallery_caption"));
				galleryDTO.setGalleryId(rs.getInt("id"));
				galleryDTO.setGallerySefTitle(rs.getString("gallery_sefurl"));
				latestGalleryData.add(galleryDTO);
			}
		} catch (Exception e) {
			System.out.println("GalleryHelper - latestGalleryChunkWoPhotoCount Exception is ;" + e);
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
}