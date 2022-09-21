<%--
    Document   : success
    Created on : Sep 3, 2010, 4:59:41 PM
    Author     : Hariharan.C
--%>
<%@include file="../../../../header.jsp"  %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Joining Formalities</title>
        <script type="text/javascript">
           <%-- setTimeout (submitForm,5000);--%>
            function submitForm(){
                $("#logoutForm").submit();
            }
            <%--history.forward();--%>
        </script>

    </head>

    <body>
        <center>
        <h1>Data Verified and Submitted by Hr</h1>
        </center>
        <form id="logoutForm" name="logoutForm" action="${pageContext.request.contextPath}/logout.do">
        </form>
    </body>
<%@include file="../../../../footer.jsp"  %>