<%--
    Document   : skills
    Created on : Mar 13, 2012, 12:12:20 PM
    Author     : 15065
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript">
            $(function() {
                $(".datePick").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat:'dd-mm-yy'
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
                            <form action="skillSubmit.htm" name="skillsForm" id="skillsForm" method="post" onSubmit="return validateSkills();">
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
                                                <th width="10%">Category</th>
                                                <th width="15%">Stream</th>
                                                <th width="15%">Skill</th>
                                                <th width="10%">Skill Type</th>
                                                <th width="15%">Experience</th>
                                                <th width="15%">Last Used</th>
                                            </c:when>
                                            <c:otherwise>
                                                <th width="14%"><font color="red">*</font>Category</th>
                                                <th width="14%"><font color="red">*</font>Stream</th>
                                                <th width="14%"><font color="red">*</font>Skill</th>
                                                <th width="14%"><font color="red">*</font>Skill Type</th>
                                                <th width="20%"><font color="red">*</font>Experience</th>
                                                <th width="20%">Last Used</th>
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
                                                        <c:if test="${data.skill_categoryX == 'T'}">
                                                            Technical
                                                        </c:if>
                                                        <c:if test="${data.skill_categoryX == 'N'}">
                                                            Non Technical
                                                        </c:if>
                                                        <c:if test="${data.skill_categoryX == 'F'}">
                                                            Functional
                                                        </c:if>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.stream_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.skill_idXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:if test="${data.skill_typeX == 'P'}">
                                                            Primary
                                                        </c:if>
                                                        <c:if test="${data.skill_typeX == 'S'}">
                                                            Secondary
                                                        </c:if>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:if test="${data.years_workedX !=0 && data.years_workedX != null}">
                                                            ${data.years_workedX} year(s)
                                                        </c:if>
                                                        <c:if test="${data.months_workedX !=0 && data.months_workedX != null}">
                                                            ${data.months_workedX} month(s)
                                                        </c:if>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:if test="${data.last_used_yearX !=0 && data.last_used_yearX != null}">
                                                            ${data.last_used_yearX} year(s)
                                                        </c:if>
                                                        <c:if test="${data.last_used_monthX !=0 && data.last_used_monthX != null}">
                                                            ${data.last_used_monthX} month(s)
                                                        </c:if>
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
                                            <div style="display:none">
                                                <select style="width:95%" id="streamFirst" name="streamFirst">
                                                    <option value="">-- Select Stream --</option>
                                                    <c:forEach items="${streams}" var="item">
                                                        <c:choose>
                                                            <c:when test="${item.streamId == data.stream_idX}">
                                                                <option selected value="${item.streamId}" >${item.streamName}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${item.streamId}" >${item.streamName}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr id="TR_${dataStatus.count}">
                                                    <input type="hidden" name="skillId_${dataStatus.count}" id="skillId_${dataStatus.count}" value="${data.skillIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td valign ="top" align="center">
                                                        <select id="skill_category_${dataStatus.count}" name="skill_category_${dataStatus.count}" style="width:95%;">
                                                            <option value="" >--Select Category--</option>
                                                            <option ${data.skill_categoryX == 'T'?'selected':''} value="T">Technical</option>
                                                            <option ${data.skill_categoryX == 'N'?'selected':''} value="N">Non-Technical</option>
                                                            <option ${data.skill_categoryX == 'F'?'selected':''} value="F">Functional</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="skill_category_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="${data.stream_idX}" id="streamSelected_${dataStatus.count}" />
                                                        <select id="stream_id_${dataStatus.count}" name="stream_id_${dataStatus.count}" style="width:95%" onChange="loadStream(this.value,${dataStatus.count})">
                                                            <option value="" >--Select Stream--</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="stream_id_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="${data.skill_idX}" id="selected_skill_${dataStatus.count}"/>
                                                        <select name="skill_id_${dataStatus.count}" id="skill_id_${dataStatus.count}" style="width:95%">
                                                            <option value="" >--Select Skill--</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="skill_id_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select name="skill_type_${dataStatus.count}" id="skill_type_${dataStatus.count}" style="width:95%">
                                                            <option value="" >--Select Skill Type--</option>
                                                            <option ${data.skill_typeX == 'P'?'selected':''} value="P">Primary</option>
                                                            <option ${data.skill_typeX == 'S'?'selected':''} value="S">Secondary</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="skill_type_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="years_worked_${dataStatus.count}" name="years_worked_${dataStatus.count}" style="width:48%">
                                                            <option value="" >--Year--</option>
                                                            <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                                                <c:choose>
                                                                    <c:when test="${extotyear == data.years_workedX}">
                                                                        <option  selected value="${extotyear}">${extotyear}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option  value="${extotyear}">${extotyear}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                        <select id="months_worked_${dataStatus.count}" name="months_worked_${dataStatus.count}" style="width:48%">
                                                            <option value="" >--Month--</option>
                                                            <c:forEach  var="extotmonth" varStatus="i"  begin="0" end="11">
                                                                <c:choose>
                                                                    <c:when test="${extotmonth == data.months_workedX}">
                                                                        <option  selected value="${extotmonth}">${extotmonth}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option  value="${extotmonth}">${extotmonth}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="years_worked_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="last_used_year_${dataStatus.count}" name="last_used_year_${dataStatus.count}" style="width:48%">
                                                            <option value="" >--Year--</option>
                                                            <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                                                <c:choose>
                                                                    <c:when test="${extotyear == data.last_used_yearX}">
                                                                        <option  selected value="${extotyear}">${extotyear}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option  value="${extotyear}">${extotyear}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                        <select id="last_used_month_${dataStatus.count}" name="last_used_month_${dataStatus.count}" style="width:48%">
                                                            <option value="" >--Month--</option>
                                                            <c:forEach  var="extotmonth" varStatus="i"  begin="0" end="11">
                                                                <c:choose>
                                                                    <c:when test="${extotmonth == data.last_used_monthX}">
                                                                        <option  selected value="${extotmonth}">${extotmonth}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option  value="${extotmonth}">${extotmonth}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="last_used_year_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Skills(${dataStatus.count});" />
                                                        <c:if test="${dataStatus.count !=1}">
                                                            <img alt="Remove" src="${pageContext.request.contextPath}/images/delete.gif" onclick="deleteRow(${dataStatus.count},1)" />
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <input type="hidden" name="skillId_1" id="skillId_1" value="" />
                                                    <input type="hidden" name="deletedTR_1" id="deletedTR_1" value="0" />
                                                    <td valign ="top" align="center">
                                                        <select id="skill_category_1" name="skill_category_1" style="width:100%;">
                                                            <option value="" >--Select Category--</option>
                                                            <option value="T">Technical</option>
                                                            <option value="N">Non-Technical</option>
                                                            <option value="F">Functional</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="skill_category_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="" id="streamSelected_${dataStatus.count}" />
                                                        <select id="stream_id_1" name="stream_id_1" style="width:95%" onChange="loadStream(this.value,1)">
                                                            <option value="" >--Select Stream--</option>
                                                            <c:forEach items="${streams}" var="item">
                                                                <option value="${item.streamId}" >${item.streamName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="stream_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="hidden" value="" id="selected_skill_1"/>
                                                        <select name="skill_id_1" id="skill_id_1" style="width:95%">
                                                            <option value="" >--Select Skill--</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="skill_id_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select name="skill_type_1" id="skill_type_1" style="width:95%">
                                                            <option value="" >--Select Skill Type--</option>
                                                            <option value="P">Primary</option>
                                                            <option value="S">Secondary</option>
                                                        </select>
                                                        <span style="color:red;display:none;" id="skill_type_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="years_worked_1" name="years_worked_1" style="width:48%">
                                                            <option value="" >--Year--</option>
                                                            <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                                                <option  value="${extotyear}">${extotyear}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <select id="months_worked_1" name="months_worked_1" style="width:48%">
                                                            <option value="" >--Month--</option>
                                                            <c:forEach  var="extotmonth" varStatus="i"  begin="0" end="11">
                                                                <option  value="${extotmonth}">${extotmonth}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="years_worked_error_1">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <select id="last_used_year_1" name="last_used_year_1" style="width:48%">
                                                            <option value="" >--Year--</option>
                                                            <c:forEach  var="extotyear" varStatus="i"  begin="0" end="20">
                                                                <option  value="${extotyear}">${extotyear}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <select id="last_used_month_1" name="last_used_month_1" style="width:48%">
                                                            <option value="" >--Month--</option>
                                                            <c:forEach  var="extotmonth" varStatus="i"  begin="0" end="11">
                                                                <option  value="${extotmonth}">${extotmonth}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="last_used_year_error_1">*required</span>
                                                    </td>
                                                    <td align="center" align="center">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Skills(1);" />
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
    for(i=1;i<=$('#recordCount').val();i++) {
        $("#stream_id_"+i).html($("#streamFirst").html());
        var q = $('#streamSelected_'+i).val();
        $("#stream_id_"+i).val(q);
        loadStream(q,i);
    }
</script>

