<!--
   created by 14620
   version:1
   Display Survey questionare
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exit Interview Questionnaire</title>
        <script type="text/javascript">

            $(document).ready(function() {
                //sets slider value to 2
                $( ".slider" ).slider({
                    range: "max",
                    min: 1,
                    max: 5,
                    value: 2,
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
                                range: "max",
                                min: 1,
                                max: 5,
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
                                    $(this).parent().parent().find(".customError").html("<font color='red'>Required data Missing</font>");
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
                                    $(this).parent().parent().find(".customError").html("<font color='red'>Required data Missing</font>");
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
                        $(elementFocused).parent().parent().find(".customError").html("<font color='red'>Required data Missing</font>");
                    }
                }
                function removeErrorDivRadio(elementFocused){
                    $(elementFocused).parent().parent().find(".customError").html("");
                }
                function validateForm(btnType){
                    //alert(btnType)
                    if(btnType=='submit'){
                        $("#exitSurveyForm").validate();
                    }
                    if(customValidation(btnType) && btnType=='submit'){
                        $("#actionType").val("1");
                    }
                    if(customValidation(btnType) || btnType=='save'){
                        //$("#saveBtn").hide();
                        //$("#submitBtn").hide();
                        $("#btnCancel").hide();
                    }
                }
                function reject()
                {
                    <%--$('#exitSurveyForm').attr("action", "employeeHome.htm");
                    $('#exitSurveyForm').submit();--%>
                             history.back();
                }
                
        </script>
        <style type="plain/css">
            #demo-frame > div.demo { padding: 10px !important; }
            table.gridtable {
                font-family: verdana,arial,sans-serif;
                font-size:11px;
                color:#333333;
                border-width: 1px;
                border-color: #666666;
                border-collapse: collapse;
                width: 70%
            }
            table.gridtable th {
                border-width: 1px;
                padding: 8px;
                border-style: solid;
                border-color: #666666;
                background-color: #dedede;
            }
            table.gridtable td {
                border-width: 1px;

                border-style: solid;
                border-color: #666666;
                background-color: #ffffff;
                width: 570px;
            }
            .radio {
                border-color: red;
            }
        </style>
    </head>
    <body>
        <form action="saveSurveyData.htm" id="exitSurveyForm" method="POST">
            <input type="hidden" id="actionType" name="actionType" value="0">
            <c:choose>
                <c:when test="${exitEmpInfo.surveyStatus=='Y'}">
                    <c:set value="disabled" var="disabledStatus" />
                    <c:set value="readonly" var="readonlyStatus" />
                </c:when>
                <c:otherwise>
                    <c:set value="" var="disabledStatus" />
                    <c:set value="" var="readonlyStatus" />
                </c:otherwise>
            </c:choose>
            <table style="float: left;font-family: Arial;font-size: 12px;">
                <tbody>
                     <tr>
                        <td align="center" width="100%" height="5">
                            <div id="errormessage" class="error-message">${Result}</div>
                        </td>
                    </tr>
                    <!-- Begin of for Each -->
                    <c:set var="questionNumber" value="0" />
                    <c:forEach items="${surveyQuestions}" var="singleRow" varStatus="status">
                        <c:if test="${singleRow.parentId eq '0'}">
                       <c:set var="questionNumber" value="${questionNumber+1}" />
                        </c:if>
                        <c:if test="${singleRow.answerType eq typeRadio}">
                            <tr>
                                <td>
                                    <c:out value="${questionNumber}"></c:out>.${singleRow.name}
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
                                                <c:if test="${answers.answerKey eq sliderMin}">
                                                    <label>${answers.answerValue}</label>
                                                </c:if>
                                                <c:if test="${answers.answerKey eq sliderMax}">
                                                    <label style="margin-left: 215px;">${answers.answerValue}</label>
                                                </c:if>
                                            </c:if>
                                        </c:forEach>
                                        <table>
                                            <tr>
                                                <td>
                                                    <div id="slider-${singleRow.questionId}" class="slider" style="width:300px;margin-left: 50px;"></div>
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
                                <td> <c:out value="${questionNumber}"></c:out>.${singleRow.name}<br/>
                                    <input type="hidden" name="questionId" value="${singleRow.questionId}"/>
                                    <textarea rows="5" name="answerValue${singleRow.questionId}" cols="50" class="required" onchange="removeErrorDiv(this)" ${readonlyStatus}>${singleRow.empAnswer}</textarea>
                                    <div class="customError"></div>
                                </td>
                            </tr>
                        </c:if>
                        <!-- End of free text conditon -->


                        <!-- Start of multiple question condition -->
                        <c:if test="${singleRow.answerType eq typeMultiple}">
                            <c:if test="${singleRow.parentId eq '0'}">
                                <tr>
                                    <td> <c:out value="${questionNumber}"></c:out>.${singleRow.name}
                                        <label class="contentdataForSurvey">${singleRow.questionDescription}</label>
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

                    <c:if test="${exitEmpInfo.resEmpId==employeeId}">
                        <tr>
                            <td align="center">
                                <input type="hidden" id="exitEmpId" name="exitEmpId" value="${exitEmpInfo.exitEmpId}" readonly>
                                <input type="submit" name="buttonName" id="saveBtn" value="Save" class="savebutton" ${disabledStatus} onclick="validateForm('save');" />
                                <input type="submit" name="buttonName" id="submitBtn" value="Submit" class="submitbutton" ${disabledStatus} onclick="validateForm('submit');"  />
                                <%--<input type="button" name="buttonName" id="btnCancel" value="Back" class="backbutton" onclick="reject()">--%>
                            </td>
                        </tr>
                    </c:if>
                    
                </tbody>
            </table>
        </form>
    </body>
</html>
