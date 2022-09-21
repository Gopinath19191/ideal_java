<%-- 
    Document   : approval
    Created on : Jul 4, 2012, 4:40:04 PM
    Author     : 16364
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
            }
            #ui-datepicker-div{
                display:none;
            }


        </style>
    </head>
    <body>
        <script>
 
//            var prjId = ${requsetorDTO.projectId};
//            $.ajax({
//                type: 'POST',
//                url: 'getPrjDetails.htm?prjId=' + prjId,
//                dataType: "html", 
//                success: function(response) {
//                    $('#prjDetails').html(response);
//                }
//            });
            
        </script>
        <div id="loadingDiv" style="position:absolute;width:100%;height:1500px;z-index:40;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div align="center" style="margin:15px 0px -20px 0px;"><font color="green" size="3">${success_msg}</font></div>
        <div class="container_inner" style="margin: 35px auto 0px;width: 1000px;">
            <div class="page_heading">
                 Timesheet Entries Report
<!--                <div style="float: right;color: #4C83B3;font-weight: bold;font-size: 12px;">
                    <img src="/iDeal_timesheet/images/icon_list.png" title="List Pending  Request" alt="List Pending Request">
                    <a style="text-decoration:none;color: #4C83B3;"  target="_self" href="pmApproval.htm?list=pending&page=1">List Pending Request</a>
                    <img src="/iDeal_timesheet/images/icon_list.png" title="List Processed Request" alt="List Processed  Request">
                    <a  style="text-decoration:none;color: #4C83B3;"  target="_self" href="pmApproval.htm?list=processed&page=1">List Processed Request</a>
                </div>-->
            </div>

            <div class="notice_page" style="width:990px">
               
                <div style="padding-left:20px;">
                    <ul class="notice_page_ul">
                       <li>Project & Project Role need to get approval from finance for timesheet approval</li>
                        <li>Use Chrome,Mozilla 48.0 and above browser</li>
                        <li style="color: #1e1467;font-style: italic;" >Listing all Timesheets of an Employee - In Employee List Box please select one employee to list all timesheets entered by the employee in both project and non project mode</li>
                    </ul>
                </div>
            </div>

