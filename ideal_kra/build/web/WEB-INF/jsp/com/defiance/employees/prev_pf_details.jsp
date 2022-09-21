<%-- 
    Document   : prev_pf_details
    Created on : 28 Jul, 2015, 6:33:46 PM
    Author     : 16047
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#prevPfDetails_err').hide();
                $('#alreadyPensionedMember_err').hide();
                $('#pfAlreadyWithdrawn_err').hide();
                $('#pfAccNo_err').hide();
                $('#uanNo_err').hide();
                $('#dateOfExit_err').hide();
                $('#internationalWorkerStatus_err').hide();
                $('#countryOfOrigin_err').hide();
                $('#passportNumber_err').hide();
                $('#countryOfIssue_err').hide();
                $('#passportValidFrom_err').hide();
                $('#passportValidUpto_err').hide();
                if ($('#prev_pf_details').val() == 'Yes') {
                    $('#No1').removeAttr("checked");
                    $('#Yes1').attr("checked", "checked");
                } else if ($('#prev_pf_details').val() == 'No') {
                    $('#Yes1').removeAttr("checked");
                    $('#No1').attr("checked", "checked");
                } else {
                    $('#No1').attr("checked", "checked");
                }

                if ($('#already_pensioned_member').val() == 'Yes') {
                    $('#No2').removeAttr("checked");
                    $('#Yes2').attr("checked", "checked");
                    $('.pf_already_withdrawn').show();
                } else if ($('#already_pensioned_member').val() == 'No') {
                    $('#Yes2').removeAttr("checked");
                    $('#No2').attr("checked", "checked");
                    $('.pf_already_withdrawn').hide();
                } else {
                    $('#No2').attr("checked", "checked");
                    $('.pf_already_withdrawn').hide();
                }

                if ($('#pf_already_withdrawn').val() == 'Yes') {
                    $('#No3').removeAttr("checked");
                    $('#Yes3').attr("checked", "checked");
                } else if ($('#pf_already_withdrawn').val() == 'No') {
                    $('#Yes3').removeAttr("checked");
                    $('#No3').attr("checked", "checked");
                } else {
                    $('#No3').attr("checked", "checked");
                }

                if ($('#international_worker_status').val() == 'Yes') {
                    $('#No4').removeAttr("checked");
                    $('#Yes4').attr("checked", "checked");
                    $('.country_of_origin').show();
                    $('.passport_number').show();
                    $('.coutry_of_issue').show();
                    $('.passport_valid_from').show();
                    $('.passport_valid_upto').show();
                    $(".adjustWidth").css("width", "55px");
                } else if ($('#international_worker_status').val() == 'No') {
                    $('#Yes4').removeAttr("checked");
                    $('#No4').attr("checked", "checked");
                    $('.country_of_origin').hide();
                    $('.passport_number').hide();
                    $('.coutry_of_issue').hide();
                    $('.passport_valid_from').hide();
                    $('.passport_valid_upto').hide();
                    $('.adjustWidth').removeAttr("style");
                } else {
                    $('#No4').attr("checked", "checked");
                    $('.country_of_origin').hide();
                    $('.passport_number').hide();
                    $('.coutry_of_issue').hide();
                    $('.passport_valid_from').hide();
                    $('.passport_valid_upto').hide();
                    $('.adjustWidth').removeAttr("style");
                }
