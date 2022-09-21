<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            <%-- $('#formAdminClearance').attr("action", "listRegnSubmittedEmp.htm");
             $('#formAdminClearance').submit();--%>
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
                  $(".resizableTextArea").resizable();
                  //$(".resizableTextArea").attr("title","Drag the triangle at the bottom right corner to resize the textarea");


                  function noenter() {
                      return !(window.event && window.event.keyCode == 13);
                  }
            <%--function validateComments(saveButtonId,submitButtonId,backButtonId,btnType){
                                if(btnType !='Save') {
                                    var counter = 0;
                                    if($("#formAdminClearance").valid()){
                                        $("#"+saveButtonId).hide();
                                        $("#"+submitButtonId).hide();
                                        $("#"+backButtonId).hide();
                                    }
                                }
                            }--%>
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
            <%--$("#formAdminClearance").validate();--%>
                        if($("#formAdminClearance").valid()){
                            $("#"+saveButtonId).hide();
                            $("#"+submitButtonId).hide();
                            $("#"+backButtonId).hide();
                        }
                    }}
        </script>
    </head>
    <body>
        <form action="saveAdminClearanceData.htm" method="post" name="formAdminClearance" id="formAdminClearance">
            <div class="qpdSearch" style="height: auto;background-color: #FFF;margin-top: 10px;">
                <div class="formarea" style="margin-bottom: 0px;margin-top: 0px;">
                    <table width="98%" border="0" align ="center" cellpadding="1" style="border:1px solid #DDDDDD;margin-left: -14px;">
                        <c:choose>
                            <c:when test="${menuDetails.admin_approval!=1}">
                                <c:set value="disabled" var="disabledStatus" />
                                <c:set value="readonly" var="readonlyStatus" />
                            </c:when>
                            <c:when test="${menuDetails.admin_approval==1 && exitEmpInfo.adApprovedDate!=null}">
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
                                <td align="center" colspan="3" width="100%" height="15">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center" style="text-align: center;">
                                    <c:choose>
                                        <c:when test="${exitEmpInfo.adApprovedDate!=null}">
                                            Admin  No due clearance approved on
                                            <fmt:formatDate value="${exitEmpInfo.adApprovedDate}" pattern="dd-MM-yyyy" var="adApprovedDate" /> ${adApprovedDate}.
                                        </c:when>
                                        <c:otherwise>
                                            Admin Clearance Not Approved.
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr class="formarea_header">
                                <td width="27%" align="center">
                                    <label class="headLabel">Assets</label>
                                </td>
                                <td width="24%" colspan="" align="center">
                                    <label class="headLabel">Surrendered / Deactivated </label>
                                </td>
                                <td width="24%" align="center">
                                    <label class="headLabel">Comments</label>
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td  align="left">
                                    <label class="displayText">ID Card/Access Card</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.idCard ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.idCard eq index.count}">
                                                        <input type="radio" style="width:30px" id="idCard" name="idCard" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="idCard" name="idCard" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="idCard" name="idCard" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="idCardRemarks" name="idCardRemarks" class=" dueCommentsClass textbox_new "  value="${adActionData.idCardRemarks}" maxlength="150" ${readonlyStatus} />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Internet Data Card</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.dataCard ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.dataCard eq index.count}">
                                                        <input type="radio" style="width:30px" id="dataCard" name="dataCard" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="dataCard" name="dataCard" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="dataCard" name="dataCard" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="dataCardRemarks" name="dataCardRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.dataCardRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Business Cards</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.businessCard ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.dataCard eq index.count}">
                                                        <input type="radio" style="width:30px" id="businessCard" name="businessCard" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="businessCard" name="businessCard" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="businessCard" name="businessCard" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="businessCardRemarks" name="businessCardRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.businessCardRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Company Provided Residence Phone</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.cmpResPhone ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.cmpResPhone eq index.count}">
                                                        <input type="radio" style="width:30px" id="cmpResPhone" name="cmpResPhone" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="cmpResPhone" name="cmpResPhone" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="cmpResPhone" name="cmpResPhone" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="cmpResPhoneRemarks" name="cmpResPhoneRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.cmpResPhoneRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Mobile Phone handset</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.mobilePhone ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.mobilePhone eq index.count}">
                                                        <input type="radio" style="width:30px" id="mobilePhone" name="mobilePhone" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="mobilePhone" name="mobilePhone" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="mobilePhone" name="mobilePhone" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="mobilePhoneRemarks" name="mobilePhoneRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.mobilePhoneRemarks}"maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Mobile Phone SIM Card</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.simCard ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.simCard eq index.count}">
                                                        <input type="radio" style="width:30px" id="simCard" name="simCard" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="simCard" name="simCard" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="simCard" name="simCard" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="simCardRemarks" name="simCardRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.simCardRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Outstanding Mobile Bills Cleared</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.outBillClr ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.outBillClr eq index.count}">
                                                        <input type="radio" style="width:30px" id="outBillClr" name="outBillClr" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="outBillClr" name="outBillClr" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="outBillClr" name="outBillClr" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="outBillClrRemarks" name="outBillClrRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.outBillClrRemarks}"  maxlength="150" />
                                </td>
                            </tr>
