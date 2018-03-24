package com.itgd.helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.AskQuestionDTO;
import com.itgd.dto.VideoDTO;

public class AskQuestionHelper {
	public static ArrayList latestQuestions(int categoryId, int currentPageNum, int countToFetch, String contentIdToAvoid) throws Exception
	{
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        AskQuestionDTO aDTO = null;
		ArrayList askquestionAL = new ArrayList();
		String questionCheck = "";
		int questionCount = 0;
		int qStartLimit = 0;
		String sql = "";
		qStartLimit = (currentPageNum-1)*countToFetch;
		int qEndLimit = countToFetch;
		
		if(!contentIdToAvoid.equals("0")) {
			if(contentIdToAvoid.indexOf(",") > 0)
				questionCheck = " and jc.id not in ("+contentIdToAvoid+") ";
			else
				questionCheck = " and jc.id != "+contentIdToAvoid + " ";
		}

		try
		{
            Dbconnection connect = null;
            connect = Dbconnection.getInstance();
            connection = connect.getConnection();
			
			sql = "SELECT count(id) as totcount FROM jos_snippets where sectionid="+categoryId+" and published=1";
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				questionCount = rs.getInt("totcount");
			}
			sql="";
			pstmt=null;
			rs=null;
			
			sql = "select jc.id,jc.headline,jc.longheadline, jc.sef_url,jc.main_text,jc.byline, date_format(jc.created,'%M %e, %Y ') as createdDate, " +
					"s.id as sectionid, s.title as sectiontitle, s.sef_url as sectionseftitle, s.metakey, s.metadesc from jos_snippets jc, jos_sections s " +
					"where jc.sectionid="+categoryId+" and s.id=jc.sectionid and jc.published=1 "+questionCheck+" order by jc.created desc, jc.ordering desc limit "+qStartLimit+","+qEndLimit;
			//sql = "select jc.id,jc.headline,jc.main_text,jc.byline, date_format(jc.created,'%M %e, %Y ') as createdDate from jos_snippets jc where jc.sectionid="+categoryId+" and s.published='1' and ars.section_id=s.id and ars.article_id=c.id and c.state=1 "+questionCheck+ " group by c.id order by ars.ordering desc ,c.id desc limit "+qStartLimit+","+qEndLimit;
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();
			//System.out.println(sql);
			while (rs.next()){
				aDTO = new AskQuestionDTO();
				aDTO.setId(rs.getInt("id"));
				aDTO.setTitle(rs.getString("headline")==null?"":rs.getString("headline"));				
				aDTO.setLongTitle(rs.getString("longheadline")==null?"":rs.getString("longheadline"));				
				aDTO.setSefTitle(rs.getString("sef_url")==null?"":rs.getString("sef_url"));
				aDTO.setByline(rs.getString("byline")==null?"":rs.getString("byline"));				
				aDTO.setCreatedDate(rs.getString("createdDate")==null?"":rs.getString("createdDate"));
				aDTO.setLongDescription(rs.getString("main_text")==null?"":rs.getString("main_text"));
				aDTO.setMetaKeyword(rs.getString("metakey")==null?"":rs.getString("metakey"));
				aDTO.setMetaDescription(rs.getString("metadesc")==null?"":rs.getString("metadesc"));
				aDTO.setQuestionCount(questionCount);
				aDTO.setSectionId(rs.getInt("sectionid"));
				aDTO.setSectionTitle(rs.getString("sectiontitle")==null?"":rs.getString("sectiontitle"));				
				aDTO.setSectionSefTitle(rs.getString("sectionseftitle")==null?"":rs.getString("sectionseftitle"));
				askquestionAL.add(aDTO);
			}
	    } catch (Exception e) {
	        System.out.println("AskQuestionHelper Listpage Exception is ;" + e);
	    } finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
	    }
		return askquestionAL;
	}



}
