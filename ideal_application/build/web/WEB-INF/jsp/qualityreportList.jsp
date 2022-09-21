<%-- 
    Document   : qualityreportList
    Created on : Mar 21, 2012, 5:23:17 PM
    Author     : 14578
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%@include file="header.jsp" %>
        <script type="text/javascript">
            $(document).ready(function() {
                $('#qualityRequestList').dataTable({
                    "bLengthChange": false,
                    "bFilter": false,
                    "sPaginationType": "full_numbers",
                    "iDisplayLength" : 20,
                    "aaSorting": [[ 4   , "asc" ],[ 3   , "desc" ]]
                } );
            
            } );
           
        </script>
        <style type="text/css">
            tr.even td.sorting_1 {
                background-color: #EFF4FA;
            }
            tr.even  td.sorting_2 {
                background-color: #EFF4FA;
            }
            tr.odd  td.sorting_2 {
                background-color: #FFFFFF;
            }
        </style>
    </head>
    <body>
        <div id="container">
            <div class="goToList">
                <img alt="Report List" title="Report List" src="/iDeal_application/css/images/icon_list.png"/>
                <a class="" style="text-decoration:none;color: #4C83B3;" target="_self" href="qualityCustomerList.htm?list=report">Report List </a>
                <!--                <a class="qualExcel" style="text-decoration:none;" target="_self" href="getRequestExcel.htm">Export Excel </a>-->
            </div>
            <div class="page_heading" style="margin-top: 45px;">
                Quality Request Report List
            </div>
<!--            <div class="formContent_new" id="datadisplay" style="padding: 0px;margin: 2px 2px 2px 19px;">-->
            <div class="filterWrap">
                 <div class="filetrInnerWrap">
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <table style="width: 100%">
                        <tr>
                            
                            <td>Requestor Name :</label></td><td>
                                <input type="text" id="employee_search" name="employee_search" style="width:375px;color: #717171;" value="Search by Requestor Number or First Name or Last name" onfocus="if(this.value=='Search by Requestor Number or First Name or Last name') this.value='';" onblur="if(this.value=='') this.value='Search by Requestor Number or First Name or Last name';" >
                                <input type="hidden" id="employee_id" name="employee_id" />
                            </td>
                            
                            <td>FeedBack Type :</label></td><td>
                                <select id="feedbackType" name="feedbackType" class="required" style="width:175px;color: #717171;" >
                                    <option value="">-- Select Feedback-- </option>
                                    <c:forEach items="${feedbackType}" var="feedbackType" varStatus="i">
                                        <option value="${feedbackType.configKey}">${feedbackType.configValue}</option>
                                    </c:forEach>
                                </select>


                            </td>
<!--                               <td>
                                   <label for="requestor_id" >Reference Number</label>
                                   <input type="text" id="refId_search" name="refId_search" style="width:200px;color: #717171;" value="Search by Reference Number" onfocus="if(this.value=='Search by Reference Number') this.value='';" onblur="if(this.value=='') this.value='Search by Reference Number';" >
                                   <input type="hidden" id="ref_id" name="ref_id" />
                           </td>-->
                        </tr>
                        <!--                        <div class="buttonAlignment">
                                                    <div id="btnGroup" class="buttonAlignment" >
                                                        <input align="middle" class=" qualitysubmit"  type="submit" value="Search" style="width: 100px; " onclick="getFilterList()" /></td><td>
                                                            <input  align="middle" class=" qualitycancel" style="width: 100px;" type="button"  onclick="getExcelReport()" value="Excel Export"/>
                                                    </div>
                                                </div>-->
                    </table> 
                    <div class="buttonAlignment" style=" margin: 0 0 0;">
                        <div id="btnGroup" class="buttonAlignment" >
                            <input align="middle" class=" qualitysearch"  type="submit" value="Search" style="width: 100px; " onclick="getFilterList()" /></td><td>
                                <input  align="middle" class=" qualityexcelexport" style="width: 120px;" type="button"  onclick="getExcelReport()" value="Excel Export"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
            <div id="datadisplay" class="formContent_new" style="width: 100%;">
                <table id="qualityRequestList" cellspacing="0" cellpadding="0" width="100%">

                    <thead>
                    <th><a href="">Reference Number</a></th>
                    <th>Requestor Number</th>
                    <th>Requestor Name</th>
                    <th>Requested Date</th>
                    <th>Responded Date</th>
                    <th>FeedBack Type</th>
                    </thead>
                    <tbody>
                    <div style="background-color: white">
                        <script type="text/javascript">$('#datadisplay #qualityRequestList th').css({'background' : ''});</script>
                        <c:forEach items="${dashBoardList}" var="dashBoardList" varStatus="rpritr">
                            <c:if test="${rpritr.index%2 ==0}">
                                <c:set var="rowClass" value="even"></c:set>
                            </c:if>
                            <c:if test="${rpritr.index%2!=0}">
                                <c:set var="rowClass" value="odd"></c:set>  
                            </c:if>
                            <tr class="${rowClass}">
                                <td>${dashBoardList.referenceId}</a></td>
                                <td>${dashBoardList.employeeId}</td>
                                <td>${dashBoardList.employeeName}</td>
                                <td><fmt:formatDate value="${dashBoardList.reqDate}" var="reqDate" pattern="dd-MMM-yyyy" />${reqDate}</td>
                                <c:choose>
                                    <c:when test="${dashBoardList.qualClosedDate==null}">
                                        <td>---</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><fmt:formatDate value="${dashBoardList.qualClosedDate}" var="qualClosedDate" pattern="dd-MMM-yyyy" />${qualClosedDate}</td>
                                    </c:otherwise>
                                </c:choose>
                                <td>
                                    <c:forEach items="${feedbackType}" var="feedbackType" varStatus="i">
                                        <c:if test="${dashBoardList.feedbackType==feedbackType.configKey}">
                                            ${feedbackType.configValue}
                                        </c:if>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </div>
                    </tbody>
                </table>  
            </div>
        </div>
        <script type="text/javascript">
            $(document).ready(function(){
                $("#employee_search").autocomplete("getEmployeeList.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
                $("#employee_search").result(function(event, data, formatted) {
                    if (data) {
                        $("#employee_id").val(data[1]);
                    }
                });
                //                $("#refId_search").autocomplete("getReferenceId.htm", {
                //                    minChars: 1,
                //                    width: 350,
                //                    matchContains: true
                //                });
                //                $("#refId_search").result(function(event, data, formatted) {
                //                    if (data) {
                //                        alert(data)
                //                        $("#ref_id").val(data[1]);
                //                    }
                //                });

            });
            function getExcelReport(){
                //    alert("Excel Export");
                $('#reportPage').attr("action", "getRequestExcel.htm");
                document.reportPage.submit();
     
            }

            function getFilterList(){
                $('#reportPage').attr("action", "generateReport.htm");
                document.reportPage.submit();
            }
        </script> 
    </body>
</html>
