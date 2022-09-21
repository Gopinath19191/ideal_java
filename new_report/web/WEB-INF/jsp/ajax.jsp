<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${projectList}" var="it" varStatus="itr">
${it.projId}|${it.projName}|
</c:forEach>
<c:forEach items="${projectDetails}" var="it" varStatus="itr">
${it.pstring}|${it.id}|
</c:forEach>
<c:forEach items="${employeeList}" var="it" varStatus="itr">
${it.empId}|${it.employeeName}|
</c:forEach>

