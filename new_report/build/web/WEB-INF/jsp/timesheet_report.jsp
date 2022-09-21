<%-- 
    Document   : timesheet_report
    Created on : 15 Mar, 2017, 11:35:41 AM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header1.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> TimeSheet Report</title>
        <style type="text/css">
          
            .required {
                font-weight: bold;
                color :red;
            }
            #prjDetails{
                border: 1px solid #bbbbbb;
                width:98%;
                margin:10px;
                border-collapse: collapse;
            }
            #prjDetails tbody tr td{
                width:25%;
                padding:3px;
                border:1px solid #bbbbbb;
            }
            span.ui-icon.ui-icon-circle-triangle-w,span.ui-icon.ui-icon-circle-triangle-e{
                text-indent: 0px;
                font-size: 8px;
                width: 22px;
                padding: 3px 0px 0px 0px;
            }
            .tableStructure tr th, .tableStructure tr td{
                padding:3px;
                border: 0px;
            }
            #ui-datepicker-div{
                display:none;
            }
            .gobutton{
                border: 1px solid #BCCFEA;
                background: none repeat scroll 0 0 #316ca8;
                color: #FFFFFF;
                float: left;
                font-weight: bold;
                height: 32px;
                margin: 0px;
                width: 50px;
            }

        </style>
    </head>
    <body>
        <script>
 
            var prjId = ${requsetorDTO.projectId};
            $.ajax({
                type: 'POST',
                url: 'getPrjDetails.htm?prjId=' + prjId,
                dataType: "html", 
                success: function(response) {
                    $('#prjDetails').html(response);
                }
            });
            
        </script>
        <div class="container_inner" style="margin: 35px auto 0px;width: 1000px;">
            <div class="page_heading">
                 Timesheet Delinquency Report
            </div>

            <div class="notice_page" style="width:990px">
                <div style="padding-left:20px;">
                    <ul class="notice_page_ul">
                        <li>Delinquency report is sum of all the timesheet entered in project and non project activities</li>
