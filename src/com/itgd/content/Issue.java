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
import com.sun.org.apache.bcel.internal.generic.ISUB;

public class Issue extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		int issueid = 0;
		ArrayList archivedata = null;
		ArrayList CoverData = null;
		String issueYear="";
		String path="";
		try{
		path=request.getRequestURI();		
		if(request.getParameter("issueId")!=null)
		{
			issueid = Integer.parseInt(request.getParameter("issueId"));
			issueYear = ArchiveIssue.issueYear(issueid);	
			issueYear=issueYear.substring(issueYear.lastIndexOf(",")+1,issueYear.length()).trim();
			String finalURL = Constants.SITE_BASEPATH +"calendar/"+issueid+"/"+issueYear+"/magazine.html";
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location", finalURL);
		}
		else
		{
			issueid = 0;
		} 	
		archivedata= new ArrayList();
		CoverData= new ArrayList();
		 archivedata = ArchiveIssue.sections(issueid);		
		 CoverData = ArchiveIssue.Cover(issueid);
		
		}catch(Exception e){			
			e.printStackTrace();
		    request.setAttribute("ERROR", path);			   
		    response.sendRedirect(Constants.PAGE_NOT_FOUND);
            return ;
			
		}
		 
		 request.getSession().setAttribute("COVERSTORY",CoverData);
	     request.getSession().setAttribute("ARCHIVEPAGE",archivedata);
	     RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.ISSUE);
	     dispatcher.forward(request, response);
		
		
	}

	

}
