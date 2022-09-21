<%-- 
    Document   : customerGroupEdit
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14058
--%>
<%@include file="header.jsp" %>
<head>
    <title>Edit Customer Group</title>
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
            /*none repeat scroll 0 0 #DFE8F6*/
            border:1px solid #99BBE8;
            border-top:0;
            clear: both;
            /*color: #000;*/
            width: 100%;
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
        function submitForm(){
            document.getElementById("reportForm").action="customerList.htm";
            document.reportForm.submit();
            startLoading();
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
        function disableSave(saveButtonId,submitButtonId,backButtonId)
        {
            if($("#customerName").val() == "") {
                $("#errormessage").html("Please Select Customer Name")
                return false;
            } else if ($("#salesPerson").val() == "") {
                $("#errormessage").html("Please Select Customer Owner")
                return false;
            } else {
                $("#"+saveButtonId).hide();
                $("#"+submitButtonId).hide();
                $("#"+backButtonId).hide();
                return true;

            }
        }
        function disableSubmit(saveButtonId,submitButtonId,backButtonId)
        {
            $("#formCustomerDetails").validate();
            if($("#formCustomerDetails").valid()) {
                var returnVariable;
                if($("#groupName").val() != "") {
                    customerGroupNameD = $("#groupName").val();
                    groupIDVar = $("#groupId").val();
                    $.ajax({
                        url: './getDuplicateCustomerGroupName.htm',
                        type: "POST",
                        async: false,
                        data: ({customerGroupName:customerGroupNameD,groupIdVar:groupIDVar}),
                        success: function(ajaxObj) {
                            if($.trim(ajaxObj) != "") {
                                $("#errormessage").html("Customer Group Name already exist")
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
                <p class="page_heading"><span class="heading">Edit Customer Group</span> </p>
            </div>
            <div class="notice_page">
                <div style="float: left"><img src="/ideal_rrf_new/css/images/icon_alert.png" title="Information" alt="Information"></img></div>
                <div style="padding-left: 10px;padding-top: 1px;">
                    <img src="/ideal_rrf_new/css/images/tick.png" title="Information" alt="Information" style="margin-left: 15px;margin-right: 10px;"></img>
                    Field marked in * are mandatory.
                </div>
            </div>
            <form action="saveCustomerGroup.htm" name="formCustomerDetails" id="formCustomerDetails" method="post">
                <div class="formContent_new" id="datadisplay" style="height:auto">
                    <table style="width: 80%" id="formTable" border="0" align="center">
                        <tbody>
                            <tr>
                                <td align="center" colspan="4" width="100%" height="15">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <tr class="">
                                <td style="text-align:left">
                                    <label class="headLabel">Group Name<font color="red">*</font></label>
                                </td>
                                <td style="text-align:left">
                                    <input type="text" name="groupName" id="groupName" style="width: 303px;" class="required textbox_new" value="${selectedCustomerGroupData[0].groupName}">
                                </td>
                                <td  style="text-align:left">
                                    <!--                                    <label class="headLabel">Group Code</label>-->
                                </td>
                                <td  style="text-align:left">
<!--                                    <input type="text" name="groupCode" id="groupCode" class="required formInputField" value="${selectedCustomerGroupData[0].groupCode}">-->
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <div class="buttonAlignment">
                    <div class="buttonAlignment" id="btnGroup">
                        <input type="hidden" name="employeeId" id="employeeId" value="${employeeId}" />
                        <input type="hidden" name="groupId" id="groupId" value="${selectedCustomerGroupData[0].groupId}" />
                        <input type="submit" name="customerGroupSubmitButton" style="width: 90px;" id="submitBtn" value="Submit" class="qualitysubmit" onclick="return disableSubmit('saveBtn','submitBtn','btnCancel');">
                        <input type="button" name="btnCancel" id="btnCancel" value="Back" class="qualityback" onclick="javascript: location.href='authenticate.htm?empId=${employee_no}&modId=529'">
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