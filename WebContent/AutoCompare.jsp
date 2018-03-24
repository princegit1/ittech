<%-- <%@ taglib uri="http://www.opensymphony.com/oscache" prefix="cache" %> --%>
<%@page language="java" import="java.sql.*,java.util.*,com.itgd.utils.CarData, com.itgd.utils.CommonFunctions, com.itgd.utils.Constants, com.itgd.dto.ContentTemplateDTO, com.itgd.helper.ContentTemplateHelper,com.itgd.education.utils.DbConnection" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
  <!doctype html>
  <html>
  <head>
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title><%//=pageMetaTitle%></title>
  <meta name="description" content="<%//=pageMetaKeyword%>" />
  <meta name="news_keywords"  content="<%//=pageMetaDescription%>" />
  <script type="text/javascript">
	<%-- var primaryCategoryJS = '<%=sectionId%>';
	 if(primaryCategoryJS == "230") {
			
	} else if(primaryCategoryJS == "204") {
			window.location.href= "http://indiatoday.intoday.in/education/<%=CurrentCategoryURL%>";
	} else if(primaryCategoryJS == "229") {
			window.location.href= "http://indiatoday.intoday.in/technology/<%=CurrentCategoryURL%>";
	} else if(primaryCategoryJS == "340") {
			window.location.href= "http://indiatoday.intoday.in/money/<%=CurrentCategoryURL%>";
	}else if(primaryCategoryJS == "86") {
			window.location.href= "http://indiatoday.intoday.in/videos";
	}else if(primaryCategoryJS == "87") {
			window.location.href= "http://indiatoday.intoday.in/programmes";
	}else{
window.location.href= "<%="http://indiatoday.intoday.in/"+CurrentCategoryURL%>";

} --%>
</script>
  <link href='http://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
  <link href="<%=Constants.SITE_URL %>technology/auto/css/style.css" rel='stylesheet' type='text/css'>
  <link href="<%=Constants.SITE_URL %>technology/auto/css/ipad_auto.css" rel='stylesheet' type='text/css'>
