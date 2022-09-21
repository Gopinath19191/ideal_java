<%-- 
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="../header.jsp" %>
<head>
    <title>Customer Mapping List</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>
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
            vertical-align: top;
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
    </style>
    <script type="text/javascript">
            function getExcelReport(){
                $('#customerReportPage').attr("action", "customerMappingExport.htm");
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

            } );
    </script>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <p class="page_heading" style="margin-left: 36px;"><span class="heading">Customer Mapping list</span> </p>
            <div style="float: right;margin: -18px 7px 6px 0px;" >
                 <form name="customerReportPage" id="customerReportPage" action="${pageContext.request.contextPath}/custMap/customerMappingExport.htm" method="POST">
<!--                    <a class=" customerexportexcel" onclick="getExcelReport();" target="_self" style="text-decoration:none;cursor: pointer;" title="Export">Export All</a>-->
                    <input class=" qualityexcelexport" style="width: 92px;text-align: right;" type="button"  onclick="getExcelReport()" value="Export All"/>
                </form> 
            </div>
           
        </div>
        <div id="container">
<!--            <div class="goToList" style="width: 91px;">
                <form name="customerReportPage" id="customerReportPage" action="${pageContext.request.contextPath}/custMap/customerMappingExport.htm" method="POST">
                    <a class=" customerexportexcel" onclick="getExcelReport();" target="_self" style="text-decoration:none;cursor: pointer;" title="Export">Export All</a>
                    <input class=" qualityexcelexport" style="width: 92px;text-align: right;" type="button"  onclick="getExcelReport()" value="Export All"/>
                </form> 
                <form name="reportForm" id="reportForm" action="accrualreport.htm" method="POST">
                    <a class="add" onclick="javascript: location.href='${pageContext.request.contextPath}/custMap/addOrEditCustMap.htm'" target="_self" style="text-decoration:none;cursor: pointer;" title="Create Customer Mapp">Add Customer Mapping</a>
                </form>    
            </div>-->
            <div id="datadisplay" class="formContent_new" style="width: 100%">
                <c:if test="${successMsg!=''}">
                    <p style="color: green;text-align: center;" >${successMsg}</p>
                </c:if>
                <c:if test="${fn:length(custMapData)>0}">
                    <table id="customerDataList"  class="display">
                        <thead>
                        <th>
                            Customer Code
                        </th>
                        <th>
                            Customer Name
                        </th>
                        <th>
                            Customer Owner
                        </th>
                        <th>
                            Leader
                        </th>
                        <th>
                            Region
                        </th>
                        <th>
                            Sub Region
                        </th>
                        <th>
                            Action
                        </th>
                        </thead>
                        <script type="text/javascript">$('#datadisplay #customerDataList th').css({'background' : ''});</script>
                        <c:forEach items="${custMapData}" var="custMapDataValues" varStatus="custMapIndex">
                            <c:if test="${custMapIndex.index%2 ==0}">
                                <c:set var="rowClass" value="even"></c:set>
                            </c:if>
                            <c:if test="${custMapIndex.index%2!=0}">
                                <c:set var="rowClass" value="odd"></c:set>
                            </c:if>
                            <tr class="${rowClass}">
                                <td>
                                    ${custMapDataValues.customerCode}
                                </td>
                                <td>
                                    ${custMapDataValues.customerName}
                                </td>
                                <td>
                                    ${custMapDataValues.bdmEmpNumber}--${custMapDataValues.bdmEmpName}
                                </td>
                                <td>
                                    ${custMapDataValues.leaderEmpNumber}--${custMapDataValues.leaderEmpName}
                                </td>
                                <td>
                                    ${custMapDataValues.region}
                                </td>
                                <td>
                                    ${custMapDataValues.subRbu}
                                </td>
                                <td>
                                    <a title="Edit" target="_self" style="text-decoration:none" href="#" onclick="getDataForEdit('${custMapDataValues.customerMapId}')" ><img src="${pageContext.request.contextPath}/css/images/document-blue.png" style="padding-left: 18px;"></a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${fn:length(custMapData)==0}">
                    <table>
                        <thead>
                        <th>
                            No data to display
                        </th>
                        </thead>
                    </table>
                </c:if>
            </div>
            <form id="editForm" name="editForm" method="POST" action="${pageContext.request.contextPath}/custMap/addOrEditCustMap.htm" >
                <input type="hidden" name="customerMapId" id="customerMapId" readonly="" value="" />
            </form>
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
            function getDataForEdit(customerMapId){
                $("#customerMapId").val(customerMapId);
                $("#editForm").submit();
            }
        </script>
</body>