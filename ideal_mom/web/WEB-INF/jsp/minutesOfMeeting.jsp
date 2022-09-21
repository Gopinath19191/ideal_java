<%-- 
    Document   : minutesOfMeeting
    Created on : 28 Feb, 2018, 3:06:38 PM
    Author     : 8000458
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>minutes of meeting</title>
       <link rel="stylesheet" href="${pageContext.request.contextPath}/css/tabjQuery.css" type="text/css">
       <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>   
        <!--<script src="http://code.jquery.com/jquery-1.9.1.js"></script>-->
        <!--<script src="http://code.jquery.com/jquery-migrate-1.1.1.js"></script>-->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
        <!--<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>-->
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.autocomplete.min.js"></script>
        <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
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
                padding: 25px 0px 0px 0px;
                margin: 0px 0px 0px 25px;
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
            #exportMomList,#exportActionList {
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
            .search_name,.mom_title_auto_search{
                width: 180px;
                height: 20px;
                padding-left: 20px;
                background: url(css/images/username_icon.png) no-repeat 2px 3px #FFF !important;
            }
        </style>
        
         <script type="text/javascript">
//            function loadForm(page) {
//               $('#page').val(page);
//               $('.actionListPage').attr("action", "minutesOfMeeting.htm");
//               
//               document.actionListPage.submit();
//           }
//            function loadForm(mompage) {
//               $('#mompage').val(mompage);
//               $('.momListPage').attr("action", "minutesOfMeeting.htm");
//               document.momListPage.submit();
//           }
            function loadForm(page) {
                var temp=$(".actionpaging .active").text();
                $('#page').val(page);
                var temp3=$("#mom_action_status :selected").val();
                $('#mom_action_status_temp').val(temp3);
                $('#page_action').val(temp);
                var temp4=$("#test_action").val();
                $('#for_paging').val("from_mom");
                $('#mom_flag').val(temp4);
                $('#momListPage').attr("action", "minutesOfMeeting.htm");
                document.momListPage.submit();
            }
                            
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
                $('#actionListPage').attr("action", "minutesOfMeeting.htm");
                document.actionListPage.submit();
            }
        </script>
    </head>
    
    <body>
        
        <div class="tabs">
           
        <ul class="tabactions">
            <li><a href="#myMom"id="ListOfMom" class="ListOfMom"><b>My Mom</b></a></li>
            <li><a href="#myAction"id="ListOfActions" class="ListOfActions"><b>My Action</b></a></li>
        </ul>
        
      <div id="myMom"style=" padding:0px; width:opx;">
            <div id="loadingDiv" style="position: absolute; width: 100%; height: 100%; background-color: rgb(0, 0, 0, 0.5); display: none; top: 0pt; left: 0pt;">
                <div style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center;color: #000;">
                    <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
                </div>
            </div>
                   <div class="tabletools" style="height: 50px;margin-top: 1px;margin-bottom: 0px;padding-bottom: 10px;border-bottom-width: 0px;padding-right: 0px;padding-left: 0px;">
                        <div class="filterWrap">
                           <div class="filetrInnerWrap">
                              <form action="#" name="momListPage" method="post" id="momListPage">
                                  <input type="hidden" id="page" name="page" value="1">
                                    <input type="hidden" name="page_action" id="page_action" value="1">
                                    <input type="hidden" name="for_paging" id="for_paging">
                                    <input type="hidden" name="mom_action_status" id="mom_action_status_temp">
                                    <!--<input type="hidden" name="mom_title" id="mom_title_ActionSearch_temp">-->
                                    <input type="hidden" id="name_id" name="id" value="${filterData.id}" />
                                    <input type="hidden" id="mom_flag" name="action_mom_title">
                                 <input type="hidden" id="mompage" name="mompage" value="1">
                                    <input type="hidden" id="name_id" name="id" value="${filterData.id}" />
                                       <table style="margin-top: 10px;" id="filter_records">
                                          <tbody>
                                             <tr>
                                                <td><label style="width: 105px; margin-left: 70px;font-size: 13px;">
                                                        <b>Search By Name</b>
                                                    </label>
                                                </td>
                                                <td><input type="text" id="test" class="search_name textbox_new" name="mom_title" value="${mom_title_search}" 
                                                           placeholder=" Search By Mom Name" style="margin-right: 49px;"/>
                                                </td>
                                                 <td>
                                                    <label  style="width: 0px;font-size: 13px;">
                                                        <b>Status Type</b>
                                                    </label>
                                                </td>
                                                 <td>
                                                    <div id="statusId">
                                                        <select name="mom_status" id="mom_status" class="statusType textbox_new" style="margin-right: 27px;margin-left: -5px;">
                                                          <option value="">--All Status--</option>                                                            
                                                            <c:forEach items="${statusData}" var = "momstatusData">
                                                                <option value="${momstatusData.configuration_key}" ${selectedStatus == momstatusData.configuration_key ? 'selected':''}>${momstatusData.mom_status}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                </td>
                                                <td>
                                                <input type="button" class="gobutton" id="goSearch" name="search" value=" Go " onclick="javascript:searchMom()">
                                                </td>
                                                <td>
                                                    <input type="button"  class="exportbutton" name="excelExport" id="exportMomList" id="excelExport" onclick="getMomExcelReport();" value="Export">
                                                    
                                                </td>
                                                <td>
                                                <img src="${pageContext.request.contextPath}/css/images/add.png" title="Add" alt="Add" id="listIcon" style="margin-right: 80px;padding-left: 9px;margin-top: 0px;margin-left: 39px;width: 14px;height: 14px; ">
                                                <p class="division_list">
                                                    <a class="add" id="listName" onclick="javascript: location.href='addMoM.htm'"style="color: #4C83B3;">Add MoM</a>
                                               </p></td>
                                            </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>                                  
                    <div class="formContenet_new" id="datadisplay" style="width: 986px;margin-bottom: 60px;padding: 5px;margin-top: 1px;border-left-width: 0px;border-right-width: 0px;">
                       <div id="momDataList_wrapper">
                        <table id="momDataList" class="display" >
                            <thead>
                                <tr>
                                    <th style="width: 200px;">MoM Name</th>
                                    <th style="width: 200px;">Created Date</th>
                                    <th style="width: 80px;">Status</th>
                                    <th style="width: 55px;">Action</th>
                                </tr>
                            </thead>
                        <input type="hidden" name="mom_edit" id ="mom_edit" value=""/>
                        <c:set var="Count" value="${0}" />
                        <c:forEach items="${details}" var="item">
                             <c:set var="Count" value="${Count+1}" /> 
                            <tr  style="font-size: 12px;" >
                                <td><a title="View" target="_self" href="viewMomDetails.htm?mom_view=${item.id}"><c:out value="${item.mom_title}"/></a></td>
                                <td><c:out value="${item.mom_date}" /></td>
                                <td><c:out value="${item.mom_status_name}" /></td>
                                <td>
                                    <a title="View" target="_self" style="text-decoration:none"href="viewMomDetails.htm?mom_view=${item.id}">
                                    <img src="${pageContext.request.contextPath}/css/images/view-icon.png" style="padding-left: 18px;"/></a>
                                    <c:if test="${item.mom_status=='o' || item.mom_status=='s'}">
                                        <a href="editMoM.htm?mom_edit=${item.id}" onclick="editMom('${item.id}');">
                                        <img src="${pageContext.request.contextPath}/css/images/edit-icon.png" alt="Edit" title="Edit" style="cursor: pointer; margin-left: 20px;"></a>
                                    </c:if>
                                    <input type="hidden" checked id="mom_status" name="mom_status" value="${list.mom_status}">
                                </td>
                                <input type="hidden" id="status" name="mom_status"  />    
                                <input type="hidden" name="mom_id" id="name" value="${mom_id}"/>
                                <input type="hidden" name="created_by" id="created_by" value="${created_by}"/>
                            </tr>
                        </c:forEach>
                            <input type="hidden" id="count_row" value="${Count}">
                    </table>
                            <c:if test="${Count==0}">
                            <p style="text-align:center;border-bottom: 2px;border-bottom:1px solid rgba(213, 225, 241, 0.55);">No records to found</p>
                            </c:if>
                            <c:if test="${paging[0] > 1}">
                            <%@include file="/WEB-INF/jsp/paging.jsp" %>
                        </c:if>
                </div>
          </div>
    </div>
    <div id="myAction" name="myAction"style="display:none;padding: 0px;">
        <input type="hidden" name="id" id="mom_id"value="${filterData.id}">
            <div class="dataTables_filter" id="customerDataList_filter" style="padding-right: 0px;height: 50px;padding-left: 5px;border-left-width: 0px;border-top-width: 2px;border-bottom-width: 2px;width: 989px;margin-top: 0px;padding-top: 19px;">
                <form action="#" name="actionListPage" id="actionListPage" method="POST">
                    <input type="hidden" id="page_1" name="page" value="1">
                        <input type="hidden" name="page_action" id="page_action_1" value="1">
                        <input type="hidden" name="for_paging" id="for_paging_1">
                        <input type="hidden" id="mom_id" name="id" value="${filterData.id}" />
                        <input type="hidden" name="mom_status" id="mom_status_temp">
                        <!--<input type="hidden" name="mom_title" id="mom_title_search_temp">-->
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
                                         <select name = "mom_action_status" id ="mom_action_status" style="margin-left: 0px;height: 21px;margin-right: 37px;" >

                                         <c:forEach items="${action_statusData}" var = "statusData">
                                              <option value="${statusData.configuration_key}" ${selectedActionStatus==statusData.configuration_key ? 'selected':' '}>${statusData.action_point_status}</option>
                                         </c:forEach>
                                         </select> 
                                     </td>

                                      <td>
                                         <input type="button" style="padding-left: 20px;margin-left: 0px;margin-right: 20px;" class="gobutton" id="goSearch" name="search" value=" Go " onclick="javascript:filterList()">
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
        <table id="datadisplay" class="display">
            <thead>
              <tr>
                <th> <input type="checkbox" name="checkall" id="checkAll"></th>
                <th width="75px" class="sorting" rowspan="1" colspan="1" style="width: 350px;">
                    Mom Title</th>
                <th width="275px" class="sorting_asc" rowspan="1" colspan="1" style="width: 91px;">
                    Action points</th>
                <th width="75px" class="sorting" rowspan="1" colspan="1" style="width: 92px;">
                    Planned Close Date</th>
                <th width="50px" class="sorting" rowspan="1" colspan="1" style="width: 63px;">
                    Actual close Date </th>
                <th width="50px" class="sorting" rowspan="1" colspan="1" style="width: 63px;">
                    Tracking comments </th>
                <th width="50px" class="sorting" rowspan="1" colspan="1" style="width: 63px;">
                    Status </th>
                <th width="50px" class="sorting" rowspan="1" colspan="1" style="width: 63px;">
                    View </th>
              </tr>
            </thead>
             <tbody style="font-family: arial;"class="action">
                <input type="hidden" name="mom_edit_first" id ="mom_edit_first" value=""> 
                <input type="hidden" name="mom_view" id="mom_view" value="" >

                <c:set var="Count" value="${0}" />
                <c:set var="flag" value="${0}" />
                <c:forEach items="${mom_list}"var="momObj">
                    <c:choose>
                        <c:when test="${momObj.action_point_status=='o' || momObj.action_point_status =='p'}">
                              <c:set var="flag" value="${flag+1}" /> 
                              <c:set var="Count" value="${Count+1}" /> 
                              <c:choose>
                                  <c:when test="${Count % 2 == 1}">
                                    <tr class="odd" style="font-size: 12px;" >
                                  </c:when>
                                  <c:otherwise>
                                      <tr class="even" style="font-size: 12px;" > 
                                  </c:otherwise>  
                              </c:choose>
                                    <td align="center" >
                                        <input type="checkbox" name="check_box" id="check_box"  class="check_box" value="${momObj.action_point_id}"> 
                                    </td>
                                    <td>
                                        <a title="View" target="_self" href="viewMomDetails.htm?mom_view=${momObj.id}"><c:out value="${momObj.mom_title}"/></a>
                                    </td>
                               <c:choose>
                                  <c:when test="${employee_id == momObj.employee_id}"> 
                                    <input type="hidden" id="previous_planned_close_date_${momObj.action_point_id}" name="previous_planned_close_date" value="${momObj.actual_close_date}">
                                    <input type="hidden" id="remarks_${momObj.action_point_id}" name="remarks" value="${momObj.tracking_comments}">
                                    <input type="hidden" id="actionstatus_${momObj.action_point_id}" name="action_status" value="${momObj.action_point_status}"> 
                                    <td align="center">
                                        <span style="float:left;">${momObj.action_point}</span>
                                    </td>
                                    <input type="hidden" name="actionid" id ="mom_action_point_id" value="${momObj.action_point_id}"> 
                                    <td align="center">
                                        <span id="planned_close_date" style="float:left;">${momObj.planned_close_date}</span>  </td>
                                     <td align="center">
