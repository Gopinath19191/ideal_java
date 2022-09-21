<%-- 
    Document   : myMomActionItems
    Created on : Mar 6, 2018, 3:12:56 PM
    Author     : 16656
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My MoM Action Items</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.ui.core.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/editMomValidation.js"></script>

        <style type="text/css">
            /*            #loadingDiv img{ 
                            border: none;
                        }
                        #loadingDiv{
                            opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1
                        }*/
            #datadisplay table tr td {
                padding: 5px 3px !important;
                /*vertical-align: top;*/
            }
            #momtable tbody tr td label {
                /*width: 190px;*/
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
                /*                margin:0px auto;
                                width: 95%;*/
                /*background-color: #F0F8FF;*/
                border: 1px solid #ccc;
            }
            #momActionPointtable img{
                cursor: pointer;
                margin-top: 3px;
            }
            .momActionPointFieldset{
                margin: 12px 0px 0px 0px;
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
                margin: 0px 0px 11px 0px;
            }
            .actionPoint tr {
                padding: 0px;
                width: auto;
            }
            #momActionPointtable th {
                border: none;
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
            .history {
                background: #4d85b8;;
                color: #FFFFFF;
                border: 1px solid #44928F;
                font-weight: bold;
                height: 25px;
                margin: 0 0 0 15px;
                width: 60px;
                cursor: pointer;
            }
            form label{
                width:90%;
            }
            .historypoint{
                color:#4d85b8;
            }
            .action tr {
                padding: 0px;
                width: auto;
                cursor:pointer;
            }
            .action:hover {
                /*                background-color: #bacfee;*/
                width: 93%;
            }
            .common_class {
                background-color:#a9bbc5 ;
                border: none;
                color: #fff;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-weight: bold;
                padding: 7px 15px 7px 15px;
                margin-top: 10px;
            }
            .common_class:hover {
                background-color: white;
                color: black;
            }
            .btn{
                background-color: #4d85b8;
            }
        </style>
    </head>
    <body>
        <!--        <div class="w3-bar w3-black">
                    <a href="editMoM.htm?mom_edit=${item.id}"> <button class="common_class " id="tablink_mom" style="margin-left: 31px;" >My Mom</button></a>
                    <button class="common_class btn" id="tablink_acttion" >My Actions</button>
                </div>-->
        <div id="loadingDiv" style="position: absolute; width: 100%; height: 100%; background-color: rgb(119, 119, 119); display: none; top: 0pt; left: 0pt;">
            <div style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center">
                <img src="/ideal_mom/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
            </div>
        </div>
        <div id="container">
            <div class="center_content" style="padding-top: 20px">
                <div class="container_inner" style="margin: 15px 0px;width: 300px;">
                    <p class="page_heading">
                        <span class="heading">My MoM Action Items</span> 
                    </p>
                </div>
            </div>
            <form action="" name="formMomActionItems" id="formMomActionItems" method="post" autocomplete="off">
