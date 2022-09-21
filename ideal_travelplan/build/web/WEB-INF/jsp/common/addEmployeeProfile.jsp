<%-- 
    Document   : addEmployeeprofile
    Created on : Oct 10, 2012, 2:37:56 PM
    Author     : 15065
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="cn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
    <head>
    <%@include file="/WEB-INF/header.jsp" %>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Cache-Control"  content="no-cache"/>
    <meta http-equiv="Pragma" content="no-cache"/>
    <meta http-equiv="Expires" content="0"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simpletip-1.3.1.js"></script>
    <style type="text/css">
        .error {
            color:red;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#birthDate').datepicker({
                        changeMonth: true,
                        changeYear: true,
                        dateFormat:'dd-mm-yy',
                        maxDate: 0
                     });
            $("#mobileDisc").simpletip({
                content: 'This is not a mandatory field. However Admin team / Travel desk might need this to reach out to you for any clarifications/changes/emergency',
                fixed: true
            });
            $(".pnIdEdDisc").simpletip({
                content: 'This is required by the Admin team / Travel desk to check the validity of your passport before travel.',
                fixed: true
            });
            $("#nationalityDisc").simpletip({
                content: 'This is required by the Admin team / Travel desk to check the visa requirement for your travel.',
                fixed: true
            });
            $("#dobDisc").simpletip({
                content: 'This is required by the Admin team / Travel desk to book your tickets.',
                fixed: true
            });
            $("#alternateEmail").simpletip({
                content: 'In Case of Emergency.',
                fixed: true
            });
        });
    </script>
    </head>
    <body>
        <div id="tRequest">
            <div class="container_inner">
                <div class="page_heading">International Travel</div>
                <div class="breadcrumb">
                    <div class="goToList" style="margin-right: 56px;margin-top: -21px;margin-bottom: 5px;">
                        <a class="add"  target="_self" href="${pageContext.request.contextPath}/getDashBoardList.htm?page=1">List Requests</a>
                    </div>
                </div>
            </div>
            <form id="empGenericDetailsContent" name="empGenericDetailsContent" action="submitGenericDetails.htm" method="POST" onsubmit="return validateEmpGenericDetails();" autocomplete="off">
                <input type="hidden" id="master_id" name="master_id"  value="${request_id}" />
                <div class="formContent_new">
                    <div class="fieldsetHeading" style="color: #4C83B3;">Passport Details</div>
                    <div class="datadisplay">
                        <table class="travelDetails_new">
                            <tr>
                                <td>Sur Name <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="surName" name="surName"  value="${empGenericDetails.surName}" class="textbox_new"/>
                                    <span class="error" id="surName_error"></span>
                                </td>
                                <td>Given Name <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="givenName" name="givenName"  value="${empGenericDetails.givenName}" class="textbox_new"/>
                                    <span class="error" id="givenName_error"></span>
                                </td>
                                <td>Gender</td>
                                <c:choose>
                                    <c:when test="${empDetails.gender=='m'}">
                                        <c:set  var="male"  value="checked"/>
                                    </c:when>
                                    <c:otherwise>
                                          <c:set  var="female"  value="checked"/>
                                    </c:otherwise>
                                </c:choose>
                                <td><input type="radio" ${male} disabled id="male" name="gender" value="0">Male &nbsp;&nbsp;
                                    <input type="radio" ${female} disabled id="female" name="gender"  value="1">Female
                                </td>
                            </tr>
                            <tr>
                                <td>Nationality <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <select id="nationality" name="nationality" class="selectbox_new">
                                        <option value="">--Select Nationality--</option>
                                        <c:forEach items="${nationalityList}" var="nationality" varStatus="i">
                                            <option value="${nationality.nationalityId}"  ${nationality.nationalityId == empGenericDetails.nationality ? 'selected' : ''}>${nationality.nationality}</option>
                                        </c:forEach>
                                    </select>
                                    <c:if test="${system == 'G'}">
                                        <span class="helpTooltip" id="nationalityDisc" href="javascript:;"></span>
                                    </c:if>
                                    <span class="error" id="nationality_error"></span>
                                </td>
                                <td>Passport  Number <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="passportNumber" name="passportNumber" value="${empGenericDetails.passportNumber}" class="textbox_new"/>
                                    <c:if test="${system == 'G'}">
                                        <span class="helpTooltip pnIdEdDisc" id="passportNumberDisc" href="javascript:;"></span>
                                    </c:if>
                                    <span class="error" id="passportNumber_error"></span>
                                </td>
                                <c:if test="${system == 'G'}">
                                    <td>Date Of Birth</td>
                                    <td>
                                        <fmt:formatDate value="${empDetails.birthDate}" var="birthDate" pattern="dd-MM-yyyy"/>
                                        <c:choose>
                                            <c:when test="${system=='I'}">
                                                <c:set var="disabledStatus" value="disabled" />
                                            </c:when>
                                            <c:when test="${system=='G'}">
                                                <c:set var="disabledStatus" value="" />
                                            </c:when>
                                        </c:choose>
                                        <input type="text" id="birthDate" name="birthDate" ${disabledStatus} value="${birthDate}" class="textbox_new"/>
                                        <c:if test="${system == 'G'}">
                                            <span class="helpTooltip" id="dobDisc" href="javascript:;"></span>
                                        </c:if>
                                        <span class="error" id="birthDate_error"></span>
                                    </td>
                                </c:if>
                                <c:if test="${system != 'G'}">
                                    <td>Place Of Issue <label style="color: red;" for="fine">*</label></td>
                                    <td><input type="text" id="issuePlace" name="issuePlace" value="${empGenericDetails.issuePlace}" class="textbox_new"/></td>
                                </c:if>
                            </tr>
                            <tr>
                                <td>Issued Date <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <fmt:formatDate value="${empGenericDetails.issuedDate}" var="issuedDate" pattern="dd-MM-yyyy"/><input type="text" id="issuedDate" name="issuedDate" readonly value="${issuedDate}" class="textbox_new datePick"/>
                                    <c:if test="${system == 'G'}">
                                        <span class="helpTooltip pnIdEdDisc" id="passportNumberDisc" href="javascript:;"></span>
                                    </c:if>
                                    <span class="error" id="issuedDate_error"></span>
                                </td>
                                <td>Expiry Date <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <fmt:formatDate value="${empGenericDetails.expiryDate}" var="expiryDate" pattern="dd-MM-yyyy"/><input type="text" id="expiryDate" name="expiryDate" readonly value="${expiryDate}" class="textbox_new datePick"/>
                                    <c:if test="${system == 'G'}">
                                        <span class="helpTooltip pnIdEdDisc" id="passportNumberDisc" href="javascript:;"></span>
                                    </c:if>
                                    <span class="error" id="expiryDate_error"></span>
                                </td>
                                <c:if test="${system != 'G'}">
                                    <td>Place Of Birth <label style="color: red;" for="fine">*</label></td>
                                    <td>
                                        <input type="text" id="birthPlace" name="birthPlace" value="${empGenericDetails.birthPlace}" class="textbox_new"/>
                                        <span class="error" id="birthPlace_error"></span>
                                    </td>
                                </c:if>    
                            </tr>
                            <tr>
                                <c:if test="${system == 'I'}">
                                    <td>Date Of Birth</td>
                                    <td>
                                        <input type="text" id="birthDate" name="birthDate" disabled value="${empDetails.birth_date}" class="textbox_new"/>
                                        <span class="error" id="birthDate_error"></span>
                                    </td>
                                </c:if>
                                <c:if test="${system != 'G'}">
                                    <td>ECNR Status</td>
                                    <td>
                                        <select id="ecnrStatus" name="ecnrStatus" class="selectbox_new">
                                            <option value="" ${empGenericDetails.ecnrStatus == '' ? 'selected' : ''}>--Select ECNR Status--</option>
                                            <option value="0" ${empGenericDetails.ecnrStatus == 0 ? 'selected' : ''}>Required</option>
                                            <option value="1" ${empGenericDetails.ecnrStatus == 1 ? 'selected' : ''}>Not Required</option
                                        </select>
                                        <span class="error" id="ecnrStatus_error"></span>
                                    </td>
                                </c:if>
                            </tr>
                        </table>
                    </div>
                </div>
                <c:if test="${system!='G'}">
                    <div class="formContent_new">
                    <div class="fieldsetHeading" style="color: #4C83B3;">Insurance Details</div>
                    <div class="datadisplay">
                        <table class="travelDetails_new">
                            <tr>
                                <td>Nominee <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="nominee" name="nominee" value="${empGenericDetails.nominee}" class="textbox_new"/>
                                    <span class="error" id="nominee_error"></span>
                                </td>
                                <td>Relationship <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="relationShip" name="relationShip" value="${empGenericDetails.relationShip}" class="textbox_new"/>
                                    <span class="error" id="relationShip_error"></span>
                                </td>
                                <td>Door No <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="doorNo" name="doorNo" value="${empGenericDetails.doorNo}" class="textbox_new"/>
                                    <span class="error" id="doorNo_error"></span>
                                </td>
                            </tr>
                            <tr>
                                <td>Street Name <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="streetName" name="streetName" value="${empGenericDetails.streetName}" class="textbox_new"/>
                                    <span class="error" id="streetName_error"></span>
                                </td>
                                <td>Area <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="area" name="area" value="${empGenericDetails.area}" class="textbox_new"/>
                                    <span class="error" id="area_error"></span>
                                </td>
                                <td>Place <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="place" name="place" value="${empGenericDetails.place}" class="textbox_new"/>
                                    <span class="error" id="place_error"></span>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>                            
                </c:if>
                <div class="formContent_new">
                    <div class="fieldsetHeading" style="color: #4C83B3;">Personal Details</div>
                    <table class="travelDetails_new">
                        <tr>
                            <td>Contact Number<c:if test="${system != 'G'}"><label style="color: red;" for="fine">*</label></c:if></td>
                            <td>
                                <input type="text" id="mobileNo" name="mobileNo" maxlength="10" value="${empDetails.mobileNo}" class="textbox_new" onkeypress="return blockNonNumbersColon(this, event, true, false);" onkeyup="return extractNumber(this,0,false);"/>
                                <c:if test="${system == 'G'}">
                                    <span class="helpTooltip" id="mobileDisc" href="javascript:;"></span>
                                </c:if>
                                <span class="error" id="mobileNo_error"></span>
                            </td>
                            <c:if test="${system != 'I'}">
                            <td>Alternate Mail Id <label style="color: red;" for="fine">*</label></td>
                            <td>
                                <input type="text" id="alternateMailId" name="alternateMailId"  value="${empDetails.alternateMailId}" class="textbox_new"/>
                                <c:if test="${system == 'G'}">
                                    <span class="helpTooltip" id="alternateEmail" href="javascript:;"></span>
                                </c:if>
                                <span class="error" id="alternateMailId_error"></span>
                            </td>
                            </c:if>
                            <c:if test="${system != 'G'}">
                                <td>Meal Preference <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <select id="mealPreference" name="mealPreference" class="selectbox_new">
                                        <option value="" ${empGenericDetails.mealPreference == '' ? 'selected' : ''}>--Select Meal Preference--</option>
                                        <option value="0" ${empGenericDetails.mealPreference == 0 ? 'selected' : ''}>Hindu Meal</option>
                                        <option value="1" ${empGenericDetails.mealPreference == 1 ? 'selected' : ''}>Asian-Veg Meal</option>
                                        <option value="2" ${empGenericDetails.mealPreference == 2 ? 'selected' : ''}>Muslim Meal</option>
                                        <option value="3" ${empGenericDetails.mealPreference == 3 ? 'selected' : ''}>Jain Meal</option>
                                        <option value="4" ${empGenericDetails.mealPreference == 4 ? 'selected' : ''}>Vegetarian Meal</option>
                                    </select>
                                    <span class="error" id="mealPreference_error"></span>
                                </td>
                            </c:if>
                            <td>Official Mail Id</td>
                            <td><input type="text" id="officailMailId" name="officailMailId" disabled value="${empDetails.officialMailId}" class="textbox_new"/></td>
                        </tr>
                        <c:if test="${system != 'G'}">
                            <tr>
                                <td>Alternate Mail Id <label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" id="alternateMailId" name="alternateMailId"  value="${empDetails.alternateMailId}" class="textbox_new"/>
                                    <span class="error" id="alternateMailId_error"></span>
                                </td>
                            </tr> 
                        </c:if>
                    </table>
                    <input type="hidden" id="empId" name="empId" value="${employeeId}"/>
                    <input type="hidden" id="system" name="empId" value="${system}"/>
                    <div align="center">
                        <input type="submit" id="saveContinue" name="saveContinue" value="Save And Continue" class="savebutton"/>
                    </div><br/>
                </div>
            </form>
        </div>
    </body>
</html>


