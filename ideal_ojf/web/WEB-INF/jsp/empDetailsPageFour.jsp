<%-- 
    Document   : empDetailsPageFour
    Created on : Dec 2, 2010, 12:06:54 PM
    Author     : Admin
--%><head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Details</title>
    </head>
    <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>


    <div class="printContentED4">
         <jsp:include page="./printHeader.jsp"></jsp:include>
         <table border="0" width="100%" class="printFont">
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;">
                        <b>22. Passport Details:</b><br>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="1" width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse;">
                        <tr>
                            <th width = "25%" >Passport Number</th>
                            <th width = "25%">Date of Issue</th>
                            <th width = "25%">Place of Issue</th>
                            <th width = "25%" class="lastTd" >Date of Expiry</th>
                        </tr>
                        <c:choose>
                             <c:when test="${fn:length(passportData)!=0}">
                                <c:forEach items="${passportData}" var="passportDataValues" varStatus="i">
                                    <tr>
                                        <td>&nbsp;${passportDataValues.passportNumberX}&nbsp;</td>
                                        <td>&nbsp;${passportDataValues.passDateOfIssueX}&nbsp;</td>
                                        <td>&nbsp;${passportDataValues.passPlaceOfIssueX}&nbsp;</td>
                                        <td>&nbsp;${passportDataValues.passDateOfExpiryX}&nbsp;</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </td>
            </tr>
<!--            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>-->
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;">
                        <b>23. Driving License:</b><br>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="1" width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse;">
                        <tr>
                            <th width = "25%" >Number</th>
                            <th width = "25%">Date of Issue</th>
                            <th width = "25%">Place of Issue</th>
                            <th width = "25%" class="lastTd" >Date of Expiry</th>
                        </tr>
                        <tr>
                            <td>&nbsp;${employeeData.dlNumber}&nbsp;</td>
                            <td>&nbsp;${employeeData.dlDateOfIssue}&nbsp;</td>
                            <td>&nbsp;${employeeData.dlPlaceOfIssue}&nbsp;</td>
                            <td>&nbsp;${employeeData.dlDateOfExpiry}&nbsp;</td>
                        </tr>
                    </table>
                </td>
            </tr>
<!--            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>-->
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;">
                        <b>24. Emergency Contacts :</b><br>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="1" width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse;">
                        <tr>
                            <th width = "20%" >Name</th>
                            <th width = "20%">Relationship</th>
                            <th width = "20%">Mobile Number</th>
                            <th width = "20%">Home Phone Number</th>
                            <th width = "20%" class="lastTd" >Work Phone Number</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(emergencyData)!=0}">
                                <c:forEach items="${emergencyData}" var="emergencyValue" varStatus="i">
                                    <tr>
                                        <td>
                                            &nbsp;${emergencyValue.nameX}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${emergencyValue.relationshipX}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${emergencyValue.mobile_numberX}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${emergencyValue.home_phone_numberX}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${emergencyValue.work_phone_numberX}&nbsp;
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </td>
            </tr>
<!--            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>-->
        </table>
        <table border="0" class="printFont">
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;">
                        <b>25.	Details of persons known / related working in Hinduja Tech and/or Companies of the Hinduja Group</b><br>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="1" width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse;">
                        <tr>
                            <th width="4%">S.No</th>
                            <th width="37%">Name</th>
                            <th width="36%">Company / Department</th>
                            <th width="27%">Relationship</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(referenceData)!=0}">
                                <c:forEach items="${referenceData}" var="referenceDataValues" varStatus="i">
                                    <tr>
                                        <td>&nbsp;${i.index+1}&nbsp;</td>
                                        <td>
                                            &nbsp;${referenceDataValues.nameOfPersonAtComDb}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${referenceDataValues.companyAndDepDb}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${referenceDataValues.pacRelationDb}&nbsp;
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </td>
            </tr>
