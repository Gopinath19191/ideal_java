<%-- 
    Document   : accrualReversal
    Created on : 30 Apr, 2020, 4:58:07 PM
    Author     : 16221
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Accrual Reversal</title>
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
                background: white url('../images/text-bg.gif') repeat;
                font-family: 'Arial';
                width:145px;
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
                    $('#saveDetails').attr("action", "submitAccrualReversal.htm");
                    $('.qualitysubmit').hide();
                    $('.exportbutton').hide();
                    $('.gobutton').hide();
                    $(".alertboxWrap").hide();
                });

            });
            
            function validateDetails(i){
                var a=i;
                var project_id = $("#project").val();
                var employee_id = $("#employee_id_"+a).val();
                var sbu = $("#sbu_"+a).val();
                var sub_sbu = $("#sub_sbu_"+a).val();
                var role_id = $("#role_id_"+a).val();
                var location_id = $("#work_location_id_"+a).val();
                var location_table = $("#location_table_"+a).val();
                var billing_month = $("#billing_month_"+a).val();
                var billing_year = $("#billing_year_"+a).val();
                var ts_hours = $("#timesheet_hours_"+a).val();
                var billable_hours = "-"+$("#billable_hours_"+a).val();
                var uom_id = $("#uom_id_"+a).val();
                var billable_efforts = "-"+$("#billable_efforts_"+a).val();
                var billing_rate = $("#billing_rate_"+a).val();
                var billable_amount = "-"+$("#billable_amount_"+a).val();
                var accrual_no = $("#accrual_no").val();
                var remarks = $("#remarks_"+a).val().trim();
                var is_outsource = $("#is_outsource_"+a).val();
                if(remarks.length < 10){
                    $("#remarks_"+a).css("border","1px solid red");
                    $(".error").html('Please enter valid reason length minimum 10 characters');
                    return false;
                }else{
                    $("#remarks_"+a).css("border","none");
                    $(".error").html('');
                    jsonArr.push({
                        accrual_id : a,
                        employee_id : employee_id,
                        project_id : project_id,
                        sbu : sbu,
                        sub_sbu : sub_sbu,
                        role_id : role_id,
                        work_location_id : location_id,
                        location_table : location_table,
                        timesheet_hours : ts_hours,
                        billing_month : billing_month,
                        billing_year : billing_year,
                        billable_hours : billable_hours,
                        uom_id : uom_id,
                        billable_efforts : billable_efforts,
                        billing_rate : billing_rate,
                        billable_amount : billable_amount,
                        accrual_no : accrual_no,
                        remarks : remarks,
                        is_outsource : is_outsource
                    });
                    $('#selectedRows').val(JSON.stringify(jsonArr));
                    return true;
                }
                
            };
            
            $.fn.removeError= function(t) 
            {
                var a=$(t).attr('class');
                $("."+a).css("border","1px solid #bfbfbf");
                $(".error").html('');
                $(".error").css({"height":"0px","margin":"0px"});
            };
            function getProject(selectedValue) {
                $(".error").html('');
                $("#project").val('');
                if(selectedValue != "") {
                    $.ajax({                   
                        url: './projectList.htm',
                        type: "post",
                        async: false,
                        data: ({cus_id:selectedValue}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#project").html('').html(ajaxObj);
                        }
                    });
                };
            };
            function getAccrualNo(selectedValue) {
                $(".error").html('');
                $("#accrual_no").val('');
                if(selectedValue != "") {
                    $.ajax({                   
                        url: './getAccrualNo.htm',
                        type: "post",
                        async: false,
                        data: ({project_id : selectedValue}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#accrual_no").html('').html(ajaxObj);
                        }
                    });
                };
            };
            function getEmployeeAccrualDetails() {
                var cus=$("#customer").val();   
                var proj=$("#project").val();
                var accno=$("#accrual_no").val();
                if(cus==''||cus=='null'){
                    $(".error").html('Please select Customer');
                    return false;
                }else if(proj=='' || proj == 'null'){
                    $(".error").html('Please select Project');
                    return false;
                }else if(accno=='' || accno == 'null'){
                    $(".error").html('Please select Accrual No');
                    return false;
                }else{
                    $('#getDetails').attr("action", "getEmployeeAccrualDetails.htm");
                    //document.getDetails.submit();                       
                }
            };
            
        </script>
    </head>
    <body>
        <div id="content">
            <div class="container_inner">
                <div class="page_heading">
                    Accrual Reversal
                </div>
            </div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:10px;padding-top: 1px;">
                    <ul class="notice_page_ul">
                        <li>Non invoiced details only can be reversed</li>
                    </ul>
                </div>
            </div>
            <div id="header">
                <form name="getDetails" id="getDetails" method="post" action="#">
                    <label id="label">Customer Name: </label>
                    <select id="customer" name="cus_id" style="width:15%" onchange="getProject(this.value)" >
                        <option value="">-- Customer --</option>
                        <c:forEach items="${customerList}" var="customer" varStatus="i">
                            <option value="${(customer.cus_id)}" ${(customer.cus_id==filterData.cus_id) ? 'selected':''}>${customer.customer_name}</option>
                        </c:forEach>
                    </select>
                    <label id="label">Project Name: </label>
                    <select id="project" name="project_id" style="width:15%" onchange="getAccrualNo(this.value)">
                        <option value="">-- Project --</option>
                        <c:forEach items="${projectList}" var="project">
                            <option value="${(project.prjt_id)}"${(project.prjt_id==filterData.project_id) ? 'selected':''}>${project.project_name}</option>
                        </c:forEach>
                    </select>
                    <label id="label">Accrual  No: </label>
                    <select id="accrual_no" name="accrual_no" style="width:15%" >
                        <option value="">-- Accrual No --</option>
                        <c:forEach items="${accrualList}" var="accrualList">
                            <option value="${(accrualList)}"${(accrualList==filterData.accrual_no) ? 'selected':''}>${accrualList}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" class="gobutton" id="go" name="submit"  value="Go" onClick="return getEmployeeAccrualDetails();"/>
                </form>
            </div>
            <div class="error"></div>
            <form name="saveDetails" id="saveDetails" method="post" action="#">
                <div id="tabledetails">
                    <table id="tab">
                        <tr>
                            <th style="width: 10px;"><input type="checkbox" id="selectall"></th>
                            <th style="width: 250px;">Employee Name</th>
                            <th style="width: 120px;">Role Name</th>
                            <th style="width: 50px;">Month</th>
                            <th style="width: 50px;">Year</th>
                            <th style="width: 50px;">Timesheet Hours</th>
                            <th style="width: 50px;">Accrued Hours</th>
                            <th style="width: 50px;">Billable Efforts</th>
                            <th style="width: 100px;">Rate<br>(${employeeAccrualList.get(0).uom_name})</th>
                            <th style="width: 100px;">Billable Amount</th>
                            <th style="width: 150px;">Remarks</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(employeeAccrualList)!=0}">
                                <input type="hidden" name="project" value="${filterData.project_id}"/>
                                <input type="hidden" name="customer" value="${filterData.cus_id}"/>
                                <c:forEach items="${employeeAccrualList}" var="details" varStatus="i">
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even">
                                            </c:when>
                                            <c:otherwise>
                                            <tr class="odd">
                                            </c:otherwise>
                                        </c:choose>
                                        <td>
                                            <c:if test="${details.eligible == '1'}">
                                                <input type="checkbox" name="checkbox" class="check" value="${details.accrual_id}" id="check_${details.accrual_id}" onclick=""/>
                                            </c:if>
                                        </td>
                                        <td>
                                            <input type="hidden" name="employee_id" class="employee_id" value="${details.employee_id}" id="employee_id_${details.accrual_id}"/>
                                            <input type="hidden" name="sbu" class="sbu" value="${details.sbu}" id="sbu_${details.accrual_id}"/>
                                            <input type="hidden" name="sub_sbu" class="sub_sbu" value="${details.sub_sbu}" id="sub_sbu_${details.accrual_id}"/>
                                            <input type="hidden" name="work_location_id" class="work_location_id" value="${details.work_location_id}" id="work_location_id_${details.accrual_id}"/>
                                            <input type="hidden" name="location_table" class="location_table" value="${details.location_table}" id="location_table_${details.accrual_id}"/>
                                            <input type="hidden" name="uom_id" class="uom_id" value="${details.uom_id}" id="uom_id_${details.accrual_id}"/>
                                            <input type="hidden" name="is_outsource" class="is_outsource" value="${details.is_outsource}" id="is_outsource_${details.accrual_id}"/>
                                            ${details.employee_name}
                                        </td>
                                        <td>
                                            <input type="hidden" name="role_id" class="role_id" value="${details.role_id}" id="role_id_${details.accrual_id}"/>
                                            ${details.role_name}
                                        </td>
                                        <td>
                                            <input type="hidden" name="billing_month" class="billing_month" value="${details.billing_month}" id="billing_month_${details.accrual_id}"/>
                                            ${details.billing_month}
                                        </td>
                                        <td>
                                            <input type="hidden" name="billing_year" class="billing_year" value="${details.billing_year}" id="billing_year_${details.accrual_id}"/>
                                            ${details.billing_year}
                                        </td>
                                        <td>
                                            <input type="hidden" name="timesheet_hours" class="timesheet_hours" value="${details.timesheet_hours}" id="timesheet_hours_${details.accrual_id}"/>
                                            ${details.timesheet_hours}
                                        </td>
                                        <td>
                                            <input type="hidden" name="billable_hours" class="billable_hours" value="${details.billable_hours}" id="billable_hours_${details.accrual_id}"/>
                                            ${details.billable_hours}
                                        </td>
                                        <td>
                                            <input type="hidden" name="billable_efforts" class="billable_efforts" value="${details.billable_efforts}" id="billable_efforts_${details.accrual_id}"/>
                                            ${details.billable_efforts}
                                        </td>
                                        <td>
                                            <input type="hidden" name="billing_rate" class="billing_rate" value="${details.billing_rate}" id="billing_rate_${details.accrual_id}"/>
                                            ${details.billing_rate}
                                        </td>
                                        <td>
                                            <input type="hidden" name="billable_amount" class="billable_amount" value="${details.billable_amount}" id="billable_amount_${details.accrual_id}"/>
                                            ${details.billable_amount}
                                        </td>
                                        <td>
                                            <input type="text" name="remarks" class="remarks" id="remarks_${details.accrual_id}" />
                                        </td>
                                    </tr>
                                </c:forEach>
                                <tr class="odd">
                                    <td colspan="20">
                                        <div id="footer" style="margin-top: 5px">
                                            <!--<input type="submit" value="save" class="qualitysave" name="save" onClick=""/>-->
                                            <input type="submit" value="submit" class="qualitysubmit" name="submit" />
                                            <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
                                        </div>
                                    </td>
                                </tr>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${fn:length(details)==0}">
                                    <tr class="odd">
                                        <td colspan="20" style="font-weight: bold;">No data to display</td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </table>

                </div>
                <div class="alertboxWrap">
                    <div id="alert" style="width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5); color:#000;">
                        <div id="alertBoxFocus" style="position: absolute;z-index: 150;bottom: 30%;left:34%;text-align: center;font-size: 14px;background-color: #fff;width: auto;height:auto;padding: 20px;border-radius: 5px;margin-top: 150px;">
                            <p style="padding:10px;margin:10px;">Are you sure you want to reverse the Accrual?<br><br>Click YES to submit, NO to edit/cancel the data</p><button class="plainButton" id="submitData">YES</button><button class="plainButton" id="cancelSubmit">NO</button>
                        </div>
                    </div>
                </div>
            </form>

        </div>
    </body>
</html>
