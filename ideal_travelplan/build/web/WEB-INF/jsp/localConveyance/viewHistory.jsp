<%-- 
    Document   : viewHistory
    Created on : 18 Oct, 2012, 2:21:55 PM
    Author     : 14578
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/header.jsp" %>
<%--<%@include file="/../../header.jsp"  %>--%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="cn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
    </head>
    <body>

        <div class="formContent_new">
            <div class="fieldsetHeading">View History</div>
            <table class="travelDetails_new" border="0" cellpadding="2" cellspacing="2" align="center">
                <tr>
                    <th>RM Name</th>
                    <th>RM Approved Date</th>
                    <th>RM Remarks</th>
                    <th>Finance Approver Name</th>
                    <th>Finance Approved Date</th>
                    <th>Finance Remarks</th>
                    <th>CFO Name</th>
                    <th>CFO Action Date</th>
                    <th>CFO Remarks</th>
                    <th>Admin Name</th>
                    <th>Admin Action Date</th>
                    <th>Admin Remarks</th>
                </tr>

                <c:choose>
                    <c:when test="${historyDetails != null}">
                        <c:forEach items="${historyDetails}" var="history" varStatus="i">
                            <tr>
                                <td>
                                    ${history.rmApproverName}

                                </td>
                                <td>
                                    ${history.rmActionDate}
                                </td>
                                <td>
                                    ${history.rmRemarks}
                                </td>
                                <td> ${history.financeRemarks}</td>
                                <td> ${history.financeApproverName}</td>
                                <td> ${history.financeRemarks}</td>
                                <td> ${history.financeApproverName}</td>
                                <td> ${history.cfoName}</td>
                                <td> ${history.cfoActionDate}</td>
                                <td> ${history.cfoRemarks}</td>
                                <td> ${history.adminApproverName}</td>
                                <td> ${history.adminActionDate}</td>
                                <td> ${history.adminRemarks}</td>

                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr>
                            <td colspan="2"  align="center">No Data To Display</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
            </table>
        </div>
    </body>
</html>
