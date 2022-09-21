<%-- 
    Document   : customerList
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Add Customer Division</title>
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
            opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1
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
        .customer_billing_address td,.customer_work_address td,.business_contact td,.finance_contact td{
                padding:0px;
                width:auto;
        }
        .customer_billing_address tr,.customer_work_address tr,.business_contact tr,.finance_contact tr{
                padding:0px;
                width:auto;
        }
        .customer_billing_address th,.customer_work_address th,.business_contact th,.finance_contact th{
                padding:0px;
                width:auto;
        }
        .customer_billing_address td input[type='text'],.customer_work_address td input[type='text'],.business_contact td input[type='text'],.finance_contact td input[type='text']
        {
            width:95%;
            height:20px;
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
        #customerBasicDetails,#customerDetails{
                border:1px solid grey;
                margin:0px 15px 15px 20px;
                border-top:0px;
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
       #workLocationFieldset,
       #businessContactFieldset,
       #financeContactFieldset,
       #billingAddressFieldset{
            margin: 12px 0px 0px 0px;
            padding: 0px;
            border: 0px;
        }
        table.customer_work_address,
        table.customer_billing_address,
        table.business_contact,
        table.finance_contact{
              width:100%;
          }
        table.customer_work_address tbody tr td,
        .customer_billing_address tbody tr td{
            width:16%;
        }
        .business_contact tbody tr th,
        .finance_contact tbody tr th{
            width:24%;
        }
        table.customer_work_address tbody tr td:last-child,
        .customer_billing_address tbody tr td:last-child{
       
            width:9%;
        }
        .business_contact tbody tr th:last-child,
        .finance_contact tbody tr th:last-child{
            width:10%;
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
        #listIcon{
            font-weight:bold; 
            float:right; 
            margin: -33px 59px 0px 0px;
            width: 12px;
        }
        #listName{
            font-weight:bold; 
            float:right; 
            margin: -30px -20px 0px 0px;
            text-decoration: none;
            color: #666666;
        }
        p.formInput {
            width: 200px;
        }
        a#attachedFileValue {
            overflow-wrap: break-word;
        }
        
    </style>
