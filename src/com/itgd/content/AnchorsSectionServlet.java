package com.itgd.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.utils.CommonFunctions;
import com.itgd.utils.Constants;

public class AnchorsSectionServlet extends HttpServlet {

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

		//System.out.println("AnchorsSectionServlet");
		String pagePath="";
		pagePath=request.getRequestURI();
		String[] tokens = pagePath.split("\\/");

		//System.out.println(pagePath);
		try {
			if(pagePath.contains("ProgrammesAnchorsSection")) {
				String finalURL = "/"+tokens[1]+"/headlines_today/anchors";
				response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				response.setHeader("Location", finalURL);
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.HLT_ANCHORS_SECTION);
			dispatcher.forward(request, response);
		} catch(Exception ex) {
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+pagePath+Constants.HLT_ANCHORS_SECTION);
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
