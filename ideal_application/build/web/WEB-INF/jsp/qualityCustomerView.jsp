<%-- 
    Document   : qualityCustomerView
    Created on : Mar 21, 2012, 12:32:26 PM
    Author     : 14578
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Details</title>
        <%@include file="header.jsp" %>
        <script type="text/javascript">
            $(function() {
               
                $( "#expClosureDate" ).datepicker(
                {
                    minDate: new Date(),
                    dateFormat: 'dd-mm-yy',
                    changeMonth: true,
                    changeYear: true}
            );
                $( "#reqClosedDate" ).datepicker({ maxDate: new Date(),dateFormat: 'dd-mm-yy',changeMonth: true,changeYear: true});
            });
            
            function submitAction(actionMethod)
            {
                $("#newRequest").validate();
                if($("#newRequest").valid()) {
                    document.getElementById("submitButton").style.visibility = 'hidden';
                    document.getElementById("cancelBtn").style.visibility = 'hidden';
                    document.newRequest.action=actionMethod;
                    document.newRequest.submit;
                }
            }
            
            function getExpdateOfClosure(Value)
            {
                if(Value!=""){
                    var expdate= document.getElementById("expClosureDate");
                    var evalStatus= document.getElementById("evalStatus");
                    if(Value=="a")
                    {
                        expdate.setAttribute("class", "required")
                        $("#acceptedclosedDate").show();
                        $("#rejectedclosedDate").hide();
                        $("#defferedClosedDate").hide();
                    }
                    else if(Value=="r")
                    {
                        expdate.removeAttribute("class");
                        $("#acceptedclosedDate").hide();
                        $("#rejectedclosedDate").show();
                        $("#defferedClosedDate").hide();
                    }
                    else if(Value=="d")
                    {
                        expdate.removeAttribute("class");
                        $("#acceptedclosedDate").hide();
                        $("#rejectedclosedDate").hide();
                        $("#defferedClosedDate").show();
                    }
                    else
                    {
                        $("#expClosureDate").val("");
                        expdate.setAttribute("class", "required");
                        $("#acceptedclosedDate").hide();
                        $("#rejectedclosedDate").hide();
                        $("#defferedClosedDate").hide();
                    }
                }
            }
            function getreqClosedDate(Value)
            { 
                var closdate= document.getElementById("reqClosedDate");
                if(Value!="")
                {
                    if(Value=="c")
                    {
                        $("#requestClosedDate").show();
                        closdate.setAttribute("class", "required");
                    }
                    else
                    {
                        $("#requestClosedDate").hide();
                        closdate.removeAttribute("class");
                    }
                }
            }
        </script>
    </head>
    <body>
        <%
            DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            String date = formatter.format(new java.util.Date());
        %>
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
            <div class="page_heading" style="margin-top: 45px;">
                <c:if test="${moduleId==commonConfigModuleId}">
                    Quality-Admin List
                </c:if>
                <c:if test="${moduleId!=commonConfigModuleId}">
                    Quality Requestor List
                </c:if>
            </div>
            <c:if test="${moduleId==commonConfigModuleId}">
                <div class="notice_page">
                    <div style="float: left"><img src="/ideal_rrf_new/css/images/icon_alert.png" title="Information" alt="Information"></img></div>
                    <div style="padding-left: 10px;padding-top: 1px;">
                        <img src="/ideal_rrf_new/css/images/tick.png" title="Information" alt="Information" style="margin-left: 15px;margin-right: 10px;"></img>
                        Fields marked in * are mandatory.
                    </div>
                </div>
            </c:if>
            <form action="#" id="newRequest" name="newRequest" method="POST" enctype="multipart/form-data">
                <div class="formContent_new" id="customerdatadisplay" style="height:auto">
                    <div id="outer">
                        <div  class="topmessage">Reference Id : ${empDetails.referenceId}</div>
                        <!--                        <div id="processImp">-->
                        <table>
                            <tr>
                                <td align="center" colspan="2"  height="15">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
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
                                <td><label>FeedBack Type </label></td>
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
                            <tr >
                                <td><label class="">Attached File</label></td>
                                <c:if test="${fileDetails.originalFileName=='' || fileDetails.originalFileName==null}">
                                    <td>---</td>
                                </c:if>
                                <c:if test="${fileDetails.originalFileName!='' || fileDetails.originalFileName!=null}">
                                    <td><a href="fileDownload.htm?fileName=${fileDetails.originalFileName}" target="_blank">${fileDetails.originalFileName}</a></td>
                                </c:if>   
                            </tr>
                        </table>
                        <!--                        </div>-->
                        <c:if test="${empDetails.feedbackType=='p'}">
                            <!--                            <div id="processImp"  >-->
                            <table>
                                <tr >
                                    <td><label>Improvement Request Type </label></td>
                                    <td>
                                        <c:forEach items="${improvementType}" var="improvementType" varStatus="i">
                                            <c:if test="${improvementType.configKey eq empDetails.improvementRequest}">
                                                ${improvementType.configValue}
                                            </c:if>
