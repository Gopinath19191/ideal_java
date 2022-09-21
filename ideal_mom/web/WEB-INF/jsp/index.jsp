<%-- 
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%--<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>--%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MoM List Page</title>
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
                width: 12px;
                margin: 0px 0px 0px 0px;
                position: relative;
                left: 72px;
                top: 1px;
            }
            #listName{
                font-weight: bold;
                font-size: 15px;
                float: right;
                margin: 4px 0px 0px -35px;
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
                margin: 0px -6px 26px 2px;
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
            #exportMomList {
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
            .search_name{
                width: 180px;
                height: 20px;
                padding-left: 20px;
                background: url(css/images/username_icon.png) no-repeat 2px 3px #FFF !important;
            }
            #actionlink{
               float: right;
               margin: 2px 64px;
            }
           
        </style>
        <script type="text/javascript">            
            function loadForm(page) {
                var temp=$(".actionpaging .active").text();
                $('#page').val(page);
//                var temp3=$("#mom_action_status :selected").val();
//                $('#mom_action_status_temp').val(temp3);
                $('#page_action').val(temp);
                var temp4=$("#test_action").val();
                $('#for_paging').val("from_mom");
                $('#mom_flag').val(temp4);
                var tem=$('#picker').val();
                $('#mom_date').val(tem);
                $('#momListPage').attr("action", "getAllDetails.htm");
                document.momListPage.submit();
            }
                            
//            function loadForm1(page_action) {
//                var temp=$(".mompaging .active").text();
//                console.log(temp);
//                var temp4=$("#test").val();
//                $('#page_action_1').val(page_action);
//                $('#action_flag').val(temp4);
//                var temp3=$("#mom_status :selected").val();
//                $('#mom_status_temp').val(temp3);
//                $('#page_1').val(temp);
//                $('#for_paging_1').val("from_action");
//                $('#actionListPage').attr("action", "getActionListDetails.htm");
//                document.actionListPage.submit();
//            }
        </script>
    </head>
    <body>
        <div id="myMom"style=" padding:0px; width:opx;">
            <div id="loadingDiv" style="position: absolute; width: 100%; height: 100%; background-color: rgb(0, 0, 0, 0.5); display: none; top: 0pt; left: 0pt;">
                <div style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center;color: #000;">
                    <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
                </div>
            </div>
                <div>
                   <p class="page_heading">
                           <span class="heading" style="padding-left: 2px;">List of MoM</span> 
                            
                       </p>
                       <a id="actionlink" href="editMomActionPoints.htm" style="text-decoration:none;color: #316CA8;font-size: 13px;">Click to Edit Actions</a>
                            
                </div>
                   <div class="tabletools" style="height: 50px; width: 940px; margin-top: -3px; margin-bottom: 0px;padding-bottom: 10px;border-bottom-width: 0px;padding-right: 0px;padding-left: 0px;">
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
                                   <input type="hidden" id="mom_date" name="mom_date" >
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
                                                        <b>MoM Date</b>
                                                    </label>
                                                </td>
                                                 <td>
                                                     <input type="text" id="picker" class="picker" name="momdate"  value="${mom_date}"  placeholder=" Search by Mom Date" onchange="datefilter()">
                                                  <!--<input type="text" id="test" class="mom_title_auto_search textbox_new" name="action_mom_title" value="${mom_date}"  placeholder=" Search by Mom Date" style="margin-right: 49px;"/>-->
                                                </td>
                                                <td>
                                                
                                                </td>
                                                <td>
                                                    <input type="button"  class="exportbutton" name="excelExport" id="exportMomList" id="excelExport" onclick="getMomExcelReport();" value="Export">
                                                    
                                                </td>
                                                <td>
                                                
                                                <p class="division_list">
                                                    <a class="add" id="listName" onclick="javascript: location.href='addMoM.htm'"style="color: #4C83B3;"><img src="${pageContext.request.contextPath}/css/images/add.png" title="Add" alt="Add" id="listIcon" style="margin-right: 80px;padding-left: 9px;margin-top: 0px;margin-left: 39px;width: 14px;height: 14px; ">Add MoM</a>
                                               </p></td>
                                            </tr>
                                    </tbody>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>                                  
                    <div class="formContenet_new" id="datadisplay" style="width: 986px;margin-bottom: 60px;padding: 6px;margin-top: 1px;border-left-width: 0px;border-right-width: 0px;width: 929px;">
                       <div id="momDataList_wrapper">
                        <table id="momDataList" class="display" >
                            <thead>
                                <tr>
                                    <th style="width: 200px;">MoM Name</th>
                                    <th style="width: 80px;">Created Date</th>
                                    <th style="width: 20px;">Open</th>
                                    <th style="width: 20px;">Closed</th>
                                    <th style="width: 20px;">In progress</th>
