<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ page language="java" import="com.itgd.utils.Constants,com.itgd.parser.JsonParser,com.itgd.parser.JsonRec,com.itgd.parser.ItemRec,com.itgd.parser.ItemFeed,java.util.*,com.itgd.utils.CommonFunctions" pageEncoding="UTF-8"%>
<%@include file="global.jsp"%>
<%-- <cache:cache key="<%=Constants.cacheURL+"technology/videos/"%>" scope="application " time="0" refresh="t" > --%>
<%
//http://feedstmp.intoday.in/inhousefeed/1.0/index.php/tech-it/videosectionlist/0/5/json
String videoUrlJson=property.getProperty("videoPageJSON");
int newPage=0;
String pagePath=request.getRequestURI();
if(!pagePath.contains(".html"))
{
	pagePath=Constants.SITE_URL+"technology/videos/1.html";
}
String dataUrl[]=pagePath.split("/");
int dataUrlLen=dataUrl.length-1;
String storyIdUrl[]=dataUrl[dataUrlLen].split("\\.");
if(pagePath.contains(".html"))
{
int currentPage=Integer.parseInt(storyIdUrl[0]);
if(storyIdUrl[0]!=null) {
	 newPage=Integer.parseInt(storyIdUrl[0])-1;
	 if(Integer.parseInt(storyIdUrl[0])==1)
	 {
	 	newPage=0;
	 }
	 else
	 {
	 	newPage=(newPage*12)+1;
	 }
	videoUrlJson+=newPage+"/12/json";
} else {
	videoUrlJson+="0/12/json";
}
}
else
{
videoUrlJson+="0/12/json";
}
//out.println(videoUrlJson);
%>
<!--   videoUrlJson+++ <%//videoUrlJson%> -->
<% 
JsonParser galleryPageObj=new JsonParser();
JsonRec videoPageData=galleryPageObj.getMeText(videoUrlJson);
List hp_second_top=videoPageData.getItems().get(1).getFeed();
int hp_second_topSize=hp_second_top.size();
List videoGallery=videoPageData.getItems().get(2).getFeed();
String videoGalleryName=videoPageData.getItems().get(2).getName();
int videoGallerySize=videoGallery.size();
int totalRec=videoPageData.getItems().get(2).getTotal_records();
%>
<!--totalRec<%//=totalRec%>-->
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=Constants.SITE_URL%>technology/"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Technology Vidoes</title>
<%@include file="resources.jsp"%>    
<%@include file="../ga.jsp"%>      
</head>
<body>
<%@include file="navigation_topnav_tech.jsp"%>
<div class="thumbarea hidden-xs">
  <div class="container">
    <div class="col-lg-12 col-md-12 col-sm-12">
      <div class="row">
        <div class="swiper-container" id="middle_cont">
          <div class="swiper-wrapper">
             <% for(int i=0;i<hp_second_topSize;i++)  {
  			  ItemFeed feeds=(ItemFeed)hp_second_top.get(i);       	
          %> 
           
            <div class="swiper-slide">
 <a href="<%=Constants.SITE_URL+"technology/"+feeds.getContent_url()%>" title="<%=feeds.getTitle()%>">
              <div class="thumb_box"> <img src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>" alt="<%=feeds.getTitle()%>">
                <p><%=feeds.getTitle() %></p>
              </div>
              </a>
              </div>
              <%
             }
              %>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="maincontainer clearfix">
  <div class="container">
    <div class="col-md-8 col-xs-12">
      <div class="row margrtl">
        <div class="leftpanel">
          <div class="custom-breadcrumb">
            <ul>
              <li><a href="<%=Constants.SITE_URL%>technology/" title="Home">Home</a></li>
              <li>&gt;</li>
              <li><%=videoGalleryName %></li>
            </ul>
          </div>
          <span class="clearfix"></span>
          <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 gallery">
            <div class="row">
              <h2>More Video From Technology</h2>
              <div class="row">
              <%
	          for(int i=0;i<videoGallerySize;i++) 
		   		{
	  			  ItemFeed feeds=(ItemFeed)videoGallery.get(i);       	
          		%> 
          		
                <div class="col-md-4 col-sm-4 col-xs-6">
                  <div class="gallery_box"> <a href="<%=Constants.SITE_URL+"technology/"+feeds.getContent_url()%>" title="<%=feeds.getTitle()%>">
                    <div class="img_area"> <img src="<%=Constants.KICKER_IMG_PATH + feeds.getLarge_kicker_image() %>" alt="<%=feeds.getTitle()%>"> <em class="fa fa-play-circle"></em> </div>
                    <p><%=feeds.getTitle() %></p>
                    </a> </div>
                </div>
                <%
		   		}
                %>
              </div>
            </div>
          </div>
          <div class="col-md-2 col-sm-2  col-lg-2"></div>
          <div class="col-md-8 col-sm-8  col-lg-8">
            <ul class="pagination">
              <%
              int getPageNo=1;
              getPageNo=Integer.parseInt(storyIdUrl[0]);
              int totalPage1= totalRec/12;
			  int remain=totalRec%12;
			   int totalPage=0;
			  if(remain>0)
			  totalPage=totalPage1+1;
              for(int i=1;i<=totalPage;i++)
              {
            	  String url=Constants.SITE_URL+"technology/"+"videos/"+i+".html";
				  if(i==1)
				  {
					  url=Constants.SITE_URL+"technology/videos";
				  }
            	  if(i==getPageNo)
				  url="javascript:void(0)";
            	  String nextUrl=Constants.SITE_URL+"technology/"+"videos/"+(getPageNo+1)+".html";
            	  if(i==1)
            	  {
            		  nextUrl=Constants.SITE_URL+"technology/videos";
            	  }
            	  if(getPageNo==totalPage)
            	  {
            		  nextUrl="javascript:void(0);";
            	  }
            	  String prevUrl=Constants.SITE_URL+"technology/"+"videos/"+(getPageNo-1)+".html";
            	   if(getPageNo==2)
            	  {
            		   prevUrl=Constants.SITE_URL+"technology/videos";
            	  }
            	  if(getPageNo==1)
            	  {
            		  prevUrl="javascript:void(0);";
            	  }
            	  if(i==1)
            	  {
              %>
              <li><a href="<%=prevUrl%>">&laquo;</a></li>
              <%
            	  }
              %>
              <li><a href="<%=url%>"><%=i %></a></li>
              <%
	              if(i==totalPage)
	        	  {
	           %>
	            <li><a href="<%=nextUrl%>">&raquo;</a></li>
	           <%
            	  }
              }
              %>
            </ul>
          </div>
          <div class="col-md-2 col-sm-2  col-lg-2"></div>
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