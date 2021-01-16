<%-- 
    Document   : index
    Created on : 27.Ara.2020, 16:45:44
    Author     : engin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true"%>
<!DOCTYPE html>
	<!-- Bootstrap extend-->
	<link rel="stylesheet" href="css/bootstrap-extend.css">
	
	<!-- Theme style -->
        <link href="css/master_style.css" rel="stylesheet" type="text/css"/>
	<!-- Crypto_Admin skins -->
	<link rel="stylesheet" href="css/_all-skins.css">	
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>index.jsp</title>
</head>
<body>
<%
    if (session.getAttribute("otelUserName")!=null)
    {
        %>
<jsp:forward page="anasayfa"/>
        <%
    }else  
{
%>
<%@include file="login.jsp" %>
<%}//else%>
</body>
</html>
