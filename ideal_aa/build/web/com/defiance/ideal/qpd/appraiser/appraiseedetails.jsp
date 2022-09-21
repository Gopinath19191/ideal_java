<%--
    Document   : Appraiser Form
    Created on : Nov 11, 2010, 11:34:34 AM
    Author     : Hariharan.C
--%>
<%@include file="../../../../../header.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery.alerts.js"></script>
    <head>
        <title>Appraisee Details</title>
        <c:if test="${submitStatus>=4}">
            <c:set var="readonlyStatus" value="readonly"></c:set>
            <c:set var="setDisabled" value="disabled"></c:set>
            <c:set var="hideCursor" value=""></c:set>
        </c:if>
        <style type="text/css">
            span.customError{
                bottom:6px;
                color:#FF5050;
                font-family: Calibiri, Geneva, sans-serif;
                font-size: 11px;
                padding-left: 10px;
                clear: none;
                width:auto;
                display: block;
                width:auto;
                position: relative;
            }
            div.customError{
                    padding-left: 10px;
            }
         .bigText{
        width: 98%;
        height:30px;
        }
        </style>
        <script type="text/javascript">
            function calculateRating(rateBoxId,weightage,ratedInput,ratedDiv){
                var rateBoxValue = document.getElementById(rateBoxId).value;
                var weightageValue = document.getElementById(weightage).value;
                var calculatedValue = (  ((rateBoxValue)*(weightageValue))/100 );
                
                document.getElementById(ratedInput).value = calculatedValue;
                document.getElementById(ratedDiv).innerHTML = calculatedValue.toFixed(1);
                
                calculateFinalRating();
            }

$(document).ready(function(){
    $("#appraiserForm").validate(
    {errorElement:"span",
     errorClass:"customError",
     rules: {
     justifyRatingAppraiser: {
           required: true,
           maxlength: 250
        },
        areasOfImprovement: {
           maxlength: 250
        },
        technologyTraining: {
           maxlength: 250
        },
        softskillTraining: {
           maxlength: 250
        }
     },
     submitHandler: function(form) {
        $(".buttons").each(  function(){$(this).hide();} );
        form.submit();
    }
    });

    $(".resizableTextArea").resizable();
    //$(".resizableTextArea").attr("title","Drag the triangle at the bottom right corner to resize the textarea");
    });

            
            function calculateFinalRating(){
            var FinalRating = 0;
            $(".ratingDiv").each(
                            function(){
                                FinalRating += parseFloat(this.value);
                            }
                        )
            $("#finalRating").val(Math.round(FinalRating));
            //alert(FinalRating);
                if(Math.round(FinalRating)==5 || Math.round(FinalRating)==1){
                    return false;
                }else{
                    <%--document.getElementById('justify').innerHTML="";
                    document.getElementById('justifyError').innerHTML="";--%>
                    return false;
                }
            }


            function calculateRate(){
                <c:if test="${!empty(kraData)}">
                        var appraiseeDataLength = '<c:out value="${fn:length(kraData)}"></c:out>';
                        for(i=0;i<appraiseeDataLength;i++){
                            calculateRating('appraiserCommentsRating'+i,'weightage'+i,'rating'+i,'ratingDiv'+i);
                        }
                </c:if>
            }
function validateAppraiserComments(){
    //alert(document.appraiserForm.justifyRatingAppraiser.value);
    var iChars = "!@#$%^&*()+=[]\\\';/{}|\":<>?";
    if(document.appraiserForm.justifyRatingAppraiser){
        for (var i = 0; i < document.appraiserForm.justifyRatingAppraiser.value.length; i++) {
        if (iChars.indexOf(document.appraiserForm.justifyRatingAppraiser.value.charAt(i)) != -1) {
            document.getElementById("justifyError").innerHTML = "Special Characters are not allowed in Justify Rating";
//            alert ("Special Characters are not allowed in Justify Rating");
            var appraiserComments=$("#justifyRatingAppraiser").val().substr(0, i);
            $("#justifyRatingAppraiser").val(appraiserComments);
            }
        }

//    document.getElementById("justifyError").innerHTML="";
    if(document.appraiserForm.justifyRatingAppraiser.value.length >= 250){
        document.getElementById("justifyError").innerHTML = "Please enter justification comments below 250 characters";
    }else{
        //document.getElementById("justifyError").innerHTML = "";
    }
    }
}

