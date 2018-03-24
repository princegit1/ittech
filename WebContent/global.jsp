<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="java.util.*"  
    pageEncoding="ISO-8859-1"%>
<%
Properties property=new Properties();
try
{
	property.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("techjson.properties"));
}
catch(Exception e)
{
	e.printStackTrace();
}
%>
