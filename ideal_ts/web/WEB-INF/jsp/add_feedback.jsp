<%-- 
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp"  %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <title>Add Ticket </title>
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
    <style>
       
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $('.submitbutton_TS').attr("disabled", true);
            $('.submitbutton_TS').css("cursor", "not-allowed");
            $("#employee_search").autocomplete("employee_search_user.htm", {
                minChars: 1,
                matchContains: true
            });
            $('#employee_search').result(function(event, data, formatted) {
                if (data) {
                    $('#employee_id').val(data[1]);
                }
            });
        <c:if test="${fn:length(mailCcList)!=0}">
            var mailCcData = eval(<json:array name="items" var="mailCcValues" items="${mailCcList}">
                <json:object>
                    <json:property name="id" value="${mailCcValues.empId}"/>
                    <json:property name="value" value="${mailCcValues.employeeName}-${mailCcValues.employeeNumber}"/>
                </json:object>
            </json:array>);</c:if>
                            $("#mailCc").autoSuggest('${pageContext.request.contextPath}/getEmployeeNameForCC.htm', {selectedItemProp: "value", asHtmlID:"mailCc", searchObjProps: "value", selectionLimit:10, selectedValuesProp:"id"<c:if test="${fn:length(mailCcList)!=0}">, preFill:mailCcData</c:if>});
                            $('#as-values-mailCc').attr("class", "required");
                });
                
                function saveFeedback(){
                    var subject = document.getElementById("subject").value;
                    var description = document.getElementById("description").value;
                    var supportType = document.getElementById("support_type").value;
                    var subUnitType =document.getElementById("sub_unit").value;
                    var issueType = document.getElementById("issue_type").value;
                    var contact_no = document.getElementById("contact_no").value;
                    var applicationArea = document.getElementById("application_area").value;
//                    var ip_address = document.getElementById("ip_address").value;
//                    if(subUnitType=='2'){
//                        var ipformat = /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
//                        if(!ip_address.match(ipformat)){
//                            alert("You have entered an invalid IP address!");
//                            return false;
//                        }
//                    }
                    if (supportType == null || supportType == "") {
                        alert('Please select support Type');
                        return false;
                    }
                    else if(subUnitType == null || subUnitType == "") {
                        alert('Please select subUnit Type');
                        return false;
                    }
                    else if (issueType == null || issueType == "") {
                        alert('Please select Request Type');
                        return false;
                    }
                    else if (applicationArea == null || applicationArea == "") {
                        alert('Please select Application Area');
                        return false;
                    }        
                    else if (subject == null || subject == "") {
                        alert('Please enter a valid Subject');
                        return false;
                    }
                    else if (description == null || description == "") {
                        alert('Please enter a valid Desciption');
                        return false;
                    }
                    else if(contact_no == null || contact_no == ""){
                        alert('Please enter Contact Number');
                        return false;
                    }else {
                        $('#addFeedbackPage').attr("action", "saveFeedbackRequest.htm");
                        document.addFeedbackPage.submit();
                        startLoading();
                        return true;
                    }
                }
                
                function blockNonNumbers(obj, e, allowDecimal, allowNegative) {
                    var key;
                    var isCtrl = false;
                    var keychar;
                    var reg;

                    if(window.event) {
                            key = e.keyCode;
                            isCtrl = window.event.ctrlKey
                    }
                    else if(e.which) {
                            key = e.which;
                            isCtrl = e.ctrlKey;
                    }

                    if (isNaN(key)) return true;

                    keychar = String.fromCharCode(key);

                    // check for backspace or delete, or if Ctrl was pressed
                    if (key == 8 || isCtrl)
                    {
                            return true;
                    }

                    reg = /\d/;
                    var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
                    var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;

                    return isFirstN || isFirstD || reg.test(keychar);
                }
                
                function showFeedbacksList() {
                    $('.cancelbutton_TS').css("cursor","not-allowed");
                    $('#addFeedbackPage').attr("action", "feedback_user.htm");
                    document.addFeedbackPage.submit();
                    //startLoading();
                }
                
                function showList() {
                    $('#addFeedbackPage').attr("action", "showListOnCancel.htm");
                    document.addFeedbackPage.submit();
                    startLoading();
                }
                function changeRequestType(selectedValue, url, outputId, outputDisplayText, outputOptValue, outputOptText, selectedId){
                //alert('id '+id+' value '+value);
                var structId = document.getElementById("support_type").value;               
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
                                //alert(returnData[counter][outputOptText]);
                            }
                            $("#issue_type").html("<option value=''>-- Select --</option>");
                        }
                    });
                  }
                }
                
                function checkUnitSelect(){
                    var unit = $("#support_type").val();
                    if(unit == null || unit == ""){
                        $("#errorMessage").fadeIn();
                        $("#errorMessage").html("Please Select Unit");
                        $("#errorMessage").css("color","Red");
                        $("#errorMessage").fadeOut(3000);
                    }else{
                        $("#errorMessage").html("");
                    }
                }
                function checkUnitSubUnitSelect(){
                    var unit = $("#support_type").val();
                    var subUnit = $("#sub_unit").val();
                    var reqArea = $("#application_area").val();
                    if((unit == null || unit == "") && (subUnit == null || subUnit == "")){
                        $("#messageToggle").hide();
                        $("#errorMessage").html("");
                        $("#errorMessage").fadeIn();
                        $("#errorMessage").html("Please Select Unit and Sub Unit");
                        $("#errorMessage").css("color","Red");
                        $("#errorMessage").fadeOut(3000);
                    }else if(subUnit == null || subUnit == ""){
                        $("#messageToggle").hide();
                        $("#errorMessage").html("");
                        $("#errorMessage").fadeIn();
                        $("#errorMessage").html("Please Select Sub Unit");
                        $("#errorMessage").css("color","Red");
                        $("#errorMessage").fadeOut(3000);
                    }else if(subUnit == 10 && (reqArea == 95 || reqArea == 96 )){
                        $("#errorMessage").html("");
                        $("#notificationMsg").html("Please specify the Month in Description"); 
                        $("#messageToggle").show();
                    }else if(subUnit == 11 && (reqArea == 97 || reqArea == 98 || reqArea == 99 || reqArea == 100 || reqArea == 101 || reqArea == 102 )){
                        $("#errorMessage").html("");
                        $("#notificationMsg").html("Please specify Reimbursement number, RM Approval date in Description"); 
                        $("#messageToggle").show();
                    }else if(subUnit == 12 && (reqArea == 103 || reqArea == 104 )){
                        $("#errorMessage").html("");
                        $("#notificationMsg").html("Please specify TP number and submitted date in Description"); 
                        $("#messageToggle").show();                        
                    }else if(subUnit == 13 && (reqArea == 105 || reqArea == 106 )){
                        $("#errorMessage").html("");
                        $("#notificationMsg").html("Ensure the Travel request is fully approved"); 
                        $("#messageToggle").show();
                    }else if(subUnit == 14 && reqArea == 107 ){
                        $("#errorMessage").html("");
                        $("#notificationMsg").html("Please specify customer name or PID in Description"); 
                        $("#messageToggle").show();
                    }else if(subUnit == 14 && reqArea == 108 ){
                        $("#errorMessage").html("");
                        $("#notificationMsg").html("Please specify Invoice Number in Description"); 
                        $("#messageToggle").show();
                    }else if(subUnit == 15 && reqArea == 109 ){
                        $("#errorMessage").html("");
                        $("#notificationMsg").html("Please specify Vendor name, PO number, Invoice number"); 
                        $("#messageToggle").show();
                    }else{
                        $("#errorMessage").html("");
                        $("#messageToggle").hide();
                    }
                }
                function validate(){
                    var unit = $("#support_type").val();
                    var sub_unit = $("#sub_unit").val();
                    var contact = $("#contact_no").val();
                    var req_type= $("#issue_type").val();
                    var req_area = $("#application_area").val();
                    var sub = $("#subject").val();
                    var desc = $("#description").val();
                    if((unit != null && unit != "")
                        && (sub_unit !== null && sub_unit != "")
                        && (contact != null && contact != "")
                        && (req_type != null && req_type != "")
                        && (req_area != null && req_area != "")
                        && (sub != null && sub != "")
                        && (desc != null && desc != "")){
                            $('.submitbutton_TS').attr("disabled", false);
                            $('.submitbutton_TS').css("cursor", "pointer");
                    }else{
                        $('.submitbutton_TS').attr("disabled", true);
                        $('.submitbutton_TS').css("cursor", "not-allowed");
                    }
                }
                function getIssueType(selectedValue, url, outputId, outputDisplayText, outputOptValue, outputOptText, selectedId){
                //alert('id '+id+' value '+value);
                    var structId = document.getElementById("sub_unit").value; 
              
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
                                   //alert(returnData[counter][outputOptText]);
                                }
                            }
                        });
                    }
            } 
            function getApplicationArea(selectedValue, url, outputId, outputDisplayText, outputOptValue, outputOptText, selectedId){
                //alert('id '+id+' value '+value);
                var unitId = document.getElementById("support_type").value; 
                var subUnitId = document.getElementById("sub_unit").value;
                var issueId = document.getElementById("issue_type").value;
                if (selectedValue != "") {
                    $.ajax({
                        url: url,
                        type: "GET",
                        async: false,
                        data: ({structureId: subUnitId,issue_type :issueId}),
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
                  }
              }
              
              function getRequestType(){
                var sub_unit_id = document.getElementById("support_type").value;
                    $.ajax({                   
                        url: './getRequestTypes.htm',
                        type: "post",
                        async: false,
                        data: ({structureId:sub_unit_id }),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#issue_type").html('').html(ajaxObj);
                            $("#application_area").html('<option>-- Select -- </option>');
                        }
                    });
                    if(sub_unit_id=='3'){
                        getApplicationArea(this.value, './getRequestAreas.htm', 'application_area', '--Select--', 'configuration_key', 'configuration_value', this.id);
                    }
                    
              }
//              function subUnitValidation(){
//                var sub_unit = $("#sub_unit").val();
//                if(sub_unit=='2'){
//                    $("#ip_address_block").css("display", "table-row");
//                }else{
//                    $("#ip_address_block").css("display", "none");
//                }
//              }
                
        </script>
    </head>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:1400px;background-color:rgba(0,0,0,0.5);display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 275px; left: 525px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="page_heading" style="padding-top: 60px;margin:0px 10px 10px 20px;">
            Add Ticket
            <div style="float: right;color: #4C83B3;font-weight: bold;font-size: 12px;">
                <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="List Pending  Request" alt="">
                <a style="text-decoration:none;color: #4C83B3;" href="#" id="pendingRequest" target="_self" onclick="javascript:history.go(-1)">List Tickets</a>
            </div>
        </div>
        <div class="notice_page" style="width:980px;height:26px;margin:0px;">
            <img src="${pageContext.request.contextPath}/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon" style="float:left;margin-top:-5px;"/>
            <div style="padding-top:3px;float:left;">
                <ul class="notice_page_ul">
                   <li>Fields marked in <span style="color:red;" >*</span> are mandatory</li>
                   <li>Please close the ticket once your issue has been resolved.</li>
                </ul>
            </div>
        </div>
        <br/>
        <%
            String modId = (String) request.getSession().getAttribute("modId");
        %>
        <input type="hidden" id="modId" name="modId" value="<%=modId%>" />
        <div id="commonformbg" style="border-radius:10px;">
            <div style="background: white;border-radius:10px;" align="center">
                <form name="addFeedbackPage" method="post" id="addFeedbackPage" enctype="multipart/form-data" autocomplete="off">
                    <table id="addForm" style="padding-top: 10px;width:900px;">
                        <tbody>
                        <tr> 
                            <td colspan="4" id="errorMessage" style="text-align: center;"></td>
                        </tr>
                        <tr>
                            <c:if test="${adminId=='admin'}">
                                <td> <label><b>On Behalf-Of </b></label></td>
                                <td colspan="3"><input type="text" id="employee_search" name="employee_search" style="width:287px;border:1px solid #99BBE8;height:20px;" value="Search by Employee Number or First/Last name" onfocus="if (this.value == 'Search by Employee Number or First/Last name')
                                        this.value = '';" onblur="if (this.value == '')
                                        this.value = 'Search by Employee Number or First/Last name';" >
                            </c:if>
                        </tr>
                        <tr>
                            <td>
                                <label><b>Support Unit <span style="color:red;" >*</span></b></label>
                            </td>
                            <td>
                                <select name="support_type" id="support_type" onchange="changeRequestType(this.value, './getSubUnitList.htm','sub_unit', '--Select--', 'subUnitId', 'sub_Unit_Name',this.id);validate();">
                                    <option value="">--Select--</option>
                                    <c:forEach items="${supportTypeList}" var="suppotType" >
                                        <option value="${suppotType.supportId}">${suppotType.supportName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label><b>Priority  </b></label>
                            </td>
                            <td>
                                <select id="priority" name="priority">
                                    <option value="Low">Low</option>
                                    <option value="Medium">Medium</option>
                                    <option value="High">High</option>
                                </select>
                            </td>
                        </tr>
                         <tr>
                            <td>
                                <label><b>Domain <span style="color:red;" >*</span> </b></label>
                            </td>
                            <td>
                                <select name="sub_unit" id="sub_unit" onChange="getRequestType();"onClick="checkUnitSelect();">
                                    <option value="">--Select--</option>
                                    <c:forEach items="${subUnitList}" var="subType" >
                                        <option value="${subType.subUnitId}">${subType.sub_Unit_Name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label><b>Contact No<span style="color:red;" >*</span></b></label>
                            </td>
                            <td>
                                <input type="text" id="contact_no" name="contact_no"  class ="textbox" onkeyup="validate();" onkeypress="return blockNonNumbers(this, event, true, false);"/>
                            </td>
                         </tr>
                        <tr>
                            <td>
                                <label><b>Request Type <span style="color:red;" >*</span></b></label>
                            </td>
                            <td>
                                <select name="issue_type" id="issue_type" onchange="getApplicationArea(this.value, './getRequestAreas.htm', 'application_area', '--Select--', 'configuration_key', 'configuration_value', this.id);validate();">
                                     <option value="">--Select--</option>
                                    <c:forEach items="${requestType}" var="issueType" >
                                        <option value="${issueType.configuration_key}">${issueType.configuration_value}</option>
                                    </c:forEach>
                                </select>
                            </td>
<!--                            <td>
                                <label><b>System</b></label>
                            </td>
                            <td>
                                <select name="system" id="system">
                                    <c:forEach items="${systemList}" var="system" >
                                        <option value="${system.configuration_key}" >${system.configuration_value}</option>
                                    </c:forEach>
                                </select>
                            </td>-->
                            <td>
                                <label><b>Location </b></label>
                            </td>
                            <td>
                                <input type ="hidden" name="system" value="d"/>
                                <input type="text" id="location" name="location"  class ="textbox" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label><b>Request Area <span style="color:red;" >*</span></b></label>
                            </td>
                            <td>
                                <select name="application_area" id="application_area" onchange="validate();" onclick="checkUnitSubUnitSelect();">
                                     <option value="">--Select--</option>
                                    <c:forEach items="${appAreaList}" var="appArea" >
                                        <option value="${appArea.configuration_key}" >${appArea.configuration_value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <!--<label><b>Location </b></label>-->
                            </td>
                            <td>
                                <!--<input type="text" id="location" name="location"  class ="textbox" />-->
                            </td>
                        </tr>
                        <tr id="messageToggle" style="display: none">
                            <td></td><td colspan="3"> <span id="notificationMsg" style="color:red;"></span></td>
                        </tr>
<!--                        <tr id="ip_address_block" style="display:none;">
                            <td>
                                <label><b>IP Address <span style="color:red;" >*</span></b></label>
                            </td>
                            <td>
                                <input type="text" id="ip_address" name="ip_address"  class ="textbox" />
                            </td>
                        </tr>-->
                        <tr>
                            <td>
                                <label><b>Subject <span style="color:red;" >*</span></b></label>
                            </td>
                            <td colspan="3">
                                <input type="text" id="subject" name="subject"  class ="textbox" style="width:350px;" onkeyup="validate();"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label><b>Description <span style="color:red;" >*</span></b></label>
                            </td>
                            <td colspan="3">
                                <textarea rows="4" cols="50" id="description" class="textarea_new" name="description" style="width: 557px; min-width: 547px; max-width: 547px; margin: 0px; height: 30px; min-height: 20px;max-height: 50px;" onkeyup="validate();"></textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label><b>Mail CC </b></label>
                            </td>
                            <td colspan="3">
                                <input type="text" id="mailCc" style="border:1px solid #99BBE8;"name="mailCc"  value="" >
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label><b>File Upload</b></label>
                            </td>
                            <td colspan="3">
                                <input type="file" style="width:auto;" name="file" id="file" />
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div>
                        <input class="submitbutton_TS" type="button" onclick="saveFeedback();"  value="Submit"/>
                        <input class="cancelbutton_TS"  type="reset" onclick="javascript:history.go(-1)" value="Cancel" />
                    </div>
                </form>
                <br>
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
                        loadingDivObj.visibility = "visible";
                    }
                    else if (ns6 || ie4)
                        loadingDivObj.display = "block";
                }
        </script>
    </div>
</body>
</html>
