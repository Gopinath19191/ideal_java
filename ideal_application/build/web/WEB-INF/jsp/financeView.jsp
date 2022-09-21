<%-- 
    Document   : financeView
    Created on : 29 Jul, 2016, 11:35:37 AM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Finance View</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
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
/*        #datadisplay1 table {
            background: #fff;
            border:1px solid #99BBE8;
            border-top:0;
            clear: both;
            color: #000;
            width: 100%;
            line-height: 20px;
        }
        #datadisplay1 th,#associateAllocation th{
            , #headerTable th

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
            background: #fff;
            border-right: 1px solid #ccc;
            padding:1px 3px;
            vertical-align: middle;
        }*/
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
/*        #datadisplay1 table tr.mouseover{
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
        }*/
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
            clear:right;
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
            padding: 25px 0px 20px 0px;
            margin: 0px 0px 0px 25px;
        }
        .goToList {
            color: #4C83B3;
            float: right;
            font-size: 12px;
            font-weight: bold;
            margin: 8px 40px 18px;
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
        div#customerDataList_filter label{
            width:auto;
            padding: 5px 0px;
        }
        .dataTables_filter{
            width:880px;
        }
        #searchdiv{
            height: auto;
            width: 881px;
            padding: 10px 0px 10px 15px;
            font-weight: bold;
            background:none;
            background-color: #ced9df ;
            border: 2px solid white;            
            padding-bottom: 0px;
            border-bottom-width: 0px;
        }
        form div{
            padding: 0px;
        }
        #customerDataList_filter{
            background:none;
            background-color: #ced9df ;
            padding-top: 0px;
            border-top-width: 0px;
            width: 881px;
        }
        form label {
            width: auto;
            padding: 8px 30px 0px 0px;
        }
        .plainButton{
            background:#316CA8;
            border: 1px solid #4492BF;
            color: #FFFFFF;
            font-weight: bold;
            margin: 0 0 0 15px;
            padding: 10px 10px;
            width: auto;
            float: left;
            cursor:pointer;
            border-radius: 5px;
        }
        #ActivateCustomerYes,#deactivateCustomerYes{
            margin-left:130px;
        }
        #projectDetails {
            border-collapse: collapse;
            MARGIN: 0PX AUTO;
            WIDTH: AUTO;
           display: table;
        }
        #projectDetails th{
            background-color: #316CA8;
            border: 1px solid #4492BF;
            color: #FFFFFF;
            font-weight: normal;
            height: 25px;
            margin: 0 0 0 15px;
        }
            
        #projectDetails td{
            text-align: left;
            word-wrap: break-word;
            border: 1px solid #ccc;
            padding:3px 10px;
            font-size:11px;
        }
        #projectDetails td:first-child{ 
            width:75%;
            }
         #projectDetails td:last-child{ 
            width:24%;
        }
        #projectDetails tr:nth-child(odd){
            background-color: #eff4fa;
        }
        #customerDataList_filter label input[type="text"]{
            margin:0px 0px 0px 64px;
            width:215px;
        }
        .qualitysearch{
            border-radius: 5px;
        }
    </style>
    
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
        <div align="center" style="margin:15px 0px -20px 0px;" id="successDiv"><font color="green" size="4">${updatedStatus}</font></div>
        <p class=" page_heading">Customers Action </p>
        <form action="customersList.htm" name="formCustomerList" id="formCustomerList" method="post">
        <div id="datadisplay" class="formContent_new" style="width: 900px">
            <div id="searchdiv" class="statusdiv" style="">
                <label id="searchStatus">Select Status :</label>
                <select name="customerStatus" id="customerStatus" class="formInput required textbox_new" style="width:120px;" >
                    <option value="">-- Select Status -- </option>
                    <c:forEach items="${customerActiveList}" var="deletedStatus" >
                        <c:set var="selsalesPerson" value=""></c:set>
                        <c:if test="${customerStatus == deletedStatus.deleted}">
                            <c:set var="selsalesPerson" value="selected"></c:set>
                        </c:if>
                        <option value="${deletedStatus.deleted}" ${selsalesPerson}>${deletedStatus.deletedName}</option>
                    </c:forEach>
                </select>
                <input type="button" name="btnGo" id="btnGo" value="Go" style="cursor:pointer; width:70px;" class="qualitysearch" >
            </div>

            <c:if test="${fn:length(customerData)>0}">
                <table id="customerDataList"  class="display">
                    <thead>
                    <th>
                        Customer Code
                    </th>    
                    <th width="275px">
                        Customer Name
                    </th>
                    <th width="160px">
                       Division Name
                    </th>
                    <th>
                       BDM/Sales Person
                    </th>
                    <th width="51px">
                        Status
                    </th>
                    <th width="73px">
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
                                <!--${rprt.customerCode}-->
                                <a title="View" target="_self"href="customerView.htm?customerID=${rprt.custID}&&editId=0">${rprt.customerCode}</a>
                            </td>
                            <td>
                                ${rprt.customerName}
                            </td>
                            <td>
                                ${rprt.divisionName}
                            </td>
                            <td>
                                ${rprt.salesPerson}
                            </td>
                            <td>
                                ${rprt.customerStatus}
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${rprt.deleted=='false'}">
                                        <c:if test="${rprt.status == 'a'}">
                                            <p class="makeDeactive" value="${rprt.custID}" style="cursor:pointer;text-decoration: underline;color:blue;" onclick="makedeactive(${rprt.custID},'${rprt.customerName}','${rprt.customerCode}')">Make Inactive</p>
                                        </c:if>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="makeActive" value="${rprt.custID}"  style="cursor:pointer;text-decoration: underline;color:blue;" onclick="makeActivate(${rprt.custID},'${rprt.customerName}','${rprt.customerCode}')">Make Active</p>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:if>
            <c:if test="${fn:length(customerData)==0}">
                <table>
                    <thead>
                        <tr style="text-align:center;">
                            <th style="width: 880px;text-align: center;">No data to display</th>
                        </tr>
                    </thead>
                </table>
            </c:if>
        </div>
            <div id="makeCustomerActivePopupDiv" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119,0.5);display: none; top: 0pt; left: 0pt;color:#000;height:1000px;">
                <div id="makeCustomerActivePopupDivFocus" style="position: absolute;z-index: 150;top: 20%;left:35%;text-align: center;font-size: 14px;background-color: #fff;width: auto;height:auto;padding: 20px;border-radius: 5px;">
                    <p id="customerNamePopup" style="margin:5px 0px 10px 0px; font-weight: bold;"></p>
                    <p style="padding:10px;margin:10px;">Are you sure you want to make this customer as Active?</p><p class="plainButton" id="ActivateCustomerYes">YES</p><p class="plainButton" id="ActivateCustomerNo">NO</p>
                </div>
            </div>
            <div id="makeCustomerInactivePopupDiv" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119,0.5);display: none; top: 0pt; left: 0pt;color:#000;height:1500px;">
                <div id="makeCustomerInactivePopupDivFocus" style="position: absolute;z-index: 150;top: 15%;left:35%;text-align: center;font-size: 14px;background-color: #fff;width: 30%;height:auto;padding: 10px;border-radius: 5px;">
                    <p id="customerNamePopup" style="margin:5px 0px 10px 0px;font-weight: bold"></p>
                    <table id="projectDetails">

                    </table>
                    <div id="projectNotMapped">
                        <p style="padding:10px;margin:10px;">Are you sure you want to make this customer as Inactive?</p><p class="plainButton" id="deactivateCustomerYes">YES</p><p class="plainButton" id="deactivateCustomerNo">NO</p>
                    </div>
                    <div id="projectMapped">
                        <p style="padding:10px;margin:10px;">You cant make this customer as Inactivate it is mapped with above projects</p><p class="plainButton" id="deactivateCustomerOk" style="margin: 0px 45%;">OK</p>
                    </div>
                </div>
            </div>
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

        function submitForm(){
            document.getElementById("reportForm").action="customerList.htm";
            document.reportForm.submit();
            startLoading();
        }

        function getCustomerProjectMapping(selectedValue) {
            $.ajax({
                url: './customerProjectMapping.htm?custID='+selectedValue,
                type: "POST",
                async: false,
                data: ({id:selectedValue}),
                success: function(response) {
                    $('#projectDetails').html(response);
                    return(response);
                }
            });
       }

        $(document).ready(function() {
            $('#customerDataList').dataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                "iDisplayLength" : 20,
                "aaSorting": [[ 1, "asc" ]]
            });
            $("#successDiv").fadeOut(2000);


            $("#btnGo").click(function() {
                var salesPersonIDVal=document.getElementById("customerStatus").value; 
                if(salesPersonIDVal=="0" || salesPersonIDVal=="1"){
                    $("#formCustomerList").attr("action","financeViewSearchCustomer.htm");
                    $("#formCustomerList").submit();
                }
            });     
        });
        function makedeactive(customerID,customerName,customerCode){
            var deactiveVal=customerID;
            $("#makeCustomerInactivePopupDiv").css({"display":"block"});
            $("p#customerNamePopup").text(customerCode+" -- "+customerName);
            getCustomerProjectMapping(deactiveVal);
            var tableLength = document.getElementById('projectDetails').rows.length;
            if(tableLength==0){
                $("#makeCustomerInactivePopupDivFocus").css({"width":"30%","top":"15%","left":"35%"});
                $("#projectDetails").css({"display":"none"});
                $("#projectMapped").css({"display":"none"});
                $("#projectNotMapped").css({"display":"block"});
                $("body").on('click','#deactivateCustomerYes',function(){
                    $("#makeCustomerInactivePopupDiv").css({"display":"none"});
                    $("#formCustomerList").attr("action","updateCustomerActiveInactive.htm?deleted="+'1'+"&custId="+deactiveVal);
                    $("#formCustomerList").submit();
                });
                $("body").on('click','#deactivateCustomerNo',function(){
                    $("#makeCustomerInactivePopupDiv").css({"display":"none"});
                });
            }else{
                $("#makeCustomerInactivePopupDivFocus").css({"width":"45%","top":"10%","left":"25%"});
                $("#projectDetails").css({"display":"table"});
                $("#projectNotMapped").css({"display":"none"});
                $("#projectMapped").css({"display":"block"});
                $("body").on('click','#deactivateCustomerOk',function(){
                    $("#makeCustomerInactivePopupDiv").css({"display":"none"});

                });
            }

        }

        function makeActivate(customerId,customerName,customerCode){
                $("#makeCustomerActivePopupDiv").css({"display":"block"});
                $("p#customerNamePopup").text(customerCode+" -- "+customerName);
                $("body").on('click','#ActivateCustomerYes',function(){
                    $("#makeCustomerActivePopupDiv").css({"display":"none"});
                    $("#formCustomerList").attr("action","updateCustomerActiveInactive.htm?deleted="+'0'+"&custId="+customerId);
                    $("#formCustomerList").submit();
                });
                $("body").on('click','#ActivateCustomerNo',function(){
                    $("#makeCustomerActivePopupDiv").css({"display":"none"});
                });
        }

    </script>
</body>
</html>
