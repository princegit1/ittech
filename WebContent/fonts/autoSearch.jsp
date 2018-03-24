<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1" import="java.sql.*,java.util.*,com.itgd.utils.CarData"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
ResultSet rs=null;
Statement stmt=null;
String brandstatus=null;
Connection con=null;
CarData carObj=null;
ArrayList<CarData> dataList=null;
Map<String,ArrayList<CarData>> AutoMaster=new LinkedHashMap<String,ArrayList<CarData>>();
try {
	Class.forName("com.mysql.jdbc.Driver");  
	//Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/itoday","itgd_office","!tgd_0ff111");
	
	//con=DriverManager.getConnection("jdbc:mysql://10.5.0.100:3306/itoday","itgd_intoday","!tgd_0ff111");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest","root","india");
	stmt=con.createStatement();
	
	rs=stmt.executeQuery("select distinct TRIM(UPPER(brand)) AS brand,vehicle,delhi_price,mumbai_price,kolkata_price,bangalore_price,Chennai_price,bodytype,EngineDisplacement,cylinders,power from autotoday order by brand");

	while(rs.next())
	{
		carObj=new CarData();
		
		
		String brand=rs.getString("brand");
		String vehicle=rs.getString("vehicle");
		String delhi_price=rs.getString("delhi_price");
		String mumbai_price=rs.getString("mumbai_price");
		String kolkata_price=rs.getString("kolkata_price");
		String bangalore_price=rs.getString("bangalore_price");
		String chennai_price=rs.getString("Chennai_price");
		String bodytype=rs.getString("bodytype");
		String engineDisplacement=rs.getString("EngineDisplacement");
		String cylinders=rs.getString("cylinders");
		String power=rs.getString("power");
		
		carObj.setBrand(brand);
		carObj.setVehicle(vehicle);
		carObj.setDelhi_price(delhi_price);
		carObj.setMumbai_price(mumbai_price);
		carObj.setKolkata_price(kolkata_price);
		carObj.setBangalore_price(bangalore_price);
		carObj.setChennai_price(chennai_price);
		carObj.setBodytype(bodytype);
		carObj.setEngineDisplacement(engineDisplacement);
		carObj.setCylinders(cylinders);
		carObj.setPower(power);
		if(AutoMaster.containsKey(brand))
		{
			dataList=AutoMaster.get(brand);
			dataList.add(carObj);
		}
		else
		{
			dataList=new ArrayList<CarData>();
			dataList.add(carObj);
		} 
		AutoMaster.put(brand,dataList);
		pageContext.setAttribute("AutoMasterData", AutoMaster);
	}
} catch (ClassNotFoundException e) {
	e.printStackTrace();
} catch (SQLException e) {
	e.printStackTrace();
}
finally
{
	con.close();
	stmt.close();
}

%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Compare your car</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<link href="css/style.css" rel='stylesheet' type='text/css' >
<link href="css/ipad_auto.css" rel='stylesheet' type='text/css' >
<link href="css/mobile_auto.css" rel='stylesheet' type='text/css' >
<style type="text/css"></style>
<script type="text/javascript" src="http://code.jquery.com/jquery.min.js"></script>
<script type="text/javascript">
var i=0;
    $(document).ready(function(){
    	 $(".custom-select").each(function(){
            $(this).wrap("<span class='select-wrapper'></span>");
            $(this).after("<span class='holder'></span>");
            i++;
        });
        $(".custom-select").change(function(){
            var selectedOption = $(this).find(":selected").text();
            $(this).next(".holder").text(selectedOption);
            i++;
        }).trigger('change');
    })
</script>
<script type="text/javascript">
function getBrandDetails(brand,vehicle)
{
	if(i>11)
	{

		//location.href="auto/"+brand+"/"+vehicle+"/"+document.getElementById('brandc').value+"/"+document.getElementById('vehiclec').value+"/compare.html";
		var url="autoSearchServlet?val="+value+"&fun="+data;
		if(data=="vehicle1")
		 url+="&val1="+document.getElementById('brand').value;
		if(data=="vehicle2")
		 url+="&val1="+document.getElementById('brandc').value;
		var xhttp = new XMLHttpRequest();
		  xhttp.onreadystatechange = function() {
		    if (xhttp.readyState == 4 && xhttp.status == 200) {
		       	 var div=null;
		    	 if(data=="brand1")
		    	 {
		    	 	 div="getModels";
		    	 	 document.getElementById('brandId').value=value;
		    	 }
		    	 else if(data=="vehicle1")
		    	 {
		    		 div="getPrices";
		    	 }
		    	 if(data=="brand2")
		    	 {
		    		div="getModels1";
		    	 	document.getElementById('brandId1').value=value;
		    	 }
		    	 else if(data=="vehicle2")
		    	 {
		    		 div="getPrices1";
		    	 }
		    	 
		    	 if(xhttp.responseText!="")
		    		 document.getElementById(div).innerHTML = xhttp.responseText;
		    	 //$("#getModels").wrap("<span class='select-wrapper'></span>");
		        // $("#getModels").after("<span class='holder'>(Select Modal)</span>");
		    
		     }
		  };
		  xhttp.open("GET", url, true);
		  xhttp.send();
	}
} 
</script>
</head>

