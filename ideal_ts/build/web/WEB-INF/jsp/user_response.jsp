<%--
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page import="java.util.ArrayList"%>
<%@include file="header.jsp"  %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <title>Add Ticket</title>
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
        fieldset legend{
            color:#666666;
            background: none;
            font-weight: 120%;
        }
        fieldset{
            border:0px;
            margin:0px;
            padding:0px;
        }
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
            font-weight: bold;
            padding-left: 10px;
        }
        form label{
            width:auto;
        }
        
    </style>
    <script type="text/javascript">
        
        $(document).ready(function(){
            $('#star'+${givenRating}).attr('checked',true);
            $("#employee_search_user").autocomplete("employee_search_user.htm", {
                minChars: 1,
                matchContains: true
            });
            var stat = $('#status').val();
            if(stat == 'c'){
                $('#rating_toggle').show();
            }else{
                $('#rating_toggle').hide();
            }
            $('#employee_search_user').result(function(event,data,formatted){
                if(data){
                    $('#employee_id').val(data[1]);
                }
            });
        <c:if test="${fn:length(mailCcList)!=0}">
                var mailCcData = eval(<json:array name="items" var="mailCcValues" items="${mailCcList}">
                <json:object>
                    <json:property  name="id" value="${mailCcValues.employee_idd}"/>
                    <json:property name="value" value="${mailCcValues.empName}-${mailCcValues.employeeNumber}"/>
                </json:object>
            </json:array>);</c:if>
            
                    $("#mailCc").autoSuggest('${pageContext.request.contextPath}/getEmployeeNameForCC.htm', {selectedItemProp: "value", asHtmlID:"mailCc", searchObjProps: "value", selectionLimit:10, selectedValuesProp:"id"<c:if test="${fn:length(mailCcList)!=0}">, preFill:mailCcData</c:if>});
                    $('#as-values-mailCc').attr("class", "required");
                });

                function removeExistingFile(parentId){
                    $.ajax({
                        type: 'POST',
                        url: 'removeExistingFile.htm',
                        async: false,
                        data: ({parent_id: parentId}),
                        success:function(ajaxObj){
                            $("#browse").html('<input  type="file"  name="file" id="file" value=""/>');
                        }
                    });
                }
        
                function saveFeedback(refNo){
                    var response = document.getElementById("response").value;
                    var rating = $('input[name=rating]:checked').val();
                    var stat = $('#status').val();
                    if(response==null || response ==""){
                        alert ("Please enter valid response");
                        return false;
                    }else if(stat == 'c'){
                        if(rating == null || rating =="" || rating == 'undefined'){
                            alert("Please give feedback rating");
                            return false;
                        }else{
                            $('#addFeedbackPage').attr("action", "editOnSaveFeedbackRequest.htm");
                            var ele = document.getElementById("container");
                            $("#loadingDiv").css("height",ele.scrollHeight);
                            document.addFeedbackPage.submit();
                            startLoading();
                            return true;
                        }
                    }else{
                        $('#addFeedbackPage').attr("action", "editOnSaveFeedbackRequest.htm");
                        var ele = document.getElementById("container");
                        $("#loadingDiv").css("height",ele.scrollHeight);
                        document.addFeedbackPage.submit();
                        startLoading();
                        return true;
                    }
                }
                function downloadFileUser(filePath,originalName){
                    $('#addFeedbackPage').attr("action","downloadFile_user.htm?filePath="+filePath+"&originalName="+originalName);
                    document.addFeedbackPage.submit();
                }        
    </script>
