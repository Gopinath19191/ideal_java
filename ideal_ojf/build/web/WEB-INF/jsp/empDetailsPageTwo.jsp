<%-- 
    Document   : empDetailsPageTwo
    Created on : Dec 2, 2010, 10:26:44 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
    <head>
        <title>Employee Details</title>
    </head>
    <%--<body onload="">--%>
<c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print(); 
        </script>
    </c:if>
<div class="printContentED2">
         <jsp:include page="./printHeader.jsp"></jsp:include>
         <table border="0" width="100%">
             <tr>
                 <td></td>
                 <td></td>
             </tr>
         </table>
         <table border="0" width="100%" class="printFont">
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;">
                        <b>17. Previous Employment (start from the latest company worked in)</b><br>
                    </p>
                </td>
            </tr>
            <%--<tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>--%>
            <tr>
                <td colspan="2">
                    <table border="1" width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse;" class="printFont">
                        <tr>
                            <th width = "4%" >S.No</th>
                            <th width = "15%" >Employer</th>
                            <th width = "10%">Date of Joining</th>
                            <th width = "12%">Designation on Joining</th>
                            <th width = "10%">Salary on Joining (pa)</th>
                            <th width = "10%">Date of relieving</th>
                            <th width = "12%">Designation on Relieving</th>
                            <th width = "10%" class="lastTd">Salary on Leaving (pa)</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(previousEmpData)!=0}">
                                <c:forEach items="${previousEmpData}" var="previousEmpDatavalues" varStatus="i">
                                    <tr>
                                        <td style="padding:0px 4px 0px 4px;">${i.index+1}</td>
                                        <td style="padding:0px 4px 0px 4px;">
                                            ${fn:toUpperCase(fn:substring(previousEmpDatavalues.namePrevEmpDb, 0, 1))}${fn:toLowerCase(fn:substring(previousEmpDatavalues.namePrevEmpDb, 1,fn:length(previousEmpDatavalues.namePrevEmpDb)))}
                                        </td>
                                        <td style="padding:0px 4px 0px 4px;">
                                            ${previousEmpDatavalues.dateOfJoinDb}
                                        </td>
                                        <td style="padding:0px 4px 0px 4px;">
                                            ${fn:toUpperCase(fn:substring(previousEmpDatavalues.desigOnJoinDb, 0, 1))}${fn:toLowerCase(fn:substring(previousEmpDatavalues.desigOnJoinDb, 1,fn:length(previousEmpDatavalues.desigOnJoinDb)))}
                                        </td>
                                        <td style="padding:0px 4px 0px 4px;">
                                            ${fn:split(previousEmpDatavalues.salaryOnJoinDb, '.')[0]}
                                        </td>
                                        <td style="padding:0px 4px 0px 4px;"> 
                                            ${previousEmpDatavalues.dateOfRelDb}
                                        </td>
                                        <td style="padding:0px 4px 0px 4px;">
                                            ${fn:toUpperCase(fn:substring(previousEmpDatavalues.desigOnRelDb, 0, 1))}${fn:toLowerCase(fn:substring(previousEmpDatavalues.desigOnRelDb, 1,fn:length(previousEmpDatavalues.desigOnRelDb)))}
                                        </td>
                                        <td class="lastTd" style="padding:0px 4px 0px 4px;">
                                            ${fn:split(previousEmpDatavalues.salOnRelDb, '.')[0]}
                                        </td>
                                    </tr>
                                </c:forEach>
                                <c:forEach var="i" begin="${(fn:length(previousEmpData))+1}" end="5" step="1" varStatus="rem">
                                    <tr><td style="padding:0px 4px 0px 4px;">${rem.count + fn:length(previousEmpData)}<br></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td>1<br></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                <tr><td>2<br></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                <tr><td>3<br></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                <tr><td>4<br></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                <tr><td>5<br></td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;">
                        <b>18.	Technical skills (Not applicable for Non Technical Employees)</b><br>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="1" width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse;" class="printFont">
                        <tr>
                            <th>S.No</th>
                            <th width = "35%">Stream</th>
                            <th width = "35%">Skill</th>
                            <th width = "26%" class="lastTd">Experience</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(techSkillsData)!=0}">
                                <c:forEach items="${techSkillsData}" var="techSkillsDataValues" varStatus="i">
                                    <tr>
                                        <td>&nbsp;${i.index+1}</td>
                                        <td>&nbsp;${techSkillsDataValues.streamName}</td>
                                        <td>&nbsp;${techSkillsDataValues.skillName}</td>
                                        <td class="lastTd">&nbsp; ${techSkillsDataValues.skill_yearX} year(s) ${techSkillsDataValues.skill_monthX} month(s) </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                    </c:otherwise>
                                </c:choose>

                    </table>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">&nbsp;</td>
            </tr>
            <tr>
                <td colspan="2" >
                    <p style="text-align:justify;text-indent:50px;">
                        <b>19. Educational Qualifications</b><br>
                    </p>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <table border="1" width="100%" cellpadding="0" cellspacing="0"  style="border-collapse: collapse;" class="printFont">
                        <tr>
                            <th width = "10%" >Degree</th>
                            <th width = "24%">Qualification</th>
                            <th width = "10%">Year of passing</th>
                            <th width = "23%">Institution</th>
                            <th width = "23%">University</th>
                            <th width = "10%" class="lastTd">% / CGPA</th>
                        </tr>
                        <c:choose>
                             <c:when test="${fn:length(educationData)!=0}">
                                <c:forEach items="${educationData}" var="educationDataValues" varStatus="i">
                                    <tr>
                                        <td style="padding:0px 4px 0px 4px;">${educationDataValues.degreeVal}</td>
                                        <td style="padding:0px 4px 0px 4px;">
                                            ${fn:toUpperCase(fn:substring(educationDataValues.qualificationX, 0, 1))}${fn:toLowerCase(fn:substring(educationDataValues.qualificationX, 1,fn:length(educationDataValues.qualificationX)))}
                                        </td>
                                        <td style="padding:0px 4px 0px 4px;">${educationDataValues.year_of_passingX}</td>
                                        <td style="padding:0px 4px 0px 4px;">
                                            ${fn:toUpperCase(fn:substring(educationDataValues.institutionX, 0, 1))}${fn:toLowerCase(fn:substring(educationDataValues.institutionX, 1,fn:length(educationDataValues.institutionX)))}
                                        </td>
                                        <td style="padding:0px 4px 0px 4px;">
                                            ${fn:toUpperCase(fn:substring(educationDataValues.universityX, 0, 1))}${fn:toLowerCase(fn:substring(educationDataValues.universityX, 1,fn:length(educationDataValues.universityX)))}
                                        </td>
                                        <td class="lastTd" style="padding:0px 3px 0px 3px;">${educationDataValues.percentageX}</td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                                <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td><td class="lastTd">&nbsp;</td></tr>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </td>
            </tr>
        </table>
</div>
<style type="text/css">
.printContentED2 .multiaddTable td{
border-left: 1px solid #000000;
border-bottom: 1px solid #000000;
}
.printContentED2 .lastTd{
border-right: 1px solid #000000;
}
.printContentED2 .multiaddTable th{
border-left: 1px solid #000000;
border-top: 1px solid #000000;
border-bottom: 1px solid #000000;
}
    .printContentED2 p{
        text-align:justify;
        line-height: 35px;
    }
    .printContentED2 .paraIndent{
        text-indent:40px;
     }
    .printContentED2 .indentLeft{
        margin-left: 300px;
    }
    .printContentED2 .printFont{
        font-size: 14px;
        font-family:"Times New Roman","serif";
    }

    .printContentED2 .printFont th{
        font-size: 13px;
        font-family:"Times New Roman","serif";
        font-weight: bold;
    }
    .printContentED2 .italicText{
    font-style: italic;
    }
    .printContentED2 .printFont tr{
        height:30px;
    }

     .printContentED2{
        width:650px;
        height:900px;
        /*border: 1px solid #000000;*/
        position: relative;
    }
</style>