<body>
<div id="auto-master">
<form id="form1" name="frm" method="POST" >
  <div class="auto-master-section">
    <div class="master-logo flt"><img src="auto/images/auto-master-logo.jpg" alt="" /></div>
    <div class="left-master-section flt">
      <div>
        <label>Brand</label>
        <select id="brand" name="brand" class="custom-select" onChange="getBrandDetails(this.value,'brand1');">
          <option>(Select Brand)</option>
          <c:forEach var="brands" items="${pageScope.AutoMasterData}" >
          	 <option value="${fn:replace(brands.key, '[^\\w.-]+','')}"  <c:if test="${brands.key == param.brandId}">selected</c:if>>${brands.key}</option>
        </c:forEach>
        </select>
      </div>
      <div>
        <label>Modal</label>
        <span id="getModels"><select name="vehicle" id="vehicle"  class="custom-select" onChange="getBrandDetails(this.value,'vehicle1');">
          <option>(Select Modal)</option>
          <c:forEach var="carModel" items="${carModels}" >
          	 <option value="${carModel}" <c:if test="${carModel==param.vehicle}">selected</c:if>>${carModel}</option>
        </c:forEach>
        </select>
        </span>
      </div>
      <div>
        <label>Price Range</label>
        <span id="getPrices">
        <select name="price_range" id="price_range" class="custom-select">
          <option>(Select Price Range)</option>
         <c:forEach var="prices" items="${priceRanges}" >
          	 <option value="${prices}" <c:if test="${prices==param.price_range}">selected</c:if>>${prices}</option>
        </c:forEach>
        </select>
        </span>
      </div>
    </div>
    <div class="break-point"><img src="auto/images/chunk-break-up.jpg" width="10" height="92" alt="" title="" /></div>
    <div class="m-break-point"><img src="auto/images/mob-down-arrow.png" width="12" height="8" /></div>
    <div class="right-master-section flt">
      <div>
        <label>Brand</label>
        <select id="brandc" name="brandc" class="custom-select" onChange="getBrandDetails(document.getElementById('brand').value,document.getElementById('vehicle').value)">
          <option>(Select Brand)</option>
          <c:forEach var="brandsc" items="${brandsDet}" >
              	 <option value="${fn:replace(brandsc.key, '[^\\w.-]+','')}"  <c:if test="${brandsc.key == param.brandId1}">selected</c:if>>${brandsc.key}</option>
        </c:forEach>
        </select>
      </div>
      <div>
        <label>Modal</label>
        <span id="getModels1"><select  name="vehiclec" id="vehiclec" class="custom-select"  onChange="getBrandDetails(this.value,'vehicle2')">
          <option>(Select Modal)</option>
          <c:forEach var="carModelc" items="${carModelsc}" >
          	 <option value="${carModelc}" <c:if test="${carModelc==param.vehiclec}">selected</c:if>>${carModelc}</option>
        </c:forEach>
        </select>
        </span>
      </div>
      <div>
        <label>Price Range</label>
        <span id="getPrices1">
        <select name="price_rangec" id="price_rangec" class="custom-select">
          <option>(Select Price Range)</option>
          <c:forEach var="pricesc" items="${priceRangesc}" >
          	 <option value="${pricesc}" <c:if test="${pricesc==param.price_rangec}">selected</c:if>>${pricesc}</option>
        </c:forEach>
        </select>
        </span>
      </div>
    </div>
    <div class="compare-title flt">
      <h3>Compare</h3>
      <!-- <img src="images/compare-forward.jpg" width="34" height="34" alt="" /> --> 
      <input type="image" src="images/compare-forward.jpg"  width="34" height="34">
      </div>
  </div>
  <input type="hidden" name="brandId" id="brandId">
  <input type="hidden" name="brandId1" id="brandId1">
  </form>
</div>
<div class="clr"></div>
<div id="compare-section">
  <div class="compare-section-two">
    <div class="compare-left" style="display:none;"> <img src="images/left-img.jpg" alt="" title="" /> </div>
    <div class="vs_text" style="display:none;"><img src="images/big-vs.jpg" alt="" title="" /></div>
    <!--<div class="vrs flt"><img src="big-vs.jpg" alt="" title="" /></div> -->
    <div class="compare-right" style="display:none;"> <img src="images/right-img.jpg" alt="" title="" /> </div>
    <div class="clr"></div>
    <div class="bm-break-point">
      <div class="lft ltext">
        <h3><%
        if(request.getParameter("brandId")!=null)
        out.print(request.getParameter("brandId")); %></h3>
        <div class="rating_icon"><img src="auto/images/rating.jpg" alt="" title="" /></div>
      </div>
      <div class="arrow_bottom"><img src="auto/images/v-img.jpg" alt="" title="" /></div>
      <div class="flr rtext">
        <h3><% if(request.getParameter("brandId1")!=null)
        	 out.print(request.getParameter("brandId1"));
        	%></h3>
        <div class="rating_icon"><img src="auto/images/rating.jpg" alt="" title="" /></div>
      </div>
    </div>
    <div class="clr"></div>
    <div class="grybg_background">
      <div class="left-table lft">
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
        </table>
         </c:forEach>
        </c:when>
        <c:otherwise>
        <table width="100%" cellpadding="5" cellspacing="5" border="0">
          <tr>
          <td><font color=red>Records not found</font></td>
          <td></td>
          </tr>
          </table>
        </c:otherwise>
        </c:choose>
      </div>
      <div class="right-table flr">
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
        </table>
        </c:forEach>
          </c:when>
          <c:otherwise>
          <table width="100%" cellpadding="5" cellspacing="5" border="0">
          <tr>
          <td><font color=red>Records not found</font></td>
          <td></td>
          </tr>
          </table>
          </c:otherwise>
          </c:choose>
      </div>
    </div>
  </div>
</div>
</body>
</html>
