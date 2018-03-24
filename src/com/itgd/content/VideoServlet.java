package com.itgd.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import com.itgd.conn.Dbconnection;
import com.itgd.dto.VideoDTO;
import com.itgd.helper.VideoHelper;
import com.itgd.utils.Constants;
import com.itgd.utils.CommonFunctions;

public class VideoServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int videoId = 0;
		String sefTitle="";
		int currentPage = 1;
		String pagePath="";
		pagePath=request.getRequestURI();
		try {
			String[] tokens = pagePath.split("\\/");
			for(int i=0; i<tokens.length; i++) {
				//System.out.println(i + "  --  " + tokens[i]);
			}
			
			if(request.getParameter("vid")!=null){
				videoId = Integer.parseInt(request.getParameter("vid"));
				currentPage=1;
				ArrayList videoDataFetched = new ArrayList();
				videoDataFetched = VideoHelper.videoDetailToRedirect(videoId);
				if(videoDataFetched.size()>0) {
					VideoDTO videoDTO = (VideoDTO) videoDataFetched.get(0);
					String finalURL = Constants.SITE_BASEPATH + CommonFunctions.videoURL(videoDTO.getVideoSefTitle(), currentPage, videoDTO.getVideoId());
					//response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					//response.setHeader("Location", finalURL);
				}
			} else {
				if(tokens[Constants.ONE].equals("Video")) {
					if(tokens.length>=Constants.TWO) {
						videoId=Integer.parseInt(tokens[Constants.TWO]);
					}
					if(currentPage<=1)
						currentPage=1;
					
					//System.out.println("videoId->"+videoId);
					if(videoId!=0) {
						System.out.println("1");
						ArrayList videoDataFetched = new ArrayList();
						videoDataFetched = VideoHelper.videoDetailToRedirect(videoId);
						//System.out.println("2");
						if(videoDataFetched.size()>0) {
							//System.out.println("3");
							VideoDTO videoDTO = (VideoDTO) videoDataFetched.get(0);
							String finalURL = Constants.SITE_BASEPATH + CommonFunctions.videoURL(videoDTO.getVideoSefTitle(), currentPage, videoDTO.getVideoId());
							System.out.println("finalURL->"+finalURL);
							//response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
							//response.setHeader("Location", finalURL);
						} else {
							System.out.println("errorSource->"+pagePath);
							//response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
							//response.setHeader("Location", Constants.PAGE_NOT_FOUND);
						}
					} else {
						if(tokens.length>=Constants.THREE) {
							int videoCategoryId = 42;
							videoCategoryId=Integer.parseInt(tokens[Constants.THREE]);
							ArrayList videoDataFetched = new ArrayList();
							videoDataFetched = VideoHelper.videoListDetailToRedirect(videoCategoryId);
							if(videoDataFetched.size()>0) {
								VideoDTO videoDTO = (VideoDTO) videoDataFetched.get(0);
								String finalURL = Constants.SITE_BASEPATH + CommonFunctions.videoListURL(videoDTO.getVideoCategorySefTitle(), currentPage, videoDTO.getVideoCategoryId());
								//response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
								//response.setHeader("Location", finalURL);
							} else {
								System.out.println("errorSource->"+pagePath);
								//response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
								//response.setHeader("Location", Constants.PAGE_NOT_FOUND);
							}
						}
					}
				} else if(tokens[Constants.ONE].equals("video")) {
					if(tokens.length>=Constants.FOUR) {
						sefTitle=tokens[Constants.TWO];					
						currentPage=Integer.parseInt(tokens[Constants.THREE]);
						if(currentPage<=1)
							currentPage=1;
						if(tokens.length>=Constants.FOUR) {
							String tempArticleId = tokens[Constants.FOUR];
							if(tempArticleId.indexOf(".") > 0)
								tempArticleId = tempArticleId.substring(0, tempArticleId.indexOf("."));
							videoId=Integer.parseInt(tempArticleId);
						}
					}
					request.setAttribute("currentVideoId",videoId);
					request.setAttribute("currentVideoPageNo",currentPage);
					RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.VIDEO_PAGE);
			        dispatcher.forward(request, response);
				} 
			}
		} catch(Exception ex) {
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+ex.toString());
			//response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			//response.setHeader("Location", Constants.PAGE_NOT_FOUND);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
