<!DOCTYPE html>
<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ page language="java" import="java.io.*,com.itgd.utils.Constants,com.itgd.parser.JsonParser,com.itgd.parser.JsonRec,com.itgd.parser.ItemRec,com.itgd.parser.ItemFeed,java.util.*" pageEncoding="UTF-8"%>	
 <cache:cache key="http://indiatoday.intoday.in/technology/index.jsp" scope="application" time="0"> 
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Technology</title>
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/style.css">
<link href='https://fonts.googleapis.com/css?family=Roboto:400,300,300italic,400italic,500,700' rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen:400,700,300' rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/swiper.min.css">
<link rel="stylesheet" href="css/font-awesome.css">
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>          
<body>       
<%@include file="navigation_topnav_tech.jsp"%>
<%@include file="global.jsp"%>               
<%    
//String servicePath="http://localhost:8080/technology/rest/generate/jsonParser?path=";
//String homePageTechJSON = "http://10.10.25.59/feedsslim/1.0/index.php/tech-it/homesection/json";
JsonParser HomePageObj=new JsonParser();
//JsonRec myRecords=HomePageObj.callService(servicePath,homePageTechJSON);
JsonRec HomePageData=HomePageObj.getMeText(property.getProperty("homePageTechJSON").trim());
//JsonRec HomePageData=HomePageObj.callService(Constants.servicePath,Constants.homePageTechJSON);
//JsonRec myRecords=HomePageObj.callService(Constants.servicePath,Constants.homePageTechJSON);
//out.println("name="+myRecords.getItems().get(0).getName());
/* if(myRecords==null)
{ */ 
	//JsonRec myRecords=ServiceObj.callService(Constants.servicePath,Constants.homePageTechDefaultJSON);
/* } */
//out.println(property.getProperty("homePageTechJSON"));
String HotGadgets=HomePageData.getItems().get(0).getFeed().get(0).getIntrotext();
List hp_second_top=HomePageData.getItems().get(1).getFeed();
int hp_second_topSize=hp_second_top.size();

List hp_special=HomePageData.getItems().get(2).getFeed();
String hp_specialName=HomePageData.getItems().get(2).getName();
int hp_specialSize=hp_special.size();

     
List news=HomePageData.getItems().get(3).getFeed();
String newsName=HomePageData.getItems().get(3).getName();
int newsSize=news.size();

List reviews=HomePageData.getItems().get(4).getFeed();
String reviewsName=HomePageData.getItems().get(4).getName();
int reviewsSize=reviews.size();

List opinion=HomePageData.getItems().get(4).getFeed();
String opinionName=HomePageData.getItems().get(4).getName();
int opinionSize=opinion.size();

List galleries=HomePageData.getItems().get(5).getFeed();
String galleriesName=HomePageData.getItems().get(5).getName();
//int galleriesSize=galleries.size();

List techTips=HomePageData.getItems().get(6).getFeed();
String techTipsName=HomePageData.getItems().get(6).getName();
int techTipsSize=techTips.size();

List buyingGuides=HomePageData.getItems().get(7).getFeed();
String buyingGuideName=HomePageData.getItems().get(7).getName();
int buyingGuideSize=buyingGuides.size();

List videos=HomePageData.getItems().get(8).getFeed();
int videosSize=videos.size();
String videosName=HomePageData.getItems().get(8).getName();

List talkingPoints=HomePageData.getItems().get(9).getFeed();
List inDepth=HomePageData.getItems().get(10).getFeed();
int inDepthSize=inDepth.size();
String inDepthName =HomePageData.getItems().get(10).getName();
List inDepth1=HomePageData.getItems().get(11).getFeed();





/* Iterator<ItemFeed> itr=news.iterator();

while(itr.hasNext())
{
	ItemRec recs=itr.next();
	String itemName=recs.getName();
	List<ItemFeed> feeds=recs.getFeed();
	Iterator<ItemFeed> itrFeed=feeds.iterator(); 
	while(itrFeed.hasNext())
	{
		ItemFeed feedItem=(ItemFeed)itr.next();
		String htmlText=feedItem.getIntrotext();
		out.println("*********"+htmlText+"<br/>");
	 } 
}  */
try
{
%>
<%=HotGadgets %>
<%
}
catch(Exception e)
{
	out.println("Hot Gadgets not found");
	e.printStackTrace();
}
/*  Iterator itr=hp_second_top.iterator();
while(itr.hasNext())
{
	ItemFeed feeds=(ItemFeed)itr.next();
	
	out.println(feeds.getIntrotext()+"<br/>");
}   */

