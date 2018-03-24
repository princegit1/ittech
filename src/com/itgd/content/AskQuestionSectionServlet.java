package com.itgd.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.itgd.conn.Dbconnection;
import com.itgd.utils.Constants;
import com.itgd.utils.CommonFunctions;

public class AskQuestionSectionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int currentPage = 1;
		String pagePath="";
		pagePath=request.getRequestURI();
		try{
			if(request.getParameter("page")!=null) {
				currentPage = Integer.parseInt(request.getParameter("page"));
			}
			if(currentPage<=1) {
				currentPage=1;
			}
			//System.out.println("currentPage->"+currentPage+"----------"+request.getParameter("page"));
			request.setAttribute("currentPage",currentPage);
	        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.AKBAR_KA_DURBAR_SECTION);
	        dispatcher.forward(request, response);
		}catch(Exception e){	
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+e.toString());
			response.sendRedirect(Constants.PAGE_NOT_FOUND);
		}
	}
}
