<%-- 
    Document   : momajaxsearch
    Created on : 9 Mar, 2018, 2:35:40 PM
    Author     : 8000458
--%>



<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>

<c:forEach items="${momRes}" var="mom" varStatus="itr">
${mom.mom_title} | ${mom.id}
</c:forEach>