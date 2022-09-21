<%--
Document   : customerList
Created on : Oct 13, 2011, 5:20:42 PM
Author     : 14053
--%>
<%@include file="header.jsp" %>
<%@page import="com.google.gson.Gson" %>
<%@page import="java.util.List"%>
<%@page import="com.defiance.ideal.application.dto.CustomerAddDTO" %>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Map" %>
<%@page import="java.util.HashMap" %>

<head>
    <title>Edit Customer</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.4.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_page.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/qpd.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/demo_table.css" type="text/css"/>

    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/c/jquery-ui-1.8.16.custom.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.16.custom.css" type="text/css"/>--%>





    <script type="text/javascript">
        var modelObject = {
            work_loc: null,
            billing_address: null,
            customer_finance_contacts: null,
            customer_contacts: null,
            getWorkLoction: function() {
                return this.work_loc;
            },
            setWorkLoction: function(work_loc) {
                this.work_loc = work_loc;
            },
            setBillingAddress: function(billing_address) {
                this.billing_address = billing_address;
            },
            getBillingAddress: function() {
                return this.billing_address;
            },
            setCustomerFinanceContacts: function(customer_finance_contacts) {
                this.customer_finance_contacts = customer_finance_contacts;
            },
            getCustomerFinanceContacts: function() {
                return this.customer_finance_contacts;
            },
            setCustomerDunningContacts: function(customer_dunning_contacts) {
                this.customer_dunning_contacts = customer_dunning_contacts;
            },
            getCustomerDunningContacts: function() {
                return this.customer_dunning_contacts;
            },
            setCustomerContacts: function(customer_contacts) {
                this.customer_contacts = customer_contacts;
            },
            getCustomerContacts: function() {
                return this.customer_contacts;
            }

        };
        //selectedCustomerContactDetails
        <%
            Gson gson = new Gson();
            Map<String, CustomerAddDTO> mapObject;

            List<CustomerAddDTO> customer_work_loc = null;
            String json_customer_work_loc = null;
            customer_work_loc = (List) request.getAttribute("selectedCustomerWorkLocations");
            mapObject = new HashMap<String, CustomerAddDTO>();

            if (customer_work_loc != null) {
                Iterator itr = customer_work_loc.iterator();
                while (itr.hasNext()) {
                    CustomerAddDTO dto = (CustomerAddDTO) itr.next();
                    mapObject.put(dto.getId(), dto);
                }
                json_customer_work_loc = gson.toJson(mapObject);
            }

            mapObject = new HashMap<String, CustomerAddDTO>();
            List<CustomerAddDTO> customer_billing_adress = null;
            String json_customer_billing_address = null;
            customer_billing_adress = (List) request.getAttribute("selectedBillingAddress");
            if (customer_billing_adress != null) {
                Iterator itr2 = customer_billing_adress.iterator();
                while (itr2.hasNext()) {
                    CustomerAddDTO dto2 = (CustomerAddDTO) itr2.next();
                    mapObject.put(dto2.getId(), dto2);
                }
                json_customer_billing_address = gson.toJson(mapObject);
            }

            mapObject = new HashMap<String, CustomerAddDTO>();
            List<CustomerAddDTO> customer_finance_contacts = null;
            String json_customer_finance_contacts = null;
            customer_finance_contacts = (List) request.getAttribute("selectedFianceContactDetails");
            if (customer_finance_contacts != null) {
                Iterator itr3 = customer_finance_contacts.iterator();
                while (itr3.hasNext()) {
                    CustomerAddDTO dto3 = (CustomerAddDTO) itr3.next();
                    mapObject.put(dto3.getId(), dto3);
                }
                json_customer_finance_contacts = gson.toJson(mapObject);
            }
            
            mapObject = new HashMap<String, CustomerAddDTO>();
            List<CustomerAddDTO> customer_dunning_contacts = null;
            String json_customer_dunning_contacts = null;
            customer_dunning_contacts = (List) request.getAttribute("selectedDunningContactDetails");
            if (customer_dunning_contacts != null) {
                Iterator itr3 = customer_dunning_contacts.iterator();
                while (itr3.hasNext()) {
                    CustomerAddDTO dto3 = (CustomerAddDTO) itr3.next();
                    mapObject.put(dto3.getId(), dto3);
                }
                json_customer_dunning_contacts = gson.toJson(mapObject);
            }

            mapObject = new HashMap<String, CustomerAddDTO>();
            List<CustomerAddDTO> customer_contacts = null;
            String json_customer_contacts = null;
            customer_contacts = (List) request.getAttribute("selectedCustomerContactDetails");

            if (customer_contacts != null) {
                Iterator itr4 = customer_contacts.iterator();
                while (itr4.hasNext()) {
                    CustomerAddDTO dto4 = (CustomerAddDTO) itr4.next();
                    mapObject.put(dto4.getId(), dto4);
                }
                json_customer_contacts = gson.toJson(mapObject);
            }
        %>
        modelObject.setWorkLoction(<%= json_customer_work_loc%>);
        modelObject.setBillingAddress(<%= json_customer_billing_address%>);
        modelObject.setCustomerFinanceContacts(<%= json_customer_finance_contacts%>);
        modelObject.setCustomerDunningContacts(<%= json_customer_dunning_contacts%>);
        modelObject.setCustomerContacts(<%= json_customer_contacts%>);

    </script>  

    <style type="text/css">
        #loadingDiv img{ border: none; }

        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1
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
            /*none repeat scroll 0 0 #DFE8F6*/
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
        /*        #datadisplay1 table tr.selected{
                    background:#DFE8F6;
                }*/
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
            width:auto;
        }

        .alertboxWrap{
            background-color:#000000;
            background-color:rgba(0,0,0,0.3);
            -ms-filter: "progid:DXImageTransform.Microsoft.Alpha(Opacity=50)";

            width:100%;
            height:100%;
            overflow:auto;
            position:fixed;
            top:0px;
            left:0px;
            display:none;
        }

        .tableStructure td{
            padding: 1px;
            border: 1px solid #C4D1E0;
            height: 20px;
        }

        .tableStructure th{
            font-size:120%;
            text-align:center;
            font-weight:bolder;
            heigh:20px;
            background-color:#4581ba;
            border:1px solid #C4D1E0; 
            background:#FFF url("../js/extjs/resources/images/default/form/text-bg.gif") repeat-x top;
            padding:3px;
            width:10%;
            font-weight: normal;
        }
        .tableStructure tr:nth-child(even) {            
            background-color: #e6e6e6;     
        }
        .tableStructure tr:nth-child(odd) {           
            background-color:#ffffff;
        }
        .tableStructure th:first-child{
            width:2%;
        }
        .dynamic_AlertTable{
            width: 99%;
            text-align: center;
            padding: 5px;
            height:auto;
            max-height: 311px;
            overflow:auto;
        }
        .alertbox
        {
            z-index:10;
            width:auto;
            padding:0px;
            background-color:#FFFFFF;
            border:grey 1px solid;
            position:absolute;
            top:35%;
            left:35%;
            opacity:1;
        }
        .dialog_alert{
            width: 940px;
            left: 15%;
            top:15%;
        }
        .dialog_submit_button, .dialog_cancel_button{
            background:#316CA8;
            width: 90px;
            height: 32px;
            font-family: Arial;
            font-weight: bold;
            font-size: 13px;
            color:white;
            text-align: center;
            border: 1px solid white;
            margin:0px 5px 0px 0px;
        }

        p
        {
            margin:0px;
            padding:0px;
        }
        #contact_butons{
            text-align: right;
            padding: 5px;
        }
        .last_selected{
            background-color: #B0BED0;
        }
        /*        .selected{
                    background-color: #B0BED9; 
                }*/
        .TableSelectInput{
            border:none;
            width:99%;
        }
        .cust_wl_check{
            height: auto;
            width:25px;
            text-align: center;
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
        fieldset
        {
            margin:10px;
            /* border:none;*/
            padding:10px;
        }
        fieldset legend {
            background: #fff;
            color: #666666;
            font-size: 14px;
            margin: 0px 0px 4px 0px;
        }.InfoText{
            margin:5px;
        }
        .errorMessage{
            color:red;
            font-size:10px;
            margin: 1px;
            display:none;
        }
        .formLabel{
            width: 20%;
            margin: 10px 26px 10px 9px;
            display: inline-block;
            float:none;
            vertical-align: top;
        }
        .formInput{
            width: 19%;
            margin: 5px 26px 5px 9px;
            display: inline-block;
            vertical-align: top;
        }
        #datadisplay  table.business_contact tbody tr,
        #datadisplay table.customer_billing_address tbody tr ,
        #datadisplay  table.customer_work_location tbody tr,
        #datadisplay  table.finance_contact tbody tr,
        #datadisplay  table.dunning_contact tbody tr{
            width:99%;
        }
        #datadisplay   table.business_contact tbody tr th,
        #datadisplay   table.business_contact tbody tr td,
        #datadisplay   table.finance_contact tbody tr th,
        #datadisplay   table.finance_contact tbody tr td,
        #datadisplay   table.dunning_contact tbody tr th,
        #datadisplay   table.dunning_contact tbody tr td{
            text-align: left;
            padding-left:6px;
        }
        #datadisplay  table.customer_billing_address tbody tr th,
        #datadisplay   table.customer_billing_address tbody tr td{
            width:15%;
            text-align: left;
            padding-left:6px;
        }
        #datadisplay  table.customer_work_location tbody tr th,
        #datadisplay   table.customer_work_location tbody tr td{
            width:10%;
            text-align: left;
            padding-left:6px;
        }
        #datadisplay  table.business_contact tbody tr th:last-child,
        #datadisplay  table.customer_billing_address tbody tr th:last-child,
        #datadisplay  table.customer_work_location tbody tr th:last-child,
        #datadisplay  table.finance_contact tbody tr th:last-child,
        #datadisplay  table.dunning_contact tbody tr th:last-child,
        #datadisplay  table.business_contact tbody tr td:last-child,
        #datadisplay  table.customer_billing_address tbody tr td:last-child,
        #datadisplay  table.customer_work_location tbody tr td:last-child,
        #datadisplay  table.finance_contact tbody tr td:last-child{
            width:4%;
            text-align: center;
        }
        #datadisplay   table.customer_work_location tbody tr td:last-child{
            text-align: center;
            width:2%;
        }
        #datadisplay  table.customer_work_location tbody tr td select,
        #datadisplay  table.customer_work_location tbody tr td input{
            width:99%;
        }
        #datadisplay  table.customer_work_location tbody tr:last-child,
        #datadisplay  table.customer_billing_address tbody tr:last-child,
        #datadisplay  table.finance_contact tbody tr:last-child,
        #datadisplay  table.dunning_contact tbody tr:last-child,
        #datadisplay  table.business_contact tbody tr:last-child{
            border-top: 0.5px solid lightgrey; 
        }

        /*        #datadisplay  table#customer_work_location tbody tr th:first-child,
                #datadisplay  table#customer_work_location tbody tr td:first-child{
                    width:1%;
                }*/
        /*        #datadisplay table#customer_work_location tbody tr th:first-child+th,
                #datadisplay table#customer_work_location tbody tr td:first-child+td{
                    width:35%;
                    word-break: break-word;
                }*/
        #datadisplay  table.business_contact tbody tr td input,
        #datadisplay  table.customer_billing_address tbody tr input,
        #datadisplay  table.customer_work_location tbody tr input,
        #datadisplay  table.finance_contact tbody tr input,
        #datadisplay  table.dunning_contact tbody tr input,
        #datadisplay  table.business_contact tbody tr input,
        #datadisplay  table.customer_billing_address tbody tr input,
        #datadisplay  table.customer_work_location tbody tr input,
        #datadisplay  table.finance_contact tbody tr input,
        #datadisplay  table.dunning_contact tbody tr input,
        #datadisplay  table.business_contact tbody tr td select,
        #datadisplay  table.customer_billing_address tbody tr select,
        #datadisplay  table.customer_work_location tbody tr select,
        #datadisplay  table.finance_contact tbody tr select,
        #datadisplay  table.dunning_contact tbody tr select,
        #datadisplay  table.business_contact tbody tr select,
        #datadisplay  table.customer_billing_address tbody tr select,
        #datadisplay  table.customer_work_location tbody tr select,
        #datadisplay  table.finance_contact tbody tr select,
        #datadisplay  table.dunning_contact tbody tr select{
            width:99%;
        }
        fieldset#billingWorkfield,
        fieldset#billingAddressfield,
        fieldset#FinContactField,
        fieldset#DunContactField,
        fieldset#businessContactField,
        fieldset.workLocationFieldset,
        fieldset.billingAddressFieldset, 
        fieldset.businessContactFieldset, 
        fieldset.financeContactFieldset,
        fieldset.dunningContactFieldset{
            margin:0px;
            border: 0px;
            width:99%;
        }

        #datadisplay  table.customer_work_location tbody tr th:last-child(2),
        #datadisplay  table.customer_work_location tbody tr td:last-child(2){
            width:10%;
        }
        div.customerEditDiv {
            padding: 10px;
            box-sizing: border-box;
        }
        div#btnGroup{
            float:none;
        }
        div.formContent_new{
            padding: 10px;
            box-shadow: border-box;
        }
        div.basicDetails{
            border: 1px solid #99BBE8;
            border-radius: 4px;
            margin:14px;
            box-shadow:border-box;
        }
        #datadisplay table {
            border: 1px solid #99BBE8;
            border-radius: 4px;
        }

        #datadisplay th{
            font-weight: bold;
            color:#666666;
        }
        .errorAlertText{
            width: 100%;
            font-weight: bold;
            padding: 15px;
            font-size: 12px;
        }
        .errorAlertWrap .dialog_alert{
            width:350px;
            left: 36%;
            top: 30%;
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
        .customerDetails{
            margin: 30px auto;
            width: 95%;
            background-color:#F0F8FF;
            border:1px solid #ccc;

        }
        .headerCenter
        {
            font-size: 12px;
        }
        .headerCenter th:first-child
        {
            border-left: 1px !important;
        }
        .customerDetails table
        {
            border-collapse: collapse;
        }
        .customerDetails table tr td
        {
            font-size: 11px;
        }
        .customer_billing_address tr th:first-child,
        .customer_work_location tr th:first-child,
        .business_contact tr th:first-child,
        .finance_contact tr th:first-child,
        .dunning_contact tr th:first-child{
            border-left: 1px !important;
        }
        select {
            padding: 3px 0px 2px 0px;
            background: url("images/text-bg.gif") repeat-x scroll center top #FFFFFF;
            border: 1px solid #C4D1E0;
            font-family: arial;
            font-size: 12px;
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
        #attachFileerrormessage{
            display: none;
            width: 110%;
            padding: 0px ;
            margin: 0px ;
        }
        #attachmentValue{
            width:99%;
            margin:0px;
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
        #userManualIcon{
            width: 15px;
            margin: 10px 0px 0px 600px;
        }
    </style>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
