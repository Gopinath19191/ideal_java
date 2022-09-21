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
                            <form action="emergency_contactsSubmit.htm" name="emergency_contactsForm" id="emergency_contactsForm" method="post" onSubmit="return validateEmergencyContacts();">
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
                                                <th width="22%">Employee Name</th>
                                                <th width="18%">Contact Name</th>
                                                <th width="15%">Relationship</th>
                                                <th width="15%">Home Phone Number</th>
                                                <th width="15%">Mobile Number</th>
                                                <th width="15%">Work Phone Number</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th width="20%"><font color="red">*</font>Name</th>
                                                <th width="19%"><font color="red">*</font>Relationship</th>
                                                <th width="19%">Home Phone Number</th>
                                                <th width="19%"><font color="red">*</font>Mobile Number</th>
                                                <th width="19%">Work Phone Number</th>
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
                                                        ${data.nameX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.relationshipX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.home_phone_numberX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.mobile_numberX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.work_phone_numberX}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr>
                                                    <td colspan="6" align="center">
                                                        <font color="red">No Records Found.Search it by Employee Name / Employee Id to find records</font>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr id="TR_${dataStatus.count}">
                                                    <input type="hidden" name="emergencyId_${dataStatus.count}" id="emergencyId_${dataStatus.count}" value="${data.emergencyIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="25" id="name_${dataStatus.count}" name="name_${dataStatus.count}" value="${data.nameX}" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="name_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="25" name="relationship_${dataStatus.count}" id="relationship_${dataStatus.count}" style="width:95%" value="${data.relationshipX}" maxlength="100" />
                                                        <span style="color:red;display:none;" id="relationship_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="15" id="home_phone_number_${dataStatus.count}" name="home_phone_number_${dataStatus.count}" value="${data.home_phone_numberX}" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="home_phone_number_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="15" id="mobile_number_${dataStatus.count}" name="mobile_number_${dataStatus.count}" value="${data.mobile_numberX}" style="width:95%" maxlength="10" />
                                                        <span style="color:red;display:none;" id="mobile_number_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="15" id="work_phone_number_${dataStatus.count}" name="work_phone_number_${dataStatus.count}" value="${data.work_phone_numberX}" style="width:95%" maxlength="10" />
                                                        <span style="color:red;display:none;" id="work_phone_number_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_EmergencyContacts(${dataStatus.count});" />
                                                        <c:if test="${dataStatus.count !=1}">
                                                            <img alt="Remove" src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteRow(${dataStatus.count},1)" />
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <input type="hidden" name="emergencyId_1" id="emergencyId_1" value="" />
                                                    <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="25" id="name_1" name="name_1" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="name_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="25" name="relationship_1" id="relationship_1" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="relationship_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="15" id="home_phone_number_1" name="home_phone_number_1" value="" style="width:95%" maxlength="15" />
                                                        <span style="color:red;display:none;" id="home_phone_number_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="15" id="mobile_number_1" name="mobile_number_1" value="" style="width:95%" maxlength="10" />
                                                        <span style="color:red;display:none;" id="mobile_number_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" maxlength="15" id="work_phone_number_1" name="work_phone_number_1" value="" style="width:95%" maxlength="10" />
                                                        <span style="color:red;display:none;" id="work_phone_number_error_1">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_EmergencyContacts(1);" />
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

