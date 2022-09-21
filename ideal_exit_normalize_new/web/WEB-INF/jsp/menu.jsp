<%-- 
    Document   : menu
    Created on : Sep 29, 2011, 4:25:47 PM
    Author     : 14583
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="style_tab">
    <span id="home" class="ticket_tab active_tab" onclick="changeClass(this);">
        <a href="exitWelcome.htm">Exit Home</a>
    </span>
    <c:if test="${menuDetails.resignation_form==1}">
        <span id="resigForm" class="ticket_tab" onclick="changeClass(this);">
            <a href="employeeHome.htm">Resignation Form</a>
        </span>
    </c:if>
    <c:if test="${menuDetails.view_status==1}">
        <span id="viewStatus" class="ticket_tab" onclick="changeClass(this);">
            <a style="width: 80px;" href="viewStatus.htm">View Status</a>
        </span>
    </c:if>
    <c:if test="${menuDetails.exit_survey==1}">
        <span id="survey" class="ticket_tab" onclick="changeClass(this);">
            <a style="width: 80px;" href="exitSurvey.htm">Exit Survey</a>
        </span>
    </c:if>
    <c:if test="${menuDetails.rm_approval==1}">
        <span id="rmApp" class="ticket_tab" onclick="changeClass(this);">
            <a href="#" style="width: 90px;" onclick="submitMenuAction(${menuDetails.rm_approvalId})">RM Approval</a>
        </span>
    </c:if>
    <c:if test="${menuDetails.rm_clearance==1}">
        <span id="rmClr" class="ticket_tab" onclick="changeClass(this);">
            <a href="#" style="width: 90px;" onclick="submitMenuAction(${menuDetails.rm_clearanceId})">RM Clearance</a>
        </span>
    </c:if>
    <c:if test="${menuDetails.fin_approval==1}">
        <span id="finApp" class="ticket_tab" onclick="changeClass(this);">
            <a href="#" onclick="submitMenuAction(${menuDetails.fin_approvalId})">Finance Approval</a>
        </span>
    </c:if>
    <c:if test="${menuDetails.network_approval==1}">
        <span id="netApp" class="ticket_tab" onclick="changeClass(this);">
            <a href="#" style="width: 110px;" onclick="submitMenuAction(${menuDetails.network_approvalId})">Network Approval</a>
        </span>
    </c:if>
    <c:if test="${menuDetails.admin_approval==1}">
        <span id="adminApp" class="ticket_tab" onclick="changeClass(this);">
            <a href="#" onclick="submitMenuAction(${menuDetails.admin_approvalId})">Admin Approval</a>
        </span>
    </c:if>
    <c:if test="${menuDetails.hr_approval==1}">
        <span id="hrApp" class="ticket_tab" onclick="changeClass(this);">
            <a href="#" onclick="submitMenuAction(${menuDetails.hr_approvalId})">HR Approval</a>
        </span>
        </c:if>
    
    <c:if test="${hrApproved=='yes'}">
        <span id="resigLetter" class="ticket_tab" onclick="changeClass(this);" >
            <a href="#" onclick="getResLetter(${employeeId})" style="width: 114px;">Resignation Letter</a>
        </span>
    </c:if>
    <span class="ticket_tab" onclick="changeClass(this);">
        <a style="width: 80px;" href="logout.htm">Logout</a>
    </span>
</div>
<form method="POST" action="" name="menuAction" id="menuAction">
        <input type="hidden" name="moduleId" id="moduleId" value="" readonly />
        <input type="hidden" name="approveType" id="approveType" value="pending" readonly />
    </form>
    <form name="resLetterForm" id="resLetterForm" method="POST" action="#" >
        <input type="hidden" readonly name="resEmpId" id="resEmpId" value="" />
    </form>
