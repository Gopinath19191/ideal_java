
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
            <%-- $('#formNSClearance').attr("action", "listRegnSubmittedEmp.htm");
             $('#formNSClearance').submit();--%>
                     history.back();
                 }
                 function disableSubmit(saveButtonId,submitButtonId,backButtonId,btnType)
                 {
            <%--alert("Here....")
            alert(${Result}+"------");--%>
                    if(btnType=='Save'){
                        $("*").removeClass("required error");
            <%--  $("#"+saveButtonId).hide();
              $("#"+submitButtonId).hide();
              $("#"+backButtonId).hide();--%>
                      }
                      validateComments(saveButtonId,submitButtonId,backButtonId,btnType);

                  }
                  function validateComments(saveButtonId,submitButtonId,backButtonId,btnType){
            <%--alert("test data")--%>
                    if(btnType !='Save'){
                        var counter = 0;
                        $(".dueCommentsClass").each(
                        function(){
                            if($(this).parent().parent().find(".dueClass:checked").val()==2){
                                $(this).addClass("required");
                                counter++;
                            }
                            else{
            <%--alert("else..")--%>
                                $(this).removeClass("required");
                            }
                        }
                    );
            <%--$(".testdta").validate();--%>
            <%--alert($(".testdta").valid());--%>
                        if($("#formNSClearance").valid()){
                            $("#"+saveButtonId).hide();
                            $("#"+submitButtonId).hide();
                            $("#"+backButtonId).hide();
                        }
                    }}

        </script>
    </head>
    <body>
        <form action="saveNSClearanceData.htm" method="post" class="testdta" name="formNSClearance" id="formNSClearance">
            <div class="qpdSearch" style="height: auto;background-color: #FFF;margin-top: 10px;">
                <div class="formarea" style="margin-bottom: 0px;margin-top: 0px;">
                    <table width="98%" border="0" cellpadding="1" style="border:1px solid #DDDDDD;margin-left: -14px;">
                        <c:choose>
                            <c:when test="${menuDetails.network_approval!=1}">
                                <c:set value="disabled" var="disabledStatus" />
                                <c:set value="readonly" var="readonlyStatus" />
                            </c:when>
                            <c:when test="${menuDetails.network_approval==1 && exitEmpInfo.nsApprovedDate!=null}">
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
                                <td align="right" colspan="3" width="100%" height="5">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center" style="text-align: center;">
                                    <c:choose>
                                        <c:when test="${exitEmpInfo.nsApprovedDate!=null}">
                                            N&S  No due clearance approved on
                                            <fmt:formatDate value="${exitEmpInfo.nsApprovedDate}" pattern="dd-MM-yyyy" var="nsApprovedDate" /> ${nsApprovedDate}.
                                        </c:when>
                                        <c:otherwise>
                                            N&S Clearance Not Approved.
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr class="formarea_header">
                                <td width="25%" align="center" >
                                    <label class="headLabel">Assets</label>
                                </td>
                                <td width="24%" colspan="" align="center" >
                                    <label class="headLabel">Surrendered / Deactivated </label>
                                </td>
                                <td width="24%" align="center" >
                                    <label class="headLabel">Comments</label>
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td  align="center">
                                    <label class="displayText">Desktop Password Deactivated</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${nsActionData.desktopPwd ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${nsActionData.desktopPwd eq index.count}">
                                                        <input type="radio" style="width:30px" id="desktopPwd" name="desktopPwd" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="desktopPwd" name="desktopPwd" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="desktopPwd" name="desktopPwd" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="desktopPwdRemarks" name="desktopPwdRemarks" class="dueCommentsClass textbox_new" ${readonlyStatus} value="${nsActionData.desktopPwdRemarks}"maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Email ID Deactivation</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${nsActionData.emailDeactivation ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${nsActionData.emailDeactivation eq index.count}">
                                                        <input type="radio" style="width:30px" id="emailDeactivation" name="emailDeactivation" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="emailDeactivation" name="emailDeactivation" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="emailDeactivation" name="emailDeactivation" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="emailDeactivationRemarks" name="emailDeactivationRemarks" class="dueCommentsClass textbox_new" ${readonlyStatus} value="${nsActionData.emailDeactivationRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Sales CRM tool deactivated</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${nsActionData.salesCrm ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${nsActionData.salesCrm eq index.count}">
                                                        <input type="radio" style="width:30px" id="salesCrm" name="salesCrm" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="salesCrm" name="salesCrm" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="salesCrm" name="salesCrm" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="salesCrmRemarks" name="salesCrmRemarks" class="dueCommentsClass textbox_new"${readonlyStatus} value="${nsActionData.salesCrmRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Finance DaX tool deactivated</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${nsActionData.finDax ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${nsActionData.finDax eq index.count}">
                                                        <input type="radio" style="width:30px" id="finDax" name="finDax" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="finDax" name="finDax" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="finDax" name="finDax" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="finDaxRemarks" name="finDaxRemarks" class="dueCommentsClass textbox_new" ${readonlyStatus} value="${nsActionData.finDaxRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Laptop</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${nsActionData.laptop ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${nsActionData.laptop eq index.count}">
                                                        <input type="radio" style="width:30px" id="laptop" name="laptop" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="laptop" name="laptop" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="laptop" name="laptop" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="laptopRemarks" name="laptopRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${nsActionData.laptopRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Any Other (Please specify)</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${nsActionData.nsOthers ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${nsActionData.nsOthers eq index.count}">
                                                        <input type="radio" style="width:30px" id="nsOthers" name="nsOthers" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="nsOthers" name="nsOthers" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="nsOthers" name="nsOthers" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="nsOtherRemarks" name="nsOtherRemarks" class="dueCommentsClass textbox_new" ${readonlyStatus} value="${nsActionData.nsOtherRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <%--   <td width="49%" align="center" colspan="3">--%>
                                <td width="49%" align="center" style="text-align: center;" colspan="3">
                                    <label>Over All Comments</label>
                                    <textarea cols="25" rows="1" id="nsOverAllComments" name="nsOverAllComments"  maxlength="250"  minlength="0" onblur="textLimit(this,250);" onkeyup="textLimit(this,250)" class="formTextArea required  minlength maxlength resizableTextArea ui-resizable" ${disabledStatus} >${nsActionData.nsOverAllComments}</textarea>
                                </td>
                            </tr>
                            </tbody>
                    </table>
                            <c:if test="${menuDetails.network_approvalId==moduleId}">
                                <table class="buttonAlignment" align="center" >
                                    <tr>
                                        <td width="49%" align="center" style="text-align: center;" colspan="3">
                                            <input type="hidden" name="nsApprovalId" id="nsApprovalId" value="${nsActionData.nsApprovalId}" readonly />
                                            <input type="hidden" name="moduleId" id="moduleId" value="${moduleId}" readonly />
                                            <input type="hidden" id="exitEmpId" name="exitEmpId" value="${exitEmpInfo.exitEmpId}" readonly>
                                            <input type="hidden" name="resEmpId" id="resEmpId" value="${exitEmpInfo.resEmpId}" readonly />
                                            <input type="submit" name="buttonName" id="saveBtn" value="Save" class="savebutton" ${disabledStatus} onclick="return disableSubmit('saveBtn','submitBtn','btnCancel','Save');" />
                                            <input type="submit" name="buttonName" id="submitBtn" value="Submit" class="submitbutton" ${disabledStatus} onclick="return disableSubmit('saveBtn','submitBtn','btnCancel','Submit');" />
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
