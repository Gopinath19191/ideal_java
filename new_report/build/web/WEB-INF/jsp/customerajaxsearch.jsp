<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${custRes}" var="it" varStatus="itr">
${it.customerName} | ${it.customerId}
</c:forEach>
