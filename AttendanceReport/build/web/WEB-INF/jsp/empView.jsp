<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<html>
    <head>
        <title>Employee View</title>
        <link href="css/empView.css" rel="stylesheet" type="text/css"/>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
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
            });
            function loadForm(page) {

                $('#page').val(page);
                var url = $('#url').val();
                $('#reportPage').attr("action", url);
                document.reportPage.submit();
            }
            function isNumber(evt) {
                evt = (evt) ? evt : window.event;
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="main-container">
            <div class="main">
                <div class="main-header">
                    <h3>My Attendance</h3>
                </div>
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <input type="hidden" id="url" name="url" value="${url}" />
                    <input type="hidden" id="page" name="page" value="1" />
                    <input type="hidden" id="rm_id" name="rm_id" value="${rmId}"/>
                    <input type="hidden" id="logged_employee_id" name="loggedInempId" value ="${filterData.loggedInempId}"/>
                    <input type="hidden" id="moduleId" name="moduleId" value="${filterData.moduleId}"/>
                    <input type="hidden" id="totalRecords" name="totalRecords" value="${filterData.totalRecords}"/>
                    <div class="filter-container">
                        <div class="date-filter">
                            <div class="date-container1 first">
                                <label><b>From Date<span class="mandat">*</span></b></label>
                                <input class="calender-field" type="text" placeholder="DD-MM-YYYY" name="fromDate" id="fromDate" value="${filterData.displayFromDate}" readonly/>
                            </div>
                            <div class="date-container2">
                                <label><b>To Date<span class="mandat">*</span></b></label>
                                <input class="calender-field" type="text" placeholder="DD-MM-YYYY" name="toDate" id="toDate" value="${filterData.displayToDate}" readonly/>
                                <input class="go-button" type="button" name="goBtn" value="Go" onclick="getFilterList()" />
                            </div>
                        </div>
                        <div class="export-container">
                            <input class="export-button" type="button" name="exportBtn" value="Export" onclick="getExcelReport()"/>
                        </div>
                    </div>
                </form>
                <div id="datadisplay">
                    <div class="table-container">
                        <c:if test="${fn:length(attendanceList) > 0}">
                            <table id="empView">
                                <thead>
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
                                        <tr class="odd">
                                            <% } else {%>
                                        <tr class="even">
                                            <%}%>
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
                            <table>
                                <tr class="even" colspan="6" style="text-align: center;">
                                    <td>
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
            </div>
    </body>
</html>