<%-- 
    Document   : dcEmployeeList
    Created on : 25 May, 2021, 5:01:52 PM
    Author     : 16221
--%>

<%@include file="idHeader.jsp" %>
<head>
    <title>Employee List</title>

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
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#customerDataList').dataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                "iDisplayLength" : 20,
                "aaSorting": [[ 2, "desc" ]]
            } );
        } );
    </script>
</head>
<body onload="">
    <div id="container">
        <p class=" page_heading">Employee List</p>
        <div id="datadisplay" class="formContent_new" style="width: 900px">
            <c:if test="${fn:length(employee_list)>0}">
                <table id="customerDataList"  class="display">
                    <thead>
                    <th>
                        Employee Id
                    </th>    
                    <th>
                        Employee Name
                    </th>
                    <th>
                        Date of Joining
                    </th>
                    <th>
                        Designation
                    </th>
                    <th>
                        Band
                    </th>
                    <th>
                        Action
                    </th>
                    </thead>
                    <script type="text/javascript">$('#datadisplay table tr td').css({'background' : ''});</script>
                    <c:forEach items="${employee_list}" var="employeelist" varStatus="rpritr">
                        <c:if test="${rpritr.index%2 ==0}">
                            <c:set var="rowClass" value="even"></c:set>
                        </c:if>
                        <c:if test="${rpritr.index%2!=0}">
                            <c:set var="rowClass" value="odd"></c:set>
                        </c:if>
                        <tr class="${rowClass}">
                            <td>
                                <a>${employeelist.employee_number}</a>
                            </td>
                            <td>
                                <a>${employeelist.employee_name}</a>
                            </td>
                            <td>
                                <a>${employeelist.date_of_joining}</a>
                            </td>
                            <td>
                                <a>${employeelist.band}</a>
                            </td>
                            <td>
                                <a>${employeelist.designation}</a>
                            </td>
                            <td>
                                <a title="Edit" target="_self" style="text-decoration:none" href="${pageContext.request.contextPath}/getDcEmployeeDetails.htm?employee_id=${employeelist.employee_id}&type=${type}"><img src="${pageContext.request.contextPath}/css/images/edit-icon.png" style="padding-left: 8px;"></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${fn:length(employee_list)==0}">
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

</body>