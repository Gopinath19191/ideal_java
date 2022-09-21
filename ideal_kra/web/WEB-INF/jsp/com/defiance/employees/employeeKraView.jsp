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
        
    <style>
        #errordiv{
        position: relative;
        left: 400px;
        bottom: 14px;
        font-size: 15px;
       }
       .nextbutton{
            padding-left: 20px;
            background: url(../ideal_kra/images/continue-button-icon.png) no-repeat 8px 8px #316CA8;
            width:150px;
            height:32px;
            font-family: Arial;
            font-weight: bold;
            font-size: 13px;
            color: #FFFFFF;
            text-align: center;
            border: 1px solid #4492BF;
            border-radius: 5px;
            cursor: pointer;
       }
    </style>
    <form  name="employeeKraForm" id="employeeKraForm" method="post" >
        <div class="container_inner"> 
              <div id="errordiv">
                <span style=" color: red;">${returnMsg}</span>
            </div>
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
        <div class="feedbacks index">
            <div id="datadisplay">
                <table cellpadding="0" cellspacing="0">
                    <tr>
                        <th style="width:500px;">KRA Description</th>
                        <th style="width:30px;">KRA UOM</th>
                        <th style="width:30px;">KRA Target</th>
                        <th style="width:30px;">KRA Weightage(%)</th>
                        <th style="width:30px;">KRA Achieved</th>
                        <th>RM Remarks</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(details)!=0}">
                            <c:forEach items="${details}" var="item">
                                <tr>
                                    <td>${item.kra_description}</td>
                                    <td style="text-align:center">${item.kra_uom}</td>
                                    <td style="text-align:center">${item.kra_target}</td>
                                    <td style="text-align:center">${item.kra_weightage}</td>
                                    <td style="text-align:center">${item.kra_achieved}</td>
                                    <td>${item.rm_remarks}</td>
                                </tr>
                            </c:forEach>
                                <tr>
                                    <th align="right" colspan="3"><label class="label">Total Weightage:</label></th>
                                    <td><input type="text" style="text-align:center;" value="${totalWeightage}" readonly/></td>
                                    <th colspan="2"></th>
                                </tr>
                                <tr>
                                    <td  align="center" colspan="6">
                                        <input type="hidden" id="employeeId" name="employeeId" value="${masterDetails.get(0).employeeId}" readonly/>
                                        <input type="hidden" id="financialYear" name="financialYear" value="${masterDetails.get(0).financial_year}" readonly/>
                                        <input type="hidden" id="quarterId" name="quarterId" value="${masterDetails.get(0).quarter_id}" readonly/>
                                        <input type="hidden" id="kraId" name="kraId" value="${masterDetails.get(0).kra_id}" readonly/>
                                        <c:if test="${masterDetails.get(0).quarter_id != '5'}">
                                            <b>  Copy KRA To : </b>                                        
                                            <select class="quarter" name="copyQuarterId" id="copyQuarterId">
                                                <c:forEach items="${quarterList}" var="quarterList" varStatus="i">
                                                    <option value="${quarterList.quarter_id}" ${quarterList.quarter_id eq current_quarter ? 'selected="selected"':''}>${quarterList.quarter_name}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="button" name="copy" value="Copy KRA" style="float:none;padding: 6px;"class="copybutton" onclick="return searchEmployeeKraToCopy();"/>
                                        </c:if>
                                        
                                        <input type="button" name="view" value="Export" style="float:none;"class="exportbutton" onclick="return getExcelExport();"/>
                                        <c:if test="${current_quarter == masterDetails.get(0).quarter_id}">
                                            <input type="button" name="view" value="Annual Appraisal" style="float:none;"class="nextbutton" onclick="return annualAppraisal();"/>
                                        </c:if>
                                    </td>    
                                </tr>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td colspan="7" style="text-align: center;"><label class="label">No data to display</label></td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    
                </table>

            </div>
        </div>
    </form>
    </body>
</html>