<!--                                                         <span>  ${momObj.actual_close_date}</span>-->
                                     <input type="text" name="actualclosedate" class="datepicker" id="datepicker_${momObj.action_point_id}">
                                     <script>
                                        var date =new Date('${momObj.planned_close_date}');
                                          $('#datepicker_${momObj.action_point_id}').datepicker({
                                           dateFormat: 'dd-M-yy',
                                           changeMonth: true,
                                           changeYear: true,
                                           minDate:date
                                          
                                        });
                                    </script>
                                        </td>
                                        <td align="center">
<!--                                                    <span> ${momObj.tracking_comments}</span>-->
                                                 <input type="text"  name="trackingcomments" class="trackingcomments"  id="tracking_comments_${momObj.action_point_id}"> 
                                         </td>
                                         <td align="center">
                                            <select name = "actionstatus" id="actionStatus_${momObj.action_point_id}">
                                                <c:forEach items="${action_statusData}" var = "action_statusData">
                                                    <option value="${action_statusData.configuration_key}" ${momObj.action_point_status==action_statusData.configuration_key ? 'selected':''}>${action_statusData.action_point_status}</option>
                                                </c:forEach>
                                            </select> 
                                         </td>   
                                          <td> 
                                            <a title="View" target="_self" style="text-decoration:none;padding-left: 20px;"href="viewMomList.htm?mom_edit=${momObj.id}">
                                            <img src="/ideal_mom/css/images/view-icon.png" style="padding-left: 2px;"/></a> 
                                          </td>
                                        <input type="hidden" name="created_by" id="created_by" value="${created_by}"/>
                                    </c:when>
                                    <c:otherwise>
                                         <span>${momObj.actual_close_date}</span>
                                   </c:otherwise>
                              </c:choose>
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
                                         <a title="View" target="_self" href="viewMomDetails.htm?mom_view=${momObj.id}"><c:out value="${momObj.mom_title}"/></a>
                                     </td>
                                   <c:choose>
                                        <c:when test="${employee_id == momObj.employee_id}"> 
                                            <input type="hidden" id="previous_planned_close_date" name="mom_previous_planned_close_date" value="${momObj.actual_close_date}">
                                            <input type="hidden" id="mom_remarks" name="mom_remarks" value="${momObj.tracking_comments}">
                                            <input type="hidden" id="mom_action_status" name="mom_action_status" value="${momObj.action_point_status}"> 
                                            <td align="center">
                                                <span class="">${momObj.action_point}</span>
                                            </td>
                                                 <input type="hidden" name="mom_action_point_id" id ="mom_action_point_id" value="${momObj.action_point_id}"> 
                                            <td align="center">
                                                 <span id="planned_close_date">${momObj.planned_close_date}</span>  </td>
                                            <td align="center">
                                                   <span>  ${momObj.actual_close_date}</span>
                                            </td>
                                            <td align="center">
                                                <span> ${momObj.tracking_comments}</span>
                                            </td>
                                            <td align="center">
                                                  <span>  ${momObj.ap_status_name}</span>
                                            </td>
                                            <td>  
                                                <a title="View" target="_self" style="text-decoration:none;padding-left: 20px;"href="viewMomList.htm?mom_edit=${momObj.id}">
                                                <img src="/ideal_mom/css/images/view-icon.png" style="padding-left: 2px;"/></a> 
                                            </td>
                                            <input type="hidden" name="created_by" id="created_by" value="${created_by}"/>
                                      </c:when>
                                 </c:choose> 
                             </tr>
                        </c:otherwise>
                   </c:choose>
                             <input id="planned_date_${momObj.action_point_id}" class="planneddate" value="${momObj.planned_close_date}" type="hidden">
                            
              </c:forEach>
                        <input type="hidden" id="count_row" value="${Count}">
                         <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
            </tbody>
     </table>
