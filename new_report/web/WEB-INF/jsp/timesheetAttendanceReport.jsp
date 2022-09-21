<%--
    Document   : timesheetAttendanceReport
    Created on : Aug 22, 2018, 9:57:27 AM
    Author     : 16656
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Timesheet Attendance Reports</title>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css">
        <!--<link rel="stylesheet" href="/resources/demos/style.css">-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        
    </head>

    <style type="text/css">
        #loadingDiv img{ border: none; }
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
        .gobutton{
            border: 1px solid #BCCFEA;
            background: none repeat scroll 0 0 #316ca8;
            color: #FFFFFF;
            float: left;
            font-weight: bold;
            height: 32px;
            margin-top: 5px;
            width: 50px;
        }
        form label { width: 118px !important; font-size: 13px; }
        .attendance_employee{ width: 200px; height: 24px; margin-left: 8px;}
        .calender-field{ height: 20px; margin-left: 28px;}
        .gobutton{ cursor: pointer; }
        .exportbutton{ margin-left: 60px; cursor: pointer; margin-top: 5px;}
        .mandat{ color: red;}
        .focused{ border: 1px solid red !important;}
    </style>

    <script type="text/javascript">

        function loadForm(page) {
            $('#page').val(page);
            $('#reportPage').attr("action", "timesheetAttendanceReport.htm");
            document.reportPage.submit();
            var ele = document.getElementById("container");
            $("#loadingDiv").css("height",ele.scrollHeight);
            startLoading();
        }

        $(document).ready(function(){
            var jsonObj=[];
        <c:forEach items="${filter_employees}" var="item">
                jsonObj.push({"label":"${item.employee_name}","value":"${item.employee_no}"});
        </c:forEach>
                jsonObj=JSON.stringify(jsonObj);
                jsonObj=JSON.parse(jsonObj);
                //                console.log(jsonObj);
                $("#gobutton").click(function(){
                    if ($("#fromDate").val() == null || $("#fromDate").val() == "") {
                        $("#fromDate").addClass("focused");
                        return false;
                    } else if ($("#toDate").val() == null || $("#toDate").val() == "") {
                        $("#toDate").addClass("focused");
                        return false;
                    } else {
                        $('#reportPage').attr("action", "timesheetAttendanceReport.htm");
                        $("#reportPage").submit();
                        startLoading();
                    }
                });
            
                $("#exportbutton").click(function(){
                    if ($("#fromDate").val() == null || $("#fromDate").val() == "") {
                        $("#fromDate").addClass("focused");
                        return false;
                    } else if ($("#toDate").val() == null || $("#toDate").val() == "") {
                        $("#toDate").addClass("focused");
                        return false;
                    } else {
                        $('#reportPage').attr("action", "gettimesheetAttendanceReportXL.htm");
                        $("#reportPage").submit();
                    }
                });
                
                $( function(){
                    //                    var availableTags = jsonObj;
                    $( ".attendance_employee" ).autocomplete({
                        source: function(request, response) {
                            var results = $.ui.autocomplete.filter(jsonObj, request.term);
                            response(results.slice(0, 10));
                        },
                        //                        source: jsonObj,
                        select: function( event, ui ) {
                            //                            console.log(ui.item.label);
                            $("#test").val(ui.item.label)
                            $("#emp_id").val(ui.item.value)
                            return false;
                        }
                    });
                });
            
                //                $(".attendance_employee").autocomplete({
                //                    minChars: 1,
                //                    width: 350,
                //                    data: jsonObj
                //                    //                dataType: "jsonp",
                //                    //                matchContains: true
                //                });
                //                $(".attendance_employee").result(function(event, data, formatted) {
                //                    if (data) {
                //                        $("#emp_id").val(data[1]);
                //                    }
                //                });

                var today = new Date();
                var yesterday = new Date(today);
                yesterday.setDate(today.getDate()-1)

                $("#fromDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    //                    disabled: true,
                    dateFormat: 'dd-mm-yy',
                    minDate : '02-01-2016',
                    maxDate: yesterday,
                    onSelect: function () {
                        var date_format = $("#fromDate").val().split('-');
                        var from_date_new = date_format[2]+'-'+date_format[1]+'-'+date_format[0];
                        $("#toDate").datepicker( "destroy" );
                        $("#toDate").datepicker({
                            changeMonth: true,
                            changeYear: true,
                            dateFormat: 'dd-mm-yy',
                            minDate: new Date(from_date_new),
                            maxDate: yesterday
                        });
                        $('#toDate').val('');
                    }
                });
                
                var date_format=$("#fromDate").val().split('-');
                var from_date_new=date_format[2]+'-'+date_format[1]+'-'+date_format[0];
                $("#toDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'dd-mm-yy',
                    minDate : new Date(from_date_new),
                    maxDate: yesterday
                });

            });
    </script>

    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Timesheet Attendance Report
                </div>
            </div>

            <div class="tabletools" >
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <!--<input type="hidden" id="emp_id" name="employee_no" value="${filterData.employee_no != '' && filterData.employee_no != null?filterData.employee_no:''}" />-->
                    <input type="hidden" id="page" name="page" value="1">
                    <table id="searchForm">
                        <tbody>
                            <tr>
                                <td width="160px">
                                    <label><b style="margin-left: 8px">Employee Name :</b> </label>
