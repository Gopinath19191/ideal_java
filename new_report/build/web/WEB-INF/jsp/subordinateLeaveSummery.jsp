<%-- 
    Document   : subordinateLeaveSummery
    Created on : 12 Jun, 2015, 1:02:38 PM
    Author     : 16047
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="header.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Subordinate Leave Summery  Report</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
    </head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
    <style type="text/css">
        #loadingDiv img{ border: none; }
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
        .paging {
            margin:10px 0 10px 18px;
            font-size : 11px;
        }

        .paging a {
            border: 1px solid #BBCFEA;
            padding: 3px 5px;
            color : #4884B8;
            text-decoration: none;
            font-weight: bold;
        }

        a.active {
            border: 1px solid #A4A5A5;
            color: #5A5B5B;
            font-weight: bold;
        }

        a.inactive {
            border: 1px solid #DEE8F4;
            color : #A3C5DE;
            font-weight: bold;
        }
        .gobutton{
            border: 1px solid #BCCFEA;
            background: none repeat scroll 0 0 #316ca8;
            color: #FFFFFF;
            float: left;
            font-weight: bold;
            height: 32px;
            margin: 0px;
            width: 50px;
        }
        </style>
        <script type="text/javascript">
            
            function getExcelReport() {
                //alert("inside export");
                $('#subordinateLeaveSummery').attr("action", "subordinateLeaveSummeryExport.htm");
                if ($("#employee_search").val() == 'Search by Employee Number or First Name or Last name') {
                    document.getElementById("employee_id").value = "";
                }
                //document.subordinateLeaveSummery.submit();
            }
            function getFilterList() {
                //alert("inside Go");
                $('#subordinateLeaveSummery').attr("action", "subordinateLeaveSummeryReport.htm");
                if ($("#employee_search").val() == 'Search by Employee Number or First Name or Last name') {
                    document.getElementById("employee_id").value = "";
                }
                //document.subordinateLeaveSummery.submit();
                startLoading();
            }

            $(document).ready(function() {
                $("#fromDate").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'yy-mm-dd'});
                $("#toDate").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'yy-mm-dd'});
                $("#employee_search").autocomplete("employeesearch.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
                $("#employee_search").result(function(event, data, formatted) {
                    if (data) {
                        $("#employee_id").val(data[1]);
                    }
                });
                $('#flag').val("0");
            });
        </script>
        <body>
            <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
                <div id="container">
                    <div class="container_inner">
                        <div class="container_inner">
                            <div class="page_heading">
                                Subordinate Leave Summery Report
                            </div>
                        </div>

                    </div>
                    <div class="tabletools">
                        <form action="#" name="subordinateLeaveSummery" method="post" id="subordinateLeaveSummery">
                            <table id="searchForm">
                                <tbody>
                                <input type="hidden" id="page" name="page" value="${paging[0] > 1 ? paging[1]:'1'}" />
                                <input type="hidden" id="flag" name="flag"/>
                                <tr>
                                    <td>
                                        <b>Employee Name</b>
                                    </td>
                                    <td>
                                        <input type="text" id="employee_search" name="employee_search" style="width:350px;color: #666666;" value="${filterData.employee_id != '' || filterData.employee_id != null?filterData.employee_search:'Search by Employee Number or First Name or Last name'}" onfocus="if (this.value == 'Search by Employee Number or First Name or Last name')
                    this.value = '';" onblur="if (this.value == '')
                    this.value = 'Search by Employee Number or First Name or Last name';" />
                                    <input type="hidden" id="employee_id" name="employee_id" value ="${filterData.employee_id}"/>
                                </td>
                                <td>
                                    <b>From Date</b>
                                </td>
                                <td>
                                    <input type="text" id="fromDate" name="fromDate" style="width:110px;color: #666666;" value="${filterData.fromDate}" />
                                </td>
                                <td>
                                    <b>To Date</b>
                                </td>
                                <td>
                                    <input type="text" id="toDate" name="toDate" style="width:110px;color: #666666;" value="${filterData.toDate}" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <input name="submitbtn" id="submit" class="gobutton" align="middle" onclick="getFilterList()" type="submit" value="Go"/>
                                </td>
                                <td>
                                    <input name="export" class="exportbutton" align="middle" type="submit" style="margin-left: 20px;" onclick="getExcelReport()" value="Export"/>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div id="datadisplay">
                    <c:if test="${fn:length(leaveDetails)>0}">
                        <table style="font-size: 10px;">
                            <tbody>
                            <th>Employee Name</th>
                            <th>Leave Category</th>
                            <th>Date Applied</th>
                            <th>From Date</th>
                            <th>To Date</th>                           
                            <th>Length Days</th>
                            <th>Leave Status</th>
                            </tbody>
                            <tbody>
                                <% int i = 0;
                                    int s = 0;
                                %>
                                <c:forEach items="${leaveDetails}" var="item">
                                    <% s = i % 2;
                                    if (s == 0) {%>
                                    <tr class="row-odd">
                                        <% } else {%>
                                    <tr class="row-even">
                                        <%}%>
                                        <td>
                                            ${item.empName}
                                        </td>
                                        <td>
                                            ${item.leaveCategory}
                                        </td>
                                        <td>
                                            ${item.dateApplied}
                                        </td>
                                        <td>
                                            ${item.fromDate}
                                        </td>
                                        <td>
                                            ${item.toDate}
                                        </td>
                                        <td>
                                            ${item.lengthDays}
                                        </td>
                                        <td>
                                            ${item.leaveStatus}
                                        </td>
                                    </tr>
                                    <% i = i + 1;%>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${fn:length(leaveDetails)==0}">
                        <table>
                            <tr class="row-odd">
                                <td colspan="6" style="text-align: center;">
                                    No data to display
                                </td>
                            </tr>
                        </table>
                    </c:if>
                    <cn:if test="${paging[0] > 1}">
                        <%@include file="/WEB-INF/jsp/paging.jsp" %>
                    </cn:if>
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
            function loadForm(page) {
              
                $('#page').val(page);
                $('#flag').val("1");
                //alert("In side Load form " + $('#page').val());
                $('#subordinateLeaveSummery').attr("action", "subordinateLeaveSummeryReport.htm");
                   $("#submit").click();
                //document.subordinateLeaveSummery.submit();
            }
                </script>
            </div>

        </body>
    </html>
