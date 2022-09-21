<%-- 
    Document   : buhApprovalList
    Created on : 5 Dec, 2017, 9:52:56 AM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/idHeader.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchase Request</title>
        <style type="text/css">
            #loadingDiv img{ border: none; }

            #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}

            #contentWrapper{
                width:90%;
                margin-left:5%;
                border:1px solid #ccc;
                float:left;
            }
            .contentGroup{
                width:30%;
                float:left;
                padding:1%;
            }
            .contentLabel{
                width:49%;
                float:left;
            }
            .contentField{
                width:49%;
                float:left;
            }        
            .effortChangedDiv{
                background-color: #FF8888;
                background-image: none;
                width:3px;
                height:7px;
                float:left;
            }
            #effortsLegend{
                margin-left:-310px;
            }
            #datadisplay1 table {
                background: #fff;
                border:1px solid #99BBE8;
                border-top:0;
                clear: both;
                /*color: #000;*/
                width: 100%;
                line-height: 20px;
            }
            #datadisplay1 th,#associateAllocation th{
                /*, #headerTable th*/

                border:1px solid #bbb;
                border-top: 1px solid #fff;
                border-left: 1px solid #fff;
                border-bottom:0;
                text-align: center;
                height:22px;
                font-weight:bolder;
            }
            #datadisplay1 th:hover{
                background:url(images/grid3-hrow-over.gif) repeat-x bottom;
            }
            #datadisplay1 th.sort_selected{
                background:url(images/grid3-hrow-over.gif) repeat-x bottom;
            }
            #datadisplay1 th a {
                width:100%;
                display: block;
                text-decoration: none;
                color:#000;
                cursor:default;
            }
            #datadisplay1 table tr td {
                /*background: #fff;*/
                /*border-right: 1px solid #ccc;*/
                padding:1px 3px;
                vertical-align: middle;
            }
            #datadisplay table tr td {
                background: none;
                /*border-right: 1px solid #ccc;*/
                padding:1px 10px;
                vertical-align: middle;
                height:25px;
            }
            #datadisplay table tr th {
                /*background: #fff;*/
                /*border-right: 1px solid #ccc;*/
                padding:1px 10px;
                vertical-align: middle;
                text-align: left;
                height:25px;
            }
            #datadisplay1 table tr.mouseover{
                background:url(images/row-over.gif) repeat-x;
            }
            #datadisplay1 table tr.selected{
                background:#DFE8F6;
            }
            #datadisplay1 table tr.altrow td {
                background: #FAFAFA;
                border-top:1px solid #EDEDED;
                border-bottom:1px solid #EDEDED;
            }
            #datadisplay1 table input.checkbox{
                float:none;
                clear:none;
                margin:0;
            }
            #datadisplay1 td.actions {
                text-align: center;
                white-space: nowrap;
            }
            #datadisplay1 td.actions a {
                margin: 0px 6px;
            }
            #datadisplay1 dl {
                width: 60%;
                margin:10px 0 0 10px;
            }
            #datadisplay1 dl .altrow {
                background: #f4f4f4;
            }
            #datadisplay1 dt {
                font-weight: bold;
                vertical-align: top;
            }
            #datadisplay1 dd {
                margin:0;
                vertical-align: top;
            }
            .dataTables_filter{
                height: 35px;
                width: 881px;
                padding: 10px 0px 0px 15px;
                font-weight: bold;
                background: #e2e8ec url(css/images/box-strip.jpg) repeat-x top;
                border: 2px solid white;
            }
            .exportexcel1{
                background: url("css/images/export-to-excel-black-icon.png") no-repeat scroll left center transparent;
                color: #4D85B8;
                float: right;
                height: 20px;
                padding: 2px 0 0 21px;
                text-decoration: none;
            }
            .paging_full_numbers{
                border-top:1px solid #C3D5ED;
                height: 22px;
                line-height: 22px;
                display: inline-block;
                padding: 10px;
            }
            .dataTables_info {
                border-top:1px solid #C3D5ED;
                width: 50%;
                display: inline-block;
                padding: 15px;
            }
            div#container{
                clear:both;
            }
            .page_heading{
                color: #666666;
                font-size: 18px;
                font-weight: bold;
                display: inline-block;
                width: 35%;
                padding: 25px 0px 0px 0px;
                margin: 0px 0px 16px 25px;
            }
            .goToList {
                color: #4C83B3;
                float: right;
                font-size: 12px;
                font-weight: bold;
                margin: 20px 40px 18px;
            }
            .display
            {
                border-collapse: collapse;
                background: none !important;
            }
            .display thead
            {
                background-color: #eee !important;
            }
            .display td a
            {
                text-decoration: none;
            }
            .display tbody
            {
                font-size: 11px !important;
                text-transform: capitalize;
                background-color: #f1f1f1;
            }
            .display tbody tr.even
            {
                background-color: #eff4fa !important;
            }
            .display tbody tr.odd
            {
                background-color: #ffffff !important;
            }
            #datadisplay td a {
                color: #4D85B8;
            }
            .display tbody td, .display thead th {
                border-right: 1px solid rgba(213, 225, 241, 0.55);
            }       
            #datadisplay th {
                background: url(images/table-header-strip.jpg) repeat-x scroll center top #EFF4FB;
                border-right: 1px solid rgba(213, 225, 241, 0.55) !important;
                font-weight: bolder;
                padding: 5px 10px;
                color: #555555;
            }
            .listReport{
                background: url(images/icon_list.png) no-repeat scroll left center transparent;
                color: #4D85B8;
                float: right;
                padding: 7px 0 0 21px;
            }
            .container {
                text-align: left;
                width: 95%;
                padding: 0px 20px 0px 20px;
                margin: auto;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function() {
                $(this).scrollTop(0);
                $("#successDiv").fadeOut(2000);
                
                $('#customerDataList').dataTable({
                    "bLengthChange": false,
                    "sPaginationType": "full_numbers",
                    "iDisplayLength" : 20,
                    "aaSorting": [[ 0, "desc" ]]
                } );
            } );
        </script>
    </head>
    <body>
        <div class="container"> 
            <p class="page_heading">
                Purchase Request -
                <c:if test="${status=='a'}">
                    Pending List
                </c:if>
                <c:if test="${status=='e'}">
                    Processed List
                </c:if>
            </p>
            <div class="goToList">
                <c:if test="${status=='a'}">
                    <img src="/ideal_attendance/images/icon_list.png" title="Apply Procurement" alt="Add Procurement">
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="procurementBUHProcessedList.htm?status=e">Processed List</a>
                </c:if>
                <c:if test="${status=='e'}">
                    <img src="/ideal_attendance/images/icon_list.png" title="Apply Procurement" alt="Add Procurement">
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="procurementBUHList.htm?status=a">Pending List</a>
                </c:if>
            </div>
            <div id="datadisplay" class="formContent_new" style="width: 900px">
                <table id="customerDataList" class="display">
                    <thead>
                        <th align="center">PR Code</th>
                        <th align="center">Requestor Name</th>
                        <th align="center">Requested Date</th>
                        <th align="center">Total Amount</th>
                        <th align="center">Status</th>
                        <th align="center">Action</th>
                    </thead>
                    <script type="text/javascript">$('#datadisplay table tr td').css({'background' : ''});</script>
                    <c:choose>
                        <c:when test="${fn:length(details)!=0}">
                            <c:forEach items="${details}" var="item" varStatus="i">
                                    <c:choose>
                                       <c:when test="${(i.count) % 2 == 0}">
                                           <tr class="odd">
                                       </c:when>
                                       <c:otherwise>
                                           <tr class="even">
                                       </c:otherwise>
                                    </c:choose>
                                    <td><a>${item.pp_code}<a></td>
                                    <td><a>${item.employee_name}<a></td>
                                    <td><a>${item.requested_date}<a></td>
                                    <td><a>${item.total}<a></td>
                                    <td><a>${item.status_name}</a></td>
                                    <td align="center">
                                        <c:if test="${item.status!='a'}">
                                            <a href="viewProcurementDetails.htm?Id=${item.id}&approverType=${approverType}"><img src="${pageContext.request.contextPath}/images/eye.png" /></a>
                                        </c:if>
                                        <c:if test="${item.status=='a'}">
                                            <a href="viewProcurementDetails.htm?Id=${item.id}&approverType=${approverType}"><img src="${pageContext.request.contextPath}/images/edit-icon.png" /></a>
                                        </c:if>

                                    </td>
                                </tr>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </table>
            </div>
        </div>
    </body>
</html>