<!--                                    <th style="width: 20px;">Saved</th>-->
                                    <th style="width: 20px;">On Hold</th>
                                    <th style="width: 20px;">Deferred</th>
                                    <th style="width: 55px;">Action</th>
                                </tr>
                            </thead>
                        <input type="hidden" name="mom_edit_first" id ="mom_edit" value="${list.id}"/>
                        <input type="hidden" name="mom_action_point_status" id="actionStatus">
                        <c:set var="Count" value="${0}" />
                        <c:forEach items="${details}" var="item" varStatus="actionStatus">
                             
                             <c:set var="Count" value="${Count+1}" />
                             
                            <tr  style="font-size: 12px;" >
                              <td>
                                  <c:choose>
                                      <c:when test="${item.total_count !=0 || (item.mom_status =='s' || item.mom_status =='dr')}">
                                           <a title="Edit" href="editMoM.htm?mom_edit=${item.id}"><c:out value="${item.mom_title}"/></a>
                                      </c:when>
                                    <c:otherwise>
                                        <a title="View"style="color:#316CA8;" target="_self" href="viewMomDetails.htm?mom_view=${item.id}"><c:out value="${item.mom_title}"/></a>
                                    </c:otherwise>
                                           
                                  </c:choose>
                                  <%--<c:if test="${item.total_count !=0 || (item.mom_status =='s' || item.mom_status =='dr')}">--%>
                                        <!--<a title="Edit" href="editMoM.htm?mom_edit=${item.id}"><c:out value="${item.mom_title}"/></a>-->
                                        <!--<img src="${pageContext.request.contextPath}/css/images/edit-icon.png" alt="Edit" title="Edit" style="cursor: pointer;"></a>-->
                                  <%--</c:if>--%>
                                 <%--<c:if test="${item.total_count == 0 && (item.mom_status !='s' || item.mom_status !='dr')}">--%>       
<!--                                     <a title="View"style="color:#316CA8;" target="_self" href="viewMomDetails.htm?mom_view=${item.id}"><c:out value="${item.mom_title}"/></a>-->
                                <%--</c:if>--%>
                              </td>
                                <td><c:out value="${item.mom_date}" /></td>
                                <td><c:out value="${item.open_count}" /></td>
                                <td><c:out value="${item.closed_count}" /></td>
                                <td><c:out value="${item.progress_count}" /></td>
                                <!--<td><c:out value="${item.saved_count}" /></td>-->
                                <td><c:out value="${item.hold_count}" /></td>
                                <td><c:out value="${item.deferred_count}" /></td>
                                <!--<td><c:out value="${item.mom_status_name}" /></td>-->
                                <td>
                                    <a title="View" target="_self" style="text-decoration:none"href="viewMomDetails.htm?mom_view=${item.id}">
                                    <img src="${pageContext.request.contextPath}/css/images/view-icon.png" style="padding-left: 18px;margin-right: 20px;"/></a>
                                    <c:if test="${item.total_count !=0 ||(item.mom_status=='s' || item.mom_status=='dr') }">
                                        <a href="editMoM.htm?mom_edit=${item.id}" onclick="editMom('${item.id}');">
                                        <img src="${pageContext.request.contextPath}/css/images/edit-icon.png" alt="Edit" title="Edit" style="cursor: pointer;"></a>
                                    </c:if>
                                    <%--<c:if test="${(item.closed_count ==0) && }">--%>
                                         <!--<a href="editMoM.htm?mom_edit=${item.id}" onclick="editMom('${item.id}');">-->
                                        <!--<img src="${pageContext.request.contextPath}/css/images/edit-icon.png" alt="Edit" title="Edit" style="cursor: pointer;"></a>-->
                                    <%--</c:if>--%>
                                    
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
            <script type="text/javascript">
                
//                var jsonArr = [];
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
//
//             
//                        $('#ListOfActions').click(function(){
//                            $('#exportMomList').hide();
//                            $('#exportActionList').show();
//                        
//                            
//                        }); 
                   
//                        $('.action').click(function() {
//                            $('.historypoint').toggle("slide");
//                        });
                    
//                        $(".mom_title_auto_search").autocomplete("actionajaxsearch.htm",{ 
//                            minChars: 1,
//                            width: 350,
//                            matchContains: true
//                         }); 
//                   
//                        $(".mom_title_auto_search").result(function(event, data, formatted) {
//                            if (data) {
//                                $("#mom_id").val(data[1]);
//                            }
//                            });
                      $(document).ready(function(){        
                        $(".search_name").autocomplete("ajaxsearchMomName.htm", {
                          minChars: 1,
                          width: 350,
                          matchContains: true
                        });
                        
                        $(".search_name").result(function(data) {
                            if (data) {
                                $("#name_id").val(data[1]);
                                var temp=$('#test').val();
                                     $('#mom_title').val(temp);
//                                     $("#mom_id").val(data[1]);
                                     $('#momListPage').attr("action", "getAllDetails.htm");
                                     document.momListPage.submit(); 
                                   }
                         
                        });
                      
                    
                                                     
                        $('.picker').datepicker({
                         dateFormat: 'dd-M-yy',
                         changeMonth: true,
                         changeYear: true

                    });
                      });
                                              
