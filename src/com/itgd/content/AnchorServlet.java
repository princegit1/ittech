package com.itgd.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itgd.utils.Constants;
import com.itgd.helper.HeadlinesTodayHelper;

public class AnchorServlet extends HttpServlet {

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

		int anchorId = 0;
		int currentPageNo = 1;
		String pagePath="";
		String sefTitle="";
		ArrayList aData = new ArrayList();
		pagePath=request.getRequestURI();
		try {
			String[] tokens = pagePath.split("\\/");
			if(request.getParameter("cId")!=null){
				anchorId = Integer.parseInt(request.getParameter("cId"));
				if(currentPageNo<=1)
					currentPageNo=1;
				String strReturned = HeadlinesTodayHelper.articleDetailToRedirect(anchorId);
				if(strReturned.indexOf("#")>0) {
					String finalURL = "/"+tokens[1]+"/headlines_today/" + HeadlinesTodayHelper.anchorURL(strReturned.substring(0,strReturned.indexOf("#")), currentPageNo, Integer.parseInt(strReturned.substring(strReturned.indexOf("#")+1,strReturned.length())));
					response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					response.setHeader("Location", finalURL);
				}
			} else {
				if(tokens.length>=Constants.TWO) {
					sefTitle=tokens[Constants.TWO];					
				}
				if(tokens.length>=Constants.THREE) {
					currentPageNo=Integer.parseInt(tokens[Constants.THREE]);
					if(currentPageNo<=1)
						currentPageNo=1;
				} else {
					currentPageNo=1;
				}
				if(tokens.length>=Constants.FOUR) {
					String tempAnchorId = tokens[Constants.FOUR];
					if(tempAnchorId.indexOf(".") > 0)
						tempAnchorId = tempAnchorId.substring(0, tempAnchorId.indexOf("."));
					anchorId=Integer.parseInt(tempAnchorId);
				}
				request.setAttribute("currentAnchorId",anchorId);
				request.setAttribute("currentAnchorPageNo",currentPageNo);

				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.HLT_ANCHOR);
			    dispatcher.forward(request, response);
			}
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
