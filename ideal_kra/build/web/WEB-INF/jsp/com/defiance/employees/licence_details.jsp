<%-- 
    Document   : id_proofs
    Created on : 5 Sep, 2014, 11:03:53 AM
    Author     : 8000167
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
                    dateFormat: 'dd-mm-yy',
                    maxDate: new Date(),
                    yearRange: '1950:' + (new Date).getFullYear(),
                    currentText: 'Now'
                });
                $(".datePickMax").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    showButtonPanel: true,
                    dateFormat: 'dd-mm-yy',
                    minDate: new Date()
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
                            <form action="licenceSubmit.htm" name="licenceForm" id="licenceForm" method="post" onSubmit="return validateLicence();">
                                <input type="hidden" id="actionName" name="actionName" value="${actionName}" />

                                <input type="hidden" name="recordCount" id="recordCount" value="1" />


                                <table class="tableStructure" border="0">
                                    <tr class="headerCenter">
                                        <c:choose>
                                            <c:when test="${accessType == 'HR'}">
                                                <th width="20%">Employee Name</th>
                                                <th width="20%">Driving Licence Number</th>
                                                <th width="10%">Issue Date</th>
                                                <th width="10%">Expiry Date</th>
                                                <th width="20%">Remarks</th>
                                                </c:when>
                                                <c:otherwise>
                                                <th width="20%"><font color="red">*</font>Driving Licence Number</th>
                                                <th width="15%"><font color="red">*</font>Issue Date</th>
                                                <th width="15%"><font color="red">*</font>Expiry Date</th>
                                                <th width="20%">Remarks</th>
                                                <!--                                                <th width="4%">Action</th>-->
                                            </c:otherwise>
                                        </c:choose>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${accessType == 'HR'}">
                                            
                                        </c:when>
                                        <c:otherwise>  
                                            <c:if test="${!empty(result)}">
                                                <c:forEach items="${result}" var="data" varStatus="dataStatus">
                                                    <tr id="TR_1">
                                                        <td valign ="top" align="center">
                                                            <input type="text" name="licence_number" id="licence_number" style="width:95%" maxlength="20" value = "${data.licence_number}"/>
                                                            <span style="color:red;display:none;" id="licence_number_error">*required</span>
                                                        </td>
                                                        <td valign ="top" align="center">
                                                            <c:out value="${data.issueDate}"></c:out>
                                                            <input type="text" class="datePick"  id="licence_date_issue" name="licence_date_issue" style="width:95%" readonly value = "${issueDate}"/>
                                                            <span style="color:red;display:none;" id="licence_date_issue_error">*required</span>
                                                        </td>
                                                        <td valign ="top" align="center">
                                                            <input type="text" class="datePickMax"  name="licence_date_expire" id="licence_date_expire" style="width:95%" readonly value = "${data.licence_date_expire}"/>
                                                            <span style="color:red;display:none;" id="licence_date_expire_error">*required</span>
                                                        </td>
                                                        <td valign ="top" align="center">
                                                            <input type="text" id="remarks" name="licence_remarks"  style="width:95%" maxlength="100" value = "${licence_remarks}"/>
                                                            <span style="color:red;display:none;" id="remarks_error">*required</span>
                                                        </td>
                                                        <!--                                    <td align="center" align="center">
                                                                                                <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Licence(1);" />
                                                                                            </td>-->
                                                    </tr>
                                                </c:forEach>
                                            </c:if>
                                            <c:if test="${empty(result)}">
                                                <tr id="TR_1">
                                                    <td valign ="top" align="center">
                                                        <input type="text" name="licence_number" id="licence_number" style="width:95%" maxlength="20" />
                                                        <span style="color:red;display:none;" id="licence_number_error">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePick"  id="licence_date_issue" name="licence_date_issue" value="" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="licence_date_issue_error">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" class="datePickMax"  name="licence_date_expire" id="licence_date_expire" style="width:95%" readonly />
                                                        <span style="color:red;display:none;" id="licence_date_expire_error">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks" name="licence_remarks" value="" style="width:95%" maxlength="100" />
                                                        <span style="color:red;display:none;" id="remarks_error">*required</span>
                                                    </td>
                                                    <!--                                    <td align="center" align="center">
                                                                                            <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Licence(1);" />
                                                                                        </td>-->
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
                </tbody>
            </table>
            <!--            <table width="100%" border="0">
                            <tbody>
                                <tr>
                                    <td>
                                        <form action="panNumberSubmit.htm" name="panNumberForm" id="panNumberForm" method="post" onSubmit="return validatePanNumber();">
                                            <input type="hidden" id="actionName" name="actionName" value="${actionName}" />
            <%--<c:choose>
                <c:when test="${!empty(result)}">
                    <input type="hidden" name="recordCount" id="recordCount1" value="${fn:length(result)}" />
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="recordCount" id="recordCount1" value="1" />
                </c:otherwise>
            </c:choose>--%>
            <table class="tableStructure" border="0">
                <tr class="headerCenter">
            <c:choose>
                <c:when test="${accessType == 'HR'}">
                    <th width="20%">Employee Name</th>
                    <th width="20%">PAN Number</th>
                    <th width="20%">Remarks</th>
                </c:when>
                <c:otherwise>
                <th width="20%"><font color="red">*</font>PAN Number</th>
                <th width="20%">Remarks</th>
                                                                <th width="4%">Action</th>
                </c:otherwise>
            </c:choose>

        </tr>
            <c:choose>
                <c:when test="${accessType == 'HR'}">

                </c:when>
                <c:otherwise> 
                    <c:if test="${!empty(panData)}">
                    <tr id="TR1_1">
                        <td valign ="top" align="center">
                            <input type="text" name="pan_number" id="pan_number" style="width:95%" maxlength="15" value = "${panData.pan_number}"/>
                            <span style="color:red;display:none;" id="pan_number_error">*required</span>
                        </td>
                        <td valign ="top" align="center">
                            <input type="text" id="remarks1" name="remarks" value="" style="width:95%" maxlength="100" value = "${panData.remarks}"/>
                            <span style="color:red;display:none;" id="remarks1_error">*required</span>
                        </td>
                                                            <td align="center" align="center">
                                                                <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Pan(1);" />
                                                            </td>
                    </tr>
                    </c:if>
                    <c:if test="${empty(panData)}">
                        <tr id="TR1_1">
                            <td valign ="top" align="center">
                                <input type="text" name="pan_number" id="pan_number" style="width:95%" maxlength="15" />
                                <span style="color:red;display:none;" id="pan_number_error">*required</span>
                            </td>
                            <td valign ="top" align="center">
                                <input type="text" id="remarks1" name="remarks" value="" style="width:95%" maxlength="100" />
                                <span style="color:red;display:none;" id="remarks1_error">*required</span>
                            </td>
                                                                <td align="center" align="center">
                                                                    <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Pan(1);" />
                                                                </td>
                        </tr>
                    </c:if>>
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
</tbody>
</table>
<table width="100%" border="0">
<tbody>
<tr>
    <td>
        <form action="adharNumberSubmit.htm" name="adharNumberForm" id="panNumberForm" method="post" onSubmit="return validateAdharNumber();">
            <input type="hidden" id="actionName" name="actionName" value="${actionName}" />
            <%--<c:choose>
                <c:when test="${!empty(result)}">
                    <input type="hidden" name="recordCount" id="recordCount2" value="${fn:length(result)}" />
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="recordCount" id="recordCount2" value="1" />
                </c:otherwise>
            </c:choose>--%>
            <table class="tableStructure" border="0">
                <tr class="headerCenter">
            <c:choose>
                <c:when test="${accessType == 'HR'}">
                    <th width="20%">Employee Name</th>
                    <th width="20%">Adhar Number</th>
                    <th width="20%">Remarks</th>
                </c:when>
                <c:otherwise>
                <th width="20%"><font color="red">*</font>Adhar Number</th>
                <th width="20%">Remarks</th>
                                                                <th width="4%">Action</th>
                </c:otherwise>
            </c:choose>

        </tr>
            <c:choose>
                <c:when test="${accessType == 'HR'}">

                </c:when>
                <c:otherwise>
                    <c:if test="${!empty(adharData)}">
                    <tr id="TR2_1">
                        <td valign ="top" align="center">
                            <input type="text" name="adhar_number" id="adhar_number" style="width:95%" maxlength="15" value = "${adharData.adhar_number}"/>
                            <span style="color:red;display:none;" id="adhar_number_error">*required</span>
                        </td>
                        <td valign ="top" align="center">
                            <input type="text" id="remarks2" name="remarks" value="" style="width:95%" maxlength="100" value = "${adharData.remarks}"/>
                            <span style="color:red;display:none;" id="remarks2_error">*required</span>
                        </td>
                                                            <td align="center" align="center">
                                                                <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Adhar(1);" />
                                                            </td>
                    </tr>
                    </c:if>
                    <c:if test="${empty(adharData)}">
                        <tr id="TR2_1">
                            <td valign ="top" align="center">
                                <input type="text" name="adhar_number" id="adhar_number" style="width:95%" maxlength="15"/>
                                <span style="color:red;display:none;" id="adhar_number_error">*required</span>
                            </td>
                            <td valign ="top" align="center">
                                <input type="text" id="remarks2" name="remarks" value="" style="width:95%" maxlength="100" />
                                <span style="color:red;display:none;" id="remarks2_error">*required</span>
                            </td>
                                                                <td align="center" align="center">
                                                                    <img alt="Add" src="${pageContext.request.contextPath}/images/add.gif" onclick="addRow_Adhar(1);" />
                                                                </td>
                        </tr>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </table>-->
            <%--<c:if test="${accessType != 'HR'}" >
                <table class="buttonClass">
                    <tr>
                        <td><input type="submit" name="buttonSubmit" id="buttonSubmit" value="Submit" class="submitbutton" /></td>
                    </tr>
                </table>
            </c:if>
        </form>--%>
            <!--                        </td>
                                </tr>
                            </tbody>
                        </table>-->
        </div>
    </body>
</html>
