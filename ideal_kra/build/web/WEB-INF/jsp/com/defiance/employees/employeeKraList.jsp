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
        <script type="text/javascript">
            $(document).ready( function() {
                if(${AnnualAppraisal == 1}){
//                    document.getElementById("AAClick").click();
//                    $("#AAClick").trigger("click")();
                    window.parent.location.href="http://182.72.70.165/idealbeta/appraisal/";
                }
            });
        </script>
    </head>
    <body>
        <div class="container_inner"> 
            <div class="page_heading">
                KRA List
                <div class="listLink" style="margin-top:5px;">
                    <!--<img src="${pageContext.request.contextPath}/images/add_1.png">-->
                    <!--<a target="_self" style="text-decoration:none;color: #4C83B3;" href="generalInfo.htm?actionName=employeeKra&page=1&associateId=${employeeId}">Add Individual KRA</a>-->
                </div>
            </div>
        </div>
        <br>
        <div id="errordiv">
            <c:if test="${AnnualAppraisal == 1}">
                <!--<a id="AAClick" href="http://182.72.70.165/idealbeta/appraisal/"></a>-->    
            </c:if>
            <span style=" color: red;">${returnMsg}</span>
        </div>
        <div id="errordiv" style="text-align: center; padding-bottom: 10px;"> <span style="color: green; text-align: center;font-size: 16px">${successMsg}</span></div>
        <div class="feedbacks index">
            <div id="datadisplay">
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <th align="center">Financial Year</th>
                        <th align="center">KRA Period</th>
                        <th align="center">Approved By</th>
                        <th align="center">Submitted On</th>
                        <th align="center">Status</th>
                        <th align="center">Action</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(details)!=0}">
                            <c:forEach items="${details}" var="item">
                                <tr>
                                    <td>${item.financial_year}</td>
                                    <td>${item.quarter_name}</td>
                                    <td>${item.rm_name}</td>
                                    <td>${item.submitted_on}</td>
                                    <td>${item.status}</td>
                                    <td align="center">
                                        <a href="viewIndividualKra.htm?kraId=${item.kra_id}&reportingManager=0&edit=0"><img src="${pageContext.request.contextPath}/images/eye.png" /></a>
                                            <c:if test="${item.status == 'Saved' || item.status == 'Sent Back'}">
                                                <a href="generalInfo.htm?actionName=employeeKra&page=1&financeYear=${item.financial_year}&quarter=${item.quarter_id}&associateId=${employeeId}"><img src="${pageContext.request.contextPath}/images/document-blue.png" /></a>
                                            </c:if>
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
    </body>
</html>