%>       

<!--<div class="sub_nav hidden-xs">
  <div class="container">
    <div class="row">
      <div class="col-md-12 col-lg-12 col-sm-12">
        <ul>
          <li><a href="#" class="active">HOT GADGETS</a></li>
          <li><a href="#">Yu Yuphoria</a></li>
          <li><a href="#">Micromax</a></li>
          <li><a href="#">Lenovo A6000 Plus</a></li>
          <li><a href="#">Motorola</a></li>
          <li><a href="#">OnePlus 2 launch</a></li>
        </ul>
      </div>
    </div>
  </div>
</div>-->
<div class="thumbarea hidden-xs">
  <div class="container">
    <div class="col-lg-12 col-md-12 col-sm-12">
      <div class="row">
        <div class="swiper-container" id="middle_cont">
          <div class="swiper-wrapper">
          <%
          try
          {
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
          }
          catch(Exception e)
          {
        	  out.println("Data not found");
        	  e.printStackTrace();
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
        <%
        try
        {
        for(int i=0;i<hp_special.size();i++) 
	   		{
			  ItemFeed feeds=(ItemFeed)hp_special.get(i); 
			  String hp_specialPath=null;
				 
			   if(feeds.getContent_url().contains("gallery")){
				  hp_specialPath=Constants.GALLERY_IMG_PATH;
			   }else{
				   hp_specialPath=Constants.STORY_IMG_PATH;
				   }
			  if(i==0)
			  {
        %> 
          <div class="col-md-9 col-sm-9 col-xs-12">
          
            <div class="row">
              <div class="topmainstory"><a href="<%=feeds.getContent_url()%>"> <img src="<%=hp_specialPath+feeds.getExtralarge_image() %>" alt="<%=feeds.getTitle() %>"> <span><%=feeds.getTitle() %></span></a> </div>
            </div>
            <%} 
			  if(i==1)
			  {
				 
            %>
            <div class="row">
              <div class="col-md-6 col-sm-6 col-xs-12 smallthumb">
                <div class="bord"> <a href="<%=feeds.getContent_url()%>"><img src="<%=hp_specialPath+feeds.getKicker_image() %>" alt="<%=feeds.getTitle() %>"> <span><%=feeds.getTitle() %></span></a> </div>
              </div>
             <% }
			  if(i==2)
			  {
			  %>
              <div class="col-md-6 col-sm-6 col-xs-12 smallthumb">
                <div class="bord1"><a href="<%=feeds.getContent_url()%>"> <img src="<%=hp_specialPath+feeds.getLarge_kicker_image()%>" alt="<%=feeds.getTitle() %>"> <span><%=feeds.getTitle() %></span></a> </div>
              </div>
            </div>
            <% }if(i==2) {%>
          </div>
          <div class="row hidden-xs">
            <div class="topliststory col-md-3 col-sm-3 col-xs-12">
              <ul>
           <%  } 
            if(i>2)
            {
           %>
                <li> <a href="<%=feeds.getContent_url() %>"><%=feeds.getTitle() %> </a></li>
                <% }}
        }
        catch(Exception e)
        {
        	out.println("Data not found");
        	e.printStackTrace();
        }
        %>
              </ul>
            </div>
          </div>      
              	<%
              	try
              	{
             		for(int i=0;i<4;i++)
             		{
            			  ItemFeed feeds=(ItemFeed)news.get(i);
            			  String newsIntroText=feeds.getIntrotext();
            			  
            			  if(newsIntroText!=null && newsIntroText.length()>90) {
            				  
            				  newsIntroText=newsIntroText.substring(0, 89)+"...";
            			  }
						  if (i==0) { %>
                          
                          <div class="heading_outer">
            <div class="heading">
             <a href="<%=feeds.getCatcontenturl()%>" class="morebtn hidden-xs" ><h2><%=newsName%></h2></a>
            </div>
          </div>
          <span class="clearfix"></span>
          <div class="newssec_outer">
            <div class="col-md-9 col-sm-9 col-xs-12 newssec">
              <ul>
						  
						   <% } %>
             	
            			  <li><a href="<%=feeds.getContent_url()%>"><img src="<%=Constants.KICKER_IMG_PATH+feeds.getKicker_image() %>" alt="<%=feeds.getTitle()%>">
                          <h3><%=feeds.getTitle()%></h3>
                          <p class="hidden-xs"><%=newsIntroText%></p>
                          </a>
                          <div class="social_area">
                          <%
                         if(feeds.getRelatedFeedsPhoto()!=null)
                         {
                          if(!feeds.getRelatedFeedsPhoto().get(0).getContentUrl().equals("") && !feeds.getRelatedFeedsPhoto().get(0).getContentUrl().equals(null))
                          {  
                          %>
                          
                           <a href="<%=feeds.getRelatedFeedsPhoto().get(0).getContentUrl() %>" class="camera"> 
                           <em class="fa fa-camera"></em> PHOTO </a>
                           <%
                          }
                         }
                          if(feeds.getRelatedFeedsVideo()!=null)
                          {
                          if(!feeds.getRelatedFeedsVideo().get(0).getContentUrl().equals("") && !feeds.getRelatedFeedsVideo().get(0).getContentUrl().equals(null))
                          {
                           %>
                           
							<a href="<%=feeds.getRelatedFeedsVideo().get(0).getContentUrl() %>" class="camera"> 
							<em class="fa fa-play-circle"></em> Video </a> 
						  <%
                          }
                          }
						  %>

</div>
                        </li>
          		 <%  }
              	}
              	catch(Exception e)
              	{
              		out.println("Data not found");
              		e.printStackTrace();
              	}
             		%>		  
              </ul>
            </div>       
            
            <div class="row hidden-xs"><div class="newsstory col-md-3 col-sm-3 col-xs-12">
            
            <%
            try
            {
             		for(int i=4;i<newsSize;i++)
             		{
            			  ItemFeed feeds=(ItemFeed)news.get(i);
            			  if(i==4)
            			  {
             	%>
                  <ul>
                  <% } %>
                  <li> <a href="<%=feeds.getContent_url()%>"> <img src="<%=Constants.KICKER_IMG_PATH+feeds.getKicker_image() %>" alt="<%=feeds.getTitle()%>">
                    <p><%=feeds.getTitle()%></p>
                    </a></li>
                  <%  if (i==newsSize-1) { %>
                            </ul>
                <a href="<%=feeds.getCatcontenturl()%>" class="morebtn hidden-xs">more</a> </div>                
                <% } }
            }
            catch(Exception e)
            {
            	out.println("Data not found");
            	e.printStackTrace();
            }
             		%>
            </div>
          </div>
           <div class="heading_outer">
            <div class="heading">
              <a href="<%=Constants.SITE_URL%>technology/galleries"><h2>Photos</h2></a>
            </div>
          </div>
          <span class="clearfix"></span>
          <div class="row photo">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
              <div class="row">
          <%
          String galleryContentUrl=null;
          try
          {
          for(int i=0;i<4;i++) 
  	   		{
  			  ItemFeed feeds=(ItemFeed)galleries.get(i);
  			galleryContentUrl=feeds.getCatcontenturl();
  			  
        
              %>
                <div class="col-lg-3 col-md-3 col-sm-3 col-xs-6">
                <a href="<%=feeds.getContent_url() %>">
<img src="<%=Constants.GALLERY_IMG_PATH+feeds.getKicker_image() %>" alt="<%=feeds.getTitle() %>"> 
<span><%=feeds.getTitle() %></span> 
</a>
</div>
             <% }
          }
          catch(Exception e)
          {
        	  out.println("Data not found");
        	  e.printStackTrace();
          }
          %>
              <div class="col-md-12"><a href="<%=Constants.SITE_URL%>technology/gallerylist.jsp" class="morebtn">more</a></div>
              </div>
            </div>
          </div>
                     
            <%
            try
            {
          		for(int i=0;i<4;i++)
           		{
          			  ItemFeed feeds=(ItemFeed)buyingGuides.get(i);
          			  if (i==0) { %>
          				  
          				<div class="heading_outer">
            <div class="heading">
              <a href="<%=feeds.getCatcontenturl() %>" class="morebtn"><h2><%=buyingGuideName %></h2></a>
            </div>
          </div>
          <span class="clearfix"></span>
          <div class="row buy">
            <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">   
          				  
          			 <%  }
            	 
            %>
              <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 buybox">
              <a href="<%=feeds.getContent_url() %>">
              <img src="<%=Constants.KICKER_IMG_PATH+feeds.getKicker_image() %>" alt="<%=feeds.getTitle() %>">
                <h3><strong><%=feeds.getByline() %></strong>
                <%=feeds.getTitle() %></h3>
                </a> </div>
             <%
             
             
             if(3==i)
            	 {
             %>
              <div class="col-md-12"><a href="<%=feeds.getCatcontenturl() %>" class="morebtn">more</a></div>
              <%
            	 }
              
             
             }
            }
            catch(Exception e)
            {
            	out.println("Data not found");
            	e.printStackTrace();
            }
              %>
            </div>
          </div>
         
                <%
                try
                {
                for(int i=0;i<5;i++)
         		{
        			  ItemFeed feeds=(ItemFeed)inDepth.get(i);
        			  
        			  if (i == 0) { %>
        				  
        				  
        				   <div class="heading_outer">
            <div class="heading">
              <a href="<%=feeds.getCatcontenturl()%>" ><h2><%=inDepthName%></h2></a>
            </div>
          </div>
          <span class="clearfix"></span>
          <div class="indepth">
          <%
           ItemFeed feedsu=(ItemFeed)inDepth.get(0);
          %>
          
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
            <a href="<%=feeds.getContent_url() %>"> 
            <img src="<%=Constants.KICKER_IMG_PATH+feedsu.getLarge_kicker_image() %>" alt="<%=feedsu.getTitle() %>"> 
            <span><%=feedsu.getTitle() %></span>
            </a>
             </div>
           
            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
              <ul>
        				  
        			  <% } 
        			  
        			  
        			  
                
                %>
                <li>
                <a href="<%=feeds.getContent_url() %>">
                <img src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>" alt="<%=feeds.getTitle() %>">
                  <p><%=feeds.getTitle() %></p>
                  </a>
                </li>
                
                 
                
                <%
         		}
                }
                catch(Exception e)
                {
                	out.println("Data not found");
                	e.printStackTrace();
                }
                %>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row">
      <div class="col-md-4 col-xs-12">
        <div class="rightpanel">
          <div class="col-lg-12 col-md-12 col-sm-6">
            <div class="row">
              <div class="ad_banner"> <img src="images/ad_banner_rtl.jpg" alt=""> </div>
            </div>
            <div class="row subscribe">
              <div class="col-md-8 col-md-12 col-sm-8 col-sm-12">
                <input type="text" class="form-control col-md-4" value="" placeholder="Enter email for our newsletter" name="">
              </div>
              <div class="col-md-4 col-sm-4 col-sm-12 pull-right">
                <input type="button" value="Subscribe" name="" class="btn btn-success pull-right">
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 mostread">
              <div class="heading_outer">
                <div class="heading">
                  <h2>Most Read</h2>
                </div>
              </div>
              <ul>
                <li><a href="#"><img src="images/most_read.jpg" alt=""><span>Windows 10 privacy settings 
                  you didn't know about</span></a></li>
                <li><a href="#"><img src="images/most_read.jpg" alt=""><span>Windows 10 privacy settings 
                  you didn't know about</span></a></li>
                <li><a href="#"><img src="images/most_read.jpg" alt=""><span>Windows 10 privacy settings 
                  you didn't know about</span></a></li>
                <li><a href="#"><img src="images/most_read.jpg" alt=""><span>Windows 10 privacy settings 
                  you didn't know about</span></a></li>
              </ul>
            </div>
            <div class="col-lg-12"><a href="#" class="morebtn">more</a></div>
          </div>
          <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12">
            <div class="row">
            <%
            try
            {
            for(int i=0;i<reviewsSize;i++)
      		{
            	 ItemFeed feeds=(ItemFeed)reviews.get(i);
            	 if(i==0)
            	 {
      		 %>
              <div class="heading_outer">
                <div class="heading">
                  <a href="<%=feeds.getCatcontenturl() %>"><h2><%=reviewsName %></h2></a>
                </div>
              </div>
              <div class="review_outer" style="padding-top:0px !important;">
                <div class="swiper_outer">
                  <div class="swiper-container" id="right_swp" style="padding-top:30px;">
                    <div class="swiper-wrapper">
                     <%
            	   }
            	  %>
		                      <div class="swiper-slide">
		                        <dev class="review">
		                        <a href="<%=feeds.getContent_url() %>">
		                         <img src="<%=Constants.KICKER_IMG_PATH+feeds.getLarge_kicker_image() %>" alt="<%=feeds.getTitle()%>">
		                         <span><%=feeds.getShort_introtext() %></span>
		                         </a>
		                          </dev>
		                      </div>
		                <%
		             }
            }
            catch(Exception e)
            {
            	out.println("Data not found");
            	e.printStackTrace();
            }  %>     
                      
                    </div>
                    <div class="swiper-pagination rgt_pag"></div>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 mostread video">
              <div class="heading_outer">
					  <div class="heading">
					    <a href="<%=Constants.SITE_URL%>technology/videos"> <h2>VIDEOS</h2></a>
					  </div>
					</div>
              <%
              try
              {
              for(int i=0;i<4;i++)
         		{
        			  ItemFeed feeds=(ItemFeed)videos.get(i);
        			 %>
					
              
              <div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">
                <div class="row">
                  <ul>
                    <li><a href="<%=feeds.getContent_url()%>"><img src="<%=Constants.KICKER_IMG_PATH+feeds.getKicker_image() %>" alt="<%=feeds.getTitle()%>"><span><%=feeds.getTitle() %></span></a></li>
                  </ul>
                </div>
              </div>
              <%
         		}
              }
              catch(Exception e)
              {
            	  out.println("Data not found");
            	  e.printStackTrace();
              }
              %>
              </ul>
              <div class="col-lg-12"><a href="<%=Constants.SITE_URL%>technology/videos" class="morebtn">more</a></div>
              
            </div>
          </div>
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 mostread techtips">
              
              <%
              try
              {
              for(int i=0;i<techTipsSize;i++)
       		{
      			  ItemFeed feeds=(ItemFeed)techTips.get(i);
      			  if (i==0) {  %>
      				  
      				  <div class="heading_outer">
                <div class="heading">
                  <a href="<%=feeds.getCatcontenturl()%>"> <h2><%=techTipsName %></h2></a>
                </div>
              </div>
              <ul>
      				  
      			  <%  } %>
                <li><a href="<%=feeds.getContent_url() %>"><img src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>" alt="<%=feeds.getTitle() %>">
                <span><%=feeds.getTitle() %></span></a></li>
              <%
              if (i == techTipsSize-1 ) {
              %>  
              </ul>
            </div>
            <div class="col-lg-12"><a href="<%=feeds.getCatcontenturl()%>" class="morebtn">more</a></div>
             <%  } }
              }
              catch(Exception e)
              {
            	  out.println("Data not found");
            	  e.printStackTrace();
              }
              %>  
            
            <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 mostread opinions">
              
               <%
               try
               {
               for(int i=0;i<opinionSize;i++)
          		{
         			  ItemFeed feeds=(ItemFeed)opinion.get(i);
         			  if (i==0) { %>
         				  
         				  <div class="heading_outer">
                <div class="heading">
                  <a href="<%=feeds.getCatcontenturl()%>"> <h2><%//opinionName %> Opinions</h2></a>
                </div>
              </div>
              <ul>
              
         			  <% }
              %>
                <li><a href="<%=feeds.getContent_url() %>"><img src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>" alt="<%=feeds.getTitle() %>">
                  <h3><%=feeds.getByline() %></h3>
                  <span><%=feeds.getTitle() %></span></a></li>
             <%}
               }
               catch(Exception e)
               {
            	   out.println("Data not found");
            	   e.printStackTrace();
               }
             %>
             
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="navigation_footernav_tech.jsp"%>
</body>
 </html>
</cache:cache> 