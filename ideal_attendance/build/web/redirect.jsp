<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% response.sendRedirect("authenticate.htm?empId=16221&modId=666"); %> <!-- upload page http://localhost:8080/BulkUpload/authenticate.htm?empId=16069&modId=658 -->
<% //response.sendRedirect("authenticate.htm?empId=16113&modId=659"); %> <!-- audit page  -->
 