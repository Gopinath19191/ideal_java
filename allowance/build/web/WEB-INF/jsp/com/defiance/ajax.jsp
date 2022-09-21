<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${employeeList}" var="it" varStatus="itr">
${it.empName}  | ${it.empId}
</c:forEach>
