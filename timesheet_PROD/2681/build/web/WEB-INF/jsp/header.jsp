<%-- 
    Document   : header
    Created on : Sep 27, 2011, 10:48:22 AM
    Author     : 14583
--%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.addHeader("P3P", "CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\"");%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon.ico" />
        <link href="${pageContext.request.contextPath}/css/demos.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/timesheet.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datepick.css" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/timesheet_Approve.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.17.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/wtooltip.js"></script>
    </head>
