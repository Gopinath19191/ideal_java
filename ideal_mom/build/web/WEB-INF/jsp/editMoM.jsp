<%-- 
    Document   : editMoM
    Created on : Feb 1, 2018, 11:40:01 AM
    Author     : 16656
--%>
<%--<%@page import="java.util.ArrayList"%>--%>
<%--<%@include file="header.jsp"  %>--%>
<%--<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MoM Edit Page</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.ui.core.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.autocomplete.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/editMomValidation.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autoSuggest.mod.js"></script>
<!--        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ext-all.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/tp.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/extjs/ext-all.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jscript.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui.min.js"></script>-->
        <link rel="stylesheet" href="css/autoSuggest.css" type="text/css">
          <script type="text/javascript" src = "${pageContext.request.contextPath}/javascript/steptwo.js"></script>
        <script type="text/javascript" src = "${pageContext.request.contextPath}/js/timeValidation.js"></script>
        <style type="text/css">
            #momtable tbody tr td label {
                /*width: 190px;*/
                padding: 5px;
                font-weight: bold;
            }
            form label {
                float: none;
                display: inline-block;
            }
            .hour_format{
                width:100px !important;
            }
            #listName{
                /*font-weight: bold;*/
                float: right;
                margin-top: -35px;
                /*                margin: -20px 0px 0px 0px;
                                text-decoration: none;
                                color: blue;
                                cursor: pointer;*/
            }
            div .formContenet_new {
                background: none repeat scroll 0 0 #FFFFFF;
                border: 1px solid #CADFF2;
                float: left;
                /*                margin: 0px 0px 30px 18px;*/
                outline: 1px none;
                /*padding-bottom: 0;*/
                width: 950px;
            }
            .momDetails{
                margin:0px auto;
                /*width: 95%;*/
                /*background-color: #F0F8FF;*/
                border: 1px solid #ccc;
            }
            #momPresent label, #momAbsent label {
                margin-left: 5px;
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
            .qualitysubmit3 {
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
            .switch {
                position: relative;
                display: inline-block;
                width: 48px;
                height: 18px;
            }
            .switch input {display:none;}

            .slider {
                position: absolute;
                cursor: pointer;
                top: 0;
                left: 0;
                right: 0;
                bottom: 0;
                background-color: #ccc;
                -webkit-transition: .4s;
                transition: .4s;
            }

            .slider:before {
                position: absolute;
                content: "";
                height: 13px;
                width: 15px;
                left: 4px;
                bottom: 3px;
                background-color: white;
                -webkit-transition: .4s;
                transition: .4s;
            }

            input:checked + .slider {
                background-color: #3385ff;
            }

            /*            input:focus + .slider {
                          box-shadow: 0 0 1px #2196F3;
                        }*/

            input:checked + .slider:before {
                -webkit-transform: translateX(26px);
                -ms-transform: translateX(26px);
                transform: translateX(26px);
            }
            #tableStatus tr td {
                width:25%;
            }
            /* Rounded sliders */
            .slider.round {
                border-radius: 30px;
            }

            .slider.round:before {
                border-radius: 40%;
            }
            #container_action{
                padding: 0 30px;
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
            .headings{
                font-weight: bold;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function(){
           
                $("#tablink_mom").click(function(){
                    $("#tablink_mom").addClass("btn"); 
                    $("#tablink_acttion").removeClass("btn"); 
                    $("#container").show(); 
                    $("#container_action").hide(); 
                });
                $("#tablink_acttion").click(function(){
                    $("#tablink_mom").removeClass("btn"); 
                    $("#tablink_acttion").addClass("btn"); 
                    $("#container_action").show(); 
                    $("#container").hide(); 
                });
            });
        </script>
    </head>
    <body>
        <div id="loadingDiv" style="position: absolute; width: 100%; height: 100%; background-color: rgb(0, 0, 0, 0.5); display: none; top: 0pt; left: 0pt;">
            <div style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center; color:#000;">
                <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
            </div>
        </div>
        <div id="container">
            <div class="center_content" style="padding-top: 20px">
                <div class="container_inner" style="margin: 15px 0px;width: 300px;">
                    <p class="page_heading">
                        <span class="heading">Edit Minutes of Meeting</span> 
                    </p>
                </div>
            </div>
            <form name="formMomDetails" id="formMomDetails" autocomplete="off">
                <!--                <input type="hidden" id="status" name="mom_status"  />-->
                <input type="hidden" name="mom_edit" id="mom_edit" value="${list.id}" />
                <input type="hidden" name="mom_status_name" id="mom_status_name" value="${list.mom_status}" />
                <input type="hidden" id="emp_id" name="employee_no" value="${filterData.employee_no}" />
                <div class="formContenet_new" id="datadisplay" style="height: auto">
                    <div class="content_page">
                        <div class="momDetails">
                            <!--<p>${list.mom_status }</p>-->
                            <c:choose>
                                <c:when test="${list.mom_status =='s'|| list.mom_status =='dr'}">
                                    <fieldset class="momDescriptionFieldset">
                                        <legend>MoM Description</legend>
                                        <table id="momtable" border="0">
                                    <tbody>
                                        <tr>
                                            <td>
                                                  <label>Title

                                                     </label>
                                              </td>
                                                <td>
                                                    <span style="margin-left: -102px;">${list.mom_title}</span>
                                                </td>
                                            <td>
                                                <label style="margin-left: 40px;">Planned Time
                                                    <font color="red">*</font>
                                                </label>
                                            </td>
                                            <td>
                                                <input list="browsers1" id="planned_start_time" value="${list.planned_start_time}" class="accrueTime" name="planned_start_time" style="margin-left:-91px;width:82px;"/>
                                                <span id="planned"></span>
                                                   <datalist  class="browsers1" id="browsers1">
                                                    <option value=""></option>
                                                    <c:forEach items="${timing}" var = "plannedstarttime">
                                                        <option value="${plannedstarttime}">${plannedstarttime}</option>
                                                    </c:forEach>
                                             </datalist>
                                            </td>
                                            <td>
                                             
                                             <input list="browsers2" id="planned_end_time" value="${list.planned_end_time}"  class="accrueTime" name="planned_end_time" style="margin-left:-178px;width:82px;"/>
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

                                                </label>
                                                </td>
                                                <td>
                                                    <input type="hidden" value="${list.mom_date}" name="mom_date" id="mom_date" readonly="readonly">
                                                    <span  style="margin-left: -103px;">${list.mom_date}</span>
                                                </td>
                                           
                                               <td>
                                                    <label style="margin-left: 40px;">Actual Time
                                                        <font color="red">*</font>
                                                    </label>
                                                </td>
                                               <td>
                                                <input list="browsers3" id="actual_start_time" value="${list.actual_start_time}" class="accrueTime" name="actual_start_time" style="margin-left:-91px;width:82px;"/> 
                                                <datalist  class="browsers3" id="browsers3">
                                                 <option value=""></option>
                                                    <c:forEach items="${timing}" var = "actualstarttime">
                                                        <option value="${actualstarttime}">${actualstarttime}</option>
                                                    </c:forEach>
                                                </datalist>   
                                                <span id="actual_start" ></span>
                                           </td>
                                           <td>
                                             <input list="browsers4" id="actual_end_time" value="${list.actual_end_time}"  class="accrueTime" name="actual_end_time" style="margin-left:-178px;width:82px;"/>   
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

                                                </label>
                                            </td>
                                            <td>
                                                <input type="text" name="mom_venue" id="mom_venue" value="${list.mom_venue}"style="margin-left: -103px; width:304px">
                                                <span id="venue" ></span>
                                            </td>

                                            <td>
                                                <label style="margin-left: 41px;">Minuted By
                                                    
                                                </label>
                                            </td>
                                            <td>
                                                <span style="margin-left: -90px;">${minuted_name} </span>
                                            </td>

                                        </tr>
                                    </tbody>
                                </table>
                                <table id="momPresent" class="momPresent" border="0">
                                    <tr>
                                        <td>
                                            <label for="test"style="margin-top: 20px;font-weight: bold;">Members Present  <font color="red">*</font></label>
                                        </td>
                                        <td> 
                                            <div id="tested" style="margin-left: -2px;"> 
                                                 <c:forEach items="${membersPresentList}" var="mal" varStatus="presentStatus">
                                                        <span style="margin-bottom: 7px;margin-left: -98px;" id="checkkaro">${mal.employee_id}</span><br>
                                                  </c:forEach>
                                               
                                                  <!--<input type="hidden" readonly id="editemp" name="editemp" value="${editemp}"/>-->
                                             
                                                <input type="text" id="test" class="members_present present" name="test"  value=""  autocomplete="off" value="0" style="/* margin-top: 2px; */margin-bottom: 0px;/* margin-left: -96px; */"/>
                                                <a href="#" id="ListOfMom" class="ListOfMom" style="margin-left: 104px;padding-top: 10px;position: absolute;">
                                                   <img src="${pageContext.request.contextPath}/css/images/addcontact.png" alt="add" title="Add contacts" style="cursor: pointer;margin-top: -2px;height:27px;/* margin-left: -85px; */"></a>
                                                 <span id="memberpresent" class="memberpresent" style="margin-top: -5px;position: absolute;"></span>  
                                                  
                                            </div> 
                                        </td>
 
                                        <td>
                                        <div id="present" style="display: none;">
                                            <c:forEach items="${membersPresentList}" var="mal" varStatus="presentStatus">
                                                        <span style="margin-bottom: 7px;margin-left: -317px;" id="checkkaro">${mal.employee_id}</span><br>
                                                  </c:forEach>
                                         <textarea id="members_present" name="members_present" class="members_Present present" style="margin: 0px 0px 0px -333px;width: 273px;height: 48px;"></textarea>
                                         <a href="#" id="ListOfactions" style="margin-left: -48px;padding-top: 18px;position: absolute;">
                                           <img src="${pageContext.request.contextPath}/css/images/back_1.png" alt="add" title="Add contacts" style="cursor: pointer;margin-top: -7px;"></a>
                                            <span id="memberPresent" class="memberPresent" ></span>
                                        </div>
                                        </td>
                                   
                                        <td>
                                            <label for="test2" style="margin-top: 20px;margin-left: -187px;font-weight: bold;">Members Absent</label>
                                        </td>
                                        <td>
                                            <div id="testing" style="margin-left: -205px;">
                                                <c:forEach items="${membersAbsentList}" var="mal" varStatus="absentStatus">
                                                        <span style="display: block;margin-left: -98px;">${mal.employee_id}</span>
                                                    </c:forEach>
                                                <input type="text" id="test2"class="members_absent"  name="test2" value="" autocomplete="off"  value="0" style="/* margin-top: 2px; */margin-bottom: 0px;/* margin-left: -105px; */" />
                                                <a href="#" id="ListOfMom2" style="margin-left: 104px;padding-top: 10px;position: absolute;">
                                                    <img src="${pageContext.request.contextPath}/css/images/addcontact.png" alt="add" title="Add contacts" style="cursor: pointer;margin-top: -3px;height:27px;/* margin-left: -90px; */"></a>
                                                <span id="memberabsent" class="memberabsent"></span>
                                            
                                            </div>
                                        </td>     
                                       
                                        <td>
                                            <div id="absent" style="display: none;">
                                                <c:forEach items="${membersAbsentList}" var="mal" varStatus="absentStatus">
                                                        <span style="display: block;margin-left: -283px;">${mal.employee_id}</span>
                                                    </c:forEach>
                                            <textarea id="members_absent" name="members_absent" class="members_Absent" style="margin: 0px 0px 0px -304px;width: 273px;height: 48px;"></textarea>
                                            <a href="#" id="ListOfactions2" style="margin-left: -22px;padding-top: 18px;position: absolute;">
                                              <img src="${pageContext.request.contextPath}/css/images/back_1.png" alt="add" title="Add contacts" style="cursor: pointer;margin-top: -7px;"></a>
                                            <span id="memberAbsent" class="memberAbsent"></span>
                                    <span id="absent_span"   style="color: red;"  ></span></div></td>
                                    </tr>
                                </table>
                            </fieldset>
                                </c:when>
                                <c:otherwise>
                                    <fieldset class="momDescriptionFieldset">
                                        <legend>MoM Description</legend>
                                        <table id="momtable" border="0">
                                            <tbody>
                                            <input type="hidden" value="${list.id}" name="id" id="id" />
                                            <tr>
                                                <td>
                                                    <label>Title:</label>
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
                                              
                                                <td><label >Minuted By:</label></td>
                                                <td>  <span  style="margin-left: -98px;">${minuted_name}</span></td>
                                                
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
                                                        <span style="display: block;margin-bottom: 7px;margin-left: -98px;" >${mal.employee_id}</span>
                                                    </c:forEach>
                                                </td>
                                                <td></td><td></td>
                                            <!--</tr>-->
                                        </table> 
                                    </fieldset>
                                </c:otherwise>
                            </c:choose>
                          
                                    <fieldset class="momAgendaFieldset">
                                        <legend>Agenda</legend>
                                        <input type="hidden" value="1" id="agendaCount"/>
                                        <c:choose>
                                            <c:when test="${!empty(momAgendaList)}">
                                                <input type="hidden" name="agendaCount" id="agendaCount" value="${fn:length(momAgendaList)}" />
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="agendaCount" id="agendaCount" value="1" />
                                            </c:otherwise>
                                        </c:choose>
                                                <legend style="margin-top: -20px;margin-left: 461px;">Discussion</legend>
                                        <c:choose>
                                            <c:when test="${!empty(momDiscussionList)}">
                                                <input type="hidden" name="discussionCount" id="discussionCount" value="${fn:length(momDiscussionList)}" />
                                            </c:when>
                                            <c:otherwise>
                                                <input type="hidden" name="discussionCount" id="discussionCount" value="1" />
                                            </c:otherwise>
                                        </c:choose>
                                        <table id="momAgendatable" border="0" style="margin-top: -16px;">
                                            <tbody>
                                                <tr>
                                                <c:forEach items="${momAgendaList}" var="agenda" varStatus="agendaStatus">
                                                    <!--<tr id="TR_${agendaStatus.count}" class="rowCount">-->
                                                    <input type="hidden" name="mom_agenda_id" id="agenda_id_${agendaStatus.count}" value="${agenda.agenda_id}" />
                                                    <input type="hidden" name="mom_agenda_deleted" id="deletedTR_${agendaStatus.count}" value="0" />
                                                    <td>
                                                        <textarea name="mom_agenda_points" class="agendaText" id="textArea_${agendaStatus.count}" rows="8" cols="30" style=" width: 445px;height: 100px;">${agenda.agenda_point}</textarea>
                                                    </td>
                                                    <div id="textarea_Agenda"></div>
                                                    <span id="agendaTextArea"></span>
                                                </c:forEach>
                                                <c:forEach items="${momDiscussionList}" var="discuss" varStatus="discussStatus">
                                                    <!--<tr id="TR1_${discussStatus.count}" class="rowCount">-->
                                                    <input type="hidden"  name="mom_discussion_id" id="discussion_id_${discussStatus.count}" value="${discuss.discussion_id}"/>
                                                    <input type="hidden" name="mom_discussion_deleted" id="deletedTR1_${discussStatus.count}" value="0" />
                                                    <td>
                                                        <textarea name="mom_discussion_points" class="discussionText" id="textAreaDiscussion_${discussStatus.count}" style=" width: 445px;height: 100px;">${discuss.discussion_point}</textarea>
                                                    </td>

                                                    <div id="textarea_Discussion"></div>
                                                    <span id="discussTextArea"></span>
                                                    <!--</tr>-->
                                                
                                                </c:forEach>
                                              </tr>  
                                            </tbody>
                                        </table>
                                    </fieldset>
                              <fieldset class="momActionPointFieldset">
                                <legend>Action Point</legend>
                                <span id="actionPoint"></span>
