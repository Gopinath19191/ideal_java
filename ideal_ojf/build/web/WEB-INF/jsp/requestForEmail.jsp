<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
<!DOCTYPE HTML PUBLIC "-//W4C//DTD HTML 4.01 Transitional//EN"
    "http://www.w4.org/TR/html4/loose.dtd">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>E-mail Creation</title>
    </head>
    <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>

    <div class="printContentEmail">
    <jsp:include page="./printHeader.jsp"></jsp:include>

    
<table style="width: 726px;" border="1" cellspacing="0" cellpadding="0">
<tbody>
<tr>
<td colspan="8" valign="top" width="726">

    <p align="center" style="height: 10px;">&nbsp;<strong>Hinduja Tech Limited</strong></p>
<p align="center"><strong><span style="text-decoration: underline;">E-mail and Windows User Account &ndash; Request Form</span></strong></p>

</td>
</tr>
<tr>
<td valign="top" width="180">
<strong style="margin-left: 3px">Employee ID:</strong>&nbsp;<c:choose>
                            <c:when test="${joinerReportData.joinerEmpId!=null && joinerReportData.jfStatus == 6}">
                                ${joinerReportData.joinerEmpId}
                            </c:when>
                            <c:otherwise>
                                ___________________________
                            </c:otherwise>
                        </c:choose>
<p></p>
</td>
<td colspan="6" valign="top" width="447">
<strong>Full Name:</strong>&nbsp;${firstName}&nbsp;${middleName}&nbsp;${lastName}&nbsp;<p></p>

</td>
<td valign="top" width="143">
<strong>Date:<b></b></strong>&nbsp;${currentDate}
<p></p>

</td>
</tr>
<tr>
<td colspan="4" valign="top" width="363">
<strong>Designation:</strong>&nbsp;${joinerReportData.desigName}
<p></p>
</td>
<td colspan="4" valign="top" width="363">
<strong>Phone Number:</strong>&nbsp;${employeeData.phoneNumberPermanent}
<p></p>
</td>
</tr>
<tr>
<td colspan="4" valign="top" width="476">
<strong>Preferred Email ID: (</strong>firstname.lastname@hindujatech.com)<p></p>
<p><strong>1.</strong>&nbsp;${firstName}.${lastName}@hindujatech.com</p>

<p><strong>2.</strong></p>
</td>
<td colspan="5" valign="top" width="363">
<strong>Personal Email ID:</strong><p></p>

<p><strong>1.</strong>${joinerReportData.personalEmailId1}</p>

<p><strong>2.</strong></p>

</td>
</tr>
<tr>
<td colspan="2" valign="top" width="261">
    <div style="height:56px;">
<strong>Practice:</strong>&nbsp;${joinerReportData.practiceGroup}<p></p>
</div>
<div>

<strong>(E.g.)&nbsp; ERP/IT Services / Support</strong>
</div>
</td>
<td colspan="3" valign="top" width="261">
      <div style="height:56px;">
<strong>Practice Group:</strong>&nbsp;${joinerReportData.subPracticeGroup} <p></p>
      </div>
    <div><strong>(E.g.) SAP/ Java/Microsoft/&hellip;</strong></div>
</td>
<td colspan="3" valign="top" width="268">
   <div style="height:56px;"> 
<strong>Location:</strong>&nbsp;${joinerReportData.employeeLocation}<p></p>
   </div>
    <div>
        <strong>(E.g.) Bangalore / Chennai /&hellip;</strong></div>
</td>
</tr>
<tr>
<td colspan="8" valign="top" width="726" style="background-color:#E5E4E2">
<strong>Undertaking by user &amp; Authorization:</strong>
</td>
</tr>
<tr>
<td colspan="8" width="726">
<ul>
<li>I undertake to check unauthorized access to my account (or PC/Laptop) by having proper password security.</li>
<li>I undertake to check unauthorized network activities from my account (or PC/Laptop).</li>
<li>I undertake to change the password (given by the administrator) immediately as soon as my mail id is created.</li>
<li>I undertake to change my password as per the password policy.</li>
<li>I undertake to keep my PC/Laptop free of computer viruses.</li>
<li>I will also ensure that CD/DVD, pen drives and other external storage devices will not be used on my PC/Laptop without my consent. I ensure that these will not be used without proper scanning for viruses using the latest anti-virus software and without written approval from N&amp;S.</li>
<li>I undertake to observe e-mail etiquette and to follow e-mail guidelines and all policies set from time to time as published by Hinduja Tech.</li>
<li>I do not have any other E-mail ID in Hinduja Tech Limited.</li>
<li>I will not use Hinduja Tech Limited mail for purposes other than official and I will not send chain mails.</li>
<li>I will keep the password confidential and will not share with anyone under any circumstances.</li>
<li>I undertake not to install any software/utilities/hardware without written approval from N&amp;S. Only N&amp;S will install all software/utilities/hardware.</li>
<li>I accept Hinduja Tech to validate my system/mobile/e-mails/files/folders/any other equipments without giving any prior communications to me.</li>
</ul>
</td>
</tr>
<tr>
<td colspan="4" width="363">
<strong>Signature of the user:</strong>
<p><strong>Date:</strong></p>
</td>
<td colspan="4" width="363">
<p><strong>Authorizations by HR: </strong></p>
<p>Name:${joinerReportData.hrName}</p>
<p>Signature:</p>
<p>Date:${currentDate}</p>&nbsp;
</td>
</tr>
<tr>
    <td colspan="8" valign="top" width="726" style="background-color:#E5E4E2" >
<strong>For official use:</strong>
</td>
</tr>
<tr>
<td colspan="3" valign="top" width="246">
<strong><span style="text-decoration: underline;">Received by:</span></strong>

<p>Name:</p>

<p>Date:</p>

<p>Signature:</p>
</td>
<td colspan="3" valign="top" width="224">
<strong><span style="text-decoration: underline;">Account created by:</span></strong>

<p>Name:</p>

<p>Date:</p>

<p>Signature:</p>
</td>
<td colspan="2" valign="top" width="256">
<strong><span style="text-decoration: underline;">Account Name &amp; Password communicated to user:</span></strong>
<p> </p>

<table border="1" cellspacing="0" cellpadding="0" height="20" width="50" align="left">
<tbody>
   
<tr>
<td valign="top" width="15" height="26">
</td>
</tr>
<tr>
<td valign="top" width="25" height="27">
</td>
</tr>
</tbody>
</table>
<p><strong>Yes, Date: ___________</strong></p>
<p><strong>No, Reasons:</strong></p>
</td>
</tr>
</tbody>
</table>
</div>
<style type="text/css">
<%--.printContentEmail table tr{
        height:35px;
}--%>
.printContentEmail ul li{
    font-size: 11px;
}

.printContentEmail{
    width:650px;
    position: relative;
}
</style>

