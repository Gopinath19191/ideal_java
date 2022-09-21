<%-- 
    Document   : report
    Created on : 26 Aug, 2019, 2:45:16 PM
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
            margin: 0px 0px 25px 25px;
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
        .searchBox{
            background: #e2e8ec url(css/images/box-strip.jpg) repeat-x top;
            border: 1px solid #BDC9D1;
            margin: 0px 0px 10px 25px;
            width:880px;
            padding: 5px;
        }
        #ui-datepicker-div{
            display:none;
        }
        span.ui-icon.ui-icon-circle-triangle-w,span.ui-icon.ui-icon-circle-triangle-e{
            text-indent: 0px;
            font-size: 8px;
            width: 22px;
            padding: 3px 0px 0px 0px;
        }
        #fromDate,#toDate{
            background: white;
        }
        .exportbutton{
            padding-left: 20px;
            background: url(images/export-to-excel-button-icon.png) no-repeat 8px 8px #316CA8;
            width: 100px;
            height: 32px;
            font-family: Arial;
            font-weight: bold;
            font-size: 13px;
            color: #FFFFFF;
            text-align: center;
            border: 1px solid #4492BF;
            cursor: pointer;
            border-radius: 10px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $('#customerDataList').dataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                "iDisplayLength" : 20,
                "aaSorting": [[ 2, "desc" ]]
            });
            
            $("#fromDate,#toDate").keydown(function() { 
                return false;
            });
            $("#ui-datepicker-div").css({"display":"none"});
            var aa=$("#fromDate").val();
            if(aa!=''){
                aa = aa.split("-");
                $("#toDate").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear: true,maxDate: '0'});
                $("#toDate").datepicker("option", "minDate", new Date(aa[2], --aa[1], aa[0]));

            }else{
                $("#ui-datepicker-div").css({"display":"none"});
            }

            $( "#toDate" ).click(function() {
                var frmDt=$("#fromDate").val();
                if(frmDt==""){
                    alert("Please select From Date");
                }else{

                }
            });
            
            $('#fromDate').datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear: true,minDate: '2019-08-01',maxDate: '0',
                onSelect: function(dateText, inst) {
                    $("#toDate").val("");
                    var minDate = $(this).datepicker('getDate') || new Date();
                    var maxDate = new Date(minDate.getTime());
                    var currentDate = new Date();
                    var maximunDate = new Date(maxDate.setMonth(maxDate.getMonth() + 2));
                    if(currentDate < maximunDate){
                        maxDate = currentDate;
                    }else{
                        //maxDate.setMonth(maxDate.getMonth() + 2);
                    }
                    $("#toDate").datepicker({dateFormat: 'yy-mm-dd', changeMonth: true, changeYear: true});
                    $("#toDate").datepicker("option", "minDate", minDate);
                    $("#toDate").datepicker("option", "maxDate", maxDate);
                    setTimeout(function(){
                        $( "#toDate" ).datepicker('show');
                    }, 16);     
                }});
        });
        
        function downloadReport(){
            $('#approveDetails').attr("action", "getExcelDownload.htm");
            document.getElementById("approveDetails").submit();
        }
    </script>
</head>
<body onload="">
        <div id="container">
            <p class=" page_heading">Employee List</p>
                <form action="#" name="saveDetails" id="approveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
                    <div class="searchBox">
                        <table width="100%" border="0">
                            <tbody>
                               <tr>
                                   <td width="13%" style="padding-left:10px;"><b>Select Type</b></td>
                                    <td width ="10%">
                                        <select name="type" class="certificate_type" id="certificate_type"> 
                                            <option value=""> -- Select Type -- </option>
                                            <c:forEach items="${report_type}" var="report_type" >
                                                <option value="${report_type.type}">${report_type.type}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td width="10%" style="padding-left:10px;"><b>From Date</b><span style="color:red;">*</span></td>
                                    <td><input id="fromDate" name="fromDate" value="${fromDate}" style="width: 70px;" readonly/></td>
                                    <td width="10%"><b>To Date</b><span style="color:red;">*</span></td>
                                    <td><input class="toDate" id="toDate" name="toDate" value="${toDate}" style="width: 70px;" readonly/></td>
                                    <td>
                                        <input type="button" id="searchreportingData" name="searchData" class=" exportbutton" value="Export" onclick="downloadReport();"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </form>    
            <div id="datadisplay" class="formContent_new" style="width: 900px">
                <c:if test="${fn:length(report_list)>0}">
                    <table id="customerDataList"  class="display">
                        <thead>
                        <th>
                            Employee Id
                        </th>    
                        <th>
                            Employee Name
                        </th>
                        <th>
                           Issued On
                        </th>
                        <th>
                            Issued By
                        </th>
                        <th>
                            Certificate Type
                        </th>
                        </thead>
                        <script type="text/javascript">$('#datadisplay table tr td').css({'background' : ''});</script>
                        <c:forEach items="${report_list}" var="report_list" varStatus="rpritr">
                            <c:if test="${rpritr.index%2 ==0}">
                                <c:set var="rowClass" value="even"></c:set>
                            </c:if>
                            <c:if test="${rpritr.index%2!=0}">
                                <c:set var="rowClass" value="odd"></c:set>
                            </c:if>
                            <tr class="${rowClass}">
                                <td>
                                    <a>${report_list.employee_number}</a>
                                </td>
                                <td>
                                    <a>${report_list.employee_name}</a>
                                </td>
                                <td>
                                    <a>${report_list.issued_on}</a>
                                </td>
                                <td>
                                    <a>${report_list.issued_by}</a>
                                </td>
                                <td>
                                    <a>${report_list.type}</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${fn:length(report_list)==0}">
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