package com.itgd.content;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itgd.utils.Constants;

public class ProgrammesSectionServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("ProgrammesSectionServlet");
		String pagePath="";
		pagePath=request.getRequestURI(); 
		String[] tokens = pagePath.split("\\/");
		try {
			if(pagePath.contains("HeadlinesTodayProgrammes")) {
				String finalURL = "/"+tokens[1]+"/programmes";
				response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
				response.setHeader("Location", finalURL);
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.HLT_PROGRAMMES_SECTION);
				dispatcher.forward(request, response);
			}
		} catch(Exception ex) {
			//System.out.println("errorSource->"+pagePath+" -- errorReason->"+pagePath+"programmes.jsp");
			response.sendRedirect(Constants.PAGE_NOT_FOUND);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
