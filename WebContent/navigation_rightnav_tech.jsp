<%@ page language="java" import="com.itgd.utils.Constants,com.itgd.parser.JsonParser,com.itgd.parser.JsonRec,com.itgd.parser.ItemRec,com.itgd.parser.ItemFeed,java.util.*" pageEncoding="UTF-8"%>
<%
JsonParser RightPageJsonObj=new JsonParser();
//JsonRec RightPageData =RightPageJsonObj.callService(property.getProperty("servicePath").trim(),property.getProperty("rightTechJSON").trim());
JsonRec RightPageData =RightPageJsonObj.getMeText(property.getProperty("rightTechJSON").trim());
//JsonRec myRecordsRight =ServiceObjRight.callService(Constants.servicePath,Constants.defaultRightSideBarJson);
List reviewsRight=RightPageData.getItems().get(0).getFeed();
String reviewsNameRight=RightPageData.getItems().get(0).getName();
int reviewsSizeRight=reviewsRight.size();

List videosRight=RightPageData.getItems().get(1).getFeed();
int videosSizeRight=videosRight.size();
String videosNameRight=RightPageData.getItems().get(1).getName();

List techTipsRight=RightPageData.getItems().get(2).getFeed();
String techTipsNameRight=RightPageData.getItems().get(2).getName();
int techTipsSizeRight=techTipsRight.size();

List opinionRight=RightPageData.getItems().get(3).getFeed();
String opinionNameRight=RightPageData.getItems().get(3).getName();
int opinionSizeRight=opinionRight.size();
%>
<div class="col-md-4 col-xs-12">
        <div class="rightpanel">
          <div class="col-lg-12 col-md-12 col-sm-6">
            <div class="row">
              <div class="ad_banner"> <img src="<%=Constants.SITE_URL%>technology/images/ad_banner_rtl.jpg" alt=""> </div>
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
            <%@include file="most_read_tech.jsp"%>
            <div class="col-lg-12"><a href="#" class="morebtn">more</a></div>
          </div>
          <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12">
            <div class="row">
            <%
            for(int i=0;i<reviewsSizeRight;i++)
      		{
            	 ItemFeed feeds=(ItemFeed)reviewsRight.get(i);
            	 if(i==0)
            	 {
      		 %>
              <div class="heading_outer">
                <div class="heading">
                  <a href="<%=Constants.SITE_URL%>technology/<%=feeds.getCatcontenturl()%>"><h2><%=reviewsNameRight%></h2></a>
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
		                        <a href="<%=Constants.SITE_URL%>technology/<%=feeds.getContent_url() %>">
		                         <img src="<%=Constants.KICKER_IMG_PATH+feeds.getLarge_kicker_image() %>" alt="<%=feeds.getTitle()%>">
		                         <span><%=feeds.getShort_introtext() %></span>
		                         </a>
		                          </dev>
		                      </div>
		                <%
		             }
		                %>     
                      
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
              for(int i=0;i<4;i++)
         		{
        			  ItemFeed feeds=(ItemFeed)videosRight.get(i);
        			 %>
					
              
              <div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">
                <div class="row">
                  <ul>
                    <li><a href="<%=Constants.SITE_URL%>technology/<%=feeds.getContent_url()%>">
                    <img src="<%=Constants.KICKER_IMG_PATH+feeds.getKicker_image() %>" alt="<%=feeds.getTitle()%>"><span><%=feeds.getTitle() %></span></a></li>
                  </ul>
                </div>
              </div>
              <%
         		}
              %>
              </ul>
              <div class="col-lg-12"><a href="<%=Constants.SITE_URL%>technology/videos" class="morebtn">more</a></div>
              
            </div>
          </div>
          <div class="row">
            <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 mostread techtips">
              
              <%
              for(int i=0;i<techTipsSizeRight;i++)
       		{
      			  ItemFeed feeds=(ItemFeed)techTipsRight.get(i);
      			  if (i==0) {  %>
      				  
      				  <div class="heading_outer">
                <div class="heading">
                  <a href="<%=Constants.SITE_URL%>technology/<%=feeds.getCatcontenturl()%>"> <h2><%=techTipsNameRight %></h2></a>
                </div>
              </div>
              <ul>
      				  
      			  <%  } %>
                <li><a href="<%=Constants.SITE_URL%>technology/<%=feeds.getContent_url() %>"><img src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>" alt="<%=feeds.getTitle() %>">
                <span><%=feeds.getTitle() %></span></a></li>
              <%
              if (i == techTipsSizeRight-1 ) {
              %>  
              </ul>
            </div>
            <div class="col-lg-12"><a href="<%=feeds.getCatcontenturl()%>" class="morebtn">more</a></div>
             <%  } } %>  
           <div class="col-lg-12 col-md-12 col-sm-6 col-xs-12 mostread opinions">
              
               <%
               for(int i=0;i<opinionSizeRight;i++)
          		{
         			  ItemFeed feeds=(ItemFeed)opinionRight.get(i);
         			  if (i==0) { %>
         				  
         				  <div class="heading_outer">
                <div class="heading">
                  <a href="<%=Constants.SITE_URL%>technology/<%=feeds.getCatcontenturl()%>"> <h2><%//opinionName %> Opinions</h2></a>
                </div>
              </div>
              <ul>
              
         			  <% }
              %>
                <li><a href="<%=Constants.SITE_URL%>technology/<%=feeds.getContent_url() %>"><img src="<%=Constants.KICKER_IMG_PATH+feeds.getMobile_image() %>" alt="<%=feeds.getTitle() %>">
                  <h3><%=feeds.getByline() %></h3>
                  <span><%=feeds.getTitle() %></span></a></li>
             <%}%>
             
              </ul>
            </div>
          </div>
        </div>
      </div>