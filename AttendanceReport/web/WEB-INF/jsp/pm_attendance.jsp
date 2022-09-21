<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="${pageContext.request.contextPath}/css/pmView.css" rel="stylesheet" type="text/css"/>
         <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <title>PM Attendance Report</title>
        <script>
            function getEmpList(prjId){
             
                $.ajax({
                    type: 'POST',
                    url: 'getEmployeeList.htm?prjId='+prjId,
                    dataType: "html",   
                    success: function(response) {
                        $('#empName').html(response);
                    }
                }); 
            }
            function filterData()
            {  
//                alert("success");
                $('#pm_attendance').attr("action", "getPmTeamAttendance.htm");
                document.pm_attendance.submit();
            }
           
            function getExcelReport() {
                $('#pm_attendance').attr("action", "exportPMAttendanceDetails.htm");
                    document.pm_attendance.submit();
            }
            function loadForm(page)   
            {   
                document.pm_attendance.page.value = page;
                $('#pm_attendance').attr("action", "getPmTeamAttendance.htm?page="+page);
                document.pm_attendance.submit();
            }

        </script>
    </head>
    <body>
        <div id="pm_att_report">
            <div class="main">
                <h2>My Project - Team Attendance&nbsp;<i style="font-size: 14px;">(For HTL-India Locations Only)</i></h2>
                <div class="filter_container">
                    <form id="pm_attendance" name="pm_attendance" action="" method="POST">
                       <input type="hidden" id="page" name="page" value="1" />
                        <div class="row">
                            <div class="row">
                                <div style="float:left; padding-bottom: 20px; font-weight: bold;">
                                    <img style="width:16px; height: auto;" alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" />
                                    <i>Please select a project to view the team attendance.</i>
                                </div>
                            </div>
                            <div class="section_one">
                                <label>Project Name</label><span><b>:</b></span>
                                <select id="projectName" class="dropdown project_name"  name="projectName" onchange="getEmpList(this.value);">

                                    <option value="">-Select Projects-</option>
<!--                                    <option value="allProjects">All Projects</option>-->
                                    <c:forEach items="${projectList}" var="prjList" varStatus="i">
                                        <option value="${prjList.projectId}" ${prjList.projectId eq requestorDTO.project_id ? 'selected="selected"' : ''}>${prjList.project_id} ${prjList.projectName} </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="section_one">
                                <label>Employee Name</label><span><b>:</b></span>
                                <select class="dropdown employee_name" id="empName" name="empName" style="cursor: not-allowed;" disabled>
                                    <option value="">-All Employees-</option>
                                <c:forEach items="${employeeList}" var="empList" varStatus="i">
                                    <option title="${empList.employeeNumber}  ${empList.empName}" value="${empList.empId}" ${empList.empId eq requestorDTO.empId  ? 'selected="selected"' : ''}>${empList.employeeNumber}  ${empList.empName}</option>
                                </c:forEach>
                                </select>
                            </div>

                        </div>
                        <div class="row">
                            <div class="section_three">
                                <div class="section_two">
                                    <label>From Date</label><span><b>:</b></span>
                                    <input type="text" class="datepicker fromDate"  id="fromDate" name="fromDate" value="${fromDate}" placeholder="DD-MM-YYYY" readonly/>
                                </div>
                                <div class="section_two">
                                    <label style="width:70px; text-align: center;">To Date</label><span><b>:</b></span>
                                    <input type="text" class="datepicker toDate" id="toDate" name="toDate" value="${toDate}" placeholder="DD-MM-YYYY" readonly/>
                                </div>
                            </div>
                            <div class="section_four btnContainer">
