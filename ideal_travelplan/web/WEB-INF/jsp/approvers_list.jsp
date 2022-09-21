
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:if test="${fn:length(workflowList) > 0}">
    <div class="formContent_new" style="margin-bottom: 5px;">
        <span class="fieldsetHeading" style="color: #4C83B3;">Approver Details</span>
        <table class="tableStructure" border="0" width="95%">
            <tr>
                <th width="15%"></th>
                <c:forEach items="${workflowList}" var="workFlowList" varStatus="workFlowStatus">
                    <th>
                        <c:choose>
                            <c:when test="${workFlowList.approver == 'Finance'}">
                                Business Ops
                            </c:when>
                            <c:otherwise>
                                ${workFlowList.approver}
                            </c:otherwise>
                        </c:choose>
                    </th>
                </c:forEach>
            </tr>
            <tr>
                <th>Approver Name</th>
                <c:forEach items="${workflowList}" var="workFlowList">
                    <td>${workFlowList.approved_by}</td>
                </c:forEach>
            </tr>
            <tr>
                <th>Approved Date</th>
                <c:forEach items="${workflowList}" var="workFlowList">
                    <td>${workFlowList.approved_date}</td>
                </c:forEach>
            </tr>
            <tr>
                <th>Approve / Reject</th>
                <c:forEach items="${workflowList}" var="workFlowList">
                    <td>
                        <c:if test="${workFlowList.approved_status == 'a'}">
                            <c:if test="${workFlowList.approver == 'Admin'}">Booked</c:if>
                            <c:if test="${workFlowList.approver == 'Treasury'}">Deposited (${travelData.approved_advance})</c:if>
                            <c:if test="${workFlowList.approver != 'Admin' && workFlowList.approver != 'Treasury'}">Approved</c:if>
                        </c:if>
                        <c:if test="${workFlowList.approved_status == 'r'}">
                            Send Back
                        </c:if>
                    </td>
                </c:forEach>
            </tr>
            <tr>
                <th>Remarks</th>
                <c:forEach items="${workflowList}" var="workFlowList">
                    <td>${workFlowList.approver_remarks}</td>
                </c:forEach>
            </tr>
        </table>
    </div>
</c:if>
