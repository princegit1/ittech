<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	<% 
	if(request.getParameter("fun").equals("brand1"))
	{
	%>	
		<select name="vehicle" id="vehicle"  class="custom-select" onChange="getBrandDetails(this.value,'vehicle1')">
		<option value="">(Select Modal)</option>
		<c:forEach var="carModel" items="${carModels}" >
		<option value="${carModel}" <c:if test="${carModel==param.vehicle}">selected</c:if>>${carModel}</option>
		</c:forEach>
		</select>
	<%
	}
	else if(request.getParameter("fun").equals("vehicle1"))
	{
	%>
	<select name="price_range" id="price_range" class="custom-select" onChange="getBrandDetails(this.value,'price1');">
          <option value="">(Select Price Range)</option>
         <c:forEach var="prices" items="${priceRanges}" >
          	 <option value="${prices}">${prices}</option>
        </c:forEach>
        </select>
	<%
	}
	%>
	<% 
	if(request.getParameter("fun").equals("brand2"))
	{
	%>	
		<select  name="vehiclec" id="vehiclec" class="custom-select"  onChange="getBrandDetails(this.value,'vehicle2')">
          <option value="">(Select Modal)</option>
          <c:forEach var="carModelc" items="${carModelsc}" >
          	 <option value="${carModelc}" <c:if test="${carModelc==param.vehiclec}">selected</c:if>>${carModelc}</option>
        </c:forEach>
        </select>
	<%
	}
	else if(request.getParameter("fun").equals("vehicle2"))
	{
	%>
		<select name="price_rangec" id="price_rangec" class="custom-select"  onChange="getBrandDetails(this.value,'price2');">
	          <option value="">(Select Price Range)</option>
	          <c:forEach var="pricesc" items="${priceRangesc}" >
	          	 <option value="${pricesc}">${pricesc}</option>
	        </c:forEach>
	        </select>
	<%
	}
	else if(request.getParameter("fun").equals("price1") || request.getParameter("fun").equals("price2"))
	{
		out.println("price");
	}
	%>
			