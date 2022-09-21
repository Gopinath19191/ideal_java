<%-- 
    Document   : view_mom
    Created on : 25 Jan, 2018, 2:24:14 PM
    Author     : 8000458
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.datepick.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control" content="no-cache">
        <meta http-equiv="Pragma" content="no-cache">
        <meta http-equiv="Expires" content="0">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cake.generic.css" type="text/css">
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabjQuery.css" type="text/css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>   
        <!--<script src="http://code.jquery.com/jquery-1.9.1.js"></script>-->
        <script src="http://code.jquery.com/jquery-migrate-1.1.1.js"></script>
        <!--<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />-->
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.autocomplete.min.js"></script>
        <!--<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">-->
        <title>View Page</title>
        <style type="text/css">

            .headerCenter th:first-child
            {
                border-left: 1px !important;
            }
            /*        select {
                        padding: 3px 0px 2px 0px;
                        background: url("images/text-bg.gif") repeat-x scroll center top #FFFFFF;
                        border: 1px solid #C4D1E0;
                        font-family: arial;
                        font-size: 12px;
                    }*/
            #loadingDiv img{ border: none; }

            #loadingDiv
            { 
                opacity: 0.8;filter: alpha(opacity = 80); 
                ZOOM: 1
            }

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

            .customer_billing_address td{
                padding:0px;
                width:auto;
            }
            .customer_billing_address tr{
                padding:0px;
                width:auto;
            }
            .customer_billing_address th{
                padding:0px;
                width:auto;
            }
            .customer_billing_address td input[type='text']
            {
                width:auto;
            }

            .alertboxWrap{
                background-color:#000000;
                background-color:rgba(0,0,0,0.3);
                -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=50)";

                width:100%;
                height:100%;
                overflow:auto;
                position:fixed;
                top:0px;
                left:0px;
                display:none;
            }

            .dynamic_AlertTable{
                width: 99%;
                text-align: center;
                padding: 5px;
                height:auto;
                max-height: 311px;
                overflow:auto;
            }
            .alertbox
            {
                z-index:10;
                width:auto;
                padding:0px;
                background-color:#FFFFFF;
                border:grey 1px solid;
                position:absolute;
                top:35%;
                left:35%;
                opacity:1;
            }
            .dialog_alert{
                width: 940px;
                left: 15%;
                top:15%;
            }
            .dialog_submit_button, .dialog_cancel_button{
                background:#316CA8;
                width: 90px;
                height: 32px;
                font-family: Arial;
                font-weight: bold;
                font-size: 13px;
                color:white;
                text-align: center;
                border: 1px solid white;
                margin:0px 5px 0px 0px;
            }

            p
            {
                margin:0px;
                padding:0px;
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
            fieldset
            {
                margin:10px;
                /* border:none;*/
                padding:10px;
            }
            fieldset legend {
                background: #fff;
                color: #666666;
                font-size: 14px;
                margin: 0px 0px 4px 0px;
            }.InfoText{
                margin:5px;
            }
            .errorMessage{
                color:red;
                font-size:10px;
                margin: 1px;
                display:none;
            }
            .formLabel{
                width: 20%;
                margin: 10px 26px 10px 9px;
                display: inline-block;
                float:none;
                vertical-align: top;
            }
            .formInput{
                width: 19%;
                margin: 5px 26px 5px 9px;
                display: inline-block;
                vertical-align: top;
            }


            fieldset#billingWorkfield,
            fieldset#billingAddressfield,
            fieldset.billingAddressFieldset 
            {
                margin:0px;
                border: 0px;
                width:99%;
            }
            div.customerEditDiv {
                padding: 10px;
                box-sizing: border-box;
            }
            div#btnGroup{
                float:none;
            }
            div.formContent_new{
                padding: 10px;
                box-shadow: border-box;
            }
            div.basicDetails{
                border: 1px solid #99BBE8;
                border-radius: 4px;
                margin:14px;
                box-shadow:border-box;
            }



            .errorAlertText{
                width: 100%;
                font-weight: bold;
                padding: 15px;
                font-size: 12px;
            }
            .errorAlertWrap .dialog_alert{
                width:350px;
                left: 36%;
                top: 30%;
            }

            .customerDetails{
                margin: 30px auto;
                width: 95%;
                background-color:#F0F8FF;
                border:1px solid #ccc;

            }

            .customerDetails table
            {
                border-collapse: collapse;
            }
            .customerDetails table tr td
            {
                font-size: 11px;
            }
            .customer_billing_address tr th:first-child
            {
                border-left: 1px !important;
            }

            .plainButton{
                float: none;
                width: auto;
                display: inline-block;
                padding:10px 10px 0px 10px;
                line-height: initial;
                border-radius: 5px;
                text-align: center;
                margin: 10px;
                font-weight:normal;
                height: 25px;
            }
            #attachFileerrormessage{
                display: none;
                width: 110%;
                padding: 0px ;
                margin: 0px ;
            }
            #attachmentValue{
                width:99%;
                margin:0px;
            }
            .errorText, .errorTextWorkHrs,.errorNameText, .errorText1,.errorTextGst{ 
                color: red;
                margin: 1px 1px 1px 50px;
                display: none;
            }
            #errShortCode, #errShortCode1
            {
                color: red;
                margin: 1px 1px 1px 50px;   
                display: none;
            }
            #userManualIcon{
                width: 15px;
                margin: 10px 0px 0px 600px;
            }
            body {
                background: #EEF2F8 url(/ideal_mom/css/images/bg_page.png) repeat-x top;
                font-family: Arial;
                font-size: 12px;
                line-height: 7px;
                margin-left: auto;
                margin-right: auto;
                width: 1000px;
                color: #666666;
            }
            .page_heading {
                color: #666666;
                font-size: 18px;
                font-weight: bold;
                margin-left: 3px;
            }
            /*        p {
                        padding-left: 2px;
                        padding-right: 2px;
                        margin-bottom: 2px;
                    }*/
            #listName {
                font-weight: bold;
                float: right;
                margin: -30px -22px 0px 0px;
                text-decoration: none;
                color: #666666;
                cursor: pointer;
            }
            #listIcon {
                font-weight: bold;
                float: right;
                margin: -33px 65px 0px 0px;
                width: 12px;
            }
            /*        img {
                        border: none;
                    }*/
            .notice_page {
                background-color: #ECE6D2;
                background-repeat: no-repeat;
                border: 1px solid #DDD6B7;
                width: 940px;
                padding: 0px 0px 8px 8px;
                /*margin: 0px 0px 5px 18px;*/
            }
            form div {
                clear: both;
                padding: 5px;
                vertical-align: text-top;
            }
            #customerTable {
                width: 900px;
            }
            div.formContent_new {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                float: left;
                /*margin: 0 0 30px 18px;*/
                outline: 1px none;
                padding-bottom: 0;
                width: 928px;
            }
            .buttonAlignment {
                color: #FFFFFF;
                font-family: arial;
                font-size: 13px;
                font-weight: bold;
            }
            div#btnGroup {
                float: none;
            }
            div#btnGroup {
                text-align: center;
                width: 100%;
                margin: 10px 0px 5px 0px;
            }
            .textbox{
                border: 1px solid #99BBE8;
                border-radius: 4px;
            }
            /*            tr {
                            display: table-row;
                            vertical-align: inherit;
                            border-color: inherit;
                        }*/
            #datadisplay table {
                border: 1px solid #99BBE8;
                border-radius: 0px;
                background: #fff;
                clear: both;
                width: 100%;
                line-height: 20px;
                color: #000;
                none :repeat scroll 0 0 #DFE8F6;

            }
            #datadisplay th{
                font-weight: bold;
                color:#666666;
            } 
            #datadisplay table.customer_billing_address tbody tr {
                width:99%;
            }
            #datadisplay  table.customer_billing_address tbody tr th,
            #datadisplay   table.customer_billing_address tbody tr td{
                width:15%;
                text-align: left;
                padding-left:2px;
            }
            /*        #datadisplay  table.customer_billing_address tbody tr th:last-child,*/
            #datadisplay  table.customer_billing_address tbody tr td:last-child
            {
                width:4%;
                text-align: center;
            }


            #datadisplay  table#customer_work_location tbody tr th:first-child,
            #datadisplay  table#customer_work_location tbody tr td:first-child{
                width:1%;
            }
            #datadisplay table#customer_work_location tbody tr th:first-child+th,
            #datadisplay table#customer_work_location tbody tr td:first-child+td{
                width:35%;
                word-break: break-word;
            }

            #datadisplay  table.customer_billing_address tbody tr input
            {
                width:99%;
            }
            tbody {
                display: table-row-group;
                vertical-align: middle;
                border-color: inherit;
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
            table.display {
                margin: 0 auto;
                border-collapse: collapse;
                background: none !important;
            }
            table {
                display: table;
                border-spacing: 15px;
                border-color: grey;
            }

            table.customer_billing_address tbody tr td {
                width: 15%;
                text-align: left;
                padding-left: 7px;
            }
            .customerDetails table tr td {
                font-size: 11px;
            }
            td, th {
                display: table-cell;
            }
            .customerDetails table {
                border-collapse: collapse;
            }


            #datadisplay table tr td {
                vertical-align: middle;
            }
            #listIcon{
                font-weight: bold;
                float: right;
                margin: -32px 65px 0px 0px;
                width: 12px;
            }
            #listName{
                font-weight: bold;
                float: right;
                margin: -30px 7px 0px 0px;
                text-decoration: none;
                color: #666666;
                cursor: pointer;
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
            #momtable tbody tr td label {
                width: 190px;
                padding: 5px;
            }
            form label {
                float: none;
                display: inline-block;
            }
            div .formContenet_new {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                float: left;
                /*                margin: 0px 0px 30px 18px;*/
                outline: 1px none;
                padding-bottom: 0;
                width: 950px;
            }
            .momDetails{
                margin:0px auto;
                width: 95%;
                /*background-color: #F0F8FF;*/
                border: 1px solid #ccc;
            }
            .momDescriptionFieldset {
                margin: 12px 0px 0px 0px;
                padding: 0px;
                border: 0px;
            }
            .momAgendaFieldset{
                margin: 12px 0px 0px 0px;
                padding: 0px;
                border: 0px;
            }
            .momDiscussionFieldset{
                margin: 12px 0px 0px 0px;
                padding: 0px;
                border: 0px;
            }
            .momActionPointFieldset{
                margin:  0px 0px;
                padding: 0px;
                border: 0px;
            }
            fieldset{
                border-radius: 5px;
            }
            fieldset legend {
                background: #fff;
                color: #555555;
                font-size: 14px;
                margin: 0px 0px 4px 0px;
            }
            /*            .discussionFieldSet{
                            margin: 12px 0px 0px 0px;
                            padding: 0px;
                            border: 0px;
                        }*/
            table.actionPoint {
                width: 90%;
                border-collapse: collapse;
                border-spacing: 5px;
                border-top:0;
            }
            /*            .actionPoint tbody tr td:last-child {
                            width: 3%;
                        }*/
            /*            .actionPoint td input[type='text'] {
                            height: 20px;
                            width: auto;
                        }*/
            .actionPoint tr {
                padding: 0px;
                width: auto;
            }
            #momPresent label, #momAbsent label {
                margin-left: 5px;
            }
            #momPresent img, #momAbsent img {
                cursor: pointer;
                vertical-align: middle;
            }
            .textArea {
                width: 690px;
                height: 20px;
            }
            #momAgendatable img, #momDiscussiontable img {
                cursor: pointer;
                margin-top: 5px;
            }
            #momActionPointtable img{
                cursor: pointer;
                margin-top: 3px;
            }
            div#btnGroup {
                text-align: center;
                width: 100%;
                margin: 10px 0px 5px 0px;
            }
            .buttonAlignment {
                color: #FFFFFF;
                font-family: arial;
                font-size: 13px;
                font-weight: bold;
            }
            .qualityback {
                background: url(css/images/back.png) no-repeat scroll 8px 8px #316CA8;
                color: #FFFFFF;
                border: 1px solid #44928F;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
                width: 90px;
                cursor: pointer;
            }
            .qualitysave {
                background: url(css/images/save-button-icon.png) no-repeat scroll 8px 8px #316CA8;
                color: #FFFFFF;
                border: 1px solid #44928F;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
                width: 90px;
                cursor: pointer;
            }
            .qualitysubmit {
                background: url(css/images/icon_btn_submit.png) no-repeat scroll 8px 8px #316CA8;
                color: #FFFFFF;
                border: 1px solid #44928F;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
                width: 90px;
                cursor: pointer;
            }
            #momtable tr td {
                width:25%;
            }
            #momPresent tr td,#momAbsent tr td{
                width:25.5%;
            }
            #momPresent, #momAbsent{
                border:none;
            }
            form label{
                width:90%;
            }
            /*            .goToList {
                            color: #4C83B3;
                            float: right;
                            font-size: 12px;
                            font-weight: bold;
                            margin: 20px 40px 18px;
                        }
                        .exportexcel1 {
                            background: url(ideal_mom/css/images/export-icon.png) no-repeat scroll left center transparent;
                            color: #4D85B8;
                            float: right;
                            height: 20px;
                            padding: 2px 0 0 21px;
                        }*/
        </style>
    </head>
    <body onload>

        <div id="loadingDiv" style="position: absolute; width: 100%; background-color: rgb(119, 119, 119); display: none; top: 0pt; left: 0pt; height: 1243px;">
            <div style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center">
                <img src="/ideal_mom/css/images/loading.gif" id="loadingImage" tabindex="-1"><br>Loading Page...Please wait
            </div>
        </div>
        <!--         <div class="tabs">
                   
                <ul class="tabactions">
                    <li><a href="#myMom"id="ListOfMom" class="ListOfMom"><b>My Mom</b></a></li>
                    <li><a href="#myAction"id="ListOfActions" class="ListOfActions"><b>My Action</b></a></li>
                </ul>-->
        <div id="container">
            <div class="center_content" style="padding-top: 20px">
                <div class="container_inner" style="margin: 15px 0px;width:300px;height: 22px;">
                    <p class="page_heading">
                        <span class="heading">View Actions</span> </p>
                    <!--                         <a id="exportActionList" name="exportActionList"class="exportexcel1" onclick="getActionExcelReport();" target="_self" style="text-decoration:none;cursor: pointer;margin-left: 871px;" title="Export">Export
                                             <img src="/ideal_mom/css/images/excel-icon.png" style="margin-left: 920px; position: absolute; top: 77px;"/></a>-->
                </div>
                <div class="notice_page">
                    <img src="/ideal_mom/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon">
                    <img src="/ideal_mom/css/images/tick.png" title="Information" alt="Information" id="tickIcon">
                    <span class="InfoText">Fields marked in <span style="color: red;" for="fine">*</span> are mandatory.</span>
                </div>
                <form name="formMomDetails" id="formMomDetails" method="post"enctype="multipart/form-data" autocomplete="off">
                    <div class="formContent_new" id="datadisplay" style="height:auto">
                        <div class="content_page">
                            <div class="momDetails">
                                <fieldset class="momDescriptionFieldset">
                                    <h3 style="padding-left:15px;">Meeting Details</h3>
                                    <table id="momtable" border="0" align="center">
                                        <tr>
                                            <td>
                                                <span class="" >Title: </span>
                                            </td>
                                            <td>
                                                <span style="margin-left:-98px;">${meet.mom_title}</span>
                                            </td>
                                            <td>
                                                <span class="" >Planned Start Time:</span>
                                            </td>
                                            <td>
                                                <span style="margin-left:-98px;">${meet.planned_start_time}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <span class="" >Date:</span>
                                            </td>
                                            <td>
                                                <span style="margin-left:-98px;">${meet.mom_date}</span>
                                            </td>

                                            <td>
                                                <span class="" >Planned End Time:</span>
                                            </td>
                                            <td>
                                                <span style="margin-left:-98px;">${meet.planned_end_time}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <span class="" >Venue:</span>
                                            </td>
                                            <td>
                                                <span style="margin-left:-98px;">${meet.mom_venue}</span>
                                            </td>

                                            <td>
                                                <span class="" >Actual Start Time:</span>
                                            </td>
                                            <td>
                                                <span style="margin-left:-98px;">${meet.actual_start_time}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label class="headings">Minuted By:
                                                    
                                                </label>
                                            </td>
                                            <td>
                                                <!--<input type="text" value="${list.minuted_by}"name="minuted_by" id="minuted_by">-->
                                                <span style="margin-left:-98px;"> ${minuted_name}</span>
                                            </td>

                                            <td>
                                                <span class="">Actual End Time:</span>
                                            </td>
                                            <td>
                                                <span style="margin-left:-98px;"> ${meet.actual_end_time}</span>
                                            </td>
                                        </tr>
                                    </table>
                                </fieldset>
                                <table>
                                    <tr>
                                        <td>
                                            <span class="" >Members Present:</span>
                                        </td>
                                        <td>
                                            <c:forEach items="${present}" var = "present">
                                                <span style="margin-left:-98px;">${present.employee_id}</span><br>
                                            </c:forEach> 
                                        </td></tr>
                                </table>
                                <table>
                                    <tr>
                                        <td>
                                            <span class="" >Members Absent:</span>
                                        </td>
                                        <td>
                                            <c:forEach items="${absent}" var = "absent">
                                                <span style="margin-right: 584px;">${absent.employee_id}</span>
                                            </c:forEach> 
                                        </td>
                                    </tr>
                                </table>
                                <fieldset id="" class= "momAgendaFieldset">
                                    <h3 style="padding-left:15px;">Agenda</h3>
                                    <table id="momAgendatable" > 
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <label>
                                                        <c:forEach items="${agenda}" var = "agenda" >
                                                            <ul style="list-style-type:disc;" >
                                                                <li>${agenda.agenda_point}</li>
                                                            </ul> 
                                                        </c:forEach>
                                                    </label>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </fieldset>

                                <fieldset class="momDiscussionFieldset">
                                    <h3 style="padding-left:15px;">Discussions</h3>
                                    <table>
                                        <tbody>
                                            <tr>
                                                <td><label>
                                                        <c:forEach items="${discuss}" var = "discuss" >
                                                            <ul style="list-style-type:disc">
                                                                <li>${discuss.discussion_point}</li>
                                                            </ul> 
                                                        </c:forEach> 
                                                    </label>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </fieldset>
                                <fieldset class="momActionPointFieldset">
                                    <h3 style="padding-left:15px;">Action Points</h3>
                                    <table   id="momActionPointtable"class="actionPoint">
                                        <thead>
                                            <tr style="background-color: #DFE8F6;">
                                                <th>S.NO</th>
                                                <th>Action points</th>   
                                                <th>Owner</th>   
                                                <th>Status</th>
                                                <th>Planned Closure Date</th>   
                                                <th>Actual / Forecast Closure Date</th>   
                                                <th>Tracking comments</th> 
                                                <th>Edited by</th>
                                            </tr>
                                        </thead>
                                        <tbody style="font-family: arial;">
                                        <input type="hidden" name="mom_edit" id ="mom_edit" value="${meet.id}"/>  
                                        <c:set var="Count" value="${0}" />
                                        <c:forEach items="${actionData}" var = "actionObj" >
                                            <input type="hidden" id="previous_planned_close_date" name="mom_previous_planned_close_date" value="${actionObj.actual_close_date}">
                                            <c:set var="Count" value="${Count+1}" /> 
                                            <c:set var="evenCount" value="${Count+1}" />
                                            <c:if test="${evenCount % 2 == 1}">
                                                <tr style="background-color: #eff4fa;">
                                                </c:if>
                                                <c:if test="${evenCount % 2 == 0}">
                                                <tr style="background-color: #ffffff;">
                                                </c:if>
                                            <input type="hidden" name="mom_action_point_id" id ="mom_action_point_id" value="${actionObj.action_point_id}"> 
                                            <td align="center">
                                                <span class="">${Count}</span>
                                            </td>
                                            <td align="center">
                                                <span class="">${actionObj.action_point}</span>
                                            </td>
                                            <td align="center">
                                                <span class="">${actionObj.employee_name}</span>
                                            </td>
                                            <td align="center">
                                                <span class="">${actionObj.ap_status_name}</span>
                                            </td>
                                            <td align="center">
                                                <span class="">${actionObj.planned_close_date}</span>
                                            </td>
                                            <td align="center">
                                                <c:forEach items="${history}" var = "his" >
                                                    <c:if test="${his.action_id == actionObj.action_point_id}">
                                                    <strike>  ${his.previous_planned_close_date}</strike><br>
                                                    </c:if>
                                                </c:forEach>
                                            
                                            </td>
                                            <td align="center">
                                                <c:forEach items="${history}" var = "his" >
                                                     <c:if test="${his.action_id == actionObj.action_point_id}">
                                                     <strike>   ${his.tracking_comments}</strike><br>
                                                     </c:if>
                                                 </c:forEach>
                                               
                                            </td>
                                            <td>
                                               ${minuted_name}
                                            </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </fieldset>                        
                            </div>
                        </div>                                   

                        <div class="buttonAlignment">
                            <div class="buttonAlignment" id="btnGroup">
                                <input type="hidden" name="parentId" id="parentId" value="546">
                                <input type="hidden" name="employeeId" id="employeeId" value="">
                               <input type="button" name="btnCancel" value="Back" class="qualityback" onclick="javascript: location.href = 'javascript:history.go(-1)'"/>

                            </div>
                        </div>  
                    </div> 
                </form>                                        
            </div>                        
            <!--</div>-->    
        </div>    <script type="text/javascript">
            //                 $( function() {
            //                      $( ".tabs" ).tabs();
            //                    });
            //              
            //                var lastTab = localStorage.getItem('lastTab');
            //                    if (!lastTab) {
            //                        lastTab = 0;
            //                    }
            //                $(".tabs").tabs({
            //                    activate: function(event, ui) {
            //                        //if another tab is activate, save it's index to the localStorage
            //                        localStorage.setItem('lastTab', ui.newTab.index());
            //                    },
            //                    //set the corresponding tab to active
            //                    active: lastTab
            //                });
        </script>
    </body>
</html>
