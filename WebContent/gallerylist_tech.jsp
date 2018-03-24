<!DOCTYPE html>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ page language="java" import="com.itgd.utils.Constants,com.itgd.parser.JsonParser,com.itgd.parser.JsonRec,com.itgd.parser.ItemRec,com.itgd.parser.ItemFeed,java.util.*" pageEncoding="UTF-8"%>
<%@include file="global.jsp"%>
<html lang="en">
<head>
<base href="<%=Constants.SITE_URL%>technology/"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Technology Title</title>
<meta name="description" content="Technolgy Gallery Desc"/>
<meta name="keywords" content="Technolgy Gallery Key"/>
<!-- Bootstrap -->
<link href="<%=Constants.SITE_URL%>technology/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="<%=Constants.SITE_URL%>technology/css/style.css">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,300italic,400italic,500,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen:400,700,300' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="<%=Constants.SITE_URL%>technology/css/swiper.min.css">
<link rel="stylesheet" href="<%=Constants.SITE_URL%>technology/css/font-awesome.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%@include file="navigation_topnav_tech.jsp"%>
<%
JsonParser ServiceObj=new JsonParser();
JsonRec myRecords=ServiceObj.getMeText(property.getProperty("galleryJSON").trim());
//out.println(property.getProperty("galleryJSON"));
List hp_second_top=myRecords.getItems().get(0).getFeed();
int hp_second_topSize=hp_second_top.size();

 List Gallery=myRecords.getItems().get(1).getFeed();
String GalleryName=myRecords.getItems().get(1).getName();
int GallerySize=Gallery.size(); 
%>
<div class="thumbarea hidden-xs">
  <div class="container">
    <div class="col-lg-12 col-md-12 col-sm-12">
      <div class="row">
        <div class="swiper-container" id="middle_cont">
          <div class="swiper-wrapper">
          <%
          for(int i=0;i<hp_second_topSize;i++) 
	   		{
  			  ItemFeed feeds=(ItemFeed)hp_second_top.get(i);       	
          %> 
           
            <div class="swiper-slide"><a href="<%=Constants.SITE_URL+"technology/"+feeds.getContent_url()%>">
              <div class="thumb_box"> <img src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>" alt="<%=feeds.getTitle() %>">
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
              <li><a href="<%=Constants.SITE_URL%>technology/">Home</a></li>
              <li>&gt;</li>
              <li>Photos</li>
            </ul>
          </div>
          <span class="clearfix"></span> <span class="clearfix"></span>
          <div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 gallery">
            <div class="row">
              <h2>More Galleries From Technology</h2>
              <div class="row">
              <%
	          for(int i=0;i<GallerySize;i++) 
		   		{
	  			  ItemFeed feeds=(ItemFeed)Gallery.get(i);       	
          		%> 
                 <div class="col-md-4 col-sm-4 col-xs-6">
                  <div class="gallery_box"> <a href="<%=feeds.getContent_url()%>">
                    <div class="img_area">                    
                    <img src="<%=Constants.GALLERY_IMG_PATH + feeds.getLarge_kicker_image() %>" alt="<%=feeds.getTitle()%>" width="305" height="182"> 
                    <em class="fa fa-camera"></em> 
                    <span><%                     
  if(feeds.getStrap_headline()!=null && feeds.getStrap_headline().toString().length()>0) {
	  out.print("#"+feeds.getStrap_headline());
	  } %></span>
                    </div>
                    <p><%=feeds.getTitle() %></p>
                    </a> </div>
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
    <%@include file="navigation_rightnav_tech.jsp"%>
  </div>
</div>
<%@include file="navigation_footernav_tech.jsp"%>
</body>
</html>