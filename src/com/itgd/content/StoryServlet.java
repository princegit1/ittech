package com.itgd.content;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import com.itgd.dto.StoryDTO;
import com.itgd.helper.StoryHelper;
import com.itgd.utils.Constants;
import com.itgd.utils.CommonFunctions;

public class StoryServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int storyId = 0;
		String sefTitle="";
		int currentPage = 0;
		String pagePath="";
		pagePath=request.getRequestURI();
		try {
			String[] tokens = pagePath.split("\\/");
			//System.out.println(tokens.length + " -----story");
			if(request.getParameter("sId")!=null){
				storyId = Integer.parseInt(request.getParameter("sId"));
				currentPage=1;
				ArrayList storyDataFetched = new ArrayList();
				storyDataFetched = StoryHelper.storyDetailToRedirect(storyId);
				if(storyDataFetched.size()>0) {
					StoryDTO sDTO = (StoryDTO) storyDataFetched.get(0);
					String finalURL = Constants.SITE_BASEPATH + CommonFunctions.storyURL(sDTO.getSefTitle(), currentPage, sDTO.getId());
					response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					response.setHeader("Location", finalURL);
				}
			} else {
				if(tokens[Constants.ONE].equals("article")) {
					if(tokens.length>(Constants.FOUR)) {
						if(tokens.length>=Constants.TWO) {
							sefTitle=tokens[Constants.TWO];					
						}
						if(tokens.length>=(Constants.THREE)) {
							currentPage=Integer.parseInt(tokens[Constants.THREE]);
							if(currentPage<=0)
								currentPage=0;
						} else {
							currentPage=1;
						}
						if(tokens.length>=(Constants.FOUR)) {
							String tempArticleId = tokens[Constants.FOUR];
							if(tempArticleId.indexOf(".") > 0)
								tempArticleId = tempArticleId.substring(0, tempArticleId.indexOf("."));
							storyId=Integer.parseInt(tempArticleId);
						}
					}
					String finalURL = Constants.SITE_BASEPATH + CommonFunctions.storyURL(tokens[Constants.TWO], currentPage, storyId);
					response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					response.setHeader("Location", finalURL);
				} else if(tokens[Constants.ONE].equals("story")) {
					//System.out.println(tokens[Constants.ONE] + " -----story");
					if(tokens.length>Constants.FOUR) {
						if(tokens.length>=Constants.TWO) {
							sefTitle=tokens[Constants.TWO];					
						}
						if(tokens.length>=(Constants.THREE)) {
							currentPage=Integer.parseInt(tokens[Constants.THREE]);
							if(currentPage<=0)
								currentPage=0;
						} else {
							currentPage=1;
						}
						if(tokens.length>=(Constants.FOUR)) {
							String tempArticleId = tokens[Constants.FOUR];
							if(tempArticleId.indexOf(".") > 0)
								tempArticleId = tempArticleId.substring(0, tempArticleId.indexOf("."));
							storyId=Integer.parseInt(tempArticleId);
						}
					}
					//System.out.println(" -----story"+storyId);
				} else {
					if(tokens.length>=(Constants.TWO)) {
						storyId=Integer.parseInt(tokens[Constants.TWO]);
					}
					if(request.getParameter("page")!=null)
						currentPage = Integer.parseInt(request.getParameter("page"))+1;
					if(currentPage<=1)
						currentPage=1;
					
					ArrayList articleDataFetched = new ArrayList();
					articleDataFetched = StoryHelper.storyDetailToRedirect(storyId);
					if(articleDataFetched.size()>0) {
						StoryDTO articleDTO = (StoryDTO) articleDataFetched.get(0);
						String finalURL = Constants.SITE_BASEPATH + CommonFunctions.storyURL(articleDTO.getSefTitle(), currentPage, articleDTO.getId());
						response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
						response.setHeader("Location", finalURL);
					}
				}
			}
			request.setAttribute("currentStoryId",storyId);
			request.setAttribute("currentStoryPageNo",currentPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.STORY_PAGE);
	        dispatcher.forward(request, response);
	        
		} catch(Exception ex) {
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+ex.toString());
			//response.sendRedirect(Constants.PAGE_NOT_FOUND);
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location",Constants.PAGE_NOT_FOUND);
		}

	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
