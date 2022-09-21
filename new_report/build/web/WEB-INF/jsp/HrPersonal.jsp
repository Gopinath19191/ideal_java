<%-- 
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hr Personal</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datepick.css" type="text/css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
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
                height: 35px;
                width: 881px;
                padding: 10px 0px 0px 15px;
                font-weight: bold;
                background: #e2e8ec url(css/images/box-strip.jpg) repeat-x top;
                border: 2px solid white;

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
                /*                background-color: #f1f1f1;*/
            }

            .display tbody tr.even {
                background-color: #eff4fa;
            }
            .display tbody tr.odd {
                background-color: #ffffff;
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
                font-size:12px;

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
                overflow-x: scroll;
                overflow-y: hidden;
                white-space: nowrap;
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
                $('#reportPage').attr("action", "getPersonalDetails.htm");
                document.reportPage.submit();
            }
			
            function searchMom() {
                var temp=$(".paging .active").text();
                $('#page').val(temp);          
                $('#reportPage').attr("action", "getPersonalDetails.htm");
                document.reportPage.submit();
                startLoading();
            }
				   
        </script>
    </head>
    <body style="margin-left:250px;">
        <div id="container">
            <div class="center_content" style="padding-top: 20px">
                <div class="container_inner" style="margin: 15px 0px;width: 300px;">
                    <p class="page_heading">
                        <span class="heading">Employee Personal Report</span> 
                    </p>
                </div>
                <div class="filterWrap">
                    <div class="filetrInnerWrap">
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
                        <table id="momDataList" class="display" >
                            <thead>
                                <tr>
                                    <th >Title</th>
                                    <th>Employee Name</th>
                                    <th>DOB</th>
                                    <th>PanCard</th>
                                    <th>AdharCard</th>
                                    <th>Joining Date</th>
                                    <th>Resigned Date</th>
                                    <th>Relieving Date</th>
                                    <th>Status</th> 
                                    <th>Location</th> 
                                    <th>Mobile Number</th> 
                                    <th>Personal Email ID</th> 
                                    <th>Permanent Address1</th> 
                                    <th>Permanent Address2</th> 
                                    <th>Pincode</th> 
                                </tr>
                            </thead>
                            <tbody>
                                <c:set var="Count" value="${0}" />
                                <c:set var="flag" value="${0}" />
                                <c:forEach items="${details}" var="item" varStatus="actionStatus">
                                    <c:set var="flag" value="${flag+1}" /> 
                                    <c:set var="Count" value="${Count+1}" /> 
                                    <c:choose>
                                        <c:when test="${Count % 2 == 0}">
                                            <tr class="odd" style="font-size: 12px;">
                                            </c:when>
                                            <c:otherwise>
                                            <tr class="even" style="font-size: 12px;">
                                            </c:otherwise>
                                        </c:choose>
                                        <td><c:out value="${item.title}" /></td>
                                        <td><c:out value="${item.employee_name}" /></td>

                                        <td><c:out value="${item.dob}" /></td>
                                        <td><c:out value="${item.pan_card}" /></td>
                                        <td><c:out value="${item.adhar_card}" /></td>
                                        <td><c:out value="${item.joined_date}" /></td>
                                        <td><c:out value="${item.relieving_date}" /></td>
                                        <td><c:out value="${item.resigned_date}" /></td>
                                        <td><c:out value="${item.status}" /></td>
                                        <td><c:out value="${item.location}" /></td>
                                        <td><c:out value="${item.mobile_number}" /></td>
                                        <td><c:out value="${item.email_id}" /></td>
                                        <td><c:out value="${item.address1}" /></td>
                                        <td><c:out value="${item.address2}" /></td>
                                        <td><c:out value="${item.pin_code}" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <c:if test="${paging[0] > 1}">
                            <%@include file="/WEB-INF/jsp/paging.jsp" %>
                        </c:if>
                    </div>           
                </div>
            </div>
        </div>

        <script type="text/javascript">
            function getExcelReport(){
                $('#reportPage').attr("action", "getPersonalDataXL.htm");
                document.reportPage.submit();
            }
            function getFilterList(){
                $('#reportPage').attr("action", "getPersonalDetails.htm");
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
                    url:"getPersonalGroup.htm",
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
