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
  
          function getCss(){
              $.ajax({
                        type: "POST",
                        url: "${pageContext.request.contextPath}/addfurtherAdvance.htm",
                        data: { name: "John", location: "Boston" },
                        dataType: "text",
                        async: false,
                        success:function(data){
                            var arr = data.split(":");
//                            alert("Data::"+arr[0]+":::"+arr[1]);
                            $("#tpId").val("100");
                            $("#empId").val(arr[1]);
                        }
                    });
                var windowWidth;
                var windowHeight;
                $("#advanceDiv").click(function(){
//                    window.location.href='${pageContext.request.contextPath}/addfurtherAdvance.htm';
                    windowWidth = $(window).width();
                    windowHeight = $(window).height();
                    var lightBoxContentWidth = $(".lightbox-content").width();
                    var lightBoxContentHeight = $(".lightbox-content").height();
                    var LeftPos = (windowWidth - lightBoxContentWidth) / 2;
                    var TopPos = (windowHeight - lightBoxContentHeight) / 2;
                    $("body").append("<div class='lightbox-overlay'></div>");
                    $(".lightbox-overlay").css({'width':windowWidth, 'height':windowHeight});
                    $(".lightbox-content").css({'top':TopPos, 'left':LeftPos}).fadeIn();
               });
          }
            function getConfirmationForGermany(forward_page){
//            alert("Here Comes...");
                 <c:forEach items="${disclaimer}" var="disclaimerValue" varStatus="disclaimerStatus">
                     <c:if test="${disclaimerValue.disclaimerId == 'employee_disclaimer'}">
                         <c:set var="employee_disclaimer" value="${disclaimerValue.disclaimerName}" ></c:set>
                     </c:if>
                </c:forEach>
                $('<div />').html('<font size="2.5">${fn:replace(employee_disclaimer,'#EMPNAME#',employeeName)}</font>').dialog({
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
                var dialog_width = 500;
                var dialog_height =  200;
                var top_px = 100;
                var left_px = (($(document).width() / 2) -  (dialog_width / 2));
                $(".ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix").css({'height':'20px'});
                $(".ui-dialog").css({"width":dialog_width, "top": top_px, "left":left_px});
                $(".ui-dialog-content").css({'height':dialog_height});
                $(".ui-widget-content").css({'background-color':'#FFF'});
                $(".ui-dialog-titlebar").css({'background-color':'#CEDFF1','border-color':'#CEDFF1','color':'#000'});
                $(".ui-dialog .ui-dialog-buttonpane button").css({'background-color':'#CEDFF1','border-color':'#CEDFF1','color':'#000'});
            }
        </script>
        
    </head>
    <body>
        <div class="container_inner">
            <div class="page_heading">${displayName} List</div>
            <div class="breadcrumb">
                <div class="goToList" style="margin-right: 56px;margin-top: -21px;margin-bottom: 5px;">
                    <%--  <c:choose>
                        <c:when test="${currEmpStatus == 'G'}">
<!--                            <a class="showList" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/getAdvanceTpList.htm?page=1')">Further Advance List</a>-->
                            <a class="add" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/${forwardPage}')">Add</a>
                        </c:when>
                        <c:otherwise>
<!--                            <a class="showList" href="${pageContext.request.contextPath}/getAdvanceTpList.htm?page=1">Further Advance List</a>-->
                            <a class="add" href="${pageContext.request.contextPath}/${forwardPage}">Add</a>
                        </c:otherwise>
                    </c:choose>
              --%>
               <a class="add" href="${pageContext.request.contextPath}/${forwardPage}">Add</a>
                    
                </div>
            </div>
        </div>
        <div class="formContent_new" style="margin-bottom: 5px;">
            <table class="tableStructure" border="0" width="96%">
                <cn:choose>
                    <cn:when test="${fn:length(listObj)!=0}">
<!--                        <tr>
                        <th style="width:10%">Request Id</th>
                        <th style="width:7%">Requested Date</th>
                        <th style="width:10%">Customer Name</th>
                        <th style="width:10%">Project Name</th>
                        <th style="width:8%">Travel Status</th>
                        <th style="width:8%">Actions</th>
                    </tr>-->
<!--                    //For Further Advance-->
                        <tr>
                        <th rowspan="2" style="width:10%">Request Id</th>
                        <th rowspan="2" style="width:7%">Requested Date</th>
                        <th rowspan="2" style="width:10%">Customer Name</th>
                        <th rowspan="2" style="width:10%">Project Name</th>
                        <th rowspan="2" style="width:8%">Travel Status</th>
                        <th colspan="2" style="width:2%">Actions</th>
                    </tr>
                    <tr><th style="width:2%">Edit</th>
<!--                    <th style="width:2%">Advance</th></tr>-->
                        <cn:forEach items="${listObj}" var="travelData" varStatus="status">
                            <cn:set var="i" value="${status.index+1}"/>
                            <cn:set var="className" value="alt"/>
                            <cn:if test="${i%2==0}">
                                <cn:set var="className" value="altrow"/>
                            </cn:if>
                            <%--Travel history loop begins--%>
                            <tr class="${className}">
                            <td style="width:10%">
                          <%--      <c:choose>
                                    <c:when test="${travelData.system == 'G'}">
                                        <a style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}')">${travelData.tpReferenceId}</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}">${travelData.tpReferenceId}</a>
                                    </c:otherwise>
                                    </c:choose> --%>
                          <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}">${travelData.tpReferenceId}</a>
                            </td>
                            <fmt:formatDate value="${travelData.tpRequestedDate}" var="tpReqDate" pattern="dd-MM-yyyy" />
                            <td>${tpReqDate}</td>
                            <td>
                                <cn:choose>
                                    <cn:when test="${travelData.customerId == -1 && travelData.customerId != '' }">
                                        ${travelData.customerOthers}
                                    </cn:when>
                                    <cn:when test="${travelData.customerId!= 0 }">
                                        ${travelData.customerName}
                                    </cn:when>
                                    <cn:otherwise>
                                        --
                                    </cn:otherwise>
                                </cn:choose>
                            </td>
                            <td>
                                <cn:choose>
                                    <cn:when test="${travelData.projectId == -1 && travelData.projectId != '' }">
                                        ${travelData.projectOthers}
                                    </cn:when>
                                    <cn:when test="${travelData.projectId!= 0 }">
                                        ${travelData.projectName}
                                    </cn:when>
                                    <cn:otherwise>
                                        --
                                    </cn:otherwise>
                                </cn:choose>
                            </td>
                            <td>
                                <c:if test="${travelData.status == 's'}">Saved</c:if>
                                <c:if test="${travelData.status == 'o'}">Rejected</c:if>
                                <c:if test="${travelData.status == 'x'}">Cancelled</c:if>
                                <c:choose>
                                    <c:when test="${travelData.previous_status == 'f' && travelData.currentStatus == 'c'}">CFO Requested</c:when>
                                    <c:otherwise>
                                        <c:if test="${travelData.previous_status == 'q'}">Submitted</c:if>
                                        <c:if test="${travelData.previous_status == 'r'}">RM/PM Approved</c:if>
                                        <c:if test="${travelData.previous_status == 'b'}">BUH Approved</c:if>
                                        <c:if test="${travelData.previous_status == 'm'}">CEO Approved</c:if>
                                        <c:if test="${travelData.previous_status == 'f'}">Finance Approved</c:if>
                                        <c:if test="${travelData.previous_status == 'c'}">CFO Approved</c:if>
                                        <c:if test="${travelData.previous_status == 'a'}">Ticket Booked</c:if>
                                        <c:if test="${travelData.previous_status == 't'}">Money Deposited</c:if>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td align="center">
                                <%--   <c:choose>
                                    <c:when test="${travelData.system == 'G'}">
                                        <a style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}')"><img src="${pageContext.request.contextPath}/css/images/document-blue.png"></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}"><img src="${pageContext.request.contextPath}/css/images/document-blue.png"></a>
                                    </c:otherwise>
                                </c:choose> --%>
                                <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}"><img src="${pageContext.request.contextPath}/css/images/document-blue.png"></a>
                            </td>