//                        $(".qualitysubmit").click(function(e){
//                            e.preventDefault();
//                            var i;
//                            var inc=0;
//                            var d;
//                            if($('.check_box:checked').length == 0){
//                                inc++;
//                                alert('Please select checkbox to continue');
//                                return false;
//                            } 
//                            $('.check_box:checked').each(function(id, value){
//                                i = $(value).attr('value');
//                                if($('.check_box:checked')){
//                                
//                                    if ($('#datepicker_'+i).val().length == 0){
//                                        inc++;
//                                        alert('actual date should not be empty'); 
//                                        return false;
//                                    }
//
//                                    else  if( $('#tracking_comments_'+i).val().length == 0 ){
//                                        inc++;
//                                        alert('comments should not be empty'); 
//                                        return false;
//                                    }
//                                }
//                               if(inc==0){
//                                    d=validateDetails(i);
//                                    if(d==false){
//                                        $('#selectedRows').val('');
//                                        jsonArr = [];
//                                        return false;
//                                    }
//                                    $('#editForm').attr("action", "updateActionPoints.htm");
//                                    document.editForm.submit(); 
//                                    return true;
//                                }
//                        });
//                    });
//                         $("#checkAll").click(function () {
//
//                                $('.check_box').attr('checked', this.checked); 
//
//
//                                $(".check_box").click(function(){		 
//                                    if($(".check_box").length == $(".check_box:checked").length) {
//                                        alert('single box');
//                                    } else {
//                                        $("#checkAll").removeAttr("checked");
//                                    }		 
//                                });
//                            });
//                       
//               });
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
                          
//                 function validateDetails(i){
//                     var a=i;
//                     var actual_close_date=$("#datepicker_"+a).val();
//                     var tracking_comments=$("#tracking_comments_"+a).val();
//                     var status=$("#actionStatus_"+a).val();
//                     var actionid=a;
//                     var previousdate=$("#previous_planned_close_date_"+a).val();
//                     var remarks=$("#remarks_"+a).val();
//                     jsonArr.push({
//                         actual_close_date:actual_close_date,
//                         tracking_comments:tracking_comments,
//                         action_point_status:status,
//                          action_point_id:actionid,
//                          previous_planned_close_date:previousdate,
//                          remarks:remarks
//                     });
//                     console.log(jsonArr);
//                     $('#selectedRows').val(JSON.stringify(jsonArr));
//                     return true;
//                    }        
//                    
    
                   function searchMom() {
                      var temp1=$(".actionpaging .active").text();
                      var temp2=$(".mompaging .active").text();
//                      var temp3=$("#mom_action_status :selected").val();
                      var temp4=$("#test_action").val();
                      $('#page').val(temp2);
                      $('#for_paging').val("from_mom");
//                      $('#mom_action_status_temp').val(temp3);
                      $('#mom_flag').val(temp4);
                      $('#page_action').val(temp1);
                      $('#momListPage').attr("action", "getAllDetails.htm");
                      document.momListPage.submit();
                      startLoading();
                  }
                  function datefilter(){
                      var temp=$('#picker').val();
                      $('#mom_date').val(temp);
                      $('#momListPage').attr("action", "getAllDetails.htm");
                      document.momListPage.submit(); 
                        startLoading();
                  }
//                  function filterList() {
//                      var temp1=$(".actionpaging .active").text();
//                      var temp2=$(".mompaging .active").text();
//                      var temp3=$("#mom_status option:selected").val();
//                      var temp4=$("#test").val();
//                      $('#page_1').val(temp2);
//                      $('#for_paging_1').val("from_action");
//                      $('#mom_status_temp').val(temp3);
//                      $('#action_flag').val(temp4);
//                      $('#page_action_1').val(temp1);
//                      $('#actionListPage').attr("action", "getActionListDetails.htm");
//                      document.actionListPage.submit();
//                      startLoading();
//                  }
                   function getMomExcelReport(){
                      $('#momListPage').attr("action", "getMomXL.htm");
                      document.momListPage.submit();
                      //                startLoading();
                  }
//                  function getActionExcelReport(){
//                      $('#actionListPage').attr("action", "getActionXL.htm");
//                      document.actionListPage.submit();
//                  } 


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
         <!--</form>-->
    </body>
</html>
