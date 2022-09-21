<%-- 
    Document   : qualityCustomerList
    Created on : Mar 16, 2012, 3:59:29 PM
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
                    "sPaginationType": "full_numbers",
                    "iDisplayLength" : 20,
                    "aaSorting": [[ 4   , "asc" ],[ 3   , "desc" ]]
                } );
            
            } );
            function getIndividualRequestDetails(refId,actionMethod)
            {
                document.getIndividulaRequestDetail.refId.value=refId;
                document.getIndividulaRequestDetail.action=actionMethod;
                document.getIndividulaRequestDetail.submit();
            }
        </script>
    </head>
    <style type="text/css">
        tr.even td.sorting_1 {
            background-color: #EFF4FA;
        }
        tr.odd  td.sorting_2 {
            background-color: #ffffff;
        }
        tr.even  td.sorting_2 {
            background-color: #EFF4FA;
        }
    </style>
    <body>
        <div id="container">
            <div class="goToList">
                <c:if test="${moduleId==commonConfigModuleId}"> 
                    <img alt="Pending List" title="Pending List" src="/iDeal_application/css/images/icon_list.png"></img>
                    <a class="" style="text-decoration:none;color: #4C83B3;" target="_self" href="qualityCustomerList.htm?list=pending">Pending List</a> 
                    <img alt="Processed List" title="Processed List" src="/iDeal_application/css/images/icon_list.png"/>
                    <a class="" style="text-decoration:none;color: #4C83B3;" target="_self" href="qualityCustomerList.htm?list=processed">Processed List</a>  
                    </c:if>
                <c:if test="${moduleId!=commonConfigModuleId}">
                    <a class="customeradd" title="Add Request" style="text-decoration:none;" target="_self" href="newRequest.htm">New Request</a>
                    <img alt="Requestor List" title="Requestor List" src="/iDeal_application/css/images/icon_list.png"/>
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="qualityCustomerList.htm?list=requestor">Requestor List</a>
                </c:if>
            </div>
            <div class="container_inner" style="margin: 45px 0 0;">
                <div class="page_heading">
                    <c:if test="${moduleId==commonConfigModuleId}">
                        Quality-Admin List
                    </c:if>
                    <c:if test="${moduleId!=commonConfigModuleId}">
                        Quality Requestor List
                    </c:if>
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
                       
                        <c:if test="${dashBoardList != null}">
                        <script type="text/javascript">$('#datadisplay #qualityRequestList th').css({'background' : ''});</script>
                        <c:forEach items="${dashBoardList}" var="dashBoardList" varStatus="rpritr">
                            <c:if test="${rpritr.index%2 ==0}">
                                <c:set var="rowClass" value="even"></c:set>
                            </c:if>
                            <c:if test="${rpritr.index%2!=0}">
                                <c:set var="rowClass" value="odd"></c:set>
                            </c:if>
                            <tr class="${rowClass}">
                                <td><a href="javascript:void(0)" onclick="getIndividualRequestDetails('${dashBoardList.referenceId}','getRequestDetails.htm')" >${dashBoardList.referenceId}</a></td>
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
                        </c:if>
                        <c:if test="${dashBoardList == ''}">
                            No Records To Display
                        </c:if>
                    </div>
                    </tbody>
                </table>  
            </div>
        </div>
        <form action="" method="POST" name="getIndividulaRequestDetail">
            <input type="hidden" id="refId" name="refId" value=""/>
            <input type="hidden" id="list" name="list" value="${listType}"/>
        </form>

    </body>
</html>
