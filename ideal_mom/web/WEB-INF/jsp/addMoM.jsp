<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MoM Add Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.ui.core.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.autocomplete.min.js"></script>
        <script type="text/javascript" src = "${pageContext.request.contextPath}/js/addMomValidation.js"></script>
         <script type="text/javascript" src = "${pageContext.request.contextPath}/js/timeValidation.js"></script>
        <script type="text/javascript" src = "${pageContext.request.contextPath}/javascript/steptwo.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autoSuggest.mod.js"></script>
<!--        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tp.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/ext-all.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jscript.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>-->
        <link rel="stylesheet" href="css/autoSuggest.css" type="text/css">
        <style type="text/css">
            /*            #datadisplay table tr td {
                            vertical-align: middle;
                        }*/
            #listIcon{
                font-weight: bold;
                /*float: right;*/
                margin: 0px 8px -2px 0px;
                width: 11px;
            }
            #listName{
                font-weight: bold;
                float: right;
                margin: -30px 39px 0px 0px;
                text-decoration: none;
                color: #4d85b8;
                cursor: pointer;
                font-size: 14px;
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
                padding: 0px 0px 7px 0px;
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
                /*                padding-bottom: 0;*/
                width: 950px;
            }
            .momDetails{
                margin:0px auto;
                /*                width: 95%;*/
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
                margin: 12px 0px 0px 0px;
                padding: 0px;
                border: 0px;
            }
            fieldset{
                border-radius: 5px;
            }
            fieldset legend {
                background: none;
                color: #555555;
                font-size: 14px;
                margin: 0px 0px 4px 0px;
            }
            /*            .discussionFieldSet{
                            margin: 12px 0px 0px 0px;
                            padding: 0px;
                            border: 0px;
                        }*/
            /*            table.actionPoint {
                            width: 90%;
                        }*/
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
            #momActionPointtable th {
                border: none;
            }
/*            #momPresent label, #momAbsent label {
                margin-left: 5px;
            }*/
            #momPresent img, #momAbsent img {
                cursor: pointer;
                vertical-align: middle;
            }
            .agendaText {
                width: 438px;
                height: 130px;
            }
            #textAreaDiscussion {
                width: 458px;
                height: 130px;
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
             .qualityDraft {
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
                width:25%;
            }
            #momPresent, #momAbsent{
                border:none;
            }
            form label{
                width:90%;
            }
            .hour_format{
                width:100px !important;
            }
/*            #mom_title{
                margin-left: -93px;
            }*/
/*            #test{
                margin-left: -181px;
            }*/
        </style>
        <!--        <script>
                    function loadForm(page) {
                        $('#page').val(page);
                        var url = $('#url').val();
                        $('#formMomDetails').attr("action", url);
                        document.formMomDetails.submit();
                    }           
                </script>-->
    </head>
    <body>
        <div id="loadingDiv" style="position: absolute; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); display: none; top: 0pt; left: 0pt;">
            <div style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center;color: #000;">
                <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
            </div>
        </div>
        <div id="container">
            <div class="center_content" style="padding-top: 20px">
                <div class="container_inner" style="margin: 15px 0px;width: 300px;">
                    <p class="page_heading">
                        <span class="heading">Add Minutes of Meeting</span> 
                    </p>
                </div>
                <div>
                    
                    <p class="division_list">
                        <a id="listName" onclick="javascript: location.href='getAllDetails.htm?page=1'"><img src="${pageContext.request.contextPath}/css/images/icon_list.png" title="Information" alt="Information" id="listIcon">MoM List</a>
                    </p>
                </div>
                <div class="notice_page" style="width: 940px">
                    <img src="${pageContext.request.contextPath}/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon">
                    <img src="${pageContext.request.contextPath}/css/images/tick.png" title="Information" alt="Information" id="tickIcon">
                    <span class="InfoText">Fields marked in
                        <span style="color: red;" for="fine">*</span>
                        are mandatory                    
                    </span>
                </div>
            </div>

            <form name="formMomDetails" id="formMomDetails" action="" method="post" autocomplete="off">
                <input type="hidden" id="status" name="mom_status" />
                <input type="hidden" id="emp_id" name="employee_no" value="${filterData.employee_no}" />
                <!--<input type="hidden" id="moduleId" name="moduleId" value="${filterData.moduleId}"/>-->
                <div class="formContenet_new" id="datadisplay" style="height: auto">
                    <div class="content_page">
                        <div class="momDetails">
                            <fieldset class="momDescriptionFieldset">
                                <legend>MoM Description</legend>
                                <table id="momtable" border="0">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <label>
                                                    Title
                                                    <font color="red">*</font>
                                                </label>
                                            </td>
                                            <td>
                                                <textarea name="mom_title" id="mom_title" style="width: 302px; margin-left: -103px;"></textarea>
                                                <span id="title" ></span>
                                                <span id="name_error_message" style="color: red; margin-left: 40px;"></span>
                                            </td>
                                            <td>
                                                <label style="margin-left: 40px;">Planned Time
                                                    <font color="red">*</font>
                                                </label>
                                            </td>
                                            <td>
