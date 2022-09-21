<%-- 
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Customers List</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>

    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/c/jquery-ui-1.8.16.custom.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.16.custom.css" type="text/css"/>--%>
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
            /*background: #fff;*/
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
            margin: 0px 0px 0px 25px;
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
            background-color: #eff4fa;
        }
        .display tbody tr.odd
        {
            background-color: #ffffff;
        }
        .display tbody td, .display thead th
        {
            border-right: 1px solid rgba(213, 225, 241, 0.55);
        }
        #customerDataList .sorting_1
        {
            background: none;
        }
        
    </style>
    <script type="text/javascript">
            function getExcelReport(){
                $('#customerReportPage').attr("action", "getCustomerXL.htm");
                document.customerReportPage.submit();
            }
            function submitForm(){
                document.getElementById("reportForm").action="customerList.htm";
                document.reportForm.submit();
                startLoading();
            }
            function addCustomer(){

                document.location.href="addCustomer.htm";
            }
            $(document).ready(function() {
                $('#customerDataList').dataTable({
                    "bLengthChange": false,
                    "sPaginationType": "full_numbers",
                    "iDisplayLength" : 20,
                    "aaSorting": [[ 1, "asc" ]]
                } );
                $("#successDiv").fadeOut(2000);
            } );
            
            function savedStatus(){
                $("#statusShow").css({'display':'block'});
                setTimeout(function (){
                    $("#statusShow").css({'display':'none'});
                } ,5000);
            }
    </script>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <!--<div id="container">-->
        <%--${filterData.monthFilter}
        ${filterData.yearFilter}
        ${filterData.projectFilter}--%>
        <div id="container">
            <div align="center" style="margin:15px 0px -20px 0px;" id="successDiv"><font color="green" size="4">${Success_message}</font></div>
                <p class=" page_heading">Customers List</p>
            <div class="goToList" style="width: 300px;">
                <%--<c:if test="${customAdd == 0}">--%>
                    <!--<p style="color:red; font-weight: bold;">Customer Details Not Saved</p>-->
                <%--</c:if>--%>
                <form name="customerReportPage" id="customerReportPage" action="getCustomerXL.htm" method="POST">
                    <a class="exportexcel1" onclick="getExcelReport();" target="_self" style="text-decoration:none;cursor: pointer;margin-left: 10px" title="Export">Export</a>
                </form> 
                <form name="reportForm" id="reportForm" action="accrualreport.htm" method="POST">
                    <a class="add" onclick="javascript: location.href='authenticate.htm?empId=${employee_no}&modId=75&action=add'" target="_self" style="text-decoration:none;cursor: pointer;" title="Add Request">Add Customer</a>
                </form>    
            </div>
            <div id="datadisplay" class="formContent_new" style="width: 900px">
                <c:if test="${fn:length(customerData)>0}">
                    <table id="customerDataList"  class="display">
                        <thead>
                        <th width="75px">
                            Customer Code
                        </th>    
                        <th width="275px">
                            Customer Name
                        </th>
                        <th width="160px">
                           Division Name
                        </th>
                        <!--<th>
                           BDM/Sales Person
                        </th>-->
                        <th width="75px">
                            Status
                        </th>
                        <th width="50px">
                            Action
                        </th>
                        </thead>
                        <script type="text/javascript">$('#datadisplay #customerDataList th').css({'background' : ''});</script>
                        <c:forEach items="${customerData}" var="rprt" varStatus="rpritr">
                            <c:if test="${rpritr.index%2 ==0}">
                                <c:set var="rowClass" value="even"></c:set>
                            </c:if>
                            <c:if test="${rpritr.index%2!=0}">
                                <c:set var="rowClass" value="odd"></c:set>
                            </c:if>
                            <tr class="${rowClass}">
                                <td>
                                    <a title="View" target="_self"href="customerView.htm?customerID=${rprt.custID}&&editId=1">${rprt.customerCode}</a>
                                </td>
                                <td>
                                    <a title="View" target="_self"href="customerView.htm?customerID=${rprt.custID}&&editId=1">${rprt.customerName}</a>
                                </td>
                                <td>
                                    <a title="View" target="_self"href="customerView.htm?customerID=${rprt.custID}&&editId=1">${rprt.divisionName}</a>
                                    
                                </td>
                                <!--<td>
                                    ${rprt.salesPerson}
                                </td>-->
                                <td>
                                    ${rprt.customerStatus}
                                </td>
                                <td>
                                    <c:if test="${rprt.status == 'a' || rprt.status == 'r'|| rprt.status == 's'}">
                                        <a title="Edit" target="_self" style="text-decoration:none" href="${pageContext.request.contextPath}/authenticate.htm?empId=${employee_no}&modId=79&customerId=${rprt.custID}"><img src="${pageContext.request.contextPath}/css/images/document-blue.png" style="padding-left: 18px;"></a>
                                    </c:if>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${fn:length(customerData)==0}">
                    <table>
                        <thead>
                        <th>
                            No data to display
                        </th>
                        </thead>
                    </table>
                </c:if>
            </div>
        </div>
        <script type="text/javascript">
            var loadingDivObj=(document.all);
            var ns4=document.layers;
            var ns6=document.getElementById&&!document.all;
            var ie4=document.all;
            if (ns4){
                loadingDivObj=document.loadingDiv;
            }else if (ns6){
                loadingDivObj=document.getElementById("loadingDiv").style;
            }else if (ie4){
                loadingDivObj=document.all.loadingDiv.style;
            }

            stopLoading();

            function stopLoading(){
                if(ns4){loadingDivObj.visibility="hidden";}
                else if (ns6||ie4) loadingDivObj.display="none";
            }

            function startLoading(){
                if(ns4){loadingDivObj.visibility="visible";}
                else if (ns6||ie4) loadingDivObj.display="block";
            }
        </script>
</body>