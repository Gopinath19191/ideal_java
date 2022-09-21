<%-- 
    Document   : fullAllowance
    Created on : 2 Jun, 2017, 10:49:15 AM
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
            var hardship=0,shift_one=0,shift_two=0,shift_three=0,count_holiday=0,one_way=0,two_way=0,no_cab=0;
            var amount_hardship=0,amount_shift_one=0,amount_shift_two=0,amount_shift_three,amount_transport=0,amount_holiday=0,hardship_max,transport_max=0;
            var hardship_total=0,shift_total=0,transport_total=0,holiday_total=0,total=0;
            var jsonArr = [];
            <c:forEach items="${shift}" var="shift" varStatus="i">
                var type='${(shift.type)}';
                var shift='${(shift.shifts)}';
                if(shift=='General'){
                    amount_hardship=${(shift.amount)};
                }else if(shift=='First_Shift'){
                    amount_shift_one=${(shift.amount)};
                }else if(shift=='Second_Shift'){
                    amount_shift_two=${(shift.amount)};
                }else if(shift=='Third_Shift'){
                    amount_shift_three=${(shift.amount)};
                }else if(shift=='Transport'){
                    amount_transport=${(shift.amount)};
                }else if(shift=='Holiday'){
                    amount_holiday=${(shift.amount)};
                }else if(shift=='Maximum_hardship'){
                    hardship_max=${(shift.amount)};
                }else if(shift=='Maximum_transport'){
                    transport_max=${(shift.amount)};
                }else{
                    total=0;
                }
                
            </c:forEach>
            
            $(document).ready(function(){
                
                $(".successMessage").fadeOut(5000);
                
                $("input").focus(function(){  
                    if (window.event) {
                        var charCode = window.event.keyCode;
                        $(":input").keypress(function(event) {
                            var name=$(document.activeElement).attr('name');
                            var charCode = window.event.keyCode;
                            
                            if(name != 'remarks'){
                                try {
                                    if (window.event) {
                                        var charCode = window.event.keyCode;
                                    }
                                    else if (e) {
                                        var charCode = e.which;
                                    }
                                    else { 
                                        return true; 
                                    }
                                    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                                        return false;
                                    }
                                    return true;
                                }
                                catch (err) {

                                }
                            }

                        });
                    }
                });
                
                $("#selectall").click(function () {
                    $('.check').attr('checked', this.checked); 
                });
                $(".check").click(function(){		 
                    if($(".check").length == $(".check:checked").length) {

                    } else {
                        $("#selectall").removeAttr("checked");
                    }		 
                });
                
                $.fn.shift= function(e,t) {
                    var a=$(t).attr('class');
                    var name=$("#employee_name"+a).val();
                    var cmp_gen=$("#cmp_general"+a).val();
                    var cmp_s_1=$("#cmp_shift_1"+a).val();
                    var cmp_s_2=$("#cmp_shift_2"+a).val();
                    var cmp_s_3=$("#cmp_shift_3"+a).val();
                    var cust_gen=$("#cust_general"+a).val();
                    var cust_s_1=$("#cust_shift_1"+a).val();
                    var cust_s_2=$("#cust_shift_2"+a).val();
                    var cust_s_3=$("#cust_shift_3"+a).val();
                    var cab_one_way=$("#one_way"+a).val();
                    var cab_two_way=$("#two_way"+a).val();
                    var cab_no_way=$("#no_cab"+a).val();
                    var holiday_count=$("#holiday_count"+a).val();
                    var working_days=$("#working_day_count"+a).val();
                    var weekend_holiday_days=$("#weekend_holiday_count"+a).val();
                    var worked_day_count = Number(cmp_gen)+Number(cmp_s_1)+Number(cmp_s_2)+Number(cmp_s_3)+Number(cust_gen)+Number(cust_s_1)+Number(cust_s_2)+Number(cust_s_3);
                    var cab_eligible_days = Number(cmp_s_1)+Number(cmp_s_2)+Number(cmp_s_3)+Number(cust_gen)+Number(cust_s_1)+Number(cust_s_2)+Number(cust_s_3);
                    var cab_count = Number(cab_one_way)+Number(cab_two_way)+Number(cab_no_way);
                    if(Number(worked_day_count)>Number(working_days)){
                        $("."+a).css("border","1px solid red");
//                        $("#check"+a).hide();
//                        $("#check"+a).attr('checked', false); 
                        $(".error").css({"height":"20px","margin-bottom":"10px"});
                        $(".error").html('Employee '+name+' is allocated only for '+working_days+' days');
                    }else if(Number(holiday_count)>Number(weekend_holiday_days)){
                        $("."+a).css("border","1px solid red");
//                        $("#check"+a).hide();
//                        $("#check"+a).attr('checked', false);
                        $(".error").css({"height":"20px","margin-bottom":"10px"});
                        $(".error").html('Number of holiday for the Employee '+name+' is '+weekend_holiday_days+' days');
                    }else if(Number(cab_count)>Number(cab_eligible_days)){
                        $("."+a).css("border","1px solid red");
//                        $("#check"+a).hide();
//                        $("#check"+a).attr('checked', false);
                        $(".error").css({"height":"20px","margin-bottom":"10px"});
                        $(".error").html('For employee '+name+' cab details count '+cab_count+' days is greater than shift days '+cab_eligible_days);
                    }else{
                        hardship=Number(cust_gen)*amount_hardship;
                        shift_one=(Number(cmp_s_1) + Number(cust_s_1))*amount_shift_one;
                        shift_two=(Number(cmp_s_2) + Number(cust_s_2))*amount_shift_two;
                        shift_three=(Number(cust_s_3) + Number(cmp_s_3))*amount_shift_three;
                        count_holiday=Number(holiday_count)*amount_holiday;
                        transport_total=Math.round(Number(cab_no_way)*amount_transport+(Number(cab_one_way)*(amount_transport/2)));
                        if(hardship>hardship_max){
                            hardship_total=hardship_max;
                        }else{
                            hardship_total=hardship;   
                        }
                        if(transport_total>transport_max){
                            transport_total=transport_max;
                        }else{
                            transport_total=transport_total;
                        }
                        shift_total=shift_one+shift_two+shift_three;
                        holiday_total=count_holiday;
                        total=hardship_total+shift_total+holiday_total+transport_total;
                        $("#hardship_amount"+a).val(hardship_total.toFixed(1));
                        $("#shift_amount"+a).val(shift_total.toFixed(1));
                        $("#holiday_amount"+a).val(holiday_total.toFixed(1));
                        $("#transport_amount"+a).val(transport_total.toFixed(1));
                        $("#total_amount"+a).val(total.toFixed(1));
//                        $("#check"+a).show();
                    }
                    
                };
                
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
                    $('#saveDetails').attr("action", "submitEmployeeAllowance.htm");
                    $('.qualitysubmit').hide();
                    $('.exportbutton').hide();
                    $('.gobutton').hide();
                    $(".alertboxWrap").hide();
                });

            });
            
            function validateDetails(i){
                var a=i;
                var name=$("#employee_name_"+a).val();
                var mnth=$("#selMon").val();
                var yr=$("#selYear").val();
                var prjt=$("#project").val();
                var cmp_gen=$("#cmp_general_"+a).val();
                var cmp_s_1=$("#cmp_shift_1_"+a).val();
                var cmp_s_2=$("#cmp_shift_2_"+a).val();
                var cmp_s_3=$("#cmp_shift_3_"+a).val();
                var cust_gen=$("#cust_general_"+a).val();
                var cust_s_1=$("#cust_shift_1_"+a).val();
                var cust_s_2=$("#cust_shift_2_"+a).val();
                var cust_s_3=$("#cust_shift_3_"+a).val();
                var cab_one_way=$("#one_way_"+a).val();
                var cab_two_way=$("#two_way_"+a).val();
                var cab_no_way=$("#no_cab_"+a).val();
                var holiday_count=$("#holiday_count_"+a).val();
                var working_days=$("#working_day_count_"+a).val();
                var weekend_holiday_days=$("#weekend_holiday_count_"+a).val();
                var worked_day_count = Number(cmp_gen)+Number(cmp_s_1)+Number(cmp_s_2)+Number(cmp_s_3)+Number(cust_gen)+Number(cust_s_1)+Number(cust_s_2)+Number(cust_s_3);
                var cab_eligible_days = Number(cmp_s_1)+Number(cmp_s_2)+Number(cmp_s_3)+Number(cust_gen)+Number(cust_s_1)+Number(cust_s_2)+Number(cust_s_3);
                var cab_count = Number(cab_one_way)+Number(cab_two_way)+Number(cab_no_way);
                if(Number(worked_day_count)>Number(working_days)){
                    $("."+a).css("border","1px solid red");
                    $(".error").css({"height":"20px","margin-bottom":"10px"});
                    $(".error").html('Employee '+name+' is allocated only for '+working_days+' days');
                    return false;
                }
                if(Number(holiday_count)>Number(weekend_holiday_days)){
                    $("."+a).css("border","1px solid red");
                    $(".error").css({"height":"20px","margin-bottom":"10px"});
                    $(".error").html('Number of holiday for the Employee '+name+' is '+weekend_holiday_days+' days');
                    return false;
                }
                if(Number(cab_count)>Number(cab_eligible_days)){
                    $("."+a).css("border","1px solid red");
                    $(".error").css({"height":"20px","margin-bottom":"10px"});
                    $(".error").html('For employee '+name+' cab details count '+cab_count+' days is greater than shift days '+cab_eligible_days);
                    return false;
                }
                if($("#total_amount_"+a).val()==0){
                    $("."+a).css("border","1px solid red");
                    $(".error").css({"height":"20px","margin-bottom":"10px"});
                    $(".error").html('For employee '+name+' the total value is Zero so please uncheck to submit other values');
                    return false;
                }
                else{
                    hardship=Number(cust_gen)*amount_hardship;
                    shift_one=(Number(cmp_s_1) + Number(cust_s_1))*amount_shift_one;
                    shift_two=(Number(cmp_s_2) + Number(cust_s_2))*amount_shift_two;
                    shift_three=(Number(cust_s_3) + Number(cmp_s_3))*amount_shift_three;
                    count_holiday=Number(holiday_count)*amount_holiday;
                    transport_total=Math.round(Number(cab_no_way)*amount_transport+(Number(cab_one_way)*(amount_transport/2)));
                    if(hardship>hardship_max){
                        hardship_total=hardship_max;
                    }else{
                        hardship_total=hardship;   
                    }
                    if(transport_total>transport_max){
                        transport_total=transport_max;
                    }else{
                        transport_total=transport_total;
                    }
                    shift_total=shift_one+shift_two+shift_three;
                    holiday_total=count_holiday;
                    total=hardship_total+shift_total+holiday_total+transport_total;
                    $("#hardship_amount_"+a).val(hardship_total.toFixed(1));
                    $("#shift_amount_"+a).val(shift_total.toFixed(1));
                    $("#holiday_amount_"+a).val(holiday_total.toFixed(1));
                    $("#transport_amount_"+a).val(transport_total.toFixed(1));
                    $("#total_amount_"+a).val(total.toFixed(1));
                    
                    jsonArr.push({
                        employee_id : a,
                        month : mnth,
                        year : yr,
                        company_general : cmp_gen,
                        company_shift_I : cmp_s_1,
                        company_shift_II : cmp_s_2,
                        company_shift_III : cmp_s_3,
                        cust_general : cust_gen,
                        cust_shift_I : cust_s_1,
                        cust_shift_II : cust_s_2,
                        cust_shift_III : cust_s_3,
                        one_way : cab_one_way,
                        two_way : cab_two_way,
                        no_cab : cab_no_way,
                        weekend_holidays_entered : holiday_count,
                        project_id : prjt,
                        hardship_amount : hardship_total,
                        shift_amount : shift_total,
                        transport_amount : transport_total,
                        holiday_amount : holiday_total,
                        total_amount : total,
                        remarks : $('#remarks_'+a).val()
                        
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
                function getEmployeeList(){
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
                        $('#getDetails').attr("action", "getEligibleEmployeeList.htm");
                        //document.getDetails.submit();                       
                    }
                        
                };
                
            function getExcelReport(processed){
                var cus=$("#customer").val();   
                var proj=$("#project").val();
                var yr=$("#selYear").val();
                var mon=$("#selMon").val();
                var processed=$("#processed").val();
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
                    //document.getDetails.submit();                       
                }

            };
        </script>
    </head>
    <body>
        <div id="msg"><div class="sucess-message">${succcessMsg}</div></div>
        <div id="content">
            <div class="container_inner">
                <div class="page_heading">
                    Allowance Management - Submit Allowance
                    <div class="listLink">
                        <img src="/allowance/images/icon_list.png" title="List Processed Allowance" alt="List Processed Allowance">
                        <a style="text-decoration:none;color: #4C83B3;" target="_self" href="processedAllowance.htm">Processed List</a>
                    </div>
                </div>
                <div class="successMessage">${submit_status}</div>
            </div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:10px;padding-top: 1px;">
                    <ul class="notice_page_ul">
                        <li>Customer location greater than 30 KM from HTL location eligible for Hardship allowance</li>
                        <li>Values gets populate based on approved timesheet linked with work location and calendar mapped</li>
                        <li>Cut off date to submit the allowance is on or before 18th of every month</li>
                        <li>Post cutoff, data cannot be submitted and it will be visible for reference</li>
                        <li>Exceptions can be handled manually through Human Resource Department</li>
                    </ul>
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
                        <input type="hidden" name="processed" id="processed" value="0"/>
                        <input type="submit" class="exportbutton" id="export" name="export"  value="Export" onClick="return getExcelReport(0);"/>
                        <input type="submit" class="gobutton" id="go" name="submit"  value="Go" onClick="return getEmployeeList();"/>
                </form>
            </div>
            <div class="error"></div>
            <form name="saveDetails" id="saveDetails" method="post" action="#">
                <div id="tabledetails">
                    <table id="tab">
                        <tr>
                            <c:if test="${editable=='1'}">
                                <th rowspan="2"><input type="checkbox" id="selectall"></th>
                            </c:if>
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
                                    <c:choose>
                                        <c:when test="${editable=='1'}">
                                            <td>
                                                <input type="checkbox" name="checkbox" class="check" value="${details.employee_id}" id="check_${details.employee_id}" onclick=""/>
                                                <input type="hidden" name="employee_id" class="employee_id" value="${details.employee_id}" id="employee_id"/>
                                                <input type="hidden" name="working_day_count" class="working_day_count" value="${details.working_days}" id="working_day_count_${details.employee_id}"/>
                                                <input type="hidden" name="weekend_holiday_count" class="weekend_holiday_count" value="${details.weekend_holiday_count}" id="weekend_holiday_count_${details.employee_id}"/>
                                            </td>
                                            <td>
                                                <input type="hidden" name="employee_name" class="employee_name" value="${details.employee_name}" id="employee_name_${details.employee_id}"/>
                                                ${details.employee_name}
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cmp_general_${details.employee_id}" name="company_general" 
                                                       value="${details.company_general}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cmp_shift_1_${details.employee_id}"name="company_shift_I" 
                                                       value="${details.company_shift_I}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cmp_shift_2_${details.employee_id}" name="company_shift_II" 
                                                       value="${details.company_shift_II}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cmp_shift_3_${details.employee_id}" name="company_shift_III" 
                                                       value="${details.company_shift_III}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cust_general_${details.employee_id}" name="cust_general" 
                                                       value="${details.cust_general}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cust_shift_1_${details.employee_id}" name="cust_shift_I" 
                                                       value="${details.cust_shift_I}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cust_shift_2_${details.employee_id}" name="cust_shift_II" 
                                                       value="${details.cust_shift_II}" onkeyup="$(this).shift(event,this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cust_shift_3_${details.employee_id}" name="cust_shift_III" 
                                                       value="${details.cust_shift_III}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="holiday_count_${details.employee_id}" name="weekend_holidays_entered" 
                                                       value="${details.weekend_holidays_entered}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="one_way_${details.employee_id}" name="one_way"
                                                       value="${details.one_way}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="two_way_${details.employee_id}" name="two_way"
                                                       value="${details.two_way}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="no_cab_${details.employee_id}" name="no_cab"
                                                       value="${details.no_cab}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" class="hardship_amount_${details.employee_id}" id="hardship_amount_${details.employee_id}" name="hardship_amount" 
                                                       style="width: 50px;" readonly value="${details.hardship_amount}"/>
                                            </td>
                                            <td>
                                                <input type="text" class="shift_amount_${details.employee_id}" id="shift_amount_${details.employee_id}" name="shift_amount" 
                                                       style="width: 50px;" readonly value="${details.shift_amount}"/>
                                            </td>
                                            <td>
                                                <input type="text" class="holiday_amount_${details.employee_id}" id="holiday_amount_${details.employee_id}" name="holiday_amount" 
                                                       style="width: 50px;" readonly value="${details.holiday_amount}"/>
                                            </td>
                                            <td>
                                                <input type="text" class="transport_amount_${details.employee_id}" id="transport_amount_${details.employee_id}" name="transport_amount" 
                                                       style="width: 50px;" readonly value="${details.transport_amount}"/>
                                            </td>
                                            <td>
                                                <input type="text" class="total_amount_${details.employee_id}" id="total_amount_${details.employee_id}" name="total_amount" 
                                                       style="width: 50px;" readonly value="${details.total_amount}"/>
                                            </td>
                                            <td>
                                                <textarea rows="1" class="remarks_${details.employee_id}" id="remarks_${details.employee_id}" name="remarks" cols="7" 
                                                          style="max-height: 50px; max-width: 200px;" value="${details.remarks}"></textarea>
                                            </td>
                                        </c:when>
                                        <c:otherwise>
                                            <td>
                                                <input type="hidden" name="employee_name" class="employee_name" value="${details.employee_name}" id="employee_name_${details.employee_id}"/>
                                                ${details.employee_name}
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cmp_general_${details.employee_id}" name="company_general" 
                                                       readonly value="${details.company_general}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cmp_shift_1_${details.employee_id}"name="company_shift_I" 
                                                       readonly value="${details.company_shift_I}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cmp_shift_2_${details.employee_id}" name="company_shift_II" 
                                                       readonly value="${details.company_shift_II}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cmp_shift_3_${details.employee_id}" name="company_shift_III" 
                                                       readonly value="${details.company_shift_III}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cust_general_${details.employee_id}" name="cust_general" 
                                                       readonly value="${details.cust_general}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cust_shift_1_${details.employee_id}" name="cust_shift_I" 
                                                       readonly value="${details.cust_shift_I}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cust_shift_2_${details.employee_id}" name="cust_shift_II" 
                                                       readonly value="${details.cust_shift_II}" onkeyup="$(this).shift(event,this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="cust_shift_3_${details.employee_id}" name="cust_shift_III" 
                                                       readonly value="${details.cust_shift_III}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="holiday_count_${details.employee_id}" name="weekend_holidays_entered" 
                                                       readonly value="${details.weekend_holidays_entered}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="one_way_${details.employee_id}" name="one_way"
                                                       readonly value="${details.one_way}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="two_way_${details.employee_id}" name="two_way"
                                                       readonly value="${details.two_way}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" maxlength="2" class="_${details.employee_id}" id="no_cab_${details.employee_id}" name="no_cab"
                                                       readonly value="${details.no_cab}" onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                            </td>
                                            <td>
                                                <input type="text" class="hardship_amount_${details.employee_id}" id="hardship_amount_${details.employee_id}" name="hardship_amount" 
                                                       style="width: 50px;" readonly value="${details.hardship_amount}"/>
                                            </td>
                                            <td>
                                                <input type="text" class="shift_amount_${details.employee_id}" id="shift_amount_${details.employee_id}" name="shift_amount" 
                                                       style="width: 50px;" readonly value="${details.shift_amount}"/>
                                            </td>
                                            <td>
                                                <input type="text" class="holiday_amount_${details.employee_id}" id="holiday_amount_${details.employee_id}" name="holiday_amount" 
                                                       style="width: 50px;" readonly value="${details.holiday_amount}"/>
                                            </td>
                                            <td>
                                                <input type="text" class="transport_amount_${details.employee_id}" id="transport_amount_${details.employee_id}" name="transport_amount" 
                                                       style="width: 50px;" readonly value="${details.transport_amount}"/>
                                            </td>
                                            <td>
                                                <input type="text" class="total_amount_${details.employee_id}" id="total_amount_${details.employee_id}" name="total_amount" 
                                                       style="width: 50px;" readonly value="${details.total_amount}"/>
                                            </td>
                                            <td>
                                                <textarea rows="1" class="remarks_${details.employee_id}" id="remarks_${details.employee_id}" name="remarks" cols="7" 
                                                          readonly style="max-height: 50px; max-width: 200px;" value="${details.remarks}"></textarea>
                                            </td>
                                        </c:otherwise>
                                    </c:choose>
                                        </tr>
                                </c:forEach>
                                <c:if test="${editable=='1'}">
                                    <tr class="odd">
                                        <td colspan="20">
                                            <div id="footer" style="margin-top: 5px">
                                                <!--<input type="submit" value="save" class="qualitysave" name="save" onClick=""/>-->
                                                <input type="submit" value="submit" class="qualitysubmit" name="submit" />
                                                <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
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
                            <p style="padding:10px;margin:10px;">Once you submit the data it will not be editable<br><br>Click YES to submit, NO to edit the data</p><button class="plainButton" id="submitData">YES</button><button class="plainButton" id="cancelSubmit">NO</button>
                        </div>
                    </div>
                </div>
            </form>
            
        </div>
    </body>
</html>
