<%-- 
    Document   : getRegionList
    Created on : Dec 17, 2011, 3:50:33 PM
    Author     : 14058
--%>

<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:forEach items="${regionList}" var="regionList" >
   $('#region').append($("<option></option>").attr("value",<c:out value="${regionList.regionID}"/>).text('<c:out value="${regionList.regionName}"/>'));
</c:forEach>