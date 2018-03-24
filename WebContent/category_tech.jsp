<%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %>
<%@ page language="java" import="com.itgd.utils.Constants,com.itgd.parser.JsonParser,com.itgd.parser.JsonRec,com.itgd.parser.ItemRec,com.itgd.parser.ItemFeed,java.util.*,com.itgd.utils.CommonFunctions" pageEncoding="UTF-8"%>
<%@include file="global.jsp"%>
<%
int currentCategoryListId = 141;
int currentCategoryListPageNo = 1;
String pagePath=null;
try{
if(request.getAttribute("currentCategoryListId")!=null)
	currentCategoryListId = (Integer)request.getAttribute("currentCategoryListId");
	
if(request.getAttribute("currentCategoryListPageNo")!=null)
	currentCategoryListPageNo = (Integer)request.getAttribute("currentCategoryListPageNo");
 pagePath=request.getAttribute("newPagePath").toString();
}catch(Exception e){
response.sendRedirect(Constants.PAGE_NOT_FOUND);
return;
}	
/* out.print("currentCategoryListId-->"+currentCategoryListId);
out.print("currentCategoryListPageNo-->"+currentCategoryListPageNo); 
*/
%>
<cache:cache key="<%=Constants.cacheURL+"technology/category/"+currentCategoryListPageNo+"/"+currentCategoryListId%>" scope="application " time="0" refresh="t" >
<%
String categoryPageJson=property.getProperty("categoryPageJson").trim()+currentCategoryListId+"/"+currentCategoryListPageNo+"/"+Constants.countDisplay+"/json";
//String categoryPageJson=Constants.defaultCategoryPageJson;
//http://feedstmp.intoday.in/inhousefeed/1.0/index.php/tech-it/category/895/1/10/json
//out.println(categoryPageJson);
JsonParser categoryPageJsonObj=new JsonParser();
JsonRec categoryPageData=categoryPageJsonObj.getMeText(categoryPageJson);
if(categoryPageData==null)
{
	categoryPageData=categoryPageJsonObj.getMeText(property.getProperty("categoryPageJson").trim());
}
String HotGadgets=categoryPageData.getItems().get(0).getFeed().get(0).getIntrotext();
List hp_second_top=categoryPageData.getItems().get(1).getFeed();
int hp_second_topSize=hp_second_top.size();
List categoryData=categoryPageData.getItems().get(2).getFeed();
String categoryName=categoryPageData.getItems().get(2).getName();
String categoryMetaTitle=categoryPageData.getItems().get(2).getFeed().get(0).getMetatitle();
String categoryMetadesc=categoryPageData.getItems().get(2).getFeed().get(0).getMetadesc();
String categoryMetakey=categoryPageData.getItems().get(2).getFeed().get(0).getMetakey();
int categoryDataSize=categoryData.size();
int totalRec=categoryPageData.getItems().get(2).getTotal_records();
//out.println(">>>>"+currentCategoryListPageNo);
%>
<!DOCTYPE html>	
<html lang="en">
<head>
<base href="<%=Constants.SITE_URL%>technology/"/>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><%=categoryMetaTitle %></title>
<meta name="description" content="<%=categoryMetadesc %>"/>
<meta name="keywords" content="<%=categoryMetakey %>"/>
<%@include file="resources.jsp"%>    
<%@include file="../ga.jsp"%>      
</head>
<body>
<%@include file="navigation_topnav_tech.jsp"%>
<%=HotGadgets %>
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
           
            <div class="swiper-slide">
            <a href="<%=Constants.SITE_URL+"technology/"+feeds.getContent_url()%>" title="<%=feeds.getTitle()%>">
              <div class="thumb_box"> <img src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>" alt="<%=feeds.getTitle()%>">
                <p><%=feeds.getTitle()%></p>
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
        <div class="leftpanel bordrtl">
          <div class="custom-breadcrumb">
            <ul>
              <li><a href="<%=Constants.SITE_URL%>technology/" title="Home">Home</a></li>
              <li>&gt;</li>
              <li><%=categoryName%></li>
            </ul>
          </div>
          <span class="clearfix"></span>
            <%
          		for(int i=0;i<categoryDataSize;i++)
           		{
          			  ItemFeed feeds=(ItemFeed)categoryData.get(i);
             %>
          <div class="col-lg-12 col-md-12 col-sm-12 listing">
            <div class="row">
              <a href="<%=Constants.SITE_URL+"technology/"+feeds.getContent_url()%>" title="<%=feeds.getTitle()%>" ><h2><%=feeds.getTitle()%></h2></a>
              <div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
                <div class="row"><a href="<%=Constants.SITE_URL+"technology/"+feeds.getContent_url()%>" title="<%=feeds.getTitle()%>"> 
                <img src="<%=Constants.KICKER_IMG_PATH+feeds.getLarge_kicker_image() %>" alt="<%=feeds.getTitle()%>">
                </a>
                </div>
              </div>
              <div class="col-lg-7 col-md-7 col-sm-7 col-xs-12 hidden-xs">
                <a href="<%=Constants.SITE_URL+"technology/"+feeds.getContent_url()%>" title="<%=feeds.getTitle()%>"><p><%=feeds.getIntrotext() %></p></a>
                <span><%=feeds.getCreateddate() %></span></div>
            </div>
          </div>
          <%
           		}
          %>
        </div>
      
        <div class="col-md-12 col-sm-12 col-lg-12 text-center">
          <ul class="pagination">
            <%
    		String dataUrl[]=pagePath.split("/");
    		int dataUrlLen=dataUrl.length-1;
    		 String storyIdUrl[]=dataUrl[dataUrlLen].split("\\.");
    		 String newUrl=Constants.SITE_URL;
    		 
    		 for(int i=0;i<dataUrlLen-1;i++)
    		 {
    			 //newUrl+=dataUrl[i]+"/";
    			 if(i>0)
    			 newUrl+=dataUrl[i]+"/";
    			 
    		 }
    		 int categoryId=Integer.parseInt(storyIdUrl[0]);
    		 int currentPage=Integer.parseInt(dataUrl[dataUrl.length-2]);
    		 int totalPage=totalRec/10;
    		
             for(int i=0;i<totalPage;i++)
            { 
               String newPage=newUrl+(i+1)+"/"+categoryId+"."+storyIdUrl[1];
               String newPrevPage=newUrl+(currentCategoryListPageNo-1)+"/"+categoryId+"."+storyIdUrl[1];
               String newNextPage=newUrl+(currentCategoryListPageNo+1)+"/"+categoryId+"."+storyIdUrl[1];
               if(currentCategoryListPageNo<=1)
               {
            	   newPrevPage="javascript:void(0);"; 
               }
               if((currentCategoryListPageNo+1)>totalPage)
               {
            	   newNextPage="javascript:void(0);"; 
               }
               if(i==0)
               {
            %>
             <li><a href="<%=newPrevPage%>">&laquo;</a></li>
            <% } %>
            <li><a href="<%=newPage%>"><%=i+1%></a>
            <% if(i==totalPage-1){ %>
            <li><a href="<%=newNextPage%>">&raquo;</a></li>
            <%
            	}
            } 
            %>
          </ul>
        </div>
       
      </div>
    </div>
    <%@include file="navigation_rightnav_tech.jsp"%>
  </div>
</div>
<%@include file="navigation_footernav_tech.jsp"%>
</body>
</html>
</cache:cache>