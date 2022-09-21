<%-- 
    Document   : employeeList
    Created on : Jun 15, 2016, 2:53:01 PM
    Author     : 16365
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RMG Updation Screen</title>
    </head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />

    <script>
        $(document).ready(function() {
            $(".sucess-message").fadeOut(5000);
            $("#employeeSearch").autocomplete("employeeSearch.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });
            $("#employeeSearch").result(function(event, data, formatted) {
                if (data) {
                    $("#employeeId").val(data[1]);
                }
            });
            $("#managerSearch").autocomplete("employeeSearch.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });
            $("#managerSearch").result(function(event, data, formatted) {
                if (data) {
                    $("#managerId").val(data[1]);
                }
            });
            $("#datepicker").datepicker({
                changeMonth: true,
                changeYear: true,
                disabled: true,
                maxDate : new Date(),
                dateFormat: 'dd-mm-yy'
            });
            $( "#export" ).click(function() {
                $( "#searchData" ).attr("action","exportExcel.htm");
            });
            $( ".go" ).click(function() {
                if($("#employeeSearch").val() == "" || $("#employeeSearch").val() == "Employee (ID/Name)"){
                    $("#employeeId").val("");
                }   
                if($("#managerSearch").val() == "" || $("#managerSearch").val() == "Manager (ID/Name)"){
                    $("#managerId").val("");
                }
                $( "#searchData" ).attr("action","employeeList.htm");
            });
            $( ".reset" ).click(function() {                
                $("#employeeSearch").val("");
                $("#managerSearch").val("");
                $("#employeeId").val("");
                $("#managerId").val("");
                $('#baseLocationId').val("");
                $("#workLocationId").val("");
                $( "#searchData" ).attr("action","employeeList.htm");
            });
           
        
        });
        function getCusAddress(selectedValue) {
            $('#cusAddress').html('');
            if(selectedValue != "") {
                $.ajax({                   
                    url: './getCusAddress.htm',
                    type: "post",
                    async: false,
                    data: ({cusId:selectedValue}),
                    dataType:'html',
                    success: function(ajaxObj) {
                        $('#cusAddress').html(ajaxObj);
                    }
                            
                });
            }
        };
        function submitData(e){        
            $('.effDate').css("color", "#666");
            var DivHeight = $("html").height();
            
            if($('.check:checked').length == 0){
                   
                $("#selectAll").css({
                    "height":DivHeight,
                    "display":"block"
                });
                
                $('#selectAllYes').click(function(){
                    $("#selectAll").css({
                        "display":"none" 
                    });                    
                });
                
                return false;
            }else if($("input[name=locationType]:checked").val() == 'base location'){
                if($("#htlBaseLocationId").val() == ''){
                    
                    $("#baseLocation").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                
                    $('#baseLocationYes').click(function(){
                        $("#baseLocation").css({
                            "display":"none" 
                        });                    
                    });
                
                    return false;
                }
                if($("#datepicker").val() == ''){
                    
                    $("#effectiveDate").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                
                    $('#effectiveDateYes').click(function(){
                        $("#effectiveDate").css({
                            "display":"none" 
                        });                    
                    });
                    
                    return false;
                }
                if($('#calName').val() == ''){
                    $("#calendarMandate").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                
                    $('#calendarMandateYes').click(function(){
                        $("#calendarMandate").css({
                            "display":"none" 
                        });                    
                    });
                    
                    return false;
                }
                var i;
                var j;
                var x=0;
                var picker=$("#datepicker").val();
                var pickerDateValueArr=picker.split('-');
                var pickerTimeStamp=new Date(pickerDateValueArr[2],pickerDateValueArr[1],pickerDateValueArr[0]).getTime();
                var values = new Array();
                $.each($("input[class='check']:checked"), function() {
                    values.push($(this).attr('id'));
                });
                var dataArr = new Array();
                for(j=0;j<values.length;j++){
                    dataArr.push( $("#row"+values[j]).closest("tr").find('td:eq(5)').text());
                }
                for(i=0;i<dataArr.length;i++){
                    var effective = dataArr[i];
                    var effectiveDateValueArr = dataArr[i].split('-');
                    var effectiveTimeStamp = new Date(effectiveDateValueArr[2],effectiveDateValueArr[1],effectiveDateValueArr[0]).getTime();
                    if(pickerTimeStamp <= effectiveTimeStamp){
                        ( $("#row"+values[i]).closest("tr").find('td:eq(5)')).css( "color", "red" );
                        x++;
                    }
                }
                if(x!=0){
                    $("#effectiveDateLess").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                
                    $('#effectiveDateLessYes').click(function(){
                        $("#effectiveDateLess").css({
                            "display":"none" 
                        });                    
                    });
                    
                    return false;
                }
                
                if ($('#checkAll:checked').length == 1){    
                    var flag = false; 
                    $("#updateAllBase").css({
                        "height":DivHeight,
                        "display":"block"
                    }); 
                    $('#updateAllBaseYes').click(function(){                        
                        $("#updateAllBase").hide();
                        $("#locationChangeField").submit();
                    });
                    $('#updateAllBaseNo').click(function(){
                        $("#updateAllBase").hide();  
                    }); 
                    return flag;                    
                }                 
            }else if($("input[name=locationType]:checked").val() == 'customer location'){
                if($("input[name=add]:checked").val() == '' || $("input[name=add]:checked").val() == undefined){
                    
                    $("#customerLocation").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                
                    $('#customerLocationYes').click(function(){
                        $("#customerLocation").css({
                            "display":"none" 
                        });                    
                    });
                    
                    return false;
                }
                if($("#datepicker").val() == ''){
                    
                    $("#effectiveDate").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                
                    $('#effectiveDateYes').click(function(){
                        $("#effectiveDate").css({
                            "display":"none" 
                        });                    
                    });
                    
                    return false;
                }
                if($('#calName').val() == ''){
                    $("#calendarMandate").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                
                    $('#calendarMandateYes').click(function(){
                        $("#calendarMandate").css({
                            "display":"none" 
                        });                    
                    });
                    return false;
                }
                var i;
                var j;
                var x=0;
                var picker=$("#datepicker").val();
                var pickerDateValueArr=picker.split('-');
                var pickerTimeStamp=new Date(pickerDateValueArr[2],pickerDateValueArr[1],pickerDateValueArr[0]).getTime();
                var values = new Array();
                $.each($("input[class='check']:checked"), function() {
                    values.push($(this).attr('id'));
                });
                var dataArr = new Array();
                for(j=0;j<values.length;j++){
                    dataArr.push( $("#row"+values[j]).closest("tr").find('td:eq(5)').text());
                }
                for(i=0;i<dataArr.length;i++){
                    var effective = dataArr[i];
                    var effectiveDateValueArr = dataArr[i].split('-');
                    var effectiveTimeStamp = new Date(effectiveDateValueArr[2],effectiveDateValueArr[1],effectiveDateValueArr[0]).getTime();
                    if(pickerTimeStamp <= effectiveTimeStamp){
                        ( $("#row"+values[i]).closest("tr").find('td:eq(5)')).css( "color", "red" );
                        x++;
                    }
                }
                if(x!=0){
                    $("#effectiveDateLess").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                
                    $('#effectiveDateLessYes').click(function(){
                        $("#effectiveDateLess").css({
                            "display":"none" 
                        });                    
                    });
                    return false;
                }
            }else{
                return false;
            }
            if ($('#checkAll:checked').length == 1){
                var flag = false; 
                $("#updateAllCustomer").css({
                    "height":DivHeight,
                    "display":"block"
                }); 
                $('#updateAllCustomerYes').click(function(){                        
                    $("#updateAllCustomer").hide();
                    $("#locationChangeField").submit();
                });
                $('#updateAllCustomerNo').click(function(){
                    $("#updateAllCustomer").hide();  
                }); 
                return flag; 
            }
        }
        function getCalendarName(idValue){           
            var customerType = $('input:radio[name=locationType]:checked').val();                                     
            if(idValue != "") {
                $.ajax({                   
                    url: './getCalendarNameList.htm',
                    type: "post",
                    async: false,
                    data: ({customer_id:idValue,is_customer:customerType,location_id:idValue}),
                    dataType:'html',
                    success: function(ajaxObj) {
                        $("#calendarName").html('').html(ajaxObj);
                    }
                });
            };
        }
        function getCustomerCalendarName(locationId){        
            var idValue = $('#Customer').val();
            var customerType = $('input:radio[name=locationType]:checked').val();
            if(locationId != "") {
                $.ajax({                   
                    url: './getCalendarNameList.htm',
                    type: "post",
                    async: false,
                    data: ({customer_id:idValue,is_customer:customerType,location_id:locationId}),
                    dataType:'html',
                    success: function(ajaxObj) {
                        $("#calendarName").html('').html(ajaxObj);
                    }
                });
            };
        }
    </script>

    <!--[if IE]>
    <style>
        .location-table tbody {
             height: 45px;
        }
    </style>
    <![endif]-->
    <body>
        <div id="workAllocationMap">
            <div class="center-content">
                <p class="table-title">Employee - Work Location Mapping</p>
                <div class="data-filter">
                    <div class="filter-container">
                        <div class="filter-wrap">
                            <form id="searchData" method="post" action="">
                                <div class="filter_row">
                                    <div class="searchBy">
                                        <div class="search1">
                                            <label>Search by employee</label>
                                            <input type="text" id="employeeSearch" name="employeeSearch" class="employee-box" value="${filterData.employeeId != '' || filterData.employeeId != null?filterData.employeeSearch:'Employee (ID/Name)'}" onfocus="if (this.value == 'Employee (ID/Name)')
                                                this.value = '';" placeholder="Employee (ID/Name)" />
                                            <input type="hidden" id="employeeId" name="employeeId" value ="${filterData.employeeId}"/>
                                        </div>
                                        <div class="search1">
                                            <label>Search by Manager</label>
                                            <input type="text" id="managerSearch" name="managerSearch" class="employee-box" value="${filterData.managerId != '' || filterData.managerId != null?filterData.managerSearch:'Manager (ID/Name)'}" onfocus="if (this.value == 'Manager (ID/Name)')
                                                this.value = '';" placeholder="Manager (ID/Name)" />
                                            <input type="hidden" id="managerId" name="managerId" value ="${filterData.managerId}"/>
                                        </div>
                                    </div>

                                    <div class="filterBy">
                                        <div class="search2">
                                            <label>Filter by Base Location</label>
                                            <select name="baseLocationId" id="baseLocationId" class="dropdown"> 
                                                <option value="" selected>Base Location</option>
                                                <c:forEach items="${baseLocationList}" var="baseList" >
                                                    <option value="${baseList.baseLocationId}"${(filterData.baseLocationId == baseList.baseLocationId) ? 'selected':''}>${baseList.baseLocationName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="search3">
                                            <label>Filter by Work Location</label>
                                            <select name="workLocationId" id="workLocationId" class="dropdown"> 
                                                <option value="" selected>Work Location</option>
                                                <c:forEach items="${workLocationList}" var="workList" >
                                                    <option value="${workList.workLocationId}"${(filterData.workLocationId == workList.workLocationId) ? 'selected':''}>${workList.workLocationName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <div class="btnHolder">
                                            <button class="go" type="submit" onclick="">Go</button>
                                            <button class="reset" type="submit" onclick="">Reset</button>
                                        </div>                                        
                                    </div>
                                    <div class="filter_row" style="padding-top: 10px;position: relative;top: 10px;">
                                        <div class="searchBy">
                                            <p class="location-title">Change Work Location</p>
                                        </div>
                                        <div class="searchBy">
                                            <div class="button-container">  
                                                <button class="exportButton" id="export" type="Submit">Export</button>

                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>



                    <form method="post" id="locationChangeField" class="locationChangeField" name="locationChangeField" action="insertLocation.htm">
                        <div class="location-change" id="location-change">
                            <div class="filter-container">                    
                                <div class="filter_row">
                                    <div class="location">
                                        <ul class="location_type">
                                            <li><input type="radio" name="locationType" value="base location"  checked="checked"/><label>Base Location</label></li>
                                            <li><input type="radio" name="locationType" value="customer location" /><label>Customer Location</label></li>
                                        </ul>
                                        <div class="location-wrap">
                                            <select class="dis dropdown" id="Customer"  onchange="getCusAddress(this.value)" style="margin-top: 10px;"> 
                                                <option value="" selected>Customer Name</option>
                                                <c:forEach items="${cusList}" var="cusList" >
                                                    <option value="${cusList.cusId}"${(filterData.cusId == cusList.cusId) ? 'selected':''}>${cusList.cusName}</option>
                                                </c:forEach>
                                            </select>
                                            <div class="customer-Location" id="cusAddress">

                                            </div>
                                        </div>
                                        <div class="base-location">
                                            <select class="dropdown" name="htlBaseLocationId" id="htlBaseLocationId" onchange="getCalendarName(this.value)"  style="margin-top: 10px;">
                                                <option value="" selected>Location Name</option>                                                
                                                <c:forEach items="${HtlBaseLocationList}" var="baseList" >
                                                    <option value="${baseList.htlBaseLocationId}"${(filterData.htlBaseLocationId == baseList.htlBaseLocationId) ? 'selected':''}>${baseList.htlBaseLocationName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class ="effCust">
                                        <div class="eff">                        
                                            <p class="effectiveDate">Effective from</p><span><b>:</b></span><input class="effectiveCalendar" type="text" id="datepicker" name="effectiveFrom" placeholder="DD-MM-YYYY" style="z-index: 2; margin-left: 4px;" readonly="true">                                               
                                        </div>
                                        <div class="calName" style="margin-top: 10px;">
                                            <p class="effectiveDate">Calendar Name</p><span><b>:</b></span>
                                            <!--<select class="dropdown" id="calendarName" name="calendarName" style="margin-top: 10px;">--> 
                                            <lable id="calendarName" > </lable>
                                            <!--</select>-->
                                        </div>
                                    </div>
                                    <div class="location-message">
                                        <span id="loc-mesg"></span>
                                    </div>
                                </div>
                            </div>
                        </div>
                </div>
            </div>  
            <div id="msg"><div class="sucess-message">${succcessMsg}</div></div>
            <div class="center-content">
                <c:if test="${fn:length(empList)!=0}">
                    <table cellpadding="0" cellspacing="0" class="location-table">

                        <thead>
                            <tr>
                                <th><input type="checkbox" id="checkAll" /></th>                                
                                <th>Employee</th>
                                <th>Reporting Manager</th>
                                <th>Base Location</th>
                                <th>Work Location</th>
                                <th>Effective From</th>
                                <th>Available Hours</th>
                                <th>Calendar Name</th>
                            </tr>
                        </thead>

                        <tbody>

                            <c:forEach items="${empList}" var="empList" varStatus="i">
                                <c:choose>
                                    <c:when test="${(i.count) % 2 == 0}">
                                        <tr class="even" id="row${(i.count)}">
                                        </c:when>
                                        <c:otherwise>
                                        <tr class="odd" id="row${(i.count)}">
                                        </c:otherwise>
                                    </c:choose> 
                                    <td>
                                        <c:choose>
                                            <c:when test="${empList.isAllocated == '0'}">
                                                <input type="checkbox" name="empLocChangeList" value="${empList.employeeId}" class="check" id="${(i.count)}"/>
                                            </c:when>                                             
                                        </c:choose>

                                    </td>
                                    <td>
                                        ${empList.empName}
                                    </td>
                                    <td>
                                        ${empList.reportingManager}
                                    </td>
                                    <td>
                                        ${empList.baseLocation}
                                    </td>
                                    <td>
                                        ${empList.workLocation}
                                    </td>
                                    <td class="effDate">
                                        ${empList.effectiveFrom}
                                        <input type="hidden" value="${empList.effectiveFrom}" class="fromDate"/>
                                    </td>
                                    <td>
                                        ${empList.available_hours}
                                    </td>
                                    <td style="width: 139px;">
                                        ${empList.calendarName}
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </c:if>
                <c:if test="${fn:length(empList)==0}">

                    <div id="datadisplay" style="width: 932px;">
                        <table>
                            <tbody><tr class="row-odd">
                                    <td colspan="6" style="text-align: center;">
                                        No data to display
                                    </td>
                                </tr>
                            </tbody></table>

                    </div>
                </c:if>
                <c:if test="${fn:length(empList)!=0}">
                    <div class="submit-button">
                        <button type="submit" id="submitDetails" class="submitDetails" onclick="return submitData(this)">Submit</button>
                    </div>
                </c:if>
            </div>
        </form>
    </div>
    <script>
        $(document).ready(function(){
            /*$('.check').change(function(){
                            $(".dis").prop("disabled", $(this).is(':checked'));
                    });
            $("#changeButton").click(function () {
                            if ($("#location-change").hasClass("fadeout"))
                                    $("#location-change").removeClass("fadeout").addClass("fadein");
                            else
                                    $("#location-change").removeClass("fadein").addClass("fadeout");
                    });
            $("#changeButton").click(function () {
                if ($("#location-change").hasClass("slideup"))
                    $("#location-change").removeClass("slideup").addClass("slidedown");
                else
                    $("#location-change").removeClass("slidedown").addClass("slideup");
            });*/
            $("#checkAll").change(function () {
                $(".location-table input:checkbox").attr('checked', $(this).attr("checked"));
            });
            if($("input[type='radio']:checked").attr("value")=="base location")
                $(".location-wrap").hide();
            $('input[type="radio"]').click(function(){
                if($(this).attr("value")=="customer location")
                {
                    $(".location-wrap").show();
                    $("#loc-mesg").show();
                    $(".base-location").hide();
                }
                else
                {		
                    $(".location-wrap").hide();
                    $("#loc-mesg").hide();
                    $(".base-location").show();
                }
            });
            //$('.customer-Location:empty').hide();
            var str = $('.customer-Location').text();
            if($.trim(str) === "") {
                $('.customer-Location').hide();
            }
            $("#Customer").change(function(){
                var a=$('.customer-Location ul li').length;
                //alert(a);
                if(a===0){
                    $('.customer-Location').hide();
                    $("#loc-mesg").text("Customer work location not available");
                }
                else{
                    $('.customer-Location').show();
                }
            });
            //Show Customer Address in a Seperate Section
            $('input[type=radio][name=add]').live('click', function(e){
                var radioValue = e.currentTarget.nextSibling.data;
                $('.customer-Location').hide();
                $("#loc-mesg").text(radioValue);
            });
        });
    </script>
    <div id="selectAll" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="selectAllFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
            <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
            <div style="padding: 5px;">
                <p style="padding:10px;margin:10px;">Please select check box to continue.</p><p class="plainButton" id="selectAllYes" style="margin: 0px 10px 10px 10px;">OK</p>
            </div>
        </div>
    </div>
    <div id="baseLocation" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="baseLocationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
            <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
            <div style="padding: 5px;">
                <p style="padding:10px;margin:10px;">Please select base location.</p><p class="plainButton" id="baseLocationYes" style="margin: 0px 10px 10px 10px;">OK</p>
            </div>
        </div>
    </div>
    <div id="customerLocation" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="customerLocationFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
            <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
            <div style="padding: 5px;">
                <p style="padding:10px;margin:10px;">Please select customer location.</p><p class="plainButton" id="customerLocationYes" style="margin: 0px 10px 10px 10px;">OK</p>
            </div>
        </div>
    </div>
    <div id="effectiveDate" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="effectiveDateFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
            <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
            <div style="padding: 5px;">
                <p style="padding:10px;margin:10px;">Please select effective date.</p><p class="plainButton" id="effectiveDateYes" style="margin: 0px 10px 10px 10px;">OK</p>
            </div>
        </div>
    </div>
    <div id="calendarMandate" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="calendarMandateFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
            <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
            <div style="padding: 5px;">
                <p style="padding:10px;margin:10px;">There is no Calendar for the location.<br> Please create a new Calendar for the location and allocate the employees.</p><p class="plainButton" id="calendarMandateYes" style="margin: 0px 10px 10px 10px;">OK</p>
            </div>
        </div>
    </div>
    <div id="effectiveDateLess" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="effectiveDateLessFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
            <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
            <div style="padding: 5px;">
                <p style="padding:10px;margin:10px;">The Effective date should be greater than pervious allocation.</p><p class="plainButton" id="effectiveDateLessYes" style="margin: 0px 10px 10px 10px;">OK</p>
            </div>
        </div>
    </div>
    <div id="updateAllBase" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="updateAllBaseFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
            <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
            <div style="padding: 5px;">
                <p style="padding:10px;margin:10px;">Are you sure you want to update all Employees location?</p><p class="plainButton" id="updateAllBaseYes" style="margin: 0px 10px 10px 10px;">OK</p>
                <p class="plainButton" id="updateAllBaseNo" style="margin: 0px 10px 10px 10px;">Cancel</p>
            </div>
        </div>
    </div>
    <div id="updateAllCustomer" style="position:absolute;width:100%;height:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="updateAllCustomerFocus" style="position: absolute;z-index: 150;top: 20%;left:38%;text-align: center;font-size: 14px;background-color: #fff;width: 340px;height:auto;border-radius: 5px;">
            <p style="border-radius: 5px 5px 0px 0px;background: url(../images/box-strip.jpg) repeat-x scroll top center #316CA8;padding: 5px;font-weight: bold;color:#fff;">Alert!</p>
            <div style="padding: 5px;">
                <p style="padding:10px;margin:10px;">Are you sure you want to update all Employees location?</p><p class="plainButton" id="updateAllCustomerYes" style="margin: 0px 10px 10px 10px;">OK</p>
                <p class="plainButton" id="updateAllCustomerNo" style="margin: 0px 10px 10px 10px;">Cancel</p>
            </div>
        </div>
    </div>
</body>
</html>
