package com.itgd.content;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import java.util.ArrayList;
import java.util.List;
import com.itgd.utils.Constants;

public class CategoryServletTech extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public CategoryServletTech() {
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
		//System.out.println("CategoryListing>>>>>>>>>>>>>>"+pagePath);
		try {
			String dataUrl[]=pagePath.split("/");
			int dataUrlLen=dataUrl.length-1;

			String storyIdUrl[]=dataUrl[dataUrlLen].split("\\.");
			categoryId=Integer.parseInt(storyIdUrl[0]);
			 currentPage=Integer.parseInt(dataUrl[dataUrl.length-2]);
			//System.out.println("categoryId>>>>>>>>>"+categoryId+"<<<<<<<<<<<"+currentPage);	
			request.setAttribute("currentCategoryListId",categoryId);
			request.setAttribute("currentCategoryListPageNo",currentPage);
			request.setAttribute("newPagePath", pagePath);
				
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.CATEGORY_PAGE_TECH);
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