<%--<div class="submenuBand">
    <ul>
            <c:if test="${menuDetails.resignation_form==1}">
                <li>
                    <a href="employeeHome.htm">Resignation Form</a>
                </li>
            </c:if>
            <c:if test="${menuDetails.view_status==1}">
                <li>
                    <a href="viewStatus.htm">View Status</a>
                </li>
            </c:if>
            <c:if test="${menuDetails.exit_survey==1}">
                <li>
                    <a href="exitSurvey.htm">Exit Survey</a>
                </li>
            </c:if>
            <c:if test="${menuDetails.rm_approval==1}">
                <li>
                    <a href="#" onclick="submitMenuAction(${menuDetails.rm_approvalId})">RM Approval</a>
                </li>
            </c:if>
            <c:if test="${menuDetails.rm_clearance==1}">
                <li>
                    <a href="#" onclick="submitMenuAction(${menuDetails.rm_clearanceId})">RM Clearance</a>
                </li>
            </c:if>
            <c:if test="${menuDetails.fin_approval==1}">
                <li>
                    <a href="#" onclick="submitMenuAction(${menuDetails.fin_approvalId})">Finance Approval</a>
                </li>
            </c:if>
            <c:if test="${menuDetails.network_approval==1}">
                <li>
                    <a href="#" onclick="submitMenuAction(${menuDetails.network_approvalId})">Network Approval</a>
                </li>
            </c:if>
            <c:if test="${menuDetails.admin_approval==1}">
                <li>
                    <a href="#" onclick="submitMenuAction(${menuDetails.admin_approvalId})">Admin Approval</a>
                </li>
            </c:if>
            <c:if test="${menuDetails.hr_approval==1}">
                <li>
                    <a href="#" onclick="submitMenuAction(${menuDetails.hr_approvalId})">HR Approval</a>
                </li>
            </c:if>
            <c:if test="${hrApproved=='yes'}">
                <li>
                    <a href="#" onclick="getResLetter(${employeeId})" >Resignation Acceptance Letter</a>
                </li>
            </c:if>
                <li>
                    <a href="logout.htm" style="margin-right: 500px;text-align: right">Logout</a>
                </li>

    </ul>

    <form method="POST" action="" name="menuAction" id="menuAction">
        <input type="hidden" name="moduleId" id="moduleId" value="" readonly />
        <input type="hidden" name="approveType" id="approveType" value="pending" readonly />
    </form>
    <form name="resLetterForm" id="resLetterForm" method="POST" action="#" >
        <input type="hidden" readonly name="resEmpId" id="resEmpId" value="" />
    </form>

</div>--%>
<script type="text/javascript">
    function submitMenuAction(moduleId)
    {
        $("#moduleId").val(moduleId);
        $('#menuAction').attr("action", "listRegnSubmittedEmp.htm");
        $('#menuAction').submit();
    }
    function getResLetter(resEmpId){
            $('#resEmpId').val(resEmpId);
            $('#resLetterForm').attr("action", "resLetterGeneration.htm");
            $('#resLetterForm').submit();
        }
    function getServLetter(resEmpId){
            $('#resEmpId').val(resEmpId);
            $('#resLetterForm').attr("action", "servLetterGeneration.htm");
            $('#resLetterForm').submit();
        }
    function publish(resEmpId){
            if(confirm('Are you sure to publish the service letter?')==true){
            $('#resEmpId').val(resEmpId);
            $('#resLetterForm').attr("action", "publishServLetter.htm");
            $('#resLetterForm').submit();
            }
            else{
                
            }
        }
        <%--
        function hrExitTriggerMenuAction(moduleId)
        {        
            $("#moduleId").val(moduleId);
            $('#menuAction').attr("action", "listEmpExitSubmittedHR.htm");
            $('#menuAction').submit();
        }
        --%>
        function changeClass(val){
        <%--$(".ticket_tab").removeClass("active_tab");--%>
        $("#"+val.id).addClass("active_tab");
        <%--$(".ticket_tab").removeClass("active_tab");--%>
        <%--$(val).addClass("active_tab");--%>
    }
    function changeTabClass(tabId){
                <%--alert("---"+tabId);--%>
                $(".ticket_tab").removeClass("active_tab");
                $("#"+tabId).addClass("active_tab");
            }
</script>
