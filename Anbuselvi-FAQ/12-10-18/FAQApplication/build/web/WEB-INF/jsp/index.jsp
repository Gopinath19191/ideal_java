<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <c:if test="${result!= null}">
            <h4><c:out value="${result}" /></h4>
        </c:if>
        <h3>FAQ Application</h3>
        <ul>
            <li><a href="faqScreen.htm">Add Screen</a></li>
            <li><a href="endUser.htm">End User</a></li>
            <li><a href="viewFaqByAdmin.htm">View Admin</a></li>
            <li><a href="faqFeedback.htm">Faq Feedback</a></li>

        </ul>

    </body>
</html>
