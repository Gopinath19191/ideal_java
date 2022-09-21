<%-- 
    Document   : applyallowance
    Created on : Feb 10, 2016, 11:43:40 AM
    Author     : 8000247
--%>

<%@page import="java.util.Calendar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

        <title></title>
        <style>
            table,td,th{
                border:1px solid #bfbfbf;
                border-collapse:collapse;
                text-align:center;
            }

            th{
                background-color: #fff;
            }
            #tab{
                margin:5px;
                width: 100%;
            }
            text{
                border:1px solid #bfbfbf;
            }
            #header{
                width: 97%;
                height:30px;
                border-bottom:1px;
                border-collapse:collapse;
                text-align:left;
                padding:20px 20px;
                color:#666;
                background-color: #f4f4f4;
            }
            #footer{
                height:50px;				
                text-align:center;
            }
            #go{
                margin-left:50px;
            }
            #export{
                margin-left:20px;
            }
            .save{
                padding:5px 10px 5px 10px;
            }
            .submit{
                padding:5px 10px 5px 10px;
            }
            #content{
                border:1px ;
            }
            .error{
                height:30px; 
                margin-top:10px;
                color:red;
            }
            .odd {
                background-color: #E6E6E6;
            }
            .even {
                background-color: #fff;
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

        </style>	
        <script>
            var s1=0,s2=0,s3=0,s4=0,s5=0,h=0;
            var hardship=0,shift1=0,shift2=0,shift3=0,transport=0;
            var jsonArr = [];
            <c:forEach items="${shift}" var="shift" varStatus="i">
                var type='${(shift.type)}';
                var shift='${(shift.shifts)}';
                if(type=='Hardship_allowance'){
                    hardship=${(shift.amount)};
                }
                if(type=='Shift_allowance'){                            
                    if(shift=='First_Shift'){
                        shift1=${(shift.amount)};
                    }
                    if(shift=='Second_Shift'){
                        shift2=${(shift.amount)};
                    }
                    if(shift=='Third_Shift'){
                        shift3=${(shift.amount)};
                    }
                }
                if(type=='Transport_allowance'){
                    transport=${(shift.amount)};
                }
            </c:forEach>
                $( window ).load(function() {
                    $("#dialog-message").hide();
                });
                $(document).ready(function(){
                    //$(".sucess-message").fadeOut(5000);
                    
                    $(".save").click(function(){
                       
                        var check_status = false;
                        var i;
                        $('.check:checked').each(function(id, value){
                            
                            i = $(value).attr('id');
                            var wayValue;
                            if(($('input:radio[name='+$("#way"+i).attr('name')+']:checked').val()!=undefined)){
                                wayValue =   $('input:radio[name='+$("#way"+i).attr('name')+']:checked').val()
                            }else{
                                wayValue = null;
                            }
                            jsonArr.push({
                                team_allo_id : i,
                                emp_id : $(".empId"+i).val(),
                                project_id :  $("#prj"+i).val(),
                                location : $('input:radio[name='+$(".lyes"+i).attr('name')+']:checked').val(),
                                general : $('#hs'+i).val(),
                                shift1_days : $('#1hs'+i).val(),
                                shift2_days : $('#2hs'+i).val(),
                                shift3_days : $('#3hs'+i).val(),
                                shift_amount : $('#vs'+i).val(),
                                hardship_amount : $('#vhs'+i).val(),
                                hs_amount : $('#amhs'+i).val(),
                                way : wayValue,
                                transport_amount : $('#transcyes'+i).val(),
                                total_amount : $('#tamhs'+i).val(),
                                remarks :$('#remarks'+i).val(),
                                status : $('#status'+i).val()
                            });
                            $('#selectedRows').val(JSON.stringify(jsonArr));
                            check_status = true;
                        })
                        $('#saveDetails').attr("action", "submit.htm");
                            
                        if(!check_status) {
                            alert("Please check the checkbox to continue");
                            return false;
                        } 
                    });
                    /////////////////////////
                    $("#selectall").click(function () {
                        $('.check').attr('checked', this.checked); 
                    });
                    $(".check").click(function(){		 
                        if($(".check").length == $(".check:checked").length) {
					
                        } else {
                            $("#selectall").removeAttr("checked");
                        }		 
                    });
 
                    $("input").focus(function(){
                            
                        if (window.event) {
                            var value=$(document.activeElement).val();
                            var s=$(document.activeElement).shift(window.event,document.activeElement);
                                   
                            var charCode = window.event.keyCode;
                            $(":input").keypress(function(event) {
                                var name=$(document.activeElement).attr('name');
                                var charCode = window.event.keyCode;
                                if(name == 'general_days'){
                                    var id=$(document.activeElement).attr('id');
                                    var v = $(".allocation"+id).attr('id');
                                    var data = $("#"+v).val();
                                }else{
                                    var id=$(document.activeElement).attr('class');
                                    var v = $(".allocationh"+id).attr('id');
                                    var data = $("#"+v).val();
                                }
                                $(".waycyes"+data).attr('checked', false);
                                $("#transcyes"+data).val('');
                                $("#tamhs"+data).val('');
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
                    $.fn.shift= function(e,t) 
                    {
                        var a=$(t).attr('class');
                        var name=$(t).attr('name');
                        if(name=='general_days')
                        {
                            var id=$(t).attr('id');
                            var v1=$("#1"+id).val();
                            var v2=$("#2"+id).val();
                            var v3=$("#3"+id).val();
                            var v4=$("#"+id).val();
                            var v5=$("#av"+id).val();
                            var v6=$("#employeeID"+id).val();
                            s1=v1*shift1;
                            s2=v2*shift2;
                            s3=v3*shift3;
                            s5=Number(v1)+Number(v2)+Number(v3)+Number(v4);
                            if(s5>v5){
                                $("#"+id).css("border","1px solid red");
                                $("#1"+id).css("border","1px solid red");
                                $("#2"+id).css("border","1px solid red");
                                $("#3"+id).css("border","1px solid red");
                                $(".error").html('The Employee '+v6+' is allocated only for '+v5+'days');
                            }
                            else{   
                                if(v4>20){
                                    h=hardship*20;
                                    $("#v"+id).val(h);
                                    $("#am"+id).val(h);
                                }
                                else{
                                    h=v4*hardship;
                                    $("#v"+id).val(h);
                                    $("#am"+id).val(s1+s2+s3+h);
                                }
                            }
                        }
                        else{
                            var v1=$("#1h"+a).val();
                            var v2=$("#2h"+a).val();
                            var v3=$("#3h"+a).val();
                            var v4=$("#h"+a).val();
                            var v5=$("#avh"+a).val();
                            var v6=$("#employeeIDh"+a).val();
                            $("#vh"+a).removeAttr("disabled");	
                            $("#h"+a).removeAttr("disabled");
                            s1=v1*shift1;
                            s2=v2*shift2;
                            s3=v3*shift3;
                            if(v4>20){
                                h=hardship*20;
                                $("#vh"+a).val(h);
                            }
                            else{
                                h=v4*hardship;
                                $("#vh"+a).val(h);
                            }
                            s5=Number(v1)+Number(v2)+Number(v3)+Number(v4);

                            if(s5>v5){
                                $("#1h"+a).css("border","1px solid red");
                                $("#2h"+a).css("border","1px solid red");
                                $("#3h"+a).css("border","1px solid red");
                                $(".error").html('The Employee '+v6+' is allocated only for '+v5+'days');
                            }
                            else{   
                                $("#v"+a).val(s1+s2+s3);
                                $("#amh"+a).val(s1+s2+s3+h);
                            }
                        }
                        s1=0,s2=0,s3=0,s5=0;
                    };
                    $.fn.removeError= function(t) 
                    {
                        var a=$(t).attr('class');
                        var name=$(t).attr('name');
                        if(name=='general_days')
                        {
                            var id=$(t).attr('id');
                            $("#"+id).css("border","1px solid #bfbfbf");
                            $("#1"+id).css("border","1px solid #bfbfbf");
                            $("#2"+id).css("border","1px solid #bfbfbf");
                            $("#3"+id).css("border","1px solid #bfbfbf");
                        }
                        else{
                            $("#1h"+a).css("border","1px solid #bfbfbf");
                            $("#2h"+a).css("border","1px solid #bfbfbf");
                            $("#3h"+a).css("border","1px solid #bfbfbf");
                                
                        }
                        $(".error").html('');
                    };
                        
                    $.fn.way= function(e) {
                        var allId=$(e).attr('id');
                        var id=$("#allocation"+allId).val();
                        var v1=$("#hs"+id).val();
                        var v2=$("#1hs"+id).val();
                        var v3=$("#2hs"+id).val();
                        var v4=$("#3hs"+id).val();
                        var valOfSrH = $("#amhs"+id).val();
                        s5=Number(v1)+Number(v2)+Number(v3)+Number(v4);
                        if(s5 == 'Infinity' || s5 == NaN || s5 == 'undefined' || s5 == 0 ){
                            $(".v"+getthevalue).val('0');
                        }else{
                            var value=$(e).val();
                            var getthevalue = $(e).attr('class');	
                            $(".v"+getthevalue).css("border","1px solid #bfbfbf");
                            $(".error").html('');
                            if(value=='1-Way'){
                                if(s5>=20){
                                    var amt= 1250;
                                    $(".v"+getthevalue).val(amt);
                                    sum = Number(valOfSrH)+amt;
                                    $("#tamhs"+id).val(sum);
                                }else{
                                    var amt=(transport/2)*s5;
                                    sum = Number(valOfSrH)+amt;
                                    $(".v"+getthevalue).val(Math.round(amt));
                                    $("#tamhs"+id).val(Math.round(sum));
                                }
                            }
                            else if(value=='No-Cab'){
                                if(s5>=20){
                                    var amt=2500;
                                    $(".v"+getthevalue).val(amt);
                                    sum = Number(valOfSrH)+amt;
                                    $("#tamhs"+id).val(sum);
                                }else{
                                    var amt=transport*s5;
                                    sum = Number(valOfSrH)+amt;
                                    $(".v"+getthevalue).val(Math.round(amt));
                                    $("#tamhs"+id).val(Math.round(sum));
                                }
                            }
                            else{
                                $(".v"+getthevalue).val('');
                                $(".v"+getthevalue).attr("readonly", "readonly");
                                sum = Number(valOfSrH);
                                $("#tamhs"+id).val(Math.round(sum));
                            }
                        }
                    };
                        
                        
                    /*******************************/
                    $(".submit").click(function(e){
                        e.preventDefault();
                        var i;
                        var amount;
                        var d;
                        if($('.check:checked').length == 0){
                            alert('Please select checkbox to continue');
                            return false;
                        }
                           
                        $('.check:checked').each(function(id, value){
                            i = $(value).attr('id');
                            amount=$("#amhs"+i).val();
                            d=validateDetails(i,amount);
                        })
                        if(d==false){
                            return false;
                            jsonArr = [];
                        }else{
                            //
                            //
                            $(".alertboxWrap").show();
                        }
                    });
                    $("#x").click(function(){
                        jsonArr = [];
                        $(".alertboxWrap").hide();
                        return false;
                    });	
                    $("#cancel").click(function(){
                        jsonArr = [];
                        $(".alertboxWrap").hide();
                        return false;
                    });		
                    $("#ok").click(function(){
                        $('#saveDetails').attr("action", "submit.htm");
                        $(".alertboxWrap").hide();
                    });	
                    /***********************/
                        
                        
                        
                    
                });
                
                function displayTransport(e) {
                    var value=$(e).val();
                    var getthevalue = $(e).attr('class');	
                    if(value=='YES'){			
                        $("#trans"+getthevalue).val('');
                        $("#trans"+getthevalue).attr("readonly", "readonly");
                        $(".way"+getthevalue).attr("disabled", "disabled"); 
                        $(".way"+getthevalue).attr('checked', false); 
                    }
                    else{
                        $("#trans"+getthevalue).removeAttr("disabled");	
                        $(".way"+getthevalue).removeAttr("disabled");	
                    }
                };	
		 		
                function getProject(selectedValue) {
                    $(".error").html('');
                    $("#project").val('');
                    var yr=$("#selYear").val();
                    var mon=$("#selMon").val();
                    if(selectedValue != "") {
                        $.ajax({                   
                            url: './getProject.htm',
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
                function getCustomer(selectedValue) {
                    $(".error").html('');
                    $("#customer").val('');
                    $("#project").val('');
                    $("#SBU_SUB_Id").val('');
                    $("#SBU_Id").val('');
                    var yr=$("#selYear").val();
                    if(selectedValue != "") {
                        $.ajax({                   
                            url: './getCustomer.htm',
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
                function getSUB_SBU(selectedValue) {
                    $("#SBU_SUB_Id").val('');
                    if(selectedValue != "") {
                        $.ajax({                   
                            url: './getSUB_SBU.htm',
                            type: "post",
                            async: false,
                            data: ({SBU_Id:selectedValue}),
                            dataType:'html',
                            success: function(ajaxObj) {
                                $("#SBU_SUB_Id").html('').html(ajaxObj);
                            }
                        });
                    }
                };
                function getMonth() {
                    $("#selMon").val('');
                    $("#customer").val('');
                    $("#project").val('');
                    $("#SBU_SUB_Id").val('');
                    $("#SBU_Id").val('');
                    $(".error").html('');
                    $.ajax({                   
                        url: './getMonth.htm',
                        type: "post",
                        async: false,
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#selMon").html('').html(ajaxObj);
                        }
                    });
               
                };
                function getTeamMemebrs(){
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
                    else if(cus==''||cus=='null')
                    {
                        $(".error").html('Please select customer');
                        return false;
                    }
                                                                                                
                    else{
                        $('#getDetails').attr("action", "getEmployeeDetails.htm");
                        document.getDetails.submit();                       
                    }
                        
                };
                function getExcel(){
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
                    else{
                        $('#getDetails').attr("action", "getExcel.htm");
                        document.getDetails.submit();                      
                    }   
                };
                function dis(e) {
                    var value=$(e).val();
                    var getthevalue = $(e).attr('class');
                    var getthevalueId = $(e).attr('id');
                    if(value=='NO'){
                        var temp1 = $("#vh"+getthevalueId).val();
                        var temp2 = $("#amh"+getthevalueId).val();
                        var temp3 = $("#tamh"+getthevalueId).val();
                        $(".loc"+getthevalue).val('');
                        $(".loc"+getthevalue).attr("readonly", "readonly");
                        $("#amh"+getthevalueId).val(Number(temp2)-Number(temp1));
                        $("#tamh"+getthevalueId).val(Number(temp3)-Number(temp1));
                    }
                    else{
                        $(".loc"+getthevalue).removeAttr("readonly");    
                    }
                };
                
                function validateDetails(i,amount){
                    if(($('input:radio[name='+$(".lyes"+i).attr('name')+']:checked').val())=='YES'&&(($("#amhs"+i).val())==0 ||($("#amhs"+i).val())=='')){                                                            
                        $("#1hs"+i).css("border","1px solid red");
                        $("#2hs"+i).css("border","1px solid red");
                        $("#3hs"+i).css("border","1px solid red");
                        $(".error").html('Please enter the days');
                        return false;
                    }
                    if(amount==0){
                        $("#1hs"+i).css("border","1px solid red");
                        $("#2hs"+i).css("border","1px solid red");
                        $("#3hs"+i).css("border","1px solid red");
                        $(".error").html('Please enter the days');
                        return false;
                    }
                    if(($('input:radio[name='+$("#way"+i).attr('name')+']:checked').val()==undefined)){
                        $("#transcyes"+i).css("border","1px solid red");
                        $(".error").html('Please select the Way of transport if cab facility has not been availed');
                        return false;
                    }
                    if($("#tamhs"+i).val() == 0){
                        $("#tamhs"+i).css("border","1px solid red");
                        $(".error").html('Enter valid input');
                        return false;
                    }
                    else if($('#'+i).prop("checked")==false){
                        $("#"+i).css("outline","1px solid red");
                        $(".error").html('Please select CheckBox');
                        return false;
                    }
                    else{
                        var wayValue;
                        if(($('input:radio[name='+$("#way"+i).attr('name')+']:checked').val()!=undefined)){
                            wayValue =   $('input:radio[name='+$("#way"+i).attr('name')+']:checked').val()
                        }else{
                            wayValue = null;
                        }
                        jsonArr.push({
                            team_allo_id : i,
                            emp_id : $(".empId"+i).val(),
                            project_id :  $("#prj"+i).val(),
                            location : $('input:radio[name='+$(".lyes"+i).attr('name')+']:checked').val(),
                            general : $('#hs'+i).val(),
                            shift1_days : $('#1hs'+i).val(),
                            shift2_days : $('#2hs'+i).val(),
                            shift3_days : $('#3hs'+i).val(),
                            shift_amount : $('#vs'+i).val(),
                            hardship_amount : $('#vhs'+i).val(),
                            hs_amount : $('#amhs'+i).val(),
                            way : wayValue,
                            transport_amount : $('#transcyes'+i).val(),
                            total_amount : $('#tamhs'+i).val(),
                            remarks : $('#remarks'+i).val(),
                            status : $('#status'+i).val()
                        });
                        $('#selectedRows').val(JSON.stringify(jsonArr));
                        return true;
                        alert($('#selectedRows').val());
                    }
                };
                //           
        </script>
    </head>
    <body style="margin-top: 10px;">
        <div class="error"></div>
        <div id="msg"><div class="sucess-message">${succcessMsg}</div></div>
        <div id="content">
            <%
                int flag = 0;
                String des = (String) session.getAttribute("designation");
                if (des == "13" || des.equals("13")) {
                    flag = 1;
                }
            %>
            <div id="header">
                <form name="getDetails" id="getDetails" method="post" action="#">
                    <label style="margin-left:10px;">Year </label>
                    <select name="selYear" id="selYear" size="1" style="width:10%" onchange="getMonth()">
                        <option align="center" value="">--Year--</option>
                        <c:forEach items="${yearsList}" var="yearsList" varStatus="sbuitr">
                            <option value="${yearsList.key}"${(filterData.year == yearsList.value) ? 'selected':''}>${yearsList.value}</option>
                        </c:forEach>
                    </select>    
                    <label style="margin-left:10px;">Month </label>
                    <select name="selMon" id="selMon" size="1" style="width:10%" onchange="getCustomer(this.value)" >
                        <option align="center" value="">--Month--</option>
                        <c:forEach items="${monthsList}" var="month">
                            <option ${selMon} value="${month.key}"${(filterData.month == month.key) ? 'selected':''}>${month.value}</option>
                        </c:forEach>
                    </select>
                    <%
                        if (flag == 1) {
                    %>
                    <label style="margin-left:10px;">SBU </label>
                    <select name="SBU_Id" id="SBU_Id" size="1" style="width:10%" onchange="getSUB_SBU(this.value)">
                        <option align="center" value="">--SBU--</option>
                        <c:forEach items="${SBU_List}" var="SBU_List" >
                            <option value="${SBU_List.SBU_Id}"${(filterData.SBU_Id == SBU_List.SBU_Id) ? 'selected':''}>${SBU_List.SBU_Name}</option>
                        </c:forEach>
                    </select>  
                    <label style="margin-left:10px;">SUB SBU </label>
                    <select name="SBU_SUB_Id" id="SBU_SUB_Id" size="1" style="width:10%">
                        <option align="center" value="">--SUB SBU--</option>
                        <c:forEach items="${SBU_SUB_List}" var="SBU_SUB_List" >
                            <option value="${SBU_SUB_List.SBU_SUB_Id}"${(filterData.SBU_SUB_Id == SBU_SUB_List.SBU_SUB_Id) ? 'selected':''}>${SBU_SUB_List.SBU_SUB_Name}</option>
                        </c:forEach>
                    </select>  
                    <%                        } else {
                    %>
                    <label>Customer Name </label>
                    <select id="customer" name="customer" style="width:15%" onchange="getProject(this.value)" >
                        <option value="">--All--</option>
                        <c:forEach items="${customer}" var="customer" varStatus="i">
                            <option value="${(customer.cus_id)}" ${(customer.cus_id==filterData.cus_id) ? 'selected':''}>${customer.customer_name}</option>
                        </c:forEach>
                    </select>
                    <label style="margin-left:10px;">Project Name </label>
                    <select id="project" name="project" style="width:15%" >
                        <option value="">--All--</option>
                        <c:forEach items="${project}" var="project">
                            <option value="${(project.project_id)}"${(project.project_id==filterData.project_id) ? 'selected':''}>${project.project_name}</option>
                        </c:forEach>
                    </select>
                    <%                            }
                    %>

                    <input type="submit" style="width:60px; height: 30px" id="go" name="submit"  value="Go" onClick="return getTeamMemebrs();"/>
                    <input type="submit" style="width:60px; height: 30px" id="export" name="export"  value="Export" onClick="return getExcel();"/>
                </form>
            </div>
            <form name="saveDetails" id="saveDetails" method="post" action="#">
                <div id="tabledetails">
                    <%
                        Calendar todaydate = Calendar.getInstance();
                        int i = todaydate.get(Calendar.DATE);
                        int j = todaydate.get(Calendar.MONTH);
                        request.setAttribute("dt", i);
                        request.setAttribute("cmt", j + 1);
                        request.setAttribute("lmt", j);
                    %> 
                    <fmt:formatDate var="dt" value='${todydate}' pattern="d" /> 
                    <fmt:formatDate var="cmt" value='${todydate}' pattern="m" /> 
                    <table id="tab">
                        <%
                        if (flag == 1) {
                    %>
                    <%                        } else {
                    %>
                    <%
                        Calendar cal = Calendar.getInstance();
                        int date = cal.get(Calendar.DATE);
                        int lmnth = cal.get(Calendar.MONTH);
                        int cmnth = lmnth + 1;
                        String mon = (String) session.getAttribute("month");
                        String scount = (String) request.getAttribute("submittedCount");
                        String dcount = (String) request.getAttribute("detailsCount");
                        if (mon != null && scount != null && dcount != null) {
                            int mn = Integer.valueOf(mon);
                            int sc = Integer.valueOf(scount);
                            int dc = Integer.valueOf(dcount);
                            if (((date <= 18 && (mn == lmnth ||(mn==12 && lmnth==0)))) && sc != dc) {
                    %>
                    <div id="footer" style="margin-top: 5px">
                        <input type="submit" value="save" class="save" name="save" onClick=""/>
                        <input type="submit" value="submit" class="submit" name="submit" />
                        <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
                    </div>
                    <%                                   }
                            }
                        }
                    %>
                        <tr>
                            <%
                                if (flag == 1) {
                            %>

                            <%                            } else {
                            %>
                            <th rowspan="2"><input type="checkbox" id="selectall"></th>
                                <%                                }
                                %>
                            <th rowspan="2">Emp Number</th>
                            <th rowspan="2">Employee Name</th>
                            <!--				<th rowspan="2" style="width: 77px;">Allocation Date</th>
                                                            <th rowspan="2" style="width: 77px;">Allocation End Date</th>-->
                            <th rowspan="2" style="width: 77px;">Project Code</th>
                            <th rowspan="2" style="width: 85px;">Project Work Location >30km from HTL Location</th>
                            <th colspan="4">No of Days worked in Shift</th>
                            <th rowspan="2">Shift Allowance</th>
                            <th rowspan="2" style="width: 70px;">Hardship Allowance</th>
                            <th rowspan="2" style="width: 70px;">Shift/<br>Hardship<br> Allowance</th>
                            <th rowspan="2" style="width: 50px;">Cab Facility Availed</th>
                            <!--<th rowspan="2">Mode of Transport</th>-->
                            <th rowspan="2" style="width: 70px;">Transport Allowance<br>(Shift <br>or<br> Hardship)</th>
                            <th rowspan="2">Total Amount</th>
                            <th rowspan="2">Remarks</th>
                        </tr>
                        <tr>
                            <th style="width: 65px;">General Shift</th>
                            <th style="width: 65px;">First Shift</th>
                            <th style="width: 65px;">Second Shift</th>
                            <th style="width: 65px;">Third Shift</th>				
                        </tr>
                        
                        <c:choose>
                            <c:when test="${fn:length(details)!=0}">
                                <c:forEach items="${details}" var="details" varStatus="i"> 
                                    <input type="hidden" value="${details.team_allo_id}" name="allocation" id="allocationway${details.team_allo_id}" class="allocationhs${details.team_allo_id}"/>
                                    <input type="hidden" value="${details.id}" name="empId" class="empId${details.team_allo_id}" id="${details.id}"/>
                                    <input type="hidden" value="&${details.id}&" name="hiddenId" />
                                    <input type="hidden" value="${details.status}" name="status" id="status${details.team_allo_id}"/>
                                    <input type="hidden" value="${details.allocation_days}" />
                                    <input type="hidden" value="${details.cus_id}" name="customer${details.team_allo_id}" />
                                    <input type="hidden" value="${details.project_id}" name="project${details.team_allo_id}" />
                                    <input type="hidden" value="${details.cus_id}" name="customer" id="cus${details.team_allo_id}" />
                                    <input type="hidden" value="${details.project_id}" name="project" id="prj${details.team_allo_id}"/>
                                    <input type="hidden" value="${details.month}" name="selMon" />
                                    <input type="hidden" value="${details.year}" name="selYear" />
                                    <input type="hidden" value="${details.working_days}" id="wrk_dys"/>
                                    <%
                                        if (flag == 1) {
                                    %>
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even">
                                            </c:when>
                                            <c:otherwise>
                                            <tr class="odd">
                                            </c:otherwise>
                                        </c:choose>   
                                        <td>${details.emp_id}</td>
                                        <td>${details.emp_name}</td>
                                        <td>${details.prj_code}</td>
                                        <td>
                                            <c:if test="${details.location=='YES'}"> 
                                                <div>Yes <input type="radio" name='location${details.team_allo_id}' id='s${details.team_allo_id}' class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" checked disabled/></div>
                                                <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);"  disabled /></div>
                                                </c:if>
                                                <c:if test="${details.location=='NO'}">    
                                                <div>Yes <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" disabled /></div>
                                                <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);" checked disabled /></div>
                                                </c:if>
                                        </td>
                                        <td>${details.general}</td>
                                        <td>${details.shift1_days}</td>
                                        <td>${details.shift2_days}</td>
                                        <td>${details.shift3_days}</td>
                                        <td>${details.shift_amount}</td>
                                        <td>${details.hardship_amount}</td>
                                        <td>${details.hs_amount}</td>
                                        <td>
                                            <c:if test="${details.way == null }">       
                                                <div style="width: 90px;">
                                                    One Way <input type="radio" name='way${details.team_allo_id}'value='1-Way' disabled/>
                                                </div>
                                                <div>Two way <input type="radio" name='way${details.team_allo_id}' value='2-Way' disabled /></div>				
                                                <div>No Cab<input type="radio" name='way${details.team_allo_id}' value='No-Cab' disabled style="margin-left:15px"/></div>				
                                                </c:if>
                                                <c:if test="${details.way=='1-Way'}">
                                                <div style="width: 90px;">
                                                    One Way <input type="radio" name='way${details.team_allo_id}'value='1-Way' checked disabled />
                                                </div>
                                                <div>Two way <input type="radio" name='way${details.team_allo_id}' value='2-Way' disabled /></div>				
                                                <div>No Cab<input type="radio" name='way${details.team_allo_id}' value='No-Cab' disabled style="margin-left:15px"/></div>			
                                                </c:if>
                                                <c:if test="${details.way=='2-Way'}">       
                                                <div style="width: 90px;">
                                                    One Way <input type="radio" name='way${details.team_allo_id}'value='1-Way' disabled />
                                                </div>
                                                <div>Two way <input type="radio" name='way${details.team_allo_id}' value='2-Way' checked disabled /></div>				
                                                <div>No Cab<input type="radio" name='way${details.team_allo_id}' value='No-Cab' disabled style="margin-left:15px"/></div>			
                                                </c:if>
                                                <c:if test="${details.way=='No-Cab'}">       
                                                <div style="width: 90px;">
                                                    One Way <input type="radio" name='way${details.team_allo_id}'value='1-Way' disabled />
                                                </div>
                                                <div>Two way <input type="radio" name='way${details.team_allo_id}' value='2-Way' disabled /></div>				
                                                <div>No Cab<input type="radio" name='way${details.team_allo_id}' value='No-Cab' checked disabled style="margin-left:15px"/></div>			
                                                </c:if>
                                        </td>
                                        <td>${details.transport_amount}</td>
                                        <td>${details.total_amount}</td>
                                        <td>${details.remarks}</td>
                                    </tr>
                                    <%                        } else {
                                    %>
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even" value="${lmt}">
                                            </c:when>
                                            <c:otherwise>
                                            <tr class="odd" value="${lmt}">
                                            </c:otherwise>
                                        </c:choose>  
                                        <c:choose>
                                            <c:when test="${(dt <=18 && (details.month==lmt || (details.month=='12' && lmt=='0')))}">
                                                <c:choose>
                                                    <c:when test="${(i.count) % 2 == 0}">
                                                    <tr class="even">
                                                    </c:when>
                                                    <c:otherwise>
                                                    <tr class="odd">
                                                    </c:otherwise>
                                                </c:choose>         
                                                <td>
                                                    <c:if test="${details.status==null || details.status=='0'}">
                                                        <input type="checkbox" name="checkbox" class="check" value="&${details.id}&" id="${details.team_allo_id}" onclick=""/>
                                                    </c:if>   
                                                    <c:if test="${details.status=='1'}">

                                                    </c:if> 
                                                </td>
                                                <td>
                                                    <input type="hidden" id="employeeIDhs${details.team_allo_id}" value="${details.emp_id}"/>
                                                    ${details.emp_id}
                                                </td>
                                                <td>
                                                    ${details.emp_name}
                                                </td>
                                                <td>${details.prj_code}</td>
                                                <c:if test="${details.status=='0'||details.status==null}">
                                                    <td>
                                                        <c:if test="${details.location==null}">
                                                            <div>Yes <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" checked /></div>
                                                            <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);"  /></div>
                                                            </c:if>
                                                            <c:if test="${details.location=='YES'}"> 
                                                            <div>Yes <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" checked /></div>
                                                            <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);"  /></div>
                                                            </c:if>
                                                            <c:if test="${details.location=='NO'}">    
                                                            <div>Yes <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" /></div>
                                                            <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);" checked /></div>
                                                            </c:if>
                                                    </td>	

                                                    <td>
                                                        <input type="text" style="width: 40px; border:1px solid #bfbfbf;" name="general_days" class="loclyes${details.team_allo_id}" id="hs${details.team_allo_id}" value="${details.general}"
                                                               onkeyup="$(this).shift(event,this);" onkeydown="$(this).removeError(this);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" style="width: 40px;  border:1px solid #bfbfbf;" name="shift1_days" class="s${details.team_allo_id}" id="1hs${details.team_allo_id}" value="${details.shift1_days}"                                          
                                                               onkeyup="$(this).shift(event,this);"  onkeydown="$(this).removeError(this);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" style="width: 40px; border:1px solid #bfbfbf;" name="shift2_days" class="s${details.team_allo_id}" id="2hs${details.team_allo_id}" value="${details.shift2_days}"
                                                               onkeyup="return $(this).shift(event,this);"  onkeydown="$(this).removeError(this);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" style="width: 40px; border:1px solid #bfbfbf;" name="shift3_days" class="s${details.team_allo_id}" id="3hs${details.team_allo_id}" value="${details.shift3_days}"
                                                               onkeyup="return $(this).shift(event,this);"  onkeydown="$(this).removeError(this);"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" style="width: 40px; border:1px solid #bfbfbf;" name="shift_amount" id="vs${details.team_allo_id}" readonly value="${details.shift_amount}"/>
                                                        <input type="hidden" id="avhs${details.team_allo_id}" value="${details.allocation_days}" size="1"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" style="width: 40px;  border:1px solid #bfbfbf;" name="hardship_amount" id="vhs${details.team_allo_id}" class="loclyes${details.team_allo_id}" 
                                                               value="${details.hardship_amount}" readonly size="1"/>
                                                    </td>
                                                    <td>
                                                        <input type="text" style="width: 40px;  border:1px solid #bfbfbf;" name="hs_amount" id="amhs${details.team_allo_id}" 
                                                               value="${details.hs_amount}" readonly size="1">
                                                    </td>
                                                    <td>
                                                        <c:if test="${details.way==null}">       
                                                            <div style="width: 90px;">
                                                                One Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                               class='waycyes${details.team_allo_id}' value='1-Way' onClick="$(this).way(this);" />
                                                            </div>
                                                            Two Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                           class='waycyes${details.team_allo_id}' value='2-Way' onClick="$(this).way(this);" />
                                                            <div>No Cab<input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                              class='waycyes${details.team_allo_id}' value='No-Cab' onClick="$(this).way(this);" style="margin-left:15px" /></div>				
                                                            </c:if> 
                                                            <c:if test="${details.way == '1-Way'}">
                                                            <div style="width: 90px;">
                                                                One Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                               class='waycyes${details.team_allo_id}' value='1-Way' onClick="$(this).way(this);" checked />
                                                            </div>
                                                            Two Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                           class='waycyes${details.team_allo_id}' value='2-Way' onClick="$(this).way(this);" />
                                                            <div>No Cab<input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                              class='waycyes${details.team_allo_id}' value='No-Cab' onClick="$(this).way(this);" style="margin-left:15px" /></div>
                                                            </c:if>
                                                            <c:if test="${details.way == '2-Way'}">       
                                                            <div style="width: 90px;">
                                                                One Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                               class='waycyes${details.team_allo_id}' value='1-Way' onClick="$(this).way(this);" />
                                                            </div>
                                                            Two Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                           class='waycyes${details.team_allo_id}' value='2-Way' onClick="$(this).way(this);" checked />
                                                            <div>No Cab<input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                              class='waycyes${details.team_allo_id}' value='No-Cab' onClick="$(this).way(this);" style="margin-left:15px"/></div>
                                                            </c:if> 

                                                        <c:if test="${details.way == 'No-Cab'}">       
                                                            <div style="width: 90px;">
                                                                One Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                               class='waycyes${details.team_allo_id}' value='1-Way' onClick="$(this).way(this);" />
                                                            </div>
                                                            Two Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                           class='waycyes${details.team_allo_id}' value='2-Way' onClick="$(this).way(this);" />
                                                            <div>No Cab<input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                              class='waycyes${details.team_allo_id}' value='No-Cab' onClick="$(this).way(this);" checked style="margin-left:15px" /></div>				
                                                            </c:if>  												
                                                    </td>
                                                    <td>
                                                        <input type="text" style="width: 40px; border:1px solid #bfbfbf;" name="transport_amount" id="transcyes${details.team_allo_id}" value="${details.transport_amount}"
                                                               class="vwaycyes${details.team_allo_id}" readonly size="1"/>
                                                    </td>   
                                                    <td>
                                                        <input type="text" style="width: 40px;  border:1px solid #bfbfbf;" name="total_amount" id="tamhs${details.team_allo_id}" 
                                                               value="${details.total_amount}" readonly size="1">
                                                    </td>
                                                    <td>
                                                        <textarea rows="1" cols="7" name="remarks" id="remarks${details.team_allo_id}">${details.remarks}</textarea>
                                                    </td> 
                                                </c:if>
                                                <c:if test="${details.status == '1'}">
                                                    <td>
                                                        <c:if test="${details.location == 'YES'}"> 
                                                            <div>Yes <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" checked disabled/></div>
                                                            <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);"  disabled/></div>
                                                            </c:if>
                                                            <c:if test="${details.location == 'NO'}">    
                                                            <div>Yes <input type="radio" name='location${details.team_allo_id}'id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" disabled/></div>
                                                            <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);" checked disabled/></div>
                                                            </c:if>  
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="general_days" value="${details.general}"/>
                                                        <c:choose>
                                                            <c:when test="${(details.general)%10 != '0'}" >
                                                                <fmt:parseNumber var="i" type="number" value="${details.general}" />
                                                                ${i}
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${details.general}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="shift1_days" value="${details.shift1_days}"/>
                                                        <c:choose>
                                                            <c:when test="${details.shift1_days%10!= '0' }" >
                                                                <fmt:parseNumber var="i" type="number" value="${details.shift1_days}" />
                                                                ${i}
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${details.shift1_days}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="shift2_days" value="${details.shift2_days}"/>
                                                        <c:choose>
                                                            <c:when test="${details.shift2_days%10 != '0'}" >
                                                                <fmt:parseNumber var="i" type="number" value="${details.shift2_days}" />
                                                                ${i}
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${details.shift2_days}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="shift3_days" value="${details.shift3_days}"/>
                                                        <c:choose>
                                                            <c:when test="${details.shift3_days%10 != '0'}" >
                                                                <fmt:parseNumber var="i" type="number" value="${details.shift3_days}" />
                                                                ${i}
                                                            </c:when>
                                                            <c:otherwise>
                                                                ${details.shift3_days}
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>

                                                    <td>
                                                        <input type="hidden" name="shift_amount" value="${details.shift_amount}"/>
                                                        ${details.shift_amount}
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="hardship_amount" value="${details.hardship_amount}"/>
                                                        ${details.hardship_amount}
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="hs_amount" value="${details.hs_amount}"/>
                                                        ${details.hs_amount}
                                                    </td>
                                                    <td>
                                                        <c:if test="${details.way==null}">       
                                                            <div style="width: 90px;">
                                                                One Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                               class='waycyes${details.team_allo_id}' value='1-Way' onClick="$(this).way(this);" disabled/>
                                                            </div>
                                                            Two Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                           class='waycyes${details.team_allo_id}' value='2-Way' onClick="$(this).way(this);" disabled/>
                                                            <div>No Cab<input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                              class='waycyes${details.team_allo_id}' value='No-Cab' onClick="$(this).way(this);" disabled style="margin-left:15px"/></div>				
                                                            </c:if> 
                                                            <c:if test="${details.way=='1-Way'}">
                                                            <div style="width: 90px;">
                                                                One Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                               class='waycyes${details.team_allo_id}' value='1-Way' onClick="$(this).way(this);" checked disabled/>
                                                            </div>
                                                            Two Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                           class='waycyes${details.team_allo_id}' value='2-Way' onClick="$(this).way(this);" disabled/>
                                                            <div>No Cab<input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                              class='waycyes${details.team_allo_id}' value='No-Cab' onClick="$(this).way(this);" disabled style="margin-left:15px"/></div>
                                                            </c:if>
                                                            <c:if test="${details.way=='2-Way'}">       
                                                            <div style="width: 90px;">
                                                                One Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                               class='waycyes${details.team_allo_id}' value='1-Way' onClick="$(this).way(this);" disabled/>
                                                            </div>
                                                            Two Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                           class='waycyes${details.team_allo_id}' value='2-Way' onClick="$(this).way(this);" checked disabled/>
                                                            <div>No Cab<input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                              class='waycyes${details.team_allo_id}' value='No-Cab' onClick="$(this).way(this);" disabled style="margin-left:15px"/></div>
                                                            </c:if> 

                                                        <c:if test="${details.way=='No-Cab'}">       
                                                            <div style="width: 90px;">
                                                                One Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                               class='waycyes${details.team_allo_id}' value='1-Way' onClick="$(this).way(this);" disabled/>
                                                            </div>
                                                            Two Way <input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                           class='waycyes${details.team_allo_id}' value='2-Way' onClick="$(this).way(this);" disabled/>
                                                            <div>No Cab<input type="radio" name='way${details.team_allo_id}' id='way${details.team_allo_id}'
                                                                              class='waycyes${details.team_allo_id}' value='No-Cab' onClick="$(this).way(this);" checked disabled style="margin-left:15px"/></div>				
                                                            </c:if>  												
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="transport_amount" value="${details.transport_amount}"/>
                                                        ${details.transport_amount}
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="total_amount" value="${details.total_amount}"/>
                                                        ${details.total_amount}
                                                    </td>
                                                    <td>
                                                        <input type="hidden" name="remarks" value="${details.remarks}"/>
                                                        ${details.remarks}
                                                    </td>
                                                </c:if>
                                            </c:when>
                                            <c:otherwise>
                                                <c:choose>
                                                    <c:when test="${(i.count) % 2 == 0}">
                                                    <tr class="even">
                                                    </c:when>
                                                    <c:otherwise>
                                                    <tr class="odd">
                                                    </c:otherwise>
                                                </c:choose>   
                                                <td></td>
                                                <td>${details.emp_id}</td>
                                                <td>${details.emp_name}</td>
                                                <td>${details.prj_code}</td>
                                                <td>
                                                    <c:if test="${details.location=='YES'}"> 
                                                        <div>Yes <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" checked/></div>
                                                        <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);"  /></div>
                                                        </c:if>
                                                        <c:if test="${details.location=='NO'}">    
                                                        <div>Yes <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='YES' onClick="dis(this);" /></div>
                                                        <div>No <input type="radio" name='location${details.team_allo_id}' id="s${details.team_allo_id}" class='lyes${details.team_allo_id}' value='NO' onClick="dis(this);" checked /></div>
                                                        </c:if>
                                                </td>
                                                <td>${details.general}</td>
                                                <td>${details.shift1_days}</td>
                                                <td>${details.shift2_days}</td>
                                                <td>${details.shift3_days}</td>
                                                <td>${details.shift_amount}</td>
                                                <td>${details.hardship_amount}</td>
                                                <td>${details.hs_amount}</td>
                                                <td>
                                                    <c:if test="${details.way == null }">       
                                                        <div style="width: 90px;">
                                                            One Way <input type="radio" name='way${details.team_allo_id}'value='1-Way'/>
                                                        </div>
                                                        <div>Two way <input type="radio" name='way${details.team_allo_id}' value='2-Way'  /></div>				
                                                        <div>No Cab<input type="radio" name='way${details.team_allo_id}' value='No-Cab' style="margin-left:15px" /></div>				
                                                        </c:if>
                                                        <c:if test="${details.way=='1-Way'}">
                                                        <div style="width: 90px;">
                                                            One Way <input type="radio" name='way${details.team_allo_id}'value='1-Way' checked />
                                                        </div>
                                                        <div>Two way <input type="radio" name='way${details.team_allo_id}' value='2-Way'  /></div>				
                                                        <div>No Cab<input type="radio" name='way${details.team_allo_id}' value='No-Cab' style="margin-left:15px" /></div>			
                                                        </c:if>
                                                        <c:if test="${details.way=='2-Way'}">       
                                                        <div style="width: 90px;">
                                                            One Way <input type="radio" name='way${details.team_allo_id}'value='1-Way'/>
                                                        </div>
                                                        <div>Two way <input type="radio" name='way${details.team_allo_id}' value='2-Way' checked /></div>				
                                                        <div>No Cab<input type="radio" name='way${details.team_allo_id}' value='No-Cab' style="margin-left:15px" /></div>			
                                                        </c:if>
                                                        <c:if test="${details.way=='No-Cab'}">       
                                                        <div style="width: 90px;">
                                                            One Way <input type="radio" name='way${details.team_allo_id}'value='1-Way'/>
                                                        </div>
                                                        <div>Two way <input type="radio" name='way${details.team_allo_id}' value='2-Way'  /></div>				
                                                        <div>No Cab<input type="radio" name='way${details.team_allo_id}' value='No-Cab' style="margin-left:15px" checked /></div>			
                                                        </c:if>
                                                </td>
                                                <td>${details.transport_amount}</td>
                                                <td>${details.total_amount}</td>
                                                <td>${details.remarks}</td>
                                            </tr>
                                        </c:otherwise>      
                                    </c:choose>
                                    <%                            }
                                    %>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${fn:length(details)==0}">
                                    <tr>
                                        <td colspan="17">No details to display</td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </table>
                    <div class="error"></div>
                    <%
                        if (flag == 1) {
                    %>
                    <%                        } else {
                    %>
                    <%
                        Calendar cal = Calendar.getInstance();
                        int date = cal.get(Calendar.DATE);
                        int lmnth = cal.get(Calendar.MONTH);
                        int cmnth = lmnth + 1;
                        String mon = (String) session.getAttribute("month");
                        String scount = (String) request.getAttribute("submittedCount");
                        String dcount = (String) request.getAttribute("detailsCount");
                        if (mon != null && scount != null && dcount != null) {
                            int mn = Integer.valueOf(mon);
                            int sc = Integer.valueOf(scount);
                            int dc = Integer.valueOf(dcount);
                            if (((date <= 18 && mn == lmnth)) && sc != dc) {
                    %>
                    <div id="footer">
                        <input type="submit" value="save" class="save" name="save" onClick=""/>
                        <input type="submit" value="submit" class="submit" name="submit" />
                        <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
                    </div>
                    <%                                   }
                            }
                        }
                    %>
                    <div class="alertboxWrap">
                        <div class="alertbox">
                            <p style="width: 38px;">Submit!</p>
                            <button id="x">x</button>
                            <p>Once you submit the data it will not be editable.Click Proceed to submit the data</p>
                            <button name="ok" id="ok">Proceed</button>
                            <button id="cancel">Cancel</button>
                        </div>
                    </div>
                </div>	

            </form>
        </div>
    </body>
</html>