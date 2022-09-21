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
        <!--        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
                <script src="jquery.datetimepicker.js"></script>-->

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

            /*            #datatable1 td {
                                padding: 0.75rem;
                                vertical-align: top; 
                                border: 0px !important;
                            }*/
            .city_auto_search{
                padding: 0px;
                width: 90px;
                height: 20px;                
                background: url(css/images/username_icon.png) no-repeat 2px 3px #FFF !important;
            }
        </style>
        <script>    
            $(document).ready(function () {
                
               
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
        </script>        
    </head>
    <body>
        <div class="slim-mainpanel">
            <div class="container">
                <div class="slim-pageheader">
                    <ol class="breadcrumb slim-breadcrumb">
                        <c:if test="${type == 'processed'}">
                            <li class="breadcrumb-item"><a style="color: #1b84e7;" href="${pageContext.request.contextPath}/adminList.htm?type=pending&approver_type=${approver_type}">List Pending</a></li>
                        </c:if>
                        <c:if test="${type == 'pending'}">
                            <li class="breadcrumb-item"><a style="color: #1b84e7;" href="${pageContext.request.contextPath}/adminList.htm?type=processed&approver_type=${approver_type}">List Processed</a></li>
                        </c:if>
                    </ol>
                    <h6 class="slim-pagetitle">Travel Settlement</h6>
                </div>
                <form name="settlementPage" method="POST" enctype="text/file">
                    <div class="section-wrapper">
                         <c:if test="${type == 'pending'}">
                        <label class="section-title">${approver_type} Pending List</label>
                        </c:if>
                        <c:if test="${type == 'processed'}">
                            <label class="section-title">${approver_type} Processed List</label>
                        </c:if>
                        <div class="table-wrapper">
                            <table id="datatable1" class="table display table-responsive nowrap">
                                <thead>
                                    <tr>
                                        <th class="wd-15p">Ticket Reference</th>
                                        <th class="wd-10p">Start Date</th>
                                        <th class="wd-10p">End Date</th>
                                        <th class="wd-20p">Employee Name</th>
                                        <th class="wd-15p">Status</th>
                                        <th class="wd-10p">Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${fn:length(adminList)>0}">
                                       <c:forEach items="${adminList}" var="adminlist" varStatus="index">
                                    <tr>
                                        <td class="wd-15p">
                                            <c:if test="${type == 'processed'}" >
                                                <a style="color: #1b84e7;" href="${pageContext.request.contextPath}/employeeViewScreen.htm?ticketId=${adminlist.id}&approver_type=${approver_type}&type=${type}">${adminlist.ticketRefId}</a>
                                            </c:if>
                                            <c:if test="${type == 'pending'}" >
                                                <a style="color: #1b84e7;" href="${pageContext.request.contextPath}/adminTravelDetails.htm?ticketId=${adminlist.id}&approver_type=${approver_type}&type=${type}">${adminlist.ticketRefId}</a>
                                            </c:if>                                            
                                        </td>                                                                                
                                        <td class="wd-10p">${adminlist.travelStartDate}</td>
                                        <td class="wd-10p">${adminlist.travelEndDate}</td>
                                        <td class="wd-20p">${adminlist.employeeName}</td>
                                        <td class="wd-15p">${adminlist.statusDesc}</td>
                                        <td class="wd-10p">
                                            <c:if test="${type == 'processed'}" >
                                                <a href="${pageContext.request.contextPath}/employeeViewScreen.htm?ticketId=${adminlist.id}&approver_type=${approver_type}&type=${type}" style="color: #1b84e7;" class="fa fa-eye"></a>
                                            </c:if>
                                            
                                            <c:if test="${type == 'pending'}" >
                                                <a href="${pageContext.request.contextPath}/adminTravelDetails.htm?ticketId=${adminlist.id}&approver_type=${approver_type}&type=${type}" style="color: #1b84e7; font-size:20px;padding-left: 10px;" class="icon ion-compose"></a>
                                            </c:if>
                                     </td>
                                    </tr>
                                       </c:forEach>
                                    </c:if>
                                    <c:if test="${fn:length(adminList)==0}">
                                    <tr>
                                        <td colspan="6" style="text-align: center;">
                                            No Data to Display
                                        </td>
                                    </tr>
                                </c:if>
                                </tbody>
                            </table>
                        </div><!-- table-wrapper -->
                    </div><!-- section-wrapper -->                    
                </form>
            </div>
        </div>
    </body>    
</html>