</head>
<body class="ext-gecko ext-gecko3" onload="<c:out value="${onloadFn}" />">
    <div id="loadingDiv" style="position:absolute;width:100%;background-color:rgba(0,0,0,0.5);display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 275px; left: 525px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner" style="margin: 0px;">
            <div class="page_heading" style="padding-top: 60px;margin:0px 10px 10px 0px;">
                Response Ticket
                <div style="float: right;color: #4C83B3;font-weight: bold;font-size: 12px;">
                    <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="Back" alt="">
                    <a href="javascript:history.go(-1)" style="text-decoration:none;color: #4C83B3;margin-right: 10px;" target="_self">Back </a>
                    <img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="List Title" alt="">
                    <a href="feedback_user.htm" style="text-decoration:none;color: #4C83B3;" target="_self" onclick="showFeedbacksList();">List Tickets</a>
                </div>
            </div>
        </div>
        <div id="commonforms">
            <div id="commonformbg">
                <div class="feedbacks form" style="background: white">
                    <%
                        String modId = (String) request.getSession().getAttribute("modId");
                        System.out.println("Module Id---" + modId);
                    %>
                    <input type="hidden" id="modId" name="modId" value="<%=modId%>" />
                    <form  name="addFeedbackPage" method="post" id="addFeedbackPage" enctype="multipart/form-data">
                        <%

                            String refNo = (String) request.getAttribute("ref_no");
                        %>                        
                        <table id="feedbacktable"  border="0" width="98%" style="padding-top: 10px;">
                            <tbody>
                            <input type="hidden" name="adminId" id="adminId" value="${adminId}"/>
                            <input type="hidden" name="frId" id="frId" value="${frId}"/>
                            <input type="hidden"  name="replied_date" id="replied_dat" value="${c_date}">
                            <input type="hidden"  name="closed_date" id="replied_dat" value="${c_date}">
                            <input type="hidden" name="mail_cc_id" id="mail_cc_id" value="${mailCCC}"/>
                            <input type="hidden" name="preStatus" id="preStatus" value="${preStatus}"/>
                            <%
                                int i = 0;
                            %>
                            <c:forEach items="${detailsRef}" var="details" varStatus="i">

                                <% i++;
                                    if (i == 1) {

                                %>
                                <tr class="odd_row" style="text-align: center;font-size: 15px;font-weight: bold;color:#666666;">
                                    <input type="hidden" id="support_type" name="support_type" value="${details.support_type}"/>
                                    <input type="hidden" id="ref_no" name="ref_no" value="${details.ref_no}"/>   
                                    <td colspan="4">Reference Number - ${details.ref_no}</td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>Support Unit</label></td>
                                    <td>${details.supportName}</td>
                                    <td><label>Request Type</label></td>
                                    <td>${details.issue_type}</td>
                                </tr>
                                <tr class="odd_row">
                                    <input type="hidden" name="sub_Unit" value="${details.sub_unit}"/>
                                    <td><label>Domain </label></td>
                                    <td>${details.sub_Unit_Name}</td>
                                    <td><label>Request Area</label></td>
                                    <td>${details.application_area}</td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>Contact No</label></td>
                                    <td>${details.contact_no} </td>
                                    <td><label>Location</label></td>
                                    <td>${details.location}</td>
                                </tr>
                                <tr class="odd_row">
<!--                                    <td><label>System</label></td>
                                    <td>${details.system}</td>-->
                                    <td><label>Priority</label></td>
                                    <td>${details.priority}</td>
                                    <td><label>Created On</label></td>
                                    <td>${details.created_date}</td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>Current Status</label></td>
                                    <td>${statuss}</td>
                                    <td></td>
                                    <td></td>
                                </tr>
                                <tr class="odd_row">
                                    <td><label>Subject</label></td>
                                    <td colspan="3">${details.subject}</td>
                                </tr>
                                <% }
                                %>
                                <tr class="even_row">
                                    <td><label>Description</label></td>
                                    <td colspan="3">${details.response} </td>
                                </tr>
                                <%if (i == 1) {%>
                                
                                
                                <%} else {%>
                                <tr class="odd_row">
                                    <td><label>Assigned Engineer</label></td>
                                    <td colspan="3">${details.assignEngineerName}</td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>Responded On</label></td>
                                    <td class="">${details.replied_date}</td>
                                </tr>
                                <%}%>
                                <tr class="odd_row">
                                    <td><label>Attachment</label></td>
                                    <td colspan="3">
                                        <a  href ="javascript:;" onclick="downloadFileUser('${details.fullFileName}','${details.attachment_file_path}');">${details.attachment_file_path}</a>
                                        <input type="hidden" readonly id="fullFileName" name="fullFileName" value="${details.attachment_file_path}"/>
                                    </td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>Mail CC</label></td>
                                    <td colspan="3">
                                        <input type="hidden" readonly id="mailCcNameList" name="mailCcNameList" value="${mailCcNameList}"/>
                                        ${mailCcNameList}
                                    </td>
                                </tr>
                                <%if (i == 1) {%>
                                    <c:if test="${details.sub_unit == '2'}">
                                    <tr class="odd_row">
                                        <td><label>IP Address</label></td>
                                        <td colspan="3">${details.ip_address}</td>
                                    </tr>    
                                    </c:if>
                                <%}%>
                                <tr><td colspan="4" class="spacing"></tr>
                                <input type="hidden" id="parent_id" name="parent_id" value="${details.responseId}"/>
                                <input type="hidden" id="parent_id" name="parent_idToId" value="${details.id}"/>
                                <input type="hidden" name="empNamee" id="empNamee" value="${details.empName}"/>
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
                                if (!"Open".equals(s) && !"Closed".equals(s)) {
                            %>  
                            
                                <tr>
                                    <td valign="top"><div class="page_heading">Add Response</div></td>

                                </tr>
                                <tr class="odd_row">
                                    <td><label>Response <span style="color:red;" >*</span></label></td>
                                    <td colspan="3">
                                        <textarea style="width: 557px; min-width: 547px; max-width: 547px; margin: 0px; height: 30px; min-height: 20px;max-height: 100px;border:1px solid #99BBE8;" id="response" name = "response"  class="textarea"></textarea>
                                    </td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>File</label></td>
                                    <td colspan="3"><input type="file" value="" id="fileAttachment" name="file"></td>
                                </tr>
                                <tr class="odd_row">
                                    <td><label>Mail CC</label></td>
                                    <td colspan="3"><input type="text" id="mailCc"  name="mailCc"  style="border:1px solid #b5b8c8;" value="" />${mailCcName}</td>
                                </tr>
                                <tr class="even_row">
                                    <td><label>Feedback Status <span style="color:red;" >*</span></label></td>
                                    <td colspan="3">                                                    
                                        <select id="status" name="status" onchange="ratingToggle()">                                                        
                                            <c:forEach items="${userStatusList}" var="userStatusValue" varStatus = "i">
                                                <option value="${userStatusValue.statusKey}" ${statuss == userStatusValue.statusValue?'selected="selected"':''}>${userStatusValue.statusValue}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </tr>                                            
                                <tr class="odd_row" id="rating_toggle">
                                    <td><label>Feedback Rating</label></td>
                                    <td colspan="3">
                                        <fieldset class="rating" style="position: relative;">
                                            <legend></legend>
                                            <input type="radio" id="star5" name="rating" value="5" class="ratingclass"/><label class="star" for="star5" title="Excellent">5 stars</label>
                                            <input type="radio" id="star4" name="rating" value="4" class="ratingclass" /><label class="star" for="star4" title="Pretty good">4 stars</label>
                                            <input type="radio" id="star3" name="rating" value="3" class="ratingclass" /><label class="star" for="star3" title="Average">3 stars</label>
                                            <input type="radio" id="star2" name="rating" value="2" class="ratingclass" /><label class="star" for="star2" title="Bad">2 stars</label>
                                            <input type="radio" id="star1" name="rating" value="1" class="ratingclass" /><label class="star" for="star1" title="Worse">1 star</label>
                                        </fieldset>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="4" style="text-align: center;">
                                        <input type="button" value="Submit" class="submitbutton_TS" onclick="saveFeedback('<%=refNo%>');"/>&nbsp;&nbsp;&nbsp;
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
        <style>
            .ratingclass{
                top: 0px;                
            }
        </style>
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
        <script>
            function ratingToggle(){
                var stat = $('#status').val();
                if(stat == 'c'){
                    $('#rating_toggle').show();
                }else{
                    $('#rating_toggle').hide();
                }
            }
            $('.popupCloseButton').click(function(){
                $('.hover_bkgr_fricc').hide();
            });           
            
            
        </script>
        <style>
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
                color: #f70;
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
                color: #f70;
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


