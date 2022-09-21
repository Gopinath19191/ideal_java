<%-- 
    Document   : exitProcess
    Created on : Sep 30, 2011, 4:45:51 PM
    Author     : 14583
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
    <head>
        <script type="text/javascript">
            $(document).ready(function() {
                    });
                    function reject()
                    {
                        removeValidation();
                        $('#formUserDetails').attr("action", "proceedToExit.htm");
                        $('#formUserDetails').submit();
                    }
                    function removeValidation()
                    {
                        $('#contactNo').removeAttr("class");
                        $('#contactAddr').removeAttr("class");
                        $('#personalEmail').removeAttr("class");
                        $('#leavingReason').removeAttr("class");
                    }
                    function textLimit(field,maxlen) {
                        if(field.value.length >= maxlen){
                            while(field.value.length > maxlen){
                                field.value=field.value.replace(/.$/,'');
                            }
                            $('#errormessage').html('Please enter ' + maxlen +  ' characters');
                            //        alert('your input has been truncated!');
                        }
                    }
                    function textMinLimit(field,minlen) {
                        if(field.value.length < minlen){
                            $('#errormessage').html('Contact number should be ' + minlen +  ' characters');
                            //        alert('your input has been truncated!');
                        }
                    }
                    function disableSubmit(saveButtonId,submitButtonId,backButtonId,buttonValue)
                    {
						
                      if(buttonValue !='Save'){
						 
                          $("#contactNo").attr("maxlength", "10");
                          $("#contactNo").attr("class", "required number minlength maxlength inputfield error");
                          $("#personalEmail").attr("class", "required email maxlength inputfield error");
                          $("#leavingReason").attr("class", "required error textarea_new");
                          $("#addressType").attr("class", "required inputfield error");
                          $('.savesubmit').html('your form has been submitted successfully.');
                      }else{
                          $("#contactNo").attr("class", "inputfield");
                          $("#personalEmail").attr("class", "inputfield");
                          $("#leavingReason").removeAttr("class");
                          $("#addressType").attr("class", "inputfield");
                          $('.savesubmit').html('your form has been saved successfully.');
                      }
                      var a=$("#formUserDetails").valid();
					
					     if(a==true){
					     
                           document.getElementById("saveBtn").style.display="none";
			               document.getElementById("submitBtn").style.display="none";
                           document.getElementById("btnCancel").style.display="none";
						 }
                  }
        </script>
    </head>
    <body onload="getAddressDetails();changeTabClass('resigForm');" >
        <span class="topheading">EPM (Exit Process Management)</span>
        <div class="generalFormContent">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <form action="saveExitProcess.htm" id="formUserDetails" method="POST">
                <c:choose>
                    <c:when test="${empDetails.submitStatus eq empSubmitStatus}">
                        <c:set value="disabled" var="disabledStatus" />
                        <c:set value="readonly" var="readOnlyStatus" />
                    </c:when>
                    <c:otherwise>
                        <c:set value="" var="disabledStatus" />
                        <c:set value="" var="readOnlyStatus" />
                    </c:otherwise>
                </c:choose>
                <fmt:formatDate value="${empDetails.resignedDate}" var="resignedDate" pattern="dd-MM-yyyy" />
                <fmt:formatDate value="${empDetails.empDoj}" var="empDoj" pattern="dd-MM-yyyy" />
                <c:choose>
                    <c:when test="${empDetails.reasonRejection!=null && empDetails.reasonRejection!=''}">
                        <c:set var="rejectStyleValue" value="height: 51px;" />
                    </c:when>
                    <c:otherwise>
                        <c:set var="rejectStyleValue" value="height: 25px;" />
                    </c:otherwise>
                </c:choose>
                <div class="qpdSearch" style="${rejectStyleValue}}">
                    <c:if test="${empDetails.reasonRejection!=null && empDetails.reasonRejection!=''}">
                        Resignation Form rejected by your reporting manager.<br> Reason For Rejection : <b>${empDetails.reasonRejection}</b><br>
                    </c:if>
                    Note : To Add/Update your address details, please click My Information > Personal Information > Address
                </div>
                <div class="qpdSearch" style="height: auto;background-color: #FFF;">
                    <table width="80%" id="formTable" border="0" align="center">
                    <tbody>
                        <tr>
                            <td align="center" colspan="4" width="80%" height="1">
                                <div class="sucess-message">${succcessMsg}</div>
                            </td>
                        </tr>
                        <tr>
                            <td align="center" colspan="4" width="100%" height="15">
                                <div id="errormessage" class="error-message">${Result}</div>
                            </td>
                        </tr>
                        <c:if test="${empDetails ne null}">
                            <tr>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">DEC</label>
                                </td>
                                <td width="25%" class="headLabel">
                                    <input type="hidden" readonly name="employeeNumber" id="employeeNumber" class="formInputField" value="${empDetails.employeeNumber}">
                                    ${empDetails.employeeNumber}
                                </td>
                                <td width="25%" style="text-align:left;">
                                    <label class="headLabel">Name</label>
                                </td>
                                <td width="25%" class="headLabel">
                                    <input type="hidden" readonly name="employeeName" id="employeeName" class="formInputField" value="${empDetails.employeeName}">
                                    ${empDetails.employeeName}
                                </td>
                            </tr>
                            <tr>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Band</label>
                                </td>
                                <td width="25%" class="headLabel">
                                    <input type="hidden" readonly name="defiBand" id="defiBand" class="formInputField" value="${empDetails.band}">
                                    ${empDetails.band}
                                </td>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Sub Band</label>
                                </td>
                                <td width="25%" class="headLabel">
                                    <input type="hidden" readonly name="defiSubBand" id="defiSubBand" class="formInputField" value="${empDetails.subBand}">
                                    ${empDetails.subBand}
                                </td>
                            </tr>
                            <tr>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">SBU</label>
                                </td>
                                <td width="25%" class="headLabel">
                                    <input type="hidden" readonly name="defiSBU" id="defiSBU" class="formInputField" value="${empDetails.practiceGroup}">
                                    ${empDetails.practiceGroup}
                                </td>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Reporting Manager Name</label>
                                </td>
                                <td width="25%" class="headLabel">
                                    <input type="hidden" readonly name="rmName" id="rmName" class="formInputField" value="${empDetails.rmName}">
                                    ${empDetails.rmName}
                                    <input type="hidden" readonly name="rmId" id="rmId" class="formInputField" value="${empDetails.rmId}">
                                </td>
                            </tr>
                            <tr>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">DOJ</label>
                                </td>
                                <td width="25%" class="headLabel">
                                    ${empDoj}
                                </td>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Date of Resignation</label>
                                </td>
                                <td width="25%" class="headLabel">
                                    <%--<input type="hidden" readonly name="resignedDate" id="resignedDate" class="formInputField" value="${empDetails.resignedDate}">--%>
                                    ${resignedDate}
                                </td>
                            </tr>
                            <tr>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Contact Number</label>
                                </td>
                                <td width="25%" style="text-align:left">
                                    <input  ${readOnlyStatus} type="text" name="contactNo" id="contactNo" value="${empDetails.contactNo}" class="inputfield textbox_new">
                                </td>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Personal Email Id</label>
                                </td>
                                <td width="25%" style="text-align:left">
                                    <input  ${readOnlyStatus} type="text" class="inputfield textbox_new" name="personalEmail" id="personalEmail" value="${empDetails.personalEmail}" maxlength="50">
                                </td>
                            </tr>
                            <tr>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Reasons for Leaving</label>
                                </td>
                                <td width="25%" style="text-align:left">
                                   <select  name="leavingReason"  id="leavingReason">
                                    <option value="">--Select--</option>
                                    <c:forEach items="${exitEmployeeStatus}" var="exitEmployeeStatus" varStatus="index" >
                                        <option value="${exitEmployeeStatus.exitKey}"${empDetails.empLeavingReason==exitEmployeeStatus.exitKey ? 'selected' : ''}>${exitEmployeeStatus.exitValues}</option>
                                    </c:forEach>
                                </select>
                                </td>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Contact Address</label>
                                </td>
                                <td width="25%" style="text-align:left">
                                    <select name="addressType" id="addressType" class="inputfield selectbox" onchange="getAddressDetails()" >
                                        <option value="">Select Address</option>
                                        <c:choose>
                                            <c:when test="${fn:length(empAddress)!=0}">
                                                <c:forEach items="${empAddress}" var="empAddressDetails" varStatus="index" >
                                                    <option value="${empAddressDetails.empAddressId}" title="${empAddressDetails.empAddress}" ${empDetails.contactAddrId==empAddressDetails.empAddressId ? 'selected' : ''} >${empAddressDetails.empAddress}</option>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                   <option value="" title="No Data Available" >No Data Available</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td width="25%" style="text-align:left">
                                    <label class="headLabel">Address Details :</label>
                                </td>
                                <td colspan="3" style="text-align:left">
                                    <label id="empAddDetails"> </label>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                                <table id="formTable" class="buttonAlignment" border="0" align="center">
                        <tbody>
                            <tr>
								
								
                                <td  align="right" colspan="2">
                                    <input type="hidden" readonly name="employeeId" id="employeeId" value="${employeeId}" />
                                    <input type="hidden" readonly name="exitEmpId" id="exitEmpId" value="${empDetails.exitEmpId}" />
                                    <input ${disabledStatus} type="submit" name="buttonName" id="saveBtn" value="Save" class="savebutton" onclick="return disableSubmit('saveBtn','submitBtn','btnCancel',this.value);">
                                    <input ${disabledStatus} type="submit" name="buttonName" id="submitBtn" value="Submit" class="submitbutton" onclick="return disableSubmit('saveBtn','submitBtn','btnCancel',this.value);">
                                </td>
                                <td  align="left" colspan="2">
                                    <input  type="button" name="btnCancel" id="btnCancel" value="Back" class="backbutton" onclick="reject()">
                                </td>
                            </tr>
                        </c:if>
                    </tbody>
                </table>
                </div>
            </form>
        </div>
    </body>
</html>
