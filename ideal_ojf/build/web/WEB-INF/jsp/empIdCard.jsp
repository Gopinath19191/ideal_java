<%-- 
    Document   : empIdCard
    Created on : Dec 3, 2010, 3:36:15 PM
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
        <title>Employee Id Card Form</title>
    </head>
    <%--<body onload="">--%>
    <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
<div class="printContentEmpId">
         <jsp:include page="./printHeader.jsp"></jsp:include><br>
        <table width="100%" border="1" style="border-collapse:collapse">
            <tr><td width="74%" align="center"><b>EMPOYEE ID CARD FORM</b></td>
            <td width="26%" rowspan="5" align="center">
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
            <tr><td><br>Full Name in Block Letters :<b style="font-size: 14px;">&nbsp;&nbsp;${firstName}&nbsp;${middleName}&nbsp;${lastName}</b><br/><br/></td></tr>
            <tr><td>Employee No:
                    <b>
                        <c:choose>
                            <c:when test="${joinerReportData.joinerEmpId!=null && joinerReportData.jfStatus == 6}">
                                ${joinerReportData.joinerEmpId}
                            </c:when>
                            <c:otherwise>
                                ___________________________
                            </c:otherwise>
                        </c:choose>
                   </b>
                    <br/><br/></td></tr>
            <tr><td>Hinduja Tech Mail Id :&nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${joinerReportData.firstName!=null && joinerReportData.lastName!=null}">
                            <b>${joinerReportData.firstName}.${joinerReportData.lastName}@hindujatech.com</b>
                        </c:when>
                        <c:otherwise>
                            <br/><br/>
                        </c:otherwise>
                    </c:choose>
                    </td></tr>
            <tr><td>Date Of Birth :&nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${joinerReportData.dateOfBirth!=null}">
                            <b>${joinerReportData.dateOfBirth}</b>
                        </c:when>
                        <c:otherwise>
                            <br/><br/>
                        </c:otherwise>
                    </c:choose>
                    </td></tr>
            <tr><td colspan="2">Date Of Joining :&nbsp;&nbsp;
                    <c:choose>
                         <c:when test="${joinerReportData.joinedDate !=null && joinerReportData.jfStatus == 6 }">
                            <b>${joinerReportData.joinedDate}</b>
                         </c:when>
                            <c:otherwise>
                            ___________________
                        </c:otherwise>
                     </c:choose>
                    </td></tr>
            <tr><td colspan="2">Designation :&nbsp;&nbsp; <b>${joinerReportData.desigName}</b></td></tr>
            <tr>
                <td colspan="2">Location :&nbsp;&nbsp;<b>${joinerReportData.employeeLocation}</b><b></b></td>
            </tr>
            <tr valign="top"><td colspan="2">Residential Address :&nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${employeeData.presentAddress!=null}">
                            <b>${employeeData.presentAddress}</b><br /><br />
                        </c:when>
                        <c:otherwise>
                                <br /><br /><br /><br />
                        </c:otherwise>
                    </c:choose>
                    Pin Code:
                </td></tr>
            <tr valign="top"><td colspan="2">Permanent Address :&nbsp;&nbsp;
                    <c:choose>
                        <c:when test="${employeeData.permanentAddress!=null}">
                            <b>${employeeData.permanentAddress}</b><br /><br />
                        </c:when>
                        <c:otherwise>
                            <br /><br /><br /><br />
                        </c:otherwise>
                    </c:choose>
                    Pin Code:
                </td></tr>
            <tr><td colspan="2">Blood Group :&nbsp;&nbsp;<b>${joinerReportData.bloodGroup}</b></td></tr>
            <tr valign="top"><td colspan="2">Emergency Contact &nbsp;&nbsp;
                    <c:choose>
                <c:when test="${fn:length(emergencyData)!=0}">
                    <c:forEach items="${emergencyData}" var="emergencyValue" varStatus="emergency">
                        <c:if test="${emergency.count == 1}">
                            <br />Name : <b>${emergencyValue.nameX}</b><br/>Mobile Number : <b>${emergencyValue.mobile_numberX}</b><br/>Telephone (Landline) : <b>${emergencyValue.home_phone_numberX}</b><br/>
                        </c:if>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <br />Name :<br/>Mobile Number :<br/>Telephone (Landline) :<br/>
                </c:otherwise>
            </c:choose>
                    
                </td></tr>
            <tr><td colspan="2">Allergies(if any):</td></tr>
            <tr><td colspan="2"align="center"><b>For Official Use:</b><br/><br/></td></tr>
            <tr><td colspan="2">Request Received on:<br/></td></tr>
            <tr><td colspan="2">Request processed on:<br/></td></tr>
            <tr><td colspan="2">Admin & Facilities:</td></tr>
        </table>
</div>
<style type="text/css">
.printContentEmpId table tr{
        height:35px;
}

.printContentEmpId{
    width:650px;
    height:900px;
    /*border: 1px solid #000000;*/
    position: relative;
}
</style>
