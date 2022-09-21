<%-- 
    Document   : adminTravelSettlement
    Created on : Jun 11, 2018, 4:08:20 PM
    Author     : 16113
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
<link type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" rel="stylesheet" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <style>
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
            .table th, .table td{
                padding:0.15rem;
            }
            .col-sm tr td{
                width:16.66%;
                padding: 3px;
            }
            .accordion-one .card-header a::before {
                top: 8px;
            }
             #datatable1 td{
                padding:0.25rem !important;
            }
            #datatable1{
                border-collapse: inherit;
            }
            .section-tables{
                padding: 10px 30px 10px 30px !important;                
                background-color: #fff;
                font-family: "Roboto", "Helvetica Neue", Arial, sans-serif;
            }
            .bg-info{
                text-align: center;
            }
            .Hotel-tables{
                padding: 50px 30px 10px 30px !important;                
                background-color: #fff;
                font-family: "Roboto", "Helvetica Neue", Arial, sans-serif;
            }
            .readonlyclass{
                padding: 3px !important;
                height: 28px !important;
                width: 160px !important;
            }
            .section-title {
                margin-top: 0px !important;
            }
            input.submitbutton {
                background: url(./css/images/icon_btn_submit.png) no-repeat scroll 8px 8px #3579c6;
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
            .section-title{
                color: #1b84e7 !important;
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
        <script>
            $(document).ready(function () {
                
//                $('.tic_tax').live('focusout', function(){
//                    var $row = $(this).closest("tr");
//                    var $row_id = $row.attr('id');
//                    var split = $row_id.split('_');
//                    var amt  = 0;
//                    var tx = 0;
//                    var tot = 0;
//                    amt  = $('#tic_billAmount_'+split[1]).val();
//                    tx = $('#tic_tax_'+split[1]).val();
//                    if(amt != null && amt !='' && tx != null && tx != ''){
//                        tot = tot + amt + tx;
//                    }else{
//                         if(tx != null && tx != ''){
//                            tot = Number(tot) + Number(tx);
//                        }
//                    }
//                    $('#tic_tot_'+split[1]).val(tot);
//         });
                
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
                    $(".cityClass").autocomplete("${pageContext.request.contextPath}/citySearch.htm?type=${travelDetails.countryId}", {
                        minChars: 2,
                        matchContains: true
                    });
                    $(".cityClass").result(function(event, data) {
                        if (data) {
                            $(this).parent().find(".hiddenbox").val(data[1]);
                        }
                    });
                }
                 travelAutoComplet = function() {
                    $(".travelCity").autocomplete("${pageContext.request.contextPath}/citySearch.htm?type=${travelDetails.countryId}", {
                        minChars: 2,
                        matchContains: true
                    });
                    $(".travelCity").result(function(event, data) {
                        if (data) {
                            $(this).parent().find(".hiddenbox").val(data[1]);                            
                        }
                    }); 
                }                
                travelAutoComplet();
                myAutoComplet();
                $(".tic_billDate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
                $(".tic_tdate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
                $(".hot_billDate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
                $(".con_billDate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
                $(".con_fdate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
                $(".con_tdate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
            });
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
                        <li class="breadcrumb-item"><a style="color: #1b84e7;" href="adminList.htm?type=pending&approver_type=${approver_type}">Settlement List</a></li>
                    </ol>
                    <h6 class="slim-pagetitle">Travel Settlement</h6>
                </div>
                <form name="settlementPage" action="addAdminSettlement.htm" method="POST" enctype="multipart/form-data" autocomplete="off">
                     <input type="hidden" id="totalCount" value="1" />
                     <input type="hidden" name="actionbtn" id="actionbtn" value=""/>
                            <input type="hidden" id="masterId" name="masterId" value="${travelDetails.masterId}" />
                            <input type="hidden" id="empId" name="empId" value="${travelDetails.empId}" />
                            <input type="hidden" name="approver_type" value="${approver_type}"/>
                            <input type="hidden" name="type" value="${type}"/>
                            <input type="hidden" id="countryId" name="countryId" value="${travelDetails.countryId}">
                            <input type="hidden" id="ticketRefId" name="ticketRefId" value="${travelDetails.ticketRefId}" />
                            <c:choose>
                                <c:when test="${!empty(adminSetlmTicDetails)}">
                                    <input type="hidden" name="ticCount" id="ticCount" value="${fn:length(adminSetlmTicDetails)}" />
                                    <input type="hidden" name="ticdelCount" id="ticdelCount" value="${fn:length(adminSetlmTicDetails)}" />
                                </c:when>
                                     <c:when test="${empty(adminSetlmTicDetails)}">
                                    <input type="hidden" name="ticCount" id="ticCount" value="1" />
                                    <input type="hidden" name="ticdelCount" id="ticdelCount" value="0" />
                                </c:when>  
                            </c:choose>
                            <c:choose>
                                <c:when test="${!empty(adminSetlmHotelDetails)}">
                                    <input type="hidden" name="hotCount" id="hotCount" value="${fn:length(adminSetlmHotelDetails)}" />
                                    <input type="hidden" name="hotdelCount" id="hotdelCount" value="${fn:length(adminSetlmHotelDetails)}" />
                                </c:when>
                                    <c:when test="${empty(adminSetlmHotelDetails)}">
                                    <input type="hidden" name="hotCount" id="hotCount" value="1" />
                                    <input type="hidden" name="hotdelCount" id="hotdelCount" value="0" />
                                </c:when>
                            </c:choose>
                            <c:choose>
                                <c:when test="${!empty(adminSetlmConvDetails)}">
                                    <input type="hidden" name="conCount" id="conCount" value="${fn:length(adminSetlmConvDetails)}" />
                                    <input type="hidden" name="delCount" id="conCount" value="${fn:length(adminSetlmConvDetails)}" />
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="conCount" id="conCount" value="1" />
                                    <input type="hidden" name="delCount" id="delCount" value="0" />
                                </c:otherwise>
                            </c:choose>
                    
                    <div class="section-tables">
                        <h6 class="slim-pagetitle mg-b-20" style="padding:0px;border:0px;text-align: center;">Travel ID - ${travelDetails.ticketRefId}</h6>
                        <div id="accordion" class="accordion-one" role="tablist" aria-multiselectable="true">
                            <div class="card">
                                <div class="card-header pd-0-force" role="tab" id="headingOne">
                                    <a class="collapsed tx-gray-800 transition pd-5-force" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                        <!--<a data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="false" aria-controls="collapseOne" class="tx-gray-800 collapsed transition pd-5-force">-->
                                        Employee Details
                                    </a>
                                </div>
                                <div id="collapseOne" class="collapse" role="tabpanel" aria-labelledby="headingOne">
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
                                <div id="collapseTwo" class="collapse" role="tabpanel" aria-labelledby="headingTwo">
                                    <div class="card-body">
                                        <table class="col-sm">
                                            <tbody>
                                         <tr>
                                             <td><strong class="tx-inverse tx-medium">Start Date</strong></td>
                                             <td><span class="text-muted">${travelDetails.travelStartDate}</span></td>
                                             <td><strong class="tx-inverse tx-medium">End Date</strong></td>
                                             <td><span class="text-muted">${travelDetails.travelEndDate}</span></td>
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
                                        <td><span class="text-muted">${travelDetails.clientReimbursable}</span></td>
                                        <td><strong class="tx-inverse tx-medium">Purpose of Travel</strong></td>
                                        <td><span class="text-muted">${travelDetails.clientReimbursable}</span></td>
                                    </tr>
                                </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>            
                        </div><!-- accordion -->                        
                    </div>
                    <c:if test="${fn:length(adminSetlmTicDetails)!=0}">
                        <div class="section-tables">
                            <label class="section-title">Expenses Incurred by Company</label>
                            <label class="section-title">Ticket</label>
                            <div class="table-responsive">
                                <table id="datatable3" class="table table-bordered display responsive nowrap ">
                                    <thead class="bg-info">
                                        <tr>
                                            <th rowspan="2" class="wd-5p">From City</th>
                                            <th rowspan="2" class="wd-5p">To City</th>
                                            <th rowspan="2" class="wd-5p">Travel Date</th>
                                            <th rowspan="2" class="wd-5p">Bill Date</th>
                                            <th rowspan="2" class="wd-5p">Document No</th>
                                            <th rowspan="2" class="wd-5p">Invoice/ Credit Note</th>
                                            <th colspan="3" class="wd-8p" style="border-bottom:rgba(255, 255, 255, 0.5) solid 1px">Expense</th>
                                            <th rowspan="2" class="wd-10p">Remarks</th>
                                            <th rowspan="2" class="wd-20p">Attachment</th>
                                            <th rowspan="2" class="wd-2p">Action</th>
                                        </tr>
                                        <tr>
                                            <th class="wd-5p">Amount</th>
                                            <th class="wd-5p">Tax</th>
                                            <th class="wd-5p">Total</th>
                                        </tr>
                                        
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${adminSetlmTicDetails}" var="tichis" varStatus="index">
                                        <tr id="TT_${index.count}">
                                        <input type="hidden" name="res_tic_id" id="tic_id_${index.count}" value="${tichis.ticketId}">
                                        <input type="hidden" name="res_tic_category" id="tic_category_${index.count}" value="${tichis.category}">
                                       
                                        <td class="wd-5p">
                                        <input type="hidden" name="res_tic_fcity" id="res_tic_fcity_${index.count}" value="${tichis.fcityId}" class="hiddenbox" />
                                        <input type="text" name="res_fcity_id" id="res_fcity_${index.count}" class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 ticketClass cityClass" value="${tichis.fromCity}">
                                         </td>
                                         <td class="wd-5p">
                                        <input type="hidden" name="res_tic_tcity" id="res_tic_tcity_${index.count}" value=" ${tichis.tcityId}" class="hiddenbox" />
                                        <input type="text" name="res_tcity_id" id="res_tcity_${index.count}" class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 tocityClass cityClass" value="${tichis.toCity}">
                                         </td>
                                        <td class="wd-5p">
                                            <input type="text" class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 tic_tdate" name="res_tic_tdate" id="tic_tdate_${index.count}" value="${tichis.travelDate}">
                                        </td>                                     
                                        <td class="wd-5p"><input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 tic_billDate" type="text" id="tic_billDate_${index.count}"  name="res_tic_billDate" value="${tichis.billDate}" readonly /></td>
                                        <td class="wd-5p"><input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 tic_billNo" type="text" name="res_tic_billNo" value="${tichis.billNo}" id="tic_billNo_${index.count}"></td>
                                        <td class="wd-5p">
                                            <select class="ht-25 wd-85 pd-0 tx-12-force rounded-5 invoice" name="tic_invoice" id="invoice_${index.count}">
                                                 <option  value="">--Select--</option>
                                                <option  value="I">Invoice</option>
                                                 <option  value="C">Credit Note</option>
                                            </select>
                                        </td>
                                        <td class="wd-5p"><input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_billAmount" type="text" name="res_tic_billAmount" value="${tichis.amount}" onkeypress="return isNumberKey(event)" onchange="calculateTotalAndTax(${index.count});" id="tic_billAmount_${index.count}"> </td> 
                                        <td class="wd-5p"><input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_tax" type="text" name="tic_tax" onkeypress="return isNumberKey(event)" value="${tichis.tax}" onchange="calculateTotalAndTax(${index.count});" id='tic_tax_${index.count}'> </td> 
                                        <td class="wd-5p"><input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_tot" type="text" name="tic_tot" onkeypress="return isNumberKey(event)" value="${tichis.ticTotal}" readonly="true" id="tic_tot_${index.count}"> </td> 
                                        <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 tic_remarks" type="text" name="res_tic_remarks" id="tic_remarks_${index.count}"> </td>
                                        <td class="wd-20p">
                                            <input type="hidden" value="0" name="tic_attachment_deleted_${index.count}" id="tic_attachment_deleted_${index.count}" />
                                            <input class="form-control ht-25 bg-white pd-0 tic_attachment" type="file" id="tic_attachment_${index.count}" onchange="checkTicFile(this,${index.count});" name="tic_attachment_${index.count}" value="" />
                                            <span style="color:red;display:none;" id="ticImageUpload_error_${index.count}"></span>
                                        </td>
                                        <td class="wd-2p">                                            
                                            <i class="fa fa-plus pd-l-10" style="padding-left: 25px" onclick="addTicRow(this);" ></i>
<!--                                            <img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/css/images/tm_add.png" onclick="addTicRow(this);" />-->
                                       
                                        </td>
                                        </tr>    
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                    <c:if test="${fn:length(adminSetlmHotelDetails)!=0}">
                        <div class="section-tables">
                            <label class="section-title">Lodging</label>
                            <div class="table-responsive">
                                <table id="datatable4" class="table table-bordered display responsive nowrap ">
                                    <thead class="bg-info">
                                        <tr>
                                            <th rowspan="2" class="wd-5p">City</th>
                                            <th rowspan="2" class="wd-5p">From Date</th>
                                            <th rowspan="2" class="wd-5p">To Date</th>
                                            <th rowspan="2" class="wd-5p">Paid By</th>
                                            <th rowspan="2" class="wd-5p">Bill Date</th>
                                            <th rowspan="2" class="wd-5p">Document No</th>
                                            <th rowspan="2" class="wd-8p">Invoice/ Credit Note</th>
                                            <th colspan="3" class="wd-10p" style="border-bottom:rgba(255, 255, 255, 0.5) solid 1px">Expense</th>
                                            <th rowspan="2" class="wd-5p">Remarks</th>
                                            <th rowspan="2" class="wd-15p">Attachment</th>
                                            <th rowspan="2" class="wd-2p">Action</th>
                                        </tr>
                                        <tr>
                                            <th class="wd-5p">Amount</th>
                                            <th class="wd-5p">Tax</th>
                                            <th class="wd-5p">Total</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${adminSetlmHotelDetails}" var="hothis" varStatus="index">
                                            <tr id="TH_${index.count}">
                                                <input type="hidden" name="res_hot_category" id="hot_category_${index.count}" value="${hothis.category}">
                                                <input type="hidden" name="res_hot_id" id="hot_id_${index.count}" value="${hothis.ticketId}">                                        
                                            <td class="wd-5p">
                                                <input type="hidden" name="res_hot_city" id="res_hot_city_${index.count}" value="${hothis.cityId}" class="hiddenbox" />
                                                <input type="text" name="hot_fcity_id" id="hot_city_${index.count}" class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 hotelClass cityClass" value="${hothis.cityName}">
                                            </td>
                                            <td class="wd-5p">
                                                <input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 hot_billDate" readonly value="${hothis.fromDate}" type="text" name="res_hot_fdate"  id="hot_fdate_${index.count}">
                                            </td>
                                            <td class="wd-5p">
                                               <input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 hot_billDate" readonly type="text" name="res_hot_tdate" value="${hothis.toDate}" id="hot_tdate_${index.count}"> 
                                           </td>
                                           <td class="wd-5p">
                                               <select class="ht-25 wd-90 pd-0 tx-12-force rounded-5 paidBy" name="paid_by" id="payed_by_${index.count}" onchange="addReadOnly('${index.count}')">
                                                    <option  value="">--Select--</option>
                                                   <option  value="e">Employee</option>
                                                    <option  value="c">Company</option>
                                                </select>
                                            </td>
                                            <td class="wd-5p"><input class="form-control ht-25 wd-75 pd-0 tx-12-force rounded-5 lod_billDate hot_billDate disableClass" type="text" id="hot_billDate_${index.count}"  name="res_hot_billDate" /></td> 
                                            <td class="wd-5p"><input class="form-control ht-25 wd-75 pd-0 rounded-5 hot_billNo disableClass" type="text" name="res_hot_billNo" id="hot_billNo_${index.count}"> </td>
                                            <td class="wd-8p">
                                               <select class="ht-25 wd-90 pd-0 tx-12-force rounded-5 invoice disableClass" name="hot_invoice" id="hot_invoice_${index.count}">
                                                    <option  value="">--Select--</option>
                                                   <option  value="I">Invoice</option>
                                                    <option  value="C">Credit Note</option>
                                                </select>
                                            </td>
                                            <td class="wd-5p"><input class="form-control ht-25 wd-55 pd-0 rounded-5 hot_billAmount disableClass" type="text" name="res_hot_billAmount" value="${hothis.amount}" onkeypress="return isNumberKey(event)" onchange="hotelTotalAndTax(${index.count});"  id="hot_billAmount_${index.count}"> </td> 
                                            <td class="wd-5p"><input class="form-control ht-25 wd-55 pd-0 rounded-5 hot_tax disableClass" type="text" name="hot_tax" onkeypress="return isNumberKey(event)" value="${hothis.tax}" onchange="hotelTotalAndTax(${index.count});" id="hot_tax_${index.count}"> </td> 
                                            <td class="wd-5p"><input class="form-control ht-25 wd-55 bg-white pd-0 rounded-5 hot_tot disableClass" type="text" readonly="true" name="hot_tot" value="${hothis.ticTotal}" onkeypress="return isNumberKey(event)" id="hot_tot_${index.count}"> </td> 
                                            <td class="wd-10p"><input class="form-control ht-25 wd-75 bg-white pd-0 rounded-5 " type="text" name="res_hot_remarks" id="hot_remarks_${index.count}"> </td>
                                            <td class="wd-15p">
                                                <input type="hidden" value="0" name="hot_attachment_deleted_${index.count}" id="hot_attachment_deleted_${index.count}" />
                                                <input class="form-control ht-25 pd-0 hot_attachment disableClass" type="file" id="hot_attachment_${index.count}" multiple="true" onchange="checkHotFile(this,${index.count});" name="hot_attachment_${index.count}" value="" />
                                                <span style="color:red;display:none;" id="hotImageUpload_error_${index.count}"></span>
                                            </td>
                                            <td class="wd-2p"><i class="fa fa-plus pd-l-10" style="padding-left: 25px" onclick="addLodRow(this);" ></i>
    <!--                                            <img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/css/images/tm_add.png" onclick="addLodRow(this);" />-->
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                 <!--
                    <c:if test="${fn:length(adminSetlmConvDetails)!=0}">
                        <div class="section-tables">
                            <div class="table-responsive">
                                <table id="datatable2" class="table table-bordered display responsive nowrap ">
                                    <thead class="bg-info">
                                        <tr>
                                            <th class="wd-10p">Category</th>
                                            <th class="wd-10p">City</th>
                                            <th class="wd-10p">From Date</th>
                                            <th class="wd-10p">To Date</th>
                                            <th class="wd-10p">Bill Date</th>
                                            <th class="wd-10p">Bill No</th>
                                            <th class="wd-10p">Amount</th>
                                            <th class="wd-10p">Remarks</th>
                                            <th class="wd-10p">Attachment</th>
                                            <th class="wd-10p">
                                                <i class="fa fa-plus" style="padding-left: 32px" onclick="addRow(this);" ></i>
    <!--                                            <img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/css/images/tm_add.png" onclick="addRow(this);" />
                                            </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:if test="${fn:length(adminSetlmConvDetails)!=0}">
                                            <c:forEach items="${adminSetlmConvDetails}" var="conhis" varStatus="index">
                                                <tr id="TR_${index.count}">
                                            <input type="hidden" name="con_deleted" id="deletedTR_${index.count}" value="0"/>
                                            <input type="hidden" name="res_con_id" id="con_id_${index.count}" value="${conhis.ticketId}">
                                            <input type="hidden" name="res_con_fdate"  id="con_fdate_${index.count}">
                                            <input type="hidden" id="con_tdate_${index.count}"  name="res_con_tdate" />
                                            <td class="wd-10p">Conveyance</td>
                                                <td class="wd-10p">
                                                    <input type="hidden" name="res_city" id="city_${index.count}" value=" ${conhis.cityId}" class="hiddenbox" />
                                                <input type="text" name="res_city_id" id="res_city_${index.count}" class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 convClass cityClass" value=" ${conhis.cityName}">
                                                </td>
                                            <td class="wd-10p">${conhis.fromDate}</td>
                                            <td class="wd-10p">${conhis.toDate}</td>
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 con_billDate" type="text" id="con_billDate_${index.count}"  name="res_con_billDate" readonly /></td> 
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5 con_billNo" type="text" name="res_con_billNo" id="con_billNo_${index.count}"> </td> 
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5 con_billAmount" type="text" name="res_con_billAmount" onkeypress="return isNumberKey(event)" id="con_billAmount_${index.count}"> </td> 
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5 " type="text" name="res_con_remarks" id="con_remarks_${index.count}"> </td>
                                            <td class="wd-10p">
                                                <input type="hidden" value="0" name="con_attachment_deleted_${index.count}" id="con_attachment_deleted_${index.count}" />
                                                <input class="form-control ht-25 wd-70 bg-white pd-0 con_attachment" type="file" id="con_attachment_${index.count}" multiple="true" name="con_attachment_${index.count}" value="" />
                                            </td>
                                            <td class="wd-10p"></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    <c:if test="${fn:length(adminSetlmConvDetails)!=0}">
                                        <tr id="TR_1">
                                            <td class="wd-10p">
                                                <input type="hidden" name="res_con_id" id="con_id_1" value="0">
                                                <input type="hidden" name="con_deleted" id="deletedTR_1" value="0"/>
                                                Conveyance</td>
                                            <td class="wd-10p">
                                                <input type="hidden" name="res_city" id="city_1" value="" class="hiddenbox" />
                                                <input type="text" name="res_city_id" id="res_city_1" class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 convClass cityClass" value="" >
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white tx-12-force pd-0 rounded-5 con_fdate" type="text" name="res_con_fdate"  id="con_fdate_1"></td>
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white tx-12-force pd-0 rounded-5 con_fdate" type="text" name="res_con_tdate"  id="con_tdate_1"></td>
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 con_billDate" type="text" id="con_billDate_1"  name="res_con_billDate" readonly/></td> 
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5 con_billNo" type="text" name="res_con_billNo" id="con_billNo_1"> </td> 
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5 con_billAmount" type="text" name="res_con_billAmount" onkeypress="return isNumberKey(event)" id="con_billAmount_1"> </td> 
                                            <td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5" type="text" name="res_con_remarks" id="con_remarks_1"> </td>
                                            <td class="wd-10p">
                                                <input type="hidden" value="0" name="con_attachment_deleted_1" id="con_attachment_deleted_1" />
                                                <input class="form-control ht-25 wd-70 bg-white pd-0 con_attachment" type="file" id="con_attachment_1" multiple="true" name="con_attachment_1" value="" /></td>
                                            <td class="wd-10p">
                                                <i class="fa fa-minus pd-l-10" style="padding-left: 32px" id="remove_1" onClick="deleteRow(1)" ></i>
    <!--                                            <a onClick="deleteRow(1)" id="remove_1" ><img src="${pageContext.request.contextPath}/css/images/tm_delete.png" /></a></td>
                                        </tr>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </c:if>
                                    
                          -->          
                    <div class="section-tables">
                        <div class="table-responsive text-center" style="margin-bottom: 20px;">
                            <input class="wd-10p rounded-5 bg-info tx-white bd-0 buttons savebutton" style="cursor: pointer;" type="submit" name="save" id="save_btn" value="Save"> 
                            <input class="wd-10p rounded-5 tx-white bd-0 submitbutton" type="submit" name="submit" value="Submit" onclick="return vaidateform();">
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <script>        
          function calculateTotalAndTax(row_id){
            var amt  = 0;
            var tx = 0;
            var tot = 0;
            amt  = $('#tic_billAmount_'+row_id).val();
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
        function hotelTotalAndTax(row_id){
            var amt  = 0;
            var tx = 0;
            var tot = 0;
            amt  = $('#hot_billAmount_'+row_id).val();
            tx = $('#hot_tax_'+row_id).val();
            if(amt != null && amt !='' && tx != null && tx != ''){
                tot = Number(amt) + Number(tx);
            }else if(amt != null && amt !='' && (tx == null || tx == '')){
                tx=0;
                tot = Number(amt) + Number(tx);
            }else{
                tot=0;
            }
            $('#hot_tot_'+row_id).val(tot);
        }
        function checkHotFile(input, id) {
            $('#hotImageUpload_error_'+id).hide();
            if (input.files && input.files[0]) {
                console.log('inside1');
                var fileUpload = document.getElementById("hot_attachment_"+id);
                var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+(.pdf)$");
                console.log('regex'+regex);
                console.log(fileUpload.value.toLowerCase());
                if (regex.test(fileUpload.value.toLowerCase())) {
                } else {
                    $('#hot_attachment_'+id).val('');
                    $('#hotImageUpload_error_'+id).html('pdf format only');
                    $('#hotImageUpload_error_'+id).show();
                    return false;
                }       
            }
        }       
        function checkTicFile(input, id) {
            $('#ticImageUpload_error_'+id).hide();
            if (input.files && input.files[0]) {
                console.log('inside1');
                var fileUpload = document.getElementById("tic_attachment_"+id);
                var regex = new RegExp("([a-zA-Z0-9\s_\\.\-:])+(.pdf)$");
                console.log('regex'+regex);
                console.log(fileUpload.value.toLowerCase());
                if (regex.test(fileUpload.value.toLowerCase())) {
//                    var size = parseFloat(input.files[0].size / 1024).toFixed(2);
//                    if(size > 2048){
//                        $('#fullImageUpload').val('');
//                        alert("Please upload below 2MB image.");
//                        return false;
//                    }else{
//                        var reader = new FileReader();
//                        reader.readAsDataURL(fileUpload.files[0]);
//                        reader.onload = function (e) {
//                            var image = new Image();
//                            image.src = e.target.result;
//                            image.onload = function () {
//                                var height = this.height;
//                                var width = this.width;
//                                if (height < 1528  || width < 1081) {                        
//                                    $('#blahfull').attr('src', e.target.result);                                                 
//                                    return true;
//                                }else{
//                                    $('#fullImageUpload').val('');
//                                    $('#fullImageUpload_error').html('File size should be 1080*1520');
//                                    $('#fullImageUpload_error').show();
//                                    return false;
//                                }
//                            };                    
//                        }
//                    }                          
                } else {
                    $('#tic_attachment_'+id).val('');
                    $('#ticImageUpload_error_'+id).html('pdf format only');
                    $('#ticImageUpload_error_'+id).show();
                    return false;
                }       
            }
        }
        
        function addReadOnly(row){
             var sel = $('#payed_by_'+row).val();
             console.log(sel);
             if(sel == 'e' ){
                  $('#TH_'+row).find('.disableClass').val('');
                 $('#TH_'+row).find('.disableClass').attr('disabled', true);
//                 $('#hot_billNo_'+row).attr('disabled', true);
//                 $('#hot_billAmount_'+row).attr('disabled', true);
//                 $('#hot_attachment_'+row).attr('disabled', true);
//                 $('#hot_billDate_'+row).attr('disabled', true);
//                $('#hot_tax_'+row).attr('disabled', true);
//                $('#hot_invoice_'+row).attr('disabled', true);
                 
             }else if(sel == '' || sel == 'c'){
                 $('#TH_'+row).find('.disableClass').attr('disabled', false);
//                 $('#hot_billNo_'+row).attr('disabled', false);
//                 $('#hot_billAmount_'+row).attr('disabled', false);
//                 $('#hot_attachment_'+row).attr('disabled', false);
//                 $('#hot_billDate_'+row).attr('disabled', false);  
//                 $('#hot_tax_'+row).attr('disabled', false);
//                 $('#hot_invoice_'+row).attr('disabled', false);
             }
        }
        function addLodRow(rowObject){
            var theRow = $(rowObject).parent().parent();
            var rowData = "";
            var ticCount = $('#hotCount').val();
            cnt = parseInt(ticCount) + 1;
            $('#hotCount').val(cnt);
            rowData += '<tr id=TH_'+cnt+'>';           
            rowData += '<input type="hidden" name="res_hot_category" id=hot_category_'+cnt+' value="">';
            rowData += '<input type="hidden" name="res_hot_id" id="hot_id_'+cnt+' value="">';
            rowData += '<td class="wd-5"><input type="hidden" name="res_hot_city" id=res_hot_city_'+cnt+' value="" class="hiddenbox" />';
            rowData += '<input type="text" name="hot_fcity_id" id=hot_city_'+cnt+' class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 hotelClass cityClass" value=""></td>';
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 hot_billDate" readonly type="text" name="res_hot_fdate"  id=hot_fdate_'+cnt+'></td>';
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 hot_billDate" readonly type="text" name="res_hot_tdate"  id=hot_tdate_'+cnt+'></td>';
            rowData += '<td class="wd-5p">';
            rowData += '<select class="ht-25 wd-90 pd-0 tx-12-force rounded-5 paidBy" name="paid_by" id="payed_by_'+cnt+'" onchange=addReadOnly('+cnt+')>';
            rowData += '<option  value="">--Select--</option>';
            rowData += '<option  value="e">Employee</option>';
            rowData += '<option  value="c">Company</option> </select></td>';
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 pd-0 tx-12-force rounded-5 lod_billDate hot_billDate disableClass" type="text" id=hot_billDate_'+cnt+'  name="res_hot_billDate" /></td>';
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 pd-0 rounded-5 hot_billNo disableClass" type="text" name="res_hot_billNo" id=hot_billNo_'+cnt+'> </td> ';
            rowData += '<td class="wd-8p">';
            rowData += '<select class="ht-25 wd-90 pd-0 tx-12-force rounded-5 invoice disableClass" name="hot_invoice" id=hot_invoice_'+cnt+'>';
            rowData += '<option  value="">--Select--</option>';
            rowData += '<option  value="I">Invoice</option><option  value="C">Credit Note</option>';
            rowData += '</select></td>';
            rowData += '<td class="wd-8p"><input class="form-control ht-25 wd-55 pd-0 rounded-5 hot_billAmount disableClass" type="text" name="res_hot_billAmount" onkeypress="return isNumberKey(event)" onchange="hotelTotalAndTax('+cnt+');"  id=hot_billAmount_'+cnt+'></td>';
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-55 pd-0 rounded-5 hot_tax disableClass" type="text" name="hot_tax" onkeypress="return isNumberKey(event)" onchange="hotelTotalAndTax('+cnt+');"  id=hot_tax_'+cnt+'> </td>';
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-55 pd-0 bg-white rounded-5 hot_tot disableClass" type="text" readonly="true" name="hot_tot" onkeypress="return isNumberKey(event)" id=hot_tot_'+cnt+'> </td>';
            rowData += '<td class="wd-10p"><input class="form-control ht-25 wd-75 bg-white pd-0 rounded-5 " type="text" name="res_hot_remarks" id=hot_remarks_'+cnt+'> </td>';
            rowData += '<td class="wd-15p"><input type="hidden" value="0" name=hot_attachment_deleted_'+cnt+' id=hot_attachment_deleted_'+cnt+' /><input class="form-control ht-25 pd-0 con_attachment disableClass" type="file" multiple="true" id=hot_attachment_'+cnt+' onchange=checkHotFile(this,'+cnt+'); name=hot_attachment_'+cnt+' value="" /><span style="color:red;display:none;" id=hotImageUpload_error_'+cnt+'></span></td>';            
            rowData += '<td class="wd-2p"><i class="fa fa-minus pd-l-10" style="padding-left: 25px" id="remove_1" onClick=deleteLodRow(this,'+cnt+') ></i></td>';
            rowData += "</tr>";
            $("#datatable4").append(rowData);
            myAutoComplet();
            $(".hot_billDate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
        }
        function deleteLodRow(rowObject, rowNum){
            $('#lod_attachment_deleted_'+rowNum).val("1");
            //var row = $(rowObject).parent().parent();
            //$(row).hide();
             $('#TH_'+ rowNum).remove();
        }
      
        function addTicRow(rowObject){
            var theRow = $(rowObject).parent().parent();
            var rowData = "";
            var ticCount = $('#ticCount').val();
            cnt = parseInt(ticCount) + 1;
            $('#ticCount').val(cnt);
            rowData += '<tr id=TT_'+cnt+'>';
            rowData += '<input type="hidden" id=tic_id_'+cnt+' name="res_tic_id" value="0}">';
            rowData += '<td class="wd-5p"><input type="hidden" name="res_tic_fcity" id=res_tic_fcity_'+cnt+' value="" class="hiddenbox" />';
            rowData += '<input type="text" name="res_fcity_id" id=res_fcity_'+cnt+' class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 ticketClass cityClass" value=""></td>';
            rowData +='<td class="wd-5p">';
            rowData +='<input type="hidden" name="res_tic_tcity" id="res_tic_tcity_${index.count}" value=" ${tichis.tcityId}" class="hiddenbox" />';
            rowData +='<input type="text" name="res_tcity_id" id="res_tcity_${index.count}" class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 tocityClass cityClass" value="">';
             rowData +='</td>';
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 tic_tdate" type="text" name="res_tic_tdate" id=tic_tdate_'+cnt+' value=""></td>';           
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 tic_billDate" readonly type="text" id=tic_billDate_'+cnt+'  name="res_tic_billDate" /></td>';
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-75 bg-white pd-0 tx-12-force rounded-5 tic_billNo" type="text" name="res_tic_billNo" id=tic_billNo_'+cnt+'></td>';
            rowData += '<td class="wd-5p">';
            rowData += '<select class="ht-25 wd-85 pd-0 tx-12-force rounded-5 invoice" name="invoice" id="invoice_${index.count}">';
            rowData += '<option  value="">--Select--</option><option  value="I">Invoice</option><option  value="C">Credit Note</option>';
            rowData += '</select></td>';            
            rowData += '<td class="wd-5p"><input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_billAmount" type="text" name="res_tic_billAmount" onkeypress="return isNumberKey(event)" onchange="calculateTotalAndTax('+cnt+');" id=tic_billAmount_'+cnt+' > </td>';
            rowData +='<td class="wd-5p"><input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_tax" type="text" name="tic_tax" onkeypress="return isNumberKey(event)" onchange="calculateTotalAndTax('+cnt+');" id=tic_tax_'+cnt+'> </td> ';
            rowData +='<td class="wd-5p"><input class="form-control ht-25 wd-55 bg-white pd-0 tx-12-force rounded-5 tic_tot" readonly="true" type="text" name="tic_tot" onkeypress="return isNumberKey(event)" id=tic_tot_'+cnt+'> </td> ';
                                      
            rowData += '<td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 tic_remarks" type="text" name="res_tic_remarks" id=tic_remarks_'+cnt+'> </td>';
            rowData += '<td class="wd-20p"><input type="hidden" value="0" name=tic_attachment_deleted_'+cnt+' id=tic_attachment_deleted_'+cnt+' /><input class="form-control ht-25 bg-white pd-0 tic_attachment" type="file" multiple="true" onchange=checkFile(this,'+cnt+'); id=tic_attachment_'+cnt+'  name=tic_attachment_'+cnt+' value="" /><span style="color:red;display:none;" id=ticImageUpload_error_'+cnt+'></span></td>';
            rowData += '<td class="wd-2p"><i class="fa fa-minus pd-l-25" style="padding-left: 25px" id="remove_1" onClick=deleteTicRow(this,'+cnt+') id=remove_'+cnt+'></td>';
            rowData += "</tr>";
            $("#datatable3").append(rowData);
            $(".tic_tdate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
            $(".tic_billDate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
            myAutoComplet();
        }
        function addRow(rowObject){
            var theRow = $(rowObject).parent().parent();
            var rowData = "";
            var conCount = $('#conCount').val();
            cnt = parseInt(conCount) + 1;
            $('#conCount').val(cnt);
            rowData += '<tr id=TR_'+cnt+'>';
            rowData += '<td class="wd-10p"><input type="hidden" name="con_deleted" id=deletedTR_'+cnt+' value="0"/>';
            rowData += '<input type="hidden" name="res_con_id" id=con_id_'+cnt+' value="">Conveyance</td>';
            rowData += '<td class="wd-10"><input type="hidden" name="res_city" id=city_'+cnt+' value="" class="hiddenbox" />';
            rowData += '<input type="text" name="res_city_id" id=res_city_'+cnt+' class="form-control ht-25 pd-0 wd-90 pd-0 tx-12-force rounded-5 convClass cityClass" value=""></td>';
            rowData += '<td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 con_fdate" type="text" name="res_con_fdate"  id=con_fdate_'+cnt+'></td>';
            rowData += '<td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 con_tdate" type="text" name="res_con_tdate"  id=con_tdate_'+cnt+'></td>';
            rowData += '<td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 con_billDate" type="text" id=con_billDate_'+cnt+'  name="res_con_billDate" readonly /></td> ';
            rowData += '<td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5 con_billNo" type="text" name="res_con_billNo" id=con_billNo_'+cnt+'> </td> ';
            rowData += '<td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5 con_billAmount" type="text" name="res_con_billAmount" onkeypress="return isNumberKey(event)" onchange="hotelTotalAndTax('+cnt+');"  id=con_billAmount_'+cnt+'></td>';
            rowData +='<td class="wd-8p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 hot_tax" type="text" name="hot_tax" onkeypress="return isNumberKey(event)" onchange="hotelTotalAndTax('+cnt+');"  id=hot_tax_'+cnt+'> </td> ';
            rowData +='<td class="wd-8p"><input class="form-control ht-25 wd-95 bg-white pd-0 tx-12-force rounded-5 hot_tot" readonly="true" type="text" name="hot_tot" onkeypress="return isNumberKey(event)" id=hot_tot_'+cnt+'> </td> ';
            rowData += '<td class="wd-10p"><input class="form-control ht-25 wd-95 bg-white pd-0 rounded-5" type="text" name="res_con_remarks" id=con_remarks_'+cnt+'> </td>';
            rowData += '<td class="wd-10p"><input type="hidden" value="0" name=con_attachment_deleted_'+cnt+' id=con_attachment_deleted_'+cnt+' />';
            rowData += '<input class="form-control ht-25 wd-70 bg-white pd-0 con_attachment" type="file" id=con_attachment_'+cnt+' multiple="true" name=con_attachment_'+cnt+' value="" /></td>';
            rowData += '<td class="wd-10p"><i class="fa fa-minus" style="padding-left: 32px"  id="remove_1" onClick=deleteRow('+cnt+') id=remove_'+cnt+' ></td>';
            rowData += "</tr>";
            $("#datatable2").append(rowData);
            myAutoComplet();
            $(".con_billDate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
            $(".con_fdate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
            $(".con_tdate").datepicker({dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
        }
        function deleteTicRow(rowObject, rowNum){
            $('#tic_attachment_deleted_'+rowNum).val("1");
            var row = $(rowObject).parent().parent();
             $('#TT_'+ rowNum).remove();
            //$(row).remove();            
        }
        function deleteRow(row) {
             $('#con_attachment_deleted_'+row).val("1");
            var cnt = $('#delCount').val();
            var delcnt = parseInt(cnt) - 1;
            $('#delCount').val(delcnt);
            $('#deletedTR_'+row).val(1); 
            $('#TR_'+ row).remove();
        }
        function vaidateform(){
            var ticDateNullCheck = tableNullCheck("tic_billDate");
            var ticNoNullCheck = tableNullCheck("tic_billNo");
            var ticCityNullCheck = tableNullCheck("ticketClass");
            var ticAmountNullCheck = tableNullCheck("tic_billAmount");
             var ticAmountNullCheck = tableNullCheck("tocityClass");
            var ticAttachmentNullCheck = tableNullCheck("tic_attachment");
            var invoiceNullCheck = tableNullCheck("invoice");
//            var hotelDateNullCheck = tableNullCheck("hot_billDate");
//            var hotelNoNullCheck = tableNullCheck("hot_billNo");
            var hotCityNullCheck = tableNullCheck("hotelClass");
             var paidByNullCheck = paidByCheck("paidBy");
//            var hotelAmountNullCheck = tableNullCheck("hot_billAmount");
            //var hotAttachmentNullCheck = tableNullCheck("hot_attachment");
            var cnt = $('#delCount').val();
            if(cnt != 0){
                var convDateNullCheck = tableNullCheck("con_billDate");
                var convCityNullCheck = tableNullCheck("convClass");
                var convNoNullCheck = tableNullCheck("con_billNo");
                var convAmountNullCheck = tableNullCheck("con_billAmount");
                var conAttachmentNullCheck = tableNullCheck("con_attachment"); 
                var confdateNullCheck = tableNullCheck("con_fdate");
                var contdateNullCheck = tableNullCheck("con_tdate");
            }else if(cnt == 0){
                var convCityNullCheck = true;
                var convDateNullCheck = true;
                var convNoNullCheck = true;
                var convAmountNullCheck = true;
                var conAttachmentNullCheck = true;
                var confdateNullCheck = true;
                var contdateNullCheck = true;
            }           
            if(ticDateNullCheck && ticNoNullCheck && ticAmountNullCheck && ticAttachmentNullCheck && conAttachmentNullCheck && invoiceNullCheck
                && paidByNullCheck && ticCityNullCheck && hotCityNullCheck && convCityNullCheck && convDateNullCheck && convNoNullCheck && convAmountNullCheck && confdateNullCheck && contdateNullCheck){               
                $('.disableClass').attr('disabled', false);
                $('.preloader').css("display","block");
                $('#actionbtn').val("11");
                return true;
            }else{
                return false;
            }
        }
        
         function paidByCheck(class_name) {
            var NullError = 0;
            var class_length = document.querySelectorAll("." + class_name).length;
            for (var i = 0; i < class_length; i++) {
                var class_name_val = document.querySelectorAll("." + class_name)[i].value;
                var class_name_val_length = $.trim(class_name_val).length;
                if (class_name_val_length > 0) {
                    document.querySelectorAll("." + class_name)[i].style.outline = "none";
                    if(class_name_val == 'c'){
                          var billattachment_val = document.querySelectorAll(".hot_attachment")[i].value;
                          var billdate_val = document.querySelectorAll(".lod_billDate")[i].value;
                          var hot_billNo_val = document.querySelectorAll(".hot_billNo")[i].value;
                          var hot_billAmount_val = document.querySelectorAll(".hot_billAmount")[i].value;
                           var billattachment_val_length = $.trim(billattachment_val).length;
                           var billdate_val_length = $.trim(billdate_val).length;
                           var hot_billNo_val_length = $.trim(hot_billNo_val).length;
                           var hot_amount_length = $.trim(hot_billAmount_val).length;
                           if (billattachment_val_length > 0) {
                               document.querySelectorAll(".hot_attachment")[i].style.outline = "none";
                           }else{
                               document.querySelectorAll(".hot_attachment")[i].style.outline = "1px solid red";
                               NullError++;
                           } 
                           if (billdate_val_length > 0) {
                               document.querySelectorAll(".lod_billDate")[i].style.outline = "none";    
                           }else{
                               document.querySelectorAll(".lod_billDate")[i].style.outline = "1px solid red";
                               NullError++;
                           }
                           if (hot_billNo_val_length > 0) {
                               document.querySelectorAll(".hot_billNo")[i].style.outline = "none";
                           }else{
                               document.querySelectorAll(".hot_billNo")[i].style.outline = "1px solid red";
                               NullError++;
                           }
                           if (hot_amount_length > 0) {
                               document.querySelectorAll(".hot_billAmount")[i].style.outline = "none";    
                           }else{
                               document.querySelectorAll(".hot_billAmount")[i].style.outline = "1px solid red";
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
        function isNumberKey(evt)
        {
            var charCode = (evt.which) ? evt.which : event.keyCode
            if (charCode > 31 && (charCode < 48 || charCode > 57))
                return false;
            else
                return true;
        }
    </script>
</html>
