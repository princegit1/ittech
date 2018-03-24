package com.itgd.content;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.utils.Constants;


public class Calender extends HttpServlet {

	String pagePath="";
	String issueId = "";
	
	String Type = "";
	String errorReason = "";
	String month="";
	String year="";
	String date="";
	String calenderDate="";
	String[] calDate=null;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
try{
			
	pagePath=request.getRequestURI();	
	String[] tokens = pagePath.split("\\/");	
	
	
	
		if(tokens.length==Constants.FOUR) {
			Type=tokens[Constants.THREE];
			if(Type.equals("online.html")){
				calenderDate=tokens[Constants.TWO];				
			}
			if(calenderDate.contains("-")){
				 calDate = calenderDate.split("\\-");
			}
			date=calDate[0];
			month=calDate[1];
			year=calDate[2];
			
			
			request.setAttribute("year",year);
			request.setAttribute("day",date);
			request.setAttribute("month",month);
			request.setAttribute("type",Type.replaceAll(".html", ""));
			}
		if(tokens.length==Constants.FIVE) {
			Type=tokens[Constants.FOUR];
			if(Type.equals("magazine.html")){
				year=tokens[Constants.THREE];	
				issueId=tokens[Constants.TWO];	
				
				request.setAttribute("issueid",issueId);
				request.setAttribute("year",year);
				request.setAttribute("type",Type.replaceAll(".html", ""));
			}				
			}
		if(tokens.length>Constants.FIVE) {
			response.sendRedirect(Constants.PAGE_NOT_FOUND);
			return;
			
			}	
	RequestDispatcher dispatcher = request.getRequestDispatcher("/calender.jsp");
	dispatcher.forward(request, response);
}catch(Exception e){
	//e.printStackTrace();
	response.sendRedirect(Constants.PAGE_NOT_FOUND);
}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		doGet(request, response);
}


}
