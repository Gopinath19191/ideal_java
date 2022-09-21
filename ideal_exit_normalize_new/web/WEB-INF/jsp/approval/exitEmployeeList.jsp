<%-- 
    Document   : exitEmployeeList
    Created on : Oct 7, 2011, 1:21:44 PM
    Author     : 14583
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script type="text/javascript">

			function reject()
			{
				$('#formListUserDetails').attr("action", "exitWelcome.htm");
				$('#formListUserDetails').submit();
			}
			function showEmpInfo(name, moduleId) {
				$('#empId').val(name);
				$('#userModuleId').val(moduleId);
				if (moduleId ==${rmApprovalModId}) {
					$('#formListUserDetails').submit();
				} else if (moduleId ==${finApprovalModId}) {
					$('#formListUserDetails').attr("action", "showFinanceClearnace.htm");
					$('#formListUserDetails').submit();
				} else if (moduleId ==${nsApprovalModId}) {
					$('#formListUserDetails').attr("action", "showNetworkClearnace.htm");
					$('#formListUserDetails').submit();
				} else if (moduleId ==${adminApprovalModId}) {
					$('#formListUserDetails').attr("action", "showAdminClearnace.htm");
					$('#formListUserDetails').submit();
				} else if (moduleId ==${hrApprovalModId}) {
					$('#formListUserDetails').attr("action", "showHrClearnace.htm");
					$('#formListUserDetails').submit();
				} else if (moduleId ==${rmCleranceModId}) {
					$('#formListUserDetails').attr("action", "showRmClearnace.htm");
					$('#formListUserDetails').submit();
				}
			}
			function exitEmployeeSurvey(name, exitEmpId, moduleId) {
				
				if (confirm("Are you sure do you want to complete the exit survey on behalf of an employee!") == true) {
					$('#empId').val(name);
					$('#exitEmpId').val(exitEmpId);
					$('#userModuleId').val(moduleId);
					$('#formListUserDetails').attr("action", "exitEmployeeSurvey.htm");
					$('#formListUserDetails').submit();
				} else {

				}

			}
        </script>   
    </head>
    <c:choose>
        <c:when test="${moduleId==496}">
            <c:set var="listName" value="rmApp" />
        </c:when>
        <c:when test="${moduleId==495}">
            <c:set var="listName" value="rmClr" />
        </c:when>
        <c:when test="${moduleId==497}">
            <c:set var="listName" value="finApp" />
        </c:when>
        <c:when test="${moduleId==498}">
            <c:set var="listName" value="netApp" />
        </c:when>
        <c:when test="${moduleId==499}">
            <c:set var="listName" value="adminApp" />
        </c:when>
        <c:when test="${moduleId==500}">
            <c:set var="listName" value="hrApp" />
        </c:when>
        <c:when test="${hrApproved=='yes'}">
            <c:set var="listName" value="resigLetter" />
        </c:when>
        
    </c:choose>
    <body onload="changeTabClass('${listName}');" >
       
        <div class="page_heading">EPM(Exit Process Management)
        <div class="goToList">
            <img src="/ideal_exit_normalize/images/icon_list.png" title=" " alt=" ">
            <a style="text-decoration:none;color: #4C83B3;margin-right: 2px;" target="_self" href="listRegnSubmittedEmp.htm?moduleId=${moduleId}&approveType=pending">List Pending Request</a>
            <img src="/ideal_exit_normalize/images/icon_list.png" title=" " alt=" ">
            <a style="text-decoration:none;color: #4C83B3;" target="_self" href="listRegnSubmittedEmp.htm?moduleId=${moduleId}&approveType=processed">List Processed Request</a>
        </div>
        </div>
        <div class="generalFormContent">
             
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            
            <div class="formarea" style="margin-bottom: 0px;">
                <c:choose>
                    <c:when test="${fn:length(regnEmpList)==0}">
                        <div class="qpdSearch" style="height: 25px;padding-top: 0px;margin-top: 0px;">
                            No data to display
                        </div>
                    </c:when>
                    <c:when test="${regnEmpList ne null}">
                        <table width="100%" border="0">
                            <tbody>
                                <tr>
                                    <td>
                                        <form action="showEmpInfo.htm" name="formListUserDetails" id="formListUserDetails" method="post">
											<%int flag = 0;%>
											<c:choose>
												<c:when test="${approveType== 'pending'}">
													<% flag = 1;%>
												</c:when>
											</c:choose>		
                                            <table id="formTable" border="0" width="100%">
                                                <thead>
                                                    <tr class="formarea_header" >
                                                        <th>Employee ID</th>
                                                        <th>Employee Name</th>
                                                        <th>Band</th>
                                                        <th>Sub Band</th>
                                                        <th>SBU</th>
                                                        <th>Date of Joining</th>
                                                        <th>Date of Resigning</th>
                                                        <th>Exit Trigger Date</th>
                                                        <th>Last Working Date</th>
                                                        <th>Status</th>
                                                        <c:if test="${moduleId==hrApprovalModId}">
                                                            <th>Resignation</th>
                                                            <th>Service</th>
                                                            <th>Publish</th>
                                                            <% if (flag == 1) {%>
                                                            <th>exit employee survey</th>
                                                            <%}%>
                                                    </c:if>
                                                    </tr>
                                                </thead>
                                                <c:set value="0" var="rowDisplay"></c:set>
                                                <c:forEach items="${regnEmpList}" var="lstExitEmployee" varStatus="item">
                                                    <c:choose>
                                                        <c:when test="${(item.index+1)%2==0}">
                                                            <c:set value="formarea_row2" var="rowDisplay"></c:set>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set value="formarea_row1" var="rowDisplay"></c:set>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <fmt:formatDate value="${lstExitEmployee.empDoj}" pattern="dd-MM-yyyy" var="empDoj" />
                                                    <fmt:formatDate value="${lstExitEmployee.resignedDate}" pattern="dd-MM-yyyy" var="resignedDate" />
                                                    <fmt:formatDate value="${lstExitEmployee.exitTriggerDate}" pattern="dd-MM-yyyy" var="exitTriggerDate" />
                                                    <fmt:formatDate value="${lstExitEmployee.lastWorkingDate}" pattern="dd-MM-yyyy" var="lastWorkingDate" />
                                                    <tr class="${rowDisplay}" >
                                                        <td title="${lstExitEmployee.employeeNumber}"><a href="#" style="color: #4C83B3;font-size: 12px;font-weight: bold" onclick="showEmpInfo('${lstExitEmployee.empId}','${moduleId}')"/>${lstExitEmployee.employeeNumber}</td>
                                                        <td title="${lstExitEmployee.employeeName}">${lstExitEmployee.employeeName}</td>
                                                        <td title="${lstExitEmployee.band}">${lstExitEmployee.band}</td>
                                                        <td title="${lstExitEmployee.subBand}">${lstExitEmployee.subBand}</td>
                                                        <td title="${lstExitEmployee.practiceGroup}">${lstExitEmployee.practiceGroup}</td>
                                                        <td title="${empDoj}">${empDoj}</td>
                                                        <td title="${resignedDate}">${resignedDate}</td>
                                                        <td title="${exitTriggerDate}"${exitTriggerDate}>
                                                            <c:choose>
                                                                <c:when test="${lstExitEmployee.exitTriggerDate ne null}">
                                                                    ${exitTriggerDate}
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ----
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td title="${lastWorkingDate}">
                                                            <c:choose>
                                                                <c:when test="${lstExitEmployee.lastWorkingDate ne null}">
                                                                    ${lastWorkingDate}
                                                                </c:when>
                                                                <c:otherwise>
                                                                    ----
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <td>
                                                            <c:choose>
                                                                <c:when test="${lstExitEmployee.rmApprovedDate!='' && lstExitEmployee.rmApprovedDate!=null && moduleId==rmApprovalModId}">
                                                                    <%--<span class="approveDisplay">RM Approved</span>--%>
                                                                    <span class="approveDisplay">Approved</span>
                                                                </c:when>
                                                                <c:when test="${lstExitEmployee.rmClrDate!=null && moduleId==rmCleranceModId}">
                                                                    <%--<span class="approveDisplay">RM Approved</span>--%>
                                                                    <span class="approveDisplay">Approved</span>
                                                                </c:when>
                                                                <c:when test="${lstExitEmployee.finApprovedDate!=null && moduleId==finApprovalModId}">
                                                                    <%--<span class="approveDisplay">Finance Approved</span>--%>
                                                                    <span class="approveDisplay">Approved</span>
                                                                </c:when>
                                                                <c:when test="${lstExitEmployee.adApprovedDate!=null && moduleId==adminApprovalModId}">
                                                                    <%--<span class="approveDisplay">Admin Approved</span>--%>
                                                                    <span class="approveDisplay">Approved</span>
                                                                </c:when>
                                                                <c:when test="${lstExitEmployee.nsApprovedDate!=null && moduleId==nsApprovalModId}">
                                                                    <span class="approveDisplay">Approved</span>
                                                                </c:when>
                                                                <c:when test="${lstExitEmployee.hrApprovedDate!=null && moduleId==hrApprovalModId}">
                                                                    <span class="approveDisplay">Approved</span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="notSubmittedDisplay">Not Submitted</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </td>
                                                        <c:if test="${moduleId==hrApprovalModId}">
                                                            <td>
                                                            <c:choose>
                                                                <c:when test="${lstExitEmployee.empStatus!='b'}"> 
                                                                    <c:choose>
                                                                    <c:when test="${lstExitEmployee.hrApprovedDate!=null && moduleId==hrApprovalModId}">
                                                                        <span class="form-head-value">
                                                                            <a href="#" onclick="getResLetter(${lstExitEmployee.empId})" >Resignation Letter</a>
                                                                        </span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="form-head-value">---</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="form-head-value">---</span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            </td>
                                                            </c:if>
                                                        
                                                        <c:if test="${moduleId==hrApprovalModId}">
                                                           <td>   
                                                        <c:choose>
                                                                <c:when test="${lstExitEmployee.empStatus!='b'}"> 
                                                              
                                                                <c:choose>
                                                                    <c:when test="${lstExitEmployee.hrApprovedDate!=null && moduleId==hrApprovalModId}">
                                                                        <span class="form-head-value">
                                                                            <a href="#" onclick="getServLetter(${lstExitEmployee.empId})" style="width: 114px;">Service Letter</a>
                                                                        </span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="form-head-value">---</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="form-head-value">---</span>
                                                                </c:otherwise>
                                                                
                                                        </c:choose>
                                                                    </td>
                                                        </c:if>
                                                        <c:if test="${moduleId==hrApprovalModId}">
                                                           <td>   
                                                        <c:choose>
                                                                <c:when test="${lstExitEmployee.empStatus!='b'}"> 
                                                              
                                                                <c:choose>
                                                                    <c:when test="${lstExitEmployee.hrApprovedDate!=null && moduleId==hrApprovalModId}">
                                                                        <span class="form-head-value">
                                                                            <a href="#" onclick="publish(${lstExitEmployee.empId})" style="width: 114px;">Publish</a>
                                                                        </span>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <span class="form-head-value">---</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span class="form-head-value">---</span>
                                                                </c:otherwise>
                                                                
                                                        </c:choose>
                                                                    </td>
                                                        </c:if>            
                                                        <%if (flag == 1) {%>
                                                        <c:if test="${moduleId==hrApprovalModId}" >
                                                                <td>
                                                                <c:choose>
                                                                    <c:when test="${lstExitEmployee.rmApprovedDate!=null && lstExitEmployee.surveyStatus != 'Y' }">
                                                                            <a href="#" onclick="exitEmployeeSurvey('${lstExitEmployee.empId}', '${lstExitEmployee.exitEmpId}', '${moduleId}')" style="width: 114px;">submit</a>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                            <span class="form-head-value">---</span>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                                </td>
                                                        </c:if>
                                                        <% }%>
                                                    </tr>
                                                </c:forEach>
                                            </table>
                                                <table width="100%" id="formTable" class="buttonAlignment">
                                                <tr>
                                                    <td width="49%" align="center" style="text-align: center;" colspan="5">
                                                        <input type="button" name="btnBack" id="btnBack" value="Home" class="homebutton" onclick="reject()">
                                                    </td>
                                                </tr>
                                            </table>
											<input type="hidden" name="exitEmpId" id="exitEmpId" readonly />	
                                            <input type="hidden" name="empId" id="empId" readonly />
                                            <input type="hidden" name="userModuleId" id="userModuleId" readonly  />
                                        </form>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </c:when>
                </c:choose>
            </div>
        </div>

        <form name="resLetterForm" id="resLetterForm" method="POST" action="#" >
            <input type="hidden" readonly name="resEmpId" id="resEmpId" value="" />
        </form>
    </body>
    <script type="text/javascript" >
			function getResLetter(resEmpId) {
				$('#resEmpId').val(resEmpId);
				$('#resLetterForm').attr("action", "resLetterGeneration.htm");
				$('#resLetterForm').submit();
			}
    </script>
    <style type="text/css">
        .approveDisplay{
            color: green;
        }
        .notSubmittedDisplay{
            color: red;
        }
    </style>

</html>
