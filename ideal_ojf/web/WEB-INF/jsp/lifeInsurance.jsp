<%-- 
    Document   : lifeInsurance
    Created on : Dec 2, 2010, 6:22:11 PM
    Author     : Admin
--%>

    <head>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nomination Form - Life Insurance</title>
    </head>
       <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
<div class="printContentLIC">
         <jsp:include page="./printHeader.jsp"></jsp:include>
         <table border="0" width="100%" class="printFont">
             <tr>
                <td colspan="2" align="center">
                </td>
            </tr>
             <tr>
                <td colspan="2" align="center">
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <u><b>NOMINATION FORM - Life Insurance</b></u><br>
                </td>
            </tr>
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;line-height: 30px;">
                        I
                        <c:choose>
                            <c:when test="${joinerReportData.firstName!=null || joinerReportData.lastName!=null}">
                                    <b>&nbsp;&nbsp;${joinerReportData.firstName} ${joinerReportData.lastName}&nbsp;&nbsp;</b>
                            </c:when>
                            <c:otherwise>
                                ________________________________________
                            </c:otherwise>
                        </c:choose>
                        S/o, D/o <b>${joinerReportData.fatherName}</b>, aged
                        <c:choose>
                            <c:when test="${joinerReportData.dateOfBirth!=null}">
                                <b>${age}</b>
                            </c:when>
                            <c:otherwise>
                                 ________
                            </c:otherwise>
                        </c:choose>
                        years presently residing at
                        <c:choose>
                            <c:when test="${joinerReportData.presentAddress!=null}">
                                <b>&nbsp;&nbsp;&nbsp;${joinerReportData.presentAddress}&nbsp;</b>
                            </c:when>
                            <c:otherwise>
                                ____________________________________________________
                            </c:otherwise>
                        </c:choose>
                        do hereby nominate  Mr/Mrs ________________________________________, aged ___________ years who is related to me to receive any dues/payments of whatever nature payable to me by the <br>Company (Hinduja Tech Limited).
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="1"  class="printFont" cellpadding="0" cellspacing="0" style="border-collapse: collapse;">
                        <tr>
                            <th width = "2%" >S.No.</th>
                            <th width = "20%">Name of nominee</th>
                            <th width = "25%">Address of nominee</th>
                            <th width = "20%">Relationship</th>
                            <th width = "10%"></th>
<!--                            <th width = "10%">Percentage of nomination</th>-->
                        </tr>
                        <c:forEach items="${nomineeData}" var="nomineeData" varStatus="index">
                            <tr>
                                <td style="padding:0px 4px 0px 4px;">${index.index+1}.</td>
                                <td style="padding:0px 4px 0px 4px;">${nomineeData.nameOfRelationDb}</td>
                                <td></td>
                                <td style="padding:0px 4px 0px 4px;">
                                    <c:forEach items="${relationShips}" var="relationShip" varStatus="index">
                                        <c:if test="${index.index==nomineeData.relationShipDb}">
                                            ${relationShip}
                                        </c:if>
                                    </c:forEach>
                                </td>
                                <td></td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
        </table>
        <table width="100%" border = "0" class="printFont">
            <tr>
                <td width="75%"></td>
                <td>
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
            <tr><td width="75%"></td><td>( ${joinerReportData.firstName} ${joinerReportData.lastName} )</td></tr>
            <tr><td width="75%"></td><td>Signature of the Employee</td></tr>
            <tr><td width="75%"></td><td>Date <b>: ${joinerReportData.joinedDate}</b></td></tr>
        </table>
</div>
        <style type="text/css">
    .printContentLIC p{
        text-align:justify;
        line-height: 35px;
    }
    .printContentLIC .paraIndent{
        text-indent:40px;
     }
    .printContentLIC .indentLeft{
        margin-left: 300px;
    }
   
    .printContentLIC .italicText{
    font-style: italic;
    }
    .printContentLIC .printFont{
        font-size: 13px;
        font-family:"Times New Roman","serif";
    }
  .printContentLIC .printFont tr{
        height:30px;
    }
  .printContentLIC{
        width:650px;
        height:900px;
        /*border: 1px solid #000000;*/
        position: relative;
    }
</style>
