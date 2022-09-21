<%-- 
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Add Customer</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
<!--    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.9.1.js"></script>-->
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/qpd.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/c/jquery-ui-1.8.16.custom.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.16.custom.css" type="text/css"/>--%>

    <style type="text/css">
        #menu
        {
            padding:0px;
            width: 935px;
        }
        #menu ul
        {
            margin:20px 0 0 20px;
            padding:0;
            border-bottom: 1px solid grey;
        }
        #menu ul li{
            width:auto;
            margin:0px;
            display:inline-block;
            font-size:16px;
            cursor:pointer;
            padding:10px 16px;
            text-align:center;
            text-decoration:none;
            border: 1px solid grey;
            border-bottom: 0px;
            border-radius: 5px 5px 0px 0px;
        }
        #menu ul li.active{
            background: url(../ideal_application/css/images/bg_tab.jpg) repeat-x top;
            background-size: 100% 100%;
            border-bottom-color: #FFF;
            font-weight: bold;
            border-bottom: 1px solid white;
            position: relative;
            top: 1px;
            padding: 11px 16px !important;
        }
        #menu ul li:hover
        {
            font-weight: bold;
        }
        #menu ul li a
        {
            /*                    padding: 5px;
                        font-size: 12px;
                        font-family: arial;
                        font-weight: bold;
                        color: #4786c2;
                        height: 20px;
                        cursor: pointer;
                        float: left;
                        border: 1px solid #bccfea;
                        text-align: center;
                        margin: 0 2px -1px 0;
                        position: relative;*/
        }

        #loadingDiv img
        {
            border: none; 
        }

        #loadingDiv
        {
            opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1;
        }

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
            /*            none repeat scroll 0 0 #DFE8F6*/
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
        fieldset
        {
            border:solid 1px #0F68B3;
            margin:10px;
            border-radius:5px;
            padding:10px;
        }
        fieldset legend {
            background: #fff;
            color: #555555;
            font-size: 14px;
            margin: 0px 0px 4px 0px;
        }
        .customer_billing_address td,.customer_work_address td,.business_contact td,.finance_contact td,.dunning_contact td{
            padding:0px;
            width:auto;
        }
        .customer_billing_address tr,.customer_work_address tr,.business_contact tr,.finance_contact tr,.dunning_contact tr{
            padding:0px;
            width:auto;
        }
        .customer_billing_address th,.customer_work_address th,.business_contact th,.finance_contact th,.dunning_contact th{
            padding:0px;
            width:auto;
        }
        .customer_billing_address td input[type='text'],.customer_work_address td input[type='text'],.business_contact td input[type='text'],.finance_contact td input[type='text'],.dunning_contact td input[type='text']
        {
            width:95%;
            height:20px;
        }
        .invoice_contact tr{
            padding:0px;
            width:100%;
        }
        .custAddLabel
        {
            margin: 0px 15px;
            width:165px;
        }
        input#attachmentValue {
            width: 200px;
            border: none;
            background: none;
        }
        #customerBasicDetails{
            border:1px solid grey;
            margin:0px 15px 15px 20px;
            border-top:0px;
        }
        .customerDetails{
            margin: 30px auto;
            width: 95%;
            background-color:#F0F8FF;
            border:1px solid #ccc;

        }
        #customerTable{
            width:900px;
        }
        #customerTable tbody tr td{
            width:200px;
        }
        #buttonContinue{
            text-align: right;
        }
        .saveBtn,.submitBtn,.btnCancel{
            text-align:center;
        }
        #customerTable tbody tr td label{
            width:190px;
            padding:5px;
        }
        #aboutCustomer{
            width:221px;
            padding: 2px 2px 1px 3px;
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
        .workLocationFieldset,
        .businessContactFieldset,
        .financeContactFieldset,
        .dunningContactFieldset,
        .billingAddressFieldset{
            margin: 12px 0px 0px 0px;
            padding: 0px;
            border: 0px;
        }
        table.customer_work_address,
        table.customer_billing_address,
        table.business_contact,
        table.finance_contact,
        table.dunning_contact{
            width:100%;
        }
        table.customer_work_address tbody tr td,
        .customer_billing_address tbody tr td{
            width:16%;
        }
        .business_contact tbody tr th,
        .finance_contact tbody tr th,
        .dunning_contact tbody tr th{
            width:24%;
        }
        table.customer_work_address tbody tr td:last-child,
        .customer_billing_address tbody tr td:last-child{

            width:3%;
        }
        .business_contact tbody tr th:last-child,
        .finance_contact tbody tr th:last-child,
        .dunning_contact tbody tr th:last-child{
            width:3%;
        }
        .errorMessage{
            color:red;
            font-size:10px;
            margin: 1px;
            display:none;
        }
        form label{
            float:none;
            display: inline-block;
        }
        .even {
            background-color: #FFFFFF;
        }
        .listView{
            background: url("css/images/icon_list.png") no-repeat scroll left center transparent;
            color: #4D85B8;
            float: right;
            height: 20px;
            padding: 6px 0 0 21px;
            text-decoration: none;
        }
        #listName{
            font-weight:bold; 
            float:right; 
            margin: -30px -22px 0px 0px;
            text-decoration: none;
            color: #666666;
            cursor: pointer;
        }
        #listIcon{
            font-weight:bold; 
            float:right; 
            margin: -33px 65px 0px 0px;
            width: 12px;
        }
        .addDivsion,.plainButton{
            background:#316CA8;
            border: 1px solid #4492BF;
            color: #FFFFFF;
            font-weight: bold;
            height: 30px;
            margin: 0 0 0 15px;
            padding: 0 10px;
            width: auto;
            float: right;
            cursor:pointer;
        }
        .financeContact{
            padding-bottom: 15px;
        }
        table.billingAddress > tbody >tr >td > .textbox_new,table.billingAddress > tbody >tr >td > .textbox_new{
            width:95%;
        }
        table.customer_work_address tbody tr td:nth-last-child(2),
        table.customer_work_address tbody tr th:nth-last-child(2){
            width:6%;
        }
        table.customer_work_address tbody tr td:nth-last-child(3),
        table.customer_work_address tbody tr th:nth-last-child(3){
            width:10%;
        }
        #datadisplay table tr td{
            vertical-align: middle;
        }
        .customerDetails table
        {
            border-collapse: collapse;
            border-spacing: 0px;
        }
        .plainButton{
            float: none;
            width: auto;
            display: inline-block;
            padding:10px 10px 0px 10px;
            line-height: initial;
            border-radius: 5px;
            text-align: center;
            margin: 10px;
            font-weight:normal;
            height: 25px;
        }
        select {
            padding: 3px 0px 2px 0px;
        }
        .errorText, .errorTextWorkHrs,.errorNameText, .errorText1,.errorTextGst{ 
            color: red;
            margin: 1px 1px 1px 50px;
            display: none;
        }
        #errShortCode, #errShortCode1
        {
            color: red;
            margin: 1px 1px 1px 50px;   
            display: none;
        }
        .copyAddress
        {
            width: 15px;
            height: 15px;
            vertical-align: middle;
        }
        .billAddOptions{
            display: none;
        }
        .billAddOptions option
        {
           width: 200px;
        }
        #userManualIcon{
            width: 15px;
            margin: 10px 0px 0px 600px;
        }
        
    </style>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; ">
        <div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center">
            <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
        </div>
    </div>
    <div id="container">
        <div class="container_inner" style="margin: 15px 0px;width:300px;">
            <p class="page_heading"><span class="heading">Add Customer</span> </p>
        </div>
        <div>
            <img src="/iDeal_application/css/images/icon_list.png" title="Information" alt="Information" id="listIcon"></img>
            <p class="divisoin_list">
