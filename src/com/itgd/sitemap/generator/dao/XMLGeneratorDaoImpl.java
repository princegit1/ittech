package com.itgd.sitemap.generator.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.xmlcontent.XmlContentDTO;

public class XMLGeneratorDaoImpl implements XMLGeneratorDaoIfc {
	
	
	public List<XmlContentDTO> processToGenerateSectionStoryXml(int sectionId) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String[] primeCategory = null;
		String redirectedURL = null;
		String selectQuery = null;
		String bseNseCode = null;
		String byline = null;
		int issueId = 0;
		int magazineId = 0;
		
		List<XmlContentDTO> xmlContentDtoList = new ArrayList<XmlContentDTO>();
				
		try {
			conn = Dbconnection.getInstance().getConnection();
			selectQuery = "SELECT cont.sef_url,cont.id, DATE_FORMAT(cont.created, '%Y-%m-%d') AS pubdate, cont.title AS headline, cont.metakey, " + 
						  "cont.metadesc FROM jos_article_section ars, jos_content cont, jos_sections js WHERE ars.section_id = ? "+ 
						  "AND ars.article_id = cont.id AND ars.section_id = js.id AND js.published = 1 AND cont.state = 1 " +
						  "GROUP BY cont.id ORDER BY ars.ordering DESC LIMIT ?";
			System.out.println("selectQuery-->"+selectQuery);
			
			pstmt = conn.prepareStatement(selectQuery);
			pstmt.setInt(1, sectionId);
			pstmt.setInt(2, 70);
			rs = pstmt.executeQuery();
			rs.last();
			rs.beforeFirst(); 

			while(rs.next()) {
				XmlContentDTO xmlContentDTO = new XmlContentDTO();
				xmlContentDTO.setContentId(rs.getInt("id"));
				xmlContentDTO.setStorySefUrl(isEmpty(rs.getString("sef_url")));
				xmlContentDTO.setHeadline(replaceSpCharacters(rs.getString("headline")).replaceAll("\\<.*?>",""));
				xmlContentDTO.setMetakey(replaceSpCharacters(rs.getString("metakey")).replaceAll("\\<.*?>",""));
				xmlContentDTO.setNewsKeyword(replaceSpCharacters(rs.getString("metadesc")).replaceAll("\\<.*?>",""));
				xmlContentDTO.setPublicationDate(rs.getString("pubdate"));
				
				xmlContentDTO.setStorySefUrl("http://indiatoday.intoday.in/auto/story/" + xmlContentDTO.getStorySefUrl().replace(".html", "") +  
											 "/1/" + xmlContentDTO.getContentId() + ".html");
				xmlContentDtoList.add(xmlContentDTO);
			}	// End-While
		} catch(SQLException sqlEx){
			System.out.println("Found SQLException in XMLGeneratorDaoImple: Data Fetching Error: " + sqlEx.toString() + ", Caused By: " + sqlEx.getCause());
			xmlContentDtoList = null;
		} finally {
			if(pstmt != null)
				pstmt.close();
			if(rs != null)
				rs.close();
			if(conn != null)
				conn.close();
		}
		return xmlContentDtoList;
	}
	
	private static String replaceSpCharacters(String strToReplace) {
        String replacedString =  null;
        if(!isBlank(strToReplace)) {
              replacedString = strToReplace.replaceAll("&rsquo;", "\'").replaceAll("&lsquo;","\'").replaceAll("&nbsp;"," ").replaceAll("&mdash;","-").replaceAll("&hellip;","...").replaceAll("&#39;","'").replaceAll("&ndash;","-").replaceAll("&ldquo;", "\'").replaceAll("&minus;", "\'").replaceAll("&rdquo;", "\'").replaceAll("&eacute;", "E ").replaceAll("&agrave;", " ").replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&hellip;","...").replaceAll("_blank","'_blank'").replaceAll("<STRONG>","").replaceAll("</STRONG>","").replaceAll("<BR>","<BR />").replaceAll("<br>","<br />").replaceAll("&","and");
        } else {
              replacedString = "";
        }           
        return replacedString;
  }



	private static String isEmpty(String value) {
        if(isBlank(value)) {
              return "";
        } else { 
              return value.trim();
        }
  }
  
	private static boolean isBlank(String value) {
        if(value == null || value == "" || value == " " || value == "null") {
              return true;
        } else if(value != null && value.trim().length() == 0){
              return true;
        } else {
              return false;
        }
  }

}
