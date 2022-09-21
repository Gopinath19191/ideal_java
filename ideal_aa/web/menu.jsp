<c:if test="${!empty(loginId)}">
        <div class="style_tab">
            <c:if test="${isAppraisee==1}">
                <span id="aaForm" class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/appraisee/begin.do" value="Appraisal Form" />
                </span>
            </c:if>
            <c:if test="${isAppraiser==1}">
                <span id="aaAppraisees" class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/appraiser/begin.do" value="Appraisees" />
                </span>
            </c:if>
            <c:if test="${isReviewer==1}">
                <span id="aaReviewer" class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/begin.do" value="Reviewer" />
                </span>
            </c:if>
            <c:if test="${isNormaliser==1}">
                <span id="aaFinalReviewer" class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/beginNormalizer.do" value="Final Reviewer" />
                </span>
            </c:if>
            <c:if test="${isBuh==1}">
                <span id="aaBuh" class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/beginBuh.do" value="BUH Review" />
                </span>
            </c:if>
            <c:if test="${isHr==1}">
                <span id="aaHr" class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/beginHr.do" value="Human Resource" />
                </span>
            </c:if>
            <c:if test="${isFinance==1}">
                <span id="aaFinance" class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/beginFinance.do" value="Finance" />
                </span>
            </c:if>
            <c:if test="${isHr==1}">
                <span id="aaTrigger" class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/admin/begin.do" value="Trigger Appraisal" />
                </span>
            </c:if>
            <c:if test="${isHr==1}">
                <span id="aaChangeData"  class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/admin/change.do" value="Change Appraisee Data" />
                </span>
            </c:if>
                <span id="aaPeriod" class="ticket_tab active_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/processDuration.do" value="Change Appraisal Year" />
                </span>
                <span class="ticket_tab" onclick="changeClass(this);">
                   <netui:anchor href="${pageContext.request.contextPath}/logout.do" value="Logout" />
                </span>
        </div>
        <%--<div class="submenuBand">
        <ul>
            <c:if test="${isAppraisee==1}">
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/appraisee/begin.do" value="QPD Form" /></b>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/appraisee/begin.do" value="Appraisal Form" /></b>
            </li>

            </c:if>
            <c:if test="${isAppraiser==1}">
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/appraiser/begin.do" value="Appraisees" /></b>
            </li>
            </c:if>
            <c:if test="${isReviewer==1}">
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/begin.do" value="Reviewer" /></b>
            </li>
            </c:if>
            <c:if test="${isNormaliser==1}">
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/beginNormalizer.do" value="Final Reviewer" /></b>
            </li>
            </c:if>
            <c:if test="${isHr==1}">
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/beginHr.do" value="HR" /></b>
            </li>
            </c:if>
            <c:if test="${isFinance==1}">
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/managers/beginFinance.do" value="Finance" /></b>
            </li>
            </c:if>
            <c:if test="${isHr==1}">
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/admin/begin.do" value="Appraisal Trigger Screen" /></b>
            </li>
            </c:if>
            <c:if test="${isHr==1}">
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/com/defiance/ideal/qpd/admin/change.do" value="Change Appraisee Data" /></b>
            </li>
            </c:if>
            <li>
                <b><netui:anchor href="${pageContext.request.contextPath}/processDuration.do" value="Change QPD Period" /></b>
                <b><netui:anchor href="${pageContext.request.contextPath}/processDuration.do" value="Change Appraisal Year" /></b>
            </li>
            <li style="float:right">
                <b><netui:anchor href="${pageContext.request.contextPath}/logout.do" value="Logout" /></b>
            </li>
        </ul>
        </div>--%>
    </c:if>