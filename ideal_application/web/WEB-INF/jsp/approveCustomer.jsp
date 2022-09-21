<%--
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Approve Customer</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/qpd.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/c/jquery-ui-1.8.16.custom.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.16.custom.css" type="text/css"/>--%>
    <style type="text/css">
        #loadingDiv img{ border: none; }

        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1;}

        #contentWrapper{
            width:90%;
            margin-left:5%;
            border:1px solid #ccc;
            float:left;
        }
        .contentGroup{
            width:30%;
            float:left;
            padding:1%;
        }
        .contentLabel{
            width:49%;
            float:left;
        }
        .contentField{
            width:49%;
            float:left;
        }
        .effortChangedDiv{
            background-color: #FF8888;
            background-image: none;
            width:3px;
            height:7px;
            float:left;
        }
        #effortsLegend{
            margin-left:-310px;
        }
        #datadisplay1 table {

            border:1px solid #99BBE8;
            border-top:0;
            clear: both;
            /*color: #000;*/
            width: 100%;
            line-height: 20px;
        }
        #datadisplay1 th,#associateAllocation th{
            /*, #headerTable th*/

            border:1px solid #bbb;
            border-top: 1px solid #fff;
            border-left: 1px solid #fff;
            border-bottom:0;
            text-align: center;
            height:22px;
            font-weight:bolder;
        }
        #datadisplay1 th:hover{
            background:url(images/grid3-hrow-over.gif) repeat-x bottom;
        }
        #datadisplay1 th.sort_selected{
            background:url(images/grid3-hrow-over.gif) repeat-x bottom;
        }
        #datadisplay1 th a {
            width:100%;
            display: block;
            text-decoration: none;
            color:#000;
            cursor:default;
        }
        #datadisplay1 table tr td {
            /*background: #fff;*/
            /*border-right: 1px solid #ccc;*/
            padding:1px 3px;
            vertical-align: top;
        }
        #datadisplay1 table tr.mouseover{
            background:url(images/row-over.gif) repeat-x;
        }
        #datadisplay1 table tr.selected{
            background:#DFE8F6;
        }
        #datadisplay1 table tr.altrow td {
            background: #FAFAFA;
            border-top:1px solid #EDEDED;
            border-bottom:1px solid #EDEDED;
        }
        #datadisplay1 table input.checkbox{
            float:none;
            clear:none;
            margin:0;
        }
        #datadisplay1 td.actions {
            text-align: center;
            white-space: nowrap;
        }
        #datadisplay1 td.actions a {
            margin: 0px 6px;
        }
        #datadisplay1 dl {
            width: 60%;
            margin:10px 0 0 10px;
        }
        #datadisplay1 dl .altrow {
            background: #f4f4f4;
        }
        #datadisplay1 dt {
            font-weight: bold;
            vertical-align: top;
        }
        #datadisplay1 dd {
            margin:0;
            vertical-align: top;
        }
        .viewData, table.financeDetails
        {
            width: 900px;
            margin:10px 10px 10px 25px;
            padding-left: 15px;
            border-radius: 10px;
        }
        table.financeDetails{
              margin:10px 10px 10px 0px;
        }
        .viewData td, table.financeDetails tbody tr td
        {
            padding:5px;
            width:25%;
        }
        table.financeDetails tbody tr td label{
            width:200px;
        }
        table.financeDetails tbody tr td textarea,table.financeDetails tbody tr td select{
            width:99%;
        }
        .viewData tr
        {
            padding:20px;
            width:auto;
        }
        .viewData th
        {
            padding:0px;
            width:auto;
        }
        .viewData td input[type='text']
        {
            width:auto;
        }
        #customerWorkLocation, #customerBillingAddress, #customerFinanceContact,#customerBusinessContact{
            border-collapse: collapse;
            margin:10px 10px 10px 25px;
            width:900px;
        }
        #customerWorkLocation table tr td{
            vertical-align: middle;
        }
        #customerWorkLocation td, #customerBillingAddress td, #customerFinanceContact td, #customerBusinessContact td{
            font-size: 11px;
            padding-left:10px !important;
            border: 1px solid #99BBE8;
            text-align: left;
            vertical-align: middle;
        }
        #customerWorkLocation th, #customerBillingAddress th, #customerFinanceContact th, #customerBusinessContact th{
            font-size: 12px;
            border: 1px solid #99BBE8;
            text-align: left;
        }
        #customerFinanceContact td, #customerBusinessContact td{
            width:25%;
        }
        #customerBillingAddress td{
            width:20%;
        }
        #customerWorkLocation td{
            width:15%;
        }
        #customerWorkLocation td:last-child{
            width:10%;
        }
        #customerBillingAddress td:first-child, #customerWorkLocation td:first-child{
            width:30%;
        }
        #customerBillingAddress td:last-child{
            width:10%;
        }
        h3{
            margin-left: 20px;
        }
        .buttonContainer{
            text-align: center;
            margin:20px;
        }
        #datadisplay table tr td {
            vertical-align: middle;
        }
        #datadisplay table {
            border:1px solid #99BBE8;
            clear: both;
            line-height: 20px;
        }
        #errormessage{
            color: red;
            font-weight: bolder;
        }
        #infoIcon{
            width: 20px;
            padding: 7px 0px 0px 0px;
            vertical-align: bottom;
        }
       #tickIcon{
            width: 10px;
            padding: 6px 0px 0px 0px;
       }
       .InfoText{
           margin:5px;
       }
        #divisionNameDisplay{
            margin-left: 15px;
        }
    </style>
    <script type="text/javascript">
        function getRegions(selectedValue) {
            $("#region").html("<option value=''>-- Select Region --</option>");
            if (selectedValue != "") {
                $.ajax({
                    url: './getRegionList.htm',
                    type: "POST",
                    async: false,
                    data: ({countryId: selectedValue}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            }
        }
        function getCities(selectedValue) {
            $("#city").html("<option value=''>-- Select City --</option>");
            var selectedCountryValue = $("#country").val();
            if (selectedValue != "" && selectedCountryValue != "") {
                $.ajax({
                    url: './getCityList.htm',
                    type: "POST",
                    async: false,
                    data: ({countryId: selectedCountryValue, regionId: selectedValue}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            }
        }
        function submitForm() {
            document.getElementById("reportForm").action = "customerList.htm";
            document.reportForm.submit();
            startLoading();
        }
        function addCustomer() {

            document.location.href = "addCustomer.htm";
        }
        $(document).ready(function() {
            $('#customerDataList').dataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                "iDisplayLength": 20
            });

        });
        function disableApprove(saveButtonId, submitButtonId, backButtonId) {
//            if ($("#customerInvoiceCode").val() == "") {
//                $("#errorMessageLine").show();
//                $("#errormessage").html("Select Invoice Code")
//                return false;
//                //            } else if($("#remarks").val() == "") {
//                //                $("#errormessage").html("Provide Remarks")
//                //                return false;
//                //                return false;
//            } <%--else if($("#customerGroupName").val() == "") {
                $("#errormessage").html("Select Customer Group")
                return false;
            }--%>// else if ($("#termsOfPayment").val() == "") {
//                $("#errormessage").html("Select Terms of Payment")
//                return false;
//            } else 
//            if ($("#remarks").val() == "") {
//                $("#errorMessageLine").show();
//                $("#errormessage").html("Please enter the remarks")
//                return false;
//            } else {
//                $("#" + saveButtonId).hide();
//                $("#" + submitButtonId).hide();
//                $("#" + backButtonId).hide();
//                return true;
//            }
            $("#" + saveButtonId).hide();
            $("#" + submitButtonId).hide();
            $("#" + backButtonId).hide();
            return true;
        }
        function disableReject(saveButtonId, submitButtonId, backButtonId) {
            if ($("#remarks").val() == "") {
                $("#errorMessageLine").show();
                $("#errormessage").html("Please Provide Remarks")
                return false;

            } else {
                $("#" + saveButtonId).hide();
                $("#" + submitButtonId).hide();
                $("#" + backButtonId).hide();
                return true;
            }
        }
        function textLimit(field, maxlen) {
            if (field.value.length > maxlen) {
                while (field.value.length > maxlen) {
                    field.value = field.value.replace(/.$/, '');
                }
                $("#errorMessageLine").show();
                $('#errormessage').html('Should not more than ' + maxlen + ' characters');
                //        alert('your input has been truncated!');
            }
        }
    </script>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div id="">
            <div class="container_inner" style="margin: 30px 0 24px;">
                <p class="page_heading"><span class="heading">Approve Customer</span> </p>
            </div>

            <div class="notice_page">
                <img src="/iDeal_application/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon"></img>
                <img src="/iDeal_application/css/images/tick.png" title="Information" alt="Information" id="tickIcon"></img>
                <span class="InfoText">Fields marked in <font color="red">*</font> are mandatory.</span>
            </div>
            <form action="approveCustomerProcess.htm" name="formUserDetails" id="formUserDetails" method="post">
                <div class="formContent_new" id="datadisplay" style="height:auto; width:100%">
                    <h3>Customer Details</h3>
                    <span style="display:none">${selectedCustomerData[0].custID}</span>
                    <table class="viewData" id="formTable" border="0" align="center">
                        <tbody>
                            <tr>
                                <td>
                                    <span class="" style="font-weight:bold">Customer Name: </span>
                                </td>
                                <td>
                                    <input type="hidden" name="customerName" id="customerName" value="${selectedCustomerData[0].customerName}" />
                                    <span class="">${selectedCustomerData[0].customerName}</span>
                                </td>    
                                <td>   
                                    <span class="" style="font-weight:bold">BDM/SalesPerson Name:</span>
                                </td>
                                <td>
                                    <span class="">${selectedCustomerData[0].salesPerson}</span>
                                </td>
                            </tr>
                            <tr>
                                 <td>
                                    <span class="" style="font-weight:bold">Customer Code:</span>
                                </td>
                                <td>
                                    <span class="">${selectedCustomerData[0].customerCode}</span>
                                </td>
                                
                                
                                <td>
                                    <span class="" style="font-weight:bold">BDM/Sales Person Contact No:</span>
                                </td>


                                <td>
                                    <input type="hidden" name="salesPerson" id="salesPerson" value="${selectedCustomerData[0].salesPersonRefId}" />
                                    <span class="">${selectedCustomerData[0].salesPersonContactNo}</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="" style="font-weight:bold">About Customer:</span>
                                </td>
                                <td>
                                    <span class="">${selectedCustomerData[0].aboutCustomer}</span>
                                </td>
                                
                                <td>
                                    <span class="" style="font-weight:bold">Attachment type:</span>
                                </td>
                                <td>
                                    <span class="">${selectedCustomerData[0].attachmentType}</span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="" style="font-weight:bold">Customer URL:</span>
                                </td>
                                <td>
                                    <c:set var="theString" value="${selectedCustomerData[0].customerURL}"/>
                                    <c:choose>
                                        <c:when test="${fn:contains(theString, 'http://')}">
                                            <a title="View" href="${selectedCustomerData[0].customerURL}" target="_blank">${selectedCustomerData[0].customerURL}</a>
                                        </c:when>
                                        <c:otherwise>
                                            <a title="View" href="http://${selectedCustomerData[0].customerURL}" target="_blank">${selectedCustomerData[0].customerURL}</a>
                                        </c:otherwise>
                                    </c:choose>
                                </td>

                                <td>
                                    <span class="" style="font-weight:bold">Attachments(NDA/PO/MSA):</span>
                                </td>
                                <td>
                                    <span class=""><a href="attachmentDownload.htm?fileName=${selectedCustomerData[0].fileName}&fileType=${selectedCustomerData[0].fileType}" target="_blank" name="file">${selectedCustomerData[0].attchedFileName}</a></span>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <span class="" style="font-weight:bold">TAN (For India customers):</span>
                                </td>    
                                <td>
                                    <span class="">${selectedCustomerData[0].tanNumber}</span>
                                </td>
                                <td></td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>
                    <c:choose>
                        <c:when test="${(selectedCustomerData[0].customerDivision)!=null && (selectedCustomerData[0].customerDivision)!=''}">
                            <div id="divisionNameDisplay">
                                <span class="" style="font-weight:bold">Division Name:</span>&nbsp&nbsp&nbsp&nbsp
                                <span class="">${selectedCustomerData[0].customerDivision}</span>
                            </div>
                        </c:when>
                    </c:choose> 
                    <h3>Customer Billing Address</h3>
                    <c:if test="${fn:length(selectedBillingAddress)>0}">
                        <table class="customerBillingAddress" id="customerBillingAddress">
                            <tr>
                                <th style="width:300px">Address</th>
                                <th>Short Code</th>
                                <th>Country</th>
                                <th>Region</th>
                                <th>City</th>
                                <th>Pincode</th>
                                <th>State Code</th>
                                <th>GSTIN/UID</th>
                            </tr>
                            <c:forEach items="${selectedBillingAddress}" var="selectedBillingAddress" >
                                <tr id="finance_contact_row_1">
                                    <td>
                                        <span class="">${selectedBillingAddress.customerAddress}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedBillingAddress.addressShortCode}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedBillingAddress.country}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedBillingAddress.region}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedBillingAddress.city}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedBillingAddress.pincode}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedBillingAddress.gstCode}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedBillingAddress.gstNumber}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${fn:length(selectedBillingAddress)==0}">
                        <table class="customerBillingAddress" id="customerBillingAddress">
                            <thead>
                            <th>
                                No data to display
                            </th>
                            </thead>
                        </table>
                    </c:if>
                    <h3>Customer Work Location</h3>
                    <c:if test="${fn:length(selectedCustomerWorkLocations)>0}">
                        <table class="customerWorkLocation" id="customerWorkLocation">
                            <tr>
                                <th style="width:300px">Address</th>
                                <th>Short Code</th>
                                <th>Country</th>
                                <th>Region</th>
                                <th>City</th>
                                <th>Pincode</th>
                                <th>Working Hrs/Day</th>
                                <th>Work Location</th>
                            </tr>
                            <c:forEach items="${selectedCustomerWorkLocations}" var="selectedCustomerWorkLocations" >
                                <tr id="finance_contact_row_1">
                                    <td>
                                        <span class="">${selectedCustomerWorkLocations.customerAddress}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedCustomerWorkLocations.addressShortCode}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedCustomerWorkLocations.country}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedCustomerWorkLocations.region}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedCustomerWorkLocations.city}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedCustomerWorkLocations.pincode}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedCustomerWorkLocations.workLocationWorkingHours}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedCustomerWorkLocations.isCompanyLocation}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${fn:length(selectedCustomerWorkLocations)==0}">
                        <table class="customerWorkLocation" id="customerWorkLocation">
                            <thead>
                            <th>
                                No data to display
                            </th>
                            </thead>
                        </table>
                    </c:if>
                    
                    <h3>Business Contact</h3>
                    <c:if test="${fn:length(selectedCustomerContactDetails)>0}">
                        <table class="customerBusinessContact" id="customerBusinessContact">
                            <tr>
                                <th>Name</th>
                                <th>Designation</th>
                                <th>Phone No.</th>
                                <th>Email Id</th>
                            </tr>
                            <c:forEach items="${selectedCustomerContactDetails}" var="selectedCustomerContactDetails" >
                                <tr id="finance_contact_row_1">
                                    <td>
                                        <span class="">${selectedCustomerContactDetails.contactPerson}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedCustomerContactDetails.contactDesignation}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedCustomerContactDetails.contactPhone}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedCustomerContactDetails.contactEmail}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${fn:length(selectedCustomerContactDetails)==0}">
                        <table class="customerBusinessContact" id="customerBusinessContact">
                            <thead>
                            <th>
                                No data to display
                            </th>
                            </thead>
                        </table>
                    </c:if>
                    <h3>Finance Contact</h3>
                    <c:if test="${fn:length(selectedFianceContactDetails)>0}">
                        <table class="customerFinanceContact" id="customerFinanceContact">
                            <tr>
                                <th>Name</th>
                                <th>Designation</th>
                                <th>Phone No.</th>
                                <th>Email Id</th>
                            </tr>
                            <c:forEach items="${selectedFianceContactDetails}" var="selectedFianceContactDetails" >
                                <tr id="finance_contact_row_1">
                                    <td>
                                        <span class="">${selectedFianceContactDetails.contactPerson}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedFianceContactDetails.contactDesignation}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedFianceContactDetails.contactPhone}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedFianceContactDetails.contactEmail}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    <c:if test="${fn:length(selectedFianceContactDetails)==0}">
                        <table class="customerFinanceContact" id="customerFinanceContact">
                            <thead>
                            <th>
                                No data to display
                            </th>
                            </thead>
                        </table>
                    </c:if>
                     <h3>Invoice Recipient</h3>                   
                        <table class="customerFinanceContact" id="customerFinanceContact">
                            <tr>
                                <td align="center"><b>Invoice will be emailed to </b></td>
                                <td>${selectedCustomerData[0].invoiceValue} </td>
                            </tr>
                            
                        </table>                   
                    <!--<h3>Finance Details</h3>-->
                    <table class="financeDetails" style="border:none">
                        <tr>
                            <td id="errorMessageLine" align="center" colspan="4"  style="height:15px; display:none">
                             <span id="errormessage" class="error-message">${Result}</span>
                            </td>
                        </tr>
                        <tr>
