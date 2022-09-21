<%-- 
    Document   : triggersuccess
    Created on : Nov 13, 2010, 6:27:47 PM
    Author     : HARIHARAN.C
--%>

<%@include file="../../../../../header.jsp" %>
    <head>
        <title> Appraisal Triggered Successfully</title>
    </head>
    <body>
        <h1>Appraisal Triggered Successfully</h1>
    </body>
    <c:out value="${appraisalTriggeredData}"></c:out>
    <c:forEach items="${appraisalTriggeredData}" var="empData">
        1
    </c:forEach>
</html>
