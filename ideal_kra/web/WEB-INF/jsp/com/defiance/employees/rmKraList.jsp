<%--
    Document   : employeeKraList
    Created on : 7 Aug, 2017, 6:30:21 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KRA Page</title>
    </head>
    <body>
        <form name="employeeKraForm" id="employeeKraForm" method="post">
        <div class="container_inner"> 
            <div class="page_heading">
                KRA List - RM View
<!--                <div class="listLink">
                    <img src="${pageContext.request.contextPath}/images/arrow_upload.png" style="width:20px;">
                    <a target="_self" style="text-decoration:none;color: #4C83B3;" href="generalInfo.htm?actionName=uploadKra&page=1">Upload KRA</a>
                </div>
                <div class="listLink" style="margin-top:5px;">
                    <img src="${pageContext.request.contextPath}/images/add_1.png">
                    <a target="_self" style="text-decoration:none;color: #4C83B3;" href="generalInfo.htm?actionName=employeeKra&page=1&associateId=">Add Individual KRA</a>
                </div>-->
            </div>
            <div class="filetrInnerWrap">
                <label class="label">Financial Year </label>
                <select class="financial_year" name="financeYear" id="financeYear">
                    <c:forEach items="${testFin}" var="testFin" varStatus="i">
                        <option value="${testFin}" ${testFin eq current_financial_year ? 'selected="selected"':''}>${testFin}</option>
                    </c:forEach>
                </select>
                <!--<input type="hidden" id="finYear" name="financeYear" value="" readonly/>-->
                <label class="label">KRA Period </label>
                <select class="quarter" name="quarter" id="quarter">
                    <c:forEach items="${quarterList}" var="quarterList" varStatus="i">
                        <option value="${quarterList.quarter_id}" ${quarterList.quarter_id eq current_quarter ? 'selected="selected"':''}>${quarterList.quarter_name}</option>
                    </c:forEach>
                </select>
                <!--<input type="hidden" id="quarter" name="quarter" value="" readonly/>-->
                <input type="button" name="view" value="Go" class="gobutton"  onClick="return rmFilter();" style="float:none;margin-left: 20px;"/>
                <input type="button" name="view" value="Export" class="exportbutton"   onclick="return getRmKraExcelExport();" style="float:none;margin-left: 30px;"/>
            </div>
        </div>
        <div class="feedbacks index">
            <div id="datadisplay">
                <table>
                    <tr>
                        <th align="center">Employee Id</th>
                        <th align="center">Employee Name</th>
                        <th align="center">Financial Year</th>
                        <th align="center">Quarter</th>
                        <th align="center">Status</th>
                        <th align="center">Action</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(details)!=0}">
                            <c:forEach items="${details}" var="item">
                                <tr>
                                    <td>${item.employee_number}</td>
                                    <td>${item.employee_name}</td>
                                    <td>${item.financial_year}</td>
                                    <td>${item.quarter_name}</td>
                                    <td>${item.status}</td>
                                    <td align="center">
                                        <a href="viewIndividualKra.htm?kraId=${item.kra_id}&reportingManager=1&edit=1"><img src="${pageContext.request.contextPath}/images/eye.png" /></a>
                                      <%--<c:if test="${item.status == 'Saved'}">--%>
                                            <!--<a href="generalInfo.htm?kraId=${item.kra_id}&actionName=employeeKra&page=1&financeYear=${item.financial_year}&quarter=${current_quarter}&associateId=${item.employeeId}"><img src="${pageContext.request.contextPath}/images/document-blue.png" /></a>-->
                                        <%--</c:if>--%> 
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="6" style="text-align: center;"><label class="label">No data to display</label></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    
                </table>

            </div>
        </div>
        </form>
    </body>
</html>
