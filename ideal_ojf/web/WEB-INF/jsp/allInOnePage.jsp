<% response.addHeader("P3P","CP=\"IDC DSP COR ADM DEVi TAIi PSA PSD IVAi IVDi CONi HIS OUR IND CNT\""); %>
    <head>
        <style type="text/css">
            <%--.iFramePrint{
                height:100%;
                width:850px;
            }--%>
            .printPages{
                page-break-after: always;
            }
        </style>
    </head>
<body>
<div class="printPages">
<jsp:include page="joiningReport.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="empDetailsPageOne.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="empDetailsPageTwo.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="empDetailsPageThree.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="empDetailsPageFour.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="groupMedical.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="lifeInsurance.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="paymentOfGratuityOne.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="paymentOfGratuityTwo.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="payrollDetails.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="empIdCard.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="requestForEmail.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="defianceWorkDetails.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="personalUndertaking.jsp"></jsp:include>
</div>
<div class="printPages">
<jsp:include page="pfNominationForm.jsp"></jsp:include>
</div>
