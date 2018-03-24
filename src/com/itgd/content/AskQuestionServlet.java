package com.itgd.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.dto.AskQuestionDTO;
import com.itgd.utils.*;
import com.itgd.helper.*;

public class AskQuestionServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int questionId = 0;
		String pagePath="";
		pagePath=request.getRequestURI();
		try{
			if(request.getParameter("qid")!=null) {
				questionId = Integer.parseInt(request.getParameter("qid"));
			}
			request.setAttribute("questionId",questionId);
	        RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.AKBAR_KA_DURBAR_STORY);
	        dispatcher.forward(request, response);
		}catch(Exception e){	
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+e.toString());
			response.sendRedirect(Constants.PAGE_NOT_FOUND);
		}
	}

}
