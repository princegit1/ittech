package com.itgd.education.content;

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

import com.itgd.education.utils.DbConnection;
import com.itgd.education.dto.StoryDTO;
import com.itgd.education.helper.StoryHelper;
import com.itgd.education.utils.Constants;
import com.itgd.education.utils.CommonFunctions;

public class StoryServlet extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int storyId = 0;
		String sefTitle="";
		int currentPage = 0;
		String pagePath="";
		pagePath=request.getRequestURI();
		try {
			String[] tokens = pagePath.split("\\/");
			/*for(int i=0; i<tokens.length; i++) {
				System.out.println(i + "  --  " + tokens[i]);
			}*/
				if(tokens[Constants.ONE].equals("story")) {
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
				}
			request.setAttribute("currentStoryId",storyId);
			request.setAttribute("currentStoryPageNo",currentPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.STORY_PAGE);
	        dispatcher.forward(request, response);
	        
		} catch(Exception ex) {
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+ex.toString());
			response.sendRedirect(Constants.PAGE_NOT_FOUND);
		}

	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
