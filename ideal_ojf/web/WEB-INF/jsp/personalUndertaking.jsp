<%-- 
    Document   : personalUndertaking
    Created on : Dec 6, 2010, 11:27:42 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Personal Undertaking Form</title>
    </head>
   <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
<div class="printContentPersonalUnder">
    <jsp:include page="./printHeader.jsp"></jsp:include>
        <table width="100%" border="0" cellpadding="2" cellspacing="2">
            <tr><td align="center" colspan="3"><br><b><u>Personal Undertaking Form<br></u><br></b></td></tr>
            <tr><td colspan="4">
                    <p style="text-align:justify;text-indent:0px;">
                        I,
                        <c:choose>
                            <c:when test="${joinerReportData.firstName!=null || joinerReportData.middleName!=null || joinerReportData.lastName!=null}">
                                <b>&nbsp;&nbsp;${joinerReportData.firstName}&nbsp;${joinerReportData.middleName}&nbsp;${joinerReportData.lastName}</b>
                            </c:when>
                            <c:otherwise>
                                ______________________________
                            </c:otherwise>
                        </c:choose>
                        aged
                        <c:choose>
                            <c:when test="${joinerReportData.dateOfBirth!=null}">
                                <b>${age}</b>
                            </c:when>
                            <c:otherwise>
                                 ________
                            </c:otherwise>
                        </c:choose>,resident of
                        <c:choose>
                            <c:when test="${joinerReportData.presentAddress!=null}">
                                   <b>${joinerReportData.presentAddress}</b>
                            </c:when>
                            <c:otherwise>
                                    _________________________<br>__________________________________________________________________
                            </c:otherwise>
                        </c:choose>
                                    do solemnly affirm and sincerely state as follows:</p></td></tr><tr>
                <td colspan="2" valign="top">
                    1.</td><td width="1259" style="line-height:20px;">  That I wish to join Hinduja Tech Limited as <b>${joinerReportData.desigName}</b> in terms of<br>
                          the offer/appointment letter dated _____________________
                        
                                issued to me.<br>
                </td></tr><tr>
                <td colspan="2" valign="top">
                2.</td><td>
                        That earlier I was working as ________________________ in ______________ <br>_______________________ from _________________to_____________________and resigned the 
                        said job on ________________________to take up the assignment with Hinduja Tech<br></td></tr><tr>
                <td colspan="2" valign="top">
                        3. </td><td>That I am unable to produce the following<br>
                </td></tr>
            <tr>
                <td></td>
                <td style="margin-left: 60px;" colspan="2"  >
                    <div style="margin-left: 90px;">
                    <div style="float: left;width: 20px;height:20px;border: 1px solid #000000;"></div><div style="float: left">&nbsp;&nbsp;Releiving letter from earlier employer</div>
                    </div>
              </td>
            </tr>
            <tr>
                <td></td>
                <td colspan="2">
                    <div style="margin-left: 90px;">
                    <div style="float: left;width: 20px;height:20px;border: 1px solid #000000;"></div><div style="float: left">&nbsp;&nbsp;Degree Certificates</div>
                    </div>
                </td>
            </tr>
            <tr><td></td><td colspan="2">
                        and the same was informed to the officials of Hinduja Tech. On their advice I am <br>submitting this undertaking. I will submit the same within _________months time from the date of joining<br>
                </td></tr>
            <tr><td colspan="2" valign="top">
                        4. </td><td>That I confirm that I have no obligation towards my previous employer(s) in terms of service<br> agreement or in any other way if in future anything is found it shall be at my own risk and responsibility.<br>
                        </td></tr>
            <tr><td colspan="2" valign="top">
                    5.  </td><td>That I hereby undertake that in future if my earlier employer initiates any action against me it shall<br>by my sole responsibilty to defend myself and Hinduja Tech shall not be responsible in any way and I shall be held responsible for all the costs and consequences thereof.<br>
                        </td></tr>
            <tr><td colspan="2" valign="top">
                    6. </td><td>That I have given this undertaking after fully knowing the consequences thereof and without any<br> force, or coercion and with free will and consent in presence of the witnesses mentioned below.<br><br>
                    IN WITNESS WHEROF this Personal Undertaking is executed by me on this day of<br> <b>${joinerReportData.joinedDate}</b> at Chennai/Bangalore.<br><br>
                </td></tr>
            <tr>
                <td colspan="3"><b>WITNESSES:</b></td>
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
            <tr>
                <td width="25"><b>1.</b></td>
                <td align="right" colspan="3" style="padding-right:30px;"><b>EXECUTANT</b>
                </td>
            </tr>
            <tr>
                <td width="25"><b>2.</b></td>
                <td align="right" colspan="3">
                    
                </td>
            </tr>
        </table>
</div>
<style type="text/css">
.printContentPersonalUnder{
    width:650px;
    height:900px;
    /*border: 1px solid #000000;*/
    position: relative;
}
</style>