</head>
<body onload="">
	<div id="loadingDiv" style="position:absolute;width:100%;height:1400px;background-color:#777777;display: block; top: 0pt; left: 0pt; ">
            <div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center">
                <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
            </div>
	</div>
	<div id="container">
                <div align="center" style="margin:15px 0px -20px 0px;" id="successDiv"><font color="green" size="4">${Success_message}</font></div>
		<div class="container_inner" style="margin: 15px 0px;width:300px;">
                    <p class="page_heading"><span class="heading">${customerData[0].customerName} - Add Division</span> </p>
		</div>
                <div>
                    <img src="/iDeal_application/css/images/icon_list.png" title="Information" alt="Information" id="listIcon"></img>
                    <p class="divisoin_list">
                        <a id="listName" href="customerDivisionList.htm?parentId=${parentId}">Division List</a>
                    </p>
                </div>
		<div class="notice_page">
                    <img src="/iDeal_application/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon"></img>
                    <img src="/iDeal_application/css/images/tick.png" title="Information" alt="Information" id="tickIcon"></img>
                    
                    <span class="InfoText">Fields marked in * are mandatory.</span>
		</div>
                <%--<c:if test="${customAdd == 0}">--%>
                    <!--<p style="color:red; font-weight: bold;">Customer Details Not Saved</p>-->
                <%--</c:if>--%>
               
		<form action="saveCustomer.htm" name="formCustomerDetails" id="formCustomerDetails" method="post" enctype="multipart/form-data">
			<div class="formContent_new" id="datadisplay" style="height:auto">
				<div id="menu">
                                    <ul>
                                        <li class="showCustomerBasicDetails"><a>Basic Details</a></li>
                                        <li class="showCustomerDetails"><a>Division/Contact Details</a></li>
                                    </ul>
				</div>
				<div id="customerBasicDetails" class="customerBasicDetails">
                                    <table id="customerTable" border="0" align="center">
                                        <tr>
                                            <td><label>Customer Name<font color="red">*</font></label></td>
                                            <td><input readonly type="text" name="customerName" id="customerName" class="required textbox_new" value="${customerData[0].customerName}">
                                                <p id="nameerrormessage" class="errorMessage">Please enter alphabets only</p>
                                            </td>
                                            <td><label class="">BDM/SalesPerson Name<font color="red">*</font></label></td>
                                            <td>
                                                <select name="salesPerson" id="salesPerson" class="required textbox_new ">
                                                    <option value="">-- Select BDM -- </option>
                                                    <c:forEach items="${salesPerson}" var="salesPerson" >
                                                        <c:set var="selsalesPerson" value=""></c:set>
                                                        <c:if test="${customerData[0].salesPersonRefId == salesPerson.salesManId}">
                                                            <c:set var="selsalesPerson" value="selected"></c:set>
                                                        </c:if>
                                                        <option value="${salesPerson.salesManId}" ${selsalesPerson}>${salesPerson.employeeName} - ${salesPerson.employeeNumber}</option>
                                                    </c:forEach>
                                                </select>
                                                <p id="salesNameerrormessage" class="errorMessage">Please select sales Person name</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label class="">About Customer</label></td>
                                            <td><textarea cols="20" rows="2" name="aboutCustomer"  id="aboutCustomer"  maxlength="500"  minlength="10" onblur="textLimit(this,500);" onkeyup="textLimit(this,500)" class=" required minlength maxlength resizableTextArea ui-resizable textbox_new" >${customerData[0].aboutCustomer}</textarea>
                                                <p id="AbtCusterrormessage" class="errorMessage">About customer should not be null</p>
                                            </td>
                                            <td><label class="">BDM/Sales Person Contact No<font color="red">*</font></label></td>
                                            <td>
                                                <input type="text" name="salesPersonContactNo" id="salesPersonContactNo" class="numberDE textbox_new " value="${customerData[0].salesPersonContactNo}">
                                                <p id="SalesContNoerrormessage" class="errorMessage">Please enter numerics only</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label class="">Customer URL</label></td>
                                            <td><input type="text" name="customerURL" id="customerURL" class="url textbox_new" value="${customerData[0].customerURL}" placeholder="http://www.hindujatech.com">
                                                <p id="custURLerrormessage" class="errorMessage">Please enter valid URL</p>
                                            </td>
                                            <td><label class="">Attachment type<font color="red">*</font></label></td>
                                            <td>
                                                <select name="attachmentType" id="attachmentType" class="textbox_new formInput formInput">
                                                    <option value="">-- Select attachment type -- </option>
                                                    <c:forEach items="${attachmentType}" var="attachmentType" >
                                                        <c:set var="selattachmentType" value=""></c:set>
                                                        <c:if test="${customerData[0].attachmentType == attachmentType.configKey}">
                                                            <c:set var="selattachmentType" value="selected"></c:set>
                                                        </c:if>
                                                        <option value="${attachmentType.configKey}" ${selattachmentType}> ${attachmentType.configKey}</option>

                                                    </c:forEach>
                                                </select>
                                                <p id="attachTypeerrormessage" class="errorMessage">Please select attachment type</p>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><label>Terms of Payment(in Days)<font color="red">*</font></label></td>
                                            <td>
                                                <input type="text" name="termsOfPayment" id="termsOfPayment" class="required textbox_new" value="${customerData[0].termsOfPayment}">
                                                <p id="termsOfPaymenterrormessage" class="errorMessage">Please enter numerics between 0 and 365</p>
                                            </td>
                                            <td><label class="">Attachments(NDA/PO/MSA)<font color="red">*</font></label></td>
                                            <td><input id="attachmentValue" type="file" name="attachmentValue" value="" width="10" />
                                                <p class="formInput">
                                                    <c:if test="${customerData[0].attchedFileName == null}">
                                                        No file Found                  
                                                    </c:if>
                                                    <c:if test="${customerData[0].attchedFileName != null}">
                                                        <a href="attachmentDownload.htm?fileName=${customerData[0].fileName}&fileType=${customerData[0].fileType}" target="_blank" name="file" id="attachedFileValue">${customerData[0].attchedFileName}</a>
                                                        <input type="hidden" name="attchedFileName" id="attchedFileName" value="${customerData[0].fileinsertId}" />
                                                   </c:if> 
                                                </p>
                                                <p id="attachFileerrormessage" class="errorMessage">Please upload a file type(.pdf/.docx/.xlsx)</p>
                                                <p id="attachFileerrormessage1" class="errorMessage">File size should be less than 3 MB</p>
                                            </td> 

                                        </tr>
                                    </table>
                                    <div class="buttonContinue" id="buttonContinue">
                                         <input type="hidden" name="parentId" id="parentId" class="buttonStatus textbox_new" value="${parentId}"> 
                                        <input type="button" name="customerBasicDetailsSave" id="customerBasicDetailsSave" value="Add Division Details" style="width: 150px; margin: 15px 0px 0px 0px;cursor:pointer;" class="qualitysave">
                                    </div>
				</div>
				<div class="customerDetails" id="customerDetails">
                                    <p style="margin:15px 10px 5px 10px;">    
                                        <label style="width: auto; margin: 2px 45px 0px 0px;">Division <span style="color: red;" for="fine">*</span></label>
                                        <input type="text" name="customerDivision" id="division" class="division textbox_new" value="">
                                        <input type="hidden" name="buttonStatus" id="buttonStatus" class="buttonStatus textbox_new" value="">
                                    </p>
                                    <div class="WorkLocation" id="customerWorkLocation">
                                        <fieldset id="workLocationFieldset">
                                            <legend> Customer Work Location </legend>            
                                            <input type="hidden" value="1" id="workLocationCount" name="workLocationCount" />
                                            <table class="customer_work_address" border="0" >
                                                <tr>
                                                    <th >Address<span style="color: red;" for="fine">*</span></th>
                                                    <th >Country<span style="color: red;" for="fine">*</span></th>
                                                    <th >Region <span style="color: red;" for="fine">*</span></th>
                                                    <th >City<span style="color: red;" for="fine">*</span></th>
                                                    <th >Pincode<span style="color: red;" for="fine">*</span></th>
                                                    <th >Working Hours/Day<span style="color: red;" for="fine">*</span></th>
                                                    <th >Action</th>
                                                </tr>
                                                <tr id="workLocation_row_1">
                                                    <td>
                                                        <input type="text" width="150px" value="" name="customerWorkAddressNew" id="w_adress_1" class="customerWorkAddress" />
                                                    </td>
                                                    <td>
                                                        <select name="customerWorkCountryNew" id="w_country_1" class="custWorkCountry required textbox_new country_select">
                                                            <option value="">-- Select Country -- </option>
                                                            <c:forEach items="${countryList}" var="countryList" >
                                                                <option value="${countryList.countryID}">${countryList.countryName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select name="customerWorkRegionNew" id="w_region_1" class="custWorkRegion textbox_new region_select"> 
                                                        <option value="">-- Select Region -- </option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select name="customerWorkCityNew" id="w_city_1" class="customerWorkCity textbox_new">
                                                        <option value="">-- Select City -- </option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" width ="120px" name="customerWorkPincodeNew" id="w_pincode_1" class="custWorkPinCode"/> 
                                                    </td>
                                                    <td>
                                                        <input type="text" name="workingHoursPerDayNew" id="w_working_hours_1" class="workingHoursPerDay required textbox_new" value="" placeholder="HH:MM">
                                                    </td>
                                                    <td align="center" id="adress_actionItems_1">
                                                        <img class="add_location_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                    </td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </div>
                                    <div class="businessContact" id="businessContact">
                                        <fieldset class="businessContactFieldset" id="businessContactFieldset">
                                            <legend> Business Contact </legend>            
                                            <input type="hidden" value="1" id="businessContactCount" name="businessContactCount" />
                                            <table class="business_contact" border="0" width="50%">
                                                <tr>
                                                    <th>Customer Contact person<span style="color: red;" for="fine">*</span></th>
                                                    <th>Customer Contact phone<span style="color: red;" for="fine">*</span></th>
                                                    <th>Customer Contact Email <span style="color: red;" for="fine">*</span></th>
                                                    <th>Action</th>
                                                </tr>
                                                <tr id="business_contact_row_1">
                                                    <td>
                                                        <input type="text" name="customerContactPersonNew" id="customerContactPerson" class="custContactPerson textbox_new" value="">
                                                    </td>
                                                    <td>
                                                        <input type="text" name="customerContactPhoneNew" id="customerContactPhone" class="custContactPhone textbox_new" value="">
                                                    </td>
                                                    <td>
                                                        <input type="text" name="customerContactEmailNew" id="customerEmail" class="customerEmail required email textbox_new" value="">
                                                    </td>
                                                    <td align="center" id="adress_actionItems_1">
                                                        <img class="add_business_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                    </td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </div>
                                    <div class="financeContact" id="financeContact">
                                        <fieldset id="financeContactFieldset" class="financeContactFieldset">
                                            <legend> Finance Contact </legend>            
                                            <input type="hidden" value="1" id="financeContactCount" name="financeContactCount" />
                                            <table class="finance_contact" border="0" width="50%">
                                                <tr>
                                                    <th>Finance Contact person<span style="color: red;" for="fine">*</span></th>
                                                    <th>Finance Contact phone<span style="color: red;" for="fine">*</span></th>
                                                    <th>Finance Contact Email <span style="color: red;" for="fine">*</span></th>
                                                    <th>Action</th>
                                                </tr>
                                                <tr id="finance_contact_row_1">
                                                    <td>
                                                        <input type="text" name="customerFinanceContactPersonNew" id="customerFinanceContactPerson" class="custFinContactName textbox_new" value="">
                                                    </td>
                                                    <td>
                                                        <input type="text" name="customerFinanceContactPhoneNew" id="customerFinanceContactPhone" class="custFinContPhone textbox_new" value="">
                                                    </td>
                                                    <td>
                                                        <input type="text" name="customerFinanceContactEmailNew" id="customerFinanceEmail" class="custFinEmail required email textbox_new" value="">
                                                    </td>
                                                    <td align="center" class="add_row_action" id="adress_actionItems_1">
                                                        <img src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                    </td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </div>
                                    <div class="billingAddress" id="billingAddress">
                                        <fieldset id="billingAddressFieldset" class="billingAddressFieldset">
                                            <legend> Customer Billing Address<font color="red">*</font></legend>
                                            <input type="hidden" value="1" id="businessAddressCount" name="businessAddressCount" />
                                            <table class="customer_billing_address" border="0" width="50%">
                                                <tr>
                                                    <th>Address<span style="color: red;" for="fine">*</span></th>
                                                    <th>Country<span style="color: red;" for="fine">*</span></th>
                                                    <th>Region <span style="color: red;" for="fine">*</span></th>
                                                    <th>City<span style="color: red;" for="fine">*</span></th>
                                                    <th>Pincode<span style="color: red;" for="fine">*</span></th>
                                                    <th>Action</th>
                                                </tr>
                                                <tr id="business_row_1">
                                                    <td>
                                                        <input type="text" value="" name="customerBillingAddressNew" id="b_adress_1" class="custBillingAddress"/>
                                                    </td>
                                                    <td>
                                                        <select name="customerBillingCountryNew" class="custBillingCountry textbox_new country_select" id="b_country_1" class="required textbox_new country_select"> 
                                                            <option value="">-- Select Country -- </option>
                                                            <c:forEach items="${countryList}" var="countryList" >
                                                                    <option value="${countryList.countryID}">${countryList.countryName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select name="customerBillingRegionNew" id="b_region_1" class="custBillingRegion textbox_new region_select">
                                                            <option value="">-- Select Region -- </option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <select name="customerBillingCityNew" id="b_city_1" class="custBillingCity textbox_new">
                                                            <option value="">-- Select City -- </option>
                                                        </select>
                                                    </td>
                                                    <td>
                                                        <input type="text" name="customerBillingPincodeNew" id="b_pincode_1" class="custBillingPincode"/> 
                                                    </td>
                                                    <td align="center"  id="adress_actionItems_1">
                                                        <img class="add_billing_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                    </td>
                                                </tr>
                                            </table>
                                        </fieldset>
                                    </div>
                                    <div class="buttonAlignment">
                                        <div class="buttonAlignment" id="btnGroup">
                                            <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}" />
                                            <input type="hidden" name="customerID" id="customerID" value="" />
                                            
                                            <input type="button" name="btnCancel" id="btnCancel" value="Back" style="cursor:pointer;" class="qualityback" onclick="javascript: location.href='customerDivisionList.htm?customerName=${customerData[0].customerName}'">
                                            <input type="button" name="customerSaveButton" id="saveBtn" value="Save" style="width: 90px; cursor:pointer;" class="qualitysave">
                                            <!--<input type="submit" name="customerSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" style="width: 90px;" onclick="return disableSubmit('saveBtn','submitBtn','btnCancel');">-->
                                            <input type="button" name="customerSubmitButton" id="submitBtn" value="Submit" class="qualitysubmit" style="width: 90px; cursor:pointer" >                                            
                                        </div>
                                    </div> 
				</div>
			</div>
		</form>
	</div>
</body>
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

        function stopLoading(){
            if(ns4){loadingDivObj.visibility="hidden";}
            else if (ns6||ie4) loadingDivObj.display="none";
        }

        function startLoading(){
            if(ns4){loadingDivObj.visibility="visible";}
            else if (ns6||ie4) loadingDivObj.display="block";
        }
        
        $(document).ready(function(){
            $(".showCustomerBasicDetails").addClass("active");
            $("#customerDetails").hide();


            $(".showCustomerBasicDetails").click(function(){
                $(".showCustomerDetails").removeClass("active");
                $(this).addClass("active");
                $("#customerDetails").hide();
                $("#customerBasicDetails").show();
            });
            $(".showCustomerDetails").click(function(){
                $(".showCustomerBasicDetails").removeClass("active");
                $(this).addClass("active");
                $("#customerDetails").show();
                $("#customerBasicDetails").hide();
            });
      $('#saveBtn').on('click',function(){
               $('#buttonStatus').val('s');
               if(saveBasicDetails()){
                    saveButtonValidation();
                }
                else{
                  
                }
          });
          stopLoading();
         $( "body" ).on( "change", '.country_select', function() {
                getRegions(this.value,this.id); 
            });
             function saveBasicDetails(){
                var savecustNameVal=document.getElementById("customerName").value;
                var savecustNameID=document.getElementById("customerName").id;
                var savecustNameStatus=allLetter(savecustNameID,savecustNameVal);
                /*******************/
                var savetermsOfPaymentVal=document.getElementById("termsOfPayment").value;
                var savetermsOfPaymentID=document.getElementById("termsOfPayment").id;
                var savetermsOfPaymentStatus=saveallNumericTOP(savetermsOfPaymentID,savetermsOfPaymentVal);   

                var savecustomerURLVal=document.getElementById("customerURL").value;
                var savecustomerURLID=document.getElementById("customerURL").id;
                var savecustomerURLStatus=savecheckUrl(savecustomerURLID,savecustomerURLVal);
                
                var savesalesPersonVal=document.getElementById("salesPerson").value;
                var savesalesPersonID=document.getElementById("salesPerson").id;
                var savesalesPersonStatus=savenullFieldCheck(savesalesPersonID,savesalesPersonVal);

                var saveattachmentTypeVal=document.getElementById("attachmentType").value;
                var saveattachmentTypeID=document.getElementById("attachmentType").id;
                var saveattachmentTypeStatus=savenullFieldCheck(saveattachmentTypeID,saveattachmentTypeVal);              


                var savesalesPersonContactNoVal=document.getElementById("salesPersonContactNo").value;
                var savesalesPersonContactNoID=document.getElementById("salesPersonContactNo").id;
                var savesalesPersonContactNoStatus=saveallNumeric(savesalesPersonContactNoID,savesalesPersonContactNoVal);   

                var saveattachmentVal=document.getElementById("attachmentValue").value;
                var saveattachmentID=document.getElementById("attachmentValue").id;
                var saveattachmentStatus=saveattachmentTypeValidation(saveattachmentID,saveattachmentVal);

                if(savecustNameStatus && 
                    savetermsOfPaymentStatus && 
                    savesalesPersonContactNoStatus && saveattachmentStatus && savecustomerURLStatus){
                    $(".showCustomerBasicDetails").removeClass("active");
                    $(".showCustomerDetails").addClass("active");
                    $("#customerDetails").show();
                    $("#customerBasicDetails").hide();
                    return true;
                }else{

                    if(!savecustNameStatus){
                        $("#customerName").trigger('focus');
                    }else if(!savetermsOfPaymentStatus){
                        $("#termsOfPayment").trigger('focus');
                    }else if(!savecustomerURLStatus){
                        $("#customerURL").trigger('focus');
                    }else if(!savesalesPersonContactNoStatus){
                        $("#salesPersonContactNo").trigger('focus');
                    }else if(!saveattachmentStatus){
                        $("#attachmentValue").trigger('focus');
                    }
                    $(".showCustomerDetails").removeClass("active");
                    $(".showCustomerBasicDetails").addClass("active");
                    $("#customerDetails").hide(); 
                    $("#customerBasicDetails").show();
                    return false;
                }
                
                
                /*******************/
                
                if(savecustNameStatus){
                    $(".showCustomerBasicDetails").removeClass("active");
                    $(".showCustomerDetails").addClass("active");
                    $("#customerDetails").show();
                    $("#customerBasicDetails").hide();
                    return true;
                }else{
                    if(!savecustNameStatus){
                        $("#customerName").trigger('focus');
                        $(".showCustomerDetails").removeClass("active");
                        $(".showCustomerBasicDetails").addClass("active");
                        $("#customerDetails").hide(); 
                        $("#customerBasicDetails").show();
                        return false;
                    }
            }
        }
             function saveButtonValidation(){
                var custWorkPinCodeStatus=saveTableNumericCheck("custWorkPinCode");
                var custworkingHoursPerDayStatus=saveTableTimeCheck("workingHoursPerDay");
                var custContactPersonStatus=saveTableAlphabetCheck("custContactPerson");
                var custContactPhoneStatus=saveTableNumericCheck("custContactPhone");
                var customerEmailStatus=saveTableEmailCheck("customerEmail");
                var custFinContPersonStatus=saveTableAlphabetCheck("custFinContactName");
                var custFinContPhoneStatus=saveTableNumericCheck("custFinContPhone");
                var custFinEmailStatus=saveTableEmailCheck("custFinEmail");
                var custBillingPincodeStatus=saveTableNumericCheck("custBillingPincode");
                if(  custWorkPinCodeStatus && customerEmailStatus && custContactPersonStatus &&
                     custContactPhoneStatus && custFinEmailStatus && custFinContPersonStatus &&
                     custFinContPhoneStatus && custworkingHoursPerDayStatus &&
                     custBillingPincodeStatus){
//                    var custName = $("#customerName").val();
//                    $.ajax({
//                      url : "getDuplicateCustomerDivisionName.htm?customerName=" + custName,
//                              type : "POST",
//                              success : function(data) {
//                                              alert(data);
//                                              if (data == 0) {
//                                                              $("#formCustomerDetails").submit();
//                                                              startLoading();
//                                              } else {
//                                                              $("#dupError").html("<span style='color:red;'><b>Please enter unique customer name.</b></span>");
//                                                              $("#customerName").trigger('focus');
//                                                              $(".showCustomerDetails").removeClass("active");
//                                                              $(".showCustomerBasicDetails").addClass("active");
//                                                              $("#customerDetails").hide();
//                                                              $("#customerBasicDetails").show();
//                                                              }
//                                              }
//                              }
//                      );
                        $("#formCustomerDetails").submit();
                        startLoading();
                }else{
                    if(!divisionStatus){
                        $("#division").trigger('focus');
                    }
                }
         }
        function getRegions(selectedValue,objId) {
                var category = objId.substring(0, 1);
                var objIdRowValue = objId.substring(10);
                 var id;
                if(category === 'w'){
                 id ="w_region_"+objIdRowValue;
                }else if(category === 'b'){
                  id ="b_region_"+objIdRowValue;
                }
             $("#"+id).html("<option value=''>-- Select Region --</option>");
             if(id != "") {
                 $.ajax({
                     url: './getRegionList.htm',
                     type: "POST",
                     async: false,
                     data: ({countryId:selectedValue,id:id}),
                     success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                     }
                 });
             }
            }
             $( "body" ).on( "change", '.region_select', function() {
                getCities(this.value,this.id);
            });
            function getCities(selectedValue,objId) {
                var selectedCountryValue;
                var category = objId.substring(0, 1);
                var objIdRowValue = objId.substring(9);
                var id;
                if(category === 'w'){
                    id ="w_city_"+objIdRowValue;
                    selectedCountryValue = $("#w_country_"+objIdRowValue).val();
                }else if(category === 'b'){
                    id ="b_city_"+objIdRowValue;
                    selectedCountryValue = $("#b_country_"+objIdRowValue).val();
                }
                $("#"+id).html("<option value=''>-- Select City --</option>");
                
                if(id != "" && selectedCountryValue != "") {
                    $.ajax({
                        url: './getCityList.htm',
                        type: "POST",
                        async: false,
                        data: ({countryId:selectedCountryValue,regionId:selectedValue,id:id}),
                        success: function(ajaxObj) {
                            var returnData = eval(ajaxObj);
                            returnData;
                        }
                    });
                }
            }
            
            $( "body" ).on( "click", '.add_row_action', function() {
                addworkFinanceContactRow(this);
            });
            $( "body" ).on( "click", '.delete_finance_row', function() {
                var Row1 = $(this).parent().parent();
                Row1.remove();
            });
             function addworkFinanceContactRow(rowObject){
		var Row1 = $(rowObject).parent().parent();
		var rowData = "";
		var financeContactCount = $('#financeContactCount').val();
		var cnt = parseInt(financeContactCount) + 1; 
		rowData +='<td style="text-align:left">';
		rowData +='<input type="text" name="customerFinanceContactPersonNew" id="customerFinanceContactPerson_'+cnt+'" class="custFinContactName textbox_new" value="">';
		rowData +='</td>';
		rowData +='<td style="text-align:left">';
		rowData +='<input type="text" name="customerFinanceContactPhoneNew" id="customerFinanceContactPhone_'+cnt+'" class="custFinContPhone textbox_new" value="">';
		rowData +='</td>';
		rowData +='<td  style="text-align:left">';
		rowData +='<input type="text" name="customerFinanceContactEmailNew" id="customerFinanceEmail_'+cnt+'" class="custFinEmail required email textbox_new" value="">';
		rowData +='<td align="center" id="adress_actionItems_'+cnt+'">';
		rowData +='<img class="add_row_action" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
 		rowData += '&nbsp;<img class="delete_finance_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;"/>';              
		rowData +='</td>';
		$(Row1).after('<tr id="finance_contact_row_'+ cnt + '">' + rowData + '</tr>');
                $('#financeContactCount').val(cnt);
            }
            
            $( "body" ).on( "click", '.add_billing_row', function() {
                addRow(this);
            });
            $( "body" ).on( "click", '.delete_billing_row', function() {
                var Row1 = $(this).parent().parent();
                Row1.remove();
            });
            
            function addRow(rowObject) {
		var theRow = $(rowObject).parent().parent();
                var rowData = "";
                var businessAddressCount = $('#businessAddressCount').val();
                var cnt = parseInt(businessAddressCount) + 1;
                rowData +='<td align="center"><input type="text" name="customerBillingAddressNew"  id="b_adress_'+cnt+'" class="custBillingAddress"></td>';
                rowData +='<td  align="center">';
                rowData +='<select name="customerBillingCountryNew" id="b_country_'+cnt +'" class="custBillingCountry  required textbox_new country_select" onchange="getRegions(this.value,this.id)">';
                rowData +='<option value="">-- Select Country -- </option>';
                rowData +='<c:forEach items="${countryList}" var="countryList" >';
                rowData +='<option value="${countryList.countryID}">${countryList.countryName}</option>';
                rowData +='</c:forEach>';
                rowData +='</select>';
                rowData +='</td>';
                rowData +='<td  align="center">';
				rowData +='<select name="customerBillingRegionNew" id="b_region_'+cnt+'" class="custBillingRegion  textbox_new region_select" onchange="getCities(this.value,this.id);">';
                rowData += '<option value="">-- Select Region -- </option></select></td>';
                rowData += '<td  align="center">';
				rowData += '<select name="customerBillingCityNew" id="b_city_'+cnt+'" class="custBillingCity textbox_new">';
				rowData +='<option value="">-- Select City -- </option></select></td>';
                rowData +='<td align="center"><input type="text" name="customerBillingPincodeNew" id="b_pincode_'+cnt+'" class="custBillingPincode"></td>';
                rowData += '<td align="center"  id="adress_actionItems_'+ cnt +'">';
                rowData += '<img class="add_billing_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                rowData += '&nbsp;<img class="delete_billing_row"src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;"/>';              
                rowData += '</td>';
                  $(theRow).after('<tr id="business_row_'+ cnt +'">' + rowData + '</tr>');
                  $('#businessAddressCount').val(cnt);
           }
           
            function submitForm(){
                document.getElementById("reportForm").action="customerList.htm";
                document.reportForm.submit();
                startLoading();
            }

            $( "body" ).on( "click", '.add_business_row', function() {
                addworkBusinessContactRow(this);
            });
            $( "body" ).on( "click", '.delete_business_row', function() {
                var Row1 = $(this).parent().parent();
                Row1.remove();
            });

            function addworkBusinessContactRow(rowObject){
		var Row1 = $(rowObject).parent().parent();
		var rowData = "";
		var businessContactCount = $('#businessContactCount').val();
		var cnt = parseInt(businessContactCount) + 1; 
		rowData +='<td style="text-align:left">';
		rowData +='<input type="text" name="customerContactPersonNew" id="customerContactPerson_'+cnt+'" class="custContactPerson  textbox_new" value="">';
		rowData +='</td>';
		rowData +='<td style="text-align:left">';
		rowData +='<input type="text" name="customerContactPhoneNew" id="customerContactPhone_'+cnt+'" class="custContactPhone  textbox_new" value="">';
		rowData +='</td>';
		rowData +='<td  style="text-align:left">';
		rowData +='<input type="text" name="customerContactEmailNew" id="customerEmail_'+cnt+'" class="customerEmail required email textbox_new" value="">';
		rowData +='<td align="center" id="adress_actionItems_'+cnt+'">';
		rowData +='<img class="add_business_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                rowData += '&nbsp;<img class = "delete_business_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;" />';              
                rowData += '</td>';
	        $(Row1).after('<tr id="business_contact_row_'+ cnt + '">' + rowData + '</tr>');
                $('#businessContactCount').val(cnt);
              }
               $( "body" ).on( "click", '.add_location_row', function() {
                addworkLocationRow(this);
            });
              $( "body" ).on( "click", '.delete_location_row', function() {
                var Row1 = $(this).parent().parent();
                Row1.remove();
            });
              function addworkLocationRow(rowObject){
		var Row1 = $(rowObject).parent().parent();
                var rowData = "";
                var workLocationCount = $('#workLocationCount').val();
                var cnt = parseInt(workLocationCount) + 1;
                rowData +='<td align="center"><input type="text" name="customerWorkAddressNew" class="customerWorkAddress" id="w_adress_'+cnt+'"></td>';
                rowData +='<td  align="center">';
                rowData +='<select name="customerWorkCountryNew" id="w_country_'+cnt +'" class="custWorkCountry  required textbox_new country_select">';
                rowData +='<option value="">-- Select Country -- </option>';
                rowData +='<c:forEach items="${countryList}" var="countryList" >';
                rowData +='<option value="${countryList.countryID}">${countryList.countryName}</option>';
                rowData +='</c:forEach>';
                rowData +='</select>';
                rowData +='</td>';
                rowData +='<td  align="center">';
		rowData +='<select name="customerWorkRegionNew" id="w_region_'+cnt +'" class="custWorkRegion textbox_new region_select">';
                rowData += '<option value="">-- Select Region -- </option></select></td>';
                rowData += '<td  align="center">';
		rowData += '<select name="customerWorkCityNew" id="w_city_'+cnt+'" class="customerWorkCity  textbox_new">';
		rowData +='<option value="">-- Select City -- </option></select></td>';
                rowData +='<td align="center"><input type="text" name="customerWorkPincodeNew" id="w_pincode_'+cnt+'" class="custWorkPinCode"></td>';
                rowData +='<td><input type="text" name="workingHoursPerDayNew" id="w_working_hours_'+cnt+'" class="workingHoursPerDay required textbox_new" value="" placeholder="HH:MM"></td>';            
                rowData += '<td align="center">';
                rowData += '<img class = "add_location_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                rowData += '&nbsp;<img class = "delete_location_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;">';              
                rowData += '</td>';
                $(Row1).after('<tr id="workLocation_row_'+ cnt + '">' + rowData + '</tr>');
                $('#workLocationCount').val(cnt);
		}
               
                function hideCustomerGroupName() {
                var customerGroupId =  $("input[@name=customerGroup]:checked").attr('id');
                var customerGroupValue = $("#"+customerGroupId).val();
                if(customerGroupValue == 'n') {
                    $("#customerGroupName").removeAttr("disabled");
                } else {
                    $("#customerGroupName").attr("disabled","disabled");
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
                                    $("#errormessage").html("Customer Name already exist");
                                    $("#errorMessageDisplay").css({"display":"block"});
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
            
            function disableSave(saveButtonId,submitButtonId,backButtonId) {
                if($("#customerName").val() == "") {
                    $("#errormessage").html("Please Select Customer Name");
                    
                    return false;
                } else if ($("#salesPerson").val() == "") {
                    $("#errormessage").html("Please Select BDM/Sales Person");
                    return false;
                } else if($("#attachmentValue").val == ""){
                    $("#errormessage").html("Please attach the file");
                    return false;
                }
                else {
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
            function textLimit(field,maxlen) {
                if(field.value.length > maxlen){
                    while(field.value.length > maxlen){
                        field.value=field.value.replace(/.$/,'');
                    }
                    $('#errormessage').html('Should not more than ' + maxlen +  ' characters');
                    
                }
            }
            $( "body" ).on( "change", '.select_sub_RBU', function() {
                getSubRBU(this.value);
            });
             
            
           /*****validation for basic info fields******/
            $("#customerBasicDetailsSave").click(function(){
              saveBasicDetails();
               
            });

            /********Submit button validation*************/
            $("#submitBtn").click(function(e){
                $('#buttonStatus').val('m');
                //e.preventDefault();
//                working hrs
                if(ContinueButton()){
                    contactDetailsValidation();
                    
                }
                else{
                  
                }
                
                
            });       
                        
            /*********BASIC DATA FIELD VALIDATION***************/
            function ContinueButton(){
                var custNameVal=document.getElementById("customerName").value;
                var custNameID=document.getElementById("customerName").id;
                var custNameStatus=allLetter(custNameID,custNameVal);

                var termsOfPaymentVal=document.getElementById("termsOfPayment").value;
                var termsOfPaymentID=document.getElementById("termsOfPayment").id;
                var termsOfPaymentStatus=allNumericTOP(termsOfPaymentID,termsOfPaymentVal);   

                var customerURLVal=document.getElementById("customerURL").value;
                var customerURLID=document.getElementById("customerURL").id;
                var customerURLStatus=checkUrl(customerURLID,customerURLVal);

                var salesPersonVal=document.getElementById("salesPerson").value;
                var salesPersonID=document.getElementById("salesPerson").id;
                var salesPersonStatus=nullFieldCheck(salesPersonID,salesPersonVal);

                var attachmentTypeVal=document.getElementById("attachmentType").value;
                var attachmentTypeID=document.getElementById("attachmentType").id;
                var attachmentTypeStatus=nullFieldCheck(attachmentTypeID,attachmentTypeVal);              

                var salesPersonContactNoVal=document.getElementById("salesPersonContactNo").value;
                var salesPersonContactNoID=document.getElementById("salesPersonContactNo").id;
                var salesPersonContactNoStatus=allNumeric(salesPersonContactNoID,salesPersonContactNoVal);   

                var attachmentVal=document.getElementById("attachmentValue").value;
                var attachmentID=document.getElementById("attachmentValue").id;
                var attachmentStatus=attachmentTypeValidation(attachmentID,attachmentVal);

                if(custNameStatus && 
                    termsOfPaymentStatus && 
                    salesPersonStatus && attachmentTypeStatus && 
                    salesPersonContactNoStatus && attachmentStatus && customerURLStatus){
                    $(".showCustomerBasicDetails").removeClass("active");
                    $(".showCustomerDetails").addClass("active");
                    $("#customerDetails").show();
                    $("#customerBasicDetails").hide();
                    return true;
                }else{

                    if(!custNameStatus){
                        $("#customerName").trigger('focus');
                    }else if(!termsOfPaymentStatus){
                        $("#termsOfPayment").trigger('focus');
                    }else if(!customerURLStatus){
                        $("#customerURL").trigger('focus');
                    }else if(!salesPersonStatus){
                        $("#salesPerson").trigger('focus');
                    }else if(!attachmentTypeStatus){
                        $("#attachmentType").trigger('focus');
                    }else if(!salesPersonContactNoStatus){
                        $("#salesPersonContactNo").trigger('focus');
                    }else if(!attachmentStatus){
                        $("#attachmentValue").trigger('focus');
                    }
                    $(".showCustomerDetails").removeClass("active");
                    $(".showCustomerBasicDetails").addClass("active");
                    $("#customerDetails").hide(); 
                    $("#customerBasicDetails").show();
                    return false;
                }
            }            
            
            function contactDetailsValidation(){
                
                var divisionVal=document.getElementById("division").value;
                var divisionID=document.getElementById("division").id;
                var divisionStatus=allLetter(divisionID,divisionVal);   
                
                var customerWorkAddressStatus=tableNullCheck("customerWorkAddress");
                var custWorkCountryStatus=tableNullCheck("custWorkCountry");
                var custWorkRegionStatus=tableNullCheck("custWorkRegion");
                var customerWorkCityStatus=tableNullCheck("customerWorkCity");
                var custWorkPinCodeStatus=tableNumericCheck("custWorkPinCode");
                 var custworkingHoursPerDayStatus=tableTimeCheck("workingHoursPerDay");

                var custContactPersonStatus=tableAlphabetCheck("custContactPerson");
                var custContactPhoneStatus=tableNumericCheck("custContactPhone");
                var customerEmailStatus=tableEmailCheck("customerEmail");

                var custFinContPersonStatus=tableAlphabetCheck("custFinContactName");
                var custFinContPhoneStatus=tableNumericCheck("custFinContPhone");
                var custFinEmailStatus=tableEmailCheck("custFinEmail");

                var custBillingAddressStatus=tableNullCheck("custBillingAddress");
                var custBillingCountryStatus=tableNullCheck("custBillingCountry");
                var custBillingRegionStatus=tableNullCheck("custBillingRegion");  
                var custBillingCityStatus=tableNullCheck("custBillingCity");
                var custBillingPincodeStatus=tableNumericCheck("custBillingPincode");
 
                if(divisionStatus && customerWorkAddressStatus && custWorkCountryStatus && custWorkRegionStatus && customerWorkCityStatus && custWorkPinCodeStatus && custworkingHoursPerDayStatus &&
                   custContactPersonStatus && custContactPhoneStatus && customerEmailStatus && 
                   custFinContPersonStatus && custFinContPhoneStatus && custFinEmailStatus && 
                   custBillingAddressStatus && custBillingCountryStatus && custBillingRegionStatus && custBillingCityStatus && custBillingPincodeStatus){
 

                   var custName = $("#division").val();
                    $.ajax({
                      url : "getDuplicateCustomerDivisionName.htm?division=" + custName,
                              type : "POST",
                              success : function(data) {
                                alert(data);
                                if (data == 0) {
                                        $("#formCustomerDetails").submit();
                                        startLoading();
                                } else {
                                    $("#dupError").html("<span style='color:red;'><b>Please enter unique customer name.</b></span>");
                                    $("#customerName").trigger('focus');
                                    $(".showCustomerDetails").removeClass("active");
                                    $(".showCustomerBasicDetails").addClass("active");
                                    $("#customerDetails").hide();
                                    $("#customerBasicDetails").show();
                                    }
                                }
                              }
                      );
//                        $("#formCustomerDetails").submit();
//                                        startLoading();
                }else{
                        if(!divisionStatus){
                        $("#division").trigger('focus');
                    }
                }
            }
            
            
            function ValidateEmail(uid,uemail){  
                var mailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;  
                if(uemail.match(mailformat)){ 
                    $("#"+uid).css({"outline":"none"});
                     $("#custURLerrormessage").css({"display":"none"});
                     return true;  
                } else{
                    $("#custURLerrormessage").css({"display":"block"});
                    $("#"+uid).css({"outline":"1px solid red"});
                    return false;                
                }  
            }
            function attachmentTypeValidation(uid,uval){
                if(uval.length > 0 ){
                    var fileExt = $('#'+uid).val().split('.').pop().toLowerCase();
                    if($.inArray(fileExt, ['pdf','docx','doc','xls','xlsx']) == -1) {
                            $("#attachFileerrormessage").css({"display":"block"});
                            $("#"+uid).css({"outline":"1px solid red"});
                            return false;
                    }else{
                        $("#"+uid).css({"outline":"none"});
                        $("#attachFileerrormessage").css({"display":"none"});
                        return true;
                    }
                }else{
                    var attachedFileValueID=document.getElementById("attachedFileValue").id;
                    var attachedFilehrefVal=document.getElementById("attachedFileValue").getAttribute('href');
                    if(attachedFilehrefVal.length > 0){
                        $("#"+uid).css({"outline":"none"});
                        $("#attachFileerrormessage").css({"display":"none"});
                        return true;
                    }else{
                        $("#attachFileerrormessage").css({"display":"block"});
                        $("#"+uid).css({"outline":"1px solid red"});
                        return false;
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
                
            }
            
            function allNumericTOP(uid,uVal){   
                var allNumeric = /^[0-9]+$/;  
                if(uVal.match(allNumeric)){ 
                    if(uVal >  0 && uVal < 365 ){
                         $("#"+uid).css({"outline":"none"});
                        $("#termsOfPaymenterrormessage").css({"display":"none"});
                        return true; 
                    }else{
                        $("#termsOfPaymenterrormessage").css({"display":"block"});
                        $("#"+uid).css({"outline":"1px solid red"});
                        return false; 
                    }
                }else{
                    $("#termsOfPaymenterrormessage").css({"display":"block"});
                    $("#"+uid).css({"outline":"1px solid red"});
                    return false;  
                }  
            }
            function allNumeric(uid,uVal){   
                var allNumeric = /^[0-9]+$/;  
                if(uVal.match(allNumeric)){ 
                    $("#"+uid).css({"outline":"none"});
                    if(uid=="termsOfPayment"){
                        $("#termsOfPaymenterrormessage").css({"display":"none"});
                    }else if(uid=="salesPersonContactNo"){
                        $("#SalesContNoerrormessage").css({"display":"none"});
                    }
                    return true;  
                }else{
                    if(uid=="termsOfPayment"){
                        $("#termsOfPaymenterrormessage").css({"display":"block"});
                    }else if(uid=="salesPersonContactNo"){
                        $("#SalesContNoerrormessage").css({"display":"block"});
                    }
                    
                    $("#"+uid).css({"outline":"1px solid red"});
                    return false;  
                }  
            }
            function checkUrl(uid,uname)
            {
                if(!uname == ''){
                    //regular expression for URL
                    var urlpattern = /^(http|https)?:\/\/[a-zA-Z0-9-\.]+\.[a-z]{2,4}/;

                    if(urlpattern.test(uname)){
                        $("#"+uid).css({"outline":"none"});
                        $("#custURLerrormessage").css({"display":"none"});
                        return true;
                    } else {
                        $("#custURLerrormessage").css({"display":"block"});
                        $("#"+uid).css({"outline":"1px solid red"});
                        return false;
                    }
                }else{
                    return true;
                }
            }
            function allLetter(uid,uname){   
                var letters = /^[A-Za-z\s]+$/;  
                if(uname.match(letters)){
                     $("#nameerrormessage").css({"display":"none"});
                     $("#"+uid).css({"outline":"none"});
                    return true;  
                }else{ 
                    $("#nameerrormessage").css({"display":"block"});
                    $("#"+uid).css({"outline":"1px solid red"});
                    return false;  
                }   
            }
            function nullFieldCheck(fieldId,fieldVal){
                var fieldValLength=fieldVal.length;
                if(fieldValLength > 0){
                    $("#"+fieldId).css({"outline":"none"});
                    if(fieldId === 'customerName'){
                       $("#nameerrormessage").css({"display":"none"});
                    }
                    else if(fieldId === 'aboutCustomer'){
                        $("#AbtCusterrormessage").css({"display":"none"});                       
                    }else if(fieldId === 'attachmentValue'){
                       $("#attachFileerrormessage").css({"display":"none"}); 
                    }else if(fieldId === 'customerGroupName'){
                        $("#custGrperrormessage").css({"display":"none"});
                    }else if(fieldId === 'salesPerson'){
                        $("#salesNameerrormessage").css({"display":"none"});
                    }else if(fieldId === 'attachmentType'){
                         $("#attachTypeerrormessage").css({"display":"none"});
                    }
                    return true;
                }else{
                    if(fieldId === 'customerName'){
                       $("#nameerrormessage").css({"display":"block"});
                    }
                    else if(fieldId === 'aboutCustomer'){
                        $("#AbtCusterrormessage").css({"display":"block"});                       
                    }else if(fieldId === 'attachmentValue'){
                       $("#attachFileerrormessage").css({"display":"block"}); 
                    }else if(fieldId === 'customerGroupName'){
                        $("#custGrperrormessage").css({"display":"block"});
                    }else if(fieldId === 'salesPerson'){
                        $("#salesNameerrormessage").css({"display":"block"});
                    }else if(fieldId === 'attachmentType'){
                         $("#attachTypeerrormessage").css({"display":"block"});
                    }
                    $("#"+fieldId).css({"outline":"1px solid red"});
                    return false;
                }
            }
            
            function workTimeCheck(timeID,TimeVal){
                var hoursPerDay = TimeVal.split(":");

                if((hoursPerDay[0] > 0 && hoursPerDay[0] <= 12 ) && (hoursPerDay[1] >=0 && hoursPerDay[1] <=59)){
                    $("#"+timeID).css({"outline":"none"});
                    $("#workHrserrormessage").css({"display":"none"});
                    return true;
                }else{
                    $("#workHrserrormessage").css({"display":"block"});
                    $("#"+timeID).css({"outline":"1px solid red"});
                    return false;
                }
            }
            
            /********CONTACT DETAILS ROW DATAS VALIDATION********/
            function tableNullCheck(class_name){
                var NullError=0;
                var class_length=document.querySelectorAll("."+class_name).length;

                for(var i=0;i<class_length;i++){
                    var class_name_val_length = document.querySelectorAll("."+class_name)[i].value.length;

                    if(class_name_val_length > 0){
                        document.querySelectorAll("."+class_name)[i].style.outline = "none";
                        
                    }else{
                        document.querySelectorAll("."+class_name)[i].style.outline = "1px solid red";
                        NullError++;
                    }
                }       
                if(NullError <= 0){
                        return true;
                }else if(NullError > 0){
                        return false;
                } 
            }
            function tableAlphabetCheck(clas_name){
                var AlpahbetError=0;
                var clas_length=document.querySelectorAll("."+clas_name).length;
               
                for(var i=0;i<clas_length;i++){
                    var clas_val = document.querySelectorAll("."+clas_name)[i].value;
                    var Tableletters = /^[A-Za-z- ]+$/;  
                    if(clas_val.match(Tableletters)){
                         document.querySelectorAll("."+clas_name)[i].style.outline = "none"; 
                    }else{ 
                        document.querySelectorAll("."+clas_name)[i].style.outline = "1px solid red";
                        AlpahbetError++; 
                    }   
                }
                if(AlpahbetError <= 0){
                        return true;
                }else if(AlpahbetError > 0){
                        return false;
                } 
            }
            function tableNumericCheck(cla_name){
                var NumericError=0;
                var cla_length=document.querySelectorAll("."+cla_name).length;
                
                for(var i=0;i<cla_length;i++){
                    var cla_val = document.querySelectorAll("."+cla_name)[i].value;
                    var TableallNumeric = /^[0-9]+$/;   
                    if(cla_val.match(TableallNumeric)){
                         document.querySelectorAll("."+cla_name)[i].style.outline = "none"; 
                    }else{ 
                        document.querySelectorAll("."+cla_name)[i].style.outline = "1px solid red";
                        NumericError++; 
                    }   
                }
                if(NumericError <= 0){
                    return true;
                }else if(NumericError > 0){
                    return false;
                } 
            }
             function tableEmailCheck(cl_name){
                var EmailError=0;
                var cl_length=document.querySelectorAll("."+cl_name).length;

                for(var i=0;i<cl_length;i++){
                    var cl_val = document.querySelectorAll("."+cl_name)[i].value;
                    var Tablemailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;    
                    if(cl_val.match(Tablemailformat)){
                         document.querySelectorAll("."+cl_name)[i].style.outline = "none"; 
                    }else{ 
                        document.querySelectorAll("."+cl_name)[i].style.outline = "1px solid red";
                        EmailError++; 
                    }   
                }
                if(EmailError <= 0){
                    return true;
                }else if(EmailError > 0){
                    return false;
                } 
            }
            
            function tableTimeCheck(c_name){
                var TimeError=0;
                var c_length=document.querySelectorAll("."+c_name).length;

                for(var i=0;i<c_length;i++){
                    var c_val = document.querySelectorAll("."+c_name)[i].value;
                    var hoursPerDay = c_val.split(":");

                    if((hoursPerDay[0] > 0 && hoursPerDay[0] <= 12 ) && (hoursPerDay[1] >=0 && hoursPerDay[1] <=60)){
                        document.querySelectorAll("."+c_name)[i].style.outline = "none";
                    }else{
                       document.querySelectorAll("."+c_name)[i].style.outline = "1px solid red";
                       TimeError++; 
                    }  
                }
                if(TimeError <= 0){
                    return true;
                }else if(TimeError > 0){
                    return false;
                } 
            }
            
            
              /**********rows save validations**********/
            function saveTableNumericCheck(savecla_name){
                var saveNumericError=0;
                var savecla_length=document.querySelectorAll("."+savecla_name).length;
                for(var i=0;i<savecla_length;i++){
                    if(document.querySelectorAll("."+savecla_name)[i].value.length > 0){
                        var savecla_val = document.querySelectorAll("."+savecla_name)[i].value;
                        var saveTableallNumeric = /^[0-9]+$/;   
                        if(savecla_val.match(saveTableallNumeric)){
                             document.querySelectorAll("."+savecla_name)[i].style.outline = "none"; 
                        }else{ 
                            document.querySelectorAll("."+savecla_name)[i].style.outline = "1px solid red";
                            saveNumericError++; 
                        } 
                    }
                }
                if(saveNumericError <= 0){
                    return true;
                }else if(saveNumericError > 0){
                    return false;
                } 
            }
            
            function saveTableEmailCheck(savecl_name){
                var saveEmailError=0;
                var savecl_length=document.querySelectorAll("."+savecl_name).length;
                for(var i=0;i<savecl_length;i++){
                    if(document.querySelectorAll("."+savecl_name)[i].value.length > 0){
                        var savecl_val = document.querySelectorAll("."+savecl_name)[i].value;
                        var saveTablemailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;    
                        if(savecl_val.match(saveTablemailformat)){
                             document.querySelectorAll("."+savecl_name)[i].style.outline = "none"; 
                        }else{ 
                            document.querySelectorAll("."+savecl_name)[i].style.outline = "1px solid red";
                            saveEmailError++; 
                        }
                    }
                }
                if(saveEmailError <= 0){
                    return true;
                }else if(saveEmailError > 0){
                    return false;
                } 
            }
            
            function saveTableTimeCheck(savec_name){
                var TimeError=0;
                var c_length=document.querySelectorAll("."+savec_name).length;
                for(var i=0;i<c_length;i++){
                    if(document.querySelectorAll("."+savec_name)[i].value.length > 0){
                        var c_val = document.querySelectorAll("."+savec_name)[i].value;
                        var hoursPerDay = c_val.split(":");

                        if((hoursPerDay[0] > 0 && hoursPerDay[0] <= 12 ) && (hoursPerDay[1] >=0 && hoursPerDay[1] <=60)){
                            document.querySelectorAll("."+savec_name)[i].style.outline = "none";
                        }else{
                           document.querySelectorAll("."+savec_name)[i].style.outline = "1px solid red";
                           TimeError++; 
                        }
                    }
                }
                if(TimeError <= 0){
                    return true;
                }else if(TimeError > 0){
                    return false;
                }
                    
            }
            
            function saveTableAlphabetCheck(clas_name){
                var AlpahbetError=0;
                var clas_length=document.querySelectorAll("."+clas_name).length;
               
                for(var i=0;i<clas_length;i++){
                    if(document.querySelectorAll("."+clas_name)[i].value.length > 0){
                        var clas_val = document.querySelectorAll("."+clas_name)[i].value;
                        var Tableletters = /^[A-Za-z- ]+$/;  
                        if(clas_val.match(Tableletters)){
                             document.querySelectorAll("."+clas_name)[i].style.outline = "none"; 
                        }else{ 
                            document.querySelectorAll("."+clas_name)[i].style.outline = "1px solid red";
                            AlpahbetError++; 
                        }
                    }
                }
                if(AlpahbetError <= 0){
                        return true;
                }else if(AlpahbetError > 0){
                        return false;
                } 
            }
            
            function saveattachmentTypeValidation(uid,uval){
                if(uval.length > 0){
                    var fileExt = $('#'+uid).val().split('.').pop().toLowerCase();
                    if($.inArray(fileExt, ['pdf','docx','doc','xls','xlsx']) == -1) {
                        $("#attachFileerrormessage").css({"display":"block"});
                        $("#"+uid).css({"outline":"1px solid red"});
                        return false;
                    }else{
                        $("#"+uid).css({"outline":"none"});
                        $("#attachFileerrormessage").css({"display":"none"});
                        return true;
                    }
                }else{
                    $("#"+uid).css({"outline":"none"});
                    $("#attachFileerrormessage").css({"display":"none"});
                    return true;
                } 
            }
            
             function saveallNumericTOP(uid,uVal){  
                if(uVal.length > 0){
                    var allNumeric = /^[0-9]+$/;  
                    if(uVal.match(allNumeric)){ 
                        if(uVal >  0 && uVal < 365 ){
                            $("#"+uid).css({"outline":"none"});
                            $("#termsOfPaymenterrormessage").css({"display":"none"});
                            return true; 
                        }else{
                            $("#termsOfPaymenterrormessage").css({"display":"block"});
                            $("#"+uid).css({"outline":"1px solid red"});
                            return false; 
                        }
                    }else{
                        $("#termsOfPaymenterrormessage").css({"display":"block"});
                        $("#"+uid).css({"outline":"1px solid red"});
                        return false;  
                    }
                }else{
                   $("#"+uid).css({"outline":"none"});
                   $("#termsOfPaymenterrormessage").css({"display":"none"});
                   return true;
                }
            }
            function saveallNumeric(uid,uVal){ 
                if(uVal.length > 0){
                    var allNumeric = /^[0-9]+$/;  
                    if(uVal.match(allNumeric)){ 
                        $("#"+uid).css({"outline":"none"});
                        if(uid=="termsOfPayment"){
                            $("#termsOfPaymenterrormessage").css({"display":"none"});
                        }else if(uid=="salesPersonContactNo"){
                            $("#SalesContNoerrormessage").css({"display":"none"});
                        }
                        return true;  
                    }else{
                        if(uid=="termsOfPayment"){
                            $("#termsOfPaymenterrormessage").css({"display":"block"});
                        }else if(uid=="salesPersonContactNo"){
                            $("#SalesContNoerrormessage").css({"display":"block"});
                        }

                        $("#"+uid).css({"outline":"1px solid red"});
                        return false;  
                    } 
                }else{
                    $("#"+uid).css({"outline":"none"});
                    if(uid=="termsOfPayment"){
                        $("#termsOfPaymenterrormessage").css({"display":"none"});
                    }else if(uid=="salesPersonContactNo"){
                        $("#SalesContNoerrormessage").css({"display":"none"});
                    }
                    return true;  
                }
            }
            function savecheckUrl(uid,uname){
                if(uname.length >0 ){
                    if(!uname == ''){
                        //regular expression for URL
                        var urlpattern = /^(http|https)?:\/\/[a-zA-Z0-9-\.]+\.[a-z]{2,4}/;

                        if(urlpattern.test(uname)){
                            $("#"+uid).css({"outline":"none"});
                            $("#custURLerrormessage").css({"display":"none"});
                            return true;
                        } else {
                            $("#custURLerrormessage").css({"display":"block"});
                            $("#"+uid).css({"outline":"1px solid red"});
                            return false;
                        }
                    }else{
                        return true;
                    }
                }else{
                    $("#"+uid).css({"outline":"none"});
                    $("#custURLerrormessage").css({"display":"none"});
                    return true;
                }
            }
            
            function savenullFieldCheck(fieldId,fieldVal){
                $("#"+fieldId).css({"outline":"none"});
                $("#salesNameerrormessage").css({"display":"none"});
                $("#attachTypeerrormessage").css({"display":"none"});
                return true;
            }
            $("#successDiv").fadeOut("slow");
 });
    </script>
