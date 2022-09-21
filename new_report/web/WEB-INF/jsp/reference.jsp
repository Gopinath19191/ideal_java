<html>
    <head>
        <%@include file="header.jsp" %>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to Home page!</h1>
        <table>
        <%--<c:forEach items="${employeeList}" var="empl" varStatus="itrtr">
                <tr>
                    <td>
                        ${itrtr.index}
                    </td>
                    <td>
                        ${empl.userName}
                    </td>
                    <td>
                        ${empl.userPasswd}
                    </td>
                </tr>
         </c:forEach>--%>
        <select>
        <c:forEach items="${monthsMap}" var="mnth" varStatus="mnthitr">
                <tr>
                    <td>
                        ${mnthitr.index}
                    </td>
                    <td>
                        ${mnth.key}
                    </td>
                    <td>
                        ${mnth.value}
                    </td>
                </tr>
         </c:forEach>
        </select>

         <c:forEach items="${yearsMap}" var="yr" varStatus="yritr">
                <tr>
                    <td>
                        ${yritr.index}
                    </td>
                    <td>
                        ${yr.key}
                    </td>
                    <td>
                        ${yr.value}
                    </td>
                </tr>
         </c:forEach>

        <c:forEach items="${projectList}" var="pjct" varStatus="pjitr">
                <tr>
                    <td>
                        ${pjitr.index}
                    </td>
                    <td>
                        ${pjct.key}
                    </td>
                    <td>
                    ${pjct.value}
                    </td>
                </tr>
         </c:forEach>

        </table>
    </body>
</html>
<%--<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://java.sun.com/jsf/html"
	xmlns:p="http://primefaces.prime.com.tr/ui">

	<h:head>

	</h:head>

	<h:body>

		<p:spinner />

	</h:body>
</html>--%>

