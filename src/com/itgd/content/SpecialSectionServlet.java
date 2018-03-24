package com.itgd.content;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.utils.Constants;

public class SpecialSectionServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SpecialSectionServlet() {
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
		int currentPage = 1;		
		String currentCategory = "";

		String pagePath="";
		pagePath=request.getRequestURI();
		try {
			//System.out.println("pagePath SpecialSectionServlet --> " + pagePath);
			String[] tokens = pagePath.split("\\/");
			/*for(int i=0; i<tokens.length; i++) {
				System.out.println(i + "  --  " + tokens[i]);
			}*/
			//System.out.println("tokens[Constants.ONE]  --  " + tokens[Constants.ONE]);
			if(tokens.length>Constants.TWO) {
				currentCategory = tokens[Constants.TWO];
				//System.out.println("tokens[Constants.TWO]  --  " + tokens[Constants.TWO]);
			}
			if(tokens.length>Constants.THREE) {
				try {
					String tempPageNum = tokens[Constants.THREE];
					if(tempPageNum.indexOf(".") > 0)
						tempPageNum = tempPageNum.substring(0, tempPageNum.indexOf("."));

					currentPage=Integer.parseInt(tempPageNum);
					//System.out.println("tokens[Constants.THREE]  --  " + tokens[Constants.THREE]);
				} catch (Exception se) {
					System.out.println("SpecialSectionServlet->"+se.toString());
				}
				if(currentPage<=1)
					currentPage=1;
			}
			request.setAttribute("currentPageNo",currentPage);
			request.setAttribute("currentCategory",currentCategory);
			//System.out.println("currentPage - " + currentPage + " -- currentCategory - " + currentCategory); 
			RequestDispatcher dispatcher = request.getRequestDispatcher("/specials.jsp");
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