<!--                            <td align="center">
                                <c:choose>
                                    <c:when test="${travelData.system == 'G' && travelData.furtherAdvance=='yes'}">
                                        <a class="cash" style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick ="getConfirmationForGermany('${pageContext.request.contextPath}/${forwardPage}&request_id=${travelData.tpId}&system=${travelData.system}')"></a>
                                    </c:when>
                                    <c:when test="${travelData.system == 'I' && travelData.furtherAdvance=='yes'}">
                                        <div id="advanceDiv">
                                            <a class="cash" style="text-decoration:none;color: #4C83B3;" href="#" onclick="getCss();" ></a> 
                                            <a class="cash" style="text-decoration:none;color: #4C83B3;" href="#" onclick="goToFurtherAdvance('${travelData.tpId}','${travelData.system}')" ></a> 
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        N/A
                                    </c:otherwise>
                                </c:choose>
                            </td>-->
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
            <form name="getAdvanceForm" id="getAdvanceForm" method="POST" action="addfurtherAdvance.htm" >
                <input type="hidden" name="tpIdForAdvance" id="tpIdForAdvance" value="" readonly   />
                <input type="hidden" name="systemForAdvance" id="systemForAdvance" value="" readonly />
            </form>
        </div>
<!--            <div class="lightbox-content">
                <%//@ include file="/WEB-INF/jsp/common/furtherAdvance.jsp" %>
            </div>-->
            <script type="text/javascript">
                function loadForm(page){
                    document.getDashBoardList.page.value = page;
                    document.getDashBoardList.submit();
                }
                function goToFurtherAdvance(tpId,system){
                    $("#tpIdForAdvance").val(tpId);
                    $("#systemForAdvance").val(system);
//                    $("#getAdvanceForm").action='addfurtherAdvance.htm';
                    $("#getAdvanceForm").submit();
                }
            </script>
    </body>
</html>