</form> 
                        <c:if test="${Count==0}">
                            <p style="text-align:center;border-bottom: 2px;border-bottom:1px solid rgba(213, 225, 241, 0.55);">No records to found</p>
                            </c:if>
                <c:if test="${paging_actions[0] > 1}">
                        <%@include file="/WEB-INF/jsp/paging_1.jsp" %>
                </c:if>
                    <c:choose>
                        <c:when test="${selectedActionStatus=='o' || selectedActionStatus =='p'}">
                            <div class="buttonAlignment">
                                 <div class="buttonAlignment" id="btnGroup">
                                     <input type="hidden" name="parentId" id="parentId" value="546">
                                     <input type="hidden" name="employeeId" id="employeeId" value="">
                                     <!--<input type="button" name="btnCancel" id="btnCancel" value="Back" style="cursor:pointer;" class="qualityback" onclick="javascript: location.href = 'minutesOfMeeting.htm?page=1'">-->
                                     <input type="submit" name="customerSubmitButton" id="submitBtn" value="Submit" style="width: 90px;cursor: pointer;" class="qualitysubmit" />
                                </div>
                            </div>
                         </c:when>
                    </c:choose>
            </div>
      </div>
  <script type="text/javascript">
       var jsonArr = [];
             $( function() {
                      $( ".tabs" ).tabs();
                    });
              
                var lastTab = localStorage.getItem('lastTab');
                    if (!lastTab) {
                        lastTab = 0;
                    }
                $(".tabs").tabs({
                    activate: function(event, ui) {
                        //if another tab is activate, save it's index to the localStorage
                        localStorage.setItem('lastTab', ui.newTab.index());
                    },
                    //set the corresponding tab to active
                    active: lastTab
                });
               $(document).ready(function(){  
    
                        $('#ListOfMom').click(function(){
                            $('#exportActionList').hide();
                             $('#exportMomList').show();
                          
                       });

             
                        $('#ListOfActions').click(function(){
                            $('#exportMomList').hide();
                            $('#exportActionList').show();
                        
                            
                        }); 
                   
                        $('.action').click(function() {
                            $('.historypoint').toggle("slide");
                        });
                    
                        $(".mom_title_auto_search").autocomplete("actionajaxsearch.htm",{ 
                            minChars: 1,
                            width: 350,
                            matchContains: true
                         }); 
                   
                        $(".mom_title_auto_search").result(function(event, data, formatted) {
                            if (data) {
                                $("#mom_id").val(data[1]);
                            }
                            });
                            
                        $(".search_name").autocomplete("ajaxsearchMomName.htm", {
                          minChars: 1,
                          width: 350,
                          matchContains: true
                        });
                        
                        $(".search_name").result(function(data) {
                            if (data) {
                                $("#name_id").val(data[1]);
                            }
                        });

                        $(".qualitysubmit").click(function(e){
                            e.preventDefault();
                            var i;
                            var inc=0;
                            var d;
                            if($('.check_box:checked').length == 0){
                                inc++;
                                alert('Please select checkbox to continue');
                                return false;
                            } 
                            $('.check_box:checked').each(function(id, value){
                                i = $(value).attr('value');
                                if($('.check_box:checked')){
                                
                                    if ($('#datepicker_'+i).val().length == 0){
                                        inc++;
                                        alert('actual date should not be empty'); 
                                        return false;
                                    }

                                    else  if( $('#tracking_comments_'+i).val().length == 0 ){
                                        inc++;
                                        alert('comments should not be empty'); 
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
                                    $('#editForm').attr("action", "updateActionPoints.htm");
                                    document.editForm.submit(); 
                                    return true;
                                }
                        });
                    });
                         $("#checkAll").click(function () {

                                $('.check_box').attr('checked', this.checked); 


                                $(".check_box").click(function(){		 
                                    if($(".check_box").length == $(".check_box:checked").length) {
                                        alert('single box');
                                    } else {
                                        $("#checkAll").removeAttr("checked");
                                    }		 
                                });
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
                     var a=i;
                     var actual_close_date=$("#datepicker_"+a).val();
                     var tracking_comments=$("#tracking_comments_"+a).val();
                     var status=$("#actionStatus_"+a).val();
                     var actionid=a;
                     var previousdate=$("#previous_planned_close_date_"+a).val();
                     var remarks=$("#remarks_"+a).val();
                     jsonArr.push({
                         actual_close_date:actual_close_date,
                         tracking_comments:tracking_comments,
                         action_point_status:status,
                          action_point_id:actionid,
                          previous_planned_close_date:previousdate,
                          remarks:remarks
                     });
                     console.log(jsonArr);
                     $('#selectedRows').val(JSON.stringify(jsonArr));
                     return true;
                    }        
                    
    
                   function searchMom() {
                      var temp1=$(".actionpaging .active").text();
                      var temp2=$(".mompaging .active").text();
                      var temp3=$("#mom_action_status :selected").val();
                      var temp4=$("#test_action").val();
                      $('#page').val(temp2);
                      $('#for_paging').val("from_mom");
                      $('#mom_action_status_temp').val(temp3);
                      $('#mom_flag').val(temp4);
                      $('#page_action').val(temp1);
                      $('#momListPage').attr("action", "minutesOfMeeting.htm");
                      document.momListPage.submit();
                      startLoading();
                  }

                  function filterList() {
                      var temp1=$(".actionpaging .active").text();
                      var temp2=$(".mompaging .active").text();
                      var temp3=$("#mom_status option:selected").val();
                      var temp4=$("#test").val();
                      $('#page_1').val(temp2);
                      $('#for_paging_1').val("from_action");
                      $('#mom_status_temp').val(temp3);
                      $('#action_flag').val(temp4);
                      $('#page_action_1').val(temp1);
                      $('#actionListPage').attr("action", "minutesOfMeeting.htm");
                      document.actionListPage.submit();
                      startLoading();
                  }
                   function getMomExcelReport(){
                      $('#momListPage').attr("action", "getMomXL.htm");
                      document.momListPage.submit();
                      //                startLoading();
                  }
                  function getActionExcelReport(){
                      $('#actionListPage').attr("action", "getActionXL.htm");
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

               
                
            
 
