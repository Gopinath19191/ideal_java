<%-- 
    Document   : approverTravelView
    Created on : 9 Jun, 2018, 8:00:33 PM
    Author     : 16221
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="now" class="java.util.Date" />
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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" type="text/css"/>


        <!-- Slim CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slim.css">

        <!-- Script from theme -->
        <script src="${pageContext.request.contextPath}/lib/jquery/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/lib/popper.js/js/popper.js"></script>
        <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/lib/jquery.cookie/js/jquery.cookie.js"></script>
        <script src="${pageContext.request.contextPath}/lib/jquery-ui/js/jquery-ui.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.5.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <script src="${pageContext.request.contextPath}/lib/datatables/js/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/lib/datatables-responsive/js/dataTables.responsive.js"></script>
        <script src="${pageContext.request.contextPath}/lib/select2/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/lib/moment/js/moment.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js" ></script>
        <script src="${pageContext.request.contextPath}/js/slim.js"></script>
        <style>
            .section-title{
                color:#1b84e7;
            }
            .col-sm tr td{
                width:16.66%;
                padding: 3px;
            }
            .tableStructure {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                clear: both;
                color: #666666;
                border-collapse: collapse;
                font-family: "Roboto", "Helvetica Neue", Arial, sans-serif;
                font-size: 0.875rem;
                font-weight: 400;
                width:90%;
            }
            .tableStructure tr th {
                background: url(./css/images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;
                border: 1px solid #CEDFF1;
                font-weight: bolder;
                text-align: center;
            }
            .tableStructure tr td {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CEDFF1;
                padding: 3px;
            }
            .commentts{
                text-align: center;
                padding:30px;
            }
            input.approvebutton {
                background: url(./css/images/icon_btn_submit.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 5px 0 0;
                padding: 0 10px 0 30px;
                cursor:pointer;
            }
            input.rejectbutton {
                background: url(./css/images/icon_btn_cancel.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 5px 0 0;
                padding: 0 10px 0 30px;
                cursor:pointer;
            }
            .error_message{
                color: red;
                font-size: small;
                display: none;
            }
            .fc-datepicker{
                background: url(./css/images/calender_icon.png) no-repeat 5px 3px;
                background-color: #FFF;
                border-style: groove;
                padding-left: 30px;
                width:163px;
            }
            td.ui-datepicker-unselectable.ui-state-disabled {
                opacity: 0.25;
            }
            .preloader {
                position: fixed;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: rgba(255,255,255,0.7);
                z-index: 999999;
            }
            .sk-circle{
                top:200px;
            }
            #reimbursable_type,#settlement_policy{
                color:green;
                font-weight: bold;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function() {
                
                $(window).on("load", function () {
                    preloaderFadeOutTime = 500;
                    function showPreloader() {
                        var preloader = $('.preloader');
                        preloader.fadeIn();
                    }
                    function hidePreloader() {
                        var preloader = $('.preloader');
                        preloader.fadeOut(preloaderFadeOutTime);
                    }
                    hidePreloader();
                   
                });
                
                $('.fc-datepicker').datepicker({
                    showOtherMonths: true,
                    selectOtherMonths: true,
                    changeMonth:true,
                    changeYear:true,
                    dateFormat: 'dd-M-yy',
                    minDate : '${travel_details.requested_date}',
                    maxDate:0
                });
                    
                $("#approveAction").click(function (e) {
                    $("#status").val('2');
                    validateRMForm();
                });
                $("#rejectAction").click(function (e) {
                    $("#status").val('3');
                    validateRMForm();
                });
                function validateRMForm(){
                    var error =0;
                    var comments = $("#rm_remarks").val();
                    if(document.getElementById("policy_confirm").checked){
                        $("#rm_agree_error").css({"display": "none"});
                    }else{
                        $("#rm_agree_error").css({"display": "block"});
                        error++;
                    }
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
                        $('.preloader').css("display","block");
                        return true;
                    }else{
                        return false;
                    }
                }
                                
                $("#buhApproveAction").click(function (e) {
                    $("#status").val('4');
                    validateBUHForm();
                });
                $("#buhRejectAction").click(function (e) {
                    $("#status").val('5');
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
                        $('.preloader').css("display","block");
                        return true;
                    }else{
                        return false;
                    }
                }
                
                $("#finApproveAction").click(function (e) {
                    $("#status").val('6');
                    validateFinForm();
                });
                $("#finRejectAction").click(function (e) {
                    $("#status").val('7');
                    validateFinForm();
                });
                
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
                        $('.preloader').css("display","block");
                        return true;
                    }else{
                        return false;
                    }
                }
                
                $("#TreasuryAction").click(function (e) {
                    $("#status").val('9');
                    validateTreasuryForm();
                });
                
                function validateTreasuryForm(){
                    var error =0;
                    var comments = $("#treasury_comments").val();
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
            function changeReimbursableType(val){
                if(val=="N"){
                    document.getElementById('reimbursable_type').innerHTML = 'Non Client Reimbursable';
                }else{
                    document.getElementById('reimbursable_type').innerHTML = 'Client Reimbursable';
                }

            }
            function changeSettlementType(val){
                if(val=="h"){
                    document.getElementById('settlement_policy').innerHTML = 'Hinduja Tech';
                }else{
                    document.getElementById('settlement_policy').innerHTML = 'Customer';
                }

            }
        function isNumberKey(evt){
            var charCode = (evt.which) ? evt.which : event.keyCode
            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
            else
                return true;
        }
        </script>
    </head>
    <body>
        <div class ='preloader'>
            <div class="sk-circle">
                <div class="sk-circle1 sk-child"></div>
                <div class="sk-circle2 sk-child"></div>
                <div class="sk-circle3 sk-child"></div>
                <div class="sk-circle4 sk-child"></div>
                <div class="sk-circle5 sk-child"></div>
                <div class="sk-circle6 sk-child"></div>
                <div class="sk-circle7 sk-child"></div>
                <div class="sk-circle8 sk-child"></div>
                <div class="sk-circle9 sk-child"></div>
                <div class="sk-circle10 sk-child"></div>
                <div class="sk-circle11 sk-child"></div>
                <div class="sk-circle12 sk-child"></div>
              </div>
        </div>
<!--        <div class="slim-header">
            <div class="container">
                <div id="logo"><a href="http://ideal.hindujatech.com/"><img src="http://ideal.hindujatech.com/img/ideal_logo.jpg" alt="" title="" border="0" /></a></div>
            </div>
        </div>-->
        <div class="slim-mainpanel">
            <div class="container">
                <div class="slim-pageheader">
                    <ol class="breadcrumb slim-breadcrumb">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/listPendingApprovers.htm?type=${type}&approver_type=${approver_type}">List Request</a></li>
                    </ol>
                    <h6 class="slim-pagetitle">Travel View</h6>
                </div>
                <div class="alert alert-info" role="alert">
                    Deviations will be marked in Red
                </div>
                <form action="approveDomestic.htm" name="saveDetails" id="saveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
                    <div class="section-wrapper">
                        <h6 class="slim-pagetitle" style="padding:0px;border:0px;text-align: center;">Travel Id - ${travel_details.tp_reference_id}</h6>
                        <input type="hidden" value="${travel_details.master_id}" name="master_id"/>
                        <input type="hidden" value="${travel_details.deviation}" name="deviation"/>
                        <input type="hidden" value="${travel_details.status}" id="status" name="status"/>
                        <input type="hidden" value="${travel_details.tp_reference_id}" id="tp_reference_id" name="tp_reference_id"/>
                        <label class="section-title">Employee Details</label>
                        <table class="col-sm">
                            <tr>
                                <td><strong class="tx-inverse tx-medium">Employee Id </strong></td>
                                <td><span class="text-muted">${travel_details.employee_number}</span></td>
                                <td><strong class="tx-inverse tx-medium">Employee Name </strong></td>
                                <td><span class="text-muted">${travel_details.employee_name}</span></td>
                                <td><strong class="tx-inverse tx-medium">Band Name </strong></td>
                                <td><span class="text-muted">${travel_details.band_name}</span></td>
                            </tr>
                            <tr>
                                <td><strong class="tx-inverse tx-medium">Practice Group </strong></td>
                                <td><span class="text-muted">${travel_details.unit_name}</span></td>
                                <td><strong class="tx-inverse tx-medium">Sub Practice Group </strong></td>
                                <td><span class="text-muted">${travel_details.sub_unit_name}</span></td>
                                <td><strong class="tx-inverse tx-medium">Designation </strong></td>
                                <td><span class="text-muted">${travel_details.designation_name}</span></td>
                            </tr>
                            <tr>
                                <td><strong class="tx-inverse tx-medium">Location </strong></td>
                                <td><span class="text-muted">${travel_details.location_name}</span></td>
                                <td><strong class="tx-inverse tx-medium">Contact Number </strong></td>
                                <td><span class="text-muted">${travel_details.contact_number}</span></td>
                                <td><strong class="tx-inverse tx-medium">Request Date </strong></td>
                                <td><span class="text-muted">${travel_details.requested_date}</span></td>
                            </tr>
                        </table>
                        <label class="section-title">Travel Details</label>
                        <table class="col-sm">
                            <tr>
                                <td><strong class="tx-inverse tx-medium">Start Date </strong></td>
                                <td><span class="text-muted">${travel_details.travel_start_date}</span></td>
                                <td><strong class="tx-inverse tx-medium">End Date </strong></td>
                                <td><span class="text-muted">${travel_details.travel_end_date}</span></td>
                                <td><strong class="tx-inverse tx-medium">Travel Term </strong></td>
                                <td><span class="text-muted">${travel_details.travel_term}</span></td>
                            </tr>
                            <tr>
                                <td><strong class="tx-inverse tx-medium">Project Travel </strong></td>
                                <td><span class="text-muted">${travel_details.travel_billable}</span></td>
                                <td><strong class="tx-inverse tx-medium">Customer </strong></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${travel_details.customer_id == '-1'}">
                                            <span class="text-muted">${travel_details.customer_others}</span>
                                        </c:when>
                                        <c:otherwise><span class="text-muted">${travel_details.customer_name}</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td><strong class="tx-inverse tx-medium">Project </strong></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${travel_details.project_id == '-1'}">
                                            <span class="text-muted">${travel_details.project_others}</span>
                                        </c:when>
                                        <c:otherwise><span class="text-muted">${travel_details.project_name}</span></c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td><strong class="tx-inverse tx-medium">Client Reimbursable </strong></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${approver_type=='rm' || approver_type=='finance'}">
                                            <select class="drop_down" id="client_reimbursable" name="client_reimbursable" onchange="changeReimbursableType(this.value);">
                                                <option value="">--Select Reimbursable--</option>
                                                <option value="N" ${travel_details.client_reimbursable == 'No'?'Selected':''}>No</option>
                                                <option value="Y" ${travel_details.client_reimbursable == 'Yes'?'Selected':''}>Yes</option>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">${travel_details.client_reimbursable}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><strong class="tx-inverse tx-medium">Settlement Policy </strong></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${approver_type=='rm' || approver_type=='finance'}">
                                            <select name="settlemet_policy" id="settlement_type" class="drop_down" onchange="changeSettlementType(this.value);">
                                                <option value="">--Settlement Policy--</option>
                                                <c:forEach items="${settlementPolicy}" var="setlist" >
                                                    <option value="${setlist.config_key}" ${travel_details.config_key == setlist.config_key ?'selected':''}>${setlist.config_value}</option>
                                                </c:forEach>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="text-muted">${travel_details.settlemet_policy}</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <c:choose>
                                    <c:when test="${travel_details.deviation == 'Y'}">
                                        <td><strong class="tx-inverse tx-medium">BUH Approver </strong></td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${approver_type == 'rm'}">
                                                    <select class="drop_down" name="buh_id">
                                                        <c:forEach items="${buh_list}" var="buhList" >
                                                            <option value="${buhList.buh_id}" ${travel_details.buh_id == buhList.buh_id ?'selected':''}>${buhList.buh_name}</option>
                                                        </c:forEach>
                                                    </select>
                                                </c:when>
                                                <c:otherwise>
                                                    <span class="text-muted">${travel_details.buh_name}</span>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="buh_id" value="${travel_details.buh_id}"/>
                                    </c:otherwise>
                                </c:choose>
                                
                            </tr>
                            <tr>
                                <td><strong class="tx-inverse tx-medium">Purpose of Travel </strong></td>
                                <td><span class="text-muted">${travel_details.travel_purpose}</span></td>
                                <td><strong class="tx-inverse tx-medium">Guest Booking </strong></td>
                                <td><span class="text-muted">${travel_details.guest_booking}</span></td>
                                <c:if test="${travel_details.guest_booking == 'Yes'}">
                                    <td><strong class="tx-inverse tx-medium">Guest Name </strong></td>
                                    <td><span class="text-muted">${travel_details.guest_booking_name}</span></td>
                                </c:if>
                            </tr>
                        </table>
                        <c:if test="${approver_type != 'admin'}">
                            <label class="section-title">Advance Details</label>
                            <table class="tableStructure" id="ticket_table" width="90%">
                                <tr class="headerCenter">
                                    <th width="10%">Required Date</th>
                                    <th width="10%">Advance Amount</th>
                                    <th width="10%">Currency</th>
                                    <th width="15%">Remarks</th>
                                    <c:if test="${approver_type=='treasury'}">
                                        <th width="15%">Deposited Date</th>
                                        <th width="15%">Deposited Amount</th>
                                        <th width="15%">Deposited Currency</th>
                                    </c:if>
                                </tr>
                                <c:if test="${fn:length(advance_details)>0}">
                                    <c:forEach items="${advance_details}" var="adv_det">
                                        <tr>
                                            <td><span class="text-muted">${adv_det.advance_date}</span></td>
                                            <td><span class="text-muted">${adv_det.advance_amount}</span></td>
                                            <td><span class="text-muted">${adv_det.currency_name}</span></td>
                                            <td><span class="text-muted">${adv_det.advance_remarks}</span></td>
                                            <c:if test="${approver_type == 'treasury' && (travel_details.status == '6' || travel_details.status == '8' || travel_details.status == '11') && adv_det.deposited_date == null}">
                                                <input type="hidden" name="arr_advance_id" id="advance_id" value="${adv_det.advance_id}"/>
                                                <fmt:formatDate value="${now}" var="currentDate" type="both" pattern="dd-MMM-yyyy" />
                                                <td><input type="text" class="fc-datepicker" id="deposited_date" name="arr_deposited_date" placeholder="DD-MMM-YYYY" value="${currentDate}"/></td>
                                                <td><input type="text" id="deposited_amount" name="arr_deposited_amount" value="${adv_det.advance_amount}" onkeypress="return isNumberKey(event)" /></td>
                                                <td>
                                                    <input type="hidden" id="deposited_currency_id" name="arr_deposited_currency" value="${adv_det.currency_id}"/>
                                                    ${adv_det.currency_name}
                                                </td>
                                            </c:if>
                                            <c:if test="${approver_type == 'treasury' && adv_det.deposited_date != null}">
                                                <td>${adv_det.deposited_date}</td>
                                                <td>${adv_det.deposited_amount}</td>
                                                <td>${adv_det.deposited_currency}</td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${fn:length(advance_details)==0}">
                                    <tr>
                                        <td colspan="4" style="text-align: center;">No Record to display</td>
                                    </tr>
                                </c:if>

                            </table>
                        </c:if>
                        
                        <c:if test="${fn:length(ticket_details)>0 && approver_type != 'treasury'}">
                            <label class="section-title">Ticket Details</label>
                            <div id="TicketTab">
                                <table class="tableStructure" id="ticket_table">
                                    <tr class="headerCenter">
                                        <th width="14%"><label>Booking Type</label></th>
                                        <th width="10%"><label>From</label></th>
                                        <th width="10%"><label>To</label></th>
                                        <th width="10%"><label>Date of Travel</label></th>
                                        <th width="10%"><label>Preference</label></th>
                                        <th width="10%"><label>Mode</label></th>
                                        <th width="10%"><label>Remarks</label></th>
                                        <c:if test="${approver_type == 'admin'}">
                                            <th width="10%"><label>Status</label></th>
                                            <th style="word-break:break-all;width:150px;"><label>Attachment</label></th>
                                        </c:if>
                                    </tr>
                                    <c:if test="${fn:length(ticket_details)>0}">
                                        <c:forEach items="${ticket_details}" var="tic_det">
                                            <tr>
                                                <td>
                                                    <!--<span class="text-muted">${tic_det.ticket_book_type}</span>-->
                                                    <!--<input type="hidden" name="ticket_booking_type" value="${tic_det.ticket_book_type}"/>-->
                                                    <select name="ticket_booking_type" id="ticket_booking_type">
                                                        <c:forEach items = "${bookingType}" var ="booking_type">
                                                            <option value="${booking_type.config_key}" ${tic_det.ticket_book_config == booking_type.config_key ?'selected':''}>${booking_type.config_value}</option>
                                                        </c:forEach>
                                                    </select>
                                                    <input type="hidden" name="ticket_id" value="${tic_det.travel_ticket_id}"/>
                                                </td>
                                                <c:choose>
                                                    <c:when test="${tic_det.travel_from_city_others == ''}">
                                                        <td><span class="text-muted">${tic_det.travel_from_city}</span></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><span class="text-muted">${tic_det.travel_from_city_others}</span></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${tic_det.travel_to_city_others == ''}">
                                                        <td><span class="text-muted">${tic_det.travel_to_city}</span></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><span class="text-muted">${tic_det.travel_to_city_others}</span></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td><span class="text-muted">${tic_det.travel_ticket_date}</span></td>
                                                <td><span class="text-muted">${tic_det.travel_travel_preference}</span></td>
                                                <td><span class="text-muted"><label style="color:${travel_details.deviation == 'Y' ?'red':''};font-weight:bold;">${tic_det.travel_travel_mode}</label></span></td>
                                                <td><span class="text-muted">${tic_det.travel_ticket_remarks}</span></td>
                                                <c:if test="${approver_type == 'admin' && tic_det.ticket_book_type == 'Company Booking'}">
                                                    <td>
                                                        <select id="travel_booking_status" name="travel_booking_status">
                                                            <option>-- Select --</option>
                                                            <option value="Y" ${tic_det.booking_status == 'Y' ?'selected':''}>Booked</option>
                                                            <option value="N" ${tic_det.booking_status == 'N' ?'selected':''}>Not Booked</option>
                                                        </select>
                                                    </td>
                                                    <td style="word-break:break-all;width:150px;">
                                                        <c:if test="${tic_det.file_name != null || tic_det.file_name !=''}">
                                                            <a href="fileDownload.htm?fileName=${tic_det.file_name}" target="_blank" name="file">${tic_det.file_name}</a>
                                                        </c:if>
                                                        <input type="file" name="ticket_attachment_${tic_det.travel_ticket_id}" id="ticket_attachment_${tic_det.travel_ticket_id}" class="filebox" size="20" style="width:140px;">
                                                    </td>
                                                </c:if>
                                                <c:if test="${approver_type == 'admin' && tic_det.ticket_book_type == 'Self Booking'}">
                                                    <td>
                                                        <input type="hidden" id="travel_booking_status" name="travel_booking_status" value="Y" />
                                                    </td>
                                                    <td style="word-break:break-all;width:150px;">
                                                        <input type="hidden" name="ticket_attachment_${tic_det.travel_ticket_id}" id="ticket_attachment_${tic_det.travel_ticket_id}"/>
                                                    </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${fn:length(ticket_details)==0}">
                                        <tr>
                                            <td colspan="7">No Record to display</td>
                                        </tr>
                                    </c:if>
                                </table>
                            </div>
                        </c:if>
                        <c:if test="${fn:length(hotel_details)>0 && approver_type != 'treasury'}">
                            <label class="section-title">Hotel Details</label>
                            <div id="HotelTab">
                                <table class="tableStructure" id="hotel_tabel">
                                    <tr class="headerCenter">
                                        <th width="14%"><label>Booking Type</label></th>
                                        <th width="10%"><label>City/Town</label></th>
                                        <th width="10%"><label>Location Preference</label></th>
                                        <th width="10%"><label>From Date</label></th>
                                        <th width="10%"><label>To Date</label></th>
                                        <th width="10%"><label>Remarks</label></th>
                                        <c:if test="${approver_type == 'admin'}">
                                            <th width="10%"><label>Status</label></th>
                                            <th style="word-break:break-all;width:150px;"><label>Attachment</label></th>
                                        </c:if>
                                    </tr>
                                    <c:if test="${fn:length(hotel_details)>0}">
                                        <c:forEach items="${hotel_details}" var="hot_det">
                                            <tr>
                                                <td>
                                                    <!--<span class="text-muted">${hot_det.hotel_book_type}</span>-->
                                                    <!--<input type="hidden" name="hotel_booking_type" value="${hot_det.hotel_book_type}"/>-->
                                                    <input type="hidden" name="hotel_id" value="${hot_det.hotelid}"/>
                                                    <select name ="hotel_booking_type" id="hotel_booking_type">
                                                        <c:forEach items = "${bookingType}" var ="booking_type">
                                                            <option value="${booking_type.config_key}" ${hot_det.ticket_book_config == booking_type.config_key ?'selected':''}>${booking_type.config_value}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <c:choose>
                                                    <c:when test="${hot_det.hotel_city_others == ''}">
                                                        <td><span class="text-muted">${hot_det.hotel_city}</span></td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td><span class="text-muted">${hot_det.hotel_city_others}</span></td>
                                                    </c:otherwise>
                                                </c:choose>
                                                <td><span class="text-muted">${hot_det.hotel_location}</span></td>
                                                <td><span class="text-muted">${hot_det.hotel_from_date}</span></td>
                                                <td><span class="text-muted">${hot_det.hotel_to_date}</span></td>
                                                <td><span class="text-muted">${hot_det.hotel_hotel_remarks}</span></td>
                                                <c:if test="${approver_type == 'admin' && hot_det.hotel_book_type == 'Company Booking'}">
                                                    <td>
                                                        <select id="hotel_booking_status" name="hotel_booking_status">
                                                            <option>-- Select --</option>
                                                            <option value="Y" ${hot_det.booking_status == 'Y' ?'selected':''}>Booked</option>
                                                            <option value="N" ${hot_det.booking_status == 'N' ?'selected':''}>Not Booked</option>
                                                        </select>
                                                    </td>
                                                    <td style="word-break:break-all;width:150px;">
                                                        <c:if test="${hot_det.file_name != null || hot_det.file_name !=''}">
                                                            <a href="fileDownload.htm?fileName=${hot_det.file_name}" target="_blank" name="file">${hot_det.file_name}</a>
                                                        </c:if>
                                                        <input type="file" display="none" class="upload_file" id="hotel_attachment_${hot_det.hotelid}" name="hotel_attachment_${hot_det.hotelid}" class="filebox" size="20" style="width:140px;">
                                                    </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${fn:length(hotel_details)==0}">
                                        <tr>
                                            <td colspan="6">No Record to display</td>
                                        </tr>
                                    </c:if>
                                </table>
                            </div>
                        </c:if>
                        <%--<c:if test="${travel_details.band_id == 2 || travel_details.band_id == 3 || travel_details.band_id == 15 || travel_details.band_id == 35 || travel_details.band_id == 36}" >--%>
                            <c:if test="${fn:length(cab_details)>0 && approver_type != 'treasury'}">
                                <label class="section-title">Conveyance Details</label>
                                <div id="ConveyanceTab" >
                                    <table class="tableStructure">
                                        <tr class="headerCenter">
                                            <th width="12%">City/Town</th>
                                            <th width="16%">Pickup Address</th>
                                            <th width="16%">Drop Address</th>
                                            <th width="12%">Date</th>
                                            <th width="12%">Time</th>
                                            <th width="16%">Remarks</th>
                                        </tr>
                                        <c:if test="${fn:length(cab_details)>0}">
                                            <c:forEach items="${cab_details}" var="cab_det">
                                                <tr>
                                                    <c:choose>
                                                        <c:when test="${cab_det.cab_city_others == ''}">
                                                            <td><span class="text-muted">${cab_det.cab_city}</span></td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td><span class="text-muted">${cab_det.cab_city_others}</span></td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td><span class="text-muted">${cab_det.cab_pickup}</span></td>
                                                    <td><span class="text-muted">${cab_det.cab_drop}</span></td>
                                                    <td><span class="text-muted">${cab_det.cab_date}</span></td>
                                                    <td><span class="text-muted">${cab_det.cab_time_hrs}</span></td>
                                                    <td><span class="text-muted">${cab_det.cab_remarks}</span></td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                        <c:if test="${fn:length(cab_details)==0}">
                                            <tr>
                                                <td colspan="6">No Record to display</td>
                                            </tr>
                                        </c:if>
                                    </table>
                                </div>
                            </c:if>
                            <c:if test="${fn:length(attachment_type)>0}">
                                <label class="section-title">Attachments</label>
                                <c:forEach items="${attachment_type}" var="attachment_type">
                                    <a href="fileDownload.htm?fileName=${attachment_type.file_name}" target="_blank" name="file">${attachment_type.file_name}</a><br>
                                </c:forEach>
                            </c:if>
                        <%--</c:if>--%>
                        <c:if test="${travel_details.rm_approved_date != null}">
                            <label class="section-title">Approver Details</label>
                            <table class="tableStructure" style="width:70%">
                                <tbody>
                                    <tr>
                                        <th></th>
                                        <th>Approver Name</th>
                                        <th>Approved Date</th>
                                        <th>Approver Comments</th>
                                    </tr>
                                    <c:if test="${travel_details.rm_approved_date != null}">
                                        <tr>
                                            <td><label>Reporting/Project Manager</label></td>
                                            <td>${travel_details.rm_name}</td>
                                            <td>${travel_details.rm_approved_date}</td>
                                            <td>${travel_details.rm_remarks}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${travel_details.buh_approved_date != null}">
                                        <tr>
                                            <td><label>BUH</label></td>
                                            <td>${travel_details.buh_name}</td>
                                            <td>${travel_details.buh_approved_date}</td>
                                            <td>${travel_details.buh_remarks}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${travel_details.finance_approved_date != null}">
                                        <tr>
                                            <td><label>Business Operation</label></td>
                                            <td>${travel_details.finance_name}</td>
                                            <td>${travel_details.finance_approved_date}</td>
                                            <td>${travel_details.finance_remarks}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${travel_details.admin_approved_date != null}">
                                        <tr>
                                            <td><label>Admin</label></td>
                                            <td>${travel_details.admin_name}</td>
                                            <td>${travel_details.admin_approved_date}</td>
                                            <td>${travel_details.admin_remarks}</td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${travel_details.treasury_approved_date != null}">
                                        <tr>
                                            <td><label>Treasury</label></td>
                                            <td>${travel_details.treasury_name}</td>
                                            <td>${travel_details.treasury_approved_date}</td>
                                            <td>${travel_details.treasury_comments}</td>
                                        </tr>
                                    </c:if>
                                </tbody>
                            </table>
                        </c:if>
                        <c:choose>
                            <c:when test="${approver_type == 'rm' && travel_details.status == '1'}">
                                <div class="commentts" id="comments_id">
                                    <input type="checkbox" id="policy_confirm"/>
                                        <label style="padding-left:10px;">I confirm that travel request is 
                                        <label id="reimbursable_type">
                                            <c:choose>
                                                <c:when test="${travel_details.client_reimbursable == 'No'}"> Non Client Reimbursable </c:when>
                                                <c:otherwise> Client Reimbursable </c:otherwise>
                                            </c:choose>
                                        </label>  and Settlement as per 
                                        <label id="settlement_policy">${travel_details.settlemet_policy}</label> travel policy</label><br>
                                    <span id="rm_agree_error" class="error_message">Please Confirm the reimbursable and settlement policy</span>
                                    <strong class="tx-inverse tx-medium">Comments : </strong>
                                    <input type="text" name="rm_remarks" id="rm_remarks"/>
                                    <span id="rm_remarks_error" class="error_message">Please enter remarks</span>
                                    <input type="hidden" name="approver_type" value="${approver_type}"/>
                                    <input type="hidden" name="list_type" value="${type}"/>
                                </div>
                                <div class="buttonAlignment" id="submitDiv">
                                    <div class="submit buttonAlignment" align="center" id="btnGroup">
                                        <input name="travelbtn" id="rejectAction" type="button" value="Reject" class="buttons rejectbutton">
                                        <input name="travelbtn" id="approveAction" type="button" readonly value="Approve" class="buttons approvebutton">
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${approver_type == 'buh' && travel_details.status == '2' && travel_details.deviation == 'Y'}">
                                <div class="commentts" id="comments_id">
                                    <strong class="tx-inverse tx-medium">Comments : </strong>
                                    <input type="text" name="buh_remarks" id="buh_remarks"/>
                                    <span id="buh_remarks_error" class="error_message">Please enter remarks</span>
                                    <input type="hidden" name="approver_type" value="${approver_type}"/>
                                    <input type="hidden" name="list_type" value="${type}"/>
                                </div>
                                <div class="buttonAlignment" id="submitDiv">
                                    <div class="submit buttonAlignment" align="center" id="btnGroup">
                                        <input name="travelbtn" id="buhRejectAction" type="button" value="Reject" class="buttons rejectbutton">
                                        <input name="travelbtn" id="buhApproveAction" type="button" readonly value="Approve" class="buttons approvebutton">
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${(approver_type == 'finance' && travel_details.status == '2' && travel_details.deviation == 'N') ||(approver_type == 'finance' && travel_details.status == '4' && travel_details.deviation == 'Y') }">
                                <div class="commentts" id="comments_id">
                                    <strong class="tx-inverse tx-medium">Comments : </strong>
                                    <input type="text" name="finance_remarks" id="finance_remarks"/>
                                    <span id="finance_remarks_error" class="error_message">Please enter remarks</span>
                                    <input type="hidden" name="approver_type" value="${approver_type}"/>
                                    <input type="hidden" name="list_type" value="${type}"/>
                                </div>
                                <div class="buttonAlignment" id="submitDiv">
                                    <div class="submit buttonAlignment" align="center" id="btnGroup">
                                        <input name="travelbtn" id="finRejectAction" type="button" value="Reject" class="buttons rejectbutton">
                                        <input name="travelbtn" id="finApproveAction" type="button" readonly value="Approve" class="buttons approvebutton">
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${approver_type == 'admin' && (travel_details.status == '6' || travel_details.status == '9' || travel_details.status == '8' || travel_details.status == '11')}">
                                <div class="commentts" id="comments_id">
                                    <strong class="tx-inverse tx-medium">Comments : </strong>
                                    <input type="text" name="admin_remarks" id="admin_remarks"/>
                                    <span id="admin_remarks_error" class="error_message">Please enter remarks</span>
                                    <input type="hidden" name="approver_type" value="${approver_type}"/>
                                    <input type="hidden" name="list_type" value="${type}"/>
                                </div>
                                <div class="buttonAlignment" id="submitDiv">
                                    <div class="submit buttonAlignment" align="center" id="btnGroup">
                                        <input name="travelbtn" id="adminApproveAction" type="button" readonly value="Ticket Booked" class="buttons approvebutton">
                                    </div>
                                </div>
                            </c:when>
                            <c:when test="${approver_type == 'treasury' && (travel_details.status == '6' || travel_details.status == '8' || travel_details.status == '11')}">
                                <div class="commentts" id="comments_id">
                                    <strong class="tx-inverse tx-medium">Comments : </strong>
                                    <input type="text" name="treasury_comments" id="treasury_comments"/>
                                    <span id="treasury_remarks_error" class="error_message">Please enter remarks</span>
                                    <input type="hidden" name="approver_type" value="${approver_type}"/>
                                    <input type="hidden" name="list_type" value="${type}"/>
                                </div>
                                <div class="buttonAlignment" id="submitDiv">
                                    <div class="submit buttonAlignment" align="center" id="btnGroup">
                                        <input name="travelbtn" id="TreasuryAction" type="button" readonly value="Amount Deposited" class="buttons approvebutton">
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
