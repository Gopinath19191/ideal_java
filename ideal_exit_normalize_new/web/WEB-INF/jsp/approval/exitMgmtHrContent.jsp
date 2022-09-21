<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script type="text/javascript">
            $(document).ready(function() {
                $(".dueClass").click(
                function(){
                    $(this).parent().find(".dueClass").removeClass("error");
                    if($(this).val()!=2){
                        $(this).parent().parent().find(".dueCommentsClass").removeClass("required error");
                    }
                });
            });
            <%-- $(document).ready(function() {
                $("#formHRClearance").validate({
                    submitHandler: function(form) {
                            $(".buttons").each(  function(){$(this).hide();} );
                            form.submit();
                        }
                });
            });--%>
                function reject()
                {
            <%--  $('#formHRClearance').attr("action", "listRegnSubmittedEmp.htm");
              $('#formHRClearance').submit();--%>
                      history.back();
                  }
                  function disableSubmit(saveButtonId,submitButtonId,backButtonId,btnType)
                  {
                      if(btnType=='Save'){
                          $("*").removeClass("required error");
            <%--  $("#"+saveButtonId).hide();
              $("#"+submitButtonId).hide();
              $("#"+backButtonId).hide();--%>
                      }
                      return validateComments(saveButtonId,submitButtonId,backButtonId,btnType);

                  }
                  function validateComments(saveButtonId,submitButtonId,backButtonId,btnType){
                      if(btnType !='Save'){
                          var counter = 0;
                          $(".dueCommentsClass").each(
                          function(){
                              if($(this).parent().parent().find(".dueClass:checked").val()==2){
                                  $(this).addClass("required");
                                  counter++;
                              }
                              else{
                                  $(this).removeClass("required");
                              }
                          }
                      );
                          $("#formHRClearance").validate();
                          if($("#formHRClearance").valid()){
                              $("#"+saveButtonId).hide();
                              $("#"+submitButtonId).hide();
                              $("#"+backButtonId).hide();
                          }
                      }
            <%--return true;--%>
                }
        </script>
    </head>
    <body>
        <form action="saveHRClearanceData.htm" method="post" name="formHRClearance" id="formHRClearance">
            <div class="qpdSearch" style="height: auto;background-color: #FFF;margin-top: 10px;">
                <div class="formarea" style="margin-bottom: 0px;margin-top: 0px;">
                    <c:choose>
                        <c:when test="${exitEmpInfo.hrApprovedDate!=null}">
                            <c:set value="disabled" var="hrDisabledStatus" />
                            <c:set value="readonly" var="readonlyStatus" />
                        </c:when>
                        <c:otherwise>
                            <c:set value="" var="hrDisabledStatus" />
                            <c:set value="" var="readonlyStatus" />
                        </c:otherwise>
                    </c:choose>
                    <table width="98%" border="0" cellpadding="1" style="border:1px solid #DDDDDD;margin-left: -14px;">
                        <tbody>
                            <tr>
                                <td align="center" colspan="3" width="100%" height="15">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <tr class="formarea_header">
                                <td width="13%" align="center">
                                    <label class="headLabel">Assets</label>
                                </td>
                                <td width="28%" colspan="" align="center">
                                    <label class="headLabel">Surrendered / Deactivated</label>
                                </td>
                                <td width="4%" align="center">
                                    <label class="headLabel">Comments</label>
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td  align="center">
                                    <label class="displayText">iDeal Tool Deactivated</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${hrActionData.idealToolDeactivated ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${hrActionData.idealToolDeactivated eq index.count}">
                                                        <input type="radio" style="width:30px" id="idealToolDeactivated" name="idealToolDeactivated" ${hrDisabledStatus} class="required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="idealToolDeactivated" name="idealToolDeactivated" ${hrDisabledStatus} class="required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="idealToolDeactivated" name="idealToolDeactivated" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="idealToolDeactivatedRemarks" name="idealToolDeactivatedRemarks" class="dueCommentsClass textbox_new required" ${readonlyStatus} value="${hrActionData.idealToolDeactivatedRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td  align="center">
                                    <label class="displayText">Leave Balance</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${hrActionData.leaveBalance ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${hrActionData.leaveBalance eq index.count}">
                                                        <input type="radio" style="width:30px" id="leaveBalance" name="leaveBalance" ${hrDisabledStatus} class="required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="leaveBalance" name="leaveBalance" ${hrDisabledStatus} class="required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="leaveBalance" name="leaveBalance" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="leaveBalanceRemarks" name="leaveBalanceRemarks" class="dueCommentsClass textbox_new required" ${readonlyStatus} value="${exitEmpInfo.leaveBalance}" maxlength="150" readonly />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td  align="center">
                                    <label class="displayText">Overseas Bond/Training Bond</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${hrActionData.overseasBond ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${hrActionData.overseasBond eq index.count}">
                                                        <input type="radio" style="width:30px" id="overseasBond" name="overseasBond" ${hrDisabledStatus} class="required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="overseasBond" name="overseasBond" ${hrDisabledStatus} class="required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="overseasBond" name="overseasBond" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="overseasBondRemarks" name="overseasBondRemarks" class="dueCommentsClass textbox_new required" ${readonlyStatus} value="${hrActionData.overseasBondRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td  align="center">
                                    <label class="displayText">Exit Interview</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${hrActionData.exitInterview ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${hrActionData.exitInterview eq index.count}">
                                                        <input type="radio" style="width:30px" id="exitInterview" name="exitInterview" ${hrDisabledStatus} class="required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="exitInterview" name="exitInterview" ${hrDisabledStatus} class="required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="exitInterview" name="exitInterview" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="exitInterviewRemarks" name="exitInterviewRemarks" class="dueCommentsClass textbox_new required" ${readonlyStatus} value="${hrActionData.exitInterviewRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td  align="center">
                                    <label class="displayText">Insurance</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${hrActionData.hrInsurance ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${hrActionData.hrInsurance eq index.count}">
                                                        <input type="radio" style="width:30px" id="hrInsurance" name="hrInsurance" ${hrDisabledStatus} class="required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="hrInsurance" name="hrInsurance" ${hrDisabledStatus} class="required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="hrInsurance" name="hrInsurance" class=" dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="hrInsuranceRemarks" name="hrInsuranceRemarks" class="dueCommentsClass textbox_new required" ${readonlyStatus} value="${hrActionData.hrInsuranceRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Any Other (Please specify)</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${hrActionData.hrOthers ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${hrActionData.hrOthers eq index.count}">
                                                        <input type="radio" style="width:30px" id="hrOthers" name="hrOthers" ${hrDisabledStatus} class="required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="hrOthers" name="hrOthers" ${hrDisabledStatus} class="required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="hrOthers" name="hrOthers" class="required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="hrOthersRemarks" name="hrOthersRemarks" class="dueCommentsClass textbox_new required" ${readonlyStatus} value="${hrActionData.hrOthersRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="displayText">Employment Status</label>
                                    
                                </td>
                                <td colspan="2">
                                    <select  name="employmentStatus"  id="employmentStatus"  class="selectbox required displayText">
                                    <option value="" >----Select Status----</option>
                                    <c:forEach items="${employmentStatus}" var="employmentStatus" >
                                        <option value="${employmentStatus.employmentStatusId}" ${employmentStatus.employmentStatusId==hrActionData.empStatus ? 'selected' : ''}>${employmentStatus.employmentStatus}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label class="displayText">Remarks</label>
                                    
                                </td>
                                <td colspan="2">
                                    <textarea style="width:480px;"cols="25" rows="1" id="remarks" name="remarks" maxlength="250"  minlength="0" onblur="textLimit(this,250);" value=""
                                              onkeyup="textLimit(this,250)" class="formTextArea required  minlength maxlength resizableTextArea ui-resizable" >${hrActionData.exitTypeRemarks}</textarea>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="checkbox" name="checkPF" checked>Does the PF details has to be send to Associates personal mail id?</td>
                            </tr>   
                            <tr>
                                <td colspan="2"><input type="checkbox" name="checkAccpLetter" checked>Does the Acceptance letter has to be send to Associates personal mail id?</td>
                            </tr>
                            <tr class="formarea_row2">
                                <%--  <td width="49%" align="center" colspan="3">--%>
                                <td width="49%" align="center" style="text-align: center;" colspan="3">
                                    <label>Over All Comments</label>
                                    <textarea cols="25" rows="1" id="hrOverAllComments" name="hrOverAllComments" maxlength="250"  minlength="0" onblur="textLimit(this,250);" onkeyup="textLimit(this,250)" class="formTextArea required  minlength maxlength resizableTextArea ui-resizable" ${hrDisabledStatus} >${hrActionData.hrOverAllComments}</textarea>
                                </td>
                            </tr>
                            </tbody>
                    </table>
                            <c:if test="${menuDetails.hr_approvalId==moduleId}">
                              
                                <table class="buttonAlignment" align="center" >
                                    <tr>
                                        <td width="49%" align="center" style="text-align: center;" colspan="3">
                                            <input type="hidden" name="hrApprovalId" id="hrApprovalId" value="${hrActionData.hrApprovalId}" readonly />
                                            <input type="hidden" id="empStatus" name="empStatus" value="${exitEmpInfo.empStatus}"/>
                                            <input type="hidden" name="moduleId" id="moduleId" value="${moduleId}" readonly />
                                            <input type="hidden" id="exitEmpId" name="exitEmpId" value="${exitEmpInfo.exitEmpId}" readonly />
                                            <input type="hidden" id="manager" name="manager" value="${exitEmpInfo.manager}" readonly />
                                            <input type="hidden" name="resEmpId" id="resEmpId" value="${exitEmpInfo.resEmpId}" readonly />
                                            <input type="submit" name="buttonName" id="saveBtn" value="Save" class="submitbutton" ${hrDisabledStatus} onclick="return disableSubmit('saveBtn','submitBtn','btnCancel','Save');" />
                                            <input type="submit" name="buttonName" id="submitBtn" value="Submit" class="submitbutton" ${hrDisabledStatus} onclick="return disableSubmit('saveBtn','submitBtn','btnCancel','Submit');" />
                                            <input type="button" name="buttonName" id="btnCancel" value="Back" class="backbutton" onclick="reject()">
                                        </td>
                                    </tr>
                                </table>
                            </c:if>
                </div>
            </div>
        </form>
    </body>
</html>

