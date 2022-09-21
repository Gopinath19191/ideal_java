<%-- 
    Document   : faqFeedback
    Created on : Aug 28, 2018, 5:11:53 PM
    Author     : 16711
--%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>FAQ Feedback</title>

        <link rel="stylesheet"  href="style.css" type="text/css"/>
        <link rel="stylesheet"  href="viewstyle.css" type="text/css"/>
        <link rel="stylesheet"  href="feedbackform.css" type="text/css"/>



        <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <meta name="viewport" content="width=device-width, initial scale='1.0'"/>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


        <%@ page isELIgnored="false"%>
        <!--        <script type="text/javascript" src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->

        <%@page contentType="text/html" pageEncoding="UTF-8"%>
    </head>
    <body>   
        <div class="container con">            
            <div class="row">
                <h1 class="text-s" id="add">FAQ Feedback</h1>
            </div>

            <div class="row" id="info-text">   
                <img src="images/icon_alert.png" title="information" alt="information" id="InfoIcon">
                <img src="images/tick.png" title="Information" alt="Information" id="tickIcon">
                <span class="Info">Fields marked in <span style="color:red;" for="fine">*</span> are mandatory</span>
                <ul class="unorder">
                    <li>
                        This Feedback screen as Rating given by user.
                    </li>
                    <li>This Feedback screen as comments by user.</li>
                </ul>
            </div>
            <div class="row">
                <div class="container-1">

<!--c:if test="${faq != null}"-->
                    <form:form name="formFaqDetails" id="formFaqDetails" action="faqFeedback.htm">        
                        <!--                <form name="formFaqDetails" id="formFaqDetails" action="updateFaq.htm" method="post">-->
                        <!--/c:if-->                
                        <table id="addform" style="padding-top:10px;">  
                            <tbody>
                                <tr>
                                    <td colspan="4" id="errorMessage" style="text-align: center; color: red; display: none;">Please Select Unit</td>
                                </tr>                  
                                <tr id="display">
                                    <td style="border: 0px !important; padding:0px !important;">
                                        <label id="label">
                                            <b>Function/unit</b><span style="color:red;"> * </span>
                                        </label>
                                    </td>
                                    <td style="border: 0px !important; padding:0px !important;">
                                        <select name="faq_unit_id" id="faq_unit_id">
                                            <option value="">--Select--</option>
                                            <c:forEach var="faq" items="${listFunctionUnit}" > 
                                                <option value="${faq.faq_unit_id}" ${faq_unit_id==faq.faq_unit_id ? 'selected':' '}>${faq.faq_unit}</option>							
                                            </c:forEach>   
                                        </select>

                                    </td>
                                    <td style="border: 0px !important; padding:0px !important;">
                                        <span id="unit" style="display:none;">Please select unit</span>
                                    </td>

                                </tr>                                
                                <tr id="display">
                                    <td style="border: 0px !important; padding:0px !important;"> 
                                        <label id="label1">
                                            <b>Question Category</b><span style="color:red;"> * </span>
                                        </label>
                                    </td>
                                    <td style="border: 0px !important; padding:0px !important;">
                                        <select name="question_category_id" id="question_category_id">

                                            <option value="">--Select--</option>
                                            <c:forEach var="faqCategory" items="${listQuestionCategory}" > 
                                                <option value="${faqCategory.question_category_id}" ${questionCategoryId==faqCategory.question_category_id ? 'selected':' '}>${faqCategory.question_category}</option>							
                                            </c:forEach>                                          
                                        </select>
                                    </td>
                                    <td style="border: 0px !important; padding:0px !important;">                                        
                                        <input type="submit" value="GO" id="GO"/>
                                    </td>
                                    <td style="border: 0px !important; padding:0px !important;">
                                        <input type="button" value="Export" id="Export"/>
                                    </td>

                                </tr>
                                <c:if test="${fn:length(listFaqFeedback1)==0}">
                                    <tr>
                                        <td style="border: 0px !important; padding:0px !important;" colspan="12" style="text-align: center;">
                                            No Data to Display
                                        </td>
                                    </tr>
                                </c:if>
                            </tbody>
                        </table> 
                        <table class="tbl">
                            <tbody>
                                <tr id="color">
                                    <td>S.No</td>
                                    <td>Employee Name</td>
                                    <td>Satisfied</td>
                                    <td>FB given Date</td>
                                    <td>Rating/Comments</td>
                                </tr>
                                <c:forEach var="faqFeedback" items="${listFaqFeedback1}" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${faqFeedback.employeeName}</td>
                                        <c:if test="${faqFeedback.is_satisfied == 'y'}">
                                        <td>Yes</td>
                                        </c:if>
                                        <c:if test="${faqFeedback.is_satisfied == 'n'}">
                                        <td>No</td>
                                        </c:if>
                                        <td>${faqFeedback.created_date}</td>
                                        <td>${faqFeedback.feedback_rating_comments}</td>                                        
                                    </tr>
                                </c:forEach>                                
                            </tbody>
                        </table>


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
        
    </script>
</html>
