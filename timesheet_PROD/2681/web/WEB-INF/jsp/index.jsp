<%--
    Document   : address
    Created on : Mar 13, 2012, 12:12:20 PM
    Author     : 15065
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TimeSheet Entry</title>
    </head>
    <style>
        .notes_detalis{
            border-radius: 5px; 
            background-size: 100% 100%;
            font-weight: bold;
            color:#666666;
            cursor: pointer;
            padding: 10px;
            border: 1px solid #ddd6b7;
            background-color: #ece6d2;
            background-repeat: no-repeat;
        }
        .searchBox{
            margin-top: 10px;
        }
        .alert_icon{
            margin-top: -7px;
            margin-right: 5px;
        }
        body{
            width:1200px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.myIframe').css('height', $(window).height()+'px');
        });
        
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:1500px;z-index:40;background-color:rgba(0,0,0,0.5);display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 14%; left: 45%;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <input type="hidden" value="${pageContext.request.contextPath}" id="base_path" />
        <div align="center" style="margin:15px 0px -20px 0px;" id="successDiv"><font color="green" size="4">${success_msg}</font></div>
        <div class="container_inner">
            <div class="page_heading">
                Timesheet Entry
            </div>
            <div class="notes_detalis" id="notes_detalis">
                <div style="float:left;"><img class="alert_icon" alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                Notes/Tips - Click to view
            </div>
            <div class="notice_page" id="notes_description">
                <div>
                    <ul class="notice_page_ul">
                        <li>Please select check-box before submitting the Time sheet. Based on the selection only time sheet entry will be submitted</li>
                        <li>Please enter regularizations as separate row by choosing appropriate reason</li>                                                                       
                        <li>Individual attendance hours for the current day will be available by next day at 10 a.m. IST for employees working from HTL India locations.  However, time sheet can be entered and saved for the current day</li>
                        <li>In case of incorrect Attendance Hours and Available Hours please contact ideal support team (idealsupport@hindujatech.com)</li>
                        <li>Press Control+F5 to clear cache if Time sheets not loaded fully</li>
                    </ul>
                </div>
            </div>
        </div>
        <form method="post" name="tmisheetFilterPage" id="tmisheetFilterPage" action="#" onSubmit="">
            <input type="hidden" value="${formData.employeeId}" id="employee_id" name="employeeId" />
            <div class="searchBox">
                <table width="100%" border="0" >
                    <tr>
                        <td style="padding-left:10px;color:#666666;" width="10%"><b>${days}Select Year :</b></td>
                        <td  width="7%">
                            <select name="res_year" id="year" style="width:70px;" onChange="loadMonth(this.value)">
                                <c:forEach  items="${year_list}" var="yearList" >
                                    <option ${(yearList.key == currentYear)?'selected':''} value="${yearList.key}">${yearList.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="color:#666666;" width="10%"><b>Select Month :</b></td>
                        <td>
                            <select name="res_month" id="month" onChange="monthChange()">
                                <option value="">--Select Month--</option>
                                <c:forEach  items="${month_list}" var="monthList" >
                                    <option ${(monthList.key == currentMonth)?'selected':''} value="${monthList.key}">${monthList.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td style="color:#666666;" width="10%"><b>Select Status :</b></td>
                        <td>
                            <select name="res_status" id="status" onChange="statusChanged()">
                                <option value="">To be Submitted</option>                                
                                <c:forEach  items="${status_list}" var="statusList" >
                                    <option ${(statusList.configuration_key == formData.status)?'selected':''} value="${statusList.configuration_key}">${statusList.configuration_value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <a onClick="refreshData('Index')" style=" padding: 7px 8px 8px 8px;background: #316CA8;width: 90px;height: 41px;font-family: Arial;font-weight: bold;font-size: 13px;color: #FFFFFF;text-align: center;border: 1px solid #4492BF;cursor: pointer;">Reset</a>
                            <input type="button" class="exportbutton" name="excelExport" id="excelExport" onclick="exportBtn();" value="Export"/>
                            <input type="button" class="exportpdfbutton" name="pdfExport" id="pdfExport" onclick="exportPdfBtn();" value="Export Pdf"/>
                        </td>
                       <td  style="width: 50px;">
                            <a href="userManual.htm"  target="_blank" style="text-decoration:none;color: #4C83B3; font-size: 11px;"><img src="/iDeal_timesheet/images/usermanual2.png"  alt="User Manual" style="width: 28px;height: auto;"></a>
                        </td>
                    </tr>
                </table>
            </div>
            <div class="contentwrap">
                <table width="100%" border="0">
                    <tbody>
                        <tr>
                            <td>
                                <div id="timesheet">
                                    <script type="text/javascript">
                                        $(function() {
                                            loadTimesheet_New(${currentMonth},${currentYear});
                                        });
                                        
                                        function statusChanged(){
                                            var year = $('#year').val();
                                            var month = $('#month').val();
                                            loadMonth(year);
                                            //
                                            var status = $('#status').val();
                                            $("#loadingDiv").show();
                                            setTimeout(function() {
                                                loadTimesheet_New(month,year,status);
                                            }, 100);
                                        }
                                        function monthChange(){
                                            var year = $('#year').val();
                                            var month = $('#month').val();
                                            var status = $('#status').val();
                                            $("#loadingDiv").show();
                                            setTimeout(function() {
                                                loadTimesheet_New(month,year,status);
                                            }, 100);
                                        }
                                    </script>

                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
        <div id="checkSelect" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="checkSelectFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select check-box<br></p><p class="plainButton" id="checkSelectYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="projectNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="rprojectNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select project<br></p><p class="plainButton" id="projectNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="saveRejectNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="saveRejectNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Sent Back entries cannot be saved.<br>Please submit it.<br></p><p class="plainButton" id="saveRejectYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="emptyEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="emptyEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Timesheet hours should not be empty.<br></p><p class="plainButton" id="emptyEntryErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="24hrsEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="24hrsEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Timesheet Hours should be lesser than 24.<br></p><p class="plainButton" id="24hrsEntryErrorYes"style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="60MinEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="60MinEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Timesheet minutes should be lesser than 60.<br></p><p class="plainButton" id="60MinEntryErrorYes"style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="halfDayEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="halfDayEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please enter valid hours.<br><br>You have applied leave for Half day<br></p><p class="plainButton" id="halfDayEntryErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="onceSubmitNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="onceSubmitNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please submit all the records of a day at once<br></p><p class="plainButton" id="onceSubmitNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="availableHrsNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="availableHrsNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Enter minimum hours equal to available hours.</p><p class="plainButton" id="availableHrsNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="attendanceHrsNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="attendanceHrsNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Enter minimum hours equal to attendance hours.</p><p class="plainButton" id="attendanceHrsNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="regularizeHrsNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="regularizeHrsNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please regularize your Hours<br></p><p class="plainButton" id="regularizeHrsNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="regularizeReasonNotification" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="regularizeReasonNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select the regularization reason<br></p><p class="plainButton" id="regularizeReasonNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="remarksEntryError" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="remarksEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Remarks should not be empty while selecting the Regularization reason.</p><p class="plainButton" id="remarksEntryErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="monthEntryError" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="monthEntryErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">Please select month to export.</p><p class="plainButton" id="monthEntryErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="policyError" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="policyErrorFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
                <div style="padding: 5px;">
                    <p style="padding:10px;margin:10px;">You are exceeding 'Work from home' policy limit for the Month/Year.<br>Please refer policy document. </p><p class="plainButton" id="policyErrorYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
        <div id="policyNotification" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
            <div id="policyNotificationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
                <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Message!</p>
                <div style="padding: 5px;">
                    <p style="padding: 10px 0px 0px 0px;margin:0px;text-align: center;">Work From Home Policy</p><br>
                    <p style="margin: 0px 10px;text-align: left;">Eligibility to work from home: <br><br>1.RM/PM/Client approval is required<br>2.HTL/Client Laptop<br>3.Employees who are at Client location should follow the customer's policy on work from home.</p><p class="plainButton" id="policyNotificationYes" style="margin: 0px 10px 10px 10px;">OK</p>
                </div>
            </div>
        </div>
    </body>
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
        //    var loadingDivObj=(document.all);
        //    var ns4=document.layers;
        //    var ns6=document.getElementById&&!document.all;
        //    var ie4=document.all;
        //    if (ns4) {
        //        loadingDivObj.visibility = "hidden";
        //    }
        //    else if (ns6 || ie4)
        //        loadingDivObj.display = "none";
            setTimeout(function(){
                $("#loadingDiv").hide();
            },100);

        }

        function startLoading() {
        //    var loadingDivObj=(document.all);
        //    var ns4=document.layers;
        //    var ns6=document.getElementById&&!document.all;
        //    var ie4=document.all;
        //    if (ns4) {
        //        loadingDivObj.visibility = "visible";
        //    }
        //    else if (ns6 || ie4)
        //        loadingDivObj.display = "block";
            setTimeout(function(){
                $("#loadingDiv").show();
            },100);

        }
        
        $(document).ready(function() {
            $("#notes_description").toggle();
            $("#notes_detalis").click(function(){
                $("#notes_description").toggle();
            });
        });
    </script>
</html>

