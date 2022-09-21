<%-- 
    Document   : travelSettlementDashBoard
    Created on : Jun 4, 2018, 7:16:25 PM
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
        <link type="text/css" href="${pageContext.request.contextPath}/css/jquery.simple-dtpicker.css" rel="stylesheet" />
        <link href="${pageContext.request.contextPath}/css/jquery.timepicker.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/jquery.datepick.css" rel="stylesheet">
        <!-- Script from theme -->

        <script src="${pageContext.request.contextPath}/lib/jquery/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/lib/popper.js/js/popper.js"></script>
        <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/lib/jquery.cookie/js/jquery.cookie.js"></script>
        <script src="${pageContext.request.contextPath}/lib/datatables/js/jquery.dataTables.js"></script>
        <script src="${pageContext.request.contextPath}/lib/datatables-responsive/js/dataTables.responsive.js"></script>
        <script src="${pageContext.request.contextPath}/lib/select2/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slim.js"></script>
        <script src="${pageContext.request.contextPath}/js/moment.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.17.custom.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.simple-dtpicker.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" rel="stylesheet" />

        <script src="${pageContext.request.contextPath}/js/jquery.timepicker.js"></script>
        <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>

        <style>          

            body { background-color: #f0f2f7; color: #101010; }
            footer{ font-size:small;position:fixed;right:5px;bottom:5px; }
            a:link, a:visited  { color: #1b84e7; }
            pre{ background-color: #eeeeee; margin-left: 1%; margin-right: 2%; padding: 2% 2% 2% 5%; }
            p { font-size: 0.9rem; }
            ul { font-size: 0.9rem; }
            hr { border: 2px solid #eeeeee; margin: 2% 0% 2% -3%; }
            h3 { border-bottom: 2px solid #eeeeee; margin: 2rem 0 2rem -1%; padding-left: 1%; padding-bottom: 0.1em; }
            h4 { border-bottom: 1px solid #eeeeee; margin-top: 2rem; margin-left: -1%; padding-left: 1%; padding-bottom: 0.1em; }
            .table th, .table td{
                padding:0.15rem;
            }
            .col-sm tr td{
                width:16.66%;
                padding: 3px;
            }
            .section-tables{
                padding: 10px 30px 10px 30px !important;                
                background-color: #fff;
                font-family: "Roboto", "Helvetica Neue", Arial, sans-serif;
            }
            .bg-info{
                text-align: center;
            }
            .section-title {
                margin-top: 20px !important;
            }
            .fc-datepicker,.fc-datepicker2,.fc-datepicker3{
                background: url(./css/images/calender_icon.png) no-repeat 5px 3px;
                background-color: #FFF;
                border-style: groove;
                padding-left: 30px;
                width:163px;
            }
            .wd-125-force{
                width: 150px !important;
            }
            /*            #datatable1{
                            border-collapse: inherit;
                        }*/
            #datatable2{
                width: 40% !important;
            }
            #datatable1 td{
                padding:0.25rem !important;
            }
            .readonlyclass{
                height: 28px !important;
                width: 160px !important;
            } 
            .section-title{
                color: #1b84e7 !important;
            }
            input.savebutton {
                background: url(./css/images/icon_btn_save.png) no-repeat scroll 8px 8px #3579c6 !important;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 50px 5px 0 0;
                padding: 0 10px 0 30px;
                border-radius: 10px;
                cursor: pointer;
                width: 80px;
            }
            input.submitbutton {
                background: url(./css/images/icon_btn_submit.png) no-repeat scroll 8px 8px #3579c6 !important;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 20px 5px 0 0;
                padding: 0 10px 0 30px;
                border-radius: 10px;
                cursor: pointer;
                width: 90px;
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
            .datepicker > .datepicker_header > .icon-close> div {
                margin-left: -10px;
            }
            td.ui-datepicker-unselectable.ui-state-disabled {
                opacity: 0.25;
            }
            .disableClass[ readonly ]{
                background-color: #FFFF !important;               
            }
            .disableClass[ disabled ]{
                background-color: #e9ecef !important;               
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
            .amount_alignment {
                text-align: right;
            }
            #travel_start_time,#travel_end_time{
                margin-left:5px;
                padding-left: 4px;
            }
            #startDate,#endDate{
                padding:5px;
            }
            .ui-datepicker-header{
                display:block;
            }
            .ui-datepicker .ui-datepicker-header{
                display:block;
            }
            .ui-datepicker select.ui-datepicker-month, .ui-datepicker select.ui-datepicker-year{
                width:27%;
            }
            .col-md-10{
                max-width: 100%;
            }
            .col-md-6,.col-md-10{
                padding-left: 0px;
            }
            .addRemove,.rowRemove{
                cursor: pointer;
            }
        </style>
        <script>
            var dateArr = {};
            function calculateDateDiff() {
                var firstDate;
                var secondDate;
                var date1 = $('#startDate').val();
                var date2 = $('#endDate').val(); 
                var t1 = $('#travel_start_time').val();
                var t2 = $('#travel_end_time').val();
                firstDate = new Date(date1+' '+t1);
                secondDate = new Date(date2+' '+t2);
                if (date2 != '' && date1 != '' && t1 && t2) {
                    var oneDay = 1000 * 60 * 60 * 24;
                    var timediff = (secondDate.getTime() - firstDate.getTime());
                    var diffDays = Math.round(Math.abs( timediff / (oneDay)));
                    var seconds = timediff/1000;
                    var days = Math.floor(seconds / (3600*24));
                    seconds  -= days*3600*24;
                    var hrs   = Math.floor(seconds / 3600);
                    seconds  -= hrs*3600;
                    var mnts = Math.floor(seconds / 60);
                    seconds  -= mnts*60;
                    $('#travelPeriod').val(days + " Day " + hrs + " Hrs " + mnts + " Mins");
                    $('#travel_period').html(days + " Day " + hrs + " Hrs " + mnts + " Mins");
                }
                if(date1 == null || date1 == ''){
                    $(".fromdateClass").datepicker("destroy");
                    $(".todateClass").datepicker("destroy");
                }else{
                    $(".fromdateClass").datepicker("destroy");
                    $(".todateClass").datepicker("destroy");
                    $(".fromdateClass").datepicker({minDate: new Date(date1),dateFormat:"dd-M-yy",maxDate:new Date(date2),
                        onSelect: function(date) {
                            var key = $(this).attr("id").split('_')[1];
                            $("#toDate_"+key).datepicker('destroy');
                            $("#toDate_"+key).val('');
                            var frd = $('#fromDate_'+key).val();
                            $("#toDate_"+key).datepicker({minDate: new Date(frd),dateFormat:"dd-M-yy",maxDate:new Date(date2),onSelect: function(date) {
                                    var key = $(this).attr("id").split('_')[1];
                                    getEliAmount(key);
                                } });
                            getEliAmount(key);
                        }
                    });
                    $(".todateClass").datepicker({minDate: new Date(date1),dateFormat:"dd-M-yy",maxDate:new Date(date2),
                        onSelect: function(date) {
                            var key = $(this).attr("id").split('_')[1];
                            getEliAmount(key);
                        }
                    });
                }
                var strd = moment(new Date(date1) , "DD-MMM-YYYY");
                var endd = moment(new Date(date2) , "DD-MMM-YYYY");
                var diffDays = endd.diff(strd, 'days');
                for(var i=0;i<= diffDays ; i++){
                    fd = moment(new Date(date1) , "DD-MMM-YYYY").format('DD-MMM-YYYY');
                    dateArr[fd] = {'250':0,'251':0,'252':0,'253':0,'254':0};
                    date1  = moment(new Date(fd)).add(1,'days').format("DD-MMM-YYYY");
                }
            }
            $(document).ready(function () {
                $('[data-toggle="tooltip"]').tooltip();
                $('#travel_start_time').timepicker({
                    onClose: function(){
                        if($('#startDate').val() && $('#endDate').val() && $('#travel_start_time').val() ){
                            if($('#startDate').val() == $('#endDate').val()){
                                $('#travel_end_time').timepicker('destroy');
                                $('#travel_end_time').timepicker({
                                    minTime: {
                                        hour: Number($(this).val().split(":")[0]),
                                        minute: Number($(this).val().split(":")[1])
                                    }                          
                                })
                            }else{
                                $('#travel_end_time').timepicker();
                            }
                        }                       
                    }
                });
                 
                $('#travel_end_time').on('click', function() {
                    var frt = $('#travel_start_time').val();
                    if(frt == null || frt == '' ){
                        $('#travel_end_time').timepicker('destroy');
                    }
                })
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
                timeBound = function(){
                    var strdate = $('#startdt').val();
                    var endate = $('#enddt').val();
                    if(strdate == null || strdate == ''){
                        $(".fromdateClass").datepicker("destroy");
                        $(".todateClass").datepicker("destroy");
                    }else{
                        $(".fromdateClass").datepicker("destroy");
                        $(".todateClass").datepicker("destroy");
                        var spl2 = strdate.split(" ");
                        var spl3 = endate.split(" ");
                        $(".fromdateClass").datepicker({minDate: new Date(spl2[0]),dateFormat:"dd-M-yy",maxDate:new Date(spl3[0]),
                            onSelect: function(date) {
                                var key = $(this).attr("id").split('_')[1];
                                $("#toDate_"+key).datepicker('destroy');
                                $("#toDate_"+key).val('');
                                var frd = $('#fromDate_'+key).val();
                                $("#toDate_"+key).datepicker({minDate: new Date(frd),dateFormat:"dd-M-yy",maxDate:new Date(spl3[0]),onSelect: function(date) {
                                        var key = $(this).attr("id").split('_')[1];
                                        getEliAmount(key);
                                    } });
                                getEliAmount(key);
                            }
                        });
                        $(".todateClass").datepicker({minDate: new Date(spl2[0]),dateFormat:"dd-M-yy",maxDate:new Date(spl3[0]),
                            onSelect: function(date) {
                                var key = $(this).attr("id").split('_')[1];
                                getEliAmount(key);
                            }
                        });
                    } 
                }
                timeBound();

                $(".billdateClass").datepicker({minDate : new Date('${travelDetails.requestedDate}') ,maxDate : new Date(), dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true });
                $("#startDate").datepicker({minDate : new Date('${travelDetails.requestedDate}') , maxDate : new Date(), dateFormat : 'yy-mm-dd',changeMonth : true,changeYear : true,
                    onSelect : function(){
                        $('#endDate').val('');
                        $('#travel_end_time').val('');
                        $('#travel_end_time').timepicker('destroy');
                        $('#endDate').datepicker('destroy');
                        $('#endDate').datepicker({dateFormat : 'yy-mm-dd',changeMonth : true,changeYear : true , minDate : $('#startDate').val(), maxDate: new Date(),
                            onSelect : function(){
                                if($('#startDate').val() && $('#endDate').val() && $('#travel_start_time').val()){
                                    $('#travel_end_time').timepicker('destroy');
                                    if($('#startDate').val() == $('#endDate').val()){                               
                                        $('#travel_end_time').timepicker({
                                            minTime: {
                                                hour: Number($('#travel_start_time').val().split(":")[0]),
                                                minute: Number($('#travel_start_time').val().split(":")[1])
                                            }                          
                                        })
                                    }else{
                                        $('#travel_end_time').timepicker();
                                    }
                                }     
                            }
                        });
                    }
                    
                });
                var strdate = $('#startDate').val();
                var endate = $('#endDate').val();
                if(strdate == null || strdate == ''){
                    $(".fromdateClass").datepicker("destroy");
                    $(".todateClass").datepicker("destroy");
                }else{
                    $(".fromdateClass").datepicker("destroy");
                    $(".todateClass").datepicker("destroy");
                    //                    var spl2 = strdate.split(" ");
                    //                    var spl3 = endate.split(" ");
                    $(".fromdateClass").datepicker({minDate: new Date(strdate),dateFormat:"dd-M-yy",maxDate:new Date(endate),
                        onSelect: function(date) {
                            var key = $(this).attr("id").split('_')[1];
                            $("#toDate_"+key).datepicker('destroy');
                            $("#toDate_"+key).val('');
                            var frd = $('#fromDate_'+key).val();
                            $("#toDate_"+key).datepicker({minDate: new Date(frd),dateFormat:"dd-M-yy",maxDate:new Date(endate),
                                onSelect: function(date) {
                                    var key = $(this).attr("id").split('_')[1];
                                    getEliAmount(key);
                                }
                            });
                            getEliAmount(key);
                        }    
                    });
                    $(".todateClass").datepicker({minDate: new Date(strdate),dateFormat:"dd-M-yy",maxDate:new Date(endate),
                        onSelect: function(date) {
                            var key = $(this).attr("id").split('_')[1];
                            getEliAmount(key);
                        }
                    });
                }
                travelAutoComplet();
                myAutoComplet();                 
                $('.billableAmountClass').live('focusout', function(){
                    var $row = $(this).closest("tr");
                    var $row_id = $row.attr('id');
                    var split = $row_id.split('_');
                    var calTx = 0;
                    var tot = 0;
                    var catval = $('#category_'+split[1]).val();
                    if(catval == '0' || catval == ''){
                        $('#category_error_'+split[1]).show();
                    }else {
                        $(".expenseTable tbody tr").each(function(){
                            var key = (this.id).split('_')[1];
                            var category = $('#category_'+key).val();
                            if(category == catval){
                                var tax = $('#tic_tax_'+key).val();
                                var tt = $('#tic_tot_'+key).val();
                                if(tax != null && tax != ''){
                                    calTx = Number(calTx) + Number(tax);
                                }else{
                                    calTx = Number(calTx) + Number(0);
                                }
                                if(tt != null && tt != ''){
                                    tot = Number(tot) + Number(tt);
                                }else{
                                    tot = Number(tot) + Number(0);
                                }
                            }
                        });
                        if(catval == 250){
                            $('#category_error_'+split[1]).hide();
                            var temp = caliculateTicket();
                            $('#ticTotal').val(temp);
                            if(temp != 0 && temp >0 ){
                                $('#tic_act').html(temp);
                            }else{
                                $('#tic_act').html('');
                            }
                            $('#ticTaxTotal').val(calTx);
                            $('#ticTaxTotalAmount').val(tot);
                            //                        if(calTx != null && calTx != '' ){
                            $('#tic_tax_total').html(calTx);
                            $('#tic_tax_total_amount').html(tot);
                            //                        }else{
                            //                            $('#tic_tax_total').html('');
                            //                            $('#tic_tax_total_amount').html('');
                            //                        }                        
                            ticketTotExpn();
                        } else if(catval == 251){
                            $('#category_error_'+split[1]).hide();
                            var temp = caliculateLodging();
                            $('#lodtotal').val(temp);
                            if(temp != 0 && temp >0 ){
                                $('#lod_act').html(temp);
                            }else{
                                $('#lod_act').html('');
                            }
                            var cal = calEligibility('lod_eli_cal');                    
                            if(cal != 0 && cal >0 ){
                                $('#lod_eli_tot').html(cal);
                                $('#lodEliTot').val(cal);
                            }else{
                                $('#lod_eli_tot').html('');
                                $('#lodEliTot').val(0);
                            }
                            $('#lodTaxTotal').val(calTx);
                            $('#lodTaxTotalAmount').val(tot);
                            //                        if(calTx != null && calTx != '' ){
                            $('#lod_tax_total').html(calTx);
                            $('#lod_tax_total_amount').html(tot);
                            //                        }else{
                            //                            $('#lod_tax_total').html('');
                            //                            $('#lod_tax_total_amount').html('');
                            //                        }
                            lodgingTotExpn();
                            lodDeviation();
                        } else if(catval == 252){
                            $('#category_error_'+split[1]).hide();
                            var temp = caliculateBoarding();                    
                            if(temp != 0 && temp >0 ){
                                $('#boar_act').html(temp);
                                $('#boartotal').val(temp);
                            }else{
                                $('#boar_act').html('');
                                $('#boartotal').val(0);
                            }                        
                            var cal = calEligibility('boar_eli_cal');
                            $('#boarEliTot').val(cal);
                            if(cal != 0 && cal >0 ){
                                $('#boar_eli_tot').html(cal);
                            }else{
                                $('#boar_eli_tot').html('');
                            }
                            $('#boarTaxTotal').val(calTx);
                            $('#boarTaxTotalAmount').val(tot);
                            //                        if(calTx != null && calTx != '' ){
                            $('#boar_tax_total').html(calTx);
                            $('#boar_tax_total_amount').html(tot);
                            //                        }else{
                            //                            $('#boar_tax_total').html('');
                            //                            $('#boar_tax_total_amount').html('');
                            //                        }
                            boardingTotExpn();
                            boarDeviation();
                         
                        } else if(catval == 253){
                            $('#category_error_'+split[1]).hide();
                            var temp = caliculateConveyance();
                            $('#convtotal').val(temp);
                            if(temp != 0 && temp >0 ){
                                $('#conv_act').html(temp);
                            }else{
                                $('#conv_act').html('');
                            }
                            $('#conTaxTotal').val(calTx);
                            $('#conTaxTotalAmount').val(tot);
                            //                        if(calTx != null && calTx != '' ){
                            $('#con_tax_total').html(calTx);
                            $('#con_tax_total_amount').html(tot);
                            //                        }else{
                            //                            $('#con_tax_total').html('');
                            //                            $('#con_tax_total_amount').html('');
                            //                        }
                            convTotExpn();
                        } else if(catval == 254){
                            $('#category_error_'+split[1]).hide();
                            var temp = caliculateMiscelianeous();
                            $('#mistotal').val(temp);
                            if(temp != 0 && temp >0 ){
                                $('#mis_act').html(temp);
                            }else{
                                $('#mis_act').html('');
                            }
                            var cal = calEligibility('mis_eli_cal');                    
                            if(cal != 0 && cal >0 ){
                                $('#mis_eli_tot').html(cal);
                                $('#misEliTot').val(cal);
                            }else{
                                $('#mis_eli_tot').html('');
                                $('#misEliTot').val(0);
                            }
                            $('#misTaxTotal').val(calTx);
                            $('#misTaxTotalAmount').val(tot);
                            //                        if(calTx != null && calTx != '' ){
                            $('#mis_tax_total').html(calTx);
                            $('#mis_tax_total_amount').html(tot);
                            //                        }else{
                            //                            $('#mis_tax_total').html('');
                            //                            $('#mis_tax_total_amount').html('');
                            //                        }
                            misTotExpn();
                            misDeviation();
                        }                    
                        //actualTotal();
                        var actTot = actualTotal();
                        $('#totalAmount').val(actTot);
                        if(actTot != 0 && actTot >0 ){
                            $('#actual_total').html(actTot);
                        }else{
                            $('#actual_total').html('');
                        }
                        totEligibility();
                        getTotalAndTax();
                        totalExpence();
                        totalDeviation();                    
                    }
                });
             
                //**********************************************
                
                var frmDt=$("#startDate").val();
                if(frmDt != null && frmDt != ''){
                    //                var aa = frmDt.split("-");
                    //                if(aa!=''){
                    alert('START DATE ON CHANGFE');
                    $('.startDate').datepicker({
                        showOtherMonths: true,
                        selectOtherMonths: true,
                        changeMonth:true,
                        changeYear:true,
                        dateFormat: 'dd-M-yy',
                        minDate:new Date(frmDt),
                        maxDate:'+1Y'
                    });
                    //                }
                }
                $("#endDate").click(function() {
                    var frmDt=$("#startDate").val();
                    if(frmDt==""){
                        $("#endDate").datepicker('destroy');
                        alert("Please select From Date");
                    }
                });
                
            });
            var disable = false;
            var prevalue ='';            
                
            myAutoComplet = function() {
                $(".cityClass").autocomplete("${pageContext.request.contextPath}/citySearch.htm?type=${travelDetails.countryId}", {
                    minChars: 2,
                    matchContains: true
                });
                $(".cityClass").result(function(event, data) {
                    if (data) {
                        $(this).parent().find(".hiddenbox").val(data[1]);
                        var key = $(this).attr("id").split('_')[2];
                        getEliAmount(key);
                    }
                });
            }
            travelAutoComplet = function() {
//                $(".travelCity").autocomplete("${pageContext.request.contextPath}/citySearch.htm?type=${travelDetails.countryId}", {
//                    minChars: 2,
//                    matchContains: true
//                });
//                $(".travelCity").result(function(event, data) {
//                    if (data) {
//                        $(this).parent().find(".hiddenbox").val(data[1]);                            
//                    }
//                }); 
            }
            travelAutoComplet();
            myAutoComplet();
            function checkCategory(rowdata){
                var spli = rowdata.split('_');
                $('#billAmount_'+spli[1]).val(''); 
                $('#eligibility_'+spli[1]).val('');
                $('#tic_tax_'+spli[1]).val('');
                $('#tic_tot_'+spli[1]).val('');
                $('#bill_'+spli[1]).attr("readonly", true);
                $('#category_error_'+spli[1]).hide();
                var preCategory = $('#preCategory_'+spli[1]).val();
                var catval = $('#'+rowdata).val();                
                var tax = 0;
                var tot = 0;
                $(".expenseTable tbody tr").each(function(){
                    var key = (this.id).split('_')[1];
                    var category = $('#category_'+key).val();
                    if(category == preCategory){
                        var tx = $('#tic_tax_'+key).val();
                        var tt = $('#tic_tot_'+key).val();
                        if(tx != null && tx != ''){
                            tax = Number(tax) + Number(tx);
                        }
                        if(tt != null && tt != ''){
                            tot = Number(tot) + Number(tt);
                        }
                    }
                });
                if(preCategory == '0' || preCategory == ''){
                    if(catval == 250){
                        $('#billAmount_'+spli[1]).addClass("tic_cal");
                        $('#bill_'+spli[1]).prop("readonly", true);
                    }else if(catval == 251){                        
                        $('#billAmount_'+spli[1]).addClass("lod_cal");
                        $('#eligibility_'+spli[1]).addClass("lod_eli_cal");
                    }else if(catval == 252){
                        $('#billAmount_'+spli[1]).addClass("boar_cal");
                        $('#eligibility_'+spli[1]).addClass("boar_eli_cal");
                    }else if(catval == 253){
                        $('#billAmount_'+spli[1]).addClass("conv_cal");
                    }else if(catval == 254){                        
                        $('#billAmount_'+spli[1]).addClass("mis_cal");
                        $('#eligibility_'+spli[1]).addClass("mis_eli_cal");
                    }
                }else if(preCategory == 250){
                    $('#billAmount_'+spli[1]).removeClass("tic_cal");
                    if(catval == 250){
                        $('#billAmount_'+spli[1]).addClass("tic_cal");
                        $('#bill_'+spli[1]).prop("readonly", true);
                    }else if(catval == 251){
                        $('#billAmount_'+spli[1]).addClass("lod_cal");
                        $('#eligibility_'+spli[1]).addClass("lod_eli_cal");
                    }else if(catval == 252){
                        $('#billAmount_'+spli[1]).addClass("boar_cal");
                        $('#eligibility_'+spli[1]).addClass("boar_eli_cal");
                    }else if(catval == 253){
                        $('#billAmount_'+spli[1]).addClass("conv_cal");
                    }else if(catval == 254){
                        $('#billAmount_'+spli[1]).addClass("mis_cal");
                        $('#eligibility_'+spli[1]).addClass("mis_eli_cal");
                    }
                    getEliAmount(spli[1]);
                    var temp =  caliculateTicket();
                    $('#ticTotal').val(temp);
                    if(temp == 0 || temp < 0){
                        $('#tic_act').html('');
                    }else{
                        $('#tic_act').html(temp);
                    }
                    $('#ticTaxTotal').val(tax);
                    $('#ticTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#tic_tax_total').html(tax);
                        $('#tic_tax_total_amount').html(tot);
                    }else{
                        $('#tic_tax_total').html('');
                        $('#tic_tax_total_amount').html('');
                    }                    
                    ticketTotExpn();
                }else if(preCategory == 251){
                    $('#billAmount_'+spli[1]).removeClass("lod_cal");                    
                    $('#eligibility_'+spli[1]).removeClass("lod_eli_cal");
                    if(catval == 250){
                        $('#billAmount_'+spli[1]).addClass("tic_cal");
                        $('#bill_'+spli[1]).attr("readonly", 'readonly');
                    }else if(catval == 251){
                        $('#billAmount_'+spli[1]).addClass("lod_cal");
                        $('#eligibility_'+spli[1]).addClass("lod_eli_cal");
                    }else if(catval == 252){
                        $('#billAmount_'+spli[1]).addClass("boar_cal");
                        $('#eligibility_'+spli[1]).addClass("boar_eli_cal");
                    }else if(catval == 253){
                        $('#billAmount_'+spli[1]).addClass("conv_cal");
                    }else if(catval == 254){
                        $('#billAmount_'+spli[1]).addClass("mis_cal");
                        $('#eligibility_'+spli[1]).addClass("mis_eli_cal");
                    }
                    var temp =caliculateLodging();
                    $('#lodtotal').val(temp);
                    if(temp == 0 || temp < 0){
                        $('#lod_act').html('');
                    }else{
                        $('#lod_act').html(temp);
                    }
                    getEliAmount(spli[1]);
                    var cal = calEligibility('lod_eli_cal');
                    $('#lodEliTot').val(cal);
                    if(cal != 0 && cal >0 ){
                        $('#lod_eli_tot').html(cal);
                    }else{
                        $('#lod_eli_tot').html('');
                    }
                    $('#lodTaxTotal').val(tax);
                    $('#lodTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#lod_tax_total').html(tax);
                        $('#lod_tax_total_amount').html(tot);
                    }else{
                        $('#lod_tax_total').html('');
                        $('#lod_tax_total_amount').html('');
                    }
                    lodgingTotExpn();
                    lodDeviation();
                } else if(preCategory == 252){
                    $('#billAmount_'+spli[1]).removeClass("boar_cal");
                    $('#eligibility_'+spli[1]).removeClass("boar_eli_cal");
                    if(catval == 250){
                        $('#billAmount_'+spli[1]).addClass("tic_cal");
                        $('#bill_'+spli[1]).attr("readonly", 'readonly');
                    }else if(catval == 251){
                        $('#billAmount_'+spli[1]).addClass("lod_cal");
                        $('#eligibility_'+spli[1]).addClass("lod_eli_cal");
                    }else if(catval == 252){
                        $('#billAmount_'+spli[1]).addClass("boar_cal");
                        $('#eligibility_'+spli[1]).addClass("boar_eli_cal");
                    }else if(catval == 253){
                        $('#billAmount_'+spli[1]).addClass("conv_cal");
                    }else if(catval == 254){
                        $('#billAmount_'+spli[1]).addClass("mis_cal");
                        $('#eligibility_'+spli[1]).addClass("mis_eli_cal");
                    }
                    var temp = caliculateBoarding();
                    $('#boartotal').val(temp);
                    if(temp == 0){
                        $('#boar_act').html('');
                    }else{                             
                        $('#boar_act').html(temp);
                    }                    
                    getEliAmount(spli[1]);
                    var cal = calEligibility('boar_eli_cal');
                    $('#boarEliTot').val(cal);
                    if(cal != 0 && cal >0 ){
                        $('#boar_eli_tot').html(cal);
                    }else{
                        $('#boar_eli_tot').html('');
                    }
                    $('#boarTaxTotal').val(tax);
                    $('#boarTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#boar_tax_total').html(tax);
                        $('#boar_tax_total_amount').html(tot);
                    }else{
                        $('#boar_tax_total').html('');
                        $('#boar_tax_total_amount').html('');
                    }
                    boardingTotExpn();
                    boarDeviation();
                }else if(preCategory == 253){
                    $('#billAmount_'+spli[1]).removeClass("conv_cal");
                    if(catval == 250){
                        $('#billAmount_'+spli[1]).addClass("tic_cal");
                        $('#bill_'+spli[1]).attr("readonly", 'readonly');
                    }else if(catval == 251){
                        $('#billAmount_'+spli[1]).addClass("lod_cal");
                        $('#eligibility_'+spli[1]).addClass("lod_eli_cal");
                    }else if(catval == 252){
                        $('#billAmount_'+spli[1]).addClass("boar_cal");
                        $('#eligibility_'+spli[1]).addClass("boar_eli_cal");
                    }else if(catval == 253){
                        $('#billAmount_'+spli[1]).addClass("conv_cal");
                    }else if(catval == 254){
                        $('#billAmount_'+spli[1]).addClass("mis_cal");
                        $('#eligibility_'+spli[1]).addClass("mis_eli_cal");
                    }           
                    getEliAmount(spli[1]);
                    var temp = caliculateConveyance();                                                  
                    $('#convtotal').val(temp);
                    if(temp == 0){
                        $('#conv_act').html('');
                    }else{                            
                        $('#conv_act').html(temp);
                    }
                    $('#conTaxTotal').val(tax);
                    $('#conTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#con_tax_total').html(tax);
                        $('#con_tax_total_amount').html(tot);
                    }else{
                        $('#con_tax_total').html('');
                        $('#con_tax_total_amount').html('');
                    }
                    convTotExpn();
                }else if(preCategory == 254){
                    $('#billAmount_'+spli[1]).removeClass("mis_cal");
                    $('#eligibility_'+spli[1]).removeClass("mis_eli_cal");
                    if(catval == 250){
                        $('#billAmount_'+spli[1]).addClass("tic_cal");
                        $('#bill_'+spli[1]).attr("readonly", 'readonly');
                    }else if(catval == 251){
                        $('#billAmount_'+spli[1]).addClass("lod_cal");
                        $('#eligibility_'+spli[1]).addClass("lod_eli_cal");
                    }else if(catval == 252){
                        $('#billAmount_'+spli[1]).addClass("boar_cal");
                        $('#eligibility_'+spli[1]).addClass("boar_eli_cal");
                    }else if(catval == 253){
                        $('#billAmount_'+spli[1]).addClass("conv_cal");
                    }else if(catval == 254){
                        $('#billAmount_'+spli[1]).addClass("mis_cal");
                        $('#eligibility_'+spli[1]).addClass("mis_eli_cal");
                    }
                    var temp = caliculateMiscelianeous();
                    $('#mistotal').val(temp);
                    if(temp == 0){
                        $('#mis_act').html('');
                    }else{
                        $('#mis_act').html(temp);
                    }
                    getEliAmount(spli[1]);
                    var cal = calEligibility('mis_eli_cal');
                    $('#misEliTot').val(cal);
                    if(cal != 0 && cal >0 ){
                        $('#mis_eli_tot').html(cal);
                    }else{
                        $('#mis_eli_tot').html('');
                    }
                    $('#misTaxTotal').val(tax);
                    $('#misTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#mis_tax_total').html(tax);
                        $('#mis_tax_total_amount').html(tot);
                    }else{
                        $('#mis_tax_total').html('');
                        $('#mis_tax_total_amount').html('');
                    }
                    misTotExpn();
                    misDeviation();
                }
                var actTot = actualTotal();
                $('#totalAmount').val(actTot);
                if(actTot != 0 && actTot >0 ){
                    $('#actual_total').html(actTot);
                }else{
                    $('#actual_total').html('');
                }                
                $('#preCategory_'+spli[1]).val(catval);
                totEligibility();                
                getTotalAndTax();
                totalExpence();
                totalDeviation();
            }            
            function totalDeviation(){
                var totexp = $('#totTotExp').val();
                var totele = $('#totEliTot').val();
                var totdev =  Number(totexp) - Number(totele);
                if(totdev > 0 ){
                    $('#totDeviation').val(totdev);
                    // $('#tot_deviation').html(totdev);
                    if(totele > 0 ){
                        var per = Math.round((totdev * 100)/Number(totele));
                        $('#deviationPercent').val(per);
                        $('#tot_deviation').html(totdev+' ( '+per+'% )');
                    }else{
                        $('#tot_deviation').html(totdev);
                    }
                }else{
                    $('#totDeviation').val(0);
                    $('#deviationPercent').val(0);
                    $('#tot_deviation').html('');
                }
            }
            
            function lodDeviation(){
                var lodtotexp = $('#lodTotExp').val();
                var lodelitot = $('#lodEliTot').val();
                var loddev =  Number(lodtotexp) - Number(lodelitot); 
                var per = 0;
                if(loddev > 0 ){
                    if(lodelitot > 0 ){
                        per = Math.round((loddev * 100)/Number(lodelitot));
                        $('#lod_deviation').html(loddev+' ( '+per+'% )');
                    }else{
                        $('#lod_deviation').html('');
                    }
                    $('#lodDevPercentage').val(per);
                    $('#lodDeviation').val(loddev);
                }else{
                    $('#lodDeviation').val(0);
                    $('#lod_deviation').html('');
                }
            }
            function boarDeviation(){
                var bortotexp = $('#borTotExp').val();
                var borelitot = $('#boarEliTot').val();
                var per = 0;
                var loddev = Number(bortotexp) - Number(borelitot);
                if(loddev > 0 ){
                    if(borelitot > 0 ){
                        var per = Math.round((loddev * 100)/Number(borelitot));
                        $('#bor_deviation').html(loddev+' ( '+per+'% )');
                    }else{
                        $('#bor_deviation').html(loddev);
                    }
                    $('#boarDevPercentage').val(per);
                    $('#borDeviation').val(loddev);                   
                }else{
                    $('#borDeviation').val(0);  
                    $('#bor_deviation').html('');
                }
            }
            function misDeviation(){
                var mistotexp = $('#misTotExp').val();
                var miselitot = $('#misEliTot').val();
                var per = 0;
                var loddev =  Number(mistotexp) - Number(miselitot);
                if(loddev > 0 ){
                    if(miselitot > 0 ){
                        var per = Math.round((loddev * 100)/Number(miselitot));
                        $('#mis_deviation').html(loddev+' ( '+per+'% )');
                    }else{
                        $('#mis_deviation').html(loddev);
                    }
                    $('#misDevPercentage').val(per);
                    $('#misDeviation').val(loddev);
                    
                }else{
                    $('#misDeviation').val(0);
                    $('#mis_deviation').html('');
                }
            }
            function actualTotal(){
                var tot = 0;
                tot = caliculateTicket() + caliculateLodging() + caliculateBoarding() + caliculateConveyance() + caliculateMiscelianeous() ;
                return tot;
            }
            function totalExpence(){
                var tte = 0;
                var ct = $('#totalTotalAmount').val();
                var at = $('#companyTotalTaxTotal').val();
                tte = Number(ct) + Number(at);
                $('#totTotExp').val(tte);
                //var tte = ticketTotExpn() + lodgingTotExpn() + boardingTotExpn() + convTotExpn() + misTotExpn() + misTotExpn() ;
                if(tte != 0 && tte >0 ){
                    $('#tot_tot_exp').html(tte);
                }else{
                    $('#tot_tot_exp').html('');
                }
            }
            function totEligibility(){
                var tot = 0 ;
                var ticeli = $('#ticEliTot').val();
                var coneli = $('#conEliTot').val();
                tot = Number(ticeli) + Number(coneli) + calEligibility('lod_eli_cal') + calEligibility('boar_eli_cal') + calEligibility('mis_eli_cal') ;
                $('#totEliTot').val(tot);
                if(tot != 0 && tot >0 ){
                    $('#tot_eli_tot').html(tot);
                }else{
                    $('#tot_eli_tot').html('');
                }
            }
            
            function calEligibility(cls){
                var tot = 0;
                $('.'+cls).each(function() {
                    tot += Number($(this).val());
                });
                return tot;
            }
            function ticketTotExpn(){
                var tot = 0;
                var tte = $('#comTicTaxTotalAmount').val();
                var ate = $('#ticTaxTotalAmount').val();
                //$('#ticTotal').val();
                tot = Number(tte) + Number(ate);
                if(tot != 0 && tot >0 ){
                    $('#ticTotExp').val(tot);
                    $('#tic_tot_exp').html(tot);
                    $('#ticEliTot').val(tot);
                    $('#tic_eli_tot').html(tot)
                }else{
                    $('#ticTotExp').val(0);
                    $('#tic_tot_exp').html('');
                    $('#ticEliTot').val(0);
                    $('#tic_eli_tot').html('')
                }
            }
            function lodgingTotExpn(){
                var tot = 0;
                var tte = $('#comLodTaxTotalAmount').val();
                var ate =$('#lodTaxTotalAmount').val();
                //$('#lodtotal').val();
                tot = Number(tte) + Number(ate);
                if(tot != 0 && tot >0 ){
                    $('#lodTotExp').val(tot);
                    $('#lod_tot_exp').html(tot);
                }else{
                    $('#lodTotExp').val(0);
                    $('#lod_tot_exp').html('');
                }                
            }
            function boardingTotExpn(){
                var tot = 0;
                var tte = $('#comBoarTaxTotalAmount').val();                
                var ate = $('#boarTaxTotalAmount').val();
                //$('#boartotal').val();
                //                if(ate == undefined){
                //                    tot = Number(tte);
                //                }else{}
                tot = Number(tte) + Number(ate);
                $('#borTotExp').val(tot);
                if(tot != 0 && tot >0 ){
                    $('#bor_tot_exp').html(tot);
                }else{
                    $('#bor_tot_exp').html('');
                }
            }
            function convTotExpn(){
                var tot = 0;
                var tte = $('#comConTaxTotalAmount').val();
                var ate = $('#conTaxTotalAmount').val();
                //$('#contotal').val();
                tot = Number(tte) + Number(ate);
                $('#conTotExp').val(tot);
                $('#conEliTot').val(tot);
                $('#con_eli_tot ').val(tot);
                if(tot != 0 && tot >0 ){
                    $('#con_tot_exp').html(tot);
                    $('#con_eli_tot').html(tot)
                }else{
                    $('#con_tot_exp').html('');
                    $('#con_eli_tot').html('')
                }
            }
            function misTotExpn(){
                var tot = 0;
                var tte = $('#comMisTaxTotalAmount').val();
                var ate = $('#misTaxTotalAmount').val();
                //$('#mistotal').val();
                tot = Number(tte) + Number(ate);
                $('#misTotExp').val(tot);
                if(tot != 0 && tot >0 ){                     
                    $('#mis_tot_exp').html(tot);
                }else{                   
                    $('#mis_tot_exp').html('');
                }                
            }
            
            function caliculateTicket(){
                var tot = 0;
                $('.tic_cal').each(function() {
                    tot += Number($(this).val());
                });
                 
                return tot;
            }
            function caliculateLodging(){
                var tot = 0;
                $('.lod_cal').each(function() {
                    tot += Number($(this).val());
                });
                return tot;
            }
            function caliculateBoarding(){
                var tot = 0;
                $('.boar_cal').each(function() {
                    tot += Number($(this).val());
                });
                return tot;
            }
            function caliculateConveyance(){
                var tot = 0;
                $('.conv_cal').each(function() {
                    tot += Number($(this).val());
                });
                return tot;
            }
            function caliculateMiscelianeous(){
                var tot = 0;
                $('.mis_cal').each(function() {
                    tot += Number($(this).val());
                });
                return tot;
            }
            // $(".billableAmountClass").focusout(function(){
            function checkFile(input, id) {
                $('#hotImageUpload_error_'+id).hide();
                if (input.files && input.files[0]) {
                    var fileUpload = document.getElementById("fileAttachment_"+id);
                    var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+(.pdf)$");
                    if (regex.test(fileUpload.value.toLowerCase())) {
                    } else {
                        $('#fileAttachment_'+id).val('');
                        $('#fileAttachment_error_'+id).html('pdf format only');
                        $('#fileAttachment_error_'+id).show();
                        return false;
                    }
                }
            }
            
            function addAttachmentRow(rowObject){
                var theRow = $(rowObject).parent().parent();
                var rowData = "";
                var totalCount = $('#attachmentCount').val();
                cnt = parseInt(totalCount) + 1;
                $('#attachmentCount').val(cnt);
                rowData += '<tr id=TA_'+cnt+'>';
                rowData += '<td class="wd-10p">';
                rowData += '<select class="ht-25 pd-0 tx-12-force rounded-5 fileCategoryClass" name="file_category_'+cnt+'" id=fileCategory_'+cnt+'>';
                rowData += '<option value="">Select</option>';
                rowData += '<c:forEach items="${catList}" var="catlist">';
                rowData += '<option  value="${catlist.configKey}">${catlist.configValue}</option>';
                rowData += '</c:forEach></select><span style="color:red;display:none;" id=fileCategory_error_'+cnt+'>*required</span></td>';
                rowData += '<td class="wd-15p"><input type="hidden" value="0" name="attachment_deleted_'+cnt+'" id=attachment_deleted_'+cnt+' />';
                rowData += '<input class="form-control ht-25 bg-white pd-0 tic_attachment" multiple="true" type="file" id=fileAttachment_'+cnt+' onchange=checkFile(this,'+cnt+');  name=fileAttachment_'+cnt+' value="" /><span style="color:red;display:none;" id=fileAttachment_error_'+cnt+'></span></td>';
                rowData += '<td class="wd-5p tx-12-force"><img class="addRemove" alt="Add" style="padding-left: 10px;" src="${pageContext.request.contextPath}/css/images/tm_add.png" onclick="addAttachmentRow(this);" />';
                rowData += '<a onClick=removeAttachmentRow(this,'+cnt+') id=remove_'+cnt+' > <img src="${pageContext.request.contextPath}/css/images/tm_delete.png" /></a></td>';
                rowData += "</tr>";
                $(theRow).after(rowData);
            }
            function removeAttachmentRow(rowObject , rowid){
                $('#attachment_deleted_'+rowid).val("1");
                var row = $(rowObject).parent().parent().css("display","none");
                
            }
            function addRow(rowObject , dupid){
                var rowData = "";
                var totalCount = $('#totalCount').val();
                cnt = parseInt(totalCount) + 1;
                $('#totalCount').val(cnt);
                var row = "";
                rowData += '<tr id=TR_'+cnt+' class="rowdup">';
                rowData += '<td class="wd-10p">';
                rowData += '<input type="hidden" name="preCategory" id=preCategory_'+cnt+' value="0">';
                rowData += '<select class="ht-25 wd-90 pd-0 tx-12-force rounded-5 categoryClass" name="res_category" id=category_'+cnt+' onchange=checkCategory("category_'+cnt+'")>';
                rowData += '<option value="">Select</option>';
                rowData += '<c:forEach items="${catList}" var="catlist">';
                rowData += '<option  value="${catlist.configKey}">${catlist.configValue}</option>';
                rowData += '</c:forEach></select><span style="color:red;display:none;" id=category_error_'+cnt+'>*required</span></td>';
                rowData += '<td class="wd-8p"><input class="form-control ht-25 wd-75 bg-white pd-0 fromDate tx-12-force rounded-5 fromdateClass" type="text" id=fromDate_'+cnt+' name="res_fromDate" value="" readonly />';
                rowData += '<span style="color:red;display:none;" id=fromdateClass_error_'+cnt+'>Invalid</span></td>';
                rowData += '<td class="wd-8p"><input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 todateClass" type="text" id=toDate_'+cnt+'  name="res_toDate" value="" readonly />';
                rowData += '<span style="color:red;display:none;" id=todateClass_error_'+cnt+'>Inavlid</span></td>';
                rowData += '<td class="wd-10"><input type="hidden" name="res_city" id=city_'+cnt+' value="" class="hiddenbox" />';
//                rowData += '<input type="text" name="res_city_id" onselect="getEliAmount('+cnt+')" id=res_city_'+cnt+' class="form-control ht-25 pd-0 wd-110 pd-0 tx-12-force rounded-5 cityClass" value=""></td>';
                rowData += '<select name="res_city_id" id="res_city_'+cnt+'" onchange="getEliAmount('+cnt+')" class="ht-25 wd-90 pd-0 tx-12-force cityClass">';
                rowData += '<option value="">-- Select City --</option><c:forEach items="${citylist}" var="citylist">';
                rowData += '<option  value="${citylist.cityId}">${citylist.cityName}</option></c:forEach></select>';
                rowData += '<td class="wd-15p tx-12-force">';
                rowData += '<select class="ht-25 wd-90 pd-0 tx-12-force rounded-5 billClass" onchange="getEliAmount('+cnt+')" name="res_bill" id=bill_'+cnt+'>';
                rowData += '<option  value="y">With Bill</option>';
                rowData += '<option  value="n">Without Bill</option>';
                rowData += '</select></td>';
                rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-55 pd-0 tx-12-force rounded-5 disableClass" type="text" id=eligibility_'+cnt+'  name="res_eligibility" value="" readonly /></td>';
                rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 pd-0 tx-12-force rounded-5 billdateClass disableClass" type="text" id=billDate_'+cnt+' name="res_billDate" value="" readonly /></td>';
                rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 pd-0 tx-12-force rounded-5 billnoClass disableClass" type="text" name="res_BillNo" id=billNo_'+cnt+' ></td>';
                rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 pd-0 tx-12-force rounded-5 billableAmountClass" type="text" name="res_amount"  id=billAmount_'+cnt+' onkeypress="return isNumberKey(event)"  onchange="calculateTotalAndTax('+cnt+');">';
                rowData += '<input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_tax disableClass" type="hidden" name="tic_tax" value="0" onkeypress="return isNumberKey(event)"  onchange="calculateTotalAndTax('+cnt+');" id=tic_tax_'+cnt+'>';
                rowData += '<input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_tot" type="hidden" name="tic_tot" onkeypress="return isNumberKey(event)" id=tic_tot_'+cnt+'></td>';
                rowData += '<td class="wd-8p"><input class="form-control ht-25 wd-90 pd-0 tx-12-force rounded-5 remarksClass" type="text" name="res_remarks" id=remarks_'+cnt+' ></td>';
                rowData += '<td class="wd-2p tx-12-force"><img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/css/images/tm_add.png" onclick="addRow(this,addRow);" />';
                if(dupid != 'cleanUp'){
                    rowData += '<a onClick="removeRow('+cnt+')" id=remove_'+cnt+' > <img class="rowRemove" src="${pageContext.request.contextPath}/css/images/tm_delete.png" /></a></td>';
                }
                rowData += "</tr>";
                if(dupid == 'cleanUp'){
                    $('.expenseTable').append(rowData);
                }else{
                    var theRow = $(rowObject).parent().parent();   
                    $(theRow).after(rowData);
                }
                myAutoComplet();
                timeBound();
                $(".billdateClass").datepicker({minDate : new Date('${travelDetails.requestedDate}') ,maxDate : new Date(), dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true });
                //                $(".billdateClass").datepicker({maxDate : new Date(), dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
                var date1 = $('#startDate').val();
                var date2 = $('#endDate').val(); 
                $(".fromdateClass").datepicker({minDate: new Date(date1),dateFormat:"dd-M-yy",maxDate:new Date(date2),
                    onSelect: function(date) {
                        var key = $(this).attr("id").split('_')[1];
                        $("#toDate_"+key).datepicker('destroy');
                        $("#toDate_"+key).val('');
                        var frd = $('#fromDate_'+key).val();
                        $("#toDate_"+key).datepicker({minDate: new Date(frd),dateFormat:"dd-M-yy",maxDate:new Date(date2),
                            onSelect: function(date) {
                                var key = $(this).attr("id").split('_')[1];
                                getEliAmount(key);
                            }
                        });
                        getEliAmount(key);
                    }
                });
                $(".todateClass").datepicker({minDate: new Date(date1),dateFormat:"dd-M-yy",maxDate:new Date(date2),
                    onSelect: function(date) {
                        var key = $(this).attr("id").split('_')[1];
                        getEliAmount(key);
                    }
                });
            }
          
            function removeRow(rowid){
                var catval = $('#category_'+rowid).val();
                $('#TR_'+rowid).remove();
                var tot=0;
                var tax = 0;
                $(".expenseTable tbody tr").each(function(){
                    var key = (this.id).split('_')[1];
                    var category = $('#category_'+key).val();
                    if(category == catval){
                        var tx = $('#tic_tax_'+key).val();
                        var tt = $('#tic_tot_'+key).val();
                        if(tx != null && tx != ''){
                            tax = Number(tax) + Number(tx);
                        }
                        if(tt != null && tt != ''){
                            tot = Number(tot) + Number(tt);
                        }
                    }
                });                
                if(catval == 250){
                    var temp = caliculateTicket();
                    $('#ticTotal').val(temp);
                    if(temp != 0 && temp >0 ){
                        $('#tic_act').html(temp);
                    }else{
                        $('#tic_act').html('');
                    }
                    $('#ticTaxTotal').val(tax);
                    $('#ticTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#tic_tax_total').html(tax);
                        $('#tic_tax_total_amount').html(tot);
                    }else{
                        $('#tic_tax_total').html('');
                        $('#tic_tax_total_amount').html('');
                    }
                    ticketTotExpn();
                } else if(catval == 251){
                    var temp = caliculateLodging();
                    $('#lodtotal').val(temp);
                    if(temp != 0 && temp >0 ){
                        $('#lod_act').html(temp);
                    }else{
                        $('#lod_act').html('');
                    }
                    var cal = calEligibility('lod_eli_cal');
                    $('#lodEliTot').val(cal);
                    if(cal != 0 && cal >0 ){
                        $('#lod_eli_tot').html(cal);
                    }else{
                        $('#lod_eli_tot').html('');
                    }
                    $('#lodTaxTotal').val(tax);
                    $('#lodTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#lod_tax_total').html(tax);
                        $('#lod_tax_total_amount').html(tot);
                    }else{
                        $('#lod_tax_total').html('');
                        $('#lod_tax_total_amount').html('');
                    }
                    lodgingTotExpn();
                    lodDeviation();                         
                } else if(catval == 252){
                    var temp = caliculateBoarding();
                    $('#boartotal').val(temp);
                    if(temp != 0 && temp >0 ){                             
                        $('#boar_act').html(temp);
                    }else{
                        //                             $('#boartotal').val("0");
                        $('#boar_act').html('');
                    }
                    var cal = calEligibility('boar_eli_cal');
                    $('#boarEliTot').val(cal);
                    if(cal != 0 && cal >0 ){
                        $('#boar_eli_tot').html(cal);
                    }else{
                        $('#boar_eli_tot').html('');
                    }
                    $('#boarTaxTotal').val(tax);
                    $('#boarTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#boar_tax_total').html(tax);
                        $('#boar_tax_total_amount').html(tot);
                    }else{
                        $('#boar_tax_total').html('');
                        $('#boar_tax_total_amount').html('');
                    }
                    boardingTotExpn();
                    boarDeviation();                         
                } else if(catval == 253){
                    var temp = caliculateConveyance();
                    $('#convtotal').val(temp);
                    if(temp != 0 && temp >0 ){
                        $('#conv_act').html(temp);
                    }else{
                        $('#conv_act').html('');
                    }
                    $('#conTaxTotal').val(tax);
                    $('#conTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#con_tax_total').html(tax);
                        $('#con_tax_total_amount').html(tot);
                    }else{
                        $('#con_tax_total').html('');
                        $('#con_tax_total_amount').html('');
                    }
                    convTotExpn();
                } else if(catval == 254){
                    var temp = caliculateMiscelianeous();
                    $('#mistotal').val(temp);
                    if(temp != 0 && temp >0 ){
                        $('#mis_act').html(temp);
                    }else{
                        $('#mis_act').html('');
                    }
                    var cal = calEligibility('mis_eli_cal');
                    $('#misEliTot').val(cal);
                    if(cal != 0 && cal >0 ){
                        $('#mis_eli_tot').html(cal);
                    }else{
                        $('#mis_eli_tot').html('');
                    }
                    $('#misTaxTotal').val(tax);
                    $('#misTaxTotalAmount').val(tot);
                    if(tax != 0 && tax >0 ){
                        $('#mis_tax_total').html(tax);
                        $('#mis_tax_total_amount').html(tot);
                    }else{
                        $('#mis_tax_total').html('');
                        $('#mis_tax_total_amount').html('');
                    }
                    misTotExpn();
                    misDeviation();
                }
                var actTot = actualTotal();
                $('#totalAmount').val(actTot);
                if(actTot != 0 && actTot >0 ){
                    $('#actual_total').html(actTot);
                }else{
                    $('#actual_total').html('');
                }
                totEligibility();
                getTotalAndTax();
                totalExpence();
                totalDeviation();
                return false;
            }
            function getEliAmount(id){                
                var tCategory = $('#category_'+id).val();
                var strdate = $('#startDate').val();
                var endate = $('#endDate').val();
                var curr = $('#currencyId').val();
                var strd = moment(new Date(strdate) , "DD-MMM-YYYY");
                var endd = moment(new Date(endate) , "DD-MMM-YYYY");
                var diffDays = endd.diff(strd, 'days');
                var sel = $('#bill_'+id).val();
                if(sel == 'n' ){
                    $('#TR_'+id).find('.disableClass').val('');
                    $('#TR_'+id).find('.disableClass').attr('disabled', true);                 
                }else if(sel == '' || sel == 'y'){
                    $('#TR_'+id).find('.disableClass').attr('disabled', false);
                }
                for(var i=0;i<= diffDays ; i++){
                    fd = moment(new Date(strdate) , "DD-MMM-YYYY").format('DD-MMM-YYYY');
                    dateArr[fd][tCategory] = 0;
                    strdate  = moment(new Date(fd)).add(1,'days').format("DD-MMM-YYYY");
                }
                $(".expenseTable tbody tr").each(function(){
                    var key = (this.id).split('_')[1];
                    var category = $('#category_'+key).val();
                    if(category == tCategory){
                        var band = $('#bandId').val();
                        var trTerm;
                        var ttr = $('#travelTerm').val();
                        if(ttr == 'Short Term'){
                            trTerm = 's';
                        }else if(ttr == 'Long Term'){
                            trTerm = 'l';
                        }                        
                        var trType = $('#travelType').val();
                        var trCategory = $('#category_'+key).val();
                        var trFromDate = $('#fromDate_'+key).val();
                        var trToDate = $('#toDate_'+key).val();
                        var trCity = $('#res_city_'+key).val();
                        var bill = $('#bill_'+key).val();
                        var flag=0;
                        var i = 0;                       
                        if(bill !=null && bill !='' &&  trCategory != null && trCategory != '' && trFromDate != null && trFromDate !='' && trToDate != null && trToDate != '' && trCity != null && trCity != ''){
                            for(i = new Date(trFromDate); i <= new Date(trToDate); i.setDate(i.getDate() + 1)){
                                fd = moment(new Date(i) , "DD-MMM-YYYY").format('DD-MMM-YYYY');                   
                                if(dateArr[fd][trCategory]=='0'){
                                    dateArr[fd][trCategory]='1';
                                    flag = flag + 1;
                                }
                            }
                            if((trCategory =='251' || trCategory == '252' || trCategory == '254') && flag >0){
                                $.ajax({
                                    url: '${pageContext.request.contextPath}/getEligibilityAmount.htm',
                                    data: {
                                        travelTerm : trTerm,
                                        travelType : trType,
                                        category : trCategory,
                                        fromDate : trFromDate,
                                        curreny : curr,
                                        toDate : trToDate,
                                        city : trCity,
                                        bandId : band,
                                        bill : bill,
                                        datediff : flag
                                    },
                                    success: function(ajaxObj) {
                                        $('#eligibility_'+key).val(ajaxObj);
                                        if(trCategory =='251'){
                                            var cal = calEligibility('lod_eli_cal');
                                            $('#lodEliTot').val(cal);
                                            if(cal != 0 && cal >0 ){
                                                $('#lod_eli_tot').html(cal);
                                            }else{
                                                $('#lod_eli_tot').html('');
                                            }
                                        } else if(trCategory =='252'){
                                            var cal = calEligibility('boar_eli_cal');
                                            $('#boarEliTot').val(cal);
                                            if(cal != 0 && cal >0 ){
                                                $('#boar_eli_tot').html(cal);
                                            }else{
                                                $('#boar_eli_tot').html('');
                                            }
                                        } else if(trCategory =='254'){
                                            var cal = calEligibility('mis_eli_cal');
                                            $('#misEliTot').val(cal);
                                            if(cal != 0 && cal >0 ){
                                                $('#mis_eli_tot').html(cal);
                                            }else{
                                                $('#mis_eli_tot').html('');
                                            }
                                        }
                                        totEligibility();
                                        totalDeviation();
                                    }
                                });
                            }else {
                                $('#eligibility_'+key).val(0);
                                if(trCategory =='251'){
                                    var cal = calEligibility('lod_eli_cal');
                                    $('#lodEliTot').val(cal);
                                    if(cal != 0 && cal >0 ){
                                        $('#lod_eli_tot').html(cal);
                                    }else{
                                        $('#lod_eli_tot').html('');
                                    }
                                } else if(trCategory =='252'){
                                    var cal = calEligibility('boar_eli_cal');
                                    $('#boarEliTot').val(cal);
                                    if(cal != 0 && cal >0 ){
                                        $('#boar_eli_tot').html(cal);
                                    }else{
                                        $('#boar_eli_tot').html('');
                                    }
                                } else if(trCategory =='254'){
                                    var cal = calEligibility('mis_eli_cal');
                                    $('#misEliTot').val(cal);
                                    if(cal != 0 && cal >0 ){
                                        $('#mis_eli_tot').html(cal);
                                    }else{
                                        $('#mis_eli_tot').html('');
                                    }
                                }
                                totEligibility();
                                totalDeviation();
                            }
                        }else{

                            $('#eligibility_'+key).val(0);
                            if(trCategory =='251'){
                                var cal = calEligibility('lod_eli_cal');
                                $('#lodEliTot').val(cal);
                                if(cal != 0 && cal >0 ){
                                    $('#lod_eli_tot').html(cal);
                                }else{
                                    $('#lod_eli_tot').html('');
                                }
                            } else if(trCategory =='252'){
                                var cal = calEligibility('boar_eli_cal');
                                $('#boarEliTot').val(cal);
                                if(cal != 0 && cal >0 ){
                                    $('#boar_eli_tot').html(cal);
                                }else{
                                    $('#boar_eli_tot').html('');
                                }
                            } else if(trCategory =='254'){
                                var cal = calEligibility('mis_eli_cal');
                                $('#misEliTot').val(cal);
                                if(cal != 0 && cal >0 ){
                                    $('#mis_eli_tot').html(cal);
                                }else{
                                    $('#mis_eli_tot').html('');
                                }
                            }
                            totEligibility();
                            totalDeviation();
                        }
                    }
                })
            }
            function isNumberKey(evt)
            {
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
        <div class="slim-mainpanel">
            <div class="container">
                <div class="slim-pageheader">
                    <ol class="breadcrumb slim-breadcrumb">
                        <li class="breadcrumb-item"><a href="getList.htm">Travel List</a></li>
                    </ol>
                    <h6 class="slim-pagetitle">Travel Settlement</h6>
                </div>
                <form name="settlementPage" action="addEmployeeSettlement.htm" method="POST" enctype="multipart/form-data" autocomplete="off">
                    <div class="section-tables">
                        <h6 class="slim-pagetitle" style="margin-top: 20px;padding:0px;border:0px;text-align: center;">Travel ID - ${travelDetails.ticketRefId}</h6>
                        <div class="table-responsive">
                            <input type="hidden" id="totalCount" value="1" />
                            <input type="hidden" id="masterId" name="masterId" value="${travelDetails.masterId}" />
                            <input type="hidden" id="travelType" name="travelType" value="${travelDetails.travelType}" />
                            <input type="hidden" id="ticketRefId" name="ticketRefId" value="${travelDetails.ticketRefId}" />
                            <input type="hidden" id="travelTerm" name="travelTerm" value="${travelDetails.travelTerm}" />
                            <input type="hidden" id="bandId" name="bandId" value="${travelDetails.bandId}" />
                            <input type="hidden" name="approver_type" id="approver_type" value="${approver_type}"/>
                            <input type="hidden" name="type" id="type" value="${type}"/>
                            <input type="hidden" name="actionbtn" id="actionbtn" value=""/>
                            <input type="hidden" name="managerId" id="managerId" value="${travelDetails.managerId}"/>
                            <input type="hidden" id="totalAdvance" name="totalAdvance" value="${totalAdvance}">
                            <input type="hidden" id="travelPeriod" name="travelPeriod" value="">
                            <input type="hidden" id="currencyId" name="currencyId" value="${travelDetails.currencyId}">
                            <input type="hidden" id="countryId" name="countryId" value="${travelDetails.countryId}">
                            <input type="hidden" name ="admin_action" value ="${travelDetails.adminAction}">
                            <label class="section-title">Travel Details</label>
                            <table class="col-sm">
                                <tbody>
                                    <tr>
                                        <td><strong class="tx-inverse tx-medium">Start Date</strong></td>
                                        <td><span class="text-muted">${travelDetails.travelCommenced}</span></td>
                                        <td><strong class="tx-inverse tx-medium">End Date</strong></td>
                                        <td><span class="text-muted">${travelDetails.travelCompleted}</span></td>
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
                                        <td><strong class="tx-inverse tx-medium"></strong></td>
                                        <td><span class="text-muted"></span></td>
                                    </tr>
                                    <tr>
                                        <td><strong class="tx-inverse tx-medium">Travel Commenced </strong><span style="color:red;" >*</span></td>
                                        <td class="wd-15p">                                           
                                            <div class="d-flex">
                                                <input class="form-control ht-25 bg-white wd-80 pd-0 tx-12-force startDate" type="text" name="startdt" id="startDate"value="" placeholder="DD-MMM-YYYY" onchange="calculateDateDiff()" readonly />
                                                <!--                                        <input type="text" class="fc-datepicker dtpicker readonlyclass billdateClass" id="startdt" name="startdt" value="" readonly>-->
                                                <input list="browsers1" id="travel_start_time" style="" class="form-control bg-white ht-25 pd-0 accrueTime wd-50 tx-12-force" placeholder="HH:MM" onchange="calculateDateDiff()" name="travel_start_time" readonly />
                                            </div>
                                            <span style="color:red;display:none;" id="travelStartDate_error">*required</span>
                                            <span id="planned"></span>
                                        </td>
                                        <td><strong class="tx-inverse tx-medium">Travel Completed</strong> <span style="color:red;" >*</span></td>
                                        <td class="wd-15p">
                                            <div>
                                                <div class="d-flex">
                                                    <input class="form-control ht-25 bg-white wd-80 pd-0 tx-12-force startDate " type="text" id="endDate" placeholder="DD-MMM-YYYY" onchange="calculateDateDiff()" name="enddt" value="" readonly />
                                                    <!--                                         <input type="text" class="dtpicker readonlyclass wd-80 fc-datepicker2 startDate" id="enddate" name="enddt" value="" readonly>-->
                                                    <input list="browsers1" id="travel_end_time"  class="form-control ht-25 pd-0 bg-white accrueTime wd-50 tx-12-force"  placeholder="HH:MM" onchange="calculateDateDiff()" name="travel_end_time" readonly/>
                                                </div>
                                                <span style="color:red;display:none;" id="travelEndDate_error">*required</span>
                                                <span id="planned"></span>
                                            </div>
                                        </td>
                                        <td><strong class="tx-inverse tx-medium">Travel Period</strong></td>
                                        <td class="wd-10p"><span id="travel_period" class="text-muted"></span></td>
                                    </tr>
                                    <tr>
                                        <td><strong class="tx-inverse tx-medium">From Destination </strong><span style="color:red;" >*</span></td>
                                        <td class="wd-15p">
                                            <select name="fromCity" id="fromCityid_1" class="ht-25 wd-150 pd-0 tx-12-force travelCity">
                                                <option value="">-- Select City --</option>
                                                <c:forEach items="${citylist}" var="citylist">
                                                    <option  value="${citylist.cityId}">${citylist.cityName}</option>
                                                </c:forEach>
                                            </select><span style="color:red;display:none;" id="from_city_error">*required</span>
                                        </td>
                                        <td><strong class="tx-inverse tx-medium">To Destination </strong><span style="color:red;" >*</span></td>
                                        <td class="wd-10p">
                                            <select name="toCity" id="toCityid_1" class="ht-25 wd-150 pd-0 tx-12-force travelCity">
                                                <option value="">-- Select City --</option>
                                                <c:forEach items="${citylist}" var="citylist">
                                                    <option  value="${citylist.cityId}">${citylist.cityName}</option>
                                                </c:forEach>
                                            </select><span style="color:red;display:none;" id="to_city_error">*required</span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <c:if test="${fn:length(advanceDetails)!=0 && advanceDetails.get(0).depositedAmount != null  && advanceDetails.get(0).depositedAmount != '0'}">
                        <div class="section-tables">
                            <label class="section-title">Advance Details</label>
                            <div class="table-wrapper col-md-6">
                                <table id="datatable1" class="table table-bordered display responsive nowrap">
                                    <thead class="bg-info">
                                        <tr>
                                            <th class="wd-15p">Currency</th>
                                            <th class="wd-15p">Deposited Date</th>
                                            <th class="wd-15p">Deposited Amount(${travelDetails.currencyName})</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${advanceDetails}" var="advdetails" varStatus="index">
                                            <tr>
                                                <td class="wd-15p">${advdetails.currencyCode}</td>
                                                <td class="wd-15p">${advdetails.depositedDate}</td>
                                                <td class="wd-15p amount_alignment">${advdetails.depositedAmount}</td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td colspan="2" style="text-align:center;font-weight: bolder;background: url(./css/images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB"class="wd-15p">Total</td>
                                            <td class="wd-15p amount_alignment">${totalAdvance}</td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${fn:length(compExpDetails)!=0}">
                        <div class="section-tables">
                            <label class="section-title">Expenses Incurred by Company</label>
                            <div class="table-responsive col-md-10">
                                <table id="datatable" class="table table-bordered display responsive nowrap ">
                                    <thead class="bg-info">
                                        <tr>                          
                                            <th class="wd-10p">Category</th>
                                            <th class="wd-10p">INVOICE/ CREDIT NOTE</th>
                                            <th class="wd-10p">Bill No</th>
                                            <th class="wd-10p">Bill Date</th>
                                            <th class="wd-10p">Amount(${travelDetails.currencyName})</th>
                                            <th class="wd-30p">Attachment</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${fn:length(compExpDetails)!=0}">
                                            <c:forEach items="${compExpDetails}" var="conhis" varStatus="index">
                                                <tr id="TM_${index.count}">
                                                    <td class="wd-10p">${conhis.category}</td>
                                                    <td class="wd-10p">${conhis.invoiceStat}</td> 
                                                    <td class="wd-10p">${conhis.billNo}</td> 
                                                    <td class="wd-10p">${conhis.billDate}</td>
                                                    <td class="wd-10p amount_alignment">${conhis.ticTotal}</td>
                                                    <td class="wd-30p">
                                                        <a href="${pageContext.request.contextPath}/downloadFile.htm?file_id=${conhis.fileId}&file_name=${conhis.file_name}" style="color: #1b84e7;" >${conhis.file_name}</a>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                    <div class="section-tables">
                        <label class="section-title">Expense Summary</label>
                        <div class="table-responsive">
                            <table id="datatable" class="table table-bordered display responsive nowrap ">
                                <input type="hidden" name="comtictotal" id="comtictotal" value="${expenseList.ticketTotal}">
                                <input type="hidden" name="comlodtotal" id="comlodtotal" value="${expenseList.lodgingTotal}">
                                <input type="hidden" name="comcontotal" id="comcontotal" value="${expenseList.conveyanceTotal}">
                                <input type="hidden" name="comboartotal" id="comboartotal" value="0">
                                <input type="hidden" name="commistotal" id="commistotal" value="0">
                                <input type="hidden" name="comspenttotal" id="comspenttotal" value="${companyTotal}">
                                <input type="hidden" name="comTicTaxTotalAmount" id="comTicTaxTotalAmount" value="${expenseList.company_ticket_tax_total}">
                                <input type="hidden" name="comLodTaxTotalAmount" id="comLodTaxTotalAmount" value="${expenseList.company_lodging_tax_total}">
                                <input type="hidden" name="comBoarTaxTotalAmount" id="comBoarTaxTotalAmount" value="0">
                                <input type="hidden" name="comConTaxTotalAmount" id="comConTaxTotalAmount" value="${expenseList.conveyanceTotal}">
                                <input type="hidden" name="comMisTaxTotalAmount" id="comMisTaxTotalAmount" value="0">
                                <input type="hidden" name="comspenttotal" id="comspenttotal" value="${expenseList.company_total_amount}">
                                <thead class="bg-info">
                                    <tr>
                                        <th class="wd-10p">Category</th>
                                        <th class="wd-10p">Eligibility</th>
                                        <th class="wd-20p">Spent By Employee</th>
                                        <th class="wd-20p">Spent By Company</th>
                                        <th class="wd-10p">Total Expense( ${travelDetails.currencyName} )</th>
                                        <th class="wd-10p">Deviation</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="wd-10p">Tickets</td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" name="ticEliTot" id="ticEliTot" value="${expenseList.company_ticket_tax_total}">
                                            <span id="tic_eli_tot"> ${expenseList.company_ticket_tax_total}</span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id="tic_act"></span>
                                            <span style="display:none" id="tic_tax_total"></span>
                                            <input type="hidden" name="ticTaxTotal" id="ticTaxTotal" >
                                            <input type="hidden" name="ticTaxTotalAmount" id="ticTaxTotalAmount">
                                            <span id="tic_tax_total_amount"></span>
                                        </td>                                       
                                        <td class="wd-5p amount_alignment">
                                            <span id="">${expenseList.company_ticket_tax_total}</span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <span style="display:none" id="">${expenseList.company_ticket_tax}</span>
                                            <span style="display:none" id="">${expenseList.company_ticket_tax_total}</span>
                                            <input type="hidden" id="ticTotExp" name="ticTotExp" value="${expenseList.company_ticket_tax_total}">                                            
                                            <span id="tic_tot_exp">${expenseList.company_ticket_tax_total}</span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="ticDeviation" name="ticDeviation" value="">
                                            <span id="tic_deviation"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="wd-10p">Lodging</td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" name="lodEliTot" id="lodEliTot">
                                            <span id="lod_eli_tot"></span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id="lod_act"></span>
                                            <span style="display:none" id="lod_tax_total"></span>
                                            <input type="hidden" name="lodTaxTotal" id="lodTaxTotal">
                                            <span id="lod_tax_total_amount"></span>
                                            <input type="hidden" name="lodTaxTotalAmount" id="lodTaxTotalAmount">
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id="">${expenseList.lodgingTotal}</span>
                                            <span style="display:none" id="">${expenseList.company_lodging_tax}</span>
                                            <span id="">${expenseList.company_lodging_tax_total}</span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="lodTotExp" name="lodTotExp" value="${expenseList.company_lodging_tax_total}">
                                            <span id="lod_tot_exp">${expenseList.company_lodging_tax_total}</span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="lodDeviation" name="lodDeviation" value="">
                                            <input type="hidden" id="lodDevPercentage" name="lodDevPercentage" value="">
                                            <span id="lod_deviation"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="wd-10p">Boarding</td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" name="boarEliTot" id="boarEliTot">
                                            <span id="boar_eli_tot"></span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id="boar_act"></span>
                                            <span style="display:none" id="boar_tax_total"></span>
                                            <input type="hidden" name="boarTaxTotal" id="boarTaxTotal">
                                            <span id="boar_tax_total_amount"></span>
                                            <input type="hidden" name="boarTaxTotalAmount" id="boarTaxTotalAmount">
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id=""></span>
                                            <span style="display:none" id=""></span>
                                            <span style="display:none" id=""></span>
                                        </td> 
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="borTotExp" name="borTotExp" value="0">
                                            <span id="bor_tot_exp"></span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="borDeviation" name="borDeviation" value="">
                                            <input type="hidden" id="boarDevPercentage" name="boarDevPercentage" value="">
                                            <span id="bor_deviation"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="wd-10p">Conveyance</td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" name="conEliTot" id="conEliTot" value="${expenseList.conveyanceTotal}">
                                            <span id="con_eli_tot">${expenseList.conveyanceTotal}</span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id="conv_act"></span>
                                            <span style="display:none" id="con_tax_total"></span>
                                            <input type="hidden" name="conTaxTotal" id="conTaxTotal" >
                                            <input type="hidden" name="conTaxTotalAmount" id="conTaxTotalAmount">
                                            <span id="con_tax_total_amount"></span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id=""></span>
                                            <span style="display:none" id=""></span> 
                                            <span id="">${expenseList.conveyanceTotal}</span>
                                        </td> 
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="conTotExp" name="conTotExp" value="${expenseList.conveyanceTotal}">
                                            <span id="con_tot_exp">${expenseList.conveyanceTotal}</span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <span id="con_deviation"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="wd-10p">Miscellaneous</td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" name="misEliTot" id="misEliTot" value="">
                                            <span id="mis_eli_tot"></span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id="mis_act"></span>
                                            <span style="display:none" id="mis_tax_total"></span>
                                            <input type="hidden" name="misTaxTotal" id="misTaxTotal">
                                            <input type="hidden" name="misTaxTotalAmount" id="misTaxTotalAmount">
                                            <span id="mis_tax_total_amount"></span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span style="display:none" id=""></span>
                                            <span style="display:none" id=""></span>
                                            <span id=""></span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="misTotExp" name="misTotExp" value="0">
                                            <span id="mis_tot_exp"></span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="misDeviation" name="misDeviation" value="">
                                            <input type="hidden" id="misDevPercentage" name="misDevPercentage" value="">
                                            <span id="mis_deviation"></span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="wd-10p">Total</td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" name="totEliTot" id="totEliTot" value="${expenseList.company_ticket_tax_total}">
                                            <span id="tot_eli_tot" class="">${expenseList.company_ticket_tax_total}</span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span id="actual_total" style="display:none" class=""></span>
                                            <input type="hidden" name="totalTax" id="totalTax">
                                            <input type="hidden" name="totalTotalAmount" id="totalTotalAmount">
                                            <span style="display:none" id="total_tax"></span>
                                            <span id="total_total_amount"></span>
                                        </td>
                                        <td class="wd-5p amount_alignment">
                                            <span id="" style="display:none" class="">${expenseList.company_total_amount}</span>
                                            <span style="display:none" id="">${expenseList.company_total_tax}</span>
                                            <input type="hidden" name="companyTotalTaxTotal" id="companyTotalTaxTotal" value="${expenseList.company_total_tax_total}">
                                            <span id="">${expenseList.company_total_tax_total}</span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="totTotExp" name="totTotExp" value="${expenseList.company_total_tax_total}">
                                            <span id="tot_tot_exp" class="">${expenseList.company_total_tax_total}</span>
                                        </td>
                                        <td class="wd-10p amount_alignment">
                                            <input type="hidden" id="deviationPercent" name="deviationPercent" value="0">
                                            <input type="hidden" id="totDeviation" name="totDeviation" value="0">
                                            <span id="tot_deviation"></span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="section-tables">
                        <label class="section-title">Details of Expense</label>
                        <div class="table-responsive">
                            <input type="hidden" name="ticTotal" id="ticTotal" value="${expenseList.ticTaxTotalAmount}">
                            <input type="hidden" name="lodtotal" id="lodtotal" value="0">
                            <input type="hidden" name="boartotal" id="boartotal" value="0">
                            <input type="hidden" name="convtotal" id="convtotal" value="0">
                            <input type="hidden" name="mistotal" id="mistotal" value="0">
                            <input type="hidden" name="totalAmount" id="totalAmount"  value="0">
                            <table id="datatable" class="fontalter table table-bordered display responsive nowrap expenseTable">
                                <thead class="bg-info">
                                    <tr>
                                        <th class="wd-10p">Category </th>
                                        <th class="wd-8p">From Date </th>
                                        <th class="wd-8p">To Date </th>
                                        <th class="wd-10p">City </th>
                                        <th class="wd-15p">Bill/Without Bill </th>
                                        <th class="wd-5p">Eligibility</th>
                                        <th class="wd-5p">Bill Date</th>
                                        <th class="wd-5p">Bill No</th>
                                        <th class="wd-5p">Expense(${travelDetails.currencyName}) </th>
                                        <th class="wd-10p">Remarks</th>
                                        <th class="wd-2p">Action</th>
                                    </tr>
                                </thead>
                                <tbody class="tx-12-force">
                                    <tr id="TR_1" class="rowdup">
                                        <td class="wd-10p">
                                            <input type="hidden" name="preCategory" id="preCategory_1" value="0">
                                            <select class="ht-25 wd-90 pd-0 tx-12-force rounded-5 duplicate categoryClass" name="res_category" id="category_1" onchange="checkCategory('category_1');">
                                                <option value="">Select</option>
                                                <c:forEach items="${catList}" var="catlist">
                                                    <option  value="${catlist.configKey}">${catlist.configValue}</option>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none;" id="category_error_1">*required</span>
                                        </td>                                        
                                        <td class="wd-8p">
                                            <input class="form-control ht-25 wd-75 bg-white pd-0 duplicate tx-12-force rounded-5 fromdateClass" type="text" id="fromDate_1" name="res_fromDate" readonly value=""  />
                                            <span style="color:red;display:none;" id="fromdateClass_error_1">Invalid</span>
                                            <span style="color:red;display:none;" id="fromdate_error_1">*required</span>
                                        </td>
                                        <td class="wd-8p">
                                            <input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force duplicate rounded-5 todateClass" type="text" id="toDate_1" name="res_toDate" readonly value=""  />
                                            <span style="color:red;display:none;" id="todateClass_error_1">Invalid</span>
                                            <span style="color:red;display:none;" id="todate_error_1">*required</span>
                                        </td>
                                        <td class="wd-10">
                                            <input type="hidden" name="res_city" id="city_1" value="" class="hiddenbox duplicate" />
                                            
                                            <select name="res_city_id" id="res_city_1" onchange="getEliAmount(1)" class="ht-25 wd-90 pd-0 tx-12-force cityClass">
                                                <option value="">-- Select City --</option>
                                                <c:forEach items="${citylist}" var="citylist">
                                                    <option  value="${citylist.cityId}">${citylist.cityName}</option>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none;" id="city_error_1">*required</span>
                                        </td>
                                        <td class="wd-15p tx-12-force">
                                            <select class="ht-25 wd-90 pd-0 tx-12-force rounded-5 duplicate billClass" onchange="getEliAmount(1)" name="res_bill" id="bill_1">
                                                <option  value="y">With Bill</option>
                                                <option  value="n">Without Bill</option>
                                            </select>
                                        </td>
                                        <td class="wd-5p">
                                            <input class="form-control ht-25 wd-55 pd-0 tx-12-force rounded-5 disableClass" type="text" id="eligibility_1"  name="res_eligibility" value="" readonly/>
                                        </td>
                                        <td class="wd-5p">
                                            <input class="form-control ht-25 wd-75 pd-0 tx-12-force rounded-5 disableClass billdateClass" type="text" id="billDate_1"  name="res_billDate" value="" readonly />
                                        </td>
                                        <td class="wd-5p">
                                            <input class="form-control ht-25 wd-75 pd-0 tx-12-force rounded-5 billnoClass disableClass" type="text" name="res_BillNo" id="billNo_1" >
                                        </td>
                                        <td class="wd-5p">
                                            <input class="form-control ht-25 wd-75 pd-0 tx-12-force rounded-5 billableAmountClass" type="text" name="res_amount" id="billAmount_1" onkeypress="return isNumberKey(event)"  onchange="calculateTotalAndTax(1);">
                                            <input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_tax disableClass" type="hidden" name="tic_tax" onkeypress="return isNumberKey(event)" onchange="calculateTotalAndTax(1);" value = "0"id='tic_tax_1'>
                                            <input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_tot " type="hidden" name="tic_tot" onkeypress="return isNumberKey(event)" id="tic_tot_1"> 
                                        </td>                                        
                                        <td class="wd-10p">
                                            <input class="form-control ht-25 wd-90 pd-0 tx-12-force rounded-5" type="text" name="res_remarks" id="remarks_1" >
                                        </td>
                                        <td class="wd-2p tx-12-force">
                                            <img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/css/images/tm_add.png" onclick="addRow(this, 'addRow');" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <c:if test="${travelDetails.countryId != '113'}">
                        <div class="section-tables">
                            <label class="section-title">Attachments</label>
                            <div class="table-responsive">
                                <table id="datatable2" class="fontalter table table-bordered display responsive nowrap">
                                    <input type="hidden" name="attachmentCount" value="1" id="attachmentCount">
                                    <thead class="bg-info">
                                        <tr>
                                            <th class="wd-10p">Category </th>
                                            <th class="wd-15p">Attachment </th>
                                            <th class="wd-5p">Action</th>
                                        </tr>
                                    </thead>
                                    <tbody class="tx-12-force">
                                        <tr id="TA_1">
                                            <td class="wd-10p">
                                                <select class="ht-25 pd-0 tx-12-force rounded-5 fileCategoryClass" name="file_category_1" id="fileCategory_1">
                                                    <option value="">Select</option>
                                                    <c:forEach items="${catList}" var="catlist">
                                                        <option  value="${catlist.configKey}">${catlist.configValue}</option>
                                                    </c:forEach>
                                                </select>
                                                <span style="color:red;display:none;" id="category_error_1">*required</span>
                                            </td>
                                            <td class="wd-15p">
                                                <input type="hidden" value="0" name="attachment_deleted_1" id="attachment_deleted_1" />
                                                <input class="form-control ht-25 bg-white pd-0 tic_attachment" type="file" id="fileAttachment_1" multiple="true" name="fileAttachment_1" onchange="checkFile(this,1);" value="" />
                                                <span style="color:red;display:none;" id="fileAttachment_error_1"></span>
                                            </td>
                                            <td class="wd-5p"><img class="addRemove" style="padding-left: 10px;" alt="Add" src="${pageContext.request.contextPath}/css/images/tm_add.png" onclick="addAttachmentRow(this);" />
                                            </td>
                                        </tr>
                                    <span style="color:red;display:none;" id="file_error">Please attach all documents</span>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${travelDetails.countryId == '113'}">
                        <input type="hidden" name="attachmentCount" value="0" id="attachmentCount">
                    </c:if>                 
                    <div class="" style="padding : 10px 30px 30px 30px !important; background-color: #fff;">
                        <div class="table-responsive text-center">
                            <input class="backbutton" onclick="javascript:history.go(-1)" type="button" name="submit" value="Back"> &nbsp; &nbsp;
                            <input class="wd-10p rounded-5 bg-info tx-white bd-0 buttons savebutton" style="cursor: pointer;" type="submit" name="save" id="save_btn" value="Save" onclick="return vaidateform(11);"> 
                            <input class="wd-10p rounded-5 bg-info tx-white bd-0 submitbutton" style="cursor: pointer;" type="submit" name="submit" value="Submit" onclick="return vaidateform(12);">
                            <!-- <c:if test="${travelDetails.admin_id == null || travelDetails.admin_id == ''}">                                
                                <input class="wd-10p rounded-5 bg-info tx-white bd-0 buttons savebutton" style="cursor: pointer;" type="submit" name="save" id="save_btn" value="Save" onclick="return vaidateform(11);"> 
                                <c:if test="${travelDetails.adminAction == 'Y'}">
                                    <input class="wd-10p rounded-5 tx-white bd-0 submitbutton" style="cursor: not-allowed;" type="button" name="submit" value="Submit" data-toggle="tooltip" data-placement="right"title="Please check with Admin/Travel desk Team to update the ticket expense details"> 
                                </c:if>
                                <c:if test="${travelDetails.adminAction == 'N'}">
                                    <input class="wd-10p rounded-5 bg-info tx-white bd-0 submitbutton" style="cursor: pointer;" type="submit" name="submit" value="Submit" onclick="return vaidateform(12);">                         
                                </c:if>
                            </c:if>
                            <c:if test="${travelDetails.admin_id != null && travelDetails.admin_id != ''}">
                                <input class="wd-10p rounded-5 bg-info tx-white bd-0 submitbutton" style="cursor: pointer;" type="submit" name="submit" value="Submit" onclick="return vaidateform(12);">                         
                            </c:if> -->
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script> 
        function getMessage(){
            alert("Admin Settlement not yet Done !");
        }
        function getTotalAndTax(){
            var tottax =0;
            var totamount = 0;
            var ticTaxTotal = $('#ticTaxTotal').val();
            var lodTaxTotal = $('#lodTaxTotal').val();
            var boarTaxTotal = $('#boarTaxTotal').val();
            var conTaxTotal = $('#conTaxTotal').val();
            var misTaxTotal = $('#misTaxTotal').val();
                
            var misTaxTotalAmount = $('#misTaxTotalAmount').val();
            var conTaxTotalAmount = $('#conTaxTotalAmount').val();
            var boarTaxTotalAmount = $('#boarTaxTotalAmount').val();
            var lodTaxTotalAmount = $('#lodTaxTotalAmount').val();
            var ticTaxTotalAmount = $('#ticTaxTotalAmount').val();
                                
            if(ticTaxTotal != null && ticTaxTotal != ''){
                tottax = Number(tottax) + Number(ticTaxTotal);
            }
            if(lodTaxTotal != null && lodTaxTotal != ''){
                tottax = Number(tottax) + Number(lodTaxTotal);
            }
            if(boarTaxTotal != null && boarTaxTotal != ''){
                tottax = Number(tottax) + Number(boarTaxTotal);
            }
            if(conTaxTotal != null && conTaxTotal != ''){
                tottax = Number(tottax) + Number(conTaxTotal);
            }
            if(misTaxTotal != null && misTaxTotal != ''){
                tottax = Number(tottax) + Number(misTaxTotal);
            }
            if(ticTaxTotalAmount != null && ticTaxTotalAmount != ''){
                totamount = Number(totamount) + Number(ticTaxTotalAmount);
            }
            if(lodTaxTotalAmount != null && lodTaxTotalAmount != ''){
                totamount = Number(totamount) + Number(lodTaxTotalAmount);
            }
            if(boarTaxTotalAmount != null && boarTaxTotalAmount != ''){
                totamount = Number(totamount) + Number(boarTaxTotalAmount);
            }
            if(conTaxTotalAmount != null && conTaxTotalAmount != ''){
                totamount = Number(totamount) + Number(conTaxTotalAmount);
            }
            if(misTaxTotalAmount != null && misTaxTotalAmount != ''){
                totamount = Number(totamount) + Number(misTaxTotalAmount);
            }
                
            $('#totalTax').val(tottax);
            $('#total_tax').html(tottax);
            $('#totalTotalAmount').val(totamount);
            $('#total_total_amount').html(totamount);
        }
        $('.tic_tax').live('focusout', function(){
            var $row = $(this).closest("tr");
            var $row_id = $row.attr('id');
            var id = $row_id.split('_')[1];
            var tCategory = $('#category_'+id).val();
            var cal = 0;
            var tot = 0;
            $(".expenseTable tbody tr").each(function(){
                var key = (this.id).split('_')[1];
                var category = $('#category_'+key).val();                    
                if(category == tCategory){
                    var tax = $('#tic_tax_'+key).val();
                    var tt = $('#tic_tot_'+key).val();
                    if(tax != null && tax != ''){
                        cal = Number(cal) + Number(tax);
                    }
                    if(tt != null && tt != ''){
                        tot = Number(tot) + Number(tt);
                    }
                }
            });            
            if(tCategory =='250'){
                $('#ticTaxTotal').val(cal);
                $('#ticTaxTotalAmount').val(tot);
                if(cal != 0 && cal >0 ){
                    $('#tic_tax_total').html(cal);
                    $('#tic_tax_total_amount').html(tot);
                }else{
                    $('#tic_tax_total').html('');
                    $('#tic_tax_total_amount').html('');
                }
                ticketTotExpn();
            }else if(tCategory =='251'){
                $('#lodTaxTotal').val(cal);
                $('#lodTaxTotalAmount').val(tot);
                if(cal != 0 && cal >0 ){
                    $('#lod_tax_total').html(cal);
                    $('#lod_tax_total_amount').html(tot);
                }else{
                    $('#lod_tax_total').html('');
                    $('#lod_tax_total_amount').html('');
                }
                lodgingTotExpn();
                lodDeviation()
            } else if(tCategory =='252'){
                $('#boarTaxTotal').val(cal);
                $('#boarTaxTotalAmount').val(tot);
                if(cal != 0 && cal >0 ){
                    $('#boar_tax_total').html(cal);
                    $('#boar_tax_total_amount').html(tot);
                }else{
                    $('#boar_tax_total').html('');
                    $('#boar_tax_total_amount').html('');
                }
                boardingTotExpn();
                boarDeviation();
            } else if(tCategory =='253'){
                $('#conTaxTotal').val(cal);
                $('#conTaxTotalAmount').val(tot);
                if(cal != 0 && cal >0 ){
                    $('#con_tax_total').html(cal);
                    $('#con_tax_total_amount').html(tot);
                }else{
                    $('#con_tax_total').html('');
                    $('#con_tax_total_amount').html('');
                }
                convTotExpn();
            } else if(tCategory =='254'){
                $('#misTaxTotal').val(cal);
                $('#misTaxTotalAmount').val(tot);
                if(cal != 0 && cal >0 ){
                    $('#mis_tax_total').html(cal);
                    $('#mis_tax_total_amount').html(tot);
                }else{
                    $('#mis_tax_total').html('');
                    $('#mis_tax_total_amount').html('');
                }
                misTotExpn();
                misDeviation();
            }
            getTotalAndTax();
            totalExpence();
            totalDeviation();
        });
        function calculateTotalAndTax(row_id){
            var amt  = 0;
            var tx = 0;
            var tot = 0;
            amt  = $('#billAmount_'+row_id).val();
            tx = $('#tic_tax_'+row_id).val();
            if(amt != null && amt !='' && tx != null && tx != ''){
                tot = Number(amt) + Number(tx);
            }else if(amt != null && amt !='' && (tx == null || tx == '')){
                tx=0;
                tot = Number(amt) + Number(tx);
            }else{
                tot=0;
            }
            $('#tic_tot_'+row_id).val(tot);
        }
        function saveVaidateform(stat){
            $('#actionbtn').val(stat);
            if ($('#startdt').val() == '') {
                $('#travelStartDate_error').show();
                return false;
            }else{
                $('#travelStartDate_error').hide();
            }
            if ($('#enddt').val() == '') {
                $('#travelEndDate_error').show();
                return false;
            }else{
                $('#travelEndDate_error').hide();
            }
            var categoryCheck = tableNullCheck("categoryClass");
            if(categoryCheck)
                return true;
            else
                return false;
        }
        function vaidateform(stat){
            var ticflag = false;
            var lodflag = false;
            var boarflag = false;
            var conflag = false;
            var misflag = false;
            
            var ticfile = false;
            var lodfile = false;
            var boarfile = false;
            var confile= false;
            var misfile = false;
            var attachmentStatus = false;
            $('#actionbtn').val(stat);
            if ($('#fromCityid_1').val() == '') {
                $('#from_city_error').show();
                $('#fromCityid_1').focus();
                return false;
            }else{
                $('#from_city_error').hide();
            }
            if ($('#toCityid_1').val() == '') {
                $('#to_city_error').show();
                $('#toCityid_1').focus();
                return false;
            }else{
                $('#to_city_error').hide();
            }
             
            if ($('#startDate').val() == '') {
                $('#travelStartDate_error').show();
                $('#startDate').focus();
                return false;
            }else{
                $('#travelStartDate_error').hide();
            }
            if ($('#endDate').val() == '') {
                $('#travelEndDate_error').show();
                $('#endDate').focus();
                return false;
            }else{
                $('#travelEndDate_error').hide();
            }
            if ($('#travel_start_time').val() == '') {
                $('#travelStartDate_error').show();
                $('#travel_start_time').focus();
                return false;
            }else{
                $('#travelStartDate_error').hide();
            }
            if ($('#travel_end_time').val() == '') {
                $('#travelEndDate_error').show();
                $('#travel_end_time').focus();
                return false;
            }else{
                $('#travelEndDate_error').hide();
            }
            var sd = $('#startDate').val()+' '+$('#travel_start_time').val();
            var ed = $('#endDate').val()+' '+$('#travel_end_time').val();
            var st = $('#travel_start_time').val();
            var et = $('#travel_end_time').val();
            var fd = moment(new Date(sd) , "DD-MMM-YYYY HH:mm:ss").format('YYYY-MM-DD HH:mm:ss');
            var ld = moment(new Date(ed) , "DD-MMM-YYYY HH:mm:ss").format('YYYY-MM-DD HH:mm:ss');
            var categoryCheck = tableNullCheck("categoryClass");
            var cityCheck = tableNullCheck("cityClass");
            //            var fdateCheck = tableNullCheck("fromdateClass");
            //            var tdateCheck = tableNullCheck("todateClass");
            var fdateCheck = toDateCheck("fromdateClass");
            var tdateCheck = toDateCheck("todateClass");
            var billCheck = billCityCheck("billClass");
            var billAmountCheck = tableNullCheck("billableAmountClass");
            $('.categoryClass').each(function() {
                var id = $(this).attr("id");
                var class_name_val = $('#'+id).val();
                if(class_name_val == '250'){
                    var bill = $('#bill_'+id.split("_")[1]).val();
                    if(bill == 'y'){
                        ticflag=true;
                    }
                }else if(class_name_val=='251'){
                    var bill = $('#bill_'+id.split("_")[1]).val();
                    if(bill == 'y'){
                        lodflag = true;
                    }
                }else if(class_name_val=='252'){
                    var bill = $('#bill_'+id.split("_")[1]).val();
                    if(bill == 'y'){
                        boarflag = true;
                    }
                }else if(class_name_val=='253'){
                    var bill = $('#bill_'+id.split("_")[1]).val();
                    if(bill == 'y'){
                        conflag = true;
                    }
                }else if(class_name_val=='254'){
                    var bill = $('#bill_'+id.split("_")[1]).val();
                    if(bill == 'y'){
                        misflag = true;
                    }
                }
            });
            $('.fileCategoryClass').each(function() {
                var id = $(this).attr("id");
                var cate = $('#'+id).val();
                if(cate == 250){
                    var bill = $('#fileAttachment_'+id.split("_")[1]).val();
                    var dele = $('#attachment_deleted_'+id.split("_")[1]).val();
                    //var oldfile = $('#attachments_'+id.split("_")[1]).val();
                    if(bill != null && bill != '' && dele == 0){
                        ticfile=true;
                    }
                }else if(cate=='251'){
                    var bill = $('#fileAttachment_'+id.split("_")[1]).val();
                    var dele = $('#attachment_deleted_'+id.split("_")[1]).val();
                    //var oldfile = $('#attachments_'+id.split("_")[1]).val();
                    if(bill != null && bill != '' && dele == 0){
                        lodfile = true;
                    }
                }else if(cate=='252'){
                    var bill = $('#fileAttachment_'+id.split("_")[1]).val();
                    var dele = $('#attachment_deleted_'+id.split("_")[1]).val();
                    if(bill != null && bill != '' && dele == 0){
                        boarfile = true;
                    }
                }else if(cate=='253'){
                    var bill = $('#fileAttachment_'+id.split("_")[1]).val();
                    var dele = $('#attachment_deleted_'+id.split("_")[1]).val();
                    if(bill != null && bill != '' && dele == 0){
                        confile = true;
                    }
                }else if(cate=='254'){
                    var bill = $('#fileAttachment_'+id.split("_")[1]).val();
                    var dele = $('#attachment_deleted_'+id.split("_")[1]).val();
                    if(bill != null && bill != '' && dele == 0){
                        misfile = true;
                    }
                }
            })
            var country_id = $("#countryId").val();
            if( country_id != '113'){
                if((ticflag == ticfile) && (lodflag == lodfile) && (boarflag == boarfile) && (conflag == confile) && (misflag == misfile) ){
                    $('#file_error').hide();
                    attachmentStatus = true;
                }else{
                    $('#file_error').show();
                }
            }else{
                $('#file_error').hide();
                attachmentStatus = true;
            }
            
            if(categoryCheck && cityCheck && fdateCheck && tdateCheck && billCheck && billAmountCheck && attachmentStatus){
                $('.disableClass').attr('disabled', false);
                $('.preloader').css("display","block");
                $('#startDate').val(fd);
                $('#endDate').val(ld);
                return true;                
            }else{
                return false;
            }
           
        }
        function toDateCheck(class_name) {
            var strdate = $('#startDate').val();
            var endate = $('#endDate').val();
            var strd = moment(new Date(strdate) , "DD-MMM-YYYY").format('DD-MMM-YYYY');
            var endd = moment(new Date(endate) , "DD-MMM-YYYY").format('DD-MMM-YYYY');
            var NullError = 0;
            $('.'+class_name).each(function (){
                var class_name_val = this.value;
                var key =(this.id).split('_')[1];
                var class_name_val_length = $.trim(class_name_val).length;
                if (class_name_val_length > 0) {
                    if(new Date(strd) <= new Date(class_name_val) && new Date(class_name_val) <= new Date(endd)){
                        $('#'+class_name+'_error_'+key).hide();
                        $(this).css('outline', 'none');
                    }else{
                        $('#'+class_name+'_error_'+key).show();                        
                        //document.querySelectorAll("."+class_name)[i].style.outline = "1px solid red";
                        NullError++;
                    }
                } else {
                    $(this).css('outline', '1px solid red');
                    //document.querySelectorAll("."+class_name)[i].style.outline = "1px solid red";
                    NullError++;
                }
            })
            if (NullError <= 0) {
                return true;
            } else if (NullError > 0) {
                return false;
            }
        }
        function billCityCheck(class_name) {
            var NullError = 0;
            var class_length = document.querySelectorAll("." + class_name).length;
            for (var i = 0; i < class_length; i++) {
                var class_name_val = document.querySelectorAll("." + class_name)[i].value;
                var class_name_val_length = $.trim(class_name_val).length;
                if (class_name_val_length > 0) {
                    document.querySelectorAll("." + class_name)[i].style.outline = "none";
                    if(class_name_val == 'y'){
                        var billdate_val = document.querySelectorAll(".billdateClass")[i].value;
                        var billno_val = document.querySelectorAll(".billnoClass")[i].value;
                        var billdate_val_length = $.trim(billdate_val).length;
                        var billno_val_length = $.trim(billno_val).length;
                        if (billdate_val_length > 0) {
                            document.querySelectorAll(".billdateClass")[i].style.outline = "none";    
                        }else{
                            document.querySelectorAll(".billdateClass")[i].style.outline = "1px solid red";
                            NullError++;
                        }
                        if (billno_val_length > 0) {
                            document.querySelectorAll(".billnoClass")[i].style.outline = "none";    
                        }else{
                            document.querySelectorAll(".billnoClass")[i].style.outline = "1px solid red";
                            NullError++;
                        }
                    }
                } else {
                    document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                    NullError++;
                }
            }
            if (NullError <= 0) {
                return true;
            } else if (NullError > 0) {
                return false;
            }
        }       
      
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
            if (NullError <= 0) {
                return true;
            } else if (NullError > 0) {
                return false;
            }
        }
    </script>
</html>
