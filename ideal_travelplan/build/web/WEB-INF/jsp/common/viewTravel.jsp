<%-- 
    Document   : viewTravel
    Created on : Oct 18, 2012, 7:36:37 PM
    Author     : 15065
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file="/WEB-INF/header.jsp" %>
        <title>Travel Request</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js" ></script>
    </head>
    <body>
        <div class="container_inner">
            <div class="page_heading">Travel Approval
                <div class="goToList">
                    <img alt="List Request" title="List Request" src="${pageContext.request.contextPath}/css/images/icon_list.png" />
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="${pageContext.request.contextPath}/getApprovalDashBoardList.htm?approveType=${approveType}&page=1">List ${approveType == 'all pending'? 'all active' : approveType} Requests</a>
                </div>       
                   
            </div>
            <div class="breadcrumb"></div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" /></div>
                <div style="padding-left:10px;padding-top: 1px;">
                    <ul class="notice_page_ul">
                        <li style="margin: -13px 0 5px 10px;" >Deviations will be marked in Red</li>
                        <li style="margin: 0 0 5px 10px;" >Fields marked in * are mandatory</li>
                    </ul>
                </div>
            </div>
        </div>
        <form action="approvalAction.htm" method="post" name="travelRequest" id="travelRequest" onSubmit="return approverSubmit()">
            <input type="hidden" name="action" id="action" value="">
            <input type="hidden" name="travelType" id="travelType" value="${travel_type}" >
            <input type="hidden" name="tpId" id="tpId" value="${travelData.master_id}" />
            <input type="hidden" name="tpRequestorId" id="tpRequestorId" value="${travelData.employee_id}" />
            <input type="hidden" name="system" id="system" value="${system}" />
            <div class="formContent_new" style="margin-bottom: 5px;">
                <table class="tableStructure" border="0" width="95%">
                    <tr>
                        <td width="33%">
                            <div style="width:50%;float:left"><b>Request No</b></div>
                            <div style="width:50%;float:left">${travelData.tpReferenceId}</div>
                        </td>
                        <td width="33%">
                            <div style="width:45%;float:left"><b>Travel Request Date</b></div>
                            <div style="width:55%;float:left">${travelData.out_travel_requested_date}</div>
                        </td>
                        <td width="33%">
                            <div style="width:35%;float:left"><b>Employee Id</b></div>
                            <div style="width:65%;float:left">${data.employee_number}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="width:50%;float:left"><b>Employee Name</b></div>
                            <div style="width:50%;float:left">${data.employee_name}</div>
                        </td>
                        <td>
                            <div style="width:45%;float:left"><b>Group</b></div>
                            <div style="width:55%;float:left">${data.group_name}</div>
                        </td>
                        <td>
                            <div style="width:35%;float:left"><b>Sub Group</b></div>
                            <div style="width:65%;float:left">${data.sub_group_name}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="width:50%;float:left"><b>Designation</b></div>
                            <div style="width:50%;float:left">${data.designation}</div>
                        </td>
                        <td>
                            <div style="width:45%;float:left"><b>Level</b></div>
                            <div style="width:55%;float:left">${data.band_name}</div>
                        </td>
                        <td>
                            <div style="width:35%;float:left"><b>Base Location</b></div>
                            <div style="width:65%;float:left">${data.city_name}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="width:50%;float:left"><b>Travel Start Date</b></div>
                            <div style="width:50%;float:left">${travelData.out_travel_start_date}</div>
                        </td>
                        <td>
                            <div style="width:45%;float:left"><b>Travel End Date</b></div>
                            <div style="width:55%;float:left">${travelData.out_travel_end_date}</div>
                        </td>
                        <td>
                            <div style="width:35%;float:left"><b>Travel Term</b></div>
                            <div style="width:65%;float:left">${travelData.travel_term}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="width:50%;float:left"><b>Project Travel</b></div>
                            <div style="width:50%;float:left">${travelData.travel_billable == 'Y'?'Yes':'No'}</div>
                        </td>
                        <td>
                            <div style="width:45%;float:left"><b>Customer</b></div>
                            <div style="width:55%;float:left">${travelData.customer_others}</div>
                        </td>
                        <td>
                            <div style="width:35%;float:left"><b>Project</b></div>
                            <div style="width:65%;float:left">${travelData.project_others}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="width:50%;float:left"><b>Reimbursable by Client</b></div>
                            <div style="width:50%;float:left">${travelData.client_reimbursable == 'Y'?'Yes':'No'}</div>
                        </td>
                        <td>
                            <div style="width:45%;float:left"><b>Advance Required</b></div>
                            <div style="width:55%;float:left">
                                <label style="color:red;font-weight:bold;">
                                    <c:choose>
                                        <c:when test="${travelData.advance_required == 0 || travelData.advance_required == ''}">--</c:when>
                                        <c:otherwise>${travelData.advance_required}</c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </td>
                        <td>
                            <div style="width:35%;float:left"><b>Currency</b></div>
                            <div style="width:65%;float:left">
                                <label style="color:red;font-weight:bold;">
                                    <c:choose>
                                        <c:when test="${travelData.advance_required == 0 || travelData.advance_required == ''}">--</c:when>
                                        <c:otherwise>${travelData.currency_type}</c:otherwise>
                                    </c:choose>
                                </label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="width:50%;float:left"><b>Purpose of Travel</b></div>
                            <div style="width:50%;float:left">${travelData.travel_purpose}</div>
                        </td>
                        <td>
                            <div style="width:45%;float:left"><b>Travel Remarks</b></div>
                            <div style="width:55%;float:left">${travelData.emp_remarks}</div>
                        </td>
                        <td>
                            <div style="width:35%;float:left"><b>Contact Number</b></div>
                            <div style="width:65%;float:left">${travelData.mobileNo}</div>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="width:50%;float:left"><b>Guest Booking</b></div>
                            <div style="width:50%;float:left">
                                <c:choose>
                                    <c:when test="${travelData.guest_booking == 'Y'}">
                                        Yes
                                    </c:when>
                                    <c:when test="${travelData.guest_booking == 'N'}">
                                        No
                                    </c:when>
                                </c:choose>
                            </div>
                        </td>
                        <td>
                            <c:if test="${travelData.guest_booking == 'Y'}">
                                <div style="width:45%;float:left"><b>Guest Name</b></div>
                                <div style="width:55%;float:left">${travelData.guest_name}</div>
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
            <c:if test="${!empty(ticketData)}">
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <span class="fieldsetHeading" style="color: #4C83B3;">Ticket</span>
                    <table class="tableStructure" border="0" width="95%">
                        <tr class="headerCenter">
                            <th width="18%">From</th>
                            <th width="18%">To</th>
                            <th width="12%">Date of Travel</th>
                            <th width="14%">Travel Preference</th>
                            <th width="14%">Mode of Travel</th>
                            <th width="20%">Remarks</th>
                        </tr>
                        <c:forEach items="${ticketData}" var="tData" varStatus="tDataStatus">
                            <tr>
                                <td align="center">
                                    <c:choose>
                                        <c:when test="${travel_type == 'I'}">
                                            <div><b>Country</b> : ${tData.out_from_country}</div>&nbsp;
                                            <div><b>City</b> : ${(tData.out_from_city_others != '')?tData.out_from_city_others:(tData.out_from_city)}</div>&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            ${(tData.out_from_city_others != '')?tData.out_from_city_others:(tData.out_from_city)}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center">
                                    <c:choose>
                                        <c:when test="${travel_type == 'I'}">
                                            <div><b>Country</b> : ${tData.out_to_country}</div>&nbsp;
                                            <div><b>City</b> : ${(tData.out_to_city_others != '')?tData.out_to_city_others:(tData.out_to_city)}</div>&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            ${(tData.out_to_city_others != '')?tData.out_to_city_others:(tData.out_to_city)}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center">
                                    ${tData.out_travel_date !=''?tData.out_travel_date:''}
                                </td>
                                <td align="center">${tData.out_travel_preference}</td>
                                <td align="center">${tData.out_travel_mode}</td>
                                <td align="center">${tData.out_ticket_remarks}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <c:if test="${!empty(hotelData)}">
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <span class="fieldsetHeading" style="color: #4C83B3;">Hotel</span>
                    <table class="tableStructure" border="0" width="95%">
                        <tr class="headerCenter">
                            <th width="20%">City/Town</th>
                            <th width="20%">Location Preference</th>
                            <th width="15%">From Date</th>
                            <th width="15%">To Date</th>
                            <th width="26%">Remarks</th>
                        </tr>
                        <c:forEach items="${hotelData}" var="hData" varStatus="hDataStatus">
                            <tr>
                                <td align="center">
                                    <c:choose>
                                        <c:when test="${travel_type == 'I'}">
                                            <div><b>Country</b> : ${hData.out_country}</div>&nbsp;
                                            <div><b>City</b> : ${(hData.out_city_others != '')?hData.out_city_others:(hData.out_city)}</div>&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            ${(hData.out_city_others != '')?hData.out_city_others:(hData.out_city)}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center">${hData.out_location}</td>
                                <td align="center">${hData.out_from_date !=''?hData.out_from_date:''}</td>
                                <td align="center">${hData.out_to_date !=''?hData.out_to_date:''}</td>
                                <td align="center">${hData.out_hotel_remarks}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <c:if test="${!empty(conveyanceData)}">
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <span class="fieldsetHeading" style="color: #4C83B3;">Conveyance</span>
                    <table class="tableStructure" border="0" width="95%">
                        <tr class="headerCenter">
                            <th width="12%">City/Town</th>
                            <th width="16%">Pickup Address</th>
                            <th width="16%">Drop Address</th>
                            <th width="12%">Pick up Date</th>
                            <th width="12%">End Date</th>
                            <th width="12%">Time (24 hrs Format)</th>
                            <th width="16%">Remarks</th>
                        </tr>
                        <c:forEach items="${conveyanceData}" var="cData" varStatus="cDataStatus">
                            <tr id="tr_conveyance_${cDataStatus.count}">
                                <td align="center">
                                    <c:choose>
                                        <c:when test="${travel_type == 'I'}">
                                            <div><b>Country</b> : ${cData.out_conveyance_country}</div>&nbsp;
                                            <div><b>City</b> : ${(cData.out_conveyance_city_others != '')?cData.out_conveyance_city_others:(cData.out_conveyance_city)}</div>&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            ${(cData.out_conveyance_city_others != '')?cData.out_conveyance_city_others:(cData.out_conveyance_city)}
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center">${cData.out_pickup}</td>
                                <td align="center">${cData.out_dropto}</td>
                                <td align="center">${cData.out_start_date !=''?cData.out_start_date:''}</td>
                                <td align="center">${cData.out_end_date !=''?cData.out_end_date:''}</td>
                                <td align="center">${fn:split(cData.out_travel_time,':')[0]} : ${fn:split(cData.out_travel_time,':')[1]}</td>
                                <td align="center">${cData.out_conveyance_remarks}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <c:if test="${travel_type == 'I'}">
                <c:if test="${!empty(visaData)}">
                    <div class="formContent_new" style="margin-bottom: 5px;">
                        <span class="fieldsetHeading" style="color: #4C83B3;">Visa Details</span>
                        <table class="tableStructure" border="0" width="95%">
                            <tr class="headerCenter">
                                <th width="14%">Reference Number</th>
                                <th width="16%">Visa Type</th>
                                <th width="10%">Valid From</th>
                                <th width="10%">Valid To</th>
                                <th width="14%">Issuing Country</th>
                                <th width="10%">Place of Issue</th>
                                <th width="10%">Entries / Visits</th>
                                <th width="14%">Remarks</th>
                            </tr>
                            <tr>
                                <td align="center">${visaData.reference_number}</td>
                                <td>${visaData.visa_type}</td>
                                <td align="center">${visaData.out_valid_from}</td>
                                <td align="center">${visaData.out_valid_to}</td>
                                <td align="center">${visaData.country_issue}</td>
                                <td align="center">${visaData.place_of_issue}</td>
                                <td>${visaData.visa_visit == 0 ?'Single':'Multiple'}</td>
                                <td align="center">${visaData.visa_remarks}</td>
                            </tr>
                        </table>
                    </div>
                </c:if>
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <span class="fieldsetHeading" style="color: #4C83B3;">Passport Details</span>
                    <table class="tableStructure" border="0" width="95%">
                        <tr>
                            <td width="33%">
                                <div style="width:35%;float:left"><b>Sur Name</b></div>
                                <div style="width:65%;float:left">${genericDetails.surName}</div>
                            </td>
                            <td width="33%">
                                <div style="width:45%;float:left"><b>Given Name</b></div>
                                <div style="width:55%;float:left">${genericDetails.givenName}</div>
                            </td>
                            <td width="33%">
                                <div style="width:40%;float:left"><b>Gender</b></div>
                                <div style="width:60%;float:left">${genericDetails.gender == 'm'?'Male':'Female'}</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div style="width:35%;float:left"><b>Nationality</b></div>
                                <div style="width:65%;float:left">${genericDetails.nationality}</div>
                            </td>
                            <td>
                                <div style="width:45%;float:left"><b>Passport Number</b></div>
                                <div style="width:55%;float:left">${genericDetails.passportNumber}</div>
                            </td>
                            <td>
                                <div style="width:40%;float:left"><b>Place of Issue</b></div>
                                <div style="width:60%;float:left">${genericDetails.issuePlace}</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div style="width:35%;float:left"><b>Issued Date</b></div>
                                <div style="width:65%;float:left"><fmt:formatDate value="${genericDetails.issuedDate}" var="issuedDate" pattern="dd-MM-yyyy"/>${issuedDate}</div>
                            </td>
                            <td>
                                <div style="width:45%;float:left"><b>Expiry Date</b></div>
                                <div style="width:55%;float:left"><fmt:formatDate value="${genericDetails.expiryDate}" var="expiryDate" pattern="dd-MM-yyyy"/>${expiryDate}</div>
                            </td>
                            <td>
                                <div style="width:40%;float:left"><b>Place of Birth</b></div>
                                <div style="width:60%;float:left">${genericDetails.birthPlace}</div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div style="width:35%;float:left"><b>Date of Birth</b></div>
                                <div style="width:65%;float:left">${genericDetails.birth_date}</div>
                            </td>
                            <td>
                                <div style="width:45%;float:left"><b>ECNR Status</b></div>
                                <div style="width:55%;float:left">${genericDetails.ecnrStatus == 0?'Required':'Not Required'}</div>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <span class="fieldsetHeading" style="color: #4C83B3;">Insurance Details</span>
                    <table class="tableStructure" border="0" width="95%">
                        <tr class="headerCenter">
                            <th width="15%">Nominee</th>
                            <th width="15%">Relationship</th>
                            <th width="10%">Door No</th>
                            <th width="15%">Street</th>
                            <th width="15%">Area</th>
                            <th width="25%">Place</th>
                        </tr>
                        <tr>
                            <td align="center">${genericDetails.nominee}</td>
                            <td align="center">${genericDetails.relationShip}</td>
                            <td align="center">${genericDetails.doorNo}</td>
                            <td align="center">${genericDetails.streetName}</td>
                            <td align="center">${genericDetails.area}</td>
                            <td align="center">${genericDetails.place}</td>
                        </tr>
                    </table>
                </div>
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <span class="fieldsetHeading" style="color: #4C83B3;">Personal Details</span>
                    <table class="tableStructure" border="0" width="95%">
                        <tr class="headerCenter">
                            <th width="20%">Meal Preference</th>
                            <th width="30%">Official Email Id</th>
                            <th width="30%">Alternate Email Id</th>
                            <th width="20%">Mobile Number</th>
                        </tr>
                        <tr>
                            <td align="center">
                                <c:choose>
                                    <c:when test="${genericDetails.mealPreference == 0}">Hindu Meal</c:when>
                                    <c:when test="${genericDetails.mealPreference == 1}">Asian-Veg Meal</c:when>
                                    <c:when test="${genericDetails.mealPreference == 2}">Muslim Meal</c:when>
                                    <c:when test="${genericDetails.mealPreference == 3}">Jain Meal</c:when>
                                    <c:when test="${genericDetails.mealPreference == 4}">Vegetarian Meal</c:when>
                                </c:choose>
                            </td>
                            <td align="center">${genericDetails.officialMailId}</td>
                            <td align="center">${genericDetails.alternateMailId}</td>
                            <td align="center">${genericDetails.mobileNo}</td>
                        </tr>
                    </table>
                </div>
            </c:if>
            <c:if test="${!empty(attachmentData)}">
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <span class="fieldsetHeading" style="color: #4C83B3;">Attachment</span>
                    <table class="tableStructure" border="0" width="50%">
                        <c:forEach items="${attachmentData}" var="aData" varStatus="aDataStatus">
                            <c:if test="${aData.fileName != '' && aData.fileName != null}">
                                <tr id="attachTR_${aDataStatus.count}">
                                    <td align="center">
                                        <input type="hidden" value="${aData.attachment_id}" name="attachment_id_${aDataStatus.count}" id="attachment_id_${aDataStatus.count}" />
                                        <input type="hidden" value="0" name="attach_del_${aDataStatus.count}" id="attach_del_${aDataStatus.count}" />
                                        <a href="fileDownload.htm?fileName=${aData.fileName}"><c:out value="${aData.fileName}"></c:out></a>
                                    </td>
                                </tr>
                            </c:if>
                        </c:forEach>
                    </table>
                </div>
            </c:if>
            <%@include file="/WEB-INF/jsp/approvers_list.jsp" %>
            <c:if test="${approveType == 'pending'}">
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <input type="hidden" name="approverModule" id="approverModule" value="${moduleId}" />
                    <table border="0" width="95%">
                        <tr>
                            <c:if test="${moduleId==606}">
                                <th width="30%" align="right">Amount Deposited</th>
                                <td width="70%" align="left">
                                    <table>
                                        <tr>
                                            <td>
                                                <input type="text" class="textbox_new" name="amount_deposited" id="amount_deposited" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" maxlength="6" />
                                                <br/><span id="amount_deposited_error" class="error"></span>
                                            </td>
                                            <th>Currency</th>
                                            <td>
                                                <select name="currency_type" id="currency_type" class="selectbox_new">
                                                    <option value="">--Select Currency--</option>
                                                    <c:forEach items="${currencyList}" var="curList" >
                                                        <option ${travelData.currency_type == curList.key ?'selected':''} value="${curList.key}">${curList.value}</option>
                                                    </c:forEach>
                                                </select>
                                                <br/><span id="currency_type_error" class="error"></span>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </c:if>
                        </tr>
                        <tr class="headerCenter">
                            <th width="30%" align="right">Remarks&nbsp;&nbsp;&nbsp;</th>
                            <td width="50%" align="left">
                                <textarea name="remarks" class="textarea_new"  cols="20" rows="3" id="remarks" style="width:400px;"></textarea>
                                <br/><span id="remarks_error" class="error"></span>
                            </td>
                        </tr>
                        <tr><td>&nbsp;</td></tr>
                        <tr id="submitDiv">
                            <td align="right"></td>
                            <td align="left">
                                <c:choose>
                                    <c:when test="${moduleId==485}">
                                        <input name="approveAction" type="submit" id="approveAction" class="buttons submitbutton"  value="Ticket Booked" style="cursor:pointer" />
                                    </c:when>
                                    <c:when test="${moduleId==606}">
                                        <input name="approveAction" type="submit" id="approveAction" class="buttons submitbutton"  value="Money Deposited" style="cursor:pointer" />
                                       <!-- <input name="printAction" type="submit" id="printAction" class="buttons submitbutton"  value="Print" style="cursor:pointer" />-->
                                    </c:when>
                                    <c:otherwise>
                                        <input name="approveAction" id="approveAction" type="submit" value="Approve" class="buttons submitbutton"  style="cursor:pointer" >
                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${moduleId == '483'}">
                                    <input name="cfoAction" id="cfoRequest" type="submit" value="Request for CFO" class="buttons submitbutton"  style="cursor:pointer" >
                                </c:if>
                                <c:if test="${moduleId != 485 && moduleId != 606}">
                                    <input name="sendBackAction" id="rejectAction" type="submit" value="Send back" class="buttons submitbutton"  style="cursor:pointer" >
                                </c:if>
                            </td>
                        </tr>
                    </table>
                </div>
            </c:if> 
       
            <c:if test="${approveType == 'processed' && moduleId == '606'}" >    
                <div class="formContent_new" style="margin-bottom: 5px;">
                <table border="0" width="95%">
                <tr id="submitDiv">                 
                    <td align="center">                        
                    <input name="printAction" type="submit" id="printAction" class="buttons submitbutton"  value="Print" style="cursor:pointer" />     
                    </td>                    
                </tr>
              </table>
                </div>
            </c:if>
                
           </form>
    </body>
</html>
