<!--
   created by 14620
   version:1
   Display Survey questionare
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="com.defiance.ideal.exitMgmt.utils.CommonConfigurations" %>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exit Interview Questionnaire</title>
    </head>
   <body onload="changeTabClass('survey');" >
        <span class="topheading">EPM (Exit Process Management)</span>
        <div class="generalFormContent">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <c:choose>
                    <c:when test="${exitEmpInfo.rmApprovedDate!=null}">
                        <table width="100%" border="0">
                            <tbody>
                                <tr>
                                    <td width="100%">
                                        Dear ${exitEmpInfo.employeeName},
                                        <p align="justify" class="contentdata">${exitSurveyProp.exitSurveyHome}</p>
                                    </td>
                                </tr>
                        </table>
                        <%@include file="/WEB-INF/jsp/commonContent.jsp" %>
                        <%@include file="/WEB-INF/jsp/approval/exitSurveyContent.jsp" %>
                    </c:when>
                    <c:otherwise>
                        <div class="qpdSearch" style="height: 25px;">
                        Exit Survey will be enabled upon your resignation acceptance by your reporting manager.
                        </div>
                    </c:otherwise>
                </c:choose>
        </div>
    </body>
</html>
