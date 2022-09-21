<%-- 
    Document   : timesheetRejection
    Created on : 16 May, 2020, 10:05:01 PM
    Author     : 16221
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Timesheet Rejection</title>
        <style>
            table,td{
                border:1px solid #CEDFF1;
                border-collapse:collapse;
                text-align:center;
                color:#666666;
            }
            #tabledetails{
                margin-bottom: 100px;
            }
            #tabledetails th{
                background: url(images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;
                font-weight: bolder;
                font-size: 11px;
                /* padding: 5px 10px; */
                padding-top: 3px;
                border: 1px solid #CEDFF1;
            }
            #tabledetails table tr td {
                background: none repeat scroll 0 0;
                border: 1px solid #CEDFF1;
                padding: 5px;
                font-size: 12px;
            }
            #tab{
                width: 100%;
            }
            text{
                border:1px solid #bfbfbf;
            }
            .page_heading{
                margin-top: 30px;
            }
            #header{
                background: url(../images/box-strip.jpg) repeat-x scroll top center #E2E8EC;
                border: 1px solid #BDC9D1;
                margin: 10px 0;
                height:30px;
                padding: 16px;
                border-collapse:collapse;
                text-align:left;
                padding:20px 20px;
                line-height: 3;
            }
            .gobutton {
                background: none repeat scroll 0 0 #316ca8;
                border: 1px solid #BCCFEA;
                color: #FFFFFF;
                float: right;
                font-weight: bold;
                height: 32px;
                width: 50px;
                margin:0px;
                border-radius: 5px;
                cursor: pointer;
            }
            .exportbutton{
                float:right;
                margin-left:10px;
                border-radius: 5px;
                cursor: pointer;
            }
            .container_inner{
                margin:0px;
            }
            #label{
                font-weight: bold;
                margin-left: 5px;
                margin-right: 5px;
                color:#666666;
            }
            #footer{
                height:35px;				
                text-align:center;
            }
            .qualitysave {
                background: url(images/icon_btn_save.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
            }
            .qualitysubmit {
                background: url(images/icon_btn_submit.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                /*margin: 0 0 0 15px;*/
                padding: 0 10px 0 30px;
                border-radius: 5px;
                cursor: pointer;
            }
            .plainButton{
                background:#316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px;
                width: auto;
                float: right;
                cursor:pointer;
            }
            .plainButton{
                float: none;
                width: auto;
                display: inline-block;
                line-height: initial;
                border-radius: 5px;
                text-align: center;
                margin: 10px;
                font-weight:normal;
                height: 25px;
            }
            #content{
                border:1px ;
            }
            .error{
                text-align: center;
                color:red;
            }
            .odd {
                background-color: #FFFFFF;
            }
            .even {
                background-color: #EFF4FB;
            }
            .sucess-message{
                color:green;
            }
            #msg{
                margin-bottom:10px;
            }
            /********custom select box*******/


            .close{
                border:none;
                padding:10px;
                border-radius:10px;
                font-size:30px;
                color:#0000FF;
                background-color:#6699FF;
            }
            .alertboxWrap{
                background-color: rgba(0,0,0,0.3);
                width:100%;
                height:100%;
                position:absolute;
                top:0px;
                left:0px;
                display:none;
            }
            .alertbox{
                z-index:10;
                width:300px;
                padding:0px;
                border:blue 1px solid;
                background-color:#b3c6ff;
                position:absolute;
                top:20%;
                left:40%;
            }
            p{
                margin:0px;
                padding:0px;
            }
            .alertbox p:first-child{
                background: -webkit-linear-gradient(left, #b3ccff,#4D94DB,#CCE0F5); /* For Safari 5.1 to 6.0 */
                background: -o-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Opera 11.1 to 12.0 */
                background: -moz-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Firefox 3.6 to 15 */
                float:left;
                padding:0px 245px 2px 0px;
                float:left;
            }
            .alertbox p:nth-of-type(2){
                padding:30px 10px 20px 10px;
                text-align:center;
            }
            button{
                margin:0px;
                padding:0px;
            }
            #x{
                width:15px;
                float:right;
            }
            .alertbox  button:nth-of-type(3){
                width:55px;
                padding:2px;
                margin:0px 30px 20px 10px;
                float:right;
            }
            .alertbox button:nth-of-type(2){
                width:60px;
                padding:2px;
                margin:0px 10px 20px 30px;
                float:left;
            }
            input[type=text] {
                border:1px solid #C4D1E0;
                /*background: white url('../images/text-bg.gif') repeat;*/
                font-family: 'Arial';
                width:100px;
                text-align: center;
            }
            .listLink{
                float: right;
                color: #4C83B3;
                font-weight: bold;
                font-size: 12px;
                margin-right: 10px;
            }
            .successMessage{
                text-align: center;
                font-weight: bold;
                color:green;
                font-size: medium;

            }
            .notice_page{
                margin-top: 10px;
            }
        </style>
        <script>
            var jsonArr = [];
            $(document).ready(function(){
                
                var today = new Date();
                $("#fromDate").datepicker({
                        changeMonth: true,
                        changeYear: true,
                        dateFormat: 'yy-mm-dd',
                        minDate : '2019-01-01',
                        maxDate: today,
                        onSelect: function () {
                            var date_format = $("#fromDate").val().split('-');
                            var from_date_new = date_format[0]+'-'+date_format[1]+'-'+date_format[2];
                            $("#toDate").datepicker( "destroy" );
                            $("#toDate").datepicker({
                                changeMonth: true,
                                changeYear: true,
                                dateFormat: 'yy-mm-dd',
                                minDate: new Date(from_date_new),
                                maxDate: today
                            });
                            $('#toDate').val('');
                        }
                    });

                    var date_format=$("#fromDate").val().split('-');
                    var from_date_new=date_format[2]+'-'+date_format[1]+'-'+date_format[0];
                    $("#toDate").datepicker({
                        changeMonth: true,
                        changeYear: true,
                        dateFormat: 'yy-mm-dd',
                        minDate : new Date(from_date_new),
                        maxDate: today
                    });

                
                $(".successMessage").fadeOut(5000);
                $("#selectall").click(function () {
                    $('.check').attr('checked', this.checked); 
                });
                $(".check").click(function(){		 
                    if($(".check").length == $(".check:checked").length) {

                    } else {
                        $("#selectall").removeAttr("checked");
                    }		 
                });
                
                $(".qualitysubmit").click(function(e){
                    e.preventDefault();
                    var i;
                    var d;
                    if($('.check:checked').length == 0){
                        alert('Please select checkbox to continue');
                        return false;
                    }
                    $('.check:checked').each(function(id, value){
                        i = $(value).attr('value');
                        d=validateDetails(i);
                        if(d==false){
                            $('#selectedRows').val('');
                            jsonArr = [];
                            return false;
                        }
                    })
                    if(d==false){
                        $('#selectedRows').val('');
                        jsonArr = [];
                        return false;
                    }else{
                        var height_page = document.body.scrollHeight;
                        $(".alertboxWrap").css({height:height_page});
                        $(".alertboxWrap").show();
                        
                    }
                });
                	
                $("#cancelSubmit").click(function(){
                    jsonArr = [];
                    $(".alertboxWrap").hide();
                    return false;
                });		
                $("#submitData").click(function(){
                    $('#saveDetails').attr("action", "rejectTimesheet.htm");
                    $('.qualitysubmit').hide();
                    $('.exportbutton').hide();
                    $('.gobutton').hide();
                    $(".alertboxWrap").hide();
                });

            });
            
            function validateDetails(i){
                var a=i;
                var timesheet_id = $("#timesheet_id_"+a).val();
                jsonArr.push({
                            timesheet_id : timesheet_id
                        });
                $('#selectedRows').val(JSON.stringify(jsonArr));
                return true;
            };
            
            $.fn.removeError= function(t) 
            {
                var a=$(t).attr('class');
                $("."+a).css("border","1px solid #bfbfbf");
                $(".error").html('');
                $(".error").css({"height":"0px","margin":"0px"});
            };
            function getEmployee(selectedValue) {
                $(".error").html('');
                $("#employee_id").val('');
                if(selectedValue != "") {
                    $.ajax({                   
                        url: './employeeList.htm',
                        type: "post",
                        async: false,
                        data: ({project_id : selectedValue}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#employee_id").html('').html(ajaxObj);
                        }
                    });
                };
            };
            
            function getEmployeeTimesheetDetails() {
                var proj=$("#project").val();
                var fromDate=$("#fromDate").val();
                var toDate=$("#toDate").val();
                if(proj=='' || proj == 'null'){
                    $(".error").html('Please select Project');
                    return false;
                }else if(fromDate=='' || fromDate == 'null'){
                    $(".error").html('Please select From Date');
                    return false;
                }else if(toDate=='' || toDate == 'null'){
                    $(".error").html('Please select To Date');
                    return false;
                }else{
                    $('#getDetails').attr("action", "getTimesheetDetails.htm");
                    //document.getDetails.submit();                       
                }
            };
            
        </script>
    </head>
    <body>
        <div id="content">
            <div class="container_inner">
                <div class="page_heading">
                    Timesheet Rejection
                </div>
            </div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:10px;padding-top: 1px;">
                    <ul class="notice_page_ul">
                        <li>Not Accrued Timesheet only can be rejected/send back</li>
                    </ul>
                </div>
            </div>
            <div class="successMessage">${submit_status}</div>
            <div id="header">
                <form name="getDetails" id="getDetails" method="post" action="#">
                    <label id="label">Project Name: </label>
                    <select id="project" name="project_id" style="width:15%" onchange="getEmployee(this.value)" >
                        <option value="">-- Project --</option>
                        <option value="Non_Project_Activity" ${('Non_Project_Activity'==filterData.project_id) ? 'selected':''}>Non Project Activity</option>
                        <c:forEach items="${projectList}" var="project">
                            <option value="${(project.project_id)}" ${(project.project_id==filterData.project_id) ? 'selected':''}>${project.project_name}</option>
                        </c:forEach>
                    </select>
                    <label id="label">Employee Name: </label>
                    <select id="employee_id" name="employee_id" style="width:15%" o>
                        <option value="">-- Employee --</option>
                        <c:forEach items="${employeeList}" var="employee" varStatus="i">
                            <option value="${(employee.employee_id)}" ${(employee.employee_id==filterData.employee_id) ? 'selected':''}>${employee.employee_name}</option>
                        </c:forEach>
                    </select>
                    <label id="label">From Date: </label>
                    <input class="calender-field" type="text" placeholder="YYYY-MM-DD" name="startDate" id="fromDate" value="${filterData.startDate}" readonly/>
                    <label id="label">To Date: </label>
                    <input class="calender-field" type="text" placeholder="YYYY-MM-DD" name="endDate" id="toDate" value="${filterData.endDate}" readonly />
                    <input type="submit" class="gobutton" id="go" name="submit"  value="Go" onClick="return getEmployeeTimesheetDetails();"/>
                </form>
            </div>
            <div class="error"></div>
            <form name="saveDetails" id="saveDetails" method="post" action="#">
                <div id="tabledetails">
                    <table id="tab">
                        <tr>
                            <th style="width: 10px;"><input type="checkbox" id="selectall"></th>
                            <th style="width: 250px;">Employee Name</th>
                            <th style="width: 75;">Date</th>
                            <th style="width: 225px;">Project Name</th>
                            <th style="width: 100px;">Role Name</th>
                            <th style="width: 100px;">Timesheet Hours</th>
                            <th style="width: 100px;">Approved Hours</th>
                            <th style="width: 100px;">Timesheet Status</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(timesheetDetails)!=0}">
                                <input type="hidden" name="project_id" value="${filterData.project_id}"/>
                                <input type="hidden" name="employee_id" value="${filterData.employee_id}"/>
                                <input type="hidden" name="startDate" value="${filterData.startDate}"/>
                                <input type="hidden" name="endDate" value="${filterData.endDate}"/>
                                <c:forEach items="${timesheetDetails}" var="details" varStatus="i">
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even">
                                            </c:when>
                                            <c:otherwise>
                                            <tr class="odd">
                                            </c:otherwise>
                                        </c:choose>
                                        <td>
                                            <input type="checkbox" name="checkbox" class="check" value="${i.count}" id="check_${i.count}" onclick=""/>
                                        </td>
                                        <td>
                                            <input type="hidden" name="timesheet_id" class="timesheet_id" value="${details.timesheet_id}" id="timesheet_id_${i.count}"/>
                                             ${details.employee_name}
                                        </td>
                                        <td>
                                             ${details.timesheet_date}
                                        </td>
                                        <td>
                                            ${details.project_name}
                                        </td>
                                        <td>
                                            ${details.role_name}
                                        </td>
                                        <td>
                                            ${details.timesheet_hours}
                                        </td>
                                        <td>
                                            ${details.billable_hours}
                                        </td>
                                        <td>
                                            ${details.remarks}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr class="odd">
                                    <td colspan="8">
                                        <div id="footer" style="margin-top: 5px">
                                            <input type="submit" value="Send Back" class="qualitysubmit" name="submit" />
                                            <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${fn:length(details)==0}">
                                    <tr class="odd">
                                        <td colspan="8" style="font-weight: bold;">No data to display</td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </table>

                </div>
                <div class="alertboxWrap">
                    <div id="alert" style="width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5); color:#000;">
                        <div id="alertBoxFocus" style="position: absolute;z-index: 150;bottom: 30%;left:34%;text-align: center;font-size: 14px;background-color: #fff;width: auto;height:auto;padding: 20px;border-radius: 5px;margin-top: 150px;">
                            <p style="padding:10px;margin:10px;">Are you sure you want to send back/reject the timesheet?<br><br>Click YES to submit, NO to edit the data</p><button class="plainButton" id="submitData">YES</button><button class="plainButton" id="cancelSubmit">NO</button>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </body>
</html>
