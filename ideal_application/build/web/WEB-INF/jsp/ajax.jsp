<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${projectList}" var="it" varStatus="itr">
${it.projId}|${it.projName}|
</c:forEach>
<c:forEach items="${employeeList}" var="it" varStatus="itr">
${it.employeeName}  | ${it.empId}
</c:forEach>
