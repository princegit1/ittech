<%@page language="java" import="java.sql.*,java.util.*,com.itgd.utils.CarData, com.itgd.utils.CommonFunctions, com.itgd.utils.Constants, com.itgd.dto.ContentTemplateDTO, com.itgd.helper.ContentTemplateHelper,com.itgd.education.utils.DbConnection" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/itoday","itgd_office","!tgd_0ff111");
	//con=DriverManager.getConnection("jdbc:mysql://10.5.0.100:3306/itoday","itgd_intoday","!tgd_0ff111");
	con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mytest","root","india");
	/* DbConnection connect = null;
	connect = DbConnection.getInstance("indiatoday");
	con = connect.getConnection("indiatoday"); */
	stmt=con.createStatement();
	
	//rs=stmt.executeQuery("select distinct TRIM(UPPER(brand)) AS brand,vehicle,delhi_price,mumbai_price,kolkata_price,bangalore_price,Chennai_price,bodytype,EngineDisplacement,cylinders,power from autotoday order by brand");
	rs=stmt.executeQuery("select distinct TRIM(UPPER(brand)) AS brand,price_range,vehicle,delhi_price,mumbai_price,kolkata_price,bangalore_price,Chennai_price,bodytype,EngineDisplacement,cylinders,power,torque,gears,geartype,fueltype,runningkm,topspeed,ARAI_Claimed,Tested,AC,Climate_Control,Adjustable_Steering_rake,Adjustable_Steering_Reach,Steering_mounted_controls,Cruise_Control,Centrallock,Remotelock,Keyless,Power_windows,Driver_seat_adjust,Parking_sensor,Parking_camera,Split_foalding,Multi_function,Door_mirror,MP3_CD_Player,USB,AUXIn,MDI,Bluetooth,Foglamps,Rear_AC_vents,Rear_defogger,Wash_wipe,Airbags,ESP,ABS from autotoday order by brand");
	while(rs.next())
	{
		carObj=new CarData();
		
		
		String brand=rs.getString("brand");
		String vehicle=rs.getString("vehicle");
		System.out.println("vehiclevehiclevehiclevehicle"+vehicle);
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
}  catch (Exception e) {
	e.printStackTrace();
}
finally
{
	con.close();
	stmt.close();
}

%>
<link href='http://fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
  <link href="<%=Constants.SITE_URL %>technology/auto/css/style.css" rel='stylesheet' type='text/css'>
  <link href="<%=Constants.SITE_URL %>technology/auto/css/ipad_auto.css" rel='stylesheet' type='text/css'>
<link href="../auto/css/mobile_auto.css" rel='stylesheet' type='text/css'>
  <script src="http://media2.intoday.in/indiatoday/js/jquery.min.1.8.2.js" type="text/javascript"></script>
  <script type="text/javascript" language="javascript" src="http://media2.intoday.in/indiatoday/js/ajaxinclude.js"></script> 
<script type="text/javascript">
var i=0;
    $(document).ready(function(){
    	document.getElementById('brandId').value=$("#brand").find(":selected").text();
    	document.getElementById('brandId1').value=$("#brandc").find(":selected").text();
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
  var modelcount=1;
  var pricecount=1;
  var modelcount1=1;
  var pricecount1=1;
function getBrandDetails(value,data)
{
	/* if(i>11)
		location.href="auto/"+brand+"/"+vehicle+"/"+document.getElementById('brandc').value+"/"+document.getElementById('vehiclec').value+"/compare.html"; */
		if(i>11)
    {
		//location.href="auto/"+brand+"/"+vehicle+"/"+document.getElementById('brandc').value+"/"+document.getElementById('vehiclec').value+"/compare.html";
		value=value.trim();
		 altervalue1=value.replace("+", "_");
		var url="automaster?val="+altervalue1.trim()+"&fun="+data;
		
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
		    	 {
		    		 if(document.getElementById(div))
		    		 document.getElementById(div).innerHTML = xhttp.responseText;
		    	 	 if(data=="brand1")
		    	 	 {
		    	 		if(modelcount==1)
		    	 		{
		    	 			$("#getModels").wrap("<span class='select-wrapper'></span>");
		        	 		$("#getModels").after("<span class='holder'>(Select Modal)</span>");
		        	 		modelcount++;
		    	 		}
		    	 		else
		    	 		{
		    	 			$("#getModels").closest('.select-wrapper').find('.holder').html("(Select Modal)");
		    	 		}
		    	 	 }
		    	 	 else if(data=="vehicle1")
		    	 	 {
		    	 		//$("#getModels").wrap("<span class='select-wrapper'></span>");
		    	 		if(pricecount==1)
		    	 		{
		    	 			$("#getPrices").wrap("<span class='select-wrapper'></span>");
		    	 			$("#getModels").closest('.select-wrapper').find('.holder').html(value);
		    	 			$("#getPrices").after("<span class='holder'>(Select Price Range)</span>");
		    	 			pricecount++;
		    	 		}
		    	 		else
		    	 		{
		    	 			$("#getModels").closest('.select-wrapper').find('.holder').html(value);
		    	 			$("#getPrices").closest('.select-wrapper').find('.holder').html("(Select Price Range)");
		    	 		}
		    	 	 }
		    	 	 else if(data=="price1")
		    	 	 {
		    	 		$("#getPrices").closest('.select-wrapper').find('.holder').html(value);
		    	 	 }
		    	 	if(data=="brand2")
		    	 	 {
		    	 		if(modelcount1==1)
		    	 		{
		    	 			$("#getModels1").wrap("<span class='select-wrapper'></span>");
		        	 		$("#getModels1").after("<span class='holder'>(Select Modal)</span>");
		        	 		modelcount1++;
		    	 		}
		    	 		else
		    	 		{
		    	 			$("#getModels1").closest('.select-wrapper').find('.holder').html("(Select Modal)");
		    	 		}
		    	 	 }
		    	 	 else if(data=="vehicle2")
		    	 	 {
		    	 		//$("#getModels").wrap("<span class='select-wrapper'></span>");
		    	 		//$("#getModels1").after("<span class='holder'>"+value+"</span>");
		    	 		
		    	 		if(pricecount1==1)
		    	 		{
			    	 		$("#getPrices1").wrap("<span class='select-wrapper' id='pricewrapper'></span>");
			    	 		$("#getModels1").closest('.select-wrapper').find('.holder').html(value);
			    	 		$("#getPrices1").after("<span class='holder' id='priceholder2'>(Select Price Range)</span>");
			    	 		pricecount1++;
		    	 		}
		    	 		else
		    	 		{
		    	 			$("#getModels1").closest('.select-wrapper').find('.holder').html(value);
		    	 			$("#getPrices1").closest('.select-wrapper').find('.holder').html("(Select Price Range)");
		    	 		}
		    	 	 }
		    	 	 else if(data=="price2")
		    	 	 {
		    	 		//$("#getPrices1").after("<span class='holder'>"+value+"</span>");
		    	 		$("#getPrices1").closest('.select-wrapper').find('.holder').html(value);
		    	 	 }
		    		 
		    	 }
		    }
		  };
		  xhttp.open("GET", url, true);
		  xhttp.send();
    }
} 
</script>


