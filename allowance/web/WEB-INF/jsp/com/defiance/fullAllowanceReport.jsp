<%-- 
    Document   : fullAllowanceReport
    Created on : 12 Jun, 2017, 12:29:29 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Allowance</title>
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
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
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
                padding:10px 10px 0px 10px;
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


            .close
            {
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
            .alertbox
            {
                z-index:10;
                width:300px;
                padding:0px;
                border:blue 1px solid;
                background-color:#b3c6ff;
                position:absolute;
                top:20%;
                left:40%;
            }
            p
            {
                margin:0px;
                padding:0px;
            }
            .alertbox p:first-child
            {
                background: -webkit-linear-gradient(left, #b3ccff,#4D94DB,#CCE0F5); /* For Safari 5.1 to 6.0 */
                background: -o-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Opera 11.1 to 12.0 */
                background: -moz-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Firefox 3.6 to 15 */
                float:left;
                padding:0px 245px 2px 0px;
                float:left;
            }
            .alertbox p:nth-of-type(2)
            {
                padding:30px 10px 20px 10px;
                text-align:center;
            }
            button
            {
                margin:0px;
                padding:0px;
            }
            #x
            {
                width:15px;
                float:right;
            }
            .alertbox  button:nth-of-type(3)
            {
                width:55px;
                padding:2px;
                margin:0px 30px 20px 10px;
                float:right;
            }
            .alertbox button:nth-of-type(2)
            {
                width:60px;
                padding:2px;
                margin:0px 10px 20px 30px;
                float:left;
            }
            input[type=text] {
                border:1px solid #C4D1E0;
                background: white url('../images/text-bg.gif') repeat;
                font-family: 'Arial';
                width:25px;
                text-align: center;
            }
            .listLink{
                float: right;
                color: #4C83B3;
                font-weight: bold;
                font-size: 12px;
                margin-right: 10px;
            }
        </style>
        <script>
            function getMonth() {
                $("#selMon").val('');
                $("#customer").val('');
                $("#project").val('');
                $("#SBU_SUB_Id").val('');
                $("#SBU_Id").val('');
                $(".error").html('');
                $.ajax({                   
                    url: './getMonthList.htm',
                    type: "post",
                    async: false,
                    dataType:'html',
                    success: function(ajaxObj) {
                        $("#selMon").html('').html(ajaxObj);
                    }
                });

            };
                
            function getCustomer(selectedValue) {
                $(".error").html('');
                $("#customer").val('');
                $("#project").val('');
                $("#SBU_SUB_Id").val('');
                $("#SBU_Id").val('');
                var yr=$("#selYear").val();
                if(selectedValue != "") {
                    $.ajax({                   
                        url: './getCustomerList.htm',
                        type: "post",
                        async: false,
                        data: ({month:selectedValue,year:yr}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#customer").html('').html(ajaxObj);
                        }
                    });
                }
            };
            function getProject(selectedValue) {
                $(".error").html('');
                $("#project").val('');
                var yr=$("#selYear").val();
                var mon=$("#selMon").val();
                if(selectedValue != "") {
                    $.ajax({                   
                        url: './getProjectList.htm',
                        type: "post",
                        async: false,
                        data: ({cus_id:selectedValue,year:yr,month:mon}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#project").html('').html(ajaxObj);
                        }
                    });
                };
            };
            
            function getSubSbu(selectedValue) {
                $("#sub_sbu_id").val('');
                if(selectedValue != "") {
                    $.ajax({                   
                        url: './getSubSbu.htm',
                        type: "post",
                        async: false,
                        data: ({SBU_Id:selectedValue}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#sub_sbu_id").html('').html(ajaxObj);
                        }
                    });
                }
            };
                
            function getEmployeeList(){
                var cus=$("#customer").val();   
                var cus=$("#customer").val(); 
                var proj=$("#project").val();
                var yr=$("#selYear").val();
                var mon=$("#selMon").val();
                if(yr==''){
                    $(".error").html('Please select Year');
                    return false;
                }
                else if(mon==''){
                    $(".error").html('Please select Month');
                    return false;
                }   
                else if(cus==''||cus=='null'){
                    $(".error").html('Please select customer');
                    return false;
                }
                else if(proj=='' || proj == 'null'){
                    $(".error").html('Please select project');
                    return false;
                }                                                                        
                else{
                    $('#getDetails').attr("action", "getProcessedList.htm");
                    document.getDetails.submit();                       
                }

            };
            
            function getExcelReport(){
                var cus=$("#customer").val();   
                var cus=$("#customer").val(); 
                var proj=$("#project").val();
                var yr=$("#selYear").val();
                var mon=$("#selMon").val();
                if(yr==''){
                    $(".error").html('Please select Year');
                    return false;
                }
                else if(mon==''){
                    $(".error").html('Please select Month');
                    return false;
                }   
                else if(cus==''||cus=='null'){
                    $(".error").html('Please select customer');
                    return false;
                }
                else if(proj=='' || proj == 'null'){
                    $(".error").html('Please select project');
                    return false;
                }                                                                        
                else{
                    $('#getDetails').attr("action", "getExcelReport.htm");
                    document.getDetails.submit();                       
                }

            };
        </script>
    </head>
    <body>
        <div id="msg"><div class="sucess-message">${succcessMsg}</div></div>
        <div id="content">
            <div class="container_inner">
                <div class="page_heading">
                    Allowance Management - Processed List
                    <c:if test="${hr_login=='0'}">
                        <div class="listLink">
                            <img src="/allowance/images/add.png" title="List Apply Allowance" alt="List Apply Allowance">
                            <a style="text-decoration:none;color: #4C83B3;" target="_self" href="applyFullAllowance.htm">Submit Allowance</a>
                        </div>
                    </c:if>
                </div>
            </div>
            <div id="header">
                <form name="getDetails" id="getDetails" method="post" action="#">
                    <label id="label">Year: </label>
                    <select name="selYear" id="selYear" size="1" style="width:10%" onchange="getMonth()">
                        <option align="center" value="">-- Year --</option>
                        <c:forEach items="${yearsList}" var="yearsList" varStatus="sbuitr">
                            <option value="${yearsList.key}"${(filterData.year == yearsList.value) ? 'selected':''}>${yearsList.value}</option>
                        </c:forEach>
                    </select>    
                    <label id="label">Month: </label>
                    <select name="selMon" id="selMon" size="1" style="width:10%" onchange="getCustomer(this.value)" >
                        <option align="center" value="">-- Month --</option>
                        <c:forEach items="${monthsList}" var="month">
                            <option ${selMon} value="${month.key}"${(filterData.month == month.key) ? 'selected':''}>${month.value}</option>
                        </c:forEach>
                    </select>
                    <c:choose>
                        <c:when test="${hr_login=='1'}">
                            <label id="label">SBU: </label>
                            <select name="sbu_id" id="sbu_id" size="1" style="width:10%" onchange="getSubSbu(this.value)">
                                <option align="center" value="">-- SBU --</option>
                                <c:forEach items="${sbu_list}" var="SBU_List" >
                                    <option value="${SBU_List.sbu_id}"${(filterData.SBU_Id == SBU_List.sbu_id) ? 'selected':''}>${SBU_List.sbu_name}</option>
                                </c:forEach>
                            </select>  
                            <label id="label">Sub SBU: </label>
                            <select name="sub_sbu_id" id="sub_sbu_id" size="1" style="width:15%">
                                <option value="">-- Sub SBU --</option>
                                <c:forEach items="${sub_sbu_list}" var="SBU_SUB_List" >
                                    <option value="${SBU_SUB_List.sub_sbu_id}"${(filterData.SBU_SUB_Id == SBU_SUB_List.sub_sbu_id) ? 'selected':''}>${SBU_SUB_List.sub_sbu_name}</option>
                                </c:forEach>
                            </select>
                        </c:when>
                        <c:otherwise>
                            <label id="label">Customer Name: </label>
                            <select id="customer" name="customer" style="width:15%" onchange="getProject(this.value)" >
                                <option value="">-- Customer --</option>
                                <c:forEach items="${customer}" var="customer" varStatus="i">
                                    <option value="${(customer.customer_id)}" ${(customer.customer_id==filterData.cus_id) ? 'selected':''}>${customer.customer_name}</option>
                                </c:forEach>
                            </select>
                            <label id="label">Project Name: </label>
                            <select id="project" name="project" style="width:15%" >
                                <option value="">-- Project --</option>
                                <c:forEach items="${project}" var="project">
                                    <option value="${(project.project_id)}"${(project.project_id==filterData.project_id) ? 'selected':''}>${project.project_name}</option>
                                </c:forEach>
                            </select>
                        </c:otherwise>
                    </c:choose>
                    <input type="hidden" name="processed" id="processed" value="1"/>
                    <input type="submit" class="exportbutton" id="export" name="export"  value="Export" onClick="return getExcelReport();"/>
                    <input type="submit" class="gobutton" id="go" name="submit"  value="Go" onClick="return getEmployeeList();"/>
                </form>
            </div>
            <div class="error"></div>
            <form name="saveDetails" id="saveDetails" method="post" action="#">
                <div id="tabledetails">
                    <table id="tab">
                        <tr>
                            <th rowspan="2" style="width: 85px;">Employee Name</th>
                            <th colspan="4" style="width: 85px;">HTL Location & Customer Location <30KM from HTL Location</th>
                            <th colspan="4" style="width: 85px;">Customer Location >30KM from HTL Location</th>
                            <th rowspan="2" style="width: 70px;">Holiday/ Week End Details</th>
                            <th colspan="3" style="width: 50px;">Transport Details</th>
                            <th rowspan="2" style="width: 40px;">Hardship Allowance</th>
                            <th rowspan="2" style="width: 40px;">Shift Allowance</th>
                            <th rowspan="2" style="width: 40px;">Holiday Allowance</th>
                            <th rowspan="2" style="width: 40px;">Transport Allowance</th>
                            <th rowspan="2">Total Amount</th>
                            <th rowspan="2">Remarks</th>
                        </tr>
                        <tr>
                            <th style="width: 40px;">General Shift</th>
                            <th style="width: 40px;">First Shift</th>
                            <th style="width: 40px;">Second Shift</th>
                            <th style="width: 40px;">Third Shift</th>
                            <th style="width: 40px;">General Shift</th>
                            <th style="width: 40px;">First Shift</th>
                            <th style="width: 40px;">Second Shift</th>
                            <th style="width: 40px;">Third Shift</th>
                            <th style="width: 40px;">One Way</th>
                            <th style="width: 40px;">Two Way</th>
                            <th style="width: 40px;">No Cab</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(details)!=0}">
                                <input type="hidden" name="selYear" value="${filterData.year}"/>
                                <input type="hidden" name="selMon" value="${filterData.month}"/>
                                <input type="hidden" name="project" value="${filterData.project_id}"/>
                                <input type="hidden" name="customer" value="${filterData.cus_id}"/>
                                <c:forEach items="${details}" var="details" varStatus="i">
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even">
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="odd">
                                        </c:otherwise>
                                    </c:choose>
                                        <td>
                                            ${details.employee_name}
                                        </td>
                                        <td>
                                            ${details.company_general}
                                        </td>
                                        <td>
                                            ${details.company_shift_I}
                                        </td>
                                        <td>
                                            ${details.company_shift_II}
                                        </td>
                                        <td>
                                            ${details.company_shift_III}
                                        </td>
                                        <td>
                                            ${details.cust_general}
                                        </td>
                                        <td>
                                            ${details.cust_shift_I}
                                        </td>
                                        <td>
                                            ${details.cust_shift_II}
                                        </td>
                                        <td>
                                            ${details.cust_shift_III}
                                        </td>
                                        <td>
                                            ${details.weekend_holidays_entered}
                                        </td>
                                        <td>
                                            ${details.one_way}
                                        </td>
                                        <td>
                                            ${details.two_way}
                                        </td>
                                        <td>
                                            ${details.no_cab}
                                        </td>
                                        <td>
                                            ${details.hardship_amount}
                                        </td>
                                        <td>
                                            ${details.shift_amount}
                                        </td>
                                        <td>
                                            ${details.holiday_amount}
                                        </td>
                                        <td>
                                            ${details.transport_amount}
                                        </td>
                                        <td>
                                            ${details.total_amount}
                                        </td>
                                        <td>
                                            <textarea rows="1" class="remarks_${details.employee_id}" id="remarks_${details.employee_id}" name="remarks" cols="7" 
                                                      style="max-height: 50px; max-width: 200px;" readonly value="${details.remarks}">${details.remarks}</textarea>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${fn:length(details)==0}">
                                    <tr class="odd">
                                        <td colspan="19" style="font-weight: bold;">No data to display</td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </table>
                    
                </div>
                <div class="alertboxWrap">
                    <div id="alert" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5); top: 0pt; left: 0pt;color:#000;">
                        <div id="alertBoxFocus" style="position: absolute;z-index: 150;top: 20%;left:34%;text-align: center;font-size: 14px;background-color: #fff;width: auto;height:auto;padding: 20px;border-radius: 5px;margin-top: 150px;">
                            <p style="padding:10px;margin:10px;">Once you submit the data it will not be editable<br><br>Click YES to submit, NO to edit the data</p><button class="plainButton" id="submitData">YES</button><button class="plainButton" id="cancelSubmit">NO</button>
                        </div>
                    </div>
                </div>
            </form>
            
        </div>
    </body>
</html>