<!--            <div class="page_heading" style="margin-left: 43%;color: #4C83B3;color: #4C83B3;">
               <%-- <c:choose>
                    <c:when test="${selectedList=='pending'}">
                        Pending List
                    </c:when>
                    <c:otherwise>
                        Processed List
                    </c:otherwise>
                </c:choose> --%>
            </div>-->
        </div>

        <form id="timesheetApproval" name="timesheetApproval" action="" method="POST">
            <input type="hidden" id="prjId" name="prjId" value=""/>
            <input type="hidden" readonly name="page" id="page" value="1"  />
            <div class="searchBox" style="margin:  0px -121px 10px;width: 1010px;margin: 0px auto;box-sizing: border-box;">

                <table width="100%" border="0">
                    <tbody>
                        <tr>
                            <td width="12%" style="padding-left:10px;">Project Name<span style="color:red;">*</span></td>
                    <input type="hidden" id="prjId" name="prjId" value="${requsetorDTO.projectId}" />
                     <input type="hidden" id="selectedPrj" name="selectedPrj" value="${tableHeader}" />
                    <input type="hidden" id="typeOfList"name="typeOfList" value="${selectedList}">
                    <td><select id="projectName" name="projectName" style="margin-top: 5px; width: 150px;" onchange="getEmpList(this.value,document.getElementById('typeOfList').value);">

                            <option value="">-      Select Project  -</option>
                            <c:forEach items="${projectList}" var="prjList" varStatus="i">
                                 
                                <option value="${prjList.projectId}" ${prjList.projectId eq requsetorDTO.projectId ? 'selected="selected"' : ''}>${prjList.projectCode}--${prjList.projectName}</option>
                                
                            </c:forEach>
                            <option value="Non_Project_Activity" ${requsetorDTO.projectId eq 'Non_Project_Activity' ? 'selected="selected"' : ''}>Non Project Activity</option>
                        </select>
                    </td>

                    <td width="12%">Employee Name</td>
                    <td><select id="empName" name="empName" style="margin-top: 5px;width: 150px;">

                            <option value="">-  Select Employee   -</option>
                            <c:forEach items="${employeeList}" var="empList" varStatus="i">
                                <option title="${empList.employeeNumber}  ${empList.empName}" value="${empList.empId}" ${requsetorDTO.empId eq empList.empId ? 'selected="selected"' : ''}>${empList.employeeNumber}  ${empList.empName}</option>
                            </c:forEach>
                        </select>
                    </td>
                    
                    </tr>
                    <tr>
                        <td width="10%" style="padding-left:10px;">From Date<span style="color:red;">*</span></td>
                        <td><input id="fromDate" name="fromDate" value="${fromDate}" style="width: 70px;" readonly/></td>
                        <td width="10%">To Date<span style="color:red;">*</span></td>
                        <td><input class="toDate" id="toDate" name="toDate" value="${toDate}" style="width: 70px;" readonly/>   </td>

                        <td><input type="hidden" id="approveType" name="approveType" value="${selectedList}" /></td>
                        <td style="float:right; padding-right:60px;">
                            <input type="button" id="search" name="search" class=" toolfind" value="Go" onclick="filterData(document.getElementById('projectName').value, document.getElementById('fromDate').value, document.getElementById('toDate').value,getPrjDetails(this.value));" style="margin-top: 3px; float:left;display: inline-block;background-color: #4C83B3;color:#fff;" />
                             <input type="button" id="searchA" name="searchA" class=" toolfind" value="Export" onclick="filterDataExport(document.getElementById('projectName').value, document.getElementById('fromDate').value, document.getElementById('toDate').value,getPrjDetails(this.value));" style="margin-top: 3px; float:left;display: inline-block;background-color: #4C83B3;color:#fff;" />
                            <c:choose>
                                <c:when test="${selectedList == 'pending'}">
                                    <a href="pmApproval.htm?list=pending&page=1" style="text-decoration: none;margin-top: 3px;display: inline-block;background: none repeat scroll 0 0 #FFFFFF;  border: 1px solid #BCCFEA; color: #4D85B8; float: right; font-weight: bold; height: 23px;margin: 3px 0 0 10px;width: 45px; text-align: center;line-height: 23px;background-color: #4C83B3;color:#fff;cursor: default;">Reset</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="pmApproval.htm?list=processed&page=1" style="text-decoration: none;margin-top: 3px;display: inline-block;background: none repeat scroll 0 0 #FFFFFF;  border: 1px solid #BCCFEA; color: #4D85B8; float: right; font-weight: bold; height: 23px;margin: 3px 0 0 10px;width: 45px; text-align: center;line-height: 23px;background-color: #4C83B3;color:#fff;cursor: default;">Reset</a>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        
                        
                    </tr>
                    </tbody>
                </table>
            <%--    <c:if test="${requsetorDTO.projectId != null }">
                    <c:if test="${requsetorDTO.projectId != 'Non_Project_Activity' }">
                        <table id="prjDetails">

                            <tr>
                                <td> Customer Name</td>
                                <td></td>
                                <td> Project Value</td>
                                <td></td>
                            </tr>
                            <tr>
                                <td> Project Code</td>
                                <td></td>
                                <td> Currency</td>
                                <td></td>
                            </tr>
                        </table>
                    </c:if>
                </c:if>--%>
            </div>
            <div class="contentwrap" style="margin: 0px -121px;width: 1010px;margin: 10px auto;">
                <%--<c:if test="${selectedList == 'pending'}">
                    <c:if test="${fn:length(approverList) != 0}">
                        <div class="tabletools" >
                            <div class="toolsbuttonwrap">
                                <input type="button" name="save" id="save" value="Save" class="approverqualitysave" onclick="formSave();"/>
                                <input type="button" name="approve" id="approve" value="Approve" class="submitbutton" onclick="formSubmit('a');"/>
                                <input type="button" name="reject" id="reject" value="Send Back" class="cancelbutton" onclick="formSubmit('r');"/>
                            </div>
                        </div>
                    </c:if>
                </c:if> --%>    

                <table class="tableStructure" style="border: 0" >
                    <thead>

                       
                        <c:choose>
                            <c:when test="${tableHeader == 'Non_Project_Activity'}">
                             
                            <th>Project Name</th>
                            <th>Project Code</th>
                            <th>Project Pricing</th>
                            <th>Employee Name</th>
                            <th>Timesheet Date</th>
                            <th>Role</th>
                            <th>Regularization Reason</th>
                            <th>Phase</th>
                            <th>Task</th>
                            <th>Available Hrs</th>
                            <th>Attended Hrs</th>
                            <th>Timesheet Hours</th>
                            <th>Approved Hours</th>
                            <th>Status</th>
                           
                        </c:when>
                        <c:otherwise>
                            
                            <th>Project Name</th>
                            <th>Project Code</th>
                            <th>Project Pricing</th>
                            <th>Employee Name</th>
                            <th>Timesheet Date</th>
                            <th>Role</th>
                            <th>Regularization Reason</th>
                            <th>Phase</th>
                            <th>Task</th>
                            <th>Available Hrs</th>
                            <th>Attended Hrs</th>
                           <th>Timesheet Hours</th>
                            <th>Approved Hours</th>
                            <th>Status</th>
                          
                        </c:otherwise>
                    </c:choose>
                    </thead>
                    <tbody>
                        <c:choose>
                            <c:when test="${fn:length(approverList) != 0}">
                                <c:forEach items="${approverList}" var="dashBoardList" varStatus="rpritr"> 

                                    <c:if test="${dashBoardList.entryStatus != 'r'}">
                                        <c:choose>
                                               <c:when test="${dashBoardList.projectName eq 'Non_Project_Activity'}"> 
                                                <tr  id ="chk_data_td_${rpritr.index}" bgcolor="#B0E0E6" >
                                                </c:when>
                                               <c:when test="${not empty dashBoardList.regReason }"> 
                                                <tr id ="chk_data_td_${rpritr.index}" bgcolor="#ffeacc" >
                                                </c:when>
                                                <c:otherwise>
                                                <tr id ="chk_data_td_${rpritr.index}">
                                                </c:otherwise>
                                            </c:choose>
                                           
                                                <c:choose>
                                                    <c:when test="${tableHeader == 'Non_Project_Activity'}">
                                                   <td>${dashBoardList.projectName}</td>
                                                <input type="hidden" value="${dashBoardList.projectName}" id="prjNamenon_${rpritr.index}"/>
                                             <td>${dashBoardList.projectCode}</td>
                                            <td>${dashBoardList.pricingType}</td>
                                                        
                                                    <td>${dashBoardList.employeeNumber}  ${dashBoardList.employeeName}</td>
                                                    <td>${dashBoardList.timesheetDate} ${workedHrs}</td>
                                                         <input type="hidden" class="timeClass${workedHrs}${dashBoardList.employeeNumber} timeclassnew" id="timesheetDate_${rpritr.index}" name="timesheetDate_${rpritr.index}" />
                                                   <td><label style="max-width: 70px !important;display: inline-block;word-wrap: break-word;"><c:choose><c:when test="${dashBoardList.roleName != null}">
                                                        ${dashBoardList.roleName}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ---  
                                                    </c:otherwise>
                                                </c:choose>
                                                    </label>
                                                   </td>
                                                    <td> ${dashBoardList.regReason}</td>
                                            <input type="hidden" value="${dashBoardList.regReason}" name="regReason" id="regReason_${rpritr.index}"/>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${dashBoardList.phaseName != null}">
                                                        ${dashBoardList.phaseName}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ---  
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${dashBoardList.taskNamee != null}">
                                                        ${dashBoardList.taskNamee}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ---  
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td style="text-align:center;">${dashBoardList.availableHours}</td>
                                            <td style="text-align:center;">
                                                <c:set var="timeParts" value="${fn:split(dashBoardList.attendance_hours, ':')}" />
                                                <c:choose>
                                                    <c:when test="${dashBoardList.attendance_hours!=null}">
                                                        ${timeParts[0]}:${timeParts[1]}
                                                    </c:when>
                                                </c:choose>
                                            </td>
                                            <td>${dashBoardList.workedHours}</td>
                                            <c:set var="string4" value="${fn:split(dashBoardList.approvedHrs, ':')}" />
                                           
                                            <td style="text-align:center;">
                                                <c:choose>
                                                    <c:when test="${dashBoardList.projectName eq 'Non_Project_Activity'}">
                                                        <c:choose>

                                                    <c:when test="${dashBoardList.entryStatus != 'a'}">

                                                       ${dashBoardList.approvedHrs}
                                                    </c:when>
                                                    <c:otherwise>
                                                         ${dashBoardList.approvedHrs}
                                                    </c:otherwise>
                                                    </c:choose>
                                                    </c:when>
                                                    <c:otherwise>
                                                       ${dashBoardList.approvedHrs}
                                                      
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                ${dashBoardList.statuss}
                                            </td>
                                            
                                        </c:when>
                                        <c:otherwise>
                                            <td >${dashBoardList.projectName}</td>
                                            <input type="hidden" value="${dashBoardList.projectId}" id="prjName_${rpritr.index}"/>
                                            <td>${dashBoardList.projectCode}</td>
                                            <td>${dashBoardList.pricingType}</td>
                                            <td>${dashBoardList.employeeNumber}  ${dashBoardList.employeeName}</td>
                                             <input type="hidden" value="${dashBoardList.employeeNumber}" id="empNumber_${rpritr.index}"/>
                                            <input type="hidden" value="${dashBoardList.employeeNumber}" id="empNumber_${rpritr.index}"/>
                                         <td>${dashBoardList.timesheetDate}${workedHrs}</td>
                                                <input type="hidden" class="timeClass${workedHrs}${dashBoardList.employeeNumber}" id="timesheetDate_${rpritr.index}" name="timesheetDate_${rpritr.index}"  />
                                            <td><label style="max-width: 70px !important;display: inline-block;word-wrap: break-word;"><c:choose><c:when test="${dashBoardList.roleName != null}">
                                                        ${dashBoardList.roleName}
                                                    </c:when>
                                                    <c:otherwise>
                                                        ---  
                                                    </c:otherwise>
                                                </c:choose>
                                            </label></td>
                                            <td> ${dashBoardList.regReason}</td>
                                            <input type="hidden" value="${dashBoardList.regReason}" name="regReason" id="regReason_${rpritr.index}"/>
                                            <td>${dashBoardList.phaseName}</td>
                                            <td>${dashBoardList.taskNamee}</td>
                                            <td style="text-align:center;">${dashBoardList.availableHours}</td>
                                            <td style="text-align:center;">
                                                <c:set var="timeParts" value="${fn:split(dashBoardList.attendance_hours, ':')}" />
                                                <c:choose>
                                                    <c:when test="${dashBoardList.attendance_hours!=null}">
                                                        ${timeParts[0]}:${timeParts[1]}
                                                    </c:when>
                                                </c:choose>
                                            </td>
