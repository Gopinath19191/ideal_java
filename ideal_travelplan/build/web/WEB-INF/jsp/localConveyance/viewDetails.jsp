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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/localConvyance.css" type="text/css" media="all" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css" media="all" />

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <title>New Local Conveyance</title>

    </head>
    <body>
        <div class="container_inner">
            <div class="page_heading"> Local Conveyance Request
                <div class="goToList">
                    <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="List Request" alt="List Request">
                    <a href="${pageContext.request.contextPath}/getApprovalDashBoardList.htm?page=1&approveType=pending" target="_self" style="text-decoration:none;color: #4C83B3;">List Requests</a>
                    <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="List Request" alt="List Request">
                    <a href="${pageContext.request.contextPath}/getApprovalDashBoardList.htm?page=1&approveType=pending" target="_self" style="text-decoration:none;color: #4C83B3;">View Local Conveyance Policy</a>
                </div>
            </div>
            <div class="breadcrumb"> </div>
        </div>

        <form id="formContent" name="formContent" action="../approvalAction.htm" method="POST">
            <div class="formContent_new conveyance_details" style="margin-bottom: 5px;">
                <table border="0" align="center" class="travelDetails_new">
                    <tr>

                        <td style="font-weight: bolder">Travel Request Date</td><td ><fmt:formatDate value="${lcViewDetails.reqDate}" var="travelreqDate" pattern="dd-MM-yyyy"/>${travelreqDate}</td>
                        <td style="font-weight: bolder">Employee Id</td><td>${lcViewDetails.employeeNumber}</td>
                        <td style="font-weight: bolder">Employee Name</td><td>${lcViewDetails.employeeName}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">Designation</td><td>${lcViewDetails.designationName}</td>
                        <td style="font-weight: bolder">Business Group/Function</td><td>${lcViewDetails.practicegroupName}</td>
                        <td style="font-weight: bolder">Practice Group/Sub Function</td><td>${lcViewDetails.businessgroupName}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">Level</td><td>${lcViewDetails.bandName} </td>
                        <td style="font-weight: bolder">Base Location</td><td>${lcViewDetails.cityName} </td>
                        <td style="font-weight: bolder">Guest Booking </td>
                        <td>   <c:forEach items="${billableList}" var="guestBook" varStatus="i" >
                                <c:if test="${fn:containsIgnoreCase(guestBook.configKey, lcViewDetails.guestBooking)}">
                                      ${guestBook.configValue}
                                 </c:if>
                                    <%-- <c:if test="${guestBook.configKey == lcViewDetails.guestBooking }" >
                                         ${guestBook.configValue}
                                     </c:if>--%>
                                </c:forEach>
                        <td>
                    </tr>
                    <c:choose>
                        <%-- test="${lcViewDetails.guestBooking=='Y'}"--%>
                        <c:when test="${fn:containsIgnoreCase(lcViewDetails.guestBooking, 'y')}" >
                            <c:set var="guestDispStyle" value="table-row"/></c:when>
                        <c:otherwise>
                            <c:set var="guestDispStyle" value="none"/></c:otherwise>
                    </c:choose>
                    <tr id="travelGuestName" style="display: ${guestDispStyle};" >
                        <td colspan="4"></td>
                        <td style="font-weight: bolder">Guest Name </td>
                        <td>${lcViewDetails.guestName}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">Project Travel</td>
                        <td>
                            <c:choose>
                                <c:when test="${lcViewDetails.projectTravel == 'Y'}">Yes</c:when>
                                <c:otherwise>No</c:otherwise>
                            </c:choose>
                        </td>
                        <td style="font-weight: bolder">Project</td>
                        <td>
                            <c:choose>
                                <c:when test="${lcViewDetails.projectId != '-1'}">
                                    ${lcViewDetails.projectName}
                                </c:when>
                                <c:otherwise>
                                    Others
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="font-weight: bolder">Purpose of Travel</td><td>${lcViewDetails.travelPurpose} </td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.projectId == -1 && lcViewDetails.projectTravel=='Y'}">
                            <c:set var="prjDispStyle" value="table-row"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="prjDispStyle" value="none"/>
                        </c:otherwise>

                    </c:choose>
                    <tr id="otherProjects" style="display: ${prjDispStyle};">
                        <td colspan="2"></td>
                        <td style="font-weight: bolder">Project (Others) </td>
                        <td>${lcViewDetails.projectOthers}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">City </td>
                        <td>
                            <c:forEach items="${cityList}" var="cityList" varStatus="i">
                                <c:if test="${cityList.cityId == lcViewDetails.city}">
                                    ${cityList.cityName}
                                </c:if>
                            </c:forEach>
                            <c:if test="${lcViewDetails.city == -1}">
                                Other
                            </c:if>
                        </td>
                        <td style="font-weight: bolder">Remarks </td>                        
                        <td>${lcViewDetails.remarks_lc}</td>
                        <td style="font-weight: bolder">Is Reimbursable</td>
                        <td>
                            <c:choose>
                                <c:when test="${lcViewDetails.client_reimbursable == 'Y'}">Yes</c:when>
                                <c:otherwise>No</c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.city == -1}">
                            <c:set var="cityDispStyle" value="table-row"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="cityDispStyle" value="none"/>
                        </c:otherwise>

                    </c:choose>
                    <tr id="cityOthers" style="display: ${cityDispStyle};">
                        <td style="font-weight: bolder">City (Others)</td>
                        <td>${lcViewDetails.cityOthers}</td>
                    </tr>
                </table>
            </div>
            <div class="formContent_new" style="margin-bottom: 5px;">
                <table border="0" align="center" class="travelDetails_new">
                    <div class="fieldsetHeading" style="color: #4C83B3;">Onward Journey</div>
                    <tr>
                        <td style="font-weight: bolder">Pick Up Point</td>
                        <td>
                            <c:forEach items="${travelPoints}" var="travelPoints" varStatus="i">
                                <c:if test="${travelPoints.addressId==lcViewDetails.onwardPickUpPoint}">
                                    ${travelPoints.travelPoints}
                                </c:if>
                            </c:forEach>
                            <c:if test="${lcViewDetails.onwardPickUpPoint== -1}">
                                <c:set var="pickUpPlace" value="Door No"/>
                                Other
                            </c:if>
                            <c:if test="${lcViewDetails.onwardPickUpPoint== -2}">
                                <c:set var="pickUpPlace" value="Hotel Name"/>
                                Hotel
                            </c:if>
                            <c:if test="${lcViewDetails.onwardPickUpPoint== -3}">
                                <c:set var="pickUpPlace" value="Customer Office Name"/>
                                Customer Office
                            </c:if>

                        </td>
                        <td style="font-weight: bolder">Pick Up Date </td>
                        <td><fmt:formatDate value="${lcViewDetails.onwardPickUpDate}" var="pickUpDate" pattern="dd-MM-yyyy"/>${pickUpDate} </td>
                        <td style="font-weight: bolder">Pick Up Time(24 Hrs Format hh:mm)</td><td>${lcViewDetails.onwardPickUpTime} </td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.onwardPickUpPoint== -3 || lcViewDetails.onwardPickUpPoint== -2 || lcViewDetails.onwardPickUpPoint== -1}">
                            <c:set var="onwardPickupStyle" value="table-row"/>
                        </c:when>
                        <c:otherwise> <c:set var="onwardPickupStyle" value="none"/></c:otherwise>
                    </c:choose>
                    <tr id="pickUpDetails" style="display: ${onwardPickupStyle}">
                        <td style="font-weight: bolder">${pickUpPlace}</td><td>${lcViewDetails.onwardPickupDoorNo}</td>
                        <td> style="font-weight: bolder"Street Name </td><td>${lcViewDetails.onwardPickupStreetName}</td>
                        <td style="font-weight: bolder">Area</td><td>${lcViewDetails.onwardPickupArea}</td>
                    </tr>
                    <tr id="pickUpLandmark" style="display: ${onwardPickupStyle}">
                        <td style="font-weight: bolder">Land Mark</td><td>${lcViewDetails.onwardPickupLandMark}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder">Onward Journey Destination </td>
                        <td>
                            <c:forEach items="${travelPoints}" var="travelPoints" varStatus="i">
                                <c:if test="${travelPoints.addressId==lcViewDetails.onwardDropJourney}">
                                    ${travelPoints.travelPoints}
                                </c:if>
                            </c:forEach>
                            <c:if test="${lcViewDetails.onwardDropJourney== -1}">
                                <c:set var="dropPlace" value="Door No"/>
                                Other
                            </c:if>
                            <c:if test="${lcViewDetails.onwardDropJourney== -2}">
                                <c:set var="dropPlace" value="Hotel Name"/>
                                Hotel
                            </c:if>
                            <c:if test="${lcViewDetails.onwardDropJourney== -3}">
                                <c:set var="dropPlace" value="Customer office Name"/>
                                Customer Office
                            </c:if>

                        </td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.onwardDropJourney== -3 || lcViewDetails.onwardDropJourney== -2 || lcViewDetails.onwardDropJourney== -1}">
                            <c:set var="onwardDropStyle" value="table-row"/>
                        </c:when>
                        <c:otherwise> <c:set var="onwardDropStyle" value="none"/></c:otherwise>
                    </c:choose>
                    <tr id="dropDetails" style="display: ${onwardDropStyle}">
                        <td style="font-weight: bolder">${dropPlace}</td><td>${lcViewDetails.onwardDropDoorNo}</td>
                        <td style="font-weight: bolder">Street Name </td><td>${lcViewDetails.onwardDropStreetName}</td>
                        <td style="font-weight: bolder">Area </td><td>${lcViewDetails.onwardDropArea}</td>
                    </tr>
                    <tr id="dropLandmark" style="display: ${onwardDropStyle}">
                        <td style="font-weight: bolder">Land Mark </td><td>${lcViewDetails.onwardDropLandMark}</td>
                    </tr>
                </table>
            </div>
            <div class="formContent_new" style="margin-bottom: 5px;">
                <table border="0" align="center" class="travelDetails_new">
                    <div class="fieldsetHeading" style="color: #4C83B3;"> Return Journey</div>
                    <c:choose>
                        <c:when test="${lcViewDetails.returnCab == 0}">
                            <c:set value="checked" var="returnCab"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="checked" var="seperateCab"/>
                        </c:otherwise>
                    </c:choose>
                    <tr>
                        <td style="font-weight: bolder">Cab at Disposal <input type="radio" id="returnCab" style="width: 5%;" disabled name="returnCab" value="0" ${returnCab} /></td>
                        <td style="font-weight: bolder">Separate Booking <input type="radio" id="returnCab" name="returnCab" disabled value="1" ${seperateCab} /> </td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.returnCab == 1}">
                            <c:set value="table-row" var="dispStyle"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="none" var="dispStyle"/>
                        </c:otherwise>
                    </c:choose>
                    <tr id="seperatePickupDetails" style="display: ${dispStyle}">
                        <td style="font-weight: bolder">Pick Up Point </td>
                        <td>
                            <c:forEach items="${travelPoints}" var="travelPoints" varStatus="i">
                                <c:if test="${travelPoints.addressId==lcViewDetails.returnPickUpPoint}">
                                    ${travelPoints.travelPoints}
                                </c:if>
                            </c:forEach>
                            <c:if test="${lcViewDetails.returnPickUpPoint== -1}">
                                <c:set var="returnPickUp" value="Door No"/>
                                Other
                            </c:if>
                            <c:if test="${lcViewDetails.returnPickUpPoint== -2}">
                                <c:set var="returnPickUp" value="Hotel Name"/>
                                Hotel
                            </c:if>
                            <c:if test="${lcViewDetails.returnPickUpPoint== -3}">
                                <c:set var="returnPickUp" value="Customer Office Name"/>
                                Customer Office
                            </c:if>
                        </td>
                        <td style="font-weight: bolder">Pick Up Date </td>
                        <td>${lcViewDetails.returnPickUpDate} </td>
                        <td style="font-weight: bolder">Pick Up Time(24 Hrs Format hh:mm) </td><td>${lcViewDetails.returnPickUpTime}</td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.returnPickUpPoint == -1 || lcViewDetails.returnPickUpPoint == -2 || lcViewDetails.returnPickUpPoint == -3}">
                            <c:set value="table-row" var="innerDispStyle"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="none" var="innerDispStyle"/>
                        </c:otherwise>
                    </c:choose>
                    <tr id="returnPickupDetails"  style="display: ${innerDispStyle};">
                        <td style="font-weight: bolder">${returnPickUp}</td><td>${lcViewDetails.returnPickupDoorNo}</td>
                        <td style="font-weight: bolder">Street Name</td><td>${lcViewDetails.returnPickupStreetName}</td>
                        <td style="font-weight: bolder">Area</td><td>${lcViewDetails.returnPickupArea}</td>
                    </tr>
                    <tr id="returnPickupLandMark1"  style="display: ${innerDispStyle};">
                        <td style="font-weight: bolder">Land Mark</td><td>${lcViewDetails.returnPickupLandMark} </td>
                    </tr>
                    <tr id="seperateDropDetails" style="display: ${dispStyle};">
                        <td style="font-weight: bolder">Return Journey Destination </td>
                        <td>
                            <c:forEach items="${travelPoints}" var="travelPoints" varStatus="i">

                                <c:if test="${travelPoints.addressId==lcViewDetails.returnDropJourney}">
                                    ${travelPoints.travelPoints}
                                </c:if>
                            </c:forEach>
                            <c:if test="${lcViewDetails.returnDropJourney == '-1'}">
                                <c:set var="returnDrop" value="Door No"/>
                                Other
                            </c:if>
                            <c:if test="${lcViewDetails.returnDropJourney == '-2'}">
                                <c:set var="returnDrop" value="Hotel Name"/>
                                Hotel
                            </c:if>
                            <c:if test="${lcViewDetails.returnDropJourney == '-3'}">
                                <c:set var="returnDrop" value="Customer Office Name"/>
                                Customer Office
                            </c:if>

                        </td>
                    </tr>
                    <c:choose>
                        <c:when test="${lcViewDetails.returnDropJourney == -1 || lcViewDetails.returnDropJourney == -2 || lcViewDetails.returnDropJourney == -3}">
                            <c:set value="table-row" var="innerDispStyle"/>
                        </c:when>
                        <c:otherwise>
                            <c:set value="none" var="innerDispStyle"/>
                        </c:otherwise>
                    </c:choose>
                    <tr id="returnDropDetails"  style="display: ${innerDispStyle};">
                        <td style="font-weight: bolder">${returnDrop}</td><td>${lcViewDetails.returnDropDoorNo}</td>
                        <td style="font-weight: bolder">Street Name</td><td>${lcViewDetails.returnDropStreetName}</td>
                        <td style="font-weight: bolder">Area</td><td>${lcViewDetails.returnDropArea}</td>
                    </tr>
                    <tr id="returnDropLandmark"  style="display: ${innerDispStyle};">
                        <td style="font-weight: bolder">Land Mark</td><td>${lcViewDetails.returnDropLandMark}</td>
                    </tr>

                    <tr>
                        <td style="font-weight: bolder">Contact No </td><td>${lcViewDetails.mobileNumber}</td>
                        <td style="font-weight: bolder">Alternate Contact No</td><td>${lcViewDetails.alternateMobileNumber}</td>
                    </tr>
                </table>
            </div>
            <%@include file="/WEB-INF/jsp/approvers_list.jsp" %>
            <c:if test="${approveType=='pending'}">
                <table border="0" width="95%" style="float:left">
                    <tr class="headerCenter">
                        <th width="30%" align="right">Remarks&nbsp;&nbsp;&nbsp;</th>
                        <td width="70%" align="left"><textarea name="remarks" class="textarea_new" style="width:400px;" rows="3" id="remarks" ></textarea></td>
                    </tr>
                    <tr><td>&nbsp;</td></tr>
                </table>
                <div align="center">
                    <c:choose>
                        <c:when test="${moduleId==485}">
                            <input type="submit" id="approveAction" class="buttons submitbutton" name="approveAction" value="Ticket Booked" />
                        </c:when>
                        <c:when test="${moduleId==606}">
                            <input type="submit" id="approveAction" class="buttons submitbutton" name="approveAction" value="Money Deposited" />
                        </c:when>
                        <c:otherwise>
                            <input name="approveAction" id="save_btn" type="submit" value="Approve" class="buttons submitbutton"  style="cursor:pointer" >
                        </c:otherwise>
                    </c:choose>
                    <c:if test="${moduleId == '483'}">
                        <input name="cfoAction" id="save_btn" type="submit" value="Request for CFO" class="buttons submitbutton"  style="cursor:pointer" >
                    </c:if>
                    <c:if test="${moduleId != 485 && moduleId != 606}">
                        <input name="sendBackAction" id="submit_btn" type="submit" value="Send back" class="buttons submitbutton"  style="cursor:pointer" >
                    </c:if>
                </div>
            </c:if>
            <input type="hidden" id="newLCNumber" name="newLCNumber" value="${uniqueId}"/>
            <input type="hidden" readonly name="tpId" id="tpId" value="${uniqueId}"/>
            <input type="hidden" readonly name="employee_id" id="employee_id" value="${lcViewDetails.empId}"/>
            <input type="hidden" readonly name="system" id="system" value="${lcViewDetails.system}"/>
        </form>
    </body>
</html>
