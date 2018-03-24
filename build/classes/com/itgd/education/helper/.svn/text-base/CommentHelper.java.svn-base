package com.itgd.education.helper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.itgd.education.utils.DbConnection;
import com.itgd.education.dto.CommentDTO;

public class CommentHelper {

	public static ArrayList contentComment(int contentId, String contentType, int countToFetch, int currentPageNum) throws Exception
	{
		//System.out.println("comment");
		//contentId -> article_id/galleryid
		//contentType -> story, photo, video, general etc
		//countToFetch -> 0 = all comments else as numbers specified
		//currentPageNum -> 0 means chunk and more than 0 means template 
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommentDTO cDTO=null;
		ArrayList commentAL = new ArrayList();
		String selectSql = "";
		String limitClause = "";
		int commentCount = 0;
		int commentCtr=0;
		int startLimit = 0;
		int endLimit = 0;
		try {
			if(contentType.trim().equals(""))
				contentType="story";
			if(contentId==0)
				contentType="general";

			if(currentPageNum>0)
				startLimit = (currentPageNum-1)*countToFetch;
			
			//endLimit = startLimit + countToFetch;
			endLimit = countToFetch;
			
			if(countToFetch > 0) {
				limitClause = " limit "+startLimit+", "+endLimit;
			}
			//System.out.println("startLimit->"+startLimit+ "--------------endLimit->"+endLimit);
			
			DbConnection connect = null;
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");
			if(currentPageNum>0) {
				if(contentType.equals("photo")) {
					selectSql = "select count(a.id) as totalcount from jos_comments a,jos_gallerynames c" +
					" where a.galleryid=? and a.content_type=? and a.galleryid=c.id and a.state=1 and c.published=1";
				} else {
					selectSql = "select count(a.id) as totalcount from jos_comments a,jos_content c" +
							" where a.article_id=? and a.content_type=? and a.article_id=c.id and a.state=1 and c.state=1";
				}
				pstmt = connection.prepareStatement(selectSql);
				pstmt.setInt(1, contentId);
				pstmt.setString(2, contentType);
				rs = pstmt.executeQuery();
				//System.out.println("comment count query->"+selectSql);
				while (rs.next()) {
					commentCount = rs.getInt("totalcount");
				}
				selectSql="";
				pstmt=null;
				rs=null;			
			}			
			if(contentType.equals("photo")) {
				selectSql = "select c.id, c.Gallery_name as title, c.sef_url, a.fname,a.lname, a.email,a.city,a.country, a.comment,a.display_emailid, " +
				" date_format(a.created_date,'%M %e, %Y') AS cdate, date_format(a.created_date,'%H:%i IST') AS ctime, a.content_type from jos_comments a,jos_gallerynames c" +
				" where a.galleryid=? and a.content_type=? and a.galleryid=c.id and a.state=1 and c.published=1 ORDER BY a.id desc"+limitClause;
			} else {
				selectSql = "select c.id, c.title, c.sef_url, a.fname,a.lname, a.email,a.city,a.country, a.comment,a.display_emailid, " +
						" date_format(a.created_date,'%M %e, %Y') AS cdate, date_format(a.created_date,'%H:%i IST') AS ctime, a.content_type from jos_comments a,jos_content c" +
						" where a.article_id=? and a.content_type=? and a.article_id=c.id and a.state=1 and c.state=1 ORDER BY a.id desc"+limitClause;
			}
			pstmt = connection.prepareStatement(selectSql);
			pstmt.setInt(1, contentId);
			pstmt.setString(2, contentType);
			rs = pstmt.executeQuery();
			//System.out.println("comment query->"+selectSql);
			while (rs.next()) {
				//if(commentCtr>=startLimit && commentCtr<endLimit) {
					cDTO = new CommentDTO();
					cDTO.setContentId(rs.getInt("id"));
					cDTO.setCity(rs.getString("city"));
					cDTO.setComment(rs.getString("comment"));
					cDTO.setDisplayEmail(rs.getInt("display_emailid"));
					cDTO.setEmail(rs.getString("email"));
					cDTO.setFirstName(rs.getString("fname"));
					cDTO.setLastName(rs.getString("lname"));
					cDTO.setCommentDate(rs.getString("cdate"));
					cDTO.setCommentTime(rs.getString("ctime"));
					cDTO.setContentTitle(rs.getString("title"));
					cDTO.setContentSefTitle(rs.getString("sef_url"));
					cDTO.setContentType(rs.getString("content_type"));
					cDTO.setCommentCount(commentCount);
					commentAL.add(cDTO);
				}
				//commentCtr++;
			//}

		} catch (Exception e) {
			System.out.println("CommentsHelper - contentComments Exception is " + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return commentAL;
	}

	public static ArrayList generalComment(String contentType, int countToFetch, int currentPageNum) throws Exception
	{
		//contentType -> story, photo, video, general etc
		//countToFetch -> 0 = all comments else as numbers specified
		//currentPageNum -> 0 means chunk and more than 0 means template 
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommentDTO cDTO=null;
		ArrayList commentAL = new ArrayList();
		String selectSql = "";
		String limitClause = "";
		int commentCount = 0;
		int startLimit = 0;
		int endLimit = 0;
		try {
			if(contentType.trim().equals(""))
				contentType="general";

			if(currentPageNum>0)
				startLimit = (currentPageNum-1)*countToFetch;
			
			endLimit = countToFetch;
			
			if(countToFetch > 0) {
				limitClause = " limit "+startLimit+", "+endLimit;
			}
			//System.out.println("startLimit->"+startLimit+ "--------------endLimit->"+endLimit);
			
			DbConnection connect = null;
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");
			if(currentPageNum>0) {
				selectSql = "select id as totalcount from jos_comments where article_id=0 and content_type=? and state=1";
				pstmt = connection.prepareStatement(selectSql);
				pstmt.setString(1, contentType);
				rs = pstmt.executeQuery();
				rs.last();
				commentCount = rs.getRow();
				rs.beforeFirst();
				/*while (rs.next()) {
					commentCount = rs.getInt("totalcount");
				}*/
				selectSql="";
				pstmt=null;
				rs=null;			
			}			
			selectSql = "select fname, lname, email, city, country, comment, display_emailid, " +
						" date_format(created_date,'%M %e, %Y') AS cdate, date_format(created_date,'%H:%i IST') AS ctime, content_type " +
						" from jos_comments where article_id=0 and content_type=? and state=1 ORDER BY id desc"+limitClause;
			pstmt = connection.prepareStatement(selectSql);
			pstmt.setString(1, contentType);
			rs = pstmt.executeQuery();
			//System.out.println(selectSql);
			while (rs.next()) {
				cDTO = new CommentDTO();
				cDTO.setCity(rs.getString("city"));
				cDTO.setComment(rs.getString("comment"));
				cDTO.setDisplayEmail(rs.getInt("display_emailid"));
				cDTO.setEmail(rs.getString("email"));
				cDTO.setFirstName(rs.getString("fname"));
				cDTO.setLastName(rs.getString("lname"));
				cDTO.setCommentDate(rs.getString("cdate"));
				cDTO.setCommentTime(rs.getString("ctime"));
				cDTO.setContentType(rs.getString("content_type"));
				cDTO.setCommentCount(commentCount);
				commentAL.add(cDTO);
			}
		} catch (Exception e) {
			System.out.println("CommentsHelper - contentComments Exception is " + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		return commentAL;
	}

	public static ArrayList contentCommentList(String contentType, int countToFetch, int currentPageNum) throws Exception
	{
		//contentType -> story, photo, video, general etc
		//countToFetch -> 0 = all comments else as numbers specified
		//currentPageNum -> 0 means chunk and more than 0 means template 
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt1 = null;
		ResultSet rs1 = null;
		CommentDTO cDTO=null;
		ArrayList commentAL = new ArrayList();
		String selectSql = "";
		String selectSql1 = "";
		String contentCheck = "";
		int commentCount = 0;
		int commentCtr=0;
		int startLimit = 0;
		int endLimit = 10;
		int contentCommentCount = 0;
		try {
			if(contentType.trim().equals("story") || contentType.trim().equals("photo") || contentType.trim().equals("video"))
				contentCheck = " and content_type='"+contentType+"'";
			else 
				contentCheck = "";
				
			if(currentPageNum>0)
				startLimit = (currentPageNum-1)*countToFetch;
			
			//endLimit = startLimit + countToFetch;
			endLimit = countToFetch;
			//System.out.println("startLimit->"+startLimit+ "--------------endLimit->"+endLimit);
			
			DbConnection connect = null;
			connect = DbConnection.getInstance("indiatoday");
			connection = connect.getConnection("indiatoday");
			if(currentPageNum>0) {
				selectSql1 = "select distinct(article_id) as id, content_type from jos_comments where article_id!=0 and state=1 "+contentCheck+" order by id desc";
				pstmt1 = connection.prepareStatement(selectSql1);
				rs1 = pstmt1.executeQuery();
				//System.out.println(selectSql1);
				rs1.last();
				commentCount = rs1.getRow();
				rs1.beforeFirst();

				while (rs1.next()) {
					if(commentCtr>=startLimit && commentCtr<endLimit) {

						if(contentType.equals("photo")) {
							selectSql = "select c.id, c.Gallery_name as title, c.sef_url, a.fname,a.lname, a.email,a.city,a.country, a.comment,a.display_emailid, " +
							" date_format(a.created_date,'%M %e, %Y') AS cdate, date_format(a.created_date,'%H:%i IST') AS ctime, a.content_type from jos_comments a,jos_gallerynames c" +
							" where a.galleryid=? and a.state=1 and a.content_type=? and c.id=a.galleryid and c.published=1 ORDER BY a.id desc limit 3";
						} else {
							selectSql = "select c.id, c.title, c.sef_url, a.fname,a.lname, a.email,a.city,a.country, a.comment,a.display_emailid, " +
									" date_format(a.created_date,'%M %e, %Y') AS cdate, date_format(a.created_date,'%H:%i IST') AS ctime, a.content_type from jos_comments a,jos_content c" +
									" where a.article_id=? and a.state=1 and a.content_type=? and c.id=a.article_id and c.state=1 ORDER BY a.id desc limit 3";
						}
						pstmt = connection.prepareStatement(selectSql);
						pstmt.setInt(1, rs1.getInt("id"));
						pstmt.setString(2, rs1.getString("content_type"));
						rs = pstmt.executeQuery();
						//System.out.println(selectSql);
						rs.last();
						contentCommentCount = rs.getRow();
						rs.beforeFirst();
						while (rs.next()) {
							cDTO = new CommentDTO();
							cDTO.setContentId(rs.getInt("id"));
							cDTO.setCity(rs.getString("city")==null?"":rs.getString("city"));
							cDTO.setComment(rs.getString("comment")==null?"":rs.getString("comment"));
							cDTO.setDisplayEmail(rs.getInt("display_emailid"));
							cDTO.setEmail(rs.getString("email")==null?"":rs.getString("email"));
							cDTO.setFirstName(rs.getString("fname")==null?"":rs.getString("fname"));
							cDTO.setLastName(rs.getString("lname")==null?"":rs.getString("lname"));
							cDTO.setCommentDate(rs.getString("cdate")==null?"":rs.getString("cdate"));
							cDTO.setCommentTime(rs.getString("ctime")==null?"":rs.getString("ctime"));
							cDTO.setContentTitle(rs.getString("title")==null?"":rs.getString("title"));
							cDTO.setContentSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
							cDTO.setContentType(rs.getString("content_type")==null?"":rs.getString("content_type"));
							cDTO.setCommentCount(commentCount);
							cDTO.setContentCommentCount(contentCommentCount);
							commentAL.add(cDTO);
						}

					}
					commentCtr++;
				}
			}			
		} catch (Exception e) {
			System.out.println("CommentsHelper - contentComments Exception is " + e);
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
		return commentAL;
	}
	 
	public static ArrayList featuredComment() throws Exception
	{
		Connection conSurf = null;
		PreparedStatement stmtSurf = null;
		ResultSet rsSurf = null;
		CommentDTO surfer = null;
		ArrayList surf = new ArrayList();
		try
		{
			DbConnection connectSurf = null;
			connectSurf = DbConnection.getInstance("indiatoday");
			conSurf  = connectSurf.getConnection("indiatoday");
			String sql = "select con.title,con.sef_url,com.content_type, com.article_id,com.fname,com.lname,com.city,com.display_emailid,com.email,com.comment from jos_comments com,jos_content con where com.state=1 and com.article_id!=0 and com.featured=1 and com.article_id=con.id order by com.id desc,created_date desc limit 2";
			stmtSurf = conSurf.prepareStatement(sql);
			rsSurf = stmtSurf.executeQuery();
			while (rsSurf.next()){
				surfer = new CommentDTO();
				surfer.setCity(rsSurf.getString("city"));
				surfer.setComment(rsSurf.getString("comment"));
				surfer.setContentType(rsSurf.getString("content_type"));
				surfer.setFirstName(rsSurf.getString("fname"));
				surfer.setLastName(rsSurf.getString("lname"));
				surfer.setContentSefTitle(rsSurf.getString("sef_url"));
				surfer.setContentId(rsSurf.getInt("article_id"));
				surfer.setContentTitle(rsSurf.getString("title"));
				surf.add(surfer);
			}
		}
		catch (Exception e){
			
			System.out.println("Exception is: " + e);
		} finally {
			if(rsSurf!=null)
				rsSurf.close();
			if(stmtSurf!=null)
				stmtSurf.close();
			if(conSurf!=null)
				conSurf.close();
		}
	
 return surf;	
	}


}
 