<%-- 
    Document   : employeeHome
    Created on : Sep 29, 2011, 2:25:36 PM
    Author     : 14583
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<html>
    <head>
        <script type="text/javascript">
            function reject()
            {
                $('#formExitProcessMain').attr("action", "exitWelcome.htm");
                $('#formExitProcessMain').submit();
            }
            <%--function changeTabClass(tabId){
                alert("---"+tabId);
                $(".ticket_tab").removeClass("active_tab");
                $("#"+tabId).addClass("active_tab");
            }--%>
        </script>
    </head>
    <body onload="changeTabClass('resigForm');">
         <span class="topheading">EPM (Exit Process Management)</span>
         <div class="generalFormContent">
             <%@include file="/WEB-INF/jsp/menu.jsp" %>
             <form action="proceedToExit.htm" method="post" id="formExitProcessMain" name="formExitProcessMain">
             
                <c:choose>
                    <c:when test="${employeeDetails ne null && employeeDetails.submitStatus!=1 && employeeDetails.submitStatus!=null && employeeDetails.deleted!= 'y'}">
                        <div class="qpdSearch" align="center" style="height: 25px;">
                            Resignation Form submitted on
                            <fmt:formatDate value="${employeeDetails.resignedDate}" pattern="dd-MM-yyyy" var="resignedDate" />${resignedDate}
                        </div>
                        <div class="qpdSearch" style="height: auto;background-color: #FFF;">
                                <form action="" method="post" name="" id="">
                                    <table width="100%" id="formTable" class="qpdSearch" border="0" style="background-color: #FFF;margin-top: -7px;" >
                                        <tbody>
                                            <%--<tr>
                                                <td align="center" colspan="8">
                                                    Resignation Form submitted on
                                                    <fmt:formatDate value="${employeeDetails.resignedDate}" pattern="dd-MM-yyyy" var="resignedDate" />${resignedDate}
                                                </td>
                                            </tr>--%>
                                            <tr>
                                                <td width="12.5%">
                                                    <label class="">Employee ID: </label>
                                                </td>
                                                <td width="12.5%">
                                                    <span class="displayText">${employeeDetails.employeeNumber} </span>
                                                </td>
                                                <td width="12.5%">
                                                    <label class="">Employee Name: </label>
                                                </td>
                                                <td width="12.5%">
                                                    <span class="displayText">${employeeDetails.employeeName}</span>
                                                </td>
                                                <td width="12.5%">
                                                    <label class="">Band: </label>
                                                </td>
                                                <td width="12.5%">
                                                    <span class="displayText">${employeeDetails.band} </span>
                                                </td>
                                                <td width="12.5%">
                                                    <label class="">Sub Band: </label>
                                                </td>
                                                <td width="12.5%">
                                                    <span class="displayText">${employeeDetails.subBand}</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="12.5%">
                                                    <label class="">SBU: </label>
                                                </td>
                                                <td width="12.5%">
                                                    <span class="displayText">${employeeDetails.practiceGroup} </span>
                                                </td>
                                                <td width="12.5%">
                                                    <label class="">Reporting Manager: </label>
                                                </td>
                                                <td width="12.5%">
                                                    <span class="displayText">${employeeDetails.rmName}</span>
                                                </td>
                                                <td width="12.5%">
                                                    <label class="">Date of Joining: </label>
                                                </td>
                                                <td width="12.5%">
                                                <span class="displayText">
                                                  <fmt:formatDate value="${employeeDetails.empDoj}" pattern="dd-MM-yyyy" var="empDoj" />${empDoj}
                                                </span>
                                                </td>
                                                <td width="12.5%">
                                                    <label class="">Date of Resignation: </label>
                                                </td>
                                                <td width="12.5%">
                                                    <span class="displayText">
                                                     ${resignedDate}
                                                    </span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="12.5%">
                                                    <label class="">Contact Number: </label>
                                                </td>
                                                <td width="12.5%">
                                                    <span class="displayText">${employeeDetails.contactNo} </span>
                                                </td>
                                                <td width="12.5%">
                                                    <label class="">Contact Address: </label>
                                                </td>
                                                <td width="12.5%" colspan="5">
                                                    <span class="displayText">${employeeDetails.empAddress}</span>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="12.5%">
                                                    <label class="">Email ID: </label>
                                                </td>
                                                <td width="12.5%" colspan="1">
                                                    <span class="displayText">${employeeDetails.personalEmail}</span>
                                                </td>
                                                <td width="12.5%">
                                                    <label class="">Reason for relieving: </label>
                                                </td>
                                                <td width="12.5%" colspan="4">
                                                    <span class="displayText">${employeeDetails.leavingReason}</span>
                                                </td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </form>
                            </div>
                    </c:when>
                    <c:otherwise>
                        <div class="qpdSearch" style="height: auto;background-color: #FFF;">
                            <table width="100%">
                                <tbody>
                                    <tr >
                                        <td width="50%">
                                            Dear ${empName},
                                            <P align="justify" class="contentdata">
                                               ${employeeHomeContent}
                                            </P>
                                        </td>
                                    </tr>
                                    </tbody>
                            </table>
                                            <table class="qpdSearch buttonAlignment" align="center" style="background-color: #FFF;">
                                                <tbody>
                                                    <tr>                                                        <td align="center">
                                                            <input type="submit" name="btnNext" id="btnNext" value="Next" class="nextbutton">
                                                            <input type="button" name="btnCancel" id="btnCancel" value="Cancel" class="cancelbutton" onclick="reject()">
                                                        </td>
                                                    </tr>
                                                </tbody>
                                            </table>
                        </div>
                    </c:otherwise>
                </c:choose>
            </form>
         </div>
    </body>
</html>
