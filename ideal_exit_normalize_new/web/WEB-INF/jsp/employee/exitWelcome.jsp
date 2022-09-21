<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <title>Welcome Page</title>
        <style type="text/css">
            p{
                color: #666666;
                float: left;
                font-family: arial;
                font-size: 12px;

            }
        </style>
    </head>
    <body>
        <span class="topheading">EPM (Exit Process Management)</span>
        <div class="generalFormContent">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <div class="exitWelcomeContent" align="center" >
                    <img src="${pageContext.request.contextPath}/images/Exit_flow.jpg"
                         alt="banner"
                         width=600px
                         align="center"
                         height=300px
                         />
                </div>
        </div>
</body>
</html>
