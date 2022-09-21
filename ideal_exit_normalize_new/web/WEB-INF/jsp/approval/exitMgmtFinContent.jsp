<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script type="text/javascript">
            var newRowArr=new Array();
            var newRowArr1=new Array();
            var countRow;

            $(document).ready(function(){
            <c:forEach items="${finMultipleData}" var="finData">
                    newRowArr1.push(${finData.otherId});
                    newRowArr.push(${finData.otherId});
            </c:forEach>
                    document.formFinanceClearance.newRow1.value=newRowArr1;
                    if(newRowArr1.length==0){
                        countRow=1;
                        newRowArr.push(0);
                        newRowArr.push(1);
                    }else{
                        countRow=newRowArr1[newRowArr1.length-1];
                        document.formFinanceClearance.newRow.value=newRowArr1;
            <%--alert(countRow);--%>
                    }
                });
                     
                     
                function addRow(tableID,rowObject,message) {
                    var table = document.getElementById(tableID);
                    var rowCount = table.rows.length;
                    countRow++;
                    var category=$(rowObject).find(".otherCategory").val();
            <%--alert(category)--%>
                    if($.trim(category)=='L'){
                        message='Other Loans and advances';
                    }else
                    {
                        message='Any Others (Please specify)';
                    }

                    var newRow = $("<tr><td align='center'><label class='displayTextH'>"+message+"</label></td>\n\
<td class='displayText' align='center'><input style='width:28px' id='"+countRow+"' name='otherLoans"+countRow+"' class='dueClass required' value='1'  type='radio'>Yes\n\
 <input style='width:28px' id='otherLoans"+countRow+"' name='otherLoans"+countRow+"' class='dueClass required' value='2' type='radio'>No\n\
<input style='width:28px' id='otherLoans"+countRow+"' name='otherLoans"+countRow+"' class='dueClass required' value='3' type='radio'>NA\n\
<input type='hidden' class='hiddenId' value='"+countRow+"'/>\n\
<input type='hidden' class='otherCategory' name='category"+countRow+"' value='"+category+"'/>\n\
</td>\n\
<td class='displayText' align='center'>\n\
  <input style='width:100px;' id='otherLoanAmt"+countRow+"' name='otherLoanAmt"+countRow+"' maxlength='8' onchange='sumOfAmount();' class='dueCommentsClass amount required number' value='' type='text'>\n\
</td>\n\
<td class='displayText' align='center'>\n\
  <input style='width:280px;' id='otherLoanRemarks"+countRow+"' name='otherLoanRemarks"+countRow+"' class='dueCommentsClass' value='' type='text'>\n\
 </td>\n\
<td id='actionItems' style='text-align:center'>\n\
 <a onclick=\"addRow('finTable',$(this).parent().parent(),'message')\" linkindex='1' href='#'>\n\
  <img src='images/add.gif' alt='add'>\n\
  </a>\n\
<a onclick=\"deleteRow('finTable',$(this).parent().parent())\" linkindex='1' href='#'>\n\
  <img src='images/delete.gif' alt='delete'>\n\
  </a>\n\
  </td>\n\
</tr>");
                    $(rowObject).after(newRow);
                    newRowArr.push(countRow);
                    document.formFinanceClearance.newRow.value=newRowArr;
                }
                
                function deleteRow(tableID,rowObject) {
                    try {
                        var table = document.getElementById(tableID);
                        var rowCount = table.rows.length;
                        var hiddenId= $(rowObject).find(".hiddenId").val();
            <%--alert(hiddenId+"---"+parseInt(hiddenId));--%>
                        $(rowObject).remove();
                              
                        var index=newRowArr.indexOf(parseInt(hiddenId));
            <%--alert("::::::"+index)--%>
                        newRowArr.splice(index,1);
            <%--alert(newRowArr);--%>
                        document.formFinanceClearance.newRow.value=newRowArr;
                    }catch(e) {
                        alert(e);
                    }
                }

                var amountArray = new Array();

                function sumOfAmount(){
                    var totalAmount = 0;
                    $(".amount").each(function(index, Element){
                        if($("#totalAmount").val()!=""){
                            amountArray.splice(index,$(this).val());
                        }
                        var amount=$(this).val();
                        if(isNaN(amount) || amount==""){
                            amount=0;
                        }
                        amountArray.push(amount);
                    });
                    for(var i=0;i<amountArray.length;i++){
                        totalAmount+=parseFloat(amountArray[i],10);

                    }
                    // alert("The total amount is ="+totalAmount);
            <%--alert(totalAmount);--%>
                    $("#totalAmount").val(totalAmount);
                }

            <%--$(document).ready(function(){
            $(".amount").change(function() {
                var totalAmount = 0;
                $(".amount").each(function(index, Element){
                    if($("#totalAmount").val()!=""){
                        amountArray.splice(index,$(this).val());
                    }
                    var amount=$(this).val();
                    if(isNaN(amount) || amount==""){
                        amount=0;
                    }
                    amountArray.push(amount);
                });
                for(var i=0;i<amountArray.length;i++){
                    totalAmount+=parseFloat(amountArray[i],10);

    }
    // alert("The total amount is ="+totalAmount);
    alert(totalAmount);
    $("#totalAmount").val(totalAmount);
});

});--%>
                              
    $(document).ready(function() {
        $(".dueClass").click(
        function(){
            $(this).parent().find(".dueClass").removeClass("error");
            if($(this).val()!=1){
                $(this).parent().parent().find(".dueCommentsClass").removeClass("required error");
            }
        });
    });
           
    function reject()
    {

        history.back();
    }
    function disableSubmit(saveButtonId,submitButtonId,backButtonId,btnType)
    {
           
        if(btnType=='Save'){
            $("*").removeClass("required error");<%--
               $("#"+saveButtonId).hide();
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
                               if($(this).parent().parent().find(".dueClass:checked").val()==1){
                                   $(this).addClass("required");
                                   counter++;
                               }
                               else{
                                   $(this).removeClass("required");
                               }
                           }
                       );
            <%--$("#formFinanceClearance").validate();--%>
                        if($("#formFinanceClearance").valid()){
                            $("#"+saveButtonId).hide();
                            $("#"+submitButtonId).hide();
                            $("#"+backButtonId).hide();
                        }
                    }
            <%--return true;--%>
                }

                $(document).ready(function() {

                    $("#lastPaidSalary option[value='${finActionData.lastPaidSalary}']").attr("selected", "selected");

                });


                function commentsMandatory(){
                    if($("#travelAdvance").val()!="" && $("#travelAdvance").val()==0)
                    {
                        if($("#travelAdvanceRemarks").val()==""){
                            $('#travelAdvanceRemarks').attr("class", "required");
                            alert("Please Enter Comments");
                            return false;
                        }else{
                            return true;
                        }
                    }
                }
    
        </script>
    </head>
    <body>
        <form action="saveFinanceClearanceData.htm" method="post" name="formFinanceClearance" id="formFinanceClearance">
            <div class="qpdSearch" style="height: auto;background-color: #FFF;margin-top: 10px;">
                <div class="formarea" style="margin-bottom: 0px;margin-top: 0px;">
                    <table width="98%" border="0" cellpadding="1" style="border:1px solid #DDDDDD;margin-left: -14px;" id="finTable">
                        <c:choose>
                            <c:when test="${menuDetails.fin_approval!=1}">
                                <c:set value="disabled" var="disabledStatus" />
                                <c:set value="readonly" var="readonlyStatus" />
                            </c:when>
                            <c:when test="${menuDetails.fin_approval==1 && exitEmpInfo.finApprovedDate!=null}">
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
                                <td align="center" colspan="5" width="100%" height="5">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="5" align="center" style="text-align: center;">
                                    <c:choose>
                                        <c:when test="${exitEmpInfo.finApprovedDate!=null}">
                                            Finance  No due clearance approved on
                                            <fmt:formatDate value="${exitEmpInfo.finApprovedDate}" pattern="dd-MM-yyyy" var="finApprovedDate" /> ${finApprovedDate}.
                                        </c:when>
                                        <c:otherwise>
                                            Finance Clearance Not Approved.
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr class="formarea_header">
                                <td width="25%" align="center">
                                    <label class="headLabel">Assets</label>
                                </td>
                                <td width="25%" colspan="" align="center">
                                    <label class="headLabel">Employee Dues</label></td>
                                <td width="8%" colspan="" align="center">
                                    <label class="headLabel">Amount</label>
                                </td>
                                <td width="32%" align="center">
                                    <label class="headLabel">Comments</label>
                                </td>
                                <td width="6%" align="center">
                                    <label class="headLabelFin">Actions</label>
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayText required">Last paid salary - Month
                                    </label>
                                </td>
                                <td  align="center">
                                    <select id="lastPaidSalary" name="lastPaidSalary" ${disabledStatus} onClick="validateForm1(document.testform)">
                                        <c:forEach items="${monthList}" var="month" varStatus="index" >
                                            <option value="${month.key}" ${finActionData.lastPaidSalary==month.key ? 'selected' : ''} >${month.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="lastPaidSalaryAmt" name="lastPaidSalaryAmt" class="required number" ${disabledStatus} maxlength="8" value="${finActionData.lastPaidSalaryAmt}" />
                                </td>
                                <td align="center" class="displayText" colspan="2">
                                    <input type="text" style="width:280px;" id="lastPaidSalaryRemarks" name="lastPaidSalaryRemarks" class="required" ${readonlyStatus} value="${finActionData.lastPaidSalaryRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_header">
                                <td colspan="5" align="center">
                                    <label class="headLabel">Advance Settlements</label>
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td>
                                    <label class="displayTextH">Salary Advance settlements</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${finActionData.salaryAdvance ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finActionData.salaryAdvance eq index.count}">
                                                        <input type="radio" style="width:28px" id="salaryAdvance" name="salaryAdvance" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="salaryAdvance" name="salaryAdvance" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:28px" id="salaryAdvance" name="salaryAdvance" class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="salAdvAmt" name="salAdvAmt" class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8" onchange="sumOfAmount();" value="${finActionData.salAdvAmt}"  />
                                </td>
                                <td align="center" class="displayText" >
                                    <input type="text" style="width:280px;" id="salaryAdvanceRemarks" name="salaryAdvanceRemarks" class="dueCommentsClass required"  ${readonlyStatus} value="${finActionData.salaryAdvanceRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td  align="center">
                                    <label class="displayTextH">Travel Advance settlements</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${finActionData.travelAdvance ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finActionData.travelAdvance eq index.count}">
                                                        <input type="radio" style="width:28px" id="travelAdvance" name="travelAdvance" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="travelAdvance" name="travelAdvance" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:28px" id="travelAdvance" name="travelAdvance" class="dueClass required "  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="travelAdvAmt" name="travelAdvAmt" class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8"  onchange="sumOfAmount();" value="${finActionData.travelAdvAmt}" />
                                </td>
                                <td colspan="2" align="center" class="displayText">
                                    <input type="text" style="width:280px;" id="travelAdvanceRemarks" name="travelAdvanceRemarks" class="dueCommentsClass required"  ${readonlyStatus} value="${finActionData.travelAdvanceRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <c:set var="categoryL" value="0" />
                            <c:set var="actions" value="0" />
                            <c:forEach items="${finMultipleData}" var="finData" varStatus="status">
                                <c:if test="${finData.otherCategory eq 'L'}">
                                    <c:set var="actions" value="${actions+1}" />
                                    <c:set var="categoryL" value="1" />
                                    <tr class="formarea_row1">
                                        <td  align="center">
                                            <label class="displayTextH">Other Loans and advances </label>
                                            <input type="hidden" class="otherCategory" name="category${finData.otherId}"  value="${finData.otherCategory}"/>
                                            <input type="hidden" class="hiddenId"  value="${finData.otherId}"/>
                                        </td>
                                        <td align="center" class="displayText">
                                            <c:choose>
                                                <c:when test="${finActionData.travelAdvance ne null}">
                                                    <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                        <c:choose>
                                                            <c:when test="${finData.other eq index.count}">
                                                                <input type="radio" style="width:28px" id="otherLoans" name="otherLoans${finData.otherId}" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="radio" style="width:28px" id="otherLoans" name="otherLoans${finData.otherId}" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </c:when>
                                                <c:otherwise>
                                                    <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                        <input type="radio" style="width:28px" id="otherLoans" name="otherLoans${finData.otherId}" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                                    </c:forEach>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td align="center" class="displayText">
                                            <input type="text" style="width:100px;" id="otherLoanAmt" name="otherLoanAmt${finData.otherId}"  class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8"  onchange="sumOfAmount();" value="${finData.otherAmt}" />
                                        </td>
                                        <td align="center" class="displayText">
                                            <input type="text" style="width:280px;" id="otherLoanRemarks" name="otherLoanRemarks${finData.otherId}"  class="dueCommentsClass required"  ${readonlyStatus} value="${finData.otherRemarks}" maxlength="150" />
                                        </td>
                                        <td id="actionItems" style="text-align:center" ${disabledStatus} >
                                            <%--<c:choose>
                           <c:when test="${Id.finApprovedDate==disabledStatus}">
                               <c:set value="disabled" var="disabledStatus" />
                           </c:when>
                           <c:otherwise>
                               <c:set value="" var="disabledStatus" />
                           </c:otherwise>
                       </c:choose>--%>

                                            <c:if test="${disabledStatus ne 'disabled'}">
                                                <a onclick="addRow('finTable',$(this).parent().parent(),'Other Loans and advances')" linkindex="1"   href="#">
                                                    <img  src="images/add.gif" alt="add"/>
                                                </a>
                                                <%-- if condition --%>
                                                <c:if test="${actions ne 1}">
                                                    <a onclick="deleteRow('finTable',$(this).parent().parent())" linkindex="1" ${disabledStatus} href="#">
                                                        <img src="images/delete.gif" alt="delete"/>
                                                    </a>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>

                            </c:forEach>
                            <c:if test="${categoryL eq 0}">
                                <tr class="formarea_row1">
                                    <td  align="center">
                                        <label class="displayTextH">Other Loans and advances</label>
                                        <input type="hidden" class="otherCategory" name="category0" value="L"/>
                                        <input type="hidden" class="hiddenId"  value="0"/>
                                    </td>
                                    <td align="center" class="displayText">
                                        <c:choose>
                                            <c:when test="${finActionData.travelAdvance ne null}">
                                                <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                    <c:choose>
                                                        <c:when test="${finData.other eq index.count}">
                                                            <input type="radio" style="width:28px" id="
                                                                   " name="otherLoans0" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="radio" style="width:28px" id="otherLoans" name="otherLoans0" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                    <input type="radio" style="width:28px" id="otherLoans" name="otherLoans0" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td align="center" class="displayText">
                                        <input type="text" style="width:100px;" id="otherLoanAmt" name="otherLoanAmt0"  class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8"  onchange="sumOfAmount();" value="${finData.otherAmt}" />
                                    </td>
                                    <td align="center" class="displayText">
                                        <input type="text" style="width:280px;" id="otherLoanRemarks" name="otherLoanRemarks0" class="dueCommentsClass"  ${readonlyStatus} value="${finData.otherRemarks}" maxlength="150" />
                                    </td>
                                    <td id="actionItems" style="text-align:center">
                                        <a onclick="addRow('finTable',$(this).parent().parent(),'Other Loans and advances')" linkindex="1" href="#">
                                            <img src="images/add.gif" alt="add"/>
                                        </a>
                                        <%--<a onclick="deleteRow('finTable',$(this).parent().parent()" linkindex="1" href="#">
                                            <img src="images/icon_cancel.gif" alt="add"/>
                                        </a>--%>
                                    </td>
                                </c:if>
                            </tr>
                            <tr class="formarea_header">
                                <td colspan="5" align="center">
                                    <label class="headLabel">Other Recoveries</label>
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayTextH">Relocation benefits</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${finActionData.relocation ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finActionData.relocation eq index.count}">
                                                        <input type="radio" style="width:28px" id="relocation" name="relocation" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="relocation" name="relocation" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:28px" id="relocation" name="relocation" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="relocationAmt" name="relocationAmt"  onchange="sumOfAmount();"  class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8" value="${finActionData.relocationAmt}" />
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:280px;" id="relocationRemarks" name="relocationRemarks" class="dueCommentsClass"  ${readonlyStatus} value="${finActionData.relocationRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayTextH">Joining Bonus</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${finActionData.joiningBonus ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finActionData.joiningBonus eq index.count}">
                                                        <input type="radio" style="width:28px" id="joiningBonus" name="joiningBonus" class="dueClass required"  ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="joiningBonus" name="joiningBonus" class="dueClass required" ${disabledStatus}  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:28px" id="joiningBonus" name="joiningBonus" class="dueClass required" value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="joiningBonusAmt" name="joiningBonusAmt"  class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8" onchange="sumOfAmount();" value="${finActionData.joiningBonusAmt}" />
                                </td>
                                <td colspan="2" align="center" class="displayText">
                                    <input type="text" style="width:280px;" id="joiningBonusRemarks" name="joiningBonusRemarks"  class="dueCommentsClass"  ${readonlyStatus} value="${finActionData.joiningBonusRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayTextH">Notice Reimbursement</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${finActionData.noticeReimburse ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finActionData.noticeReimburse eq index.count}">
                                                        <input type="radio" style="width:28px" id="noticeReimburse" name="noticeReimburse" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="noticeReimburse" name="noticeReimburse" class="dueClass required" ${disabledStatus}  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:28px" id="noticeReimburse" name="noticeReimburse" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="noticeReimbAmt" name="noticeReimbAmt"  class="dueCommentsClass  amount required number" ${disabledStatus} maxlength="8" onchange="sumOfAmount();" value="${finActionData.noticeReimbAmt}" />
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:280px;" id="noticeReimburseRemarks" name="noticeReimburseRemarks"  class="dueCommentsClass"  ${readonlyStatus} value="${finActionData.noticeReimburseRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center">
                                    <label class="displayTextH">Professional Fee</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${finActionData.professionalFee ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finActionData.professionalFee eq index.count}">
                                                        <input type="radio" style="width:28px" id="professionalFee" name="professionalFee" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="professionalFee" name="professionalFee" class="dueClass required" ${disabledStatus} value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:28px" id="professionalFee" name="professionalFee" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="professionalfeeAmt" name="professionalfeeAmt"  class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8" onchange="sumOfAmount();" value="${finActionData.professionalfeeAmt}" />
                                </td>
                                <td colspan="2" align="center" class="displayText">
                                    <input type="text" style="width:280px;" id="professionalFeeRemarks" name="professionalFeeRemarks"  class="dueCommentsClass"  ${readonlyStatus} value="${finActionData.professionalFeeRemarks}" maxlength="150" />
                                </td>
                            </tr>
                            <tr class="formarea_row1">
                                <td align="center">
                                    <label class="displayTextH">Loans and Advances</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${finActionData.loans ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finActionData.loans eq index.count}">
                                                        <input type="radio" style="width:28px" id="loans" name="loans" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="loans" name="loans" ${disabledStatus} class="dueClass required"  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:28px" id="loans" name="loans" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="loansAmt" name="loansAmt" class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8" onchange="sumOfAmount();" value="${finActionData.loansAmt}" />
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:280px;" id="loansRemarks" name="loansRemarks"  class="dueCommentsClass"  ${readonlyStatus} value="${finActionData.loansRemarks}"maxlength="150"  />
                                </td>
                            </tr>
                            <tr class="formarea_row2">
                                <td align="center" >
                                    <label class="displayTextH">Outstanding Loans and official expenses</label>
                                </td>
                                <td align="center" class="displayText">
                                    <c:choose>
                                        <c:when test="${finActionData.officialExpenses ne null}">
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finActionData.officialExpenses eq index.count}">
                                                        <input type="radio" style="width:28px" id="officialExpenses" name="officialExpenses" class="dueClass required" ${disabledStatus}  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="officialExpenses" name="officialExpenses" class="dueClass required" ${disabledStatus}  value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <input type="radio" style="width:28px" id="officialExpenses" name="officialExpenses" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td align="center" class="displayText">
                                    <input type="text" style="width:100px;" id="officialExpAmt" name="officialExpAmt"  class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8" onchange="sumOfAmount();" value="${finActionData.officialExpAmt}" />
                                </td>
                                <td colspan="2" align="center" class="displayText">
                                    <input type="text" style="width:280px;" id="officialExpensesRemarks" name="officialExpensesRemarks"  class="dueCommentsClass"  ${readonlyStatus} value="${finActionData.officialExpensesRemarks}" maxlength="150"  />
                                </td>
                            </tr>
                            <c:set var="categoryR" value="0" />
                            <c:set var="actionso" value="0" />
                            <c:forEach items="${finMultipleData}" var="finData">

                                <c:if test="${finData.otherCategory eq 'R'}">
                                    <c:set var="actionso" value="${actionso+1}" />
                                    <c:set var="categoryR" value="1" />
                                    <tr class="formarea_row1">
                                        <td align="center">
                                            <label class="displayTextH">Any Others (Please specify)</label>
                                            <input type="hidden" class="otherCategory" name="category${finData.otherId}" value="${finData.otherCategory}"/>
                                            <input type="hidden" class="hiddenId"  value="${finData.otherId}"/>
                                        </td>
                                        <td align="center" class="displayText">
                                            <%--   <c:choose> --%>
                                            <%-- <c:when test="${finActionData.other ne null}">--%>

                                            <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                <c:choose>
                                                    <c:when test="${finData.other eq index.count}">
                                                        <input type="radio" style="width:28px" id="other" name="otherLoans${finData.otherId}" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input type="radio" style="width:28px" id="other" name="otherLoans${finData.otherId}" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>
                                            <%--</c:when>--%>
                                            <%-- <c:otherwise>
                                                 <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                     <input type="radio" style="width:28px" id="other" name="otherLoans${finData.otherId}" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                                 </c:forEach>
                                             </c:otherwise>
                                         </c:choose>--%>
                                        </td>
                                        <td align="center" class="displayText">
                                            <input type="text" style="width:100px;" id="otherAmt" name="otherLoanAmt${finData.otherId}"  class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8" onchange="sumOfAmount();"  value="${finData.otherAmt}" />
                                        </td>
                                        <td align="center" class="displayText">
                                            <input type="text" style="width:280px;" id="otherRemarks" name="otherLoanRemarks${finData.otherId}"  class="dueCommentsClass"  ${readonlyStatus} value="${finData.otherRemarks}" maxlength="150" />
                                        </td>
                                        <td id="actionItems" style="text-align:center">
                                            <c:if test="${disabledStatus ne 'disabled'}">

                                                <a onclick="addRow('finTable',$(this).parent().parent(),'Any Others (Please specify)')" linkindex="1" href="#" >
                                                    <img src="images/add.gif" alt="add"/>
                                                </a>

                                                <%--${actionso}--%>
                                                <c:if test="${actionso ne 1}">
                                                    <a onclick="deleteRow('finTable',$(this).parent().parent())" linkindex="1" href="#">
                                                        <img src="images/delete.gif" alt="delete"/>
                                                    </a>
                                                </c:if>
                                            </c:if>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${categoryR eq 0}">
                                <tr class="formarea_row1">
                                    <td  align="center">
                                        <label class="displayTextH">Any Others (Please specify)</label>
                                        <%--<input type="hidden" class="otherCategory" name="category1" value="r"/>--%>
                                        <input type="hidden" class="otherCategory" name="category1" value="R"/>
                                        <input type="hidden" class="hiddenId"  value="1"/>
                                    </td>
                                    <td align="center" class="displayText">
                                        <c:choose>
                                            <c:when test="${finActionData.travelAdvance ne null}">
                                                <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                    <c:choose>
                                                        <c:when test="${finData.other eq index.count}">
                                                            <input type="radio" style="width:28px" id="otherLoans" name="otherLoans1" ${disabledStatus} class="dueClass required"  value="${index.count}" checked/>${surrenderValues}
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="radio" style="width:28px" id="otherLoans" name="otherLoans1" ${disabledStatus} class="dueClass required" value="${index.count}"/>${surrenderValues}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </c:when>
                                            <c:otherwise>
                                                <c:forEach items="${surrenderList}" var="surrenderValues" varStatus="index">
                                                    <input type="radio" style="width:28px" id="otherLoans" name="otherLoans1" class="dueClass required"  value="${index.count}" />${surrenderValues}
                                                </c:forEach>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td align="center" class="displayText">
                                        <input type="text" style="width:100px;" id="otherLoanAmt"  name="otherLoanAmt1" class="dueCommentsClass amount required number" ${disabledStatus} maxlength="8" onchange="sumOfAmount();" value="${finData.otherAmt}" />
                                    </td>
                                    <td align="center" class="displayText">
                                        <input type="text" style="width:280px;" id="otherLoanRemarks" name="otherLoanRemarks1"  class="dueCommentsClass"  ${readonlyStatus} value="${finData.otherRemarks}" maxlength="150"  />
                                    </td>
                                    <td id="actionItems" style="text-align:center">
                                        <a onclick="addRow('finTable',$(this).parent().parent(),'Other Loans and advances')" linkindex="1" href="#">
                                            <img src="images/add.gif" alt="add"/>
                                        </a>
                                        <%--   <a onclick="deleteRow('finTable',$(this).parent().parent()" linkindex="1" href="#">
                                               <img src="images/icon_cancel.gif" alt="add"/>
                                           </a>--%>
                                    </td>
                                </c:if>
                            </tr>
                            <tr>

                            </tr>
                            <tr class="formarea_row2">
                                <td>

                                </td>
                                <td  align="center" class="displayTextH" width="49%" >
                                    <label class="headLabel">Total Amount</label>
                                </td>

                                <td colspan="3" align="center" class="displayTextH">
                                    <input type="text" style="width:100px;" id="totalAmount" name="totalAmount"  class="dueClass" ${disabledStatus} value="${finActionData.totalAmount}" readonly/>

                                </td></tr>
                            <tr class="formarea_row1">
                                <td>
                                </td>
                                <td  width="49%" align="center" style="text-align: center;" colspan="3">
                                    <label class="headLabel">Over All Comments</label>
                                    <textarea cols="20" rows="1" id="finOverAllComments" name="finOverAllComments"  maxlength="250"  minlength="0" onblur="textLimit(this,250);" onkeyup="textLimit(this,250)" class="formTextArea required  minlength maxlength resizableTextArea ui-resizable" ${disabledStatus} >${finActionData.finOverAllComments}</textarea>
                                </td>
                            </tr>
                            </tbody>
                    </table>
                                <table class="buttonAlignment" align="center">
                            <c:if test="${menuDetails.fin_approvalId==moduleId}">
                                <tr>
                                    <td width="49%" align="center" style="text-align: center;" colspan="5">
                                        <input type="hidden" name="finApprovalId" id="finApprovalId" value="${finActionData.finApprovalId}" readonly />
                                        <input type="hidden" name="moduleId" id="moduleId" value="${moduleId}" readonly />
                                        <input type="hidden" id="exitEmpId" name="exitEmpId" value="${exitEmpInfo.exitEmpId}" readonly>
                                        <input type="hidden" name="resEmpId" id="resEmpId" value="${exitEmpInfo.resEmpId}" readonly />
                                        <input type="submit" name="buttonName" id="saveBtn" value="Save" class="savebutton" ${disabledStatus} onclick="return disableSubmit('saveBtn','submitBtn','btnCancel','Save');" />
                                        <input type="submit" name="buttonName" id="submitBtn" value="Submit" class="submitbutton" ${disabledStatus} onclick="return disableSubmit('saveBtn','submitBtn','btnCancel','Submit');" />
                                        <input type="button" name="buttonName" id="btnCancel" value="Back" class="backbutton" onclick="reject()">
                                    </td>
                                </tr>
                            </c:if>
                    </table>
                </div>
            </div>
            <input  type="hidden"  value="0,1" id="newRow" name="newRow" />
            <input  type="hidden"  value="" id="newRow1" name="newRow1" />
        </form>
    </body>
</html>
