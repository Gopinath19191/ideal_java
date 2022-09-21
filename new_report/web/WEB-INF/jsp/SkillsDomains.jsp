<%-- 
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Skills and Domains</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datepick.css" type="text/css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>

        <style type="text/css">
            tr:nth-child(even) {
                background: #eff4fa;
            }
            #listIcon{
                font-weight: bold;
                float: right;
                margin: -17px 65px 0px 0px;
                width: 12px;
            }
            #listName{
                font-weight: bold;
                font-size: 15px;
                float: right;
                margin: -14px 7px 0px 0px;
                text-decoration: none;
                color: #666666;
                cursor: pointer;
            }
            div.formContenet_new {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                float: left;
                outline: 1px none;
                padding-bottom: 0;

            }
            table.display {
                margin: 0 auto;
                width: 100%;
            }

            #momDataList th {
                border: none;
                pointer-events: none;
            }

            #datadisplay table tr th {
                padding: 1px 10px;
                vertical-align: middle;
                text-align: middle;
                height: 25px;
                font-size:12px;
            }
            .display thead th {
                border-right: 1px solid rgba(213,225,241,0.55);
            }

            #container {
                text-align: left;
                width: 95%;
                padding: 0px 20px 0px 20px;
                margin: -44px 0px 0px -70px;


            }
            div#container {
                clear: both;

            }
            div {
                display: block;
            }

            body {
                font-family: Arial;
                font-size: 12px;
                /*line-height: 15px;*/
                margin-left: auto;
                margin-right: auto;
                width: 1000px;
                color: #666666;
                margin-top: 44px;
                margin-bottom: 20px;
            }
            .page_heading {
                color: #666666;
                font-size: 18px;
                font-weight: bold;
                display: inline-block;
            }

            form {
                display: block;
                margin-top: 0em;
            }

            a {
                outline: none;
            }
            div.formContent_new {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                float: left;
                margin: 0 0 30px 18px;
                outline: 1px none;
                padding-bottom: 0;
                width: 950px;
            }
            .dataTables_wrapper {
                position: relative;
                clear: both;
                zoom: 1;
            }
            .dataTables_filter {
                font-weight: bold;
                margin-top: -48px;
                margin-left: 15px;
                padding: 2px 0px 0px 15px;
                height: 43px;

            }
            label {
                cursor: default;
            }
            #datadisplay table {
                background: #fff;
                border-top: 0;
                clear: both;
            }
            .display {
                border-collapse: collapse;

            }
            table {
                display: table;
                border-collapse: separate;
                border-spacing: 2px;
                border-color: grey;
            }
            .display thead {
                background-color: #eee !important;
            }
            thead {
                display: table-header-group;
                vertical-align: middle;
                border-color: inherit;
            }
            tr {
                display: table-row;
                vertical-align: inherit;
                border-color: inherit;

            }
            .display tbody {
                font-size: 11px !important;
                text-transform: capitalize;
            }


            .display tbody td{
                border-right: 1px solid rgba(213, 225, 241, 0.55);
                border-left:1px solid rgba(213, 225, 241, 0.55);
                border-bottom:1px solid rgba(213, 225, 241, 0.55);
                height: 25px;
            }
            #datadisplay table tr td {
                padding: 1px 10px;
                vertical-align: middle;
                height: 25px;
            }
            #datadisplay th, #associateAllocation th {

                border-bottom: 0 none;
                border-left: 1px solid #EFF4FB;
                font-weight: bolder;
                padding: 5px 10px;
            }
            table.display thead th {
                cursor: pointer;
                cursor: hand;
            }
            .goToList {
                color: #4C83B3;
                float: right;
                font-size: 12px;
                font-weight: bold;
                margin: 20px 40px 18px;
            }
            .qualityback {
                background: url(/ideal_mom/css/images/back.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
                width: 90px;
            }
            .qualitysubmit {
                background: url(/ideal_mom/css/images/icon_btn_submit.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
            }
            .buttonAlignment {
                color: #FFFFFF;
                font-family: arial;
                font-size: 13px;
                font-weight: bold;
            }
            div#btnGroup{
                float:none;
            }
            div#btnGroup {
                text-align: center;
                width: 100%;
                margin: 10px 0px 5px 0px;
            }
            .action tr {
                padding: 0px;
                width: auto;
                cursor:pointer;
            }
            .action:hover {
                background-color: #bacfee;
                width: 93%;
            }
            .historypoint{
                color:#4d85b8;
            }
            form label {
                float: none !important;
                display:inherit !important;

            }
            .export{
                float: right;
                margin: -28px 10px 0px 0px;
            }
            #exportActionList {
                padding-left: 20px;
                background: url(css/images/export-to-excel-button-icon.png) no-repeat 8px 8px #316CA8;
                width: 90px;
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
            #goSearch {
                background: url(css/images/search.png) no-repeat 8px #316CA8;
                border: 1px solid #BCCFEA;
                color: #FFF;
                width: 60px;
                font-weight: bold;
                height: 32px;
                padding-left: 20px;
                margin: 0px 34px 0px 2px;
                border-radius: 10px;
                cursor: pointer;
                clear: both;
                float: none;

            }
            .mom_title_auto_search{
                width: 180px;
                height: 20px;
                padding-left: 20px;
                background: url(css/images/username_icon.png) no-repeat 2px 3px #FFF !important;
            }
            .backview{
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
            }
            div.formContenet_new {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                float: left;
                outline: 1px none;
                padding-bottom: 0;
            }
            #momDataList th {
                border: none;
                pointer-events: none;
            }
            #infoIcon{
                width: 20px;
                padding: 7px 0px 0px 0px;
                vertical-align: bottom;
            }
            #tickIcon{
                width: 10px;
                padding: 6px 0px 0px 0px;
            }
            .InfoText{
                margin: 5px;
            }
        </style>
        <script>
            function loadForm(page) {
                var temp=$(".paging .active").text();
                $('#page').val(page); 
                $('#page_action').val(temp);
                $('#reportPage').attr("action", "getSkillDomainDetails.htm");
                document.reportPage.submit();
            }
			
            function searchMom() {
                var temp=$(".paging .active").text();
                $('#page').val(temp);          
                $('#reportPage').attr("action", "getSkillDomainDetails.htm");
                document.reportPage.submit();
                startLoading();
            }
				   
        </script>
    </head>
    <body style="margin-left:250px;">
        <div id="container">
            <div class="center_content" style="padding-top: 20px" id="successDiv">
                <div class="container_inner" style="margin: 15px 0px;width: 300px;">
                    <p class="page_heading">
                        <span class="heading">Skills Domains Report</span> 
                    </p>
                </div>
                <div class="filterWrap">
                    <div class="filetrInnerWrap" style="height: 55px;">
                        <form name="reportPage" id="reportPage" method="POST">
                            <input type="hidden" id="page" name="page" value="1">
                            <input type="hidden" name="page_action" id="page_action" value="1">
                            <input type="hidden" name="for_paging" id="for_paging">
                            <table style="margin-top: 10px;" id="filter_records">
                                <tbody>
                                    <tr>
                                        <td><label for="sbu" ><b>SBU</b></label></td>
                                        <td>
                                            <select name = "sbu" id ="sbu" style="margin-left: -136px;height: 21px;margin-right: 36px;" onchange="getSubSbu1(this.value);">
                                                <option style="text-align:center;" value="">--All--</option>
                                                <c:forEach items="${sbu}" var = "sbu">
                                                    <option value="${sbu.name}" ${selectedsbu==sbu.name ? 'selected':' '}>${sbu.name}</option>
                                                </c:forEach>
                                            </select> 
                                        </td>
                                        <td><label for="subsbu" ><b>SubSBU</b></label></td>
                                        <td>
                                            <select name = "subsbu" id ="subsbu" style="margin-left: -116px;height: 21px;margin-right: 37px;" >
                                                <option style="text-align:center;" value="">--All--</option>
                                                <c:forEach items="${subsbu}" var = "subsbu">
                                                    <option  value="${subsbu.id}" ${subsbu.id eq selectedsubsbu ? 'selected="selected"':''}>${subsbu.name}</option>
                                                </c:forEach>
                                            </select> 
                                        </td>
                                        <td>
                                            <input class="gobutton" id="goSearch"onclick="searchMom()" type="submit" value="Go"/>
                                        </td>
                                        <td> <input class="exportbutton" type="button" style="margin-left: 20px;border-radius: 17px;width: 100px;cursor: pointer;" onclick="getExcelReport()" value="Export"/>
                                        </td>
                                    </tr> 
                                </tbody>
                            </table>
                        </form>
                    </div>
                </div>
                <div class="formContenet_new" id="datadisplay" style="width: 986px;margin-bottom: 60px;padding: 6px;margin-top: 1px;border-left-width: 0px;border-right-width: 0px;width: 929px;">
                    <div id="momDataList_wrapper">
                        <c:if test="${fn:length(details)>0}">
                            <table id="customerDataList" class="display" >
                                <thead>
                                    <tr>
                                        <th >Employee ID</th>
                                        <th>Employee Name</th>
                                        <th>Practice Group</th>
                                        <th>SubPractice Group</th>
                                        <th>Skills</th>
                                        <th>Domain</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:set var="Count" value="${0}" />
                                    <c:set var="flag" value="${0}" />
                                <script type="text/javascript">$('#datadisplay #customerDataList th').css({'background' : ''});</script>
                                <c:forEach items="${details}" var="item" varStatus="actionStatus">
                                    <c:set var="flag" value="${flag+1}" /> 
                                    <c:set var="Count" value="${Count+1}" /> 
                                    <td><c:out value="${item.employee_number}" /></td>
                                    <td><c:out value="${item.employee_name}" /></td>
                                    <td><c:out value="${item.sbu}" /></td>
                                    <td><c:out value="${item.subsbu}" /></td>
                                    <td><c:out value="${item.skills}" /></td>
                                    <td><c:out value="${item.domains}" /></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table></c:if>
                        <c:if test="${fn:length(details)==0}">
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
            </div>
        </div>

        <script type="text/javascript">
            $(document).ready(function() {
                $('#customerDataList').dataTable({
                    "bLengthChange": false,
                    "sPaginationType": "full_numbers",
                    "iDisplayLength" : 15,
                    "aaSorting": [[ 1, "asc" ]]
                } );

            } );
            function getExcelReport(){
                $('#reportPage').attr("action", "getSkillDomainDataXL.htm");
                document.reportPage.submit();
            }
            function getFilterList(){
                $('#reportPage').attr("action", "getSkillDomainDetails.htm");
                document.reportPage.submit();
                startLoading();
            }
        
            function getSubSbu1(value)
            {          
                var subSbu;
                if(value=="Delivery")
                {
                    subSbu="1";
                }else if(value=="Enabling Functions")
                {
                    subSbu="12";
                }
                else if(value=="Sales"){
                    subSbu="52"
                }
                else if(value=="All" || value=='')
                {
                    subSbu="1,12,52";
                }

                $.ajax({
                    url:"getSkillDomainGroup.htm",
                    data:({parentId:subSbu}),
                    type:"GET",
                    contentType: "text/html",
                    success:function(data){
                        $('#subsbu').html('');
                        $('#subsbu').append(data);
                    }
                });
            }
        
        </script>
    </body> 
</html>
