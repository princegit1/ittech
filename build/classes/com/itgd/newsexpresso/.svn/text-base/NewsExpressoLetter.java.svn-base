/**
 * 
 */
package com.itgd.newsexpresso;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.HomePageManagerDTO;

/**
 * @author ashutosh1
 * This file used to generate News Expresso Newsletter
 *
 */
public class NewsExpressoLetter {
	public static ArrayList newsExpresso(int secid,int status) throws Exception {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HomePageManagerDTO homePageMgrDto = null;	
		ArrayList dataList = new ArrayList();
		StringBuffer queryBuff = null;
		String sefUrl = null;
		String checkValue = null;
		try {
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
			queryBuff = new StringBuffer("SELECT snippet.id, snippet.snippet_icon, snippet.strapheadline, snippet.headline, snippet.longheadline, snippet.main_text, ")
							.append("snippet.published, snippet.ordering, snippet.sef_url, content.kicker_image ")
							.append("FROM jos_snippets snippet LEFT JOIN jos_content content ON snippet.story_id = content.id WHERE snippet.sectionid= ? ");
			
			if(status==1) {
				queryBuff.append("AND snippet.published=1 AND snippet.strapheadline = CURRENT_DATE ORDER BY snippet.id, snippet.ordering");
			} else {
				queryBuff.append("AND snippet.strapheadline = CURRENT_DATE AND snippet.id >= (SELECT snippet_icon FROM jos_snippets WHERE sectionid=?")
				.append(" ORDER BY id DESC LIMIT 1) ORDER BY snippet.id, snippet.ordering");
			}
			System.out.println("HomePageManagerHelper.newsExpresso("+secid+", "+status+"): queryBuff >>> "+queryBuff);
			pstmt = connection.prepareStatement(queryBuff.toString());
			pstmt.setInt(1, secid);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				homePageMgrDto = new HomePageManagerDTO();
				checkValue = rs.getString("headline");
				homePageMgrDto.setTitle((checkValue==null?"":checkValue));
				
				checkValue = rs.getString("longheadline");
				homePageMgrDto.setDescription((checkValue==null?"":checkValue));//(checkValue==null?"":checkValue)
				
				checkValue = rs.getString("main_text");
				homePageMgrDto.setShortDescription((checkValue==null?"":checkValue));
				
				checkValue = rs.getString("strapheadline");
				homePageMgrDto.setStrapHeadline((checkValue==null?"":checkValue));
				
				checkValue = rs.getString("kicker_image");
				homePageMgrDto.setKickerImage((checkValue==null?"":checkValue));
				
				sefUrl = rs.getString("sef_url");
				if(sefUrl != null){
					homePageMgrDto.setSefTitle(sefUrl);
				}
								
				dataList.add(homePageMgrDto);
			}
		} catch (Exception e) {
			System.out.println("IT HomePageManagerHelper.java-newsExpresso-Exception is :" + e);
		} finally {
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(connection!=null)
				connection.close();
		}
		//System.out.println("HomePageManagerHelper.newsExpresso():List size: "+dataList.size());
		return dataList;
	}
}
