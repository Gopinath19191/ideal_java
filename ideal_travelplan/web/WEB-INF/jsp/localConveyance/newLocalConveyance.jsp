<%--
    Document   : newLocalConveyance
    Created on : 8 Oct, 2012, 11:55:12 AM
    Author     : 14578
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib  prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <%@include file="/WEB-INF/header.jsp" %>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css" media="all" />
        <title>Local Conveyance</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.slidingmessage.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.slidingmessage.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/localConveyance.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simpletip-1.3.1.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
            .error {
                color:red;
            }
        </style>
        <title>New Local Conveyance</title>
        <script>
            $(document).ready(function() {
                $(".link1").click(function(event){
                    event.preventDefault();
                    var url =$(this).attr("href");

                    $('#right-pane').load(url, function(data) {
                        console.log(data);
                    });
                    console.log(url);
                });
                $(".helpTooltip").simpletip({
                    content: 'This is not mandatory field. However Admin team / Treasury desk might need this to reach out you for clarifications/changes/emergency',
                    fixed: true
                });
            });
            
            function setApproverId(val){
                var option = $('option:selected', val).attr('rm_id');
                $("#rm_id").val(option);
                $("#rm_id").attr("readonly", true);
            }
        </script>
    </head>
    <body>

        <div class="container_inner">
            <div class="page_heading"> Local Conveyance Request
                <div class="goToList">
                    <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="List Request" alt="List Request">
                    <a href="${pageContext.request.contextPath}/getDashBoardList.htm?page=1" target="_self" style="text-decoration:none;color: #4C83B3;">List Requests</a>
                    <!--<img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="List Request" alt="List Request">-->
                    <!--<a href="http://ideal.hindujatech.com/uploads/document_uploads/policy_document/Local Coveyance Policy_20140730151154_520618.pdf" target="_blank" style="text-decoration:none;color: #4C83B3;">View Local Conveyance Policy</a>-->
                </div>
            </div>
            <div class="breadcrumb"></div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" /></div>
                <div style="padding-left:10px;padding-top: 1px;">
                    <ul class="notice_page_ul">
                        <li style="margin: -13px 0 5px 10px;" >Travel Request should be raised atleast 3 days prior to the travel</li>
                        <li style="margin: 0 0 5px 10px;" >Fields marked in * are mandatory.</li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="errormessage" style="margin-left: 20px;"></div>
        <form id="formContent" name="formContent" action="requestorSubmit.htm?btnId=saveBtn" method="POST" onsubmit="return formValidate();" enctype="multipart/form-data" >
            <input type="hidden" id="action" name="action" value="" >
            <input type="hidden" id="newLCNumber" name="newLCNumber" value="${uniqueId}" >
            <input type="hidden" id="tpReferenceId" name="tpReferenceId" value="${lcViewDetails.tpRefId}" >
            <input type="hidden" name="currentStatus" id="currentStatus" value="${lcViewDetails.status}">
            <input type="hidden" name="workFlowId" id="workFlowId" value="${lcViewDetails.workflow_id}">
            <input type="hidden" name="system" id="system" value="${system}">
            <div class="formContent_new" style="margin-bottom: 5px;">
                <table border="0" align="center" class="travelDetails_new" cellpadding="3" cellpadding="3">
                    <tr>
                        <td style="font-weight: bolder">Travel Request Date<label class="required">*</label></td><td><fmt:formatDate value="${lcViewDetails.reqDate}" var="reqDate" pattern="dd-MM-yyyy"/><input type="text" id="reqDate" name="reqDate" class="textbox_new" value="${reqDate}"/></td>
                        <td style="font-weight: bolder">Employee Id</td><td><input type="text" id="employeeNumber" name="employeeNumber" disabled="true" class="textbox_new" value="${empDetails.employeeNumber}"/></td>
                        <td style="font-weight: bolder">Employee Name</td><td><input type="text" id="empName" name="empName" disabled class="textbox_new" value="${empDetails.employeeName}"/></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">Designation</td><td><input type="text" id="designation" disabled name="designation" class="textbox_new" value="${empDetails.designationName}" /></td>
                        <td style="font-weight: bolder">Practice Group</td><td><input type="text" id="businessGroup" disabled name="businessGroup" class="textbox_new" value="${empDetails.practicegroupName}" /></td>
                        <td style="font-weight: bolder">Sub Practice Group</td><td><input type="text" id="practiceGroup" disabled name="practiceGroup" class="textbox_new" value="${empDetails.businessgroupName}" /></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">Level</td><td><input type="text" id="level" name="level" class="textbox_new" disabled value="${empDetails.bandName}"/> </td>
                        <td style="font-weight: bolder">Base Location</td><td><input type="text" id="baseLocation" class="textbox_new" disabled name="baseLocation" value="${empDetails.cityName}"/> </td>
                        <td style="font-weight: bolder">Guest Booking <label class="required">*</label></td>
                        <td><select id="guestBooking" name="guestBooking" class="selectbox_new" >
                                <option value="">--Select Guest Booking--</option>
                                <option ${lcViewDetails.guestBooking == 'N'?'selected':''} value="N">No</option>
                                <option ${lcViewDetails.guestBooking == 'Y'?'selected':''} value="Y">Yes</option>
                            </select>
                        <td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.guestBooking=='Y'}">
                            <c:set var="guestDispStyle" value="table-row"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="guestDispStyle" value="none"/>
                        </c:otherwise>
                    </c:choose>

                    <tr id="travelGuestName" style="display: ${guestDispStyle};" >
                        <td colspan="4"></td>
                        <td style="font-weight: bolder">Guest Name <label class="required">*</label></td>
                        <td><input type="text" id="guestName" name="guestName" value="${lcViewDetails.guestName}" class="textbox_new"/></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">Project Travel<label class="required">*</label></td>
                        <td>
                            <select id="projectTravel" name="projectTravel"  class="selectbox_new">
                                <option value="" ${lcViewDetails.projectTravel == '' ? 'selected' : ''}>--Select Billable--</option>
                                <option value="y" ${lcViewDetails.projectTravel == 'Y' ? 'selected' : ''}>Yes</option>
                                <option value="n" ${lcViewDetails.projectTravel == 'N' ? 'selected' : ''}>No</option>
                            </select>
                        </td>
                        <td style="font-weight: bolder">Project <label class="required">*</label></td>
                        <td>
                            <input type="hidden" value="${empDetails.rm_id}" name="rm_id" id="rm_id"/>
                            <select id="project" name="project" class="selectbox_new" onchange="setApproverId(this);" >
                                <option value="" rm_id="${empDetails.rm_id}">--Select Project--</option>
                                <c:forEach items="${projectList}" var="prjList" varStatus="i">
                                    <option value="${prjList.projectId}" ${prjList.projectId == lcViewDetails.projectId ? 'selected' : ''} rm_id="${prjList.rm_id}">${prjList.projectCode}--${prjList.projectName}</option>
                                </c:forEach>
                                <option value="-1" ${lcViewDetails.projectId == -1 ? 'selected' : ''} rm_id="${empDetails.rm_id}">Others</option>
                            </select>
                        </td>
                        <td style="font-weight: bolder">Purpose of Travel <label class="required">*</label></td><td><textarea rows="2" class="textarea_new" id="travelPurpose" name="travelPurpose">${lcViewDetails.travelPurpose}</textarea> </td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.projectId=='-1'}">
                            <c:set var="prjDispStyle" value="table-row"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="prjDispStyle" value="none"/>
                        </c:otherwise>
                    </c:choose>
                    <tr id="otherProjects" style="display: ${prjDispStyle};">
                        <td colspan="2"></td>
                        <td style="font-weight: bolder">Project (Others) <label class="required">*</label></td>
                        <td><input type="text" class="textbox_new" id="projectOthers" name="projectOthers" value="${lcViewDetails.projectOthers}"/></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">City <label class="required">*</label></td>
                        <td>
                            <select id="city" name="city" class="selectbox_new" onChange="changeTravelPoints(this.value)">
                                <option value="">--Select City---</option>
                                <c:forEach items="${cityList}" var="cityList" varStatus="i">
                                    <option value="${cityList.cityId}" ${cityList.cityId == lcViewDetails.city ? 'selected' : ''}>${cityList.cityName}</option>
                                </c:forEach>
                                <c:if test="${empDetails.bandId==1 || empDetails.bandId==2 || empDetails.bandId==3 || empDetails.bandId==35 || empDetails.bandId==36}">
                                    <option value="-1">Others</option>
                                </c:if>
                            </select>
                        </td>
                        <td style="font-weight: bolder">Remarks<br/><sub>(CAB Type or Any Other Specifications)</sub> </td>
                        <td><textarea rows="2" class="textarea_new" id="remarks_lc" name="remarks_lc">${lcViewDetails.remarks_lc}</textarea> </td>
                        <td style="font-weight: bolder">Is Reimbursable<label class="required">*</label></td>
                        <td>
                            <select id="client_reimbursable" name="client_reimbursable"  class="selectbox_new">
                                <option value="" ${lcViewDetails.client_reimbursable == '' ? 'selected' : ''}>--Select Billable--</option>
                                <option value="y" ${lcViewDetails.client_reimbursable == 'Y' ? 'selected' : ''}>Yes</option>
                                <option value="n" ${lcViewDetails.client_reimbursable == 'N' ? 'selected' : ''}>No</option>
                            </select>
                        </td>
                    </tr>
                    <tr id="cityOthers" style="display: none;">
                        <td style="font-weight: bolder">City (Others) <label class="required">*</label></td>
                        <td><input type="text" class="textbox_new" id="cityOthers" name="cityOthers" value="${lcViewDetails.cityOthers}" /></td>
                    </tr>
                </table>
            </div>
            <div class="formContent_new" style="margin-bottom: 5px;">
                <div class="fieldsetHeading" style="color: #4C83B3;">Onward Journey</div>
                <table border="0" align="center" class="travelDetails_new">
                    <tr>
                        <td style="font-weight: bolder">Pick Up Point <label class="required">*</label></td>
                        <td>
                            <select id="onwardPickUpPoint" name="onwardPickUpPoint" class="selectbox_new" onchange="selectTravelDetails(this.value,'pickUpDetails','pickUpLandmark','pickUpPlace')">
                                <option value="">--Select pick-up point--</option>
                                <c:forEach items="${travelPointsList}" var="travelPointsList" varStatus="i">
                                    <option value="${travelPointsList.addressId}" ${travelPointsList.addressId==lcViewDetails.onwardPickUpPoint ? 'selected' : ''}>${travelPointsList.travelPoints}</option>
                                </c:forEach>
                                <option value="-3" ${lcViewDetails.onwardPickUpPoint=='-3' ? 'selected' : ''}>Customer Office</option>
                                <option value="-2" ${lcViewDetails.onwardPickUpPoint=='-2' ? 'selected' : ''}>Hotel</option>
                                <option value="-1" ${lcViewDetails.onwardPickUpPoint=='-1' ? 'selected' : ''}>Others</option>
                            </select>
                        </td>
                        <td style="font-weight: bolder">Pick Up Date <label class="required">*</label></td>
                        <td><fmt:formatDate value="${lcViewDetails.onwardPickUpDate}" var="pickUpDate" pattern="dd-MM-yyyy"/><input type="text" id="onwardPickUpDate" class="textbox_new" name="onwardPickUpDate" value="${pickUpDate}" /> </td>
                        <td style="font-weight: bolder">Pick Up Time(24 Hrs Format hh:mm) <label class="required">*</label></td>
                        <c:if test="${lcViewDetails != null}">

                            <c:set var="pickUpTime" value="${fn:split(lcViewDetails.onwardPickUpTime, ':')}"/>
                        </c:if>
                        <td>
                            <input type="text" size="1" maxlength="2" class="inputbox_small" name="onwardPickupHrs" id="onwardPickupHrs" value="${pickUpTime[0]}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" >
                            &nbsp;:&nbsp;
                            <input type="text" size="1" maxlength="2" class="inputbox_small" name="onwardPickupMins" id="onwardPickupMins" value="${pickUpTime[1]}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);">
                        </td>
                    </tr>

                    <tr id="pickUpDetails" style="display: none">
                        <td id="pickUpPlace" style="font-weight: bolder"> <label class="required">*</label></td><td><input type="text" id="onwardPickupDoorNo" class="textbox_new" name="onwardPickupDoorNo" value="${lcViewDetails.onwardPickupDoorNo}"/></td>
                        <td style="font-weight: bolder">Street Name <label class="required">*</label></td><td><input type="text" id="onwardPickupStreetName" class="textbox_new" name="onwardPickupStreetName" value="${lcViewDetails.onwardPickupStreetName}"/></td>
                        <td style="font-weight: bolder">Area <label class="required">*</label></td><td><input type="text" id="onwardPickupArea" class="textbox_new" name="onwardPickupArea" value="${lcViewDetails.onwardPickupArea}"/></td>
                    </tr>
                    <tr id="pickUpLandmark" style="display: none">
                        <td style="font-weight: bolder">Land Mark <label class="required">*</label></td><td><input type="text" id="onwardPickupLandMark" class="textbox_new" name="onwardPickupLandMark" value="${lcViewDetails.onwardPickupLandMark}"/> </td>
                    </tr>

                    <tr>
                        <td style="font-weight: bolder">Onward Destination <label class="required">*</label></td>
                        <td>
                            <select id="onwardDropJourney" name="onwardDropJourney" class="selectbox_new" onchange="selectTravelDetails(this.value,'dropDetails','dropLandmark','dropPlace')">
                                <option value="">--Select drop point--</option>

                                <c:forEach items="${travelPointsList}" var="travelPointsList" varStatus="i">
                                    <option value="${travelPointsList.addressId}" ${travelPointsList.addressId==lcViewDetails.onwardDropJourney ? 'selected' : ''}>${travelPointsList.travelPoints}</option>
                                </c:forEach>
                                <option value="-3" ${lcViewDetails.onwardDropJourney=='-3' ? 'selected' : ''}>Customer Office</option>
                                <option value="-2" ${lcViewDetails.onwardDropJourney=='-2' ? 'selected' : ''}>Hotel</option>
                                <option value="-1" ${lcViewDetails.onwardDropJourney=='-1' ? 'selected' : ''}>Others</option>
                            </select>
                        </td>
                    </tr>
                    <tr id="dropDetails" style="display: none;">
                        <td id="dropPlace" style="font-weight: bolder"> <label class="required">*</label></td><td><input type="text" id="onwardDropDoorNo" class="textbox_new" name="onwardDropDoorNo" value="${lcViewDetails.onwardDropDoorNo}"/></td>
                        <td style="font-weight: bolder">Street Name <label class="required">*</label></td><td><input type="text" id="onwardDropStreetName" class="textbox_new" name="onwardDropStreetName" value="${lcViewDetails.onwardDropStreetName}"/></td>
                        <td style="font-weight: bolder">Area <label class="required">*</label></td><td><input type="text" id="onwardDropArea" name="onwardDropArea" class="textbox_new" value="${lcViewDetails.onwardDropArea}"/></td>
                    </tr>
                    <tr id="dropLandmark" style="display: none;">
                        <td style="font-weight: bolder">Land Mark <label class="required">*</label></td><td><input type="text" id="onwardDropLandMark" class="textbox_new" name="onwardDropLandMark" value="${lcViewDetails.onwardDropLandMark}"/> </td>
                    </tr>

                </table>
            </div>
            <div class="formContent_new" style="margin-bottom: 5px;">
                <table border="0" align="center" class="travelDetails_new">
                    <div class="fieldsetHeading" style="color: #4C83B3;"> Return Journey</div>

                    <c:choose>
                        <c:when test="${lcViewDetails != null}">
                            <c:choose>
                                <c:when test="${lcViewDetails.returnCab == 0}">
                                    <c:set value="checked"  var="disposal"/>
                                    <c:set value="none"  var="returnPickUpDisp"/>
                                </c:when>
                                <c:otherwise>
                                    <c:set value="checked"  var="seperate"/>
                                    <c:set value="table-row"  var="returnPickUpDisp"/>
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                        <c:otherwise>
                            <c:set value="checked"  var="disposal"/>
                            <c:set value="none"  var="returnPickUpDisp"/>
                        </c:otherwise>
                    </c:choose>

                    <tr>
                        <td style="font-weight: bolder">Cab at Disposal <input type="radio" id="disposalCab" style="width: 5%;"  name="returnCab" ${disposal} value="0"  onclick="returnType(this.value,'checked');" /></td>
                        <td style="font-weight: bolder">Separate Booking <input type="radio" id="seperateCab" name="returnCab" value="1" ${seperate} onclick="returnType(this.value,'checked');"/> </td>
                    </tr>

                    <tr id="seperatePickupDetails" style="display: ${returnPickUpDisp};">
                        <td style="font-weight: bolder">Pick Up Point<label class="required">*</label></td>
                        <td>
                            <select id="returnPickUpPoint" name="returnPickUpPoint" class="selectbox_new" onchange="selectTravelDetails(this.value,'returnPickupDetails','returnPickupLandMark1','returnPickup')">
                                <option value="">--Select pick-up point--</option>
                                <c:forEach items="${travelPointsList}" var="travelPointsList" varStatus="i">
                                    <option value="${travelPointsList.addressId}" ${travelPointsList.addressId == lcViewDetails.returnPickUpPoint ? 'selected' : ''} >${travelPointsList.travelPoints}</option>
                                </c:forEach>
                                <option value="-3" ${lcViewDetails.returnPickUpPoint == '-3' ? 'selected' : ''}>Customer Office</option>
                                <option value="-2" ${lcViewDetails.returnPickUpPoint == '-2' ? 'selected' : ''}>Hotel</option>
                                <option value="-1" ${lcViewDetails.returnPickUpPoint == '-1' ? 'selected' : ''}>Others</option>
                            </select>
                        </td>
                        <td style="font-weight: bolder">Pick Up Date <label class="required">*</label></td>
                        <td><fmt:formatDate value="${lcViewDetails.returnPickUpDate}" var="returnPickUpDate" pattern="dd-MM-yyyy"/><input type="text" id="returnPickUpDate" class="textbox_new" name="returnPickUpDate" value="${returnPickUpDate}" /> </td>
                        <td style="font-weight: bolder">Pick Up Time(24 Hrs Format hh:mm) <label class="required">*</label></td>
                        <c:if test="${lcViewDetails != null}">
                            <c:set var="returnpickUpTime" value="${fn:split(lcViewDetails.returnPickUpTime, ':')}"/>
                        </c:if>
                        <td>
                            <input type="text" size="1" maxlength="2" class="inputbox_small" name="returnPickupHrs" id="returnPickupHrs" value="${returnpickUpTime[0]}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" >
                            &nbsp;:&nbsp;
                            <input type="text" size="1" maxlength="2" class="inputbox_small" name="returnPickupMins" id="returnPickupMins" value="${returnpickUpTime[1]}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);">
                        </td>
                    </tr>

                    <tr id="returnPickupDetails"  style="display:  none;">
                        <td id="returnPickup" style="font-weight: bolder"><label class="required">*</label></td><td><input type="text" id="returnPickupDoorNo" name="returnPickupDoorNo" class="textbox_new" value="${lcViewDetails.returnPickupDoorNo}"/></td>
                        <td style="font-weight: bolder">Street Name <label class="required">*</label></td><td><input type="text" id="returnPickupStreetName" name="returnPickupStreetName" class="textbox_new" value="${lcViewDetails.returnPickupStreetName}"/></td>
                        <td style="font-weight: bolder">Area <label class="required">*</label></td><td><input type="text" id="returnPickupArea" name="returnPickupArea" class="textbox_new" value="${lcViewDetails.returnPickupArea}"/></td>
                    </tr>
                    <tr id="returnPickupLandMark1"  style="display: none;">
                        <td style="font-weight: bolder">Land Mark <label class="required">*</label></td><td><input type="text" id="returnPickupLandMark" name="returnPickupLandMark" class="textbox_new" value="${lcViewDetails.returnPickupLandMark}"/> </td>
                    </tr>
                    <tr id="seperateDropDetails" style="display:  ${returnPickUpDisp};">
                        <td style="font-weight: bolder">Return Journey Destination <label class="required">*</label></td>
                        <td>
                            <select id="returnDropJourney" name="returnDropJourney" class="selectbox_new"  onchange="selectTravelDetails(this.value,'returnDropDetails','returnDropLandmark','returnDrop')">
                                <option value="">--Select drop-up point--</option>
                                <c:forEach items="${travelPointsList}" var="travelPointsList" varStatus="i">
                                    <option value="${travelPointsList.addressId}" ${travelPointsList.addressId == lcViewDetails.returnDropJourney ? 'selected' : ''}>${travelPointsList.travelPoints}</option>
                                </c:forEach>
                                <option value="-3" ${lcViewDetails.returnDropJourney == '-3' ? 'selected' : ''}>Customer Office</option>
                                <option value="-2" ${lcViewDetails.returnDropJourney == '-2' ? 'selected' : ''}>Hotel</option>
                                <option value="-1" ${lcViewDetails.returnDropJourney == '-1' ? 'selected' : ''}>Others</option>

                            </select>
                        </td>
                    </tr>

                    <tr id="returnDropDetails"  style="display: none;">
                        <td id="returnDrop" style="font-weight: bolder"><label class="required">*</label></td><td><input type="text" id="returnDropDoorNo" name="returnDropDoorNo" class="textbox_new" value="${lcViewDetails.returnDropDoorNo}"/></td>
                        <td style="font-weight: bolder">Street Name <label class="required">*</label></td><td><input type="text" id="returnDropStreetName" name="returnDropStreetName"class="textbox_new"  value="${lcViewDetails.returnDropStreetName}"/></td>
                        <td style="font-weight: bolder">Area <label class="required">*</label></td><td><input type="text" id="returnDropArea" name="returnDropArea" class="textbox_new" value="${lcViewDetails.returnDropArea}"/></td>
                    </tr>
                    <tr id="returnDropLandmark"  style="display: none;">
                        <td style="font-weight: bolder">Land Mark <label class="required">*</label></td><td><input type="text" id="returnDropLandMark" name="returnDropLandMark" class="textbox_new" value="${lcViewDetails.returnDropLandMark}"/> </td>
                    </tr>

                    <tr>
                        <td style="font-weight: bolder">Contact Number <c:if test="${system != 'G'}"><label style="color: red;" for="fine">*</label></c:if></td>
                        <td>
                            <input type="text" id="mobileNumber" maxlength="12" class="textbox_new" name="mobileNumber" value="${lcViewDetails.mobileNumber}" onkeypress="return blockNonNumbersColon(this, event, true, false);" onkeyup="return extractNumber(this,0,false);"/>
                            <c:if test="${system == 'G'}">
                                <a class="helpTooltip" href="javascript:;"></a>
                            </c:if>
                        </td>
                        <td style="font-weight: bolder">Alternate Contact Number</td><td><input type="text" maxlength="12" id="alternateMobileNumber" class="textbox_new" name="alternateMobileNumber" value="${lcViewDetails.alternateMobileNumber}" onkeypress="return blockNonNumbersColon(this, event, true, false);" onkeyup="return extractNumber(this,0,false);"/></td>
                    </tr>
                </table>
                <input type="hidden" value="1" id="attachmentCount" name="attachmentCount" />
                <table class="tableStructure" border="0" width="50%">
                    
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
                <div class="" style="margin-bottom: 5px;">
                    <c:if test="${lcViewDetails.tpRefId != null && lcViewDetails.tpRefId != '' && !(lcViewDetails.previous_status == 'c' || lcViewDetails.previous_status == 'a' || lcViewDetails.previous_status == 't' || (lcViewDetails.previous_status == 'f' && lcViewDetails.previous_status != 'c')) && lcViewDetails.status != 'x'}">
                        <div align="center" id="cancelRemarks" style="text-align: center;float:left;width:100%">
                            <table align="left" style="padding-left:20px;">
                                <tr>
                                    <th valign="top">Remarks on Cancellation&nbsp;</th>
                                    <td>
                                        <textarea name="cancel_remarks" class="textarea_new"  style="width:500px" rows="3" id="cancel_remarks" ></textarea>
                                    </td>
                                </tr>
                                <tr><td>&nbsp;<td><div class="error" id="cancel_remarks_error"></div></td></tr>
                            </table>
                        </div>
                    </c:if>
                    <div align="center">
                        <c:choose>
                            <c:when test="${lcViewDetails.tpRefId != null && lcViewDetails.tpRefId != ''}">
                                <c:if test="${!(lcViewDetails.previous_status == 'c' || lcViewDetails.previous_status == 'a' || lcViewDetails.previous_status == 't' || (lcViewDetails.previous_status == 'f' && lcViewDetails.previous_status != 'c') ) && lcViewDetails.status != 'x' }">
                                    <input name="submitAction" id="submitBtn" type="submit" value="Re Submit" class="buttons submitbutton"  style="cursor:pointer" >
                                    <input name="cancelAction" id="cancelBtn" type="submit" value="Cancel Request" class="buttons cancelbutton" style="cursor:pointer" >
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <input name="saveAction" id="saveBtn" type="submit" value="Save" class="buttons savebutton"  style="cursor:pointer" >
                                <input name="submitAction" id="submitBtn" type="submit" value="Submit" class="buttons submitbutton"  style="cursor:pointer" >
                            </c:otherwise>
                        </c:choose>
                    </div><br/>
                </div>
            </div>
        </form>
        <%@include file="/WEB-INF/jsp/approvers_list.jsp" %>
    </body>
    <script type="text/javascript">
        $(document).ready(function(){
            $("#reqDate").datepicker({dateFormat: 'dd-mm-yy',changeMonth: true,changeYear: true,minDate: new Date()});
            if($("#reqDate").val()==""){
                $('#reqDate').datepicker("setDate",new Date())
            }
            $("#onwardPickUpDate").datepicker({dateFormat: 'dd-mm-yy',changeMonth: true,changeYear: true,minDate: new Date()});
            $("#returnPickUpDate").datepicker({dateFormat: 'dd-mm-yy',changeMonth: true,changeYear: true,minDate: new Date()});
        });
    </script>
    <style>
        a {
            text-decoration: none;
        }
        .required {
            font-weight: bold;
            color :red;
        }
    </style>

</html>