<!--                        <li>Delinquency report for RM is sum of all the timesheet entered in project and non project activities</li>
                        <li>Delinquency report for PM is sum of all the timesheet entered in project and non project activities</li>-->
                    </ul>
                </div>
            </div>
        </div>

        <form action="employeeTimesheetReport.htm" id="timesheetReport" name="timesheetReport" method="POST">
            <input type="hidden" id="prjId" name="prjId" value=""/>
            <input type="hidden" readonly name="page" id="page" value="1"  />
            <div class="searchBox" style="margin:  0px -121px 10px;width: 1010px;margin: 0px auto;box-sizing: border-box;">

                <table width="100%" border="0">
                    <tbody>
                       <tr>
                        <td width="10%" style="padding-left:10px;">From Date<span style="color:red;">*</span></td>
                        <td><input id="fromDate" name="fromDate" value="${fromDate}" style="width: 70px;" readonly/></td>
                        <td width="10%">To Date<span style="color:red;">*</span></td>
                        <td><input class="toDate" id="toDate" name="toDate" value="${toDate}" style="width: 70px;" readonly/>   </td>

                        
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="contentwrap" style="margin: 0px -121px;width: 1010px;margin: 10px auto;"> 

                <table class="tableStructure" style="border: 0" >
                    <tbody>
                        <c:if test="${fn:length(structure)>0}">
                            <tr>
                                <td width="13%" style="padding-left:10px; font-size: 14px;">Select Unit<span style="color:red;">*</span></td>
                                <td width ="10%">
                                    <select name="company_structure_id" class="company_structure" id="company_structure"> 
                                        <option value="ALL"> -- ALL -- </option>
                                        <c:forEach items="${structure}" var="structure" >
                                            <option value="${structure.company_structure_name}">${structure.company_structure_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="button" id="searchData" name="searchData" class=" toolfind" value="Export" style="margin-top: 3px; display: inline-block; float:left; background-color: #4C83B3;color:#fff;" />
                                </td>
                            </tr>
                            <tr height="20px">

                            </tr>
                        </c:if>
                        
                        <c:if test="${fn:length(employeeList)>0}">
                            <tr>
                                <td width="13%" style="padding-left:10px; font-size: 14px;">Select Employee</td>
                                <td width ="10%">
                                    <input type="hidden" name="reporting_manager_id" id="reporting_manager_id" value="${reportingManagerId}" />
                                    <select name="employee_id" class="employee_id" id="employee_id"> 
                                        <option value=""> -- Select Employee -- </option>
                                        <c:forEach items="${employeeList}" var="employeeList" >
                                            <option value="${employeeList.employee_id}">${employeeList.employee_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="button" id="searchreportingData" name="searchData" class=" toolfind" value="Export" style="margin-top: 3px; display: inline-block; float:left; background-color: #4C83B3;color:#fff;" />
                                </td>
                            </tr>
                            <tr height="20px">

                            </tr>
                        </c:if>
                        <c:if test="${fn:length(projectList)>0}">
                            <tr>
                                <td width="13%" style="padding-left:10px; font-size: 14px;">Select Project<span style="color:red;">*</span></td>
                                <td width ="10%">
                                    <select name="project_id" class="project_id" id="project_id" style ="width:100%"> 
                                        <option value=""> -- ALL -- </option>
                                        <c:forEach items="${projectList}" var="projectList" >
                                            <option value="${projectList.project_id}">${projectList.project_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="button" id="searchProjectData" name="searchData" class=" toolfind" value="Export" style="margin-top: 3px; display: inline-block; float:left; background-color: #4C83B3;color:#fff;" />
                                </td>
                            </tr>
                        </c:if>
                        
                        <input type="hidden" name="button_value" id="button_value" value="" />           
                    </tbody>
                </table>
            </div>
        </form>
        <!--        </div>-->
        <script>
            $(document).ready(function() {
           // $( document ).on( "focus", "input.date:not(.hasDatepicker)", function() {
                $("#fromDate,#toDate").keydown(function() { 
                    return false;
                });
                
                var aa=$("#fromDate").val();
                if(aa!=''){
                    aa = aa.split("-");
                  $("#toDate").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear: true,maxDate: '0'});
                  $("#toDate").datepicker("option", "minDate", new Date(aa[2], --aa[1], aa[0]));
                     
                }else{
                   
                    //alert("empty")
                }
                  
                $( "#toDate" ).click(function() {
                    var frmDt=$("#fromDate").val();
                    if(frmDt==""){
                        alert("Please select From Date");
                    }else{

                    }
                });
                $( "#searchData" ).click(function() {
                    var unit=$("#company_structure").val();
                    var fromDate=$("#fromDate").val();
                    var toDate=$("#toDate").val();
                    if(unit==""){
                        alert("Please select any Unit");
                    }else if(fromDate==""){
                        alert("Please select From Date");
                    }else if(toDate==""){
                        alert("Please select To Date");
                    }else{
                        $("#button_value").val("searchData");
//                        $("#searchData").hide();
//                        $("#searchreportingData").hide();
//                        $("#searchProjectData").hide();
                        $("#timesheetReport").submit();
                    }
                });
                
                $( "#searchreportingData" ).click(function() {
                    var fromDate=$("#fromDate").val();
                    var toDate=$("#toDate").val();
                    if(fromDate==""){
                        alert("Please select From Date");
                    }else if(toDate==""){
                        alert("Please select To Date");
                    }else{
                        $("#button_value").val("searchreportingData");
//                        $("#searchData").hide();
//                        $("#searchreportingData").hide();
//                        $("#searchProjectData").hide();
                        $("#timesheetReport").submit();
                    }
                });
                
                $( "#searchProjectData" ).click(function() {
                    var fromDate=$("#fromDate").val();
                    var toDate=$("#toDate").val();
                    var prjtid=$("#project_id").val();
                    if(fromDate==""){
                        alert("Please select From Date");
                    }else if(toDate==""){
                        alert("Please select To Date");
                    }else if(prjtid==""){
                        alert("Please select Project");
                    }else{
                        $("#button_value").val("searchProjectData");
//                        $("#searchData").hide();
//                        $("#searchreportingData").hide();
//                        $("#searchProjectData").hide();
                        $("#timesheetReport").submit();
                    }
                });
                
                
                
                $('#fromDate').datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear: true,minDate: '2016-04-01',maxDate: '0',
                    onSelect: function(dateText, inst) {
                        $("#toDate").val("");
                        var minDate = $(this).datepicker('getDate') || new Date();
                        var maxDate = new Date(minDate.getTime());
                        var currentDate = new Date();
                        var maximunDate = new Date(maxDate.setMonth(maxDate.getMonth() + 2));
                        if(currentDate < maximunDate){
                            maxDate = currentDate;
                        }else{
                            //maxDate.setMonth(maxDate.getMonth() + 2);
                        }
                        $("#toDate").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear: true});
                        $("#toDate").datepicker("option", "minDate", minDate);
                        $("#toDate").datepicker("option", "maxDate", maxDate);
                        setTimeout(function(){
                            $( "#toDate" ).datepicker('show');
                        }, 16);     
                    }});
            });
            //    $('#fromDate').datepicker({dateFormat: "dd-mm-yy", maxDate:new Date()});
            //            $('#toDate').datepicker({dateFormat: 'dd-mm-yy', maxDate:new Date()});
        </script>

        
    </body>
</html>

