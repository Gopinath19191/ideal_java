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
            padding:1px 2px;
            vertical-align: middle;
            height:25px;
        }
        #datadisplay table tr th {
            /*background: #fff;*/
            /*border-right: 1px solid #ccc;*/
            padding:1px 2px;
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
            //width: 350px;
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
            padding: 12px 0px 15px 0px;
            margin: 0px 0px 0px 20px;
        }
        .goToList {
            color: #4C83B3;
            float: right;
            font-size: 12px;
            font-weight: bold;
            margin: 8px 13px 18px;
        }
        #listName{
            font-weight:bold; 
            float:right; 
            margin: -30px 33px 0px 0px;
            text-decoration: none;
            color: #666666;
            cursor: pointer;
        }
        #listIcon{
            font-weight:bold; 
            float:right; 
            margin: -33px 120px 0px 0px;
            width: 12px;
        }
        #add_division{
            background:#316CA8;
            border: 1px solid #4492BF;
            color: #FFFFFF;
            font-weight: bold;
            height: 30px;
            margin: 0 0 0 15px;
            padding: 0 10px 0 10px;
            width:120px;
        }
        .dataTables_filter{
            display: none;
        }
        .page_heading_2{
            margin:0px 0px 0px 3px;
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
                $("#successDiv").fadeOut("slow");
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
            <p class=" page_heading">Customers Division List</p>
            <div>
                <img src="/iDeal_application/css/images/icon_list.png" title="Information" alt="Information" id="listIcon"></img>
                <p class="divisoin_list">
                    <a id="listName" onclick="javascript: location.href='authenticate.htm?empId=${employee_no}&modId=73&parentId=0'">Customer List</a>
                </p>
            </div>
            <div id="datadisplay" class="formContent_new" style="width: 900px">
                <p class=" page_heading page_heading_2">Customer Name: ${customerData.get(0).customerName}</p>
                <div class="goToList" style="width: 300px; margin:10px 0px 10px 0px;">
                    <form name="reportForm" id="reportForm" style="margin-left:150px">
                        <!--<a class="add" href="customerAddDivision.htm?customerId=${customerData.get(0).custID}" target="_self" style="text-decoration:none;cursor: pointer;" title="Add Request">Add Division</a>-->
                        <input type="button" name="add_division" id="add_division" value="Add Division" style="cursor:pointer;" class="add_division" onclick="javascript: location.href='customerAddDivision.htm?parentId=${parentId}&action=add_division'">
                    </form>    
                </div>
                
                <c:if test="${fn:length(customerData)>0}">
                    <table id="customerDataList"  class="display">
                        <thead>
                        <th>
                            Division Code
                        </th>
<!--                        <th>
                            Customer Name
                        </th>-->
                        <th>
                            Division Name
                        </th>
                        <th>
                            Division URL
                        </th>
                        <th>
                            Sales Person
                        </th>
                        <th>
                            Status
                        </th>
                        <th>
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
                                    <a title="View" target="_self"href="customerView.htm?customerID=${rprt.customerID}">${rprt.customerCode}</a>
                                </td>
<!--                                <td>
                                    <a title="View" target="_self"href="customerView.htm?customerID=${rprt.customerID}">${rprt.customerName}</a>

                                </td>-->
                                <td>
                                    <a title="View" target="_self"href="customerView.htm?customerID=${rprt.customerID}">${rprt.customerDivision}</a>                           
                                </td>
                                <td>
                                    <c:set var="theString" value="${rprt.customerURL}"/>
                                    <c:choose>
                                        <c:when test="${fn:contains(theString, 'http://')}">
                                            <a title="View" href="${rprt.customerURL}" target="_blank">${rprt.customerURL}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a title="View" href="http://${rprt.customerURL}" target="_blank">${rprt.customerURL}</a>
                                        </c:otherwise>
                                    </c:choose>

                                </td>
                                <td>
                                    ${rprt.salesPerson}
                                </td>
                                <td>
                                    ${rprt.customerStatus}
                                </td>
                                <td>
                                    <a title="Edit" target="_self" style="text-decoration:none" href="${pageContext.request.contextPath}/authenticate.htm?empId=${employee_no}&modId=79&parentId=${parentId}&customerId=${rprt.customerID}"><img src="${pageContext.request.contextPath}/css/images/document-blue.png" style="padding-left: 18px;"></a>
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