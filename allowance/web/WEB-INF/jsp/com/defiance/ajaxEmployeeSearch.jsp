<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<c:forEach items="${empRes}" var="it" varStatus="itr">
${it.employee_name}|${it.employee_id}|${it.band_name}|${it.designation}|${it.rm_name}|${it.unit_name}|${it.sub_unit_name}
</c:forEach>
