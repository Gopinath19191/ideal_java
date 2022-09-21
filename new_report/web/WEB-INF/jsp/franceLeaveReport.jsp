<%-- 
    Document   : franceLeaveReport
    Created on : Aug 16, 2018, 12:52:29 PM
    Author     : 16656
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>France Leave Reports</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
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
            width: 50px;
            margin-left: 10px;
            cursor: pointer;
        }
        form label { width: 118px !important; font-size: 14px; }
        .france_employee{ width: 185px; height: 21px; margin-left: -6px; }
        .calendar{ margin-left: 15px; }
        .calender-field1{ height: 21px; margin-left: -45px; width: 120px; }
        .calender-field2{ height: 21px; margin-left: -62px; width: 120px; }
        .exportbutton{ margin-left: 25px; cursor: pointer; }
    </style>
    
    <script type="text/javascript">
        function getExcelReport(){
            $('#reportPage').attr("action", "getfranceLeaveReportXL.htm");
            document.reportPage.submit();
        }
        function getFilterList(){
//            var date_from = $("#atartDate").val()
//            if(date_from == null || date_from === ''){
//                alert("")
//            }
            $('#reportPage').attr("action", "franceLeaveReport.htm");
            document.reportPage.submit();
            startLoading();
        }
        function loadForm(page) {
            $('#page').val(page);
            $('#reportPage').attr("action", "franceLeaveReport.htm");
            document.reportPage.submit();
            startLoading();
        }
        
        $(document).ready(function(){
            $("#atartDate").datepicker({
                changeMonth: true,
                changeYear: true,
                minDate:'01-01-2017',
                maxDate: '+1Y',
                dateFormat: 'dd-mm-yy',
                onSelect: function () {
                    $("#endDate").datepicker( "destroy" );
                    var date_format = $("#atartDate").val().split('-');
                    var from_date_new = date_format[2]+'-'+date_format[1]+'-'+date_format[0];
                    $("#endDate").datepicker({
                        changeMonth: true,
                        changeYear: true,
                        dateFormat: 'dd-mm-yy',
                        minDate: new Date(from_date_new),
                        maxDate: '+1Y'
                    });
                    $('#endDate').val('');
                }
            });
            if($("#atartDate").val()){
                var date_format = $("#atartDate").val().split('-');
                var from_date_new = date_format[2]+'-'+date_format[1]+'-'+date_format[0];
                $("#endDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'dd-mm-yy',
                    minDate: new Date(from_date_new),
                    maxDate: '+1Y'
                });
            }
            $("#endDate").click(function(){
                if(!$("#endDate").hasClass('hasDatepicker')){
                    $("#atartDate").focus();
                }
            });
            
            $(".france_employee").autocomplete("ajaxsearchFranceEmp.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            }); 
            $(".france_employee").result(function(event, data, formatted) {
                if (data) {
                    $("#emp_id").val(data[1]);
                }
            });
        });
    </script>
    
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    France Leave Report
                </div>
            </div>

            <div class="tabletools" >
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <input type="hidden" id="emp_id" name="employee_no" value="${filterData.employee_no != '' && filterData.employee_no != null?filterData.employee_no:''}" />
                    <input type="hidden" id="page" name="page" value="1">
                    <table id="searchForm">
                        <tbody>
                            <tr>
                                <td>
                                    <label><b>Employee Name :</b> </label>
                                </td>
                                <td width="150px">
                                    <input type="text" id="test" class="france_employee" name="employee_name" value="${filterData.employee_no != '' && filterData.employee_no != null?filterData.employee_name:''}" 
                                           placeholder=" Seartch By Employee" autocomplete="off" />
                                </td>
                                <td><label class="calendar"><b>From Date :</b></label></td>
                                <td>
                                    <input class="calender-field1" type="text" placeholder="  DD-MM-YYYY" name="atartDate" value="${filterData.displayFromDate}" id="atartDate" readonly/>
                                </td>
                                <td><label class="calendar"><b>To Date :</b></label></td>
                                <td>
                                    <input class="calender-field2" type="text" placeholder="  DD-MM-YYYY" name="endDate" value="${filterData.displayToDate}" id="endDate" readonly/>
                                </td>
                                <td style="padding-left: 25px;" >
                                    <input type="submit" class="gobutton" align="middle" onclick="getFilterList()" value="Go"/>
                                </td>
                                <td>
                                    <input type="button" class="exportbutton" align="middle" onclick="getExcelReport()" value="Export"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="datadisplay">
                <table style="font-size: 10px;">
                    <thead>
                    <!--<th>Employee No.</th>-->
                    <th style="width:150px">Employee Name</th>
                    <th style="width:70px">Leave Type</th>
                    <!--<th style="width:160px">Leave Reason</th>-->
                    <th style="width:70px">Applied Date</th>
                    <th style="width:70px">Date Approved</th>
                    <th style="width:70px">Start Date</th>
                    <th style="width:70px">End Date</th>
                    <!--<th style="width:40px">Half Day</th>-->
                    <th style="width:30px">Duration</th>
                    <th style="width:50px">Leave Status</th>
                    <th style="width:150px">Approved By</th>
                    <!--<th style="width:60px">Reason for Rejection</th>-->
                    </thead>
                    <tbody>
                        <c:if test="${fn:length(franceLeave)>0}">
                        <% int i = 0;
                            int s = 0;
                        %>
                        <c:forEach items="${franceLeave}" var="item">
                            <% s = i % 2;
                                       if (s == 0) {%>
                            <tr class="row-odd">
                                <% } else {%>
                            <tr class="row-even">
                                <%}%>
<!--                                <td> ${item.empNumber} </td>-->
                                <td> ${item.empName} </td>
                                <td> ${item.leave_type} </td>
<!--                                <td> ${item.leave_reason} </td>-->
                                <td> ${item.appliedDate} </td>
                                <td> ${item.date_approved} </td>
                                <td> ${item.atartDate} </td>
                                <td> ${item.endDate} </td>
<!--                                <td> ${item.half_day} </td>-->
                                <td> ${item.duration} </td>
                                <td> ${item.leave_status} </td>
                                <td> ${item.approvedBy} </td>
<!--                                <td> ${item.reason_for_rejection} </td>-->
                            </tr>
                            <% i = i + 1;%>
                        </c:forEach>
                        </c:if>
                            <c:if test="${fn:length(franceLeave)==0}">
                                <tr  class="row-odd">
                                    <td colspan="13" style="text-align: center;">
                                        No data to display
                                    </td>
                                </tr>
                            </c:if>
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

