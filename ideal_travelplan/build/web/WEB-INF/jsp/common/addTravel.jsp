<%-- 
    Document   : domestic
    Created on : Oct 8, 2012, 4:19:16 PM
    Author     : 15065
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <%@include file="/WEB-INF/header.jsp" %>
        <title>Domestic Travel</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simpletip-1.3.1.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
        <style type="text/css">
            .error {
                color:red;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function() {
                $(".helpTooltip").simpletip({
                    content: 'This is not a mandatory field. However Admin team / Travel desk might need this to reach out to you for any clarifications/changes/emergency',
                    fixed: true
               });

                $("#Yes").click(function() {
                        $("#buh_id").removeAttr("disabled");
                    });
              $("#No").click(function() {
                        $("#buh_id").attr("disabled","disabled");
                    });
           });

             

        </script>
    </head>
    <body>
        <form action="submitTravel.htm" method="post" name="travelRequest" id="travelRequest" enctype="multipart/form-data" onSubmit="return validateTravelSubmit()">
            <c:if test="${travel_type == 'D'}">
                <c:set value="Domestic" var="travelTypeText"></c:set>
            </c:if>
            <c:if test="${travel_type == 'I'}">
                <c:set value="International" var="travelTypeText"></c:set>
            </c:if>
            <div id="tRequest" style="margin-top:0px">
                <div class="container_inner">
                    <div class="page_heading">${travelTypeText} Travel Request
                        <div class="goToList">
                            <c:if test="${travel_type == 'D'}">
                                <c:set value="20140730151152_594094" var="documentNumber"></c:set>
                            </c:if>
                            <c:if test="${travel_type == 'I'}">
                                <c:set value="20140730151153_222461" var="documentNumber"></c:set>
                            </c:if>
                            <a class="showList" target="_self" href="${pageContext.request.contextPath}/getDashBoardList.htm?page=1">List Requests</a>
                            <!--<a class="showList" style="text-decoration:none;color: #4C83B3;" target="_blank" href="http://ideal.hindujatech.com/uploads/document_uploads/policy_document/${travelTypeText} Travel Policy_${documentNumber}.pdf">View ${travelTypeText} Travel Policy</a>-->
                        </div>
                    </div>
                    <div class="breadcrumb"></div>
                    <div class="notice_page">
                        <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" /></div>
                        <div style="padding-left:10px;padding-top: 1px;">
                            <ul class="notice_page_ul">
                                <li style="margin: -13px 0 5px 10px;" >Travel Request should be raised atleast 2 working days prior to the travel</li>
                                <li style="margin: 0 0 5px 10px;" >Fields marked in * are mandatory.</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <div class="datadisplay">
                        <table class="travelDetails_new" cellspacing="3" cellpadding="3">
                            <tr width="100%" >
                                <td valign="top">
                                    <input type="hidden" name="action" id="action" value="">
                                    <input type="hidden" name="tpReferenceId" id="tpReferenceId" value="${travelData.tpReferenceId}">
                                    <input type="hidden" name="master_id" id="master_id" value="${travelData.master_id}">
                                    <input type="hidden" name="currentStatus" id="currentStatus" value="${travelData.currentStatus}">
                                    <input type="hidden" name="previous_status" id="previous_status" value="${travelData.previous_status}">
                                    <input type="hidden" name="tpWorkFlowId" id="tpWorkFlowId" value="${travelData.workflow_id}">
                                    <input type="hidden" value="${travel_type}" name="travelType" id="travelType">
                                    <input type="hidden" value="${system}" name="system" id="system">
                                    <label for="requestDate" style="font-weight: bolder">Travel Request Date</label>
                                </td>
                                <td valign="top">
                                    <fmt:formatDate value="${now}" var="currentDate" type="both" pattern="dd-MM-yyyy" />
                                    <c:choose>
                                        <c:when test="${travelData.out_travel_requested_date != null && travelData.out_travel_requested_date != ''}">
                                            <input type="text" name="travel_requested_date" class="textbox_new datePick"  id="travel_requested_date" value="${travelData.out_travel_requested_date}">
                                        </c:when>
                                        <c:otherwise>
                                            <input type="text" name="travel_requested_date" class="textbox_new datePick"  id="travel_requested_date" value="${currentDate}">
                                        </c:otherwise>
                                    </c:choose>
                                    <span id="travel_requested_date_error" class="error"></span>
                                </td>
                                <td valign="top">
                                    <label for="empId" style="font-weight: bolder">Employee Id</label>
                                    <input type="hidden" name="employee_id" class="textbox_new"  id="employee_id"  value="${data.employee_id}">
                                </td>
                                <td valign="top">
                                    <input type="text" name="empId" class="textbox_new" disabled="true" id="empId"  value="${data.employee_number}">
                                </td>
                                <td valign="top">
                                    <label for="empName" style="font-weight: bolder">Employee Name</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="employeeName" class="textbox_new" disabled="true" id="employeeName"  value="${data.employee_name}">
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <label for="designation" style="font-weight: bolder">Designation</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="designation" class="textbox_new" disabled="true" id="designation"  value="${data.designation}">
                                </td>

                                <td valign="top">
                                    <label for="unit" style="font-weight: bolder">Practice Group</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="unit" class="textbox_new" disabled="true" id="unit"  value="${data.group_name}">
                                </td>
                                <td valign="top">
                                    <label for="department" style="font-weight: bolder">Sub Practice Group</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="department" class="textbox_new" disabled="true" id="department"  value="${data.sub_group_name}">
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <label for="level" style="font-weight: bolder">Level</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="level" class="textbox_new" disabled="true" id="level" value="${data.band_name}">
                                </td>
                                <td valign="top">
                                    <label for="baseLocation" style="font-weight: bolder">Base Location</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="baseLocation" class="textbox_new" disabled="true" id="level" value="${data.city_name}">
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <label for="travel_start_date" style="font-weight: bolder">Travel Start Date</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="travel_start_date" class="textbox_new datePick"  id="travel_start_date"  onChange="calculateDateDiff();" readonly="true" value="${travelData.out_travel_start_date !=''?travelData.out_travel_start_date:'Choose Date'}">
                                    <span id="travel_start_date_error" class="error"></span>
                                </td>
                                <td valign="top">
                                    <label for="travel_end_date" style="font-weight: bolder">Travel End Date</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="travel_end_date" class="textbox_new datePick"  id="travel_end_date"  onChange="calculateDateDiff();" readonly="true" value="${travelData.out_travel_end_date !=''?travelData.out_travel_end_date:'Choose Date'}">
                                    <span id="travel_end_date_error" class="error"></span>
                                </td>
                                <td valign="top">
                                    <label for="travel_term" style="font-weight: bolder">Travel Term</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="travel_term" readonly class="textbox_new"  id="travel_term"  value="${travelData.travel_term}" >
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <label for="travelBillable" style="font-weight: bolder">Project Travel</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <select name="travel_billable" id="travel_billable" class="selectbox_new" >
                                        <option value="">--Select Billable--</option>
                                        <option value="N" ${travelData.travel_billable == 'N'?'selected':''} >No</option>
                                        <option value="Y" ${travelData.travel_billable == 'Y'?'selected':''}>Yes</option>
                                    </select>
                                    <span id="travel_billable_error" class="error"></span>
                                </td>

                                <td valign="top">
                                    <label for="customer_id" id="customer" style="font-weight: bolder">Customer</label> <label id="lblCustomer" style="color: red;display:none" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <select name="customer_id" id="customer_id" class="selectbox_new" onchange="loadProject(this.value);">
                                        <option value="">--Select Customer--</option>
                                        <c:forEach items="${customerList}" var="custList" >
                                            <option ${travelData.customer_id == custList.customer_id ?'selected':''} value="${custList.customer_id}">${custList.customer_name}</option>
                                        </c:forEach>
                                        <option value="-1" ${travelData.customer_id == -1 ?'selected':''}>Others</option>
                                    </select>
                                    <span id="customer_id_error" class="error"></span>
                                </td>
                                <td valign="top">
                                    <label style="font-weight: bolder">Project</label><label id="lblProject" style="color: red;display:none" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <select name="project_id" id="project_id" class="selectbox_new" onchange="showProjectOthers(this.value);">
                                        <option value="">--Select Project--</option>
                                        <c:forEach items="${projectList}" var="prjList" >
                                            <option ${travelData.project_id == prjList.project_id ?'selected':''} value="${prjList.project_id}">${prjList.project_name}</option>
                                        </c:forEach>
                                        <option ${travelData.project_id == -1 ?'selected':''} value="-1">Others</option>
                                    </select>
                                    <span id="project_id_error" class="error"></span>
                                </td>
                            </tr>
                            <tr id="othersRow" style="display:${!((travelData.project_id == -1 || travelData.customer_id == -1))?'none':''}" >
                                <td colspan="2">&nbsp;</td>
                                <td id="customerOthersLabel" style="visibility:${travelData.customer_id == -1?'visible':'hidden'}">
                                    <label style="font-weight: bolder">Customer (Others)</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td id="customerOthers" style="visibility:${travelData.customer_id == -1?'visible':'hidden'}">
                                    <input type="text" name="customer_others" class="textbox_new" id="customer_others" maxlength="50" value="${travelData.customer_others}">
                                    <span id="customer_others_error" class="error"></span>
                                </td>
                                <td id="projectOthersLabel" style="visibility:${travelData.project_id == -1?'visible':'hidden'}">
                                    <label style="font-weight: bolder">Project (Others)</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td id="projectOthers" style="visibility:${travelData.project_id == -1?'visible':'hidden'}">
                                    <input type="text" name="project_others" class="textbox_new" id="project_others" maxlength="50" value="${travelData.project_others}">
                                    <span id="project_others_error" class="error"></span>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <label for="client_reimbursable" style="font-weight: bolder">Reimbursable by Client</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <select name="client_reimbursable" id="client_reimbursable" class="selectbox_new" onchange="">
                                        <option value="">--Select Reimbursable--</option>
                                        <option value="N" ${travelData.client_reimbursable == 'N'?'selected':''} >No</option>
                                        <option value="Y" ${travelData.client_reimbursable == 'Y'?'selected':''}>Yes</option>
                                    </select>
                                    <span id="client_reimbursable_error" class="error"></span>
                                </td>
                                <td valign="top">
                                    <label for="advanceRequired" style="font-weight: bolder">Advance</label>
                                </td>
                                <td valign="top">
                                    <input type="text" name="advance_required" class="textbox_new" id="advance_required" maxlength="6" value="${travelData.advance_required}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);">
                                    <label for="advanceRequired">&nbsp;&nbsp;&nbsp;(If not needed, enter Zero)</label>
                                </td>
                                <td valign="top">
                                    <label for="travelPurpose" style="font-weight: bolder">Currency</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <select name="currency_type" id="currency_type" class="selectbox_new">
                                        <option value="">--Select Currency--</option>
                                        <c:forEach items="${currencyList}" var="curList" >
                                            <option ${travelData.currency_type == curList.key ?'selected':''} value="${curList.key}">${curList.value}</option>
                                        </c:forEach>
                                    </select>
                                    <span id="currency_type_error" class="error"></span>
                                </td>
                            </tr>
                            <tr>

                                <td valign="top">
                                    <label for="travelPurpose" style="font-weight: bolder">Purpose of Travel</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <textarea name="travel_purpose" class="textarea_new"  cols="20" rows="2" id="travel_purpose" >${travelData.travel_purpose}</textarea>
                                    <span id="travel_purpose_error" class="error"></span>
                                </td>
                                <td valign="top">
                                    <label for="empRemarks" style="font-weight: bolder">Travel Remarks</label>
                                </td>
                                <td valign="top">
                                    <textarea name="emp_remarks" class="textarea_new"  cols="20" rows="2" id="emp_remarks" >${travelData.emp_remarks}</textarea>
                                </td>
                                <td valign="top">
                                    <label for="travelPurpose" style="font-weight: bolder">Contact Number</label>
                                    <c:if test="${system != 'G'}">
                                        <label style="color: red;" for="fine">*</label>
                                    </c:if>
                                </td>
                                <td valign="top">
                                    <input type="text" name="mobileNo" class="textbox_new" id="mobileNo" maxlength="15" value="${travelData.mobileNo}">
                                    <c:if test="${system == 'G'}">
                                        <a class="helpTooltip" href="javascript:;"></a>
                                    </c:if>
                                    <span id="mobileNo_error" class="error"></span>
                                </td>
                            </tr>
                            <tr>
                                <td valign="top">
                                    <label for="guest_booking" style="font-weight: bolder">Guest Booking</label><label style="color: red;" for="fine">*</label>
                                </td>
                                <td valign="top">
                                    <select name="guest_booking" id="guest_booking" class="selectbox_new" onchange="GuestBooking(this.value)">
                                        <option value="">--Guest Booking--</option>
                                            <option ${travelData.guest_booking == 'N'?'selected':''} value="N">No</option>
                                            <option ${travelData.guest_booking == 'Y'?'selected':''} value="Y">Yes</option>
                                    </select>
                                    <span id="guest_booking_error" class="error"></span>
                                </td>
                                <td valign="top">
                                    <div id="guestLabel" style="display:${travelData.guest_booking == 'Y'?'block':'none'};"><label for="empRemarks" style="font-weight: bolder">Guest Name</label></div>
                                </td>
                                <td valign="top">
                                    <div id="guestOutput" style="display:${travelData.guest_booking == 'Y'?'block':'none'};">
                                        <input type="text" name="guest_name" class="textbox_new" id="guest_name" maxlength="50" value="${travelData.guest_name}">
                                        <span id="guest_name_error" class="error"></span>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td> 
                                    <label style="font-weight: bolder"> Current BUH Approver </label>
                                </td>
                                 <td >
                                      <input type="text" name="buhName" class="textbox_new"  id="buhName" disabled="true"  value="${buh.buhsName}">
                                </td>

                            </tr>
                            <tr>
                                 <td >
                                       <label for="buhapprover" style="font-weight: bolder">Do you want to change your BUH </label>
                                 </td>

                                 <td>
                                     <c:if test="${travelData.buh_change == 'N'}">
                                     <input name="discount" type="radio" id="Yes" value="Yes"/>Yes
                                     <input name="discount" type="radio" id="No" value="No" checked="checked" />No<br />                                  
                                     </c:if>
                                     <c:if test="${travelData.buh_change == 'Y'}">
                                     <input name="discount" type="radio" id="Yes" value="Yes" checked="checked" />Yes
                                     <input name="discount" type="radio" id="No" value="No"  />No<br />                                  
                                     </c:if>
                                  
                                 </td>
                                 <td colspan="4">
                                      <select name="change_buh_id" id="buh_id" disabled="disabled">
                                        <option value="">--Select BUH--</option>
                                       <c:forEach items="${BuhList}" var="BUHList" >
                                              <option ${travelData.buh_id == BUHList.emp_id ?'selected':''} value="${BUHList.emp_id}">${BUHList.buhName}</option>
                                       </c:forEach>
                                      </select>
                           
                                     </td>
                            </tr>


                            <c:if test="${employeeId == '775' && travel_type == 'I'}">
                                <tr>
                                    <td colspan="2">Is your travel includes cities in US/Europe/South Africa/UAE countries?*</td>
                                    <td>
                                        <input type="radio" value="Y" ${travelData.travel_other_country == 'Y'?'checked':''} name="travel_other_country" id="travel_other_countryY" />&nbsp;Yes
                                        <input type="radio" value="N" ${travelData.travel_other_country == 'N' || travelData.travel_other_country == ''?'checked':''} name="travel_other_country" id="travel_other_countryN" />&nbsp;No
                                    </td>
                                </tr>
                            </c:if>
                        </table>
                        <div class="contentHolderMax">
                            <div class="style_tab">
                                <div id="ticketOption">
                                    <span id="TicketSpan" onclick="changeTab('Ticket',this);" class="ticket_tab active_tab" > Ticket </span>
                                </div>
                                <div id="hotelOption">
                                    <span id="HotelSpan" onclick="changeTab('Hotel',this);" class="ticket_tab" > Hotel </span>
                                </div>
                                <c:if test="${data.band_id == 2 || data.band_id == 3 || data.band_id == 35 || data.band_id == 36}" >
                                    <div id="conveyanceOption">
                                        <span id="ConveyanceSpan" onclick="changeTab('Conveyance',this);" class="ticket_tab" > Conveyance </span>
                                    </div>
                                </c:if>
                                <div id="fileOption" >
                                    <span id="AttachmentSpan" onclick="changeTab('Attachment',this);" class="ticket_tab" > Attachment </span>
                                </div>
                                <c:if test="${travel_type == 'I'}">
                                    <div id="visaOption" >
                                        <span id="VisaSpan" onclick="changeTab('Visa',this);" class="ticket_tab" > Visa </span>
                                    </div>
                                </c:if>
                            </div>
                        </div>
                        <div id="AttachmentTab" style="display: none;" >
                            <input type="hidden" value="1" id="attachmentCount" name="attachmentCount" />
                            <table><tr><td height="20">&nbsp;</td></tr></table>
                            <table class="tableStructure" border="0" width="50%">
                                <tr class="headerCenter">
                                    <th>File Name / File Upload<label style="color: red;" for="fine">*</label></th>
                                    <th>Action</th>
                                </tr>
                                <input type="hidden" value="${fn:length(attachmentData)}" id="attach_count" name="attach_count" />
                                <c:forEach items="${attachmentData}" var="aData" varStatus="aDataStatus">
                                    <c:if test="${aData.fileName != '' && aData.fileName != null}">
                                        <tr id="attachTR_${aDataStatus.count}">
                                            <td align="center">
                                                <input type="hidden" value="${aData.attachment_id}" name="attachment_id_${aDataStatus.count}" id="attachment_id_${aDataStatus.count}" />
                                                <input type="hidden" value="0" name="attach_del_${aDataStatus.count}" id="attach_del_${aDataStatus.count}" />
                                                <a href="fileDownload.htm?fileName=${aData.fileName}"><c:out value="${aData.fileName}"></c:out></a>
                                            </td>
                                            <td align="center">
                                                <img onClick="deleteAttachment(${aDataStatus.count})" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                                <tr id="tr_attachment_1">
                                    <input type="hidden" value="0" name="attachmentdeleted_1" id="attachmentdeleted_1" />
                                    <td align="center">
                                        <input type="file" name="attach_doc_1" id="attach_doc_1" class="filebox" size="20" >
                                    </td>
                                    <td align="center" id="attachment_actionItems_1">
                                        <img onClick="addAttachment(this,1)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="TicketTab" class="tabContent" >
                            <table class="tableStructure" border="0" width="90%">
                                <tr class="headerCenter">
                                    <th width="18%">From<label style="color: red;" for="fine">*</label></th>
                                    <th width="18%">To<label style="color: red;" for="fine">*</label></th>
                                    <th width="12%">Date of Travel<label style="color: red;" for="fine">*</label></th>
                                    <th width="14%">Travel Preference<label style="color: red;" for="fine">*</label></th>
                                    <th width="14%">Mode of Travel<label style="color: red;" for="fine">*</label></th>
                                    <th width="20%">Remarks</th>
                                    <th width="4%">Action</th>
                                </tr>
                                <c:choose>
                                    <c:when test="${!empty(ticketData)}">
                                        <input type="hidden" value="${fn:length(ticketData)}" id="ticketCount" name="ticketCount" />
                                        <c:forEach items="${ticketData}" var="tData" varStatus="tDataStatus">
                                            <tr id="tr_ticket_${tDataStatus.count}">
                                                <input type="hidden" value="0" name="ticketdeleted" id="ticketdeleted_${tDataStatus.count}" />
                                                <input type="hidden" name="ticket_id" id="ticket_id_${tDataStatus.count}" value="${tData.out_ticket_id}" />
                                                <td align="center">
                                                    <c:choose>
                                                        <c:when test="${travel_type == 'I'}">
                                                            <div>
                                                                <select name="from_country" id="from_country_${tDataStatus.count}" class="inputbox">
                                                                    <option value="">--Country--</option>
                                                                    <c:forEach items="${countryList}" var="countryArr" >
                                                                        <option ${tData.out_from_country == countryArr.countryId ?'selected':''} value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <span id="from_country_error_${tDataStatus.count}" class="error" ></span>
                                                            </div>&nbsp;
                                                        </c:when>
                                                        <c:otherwise>
                                                             <input type="hidden" value="" name="from_country" id="from_country_${tDataStatus.count}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <div>
                                                        <input type="hidden" name="from_city_id" id="from_city_id_${tDataStatus.count}" value="${tData.out_from_city_id}" class="hiddenbox" />
                                                        <input type="text" name="from_city" id="from_city_${tDataStatus.count}" class="inputbox autobox" value="${(tData.out_from_city_others != '')?tData.out_from_city_others:(tData.out_from_city)}" >
                                                        <span id="from_city_error_${tDataStatus.count}" class="error" ></span>
                                                    </div>
                                                </td>
                                                <td align="center">
                                                    <c:choose>
                                                        <c:when test="${travel_type == 'I'}">
                                                            <div>
                                                                <select name="to_country" id="to_country_${tDataStatus.count}" class="inputbox">
                                                                    <option value="">--Country--</option>
                                                                    <c:forEach items="${countryList}" var="countryArr" >
                                                                        <option ${tData.out_to_country == countryArr.countryId ?'selected':''} value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <span id="to_country_error_${tDataStatus.count}" class="error" ></span>
                                                            </div>&nbsp;
                                                        </c:when>
                                                        <c:otherwise>
                                                             <input type="hidden" value="" name="to_country" id="to_country_${tDataStatus.count}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <div>
                                                        <input type="hidden" name="to_city_id" id="to_city_id_${tDataStatus.count}" value="${tData.out_to_city_id}" class="hiddenbox" />
                                                        <input type="text" name="to_city" id="to_city_${tDataStatus.count}" class="inputbox autobox" value="${(tData.out_to_city_others != '')?tData.out_to_city_others:(tData.out_to_city)}">
                                                        <span id="to_city_error_${tDataStatus.count}" class="error" ></span>
                                                    </div>
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="travel_date" class="inputbox datePick"  id="travel_date_${tDataStatus.count}" readonly="true" value="${tData.out_travel_date !=''?tData.out_travel_date:''}">
                                                    <span id="travel_date_error_${tDataStatus.count}" class="error" ></span>
                                                </td>
                                                <td align="center">
                                                    <select name="travel_preference" id="travel_preference_${tDataStatus.count}" class="inputbox">
                                                        <option value="">-- Preference --</option>
                                                        <option ${tData.out_travel_preference == 'Morning'?'selected':''} value="Morning">Morning</option>
                                                        <option ${tData.out_travel_preference == 'Afternoon'?'selected':''} value="Afternoon">Afternoon</option>
                                                        <option ${tData.out_travel_preference == 'Evening'?'selected':''} value="Evening">Evening</option>
                                                        <option ${tData.out_travel_preference == 'Night'?'selected':''} value="Night">Night</option>
                                                    </select>
                                                    <span id="travel_preference_error_${tDataStatus.count}" class="error" ></span>
                                                </td>
                                                <td align="center">
                                                    <select name="travel_mode" id="travel_mode_${tDataStatus.count}" class="inputbox">
                                                        <option value="">-- Mode --</option>
                                                        <option ${tData.out_travel_mode == 'Air'?'selected':''} value="Air">Air</option>
                                                        <option ${tData.out_travel_mode == 'Bus'?'selected':''} value="Bus">Road</option>
                                                        <option ${tData.out_travel_mode == 'Train'?'selected':''} value="Train">Rail</option>
                                                    </select>
                                                    <span id="travel_mode_error_${tDataStatus.count}" class="error" ></span>
                                                </td>
                                                <td align="center">
                                                    <textarea name="ticket_remarks" class="textareaTravel"  cols="20" rows="2" id="ticket_remarks_${tDataStatus.count}" onKeyUp="checkInputLength(this,'Remarks can contains upto 200 characters ');">${tData.out_ticket_remarks}</textarea>
                                                </td>
                                                <td align="center" id="ticket_actionItems_${tDataStatus.count}">
                                                    <img onClick="addTicket(this,${tDataStatus.count})" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                    <img onClick="return removeTicketTR(${tDataStatus.count})" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" value="1" id="ticketCount" name="ticketCount" />
                                        <tr id="tr_ticket_1">
                                            <input type="hidden" value="0" name="ticketdeleted" id="ticketdeleted_1" />
                                            <input type="hidden" value="" name="ticket_id" id="ticket_id_1" />
                                            <td align="center">
                                                <c:choose>
                                                    <c:when test="${travel_type == 'I'}">
                                                        <div>
                                                            <select name="from_country" id="from_country_1" class="inputbox">
                                                                <option value="">--Country--</option>
                                                                <c:forEach items="${countryList}" var="countryArr" >
                                                                    <option value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <span id="from_country_error_1" class="error" ></span>
                                                        </div>&nbsp;
                                                    </c:when>
                                                    <c:otherwise>
                                                         <input type="hidden" value="" name="from_country" id="from_country_1" />
                                                    </c:otherwise>
                                                </c:choose>
                                                <div>
                                                    <input type="hidden" name="from_city_id" id="from_city_id_1" value="" class="hiddenbox" />
                                                    <input type="text" name="from_city" id="from_city_1" class="inputbox autobox"  value="" >
                                                    <span id="from_city_error_1" class="error" ></span>
                                                </div>
                                            </td>
                                            <td align="center">
                                                <c:choose>
                                                    <c:when test="${travel_type == 'I'}">
                                                        <div>
                                                            <select name="to_country" id="to_country_1" class="inputbox">
                                                                <option value="">--Country--</option>
                                                                <c:forEach items="${countryList}" var="countryArr" >
                                                                    <option value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <span id="to_country_error_1" class="error" ></span>
                                                        </div>&nbsp;
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" value="" name="to_country" id="to_country_1" />
                                                    </c:otherwise>
                                                </c:choose>
                                                <div>
                                                    <input type="hidden" name="to_city_id" id="to_city_id_1" value="" class="hiddenbox" />
                                                    <input type="text" name="to_city" id="to_city_1" class="inputbox autobox"  value="" >
                                                    <span id="to_city_error_1" class="error" ></span>
                                                </div>
                                            </td>
                                            <td align="center">
                                                <input type="text" name="travel_date" class="inputbox datePick"  id="travel_date_1" readonly="true" value="">
                                                <span id="travel_date_error_1" class="error" ></span>
                                            </td>
                                            <td align="center">
                                                <select name="travel_preference" id="travel_preference_1" class="inputbox">
                                                    <option value="">-- Preference --</option>
                                                    <option value="Morning">Morning</option>
                                                    <option value="Afternoon">Afternoon</option>
                                                    <option value="Evening">Evening</option>
                                                    <option value="Night">Night</option>
                                                </select>
                                                <span id="travel_preference_error_1" class="error" ></span>
                                            </td>
                                            <td align="center">
                                                <select name="travel_mode" id="travel_mode_1" class="inputbox">
                                                    <option value="">-- Mode --</option>
                                                    <option value="Air">Air</option>
                                                    <option value="Bus">Road</option>
                                                    <option value="Train">Rail</option>
                                                </select>
                                                <span id="travel_mode_error_1" class="error" ></span>
                                            </td>
                                            <td align="center">
                                                <textarea name="ticket_remarks" class="textareaTravel"  cols="20" rows="2" id="ticket_remarks_1" onKeyUp="checkInputLength(this,'Remarks can contains upto 200 characters ');"></textarea>
                                            </td>
                                            <td align="center" id="ticket_actionItems_1">
                                                <img onClick="addTicket(this,1)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                                
                            </table>
                        </div>
                        <div id="HotelTab" class="tabContent" style="display: none;">
                            <table class="tableStructure" border="0" width="90%">
                                <tr class="headerCenter">
                                    <th width="20%">City/Town<label style="color: red;" for="fine">*</label></th>
                                    <th width="20%">Location Preference<c:if test="${system != 'G'}"><label style="color: red;" for="fine">*</label></c:if></th>
                                    <th width="15%">From Date<label style="color: red;" for="fine">*</label></th>
                                    <th width="15%">To Date<label style="color: red;" for="fine">*</label></th>
                                    <th width="26%">Remarks</th>
                                    <th width="4%">Action</th>
                                </tr>
                                <c:choose>
                                    <c:when test="${!empty(hotelData)}">
                                        <input type="hidden" id="hotelCount" name="hotelCount" value="${fn:length(hotelData)}" />
                                        <c:forEach items="${hotelData}" var="hData" varStatus="hDataStatus">
                                            <tr id="tr_hotel_${hDataStatus.count}">
                                                <input type="hidden" value="0" name="hoteldeleted" id="hoteldeleted_${hDataStatus.count}" />
                                                <input type="hidden" name="hotel_id" id="hotel_id_${hDataStatus.count}" value="${hData.out_hotel_id}" />
                                                <td align="center">
                                                    <c:choose>
                                                        <c:when test="${travel_type == 'I'}">
                                                            <div>
                                                                <select name="country" id="country_${hDataStatus.count}" class="inputbox">
                                                                    <option value="">--Country--</option>
                                                                    <c:forEach items="${countryList}" var="countryArr" >
                                                                        <option ${hData.out_country == countryArr.countryId ?'selected':''} value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                                    </c:forEach>
                                                                </select>
                                                                <span id="country_error_${hDataStatus.count}" class="error" ></span>
                                                            </div>&nbsp;
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="hidden" value="" name="country" id="country_${hDataStatus.count}" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <div>
                                                        <input type="hidden" name="city_id" id="city_id_${hDataStatus.count}" value="${hData.out_city_id}" class="hiddenbox" />
                                                        <input type="text" name="city" id="city_${hDataStatus.count}" class="inputbox autobox" value="${(hData.out_city_others != '')?hData.out_city_others:(hData.out_city)}">
                                                        <span id="city_error_${hDataStatus.count}" class="error" ></span>
                                                    </div>
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="location" id="location_${hDataStatus.count}" class="inputbox" value="${hData.out_location}">
                                                    <span id="location_error_${hDataStatus.count}" class="error" ></span>
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="from_date" class="inputbox datePick"  id="from_date_${hDataStatus.count}" readonly="true" value="${hData.out_from_date !=''?hData.out_from_date:''}">
                                                    <span id="from_date_error_${hDataStatus.count}" class="error" ></span>
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="to_date" class="inputbox datePick"  id="to_date_${hDataStatus.count}" readonly="true" value="${hData.out_to_date !=''?hData.out_to_date:''}">
                                                    <span id="to_date_error_${hDataStatus.count}" class="error" ></span>
                                                </td>
                                                <td align="center">
                                                    <textarea name="hotel_remarks" class="textareaTravel"  cols="28" rows="2" id="hotel_remarks_${hDataStatus.count}">${hData.out_hotel_remarks}</textarea>
                                                    <span id="hotel_remarks_error_${hDataStatus.count}" class="error" ></span>
                                                </td>
                                                <td align="center" id="hotel_actionItems_${hDataStatus.count}">
                                                    <img onClick="addHotel(this,${hDataStatus.count})" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                    <img onClick="return removeHotelTR(${hDataStatus.count})" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                    </c:forEach>
                                </c:when>
                                    <c:otherwise>
                                        <input type="hidden" id="hotelCount" name="hotelCount" value="1"/>
                                        <tr id="tr_hotel_1">
                                            <input type="hidden" value="0" name="hoteldeleted" id="hoteldeleted_1" />
                                            <input type="hidden" value="" name="hotel_id" id="hotel_id_1" />
                                            <td align="center">
                                                <c:choose>
                                                    <c:when test="${travel_type == 'I'}">
                                                        <div>
                                                            <select name="country" id="country_1" class="inputbox">
                                                                <option value="">--Country--</option>
                                                                <c:forEach items="${countryList}" var="countryArr" >
                                                                    <option value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <span id="country_error_1" class="error" ></span>
                                                        </div>&nbsp;
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="hidden" value="" name="country" id="country_1" />
                                                    </c:otherwise>
                                                </c:choose>
                                                <div>
                                                    <input type="hidden" name="city_id" id="city_id_1" value="" class="hiddenbox" />
                                                    <input type="text" name="city" id="city_1" class="inputbox autobox">
                                                    <span id="city_error_1" class="error" ></span>
                                                </div>
                                            </td>
                                            <td align="center">
                                                <input type="text" name="location" id="location_1" class="inputbox">
                                                <span id="location_error_1" class="error" ></span>
                                            </td>
                                            <td align="center">
                                                <input type="text" name="from_date" class="inputbox datePick"  id="from_date_1" readonly="true" value="">
                                                <span id="from_date_error_1" class="error" ></span>
                                            </td>
                                            <td align="center">
                                                <input type="text" name="to_date" class="inputbox datePick"  id="to_date_1" readonly="true" value="">
                                                <span id="to_date_error_1" class="error" ></span>
                                            </td>
                                            <td align="center">
                                                <textarea name="hotel_remarks" class="textareaTravel"  cols="28" rows="2" id="hotel_remarks_1"></textarea>
                                                <span id="hotel_remarks_error_1" class="error" ></span>
                                            </td>
                                            <td align="center" id="hotel_actionItems_1">
                                                <img onClick="addHotel(this,1)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                            </td>
                                        </tr>
                                    </c:otherwise>
                                </c:choose>
                            </table>
                        </div>
                        <div id="ConveyanceTab" class="tabContent" style="display: none;" >
                            <c:choose>
                                <c:when test="${data.band_id == 2 || data.band_id == 3 || data.band_id == 35 || data.band_id == 36}" >
                                    <table class="tableStructure" border="0" width="90%">
                                        <tr class="headerCenter">
                                            <th width="12%">City/Town<label style="color: red;" for="fine">*</label></th>
                                            <th width="16%">Pickup Address<label style="color: red;" for="fine">*</label></th>
                                            <th width="16%">Drop Address<label style="color: red;" for="fine">*</label></th>
                                            <th width="12%">Pick up Date<label style="color: red;" for="fine">*</label></th>
                                            <th width="12%">End Date<label style="color: red;" for="fine">*</label></th>
                                            <th width="12%">Time <label style="color: red;" for="fine">*</label> (24 hrs Format hh:mm)</th>
                                            <th width="16%">Remarks</th>
                                            <th width="4%">Action</th>
                                        </tr>
                                        <c:choose>
                                            <c:when test="${!empty(conveyanceData)}">
                                                <input type="hidden" value="${fn:length(conveyanceData)}" id="conveyanceCount" name="conveyanceCount" />
                                                <c:forEach items="${conveyanceData}" var="cData" varStatus="cDataStatus">
                                                    <tr id="tr_conveyance_${cDataStatus.count}">
                                                        <input type="hidden" value="0" name="conveyancedeleted" id="conveyancedeleted_${cDataStatus.count}" />
                                                        <input type="hidden" name="conveyance_id" id="conveyance_id_${cDataStatus.count}" value="${cData.out_conveyance_id}" />
                                                        <td align="center">
                                                            <c:choose>
                                                                <c:when test="${travel_type == 'I'}">
                                                                    <div>
                                                                        <select name="conveyance_country" id="conveyance_country_${cDataStatus.count}" class="inputbox">
                                                                            <option value="">--Country--</option>
                                                                            <c:forEach items="${countryList}" var="countryArr" >
                                                                                <option ${cData.out_conveyance_country == countryArr.countryId ?'selected':''} value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                                            </c:forEach>
                                                                        </select>
                                                                        <span id="conveyance_country_error_${cDataStatus.count}" class="error" ></span>
                                                                    </div>&nbsp;
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="hidden" value="" name="conveyance_country" id="conveyance_country_${cDataStatus.count}" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <div>
                                                                <input type="hidden" name="conveyance_city_id" id="conveyance_city_id_${cDataStatus.count}" value="${cData.conveyance_city_id}" class="hiddenbox" />
                                                                <input type="text" name="conveyance_city" id="conveyance_city_${cDataStatus.count}" class="inputbox autobox" value="${(cData.out_conveyance_city_others != '')?cData.out_conveyance_city_others:(cData.out_conveyance_city)}">
                                                                <span id="conveyance_city_error_${cDataStatus.count}" class="error" ></span>
                                                            </div>
                                                        </td>
                                                        <td align="center">
                                                            <textarea name="pickup" class="textareaTravel"  cols="15" rows="2" id="pickup_${cDataStatus.count}">${cData.out_pickup}</textarea>
                                                            <span id="pickup_error_${cDataStatus.count}" class="error" ></span>
                                                        </td>
                                                        <td align="center">
                                                            <textarea name="dropto" class="textareaTravel"  cols="15" rows="2" id="dropto_${cDataStatus.count}">${cData.out_dropto}</textarea>
                                                            <span id="dropto_error_${cDataStatus.count}" class="error" ></span>
                                                        </td>
                                                        <td align="center">
                                                            <input type="text" name="start_date" class="inputbox datePick"  id="start_date_${cDataStatus.count}" readonly="true" value="${cData.out_start_date !=''?cData.out_start_date:''}">
                                                            <span id="start_date_error_${cDataStatus.count}" class="error" ></span>
                                                        </td>
                                                        <td align="center">
                                                            <input type="text" name="end_date" class="inputbox datePick"  id="end_date_${cDataStatus.count}" readonly="true" value="${cData.out_end_date !=''?cData.out_end_date:''}">
                                                            <span id="end_date_error_${cDataStatus.count}" class="error" ></span>
                                                        </td>
                                                        <td align="center">
                                                            <input type="text" size="1" maxlength="2" class="inputbox_small" value="${fn:split(cData.out_travel_time,':')[0]}" name="time_hr" id="time_hr_${cDataStatus.count}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);">
                                                            &nbsp;:&nbsp;
                                                            <input type="text" size="1" maxlength="2" class="inputbox_small" value="${fn:split(cData.out_travel_time,':')[1]}" name="time_min" id="time_min_${cDataStatus.count}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);">
                                                            <span id="travel_time_error_${cDataStatus.count}" class="error" ></span>
                                                        </td>
                                                        <td align="center">
                                                            <textarea name="conveyance_remarks" class="textareaTravel"  cols="15" rows="2" id="conveyance_remarks_${cDataStatus.count}">${cData.out_conveyance_remarks}</textarea>
                                                            <span id="conveyance_remarks_error_${cDataStatus.count}" class="error" ></span>
                                                        </td>
                                                        <td align="center" id="conveyance_actionItems_${cDataStatus.count}">
                                                            <img onClick="addConveyance(this,${cDataStatus.count})" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                            <img onClick="return removeConveyanceTR(${cDataStatus.count})" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" value="1" id="conveyanceCount" name="conveyanceCount" />
                                                <tr id="tr_conveyance_1">
                                                    <input type="hidden" value="0" name="conveyancedeleted" id="conveyancedeleted_1" />
                                                    <input type="hidden" value="" name="conveyance_id" id="conveyance_id_1" />
                                                    <td align="center">
                                                        <c:choose>
                                                            <c:when test="${travel_type == 'I'}">
                                                                <div>
                                                                    <select name="conveyance_country" id="conveyance_country_1" class="inputbox">
                                                                        <option value="">--Country--</option>
                                                                        <c:forEach items="${countryList}" var="countryArr" >
                                                                            <option value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                    <span id="conveyance_country_error_1" class="error" ></span>
                                                                </div>&nbsp;
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="hidden" value="" name="conveyance_country" id="conveyance_country_1" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <div>
                                                            <input type="hidden" name="conveyance_city_id" id="conveyance_city_id_1" value="" class="hiddenbox" />
                                                            <input type="text" name="conveyance_city" id="conveyance_city_1" class="inputbox autobox">
                                                            <span id="conveyance_city_error_1" class="error" ></span>
                                                        </div>
                                                    </td>
                                                    <td align="center">
                                                        <textarea name="pickup" class="textareaTravel"  cols="15" rows="2" id="pickup_1" ></textarea>
                                                        <span id="pickup_error_1" class="error" ></span>
                                                    </td>
                                                    <td align="center">
                                                        <textarea name="dropto" class="textareaTravel"  cols="15" rows="2" id="dropto_1" ></textarea>
                                                        <span id="dropto_error_1" class="error" ></span>
                                                    </td>
                                                    <td align="center">
                                                        <input type="text" name="start_date" class="inputbox datePick"  id="start_date_1" readonly="true" value="">
                                                        <span id="start_date_error_1" class="error" ></span>
                                                    </td>
                                                    <td align="center">
                                                        <input type="text" name="end_date" class="inputbox datePick"  id="end_date_1" readonly="true" value="">
                                                        <span id="end_date_error_1" class="error" ></span>
                                                    </td>
                                                    <td align="center">
                                                        <input type="text" size="1" maxlength="2" class="inputbox_small" name="time_hr" id="time_hr_1" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);">
                                                        &nbsp;:&nbsp;
                                                        <input type="text" size="1" maxlength="2" class="inputbox_small" name="time_min" id="time_min_1" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);">
                                                        <span id="travel_time_error_1" class="error" ></span>
                                                    </td>
                                                    <td align="center">
                                                        <textarea name="conveyance_remarks" class="textareaTravel"  cols="15" rows="2" id="conveyance_remarks_1" onKeyUp="checkInputLength(this,'Remarks can contains upto 200 characters ');"></textarea>
                                                        <span id="conveyance_remarks_error_1" class="error" ></span>
                                                    </td>
                                                    <td align="center" id="conveyance_actionItems_1">
                                                        <img onClick="addConveyance(this,1)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                    </td>
                                                </tr>
                                            </c:otherwise>
                                        </c:choose>
                                    </table>
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" value="0" id="conveyanceCount" name="conveyanceCount" />
                                    <table class="tableStructure" border="0" width="90%">
                                        <tr>
                                            <td><div style="font-size:12px;font-weight: bold;">Pick up / Drop facility From / To Airport / Railway station are not provided by the Company.We kindly request you to book a Taxi/Auto and get it reimbursed with travel settlement.</div></td>
                                        </tr>
                                    </table>
                                    
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <c:if test="${travel_type == 'I'}">
                            <div id="VisaTab" class="tabContent" style="display: none;" >
                                <table class="tableStructure" border="0" width="90%">
                                    <c:if test="${system!='I'}">
                                        <tr>
                                            <th colspan="8" style="background: none;" ><div style="float: left">Note: This is required by the Admin team/Travel desk to validate the visa for your travel</div></th> 
                                        </tr> 
                                    </c:if>
                                   
                                    <tr class="headerCenter">
                                        <th width="14%">Reference Number<label style="color: red;" for="fine">*</label></th>
                                        <th width="16%">Visa Type<label style="color: red;" for="fine">*</label></th>
                                        <th width="10%">Valid From<label style="color: red;" for="fine">*</label></th>
                                        <th width="10%">Valid To<label style="color: red;" for="fine">*</label></th>
                                        <th width="14%">Issuing Country<label style="color: red;" for="fine">*</label></th>
                                        <th width="14%">Place of Issue<label style="color: red;" for="fine">*</label></th>
                                        <th width="10%">Entries / Visits <label style="color: red;" for="fine">*</label></th>
                                        <th width="10%">Remarks<label style="color: red;" for="fine">*</label></th>
                                    </tr>
                                    <tr>
                                        <input type="hidden" value="${visaData.visa_id}" name="visa_id" id="visa_id" />
                                        <td align="center">
                                            <input type="text" name="reference_number" id="reference_number" class="inputbox" value="${visaData.reference_number}">
                                            <span id = "reference_number_error" class = "error"></span>
                                        </td>
                                        <td>
                                            <select name="visa_type" id="visa_type" class="inputbox">
                                                <option value="">--Visa Type--</option>
                                                <c:forEach items="${visaList}" var="visaArr" >
                                                    <option ${visaData.visa_type == visaArr.key ?'selected':''} value="${visaArr.key}">${visaArr.value}</option>
                                                </c:forEach>
                                            </select>
                                            <span id = "visa_type_error" class = "error"></span>
                                        </td>
                                        <td align="center">
                                            <input type="text" name="valid_from" class="inputbox datePick"  id="valid_from" readonly="true" value="${visaData.out_valid_from}">
                                            <span id = "valid_from_error" class = "error"></span>
                                        </td>
                                        <td align="center">
                                            <input type="text" name="valid_to" class="inputbox datePick"  id="valid_to" readonly="true" value="${visaData.out_valid_to}">
                                            <span id = "valid_to_error" class = "error"></span>
                                        </td>
                                        <td>
                                            <select name="country_issue" id="country_issue" class="inputbox">
                                                <option value="">--Country--</option>
                                                <c:forEach items="${countryList}" var="countryArr" >
                                                    <option ${visaData.country_issue == countryArr.countryId ?'selected':''} value="${countryArr.countryId}">${countryArr.countryName}</option>
                                                </c:forEach>
                                            </select>
                                            <span id = "country_issue_error" class = "error"></span>
                                        </td>
                                        <td align="center">
                                            <input type="text" name="place_of_issue" class="inputbox"  id="place_of_issue" value="${visaData.place_of_issue}">
                                            <span id = "place_of_issue_error" class = "error"></span>
                                        </td>
                                        <td>
                                            <select name="visa_visit" id="visa_visit" class="inputbox">
                                                <option value="">--Entries--</option>
                                                <option ${visaData.visa_visit == 0 ?'selected':''} value="0">Single</option>
                                                <option ${visaData.visa_visit == 1 ?'selected':''} value="1">Multiple</option>
                                            </select>
                                            <span id = "visa_visit_error" class = "error"></span>
                                        </td>
                                        <td align="center">
                                            <textarea name="visa_remarks" class="textareaTravel"  cols="15" rows="2" id="visa_remarks" >${visaData.visa_remarks}</textarea>
                                            <span id = "visa_remarks_error" class = "error"></span>
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:if>
                        <br><br>
                    </div>
                    <c:if test="${travelData.tpReferenceId != null && travelData.tpReferenceId != '' && !(travelData.previous_status == 'c' || travelData.previous_status == 'a' || travelData.previous_status == 't' || (travelData.previous_status == 'f' && travelData.previous_status != 'c')) && travelData.currentStatus != 'x'}">
                        <div align="center" id="cancelRemarks" style="text-align: center;float:left;width:100%">
                            <table align="left" style="padding-left:20px;">
                                <tr>
                                    <th valign="top">Remarks on Cancellation&nbsp;</th>
                                    <td><textarea name="cancel_remarks" class="textarea_new"  style="width:500px" rows="3" id="cancel_remarks" ></textarea></td>
                                </tr>
                                <tr><td>&nbsp;<td><div class="error" id="cancel_remarks_error"></div></td></tr>
                                <tr><td>&nbsp;</td></tr>
                            </table>                            
                        </div>
                    </c:if>
                    
                    <div class="buttonAlignment" id="submitDiv">
                        <div class="submit buttonAlignment" align="center" id="btnGroup">
                            <c:choose>
                                <c:when test="${travelData.tpReferenceId != null && travelData.tpReferenceId != ''}">
                                    <c:if test="${!(travelData.previous_status == 'c' || travelData.previous_status == 'a' || travelData.previous_status == 't' || (travelData.previous_status == 'f' && travelData.previous_status != 'c') ) && travelData.currentStatus != 'x' }">
                                        <input name="travelbtn" id="submit_btn" type="submit" value="Re Submit" class="buttons submitbutton"  style="cursor:pointer;width:120px;" >
                                    </c:if>
                                </c:when>
                                <c:otherwise>
                                    <input name="travelbtn" id="save_btn" type="submit" value="Save" class="buttons savebutton"  style="cursor:pointer" >
                                    <input name="travelbtn" id="submit_btn" type="submit" value="Submit" class="buttons submitbutton"  style="cursor:pointer" >
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${travelData.tpReferenceId != null && travelData.tpReferenceId != '' && !(travelData.previous_status == 'c' || travelData.previous_status == 'a' || travelData.previous_status == 't' || (travelData.previous_status == 'f' && travelData.previous_status != 'c')) && travelData.currentStatus != 'x'}">
                                <input name="cancelRequest" id="cancel_btn" type="submit" value="Cancel Request" class="buttons cancelbutton" style="cursor:pointer;width:150px;" >
                            </c:if>
                        </div>
                    </div>
                </div>
                <%@include file="/WEB-INF/jsp/approvers_list.jsp" %>
            </div>
        </form>
    </body>
</html>
