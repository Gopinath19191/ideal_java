    <%--
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>View Customer</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<!--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/qpd.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/c/jquery-ui-1.8.16.custom.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.16.custom.css" type="text/css"/>--%>
    <style type="text/css">
        #loadingDiv img{ border: none; }

        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}

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
        #datadisplay table {
            border:1px solid #99BBE8;
            clear: both;
            /*color: #000;*/
            line-height: 20px;
            
        }
/*        .qualityedit{
            background: url("css/images/icon_btn_edit.png") no-repeat scroll 8px 8px #316CA8;
            border: 1px solid #4492BF;
            color: #FFFFFF;
            font-weight: bold;
            height: 30px;
            margin: 0 0 0 15px;
            padding: 0 10px 0 30px;
            width:90px;
        }*/
        .viewData
        {
            width: 900px;
            margin:10px 10px 10px 25px;
            padding-left: 15px;
            border-radius: 10px;
        }
        .viewData td
        {
            padding:5px;
            width:25%;
            word-wrap:break-word;
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
        #customerWorkLocation, #customerBillingAddress, #customerFinanceContact,#customerBusinessContact,#customerDunningContact{
            border-collapse: collapse;
            margin:10px 10px 10px 25px;
            width:900px;
        }
        #customerWorkLocation table tr td{
            vertical-align: middle;
        }
        #customerWorkLocation td, #customerBillingAddress td, #customerFinanceContact td, #customerBusinessContact td,#customerDunningContact td{
            font-size: 11px;
            border: 1px solid #99BBE8;
            text-align: left;
            vertical-align: middle;
            padding-left: 10px;
        }
        #customerFinanceContact td, #customerBusinessContact td, #customerDunningContact td{
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
        #customerWorkLocation th, #customerBillingAddress th, #customerFinanceContact th, #customerBusinessContact th, #customerDunningContact th{
            border: 1px solid #99BBE8;
            text-align: left;
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
            padding-left:10px;
        }
        #divisionNameDisplay{
            margin-left: 15px;
        }
    </style>
    <script type="text/javascript">
        function getRegions(selectedValue) {
            $("#region").html("<option value=''>-- Select Region --</option>");
            if(selectedValue != "") {
                $.ajax({
                    url: './getRegionList.htm',
                    type: "POST",
                    async: false,
                    data: ({countryId:selectedValue}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            }
        }
        function getSubRBU(selectedValue) {
            $("#subRBU").html("<option value=''>-- Select Sub RBU --</option>");
            if(selectedValue != "") {
                $.ajax({
                    url: './getsubRBUList.htm',
                    type: "POST",
                    async: false,
                    data: ({RBU:selectedValue}),
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
            if(selectedValue != "" && selectedCountryValue != "") {
                $.ajax({
                    url: './getCityList.htm',
                    type: "POST",
                    async: false,
                    data: ({countryId:selectedCountryValue,regionId:selectedValue}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            }
        }
        function submitForm(){
            document.getElementById("reportForm").action="customerList.htm";
            document.reportForm.submit();
            startLoading();
        }
        function disableSave(saveButtonId,submitButtonId,backButtonId)
        {
            if($("#customerName").val() == "") {
                $("#errormessage").html("Please Select Customer Name")
                return false;
            } else if ($("#salesPerson").val() == "") {
                $("#errormessage").html("Please Select Customer Owner")
                return false;
            } else {
                var returnVariable;
                if($("#customerName").val() != "") {
                    customerNameD = $("#customerName").val();
                    customerIDVar = $("#customerID").val();
                    $.ajax({
                        url: './getDuplicateCustomerName.htm',
                        type: "POST",
                        async: false,
                        data: ({customerName:customerNameD,customerID:customerIDVar}),
                        success: function(ajaxObj) {
                            if($.trim(ajaxObj) != "") {
                                $("#errormessage").html("Customer Name already exist")
                                returnVariable = false;
                            } else {
                                returnVariable = true;
                            }
                        }
                    });
                }
                if(returnVariable) {
                    $("#"+saveButtonId).hide();
                    $("#"+submitButtonId).hide();
                    $("#"+backButtonId).hide();
                }
                return returnVariable;

            }
        }
        function disableSubmit(saveButtonId,submitButtonId,backButtonId)
        {
            $("#formCustomerDetails").validate();
            if($("#formCustomerDetails").valid()) {
                var returnVariable;
                if($("#customerName").val() != "") {
                    customerNameD = $("#customerName").val();
                    customerIDVar = $("#customerID").val();
                    $.ajax({
                        url: './getDuplicateCustomerName.htm',
                        type: "POST",
                        async: false,
                        data: ({customerName:customerNameD,customerId:customerIDVar}),
                        success: function(ajaxObj) {
                            if($.trim(ajaxObj) != "") {
                                $("#errormessage").html("Customer Name already exist")
                                returnVariable = false;
                            } else {
                                returnVariable = true;
                            }
                        }
                    });
                }
                if(returnVariable) {
                    $("#"+saveButtonId).hide();
                    $("#"+submitButtonId).hide();
                    $("#"+backButtonId).hide();
                }
                return returnVariable;
            }
        }
        function addCustomer(){

            document.location.href="addCustomer.htm";
        }
        $(document).ready(function() {
            $('#customerDataList').dataTable({
                "bLengthChange": false,
                "sPaginationType": "full_numbers",
                "iDisplayLength" : 20
            } );

        } );
        function hideCustomerGroupName() {
            var customerGroupId =  $("input[@name=customerGroup]:checked").attr('id');
            var customerGroupValue = $("#"+customerGroupId).val();
            if(customerGroupValue == 'n') {
                $("#customerGroupName").removeAttr("disabled");
            } else {
                $("#customerGroupName").attr("disabled","disabled");
            }
        }
        function textLimit(field,maxlen) {
            if(field.value.length > maxlen){
                while(field.value.length > maxlen){
                    field.value=field.value.replace(/.$/,'');
                }
                $('#errormessage').html('Should not more than ' + maxlen +  ' characters');
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
                <p class="page_heading"><span class="heading">${selectedCustomerData[0].customerName} - View Customer</span> </p>
            </div>
<!--            <div class="notice_page">
                <div style="float: left"><img src="/ideal_application/css/images/icon_alert.png" title="Information" alt="Information"></img></div>
                <div style="padding-left: 10px;padding-top: 1px;">
                    <img src="/ideal_application/css/images/tick.png" title="Information" alt="Information" style="margin-left: 15px;margin-right: 10px;"></img>
                    Fields marked in * are mandatory.
                </div>
            </div>-->
            <form action="saveCustomer.htm" name="formCustomerDetails" id="formCustomerDetails" method="post" enctype="multipart/form-data">
                <div class="formContent_new" id="datadisplay" style="height:auto; width:100%">
                    <h3>Customer Details</h3>
                    <span style="display:none">${selectedCustomerData[0].custID}</span>
                    <span style="display:none">${selectedCustomerData[0].status}</span>
                    <table class="viewData"id="formTable" border="0" align="center">
                        <tbody>
                            
                            <tr>
                                <td>
                                    <span class="" style="font-weight:bold">Customer Name: </span>
                                </td>
                                <td>
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
<!--                                <td>
                                    <span class="" style="font-weight:bold">Terms of Payment:</span>
                                </td>
                                <td>
                                    <span class="">${selectedCustomerData[0].termsOfPayment} Days</span>
                                </td>-->
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
                                    <span class="">
                                       <a href="attachmentDownload.htm?fileName=${selectedCustomerData[0].fileName}&fileType=${selectedCustomerData[0].fileType}" target="_blank" name="file">${selectedCustomerData[0].attchedFileName}</a>
                                    </span>
                                </td>
                            </tr> 
                            <tr>
                                <td>
                                    <span class="" style="font-weight:bold">TAN(For India):</span>
                                </td>
                                <td>
                                    <span class="">${selectedCustomerData[0].tanNumber} </span>
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
                                <th>Address</th>
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
                                <th>Address</th>
                                <th>Short Code</th>
                                <th>Country</th>
                                <th>Region</th>
                                <th>City</th>
                                <th>Pincode</th>
                                <th>Working Hours/Day</th>
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
                    <h3>Dunning Report Contact</h3>
                    <c:if test="${fn:length(selectedDunningContactDetails)>0}">
                        <table class="customerDunningContact" id="customerDunningContact">
                            <tr>
                                <th>Name</th>
                                <th>Designation</th>
                                <th>Phone No.</th>
                                <th>Email Id</th>
                            </tr>
                            <c:forEach items="${selectedDunningContactDetails}" var="selectedDunningContactDetails" >
                                <tr id="Dunning_contact_row_1">
                                    <td>
                                        <span class="">${selectedDunningContactDetails.contactPerson}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedDunningContactDetails.contactDesignation}</span>
                                    </td>
                                    <td>
                                        <span class="">${selectedDunningContactDetails.contactPhone}</span>
                                    </td>
                                    <td>
                                       <span class="">${selectedDunningContactDetails.contactEmail}</span>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                    
                    <h3>Invoice Recipient</h3>                   
                        <table class="customerFinanceContact" id="customerFinanceContact">
                            <tr>
                                <td align="center"><b>Invoice will be emailed to </b></td>
                                <td>${selectedCustomerData[0].invoiceValue} </td>
                            </tr>
                            
                        </table>                   
                    
                    
                    <c:if test ="${selectedCustomerData[0].remarks!= null &&  fn:length(selectedCustomerData[0].remarks)>0}">
                        <tr>
                            <td>
                                &nbsp&nbsp&nbsp&nbsp&nbsp<span class="" style="font-weight:bold">Remarks:</span>
                                
                            </td>
                            <td>
                                <span class="">${selectedCustomerData[0].remarks}</span>
                            </td>
                        </tr>
                    </c:if>
                    
                    <div class="buttonContainer" id="buttonContainer">
                        <!--<a title="Edit" target="_self" style="text-decoration:none" href="${pageContext.request.contextPath}/authenticate.htm?empId=${employee_no}&modId=79&customerId=${rprt.custID}">-->
<!--                        <input type="button" name="btnCancel" id="btnCancel" value="Back" class="qualityback" href="customerDivisionList.htm?customerName=${selectedCustomerData[0].customerName}" style="cursor: pointer">-->
                        <c:choose>
                            <c:when test="${editOption=='1'}">
                                <input type="button" name="btnCancel" id="btnCancel" value="Back" style="cursor:pointer;" class="qualityback" onclick="javascript: location.href='customersList.htm'">
                                <c:if test="${selectedCustomerData[0].status == 'a' || selectedCustomerData[0].status == 'r' || selectedCustomerData[0].status == 's'}">
                                    <input type="button" name="btnEdit" id="btnEdit" value="Edit" class="qualityedit" onclick="javascript: location.href='${pageContext.request.contextPath}/authenticate.htm?empId=${employee_no}&modId=79&customerId=${selectedCustomerData[0].custID}'" style="cursor: pointer">
                                </c:if>
                            </c:when>
                            <c:otherwise>
                                <input type="button" name="btnCancel" id="btnCancel" value="Back" style="cursor:pointer;" class="qualityback" onclick="javascript: location.href='financeView.htm'">
                            </c:otherwise>
                        </c:choose>
                        
                        
                    </div>
                </div>
                
            </form>
        </div>

    </div>
    <script type="text/javascript">
        var loadingDivObj=(document.all);
        var ns4=document.layers;
        var ns6=document.getElementById&&!document.all;
        var ie4=document.all;
        if (ns4){
            loadingDivObj=document.loadingDiv;
        }else if (ns6){
            loadingDivObj=document.getElementById("loadingDiv").style;
        }else if (ie4){
            loadingDivObj=document.all.loadingDiv.style;
        }

        stopLoading();

        function stopLoading(){
            if(ns4){loadingDivObj.visibility="hidden";}
            else if (ns6||ie4) loadingDivObj.display="none";
        }

        function startLoading(){
            if(ns4){loadingDivObj.visibility="visible";}
            else if (ns6||ie4) loadingDivObj.display="block";
        }
        $(document).ready(function(){
           $( "body" ).on( "click", '.add_row_action', function() {
                addworkFinanceContactRow(this);
            });
            $( "body" ).on( "click", '.delete_finance_row', function() {
                var Row1 = $(this).parent().parent();
                Row1.remove();
            });
             function addworkFinanceContactRow(rowObject){
		alert('finance Row'+rowObject);
                var Row1 = $(rowObject).parent().parent();
		var rowData = "";
		var FinanceContactCount = $('#financeContactCount').val();
		var cnt = parseInt(financeContactCount) + 1; 
		rowData +='<td style="text-align:left">';
		rowData +='<input type="text" name="customerFinanceContactPerson" id="customerFinanceContactPerson_'+cnt+'" class="textbox_new" value="">';
		rowData +='</td>';
		rowData +='<td style="text-align:left">';
		rowData +='<input type="text" name="customerFinanceContactPhone" id="customerFinanceContactPhone_'+cnt+'" class="textbox_new" value="">';
		rowData +='</td>';
		rowData +='<td  style="text-align:left">';
		rowData +='<input type="text" name="customerFinanceEmail" id="customerFinanceEmail_'+cnt+'" class="required email textbox_new" value="">';
		rowData +='<td align="center" id="adress_actionItems_'+cnt+'">';
		rowData +='<img class="add_row_action" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
 		rowData += '&nbsp;<img class="delete_finance_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" />';              
		rowData +='</td>';
		$(Row1).after('<tr id="finance_contact_row_'+ cnt + '">' + rowData + '</tr>');
                $('#financeContactCount').val(cnt);
            }
            
            $( "body" ).on( "click", '.add_business_row', function() {
                addworkBusinessContactRow(this);
            });
            $( "body" ).on( "click", '.delete_business_row', function() {
                var Row1 = $(this).parent().parent();
                Row1.remove();
            });

            function addworkBusinessContactRow(rowObject){
                    alert('business'+rowObject);
                    console.log('busoness');
		var Row1 = $(rowObject).parent().parent();
		var rowData = "";
		var businessContactCount = $('#businessContactCount').val();
		var cnt = parseInt(businessContactCount) + 1; 
		rowData +='<td style="text-align:left">';
		rowData +='<input type="text" name="customerContactPerson" id="customerContactPerson_'+cnt+'" class="textbox_new" value="">';
		rowData +='</td>';
		rowData +='<td style="text-align:left">';
		rowData +='<input type="text" name="customerContactPhone" id="customerContactPhone_'+cnt+'" class="textbox_new" value="">';
		rowData +='</td>';
		rowData +='<td  style="text-align:left">';
		rowData +='<input type="text" name="customerEmail" id="customerEmail_'+cnt+'" class="required email textbox_new" value="">';
		rowData +='<td align="center" id="adress_actionItems_'+cnt+'">';
		rowData +='<img class="add_business_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
//		rowData += '&nbsp;<a href="javascript:;" onClick="deleteworkBusinessContactRow(' + cnt + ')"><img src="${pageContext.request.contextPath}/css/images/tm_delete.png" /></a>';              
                rowData += '&nbsp;<img class = "delete_business_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png"/>';              
                rowData += '</td>';
	        $(Row1).after('<tr id="business_contact_row_'+ cnt + '">' + rowData + '</tr>');
                $('#businessContactCount').val(cnt);
              }
        });
        
    </script>
</body>