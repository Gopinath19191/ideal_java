<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% //response.sendRedirect("../index.jsp"); %>

<!--Domestic-->
<% response.sendRedirect("authenticate.htm?empId=16221&modId=478"); %>
<!--International-->
<% //response.sendRedirect("authenticate.htm?empId=14402&modId=479"); %>
<!--Local Conveyance-->
<% //response.sendRedirect("authenticate.htm?empId=14402&modId=480"); %>
<!--RM -->
<% //response.sendRedirect("authenticate.htm?empId=14934&modId=481"); %>
<!--BUH-->
<% //response.sendRedirect("authenticate.htm?empId=14162&modId=482"); %>
<!--Finance-->
<% //response.sendRedirect("authenticate.htm?empId=15661&modId=483"); %>
<!--CFO-->
<% //response.sendRedirect("authenticate.htm?empId=14028&modId=484"); %>
<!--Admin-->
<% //response.sendRedirect("authenticate.htm?empId=14068&modId=485"); %>
<!--MD-->
<% //response.sendRedirect("authenticate.htm?empId=16469&modId=486"); %>
<!--Treasury-->
<%// response.sendRedirect("authenticate.htm?empId=14686&modId=606"); %>

<!--Domestic-->
<% //response.sendRedirect("authenticate.htm?empId=16221&modId=478"); %>
<!--International-->
<% //response.sendRedirect("authenticate.htm?empId=14162&modId=479"); %>
<!--Local Conveyance-->
<% //response.sendRedirect("authenticate.htm?empId=14312&modId=480"); %>
<!--RM Settlement -->
<% //response.sendRedirect("authenticate.htm?empId=14159&modId=796"); %>
<!--BUH Settlement-->
<% //response.sendRedirect("authenticate.htm?empId=16469&modId=797"); %>
<!--Finance Settlement-->
<% //response.sendRedirect("authenticate.htm?empId=14686&modId=799"); %>
<!--CFO Settlement-->
<% //response.sendRedirect("authenticate.htm?empId=15818&modId=800"); %>
<!--Admin-->
<% //response.sendRedirect("authenticate.htm?empId=8000133&modId=801"); %>
<!--Payroll Settlement-->
<% //response.sendRedirect("authenticate.htm?empId=8000552 &modId=798"); %>
<!--Treasury Settlement-->
<% //response.sendRedirect("authenticate.htm?empId=14686&modId=802"); %>
