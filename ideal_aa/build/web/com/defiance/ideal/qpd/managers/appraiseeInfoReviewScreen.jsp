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
    <body>
        <div class="tabletools submenuwrap">
            <%@include file="../../../../../menu.jsp" %>
            <div class="contentwrap">
            <%--<div class="contentBand">Quarterly Performance Dialogue Form</div>--%>
            <div class="contentBand">Annual Appraisal</div>
            <div class="contentarea">
                <%--<table style="width:100%">
                    <tr>
                    <td>Appraisal Year Under Review
                     
                             <select name="appraisalQuarterForm" id="appraisalPeriod" class="required" style="width: 4em" onchange="fetchAppraiseeHistory()">
<!--                                    <option value="">Select Period</option>-->
                                        <c:forEach begin="1" end="4" var="qValue">
                                              <c:choose>
                                                <c:when test="${appraisalQuarter == qValue}">
                                                    <option value="${qValue}" selected="selected">Q${qValue}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${qValue}">Q${qValue}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                </select>
                            <select name="appraisalYearForm" id="appraisalYear" class="required" style="width: 5em">
<!--                                    <option value="">Select Year</option>-->
                                    <c:forEach items="${yearData}" var="year">
                                               <c:choose>
                                                <c:when test="${appraisalYear == year}">
                                                    <option value="${year}" selected="selected">${year}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${year}">${year}</option>
                                                </c:otherwise>
                                            </c:choose>
                                    </c:forEach>
                                </select>
                         </td></tr>
                </table>--%>
                <table style="width:100%">
                     <tr>
                     <td>
                         <label class="headLabel">Employee Name</label></td><td><span class="displayText">${appraiserObj.appraiseeNameForm}</span>
                     </td>
                     <td><label class="headLabel">Employee ID</label></td><td><span class="displayText">${appraiserObj.appraiseeNumberForm}</span></td>
                     <%--<td><label class="headLabel">Quarter Under Review</label></td>--%>
                     <td><label class="headLabel">Year Under Review</label></td>
                     <td><span class="displayText">${appraisalYear}
                             <%--<select name="appraisalQuarterForm" id="appraisalPeriod" style="width: 4em" onchange="fetchAppraiseeInfoAppraiser()">
<!--                                    <option value="">Select Period</option>-->
                                        <c:forEach begin="1" end="4" var="qValue">
                                              <c:choose>
                                                <c:when test="${appraiseeInitialData.appraisalQuarterForm == qValue}">
                                                    <option value="${qValue}" selected="selected">Q${qValue}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${qValue}">Q${qValue}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                </select>--%>
                            <%--<select name="appraisalYearForm" id="appraisalYear" style="width: 5em">
