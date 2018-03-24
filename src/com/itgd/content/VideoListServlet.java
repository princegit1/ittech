package com.itgd.content;

import java.io.IOException;
import java.io.PrintWriter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.utils.Constants;
import com.itgd.helper.VideoHelper;

public class VideoListServlet extends HttpServlet {

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

		int categoryId = 42;
		int currentPage = 1;
		String pagePath="";
		String sefTitle="india";
		pagePath=request.getRequestURI();
		try {
			//System.out.println("VideoListServlet----"+pagePath);
			String[] tokens = pagePath.split("\\/");
			if(tokens.length>Constants.TWO) {
				sefTitle=tokens[Constants.TWO];					
			}
			if(tokens.length>Constants.THREE) {
				currentPage=Integer.parseInt(tokens[Constants.THREE]);
				if(currentPage<=1)
					currentPage=1;
			} else {
				currentPage=1;
			}
			if(tokens.length>Constants.FOUR) {
				String tempCategoryId = tokens[Constants.FOUR];
				if(tempCategoryId.indexOf(".") > 0)
					tempCategoryId = tempCategoryId.substring(0, tempCategoryId.indexOf("."));
				categoryId=Integer.parseInt(tempCategoryId);
			}
			request.setAttribute("videoCategoryId",categoryId);
			request.setAttribute("currentVideoCategoryPageNo",currentPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.VIDEO_LIST);
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

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out
				.println("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">");
		out.println("<HTML>");
		out.println("  <HEAD><TITLE>A Servlet</TITLE></HEAD>");
		out.println("  <BODY>");
		out.print("    This is ");
		out.print(this.getClass());
		out.println(", using the POST method");
		out.println("  </BODY>");
		out.println("</HTML>");
		out.flush();
		out.close();
	}

}
