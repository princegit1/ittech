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

public class SimplyServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int page = 0;
		int CatId=0;
		String pagenum="";		
		String path="";
		
		try{
		
		path=request.getRequestURI();
		pagenum = request.getParameter("page");
		CatId = Integer.parseInt(request.getParameter("catId"));
		
		if(pagenum==null)
		{
			page = 0;
		}
		else
		{
			page = Integer.parseInt(request.getParameter("page"));
		}
		 request.setAttribute("pagenum",page);
	     request.setAttribute("CatId",CatId);
	     
	        
	     RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.SIMPLY);
	     dispatcher.forward(request, response);
	
		} catch (Exception e) {
			
			response.sendRedirect(Constants.PAGE_NOT_FOUND);
		}
			
	}

	

}