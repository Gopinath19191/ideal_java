<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <script type="text/javascript">
            $(function() {
                $("#Tabs").tabs({
                });
            });
            $(document).ready(function() {
                $("#formHRClearance").validate();
            });

            function fileDownload(fileName, fileType, referenceName, referenceId, fileId, moduleName) {
                $("#fileName").val(fileName);
                $("#fileType").val(fileType);
                $("#referenceName").val(referenceName);
                $("#referenceId").val(referenceId);
                $("#fileId").val(fileId);
                $("#moduleName").val(moduleName);
                document.getElementById('fileDownloadForm').action = 'approvalFileDownload.htm';
                document.getElementById('fileDownloadForm').submit();
                //$('#fileDownloadForm').submit();
            }

        </script>
        <style type="text/css">
            .ui-widget-content{
                border:0px solid #DDDDDD;
            }
        </style>
    </head>
    <body onload="changeTabClass('viewStatus');" >
        <span class="topheading">EPM (Exit Process Management)</span>
        <div class="generalFormContent">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <c:choose>
                <c:when test="${exitEmpInfo.rmApprovedDate!=null}">
                    <%@include file="/WEB-INF/jsp/commonContent.jsp" %>
                    <div class="qpdSearch" style="height: auto;background-color: #FFF;margin-top: 10px;">
                        <div class="formarea" style="margin-bottom: 0px;margin-top: 0px;">
                            <form action="saveHRClearanceData.htm" method="post" name="formHRClearance" id="formHRClearance">
                                <div id="Tabs" style="margin-bottom: 10px;">
                                    <ul>
                                        <c:if test="${menuDetails.rm_approval_view==1}">
                                            <li><a href="#RMA">RM Approval</a></li>
                                            </c:if>
                                            <c:if test="${menuDetails.rm_clearance_view==1}">
                                            <li><a href="#RMC">RM Clearance</a></li>
                                            </c:if>
                                            <c:if test="${menuDetails.fin_approval_view==1}">
                                            <li><a href="#FTC">Finance Clearance</a></li>
                                            </c:if>
                                            <c:if test="${menuDetails.admin_approval_view==1}">
                                            <li><a href="#ATC">Admin Clearance</a></li>
                                            </c:if>
                                            <c:if test="${menuDetails.network_approval_view==1}">
                                            <li><a href="#NSC">N & S Clearance</a></li>
                                            </c:if>
                                            <c:if test="${menuDetails.hr_approval_view==1}">
                                            <li><a href="#HRC">HR Clearance</a></li>
                                            </c:if>
                                    </ul>
                                    <div id="HRC">
                                        <div class="scrollDiv">
                                            <c:choose>
                                                <c:when test="${exitEmpInfo.hrApprovedDate!=null}">
                                                    <%@include file="/WEB-INF/jsp/approval/exitMgmtHrContent.jsp" %>
                                                </c:when>
                                                <c:otherwise>
                                                    Not Approved
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div id="RMA">
                                        <div class="scrollDiv">
                                            <table width="100%" id="formTable">
                                                <tbody>
                                                    <c:choose>
                                                        <c:when test="${rmActionData ne null && exitEmpInfo ne null}">
                                                            <tr>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Band:</label>
                                                                </td>
                                                                <td width="25%" style="text-align:left">
                                                                    <span class="displayText">${exitEmpInfo.band}</span>
                                                                </td>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Sub Band:</label>
                                                                </td>
                                                                <td width="45%" style="text-align:left">
                                                                    <span class="displayText">${exitEmpInfo.subBand}</span>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Contact Number:</label>
                                                                </td>
                                                                <td width="25%" style="text-align:left">
                                                                    <span class="displayText">${exitEmpInfo.contactNo}</span>
                                                                </td>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Contact Address:</label>
                                                                </td>
                                                                <td width="45%" style="text-align:left">
                                                                    <span class="displayText">${exitEmpInfo.empAddress}</span>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Email Id:</label>
                                                                </td>
                                                                <td width="25%" style="text-align:left">
                                                                    <span class="displayText">${exitEmpInfo.personalEmail}</span>
                                                                </td>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Reasons of Leaving</label>
                                                                </td>
                                                                <td width="45%" style="text-align:left">
                                                                    <span class="displayText">${exitEmpInfo.leavingReason}</span>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Exit Trigger Date:</label>
                                                                </td>
                                                                <td width="25%" style="text-align:left">
                                                                    <span class="displayText">
                                                                        <fmt:formatDate value="${rmActionData.exitTriggerDate}" pattern="dd-MM-yyyy" var="exitTriggerDate" /> ${exitTriggerDate}
                                                                    </span>
                                                                </td>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Last Working Date:</label>
                                                                </td>
                                                                <td width="25%" style="text-align:left">
                                                                    <span class="displayText">
                                                                        <fmt:formatDate value="${rmActionData.lastWorkingDate}" pattern="dd-MM-yyyy" var="lastWorkingDate" /> ${lastWorkingDate}
                                                                    </span>
                                                                </td>

                                                            </tr>

                                                            <tr>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Balance notice period:</label>
                                                                </td>
                                                                <td width="25%" style="text-align:left">
                                                                    <span class="displayText">${rmActionData.balNoticePeriod}</span>
                                                                </td>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">No of days served</label>
                                                                </td>
                                                                <td width="45%" style="text-align:left">
                                                                    <span class="displayText">${rmActionData.daysServed}</span>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td width="15%" style="text-align:left">
                                                                    <label class="headLabel">Notice Waiver</label>
                                                                </td>
                                                                <td width="45%" style="text-align:left; width:150px;">
                                                                    <span class="displayText">
                                                                        <c:forEach items="${noticeWaiverValueList}" var="noticeWaiverValues" varStatus="index" >
                                                                            <c:if test="${rmActionData.noticeWaiverValue==index.index}">
                                                                                ${noticeWaiverValues}
                                                                            </c:if>
                                                                        </c:forEach>
                                                                    </span>
                                                                </td>

                                                                <c:if test="${rmActionData.rmComments != null}">
                                                                    <td width="15%" style="text-align:left">
                                                                        <label class="headLabel">RM Justification</label> 
                                                                    </td>
                                                                    <td width="45%" style="text-align:left">
                                                                        <span class="displayText">${rmActionData.rmComments}</span>
                                                                    </td>
                                                                </c:if>

                                                            </tr>

                                                            <c:if test="${fileDetails != null}">
                                                                <tr>
                                                                    <td width="15%" style="text-align:left">
                                                                        <label class="headLabel">Attachment</label>
                                                                    </td>
                                                                    <td>
                                                                        <c:set var="fileNameArray" value="${fn:split(fileDetails.fileName,'~~')}"></c:set>
                                                                        <a href="javascript:void(0)" onclick="fileDownload('${fileDetails.fileName}', '${fileDetails.fileType}', '${fileDetails.referenceName}', '${fileDetails.referenceId}', '${fileDetails.fileId}', '${fileDetails.moduleName}')">${fileNameArray[2]}</a>
                                                                    </td>
                                                                </tr>
                                                            </c:if>

                                                            <!--                                                        <tr style="margin-bottom: 100px;">
                                                                                                                        <td width="25%" align="center" colspan="4">
                                                                                                                            Status :
                                                            <c:forEach items="${statusList}" var="statusListValues" varStatus="index" >
                                                                <c:if test="${exitEmpInfo.submitStatus==index.index}">
                                                                    ${statusListValues}
                                                                </c:if>
                                                            </c:forEach>
                                                        </td>
                                                    </tr> -->
                                                        </c:when>
                                                        <c:otherwise>
                                                            <tr>
                                                                <td>
                                                                    Not Approved
                                                                </td>
                                                            </tr>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div id="RMC" style="border: 0px;">
                                        <div class="scrollDiv">
                                            <%@include file="/WEB-INF/jsp/approval/exitMgmtRMClrContent.jsp" %>
                                        </div>
                                    </div>
                                    <div id="FTC" style="border: 0px;">
                                        <div class="scrollDiv">
                                            <%@include file="/WEB-INF/jsp/approval/exitMgmtFinContent.jsp" %>
                                            <%-- <c:choose>
                                                 <c:when test="${exitEmpInfo.finApprovedDate!=null || exitEmpInfo.finStatus=='y'}">
                                                     <%@include file="/WEB-INF/jsp/approval/exitMgmtFinContent.jsp" %>
                                                 </c:when>
                                                 <c:otherwise>
                                                     Not Approved
                                                 </c:otherwise>
                                             </c:choose>--%>
                                        </div>
                                    </div>
                                    <div id="ATC" style="border: 0px;">
                                        <div class="scrollDiv">
                                            <%@include file="/WEB-INF/jsp/approval/exitMgmtAdContent.jsp" %>
                                            <%--<c:choose>
                                                <c:when test="${exitEmpInfo.adApprovedDate!=null || exitEmpInfo.adminStatus=='y'}">
                                                    <%@include file="/WEB-INF/jsp/approval/exitMgmtAdContent.jsp" %>
                                                </c:when>
                                                <c:otherwise>
                                                    Not Approved
                                                </c:otherwise>
                                            </c:choose>--%>
                                        </div>
                                    </div>
                                    <div id="NSC" style="border: 0px;">
                                        <div class="scrollDiv">
                                            <%@include file="/WEB-INF/jsp/approval/exitMgmtNsContent.jsp" %>
                                            <%--<c:choose>
                                                <c:when test="${exitEmpInfo.nsApprovedDate!=null || exitEmpInfo.nsStatus=='y'}">
                                                    <%@include file="/WEB-INF/jsp/approval/exitMgmtNsContent.jsp" %>
                                                </c:when>
                                                <c:otherwise>
                                                    Not Approved
                                                </c:otherwise>
                                            </c:choose>--%>
                                        </div>
                                    </div>
                                </div>
                            </form>

                            <form action="" method="post" name="fileDownloadForm" id="fileDownloadForm">
                                <input class="fileUplaodData" type="hidden" name="fileName" id="fileName" />
                                <input class="fileUplaodData" type="hidden" name="fileType" id="fileType" />
                                <input class="fileUplaodData" type="hidden" name="referenceName" id="referenceName" />
                                <input class="fileUplaodData" type="hidden" name="referenceId" id="referenceId" />
                                <input class="fileUplaodData" type="hidden" name="fileId" id="fileId" />
                                <input class="fileUplaodData" type="hidden" name="moduleName" id="moduleName" />
                            </form>

                        </div>
                    </div>
                </c:when>
                <c:otherwise>
                    <div class="qpdSearch" style="height: 25px;">
                        Status will be enabled upon acceptance of your resignation by your Reporting Manager.
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </body>
</html>