<!--                        <a id="listName" onclick="javascript: location.href='authenticate.htm?empId=${employee_no}&modId=73'">Customer List</a>-->
                <a id="listName" onclick="javascript: location.href = 'customersList.htm'">Customer List</a>
            </p>
        </div>
        <div class="notice_page">
            <img src="/iDeal_application/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon"></img>
            <img src="/iDeal_application/css/images/tick.png" title="Information" alt="Information" id="tickIcon"></img>
            <span class="InfoText">Fields marked in <span style="color: red;" for="fine">*</span> are mandatory.</span>
            <!--<img src="/iDeal_application/css/images/usermanual2.png" title="Information" alt="Information" id="userManualIcon"/><a href="userManualDownload.htm"  target="_blank" style="text-decoration:none;color: #4C83B3; font-size: 13px; font-weight:bold">  User Manual</a>-->
        </div>
        <c:if test="${customAdd == 0}">
            <p style="color:red; font-weight: bold;">Customer Details Not Saved</p>
        </c:if>
            <form action="saveCustomer.htm" name="formCustomerDetails" id="formCustomerDetails" method="post" enctype="multipart/form-data" autocomplete="off">
            <div class="formContent_new" id="datadisplay" style="height:auto">
                <div class="content_page" >
                    <table id="customerTable" border="0" align="center">
                        <tr>
                            <td><label>Customer Name<font color="red">*</font></label></td>
                            <td><input type="text" name="customerName" id="customerName" class="required textbox_new" value="">
                                <p id="nameerrormessage" class="errorMessage">Field should not be null</p>
                                <p id="dupError"></p>
                            </td>
                            <td><label class="">BDM/SalesPerson Name<font color="red">*</font></label></td>
                            <td>
                                <c:choose>
                                    <c:when test="${fn:length(salesPerson)>1}">
                                        <select name="salesPerson" id="salesPerson" class="required textbox_new " onchange="getSalesPersonPhoneNumber(this.value);">
                                            <option value="">-- Select BDM -- </option>
                                            <c:forEach items="${salesPerson}" var="salesPerson" >
                                                <option value="${salesPerson.salesManId}">${salesPerson.employeeName} - ${salesPerson.employeeNumber}</option>
                                            </c:forEach>
                                        </select>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="salesPerson" type="hidden" id="salesPerson" value="${salesPerson.get(0).salesManId}" class="required textbox_new "/>
                                        <input name="salesPersonName" id="salesPersonName" readonly value="${salesPerson.get(0).employeeName} - ${salesPerson.get(0).employeeNumber}" class="required textbox_new "/>
                                    </c:otherwise>
                                </c:choose>
                                <p id="salesNameerrormessage" class="errorMessage">Please select sales Person name</p>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="">About Customer<font color="red">*</font></label></td>
                            <td><textarea cols="20" rows="2" name="aboutCustomer"  id="aboutCustomer"  maxlength="500"  minlength="10" onblur="textLimit(this, 500);" onkeyup="textLimit(this, 500)" class=" required minlength maxlength resizableTextArea ui-resizable textbox_new" style="max-width: 221px;"></textarea>
                                <p id="AbtCusterrormessage" class="errorMessage">Please enter About customer </p>
                            </td>
                            <td><label class="">BDM/Sales Person Contact No<font color="red">*</font></label></td>
                            <td>
                                <input type="text" name="salesPersonContactNo" id="salesPersonContactNo" class="numberDE textbox_new ">
                                <p id="SalesContNoerrormessage" class="errorMessage">Please enter numerics only</p>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="">Customer URL</label></td>
                            <td><input type="text" name="customerURL" id="customerURL" class="url textbox_new" value="" placeholder="http://www.hindujatech.com">
                                <p id="custURLerrormessage" class="errorMessage">Please enter valid URL</p>
                            </td>
                            <td><label class="">Attachment type<font color="red">*</font></label></td>
                            <td>
                                <select name="attachmentType" id="attachmentType" class="textbox_new">
                                    <option value="">-- Select Attachment Type -- </option>
                                    <c:forEach items="${attachmentType}" var="attachmentType" >
                                        <option value="${attachmentType.configKey}" ${selattachmentType}> ${attachmentType.configKey}</option>
                                    </c:forEach>
                                </select>
                                <p id="attachTypeerrormessage" class="errorMessage">Please select attachment type</p>
                            </td>
                        </tr>
                        <tr>
                            <td><label>TAN(for India based customers)</label></td>                                            
                            <td>
                                <input type="text" name="tanNumber" id="tanNumber" class="required textbox_new" value="">
                                <p id="tanNumbererrormessage" class="errorMessage"></p>

                            </td>
                            <td><label class="">Attachments(NDA/PO/MSA)<font color="red">*</font></label></td>
                            <td><input id="attachmentValue" type="file" name="attachmentValue" value="" width="10" />
                                <p id="attachFileerrormessage" class="errorMessageNone" style="font-size:10px;">Please upload a file type(.pdf/.jpeg/.png/.jpg)</p>
                                <!--<p id="attachFileerrormessage1" class="errorMessage">File size should be less than 3 MB</p>-->
                            </td> 

                        </tr>
                        <tr>
                        </tr>
                    </table>
                    <p style="margin:15px 10px 5px 10px;">    
                        <label style="width: auto; margin: 2px 107px 0px 20px;">Division(if any)</label>
                        <input type="checkbox"  style="width:16px;vertical-align:middle;" id="addDivisionName" />
                        <input type="hidden" name="buttonStatus" id="buttonStatus" class="buttonStatus textbox_new" value="" />
                    </p>

                    <p style="width:95%;margin:0px 5px 5px 10px;visibility:hidden;display:none;" class="addDivisionDiv">    
                        <label style="width: auto; margin: 2px 106px 0px 20px;">Division Name<span style="color: red;" for="fine">*</span></label>
                        <input type="text" name="customerDivisionListName"  class="division textbox_new" value="" id="division_1">
                        <input type="button"    class="addDivsion addDivisionBtn" value="Add Division">
                    </p>
                    <div class="customerDetails" id="customerDetails_1" name="customerDetails_1">       
                        <!--                                        <p style="width:95%;margin:0px 5px 5px 10px;display:inline-block;" class="removeDivisionDiv">    
                                                                    <label style="width: auto; margin: 2px 106px 0px 20px;">Division Name<span style="color: red;" for="fine">*</span></label>
                                                                    <input type="text" name="customerDivision"  class="division textbox_new" value="">
                                                                    <input type="button"  class="addDivsion removeDivisionBtn" value="Remove Division">
                                                                </p>-->

                        <div class="billingAddress" id="billingAddressDiv_1" name="billingAddressDiv_1" >
                            <fieldset class="billingAddressFieldset">
                                <legend> Customer Billing Address<font color="red">*</font></legend>
                                <input type="hidden" value="1"  name="businessAddressCount_1"  id="businessAddressCount_1"/>
                                <input type="hidden" value="7"  name="columnCount_1"  id="columnCount_1_1"/>
                                <table class="customer_billing_address" id="customerBillingAddress_1" name="customerBillingAddress_1" border="0" width="50%">
                                    <tr>
                                        <th>Address<span style="color: red;" for="fine">*</span></th>
                                        <th>Short Code<span style="color: red;" for="fine">*</span></th>
                                        <th>Country<span style="color: red;" for="fine">*</span></th>
                                        <th>Region <span style="color: red;" for="fine">*</span></th>
                                        <th>City<span style="color: red;" for="fine">*</span></th>
                                        <th>Pincode<span style="color: red;" for="fine">*</span></th>
                                        <th class="gstHeader">State Code<span style="color: red;" for="fine">*</span></th>
                                        <th class="gstHeader">GSTIN/UID<span style="color: red;" for="fine">*</span></th>
                                        <th>Action</th>
                                    </tr>
                                    <tr  class="business_row" id="business_row_1_1">
                                        <td>
                                            <textarea cols="20" rows="2" name="customerBillingAddressNew_1_1"  id="b_adress_1_1"  maxlength="200"  minlength="10" onblur="textLimit(this, 200)" onkeyup="textLimit(this, 200)" class="required minlength maxlength resizableTextArea ui-resizable textbox_new custBillingAddress" style="width: 221px; max-width: 221px;"></textarea>
                                            <!--                                                        <input type="text" value="" name="customerBillingAddressNew_1_1" id="customerBillingAddressNew_1_1" class="custBillingAddress"/>-->
                                        </td>
                                        <td>
                                            <input type="text" value="" name="customerBillingShortCodeNew_1_1" id="customerBillingShortCode_1_1" class="custBillingShortCode" maxlength="15"/>
                                        </td>
                                        <td>
                                            <select name="customerBillingCountryNew_1_1" class="custBillingCountry required textbox_new country_select" id="b_country_1_1"> 
                                                <option value="">-- Select Country -- </option>
                                                <c:forEach items="${countryList}" var="countryList" >
                                                    <option value="${countryList.countryID}">${countryList.countryName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <select name="customerBillingRegionNew_1_1" id="b_region_1_1" class="custBillingRegion textbox_new region_select">
                                                <option value="">-- Select Region -- </option>
                                            </select>
                                        </td>
                                        <td>
                                            <select name="customerBillingCityNew_1_1" id="b_city_1_1" class="custBillingCity textbox_new">
                                                <option value="">-- Select City -- </option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" name="customerBillingPincodeNew_1_1" id="customerBillingPincodeNew_1_1" class="custBillingPincode"/> 
                                        </td>
                                        <td class="gstRow" id ="gstColumn_1_1">
                                            <input type="text" name="customerBillingGstCodeNew_1_1" id="b_gst_1_1" readonly class="custBillingGstCode" style="display:none"/> 
                                        </td>
                                        <td class="gstRow" id ="gstColumn1_1_1">
                                            <input type="text" name="customerBillingGstNumberNew_1_1" id="customerBillingGstNumberNew_1_1" class="custBillingGstNumber" style="display:none"/> 
                                        </td>
                                        <td align="center"  id="adress_actionItems_1_1" name="adress_actionItems_1_1" >
                                            <img class="add_billing_row" id="add_billing_row_1_1" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </div>
                        <script>
        //Dynamic Copy functionality
                          $(document).ready(function () {
                                 $("body").on("click",".copyAddress",function(){
                                    var customerBTLength = ($(".customer_billing_address tr").length)-1;
    //                              var billAddressID = $(".custBillingAddress").attr("id");
                                    var custBillAdd = $(".custBillingAddress").val();
                                    var custBillShrtCode = $(".custBillingShortCode").val();
                                    var custBillCountry = $(".custBillingCountry").val();
                                    var custBillRegionVal = $(".custBillingRegion").val();
                                    var custBillRegionText = $(".custBillingRegion option:selected").text();
                                    var custBillCityVal = $(".custBillingCity").val();
                                    var custBillCityText = $(".custBillingCity option:selected").text();
                                    var custBillPincode = $(".custBillingPincode").val();
                                 if (customerBTLength === 1)
                                    {
                                    $(".billAddOptions").hide();
                                        if($(this).is(':checked'))
                                        {
                                            $(".customerWorkAddress").text(custBillAdd);
                                            $(".custWorkShortCode").val(custBillShrtCode);
                                            $(".custWorkCountry").val(custBillCountry);
                                            $(".custWorkRegion").attr("selected", "true");
                                            $(".custWorkRegion option").val(custBillRegionVal).text(custBillRegionText);
                                            $(".customerWorkCity").attr("selected", "true");
                                            $(".customerWorkCity option").val(custBillCityVal).text(custBillCityText);
                                            $(".custWorkPinCode").val(custBillPincode);
                                        }
                                }
                                 if (customerBTLength > 1)
                                 {
                                    if($(this).is(':checked')){
                                        $(this).parent().find(".billAddOptions").show();
                                        //$(".billAddOptions").show();
                                        var addBillOptions = '';
                                        var defaultCode = '<option>-Select Address-</option>';
                                        addBillOptions += defaultCode;
                                        $(".custBillingShortCode").each(function(){
                                            var billAddressID = $(this).attr("id");
                                           addBillOptions += ("<option value='"+billAddressID+"'>"+$(this).val()+"</option>"); 
                                        });
                                        $(".billAddOptions").html(addBillOptions);
                                    }
                                    else
                                    {
                                       $(this).parent().find(".billAddOptions").hide();
                                    }
                                }
                                });
                                
                                $("body").on("change" , ".billAddOptions", function(){
                                     var that = this;
                                     var custBillAdd = $(that).val();
                                     $(".custBillingShortCode").each(function(){
                                         var currBillAddId = $(this).attr("id");
                                        if(custBillAdd === currBillAddId) 
                                        {
                                           var rowSelectorId =  $("#"+custBillAdd).parent().parent().attr("id");
                                           var copiedAdd = $("#"+rowSelectorId).children().find(".custBillingAddress").val();
                                           var copiedShortCode = $("#"+rowSelectorId).children().find(".custBillingShortCode").val();
                                           var copiedCountry = $("#"+rowSelectorId).children().find(".custBillingCountry").val();
                                           var copiedRegionVal = $("#"+rowSelectorId).children().find(".custBillingRegion").val();
                                           var copiedRegionText =$("#"+rowSelectorId).children().find(".custBillingRegion option:selected").text();
                                           var copiedCityVal = $("#"+rowSelectorId).children().find(".custBillingCity").val();
                                           var copiedCityText = $("#"+rowSelectorId).children().find(".custBillingCity option:selected").text();
                                           var copiedPincode = $("#"+rowSelectorId).children().find(".custBillingPincode").val();
                                            
                                            var parentCopy = $(that).parent().parent().next();
                                            var parentCopyId = parentCopy.attr("id");
                                            $("#"+parentCopyId).children().find(".customerWorkAddress").text(copiedAdd);
                                            $("#"+parentCopyId).children().find(".custWorkShortCode").val(copiedShortCode);
                                            $("#"+parentCopyId).children().find(".custWorkCountry").val(copiedCountry);
                                            $("#"+parentCopyId).children().find(".custWorkRegion").attr("selected", "true");
                                            $("#"+parentCopyId).children().find(".custWorkRegion option").val(copiedRegionVal).text(copiedRegionText);
                                            $("#"+parentCopyId).children().find(".customerWorkCity").attr("selected", "true");
                                            $("#"+parentCopyId).children().find(".customerWorkCity option").val(copiedCityVal).text(copiedCityText);
                                            $("#"+parentCopyId).children().find(".custWorkPinCode").val(copiedPincode);
                                        }
                                     });
                                });  
                            });   
                        </script>
                        <div class="WorkLocation"  id="WorkLocationDiv_1" name="WorkLocationDiv_1">
                            <fieldset class="workLocationFieldset">
                                <legend> Customer Work Location </legend>            
                                <input type="hidden" value="1" name="workLocationCount_1" id="workLocationCount_1"/>
                                <table class="customer_work_address" border="0" id="customerWorkAddress_1" name="customerWorkAddress_1">
                                    <tr>
                                        <th >Address<span style="color: red;" for="fine">*</span></th>
                                        <th>Short Code<span style="color: red;" for="fine">*</span></th>
                                        <th >Country<span style="color: red;" for="fine">*</span></th>
                                        <th >Region <span style="color: red;" for="fine">*</span></th>
                                        <th >City<span style="color: red;" for="fine">*</span></th>
                                        <th >Pincode<span style="color: red;" for="fine">*</span></th>
                                        <th >Working Hrs/Day<span style="color: red;" for="fine">*</span></th>
                                        <th >Work Location<span style="color: red;" for="fine">*</span></th>
                                        <th >Action</th>
                                    </tr>
                                    <tr class="copyRow">
                                        <td colspan="8"><input type="checkbox" name="copyAddress" class="copyAddress" value="copy"/><span>Customer Work Location same as Customer Billing Address</span>&nbsp;&nbsp;<select class="billAddOptions"><option>-Select Address-</option></select></td>
                                    </tr>
                                    <tr id="workLocationRow_1_1" name="workLocationRow_1_1">
                                        <td>
                                            <textarea cols="20" rows="2" name="customerWorkAddressNew_1_1"  id="w_adress_1_1"  maxlength="200"  minlength="10" onblur="textLimit(this, 500);" onkeyup="textLimit(this, 200)" class=" required minlength maxlength resizableTextArea ui-resizable textbox_new customerWorkAddress" style="width: 221px; max-width: 221px;"></textarea>
                                            <!--                                                        <input type="text" width="150px" value="" name="customerWorkAddressNew_1_1"  id="customerWorkAddressNew_1_1" class="customerWorkAddress" />-->
                                        </td>
                                        <td>
                                            <input type="text" value="" name="customerWorkShortCodeNew_1_1" id="customerWorkShortCode_1_1" class="custWorkShortCode" maxlength="15"/>
                                        </td>
                                        <td>
                                            <select name="customerWorkCountryNew_1_1" id ="w_country_1_1" class="custWorkCountry required textbox_new country_select">
                                                <option value="">-- Select Country -- </option>
                                                <c:forEach items="${countryList}" var="countryList" >
                                                    <option value="${countryList.countryID}">${countryList.countryName}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <select name="customerWorkRegionNew_1_1" id="w_region_1_1" class="custWorkRegion textbox_new region_select"> 
                                                <option value="">-- Select Region -- </option>
                                            </select>
                                        </td>
                                        <td>
                                            <select name="customerWorkCityNew_1_1" id="w_city_1_1" class="customerWorkCity textbox_new">
                                                <option value="">-- Select City -- </option>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" width ="120px" name="customerWorkPincodeNew_1_1" id="customerWorkPincodeNew_1_1" class="custWorkPinCode"/> 
                                        </td>
                                        <td>
                                            <input type="text" name="workingHoursPerDayNew_1_1" id="workingHoursPerDayNew_1_1" class="workingHoursPerDay required textbox_new" value="" placeholder="HH:MM">
                                        </td>
                                        <td>
                                            <select name="workingLocationIsCompanyLocationNew_1_1" id="workingLocationIsCompanyLocationNew_1_1" class="customerWorkLocationDefining textbox_new">
                                                <option value="">--Select Location--</option>
                                                <option value="0">Customer Location</option>
                                                <option value="1">Company Location</option>
                                            </select>
                                        </td>
                                        <td align="center">
                                            <img class="add_location_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </div>
                        <div class="businessContact" id="businessContactDiv_1" name="businessContactDiv_1">
                            <fieldset class="businessContactFieldset">
                                <legend> Business Contact </legend>            
                                <input type="hidden" value="1" name="businessContactCount_1" id="businessContactCount_1"/>
                                <table class="business_contact" border="0" width="50%" id="businessContact_1" name="businessContact_1">
                                    <tr>
                                        <th>Name<span style="color: red;" for="fine">*</span></th>
                                        <th>Designation<span style="color: red;" for="fine">*</span></th>
                                        <th>Phone No.<span style="color: red;" for="fine">*</span></th>
                                        <th>Email Id<span style="color: red;" for="fine">*</span></th>
                                        <th>Action</th>
                                    </tr>
                                    <tr id="businessContactRow_1_1" name="businessContactRow_1_1">
                                        <td>
                                            <input type="text" name="customerContactPersonNew_1_1" id="customerContactPersonNew_1_1" class="custContactPerson textbox_new" value="">
                                        </td>
                                        <td>
                                            <input type="text" name="customerDesignationNew_1_1" id="customerDesignation_1_1" class="custFinContactName textbox_new" value="">
                                        </td>
                                        <td>
                                            <input type="text" name="customerContactPhoneNew_1_1" id="customerContactPhoneNew_1_1" class="custContactPhone textbox_new" value="">
                                        </td>
                                        <td>
                                            <input type="text" name="customerContactEmailNew_1_1" id="customerContactEmailNew_1_1" class="customerEmail required email textbox_new" value="">
                                        </td>
                                        <td align="center">
                                            <img class="add_business_row" id="add_business_row_1_1" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                        </td>
                                    </tr>
                                </table>
                            </fieldset>
                        </div>
                        <div class="financeContact" id="financeContactDiv_1"  name="financeContactDiv_1">
                            <fieldset class="financeContactFieldset">
                                <legend> Finance Contact </legend>            
                                <input type="hidden" value="1" name="financeContactCount_1" id="financeContactCount_1"/>
                                <table class="finance_contact" id="financeContact_1" name="financeContact_1" border="0" width="50%">
                                    <tbody>
                                        <tr>
                                            <th>Name<span style="color: red;" for="fine">*</span></th>
                                            <th>Designation<span style="color: red;" for="fine">*</span></th>
                                            <th>Phone No.<span style="color: red;" for="fine">*</span></th>
                                            <th>Email Id<span style="color: red;" for="fine">*</span></th>
                                            <th>Action</th>
                                        </tr>
                                        <tr id="financeContactRow_1_1" name="financeContactRow_1_1">
                                            <td>
                                                <input type="text" name="customerFinanceContactPersonNew_1_1" id="customerFinanceContactPersonNew_1_1" class="custFinContactName textbox_new" value="">
                                            </td>
                                            <td>
                                                <input type="text" name="customerFinanceDesignationNew_1_1" id="customerFinanceDesignationNew_1_1" class="custFinContactName textbox_new" value="">
                                            </td>
                                            <td>
                                                <input type="text" name="customerFinanceContactPhoneNew_1_1" id="customerFinanceContactPhoneNew_1_1" class="custFinContPhone textbox_new" value="">
                                            </td>
                                            <td>
                                                <input type="text" name="customerFinanceContactEmailNew_1_1" id="customerFinanceContactEmailNew_1_1" class="custFinEmail required email textbox_new" value="">
                                            </td>
                                            <td align="center" class="add_row_action" >
                                                <img class="add_financerow" id="add_financerow_1_1" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </fieldset>
                        </div>
                        <div class="duningContact" id="dunningContactDiv_1"  name="dunningContactDiv_1">
                            <fieldset class="dunningContactFieldset">
                                <legend> Dunning Report Contact </legend>            
                                <input type="hidden" value="1" name="dunningContactCount_1" id="dunningContactCount_1"/>
                                <table class="dunning_contact" id="dunningContact_1" name="dunningContact_1" border="0" width="50%">
                                    <tbody>
                                        <tr>
                                            <th>Name<span style="color: red;" for="fine">*</span></th>
                                            <th>Designation<span style="color: red;" for="fine">*</span></th>
                                            <th>Phone No.<span style="color: red;" for="fine">*</span></th>
                                            <th>Email Id<span style="color: red;" for="fine">*</span></th>
                                            <th>Action</th>
                                        </tr>
                                        <tr id="dunningContactRow_1_1" name="dunningContactRow_1_1">
                                            <td>
                                                <input type="text" name="customerDunningContactPersonNew_1_1" id="customerDunningContactPersonNew_1_1" class="custDunContactName textbox_new" value="">
                                            </td>
                                            <td>
                                                <input type="text" name="customerDunningDesignationNew_1_1" id="customerDunningDesignationNew_1_1" class="custDunContactName textbox_new" value="">
                                            </td>
                                            <td>
                                                <input type="text" name="customerDunningContactPhoneNew_1_1" id="customerDunningContactPhoneNew_1_1" class="custDunContPhone textbox_new" value="">
                                            </td>
                                            <td>
                                                <input type="text" name="customerDunningContactEmailNew_1_1" id="customerDunningContactEmailNew_1_1" class="custDunEmail required email textbox_new" value="">
                                            </td>
                                            <td align="center" class="dunn_row_action" >
                                                <img class="add_dunningrow" id="add_dunningrow_1_1" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </fieldset>
                        </div>
                        <div class="businessContact" id="businessContactDiv_1" name="businessContactDiv_1">
                            <fieldset class="businessContactFieldset">
                                <legend> Invoice Recipient</legend>            
                                <table class="invoice_contact" id="invoice_contact" name="invoice_contact" border="0" width="100%" align="center">
                                    <tbody>
                                        <tr>                                          
                                            <td style="padding-top:10px; width:150px;"><b>Invoice will be Emailed To</b><span style="color: red;" for="fine">*</span> </td>
                                            <td>
                                                <select name="invoiceTo_1" id="invoiceTo_1" class="custInvoice required textbox_new country_select" style="width:150px;">
                                                        <c:forEach items="${invoiceList}" var="invoiceObj" >
                                                             <option value="${invoiceObj.configKey}" ${selectedInvoice}> ${invoiceObj.configValue}</option>
                                                        </c:forEach>
                                                </select> 
                                            </td>                                          
                                        </tr>
                                    </tbody>
                                </table>
                            </fieldset>
                        </div>
                    </div>  
                </div> 
                <span id="errShortCode"></span>
                <span id="errShortCode1"></span>
                <p class="errorText">Please enter email format (e.g) abcdef@hindujatech.com</p>
                <p class="errorTextGst">Please enter GST number for Indian Customers</p>
                <p class="errorText1">Please enter email format (e.g) abcdef@hindujatech.com</p> 
                <p class="errorTextWorkHrs">Please enter working hours in hh:mm format (e.g)08:30 and less than 12:00</p>
                <p class="errorNameText">Please enter Name format (e.g) Mr. Test User(Special characters are not allowed)</p> 
                <p id="invoiceerrormessage" class="errorMessage">Please select invoice recipient type</p>
                <div class="buttonAlignment">
                    <div class="buttonAlignment" id="btnGroup">
                        <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}" />
                        <input type="hidden" name="customerID" id="customerID" value="" />
                        <input type="button" name="btnCancel" id="btnCancel" value="Back" style="cursor:pointer;" class="qualityback" onclick="javascript: location.href = 'authenticate.htm?empId=${employee_no}&modId=73'">
                        <input type="button" name="customerSaveButton" id="saveBtn" value="Save" style="width: 90px; cursor:pointer" class="qualitysave">
                        <!--<input type="submit" name="customerSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" style="width: 90px;" onclick="return disableSubmit('saveBtn','submitBtn','btnCancel');">-->
                        <input type="button" name="customerSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" style="width: 90px; cursor:pointer" >                                            
                    </div>
                </div> 

            </div>
        </form>
    </div>
    <div id="RemoveIndividualDiv" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="RemoveIndividualDivFocus" style="position: absolute;z-index: 150;top: 20%;left:34%;text-align: center;font-size: 14px;background-color: #fff;width: auto;height:auto;padding: 20px;border-radius: 5px;">
            <p style="padding:10px;margin:10px;">Are you sure want to remove the division?<br><br>Unsaved division details will be lost</p><p class="plainButton" id="removeDivYes">YES</p><p class="plainButton" id="removeDivNo">NO</p>
        </div>
    </div>
    <div id="RemoveAllDiv" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="RemoveAllDivFocus" style="position: absolute;z-index: 150;top: 20%;left:34%;text-align: center;font-size: 14px;background-color: #fff;width: auto;height:auto;padding: 20px;border-radius: 5px;">
            <p style="padding:10px;margin:10px;">Are you sure want to remove all the added division?<br><br>Unsaved division details will be lost</p><p class="plainButton" id="removeAllDivYes">YES</p><p class="plainButton" id="removeAllDivNo">NO</p>
        </div>
    </div> 
