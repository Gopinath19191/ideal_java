<%-- 
    Document   : approvelView
    Created on : Jul 5, 2018, 5:05:08 PM
    Author     : 16113
--%>

<%@page import="com.defiance.ideal.travelplan.dto.TravelSettlementDto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- css from theme -->
        <link href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/Ionicons/css/ionicons.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/datatables/css/jquery.dataTables.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/select2/css/select2.min.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/lib/SpinKit/css/spinkit.css" rel="stylesheet">

        <!-- Slim CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slim.css">
        <!-- Script from theme -->
        <script src="${pageContext.request.contextPath}/lib/jquery/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/lib/popper.js/js/popper.js"></script>
        <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/lib/jquery.cookie/js/jquery.cookie.js"></script>
        <script src="${pageContext.request.contextPath}/lib/datatables/js/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/lib/datatables-responsive/js/dataTables.responsive.js"></script>
        <script src="${pageContext.request.contextPath}/lib/select2/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slim.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.17.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css" rel="stylesheet" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" rel="stylesheet" />
        <!--        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
                <script src="jquery.datetimepicker.js"></script>-->

        <style>            
            body { background-color: #f0f2f7; color: #101010; }
            footer{ font-size:small;position:fixed;right:5px;bottom:5px; }
            a:link, a:visited  { color: #0000ee; }
            pre{ background-color: #eeeeee; margin-left: 1%; margin-right: 2%; padding: 2% 2% 2% 5%; }
            p { font-size: 0.9rem; }
            ul { font-size: 0.9rem; }
            hr { border: 2px solid #eeeeee; margin: 2% 0% 2% -3%; }
            h3 { border-bottom: 2px solid #eeeeee; margin: 2rem 0 2rem -1%; padding-left: 1%; padding-bottom: 0.1em; }
            h4 { border-bottom: 1px solid #eeeeee; margin-top: 2rem; margin-left: -1%; padding-left: 1%; padding-bottom: 0.1em; }

            .table th, .table td{
                padding:0.15rem;
            }
            .accordion-one .card-header a::before {
                top: 8px;
            }
            .col-sm tr td{
                width:16.66%;
                padding: 3px;
            }
            .wd-125-force{
                width: 150px !important;
            }
            .bg-info{
                text-align: center;
            }
            #datatable1 td{
                padding:0.15rem !important;
            }
            #datatable1{
                border-collapse: inherit;
            }            
            .amount_alignment {
                text-align: right;
            }
            #datatable2{
                width: 50% !important;
            }
            .section-tables{
                padding: 10px 30px 10px 30px !important;                
                background-color: #fff;
                font-family: "Roboto", "Helvetica Neue", Arial, sans-serif;
            }
            .readonlyclass{
                padding: 3px !important;
                height: 28px !important;
                width: 160px !important;
            }
            .error_message{
                color: red;
                font-size: small;
                display: none;
            }
            #datatablept tr,#datatablept tr td{
                border :1px #5b636a;               
            }
            .datacolour{
                color: #5b636a;   
            }
            .backbutton{
                background: url(./css/images/back.png) no-repeat scroll 8px 8px #3579c6;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 5px 0 0;
                border-radius: 10px;
                padding: 0 10px 0 30px;
                cursor: pointer;
                width: 80px;
            }
            .section-title{
                padding-left: 15px;
                margin-top: 20px;
                color: #1b84e7 !important;
            }
            .printbutton{
                background: url(./css/images/print_image1.png) no-repeat scroll 8px 8px #3579c6;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 5px 0 0;
                border-radius: 10px;
                padding: 0 10px 0 30px;
                cursor: pointer;
                width: 80px;                
            }
        </style>
        <script>
            $(document).ready(function() {
                $(".tic_billDate").datepicker({dateFormat : 'yy-mm-dd',changeMonth : true,changeYear : true});
                $("#approveAction").click(function (e) {
                    $("#status").val('13');
                    validateRMForm();
                });
                $("#rejectAction").click(function (e) {
                    $("#status").val('14');
                    validateRMForm();
                });
                function validateRMForm(){
                    var error =0;
                    var comments = $("#rm_remarks").val();
                    if(comments=="" || comments==null){
                        $("#rm_remarks").css({"outline": "1px solid red"});
                        $("#rm_remarks_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#rm_remarks").css({"outline": "none"});
                        $("#rm_remarks_error").css({"display": "none"});
                    }
                    if(error==0){
                        $("#saveDetails").submit();                        
                        return true;
                    }else{
                        return false;
                    }
                }
                $("#buhApproveAction").click(function (e) {
                    $("#status").val('15');
                    validateBUHForm();
                });
                $("#buhRejectAction").click(function (e) {
                    $("#status").val('16');
                    validateBUHForm();
                });
                
                function validateBUHForm(){
                    var error =0;
                    var comments = $("#buh_remarks").val();
                    if(comments=="" || comments==null){
                        $("#buh_remarks").css({"outline": "1px solid red"});
                        $("#buh_remarks_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#buh_remarks").css({"outline": "none"});
                        $("#buh_remarks_error").css({"display": "none"});
                    }
                    if(error==0){
                        $("#saveDetails").submit();                       
                        return true;
                    }else{
                        return false;
                    }
                }
                $("#cfoApproveAction").click(function (e) {
                    $("#status").val('19');
                    validateFinForm();
                });
                $("#cfoRejectAction").click(function (e) {
                    $("#status").val('20');
                    validateFinForm();
                });
                
                function validateFinForm(){
                    var error =0;
                    var comments = $("#cfo_remarks").val();
                    if(comments=="" || comments==null){
                        $("#cfo_remarks").css({"outline": "1px solid red"});
                        $("#cfo_remarks_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#cfo_remarks").css({"outline": "none"});
                        $("#cfo_remarks_error").css({"display": "none"});
                    }
                    if(error==0){
                        $("#saveDetails").submit();                      
                        return true;
                    }else{
                        return false;
                    }
                }
                
                $("#finApproveAction").click(function (e) {
                    $("#status").val('19');                    
                    validateAmount
                    validateFinForm();
                });
                $("#finRejectAction").click(function (e) {
                    $("#status").val('20');
                    validateFinForm();
                });
                function validateAmount(){
                    var error =0;
                    var pay = $("#ispayroll").val();
                    if(pay == 'Y'){
                        var tot = $("#tobeRecovered").val();  
                        if(tot==null || tot==""){
                            $("#tobeRecovered").css({"outline": "1px solid red"});                        
                            error++;
                        }else{
                            $("#tobeRecovered").css({"outline": "none"});
                        }
                    }else if(pay =='N'){
                        var tot = $("#tobeDeposited").val();  
                        if(tot==null || tot==""){
                            $("#tobeDeposited").css({"outline": "1px solid red"});                        
                            error++;
                        }else{
                            $("#tobeDeposited").css({"outline": "none"});
                        }
                    }
                    if(error==0){
                        $("#saveDetails").submit();                       
                        return true;
                    }else{
                        return false;
                    }
                }
                function validateFinForm(){
                    var error =0;
                    var comments = $("#finance_remarks").val();
                    if(comments=="" || comments==null){
                        $("#finance_remarks").css({"outline": "1px solid red"});
                        $("#finance_remarks_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#finance_remarks").css({"outline": "none"});
                        $("#finance_remarks_error").css({"display": "none"});
                    }
                    if(error==0){
                        $("#saveDetails").submit();                       
                        return true;
                    }else{
                        return false;
                    }
                }
                
                $("#TreasuryAction").click(function (e) {
                    $("#status").val('21');
                    validateTreasuryForm();                });
                
                $("#PayrollAction").click(function (e) {
                    $("#status").val('22');
                    validatePayrollForm();
                });
                function validatePayrollForm(){
                    var error =0;
                    var comments = $("#payroll_comments").val();
                    var amount = $('#recoveredAmount').val();
                    var dat = $('#recoveredDate').val();
                    if(amount=="" || amount==null){
                        $("#recoveredAmount").css({"outline": "1px solid red"});
                        $("#payroll_amount_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#recoveredAmount").css({"outline": "none"});
                        $("#payroll_amount_error").css({"display": "none"});
                    }
                    if(dat=="" || dat==null){
                        $("#recoveredDate").css({"outline": "1px solid red"});
                        $("#payroll_date_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#recoveredDate").css({"outline": "none"});
                        $("#payroll_date_error").css({"display": "none"});
                    }
                    if(comments=="" || comments==null){
                        $("#payroll_comments").css({"outline": "1px solid red"});
                        $("#payroll_remarks_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#payroll_comments").css({"outline": "none"});
                        $("#payroll_remarks_error").css({"display": "none"});
                    }
                    
                    if(error==0){
                        $("#saveDetails").submit();                         
                        return true;
                    }else{
                        return false;
                    }
                }
                
                function validateTreasuryForm(){
                    var error =0;
                    var comments = $("#treasury_comments").val();
                    var amount = $('#depositedAmount').val();
                    var dat = $('#depositedDate').val();
                    if(amount=="" || amount==null){
                        $("#depositedAmount").css({"outline": "1px solid red"});
                        $("#treasury_amount_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#depositedAmount").css({"outline": "none"});
                        $("#treasury_amount_error").css({"display": "none"});
                    }
                    if(dat=="" || dat==null){
                        $("#depositedDate").css({"outline": "1px solid red"});
                        $("#treasury_date_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#depositedDate").css({"outline": "none"});
                        $("#treasury_date_error").css({"display": "none"});
                    }
                    if(comments=="" || comments==null){
                        $("#treasury_comments").css({"outline": "1px solid red"});
                        $("#treasury_remarks_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#treasury_comments").css({"outline": "none"});
                        $("#treasury_remarks_error").css({"display": "none"});
                    }
                    if(error==0){
                        $("#saveDetails").submit(); 
                        $('.preloader').css("display","block");
                        return true;
                    }else{
                        return false;
                    }
                }
                
                $("#adminApproveAction").click(function (e) {
                    $("#status").val('8');
                    validateAdminForm();
                });
                
                function validateAdminForm(){
                    var error =0;
                    var comments = $("#admin_remarks").val();
                    if(comments=="" || comments==null){
                        $("#admin_remarks").css({"outline": "1px solid red"});
                        $("#admin_remarks_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#admin_remarks").css({"outline": "none"});
                        $("#admin_remarks_error").css({"display": "none"});
                    }
                    if(error==0){
                        $("#saveDetails").submit();
                        $('.preloader').css("display","block");
                        return true;
                    }else{
                        return false;
                    }
                }
            });
            function printPage(){
                window.print();
            }
        </script>
    </head>
    <body>               
        <div class="slim-mainpanel">
            <div class="container">
                <div class="slim-pageheader">
                    <ol class="breadcrumb slim-breadcrumb">
                        <c:if test="${type == 'processed'}">
                            <li class="breadcrumb-item"><a style="color: #1b84e7;" href="${pageContext.request.contextPath}/settlementPendingApprovels.htm?type=processed&approver_type=${approver_type}">List Processed</a></li>
                        </c:if>
                        <c:if test="${type == 'pending'}">
                            <li class="breadcrumb-item"><a style="color: #1b84e7;" href="${pageContext.request.contextPath}/settlementPendingApprovels.htm?type=pending&approver_type=${approver_type}">List Pending</a></li>
                        </c:if>
                    </ol>
                    <h6 class="slim-pagetitle">Travel View</h6>
                </div>
                <form name="saveDetails"  id="saveDetails" action="approveSettlement.htm" method="POST" enctype="text/file">
                    <input type="hidden" id="masterId" name="masterId" value="${travelDetails.masterId}" />
                    <input type="hidden" id="travelType" name="travelType" value="${travelDetails.travelType}" />
                    <input type="hidden" id="travelTerm" name="travelTerm" value="${travelDetails.travelTerm}" />
                    <input type="hidden" id="bandId" name="bandId" value="${travelDetails.bandId}" />
                    <input type="hidden" id="deviation" name="deviation" value="${travelDetails.deviation}" />
                    <input type="hidden" id="practiceGroupId" name="practiceGroupId" value="${travelDetails.practiceGroupId}" />
                    <div class="section-tables">
                        <h6 class="slim-pagetitle mg-b-20" style="padding:0px;border:0px;text-align: center;">Travel ID - ${travelDetails.ticketRefId}</h6>
                        <div id="accordion" class="accordion-one mg-r-45 mg-l-45" role="tablist" aria-multiselectable="true">
                            <div class="card">
                                <div class="card-header pd-0-force" role="tab" id="headingOne">
                                    <a class="collapsed tx-gray-800 transition pd-5-force" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                        <!--<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne" class="tx-gray-800 collapsed transition pd-5-force">-->
                                        Employee Details
                                    </a>
                                </div><!-- card-header -->

                                <div id="collapseOne" class="collapse show" role="tabpanel" aria-labelledby="headingOne">
                                    <div class="card-body">
                                        <table class="col-sm">
                                            <input type="hidden" value="${travelDetails.status}" id="status" name="status"/>
                                            <tbody>
                                                <tr>
                                                    <td><strong class="tx-inverse tx-medium">Employee Id</strong></td>
                                                    <td><span class="text-muted">${travelDetails.employeeNumber}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Employee Name</strong></td>
                                                    <td><span class="text-muted">${travelDetails.employeeName}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Band</strong></td>
                                                    <td><span class="text-muted">${travelDetails.bandName}</span></td>
                                                </tr>
                                                <tr>
                                                    <td><strong class="tx-inverse tx-medium">Practice Group</strong></td>
                                                    <td><span class="text-muted">${travelDetails.practiceGroupName}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Sub Practice Group</strong></td>
                                                    <td><span class="text-muted">${travelDetails.subPracticeGroupName}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Designation</strong></td>
                                                    <td><span class="text-muted">${travelDetails.designationName}</span></td>
                                                </tr>
                                                <tr>
                                                    <td><strong class="tx-inverse tx-medium">Location</strong></td>
                                                    <td><span class="text-muted">${travelDetails.city}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Contact No</strong></td>
                                                    <td><span class="text-muted">${travelDetails.contactNo}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Requested Date</strong></td>
                                                    <td><span class="text-muted">${travelDetails.requestedDate}</span></td>                                        
                                                </tr>
                                            </tbody>
                                        </table>                    
                                    </div>
                                </div>
                            </div>
                            <div class="card">
                                <div class="card-header" role="tab" id="headingTwo">
                                    <a class="collapsed tx-gray-800 transition pd-5-force" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                        Travel Details
                                    </a>
                                </div>
                                <div id="collapseTwo" class="collapse show" role="tabpanel" aria-labelledby="headingTwo">
                                    <div class="card-body">
                                        <table class="col-sm">
                                            <tbody>
                                                <tr>
                                                    <td class="wd-15p text-dark font-weight-medium"> From City </td>
                                                    <td class="wd-15p text-muted">${travelDetails.fromCity}</td>
                                                    <td class="wd-15p text-dark font-weight-medium">To City</td>
                                                    <td class="wd-15p text-muted">${travelDetails.toCity}</td>
                                                    <td><strong class="tx-inverse tx-medium">Travel Term</strong></td>
                                                    <td><span class="text-muted">${travelDetails.travelTerm}</span></td>
                                                </tr>
                                                <tr>
                                                    <td><strong class="tx-inverse tx-medium">Project Travel</strong></td>
                                                    <td><span class="text-muted">${travelDetails.projectTravel}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Customer</strong></td>
                                                    <td><span class="text-muted">${travelDetails.customerName}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Project</strong></td>
                                                    <td><span class="text-muted">${travelDetails.projectName}</span></td>
                                                </tr>
                                                <tr>
                                                    <td><strong class="tx-inverse tx-medium">Client Reimbursable</strong></td>
                                                    <td><span class="text-muted">${travelDetails.clientReimbursable}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Settlement Policy</strong></td>
                                                    <td><span class="text-muted">${travelDetails.settlemet_policy}</span><input type="hidden" name="settlementType" id="settlementType" value="${travelDetails.settlementType}"></td>
                                                    <td><strong class="tx-inverse tx-medium">Submitted Date</strong></td>
                                                    <td><span class="text-muted">${travelDetails.submittedDate}</span></td>
                                                </tr>
                                                <tr>
                                                    <td><strong class="tx-inverse tx-medium">Travel Commenced </strong></td>
                                                    <td> <span class="text-muted"> ${travelDetails.travelCommenced}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Travel Completed </strong></td>
                                                    <td><span class="text-muted">${travelDetails.travelCompleted}</span></td>
                                                    <td><strong class="tx-inverse tx-medium">Travel Period </strong></td>
                                                    <td><span class="text-muted">${travelDetails.travelPeriod}</span></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>            
                        </div>
                        <c:if test="${fn:length(advanceDetails)!=0 && advanceDetails.get(0).depositedAmount != null && advanceDetails.get(0).depositedAmount != '0'}">
                            <div class="section-tables">
                                <label class="section-title">Advance Details</label>
                                <div class="table-responsive col-md-6">
                                    <table id="datatable" class="table table-bordered display responsive nowrap">
                                        <thead class="bg-info">
                                            <tr>
                                                <th class="wd-15p">Deposited Date</th>
                                                <th class="wd-15p">Currency</th>
                                                <th class="wd-15p">Deposited Amount</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${advanceDetails}" var="advdetails" varStatus="index">
                                                <tr>
                                                    <td class="wd-15p">${advdetails.depositedDate}</td>
                                                    <td class="wd-15p">${advdetails.currencyCode}</td>
                                                    <td class="wd-15p amount_alignment">${advdetails.depositedAmount}</td>
                                                </tr>
                                            </c:forEach>
                                            <tr style="background-color: #EFF4FB;">
                                                <td class="wd-15p">Total</td>
                                                <td class="wd-15p">${travelDetails.currencyName}</td>
                                                <td class="wd-15p amount_alignment">${totalAdvance}</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>                   
                        <div class="section-tables">
                            <label class="section-title">Expense Summary</label>
                            <div class="table-responsive col-md-12">
                                <table id="datatable" class="table table-bordered display responsive nowrap ">
                                    <input type="hidden" name="comtictotal" id="comtictotal" value="${comTicTotal}">
                                    <input type="hidden" name="comlodtotal" id="comlodtotal" value="${comLodTotal}">
                                    <input type="hidden" name="comcontotal" id="comcontotal" value="${comConvTotal}">
                                    <input type="hidden" name="comboartotal" id="comboartotal" value="0">
                                    <input type="hidden" name="commistotal" id="commistotal" value="0">
                                    <input type="hidden" name="comspenttotal" id="comspenttotal" value="${companyTotal}">
                                    <thead class="bg-info">
                                        <tr>
                                            <th class="wd-10p">Category</th>
                                            <th class="wd-10p">Eligibility</th>
                                            <th class="wd-20p">Spent By Employee</th>
                                            <th class="wd-20p">Spent By Company</th>
                                            <th class="wd-10p">Total Expense(${travelDetails.currencyName})</th>
                                            <th class="wd-10p">Deviation</th>
                                            <th class="wd-10p">Finance Approved (${travelDetails.currencyName})</th>
                                        </tr>
                                    </thead>                                
                                    <tbody>
                                        <tr>
                                            <td class="wd-10p">Tickets</td>
                                            <td class="wd-10p amount_alignment"><span id="tic_eli_tot">${expenseList.ticEliTot}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="tic_act">${expenseList.actual_ticket_amount}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="tic_act">${expenseList.actual_ticket_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="tic_tax_total">${expenseList.actual_ticket_tax_total}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.ticketTotal}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="">${expenseList.company_ticket_tax}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_ticket_tax_total}</span></td>-->
                                            <td class="wd-10p amount_alignment"><span id="tic_tot_exp">${expenseList.total_ticket_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="tic_deviation">${expenseList.deviation_ticket_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="tic_deviation">${expenseList.tic_approved_amount}</span></td>
                                        </tr>
                                        <tr>
                                            <td class="wd-10p">Lodging</td>
                                            <td class="wd-10p amount_alignment"><span id="lod_eli_tot"> ${expenseList.lodEliTot}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="lod_act">${expenseList.actual_lodging_amount}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="tic_act">${expenseList.actual_lodging_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="tic_tax_total">${expenseList.actual_lodging_tax_total}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="">${expenseList.lodgingTotal}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_lodging_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_lodging_tax_total}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="lod_tot_exp">${expenseList.total_lodging_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="lod_deviation">${expenseList.deviation_lodging_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="lod_deviation">${expenseList.lod_approved_amount}</span></td>
                                        </tr>
                                        <tr>
                                            <td class="wd-10p">Boarding</td>
                                            <td class="wd-10p amount_alignment"><span id="boar_eli_tot">${expenseList.boarEliTot}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="boar_act">${expenseList.actual_boarding_amount}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="tic_act">${expenseList.actual_boarding_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="tic_tax_total">${expenseList.actual_boarding_tax_total}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id=""></span>${expenseList.boardingTotal}</td>
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_boarding_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_boarding_tax_total}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="bor_tot_exp">${expenseList.total_boarding_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="bor_deviation">${expenseList.deviation_boarding_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="bor_deviation">${expenseList.boar_approved_amount}</span></td>
                                        </tr>
                                        <tr>
                                            <td class="wd-10p">Conveyance</td>
                                            <td class="wd-10p amount_alignment"><span id="con_eli_tot">${expenseList.conEliTot}</span> </td>
            <!--                                <td class="wd-5p amount_alignment"><span id="conv_act">${expenseList.actual_conveyance_amount}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="tic_act">${expenseList.actual_conveyance_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="tic_tax_total">${expenseList.actual_conveyance_tax_total}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="">${expenseList.conveyanceTotal}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_conveyance_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_conveyance_tax_total}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="con_tot_exp">${expenseList.total_conveyance_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="con_deviation">${expenseList.deviation_conveyance_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="con_deviation">${expenseList.con_approved_amount}</span></td>

                                        </tr>
                                        <tr>
                                            <td class="wd-10p">Miscellaneous</td>
                                            <td class="wd-10p amount_alignment"><span id="mis_eli_tot">${expenseList.misEliTot}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="mis_act">${expenseList.actual_miscellaneous_amount}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="tic_act">${expenseList.actual_miscellaneous_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="tic_tax_total">${expenseList.actual_miscellaneous_tax_total}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="">${expenseList.miscTotal}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_miscellaneous_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_miscellaneous_tax_total}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="mis_tot_exp">${expenseList.total_miscellaneous_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="mis_deviation">${expenseList.deviation_miscellaneous_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="mis_deviation">${expenseList.mis_approved_amount}</span></td>
                                        </tr>
                                        <tr style="background-color: #EFF4FB;">
                                            <td class="wd-10p">Total</td>
                                            <td class="wd-10p amount_alignment"><span id="tot_eli_tot" class="">${expenseList.totEliTot}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="actual_total" class="">${expenseList.actual_total_amount}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="tic_act">${expenseList.actual_total_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="tic_tax_total">${expenseList.actual_total_tax_total}</span></td>
            <!--                                <td class="wd-5p amount_alignment"><span id="" class=" ">${expenseList.totalExpance}</span></td>
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_total_tax}</span></td>-->
                                            <td class="wd-5p amount_alignment"><span id="">${expenseList.company_total_tax_total}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="tot_tot_exp" class="">${expenseList.overall_total_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="tot_deviation">${expenseList.deviation_total_amount}</span></td>
                                            <td class="wd-10p amount_alignment"><span id="tot_deviation">${expenseList.tot_approved_amount}</span></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <c:if test="${fn:length(compExpDetails)!=0}">
                            <div class="section-tables">
                                <label class="section-title">Expense Incurred by Company</label>
                                <div class="table-responsive col-md-12">
                                    <table id="datatable" class="table table-bordered display responsive nowrap ">
                                        <thead class="bg-info">
                                            <tr>
                                                <th class="wd-10p">Category</th>
                                                <th class="wd-20p">Vendor Name</th>
                                                <th class="wd-10p">Invoice/ Credit Note</th>
                                                <th class="wd-10p">Bill No</th>
                                                <th class="wd-10p">Bill Date</th>
                                                <th class="wd-10p">Amount(${travelDetails.currencyName})</th>
                                                <th class="wd-30p">Attachment</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${fn:length(compExpDetails)!=0}">
                                                <c:forEach items="${compExpDetails}" var="conhis" varStatus="index">
                                                    <c:if test="${conhis.settlementBy == 'a'}">
                                                        <tr id="TR_${index.count}">
                                                            <td class="wd-10p">${conhis.category}</td>
                                                            <td class="wd-20p">${conhis.vendor_name}</td>
                                                            <td class="wd-8p">${conhis.invoiceStat}</td>
                                                            <td class="wd-8p">${conhis.billNo}</td>
                                                            <td class="wd-8p">${conhis.billDate}</td>
                                                            <td class="wd-7p amount_alignment">${conhis.ticTotal}</td>
                                                            <td class="wd-30p">
                                                                <a href="${pageContext.request.contextPath}/downloadFile.htm?file_id=${conhis.fileId}&file_name=${conhis.file_name}" style="color: #1b84e7;" >${conhis.file_name}</a>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${fn:length(compExpDetails)!=0}">
                            <div class="section-tables">
                                <label class="section-title">Expense of Employee</label>
                                <div class="table-responsive col-md-12">
                                    <table id="datatable" class="table table-bordered display responsive nowrap ">
                                        <thead class="bg-info">
                                            <tr>
                                                <th>#</th>
                                                <th class="wd-8p">Category</th>
                                                <th class="wd-8p">City</th>
                                                <th class="wd-8p">From Date </th>
                                                <th class="wd-8p">To Date </th>
                                                <th class="wd-10p">With Bill/ Without Bill</th>
                                                <th class="wd-8p">Bill No</th>
                                                <th class="wd-8p">Bill Date</th>
                                                <th class="wd-8p">Eligibility(${travelDetails.currencyName}) </th>
                                                <th class="wd-20p">Amount(${travelDetails.currencyName})</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:if test="${fn:length(compExpDetails)!=0}">
                                                <c:forEach items="${compExpDetails}" var="conhis" varStatus="index">
                                                    <c:if test="${conhis.settlementBy == 'e'}">
                                                        <tr id="TR_${index.count}">
                                                            <td>${index.count}</td>
                                                            <td class="wd-8p">${conhis.category}</td>
                                                            <td class="wd-8p">${conhis.cityName} </td> 
                                                            <td class="wd-8p">${conhis.fromDate}</td>
                                                            <td class="wd-8p">${conhis.toDate}</td>                                                        
                                                            <td class="wd-10p">
                                                                <c:if test="${conhis.bill == 'y'}">With Bill</c:if>
                                                                <c:if test="${conhis.bill == 'n'}">With out Bill</c:if>
                                                                </td>
                                                                <td class="wd-8p">${conhis.billNo}</td>
                                                            <td class="wd-8p">${conhis.billDate}</td>
                                                            <td class="wd-10p amount_alignment">${conhis.eligibiityAmount}</td>
                                    <!--                        <td class="wd-5p amount_alignment">${conhis.amount}</td>
                                                            <td class="wd-5p amount_alignment">${conhis.ticTax}</td>-->
                                                            <td class="wd-5p amount_alignment">${conhis.ticTotal}</td>
                                                        </tr>
                                                    </c:if>
                                                </c:forEach>
                                            </c:if>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>
                        <c:if test="${fn:length(attachList)!=0}">
                            <div class="section-tables">
                                <label class="section-title">Attachments</label>
                                <div class="table-responsive col-md-6">
                                    <table id="datatable" class="fontalter table table-bordered display responsive nowrap">
                                        <input type="hidden" name="attachmentCount" value="1" id="attachmentCount">
                                        <thead class="bg-info">
                                            <tr>
                                                <th class="wd-10p">Category </th>
                                                <th class="wd-15p">Attachment </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${attachList}" var="attachlist" varStatus="indx">
                                                <tr id="TA_1">
                                                    <td class="wd-10p">
                                                        ${categorymap[attachlist.categoryType]}
                                                    </td>                                            
                                                    <td class="wd-15p">
                                                        <a href="${pageContext.request.contextPath}/downloadFile.htm?file_id=${attachlist.fileId}&file_name=${attachlist.file_name}" style="color: #1b84e7;" >${attachlist.file_name}</a>
                                                    </td>                                          

                                                </tr>
                                            </c:forEach>                                  
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </c:if>
                        <div class="section-tables">
                            <label class="section-title">Approver Details</label>
                            <div class="table-responsive col-md-9">
                                <table id="datatable" class="table table-bordered display responsive nowrap ">
                                    <thead class="bg-info">
                                        <tr>
                                            <th class="wd-15p"></th>
                                            <th class="wd-15p">Approver Name</th>
                                            <th class="wd-15p">Approved Date</th>
                                            <th class="wd-15p">Approver Comments</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${approvelStatus.rm_approved_date != null}">
                                            <tr>
                                                <td class="wd-15p">Reporting/Project Manager</td>
                                                <td class="wd-15p">${approvelStatus.rm_name} </td>
                                                <td class="wd-15p">${approvelStatus.rm_approved_date}</td>
                                                <td class="wd-15p">${approvelStatus.rm_remarks}</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${approvelStatus.buh_approved_date != null}">
                                            <tr>
                                                <td class="wd-15p">BUH </td>
                                                <td class="wd-15p">${approvelStatus.buh_name} </td>
                                                <td class="wd-15p">${approvelStatus.buh_approved_date}</td>
                                                <td class="wd-15p">${approvelStatus.buh_remarks}</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${approvelStatus.cfo_approved_date != null}"  >
                                            <tr>
                                                <td class="wd-15p">CFO </td>
                                                <td class="wd-15p">${approvelStatus.cfo_name} </td>
                                                <td class="wd-15p">${approvelStatus.cfo_approved_date}</td>
                                                <td class="wd-15p">${approvelStatus.cfo_remarks}</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${approvelStatus.finance_approved_date != null}"  >
                                            <tr>
                                                <td class="wd-15p">Finance </td>
                                                <td class="wd-15p">${approvelStatus.finance_name} </td>
                                                <td class="wd-15p">${approvelStatus.finance_approved_date}</td>
                                                <td class="wd-15p">${approvelStatus.finance_remarks}</td>
                                            </tr>
                                        </c:if>
                                        <c:if test="${approvelStatus.treasury_approved_date != null}"  >
                                            <tr>
                                                <td class="wd-15p">Treasury </td>
                                                <td class="wd-15p">${approvelStatus.treasury_name} </td>
                                                <td class="wd-15p">${approvelStatus.treasury_approved_date}</td>
                                                <td class="wd-15p">${approvelStatus.treasury_remarks}</td>
                                            </tr>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div class="section-tables">
                            <h4 class="slim-pagetitle" style="font-size:15px;border:0px; padding:10px 25px;">Settlement Status - ${travelDetails.statusDesc}</h4>
                            <div class="table-responsive text-center">
                                <input class="printbutton" onclick="printPage();" type="button" name="Print" value="Print">
                                <input class="backbutton" style="cursor: pointer;" onclick="javascript:history.go(-1);" type="button" name="submit" value="Back">
                            </div>
                        </div>
                </form>
            </div>
        </div>
    </body>   
</html>
