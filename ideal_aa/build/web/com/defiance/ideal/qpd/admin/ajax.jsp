<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<c:if test="${getDataFor eq 'employeeName'}">
    <c:forEach items="${employeeName}" var="data">
    ${data.employeeNumberAc}-${data.employeeNameAc}|${data.empIdAc}
    </c:forEach>
</c:if>

    

    