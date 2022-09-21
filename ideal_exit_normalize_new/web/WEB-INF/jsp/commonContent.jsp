<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
    <div class="qpdSearch" style="height: auto;">
        <table style="padding-bottom:10px;" width="99%" border="0"  id="formTable">
            <tbody>
                <c:if test="${exitSurveyProp==null}">
                    <tr>
                        <td align="center" colspan="8">
                            <div class="">FULL & FINAL SETTLEMENT CLEARANCE FORM</div>
                        </td>
                    </tr>
                </c:if>
                <tr style="padding-bottom:10px;">
                    <td>
                        <label >Employee ID</label>
                    </td>
                    <td>
                        <span class="displayText">${exitEmpInfo.employeeNumber} </span>
                    </td>
                    <td>
                        <label >Employee Name</label>
                    </td>
                    <td>
                        <span class="displayText" name="resEmpId">${exitEmpInfo.employeeName}</span>
                    </td>
                    <td>
                        <label >Date of Joining</label>
                    </td>
                    <td>
                        <span class="displayText">
                            <fmt:formatDate value="${exitEmpInfo.empDoj}" pattern="dd-MM-yyyy" var="empDoj" />${empDoj}
                        </span>
                    </td>
                    <td>
                        <label >Date of Resignation</label>
                    </td>
                    <td>
                        <span class="displayText">
                            <fmt:formatDate value="${exitEmpInfo.resignedDate}" pattern="dd-MM-yyyy" var="resignedDate" />${resignedDate}
                        </span>
                    </td>
                </tr>
                <tr style="padding-bottom:10px;">
                    <td>
                        <label >Date of Relieving</label>
                    </td>
                    <td>
                        <span class="displayText">
                            <fmt:formatDate value="${rmActionData.lastWorkingDate}" pattern="dd-MM-yyyy" var="lastWorkingDate" /> ${lastWorkingDate}
                        </span>
                    </td>
                    <td>
                        <label >Last Working Date</label>
                    </td>
                    <td>
                        <span class="displayText">
                            ${lastWorkingDate}
                        </span>
                    </td>
                    <td>
                        <label >SBU</label>
                    </td>
                    <td>
                        <span class="displayText">${exitEmpInfo.practiceGroup} </span>
                    </td>
                    <td>
                        <label>Reporting Manager</label>
                    </td>
                    <td>
                        <span class="displayText">${exitEmpInfo.rmName}</span>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label>Leave Balance</label>
                    </td>
                    <td>
                        <span class="displayText">${exitEmpInfo.leaveBalance}</span>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</body>
