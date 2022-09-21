<%-- 
    Document   : paymentOfGratuityTwo
    Created on : Dec 3, 2010, 11:05:49 AM
    Author     : Admin
--%><head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Payment Of Gratuity Rules</title>
    </head>
<c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
    <div class="printContentGratuity2">
         <jsp:include page="./printHeader.jsp"></jsp:include>
            <table width="100%" border = "0">
            <tr>
                <td colspan="2">
                    <p style="text-align:justify;text-indent:0px;">5.Department / Branch / Section where employed : <b>${joinerReportData.practiceGroup} / ${joinerReportData.subPracticeGroup}</b>
                        <br>6.Post held with Ticket or Serial No., if any. (Employee No.) :
                        <c:choose>
                             <c:when test="${joinerReportData.joinerEmpId != null && joinerReportData.jfStatus == 6}">
                                <b>${joinerReportData.joinerEmpId}</b>
                             </c:when>
                                <c:otherwise>
                                ___________________
                            </c:otherwise>
                         </c:choose>
                        <br>7.  Date of appointment :
                        <c:choose>
                             <c:when test="${joinerReportData.joinedDate !=null && joinerReportData.jfStatus == 6 }">
                                <b>${joinerReportData.joinedDate}</b>
                             </c:when>
                                <c:otherwise>
                                ___________________
                            </c:otherwise>
                         </c:choose>
                        
                         <br>8.  Permanent address :
                         <c:choose>
                             <c:when test="${employeeData.permanentAddress!=null}">
                                <b>${employeeData.permanentAddress}</b>
                             </c:when>
                                <c:otherwise>
                                _______________________________
                                <br>___________________________
                            </c:otherwise>
                         </c:choose>
                        <br><br></p>
                </td>
            </tr>
            <tr>
                <td width="50%"></td>
                <td align="center">
                    <c:choose>
                        <c:when test="${joinerReportData.empSignatureFileName!='' && joinerReportData.empSignatureFileName!=null}">
                            <img alt="Employee Photo here" src="http://ideal.hindujatech.com/uploads/ojf_files/${joinerReportData.empSignatureFileName}" style="width: 150px;height:50px;"  />
                        </c:when>
                        <c:otherwise>
                            <img alt="Employee Photo here" src="${pageContext.request.contextPath}/css/images/sign.png" style="width: 150px;height:50px;" />
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td width="50%"><b>Place :</b> ${authorizerData.cityName}</td><td align="center">( ${joinerReportData.firstName} ${joinerReportData.lastName} )</td>
            </tr>
            <tr>
                <td class="lineHeight" width="50%"><b>Date :</b> ${joinerReportData.joinedDate}</td><td align="right"><b>Signature/Thumb impression of the employee</b></td>
            </tr>
            <tr><td align="center" colspan="2"><br><i><b><u>Declaration by witnesses</u></b></i><br></td></tr>
        </table>
            <table border="0" width="100%"><tr>
                    <td colspan="3"><br>Fresh nomination signed/ thumb impressed before me.</td></tr>
            <tr>
                <td width="300" colspan="2">Name in full and full address of witnesses.</td>
                <td>Signature of witness.</td>
            </tr>
            <tr><td></td></tr><tr><td></td></tr>
            <tr>
                <td width="300" colspan="2">1.</td>
                <td>1.</td>
            <tr>
                <td width="300" colspan="2">2.</td>
                <td>2.</td>
            </tr>
            <tr>
                <td colspan="3">
                    Place : ${authorizerData.cityName}
                    <br>
                    Date : ${joinerReportData.joinedDate}
                </td>
            </tr>
            <tr><td colspan="3" align="center"><b><i><u>Certificate by the Employer</u></i></b></td></tr>
            <tr><td colspan="3" class="lineHeight">Certified that the particulars of the above nomination have been verified and recorded in this </td></tr>
        </table>
            <table border="0" width="100%">
            <tr>
                <td  colspan="3" class="lineHeight">Employer's Reference No., if any.</td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
                <td align="center"><br>Signature of the employer/officer authorized.</td>
            </tr>
            <tr>
                <td colspan="2">&nbsp;</td>
                <td align="center">Designation</td>
            </tr>
            <tr><td colspan="3">&nbsp;<br></td></tr>
            <tr><td colspan="3">&nbsp;<br></td></tr>
            <tr>
                <td colspan="2">Date :${joinerReportData.joinedDate}</td>
                <td>Name and address of the establishment or Rubber stamp thereof.</td>
            </tr>
            <tr><td></td></tr><tr><td></td></tr>
            <tr>
                <td colspan="3" align="center"><i><u>Acknowledgement by the Employee</u></i></td>
            </tr>
            <tr>
                <td colspan="3" class="lineHeight">
                    <p style="text-align:justify;text-indent:40px;">Received the duplicate copy of the nomination in Form
                        <c:choose>
                            <c:when test="${joinerReportData.firstName!=null || joinerReportData.lastName!=null}">
                                <b>${joinerReportData.firstName} ${joinerReportData.lastName}</b>
                            </c:when>
                            <c:otherwise>
                                _______________________________________________________
                            </c:otherwise>
                        </c:choose>
                         filled by me on <b>${joinerReportData.joinedDate}</b> duly certified by the employer
                    </p>
                </td>
            </tr>
        </table>
        <table width="100%" border = "0" class="printFont">
            <tr>
                <td width="50%"></td>
                <td align="center">
                    <c:choose>
                        <c:when test="${joinerReportData.empSignatureFileName!='' && joinerReportData.empSignatureFileName!=null}">
                            <img alt="Employee Photo here" src="http://ideal.hindujatech.com/uploads/ojf_files/${joinerReportData.empSignatureFileName}" style="width: 150px;height:50px;"  />
                        </c:when>
                        <c:otherwise>
                            <img alt="Employee Photo here" src="${pageContext.request.contextPath}/css/images/sign.png" style="width: 150px;height:50px;" />
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td align="center">( ${joinerReportData.firstName} ${joinerReportData.lastName} )</td>
            </tr>
            <tr>
                <td><b>Date :</b>${joinerReportData.joinedDate}</td>
                <td align="center"><b>Signature of the Employee</b></td>
            </tr>
        </table>
</div>
        <style type="text/css">
                .printContentGratuity2{
                width:650px;
                height:900px;
                /*border: 1px solid #000000;*/
                position: relative;
            }
            .lineHeight
            {
                line-height: 180%;
}
            </style>