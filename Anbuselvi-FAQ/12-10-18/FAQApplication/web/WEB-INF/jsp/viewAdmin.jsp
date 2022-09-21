<%-- 
    Document   : viewAdmin
    Created on : Aug 28, 2018, 5:10:39 PM
    Author     : 16711
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Admin FAQ</title>

        <link rel="stylesheet"  href="style.css" type="text/css">
        <link rel="stylesheet"  href="viewstyle.css" type="text/css">



        <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <meta name="viewport" content="width=device-width, initial scale='1.0'">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


        <%@ page isELIgnored="false"%>
        <!--        <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->

        <%@page contentType="text/html" pageEncoding="UTF-8"%>
    </head>
    <body>   
        <div class="container con">            
            <div class="row">
                <h1 class="text-s" id="add">View Admin FAQ </h1>
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

<!--c:if test="${faq != null}"-->
                    <form:form name="formFaqDetails" id="formFaqDetails" action="listQuestionAnswer.htm">        
                        <!--                <form name="formFaqDetails" id="formFaqDetails" action="updateFaq.htm" method="post">-->
                        <!--/c:if-->                
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
                                    <td>
                                        <a href="faqScreen.htm"><input type="button" value="add FAQ" id="FAQ"/></a>
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
                                                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne${questionAnswer.question_answer_id}" aria-expanded="true" aria-controls="collapseOne${questionAnswer.question_answer_id}" 
                                                       <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                                        
                                                           <div id="questionPara${questionAnswer.question_answer_id}"><c:out value='${questionAnswer.question}' /></div>
                                                    </a>                                                    
                                                    <input type="button" style="float:right;margin-right:-70px;" id="edit" value="edit" onclick="editQue('${questionAnswer.question_answer_id}');"/>
                                                    <div class="modal fade" id="idModal${questionAnswer.question_answer_id}" role="dialog" >
                                                        <div class="modal-dialog">

                                                            <!-- Modal content-->
                                                            <div class="modal-content">
                                                                <div class="modal-header">
                                                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                                    <h4 class="modal-title">Please click save button</h4>
                                                                </div>
                                                                <div class="modal-body">
                                                                    <input type="hidden" name="question_answer_id" id="question_answer_id${questionAnswer.question_answer_id}" size="10" value="<c:out value='${questionAnswer.question_answer_id}' />"/>
                                                                    <textarea id="modalQuestion${questionAnswer.question_answer_id}"></textarea></br>
								    <input type="hidden" name="currentQuestion" id="currentQuestion"></input>
                                                                    <textarea id="modalAnswer${questionAnswer.question_answer_id}"></textarea>
								    <input type="hidden" name="currentAnswer" id="currentAnswer"></input>
                                                                </div>
                                                                <div class="modal-footer">
                                                                    
								    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                		    <input id="tag-form-submit" type="button" onclick="updateQuestionAnswerers('${questionAnswer.question_answer_id}')" value="UpdateQuestionAnswer">
                                                                </div>
                                                            </div>

                                                        </div>
                                                    </div>

                                                    <input type="button" id="delete" value="delete" onclick="deleteQuestionAnswer(${questionAnswer.question_answer_id})"/>
                                                </h4>
                                            </div>
                                            <div id="collapseOne${questionAnswer.question_answer_id}" class="panel-collapse collapse " role="tabpanel" aria-labelledby="headingOne${questionAnswer.question_answer_id}">
                                                <div class="panel-body">
                                                    <p id="answerPara${questionAnswer.question_answer_id}"><c:out value='${questionAnswer.answer}' /></p>
                                                    
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>


                                </div>
                            </div>


                        </div>
                    </form:form>
                </div>


            </div>

    </body>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src = "${pageContext.request.contextPath}/js/addScreen.js"></script>

    <script>
        function deleteQuestionAnswer(id){ 
    
            //            alert("inside deleteQuestionAnswer method"+id);

            var faq_unit_id = $("#faq_unit_id").val();
            
            var questionCategoryId = $("#question_category_id").val();
            var error = 0;
            
            if (faq_unit_id == "" || questionCategoryId == ""){
                alert("please enter function unit / question category");
                error++;
            }
            
            //alert("Inside validateEndUser method "+error);
            if(error==0){
                alert("here"+id);
                //var feedback1 = $("#feedback1").val();
                //alert(feedback1);
                $('#formFaqDetails').attr("action", "updateDeleteFlag.htm?id="+id);
                document.formFaqDetails.submit();
                return true;
            }else{
                return false;
            }


        }
        function editQue(id){
	
            alert("inside editQue method");
            //var answer1 = $('#answerPara').text();
             //var question1 = $('#questionPara').text();
            
            document.getElementById('modalQuestion'+id).value = $('#questionPara'+id).text();
            document.getElementById('modalAnswer'+id).value = $('#answerPara'+id).text(); 
//            var questionPara = $("#questionPara").text();
//            var answerPara = $("#answerPara").text();
//            
//            var modalQuestion = $("#modalQuestion").val();
//            var modalAnswer = $("#modalAnswer").val();
            //alert(currentQuestion);
           / //alert(currentAnswer);
            
            //document.getElementById('currentQuestion').value = $('#questionPara').text(); 
           // document.getElementById('currentAnswer').value = $('#answerPara').text();
            //$("#currentQuestion").val(currentQuestion);
            //$("#currentAnswer").val(currentAnswer);
            
            $('#idModal'+id).modal('show'); 
	
        }
        
        function updateQuestionAnswerers(id){
            
            //var answer1 = $('#modalQuestion').val();
            // var question1 = $('#modalAnswer').val();
             
            var faq_unit_id = $("#faq_unit_id").val();
            var question_category_id = $("#question_category_id").val();
            var question_answer_id = $("#question_answer_id"+id).val();
            var currentQuestion = $('#modalQuestion'+id).val();
            var currentAnswer = $('#modalAnswer'+id).val();
            
            $.ajax({
        type: "POST",
        url: "updateQuestionAnswer.htm",
        async: false,
        data:({
            faq_unit_id:faq_unit_id,
            question_category_id:question_category_id,
            currentQuestion:currentQuestion,
            currentAnswer:currentAnswer,
            question_answer_id:question_answer_id
        }),
        success : function(response) {
            alert("success");
            location.reload();
            //$("#question_category_id").html('').html(response);
        },

        error: function() {
            alert('Error');
        }
    });
    $('#idModal'+id).modal('hide'); 
        }
    </script>
</html>
