package com.itgd.sitemap.generator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itgd.dto.xmlcontent.XmlContentDTO;
import com.itgd.sitemap.generator.dao.XMLGeneratorDaoIfc;
import com.itgd.sitemap.generator.dao.XMLGeneratorDaoImpl;

/**
 * Servlet implementation class SitemapGenerator
 */
public class AutoSitemapGenerator extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoSitemapGenerator() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessSiteMapGenerator(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcessSiteMapGenerator(request, response);
	}

	private void doProcessSiteMapGenerator(HttpServletRequest request, HttpServletResponse response) {
		XMLGeneratorDaoIfc xmlGenerator = new XMLGeneratorDaoImpl();

		StringBuffer xmlWriteBuff = new StringBuffer();
		PrintWriter out = null;
		int sectionID = 230;	
		try {
			response.setContentType("text/xml");
			out = response.getWriter();
			xmlWriteBuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			xmlWriteBuff.append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\"  xmlns:news=\"http://www.google.com/schemas/sitemap-news/0.9\">\n");
			
			
			List<XmlContentDTO> xmlContentDtoList = xmlGenerator.processToGenerateSectionStoryXml(sectionID);
			if(xmlContentDtoList != null && xmlContentDtoList.size() > 0) {
				for(XmlContentDTO xmlContentDTO: xmlContentDtoList) {
					xmlWriteBuff.append("<url>\n");
					xmlWriteBuff.append("<loc>" + xmlContentDTO.getStorySefUrl() + "</loc>\n");
					xmlWriteBuff.append("<news:news>\n<news:publication><news:name>India Today</news:name><news:language>en</news:language></news:publication>\n");
					
					if(xmlContentDTO.getMetakey() != null)
						//xmlWriteBuff.append("<news:genres>PressRelease,Blog</news:genres>\n");
					
					xmlWriteBuff.append("<news:publication_date>" + xmlContentDTO.getPublicationDate() + "</news:publication_date>\n");
					xmlWriteBuff.append("<news:title>" + xmlContentDTO.getHeadline() + "</news:title>\n");
					
					if(xmlContentDTO.getNewsKeyword() != null)
						xmlWriteBuff.append("<news:keywords>" + xmlContentDTO.getMetakey() + "</news:keywords>\n");
					else
						xmlWriteBuff.append("<news:keywords>" + xmlContentDTO.getNewsKeyword() + "</news:keywords>\n");
						
					xmlWriteBuff.append("</news:news>\n");
					xmlWriteBuff.append("</url>\n");
				}
			}
			
			xmlWriteBuff.append("</urlset>");
			out.println(xmlWriteBuff.toString());
			
		} catch(Exception ex) {
			System.out.println("Found Exception in SitemapGenerator.doProcessSiteMapGenerator(request, response): " + ex.getMessage() + ", Caused By: " + ex.getCause());
		}
	}
}