<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%  //response.sendRedirect("index.htm"); %>
<%  response.sendRedirect("authenticate.htm?empId=16221&modId=846"); %>
<% //response.sendRedirect("authenticate.htm?empId=16283&modId=441&access=HR"); %>
<% //response.sendRedirect("authenticate.htm?empId=14152&modId=441&access=RM"); %>
<% //response.sendRedirect("authenticate.htm?empId=14312&modId=656&access=PM"); %>
<% //response.sendRedirect("authenticate.htm?empId=14312&modId=656&access=PM"); %>