<!--                                                <input type="text" style="margin-left:-89px;width:85px;" class="accrueTime" name="planned_start_time" id="planned_start_time" placeholder="0.00">-->
                                               <input list="browsers1" id="planned_start_time"  class="accrueTime" name="planned_start_time" style="margin-left:-91px;width:82px;"/>
                                                <span id="planned"></span>
                                                <!--<select name = "planned_start_time" id="time" class="time" style="margin-left:-90px;width:85px;">-->
                                                   <datalist  class="browsers1" id="browsers1">
                                                    <option value=""></option>
                                                    <c:forEach items="${timing}" var = "plannedstarttime">
                                                        <option value="${plannedstarttime}">${plannedstarttime}</option>
                                                    </c:forEach>
                                                <!--</select>--></datalist>
                                            </td>
                                            <td>
                                                <!--<input type="text" class="accrueTime"style="margin-left:-168px;width:85px;" name="planned_end_time" id="planned_end_time" placeholder="0.00">-->
                                              <!--<select name = "planned_end_time" id="time" class="time" style="margin-left:-90px;width:85px;">-->
                                             <input list="browsers2" id="planned_end_time"  class="accrueTime" name="planned_end_time" style="margin-left:-178px;width:82px;"/>
                                             <datalist id="browsers2" class="browsers2"  >
                                                <option value=""></option>
                                                    <c:forEach items="${timing}" var = "plannedendtime">
                                                        <option value="${plannedendtime}">${plannedendtime}</option>
                                                    </c:forEach>
                                              </datalist>
                                                <span id="planned_end"></span>
                                                <span id="plannedEndTimeTemp" style="color:red;position: absolute;margin-left: -251px;margin-top: 27px;"></span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>Date
                                                    <font color="red">*</font>
                                                </label>
                                            </td>
                                            <td>
                                                <input type="text" name="mom_date" id="mom_date" class="datepicker" readonly="readonly" style="margin-left: -103px;">
                                                <span id="date" ></span>
                                                
                                            </td>
                                       <td>
                                                <label style="margin-left: 40px;">Actual Time
                                                    <font color="red">*</font>
                                                </label>
                                            </td>
                                           <td>
                                                <!--<input type="text" class="accrueTime" style="margin-left:-89px;width:85px;" name="actual_start_time"  id="actual_start_time" placeholder="0.00">-->
                                             <!--<select name = "actual_start_time" id="time" class="time" style="margin-left:-90px;width:85px;">-->
                                                <input list="browsers3" id="actual_start_time"  class="accrueTime" name="actual_start_time" style="margin-left:-91px;width:82px;"/> 
                                             <datalist  class="browsers3" id="browsers3">
                                                 <option value=""></option>
                                                    <c:forEach items="${timing}" var = "actualstarttime">
                                                        <option value="${actualstarttime}">${actualstarttime}</option>
                                                    </c:forEach>
                                                </datalist>   
                                                <span id="actual_start" ></span>
                                           </td>
                                           <td>
                                                <!--<input type="text" style="margin-left:-168px;width:85px;" class="accrueTime" name="actual_end_time" id="actual_end_time" placeholder="0.00">-->
                                              <!--<select name = "actual_end_time" id="time" class="time" style="margin-left:-90px;width:85px;">-->
                                                  <input list="browsers4" id="actual_end_time"  class="accrueTime" name="actual_end_time" style="margin-left:-178px;width:82px;"/>   
                                                  <div>   <span id="actualEndTimeTemp" style="color:red;position: absolute;margin-left: -343px;margin-top: -4px;"></span>  </div>
                                                  <datalist class="browsers4" id="browsers4">
                                                    <option value=""></option>
                                                    <c:forEach items="${timing}" var = "actualendtime">
                                                        <option value="${actualendtime}">${actualendtime}</option>
                                                    </c:forEach>
                                             </datalist>
                                                <span id="actual_end" ></span>
                                              
                                           </td>
                                            
                                        </tr>
                                        <tr>
                                            <td>
                                                <label>Venue
                                                    <!--<font color="red">*</font>-->
                                                </label>
                                            </td>
                                            <td>
                                                <input type="text" name="mom_venue" id="mom_venue" style="margin-left: -103px; width:304px">
                                                <span id="venue" ></span>
                                            </td>

                                           
