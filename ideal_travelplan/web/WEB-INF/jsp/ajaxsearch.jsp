<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${result}" var="resultset" varStatus="itr">
${resultset.valueRes}|${resultset.keyRes}
</c:forEach>
