<%--
    Document   : visa
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
                            <form action="visaSubmit.htm" name="visaForm" id="visaForm" method="post" onSubmit="return validateVisa();">
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
                                                <th width="10%">Reference Number</th>
                                                <th width="9%">Country</th>
                                                <th width="10%">Visa Type</th>
                                                <th width="9%">Issued Date</th>
                                                <th width="9%">Expiry Date</th>
                                                <th width="9%">Visits</th>
                                                <th width="9%">Place of Issue</th>
                                                <th width="15%">Remarks</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th width="12%"><font color="red">*</font>Reference Number</th>
                                                <th width="12%"><font color="red">*</font>Country</th>
                                                <th width="10%"><font color="red">*</font>Visa Type</th>
                                                <th width="10%"><font color="red">*</font>Issued Date</th>
                                                <th width="10%"><font color="red">*</font>Expiry Date</th>
                                                <th width="10%"><font color="red">*</font>Visits</th>
                                                <th width="12%"><font color="red">*</font>Place of Issue</th>
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
                                                        ${data.reference_numberX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.country_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.visa_typeXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.date_of_issueX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.date_of_expiryX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:if test="${data.visa_visitX == 0}">
                                                            Single
                                                        </c:if>
                                                        <c:if test="${data.visa_visitX == 1}">
                                                            Multiple
                                                        </c:if>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.place_of_issueX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <div title="${data.remarksX}">${fn:substring(data.remarksX,0,60)}</div>
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
                                                    <input type="hidden" name="visaId_${dataStatus.count}" id="visaId_${dataStatus.count}" value="${data.visaIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="reference_number_${dataStatus.count}" id="reference_number_${dataStatus.count}" value="${data.reference_numberX}" style="width:95%" maxlength="50" onChange="checkUnique(this.value,'visa','visaId_${dataStatus.count}','Reference');" />
                                                        <span style="color:red;display:none;" id="reference_number_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="country_id_${dataStatus.count}" name="country_id_${dataStatus.count}" style="width:95%" >
                                                            <option value="" >--Select--</option>
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
                                                        <select name="visa_type_${dataStatus.count}" id="visa_type_${dataStatus.count}" style="width:95%" >
                                                            <option value="" >--Select--</option>
                                                            <c:forEach items="${visaTypes}" var="res">
                                                                 <c:choose>
                                                                    <c:when test="${res.visaId == data.visa_typeX}">
                                                                        <option selected value="${res.visaId}">${res.visaName}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${res.visaId}">${res.visaName}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="visa_type_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePick" id="date_of_issue_${dataStatus.count}" name="date_of_issue_${dataStatus.count}" value="${data.date_of_issueX}" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="date_of_issue_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePickMax" id="date_of_expiry_${dataStatus.count}" name="date_of_expiry_${dataStatus.count}" value="${data.date_of_expiryX}" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="date_of_expiry_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="visa_visit_${dataStatus.count}" name="visa_visit_${dataStatus.count}" style="width:95%">
                                                            <option value="" >--Select--</option>
                                                            <option ${data.visa_visitX == 0?'selected':''} value="0">Single</option>
                                                            <option ${data.visa_visitX == 1?'selected':''} value="1">Multiple</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="visa_visit_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="place_of_issue_${dataStatus.count}" name="place_of_issue_${dataStatus.count}" value="${data.place_of_issueX}" style="width:95%" maxlength="50" />
                                                        <span style="color:red;display:none;" id="place_of_issue_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks_${dataStatus.count}" name="remarks_${dataStatus.count}" value="${data.remarksX}" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Visa(${dataStatus.count});" />
                                                        <c:if test="${dataStatus.count !=1}">
                                                            <img alt="Remove" src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteRow(${dataStatus.count},1)" />
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <input type="hidden" name="visaId_1" id="visaId_1" value="" />
                                                    <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="reference_number_1" id="reference_number_1" style="width:95%" maxlength="50" onChange="checkUnique(this.value,'visa','visaId_1','Reference');" />
                                                        <span style="color:red;display:none;" id="reference_number_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="country_id_1" name="country_id_1" style="width:95%" >
                                                            <option value="" >--Select--</option>
                                                            <c:forEach items="${countries}" var="res">
                                                                <option value="${res.countryId}">${res.countryName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="country_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select name="visa_type_1" id="visa_type_1" style="width:95%" >
                                                            <option value="" >--Select--</option>
                                                            <c:forEach items="${visaTypes}" var="res">
                                                                <option value="${res.visaId}">${res.visaName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="visa_type_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text"  class="datePick" id="date_of_issue_1" name="date_of_issue_1" value="" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="date_of_issue_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text"  class="datePickMax" id="date_of_expiry_1" name="date_of_expiry_1" value="" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="date_of_expiry_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="visa_visit_1" name="visa_visit_1" style="width:95%" >
                                                            <option value="" >--Select--</option>
                                                            <option value="0">Single</option>
                                                            <option value="1">Multiple</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="visa_visit_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="place_of_issue_1" name="place_of_issue_1" value="" style="width:95%" maxlength="50" />
                                                        <span style="color:red;display:none;" id="place_of_issue_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks_1" name="remarks_1" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error_1">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Visa(1);" />
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