<!--                                        </tr>
                                        <tr>-->
                                            <td>
                                                <label style="margin-left: 41px;">Minuted By
                                                    
                                                </label>
                                            </td>
                                            <td>
                                                <span style="margin-left: -90px;">${minuted_name} </span>
                                            </td>
<!--                                            <td>
                                                <label style="margin-left: 40px;">Actual End Time
                                                    <font color="red">*</font>
                                                </label>
                                            </td>-->
                                           
                                        </tr>
                                    </tbody>
                                </table>
                                <table id="momPresent" class="momPresent" border="0">
                                    <tr>
                                        <td>
                                            <label for="test"style="margin-top: 16px;">Members Present  <font color="red">*</font></label>
                                        </td>
                                        <td> 
                                            <div id="tested" style="margin-left: -2px;">  
                                                <input type="text" id="test" class="members_present present" name="test"  value=""  autocomplete="off" style="margin-top: -21px;margin-bottom: 0px;"/>
                                                <a href="#" id="ListOfMom" class="ListOfMom" style="margin-left: 104px;padding-top: 10px;position: absolute;">
                                                   <img src="${pageContext.request.contextPath}/css/images/addcontact.png" alt="add" title="Add contacts" style="cursor: pointer;margin-top: -7px;height:27px;"></a>
                                                 <span id="memberpresent" class="memberpresent" style="margin-top: -5px;position: absolute;"></span>    
                                            </div> 
                                        </td>
                                        
<!--                                    <form id="present" style="display: none;">-->
                                        <td>
                                        <div id="present" style="display: none;">
                                         <textarea id="members_present" name="members_present" class="members_Present present" style="margin: 0px 0px 0px -333px;width: 273px;height: 48px;"></textarea>
                                         <a href="#" id="ListOfactions" style="margin-left: -48px;padding-top: 18px;position: absolute;">
                                           <img src="${pageContext.request.contextPath}/css/images/back_1.png" alt="add" title="Add contacts" style="cursor: pointer;margin-top: -7px;"></a>
                                            <span id="memberPresent" class="memberPresent" ></span>
                                        </div>
                                        </td>
                                        
