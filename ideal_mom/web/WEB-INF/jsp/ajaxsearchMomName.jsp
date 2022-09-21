<%-- 
    Document   : ajaxsearchMomName
    Created on : Mar 9, 2018, 3:13:16 PM
    Author     : 16656
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${momRes}" var="it" varStatus="itr">
${it.mom_title} | ${it.id}
</c:forEach>
