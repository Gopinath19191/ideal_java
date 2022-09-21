<%-- 
    Document   : header
    Created on : Sep 26, 2011, 4:54:32 PM
    Author     : 14517
--%>

<% response.addHeader("P3P", "CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\"");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" import="java.util.*" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control"  content="no-cache"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/cake.generic.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/qpd.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/qualityCustomer.css" type="text/css"/>

<!--    For Date Picker-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.17.custom.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.17.custom.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datepick.css" type="text/css"/>

<!--    Auto Comp[lete Css-->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.autocomplete_1.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.superbox_1.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
<!--    Auto Complete-->
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.superbox-min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.superbox.js"></script>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ui.all.css" type="text/css"/>--%>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.5.custom.css" type="text/css"/>--%>





