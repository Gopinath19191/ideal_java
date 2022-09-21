<%-- 
    Document   : paging
    Created on : Jun 13, 2012, 7:03:00 PM
    Author     : 15065
--%>

<tr>
    <td>
        <table class="paging">
            <tr>
                <td>
                    <c:choose>
                        <c:when test="${paging[1] != 1}">
                            <a onclick="loadForm('${actionName}',1)" href="javascript:;"><< First</a>
                            <a onclick="loadForm('${actionName}',${paging[4]})" href="javascript:;">< Previous</a>
                        </c:when>
                        <c:otherwise>
                            <a href="javascript:;" class="inactive">< Previous</a>
                        </c:otherwise>
                    </c:choose>
                    <c:forEach var="page" begin="${paging[2]}" end="${paging[3]}" step="1" varStatus ="i">
                        <c:choose>
                            <c:when test="${page == paging[1]}">
                                <a class="active" onclick="loadForm('${actionName}',${page})" href="javascript:;"><b>${page}</b></a>
                            </c:when>
                            <c:otherwise>
                                <a onclick="loadForm('${actionName}',${page})" href="javascript:;">${page}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:choose>
                        <c:when test="${paging[1] != paging[0]}">
                            <a onclick="loadForm('${actionName}',${paging[5]})" href="javascript:;">Next ></a>
                            <a onclick="loadForm('${actionName}',${paging[0]})" href="javascript:;">Last >></a>
                        </c:when>
                        <c:otherwise>
                           <a href="javascript:;" class="inactive"> Next ></a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </td>
</tr>
