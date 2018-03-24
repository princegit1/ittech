package com.itgd.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.helper.HeadlinesTodayHelper;
import com.itgd.utils.Constants;


public class ProgrammeServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int programmeId = 0;
		int currentPageNo = 1;
		String pagePath="";
		String sefTitle="";
		ArrayList pData = new ArrayList();		
		pagePath=request.getRequestURI();
		
		try {
			String[] tokens = pagePath.split("\\/");
			if(request.getParameter("contentId")!=null){
				programmeId = Integer.parseInt(request.getParameter("contentId"));
				if(request.getParameter("video")!=null){
					currentPageNo = Integer.parseInt(request.getParameter("video"))+1;
				}
				if(currentPageNo<=0)
					currentPageNo=1;
				String strReturned = HeadlinesTodayHelper.articleDetailToRedirect(programmeId);
				if(strReturned.indexOf("#")>0) {
					String finalURL = "/"+tokens[1]+"/newhome/" + HeadlinesTodayHelper.programmeURL(strReturned.substring(0,strReturned.indexOf("#")), currentPageNo, Integer.parseInt(strReturned.substring(strReturned.indexOf("#")+1,strReturned.length())));
					response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					response.setHeader("Location", finalURL);
				}
			} else {
			
				/*if(tokens.length>Constants.THREE) {
							sefTitle=tokens[Constants.THREE];					
						}*/
				if(tokens!=null && tokens.length>Constants.THREE)
				{
				if(tokens.length>=Constants.THREE) {
					currentPageNo=Integer.parseInt(tokens[Constants.THREE]);
					if(currentPageNo<=1)
						currentPageNo=1;
				} else {
					currentPageNo=1;
				}
				if(tokens.length>=Constants.TWO) {
					sefTitle=tokens[Constants.TWO];					
				}
				
				if(tokens.length>=Constants.FOUR) {
					String tempProgrammeId = tokens[Constants.FOUR];
					if(tempProgrammeId.indexOf(".") > 0)
						tempProgrammeId = tempProgrammeId.substring(0, tempProgrammeId.indexOf("."));
					programmeId=Integer.parseInt(tempProgrammeId);
				}
				}
				request.setAttribute("currentProgrammeId",programmeId);
				request.setAttribute("currentProgrammePageNo",currentPageNo);

				RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.HLT_PROGRAMME);
			    dispatcher.forward(request, response);
			}
		} catch(Exception ex) {
			//ex.printStackTrace();
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+ex.toString());
			//response.sendRedirect(Constants.PAGE_NOT_FOUND);
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location", Constants.PAGE_NOT_FOUND);
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	doGet(request, response);
	}

}
