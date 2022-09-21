<%--
Views should be stored under the WEB-INF folder so that
they are not accessible except through controller process.

This JSP is here to provide a redirect to the dispatcher
servlet but should be the only JSP outside of WEB-INF.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% //response.sendRedirect("authenticate.htm?empId=16283&modId=546&access=INNER"); %>
 
<% //response.sendRedirect("authenticate.htm?empId=16113&modId=657"); %> <!-- upload page -->
<% //response.sendRedirect("authenticate.htm?empId=16113&modId=658"); %> <!-- audit page  -->
<% response.sendRedirect("authenticate.htm?empId=15690&modId=734"); %><!-- 734 rm kra page  -->
<% //response.sendRedirect("authenticate.htm?empId=16221&modId=734"); %><!-- 733 employee kra  -->
<% //response.sendRedirect("authenticate.htm?empId=16069&modId=736"); %><!-- 733 employee kra  -->