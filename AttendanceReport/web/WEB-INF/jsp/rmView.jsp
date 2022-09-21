<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<html>
    <head>
        <title>RM View</title>
        <link href="css/rmView.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <script>
            function getExcelReport() {
                $('#reportPage').attr("action", "exportAttendanceDetails.htm");
                var moduleId = $("#moduleId").val();
                var fDate = new Date($("#fromDate").val());
                var tDate = new Date($("#toDate").val());
                var fromDateValue=$("#fromDate").val();
                var toDateValue=$("#toDate").val();
                var fromDateValueArr=fromDateValue.split('-');
                var toDateValueArr=toDateValue.split('-');
                var fromTimeStamp=new Date(fromDateValueArr[2],fromDateValueArr[1],fromDateValueArr[0]).getTime();
                var toTimeStamp=new Date(toDateValueArr[2],toDateValueArr[1],toDateValueArr[0]).getTime();
                if ($("#employee_search").val() == 'Search by Employee Number or First Name or Last name' && (moduleId == '664' || moduleId == '663')) {
                    document.getElementById("emp_id").value = "";
                }
                if ($("#fromDate").val() == null || $("#fromDate").val() == "") {
                    alert("'From Date'and 'To Date' fields are mandatory.");
                } else if ($("#toDate").val() == null || $("#toDate").val() == "") {
                    alert("'From Date'and 'To Date' fields are mandatory.");
                } else if((toTimeStamp) < (fromTimeStamp)){
                    alert('To Date Should be greater than from Date');
                }else {
                    document.reportPage.submit();
                }
            }
            function getFilterList() {
                $("#totalRecords").val(0);
                var moduleId = $("#moduleId").val();
                var fDate = new Date($("#fromDate").val());
                var tDate = new Date($("#toDate").val());
                var fromDateValue=$("#fromDate").val();
                var toDateValue=$("#toDate").val();
                var fromDateValueArr=fromDateValue.split('-');
                var toDateValueArr=toDateValue.split('-');
                var fromTimeStamp=new Date(fromDateValueArr[2],fromDateValueArr[1],fromDateValueArr[0]).getTime();
                var toTimeStamp=new Date(toDateValueArr[2],toDateValueArr[1],toDateValueArr[0]).getTime();
                if (($("#employee_search").val() == 'Employee (ID/Name)' || $("#employee_search").val() == '')&& (moduleId == '664' || moduleId == '663')) {
                    document.getElementById("emp_id").value = "";
                }
                
                $('#page').val("1");
                $('#reportPage').attr("action", "getAttendanceDetails.htm");
                
                if ($("#fromDate").val() == null || $("#fromDate").val() == "") {
                    alert("'From Date'and 'To Date' fields are mandatory.");
                } else if ($("#toDate").val() == null || $("#toDate").val() == "") {
                    alert("'From Date'and 'To Date' fields are mandatory.");
                } else if((toTimeStamp) < (fromTimeStamp)){
                    alert('To Date Should be greater than from Date');
                }else {
                    document.reportPage.submit();
                    startLoading();
                }
            }
            $(document).ready(function() {
                
                if('${dataDispaly}' == '0'){
//                    $('#datadisplay').hide();
                    $('#noDataDisplay').show();
                }
                else {
                    $('#datadisplay').show();
                    $('#noDataDisplay').hide();
                }
                var today = new Date();
                var yesterday = new Date(today);
                yesterday.setDate(today.getDate()-1)
                $("#fromDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    disabled: true,
                    dateFormat: 'dd-mm-yy',
                    minDate : '28-06-2016',
                    maxDate: yesterday
                });
                $("#toDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    disabled: true,
                    dateFormat: 'dd-mm-yy',
                    minDate : '28-06-2016',
                    maxDate: yesterday
                });
                $("#employee_search").autocomplete("ajaxsearch.htm", {
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
            function loadForm(page) {

                $('#page').val(page);
                var url = $('#url').val();
                $('#reportPage').attr("action", url);
                document.reportPage.submit();
            }
        </script>
    </head>
    <body>
        <div id="main-container">
            <div class="main">
                <h3> My Team Attendance </h3>
                <div class="filter-container">
                    <form action="#" name="reportPage" method="post" id="reportPage">
                        <input type="hidden" id="url" name="url" value="${url}" />
                        <input type="hidden" id="page" name="page" value="1" />
                        <input type="hidden" id="emp_id" name="empId" value="${filterData.empId}" />
                        <input type="hidden" id="rm_id" name="rm_id" value="${rmId}"/>
                        <input type="hidden" id="moduleId" name="moduleId" value="${filterData.moduleId}"/>
                        <input type="hidden" id="totalRecords" name="totalRecords" value="${filterData.totalRecords}"/>
                        <div class="filter1">
                            <div class="filter-sub">
                                <label><b>Employee Search</b></label>                                
                                <input class="employee-box" type="text" id="employee_search" name="emp_id" value="${filterData.empId != '' && filterData.empId != null?empName:''}" 
                                       placeholder=" Employee (ID/Name)"   />
                            </div>
                            <div class="filter-sub bottom">
                                <label><b>Unit</b></label><br/>
                                <select class="department-unit" name="unit" id="unit">
                                    <option  value="All">All</option>
                                    <c:forEach items="${unitList}" var="unitStatus" varStatus="i">
                                        <option  value="${unitStatus.unitId}" ${unitStatus.unitId eq filterData.unit ? 'selected="selected" ':''}>${unitStatus.unitName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="filter2">
                            <div class="filter-sub">
                                <label><b>From Date<span class="mandat">*</span></b></label>
                                <input class="calender-field" type="text" placeholder="DD-MM-YYYY" name="fromDate" value="${filterData.displayFromDate}" id="fromDate" readonly/>
                            </div>
                            <div class="filter-sub bottom">
                                <label><b>Swipe Location</b></label><br/>
                                <select class="location-unit" name="location" id="location">
                                    <option  value="All">All</option>
                                    <c:forEach items="${locationList}" var="locationStatus" varStatus="i">
                                        <option  value="${locationStatus.locationId}" ${locationStatus.locationId eq filterData.location ?'selected="selected"':''}>${locationStatus.locationName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="filter3">
                            <div class="filter-sub">
                                <label><b>To Date<span class="mandat">*</span></b></label>
                                <input class="calender-field" type="text" placeholder="DD-MM-YYYY" name="toDate" value="${filterData.displayToDate}" id="toDate" readonly />
                            </div>
                            <div class="filter-sub bottom">
                                <label><b>Reporting Manager</b></label><br/>
                                <select id="directReportee" name="rmId">
                                    <option value="">All</option>
                                    <c:forEach items="${directReporteeList}" var="directReportee" varStatus="directReporteeStatus">
                                        <option value="${directReportee.directReporteeId}" ${directReportee.directReporteeId == filterData.rmId?'SELECTED':''}>${directReportee.directReporteeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="button-container">
                            <input class="go-button" type="button" name="goBtn" value="Go" onclick="getFilterList()"/>
                            <input class="export-button" type="button" name="exportBtn" value="Export" onclick="getExcelReport()"/>
                        </div>
                    </form>
                </div>
                <div id="datadisplay">
                    <div class="table-container">
                        <c:if test="${fn:length(attendanceList) > 0}">
                            <table id="rmView">
                                <thead>
                                <th class="empID">Emp ID</th>
                                <th class="empName">Name</th>
                                <th class="rmName">Reporting Manager</th>
                                <th class="unit">Unit</th>
                                <th class="date">Date</th>
                                <th class="status">Status</th>
                                <th class="inTime">In Time</th>
                                <th class="outTime">Out Time</th>
                                <th class="timeDuration">Time Duration</th>
                                <th class="swipeLocation">Swipe Location</th>
                                <th class="workLocation">Work Location</th>
                                </thead>
                                <tbody>
                                    <% int i = 0;
                                        int s = 0;
                                    %>
                                    <c:forEach items="${attendanceList}" var="item" varStatus="i">
                                        <% s = i % 2;
                                            if (s == 0) {%>
                                        <tr class="row-odd">
                                            <% } else {%>
                                        <tr class="row-even">
                                            <%}%>
                                            <td>
                                                ${item.employeeNumber}
                                            </td>
                                            <td>
                                                ${item.employeeName}
                                            </td>
                                            <td>
                                                ${item.reportingManager}
                                            </td>
                                            <td>
                                                ${item.unit}
                                            </td>
                                            <td>
                                                ${item.attendanceDate}
                                            </td>
                                            <td>
                                                ${item.status}
                                            </td>
                                            <td>
                                                ${item.inTime == null || item.inTime == ''?'---':item.inTime}
                                            </td>
                                            <td>
                                                ${item.outTime == null || item.outTime == ''?'---':item.outTime}
                                                <c:if test="${item.crossOverDate != null && item.crossOverDate != ''}">
                                                    <br>[${item.crossOverDate}]
                                                </c:if>
                                            </td>
                                            <td>
                                                ${item.timeDuration == null || item.timeDuration == ''?'---':item.timeDuration}
                                                <input type="hidden" id="time${j.index}" value="${item.timeDuration}"/>
                                            </td>
                                            <td>
                                                ${item.location == null || item.location == ''?'---':item.location}
                                            </td>
                                            <td>
                                                ${item.worklocation}
                                            </td>
                                            <% i = i + 1;%>
                                        </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${fn:length(attendanceList)==0}">
                            <div id="noDataDisplay">
                                <table>
                                    <tr class="row-odd">
                                        <td colspan="6" style="display: block; text-align: center;">
                                            No Data to Display
                                        </td>
                                    </tr>
                                </table>
                            </div>
                        </c:if>   
                        <c:if test="${paging[0] > 1}">

                            <%@include file="/WEB-INF/jsp/paging.jsp" %>

                        </c:if>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript">
            var loadingDivObj = (document.all);
            var ns4 = document.layers;
            var ns6 = document.getElementById && !document.all;
            var ie4 = document.all;
            if (ns4) {
                loadingDivObj = document.loadingDiv;
            } else if (ns6) {
                loadingDivObj = document.getElementById("loadingDiv").style;
            } else if (ie4) {
                loadingDivObj = document.all.loadingDiv.style;
            }

            stopLoading();

            function stopLoading() {
                if (ns4) {
                    loadingDivObj.visibility = "hidden";
                }
                else if (ns6 || ie4)
                    loadingDivObj.display = "none";
            }

            function startLoading() {
                if (ns4) {
                    loadingDivObj.visibility = "visible";
                }
                else if (ns6 || ie4)
                    loadingDivObj.display = "block";
            }
        </script>
    </body>
</html>