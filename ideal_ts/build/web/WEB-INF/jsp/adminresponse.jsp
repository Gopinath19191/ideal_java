<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@include file="header.jsp"  %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <title>Add Response</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autoSuggest.mod.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demos.css" />        
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tp.css" />
    </head>
    <style type="text/css">
        #loadingDiv img{ border: none; }
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
        #feedbacktable td{
            background: none;
            width:25%;
        }
        .even_row{
            background: #FFFFFF
        }
        .odd_row{
            background: #EFF4FA;;
        }
        #feedbacktable tr td label{
            /*color:#666;*/
            font-weight: bold;
            padding-left: 10px;
        }
        form label{
            width:auto;
        }
        .error {
            color: red;
            padding-left: 15px;
            display: none;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#star'+${givenRating}).attr('checked',true);
            var ele = document.getElementById("container");
            $("#loadingDiv").css("height",ele.scrollHeight);
            var stat = document.getElementById("status").value;
            if (stat == 'a' || stat == 'p' || stat == 'o' || stat == 'f' || stat == 's' || stat == 'n'
                || stat == 'hw' || stat == 'hc' || stat == 'hp' || stat == 'an' || stat == 't') {
                $('#toggleActualEffort').hide();
            }
            else{
                $('#toggleActualEffort').show();
            }
            $('#status').change(
                function () {
                    var stat = $('#status option:selected').val();
                    var issuetype = document.getElementById("issue_type").value;
                    if(issuetype=='2'){
                        if(stat=='p' || stat =='an'){
                            $('#togglePlannedEffort').show();
                        }else{
                            $('#togglePlannedEffort').hide();
                        }
                        if (stat == 'ur' || stat == 'g') {
                            $('#toggleActualEffort').show();
                        } else {
                            $('#toggleActualEffort').hide();
                        }
                    }
                }
            );

            var issuetype = document.getElementById("issue_type").value;
            if (issuetype == '1' || issuetype == '3') {
                $('#togglePlannedEffort').hide();
            }else{
                var stat = document.getElementById("status").value;
                if(stat=='p' || stat =='an'){
                    $('#togglePlannedEffort').show();
                }else{
                    $('#togglePlannedEffort').hide();
                }
            }
            $('#issue_type').change(
                function () {
                    var issuetype = $('#issue_type option:selected').val();
                    if (issuetype == '1' || issuetype == '3'){
                        $('#togglePlannedEffort').hide();
                    } else {
                        $('#togglePlannedEffort').show();
                    }
                }
            );
        <c:if test="${fn:length(mailCcList)!=0}">
                var mailCcData = eval(<json:array name="items" var="mailCcValues" items="${mailCcList}">
                <json:object>
                    <json:property  name="id" value="${mailCcValues.employee_idd}"/>
                    <json:property name="value" value="${mailCcValues.empName}-${mailCcValues.employeeNumber}"/>
                </json:object>
            </json:array>);
        </c:if>           
                $("#mailCc").autoSuggest('${pageContext.request.contextPath}/getEmployeeNameForCC.htm', {selectedItemProp: "value", asHtmlID:"mailCc", searchObjProps: "value", selectionLimit:10, selectedValuesProp:"id"<c:if test="${fn:length(mailCcList)!=0}">, preFill:mailCcData</c:if>});
                $('#as-values-mailCc').attr("class", "required");
//                $("#assign_engineer").autocomplete("assign_engineer.htm", {
//                    minChars: 1,
//                    width: 350,
//                    matchContains: true
//                });
                
                $('.cancelbutton_TS').click(function(){
                    $('.cancelbutton_TS').attr("disabled", true);
                    $('.cancelbutton_TS').css("cursor", "not-allowed");  
                })
            });        
            function saveFeedbackResponse(refNo, empName) {
                var response = document.getElementById("response").value;
                var status = document.getElementById("status").value;
                var area = document.getElementById("application_area").value;
                var issuetype =  document.getElementById("issue_type").value;
                //                var planned_effort = document.getElementById("planned_effort").value;                    
                //                var actual_effort =  document.getElementById("actual_effort").value;
                var sub_unit = document.getElementById("sub_unit").value;
                var assign_engineer= document.getElementById("assign_engineer").value;
                var error_count=0;
                if (response == null || response == "") {
                    $("#response_error").css("display","block");
                    error_count++;
                }else{
                    $("#response_error").css("display","none");
                } 
                if (status ==   null || status == "" || status == "-99") {
                    $("#status_error").css("display","block");
                    error_count++;
                }else{
                    $("#status_error").css("display","none");
                } 
                if (issuetype ==   null || issuetype == "" || issuetype == "-99") {
                    $("#request_type_error").css("display","block");
                    error_count++;
                }else{
                    $("#request_type_error").css("display","none");
                } 
                if (area ==   null || area == "" || area == "-99") {
                    $("#request_area_error").css("display","block");
                    error_count++;
                }else{
                    $("#request_area_error").css("display","none");
                } 
                if(assign_engineer == null || assign_engineer == ""){
                    $("#assign_engineer_error").css("display","block");
                    error_count++;
                }else{
                    $("#assign_engineer_error").css("display","none");
                } 
                if(sub_unit == null || sub_unit == ""){
                    $("#sub_unit_error").css("display","block");
                    error_count++;
                }else{
                    $("#sub_unit_error").css("display","none");
                }
                if(error_count>0){
                    return false;
                }else{
                    $('#editFeedbackPage').attr("action", "insertResponse.htm?refNo="+refNo+"&empName=" + empName);
                    $('.submitbutton_TS').attr("disabled", true);
                    $('.submitbutton_TS').css("cursor", "not-allowed");
                    var ele = document.getElementById("container");
                    $("#loadingDiv").css("height",ele.scrollHeight);
                    document.editFeedbackPage.submit();
                    startLoading();
                    return true;
                }
            }
            function showFeedbacksList() {
                $('#editFeedbackPage').attr("action", "showFeedbacksList.htm");
                document.editFeedbackPage.submit();
                startLoading();
            }
            function downloadFile(filePath, originalName) {
                $('#editFeedbackPage').attr("action", "downloadFile.htm?filePath=" + filePath + "&originalName=" + originalName);
                document.editFeedbackPage.submit();
            }
            function showList() {
                $('#editFeedbackPage').attr("action", "showListOnCancel.htm");
                document.editFeedbackPage.submit();
                startLoading();
            }
            function getStatusList() {
                var issueId = document.getElementById("issue_type").value;
                $.ajax({
                    url: "getSelectedStatus.htm",
                    data: "issueId=" + issueId,
                    type: "post",
                    success: function(result)
                    {
                        jsonResult = jQuery.parseJSON(result);
                        var options = "";
                        $.each(jsonResult, function(index, statusVar)
                        {
                            options = options + "<option value=" + statusVar.statusKey + ">" + statusVar.statusValue + "</option>";
                        });

                        $("#status").html(options);
                    }
                });
            }

            //                function setIssueTypeValue(val){
            //                if(val != null && val != ''){
            //                    $("#issueTypeX").val(val);
            //                }
            //            }
            function setAppTypeValue(val){
                if(val != null && val != ''){
                    $("#appTypeX").val(val);                    
                }
            }
            function changeRequestType(selectedValue, url, outputId, outputDisplayText, outputOptValue, outputOptText, selectedId){
                var structId = document.getElementById("support_type").value;
                if(outputId === "issue_type"){
                    $("#supportTypeX").val(selectedValue);
                }        
                else if(outputId === "application_area"){
                    $("#issueTypeX").val(selectedValue);                   
                }
                var last_unit = document.getElementById("supportTypeX").value;
                
                if (selectedValue != "") {
                    $.ajax({
                        url: url,
                        type: "GET",
                        async: false,
                        data: ({structureId: structId}),
                        success: function(ajaxObj) {
                            var returnData = eval(ajaxObj);
                            var arrayLength = returnData.length;
                            
                            var selectObj = document.getElementById(outputId);
                            selectObj.length = 0;
                            $('#' + outputId).append($("<option></option>").attr("value", "").text(outputDisplayText));
                            for (counter = 0; counter < arrayLength; counter++) {                                
                                var str = returnData[counter][outputOptText].replace(/\&amp;/g,'&');
                                $('#' + outputId).append($("<option></option>").attr("value", returnData[counter][outputOptValue]).text(str));
                            }
                        }
                    });
                    $("#issue_type").html("<option value=''>-- Select --</option>");
                }
                if(last_unit!=structId){
                    var selecting = "<option value=''>-- Select --</option>";
                    $("#issue_type").html(selecting);
                    $("#application_area").html(selecting);
                    $("#status").html("<option value='o'>Open</option>");
                }else{
                    var current_status = document.getElementById("lastStatus").value;
                    var current_request = document.getElementById("issueTypeX").value;
                    var selecting = "<option value=''>-- Select --</option>";
                    $("#issue_type").html(selecting);
                    $("#application_area").html(selecting);
                    var ticket_id = document.getElementById(ticket_id);
                    $.ajax({                   
                        url: './getNextStatus.htm',
                        type: "post",
                        async: false,
                        data: ({status_id:current_status,issue_type:current_request}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#status").html('').html(ajaxObj);
                        }
                    });
                }
            }
                
            function getIssueType(selectedValue, url, outputId, outputDisplayText, outputOptValue, outputOptText, selectedId){
                var structId = document.getElementById("support_type").value;
                if (selectedValue != "") {
                    $.ajax({
                        url: url,
                        type: "GET",
                        async: false,
                        data: ({structureId: structId}),
                        success: function(ajaxObj) {
                            $("#issue_type").html('').html(ajaxObj);
                            $("#application_area").html('<option>-- Select -- </option>');
//                            var returnData = eval(ajaxObj);
//                            var arrayLength = returnData.length;
//                            
//                            var selectObj = document.getElementById(outputId);
//                            selectObj.length = 0;
//                            $('#' + outputId).append($("<option></option>").attr("value", "").text(outputDisplayText));
//                            for (counter = 0; counter < arrayLength; counter++) {                                
//                                var str = returnData[counter][outputOptText].replace(/\&amp;/g,'&');
//                                $('#' + outputId).append($("<option></option>").attr("value", returnData[counter][outputOptValue]).text(str));
//                                //alert(returnData[counter][outputOptText]);
//                            }
                        }
                    });
                }
            }
            
            function getAssignEngineer(){
                var unit_id = document.getElementById("support_type").value;
                var sub_unit_id = document.getElementById("sub_unit").value;
                $.ajax({                   
                        url: './getSupportEngineerList.htm',
                        type: "post",
                        async: false,
                        data: ({support_type:unit_id,sub_unit:sub_unit_id}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#assign_engineer").html('').html(ajaxObj);
                        }
                    });
            }
                
            function getApplicationArea(selectedValue, url, outputId, outputDisplayText, outputOptValue, outputOptText, selectedId){
                var structId = document.getElementById("sub_unit").value;
                var issueId = document.getElementById("issue_type").value;
                if (selectedValue != "") {
                    $.ajax({
                        url: url,
                        type: "GET",
                        async: false,
                        data: ({structureId: structId,issue_type :issueId}),
                        success: function(ajaxObj) {
                            var returnData = eval(ajaxObj);
                            var arrayLength = returnData.length;
                            
                            var selectObj = document.getElementById(outputId);
                            selectObj.length = 0;
                            $('#' + outputId).append($("<option></option>").attr("value", "").text(outputDisplayText));
                            for (counter = 0; counter < arrayLength; counter++) {                                
                                var str = returnData[counter][outputOptText].replace(/\&amp;/g,'&');
                                $('#' + outputId).append($("<option></option>").attr("value", returnData[counter][outputOptValue]).text(str));
                                //alert(returnData[counter][outputOptText]);
                            }
                        }
                    });
                }
                var last_unit = document.getElementById("supportTypeX").value;
                var structId = document.getElementById("support_type").value;
                var current_status = document.getElementById("lastStatus").value;
                var current_request = document.getElementById("issueTypeX").value;
                if(last_unit!=structId){
                    $("#status").html("<option value = 'o'>Open</option>");
                }else{
                    var current_request = document.getElementById("issue_type").value;
                    $.ajax({                   
                        url: './getNextStatus.htm',
                        type: "post",
                        async: false,
                        data: ({status_id:current_status,issue_type:current_request}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#status").html('').html(ajaxObj);
                        }
                    });
                }
            }
                
        </script>
        <body>
            <div id="loadingDiv" style="position:absolute;width:100%;background-color:rgba(0,0,0,0.5);display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 275px; left: 525px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner" style="margin: 0px;">
                <div class="page_heading" style="padding-top: 60px;margin:0px 10px 10px 0px;">
                    Response Ticket
                    <div style="float: right;color: #4C83B3;font-weight: bold;font-size: 12px;">
                        <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="Back" alt="">
                        <a href="javascript:history.go(-1)" style="text-decoration:none;color: #4C83B3;margin-right: 10px;" target="_self">Back </a>
                        <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="List Title" alt="">
                        <a href="showEmployees.htm" style="text-decoration:none;color: #4C83B3;" target="_self" >List Tickets</a>
                    </div>
                </div>
            </div>

            <div id="commonforms">
                <div id="commonformbg">
                    <div class="feedbacks form" style="background: white">
                        <form method="post"  name="editFeedbackPage" id="editFeedbackPage" enctype="multipart/form-data">

                            <%
                                String id = (String) request.getAttribute("sid");
                                String refNo = (String) request.getAttribute("ref_no");
                            %>
                            <table id="feedbacktable"  border="0" width="98%" style="padding-top: 10px;">
                                <tbody>
                                    <%
                                        int i = 0;
                                    %>
                                    <c:forEach items="${item}" var="item" varStatus="dataStatus">
                                        <% i++;
                                            if (i == 1) {
                                        %>
                                    <input type="hidden" id="ticket_id" name="id" value="${item.id}" >
                                    <input type="hidden" name="mailId" value="${item.mailId}" >
                                    <input type="hidden" name="empNumber" value="${item.empNumber}">
                                    <input type="hidden" name="closed_date" id="replied_dat" value="${r_date}">
                                    <input type="hidden" name="fileName" id="fileName" value="${item.attachment_file_path}">
                                    <tr class="odd_row" style="text-align: center;font-size: 15px;font-weight: bold;color:#666666;">
                                        <td colspan="4">
                                            Reference Number -                           		
                                            <input type="hidden" name="ref_n" value=" ${item.ref_no}" >
                                            ${item.ref_no}
                                        </td>
                                    </tr>
                                    <tr class="even_row">
                                        <td><label>Employee</label></td>
                                        <td>${item.empNumber} - ${item.responderName}</td>
                                        <td><label>Created On</label></td>
                                        <td>${item.created_date}<input type="hidden" name="created_date_mail" value="${item.created_date}"></td>
                                    </tr>
                                    <tr class="odd_row">
                                        <td><label>Support Unit</label></td>
                                        <td>${item.supportName}</td>
                                        <td><label>Request Type</label></td>
                                        <td>${item.issue_type}</td>
                                    </tr>
                                    <tr class="even_row">
                                        <td><label>Domain</label></td>
                                        <td>${item.sub_Unit_Name}</td>
                                        <td><label>Request Area</label></td>
                                        <td>${item.application_area}</td>
                                    </tr>
                                    <tr class="odd_row">
                                        <td><label>Contact No</label></td>
                                        <td>${item.contact_no}</td>
                                        <td><label>Location</label></td>
                                        <td>${item.location}</td>
                                    </tr>
                                    <tr class="even_row">
<!--                                        <td><label>System</label></td>
                                        <td>${item.system}</td>-->
                                        <td><label>Priority</label></td>
                                        <td>${item.priority}</td>
                                    </tr>
                                    <tr class="odd_row">
                                        <td><label>Subject</label></td>
                                        <td colspan="3">${item.subject}</td>
                                    </tr>    
                                    <% }%>
                                    <tr class="even_row">
                                        <td><label>Description</label></td>
                                        <td colspan="3">
                                            ${item.response}
                                            <input type="hidden" name="conv_resp" value=" ${item.response}" >
                                            <input type="hidden" name="conv_sts" value=" ${item.status}" >
                                            <input type="hidden" name="user_created" value=" ${item.user_created}" >  
                                            <input type="hidden" name="admin_created" value=" ${item.admin_created}" >
                                            <input type="hidden" name="response_time" value=" ${item.response_time}" >
                                            <input type="hidden" name="responder_name" id="fileName" value="${item.responderName}">
                                        </td>
                                    </tr>
                                    <tr class="odd_row">
                                        <td><label>Status</label></td>
                                        <td colspan="3">${item.status}</td>
                                    </tr>
                                    <%if (i != 1) {%>
                                    <tr class="even_row">
                                        <td><label>Responded By</label></td>
                                        <td colspan="3">${item.responderName}</td>
                                    </tr>
                                    <tr class="odd_row">
                                        <td><label>Responded On</label></td>
                                        <td colspan="3">
                                            ${item.replied_date}
                                            <input type="hidden" name="replied_date_mail" value="${item.replied_date}">
                                        </td>
                                    </tr>
                                    <%}%>
                                    <tr class="even_row">
                                        <td><label>Attachment</label></td>
                                        <td colspan="3">
                                            <a  href ="javascript:;" onclick="downloadFile('${item.fullFileName}', '${item.attachment_file_path}');">${item.attachment_file_path}</a>
                                            <input type="hidden" readonly id="fullFileName" name="fullFileName" value="${item.attachment_file_path}"/>
                                        </td>
                                    </tr>
                                    <tr class="odd_row">
                                        <td><label>Mail CC</label></td>
                                        <td colspan="3">
                                            <input type="hidden" readonly id="mailCcNameList" name="mailCcNameList" value="${mailCcNameList}"/>
                                            ${mailCcNameList}
                                        </td>
                                    </tr>
                                    <%if (i == 1) {%>
                                        <c:if test="${item.sub_unit == '2'}">
<!--                                        <tr class="even_row">
                                            <td><label>IP Address</label></td>
                                            <td colspan="3">${item.ip_address}</td>
                                        </tr>    -->
                                        </c:if>
                                    <%}%>
                                    <%if (i != 1) {%>
                                    <tr class="even_row">
                                        <td><label>Assigned Employee</label></td>
                                        <td colspan="3">${assignEmpName}</td>
                                    </tr>
                                    <%}%>
                                    <tr>
                                        <td colspan="4" class="spacing" style="font-weight: bold;"></td>
                                    </tr>
                                </c:forEach>
                                <%
                                    String stat = (String) request.getAttribute("hide");
                                    if ("Closed".equalsIgnoreCase(stat)) {
                                %>
                                <tr>
                                    <td><label>Given rating</label></td>
                                    <td colspan="3">
                                        <div class="disableClass"></div>
                                        <fieldset class="rating1" style="position: relative;">
                                            <legend></legend>
                                            <input type="radio" id="star5" name="rating" value="5" class="ratingclass"/><label class="star" for="star5" title="Excellent">5 stars</label>
                                            <input type="radio" id="star4" name="rating" value="4" class="ratingclass" /><label class="star" for="star4" title="Pretty good">4 stars</label>
                                            <input type="radio" id="star3" name="rating" value="3" class="ratingclass" /><label class="star" for="star3" title="Average">3 stars</label>
                                            <input type="radio" id="star2" name="rating" value="2" class="ratingclass" /><label class="star" for="star2" title="Bad">2 stars</label>
                                            <input type="radio" id="star1" name="rating" value="1" class="ratingclass" /><label class="star" for="star1" title="Worse">1 star</label>
                                        </fieldset>
                                    </td>
                                </tr>
                                <%                                        }
                                %>
                                <%
                                    String s = (String) request.getAttribute("hide");
                                    if (!"Closed".equals(s) && !"Waiting Feedback".equals(s) && !"Go Live".equals(s)) {
                                %>  
                                <tr>
                                    <!--<input type="hidden" value="${hide}" id="status"/>-->
                                    <td valign="top"><div class="page_heading">Add Response</div></td>
                                </tr>
                                <tr class="odd_row">
                                <input type="hidden" name="supportTypeX" id="supportTypeX" value="${lastSupportType}"/>
                                <td><label>Support Unit <span style="color:red;" >*</span></label></td>
                                <td colspan="3">
                                    <select name="support_type" id="support_type" class="issueType" onchange="changeRequestType(this.value,'./getSubUnitList.htm','sub_unit', '--Select--', 'subUnitId', 'sub_Unit_Name',this.id)">
                                        <c:forEach items="${supportTypeList}" var="supprotType" >
                                            <c:set var="selectedStatusValue" value=""></c:set>
                                            <option value="${supprotType.supportId}" ${lastSupportType == supprotType.supportId?'selected="selected"':''}>${supprotType.supportName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                </tr>
                                <tr class="even_row">
                                <input type="hidden" name="subTypeX" id="subTypeX" value="${lastSubUnitType}"/>
                                <td><label>Domain <span style="color:red;" >*</span></label></td>
                                <td colspan="3">
                                    <select name="sub_unit" id="sub_unit" class="subType" onchange="getIssueType(this.value, './getRequestTypes.htm', 'issue_type', '--Select--', 'configuration_key', 'configuration_value', this.id);getAssignEngineer()">
                                        <c:forEach items="${subUnitList}" var="subUnitType" >
                                            <c:set var="selectedStatusValue" value=""></c:set>
                                            <option value="${subUnitType.subUnitId}" ${lastSubUnitType == subUnitType.subUnitId?'selected="selected"':''}>${subUnitType.sub_Unit_Name}</option>
                                        </c:forEach>
                                    </select><span id ="sub_unit_error" class="error">Please select sub unit</span>
                                </td>
                                </tr>
                                <tr class="odd_row">
                                <input type="hidden" name="issueTypeX" id="issueTypeX" value="${lastIssueType}"/>
                                <td><label>Request Type <span style="color:red;" >*</span></label></td>
                                <td colspan="3">
                                    <select name="issue_type" id="issue_type" class="issueType" onchange="getApplicationArea(this.value, './getRequestAreas.htm', 'application_area', '--Select--', 'configuration_key', 'configuration_value', this.id)">
                                        <c:forEach items="${issueTypeList}" var="issueType" >
                                            <c:set var="selectedStatusValue" value=""></c:set>
                                            <option value="${issueType.configuration_key}" ${lastIssueType == issueType.configuration_key?'selected="selected"':''}>${issueType.configuration_value}</option>
                                        </c:forEach>
                                    </select><span id ="request_type_error" class="error">Please select request type</span>
                                </td>
                                </tr>
                                <tr class="even_row">
                                <input type="hidden" name="appTypeX" id="appTypeX" value="${lastApptype}"/>
                                <td><label>Request area <span style="color:red;" >*</span></label></td>
                                <td colspan="3">
                                    <select name="application_area" id="application_area" class="appType" onchange="setAppTypeValue(this.value);">
                                        <c:forEach items="${appTypeList}" var="appType" >
                                            <c:set var="selectedStatusValue" value=""></c:set>
                                            <option value="${appType.appKey}" ${lastApptype == appType.appKey?'selected="selected"':''}>${appType.appValue}</option>
                                        </c:forEach>
                                    </select><span id ="request_area_error" class="error">Please select request area</span>
                                </td>
                                </tr>
                                <tr class="odd_row">
                                    <td><label>Assigned Employee <span style="color:red;" >*</span></label></td>
                                    <td colspan="3">
<!--                                        <input type="text" id="assign_engineer"  name="assign_engineer"  style="border:1px solid #99BBE8;width:225px;height:20px;padding:2px 2px 1px 3px;" 
                                               value="${filterData.empName == '' || filterData.empName == null? assignEmpName : filterData.empName}"  
                                               onblur="if (this.value == '') this.value = ${assignEmpName};" 
                                               onfocus="if (this.value == ${assignEmpName}) this.value = '';" /> -->
                                        <select name="assign_engineer" id="assign_engineer" class="appType" >
                                            <option value="">-- Select --</option>
                                            <c:forEach items="${assignEngineerList}" var="engineer" >
                                                <option value="${engineer.assignEngineerId}" ${assignedEmployee == engineer.assignEngineerId?'selected="selected"':''}>${engineer.assignEngineerName}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="hidden" name="assigneng" id="assigneng" value="${assignEmpName}"/>
                                        <span id ="assign_engineer_error" class="error">Please assign the employee</span>
                                    </td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>Response <span style="color:red;" >*</span></label></td>
                                    <td colspan="3">
                                        <textarea  style="width: 557px; min-width: 547px; max-width: 547px; margin: 0px; height: 30px; min-height: 20px;max-height: 100px;border:1px solid #99BBE8;"  id="response" name = "response"  class="textarea"></textarea>
                                        <span id ="response_error" class="error" style="padding-top:10px;">Please provide response</span>
                                    </td>
                                </tr>
                                <tr class="odd_row">
                                    <td><label>File</label></td>
                                    <td colspan="3">
                                        <input type="file" value="" id="fileAttachment" name="file" style="width:500px;">
                                    </td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>Mail CC</label></td>
                                    <td colspan="3">
                                        <input type="text" id="mailCc"  name="mailCc"  style="border:1px solid #99BBE8;" value="" />  
                                        ${mailCcName}
                                    </td>
                                </tr>
                                <tr id="togglePlannedEffort" class="odd_row">
                                    <td><label>Planned effort (Hours)</label></td>
                                    <td colspan="3">
                                        <input type="text" id="planned_effort"  name="planned_effort" value="${plannedEffort}" style="border:1px solid #b5b8c8;"/>
                                    </td>
                                </tr>
                                <tr id="toggleActualEffort" class="even_row">
                                    <td><label>Actual effort (Hours)</label></td>
                                    <td colspan="3">
                                        <input type="text" id="actual_effort"  name="actual_effort"  value="${actualEffort}" style="border:1px solid #b5b8c8;"/>
                                    </td>
                                </tr>
                                <tr class="odd_row">
                                    <td><label>Feedback Status <span style="color:red;" >*</span></label></td>
                                    <td colspan="3">
                                        <input type="hidden" name="lastStatus" id ="lastStatus" value="${lastStatus}"/>
                                        <select id="status" name="status">
                                            <c:forEach items="${statusList}" var="statusVar" >
                                                <c:set var="selectedStatusValue" value=""></c:set>
                                                <c:if test="${lastStatus == statusVar.statusValue}">
                                                    <c:set var="selectedStatusValue" value="selected='selected'"></c:set>
                                                </c:if>
                                                <option value="${statusVar.statusKey}" ${lastStatus == statusVar.statusValue?'selected="selected"':''}>${statusVar.statusValue}</option>
                                            </c:forEach>
                                        </select><span id ="status_error" class="error">Please select the status</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" style="text-align: center;">
                                        <input type="button" value="Submit" class="submitbutton_TS" onclick="saveFeedbackResponse();"/>&nbsp;&nbsp;&nbsp
                                        <input type="button" value="Cancel" class="cancelbutton_TS" onclick="history.go(-1);"/>
                                    </td>
                                </tr>

                                <%   }
                                %>
                                </tbody>
                            </table>
                        </form>
                        <br>
                    </div>
                </div>
            </div>
            <br>
            <script type="text/javascript">
                var loadingDivObj = (document.all);
                var ns4 = document.layers;
                var ns6 = document.getElementById && !document.all;
                var ie4 = document.all;
                if (ns4) {
                    loadingDivObj = document.loadingDiv;
                } else if (ns6) {
                    loadingDivObj = document.getElementById("loadingDiv").style;
                } else if (ie4) {
                    loadingDivObj = document.all.loadingDiv.style;
                }
                stopLoading();
                function stopLoading() {
                    if (ns4) {
                        loadingDivObj.visibility = "hidden";
                    }
                    else if (ns6 || ie4)
                        loadingDivObj.display = "none";
                }
                function startLoading() {
                    if (ns4) {
                        var he = window.outerHeight;
                        $("#loadingDiv").css("height",he);
                        loadingDivObj.visibility = "visible";
                    }
                    else if (ns6 || ie4)
                        loadingDivObj.display = "block";
                }
            </script>
            <style>
            fieldset {
                border: 0px;
                margin: 0px;
                padding: 0px;
            }
            .hover_bkgr_fricc{
                background:rgba(0,0,0,.4);
                cursor:pointer;
                display:none;
                position: absolute;
                text-align:center;
                top:0px;
                left: 0;
                width:100%;
                z-index:10000;
                height: 100%;
            }
            .hover_bkgr_fricc .helper{
                display:inline-block;
                height:100%;
                vertical-align:middle;
            }
            .hover_bkgr_fricc > div {
                height: 460px;
                width: 100%;
                border-radius: 8px;
                float: left;
            }
            .popupCloseButton {
                background-color: #fff;
                border: 3px solid #999;
                border-radius: 50px;
                cursor: pointer;
                display: inline-block;
                font-family: arial;
                font-weight: bold;
                position: absolute;
                right: 0px;
                font-size: 17px;
                line-height: 22px;
                width: 20px;
                height: 20px;
                text-align: center;
            }
            .popupCloseButton:hover {
                background-color: #ccc;
            }
            .rating {
                float:left;
            }

            /* :not(:checked) is a filter, so that browsers that don’t support :checked don’t 
               follow these rules. Every browser that supports :checked also supports :not(), so
               it doesn’t make the test unnecessarily selective */
            .rating:not(:checked) > input {
                position:absolute;
                /*                top:-9999px;*/
                clip:rect(0,0,0,0);
            }

            .rating:not(:checked) > label {
                float:right;
                width:1em;
                padding:0 .1em;
                overflow:hidden;
                white-space:nowrap;
                cursor:pointer;
                font-size:200%;
                line-height:1.2;
                color:#ddd;
                text-shadow:1px 1px #bbb, 2px 2px #666, .1em .1em .2em rgba(0,0,0,.5);
            }

            .rating:not(:checked) > label:before {
                content: '★ ';
            }

            .rating > input:checked ~ label {
                color: #f70 !important;
                text-shadow:1px 1px #c60, 2px 2px #940, .1em .1em .2em rgba(0,0,0,.5);
            }

            .rating:not(:checked) > label:hover,
            .rating:not(:checked) > label:hover ~ label {
                color: gold;
                text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
            }

            .rating > input:checked + label:hover,
            .rating > input:checked + label:hover ~ label,
            .rating > input:checked ~ label:hover,
            .rating > input:checked ~ label:hover ~ label,
            .rating > label:hover ~ input:checked ~ label {
                color: #ea0;
                text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
            }

            .rating > label:active {
                position:relative;
                top:2px;
                left:2px;
            }
            /*            to disable rating*/
            .rating1 {
                float:left;
            }

            /* :not(:checked) is a filter, so that browsers that don’t support :checked don’t 
               follow these rules. Every browser that supports :checked also supports :not(), so
               it doesn’t make the test unnecessarily selective */
            .rating1:not(:checked) > input {
                position:absolute;
                /*                top:-9999px;*/
                clip:rect(0,0,0,0);
            }

            .rating1:not(:checked) > label {
                float:right;
                width:1em;
                padding:0 .1em;
                overflow:hidden;
                white-space:nowrap;
                cursor:pointer;
                font-size:200%;
                line-height:1.2;
                color:#ddd;
                text-shadow:1px 1px #bbb, 2px 2px #666, .1em .1em .2em rgba(0,0,0,.5);
            }

            .rating1:not(:checked) > label:before {
                content: '★ ';
            }

            .rating1 > input:checked ~ label {
                color: #f70 !important;
                text-shadow:1px 1px #c60, 2px 2px #940, .1em .1em .2em rgba(0,0,0,.5);
            }

            .rating1:not(:checked) > label:hover,
            .rating1:not(:checked) > label:hover ~ label {
                color: gold;
                text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
            }

            .rating1 > input:checked + label:hover,
            .rating1 > input:checked + label:hover ~ label,
            .rating1 > input:checked ~ label:hover,
            .rating1 > input:checked ~ label:hover ~ label,
            .rating1 > label:hover ~ input:checked ~ label {
                color: #ea0;
                text-shadow:1px 1px goldenrod, 2px 2px #B57340, .1em .1em .2em rgba(0,0,0,.5);
            }

            .rating1 > label:active {
                position:relative;
                top:2px;
                left:2px;
            }
            .disableClass{
                height: 30px;
                position: absolute;
                z-index: 4;
                padding: 0px;
            }
            
        </style>
        </div>
    </body>
</html>
