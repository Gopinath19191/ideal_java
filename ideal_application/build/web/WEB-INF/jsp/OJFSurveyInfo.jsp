<%--
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<% response.addHeader("P3P", "CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\"");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"   "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Cache-Control"  content="no-cache"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<head>
    <title>OJF Associate Survey</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.6.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.17.custom.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.slider.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/qpd.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/c/jquery-ui-1.8.16.custom.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.16.custom.css" type="text/css"/>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.5.custom.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.ui.all.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.ui.core.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.ui.slider.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/qualityCustomer.css" type="text/css"/>

    <script type="text/javascript">
        $(document).ready(function() {
            //sets slider value to 2
            $( ".slider" ).slider({
                range: "min",
                min: 1,
                max: 5,
                value: 5,
                animate:true,
                step:1,
                slide: function( event, ui ) {
                    setSliderValue(this.id,ui.value)
                }
            });
            $( ".sliderval" ).val( $( ".slider" ).slider( "value" ) );

            //set slider to saved value
        <c:forEach items="${surveyQuestions}" var="singleRow" varStatus="status">
            <c:if test="${!empty singleRow.empAnswer}">
                <c:if test="${singleRow.answerType  ne typeRadio and singleRow.answerType ne typeFreeText}">
                        $("#slider-${singleRow.questionId}" ).slider({
                            range: "min",
                            min: 1,
                            max: 5,
                            step:1,
                            value: parseInt(${singleRow.empAnswer},10),
                            slide: function( event, ui ) {
                                setSliderValue(this.id,ui.value)
                            }
                        });
                        $("#sliderValue${singleRow.questionId}" ).val(parseInt(${singleRow.empAnswer}, 10));
                </c:if>
            </c:if>
        </c:forEach>
            });

            function setSliderValue(id,ui){
                //alert(id+":UI:"+ui);
                var mainId=id.replace("slider-","");
                $( "#sliderValue"+mainId).val(ui);
            }



            function customValidation(btnType){
                // $("#exitSurveyForm").validate();
                var errorFlag=0;
                $(".required").each(
                function(){
                    if(this.type=='radio'){
                        if($('input:radio[name='+this.name+']:checked').val()==undefined)
                        {
                            if($(this).parent().find(".customError").is(':empty')){
                                $(this).parent().parent().find(".customError").html("<font style='color:red; font-weight:bold;'>Required data Missing</font>");
                                errorFlag++;
                            }
                        }
                        else{
                            $(this).parent().parent().find(".customError").html("");
                        }

                    }
                    else{
                        if(this.value==""){
                            if(btnType!='save'){
                                $(this).parent().parent().find(".customError").html("<font style='color:red; font-weight:bold;'>Required data Missing</font>");
                            }
                            errorFlag++;
                        }else{
                            if(btnType!='save'){
                                $(this).parent().parent().find(".customError").html("");
                            }
                        }
                    }
                }
            )

                if(errorFlag==0){
                    return true;
                }else{
                    return false;
                }
            }
            function removeErrorDiv(elementFocused){
                if(elementFocused.value!=""){
                    $(elementFocused).parent().parent().find(".customError").html("");
                }else{
                    $(elementFocused).parent().parent().find(".customError").html("<font style='color:red; font-weight:bold;'>Required data Missing</font>");
                }
            }
            function removeErrorDivRadio(elementFocused){
                $(elementFocused).parent().parent().find(".customError").html("");
            }
            function validateForm(btnType){
                //alert(btnType)
                if(btnType=='submit') {
                    var returnValue = $("#OJFSurveyForm").validate();
                }
                //                if(customValidation(btnType) && ){
                //                    $("#actionType").val("1");
                //                }
                if(btnType=='submit') {
                    if(customValidation(btnType)) {
                        $("#actionType").val("1");
                        $("#saveBtn").hide();
                        $("#submitBtn").hide();
                        $("#btnCancel").hide();
                    }
                } else if(btnType=='save') {
                    $("#saveBtn").hide();
                    $("#submitBtn").hide();
                    $("#btnCancel").hide();
                    document.OJFSurveyForm.submit();
                }
            }
            function reject()
            {
                window.location.href = "OJFSurveyRedirect.htm"
            }
            function verifySaveProcess() {
                if("${param.process}" == "completed") {
                    parent.location.href = "${urlToRedirect}"
                }
            }
            verifySaveProcess();
    </script>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="">
        <div id="">
            <div class="container_inner">
                <p class="page_heading" style="margin-bottom: 40px;margin-left: 17px;"><span class="heading">OJF Survey</span> </p>
            </div>
            <div class="notice_page" style="width: 976px;">
                <div style="float: left"><img src="/ideal_rrf_new/css/images/icon_alert.png" title="Information" alt="Information"></img></div>
                <div style="padding-left: 10px;padding-top: 1px;">
                    <img src="/ideal_rrf_new/css/images/tick.png" title="Information" alt="Information" style="margin-left: 15px;margin-right: 10px;"></img>
                        Please fill all the details to submit.
                </div>
            </div>
            <form action="saveOJFSurveyData.htm" id="OJFSurveyForm" name="OJFSurveyForm" method="POST">
                <div id="datadisplay" class="formContent_new" style="width: 100%">
                    <input type="hidden" id="actionType" name="actionType" value="0">
                    <c:choose>
                        <c:when test="${exitEmpInfo.surveyStatus == SURVEY_SUBMIT_STATUS}">
                            <c:set value="disabled" var="disabledStatus" />
                            <c:set value="readonly" var="readonlyStatus" />
                        </c:when>
                        <c:otherwise>
                            <c:set value="" var="disabledStatus" />
                            <c:set value="" var="readonlyStatus" />
                        </c:otherwise>
                    </c:choose>
                    <c:set value="" var="disabledStatus" />
                    <c:set value="" var="readonlyStatus" />
                    <table style="line-height:33px; width:100%;">
                        <tbody>
                            <tr>
                                <td align="center" colspan="2" width="100%" height="5">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <!-- Begin of for Each -->
                            <c:set var="questionNumber" value="0" />
                            <c:set var="parentHeader" value="" />
                            <c:forEach items="${surveyQuestions}" var="singleRow" varStatus="status">
                                <c:forEach items="${surveyQuestionsParent}" var="singleRowParent" varStatus="status">
                                    <c:if test="${parentHeader != singleRow.parentId}">
                                        <c:if test="${singleRowParent.questionId eq singleRow.parentId}">
                                            <c:set var="parentHeader" value="${singleRow.parentId}" />
                                            <tr class="ojfSurveyParent">
                                                <td> ${singleRowParent.name}</td>
                                            </tr>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                                <c:if test="${singleRow.answerType eq typeRadio}">
                                    <c:set var="questionNumber" value="${questionNumber+1}" />
                                    <tr>
                                        <td style="font-weight:bold;">
                                            <c:out value="${questionNumber}"></c:out>. ${singleRow.name}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type="hidden" name="questionId" value="${singleRow.questionId}">
                                            <c:forEach items="${surveyAnswers}" var="answers" >
                                                <c:if test="${answers.questionId eq singleRow.questionId}">
                                                    <c:choose>
                                                        <c:when test="${answers.answerKey eq singleRow.empAnswer}">
                                                            <input type="radio"  ${disabledStatus} style="width:30px" name="answerValue${singleRow.questionId}" checked value="${answers.answerKey}" class="required" onclick="removeErrorDivRadio(this)" > ${answers.answerValue}
                                                        </c:when>
                                                        <c:otherwise>
                                                            <input type="radio" ${disabledStatus} style="width:30px" name="answerValue${singleRow.questionId}"  value="${answers.answerKey}" class="required" onclick="removeErrorDivRadio(this)" > ${answers.answerValue}
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:if>
                                            </c:forEach>
                                            <div class="customError"></div>
                                        </td>
                                    </tr>
                                </c:if>
                                <!--Check condition for slider -->
                                <c:if test="${singleRow.answerType eq typeSlider and singleRow.parentId eq '0'}">
                                    <tr>
                                        <td>  <c:out value="${questionNumber}"></c:out>.${singleRow.name}<br/>
                                            <label class="contentdata">${singleRow.questionDescription}</label>
                                            <div class="demo">
                                                <c:forEach items="${surveyAnswers}" var="answers" >
                                                    <c:if test="${answers.questionId eq singleRow.questionId}">
                                                        <label class="diffSlider${answers.answerKey}" style="">${answers.answerValue}</label>
                                                    </c:if>
                                                </c:forEach>
                                                <table>
                                                    <tr>
                                                        <td>
                                                            <div id="slider-${singleRow.questionId}" class="slider" style="width:450px;margin-left: 50px;"></div>
                                                        </td>
                                                        <td>
                                                            <input type="hidden" name="questionId" value="${singleRow.questionId}"/>
                                                            &nbsp; <input type="text" name="answerValue${singleRow.questionId}" id="sliderValue${singleRow.questionId}" readonly class="sliderval" style="border:0; color:#000000; font-weight:bold;" value="${singleRow.empAnswer}" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                </c:if>
                                <!-- End of slider condition -->

                                <!-- start of free text condition -->
                                <c:if test="${singleRow.answerType eq typeFreeText}">
                                    <tr>
                                        <td><label class="ojfSurveyFreeText"> ${singleRow.name}</label>
                                            <input type="hidden" name="questionId" value="${singleRow.questionId}"/>
                                            <textarea rows="2" name="answerValue${singleRow.questionId}" cols="100" style="height: 69px;width: 652px;margin: 10px;" class="textbox_new" onchange="removeErrorDiv(this)" ${readonlyStatus}>${singleRow.empAnswer}</textarea>
                                            <div class="customError"></div>
                                        </td>
                                    </tr>
                                </c:if>
                                <!-- End of free text conditon -->


                                <!-- Start of multiple question condition -->
                                <c:if test="${singleRow.answerType eq typeMultiple}">
                                    <c:if test="${singleRow.parentId eq '0'}">
                                        <tr>
                                            <td> <c:out value="${questionNumber}"></c:out>.${singleRow.name}<br/>
                                                <label class="contentdata">${singleRow.questionDescription}</label>
        <!--                                                <input type="hidden" name="questionId" value="${multipleQuestions.questionId}"/>-->
                                                <!--                                                <input type="hidden" name="answerValue" value="0" />-->
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <table class="gridtable">
                                                    <tr>
                                                        <td></td>
                                                        <td>
                                                            <label>${exitSurveyProp.commonNotImportant}</label><label style="margin-left: 200px;">${exitSurveyProp.commonImportant}</label>
                                                        </td>
                                                        <td></td>
                                                    </tr>
                                                    <c:forEach items="${surveyQuestions}" var="multipleQuestions" varStatus="multipleStatus">
                                                        <c:if test="${singleRow.questionId eq multipleQuestions.parentId}">
                                                            <tr>
                                                                <td>${multipleQuestions.name}</td>
                                                                <td>
                                                                    <div class="demo">
                                                                        <div id="slider-${multipleQuestions.questionId}" class="slider" style="width:300px;margin-left: 50px"></div>
                                                                    </div>
                                                                </td>
                                                                <td style="width: 2%">
                                                                    <input type="hidden" name="questionId" value="${multipleQuestions.questionId}"/>
                                                                    <input type="text" name="answerValue${multipleQuestions.questionId}" id="sliderValue${multipleQuestions.questionId}" readonly class="sliderval" style="border:0; color:#000000; font-weight:bold;" >
                                                                </td>
                                                            </tr>
                                                        </c:if>
                                                    </c:forEach>
                                                </table>
                                            </td>
                                        </tr>
                                    </c:if>
                                </c:if>
                                <!-- End of multiple question condition -->
                            </c:forEach>

                            <!-- End of for Each -->

                            <tr class="ojfSurveyParent">
                                <td> Over All Rating</td>
                            </tr>
                            <tr>
                                <td align="center" style="font-weight:bold;align:center">
                                    (Highest Rating would be 5)
                                </td>
                            </tr>
                            <tr>
                                <td align="center" style="font-weight:bold;align:center">
                                    <c:forEach items="${surveyRatings}" var="ojfRating" >
                                        <c:set var="selSurveyRating" value=""></c:set>
                                        <c:if test="${ojfEmployeeData[0].ojf_survey_rating == ojfRating.key}">
                                            <c:set var="selSurveyRating" value="checked"></c:set>
                                        </c:if>
                                        <input type="radio" style="width:30px" name="ojf_survey_rating" value="${ojfRating.key}" ${selSurveyRating} class="required" onclick="removeErrorDivRadio(this)" > ${ojfRating.value}
                                    </c:forEach>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <c:if test="${exitEmpInfo.resEmpId==employeeId}">
                    <div class="buttonAlignment">
                        <div class="buttonAlignment" id="btnGroup">
                            <input type="hidden" id="exitEmpId" name="exitEmpId" value="${ojfEmployeeData[0].exitEmpId}" readonly>
                            <c:if test="${ojfEmployeeData[0].ojf_survey_status != '1'}">
                                <c:if test="${dateDifference < ojf_survey_end}">
                                    <input type="submit" name="buttonName" id="saveBtn" value="Save" style="width: 90px;" class="qualitysave" ${disabledStatus} onclick="validateForm('save');" />
                                </c:if>
                                    <input type="submit" name="buttonName" id="submitBtn" value="Submit" style="width: 90px;" class="qualitysubmit" ${disabledStatus} onclick="validateForm('submit');"  />
                            </c:if>
                            <c:if test="${dateDifference < ojf_survey_end}">
                                <input type="button" name="buttonName" id="btnCancel" value="Back" class="qualityback" onclick="reject()">
                            </c:if>
                        </div>
                    </div>
                </c:if>
            </form>
        </div>
    </div>
    <script type="text/javascript">
        var loadingDivObj=(document.all);
        var ns4=document.layers;
        var ns6=document.getElementById&&!document.all;
        var ie4=document.all;
        if (ns4){
            loadingDivObj=document.loadingDiv;
        }else if (ns6){
            loadingDivObj=document.getElementById("loadingDiv").style;
        }else if (ie4){
            loadingDivObj=document.all.loadingDiv.style;
        }

        stopLoading();

        function stopLoading(){
            if(ns4){loadingDivObj.visibility="hidden";}
            else if (ns6||ie4) loadingDivObj.display="none";
        }

        function startLoading(){
            if(ns4){loadingDivObj.visibility="visible";}
            else if (ns6||ie4) loadingDivObj.display="block";
        }
    </script>
</body>