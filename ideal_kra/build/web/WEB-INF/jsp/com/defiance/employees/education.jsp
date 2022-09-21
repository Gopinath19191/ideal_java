<%--
    Document   : education
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
                            <form action="certificationSubmit.htm" name="educationForm" id="educationForm" method="post" onSubmit="return validateEducation();">
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
                                                <th width="10%">Degree</th>
                                                <th width="14%">Qualification</th>
                                                <th width="8%">Year of passing</th>
                                                <th width="14%">Institution</th>
                                                <th width="14%">University / Board</th>
                                                <th width="8%">Percentage / CGPA</th>
                                                <th width="12%">Remarks</th>

                                            </c:when>
                                            <c:otherwise>
                                                <th width="12%"><font color="red">*</font>Degree</th>
                                                <th width="16%"><font color="red">*</font>Qualification</th>
                                                <th width="10%"><font color="red">*</font>Year of passing</th>
                                                <th width="16%"><font color="red">*</font>Institution</th>
                                                <th width="16%"><font color="red">*</font>University / Board</th>
                                                <th width="10%"><font color="red">*</font>Percentage / CGPA</th>
                                                <th width="16%">Remarks</th>
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
                                                        ${data.degreeXY}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:choose>
                                                            <c:when test="${data.degreeX != '10' && data.degreeX != '12' && data.qualificationXY != null}" >
                                                                ${data.qualificationXY}
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${data.qualificationX}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.year_of_passingX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:choose>
                                                            <c:when test="${data.degreeX != '10' && data.degreeX != '12' && data.institutionXY != null}" >
                                                                ${data.institutionXY}
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${data.institutionX}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        <c:choose>
                                                             <c:when test="${data.degreeX != '10' && data.degreeX != '12' && data.universityXY != null}" >
                                                                ${data.universityXY}
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${data.universityX}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.percentageX}
                                                    </td>
                                                    <td valign ="top" align="left">
                                                        ${data.remarksX}
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <c:if test="${empty(result)}">
                                                <tr>
                                                    <td colspan="8" align="center">
                                                        <font color="red">No Records Found</font>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:when>
                                        <c:otherwise>
                                            <div style="display:none">
                                                <select style="width:95%" id="qualificationFirst" name="qualificationFirst">
                                                    <option value="">-- Select Qualification --</option>
                                                    <c:forEach items="${course}" var="item">
                                                        <option value="${item.courseId}" title="${item.courseName}" >${item.courseName}</option>
                                                    </c:forEach>
                                                </select>
                                                <select style="width:95%" id="institutionFirst" name="institutionFirst">
                                                    <option value="">-- Select Institution --</option>
                                                    <c:forEach items="${institute}" var="item">
                                                        <option value="${item.instituteId}" title="${item.instituteName}">${item.instituteName}</option>
                                                    </c:forEach>
                                                </select>
                                                <select style="width:95%" id="universityFirst" name="universityFirst">
                                                    <option value="">-- Select University --</option>
                                                    <c:forEach items="${university}" var="item">
                                                        <option value="${item.universityId}" title="${item.universityName}">${item.universityName}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                <tr id="TR_${dataStatus.count}">
                                                    <input type="hidden" name="certificationId_${dataStatus.count}" id="certificationId_${dataStatus.count}" value="${data.certificationIdX}" />
                                                    <input type="hidden" name="deletedTR_${dataStatus.count}" id="deletedTR_${dataStatus.count}" value="0" />
                                                    <td align="center" valign="top">
                                                        <select style="width:95%" onChange="change(this.value,dataStatus.count)" title="${data.degreeX}" disabled>
                                                            <option>-- Select Degree --</option>
                                                            <c:forEach items="${degree}" var="item">
                                                                <c:set var="selval" value="" ></c:set>
                                                                <c:choose>
                                                                    <c:when test="${item.degreeId == data.degreeX}">
                                                                        <option selected value="${item.degreeId}" >${item.degreeName}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value="${item.degreeId}" >${item.degreeName}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                            <div style="display:none">
                                                                <select style="width:95%" id="degree_${dataStatus.count}" name="degree_${dataStatus.count}" onChange="change(this.value,dataStatus.count)" title="${data.degreeX}">
                                                                    <option>-- Select Degree --</option>
                                                                    <c:forEach items="${degree}" var="item">
                                                                        <c:set var="selval" value="" ></c:set>
                                                                        <c:choose>
                                                                            <c:when test="${item.degreeId == data.degreeX}">
                                                                                <option selected value="${item.degreeId}" >${item.degreeName}</option>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <option value="${item.degreeId}" >${item.degreeName}</option>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </c:forEach>
                                                                </select>
                                                            </div>
                                                        <span style="color:red;display:none;" id="degree_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">

                                                        <span id="span1_qualification_${dataStatus.count}" style="display:${(data.degreeX == '10' || data.degreeX == '12')?'none':'block'}">
                                                            <c:choose>
                                                                <c:when test="${ data.qualificationXY == '' || data.qualificationXY == null }">
                                                                    <input type="text" style="width:95%" id="qualification_${dataStatus.count}" name="qualification_${dataStatus.count}" maxlength="50" value="${data.qualificationX}" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="hidden" value="${data.qualificationX}" id="qualificationSelected_${dataStatus.count}" />
                                                                    <select style="width:95%" id="qualification_${dataStatus.count}" name="qualification_${dataStatus.count}" >
                                                                        <option value="">-- Select Qualification --</option>
                                                                    </select>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                        <span id="span2_qualification_${dataStatus.count}" style="display:${(data.degreeX == '10' || data.degreeX == '12')?'block':'none'}">
                                                            <input type="text" style="width:95%" id="qualification1_${dataStatus.count}" name="qualification1_${dataStatus.count}" maxlength="50" value="${data.qualificationX}" />
                                                        </span>
                                                        <span style="color:red;display:none;" id="qualification_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="text" style="width:95%" id="year_of_passing_${dataStatus.count}" name="year_of_passing_${dataStatus.count}" maxlength="4" value="${data.year_of_passingX}" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,2,false);" />
                                                        <span style="color:red;display:none;" id="year_of_passing_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">

                                                        <span id="span1_institution_${dataStatus.count}" style="display:${(data.degreeX == '10' || data.degreeX == '12')?'none':'block'}">
                                                            <c:choose>
                                                                <c:when test="${ data.institutionXY == '' || data.institutionXY == null }">
                                                                    <input type="text" style="width:95%" id="institution_${dataStatus.count}" name="institution_${dataStatus.count}" maxlength="50" value="${data.institutionX}" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="hidden" value="${data.institutionX}" id="institutionSelected_${dataStatus.count}" />
                                                                    <select style="width:95%" id="institution_${dataStatus.count}" name="institution_${dataStatus.count}">
                                                                        <option value="">-- Select Institution --</option>
                                                                    </select>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                        <span id="span2_institution_${dataStatus.count}" style="display:${(data.degreeX == '10' || data.degreeX == '12')?'block':'none'}">
                                                            <input type="text" style="width:95%" id="institution1_${dataStatus.count}" name="institution1_${dataStatus.count}" maxlength="50" value="${data.institutionX}" />
                                                        </span>
                                                        <span style="color:red;display:none;" id="institution_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">

                                                        <span id="span1_university_${dataStatus.count}" style="display:${(data.degreeX == '10' || data.degreeX == '12')?'none':'block'}">
                                                            <c:choose>
                                                                <c:when test="${ data.universityXY == '' || data.universityXY == null }">
                                                                    <input type="text" style="width:95%" id="university_${dataStatus.count}" name="university_${dataStatus.count}" maxlength="50" value="${data.universityX}" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <input type="hidden" value="${data.universityX}" id="universitySelected_${dataStatus.count}" />
                                                                    <select style="width:95%" id="university_${dataStatus.count}" name="university_${dataStatus.count}">
                                                                        <option value="">-- Select University --</option>
                                                                    </select>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </span>
                                                        <span id="span2_university_${dataStatus.count}" style="display:${(data.degreeX == '10' || data.degreeX == '12')?'block':'none'}">
                                                            <input type="text" style="width:95%" id="university1_${dataStatus.count}" name="university1_${dataStatus.count}" maxlength="50" value="${data.universityX}" />
                                                        </span>
                                                        <span style="color:red;display:none;" id="university_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="text" style="width:95%" id="percentage_${dataStatus.count}" name="percentage_${dataStatus.count}" maxlength="5" value="${data.percentageX+((data.percentageX%1>0.5)?(1-(data.percentageX%1))%1:-(data.percentageX%1))}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);"/>
                                                        <span style="color:red;display:none;" id="percentage_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="text" style="width:95%" id="remarks_${dataStatus.count}" name="remarks_${dataStatus.count}" maxlength="50" value="${data.remarksX}"/>
                                                        <span style="color:red;display:none;" id="remarks_error_${dataStatus.count}">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Education(${dataStatus.count});" />
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
                                                    <td align="center" valign="top">
                                                        <select style="width:95%" id="degree_1" name="degree_1" onChange="change(this.value,1)">
                                                            <option>-- Select Degree --</option>
                                                            <c:forEach items="${degree}" var="item">
                                                                <option value="${item.degreeId}" >${item.degreeName}</option>
                                                            </c:forEach>
                                                        </select>
                                                        <span style="color:red;display:none;" id="degree_error_1">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="hidden" value="" id="qualificationSelected_1" />
                                                        <span id="span1_qualification_1">
                                                            <select style="width:95%" id="qualification_1" name="qualification_1" >
                                                                <option value="">-- Select Qualification --</option>
                                                            </select>
                                                        </span>
                                                        <span id="span2_qualification_1" style="display:none">
                                                            <input type="text" style="width:95%" id="qualification1_1" name="qualification1_1" maxlength="50" />
                                                        </span>
                                                        <span style="color:red;display:none;" id="qualification_error_1">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="text" style="width:95%" id="year_of_passing_1" name="year_of_passing_1" maxlength="4" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,2,false);" />
                                                        <span style="color:red;display:none;" id="year_of_passing_error_1">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="hidden" value="" id="institutionSelected_1" />
                                                        <span id="span1_institution_1">
                                                            <select style="width:95%" id="institution_1" name="institution_1">
                                                                <option value="">-- Select Institution --</option>
                                                            </select>
                                                        </span>
                                                        <span id="span2_institution_1" style="display:none">
                                                            <input type="text" style="width:95%" id="institution1_1" name="institution1_1" maxlength="50" />
                                                        </span>
                                                        <span style="color:red;display:none;" id="institution_error_1">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="hidden" value="" id="institutionSelected_1" />
                                                        <span id="span1_university_1">
                                                            <select style="width:95%" id="university_1" name="university_1">
                                                                <option value="">-- Select University --</option>
                                                            </select>
                                                        </span>
                                                        <span id="span2_university_1" style="display:none">
                                                            <input type="text" style="width:95%" id="university1_1" name="university1_1" maxlength="50" />
                                                        </span>
                                                        <span style="color:red;display:none;" id="university_error_1">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="text" style="width:95%" id="percentage_1" name="percentage_1" maxlength="5" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);"/>
                                                        <span style="color:red;display:none;" id="percentage_error_1">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <input type="text" style="width:95%" id="remarks_1" name="remarks_1" maxlength="50"/>
                                                        <span style="color:red;display:none;" id="remarks_error_1">*required</span>
                                                    </td>
                                                    <td align="center" valign="top">
                                                        <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Education(1);" />
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
        for(var k=1; k<=$('#recordCount').val();k++) {
            //$('#qualificationFirst').find('option').clone().appendTo('#qualification_'+k);
            //$('#institutionFirst').find('option').clone().appendTo('#institution_'+k);
            //$('#universityFirst').find('option').clone().appendTo('#university_'+k);
            //$('#qualificationFirst option').clone().appendTo('#qualification_'+k);
            if(($('#degree_'+k).val() != 10 && $('#degree_'+k).val() != 12)) {
               $("#qualification_"+k).html($("#qualificationFirst").html());
                $("#institution_"+k).html($("#institutionFirst").html());
                $("#university_"+k).html($("#universityFirst").html());
            }

            if(document.getElementById('qualificationSelected_'+k) != null ) {
                var q = $('#qualificationSelected_'+k).val();
                $("#qualification_"+k).val(q);
            }
            if(document.getElementById('institutionSelected_'+k) != null ) {
                var r = $('#institutionSelected_'+k).val();
                $("#institution_"+k).val(r);
            }
            if(document.getElementById('universitySelected_'+k) != null ) {
                var s = $('#universitySelected_'+k).val();
                $("#university_"+k).val(s);
            }
        }
    });

</script>