//                $('.pf_already_withdrawn').hide();
//                $('.country_of_origin').hide();
//                $('.passport_number').hide();
//                $('.coutry_of_issue').hide();
//                $('.passport_valid_from').hide();
//                $('.passport_valid_upto').hide();
            });
            function hidePfWithdrawn() {
                $('.pf_already_withdrawn').hide();
                $('#No3').attr("checked", "checked");
            }
            function showPfWithdrawn() {
                $('.pf_already_withdrawn').show();
            }
            function showHiddenFields() {
                $('.country_of_origin').show();
                $('.passport_number').show();
                $('.coutry_of_issue').show();
                $('.passport_valid_from').show();
                $('.passport_valid_upto').show();
                $(".adjustWidth").css("width", "55px");
            }
            function hideHiddenFields() {
                $('.country_of_origin').hide();
                $('.passport_number').hide();
                $('.coutry_of_issue').hide();
                $('.passport_valid_from').hide();
                $('.passport_valid_upto').hide();
                $('.adjustWidth').removeAttr("style");
            }

            $(function() {
                $("#dateOfExit").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat: 'yy-mm-dd',
                    maxDate: new Date(),
                    yearRange: '1950:' + (new Date).getFullYear(),
                    currentText: 'Now'
                });
                $(".datePick").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat: 'yy-mm-dd',
                    maxDate: new Date(),
                    yearRange: '1950:' + (new Date).getFullYear(),
                    currentText: 'Now'
                });
                $(".datePickMax").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat: 'yy-mm-dd',
                    minDate: new Date()
                });
            });

            function validatePfDetails() {
                var errCount = 0;

                if ($("#pfAccNo").val() == "" || $("#pfAccNo").val() == null) {
                    $('#pfAccNo_err').show();
                    errCount++;
                } else {
                    $('#pfAccNo_err').hide();
                }
                if ($("#uanNo").val() == "" || $("#uanNo").val() == null) {
                    $('#uanNo_err').show();
                    errCount++;
                } else {
                    $('#uanNo_err').hide();
                }
                if ($("#dateOfExit").val() == "" || $("#dateOfExit").val() == null) {
                    $('#dateOfExit_err').show();
                    errCount++;
                }
                else {
                    $('#dateOfExit_err').hide();
                }
                if ($('#Yes4').is(':checked')) {
                    if ($("#countryOfOrigin").val() == "" || $("#countryOfOrigin").val() == null) {
                        $('#countryOfOrigin_err').show();
                        errCount++;
                    }
                    else {
                        $('#countryOfOrigin_err').hide();
                    }
                    if ($("#passportNumber").val() == "" || $("#passportNumber").val() == null) {
                        $('#passportNumber_err').show();
                        errCount++;
                    }
                    else {
                        $('#passportNumber_err').hide();
                    }
                    if ($("#countryOfIssue").val() == "" || $("#countryOfIssue").val() == null) {
                        $('#countryOfIssue_err').show();
                        errCount++;
                    }
                    else {
                        $('#countryOfIssue_err').hide();
                    }
                    if ($("#passportValidFrom").val() == "" || $("#passportValidFrom").val() == null) {
                        $('#passportValidFrom_err').show();
                        errCount++;
                    }
                    else {
                        $('#passportValidFrom_err').hide();
                    }
                    if ($("#passportValidUpto").val() == "" || $("#passportValidUpto").val() == null) {
                        $('#passportValidUpto_err').show();
                        errCount++;
                    }
                    else {
                        $('#passportValidUpto_err').hide();
                    }
                }

                if (errCount > 0) {
                    return false;
                } else {
                    return true;
                }
            }
        </script>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/com/defiance/employees/common.jsp" %>
        <div class="contentwrap" style="height:auto;">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <table width="100%" border="0">
                <tbody>
                    <tr>
                        <td>
                            <form action="prevPfSubmit.htm" name="prevPfForm" id="prevPfForm" method="post" onSubmit="return validatePfDetails();">
                                <input type="hidden" id="actionName" name="actionName" value="${actionName}" />

                                <table class="tableStructure" border="0">
                                    <tr class="headerCenter">
                                        <c:choose>
                                            <c:when test="${accessType == 'HR'}">
                                                <!--                                                <th width="20%">Employee Name</th>
                                                                                                <th width="20%">Adhar Number</th>
                                                                                                <th width="20%">Remarks</th>-->
                                                <th width="10%">Employee Name</th>
                                                <th width="10%">Are you already a member of PF in your Prev. Establishment</th>
                                                <th width="10%">Are you already a member of Pension in your Prev. Establishment</th>
                                                <th width="10%">Have you withdrawn the PF & Pension Amount for Your Prev.EPF Account</th>
                                                <th width="10%">PF Account No</th>
                                                <th width="10%">UAN No</th>
                                                <th width="10%">Date of Exit for your Prev. PF Account No</th>
                                                <th width="10%">International Worker Status</th>
                                                <th width="10%">Country Of Origin</th>
                                                <th width="10%">Passport Number</th>
                                                <th width="10%">Country Of Issue</th>
                                                <th width="10%">Passport Valid From</th>
                                                <th width="10%">Passport Valid Upto</th>
                                                </c:when>
                                                <c:otherwise>
                                                <th width="10%"><font color="red">*</font>Are you already a member of PF in your Prev. Establishment</th>
                                                <th width="10%"><font color="red">*</font>Are you already a member of Pension in your Prev. Establishment</th>
                                                <th width="10%" class="pf_already_withdrawn"><font color="red">*</font>Have you withdrawn the PF & Pension Amount for Your Prev.EPF Account</th>
                                                <th width="10%"><font color="red">*</font>PF Account No</th>
                                                <th width="10%"><font color="red">*</font>UAN No</th>
                                                <th width="10%"<font color="red">*</font>Date of Exit for your Prev. PF Account No</th>
                                                <th width="10%"><font color="red">*</font>International Worker Status</th>
                                                <th width="10%" class="country_of_origin"><font color="red">*</font>Country Of Origin</th>
                                                <th width="10%" class="passport_number"><font color="red">*</font>Passport Number</th>
                                                <th width="10%" class="coutry_of_issue"><font color="red">*</font>Country Of Issue</th>
                                                <th width="10%" class="passport_valid_from"><font color="red">*</font>Passport Valid From</th>
                                                <th width="10%" class="passport_valid_upto"><font color="red">*</font>Passport Valid Upto</th>

                                                <!--                                                <th width="4%">Action</th>-->
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${accessType == 'HR'}">
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr class="${dataStatus.count%2==0?'altrow':''}">
                                                    <td valign ="top" align="left">
                                                        ${data.empName}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.prevPfDetails}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.alreadyPensionedMember}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.pfAlreadyWithdrawn}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.pfAccNo}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.uanNo}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.dateOfExit}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.internationalWorkerStatus}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.countryOfOrigin}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.passportNumber}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.countryOfIssue}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.passportValidFrom}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.passportValidUpto}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr>
                                                    <td colspan="12" align="center">
                                                        <font color="red">No Records Found.Search it by Employee Name / Employee Id to find records</font>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:if test="${!empty(result)}">
                                                <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                    <input type="hidden" id="pfId" name="pfId" value="${data.pfId}" />
                                                    <tr>
                                                        <td valign ="top" align="center">
                                                            <input type="hidden" id="prev_pf_details" value="${data.prevPfDetails}">
                                                            <input type="radio" name="prevPfDetails" id="Yes1" value="Yes">Yes
                                                            <input type="radio" name="prevPfDetails" id="No1" value="No">No
                                                            <div id="prevPfDetails_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center">
                                                            <input type="hidden" id="already_pensioned_member" value="${data.alreadyPensionedMember}">
                                                            <input type="radio" name="alreadyPensionedMember" id="Yes2" value="Yes" onchange="showPfWithdrawn();">Yes
                                                            <input type="radio" name="alreadyPensionedMember" id="No2" value="No" onchange="hidePfWithdrawn();">No
                                                            <div id="alreadyPensionedMember_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center" class="pf_already_withdrawn">
                                                            <input type="hidden" id="pf_already_withdrawn" value="${data.pfAlreadyWithdrawn}">
                                                            <input type="radio" name="pfAlreadyWithdrawn" id="Yes3" value="Yes">Yes
                                                            <input type="radio" name="pfAlreadyWithdrawn" id="No3" value="No">No
                                                            <div id="pfAlreadyWithdrawn_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center">
                                                            <input class="adjustWidth" type="text" name="pfAccNo" id="pfAccNo" value="${data.pfAccNo}">
                                                            <div id="pfAccNo_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center">
                                                            <input class="adjustWidth" type="text" name="uanNo" id="uanNo" value="${data.uanNo}">
                                                            <div id="uanNo_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center">
                                                            <input class="adjustWidth" type="text" name="dateOfExit" id="dateOfExit" value="${data.dateOfExit}" readonly>
                                                            <div id="dateOfExit_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center">
                                                            <input type="hidden" id="international_worker_status" value="${data.internationalWorkerStatus}">
                                                            <input type="radio" name="internationalWorkerStatus" id="Yes4" value="Yes" onchange="showHiddenFields();">Yes
                                                            <input type="radio" name="internationalWorkerStatus" id="No4" value="No" onchange="hideHiddenFields();">No
                                                            <div id="internationalWorkerStatus_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center" class="country_of_origin">
                                                            <input class="adjustWidth" type="text" name="countryOfOrigin"  id="countryOfOrigin" value="${data.countryOfOrigin}">
                                                            <div id="countryOfOrigin_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center" class="passport_number">
                                                            <input class="adjustWidth" type="text" name="passportNumber"  id="passportNumber" value="${data.passportNumber}">
                                                            <div id="passportNumber_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center" class="coutry_of_issue">
                                                            <input class="adjustWidth" type="text" name="countryOfIssue"  id="countryOfIssue" value="${data.countryOfIssue}">
                                                            <div id="countryOfIssue_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center" class="passport_valid_from">
                                                            <input class="adjustWidth datePick" type="text" name="passportValidFrom"  id="passportValidFrom" value="${data.passportValidFrom}" readonly>
                                                            <div id="passportValidFrom_err"><span style="color: red">required</span></div>
                                                        </td>
                                                        <td valign ="top" align="center" class="passport_valid_upto">
                                                            <input class="adjustWidth datePickMax" type="text" name="passportValidUpto"  id="passportValidUpto" value="${data.passportValidUpto}" readonly>
                                                            <div id="passportValidUpto_err"><span style="color: red">required</span></div>
                                                        </td>
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty(result)}">
                                                <input type="hidden" id="pfId" name="pfId" value="" />
                                                <tr>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" id="prev_pf_details" value="">
                                                        <input type="radio" name="prevPfDetails" id="Yes1" value="Yes">Yes
                                                        <input type="radio" name="prevPfDetails" id="No1" value="No">No
                                                        <div id="prevPfDetails_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" id="already_pensioned_member" value="">
                                                        <input type="radio" name="alreadyPensionedMember" id="Yes2" value="Yes" onchange="showPfWithdrawn();">Yes
                                                        <input type="radio" name="alreadyPensionedMember" id="No2" value="No" onchange="hidePfWithdrawn();">No
                                                        <div id="alreadyPensionedMember_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center" class="pf_already_withdrawn">
                                                        <input type="hidden" id="pf_already_withdrawn" value="">
                                                        <input type="radio" name="pfAlreadyWithdrawn" id="Yes3" value="Yes">Yes
                                                        <input type="radio" name="pfAlreadyWithdrawn" id="No3" value="No">No
                                                        <div id="pfAlreadyWithdrawn_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input class="adjustWidth" type="text" name="pfAccNo" id="pfAccNo">
                                                        <div id="pfAccNo_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input class="adjustWidth" type="text" name="uanNo" id="uanNo">
                                                        <div id="uanNo_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input class="adjustWidth" type="text" name="dateOfExit" id="dateOfExit" readonly>
                                                        <div id="dateOfExit_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" id="international_worker_status" value="">
                                                        <input type="radio" name="internationalWorkerStatus" id="Yes4" value="Yes" onchange="showHiddenFields();">Yes
                                                        <input type="radio" name="internationalWorkerStatus" id="No4" value="No" onchange="hideHiddenFields();">No
                                                        <div id="internationalWorkerStatus_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center" class="country_of_origin">
                                                        <input class="adjustWidth" type="text" name="countryOfOrigin"  id="countryOfOrigin">
                                                        <div id="countryOfOrigin_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center" class="passport_number">
                                                        <input class="adjustWidth" type="text" name="passportNumber"  id="passportNumber">
                                                        <div id="passportNumber_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center" class="coutry_of_issue">
                                                        <input class="adjustWidth" type="text" name="countryOfIssue"  id="countryOfIssue">
                                                        <div id="countryOfIssue_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center" class="passport_valid_from">
                                                        <input class="adjustWidth datePick" type="text" name="passportValidFrom"  id="passportValidFrom" readonly>
                                                        <div id="passportValidFrom_err"><span style="color: red">required</span></div>
                                                    </td>
                                                    <td valign ="top" align="center" class="passport_valid_upto">
                                                        <input class="adjustWidth datePickMax" type="text" name="passportValidUpto"  id="passportValidUpto" readonly>
                                                        <div id="passportValidUpto_err"><span style="color: red">required</span></div>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                                <c:if test="${accessType != 'HR'}" >
                                    <table class="buttonClass">
                                        <tr>
                                            <td><input type="submit" name="buttonSubmit" id="buttonSubmit" value="Submit" class="submitbutton" /></td>
                                        </tr>
                                    </table>
                                </c:if>
                            </form>
                        </td>
                    </tr>
                    <c:if test="${accessType == 'HR' && paging[0] > 1}">
                        <%@include file="/WEB-INF/jsp/com/defiance/employees/paging.jsp" %>
                    </c:if>
                </tbody>
            </table>
        </div>
    </body>
</html>