<!--                                    </tr>-->
<!--                                </table>
                                <table id="momAbsent" class="momAbsent" border="0">
                                    <tr>                              -->
                                        <td>
                                            <label for="test2" style="margin-top: 15px;margin-left: -187px;">Members Absent</label>
                                        </td>
                                        <td>
                                            <div id="testing" style="margin-left: -205px;">
                                                <input type="text" id="test2"class="members_absent"  name="test2" value="" autocomplete="off" style="margin-top: -21px;margin-bottom: 0px;" />
                                                    <a href="#" id="ListOfMom2" style="margin-left: 104px;padding-top: 10px;position: absolute;">
                                                    <img src="${pageContext.request.contextPath}/css/images/addcontact.png" alt="add" title="Add contacts" style="cursor: pointer;margin-top: -7px;height:27px;"></a>
                                                <span id="memberabsent" class="memberabsent"></span>
                                            
                                            </div>
                                        </td>     
                                       
                                        <td>
                                            <div id="absent" style="display: none;">
                                            <textarea id="members_absent" name="members_absent" class="members_Absent" style="margin: 0px 0px 0px -304px;width: 273px;height: 48px;"></textarea>
                                            <a href="#" id="ListOfactions2" style="margin-left: -22px;padding-top: 18px;position: absolute;">
                                              <img src="${pageContext.request.contextPath}/css/images/back_1.png" alt="add" title="Add contacts" style="cursor: pointer;margin-top: -7px;"></a>
                                            <span id="memberAbsent" class="memberAbsent"></span>
                                            <span id="absent_span"   style="color: red;"  ></span></div>
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                            <fieldset class="momAgendaFieldset">
                                <label style="margin-right:0px;font-weight: bold; font-size: 14px;">Agenda
                                <font color="red">*</font></label>
                                <span style="margin-left: -380px;font-weight: bold; font-size: 14px;"> Discussion<font color="red">*</font></span>
                              
                                <table id="momAgendatable" border="0">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <textarea type="text" class="agendaText"name="mom_agenda_points" id="textArea" rows="8" cols="30" ></textarea>
                                                    
                                            <span id="agendaTextArea"></span>
                                            </td>
                                    
                                            <td>
                                                <textarea type="text" class="discussionText" name="mom_discussion_points" id="textAreaDiscussion"></textarea>
                                            </td>
                                    <span id="discussTextArea"></span>
                                    </tr>
                                    </tbody>
                                </table>
                            </fieldset>
                            <fieldset class="momActionPointFieldset">
                                <legend>Action Point </legend><span id="actionPoint"></span>
                                <!--<div style="color: red; font-size: 11px;">(All fields are mandatory to fill)</div>-->
                                <c:choose>
                                <c:when test="${!empty(actionPointList)}">
                                    <input type="hidden" name="actionCount" id="actionCount" value="${fn:length(actionPointList)}" />
                                </c:when>
                                <c:otherwise>
                                <input type="hidden" name="actionCount" id="actionCount" value="1" />
                                </c:otherwise>
                                </c:choose>
                                <table id="momActionPointtable" class="actionPoint">
                                    <tbody>
                                        <tr>
                                            <th style="pointer-events: none">Actions<font color="red">*</font></th>
                                            <th style="pointer-events: none">Owner ID/Name<font color="red">*</font></th>
                                            <th style="pointer-events: none">Planned Closure Date<font color="red">*</font></th>
                                            <th style="pointer-events: none" >Actual/Forecast Closure Date </th>
                                            
                                            <th style="pointer-events: none">Tracking Comments</th>
                                            <th style="pointer-events: none">Status<font color="red">*</font></th>
                                            <!--<th>Action</th>-->
                                        </tr>
                                        <c:set var="Count" value="${0}" />
                                        <c:forEach begin="0" end="4">
                                            <c:set var="Count" value="${Count+1}" />
                                        <tr align="center">
                                            <td><textarea name="mom_action_point" class="action_point"  id="action_point_${Count}" style="width: 149px;"></textarea>
                                                <span id="actionPoint"></span>
                                            </td>
                                            <td>
                                                <input type="text" class="members_ActionPoint" name="mom_action_point_employee_id" id="members_ActionPoint_${Count}" value="${filterData.employee_no != '' && filterData.employee_no != null?employee_name:''}" 
                                                       placeholder=" Employee ( ID / Name )"  style="width: 225px;" />
                                                <span id="actionPointOwner"></span>
                                            </td>
                                            <td><input type="text" name="mom_planned_close_date" class="datepicker planeDate" id="actionDate_${Count}" style="width: 80px;">
                                                <span id="plannedClosedDate"></span>
                                            </td>
                                            <td align="center">
                                                  
                                            <input type="text" name="mom_actual_close_date" class="datepicker_" id="datepicker_${Count}" style="width: 80px;">
                                            <script>
                                               $(document).ready(function(){           
                                              var row=$('#count_row').val();
                                                for(var i=1;i<=row;i++){
                                            $('#actionDate_'+i).datepicker({
                                                           dateFormat: "dd-M-yy" ,
                                                          minDate: new Date()
                                            
                                                 });
                                            $('#datepicker_'+i).datepicker({
                                                           dateFormat: "dd-M-yy" 
                                                         
                                            
                                                 });
                                                }
                                            });
                                         </script>
                                        </td>
                                         
                                            <td><textarea name="mom_tracking_comments" class="track_comments" style="width: 157px;"></textarea>
                                                <!--<span id="trackingComments"></span>-->
                                            </td>
                                            
                                            <td align="center">
                                                <select name = "mom_action_point_status" id="actionStatus" class="actionStatus" style="width: 96px;" >
                                                    <option value="">--Select--</option>
                                                    <c:forEach items="${action_statusData}" var = "action_statusData">
                                                        <option value="${action_statusData.configuration_key}" ${action_point_status==action_statusData.configuration_key ? 'selected':''}>${action_statusData.action_point_status}</option>
                                                    </c:forEach>
                                                </select> 
                                            </td>
<!--                                            <td align="center">
                                                <img src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add"onclick="add_action_point();">
                                            </td>-->
                                        </tr>
                                        </c:forEach>
                                        <!--<input type="hidden" id="mom_date_${count}" value="${mom_date}" >-->
                                    </tbody>
                                    <input type="hidden" id="count_row" class="count_row" value="${Count}">
