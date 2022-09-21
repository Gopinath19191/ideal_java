<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<%@ taglib uri="http://beehive.apache.org/netui/tags-html-1.0" prefix="netui"%>
<%@ taglib uri="http://beehive.apache.org/netui/tags-databinding-1.0" prefix="netui-data"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/QPD.css" type="text/css"/>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/icon.ico" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.7.custom_mod.css" type="text/css"/>
        
         
        <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/ui.all.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/autoSuggest.css" type="text/css"/>--%>
        <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.5.custom.css" type="text/css"/>--%>
        
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>

        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-ui-1.8.5.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.autoSuggest.mod.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.validate.1.7.mod.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/additional-methods.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.autocomplete.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.scrollTo-min.js"></script>
      
        
        <!-- Alternate js open in case of problems occuring at last minute -->
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-ui-1.8.5.custom.min.js"></script>-->

    <script type="text/javascript">
        function changeTabClass(tabId){
            $(".ticket_tab").removeClass("active_tab");
            $("#"+tabId).addClass("active_tab");
        }
$(document).ready(function(){
    checkCookie();
});
function checkCookie(){
	if(!document.cookie){
	        alert('Please make sure cookies are enabled for this site. Otherwise, you will not be able to login.');
        }
}

function noBack(){window.history.forward()}
noBack();
window.onload=noBack;
window.onpageshow=function(evt){if(evt.persisted)noBack()}
window.onunload=function(){void(0)}
    </script>
    <noscript>
        <table>
            <tr>
            <td>
            <h1>Error! - Javascript Disabled</h1>
            <font face="Arial, Helevetica" color="red" >You must have JavaScript enabled on your browser.</font>
            </td>
            </tr>
            </table>
    </noscript>
    </head>
<%--<body>--%>
        <%--<c:out value="${isAppraisee}"></c:out>
        <c:out value="${isAppraiser}"></c:out>
        <c:out value="${isReviewer}"></c:out>
        <c:out value="${isNormaliser}"></c:out>
        <c:out value="${isHr}"></c:out>
        <c:out value="${isFinance}"></c:out>--%>
<%--<div class="tabletools submenuwrap" style="width:80%;margin:auto">--%>
    
