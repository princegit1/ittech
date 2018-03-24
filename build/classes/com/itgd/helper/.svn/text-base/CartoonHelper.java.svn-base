package com.itgd.helper;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import com.itgd.conn.Dbconnection;
import com.itgd.dto.CartoonDTO;

public class CartoonHelper {
	public static ArrayList cartoonDetail(int id) throws Exception 
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartoonDTO cartoongallery = null;;
		ArrayList datalist = new ArrayList();
		try {

			Dbconnection connect = null;
			String sql = "SELECT n.CartoonGallery_name,j.cartoon_name,j.cartoon_caption,j.cartoon_title FROM jos_cartoongallery j,jos_cartoongallerynames n where j.cartoongallery_id = '"+id+"' and j.enable=1 and j.cartoongallery_id = n.id and n.published=1";
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println(sql);
			while (rs.next()) {
				cartoongallery = new CartoonDTO();
				cartoongallery.setGalleryCaption(rs.getString("cartoon_caption"));
				cartoongallery.setGalleryImage(rs.getString("cartoon_name"));
				cartoongallery.setGalleryText(rs.getString("cartoon_title"));
				cartoongallery.setGalleryTitle(rs.getString("CartoonGallery_name"));
				datalist.add(cartoongallery);

			}
		} catch (Exception e) {
			System.out.println("Exception is ;" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return datalist;
	}
	
	public static ArrayList cartoonList(int currentPageNum, int countToFetch, String cartoonIdToAvoid) throws Exception
	{
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CartoonDTO cDTO = null;
		ArrayList cAL = new ArrayList();
		String cartoonCheck = "";
		
		int cartoonCount = 0;
		int cStartLimit = 0;
		String sql = "";
		if(currentPageNum>0)
			cStartLimit = (currentPageNum-1)*countToFetch;
		
		int cEndLimit = countToFetch;
		
		if(!cartoonIdToAvoid.equals("0")) {
			if(cartoonIdToAvoid.indexOf(",") > 0)
				cartoonCheck = " and gn.id not in ("+cartoonIdToAvoid+") ";
			else
				cartoonCheck = " and gn.id != "+cartoonIdToAvoid;
		}
		
		try
		{
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();

			if(currentPageNum>0) {
				sql = "SELECT count(id) as totcount FROM jos_cartoongallerynames where published=1";
				pstmt = connection.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					cartoonCount = rs.getInt("totcount");
				}
			}		
			
			sql="";
			pstmt=null;
			rs=null;

			sql = "SELECT id,Cartoonthumb_image,cartoonGallery_name FROM jos_cartoongallerynames where published=1 "+cartoonCheck+" order by id desc  limit "+cStartLimit+","+cEndLimit;
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//System.out.println(sql);
			while (rs.next()){
				cDTO = new CartoonDTO();
				cDTO.setCartoonId(rs.getInt("id"));
				cDTO.setCartoonImage(rs.getString("Cartoonthumb_image"));
				cDTO.setCartoonTitle(rs.getString("cartoonGallery_name"));
				cDTO.setCartoonCount(cartoonCount);
				cAL.add(cDTO);
			}
		} catch (Exception e){
			System.out.println("CartoonHelper - cartoonList Exception is: " + e);
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
