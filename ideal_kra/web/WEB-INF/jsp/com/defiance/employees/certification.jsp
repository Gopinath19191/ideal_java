<%--
    Document   : certification
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
        <div class="contentwrap">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <table width="100%" border="0">
                <tbody>
                    <tr>
                        <td>
                            <form action="certificationSubmit.htm" name="certificationForm" id="certificationForm" method="post" onSubmit="return validateCertificate();">
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
                                                <th width="20%">Certification Name</th>
                                                <th width="10%">Date of passing</th>
                                                <th width="20%">Institution</th>
                                                <th width="10%">Percentage / CGPA</th>
                                                <th width="20%">Remarks</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th width="20%"><font color="red">*</font>Certification Name</th>
                                                <th width="15%"><font color="red">*</font>Year / Month Of Passing <br/>(eg : March 2005)</th>
                                                <th width="25%"><font color="red">*</font>Institution</th>
                                                <th width="14%"><font color="red">*</font>Percentage / CGPA</th>
                                                <th width="22%">Remarks</th>
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
                                                        ${data.qualificationX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.year_of_passingX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.institutionX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.percentageX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <div title="${data.remarksX}">${fn:substring(data.remarksX,0,60)}</div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr>
                                                    <td colspan="5" align="center">
                                                        <font color="red">No Records Found.Search it by Employee Name / Employee Id to find records</font>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr id="TR_${dataStatus.count}">
                                                    <input type="hidden" name="certificationId_${dataStatus.count}" id="certificationId_${dataStatus.count}" value="${data.certificationIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="qualification_${dataStatus.count}" id="qualification_${dataStatus.count}" value="${data.qualificationX}" style="width:95%" maxlength="50" />
                                                        <span style="color:red;display:none;" id="qualification_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="year_of_passing_${dataStatus.count}" name="year_of_passing_${dataStatus.count}" value="${data.year_of_passingX}" style="width:95%" maxlength="15"/>
                                                        <span style="color:red;display:none;" id="year_of_passing_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="institution_${dataStatus.count}" id="institution_${dataStatus.count}" value="${data.institutionX}" style="width:95%" maxlength="100"/>
                                                        <span style="color:red;display:none;" id="institution_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text"  id="percentage_${dataStatus.count}" name="percentage_${dataStatus.count}" value="${data.percentageX}" style="width:95%" maxlength="5" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" />
                                                        <span style="color:red;display:none;" id="percentage_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks_${dataStatus.count}" name="remarks_${dataStatus.count}" value="${data.remarksX}" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Certification(${dataStatus.count});" />
                                                        <c:if test="${dataStatus.count !=1}">
                                                            <img alt="Remove" src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteRow(${dataStatus.count},1)" />
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <input type="hidden" name="certificationId_1" id="certificationId_1" value="" />
                                                    <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="qualification_1" id="qualification_1" style="width:95%" maxlength="50" />
                                                        <span style="color:red;display:none;" id="qualification_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="year_of_passing_1" name="year_of_passing_1" value="" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="year_of_passing_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="institution_1" id="institution_1" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="institution_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text"  id="percentage_1" name="percentage_1" value="" style="width:95%" maxlength="5" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" />
                                                        <span style="color:red;display:none;" id="percentage_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="50" id="remarks_1" name="remarks_1" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Certification(1);" />
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

