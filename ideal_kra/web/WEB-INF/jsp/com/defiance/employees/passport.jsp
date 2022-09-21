<%--
    Document   : passport
    Created on : Mar 13, 2012, 12:12:20 PM
    Author     : 15065
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script type="text/javascript">
            $(function() {
                $(".datePick").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat:'dd-mm-yy',
                    maxDate : new Date(),
                    yearRange: '1950:'+(new Date).getFullYear(),
                    currentText: 'Now'
                });
                $(".datePickMax").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat:'dd-mm-yy',
                    minDate : new Date()
                });
           });
        </script>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/com/defiance/employees/common.jsp" %>
        <div class="contentwrap" style="height:auto;" >
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <table width="100%" border="0">
                <tbody>
                    <tr>
                        <td>
                            <form action="passportSubmit.htm" name="passportForm" id="passportForm" method="post" onSubmit="return validatePassport();">
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
                                                <th width="15%">Passport Number</th>
                                                <th width="10%">Issue Date</th>
                                                <th width="10%">Expiry Date</th>
                                                <th width="15%">Issued At</th>
                                                <th width="10%">Status</th>
                                                <th width="20%">Remarks</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th width="20%"><font color="red">*</font>Passport Number</th>
                                                <th width="15%"><font color="red">*</font>Issue Date</th>
                                                <th width="15%"><font color="red">*</font>Expiry Date</th>
                                                <th width="20%"><font color="red">*</font>Issued At</th>
                                                <th width="15%">Status</th>
                                                <th width="20%">Remarks</th>
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
                                                        ${data.passport_numberX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.passport_date_issueX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.passport_date_expireX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.passport_place_issueX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:if test="${data.passport_statusX == 'a'}">
                                                            Active
                                                        </c:if>
                                                        <c:if test="${data.passport_statusX == 'i'}">
                                                            Inactive
                                                        </c:if>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <div title="${data.remarksX}">${fn:substring(data.remarksX,0,60)}</div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr>
                                                    <td colspan="7" align="center">
                                                        <font color="red">No Records Found</font>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr id="TR_${dataStatus.count}">
                                                    <input type="hidden" name="passportId_${dataStatus.count}" id="passportId_${dataStatus.count}" value="${data.passportIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="passport_number_${dataStatus.count}" id="passport_number_${dataStatus.count}" value="${data.passport_numberX}" style="width:95%" maxlength="15" onChange="checkUnique(this.value,'passport','passportId_${dataStatus.count}','Passport');" />
                                                        <span style="color:red;display:none;" id="passport_number_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePick" id="passport_date_issue_${dataStatus.count}" name="passport_date_issue_${dataStatus.count}" value="${data.passport_date_issueX}" style="width:95%"  readonly />
                                                        <span style="color:red;display:none;" id="passport_date_issue_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePickMax" name="passport_date_expire_${dataStatus.count}" id="passport_date_expire_${dataStatus.count}" value="${data.passport_date_expireX}" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="passport_date_expire_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="passport_place_issue_${dataStatus.count}" name="passport_place_issue_${dataStatus.count}" value="${data.passport_place_issueX}" style="width:95%" maxlength="25" />
                                                        <span style="color:red;display:none;" id="passport_place_issue_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="passport_status_${dataStatus.count}" name="passport_status_${dataStatus.count}" style="width:95%">
                                                            <option value="a" ${data.passport_statusX == "a"?'selected':''}>Active</option>
                                                            <option value="i" ${data.passport_statusX == "i"?'selected':''}>Inactive</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="passport_status_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks_${dataStatus.count}" name="remarks_${dataStatus.count}" value="${data.remarksX}" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Passport(${dataStatus.count});" />
                                                        <c:if test="${dataStatus.count !=1}">
                                                            <img alt="Remove" src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteRow(${dataStatus.count},1)" />
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <input type="hidden" name="passportId_1" id="passportId_1" value="" />
                                                    <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="passport_number_1" id="passport_number_1" style="width:95%" maxlength="15" onChange="checkUnique(this.value,'passport','passportId_1','Passport');" />
                                                        <span style="color:red;display:none;" id="passport_number_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePick"  id="passport_date_issue_1" name="passport_date_issue_1" value="" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="passport_date_issue_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePickMax"  name="passport_date_expire_1" id="passport_date_expire_1" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="passport_date_expire_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text"  id="passport_place_issue_1" name="passport_place_issue_1" value="" style="width:95%" maxlength="25" />
                                                        <span style="color:red;display:none;" id="passport_place_issue_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="passport_status_1" name="passport_status_1" style="width:95%">
                                                            <option value="a">Active</option>
                                                            <option value="i">Inactive</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="passport_status_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks_1" name="remarks_1" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error_1">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Passport(1);" />
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

