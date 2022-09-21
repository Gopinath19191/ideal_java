<%-- 
    Document   : ajaxsearch
    Created on : Feb 5, 2018, 1:24:09 PM
    Author     : 16656
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${empRes}" var="it" varStatus="itr">
${it.employee_name} | ${it.employee_no}
</c:forEach>
