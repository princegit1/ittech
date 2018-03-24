package com.itgd.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.itgd.utils.Constants;

public class CartoonListServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int currentPage = 1;
		String pagePath="";
		pagePath=request.getRequestURI();
		try {
			//System.out.println("AA----"+pagePath);
			String[] tokens = pagePath.split("\\/");
			if(tokens.length>=Constants.TWO) {
				currentPage=Integer.parseInt(tokens[Constants.TWO]);
				if(currentPage<=1)
					currentPage=1;
			} else {
				currentPage=1;
			}
			request.setAttribute("currentCartoonPageNo",currentPage);

			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.CARTOON_LIST);
	        dispatcher.forward(request, response);
		} catch(Exception ex) {
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+ex.toString());
			response.sendRedirect(Constants.PAGE_NOT_FOUND);
		}
	}	
}
