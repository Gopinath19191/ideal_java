<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <body>
        <table border="1" style="border-collapse: collapse">
            <tr>
                <th>Name</th>
                <th>Passwords</th>
                <th>Group Id</th>
            </tr>
                <tr>
                    <td>
                        ${loginDetails.userName}
                    </td>
                    <td>
                        ${loginDetails.password}
                    </td>
                    <td>
                        ${loginDetails.groupId}
                    </td>
                </tr>
        </table>
    </body>
</html>
