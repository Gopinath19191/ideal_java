<head>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
    <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    <% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
    <title>Joining Formalities</title>
</head>
<c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
</c:if>
    <div class="printContentJR">
                    <jsp:include page="./printHeader.jsp"></jsp:include>
                    <table border="0">
                            <tr>
                                <td colspan="2" class="alignCenter">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2" class="alignCenter">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2" class="alignCenter">
                                    <center><u><b><h4>JOINING REPORT</h4></b></u></center>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <b>To</b><br><br>
                                    Human Resources<br>
                                    Hinduja Tech Limited<br>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${joinerReportData.empFileName!='' && joinerReportData.empFileName!=null}">
                                            <img alt="Employee Photo here" src="http://ideal.hindujatech.com/uploads/ojf_files/${joinerReportData.empFileName}" style="width: 100px;height:100px;margin-left: 250px;"  />
                                        </c:when>
                                        <c:otherwise>
                                            <img alt="Employee Photo here" src="${pageContext.request.contextPath}/css/images/PHOTO3.jpg" style="width: 100px;height:100px;margin-left: 250px;"  />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="alignCenter">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center"><b>Sub: Date of Joining ${joinerReportData.joinedDate} </b></td>
                            </tr>
                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    Dear Sir,
                                </td>
                            </tr>

                            <tr>
                                <td colspan="2">&nbsp;</td>
                            </tr>
                            <tr>
                                <td colspan="2" >
                                    <p>This has reference to your Offer of employment - Ref no: <b>&nbsp;${joinerReportData.refnumber}&nbsp;</b>Dated: <b>${joinerReportData.joinedDate}</b>.</p>
                                    <p>With reference to the above, I <b>${joinerReportData.firstName} ${joinerReportData.lastName}</b>, 
                                        <c:if test="${employeeData.gender=='m'}">
                                            S/o 
                                        </c:if>
                                        <c:if test="${employeeData.gender=='f'}">
                                            D/o 
                                        </c:if>
                                        ${joinerReportData.fatherName} do hereby join my duties as <b>${joinerReportData.desigName}</b> (Title), in (Band) &nbsp;<b>${joinerReportData.bandName} / ${joinerReportData.subBandName}</b>.<br></p>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" align="center">&nbsp;</td>
                            </tr>
                              <tr class="contentRow">
                                  <td width="35%">
                                Employee ID No (to be filled by the HR) 
                            </td>
                            <td>
                                : &nbsp;&nbsp;
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
                        <tr class="contentRow">
                            <td>Location</td>
                            <td>: &nbsp;&nbsp;
                                <c:choose>
                                     <c:when test="${joinerReportData.employeeLocation !=null && joinerReportData.jfStatus == 6 }">
                                        <b>${joinerReportData.employeeLocation}</b>
                                     </c:when>
                                        <c:otherwise>
                                        ___________________
                                    </c:otherwise>
                                 </c:choose>
                            </td>
                        </tr>
                        <tr class="contentRow">
                            <td>
                                Reporting Manager :
                            </td>
                            <td>: &nbsp;&nbsp;
                                <c:choose>
                                    <c:when test="${joinerReportData.reportingManager!=''}">
                                        ${joinerReportData.reportingManager}
                                    </c:when>
                                    <c:otherwise>
                                        ___________________________
                                    </c:otherwise>
                                </c:choose>
                                <u></u>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <p style="line-height:180%">I hereby undertake to abide by the rules, regulations terms and conditions applicable to me at Hinduja Tech Limited.<br><br></p>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <table border="0" width="100%">
                                    <tr class="italicText contentRow">
                                        <td width="65%">
                                            <c:choose>
                                                <c:when test="${authorizerData.employeeId!='' && authorizerData.employeeId!=null}">
                                                    <img alt="Employee Photo here" src="http://ideal.hindujatech.com/uploads/ojf_files/${authorizerData.employeeId}_hr_sign.jpg" style="width: 150px;height:50px;"  />
                                                </c:when>
                                                <c:otherwise>
                                                    <img alt="Employee Photo here" src="${pageContext.request.contextPath}/css/images/sign.png" style="width: 150px;height:50px;"  />
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
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
                                    <tr class="italicText contentRow">
                                        <td width="65%">Authorized Signatory(HR)</td>
                                        <td align="left">Signature of the Employee</td>
                                    </tr>
                                    <tr class="italicText contentRow">
                                        <td>Name :${authorizerData.hrName}</td>
                                        <td align="left">Name : ${joinerReportData.firstName} ${joinerReportData.lastName}</td>

                                    </tr>
                                    <tr class="italicText contentRow">
                                        <td>Date :${joinerReportData.joinedDate}</td>
                                        <td align="left">Date : ${joinerReportData.joinedDate}</td>
                                    </tr>
                                    <tr class="italicText contentRow">
                                        <td>Place : ${authorizerData.cityName}</td>
                                        <td align="left">Place : ${authorizerData.cityName}</td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
    </div>
<head>
    <style type="text/css">
    .printContentJR table tr td{
        font-size: 13px;
        font-family:"Times New Roman","serif";
    }
    .printContentJR .alignCenter{
        text-align: center;
    }
    .printContentJR{
        width:650px;
        height:900px;
        position: relative;
    }
    .printContentJR p{
        text-indent:40px;
        font-size: 13px;
        font-family:"Times New Roman","serif";
        line-height: 40px;
        text-align: justify;
    }
    .printContentJR .contentRow{
            height:23px;
    }
    .printContentJR .italicText{
            font-style: italic;
    }
        
    .printContentJR .indentLeft{
           padding-left: 150px;
    }
</style>
</head>
