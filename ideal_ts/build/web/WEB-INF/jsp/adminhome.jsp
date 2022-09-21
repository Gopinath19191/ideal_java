<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<!DOCTYPE html>
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
        <style>
            #loadingDiv img{ border: none; }
            #loadingDiv{filter: alpha(opacity = 80); ZOOM: 1}
            form label{
                padding:0px;
            }
        </style>
        
        <script type="text/javascript">
            
            $(document).ready(function() {
                $("#feedback_search").autocomplete("feedback_search.htm", {
                    minChars: 1,
                    matchContains: true
                });
                $("#employee_search").autocomplete("employee_search.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
            });
            
            function addFeedback() {
                $('#feedbacksListPage').attr("action", "add_feedback.htm");
                document.feedbacksListPage.submit();
                startLoading();
            }

            function loadForm(page) {                
                $('#page').val(page);
                $('#feedbacksListPage').attr("action", "showEmployees.htm");
                document.feedbacksListPage.submit();
                startLoading();
            }
            
            function searchFeedback(pageNo) {
                $('.gobutton').css("disabled",true);
                $('.gobutton').css("cursor","not-allowed");
                var refNo = $('#feedback_search').val();
                $('#feedbacksListPage').attr("action", "search_feedback_admin.htm?referenceNo=" + refNo);
                var pageElement = document.getElementById("page");
                pageElement.value = pageNo;
                document.feedbacksListPage.submit();
                startLoading();
            }
            
            function getExcelReport() {
                $('#feedbacksListPage').attr("action", "excelexportTicAdmin.htm");
                document.feedbacksListPage.submit();
            }
            
            function getFilterList() {
                $('#feedbacksListPage').attr("action", "showEmployees.htm");
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
                    options = options + "<option value=''>--Select--</option>"
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
            <div class="center_content" style="padding-top: 60px">
                <div class="page_heading">
                    Ticketing System
                </div>
            </div>        
            <div class="tabletools" style="padding: 16px;">
                <div class="filterWrap">
                    <div class="filetrInnerWrap">
                        <form action="showEmployees.htm" name="feedbacksListPage" method="post" id="feedbacksListPage" >

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
                            <table>
                                <input id="page" name="page" value="1" type="hidden">
                                <input type="hidden" name="referenceId" id="referenceId" value = "<%=refNoString%>"/>
                                <tr>
                                    <td><label><b>Reference Number</b></label></td>
                                    <td><input type="text" id="feedback_search" name="feedback_search" class="textbox searchtext" style="width:160px;" value="<%=refNoString%>"/></td>
                                    <td><label><b>Employee Name</b></label></td>
                                    <td>
                                        <input type="text" id="employee_search" name="employee_search" style="width:250px;color: #717171;" 
                                                value="${filterData.empName == '' || filterData.empName == null? 'Search by Employee Number or First/Last name' : filterData.empName}"  
                                                onblur="if (this.value == '')
                                                this.value = 'Search by Employee Number or First/Last name';" 
                                                onfocus="if (this.value == 'Search by Employee Number or First/Last name')
                                                this.value = '';"  >
                                        <input type="hidden" id="employee_id" name="employee_id" readonly />
                                    </td>
                                    <td style="padding-left:30px;"><label style="width:100px;"><b>Total Records : </b></label><c:out value="${totalRecords}"></c:out></td>
                                </tr>
                                <tr>
                                    <td><label><b>Assigned Employee</b></label></td>
                                    <td>
                                        <select name="assignEngineer" id="assignEngineerList" class="assignEngineerList">
                                            <option value="">--Select--</option>
                                            <c:forEach items="${assignEngineerList}" var="assignEngineerVar" >
                                                <c:set var="selectedEngineerValue" value=""></c:set>
                                                <c:if test="${filterData.engineerId == assignEngineerVar.assignEngineerId}">
                                                    <c:set var="selectedEngineerValue" value="selected"></c:set>
                                                </c:if>
                                                <option value="${assignEngineerVar.assignEngineerId}" ${selectedEngineerValue}>${assignEngineerVar.assignEngineerName}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td><label><b>Status</b></label></td>
                                    <td>
                                        <select name="status" id="status" class="status">
                                            <option value="">--Select--</option>
                                            <c:forEach items="${statusList}" var="statusVar" >
                                                <c:set var="selectedStatusValue" value=""></c:set>
                                                <c:if test="${filterData.status== statusVar.statusKey}">
                                                    <c:set var="selectedStatusValue" value="selected"></c:set>
                                                </c:if>
                                                <option value="${statusVar.statusKey}" ${selectedStatusValue}>${statusVar.statusValue}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="button" name="view" value="Go" class="gobutton"  onclick="javascript:searchFeedback(<%=pageNo%>);"/>
                                        <input type="button" name="view" value="Export" class="exportbutton"  onclick="javascript:getExcelReport();"/>
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
                            <th align="center">Employee</th>
                            <th align="center">Reference</th>
                            <th align="center">Assigned Employee</th>
                            <th align="center" style="width:150px;word-break: break-all;">Subject</th>
                            <th align="center">Support Unit</th>
                            <th align="center">Domain</th>
                            <th align="center">Request Area</th>
                            <th align="center">Priority</th>                            
                            <th align="center">Contact No</th>
                            <th align="center">Created Date</th>
                            <th align="center">Replied Date</th>
                            <th align="center" style="width:60px;">Time Taken</th>
                            <th align="center">Status</th>
                            <th align="center">Actions</th>
                        </tr>
                        <c:forEach items="${details}" var="item">
                            <tr>
                                <td><c:out value="${item.empNumber} - ${item.empName}" /></td>
                                <td><c:out value="${item.ref_no}" /></td>
                                <td><c:out value="${item.assignEngineerName}" /></td>
                                <td style="width:150px;word-break: break-all;"><c:out value="${item.subject}" /></td>
                                <td><c:out value="${item.supportName}" /></td>
                                <td><c:out value="${item.sub_Unit_Name}" /></td>
                                <td><c:out value="${item.application_area}" /></td>
                                <td><c:out value="${item.priority}" /></td>                                
                                <td><c:out value="${item.contact_no}" /></td>
                                <td><c:out value="${item.created_date}" /></td>
                                <td><c:out value="${item.replied_date}" /></td>
                                <td style="width:60px;"><c:out value="${item.time_taken}" /></td>
                                <td><c:out value="${item.status}"/></td>
                                <td class="actions">
                                    <c:choose>
                                        <c:when test="${item.status =='Closed'}">
                                            <a href="selectEmpByRef.htm?ref_no=<c:out value='${item.ref_no}'/>&empName=<c:out value='${item.empName}'/>" ><img src="${pageContext.request.contextPath}/images/document-view.png" alt="Provide Feedback" title="Provide Feedback" /></a>
                                            </c:when>
                                            <c:otherwise>
                                            <a href="selectEmpByRef.htm?ref_no=<c:out value='${item.ref_no}'/>&empName=<c:out value='${item.empFullName}'/>"><img src="${pageContext.request.contextPath}/images/document-blue.png" /></a>
                                            </c:otherwise>
                                        </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${fn:length(details)==0}">
                            <tr>
                                <td colspan="13" style="text-align:center;font-weight: bold;">
                                    No Data to display
                                </td>
                            </tr>
                        </c:if>
                    </table>
                    <cn:if test="${paging[0] > 1}">
                        <%@include file="/WEB-INF/jsp/paging.jsp" %>
                    </cn:if>

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
