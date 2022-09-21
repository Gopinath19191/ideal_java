<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script type="text/javascript">
            $(function() {
                $("#tabs").tabs({
                });
            });
            <%-- $(document).ready(function() {
                 $("#formHRClearance").validate();
             });
             function reject()
             {
                 $('#hrOverAllComments').attr("class", "");
                 $('#formHRClearance').attr("action", "listRegnSubmittedEmp.htm");
                 $('#formHRClearance').submit();
             }
             function disableSubmit(saveButtonId,submitButtonId,backButtonId)
             {
                         if(${Result!=NaN}){
                             $("#"+saveButtonId).hide();
                             $("#"+submitButtonId).hide();
                             $("#"+backButtonId).hide();
                         }
                         var result = validateComments();
                         if(result){
                             return true;
                         }else{
                             return false;
                         }
                     }
                     function validateComments(){
                         var counter = 0;
                         $(".dueCommentsClass").each(
                         function(){
                             if($(this).parent().parent().find(".dueClass:checked").val()==0){
                                 $(this).attr("class", "required");
                                 counter++;
                             }
                             else{
                                 $(this).attr("class", "");
                             }
                         }
                     );
                         $("#formHRClearance").validate();
                         return true;
                     }--%>

                         function downLoadData(type, actionName) {
                             if (type == 'no_due') {
                                 $('#formDownLoadData').attr("action", actionName);
                             } else if (type == 'exit_interview') {
                                 $('#formDownLoadData').attr("action", actionName);
                             } else if (type == 'exit_ffs') {
                                 $('#formDownLoadData').attr("action", actionName);
                             }
                             $('#formDownLoadData').submit();
                         }

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
                         function removeFile(){
                             document.getElementById('updateData').action = 'uploadFileFromHrClr.htm';
                             document.getElementById('updateData').submit();
                         }
        </script>
        <%--<style type="text/css">
            .ui-widget-content{
             border: 0px;
            }
        </style>--%>
    </head>
    <body onload="changeTabClass('hrApp');" >
        <span class="topheading">EPM (Exit Process Management)</span>
        <div class="generalFormContent">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <%@include file="/WEB-INF/jsp/commonContent.jsp" %>
            <div class="qpdSearch" style="height: auto;background-color: #FFF;margin-top: 10px;">
                <div class="formarea" style="margin-bottom: 0px;margin-top: 0px;">
                    <div id="tabs" style="margin-bottom: 10px;">
                        <ul>
                            <li><a href="#RMA">RM Approval</a></li>
                            <li><a href="#RMC">RM Clearance</a></li>
                            <li><a href="#FTC">Finance</a></li>
                            <li><a href="#ATC">Admin</a></li>
                            <li><a href="#NSC">N & S</a></li>
                            <li><a href="#EES">Exit Survey</a></li>
                            <li><a href="#HRC">HR</a></li>
                            <li><a href="#DED">Download Data</a></li>
                        </ul>
                        <div id="RMA">
                            <div class="scrollDiv">
                                <table width="100%" border="0" id="formTable">
                                    <tbody>
                                        <fmt:formatDate value="${rmActionData.exitTriggerDate}" pattern="dd-MM-yyyy" var="exitTriggerDate" />
                                        <fmt:formatDate value="${rmActionData.lastWorkingDate}" pattern="dd-MM-yyyy" var="lastWorkingDate" />
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
                                                    <td width="65%" style="text-align:left">
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
                                                        <span class="displayText">${exitTriggerDate}</span>
                                                    </td>
                                                    <td width="15%" style="text-align:left">
                                                        <label class="headLabel">Last Working Date:</label>
                                                    </td>

                                                    <td width="25%" style="text-align:left">
                                                        <span class="displayText">${lastWorkingDate}</span>
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
                                                        <td>
                                                            <label class="headLabel">RM Justification</label>                                      
                                                        </td>
                                                        <td>
                                                            <span class="displayText">${rmActionData.rmComments}</span>
                                                        </td>
                                                    </c:if>

                                                </tr>
                                                <c:if test="${fileDetails != null}">
                                                    <tr>
                                                        <td>
                                                            <label class="headLabel">Attachment</label>
                                                        </td>
                                                        <td>
                                                        <c:set var="fileNameArray" value="${fn:split(fileDetails.fileName,'~~')}"></c:set>
                                                        <a class="rmFile" href="javascript:void(0)" onclick="fileDownload('${fileDetails.fileName}', '${fileDetails.fileType}', '${fileDetails.referenceName}', '${fileDetails.referenceId}', '${fileDetails.fileId}', '${fileDetails.moduleName}')">${fileNameArray[2]}</a>   
                                                        </td>
                                                        <td>
                                                            <form name="updateData" id="updateData" method="POST" action=""  enctype="multipart/form-data">
                                                                <input type="hidden" readonly name="exitEmpId" id="empId" value="${exitEmpInfo.exitEmpId}" >
                                                                <input type="hidden" readonly name="empId" id="empId" value="${exitEmpInfo.resEmpId}" >
                                                                <input type="file" value="" name="file" onchange="removeFile()">
                                                            </form>
                                                        </td>
                                                    </tr>
                                                </c:if>
                                            </c:when>
                                        </c:choose>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <div id="RMC" style="border: 0px;">
                            <div class="scrollDiv">
                                <c:choose>
                                    <c:when test="${exitEmpInfo.rmClrDate!=null}">
                                        <%@include file="/WEB-INF/jsp/approval/exitMgmtRMClrContent.jsp" %>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="qpdSearch" style="height: 25px;margin-top: 0px;">
                                            Not Approved
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div id="FTC" style="border: 0px;">
                            <div class="scrollDiv">
                                <c:choose>
                                    <c:when test="${exitEmpInfo.finApprovedDate!=null}">
                                        <%@include file="/WEB-INF/jsp/approval/exitMgmtFinContent.jsp" %>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="qpdSearch" style="height: 25px;margin-top: 0px;">
                                            Not Approved
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div id="ATC" style="border: 0px;">
                            <div class="scrollDiv">
                                <c:choose>
                                    <c:when test="${exitEmpInfo.adApprovedDate!=null}">
                                        <%@include file="/WEB-INF/jsp/approval/exitMgmtAdContent.jsp" %>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="qpdSearch" style="height: 25px;margin-top: 0px;">
                                            Not Approved
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div id="NSC" style="border: 0px;">
                            <div class="scrollDiv">
                                <c:choose>
                                    <c:when test="${exitEmpInfo.nsApprovedDate!=null}">
                                        <%@include file="/WEB-INF/jsp/approval/exitMgmtNsContent.jsp" %>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="qpdSearch" style="height: 25px;margin-top: 0px;">
                                            Not Approved
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div id="EES" style="border: 0px;">
                            <div class="scrollDiv">
                                <c:choose>
                                    <c:when test="${exitEmpInfo.surveyStatus=='Y'}">
                                        <%@include file="/WEB-INF/jsp/approval/exitSurveyContent.jsp" %>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="qpdSearch" style="height: 25px;margin-top: 0px;">
                                            Not Submitted
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div id="HRC" style="border: 0px;">
                            <div class="scrollDiv">
                                <c:choose>
                                    <c:when test="${exitEmpInfo.rmApprovedDate!=null && exitEmpInfo.adApprovedDate!=null && exitEmpInfo.finApprovedDate!=null && exitEmpInfo.nsApprovedDate!=null && exitEmpInfo.rmClrDate!=null && exitEmpInfo.surveyStatus=='Y'}">
                                        <%@include file="/WEB-INF/jsp/approval/exitMgmtHrContent.jsp" %>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="qpdSearch" style="height: 25px;margin-top: 0px;">
                                            HR Approval Form Not Enabled
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div id="DED" style="border: 0px;">
                            <div class="scrollDiv">
                                <c:choose>
                                    <c:when test="${exitEmpInfo.rmApprovedDate!=null && exitEmpInfo.adApprovedDate!=null && exitEmpInfo.finApprovedDate!=null && exitEmpInfo.nsApprovedDate!=null && exitEmpInfo.rmClrDate!=null && exitEmpInfo.hrApprovedDate!=null && exitEmpInfo.surveyStatus=='Y'}">
                                        <a href="#" onclick="downLoadData('no_due', 'dwnldempdata.htm');"> No due form</a>&nbsp;
                                        <a href="#" onclick="downLoadData('exit_interview', 'dwnldexitempdata.htm');">Exit interview</a>
                                        <a href="#" onclick="downLoadData('exit_ffs', 'dwnldExitFfsData.htm');">Employee FFS</a>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="qpdSearch" style="height: 25px;margin-top: 0px;">
                                            Download employee data not enabled
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <form action="" method="post" name="fileDownloadForm" id="fileDownloadForm">
            <input class="fileUplaodData" type="hidden" name="fileName" id="fileName" />
            <input class="fileUplaodData" type="hidden" name="fileType" id="fileType" />
            <input class="fileUplaodData" type="hidden" name="referenceName" id="referenceName" />
            <input class="fileUplaodData" type="hidden" name="referenceId" id="referenceId" />
            <input class="fileUplaodData" type="hidden" name="fileId" id="fileId" />
            <input class="fileUplaodData" type="hidden" name="moduleName" id="moduleName" />
        </form>

        <form name="formDownLoadData" id="formDownLoadData" method="POST" action="">
            <input type="hidden" readonly name="empId" id="empId" value="${exitEmpInfo.resEmpId}" >
        </form>
        
    </body>
</html>

