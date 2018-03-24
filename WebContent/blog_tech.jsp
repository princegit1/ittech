<%-- <%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %> --%>
<%@ page language="java" import="com.itgd.utils.Constants,com.itgd.parser.JsonParser,com.itgd.parser.JsonRec,com.itgd.parser.ItemRec,com.itgd.parser.ItemFeed,java.util.*,com.itgd.parser.Bitrate,com.itgd.utils.CommonFunctions,java.math.BigInteger" pageEncoding="UTF-8"%>
<%@include file="global.jsp"%>
<%
int BlogCategoryId = 141;
int currentPageNo = 1;
String pagePath=null;
try{
if(request.getAttribute("BlogCategoryId")!=null)
	BlogCategoryId = (Integer)request.getAttribute("BlogCategoryId");
if(request.getAttribute("currentCategoryListPageNo")!=null)
	currentPageNo = (Integer)request.getAttribute("currentPageNo");
 pagePath=request.getAttribute("newPagePath").toString();
}catch(Exception e){
response.sendRedirect(Constants.PAGE_NOT_FOUND);
return;
} %>
<%
JsonParser BlogListJsonObj=new JsonParser();
JsonRec topContentsData=BlogListJsonObj.getMeText(property.getProperty("topBarJSON").trim());

String HotGadgets=topContentsData.getItems().get(0).getFeed().get(0).getIntrotext();
List hp_second_top=topContentsData.getItems().get(1).getFeed();
int hp_second_topSize=hp_second_top.size(); 

String blogListJson="http://feedstmp.intoday.in/inhousefeed/1.0/index.php/tech-it/blogs_comments/"+BlogCategoryId+"/0/20/json";
JsonRec blogPageData=BlogListJsonObj.getMeText(blogListJson);
ItemRec blogItems=blogPageData.getItems().get(0);
int totalRecords=blogItems.getTotal_records();
String blogTitle=blogItems.getBlogTitle();
String firstWord=null;
 if(blogTitle!=null && blogTitle.contains(" ")){
	   firstWord= blogTitle.substring(0, blogTitle.indexOf(" ")); 
	   blogTitle=blogTitle.replace(firstWord,"");
	} 
String blogDesc=blogItems.getBlogDesc();
List BlogFeeds=blogPageData.getItems().get(0).getFeed();
int BlogListSize=BlogFeeds.size();

%>

<%-- <cache:cache key="<%=Constants.cacheURL+"technology/blog_tech.jsp/"%>" scope="application " time="0" refresh="t" > --%>

<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=Constants.SITE_URL%>technology/"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Technology</title>
<%@include file="resources.jsp"%>     
<%@include file="../ga.jsp"%>     
</head>
<body>
<%@include file="navigation_topnav_tech.jsp"%>

<%
 try
{ 
%>
<%=HotGadgets %>
<%
}
catch(Exception e)
{
	//out.println("Hot Gadgets not found");
	e.printStackTrace();
}
%>
<div class="thumbarea">
  <div class="container">
    <div class="col-lg-12 col-md-12 col-sm-12">
      <div class="row liveblog">
        <div class="swiper-container hidden-xs"  id="middle_cont">
          <div class="swiper-wrapper">
           <%
          try
          {
          for(int i=0;i<hp_second_topSize;i++) 
	   		{
  			  ItemFeed feeds=(ItemFeed)hp_second_top.get(i);       	
          %>
<div class="swiper-slide"><a
	href="<%=Constants.SITE_URL+"technology/"+feeds.getContent_url()%>" title="<%=feeds.getTitle()%>">
<div class="thumb_box"><img
	src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>"
	alt="<%=feeds.getTitle()%>">
<p><%=feeds.getTitle() %></p>
</div>
</a></div>
<%
             }
          }
          catch(Exception e)
          {
        	  //out.println("Data not found");
        	  e.printStackTrace();
          }
              %>
          </div>
        </div>
        
        <h1><strong><%=firstWord %></strong><%=blogTitle %></h1>
        <p><%=blogDesc %></p>
      </div>
    </div>
  </div>
</div>





<div class="maincontainer clearfix margtop">
  <div class="container">
    <div class="col-md-8 col-xs-12">
      <div class="row margrtl">
        <div class="leftpanel bordrtl">
        <%     	 
        	for(int i=0;i<BlogListSize;i++) 
		 	{
	  			ItemFeed feeds=(ItemFeed)BlogFeeds.get(i);
        %> 
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
        <div class="row">
        <div class="blog">
        <span><%=feeds.getDate_time() %></span>
        <p><%=feeds.getComments() %></p>
        </div>
        </div>
        </div>
        <%} %>
        </div>
        
      </div>
    </div>
    <%@include file="navigation_rightnav_tech.jsp"%>
    
  </div>
</div>

<%@include file="navigation_footernav_tech.jsp"%>
</body>
</html>
<%-- </cache:cache> --%>