<!--                                    <p>${Count}</p>-->
                                </table>
                            </fieldset>
                        </div>
                    </div>
                </div>
                <div class="buttonAlignment">
                    <div class="buttonAlignment" id="btnGroup">
                        <input type="button" name="btnCancel" value="Back" class="qualityback" onclick="javascript: location.href='getAllDetails.htm?page=1'"/>
                        <input type="button" name="momSaveButton" id="saveBtn" value="Save" class="qualitysave" onclick="saveMomForm();"/>
                        <input type="button" name="momSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" onclick="submitMomForm();"/> 
                        <input type="button" name="momDraftButton" id="DraftBtn" value="Draft" class="qualityDraft" onclick="draftMomForm();"/>
                    </div>
                </div>
                                <input type="hidden" name="typecheck" id="typecheck">
                                <input type="hidden" name="typecheck2" id="typecheck2">
            </form>                           
        </div>

        <script type="text/javascript">
            $(document).ready(function(){
               
//                $(".members_Present").autocomplete("ajaxsearch.htm", {
//                    minChars: 1,
//                    width: 350,
//                    matchContains: true
//                }); 
//                $(".members_Present").result(function(event, data, formatted) {
//                    if (data) {
//                        $("#emp_id").val(data[1]);
//                    }
//                });
//               
//                $(".members_Absent").autocomplete("ajaxsearch.htm", {
//                    minChars: 1,
//                    width: 350,
//                    matchContains: true
//                }); 
//                $(".members_Absent").result(function(event, data, formatted) {
//                    if (data) {
//                        $("#emp_id").val(data[1]);
//                    }
//                });
//                $("#mom_date").datepicker({
//                    dateFormat: "dd-mm-yy",
//                    numberOfMonths:[1,2],
//                    minDate: new Date()
//                    // gotoCurrent: true
//                  });
                  $("#mom_date").val($.datepicker.formatDate("d-M-yy", new Date()));
              $('#ListOfMom').click(function(){
                            $('#tested').hide();
                             $('#present').show();
                          
                       });
                      $('#ListOfactions').click(function(){
                            $('#tested').show();
                             $('#present').hide();
                          
                       }); 
                $('#ListOfMom2').click(function(){
                            $('#testing').hide();
                             $('#absent').show();
                          
                       });
                      $('#ListOfactions2').click(function(){
                            $('#testing').show();
                             $('#absent').hide();
                          
                       });         
                 
                $("#test").autoSuggest('${pageContext.request.contextPath}/getEmployeeName.htm', {
                    selectedItemProp: "value", asHtmlID:"test", searchObjProps: "value", selectionLimit:10
                });
                $('#as-values-test').attr("class", "required");
                
                $("#test2").autoSuggest('${pageContext.request.contextPath}/getEmployeeName.htm', {
                    selectedItemProp: "value", asHtmlID:"test2", searchObjProps: "value", selectionLimit:10
                });
                $('#as-values-test2').attr("class", "required");

                $(".members_ActionPoint").autocomplete("ajaxsearch.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
                $(".members_ActionPoint").result(function(event, data, formatted) {
                    if (data) {
                        $("#emp_id").val(data[1]);
                    }
                });
                
                $(document).delegate('#planned_start_time',"change",function() {
                            console.log("dropdown");
                            var userText = $(this).val();
                             $('#actual_start_time').val(userText);
                         });
                         $(document).delegate('#planned_end_time',"change",function() {
                            console.log("dropdown");
                            var userText = $(this).val();
                             $('#actual_end_time').val(userText);
                         });

                $("#mom_date").datepicker({
                    dateFormat: "dd-M-yy", 
                    maxDate:  0,
//                     if ($('#mom_date').val() == ''){
//                         $('#mom_date').val(current_date);
//                     }
//                    minDate:'',
                    onSelect: function(date){            
                        var date1 = $('#mom_date').datepicker('getDate');           
                        var date = new Date( Date.parse( date1 ) ); 
                        date.setDate( date.getDate() );        
                        var newDate = date.toDateString(); 
                        newDate = new Date( Date.parse( newDate ) );                      
                        $('#actionDate_').datepicker("option","minDate",newDate);            
                        $('.planeDate').datepicker("option","minDate",newDate); 
                        $('#datepicker_'+rowId).datepicker({
                            dateFormat: "dd-M-yy" 


                  });
                    } 
                });
//                $('#actionDate_'+i).datepicker({
////                                             var date = $('#actionDate_'+i).val();
//                    dateFormat: "dd-M-yy", 
//                 onSelect: function(date){            
//                var date1 = $('#actionDate_'+i).datepicker('getDate');           
//                var date = new Date( Date.parse( date1 ) ); 
//                date.setDate( date.getDate() );        
//                var newDate2 = date.toDateString(); 
//                newDate2 = new Date( Date.parse( newDate2 ) );                      
//                //                        $('#actionDate_').datepicker("option","minDate",newDate);            
//                $('datepicker_'+i).datepicker("option","minDate",newDate2);  
//                 }
//                });
                
                  $(document).delegate('#momActionPointtable tr:last-child td:last-child',"keydown",function(e) {
                            var x = e.charCode || e.keyCode;  // Get the Unicode value
                            var y = String.fromCharCode(x);  // Convert the value into a character
                            console.log('xgcxgvcxgcgxcg');
                            console.log(e);
                     if (e.keyCode === 9) {
                        add_action_point();
                      }
                      });
//                       $('.action_point').click(function() {
                       $(document).delegate('#momActionPointtable tr:last-child td:last-child',"change",function() {
                            add_action_point();
                       });
              });   
//                        $('.browsers1').change(function () {
//                        //var val = $('.user-select').find(':selected').attr('data-id');
//                        var val = $('.browsers1').prop('selectedIndex');
//                        $(".browsers3").prop('selectedIndex', val);
////                        $('.result').html($(".auto-select").val());
//                        });
                       
//                           
//                            $('#planned_start_time').change(function(){ 
//                                 console.log("dropdown");
//                                var userSelect = $(this).find(":selected").val();
//                                $('#browsers3').val(userSelect);
////                            $('.result').text($( ".auto-select option:selected" ).text());
//                            });
                        
//                       function dropdownall(){
//                           $('#browsers3').val($('#browsers1').val());
//                       }
                
//                $(".agendaText").keyup(function(){
//                    $("#textarea_Agenda").text("Characters left: " + (200 - $(this).val().length));
//                });
//                                                     $('.agendaText').keypress(function (event) {
////                     $(document).delegate('#momActionPointtable tr:last-child td:last-child',"keydown",function(e) {
//                  
//                   var regex = new RegExp("^[a-zA-Z0-9]+$");
//                    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
//                    if (!regex.test(key)) {
//                       event.preventDefault();
//                       return false;
//                    }
//                });
              
//                $(".discussionText").keyup(function(){
//                    $("#textarea_Discussion").text("Characters left: " + (200 - $(this).val().length));
//                });
//                    $('.actionStatus').on(focus.function() {
                       
//                      $('.action_point').delegate()tr:last-child
////
//            $('.actionStatus').keypress(function(e){
//            a  = String.fromCharCode(e.which);
//        console.log(e);
//        console.log("Keycode : " + e.which);
//        console.log("Character : " + a);
//            });
          

//            function add_MemberPresent(){
//                var row = '';
//                row ="<tr>";
//                row +="<td></td>";
//                row +="<td><input type='text' class='members_Present' name='members_present' value='${filterData.employee_no != '' && filterData.employee_no != null?employee_name:''}' placeholder=' Employee ( ID / Name )' style='margin-left: -104px;'/>";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_add.png' style='padding-left: 6px;' alt='Add' title='Add' onclick='add_MemberPresent();'>";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_delete.png' style='padding-left: 6px;' alt='Remove' title='Remove' onclick='remove_MemberPresent(this);'>";
//                 
//                row +="<span id='memberPresent'></span>";
//                row +="</td>";
//                row +="</tr>";
//                $("#momPresent").append(row);
//                $(".members_Present").autocomplete("ajaxsearch.htm", {
//                    minChars: 1,
//                    width: 350,
//                    matchContains: true
//                });
//            }
//            function remove_MemberPresent(rmv){
//                var row = rmv.parentNode.parentNode.rowIndex;
//                document.getElementById("momPresent").deleteRow(row);
//            }
//             
//           
//            function add_MemberAbsent(){
//                var row = '';
//                row ="<tr>";
//                row +="<td>";
//                row +="</td>";
//                row +="<td><input type='text' class='members_Absent' name='members_absent' value='${filterData.employee_no != '' && filterData.employee_no != null?employee_name:''}' placeholder=' Employee ( ID / Name )'  style='margin-left:-104px;' />";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_add.png' style='padding-left: 6px;' alt='Add' title='Add' onclick='add_MemberAbsent();'>";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_delete.png' style='padding-left: 6px;' alt='Remove' title='Remove' onclick='remove_MemberAbsent(this);'>";
//                row +="<span id='memberAbsent' style='color: red;'></span>";
//                row +="<span id='absent_span' style='color: red;display: none'></span>";
//                row +="</td>";
//                row +="</tr>";
//                $("#momAbsent").append(row);
//                $(".members_Absent").autocomplete("ajaxsearch.htm", {
//                    minChars: 1,
//                    width: 350,
//                    matchContains: true
//                });
//            }
//            function remove_MemberAbsent(rmv){
//                var row = rmv.parentNode.parentNode.rowIndex;
//                console.log(row);
//                document.getElementById("momAbsent").deleteRow(row);
//            }
            
//            function add_Agenda(){
//                var row = '';
//                row ="<tr>";
//                row +="<td><textarea type='text' class='agendaText' name='mom_agenda_points' id='textArea' rows='8' cols='30' maxlength='200'></textarea>";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_add.png' style='padding-left: 6px;' alt='Add' title='Add' onclick='add_Agenda();'>";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_delete.png' style='padding-left: 6px;' alt='Remove' title='Remove' onclick='remove_Agenda(this);'>"
//                //                row +="<div id='textarea_Agenda'></div>";
//                row +="<span id='agendaTextArea'></span>";
//                row +="</td>";
//                row +="</tr>";
//                $("#momAgendatable").append(row);
//                $(".agendaText").keyup(function(){
//                    $("#textarea_Agenda").text("Characters left: " + (200 - $(this).val().length));
//                });
//            }
//            function remove_Agenda(rmv){
//                var row = rmv.parentNode.parentNode.rowIndex;
//                document.getElementById("momAgendatable").deleteRow(row);    
//            }
//            
//            function add_Discussions(){
//                var row = '';
//                row ="<tr>";
//                row +="<td><textarea type='text' class='discussionText' name='mom_discussion_points' id='textAreaDiscussion'></textarea>";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_add.png' style='padding-left: 6px;' alt='Add' title='Add' onclick='add_Discussions();'>";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_delete.png' style='padding-left: 6px;' alt='Remove' title='Remove' onclick='remove_Discussions(this);'>"
//                //                row +="<div id='textarea_Discussion'></div>";
//                row +="<span id='discussTextArea'></span>";
//                row +="</td>";
//                row +="</tr>";
//                $("#momDiscussiontable").append(row);
//                $(".discussionText").keyup(function(){
//                    $("#textarea_Discussion").text("Characters left: " + (200 - $(this).val().length));
//                });
//            }
//            function remove_Discussions(rmv){
//                var row = rmv.parentNode.parentNode.rowIndex;
//                document.getElementById("momDiscussiontable").deleteRow(row);
//            }
//            function tabkeypressed(evt){     
//                var _keyCode = evt.which;  
////                var comp = evt.getSource();  
//            if (_keyCode == AdfKeyStroke.TAB_KEY ){     
//                add_action_point();
//           //    AdfCustomEvent.queue(comp, "tabpress",{}, true);           
//           //    evt.cancel();  
//              }  
//            }  
          
            function add_action_point(){
                var rowId = parseInt($('#actionCount').val()) + 1;
                $('#actionCount').val(rowId);
                var row = '';
                row ="<tr align='center'>";
                row +="<td><textarea  name='mom_action_point'class='action_point' style='width: 149px;'></textarea></td>";
                row +="<td><input type='text' class='members_ActionPoint' name='mom_action_point_employee_id' value='${filterData.employee_no != '' && filterData.employee_no != null?employee_name:''}' placeholder=' Employee ( ID / Name )' style='width: 225px;'/></td>";                
                row +="<td><input type='text' name='mom_planned_close_date' class='datepicker planeDate' id='actionDate_"+rowId+"' readonly='readonly' style='width: 80px;'></td>";
                row +="<td><input type='text' name='mom_actual_close_date' class='datepicker_' id='datepicker_"+rowId+"' style='width: 80px;'></td>";
                row +="<td><textarea name='mom_tracking_comments' class='track_comments' style='width: 157px;'></textarea></td>";
                row += '<td> <select name = "mom_action_point_status" id="actionStatus" style="width: 96px;">';
                row += '<option>--Select--</option><c:forEach items="${action_statusData}" var="action_statusData" ><option value="${action_statusData.configuration_key}" ${action_point_status==action_statusData.configuration_key ? 'selected':''}>${action_statusData.action_point_status}</option></c:forEach></select></td>';
//                row +="<td align='center'><img src='${pageContext.request.contextPath}/css/images/tm_add.png' alt='Add' title='Add' onclick='add_action_point();'>";
//                row +="<img src='${pageContext.request.contextPath}/css/images/tm_delete.png' style='padding-left: 6px;' alt='Remove' title='Remove' onclick='remove_action_point(this);'>";
                row +="</td>";
                row +="</tr>";
                $("#momActionPointtable").append(row);
                $('.planeDate').datepicker({
                    dateFormat: "dd-M-yy" 
                });
                var row1=$('#actionCount').val();
                for(var j=1;j<=row1;j++){
                    $('.planeDate').datepicker({
                        dateFormat: "dd-M-yy" 
                    });
                    var date1 = $('#mom_date').datepicker('getDate'); 
                    var date = new Date( Date.parse( date1 ) ); 
                    date.setDate( date.getDate() );        
                    var newDate = date.toDateString(); 
                    newDate = new Date( Date.parse( newDate ) );
                    $("#actionDate_"+j).datepicker( "option", "minDate", newDate );
                    $('.planeDate').datepicker("option","minDate",newDate);
                    $('.datepicker_').datepicker({
                            dateFormat: "dd-M-yy" 


                  });
                }
               
                $(".members_ActionPoint").autocomplete("ajaxsearch.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
            }
            
            function remove_action_point(rmv){
                var row = rmv.parentNode.parentNode.rowIndex;
                document.getElementById("momActionPointtable").deleteRow(row);
            }
            
            function saveMomForm(){
                if($('#tested').css("display") == "block"){
                        $('#typecheck').val("0");
                         console.log("typecheck0");
                    }
                    else if($('#present').css("display") == "block"){
                         $('#typecheck').val("1");
                    }
                   if($('#testing').css("display") == "block"){
                      $('#typecheck2').val("0");
                        console.log("typecheck0");
                    }  
                    else if($('#absent').css("display") == "block"){
                         $('#typecheck2').val("1");
                    }
//                     var text=  $('#textArea').val();
//              var dis=$('#textAreaDiscussion').val();
//              console.log(dis);
//                console.log(text);
////                if (specials.test(text)){
//                  var agenda= text.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                  var discussion=dis.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                     $('#textArea').val(agenda);
//                     $('#textAreaDiscussion').val(discussion);
                if(saveMom()){
                    console.log("method");
                    
                    $('#status').val('s');
                    $('#formMomDetails').attr("action", "insertMom.htm");
                    document.formMomDetails.submit();
                    startLoading();
                }
            }
            function draftMomForm(){
                if(draftMom()){
//                    var text=  $('#textArea').val();
//              var dis=$('#textAreaDiscussion').val();
//              console.log(dis);
//                console.log(text);
////                if (specials.test(text)){
//                  var agenda= text.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                  var discussion=dis.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                     $('#textArea').val(agenda);
//                     $('#textAreaDiscussion').val(discussion);
                   if($('#tested').css("display") == "block"){
                        $('#typecheck').val("0");
                    }
                    else if($('#present').css("display") == "block"){
                         $('#typecheck').val("1");
                    }
                   if($('#testing').css("display") == "block"){
                      $('#typecheck2').val("0");
                    }  
                    else if($('#absent').css("display") == "block"){
                         $('#typecheck2').val("1");
                    }
                    $('#status').val('dr');
                    $('#formMomDetails').attr("action", "insertMom.htm");
                    document.formMomDetails.submit();
                    startLoading();
                }
            }
            function submitMomForm(){
                console.log("function");
//                alert(submitMom());
                 console.log("method");
                    if($('#tested').css("display") == "block"){
                        $('#typecheck').val("0");
                    }
                    else if($('#present').css("display") == "block"){
                         $('#typecheck').val("1");
                    }
                   if($('#testing').css("display") == "block"){
                      $('#typecheck2').val("0");
                    }  
                    else if($('#absent').css("display") == "block"){
                         $('#typecheck2').val("1");
                    }
//                    var specials=/[*|\":<>[\]{}`\\()';@&$]/;
//              var text=  $('#textArea').val();
//              var dis=$('#textAreaDiscussion').val();
//              console.log(dis);
//                console.log(text);
////                if (specials.test(text)){
//                  var agenda= text.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                  var discussion=dis.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                     $('#textArea').val(agenda);
//                     $('#textAreaDiscussion').val(discussion);
                if(submitMom()){
                   
                    $('#status').val('o');
                    $('#formMomDetails').attr("action", "insertMom.htm");
                    document.formMomDetails.submit();
                    startLoading();
                }
            }
            
//            }
            
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
                //                var loaderStartHeight = $("html").height();
                //                loadingDivObj.height = loaderStartHeight + "px";
                if (ns4) {
                    loadingDivObj.visibility = "visible";
                    //                    $("#loadingImage").attr("tabindex", -1).focus();
                } else if (ns6 || ie4)
                    loadingDivObj.display = "block";
                //                $("#loadingImage").attr("tabindex", -1).focus();
            }

            function stopLoading() {
                //                var loaderStopHeight = $("html").height();
                //                loadingDivObj.height = loaderStopHeight + "px";
                if (ns4) {
                    loadingDivObj.visibility = "hidden";
                } else if (ns6 || ie4)
                    loadingDivObj.display = "none";
                //                $("#loadingImage").attr("tabindex", -1).focus();
            }
        </script>
    </body>
</html>
