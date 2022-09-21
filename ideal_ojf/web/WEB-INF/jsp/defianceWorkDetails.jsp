<%-- 
    Document   : defianceWorkDetails
    Created on : Dec 6, 2010, 11:11:50 AM
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
        <title>Hinduja Tech Work Related Details</title>
    </head>
   <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
<div class="printContentWorkDetails">
    <jsp:include page="./printHeader.jsp"></jsp:include>
        <table width="100%" border="1" >
            <br><br>
            <tr>
                <td align="center"><b>HINDUJA TECH WORK RELATED DETAILS</b></td>
                <td rowspan="4" align="center">
                    <c:choose>
                        <c:when test="${joinerReportData.empFileName!='' && joinerReportData.empFileName!=null}">
                            <img alt="Employee Photo here" src="http://ideal.hindujatech.com/uploads/ojf_files/${joinerReportData.empFileName}" style="width: 100px;height:100px;"  />
                        </c:when>
                        <c:otherwise>
                            <img alt="Employee Photo here" src="${pageContext.request.contextPath}/css/images/PHOTO3.jpg" style="width: 100px;height:100px;"  />
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
            <tr><td>Name:&nbsp;&nbsp;<b>${joinerReportData.firstName}&nbsp;${joinerReportData.middleName}&nbsp;${joinerReportData.lastName}</b><br/><br/></td></tr>
            <tr><td>Date of Joining:&nbsp;&nbsp;<b>${joinerReportData.joinedDate}</b>&nbsp;<br/><br/></td></tr>
            <tr><td>Skill Area:<br/><br/></td></tr>
            <tr><td colspan="2">Project Name:<br/><br/></td></tr>
            <tr><td colspan="2">Project Technologies:<br/><br/></td></tr>
            <tr><td colspan="2">Reporting To:&nbsp;&nbsp;<b>${joinerReportData.reportingManager}</b><br/><br/></td></tr>
            <tr valign="top"><td colspan="2">Educational Background:
                    <br /><br /><br /><br /><br/><br/>
                </td></tr>
            <tr><td colspan="2">Previous Experience:<br/>
                    (Companies,year of exp)<br/><br/><br/><br/><br/><br/></td></tr>
            <tr><td colspan="2"><u>Personal Details:</u><br/>
                        Family details:<br/><br/><br/><br/>Hobbies:<br/><br/><br/></td></tr>
            <tr><td colspan="2">Hinduja Tech Mail ID :<b> &nbsp;${joinerReportData.firstName}.${joinerReportData.lastName}@hindujatech.com</b><br/>
                        Contact No:${employeeData.phoneNumberPresent}
                </td></tr>
        </table>
</div>

<style type="text/css">
.printContentWorkDetails{
    width:650px;
    height:900px;
    /*border: 1px solid #000000;*/
    position: relative;
}
</style>