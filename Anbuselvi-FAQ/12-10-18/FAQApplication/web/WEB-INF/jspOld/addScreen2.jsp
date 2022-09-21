<%-- 
    Document   : addScreen
    Created on : Aug 28, 2018, 5:10:14 PM
    Author     : 16711
--%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add FAQ Screen</title>
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <link rel="stylesheet"  href="style.css" type="text/css">
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


        <%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
        <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial scale='1.0'">
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
        <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src = "${pageContext.request.contextPath}/js/addScreen.js"></script>
        <%@ page isELIgnored="false"%>

        <%@page contentType="text/html" pageEncoding="UTF-8"%>
    </head>
    <body>   
        <div class="container con">            
            <div class="row">
                <h1 class="text-s" id="add">Add FAQ Screen</h1>
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
                    <form:form name="formFaqDetails" id="formFaqDetails" action="updateFaq.htm" method="post" >        
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
                                            <option value="" selected>--Select--</option>
                                            <c:forEach var="faq" items="${listFunctionUnit}" >                                        
                                                <option value="<c:out value='${faq.faq_unit_id}'/>"><c:out value='${faq.faq_unit}'/>  </option>
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
                                            <option value="" selected>--Select--</option>
                                            <c:forEach var="faqCategory" items="${listQuestionCategory}" >                                        
                                                <option value="<c:out value='${faqCategory.question_category_id}'/>"><c:out value='${faqCategory.question_category}'/>  </option>
                                            </c:forEach>
                                        </select>

                                    </td>
                            <div class="container">
                                <td><input type="button" id="button" value="Add category" class="cat" 
                                           data-toggle="modal" data-target="#myModal"></td>

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
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">save</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </div>
                            </tr>
                            <tr>
                                <td>
                                    <input type="hidden" name="rowId" id="totalQANumber" value="1" />
                                    <label id="label1">
                                        <b>Question</b><span style="color:red;"> * </span>
                                    </label>
                                </td>
                                <td>
                                    <!--INPUT type='text' id="question[0].name" name = "question[0].name"/--> 
                                    <textarea type="text" class="select" id="question[0].name" name = "question[0].name"></textarea>
                                </td>
                                <td><input type="button" value="Add" id="button" onclick="addkid()" class="add1"></td>
                            </tr>
                            <tr>
                                <td>
                                    <label id="label1">
                                        <b>Answer</b><span style="color:red;"> * </span>
                                    </label>
                                </td>
                                <td>
                                    <!--INPUT type='text' id="answer[0].name" name = "answer[0].name"/--> 
                                    <textarea type="text" class="select" id="answer[0].name" name = "answer[0].name"></textarea>
                                </td>

                                <td>
                                    <label id="label2">File Upload</label>
                                </td>
                                <td><input type="file" value="choose file" id="file1" accept="file_extension|audio/*|video/*|image/*|media_type"></td>


                            </tr>




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

</html>