<!--                                    <option value="">Select Year</option>-->
                                    <c:forEach items="${yearData}" var="year">
                                               <c:choose>
                                                <c:when test="${appraiseeInitialData.appraisalYearForm == year}">
                                                    <option value="${year}" selected="selected">${year}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${year}">${year}</option>
                                                </c:otherwise>
                                            </c:choose>
                                    </c:forEach>
                                </select>--%>
                         </span>
                     </td>
                     </tr>
                     <tr><td><label class="headLabel">Appraisee Status</label></td>
                         <td><span class="displayText">${appraiserObj.appraiseeStatusForm}</span></td>
                         <td><label class="headLabel">Appraisee Joining Date</label></td>
                         <td><span class="displayText">${appraiserObj.appraiseeJoiningDateForm}</span></td></tr>
                     </table>
            <%--</div>--%>

                            <c:if test="${fn:length(topAchievements)!=0}">
                                <div>
                                    <fieldset>
                                        <legend>Appraisee Top Achievements During The Assessment Period</legend>
                                        <table id="topAchievements" width="100%" align="center" border="0" style="border-collapse: collapse">
                                            <tr>
                                                <th>
                                                    Appraisee Top Achievements
                                                </th>
                                                <th>
                                                    Appraiser Remarks
                                                </th>
                                                <%--<c:if test="${appraiseeDetails.submitStatus<2}">
                                                    <th width="100px">
                                                        Actions
                                                    </th>
                                                </c:if>--%>
                                            </tr>
                                            <c:forEach items="${topAchievements}" var="topAchievementsDetail" varStatus="taindex">
                                                <tr>
                                                    <td class="tableData">
                                                        ${topAchievementsDetail.keyResultArea}
                                                        <%--<textarea readonly cols="" rows="" class="bigText" id="keyResultAreas${taindex.index}" name="keyResultAreas[]">${topAchievementsDetail.keyResultArea}</textarea>
                                                        <input type="hidden" name="achievementId[]" id="achievementId${taindex.index}" value="${topAchievementsDetail.achievementId}" />--%>
                                                    </td>
                                                    <td>
                                                        ${topAchievementsDetail.appraiserRemarks}
                                                        <%--<textarea ${readonlystatus} cols="" rows="" class="bigText" id="appraiserRemarks${taindex.index}" name="appraiserRemarks[]">${topAchievementsDetail.appraiserRemarks}</textarea>--%>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </fieldset>
                                </div>
                         </c:if>

            </div>



            <c:if test="${!empty(kraData)}">
            <div class="formarea">
                <c:set var="appraiserRating" value="0"></c:set>
                <c:set var="appraiserComments" value="0"></c:set>
               <form action="save.do" method="post" name="appraiseeForm" id="appraiseeForm" enctype="multipart/form-data">
                   <table width="100%" align="center" border="0" datapagesize="10">
                     <thead>
                         <tr class="formarea_header">
                            <th>Key Result Area's </th>
                            <th>Performance Indicator</th>
                            <%--<th>MEASUREMENT CRITERIA</th>--%>
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
                             <div>
                                 <fieldset>
                                     <legend>Appraisee Development Needs</legend>
                                     <table id="developmentNeeds" width="100%" align="center" border="0" style="border-collapse: collapse">
                                         <tr>
                                             <th>
                                                 Appraisee Development Needs
                                             </th>
                                             <th>
                                                 Appraiser Remarks
                                             </th>
                                         </tr>
                                         <c:forEach items="${developmentNeeds}" var="developmentNeedsDetail" varStatus="dnindex">
                                             <tr>
                                                 <td class="tableData">
                                                     ${developmentNeedsDetail.developmentNeeds}
                                                     <%--<textarea readonly cols="" rows="" class="bigText" id="devNeedData${dnindex.index}" name="devNeedData[]">${developmentNeedsDetail.developmentNeeds}</textarea></td>--%>
                                                 <td class="tableData">
                                                     ${developmentNeedsDetail.apprDevRemarks}
                                                     <%--<textarea ${readonlystatus} cols="" rows="" class="bigText" id="apprDevelopmentRemarks${dnindex.index}" name="apprDevelopmentRemarks[]">${developmentNeedsDetail.apprDevRemarks}</textarea>
                                                     <input type="hidden" name="developmentNeedsId[]" id="developmentNeedsId${dnindex.index}" value="${developmentNeedsDetail.needsId}" />--%>
                                                 </td>
                                             </tr>
                                         </c:forEach>
                                     </table>
                                 </fieldset>
                             </div>
                         </c:if>

                         <c:if test="${fn:length(goalSheet)!=0}">
                             <div>
                                <fieldset>
                                    <legend>Appraisee Goal Sheet</legend>
                                    <table id="goalSheet" width="100%" align="center" border="0" style="border-collapse: collapse">
                                        <tr>
                                            <th>
                                                Appraisee Goals Sheet
                                            </th>
                                             <th>
                                                 Appraiser Remarks
                                             </th>
                                         </tr>
                                         <c:forEach items="${goalSheet}" var="goalSheetDetail" varStatus="glIndex">
                                             <tr>
                                                <td class="tableData">
                                                    ${goalSheetDetail.goalData}
                                                    <%--<textarea readonly cols="" rows="" class="bigText" id="goals${glIndex.index}" name="goals[]">${goalSheetDetail.goalData}</textarea></td>--%>
                                                <td class="tableData">
                                                    ${goalSheetDetail.appGoalRemarks}
                                                    <%--<textarea ${readonlystatus} cols="" rows="" class="bigText" id="apprGoalRemarks${glIndex.index}" name="apprGoalRemarks[]">${goalSheetDetail.appGoalRemarks}</textarea></td>--%>
                                            </tr>
                                        </c:forEach>
                                     </table>
                                 </fieldset>
                             </div>
                         </c:if>

                         <table width="100%" border="0">
                         <tr>
                             <td style="text-align: center"  valign="middle" width="25%">
                                 <%--Please add comments to justify rating --%> Strengths :
                             </td>
                             <td width="25%">
                                 ${appraiserComments}
                                 <%--<textarea cols=30 rows=3 class="required resizableTextArea" id="justifyRatingAppraiser" name="justifyRatingAppraiser" ${setDisabled}>${appraiserComments}</textarea>--%>
                             </td>
                             <td valign="middle" style="text-align: center" width="25%">
                                 <%--Areas of improvement--%> Improvement Areas :
                             </td>
                             <td width="25%">
                                 ${appraiserObj.areasOfImprovement}
                                 <%--<textarea class="resizableTextArea alphanumericlang" name="areasOfImprovement" cols="30" rows="3" ${setDisabled}>${appraiserObj.areasOfImprovement}</textarea>--%>
                             </td>
                         </tr>
                         </table>
                         <%--<fieldset>
                             <legend><span>Trainings Recommended</span></legend>--%>
                             <table width="100%" align="center">
                                 <tr>
                                     <td style="text-align: center" valign="middle" width="24%">
                                         <%--Technology Training --%> Action Plans:
                                     </td>
                                     <td style="text-align: left" colspan="2" width="24%">
                                         ${appraiserObj.technologyTraining}
                                         <%--<textarea class="resizableTextArea alphanumericlang" name="technologyTraining" cols="30" rows="3" ${setDisabled}>${appraiserObj.technologyTraining}</textarea>--%>
                                     </td>
                                     <td style="text-align: center" valign="middle" width="24%">
                                         <%--Soft skill Training --%> Over All Comments :
                                     </td>
                                     <td style="text-align: left" colspan="2" width="24%">
                                         ${appraiserObj.softskillTraining}
                                         <%--<textarea class="resizableTextArea alphanumericlang" name="softskillTraining" cols="30" rows="3" ${setDisabled}>${appraiserObj.softskillTraining}</textarea>--%>
                                     </td>
                                 </tr>
                             </table>
                   <table width="100%" align="center">
                       <tr>
                           <td style="text-align: center">
                                 <%--<a href="${back}" style="text-decoration: none">Back</a>--%>
                                 <input type="button" class="styledButton" value="Back" name="back" onclick="window.location.href='${back}'">
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
