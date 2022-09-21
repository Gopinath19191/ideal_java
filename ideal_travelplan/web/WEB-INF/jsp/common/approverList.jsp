<%-- 
    Document   : approverList
    Created on : 9 Jun, 2018, 8:00:17 PM
    Author     : 16221
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
        <!-- Slim CSS -->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/slim.css">
        
        <!-- Script from theme -->
        <script src="${pageContext.request.contextPath}/lib/jquery/js/jquery.js"></script>
        <script src="${pageContext.request.contextPath}/lib/popper.js/js/popper.js"></script>
        <script src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.js"></script>
        <script src="${pageContext.request.contextPath}/lib/jquery.cookie/js/jquery.cookie.js"></script>
        <script src="${pageContext.request.contextPath}/lib/datatables/js/jquery.dataTables.js"></script>
        <!--<script src="${pageContext.request.contextPath}/lib/datatables-responsive/js/dataTables.responsive.js"></script>-->
        <script src="${pageContext.request.contextPath}/lib/select2/js/select2.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/slim.js"></script>
        <style>
            .ion-compose{
                font-size: 20px;
                line-height: 0;
                margin-right: 8px;
                color: #1b84e7;
                width: 18px;
                text-align: center;
                transition: all 0.2s ease-in-out;
            }
            .fa-eye{
                color:#666;
                font-size: 20px;
                padding:0px 10px;
            }
        </style>
        <script>
      $(function(){
        'use strict';

        $('#datatable1').DataTable({
            "order": [[ 0, "desc" ]],
            responsive: false,
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
                        <c:if test="${type == 'processed'}">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/listPendingApprovers.htm?type=pending&approver_type=${approver_type}">List Pending</a></li>
                        </c:if>
                        <c:if test="${type == 'pending'}">
                            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/listPendingApprovers.htm?type=processed&approver_type=${approver_type}">List Processed</a></li>
                        </c:if>
                        
                    </ol>
                    <h6 class="slim-pagetitle">Domestic Travel List</h6>
                </div>
                <div class="section-wrapper">
                    <c:if test="${type == 'pending'}">
                        <label class="section-title">${approver_type} Pending List</label>
                    </c:if>
                    <c:if test="${type == 'processed'}">
                        <label class="section-title">${approver_type} Processed List</label>
                    </c:if>
                    <div class="table-wrapper">
                        <table id="datatable1" class="table display responsive nowrap">
                            <thead class="thead-colored thead-info">
                                <tr>
                                    <th class="wd" style="display:none;"></th>
                                    <th class="wd-15p">Request Id</th>
                                    <th class="wd-15p">Request Date</th>
                                    <th class="wd-25p">Employee Name</th>
                                    <th class="wd-20p">Start Date</th>
                                    <c:if test="${approver_type == 'treasury'}">
                                        <th class="wd-20p">End Date</th>
                                        <th class="wd-20p">Currency</th>
                                        <th class="wd-20p">Amount</th>
                                    </c:if>
                                    
                                    <th class="wd-15p">Status</th>
                                    <th class="wd-10p">Action</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${fn:length(travel_details)>0}">
                                    <c:forEach items="${travel_details}" var="travelDetails">
                                        <tr>
                                            <td class="wd" style="display:none;"><a style="display:none;">${travelDetails.master_id}</a></td>
                                            <td class="wd-15p"><a href="${pageContext.request.contextPath}/approverTravelView.htm?master_id=${travelDetails.master_id}&approver_type=${approver_type}&type=${type}">${travelDetails.tp_reference_id}</a></td>
                                            <td class="wd-15p">${travelDetails.requested_date}</td>
                                            <td class="wd-25p">${travelDetails.employee_name}</td>
                                            <td class="wd-10p">${travelDetails.travel_start_date}</td>
                                            <c:if test="${approver_type == 'treasury'}">
                                                <td class="wd-10p">${travelDetails.travel_end_date}</td>
                                                <td class="wd-10p">${travelDetails.currency_name}</td>
                                                <td class="wd-10p">${travelDetails.advance_amount}</td>
                                            </c:if>
                                            <td class="wd-15p">${travelDetails.status}</td>
                                            <td class="wd-10p">
<!--                                                <a href="${pageContext.request.contextPath}/viewTravelDetails.htm?master_id=${travelDetails.master_id}" class="fa fa-eye"></a>-->
                                                <a href="${pageContext.request.contextPath}/approverTravelView.htm?master_id=${travelDetails.master_id}&approver_type=${approver_type}&type=${type}" class="icon ion-compose"></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                                <c:if test="${fn:length(travel_details)==0}">
                                    <tr>
                                        <td colspan="6" style="text-align: center;">
                                            No Data to Display
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

