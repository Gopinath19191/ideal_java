<%-- 
    Document   : furtherAdvance
    Created on : Dec 13, 2012, 11:06:16 AM
    Author     : 14583
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/header.jsp" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/common.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js" ></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
        <script type="text/javascript">
            $(document).ready(function(){
                <fmt:formatDate  value="${tpDetails.travel_end_date}" var="tpEndDate" pattern="dd-MM-yyyy"  />
                    $('#travel_end_date').datepicker({
                        changeMonth: true,
                        changeYear: true,
                        dateFormat:'dd-mm-yy',
                        minDate : '${tpEndDate}'
                     });
                    });
        </script>
    </head>
    <body>
        <div id="tRequest" style="margin-top:0px">
            <div class="container_inner">
                <div class="page_heading">Domestic Travel Request - Further Advance
                    <div class="goToList">
                        <a class="showList" target="_self" href="${pageContext.request.contextPath}/getDashBoardList.htm?page=1">List Requests</a>
                        <a class="showList" target="_self" href="${pageContext.request.contextPath}/getAdvanceTpList.htm?page=1">List Further Advance Requests</a>
                        <a class="showList" style="text-decoration:none;color: #4C83B3;" target="_blank" href="http://ideal.defiance-tech.com/document/policyDocuments/${travelTypeText} Travel Policy.pdf">View ${travelTypeText} Travel Policy</a>
                    </div>
                </div>
                <div class="breadcrumb"></div>
                <div class="notice_page">
                    <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" /></div>
                    <div style="padding-left:10px;padding-top: 1px;">
                        <ul class="notice_page_ul">
                            <li style="margin: -13px 0 5px 10px;" >Further Advance should be raised with in the travel start date and travel end date</li>
                            <li style="margin: 0 0 5px 10px;" >Fields marked in * are mandatory.</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="formContent_new" style="margin-bottom: 5px;">
                <div class="datadisplay">
                    <table class="travelDetails_new_advance" cellspacing="3" cellpadding="3">
                        <tr width="100%" >
                            <td valign="top"><label for="requestDate">TP Id</label></td>
                            <td valign="top"><div id="tpId">${tpDetails.tpReferenceId}</div></td>
                            <td valign="top"><label for="empId">Employee Id</label></td>
                            <td valign="top"><div id="empId">${tpDetails.employeeNumber}</div></td>
                            <td valign="top"><label for="empName">Employee Name</label></td>
                            <td valign="top">${tpDetails.employeeName}</td>
                        </tr>
                        <tr>
                            <td valign="top"><label for="travel_start_date">Travel Start Date</label></td>
                            <td valign="top">
                                <fmt:formatDate  value="${tpDetails.travel_start_date}" var="tpStartDate" pattern="dd-MM-yyyy"  />
                                ${tpStartDate}</td>
                            <td valign="top"><label for="travel_end_date">Travel End Date</label></td>
                            <td valign="top">${tpEndDate}</td>
                            <td valign="top"><label for="travel_term">Travel Term</label></td>
                            <td valign="top">${tpDetails.travel_term}</td>
                        </tr>
                        <tr>
                            <td valign="top"><label for="advanceRequired">Advance</label></td>
                            <td valign="top">${tpDetails.advance_required}</td>
                            <td valign="top"><label for="travelPurpose">Currency</label></td>
                            <td valign="top">
                                <c:forEach items="${currencyList}" var="currencyListValues" varStatus="index" >
                                    ${tpDetails.currency_type==currencyListValues.key ? currencyListValues.value : ''}
                                </c:forEach>
                            </td>
                            <td valign="top"><label for="travelPurpose"></label></td>
                            <td valign="top"></td>
                        </tr>
                    </table>
                </div>
            </div>
            <form name="advanceForm" id="advanceForm" action="" method="POST" enctype="multipart/form-data" onSubmit="return validateTravelAdvanceSubmit()" >
                <div class="formContent_new" style="margin-bottom: 5px;">
                    <table class="travelDetails_new_advance" cellspacing="3" cellpadding="3">
                        <tr width="100%" >
                            <td valign="top"><label for="requestDate">Extended End Date<label style="color: red;" for="fine">*</label></label></td>
                            <td valign="top">
                                <input type="text" name="travelAdvanceEndDate" class="textbox_new datePick"  id="travelAdvanceEndDate" value="${tpEndDate}" />
                                <span id="travelAdvanceEndDate_error" class="error"></span>
                            </td>
                            <td valign="top">
                                <label for="requestDate">Amount<label style="color: red;" for="fine">*</label></label>
                            </td>
                            <td valign="top"><input name="furtherAdvance" id="furtherAdvance" class="textbox_new" maxlength="6" value="${travelData.advance_required}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" value="" />
                            <span id="furtherAdvance_error" class="error"></span></td>
                            <td valign="top"><label for="empId">Remarks<label style="color: red;" for="fine">*</label></label></td>
                            <td valign="top">
                                <textarea name="advanceRemarks" class="textarea_new"  cols="20" rows="2" id="advanceRemarks" ></textarea>
                                <span id="advanceRemarks_error" class="error"></span>
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="buttonAlignment" id="submitDiv">
                    <div class="submit buttonAlignment" align="center" id="btnGroup">
                        <input type="hidden" name="master_id" id="master_id" value="${tpDetails.master_id}" />
                        <input type="hidden" name="action" id="action" value="" />
                        <input type="hidden" name="system" id="system" value="${tpDetails.system}" />
                        <input type="hidden" name="tpWorkFlowId" id="tpWorkFlowId" value="${tpDetails.workflow_id}" />
<!--                        <input name="travelbtn" id="save_btn" type="submit" value="Save" class="buttons savebutton"  style="cursor:pointer" />-->
                        <input name="travelbtn" id="submit_btn" type="button" value="Submit" class="buttons submitbutton" onclick="formSubmit('saveFurtherAdvance.htm','Submitted')"  style="cursor:pointer" />
                        <input name="cancelRequest" id="cancel_btn" type="button" value="Cancel" class="buttons cancelbutton" onclick="formSubmit('${pageContext.request.contextPath}/getDashBoardList.htm?page=1','Submitted')" style="cursor:pointer;" />
                    </div>
                </div>
            </form>
        </div>
    </body>
    <script>
        function formSubmit(url,action){
            $("#action").val(action);
            $("#advanceForm").attr('action',url);
            $("#advanceForm").submit();
        }
    </script>
</html>