<!--                                <input type="hidden" name="mom_edit" id="mom_edit" value="${list.id}" />-->
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
                                            <th><input type="checkbox" name="checkall" id="checkall" ></th>                                  
                                            <th>Actions</th>
                                            <th style="pointer-events: none">Owner ID/Name</th>
                                            <th style="pointer-events: none">Planned Closure Date</th>
                                            <th style="pointer-events: none">Actual / Forecast Closure Date</th>
                                            <th style="pointer-events: none">Tracking Comments</th>
                                            <th style="pointer-events: none">Status</th>
                                            <!--<th style="pointer-events: none">Action</th>-->
                                        </tr>
                                        <!--<p>${actionPointList}</p>-->
                                        <c:set var="Count1" value="${0}" />
                               <c:if test="${!empty(actionPointList)}">
                                   
                                 <c:forEach items="${actionPointList}" var="action" varStatus="actionStatus">
                                      
                                        <c:choose>
                                            <c:when test="${action.action_point_status!='c'}">
                                                <c:set var="Count1" value="${Count1+1}" />
                                                
                                                <tr align="center" id="TR4_${actionStatus.count}">
                                                    <td>
                                                        <input type="hidden" name="mom_action_point_id" id="action_point_id_${Count}" value="${action.action_point_id}" />
                                                <!--<input type="hidden" name="mom_action_point_employee_id" id="action_point_employee_id_${actionStatus.count}" value="${action.action_point_employee_id}" />-->
                                                <input type="hidden" name="mom_action_point_deleted" id="deletedTR4_${actionStatus.count}" value="0" />                                                
                                                        <input type="hidden" id="mom_date" name="mom_date" value="${action.planned_close_date}">
                                                        <input type="checkbox" name="check_box" id="check_box_${action.action_point_id}"  class="check_box" value="${action.action_point_id}">
                                                       <span id="checkbox4"></span>
                                                    </td>
                                                    <td><textarea name="mom_action_point" class="action_point" id="action_point_${Count1}" style="width: 151px;padding-bottom: 0px;">${action.action_point}</textarea>
                                                     <span id="actionPoint"></span>
                                                    </td>
                                                    <td><input type="text" title="${action.employee_name}" value="${action.employee_id}" name="mom_action_point_employee_id" class="members_ActionPoint" id="members_ActionPoint_${Count1}" style="width: 226px;">
                                                    <span id="actionPointOwner"></span>
                                                    </td>
                                                    <td><input type="text" value="${action.planned_close_date}" name="mom_planned_close_date" class="datepicker" readonly="readonly" id="actionDate_${actionStatus.count}"  style="width: 80px;">
                                                     <span id="plannedClosedDate"></span>
                                                    </td>
                                                    <td><input type="text" value="${action.actual_close_date}" name="mom_actual_close_date" id="datepicker_${action.action_point_id}" style="width: 80px;">
                                                     <script>
                                        
                                                 var date =new Date('${action.planned_close_date}');
                                                 $('#datepicker_${action.action_point_id}').datepicker({
                                                     dateFormat: 'dd-M-yy',
                                                     changeMonth: true,
                                                     changeYear: true,
                                                     minDate:date

                                                 });

                                                </script>
                                                    
                                                    </td>

                                                <td><textarea  name="mom_tracking_comments" class="track_comments" id="tracking_comments_${action.action_point_id}"id="actionComments_${actionStatus.count}">${action.tracking_comments}</textarea> </td>
                                                <td>
                                                    
                                                    <select name = "mom_action_point_status" id="actionStatus"  style="width: 100px;">
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${action_statusData}" var = "action_statusData">
                                                            <option value="${action_statusData.configuration_key}" ${action.action_point_status==action_statusData.configuration_key ? 'selected':''}>${action_statusData.action_point_status}</option>
                                                        </c:forEach>
                                                    </select> 
                                                     <input type="hidden" id="mom_date_${actionStatus.count}" value="${action.mom_date}" >
                                                </td>
                                                
                                                </tr>
                                               
                                            </c:when>
                                         <c:otherwise>
                                              <c:set var="Count1" value="${Count1+1}" />
                                                <!--<input type="hidden" id="mom_action_point_status" name="mom_action_point_status" value="${action.action_point_status}">-->
                                                
                                             <tr align="center" id="TR4_${actionStatus.count}">
                                               
                                                <td></td>
                                               
                                                <td>
                                                    <input type="hidden" id="mom_date" name="mom_date" value="${action.planned_close_date}">
                                                     <input type="hidden" name="mom_action_point_id" id="action_point_id_${Count}" value="${action.action_point_id}" />
                                                <!--<input type="hidden" name="mom_action_point_employee_id" id="action_point_employee_id_${actionStatus.count}" value="${action.action_point_employee_id}" />-->
                                                <input type="hidden" name="mom_action_point_deleted" id="deletedTR4_${actionStatus.count}" value="0" />
                                                    <textarea  readonly="readonly" name="mom_action_point" class="action_point" id="action_point_${Count1}" style="width: 151px;padding-bottom: 0px;">${action.action_point}</textarea>
                                                 <span id="actionPoint"></span>
                                                </td>
                                                <td><input type="text" value="${action.employee_id}" name="mom_action_point_employee_id" readonly="readonly" class="members_ActionPoint" id="members_ActionPoint_${Count1}" style="width: 226px;" title="${action.employee_name}">
                                                <span id="actionPointOwner"></span></td>
                                                <td><input type="text" value="${action.planned_close_date}" name="mom_planned_close_date"  readonly="readonly"  style="width: 82px;">
                                                 <span id="plannedClosedDate"></span>
                                                </td>
                                                <td><input type="text" value="${action.actual_close_date}" name="mom_actual_close_date" readonly="readonly" id="actionDate_${actionStatus.count}"  style="width: 80px;" ></td>
                                                <td><textarea  name="mom_tracking_comments" readonly="readonly" class="track_comments" readonly="readonly" id="actionComments_${actionStatus.count}">${action.tracking_comments}</textarea></td>
                                                <c:forEach items="${action_statusData}" var="action_statusData" >
                                                    <c:if test="${action.action_point_status==action_statusData.configuration_key}">
                                                        <td>
                                                            <input type="text"  style="width: 100px;" name="mom_action_point_status_temp" value="${action_statusData.action_point_status}" readonly="readonly">
                                                            <input type="hidden" name="mom_action_point_status" value="${action_statusData.configuration_key}" readonly="readonly">
                                                        </td>
                                                    </c:if>
                                                </c:forEach>
                                                       
                                                </tr> 
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </c:if>
                                       <c:if test="${empty(actionPointList)}">
                                            
                                          <c:forEach begin="0" end="4">
                                              <c:set var="Count1" value="${Count1+1}" />
                                                 <tr align="center">
                                                     <td align="center" >
                                                           <input type="hidden" name="mom_action_point_id" id="action_point_id_${Count}" value="${action.action_point_id}" />
                                                                <input type="checkbox" name="check_box" id="check_box_${action.action_point_id}"  class="check_box" value="${action.action_point_id}"> 
                                                              <span id="checkbox4"></span>
                                                     </td>
                                                    <td><textarea name="mom_action_point" class="action_point"  id="action_point_${Count1}" style="width: 149px;"></textarea>
                                                        <span id="actionPoint"></span>
                                                    </td>
                                                    <td>
                                                        <input type="text" class="members_ActionPoint" name="mom_action_point_employee_id" id="members_ActionPoint_${Count1}" value="${filterData.employee_no != '' && filterData.employee_no != null?employee_name:''}" 
                                                               placeholder=" Employee ( ID / Name )"  style="width: 225px;" />
                                                        <span id="actionPointOwner"></span>
                                                    </td>
                                                    <td><input type="text" name="mom_planned_close_date" class="datepicker planeDate" id="actionDate_${Count1}" style="width: 80px;">
                                                        <script>
                                                       $(document).ready(function(){           
                                                      var row=$('#count_row').val();
                                                        for(var i=1;i<=row;i++){
                                                    $('#actionDate_'+i).datepicker({
                                                                   dateFormat: "dd-M-yy" ,
                                                                  minDate: new Date()

                                                            });
                                                        } 
                                                    });
                                                    
                                                 </script>
                                                        <span id="plannedClosedDate"></span>
                                                    </td>
                                                    <td align="center">
                                                        <input type="text" name="mom_actual_close_date" class="datepicker_" id="datepicker_${Count1}" style="width: 80px;">
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
                                                
                                            </tr>
                                        </c:forEach>
                                     </c:if>
                                                  
                                    </tbody>
                                    
                                </table>
                                <input type="hidden" id="count_row" class="count_row" value="${Count1}">
                                <input type="hidden" name="actionid" id="actionid" class="actionid" >
                            </fieldset>

                        </div>
                    </div>
                </div>
                <div class="buttonAlignment">
                    <div class="buttonAlignment" id="btnGroup">
                        <!--                        <input type="button" name="btnCancel" value="Back" class="qualityback" onclick="javascript: location.href='javascript:history.go(-1)'"/>-->
                        <input type="button" name="btnCancel" value="Cancel" class="qualityback" onclick="javascript: location.href='getAllDetails.htm?page=1'"/>
                        <c:choose>
                            <c:when test="${list.mom_status=='s'}">
                                <input type="button" name="momSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" onclick="updateMom();"/> 
                            </c:when>
                              <c:when test="${list.mom_status=='dr'}">
                                <input type="button" name="momSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" onclick="updateMom();"/> 
                            </c:when>  
                            <c:otherwise>
                                <input type="button" name="momSubmitButton" id="submitBtn" value="Update" class="qualitysubmit3" onclick="updateMom();"/>
                            </c:otherwise>
                        </c:choose>    
                    </div>
                </div>
                                 <input type="hidden" name="typecheck" id="typecheck">
                                <input type="hidden" name="typecheck2" id="typecheck2">
            </form>
        </div>

        <script type="text/javascript">
            $(document).ready(function(){
              $(document).delegate('#momActionPointtable tr:last-child td:last-child',"keydown",function(e) {
                  var userText=$(this).closest('tr').find('td input').val();
                     console.log(userText);
                    var x = e.charCode || e.keyCode;  // Get the Unicode value
                    var y = String.fromCharCode(x);  // Convert the value into a character
                    console.log('xgcxgvcxgcgxcg');
                    console.log(e);
                     if (e.keyCode === 9) {
                        add_action_point(userText);
                      }
                });
                      
                $(document).delegate('#momActionPointtable tr:last-child td:last-child',"change",function() {
                   var userText=$(this).closest('tr').find('td input').val();
                     console.log(userText);
                     add_action_point(userText);
                });
                
                
                var row1=$('#actionCount').val();
                for(var j=1;j<=row1;j++){
                    $('#actionDate_'+j).datepicker({
                        dateFormat: 'dd-M-yy',
                        changeMonth: true,
                        changeYear: true,
                        minDate:$('#mom_date').val()
                    });
                    
                }
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
                var row1=$('#actionCount').val();
                for(var j=1;j<=row1;j++){
                 $(document).delegate('#action_point_'+j,"click",function() {
//                            var userText = $(this).closest('tr');
                            var userText1=$(this).closest('tr').find('td input').val();
//                                alert(userText);
                               $('#check_box_'+userText1).attr("checked", true); 
                            }); 
                }    
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
             });     
   //                $(".agendaText").keyup(function(){
