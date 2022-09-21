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
                    <form:form name="formFaqDetails" id="formFaqDetails">        
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
                                            <c:choose>
                                                <c:when test="${faq_unit_id != null}">                                            
                                                    <option value="<c:out value='${faq_unit_id}'/>" selected><c:out value='${faq_unit}'/>  </option>
                                                    <c:forEach var="faq" items="${listFunctionUnit}" >                                        
                                                        <option value="<c:out value='${faq.faq_unit_id}'/>"><c:out value='${faq.faq_unit}'/>  </option>
                                                    </c:forEach>

                                                </c:when>
                                                <c:otherwise>
                                                    <option value="" selected>--Select--</option>
                                                    <c:forEach var="faq" items="${listFunctionUnit}" >                                        
                                                        <option value="<c:out value='${faq.faq_unit_id}'/>"><c:out value='${faq.faq_unit}'/>  </option>
                                                    </c:forEach>

                                                </c:otherwise>
                                            </c:choose>    

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
                                            
                                            <c:choose>
                                                <c:when test="${questionCategoryId != null}">                                            
                                                    <option value="<c:out value='${questionCategoryId}'/>" selected><c:out value='${questionCategory}'/>  </option>
                                                    <c:forEach var="faqCategory" items="${listQuestionCategory}" >                                        
                                                        <option value="<c:out value='${faqCategory.question_category_id}'/>"><c:out value='${faqCategory.question_category}'/>  </option>
                                                    </c:forEach>

                                                </c:when>
                                                <c:otherwise>
                                                    <option value="" selected>--Select--</option>
                                                    <c:forEach var="faqCategory" items="${listQuestionCategory}" >                                        
                                                        <option value="<c:out value='${faqCategory.question_category_id}'/>"><c:out value='${faqCategory.question_category}'/>  </option>
                                                    </c:forEach>

                                                </c:otherwise>
                                            </c:choose>                                            
                                        </select>
                                    </td>
                            <div class="container">
                                <td><input disabled="true" type="button" id="button" value="Add category" class="cat" onclick="openModel()"></td>
                                <!--                                <form id="subscribe-email-form" action="addQuestionCategory.htm" method="post">-->
                                <!-- Modal -->
                                <div class="modal fade" id="myModal" role="dialog">
                                    <div class="modal-dialog">
                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Please enter category name</h4>
                                            </div>
                                            <div class="modal-body">
                                                <input type="text" name="newQuesCategory" id="newQuesCategory"></input>

                                                <input type="hidden" name="currentUnit" id="currentUnit"></input>

                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                <input id="tag-form-submit" type="button" onclick="addQuesCategory()" value="AddCategory">
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <!--                                </form>      -->
                            </div>
                            <td>
                                <label id="label2">File Upload</label>
                            </td>
                            <td><input disabled="true" type="file" value="choose file" id="file1" accept="file_extension|audio/*|video/*|image/*|media_type"></td>
                            <td><input disabled="true" type="button" value="Add" id="button" onclick="addkid()" class="add1"></td>
                            </tr>

                            <!--c:forEach var="faq" items="${faqDtoForm.listFaq}" varStatus="status"-->       
                            <div id="questionAnswer">
                                <!--                                <tr>
                                                                    <td><input type="text" disabled="true" name="listFaq[${status.index}].question_answer_id" id="question_answer_id" size="10" value="<c:out value='${faq.question_answer_id}' />"/></td>
                                                                <td>
                                                                    <select name="question_answer_id" id="question_answer_id" onclick="showAnswer()">
                                                                            <option value="<c:out value='${faq.question_answer_id}'/>"><c:out value='${faq.question}'/>  </option>                                      
                                                                    </select>
                                                                </td>
                                                                </tr>
                                                                
                                                                    <tr>                                    
                                                                    <td onshow="false">
                                                                        <textarea type="text" class="select" id="answer" name="answer" value="<c:out value='${faq.answer}' />"></textarea>                                    
                                                                    </td> 
                                                                    <td><a hidden="true" href="editFaqQuestion.htm?question_answer_id=<c:out value='${faq.question_answer_id}'/>">Edit</a>
                                                                        &nbsp;&nbsp;&nbsp;&nbsp;
                                                                        <a href="deleteFaqQuestion.htm?question_answer_id=<c:out value='${faq.question_answer_id}'/>">Delete</a>                    
                                                                    </td>
                                
                                                                </tr>-->
                            </div>


                            <!--/c:forEach-->

                            </tbody>
                        </table> 
                        <div id="sub">
                            <td><input type="submit" name="faqSubmit" value="Submit" class="submitbutton_TS"></td>

                        </div>


                    </form:form>
                </div>
            </div>


        </div>

    </body>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script type="text/javascript" src = "${pageContext.request.contextPath}/js/addScreen.js"></script>
</html>
