<%-- 
    Document   : groupMedical
    Created on : Dec 2, 2010, 5:50:00 PM
    Author     : Admin
--%>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Group Medical Insurance</title>
        <%@page contentType="text/html" pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
        <%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
        <% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
    </head>
        <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
<div class="printContentGM"> 
         <jsp:include page="./printHeader.jsp"></jsp:include>
         <table border="0" width="100%" class="printFont">
            <tr>
                <td colspan="2" align="center" class="fontSize" style="font-size: 14px;">
                    <b><br>GROUP MEDICAL INSURANCE POLICY PROPOSAL</b><br>
                    <b>Employee's/Member's Personal Statement Form</b>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="0" width="100%">
                        <tr>
                            <td width="150"><b>Name of Employee</b></td><td><b>:</b></td><td>${joinerReportData.firstName} ${joinerReportData.lastName}<br></td>
                        </tr>
                        <tr>
                            <td><b>Employee ID</b></td><td><b>:</b></td>
                            <td>
                                <c:if test="${joinerReportData.joinerEmpId!=null && joinerReportData.jfStatus == 6}">
                                        ${joinerReportData.joinerEmpId}
                                    </c:if><br>
                           </td>
                        </tr>
                        <tr>
                            <td><b>Designations</b></td><td><b>:</b></td><td>${joinerReportData.desigName}</td>
                        </tr>
                        <tr>
                            <td><b>Band / Level</b></td><td><b>:</b></td><td>${joinerReportData.bandName} / ${joinerReportData.subBandName}</td>
                        </tr>
                        <tr>
                            <td><b>Location</b></td><td><b>:</b></td>
                            <td>
                                 <c:if test="${joinerReportData.employeeLocation !=null && joinerReportData.jfStatus == 6 }">
                                    ${joinerReportData.employeeLocation}
                                 </c:if>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="fontSize">
                    <b>1.Details of the family members to be insured:</b>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table width="100%" border = "1" cellpadding="0" cellspacing="0" style="border-collapse: collapse;font-size: 13px;">
                        <tr>
                            <th width = "5%" >S No</th>
                            <th width = "25%" >Name of Insured Person</th>
                            <th width = "15%" >Age / Date Of Birth</th>
                            <th width = "15%">Gender ( Male / Female )</th>
                            <th width = "15%">Relationship with Insured</th>
                            <th width = "25%">Details of Pre Existing Diseases / Illness</th>
                        </tr>
                        <tr>
                            <td style="padding:0px 4px 0px 4px;">1<br></td>
                            <td style="padding:0px 4px 0px 4px;">${joinerReportData.firstName} ${joinerReportData.lastName}</td>
                            <td style="padding:0px 4px 0px 4px;">${joinerReportData.dateOfBirth}</td>
                            <td style="padding:0px 4px 0px 4px;">
                                <c:choose>
                                    <c:when test="${employeeData.gender=='m'}">
                                         Male
                                    </c:when>
                                    <c:otherwise>
                                         Female
                                    </c:otherwise>
                                 </c:choose>
                            </td>
                            <td style="padding:0px 4px 0px 4px;">Self</td>
                            <td>&nbsp;</td>
                        </tr>
                        <c:forEach items="${mediInsuranceData}" var="medicalInsuranceData" varStatus="index">
                            <tr>
                                <td style="padding:0px 4px 0px 4px;">${index.index+2}<br></td>
                                <td style="padding:0px 4px 0px 4px;">${medicalInsuranceData.nameOfRelationDb}<br></td>
                                <td style="padding:0px 4px 0px 4px;">${medicalInsuranceData.dobRelationDb}</td>
                                <td style="padding:0px 4px 0px 4px;">
                                    <c:if test="${medicalInsuranceData.relationShipDb==0 || medicalInsuranceData.relationShipDb==3}">
                                        Male
                                    </c:if>
                                    <c:if test="${medicalInsuranceData.relationShipDb==1 || medicalInsuranceData.relationShipDb==4}">
                                        Female
                                    </c:if>
                                    <c:if test="${medicalInsuranceData.relationShipDb==2}">
                                        <c:choose>
                                            <c:when test="${employeeData.gender=='m'}">
                                                Female
                                            </c:when>
                                            <c:otherwise>
                                                Male
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>
                                </td>
                                <td style="padding:0px 4px 0px 4px;">
                                    <c:forEach items="${relationShips}" var="relationShip" varStatus="index">
                                        <c:if test="${index.index==medicalInsuranceData.relationShipDb}">
                                            ${relationShip}
                                        </c:if>
                                    </c:forEach>
                                </td><td>&nbsp;</td></tr>
                        </c:forEach>
                        <tr><td>&nbsp;<br></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                    </table><br>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="line-height:20px;" class="fontSize">
                    <b>2. Are you suffering/have you suffered from Diabetes/Hypertension/Chest pain or Coronary, Insufficiency or Myocardia
                        Infraction? If so. Please specify.</b><br>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="fontSize">
                    <b>3.  Name and address of family doctor, including telephone number if any:  </b><br>
                </td>
            </tr>
            <tr>
                <td colspan="2" class="fontSize">
                    <b>4. Date of medical check-up / complete Health check last undertaken :  </b><br>
                </td>
            </tr>
            <tr>
                <td colspan="2" style="line-height:20px;" class="fontSize">
                    <b>I/We hereby declare that the information furnished above are true and correct to my/our knowledge and belief I/We also declare that I/We am/are sound in health and am/are devoid of any disease/illness.<br> </b>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <b><u>ASSIGNMENT FOR PERSONAL ACCIDENT INSURANCE (For Nomination)</u></b><br><br>
                </td>
            </tr>
            <tr><td colspan="2">List of Nominee's:</td></tr>
            <c:forEach items="${nomineeData}" var="nomineeData" varStatus="index">
                <tr>
                    <td style="padding:0px 4px 0px 4px;">${index.index+1}.&nbsp;&nbsp;
                        ${nomineeData.nameOfRelationDb}
                        &nbsp;Relationship &nbsp;
                        <c:forEach items="${relationShips}" var="relationShip" varStatus="index">
                            <c:if test="${index.index==nomineeData.relationShipDb}">
                                ${relationShip}
                            </c:if>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td></td>
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
            <tr>
                <td ><b>Place : </b>${authorizerData.cityName}</td>
                <td align="left"> ( ${joinerReportData.firstName} ${joinerReportData.lastName} ) </td>
            </tr>
            <tr>
                <td width="450"><b>Date :</b> ${joinerReportData.joinedDate}</td>
                <td align="left"><b>Signature of the Proposer/Insured</b></td>
            </tr>
        </table>
    </div>
    <style type="text/css">
        .printContentGM p{
            text-align:justify;
            line-height: 30px;
        }
        .printContentGM .paraIndent{
            text-indent:40px;
         }
        .printContentGM .indentLeft{
            margin-left: 300px;
        }
        .printContentGM .printFont{
            font-size: 13px;
            font-family:"Times New Roman","serif";
        }
        .printContentGM .italicText{
        font-style: italic;
        }
        .printContentGM .printFont tr{
            height:25px;
        }
        .printContentGM{
            width:650px;
            height:900px;
            /*border: 1px solid #000000;*/
            position: relative;
        }
        .lineHeight{
            line-height:20px;
}
    </style>
