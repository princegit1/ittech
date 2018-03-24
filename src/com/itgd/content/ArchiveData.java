package com.itgd.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.dto.*;
import com.itgd.utils.*;
import com.itgd.helper.*;



public class ArchiveData extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*int page = 0;
		ArrayList issueList = new ArrayList();
		String path="";
		try{
		path=request.getRequestURI();
		String pagenum = request.getParameter("page");
		if(pagenum==null)
		{
			page = 0;
		}
		else
		{
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		 issueList = Archive.ArchiveList(page);
		
		 
		}catch(Exception e){
			
			e.printStackTrace();
		    request.setAttribute("ERROR", path);			   
            ForwardPage.forwardRequestToPage("error.jsp", request, response);
            return ;
			
		}
		 
	     request.getSession().setAttribute("ARCHIVE",issueList);
	     RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ARCHIVES);
	     dispatcher.forward(request, response);*/
		String finalURL = Constants.SITE_BASEPATH +"calendar";
		response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		response.setHeader("Location", finalURL);
	}

	

}
