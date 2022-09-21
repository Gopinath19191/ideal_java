<%-- 
    Document   : empDetailsPageThree
    Created on : Dec 2, 2010, 11:43:29 AM
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
        <title>Employee Details</title>
    </head>
    <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
<div class="printContentED3">
        <jsp:include page="./printHeader.jsp"></jsp:include>
           <table width="100%" border="0" class="printFont" style="border-collapse: collapse;">
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" >
                    <b>20. Provide details of the last drawn salary and  gross emoluments indicating the monthly and yearly perks:</b><br>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="1" style="border-collapse: collapse;">
                        <tr>
                            <th width = "20%" >ANNUAL SALARY</th>
                            <th width = "10%">Amount Rs.</th>
                            <th width = "20%">ANNUAL BENEFITS</th>
                            <th width = "10%">Amount Rs.</th></tr>
                        <tr><td>&nbsp;Basic<br></td><td>&nbsp;</td><td>&nbsp;Bonus<br></td><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;Dearness Allowance<br></td><td>&nbsp;</td><td>&nbsp;Ex-Gratia<br></td><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;Vehicle Allowance<br></td><td>&nbsp;</td><td>&nbsp;Medical Reimbursement<br></td><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;Conveyance Allowance<br></td><td>&nbsp;</td><td>&nbsp;Superannuation<br></td><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;Other Allowances <br>&nbsp;i)	______________________<br> &nbsp;ii) _____________________<br>&nbsp;iii)	 ______________________<br></td><td>&nbsp;</td><td>&nbsp;Any other benefits<br>&nbsp;i)	__________________<br>

                                &nbsp;ii)	__________________<br>

                                &nbsp;iii)	__________________<br></td><td>&nbsp;</td></tr>
                        <tr><td>&nbsp;a) Total per Annum<br></td><td>&nbsp;</td><td>&nbsp;b) Total per annum<br></td><td>&nbsp;</td></tr>
                        <tr><td colspan="4"><b>&nbsp;GRAND TOTAL PER ANNUM (a + b):</b><br></td></tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" >
                    <b>21. Family Details:  Please provide full details of family including parents, spouse, children, brothers and sisters.</b><br>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2">
                    <table  width="100%" border="1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
                        <tr>
                            <th width="6%">S.No</th>
                            <th width="30%">Name</th>
                            <th width="20%">Relationship</th>
                            <th width="15%">Date of Birth</th>
                            <th width="30%">Occupation</th>
                        </tr>
                        <c:choose>
                            <c:when test="${familyMemberData!=null}">
                                <c:forEach items="${familyMemberData}" var="familyMemberDataValues" varStatus="i">
                                    <tr>
                                        <td>
                                            &nbsp;${i.index+1}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${familyMemberDataValues.nameOfRelationDb}&nbsp;
                                        </td>
                                        <td>
                                            <c:forEach items="${relationShips}" var="relationShip" varStatus="index">
                                                <c:if test="${familyMemberDataValues.relationShipDb==index.index}">
                                                    &nbsp;${relationShip}&nbsp;
                                                </c:if>
                                            </c:forEach>
                                        </td>
                                        <td>
                                            &nbsp;${familyMemberDataValues.dobRelationDb}&nbsp;
                                        </td>
                                        <td>
                                            &nbsp;${familyMemberDataValues.occupationOfRelDb}&nbsp;
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="i" begin="${(fn:length(familyMemberData))+1}" end="6" step="1" varStatus="rem">
                                    <tr><td style="padding:0px 4px 0px 4px;">${rem.count + fn:length(familyMemberData)}<br></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
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
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
        </table>
</div>
    <style type="text/css">
    .printContentED3 p{
        text-align:justify;
        line-height: 35px;
    }
    .printContentED3 .paraIndent{
        text-indent:40px;
     }
    .printContentED3 .indentLeft{
        margin-left: 300px;
    }
    .printContentED3 .printFont{
        font-size: 13px;
        font-family:"Times New Roman","serif";
    }

    .printContentED3 .printFont th{
        font-size: 14px;
        font-family:"Times New Roman","serif";
        font-weight: bold;
    }

    .printContentED3 .italicText{
    font-style: italic;
    }
    
    .printContentED3 table tr{
    line-height: 25px;
    }
    
     .printContentED3{
        width:650px;
        height:900px;
        /*border: 1px solid #000000;*/
        position: relative;
    }
</style>
