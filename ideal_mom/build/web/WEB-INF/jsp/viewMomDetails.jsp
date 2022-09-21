<%-- 
    Document   : editMoM
    Created on : Feb 1, 2018, 11:40:01 AM
    Author     : 16656
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MoM View Page</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.ui.core.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <!--<script type="text/javascript" src = "${pageContext.request.contextPath}/js/addMomValidation.js"></script>-->

        <style type="text/css">
            #momtable tbody tr td label {
                /*width: 190px;*/
                padding: 5px;
            }
            form label {
                float: none;
                display: inline-block;
            }
            .hour_format{
                width:100px !important;
            }
            div .formContenet_new {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                float: left;
                /*                margin: 0px 0px 30px 18px;*/
                outline: 1px none;
                padding: 5px;
                padding-bottom: 10px;
                width: 100%;
            }
            .momDetails{
                /*margin:0px auto;*/
                margin: 5px 5px 0 4px;
                /*width: 95%;*/
                /*background-color: #F0F8FF;*/
                border: 1px solid #ccc;
            }
            #momPresent label, #momAbsent label {
                margin-left: 5px;
                padding: 1px 0 8px 0px;
            }
            #momPresent img, #momAbsent img {
                cursor: pointer;
                vertical-align: middle;
            }
            #momAgendatable img, #momDiscussiontable img {
                cursor: pointer;
                margin-top: 5px;
            }
            #momActionPointtable img{
                cursor: pointer;
                margin-top: 3px;
            }
            .agendaText {
                width: 690px;
                height: 20px;
            }
            .discussionText {
                width: 690px;
                height: 20px;
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
                background: #fff;
                color: #555555;
                font-size: 14px;
                margin: 0px 0px 4px 0px;
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
                margin: 40px 0px;
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
            .headings{
                font-weight: bold;
            }
            /*            #momActionPointtable tr td {
                            padding: 5px 0px 5px 0px;
                        }*/
            #momActionPointtable td {
                padding: 4px !important;
            }
            #datadisplay table tr td {
                padding: 5px 3px !important;
            }
            #container {
                clear: both;
                padding: 5px;
                vertical-align: text-top;
            }
        </style>
    </head>
    <body>
         
            <%--<c:forEach items="${totalcount}" var="item" varStatus="actionStatus">--%>
<!--                <input type="hidden" name="mom_edit_first" id ="mom_edit" value="${item.mom_id}"/>
               <%--<c:if test="${item.total_count !=0}">--%>
                  <a href="editMoM.htm?mom_edit=${item.mom_id}"></a>
                  <img src="${pageContext.request.contextPath}/css/images/edit-icon.png" alt="Edit" title="Edit" style="cursor: pointer;"></a>
              <%--</c:if>--%>
            -->
         
         <%--<c:if test="${item.total_count ==0}">--%>
        <div id="container">
            <div class="center_content" style="padding-top: 20px">
                <div class="container_inner" style="margin: 15px 0px;width: 300px;">
                    <p class="page_heading">
                        <span class="heading">View Mom</span> 
                    </p>
                </div>
            </div>
            <input type="hidden" name="mom_view" id="mom_view" value="${list.id}" >
            <div class="formContenet_new" id="datadisplay" style="height: auto">
                <div class="content_page">
                    <div class="momDetails">
                        <fieldset class="momDescriptionFieldset">
                                        <legend>MoM Description</legend>
                                        <table id="momtable" border="0">
                                            <tbody>
                                            <input type="hidden" value="${list.id}" name="id" id="id" />
                                            <tr>
                                                <td>
                                                    <label>Title:

                                                    </label>
                                                </td>
                                                <td><span style="margin-left: -98px;">${list.mom_title}</span></td>
                                                <td><label>Planned Time:</label></td>
                                                <td>
                                                    <span style="width: 12px; display: inline-block;margin-left: -98px; margin-top:5px;">${list.planned_start_time}</span>
                                                </td>
                                                <td>
                                                    <span style="width: 12px; display: inline-block;margin-left: -248px; margin-top:5px;">${list.planned_end_time}</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td><label>Date:</label></td>
                                                <td>
                                                    <input type="hidden" value="${list.mom_date}" name="mom_date" id="mom_date" readonly="readonly">
                                                    <span  style="margin-left: -98px;">${list.mom_date}</span>
                                                </td>
                                                <td><label>Actual Time:</label>
                                                </td>
                                                <td>
                                                    <span style="width: 12px; display: inline-block;margin-left: -98px; margin-top:5px;">${list.actual_start_time}</span>&nbsp; : &nbsp;
                                                </td>
                                                <td>
                                                    <span style="width: 12px; display: inline-block;margin-left: -248px; margin-top:5px;">${list.actual_end_time}</span>&nbsp; : &nbsp;
                                                </td>
                                                 
                                            </tr>
                                            <tr>
                                                <td><label>Venue:</label></td>
                                                <td><span  style="margin-left: -98px;width: 233px;">${list.mom_venue}</span></td>
                                               