<!--                <input type="hidden" name="mom_edit" id="mom_edit" value="${list.id}" />-->
                <div class="formContenet_new" id="datadisplay" style="height: auto">
                    <div class="content_page">
                        <div class="momDetails">
                            <fieldset class="momActionPointFieldset">
                                <legend>Action Points</legend>
                                <span id="closeDate" style=" margin: -22px 0px 10px 351px; color: red;"></span>
                                <table id="momActionPointtable" class="actionPoint">
                                    <thead style="pointer-events: none">
                                        <tr>
                                            <!--<th>S.NO<span style="color: red;" for="fine">*</span></th>-->
                                            <th>Action points</th>   
                                            <th>Owner ID/Name</th>   
                                            <th>Planned Closure Date</th>   
                                            <th>Actual Closure Date</th>   
                                            <th>Tracking comments</th> 
                                            <th>Action Status</th>
                                            <th>History</th>
                                            <!--<th>Edited By</th>-->
                                        </tr>
                                    </thead>
                                    <tbody style="font-family: arial;" class="actionHis">
                                        <!--<input type="hidden" name="mom_edit" id="mom_edit" value="${list.id}" />-->
                                        <!--<input type="hidden" name="mom_edit" id ="mom_edit" value="${meet.id}">-->  
                                        <c:set var="Count" value="${0}" />
                                        <c:set var="flag" value="${0}" />
                                        <c:forEach items="${actionItems}" var = "item" >
                                            <c:choose>
                                                <c:when test="${item.action_point_status!='c'}">
                                            <input type="hidden" id="previous_planned_close_date" name="mom_previous_planned_close_date" value="${item.actual_close_date}">
                                            <input type="hidden" id="mom_remarks" name="mom_remarks" value="${item.tracking_comments}">                                            
                                            <c:set var="Count" value="${Count+1}" /> 
                                            <c:set var="flag" value="${flag+1}" />
                                            <input type="hidden" name="mom_action_point_id" id="action_point_id_${actionStatus.count}" value="${item.action_point_id}" />
                                            <input type="hidden" name="mom_edit" id="mom_edit" value="${item.id}">
                                            <tr>
                                                <td align="center">
                                                    <span>${item.action_point}</span>
                                                </td>
                                                <td align="center">
                                                    <span>${item.employee_name}</span>
                                                </td>
                                                <td align="center">
                                                    <span id="planned_close_date">${item.planned_close_date}</span>
                                                </td>
                                                <td align="center">
                                                    <div id="historypointdate_${flag}" style="display:none;color:#4d85b8;">  
                                                        <c:if test="${fn:length(history)>0}">
                                                            <c:forEach items="${history}" var = "his" >
                                                                <c:if test="${his.action_id == item.action_point_id}">
                                                                    ${his.previous_planned_close_date}<br>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>   
                                                    <input type="text" name="mom_actual_close_date" value="${item.actual_close_date}" class="datepicker closeDate" id="datepicker_${flag}" readonly="readonly">
                                                    <span id="actualCloseDate_${flag}"></span>
                                                </td>
                                                <td align="center">
                                                    <div id="historypoint_${flag}" style="display:none;color:#4d85b8;">  
                                                        <c:if test="${fn:length(history)>0}">
                                                            <c:forEach items="${history}" var = "his">
                                                                <c:if test="${his.action_id == item.action_point_id}">
                                                                    ${his.tracking_comments}<br>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                    <input type="text" name="mom_tracking_comments" value="${item.tracking_comments}"> 
                                                </td>
                                                <td align="center">
                                                    <select name = "mom_action_point_status">
                                                        <c:forEach items="${statusData}" var = "statusData">
                                                            <option value="${statusData.configuration_key}" ${item.action_point_status==statusData.configuration_key ? 'selected':''}>${statusData.action_point_status}</option>
                                                        </c:forEach>
                                                    </select> 
                                                </td>
                                                <td>
                                                    <input type="button" name="history" value="History" class="history" id="history_${flag}" onclick="historyBtn(${flag});" />
                                                </td>
                                                <!--                                            <td>
                                                ${minuted_name}
                                            </td>-->
                                            </tr>
                                            <input type="hidden" id="planned_date_${flag}" value="${item.planned_close_date}" >
                                        </c:when>
                                        <c:otherwise>
                                            <input type="hidden" id="previous_planned_close_date" name="mom_previous_planned_close_date" value="${item.actual_close_date}">
                                            <input type="hidden" id="mom_remarks" name="mom_remarks" value="${item.tracking_comments}">                                            
                                            <c:set var="Count" value="${Count+1}" /> 
                                            <c:set var="flag" value="${flag+1}" />
                                            <!--<input type="hidden" id="mom_action_point_status" name="mom_action_point_status" value="${action.action_point_status}">-->
                                            <input type="hidden" id="mom_date" name="mom_date" value="${action.planned_close_date}">
                                            <tr align="center" id="TR4_${actionStatus.count}">
                                            <input type="hidden" name="mom_action_point_id" id="action_point_id_${actionStatus.count}" value="${action.action_point_id}" />
                                            <!--<input type="hidden" name="mom_action_point_employee_id" id="action_point_employee_id_${actionStatus.count}" value="${action.action_point_employee_id}" />-->
                                            <input type="hidden" name="mom_action_point_deleted" id="deletedTR4_${actionStatus.count}" value="0" />
                                            <td><span>${item.action_point}</span></td>
                                            <td><span>${item.employee_name}</span></td>
                                            <td><span id="planned_close_date">${item.planned_close_date}</span></td>
                                            <td>
                                                <div id="historypointdate_${flag}" style="display:none;">  
                                                    <c:if test="${fn:length(history)>0}">
                                                        <c:forEach items="${history}" var = "his" >
                                                            <c:if test="${his.action_id == item.action_point_id}">
                                                                ${his.previous_planned_close_date}<br>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                                <input type="text" name="mom_actual_close_date" value="${item.actual_close_date}" readonly="readonly">
                                                <span id="actualCloseDate_${flag}"></span>
                                            </td>
                                            <td>
                                                <div id="historypoint_${flag}" style="display:none;">  
                                                    <c:if test="${fn:length(history)>0}">
                                                        <c:forEach items="${history}" var = "his">
                                                            <c:if test="${his.action_id == item.action_point_id}">
                                                                ${his.tracking_comments}<br>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                                <input type="text" name="mom_tracking_comments" value="${item.tracking_comments}" readonly="readonly">
                                            </td>
                                            <c:forEach items="${statusData}" var = "statusData">
                                                <c:if test="${item.action_point_status==statusData.configuration_key}">
                                                <td>
                                                    <input type="text" name="mom_action_point_status_temp" value="${statusData.action_point_status}" readonly="readonly">
                                                    <input type="hidden" name="mom_action_point_status" value="${statusData.configuration_key}" readonly="readonly">
                                                </td>
                                                </c:if>
                                            </c:forEach>
                                            <td>
                                                <input type="button" name="history" value="History" class="history" id="history_${flag}" onclick="historyBtn(${flag});" />
                                            </td>
                                            </tr>
                                        </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <input type="hidden" id="count_row" value="${Count}">
                                    </tbody>
                                </table>
                            </fieldset>
                        </div>
                    </div>
                </div>
                <div class="buttonAlignment">
                    <div class="buttonAlignment" id="btnGroup">
                        <input type="hidden" name="parentId" id="parentId" value="546">
                        <input type="hidden" name="employeeId" id="employeeId" value="">
                        <input type="button" name="btnCancel" id="btnCancel" value="Back" style="cursor:pointer;" class="qualityback" onclick="javascript: location.href = 'editMoM.htm?mom_edit=${list.id}'">
                        <input type="button" name="actionSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" onclick="updateMyMomActions();">
                    </div>
                </div>
            </form>
        </div>
        <script type="text/javascript">
            //            function updateMomActionItems(){
            //                $('#formMomActionItems').attr("action", "updateMyMomActionItems.htm");
            //                document.formMomActionItems.submit();
            //            }
            $(document).ready(function(){                     
                var row=$('#count_row').val();
                for(var i=1;i<=row;i++){
                    $('#datepicker_'+i).datepicker({
                        dateFormat: 'dd-M-yy',
                        changeMonth: true,
                        changeYear: true,
                        minDate:$('#planned_date_'+i).val()
                    })       
                }
            }); 
        
            function updateMyMomActions(){
                if(updateMomActions()){
                    $('#formMomActionItems').attr("action", "updateMyMomActionItems.htm");
                    document.formMomActionItems.submit();
                    startLoading();
                }
            }
            
            /*function updateMyMomActions(pName,pError,pDelete){
                var members= $(".closeDate").map(function() {
                    return $(this).val();
                }).get();
                console.log(members);
                var error=0;
                var sorted_arr = members.slice().sort(); 
                var j =0;
                for (var i = 0; i < sorted_arr.length - 1; i++) {
                    j=i+1;
                    if (sorted_arr[i]==null || sorted_arr[i]=="") {
                        document.getElementById("actualCloseDate_"+j).innerHTML="Please Select Actual Close Date";
                        document.getElementById("actualCloseDate_"+j).style.color = 'red';
                        document.getElementById("actualCloseDate_"+j).style.display = 'block';
                        document.getElementById("datepicker_"+j).focus();
                       error++;
                    }
                }
               if(error){
                   return false;
               }else{
                   document.getElementById("actualCloseDate").style.display = 'none';
                    $('#formMomActionItems').attr("action", "updateMyMomActionItems.htm");
                    document.formMomActionItems.submit();
                    startLoading();
               }
            }*/

            function historyBtn(rowid){
                console.log(rowid);
                //if($('#historypoint_'+rowid).val().length>0|| $('#historypointdate_'+rowid).val().length>0){
                $('#historypoint_'+rowid).slideToggle();
                $('#historypointdate_'+rowid).slideToggle();
                //            }
            }
        
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
        
            function startLoading() {
                if (ns4) {
                    loadingDivObj.visibility = "visible";
                } else if (ns6 || ie4)
                    loadingDivObj.display = "block";
            }
            function stopLoading() {
                if (ns4) {
                    loadingDivObj.visibility = "hidden";
                } else if (ns6 || ie4)
                    loadingDivObj.display = "none";
            }
        </script>
    </body>
</html>