function removeErrorDiv(elementFocused){
                if(elementFocused.value!=""){
                    document.getElementById(elementFocused+"Div").innerHTML = "";
                }else{
                    document.getElementById(elementFocused+"Div").innerHTML = "Select Ratings";
                }
}
//this function has to be removed
function removeErrorDiv2(){
                    if(document.getElementById("justifyRatingAppraiser") && (document.getElementById("justifyRatingAppraiser").value == "")){
                        document.getElementById("justifyError").innerHTML = "Required Data Missing";
                    }else{
                        document.getElementById("justifyError").innerHTML = "";
                    }
}

function removeErrorDiv3(elementToCheck){
                    if($(elementToCheck).val()==""){
                        $(elementToCheck).parent().find(".customError").html("Please Enter remarks");
                    }else{
                        $(elementToCheck).parent().find(".customError").html("");
                        $(elementToCheck).parent().find(".customError").hide();
                    }
}

function validateRating(){
                var errorFlag = 0;
                var scrollFlag = 0;
                $(".validateData").each(function(){
                     if(this.value==""){
                            if(scrollFlag==0){$.scrollTo(this,{speed:1000,easing:'swing'});}
                            $(this).parent().find(".customError").show();
                            $(this).parent().find(".customError").html("Please Enter Remarks");
                            errorFlag++;
                            scrollFlag++;
                     }else{
                            $(this).parent().find(".customError").hide();
                            $(this).parent().find(".customError").html("");
                     }
                });
                
                $(".customRequired").each(
                function(){
                        var dataId = this.id;
                        if(this.value==""){
                                    if(scrollFlag==0){$.scrollTo(this,{speed:1000,easing:'swing'});}
                                    document.getElementById(dataId+"Div").innerHTML = "Select Ratings";
                                    errorFlag++;
                                    scrollFlag++;
                            }else{
                        //  divElement.innerHTML="<font color='red'>Required data Missing</font>";
                                <%--if($("#"+dataId+"Div").html().trim()!=""){--%>
                                    document.getElementById(dataId+"Div").innerHTML="";
                                <%--}--%>
                            }
                        }
                )
                    <%--if(document.getElementById("justifyRatingAppraiser") && (document.getElementById("justifyRatingAppraiser").value == "")){
                        document.getElementById("justifyError").innerHTML = "Required Data Missing";
                        errorFlag++;
                    }else{
                        document.getElementById("justifyError").innerHTML = "";
                    }--%>

                    if(errorFlag==0){
                        return true;
                    }else{
                        return false;
                    }
            }



