<%@page language="java" import="java.sql.*,java.util.*,com.itgd.utils.CarData, com.itgd.utils.CommonFunctions, com.itgd.utils.Constants, com.itgd.dto.ContentTemplateDTO, com.itgd.helper.ContentTemplateHelper,com.itgd.education.utils.DbConnection"%>
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
}  catch (Exception e) {
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
		var url="automaster?val="+value+"&fun="+data;
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