<!--                                                        <option value="${improvementType.configKey}" ${improvementType.configKey eq empDetails.improvementRequest ? 'selected="selected"':''}>${improvementType.configValue}</option>-->
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr >
                                    <td><label>Process Area </label></td>
                                    <td>
                                        <c:forEach items="${processType}" var="processType" varStatus="i">
                                            <c:if test="${processType.parentId eq empDetails.processArea}">
                                                ${processType.configValue} 
                                            </c:if>
<!--                                                        <option value="${processType.parentId}" ${processType.parentId eq empDetails.processArea ? 'selected="selected"':''}>${processType.configValue}</option>-->
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr >
                                    <td><label>Focus Area </label></td>
                                    <td>
                                        <c:forEach items="${focusType}" var="focusType" varStatus="i">
                                            <c:if test="${focusType.configKey eq empDetails.focusArea}">
                                                ${focusType.configValue} 
                                            </c:if>

<!--                                                        <option value="${focusType.configKey}" >${focusType.configValue}</option>-->
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr >
                                    <td><label>Justification</label></td>
                                    <td>${empDetails.justification}</td>
                                </tr>

                                <c:if test="${moduleId==commonConfigModuleId}">
                                    <tr >
                                        <td><label>Improvement Evaluation Description <font color="red">*</font></label></td>
                                        <td><textarea class=" required textbox_new" style="width: 300px;height: 90px;" id="evalDescription" name="evalDescription">${empDetails.evalDescription}</textarea></td>
                                    </tr>
                                    <tr >
                                        <td><label>Evaluation Status <font color="red">*</font> </label></td>
                                        <td><select id="evalStatus" name="evalStatus" class=" required textbox_new" style="width: 193px;" onchange="getExpdateOfClosure(this.value);">
                                                <option value="0">--Select Evaluation Status--</option>
                                                <c:forEach items="${evalStatus}" var="evalStatus" varStatus="i">
                                                    <option value="${evalStatus.configKey}" ${evalStatus.configKey eq empDetails.evalStatus ? 'selected="selected"':''}>${evalStatus.configValue}</option>
                                                </c:forEach>
                                            </select></td>
                                    </tr>

                                    <c:choose>
                                        <c:when test="${empDetails.evalStatus=='a'}">
                                            <tr id="acceptedclosedDate" style=" ">
                                            </c:when>
                                            <c:otherwise>
                                            <tr id="acceptedclosedDate" style="display: none;">
                                            </c:otherwise>
                                        </c:choose>
                                        <td><label>Expected Date OF Closure </label></td>
                                        <td><input  type="text" id="expClosureDate" name="acceptexpClosureDate" class=" textbox_new" value="<fmt:formatDate pattern="dd-MM-yyyy"
                                                        value="${empDetails.expClosureDate}" />"/></td>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${empDetails.evalStatus=='r'}">
                                            <tr id="rejectedclosedDate" style=" ">
                                            </c:when>
                                            <c:otherwise>
                                            <tr id="rejectedclosedDate" style="display: none;">
                                            </c:otherwise>
                                        </c:choose>
                                        <td><label>Rejected Date </label> </td>
                                        <td><input  type="text" class=" textbox_new" readonly id="expClosureDate" name="rejectexpClosureDate" value="<%=date%>"/></td>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${empDetails.evalStatus=='d'}">
                                            <tr id="defferedClosedDate" style=" ">
                                            </c:when>
                                            <c:otherwise>
                                            <tr id="defferedClosedDate" style="display:  none;">
                                            </c:otherwise>
                                        </c:choose>
                                        <td><label>Deffered Date </label> </td>
                                        <td><input  type="text" class=" textbox_new" readonly  id="expClosureDate" name="defferedexpClosureDate" value="<%=date%>"/></td>
                                    </tr>

                                    <tr ><td><label>Request Status </label></td>
                                        <td><select id="reqStatus" name="reqStatus" class="textbox_new" style="width: 193px;" onchange="getreqClosedDate(this.value);">
                                                <option value="0">--Select Request Status--</option>
                                                <c:forEach items="${reqStatus}" var="reqStatus" varStatus="i">
                                                    <option value="${reqStatus.configKey}" ${reqStatus.configKey eq empDetails.reqStatus ? 'selected="selected"':''}>${reqStatus.configValue}</option>
