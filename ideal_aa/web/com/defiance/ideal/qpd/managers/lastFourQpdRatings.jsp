<%-- 
    Document   : lastThreeQpdRatings
    Created on : Sep 19, 2011, 5:41:00 PM
    Author     : 14583
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table id="qpdRatingTable" width="100%" border="1" style="border-collapse: collapse">
            <c:choose>
                <c:when test="${fn:length(qpdRatingDetails)!=0}">
                    <c:forEach items="${qpdRatingDetails}" var="last3QpdRating" varStatus="index">
                        <th>
                            Q${last3QpdRating.qpdQuarter} - ${last3QpdRating.yearQPD}
                        </th>
                    </c:forEach>
                    <tr>
                        <c:forEach items="${qpdRatingDetails}" var="last3QpdRating" varStatus="index">
                            <td>
                                ${last3QpdRating.qpdRating}
                            </td>
                        </c:forEach>
                    </tr>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td align="center">
                            No Data Available.
                        </td>
                    </tr>
                </c:otherwise>
            </c:choose>
            
    </table>
    </body>
</html>
