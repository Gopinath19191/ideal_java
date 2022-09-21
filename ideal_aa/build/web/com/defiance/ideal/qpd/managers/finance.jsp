<%--
    Document   : review.jsp
    Created on : Nov 15, 2010, 11:37:08 AM
    Author     : madhan
--%>
<%@include file="../../../../../header.jsp" %>
    <head>
        <title>Finance</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%--<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/highcharts.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/float.js"></script>--%>
        <script type="text/javascript">
        function submitFilter(){
            document.getElementById("saveReview").action = 'beginFinance.do';
            document.getElementById("saveReview").submit();
        }
        function showHideChart(btnObj){
            if(btnObj.value=='Show Chart'){
                btnObj.value='Hide Chart';
                $("#chartContainer").show();
            }else if(btnObj.value=='Hide Chart'){
                btnObj.value='Show Chart';
                $("#chartContainer").hide();
            }
        }
     function setAppraiseeData(appraiseeId,bandId,appraisalQuarter,appraisalYear,appraiserName,appraiseeEmpId,departmentId){
         //alert(appraiseeId+appraiserName+appraisalQuarter+appraisalYear)
         $("#appraiseeIdSelected").val(appraiseeId);
         $("#bandIdSelected").val(bandId);
         $("#appraisalQuarterSelected").val(appraisalQuarter);
         $("#appraisalYearSelected").val(appraisalYear);
         $("#appraiserNameSelected").val(appraiserName);
         $("#appraiseeEmpIdSelected").val(appraiseeEmpId);
         $("#appraiseeInfoReviewer").val("finance");
         $("#departmentIdSelected").val(departmentId);
         document.getElementById('appraiseeInfo').action="fetchAppraiseeKraData.do";
         $("#appraiseeInfo").submit();
     }
     function fetchAppraiseeListFinance(){
         document.getElementById('saveReview').action="beginFinance.do";
         document.getElementById('saveReview').submit();
     }
    </script>
    <noscript>
        <table align="center">
            <tr>
                <td>
                    <h1>Error! - Javascript Disabled</h1>
                    <font face="Arial, Helevetica" color="red" >You must have JavaScript enabled on your browser.</font>
                </td>
            </tr>
        </table>
    </noscript>
    <style type="text/css">
            #chartContainer{
            cursor: move;
            border: 1px solid #ff0000;
            background-color: #ffffff;
             }
    </style>
    </head>
    <body>
        <c:set var="submitCount" value="0"></c:set>
        <div class="tabletools submenuwrap">
        <%@include file="../../../../../menu.jsp" %>
        <div class="contentwrap">
        <div class="contentBand">Finance</div>
        <form action="financeAction.do" method="post" name="saveReview" id="saveReview" >
          <div class="contentarea">
              <%int count = 0;%>
                 <table width="100%" id="apprMaster" cellpadding="0" cellspacing="0" >
                    <tr align="center">
                        <td bgcolor="#ffffff">
                                 <%--  Quarter :
                                   <select name="appraisalQuarter" id="appraisalQuarter" class="required" style="width: 4em" onchange="fetchAppraiseeListFinance()">
                                        <c:forEach begin="1" end="4" var="qValue">
                                              <c:choose>
                                                <c:when test="${appraisalQuarterFinance == qValue}">
                                                    <option value="${qValue}" selected="selected">Q${qValue}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${qValue}">Q${qValue}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                </select>--%>
                                    Year :
                            <select name="appraisalYear" id="appraisalYear" class="required" style="width: 5em" onchange="fetchAppraiseeListFinance()">
                                    <c:forEach items="${yearDataFinance}" var="year">
                                               <c:choose>
                                                <c:when test="${appraisalYearFinance == year}">
                                                    <option value="${year}" selected="selected">${year}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${year}">${year}</option>
                                                </c:otherwise>
                                            </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                 </table>
          </div>
                 
                 <div class="formarea">
                 
                        <c:if test="${fn:length(appraiseesdetailsFinance)>0}">
                            <table width="100%">
                                        <thead>
                                            <tr class="formarea_header">
                                                    <th rowspan="2">Appraisee ID</th>
                                                    <th rowspan="2">Appraisee Name</th>
                                                    <th rowspan="2" style="text-align: center" >Final Reviewer Rating</th>
                                             </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${appraiseesdetailsFinance}" var="tes" varStatus="status">
                                        <c:if test="${(status.index%2)==0}"><c:set var="rowClass" value="formarea_row1"></c:set></c:if>
                                        <c:if test="${(status.index%2)!=0}"><c:set var="rowClass" value="formarea_row2"></c:set></c:if>
                                        <tr class="${rowClass}">
                                            <%--${tes.normalisedReviewerRating}--${tes.sendbackbyhr}--%>
                                                <td><a href="javascript:void(0)" onclick="setAppraiseeData(${tes.appraiseeId},${tes.bandId},${appraisalQuarterFinance},${appraisalYearFinance},'${tes.appraiserName}',${tes.appraiseeEmpId},${tes.departmentId})">${tes.appraiseeEmpId}</a></td>
                                                <td><span class="displayText">${tes.appraiseeName}</span></td>
                                                <%--<c:if test="${tes.normalisedReviewerRating==0}">
                                                <td style="text-align: center"><span class="displayText">
                                                    ${tes.reviewerRating}</span>
                                                    <input type="hidden" value="${tes.reviewerRating}" class="normalisedReviewerRatings"/>
                                                </td>
                                                </c:if>
                                                <c:if test="${tes.normalisedReviewerRating != 0 && tes.correctedNormalizerRating < 1}">
                                                    <td style="text-align: center"><span class="displayText">
                                                     ${tes.normalisedReviewerRating}</span>
                                                    <input type="hidden" value="${tes.normalisedReviewerRating}" class="normalisedReviewerRatings"/>
                                                    </td>
                                                </c:if>
                                                <c:if test="${tes.normalisedReviewerRating != 0 && tes.correctedNormalizerRating > 0}">
                                                    <td style="text-align: center"><span class="displayText">
                                                     ${tes.correctedNormalizerRating}</span>
                                                    </td>
                                                </c:if>--%>
                                                <c:if test="${tes.sendbackbyhr == 0}">
                                                    <td style="text-align: center">
                                                                <span class="displayText">
                                                                    ${tes.normalisedReviewerRating}
                                                                </span>
                                                    </td>
                                                </c:if>
                                                <c:if test="${tes.sendbackbyhr == 1}">
                                                            <td style="text-align: center">
                                                                <span class="displayText">
                                                                    ${tes.correctedNormalizerRating}
                                                                </span>
                                                            </td>
                                                </c:if>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <td style="text-align: center" colspan="3" align="center"><input type="submit" name="button" class="buttons exportbutton" id="saveRating" value="Export to Excel" /></td>
                                        </tr>
                                        </tbody>
                                       </table>
                                    </div>
                        </form>
               <form id="appraiseeInfo" name="appraiseeInfo" action="" method="post">
                     <input type="hidden" name="appraiseeIdSelected" id="appraiseeIdSelected">
                     <input type="hidden" name="appraiseeEmpIdSelected" id="appraiseeEmpIdSelected">
                     <input type="hidden" name="bandIdSelected" id="bandIdSelected">
                     <input type="hidden" name="appraisalQuarterSelected" id="appraisalQuarterSelected">
                     <input type="hidden" name="appraisalYearSelected" id="appraisalYearSelected">
                     <input type="hidden" name="appraiserNameSelected" id="appraiserNameSelected">
                     <input type="hidden" name="departmentIdSelected" id="departmentIdSelected">
                     <input type="hidden" name="appraiseeInfoReviewer" id="appraiseeInfoReviewer">
               </form>
</c:if>
                        <c:if test="${fn:length(appraiseesdetailsFinance)==0}">
                            <table width="100%">
                                <thead>
                                    <tr class="formarea_header" >
                                        <td style="text-align: center">-- Employee Data Not Available For This Appraisal Year --</td>
                                    </tr>
                                </thead>
                            </table>
                        </c:if>
</body>

</html>