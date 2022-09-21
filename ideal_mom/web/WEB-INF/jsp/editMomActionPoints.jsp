<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%-- 
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Actions</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.ui.core.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.autocomplete.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.datepick.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.datepick.css" type="text/css">

        <style type="text/css">
           /*            #loadingDiv img{ border: none; }
            #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}*/
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
/*                margin: 0px 0px 30px 18px;*/
                outline: 1px none;
                padding-bottom: 0;
/*                width: 950px;*/
            }
/*            .dataTables_wrapper {
                position: relative;
                clear: both;
                zoom: 1;
            }*/
            table.display {
                margin: 0 auto;
                width: 100%;
            }
/*            .display {
                border-collapse: collapse;
                background: none;
            }*/
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
                margin: auto;
            }
            div#container {
                clear: both;
            }
            div {
                display: block;
            }
            
            body {
/*                background: #EEF2F8 url(images/bg_page.png) repeat-x top;*/
                font-family: Arial;
                font-size: 12px;
                    line-height: 15px;
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
                width: 35%;
                padding: -14px 0px 0px 0px;
                margin: 0px -6px 41px 2px;
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
/*                background: url(images/table-header-strip.jpg) repeat-x scroll center top #EFF4FB;*/
                border-bottom: 0 none;
                border-left: 1px solid #EFF4FB;
                font-weight: bolder;
                padding: 5px 10px;
                font-size:12px;
/*                color: #555555;*/
            }
            table.display thead th {
/*                padding: 3px 18px 3px 10px;
                border-bottom: 1px solid black;
                font-weight: bold;*/
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
        </style>
        <script type="text/javascript">
//            function loadForm(page) {
//                var temp=$(".actionpaging .active").text();
//                $('#page').val(page);
//                var temp3=$("#mom_action_status :selected").val();
//                $('#mom_action_status_temp').val(temp3);
//                $('#page_action').val(temp);
//                var temp4=$("#test_action").val();
//                $('#for_paging').val("from_mom");
//                $('#mom_flag').val(temp4);
//                $('#momListPage').attr("action", "minutesOfMeeting.htm");
//                document.momListPage.submit();
//            }
//                            
            function loadForm1(page_action) {
                var temp=$(".mompaging .active").text();
                console.log(temp);
                var temp4=$("#test").val();
                $('#page_action_1').val(page_action);
                $('#action_flag').val(temp4);
                var temp3=$("#mom_status :selected").val();
                $('#mom_status_temp').val(temp3);
                $('#page_1').val(temp);
                $('#for_paging_1').val("from_action");
                $('#actionListPage').attr("action", "editMomActionPoints.htm");
                document.actionListPage.submit();
            }
             function historyBtn(rowid){
                console.log(rowid);
                //if($('#historypoint_'+rowid).val().length>0|| $('#historypointdate_'+rowid).val().length>0){
                $('#historypoint_'+rowid).slideToggle();
                $('#historypointdate_'+rowid).slideToggle();
                //            }
            }
           
        </script>
    </head>
    <body>   
         <div id="loadingDiv" style="position: absolute; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); display: none; top: 0pt; left: 0pt;">
            <div style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center;color: #000;">
                <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
            </div>
        </div>
        <div>
                   <p class="page_heading">
                           <span class="heading" style="padding-left: 2px;">List of Actions</span> 
                       </p></div>
        <div id="myAction" name="myAction" style="padding: 0px;margin-top: -25px;">
        <input type="hidden" name="id" id="mom_id"value="${filterData.id}">
        
            <div class="dataTables_filter" id="customerDataList_filter" style="padding-right: 0px;height: 50px;padding-left: 5px;border-left-width: 2px;border-top-width: 2px;border-bottom-width: 2px;width: 940px;margin-top: 0px;padding-top: 19px;margin-left: 0px;">
                <form name="actionListPage" id="actionListPage" method="POST">
                    <input type="hidden" id="page_1" name="page" value="1">
                        <input type="hidden" name="page_action" id="page_action_1" value="1">
                        <input type="hidden" name="for_paging" id="for_paging_1">
                        <input type="hidden" id="mom_id" name="id" value="${filterData.id}" />
                        <!--<input type="hidden" name="mom_status" id="mom_status_temp">-->
                        <input type="hidden" name="mom_title" id="mom_title_search_temp">
                        <input type="hidden" id="action_flag" name="mom_title">
                        <!--<input type="hidden" name="page" id="page" value="1">-->
                        <table>
                            <tbody>
                                <tr>
                                    <td>
                                        <label   style="width: 76px;line-height: 25px;color:#666666;"><b style="font-size: 13px;">Mom Title: </b></label>
                                    </td> 
                                    <td>
                                        <input type="text" id="test" class="mom_title_auto_search textbox_new" name="action_mom_title" value="${mom_title_Actionsearch}"  placeholder=" Search by Mom  Title" style="margin-right: 49px;"/>
                                    </td>                              
                                    <td>
                                         <label style="color:#666666;" width="10%"><b style="     padding-left: 20px; font-size: 13px;">Select Status :</b></label> 
                                    </td>
                                    <td>
                                        <select name = "mom_action_status" id ="mom_action_status" style="margin-left: 0px;height: 21px;margin-right: 37px;"  onchange="filterList()">
                                             <option style="text-align:center;" value="">--All Status--</option>
                                         <c:forEach items="${action_statusData}" var = "statusData">
                                              <option value="${statusData.configuration_key}" ${selectedActionStatus==statusData.configuration_key ? 'selected':' '}>${statusData.action_point_status}</option>
                                         </c:forEach>
                                         </select> 
                                     </td>

                                      <td>
                                         <!--<input type="button" style="padding-left: 20px;margin-left: 0px;margin-right: 20px;" class="gobutton" id="goSearch" name="search" value=" Go " onclick="javascript:filterList()">-->
                                      </td>
                                     <td>
                                         <input type="button" id="exportActionList" class="exportbutton" name="excelExport" id="excelExport" onclick="getActionExcelReport();" value="Export">
                                     </td>
                                 </tr>
                       </tbody>         
             </table>
          </form>
     </div>
     <form id="editForm" name="editForm" method="POST" action="#"> 
         <div class="backview" style="width:937px;">
        <table id="datadisplay" class="display">
            <thead>
              <tr>
                  <th> <input type="checkbox" name="checkall" id="checkall" ></th>
                <th width="75px" class="sorting" rowspan="1" colspan="1" style="width: 350px;">
                    Mom Title</th>
                <th width="275px" class="sorting_asc" rowspan="1" colspan="1" style="width: 91px;">
                    Action points</th>
                <th width="75px" class="sorting" rowspan="1" colspan="1" style="width: 92px;">
                    Planned Closure Date</th>
                <th width="50px" class="sorting" rowspan="1" colspan="1" style="width: 63px;">
                    Actual / Forecast Closure Date </th>
                <th width="50px" class="sorting" rowspan="1" colspan="1" style="width: 63px;">
                    Tracking comments </th>
                <th width="50px" class="sorting" rowspan="1" colspan="1" style="width: 63px;">
                    Status </th>
                <th width="50px" class="sorting" rowspan="1" colspan="1" style="width: 63px;">
                    History </th>
              </tr>
            </thead>
             <tbody style="font-family: arial;"class="action">
                 
                <input type="hidden" name="mom_view" id="mom_view" value="" >

                <c:set var="Count" value="${0}" />
                <c:set var="flag" value="${0}" />
                <c:forEach items="${mom_list}"var="momObj">
                    <input type="hidden" name="updated_by" value="${momObj.updated_by}" id="updated_by_${momObj.action_point_id}">
                    <input type="hidden" name="mom_edit_first" id ="mom_edit_first_${momObj.action_point_id}" value="${momObj.mom_id}">
                    
                    <c:choose>
                        <c:when test="${momObj.action_point_status=='o' || momObj.action_point_status =='p'}">
                              <c:set var="flag" value="${flag+1}" /> 
                              <c:set var="Count" value="${Count+1}" /> 
                              <c:choose>
                                  <c:when test="${Count % 2 == 1}">
                                    <tr class="odd" style="font-size: 12px;">
                                  </c:when>
                                  <c:otherwise>
                                      <tr class="even" style="font-size: 12px;"> 
                                  </c:otherwise>  
                              </c:choose>
                                    <td align="center" >
                                        <input type="checkbox" name="check_box" id="check_box_action_point_id_${momObj.action_point_id}"  class="check_box" value="${momObj.action_point_id}"> 
                                    </td>
                                    <td>
                                        <a title="View" target="_self" href="viewActionMomDetails.htm?mom_view=${momObj.id}" style="color:#316CA8;"><c:out value="${momObj.mom_title}"/></a>
                                    </td>
                                      <!--<div></div>-->
                                    <input type="hidden" id="previous_planned_close_date_${momObj.action_point_id}" name="previous_planned_close_date" value="${momObj.actual_close_date}">
                                    <input type="hidden" id="remarks_${momObj.action_point_id}" name="remarks" value="${momObj.tracking_comments}">
                                    <input type="hidden" id="actionstatus_${momObj.action_point_id}" name="mom_action_status" value="${momObj.action_point_status}"> 
                                    <td>
                                        <span style="float:left;">${momObj.action_point}</span>
                                    </td>
                                    <input type="hidden" name="actionid" id ="mom_action_point_id" value="${momObj.action_point_id}"> 
                                    <td>
                                        <span id="planned_close_date" style="float:left;">${momObj.planned_close_date}</span></td>
                                         <td align="center">
                                                    <div id="historypointdate_${flag}" style="display:none;color:#4d85b8;">
                                                        <c:if test="${fn:length(history)>0}">
                                                            <c:forEach items="${history}" var = "his" >
                                                                <c:if test="${his.action_id == momObj.action_point_id}">
                                                                    ${his.previous_planned_close_date}<br>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>   
                                                    <input type="text" name="actualclosedate" class="datepicker" id="datepicker_${momObj.action_point_id}">
                                            <input type="hidden" name="action_point_employee_id" class="action_point_employee_id" id="action_point_employee_id_${momObj.action_point_id}" value="${momObj.employee_id}">
                                                </td>
                                                <td align="center">
                                                    <div id="historypoint_${flag}" style="display:none;color:#4d85b8;">  
                                                        <c:if test="${fn:length(history)>0}">
                                                            <c:forEach items="${history}" var = "his">
                                                                <c:if test="${his.action_id == momObj.action_point_id}">
                                                                    ${his.tracking_comments}<br>
                                                                </c:if>
                                                            </c:forEach>
                                                        </c:if>
                                                    </div>
                                                     <input type="text"  name="trackingcomments" class="trackingcomments"  id="tracking_comments_${momObj.action_point_id}">  
                                                </td>
                                     
                                         <td>
                                            <select name = "actionstatus" id="actionStatus_${momObj.action_point_id}">
                                                
                                                <c:forEach items="${action_statusData}" var = "action_statusData">
                                                    <option value="${action_statusData.configuration_key}" ${momObj.action_point_status==action_statusData.configuration_key ? 'selected':''}>${action_statusData.action_point_status}</option>
                                                </c:forEach>
                                            </select> 
                                         </td>   
                                          <td align="center">
<!--                                            <a title="View" target="_self" style="text-decoration:none;padding-left: 16px;"href="viewMomList.htm?mom_edit=${momObj.id}">
                                            <img src="/ideal_mom/css/images/view-icon.png" style="padding-left: 2px;"/></a> -->
                                            <img src="${pageContext.request.contextPath}/css/images/history.png" alt="Edit" title="History" style="cursor: pointer; margin-left: 6px;" onclick="historyBtn(${flag});">
                                           </td> 
                                        <input type="hidden" name="created_by" id="created_by" value="${created_by}"/>
                                    <script>
                                        
                                        var date =new Date('${momObj.planned_close_date}');
                                          $('#datepicker_${momObj.action_point_id}').datepicker({
                                           dateFormat: 'dd-M-yy',
                                           changeMonth: true,
                                           changeYear: true,
                                           minDate:date
                                          
                                        });
                                      
                                    </script>
                                  
                    </c:when>
                           <c:otherwise>
                                <c:set var="flag" value="${flag+1}" /> 
                                 <c:set var="Count" value="${Count+1}" /> 
                                 <c:choose>
                                   <c:when test="${Count % 2 == 1}">
                                       <tr class="odd">
                                   </c:when>
                                   <c:otherwise>
                                       <tr class="even"> 
                                   </c:otherwise>  
                                 </c:choose>
                                    <td></td>
                                     <td>
                                         <a title="View" target="_self" href="viewActionMomDetails.htm?mom_view=${momObj.id}"><c:out value="${momObj.mom_title}"/></a>
                                     </td>
                                  
                                            <input type="hidden" id="previous_planned_close_date_${momObj.action_point_id}" name="previous_planned_close_date" value="${momObj.actual_close_date}">
                                    <input type="hidden" id="remarks_${momObj.action_point_id}" name="remarks" value="${momObj.tracking_comments}">
                                    <input type="hidden" id="actionstatus_${momObj.action_point_id}" name="mom_action_status" value="${momObj.action_point_status}">  
                                            <td align="center">
                                                <span class="">${momObj.action_point}</span>
                                            </td>
                                                 <input type="hidden" name="mom_action_point_id" id ="mom_action_point_id" value="${momObj.action_point_id}"> 
                                            <td align="center">
                                                 <span id="planned_close_date">${momObj.planned_close_date}</span>  </td>
                                            <td>
                                                <div id="historypointdate_${flag}" style="display:none;">  
                                                    <c:if test="${fn:length(history)>0}">
                                                        <c:forEach items="${history}" var = "his" >
                                                            <c:if test="${his.action_id == momObj.action_point_id}">
                                                                ${his.previous_planned_close_date}<br>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                                    <span>${momObj.actual_close_date}</span>
                                            </td>
                                            <td>
                                                <div id="historypoint_${flag}" style="display:none;">  
                                                    <c:if test="${fn:length(history)>0}">
                                                        <c:forEach items="${history}" var = "his">
                                                            <c:if test="${his.action_id == momObj.action_point_id}">
                                                                ${his.tracking_comments}<br>
                                                            </c:if>
                                                        </c:forEach>
                                                    </c:if>
                                                </div>
                                                <span>${momObj.tracking_comments}</span>
                                            </td>
                                            <td align="center">
                                                  <span>  ${momObj.ap_status_name}</span>
                                            </td>
                                           <td align="center">
<!--                                                <a title="View" target="_self" style="text-decoration:none;padding-left: 20px;"href="viewMomList.htm?mom_edit=${momObj.id}">
                                                <img src="/ideal_mom/css/images/view-icon.png" style="padding-left: 2px;"/></a> -->
                                                 <img src="${pageContext.request.contextPath}/css/images/history.png" alt="Edit" title="History" style="cursor: pointer; margin-left: 6px;" onclick="historyBtn(${flag});">
                                            </td>
                                            <input type="hidden" name="created_by" id="created_by" value="${created_by}"/>
                                    
                             </tr>
                        </c:otherwise>
                   </c:choose>
                             <input id="planned_date_${momObj.action_point_id}" class="planneddate" value="${momObj.planned_close_date}" type="hidden">
                        
              </c:forEach>
                        <input type="hidden" id="count_row" value="${Count}">
                         <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
            </tbody>
     </table>
 
                        <c:if test="${Count==0}">
                            <p style="text-align:center;border-bottom: 2px;border-bottom:1px solid rgba(213, 225, 241, 0.55);">No records to found</p>
                            </c:if>
                <c:if test="${paging_actions[0] > 1}">
                        <%@include file="/WEB-INF/jsp/paging_1.jsp" %>
                </c:if></div>
                    <c:choose>
                        <c:when test="${selectedActionStatus=='o' || selectedActionStatus =='p'}">
                            <div class="buttonAlignment">
                                 <div class="buttonAlignment" id="btnGroup">
                                     <input type="hidden" name="parentId" id="parentId" value="546">
                                     <input type="hidden" name="employeeId" id="employeeId" value="">
                                     <input type="button" name="btnCancel" id="btnCancel" value="Back" style="cursor:pointer;" class="qualityback" onclick="javascript: location.href = 'getAllDetails.htm?page=1'">
                                     <input type="button" name="customerSubmitButton" id="submitBtn" value="Submit" style="width: 90px;cursor: pointer;" class="qualitysubmit" />
                                </div>
                            </div>
                         </c:when>
                    </c:choose>
             </form>
     </div>
     
    <script type="text/javascript">
       var jsonArr = [];
//             $( function() {
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
              
    
//                        $('#ListOfMom').click(function(){
//                            $('#exportActionList').hide();
//                             $('#exportMomList').show();
//                          
//                       });

             
//                        $('#ListOfActions').click(function(){
//                            $('#exportMomList').hide();
//                            $('#exportActionList').show();
//                        
//                            
//                        }); 
                   
//                        $('.action').click(function() {
//                            $('.historypoint').toggle("slide");
//                        });
                    
                        
                            
//                        $(".search_name").autocomplete("ajaxsearchMomName.htm", {
//                          minChars: 1,
//                          width: 350,
//                          matchContains: true
//                        });
//                        
//                        $(".search_name").result(function(data) {
//                            if (data) {
//                                $("#name_id").val(data[1]);
//                            }
//                        });
                 $(document).ready(function(){  
                        $(".mom_title_auto_search").autocomplete("actionajaxsearch.htm",{ 
                                 minChars: 1,
                                 width: 350,
                                 matchContains: true
                              }); 

                        $(".mom_title_auto_search").result(function(event, data, formatted) {
                                 if (data) {
                                       var temp=$('#test').val();
                                     $('#mom_title').val(temp);
                                     $("#mom_id").val(data[1]);
                                     $('#actionListPage').attr("action", "getActionListDetails.htm");
                                     document.actionListPage.submit(); 
                                   }
                                   });
                                   $(document).delegate('.datepicker',"change",function() {
//                            var userText = $(this).closest('tr');
                            var userText=$(this).closest('tr').find('td:first input').val();
//                                alert(userText);
                               $('#check_box_action_point_id_'+userText).attr("checked", true); 
                            });    
                           $(document).delegate('.trackingcomments',"keydown",function() {
                           var userText=$(this).closest('tr').find('td:first input').val();
//                                alert(userText);
                               $('#check_box_action_point_id_'+userText).attr("checked", true); 
                            });    
                            $(document).delegate('.actionstatus',"change",function() {
                           var userText=$(this).closest('tr').find('td:first input').val();
//                                alert(userText);
                               $('#check_box_action_point_id_'+userText).attr("checked", true); 
                            }); 
                        $(".qualitysubmit").click(function(e){
                            e.preventDefault();
                            var i;
                            var inc=0;
                            var d;
                            if($('.check_box:checked').length == 0){
                                inc++;
                                jsonArr = [];
                                alert('Please select the record to continue');
                                return false;
                            } 
                            $('.check_box:checked').each(function(id, value){
                                i = $(value).attr('value');
                                if($('.check_box:checked')){
                                    if ($('#datepicker_'+i).val().length == 0){
                                        inc++;
                                        jsonArr = [];
                                        alert('please fill the data'); 
                                        return false;
                                    }
                                    else  if ($('#datepicker_'+i).val().length > 0){
                                     $('#check_box_'+i).attr("checked", this.checked);
                                     }
//                                    else  if( $('#tracking_comments_'+i).val().length == 0 ){
                                    else  if( $('#tracking_comments_'+i).val().length == 0 ){
                                        inc++;
                                        jsonArr = [];
                                        alert('please fill the data'); 
                                        return false;
                                    }
                                }                               
                               if(inc==0){
                                    d=validateDetails(i);
                                    if(d==false){
                                        $('#selectedRows').val('');
                                        jsonArr = [];
                                        return false;
                                    }
                                    else{
                                        return true;
                                    }
                               }
                             });  
                                if(inc==0){
                                   $('#editForm').attr("action", "updateMomActionPoints.htm");
                                   document.editForm.submit(); 
                                   startLoading();
                                }
                              
                      });   
               });
//                else{
//                    var temp=0;
//                    var arr=[];
//                    $('.check_box:checked').each(function(id, value){
//                        i = $(value).attr('value');
//                        var actual_close_date=$("#datepicker_"+i).val();
//                        var tracking_comments=$("#tracking_comments_"+i).val();
//                        var status=$("#actionStatus_"+i).val();
//                        arr[temp++] =[actual_close_date,tracking_comments,status]
//                                            
//                    });
                    
                     
//                                $.ajax({
//                                data:{data:d},
//                                url:'updateActionPoints.htm',    
//                                type:"POST",
//                                success: function(mydata)
//                                {
//
//                                }
//
//                                });
                          
                 function validateDetails(i){
//                 alert(i);
                     var a=i;
                     var actual_close_date=$("#datepicker_"+a).val();
                     var tracking_comments=$("#tracking_comments_"+a).val();
                     var status=$("#actionStatus_"+a).val();
                     var actionid=a;
                     var previousdate=$("#previous_planned_close_date_"+a).val();
                     var remarks=$("#remarks_"+a).val();
                     var updated_by=$("#updated_by_"+a).val();
                     var employee_id=$("#action_point_employee_id_"+a).val();
                     jsonArr.push({
                         actual_close_date:actual_close_date,
                         tracking_comments:tracking_comments,
                         action_point_status:status,
                          action_point_id:actionid,
                          previous_planned_close_date:previousdate,
                          updated_by:updated_by,
                          remarks:remarks,
                          employee_id:employee_id
                     });
                     console.log(jsonArr);
                     $('#selectedRows').val(JSON.stringify(jsonArr));
                     return true;
                    }        
                    
    
//                   function searchMom() {
//                      var temp1=$(".actionpaging .active").text();
//                      var temp2=$(".mompaging .active").text();
//                      var temp3=$("#mom_action_status :selected").val();
//                      var temp4=$("#test_action").val();
//                      $('#page').val(temp2);
//                      $('#for_paging').val("from_mom");
//                      $('#mom_action_status_temp').val(temp3);
//                      $('#mom_flag').val(temp4);
//                      $('#page_action').val(temp1);
//                      $('#momListPage').attr("action", "getAllDetails.htm");
//                      document.momListPage.submit();
//                      startLoading();
//                  }
//style="pointer-events: none;"
                  function filterList() {
                      var temp1=$(".actionpaging .active").text();
                      var temp2=$(".mompaging .active").text();
                      var temp3=$("#mom_status option:selected").val();
                      var temp4=$("#test").val();
                      $('#page_1').val(temp2);                      
                      $('#for_paging_1').val("from_action");
                      $('#mom_status_temp').val(temp3);
                      $('#action_flag').val(temp4);
                      $('#page_action_1').val('');
                      $('#actionListPage').attr("action", "editMomActionPoints.htm");
                      document.actionListPage.submit();
                      startLoading();
                  }
//                   function getMomExcelReport(){
//                      $('#momListPage').attr("action", "getMomXL.htm");
//                      document.momListPage.submit();
//                      //                startLoading();
//                  }
                  function getActionExcelReport(){
                      $('#actionListPage').attr("action", "getActionMomXL.htm");
                      document.actionListPage.submit();
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