<!--                                    <input type="text" id="test" class="attendance_employee" name="employee_name" value="${filterData.employee_no != '' && filterData.employee_no != null?filterData.employee_name:''}"
                                           placeholder=" Seartch By Employee (ID/Name)" autocomplete="off" />-->
                                    <select class="attendance_employee" name="employee_no" id="test">
                                        <option  value=""> -- Select Reportee -- </option>
                                        <c:forEach items="${filter_employees}" var="reportee" varStatus="empStatus">
                                            <option value="${reportee.employee_no}" ${reportee.employee_no == filterData.employee_no?'SELECTED':''}>${reportee.employee_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td width="1px">
                                    <label><b style="margin-left: 28px;">From Date<span class="mandat">*</span></b></label>
                                    <input class="calender-field" type="text" placeholder="  DD-MM-YYYY" name="fromDate" value="${filterData.displayFromDate}" id="fromDate" readonly/>
                                </td>
                                <td width="1px">
                                    <label><b style="margin-left: 28px;">To Date<span class="mandat">*</span></b></label>
                                    <input class="calender-field" type="text" placeholder="  DD-MM-YYYY" name="toDate" value="${filterData.displayToDate}" id="toDate" readonly />
                                </td>
                                <td style="padding-left: 25px;" >
                                    <input type="submit" class="gobutton" id="gobutton" align="middle" value="Go"/>
                                    <input type="button" class="exportbutton" id="exportbutton" align="middle" value="Export"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="datadisplay">
                <table style="font-size: 10px;">
                    <thead>
                    <th style="width:130px">Employee Name</th>
                    <th style="width:70px">Timesheet Date</th>
                    <th style="width:60px">Day Name</th>
                    <th style="width:100px">Project Name</th>
                    <th style="width:40px">Attendance Hours</th>
                    <th style="width:40px">Worked Hours</th>
                    <th style="width:120px">Regularization Reason</th>
                    <th style="width:50px">Remarks</th>
                    <th style="width:130px">Reporting Manager</th>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${fn:length(timesheetAttendanceReport)<=0}">
                                <tr  class="row-odd">
                                    <td colspan="13" style="text-align: center;">
                                        No data to display
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <% int i = 0;
                                    int s = 0;
                                %>
                                <c:forEach items="${timesheetAttendanceReport}" var="item">
                                    <% s = i % 2;
                                        if (s == 0) {%>
                                    <tr class="row-odd">
                                        <% } else {%>
                                    <tr class="row-even">
                                        <%}%>
                                        <td> ${employee.employee_name} </td>
                                        <td> ${item.timesheet_Date} </td>
                                        <td> ${item.day_Name} </td>
                                        <td> ${item.project_Name} </td>
                                        <td> ${item.attendance_Hours} </td>
                                        <td> ${item.worked_Hours} </td>
                                        <td> ${item.regularization_Reason} </td>
                                        <td> ${item.remarks} </td>
                                        <td> ${employee.manager_name} </td>
                                    </tr>
                                    <% i = i + 1;%>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
                <c:if test="${paging[0] > 1}">
                    <%@include file="/WEB-INF/jsp/paging.jsp" %>
                </c:if>
            </div>
            <script type="text/javascript">
                var loadingDivObj=(document.all);
                var ns4=document.layers;
                var ns6=document.getElementById&&!document.all;
                var ie4=document.all;
                if (ns4){
                    loadingDivObj=document.loadingDiv;
                }else if (ns6){
                    loadingDivObj=document.getElementById("loadingDiv").style;
                }else if (ie4){
                    loadingDivObj=document.all.loadingDiv.style;
                }

                stopLoading();

                function stopLoading(){
                    if(ns4){loadingDivObj.visibility="hidden";}
                    else if (ns6||ie4) loadingDivObj.display="none";
                }

                function startLoading(){
                    if(ns4){loadingDivObj.visibility="visible";}
                    else if (ns6||ie4) loadingDivObj.display="block";
                }
            </script>
        </div>
    </body>
</html>