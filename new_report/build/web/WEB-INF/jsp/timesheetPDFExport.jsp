<%-- 
    Document   : timesheetPDFExport
    Created on : 18 Jan, 2018, 5:06:26 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <title>Timesheet Export</title>
        <style type="text/css">
            form label{
                float: none;
                display: inline;
                font-weight: bold;
            }
            #selectMonth,#selectYear,#selectVendor{
                height:22px;
                margin-right:10px;
                padding:1px 0px 1px 0px;
                background: #FFFFFF;
            }
            #selectMonth{
                width:90px;
            }
            #selectYear{
                width:55px;
            }
            .exportbutton{
                border-radius: 10px;
                cursor: pointer;
            }
            #employee_search{
                margin-right:10px;
            }
            .notice_page_ul li {
                margin-left: 30px;
            }
            form div {
                padding: 0px;
            }
        </style>
        <script>
            
            $(document).ready(function() {
                $("#employee_search").autocomplete("searchEmployee.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
                $("#employee_search").result(function(event, data, formatted) {
                    if (data) {
                        $("#emp_id").val(data[1]);
                    }
                });
            });
            function getFilterList() {
                $('#page').val("1");
                if (($("#employee_search").val() == 'Employee (ID/Name)' || $("#employee_search").val() == '')) {
                    document.getElementById("emp_id").value = null;
                }
                $('#vendorExport').attr("action", "vendorContractExport.htm");
                document.vendorExport.submit();
                startLoading();
                
            }
            function getExportAll(){
                $('#vendorExport').attr("action", "exportAllTimesheet.htm");
                document.vendorExport.submit();
            }
            
            function loadForm(page) {
                $('#page').val(page);
                var url = $('#url').val();
                $('#vendorExport').attr("action", url);
                document.vendorExport.submit();
            }
        </script>

    </head>
    <body>
        <div class="container_inner" style="margin: 35px auto 0px;height:600px;width: 1000px;">
            <div class="page_heading">
                VC Timesheet Report
                <div style="float: right;color: #4C83B3;font-weight: bold;font-size: 12px;">
                    <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="List Pending  Request" alt="">
                    <a style="text-decoration:none;color: #4C83B3;" id="pendingRequest" target="_self" href="vendorConsolidateExport.htm?page=1">Consolidate Timesheet List</a>
                </div>
            </div>
            <div class="notice_page" style="width:980px;height:20px;">
                <img src="${pageContext.request.contextPath}/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon" style="float:left;margin-top:-5px;"/>
                <div style="padding-top:3px;float:left;">
                    <ul class="notice_page_ul">
                       <li>Export all will download the timesheet of associates displayed in the page</li>
                    </ul>
                </div>
            </div>
            <form action="#" name="vendorExport" id="vendorExport" method ="post">
                <input type="hidden" id="url" name="url" value="${url}" />
                <input type="hidden" id="page" name="page" value="1" />
                <input type="hidden" id="emp_id" name="empId" value="${empId}" />
                <div class="tabletools">
                    <label>Employee :</label>
                    <input class="employee-box" type="text" id="employee_search" name="empName" placeholder=" Employee (ID/Name)" value="${empId != '' && empId != null?empName:''}"/>
                    <label>Month :</label>
                    <select id ="selectMonth" name="monthId">
                        <c:forEach items="${monthList}" var="month">
                            <option value="${month.key}" ${month.key==selectedMonth?'selected':''}>${month.value}</option>
                        </c:forEach>
                    </select>
                    <label>Year :</label>
                    <select id ="selectYear" name="yearId">
                        <c:forEach items="${yearList}" var="year">
                            <option value="${year.key}" ${year.key==selectedYear?'selected':''}>${year.value}</option>
                        </c:forEach>
                    </select>
                    <label>Vendor :</label>
                    <select id ="selectVendor" name="vendor_id">
                        <option value="">-- Select Vendor --</option>
                        <c:forEach items="${vendorList}" var="vendorList">
                            <option value="${vendorList.vendor_id}" ${vendorList.vendor_id==selectedVendor?'selected':''}>${vendorList.vendor_name}</option>
                        </c:forEach>
                    </select>
                    <input type="button" class="goButtonNew" id="goSearch" name="search" value=" Go " onclick="getFilterList()"/>
                    <input type="button" class="exportbutton" id="exportData" style="width:110px;" name="exportAll" value="Export All" onclick="getExportAll()"/>
                </div>
                <div id="datadisplay">
                    <div class="table-container">
                        <c:if test="${fn:length(employeeList) > 0}">
                            <table id="rmView">
                                <thead>
                                    <th class="empID">Employee Id</th>
                                    <th class="empName">Employee Name</th>
                                    <th class="rmName">Reporting Manager</th>
                                    <th class="unit">SBU Name</th>
                                    <th class="date">Sub SBU Name</th>
                                    <th class="date">Vendor Name</th>
                                    <th class="status">Export</th>
                                </thead>
                                <tbody>
                                    <% int i = 0;
                                        int s = 0;
                                    %>
                                    <c:forEach items="${employeeList}" var="item" varStatus="i">
                                        <% s = i % 2;
                                        if (s == 0) {%>
                                        <tr class="row-odd">
                                                <% } else {%>
                                        <tr class="row-even">
                                                <%}%>
                                        <td>
                                            <input type="hidden" name="employeeId" value="${item.employee_id}"/>
                                            ${item.employee_number}
                                        </td>
                                        <td>${item.employee_name}</td>
                                        <td>${item.reporting_manager}</td>
                                        <td>${item.sbu_name}</td>
                                        <td>${item.sub_sbu_name}</td>
                                        <td>${item.vendor_name}</td>
                                        <td style="text-align:center;">
                                            <a href="vendorContractTimesheetDetails.htm?employee_id=${item.employee_id}&monthId=${selectedMonth}&yearId=${selectedYear}">Export</a>
                                        </td>
                                        <% i = i + 1;%>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${fn:length(employeeList)==0}">
                            <table>
                                <tr class="row-odd">
                                    <td colspan="6" style="text-align: center;">
                                        No Data to Display
                                    </td>
                                </tr>
                            </table>
                        </c:if>   
                        <c:if test="${paging[0] > 1}">
                            <%@include file="/WEB-INF/jsp/paging.jsp" %>
                        </c:if>
                    </div>
                </div>
            </form>
        </div>
        
    </body>
</html>
