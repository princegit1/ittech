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
import java.util.List;

import com.itgd.education.utils.DbConnection;
import com.itgd.education.utils.Constants;
import com.itgd.education.utils.CommonFunctions;

public class CategoryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CategoryServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

		int categoryId = 0;
		String sefTitle="";
		int currentPage = 1;
		String pagePath="";
		pagePath=request.getRequestURI();
		//System.out.println("CategoryServlet");
		try {
				//System.out.println("CategoryListServle----"+pagePath);
				String[] tokens = pagePath.split("\\/");
				//System.out.println("tokens.length->"+tokens.length);
				if(tokens.length>Constants.FOUR) {
					if(tokens.length>=Constants.TWO) {
						sefTitle=tokens[Constants.TWO];					
					}
					if(tokens.length>=Constants.THREE) {
						currentPage=Integer.parseInt(tokens[Constants.THREE]);
						if(currentPage<=1)
							currentPage=1;
					} else {
						currentPage=1;
					}
					if(tokens.length>=Constants.FOUR) {
						String tempGalleryId = tokens[Constants.FOUR];
						if(tempGalleryId.indexOf(".") > 0)
							tempGalleryId = tempGalleryId.substring(0, tempGalleryId.indexOf("."));
						categoryId=Integer.parseInt(tempGalleryId);
					}
				} else if(tokens.length==Constants.FOUR) {
					categoryId=Integer.parseInt(tokens[Constants.TWO]);
					currentPage=1;
				}

			request.setAttribute("currentCategoryListId",categoryId);
			request.setAttribute("currentCategoryListPageNo",currentPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.CATEGORY_PAGE);
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