<!--                                <input type="button" class="goBtn" value="Go" onclick="filterData()"/>-->
                                <input type="button" id="goBtn" class="goBtn" value="Go"/>
                                <input type="button" class="resetBtn" value="Reset" />
                                <input type="button" class="exportBtn" value="Export" onclick="getExcelReport()"/>
                            </div>
                        </div>
                    </form>
                </div>
                <div id="pm_att_report_table_container">
                    <c:if test="${fn:length(attPmReport) > 0}">
                    <table id="pm_att_report_table">
                        <thead>
                        <th style="width: 60px;">Emp ID</th>
                        <th style="width: 90px;">Emp Name</th>
                        <th style="width: 120px;">Project Name</th>
                        <th style="width: 100px;">Reporting Manager</th>
                        <th style="width: 70px;">Unit</th>
                        <th style="width: 100px;">Date</th>
                        <th style="width: 60px;">Status</th>
                        <th style="width: 60px;">In Time</th>
                        <th style="width: 60px;">Out Time</th>
                        <th style="width: 60px;">Time Duration</th>
                        <th style="width: 70px;">Swipe Location</th>
                        <th style="width: 90px;">Work Location</th>
                        </thead>
                        <tbody>
                            <c:forEach items="${attPmReport}" var="attPmReport" >
                                <tr>                                
                                    <td>
                                        ${attPmReport.emp_id}
                                    </td>
                                    <td>
                                        ${attPmReport.emp_name}
                                    </td>
                                    <td>
                                        ${attPmReport.projectName}
                                    </td>
                                    <td>
                                        ${attPmReport.reporting_manager}
                                    </td>
                                    <td>
                                        ${attPmReport.unit}
                                    </td>
                                    <td>
                                        ${attPmReport.attendance_date}
                                    </td>
                                    <td>
                                        ${attPmReport.attendance_status}
                                    </td>
                                    <td>
                                        ${attPmReport.in_time}
                                    </td>
                                    <td>
                                        ${attPmReport.out_time}
                                    </td>
                                    <td>
                                        ${attPmReport.time_duration}
                                    </td>
                                    <td>
                                        ${attPmReport.swipe_location}
                                    </td>
                                    <td>
                                        ${attPmReport.workLocation}
                                    </td>
                                </tr>
                            </c:forEach>
                                </c:if>
                     <c:if test="${paging[0] > 1}">
                           
                            <tr> 
                                  <td colspan="13">
                                    <c:choose>
                                        <c:when test="${paging[1] != 1}">
                                            <a onclick="loadForm(1)" href="javascript:;"><< First</a>&nbsp;&nbsp;
                                            <a onclick="loadForm(${paging[4]})" href="javascript:;">< Previous</a>&nbsp;|&nbsp;
                                        </c:when>
                                        <c:otherwise>
                                            < Previous&nbsp;|&nbsp;
                                        </c:otherwise>
                                    </c:choose>
                                    <c:forEach var="page" begin="${paging[2]}" end="${paging[3]}" step="1" varStatus ="i">
                                        <c:choose>
                                            <c:when test="${page == paging[1]}">
                                                <a class="selected" onclick="loadForm(${page})" href="javascript:;"><b>${page}</b></a>&nbsp;|&nbsp;
                                            </c:when>
                                            <c:otherwise>
                                                <a onclick="loadForm(${page})" href="javascript:;">${page}</a>&nbsp;|&nbsp;
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <c:choose>
                                        <c:when test="${paging[1] != paging[0]}">
                                            <a onclick="loadForm(${paging[5]})" href="javascript:;">Next ></a>&nbsp;&nbsp;
                                            <a onclick="loadForm(${paging[0]})" href="javascript:;">Last >></a>
                                        </c:when>
                                        <c:otherwise>
                                            Next >
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                          
                        </c:if>
                        <c:if test="${paging[0] == 0}">
                            <div>
                                <p style="text-align: center;">No Data to Display</p>
                            </div>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <script>
            $(document).ready(function () {
//                $("#fromDate, #toDate").datepicker({
//                    changeMonth: true,
//                    changeYear: true,
//                    disabled: true,
//                    dateFormat: 'dd-mm-yy'
//                });

//                    $("#fromDate").datepicker({
//                        dateFormat: "dd-mm-yy",
//                        changeMonth: true,
//                        changeYear: true,
//                        disabled: true,
//                //        minDate: "-10y",
//                        maxDate: "+0d",
//                        onSelect: function () {
//                            var dt2 = $('#toDate');
//                            var startDate = $(this).datepicker('getDate');
//                            //add 30 days to selected date
//                            startDate.setDate(startDate.getDate() + 30);
//                            var minDate = $(this).datepicker('getDate');
//                            //minDate of dt2 datepicker = dt1 selected day
//                //            dt2.datepicker('setDate', minDate);
//                            //sets dt2 maxDate to the last day of 30 days window
//                //            dt2.datepicker('option', 'maxDate', startDate);
//                            //first day which can be selected in dt2 is selected date in dt1
//                            dt2.datepicker('option', 'minDate', minDate);
//                            //same for dt1
//                //            $(this).datepicker('option', 'minDate', minDate);
//                        }
//                    });
//                    $('#toDate').datepicker({
//                        dateFormat: "dd-mm-yy",
//                        changeMonth: true,
//                        changeYear: true,
//                        disabled: true,
//                //        minDate: "-10y",
//                        maxDate: "+0d"
//                    });
                    
                     $("#fromDate,#toDate").keydown(function() { 
                        return false;
                    });

                    var aa=$("#fromDate").val();
                    if(aa!=''){
                        aa = aa.split("-");
                      $("#toDate").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,maxDate: '0'});
                      $("#toDate").datepicker("option", "minDate", new Date(aa[2], --aa[1], aa[0]));

                    }else{

                        //alert("empty")
                    }
                    $(".goBtn").click(function(){
                        var frmDt=$("#fromDate").val();
                        var toDate = $("#toDate").val();
                        if(frmDt ===""){
                            alert("Please select From Date");
                            return false;
                        }
                        if(toDate ===""){
                            alert("Please select To Date");
                            return false;
                        }
                        else
                        {
                            filterData();
                        }
                    });
                    
                    $( "#toDate" ).click(function() {
                    var frmDt=$("#fromDate").val();
                    if(frmDt===""){
                        alert("Please select From Date");
                    }else{

                    }
                    });

                    $('#fromDate').datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,minDate: '01-07-2016',maxDate: '0',
                        onSelect: function(dateText, inst) {
                            var selectedDate = dateText.split("-");
                            $("#toDate").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,maxDate: '0'});
                            $("#toDate").datepicker("option", "minDate", new Date(selectedDate[2], --selectedDate[1], selectedDate[0]));
                            setTimeout(function(){
                                $( "#toDate" ).datepicker('show');
                            }, 16);     
                        }});
                //});
                    
                    //On page load, preventing the employee field from disabled
                    var proj_val = $("#projectName").val();
                    if(proj_val !=='')
                    {
                        $("#empName").removeAttr("disabled");
                        $("#empName").css("cursor", "default");
                    }
                    
                    $("#projectName").change(function(){
                       var proj_val = $(this).val();
                       if(proj_val !=='')
                       {
                           $("#empName").removeAttr("disabled");
                           $("#empName").css("cursor", "default");
                       }
                       else
                       {
                           $("#empName").attr("disabled", "true");
                           $("#empName").css("cursor", "not-allowed");
                       }
                    });
                    
                    $(".resetBtn").click(function(){
                        $("#pm_attendance").find("select, input[type=text]").val("");
                        $("#empName").attr("disabled", "true");
                        $("#empName").css("cursor", "not-allowed");
                    });
            });
        </script>
    </body>
</html>
