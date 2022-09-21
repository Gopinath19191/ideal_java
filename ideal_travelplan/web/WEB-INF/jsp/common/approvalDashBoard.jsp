<%-- 
    Document   : approvalDashBoard
    Created on : Oct 11, 2012, 7:35:20 PM
    Author     : 15065
--%>
<html>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@page contentType="text/html" pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/header.jsp" %>
    <%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="cn" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <body>
    <head>
        <title>Travel Approval</title>
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
        <style>
            .notice_page{
                margin:0px 0px 10px 20px;
                width:94%;
            }
        </style>
        <script type="text/javascript">
            var base_path = $('#base_path').val();
            $(document).ready(function() {
                $("#filter_employeeName").autocomplete(base_path + "/ajaxsearch.htm?type=employee", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
                $("#filter_employeeName").result(function(event, data, formatted) {
                    if (data) {
                        $("#filter_employeeId").val(data[1]);
                    }
                });
            });

            function clearSearch() {
                $('#filter_employeeId').val("");
                $('#filter_employeeName').val("");
                $('#filter_travelType').val("");
                $('#dashboardList').submit();
            }

            function getConfirmationForHR(forward_page, system) {
            <c:forEach items="${disclaimer}" var="disclaimerValue" varStatus="disclaimerStatus">
                <%-- <c:if test="${disclaimerValue.disclaimerId == 'stake_holders_disclaimer_germany'}">
                    <c:set var="stake_holders_disclaimer_germany" value="${disclaimerValue.disclaimerName}" ></c:set>
                </c:if>--%>
                <c:if test="${disclaimerValue.disclaimerId == 'stake_holders_disclaimer_india'}">
                    <c:set var="stake_holders_disclaimer_india" value="${disclaimerValue.disclaimerName}" ></c:set>
                </c:if>
            </c:forEach>

                var stake_holders_disclaimer;
                // if(system == "G")
                //   stake_holders_disclaimer = "${fn:replace(stake_holders_disclaimer_germany,'#EMPNAME#',employeeName)}";
                // else
                stake_holders_disclaimer = "${fn:replace(stake_holders_disclaimer_india,'#EMPNAME#',employeeName)}";
                $('<div />').html('<font size="2.5">' + stake_holders_disclaimer + '</font>').dialog({
                    title: "Disclaimer",
                    buttons: {
                        "Click here to proceed": function() {
                            $(this).dialog("close");
                            window.location.href = forward_page;
                        }
                    }
                }).addClass("dialogClass");
                var dialog_width = 800;
                var dialog_height = 350;
                var top_px = 100;
                var left_px = (($(document).width() / 2) - (dialog_width / 2));
                $(".ui-dialog").css({"width": dialog_width, "top": top_px, "left": left_px});
                $(".ui-dialog-content").css({'height': dialog_height});
                $(".ui-widget-content").css({'background-color': '#FFF'});
                $(".ui-dialog-titlebar").css({'background-color': '#CEDFF1', 'border-color': '#CEDFF1', 'color': '#000'});
                $(".ui-dialog .ui-dialog-buttonpane button").css({'background-color': '#CEDFF1', 'border-color': '#CEDFF1', 'color': '#000'});
            }
            function formValidate(){
                if($('#filter_employeeName').val() == "" || $('#filter_employeeName').val() == "Search by Employee Number or Name"){
                    document.getElementById('filter_employeeId').value = "";
                    }
            }
        </script>
    </head>
    <div class="container_inner">
        <div class="page_heading" style="padding-top: 20px;margin:0px 10px 10px 0px;">
            ${displayName} ${listType} List
                <div style="float: right;color: #4C83B3;font-weight: bold;font-size: 12px;">
                    <img alt="View ${nextClickType} List" title="View ${nextClickType} List" src="${pageContext.request.contextPath}/css/images/icon_list.png" />
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getApprovalDashBoardList.htm?approveType=${nextClickTypeVar}&page=1">List ${nextClickType} Request</a>
                    <c:choose>
                        <c:when test="${displayName == 'RM/PM'}">
                            <img alt="View ${nextClickType} List" title="View ${nextClickType} List" src="${pageContext.request.contextPath}/css/images/icon_list.png" />
                            <a style="text-decoration:none;color: #4C83B3;" target="_self" href="listPendingApprovers.htm?approver_type=rm&type=pending">List Domestic Travel Request</a>
                        </c:when>
                        <c:when test="${displayName == 'BUH'}">
                            <img alt="View ${nextClickType} List" title="View ${nextClickType} List" src="${pageContext.request.contextPath}/css/images/icon_list.png" />
                            <a style="text-decoration:none;color: #4C83B3;" target="_self" href="listPendingApprovers.htm?approver_type=buh&type=pending">List Domestic Travel Request</a>
                        </c:when>
                        <c:when test="${displayName == 'Finance'}">
                            <img alt="View ${nextClickType} List" title="View ${nextClickType} List" src="${pageContext.request.contextPath}/css/images/icon_list.png" />
                            <a style="text-decoration:none;color: #4C83B3;" target="_self" href="listPendingApprovers.htm?approver_type=finance&type=pending">List Domestic Travel Request</a>
                        </c:when>
                        <c:when test="${displayName == 'Admin'}">
                            <img alt="View ${nextClickType} List" title="View ${nextClickType} List" src="${pageContext.request.contextPath}/css/images/icon_list.png" />
                            <a style="text-decoration:none;color: #4C83B3;" target="_self" href="listPendingApprovers.htm?approver_type=admin&type=pending">List Domestic Travel Request</a>
                        </c:when>
                        <c:when test="${displayName == 'Treasury'}">
                            <img alt="View ${nextClickType} List" title="View ${nextClickType} List" src="${pageContext.request.contextPath}/css/images/icon_list.png" />
                            <a style="text-decoration:none;color: #4C83B3;" target="_self" href="listPendingApprovers.htm?approver_type=treasury&type=pending">List Domestic Travel Request</a>
                        </c:when>
                    </c:choose>
                </div>
                <%--<c:if test="${displayName == 'Finance'}">--%>
    <!--                <div class="goToList" style="margin-right: 56px;margin-top: -21px;margin-bottom: 5px;">
                        <img alt="View ${nextClickType1} List" title="View ${nextClickType1} List" src="${pageContext.request.contextPath}/css/images/icon_list.png" />
                        <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getApprovalDashBoardList.htm?approveType=${nextClickTypeVar1}&page=1">List ${nextClickType1} Request</a><br><br>
                    </div>-->
                <%--</c:if>--%>
        </div>
    </div>
    <form action="getApprovalDashBoardList.htm" name="dashboardList" method="post" id="dashboardList" onsubmit="formValidate();">
        <div class="notice_page">
            <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" /></div>
            <div style="padding-left:10px;padding-top: 1px;">
                <ul class="notice_page_ul">
                    <li style="margin: 0px 0px 0px 10px;" >Please Click on "View Domestic Travel Request" link above for Domestic travel pending list</li>
                </ul>
            </div>
        </div>
        <div class="searchBox">
            <table width="100%" border="0" >
                <input type="hidden" id="page" name="page" value="${paging[0] > 1 ? paging[1]:'1'}" />
                <input type="hidden" id="approveType" name="approveType" value="${listType}" />
                <tr>
                    <td style="padding-left:10px;" width="25%">
                        <c:choose>
                            <c:when test="${filter_employeeName == '' || filter_employeeName == null}">
                                <c:set var="filter_employeeName" value="Search by Employee Number or Name"></c:set>
                            </c:when>
                            <c:otherwise>
                                <c:set var="filter_employeeName" value="${filter_employeeName}"></c:set>
                            </c:otherwise>
                        </c:choose>
                        <input type="text" id="filter_employeeName" value="${filter_employeeName}" style="width:250px;" onfocus="if (this.value == 'Search by Employee Number or Name')
                                    this.value = '';" onblur="if (this.value == '')
                                                this.value = 'Search by Employee Number or Name';" />
                        <input type="hidden" id="filter_employeeId" name="filter_employeeId" value="${commonFormData.filter_employeeId}" />
                        <input type="hidden" id="page" name="page" value="1" />
                    </td>
                    <%--<c:if test="${(displayName != 'Treasury') || (displayName == 'Treasury' && listType == 'processed')}">--%>
                    <td width="15%" align="right">Travel Type&nbsp;:</td>
                    <td width="20%">
                        <select name="filter_travelType" id="filter_travelType" class="selectbox_new" >
                            <option value="" >-- Select Travel Type --</option>
                            <option ${commonFormData.filter_travelType == 'D' ? 'selected':''} value="D" >Domestic</option>
                            <option ${commonFormData.filter_travelType == 'I' ? 'selected':''} value="I" >International</option>
                            <option ${commonFormData.filter_travelType == 'L' ? 'selected':''} value="L" >Local Conveyance</option>
                        </select>
                    </td>
                    <%--</c:if>--%>
                    <td align="left">
                        <a href="javascript:;" onClick="clearSearch();">Clear Search</a>&nbsp;&nbsp;&nbsp;
                        <input type="submit" name="submitButton" id="submitBtn" value="Find" class="submitbutton" />&nbsp;&nbsp;&nbsp;
                        <input type="submit" name="exportButton" id="excelsubmitBtn" value="Export" class="exportbutton" />
                    </td>
                </tr>
            </table>
        </div>
        <div id="datadisplay" class="formContent_new" >
            <table class="tableStructure" border="0" width="96%">
                <tr>
                    <th><a style="cursor:pointer" href="javascript:void(0)" onclick="getDetailsForApproval()" >Request Id</a></th>
                    <th><a href="#">Employee Id</a></th>
                    <th><a href="#">Employee Name</a></th>
                    <th><a href="#">Request Date</a></th>
                        <c:if test="${displayName == 'Treasury' && listType == 'pending'}">
                        <th><a href="#">Start Date</a></th>
                        <th><a href="#">End Date</a></th>
                        <th><a href="#">Currency</a></th>
                        <th><a href="#">Amount</a></th>
                        </c:if>
                    <th><a href="#">Travel Type</a></th>
                        <c:if test="${(displayName != 'Treasury') || (displayName == 'Treasury' && listType == 'processed')}">
                        <th><a href="#">Status</a></th>
                        </c:if>


                    <th><a href="#">Actions</a></th>
                </tr>
                <cn:choose>
                    <cn:when test="${fn:length(listObj)!=0}">
                        <cn:forEach items="${listObj}" var="travelData" varStatus="status">
                            <cn:set var="i" value="${status.index+1}"/>
                            <cn:set var="className" value="alt"/>
                            <cn:if test="${i%2==0}">
                                <cn:set var="className" value="altrow"/>
                            </cn:if>
                            <tr class="${className}">
                                <td style="width:10%">
                                    <c:choose>
                                        <c:when test="${travelData.system == 'G'}">
                                            <c:if test="${travelData.travelType == 'L'}">
                                                <a style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick="getConfirmationForHR('${pageContext.request.contextPath}/localconveyance/getLCViewDetails.htm?request_id=${travelData.tpId}&travel_type=${travelData.travelType}&approveType=${listType}&system=${travelData.system}', '${currEmpStatus}')">${travelData.tpReferenceId}</a>
                                            </c:if>
                                            <c:if test="${travelData.travelType == 'D' || travelData.travelType == 'I'}">
                                                <a style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick="getConfirmationForHR('${pageContext.request.contextPath}/viewTravel.htm?request_id=${travelData.tpId}&approveType=${listType}&travel_type=${travelData.travelType}&system=${travelData.system}', '${currEmpStatus}')">${travelData.tpReferenceId}</a>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${travelData.travelType == 'L'}">
                                                <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/localconveyance/getLCViewDetails.htm?request_id=${travelData.tpId}&travel_type=${travelData.travelType}&approveType=${listType}&system=${travelData.system}">${travelData.tpReferenceId}</a>
                                            </c:if>
                                            <c:if test="${travelData.travelType == 'D' || travelData.travelType == 'I'}">
                                                <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/viewTravel.htm?request_id=${travelData.tpId}&approveType=${listType}&travel_type=${travelData.travelType}&system=${travelData.system}">${travelData.tpReferenceId}</a>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <fmt:formatDate value="${travelData.tpRequestedDate}" var="tpReqDate" pattern="dd-MM-yyyy" />
                                <td>${travelData.employeeNumber}</td>
                                <td>${travelData.employeeName}</td>
                                <td>${tpReqDate}</td>
                                <c:if test="${displayName == 'Treasury' && listType == 'pending'}">
                                    <td>${travelData.startDate}</td>
                                    <td>${travelData.endDate}</td>
                                    <td>${travelData.currency_type}</td>
                                    <td>${travelData.amount}</td>
                                </c:if>
                                <td>
                                    <c:if test="${travelData.travelType == 'L'}">Local Conveyance</c:if>
                                    <c:if test="${travelData.travelType == 'I'}">International</c:if>
                                    <c:if test="${travelData.travelType == 'D'}">Domestic</c:if>
                                    </td>
                                <c:if test="${(displayName != 'Treasury') || (displayName == 'Treasury' && listType == 'processed')}">
                                    <td>
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
                                                <c:if test="${travelData.currentStatus == 's'}">Saved</c:if>
                                                <c:if test="${travelData.currentStatus == 'o'}">Rejected</c:if>
                                                <c:if test="${travelData.currentStatus == 'x'}">Cancelled</c:if>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </c:if>


                                <td>
                                    <c:choose>
                                        <c:when test="${travelData.system == 'G'}">
                                            <c:if test="${travelData.travelType == 'L'}">
                                                <a style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick="getConfirmationForHR('${pageContext.request.contextPath}/localconveyance/getLCViewDetails.htm?request_id=${travelData.tpId}&travel_type=${travelData.travelType}&approveType=${listType}&system=${travelData.system}', '${currEmpStatus}')"><img src="${pageContext.request.contextPath}/css/images/application_view_list.png"></a>
                                                </c:if>
                                                <c:if test="${travelData.travelType == 'D' || travelData.travelType == 'I'}">
                                                <a style="text-decoration:none;color: #4C83B3;" href="javascript:;" onClick="getConfirmationForHR('${pageContext.request.contextPath}/viewTravel.htm?request_id=${travelData.tpId}&approveType=${listType}&travel_type=${travelData.travelType}&system=${travelData.system}', '${currEmpStatus}')"><img src="${pageContext.request.contextPath}/css/images/application_view_list.png"></a>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <c:if test="${travelData.travelType == 'L'}">
                                                <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/localconveyance/getLCViewDetails.htm?request_id=${travelData.tpId}&travel_type=${travelData.travelType}&approveType=${listType}&system=${travelData.system}"><img src="${pageContext.request.contextPath}/css/images/application_view_list.png"></a>
                                                </c:if>
                                                <c:if test="${travelData.travelType == 'D' || travelData.travelType == 'I'}">
                                                <a style="text-decoration:none;color: #4C83B3;" href="${pageContext.request.contextPath}/viewTravel.htm?request_id=${travelData.tpId}&approveType=${listType}&travel_type=${travelData.travelType}&system=${travelData.system}"><img src="${pageContext.request.contextPath}/css/images/application_view_list.png"></a>
                                                </c:if>
                                            </c:otherwise>
                                        </c:choose>
                                </td>
                            </tr>
                        </cn:forEach>
                    </cn:when>
                    <cn:otherwise>
                        <tr>
                            <td colspan="7" align="center"><font color="red" size="2">No Travel Requests found.Please modify your search</font></td>
                        </tr>
                    </cn:otherwise>
                </cn:choose>
            </table>
            <cn:if test="${paging[0] > 1}">
                <%@include file="/WEB-INF/jsp/paging.jsp" %>
            </cn:if>
        </div>
    </form>
</body>
<script type="text/javascript" >
    function getDetailsForApproval(tpId, url, tpRequestorId, system) {
        //alert("----" + tpId + "---" + url + "--" + system);
        $("#dashboardTpId").val(tpId);
        $("#tpRequestorId").val(tpRequestorId);
        $("#system").val(system);
        $("#dashBoardForm").action = url;
        $("#dashBoardForm").submit();
    }

    function loadForm(page) {
        $('#page').val(page);
        $('#dashboardList').submit();
    }
</script>
</html>

