<%--
    Document   : review.jsp
    Created on : Nov 15, 2010, 11:37:08 AM
    Author     : Hariharan.C
--%>
<%@include file="../../../../../header.jsp" %>
<head>
    <title>HR</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/highcharts.js"></script>
    <script type="text/javascript">

        function submitFilter(){
            document.getElementById("saveReview").action = 'beginHr.do';
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

        function sendAppraiseeInfo(appraiseeId,bandId,appraisalQuarter,appraisalYear,appraiserName,appraiseeEmpId,departmentId){
            //alert(appraiseeId+appraiserName+appraisalQuarter+appraisalYear)
            $("#appraiseeIdSelected").val(appraiseeId);
            $("#bandIdSelected").val(bandId);
            $("#appraisalQuarterSelected").val(appraisalQuarter);
            $("#appraisalYearSelected").val(appraisalYear);
            $("#appraiserNameSelected").val(appraiserName);
            $("#appraiseeEmpIdSelected").val(appraiseeEmpId);
            $("#appraiseeInfoReviewer").val("hr");
            $("#departmentIdSelected").val(departmentId);
            $("#appraiseeInfo").submit();
        }
var clickFlag = true;
        function popupHr(){
             $( "#dialog:ui-dialog" ).dialog( "destroy" );
     if(clickFlag){
               $( "#dialog-confirm" ).dialog({
			resizable: false,
			modal: true,
			buttons: {
				"Ok": function() {
                                        if($("#sendBackReason").val()==""){
                                            popupHr();
                                            return false;
                                        }else{
					$("#reasonSendbackHr").val($("#sendBackReason").val());
                                        $(this).dialog("close");
                                        clickFlag=false;
                                         $("#saveRatingSB").click();
                                        return true;
                                        }
				},
				Cancel: function() {
                                        $("#sendBackReason").val("");
					$( this ).dialog( "close" );
                                        return false;
				}
			}
		});
     }
                return false;
        }

        function fetchAppraiseeListHr(){
            document.getElementById('saveReview').action="beginHr.do";
            document.getElementById('saveReview').submit();
        }

        function changeAppraiserAndReviewer(){
            document.getElementById("saveReview").action = "beginHr.do";
            document.getElementById("saveReview").submit();
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
<body onload="changeTabClass('aaHr');" style="width: auto;">
    <span class="topheading">Annual Appraisal</span>
    <c:set var="submitCount" value="0"></c:set>
    <c:set var="correctHeader" value="0"></c:set>
    <c:set var="finalStatus" value="0"></c:set>
    <c:set var="hrSubmitCount" value="0"></c:set>
    <c:set var="appraiseeCount" value="0"></c:set>
    <c:set var="buttonsDisplay" value="0"></c:set>
<div class="tabletools submenuwrap" style="width: auto;">
    <div id="dialog-confirm" title="Reason For sending Back?" style="display: none;">
	<p>
            <%--<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>--%>
            <input type="text" maxlength="50" id="sendBackReason" name="sendBackReason">
        </p>
    </div>

            <%@include file="../../../../../menu.jsp" %>
            <div class="contentwrap">
            <form action="hrAction.do" method="post" name="saveReview" id="saveReview" >
            <%int count = 0;%>
            <div class="contentarea">
                <div class="qpdSearch" style="width: 97%">
                    <table id="apprMaster" align="center" cellpadding="2">
                        <tr align="center">
                            <td>Final Reviewer</td>
                            <td>
                                <select name="mynormalizerId" class="selectbox" onchange="changeAppraiserAndReviewer()">
                                    <option value="">--All-</option>
                                    <c:forEach var="selectNormalizer" items="${normalizerList}">
                                        <option ${(selectNormalizer.normalizerId==param.mynormalizerId)?'selected':'' } value="${selectNormalizer.normalizerId}">${selectNormalizer.normalizerName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>SBU/Function</td>
                            <td>
                                <select name="appraiseeSBUDepartmentId" class="selectbox" onchange="changeAppraiserAndReviewer()">
                                    <option value="">--All-</option>
                                    <c:forEach var="selectSBU" items="${organizationalList}">
                                        <option ${(selectSBU.departmentId==param.appraiseeSBUDepartmentId)?'selected':'' } value="${selectSBU.departmentId}">${selectSBU.appraiseeSBU}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>Year</td>
                            <td>
                                <select name="appraisalYear" id="appraisalYear" class="required selectbox" style="width: 5em" onchange="changeAppraiserAndReviewer()" >
                                    <c:forEach items="${yearDataHr}" var="year">
                                        <c:choose>
                                            <c:when test="${appraisalYearHr == year}">
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

                <c:if test="${fn:length(appraiseesdetailsHr)>0}">
                    <div class="formarea">
                            <table width="100%">
                                <thead>
                                    <tr class="formarea_header">
                                        <c:forEach items="${appraiseesdetailsHr}" var="correctedDisplay" varStatus="status">
                                            <c:if test="${correctedDisplay.sendbackbyhr==1 && correctedDisplay.submitStatus>=8}">
                                                <c:set var="correctHeader" value="1"></c:set>
                                            </c:if>
                                            <c:set var="finalStatus" value="${correctedDisplay.submitStatus}"></c:set>
                                        </c:forEach>
                                        <th rowspan="2">Appraisee ID</th>
                                        <th rowspan="2">Appraisee Name</th>
                                        <th rowspan="2">Appraiser ID</th>
                                        <th rowspan="2">Appraiser Name</th>
                                        <%--<th colspan="4" class="ratingAlign">QPD Status</th>--%>
                                        <th colspan="4" class="ratingAlign">Appraisal Status</th>
                                        <th colspan="2" class="ratingAlign" rowspan="2">Appraiser Rating</th>
                                        <th colspan="2" rowspan="2">Appraiser Comments</th>
                                        <th colspan="2" rowspan="2">Appraiser Promotions</th>
                                        <th colspan="2" class="ratingAlign" rowspan="2">Reviewer Rating</th>
                                        <th colspan="2" rowspan="2">Reviewer Comments</th>
                                        <th colspan="2" rowspan="2">Reviewer Promotions</th>
                                        <th colspan="2" class="ratingAlign" rowspan="2">Final Review Rating</th>
                                        <c:if test="${correctHeader==1}"><th colspan="2" class="ratingAlign" rowspan="2">Final Review Rating (corrected)</th></c:if>
                                        <th colspan="2" rowspan="2">Final Review Comments</th>
                                        <th colspan="2" rowspan="2">Final Review Promotions</th>
                                    </tr>
                                    <tr align="center" class="formarea_header">
                                        <th class="ratingAlign">Appraisee</th>
                                        <th class="ratingAlign">Appraiser</th>
                                        <th class="ratingAlign">Reviewer</th>
                                        <th class="ratingAlign">Final Review</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${appraiseesdetailsHr}" var="tes" varStatus="status">
                                    <c:set var="appraiseeCount" value="${appraiseeCount+1}"></c:set>
                                    <c:set value="${tes.submitStatus}" var="normalizerCurrentStatus"></c:set>
                                        <c:choose>
                                            <c:when test="${normalizerCurrentStatus<8 || normalizerCurrentStatus >=9}">
                                                          <c:set value="disabled" var="hrDisabled"></c:set>
                                                          <c:set value="readonly" var="hrReadOnly"></c:set>
                                            </c:when>
                                            <c:otherwise>
                                                      <c:set value="" var="hrDisabled"></c:set>
                                                      <c:set value="" var="hrReadOnly"></c:set>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:if test="${hrDisabled==''}">
                                            <c:set var="buttonsDisplay" value="show"></c:set>
                                        </c:if>
                         <c:if test="${(status.index%2)==0}"><c:set var="rowClass" value="formarea_row1"></c:set></c:if>
                         <c:if test="${(status.index%2)!=0}"><c:set var="rowClass" value="formarea_row2"></c:set></c:if>
                         <tr class="${rowClass}">
                                            <td><a style="color: #4C83B3;font-size: 12px;font-weight: bold" href="javascript:void(0)" onclick="sendAppraiseeInfo(${tes.appraiseeId},${tes.bandId},${appraisalQuarterHr},${appraisalYearHr},'${tes.appraiserName}',${tes.appraiseeEmpId},${tes.departmentId})">${tes.appraiseeEmpId}</a></td>
                                            <td> <span class="displayText">${tes.appraiseeName}</span></td>
                                            <td> <span class="displayText">${tes.appraiserEmpId}</span></td>
                                            <td>
                                                <span class="displayText">
                                                ${tes.appraiserName}
                                                <input type="hidden" value="${tes.appraiseeId}" name="appraiseeIdForm[]" ${hrDisabled}>
                                                <input type="hidden" value="${tes.normalizerName}" name="normalizerNameForm[]" ${hrDisabled}>
                                                <input type="hidden" value="${tes.normalizerEmail}" name="normalizerEmailForm[]" ${hrDisabled}>
                                                <c:if test="${tes.submitStatus>=8}">
                                                    <c:set var="submitCount" value="${submitCount+1}"></c:set>
                                                </c:if>
                                                <c:if test="${tes.submitStatus>=9}">
                                                    <c:set value="${hrSubmitCount+1}" var="hrSubmitCount"></c:set>
                                                </c:if>
                                                </span>
                                            </td>
                                            <td>
                                                <span class="displayText">
                                                    <c:choose>
                                                    <c:when test="${tes.submitStatus >=2}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==1}"><font color="red">Sent back to Appraisee</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                </c:choose>
                                                </span>
                                            </td>
                                            <td>
                                                <span class="displayText">
                                                <c:choose>
                                                    <c:when test="${tes.submitStatus >=4}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==3}"><font color="red">Send Back to Appraiser</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                </c:choose>
                                                </span>
                                            </td>
                                            <td>
                                                <span class="displayText">
                                                <c:choose>
                                                    <c:when test="${tes.submitStatus >=6}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==5}"><font color="red">Send Back to Reviewer</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                </c:choose>
                                                    </span>
                                            </td>
                                            <td>
                                                <span class="displayText">
                                                    <c:choose>
                                                    <c:when test="${tes.submitStatus >=8}"><font color="green">Submitted</font></c:when>
                                                    <c:when test="${tes.submitStatus ==7}"><font color="red">Sent back for correction</font></c:when>
                                                    <c:otherwise><font color="red">Not Submitted</font></c:otherwise>
                                                    </c:choose>
                                                </span>
                                            </td>
                                            <td colspan="2" style="text-align: center">
                                                <span class="displayText">${tes.appraiserRating}</span>
                                                <input type="hidden" value="${tes.appraiserRating}" class="appraiserRatings"/>
                                            </td>
                                            <td colspan="2"><span class="displayText">${tes.appraiserComments} </span></td>
                                            <td colspan="2"><span class="displayText">
                                                    <c:set value="" var="appPromoRec" />
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
                                            <td colspan="2" style="text-align: center">
                                                <span class="displayText">${tes.reviewerRating}</span>
                                                <input type="hidden" value="${tes.reviewerRating}" class="reviewerRatings"/>
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

                                            <td colspan="2" style="text-align: center"><span class="displayText">${tes.normalisedReviewerRating}</span>
                                                <input type="hidden" value="${tes.normalisedReviewerRating}" class="normalizerRatings"/>
                                            </td>

                                            <c:if test="${correctHeader==1}">
                                                <td colspan="2" style="text-align: center"><span class="displayText">${tes.correctedNormalizerRating}
                                                    <input type="hidden" value="${tes.correctedNormalizerRating}" class="normalizerRatingsNew"/>
                                                    </span>
                                                </td>
                                            </c:if>
                                                <td colspan="2"><span class="displayText">${tes.normalizerComments}</span></td>
                                                <td colspan="2"><span class="displayText">
                                                <c:choose>
                                                    <c:when test="${tes.normalizerPromotionRec==1}">
                                                        <c:set value="Yes" var="appPromoRec" />
                                                    </c:when>
                                                    <c:when test="${tes.normalizerPromotionRec==0}">
                                                        <c:set value="No" var="appPromoRec" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <c:set value="" var="appPromoRec" />
                                                    </c:otherwise>
                                                </c:choose>
                                                ${appPromoRec}</span></td>
                                        </tr>
                                    </c:forEach>
                             </tbody>
                            </table>
</div>

                                          <table width="100%">
                                            <tr>
                                                <td style="text-align: center">
                                                    <input class="buttons chartbutton" type="button" onclick="showHideChart(this)" id="showChart" value="Show Chart" style="display:none"/>
                                                    <div id="chartContainer" >
                                                        <h3 style="padding:0px; margin: 0px;color: #3E576F;font-family:Arial;font-size: 12px">
                                                            <center>
                                                                <input type="button" class="buttons chartbutton" onclick="showHideChart(this)" id="hideChart" value="Hide Chart"/>
                                                                <br>
                                                                <font size="4">Annual Appraisal rating</font>
                                                                <br/>
                                                            </center>
                                                        </h3>
                                                        <div id="appraiseeChart" class="appraiseeChart" ></div>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>

                                        <table width="100%">
                              <tr>
                                  <td align="center" style="text-align: center">
                                  <input type="hidden" name="reasonSendbackHr" id="reasonSendbackHr">
                                  <input type="submit" name="button" class="buttons exportbutton" id="saveRating" value="Export to Excel" />
                                  <%--<c:if test="${fn:length(appraiseesdetailsHr) == submitCount && finalStatus<9 && ((param.mynormalizerId==null || param.mynormalizerId=='')&&(param.appraiseeSBUDepartmentId=='' || param.appraiseeSBUDepartmentId==null))}">--%>
                                  <c:if test="${submitCount >= 1 && hrSubmitCount < appraiseeCount && buttonsDisplay=='show'}">
                                  <input type="submit" style="width: 169px;" name="button" class="buttons submitbutton" id="SubmitToNormalizer" value="Submit To Finance" />
                                  <input type="submit" style="width: 205px;" name="button" id="saveRatingSB" class="buttons sendbackbutton" value="Send back for correction" onclick="return popupHr();"/>
                                  <%--<input class="buttons resetbutton" value="Reset" type="reset" />--%>
                                  </c:if>
                                  </td>
                              </tr>
                      </table>
                
                                        
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
                        <font face="Arial, Helevetica" color="red" >You must have JavaScript enabled on your browser.</font>
                    </td>
                </tr>
            </table>
        </noscript>
        <script type="text/javascript">
            var chart1; // globally available
            var appraiserRating = new Array(0,0,0,0,0);
            var reviewerRating = new Array(0,0,0,0,0);
            var normalizedRating = new Array(0,0,0,0,0);
            var correctedNormalizerRatingsArray = new Array(0,0,0,0,0);
            function calculateRatings(){
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


                //alert(reviewerRatingsArray+"\n"+appraiserRatingArray+"\n"+totalAppraisees);

                newAppraiserRatingsArray = new Array();
                newReviewerRatingsArray = new Array();
                newNormalizerRatingsArray = new Array();
                newCorrectedNormalizerRatingsArray = new Array();

                for(i=0;i<5;i++){
                    reviewerRatingsArray[i]=parseInt((((reviewerRatingsArray[i])/totalAppraisees)*100),10);
                    appraiserRatingsArray[i]=parseInt((((appraiserRatingsArray[i])/totalAppraisees)*100),10);
                    normalizerRatingsArray[i]=parseInt((((normalizerRatingsArray[i])/totalAppraisees)*100),10);
                    correctedNormalizerRatingsArray[i]=parseInt((((correctedNormalizerRatingsArray[i])/totalAppraisees)*100),10);
                }

                //alert(appraiserRatingArray+"\n"+reviewerRatingsArray+"\n"+totalAppraisees);

                reviewerRating = reviewerRatingsArray.reverse();
                appraiserRating = appraiserRatingsArray.reverse();
                normalizedRating = normalizerRatingsArray.reverse();
                correctedNormalizedRating = correctedNormalizerRatingsArray.reverse();


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
                            {name: 'Final Review Rating',data:normalizedRating},
                            {name: 'Final Review Rating-Corrected',data:correctedNormalizedRating},
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


              $("#saveReview").submit(function() {
            $(".buttons").each(
            function(){$(this).hide();}
        );
            return true;
        });

        </script>
    </c:if>
    <c:if test="${fn:length(appraiseesdetailsHr)==0}">
        <div class="formarea">
         <table width="100%">
                   <thead>
                   <tr class="formarea_header">
                        <td style="text-align: center">
                            -- Employee Data Not Available For This Appraisal Year --
                        </td>
                   </tr>
                   </thead>
         </table>
        </div>
    </c:if>
</body>
</html>