package com.itgd.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.utils.Constants;
import com.itgd.helper.GalleryHelper;

public class GalleryListServlet extends HttpServlet {

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
		int currentPage = 1;
		String pagePath="";
		String sefTitle="";
		pagePath=request.getRequestURI();
		try {
			if(request.getParameter("photocat")!=null){
				categoryId = Integer.parseInt(request.getParameter("photocat"));
				if(request.getParameter("page")!=null)
					currentPage = Integer.parseInt(request.getParameter("page"))+1;
				if(currentPage<=1)
					currentPage=1;
			} else {
			//System.out.println("AA----"+pagePath);
				String[] tokens = pagePath.split("\\/");
				if(tokens[Constants.ONE].equals("photo")) {
					categoryId = Integer.parseInt(tokens[Constants.THREE]);
					currentPage=1;
				} else {
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
				}
			}
			request.setAttribute("photoCategoryId",categoryId);
			request.setAttribute("currentPhotoCategoryPageNo",currentPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GALLERY_LIST);
	        dispatcher.forward(request, response);
		} catch(Exception ex) {
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+ex.toString());
			response.sendRedirect(Constants.PAGE_NOT_FOUND);
		}
	}
}
