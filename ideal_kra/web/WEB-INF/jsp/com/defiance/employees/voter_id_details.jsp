<%-- 
    Document   : pan_details
    Created on : 8 Sep, 2014, 12:17:49 PM
    Author     : 8000167
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
                            <form action="voterIdSubmit.htm" name="voterIdForm" id="voterIdForm" method="post" onSubmit="return validateVoterId();">
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
                                                <th width="20%">Voter Id</th>
                                                <th width="20%">Remarks</th>
                                                </c:when>
                                                <c:otherwise>
                                                <th width="20%"><font color="red">*</font>Voter Id</th>
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
                                                    <input type="text" name="voter_id" id="voter_id" style="width:95%" maxlength="20" value = "${data.voter_id}"/>
                                                    <span style="color:red;display:none;" id="voter_id_error">*required</span>
                                                </td>
                                                <td valign ="top" align="center">
                                                    <input type="text" id="remarks" name="voter_remarks" style="width:95%" maxlength="100" value = "${voter_remarks}"/>
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
                                                        <input type="text" name="voter_id" id="voter_id" style="width:95%" maxlength="20" />
                                                        <span style="color:red;display:none;" id="voter_id_error">*required</span>
                                                    </td>
                                                    <td valign ="top" align="center">
                                                        <input type="text" id="remarks" name="voter_remarks" value="" style="width:95%" maxlength="100" />
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
        </div>
    </body>
</html>