<%--                                            <td>${dashBoardList.locationName}</td>--%>
                                            <td>${dashBoardList.workedHours}</td>

                                            <c:set var="string4" value="${fn:split(dashBoardList.approvedHrs, ':')}" />
                                           
                                            <td style="text-align:center;">
                                                <c:choose>
                                                    <c:when test="${dashBoardList.projectName eq 'Non_Project_Activity'}">
                                                       ${dashBoardList.approvedHrs}
                                                      
                                                    </c:when>
                                                    <c:otherwise>
                                                      <c:choose>

                                                    <c:when test="${dashBoardList.entryStatus != 'a'}">

                                                       ${dashBoardList.approvedHrs}
                                                      
                                                    </c:when>
                                                    <c:otherwise>
                                                         ${dashBoardList.approvedHrs}
                                                    </c:otherwise>
                                                    </c:choose>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                ${dashBoardList.statuss}
                                            </td>
                                            </tr>

                                        </c:otherwise>
                                    </c:choose>
                                </c:if>
                           <%--     <input type="hidden" id="selMonth" name="selMonth" <fmt:formatDate value="${dashBoardList.timesheetDate}" var="workedMonth" pattern="MM" /> value="${workedMonth}"/>
                                <input type="hidden" id="selYear" name="selYear" <fmt:formatDate value="${dashBoardList.timesheetDate}" var="workedYear" pattern="yyyy" /> value="${workedYear}"/>  --%>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <script>
                                document.getElementById("chk_all").style.visibility = "hidden";
                            </script>
                            <tr class="odd">
                                <td colspan="17" style="text-align: center">
                                    <script>
                                        document.getElementById("chk_all").style.visibility = "hidden";
                                        document.getElementById("approve").style.visibility = "hidden";
                                        document.getElementById("reject").style.visibility = "hidden";  
                                        document.getElementById("save").style.visibility = "hidden";  
                                    </script>
                                    No Data To Display
                                </td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" id="actionValue" name="actionValue" value=""/>
                    </tbody>
                 
                       <c:if test="${paging[0] > 1}">
                           
                            <tr>  <c:choose>
                                    <c:when test="${tableHeader == 'Non_Project_Activity'}">
                                        <td colspan="12">
                                        </c:when>
                                        <c:otherwise>
                                        <td colspan="13">
                                        </c:otherwise>
                                    </c:choose>
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
                   
                 <%--   <c:if test="${selectedList == 'pending'}">
                        <tr class="headerCenter">
                            <c:choose>
                                <c:when test="${tableHeader == 'Non_Project_Activity'}">
                                    <th colspan="13" align="right">Total Hours</th>
                                </c:when>
                                <c:otherwise>
                                    <th colspan="13" align="right">Total Hours</th>
                                </c:otherwise>
                            </c:choose>



                            <td style="text-align:center;">

                                <input type="text" name="worked_hours" id="worked_hours" value="" style="width:20px;font-size:12px;" readonly />&nbsp;:

                                <input type="text" name="worked_minutes" id="worked_minutes" value="" style="width:15px;font-size:12px;" readonly />

                            </td>
                            <th colspan="5"></th>
                        </tr> 
                    </c:if>--%>
                    <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
                </table>

               <%-- <c:if test="${selectedList == 'pending'}">
                    <c:if test="${fn:length(approverList) != 0}">
                    <div class="tabletools">
                            <div class="toolsbuttonwrap">
                                <input type="button" name="save" id="save1" value="Save" class="approverqualitysave" onclick="formSave();"/>
                                <input type="button" name="approve" id="approve1" value="Approve" class="submitbutton"  onclick="formSubmit('a');"/>
                                <input type="button" name="reject" id="reject1" value="Send Back" class="cancelbutton" onclick="formSubmit('r');"/>
                            </div>
                    </div>
                    </c:if>
                </c:if>--%>
            </div>
        </form>
    <html>
         <div id="checkProject" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkProjectFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select project name<br></p><p class="plainButton" id="checkProjectYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="checkFromDate" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkFromDateFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select From Date<br></p><p class="plainButton" id="checkFromDateYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="checkToDate" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkToDateFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select To Date<br></p><p class="plainButton" id="checkToDateYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
         <div id="checkRemarks" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkRemarksFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Enter remarks to send back the Timesheet<br></p><p class="plainButton" id="checkRemarksYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="checkemptyCount" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkemptyCountFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Approved Hours should not be empty/'00' !<br></p><p class="plainButton" id="checkemptyCountYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
         <div id="checkhrsCount" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkhrsCountFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Approved Hours should not be greater than or equal to 24 for a day.<br></p><p class="plainButton" id="checkhrsCountYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
         <div id="checkminsCount" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkminsCountFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Approved Minutes should not be greater than 59<br></p><p class="plainButton" id="checkminsCountYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="confirmBox" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="confirmBoxFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">You have selected regularization entries too.Are you sure to approve regularization entries ?<br></p><p class="plainButton" id="confirmBoxNo" style="margin: 0px 10px 10px 10px;">No</p><p class="plainButton" id="confirmBoxYes" style="margin: 0px 10px 10px 10px;">Yes</p>
                </div>
            </div>
        </div>
         <div id="checkBoxApprove" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkBoxApproveFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select check-box before submit the Time sheet<br></p><p class="plainButton" id="checkBoxApproveYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="checkBoxSave" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkBoxSaveFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select checkbox<br></p><p class="plainButton" id="checkBoxSaveYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
    </html>
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
                  $("#toDate").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,maxDate: '0'});
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
                
                $('#fromDate').datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,minDate: '01-04-2015',maxDate: '0',
                    onSelect: function(dateText, inst) {
                        var selectedDate = dateText.split("-");
                        $("#toDate").datepicker({dateFormat: 'dd-mm-yy', changeMonth: true, changeYear: true,maxDate: '0'});
                        $("#toDate").datepicker("option", "minDate", new Date(selectedDate[2], --selectedDate[1], selectedDate[0]));
                        setTimeout(function(){
                            $( "#toDate" ).datepicker('show');
                        }, 16);     
                    }});
            });
            //    $('#fromDate').datepicker({dateFormat: "dd-mm-yy", maxDate:new Date()});
            //            $('#toDate').datepicker({dateFormat: 'dd-mm-yy', maxDate:new Date()});
        </script>

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
