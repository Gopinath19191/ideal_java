<%-- 
    Document   : employeeKraView
    Created on : 7 Aug, 2017, 7:05:49 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>KRA Page</title>
    </head>
    <body>
        <div class="container_inner"> 
            <div class="page_heading">
                KRA View
                <div class="listLink">
                    <img src="${pageContext.request.contextPath}/images/icon_list.png">
                    <c:if test="${RmList==1}">
                        <a href="rmListKra.htm" target="_self" style="text-decoration:none;color: #4C83B3;">List KRA</a>
                    </c:if>
                    <c:if test="${RmList==0}">
                        <a href="listKra.htm" target="_self" style="text-decoration:none;color: #4C83B3;">List KRA</a>
                    </c:if>
                    <c:if test="${RmList==2}">
                        <a href="reportListKra.htm" target="_self" style="text-decoration:none;color: #4C83B3;">List KRA</a>
                    </c:if>
                    
                </div>
            </div>
        </div>
        <div class="filterWrap">
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <th>Name</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).empNumber}-${masterDetails.get(0).employee_name}</th>
                    <th>Finance year</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).financial_year}</th>
                    <th>KRA Period</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).quarter_name}</th>
<!--                    <th>Submitted On</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).submitted_on}</th>-->
                </tr>
                <tr>
                    <th>Submitted On</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).submitted_on}</th>
<!--                    <th>Approved By</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).rm_name}</th>
                    <th>Approved On</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).approved_on}</th>-->
                    <th>Accepted On</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).accepted_on}</th>
                    <th>Status</th>
                    <th style="font-weight: normal;">${masterDetails.get(0).status}</th>
                </tr>
            </table>
        </div>
        <form name="employeeKraForm" id="employeeKraForm" method="post">
        <div class="feedbacks index">
            <div id="datadisplay">
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <th>KRA Description</th>
                        <th style="width:60px;">KRA UOM</th>
                        <th style="width:30px;">KRA Target</th>
                        <th style="width:30px;">KRA Weightage(%)</th>
                        <!--<th style="width:30px;">KRA Achieved</th>-->
                        <th>RM Remarks</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(details)!=0}">
                            <c:forEach items="${details}" var="item" varStatus="iteamCount">
                                <tr>
                                    <td><textarea class="kraDescription" readonly>${item.kra_description}</textarea></td>
                                    <td><input type="text" style="text-align:center;" value="${item.kra_uom}" readonly/></td>
                                    <td><input type="text" style="text-align:center;" value="${item.kra_target}" readonly/></td>
                                    <td><input type="text" style="text-align:center;" value="${item.kra_weightage}" readonly/></td>
                                    <!--<td><input type="text" value="${item.kra_achieved}" readonly/></td>-->
                                    <td>
                                        <input type="hidden" value="${item.description_id}" name="certificationId_${iteamCount.count}">
                                        <textarea class="kraRmRemarks" name="qualification_${iteamCount.count}" id="rm_remarks_${iteamCount.count}">${item.rm_remarks}</textarea>
                                    </td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <input type="hidden" name="kra_id" value="${details.get(0).kra_id}"/>
                                <input type="hidden" name="recordCount" id="recordCount" value="${fn:length(details)}" />
                                <th align="right" colspan="3"><label class="label">Total Weightage:</label></th>
                                <td><input type="text" style="text-align:center;" value="${totalWeightage}" readonly/></td>
                                <th></th>
                            </tr>
                            <tr>
                                <td colspan="5">
                                    <input type="hidden" id="actionValue" name="actionValue" value=""/>
                                    <div class="buttonClass">
                                        <c:if test="${fn:length(details)!=0}">
                                            <!--<input type="button" name="buttonSubmit" id="buttonSubmit" value="Cancel" class="cancelbutton" onclick="javascript: location.href='rmListKra.htm'"/>-->
<!--                                            <input type="button" name="buttonSubmit" id="buttonSubmit" value="Approve" class="approvebutton" onClick="return rmValidateKra('p');"/>--> 
                                                <!--<input type="button" name="buttonSubmit" id="buttonSubmit" value="Submit" class="approvebutton" onClick="return rmValidateKra('m');"/>-->
<!-- <input type="button" name="buttonSubmit" id="buttonSubmit" value="Send Back" class="sendbackbutton" onClick="return rmValidateKra('r');"/>-->
                                        </c:if>
                                    </div>
                                </td>
                            </tr>
                            
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="5" style="text-align: center;"><label class="label">No data to display</label></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    
                </table>
                
            </div>
        </div>
    </form>
    </body>
</html>
