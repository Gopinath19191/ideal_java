<%-- 
    Document   : qualityCustomerView
    Created on : Mar 21, 2012, 12:32:26 PM
    Author     : 14578
--%>
<%@include file="header.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Details</title>
    </head>
    <body>

        <div id="container">
            <div class="goToList">
                <img alt="Processed List" title="Pending List" src="/iDeal_application/css/images/icon_list.png"/>
                <a class="" style="text-decoration:none;color: #4C83B3;" target="_self" href="qualityCustomerList.htm?list=pending">Pending List</a> 
                <img alt="Processed List" title="Processed List" src="/iDeal_application/css/images/icon_list.png"/>
                <a class="" style="text-decoration:none;color: #4C83B3;" target="_self" href="qualityCustomerList.htm?list=processed">Processed List</a> 
                <!--                <p class="paging_details" style="margin-top: 5px;"><span class="heading">Processed List</span>  </p>-->
            </div>
            <div class="page_heading" style="margin-top: 45px;">
                Processed List
            </div>
            <div id="">
                <div class="formContent_new" id="customerdatadisplay" style="height:auto">
                    <form action="#" id="newRequest" name="newRequest" method="POST" enctype="multipart/form-data">
                        <div id="outer">
                            <div  class="topmessage">Request Id: ${empDetails.referenceId}</div>
<!--                            <div id="processImp">-->
                                <table>
                                    <tr >
                                        <td><label  >Requestor Name </label></td>
                                        <td>${empDetails.employeeId}   ${empDetails.employeeName}</td>
                                    </tr>

                                    <tr >
                                        <td><label>Location </label></td>
                                        <c:if test="${empDetails.empLocation=='' || empDetails.empLocation==null}">
                                            <td>---</td>
                                        </c:if>
                                        <c:if test="${empDetails.empLocation=='' || empDetails.empLocation!=null}">
                                            <td>${empDetails.empLocation}</td>
                                        </c:if>
                                    </tr>
                                    <tr >
                                        <td>Requested Date </td>
                                        <td><fmt:formatDate value="${empDetails.reqDate}" var="reqDate" pattern="dd-MMM-yyyy" />${reqDate}</td>
                                    </tr>
                                    <tr >
                                        <td><label>FeedBack Type: </label></td>
                                        <td>
                                            <c:forEach items="${feedbackType}" var="feedbackType" varStatus="i">
                                                <c:if test="${feedbackType.configKey==empDetails.feedbackType}">
                                                    ${feedbackType.configValue}
                                                </c:if>
<!--                                                    <option  value="${feedbackType.configKey}" ${feedbackType.configKey eq empDetails.feedbackType ? 'selected="selected"':''}>${feedbackType.configValue}</option>-->
                                            </c:forEach>
                                        </td>
                                    </tr>
                                    <tr >
                                        <td><label>Description </label></td>
                                        <td>${empDetails.description}</td>
                                    </tr>
                                    <tr ><td><label class="">Attached File</label></td>
                                        <c:if test="${fileDetails.originalFileName=='' || fileDetails.originalFileName==null}">
                                            <td>---</td>
                                        </c:if>
                                        <c:if test="${fileDetails.originalFileName!='' || fileDetails.originalFileName!=null}">
                                            <td><a href="fileDownload.htm?fileName=${fileDetails.originalFileName}" target="_blank">${fileDetails.originalFileName}</a></td>
                                        </c:if>   
                                    </tr>
                                </table>
<!--                            </div>-->
                            <c:if test="${empDetails.feedbackType=='p'}">
<!--                                <div id="processImp" >-->
                                    <table>
                                        <tr >
                                            <td><label>Improvement Request Type</label></td>
                                            <td><c:forEach items="${improvementType}" var="improvementType" varStatus="i">
                                                    <c:if test="${improvementType.configKey eq empDetails.improvementRequest}">
                                                        ${improvementType.configValue}
                                                    </c:if>
