<%-- .
    Document   : review.jsp
    Created on : Nov 15, 2010, 11:37:08 AM
    Author     : Phaneendra & Hariharan C
--%>

<%@include file="../../../../../header.jsp" %>

<head>
    <title>Final Reviewer</title>
    <meta http-equiv="X-UA-Compatible" content="IE=8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/highcharts.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
                $("#appraiserForm").validate({
                    errorElement:"div",
                    errorClass:"customError"
                });
        });

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

        function validateNormalizerComments(){
            var errorFlag = 0;
            var scrollFlag = 0;
            $(".normalizerRatings").each(
            function(){

                <%--if(this.value==5 || this.value==1 && document.getElementById(this.id).disabled==false){ Change Request By Varun--%>

                 if( ($("#justifyRating"+this.id+"")) && ($("#reviewerRatings"+this.id+"").val()!=this.value)){

                        if(this.id!="" && document.getElementById(""+this.id) && !document.getElementById(this.id).disabled){
                            if( $("#justifyRating"+this.id+"").val()=="" && document.getElementById(this.id).disabled==false){
                                document.getElementById("justifyRating"+this.id+"Div").innerHTML="Missing : Reason for change of rating";
                                if(scrollFlag==0){$.scrollTo(this,{speed:1000,easing:'swing'});}
                                scrollFlag++;
                                errorFlag++;
                            }else if($('#justifyRating'+this.id+'') && ($("#justifyRating"+this.id+"").val().length>=250)){
                                document.getElementById("justifyRating"+this.id+"Div").innerHTML="Justification Comments should be below 250 Characters";
                                if(scrollFlag==0){$.scrollTo(this,{speed:1000,easing:'swing'});}
                                scrollFlag++;
                                errorFlag++;
                         }else{
                                document.getElementById(this.id+"Div").innerHTML="";
                                document.getElementById("justifyRating"+this.id+"Div").innerHTML="";
                         }
                    }

                }else if((this.value=="") && document.getElementById(this.id).disabled==false){
                    document.getElementById(this.id+"Div").innerHTML="Select Ratings";
                    if(scrollFlag==0){$.scrollTo(this,{speed:1000,easing:'swing'});}
                    scrollFlag++;
                    errorFlag++;
                }else{
                    if(document.getElementById(this.id+"Div")){document.getElementById(this.id+"Div").innerHTML="";}
                    if(document.getElementById("justifyRating"+this.id+"Div")){document.getElementById("justifyRating"+this.id+"Div").innerHTML="";}
                    //$("#justifyRating"+this.id+"").val("");
                    //$("#justifyRating"+this.id+"").hide();
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




        function validateReviewerComments1(){
        var errorFlag = 0;
        $(".normalizerRatings").each(
            function(){
            if(this.value==5 || this.value==1){
                if($("#justifyRating"+this.id+"").val()=="" && document.getElementById(this.id).readonly==false){
                        document.getElementById(this.id+"Div").innerHTML="";
                        document.getElementById("justifyRating"+this.id+"Div").innerHTML="Required Data Missing";
                        errorFlag++;
                 }else if($("#justifyRating"+this.id+"").val().length>=250){
                        document.getElementById("justifyRating"+this.id+"Div").innerHTML="Justification Comments should be below 250 Characters";
                        errorFlag++;
                 }else{
                        document.getElementById(this.id+"Div").innerHTML="";
                        document.getElementById("justifyRating"+this.id+"Div").innerHTML="";
                 }
            }else if((this.value=="" && document.getElementById(this.id).disabled==false) && document.getElementById(this.id).disabled==false){
                       document.getElementById(this.id+"Div").innerHTML="Select Ratings";
                       errorFlag++;
                }else{
                       document.getElementById(this.id+"Div").innerHTML="";
                }
           }
       )

           if(errorFlag==0){
                return true;
            }else{
                return false;
            }
     }



        function validateNormalizerCorrected(){
            var errorFlag = 0;
            $(".normalizerRatingsNew").each(
            function(){
                if(this.value==""){
                    document.getElementById(this.id+"Div").innerHTML="Select Ratings";
                    errorFlag++;
                }else{
                    if(document.getElementById(this.id+"Div")){document.getElementById(this.id+"Div").innerHTML="";}
                    //if(document.getElementById("justifyRating"+this.id+"Div")){document.getElementById("justifyRating"+this.id+"Div").innerHTML="";}
                    //$("#justifyRating"+this.id+"").val("");
                    //$("#justifyRating"+this.id+"").hide();
                }
                }
            )
            if(errorFlag==0){
                return true;
            }else{
                return false;
            }
        }

        function setAppraiseeDataNor(appraiseeId,bandId,appraisalQuarter,appraisalYear,appraiserName,appraiseeEmpId,departmentId){
            //alert(appraiseeId+appraiserName+appraisalQuarter+appraisalYear)
            $("#appraiseeIdSelected").val(appraiseeId);
            $("#bandIdSelected").val(bandId);
            $("#appraisalQuarterSelected").val(appraisalQuarter);
            $("#appraisalYearSelected").val(appraisalYear);
            $("#appraiserNameSelected").val(appraiserName);
            $("#appraiseeEmpIdSelected").val(appraiseeEmpId);
            $("#appraiseeInfoReviewer").val("normalizer");
            $("#departmentIdSelected").val(departmentId);
            $("#appraiseeInfo").submit();
        }

        function changeAppraiserAndReviewer(){
            document.getElementById("saveReview").action = "beginNormalizer.do";
            document.getElementById("saveReview").submit();
        }

        function removeErrorDiv(elementFocused,messageToDisplay){
                if(elementFocused.value==""){
                    document.getElementById(elementFocused+"Div").innerHTML = messageToDisplay;
                }else{
                    document.getElementById(elementFocused+"Div").innerHTML = "";
            }
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
    <style type="text/css">
    .contentwrap{
        width:auto;
        margin-left: 10px;
        margin-right: 10px;
}
.submenuwrap{
	width:auto;
}
.formarea{
    overflow:auto;
}
</style>
</head>

<body>
    <div class="submenuwrap">
        <%@include file="../../../../../menu.jsp" %>
            <c:if test="${!empty(savedMessage)}">
                 <center><div class="savedmsgalert"><font color="green">${savedMessage}</font></div></center>
             </c:if>
            <c:if test="${!empty(submittedMessage)}">
                 <center><div class="savedmsgalert"><font color="green">${submittedMessage}</font></div></center>
             </c:if>
        <div class="contentwrap">
                <div class="contentBand">Final Reviewer</div>
                    <c:set var="submitCount" value="0"></c:set>
                    <c:set var="reviewerSubmitCount" value="0"></c:set>
                    <c:set var="normalizerSubmitStatus" value="0"></c:set>
                    <c:set var="displayCorrectedNormalizerRating" value="0"></c:set>
                    <c:set var="CorrectedNormalizerRatingDisabled" value="0"></c:set>
                    <c:set var="normalizerSubmitCount" value="0"></c:set>
                    <c:set var="appraiseeCount" value="0"></c:set>
                    <c:set var="buttonsDisplay" value="0"></c:set>
            <form action="normalizerAction.do" method="POST" name="saveReview" id="saveReview" >
                <%int count=0; %>
                <div class="contentarea">
                    <table align="center" id="apprMaster" cellpadding="2" cellspacing="0" >
                        <tr align="center">
                            <td>Appraiser: </td><td>
                                <select name="myappraiserId" onchange="changeAppraiserAndReviewer()">
                                    <option value="">--All-</option>
                                    <c:forEach var="selectAppraiser" items="${appraiserList}">
                                        <option ${(selectAppraiser.appraiserId==selectedAppraiser)?'selected':'' } value="${selectAppraiser.appraiserId}">${selectAppraiser.appraiserName}</option>
                                    </c:forEach>
                                </select></td><td>
                                Reviewer:</td><td>
                                <select name="myreviewerId" onchange="changeAppraiserAndReviewer()">
                                    <option value="">--All-</option>
                                    <c:forEach var="selectReviewer" items="${reviewerList}">
                                        <option ${(selectReviewer.reviewerId==selectedReviewer)?'selected':'' } value="${selectReviewer.reviewerId}">${selectReviewer.reviewerName}</option>
                                    </c:forEach>
                                </select></td><%--<td>
                                Quarter :</td><td>
                                <select name="appraisalQuarter" id="appraisalQuarter" class="required" style="width: 4em" onchange="changeAppraiserAndReviewer()" >
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
                                </select></td>--%><td>
                                Year :</td><td>
                                <select name="appraisalYear" id="appraisalYear" class="required" style="width: 5em" onchange="changeAppraiserAndReviewer()" >
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
                </div>
                <c:if test="${fn:length(appraiseesdetails)>0}">
                    <div class="formarea" style="overflow-x:auto;">
                        <table width="100%">
                            <thead>
                                <c:forEach items="${appraiseesdetails}" var="reviewerStatusCheck">
                                    <c:set value="${reviewerStatusCheck.submitStatus}" var="normalizerSubmitStatus"></c:set>
                                    <c:if test="${reviewerStatusCheck.submitStatus>=6}">
                                        <c:set var="reviewerSubmitCount" value="${reviewerSubmitCount+1}"></c:set>
                                    </c:if>
                                    <c:if test="${reviewerStatusCheck.sendbackbyhr==1}">
                                        <c:set var="displayCorrectedNormalizerRating" value="1"></c:set>
                                    </c:if>
                                    <c:if test="${reviewerStatusCheck.sendbackbyhr==1 && reviewerStatusCheck.submitStatus>=8}">
                                        <c:set var="CorrectedNormalizerRatingDisabled" value="disabled"></c:set>
                                    </c:if>
                                </c:forEach>
                                <tr class="formarea_header">
                                    <th rowspan="2">Appraisee ID</th>
                                    <th rowspan="2">Appraisee Name</th>
                                    <th rowspan="2">Appraiser ID</th>
                                    <th rowspan="2">Appraiser Name</th>
                                    <%--<th colspan="4"  class="ratingAlign">QPD Status</th>--%>
                                    <th colspan="4"  class="ratingAlign">Appraisal Status</th>
                                    <th colspan="2" class="ratingAlign" rowspan="2">Appraiser Rating</th>
                                    <th colspan="2" rowspan="2">Appraiser Comments</th>
                                    <th colspan="2" rowspan="2">Appraiser Promotions</th>
                                    <th colspan="2" class="ratingAlign" rowspan="2">Reviewer Rating</th>
                                    <th colspan="2" rowspan="2">Reviewer Comments</th>
                                    <th colspan="2" rowspan="2">Reviewer Promotions</th>
                                    <%--<th colspan="2" rowspan="2" width="10%">Normalizer Promotions</th>--%>
                                    <th colspan="2" rowspan="2" width="10%">Final Reviewer Promotions</th>
                                    <th colspan="2" class="ratingAlign" rowspan="2">Final Reviewer Rating</th>
                                    <c:if test="${displayCorrectedNormalizerRating==1}"><th colspan="2" rowspan="2">Final Review Rating(Corrected)</th></c:if>
                                    <th colspan="2" rowspan="2">Justify Rating</th>
                                </tr>
                                <tr class="formarea_header">
                                    <th class="ratingAlign">Appraisee</th>
                                    <th class="ratingAlign">Appraiser</th>
                                    <th class="ratingAlign">Reviewer</th>
                                    <th class="ratingAlign">Final Reviewer</th>
                                </tr>
                            </thead>
                            <%--<c:out value="${appraiseesCount}"></c:out>--%>
                            <c:if test="${appraiseesCount != reviewerSubmitCount}">
                                <c:set value="disabled" var="ratingDisabled"></c:set>
                                <c:set value="readonly" var="normalizerCommentsReadonly"></c:set>
                            </c:if>
                            <c:if test="${normalizerSubmitStatus>=8}">
                                <c:set value="disabled" var="ratingDisabled"></c:set>
                                <c:set value="readonly" var="normalizerCommentsReadonly"></c:set>
                            </c:if>
                            <c:set value="0" var="rowDisplay"></c:set>
                            <c:set value="" var="hideCursor"></c:set>
                            <c:forEach items="${appraiseesdetails}" var="tes" varStatus="status">
                                <c:set var="appraiseeCount" value="${appraiseeCount+1}"></c:set>
                                <c:choose>
                                    <c:when test="${(status.index+1)%2==0}">
                                        <c:set value="formarea_row2" var="rowDisplay"></c:set>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set value="formarea_row1" var="rowDisplay"></c:set>
                                    </c:otherwise>
                                </c:choose>
                                <c:set value="${tes.submitStatus}" var="reviewerCurrentStatus"></c:set>

                                        <c:choose>
                                            <c:when test="${reviewerCurrentStatus<6 || reviewerCurrentStatus >=8}">
                                                          <c:set value="disabled" var="finalDisabled"></c:set>
                                                          <c:set value="readonly" var="finalReadOnly"></c:set>
                                                          <c:set value="onfocus='this.blur();'" var="hideCursor"></c:set>
                                            </c:when>
                                            <c:otherwise>
                                                      <c:set value="" var="finalDisabled"></c:set>
                                                      <c:set value="" var="finalReadOnly"></c:set>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${finalDisabled==''}">
                                            <c:set var="buttonsDisplay" value="show"></c:set>
                                        </c:if>
                                <tbody>
                                    <tr class="${rowDisplay}">
                                        <td><a href="javascript:void(0)" onclick="setAppraiseeDataNor(${tes.appraiseeId},${tes.bandId},${appraisalQuarter},${appraisalYear},'${tes.appraiserName}',${tes.appraiseeEmpId},${tes.departmentId})">${tes.appraiseeEmpId}</a>
                                            <input type="hidden" value="${tes.appraiseeId}" name="appraiseeIdForm[]" ${finalDisabled}>
                                            <input type="hidden" value="${tes.normalizerName}" name="normalizerNameForm[]" ${finalDisabled}>
                                            <input type="hidden" value="${tes.hrName}" name="hrNameForm[]" ${finalDisabled}>
                                            <input type="hidden" value="${tes.hrEmail}" name="hrEmailForm[]" ${finalDisabled}>
                                            <input type="hidden" value="${tes.sendbackbyhr}" name="sendbackbyhrForm[]" ${finalDisabled}>
                                            <c:if test="${tes.submitStatus>=6}">
                                                <c:set var="submitCount" value="${submitCount+1}"></c:set>
                                            </c:if>
                                            <c:if test="${tes.submitStatus>=8}">
                                                <c:set value="${normalizerSubmitCount+1}" var="normalizerSubmitCount"></c:set>
                                            </c:if>
                                        </td>
                                        <td><span class="displayText">${tes.appraiseeName}</span></td>
                                        <td><span class="displayText">${tes.appraiserEmpId}</span></td>
                                        <td><span class="displayText">${tes.appraiserName}</span></td>
                                        <td>
                                            <span class="displayText">
                                                <c:choose>
                                                    <c:when test="${tes.submitStatus >=2}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==1}"><font color="red">Sent Back to Appraisee</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                </c:choose>
                                            </span>
                                        </td>
                                        <td><span class="displayText"><c:choose><c:when test="${tes.submitStatus >=4}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==3}"><font color="red">Send Back to Appraiser</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                </c:choose>  </span></td>
                                        <td><span class="displayText"><c:choose>
                                                    <c:when test="${tes.submitStatus >=6}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==5}"><font color="red">Send Back to Reviewer</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                </c:choose>  </span></td>
                                        <td><span class="displayText"><c:choose>
                                                    <c:when test="${tes.submitStatus >=8}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==7}"><font color="red">sent back for correction</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                </c:choose>  </span></td>
                                        <td colspan="2" style="text-align: center">
                                            <span class="displayText">${tes.appraiserRating}</span>
                                            <input type="hidden" value="${tes.appraiserRating}" class="appraiserRatings" ${finalDisabled}/>
                                            <input type="hidden" name="submitStatusForm[]" value="${tes.submitStatus}" ${finalDisabled}>
                                        </td>
                                        <td colspan="2"><span class="displayText">${tes.appraiserComments}</span></td>
                                        <td colspan="2"><span class="displayText">
                                                <c:choose>
                                                    <c:when test="${tes.appraiserPromotionRecommeded==1}">
                                                        <c:set value="Yes" var="appPromoRec" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set value="No" var="appPromoRec" />
                                                    </c:otherwise>
                                                </c:choose>
                                                ${appPromoRec}</span></td>
                                        <td colspan="2" style="text-align: center"><span class="displayText">${tes.reviewerRating}</span>
                                            <input type="hidden" value="${tes.reviewerRating}" class="reviewerRatings" id="reviewerRatings${tes.appraiseeEmpId}"/>
                                        </td>
                                        <td colspan="2"><span class="displayText">${tes.reviewerComments}</span></td>
                                        <td colspan="2"><span class="displayText">
                                                <c:choose>
                                                    <c:when test="${tes.reviewerPromotionRec==1}">
                                                        <c:set value="Yes" var="appPromoRec" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set value="No" var="appPromoRec" />
                                                    </c:otherwise>
                                                </c:choose>
                                                ${appPromoRec}</span></td>
                                        <c:if test="${tes.sendbackbyhr==1}">
                                            <c:set value="disabled" var="ratingDisabled"></c:set>
                                        </c:if>
                                        <c:if test="${tes.sendbackbyhr==0}">
                                            <td colspan="2" width="25%"><span class="displayText">
                                                    <c:set value="" var="appPromStatusNo" />
                                                        <c:set value="" var="appPromStatusYes" />
                                                    <c:choose>
                                                        <c:when test="${tes.normalizerPromotionRec==1}">
                                                            <c:set value="selected" var="appPromStatusYes" />
                                                        </c:when>
                                                        <c:when test="${tes.normalizerPromotionRec==0}">
                                                            <c:set value="selected" var="appPromStatusNo" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set value="selected" var="appPromStatus" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <select style="width: auto;text-align: center;" name="normalizerPromotionRecommended[]" class="required" onchange="removeErrorDiv(this.id,'Select Promotions');" id="${tes.appraiseeEmpId}" ${finalDisabled}>
                                                        <option value="" ${appPromStatus}>--Select Promo--</option>
                                                        <option value="1" ${appPromStatusYes} >Yes</option>
                                                        <option value="0" ${appPromStatusNo}>No</option>
                                                    </select>
                                                   <%-- <input type="radio" name="normalizerPromotionRecommended${status.index}" id="normalizerPromotionRecommended${tes.appraiseeEmpId}"  value="1" ${finalDisabled} ${appPromStatusYes}>Yes<input type="radio" name="normalizerPromotionRecommended${status.index}" id="normalizerPromotionRecommended${tes.appraiseeEmpId}"  value="0" ${finalDisabled} ${appPromStatusNo}>No--%>
                                                <div class="customError" id="normalizerPromotionRecommended${tes.appraiseeEmpId}Div"></div>
                                            </span></td>
                                            <td colspan="2" style="text-align: center">
                                                <select style="width: auto;text-align: center;" name="reviewerRatingForm[]" class="normalizerRatings" onchange="calculateRatings();removeErrorDiv(this.id,'Select Ratings');" id="${tes.appraiseeEmpId}" ${finalDisabled}>
                                                    <option value="">-- Select Rating --</option>
                                                    <c:forEach begin="1" end="5" var="rateValue">
                                                        <c:choose>
                                                            <c:when test="${tes.reviewerRating == rateValue && tes.normalisedReviewerRating<1}">
                                                                <option value="${rateValue}" selected="selected">${rateValue}</option>
                                                            </c:when>
                                                            <c:when test="${tes.normalisedReviewerRating == rateValue && tes.normalisedReviewerRating>0}">
                                                                <option value="${tes.normalisedReviewerRating}" selected="selected">${tes.normalisedReviewerRating}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${rateValue}">${rateValue}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                                <div class="customError" id="${tes.appraiseeEmpId}Div"></div>
                                            </td>
                                        </c:if>

                                        <c:if test="${tes.sendbackbyhr==1}">
                                             <%--<td colspan="2">
                                                <select style="width: auto;text-align: center;" name="reviewerRatingForm[]" class="normalizerRatingsNew" onchange="calculateRatings();removeErrorDiv(this.id,'Select Ratings');" id="${tes.appraiseeEmpId}" ${CorrectedNormalizerRatingDisabled}>
                                                    <option value="">-- Select Rating --</option>
                                                    <c:forEach begin="1" end="5" var="rateValue">
                                                        <c:choose>
                                                            <c:when test="${tes.normalisedReviewerRating == rateValue && tes.correctedNormalizerRating<1}">
                                                                <option value="${rateValue}" selected="selected">${rateValue}</option>
                                                            </c:when>
                                                            <c:when test="${tes.correctedNormalizerRating == rateValue && tes.correctedNormalizerRating>0}">
                                                                <option value="${tes.correctedNormalizerRating}" selected="selected">${tes.correctedNormalizerRating}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${rateValue}">${rateValue}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                                <div class="customError" id="${tes.appraiseeEmpId}Div"></div>
                                            </td>--%>
                                            <td colspan="2" width="25%"><span class="displayText">
                                                    <c:set value="" var="appPromStatusNo" />
                                                        <c:set value="" var="appPromStatusYes" />
                                                    <c:choose>
                                                        <c:when test="${tes.normalizerPromotionRec==1}">
                                                            <c:set value="selected" var="appPromStatusYes" />
                                                        </c:when>
                                                        <c:when test="${tes.normalizerPromotionRec==0}">
                                                            <c:set value="selected" var="appPromStatusNo" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set value="selected" var="appPromStatus" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <select style="width: auto;text-align: center;" name="normalizerPromotionRecommended[]" class="required" onchange="removeErrorDiv(this.id,'Select Promotions');" id="${tes.appraiseeEmpId}" ${finalDisabled}>
                                                        <option value="" ${appPromStatus}>--Select Promo--</option>
                                                        <option value="1" ${appPromStatusYes} >Yes</option>
                                                        <option value="0" ${appPromStatusNo}>No</option>
                                                    </select>
                                                   <%-- <input type="radio" name="normalizerPromotionRecommended${status.index}" id="normalizerPromotionRecommended${tes.appraiseeEmpId}"  value="1" ${finalDisabled} ${appPromStatusYes}>Yes<input type="radio" name="normalizerPromotionRecommended${status.index}" id="normalizerPromotionRecommended${tes.appraiseeEmpId}"  value="0" ${finalDisabled} ${appPromStatusNo}>No--%>
                                                <div class="customError" id="normalizerPromotionRecommended${tes.appraiseeEmpId}Div"></div>
                                            </span></td>
                                            <td colspan="2" style="text-align: center">
                                                <select style="width: auto;text-align: center;"  class="normalizerRatings" onchange="calculateRatings();removeErrorDiv(this.id,'Select Ratings');" id="${tes.appraiseeEmpId}" ${finalDisabled} ${ratingDisabled}>
                                                    <option value="">-- Select Rating --</option>
                                                    <c:forEach begin="1" end="5" var="rateValue">
                                                        <c:choose>
                                                            <c:when test="${tes.reviewerRating == rateValue && tes.normalisedReviewerRating<1}">
                                                                <option value="${rateValue}" selected="selected">${rateValue}</option>
                                                            </c:when>
                                                            <c:when test="${tes.normalisedReviewerRating == rateValue && tes.normalisedReviewerRating>0}">
                                                                <option value="${tes.normalisedReviewerRating}" selected="selected">${tes.normalisedReviewerRating}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${rateValue}">${rateValue}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                                <input type="hidden" value="${tes.normalisedReviewerRating}" class="normalizerRatings"/></td>
                                            <td colspan="2">
                                                <select style="width: auto;text-align: center;" name="reviewerRatingForm[]" class="normalizerRatingsNew" onchange="calculateRatings();removeErrorDiv(this.id,'Select Ratings');" id="${tes.appraiseeEmpId}" ${CorrectedNormalizerRatingDisabled}>
                                                    <option value="">-- Select Rating --</option>
                                                    <c:forEach begin="1" end="5" var="rateValue">
                                                        <c:choose>
                                                            <c:when test="${tes.normalisedReviewerRating == rateValue && tes.correctedNormalizerRating<1}">
                                                                <option value="${rateValue}" selected="selected">${rateValue}</option>
                                                            </c:when>
                                                            <c:when test="${tes.correctedNormalizerRating == rateValue && tes.correctedNormalizerRating>0}">
                                                                <option value="${tes.correctedNormalizerRating}" selected="selected">${tes.correctedNormalizerRating}</option>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <option value="${rateValue}">${rateValue}</option>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </c:forEach>
                                                </select>
                                                <div class="customError" id="${tes.appraiseeEmpId}Div"></div>
                                            </td>
                                        </c:if>
                                        <c:if test="${tes.sendbackbyhr==1}">
                                            <td colspan="2">
                                                <%--${tes.normalizerComments}--%>
                                         <input type="text" name="justifyRatingNormalizer[]" id="justifyRating${tes.appraiseeEmpId}" class="justify" maxlength="250" value="${tes.normalizerComments}" ${finalDisabled} >
                                            </td>
                                        </c:if>
                                        <c:if test="${tes.sendbackbyhr==0}">
                                            <td colspan="2">
                                                <input type="text" name="justifyRatingNormalizer[]" id="justifyRating${tes.appraiseeEmpId}" class="justify" maxlength="250" value="${tes.normalizerComments}" ${finalDisabled} >
                                                <div class="customError" id="justifyRating${tes.appraiseeEmpId}Div"></div>
                                            </td>
                                        </c:if>
                                    </tr>
                                </tbody>
                            </c:forEach>
                        </table>
                    </div>
                             <table width="100%">
                            <tbody>
                            <tr>
                                <td style="text-align: center">
                                    <input class="buttons" type="button" onclick="showHideChart(this)" value="Show Chart" id="showChart" style="display: none"/>
                                    <div id="chartContainer">
                                        <h3 style="padding:0px; margin: 0px;color: #3E576F;font-family:Arial;font-size: 12px">
                                            <center>
                                                <input type="button" class="buttons" onclick="showHideChart(this)" id="hideChart" value="Hide Chart"/><br>
                                                <font size="4" >Appraisal rating</font>
                                                <br>
                                            </center>
                                        </h3>
                                        <div id="appraiseeChart" class="appraiseeChart" ></div>
                                    </div>
                                </td>
                            </tr>
                            </tbody>
                        </table>

                            <table width="100%">
                            <tr>
                                <td colspan="10" align="center" style="text-align: center">
                                    <input type="submit" name="button" id="saveRating" class="buttons exportbutton" value="Export to Excel" />
                                    <c:if test="${submitCount >= 1 && normalizerSubmitCount < appraiseeCount && buttonsDisplay=='show'}">
                                        <input type="submit" name="button" id="saveRating" class="buttons savebutton" value="Save" onclick="return checkValidations()" />
                                        <input style="width: 130px;" type="submit" name="button" id="SubmitToNormalizer" class="buttons submitbutton" value="Submit To HR" onclick="return checkValidations()" />
                                        <%--<input class="styledButton buttons resetbutton" value="Reset" type="reset" />--%>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
            <input type="hidden" value="" name="buttonValueChart" id="buttonValueChart"/>
            </form>

            <form id="appraiseeInfo" name="appraiseeInfo" action="fetchAppraiseeKraData.do" method="post">
                <input type="hidden" name="appraiseeIdSelected" id="appraiseeIdSelected">
                <input type="hidden" name="appraiseeEmpIdSelected" id="appraiseeEmpIdSelected">
                <input type="hidden" name="bandIdSelected" id="bandIdSelected">
                <input type="hidden" name="appraisalQuarterSelected" id="appraisalQuarterSelected">
                <input type="hidden" name="appraisalYearSelected" id="appraisalYearSelected">
                <input type="hidden" name="appraiserNameSelected" id="appraiserNameSelected">
                <input type="hidden" name="departmentIdSelected" id="departmentIdSelected">
                <input type="hidden" name="appraiseeInfoReviewer" id="appraiseeInfoReviewer">
            </form>


        </div>
    </div>
    <noscript>
        <table align="center">
            <tr>
                <td>
                    <h1>Error! - Javascript Disabled</h1>
                    <font face="Arial, Helevetica" color="red" >You Must Have JavaScript Enabled On Your Browser.</font>
                </td>
            </tr>
        </table>
    </noscript>
    <script type="text/javascript">
        var ratingJustify=0;
        var chart1; // globally available

        var appraiserRating = new Array(0,0,0,0,0);
        var reviewerRating = new Array(0,0,0,0,0);
        var normalizedRating = new Array(0,0,0,0,0);
        var correctedNormalizedRating = new Array(0,0,0,0,0);
        function justifyRatings(){
            $(".normalizerRatings").each(
            function(){
                <%--if(this.value==5 || this.value==1){ Change request by Varun--%>
                if(this.value!=""){
                    //$("#justifyRating"+this.id+"").attr("disabled", "");
//                    $("#justifyRating").val("");
                    $("#justifyRating"+this.id+"").show();
                    $("#justifyRating"+this.id+"Div").show();
                }else{
                    $("#justifyRating"+this.id+"").hide();
                    $("#justifyRating"+this.id+"").val("");
                    $("#justifyRating"+this.id+"Div").html("");
                }
            }
        )
        }

        function calculateRatings(){
            justifyRatings();
            var totalAppraisees = $(".appraiserRatings").length;
            var appraiserRatingsArray = new Array(0,0,0,0,0);
            var reviewerRatingsArray = new Array(0,0,0,0,0);
            var normalizerRatingsArray = new Array(0,0,0,0,0);
            var correctedNormalizerRatingsArray = new Array(0,0,0,0,0);
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
            $(".normalizerRatings").each(
            function(){
                normalizerRatingsArray[(this.value-1)]++;
            }
        )
            $(".normalizerRatingsNew").each(
            function(){
                correctedNormalizerRatingsArray[(this.value-1)]++;
            }
        )
            newAppraiserRatingsArray = new Array();
            newReviewerRatingsArray = new Array();
            newNormalizerRatingsArray = new Array();
            newCorrectedNormalizerRatingsArray = new Array();
            for(i=0;i<5;i++){
                newAppraiserRatingsArray[i]=parseInt((((appraiserRatingsArray[i])/totalAppraisees)*100),10);
                newReviewerRatingsArray[i]=parseInt((((reviewerRatingsArray[i])/totalAppraisees)*100),10);
                newNormalizerRatingsArray[i]=parseInt((((normalizerRatingsArray[i])/totalAppraisees)*100),10);
                newCorrectedNormalizerRatingsArray[i]=parseInt((((correctedNormalizerRatingsArray[i])/totalAppraisees)*100),10);
            }
            appraiserRating = newAppraiserRatingsArray.reverse();
            reviewerRating = newReviewerRatingsArray.reverse();
            normalizedRating = newNormalizerRatingsArray.reverse();
            correctedNormalizedRating = newCorrectedNormalizerRatingsArray.reverse();
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
                        {name: 'Final Review rating',data:normalizedRating},
                        {name: 'Final Review rating(Corrected)',data:correctedNormalizedRating},
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
        function checkValidations(){
        validationFlag = 0;
            if(validateNormalizerComments()){

            }else{
            validationFlag++;
            }
            if(validateNormalizerCorrected()){

            }else{
            validationFlag++;
            }

            if(validationFlag==0){
                return true;
            }else{
                return false;
            }
        }
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
                <tr class="formarea_header">
                    <td align="center" style="text-align: center">
                        -- Employee Data Not Available For This Appraisal Year--
                    </td>
                </tr>
            </table>
        </div>
    </c:if>
</body>


</html>
