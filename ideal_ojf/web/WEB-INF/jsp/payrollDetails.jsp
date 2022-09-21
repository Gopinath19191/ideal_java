<%-- 
    Document   : payrollDetails
    Created on : Dec 3, 2010, 12:13:18 PM
    Author     : Admin
--%><head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
       
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payroll Details</title>
    </head>
<c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>

    <div class="printContentPayDet">
         <jsp:include page="./printHeader.jsp"></jsp:include>
            <table width="100%" border = "0">
            <tr>
                <td colspan="4" align="center">
                    <br><u><b>Payroll Details</b></u><br><br><br>
                </td>
            </tr>
            <tr>
                <td>1.Name of Employee </td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="2">${joinerReportData.firstName}${joinerReportData.middleName}&nbsp;&nbsp;${joinerReportData.lastName}</td>
            </tr>
            <tr>
                <td>2.Gender</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="2">
                    <c:if test="${employeeData.gender=='m'}">
                        Male
                    </c:if>
                    <c:if test="${employeeData.gender=='f'}">
                        Female
                    </c:if>    
                </td>
            </tr>
            <tr>
                <td>3.Father Name</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="2">
                    <c:choose>
                        <c:when test="${employeeData.fatherName!=null}">
                            ${employeeData.fatherName}
                        </c:when>
                        <c:otherwise>
                            ____________________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            
            <tr>
                <td>4.Spouse Name(if married)</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="2">
                    <c:choose>
                        <c:when test="${employeeData.spouseName!=null}">
                            ${employeeData.spouseName}
                        </c:when>
                        <c:otherwise>
                            ____________________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>5.Date of Birth</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td colspan="2">
                    <c:choose>
                        <c:when test="${joinerReportData.dateOfBirth!=null}">
                            ${joinerReportData.dateOfBirth}
                        </c:when>
                        <c:otherwise>
                            
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>6.Bank Name</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td  colspan="2">
                    <c:choose>
                        <c:when test="${employeeData.bankName!=null}">
                            <c:forEach items="${bankName}" var="bankNameValues" varStatus="i">
                                <c:if test="${i.index==employeeData.bankName}">
                                    ${bankNameValues}
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            ____________________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>7.Account Number</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td  colspan="2">
                    <c:choose>
                        <c:when test="${employeeData.accountNumber!=null}">
                            ${employeeData.accountNumber}
                        </c:when>
                        <c:otherwise>
                            ____________________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>8.PAN Number</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td  colspan="4">
                    <c:choose>
                        <c:when test="${joinerReportData.panNo!=null}">
                            ${joinerReportData.panNo}
                        </c:when>
                        <c:otherwise>
                            ____________________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>9.Nationality</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td  colspan="2">
                    ${employeeData.nationality}
                </td>
            </tr>
            <tr>
                <td>10.Hinduja Tech Emp Id</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td  colspan="2">
                    <c:choose>
                        <c:when test="${joinerReportData.joinerEmpId!=null && joinerReportData.jfStatus == 6}">
                            ${joinerReportData.joinerEmpId}
                        </c:when>
                        <c:otherwise>
                            ___________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>  
            <tr>
                <td>11.Hinduja Tech Mail id</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td  colspan="2">
                    <c:choose>
                        <c:when test="${joinerReportData.firstName!='' && joinerReportData.lastName!=''}">
                              ${joinerReportData.firstName}.${joinerReportData.lastName}@hindujatech.com
                        </c:when>
                        <c:otherwise>
                            ____________________________________
                        </c:otherwise>
                    </c:choose>
              </td>
            </tr>
            <tr>
                <td>12.Personal Mail id</td>
                <td width="1px" style="font-weight: bold">:&nbsp;&nbsp;</td>
                <td  colspan="4">
                    <c:choose>
                        <c:when test="${joinerReportData.personalEmailId1!=null}">
                           ${joinerReportData.personalEmailId1}
                        </c:when>
                        <c:otherwise>
                            ____________________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <c:choose>
                <c:when test="${fn:length(emergencyData)!=0}">
                    <c:forEach items="${emergencyData}" var="emergencyValue" varStatus="emergency">
                        <c:if test="${emergency.count == 1}">
                            <tr>
                                <td>
                                    13.Emergency Contact:
                                </td>
                                <td width="1px" style="font-weight: bold">:</td>
                                <td  colspan="4">&nbsp;Name:${emergencyValue.nameX}</td>
                            </tr>
                            <tr>
                                <td ></td>
                                <td width="1px" style="font-weight: bold">:</td>
                                <td  colspan="4">&nbsp;Telephone (land line) : ${emergencyValue.home_phone_numberX}</td>
                            </tr>
                            <tr>
                                <td ></td>
                                <td width="1px" style="font-weight: bold">:</td>
                                <td  colspan="4">&nbsp;Telephone (Mobile) : ${emergencyValue.mobile_numberX}</td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <tr>
                        <td>
                            13.Emergency Contact:
                        </td>
                        <td width="1px" style="font-weight: bold">:</td>
                        <td  colspan="4">&nbsp;Name:_______________________________</td>
                    </tr>
                    <tr>
                        <td ></td>
                        <td width="1px" style="font-weight: bold">:</td>
                        <td  colspan="4">&nbsp;Telephone :(land line)____________________</td>
                    </tr>
                    <tr>
                        <td ></td>
                        <td width="1px" style="font-weight: bold">:</td>
                        <td  colspan="4">&nbsp;Telephone :(Mobile)_____________________</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>
</div>
<style type="text/css">
.printContentPayDet table tr{
        height:35px;
}

.printContentPayDet{
    width:650px;
    height:900px;
    /*border: 1px solid #000000;*/
    position: relative;
}
</style>
