<%@include file="/header.jsp"  %>
<head>
    <title>Joining Formalities</title>
<!--
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/jquey.tablesorter.js"></script>
<script type="text/javascript">
       $(document).ready(function(){
        $("#recruitmentList").tablesorter();
       });
</script>
-->
</head>
<body>
<div id="container">
        <%@include file="menu.jsp" %>
        <br>
        <div id="datadisplay">
            <form name="printAll" method="post" action="printAllPages.htm">
            <table border="0">
                <th colspan="2">On-boarding Checklist</th>
            <tr>
                <td>1</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/joinerReportPrint.htm',${param.jfId})" >Joining Report</a></td>
            </tr>
            <tr>
                <td>2</td>
                <td>Employee Details&nbsp;&nbsp;<a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/empDetailsPageOne.htm',${param.jfId})" >Page 1</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/empDetailsPageTwo.htm',${param.jfId})">Page 2</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/empDetailsPageThree.htm',${param.jfId})">Page 3</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/empDetailsPageFour.htm',${param.jfId})">Page 4</a></td>
            </tr>
            <tr>
                <td>3</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/groupMedical.htm',${param.jfId})">Group Medical Insurance Policy Proposal</a></td>
            </tr>
            <tr>
                <td>4</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/lifeInsurance.htm',${param.jfId})">Nomination Form - Life Insurance</a></td>
            </tr>
            <tr>
                <td>5</td>
                <td>The Payment of Gratuity (Central) Rules&nbsp;&nbsp;<a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/paymentOfGratuityOne.htm',${param.jfId})">Page 1</a>&nbsp;&nbsp;<a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/paymentOfGratuityTwo.htm',${param.jfId})">Page 2</a></td>
            </tr>
            <tr>
                <td>6</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/payrollDetails.htm',${param.jfId})">Payroll Details</a></td>
            </tr>
            <tr>
                <td>7</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/empIdCard.htm',${param.jfId})">Employee ID Card Form</a></td>
            </tr>
            <tr>
                <td>8</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/requestForEmail.htm',${param.jfId})">Request for Creation of E-mail and Windows user account</a></td>
            </tr>
            <tr>
                <td>9</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/defianceWorkDetails.htm',${param.jfId})">Hinduja Tech Work Related Details</a></td>
            </tr>
            <tr>
                <td>10</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/personalUndertaking.htm',${param.jfId})">Personal Undertaking Form</a></td>
            </tr>
            <tr>
                <td>11</td>
                <td><a href="javascript:void(0)" onclick="printJFDetails('${pageContext.request.contextPath}/pfNominationForm.htm',${param.jfId})">Guidelines for filling PF nomination form</a></td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="hidden" name="empId" id="empId" value="${param.jfId}" />
                    <input type="submit" name="printAll" id="printAll" value="PrintAll"/>
                </td>
            </tr>
            </table>
            </form>
        </div>
</div>
            <form action="" method="POST" name="printForm" id="printForm">
                <input type="hidden" name="empId" id="empId" value="" />
            </form>
            <script type="text/javascript">
                function printJFDetails(url,jfId)
                {
                    <%--alert(url+"--"+jfId);--%>
                    document.printForm.empId.value=jfId;
                    document.printForm.action=url;
                    document.printForm.submit();
                }
            </script>
<%@include file="footer.jsp"  %>
