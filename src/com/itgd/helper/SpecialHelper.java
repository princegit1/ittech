package com.itgd.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.itgd.conn.Dbconnection;
import com.itgd.dto.SpecialDTO;

public class SpecialHelper {
	public static ArrayList specialsContent(String currentCategory, int currentPageNum, int countToFetch, int status, String orderByField, String orderByStatus) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SpecialDTO spDTO = null;	
		ArrayList specialsAL = new ArrayList();
		String contentStatus="";
		String contentOrderByField = "ordering";
		String contentOrderByStatus = "desc";
		if(status==1) {
			contentStatus = " published=1 and ";
		}		
		if(orderByField.equals("title") || orderByField.equals("created") || orderByField.equals("ordering")) {
			contentOrderByField = orderByField;
		}
		if(orderByStatus.equals("asc") || orderByStatus.equals("desc")) {
			contentOrderByStatus = orderByStatus;
		}
		int startLimit = 0;
		startLimit = (currentPageNum-1)*countToFetch;
		int endLimit = countToFetch;
		String limitClause = "";
		String categoryClause = "";
		int specialContentCount = 0;
		String sql = "";
		if(countToFetch > 0) {
			limitClause = " limit "+startLimit+", "+endLimit;
		}
		if(currentCategory.trim().length() > 0) {
			categoryClause = " and category_seftitle = '"+currentCategory+"' ";

		}
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			sql = "SELECT count(id) as contentCount FROM jos_specials where " +contentStatus +" website='indiatoday' "+categoryClause;
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				specialContentCount = rs.getInt("contentCount");
			}
			sql = "";
			pstmt = null;
			rs = null;
			
			sql = "SELECT id, title, url, small_image, small_image_alttext, medium_image, large_image, short_description, meta_keyword, " +
					"meta_description, date_format(modified,'%M %e, %Y') as modifieddatedisplay,  " +
					"date_format(created,'%M %e, %Y') as createddatedisplay, website, content_type, category_id, category_title, category_seftitle from jos_specials " +
					"where " +contentStatus +" website='indiatoday' "+categoryClause+" order by "+contentOrderByField+ " "+contentOrderByStatus + limitClause;
			//System.out.println("Special Query ->" + sql);	
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				spDTO = new SpecialDTO();
				spDTO.setId(rs.getInt("id"));				
				spDTO.setTitle(rs.getString("title"));
				spDTO.setUrl(rs.getString("url") == null ? "" : rs.getString("url"));
				spDTO.setShortDescription(rs.getString("short_description") == null ? "" : rs.getString("short_description"));
				spDTO.setSmallImage(rs.getString("small_image") == null ? "" : rs.getString("small_image"));
				spDTO.setMediumImage(rs.getString("medium_image") == null ? "" : rs.getString("medium_image"));
				spDTO.setLargeImage(rs.getString("large_image") == null ? "" : rs.getString("large_image"));
				spDTO.setSmallImageAltText(rs.getString("small_image_alttext") == null ? "" : rs.getString("small_image_alttext"));
				spDTO.setMetaKeyword(rs.getString("meta_keyword") == null ? "" : rs.getString("meta_keyword"));				
				spDTO.setSmallImageAltText(rs.getString("small_image_alttext") == null ? "" : rs.getString("small_image_alttext"));
				spDTO.setMetaDescription(rs.getString("meta_description") == null ? "" : rs.getString("meta_description"));				
				spDTO.setSmallImageAltText(rs.getString("small_image_alttext") == null ? "" : rs.getString("small_image_alttext"));
				spDTO.setCreatedDate(rs.getString("createddatedisplay") == null ? "" : rs.getString("createddatedisplay"));
				spDTO.setUpdatedDate(rs.getString("modifieddatedisplay") == null ? "" : rs.getString("modifieddatedisplay"));
				spDTO.setSpecialCount(specialContentCount);
				spDTO.setCategoryId(rs.getInt("category_id"));
				spDTO.setCategoryTitle(rs.getString("category_title") == null ? "" : rs.getString("category_title"));
				spDTO.setCategorySefTitle(rs.getString("category_seftitle") == null ? "" : rs.getString("category_seftitle"));				
				spDTO.setContentType(rs.getString("content_type") == null ? "" : rs.getString("content_type"));
				specialsAL.add(spDTO);
			}
		} catch (Exception e) {
			System.out.println("IT SpecialHelper.java-specialsContent-Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return specialsAL;
	}


}