<!--        <div align="center" style="margin:15px 0px 20px 0px;" id="successDiv"><font color="red" size="4">${Success_message}</font></div>-->
        <div class="container_inner" style="margin: 15px 0px; width:960px;">
            <p class="page_heading"><span class="heading">${selectedCustomerData[0].customerName} - Edit Customer</span> </p>
        </div>
        <div class="notice_page" width="960px">
            <img src="/iDeal_application/css/images/icon_alert.png" title="Information" alt="Information" id="infoIcon">
            <img src="/iDeal_application/css/images/tick.png" title="Information" alt="Information" id="tickIcon">
            <span class="InfoText">Fields marked in <span style="color: red;" for="fine">*</span> are mandatory.</span>
            <!--<img src="/iDeal_application/css/images/usermanual2.png" title="Information" alt="Information" id="userManualIcon"/><a href="userManualDownload.htm"  target="_blank" style="text-decoration:none;color: #4C83B3; font-size: 13px; font-weight:bold">  User Manual</a>-->
        </div>
        <form action="saveEditCustomer.htm" name="formCustomerDetails" id="formCustomerDetails" method="post" enctype="multipart/form-data" autocomplete="off">



            <div class="formContent_new" id="datadisplay" style="height:auto">
                <div class="content_page" >
                    <h3 style="padding-left:15px;">Customer Details</h3>
                    <div class="basicDetails">
                        <label class="formLabel">Customer Name<font color="red">*</font></label>
                            <c:choose>
                                <c:when test="${selectedCustomerData[0].status == 's' || selectedCustomerData[0].status == 'm' ||(selectedCustomerData[0].status == 'r'&&selectedCustomerData[0].customerCode==null && selectedCustomerData[0].custID == selectedCustomerData[0].parentId)}">
                                    <input type="text" name="customerName" id="customerName" class="required textbox_new formInput" value="${selectedCustomerData[0].customerName}">
                                </c:when>

                                <c:otherwise>
                                    <input readonly type="text" name="customerName" id="customerName" class="required textbox_new formInput" value="${selectedCustomerData[0].customerName}">
                                </c:otherwise>
                            </c:choose>

                        <label class="formLabel">BDM/SalesPerson Name<font color="red">*</font></label>
                            <c:choose>
                                <c:when test="${fn:length(salesPerson)>1}">
                                <select name="salesPerson" id="salesPerson" class="formInput required textbox_new" onchange="getSalesPersonPhoneNumber(this.value);">
                                    <option value="">-- Select BDM -- </option>
                                    <c:forEach items="${salesPerson}" var="salesPerson" >
                                        <c:set var="selsalesPerson" value=""></c:set>
                                        <c:if test="${selectedCustomerData[0].salesPersonRefId == salesPerson.salesManId}">
                                            <c:set var="selsalesPerson" value="selected"></c:set>
                                        </c:if>

                                        <option value="${salesPerson.salesManId}" ${selsalesPerson}>${salesPerson.employeeName} - ${salesPerson.employeeNumber}</option>
                                    </c:forEach>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <input name="salesPerson" type="hidden" id="salesPerson" value="${salesPerson.get(0).salesManId}" class="required textbox_new "/>
                                <input name="salesPersonName" id="salesPersonName" readonly value="${salesPerson.get(0).employeeName} - ${salesPerson.get(0).employeeNumber}" class="required textbox_new formInput"/>
                            </c:otherwise>
                        </c:choose>


                        <label class="formLabel">Customer Code </label>
                        <input readonly type="text" name="customerCode" id="customerCode" class="formInput textbox_new" value="${selectedCustomerData[0].customerCode}">



                        <label class="formLabel">Sales Person Contact No<font color="red">*</font></label>
                        <input type="text" name="salesPersonContactNo" id="salesPersonContactNo" class="formInput textbox_new" value="${selectedCustomerData[0].salesPersonContactNo}">

                        <label class="formLabel">About Customer<font color="red">*</font></label>
                        <textarea cols="20" rows="2" style="max-width: 20% !important;" name="aboutCustomer"   id="aboutCustomer" class="formInput">${selectedCustomerData[0].aboutCustomer}</textarea>

                        <label class="formLabel">Attachment type<font color="red">*</font></label>
                        <select name="attachmentType" id="attachmentType" class="textbox_new formInput formInput">
                            <option value="">-- Select attachment type -- </option>
                            <c:forEach items="${attachmentType}" var="attachmentType" >
                                <c:set var="selattachmentType" value=""></c:set>
                                <c:if test="${selectedCustomerData[0].attachmentType == attachmentType.configKey}">
                                    <c:set var="selattachmentType" value="selected"></c:set>
                                </c:if>
                                <option value="${attachmentType.configKey}" ${selattachmentType}> ${attachmentType.configKey}</option>

                            </c:forEach>
                        </select>

                        <label class="formLabel">Customer URL</label>
                        <input type="text" name="customerURL" id="customerURL" class="url textbox_new formInput" value="${selectedCustomerData[0].customerURL}" placeholder="http://www.hindujatech.com">


                        <label class="formLabel">Attachments(NDA/PO/MSA)<font color="red">*</font> </label>
                        <div class="formInput attachmentInput">
                            <input id="attachmentValue" type="file" name="attachmentValue" value="" class="formInput"/>
                            
                            <input id="attachmentValue" type="hidden" name="attachmentValue1" value="${selectedCustomerData[0].attchedFileName}" class="formInput"/>
                            <p id="attachFileerrormessageName" class="errorMessageName" style="font-size:8px;">Please upload a file type(.pdf/.jpg/.png/.jpeg)</p>



                            <!--<p id="attachFileerrormessage1" class="errorMessage">File size should be less than 3 MB</p>-->
                        </div>
                        <label class="formLabel">TAN(for India based Customers)</label>
                        <input type="text" name="tanNumber" id="tanNumber" value="${selectedCustomerData[0].tanNumber}" class="required textbox_new formInput" value="">
                        <label style="width: 22%;margin: 0px 26px 10px 163px; display: inline-block;float: right;text-align: right;vertical-align: top;">
                            <c:if test="${selectedCustomerData[0].attchedFileName == null}">
                                No file Found                  
                            </c:if>
                            <c:if test="${selectedCustomerData[0].attchedFileName != null}">
                                <a href="attachmentDownload.htm?fileName=${selectedCustomerData[0].fileName}&fileType=${selectedCustomerData[0].fileType}" target="_blank" name="file" id="attachedFileValue">${selectedCustomerData[0].attchedFileName}</a>
                            </c:if> 
                        </label>  
                    </div> 
                    <div>

                        <p style="width:95%;margin:0px 5px 5px 10px;" class="addDivisionDiv">                    
                            <c:choose>
                                <c:when test="${selectedCustomerData[0].customerDivision == ''|| selectedCustomerData[0].customerDivision == null}">
                                    <input type="hidden" name="customerDivisionListName"  class="division textbox_new" value="${selectedCustomerData[0].customerDivision}">
                                </c:when>
                                <c:otherwise>
                                    <label style="width: auto; margin: 2px 106px 0px 20px;">Division Name</label>
                                    <input type="text" name="customerDivisionListName"  class="division textbox_new" value="${selectedCustomerData[0].customerDivision}">
                                </c:otherwise>
                            </c:choose>
                            <c:if test="${selectedCustomerData[0].custID == selectedCustomerData[0].parentId}">
                                <input type="button"    class="addDivsion addDivisionBtn" value="Add Division">
                            </c:if>
                            
                        </p>
                    </div>
                    <div class="customerDetails" id="customerDetails_1" name="customerDetails_1">       

                        <div class="billingAddress" id="billingAddressDiv_1" name="billingAddressDiv_1" >
                            <fieldset id="billingAddressfield">
                                <legend> Customer Billing Address</legend>
                                <c:choose>
                                    <c:when test="${fn:length(selectedBillingAddress)>0}">
                                        <input type="hidden" value="${fn:length(selectedBillingAddress)}" id="businessAddressCount_1" name="businessAddressCount_1" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" value="1" id="businessAddressCount_1" name="businessAddressCount_1" />
                                    </c:otherwise>
                                </c:choose>

                                <table class="customer_billing_address" id="customerBillingAddress_1" name="customerBillingAddress_1" border="0" width="99%">
                                    <tr class="headerCenter">
                                        <th>Address<span style="color: red;" for="fine">*</span></th>
                                        <th>Short Code<span style="color: red;" for="fine">*</span></th>
                                        <th>Country<span style="color: red;" for="fine">*</span></th>
                                        <th>Region <span style="color: red;" for="fine">*</span></th>
                                        <th>City<span style="color: red;" for="fine">*</span></th>
                                        <th>Pincode<span style="color: red;" for="fine">*</span></th>
                                        <th>State Code<span style="color: red;" for="fine">*</span></th>
                                        <th>GSTIN/UID<span style="color: red;" for="fine">*</span></th>
                                        <th>Action</th>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${fn:length(selectedBillingAddress)>0}">
                                            <c:set var="exbllength"  value="${fn:length(selectedBillingAddress)}"/>
                                            <c:forEach items="${selectedBillingAddress}" var="selectedBillingAddress" varStatus="cb_loop">
                                                <tr id="cust_billing_${cb_loop.index}">
                                                    <td style="min-width: 221px;max-width: 221px !important;">
                                                        ${selectedBillingAddress.customerAddress}
                                                        <input type="hidden"  id="customerBillingID_${cb_loop.index}" value="${selectedBillingAddress.id}">
                                                    </td>
                                                    <td align="center">
                                                        ${selectedBillingAddress.addressShortCode}
                                                    </td>
                                                    <td  align="center">
                                                        ${selectedBillingAddress.country}
                                                    </td>
                                                    <td  align="center">
                                                        ${selectedBillingAddress.region}
                                                    </td>
                                                    <td  align="center">
                                                        ${selectedBillingAddress.city}
                                                    </td>
                                                    <td align="center">
                                                        ${selectedBillingAddress.pincode}
                                                    </td>
                                                    <td align="center">
                                                        <c:if test="${selectedBillingAddress.countryID==113}">
                                                            ${selectedBillingAddress.gstCode}
                                                        </c:if>
                                                    </td>
                                                    <td align="center">
                                                        <c:if test="${selectedBillingAddress.countryID==113}">
                                                            ${selectedBillingAddress.gstNumber}
                                                        </c:if>
                                                    </td>
                                                    <td align="center">
                                                        <img class="edit_cb" src="${pageContext.request.contextPath}/css/images/document-blue.png"  id="edit_cb_${loop.index}" title="Edit Address" style="cursor:pointer;">
                                                    </td>
                                                </tr>
                                            </c:forEach> 
                                            <tr id="cust_billing_${exbllength}">
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>   
                                                <td align="center">
                                                </td>   
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                    <img class="add_billing_row" id="add_billing_parent" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr id="cust_billing_1">
                                                <td align="center">
                                                    <textarea cols="20" rows="2" name="customerBillingAddressNew_1_1"  id="b_adress_1_1"  maxlength="200"  minlength="10" onblur="textLimit(this, 200)" onkeyup="textLimit(this, 200)" class="customerBillingAddress" style="min-width: 221px ;max-width: 221px;"></textarea>
                                                    <!--<input type="text" name="customerBillingAddressNew_1_1" class="customerBillingAddress" id="customerBillingAddressNew_1_1">-->
                                                </td>
                                                <td align="center">
                                                    <input type="text" value="" name="customerBillingShortCodeNew_1_1" id="customerBillingShortCode_1_1" class="custBillingShortCode" maxlength="15">
                                                </td>
                                                <td  align="center">
                                                    <select name="customerBillingCountryNew_1_1" id="customerBillingCountry_1_1" class="customerBillingCountry">
                                                        <option value="">-- Select Country -- </option>
                                                        <c:forEach items="${countryList}" var="countryList" >
                                                            <option value="${countryList.countryID}">${countryList.countryName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td  align="center">
                                                    <select name="customerBillingRegionNew_1_1" class="customerBillingRegion" id="customerBillingRegion_1_1" >
                                                        <option value="">-- Select Region -- </option>
                                                    </select>
                                                </td>
                                                <td  align="center">
                                                    <select name="customerBillingCityNew_1_1" class="customerBillingCity" id="customerBillingCity_1_1" >
                                                        <option value="">-- Select City -- </option>
                                                    </select>
                                                </td>
                                                <td align="center">
                                                    <input type="text" class="customerBillingPincode" name="customerBillingPincodeNew_1_1" id="customerBillingPincode_1_1">
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="customerBillingGstCodeNew_1_1" id="customerBillingGstCode_1_1" readonly class="custBillingGstCode"/> 
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="customerBillingGstNumberNew_1_1" id="customerBillingGstNumberNew_1_1" class="customerBillingGstNumber"/> 
                                                </td>
                                                <td align="LEFT">
                                                    <img class="add_billing_row" id="add_billing_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                            </fieldset>
                        </div>
                        <div class="WorkLocation"  id="WorkLocationDiv_1" name="WorkLocationDiv_1">
                            <fieldset id="billingWorkfield">
                                <legend> Customer Work Location </legend>  
                                <c:choose>
                                    <c:when test="${fn:length(selectedCustomerWorkLocations)>0}">
                                        <input type="hidden" value="${fn:length(selectedCustomerWorkLocations)}" id="workLocationCount" name="workLocationCount" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" value="1" id="workLocationCount_1" name="workLocationCount_1" />
                                    </c:otherwise>
                                </c:choose>

                                <table class ="customer_work_location" id="customerWorkAddress_1" name="customerWorkAddress_1" border="0" width="99%">
                                    <tr class="headerCenter">
                                        <th>Address<span style="color: red;" for="fine">*</span></th>
                                        <th>Short Code<span style="color: red;" for="fine">*</span></th>
                                        <th>Country<span style="color: red;" for="fine">*</span></th>
                                        <th>Region <span style="color: red;" for="fine">*</span></th>
                                        <th>City<span style="color: red;" for="fine">*</span></th>
                                        <th>Pincode<span style="color: red;" for="fine">*</span></th>
                                        <th>Working Hours<span style="color: red;" for="fine">*</span></th>
                                        <th>Work Location<span style="color: red;" for="fine">*</span></th>
                                        <th>Action</th>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${fn:length(selectedCustomerWorkLocations)>0}">
                                            <c:set var="exwllength"  value="${fn:length(selectedCustomerWorkLocations)}"/>
                                            <input readonly type="hidden" value="${selectedCustomerData[0].status}" id="customerStatus" name="customerStatus" />
                                            <input readonly type="hidden" value="${fn:length(selectedCustomerWorkLocations)}" id="workLocationCount_1" name="workLocationCount_1" />
                                            <c:forEach items="${selectedCustomerWorkLocations}" var="selectedCustomerWorkLocations" varStatus="loop">
                                                <tr id="cust-wl_${loop.index}">
                                                    <td>
                                                        ${selectedCustomerWorkLocations.customerAddress}
                                                        <input type="hidden"  value="${selectedCustomerWorkLocations.id}"   id="customerWorkLocationID_${loop.index}"/>
                                                    </td>
                                                    <td align="center">
                                                        ${selectedCustomerWorkLocations.addressShortCode}
                                                    </td>
                                                    <td  align="center">
                                                        ${selectedCustomerWorkLocations.country}
                                                    </td>
                                                    <td  align="center">
                                                        ${selectedCustomerWorkLocations.region}
                                                    </td>  
                                                    <td  align="center">
                                                        ${selectedCustomerWorkLocations.city}
                                                    </td>
                                                    <td align="center">
                                                        ${selectedCustomerWorkLocations.pincode}
                                                    </td>
                                                    <td>
                                                        ${selectedCustomerWorkLocations.workLocationWorkingHours}
                                                    </td>
                                                    <td>
                                                        ${selectedCustomerWorkLocations.iscompanyLocationId}
                                                    </td>
                                                    <td align="center">
                                                        <img class="edit_wl" src="${pageContext.request.contextPath}/css/images/document-blue.png" alt="Search" id="edit_wl_${loop.index}" title="Edit Address" style="cursor:pointer;">
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                            <tr id="cust-wl_${exwllength}">
                                                <td align="center">
                                                </td>   
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>   
                                                <td align="center">
                                                </td>   
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                </td>
                                                <td align="center">
                                                    <img class="add_customer_wl" id="add_workloc_parent" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr id="cust-wl_1">
                                            <input type="hidden" value="1" id="workLocationCount_1" name="workLocationCount_1" />
                                            <td align="center">
                                                <textarea cols="20" rows="2" name="customerWorkAddressNew_1_1"  id="w_adress_1_1"  maxlength="200"  minlength="10" class="customerWorkAddress" style="min-width: 221px ; max-width: 221px;"></textarea>
                                                <!--<input type="text" name="customerWorkAddressNew_1_1" class="customerWorkAddress" id="customerWorkAddressNew_1_1">-->
                                            </td>
                                            <td align="center">
                                                <input type="text" value="" name="customerWorkShortCodeNew_1_1" id="customerWorkShortCode_1_1" class="custWorkShortCode" maxlength="15">
                                            </td>
                                            <td  align="center">
                                                <select name="customerWorkCountryNew_1_1" class="customerWorkCountry" id="customerWorkCountryNew_1_1" >
                                                    <option value="">-- Select Country -- </option>
                                                    <c:forEach items="${countryList}" var="countryList" >
                                                        <option value="${countryList.countryID}">${countryList.countryName}</option>
                                                    </c:forEach>
                                                </select>
                                            </td>

                                            <td  align="center">
                                                <select name="customerWorkRegionNew_1_1" class="customerWorkRegion" id="customerWorkRegionNew_1_1">
                                                    <option value="">-- Select Region -- </option>
                                                </select>
                                            </td>
                                            <td  align="center">
                                                <select name="customerWorkCityNew_1_1" class="customerWorkCity" id="customerWorkCityNew_1_1">
                                                    <option value="">-- Select City -- </option>
                                                </select>
                                            </td>
                                            <td align="center">
                                                <input type="text" name="customerWorkPincodeNew_1_1" class="customerWorkPincode" id="customerWorkPincodeNew_1_1">
                                            </td>
                                            <td align="center">
                                                <input type="text" name="workingHoursPerDayNew_1_1" class="workingHoursPerDay" id="workingHoursPerDayNew_1_1" placeholder="HH:MM">
                                            </td>
                                            <td>
                                                <select name="workingLocationIsCompanyLocationNew_1_1" id="workingLocationIsCompanyLocationNew_1_1" class="customerWorkLocationDefining textbox_new">
                                                    <option value="">--Select Location--</option>
                                                    <option value="0">Customer Location</option>
                                                    <option value="1">Company Location</option>
                                                </select>
                                            </td>
                                            <td align="center">
                                                <img class="add_customer_wl" id="add_workloc_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                            </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                    </tbody>
                                </table>
                            </fieldset>
                        </div>
                        <div class="businessContact" id="businessContactDiv_1" name="businessContactDiv_1">
                            <fieldset id="businessContactField">
                                <legend> Business Contact </legend>
                                <c:choose>
                                    <c:when test="${fn:length(selectedCustomerContactDetails)>0}">
                                        <input type="hidden" value="${fn:length(selectedCustomerContactDetails)}" name="businessContactCount_1" id="businessContactCount_1"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" value="1" name="businessContactCount_1" id="businessContactCount_1"/>
                                    </c:otherwise>
                                </c:choose>

                                <table class="business_contact" id="businessContact_1" name="businessContact_1" border="0" width="99%">
                                    <tr class="headerCenter">
                                        <th >Name<span style="color: red;" for="fine">*</span></th>
                                        <th >Designation<span style="color: red;" for="fine">*</span> </th>
                                        <th >Phone No.<span style="color: red;" for="fine">*</span></th>
                                        <th >Email Id <span style="color: red;" for="fine">*</span></th>
                                        <th >Action</th>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${fn:length(selectedCustomerContactDetails)>0}">
                                            <c:set var="excctlength"  value="${fn:length(selectedCustomerContactDetails)}"/>
                                            <c:forEach items="${selectedCustomerContactDetails}" var="selectedCustomerContactDetails" varStatus="cust_ct_loop">
                                                <tr id="business_contact_${cust_ct_loop.index}">
                                                    <td style="text-align:left">
                                                        ${selectedCustomerContactDetails.contactPerson}
                                                        <input type="hidden"  id="customerContactID_${cust_ct_loop.index}"  value="${selectedCustomerContactDetails.id}">
                                                    </td>
                                                    <td style="text-align:left">
                                                        ${selectedCustomerContactDetails.contactDesignation}
                                                    </td>
                                                    <td style="text-align:left">
                                                        ${selectedCustomerContactDetails.contactPhone}
                                                    </td>
                                                    <td  style="text-align:left">
                                                        ${selectedCustomerContactDetails.contactEmail}
                                                    </td>
                                                    <td align="center">
                                                        <img class="edit_cc" src="${pageContext.request.contextPath}/css/images/document-blue.png"  id="edit_fc_${fc_loop.index}" title="Edit Address" style="cursor:pointer;">
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            <tr id="business_contact_${excctlength}">
                                                <td style="text-align:left">
                                                </td>
                                                <td style="text-align:left">
                                                </td>
                                                <td  style="text-align:left">
                                                </td>   
                                                <td  style="text-align:left">
                                                </td>
                                                <td align="center">
                                                    <img class="add_customer_contact" id="add_customer_contact_parent" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr id="business_contact_1">
                                            <input type="hidden" value="1" id="customerContactCount" />
                                            <td align="center">
                                                <input type="text" name="customerContactPersonNew_1_1" class="customerContactPerson" id="customerContactPerson_1_1">
                                            </td>
                                            <td align="center">
                                                <input type="text" name="customerContactDesignationNew_1_1" class="customerContactDesignation" id="customerContactDesignation_1_1">
                                            </td>
                                            <td  align="center">
                                                <input type="text" name="customerContactPhoneNew_1_1" class="customerContactPhone" id="customerContactPhone_1_1">
                                            </td>
                                            <td align="center">
                                                <input type="text" name="customerContactEmailNew_1_1" class="customerContactEmail" id="customerContactEmail_1_1">
                                            </td>
                                            <td align="center">
                                                <img class="add_customer_contact" id="add_customer_contact_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" style="cursor:pointer;">
                                            </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                </table>
                            </fieldset>
                        </div>
                        <div class="financeContact" id="financeContactDiv_1"  name="financeContactDiv_1">
                            <fieldset id="FinContactField">
                                <legend> Finance Contact </legend>
                                <c:choose>
                                    <c:when test="${fn:length(selectedFianceContactDetails)>0}">
                                        <input type="hidden" value="${fn:length(selectedFianceContactDetails)}" name="financeContactCount_1" id="financeContactCount_1" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" value="1" name="financeContactCount_1" id="financeContactCount_1" />
                                    </c:otherwise>
                                </c:choose>

                                <table class="finance_contact" id="financeContact_1" name="financeContact_1" border="0" width="99%">
                                    <tr class="headerCenter">
                                        <th >Name<span style="color: red;" for="fine">*</span></th>
                                        <th >Designation<span style="color: red;" for="fine">*</span> </th>
                                        <th >Phone No.<span style="color: red;" for="fine">*</span></th>
                                        <th >Email Id<span style="color: red;" for="fine">*</span></th>
                                        <th >Action</th>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${fn:length(selectedFianceContactDetails)>0}">
                                            <c:set var="exfclength"  value="${fn:length(selectedFianceContactDetails)}"/>
                                            <c:forEach items="${selectedFianceContactDetails}" var="selectedFianceContactDetails" varStatus="fc_loop">
                                                <tr id="finance_contact_${fc_loop.index}">
                                                    <td style="text-align:left">
                                                        <input type="hidden" value="${selectedFianceContactDetails.id}"   id="customerFinanceContactID_${fc_loop.index}"/>
                                                        ${selectedFianceContactDetails.contactPerson}

                                                    </td>
                                                    <td style="text-align:left">
                                                        ${selectedFianceContactDetails.contactDesignation}
                                                    </td>
                                                    <td style="text-align:left">
                                                        ${selectedFianceContactDetails.contactPhone}

                                                    </td>
                                                    <td  style="text-align:left">
                                                        ${selectedFianceContactDetails.contactEmail}

                                                    </td>
                                                    <td align="center">
                                                        <img class="edit_fc" src="${pageContext.request.contextPath}/css/images/document-blue.png"  id="edit_fc_${fc_loop.index}" title="Edit Address" style="cursor:pointer;">
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                            <tr id="finance_contact_${exfclength}">
                                                <td style="text-align:left">
                                                </td>
                                                <td style="text-align:left">
                                                </td>
                                                <td  style="text-align:left">
                                                </td>  
                                                <td  style="text-align:left">
                                                </td>
                                                <td align="center">
                                                    <img class="add_finance_contact" id="add_finance_contact_parent" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="hidden" value="1" id="financeContactCount" />
                                            <tr id="finance_contact_1">
                                                <td align="center">
                                                    <input type="text" name="customerFinanceContactPersonNew_1_1" class="customerFinanceContactPerson" id="customerFinanceContactPerson_1_1">
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="customerFinanceContactDesignationNew_1_1" class="customerFinanceContactDesignation" id="customerFinanceContactDesignation_1_1">
                                                </td>

                                                <td  align="center">
                                                    <input type="text" name="customerFinanceContactPhoneNew_1_1" class="customerFinanceContactPhone" id="customerFinanceContactPhone_1_1">
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="customerFinanceContactEmailNew_1_1" class="customerFinanceContactEmail" id="customerFinanceContactEmail_1_1">
                                                </td>
                                                <td align="center">
                                                    <img class="add_finance_contact" id="add_finance_contact_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                   
                                </table>
                            </fieldset>
                            
                        </div>
                        <!-- dunning contact-->
                        
                        <div class="dunningContact" id="dunningContactDiv_1"  name="dunningContactDiv_1">
                            <fieldset id="DunContactField">
                                <legend> Dunning Report Contact </legend>
                                <c:choose>
                                    <c:when test="${fn:length(selectedDunningContactDetails)>0}">
                                        <input type="hidden" value="${fn:length(selectedDunningContactDetails)}" name="dunningContactCount_1" id="dunningContactCount_1" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" value="1" name="dunningContactCount_1" id="dunningContactCount_1" />
                                    </c:otherwise>
                                </c:choose>

                                <table class="dunning_contact" id="dunningContact_1" name="dunningContact_1" border="0" width="99%">
                                    <tr class="headerCenter">
                                        <th >Name<span style="color: red;" for="fine">*</span></th>
                                        <th >Designation<span style="color: red;" for="fine">*</span> </th>
                                        <th >Phone No.<span style="color: red;" for="fine">*</span></th>
                                        <th >Email Id<span style="color: red;" for="fine">*</span></th>
                                        <th >Action</th>
                                    </tr>
                                    <c:choose>
                                        <c:when test="${fn:length(selectedDunningContactDetails)>0}">
                                            <c:set var="exfclength"  value="${fn:length(selectedDunningContactDetails)}"/>
                                            <c:forEach items="${selectedDunningContactDetails}" var="selectedDunningContactDetails" varStatus="dc_loop">
                                                <tr id="dunning_contact_${dc_loop.index}">
                                                    <td style="text-align:left">
                                                        <input type="hidden" value="${selectedDunningContactDetails.id}"   id="customerDunningContactID_${dc_loop.index}"/>
                                                        ${selectedDunningContactDetails.contactPerson}
                                                    </td>
                                                    <td style="text-align:left">
                                                        ${selectedDunningContactDetails.contactDesignation}
                                                    </td>
                                                    <td style="text-align:left">
                                                        ${selectedDunningContactDetails.contactPhone}
                                                    </td>
                                                    <td  style="text-align:left">
                                                        ${selectedDunningContactDetails.contactEmail}
                                                    </td>
                                                    <td align="center">
                                                        <img class="edit_dc" src="${pageContext.request.contextPath}/css/images/document-blue.png"  id="edit_dc_${dc_loop.index}" title="Edit Address" style="cursor:pointer;">
                                                    </td>
                                                </tr> 
                                            </c:forEach>
                                            <tr id="finance_contact_${exdclength}">
                                                <td style="text-align:left">
                                                </td>
                                                <td style="text-align:left">
                                                </td>
                                                <td  style="text-align:left">
                                                </td>  
                                                <td  style="text-align:left">
                                                </td>
                                                <td align="center">
                                                    <img class="add_dunning_contact" id="add_dunning_contact_parent" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="hidden" value="1" id="dunningContactCount" />
                                            <tr id="dunning_contact_1">
                                                <td align="center">
                                                    <input type="text" name="customerDunningContactPersonNew_1_1" class="customerDunningContactPerson" id="customerDunningContactPerson_1_1">
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="customerDunningContactDesignationNew_1_1" class="customerDunningContactDesignation" id="customerDunningContactDesignation_1_1">
                                                </td>

                                                <td  align="center">
                                                    <input type="text" name="customerDunningContactPhoneNew_1_1" class="customerDunningContactPhone" id="customerDunningContactPhone_1_1">
                                                </td>
                                                <td align="center">
                                                    <input type="text" name="customerDunningContactEmailNew_1_1" class="customerDunningContactEmail" id="customerDunningContactEmail_1_1">
                                                </td>
                                                <td align="center">
                                                    <img class="add_dunning_contact" id="add_dunning_contact_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" style="cursor:pointer;">
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                   
                                </table>
                            </fieldset>
                            
                        </div>
                        
                        
                        <div class="financeContact" id="financeContactDiv_1"  name="financeContactDiv_1">
                            <fieldset id="FinContactField">
                                <legend> Invoice Recipient</legend>
                                <table class="finance_contact" id="financeContact_1" name="financeContact_1" border="0" width="100%" align="center">
                                    <tbody>
                                        <tr>     
                                            <input type="hidden" name="preInvoiceTo" id="preInvoiceTo" value="${preInvoice}"/>    
                                            <td style="padding-top:10px;width:150px;"><b>Invoice will be Emailed To</b><span style="color: red;" for="fine">*</span> </td>
                                            <td>
                                                <select name="invoiceTo_1" id="invoiceTo_1" class="custInvoice required textbox_new country_select" style="width:150px;">
                                                    
                                                        <c:forEach items="${invoiceList}" var="invoiceObj" >
                                                             <option value="${invoiceObj.configKey}" ${selectedCustomerData[0].invoiceTo == invoiceObj.configKey?'selected="selected"':''}> ${invoiceObj.configValue}</option>
                                                        </c:forEach>
                                                </select> 
                                            </td>
                                            <td></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </fieldset>  
                            <c:if test ="${selectedCustomerData[0].remarks!= null &&  fn:length(selectedCustomerData[0].remarks)>0}">
                                    <tr>
                                        <td>
                                            <label class="formLabel">Remarks:</label>
<!--                                            &nbsp&nbsp&nbsp&nbsp&nbsp<span class="" style="font-weight:bold">Remarks:</span>-->

                                        </td>
                                        <td>
                                            <textarea name="remarks" id="remarks" class="required textbox_new formInput" maxlength="200"  minlength="10" onblur="textLimit(this, 200)" onkeyup="textLimit(this, 200)" class="customerBillingAddress" style="min-width: 221px ;max-width: 221px;">${selectedCustomerData[0].remarks}</textarea>
                                        </td>
                                    </tr>
                                </c:if>
                        </div>
                    </div>
                </div>
                         <span id="errShortCode"></span>
                         <span id="errShortCode1"></span>
                <p class="errorText">Please enter email format (e.g) abcdef@hindujatech.com</p>
                <p class="errorTextGst">Please enter GST number for Indian Customers</p>
                <p class="errorText1">Please enter email format (e.g) abcdef@hindujatech.com</p> 
                <p class="errorTextWorkHrs">Please enter working hours in hh:mm format (e.g)08:30 and less than 12:00</p>
                <p id="invoiceerrormessage" class="errorMessage">Please select invoice recipient type</p>
                <div class="buttonAlignment">
                    <div class="buttonAlignment" id="btnGroup">
                        <input type="hidden" name="attchedFileName" id="attchedFileName" value="${selectedCustomerData[0].fileinsertId}" />
                        <input type="hidden" name="customerID" id="customerID"  value="${selectedCustomerData[0].custID}">
                        <input type="hidden" name="parentId" id="parentId"  value="${selectedCustomerData[0].parentId}">
                        <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}" />
                        <input type="hidden" name="prevStatus" id="prevStatus" value="${selectedCustomerData[0].status}" />
                        <input type="hidden" name="customerCode" id="customerCode" value="${selectedCustomerData[0].customerCode}" />
                        <input type="hidden" name="action" id="action" />
                        <input type="button" name="btnCancel" id="btnCancel" value="Back" style="cursor:pointer;" class="qualityback" onclick="javascript: location.href = 'customersList.htm'">
                        <c:if test="${selectedCustomerData[0].status == 's'}">
                            <input type="button" name="customerSaveButton" id="saveBtn" value="Save" style="width: 90px;cursor: pointer;" class="qualitysave" onclick="">
                            <input type="button" name="customerSubmitButton" id="submitBtn" value="Submit" style="width: 90px;cursor: pointer;" class="qualitysubmit" onclick="">
                        </c:if>
                        <c:if test="${selectedCustomerData[0].status == 'a' || selectedCustomerData[0].status == 'r'}">
                            <input type="button" name="customerSubmitButton" id="submitBtn" value="Submit" style="width: 90px;cursor: pointer;" class="qualitysubmit">
                        </c:if>
