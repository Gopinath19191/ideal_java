<%-- 
    Document   : paging
    Created on : 3 Sep, 2019, 12:24:45 PM
    Author     : 16221
--%>

<tr>
    <td>
        <table class="paging">
            <tr>
                <td>
            
                <c:choose>
                    <c:when test="${paging[1] != 1}">
                        <a onclick="loadForm(1)" href="javascript:;"><< First</a>
                        <a onclick="loadForm(${paging[4]})" href="javascript:;">< Previous</a>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:;" class="inactive">< Previous</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="page" begin="${paging[2]}" end="${paging[3]}" step="1" varStatus ="i">
                    <c:choose>
                        <c:when test="${page == paging[1]}">
                            <a class="active" onclick="loadForm(${page})" href="javascript:;"><b>${page}</b></a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="loadForm(${page})" href="javascript:;">${page}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${paging[1] != paging[0]}">
                        <a onclick="loadForm(${paging[5]})" href="javascript:;">Next ></a>
                        <a onclick="loadForm(${paging[0]})" href="javascript:;">Last >></a>
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
