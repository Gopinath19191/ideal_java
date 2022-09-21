<%-- 
    Document   : pfNominationForm
    Created on : Dec 6, 2010, 12:03:17 PM
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
        <title>PF Nomination Form</title>
</head>
     <c:if test="${param.cancelPrint!=0}">
        <script type="text/javascript">
            window.print();
        </script>
    </c:if>
    <div class="printContentPfNomin">
    <jsp:include page="./printHeader.jsp"></jsp:include>
        <table width="100%" border="0" cellpadding="2" cellspacing="2" class="printFont">
            <tr><td align="center" colspan="3"><br>Guidelines for filling PF nomination form
                </td></tr>
            <tr><td align="center" colspan="3"><br><u><b>For employees who are married</b></u><br>
                </td></tr>
            <tr><td>
                    <table width="100%" border="1" cellpadding="2" cellspacing="2" class="printFont"><tr><td>
                                <p style="text-align:justify;text-indent:0px;">
                                    PART 'A' <b>(EPF - EMPLOYEE PROVIDENT FUND) </b>:<br>Please ensure the percentage of share payable to the nominee (In case if there is more than one) is clearly indicated
                                    Please mention the Date of Birth of the nominee clearly in the form. It’s very important.</p></td></tr>
                    </table></td></tr>
            <tr><td>
                    <table width="100%" border="1" cellpadding="2" cellspacing="2" class="printFont"><tr><td>
                                <p style="text-align:justify;text-indent:0px;">
                                    PAGE 2:  PART 'B' <b>(EPS - EMPLOYEE PENSION SCHEME):</b>
                                    First Block / Table is to be filled, you can nominate your family members (Spouse & Children) only. In the second block / Table, you can nominate anybody (Other than your Spouse & Children) OR You can nominate your youngest Child (for maximum benefit)
                                </p></td></tr>
                    </table></td></tr>
            <tr><td>
                    <table width="100%" border="1" cellpadding="2" cellspacing="2" class="printFont"><tr><td>
                                <p style="text-align:justify;text-indent:0px;">
                                    Please ensure that your Signature is Affixed in the First and the Second page.
                                    (ie). THE<b> PF AND THE EPS FORM</b>
                                </p></td></tr>
                    </table></td></tr>
            <tr><td align="center" colspan="3"><u><b>For employees who are single</b></u>
                </td></tr>
            <tr><td>
                    <table width="100%" border="1" cellpadding="2" cellspacing="2" class="printFont"><tr><td>
                                <p style="text-align:justify;text-indent:0px;">
                                    PART 'A' <b>(EPF - EMPLOYEE PROVIDENT FUND) </b>:<br>Please ensure the percentage of share payable to the nominee (In case if there is more than one) is clearly indicated
                                    Please mention the Date of Birth of the nominee clearly in the form. It’s very important.<br></p></td></tr>
                    </table></td></tr>
            <tr><td>
                    <table width="100%" border="1" cellpadding="2" cellspacing="2" class="printFont"><tr><td>
                                <p style="text-align:justify;text-indent:0px;">
                                    PAGE 2:  PART 'B' <b>(EPS - EMPLOYEE PENSION SCHEME):</b>
                                    <br>
                                    First Block / Table is to be filled, you can nominate your family members (Spouse & Children) only. In the second block / Table, you can nominate anybody (Other than your Spouse & Children) OR <br>You can nominate your youngest Child (for maximum benefit)
                                </p></td></tr>
                    </table></td></tr>
            <tr><td>
                    <table width="100%" border="1" cellpadding="2" cellspacing="2" class="printFont"><tr><td>
                                <p style="text-align:justify;text-indent:0px;">
                                    Please ensure that your Signature is Affixed in the First and the Second page.
                                    (ie). THE<b> PF AND THE EPS FORM</b>
                    </table>
                </td>
            </tr>
        </table>
    </div>
    <style type="text/css">
   .printContentPfNomin  p{
        text-align:justify;
        line-height: 35px;
    }
    .printContentPfNomin .paraIndent{
        text-indent:40px;
     }
    .printContentPfNomin .indentLeft{
        margin-left: 300px;
    }
    .printContentPfNomin .printFont{
        font-size: 13px;
        font-family:"Times New Roman","serif";
    }
    .printContentPfNomin .italicText{
    font-style: italic;
    }
    .printContentPfNomin .printFont tr{
        height:30px;

    }
    .printContentPfNomin{
        width:650px;
        height:900px;
        /*border: 1px solid #000000;*/
        position: relative;
    }
    </style>
