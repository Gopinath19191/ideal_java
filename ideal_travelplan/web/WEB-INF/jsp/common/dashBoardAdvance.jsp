<%-- 
    Document   : dashBoard
    Created on : Oct 8, 2012, 11:33:18 AM
    Author     : 14583
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
        <title>Travel Request</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/><BR/>
        <script type="text/javascript">
            function getConfirmationForGermany(forward_page){
                 <c:forEach items="${disclaimer}" var="disclaimerValue" varStatus="disclaimerStatus">
                     <c:if test="${disclaimerValue.disclaimerId == 'employee_disclaimer'}">
                         <c:set var="employee_disclaimer" value="${disclaimerValue.disclaimerName}" ></c:set>
                     </c:if>
                </c:forEach>
                $('<div />').html('${employee_disclaimer}').dialog({
                    title: "Disclaimer",
                    buttons: {
                        "Yes": function() {
                            $(this).dialog("close");
                            window.location.href = forward_page;
                        },
                        "No": function() {
                            $(this).dialog("close");
                        }
                    }
                }).addClass("dialogClass");
                var dialog_width = 600;
                var dialog_height =  210;
                var top_px = 100;
                var left_px = (($(document).width() / 2) -  (dialog_width / 2));
                $(".ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix").css({'height':'20px'});
                $(".ui-dialog").css({"width":dialog_width, "top": top_px, "left":left_px});
                $(".ui-dialog-content").css({'height':dialog_height});
                $(".ui-widget-content").css({'background-color':'#CBDCED'});
                $(".ui-dialog-titlebar").css({'background-color':'#4D85B8','border-color':'#4D85B8'});
                $(".ui-dialog .ui-dialog-buttonpane button").css({'background-color':'#4D85B8','border-color':'#4D85B8','color':'#FFFFFF'});

            }
        </script>
    </head>
    <body>
        <div class="container_inner">
            <div class="page_heading">${displayName} List - Further Advance</div>
            <div class="breadcrumb">
                <div class="goToList" style="margin-right: 56px;margin-top: -21px;margin-bottom: 5px;">
                    <c:choose>
                        <c:when test="${currEmpStatus == 'G'}">
                            <a class="showList" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/getDashBoardList.htm?page=1')">List Requests</a>
<!--                            <a class="add" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/${forwardPage}')">Add</a>-->
                        </c:when>
                        <c:otherwise>
                            <a class="showList" href="${pageContext.request.contextPath}/getDashBoardList.htm?page=1">List Requests</a>
<!--                            <a class="add" href="${pageContext.request.contextPath}/${forwardPage}">Add</a>-->
                        </c:otherwise>
                    </c:choose>
                    
                </div>
            </div>
        </div>
        <div class="formContent_new" style="margin-bottom: 5px;">
            <table class="tableStructure" border="0" width="96%">
                <cn:choose>
                    <cn:when test="${fn:length(listObj)!=0}">
                        <tr>
                        <th style="width:10%">Request Id</th>
                        <th style="width:7%">Advance Requested Date</th>
                        <th  style="width:10%">Extended End Date</th>
                        <th  style="width:10%">Amount Requested</th>
                        <th style="width:8%">Travel Status</th>
                        <th style="width:2%">&nbsp;</th>
                    </tr>
                        <cn:forEach items="${listObj}" var="travelData" varStatus="status">
                            <cn:set var="i" value="${status.index+1}"/>
                            <cn:set var="className" value="alt"/>
                            <cn:if test="${i%2==0}">
                                <cn:set var="className" value="altrow"/>
                            </cn:if>
                            <%--Travel history loop begins--%>
                            <tr class="${className}">
                            <td style="width:10%">
                                <c:choose>
                                    <c:when test="${travelData.system == 'G'}">
                                        <a style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}')">${travelData.tpReferenceId}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}">${travelData.tpReferenceId}</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <fmt:formatDate value="${travelData.travelAdvanceRequestedDate}" var="advanceReqDate" pattern="dd-MM-yyyy" />
                            <fmt:formatDate value="${travelData.travelAdvanceEndDate}" var="advanceEndDate" pattern="dd-MM-yyyy" />
                            <td>${advanceReqDate}</td>
                            <td>${advanceEndDate}</td>
                            <td>${travelData.furtherAdvance}</td>
                            <td>
                                <c:if test="${travelData.advanceStatus == 's'}">Saved</c:if>
                                <c:if test="${travelData.advanceStatus == 'o'}">Rejected</c:if>
                                <c:if test="${travelData.advanceStatus == 'x'}">Cancelled</c:if>
                                <c:choose>
                                    <c:when test="${travelData.advancePreviousStatus == 'f' && travelData.currentStatus == 'c'}">CFO Requested</c:when>
                                    <c:otherwise>
                                        <c:if test="${travelData.advancePreviousStatus == 'q'}">Submitted</c:if>
                                        <c:if test="${travelData.advancePreviousStatus == 'r'}">RM Approved</c:if>
                                        <c:if test="${travelData.advancePreviousStatus == 'b'}">BUH Approved</c:if>
                                        <c:if test="${travelData.advancePreviousStatus == 'm'}">MD Approved</c:if>
                                        <c:if test="${travelData.advancePreviousStatus == 'f'}">Finance Approved</c:if>
                                        <c:if test="${travelData.advancePreviousStatus == 'c'}">CFO Approved</c:if>
                                        <c:if test="${travelData.advancePreviousStatus == 'a'}">Ticket Booked</c:if>
                                        <c:if test="${travelData.advancePreviousStatus == 't'}">Money Deposited</c:if>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${travelData.system == 'G'}">
                                        <a style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}')"><img src="${pageContext.request.contextPath}/css/images/document-blue.png"></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}"><img src="${pageContext.request.contextPath}/css/images/document-blue.png"></a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </cn:forEach>
                    </cn:when>
                        <cn:otherwise>
                            <div style="color:red"><b><br><br>
                                    There are no Travel Requests found.
                                </b> </div>
                        </cn:otherwise>
                </cn:choose>
            </table>
            <cn:if test="${paging[0] > 1}">
                <form action="getDashBoardList.htm" method="post" name="getDashBoardList" id="getDashBoardList">
                    <input type="hidden" id="page" name="page" value="" />
                    <%@include file="/WEB-INF/jsp/paging.jsp" %>
                </form>
            </cn:if>
        </div>
            <script type="text/javascript">
                function loadForm(page){
                    document.getDashBoardList.page.value = page;
                    document.getDashBoardList.submit();
                }
            </script>
    </body>
</html>