<!--                                                        <option value="${improvementType.configKey}" ${improvementType.configKey eq empDetails.improvementRequest ? 'selected="selected"':''}>${improvementType.configValue}</option>-->
                                                </c:forEach></td>
                                        </tr>
                                        <tr >
                                            <td><label>Process Area</label></td>
                                            <td><c:forEach items="${processType}" var="processType" varStatus="i">
                                                    <c:if test="${processType.parentId eq empDetails.processArea}">
                                                        ${processType.configValue} 
                                                    </c:if>
<!--                                                        <option value="${processType.parentId}" ${processType.parentId eq empDetails.processArea ? 'selected="selected"':''}>${processType.configValue}</option>-->
                                                </c:forEach></td>
                                        </tr>
                                        <tr >
                                            <td><label>Focus Area</label></td>
                                            <td><c:forEach items="${focusType}" var="focusType" varStatus="i">

                                                    <c:if test="${focusType.configKey eq empDetails.focusArea}">
                                                        ${focusType.configValue} 
                                                    </c:if>
<!--                                                        <option value="${focusType.configKey}" >${focusType.configValue}</option>-->
                                                </c:forEach></td>
                                        </tr>
                                        <tr >
                                            <td><label>Justification</label></td>
                                            <td>${empDetails.justification}</td>
                                        </tr>

                                        <c:if test="${moduleId==commonConfigModuleId}">
                                            <tr >
                                                <td><label>Improvement Evaluation Description</label></td>

                                                <td>${empDetails.evalDescription}</td>
                                            </tr>
                                            <tr >
                                                <td><label>Evaluation Status</label></td>
                                                <td>

                                                    <c:forEach items="${evalStatus}" var="evalStatus" varStatus="i">
                                                        <c:if test="${evalStatus.configKey eq empDetails.evalStatus}">
                                                            ${evalStatus.configValue}
                                                        </c:if>
<!--                                                            <option value="${evalStatus.configKey}" ${evalStatus.configKey eq empDetails.evalStatus ? 'selected="selected"':''}>${evalStatus.configValue}</option>-->
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                            <tr >
                                                <td><label>Expected Date OF Closure</label></td>
                                                <td><fmt:formatDate value="${empDetails.expClosureDate}" var="expClosureDate" pattern="dd-MMM-yyyy" />${expClosureDate}</td>
                                            </tr>
                                            <tr ><td><label>Request Status </label></td>
                                                <td>
                                                    <c:forEach items="${reqStatus}" var="reqStatus"  varStatus="i">
                                                        <c:if test="${reqStatus.configKey eq empDetails.reqStatus}">
                                                            ${reqStatus.configValue}  
                                                        </c:if>
<!--                                                            <option value="${reqStatus.configKey}" ${reqStatus.configKey eq empDetails.reqStatus ? 'selected="selected"':''}>${reqStatus.configValue}</option>-->
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                            <tr >
                                                <td><label>Request Closed Date </label></td>
                                                <td><fmt:formatDate value="${empDetails.reqClosedDate}" var="reqClosedDate" pattern="dd-MMM-yyyy" />${reqClosedDate}</td>
                                            </tr>

                                        </c:if>
                                    </table>
<!--                                </div>-->
                            </c:if>
                            <c:if test="${moduleId==commonConfigModuleId}">
<!--                                <div id="processImp">-->
                                    <table>
                                        <tr >
                                            <td><label>Root Cause Analysis(RCA) </label></td>
                                            <td>
                                                ${empDetails.rootCauseAnalysis}
                                            </td>
                                        </tr>
                                        <tr><td><label>Remarks  </label></td>
                                            <td>${empDetails.qualtyRemarks}</td>
                                        </tr>
                                    </table>
<!--                                </div>-->
                                <div id="actionBtn" style="padding-left: 200px;">
                                    <input type="hidden" id="referenceId" name="referenceId" readonly value="${empDetails.referenceId}" />
                                    <input type="hidden" id="id" name="id" readonly value="${empDetails.id}" />
                                    <input type="hidden" id="qualityId" name="qualityId" readonly value="${empId}" />

                                </div>
                            </c:if>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </body>
</html>
