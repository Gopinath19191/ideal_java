<%-- 
    Document   : domesticTravel
    Created on : 30 May, 2018, 6:47:13 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
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
            .col-sm-4{
                width:33.33%;
            }
            .fc-datepicker,.fc-datepicker2,.fc-datepicker3,.fc-datepicker4{
                background: url(./css/images/calender_icon.png) no-repeat 5px 3px;
                background-color: #FFF;
                border-style: groove;
                padding-left: 30px;
                width:163px;
            }
            .drop_down{
                width:163px;
            }
            input[readonly]{
                background-color: #e9ecef;
                opacity: 1;
                border:1px solid #ced4da;
            }
            div.contentHolderMax{
                width: 100%;
                float: left;
            }
            div.style_tab{
                float: left;
                border-bottom: 1px solid #bccfea;
                width: 100%;
            }
            span.ticket_tab {
                padding: 5px 7px;
                font-weight: bold;
                background: #3579c6;
                color:#fff;
                width:110px;
                height: 33px;
                cursor: pointer;
                float: left;
                border: 2px solid #bccfea;
                text-align: center;
                position: relative;
            }
            span.active_tab {
                background: #fff;
                color:#3579c6;
                border-bottom-color: #FFF;
            }
            .tabContent{
                width:100%;
            }
            .tableStructure{
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                clear: both;
                color: #666666;
                border-collapse: collapse;
                font-family: "Roboto", "Helvetica Neue", Arial, sans-serif;
                font-size: 0.875rem;
                font-weight: 400;
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
                padding:3px;
            }
            .tabContent {
                width: 1000px;
                float: left;
                margin: 10px; 
                font-weight: normal;
            }
            input.savebutton {
                background: url(./css/images/icon_btn_save.png) no-repeat scroll 8px 8px #3579c6;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 50px 5px 0 0;
                padding: 0 10px 0 30px;
                border-radius: 10px;
                cursor: pointer;
                width:80px;
            }
            input.submitbutton {
                background: url(./css/images/icon_btn_submit.png) no-repeat scroll 8px 8px #3579c6;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 50px 5px 0 0;
                padding: 0 10px 0 30px;
                border-radius: 10px;
                cursor: pointer;
                width:90px;
            }
            td.ui-datepicker-unselectable.ui-state-disabled {
                opacity: 0.25;
            }
            .error_message{
                color: red;
                font-size: small;
                display: none;
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
        </style>
        <script type="text/javascript">
            var myAutoComplet = null;
            var myDatePicker = null;
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
                
                myAutoComplet = function() {
                    $(".autobox").autocomplete("${pageContext.request.contextPath}/citySearch.htm?type=${employee_details.country_id}", {
                        minChars: 2,
                        matchContains: true
                    });
                    $(".autobox").result(function(event, data) {
                        if (data) {
                            $(this).parent().find(".hiddenbox").val(data[1]);
                        }
                    });
                }
                
                
                myDatePicker = function(){
                    var frmDt=$("#request_from_date").val();
                    var toDt=$("#request_to_date").val();
                    if(frmDt=="" || toDt==""){
                        $(".fc-datepicker3").datepicker("destroy");
                        $(".fc-datepicker4").datepicker("destroy");
                    }else{
                        firstDate = new Date(frmDt);
                        secondDate = new Date(toDt);
                        $('.fc-datepicker3').datepicker({
                            changeMonth:true,
                            changeYear:true,
                            dateFormat: 'dd-M-yy',
                            minDate:firstDate,
                            maxDate:secondDate
                        });
                        $('.fc-datepicker4').datepicker({
                            changeMonth:true,
                            changeYear:true,
                            dateFormat: 'dd-M-yy',
                            minDate:firstDate-1,
                            maxDate:secondDate
                        });
                    }
                }
                myAutoComplet();
                myDatePicker();
                $("#submit_btn").click(function (e) {
                    $("#status").val('1');
                    validateForm();
//                    $("#saveDetails").submit();
                });
                $("#save_btn").click(function (e) {
                    $("#status").val('0');
                    var error = 0;
                    var mob_no = $("#contact_number").val();
                    var from_date = $("#request_from_date").val();
                    var to_date = $("#request_to_date").val();
                    if(mob_no == "" || mob_no == null){
                        $("#contact_number").css({"outline": "1px solid red"});
                        $("#contact_number_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#contact_number").css({"outline": "none"});
                        $("#contact_number_error").css({"display": "none"});
                    }
                    
                    if(from_date =="" || from_date==null){
                        $("#request_from_date").css({"outline": "1px solid red"});
                        $("#request_from_date_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#request_from_date").css({"outline": "none"});
                        $("#request_from_date_error").css({"display": "none"});
                    }
                    
                    if(to_date =="" || to_date==null){
                        $("#request_to_date").css({"outline": "1px solid red"});
                        $("#request_to_date_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#request_to_date").css({"outline": "none"});
                        $("#request_to_date_error").css({"display": "none"});
                    }
                    if(error==0){
                        $("#saveDetails").submit();                       
                        $('.preloader').css("display","block");
                        return true;
                    }else{
                        return false;
                    }
                });
                function validateForm(){
                    var error = 0;
                    var mob_no = $("#contact_number").val();
                    var from_date = $("#request_from_date").val();
                    var to_date = $("#request_to_date").val();
                    var project_travel = $("#travel_billable").val();
                    var cust = $("#customer_id").val();
                    var prjt = $("#project_id").val();
                    var cust_other = $("#customer_others").val();
                    var prjt_other = $("#project_others").val();
                    var client_reim = $("#client_reimbursable").val();
                    var sett_type = $("#settlement_type").val();
                    var purpose = $("#travel_purpose").val();
                    var guest_type = $("#guest_booking").val();
                    var guest_name = $("#guest_booking_name").val();
                    var adv_date = $("#advance_date").val();
                    var adv_amt = $("#advance_amount").val();
                    if(mob_no == "" || mob_no == null){
                        $("#contact_number").css({"outline": "1px solid red"});
                        $("#contact_number_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#contact_number").css({"outline": "none"});
                        $("#contact_number_error").css({"display": "none"});
                    }
                    
                    if(from_date =="" || from_date==null){
                        $("#request_from_date").css({"outline": "1px solid red"});
                        $("#request_from_date_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#request_from_date").css({"outline": "none"});
                        $("#request_from_date_error").css({"display": "none"});
                    }
                    
                    if(to_date =="" || to_date==null){
                        $("#request_to_date").css({"outline": "1px solid red"});
                        $("#request_to_date_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#request_to_date").css({"outline": "none"});
                        $("#request_to_date_error").css({"display": "none"});
                    }
                    
                    if(project_travel =="" || project_travel==null){
                        $("#travel_billable").css({"outline": "1px solid red"});
                        $("#travel_billable_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#travel_billable").css({"outline": "none"});
                        $("#travel_billable_error").css({"display": "none"});
                    }
                    
                    if(project_travel == "Y"){
                        if(cust =="" || cust==null){
                            $("#customer_id").css({"outline": "1px solid red"});
                            $("#customer_id_error").css({"display": "block"});
                            error++;
                        }else{
                            $("#customer_id").css({"outline": "none"});
                            $("#project_id_error").css({"display": "none"});
                        }
                        
                        if(prjt =="" || prjt==null){
                            $("#project_id").css({"outline": "1px solid red"});
                            $("#project_id_error").css({"display": "block"});
                            error++;
                        }else{
                            $("#project_id").css({"outline": "none"});
                            $("#project_id_error").css({"display": "none"});
                        }
                    }
                    if(cust == '-1'){
                        if(cust_other =="" || cust_other==null){
                            $("#customer_others").css({"outline": "1px solid red"});
                            $("#customer_others_error").css({"display": "block"});
                            error++;
                        }else{
                            $("#customer_others").css({"outline": "none"});
                            $("#customer_others_error").css({"display": "none"});
                        }
                    }
                    
                    if(prjt == '-1'){
                        if(prjt_other =="" || prjt_other==null){
                            $("#project_others").css({"outline": "1px solid red"});
                            $("#project_others_error").css({"display": "block"});
                            error++;
                        }else{
                            $("#project_others").css({"outline": "none"});
                            $("#project_others_error").css({"display": "none"});
                        }
                    }
                    if(client_reim =="" || client_reim==null){
                        $("#client_reimbursable").css({"outline": "1px solid red"});
                        $("#client_reimbursable_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#client_reimbursable").css({"outline": "none"});
                        $("#client_reimbursable_error").css({"display": "none"});
                    }

                    if(sett_type =="" || sett_type==null){
                        $("#settlement_type").css({"outline": "1px solid red"});
                        $("#settlement_type_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#settlement_type").css({"outline": "none"});
                        $("#settlement_type_error").css({"display": "none"});
                    }

                    if(purpose =="" || purpose==null){
                        $("#travel_purpose").css({"outline": "1px solid red"});
                        $("#travel_purpose_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#travel_purpose").css({"outline": "none"});
                        $("#travel_purpose_error").css({"display": "none"});
                    }
                    
                    if(guest_type =="" || guest_type==null){
                        $("#guest_booking").css({"outline": "1px solid red"});
                        $("#guest_booking_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#guest_booking").css({"outline": "none"});
                        $("#guest_booking_error").css({"display": "none"});
                    }
                    
                    if(guest_type =="Y"){
                        if(guest_name =="" || guest_name==null){
                            $("#guest_booking_name").css({"outline": "1px solid red"});
                            $("#guest_booking_name_error").css({"display": "block"});
                            error++;
                        }else{
                            $("#guest_booking_name").css({"outline": "none"});
                            $("#guest_booking_name_error").css({"display": "none"});
                        }
                    }
                    
                    if(adv_date =="" || adv_date==null){
                        $("#advance_date").css({"outline": "1px solid red"});
                        $("#advance_date_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#advance_date").css({"outline": "none"});
                        $("#advance_date_error").css({"display": "none"});
                    }
                    if(adv_amt =="" || adv_amt==null){
                        $("#advance_amount").css({"outline": "1px solid red"});
                        $("#advance_amount_error").css({"display": "block"});
                        error++;
                    }else{
                        $("#advance_amount").css({"outline": "none"});
                        $("#advance_amount_error").css({"display": "none"});
                    }
                    var ticket_validation = validateTicket();
                    if (ticket_validation>0) {
                        changeTab('Ticket');
                        $('#TicketSpan').addClass("active_tab");
                        return false;
                    }
                    var hotel_validation = validateHotel();
                    if (hotel_validation) {
                        changeTab('Hotel');
                        $('#HotelSpan').addClass("active_tab");
                        return false;
                    }
                    var cab_validation = validateCab();
                    if (cab_validation>0) {
                        changeTab('Conveyance');
                        $('#ConveyanceSpan').addClass("active_tab");
                        return false;
                    }
                    var file_name = $("#attach_doc_1").val().replace("C:\\fakepath\\", "");
                    var regex = /[!@#$%^&*(),?":{}|<>]/;
                    if(file_name.length>0){
                        if(regex.test(file_name)){
                            $("#file_type_error").css({"display": "block"});
                            changeTab('Attachment');
                            $('#AttachmentSpan').addClass("active_tab");
                            error++;
                        }else{
                            $("#file_type_error").css({"display": "none"});
                        }
                    }
                    
                    error = error+ticket_validation+hotel_validation+cab_validation;
                    if(error==0){
                        $("#saveDetails").submit();
                        $('.preloader').css("display","block");
                        return true;
                    }else{
                        return false;
                    }
                }
                
                function validateTicket(){
                    var ticket_error=0;
                    var trav_book_type = tableNullCheck("booking_type");
                    var trav_from_place = tableNullCheck("from_place");
                    var trav_to_place = tableNullCheck("to_place");
                    var trav_date = tableNullCheck("ticket_date");
                    var trav_pref = tableNullCheck("travel_preference");
                    var trav_mod = tableNullCheck("travel_mode");
                    if(trav_book_type > 0 ||trav_from_place > 0 || trav_to_place>0 ||trav_date>0 || trav_pref>0 || trav_mod>0){
                        ticket_error++;
                    }
                    return ticket_error;
                }
                
                function validateHotel(){
                    var hotel_error=0;
                    var hotel_book_type = tableNullCheck("hotel_booking_type");
                    var hotel_place = tableNullCheck("hotel_city");
                    var hotel_location = tableNullCheck("hotel_location");
                    var hotel_from_date = tableNullCheck("hotel_from_date");
                    var hotel_to_date = tableNullCheck("hotel_to_date");
                    if(hotel_book_type > 0 || hotel_place > 0 || hotel_location>0 ||hotel_from_date>0 || hotel_to_date>0){
                        hotel_error++;
                    }
                    return hotel_error;
                }
                
                function validateCab(){
                    var cab_error=0;
                    var band_id = $("#band_id").val();
                    if(band_id == 2 || band_id == 3 || band_id == 15 || band_id == 35 || band_id == 36){
                        var cab_city = tableNullCheck("cab_city");
                        var cab_pick = tableNullCheck("cab_pickup");
                        var cab_drop = tableNullCheck("cab_drop");
                        var cab_date = tableNullCheck("cab_date");
                        var cat_hrs = tableNullCheck("cab_time_hrs");
                        var cab_min = tableNullCheck("cab_time_min");
                        if(cab_city > 0 || cab_pick > 0 || cab_drop>0 ||cab_date>0 || cat_hrs>0 || cab_min>0){
                            cab_error++;
                        }
                        if(cab_city == 1 && cab_pick == 1 && cab_drop == 1 && cab_date == 1 && cat_hrs == 1 && cab_min == 1){
                            cab_error=0;
                        }
                    }else{
                        cab_error=0;
                    }
                    return cab_error;
                }
                
            });
            
            function tableNullCheck(class_name) {
                var NullError = 0;
                var class_length = document.querySelectorAll("." + class_name).length;
                for (var i = 0; i < class_length; i++) {
                    var class_name_val = document.querySelectorAll("." + class_name)[i].value;
                    var class_name_val_length = $.trim(class_name_val).length;
                    if (class_name_val_length > 0) {
                        document.querySelectorAll("." + class_name)[i].style.outline = "none";
                    } else {
                        document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                        NullError++;
                    }
                }
                return NullError;
            }
            $(function(){
                'use strict';
                $('#datatable1').DataTable({
                    responsive: true,
                    language: {
                        searchPlaceholder: 'Search...',
                        sSearch: '',
                        lengthMenu: '_MENU_ items/page'
                    }
                });

                $('#datatable2').DataTable({
                    bLengthChange: false,
                    searching: false,
                    responsive: true
                });

                // Select2
                $('.dataTables_length select').select2({ minimumResultsForSearch: Infinity });
                $("#request_from_date,#request_to_date").keydown(function() { 
                    return false;
                });
                
                $('#request_from_date').datepicker({dateFormat: 'dd-M-yy', changeMonth: true, changeYear: true,minDate:0,
                    maxDate:'+1Y',
                    onSelect: function(dateText, inst) {
                        $("#request_to_date").val("");
                        var minDate = $(this).datepicker('getDate') || new Date();
                        $("#request_to_date").datepicker({dateFormat: 'dd-M-yy', changeMonth: true, changeYear: true});
                        $("#request_to_date").datepicker("option", "minDate", minDate);
                        $("#request_to_date").datepicker("option", "maxDate", '+1Y');
                        setTimeout(function(){
                            $( "#request_to_date" ).datepicker('show');
                        }, 16);     
                    }
                });
                
                var frmDt=$("#request_from_date").val();
                var aa = frmDt.split("-");
                if(aa!=''){
                    $('.fc-datepicker2').datepicker({
                        showOtherMonths: true,
                        selectOtherMonths: true,
                        changeMonth:true,
                        changeYear:true,
                        dateFormat: 'yy-mm-dd',
                        minDate:new Date(aa[2], --aa[1], aa[0]),
                        maxDate:'+1Y'
                    });
                }else{
                    
                }
                $( "#request_to_date" ).click(function() {
                    var frmDt=$("#request_from_date").val();
                    if(frmDt==""){
                        alert("Please select From Date");
                    }else{
                        
                    }
                });
                $( ".fc-datepicker3" ).click(function() {
                    var frmDt=$("#request_from_date").val();
                    var toDt=$("#request_to_date").val();
                    if(frmDt=="" || toDt==""){
                        $(".fc-datepicker3").datepicker("destroy");
                        alert("Please select Travel Date");
                    }else{
                        
                    }
                });
                
                $( ".fc-datepicker4" ).click(function() {
                    var frmDt=$("#request_from_date").val();
                    var toDt=$("#request_to_date").val();
                    if(frmDt=="" || toDt==""){
                        $(".fc-datepicker4").datepicker("destroy");
                        alert("Please select Travel Date");
                    }else{
                        firstDate = new Date(frmDt);
                        secondDate = new Date(toDt);
                        var currentDate = new Date();
                        var stDate = new Date(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate());
                        var curDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
                        if(stDate-curDate === 0){
                            $('.fc-datepicker4').datepicker({
                                changeMonth:true,
                                changeYear:true,
                                dateFormat: 'dd-M-yy',
                                minDate:new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()),
                                maxDate:secondDate
                            });
                        }else if(stDate-curDate === 86400000){
                            $('.fc-datepicker4').datepicker({
                                changeMonth:true,
                                changeYear:true,
                                dateFormat: 'dd-M-yy',
                                minDate:new Date(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate()-1),
                                maxDate:secondDate
                            });
                        }else{
                            $('.fc-datepicker4').datepicker({
                                changeMonth:true,
                                changeYear:true,
                                dateFormat: 'dd-M-yy',
                                minDate:new Date(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate()),
                                maxDate:secondDate
                            });
                        }
                        
                    }
                });

            });
            function calculateDateDiff() {
                $(".fc-datepicker3").datepicker("destroy");
                $(".fc-datepicker4").datepicker("destroy");
                var date1 = Date.parse($('#request_from_date').val());
                var date2 = Date.parse($('#request_to_date').val());
                if (date2 != '' && date1 != '') {
                    firstDate = new Date(date1);
                    secondDate = new Date(date2);
                    var oneDay = 1000 * 60 * 60 * 24;
                    var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime()) / (oneDay)));
                    if (diffDays > 30)
                        $('#travel_term').val("Long Term");
                    else
                        $('#travel_term').val("Short Term");
                    
                    $('.fc-datepicker3').datepicker({
                        changeMonth:true,
                        changeYear:true,
                        dateFormat: 'dd-M-yy',
                        minDate:firstDate,
                        maxDate:secondDate
                    });
                    var currentDate = new Date();
                    var stDate = new Date(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate());
                    var curDate = new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate());
                    if(stDate-curDate === 0){
                        $('.fc-datepicker4').datepicker({
                            changeMonth:true,
                            changeYear:true,
                            dateFormat: 'dd-M-yy',
                            minDate:new Date(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()),
                            maxDate:secondDate
                        });
                    }else if(stDate-curDate === 86400000){
                        $('.fc-datepicker4').datepicker({
                            changeMonth:true,
                            changeYear:true,
                            dateFormat: 'dd-M-yy',
                            minDate:new Date(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate()-1),
                            maxDate:secondDate
                        });
                    }else{
                        $('.fc-datepicker4').datepicker({
                            changeMonth:true,
                            changeYear:true,
                            dateFormat: 'dd-M-yy',
                            minDate:new Date(firstDate.getFullYear(), firstDate.getMonth(), firstDate.getDate()-2),
                            maxDate:secondDate
                        });
                    }
                    $("#travel_date_1").val($('#request_from_date').val());
                    $("#from_date_1").val($('#request_from_date').val());
                    
                    $("#travel_date_2").val($('#request_to_date').val());
                    $("#to_date_1").val($('#request_to_date').val());
                    $('#advance_date').val('');
                }
            }
            function validate_file_name(){
                var file_name = $("#attach_doc_1").val().replace("C:\\fakepath\\", "");
                var regex = /[!@#$%^&*(),?":{}|<>]/;
                if(regex.test(file_name)){
                    $("#file_type_error").css({"display": "block"});
                }else{
                    $("#file_type_error").css({"display": "none"});
                }
            }
            function showCustomersOthers(value) {
                if (value == -1) {
                    $('#othersRow').show();
                    $("#customerOthersLabel").css('visibility', 'visible');
                    $("#customerOthers").css('visibility', 'visible');
                } else {
                    $("#customerOthersLabel").css('visibility', 'hidden');
                    $("#customerOthers").css('visibility', 'hidden');
                    if ($('#project_id').val() != -1) {
                        $('#othersRow').hide();
                    }
                }
            }

            function showProjectOthers(value) {
                if (value == -1) {
                    $('#othersRow').show();
                    $("#projectOthersLabel").css('visibility', 'visible');
                    $("#projectOthers").css('visibility', 'visible');
                } else {
                    $("#projectOthersLabel").css('visibility', 'hidden');
                    $("#projectOthers").css('visibility', 'hidden');
                    if ($('#customer_id').val() != -1) {
                        $('#othersRow').hide();
                    }
                }
            }
            function setApproverId(val){
                var option = $('option:selected', val).attr('rm_id');
                $("#rm_id").val(option);
                $("#rm_id").attr("readonly", true);
            }
            
            function settlementPolicy(val){
                if(val == "N"){
                    $("#settlement_type").val("h");
                    var selectobject=document.getElementById("settlement_type")
                    for (var i=0; i<selectobject.length; i++){
                    if (selectobject.options[i].value == 'c' )
                       selectobject.remove(i);
                    }
                }else{
                    $("#settlement_type").val("");
                    var selectobject=document.getElementById("settlement_type")
                    if(selectobject.length==2){
                        selectobject.options[selectobject.options.length] = new Option('Customer Policy', 'c');
                    }
                }
            }
            
            function GuestBooking(val) {
                if (val == "Y") {
                    $('#guestLabel').show();
                    $('#guestOutput').show();
                } else {
                    $('#guestLabel').hide();
                    $('#guestOutput').hide();
                }
            }
            function changeTab(tabValue, tab) {
                $(".ticket_tab").removeClass("active_tab");
                $(tab).addClass("active_tab");
                if (tabValue == "Ticket") {
                    $('#TicketTab').show();
                    $('#AttachmentTab').hide();
                    $('#HotelTab').hide();
                    $('#ConveyanceTab').hide();
                    $('#VisaTab').hide();
                } else if (tabValue == "Hotel") {
                    $('#HotelTab').show();
                    $('#AttachmentTab').hide();
                    $('#TicketTab').hide();
                    $('#ConveyanceTab').hide();
                    $('#VisaTab').hide();
                } else if (tabValue == "Conveyance") {
                    $('#ConveyanceTab').show();
                    $('#AttachmentTab').hide();
                    $('#TicketTab').hide();
                    $('#HotelTab').hide();
                    $('#VisaTab').hide();
                } else if (tabValue == "Attachment") {
                    $('#AttachmentTab').show();
                    $('#HotelTab').hide();
                    $('#TicketTab').hide();
                    $('#ConveyanceTab').hide();
                    $('#VisaTab').hide();
                } else {
                    $('#VisaTab').show();
                    $('#AttachmentTab').hide();
                    $('#HotelTab').hide();
                    $('#TicketTab').hide();
                    $('#ConveyanceTab').hide();
                }
            }
            function addTicket(rowObject, id) {
                var current_row = $("#ticketCount").val();
                var cnt = parseInt($("#ticketCount").val())+1;
                $("#ticketCount").val(cnt);
                var tr = '';
                tr += '<tr id="tr_ticket_'+cnt+'">';
                tr += '<td><input type="hidden" name="ticket_id" value="0"><select name="ticket_booking_type" class="booking_type" id="travel_booking_'+cnt+'" style="width:120px;">';
                tr += '<option value="">-- Booking --</option>';
                tr += '<c:forEach items="${bookingType}" var="booktype" ><option value="${booktype.config_key}">${booktype.config_value}</option></c:forEach>';
                tr += '</select>';
                tr += '<span id="travel_preference_error_'+cnt+'" class="error" ></span>';
                tr += '</td>';
                tr += '<td> <input type="hidden" name="from_city_id" id="from_city_id_'+cnt+'" value="" class="hiddenbox" />';
                tr += '<input type="text" name="from_city" id="from_city_'+cnt+'" class="inputbox autobox from_place"  value="" style="width:140px;">';
                tr += '<span id="from_city_error_'+cnt+'" class="error" ></span>';
                tr += '</td>';
                tr += '<td> <input type="hidden" name="to_city_id" id="to_city_id_'+cnt+'" value="" class="hiddenbox" />';
                tr += '<input type="text" name="to_city" id="to_city_'+cnt+'" class="inputbox autobox to_place"  value="" style="width:140px;">';
                tr += '<span id="to_city_error_'+cnt+'" class="error" ></span>';
                tr += '</td>';
                tr += '<td><input type="text" name="travel_date" class="inputbox fc-datepicker3 ticket_date"  id="travel_date_'+cnt+'" value="" style="width:140px;">';
                tr += '<span id="travel_date_error_'+cnt+'" class="error" ></span>';
                tr += '</td>';
                tr += '<td>';
                tr += '<select name="travel_preference" id="travel_preference_'+cnt+'" class="travel_preference" style="width:100px;">';
                tr += '<option value="">-- Preference --</option>';
                tr += '<option value="Morning">Morning</option>';
                tr += '<option value="Afternoon">Afternoon</option>';
                tr += '<option value="Evening">Evening</option>';
                tr += '<option value="Night">Night</option>';
                tr += '</select>';
                tr += '<span id="travel_preference_error_'+cnt+'" class="error" ></span>';
                tr += '</td>';
                tr += '<td>';
                tr += '<select name="travel_mode" id="travel_mode_'+cnt+'" class="travel_mode" style="width:100px;">';
                tr += '<option value="">-- Mode --</option>';
                tr += '<option value="Air">Air</option>';
                tr += '<option value="Bus">Road</option>';
                tr += '<option value="Train">Rail</option>';
                tr += '</select>';
                tr += '<span id="travel_mode_error_'+cnt+'" class="error" ></span>';
                tr += '</td>';
                tr += '<td>';
                tr += '<input name="ticket_remarks" class="textareaTravel" id="ticket_remarks_'+cnt+'" style="width:170px;"/>';
                tr += '<input type="hidden" name="ticket_deleted" value="0"/></td>';
                tr += '<td id="ticket_actionItems_'+cnt+'" style="text-align: center;">';
                tr += '<img onClick="addTicket(this,'+cnt+')" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                tr += '<img onClick="removeTicket('+cnt+',1)" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">';
                tr += '</td>';
                tr += '</tr>'; 
                $('#tr_ticket_'+id).after(tr);
                myDatePicker();
                myAutoComplet();
            }
            
            function removeTicket(rowObject, id){
                var current_row = rowObject;
//                var cnt = parseInt($("#ticketCount").val())-1;
//                $("#ticketCount").val(cnt);
                $('#tr_ticket_'+current_row).remove();
            }
            
            function addHotel(current_row, id){
                var cnt = parseInt($("#hotelCount").val())+1;
                $("#hotelCount").val(cnt);
                var tr ='';
                tr += '<tr id="tr_hotel_'+cnt+'">';
                tr += '<td> <input type="hidden" name="hotel_id" value="0"/>';
                tr += '<select name="hotel_booking_type" class="hotel_booking_type" id="travel_preference_'+cnt+'" style="width:100px;">';
                tr += '<option value="">-- Booking --</option><c:forEach items="${bookingType}" var="booktype" ><option value="${booktype.config_key}">${booktype.config_value}</option></c:forEach>';
                tr += '</select> <span id="travel_preference_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td> <input type="hidden" name="city_id" id="city_id_'+cnt+'" value="" class="hiddenbox" />';
                tr += '<input type="text" name="city" id="city_'+cnt+'" class="inputbox autobox hotel_city"> <span id="city_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td> <input type="text" name="location" id="location_'+cnt+'" class="inputbox hotel_location">';
                tr += '<span id="location_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td> <input type="text" name="from_date" class="inputbox fc-datepicker3 hotel_from_date"  id="from_date_'+cnt+'" value="">';
                tr += '<span id="from_date_error_'+cnt+'" class="error" ></span></td>';
                tr += '<td><input type="text" name="to_date" class="inputbox fc-datepicker3 hotel_to_date"  id="to_date_'+cnt+'" value="">';
                tr += '<span id="to_date_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td> <input name="hotel_remarks" class="textareaTravel"  id="hotel_remarks_'+cnt+'"/>';
                tr += '<input type="hidden" name="hotel_deleted" value="0"/> <span id="hotel_remarks_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td align="center" id="hotel_actionItems_'+cnt+'">';
                tr += '<img onClick="addHotel(this,'+cnt+')" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                tr += '<img onClick="removeHotel('+cnt+',1)" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">';
                tr += '</td> </tr>';
                $('#tr_hotel_'+id).after(tr);
                myDatePicker();
                myAutoComplet();
            }
            function removeHotel(rowObject, id){
                var current_row = rowObject;
//                var cnt = parseInt($("#ticketCount").val())-1;
//                $("#ticketCount").val(cnt);
                $('#tr_hotel_'+current_row).remove();
            }
            
            function addConveyance(rowObject, id){
                var cnt = parseInt($("#conveyanceCount").val())+1;
                var cab_cnt = parseInt($("#cab_id").val())+1;
                $("#cab_id").val(cab_cnt);
                $("#conveyanceCount").val(cnt);
                var tr ='';
                tr += '<tr id="tr_conveyance_'+cnt+'">';
                tr += '<td> <input type="hidden" name="conveyance_id" value="0"/> <input type="hidden" name="conveyance_city_id" id="conveyance_city_id_'+cnt+'" value="" class="hiddenbox" />';
                tr += '<input type="text" name="conveyance_city" id="conveyance_city_'+cnt+'" class="inputbox autobox cab_city">';
                tr += '<span id="conveyance_city_error_'+cnt+'" class="error" ></span></td>';
                tr += '<td> <input name="pickup" class="textareaTravel cab_pickup" id="pickup_'+cnt+'" />';
                tr += '<span id="pickup_error_'+cnt+'" class="error" ></span></td>';
                tr += '<td> <input name="dropto" class="textareaTravel cab_drop" id="dropto_'+cnt+'" />';
                tr += '<span id="dropto_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td> <input type="text" name="start_date" class="inputbox fc-datepicker3 cab_date"  id="start_date_'+cnt+'" value="">';
                tr += '<span id="start_date_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td> <input type="text" size="1" maxlength="2" class="inputbox_small cab_time_hrs" name="time_hr" id="time_hr_'+cnt+'" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,0,false);">';
                tr += ' &nbsp;:&nbsp; <input type="text" size="1" maxlength="2" class="inputbox_small cab_time_min" name="time_min" id="time_min_'+cnt+'" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,0,false);">';
                tr += '<span id="travel_time_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td><input type="hidden" name="conveyancedeleted" value="0"/><input name="conveyance_remarks" class="textareaTravel" id="conveyance_remarks_'+cnt+'" />';
                tr += '<span id="conveyance_remarks_error_'+cnt+'" class="error" ></span> </td>';
                tr += '<td align="center" id="conveyance_actionItems_'+cnt+'">';
                tr += '<img onClick="addConveyance(this,'+cnt+')" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                tr += '<img onClick="removeConveyance('+cnt+',1)" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Add" title="Add" style="cursor:pointer;">';
                tr += '</td> </tr>';
                $('#tr_conveyance_'+id).after(tr);
                myDatePicker();
                myAutoComplet();
            }
            function removeConveyance(rowObject,id){
                var current_row = rowObject;
                var cab_cnt = parseInt($("#cab_id").val())-1;
                $("#cab_id").val(cab_cnt);
//                var cnt = parseInt($("#ticketCount").val())-1;
//                $("#ticketCount").val(cnt);
                $('#tr_conveyance_'+current_row).remove();
            }
            function addAdvance(rowObject,id){
                var cnt = parseInt($("#advanceCount").val())+1;
                $("#advanceCount").val(cnt);
                var tr ='';
                tr += '<tr id="tr_advance_'+cnt+'">';
                tr += '<td><input type="text" name="advance_date" class="fc-datepicker3" id="advance_date_'+cnt+'"/></td>';
                tr += '<td><input type="text" name="advance_amount" class="advance_amount" id="advance_amount_'+cnt+'" maxlength="6" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,0,false);"/></td>';
                tr += '<td style="text-align:center;"><label style="margin: 0px;">${employee_details.currency_name}</label></td>';
                tr += '<td><input type="text" name="advance_remarks" class="advance_remarks" id="advance_remarks_'+cnt+'"/></td>';
                tr += '<td><img onClick="addAdvance(this,'+cnt+')" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                tr += '<img onClick="removeAdvance('+cnt+',1)" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Add" title="Add" style="cursor:pointer;">';
                tr += '</td></tr>';
                $('#tr_advance_'+id).after(tr);
                myDatePicker();
            }
            function removeAdvance(rowObject,id){
                var current_row = rowObject;
//                var cnt = parseInt($("#ticketCount").val())-1;
//                $("#ticketCount").val(cnt);
                $('#tr_advance_'+current_row).remove();
            }
            function validateTravelSubmit(){
                return true;
            }
            function setFromToCity(value,id){
                $("#to_city_"+cnt).val("");
                var cnt = parseInt(id)+1;
                var city_id = $("#from_city_id_"+id).val();
                $("#to_city_"+cnt).val(value);
                $("#to_city_id_"+cnt).val(city_id);
            }
            
            function setToFromCity(value,id){
                $("#from_city_"+cnt).val("");
                var cnt = parseInt(id)+1;
                var city_id = $("#to_city_id_"+id).val();
                $("#from_city_"+cnt).val(value);
                $("#from_city_id_"+cnt).val(city_id);
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
                        <!--<li class="breadcrumb-item"><a class="showList" style="text-decoration:none;color: #4C83B3;" target="_blank" href="http://ideal.hindujatech.com/uploads/document_uploads/policy_document/Domestic Travel Policy_20140730151152_594094.pdf">View Domestic Travel Policy</a></li>-->
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/getList.htm">List Request</a></li>
                    </ol>
                    <h6 class="slim-pagetitle">Domestic Travel Request</h6>
                </div>
                <form action="submitDomestic.htm" name="saveDetails" id="saveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
                    <div class="section-wrapper">
                        <input type="hidden" id="status" name="status" value="0"/>
                        <label class="section-title">Employee Details</label>
                        <table class="col-sm">
                            <tr>
                                <td><label>Employee Id </label></td>
                                <td>
                                    <input type="text" name="employee_number" value="${employee_details.employee_number}" readonly/>
                                    <input type="hidden" name="employee_id" value="${employee_details.employee_id}"/>
                                    <input type="hidden" value="${employee_details.currency_id}" name ="currency_id"/>
                                    <input type="hidden" value="${employee_details.location_id}" name ="location_id"/>
                                </td>
                                <td><label>Employee Name </label></td>
                                <td>
                                    <input type="text" name="employee_name" value="${employee_details.employee_name}" readonly/>
                                </td>
                                <td><label>Band </label></td>
                                <td>
                                    <input type="text" name="band_name" value="${employee_details.band_name}" readonly/>
                                    <input type="hidden" name="band_id" id="band_id" value="${employee_details.band_id}" />
                                </td>
                            </tr>
                            <tr>
                                <td><label>SBU </label></td>
                                <td>
                                    <input type="text" name="unit_name" value="${employee_details.unit_name}" readonly/>
                                    <input type="hidden" name="unit_id" value="${employee_details.unit_id}"/>
                                </td>
                                <td><label>Sub BU </label></td>
                                <td>
                                    <input type="text" name="sub_unit_name" value="${employee_details.sub_unit_name}" readonly/>
                                    <input type="hidden" name="sub_unit_id" value="${employee_details.sub_unit_id}"/>
                                </td>
                                <td><label>Designation </label></td>
                                <td>
                                    <input type="text" name="designation_name" value="${employee_details.designation_name}" readonly/>
                                    <input type="hidden" name="designation_id" value="${employee_details.designation_id}" />
                                </td>
                            </tr>
                            <tr>
                                <td><label>Location </label></td>
                                <td><input type="text" value="${employee_details.location_name}" readonly/></td>
                                <td><label>Contact Number </label><label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" name="contact_number" id="contact_number" value="${employee_details.contact_number}" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,0,false);"/>
                                    <br><span id="contact_number_error" class="error_message"> Please Enter Mobile Number</span>
                                </td>
                                <td><label>Request Date </label></td>
                                <td><input type="text" name="requested_date" value="${employee_details.requested_date}" readonly/></td>
                            </tr>
                        </table>
                        <label class="section-title">Travel Details</label>
                        <table class="col-sm">
                            <tr>
                                <td><label>Start Date </label><label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" class="fc-datepicker" id="request_from_date" name="travel_start_date" placeholder="DD-MMM-YYYY" onChange="calculateDateDiff();">
                                    <br><span id="request_from_date_error" class="error_message"> Please select the date</span>
                                </td>
                                <td><label>End Date </label><label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" class="fc-datepicker2" id="request_to_date" name="travel_end_date" placeholder="DD-MMM-YYYY" onChange="calculateDateDiff();">
                                    <br><span id="request_to_date_error" class="error_message"> Please select the date</span>
                                </td>
                                <td><label>Travel Term </label></td><td><input type="text" id="travel_term" name="travel_term" readonly/></td>
                            </tr>
                            <tr>
                                <td><label>Project Travel </label><label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <select class="drop_down" id="travel_billable" name="travel_billable">
                                        <option value="">--Select Billable--</option>
                                        <option value="Y">Yes</option>
                                        <option value="N">No</option>
                                    </select>
                                    <br><span id="travel_billable_error" class="error_message"> Please select the Travel type</span>
                                </td>
                                <td><label>Customer </label></td>
                                <td>
                                    <select class="drop_down" id="customer_id" name="customer_id" onchange="showCustomersOthers(this.value);">
                                        <option value="">--Select Customer--</option>
                                        <c:forEach items="${customer_list}" var="custList" >
                                            <option value="${custList.customer_id}">${custList.customer_name}</option>
                                        </c:forEach>
                                        <option value="-1">Others</option>
                                    </select>
                                    <br><span id="customer_id_error" class="error_message"> Please select the customer</span>
                                </td>
                                <td><label>Project </label></td>
                                <td>
                                    <input type="hidden" value="${employee_details.rm_id}" name="rm_id" id="rm_id"/>
                                    <select class="drop_down" id="project_id" name="project_id" onchange="showProjectOthers(this.value);setApproverId(this);">
                                        <option value="" rm_id="${employee_details.rm_id}">--Select Project--</option>
                                        <c:forEach items="${project_list}" var="prjList" >
                                            <option value="${prjList.project_id}" rm_id="${prjList.pm_id}">${prjList.project_name}</option>
                                        </c:forEach>
                                        <option value="-1" rm_id="${employee_details.rm_id}">Others</option>
                                    </select>
                                    <br><span id="project_id_error" class="error_message"> Please select the project</span>
                                </td>
                            </tr>
                            <tr id="othersRow" style="display:none;">
                                <td colspan="2"></td>
                                <td id="customerOthersLabel" style="visibility:hidden;"><label>Customer Others </label><label style="color: red;" for="fine">*</label></td>
                                <td id="customerOthers" style="visibility:hidden;">
                                    <input type="text" name="customer_others" id="customer_others"/>
                                    <br><span id="customer_others_error" class="error_message"> Please enter the customer others</span>
                                </td>
                                <td id="projectOthersLabel" style="visibility:hidden;"><label>Project Others </label><label style="color: red;" for="fine">*</label></td>
                                <td id="projectOthers" style="visibility:hidden;">
                                    <input type="text" name="project_others" id="project_others"/>
                                    <br><span id="project_others_error" class="error_message"> Please enter the project others</span>
                                </td>
                            </tr>
                            <tr>
                                <td><label>BUH Approver </label></td>
                                <td>
                                    <select class="drop_down" name="buh_id">
                                        <c:forEach items="${buh_list}" var="buhList" >
                                            <option value="${buhList.buh_id}" ${employee_details.buh_id == buhList.buh_id ?'selected':''}>${buhList.buh_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td><label>Client Reimbursable </label><label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <select class="drop_down" id="client_reimbursable" name="client_reimbursable" onchange="settlementPolicy(this.value);">
                                        <option value="">--Select Reimbursable--</option>
                                        <option value="N">No</option>
                                        <option value="Y">Yes</option>
                                    </select>
                                    <br><span id="client_reimbursable_error" class="error_message"> Please select the reimbursable type</span>
                                </td>
                                <td><label>Settlement Policy </label><label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <select name="settlemet_policy" id="settlement_type" class="drop_down">
                                        <option value="">--Settlement Policy--</option>
                                        <c:forEach items="${settlementPolicy}" var="setlist" >
                                            <option value="${setlist.config_key}">${setlist.config_value}</option>
                                        </c:forEach>
                                    </select>
                                    <br><span id="settlement_type_error" class="error_message"> Please select the reimbursable type</span>
                                </td>
                            </tr>
                            <tr>
                                <td><label>Purpose of Travel </label><label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <input type="text" name="travel_purpose" id="travel_purpose"/>
                                    <br><span id="travel_purpose_error" class="error_message"> Please select the reimbursable type</span>
                                </td>
                                <td><label>Guest Booking </label><label style="color: red;" for="fine">*</label></td>
                                <td>
                                    <select name="guest_booking" id="guest_booking" class="drop_down" onchange="GuestBooking(this.value)">
                                        <option value="N">No</option>
                                        <option value="Y">Yes</option>
                                    </select>
                                    <br><span id="guest_booking_error" class="error_message"> Please select the guest booking type</span>
                                </td>
                                <td id="guestLabel" style="display:none"><label>Guest Name<label style="color: red;" for="fine">*</label></label></td>
                                <td id="guestOutput" style="display:none">
                                    <input type="text" name="guest_booking_name" id="guest_booking_name"/>
                                    <br><span id="guest_booking_name_error" class="error_message"> Please enter the guest name</span>
                                </td>
                            </tr>
                        </table>
                        <label class="section-title">Advance Details</label>
                        <table class="tableStructure" id="ticket_table" width="70%">
                            <tr class="headerCenter">
                                <th width="15%">Required Date<label style="color: red;" for="fine">*</label></th>
                                <th width="15%">Advance Amount<label style="color: red;" for="fine">*</label></th>
                                <th width="15%">Currency</th>
                                <th width="15%">Remarks</th>
                            </tr>
                            <input type="hidden" value="1" id="advanceCount" name="advanceCount" />
                            <tr id="tr_advance_1">
                                <td>
                                    <input type="hidden" value="0" name="arr_advance_id"/>
                                    <input type="text" name="arr_advance_date" class="fc-datepicker4" id="advance_date"/>
                                    <br><span id="advance_date_error" class="error_message"> required</span>
                                </td>
                                <td>
                                    <input type="text" name="arr_advance_amount" class="advance_amount" id="advance_amount"  maxlength="6" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,0,false);"/>
                                    <br><span id="advance_amount_error" class="error_message"> required</span>
                                </td>
                                <td style="text-align:center;">
                                    <input type="hidden" value="${employee_details.currency_id}" name ="arr_currency_id"/>
                                    <label style="margin: 0px;">${employee_details.currency_name}</label>
                                </td>
                                <td>
                                    <input type="text" name="arr_advance_remarks" class="advance_remarks" id="advance_remarks"/>
                                    <input type="hidden" name="arr_advance_deleted" value="0"/>
                                </td>
                            </tr>
                            <tr style="text-align: center;">
                                <td colspan="4"><label>If advance not required please enter zero</label></td>
                            </tr>
                        </table>
                        
                        <label class="section-title">Booking Details</label>
                        <div class="contentHolderMax">
                            <div class="style_tab">
                                <div id="ticketOption">
                                    <span id="TicketSpan" onclick="changeTab('Ticket',this);" class="ticket_tab active_tab" > Ticket </span>
                                </div>
                                <div id="hotelOption">
                                    <span id="HotelSpan" onclick="changeTab('Hotel',this);" class="ticket_tab" > Hotel </span>
                                </div>
                                <c:if test="${employee_details.band_id == 2 || employee_details.band_id == 3 || employee_details.band_id == 15 || employee_details.band_id == 35 || employee_details.band_id == 36}" >
                                    <div id="conveyanceOption">
                                        <span id="ConveyanceSpan" onclick="changeTab('Conveyance',this);" class="ticket_tab" > Conveyance </span>
                                    </div>
                                </c:if>
                                <div id="fileOption" >
                                    <span id="AttachmentSpan" onclick="changeTab('Attachment',this);" class="ticket_tab" > Attachment </span>
                                </div>
                            </div>
                        </div>
                        <div id="TicketTab" class="tabContent" >
                            <table class="tableStructure" id="ticket_table">
                                <tr class="headerCenter">
                                    <th width="14%"><label>Booking Type</label><label style="color: red;" for="fine">*</label></th>
                                    <th width="14%"><label>From</label><label style="color: red;" for="fine">*</label></th>
                                    <th width="14%"><label>To</label><label style="color: red;" for="fine">*</label></th>
                                    <th width="14%"><label>Date of Travel</label><label style="color: red;" for="fine">*</label></th>
                                    <th width="10%"><label>Preference</label><label style="color: red;" for="fine">*</label></th>
                                    <th width="10%"><label>Mode</label><label style="color: red;" for="fine">*</label></th>
                                    <th width="20%"><label>Remarks</label></th>
                                    <th width="4%"><label>Action</label></th>
                                </tr>
                                <input type="hidden" value="2" id="ticketCount" />
                                <tr id="tr_ticket_1">
                                    <td>
                                        <input type="hidden" name="ticket_id" value="0">
                                        <select name="ticket_booking_type" class="booking_type" id="travel_booking_1" style="width:120px;">
                                            <option value="">-- Booking --</option>
                                            <c:forEach items="${bookingType}" var="booktype" >
                                                <option value="${booktype.config_key}">${booktype.config_value}</option>
                                            </c:forEach>
                                        </select>
                                        <span id="travel_preference_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="hidden" name="from_city_id" id="from_city_id_1" value="" class="hiddenbox" />
                                        <input type="text" name="from_city" id="from_city_1" class="inputbox autobox from_place" onfocusout="setFromToCity(this.value,1);" value="" style="width:140px;">
                                        <span id="from_city_error_1" class="error" ></span>
                                    </td>
                                    <td>

                                        <input type="hidden" name="to_city_id" id="to_city_id_1" value="" class="hiddenbox" />
                                        <input type="text" name="to_city" id="to_city_1" class="inputbox autobox to_place"  onfocusout="setToFromCity(this.value,1);" value="" style="width:140px;">
                                        <span id="to_city_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="text" name="travel_date" class="inputbox fc-datepicker3 ticket_date"  id="travel_date_1" value="" style="width:140px;">
                                        <span id="travel_date_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <select name="travel_preference" id="travel_preference_1" class="travel_preference" style="width:100px;">
                                            <option value="">-- Preference --</option>
                                            <option value="Morning">Morning</option>
                                            <option value="Afternoon">Afternoon</option>
                                            <option value="Evening">Evening</option>
                                            <option value="Night">Night</option>
                                        </select>
                                        <span id="travel_preference_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <select name="travel_mode" id="travel_mode_1" class="travel_mode" style="width:100px;">
                                            <option value="">-- Mode --</option>
                                            <option value="Air">Air</option>
                                            <option value="Bus">Road</option>
                                            <option value="Train">Rail</option>
                                        </select>
                                        <span id="travel_mode_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input name="ticket_remarks" class="textareaTravel" id="ticket_remarks_1" style="width:170px;"/>
                                        <input type="hidden" name="ticket_deleted" value="0"/>
                                    </td>
                                    <td id="ticket_actionItems_1" style="text-align: center;">
                                        <img onClick="addTicket(this,1)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                    </td>
                                </tr>
                                <tr id="tr_ticket_2">
                                    <td>
                                        <input type="hidden" name="ticket_id" value="0">
                                        <select name="ticket_booking_type" class="booking_type" id="travel_booking_2" style="width:120px;">
                                            <option value="">-- Booking --</option>
                                            <c:forEach items="${bookingType}" var="booktype" >
                                                <option value="${booktype.config_key}">${booktype.config_value}</option>
                                            </c:forEach>
                                        </select>
                                        <span id="travel_preference_error_2" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="hidden" name="from_city_id" id="from_city_id_2" value="" class="hiddenbox" />
                                        <input type="text" name="from_city" id="from_city_2" class="inputbox autobox from_place"  value="" style="width:140px;">
                                        <span id="from_city_error_2" class="error" ></span>
                                    </td>
                                    <td>

                                        <input type="hidden" name="to_city_id" id="to_city_id_2" value="" class="hiddenbox" />
                                        <input type="text" name="to_city" id="to_city_2" class="inputbox autobox to_place"  value="" style="width:140px;">
                                        <span id="to_city_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="text" name="travel_date" class="inputbox fc-datepicker3 ticket_date"  id="travel_date_2" value="" style="width:140px;">
                                        <span id="travel_date_error_2" class="error" ></span>
                                    </td>
                                    <td>
                                        <select name="travel_preference" id="travel_preference_2" class="travel_preference" style="width:100px;">
                                            <option value="">-- Preference --</option>
                                            <option value="Morning">Morning</option>
                                            <option value="Afternoon">Afternoon</option>
                                            <option value="Evening">Evening</option>
                                            <option value="Night">Night</option>
                                        </select>
                                        <span id="travel_preference_error_2" class="error" ></span>
                                    </td>
                                    <td>
                                        <select name="travel_mode" id="travel_mode_2" class="travel_mode" style="width:100px;">
                                            <option value="">-- Mode --</option>
                                            <option value="Air">Air</option>
                                            <option value="Bus">Road</option>
                                            <option value="Train">Rail</option>
                                        </select>
                                        <span id="travel_mode_error_2" class="error" ></span>
                                    </td>
                                    <td>
                                        <input name="ticket_remarks" class="textareaTravel" id="ticket_remarks_2" style="width:170px;"/>
                                        <input type="hidden" name="ticket_deleted" value="0"/>
                                    </td>
                                    <td id="ticket_actionItems_2" style="text-align: center;">
                                        <img onClick="addTicket(this,2)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                        <img onClick="removeTicket(2,1)" src="${pageContext.request.contextPath}/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="HotelTab" class="tabContent" style="display: none;">
                            <table class="tableStructure" id="hotel_tabel">
                                <tr class="headerCenter">
                                    <th width="15%"><label>Booking Type</label><label style="color: red;" for="fine">*</label></th>
                                    <th width="15%">City/Town<label style="color: red;" for="fine">*</label></th>
                                    <th width="15%">Location Preference<label style="color: red;" for="fine">*</label></th>
                                    <th width="15%">From Date<label style="color: red;" for="fine">*</label></th>
                                    <th width="15%">To Date<label style="color: red;" for="fine">*</label></th>
                                    <th width="20%">Remarks</th>
                                    <th width="4%">Action</th>
                                </tr>
                                <input type="hidden" id="hotelCount" name="hotelCount" value="1"/>
                                <tr id="tr_hotel_1">
                                    <td>
                                        <input type="hidden" name="hotel_id" value="0"/>
                                        <select name="hotel_booking_type" class="hotel_booking_type" id="travel_preference_1" style="width:100px;">
                                            <option value="">-- Booking --</option>
                                            <c:forEach items="${bookingType}" var="booktype" >
                                                <option value="${booktype.config_key}">${booktype.config_value}</option>
                                            </c:forEach>
                                        </select>
                                        <span id="travel_preference_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="hidden" name="city_id" id="city_id_1" value="" class="hiddenbox" />
                                        <input type="text" name="city" id="city_1" class="inputbox autobox hotel_city">
                                        <span id="city_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="text" name="location" id="location_1" class="inputbox hotel_location">
                                        <span id="location_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="text" name="from_date" class="inputbox fc-datepicker3 hotel_from_date"  id="from_date_1" value="">
                                        <span id="from_date_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="text" name="to_date" class="inputbox fc-datepicker3 hotel_to_date"  id="to_date_1" value="">
                                        <span id="to_date_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input name="hotel_remarks" class="textareaTravel"  id="hotel_remarks_1"/>
                                        <span id="hotel_remarks_error_1" class="error" ></span>
                                        <input type="hidden" name="hotel_deleted" value="0"/>
                                    </td>
                                    <td align="center" id="hotel_actionItems_1">
                                        <img onClick="addHotel(this,1)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="ConveyanceTab" class="tabContent" style="display: none;" >
                            <table class="tableStructure">
                                <tr class="headerCenter">
                                    <th width="12%">City/Town<label style="color: red;" for="fine">*</label></th>
                                    <th width="16%">Pickup Address<label style="color: red;" for="fine">*</label></th>
                                    <th width="16%">Drop Address<label style="color: red;" for="fine">*</label></th>
                                    <th width="12%">Date<label style="color: red;" for="fine">*</label></th>
                                    <th width="12%">Time <label style="color: red;" for="fine">*</label> (24 hrs Format hh:mm)</th>
                                    <th width="16%">Remarks</th>
                                    <th width="4%">Action</th>
                                </tr>
                                <input type="hidden" value="1" id="conveyanceCount" name="conveyanceCount" />
                                <input type="hidden" value="0" id="cab_id" name="cab_id" />
                                <tr id="tr_conveyance_1">
                                    <td>
                                        <input type="hidden" name="conveyance_id" value="0"/>
                                        <input type="hidden" name="conveyance_city_id" id="conveyance_city_id_1" value="" class="hiddenbox" />
                                        <input type="text" name="conveyance_city" id="conveyance_city_1" class="inputbox autobox cab_city">
                                        <span id="conveyance_city_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input name="pickup" class="textareaTravel cab_pickup" id="pickup_1" />
                                        <span id="pickup_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input name="dropto" class="textareaTravel cab_drop" id="dropto_1" />
                                        <span id="dropto_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="text" name="start_date" class="inputbox fc-datepicker3 cab_date"  id="start_date_1" value="">
                                        <span id="start_date_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="text" size="1" maxlength="2" min="0" max="24" class="inputbox_small cab_time_hrs" name="time_hr" id="time_hr_1" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,0,false);">
                                        &nbsp;:&nbsp;
                                        <input type="text" size="1" maxlength="2" min="0" max="59" class="inputbox_small cab_time_min" name="time_min" id="time_min_1" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,0,false);">
                                        <span id="travel_time_error_1" class="error" ></span>
                                    </td>
                                    <td>
                                        <input type="hidden" name="conveyancedeleted" value="0"/>
                                        <input name="conveyance_remarks" class="textareaTravel"  id="conveyance_remarks_1" />
                                        <span id="conveyance_remarks_error_1" class="error" ></span>
                                    </td>
                                    <td align="center" id="conveyance_actionItems_1">
                                        <img onClick="addConveyance(this,1)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div id="AttachmentTab" class="tabContent" style="display: none;" >
                            <input type="hidden" value="1" id="attachmentCount" name="attachmentCount" />
                            <table class="tableStructure" border="0" width="50%">
                                <tr class="headerCenter">
                                    <th>File Name / File Upload<label style="color: red;" for="fine">*</label></th>
                                    <!--<th>Action</th>-->
                                </tr>
                                <tr id="tr_attachment_1">
                                    <input type="hidden" value="0" name="attachmentdeleted_1" id="attachmentdeleted_1" />
                                    <td align="center">
                                        <input type="file" name="attach_doc_1" id="attach_doc_1" class="filebox" size="20" onchange="validate_file_name();" >
                                    </td>
<!--                                    <td align="center" id="attachment_actionItems_1">
                                        <img onClick="addAttachment(this,1)" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                    </td>-->
                                </tr>
                            </table>
                            <span id="file_type_error" class="error_message">Attached file name should not contain special characters. Please rename the file and attach again</span>
                        </div>
                        <div class="buttonAlignment" id="submitDiv">
                            <div class="submit buttonAlignment" align="center" id="btnGroup">
                                <input name="travelbtn" id="save_btn" type="buttont" readonly value="Save" class="buttons savebutton">
                                <input name="travelbtn" id="submit_btn" type="buttton" readonly value="Submit" class="buttons submitbutton">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
