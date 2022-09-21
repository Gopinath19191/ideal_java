<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${empRes}" var="it" varStatus="itr">
${it.employeeName} | ${it.employee_id}
</c:forEach>
