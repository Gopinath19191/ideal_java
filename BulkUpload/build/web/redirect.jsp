<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% //response.sendRedirect("authenticate.htm?empId=16283&modId=657"); %> <!-- upload page -->
<% response.sendRedirect("authenticate.htm?empId=16221&modId=658"); %> <!-- audit page  -->
<% //response.sendRedirect("authenticate.htm?empId=16283&modId=731"); %><!-- 731 audit page  -->
<% //response.sendRedirect("authenticate.htm?empId=16364&modId=732"); %><!-- 732 ns page  -->
