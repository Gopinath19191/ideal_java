<%-- 
    Document   : paymentOfGratuity
    Created on : Dec 2, 2010, 6:33:31 PM
    Author     : Admin
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Payment Of Gratuity Rules</title>
    </head>
    <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
<div class="printContentGratuity">
         <jsp:include page="./printHeader.jsp"></jsp:include>
         <table border="0" width="100%" class="printFont">
            <tr><td align="center" colspan="2" style="font-size:14px;"><br><b>PAYMENT OF GRATUITY (CENTRAL) RULES,1972</b></td></tr>
            <tr><td align="center" colspan="2" style="font-size:14px;"><b>Form "G"</b></td></tr>
            <tr><td align="left" colspan="2" style="font-size:14px;"><br>See sub-rule(3) of rule6)-<b>Fresh Nomination</b></td></tr>
            <tr><td align="left" colspan="2" style="font-size:14px;"><br>To</td></tr>
            <tr><td align="left" colspan="2" style="font-size:14px;"><b>Human Resources</b></td></tr>
            <tr><td align="left" colspan="2" style="font-size:14px;">Hinduja Tech Limited</td></tr>
            <tr>
                <td colspan="2" style="font-size:14px;">
                    <p style="text-align:justify;text-indent:50px;">
                        <br>I, Shri/Shrimati
                        <c:choose>
                            <c:when test="${joinerReportData.firstName!=null || joinerReportData.lastName!=null}">
                                    <b>&nbsp;&nbsp;${joinerReportData.firstName} ${joinerReportData.lastName}&nbsp;&nbsp;</b>
                            </c:when>
                            <c:otherwise>
                                ________________________________________
                            </c:otherwise>
                        </c:choose>
                        whose particulars are given in the statement below, have acquired a family within the meaning of clause (h) of Section (2) of the Payment of Gratuity Act, 1972  with effect from the
                        <c:choose>
                            <c:when test="${joinerReportData.dateOfBirth!=null}">
                                <b>${joinerReportData.dateOfBirth}</b>
                            </c:when>
                            <c:otherwise>
                                _______________________
                            </c:otherwise>
                        </c:choose>
                         (DOB or DOM) in the manner indicated below and therefore nominate afresh the person (s) mentioned below to receive the gratuity payable after my death as also the gratuity standing to my credit in the event of my death before that amount has become payable, or having become payable has not been paid direct that the said amount of gratuity shall be paid in proportion indicated against the name (s) of the nominee (s).
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="font-size:14px;">
                    <p style="text-align:justify;text-indent:50px;">
                        <br>2.I hereby certify the person (s) nominated is a/are member (s) of my family within the meaning of clause (h) of section 2 of the said Act.
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="font-size:14px;">
                    <p style="text-align:justify;text-indent:50px;">
                        <br>3.  (a) My father / mother / parents  is / are not dependent on me<br>
                        &nbsp;&nbsp;&nbsp;&nbsp;(b) My husband’s father / mother / parents is / are not dependent on my husband.
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="font-size:14px;">
                    <p style="text-align:justify;text-indent:50px;">
                        <br>4.  I have excluded my husband from my family by a notice dated the _____________ to the
                        controlling authority in terms of the proviso to clause (h) of Section 2 of the said Act.
                    </p>
                </td>
            </tr>
            <tr><td align="center" colspan="2" style="font-size:14px;"><br><b>Nominees(s)</b><br></td></tr></table>
        <table width="100%" border="1" cellspacing ="0 " cellpadding="0" style="border-collapse: collapse;">
            <tr>
                <th width = "5%" style="font-size:14px;">S.No</th>
                <th width = "35%" style="font-size:14px;">Name in full with full address of nominee(s)</th>
                <th width = "15%" style="font-size:14px;">RelationShip with the employee</th>
                <th width = "15%" style="font-size:14px;">Age/DOB of nominee</th>
                <th width = "25%" style="font-size:14px;">Proportion by which the gratuity will be shared</th>
            </tr>
            <c:forEach items="${nomineeData}" var="nomineeData" varStatus="index">
                <tr>
                    <td style="padding:0px 4px 0px 4px;">${index.index+1}.</td>
                    <td style="padding:0px 4px 0px 4px;">${nomineeData.nameOfRelationDb}</td>
                    <td style="padding:0px 4px 0px 4px;">
                        <c:forEach items="${relationShips}" var="relationShip" varStatus="index">
                            <c:if test="${index.index==nomineeData.relationShipDb}">
                                ${relationShip}
                            </c:if>
                        </c:forEach>
                    </td>
                    <td style="padding:0px 4px 0px 4px;">${nomineeData.dobRelationDb}</td>
                    <td></td>
                </tr>
            </c:forEach>
        </table><br>
        <table width="100%" border="0" cellspacing ="0 " cellpadding="0">
            <tr><td align="center" colspan="2" style="font-size:14px;"><b>THE PAYMENT OF GRATUITY(CENTRAL)RULES,1972</b><br></td></tr>
            <tr><td align="left" colspan="2" style="font-size:14px;"><br><b>Manner of acquiring a "family"</b></td></tr>
            <tr>
                <td colspan="2" style="font-size:14px;">
                    <p style="text-align:justify;text-indent:50px;">[Here give details as to how a family was acquired, i.e., whether by marriage or parents being rendered dependent or through other process like adoption]</p>
                </td>
            </tr>
            <tr><td align="center" colspan="2" style="font-size:14px;"><br><b>Statement</b></td></tr></table>
            <table width="100%" border="0" cellspacing ="0 " cellpadding="0">
            <tr>
                <td width="30%" align=left colspan="1">1.&nbsp;Name of the employee in full</td>
                <td width="1%"><b>:</b></td>
                <td width="30%" align=left colspan="1">
                    <c:choose>
                        <c:when test="${joinerReportData.firstName!=null || joinerReportData.lastName!=null}">
                            <b>${joinerReportData.firstName} ${joinerReportData.lastName}</b>
                        </c:when>
                        <c:otherwise>
                            _____________________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td width="30%" align=left colspan="1">2.&nbsp Gender</td>
                <td><b>:</b></td>
                <td width="30%" align=left colspan="1">
                    <c:choose>
                        <c:when test="${employeeData.gender=='m'}">
                            <b>Male</b>
                        </c:when>
                            <c:otherwise><b>Female</b></c:otherwise>
                </c:choose>
                </td>
            </tr>
            <tr>
                <td width="30%" align=left colspan="1">3.&nbsp Religion</td>
                <td><b>:</b></td>
                <td width="30%" align=left colspan="1">
                    <c:choose>
                        <c:when test="${employeeData.religion!=null}">
                            <b>${employeeData.religion}</b>
                        </c:when>
                        <c:otherwise>
                            _____________________________________
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr>
                <td width="30%" align=left colspan="1">4.&nbsp Marital Status</td>
                <td><b>:</b></td>
                <td width="30%" align=left colspan="1">
                    <c:choose>
                        <c:when test="${employeeData.maritalStatus!=null}">
                            <c:forEach items="${maritalStatus}" var="maritalStatusValues" varStatus="i">
                                <c:if test="${maritalStatusValues.maritalstatusId==employeeData.maritalStatus}">
                                    <b>${maritalStatusValues.maritalstatusName}</b>
                                </c:if>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            _____________________________________
                        </c:otherwise>
                    </c:choose>
                    <u></u>
                </td>
            </tr>
        </table>
</div>
            <style type="text/css">
             .printContentGratuity .printContentLIC .printFont{
        font-size: 13px;
        font-family:"Times New Roman","serif";
    }
             .printContentGratuity .printContentLIC .printFont tr{
        height:30px;
    }
                .printContentGratuity{
                width:650px;
                height:900px;
                /*border: 1px solid #000000;*/
                position: relative;
            }
            </style>