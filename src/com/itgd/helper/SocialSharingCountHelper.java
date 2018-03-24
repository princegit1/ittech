package com.itgd.helper;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.SocialShairingDTO;

public class SocialSharingCountHelper {
	
	public static String domain = "http://s2c.intoday.in/";		
	//public static String domain = "http://indiatodaylocal.in:8080/ads/sharecount1.json";
	
	public static String totalSharingDataCount(String createdDate,String urlString) throws Exception {
		URL url = null;
		HttpURLConnection conn=null;
		
		if(urlString!=null&&urlString.length()>0&&!urlString.equals("null")){
			if(urlString.contains(" ")){				
				urlString=urlString.replace(" ", "-");
			}
		}
		
		String fetchUrl=domain+"api.html?dt="+createdDate+"&url="+urlString;
		Long totalCount =null;
		JSONParser parser =null;
		Object obj =null;
		JSONObject jsonObject=null;
		String totalShareCount =null;
		try {
			url = new URL(fetchUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			//System.out.println("@@@@@@@@@-->"+fetchUrl);
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			parser = new JSONParser(); 
            obj = parser.parse(readUrl(fetchUrl)); 
            jsonObject = (JSONObject) obj;
            if(jsonObject!=null && jsonObject.get("total_count")!=null && !"".equals(jsonObject.get("total_count")))
            totalCount = (Long) jsonObject.get("total_count");   
            else
            	totalCount=0L;	
           // System.out.println("facebookTotalCount: " + totalCount);        
            totalShareCount =Format(totalCount.intValue());
            //System.out.println("facebookTotalCount: " + totalShareCount); 
        }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("s2c error-->"+e.getMessage());
        }finally{        	
    		parser =null;
    		obj =null;
    		jsonObject=null;
    		conn.disconnect();      	
        }
        return totalShareCount;
	}
	public static HashMap sharingDataCount(String createdDate,String urlString) throws Exception {
	
		URL url = null;
		HttpURLConnection conn=null;
		String fetchUrl=domain+"api.html?dt="+createdDate+"&url="+urlString;
		Long totalCount =null;
		HashMap objMap=null;
		JSONParser parser =null;
		Object obj =null;
		JSONObject jsonObject=null;
		if(urlString!=null&&urlString.length()>0&&!urlString.equals("null")){
			if(urlString.contains(" ")){				
				urlString=urlString.replace(" ", "-");
			}
		}
		try {
			url = new URL(fetchUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			System.out.println("@@@@@@@@@11-->"+fetchUrl);
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			parser = new JSONParser(); 
            obj = parser.parse(readUrl(fetchUrl)); 
            jsonObject = (JSONObject) obj;           
            objMap= new HashMap();
            if(jsonObject!=null && jsonObject.get("fb_total_count")!=null && !"".equals(jsonObject.get("fb_total_count")))
            	objMap.put("fb",Format(Integer.parseInt(String.valueOf(jsonObject.get("fb_total_count")))));
            else
            	objMap.put("fb","0");
            if(jsonObject!=null && jsonObject.get("twitter")!=null && !"".equals(jsonObject.get("twitter")))
            	objMap.put("twitter",Format(Integer.parseInt(String.valueOf(jsonObject.get("twitter")))));
            else
            	objMap.put("twitter","0");	
            if(jsonObject!=null && jsonObject.get("googleplusone")!=null && !"".equals(jsonObject.get("googleplusone")))
            	objMap.put("google",Format(Integer.parseInt(String.valueOf(jsonObject.get("googleplusone")))));
            else
            	objMap.put("google","0");	
                       
        }
        } catch (Exception e) {
        	System.out.println("s2c error-->"+e.getMessage());
        }finally{
        	parser =null;
    		obj =null;
    		jsonObject=null;
    		conn.disconnect();      	
        }
        return objMap;
	}
	public static ArrayList trendingDataDetails(String urlString,int contentCount) throws Exception {
		
		URL url = null;
		HttpURLConnection conn=null;
		String fetchUrl=domain+"topcount.html?host="+urlString+"&count="+contentCount;
		//fetchUrl="http://indiatodaylocal.in:8080/test.json";
		Long totalCount =null;
		ArrayList objList=null;
		JSONParser parser =null;
		Object obj =null;
		JSONObject jsonObject=null;
		SocialShairingDTO objSocialShairingDTO=null; 
		try {
			url = new URL(fetchUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
			//System.out.println("@@@@@@@@@-->"+fetchUrl); 
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
			parser = new JSONParser(); 
            obj = parser.parse(readUrl(fetchUrl)); 
            jsonObject = (JSONObject) obj;            
            objList= new ArrayList();
            JSONArray jsonArray = (JSONArray) jsonObject.get("JsonData");
            int jsonObjSize=jsonArray.size();
        	//System.out.println("@@@@@@@@@-->"+jsonObjSize); 
            for (int i = 0; i <jsonArray.size(); ++i) {
                 final JSONObject jsonObj = (JSONObject) jsonArray.get(i);
            	objSocialShairingDTO= new SocialShairingDTO();            	
                objSocialShairingDTO.setContentType((String) jsonObj.get("content_type"));
                objSocialShairingDTO.setContentImage((String) jsonObj.get("image"));
                objSocialShairingDTO.setContentUrl((String) jsonObj.get("content_url"));
                if(jsonObj!=null && jsonObj.get("total_count")!=null && !"".equals(jsonObj.get("total_count"))){
                	objSocialShairingDTO.setTrendTotalCount(Format(Integer.parseInt(((String) jsonObj.get("total_count")))));
                } else{
                	objSocialShairingDTO.setTrendTotalCount("0");
                }
                
            	//System.out.println(jsonObj.get("article_id")+"@@@@@@@@@-->"+objSocialShairingDTO.getTrendTotalCount()); 
                
                objSocialShairingDTO.setContentId((String) jsonObj.get("article_id"));
                objList.add(objSocialShairingDTO);
			}
            
        }
        } catch (Exception e) {
        	System.out.println("s2c error-->"+e.getMessage());
        	//e.printStackTrace();
        	
        }finally{        	
    		parser =null;
    		obj =null;
    		jsonObject=null;
    		conn.disconnect();      	
        }
        return objList;
	}public static HashMap <String, String> multiURlDataDetails(String urlString,int contentCount) throws Exception {
		
		URL url = null;
		HttpURLConnection conn=null;	
		if(urlString!=null&&urlString.length()>0&&!urlString.equals("null")){
			if(urlString.contains(" ")){				
				urlString=urlString.replace(" ", "-");
			}
		}
		//urlString="data[0][date]=2015-04-08&data[0][url]=http%3A%2F%2Faajtak.intoday.in%2Fstory%2Ffew-records-made-in-ipl-season-one-are-still-unbeatable-1-806891.html&data[1][date]=2015-04-08&data[1][url]=http%3A%2F%2Findiatodaylocal.in%3A8080%2Fsection%2F113%2F1%2Fworld.html&data[2][date]=&data[2][url]=&data[3][date]=&data[3][url]=&data[4][date]=&data[4][url]=";
		//urlString="data[0][date]=2015-04-13&data[0][url]=http://indiatoday.intoday.in/story/modi-germany-hannover-messe-business-investment-make-in-india/1/429780.html";
		///contentIdList="431406#431406#431406#431406";
		//urlString="story~433566~2015-05-22|story~433557~2015-05-21|story~433564~2015-05-22|story~433542~2015-05-21|story~433559~2015-05-21|story~433532~2015-05-21|story~433536~2015-05-21|story~433490~2015-05-20|story~433493~2015-05-20|story~433484~2015-05-5|photo~14763~2015-05-3|story~433507~2015-05-22|story~433509~2015-05-20|story~433552~2015-05-21|story~433091~2015-05-21|story~433549~2015-05-21|story~433426~2015-05-21|story~433424~2015-05-21|story~433451~2015-05-3|";
		//String fetchUrl=domain+"apimulti.html?siteId=1&list="+urlString;
		//String fetchUrl="http://s2c.intoday.in/apimulti.html?sieId=1&list=story~435256~2015-05-7|story~435265~2015-05-7|story~436125~2015-05-7|story~436152~2015-05-8|story~435085~2015-05-21|story~435259~2015-05-7|story~436121~2015-05-7|story~435227~2015-05-7|photo~14787~2015-05-7|story~435263~2015-05-7|story~436129~2015-05-7|story~436142~2015-05-7|story~435247~2015-05-7|story~435196~2015-05-7|story~435238~2015-05-7|story~435211~2015-05-7|story~435222~2015-05-7|story~435207~2015-05-7|story~435145~2015-05-7|";
		String fetchUrl=domain+"apimulti.html?siteId=1&list="+urlString;
		//System.out.println("@@@@@@@@@@-->"+fetchUrl);
		Long totalCount =null;	
		JSONParser parser =null;
		Object obj =null;
		JSONObject jsonObject=null;		
		
		
		HashMap<String, String> objMap=null;
		try {
			url = new URL(fetchUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.connect();
		
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {		

				parser = new JSONParser(); 
	            obj = parser.parse(readUrl(fetchUrl)); 
	            jsonObject = (JSONObject) obj;
	            JSONArray jsonArray = (JSONArray) jsonObject.get("JsonData");
	            int jsonObjSize=jsonArray.size();
	            objMap= new HashMap<String, String>();
	            for (int i = 0; i <jsonArray.size(); ++i) {
	                 final JSONObject jsonObj = (JSONObject) jsonArray.get(i);
	                if(jsonObj!=null && jsonObj.get("article_id")!=null && !"".equals(jsonObj.get("article_id"))&& jsonObj.get("total_count")!=null && !"".equals(jsonObj.get("total_count")))
	                objMap.put(String.valueOf(jsonObj.get("article_id")).trim(),Format(Integer.parseInt(String.valueOf(jsonObj.get("total_count")))));
	                               
	                
				}
			}
			
        } catch (Exception e) {
        	System.out.println("s2c error-->"+e.getMessage());
        }finally{        	
    		parser =null;
    		obj =null;
    		jsonObject=null;
    		conn.disconnect();      	
        }
        return objMap;
	}
	
	private static String readUrl(String urlString) throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL(urlString);
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read=0;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
	public static int contentCommentCount(int contentId, String contentType) throws Exception
	{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String selectSql = "";		
		int commentCount = 0;		
		try {
			if(contentType.trim().equals(""))
				contentType="story";
			if(contentId==0)
				contentType="general";		
			
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
		
			if(contentType.equals("photo")) {
				selectSql = "select count(a.id) as totalcount from jos_comments a,jos_gallerynames c" +
				" where a.article_id=? and a.content_type=? and a.slideid=? and a.article_id=c.id and c.published>=0";			
				
			} else {
				selectSql = "select count(a.id) as totalcount from jos_comments a,jos_content c" +
						" where a.article_id=? and a.content_type=? and a.article_id=c.id and c.state>=0";
			}
			//System.out.println(contentType+"gdddg"+contentId+"CommentsHelper - contentComments Exception is " + selectSql);
			pstmt = connection.prepareStatement(selectSql);
			pstmt.setInt(1, contentId);
			pstmt.setString(2, contentType);			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				commentCount = rs.getInt("totalcount");					
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
		return commentCount;
	}
	public static ArrayList trendingDataDetailsFromContent(String contentId, String contentType) throws Exception
	{
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		String selectSql = "";		
		int commentCount = 0;
		ArrayList objList=null;
		  objList= new ArrayList();
		SocialShairingDTO objSocialShairingDTO=null; 
		try {
		
			Dbconnection connect = null;
			connect = Dbconnection.getInstance();
			connection = connect.getConnection();
		
			if(contentType.equals("photo")) {
				selectSql = "select title,large_image as largeimage from jos_gallerynames c" +
				" where c.id=? and c.published=1";			
				
			} else {
				selectSql = "select title,large_kicker_image as largeimage from jos_content c" +
						" where c.id=? and c.state=1";
			}
			//System.out.println(contentType+"gdddg"+contentId+"CommentsHelper - contentComments Exception is " + selectSql);
			pstmt = connection.prepareStatement(selectSql);
			pstmt.setString(1, contentId);					
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objSocialShairingDTO= new SocialShairingDTO();
				objSocialShairingDTO.setContentType(contentType);
                objSocialShairingDTO.setContentImage(rs.getString("largeimage"));
                objSocialShairingDTO.setContentTitle(rs.getString("title"));
                objList.add(objSocialShairingDTO);
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
		return objList;
	}
	
	public static String Format(Integer number){
	    String[] suffix = new String[]{"k","m","b","t"};
	    String result =""; 
	    int size = 0;
	    try{
	    size = (number.intValue() != 0) ? (int) Math.log10(number) : 0;
	    if (size >= 3){
	        while (size % 3 != 0) {
	            size = size - 1;
	        }
	    }
	    double notation = Math.pow(10, size);
	    result = (size >= 3) ? + (Math.round((number / notation) * 100) / 100.0d)+suffix[(size/3) - 1] : + number + "";
	    }catch(Exception e){}
	    return result;
	}
	public static void main(String[] args) {
		try {
			//SocialSharingCountHelper.totalSharingDataCount("2015-05-21", "http://indiatoday.intoday.in/story/tanu-weds-manu-returns-review-kangana-ranaut-r-madhavan-deepak-dobriyal-anand-l-rai/1/439454.html");
			/*HashMap <String, String>  middleLeftMap =SocialSharingCountHelper.multiURlDataDetails("wwwdd",1);
			
			   String middleLeftShare; 
			     if(middleLeftMap != null && middleLeftMap.size() > 0){
			       middleLeftShare=  middleLeftMap.get(String.valueOf(435256));
			       if(middleLeftShare!=null&&middleLeftShare.length()>0)
			       middleLeftShare=middleLeftShare;
			       else
			       middleLeftShare="0";
			       }else{
			        middleLeftShare="0";
			      }*/
			    // System.out.println("@@@@@@@@-->"+middleLeftShare);
			//HashMap map =SocialSharingCountHelper.sharingDataCount("2015-04-08", "http://aajtak.intoday.in/story/few-records-made-in-ipl-season-one-are-still-unbeatable-1-806891.html");
			//System.out.println(map.get("fb"));
			ArrayList al =SocialSharingCountHelper.trendingDataDetails("indiatoday.intoday.in", 12);
			System.out.println(al.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("s2c error-->"+e.getMessage());
		}
	}
}
