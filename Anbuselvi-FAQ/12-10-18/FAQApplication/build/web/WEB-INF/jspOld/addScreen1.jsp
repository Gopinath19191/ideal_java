<%-- 
    Document   : addScreen
    Created on : Aug 28, 2018, 5:10:14 PM
    Author     : 16711
--%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>-->
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src = "${pageContext.request.contextPath}/js/addScreen.js"></script>

<%@ page isELIgnored="false"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add FAQ Screen</title>
    </head>
    <body>   
        <div align="center">             
            <!--c:if test="${faq != null}"-->
            <form:form name="formFaqDetails" id="formFaqDetails" action="updateFaq.htm" method="post" >        
                <!--                <form name="formFaqDetails" id="formFaqDetails" action="updateFaq.htm" method="post">-->
                <!--/c:if-->                
                <table id="dataTable1" border="1" cellpadding="5">                    
                    <tr>
                    <h3>Add FAQ Screen</h3>

                    <th>Function / Unit : </th>
                    <td>
                        <select name="faq_unit_id" id="faq_unit_id" style="width: 100; height: 20" >
                            <option value="" selected>Select</option>
                            <c:forEach var="faq" items="${listFunctionUnit}" >                                        
                                <option value="<c:out value='${faq.faq_unit_id}'/>"><c:out value='${faq.faq_unit}'/>  </option>
                            </c:forEach>
                        </select>

                    </td>
                    </tr>
                    <tr>
                        <th>Faq Question Category : </th>
                        <td>
                            <select name="question_category_id" id="question_category_id" style="width: 100; height: 20">
                                <option value="" selected>Select</option>
                                <c:forEach var="faqCategory" items="${listQuestionCategory}" >                                        
                                    <option value="<c:out value='${faqCategory.question_category_id}'/>"><c:out value='${faqCategory.question_category}'/>  </option>
                                </c:forEach>
                            </select>

                        </td>
                    </tr>



<!--   <input type="hidden" name="question_answer_id" id="question_answer_id" value="<c:out value='${faq.question_answer_id}'/>"/>-->
                    <tr>
                        <th>Question : </th>
                        <td>
                             <INPUT type='text' id="question[0].name" name = "question[0].name"/> 

                        </td>
                    </tr>
                    <tr>
                        <th>Answer : </th>
                        <td>
                             <INPUT type='text' id="answer[0].name" name = "answer[0].name"/> 

                        </td>

                        <td>
                            <INPUT type="button" value="Add Row" onclick='addRow("dataTable1",0)' >  </INPUT>
                            <INPUT type="button" value="Delete Row" onclick='deleteRow("dataTable1",0)' > </INPUT>
				 
<!--                            <TABLE id="dataTable" width="350px" border="0">
                                <TR>        

                                    <TD><INPUT type="text" id="question[0].name" name="question[0].name"/></TD>

                                    <TD> <INPUT type='text' id="answer[0].name" name = "answer[0].name"/> </TD>					           
                                </TR>
                            </TABLE>-->

                        </td>
                    </tr>
                    





                    <tr>
                        <td><input type="submit" name="faqSubmit" value="Submit" ></td>
                    </tr>

                </table>                
            </form:form>
        </div>
    </body>

</html>
