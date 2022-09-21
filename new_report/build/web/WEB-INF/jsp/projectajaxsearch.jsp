<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${prjRes}" var="it" varStatus="itr">
${it.projectName} | ${it.projectId}
</c:forEach>
