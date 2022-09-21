<%--
    Document   : MyAppraisal
    Created on : Nov 11, 2010, 11:34:34 AM
    Author     : Administrator

--%>

<%@include file="../../../../../header.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Annual Appraisal</title>
         <script type="text/javascript">
             function fileDownloadManager(fileName,fileType,referenceName,referenceId,fileId,moduleName){
                $("#fileName").val(fileName);
                $("#fileType").val(fileType);
                $("#referenceName").val(referenceName);
                $("#referenceId").val(referenceId);
                $("#fileId").val(fileId);
                $("#moduleName").val(moduleName);
                document.getElementById('fileDownloadForm').action='fileDownload.do';
                document.getElementById('fileDownloadForm').submit();
                //$('#fileDownloadForm').submit();
                }
             function fetchAppraiseeHistory(){
                 $("#appraisalQuarterSelected").val($("#appraisalPeriod").val());
                 $("#appraisalYearSelected").val($("#appraisalYear").val());
                 document.getElementById('appraiseeInfo').action="fetchAppraiseeKraData.do";
                 document.getElementById('appraiseeInfo').submit();
             }
         </script>
   </head>
     <c:choose>
       <c:when test="${menuCheck=='reviewer'}">
            <body onload="changeTabClass('aaReviewer');">
       </c:when>
       <c:when test="${menuCheck=='finalReviewer'}">
            <body onload="changeTabClass('aaFinalReviewer');">
       </c:when>
       <c:otherwise>
            <body onload="changeTabClass('aaFinance');">
       </c:otherwise>
   </c:choose>
        <span class="topheading">Annual Appraisal</span>
        <div class="tabletools submenuwrap">
            <%@include file="../../../../../menu.jsp" %>
            <div class="contentwrap">
            <div class="contentarea">
                <div class="qpdSearch" style="height: 59px;margin-top: 0px;padding-top: 2px;">
                    <table style="width:100%">
                        <tr>
                            <td>
                                <label class="headLabel">Employee Name</label></td><td><span class="displayText">${appraiserObj.appraiseeNameForm}</span>
                            </td>
                            <td><label class="headLabel">Appraisee Status</label></td>
                            <td><span class="displayText">${appraiserObj.appraiseeStatusForm}</span></td>
                            <td><label class="headLabel">Year Under Review</label></td>
                            <td><span class="displayText">${appraisalYear}</span></td>
                        </tr>
                        <tr>
                            <td><label class="headLabel">Employee ID</label></td><td><span class="displayText">${appraiserObj.appraiseeNumberForm}</span></td>
                            <td><label class="headLabel">Appraisee Joining Date</label></td>
                            <td><span class="displayText">${appraiserObj.appraiseeJoiningDateForm}</span></td>
                            <td><label class="headLabel">Sub Band</label></td>
                            <td><span class="displayText">${appraiserObj.bandName}</span></td>
                        </tr>
                    </table>
                </div>
                            <c:if test="${fn:length(topAchievements)!=0}">
                                <div class="formarea">
                                    <div>
                                        <table id="topAchievements"  width="100%" align="center" border="0" style="border-collapse: collapse;float: left;">
                                            <tr class="formarea_header">
                                                <th>
                                                    Appraisee Top Achievements During The Assessment Period
                                                </th>
                                                <th>
                                                    Appraiser Remarks
                                                </th>
                                            </tr>
                                            <c:forEach items="${topAchievements}" var="topAchievementsDetail" varStatus="taindex">
                                                <c:choose>
                                                    <c:when test="${(taindex.index+1)%2==0}">
                                                        <c:set value="formarea_row2" var="rowDisplay"></c:set>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set value="formarea_row1" var="rowDisplay"></c:set>
                                                    </c:otherwise>
                                                </c:choose>
                                                <tr>
                                                    <td class="${rowDisplay}">
                                                        ${topAchievementsDetail.keyResultArea}
                                                        <%--<textarea readonly cols="" rows="" class="bigText" id="keyResultAreas${taindex.index}" name="keyResultAreas[]">${topAchievementsDetail.keyResultArea}</textarea>
                                                        <input type="hidden" name="achievementId[]" id="achievementId${taindex.index}" value="${topAchievementsDetail.achievementId}" />--%>
                                                    </td>
                                                    <td class="${rowDisplay}">
                                                        ${topAchievementsDetail.appraiserRemarks}
                                                        <%--<textarea ${readonlystatus} cols="" rows="" class="bigText" id="appraiserRemarks${taindex.index}" name="appraiserRemarks[]">${topAchievementsDetail.appraiserRemarks}</textarea>--%>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                </div>
                                </div>
                         </c:if>
            </div>
            <c:if test="${!empty(kraData)}">
                <div class="formarea" style="margin-top: 0px;">
                <c:set var="appraiserRating" value="0"></c:set>
                <c:set var="appraiserComments" value="0"></c:set>
               <form action="save.do" method="post" name="appraiseeForm" id="appraiseeForm" enctype="multipart/form-data">
                   <table width="100%" align="center" border="0" datapagesize="10">
                     <thead>
                         <tr class="formarea_header">
                            <th>Key Result Area's </th>
                            <th>Performance Indicator</th>
                            <th>Weightage</th>
                            <th>Key Achievements - Comments</th>
                            <th>Rating</th>
                            <th>Rating Weightage</th>
                            <th>Appraiser Comments</th>
                        </tr>
                     </thead>
                     <tbody>
                         <c:set value="0" var="rowDisplay"></c:set>
                         <c:forEach items="${kraData}" var="datum" varStatus="indexValue">
                             <c:choose>
                             <c:when test="${(indexValue.index+1)%2==0}">
                                 <c:set value="formarea_row2" var="rowDisplay"></c:set>
                             </c:when>
                             <c:otherwise>
                                 <c:set value="formarea_row1" var="rowDisplay"></c:set>
                             </c:otherwise>
                            </c:choose>
                             <tr class="${rowDisplay}">
                                 <td>
                                     <c:choose>
                                         <c:when test="${indexValue.index>0}">
                                             <c:choose>
                                                 <c:when test="${kraData[indexValue.index].kraDescription!=kraData[indexValue.index-1].kraDescription}">
                                                     ${kraData[indexValue.index].kraDescription}
                                                 </c:when>
                                                 <c:otherwise>

                                                 </c:otherwise>
                                             </c:choose>
                                         </c:when>
                                         <c:otherwise>
                                             ${kraData[indexValue.index].kraDescription}
                                         </c:otherwise>
                                     </c:choose>
                                     <c:if test="${datum.qpdKraId==0}">
                                         <c:set value="${KraIdCount+1}" var="KraIdCount"></c:set>
                                     </c:if>
                                 </td>
                                 <%--<td>${datum.kraDescription}</td>--%>
                                 <td><b>${datum.attributes}</b><span class="displayText">${datum.performanceCriteria}</span></td>
                                 <%--<td><span class="displayText">${datum.measurementCriteria}</span></td>--%>
                                 <td style="text-align: center"><span class="displayText">${datum.weightage}%</span></td>
                                 <td><span class="displayText">${datum.selfComments}</span></td>
                                 <td style="text-align: center"><span class="displayText">${datum.appraiserIntitialRatings}</span></td>
                                 <td style="text-align: center"><span class="displayText">${datum.weightage*datum.appraiserIntitialRatings/100}</span></td>
                                 <td><span class="displayText">${datum.appraiserCommentsNew}</span></td>
                             </tr>
                             <c:set var="appraiserRating" value="${datum.appraiserRating}"></c:set>
                             <c:set var="appraiserComments" value="${datum.appraiserComments}"></c:set>
                             <c:set var="finalRating" value="${datum.correctedNormalizerRating}"></c:set>
                             <c:set var="submitStatus" value="${datum.submitStatus}"></c:set>
                         </c:forEach>
                             <c:if test="${submitStatus>=9}">
                             <tr><td align="center" style="text-align: center" colspan="7">Performance Level : ${finalRating}<c:if test="${appraiserComments!=null && appraiserComments!=''}"><span>||</span>Appraiser Comments : ${appraiserComments}</c:if></td></tr>
                             </c:if>
                         <c:if test="${fn:length(fileDetails)!=0}">
                             <tr>
                                 <td colspan="5" align="left">
                                     Attached Documents
                                     <c:forEach items="${fileDetails}" var="file" varStatus="index">
                                        <c:set var="fileNameArray" value="${fn:split(file.fileName,'~~')}"></c:set>
                                        <a href="javascript:void(0)" onclick="fileDownloadManager('${file.fileName}','${file.fileType}','${file.referenceName}','${file.referenceId}','${file.fileId}','${file.moduleName}')">${fileNameArray[2]}</a><br>
                                     </c:forEach>
                                 </td>
                             </tr>
                         </c:if>
                       </tbody>
                 </table>
                         <c:if test="${fn:length(developmentNeeds)!=0}">
                             <div class="formarea" style="margin-top: 10px;line-height: normal;">
                                 <div>
                                     <table id="developmentNeeds" width="102%" align="center" border="0" style="border-collapse: collapse">
                                         <tr class="formarea_header">
                                             <th>
                                                 Appraisee Development Needs
                                             </th>
                                             <th>
                                                 Appraiser Remarks
                                             </th>
                                         </tr>
                                         <c:forEach items="${developmentNeeds}" var="developmentNeedsDetail" varStatus="dnindex">
                                             <c:choose>
                                                 <c:when test="${(dnindex.index+1)%2==0}">
                                                     <c:set value="formarea_row2" var="rowDisplay"></c:set>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <c:set value="formarea_row1" var="rowDisplay"></c:set>
                                                 </c:otherwise>
                                             </c:choose>
                                             <tr>
                                                 <td class="${rowDisplay}">
                                                     ${developmentNeedsDetail.developmentNeeds}
                                                     <%--<textarea readonly cols="" rows="" class="bigText" id="devNeedData${dnindex.index}" name="devNeedData[]">${developmentNeedsDetail.developmentNeeds}</textarea></td>--%>
                                                 <td class="${rowDisplay}">
                                                     ${developmentNeedsDetail.apprDevRemarks}
                                                     <%--<textarea ${readonlystatus} cols="" rows="" class="bigText" id="apprDevelopmentRemarks${dnindex.index}" name="apprDevelopmentRemarks[]">${developmentNeedsDetail.apprDevRemarks}</textarea>
                                                     <input type="hidden" name="developmentNeedsId[]" id="developmentNeedsId${dnindex.index}" value="${developmentNeedsDetail.needsId}" />--%>
                                                 </td>
                                             </tr>
                                         </c:forEach>
                                     </table>
                                 </div>
                             </div>
                         </c:if>

                         <c:if test="${fn:length(goalSheet)!=0}">
                             <div class="formarea" style="margin-top: 10px;line-height: normal;">
                                 <div>
                                    <table id="goalSheet" width="102%" align="center" border="0" style="border-collapse: collapse">
                                        <tr class="formarea_header">
                                            <th>
                                                Appraisee Goals Sheet
                                            </th>
                                             <th>
                                                 Appraiser Remarks
                                             </th>
                                         </tr>
                                         <c:forEach items="${goalSheet}" var="goalSheetDetail" varStatus="glIndex">
                                             <c:choose>
                                                 <c:when test="${(dnindex.index+1)%2==0}">
                                                     <c:set value="formarea_row2" var="rowDisplay"></c:set>
                                                 </c:when>
                                                 <c:otherwise>
                                                     <c:set value="formarea_row1" var="rowDisplay"></c:set>
                                                 </c:otherwise>
                                             </c:choose>
                                             <tr>
                                                <td class="${rowDisplay}">
                                                    ${goalSheetDetail.goalData}
                                                    <%--<textarea readonly cols="" rows="" class="bigText" id="goals${glIndex.index}" name="goals[]">${goalSheetDetail.goalData}</textarea></td>--%>
                                                <td class="${rowDisplay}">
                                                    ${goalSheetDetail.appGoalRemarks}
                                                    <%--<textarea ${readonlystatus} cols="" rows="" class="bigText" id="apprGoalRemarks${glIndex.index}" name="apprGoalRemarks[]">${goalSheetDetail.appGoalRemarks}</textarea></td>--%>
                                            </tr>
                                        </c:forEach>
                                     </table>
                             </div>
                             </div>
                         </c:if>

                         <table width="102%" border="0">
                             <tr class="formarea_row2">
                             <td style="text-align: center"  valign="middle" width="25%">
                                 Strengths 
                             </td>
                             <td width="25%">
                                 ${appraiserComments}
                             </td>
                             <td valign="middle" style="text-align: center" width="25%">
                                 Improvement Areas 
                             </td>
                             <td width="25%">
                                 ${appraiserObj.areasOfImprovement}
                             </td>
                         </tr>
                         </table>
                             <table width="100%" align="center">
                                 <tr class="formarea_row1">
                                     <td style="text-align: center" valign="middle" width="24%">
                                         Action Plans
                                     </td>
                                     <td style="text-align: left" colspan="2" width="24%">
                                         ${appraiserObj.technologyTraining}
                                     </td>
                                     <td style="text-align: center" valign="middle" width="24%">
                                         Over All Comments
                                     </td>
                                     <td style="text-align: left" colspan="2" width="24%">
                                         ${appraiserObj.softskillTraining}
                                     </td>
                                 </tr>
                             </table>
                   <table width="100%" align="center">
                       <tr>
                           <td style="text-align: center">
                                 <%--<a href="${back}" style="text-decoration: none">Back</a>--%>
                                 <input type="button" class="styledButton backButton" value="Back" name="back" onclick="window.location.href='${back}'">
                           </td>
                       </tr>
                   </table>
             </form>
                     <form action="" method="post" name="fileDownloadForm" id="fileDownloadForm">
                           <input type="hidden" name="fileName" id="fileName" />
                           <input type="hidden" name="fileType" id="fileType" />
                           <input type="hidden" name="referenceName" id="referenceName" />
                           <input type="hidden" name="referenceId" id="referenceId" />
                           <input type="hidden" name="fileId" id="fileId" />
                           <input type="hidden" name="moduleName" id="moduleName" />
                    </form>

            </div>
            </c:if>
            <form action="" method="post" name="appraiseeInfo" id="appraiseeInfo">
                         <input type="hidden" name="departmentIdSelected" id="departmentIdSelected" value="${departmentId}">
                        <input type="hidden" name="bandIdSelected" id="bandIdSelected" value="${bandId}">
                        <input type="hidden" name="appraiseeIdSelected" id="appraiseeIdSelected" value="${appraiseeId}">
                          <input type="hidden" name="qpdIdSelected" id="qpdIdSelected" value="${qpdId}">
                        <input type="hidden" name="appraisalQuarterSelected" id="appraisalQuarterSelected" value="">
                        <input type="hidden" name="appraisalYearSelected" id="appraisalYearSelected" value="">
                     </form>
            <c:if test="${empty(kraData)}">
        <div class="formarea">
            <table class="row" align="center" border="0" >
                <tr>
                    <td align="center">KRA Data Not Available For This Year</td>
                </tr>
            </table>
        </div>
    </c:if>
        </div>
    </div>
    </body>
</html>
