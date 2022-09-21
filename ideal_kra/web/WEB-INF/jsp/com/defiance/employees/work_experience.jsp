<%--
    Document   : work_experience
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
           });
        </script>
    </head>
    <body>
        <%@include file="/WEB-INF/jsp/com/defiance/employees/common.jsp" %>
        <div class="contentwrap">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <table width="100%" border="0">
                <tbody>
                    <tr>
                        <td>
                            <form action="work_experienceSubmit.htm" name="work_experienceForm" id="work_experienceForm" method="post" onSubmit="return validateExperience();">
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
                                                <th width="14%">Employer</th>
                                                <th width="18%">Address</th>
                                                <th width="12%">Job Title</th>
                                                <th width="8%">Date of Joining</th>
                                                <th width="8%">Date of Relieving</th>
                                                <th width="20%">Remarks</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th width="15%"><font color="red">*</font>Employer</th>
                                                <th width="21%"><font color="red">*</font>Address</th>
                                                <th width="20%"><font color="red">*</font>Job Title</th>
                                                <th width="10%"><font color="red">*</font>Date of Joining</th>
                                                <th width="10%"><font color="red">*</font>Date of Relieving</th>
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
                                                        ${data.company_nameX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.company_addressX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.job_titleX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.start_dateX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.end_dateX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <div title="${data.remarksX}">${fn:substring(data.remarksX,0,60)}</div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr>
                                                    <td colspan="7" align="center">
                                                        <font color="red">No Records Found.Search it by Employee Name / Employee Id to find records</font>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr id="TR_${dataStatus.count}">
                                                    <input type="hidden" name="experienceId_${dataStatus.count}" id="experienceId_${dataStatus.count}" value="${data.experienceIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="company_name_${dataStatus.count}" id="company_name_${dataStatus.count}" value="${data.company_nameX}" style="width:95%" maxlength="50" />
                                                        <span style="color:red;display:none;" id="company_name_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="company_address_${dataStatus.count}" name="company_address_${dataStatus.count}" value="${data.company_addressX}" style="width:95%" maxlength="100"/>
                                                        <span style="color:red;display:none;" id="company_address_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="job_title_${dataStatus.count}" id="job_title_${dataStatus.count}" value="${data.job_titleX}" style="width:95%" maxlength="100"/>
                                                        <span style="color:red;display:none;" id="job_title_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePick" id="start_date_${dataStatus.count}" name="start_date_${dataStatus.count}" value="${data.start_dateX}" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="start_date_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                         <c:choose>
                                                            <c:when test="${data.end_dateX == null || data.end_dateX == '' }">
                                                                <input type="text"  id="end_date_${dataStatus.count}" name="" value="Till Date" style="width:95%" readonly />
                                                                <div style="display:none;"><input type="text"  id="end_date_${dataStatus.count}" name="end_date_${dataStatus.count}" value="" style="width:95%" readonly /></div>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="text" class="datePick" id="end_date_${dataStatus.count}" name="end_date_${dataStatus.count}" value="${data.end_dateX}" style="width:95%" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <span style="color:red;display:none;" id="end_date_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks_${dataStatus.count}" name="remarks_${dataStatus.count}" value="${data.remarksX}" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Experience(${dataStatus.count});" />
                                                        <c:if test="${dataStatus.count !=1}">
                                                            <img alt="Remove" src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteRow(${dataStatus.count},1)" />
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <input type="hidden" name="experienceId_1" id="experienceId_1" value="" />
                                                    <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="company_name_1" id="company_name_1" style="width:95%" maxlength="50" />
                                                        <span style="color:red;display:none;" id="company_name_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="company_address_1" name="company_address_1" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="company_address_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="job_title_1" id="job_title_1" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="job_title_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text"  class="datePick" id="start_date_1" name="start_date_1" value="" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="start_date_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text"  class="datePick" id="end_date_1" name="end_date_1" value="" style="width:95%" />
                                                        <span style="color:red;display:none;" id="end_date_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks_1" name="remarks_1" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error_1">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Experience(1);" />
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