<!--                        <input type="button" name="btnCancel" id="btnCancel" value="Back" class="qualityback" href="customerDivisionList.htm?customerName=${selectedCustomerData[0].customerName}" style="cursor: pointer">-->

                    </div>
                </div>

            </div>

        </form>
    </div>

    <div id="RemoveIndividualDiv" style="position:absolute;width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119,0.5);display: none; top: 0pt; left: 0pt;color:#000;">
        <div id="RemoveIndividualDivFocus" style="position: absolute;z-index: 150;top: 20%;left:35%;text-align: center;font-size: 14px;background-color: #fff;width: auto;height:auto;padding: 20px;border-radius: 5px;">
            <p style="padding:10px;margin:10px;">Are You sure want to remove the Division?</p><p class="plainButton" id="removeDivYes">YES</p><p class="plainButton" id="removeDivNo">NO</p>
        </div>
    </div>

    <div class="alertboxWrap">
        <div class="alertbox dialog_alert">
            <p id="appendTable"></p>

            <div id="contact_butons">
                <button class="dialog_submit_button " id="dialog_submit" style='cursor:pointer;'>Choose</button>
                <button class="dialog_cancel_button" id="dialog_cancel" style='cursor:pointer;'>Cancel</button>
            </div>
        </div>
    </div>
    <!--<div class="alertboxWrap errorAlertWrap" id="errorAlert">
        <div class="alertbox dialog_alert">
            <p class="errorAlertText">Please correct the error fields and Submit!</br></br> - Please enter Terms of payment between 0 and 365</br> - Mandatory fields should not be null</p>
            <div id="contact_butons">
                <button class="dialog_cancel_button" id="errorAlertCancel" style='cursor:pointer;'>Close</button>
            </div>
        </div>
    </div>  -->
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
            }
            else if (ns6 || ie4)
                loadingDivObj.display = "none";
            $("#loadingImage").attr("tabindex", -1).focus();
        }

        function startLoading() {
            var loaderStartHeight = $("html").height();
            loadingDivObj.height = loaderStartHeight + "px";
            if (ns4) {
                loadingDivObj.visibility = "visible";
                $("#loadingImage").attr("tabindex", -1).focus();
            }
            else if (ns6 || ie4)
                loadingDivObj.display = "block";
            $("#loadingImage").attr("tabindex", -1).focus();
        }

        function getSalesPersonPhoneNumber(selectedValue) {
            $.ajax({
                url: './getSalesPersonPhoneNumber.htm?id=' + selectedValue,
                type: "POST",
                async: false,
                data: ({id: selectedValue}),
                success: function(response) {
                    $('#salesPersonContactNo').val(response);
                }
            });
        }





        $(document).ready(function() {
            $("#successDiv").fadeOut(2000);
            if (typeof String.prototype.trim !== 'function') {
                String.prototype.trim = function() {
                    return this.replace(/^\s+|\s+$/g, '');
                };
            }
            $( "body" ).on('input','.custBillingShortCode', function() {
                if ($(this).val().length>=15) {
                    alert('Short code length 1-15 you reached the limit');       
                }
            });
            
            $( "body").on('input','.custWorkShortCode', function() {
                if ($(this).val().length>=15) {
                    alert('Short code length 1-15 you reached the limit');       
                }
            });
            
            $('#addDivisionName').on('click',function(){
                var checkedValue=$(this).prop('checked');
                if(checkedValue) {                    
                    $(".addDivisionDiv").css({"visibility":"visible"});
                    $(".addDivisionDiv").css({"display":"inline-block"});
                }else {
                    $(".addDivisionDiv").css({"visibility":"hidden"});
                    $(".addDivisionDiv").css({"display":"none"});
                    $("#division_1").val('');
                    var custDetailsLastID= $(".customerDetails").last().attr("id");
                    var custDetailsLengthToRemove = custDetailsLastID.split('_')[1]; 
                    var RemoveDivHeight = $("html").height();
                    if($(".customerDetails").length > 1){
                        $("#RemoveAllDiv").css({"height":RemoveDivHeight,"display":"block"});
                        $("#RemoveAllDivFocus").attr("tabindex",-1).focus();
                        $("body").on('click','#removeAllDivNo',function(){ 
                            $("#RemoveAllDiv").css({"display":"none"});
                            $("#addDivisionName").prop("checked",true);
                            $(".addDivisionDiv").css({"visibility":"visible"});
                            $(".addDivisionDiv").css({"display":"inline-block"});
                        });
                        $("body").on('click','#removeAllDivYes',function(){ 
                            for(var q=2;q<=custDetailsLengthToRemove;q++){
                                $("#customerDetails_"+q).remove();
                            } 
                            $("#RemoveAllDiv").css({"display":"none"});
                            $("#division_1").val('');
                        });
                    }
                }
            });
            
            var salesPersonIDVal = document.getElementById("salesPerson").value;
            getSalesPersonPhoneNumber(salesPersonIDVal);



            var divisionLength = $(".customerDetails").length;
            divisionLength++;
            $('input[readonly]').keydown(function(event) {
                if (event.which == 8) {
                    event.preventDefault();
                }
            });
            $("body").on("click", '.delete_row', function() {
                var Row1 = $(this).parent().parent();
                Row1.remove();
            });
            stopLoading();




            /*-------customercontactstart-------------*/
            $("body").on("click", '.add_customer_contact', function() {
                var theRow = $(this).parent().parent();
                var BusinessContactId = $(this).parent().parent().parent().parent().attr("id");
                var BusinessContactIdNum = BusinessContactId.split('_')[1];
                var rowData = "";
                var businessContactCount = $('#businessContactCount_' + BusinessContactIdNum).val();
                var cnt = parseInt(businessContactCount) + 1;
                rowData += '<td align="center">';
                rowData += '<input type="text" name="customerContactPersonNew_' + BusinessContactIdNum + '_' + cnt + '" class="customerContactPerson" id="customerContactPerson_' + BusinessContactIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td  align="center">';
                rowData += '<input type="text" name="customerContactDesignationNew_' + BusinessContactIdNum + '_' + cnt + '" class="customerContactDesignation" id="customerContactDesignation_' + BusinessContactIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td  align="center">';
                rowData += '<input type="text" name="customerContactPhoneNew_' + BusinessContactIdNum + '_' + cnt + '" class="customerContactPhone" id="customerContactPhone_' + BusinessContactIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td align="center">';
                rowData += '<input type="text" name="customerContactEmailNew_' + BusinessContactIdNum + '_' + cnt + '" class="customerContactEmail" id="customerContactEmail_' + BusinessContactIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td align="center">';
                rowData += '<img class="add_customer_contact" id="add_customer_contact_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" style="cursor:pointer;">';
                rowData += '&nbsp;';
                rowData += '<img class="delete_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;"/>';
                rowData += '</td>';
                if ($(this).attr('id') == 'add_customer_contact_parent')
                    $(theRow).before('<tr id="business_contact_' + BusinessContactIdNum + '_' + cnt + '">' + rowData + '</tr>');
                else
                    $(theRow).after('<tr id="business_contact_' + BusinessContactIdNum + '_' + cnt + '">' + rowData + '</tr>');
                $('#businessContactCount_' + BusinessContactIdNum).val(cnt);
            });
            $('body').on('click', '.reset_cct', function(e) {
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var index = id.substring(17);
                var pk = $('#customerContactID_' + index).val();
                if (modelObject.getCustomerContacts().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getCustomerContacts()[pk];
                    var content = '';
                    content += '<tr id="business_contact_' + index + '">';
                    content += '<td>';
                    content += '<input type="hidden" value="' + ex_object.id + '"   id="customerContactID_' + index + '"/>';
                    content += ex_object.contactPerson;
                    content += '</td>';
                    content += '<td>';
                    content += ex_object.contactDesignation;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.contactPhone;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.contactEmail;
                    content += '</td>';
                    content += '<td align="center">';
                    content += '  <img class="edit_cc" src="${pageContext.request.contextPath}/css/images/document-blue.png"  id="edit_cc_' + index + '" title="Edit Address" style="cursor:pointer;">';
                    content += '</td>';
                    content += '</tr>';
                    $("#business_contact_" + index).replaceWith(content);
                }
            });

            $("body").on("click", ".edit_cc", function() {  //finance_contact_
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var row_index = id.substring(17);
                var pk = $('#customerContactID_' + row_index).val();
                if (modelObject.getCustomerContacts().hasOwnProperty(pk)) {

                    var ex_object = modelObject.getCustomerContacts()[pk];

                    if (typeof ex_object.contactPerson == "undefined") {
                        ex_object.contactPerson = "";
                    }
                    if (typeof ex_object.contactDesignation == "undefined") {
                        ex_object.contactDesignation = "";
                    }
                    if (typeof ex_object.contactPhone == "undefined") {
                        ex_object.contactPhone = "";
                    }
                    if (typeof ex_object.contactEmail == "undefined") {
                        ex_object.contactEmail = "";
                    }

                    var content = '';
                    content += '<tr id="business_contact_' + row_index + '">';
                    content += '<td align="center">';
                    content += '<input type="hidden" value="' + ex_object.id + '" name="customerContactID" id="customerContactID_' + row_index + '" />';
                    content += '<input type="text" value="' + ex_object.contactPerson + '" name="customerContactPerson" id="customerContactPerson_' + row_index + '" class="customerContactPerson"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactDesignation + '" name="customerContactDesignation" id="customerContactDesignation_' + row_index + '" class="customerContactDesignation"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactPhone + '" name="customerContactPhone" id="customerContactPhone_' + row_index + '" class="customerContactPhone"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactEmail + '"  name="customerContactEmail" id="customerContactEmail_' + row_index + '" class="customerContactEmail"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<img class="reset_cct" src="${pageContext.request.contextPath}/css/images/reset.png" alt=""  id="reset_cct_' + row_index + '" title="Edit Address" style="cursor:pointer;display:none">';
                    content += '</td>';
                    content += '</tr>';
                    $("#business_contact_" + row_index).replaceWith(content);
                }
            });
            
            /*-------customercontactend-------------*/










            /*-------customerfinancecontactstart-------------*/


            $("body").on("click", '.add_finance_contact', function() {
                var theRow = $(this).parent().parent();
                var FinanceContactDivId = $(this).parent().parent().parent().parent().attr("id");
                var FinanceContactDivIdNum = FinanceContactDivId.split('_')[1];
                var rowData = "";
                var financeContactCount = $('#financeContactCount_' + FinanceContactDivIdNum).val();
                var cnt = parseInt(financeContactCount) + 1;

                rowData += '<td align="center">';
                rowData += '<input type="text" name="customerFinanceContactPersonNew_' + FinanceContactDivIdNum + '_' + cnt + '" class="customerFinanceContactPerson" id="customerFinanceContactPerson_' + FinanceContactDivIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td  align="center">';
                rowData += '<input type="text" name="customerFinanceContactDesignationNew_' + FinanceContactDivIdNum + '_' + cnt + '" class="customerFinanceContactDesignation" id="customerFinanceContactDesignation_' + FinanceContactDivIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td  align="center">';
                rowData += '<input type="text" name="customerFinanceContactPhoneNew_' + FinanceContactDivIdNum + '_' + cnt + '" class="customerFinanceContactPhone" id="customerFinanceContactPhone_' + FinanceContactDivIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td align="center">';
                rowData += '<input type="text" name="customerFinanceContactEmailNew_' + FinanceContactDivIdNum + '_' + cnt + '" class="customerFinanceContactEmail" id="customerFinanceContactEmail_' + FinanceContactDivIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td align="center">';
                rowData += '<img class="add_finance_contact" id="add_finance_contact_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" style="cursor:pointer;">';
                rowData += '&nbsp;';
                rowData += '<img class="delete_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;" />';
                rowData += '</td>';
                if ($(this).attr('id') == 'add_finance_contact_parent')
                    $(theRow).before('<tr id="finance_contact_' + FinanceContactDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
                else
                    $(theRow).after('<tr id="finance_contact_' + FinanceContactDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
                $('#financeContactCount_' + FinanceContactDivIdNum).val(cnt);
            });

            $("body").on("click", '.add_dunning_contact', function() {
                var theRow = $(this).parent().parent();
                var DunningContactDivId = $(this).parent().parent().parent().parent().attr("id");
                var DunningContactDivIdNum = DunningContactDivId.split('_')[1];
                var rowData = "";
                var dunningContactCount = $('#dunningContactCount_' + DunningContactDivIdNum).val();
                var cnt = parseInt(dunningContactCount) + 1;

                rowData += '<td align="center">';
                rowData += '<input type="text" name="customerDunningContactPersonNew_' + DunningContactDivIdNum + '_' + cnt + '" class="customerDunningContactPerson" id="customerDunningContactPerson_' + DunningContactDivIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td  align="center">';
                rowData += '<input type="text" name="customerDunningContactDesignationNew_' + DunningContactDivIdNum + '_' + cnt + '" class="customerDunningContactDesignation" id="customerDunningContactDesignation_' + DunningContactDivIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td  align="center">';
                rowData += '<input type="text" name="customerDunningContactPhoneNew_' + DunningContactDivIdNum + '_' + cnt + '" class="customerDunningContactPhone" id="customerDunningContactPhone_' + DunningContactDivIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td align="center">';
                rowData += '<input type="text" name="customerDunningContactEmailNew_' + DunningContactDivIdNum + '_' + cnt + '" class="customerDunningContactEmail" id="customerDunningContactEmail_' + DunningContactDivIdNum + '_' + cnt + '">';
                rowData += '</td>';
                rowData += '<td align="center">';
                rowData += '<img class="add_dunning_contact" id="add_dunning_contact_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" style="cursor:pointer;">';
                rowData += '&nbsp;';
                rowData += '<img class="delete_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;" />';
                rowData += '</td>';
                if ($(this).attr('id') == 'add_dunning_contact_parent')
                    $(theRow).before('<tr id="dunning_contact_' + DunningContactDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
                else
                    $(theRow).after('<tr id="dunning_contact_' + DunningContactDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
                $('#dunningContactCount_' + DunningContactDivIdNum).val(cnt);
            });



            $('body').on('click', '.reset_cfc', function(e) {
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var index = id.substring(16);
                var pk = $('#customerFinanceContactID_' + index).val();
                if (modelObject.getCustomerFinanceContacts().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getCustomerFinanceContacts()[pk];



                    if (typeof ex_object.contactPerson == "undefined") {
                        ex_object.contactPerson = "";
                    }
                    if (typeof ex_object.contactDesignation == "undefined") {
                        ex_object.contactDesignation = "";
                    }
                    if (typeof ex_object.contactPhone == "undefined") {
                        ex_object.contactPhone = "";
                    }
                    if (typeof ex_object.contactEmail == "undefined") {
                        ex_object.contactEmail = "";
                    }
                    var content = '';
                    content += '<tr id="finance_contact_' + index + '">';
                    content += '<td>';
                    content += '<input type="hidden" value="' + ex_object.id + '"   id="customerFinanceContactID_' + index + '"/>';
                    content += ex_object.contactPerson;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.contactDesignation;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.contactPhone;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.contactEmail;
                    content += '</td>';
                    content += '<td align="center">';
                    content += '  <img class="edit_fc" src="${pageContext.request.contextPath}/css/images/document-blue.png"  id="edit_dc_' + index + '" title="Edit Address" style="cursor:pointer;">';
                    content += '</td>';
                    content += '</tr>';
                    $("#finance_contact_" + index).replaceWith(content);
                }
            });


            $("body").on("click", ".edit_dc", function() {  //dunning contact
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var row_index = id.substring(16);
                var pk = $('#customerDunningContactID_' + row_index).val();
                if (modelObject.getCustomerDunningContacts().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getCustomerDunningContacts()[pk];

                    if (typeof ex_object.contactPerson == "undefined") {
                        ex_object.contactPerson = "";
                    }
                    if (typeof ex_object.contactDesignation == "undefined") {
                        ex_object.contactDesignation = "";
                    }
                    if (typeof ex_object.contactPhone == "undefined") {
                        ex_object.contactPhone = "";
                    }
                    if (typeof ex_object.contactEmail == "undefined") {
                        ex_object.contactEmail = "";
                    }
                    var content = '';
                    content += '<tr id="dunning_contact_' + row_index + '">';
                    content += '<td align="center">';
                    content += '<input type="hidden" value="' + ex_object.id + '" name="customerDunningContactID" id="customerDunningContactID_' + row_index + '" />';
                    content += '<input type="text" value="' + ex_object.contactPerson + '" name="customerDunningContactPerson" id="customerDunningContactPerson_' + row_index + '" class="customerDunningContactPerson"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactDesignation + '" name="customerDunningContactDesignation" id="customerDunningContactDesignation_' + row_index + '" class="customerDunningContactDesignation"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactPhone + '" name="customerDunningContactPhone" id="customerDunningContactPhone_' + row_index + '" class="customerDunningContactPhone"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactEmail + '"  name="customerDunningContactEmail" id="customerDunningContactEmail_' + row_index + '" class="customerDunningContactEmail"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<img class="reset_dfc" src="${pageContext.request.contextPath}/css/images/reset.png" alt=""  id="reset_fc_' + row_index + '" title="Edit Address" style="cursor:pointer;display:none">';
                    content += '</td>';
                    content += '</tr>';
                    $("#dunning_contact_" + row_index).replaceWith(content);
                }
            });
            
            $('body').on('click', '.reset_dfc', function(e) {
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var index = id.substring(16);
                var pk = $('#customerDunningContactID_' + index).val();
                if (modelObject.getCustomerDunningContacts().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getCustomerDunningContacts()[pk];
                    if (typeof ex_object.contactPerson == "undefined") {
                        ex_object.contactPerson = "";
                    }
                    if (typeof ex_object.contactDesignation == "undefined") {
                        ex_object.contactDesignation = "";
                    }
                    if (typeof ex_object.contactPhone == "undefined") {
                        ex_object.contactPhone = "";
                    }
                    if (typeof ex_object.contactEmail == "undefined") {
                        ex_object.contactEmail = "";
                    }
                    var content = '';
                    content += '<tr id="dunning_contact_' + index + '">';
                    content += '<td>';
                    content += '<input type="hidden" value="' + ex_object.id + '"   id="customerDunningContactID_' + index + '"/>';
                    content += ex_object.contactPerson;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.contactDesignation;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.contactPhone;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.contactEmail;
                    content += '</td>';
                    content += '<td align="center">';
                    content += '  <img class="edit_dc" src="${pageContext.request.contextPath}/css/images/document-blue.png"  id="edit_dc_' + index + '" title="Edit Address" style="cursor:pointer;">';
                    content += '</td>';
                    content += '</tr>';
                    $("#dunning_contact_" + index).replaceWith(content);
                }
            });


            /*-------customerfinancecontactend-------------*/
            $("body").on("click", ".edit_fc", function() {  //finance_contact_
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var row_index = id.substring(16);
                var pk = $('#customerFinanceContactID_' + row_index).val();
                if (modelObject.getCustomerFinanceContacts().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getCustomerFinanceContacts()[pk];

                    if (typeof ex_object.contactPerson == "undefined") {
                        ex_object.contactPerson = "";
                    }
                    if (typeof ex_object.contactDesignation == "undefined") {
                        ex_object.contactDesignation = "";
                    }
                    if (typeof ex_object.contactPhone == "undefined") {
                        ex_object.contactPhone = "";
                    }
                    if (typeof ex_object.contactEmail == "undefined") {
                        ex_object.contactEmail = "";
                    }
                    var content = '';
                    content += '<tr id="finance_contact_' + row_index + '">';
                    content += '<td align="center">';
                    content += '<input type="hidden" value="' + ex_object.id + '" name="customerFinanceContactID" id="customerFinanceContactID_' + row_index + '" />';
                    content += '<input type="text" value="' + ex_object.contactPerson + '" name="customerFinanceContactPerson" id="customerFinanceContactPerson_' + row_index + '" class="customerFinanceContactPerson"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactDesignation + '" name="customerFinanceContactDesignation" id="customerFinanceContactDesignation_' + row_index + '" class="customerFinanceContactDesignation"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactPhone + '" name="customerFinanceContactPhone" id="customerFinanceContactPhone_' + row_index + '" class="customerFinanceContactPhone"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<input type="text" value="' + ex_object.contactEmail + '"  name="customerFinanceContactEmail" id="customerFinanceContactEmail_' + row_index + '" class="customerFinanceContactEmail"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += '<img class="reset_cfc" src="${pageContext.request.contextPath}/css/images/reset.png" alt=""  id="reset_fc_' + row_index + '" title="Edit Address" style="cursor:pointer;display:none">';
                    content += '</td>';
                    content += '</tr>';
                    $("#finance_contact_" + row_index).replaceWith(content);
                }
            });
            /*--------------------customer billing ------------------------------*/

            $("body").on("click", '.add_billing_row', function() {
                addBillingLocation(this);
            });
            $("body").on("change", ".customerBillingCountry", function() { //23
                var index = this.id;
                if (index.length > 23) {
                    index = index.substring(23);
                    var country_id = $("#" + this.id).val();
                    var region_id = null;
                    if(country_id==113){
                        $("#customerBillingGstCode_"+index).css({"display":"table-cell"});
                        if(index.length==1){
                            $("#customerBillingGstNumber_"+index).css({"display":"table-cell"});
                        }else{
                            $("#customerBillingGstNumberNew_"+index).css({"display":"table-cell"});
                        }
                    }else{
                        $("#customerBillingGstCode_"+index).css({"display":"none"});
                        if(index.length==1){
                            $("#customerBillingGstNumber_"+index).css({"display":"none"});
                        }else{
                            $("#customerBillingGstNumberNew_"+index).css({"display":"none"});
                        }
                    }
                    getRegions(country_id, region_id, function(obj) {
                        $("#customerBillingRegion_" + index).html(obj);
                        $("#customerBillingCity_"+index).html('<option value="">-- Select City -- </option></select>');
                    });
                } else {
                    index = index.substring(23);
                    var country_id = $("#" + this.id).val();
                    var region_id = null;
                    getRegions(country_id, region_id, function(obj) {
                        $("#customerBillingRegion_" + index).html(obj);
                        $("#customerBillingCity_"+index).html('<option value="">-- Select City -- </option></select>');
                    });
                }

            });

            $("body").on("change", ".customerBillingRegion", function() { //22
                var index = this.id;
                index = index.substring(22);
                var country_id = $("#customerBillingCountry_" + index).val();
                var region_id = $("#" + this.id).val();
                var city_id = null;
                getCities(country_id, region_id, city_id, function(obj) {
                    $("#customerBillingCity_" + index).html(obj);
                });
                getGstCode(country_id, region_id, city_id, function(obj) {
                    $("#customerBillingGstCode_" + index).val(obj);
                });
            });
            
            function addBillingLocation(rowObject) {
                var theRow = $(rowObject).parent().parent();
                var businessDivId = $(rowObject).parent().parent().parent().parent().attr("id");
                var businessDivIdNum = businessDivId.split('_')[1];
                var rowData = "";
                var businessAddressCount = $('#businessAddressCount_' + businessDivIdNum).val();
                var cnt = parseInt(businessAddressCount) + 1;
                rowData += '<td align="center"><textarea type="text" name="customerBillingAddressNew_' + businessDivIdNum + '_' + cnt + '" class="customerBillingAddress" id="customerBillingAddress_' + businessDivIdNum + '_' + cnt + '" style="min-width: 221px ;max-width: 221px !important;"></textarea></td>';
                rowData += '<td><input type="text" value="" name="customerBillingShortCodeNew_' + businessDivIdNum + '_' + cnt + '" id="customerBillingShortCode_' + businessDivIdNum + '_' + cnt + '" class="custBillingShortCode" maxlength="15"></td>';
                rowData += '<td  align="center">';
                rowData += '<select name="customerBillingCountryNew_' + businessDivIdNum + '_' + cnt + '" id="customerBillingCountry_' + businessDivIdNum + '_' + cnt + '" class="customerBillingCountry">';
                rowData += '<option value="">-- Select Country -- </option>';
                rowData += '<c:forEach items="${countryList}" var="countryList" >';
                rowData += '<option value="${countryList.countryID}">${countryList.countryName}</option>';
                rowData += '</c:forEach>';
                rowData += '</select>';
                rowData += '</td>';
                rowData += '<td  align="center">';
                rowData += '<select name="customerBillingRegionNew_' + businessDivIdNum + '_' + cnt + '" class="customerBillingRegion" id="customerBillingRegion_' + businessDivIdNum + '_' + cnt + '" >';
                rowData += '<option value="">-- Select Region -- </option></select></td>';
                rowData += '<td  align="center">';
                rowData += '<select name="customerBillingCityNew_' + businessDivIdNum + '_' + cnt + '" class="customerBillingCity" id="customerBillingCity_' + businessDivIdNum + '_' + cnt + '" >';
                rowData += '<option value="">-- Select City -- </option></select></td>';
                rowData += '<td align="center"><input type="text" class="customerBillingPincode" name="customerBillingPincodeNew_' + businessDivIdNum + '_' + cnt + '" id="customerBillingPincode_' + businessDivIdNum + '_' + cnt + '"></td>';
                rowData += '<td align="center"><input type="text" name="customerBillingGstCodeNew_'+ businessDivIdNum +'_'+ cnt +'" id="customerBillingGstCode_' + businessDivIdNum + '_' + cnt + '" readonly class="custBillingGstCode" style="display:none;"/></td>';
                rowData += '<td align="center"><input type="text" name="customerBillingGstNumberNew_'+ businessDivIdNum +'_'+ cnt +'" id="customerBillingGstNumberNew_' + businessDivIdNum + '_' + cnt + '" class="customerBillingGstNumber" style="display:none;"/></td>';
                rowData += '<td align="center">';
                rowData += '<img class="add_billing_row" id="add_billing_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                rowData += '&nbsp;<img class="delete_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;"/>';
                rowData += '</td>';
                if ($(rowObject).attr('id') == 'add_billing_parent')
                    $(theRow).before('<tr id="cust_billing_' + businessDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
                else
                    $(theRow).after('<tr id="cust_billing_' + businessDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
                $('#businessAddressCount_' + businessDivIdNum).val(cnt);

            }
            $("body").on("click", ".edit_cb", function() {
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var row_index = id.substring(13); // cust_billing_
                var pk = $('#customerBillingID_' + row_index).val();
                if (modelObject.getBillingAddress().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getBillingAddress()[pk];


                    if (typeof ex_object.customerAddress == "undefined") {
                        ex_object.customerAddress = "";
                    }
                    if (typeof ex_object.addressShortCode == "undefined") {
                        ex_object.addressShortCode = "";
                    }
                    if (typeof ex_object.countryID == "undefined") {
                        ex_object.countryID = "";
                    }
                    if (typeof ex_object.regionID == "undefined") {
                        ex_object.regionID = "";
                    }
                    if (typeof ex_object.cityID == "undefined") {
                        ex_object.cityID = "";
                    }
                    if (typeof ex_object.pincode == "undefined") {
                        ex_object.pincode = "";
                    }
                    if (typeof ex_object.gstCode == "undefined") {
                        ex_object.gstCode = "";
                    }
                    if (typeof ex_object.gstNumber == "undefined") {
                        ex_object.gstNumber = "";
                    }


                    
                    var region_obj = '';
                    var city_obj = '';
                    var Cn_selected = '';
                    var content = '';
                    var check_id = ex_object.countryID;
                    content += '<tr id="cust_billing_' + row_index + '">';
                    content += '<td id="cust_billing_address_' + row_index + '">';
                    content += '<textarea name="customerBillingAddress" class="customerBillingAddress"  id="customerBillingAddress_' + row_index + '" value="' + ex_object.customerAddress + '" style="min-width:221px; max-width:221px;">' + ex_object.customerAddress + '</textarea>';
                    content += '<input type="hidden"  name="customerBillingID" value="' + pk + '"  id="customerBillingID_' + row_index + '"/>';
                    content += '</td>';
                    content += '<td><input type="text" maxlength="15" name="customerBillingShortCode" id="customerBillingShortCode_' + row_index + '" class="custBillingShortCode" value="' + ex_object.addressShortCode + '"></td>';
                    content += '<td>';
                    content += '<select name="customerBillingCountry" class="customerBillingCountry" id="customerBillingCountry_' + row_index + '">';
                    content += '<option value="">-- Select Country -- </option>';
                    <c:forEach items="${countryList}" var="countryList">
                        if (ex_object.countryID == ${countryList.countryID}) {
                            Cn_selected = "selected";
                        }
                        content += '<option value="${countryList.countryID}" ' + Cn_selected + ' >${countryList.countryName}</option>';
                        Cn_selected = '';
                    </c:forEach>
                    content += '</select></td>';
                    content += '<td align="center">';
                    content += '<select name="customerBillingRegion" class="customerBillingRegion" id="customerBillingRegion_' + row_index + '">';
                    content += '</select></td>';
                    content += '<td align="center">';
                    content += '<select name="customerBillingCity" class="customerBillingCity" id="customerBillingCity_' + row_index + '">';
                    content += '</select></td>';
                    content += '<td><input type="text" name="customerBillingPincode" class="customerBillingPincode" value="' + ex_object.pincode + '" id="customerBillingPincode_' + row_index + '" ></td>';
                    content += '<td>';
                    if(check_id === '113') {
                        content += '<input type="text" name="customerBillingGstCode" readonly class="customerBillingGstCode" value="' + ex_object.gstCode + '" id="customerBillingGstCode_' + row_index + '" >';
                    }else{
                        content += '<input type="text" name="customerBillingGstCode" readonly class="customerBillingGstCode" value="' + ex_object.gstCode + '" id="customerBillingGstCode_' + row_index + '" style="display:none">';
                    }
                    content += '</td>';
                    content += '<td>';
                    if(check_id === '113') {
                        content += '<input type="text" name="customerBillingGstNumber" class="customerBillingGstNumber" value="' + ex_object.gstNumber + '" id="customerBillingGstNumber_' + row_index + '" >';
                    }else{
                        content += '<input type="text" name="customerBillingGstNumber" class="customerBillingGstNumber" value="' + ex_object.gstNumber + '" id="customerBillingGstNumber_' + row_index + '" style="display:none">';
                    }
                    content += '</td>';
                    content += '<td align="center">';
                    content += ' <img class="reset_cbl" src="${pageContext.request.contextPath}/css/images/reset.png" alt="" class="reset_cbl" id="reset_wl_' + row_index + '" title="Edit Address" style="cursor:pointer;display:none">';
                    content += '</td>';
                    content += '</tr>';
                    $("#cust_billing_" + row_index).replaceWith(content);
                    getRegions(ex_object.countryID, ex_object.regionID, function(obj) {
                        region_obj = obj;
                    });
                    getCities(ex_object.countryID, ex_object.regionID, ex_object.cityID, function(obj) {
                        city_obj = obj;
                    });
                }
                $('#customerBillingRegion_' + row_index).html(region_obj);
                $('#customerBillingCity_' + row_index).html(city_obj);
            });

            $('body').on('click', '.reset_cbl', function(e) {
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var index = id.substring(13); // cust_billing_
                var pk = $("#customerBillingID_" + index).val();
                if (modelObject.getBillingAddress().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getBillingAddress()[pk];

                    if (typeof ex_object.customerAddress == "undefined") {
                        ex_object.customerAddress = "";
                    }
                    if (typeof ex_object.addressShortCode == "undefined") {
                        ex_object.addressShortCode = "";
                    }
                    if (typeof ex_object.countryID == "undefined") {
                        ex_object.countryID = "";
                    }
                    if (typeof ex_object.regionID == "undefined") {
                        ex_object.regionID = "";
                    }
                    if (typeof ex_object.cityID == "undefined") {
                        ex_object.cityID = "";
                    }
                    if (typeof ex_object.pincode == "undefined") {
                        ex_object.pincode = "";
                    }
                    if (typeof ex_object.gstCode == "undefined") {
                        ex_object.gstCode = "";
                    }
                    if (typeof ex_object.gstNumber == "undefined") {
                        ex_object.gstNumber = "";
                    }


                    var content = '';
                    content += '<tr id="cust_billing_' + index + '">';
                    content += '<td id="cust_billing_address_' + index + '">';
                    content += '<input type="hidden" value="' + ex_object.id + '"   id="customerBillingID_' + index + '"/>';
                    content += ex_object.customerAddress;
                    // content +='<input type="hidden"  id="customerBillingAddress_'+index+'" value="'+ex_object.customerAddress+'"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.addressShortCode;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.country;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.region;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.city;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.pincode;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.gstCode;
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.gstNumber;
                    content += '</td>';
                    content += '<td align="center">';
                    content += '  <img class="edit_cb" src="${pageContext.request.contextPath}/css/images/document-blue.png"  id="edit_cb_' + index + '" title="Edit Address" style="cursor:pointer;">';
                    content += '</td>';
                    content += '</tr>';
                    $("#cust_billing_" + index).replaceWith(content);
                }
            });
            /*--------------------customerbillingend------------------------------*/


    //        if(Object.keys(modelObject.billing_address).length > 0)
    //        {
    //            setTimeout(function() {
    //                $(".edit_cb").trigger('click');
    //            },10);
    //        }

            var billing_address_count = 0;
            var i_billing_address;
            for (i_billing_address in modelObject.billing_address) {
                if (modelObject.billing_address.hasOwnProperty(i_billing_address)) {
                    billing_address_count++;
                }
            }
            if (billing_address_count > 0) {
                setTimeout(function() {
                    $(".edit_cb").trigger('click');
                }, 10);
            }
            ////////////////////////////////////////

    //        if(Object.keys(modelObject.work_loc).length > 0)
    //        {
    //            setTimeout(function() {
    //                $(".edit_wl").trigger('click');
    //            },10);
    //        }        


            var work_loc_count = 0;
            var i_work_loc;

            for (i_work_loc in modelObject.work_loc) {
                if (modelObject.work_loc.hasOwnProperty(i_work_loc)) {
                    work_loc_count++;
                }
            }
            if (work_loc_count > 0) {
                setTimeout(function() {
                    $(".edit_wl").trigger('click');
                }, 10);
            }
            ///////////////////////////////////////////////////////////
    //        if(Object.keys(modelObject.customer_contacts).length > 0)
    //        {
    //            setTimeout(function() {
    //                $(".edit_cc").trigger('click');
    //            },10);
    //        }


            var customer_contacts_count = 0;
            var i_customer_contacts;

            for (i_customer_contacts in modelObject.customer_contacts) {
                if (modelObject.customer_contacts.hasOwnProperty(i_customer_contacts)) {
                    customer_contacts_count++;
                }
            }
            if (customer_contacts_count > 0) {
                setTimeout(function() {
                    $(".edit_cc").trigger('click');
                }, 10);
            }
            /////////////////////////////////
    //        if(Object.keys(modelObject.customer_finance_contacts).length > 0)
    //        {
    //            setTimeout(function() {
    //                $(".edit_fc").trigger('click');
    //            },10);
    //        }

            var customer_finance_contacts_count = 0;
            var i_customer_finance_contacts;

            for (i_customer_finance_contacts in modelObject.customer_finance_contacts) {
                if (modelObject.customer_finance_contacts.hasOwnProperty(i_customer_finance_contacts)) {
                    customer_finance_contacts_count++;
                }
            }
            if (customer_finance_contacts_count > 0)
            {
                setTimeout(function() {
                    $(".edit_fc").trigger('click');
                }, 10);
            }
            
            var customer_dunning_contacts_count = 0;
            var i_customer_dunning_contacts;

            for (i_customer_dunning_contacts in modelObject.customer_dunning_contacts) {
                if (modelObject.customer_dunning_contacts.hasOwnProperty(i_customer_dunning_contacts)) {
                    customer_dunning_contacts_count++;
                }
            }
            if (customer_dunning_contacts_count > 0)
            {
                setTimeout(function() {
                    $(".edit_dc").trigger('click');
                }, 10);
            }

            /*-------------------------------worklocationstart -----------------------------------------------*/
            $("body").on("click", ".edit_wl", function() {
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id'); // cust-wl_
                var row_index = id.substring(8);
                var pk = $('#customerWorkLocationID_' + row_index).val();
                var statusCheck = $('#customerStatus').val();
                var region_obj = '';
                var city_obj = '';
                var Cn_selected = '';
                var content = '';
                if (modelObject.getWorkLoction().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getWorkLoction()[pk];

                    if (typeof ex_object.customerAddress == "undefined") {
                        ex_object.customerAddress = "";
                    }
                    if (typeof ex_object.addressShortCode == "undefined") {
                        ex_object.addressShortCode = "";
                    }
                    if (typeof ex_object.countryID == "undefined") {
                        ex_object.countryID = "";
                    }
                    if (typeof ex_object.regionID == "undefined") {
                        ex_object.regionID = "";
                    }
                    if (typeof ex_object.cityID == "undefined") {
                        ex_object.cityID = "";
                    }
                    if (typeof ex_object.workLocationWorkingHours == "undefined") {
                        ex_object.workLocationWorkingHours = "";
                    }
                    if (typeof ex_object.pincode == "undefined") {
                        ex_object.pincode = "";
                    }
                    if (typeof ex_object.iscompanyLocationId == "undefined") {
                        ex_object.iscompanyLocationId = "";
                    }
                    if(statusCheck=="s" || statusCheck=="r"){
                        content += '<tr id="cust-wl_' + row_index + '">';
                        content += '<td><textarea type="text" class="customerWorkAddress" name="customerWorkAddress" value="' + ex_object.customerAddress + '" id="customerWorkAddress_' + row_index + '" style="min-width:221px; max-width:221px;">' + ex_object.customerAddress + '</textarea>';
                        content += '<input type="hidden"  name="customerWorkLocationID" value="' + pk + '"  id="customerWorkLocationID_' + row_index + '"/>';
                        content += '</td>';
                        content += '<td><input type="text" maxlength="15" name="customerWorkShortCode" value="' + ex_object.addressShortCode + '" id="customerWorkShortCode_' + row_index + '" class="custWorkShortCode"></td>';
                        content += '<td>';
                        content += '<select name="customerWorkCountry" class="customerWorkCountry" id="customerWorkCountry_' + row_index + '">';
                        content += '<option value="">-- Select Country -- </option>';
                        <c:forEach items="${countryList}" var="countryList">
                            if (ex_object.countryID == ${countryList.countryID}) {
                                Cn_selected = "selected";
                            }
                            content += '<option value="${countryList.countryID}" ' + Cn_selected + ' >${countryList.countryName}</option>';
                            Cn_selected = '';
                         </c:forEach>
                        content += '</select></td>';
                        content += '<td align="center">';
                        content += '<select name="customerWorkRegion" class="customerWorkRegion" id="customerWorkRegion_' + row_index + '">';
                        content += '</select></td>';
                        content += '<td align="center">';
                        content += '<select name="customerWorkCity" class="customerWorkCity" id="customerWorkCity_' + row_index + '">';
                        content += '</select></td>';
                        content += '<td><input type="text" name="customerWorkPincode" class="customerWorkPincode" value="' + ex_object.pincode + '" id="customerWorkPincode_' + row_index + '" ></td>';
                        content += '<td><input type="text" name="workingHoursPerDay" class="workingHoursPerDay" value="' + ex_object.workLocationWorkingHours + '" id="workingHoursPerDay_' + row_index + '" ></td>';
                        content += '<td><select name="isworklocationcompanylocation" class="isCompanyLocation" id="WorkLocationIsCompanyLocation_' + row_index + '">';
                            if(ex_object.iscompanyLocationId == '1'){
                                content += '<option value="">--Select Location--</option>';
                                content += '<option value="0">Customer Location</option>';
                                content += '<option value="1" selected >Company Location</option>';
                            }else{
                                content += '<option value="">--Select Location--</option>';
                                content += '<option value="0"  selected >Customer Location</option>';
                                content += '<option value="1">Company Location</option>';
                            }
                            
                        content += '</select><input type="hidden"  name="WorkLocationIsCompanyLocation" value="' + ex_object.iscompanyLocationId + '"  id="iscompanyLocationId_' + row_index + '"/></td>';
                        content += '<td align="center">';
                        content += '<img class="reset_wl" src="${pageContext.request.contextPath}/css/images/reset.png" alt="" class="reset_wl" id="reset_wl_' + row_index + '" title="Edit Address" style="cursor:pointer;display:none">';
                        content += '</td>';
                        content += '</tr>';
                    }else{
                        content += '<tr id="cust-wl_' + row_index + '">';
                        content += '<td><textarea readonly type="text" class="customerWorkAddress" name="customerWorkAddress" value="' + ex_object.customerAddress + '" id="customerWorkAddress_' + row_index + '" style="min-width:221px; max-width:221px;">' + ex_object.customerAddress + '</textarea>';
                        content += '<input type="hidden"  name="customerWorkLocationID" value="' + pk + '"  id="customerWorkLocationID_' + row_index + '"/>';
                        content += '</td>';
                        content += '<td><input readonly type="text" maxlength="15" name="customerWorkShortCode" value="' + ex_object.addressShortCode + '" id="customerWorkShortCode_' + row_index + '" class="custWorkShortCode"></td>';
                        content += '<td>';
                        content += '<select disabled name="customerWorkCountry" class="customerWorkCountry" id="customerWorkCountry_' + row_index + '">';
                        content += '<option value="">-- Select Country -- </option>';
                        <c:forEach items="${countryList}" var="countryList">
                            if (ex_object.countryID == ${countryList.countryID}) {
                                Cn_selected = "selected";
                            }
                            content += '<option value="${countryList.countryID}" ' + Cn_selected + ' >${countryList.countryName}</option>';
                            Cn_selected = '';
                         </c:forEach>
                        content += '</select></td>';
                        content += '<td align="center">';
                        content += '<select disabled name="customerWorkRegion" class="customerWorkRegion" id="customerWorkRegion_' + row_index + '">';
                        content += '</select></td>';
                        content += '<td align="center">';
                        content += '<select disabled name="customerWorkCity" class="customerWorkCity" id="customerWorkCity_' + row_index + '">';
                        content += '</select></td>';
                        content += '<td><input readonly type="text" name="customerWorkPincode" class="customerWorkPincode" value="' + ex_object.pincode + '" id="customerWorkPincode_' + row_index + '" ></td>';
                        content += '<td><input readonly type="text" name="workingHoursPerDay" class="workingHoursPerDay" value="' + ex_object.workLocationWorkingHours + '" id="workingHoursPerDay_' + row_index + '" ></td>';
                        content += '<td><select disabled name="isworklocationcompanylocation" class="isCompanyLocation" id="WorkLocationIsCompanyLocation_' + row_index + '">';
                            if(ex_object.iscompanyLocationId == "1"){
                                content += '<option value="">--Select Location--</option>';
                                content += '<option value="0">Customer Location</option>';
                                content += '<option value="1" selected >Company Location</option>';
                            }else{
                                content += '<option value="">--Select Location--</option>';
                                content += '<option value="0"  selected >Customer Location</option>';
                                content += '<option value="1">Company Location</option>';
                            }
                        content += '</select><input type="hidden"  name="WorkLocationIsCompanyLocation" value="' + ex_object.iscompanyLocationId + '"  id="iscompanyLocationId_' + row_index + '"/></td>';
                        content += '<td align="center">';
                        content += ' <img class="reset_wl" src="${pageContext.request.contextPath}/css/images/reset.png" alt="" class="reset_wl" id="reset_wl_' + row_index + '" title="Edit Address" style="cursor:pointer;display:none">';
                        content += '</td>';
                        content += '</tr>';
                    }
                    $("#cust-wl_" + row_index).replaceWith(content);
                    getRegions(ex_object.countryID, ex_object.regionID, function(obj) {
                        region_obj = obj;
                    });
                    getCities(ex_object.countryID, ex_object.regionID, ex_object.cityID, function(obj) {
                        city_obj = obj;
                    });
                    $('#customerWorkRegion_' + row_index).html(region_obj);
                    $('#customerWorkCity_' + row_index).html(city_obj);
                }
            });


            $('body').on('click', '.reset_wl', function(e) {
                var $tablerow = $(this).closest('tr');
                var id = $tablerow.attr('id');
                var index = id.substring(8);
                var pk = $("#customerWorkLocationID_" + index).val();
                if (modelObject.getWorkLoction().hasOwnProperty(pk)) {
                    var ex_object = modelObject.getWorkLoction()[pk];


                    if (typeof ex_object.customerAddress == "undefined") {
                        ex_object.customerAddress = "";
                    }
                    if (typeof ex_object.addressShortCode == "undefined") {
                        ex_object.addressShortCode = "";
                    }
                    if (typeof ex_object.countryID == "undefined") {
                        ex_object.countryID = "";
                    }
                    if (typeof ex_object.regionID == "undefined") {
                        ex_object.regionID = "";
                    }
                    if (typeof ex_object.cityID == "undefined") {
                        ex_object.cityID = "";
                    }

                    if (typeof ex_object.workLocationWorkingHours == "undefined") {
                        ex_object.workLocationWorkingHours = "";
                    }

                    if (typeof ex_object.pincode == "undefined") {
                        ex_object.pincode = "";
                    }
                    if (typeof ex_object.iscompanyLocationId == "undefined") {
                        ex_object.iscompanyLocationId = "";
                    }



                    var content = '';
                    content += '<tr id="cust-wl_' + index + '">';
                    content += '<td id="cwl_td_' + index + '">';
                    content += '<input type="hidden" value="' + ex_object.id + '"   id="customerWorkLocationID_' + index + '"/>';
                    content += ex_object.customerAddress;
                    // content +='<input type="hidden"  id="customerWorkAddress_'+index+'" value="'+ex_object.customerAddress+'"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.addressShortCode;
                    //content +='<input type="hidden"  id="customerWorkCountry_'+index+'" value="'+ex_object.countryID+'"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.country;
                    //content +='<input type="hidden"  id="customerWorkCountry_'+index+'" value="'+ex_object.countryID+'"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.region;
                    //content +='<input type="hidden"  id="customerWorkRegion_'+index+'" value="'+ex_object.regionID+'"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.city;
                    //content +='<input type="hidden"  id="customerWorkCity_'+index+'" value="'+ex_object.cityID+'"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.pincode;
                    //content +='<input type="hidden"  id="customerWorkPincode_'+index+'" value="'+ex_object.pincode+'"/>';
                    content += '</td>';
                    content += '<td align="center">';
                    content += ex_object.workLocationWorkingHours;
                    content += '</td>';
                    
                    content += '<td align="center">';
                    content += ex_object.isCompanyLocation;
                    content += '</td>';
                    content += '<td align="center">';
//                    content += ' <img class="edit_wl" class="add_customer_wl" src="${pageContext.request.contextPath}/css/images/document-blue.png" alt="Search" id="edit_wl_' + index + '" title="Edit Address" style="cursor:pointer;">';
                    content += '</td>';
                    content += '</tr>';
                    $("#cust-wl_" + index).replaceWith(content);
                }
            });

            $("body").on("click", '.add_customer_wl', function() {
                addworkLocationRow(this);
            });

            $("body").on("click", '.delete_customer_wl', function() {
                var Row1 = $(this).parent().parent();
                Row1.remove();
            });

            $("body").on("change", ".customerWorkCountry", function() { //20
                var index = this.id;
                if (index.length > 23) {
                    index = index.substring(23);
                    var country_id = $("#" + this.id).val();
                    var region_id = null;
                    getRegions(country_id, region_id, function(obj) {
                        $("#customerWorkRegionNew_" + index).html(obj);
                    });
                } else {
                    index = index.substring(20);
                    var country_id = $("#" + this.id).val();
                    var region_id = null;
                    getRegions(country_id, region_id, function(obj) {
                        $("#customerWorkRegion_" + index).html(obj);
                    });
                }

            });

            $("body").on("change", ".customerWorkRegion", function() { //19
                var index = this.id;
                if (index.length > 22) {
                    index = index.substring(22);
                    var country_id = $("#customerWorkCountryNew_" + index).val();
                    var region_id = $("#" + this.id).val();
                    var city_id = null;
                    getCities(country_id, region_id, city_id, function(obj) {
                        $("#customerWorkCityNew_" + index).html(obj);
                    });
                } else {
                    index = index.substring(19);
                    var country_id = $("#customerWorkCountry_" + index).val();
                    var region_id = $("#" + this.id).val();
                    var city_id = null;
                    getCities(country_id, region_id, city_id, function(obj) {
                        $("#customerWorkCity_" + index).html(obj);
                    });
                }

            });



            function addworkLocationRow(rowObject) {
                var Row1 = $(rowObject).parent().parent();
                var workLocationDivId = $(rowObject).parent().parent().parent().parent().attr("id");
                var workLocationDivIdNum = workLocationDivId.split('_')[1];
                var rowData = "";
                var workLocationCount = $('#workLocationCount_' + workLocationDivIdNum).val();
                var cnt = parseInt(workLocationCount) + 1;
                rowData += '<td align="center"><textarea type="text" name="customerWorkAddressNew_' + workLocationDivIdNum + '_' + cnt + '" class="customerWorkAddress" id="customerWorkAddressNew_' + workLocationDivIdNum + '_' + cnt + '" style="min-width: 221px ;max-width: 221px !important;"></textarea></td>';
                rowData += '<td><input type="text" maxlength="15" value="" name="customerWorkShortCodeNew_' + workLocationDivIdNum + '_' + cnt + '" id="customerWorkShortCode_' + workLocationDivIdNum + '_' + cnt + '" class="custWorkShortCode"></td>';
                rowData += '<td  align="center">';
                rowData += '<select name="customerWorkCountryNew_' + workLocationDivIdNum + '_' + cnt + '" class="customerWorkCountry" id="customerWorkCountryNew_' + workLocationDivIdNum + '_' + cnt + '" >';
                rowData += '<option value="">-- Select Country -- </option>';
                rowData += '<c:forEach items="${countryList}" var="countryList" >';
                rowData += '<option value="${countryList.countryID}">${countryList.countryName}</option>';
                rowData += '</c:forEach>';
                rowData += '</select>';
                rowData += '</td>';
                rowData += '<td  align="center">';
                rowData += '<select name="customerWorkRegionNew_' + workLocationDivIdNum + '_' + cnt + '" class="customerWorkRegion" id="customerWorkRegionNew_' + workLocationDivIdNum + '_' + cnt + '">';
                rowData += '<option value="">-- Select Region -- </option></select></td>';
                rowData += '<td  align="center">';
                rowData += '<select name="customerWorkCityNew_' + workLocationDivIdNum + '_' + cnt + '" class="customerWorkCity" id="customerWorkCityNew_' + workLocationDivIdNum + '_' + cnt + '">';
                rowData += '<option value="">-- Select City -- </option></select></td>';
                rowData += '<td align="center"><input type="text" name="customerWorkPincodeNew_' + workLocationDivIdNum + '_' + cnt + '" class="customerWorkPincode" id="customerWorkPincodeNew_' + workLocationDivIdNum + '_' + cnt + '"></td>';
                rowData += '<td align="center"><input type="text" name="workingHoursPerDayNew_' + workLocationDivIdNum + '_' + cnt + '" class="workingHoursPerDay" id="workingHoursPerDayNew_' + workLocationDivIdNum + '_' + cnt + '" placeholder="HH:MM"></td>';
                
                rowData += '<td><select name="workingLocationIsCompanyLocationNew_'+ workLocationDivIdNum + '_' + cnt + '" id="workingLocationIsCompanyLocationNew_'+ workLocationDivIdNum + '_' + cnt + '" class="customerWorkLocationDefining textbox_new">';
                rowData += '<option value="">--Select Location--</option>';
                rowData += '<option value="0">Customer Location</option>';
                rowData += '<option value="1">Company Location</option>';
                rowData += '</select></td>';
                
                rowData += '<td align="center">';
                rowData += '<img class="add_customer_wl" id="add_workloc_child" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
                rowData += '&nbsp;<img class = "delete_row" src="${pageContext.request.contextPath}/css/images/tm_delete.png" style="cursor:pointer;" >';
                rowData += '</td>';
                if ($(rowObject).attr('id') == 'add_workloc_parent')
                    $(Row1).before('<tr id="cust-wl_' + workLocationDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
                else
                    $(Row1).after('<tr id="cust-wl_' + workLocationDivIdNum + '_' + cnt + '">' + rowData + '</tr>');
                $('#workLocationCount_' + workLocationDivIdNum).val(cnt);
            }

            /* ----------------------------work location end----------------------------------------------- */
            function getRegions(country_id, region_id, callback) {
                $.ajax({
                    url: 'getRegion.htm',
                    type: "POST",
                    async: false,
                    data: ({countryFilter: country_id,
                        regionID: region_id}),
                    success: function(ajaxObj) {
                        callback(ajaxObj);
                    }
                });
            }

            function getCities(country_id, region_id, city_id, callback) {
                $.ajax({
                    url: 'getCity.htm',
                    type: "POST",
                    async: false,
                    data: ({countryFilter: country_id,
                        regionFilter: region_id,
                        cityID: city_id}),
                    success: function(ajaxObj) {
                        callback(ajaxObj);
                    }
                });
            }
            
            function getGstCode(country_id, region_id, city_id, callback) {
                $.ajax({
                    url: 'getGstCode.htm',
                    type: "POST",
                    async: false,
                    data: ({countryFilter: country_id,
                            regionId: region_id,
                            cityID: city_id}),
                    success: function(ajaxObj) {
                        callback(ajaxObj);
                    }
                });
            }


            /*---------------basic datas field check--------*/
    //        function allLetter(uid, uname) {
    //            var letters = /^[A-Za-z- ]+$/;
    //            if (uname.match(letters)) {
    //                $("#" + uid).css({"outline": "none"});
    //                return true;
    //            } else {
    //                $("#" + uid).css({"outline": "1px solid red"});
    //                return false;
    //            }
    //        }
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
                    return true;
                } else {
                    $("#" + fieldId).css({"outline": "1px solid red"});
                    return false;
                }
            }

    //        function allNumericTOP(uid,uVal){   
    //                var allNumeric = /^[0-9]+$/;  
    //                if(uVal.match(allNumeric)){ 
    //                    if(uVal >  0 && uVal < 365 ){
    //                         $("#"+uid).css({"outline":"none"});
    //                        $("#termsOfPaymenterrormessage").css({"display":"none"});
    //                        return true; 
    //                    }else{
    //                        $("#termsOfPaymenterrormessage").css({"display":"block"});
    //                        $("#"+uid).css({"outline":"1px solid red"});
    //                        return false; 
    //                    }
    //                }else{
    //                    $("#termsOfPaymenterrormessage").css({"display":"block"});
    //                    $("#"+uid).css({"outline":"1px solid red"});
    //                    return false;  
    //                }  
    //            }

            function allNumeric(fieldId, fieldVal) {
                var allNumeric = /^[0-9]+$/;
                if (fieldVal.match(allNumeric)) {
                    $("#" + fieldId).css({"outline": "none"});
                    return true;
                } else {
                    $("#" + fieldId).css({"outline": "1px solid red"});
                    return false;
                }
            }
            /*-------------edit rows validation------------*/
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
            function tableEmailCheck(cl_name) {
                var EmailError = 0;
                var cl_length = document.querySelectorAll("." + cl_name).length;
                for (var i = 0; i < cl_length; i++) {
                    var cl_val = document.querySelectorAll("." + cl_name)[i].value.trim();
                    var Tablemailformat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
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
            
            function tableEmailCheck1(cl_name){
                var EmailError=0;
                var cl_length=document.querySelectorAll("."+cl_name).length;

                for(var i=0;i<cl_length;i++){
                    var cl_val = document.querySelectorAll("."+cl_name)[i].value.trim();
                    var Tablemailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                    if(cl_val.match(Tablemailformat)){
                         document.querySelectorAll("."+cl_name)[i].style.outline = "none";
                         $(".errorText1").css({"display":"none"});
                    }else{ 
                        document.querySelectorAll("."+cl_name)[i].style.outline = "1px solid red";
                         $(".errorText1").show();
                        $(".errorText1").css({"display":"block"});
                        EmailError++; 
                    }   
                }
                if(EmailError <= 0){
                    return true;
                }else if(EmailError > 0){
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
                    } else {
                        document.querySelectorAll("." + clas_name)[i].style.outline = "1px solid red";
                        AlpahbetError++;
                    }
                }
                if (AlpahbetError <= 0) {
                    return true;
                } else if (AlpahbetError > 0) {
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
            function attachmentTypeValidation(uid, uval) {
                var fileExt = $('#' + uid).val().split('.').pop().toLowerCase();
                if ($("#attachedFileValue").length == 0) {
                    if ($.inArray(fileExt, ['pdf', 'jpg', 'png', 'jpeg']) == -1) {
                        $("#attachFileerrormessage").css({"display": "block"});
                        $(".errorText").show();
                        $(".errorText").css({"display": "block"});
                        $("#" + uid).css({"outline": "1px solid red"});
                        return false;
                    } else {
                        $("#" + uid).css({"outline": "none"});
                        $("#attachFileerrormessage").css({"display": "none"});
                        $(".errorText").css({"display": "none"});
                        return true;
                    }
                } else {
                    if (uval != 0) {
                        if ($.inArray(fileExt, ['pdf', 'jpg', 'png', 'jpeg']) == -1) {
                            $("#attachFileerrormessage").css({"display": "block"});
                            $("#" + uid).css({"outline": "1px solid red"});
                            return false;
                        } else {
                            $("#" + uid).css({"outline": "none"});
                            $("#attachFileerrormessage").css({"display": "none"});
                            $(".errorText").css({"display": "none"});
                            return true;
                        }
                    } else {
                        $("#" + uid).css({"outline": "none"});
                        $("#attachFileerrormessage").css({"display": "none"});
                        return true;
                    }
                }

    //                if(!uval == ''){
    //                    var ext = uval.split(".");
    //                    var len = ext.length;
    //                    var imgsize = document.getElementById(uid).files[0];
    //                    var fileSize = imgsize.size;
    //                    var extType=ext[(len-1)];
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
    //                            $("#"+uid).css({"outline":"1px solid red"});
    //                            return false;
    //                        }
    //
    //                        $("#"+uid).css({"outline":"none"});
    //                        $("#attachFileerrormessage").css({"display":"none"});
    //    //                    return false;
    //                    }
    //                    else{
    //                        $("#attachFileerrormessage").css({"display":"block"});
    //                        $("#"+uid).css({"outline":"1px solid red"});
    //                        return false;
    //                    }
    //                }else{
    //
    //                    $("#attachFileerrormessage").css({"display":"block"});
    //                    $("#"+uid).css({"outline":"1px solid red"});
    //                    return false;
    //                }

            }
            function allNumericTOP(uid, uVal) {
                var allNumeric = /^[0-9]+$/;
                if (uVal.match(allNumeric)) {
                    if (uVal > 0 && uVal < 365) {
                        $("#" + uid).css({"outline": "none"});
    //                        $("#errorAlert").css({"display":"none"});
                        return true;
                    } else {
    //                        $("#errorAlert").css({"display":"block"});
                        $("#" + uid).css({"outline": "1px solid red"});
                        return false;
                    }
                } else {
    //                    $("#errorAlert").css({"display":"block"});
                    $("#" + uid).css({"outline": "1px solid red"});
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
            function divisionNameCheck(class_name) {
                var NullError = 0;
                var class_length = document.querySelectorAll("." + class_name).length;
                for (var i = 0; i < class_length; i++) {
                    var class_name_val_length = document.querySelectorAll("." + class_name)[i].value.trim().length;
                    if (i === 0) {
                        if (class_name_val_length >= 0) {
                            document.querySelectorAll("." + class_name)[i].style.outline = "none";
                        } else {
                            document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                            NullError++;
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

            function saveTableEmailCheck(savecl_name) {
                var saveEmailError = 0;
                var savecl_length = document.querySelectorAll("." + savecl_name).length;
                for (var i = 0; i < savecl_length; i++) {
                    if (document.querySelectorAll("." + savecl_name)[i].value.length > 0) {
                        var savecl_val = document.querySelectorAll("." + savecl_name)[i].value;
                        var saveTablemailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
                        if (savecl_val.match(saveTablemailformat)) {
                            document.querySelectorAll("." + savecl_name)[i].style.outline = "none";
                        } else {
                            document.querySelectorAll("." + savecl_name)[i].style.outline = "1px solid red";
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
                        } else {
                            document.querySelectorAll("." + clas_name)[i].style.outline = "1px solid red";
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
                    if ($.inArray(fileExt, ['pdf', 'jpg', 'jpeg', 'png']) == -1) {
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
                    var allNumeric = /^[0-9]+$/;
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
            function phoneNumberCheck(uid, uVal) {
                var allNumeric = /^[0-9+-]+$/;
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
            function savenullFieldCheck(fieldId) {
                $("#" + fieldId).css({"outline": "none"});
                $("#salesNameerrormessage").css({"display": "none"});
                $("#attachTypeerrormessage").css({"display": "none"});
                return true;
            }
            function saveButtonValidation() {
                var savecustNameVal = document.getElementById("customerName").value;
                var savecustNameID = document.getElementById("customerName").id;
                var savecustNameStatus = nullFieldCheck(savecustNameID, savecustNameVal);

                document.getElementById("aboutCustomer").style.outline = "none";

                document.getElementById("salesPerson").style.outline = "none";

                document.getElementById("salesPersonContactNo").style.outline = "none";

                document.getElementById("attachmentType").style.outline = "none";

                document.getElementById("attachmentValue").style.outline = "none";



                savetableNullRemoveCheck("customerBillingAddress");
                savetableNullRemoveCheck("custBillingShortCode");
                savetableNullRemoveCheck("customerBillingCountry");
                savetableNullRemoveCheck("customerBillingRegion");
                savetableNullRemoveCheck("customerBillingCity");

                savetableNullRemoveCheck("customerWorkAddress");
                savetableNullRemoveCheck("custWorkShortCode");
                savetableNullRemoveCheck("customerWorkCountry");
                savetableNullRemoveCheck("customerWorkRegion");
                savetableNullRemoveCheck("customerWorkCity");

                //var DivsionStatus=divisionNameCheck("division");
                var custWorkPinCodeStatus = saveTableAlphaNumericCheck("customerWorkPincode");
                var custworkingHoursPerDayStatus = saveTableTimeCheck("workingHoursPerDay");
                var custContactPersonStatus = saveTableEmailCheck("customerContactEmail");
                var custContactPhoneStatus = saveTableNumericCheck("customerContactPhone");
                var custContactDesignationStatus = saveTableAlphabetCheck("customerContactDesignation");

                var customerEmailStatus = saveTableEmailCheck("customerContactEmail");
                var custFinContPersonStatus = saveTableAlphabetCheck("customerFinanceContactPerson");
                var custFinContDesignationStatus = saveTableAlphabetCheck("customerFinanceContactDesignation");
                var custFinContPhoneStatus = saveTableNumericCheck("customerFinanceContactPhone");
                var custFinEmailStatus = saveTableEmailCheck("customerFinanceContactEmail");
                var custBillingPincodeStatus = saveTableAlphaNumericCheck("customerBillingPincode");
                var custBillingGstNumberStatus = tableAlphaNumericGst("customerBillingGstNumber","customerBillingCountry");
                if (savecustNameStatus && custContactDesignationStatus &&
                        custWorkPinCodeStatus && customerEmailStatus && custContactPersonStatus &&
                        custContactPhoneStatus && custFinEmailStatus && custFinContPersonStatus &&
                        custFinContPhoneStatus && custworkingHoursPerDayStatus && custFinContDesignationStatus &&
                        custBillingPincodeStatus && custBillingGstNumberStatus) {
                    $("#formCustomerDetails").submit();
                    startLoading();
                } else {

                }
            }

            $("#submitBtn,#saveBtn").click(function() {
                var action = this.id;
                if (action == 'saveBtn') {
                    $("#action").val("s");
                    saveButtonValidation();


                } else if (action == 'submitBtn') {
                    $("#action").val("m");
                    /*---------basic field validation----*/
                    var custNameVal = document.getElementById("customerName").value;
                    var custNameID = document.getElementById("customerName").id;
                    // var custNameStatus = allLetter(custNameID, custNameVal);
                    var custNameStatus = nullFieldCheck(custNameID, custNameVal);

                    var salesPersonVal = document.getElementById("salesPerson").value;
                    var salesPersonID = document.getElementById("salesPerson").id;
                    var salesPersonStatus = nullFieldCheck(salesPersonID, salesPersonVal);

    //            var termsOfPaymentVal = document.getElementById("termsOfPayment").value;
    //            var termsOfPaymentID = document.getElementById("termsOfPayment").id;
    //            var termsOfPaymentStatus = allNumericTOP(termsOfPaymentID, termsOfPaymentVal);

                    var aboutCustomerVal = document.getElementById("aboutCustomer").value;
                    var aboutCustomerID = document.getElementById("aboutCustomer").id;
                    var aboutCustomerStatus = nullFieldCheck(aboutCustomerID, aboutCustomerVal);

                    var attachmentTypeVal = document.getElementById("attachmentType").value;
                    var attachmentTypeID = document.getElementById("attachmentType").id;
                    var attachmentTypeStatus = nullFieldCheck(attachmentTypeID, attachmentTypeVal);

                    var salesPersonContactNoVal = document.getElementById("salesPersonContactNo").value;
                    var salesPersonContactNoID = document.getElementById("salesPersonContactNo").id;
                    var salesPersonContactNoStatus = phoneNumberCheck(salesPersonContactNoID, salesPersonContactNoVal);

                    var attachmentVal = document.getElementById("attachmentValue").value;
                    var attachmentID = document.getElementById("attachmentValue").id;
                    var attachmentStatus = attachmentTypeValidation(attachmentID, attachmentVal);
                    
//                    var invoiceVal = document.getElementById("invoiceTo").value;
//                    var invoiceID = document.getElementById("invoiceTo").id;
//                    var invoiceStatus = nullFieldCheck(invoiceID, invoiceVal);

                    /*--------rows field validation------*/
                    var DivsionStatus = divisionNameCheck("division");

                    var customerContactPersonStatus = tableAlphabetCheck("customerContactPerson");
                    var customerContactDesignationStatus = tableNullCheck("customerContactDesignation");
                    var customerContactPhoneStatus = tableNumericCheck("customerContactPhone");
                    var customerContactEmailStatus = tableEmailCheck1("customerContactEmail");

                    var customerFinanceContactPersonStatus = tableAlphabetCheck("customerFinanceContactPerson");
                    var customerFinanceContactDesignationStatus = tableNullCheck("customerFinanceContactDesignation");
                    var customerFinanceContactPhoneStatus = tableNumericCheck("customerFinanceContactPhone");
                    var customerFinanceEmailStatus = tableEmailCheck("customerFinanceContactEmail");

                    var customerBillingAddressStatus = tableNullCheck("customerBillingAddress");
                    var custBillingShortCodeStatus = tableNullCheck("custBillingShortCode");
                    var customerBillingCountryStatus = tableNullCheck("customerBillingCountry");
                    var customerBillingRegionStatus = tableNullCheck("customerBillingRegion");
                    var customerBillingCityStatus = tableNullCheck("customerBillingCity");
                    var customerBillingPincodeStatus = tableAlphaNumericCheck("customerBillingPincode");
                    var custBillingGstNumberStatus = tableAlphaNumericGst("customerBillingGstNumber","customerBillingCountry");
                    
                    var customerWorkAddressStatus = tableNullCheck("customerWorkAddress");
                    var custWorkShortCodeStatus = tableNullCheck("custWorkShortCode");
                    var customerWorkCountryStatus = tableNullCheck("customerWorkCountry");
                    var customerWorkRegionStatus = tableNullCheck("customerWorkRegion");
                    var customerWorkCityStatus = tableNullCheck("customerWorkCity");
                    var customerWorkPincodeStatus = tableAlphaNumericCheck("customerWorkPincode");
                    var workingHoursPerDayStatus = tableTimeCheck("workingHoursPerDay");
                    var customerWorkLocation = tableNullCheck("customerWorkLocationDefining");
                    var custInvoiceStatus = tableNullCheck("custInvoice");

                    if (custNameStatus && salesPersonStatus && aboutCustomerStatus && attachmentTypeStatus && salesPersonContactNoStatus && attachmentStatus && custInvoiceStatus &&
                            customerContactPersonStatus && customerContactDesignationStatus && customerContactPhoneStatus && customerContactEmailStatus && DivsionStatus &&
                            customerFinanceContactPersonStatus && customerFinanceContactDesignationStatus && customerFinanceContactPhoneStatus && customerFinanceEmailStatus &&
                            customerBillingAddressStatus && custBillingShortCodeStatus && customerBillingCountryStatus && customerBillingRegionStatus && customerBillingCityStatus && customerBillingPincodeStatus &&
                            customerWorkAddressStatus && custWorkShortCodeStatus && customerWorkCountryStatus && customerWorkRegionStatus && customerWorkCityStatus && customerWorkPincodeStatus && workingHoursPerDayStatus && 
                            customerWorkLocation && custBillingGstNumberStatus) {
                        var error = 0;

    //                     if($("#attachedFileValue").length == 0){
    //                        var attachmentNameStatus = attachmentTypeValidation(attachmentNameID,attachmentNameVal);
    //                         if(attachmentNameStatus){
                        $("#saveBtn").hide();
                        $("#submitBtn").hide();
                        $("#btnCancel").hide();
                        $("#formCustomerDetails").attr("action", "saveEditCustomer.htm");
                        $("#formCustomerDetails").submit();
                        startLoading();
    //                        }
    //                     }else if(error == 0){
//                        $("#formCustomerDetails").attr("action", "saveEditCustomer.htm");
//                        $("#formCustomerDetails").submit();
//                        startLoading();
    //                     }
                    } else {
    //                    $("#errorAlert").css({"display": "block"});
                    }
                }
            });

    //            $("#errorAlertCancel").click(function() {
    //                $("#errorAlert").css({"display": "none"});
    //            });
            function tableAlphaNumericGst(cla_name,cunty_name) {
                var NumericError = 0;
                var cla_length = document.querySelectorAll("." + cla_name).length;

                for (var i = 0; i < cla_length; i++) {
                    var cla_val = document.querySelectorAll("." + cla_name)[i].value.trim();
                    var country_value = document.querySelectorAll("."+cunty_name)[i].value.trim();
                    console.log(country_value);
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

            $("body").on('click', '.addDivisionBtn', function() {
                var appendDivision = '<div class="customerDetails" id="customerDetails_' + divisionLength + '" name="customerDetails_' + divisionLength + '">';
                var addDivisionDivi = '<p style="width:98%;margin:10px 5px 5px 10px;display:inline-block;" id="addDivisionDiv_' + divisionLength + '"><label style="width: auto;">Division Name<span style="color: red;" for="fine">*</span></label><input type="text" name="customerDivisionListName" id="division_' + divisionLength + '" class="division" value=""><input type="button" value="Remove Division"  class="addDivsion removeDivision" ></p>';
                var addWorkLocation = '<div class="WorkLocation" id="WorkLocationDiv_' + divisionLength + '" name="WorkLocationDiv_' + divisionLength + '"><fieldset class="workLocationFieldset"><legend> Customer Work Location </legend>';
                addWorkLocation += '<input type="hidden" value="1" id="workLocationCount_' + divisionLength + '" name="workLocationCount_' + divisionLength + '" />';
                addWorkLocation += '<table class="customer_work_location" id="customerWorkAddress_' + divisionLength + '" name="customerWorkAddress_' + divisionLength + '" border="0" width="99%" ><tr><th >Address<span style="color: red;" for="fine">*</span><th>Short Code<span style="color: red;" for="fine">*</span></th></th><th >Country<span style="color: red;" for="fine">*</span></th>';
                addWorkLocation += '<th >Region <span style="color: red;" for="fine">*</span></th> <th >City<span style="color: red;" for="fine">*</span></th><th >Pincode<span style="color: red;" for="fine">*</span></th>';
                addWorkLocation += '<th >Working Hours/Day<span style="color: red;" for="fine">*</span></th><th >Work Location<span style="color: red;" for="fine">*</span></th><th >Action</th></tr>';
                //addWorkLocation += '<tr class="copyRow"><td colspan="8"><input type="checkbox" name="copyAddress" class="copyAddress" value="copy"/><span>Customer Work Location same as Customer Billing Address</span>&nbsp;&nbsp;<select class="billAddOptions"><option>-Select Address-</option></select></td></tr>';
                addWorkLocation += '<tr id="workLocationRow_' + divisionLength + '_1" name="workLocationRow_' + divisionLength + '_1">';
    //                                    '<td><input type="text" width="150px" value="" name="customerWorkAddressNew_'+divisionLength+'_1" id="w_adress_'+divisionLength+'_1" class="customerWorkAddress" /></td>';
                addWorkLocation += '<td>';
                addWorkLocation += '<textarea cols="20" rows="2" name="customerWorkAddressNew_' + divisionLength + '_1"  id="customerWorkAddressNew_' + divisionLength + '_1"  maxlength="200"  minlength="10" class="customerWorkAddress" style="min-width: 221px ;max-width: 221px;">';
                addWorkLocation += '</textarea></td>';
                addWorkLocation += '<td align="center"><input type="text" value="" name="customerWorkShortCodeNew_' + divisionLength + '_1" id="customerWorkShortCode_' + divisionLength + '_1" class="custWorkShortCode" maxlength="15"></td>';
                addWorkLocation += '<td><select name="customerWorkCountryNew_' + divisionLength + '_1" id="customerWorkCountryNew_' + divisionLength + '_1" class="customerWorkCountry  country_select">';
                addWorkLocation += '<option value="">-- Select Country -- </option><c:forEach items="${countryList}" var="countryList" ><option value="${countryList.countryID}">${countryList.countryName}</option></c:forEach></select></td>';
                addWorkLocation += '<td><select name="customerWorkRegionNew_' + divisionLength + '_1" id="customerWorkRegionNew_' + divisionLength + '_1" class="customerWorkRegion  region_select"> <option value="">-- Select Region -- </option></select></td><td>';
                addWorkLocation += '<select name="customerWorkCityNew_' + divisionLength + '_1" id="customerWorkCityNew_' + divisionLength + '_1" class="customerWorkCity "><option value="">-- Select City -- </option></select></td>';
                addWorkLocation += '<td><input type="text" width ="120px" name="customerWorkPincodeNew_' + divisionLength + '_1" id="w_pincode_' + divisionLength + '_1" class="customerWorkPincode"/></td>';
                addWorkLocation += '<td><input type="text" name="workingHoursPerDayNew_' + divisionLength + '_1" id="w_working_hours_' + divisionLength + '_1" class="workingHoursPerDay  " value="" placeholder="HH:MM"></td>';
                
                addWorkLocation += '<td><select name="workingLocationIsCompanyLocationNew_'+ divisionLength + '_1" id="workingLocationIsCompanyLocationNew_'+ divisionLength + '_1" class="customerWorkLocationDefining textbox_new">';
                addWorkLocation += '<option value="">--Select Location--</option>';
                addWorkLocation += '<option value="0">Customer Location</option>';
                addWorkLocation += '<option value="1">Company Location</option>';
                addWorkLocation += '</select></td>';

                addWorkLocation += '<td align="center" id="adress_actionItems_' + divisionLength + '_1"><img class="add_customer_wl" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;"></td></tr>';
                addWorkLocation += '</table></fieldset></div>';

                var addbusinessContact = '<div class="businessContact" id="businessContactDiv_' + divisionLength + '" name="businessContactDiv_' + divisionLength + '"><fieldset class="businessContactFieldset"><legend> Business Contact </legend><input type="hidden" value="1" id="businessContactCount_' + divisionLength + '" name="businessContactCount_' + divisionLength + '" />';
                addbusinessContact += '<table class="business_contact" id="businessContact_' + divisionLength + '" name="businessContact_' + divisionLength + '" border="0" width="99%"><tr><th>Customer Contact person<span style="color: red;" for="fine">*</span></th> <th>Designation<span style="color: red;" for="fine">*</span></th><th>Customer Contact phone<span style="color: red;" for="fine">*</span></th>';
                addbusinessContact += '<th>Customer Contact Email <span style="color: red;" for="fine">*</span></th><th>Action</th></tr>';
                addbusinessContact += '<tr id="businessContactRow_' + divisionLength + '_1" name="businessContactRow_' + divisionLength + '_1" >';
                addbusinessContact += '<td><input type="text" name="customerContactPersonNew_' + divisionLength + '_1" id="customerContactPerson_' + divisionLength + '_1" class="customerContactPerson " value=""></td>';
                addbusinessContact += '<td><input type="text" name="customerContactDesignationNew_' + divisionLength + '_1" id="customerContactDesignation_' + divisionLength + '_1" class="customerContactDesignation " value=""></td>';
                addbusinessContact += '<td><input type="text" name="customerContactPhoneNew_' + divisionLength + '_1" id="customerContactPhone_' + divisionLength + '_1" class="customerContactPhone " value=""></td>';
                addbusinessContact += '<td><input type="text" name="customerContactEmailNew_' + divisionLength + '_1" id="customerEmail_' + divisionLength + '_1" class="customerContactEmail  email " value=""></td>';
                addbusinessContact += '<td align="center" id="adress_actionItems_' + divisionLength + '_1"><img class="add_customer_contact" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;"></td></tr>';
                addbusinessContact += '</table></fieldset></div>';

                var addfinanceContact = '<div class="financeContact" id="financeContactDiv_' + divisionLength + '" name="financeContactDiv_' + divisionLength + '"><fieldset class="financeContactFieldset"><legend> Finance Contact </legend><input type="hidden" value="1" id="financeContactCount_' + divisionLength + '" name="financeContactCount_' + divisionLength + '" />';
                addfinanceContact += '<table class="finance_contact" id="financeContact_' + divisionLength + '" name="financeContact_' + divisionLength + '" border="0" width="99%"><tr><th>Finance Contact person<span style="color: red;" for="fine">*</span></th><th>Designation<span style="color: red;" for="fine">*</span></th><th>Finance Contact phone<span style="color: red;" for="fine">*</span></th>';
                addfinanceContact += '<th>Finance Contact Email <span style="color: red;" for="fine">*</span></th><th>Action</th></tr><tr id="financeContactRow_' + divisionLength + '_1">';
                addfinanceContact += '<td><input type="text" name="customerFinanceContactPersonNew_' + divisionLength + '_1" id="customerFinanceContactPerson_' + divisionLength + '_1" class="customerFinanceContactPerson" value=""></td>';
                addfinanceContact += '<td><input type="text" name="customerFinanceContactDesignationNew_' + divisionLength + '_1" id="customerFinanceContactDesignation_' + divisionLength + '_1" class="customerFinanceContactDesignation " value=""></td>';
                addfinanceContact += '<td><input type="text" name="customerFinanceContactPhoneNew_' + divisionLength + '_1" id="customerFinanceContactPhone_' + divisionLength + '_1" class="customerFinanceContactPhone " value=""></td>';
                addfinanceContact += '<td><input type="text" name="customerFinanceContactEmailNew_' + divisionLength + '_1" id="customerFinanceEmail_' + divisionLength + '_1" class="customerFinanceContactEmail  email " value=""></td>';
                addfinanceContact += '<td align="center" id="adress_actionItems_' + divisionLength + '_1"><img class="add_finance_contact" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;"></td>';
                addfinanceContact += '</tr></table></fieldset></div>';

                var addbillingAddress = '<div class="billingAddress" id="billingAddressDiv_' + divisionLength + '" name="billingAddressDiv_' + divisionLength + '">';
                addbillingAddress += '<fieldset class="billingAddressFieldset"><legend> Customer Billing Address<font color="red">*</font></legend>';
                addbillingAddress += '<input type="hidden" value="1" id="businessAddressCount_' + divisionLength + '" name="businessAddressCount_' + divisionLength + '" />';
                addbillingAddress += '<table class="customer_billing_address" id="customerBillingAddress_' + divisionLength + '" name="customerBillingAddress_' + divisionLength + '" border="0" width="99%"><tr><th>Address<span style="color: red;" for="fine">*</span></th><th>Short Code<span style="color: red;" for="fine">*</span></th><th>Country<span style="color: red;" for="fine">*</span></th>';
                addbillingAddress += '<th>Region<span style="color: red;" for="fine">*</span></th><th>City<span style="color: red;" for="fine">*</span></th><th>Pincode<span style="color: red;" for="fine">*</span></th><th>State Code<span style="color: red;" for="fine">*</span></th><th>GSTIN/UID<span style="color: red;" for="fine">*</span></th><th>Action</th></tr><tr class="business_row" id="business_row_' + divisionLength + '_1">';
    //                addbillingAddress += '<td><input type="text" value="" name="customerBillingAddressNew_'+divisionLength+'_1" id="b_adress_'+divisionLength+'_1" class="customerBillingAddress"/></td>';
                addbillingAddress += '<td><textarea cols="20" rows="2" name="customerBillingAddressNew_' + divisionLength + '_1"  id="customerBillingAddressNew_' + divisionLength + '_1"  maxlength="200"  minlength="10" class="customerBillingAddress" style="min-width: 221px ;max-width: 221px;">';
                addbillingAddress += '</textarea></td>';
                addbillingAddress += '<td align="center"><input type="text" value="" name="customerBillingShortCodeNew_' + divisionLength + '_1" id="customerBillingShortCode_' + divisionLength + '_1" class="custBillingShortCode" maxlength="15"></td>';
                addbillingAddress += '<td><select name="customerBillingCountryNew_' + divisionLength + '_1" class="customerBillingCountry country_select" id="customerBillingCountry_' + divisionLength + '_1" class="country_select"><option value="">-- Select Country -- </option>';
                addbillingAddress += '<c:forEach items="${countryList}" var="countryList" ><option value="${countryList.countryID}">${countryList.countryName}</option></c:forEach></select></td>';
                addbillingAddress += '<td><select name="customerBillingRegionNew_' + divisionLength + '_1" id="customerBillingRegion_' + divisionLength + '_1" class="customerBillingRegion  region_select"><option value="">-- Select Region -- </option></select></td>';
                addbillingAddress += '<td><select name="customerBillingCityNew_' + divisionLength + '_1" id="customerBillingCity_' + divisionLength + '_1" class="customerBillingCity "><option value="">-- Select City -- </option></select></td>';
                addbillingAddress += '<td><input type="text" name="customerBillingPincodeNew_' + divisionLength + '_1" id="b_pincode_' + divisionLength + '_1" class="customerBillingPincode"/></td>';
                addbillingAddress += '<td align="center"><input type="text" name="customerBillingGstCodeNew_'+ divisionLength +'_1" id="customerBillingGstCode_' + divisionLength + '_1" readonly class="custBillingGstCode"/></td>';
                addbillingAddress += '<td align="center"><input type="text" name="customerBillingGstNumberNew_'+ divisionLength +'_1" id="customerBillingGstNumberNew_' + divisionLength + '_1" class="customerBillingGstNumber"/></td>';
                addbillingAddress += '<td align="center"  id="adress_actionItems_' + divisionLength + '_1"><img class="add_billing_row" src="${pageContext.request.contextPath}/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;"></td>';
                addbillingAddress += '</tr></table></fieldset></div>';
                
                 var invoiceContact = '<div class="businessContact" id="businessContactDiv_' + divisionLength + '" name="businessContactDiv_' + divisionLength + '"><fieldset class="businessContactFieldset"><legend> Invoice Recipient</legend>';
            invoiceContact += '<table class="invoice_contact" id="invoice_contact_' + divisionLength + '" name="invoice_contact_' + divisionLength + '" border="0" width="100%" align="center">';
            invoiceContact += '<tbody><tr><td style="padding-top:10px;width:150px;"><b>Invoice will be Emailed To</b><span style="color: red;" for="fine">*</span> </td>';
            invoiceContact += '<td><select name="invoiceTo_' + divisionLength + '" id="invoiceTo_' + divisionLength + '" class="custInvoice required textbox_new country_select" style="width:150px;>';
            invoiceContact += '<c:forEach items="${invoiceList}" var="invoiceObj" >';
            invoiceContact += '<option value="${invoiceObj.configKey}" ${selectedInvoice}> ${invoiceObj.configValue}</option></c:forEach></select>';
            invoiceContact += '</td></tr></tbody></table></fieldset></div>';

                appendDivision += addDivisionDivi;
                appendDivision += addbillingAddress;
                appendDivision += addWorkLocation;
                appendDivision += addbusinessContact;
                appendDivision += addfinanceContact;
                appendDivision += invoiceContact;

                appendDivision += '</div>';
                /***********/
                $(".content_page").append(appendDivision);
                $("#customerDetails_" + divisionLength).attr("tabindex", -1).focus();
                divisionLength++;
            });
            $("body").on('click', 'input.removeDivision', function() {
                var DivHeight = $("html").height();
                $("#RemoveIndividualDiv").css({"height": DivHeight, "display": "block"});
                $("#RemoveIndividualDivFocus").attr("tabindex", -1).focus();
                var closestDivDelete = $(this).closest(".customerDetails");
                $("body").on('click', '#removeDivYes', function() {
                    closestDivDelete.remove();
                    $("#RemoveIndividualDiv").css({"display": "none"});
                });
                $("body").on('click', '#removeDivNo', function() {
                    $("#RemoveIndividualDiv").css({"display": "none"});
                });
            });
            
            //Customer Billing Address
             $("body").bind('keyup', '.custBillingShortCode', function(){
                    var tableLength = $(".customer_billing_address").length;
    //                alert(tableLength);
                    var shortCode = $(".custBillingShortCode");
                    var tableCount =1;
                    for(tableCount=1; tableCount<=tableLength; tableCount++)
                    {
                    $("#customerBillingAddress_"+tableCount+" tr .custBillingShortCode").each(function(){
                       var checkCode = $(this);
                       var currentCode = checkCode.val();
                       var cCode = shortCode.val();
                       var currentId = checkCode.parent().parent().attr('id');
                       var cCodeId = shortCode.parent().parent().attr('id');
                       if(currentCode === cCode && currentId !== cCodeId)
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
             $("body").on('keyup', '.custWorkShortCode', function(){
                    var tableLength = $(".customer_work_location").length;
                    var shortCode = $(this);
                    var tableCount =1;
                    for(tableCount=1; tableCount<=tableLength; tableCount++)
                    {
                    $("#customerWorkAddress_" + tableCount + " tr .custWorkShortCode").each(function () {
                       var checkCode = $(this);
                       var currentCode = checkCode.val();
                       var cCode = shortCode.val();
                       var currentId = checkCode.parent().parent().attr('id');
                       var cCodeId = shortCode.parent().parent().attr('id');
                       if(currentCode === cCode && currentId !== cCodeId)
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
                    var tableLength = $(".customer_work_location").length;
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