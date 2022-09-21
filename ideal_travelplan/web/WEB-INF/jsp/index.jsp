<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <p><b><center>Hello! This is the first page of TravelPlan(TP) </center></b></p>
        <p><b><center>TravelPlan </center></b></p>

    <!--Domestic-->
    <a href="authenticate.htm?empId=14583&modId=478">Domestic Travel</a><br>
    <a href="authenticate.htm?empId=14583&modId=479">International Travel</a><br>
    <a href="authenticate.htm?empId=14583&modId=480">Local Conveyance Travel</a><br>
    <a href="authenticate.htm?empId=14048&modId=481">RM Approval(14048-Sreeram)</a><br>
    <a href="authenticate.htm?empId=14545&modId=482">BUH Approval(14545-Dustur)</a><br>
    <a href="authenticate.htm?empId=14045&modId=486">MD Approval(14045-Subbu)</a><br>
    <a href="authenticate.htm?empId=15220&modId=483">Finance Approval(15220-Amarnath)</a><br>
    <a href="authenticate.htm?empId=14028&modId=484">CFO Approval(14028-SV)</a><br>
    <a href="authenticate.htm?empId=14068&modId=485">Admin Approval(14068-Sharad)</a><br>
    <a href="authenticate.htm?empId=14003&modId=606">Treasury Approval(14003-Satish Kumar)</a><br>
<% //response.sendRedirect("authenticate.htm?empId=14583&modId=478"); %>
<!--International-->
<%// response.sendRedirect("authenticate.htm?empId=14320&modId=479"); %>
<!--Local Conveyance-->
<% //response.sendRedirect("authenticate.htm?empId=15065&modId=480"); %>
<!--RM -->
<% //response.sendRedirect("authenticate.htm?empId=15065&modId=481"); %>
<!--BUH-->
<% //response.sendRedirect("authenticate.htm?empId=15065&modId=482"); %>
<!--Finance-->
<% //response.sendRedirect("authenticate.htm?empId=15065&modId=483"); %>
<!--CFO-->
<% //response.sendRedirect("authenticate.htm?empId=15065&modId=484"); %>
<!--Admin-->
<% //response.sendRedirect("authenticate.htm?empId=15065&modId=485"); %>
<!--MD-->
<% //response.sendRedirect("authenticate.htm?empId=15065&modId=486"); %>
<!--Treasury-->
<% //response.sendRedirect("authenticate.htm?empId=15065&modId=606"); %>

    </body>

<!--    <a href="test.htm?userName=14583">
        Click here for test
    </a>-->
</html>