<!--                                            </tr>
                                            <tr>-->
                                                <td><label >Minuted By:</label></td>
                                                <td>  <span  style="margin-left: -98px;">${minuted_name}</span></td>
                                                <!--<td><label>Actual End Time:</label></td>-->
                                                
                                            </tr>
                                            </tbody>
                                        </table>              
                                        <table id="momPresent" border="0" style="height: 36px;">
                                           <!--<tr id="TR2_${presentStatus.count}">-->
                                                <td><span class="headings" style="margin-left: 5px;font-weight: bold;">Members Present:</span></td>
                                                <td> 

                                                <c:forEach items="${membersPresentList}" var="mal" varStatus="presentStatus">
                                                        <span style="display: block;margin-bottom: 7px;margin-left: -98px;">${mal.employee_id}</span>
                                                    </c:forEach>
                                                </td>

                                                <td><span class="headings" style="margin-left: 5px;font-weight: bold;">Members Absent:</span></td>
                                                <td>
                                                    <c:forEach items="${membersAbsentList}" var="mal" varStatus="absentStatus">
                                                        <span style="display: block;margin-bottom: 7px;margin-left: -98px;">${mal.employee_id}</span>
                                                    </c:forEach>
                                                </td>
                                                <td></td><td></td>
                                            <!--</tr>-->
                                        </table> 
                                    </fieldset>
                        <fieldset class="momAgendaFieldset">
                            <legend>Agenda</legend> 
                             <legend style="margin-top: -23px;margin-left: 487px;">Discussion</legend>
                            <table id="momAgendatable" border="0" style="border:0px">
                                <tbody>
                                   
                                    <tr>
                                    <c:forEach items="${momAgendaList}" var="agenda" varStatus="agendaStatus">
                                        <!--<tr id="TR_${agendaStatus.count}" class="rowCount">-->
                                            <!--<input type="hidden" name="mom_agenda_id" id="agenda_id_${agendaStatus.count}" value="${agenda.agenda_id}" />-->
                                            <!--<input type="hidden" name="mom_agenda_deleted" id="deletedTR_${agendaStatus.count}" value="0" />-->
                                            <td class="rowCount1"style="border:1px solid #99BBE8; width: 49%;">
                                                <ul style="list-style-type:disc">
                                                    <li style="margin-left: 20px; height: 7px;"> ${agenda.agenda_point}</li><br>
                                                </ul>
                                            </td>    
                                        <!--</tr>-->
                                    </c:forEach>
                              
                                        <c:forEach items="${momDiscussionList}" var="discuss" varStatus="discussStatus">
                                        <!--<tr id="TR1_${discussStatus.count}" class="rowCount">-->
                                            <!--<input type="hidden"  name="mom_discussion_id" id="discussion_id_${discussStatus.count}" value="${discuss.discussion_id}"/>-->
                                            <!--<input type="hidden" name="mom_discussion_deleted" id="deletedTR1_${discussStatus.count}" value="0" />-->
                                            <td class="rowCount1" style="border:1px solid #99BBE8; width: 49%;">
                                                <ul style="list-style-type:disc">
                                                    <li style="margin-left: 20px; height: 7px; padding-bottom: 14px;"> ${discuss.discussion_point}</li>
                                                </ul>
                                            </td>  
                                        <!--</tr>-->
                                    </c:forEach>
                                     </tr>  
                                    
                                </tbody>
                            </table>
                        </fieldset>
<!--                        <fieldset class="momDiscussionFieldset">
                            <legend>Discussion</legend>
                            <table id="momDiscussiontable" border="0">
                                <tbody>
                                    <%--<c:forEach items="${momDiscussionList}" var="discuss" varStatus="discussStatus">--%>
                                        <tr id="TR1_${discussStatus.count}" class="rowCount">
                                            <input type="hidden"  name="mom_discussion_id" id="discussion_id_${discussStatus.count}" value="${discuss.discussion_id}"/>
                                            <input type="hidden" name="mom_discussion_deleted" id="deletedTR1_${discussStatus.count}" value="0" />
                                            <td class="rowCount1">
                                                <ul style="list-style-type:disc">
                                                    <li style="margin-left: 20px; height: 7px; padding-bottom: 14px;"> ${discuss.discussion_point}</li>
                                                </ul>
                                            </td>  
                                        </tr>
                                    <%--</c:forEach>--%>
                                </tbody>
                            </table>
                        </fieldset>-->
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
                                                
                                                <%--<c:forEach items="${history}" var = "his" >--%>
                                                    <%--<c:if test="${his.action_id == actionObj.action_point_id}">--%>
                                                    <!--</strike>  ${his.previous_planned_close_date}</strike><br>-->
                                                    <%--</c:if>--%>
                                                <%--</c:forEach>--%>
                                            <span>${actionObj.actual_close_date}</span>
                                            </td>
                                            <td align="center">
                                                <%--<c:forEach items="${history}" var = "his" >--%>
                                                     <%--<c:if test="${his.action_id == actionObj.action_point_id}">--%>
                                                     <!--<strike>   ${his.tracking_comments}</strike><br>-->
                                                     <%--</c:if>--%>
                                                 <%--</c:forEach>--%>
                                                 <span>${actionObj.tracking_comments}</span>
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
            </div>
            <div class="buttonAlignment">
                <div class="buttonAlignment" id="btnGroup">
                    <input type="button" name="btnCancel" value="Back" class="qualityback" onclick="javascript: location.href = 'javascript:history.go(-1)'"/>
                </div>
            </div>
        </div>  
         <%--</c:if>   </c:forEach>--%>      
    </body>
</html>
       