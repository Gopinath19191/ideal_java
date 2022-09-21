<%-- 
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Add Client</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/qpd.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
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
        function addCustomer(){

            document.location.href="addCustomer.htm";
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
        function disableSave(saveButtonId,submitButtonId,backButtonId) {
            if($("#customerName").val() == "") {
                $("#errormessage").html("Please Select Customer Name")
                return false;
            } else if ($("#salesPerson").val() == "") {
                $("#errormessage").html("Please Select BDM")
                return false;
            } else {
                var returnVariable;
                if($("#customerName").val() != "") {
                    customerNameD = $("#customerName").val();
                    $.ajax({
                        url: './getDuplicateCustomerName.htm',
                        type: "POST",
                        async: false,
                        data: ({customerName:customerNameD}),
                        success: function(ajaxObj) {
                            if($.trim(ajaxObj) != "") {
                                $("#errormessage").html("Client Name already exist")
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
                    $.ajax({
                        url: './getDuplicateCustomerName.htm',
                        type: "POST",
                        async: false,
                        data: ({customerName:customerNameD}),
                        success: function(ajaxObj) {
                            if($.trim(ajaxObj) != "") {
                                $("#errormessage").html("Client Name already exist")
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
    </script>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div id="">
            <div class="container_inner" style="margin: 30px 0 24px;">
                <p class="page_heading"><span class="heading">Add Client</span> </p>
            </div>
             <div class="notice_page">
                <div style="float: left"><img src="/ideal_rrf_new/css/images/icon_alert.png" title="Information" alt="Information"></img></div>
                <div style="padding-left: 10px;padding-top: 1px;">
                    <img src="/ideal_rrf_new/css/images/tick.png" title="Information" alt="Information" style="margin-left: 15px;margin-right: 10px;"></img>
                        Fields marked in * are mandatory.
                </div>
            </div>
            <form action="saveCustomer.htm" name="formCustomerDetails" id="formCustomerDetails" method="post">
                <div class="formContent_new" id="datadisplay" style="height:auto">
                    <table id="formTable" border="0" align="center">
                        <tbody>
                            <tr>
                                <!--                            <td align="center" colspan="4" width="100%" height="15">-->
                                <td align="center" colspan="4"  height="15">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label>Client Name<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="text" name="customerName" id="customerName" class="required textbox_new" value="">
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Client Category</label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="customerCategory" id="customerCategory" class="textbox_new">
                                        <option value="">-- Select Category -- </option>
                                        <c:forEach items="${customerCategory}" var="customerCategory" >
                                            <option value="${customerCategory.configKey}" ${selcustomerCategory}> ${customerCategory.configValue}</option>
                                        </c:forEach>
                                    </select>
                                </td>
<!--                                <td style="text-align:left">
                                    <label class="">BDM<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="salesPerson" id="salesPerson" class="required textbox_new ">
                                        <option value="">-- Select BDM -- </option>
                                        <c:forEach items="${salesPerson}" var="salesPerson" >
                                            <option value="${salesPerson.salesManId}">${salesPerson.employeeName} - ${salesPerson.employeeNumber}</option>
                                        </c:forEach>

                                    </select>
                                </td>-->
                            </tr>
                            <tr class="">
<!--                                <td  style="text-align:left">
                                    <label class="">Business Leader</label>
                                </td>
                                <td style="text-align:left">
                                    <select name="businessLeaderName" id="businessLeaderName" class="textbox_new">
                                        <option value="">-- Select Business Leader -- </option>
                                        <c:forEach items="${businessLeader}" var="businessLeader" >
                                            <option value="${businessLeader.salesManId}">${businessLeader.employeeName} - ${businessLeader.employeeNumber}</option>
                                        </c:forEach>

                                    </select>
                                </td>-->
                                <td  style="text-align:left">
                                    <label class="">Contact Person Name</label>
                                </td>
                                <td style="text-align:left">
                                    <input type="text" name="customerContactPerson" id="customerContactPerson" class="textbox_new" value="">
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Contact Person No</label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="text" name="salesPersonContactNo" id="salesPersonContactNo" class="numberDE textbox_new " value="">
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Client Email<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="text" name="customerEmail" id="customerEmail" class="required email textbox_new" value="">
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Client Contact Phone</label>
                                </td>
                                <td style="text-align:left">
                                    <input type="text" name="customerContactPhone" id="customerContactPhone" class="textbox_new" value="">
                                </td>
                            </tr>
<!--                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Client Category</label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="customerCategory" id="customerCategory" class="textbox_new">
                                        <option value="">-- Select Category -- </option>
                                        <c:forEach items="${customerCategory}" var="customerCategory" >
                                            <option value="${customerCategory.configKey}" ${selcustomerCategory}> ${customerCategory.configValue}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Client Contact Phone</label>
                                </td>
                                <td style="text-align:left">
                                    <input type="text" name="customerContactPhone" id="customerContactPhone" class="textbox_new" value="">
                                </td>
                            </tr>-->
                            <tr class="">
<!--                                <td  style="text-align:left">
                                    <label class="">Currency<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="currency" id="currency" class="required textbox_new">
                                        <option value="">-- Select Currency -- </option>
                                        <c:forEach items="${currencyList}" var="currencyList" >
                                            <option value="${currencyList.currencyId}">${currencyList.currencyCode}</option>
                                        </c:forEach>

                                    </select>
                                </td>-->
                                <td  style="text-align:left">
                                    <label class="">Client URL</label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="text" name="customerURL" id="customerURL" class="url textbox_new" value="">
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Tax Registration No</label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="text" name="stcNo" id="stcNo" class="textbox_new" value="">
                                </td>
                            </tr>
<!--                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Country<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="country" id="country" class=" required textbox_new" onchange="getRegions(this.value)">
                                        <option value="">-- Select Country -- </option>
                                        <c:forEach items="${countryList}" var="countryList" >
                                            <option value="${countryList.countryID}">${countryList.countryName}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Region</label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="region" id="region" class="textbox_new" onchange="getCities(this.value)">
                                        <option value="">-- Select Region -- </option>
                                        <%--  <c:forEach items="${regionList}" var="regionList" >
                                            <option value="${regionList.regionID}">${regionList.regionName}</option>
                                        </c:forEach>  --%>
                                    </select>
                                </td>
                            </tr>-->
                            <tr class="">
<!--                                <td  style="text-align:left">
                                    <label class="">City</label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="city" id="city" class="textbox_new">
                                        <option value="">-- Select City -- </option>
                                    </select>
                                </td>-->
<!--                                <td  style="text-align:left">
                                    <label class="">Tax Registration No</label>
                                </td>
                                <td  style="text-align:left">
                                    <input type="text" name="stcNo" id="stcNo" class="textbox_new" value="">
                                </td>-->
                            </tr>
<!--                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">RBU<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="RBU" id="RBU" class="required textbox_new"  onchange="getSubRBU(this.value)">
                                        <option value="">-- Select RBU -- </option>
                                        <c:forEach items="${rbuList}" var="rbuList" >
                                            <option value="${rbuList.rbuID}">${rbuList.rbuName}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">Sub RBU<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <select name="subRBU" id="subRBU" class="required textbox_new">
                                        <option value="">-- Select Sub RBU -- </option>
                                    </select>
                                </td>
                            </tr>
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">SBU</label>
                                </td>
                                <td style="text-align:left">
                                    <select name="SBU" id="SBU" class="textbox_new">
                                        <option value="">-- Select SBU -- </option>
                                        <c:forEach items="${sbuList}" var="sbuList" >
                                            <option value="${sbuList.sbuID}">${sbuList.sbuName}</option>
                                        </c:forEach>

                                    </select>
                                </td>
                                <td width="25%" style="text-align:left"></td>
                                <td width="25%" style="text-align:left"></td>
                            </tr>-->
                            <!--                            <tr class="even">
                                                            <td width="25%" style="text-align:left">
                                                                <label class="headLabel">Terms of Payment</label>
                                                            </td>
                                                            <td width="25%" style="text-align:left">
                                                                <input type="text" name="termsOfPayment" id="termsOfPayment" class="formInputField" value="">
                                                            </td>
                                                            <td width="25%" style="text-align:left">
                                                                <label class="headLabel">Data Area Id</label>
                                                            </td>
                                                            <td width="25%" style="text-align:left">
                                                                <input type="text" name="dataAreaId" id="dataAreaId" class="formInputField" value="">
                                                            </td>
                                                        </tr>-->
                            <tr class="">
                                <td  style="text-align:left">
                                    <label class="">Client Address<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <textarea cols="20" rows="2" style="height: 55px;width: 158px;" name="customerAddress" id="customerAddress"  maxlength="500"  minlength="10" onblur="textLimit(this,500);" onkeyup="textLimit(this,500)" class=" required minlength maxlength resizableTextArea ui-resizable textbox_new"></textarea>
                                </td>
                                <td  style="text-align:left">
                                    <label class="">About Client<font color="red">*</font></label>
                                </td>
                                <td  style="text-align:left">
                                    <textarea cols="20" rows="2" name="aboutCustomer" style="height: 55px;width: 158px;" id="aboutCustomer"  maxlength="500"  minlength="10" onblur="textLimit(this,500);" onkeyup="textLimit(this,500)" class=" required minlength maxlength resizableTextArea ui-resizable textbox_new"></textarea>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttonAlignment">
                    <div class="buttonAlignment" id="btnGroup">
                        <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}" />
                        <input type="hidden" name="customerID" id="customerID" value="" />
                        <input type="submit" name="customerSaveButton" id="saveBtn" value="Save" style="width: 90px;" class="qualitysave" onclick="return disableSave('saveBtn','submitBtn','btnCancel');">
                        <input type="submit" name="customerSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" style="width: 90px;" onclick="return disableSubmit('saveBtn','submitBtn','btnCancel');">
                        <input type="button" name="btnCancel" id="btnCancel" value="Back" class="qualityback" onclick="javascript: location.href='authenticate.htm?empId=${employee_no}&modId=73'">
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
    <style type="text/css">
        .even {
            background-color: #FFFFFF;
        }
    </style>
</body>