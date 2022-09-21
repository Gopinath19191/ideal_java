<%-- 
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html>
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Cache-Control"  content="no-cache"/>
        <meta http-equiv="Pragma" content="no-cache"/>
        <meta http-equiv="Expires" content="0"/>
        <title>Tickets</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />   
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/rrf.css" />        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demos.css" />              
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <style type="text/css">
            #loadingDiv img{ border: none; }
            #loadingDiv{filter: alpha(opacity = 80); ZOOM: 1}
            .feedbacks{
                margin-bottom: 50px;
            }
            .status{
                width:150px;
            }
        </style>
        
        <script type="text/javascript">
            $(document).ready(function() {
                $("#feedback_search").autocomplete("feedback_search_user.htm", {
                    minChars: 1,
                    matchContains: true
                });
            });

            function showFeedback(state, id, ref_no) {
                if (state == 'view') {
                    $('#feedbacksListPage').attr("action", "view_feedback.htm?id=" + id + "&ref_no=" + ref_no);
                } else {
                    $('#feedbacksListPage').attr("action", "add_response.htm?id=" + id + "&ref_no=" + ref_no);
                }
                document.feedbacksListPage.submit();
                startLoading();
            }
            
            function addFeedback() {
                $('#feedbacksListPage').attr("action", "addFeedback.htm");
                document.feedbacksListPage.submit();
                startLoading();
            }
            
            function loadForm(page) {
                $('#page').val(page);
                $('#feedbacksListPage').attr("action", "search_feedback_user.htm");
//                var pageElement = document.getElementById("page");
//                pageElement.value = page;
                document.feedbacksListPage.submit();
                startLoading();
            }
            
            function searchFeedback(pageNo) {
                $('.gobutton').css("cursor", "not-allowed");
                var refNo = $('#feedback_search').val();
                $('#feedbacksListPage').attr("action", "search_feedback_user.htm?referenceNo=" + refNo);
                var pageElement = document.getElementById("page");
                pageElement.value = pageNo;
                document.feedbacksListPage.submit();
                startLoading();
            }
            
            function getStatusList() {

                var issueId = document.getElementById("issue_type").value;
                $.ajax({
                    url: "getSelectedStatus.htm",
                    data: "issueId=" + issueId,
                    type: "post",
                    success: function(result)
                    {
                        jsonResult = jQuery.parseJSON(result);
                        var options = "";
                        options = options + "<option value=''>--All--</option>"
                        $.each(jsonResult, function(index, statusVar)
                        {
                            options = options + "<option value=" + statusVar.statusKey + ">" + statusVar.statusValue + "</option>";
                        });

                        $("#status").html(options);
                    }
                });
            }
        </script>
    
    </head>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:1400px;background-color:rgba(0,0,0,0.5);display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 275px; left: 525px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="main_container">
            <div class="center_content" style="padding-top: 60px;margin-bottom: 15px;">
                <div class="page_heading">
                    Ticketing System
                </div>
            </div>
            <div class="notice_page" style="width:980px;height:20px;margin:0px;float:none;">
                <img src="${pageContext.request.contextPath}/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon" style="float:left;margin-top:-5px;"/>
                <div style="padding-top:3px;float:left;">
                    <ul class="notice_page_ul">
                       <li>Please close the ticket once your issue has been resolved.</li>
                    </ul>
                </div>
            </div>
            <div class="tabletools">
                <div class="filterWrap">
                    <div class="filetrInnerWrap">
                        <form action="" name="feedbacksListPage" method="post" id="feedbacksListPage" onsubmit="javascript:getData()">

                            <%
                                String refNoString = (String) request.getAttribute("refNoString");
                                if (refNoString == null || "".equalsIgnoreCase(refNoString)) {
                                    refNoString = "";
                                }
                                String pageNo = (String) request.getAttribute("page");
                                if (pageNo == null || "".equalsIgnoreCase(pageNo)) {
                                    pageNo = "1";
                                }
                            %>
                            <input type="hidden" id="page" name="page" value="<%=pageNo%>" />
                            <table>
                                <tr>
                                    <td><label for="refNo" style="padding-top:4px;"><b>Reference Number :</b></label></td>
                                    <td><input type="text" id="feedback_search" name="feedback_search" class="textbox searchtext" style="width:120px;height:18px;" value="${filterData.ref_no}"/></td>
                                    <td><label for="status" style="width:50px;margin-left: 10px;padding-top:4px;"><b>Status :</b></label></td>
                                    <td>
                                        <%
                                            String selectedStatus = (String) request.getAttribute("selectedStatus");
                                            if (selectedStatus == null || selectedStatus.equalsIgnoreCase("")) {
                                                selectedStatus = "-99";
                                            }
                                        %>
                                        <select name="status" id="status" class="status">
                                            <option value="">-- Select Status --</option>
                                            <c:forEach items="${statusList}" var="statusVar" >
                                                <c:set var="selectedStatusValue" value=""></c:set>
                                                <c:if test="${filterData.status== statusVar.statusKey}">
                                                    <c:set var="selectedStatusValue" value="selected"></c:set>
                                                </c:if>
                                                <option value="${statusVar.statusKey}" ${selectedStatusValue}>${statusVar.statusValue}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><label for="recordCount" Style="margin-left: 10px;padding-top:4px;"><b>Total Record : ${totalRecords}</b></label></td>
                                    <td>
                                        <input type="button" name="view" value="Go" class="gobutton" onclick="javascript:searchFeedback(<%=pageNo%>);"/>
                                        <input type="button" name="addNewRequest" value="Add Ticket" class="addbutton" onclick="javascript:addFeedback();"/>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </div>
                </div>
            </div>
            <br>
            <div class="feedbacks index">
                <div id="datadisplay">
                    <table cellpadding="0" cellspacing="0">
                        <tr>
                            <th align="center">Reference</th>
                            <th align="center">Assigned Employee</th>
                            <th align="center" style="width:150px;word-break: break-all;">Subject</th>
                            <th align="center">Support Unit</th>
                            <th align="center">Domain</th>
                            <th align="center">Request Type</th>
                            <th align="center">Request Area</th>
                            <th align="center">Priority</th>
                            <th align="center">Created Date</th>
                            <th align="center">Replied Date</th>
                            <th align="center" style="width:60px;">Time Taken </th>
                            <th align="center">Status</th>
                            <th align="center">Actions</th>
                        </tr>
                        <c:if test="${fn:length(details)>0}">
                        <c:forEach items="${details}" var="item">
                            <tr>
                                <td><c:out value="${item.ref_no}" /></td>
                                <td><c:out value="${item.assignEngineerName}" /></td>
                                <td style="width:150px;word-break: break-all;"><c:out value="${item.subject}" /></td>
                                <td><c:out value="${item.supportName}" /></td>
                                <td><c:out value="${item.sub_Unit_Name}" /></td>
                                <td><c:out value="${item.issue_type}" /></td>
                                <td><c:out value="${item.application_area}" /></td>
                                <td><c:out value="${item.priority}" /></td>
                                <td><c:out value="${item.created_date}" /></td>
                                <td><c:out value="${item.replied_date}" /></td>
                                <td style="width:60px;"><c:out value="${item.time_taken}" /></td>
                                <td><c:out value="${item.status}" /></td>
                                <td class="actions">
                                    <c:choose>
                                        <c:when test="${item.status =='Closed'}">
                                            <a href="selectEmpByRef_user.htm?ref_no=<c:out value='${item.ref_no}'/>"><img src="${pageContext.request.contextPath}/images/document-view.png" alt="Provide Feedback" title="Provide Feedback" /></a>
                                            </c:when>
                                            <c:otherwise>
                                            <a href="selectEmpByRef_user.htm?ref_no=<c:out value='${item.ref_no}'/>"><img src="${pageContext.request.contextPath}/images/document-blue.png" /></a>
                                            </c:otherwise>
                                        </c:choose>
                                </td>
                            </tr>
                            <input type="hidden" name="empId" id="name" value="${empId}"/>
                            <input type="hidden" name="created_by" id="created_by" value="${created_by}"/>
                            <input type="hidden" name="UniqueReqId" id="name" value="${item.id}"/>
                            <input type="hidden" name="file" id="name" value="${item.attachment_file_path}"/>
                        </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(details)==0}">
                            <tr>
                                <td colspan="12" style="text-align: center;">
                                    No Data to Display
                                </td>
                            </tr>
                        </c:if>
                    </table>
                    <c:if test="${paging[0] > 1}">
                        <%@include file="/WEB-INF/jsp/paging.jsp" %>
                    </c:if>
                </div>

            </div>
        </div>

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
    </body>
</html>
