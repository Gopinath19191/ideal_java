<%-- 
    Document   : viewTravelDetails
    Created on : 8 Jun, 2018, 3:13:24 PM
    Author     : 16221
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            input.backbutton {
                background: url(./css/images/back.png) no-repeat scroll 8px 8px #3579c6;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 5px 0 0;
                border-radius: 10px;
                padding: 0 10px 0 30px;
                cursor:pointer;
                width:80px;
            }
            .error_message{
                color: red;
                font-size: small;
                display: none;
            }
            .commentts{
                text-align: center;
                padding:30px;
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
                width:90px;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#back_btn").click(function (e) {
                    $("#saveDetails").submit(); 
                    return true;
                });
                $('#settlement_view_btn').click(function (e){
                    var ticId = $('#master_id').val();
                    $('#saveDetails').attr("action", "employeeViewScreen.htm?ticketId="+ticId);
                    document.saveDetails.submit();
                });
            });
            
        </script>
    </head>
    <body>
<!--        <div class="slim-header">
            <div class="container">
                <div id="logo"><a href="http://ideal.hindujatech.com/"><img src="http://ideal.hindujatech.com/img/ideal_logo.jpg" alt="" title="" border="0" /></a></div>
            </div>
        </div>-->
        <div class="slim-mainpanel">
            <div class="container">
                <div class="slim-pageheader">
                    <ol class="breadcrumb slim-breadcrumb">
                        <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/getList.htm">List Request</a></li>
                    </ol>
                    <h6 class="slim-pagetitle">Travel View</h6>
                </div>
                <form action="getList.htm" name="saveDetails" id="saveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
                    <div class="section-wrapper">
                        <h6 class="slim-pagetitle" style="padding:0px;border:0px;text-align: center;">Travel Id - ${travel_details.tp_reference_id}</h6>
                        <label class="section-title">Employee Details</label>
                        <input type="hidden" name="master_id" id="master_id" value="${travel_details.master_id}"/>
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
                                <td><strong class="tx-inverse tx-medium">BUH Approver </strong></td>
                                <td><span class="text-muted">${travel_details.buh_name}</span></td>
                                <td><strong class="tx-inverse tx-medium">Client Reimbursable </strong></td>
                                <td><span class="text-muted">${travel_details.client_reimbursable}</span></td>
                                <td><strong class="tx-inverse tx-medium">Settlement Policy </strong></td>
                                <td><span class="text-muted">${travel_details.settlemet_policy}</span></td>
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
                        <label class="section-title">Advance Details</label>
                        <table class="tableStructure" id="ticket_table" width="90%">
                            <tr class="headerCenter">
                                <th width="10%">Required Date</th>
                                <th width="15%">Advance Amount</th>
                                <th width="10%">Currency</th>
                                <th width="15%">Remarks</th>
                                <c:if test="${travel_details.treasury_approved_date != null}">
                                    <th width="10%">Deposited Date</th>
                                    <th width="10%">Deposited Amount</th>
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
                                        <c:if test="${travel_details.treasury_approved_date != null}">
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
                        <c:if test="${fn:length(ticket_details)>0}">
                            <label class="section-title">Ticket Details</label>
                            <div id="TicketTab">
                                <table class="tableStructure" id="ticket_table">
                                    <tr class="headerCenter">
                                        <th width="14%"><label>Booking Type</label></th>
                                        <th width="14%"><label>From</label></th>
                                        <th width="14%"><label>To</label></th>
                                        <th width="10%"><label>Date of Travel</label></th>
                                        <th width="10%"><label>Preference</label></th>
                                        <th width="10%"><label>Mode</label></th>
                                        <th width="10%"><label>Remarks</label></th>
                                        <th width="10%"><label>Status</label></th>
                                        <th width="10%"><label>Ticket</label></th>
                                    </tr>
                                    <c:if test="${fn:length(ticket_details)>0}">
                                        <c:forEach items="${ticket_details}" var="tic_det">
                                            <tr>
                                                <td><span class="text-muted">${tic_det.ticket_book_type}</span></td>
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
                                                <td><span class="text-muted">${tic_det.travel_travel_mode}</span></td>
                                                <td><span class="text-muted">${tic_det.travel_ticket_remarks}</span></td>
                                                <td><span class="text-muted">${tic_det.booking_status_name}</span></td>
                                                <td>
                                                    <a href="fileDownload.htm?fileName=${tic_det.file_name}" target="_blank" name="file">${tic_det.file_name}</a>
                                                </td>
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
                        <c:if test="${fn:length(hotel_details)>0}">
                            <label class="section-title">Hotel Details</label>
                            <div id="HotelTab">
                                <table class="tableStructure" id="hotel_tabel">
                                    <tr class="headerCenter">
                                        <th width="15%"><label>Booking Type</label></th>
                                        <th width="15%"><label>City/Town</label></th>
                                        <th width="15%"><label>Location Preference</label></th>
                                        <th width="10%"><label>From Date</label></th>
                                        <th width="10%"><label>To Date</label></th>
                                        <th width="10%"><label>Remarks</label></th>
                                        <th width="10%"><label>Status</label></th>
                                        <th width="10%"><label>Hotel Details</label></th>
                                    </tr>
                                    <c:if test="${fn:length(hotel_details)>0}">
                                        <c:forEach items="${hotel_details}" var="hot_det">
                                            <tr>
                                                <td><span class="text-muted">${hot_det.hotel_book_type}</span></td>
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
                                                <td><span class="text-muted">${hot_det.booking_status_name}</span></td>
                                                <td>
                                                    <a href="fileDownload.htm?fileName=${hot_det.file_name}" target="_blank" name="file">${hot_det.file_name}</a>
                                                </td>
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
                            <c:if test="${fn:length(cab_details)>0}">
                                <label class="section-title">Hotel Details</label>
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
                        <%--</c:if>--%>
                        <label class="section-title">Approver Details</label>
                        <table class="tableStructure" style="width:70%">
                            <tbody>
                                <tr>
                                    <th></th>
                                    <th>Approver Name</th>
                                    <th>Approved Date</th>
                                    <th>Approver Comments</th>
                                </tr>
                                <tr>
                                    <td><label>Reporting/Project Manager</label></td>
                                    <td>${travel_details.rm_name}</td>
                                    <td>${travel_details.rm_approved_date}</td>
                                    <td>${travel_details.rm_remarks}</td>
                                </tr>
                                <c:if test="${travel_details.deviation == 'Y'}">
                                    <tr>
                                        <td><label>BUH</label></td>
                                        <td>${travel_details.buh_name}</td>
                                        <td>${travel_details.buh_approved_date}</td>
                                        <td>${travel_details.buh_remarks}</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td><label>Business Operation</label></td>
                                    <td>${travel_details.finance_name}</td>
                                    <td>${travel_details.finance_approved_date}</td>
                                    <td>${travel_details.finance_remarks}</td>
                                </tr>
                                <c:if test="${travel_details.admin_action == 'Y'}">
                                    <tr>
                                        <td><label>Admin</label></td>
                                        <td>${travel_details.admin_name}</td>
                                        <td>${travel_details.admin_approved_date}</td>
                                        <td>${travel_details.admin_remarks}</td>
                                    </tr>
                                </c:if>
                                <c:if test="${travel_details.treasury_action == 'Y'}">
                                    <tr>
                                        <td><label>Treasury</label></td>
                                        <td>${travel_details.treasury_name}</td>
                                        <td>${travel_details.treasury_approved_date}</td>
                                        <td>${travel_details.treasury_comments}</td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                        <h4 class="slim-pagetitle" style="font-size:15px;border:0px; padding:30px 0px;">Travel Status - ${travel_details.status_name}</h4>
<!--                        <div class="commentts" id="comments_id">
                            <strong class="tx-inverse tx-medium">Comments : </strong>
                            <input type="text" name="cancel_remarks" id="treasury_comments"/>
                            <span id="cancel_remarks_error" class="error_message">Please enter remarks</span>
                        </div>-->
                        <div class="buttonAlignment" id="submitDiv">
                            <div class="submit buttonAlignment" align="center" id="btnGroup">
                                <input name="travelbtn" id="back_btn" type="button" readonly value="Back" class="buttons backbutton">
                                <c:if test="${travel_details.status > 11 }">
                                    <input name="travelbtn" style="width: 150px;"id="settlement_view_btn" type="buttton" readonly value="View Settlement" class="buttons submitbutton">
                                </c:if>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
</html>
