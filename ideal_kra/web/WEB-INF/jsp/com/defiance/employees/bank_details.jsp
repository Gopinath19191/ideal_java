<%--
    Document   : bank_details
    Created on : Mar 13, 2012, 12:12:20 PM
    Author     : 15065
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/com/defiance/employees/common.jsp" %>
        <div class="contentwrap" style="height:auto;" >
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <table width="100%" border="0">
                <tbody>
                    <tr>
                        <td>
                            <form action="bank_detailsSubmit.htm" name="bank_detailsForm" id="bank_detailsForm" method="post" onSubmit="return validateBankDetails();">
                                <input type="hidden" id="actionName" name="actionName" value="${actionName}" />
                                <c:choose>
                                    <c:when test="${!empty(result)}">
                                        <input type="hidden" name="recordCount" id="recordCount" value="${fn:length(result)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="recordCount" id="recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table class="tableStructure" border="0">
                                    <tr class="headerCenter">
                                        <c:choose>
                                            <c:when test="${accessType == 'HR'}">
                                                <th width="20%">Employee Name</th>
                                                <th width="15%">Account Holder Name</th>
                                                <th width="12%">Account Number</th>
                                                <th width="10%">Bank Name</th>
                                                <th width="11%">Bank Branch</th>
                                                <th width="10%">Country</th>
                                                <th width="10%">Region</th>
                                                <th width="10%">City</th>
                                                <th width="12%">Bank Code</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th width="15%"><font color="red">*</font>Account Holder Name</th>
                                                <th width="15%"><font color="red">*</font>Account Number</th>
                                                <th width="10%"><font color="red">*</font>Bank Name</th>
                                                <th width="11%"><font color="red">*</font>Bank Branch</th>
                                                <th width="11%"><font color="red">*</font>Country</th>
                                                <th width="11%">Region</th>
                                                <th width="11%">City</th>
                                                <th width="11%"><font color="red">*</font>Bank Code</th>
                                                <th width="4%">Action</th>
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
                                                        ${data.account_nameX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.account_numberX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.bank_nameXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.branch_nameX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.country_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.region_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.city_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.bank_codeX}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr>
                                                    <td colspan="9" align="center">
                                                        <font color="red">No Records Found.Search it by Employee Name / Employee Id to find records</font>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr id="TR_${dataStatus.count}">
                                                    <input type="hidden" name="bankId_${dataStatus.count}" id="bankId_${dataStatus.count}" value="${data.bankIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="account_name_${dataStatus.count}" name="account_name_${dataStatus.count}" value="${data.account_nameX}" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="account_name_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="account_number_${dataStatus.count}" id="account_number_${dataStatus.count}" style="width:95%" value="${data.account_numberX}" maxlength="15" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,2,false);" onChange="checkUnique(this.value,'account','bankId_${dataStatus.count}','Account');" />
                                                        <span style="color:red;display:none;" id="account_number_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select style="width:95%" id="bank_name_${dataStatus.count}" name="bank_name_${dataStatus.count}" >
                                                            <option value="">-- Bank --</option>
                                                            <c:forEach items="${banks}" var="res">
                                                                <c:choose>
                                                                    <c:when test="${res.bankId == data.bank_nameX}">
                                                                        <option selected value="${res.bankId}">${res.bankName}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${res.bankId}">${res.bankName}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="bank_name_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="branch_name_${dataStatus.count}" name="branch_name_${dataStatus.count}" value="${data.branch_nameX}" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="branch_name_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select style="width:95%" id="country_id_${dataStatus.count}" name="country_id_${dataStatus.count}" onChange="loadState(this.value,'region',${dataStatus.count})">
                                                            <option value="">-- Country --</option>
                                                            <c:forEach items="${countries}" var="res">
                                                                <c:choose>
                                                                    <c:when test="${res.countryId == data.country_idX}">
                                                                        <option selected value="${res.countryId}">${res.countryName}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${res.countryId}">${res.countryName}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="country_id_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="${data.region_idX}" id="selected_region_id_${dataStatus.count}" />
                                                        <select style="width:95%" id="region_id_${dataStatus.count}" name="region_id_${dataStatus.count}" onChange="loadState(this.value,'city',${dataStatus.count})">
                                                            <option value="">-- Region --</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="region_id_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="${data.city_idX}" id="selected_city_id_${dataStatus.count}" />
                                                        <select style="width:95%" id="city_id_${dataStatus.count}" name="city_id_${dataStatus.count}" >
                                                            <option value="">-- City --</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="city_id_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="bank_code_${dataStatus.count}" name="bank_code_${dataStatus.count}" value="${data.bank_codeX}" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="bank_code_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_BankDetails(${dataStatus.count});" />
                                                        <c:if test="${dataStatus.count !=1}">
                                                            <img alt="Remove" src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteRow(${dataStatus.count},1)" />
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <input type="hidden" name="bankId_1" id="bankId_1" value="" />
                                                    <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="account_name_1" name="account_name_1" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="account_name_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="account_number_1" id="account_number_1" style="width:95%" maxlength="15" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,2,false);" onChange="checkUnique(this.value,'account','bankId_1','Account');" />
                                                        <span style="color:red;display:none;" id="account_number_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select style="width:95%" id="bank_name_1" name="bank_name_1" >
                                                            <option value="">-- Bank --</option>
                                                            <c:forEach items="${banks}" var="res">
                                                                <option value="${res.bankId}">${res.bankName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="bank_name_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="branch_name_1" name="branch_name_1" value="" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="branch_name_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select style="width:95%" id="country_id_1" name="country_id_1" onChange="loadState(this.value,'region',1)">
                                                            <option value="">-- Country --</option>
                                                            <c:forEach items="${countries}" var="res">
                                                                <option value="${res.countryId}">${res.countryName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="country_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="" id="selected_region_id_1" />
                                                        <select style="width:95%" id="region_id_1" name="region_id_1" onChange="loadState(this.value,'city',1)">
                                                            <option value="">-- Region --</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="region_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="" id="selected_city_id_1" />
                                                        <select style="width:95%" id="city_id_1" name="city_id_1" >
                                                            <option value="">-- City --</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="city_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="bank_code_1" name="bank_code_1" value="" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="bank_code_error_1">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_BankDetails(1);" />
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
<script type="text/javascript">
    $(document).ready(function(){
        for(i=1;i<=$('#recordCount').val();i++) {
            loadState($("#country_id_"+i).val(),'region',i);
            loadState($("#selected_region_id_"+i).val(),'city',i);
        }
    });
</script>