<!--                            <td>
                                <label class="" style="font-weight:bold; width: 200px" width="220px">Invoice Code<font color="red">*</font>:</label>
                            </td>

                            <td>
                                <c:set var="disableCustomerInvoiceCode" value=""></c:set>
                                <c:if test="${fn:trim(selectedCustomerData[0].customerCode) != ''}">
                                    <c:set var="disableCustomerInvoiceCode" value="disabled"></c:set>
                                    <input type="hidden" name="customerInvoiceCode" id="customerInvoiceCode" value="${selectedCustomerData[0].customerInvoiceCode}" />
                                </c:if>
                                <select ${disableCustomerInvoiceCode} name="customerInvoiceCode" id="customerInvoiceCode" class="textbox_new">
                                    <option value="">-- Select Invoice Code -- </option>
                                    <c:forEach items="${invoiceCodeList}" var="invoiceCodeList" >
                                        <c:set var="selcustomerInvoiceCode" value=""></c:set>
                                        <c:if test="${selectedCustomerData[0].customerInvoiceCode == invoiceCodeList.invoiceID}">
                                            <c:set var="selcustomerInvoiceCode" value="selected"></c:set>
                                        </c:if>
                                        <option value="${invoiceCodeList.invoiceID}" ${selcustomerInvoiceCode}>${invoiceCodeList.invoiceCode}</option>
                                    </c:forEach>
                                </select>
                            </td>-->
                            <td>
                                <label class="" style="font-weight:bold; width: 200px;padding: 0px 35px 0px 15px;margin: 0px 5px;">Remarks :</br><span style="font-size:10px">(Mandatory for send Back)</span></label>
                            </td>
                            <td>
                                <textarea cols="20" rows="2" name="remarks" id="remarks"  maxlength="500"  minlength="1" onblur="textLimit(this, 500);" onkeyup="textLimit(this, 250)" class="textbox_new required address minlength maxlength resizableTextArea ui-resizable">${selectedCustomerData[0].remarks}</textarea>
                            </td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                    <div class="buttonAlignment">
                        <div class="buttonAlignment" id="btnGroup">
                            <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}" />
                            <input type="hidden" name="customerID" id="customerID" value="${selectedCustomerData[0].custID}" />
                            <input type="hidden" name="customerCode" id="customerCode" value="${selectedCustomerData[0].customerCode}" />
                            <input type="hidden" name="status" id="status" value="${selectedCustomerData[0].status}" />
                            <input type="button" name="btnCancel" id="btnCancel" style="cursor: pointer;" value="Back" class="qualityback" onclick="javascript: location.href = 'authenticate.htm?empId=${employee_no}&modId=76'">
                            <c:if test="${selectedCustomerData[0].status == 'd' || selectedCustomerData[0].status == 'm'}">
                                <input type="submit" name="customerApproveButton" id="approveBtn" style="width: 90px; cursor: pointer;" value="Approve" class="qualitysubmit" onclick="return disableApprove('approveBtn', 'rejectBtn', 'btnCancel');">
                                <input type="submit" name="customerRejectButton" id="rejectBtn" style="width: 100px; cursor: pointer" value="Send Back" class="qualitycancel" onclick="return disableReject('approveBtn', 'rejectBtn', 'btnCancel');">
                            </c:if>
                        </div>
                    </div> 
                </div>
            </form>
            
        </div>
    </div>
    <script type="text/javascript">
        var loadingDivObj = (document.all);
        var ns4 = document.layers;
        var ns6 = document.getElementById && !document.all;
        var ie4 = document.all;
        if (ns4) {
            loadingDivObj = document.loadingDiv;
        } else if (ns6) {
            loadingDivObj = document.getElementById("loadingDiv").style;
        } else if (ie4) {
            loadingDivObj = document.all.loadingDiv.style;
        }

        stopLoading();

        function stopLoading() {
            if (ns4) {
                loadingDivObj.visibility = "hidden";
            }
            else if (ns6 || ie4)
                loadingDivObj.display = "none";
        }

        function startLoading() {
            if (ns4) {
                loadingDivObj.visibility = "visible";
            }
            else if (ns6 || ie4)
                loadingDivObj.display = "block";
        }
    </script>
</body>