<!--            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>-->
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;">
                        <b>26.	Give Two References from your Earlier Employer:</b><br>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table width="100%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
                        <tr>
                            <th width="4%">S.No</th>
                            <th width="17%">Name</th>
                            <th width="21%">Company Name / Department</th>
                            <th width="22%">Address & Telephone number</th>
                            <th width="18%">Designation / Role</th>
                            <th width="18%">No. of years known</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(prevCompData)!=0}">
                                <c:forEach items="${prevCompData}" var="prevCompDataValues" varStatus="i">
                                    <tr>
                                        <td>&nbsp;${i.index+1}&nbsp;</td>
                                        <td>
                                            &nbsp;${prevCompDataValues.refFrmEarNameDb}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${prevCompDataValues.refFrmEarCmpNameDb}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${prevCompDataValues.refFrmEarAddressAndTelDb}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${prevCompDataValues.refFrmEarDesignationDb}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${prevCompDataValues.refFrmEarNoOfYearsKnownDb}&nbsp;
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" align = "justify">
                    <i>I do hereby declare that the information furnished as above by me is true and correct to the best of my knowledge and belief. If any information furnished by me is proved to be incorrect and false, management may take appropriate action against me including termination of services.</i>
                    <br><br>
                </td>
            </tr>
            <tr>
                <td width="1249">
                    <c:choose>
                        <c:when test="${authorizerData.employeeId!='' && authorizerData.employeeId!=null}">
                            <img alt="Employee Photo here" src="http://ideal.hindujatech.com/uploads/ojf_files/${authorizerData.employeeId}_hr_sign.jpg" style="width: 150px;height:50px;"  />
                        </c:when>
                        <c:otherwise>
                            <img alt="Employee Photo here" src="${pageContext.request.contextPath}/css/images/sign.png" style="width: 150px;height:50px;"  />
                        </c:otherwise>
                    </c:choose>
                </td>
                <td width="484">
                    <c:choose>
                        <c:when test="${joinerReportData.empSignatureFileName!='' && joinerReportData.empSignatureFileName!=null}">
                            <img alt="Employee Photo here" src="http://ideal.hindujatech.com/uploads/ojf_files/${joinerReportData.empSignatureFileName}" style="width: 150px;height:50px;"  />
                        </c:when>
                        <c:otherwise>
                            <img alt="Employee Photo here" src="${pageContext.request.contextPath}/css/images/sign.png" style="width: 150px;height:50px;"  />
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td width="1249">
                    (${authorizerData.hrName})
                </td>
                <td width="484" align="left">( ${joinerReportData.firstName} ${joinerReportData.lastName} )</td>
            </tr>
            <tr>
                <td width="1249"><i>Signature of HR</i></td>
                <td width="484" align="left"><i>Signature of the Employee</i></td>
            </tr>
            <tr>
                <td>
                    <i>Date </i> <b>: ${joinerReportData.joinedDate}</b>
                </td>
                <td align="left">
                    <i>Date </i><b>: ${joinerReportData.joinedDate}</b>
                </td>
            </tr>
        </table>
</div>
     <style type="text/css">
        .printContentED4 .multiaddTable td{
            border-left: 1px solid #000000;
            border-bottom: 1px solid #000000;
        }
        .printContentED4 .lastTd{
            border-right: 1px solid #000000;
        }
        .printContentED4 .multiaddTable th{
            border-left: 1px solid #000000;
            border-top: 1px solid #000000;
            border-bottom: 1px solid #000000;
        }
        .printContentED4 p{
            text-align:justify;
            line-height: 35px;
        }
        .printContentED4 .paraIndent{
            text-indent:40px;
         }
        .printContentED4 .indentLeft{
            margin-left: 300px;
        }
        .printContentED4 .printFont{
            font-size: 12px;
            font-family:"Times New Roman","serif";
        }

        .printContentED4 .printFont th{
            font-size: 13px;
            font-family:"Times New Roman","serif";
            font-weight: bold;
        }

        .printContentED4 .italicText{
        font-style: italic;
        }
        .printContentED4 .printFont tr{
            line-height:20px;
        }
        .printContentED4{
            width:650px;
            height:900px;
            /*border: 1px solid #000000;*/
            position: relative;
        }
    </style>