<!--                                                            <option value="${reqStatus.configKey}" >${reqStatus.configValue}</option>-->
                                                </c:forEach>
                                            </select></td>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${empDetails.reqStatus == 'c'}">
                                            <tr id="requestClosedDate" style=" ">
                                            </c:when>
                                            <c:otherwise>
                                            <tr id="requestClosedDate" style="display:  none;">
                                            </c:otherwise>
                                        </c:choose>

                                        <td><label>Request Closed Date </label></td>
                                        <td><input type="text"  id="reqClosedDate" name="reqClosedDate" class="textbox_new" value="${empDetails.reqClosedDate}" /></td>
                                    </tr>

                                </c:if>
                            </table>
                            <!--                            </div>-->
                        </c:if>
                        <c:if test="${moduleId==commonConfigModuleId}">
                            <!--                            <div id="processImp" >-->
                            <table width="50%">
                                <tr ><td><label>Responder Name </label></td>
                                    <td>${qualityEmpDetails.employeeId}   ${qualityEmpDetails.employeeName}  </td>
                                </tr>
                                <tr >
                                    <c:choose>
                                        <c:when test="${empDetails.feedbackType=='c'}">
                                            <td><label>Root Cause Analysis(RCA)  <font color="red">*</font></label></td>
                                            <td><textarea id="rootCauseAnalysis" style="width: 300px;height: 90px;" class="required textbox_new" name="rootCauseAnalysis">${empDetails.rootCauseAnalysis}</textarea></td>
                                        </c:when>
                                        <c:when test="${empDetails.feedbackType=='q'|| empDetails.feedbackType=='p'}">
                                            <td><label>Root Cause Analysis(RCA)</label></td>
                                            <td><textarea class="textbox_new" id="rootCauseAnalysis" name="rootCauseAnalysis" style="width: 300px;height: 90px;">${empDetails.rootCauseAnalysis}</textarea></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><label>Root Cause Analysis(RCA)</label></td>
                                            <td>---</td>
                                        </c:otherwise>
                                    </c:choose>


                                </tr>
                                <tr >
                                    <c:choose>
                                        <c:when test="${empDetails.feedbackType=='p'}">
                                            <td><label>Remarks </label></td>
                                            <td><textarea class="textbox_new" style="width: 300px;height: 90px;" id="qualtyRemarks" name="qualtyRemarks">${empDetails.qualtyRemarks}</textarea></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><label>Response <font color="red">*</font></label></td>
                                            <td><textarea id="qualtyRemarks" style="width: 300px;height: 90px;" class="required textbox_new" name="qualtyRemarks"></textarea></td>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </table>
                            <!--                            </div>-->
                        </div>

                        <div class="buttonAlignment">
                            <div id="btnGroup" class="buttonAlignment" >
                                <!--                                <div id="actionBtn" style="padding-left: 200px;">-->
                                <input type="hidden" id="referenceId" name="referenceId" readonly value="${empDetails.referenceId}" />
                                <input type="hidden" id="id" name="id" readonly value="${empDetails.id}" />
                                <input type="hidden" id="qualityId" name="qualityId" readonly value="${empId}" />
                                <input type="hidden" id="list" name="list" readonly value="${listType}" />
                                <input type="hidden" id="employeeName" name="employeeName" readonly value="${empDetails.employeeName} ${empDetails.employeeId}" />
                                <input type="hidden" id="resposerName" name="resposerName" readonly value="${qualityEmpDetails.employeeName}  ${qualityEmpDetails.employeeId}" />
                                <input type="hidden" id="resposerId" name="resposerId" readonly value="${qualityEmpDetails.employeeId}" />
                                <input type="hidden" id="empId" name="empId" readonly value="${empDetails.employeeName}  ${empDetails.employeeId}" />
                                <input type="hidden"  id="qualClosedDate" name="qualClosedDate" readonly value="<%= date%>"/>
                                <input type="hidden"  id="feedbackType" name="feedbackType" readonly value="${empDetails.feedbackType}"/>
                                <input type="hidden"  id="mailName" name="mailName" readonly value="${empDetails.employeeName}"/>
                                <input type="hidden"  id="mailSubName" name="mailSubName" readonly value="${qualityEmpDetails.employeeName}"/>
                                <!--//Fix Ticket ID RefNo ITS000602 -->
                                <input type="hidden"  id="requestorEmployeeID" name="requestorEmployeeID" readonly value="${empDetails.requestorEmployeeID}"/>

                                <button class=" qualitysubmit" name="submitButton" id="submitButton" value="2"  onclick="submitAction('updateRequest.htm');">Submit</button>
                                <input class=" qualitycancel" type="button" id="cancelBtn" name="cancelBtn"  value="Cancel" onclick="javascript: location.href='authenticate.htm?empId=${employee_no}&modId=570' " />
                                <!--                                </div>-->
                            </div>
                        </div>
                    </c:if>
                </div>
            </form>
        </div>
    </body>

</html>
