<%-- .
    Document   : review.jsp
    Created on : Nov 15, 2010, 11:37:08 AM
    Author     : Phaneendra & Hariharan C
--%>

<%--<%@page import="com.defiance.ideal.managers.dto.ManagerDTO"%>--%>
<%@include file="../../../../../header.jsp" %>
<head>
    <title>Reviewer</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/highcharts.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/float.js"></script>
    <script type="text/javascript">
        function submitFilter(){
            document.getElementById("saveReview").action = 'begin.do';
            document.getElementById("saveReview").submit();
        }
        function showHideChart(btnObj){
            if(btnObj.value=='Hide Chart'){
                $("#chartContainer").hide();
                $("#showChart").show();
            }else if(btnObj.value=='Show Chart'){
                $("#showChart").hide();
                $("#chartContainer").show();
            }
       }
      function validateReviewerComments(){
        var errorFlag = 0;
        var scrollFlag = 0;
        $(".reviewerRatings").each(
            function(){
              <%--if(this.value==5 || this.value==1){  Change Request By Varun --%>
              if(this.value!=$("#appraiserRatings"+this.id+"").val()){
                if($("#justifyRating"+this.id+"").val()=="" && document.getElementById(this.id).disabled==false){
                        document.getElementById(this.id+"Div").innerHTML="";
                        document.getElementById("justifyRating"+this.id+"Div").innerHTML="Missing : Reason for change of rating";
                         if(scrollFlag==0){$.scrollTo(this,{speed:1000,easing:'swing'});}
                        errorFlag++;
                        scrollFlag++;
                 }else if($("#justifyRating"+this.id+"").val().length>=250){
                        document.getElementById("justifyRating"+this.id+"Div").innerHTML="Justification Comments should be below 250 Characters";
                        errorFlag++;
                 }else{
                        document.getElementById(this.id+"Div").innerHTML="";
                        document.getElementById("justifyRating"+this.id+"Div").innerHTML="";
                 }
             }else if((this.value=="") && document.getElementById(this.id).disabled==false){
                       if(scrollFlag==0){$.scrollTo(this,{speed:1000,easing:'swing'});}
                       document.getElementById(this.id+"Div").innerHTML="Select Ratings";
                       scrollFlag++;
                       errorFlag++;
                }else{
                       document.getElementById(this.id+"Div").innerHTML="";
                }
           }
       )
           if(errorFlag==0){
               $("#buttonValueChart").val($("#chartContainer").is(':visible'));
                return true;
            }else{
                return false;
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
         $("#appraiseeInfoReviewer").val("reviewer");
         $("#departmentIdSelected").val(departmentId);
         document.getElementById('appraiseeInfo').action="fetchAppraiseeKraData.do";
         $("#appraiseeInfo").submit();
     }
    </script>
    <style type="text/css">
            #chartContainer{
                cursor: move;
                background-color: #ffffff;
                width:500px;
                height:500px;
                margin: auto;
            }
    </style>
    <script type="text/javascript">
        function filterByAppraiser(){
            document.getElementById("saveReview").action = "begin.do";
            document.getElementById("saveReview").submit();
        }
    </script>
              <style type="text/css">
.formarea{
    overflow:auto;
}
</style>
</head>
<body>
    <%--<c:out value="${appraiseesCount}"></c:out>--%>
    <div class="submenuwrap">
     <%@include file="../../../../../menu.jsp" %>
       <c:if test="${!empty(savedMessage)}">
                 <center><div class="savedmsgalert"><font color="green">${savedMessage}</font></div></center>
       </c:if>
       <c:if test="${!empty(submittedMessage)}">
                 <center><div class="savedmsgalert"><font color="green">${submittedMessage}</font></div></center>
       </c:if>
     <div class="contentwrap">
     <div class="contentBand">Reviewer</div>

    <c:set var="submitCount" value="0"></c:set>
    <c:set var="appraiserSubmitCount" value="0"></c:set>
    <c:set var="reviewerSubmitCount" value="0"></c:set>
    <c:set var="appraiseeCount" value="0"></c:set>
    <c:set var="buttonsDisplay" value="0"></c:set>
    <form action="reviewerAction.do" method="POST" name="saveReview" id="saveReview">
          
              <%int count = 0;%>
              <div class="contentarea">
                  <table id="apprMaster" cellpadding="2" cellspacing="0" align="center">
                    <tr align="center">
                        <td bgcolor="#ffffff">   Appraiser: </td><td>
                             <select name="myappraiserId" onchange="filterByAppraiser()">
                                <option value="">--All-</option>
                                <c:forEach var="selectAppraiser" items="${appraiserList}">
                                        <option ${(selectAppraiser.appraiserId==selectedAppraiser)?'selected':'' } value="${selectAppraiser.appraiserId}">${selectAppraiser.appraiserName}</option>
                                    </c:forEach>
                                </select>  </td><%--<td>
                                   Quarter : </td><td>
                                      <select name="appraisalQuarter" id="appraisalQuarter" class="required" style="width: 4em" onchange="filterByAppraiser()" >
<!--                                          <option>--Select Quarter--</option>-->
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
                                </select> </td>--%><td>
                                   Appraisal Year : </td><td>
                            <select name="appraisalYear" id="appraisalYear" class="required" style="width: 5em" onchange="filterByAppraiser()" >
<!--                                    <option>--Select Year--</option>-->
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
                                    <%--<input class="buttons submitbutton" type="button" value="Fetch" style="width: 5em" name="button" onclick="submitFilter();" >--%>
                            </td>
                        </tr>
                 </table>
              </div>
<c:if test="${fn:length(appraiseesdetails)>0}">
                        <div class="formarea">
                            <table width="100%" >
                                        <thead>
                                            <tr class="formarea_header">
                                                <th rowspan="2">Appraisee ID</th>
                                                <th rowspan="2">Appraisee Name</th>
                                                <th rowspan="2">Appraiser ID</th>
                                                <th rowspan="2">Appraiser Name</th>
                                                <%--<th colspan="4" class="ratingAlign">QPD Status</th>--%>
                                                <th colspan="4" class="ratingAlign">Appraisal Status</th>
                                                <th colspan="2" class="ratingAlign" rowspan="2">Appraiser Rating</th>
                                                <th colspan="2" rowspan="2" width="100px">Appraiser Comments</th>
                                                <th colspan="2" rowspan="2" width="100px">Appraiser Promotion Recommended</th>
                                                <th colspan="2" rowspan="2">Reviewer Promotion Recommonded</th>
                                                <th colspan="2" class="ratingAlign" rowspan="2">Reviewer Rating</th>
                                                <c:if test="${tes.sendbackbyhr == 1}"><th colspan="2" rowspan="2">Final Reviewer Rating</th></c:if>
                                                <th colspan="2" rowspan="2">Justify Rating</th>
                                            </tr>
                                            <tr  class="formarea_header">
                                                <th class="ratingAlign">Appraisee</th>
                                                <th class="ratingAlign">Appraiser</th>
                                                <th class="ratingAlign">Reviewer</th>
                                                <th class="ratingAlign">Final Reviewer</th>
                                            </tr>
                                        </thead>
                                        <%--<c:out value="${fn:length(appraiseesdetails)}"></c:out>--%>
                                        <c:set value="0" var="rowDisplay"></c:set>
<%--                                        <c:forEach items="${appraiseesdetails}" var="appraiserStatusCheck">
                                            <c:if test="${appraiserStatusCheck.submitStatus>=4}">
                                                <c:set var="appraiserSubmitCount" value="${appraiserSubmitCount+1}"></c:set>
                                            </c:if>
                                        </c:forEach>
                                        <c:if test="${appraiseesCount != appraiserSubmitCount}">
                                                <c:set value="disabled" var="ratingDisabled"></c:set>
                                                <c:set value="readonly" var="reviewerCommentsReadonly"></c:set>
                                        </c:if>--%>

                                        <c:forEach items="${appraiseesdetails}" var="tes" varStatus="status">
                                        <%--<c:out value="${tes.submitStatus}"></c:out>--%>
                                        <c:set var="appraiseeCount" value="${appraiseeCount+1}"></c:set>
                                        <c:set value="${tes.submitStatus}" var="appraiseeCurrentStatus"></c:set>
                                        <c:choose>
                                            <c:when test="${appraiseeCurrentStatus<4 || appraiseeCurrentStatus >=6}">
                                                          <c:set value="disabled" var="reviewerDisabled"></c:set>
                                                          <c:set value="readonly" var="reviewerReadOnly"></c:set>
                                            </c:when>
                                            <c:otherwise>
                                                      <c:set value="" var="reviewerDisabled"></c:set>
                                                      <c:set value="" var="reviewerReadOnly"></c:set>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${reviewerDisabled==''}">
                                            <c:set var="buttonsDisplay" value="show"></c:set>
                                        </c:if>
<%--<c:out value="${reviewerDisabled}-${appraiseeCurrentStatus}"></c:out>--%>


                                            <c:choose>
                                            <c:when test="${(status.index+1)%2==0}">
                                                <c:set value="formarea_row2" var="rowDisplay"></c:set>
                                            </c:when>
                                            <c:otherwise>
                                                <c:set value="formarea_row1" var="rowDisplay"></c:set>
                                            </c:otherwise>
                                            </c:choose>
                                            <tbody>
                                            <tr class="${rowDisplay}">
                                                <td><a href="javascript:void(0)" onclick="setAppraiseeData(${tes.appraiseeId},${tes.bandId},${appraisalQuarter},${appraisalYear},'${tes.appraiserName}',${tes.appraiseeEmpId},${tes.departmentId})">${tes.appraiseeEmpId}</a>
                                                    <input type="hidden" value="${tes.appraiseeId}" name="appraiseeIdForm[]" ${reviewerDisabled}>
                                                    <input type="hidden" value="${tes.normalizerName}" name="normalizerNameForm[]" ${reviewerDisabled}>
                                                    <input type="hidden" value="${tes.normalizerEmail}" name="normalizerEmailForm[]" ${reviewerDisabled}>
                                                   <c:if test="${tes.submitStatus>=4}">
                                                       <c:set var="submitCount" value="${submitCount+1}"></c:set>
                                                    </c:if>
                                                   <c:if test="${tes.submitStatus>=6}">
                                                        <c:set value="${reviewerSubmitCount+1}" var="reviewerSubmitCount"></c:set>
                                                   </c:if>
                                                </td>
                                                <td><span class="displayText">${tes.appraiseeName}</span></td>
                                                <td><span class="displayText">${tes.appraiserEmpId}</span></td>
                                                <td><span class="displayText">${tes.appraiserName}</span></td>
                                                <td><span class="displayText"><c:choose>
                                                    <c:when test="${tes.submitStatus >=2}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==1}"><font color="red">Sent back to Appraisee</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                    </c:choose></span></td>
                                                <td><span class="displayText"><c:choose><c:when test="${tes.submitStatus >=4}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==3}"><font color="red">Send Back to Appraiser</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                    </c:choose></span></td>
                                                <td><span class="displayText"><c:choose>
                                                    <c:when test="${tes.submitStatus >=6}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==5}"><font color="red">Send Back to Reviewer</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                    </c:choose></span></td>
                                                <td><span class="displayText"><c:choose>
                                                    <c:when test="${tes.submitStatus >=8}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==7}"><font color="red">Sent back for correction</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                    </c:choose></span></td>
                                                <td colspan="2" style="text-align: center;">
                                                       <span class="displayText">${tes.appraiserRating}</span>
                                                        <input type="hidden" id="appraiserRatings${tes.appraiseeEmpId}" value="${tes.appraiserRating}" class="appraiserRatings" ${reviewerDisabled}/>
                                                        <input type="hidden" name="submitStatusForm[]" value="${tes.submitStatus}" ${reviewerDisabled}/>
                                                    </td>
                                                    <td colspan="2" width="100px"><span class="displayText">${tes.appraiserComments}</span></td>
                                                    <td colspan="2" width="100px"><span class="displayText">
                                                            <c:choose>
                                                                <c:when test="${tes.appraiserPromotionRecommeded==1}">
                                                                    <c:set value="Yes" var="appPromoRec" />
                                                                </c:when>
                                                                <c:when test="${tes.appraiserPromotionRecommeded==0}">
                                                                    <c:set value="No" var="appPromoRec" />
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:set value="" var="appPromoRec" />
                                                                </c:otherwise>
                                                            </c:choose>
                                                            ${appPromoRec}</span></td>
                                                    <td colspan="2" width="100px">
                                                        <c:set value="" var="appPromStatusNo" />
                                                        <c:set value="" var="appPromStatusYes" />
                                                        <c:choose>
                                                            <c:when test="${tes.reviewerPromotionRec==1}">
                                                                <c:set value="selected" var="appPromStatusYes" />
                                                            </c:when>
                                                            <c:when test="${tes.reviewerPromotionRec==0}">
                                                                <c:set value="selected" var="appPromStatusNo" />
                                                            </c:when>
                                                            <c:otherwise>
                                                                <c:set value="selected" var="appPromStatus" />
                                                            </c:otherwise>
                                                        </c:choose>
                                                        <select style="width: auto;text-align: center;" name="reviewerPromotionRecommended[]" class="required" onchange="removeErrorDiv(this.id,'Select Promotions');" id="${tes.appraiseeEmpId}" ${reviewerDisabled}>
                                                            <option value="">--Select Promo--</option>
                                                            <option value="1" ${appPromStatusYes} >Yes</option>
                                                            <option value="0" ${appPromStatusNo}>No</option>
                                                        </select>
                                                         <%--<input type="radio" name="reviewerPromotionRecommended${status.index}" id="reviewerPromotionRecommended${tes.appraiseeEmpId}"  value="1" ${reviewerDisabled} ${appPromStatusYes}>Yes
                                                         <input type="radio" name="reviewerPromotionRecommended${status.index}" id="reviewerPromotionRecommended${tes.appraiseeEmpId}"  value="0" ${reviewerDisabled} ${appPromStatusNo}>No--%>
                                                      <div class="customError" id="reviewerPromotionRecommded${tes.appraiseeEmpId}Div"></div>
                                                  </td>
                                                    <td colspan="2" >
                                                                <select style="width: auto;text-align: center;" name="reviewerRatingForm[]" class="reviewerRatings" onchange="calculateRatings();removeErrorDiv(this.id,'Select Ratings');" id="${tes.appraiseeEmpId}" ${reviewerDisabled}>
                                                                    <option value="">-- Select Rating --</option>
                                                                        <c:forEach begin="1" end="5" var="rateValue">
                                                                            <c:choose>
                                                                                <c:when test="${tes.appraiserRating == rateValue && tes.reviewerRating<1}">
                                                                                    <option value="${rateValue}" selected="selected">${rateValue}</option>
                                                                                </c:when>
                                                                                <c:when test="${tes.reviewerRating == rateValue && tes.reviewerRating>0}">
                                                                                  <option value="${tes.reviewerRating}" selected="selected">${tes.reviewerRating}</option>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <option value="${rateValue}">${rateValue}</option>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </c:forEach>
                                                                </select>
                                                                <div class="customError" id="${tes.appraiseeEmpId}Div"></div>
                                                            </td>
                                                            <td>
                                                                <input type="text" onchange="removeErrorDiv(this.id,'Missing : Reason for change of rating')" name="justifyRatingReviewer[]" id="justifyRating${tes.appraiseeEmpId}" maxlength="250" class="justify" value="${tes.reviewerComments}" ${reviewerDisabled}>
                                                                <div class="customError" id="justifyRating${tes.appraiseeEmpId}Div"></div>
                                                            </td>
                                                     <%--<td>
                                                         <input type="radio" name="reviewerPromotionRecommded[]" id="reviewerPromotionRecommded${tes.appraiseeEmpId}"  value="1" ${reviewerDisabled}>Yes
                                                         <input type="radio" name="reviewerPromotionRecommded[]" id="reviewerPromotionRecommded${tes.appraiseeEmpId}"  value="0" ${reviewerDisabled}>No
                                                      <div class="customError" id="reviewerPromotionRecommded${tes.appraiseeEmpId}Div"></div>
                                                  </td>--%>
                                                  
                                                </tr>
                                            </tbody>
                                        </c:forEach>
                                    </table>
                                </div>


                                        <table width="100%">
                                               <tr>
                                                   <td style="text-align: center">
                                                       <input class="buttons" type="button" id="showChart" onclick="showHideChart(this)" value="Show Chart" style="display:none"/>
                                                      <div id="chartContainer">
                                                            <h3 style="padding:0px; margin: 0px;color: #3E576F;font-family:Arial;font-size: 12px">
                                                                <center>
                                                                    <input type="button" class="buttons" onclick="showHideChart(this)" id="hideChart" value="Hide Chart"/><br>
                                                                    <%--<font size="4" >QPD  rating</font>--%>
                                                                    <font size="4" >Appraisal  rating</font>
                                                                    <br>
                                                                </center>
                                                            </h3>
                                                        <div id="appraiseeChart" class="appraiseeChart" ></div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                    <table width="100%">
                        <tr><td style="text-align: center;"><input type="submit" name="button" id="saveRating" class="buttons exportbutton" value="Export to Excel"  />
                                <c:if test="${submitCount >= 1 && reviewerSubmitCount < appraiseeCount && buttonsDisplay=='show'}">
                                    <input type="submit" name="button" id="saveRating" class="buttons savebutton" value="Save" onclick="return validateReviewerComments()" />
                                    <input style="width: 190px;" type="submit" name="submitToNormalizer" id="SubmitToNormalizer" class="buttons submitbutton" value="Submit for Final Review" onclick="return validateReviewerComments()" />
                                    <%--<input class="buttons resetbutton styledButton" value="Reset" type="reset" />--%>
                                </c:if>
                        </td></tr>
                    </table>
            <input type="hidden" value="" name="buttonValueChart" id="buttonValueChart"/>
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

<script type="text/javascript">
    var ratingJustify=0;
    var chart1; // globally available

    var appraiserRating = new Array(0,0,0,0,0);
    var reviewerRating = new Array(0,0,0,0,0);
    //var normalisedReviewer = new Array(0,0,0,0,0);
    function justifyRatings(){
        $(".reviewerRatings").each(
        function(){
            <%--alert(this.id);--%>
            //justifyRating="#justifyRating"+this.id;
            <%--if(this.value==5 || this.value==1){ Change Request By Varun --%>
            if(this.value!=""){
                //$("#justifyRating"+this.id+"").attr("disabled", "");
                $("#justifyRating"+this.id+"").show();
                $("#justifyRating"+this.id+"Div").show();
                //$("#"+this.id+"Div").html("");
            }else{
                $("#justifyRating"+this.id+"").hide();
                $("#justifyRating"+this.id+"").val("");
                //$("#"+this.id+"Div").html("");
                $("#justifyRating"+this.id+"Div").html("");
                }
            }
        )
    }




            function removeErrorDiv(elementFocused,messageToDisplay){
                
                            if(elementFocused.value==""){
                                document.getElementById(elementFocused+"Div").innerHTML = messageToDisplay;
                            }else{
                                document.getElementById(elementFocused+"Div").innerHTML = "";
                            }
                            
            }


            function calculateRatings(){
                
            justifyRatings();
             var totalAppraisees = $(".appraiserRatings").length;
             var appraiserRatingsArray = new Array(0,0,0,0,0);
             var reviewerRatingsArray = new Array(0,0,0,0,0);
             $(".appraiserRatings").each(
                                function(){
                                    appraiserRatingsArray[(this.value-1)]++;
                                }
               )
             $(".reviewerRatings").each(
                                function(){
                                    reviewerRatingsArray[(this.value-1)]++;
                                }
               )


            //alert(appraiserRatingsArray+"\n"+reviewerRatingsArray+"\n"+totalAppraisees);
            newAppraiserRatingsArray = new Array();
            newReviewerRatingsArray = new Array();
            for(i=0;i<5;i++){
                newAppraiserRatingsArray[i]=parseInt((((appraiserRatingsArray[i])/totalAppraisees)*100),10);
                newReviewerRatingsArray[i]=parseInt((((reviewerRatingsArray[i])/totalAppraisees)*100),10);
            }

            //alert(newAppraiserRatingsArray+"\n"+newReviewerRatingsArray+"\n"+totalAppraisees);

             appraiserRating = newAppraiserRatingsArray.reverse();
             reviewerRating = newReviewerRatingsArray.reverse();

            //alert(appraiserRating+"\n"+reviewerRating+"\n"+totalAppraisees);
            $(document).ready(function(){
                     chart1 = new Highcharts.Chart({
                     chart: {
                        renderTo: 'appraiseeChart',
                        defaultSeriesType: 'column'
                     },
                     title: {
                        text: ''
                     },
                     xAxis: {
                                categories: ['5','4' ,'3', '2','1'],
                                title: {
                                    text: 'Status'
                                },labels: {
                                    //rotation: -10,
                                    align: 'right',
                                    style: {
                                        font: 'bold 10px Verdana'
                                    }
                                }
                      },
                      labels: {

                          align: 'right',
                          style: {font: 'bold 10px Verdana'}
                                }
                     ,
                     yAxis: {
                                title: {
                                    text: 'Rating'
                                },
                                labels: {
                                        formatter: function() {
                                          return this.value   +'%';
                                        }
                                    }

                      },
                      tooltip: {

                                formatter: function() {
                                    return '<b>'+ this.series.name +'</b><br/>'+
                                        this.x +': '+ this.y +'%';
                                }
                      },
                      credits: {
                      enabled: false
                      },
                      series: [
                      {name:'Appraiser Rating',data: appraiserRating},
                      {name: 'Reviewer Rating',data: reviewerRating} ,
                      //{name: 'Normalised Reviewer Rating',data:normalisedReviewer},
                      {
                      type: 'spline',
                      name: 'Normal Curve',
                      data: [10,35,40,10,5],
                      dataLabels: {enabled: true}
                      }]
                  });
               });




            //$('#chartContainer').makeFloat({x:"current",y:"current"});
            $('#chartContainer').draggable();
            //$('#chartContainer').resizable({ alsoResize: '.appraiseeChart' });
            }
            calculateRatings();
            <c:if test="${param.buttonValueChart==false}">
                    $("#chartContainer").hide();
                    $("#showChart").show();
            </c:if>
                $("#saveReview").submit(function() {
                    $(".buttons").each(
                    function(){$(this).hide();}
                );
                    return true;
                });
            </script>
            </c:if>

            <c:if test="${fn:length(appraiseesdetails)==0}">
                <div class="formarea">
                    <table width="100%">
                            <tr  class="formarea_header">
                                <td align="center" style="text-align: center">
                                    -- Employee data not available for this appraisal --${appraisalYear}
                                </td>
                            </tr>
                        </table>
                </div>
            </c:if>

        </div>
    </div>
</body>
</html>
