
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
            function reject()
            {
            <%--$('#formRmClearance').attr("action", "listRegnSubmittedEmp.htm");
            $('#formRmClearance').submit();--%>
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
                      validateComments(saveButtonId,submitButtonId,backButtonId,btnType);

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
            <%--$("#formRmClearance").validate();--%>
                        if($("#formRmClearance").valid()){
                            $("#"+saveButtonId).hide();
                            $("#"+submitButtonId).hide();
                            $("#"+backButtonId).hide();
                        }
                    }}

        </script>
    </head>
    <body>
        <form action="saveRmClearanceData.htm" method="post" name="formRmClearance" id="formRmClearance">
            <div class="qpdSearch" style="height: auto;background-color: #FFF;margin-top: 10px;">
                <div class="formarea" style="margin-bottom: 0px;margin-top: 0px;">
                    <table width="100%" border="0" cellpadding="1" style="border:1px solid #DDDDDD;margin-left: -14px;">
                        <c:choose>
                            <c:when test="${menuDetails.rm_clearance!=1}">
                                <c:set value="disabled" var="disabledStatus" />
                                <c:set value="readonly" var="readonlyStatus" />
                            </c:when>
                            <c:when test="${menuDetails.rm_clearance==1 && exitEmpInfo.rmClrDate!=null}">
                                <c:set value="disabled" var="disabledStatus" />
                                <c:set value="readonly" var="readonlyStatus" />
                            </c:when>
                            <c:otherwise>
                                <c:set value="" var="disabledStatus" />
                                <c:set value="" var="readonlyStatus" />
                            </c:otherwise>
                        </c:choose>
                        <tbody>
                            <tr>
                                <td align="center" style="text-align: center;" colspan="3" width="100%" height="2">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center" style="text-align: center;">
                                    <c:choose>
                                        <c:when test="${exitEmpInfo.rmClrDate!=null}">
                                            RM  No due clearance approved by ${exitEmpInfo.rmName} on
                                            <fmt:formatDate value="${exitEmpInfo.rmClrDate}" pattern="dd-MM-yyyy" var="rmClrDate" /> ${rmClrDate}.
                                        </c:when>
                                        <c:otherwise>
                                            RM Clearance Not Approved.
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr class="formarea_header">
                                <td width="30%" align="center">
                                    <label class="headLabel">Assets</label>
                                </td>
                                <%--<td width="24%" colspan="" align="center" style="background-color:#99BBE8">--%>
                                <td width="20%" colspan="" align="center">
                                    <label class="headLabel">Surrendered / Deactivated </label>
                                </td>
                                <td width="24%" align="center">
                                    <label class="headLabel">Comments</label>
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td  align="center">
                                    <label class="displayText">Company Manuals / Documents / Training Material</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${rmActionData.compDoc ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${rmActionData.compDoc eq index.count}">
                                                        <input type="radio" style="width:30px" id="compDoc" name="compDoc" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="compDoc" name="compDoc" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="compDoc" name="compDoc" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="compDocRemarks" name="compDocRemarks" class="dueCommentsClass" ${readonlyStatus} value="${rmActionData.compDocRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Project / function related transition documents</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${rmActionData.projDoc ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${rmActionData.projDoc eq index.count}">
                                                        <input type="radio" style="width:30px" id="projDoc" name="projDoc" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="projDoc" name="projDoc" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="projDoc" name="projDoc" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="projDocRemarks" name="projDocRemarks" class="dueCommentsClass required"${readonlyStatus} value="${rmActionData.projDocRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Customer/Project Manager informed and approval obtained</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${rmActionData.custApproval ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${rmActionData.custApproval eq index.count}">
                                                        <input type="radio" style="width:30px" id="custApproval" name="custApproval" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="custApproval" name="custApproval" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="custApproval" name="custApproval" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="custApprovalRemarks" name="custApprovalRemarks" class="dueCommentsClass required"${readonlyStatus} value="${rmActionData.custApprovalRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">QPD completed</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${rmActionData.empQpd ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${rmActionData.empQpd eq index.count}">
                                                        <input type="radio" style="width:30px" id="empQpd" name="empQpd" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="empQpd" name="empQpd" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="empQpd" name="empQpd" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="empQpdRemarks" name="empQpdRemarks" class="dueCommentsClass required" ${readonlyStatus} value="${rmActionData.empQpdRemarks}"maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Any Other (Please specify)</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${rmActionData.rmOthers ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${rmActionData.rmOthers eq index.count}">
                                                        <input type="radio" style="width:30px" id="rmOthers" name="rmOthers" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="rmOthers" name="rmOthers" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="rmOthers" name="rmOthers" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="rmOthersRemarks" name="rmOthersRemarks" class="dueCommentsClass required" ${readonlyStatus} value="${rmActionData.rmOthersRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td width="49%" align="center" style="text-align:center;" colspan="3">
                                    <label>Over All Comments</label>
                                    <textarea cols="25" rows="1" id="rmClrOverAllComments" name="rmClrOverAllComments" maxlength="250"  minlength="0" onblur="textLimit(this,250);" onkeyup="textLimit(this,250)" class="formTextArea required  minlength maxlength resizableTextArea ui-resizable" ${disabledStatus} >${rmActionData.rmClrOverAllComments}</textarea>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <table class="buttonAlignment" align="center">
                        <c:if test="${Reportees_count!=0}">
                            <tr>
                                <td>
                                    <p style="color: red">Active employees(${Reportees_count}) are reporting to this employee. Kindly arrange to move them to New RM before clearance.</p>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${rmCleranceModId==moduleId}">
                            <tr>
                                <td width="49%" align="center" style="text-align: center;" colspan="3">
                                    <input type="hidden" name="rmClrId" id="rmClrId" value="${rmActionData.rmApprovalId}" readonly />
                                    <input type="hidden" name="moduleId" id="moduleId" value="${moduleId}" readonly />
                                    <input type="hidden" id="exitEmpId" name="exitEmpId" value="${exitEmpInfo.exitEmpId}" readonly>
                                    <input type="hidden" name="resEmpId" id="resEmpId" value="${exitEmpInfo.resEmpId}" readonly />
                                    <input type="submit" name="buttonName" id="saveBtn" value="Save" class="savebutton" ${disabledStatus} onclick="return disableSubmit('saveBtn','submitBtn','btnCancel','Save');" />
                                    <c:if test="${Reportees_count==0}">
                                        <input type="submit" name="buttonName" id="submitBtn" value="Submit" class="submitbutton" ${disabledStatus} onclick="return disableSubmit('saveBtn','submitBtn','btnCancel','Submit');" />
                                    </c:if>
                                    <input type="button" name="buttonName" id="btnCancel" value="Back" class="backbutton" onclick="reject()">
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </div>
            </div>
        </form>
    </body>
</html>