function onLoadFunctions(){
    calculateRate();
    calculateFinalRating();
}
function fileDownloadAppraiser(fileName,fileType,referenceName,referenceId,fileId,moduleName){
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
<%--function popupAppraiser(){
alert(popupAppraiser2());
return false;
}--%>
    var clickFlag = true;
function popupAppraiser(){
     $( "#dialog:ui-dialog" ).dialog( "destroy" );
     if(clickFlag){
     $( "#dialog-confirm" ).dialog({
			resizable: false,
			modal: true,
			buttons: {
				"Ok": function() {
                                        if($("#sendBackReason").val()==""){
                                            alert("please enter jsutification comments");
                                            popupAppraiser();
                                            return false;
                                        }else{
					$("#reasonSendbackAppraiser").val($("#sendBackReason").val());
                                        $(this).dialog("close");
                                        clickFlag=false;
                                        $("#sendbackToAppraisee").click();
                                        return true;
                                        }
				},
				Cancel: function() {
                                        $("#sendBackReason").val("");
					$( this ).dialog( "close" );
				}
			}
        });
}
return false;
}

function fetchAppraiseeInfoAppraiser(){
    document.getElementById('appraiseeHeader').action="fetchAppraiseeData.do";
    document.getElementById('appraiseeHeader').submit();
}
        </script>
   </head>
   <body>
        <div class="submenuwrap">
                    <div id="dialog-confirm" title="Reason For sending Back?" style="display: none;">
                        <p>
                            <%--<span class="ui-icon ui-icon-alert" style="float:left; margin:0 7px 20px 0;"></span>--%>
                            <input type="text" maxlength="50" id="sendBackReason" name="sendBackReason">
                        </p>
                    </div>
           <%@include file="../../../../../menu.jsp" %>
             <c:if test="${!empty(savedmsg)}">
                 <center><div class="savedmsgalert"><font color="green">${savedmsg}</font></div></center>
             </c:if>

                    <%--<div class="contentBand">Quarterly Performance Dialogue Form</div>--%>
                    <div class="contentBand">Annual Appraisal</div>
                    <div class="contentarea">
                    <form action="fetchAppraiseeData.do" method="POST" name="appraiseeHeader" id="appraiseeHeader">
                    <table style="width:100%">
                     <tr>
                     <td>
                         <label class="headLabel">Employee Name</label></td><td><span class="displayText">${appraiseeInitialData.appraiseeNameForm}</span>
                     </td>
                     <td><label class="headLabel">Employee ID</label></td><td><span class="displayText">${appraiseeInitialData.appraiseeNumberForm}</span></td>
                     <%--<td><label class="headLabel">Quarter Under Review</label></td>--%>
                     <td><label class="headLabel">Year Under Review</label></td>
                     <td><span class="displayText">
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
                            <select name="appraisalYearForm" id="appraisalYear" style="width: 5em">
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
                                </select>
                         </span>

                         <input type="hidden" name="bandIdForm" value="${appraiseeInitialData.bandIdForm}" id="bandId">
                         <input type="hidden" name="appraiseeIdForm" value="${appraiseeInitialData.appraiseeIdForm}" id="appraiseeId">
                         <input type="hidden" name="appraiseeJoiningDateForm" value="${appraiseeInitialData.appraiseeJoiningDateForm}" >
                         <input type="hidden" name="appraiseeStatusForm" value="${appraiseeInitialData.appraiseeStatusForm}" >
                         <input type="hidden" name="appraiseeNumberForm" value="${appraiseeInitialData.appraiseeNumberForm}" >
                         <input type="hidden" name="appraiseeNameForm" value="${appraiseeInitialData.appraiseeNameForm}" >
                         <input type="hidden" name="submitStatus" value="${submitStatus}" id="submitStatus">
<!--                         <input class="buttons submitbutton" type="submit" value="Fetch" style="width: 5em" name="button" >-->
                     </td>
                     </tr>
                     <tr><td><label class="headLabel">Appraisee Status</label></td>
                         <td><span class="displayText">${employeeStatus}</span></td>
                         <td><label class="headLabel">Appraisee Joining Date</label></td>
                         <td><span class="displayText">${appraiseeInitialData.appraiseeJoiningDateForm}</span></td></tr>
                     </table>
                    </form>
                </div>
                <c:if test="${!empty(kraData)}">
                 <div class="formarea">
                     <form action="save.do" method="POST" name="appraiserForm" id="appraiserForm">
                            <input type="hidden" name="qpdId" value="${appraiserObj.qpdId}" id="qpdId">
                            <input type="hidden" name="bandId" value="${appraiseeInitialData.bandIdForm}" id="bandId">
                            <input type="hidden" name="appraiseeId" value="${appraiseeInitialData.appraiseeIdForm}" id="appraiseeId">
                            <input type="hidden" name="appraiseeYear" value="${appraiseeInitialData.appraisalYearForm}" id="appraiseeYear">
                            <input type="hidden" name="appraiseeQuarter" value="${appraiseeInitialData.appraisalQuarterForm}" id="appraiseeQuarter">
                            <input type="hidden" name="submitStatus" value="${submitStatus}" >
                            <input type="hidden" name="appraiseeJoiningDate" value="${appraiseeInitialData.appraiseeJoiningDateForm}" >
                            <input type="hidden" name="appraiseeStatus" value="${appraiseeInitialData.appraiseeStatusForm}" >
                            <input type="hidden" name="appraiseeNumber" value="${appraiseeInitialData.appraiseeNumberForm}" >
                            <input type="hidden" name="appraiseeName" value="${appraiseeInitialData.appraiseeNameForm}">
                            <c:if test="${fn:length(topAchievements)!=0}">
                                <div>
                                    <fieldset>
                                        <legend>Top achievements during the assessment period</legend>
                                        <table id="topAchievements" width="100%" align="center" border="0" style="border-collapse: collapse">
                                            <tr>
                                                <th>
                                                    Top Achievements during the assessment year
                                                </th>
                                                <th>
                                                    Appraiser remarks
                                                </th>
                                                <c:if test="${appraiseeDetails.submitStatus<2}">
                                                    <th width="100px">
                                                        Actions
                                                    </th>
                                                </c:if>
                                            </tr>
                                            <c:forEach items="${topAchievements}" var="topAchievementsDetail" varStatus="taindex">
                                                <tr>
                                                    <td class="tableData">
                                                        <textarea readonly cols="" rows="" class="bigText" id="keyResultAreas${taindex.index}" name="keyResultAreas[]">${topAchievementsDetail.keyResultArea}</textarea>
                                                        <input type="hidden" name="achievementId[]" id="achievementId${taindex.index}" value="${topAchievementsDetail.achievementId}" />
                                                    </td>
                                                    <td>
                                                        <textarea ${setDisabled} onchange="removeErrorDiv3(this)" cols="" rows="" class="bigText validateData" id="appraiserRemarks${taindex.index}" name="appraiserRemarks[]">${topAchievementsDetail.appraiserRemarks}</textarea>
                                                        <div class="customError" style="display:none;color: red"></div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </table>
                                    </fieldset>
                                </div>
                         </c:if>
                         <br>
                         <br>
                 <table width="100%">
                     <thead>
                         <tr class="formarea_header">
                            <th width="200px;">Key Result Areas</th>
                            <th>Performance Criteria</th>
                            <%--<th>Measurement Criteria</th>--%>
                            <th>Weightage</th>
                            <th>Key Achievements - Comments</th>
                            <th>Rating</th>
                            <th align="center">Rating * Weightage</th>
                            <th align="center">Appraiser Comments</th>
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
                                <%-- <td>
                                     <input type="hidden" name="qpdKraIdHidden[]" id="qpdKraIdHidden" value="${datum.qpdKraId}">
                                     <input type="hidden" name="kraQtrIdHidden[]" id="kraQtrIdHidden" value="${datum.kraQtrId}">
                                     ${datum.kraDescription}</td>--%>
                                 <td>
                                     <input type="hidden" name="qpdKraIdHidden[]" id="qpdKraIdHidden" value="${datum.qpdKraId}">
                                     <input type="hidden" name="kraQtrIdHidden[]" id="kraQtrIdHidden" value="${datum.kraQtrId}">
                                     ${datum.attributes}<span class="displayText">${datum.performanceCriteria}</span></td>
                                 <%--<td><span class="displayText">${datum.measurementCriteria}</span></td>--%>
                                 <td style="text-align: center"><input type="hidden" name="weightage[]" id="weightage${indexValue.index}" value="${datum.weightage}" /><span class="displayText">${datum.weightage}%</span></td>
                                 <td><textarea cols="15" rows="3" name="appraiseeComments[]" readonly>${datum.selfComments}</textarea></td>
                                 <td>
                                    <select style="width: auto; text-align: center;" <c:out value="${setDisabled}"></c:out> name="appraiserCommentsRating[]" id="appraiserCommentsRating${indexValue.index}" class="customRequired" onchange="calculateRating('appraiserCommentsRating${indexValue.index}','weightage${indexValue.index}','rating${indexValue.index}','ratingDiv${indexValue.index}');calculateFinalRating();removeErrorDiv(this.id);">
                                    <option value="">-- Select Rating --</option>
                                        <c:forEach begin="1" end="5" var="rateValue">
                                              <c:choose>
                                                <c:when test="${datum.appraiserComments == rateValue}">
                                                    <option value="${rateValue}" selected="selected">${rateValue}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${rateValue}">${rateValue}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                    <div class="customError" id="appraiserCommentsRating${indexValue.index}Div"></div>
                                 </td>
                                 <td align="center">
                                     <div align="center" id="ratingDiv${indexValue.index}"></div>
                                     <input <c:out value="${readonlyStatus}"></c:out> type="hidden" class="ratingDiv" id="rating${indexValue.index}" value="0" />
                                 </td>
                                 <td>
                                     <textarea cols="15" rows="3" name="appraiserCommentsNew[]" class="resizableTextArea" ${setDisabled}>${datum.appraiserCommentsNew}</textarea>
                                 </td>
                             </tr>
                         </c:forEach>
                         <tr>
                                 <td colspan="8" align="left">
                                     <c:if test="${fn:length(fileDetails)>0}">
                                         <b><u>Attached Documents</u></b><br>
                                     <c:forEach items="${fileDetails}" var="file" varStatus="index">
                                        <c:set var="fileNameArray" value="${fn:split(file.fileName,'~~')}"></c:set>
                                        <a href="javascript:void(0)" onclick="fileDownloadAppraiser('${file.fileName}','${file.fileType}','${file.referenceName}','${file.referenceId}','${file.fileId}','${file.moduleName}')">${fileNameArray[2]}</a><br>
                                     </c:forEach>
                                     </c:if>
                                 </td>
                             </tr>
                         <tr>
                        </table>
                         <c:if test="${fn:length(developmentNeeds)!=0}">
                             <div>
                                 <fieldset>
                                     <legend>Development Needs</legend>
                                     <table id="developmentNeeds" width="100%" align="center" border="0" style="border-collapse: collapse">
                                         <tr>
                                             <th>
                                                 Development Needs
                                             </th>
                                             <th>
                                                 Appraiser Remarks
                                             </th>
                                         </tr>
                                         <c:forEach items="${developmentNeeds}" var="developmentNeedsDetail" varStatus="dnindex">
                                             <tr>
                                                 <td class="tableData"><textarea readonly cols="" rows="" class="bigText" id="devNeedData${dnindex.index}" name="devNeedData[]">${developmentNeedsDetail.developmentNeeds}</textarea></td>
                                                 <td class="tableData"><textarea ${setDisabled} onchange="removeErrorDiv3(this)" cols="" rows="" class="bigText validateData" id="apprDevelopmentRemarks${dnindex.index}" name="apprDevelopmentRemarks[]">${developmentNeedsDetail.apprDevRemarks}</textarea>
                                                     <div class="customError" style="display:none;color: red"></div>
                                                     <input type="hidden" name="developmentNeedsId[]" id="developmentNeedsId${dnindex.index}" value="${developmentNeedsDetail.needsId}" />
                                                 </td>
                                             </tr>
                                         </c:forEach>
                                     </table>
                                 </fieldset>
                             </div>
                         </c:if>

                         <br>
                          <c:if test="${fn:length(goalSheet)!=0}">
                             <div>
                                <fieldset>
                                    <legend>Goal Sheet for ${appraiseeInitialData.appraisalYearForm} - ${appraiseeInitialData.appraisalYearForm+1}</legend>
                                    <table id="goalSheet" width="100%" align="center" border="0" style="border-collapse: collapse">
                                        <tr>
                                            <th>
                                                Goals
                                            </th>
                                             <th>
                                                 Appraiser Remarks
                                             </th>
                                         </tr>
                                         <c:forEach items="${goalSheet}" var="goalSheetDetail" varStatus="glIndex">
                                             <tr>
                                                <td class="tableData"><textarea readonly cols="" rows="" class="bigText" id="goals${glIndex.index}" name="goals[]">${goalSheetDetail.goalData}</textarea></td>
                                                <td class="tableData">
                                                    <textarea ${setDisabled} cols="" rows="" onchange="removeErrorDiv3(this)" class="bigText validateData" id="apprGoalRemarks${glIndex.index}" name="apprGoalRemarks[]">${goalSheetDetail.appGoalRemarks}</textarea><div class="customError" style="display:none;color: red"></div>
                                                    <input type="hidden" name="goalsId[]" id="goalsId${glIndex.index}" value="${goalSheetDetail.goalId}" />
                                                </td>
                                            </tr>
                                        </c:forEach>
                                     </table>
                                 </fieldset>
                             </div>
                         </c:if>




                         <table width="100%" border="0">
                         <tr>
                             <td style="text-align: center"  valign="middle" width="25%">
                                 <%--Please add comments to justify rating --%> Strengths<br> (Max 250 Characters):
                             </td>
                             <td width="25%">
                                 <textarea cols=30 rows=3 class="required alphanumericlang resizableTextArea" id="justifyRatingAppraiser" name="justifyRatingAppraiser" ${setDisabled}>${appraiserComments}</textarea>
                             </td>
                             <td valign="middle" style="text-align: center" width="25%">
                                 <%--Areas of improvement--%> Improvement Areas <br> (Max 250 Characters):
                             </td>
                             <td width="25%">
                                 <textarea class="resizableTextArea alphanumericlang required" name="areasOfImprovement" cols="30" rows="3" ${setDisabled}>${appraiserObj.areasOfImprovement}</textarea>
                             </td>
                         </tr>
                         </table>
                         <%--<fieldset>
                             <legend><span>Trainings Recommended</span></legend>--%>
                             <table width="100%" align="center">
                                 <tr>
                                     <td style="text-align: center" valign="middle" width="24%">
                                         <%--Technology Training --%> Action Plans<br> (Max 250 Characters) :
                                     </td>
                                     <td colspan="2" width="24%">
                                         <textarea class="resizableTextArea alphanumericlang required" name="technologyTraining" cols="30" rows="3" ${setDisabled}>${appraiserObj.technologyTraining}</textarea>
                                     </td>
                                     <td style="text-align: center" valign="middle" width="24%">
                                         <%--Soft skill Training --%> Over All Comments <br> (Max 250 Characters) :
                                     </td>
                                     <td colspan="2" width="24%">
                                         <textarea class="required  resizableTextArea alphanumericlang" name="softskillTraining" cols="30" rows="3" ${setDisabled}>${appraiserObj.softskillTraining}</textarea>
                                     </td>
                                 </tr>
                             </table>
                         <%--</fieldset>--%>

                         <table width="100%" align="center" border="0">
                             <tr>
                                 <td style="text-align: center" width="50%" colspan="3">
                                     Performance Level : <input style="width: 20px;" onfocus="this.blur();" type="text" readonly value="" id="finalRating" name="finalRating" >
                                 </td>
                             </tr>
                             <tr>
                                <td style="text-align: center;" colspan="3">
                                     <c:choose>
                                         <c:when test="${appraiserObj.appraiserPromotionRecommeded==1}">
                                            <c:set value="checked" var="appPromStatusYes" />
                                         </c:when>
                                         <c:when test="${appraiserObj.appraiserPromotionRecommeded==0}">
                                            <c:set value="checked" var="appPromStatusNo" />
                                         </c:when>
                                     </c:choose>

                                     Promotion Recommended : <input ${setDisabled} class="required" type="radio" name="appraiserPromotionRecommeded" id="appraiserPromotionRecommeded" value="1" ${appPromStatusYes}  />Yes <input type="radio" ${setDisabled} class="required" name="appraiserPromotionRecommeded" id="appraiserPromotionRecommeded" value="0" ${appPromStatusNo} /> No
                                 </td>
                             </tr>
                         </table>

                         <table width="100%" align="center">
                             <tr>
                             <td colspan="8" style="text-align: center" align="center">
                                 <input class="buttons exportbutton" type="submit" value="Export to Excel" name="button">
                                 <c:if test="${submitStatus<4}">
                                 <input class="buttons savebutton" type="submit" value="Save" name="button" onclick="return validateRating();">
                                 <input class="buttons submitbutton" value="Submit" type="submit" name="button" onclick="return validateRating();" >
                                 <input id="sendbackToAppraisee" style="width:200px;" class="buttons sendbackbutton" value="Send Back To Appraisee" type="submit" name="button" onclick="return popupAppraiser();">
                                <%--<input class="styledButton buttons resetbutton" value="Reset" type="reset" />--%>
                                <input type="hidden" name="reasonSendbackAppraiser" id="reasonSendbackAppraiser">
                                </c:if>
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
        </div>
    </body>
    <script type="text/javascript">
        onLoadFunctions();
    </script>
    </c:if>
    <c:if test="${empty(kraData)}">
        <div class="formarea">
            <table class="row" align="center" border="0" >
                <tr>
                    <td align="center">--Annual Appraisal Data Not Available For This Year--</td>
                </tr>
            </table>
        </div>
    </c:if>
</html>
