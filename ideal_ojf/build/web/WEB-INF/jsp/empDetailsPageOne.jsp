<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<title>EMPLOYEE_DETAILS</title>
<style type="text/css">
    .printContentED1 p{
        text-align:justify;
        line-height: 35px;
    }
    .printContentED1 .paraIndent{
        text-indent:40px;
     }
    .printContentED1 .indentLeft{
        margin-left: 300px;
    }
    .printContentED1 .italicText{
        font-style: italic;
    }
    .printContentED1 tr{
        line-height: 24px;
    }

    .printContentED1 table tr td{
        font-size: 13px;
        font-family:"Times New Roman","serif";
    }

    .printContentED1{
        width:650px;
        height:900px;
       /* border: 1px solid #000000;*/
	   /* border: 1px solid #000000;*/
        position: relative;
    }
</style>

    <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
</head>

<div class="printContentED1">
            <jsp:include page="printHeader.jsp"></jsp:include>
            <table border="0" width="100%">
            <tr>
                <td colspan="5" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="5" align="center">
                    <u><b>EMPLOYEE DETAILS</b></u>
                </td>
            </tr>
            <tr class="lineHeight">
                <td >1.Name of Employee </td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3" style="font-weight: bold">${joinerReportData.firstName}${joinerReportData.middleName}&nbsp;&nbsp;${joinerReportData.lastName}</td>
            </tr>
            <tr class="lineHeight">
                    <td width="150">2.Gender</td>
                    <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                    <td colspan="3">
                        <c:if test="${employeeData.gender=='m'}">
                            Male
                        </c:if>
                        <c:if test="${employeeData.gender=='f'}">
                            Female
                        </c:if>    
                    </td>
            </tr>
            <tr class="lineHeight">
                <td>3. Father Name</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.fatherName!=''}">
                            ${employeeData.fatherName}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr class="lineHeight">
                <td>4. Marital Status</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.maritalStatus!=null}">
                            <c:forEach items="${maritalStatus}" var="maritalStatusValues" varStatus="i">
                                <c:if test="${maritalStatusValues.maritalstatusId==employeeData.maritalStatus}">
                                    ${maritalStatusValues.maritalstatusName}
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <c:if test="${employeeData.maritalStatus == 'm'}">
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Spouse Name</td>
                    <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${employeeData.spouseName!=null}">
                                ${employeeData.spouseName}
                            </c:when>
                            <c:otherwise>
                                
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Date of Marriage</td>
                    <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                    <td colspan="3">
                        <c:choose>
                            <c:when test="${employeeData.dateOfMarriage!=null}">
                                ${employeeData.dateOfMarriage}
                            </c:when>
                            <c:otherwise>
                                
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:if>
            
            <tr>
                <td>5.Date of Birth</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${joinerReportData.dateOfBirth!=''}">
                            ${joinerReportData.dateOfBirth}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>6.Place of Birth</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.placeOfBirth!=''}">
                            ${employeeData.placeOfBirth}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>7.Religion</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.religion!=''}">
                            ${employeeData.religion}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>8.Mother Tongue</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.motherTongue!=''}">
                            ${employeeData.motherTongue}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>9.Nationality</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    ${employeeData.nationality}
                    
                </td>
            </tr>
            <tr>
                <td>10.Present Address</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.presentAddress!=''}">
                            ${employeeData.presentAddress}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>11.Mobile No</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.phoneNumberPresent!=''}">
                            ${employeeData.phoneNumberPresent}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>12. E-mail ID </td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${joinerReportData.personalEmailId1!=''}">
                            ${joinerReportData.personalEmailId1}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>13.Permanent Address</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.permanentAddress!=''}">
                            ${employeeData.permanentAddress}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>14.Previous PF No.</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${employeeData.previousPfNumber!=''}">
                            ${employeeData.previousPfNumber}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>15.PAN No.</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${joinerReportData.panNo!=''}">
                            ${joinerReportData.panNo}
                        </c:when>
                        <c:otherwise>
                           
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>16.Bank details</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="3">Bank Name:
                    <c:choose>
                        <c:when test="${employeeData.bankName!=''}">
                            <c:forEach items="${bankName}" var="bankNameValue" varStatus="i">
                                <c:if test="${i.index==employeeData.bankName}">
                                    <b>&nbsp;&nbsp;${bankNameValue}&nbsp;&nbsp;</b>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td colspan="3">
                    Name (as per Bank Records) :
                    <c:choose>
                        <c:when test="${employeeData.nameInBankRecords!=''}">
                            ${employeeData.nameInBankRecords}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td colspan="3">Branch:
                    <c:choose>
                        <c:when test="${employeeData.branch!=''}">
                            <b>&nbsp;&nbsp;${employeeData.branch}&nbsp;&nbsp;</b>
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td></td>
                <td></td>
                <td colspan="3">Account No:
                    <c:choose>
                        <c:when test="${employeeData.accountNumber!=''}">
                            <b>&nbsp;&nbsp;${employeeData.accountNumber}&nbsp;&nbsp;</b>
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
</div>



