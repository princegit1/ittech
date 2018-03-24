<%@ page language="java" pageEncoding="ISO-8859-1"%>
<%@page import="java.util.ArrayList, com.itgd.dto.ContentTemplateDTO, com.itgd.helper.ContentTemplateHelper,com.itgd.dto.LatestContentDTO, com.itgd.utils.CommonFunctions, com.itgd.utils.Constants"%>
<%
 String TipsTricksID =request.getParameter("TipsTricksID");
 int Tips_TricksID = 992;
 if(TipsTricksID !=null ) { Tips_TricksID=Integer.parseInt(TipsTricksID.trim()); }
%>
<%-- <%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %> --%>
<%-- <cache:cache key="<%=Constants.SITE_URL+"auto/chunk_home_tipsTricks.jsp"%>" scope="application" time="600" refresh="t" > --%>


<% ArrayList tfAL = (ArrayList) ContentTemplateHelper.latestStory("category", Tips_TricksID, 1, 8, "0", "");               
      if (tfAL != null && tfAL.size() > 0) {
	for (int i = 0; i < tfAL.size(); i++) {
		ContentTemplateDTO tfDTO = (ContentTemplateDTO) tfAL.get(i);
	 if(i==0) { %>
   <div class="tips_and_tricks">
   
   
      <a href="/auto/<%=tfDTO.getCategoryURL()%>" title="<%=tfDTO.getCategoryURL()%>"><h2 class="heading">Tips and Tricks<%//tfDTO.getCategoryTitle()%></h2></a>
      <div class="tips_and_tricks_cont">
        <ul class="tips_scr">
        
        <% } %>
        
          <li>
            <div class="tips_con">
              <div class="tips_hd"><a href="/auto/<%=tfDTO.getContentURL()%>"><%=tfDTO.getTitle()%></a></div>
              <div class="tips_des"><%=tfDTO.getShortDescription()%></div>
            </div>
          </li>
          
         <%  } }%> 
        </ul>
      </div>
    </div>       
<%-- </cache:cache> --%>