<link href="../auto/css/mobile_auto.css" rel='stylesheet' type='text/css'>
  <script src="http://media2.intoday.in/indiatoday/js/jquery.min.1.8.2.js" type="text/javascript"></script>
  <script type="text/javascript" language="javascript" src="http://media2.intoday.in/indiatoday/js/ajaxinclude.js"></script>  
  <%@include file="../ga.jsp"%>
  </head>
  <body>
  <div id="top_header">
    <%@include file="header.jsp" %>
  </div>
  <div class="clearfix"></div>
  <%@include file="top-navigation.jsp" %>
  <div class="wrapper">
    <%@include file="compareAuto.jsp" %>
  </div>
  <div class="wrapper">
    <div class="left-panel">
   <div class="auto-master-section bg-white">
	
	<div id="compare-section">
  <div class="compare-section-two">
    <%-- <div class="compare-left"> <img src="<%=Constants.SITE_URL %>technology/auto/images/left-img.jpg" alt="" title="" /> </div>
    <div class="vs_text"><img src="<%=Constants.SITE_URL %>technology/auto/images/big-vs.jpg" alt="" title="" /></div>
    <!--<div class="vrs flt"><img src="big-vs.jpg" alt="" title="" /></div> -->
    <div class="compare-right"> <img src="<%=Constants.SITE_URL %>technology/auto/images/right-img.jpg" alt="" title="" /> </div> --%>
    <div class="clr"></div>
    <div class="bm-break-point">
      <div class="lft ltext">
        <h3><%
        if(request.getParameter("brandId")!=null)
        out.print(request.getParameter("brandId")); %></h3>
        <div class="rating_icon"><img src="<%=Constants.SITE_URL %>technology/auto/images/rating.jpg" alt="" title="" /></div>
      </div>
      <div class="arrow_bottom"><img src="<%=Constants.SITE_URL %>technology/auto/images/v-img.jpg" alt="" title="" /></div>
      <div class="flr rtext">
        <h3><% if(request.getParameter("brandId1")!=null)
        	 out.print(request.getParameter("brandId1"));
        	%></h3>
        <div class="rating_icon"><img src="<%=Constants.SITE_URL %>technology/auto/images/rating.jpg" alt="" title="" /></div>
      </div>
    </div>
    <div class="clr"></div>
    <div class="grybg_background">
    <c:choose>
    <c:when test="${fn:length(secondBrandDet)==0}">
      <div class="left-table lft" style="width:100%;">
    </c:when>
    <c:otherwise>
    <div class="left-table lft">
    </c:otherwise>
    </c:choose>
        <c:choose>
      <c:when test="${fn:length(firstBrandDet)>0}">
       <c:forEach var="firstBrand" items="${firstBrandDet}" >
        <table width="100%" cellpadding="5" cellspacing="5" border="0">
          <tr>
            <td>Brand Name</td>
            <td>${firstBrand.brand}</td>
          </tr>
         <tr>
            <td>Modal </td>
            <td>${firstBrand.vehicle}</td>
          </tr>
          <tr>
            <td>Delhi Price </td>
            <td><span>&#8377 ${firstBrand.delhi_price}</span></td>
          </tr>
          <tr>
            <td>Mumbai Price</td>
            <td><span>&#8377 ${firstBrand.mumbai_price}</span></td>
          </tr>
          <tr>
            <td>Kolkata Price</td>
            <td><span>&#8377 ${firstBrand.kolkata_price}</span></td>
          </tr>
          <tr>
            <td>Banglore price</td>
            <td><span>&#8377 ${firstBrand.bangalore_price}</span></td>
          </tr>
          <tr>
            <td>Chennai Price</td>
            <td><span>&#8377 ${firstBrand.chennai_price}</span></td>
          </tr>
          <tr>
            <td>Body Type</td>
            <td>${firstBrand.bodytype}</td>
          </tr>
          <tr>
            <td nowrap="nowrap">Engine Displacement</td>
            <td>${firstBrand.engineDisplacement}</td>
          </tr>
          <tr>
            <td>Cylinder</td>
            <td>${firstBrand.cylinders}</td>
          </tr>
          <tr>
            <td>Power</td>
            <td>${firstBrand.power} </td>
          </tr>
          <tr>
            <td>Torque</td>
            <td>${firstBrand.torque}</td>
          </tr> 
          <tr>
            <td>Gears</td>
            <td>${firstBrand.gears}</td>
          </tr>
          <tr>
            <td>Gear Type</td>
            <td>${firstBrand.gearType}</td>
          </tr>
          <tr>
            <td>Fuel Type</td>
            <td>${firstBrand.fuelType}</td>
          </tr>
          <tr>
            <td>Running Km.</td>
            <td>${firstBrand.runningkm}</td>
          </tr>
          <tr>
            <td>ARAI Claimed</td>
            <td>${firstBrand.arai_Claimed}</td>
          </tr>
         <tr>
            <td>Tested</td>
            <td>${firstBrand.tested}</td>
          </tr>
          <tr>
            <td>AC</td>
            <td>${firstBrand.ac}</td>
          </tr>
          <tr>
            <td>Climate Control</td>
            <td>${firstBrand.climate_Control}</td>
          </tr> 
          <tr>
            <td>Adjustable Steering Rake</td>
            <td>${firstBrand.adjustable_Steering_rake}</td>
          </tr>
          <tr>
            <td>Adjustable Steering Reach</td>
            <td>${firstBrand.adjustable_Steering_Reach}</td>
          </tr>
          <tr>
            <td>Steering mounted controls</td>
            <td>${firstBrand.steering_mounted_controls}</td>
          </tr>
          <tr>
            <td>Cruise Control</td>
            <td>${firstBrand.cruise_Control}</td>
          </tr>
          <tr>
            <td>Central Lock</td>
            <td>${firstBrand.centrallock}</td>
          </tr>
          <tr>
            <td>Remote Lock</td>
            <td>${firstBrand.remotelock}</td>
          </tr>
          <tr>
            <td>Keyless</td>
            <td>${firstBrand.keyless}</td>
          </tr>
          <tr>
            <td>Power Windows</td>
            <td>${firstBrand.power_windows}</td>
          </tr>
          <tr>
            <td>Driver seat adjust</td>
            <td>${firstBrand.driver_seat_adjust}</td>
          </tr>
          <tr>
            <td>Parking sensor</td>
            <td>${firstBrand.parking_sensor}</td>
          </tr>
          <tr>
            <td>Parking camera</td>
            <td>${firstBrand.parking_camera}</td>
          </tr>
          <tr>
            <td>Split foalding</td>
            <td>${firstBrand.split_foalding}</td>
          </tr>
          <tr>
            <td>Multi function</td>
            <td>${firstBrand.multi_function}</td>
          </tr>
          <tr>
            <td>Door mirror</td>
            <td>${firstBrand.door_mirror}</td>
          </tr>
          <tr>
            <td>MP3 CD Player</td>
            <td>${firstBrand.mp3_CD_Player}</td>
          </tr>
          <tr>
            <td>USB</td>
            <td>${firstBrand.usb}</td>
          </tr>
          <tr>
            <td>AUXIn</td>
            <td>${firstBrand.auxin}</td>
          </tr>
          <tr>
            <td>MDI</td>
            <td>${firstBrand.mdi}</td>
          </tr>
           <tr>
            <td>Blue tooth</td>
            <td>${firstBrand.bluetooth}</td>
          </tr> 
           <tr>
            <td>Fog lamps</td>
            <td>${firstBrand.foglamps}</td>
          </tr>
           <tr>
            <td>Rear AC vents</td>
            <td>${firstBrand.rear_AC_vents}</td>
          </tr>
          <tr>
            <td>Rear defogger</td>
            <td>${firstBrand.rear_defogger}</td>
          </tr>
          <tr>
            <td>Wash wipe</td>
            <td>${firstBrand.wash_wipe}</td>
          </tr>
          <tr>
            <td>Airbags</td>
            <td>${firstBrand.airbags}</td>
          </tr>
          <tr>
            <td>ESP</td>
            <td>${firstBrand.esp}</td>
          </tr>
          <tr>
            <td>ABS</td>
            <td>${firstBrand.abs}</td>
          </tr>
        </table>
         </c:forEach>
        </c:when>
       <%--  <c:otherwise>
        <table width="100%" cellpadding="5" cellspacing="5" border="0">
          <tr>
          <td><font color=red>Records not found</font></td>
          <td></td>
          </tr>
          </table>
        </c:otherwise> --%>
        </c:choose>
      </div>
       <c:choose>
    <c:when test="${fn:length(firstBrandDet)==0}">
      <div class="right-table flr" style="width:100%;">
    </c:when>
    <c:otherwise>
    <div class="right-table flr">
    </c:otherwise>
    </c:choose>
       <c:choose>
      <c:when test="${fn:length(secondBrandDet)>0}">
        <c:forEach var="secondBrandDet" items="${secondBrandDet}" >
        <table width="100%" cellpadding="5" cellspacing="5" border="0">
          <tr>
            <td>Brand Name</td>
            <td>${secondBrandDet.brand}</td>
          </tr>
          <tr>
            <td>Modal </td>
            <td>${secondBrandDet.vehicle}</td>
          </tr>
          <tr>
            <td>Delhi Price </td>
            <td><span>&#8377 ${secondBrandDet.delhi_price}</span></td>
          </tr>
          <tr>
            <td>Mumbai Price</td>
            <td><span>&#8377 ${secondBrandDet.mumbai_price}</span></td>
          </tr>
          <tr>
            <td>Kolkata Price</td>
            <td><span>&#8377 ${secondBrandDet.kolkata_price}</span></td>
          </tr>
          <tr>
            <td>Banglore price</td>
            <td><span>&#8377 ${secondBrandDet.bangalore_price}</span></td>
          </tr>
          <tr>
            <td>Chennai Price</td>
            <td><span>&#8377 ${secondBrandDet.chennai_price}</span></td>
          </tr>
          <tr>
            <td>Body Type</td>
            <td>${secondBrandDet.bodytype}</td>
          </tr>
          <tr>
            <td>Engine Displacement</td>
            <td>${secondBrandDet.engineDisplacement}</td>
          </tr>
          <tr>
            <td>Cylinder</td>
            <td>${secondBrandDet.cylinders}</td>
          </tr>
          <tr>
            <td>Power</td>
            <td>${secondBrandDet.power}</td>
          </tr>
          <tr>
            <td>Torque</td>
            <td>${secondBrandDet.torque}</td>
          </tr>
          <tr>
            <td>Gears</td>
            <td>${secondBrandDet.gears}</td>
          </tr>
          <tr>
            <td>Gear Type</td>
            <td>${secondBrandDet.gearType}</td>
          </tr>
          <tr>
            <td>Fuel Type</td>
            <td>${secondBrandDet.fuelType}</td>
          </tr>
          <tr>
            <td>Running Km.</td>
            <td>${secondBrandDet.runningkm}</td>
          </tr>
          <tr>
            <td>ARAI Claimed</td>
            <td>${secondBrandDet.arai_Claimed}</td>
          </tr>
         <tr>
            <td>Tested</td>
            <td>${secondBrandDet.tested}</td>
          </tr>
          <tr>
            <td>AC</td>
            <td>${secondBrandDet.ac}</td>
          </tr>
          <tr>
            <td>Climate Control</td>
            <td>${secondBrandDet.climate_Control}</td>
          </tr> 
          <tr>
            <td>Adjustable Steering Rake</td>
            <td>${secondBrandDet.adjustable_Steering_rake}</td>
          </tr>
          <tr>
            <td>Adjustable Steering Reach</td>
            <td>${secondBrandDet.adjustable_Steering_Reach}</td>
          </tr>
          <tr>
            <td>Steering mounted controls</td>
            <td>${secondBrandDet.steering_mounted_controls}</td>
          </tr>
          <tr>
            <td>Cruise Control</td>
            <td>${secondBrandDet.cruise_Control}</td>
          </tr>
          <tr>
            <td>Central Lock</td>
            <td>${secondBrandDet.centrallock}</td>
          </tr>
          <tr>
            <td>Remote Lock</td>
            <td>${secondBrandDet.remotelock}</td>
          </tr>
          <tr>
            <td>Keyless</td>
            <td>${secondBrandDet.keyless}</td>
          </tr>
          <tr>
            <td>Power Windows</td>
            <td>${secondBrandDet.power_windows}</td>
          </tr>
          <tr>
            <td>Driver seat adjust</td>
            <td>${secondBrandDet.driver_seat_adjust}</td>
          </tr>
          <tr>
            <td>Parking sensor</td>
            <td>${secondBrandDet.parking_sensor}</td>
          </tr>
          <tr>
            <td>Parking camera</td>
            <td>${secondBrandDet.parking_camera}</td>
          </tr>
          <tr>
            <td>Split foalding</td>
            <td>${secondBrandDet.split_foalding}</td>
          </tr>
          <tr>
            <td>Multi function</td>
            <td>${secondBrandDet.multi_function}</td>
          </tr>
          <tr>
            <td>Door mirror</td>
            <td>${secondBrandDet.door_mirror}</td>
          </tr>
          <tr>
            <td>MP3 CD Player</td>
            <td>${secondBrandDet.mp3_CD_Player}</td>
          </tr>
          <tr>
            <td>USB</td>
            <td>${secondBrandDet.usb}</td>
          </tr>
          <tr>
            <td>AUXIn</td>
            <td>${secondBrandDet.auxin}</td>
          </tr>
          <tr>
            <td>MDI</td>
            <td>${secondBrandDet.mdi}</td>
          </tr>
           <tr>
            <td>Blue tooth</td>
            <td>${secondBrandDet.bluetooth}</td>
          </tr> 
           <tr>
            <td>Fog lamps</td>
            <td>${secondBrandDet.foglamps}</td>
          </tr>
           <tr>
            <td>Rear AC vents</td>
            <td>${secondBrandDet.rear_AC_vents}</td>
          </tr>
          <tr>
            <td>Rear defogger</td>
            <td>${secondBrandDet.rear_defogger}</td>
          </tr>
          <tr>
            <td>Wash wipe</td>
            <td>${secondBrandDet.wash_wipe}</td>
          </tr>
          <tr>
            <td>Airbags</td>
            <td>${secondBrandDet.airbags}</td>
          </tr>
          <tr>
            <td>ESP</td>
            <td>${secondBrandDet.esp}</td>
          </tr>
          <tr>
            <td>ABS</td>
            <td>${secondBrandDet.abs}</td>
          </tr>
        </table>
        </c:forEach>
          </c:when>
          <%-- <c:otherwise>
          <table width="100%" cellpadding="5" cellspacing="5" border="0">
          <tr>
          <td><font color=red>Records not found</font></td>
          <td></td>
          </tr>
          </table>
          </c:otherwise> --%>
          </c:choose>
      </div>
    </div>
  </div>
</div>
	
	
	
	
	
  </div>
    </div>
    <div class="right-panel">
      <%@include file="right-panel-chunk.jsp" %>
    </div>
  </div>
  <div class="clearfix"></div>
  <%@include file="footer-chunk.jsp" %>
  <%/* } 
}catch(Exception ee)
{
//response.sendRedirect(Constants.PAGE_NOT_FOUND);
}  */
%>
  </body>
  </html>
<%-- </cache:cache> --%>