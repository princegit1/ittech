package com.itgd.content;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.utils.CommonFunctions;
import com.itgd.utils.Constants;
import com.itgd.dto.GalleryDTO;
import com.itgd.helper.GalleryHelper;

public class GalleryServlet extends HttpServlet {

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int galleryId = 0;
		int currentPhotoNo = 1;
		String pagePath="";
		String sefTitle="";
		ArrayList photoData = new ArrayList();
		
		pagePath=request.getRequestURI();
		//System.out.println("AA----"+pagePath);
		try {
			String[] tokens = pagePath.split("\\/");
			if(request.getParameter("gId")!=null){
				galleryId = Integer.parseInt(request.getParameter("gId"));
				currentPhotoNo=1;
				ArrayList galleryDataFetched = new ArrayList();
				galleryDataFetched = GalleryHelper.galleryDetailToRedirect(galleryId);
				if(galleryDataFetched.size()>0) {
					GalleryDTO galleryDTO = (GalleryDTO) galleryDataFetched.get(0);
					String finalURL = Constants.SITE_BASEPATH + CommonFunctions.galleryURL(galleryDTO.getGallerySefTitle(), currentPhotoNo, galleryDTO.getGalleryId());
					response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
					response.setHeader("Location", finalURL);
				}
			} else {
				if(tokens[Constants.ONE].equals("Photo")) {
					galleryId=Integer.parseInt(tokens[Constants.TWO]);
					if(tokens.length>=Constants.FIVE) {
						String tempPhotoNo = tokens[Constants.FOUR];					
						if(tempPhotoNo.matches("photo[0-9]{1,2}")){
							tempPhotoNo=tempPhotoNo.substring(tempPhotoNo.indexOf("photo")+5,tempPhotoNo.length());
							currentPhotoNo=Integer.parseInt(tempPhotoNo);
							if(currentPhotoNo<=1)
								currentPhotoNo=1;
						} else {
							currentPhotoNo=1;
						}
					} else {
						currentPhotoNo=1;
					}
					ArrayList galleryDataFetched = new ArrayList();
					galleryDataFetched = GalleryHelper.galleryDetailToRedirect(galleryId);
					if(galleryDataFetched.size()>0) {
						GalleryDTO galleryDTO = (GalleryDTO) galleryDataFetched.get(0);
						String finalURL = Constants.SITE_BASEPATH + CommonFunctions.galleryURL(galleryDTO.getGallerySefTitle(), currentPhotoNo, galleryDTO.getGalleryId());
						response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
						response.setHeader("Location", finalURL);
					}
				} else {
					if(tokens.length>=Constants.TWO) {
						sefTitle=tokens[Constants.TWO];					
					}
					if(tokens.length>=Constants.THREE) {
						currentPhotoNo=Integer.parseInt(tokens[Constants.THREE]);
						if(currentPhotoNo<=1)
							currentPhotoNo=1;
					} else {
						currentPhotoNo=1;
					}
					if(tokens.length>=Constants.FOUR) {
						String tempGalleryId = tokens[Constants.FOUR];
						if(tempGalleryId.indexOf(".") > 0)
							tempGalleryId = tempGalleryId.substring(0, tempGalleryId.indexOf("."));
						galleryId=Integer.parseInt(tempGalleryId);
					}
				}
			}
			request.setAttribute("photoGalleryId",galleryId);
			request.setAttribute("currentPhotoNo",currentPhotoNo);


			RequestDispatcher dispatcher = request.getRequestDispatcher(Constants.GALLERY);
	        dispatcher.forward(request, response);
		} catch(Exception ex) {
			System.out.println("errorSource->"+pagePath+" -- errorReason->"+ex.toString());
			response.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
			response.setHeader("Location", Constants.PAGE_NOT_FOUND);
		}
	}
}