<div id="auto-master">
<form id="form1" name="frm" method="POST" action="automaster" >
  <div class="auto-master-section">
    <div class="master-logo flt"><img src="<%=Constants.SITE_URL %>technology/auto/images/auto-master-logo.jpg" alt="" /></div>
    <div class="left-master-section flt">
      <div>
        <label>Brand</label>
        <select id="brand" name="brand" class="custom-select" onChange="getBrandDetails(this.value,'brand1');">
          <option>(Select Brand)</option>
          <c:forEach var="brands" items="${pageScope.AutoMasterData}" >
          	 <option value="${fn:replace(brands.key, '[^\\w.-]+','')}"  <c:if test="${brands.key == param.brandId}">selected</c:if>>${brands.key}</option>
        </c:forEach>
        </select>
        </select>
        
      </div>
      <div id="modaldiv1">
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
        <select name="price_range" id="price_range" class="custom-select" onChange="getBrandDetails(this.value,'price1');">
          <option>(Select Price Range)</option>
         <c:forEach var="prices" items="${priceRanges}" >
          	 <option value="${prices}" <c:if test="${prices==param.price_range}">selected</c:if>>${prices}</option>
        </c:forEach>
        </select>
        </span>
      </div>
    </div>
    <div class="break-point"><img src="<%=Constants.SITE_URL %>technology/auto/images/chunk-break-up.jpg" width="10" height="92" alt="" title="" /></div>
    <div class="m-break-point"><img src="<%=Constants.SITE_URL %>technology/auto/images/mob-down-arrow.png" width="12" height="8" /></div>
    <div class="right-master-section flt">
      <div>
        <label>Brand</label>
        <select id="brandc" name="brandc" class="custom-select" onChange="getBrandDetails(this.value,'brand2')">
          <option value="">(Select Brand)</option>
          <c:forEach var="brandsc" items="${pageScope.AutoMasterData}" >
              	 <option value="${fn:replace(brandsc.key, '[^\\w.-]+','')}"  <c:if test="${brandsc.key == param.brandId1}">selected</c:if>>${brandsc.key}</option>
        </c:forEach>
        </select>
      </div>
      <div>
        <label>Modal</label>
        <span id="getModels1"><select  name="vehiclec" id="vehiclec" class="custom-select"  onChange="getBrandDetails(this.value,'vehicle2')">
          <option value="">(Select Modal)</option>
          <c:forEach var="carModelc" items="${carModelsc}" >
          	 <option value="${carModelc}" <c:if test="${carModelc==param.vehiclec}">selected</c:if>>${carModelc}</option>
        </c:forEach>
        </select>
        </span>
      </div>
      <div>
        <label>Price Range</label>
        <span id="getPrices1">
        <select name="price_rangec" id="price_rangec" class="custom-select" onChange="getBrandDetails(this.value,'price2');">
          <option value="">(Select Price Range)</option>
          <c:forEach var="pricesc" items="${priceRangesc}" >
          	 <option value="${pricesc}" <c:if test="${pricesc==param.price_rangec}">selected</c:if>>${pricesc}</option>
        </c:forEach>
        </select>
        </span>
      </div>
    </div>
    <div class="compare-title flt">
      <h3>Compare</h3>
      <input type="image" src="auto/images/compare-forward.jpg"  width="34" height="34">
  </div>
</div>
<input type="hidden" name="brandId" id="brandId">
  <input type="hidden" name="brandId1" id="brandId1">
</form>
</div>