<!--                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Laptop</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.laptop ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.laptop eq index.count}">
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
                                    <input type="text" style="width:300px;" id="laptopRemarks" name="laptopRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.laptopRemarks}" maxlength="150" />
                                </td>
                            </tr>-->
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">All external mass storage devices (CD/pen drives)</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.cdPenDrive ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.cdPenDrive eq index.count}">
                                                        <input type="radio" style="width:30px" id="cdPenDrive" name="cdPenDrive" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="cdPenDrive" name="cdPenDrive" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="cdPenDrive" name="cdPenDrive" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="cdPenDriveRemarks" name="cdPenDriveRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.cdPenDriveRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Cabin Keys</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.cabinKey ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.cabinKey eq index.count}">
                                                        <input type="radio" style="width:30px" id="cabinKey" name="cabinKey" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="cabinKey" name="cabinKey" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="cabinKey" name="cabinKey" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="cabinKeyRemarks" name="cabinKeyRemarks" class=" dueCommentsClass textbox_new "${readonlyStatus} value="${adActionData.cabinKeyRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayText">Drawer/Storage Keys</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.drawerKey ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.drawerKey eq index.count}">
                                                        <input type="radio" style="width:30px" id="drawerKey" name="drawerKey" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="drawerKey" name="drawerKey" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="drawerKey" name="drawerKey" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="drawerKeyRemarks" name="drawerKeyRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.drawerKeyRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText">Any Other (Please specify)</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${adActionData.adOther ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${adActionData.adOther eq index.count}">
                                                        <input type="radio" style="width:30px" id="adOther" name="adOther" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:30px" id="adOther" name="adOther" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:30px" id="adOther" name="adOther" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:300px;" id="adOtherRemarks" name="adOtherRemarks" class=" dueCommentsClass textbox_new " ${readonlyStatus} value="${adActionData.adOtherRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td width="30%" align="center" style="text-align: center;" colspan="3">
                                    <label>Over All Comments</label>
                                    <textarea cols="25" rows="1" id="adOverAllComments" name="adOverAllComments"  maxlength="250"  minlength="0" onblur="textLimit(this,250);" onkeyup="textLimit(this,250)" class="formTextArea  required  minlength maxlength resizableTextArea ui-resizable" ${disabledStatus} >${adActionData.adOverAllComments}</textarea>
                                </td>
                            </tr>
                            </tbody>
                    </table>
                                    <c:if test="${menuDetails.admin_approvalId==moduleId}">
                                        <table class="buttonAlignment" align="center" >
                                            <tr>
                                                <td width="49%" align="center" style="text-align: center;" colspan="3">
                                                    <input type="hidden" name="adApprovalId" id="adApprovalId" value="${adActionData.adApprovalId}" readonly />
                                                    <input type="hidden" name="moduleId" id="moduleId" value="${moduleId}" readonly />
                                                    <input type="hidden" name="resEmpId" id="resEmpId" value="${exitEmpInfo.resEmpId}" readonly />
                                                    <input type="hidden" id="exitEmpId" name="exitEmpId" value="${exitEmpInfo.exitEmpId}" readonly>
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
