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
import com.itgd.dto.StoryDTO;
import com.itgd.helper.StoryHelper;
import com.itgd.utils.Constants;
import com.itgd.utils.CommonFunctions;

public class AutoPageRedirectServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

int articleId = 0;
String sefTitle="";
int currentPage = 0;
String pagePath="";
pagePath=request.getRequestURI();
try {
	System.out.println("AA----"+pagePath);
	String[] tokens = pagePath.split("\\/");
	System.out.println("tokens.length->"+tokens.length);
	//for (String Str:tokens)
		//System.out.println(Str+"<br/>");
	
	for(int i=0;i<tokens.length;i++)
	{
		System.out.println(i + "--->"+tokens[i]);
	}
	
	response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
	
	if(tokens[2]!=null && tokens[2].length()>0 && tokens[2].trim().equals("photos")) {
		System.out.println("nitin-->"+tokens[2]);
		response.setHeader("Location", Constants.SITE_URL + "auto/photos");
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/auto/gallerylist.jsp");
	    //dispatcher.forward(request, response);
	}
	

	/*if(tokens.length>Constants.FOUR) {
		if(tokens.length>=Constants.TWO) {
			sefTitle=tokens[Constants.TWO];					
		}
		if(tokens.length>=Constants.THREE) {
			currentPage=Integer.parseInt(tokens[Constants.THREE]);
			if(currentPage<=0)
				currentPage=0;
		} else {
			currentPage=1;
		}
		if(tokens.length>=Constants.FOUR) {
			String tempArticleId = tokens[Constants.FOUR];
			if(tempArticleId.indexOf(".") > 0)
				tempArticleId = tempArticleId.substring(0, tempArticleId.indexOf("."));
			articleId=Integer.parseInt(tempArticleId);
		}
	}
	request.setAttribute("currentStoryId",articleId);
	request.setAttribute("currentStoryPageNo",currentPage);*/

	//RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.STORYPREVIEW_PAGE);
    //dispatcher.forward(request, response);
} catch(Exception ex) {
	System.out.println("errorSource->"+pagePath+" -- errorReason->"+ex.toString());
	response.sendRedirect(Constants.PAGE_NOT_FOUND);
}
}


public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

doGet(request, response);
}

}