//                    $("#textarea_Agenda").text("Characters left: " + (200 - $(this).val().length));
//                });
//                $('.agendaText').keypress(function (event) {
////                     $(document).delegate('#momActionPointtable tr:last-child td:last-child',"keydown",function(e) {
//                    var regex = new RegExp("^[a-zA-Z0-9]+$");
//                    var key = String.fromCharCode(!event.charCode ? event.which : event.charCode);
//                    if (!regex.test(key)) {
//                       event.preventDefault();
//                       return false;
//                    }
//                });                       
                
//                $(".discussionText").keyup(function(){
//                    $("#textarea_Discussion").text("Characters left: " + (200 - $(this).val().length));
//                });
//'#momActionPointtable tr:last-child td:last-child',"keydown"
//                 $(document).delegate('#actionStatus',"keydown",function(e) {
//                            var x = e.charCode || e.keyCode;  // Get the Unicode value
//                            var y = String.fromCharCode(x);  // Convert the value into a character
//                            console.log('xgcxgvcxgcgxcg');
//                            console.log(e);
//                     if (e.keyCode === 9) {
//                        add_action_point();
//                      }
//                      });

//                $(document).delegate('#actionStatus',"change",function() {
//                     add_action_point();
//                });
               
               
                 
//            });
//             });
                
         function add_action_point(actionid){
                var rowId = parseInt($('#actionCount').val()) + 1;
                actionid++;
                $('#actionCount').val(rowId);
                var row = '';
                row +='<tr align="center" id="TR4_'+rowId+'">';
                
                row +='<td><input type="hidden" name="mom_action_point_id" id="action_point_id_'+rowId+'" value="0" /><input type="hidden" name="mom_action_point_deleted" id="deletedTR4_'+rowId+'" value="0" /> <input type="checkbox" name="check_box" id="check_box_'+rowId+'"  class="check_box" value="0"></td>';
                row +='<td><textarea name="mom_action_point" class="action_point" id="action_point_'+rowId+'"style="width: 150px;padding-bottom: 0px;"></textarea></td>';
                row +='<td><input type="text" name="mom_action_point_employee_id" class="members_ActionPoint" id="actionOwner_'+rowId+'" value="${filterData.employee_no != '' && filterData.employee_no != null?employee_name:''}" placeholder=" Employee ( ID / Name )" style="width: 226px;" title=""/></td>';
                row +='<td><input type="text" name="mom_planned_close_date" class="datepicker" id="actionDate_'+rowId+'" style="width: 80px;"></td>';
                row +='<td><input type="text" value="" name="mom_actual_close_date" class="datepicker" id="datepicker_'+rowId+'"  style="width: 80px;"></td>';
                row +='<td><textarea name="mom_tracking_comments" class="track_comments" id="actionComments_'+rowId+'"></textarea></td>';
                row += '<td> <select name = "mom_action_point_status" id="actionStatus" style="width: 100px;">';
                row += '<option>--Select--</option><c:forEach items="${action_statusData}" var="action_statusData" ><option value="${action_statusData.configuration_key}" ${action.action_point_status==action_statusData.configuration_key ? 'selected':''}>${action_statusData.action_point_status}</option></c:forEach></select><input type="hidden" id="mom_date_'+rowId+'" value="0" /></td>';
//                row +='<td align="center"><img src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" onclick="add_action_point(1);">';
//                row +='<img src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="padding-left: 9px;" alt="Remove" title="Remove" onclick="remove_action_point(' + rowId + ',0);">';
                
//                row +='</td>';
                row +='</tr>';
                $("#momActionPointtable").append(row);
                /*$(".datepicker").datepicker({
                    //                    dateFormat: 'yy-mm-dd',
                    dateFormat: 'dd-M-yy',
                    changeMonth: true,
                    changeYear: true,
                    maxDate: 0
                    //                    minDate: 0
                })*/
                var row1=$('#actionCount').val();
                for(var j=1;j<=row1;j++){
                    $('#actionDate_'+j).datepicker({
                        dateFormat: 'dd-M-yy',
                        changeMonth: true,
                        changeYear: true,
                        minDate:$('#mom_date').val()
                    })
                }
                var date =new Date('${action.planned_close_date}');
                $('.datepicker').datepicker({
                    dateFormat: 'dd-M-yy',
                    changeMonth: true,
                    changeYear: true
//                    minDate:date

                });

                $(".members_ActionPoint").autocomplete("ajaxsearch.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
               
               $(document).delegate('#action_point_'+rowId,"click",function() {
//                            var userText = $(this).closest('tr');
//                            var userText1=$(this).closest('tr').find('td input').val();
//                                alert(userText);
                               $('#check_box_'+rowId).attr("checked", true); 
                            });  
            }
            
            function remove_action_point(row) {
                var rowId = parseInt($('#actionCount').val()) - 1;
                $('#actionCount').val(rowId);
                $('#deletedTR4_'+row).val("1");
                $('#TR4_' + row).hide();
                return true;
            }
            //            function remove_action_point(rmv){
            //                var row = rmv.parentNode.parentNode.rowIndex;
            //                document.getElementById("momActionPointtable").deleteRow(row);
            //            }
            
            function updateMom(){
//                alert("uihgjkghijij");
               var jsonArr = [];
                var status = $('#mom_status_name').val();               
                if(status=='s'){  
//  var text=  $('.agendaText').val();
//                           var dis=$('.discussionText').val();
//                           console.log(dis);
//                             console.log(text);
//             //                if (specials.test(text)){
//                               var agenda= text.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                               var discussion=dis.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');   
//                                  $('.agendaText').val(agenda);
//                                  $('.discussionText').val(discussion);
//                        var i;
//                        var inc=0;
//                        var d;
//                        if($('.check_box:checked').length == 0){
//                            inc++;
////                            jsonArr = [];
//                            alert('Please select the record to continue');
//                            return false;
//                        }   
//        
//                        alert("error count"+inc);
//                        if(inc==0){
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
                    if(updateMomValidation()){
                            $('#formMomDetails').attr("action", "updateMomwithSave.htm");
                            document.formMomDetails.submit();
                            startLoading();
                         
                    }
                }
                else if(status=='dr'){ 
//                      var text=  $('.agendaText').val();
//                           var dis=$('.discussionText').val();
//                           console.log(dis);
//                             console.log(text);
//             //                if (specials.test(text)){
//                               var agenda= text.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                               var discussion=dis.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');   
//                                  $('.agendaText').val(agenda);
//                                  $('.discussionText').val(discussion);
//                    alert("status "+updateMomValidation());
                    if(updateMomValidation()){
//                        e.preventDefault();
//                        var i;
//                        var inc=0;
//                        var d;
//                        if($('.check_box:checked').length == 0){
//                            inc++;
////                            jsonArr = [];
//                            alert('Please select the record to continue');
//                            return false;
//                        }   
//        
//                        alert("error count"+inc);
//                        if(inc==0){
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
                            $('#formMomDetails').attr("action", "updateMomwithDraft.htm");
                            document.formMomDetails.submit();
                            startLoading();
                      
                    }
                }
                else {
//  var text=  $('.agendaText').val();
//                           var dis=$('.discussionText').val();
//                           console.log(dis);
//                             console.log(text);
//             //                if (specials.test(text)){
//                               var agenda= text.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');  
//                               var discussion=dis.replace(/[!&\/\\#()$~'"*<>{}|^]/g,' ');   
//                                  $('.agendaText').val(agenda);
//                                  $('.discussionText').val(discussion);
                       if(updateActionValidation()){
//                            e.preventDefault();
//                            var i;
//                            var inc=0;
////                            var d;
//                        if($('.action_point').val() !=null && !$('.action_point').val().equals("")){
//                            
//                        if($('.check_box:checked').length == 0){
//                            inc++;
////                            jsonArr = [];
//                            alert('Please select the record to continue');
//                            return false;
//                        }
//                    }
//                         $('.check_box:checked').each(function(id, value){
//                                i = $(value).attr('value');
//                         if($('.check_box:checked')){
//                                if ($('#datepicker_'+i).val().length == 0){
//                                               inc++;
//                                               jsonArr = [];
//                                               alert('please fill the data'); 
//                                               return false;
//                                           }
//                                else  if( $('#tracking_comments_'+i).val().length == 0 ){
//                                       inc++;
//                                       jsonArr = [];
//                                       alert('please fill the data'); 
//                                       return false;
//                                   }
//                        }
//                        });  
//                        alert("error count"+inc);
//                        if(inc==0){
                         
                            $('#formMomDetails').attr("action", "updateMom.htm");
                            document.formMomDetails.submit();
                            startLoading();
//                        }
                       }
                     }
                }
//            }
            
            
            function getValue() {
                var isChecked = document.getElementById("myCheckBox").checked;
                if(isChecked){ 
                    document.getElementById("text").style.display = "block";
                    document.getElementById("text").style.margin = "-20px 0 0 60px";
                    document.getElementById("status").value = "o";
                    document.getElementById("text").innerHTML = "Open";
                    document.getElementById("text_1").style.display = "none";
                
                } 
                //               else if(isChecked){
                //                   document.getElementById("status").value = "s";
                //               }
                else {
                    document.getElementById("text_1").style.display = "block";
                    document.getElementById("text_1").style.margin = "0px 0px -15px -50px";
                    document.getElementById("status").value = "c";
                    document.getElementById("text_1").innerHTML = "Closed";
                    document.getElementById("text").style.display = "none";
                }
                //               else {
                //                   document.getElementById("myCheckBox_value").value = ${list.mom_status};
                //               }
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
                
            function stopLoading() {
                //                var loaderStopHeight = $("html").height();
                //                loadingDivObj.height = loaderStopHeight + "px";
                if (ns4) {
                    loadingDivObj.visibility = "hidden";
                } else if (ns6 || ie4)
                    loadingDivObj.display = "none";
                //                $("#loadingImage").attr("tabindex", -1).focus();
            }

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
        </script>
        <script>
            $('#checkall').click(function () {
                //        alert("checking");
                $('.check_box').attr("checked", this.checked);
                $('.check_box').click(function(){		 
                    if($(".check_box").length == $('.check_box:checked').length) {
                        //                   alert("single box");
                    } else {
                        $('#checkall').removeAttr("checked");
                    }		 
                });
            });  
        </script>
    </body>
</html>
