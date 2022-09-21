<%--
    Document   : MyAppraisal
    Created on : Nov 11, 2010, 11:34:34 AM
    Author     : Administrator
--%>

<%@include file="../../../../../header.jsp" %>
    <head>
        <title>Appraisee List</title>
        <script type="text/javascript">
            function sendAppraiseeData(appraiseeId, bandId,submitStatus,appraiseeName,joiningDate,workStatus,appraiseeNumber,qpdId){
                $("#bandId").val(bandId);
                $("#appraiseeId").val(appraiseeId);
                $("#appraiseeSubmitStatus").val(submitStatus);
                $("#appraiseeName").val(appraiseeName);
                $("#appraiseeJoiningDate").val(joiningDate);
                $("#appraiseeStatus").val(workStatus);
                $("#appraiseeNumber").val(appraiseeNumber);
                $("#qpdId").val(qpdId);
                $("#appraiseeListForm").submit();
                
            }
            function fetchAppraiseeListAppraiser(){
                document.getElementById('appraiseeHeader').action="begin.do";
                document.getElementById('appraiseeHeader').submit();
            }
        </script>
   </head>
    <body>
        <div class="submenuwrap">
           <%@include file="../../../../../menu.jsp" %>
           <div class="contentwrap">
                    <div class="contentBand">Appraisees List</div>
            <div class="contentarea">
            <form action="begin.do" method="post" name="appraiseeHeader" id="appraiseeHeader" >
                <table align="center">
                     <tr>
                         <%--<td>Quarter:</td>
                         <td><select name="appraisalQuarter" id="appraisalPeriod" class="required" style="width: 4em" onchange="fetchAppraiseeListAppraiser()">
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
                                </select></td>--%>
                            <td>Appraisal Year:</td>
                            <td>
                                <select name="appraisalYear" id="appraisalYear" class="required" style="width: 5em" onchange="fetchAppraiseeListAppraiser()">
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
                            </td>
                     </tr>
                     </table>
              </form>
            </div>
             <div class="formarea">
                <c:if test="${!empty(appraiseeData)}">
                 <table width="100%">
                     <tr class="formarea_header">
                         <th>Appraisee Number</th>
                         <th>Appraisee Name</th>
                         <th>Appraisee Submit Status</th>
                         <th>Appraiser Submit Status</th>
                         <th>Reviewer Submit Status</th>
                     </tr>
                     <c:set value="0" var="rowDisplay"></c:set>
                     <c:forEach items="${appraiseeData}" var="datum" varStatus="item">
                         <c:choose>
                             <c:when test="${(item.index+1)%2==0}">
                                 <c:set value="formarea_row2" var="rowDisplay"></c:set>
                             </c:when>
                             <c:otherwise>
                                 <c:set value="formarea_row1" var="rowDisplay"></c:set>
                             </c:otherwise>
                        </c:choose>
                         <tr class="${rowDisplay}">
                             <td style="text-align: center">
                                 <c:choose>
                                     <c:when test="${datum.submitStatus >=2}">
                                     <a href="javascript:void(0)" onclick="sendAppraiseeData(${datum.appraiseeId},${datum.bandId},${datum.submitStatus},'${datum.appraiseeName}','${datum.appraiseeJoiningDate}','${datum.appraiseeStatus}','${datum.appraiseeNumber}','${datum.qpdId}')">${datum.appraiseeNumber}</a>
                                     </c:when>
                                     <c:otherwise>
                                         ${datum.appraiseeNumber}
                                     </c:otherwise>
                                 </c:choose>
                             </td>
                             <td><span class="displayText">${datum.appraiseeName}</span></td>
                             <td class="centerAlign">
                                 <span class="displayText">
                                 <c:choose>
                                     <c:when test="${datum.submitStatus >=2}">
                                          <font color="green">Submitted</font>
                                     </c:when>
                                     <c:when test="${datum.submitStatus ==1}">
                                          <font color="red">Sent Back to Appraisee</font>
                                     </c:when>
                                     <c:otherwise>
                                         <font color="red">Not Submitted</font>
                                     </c:otherwise>
                                 </c:choose>
                                </span>
                             </td>
                             <td class="centerAlign">
                                 <span class="displayText">
                                 <c:choose>
                                     <c:when test="${datum.submitStatus >=4}">
                                         <font color="green">Submitted</font>
                                     </c:when>
                                     <c:when test="${datum.submitStatus ==3}">
                                          <font color="red">Send Back to Appraiser</font>
                                     </c:when>
                                     <c:otherwise>
                                        <font color="red">Not Submitted</font>
                                     </c:otherwise>
                                 </c:choose>
                                </span>
                             </td>
                             <td class="centerAlign">
                                 <span class="displayText">
                                 <c:choose>
                                     <c:when test="${datum.submitStatus >=6}">
                                                    <font color="green">Submitted</font>
                                     </c:when>
                                     <c:when test="${datum.submitStatus ==5}">
                                          <font color="red">Send Back to Reviewer</font>
                                     </c:when>
                                     <c:otherwise>
                                        <font color="red">Not Submitted</font>
                                     </c:otherwise>
                                 </c:choose>
                                </span>
                             </td>
                         </tr>
                     </c:forEach>
                 </table>
                 </c:if>
                <c:if test="${empty(appraiseeData)}">
                    
                    <table width="100%">
                        <tr class="formarea_header">
                            <td colspan="8" align="center" style="text-align: center">
                    <%---- Employee data not available for quarter Q${appraisalQuarter}-${appraisalYear} ----%>
                    -- Employee Data Not Available For The Appraisal Year ${appraisalYear}--
                            </td>
                        </tr>
                    </table>
                            
                </c:if>
                 <form action="fetchAppraiseeData.do" method="POST" name="appraiseeListForm" id="appraiseeListForm">

                     <input type="hidden" name="qpdId" value="" id="qpdId">
                     <input type="hidden" name="appraiseeNameForm" value="" id="appraiseeName">
                     <input type="hidden" name="appraiseeJoiningDateForm" value="" id="appraiseeJoiningDate">
                     <input type="hidden" name="appraiseeStatusForm" value="" id="appraiseeStatus">
                     <input type="hidden" name="appraiseeNumberForm" value="" id="appraiseeNumber">
                     <input type="hidden" name="bandIdForm" value="" id="bandId">
                     <input type="hidden" name="appraiseeIdForm" value="" id="appraiseeId">
                     <input type="hidden" name="appraisalYearForm" value="${appraisalYear}" id="appraisalYear">
                     <input type="hidden" name="appraisalQuarterForm" value="${appraisalQuarter}" id="appraisalQuarter">
                     <input type="hidden" name="appraiseeSubmitStatusForm" value="" id="appraiseeSubmitStatus">
                     <input type="hidden" name="appraiseeNameForm" value="" id="appraiseeName">
                 </form>
             </div>
        </div>
        </div>
    </body>
</html>
