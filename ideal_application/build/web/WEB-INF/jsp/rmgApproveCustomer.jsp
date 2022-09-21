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
        #datadisplay1 table {
            none repeat scroll 0 0 #DFE8F6
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
        function disableApprove(saveButtonId,submitButtonId,backButtonId) {
            $("#"+approveBtn).hide();
            $("#"+backButtonId).hide();
            return true;
        }
        function disableReject(saveButtonId,submitButtonId,backButtonId) {
            if($("#remarks").val() == "") {
                $("#errormessage").html("Provide Remarks for Rejection")
                return false;
                return false;
            } else {
                $("#"+saveButtonId).hide();
                $("#"+submitButtonId).hide();
                $("#"+backButtonId).hide();
                return true;
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
                <p class="page_heading"><span class="heading">Modify Customer's Group</span> </p>
            </div>

            <form action="rmgApproveCustomerProcess.htm" name="formUserDetails" id="formUserDetails" method="post">
                <div class="formContent_new" id="datadisplay" style="height:auto">
                    <table  id="formTable" border="0" align="center">
                        <tbody>
                            <tr>
                                <td align="center" colspan="4"  height="15">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <!--                            <tr class="odd">
                                                            <td width="25%" style="text-align:left">
                                                                <label class="headLabel">Create Customer Group</label>
                                                            </td>
                                                            <td width="25%" style="text-align:left">
                            <c:set var="selectedYesValue" value=""></c:set>
                            <c:set var="selectedNoValue" value=""></c:set>
                            <c:if test="${selectedCustomerData[0].customerGroup=='y'}">
                                <c:set var="selectedYesValue" value="checked"></c:set>
                            </c:if>
                            <c:if test="${selectedCustomerData[0].customerGroup=='n'}">
                                <c:set var="selectedNoValue" value="checked"></c:set>
                            </c:if>
                            <input disabled type="radio" name="customerGroup" ${selectedYesValue} id="customerGroupY" class="formInputField" value="y" onclick="hideCustomerGroupName();"><label style="float:left;width: 25px;">Yes</label>
                            <input disabled type="radio" name="customerGroup" ${selectedNoValue} id="customerGroupN" class="formInputField" value="n" onclick="hideCustomerGroupName();"><label style="float:left">No</label>
                        </td>
                        <td width="25%" style="text-align:left">
                            <label class="headLabel">Customer Group</label>
                        </td>
                        <td width="25%" style="text-align:left">
                            <select disabled name="customerGroupName" id="customerGroupName" class="formInputField">
                                <option value="">-- Select Customer Group -- </option>
                            <%-- <c:forEach items="${customerGroup}" var="customerGroupName" >
                                <c:set var="selcustomerGroupName" value=""></c:set>
                                <c:if test="${selectedCustomerData[0].customerGroupName== customerGroupName.customerID}">
                                    <c:set var="selcustomerGroupName" value="selected"></c:set>
                                </c:if>
                                <option value="${customerGroupName.customerID}" ${selcustomerGroupName}>${customerGroupName.customerGroupName}</option>
                            </c:forEach> --%>

                        </select>
                    </td>
                </tr>-->
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Customer Name</label>
                                </td>
                                <td  style="text-align:left">
                                    <input readonly type="text" name="customerName" id="customerName" class="textbox_new" value="${selectedCustomerData[0].customerName}">
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Customer Owner</label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="hidden" name="salesPersonId" id="salesPersonId" value="${selectedCustomerData[0].salesPerson}" />
                                    <select disabled name="salesPerson" id="salesPerson" class="textbox_new">
                                        <option value="">-- Select Customer Owner -- </option>
                                        <c:forEach items="${salesPerson}" var="salesPerson" >
                                            <c:set var="selsalesPerson" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].salesPerson== salesPerson.salesManId}">
                                                <c:set var="selsalesPerson" value="selected"></c:set>
                                            </c:if>
                                            <option value="${salesPerson.salesManId}" ${selsalesPerson}>${salesPerson.employeeNumber} - ${salesPerson.employeeName}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Business Leader</label>
                                </td>
                                <td  style="text-align:left">
                                    <select disabled name="businessLeaderName" id="businessLeaderName" class="textbox_new">
                                        <option value="">-- Select Business Leader -- </option>
                                        <c:forEach items="${businessLeader}" var="businessLeader" >
                                            <c:set var="selbusinessLeaderName" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].businessLeaderName == businessLeader.salesManId}">
                                                <c:set var="selbusinessLeaderName" value="selected"></c:set>
                                            </c:if>
                                            <option value="${businessLeader.salesManId}" ${selbusinessLeaderName}> ${businessLeader.employeeNumber} - ${businessLeader.employeeName}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Sales Person Contact No</label>
                                </td>
                                <td  style="text-align:left">
                                    <input disabled type="text" name="salesPersonContactNo" id="salesPersonContactNo" class="textbox_new" value="${selectedCustomerData[0].salesPersonContactNo}">
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Customer Email</label>
                                </td>
                                <td  style="text-align:left">
                                    <input disabled type="text" name="customerEmail" id="customerEmail" class="textbox_new" value="${selectedCustomerData[0].customerEmail}">
                                </td>
                                <td style="text-align:left">
                                    <label class="">Customer Contact Person</label>
                                </td>
                                <td  style="text-align:left">
                                    <input disabled type="text" name="customerContactPerson" id="customerContactPerson" class="textbox_new" value="${selectedCustomerData[0].customerContactPerson}">
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="headLabel">Customer Category</label>
                                </td>
                                <td  style="text-align:left">
                                    <select disabled name="customerCategory" id="customerCategory" class="textbox_new">
                                        <option value="">-- Select Category -- </option>
                                        <c:forEach items="${customerCategory}" var="customerCategory" >
                                            <c:set var="selcustomerCategory" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].customerCategory == customerCategory.configKey}">
                                                <c:set var="selcustomerCategory" value="selected"></c:set>
                                            </c:if>
                                            <option value="${customerCategory.configKey}" ${selcustomerCategory}> ${customerCategory.configValue}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Customer Contact Phone</label>
                                </td>
                                <td  style="text-align:left">
                                    <input disabled type="text" name="customerContactPhone" id="customerContactPhone" class="textbox_new" value="${selectedCustomerData[0].customerContactPhone}">
                                </td>
                            </tr>
                            <tr >
                                <td  style="text-align:left">
                                    <label class="">Currency</label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="hidden" name="currencyId" id="currencyId" value="${selectedCustomerData[0].currency}" />
                                    <select disabled name="currency" id="currency" class="textbox_new">
                                        <option value="">-- Select Currency -- </option>
                                        <c:forEach items="${currencyList}" var="currencyList" >
                                            <c:set var="selcurrency" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].currency== currencyList.currencyId}">
                                                <c:set var="selcurrency" value="selected"></c:set>
                                            </c:if>
                                            <option value="${currencyList.currencyId}" ${selcurrency}>${currencyList.currencyCode}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Customer URL</label>
                                </td>
                                <td  style="text-align:left">
                                    <input disabled type="text" name="customerURL" id="customerURL" class="textbox_new" value="${selectedCustomerData[0].customerURL}">
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Country</label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="hidden" name="countryHidId" id="countryHidId" value="${selectedCustomerData[0].country}" />
                                    <select disabled name="country" id="country" class="textbox_new" onchange="getRegions(this.value)">
                                        <option value="">-- Select Country -- </option>
                                        <c:forEach items="${countryList}" var="countryList" >
                                            <c:set var="selcountry" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].country== countryList.countryID}">
                                                <c:set var="selcountry" value="selected"></c:set>
                                            </c:if>
                                            <option value="${countryList.countryID}" ${selcountry}>${countryList.countryName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Region</label>
                                </td>
                                <td  style="text-align:left">
                                    <select disabled name="region" id="region" class="textbox_new" onchange="getCities(this.value)">
                                        <option value="">-- Select Region -- </option>
                                        <c:forEach items="${regionList}" var="regionList" >
                                            <c:set var="selregion" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].region== regionList.regionID}">
                                                <c:set var="selregion" value="selected"></c:set>
                                            </c:if>
                                            <option value="${regionList.regionID}" ${selregion}>${regionList.regionName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr class="">
                                <td w style="text-align:left">
                                    <label class="">City</label>
                                </td>
                                <td  style="text-align:left">
                                    <select disabled name="city" id="city" class="textbox_new">
                                        <option value="">-- Select City -- </option>
                                        <c:forEach items="${cityList}" var="cityList" >
                                            <c:set var="selcity" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].city== cityList.cityID}">
                                                <c:set var="selcity" value="selected"></c:set>
                                            </c:if>
                                            <option value="${cityList.cityID}" ${selcity}>${cityList.cityName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Tax Registration No</label>
                                </td>
                                <td  style="text-align:left">
                                    <input disabled type="text" name="stcNo" id="stcNo" class="textbox_new" value="${selectedCustomerData[0].stcNo}">
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">RBU</label>
                                </td>
                                <td  style="text-align:left">
                                    <select disabled name="RBU" id="RBU" class="textbox_new">
                                        <option value="">-- Select RBU -- </option>
                                        <c:forEach items="${rbuList}" var="rbuList" >
                                            <c:set var="selRBU" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].RBU == rbuList.rbuID}">
                                                <c:set var="selRBU" value="selected"></c:set>
                                            </c:if>
                                            <option value="${rbuList.rbuID}" ${selRBU}>${rbuList.rbuName}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Sub RBU</label>
                                </td>
                                <td  style="text-align:left">
                                    <select disabled name="subRBU" id="subRBU" class="textbox_new">
                                        <option value="">-- Select Sub RBU -- </option>
                                        <c:forEach items="${subRBUList}" var="subRBUList" >
                                            <c:set var="selSubRBU" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].subRBU == subRBUList.rbuID}">
                                                <c:set var="selSubRBU" value="selected"></c:set>
                                            </c:if>
                                            <option value="${subRBUList.rbuID}" ${selSubRBU}>${subRBUList.rbuName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr class="">
                                <td style="text-align:left">
                                    <label class="">SBU</label>
                                </td>
                                <td style="text-align:left">
                                    <select disabled name="SBU" id="SBU" class="textbox_new">
                                        <option value="">-- Select SBU -- </option>
                                        <c:forEach items="${sbuList}" var="sbuList" >
                                            <c:set var="selRBU" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].SBU== sbuList.sbuID}">
                                                <c:set var="selRBU" value="selected"></c:set>
                                            </c:if>
                                            <option value="${sbuList.sbuID}" ${selRBU}>${sbuList.sbuName}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                                <td  style="text-align:left"></td>
                                <td  style="text-align:left"></td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Customer Address</label>
                                </td>
                                <td  style="text-align:left">
                                    <textarea disabled cols="20" rows="2" style=" width: 158px;height: 55px;" name="customerAddress" id="customerAddress"  maxlength="500"  minlength="10" onblur="textLimit(this,500);" onkeyup="textLimit(this,100)" class="textbox_new required address minlength maxlength resizableTextArea ui-resizable">${selectedCustomerData[0].customerAddress}</textarea>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">About Customer</label>
                                </td>
                                <td  style="text-align:left">
                                    <textarea disabled cols="20" rows="2" style=" width: 158px;height: 55px;" name="aboutCustomer" id="aboutCustomer"  maxlength="500"  minlength="10" onblur="textLimit(this,500);" onkeyup="textLimit(this,100)" class="textbox_new required address minlength maxlength resizableTextArea ui-resizable">${selectedCustomerData[0].aboutCustomer}</textarea>
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Invoice Code</label>
                                </td>
                                <td  style="text-align:left">
                                    <c:set var="disableCustomerInvoiceCode" value=""></c:set>
                                    <c:if test="${fn:trim(selectedCustomerData[0].customerCode) != ''}">
                                        <c:set var="disableCustomerInvoiceCode" value="disabled"></c:set>
                                        <input type="hidden" name="customerInvoiceCode" id="customerInvoiceCode" value="${selectedCustomerData[0].customerInvoiceCode}" />
                                    </c:if>
                                    <select disabled ${disableCustomerInvoiceCode} name="customerInvoiceCode" id="customerInvoiceCode" class="textbox_new">
                                        <option value="">-- Select Invoice Code -- </option>
                                        <c:forEach items="${invoiceCodeList}" var="invoiceCodeList" >
                                            <c:set var="selcustomerInvoiceCode" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].customerInvoiceCode == invoiceCodeList.invoiceID}">
                                                <c:set var="selcustomerInvoiceCode" value="selected"></c:set>
                                            </c:if>
                                            <option value="${invoiceCodeList.invoiceID}" ${selcustomerInvoiceCode}>${invoiceCodeList.invoiceCode}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                                <td style="text-align:left">
                                    <label class="">Customer Group</label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="customerGroupName" id="customerGroupName" class="textbox_new">
                                        <option value="">-- Select Customer Group -- </option>
                                        <c:forEach items="${customerGroup}" var="customerGroupName" >
                                            <c:set var="selcustomerGroupName" value=""></c:set>
                                            <c:if test="${selectedCustomerData[0].customerGroupName== customerGroupName.customerID}">
                                                <c:set var="selcustomerGroupName" value="selected"></c:set>
                                            </c:if>
                                            <option value="${customerGroupName.customerID}" ${selcustomerGroupName}>${customerGroupName.customerGroupName}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Remarks</label>
                                </td>
                                <td  style="text-align:left">
                                    <textarea disabled cols="20" rows="2" style=" width: 158px;height: 55px;" name="remarks" id="remarks"  maxlength="500"  minlength="10" onblur="textLimit(this,500);" onkeyup="textLimit(this,100)" class="textbox_new required address minlength maxlength resizableTextArea ui-resizable">${selectedCustomerData[0].remarks}</textarea>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Terms of Payment</label>
                                </td>
                                <td  style="text-align:left">
                                    <input disabled type="text" name="termsOfPayment" id="termsOfPayment" class="numberDE required textbox_new" value="${selectedCustomerData[0].termsOfPayment}">Days
                                </td>

                            </tr>
                        </tbody>
                    </table>
                </div>            
                <div class="buttonAlignment">
                    <div class="buttonAlignment" id="btnGroup">
                        <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}" />
                        <input type="hidden" name="customerID" id="customerID" value="${selectedCustomerData[0].custID}" />
                        <input type="hidden" name="customerCode" id="customerCode" value="${selectedCustomerData[0].customerCode}" />
                        <input type="hidden" name="status" id="status" value="${selectedCustomerData[0].status}" />
                        <input type="submit" name="customerApproveButton" id="approveBtn" style="width: 90px;" value="Submit" class="qualitysubmit" onclick="return disableApprove('approveBtn','rejectBtn','btnCancel');">
                        <input type="button" name="btnCancel" id="btnCancel" value="Back" class="qualityback" onclick="javascript: location.href='authenticate.htm?empId=${employee_no}&modId=602'">
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
    </script>
</body>