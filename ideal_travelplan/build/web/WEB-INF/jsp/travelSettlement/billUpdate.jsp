<%-- 
    Document   : billUpdate
    Created on : 26 Nov, 2019, 11:46:16 AM
    Author     : 16221
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
        <link type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" rel="stylesheet" />
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
        <script src="${pageContext.request.contextPath}/js/slim.js"></script>
        <script src="${pageContext.request.contextPath}/lib/moment/js/moment.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/number_validate.js" ></script>
        <script src="${pageContext.request.contextPath}/js/slim.js"></script>

        <style> 
            #datatable1_length select{
                padding: 10px 0px 10px 0px;
            }
            body { background-color: #f0f2f7;  color: #101010; }
            footer{ font-size:small;position:fixed;right:5px;bottom:5px; }
            a:link, a:visited  { color: #0000ee; }
            pre{ background-color: #eeeeee; margin-left: 1%; margin-right: 2%; padding: 2% 2% 2% 5%; }
            p { font-size: 0.9rem; }
            ul { font-size: 0.9rem; }
            hr { border: 2px solid #eeeeee; margin: 2% 0% 2% -3%; }
            h3 { border-bottom: 2px solid #eeeeee; margin: 2rem 0 2rem -1%; padding-left: 1%; padding-bottom: 0.1em; }
            h4 { border-bottom: 1px solid #eeeeee; margin-top: 2rem; margin-left: -1%; padding-left: 1%; padding-bottom: 0.1em; }
            .slim-pageheader{ display:block;}
            .city_auto_search{
                padding: 0px;
                width: 90px;
                height: 20px;                
                background: url(css/images/username_icon.png) no-repeat 2px 3px #FFF !important;
            }
            td.ui-datepicker-unselectable.ui-state-disabled {
                opacity: 0.25;
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
            input.approvebutton {
                background: url(./css/images/icon_btn_submit.png) no-repeat scroll 8px 8px #3579c6;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 5px 0 0;
                border-radius: 10px;
                padding: 0 10px 0 30px;
                cursor: pointer;
            }
            .col-sm tr td{
                padding:10px;
            }
        </style>
        <script>    
            $(document).ready(function () {
                $(".hot_billDate").datepicker({minDate : new Date('${travelDetails.requestedDate}'),maxDate : new Date(),dateFormat : 'dd-M-yy',changeMonth : true,changeYear : true});
               
            }); 
            $(function(){
                'use strict';

                $('#datatable1').DataTable({
                    "order": [[ 0, "desc" ]],
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

            });
//            $("#backButton").click(function (e) {
//                $("#status").val('22');
//                
//            });
            function listPage(){
                $('#saveBillDetails').attr("action", "settlementPendingApprovels.htm?approver_type=finance&type=pending");
                document.getElementById("saveBillDetails").submit();
            }
            function updateBill(){
                var bill_type=$("#bill_received").val();
                var bill_date=$("#billReceivedDate").val();
                if(bill_type=="" && bill_date==""){
                    alert("Select date and bill received type");
                }else if(bill_type=="Yes" && bill_date==""){
                    alert("Select bill received date");
                }else{
                    $('#saveBillDetails').attr("action", "saveBillDetails.htm");
                    document.getElementById("saveBillDetails").submit();
                }
                
            }
        </script>        
    </head>
    <body>
        <div class="slim-mainpanel">
            <div class="container">
                <div class="slim-pageheader">
                    <h6 class="slim-pagetitle">Travel Settlement - Bill Details Update</h6>
                </div>
                <form name="saveBillDetails" method="POST" enctype="text/file" id="saveBillDetails" action="#">
                    <div class="section-wrapper">
                        <div class="table-wrapper">
                            <h6 class="slim-pagetitle mg-b-20" style="padding:0px;border:0px;text-align: center;">Travel ID - ${travelDetails.ticketRefId}</h6>
                            <table class="col-sm">
                                <input type="hidden" value="${travelDetails.masterId}" id="status" name="masterId"/>
                                <input type="hidden" name="approver_type" value="${approver_type}"/>
                                <input type="hidden" name="empId" value="${travelDetails.empId}"/>
                                <input type="hidden" name="list_type" value="${type}"/>
                                <input type="hidden" name="ticketRefId" value="${travelDetails.ticketRefId}"/>
                                <tbody>
                                    <tr>
                                        <td style="text-align: right;"><strong class="tx-inverse tx-medium">Bill Received</strong></td>
                                        <td>
                                            <select name="billReceived" id="bill_received">
                                                <option value="">--Select--</option>
                                                <option value="NA">NA</option>
                                                <option value="Yes">Yes</option>
                                                <option value="No">No</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="text-align: right;"><strong class="tx-inverse tx-medium">Bill Received Date</strong></td>
                                        <td>
                                            <input class="form-control ht-25 wd-100 pd-0 tx-12-force rounded-5 lod_billDate hot_billDate disableClass" type="text" id="billReceivedDate"  name="billReceivedDate" />
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                            <div class="section-tables">
                                <div class="table-responsive text-center" style="margin: 20px;">
                                    <input name="travelbtn" id="backButton" style="cursor: pointer;" type="button" readonly value="Back"  class="buttons backbutton" onclick="listPage();">
                                    <input name="billUpdatebtn" id="updateBillDetails" style="cursor: pointer;" type="button" readonly value="Submit"  class="buttons approvebutton" onclick="updateBill();">
                                </div><!-- table-wrapper -->
                            </div>
                        </div><!-- section-wrapper -->    
                    </div>
                </form>
            </div>
        </div>
    </body>    
</html>
