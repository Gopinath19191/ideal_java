<%-- 
    Document   : error
    Created on : Oct 10, 2011, 12:27:56 PM
    Author     : 14053
--%>
<html>
    <head>
        <%@include file="header.jsp" %>
        <title>Error Page</title>
    </head>
    <body>
        <h1><font color="red">Error !</font></h1>
        <c:forEach items="${errorMessage}" var="err" varStatus="erroritr">
            Method name : <i>${err.methodName}</i> - File name :  <i>${err.fileName}</i> -  Line Number :  <i>${err.lineNumber}</i> <br>
        </c:forEach>
    </body>
</html>
