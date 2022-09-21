<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<c:forEach items="${customerList}" var="customerList" >
   $('#customer_id').append($("<option></option>").attr("value",<c:out value="${customerList.customer_id}"/>).text('<c:out value="${customerList.customer_name}"/>'));
</c:forEach>