</body>
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

    function stopLoading() {
        var loaderStopHeight = $("html").height();
        loadingDivObj.height = loaderStopHeight + "px";
        if (ns4) {
            loadingDivObj.visibility = "hidden";
        } else if (ns6 || ie4)
            loadingDivObj.display = "none";
        $("#loadingImage").attr("tabindex", -1).focus();
    }

    function startLoading() {
        var loaderStartHeight = $("html").height();
        loadingDivObj.height = loaderStartHeight + "px";
        if (ns4) {
            loadingDivObj.visibility = "visible";
            $("#loadingImage").attr("tabindex", -1).focus();
        } else if (ns6 || ie4)
            loadingDivObj.display = "block";
        $("#loadingImage").attr("tabindex", -1).focus();
    }
    function getSalesPersonPhoneNumber(selectedValue) {
        $.ajax({
            url: './getSalesPersonPhoneNumber.htm?id=' + selectedValue,
            type: "POST",
            async: false,
            data: ({id: selectedValue}),
            success: function (response) {
                $('#salesPersonContactNo').val(response);
            }
        });
    }
    $(document).ready(function () {
    <%--             <% 
                   response.setHeader("Cache-Control","no-cache");
                    response.setHeader("Cache-Control","no-store");
                    response.setDateHeader("Expires",0);
                    response.setHeader("Pragma","no-cache");
                
                    String UserN = (String)session.getAttribute("plno");
                    if(null == UserN){
                        RequestDispatcher rd = request.getRequestDispatcher("customerList.jsp");
                        rd.include(request,response);
                        out.println("Sesion ended. Please Login");
                    }
                 %> --%>

        if (typeof String.prototype.trim !== 'function') {
            String.prototype.trim = function () {
                return this.replace(/^\s+|\s+$/g, '');
            };
        }

        $("body").on('input', '.custBillingShortCode', function () {
            if ($(this).val().length >= 15) {
                alert('Short code length 1-15 you reached the limit');
            }
        });

        $("body").on('input', '.custWorkShortCode', function () {
            if ($(this).val().length >= 15) {
                alert('Short code length 1-15 you reached the limit');
            }
        });
        $("body").on('click','.isCompanyLocationNewCheck',function(){
            
            checkBoxCheck(this.id,this.value);
        });
        
         function checkBoxCheck(checkID,checkVal){
            var checkVal =0;
            if($("#"+checkID).checked(true)){
                checkval.valueOf("1");
            }
            else{
                checkval.valueOf("0");
            }
            return checkval;
        }
        var salesPersonIDVal = document.getElementById("salesPerson").value;
        getSalesPersonPhoneNumber(salesPersonIDVal);
        var divisionLength = $(".customerDetails").length;
        divisionLength++;
        /***********************************************************************************/
        $('#saveBtn').on('click', function () {
            $('#buttonStatus').val('s');
            saveButtonValidation();
        });
        stopLoading();
        $("body").on("change", '.country_select', function (evt) {
            getRegions(this.value, this.id);
            
        });
        function saveButtonValidation() {

            var savecustNameVal = document.getElementById("customerName").value;
            var savecustNameID = document.getElementById("customerName").id;
            var savecustNameStatus = nullFieldCheck(savecustNameID, savecustNameVal);

            var saveattachmentVal = document.getElementById("attachmentValue").value;
            var saveattachmentID = document.getElementById("attachmentValue").id;
            var saveattachmentStatus = saveAttachmentTypeValidation(saveattachmentID, saveattachmentVal);

            var savesalesPersonContactNoVal = document.getElementById("salesPersonContactNo").value;
            var savesalesPersonContactNoID = document.getElementById("salesPersonContactNo").id;
            var savesalesPersonContactNoStatus = savePhNoCheck(savesalesPersonContactNoID, savesalesPersonContactNoVal);

            document.getElementById("aboutCustomer").style.outline = "none";
            document.getElementById("AbtCusterrormessage").style.display = "none";
            document.getElementById("salesPerson").style.outline = "none";
            document.getElementById("salesNameerrormessage").style.display = "none";
            document.getElementById("attachmentType").style.outline = "none";
            document.getElementById("attachTypeerrormessage").style.display = "none";
//            document.getElementById("invoiceTo").style.outline = "none";
//            document.getElementById("invoiceerrormessage").style.display = "none";
            $(".errorText").css({"display": "none"});
            $(".errorText1").css({"display": "none"});


            savetableNullRemoveCheck("custBillingAddress");
            savetableNullRemoveCheck("custBillingShortCode");
            savetableNullRemoveCheck("custBillingCountry");
            savetableNullRemoveCheck("custBillingRegion");
            savetableNullRemoveCheck("custBillingCity");
            savetableNullRemoveCheck("custInvoice");

            savetableNullRemoveCheck("customerWorkAddress");
            savetableNullRemoveCheck("custWorkShortCode");
            savetableNullRemoveCheck("custWorkCountry");
            savetableNullRemoveCheck("custWorkRegion");
            savetableNullRemoveCheck("customerWorkCity");

            var DivsionStatus = divisionNameCheck("division");
            var custWorkPinCodeStatus = saveTableAlphaNumericCheck("custWorkPinCode");
            var custworkingHoursPerDayStatus = saveTableTimeCheck("workingHoursPerDay");
            var custContactPersonStatus = saveTableAlphabetCheck("custContactPerson");
            var custContactPhoneStatus = saveTableNumericCheck("custContactPhone");
            var customerEmailStatus = saveTableEmailCheck("customerEmail");
            var custFinContPersonStatus = saveTableAlphabetCheck("custFinContactName");
            var custFinContPhoneStatus = saveTableNumericCheck("custFinContPhone");
            var custFinEmailStatus = saveTableEmailCheck("custFinEmail");
            var custBillingPincodeStatus = saveTableAlphaNumericCheck("custBillingPincode");

            if (savecustNameStatus && DivsionStatus && saveattachmentStatus && savesalesPersonContactNoStatus &&
                    custWorkPinCodeStatus && customerEmailStatus && custContactPersonStatus &&
                    custContactPhoneStatus && custFinEmailStatus && custFinContPersonStatus &&
                    custFinContPhoneStatus && custworkingHoursPerDayStatus &&
                    custBillingPincodeStatus) {
                var custName = $("#customerName").val();
                $.ajax({
                    url: "getDuplicateCustomerName.htm?customerName=" + custName,
                    type: "POST",
                    success: function (data) {
                        if (data == 0) {
                            $("#formCustomerDetails").submit();
                            startLoading();
                        } else {
                            $("#dupError").html("<span style='color:red;'><b>Please enter unique customer name.</b></span>");
                            $("#customerName").trigger('focus');
                        }
                    }
                });
            } else {

            }
        }
        function getRegions(selectedValue, objId) {
            var category = objId.substring(0, 1);
            var objIdRowValue = objId.substring(10);
            var id;
            var country_id = selectedValue;
            if(country_id==113){
                $("#b_gst_"+objIdRowValue).css({"display":"table-cell"});
                $("#customerBillingGstNumberNew_"+objIdRowValue).css({"display":"table-cell"});
//                $(".gstRow").css({"visiblity":"initial"});
            }else{
                $("#b_gst_"+objIdRowValue).css({"display":"none"});
                $("#customerBillingGstNumberNew_"+objIdRowValue).css({"display":"none"});
            }
            if (category === 'w') {
                id = "w_region_" + objIdRowValue;
            } else if (category === 'b') {
                id = "b_region_" + objIdRowValue;
            }
            $("#" + id).html("<option value=''>-- Select Region --</option>");
            if (id != "") {
                $.ajax({
                    url: './getRegionList.htm',
                    type: "POST",
                    async: false,
                    data: ({countryId: selectedValue, id: id}),
                    success: function (ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            }
        }
        
        $("body").on("change", '.region_select', function () {
            getCities(this.value, this.id);
            getGstCode(this.value, this.id);
        });
        function getCities(selectedValue, objId) {
            var selectedCountryValue;
            var category = objId.substring(0, 1);
            var objIdRowValue = objId.substring(9);
            var id;
            if (category === 'w') {
                id = "w_city_" + objIdRowValue;
                selectedCountryValue = $("#w_country_" + objIdRowValue).val();
            } else if (category === 'b') {
                id = "b_city_" + objIdRowValue;
                selectedCountryValue = $("#b_country_" + objIdRowValue).val();
            }
            $("#" + id).html("<option value=''>-- Select City --</option>");

            if (id != "" && selectedCountryValue != "") {
                $.ajax({
                    url: './getCityList.htm',
                    type: "POST",
                    async: false,
                    data: ({countryId: selectedCountryValue, regionId: selectedValue, id: id}),
                    success: function (ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            }
        }
        
        function getGstCode(selectedValue, objId) {
            var category = objId.substring(0, 1);
            var objIdRowValue = objId.substring(9);
            var id;
            if (category === 'w') {
                id = "b_gst_" + objIdRowValue;
            } else if (category === 'b') {
                id = "b_gst_" + objIdRowValue;
            }
            
            if (id != "" ) {
                $.ajax({
                    url: './getGstCode.htm',
                    type: "POST",
                    async: false,
                    data: ({regionId: selectedValue}),
                    success: function (ajaxObj) {
                        $("#" + id).val(ajaxObj);
                    }
                });
            }
    
            
        }
        
        $("body").on("click", '.add_row_action', function () {
            addworkFinanceContactRow(this);
        });
        $("body").on("click", '.delete_finance_row', function () {
            var Row1 = $(this).parent().parent();
            Row1.remove();
        });
        function addworkFinanceContactRow(rowObject) {
            var Row1 = $(rowObject).parent().parent();
            var FinanceContactDivId = $(rowObject).parent().parent().parent().attr("id");
            var FinanceContactDivIdNum = FinanceContactDivId.split('_')[1];
            var rowData = "";
            var financeContactCount = $('#financeContactCount_' + FinanceContactDivIdNum).val();
            var cnt = parseInt(financeContactCount) + 1;
            rowData += '<td style="text-align:left">';
            rowData += '<input type="text" name="customerFinanceContactPersonNew_' + FinanceContactDivIdNum + '_' + cnt + '" id="customerFinanceContactPerson_' + FinanceContactDivIdNum + '_' + cnt + '" class="custFinContactName textbox_new" value="">';
            rowData += '</td>';
            rowData += '<td><input type="text" name="customerFinanceDesignationNew_' + FinanceContactDivIdNum + '_' + cnt + '" id="customerFinanceDesignation_' + FinanceContactDivIdNum + '_' + cnt + '" class="custFinContactName textbox_new" value=""></td>';
            rowData += '<td style="text-align:left">';
            rowData += '<input type="text" name="customerFinanceContactPhoneNew_' + FinanceContactDivIdNum + '_' + cnt + '" id="customerFinanceContactPhone_' + FinanceContactDivIdNum + '_' + cnt + '" class="custFinContPhone textbox_new" value="">';
            rowData += '</td>';
            rowData += '<td  style="text-align:left">';
            rowData += '<input type="text" name="customerFinanceContactEmailNew_' + FinanceContactDivIdNum + '_' + cnt + '" id="customerFinanceEmail_' + FinanceContactDivIdNum + '_' + cnt + '" class="custFinEmail required email textbox_new" value="">';
            rowData += '<td align="center" id="adress_actionItems_' + FinanceContactDivIdNum + '_' + cnt + '">';
            rowData += '<img class="add_row_action" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
            rowData += '&nbsp;<img class="delete_finance_row" id="delete_finance_row_1_' + cnt + '" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;"/>';
            rowData += '</td>';
            $(Row1).after('<tr id="financeContactRow_' + FinanceContactDivIdNum + '_' + cnt + '" name="financeContactRow_' + FinanceContactDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
            $('#financeContactCount_' + FinanceContactDivIdNum).val(cnt);
        }
        
        //dunning contact details   
        $("body").on("click", '.dunn_row_action', function () {
            addworkFinanceContactRow(this);
        });
        $("body").on("click", '.delete_dunning_row', function () {
            var Row1 = $(this).parent().parent();
            Row1.remove();
        });
        function addworkFinanceContactRow(rowObject) {
            var Row1 = $(rowObject).parent().parent();
            var DunningContactDivId = $(rowObject).parent().parent().parent().attr("id");
            var DunningContactDivIdNum = DunningContactDivId.split('_')[1];
            var rowData = "";
            var dunningContactCount = $('#dunningContactCount_' + DunningContactDivIdNum).val();
            var cnt = parseInt(dunningContactCount) + 1;
            rowData += '<td style="text-align:left">';
            rowData += '<input type="text" name="customerDunningContactPersonNew_' + DunningContactDivIdNum + '_' + cnt + '" id="customerDunningContactPerson_' + DunningContactDivIdNum + '_' + cnt + '" class="custDunContactName textbox_new" value="">';
            rowData += '</td>';
            rowData += '<td><input type="text" name="customerDunningDesignationNew_' + DunningContactDivIdNum + '_' + cnt + '" id="customerDunningDesignation_' + DunningContactDivIdNum + '_' + cnt + '" class="custDunContactName textbox_new" value=""></td>';
            rowData += '<td style="text-align:left">';
            rowData += '<input type="text" name="customerDunningContactPhoneNew_' + DunningContactDivIdNum + '_' + cnt + '" id="customerDunningContactPhone_' + DunningContactDivIdNum + '_' + cnt + '" class="custDunContPhone textbox_new" value="">';
            rowData += '</td>';
            rowData += '<td  style="text-align:left">';
            rowData += '<input type="text" name="customerDunningContactEmailNew_' + DunningContactDivIdNum + '_' + cnt + '" id="customerDunningEmail_' + DunningContactDivIdNum + '_' + cnt + '" class="custDunEmail required email textbox_new" value="">';
            rowData += '<td align="center" id="adress_actionItems_' + DunningContactDivIdNum + '_' + cnt + '">';
            rowData += '<img class="dun_row_action" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
            rowData += '&nbsp;<img class="delete_dunning_row" id="delete_dunning_row_1_' + cnt + '" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;"/>';
            rowData += '</td>';
            $(Row1).after('<tr id="dunningContactRow_' + DunningContactDivIdNum + '_' + cnt + '" name="dunningContactRow_' + DunningContactDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
            $('#dunningContactCount_' + DunningContactDivIdNum).val(cnt);
        }
        

        $("body").on("click", '.add_billing_row', function () {
            addRow(this);
        });
        $("body").on("click", '.delete_billing_row', function () {
            var BillingRowDelete = $(this).parent().parent();
//                var businessDivId=$(rowObject).parent().parent().parent().parent().attr("id");
//                var businessDivIdNum=businessDivId.split('_')[1];
//                var businessAddressCount = $('#businessAddressCount_'+businessDivIdNum).val();
//                businessAddressCount = parseInt(businessAddressCount) - 1;
            BillingRowDelete.remove();
        });

        function addRow(rowObject) {
            var theRow = $(rowObject).parent().parent();
            var businessDivId = $(rowObject).parent().parent().parent().parent().attr("id");
            var businessDivIdNum = businessDivId.split('_')[1];
            var rowData = "";
            var businessAddressCount = $('#businessAddressCount_' + businessDivIdNum).val();
            var cnt = parseInt(businessAddressCount) + 1;
            rowData += '<td align="center"><textarea type="text" name="customerBillingAddressNew_' + businessDivIdNum + '_' + cnt + '"  id="b_adress_' + businessDivIdNum + '_' + cnt + '" class="custBillingAddress" style="max-width: 221px !important;"></textarea></td>';
            rowData += '<td align="center"><input type="text" name="customerBillingShortCodeNew_' + businessDivIdNum + '_' + cnt + '" id="customerBillingShortCode_' + businessDivIdNum + '_' + cnt + '" class="custBillingShortCode" maxlength="15"></td>';
            rowData += '<td  align="center">';
            rowData += '<select name="customerBillingCountryNew_' + businessDivIdNum + '_' + cnt + '" id="b_country_' + businessDivIdNum + '_' + cnt + '" class="custBillingCountry  required textbox_new country_select" onchange="getRegions(this.value,this.id)">';
            rowData += '<option value="">-- Select Country -- </option>';
            rowData += '<c:forEach items="${countryList}" var="countryList" >';
            rowData += '<option value="${countryList.countryID}">${countryList.countryName}</option>';
            rowData += '</c:forEach>';
            rowData += '</select>';
            rowData += '</td>';
            rowData += '<td  align="center">';
            rowData += '<select name="customerBillingRegionNew_' + businessDivIdNum + '_' + cnt + '"  id="b_region_' + businessDivIdNum + '_' + cnt + '" class="custBillingRegion  textbox_new region_select" onchange="getCities(this.value,this.id);">';
            rowData += '<option value="">-- Select Region -- </option></select></td>';
            rowData += '<td  align="center">';
            rowData += '<select name="customerBillingCityNew_' + businessDivIdNum + '_' + cnt + '" id="b_city_' + businessDivIdNum + '_' + cnt + '" class="custBillingCity textbox_new">';
            rowData += '<option value="">-- Select City -- </option></select></td>';
            rowData += '<td align="center"><input type="text" name="customerBillingPincodeNew_' + businessDivIdNum + '_' + cnt + '" id="b_pincode_' + businessDivIdNum + '_' + cnt + '" class="custBillingPincode"></td>';
            rowData += '<td align="center" class="gstRow" id ="gstColumn_'+businessDivIdNum+'_'+cnt+'"><input type="text" name="customerBillingGstCodeNew_' + businessDivIdNum + '_' + cnt + '" id="b_gst_' + businessDivIdNum + '_' + cnt + '" readonly class="custBillingGstCode" style="display:none"/></td>';
            rowData += '<td align="center" class="gstRow" id ="gstColumn1_'+businessDivIdNum+'_'+cnt+'"><input type="text" name="customerBillingGstNumberNew_' + businessDivIdNum + '_' + cnt + '" id="customerBillingGstNumberNew_' + businessDivIdNum + '_' + cnt + '" class="custBillingGstNumber" style="display:none"/></td>';
            rowData += '<td align="center"  id="adress_actionItems_' + businessDivIdNum + '_' + cnt + '">';
            rowData += '<img class="add_billing_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
            rowData += '&nbsp;<img class="delete_billing_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;"/>';
            rowData += '</td>';
            $(theRow).after('<tr class="business_row" id="business_row_' + businessDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
            $('#businessAddressCount_' + businessDivIdNum).val(cnt);
        }

        function submitForm() {
            document.getElementById("reportForm").action = "customerList.htm";
            document.reportForm.submit();
            startLoading();
        }

        $("body").on("click", '.add_business_row', function () {
            addworkBusinessContactRow(this);
        });
        $("body").on("click", '.delete_business_row', function () {
            var Row1 = $(this).parent().parent();
//                var BusinessContactId=$(rowObject).parent().parent().parent().parent().attr("id");
//                var BusinessContactIdNum=BusinessContactId.split('_')[1];
//			var businessContactCount = $('#businessContactCount_'+BusinessContactIdNum).val();
//			businessContactCount = parseInt(businessContactCount) - 1; 
            Row1.remove();
        });

        function addworkBusinessContactRow(rowObject) {
            var Row1 = $(rowObject).parent().parent();
            var BusinessContactId = $(rowObject).parent().parent().parent().parent().attr("id");
            var BusinessContactIdNum = BusinessContactId.split('_')[1];
            var rowData = "";
            var businessContactCount = $('#businessContactCount_' + BusinessContactIdNum).val();
            var cnt = parseInt(businessContactCount) + 1;
            rowData += '<td style="text-align:left">';
            rowData += '<input type="text" name="customerContactPersonNew_' + BusinessContactIdNum + '_' + cnt + '" id="customerContactPerson_' + BusinessContactIdNum + '_' + cnt + '" class="custContactPerson  textbox_new" value="">';
            rowData += '</td>';
            rowData += '<td><input type="text" name="customerDesignationNew_' + BusinessContactIdNum + '_' + cnt + '" id="customerDesignation_' + BusinessContactIdNum + '_' + cnt + '" class="custFinContactName textbox_new" value=""></td>';
            rowData += '<td style="text-align:left">';
            rowData += '<input type="text" name="customerContactPhoneNew_' + BusinessContactIdNum + '_' + cnt + '" id="customerContactPhone_' + BusinessContactIdNum + '_' + cnt + '" class="custContactPhone  textbox_new" value="">';
            rowData += '</td>';
            rowData += '<td  style="text-align:left">';
            rowData += '<input type="text" name="customerContactEmailNew_' + BusinessContactIdNum + '_' + cnt + '" id="customerEmail_' + BusinessContactIdNum + '_' + cnt + '" class="customerEmail required email textbox_new" value="">';
            rowData += '<td align="center" id="adress_actionItems_' + BusinessContactIdNum + '_' + cnt + '" name="adress_actionItems_' + BusinessContactIdNum + '_' + cnt + '">';
            rowData += '<img class="add_business_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
            rowData += '&nbsp;<img class = "delete_business_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;" />';
            rowData += '</td>';
            $(Row1).after('<tr id="businessContactRow_' + BusinessContactIdNum + '_' + cnt + '">' + rowData + '</tr>');
            $('#businessContactCount_' + BusinessContactIdNum).val(cnt);
        }
        $("body").on("click", '.add_location_row', function () {
            //var ab = '<tr class="copyRow"><td colspan="8"><input type="checkbox" name="copyAddress" class="copyAddress" value="copy"/><span>Customer Work Location same as Customer Billing Address</span>&nbsp;&nbsp;<select class="billAddOptions"><option>-Select Address-</option></select></td></tr>';
            addworkLocationRow(this);
            //$(".customer_work_address tr:last").before(ab);
        });
        
        $("body").on("click", '.delete_location_row', function () {
            var locationRowDelete = $(this).parent().parent();
//                var workLocationDivId=$(rowObject).parent().parent().parent().parent().attr("id");
//                var workLocationDivIdNum=workLocationDivId.split('_')[1];
//                var workLocationCount = $('#workLocationCount_'+workLocationDivIdNum).val();
//                workLocationCount = parseInt(workLocationCount) - 1;
         //Added for Copy Location
            locationRowDelete.prev(".copyRow").remove();
            locationRowDelete.remove();
        });
        function addworkLocationRow(rowObject) {
            var Row1 = $(rowObject).parent().parent();
            var workLocationDivId = $(rowObject).parent().parent().parent().parent().attr("id");
            var workLocationDivIdNum = workLocationDivId.split('_')[1];
            var rowData = "";
            var workLocationCount = $('#workLocationCount_' + workLocationDivIdNum).val();
            var cnt = parseInt(workLocationCount) + 1;
            var ab = '<tr class="copyRow"><td colspan="8"><input type="checkbox" name="copyAddress" class="copyAddress" value="copy"/><span>Customer Work Location same as Customer Billing Address</span>&nbsp;&nbsp;<select class="billAddOptions"><option>-Select Address-</option></select></td></tr>';
    //Added for Copy Address function
            //rowData +='<tr class="copyRow"><td colspan="8"><input type="checkbox" name="copyAddress" class="copyAddress" value="copy"/><span>Customer Work Location same as Customer Billing Address</span>&nbsp;&nbsp;<select class="billAddOptions"><option>-Select Address-</option></select></td></tr>';
            rowData += '<td align="center"><textarea type="text" name="customerWorkAddressNew_' + workLocationDivIdNum + '_' + cnt + '" class="customerWorkAddress" id="w_adress_' + workLocationDivIdNum + '_' + cnt + '" style="max-width: 221px !important;"></textarea></td>';
            rowData += '<td align="center"><input type="text" name="customerWorkShortCodeNew_' + workLocationDivIdNum + '_' + cnt + '" id="customerWorkShortCode_' + workLocationDivIdNum + '_' + cnt + '" class="custWorkShortCode" maxlength="15"></td>';
            rowData += '<td  align="center">';
            rowData += '<select name="customerWorkCountryNew_' + workLocationDivIdNum + '_' + cnt + '" id="w_country_' + workLocationDivIdNum + '_' + cnt + '" class="custWorkCountry  required textbox_new country_select">';
            rowData += '<option value="">-- Select Country -- </option>';
            rowData += '<c:forEach items="${countryList}" var="countryList" >';
            rowData += '<option value="${countryList.countryID}">${countryList.countryName}</option>';
            rowData += '</c:forEach>';
            rowData += '</select>';
            rowData += '</td>';
            rowData += '<td  align="center">';
            rowData += '<select name="customerWorkRegionNew_' + workLocationDivIdNum + '_' + cnt + '" id="w_region_' + workLocationDivIdNum + '_' + cnt + '" class="custWorkRegion textbox_new region_select">';
            rowData += '<option value="">-- Select Region -- </option></select></td>';
            rowData += '<td  align="center">';
            rowData += '<select name="customerWorkCityNew_' + workLocationDivIdNum + '_' + cnt + '" id="w_city_' + workLocationDivIdNum + '_' + cnt + '" class="customerWorkCity  textbox_new">';
            rowData += '<option value="">-- Select City -- </option></select></td>';
            rowData += '<td align="center"><input type="text" name="customerWorkPincodeNew_' + workLocationDivIdNum + '_' + cnt + '" id="w_pincode_' + workLocationDivIdNum + '_' + cnt + '" class="custWorkPinCode"></td>';
            rowData += '<td><input type="text" name="workingHoursPerDayNew_' + workLocationDivIdNum + '_' + cnt + '" id="w_working_hours_' + workLocationDivIdNum + '_' + cnt + '" class="workingHoursPerDay required textbox_new" value="" placeholder="HH:MM"></td>';
            rowData += '<td><select name="workingLocationIsCompanyLocationNew_'+ workLocationDivIdNum + '_' + cnt + '" id="workingLocationIsCompanyLocationNew_'+ workLocationDivIdNum + '_' + cnt + '" class="customerWorkLocationDefining textbox_new">';
            rowData += '<option value="">--Select Location--</option>';
            rowData += '<option value="0">Customer Location</option>';
            rowData += '<option value="1">Company Location</option>';
            rowData += '</select></td>';
            rowData += '<td align="center">';
            rowData += '<img class = "add_location_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
            rowData += '&nbsp;<img class = "delete_location_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;">';
            rowData += '</td>';
            //$(Row1).before(ab);
            $(Row1).after('<tr class="copyRow"><td colspan="8"><input type="checkbox" name="copyAddress" class="copyAddress" value="copy"/><span>Customer Work Location same as Customer Billing Address</span>&nbsp;&nbsp;<select class="billAddOptions"><option>-Select Address-</option></select></td></tr><tr id="workLocationRow_' + workLocationDivIdNum + '_' + cnt + '" name="workLocationRow_' + workLocationDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
            $('#workLocationCount_' + workLocationDivIdNum).val(cnt);
        }

        function hideCustomerGroupName() {
            var customerGroupId = $("input[@name=customerGroup]:checked").attr('id');
            var customerGroupValue = $("#" + customerGroupId).val();
            if (customerGroupValue == 'n') {
                $("#customerGroupName").removeAttr("disabled");
            } else {
                $("#customerGroupName").attr("disabled", "disabled");
            }
        }

        function disableSubmit(saveButtonId, submitButtonId, backButtonId)
        {
            $("#formCustomerDetails").validate();
            if ($("#formCustomerDetails").valid()) {
                var returnVariable;
                if ($("#customerName").val() != "") {
                    customerNameD = $("#customerName").val();
                    $.ajax({
                        url: './getDuplicateCustomerName.htm',
                        type: "POST",
                        async: false,
                        data: ({customerName: customerNameD}),
                        success: function (ajaxObj) {
                            if ($.trim(ajaxObj) != "") {
                                $("#errormessage").html("Customer Name already exist");
                                $("#errorMessageDisplay").css({"display": "block"});
                                returnVariable = false;
                            } else {
                                returnVariable = true;
                            }
                        }
                    });
                }
                if (returnVariable) {
                    $("#" + saveButtonId).hide();
                    $("#" + submitButtonId).hide();
                    $("#" + backButtonId).hide();
                }
                return returnVariable;
            }
        }

        function disableSave(saveButtonId, submitButtonId, backButtonId) {
            if ($("#customerName").val() == "") {
                $("#errormessage").html("Please Select Customer Name");

                return false;
            } else if ($("#salesPerson").val() == "") {
                $("#errormessage").html("Please Select BDM/Sales Person");
                return false;
            } else if ($("#attachmentValue").val == "") {
                $("#errormessage").html("Please attach the file");
                return false;
            }
            else {
                var returnVariable;
                if ($("#customerName").val() != "") {
                    customerNameD = $("#customerName").val();
                    $.ajax({
                        url: './getDuplicateCustomerName.htm',
                        type: "POST",
                        async: false,
                        data: ({customerName: customerNameD}),
                        success: function (ajaxObj) {
                            if ($.trim(ajaxObj) != "") {
                                $("#errormessage").html("Customer Name already exist")
                                returnVariable = false;
                            } else {
                                returnVariable = true;
                            }
                        }
                    });
                }
                if (returnVariable) {
                    $("#" + saveButtonId).hide();
                    $("#" + submitButtonId).hide();
                    $("#" + backButtonId).hide();
                }
                return returnVariable;
            }
        }
        function addCustomer() {

            document.location.href = "addCustomer.htm";
        }
        function textLimit(field, maxlen) {
            if (field.value.length > maxlen) {
                while (field.value.length > maxlen) {
                    field.value = field.value.replace(/.$/, '');
                }
                $('#errormessage').html('Should not more than ' + maxlen + ' characters');

            }
        }
        $("body").on("change", '.select_sub_RBU', function () {
            getSubRBU(this.value);
        });



        /********Submit button validation*************/
        $("#submitBtn").click(function (e) {
            $('#buttonStatus').val('m');
            contactDetailsValidation();

        });

        function contactDetailsValidation() {
            var custNameVal = document.getElementById("customerName").value;
            var custNameID = document.getElementById("customerName").id;
            //var custNameStatus=allLetter(custNameID,custNameVal);
            var custNameStatus = nullFieldCheck(custNameID, custNameVal);

            var aboutCustomerVal = document.getElementById("aboutCustomer").value;
            var aboutCustomerID = document.getElementById("aboutCustomer").id;
            var aboutCustomerStatus = nullFieldCheck(aboutCustomerID, aboutCustomerVal);

            var customerURLVal = document.getElementById("customerURL").value;
            var customerURLID = document.getElementById("customerURL").id;
            var customerURLStatus = checkUrl(customerURLID, customerURLVal);

            var salesPersonVal = document.getElementById("salesPerson").value;
            var salesPersonID = document.getElementById("salesPerson").id;
            var salesPersonStatus = nullFieldCheck(salesPersonID, salesPersonVal);

            var attachmentTypeVal = document.getElementById("attachmentType").value;
            var attachmentTypeID = document.getElementById("attachmentType").id;
            var attachmentTypeStatus = nullFieldCheck(attachmentTypeID, attachmentTypeVal);

            var salesPersonContactNoVal = document.getElementById("salesPersonContactNo").value;
            var salesPersonContactNoID = document.getElementById("salesPersonContactNo").id;
            var salesPersonContactNoStatus = phoneNumberCheck(salesPersonContactNoID, salesPersonContactNoVal);

            var attachmentVal = document.getElementById("attachmentValue").value;
            var attachmentID = document.getElementById("attachmentValue").id;
            var attachmentStatus = attachmentTypeValidation(attachmentID, attachmentVal);
            
//            var invoiceVal = document.getElementById("invoiceTo").value;
//            var invoiceID = document.getElementById("invoiceTo").id;
//            var invoiceStatus = nullFieldCheck(invoiceID, invoiceVal);

            /*********************/
//                var divisionVal=document.getElementById("division").value;
//                var divisionID=document.getElementById("division").id;
//                var divisionStatus=allLetter(divisionID,divisionVal); 

//		var DivsionStatus=tableAlphabetCheck("division");
            var DivsionStatus = divisionNameCheck("division");
            var customerWorkAddressStatus = tableNullCheck("customerWorkAddress");
            var custWorkShortCodeStatus = tableNullCheck("custWorkShortCode");
            var custWorkCountryStatus = tableNullCheck("custWorkCountry");
            var custWorkRegionStatus = tableNullCheck("custWorkRegion");
            var customerWorkCityStatus = tableNullCheck("customerWorkCity");
            var custWorkPinCodeStatus = tableAlphaNumericCheck("custWorkPinCode");
            var custworkingHoursPerDayStatus = tableTimeCheck("workingHoursPerDay");
            var customerWorkLocation = tableNullCheck("customerWorkLocationDefining");

            var custContactPersonStatus = tableAlphabetCheck("custContactPerson");
            var custContactPhoneStatus = tableNumericCheck("custContactPhone");
            var customerEmailStatus = tableEmailCheck1("customerEmail");

            var custFinContPersonStatus = tableAlphabetCheck("custFinContactName");
            var custFinContPhoneStatus = tableNumericCheck("custFinContPhone");
            var custFinEmailStatus = tableEmailCheck("custFinEmail");
            
            var custDunContPersonStatus = tableAlphabetCheck("custDunContactName");
            var custDunContPhoneStatus = tableNumericCheck("custDunContPhone");
            var custDunEmailStatus = tableEmailCheck("custDunEmail");

            var custBillingAddressStatus = tableNullCheck("custBillingAddress");
            var custBillingShortCodeStatus = tableNullCheck("custBillingShortCode");
            var custBillingCountryStatus = tableNullCheck("custBillingCountry");
            var custInvoiceStatus = tableNullCheck("custInvoice");
            var custBillingRegionStatus = tableNullCheck("custBillingRegion");
            var custBillingCityStatus = tableNullCheck("custBillingCity");
            var custBillingPincodeStatus = tableAlphaNumericCheck("custBillingPincode");
            var custBillingGstNumberStatus = tableAlphaNumericGst("custBillingGstNumber","custBillingCountry");
            if (custNameStatus && aboutCustomerStatus && salesPersonStatus && attachmentTypeStatus &&
                    salesPersonContactNoStatus && attachmentStatus && customerURLStatus && DivsionStatus && 
                    custInvoiceStatus && customerWorkAddressStatus && custWorkShortCodeStatus && 
                    custWorkCountryStatus && custWorkRegionStatus && customerWorkCityStatus && 
                    custWorkPinCodeStatus && custworkingHoursPerDayStatus && custContactPersonStatus && 
                    custContactPhoneStatus && customerEmailStatus && custFinContPersonStatus && 
                    custFinContPhoneStatus && custFinEmailStatus && custBillingAddressStatus && 
                    custBillingShortCodeStatus && custBillingCountryStatus && custBillingRegionStatus && 
                    custBillingCityStatus && custBillingPincodeStatus && customerWorkLocation && 
                    custBillingGstNumberStatus && custDunContPersonStatus && custDunContPhoneStatus && custDunEmailStatus) {

                var custName = $("#customerName").val();
                $.ajax({
                    url: "getDuplicateCustomerName.htm?customerName=" + custName,
                    type: "POST",
                    success: function (data) {
                        if (data == 0) {
                            $("#saveBtn").hide();
                            $("#submitBtn").hide();
                            $("#btnCancel").hide();
                            $("#formCustomerDetails").submit();
                            startLoading();
                        } else {
                            $("#dupError").html("<span style='color:red;'><b>Please enter unique customer name.</b></span>");
                            $("#customerName").trigger('focus');
                        }
                    }
                });
//                    $("#formCustomerDetails").submit();
//                    startLoading();
            } else {
            }
        }
        function ValidateEmail(uid, uemail) {
            var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
            if (uemail.match(mailformat)) {
                $("#" + uid).css({"outline": "none"});
                $(".errorText").css({"display": "none"});
                $("#custURLerrormessage").css({"display": "none"});
                return true;
            } else {
                $("#custURLerrormessage").css({"display": "block"});
                $(".errorText").show();
                $(".errorText").css({"display": "block"});
                $("#" + uid).css({"outline": "1px solid red"});
                return false;
            }
        }
        function attachmentTypeValidation(uid, uval) {
            var fileExt = $('#' + uid).val().split('.').pop().toLowerCase();
            if ($.inArray(fileExt, ['pdf', 'jpg', 'png', 'jpeg']) == -1) {
                $("#attachFileerrormessage").css({"display": "block"});
                $("#" + uid).css({"outline": "1px solid red"});
                return false;
            } else {
                $("#" + uid).css({"outline": "none"});
                $("#attachFileerrormessage").css({"display": "none"});
                return true;
            }
        }
        function saveAttachmentTypeValidation(uid, uval) {
            var fileExt = $('#' + uid).val().split('.').pop().toLowerCase();
            if (fileExt.length > 0) {
                if ($.inArray(fileExt, ['pdf', 'jpg', 'png', 'jpeg']) == -1) {
                    $("#attachFileerrormessage").css({"display": "block"});
                    $("#" + uid).css({"outline": "1px solid red"});
                    return false;
                } else {
                    $("#" + uid).css({"outline": "none"});
                    $("#attachFileerrormessage").css({"display": "none"});
                    return true;
                }
            } else {
                $("#" + uid).css({"outline": "none"});
                $("#attachFileerrormessage").css({"display": "none"});
                return true;
            }
        }
//                    if(!uval == ''){
//                        var ext = uval.split(".");
//                        var len = ext.length;
//                        var imgsize = document.getElementById(uid).files[0];
//                        var fileSize = imgsize.size;
//                        var extType=ext[(len-1)];
//                    if((extType === "docx" || extType === "pdf" ||extType === "xlsx")){
//                        if(fileSize < 3145728)
//                        {
//                           $("#"+uid).css({"outline":"none"});
//                           $("#attachFileerrormessage").css({"display":"none"});
//                           $("#attachFileerrormessage1").css({"display":"none"});
//                           return true;
//                        }
//                        else{
//                            $("#attachFileerrormessage1").css({"display":"block"});
//                           $("#"+uid).css({"outline":"1px solid red"});
//                            return false;
//                        }
//                        $("#"+uid).css({"outline":"none"});
//                        $("#attachFileerrormessage").css({"display":"none"});
//                        return false;
//                    }
//                    else{
//                        $("#attachFileerrormessage").css({"display":"block"});
//                        $("#"+uid).css({"outline":"1px solid red"});
//                        return false;
//                    }
//                }else{
//                   $("#attachFileerrormessage").css({"display":"block"});
//                    $("#"+uid).css({"outline":"1px solid red"});
//                    return false;
//                }



        function allNumericTOP(uid, uVal) {
            var allNumeric = /^[0-9]+$/;
            if (uVal.match(allNumeric)) {
                if (uVal > 0 && uVal < 365) {
                    $("#" + uid).css({"outline": "none"});
                    $("#termsOfPaymenterrormessage").css({"display": "none"});
                    return true;
                } else {
                    $("#termsOfPaymenterrormessage").css({"display": "block"});
                    $("#" + uid).css({"outline": "1px solid red"});
                    return false;
                }
            } else {
                $("#termsOfPaymenterrormessage").css({"display": "block"});
                $("#" + uid).css({"outline": "1px solid red"});
                return false;
            }
        }
        function allNumeric(uid, uVal) {
            var allNumeric = /(?:\(?\+\d{2}\)?\s*)?\d+(?:[ -]*\d+)*$/;
            if (uVal.match(allNumeric)) {
                $("#" + uid).css({"outline": "none"});
                if (uid == "termsOfPayment") {
                    $("#termsOfPaymenterrormessage").css({"display": "none"});
                } else if (uid == "salesPersonContactNo") {
                    $("#SalesContNoerrormessage").css({"display": "none"});
                }
                return true;
            } else {
                if (uid == "termsOfPayment") {
                    $("#termsOfPaymenterrormessage").css({"display": "block"});
                } else if (uid == "salesPersonContactNo") {
                    $("#SalesContNoerrormessage").css({"display": "block"});
                }

                $("#" + uid).css({"outline": "1px solid red"});
                return false;
            }
        }

        function phoneNumberCheck(uid, uVal) {
            var allNumeric = /^\(?\+?[\d\(\-\s\)]+$/;
            if (uVal.match(allNumeric)) {
                $("#" + uid).css({"outline": "none"});
                if (uid == "termsOfPayment") {
                    $("#termsOfPaymenterrormessage").css({"display": "none"});
                } else if (uid == "salesPersonContactNo") {
                    $("#SalesContNoerrormessage").css({"display": "none"});
                }
                return true;
            } else {
                if (uid == "termsOfPayment") {
                    $("#termsOfPaymenterrormessage").css({"display": "block"});
                } else if (uid == "salesPersonContactNo") {
                    $("#SalesContNoerrormessage").css({"display": "block"});
                }

                $("#" + uid).css({"outline": "1px solid red"});
                return false;
            }
        }


        function savePhNoCheck(uid, uVal) {
            var allNumeric = /^\(?\+?[\d\(\-\s\)]+$/;
            if (uVal.length > 0) {
                if (uVal.match(allNumeric)) {
                    $("#" + uid).css({"outline": "none"});
                    $("#SalesContNoerrormessage").css({"display": "none"});
                    return true;
                } else {
                    $("#SalesContNoerrormessage").css({"display": "block"});
                    $("#" + uid).css({"outline": "1px solid red"});
                    return false;
                }
            } else {
                $("#" + uid).css({"outline": "none"});
                $("#SalesContNoerrormessage").css({"display": "none"});
                return true;
            }
        }


        function checkUrl(uid, uname)
        {
            if (!uname == '') {
                //regular expression for URL
                var urlpattern = /^(http|https)?:\/\/[a-zA-Z0-9-\.]+\.[a-z]{2,4}/;

                if (urlpattern.test(uname)) {
                    $("#" + uid).css({"outline": "none"});
                    $("#custURLerrormessage").css({"display": "none"});
                    return true;
                } else {
                    $("#custURLerrormessage").css({"display": "block"});
                    $("#" + uid).css({"outline": "1px solid red"});
                    return false;
                }
            } else {
                return true;
            }
        }

        function allLetter(uid, uname) {
            var letters = /^[A-Za-z- ]+$/;
            var fieldValLength = $.trim(uname).length;
            if (fieldValLength > 0) {
                if (uname.match(letters)) {
                    $("#" + uid).css({"outline": "none"});
                    $("#nameerrormessage").css({"display": "none"});
                    return true;
                } else {
                    $("#nameerrormessage").css({"display": "block"});
                    $("#" + uid).css({"outline": "1px solid red"});
                    return false;
                }
            } else {
                $("#nameerrormessage").css({"display": "block"});
                $("#" + uid).css({"outline": "1px solid red"});
                return false;
            }
        }
        function nullFieldCheck(fieldId, fieldVal) {
            var fieldValLength = $.trim(fieldVal).length;
            if (fieldValLength > 0) {
                $("#" + fieldId).css({"outline": "none"});
                if (fieldId === 'customerName') {
                    $("#nameerrormessage").css({"display": "none"});
                }
                else if (fieldId === 'aboutCustomer') {
                    $("#AbtCusterrormessage").css({"display": "none"});
                } else if (fieldId === 'attachmentValue') {
                    $("#attachFileerrormessage").css({"display": "none"});
                } else if (fieldId === 'customerGroupName') {
                    $("#custGrperrormessage").css({"display": "none"});
                } else if (fieldId === 'salesPerson') {
                    $("#salesNameerrormessage").css({"display": "none"});
                } else if (fieldId === 'attachmentType') {
                    $("#attachTypeerrormessage").css({"display": "none"});
                }else if (fieldId === 'invoiceTo') {
                    $("#invoiceerrormessage").css({"display": "none"});
                }
                return true;
            } else {
                if (fieldId === 'customerName') {
                    $("#nameerrormessage").css({"display": "block"});
                }
                else if (fieldId === 'aboutCustomer') {
                    $("#AbtCusterrormessage").css({"display": "block"});
                } else if (fieldId === 'attachmentValue') {
                    $("#attachFileerrormessage").css({"display": "block"});
                } else if (fieldId === 'customerGroupName') {
                    $("#custGrperrormessage").css({"display": "block"});
                } else if (fieldId === 'salesPerson') {
                    $("#salesNameerrormessage").css({"display": "block"});
                } else if (fieldId === 'attachmentType') {
                    $("#attachTypeerrormessage").css({"display": "block"});
                }else if (fieldId === 'invoiceTo') {
                    $("#invoiceerrormessage").css({"display": "block"});
                }
                $("#" + fieldId).css({"outline": "1px solid red"});
                return false;
            }
        }

        function workTimeCheck(timeID, TimeVal) {
            var hoursPerDay = TimeVal.split(":");

            if ((hoursPerDay[0] > 0 && hoursPerDay[0] <= 12) && (hoursPerDay[1] >= 0 && hoursPerDay[1] <= 59)) {
                if (hoursPerDay[0] == 12 && hoursPerDay[1] > 0) {
                    $("#workHrserrormessage").css({"display": "block"});
                    $("#" + timeID).css({"outline": "1px solid red"});
                    return false;
                } else {
                    $("#" + timeID).css({"outline": "none"});
                    $("#workHrserrormessage").css({"display": "none"});
                    return true;
                }

            } else {
                $("#workHrserrormessage").css({"display": "block"});
                $("#" + timeID).css({"outline": "1px solid red"});
                return false;
            }
        }
        
       

        /********CONTACT DETAILS ROW DATAS VALIDATION********/
        function divisionNameCheck(class_name) {
            var NullError = 0;
            var class_length = document.querySelectorAll("." + class_name).length;
            for (var i = 0; i < class_length; i++) {
                var class_name_val_length = document.querySelectorAll("." + class_name)[i].value.trim().length;
                if (i === 0) {
                    if (document.querySelector(".addDivisionDiv").style.visibility === 'visible') {
                        if (class_name_val_length > 0) {
                            document.querySelectorAll("." + class_name)[i].style.outline = "none";
                        } else {
                            document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                            NullError++;
                        }
                    } else {
                        document.querySelectorAll("." + class_name)[i].style.outline = "none";
                    }
                } else {
                    if (class_name_val_length > 0) {
                        for (var j = 0; j < class_length; j++) {
                            if (j !== i) {
                                var actualData = (document.querySelectorAll("." + class_name)[i].value);
                                var compareData = (document.querySelectorAll("." + class_name)[j].value);
                                if (actualData !== compareData) {
                                    document.querySelectorAll("." + class_name)[i].style.outline = "none";
                                } else {
                                    document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                                    document.querySelectorAll("." + class_name)[j].style.outline = "1px solid red";
                                    NullError++;
                                }
                            }
                        }
                    } else {
                        document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                        NullError++;
                    }
                }
            }
            if (NullError <= 0) {
                return true;
            } else if (NullError > 0) {
                return false;
            }

        }

        function tableNullCheck(class_name) {
            var NullError = 0;
            var class_length = document.querySelectorAll("." + class_name).length;

            for (var i = 0; i < class_length; i++) {
                var class_name_val = document.querySelectorAll("." + class_name)[i].value;
                var class_name_val_length = $.trim(class_name_val).length;

                if (class_name_val_length > 0) {
                    document.querySelectorAll("." + class_name)[i].style.outline = "none";

                } else {
                    document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                    NullError++;
                }
            }
            if (NullError <= 0) {
                return true;
            } else if (NullError > 0) {
                return false;
            }
        }
        function tableAlphabetCheck(clas_name) {
            var AlpahbetError = 0;
            var clas_length = document.querySelectorAll("." + clas_name).length;

            for (var i = 0; i < clas_length; i++) {
                var clas_val = document.querySelectorAll("." + clas_name)[i].value.trim();
                var Tableletters = /^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$/;
                if (clas_val.match(Tableletters)) {
                    document.querySelectorAll("." + clas_name)[i].style.outline = "none";
                    $(".errorNameText").css({"display": "none"});
                } else {
                    document.querySelectorAll("." + clas_name)[i].style.outline = "1px solid red";
                    $(".errorNameText").css({"display": "block"});
                    AlpahbetError++;
                }
            }
            if (AlpahbetError <= 0) {
                return true;
            } else if (AlpahbetError > 0) {
                return false;
            }
        }
        function tableNumericCheck(cla_name) {
            var NumericError = 0;
            var cla_length = document.querySelectorAll("." + cla_name).length;

            for (var i = 0; i < cla_length; i++) {
                var cla_val = document.querySelectorAll("." + cla_name)[i].value.trim();
                var TableallNumeric = /^\(?\+?[\d\(\-\s\)]+$/;
                if (cla_val.match(TableallNumeric)) {
                    document.querySelectorAll("." + cla_name)[i].style.outline = "none";
                } else {
                    document.querySelectorAll("." + cla_name)[i].style.outline = "1px solid red";
                    NumericError++;
                }
            }
            if (NumericError <= 0) {
                return true;
            } else if (NumericError > 0) {
                return false;
            }
        }
        function tableAlphaNumericCheck(cla_name) {
            var NumericError = 0;
            var cla_length = document.querySelectorAll("." + cla_name).length;

            for (var i = 0; i < cla_length; i++) {
                var cla_val = document.querySelectorAll("." + cla_name)[i].value.trim();
                var TableallNumeric = /^[A-Za-z0-9\s]+$/;
                if (cla_val.match(TableallNumeric)) {
                    document.querySelectorAll("." + cla_name)[i].style.outline = "none";
                } else {
                    document.querySelectorAll("." + cla_name)[i].style.outline = "1px solid red";
                    NumericError++;
                }
            }
            if (NumericError <= 0) {
                return true;
            } else if (NumericError > 0) {
                return false;
            }
        }
        function tableAlphaNumericGst(cla_name,cunty_name) {
            var NumericError = 0;
            var cla_length = document.querySelectorAll("." + cla_name).length;

            for (var i = 0; i < cla_length; i++) {
                var cla_val = document.querySelectorAll("." + cla_name)[i].value.trim();
                var country_value = document.querySelectorAll("."+cunty_name)[i].value.trim();
                var TableallNumeric = /^[A-Za-z0-9]+$/;
                if(country_value.match("113")){
                   if (cla_val.match(TableallNumeric)) {
                        document.querySelectorAll("." + cla_name)[i].style.outline = "none";
                        $(".errorTextGst").css({"display": "none"});
                    } else {
                        document.querySelectorAll("." + cla_name)[i].style.outline = "1px solid red";
                        $(".errorTextGst").css({"display": "block"});
                        NumericError++;
                    }
                }else{
                    document.querySelectorAll("." + cla_name)[i].style.outline = "none";
                    $(".errorTextGst").css({"display": "none"});
                }
                
            }
            if (NumericError <= 0) {
                return true;
            } else if (NumericError > 0) {
                return false;
            }
        }
        function tableEmailCheck(cl_name) {
            var EmailError = 0;
            var cl_length = document.querySelectorAll("." + cl_name).length;

            for (var i = 0; i < cl_length; i++) {
                var cl_val = document.querySelectorAll("." + cl_name)[i].value.trim();
                var Tablemailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if (cl_val.match(Tablemailformat)) {
                    document.querySelectorAll("." + cl_name)[i].style.outline = "none";
                    $(".errorText1").css({"display": "none"});
                } else {
                    document.querySelectorAll("." + cl_name)[i].style.outline = "1px solid red";
                    $(".errorText1").show();
                    $(".errorText1").css({"display": "block"});
                    EmailError++;
                }
            }
            if (EmailError <= 0) {
                return true;
            } else if (EmailError > 0) {
                return false;
            }
        }

        function tableEmailCheck1(cl_name) {
            var EmailError = 0;
            var cl_length = document.querySelectorAll("." + cl_name).length;

            for (var i = 0; i < cl_length; i++) {
                var cl_val = document.querySelectorAll("." + cl_name)[i].value.trim();
                var Tablemailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                if (cl_val.match(Tablemailformat)) {
                    document.querySelectorAll("." + cl_name)[i].style.outline = "none";
                    $(".errorText").css({"display": "none"});
                } else {
                    document.querySelectorAll("." + cl_name)[i].style.outline = "1px solid red";
                    $(".errorText").show();
                    $(".errorText").css({"display": "block"});
                    EmailError++;
                }
            }
            if (EmailError <= 0) {
                return true;
            } else if (EmailError > 0) {
                return false;
            }
        }

        function tableTimeCheck(c_name) {
            var TimeError = 0;
            var c_length = document.querySelectorAll("." + c_name).length;
            for (var i = 0; i < c_length; i++) {
                var timeRegex = /[0-9]{2}[:][0-9]{2}$/;
                var clas_val = document.querySelectorAll("." + c_name)[i].value;
                if (clas_val.match(timeRegex)) {
                    var c_val = document.querySelectorAll("." + c_name)[i].value;
                    var hoursPerDay = c_val.split(":");

                    if ((hoursPerDay[0] > 0 && hoursPerDay[0] <= 12) && (hoursPerDay[1] >= 0 && hoursPerDay[1] <= 59)) {
                        if (hoursPerDay[0] == 12 && hoursPerDay[1] > 0) {
                            document.querySelectorAll("." + c_name)[i].style.outline = "1px solid red";
                            $(".errorTextWorkHrs").css({"display": "block"});
                            TimeError++;
                        } else {
                            document.querySelectorAll("." + c_name)[i].style.outline = "none";
                            $(".errorTextWorkHrs").css({"display": "none"});
                        }

                    } else {
                        document.querySelectorAll("." + c_name)[i].style.outline = "1px solid red";
                        $(".errorTextWorkHrs").css({"display": "block"});
                        TimeError++;
                    }
                } else {
                    document.querySelectorAll("." + c_name)[i].style.outline = "1px solid red";
                    $(".errorTextWorkHrs").css({"display": "block"});
                    TimeError++;
                }
            }
            if (TimeError <= 0) {
                return true;
            } else if (TimeError > 0) {
                return false;
            }
        }


        /**********rows save validations**********/
        function savetableNullRemoveCheck(class_name) {
            var NullError = 0;
            var class_length = document.querySelectorAll("." + class_name).length;
            for (var i = 0; i < class_length; i++) {
                document.querySelectorAll("." + class_name)[i].style.outline = "none";
            }
        }




        function saveTableNumericCheck(savecla_name) {
            var saveNumericError = 0;
            var savecla_length = document.querySelectorAll("." + savecla_name).length;
            for (var i = 0; i < savecla_length; i++) {
                if (document.querySelectorAll("." + savecla_name)[i].value.length > 0) {
                    var savecla_val = document.querySelectorAll("." + savecla_name)[i].value;
                    var saveTableallNumeric = /^\(?\+?[\d\(\-\s\)]+$/;
                    if (savecla_val.match(saveTableallNumeric)) {
                        document.querySelectorAll("." + savecla_name)[i].style.outline = "none";
                    } else {
                        document.querySelectorAll("." + savecla_name)[i].style.outline = "1px solid red";
                        saveNumericError++;
                    }
                } else {
                    document.querySelectorAll("." + savecla_name)[i].style.outline = "none";
                }
            }
            if (saveNumericError <= 0) {
                return true;
            } else if (saveNumericError > 0) {
                return false;
            }
        }
        function saveTableAlphaNumericCheck(savecla_name) {
            var saveNumericError = 0;
            var savecla_length = document.querySelectorAll("." + savecla_name).length;
            for (var i = 0; i < savecla_length; i++) {
                if (document.querySelectorAll("." + savecla_name)[i].value.length > 0) {
                    var savecla_val = document.querySelectorAll("." + savecla_name)[i].value;
                    var saveTableallNumeric = /^[A-Za-z0-9]+$/;
                    if (savecla_val.match(saveTableallNumeric)) {
                        document.querySelectorAll("." + savecla_name)[i].style.outline = "none";
                    } else {
                        document.querySelectorAll("." + savecla_name)[i].style.outline = "1px solid red";
                        saveNumericError++;
                    }
                } else {
                    document.querySelectorAll("." + savecla_name)[i].style.outline = "none";
                }
            }
            if (saveNumericError <= 0) {
                return true;
            } else if (saveNumericError > 0) {
                return false;
            }
        }

        function saveTableEmailCheck(savecl_name) {
            var saveEmailError = 0;
            var savecl_length = document.querySelectorAll("." + savecl_name).length;
            for (var i = 0; i < savecl_length; i++) {
                if (document.querySelectorAll("." + savecl_name)[i].value.length > 0) {
                    var savecl_val = document.querySelectorAll("." + savecl_name)[i].value;
                    var saveTablemailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                    if (savecl_val.match(saveTablemailformat)) {
                        document.querySelectorAll("." + savecl_name)[i].style.outline = "none";
                        $(".errorText").css({"display": "none"});
                    } else {
                        document.querySelectorAll("." + savecl_name)[i].style.outline = "1px solid red";
                        $(".errorText").show();
                        $(".errorText").css({"display": "block"});
                        saveEmailError++;
                    }
                } else {
                    document.querySelectorAll("." + savecl_name)[i].style.outline = "none";
                }
            }
            if (saveEmailError <= 0) {
                return true;
            } else if (saveEmailError > 0) {
                return false;
            }
        }

        function saveTableTimeCheck(c_name) {
            var TimeError = 0;
            var c_length = document.querySelectorAll("." + c_name).length;
            for (var i = 0; i < c_length; i++) {
                var timeRegex = /[0-9]{2}[:][0-9]{2}$/;
                var clas_val = document.querySelectorAll("." + c_name)[i].value.trim();
                if (clas_val.length > 0) {
                    if (clas_val.match(timeRegex)) {
                        var c_val = document.querySelectorAll("." + c_name)[i].value;
                        var hoursPerDay = c_val.split(":");

                        if ((hoursPerDay[0] > 0 && hoursPerDay[0] <= 12) && (hoursPerDay[1] >= 0 && hoursPerDay[1] <= 59)) {
                            if (hoursPerDay[0] == 12 && hoursPerDay[1] > 0) {
                                document.querySelectorAll("." + c_name)[i].style.outline = "1px solid red";
                                $(".errorTextWorkHrs").css({"display": "block"});
                                TimeError++;
                            } else {
                                document.querySelectorAll("." + c_name)[i].style.outline = "none";
                                $(".errorTextWorkHrs").css({"display": "none"});
                            }

                        } else {
                            document.querySelectorAll("." + c_name)[i].style.outline = "1px solid red";
                            $(".errorTextWorkHrs").css({"display": "block"});
                            TimeError++;
                        }
                    } else {
                        document.querySelectorAll("." + c_name)[i].style.outline = "1px solid red";
                        $(".errorTextWorkHrs").css({"display": "block"});
                        TimeError++;
                    }
                } else {
                    document.querySelectorAll("." + c_name)[i].style.outline = "none";

                }
            }
            if (TimeError <= 0) {
                return true;
            } else if (TimeError > 0) {
                return false;
            }

        }

        function saveTableAlphabetCheck(clas_name) {
            var AlpahbetError = 0;
            var clas_length = document.querySelectorAll("." + clas_name).length;

            for (var i = 0; i < clas_length; i++) {
                if (document.querySelectorAll("." + clas_name)[i].value.length > 0) {
                    var clas_val = document.querySelectorAll("." + clas_name)[i].value;
                    var Tableletters = /^[A-Za-z\s]{1,}[\.]{0,1}[A-Za-z\s]{0,}$/;
                    if (clas_val.match(Tableletters)) {
                        document.querySelectorAll("." + clas_name)[i].style.outline = "none";
                        $(".errorNameText").css({"display": "none"});
                    } else {
                        document.querySelectorAll("." + clas_name)[i].style.outline = "1px solid red";
                        $(".errorNameText").css({"display": "block"});
                        AlpahbetError++;
                    }
                } else {
                    document.querySelectorAll("." + clas_name)[i].style.outline = "none";
                }

            }
            if (AlpahbetError <= 0) {
                return true;
            } else if (AlpahbetError > 0) {
                return false;
            }
        }

        function saveattachmentTypeValidation(uid, uval) {
            if (uval.length > 0) {
                var fileExt = $('#' + uid).val().split('.').pop().toLowerCase();
                if ($.inArray(fileExt, ['pdf', 'jpg', 'png', 'jpeg']) == -1) {
                    $("#attachFileerrormessage").css({"display": "block"});
                    $("#" + uid).css({"outline": "1px solid red"});
                    return false;
                } else {
                    $("#" + uid).css({"outline": "none"});
                    $("#attachFileerrormessage").css({"display": "none"});
                    return true;
                }
            } else {
                $("#" + uid).css({"outline": "none"});
                $("#attachFileerrormessage").css({"display": "none"});
                return true;
            }
        }

        function saveallNumericTOP(uid, uVal) {
            if (uVal.length > 0) {
                var allNumeric = /^[0-9]+$/;
                if (uVal.match(allNumeric)) {
                    if (uVal > 0 && uVal < 365) {
                        $("#" + uid).css({"outline": "none"});
                        $("#termsOfPaymenterrormessage").css({"display": "none"});
                        return true;
                    } else {
                        $("#termsOfPaymenterrormessage").css({"display": "block"});
                        $("#" + uid).css({"outline": "1px solid red"});
                        return false;
                    }
                } else {
                    $("#termsOfPaymenterrormessage").css({"display": "block"});
                    $("#" + uid).css({"outline": "1px solid red"});
                    return false;
                }
            } else {
                $("#" + uid).css({"outline": "none"});
                $("#termsOfPaymenterrormessage").css({"display": "none"});
                return true;
            }
        }
        function saveallNumeric(uid, uVal) {
            if (uVal.length > 0) {
                var allNumeric = /^\(?\+?[\d\(\-\s\)]+$/;
                if (uVal.match(allNumeric)) {
                    $("#" + uid).css({"outline": "none"});
                    if (uid == "termsOfPayment") {
                        $("#termsOfPaymenterrormessage").css({"display": "none"});
                    } else if (uid == "salesPersonContactNo") {
                        $("#SalesContNoerrormessage").css({"display": "none"});
                    }
                    return true;
                } else {
                    if (uid == "termsOfPayment") {
                        $("#termsOfPaymenterrormessage").css({"display": "block"});
                    } else if (uid == "salesPersonContactNo") {
                        $("#SalesContNoerrormessage").css({"display": "block"});
                    }

                    $("#" + uid).css({"outline": "1px solid red"});
                    return false;
                }
            } else {
                $("#" + uid).css({"outline": "none"});
                if (uid == "termsOfPayment") {
                    $("#termsOfPaymenterrormessage").css({"display": "none"});
                } else if (uid == "salesPersonContactNo") {
                    $("#SalesContNoerrormessage").css({"display": "none"});
                }
                return true;
            }
        }
        function savecheckUrl(uid, uname) {
            if (uname.length > 0) {
                if (!uname == '') {
                    //regular expression for URL
                    var urlpattern = /^(http|https)?:\/\/[a-zA-Z0-9-\.]+\.[a-z]{2,4}/;

                    if (urlpattern.test(uname)) {
                        $("#" + uid).css({"outline": "none"});
                        $("#custURLerrormessage").css({"display": "none"});
                        return true;
                    } else {
                        $("#custURLerrormessage").css({"display": "block"});
                        $("#" + uid).css({"outline": "1px solid red"});
                        return false;
                    }
                } else {
                    return true;
                }
            } else {
                $("#" + uid).css({"outline": "none"});
                $("#custURLerrormessage").css({"display": "none"});
                return true;
            }
        }

        function savenullFieldCheck(fieldId, fieldVal) {
            $("#" + fieldId).css({"outline": "none"});
            $("#salesNameerrormessage").css({"display": "none"});
            $("#attachTypeerrormessage").css({"display": "none"});
            $("#invoiceerrormessage").css({"display": "none"});
            return true;
        }

        $('#addDivisionName').on('click', function () {
            var checkedValue = $(this).prop('checked');
            if (checkedValue) {
                $(".addDivisionDiv").css({"visibility": "visible"});
                $(".addDivisionDiv").css({"display": "inline-block"});
            } else {
                $(".addDivisionDiv").css({"visibility": "hidden"});
                $(".addDivisionDiv").css({"display": "none"});
                $("#division_1").val('');
                var custDetailsLastID = $(".customerDetails").last().attr("id");
                var custDetailsLengthToRemove = custDetailsLastID.split('_')[1];
                var RemoveDivHeight = $("html").height();
                if ($(".customerDetails").length > 1) {
                    $("#RemoveAllDiv").css({"height": RemoveDivHeight, "display": "block"});
                    $("#RemoveAllDivFocus").attr("tabindex", -1).focus();
                    $("body").on('click', '#removeAllDivNo', function () {
                        $("#RemoveAllDiv").css({"display": "none"});
                        $("#addDivisionName").prop("checked", true);
                        $(".addDivisionDiv").css({"visibility": "visible"});
                        $(".addDivisionDiv").css({"display": "inline-block"});
                    });
                    $("body").on('click', '#removeAllDivYes', function () {
                        for (var q = 2; q <= custDetailsLengthToRemove; q++) {
                            $("#customerDetails_" + q).remove();
                        }
                        $("#RemoveAllDiv").css({"display": "none"});
                        $("#division_1").val('');
                    });
                }
            }
        });

        $("body").on('click', '.addDivisionBtn', function () {
            var appendDivision = '<div class="customerDetails" id="customerDetails_' + divisionLength + '" name="customerDetails_' + divisionLength + '">';
            var addDivisionDivi = '<p style="width:98%;margin:10px 5px 5px 10px;display:inline-block;" id="addDivisionDiv_' + divisionLength + '">';
            addDivisionDivi += '<label style="width: auto;">Division Name<span style="color: red;" for="fine">*</span></label>';
            addDivisionDivi += '<input type="text" name="customerDivisionListName" id="division_' + divisionLength + '" class="division textbox_new" value="">';
            addDivisionDivi += '<input type="button" value="Remove Division"  class="addDivsion removeDivision" ></p>';
            var addWorkLocation = '<div class="WorkLocation" id="WorkLocationDiv_' + divisionLength + '" name="WorkLocationDiv_' + divisionLength + '">';
            addWorkLocation += '<fieldset class="workLocationFieldset"><legend> Customer Work Location </legend>';
            addWorkLocation += '<input type="hidden" value="1" id="workLocationCount_' + divisionLength + '" name="workLocationCount_' + divisionLength + '" />';
            addWorkLocation += '<table class="customer_work_address" id="customerWorkAddress_' + divisionLength + '_1" name="customerWorkAddress_' + divisionLength + '_1"  border="0" >';
            addWorkLocation += '<tr>';
            addWorkLocation += '<th >Address<span style="color: red;" for="fine">*</span></th>';
            addWorkLocation += '<th >Short Code<span style="color: red;" for="fine">*</span></th>';
            addWorkLocation += '<th >Country<span style="color: red;" for="fine">*</span></th>';
            addWorkLocation += '<th >Region <span style="color: red;" for="fine">*</span></th>';
            addWorkLocation += '<th >City<span style="color: red;" for="fine">*</span></th>';
            addWorkLocation += '<th >Pincode<span style="color: red;" for="fine">*</span></th>';
            addWorkLocation += '<th >Working Hours/Day<span style="color: red;" for="fine">*</span></th>';
            addWorkLocation += '<th >Work Location</th>';
            addWorkLocation += '<th >Action</th></tr>';
            addWorkLocation += '<tr class="copyRow"><td colspan="8"><input type="checkbox" name="copyAddress" class="copyAddress" value="copy"/><span>Customer Work Location same as Customer Billing Address</span>&nbsp;&nbsp;<select class="billAddOptions"><option>-Select Address-</option></select></td></tr>';
            addWorkLocation += '<tr id="workLocationRow_' + divisionLength + '_1" name="workLocationRow_' + divisionLength + '_1">';
            addWorkLocation += '<td>';
            addWorkLocation += '<textarea cols="20" rows="2" name="customerWorkAddressNew_' + divisionLength + '_1"  id="customerWorkAddressNew_' + divisionLength + '_1"  maxlength="200"  minlength="10" onblur="textLimit(this,500); onkeyup="textLimit(this,200)" class=" required minlength maxlength resizableTextArea ui-resizable textbox_new customerWorkAddress" style="width: 221px; max-width: 221px;">';
            addWorkLocation += '</textarea></td>';

            addWorkLocation += '<td>';
            addWorkLocation += '<input type="text" value="" name="customerWorkShortCodeNew_' + divisionLength + '_1" id="customerWorkShortCode_' + divisionLength + '_1" class="custWorkShortCode" maxlength="15"/></td>';
            addWorkLocation += '<td><select name="customerWorkCountryNew_' + divisionLength + '_1" id="w_country_' + divisionLength + '_1" class="custWorkCountry required textbox_new country_select">';
            addWorkLocation += '<option value="">-- Select Country -- </option><c:forEach items="${countryList}" var="countryList" ><option value="${countryList.countryID}">${countryList.countryName}</option></c:forEach></select></td>';
            addWorkLocation += '<td><select name="customerWorkRegionNew_' + divisionLength + '_1" id="w_region_' + divisionLength + '_1" class="custWorkRegion textbox_new region_select"> <option value="">-- Select Region -- </option></select></td><td>';
            addWorkLocation += '<select name="customerWorkCityNew_' + divisionLength + '_1" id="w_city_' + divisionLength + '_1" class="customerWorkCity textbox_new"><option value="">-- Select City -- </option></select></td>';
            addWorkLocation += '<td><input type="text" width ="120px" name="customerWorkPincodeNew_' + divisionLength + '_1" id="w_pincode_' + divisionLength + '_1" class="custWorkPinCode"/></td>';
            addWorkLocation += '<td><input type="text" name="workingHoursPerDayNew_' + divisionLength + '_1" id="w_working_hours_' + divisionLength + '_1" class="workingHoursPerDay required textbox_new" value="" placeholder="HH:MM"></td>';
            
            addWorkLocation += '<td><select name="workingLocationIsCompanyLocationNew_'+ divisionLength + '_1" id="workingLocationIsCompanyLocationNew_'+ divisionLength + '_1" class="customerWorkLocationDefining textbox_new">';
            addWorkLocation += '<option value="">--Select Location--</option>';
            addWorkLocation += '<option value="0">Customer Location</option>';
            addWorkLocation += '<option value="1">Company Location</option>';
            addWorkLocation += '</select></td>';
            
            addWorkLocation += '<td align="center" id="adress_actionItems_' + divisionLength + '_1"><img class="add_location_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;"></td></tr>';
            addWorkLocation += '</table></fieldset></div>';

            var addbusinessContact = '<div class="businessContact" id="businessContactDiv_' + divisionLength + '" name="businessContactDiv_' + divisionLength + '"><fieldset class="businessContactFieldset"><legend> Business Contact </legend><input type="hidden" value="1" id="businessContactCount_' + divisionLength + '" name="businessContactCount_' + divisionLength + '" />';
            addbusinessContact += '<table class="business_contact" id="businessContact_' + divisionLength + '" name="businessContact_' + divisionLength + '" border="0" width="50%"><tr><th>Customer Contact person<span style="color: red;" for="fine">*</span></th> <th>Designation<span style="color: red;" for="fine">*</span></th><th>Customer Contact phone<span style="color: red;" for="fine">*</span></th>';
            addbusinessContact += '<th>Customer Contact Email <span style="color: red;" for="fine">*</span></th><th>Action</th></tr>';
            addbusinessContact += '<tr id="businessContactRow_' + divisionLength + '_1" name="businessContactRow_' + divisionLength + '_1" >';
            addbusinessContact += '<td><input type="text" name="customerContactPersonNew_' + divisionLength + '_1" id="customerContactPerson_' + divisionLength + '_1" class="custContactPerson textbox_new" value=""></td>';
            addbusinessContact += '<td><input type="text" name="customerDesignationNew_' + divisionLength + '_1" id="customerFinanceDesignation_' + divisionLength + '_1" class="custFinContactName textbox_new" value=""></td>';
            addbusinessContact += '<td><input type="text" name="customerContactPhoneNew_' + divisionLength + '_1" id="customerContactPhone_' + divisionLength + '_1" class="custContactPhone textbox_new" value=""></td>';
            addbusinessContact += '<td><input type="text" name="customerContactEmailNew_' + divisionLength + '_1" id="customerEmail_' + divisionLength + '_1" class="customerEmail required email textbox_new" value=""></td>';
            addbusinessContact += '<td align="center" id="adress_actionItems_' + divisionLength + '_1"><img class="add_business_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;"></td></tr>';
            addbusinessContact += '</table></fieldset></div>';
            
             var invoiceContact = '<div class="businessContact" id="businessContactDiv_' + divisionLength + '" name="businessContactDiv_' + divisionLength + '"><fieldset class="businessContactFieldset"><legend> Invoice Recipient</legend>';
            invoiceContact += '<table class="invoice_contact" id="invoice_contact_' + divisionLength + '" name="invoice_contact_' + divisionLength + '" border="0" width="100%" align="center">';
            invoiceContact += '<tbody><tr><td Style="padding-top:10px; width:100px;"><b>Invoice will be Emailed To</b><span style="color: red;" for="fine">*</span> </td>';
            invoiceContact += '<td><select name="invoiceTo_' + divisionLength + '" id="invoiceTo_' + divisionLength + '" class="custInvoice required textbox_new country_select" style="width:150px;">';
            invoiceContact += '<c:forEach items="${invoiceList}" var="invoiceObj" >';
            invoiceContact += '<option value="${invoiceObj.configKey}" ${selectedInvoice}> ${invoiceObj.configValue}</option></c:forEach></select>';
            invoiceContact += '</td></tr></tbody></table></fieldset></div>';


            var addfinanceContact = '<div class="financeContact" id="financeContactDiv_' + divisionLength + '" name="financeContactDiv_' + divisionLength + '"><fieldset class="financeContactFieldset"><legend> Finance Contact </legend><input type="hidden" value="1" id="financeContactCount_' + divisionLength + '" name="financeContactCount_' + divisionLength + '" />';
            addfinanceContact += '<table class="finance_contact" id="financeContact_' + divisionLength + '" name="financeContact_' + divisionLength + '" border="0" width="50%"><tr><th>Finance Contact person<span style="color: red;" for="fine">*</span></th><th>Designation<span style="color: red;" for="fine">*</span></th><th>Finance Contact phone<span style="color: red;" for="fine">*</span></th>';
            addfinanceContact += '<th>Finance Contact Email <span style="color: red;" for="fine">*</span></th><th>Action</th></tr><tr id="financeContactRow_' + divisionLength + '_1">';
            addfinanceContact += '<td><input type="text" name="customerFinanceContactPersonNew_' + divisionLength + '_1" id="customerFinanceContactPerson_' + divisionLength + '_1" class="custFinContactName textbox_new" value=""></td>';
            addfinanceContact += '<td><input type="text" name="customerFinanceDesignationNew_' + divisionLength + '_1" id="customerFinanceDesignation_' + divisionLength + '_1" class="custFinContactName textbox_new" value=""></td>';
            addfinanceContact += '<td><input type="text" name="customerFinanceContactPhoneNew_' + divisionLength + '_1" id="customerFinanceContactPhone_' + divisionLength + '_1" class="custFinContPhone textbox_new" value=""></td>';
            addfinanceContact += '<td><input type="text" name="customerFinanceContactEmailNew_' + divisionLength + '_1" id="customerFinanceEmail_' + divisionLength + '_1" class="custFinEmail required email textbox_new" value=""></td>';
            addfinanceContact += '<td align="center" class="add_row_action" id="adress_actionItems_' + divisionLength + '_1"><img src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;"></td>';
            addfinanceContact += '</tr></table></fieldset></div>';

            var addbillingAddress = '<div class="billingAddress" id="billingAddressDiv_' + divisionLength + '" name="billingAddressDiv_' + divisionLength + '">';
            addbillingAddress += '<fieldset class="billingAddressFieldset">';
            addbillingAddress += '<legend> Customer Billing Address<font color="red">*</font></legend>';
            addbillingAddress += '<input type="hidden" value="1" id="businessAddressCount_' + divisionLength + '" name="businessAddressCount_' + divisionLength + '" />';
            addbillingAddress += '<table class="customer_billing_address" id="customerBillingAddress_' + divisionLength + '" name="customerBillingAddress_' + divisionLength + '" border="0" width="50%">';
            addbillingAddress += '<tr>';
            addbillingAddress += '<th>Address<span style="color: red;" for="fine">*</span></th>';
            addbillingAddress += '<th>Short Code<span style="color: red;" for="fine">*</span></th>';
            addbillingAddress += '<th>Country<span style="color: red;" for="fine">*</span></th>';
            addbillingAddress += '<th>Region <span style="color: red;" for="fine">*</span></th>';
            addbillingAddress += '<th>City<span style="color: red;" for="fine">*</span></th>';
            addbillingAddress += '<th>Pincode<span style="color: red;" for="fine">*</span></th>';
            addbillingAddress += '<th class="gstHeader">State Code<span style="color: red;" for="fine">*</span></th>';
            addbillingAddress += '<th class="gstHeader">GSTIN/<span style="color: red;" for="fine">*</span></th>';
            addbillingAddress += '<th>Action</th></tr><tr class="business_row" id="business_row_' + divisionLength + '_1">';
            addbillingAddress += '<td>';
            addbillingAddress += '<textarea cols="20" rows="2" name="customerBillingAddressNew_' + divisionLength + '_1"  id="customerBillingAddressNew_' + divisionLength + '_1"  maxlength="200"  minlength="10" onblur="textLimit(this,200);';
            addbillingAddress += 'onkeyup="textLimit(this,200)" class=" required minlength maxlength resizableTextArea ui-resizable textbox_new custBillingAddress" style="width: 221px; max-width: 221px;">';
            addbillingAddress += '</textarea></td>';

            addbillingAddress += '<td>';
            addbillingAddress += '<input type="text" value="" name="customerBillingShortCodeNew_' + divisionLength + '_1" id="customerBillingShortCode_' + divisionLength + '_1" class="custBillingShortCode" maxlength="15"/></td>';
            addbillingAddress += '<td><select name="customerBillingCountryNew_' + divisionLength + '_1" class="custBillingCountry textbox_new country_select" id="b_country_' + divisionLength + '_1" class="required textbox_new country_select"><option value="">-- Select Country -- </option>';
            addbillingAddress += '<c:forEach items="${countryList}" var="countryList" ><option value="${countryList.countryID}">${countryList.countryName}</option></c:forEach></select></td>';
            addbillingAddress += '<td><select name="customerBillingRegionNew_' + divisionLength + '_1" id="b_region_' + divisionLength + '_1" class="custBillingRegion textbox_new region_select"><option value="">-- Select Region -- </option></select></td>';
            addbillingAddress += '<td><select name="customerBillingCityNew_' + divisionLength + '_1" id="b_city_' + divisionLength + '_1" class="custBillingCity textbox_new"><option value="">-- Select City -- </option></select></td>';
            addbillingAddress += '<td><input type="text" name="customerBillingPincodeNew_' + divisionLength + '_1" id="b_pincode_' + divisionLength + '_1" class="custBillingPincode"/></td>';
            addbillingAddress += '<td align="center" class="gstRow" id ="gstColumn_' + divisionLength + '_1"><input type="text" name="customerBillingGstCodeNew_' + divisionLength + '_1" id="b_gst_' + divisionLength + '_1" readonly class="custBillingGstCode" style="display:none"/></td>';
            addbillingAddress += '<td align="center" class="gstRow" id ="gstColumn1_' + divisionLength + '_1"><input type="text" name="customerBillingGstNumberNew_' + divisionLength + '_1" id="customerBillingGstNumberNew_' + divisionLength + '_1" class="custBillingGstNumber"/></td>';
            addbillingAddress += '<td align="center"  id="adress_actionItems_' + divisionLength + '_1"><img class="add_billing_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;"></td>';
            addbillingAddress += '</tr></table></fieldset></div>';

            appendDivision += addDivisionDivi;
            appendDivision += addbillingAddress;
            appendDivision += addWorkLocation;
            appendDivision += addbusinessContact;
            appendDivision += addfinanceContact;
            appendDivision += invoiceContact;

            appendDivision += '</div>';
            /***********/
            $(".content_page").append(appendDivision).focus();
            $("#customerDetails_" + divisionLength).attr("tabindex", -1).focus();
            divisionLength++;
        });

        $("body").on('click', 'input.removeDivision', function () {
            var DivHeight = $("html").height();
            $("#RemoveIndividualDiv").css({"height": DivHeight, "display": "block"});
            $("#RemoveIndividualDivFocus").attr("tabindex", -1).focus();
            var closestDivDelete = $(this).closest(".customerDetails");
            $("body").on('click', '#removeDivYes', function () {
                closestDivDelete.remove();
                $("#RemoveIndividualDiv").css({"display": "none"});
            });
            $("body").on('click', '#removeDivNo', function () {
                $("#RemoveIndividualDiv").css({"display": "none"});
            });
        });
        //Customer Billing Address
        $("body").on('change', '.custBillingShortCode', function () {
            var tableLength = $(".customer_billing_address").length;
            //                alert(tableLength);
            var shortCode = $(this);
            var tableCount = 1;
            for (tableCount = 1; tableCount <= tableLength; tableCount++)
            {
                $("#customerBillingAddress_" + tableCount + " tr .custBillingShortCode").each(function () {
                    var checkCode = $(this);
                    var currentCode = checkCode.val();
                    var cCode = shortCode.val();
                    var currentId = checkCode.parent().parent().attr('id');
                    var cCodeId = shortCode.parent().parent().attr('id');
                    if (currentCode === cCode && currentId !== cCodeId)
                    {
                        $(".custBillingShortCode").css("border", "1px solid red");
                        $("#errShortCode").show();
                        $("#errShortCode").text("Same short code");
                        return false;
                    }
                    else
                    {
                        $(".custBillingShortCode").css("border", "1px solid #C4D1E0");
                        $("#errShortCode").hide();
                    }
                });

            }
        });

        //Customer Work Location
        $("body").on('keyup', '.custWorkShortCode', function () {
            var tableLength = $(".customer_work_address").length;
            var shortCode = $(this);
            var tableCount = 1;
            for (tableCount = 1; tableCount <= tableLength; tableCount++)
            {
                $("#customerWorkAddress_" + tableCount + " tr .custWorkShortCode").each(function () {
                    var checkCode = $(this);
                    var currentCode = checkCode.val();
                    var cCode = shortCode.val();
                    var currentId = checkCode.parent().parent().attr('id');
                    var cCodeId = shortCode.parent().parent().attr('id');
                    if (currentCode === cCode && currentId !== cCodeId)
                    {
                        $(checkCode).css("border", "1px solid red");
                        $(shortCode).css("border", "1px solid red");
                        $("#errShortCode1").show();
                        $("#errShortCode1").text("Same work location short code");
                        return false;
                    }
                    else
                    {
                        $(checkCode).css("border", "1px solid #C4D1E0");
                        $(shortCode).css("border", "1px solid #C4D1E0");
                        $("#errShortCode1").hide();
                    }
                });

            }
        });
        
         $("body").on('keyup', '.custWorkShortCode', function(){
                    var tableLength = $(".customer_work_address").length;
    //                alert(tableLength);
                    var shortCode = $(this);
                    var tableCount =1;
                    for(tableCount=1; tableCount<=tableLength; tableCount++)
                    {
                    $("#customerBillingAddress_"+tableCount+"_1 tr .custWorkShortCode").each(function(){
                       var checkCode = $(this);
                       var currentCode = checkCode.val();
                       var cCode = shortCode.val();
                       var currentId = checkCode.parent().parent().attr('id');
                       var cCodeId = shortCode.parent().parent().attr('id');
                       if(currentCode === cCode && currentId !== cCodeId)
                       {
                           $(".custWorkShortCode").css("border", "1px solid red");
                           $("#errShortCode1").show();
                           $("#errShortCode1").text("Same work location short code");
                           return false;
                       }
                       else
                       {
                           $(".custWorkShortCode").css("border", "1px solid #C4D1E0");
                           $("#errShortCode1").hide();
                       }
                    });
               	
            }
             });
    });
</script>
