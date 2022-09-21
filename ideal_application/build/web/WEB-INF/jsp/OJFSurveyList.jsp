<%-- 
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>OJF Survey List</title>
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

      <%--  $(document).ready(function() {
		$( ".jQcalendarStDate").datepicker({
                changeMonth: true,
		changeYear: true,
                dateFormat:"dd-M-yy",
                altField: "#startDate",
                altFormat: "yy-m-dd"
                }
            );
		$(".jQcalendarEdDate").datepicker({
                changeMonth: true,
		changeYear: true,
                dateFormat:"dd-M-yy",
                minDate:$(".jQcalendarStDate").val(),
                altField: "#endDate",
                altFormat: "yy-m-dd"
                }
                );

		$( ".toDate").datepicker( "option", "dateFormat", $( this ).val() );
	});--%>
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

        function downLoadData(empId){
            $("#empId").val(empId);
            $('#formOJFSurveyDownLoadData').submit();
        }
    </script>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <p class="page_heading" style="margin-bottom: 40px;margin-left: 17px;"><span class="heading">OJF Survey list</span> </p>
        </div>
            <div id="datadisplay" class="formContent_new" style="width: 100%">
            <c:if test="${fn:length(ojfEmployeeData)>0}">
                <table id="customerDataList"  class="display">
                    <thead>
                    <th>
                        Employee ID
                    </th>
                    <th>
                        Employee Name
                    </th>
                    <th>
                        Joined Date
                    </th>
                    <th>
                        RM Name
                    </th>
                    <th>
                        SBU
                    </th>
                    <th>
                        Survey Rating
                    </th>
                    <th>
                        Action
                    </th>
                    </thead>
                    <script type="text/javascript">$('#datadisplay #customerDataList th').css({'background' : ''});</script>
                    <c:forEach items="${ojfEmployeeData}" var="rprt" varStatus="rpritr">
                        <c:if test="${rpritr.index%2 ==0}">
                            <c:set var="rowClass" value="even"></c:set>
                        </c:if>
                        <c:if test="${rpritr.index%2!=0}">
                            <c:set var="rowClass" value="odd"></c:set>
                        </c:if>
                        <tr class="${rowClass}">
                            <td>
                                ${rprt.employee_code}
                            </td>
                            <td>
                                ${rprt.employee_name}
                            </td>
                            <td>
                                ${rprt.joined_date}
                            </td>
                            <td>
                                 ${rprt.pm_name}
                            </td>
                             <td>
                                 ${rprt.sbu_name}
                            </td>
                            <td>
                                 ${rprt.ojf_survey_rating}
                            </td>
                            <td align="center">
                                <a title="View" target="_self" style="text-decoration:none" href="${pageContext.request.contextPath}/authenticate.htm?empId=${employee_no}&modId=564&surveyEmployeeId=${rprt.exitEmpId}"><img src="${pageContext.request.contextPath}/css/images/document-blue.png"></a>
                                <a title="Export" style="text-decoration:none" href="#" onclick="downLoadData('${rprt.exitEmpId}')"><img src="${pageContext.request.contextPath}/css/images/export-16x16.png"></a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
                </c:if>

                <form name="formOJFSurveyDownLoadData" id="formOJFSurveyDownLoadData" method="POST" action="downloadEmployeeSurveyData.htm">
                    <input type="hidden" readonly name="empId" id="empId" value="" >
                </form>
                <c:if test="${fn:length(ojfEmployeeData)==0}">
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