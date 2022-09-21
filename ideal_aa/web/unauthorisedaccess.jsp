<%@include file="header.jsp"  %>
<html>
    <head>
        <title>Unauthorized access</title>
    </head>
    <body>
        <h1>You don't have proper permission to access this page</h1>
    </body>
    <script type="text/javascript">
        setTimeout(redirectPage,4000);
        function redirectPage(){
            window.location="${pageContext.request.contextPath}/logout.do";
        }
    </script>
</html>
