<%-- 
    Document   : endUser
    Created on : Aug 28, 2018, 5:11:10 PM
    Author     : 16711
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>End User FAQ</title>

        <link rel="stylesheet"  href="style.css" type="text/css">
        <link rel="stylesheet"  href="viewstyle.css" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script type="text/javascript" src = "${pageContext.request.contextPath}/js/addScreen.js"></script>


        <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <meta name="viewport" content="width=device-width, initial scale='1.0'">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


        <%@ page isELIgnored="false"%>
        <!--        <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->

        <%@page contentType="text/html" pageEncoding="UTF-8"%>
    </head>
    <body onload="hideRemarks()">   
        <div class="container con">            
            <div class="row">
                <h1 class="text-s" id="add">End User FAQ </h1>
            </div>

            <div class="row" id="info-text">   
                <img src="images/icon_alert.png" title="information" alt="information" id="InfoIcon">
                <img src="images/tick.png" title="Information" alt="Information" id="tickIcon">
                <span class="Info">Fields marked in <span style="color:red;" for="fine">*</span> are mandatory</span>
                <ul class="unorder">
                    <li>
                        Click on Save button it will save your changes.
                    </li>
                    <li>Click on Submit button it will submit your changes.</li>
                </ul>
            </div>
            <div class="row">
                <div class="container-1">


                    <form:form name="formFaqDetails" id="formFaqDetails" action="endUser.htm">        

                        <table id="addform" style="padding-top:10px;">  
                            <tbody>
                                <tr>
                                    <td colspan="4" id="errorMessage" style="text-align: center; color: red; display: none;">Please Select Unit</td>
                                </tr>                  
                                <tr>
                                    <td>
                                        <label id="label">
                                            <b>Function/unit</b><span style="color:red;"> * </span>
                                        </label>
                                    </td>
                                    <td>
                                        <select name="faq_unit_id" id="faq_unit_id">
                                            <option value="">--Select--</option>
                                            <c:forEach var="faq" items="${listFunctionUnit}" > 
                                                <option value="${faq.faq_unit_id}" ${faq_unit_id==faq.faq_unit_id ? 'selected':' '}>${faq.faq_unit}</option>							
                                            </c:forEach>   
                                        </select>

                                    </td>                                    
                                    
                                </tr>
                                <tr>
                                    <td>
                                        <label id="label1">
                                            <b>Question Category</b><span style="color:red;"> * </span>
                                        </label>
                                    </td>
                                    <td>
                                        <select name="question_category_id" id="question_category_id">

                                            <option value="">--Select--</option>
                                            <c:forEach var="faqCategory" items="${listQuestionCategory}" > 
                                                <option value="${faqCategory.question_category_id}" ${questionCategoryId==faqCategory.question_category_id ? 'selected':' '}>${faqCategory.question_category}</option>							
                                            </c:forEach>                                          
                                        </select>
                                    </td>
                                    <td>                                        
                                        <input type="submit" value="GO" id="GO"/>
                                    </td>

                                </tr>                             
                                <c:if test="${fn:length(listQuestionAnswer)==0}">
                                    <tr>
                                        <td colspan="12" style="text-align: center;">
                                            No Data to Display
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table> 
                        <div class="wrapper">
                            <div class="wrapper center-block">
                                <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                                    <c:forEach var="questionAnswer" items="${listQuestionAnswer}" varStatus="status">
                                        <div class="panel panel-default">
                                            <div class="panel-heading active" role="tab" id="headingOne${questionAnswer.question_answer_id}">
                                                <h4 class="panel-title">
                                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne${questionAnswer.question_answer_id}" aria-expanded="true" aria-controls="collapseOne${questionAnswer.question_answer_id}">
                                                        <c:out value='${questionAnswer.question}' />
                                                    </a>
                                                </h4>
                                            </div>
                                            <div id="collapseOne${questionAnswer.question_answer_id}" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingOne${questionAnswer.question_answer_id}">
                                                <div class="panel-body">
                                                    <p><c:out value='${questionAnswer.answer}' /></p>
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>

                                </div>
                            </div>


                        </div>

                        <table> 

                            <tr>
                                <td><label id="label">Was this Question/Answer helpful? <span style="color:red;" >*</span></label></td>
                                <td><select name="satisfied" id="satisfied" onchange="areSatisfied()">
                                        <option value="">--select--</option>
                                        <option value="y">Yes</option>
                                        <option value="n">No</option>                            
                                    </select></td>
                            </tr>
                            <input type="hidden" id ="feedback2" name="feedback2" />
                            <tr id="rating_toggle">
                                <td><label id="label">Feedback Rating</label></td>
                                <td>
                                    <fieldset class="rating" style="position: relative;">                                        
                                        <input type="radio" id="star5" name="rating" value="5" class="ratingclass"/><label class="star" for="star5" title="Excellent">5 stars</label>
                                        <input type="radio" id="star4" name="rating" value="4" class="ratingclass" /><label class="star" for="star4" title="Pretty good">4 stars</label>
                                        <input type="radio" id="star3" name="rating" value="3" class="ratingclass" /><label class="star" for="star3" title="Average">3 stars</label>
                                        <input type="radio" id="star2" name="rating" value="2" class="ratingclass" /><label class="star" for="star2" title="Bad">2 stars</label>
                                        <input type="radio" id="star1" name="rating" value="1" class="ratingclass" /><label class="star" for="star1" title="Worse">1 star</label>
                                    </fieldset>
                                </td>

                            </tr>
                            <tr id="rating_toggle1">
                                <td><label id="label">Enter Feedback</label></td>
                                <td>
                                    <textarea type="text" class="select55" id="feedback1" name = "feedback1" style="min-width:225px; min-height:30px; max-width:305px; max-height:50px;"></textarea>                                   
                                </td>
                            </tr>
                            <!--                        <tr class="odd_row" id="rating_toggle">
                                                            <div><label>Given rating</label></div>
                                                            <div>
                                                                <div class="disableClass"></div>
                                                                <fieldset class="rating1" style="position: relative;">
                                                                    <legend></legend>
                                                                    <input type="radio" id="star5" name="rating" value="5" class="ratingclass"/><label class="star" for="star5" title="Excellent">5 stars</label>
                                                                    <input type="radio" id="star4" name="rating" value="4" class="ratingclass" /><label class="star" for="star4" title="Pretty good">4 stars</label>
                                                                    <input type="radio" id="star3" name="rating" value="3" class="ratingclass" /><label class="star" for="star3" title="Average">3 stars</label>
                                                                    <input type="radio" id="star2" name="rating" value="2" class="ratingclass" /><label class="star" for="star2" title="Bad">2 stars</label>
                                                                    <input type="radio" id="star1" name="rating" value="1" class="ratingclass" /><label class="star" for="star1" title="Worse">1 star</label>
                                                                </fieldset>
                                                            </div>
                                                        </tr>-->
                        </table>


                        <div id="sub">
                            <td><input type="button" name="faqSubmit" value="Submit" class="submitbutton_TS" onclick="validateEndUser();"></td>

                        </div>
                    </form:form>
                </div>


            </div>

    </body>
    <script>
        function validateEndUser(){ 
    
            alert("inside validateEndUser method");

            var faq_unit_id = $("#faq_unit_id").val();
            
            var questionCategoryId = $("#question_category_id").val();
            var error = 0;
            
            if (faq_unit_id == "" || questionCategoryId == ""){
                alert("please enter function unit / question category");
                error++;
            }
            var feedback2 = $('input[name=rating]:checked').val();
            $("#feedback2").val(feedback2);
            alert("rating "+feedback2);
          alert("Inside validateEndUser method "+error);
            if(error==0){
                alert("here");
                var feedback1 = $("#feedback1").val();
                 alert(feedback1);
                $('#formFaqDetails').attr("action", "updateFeedback.htm");
                document.formFaqDetails.submit();
                return true;
            }else{
                return false;
            }


        }
    </script>
</html>
