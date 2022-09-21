<%-- 
    Document   : paging
    Created on : Jun 13, 2012, 7:03:00 PM
    Author     : 15065
--%>
<tr>
    <td>
        <table class="paging actionpaging"style="border: 1px solid #99BBE8;
        width: 940px;
    padding: 0px;
    border-left-width: -42px;
    height: 37px;
        margin-left: -4px;
    margin-top: 7px;"> 
            <tr>
                <td>
            
                <c:choose>
                    <c:when test="${paging_actions[1] != 1}">
                        <a onclick="loadForm1(1)" href="javascript:;"><< First</a>
                        <a onclick="loadForm1(${paging_actions[4]})" href="javascript:;">< Previous</a>
                    </c:when>
                    <c:otherwise>
                        <a href="javascript:;" class="inactive">< Previous</a>
                    </c:otherwise>
                </c:choose>
                <c:forEach var="page_action" begin="${paging_actions[2]}" end="${paging_actions[3]}" step="1" varStatus ="i">
                    <c:choose>
                        <c:when test="${page_action == paging_actions[1]}">
                            <a class="active" onclick="loadForm1(${page_action})" href="javascript:;"><b>${page_action}</b></a>
                        </c:when>
                        <c:otherwise>
                            <a onclick="loadForm1(${page_action})" href="javascript:;">${page_action}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:choose>
                    <c:when test="${paging_actions[1] != paging_actions[0]}">
                        <a onclick="loadForm1(${paging_actions[5]})" href="javascript:;">Next ></a>
                        <a onclick="loadForm1(${paging_actions[0]})" href="javascript:;">Last >></a>
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
