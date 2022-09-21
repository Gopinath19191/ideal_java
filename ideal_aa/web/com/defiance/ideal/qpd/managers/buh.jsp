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
     <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.qtip.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.qtip.min.css" type="text/css"/>
    <script type="text/javascript">
// Create the tooltips only on document load
$(document).ready(function()
{
	// Make sure to only match links to wikipedia with a rel tag
	$('.qpdRating').each(function()
	{
		// We make use of the .each() loop to gain access to each element via the "this" keyword...
		$(this).qtip(
		{
			content: {
				// Set the text to an image HTML string with the correct src URL to the loading image you want to use
				text: '<img class="throbber" src="/projects/qtip/images/throbber.gif" alt="Loading..." />',
				ajax: {
					url: $(this).attr('href'), // Use the rel attribute of each element for the url to load
                                        cache:false
					//url: '${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/getQpdRating.do?appraiseeId=303' // Use the rel attribute of each element for the url to load
				},
				title: {
					<%--text: 'Previous QPD ratings for - ' + $(this).text(), // Give the tooltip a title using each elements text--%>
					text: 'Previous QPD ratings ', // Give the tooltip a title using each elements text
					button: true
				}
			},
			position: {
				at: 'bottom right', // Position the tooltip above the link
				my: 'top center',
				viewport: $(window), // Keep the tooltip on-screen at all times
				effect: false // Disable positioning animation
			},
			show: {
				event: 'mouseenter',
				solo: true // Only show one tooltip at a time
			},
			hide: 'unfocus',
			style: {
				classes: 'ui-tooltip-wiki ui-tooltip-light ui-tooltip-shadow'
			}
		})
	})

	// Make sure it doesn't follow the link when we click it
	.click(function(event) { event.preventDefault(); });
});
</script>
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

        function setAppraiseeDataNor(appraiseeId,bandId,appraisalQuarter,appraisalYear,appraiserName,appraiseeEmpId,departmentId,reviewerMenuCheck){
            //alert(appraiseeId+appraiserName+appraisalQuarter+appraisalYear)
            $("#appraiseeIdSelected").val(appraiseeId);
            $("#bandIdSelected").val(bandId);
            $("#appraisalQuarterSelected").val(appraisalQuarter);
            $("#appraisalYearSelected").val(appraisalYear);
            $("#appraiserNameSelected").val(appraiserName);
            $("#appraiseeEmpIdSelected").val(appraiseeEmpId);
            $("#appraiseeInfoReviewer").val("normalizer");
            $("#departmentIdSelected").val(departmentId);
            $("#reviewerMenuCheck").val(reviewerMenuCheck);
            $("#appraiseeInfo").submit();
        }

        function changeAppraiserAndReviewer(){
            document.getElementById("saveReview").action = "beginBuh.do";
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
</head>
<body onload="changeTabClass('aaBuh');" style="width: auto;">
    <span class="topheading">Annual Appraisal</span>
    <div class="submenuwrap" style="width: auto;">
        <%@include file="../../../../../menu.jsp" %>
        <div class="contentwrap">
            <c:if test="${!empty(savedMessage)}">
                 <center><div class="savedmsgalert"><font color="green">${savedMessage}</font></div></center>
             </c:if>
            <c:if test="${!empty(submittedMessage)}">
                 <center><div class="savedmsgalert"><font color="green">${submittedMessage}</font></div></center>
             </c:if>
                    <c:set var="submitCount" value="0"></c:set>
                    <c:set var="reviewerSubmitCount" value="0"></c:set>
                    <c:set var="normalizerSubmitStatus" value="0"></c:set>
                    <c:set var="displayCorrectedNormalizerRating" value="0"></c:set>
                    <c:set var="CorrectedNormalizerRatingDisabled" value="0"></c:set>
                    <c:set var="normalizerSubmitCount" value="0"></c:set>
                    <c:set var="appraiseeCount" value="0"></c:set>
                    <c:set var="buttonsDisplay" value="0"></c:set>
            <form action="buhAction.do" method="POST" name="saveReview" id="saveReview" >
                <%int count=0; %>
                <div class="contentarea">
                    <div class="qpdSearch" style="width: 97%;" >
                        <table align="center" id="apprMaster" cellpadding="2" cellspacing="0" >
                            <tr align="center">
                                <td>Appraiser</td><td>
                                    <select name="myappraiserId" class="selectbox"  onchange="changeAppraiserAndReviewer()">
                                        <option value="">--All-</option>
                                        <c:forEach var="selectAppraiser" items="${appraiserList}">
                                            <option ${(selectAppraiser.appraiserId==selectedAppraiser)?'selected':'' } value="${selectAppraiser.appraiserId}">${selectAppraiser.appraiserName}</option>
                                        </c:forEach>
                                    </select></td>
                                    <td>Reviewer</td>
                                    <td>
                                        <select name="myreviewerId" class="selectbox" onchange="changeAppraiserAndReviewer()">
                                        <option value="">--All-</option>
                                        <c:forEach var="selectReviewer" items="${reviewerList}">
                                            <option ${(selectReviewer.reviewerId==selectedReviewer)?'selected':'' } value="${selectReviewer.reviewerId}">${selectReviewer.reviewerName}</option>
                                        </c:forEach>
                                    </select></td>
                                    <td>Normalizer</td>
                                    <td>
                                        <select name="mynormalizerId" class="selectbox" onchange="changeAppraiserAndReviewer()">
                                        <option value="">--All-</option>
                                        <c:forEach var="selectNormalizer" items="${normalizerList}">
                                            <option ${(selectNormalizer.normalizerId==selectedNormalizer)?'selected':'' } value="${selectNormalizer.normalizerId}">${selectNormalizer.normalizerName}</option>
                                        </c:forEach>
                                    </select></td>
                                    <td>Year</td>
                                    <td>
                                    <select name="appraisalYear" id="appraisalYear" class="required selectbox" style="width: 5em" onchange="changeAppraiserAndReviewer()" >
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
                </div>
                <c:if test="${fn:length(appraiseesdetails)>0}">
                    <div class="formarea">
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
                                    <th rowspan="2">Appraisee Band</th>
<!--                                    <th rowspan="2">Appraiser ID</th>
                                    <th rowspan="2">Appraiser Name</th>
                                    <%--<th colspan="4"  class="ratingAlign">QPD Status</th>--%>
                                    <th colspan="4"  class="ratingAlign">Appraisal Status</th>-->
                                    <th colspan="2" class="ratingAlign" rowspan="2">Appraiser Rating</th>
                                    <th colspan="2" rowspan="2">Appraiser Comments</th>
                                    <th colspan="2" rowspan="2">Appraiser Promotions</th>
                                    <th colspan="2" class="ratingAlign" rowspan="2">Reviewer Rating</th>
                                    <th colspan="2" rowspan="2">Reviewer Comments</th>
                                    <th colspan="2" rowspan="2">Reviewer Promotions</th>
                                    <%--<th colspan="2" rowspan="2" width="10%">Normalizer Promotions</th>--%>
                                    <th colspan="2" rowspan="2">Final Reviewer Promotions</th>
                                    <th colspan="2" class="" rowspan="2">Final Reviewer Rating</th>
                                    <th colspan="2" class="" rowspan="2">Previous QPD</th>
                                </tr>
                            </thead>
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
                                        <td>
                                            ${tes.appraiseeEmpId}
                                        </td>
                                        <td><span class="displayText">${tes.appraiseeName}</span></td>
                                        <td><span class="displayText">${tes.bandName}</span></td>
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
                                                    <c:when test="${tes.appraiserPromotionRecommeded==0}">
                                                        <c:set value="No" var="appPromoRec" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set value="" var="appPromoRec" />
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
                                                    <c:when test="${tes.reviewerPromotionRec==0}">
                                                        <c:set value="No" var="appPromoRec" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set value="" var="appPromoRec" />
                                                    </c:otherwise>
                                                </c:choose>
                                                ${appPromoRec}</span></td>
                                        <c:if test="${tes.sendbackbyhr==0}">
                                            <td colspan="2" width="6%"><span class="displayText">
                                                    <c:choose>
                                                        <c:when test="${tes.normalizerPromotionRec!='' && tes.normalizerPromotionRec!=null && tes.normalizerPromotionRec==1}">
                                                            <c:set value="Yes" var="appPromoRec" />
                                                        </c:when>
                                                        <c:when test="${tes.normalizerPromotionRec!='' && tes.normalizerPromotionRec!=null && tes.normalizerPromotionRec==0}">
                                                            <c:set value="No" var="appPromoRec" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set value="" var="appPromoRec" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    ${appPromoRec}
                                            </span></td>
                                            <td colspan="2" width="6%"><span class="displayText">
                                                    <c:choose>
                                                        <c:when test="${tes.normalisedReviewerRating==null || tes.normalisedReviewerRating=='' || tes.normalisedReviewerRating==0}">
                                                            
                                                        </c:when>
                                                        <c:otherwise>
                                                            ${tes.normalisedReviewerRating}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </span>
                                                <input type="hidden" value="${tes.normalisedReviewerRating}" class="normalizerRatings"/>
                                            </td>
                                            <td colspan="2" width="6%">
                                           <a style="color: #4C83B3;font-size: 12px;font-weight: bold"  class="qpdRating" href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/getQpdRating.do?appraiseeId=${tes.appraiseeId}" rel="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/lastThreeQpdRatings.jsp">QPD Ratings</a>
                                            </td>
                                        </c:if>
                                        <c:if test="${tes.sendbackbyhr==1}">
                                            <td colspan="2" width="25%"><span class="displayText">
                                                    <c:choose>
                                                        <c:when test="${tes.normalizerPromotionRec!='' && tes.normalizerPromotionRec!=null && tes.normalizerPromotionRec==1}">
                                                            <c:set value="Yes" var="appPromoRec" />
                                                        </c:when>
                                                        <c:when test="${tes.normalizerPromotionRec!='' && tes.normalizerPromotionRec!=null && tes.normalizerPromotionRec==0}">
                                                            <c:set value="No" var="appPromoRec" />
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:set value="--" var="appPromoRec" />
                                                        </c:otherwise>
                                                    </c:choose>
                                                    ${appPromoRec}
                                            </span></td>
                                            <td>
                                                <span class="displayText">
                                                    <c:choose>
                                                        <c:when test="${tes.correctedNormalizerRating==null || tes.correctedNormalizerRating=='' || tes.correctedNormalizerRating==0}">
                                                            --
                                                        </c:when>
                                                        <c:otherwise>
                                                            ${tes.normalisedReviewerRating}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </span>
                                            </td>
                                            <td>
                                                <a style="color: #4C83B3;font-size: 12px;font-weight: bold"  class="qpdRating" href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/getQpdRating.do?appraiseeId=${tes.appraiseeId}" rel="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/lastThreeQpdRatings.jsp">Here</a>
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
                                    <input class="buttons chartbutton" type="button" onclick="showHideChart(this)" value="Show Chart" id="showChart" style="display: none"/>
                                    <div id="chartContainer">
                                        <h3 style="padding:0px; margin: 0px;color: #3E576F;font-family:Arial;font-size: 12px">
                                            <center>
                                                <input type="button" class="buttons chartbutton" onclick="showHideChart(this)" id="hideChart" value="Hide Chart"/><br>
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
                <input type="hidden" name="reviewerMenuCheck" id="reviewerMenuCheck">
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
