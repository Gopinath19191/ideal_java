<%@page contentType="text/html" import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="header.jsp" %>
<html>
    <head>
        <title>Debtors Report</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.12.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/demo_table.css" />
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    </head>
    <style type="text/css">
        table.dataTable thead .sorting_asc {
            background: url("http://cdn.datatables.net/1.10.0/images/sort_asc.png") no-repeat center left;
        }
        table.dataTable thead .sorting_desc {
            background: url("http://cdn.datatables.net/1.10.0/images/sort_desc.png") no-repeat center left;
        }
        table.dataTable thead .sorting {
            background: url("http://cdn.datatables.net/1.10.0/images/sort_both.png") no-repeat center left;
        }

        .gobutton{
            border: 1px solid #BCCFEA;
            background: none repeat scroll 0 0 #316ca8;
            color: #FFFFFF;
            float: left;
            font-weight: bold;
            height: 32px;
            margin: 0px;
            width: 50px;
        }
       
        table { 
            width: 100%; 
            
        }
       
        tr:nth-of-type(odd) { 
            background: #eee; 
        }
        th { 
            background: #eee; 
            color: black; 
            font-weight: bold; 
        }
        td, th { 
            padding: 6px; 
            border: 0.5px #4884B8; 
            text-align: left; 
        }
        #loadingDiv img{ border: none; }
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1;z-index: 100;}

        .dataTables_wrapper {
            position: relative;
            clear: both;
            zoom: 1;
            margin: 0 auto;
        }
        #example_filter label {
            width: 100%;
        }
        
        #example_wrapper, .dataTables_scrollHead, .dataTables_scrollHeadInner {
            padding: 0 !important;
        }
        .dataTables_scrollHeadInner {
            width: 100%;
        }
        #example td {
            font-size: 11px !important;
        }
        .filter_align{
            position: absolute;
            margin-top: 6px;
            left: 14px;
        }
        .dataTables_filter label input[type="search"] {

        }
        #example_filter {
            width: 100%;
        }
        #example_filter label:nth-child(3) {
            width: 25%;
            float: right;
            padding-top: 10px;

        }
        

        form div{
            padding:0px !important;
        }
      

        .common_date_submission{

        }
        .common_date_submission_info{
            margin: 10px 0px 0px 10px;
            display: inline-block;
            background-color: #EBDFB5;
        }
    </style>
    <script type="text/javascript">
        function updateDataTableSelectAllCtrl(table){
            var $table             = table.table().node();
            var $chkbox_all        = $('tbody input[type="checkbox"]', $table);
            var $chkbox_checked    = $('tbody input[type="checkbox"]:checked', $table);
            var chkbox_select_all  = $('thead input[id="select_all"]', $table).get(0);

            // If none of the checkboxes are checked
            if($chkbox_checked.length === 0){
                chkbox_select_all.checked = false;
                if('indeterminate' in chkbox_select_all){
                    chkbox_select_all.indeterminate = false;
                }

                // If all of the checkboxes are checked
            } else if ($chkbox_checked.length === $chkbox_all.length){
                chkbox_select_all.checked = true;
                if('indeterminate' in chkbox_select_all){
                    chkbox_select_all.indeterminate = false;
                }

                // If some of the checkboxes are checked
            } else {
                chkbox_select_all.checked = true;
                if('indeterminate' in chkbox_select_all){
                    chkbox_select_all.indeterminate = true;
                }
            }
        }
       
         
        $(document).ready(function() {
           
           
            $('body').css('overflow','hidden');
            var rows_selected = [];
            var table =  $('#example').DataTable({
                "sScrollY":        "340px",
                "sScrollX": true,
                "scrollCollapse": true,
                "paging":         false,
                'columnDefs': [{
                        'targets': 0,
                        'searchable': false,
                        'orderable': false,
                        'className': 'dt-body-center'
                    }],
                'order': [[1, 'asc']],
                'rowCallback': function(row, data, dataIndex){
                   
                    var rowId = data[0];

                   
                    if($.inArray(rowId, rows_selected) !== -1){
                        $(row).find('input[type="checkbox"]').prop('checked', true);
                        $(row).addClass('selected');
                    }
                }
               
            });
         
            $('#example tbody ').on('click','input[type="checkbox"]', function(e){
                var $row = $(this).closest('tr');
                
                var data = table.row($row).data();

               
                var rowId = data[0];
               
                var index = $.inArray(rowId, rows_selected);

                if(this.checked && index === -1){
                    rows_selected.push(rowId);

                  
                } else if (!this.checked && index !== -1){
                    rows_selected.splice(index, 1);
                }
              
                var attr_id = $(rowId).attr('id');
                var rowValue = attr_id.substring(10);
                if(this.checked){
                    $row.addClass('selected');
                    if($("#invoice_datePickerCommon").val() != ''){
                        
                        $("#invoiceDateSubmitCustomer_"+rowValue).val($("#invoice_datePickerCommon").val());
                        
                    }
                    if($("#customer_expectdatePickerCommon").val() != ''){
                       
                        $("#expectedCollectionDate_"+rowValue).val($("#customer_expectdatePickerCommon").val());
                       
                    }
                    $("#invoiceDateSubmitCustomer_"+rowValue).attr('disabled', false);
                    $("#expectedCollectionDate_"+rowValue).attr('disabled', false);
                    $("#dateOfReport_"+rowValue).attr("disabled",false);
                    $("#invoiceNumber_"+rowValue).attr("disabled",false);
                } else {
                    $row.removeClass('selected');
                    $("#invoiceDateSubmitCustomer_"+rowValue).attr('disabled', 'disabled');
                    $("#expectedCollectionDate_"+rowValue).attr('disabled', 'disabled');
                    $("#dateOfReport_"+rowValue).attr("disabled",'disabled');
                    $("#invoiceNumber_"+rowValue).attr("disabled",'disabled');
                    $("#invoiceDateSubmitCustomer_"+rowValue).css('border-color', '');
                    $("#expectedCollectionDate_"+rowValue).css('border-color', '');
                    if($('#hidden-invoiceDateSubmitCustomer_'+rowValue).val() != ""){
                        $("#invoiceDateSubmitCustomer_"+rowValue).val($('#hidden-invoiceDateSubmitCustomer_'+rowValue).val());  
                    }else{
                        $("#invoiceDateSubmitCustomer_"+rowValue).val(''); 
                    }
                    if($('#hidden-expectedCollectionDate_'+rowValue).val() != ""){
                        $("#expectedCollectionDate_"+rowValue).val($('#hidden-expectedCollectionDate_'+rowValue).val()); 
                    }else{
                        $("#expectedCollectionDate_"+rowValue).val(''); 
                    }
                }

               
                updateDataTableSelectAllCtrl(table);

                
                e.stopPropagation();
            });
        
        
            $('thead input[id="select_all"]', table.table().container()).on('click', function(e){
                if(this.checked){
                    $('#example tbody input[type="checkbox"]:not(:checked)').trigger('click');
                } else {
                    $('#example tbody input[type="checkbox"]:checked').trigger('click');
                }

               
                e.stopPropagation();
            });
            table.on('draw', function(){
              
                updateDataTableSelectAllCtrl(table);
            });

            $("#submitButton").on('click',function(){
                if(rows_selected.length > 0){
                    var error = 0;
                    var error_row = [];
                    try{
                        $.each(rows_selected, function(index, rowId){
                            var attr_id = $(rowId).attr('id');
                            var rowValue = attr_id.substring(10);
                            if($("#invoiceDateSubmitCustomer_"+rowValue).val() == ""){
                                $("#invoiceDateSubmitCustomer_"+rowValue).css('border-color', 'red');
                                var theRow = $("#invoiceDateSubmitCustomer_"+rowValue).parent().parent();
                                error_row.push($(theRow).index());
                            }else{
                                $("#invoiceDateSubmitCustomer_"+rowValue).css('border-color', '');
                            }
                            if($("#expectedCollectionDate_"+rowValue).val() == ""){
                                $("#expectedCollectionDate_"+rowValue).css('border-color', 'red');
                                var theRow = $("#expectedCollectionDate_"+rowValue).parent().parent();
                                error_row.push($(theRow).index());
                            }else{
                                $("#expectedCollectionDate_"+rowValue).css('border-color', '');
                            }
                        });
                    }catch(err){
                        
                    }
                    try{
                        var rowNo = 0;
                        for(var i = 0;i<=error_row.length;i++){
                            rowNo = error_row[i];
                            break;
                        }
                        var ypos = 0;
                        if(rowNo != undefined && rowNo != 0){
                            ypos = $('#example tr:eq('+rowNo+')').position().top;
                        }
                        $('.dataTables_scrollBody').animate({
                            scrollTop: ypos,
                            scrollLeft: 900
                        },500);
                    }catch(err){
                       
                    }
                
                    if(error_row.length == 0){
                        if (confirm("Are You sure do you want to submit?") == true) {
                            var form = $('#custaging');
                            form.attr("action", "updateInvoiceDates.htm");
                            document.custaging.submit();
                            startLoading();
                        } 
                    }
                }else{
                    alert("Please check the checkbox to continue");
                }
            });
             
            var updateDate = function(date,column){
                var focusRow = [];
                var flag = 0;
                if(rows_selected.length<=0){
                    alert("Please select the row");
                    $("#customer_expectdatePickerCommon").val('');
                    $("#invoice_datePickerCommon").val('');
                }
               
                $.each(rows_selected, function(index, rowId){
                    var attr_id = $(rowId).attr('id');
                    var rowValue = attr_id.substring(10);
                    if(column == "invoiceDateSubmitCustomer"){
                       
                        $("#invoiceDateSubmitCustomer_"+rowValue).val(date);
                       
                        var theRow = $("#invoiceDateSubmitCustomer_"+rowValue).parent().parent();
                        focusRow.push($(theRow).index());
                    }
                    else if(column == "expectedCollectionDate"){
                        $("#expectedCollectionDate_"+rowValue).val(date);
                        var theRow = $("#expectedCollectionDate_"+rowValue).parent().parent();
                        focusRow.push($(theRow).index());
                    }
                });
             
                var rowNo = 0;
                for(var i = 0;i<=focusRow.length;i++){
                    rowNo = focusRow[i];
                    break;
                }
                var ypos = 0;
                if(rowNo != undefined && rowNo != 0){
                    ypos = $('#example tr:eq('+rowNo+')').position().top;
                }
                $('.dataTables_scrollBody').animate({
                    scrollTop: ypos,
                    scrollLeft: 900
                },500);
              
            }
            $('#example_filter').prepend('<div class="common_date_submission_info"><img style="width: 15px;padding-right: 5px;  height: 15px;  vertical-align: bottom;" src="css/images/icon_alert.png">In case of multiple line items to be submitted with same date choose the line item and enter the date here to submit.</div><div class="common_date_submission"><label style="width: 31%; float: left; padding-top: 10px;margin-left: 10px;">Date of Submission<input type="text"  id="invoice_datePickerCommon" /></label>'
                +'<label style="width: 31%;float: left;padding-top: 10px;">Expected Collection Date<input  type="text"  id="customer_expectdatePickerCommon" /></label></div>'
        ); 
          
            var min_date = function(){
                var customer_expect_date =  $("#customer_expectdatePickerCommon").val();
                if(customer_expect_date != ''){
                    var datefrom = customer_expect_date.split("-");
                    var max_date = new Date();
                    max_date.setDate(datefrom[0]);
                    max_date.setMonth(datefrom[1]-1);    
                    max_date.setFullYear(datefrom[2]);
                    max_date.setDate(max_date.getDate());
                    $("#invoice_datePickerCommon").datepicker( "option", "maxDate", max_date);
                }
            }
            $("#invoice_datePickerCommon").datepicker({
                maxDate:'',
                showOn: "both",
                dateFormat: "dd-mm-yy",
                showOn: "button",
                showAnim: 'slideDown',
                showButtonPanel: true ,
                autoSize: true,
                beforeShow : min_date,
                buttonImage: "//jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                buttonImageOnly: true,
                buttonText: "Select date",
                closeText: "Clear",
                onClose: function() {
                    updateDate($("#invoice_datePickerCommon").val(),"invoiceDateSubmitCustomer");
                }
            });
            var max_date = function(){
                var invoice_date =  $("#invoice_datePickerCommon").val();
                if(invoice_date != ""){
                    var datefrom = invoice_date.split("-");
                    var minimumDate = new Date();
                    minimumDate.setDate(datefrom[0]);
                    minimumDate.setMonth(datefrom[1]-1);    
                    minimumDate.setFullYear(datefrom[2]);
                    minimumDate.setDate(minimumDate.getDate());
                    $("#customer_expectdatePickerCommon").datepicker( "option", "minDate", minimumDate );
                }
            }
          
            $("#customer_expectdatePickerCommon").datepicker({
                dateFormat: "dd-mm-yy",
                showOn: "button",
                showAnim: 'slideDown',
                showButtonPanel: true ,
                beforeShow : max_date,
                autoSize: true,
                buttonImage: "//jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                buttonImageOnly: true,
                buttonText: "Select date",
                closeText: "Clear",
                onClose: function() {
                    updateDate($("#customer_expectdatePickerCommon").val(),"expectedCollectionDate");
                }
            });
            
        });
        var enabledDays = [];
        <c:if test="${ReportDates != null}">
            <c:forEach items="${ReportDates}" var="dateOfReport" varStatus="status"> 
                enabledDays.push("${dateOfReport.date_of_report}");
            </c:forEach>
        </c:if>
            var displayDates = function(date){
                var string = jQuery.datepicker.formatDate('dd-mm-yy', date);
                var length = enabledDays.length;
                for(var i = 0; i < length; i++) {
                    if(enabledDays[i] == string)
                        return [true];
                }
                return [false];

            }
            function getExcelReport(){
                $('#reportPage').attr("action", "CustagingReportXL.htm");
                document.reportPage.submit();
            }

            function getFilterList(){
                $('#reportPage').attr("action", "CustagingReport.htm");
                document.reportPage.submit();
                startLoading();
            }

            $(document).ready(function(){
                
                $('#reportDate').datepicker({
                    showOn: "both",
                    changeMonth: true,
                    changeYear: true,
                    maxDate: 'today',
                    dateFormat:'dd-mm-yy',
                    buttonImage: "//jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                    beforeShowDay: displayDates,
                    onClose: function() {
                        loadBdmList();
                    }
                });
                 
                var minimumDate = '';
                var maximumDate = '';
                 
                var set_date = function(){ 
                    var currentId = this.id;
                    var rowValue = currentId.split('_');
                    var rowName = rowValue[0];
                    var rowValue = rowValue[1];
                    if(rowName == "invoiceDateSubmitCustomer"){ 
                        if($("#expectedCollectionDate_"+rowValue).val() != ""){
                            var expect_date = $("#expectedCollectionDate_"+rowValue).val();
                            var datefrom = expect_date.split("-");
                            maximumDate = new Date();
                            maximumDate.setDate(datefrom[0]);
                            maximumDate.setMonth(datefrom[1]-1);    
                            maximumDate.setFullYear(datefrom[2]);
                            maximumDate.setDate(maximumDate.getDate());
                            $("#"+currentId).datepicker( "option", "maxDate", maximumDate );
                        }
                    }else if(rowName == "expectedCollectionDate"){
                        var invoice_date = $("#invoiceDateSubmitCustomer_"+rowValue).val();
                        var datefrom = invoice_date.split("-");
                        minimumDate = new Date();
                        minimumDate.setDate(datefrom[0]);
                        minimumDate.setMonth(datefrom[1]-1);    
                        minimumDate.setFullYear(datefrom[2]);
                        minimumDate.setDate(minimumDate.getDate());
                        $("#"+currentId).datepicker( "option", "minDate", minimumDate );
                    }
                }
            
                $(".datePicker_startdate").datepicker({
                    beforeShow : set_date,
                    showOn: "both",
                    maxDate : maximumDate,
                    dateFormat: "dd-mm-yy",
                    showOn: "button",
                    showAnim: 'slideDown',
                    showButtonPanel: true ,
                    autoSize: true,
                    buttonImage: "//jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                    buttonImageOnly: true,
                    buttonText: "Select date",
                    closeText: "Clear"
                });
                $(".datePicker_enddate").datepicker({
                    minDate : minimumDate,
                    showOn: "both",
                    dateFormat: "dd-mm-yy",
                    showOn: "button",
                    showAnim: 'slideDown',
                    showButtonPanel: true ,
                    autoSize: true,
                    buttonImage: "//jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                    buttonImageOnly: true,
                    buttonText: "Select date",
                    closeText: "Clear",
                    beforeShow : set_date
                    
                });

            });
            function loadCustomersList() {
                var business_leader_id  = $("#business_leader_id").val();
                var bdm_id = $("#bdm_id").val();
                var reportDate = $("#reportDate").val();
                $("#customer_id").html("<option value=''>-- All --</option>");
                $.ajax({
                    url: 'getCustomerList.htm',
                    type: "POST",
                    async: false,
                    data: ({business_leader_id:business_leader_id,bdm_id:bdm_id,reportDate:reportDate}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
            }

            function loadBdmList() {
                
                var business_leader_id  = $("#business_leader_id").val();
                var reportDate = $("#reportDate").val();
                $("#bdm_id").html('').html("<option value=''>-- All --</option>");
                $("#customer_id").html('').html("<option value=''>-- All --</option>");
                //if(business_leader_id != '') {
                $.ajax({
                    url: 'getBdmList.htm',
                    type: "POST",
                    async: false,
                    data: ({business_leader_id:business_leader_id,reportDate:reportDate}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
                $.ajax({
                    url: 'getCustomerList.htm',
                    type: "POST",
                    async: false,
                    data: ({business_leader_id:business_leader_id,reportDate:reportDate}),
                    success: function(ajaxObj) {
                        var returnData = eval(ajaxObj);
                        returnData;
                    }
                });
               
            }
            function convertToRupee(rupee,obj){
                var attr_id = $(obj).attr('id');
                var x=rupee;
                x=x.toString();
                var afterPoint = '';
                if(x.indexOf('.') > 0)
                    afterPoint = x.substring(x.indexOf('.'),x.length);
                x = Math.floor(x);
                x=x.toString();
                var lastThree = x.substring(x.length-3);
                var otherNumbers = x.substring(0,x.length-3);
                if(otherNumbers != '')
                    lastThree = ',' + lastThree;
                var res = otherNumbers.replace(/\B(?=(\d{2})+(?!\d))/g, ",") + lastThree + afterPoint;
                $("#"+attr_id).text(res);
            }
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Debtors Report
                </div>
            </div>
            <form action="#" name="reportPage" method="post" id="reportPage">
                <div class="tabletools" >

                    <c:if test="${access == 'BUSINESS_LEADER' }">
                        <input type="hidden" id="business_leader_id" name="business_leader_id" value="${employee_id}">
                    </c:if>
                    <table id="searchForm" width="100%" style="height: 38px;">
                        <tbody style="height: 23px;">
                            <tr>
                                <td width="30%">
                                    <label for="reportDate" style="width: 115px;"><b>Debtors Report as on : </b></label>
                                    <c:if test="${filterData.reportDate != '' && filterData.reportDate!=null}">
                                        <c:set var="selDateDisp" value="${filterData.reportDate}" ></c:set>
                                    </c:if>
                                    <input name="reportDate" id="reportDate" class="textbox_new" value="${systemDate}" readonly="true" />
                                </td>

                                <td width="30%">
                                    <label for="sbuFilter"  style="width: 115px;"><b>Legal Entity :</b> </label>
                                    <select id="legal_entity" name="legal_entity" class="textbox_new"> 
                                        <option value="" >-- All --</option>
                                        <c:forEach items="${legal_entities}" var="legalEntity" varStatus="sbuitr">
                                            <c:set var="selSbu" value="" ></c:set>
                                            <c:if test="${legalEntity.legal_entity_id==filterData.legal_entity}">
                                                <c:set var="selSbu" value="selected='selected'" ></c:set>
                                            </c:if>
                                            <option ${selSbu} value="${legalEntity.legal_entity_id}">${legalEntity.legal_entity_code}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <c:if test="${access == 'FULL'}">
                                    <td width="30%">
                                        <label for="sbuFilter"  style="width: 115px;"><b>Business Leader: </b></label>
                                        <select id="business_leader_id" name="business_leader_id" onchange="loadBdmList();" class="textbox_new">
                                            <option value="" >-- All --</option>
                                            <c:forEach items="${business_leader}" var="businessLeader" varStatus="sbuitr">
                                                <c:set var="selSbu" value="" ></c:set>
                                                <c:if test="${businessLeader.business_leader_id==filterData.business_leader_id}">
                                                    <c:set var="selSbu" value="selected='selected'" ></c:set>
                                                </c:if>
                                                <option ${selSbu} value="${businessLeader.business_leader_id}">${businessLeader.business_leader_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </c:if>
                            </tr>
                            <tr>
                                <c:if test="${access == 'FULL' || access == 'BUSINESS_LEADER' }">
                                    <td width="30%">
                                        <label for="sbuFilter"  style="width: 115px;"><b>Customer Owner : </b></label>
                                        <select id="bdm_id" name="bdm_id" onchange="loadCustomersList();" class="textbox_new">
                                            <option value="" >-- All --</option>
                                            <c:forEach items="${bdm_name}" var="bdmName" varStatus="sbuitr">
                                                <c:set var="selSbu" value="" ></c:set>
                                                <c:if test="${bdmName.bdm_id==filterData.bdm_id}">
                                                    <c:set var="selSbu" value="selected='selected'" ></c:set>
                                                </c:if>
                                                <option ${selSbu} value="${bdmName.bdm_id}">${bdmName.bdm_name}</option>
                                            </c:forEach>
                                        </select>
                                    </td>
                                </c:if>

                                <td width="30%">
                                    <label for="sbuFilter"  style="width: 115px;"><b>Customer : </b></label>
                                    <select id="customer_id" name="customer_id" class="textbox_new">
                                        <option value="" >-- All --</option>
                                        <c:forEach items="${customer_name}" var="customerName" varStatus="sbuitr">
                                            <c:set var="selSbu" value="" ></c:set>
                                            <c:if test="${customerName.customer_id==filterData.customer_id}">
                                                <c:set var="selSbu" value="selected='selected'" ></c:set>
                                            </c:if>
                                            <option ${selSbu} value="${customerName.customer_id}">${customerName.customer_name}</option>
                                        </c:forEach>
                                    </select>
                                </td>

                                <td width="40%">
                                    <label for="agingOverDue" style="width: 115px;"><b>Aging Of OverDue :</b></label>
                                    <select id="operators" name="operators" style="width: 40px;float: left;">
                                        <option value="1" ${"1" eq selectOperator ? 'selected':''}>=</option>
                                        <option value="2" ${"2" eq selectOperator ? 'selected':''}>></option>
                                        <option value="3" ${"3" eq selectOperator ? 'selected':''}>>=</option>
                                        <option value="4" ${"4" eq selectOperator ? 'selected':''}><</option>
                                        <option value="5" ${"5" eq selectOperator ? 'selected':''}><=</option>
                                    </select>
                                    <input type="text" id="overDue_aging" name="overDue_aging" class="textbox_new" style="width: 50px;height: 17px;" maxlength="3" value="${overDue_aging}"/>


                                    <!--<td  style="margin-right: 68px; padding-left: 147px;width: 10%">-->

                                    <input class="gobutton" align="middle" onclick="getFilterList()" type="submit" value="Go"/>
                                    <input class="exportbutton" align="middle" type="button" style="margin-left: 20px;" onclick="getExcelReport()" value="Export"/>
                                </td>

                            </tr>

                        </tbody></table>
                   
                </div>
            </form>                   
        </div>

        <div width="100%">

            <form id="custaging" method="post"  name="custaging" action="#"> 
                <c:if test="${access != 'BUSINESS_LEADER' }">

                </c:if>
                <table id="example"  class="stripe row-border order-column cell-border" style="word-wrap:break-word;" cellspacing="0" width="100%">

                    <thead><tr>
                            <c:if test="${access != 'BUSINESS_LEADER'}">
                                <th width="2%"><input type="checkbox"   id="select_all"/></th>
                                </c:if>
                                <c:if test="${access == 'BUSINESS_LEADER'}">
                                <th >Customer Name</th>
                                <th>Customer Owner</th>
                                <th>Project ID</th>
                                <th style="word-break: keep-all;width:20px;">Project Name</th>
                                <th>Customer Contact Name</th>
                                <th style="word-break: keep-all;width:20px;">Customer Email ID</th>
                                <th>Customer Phone No</th>
                                <th>Invoice Number</th>
                                <th>Invoice Date</th>
                                <th>Credit Period</th>
                                <th>Due Date</th>
                                <th>Date of Submission</th>
                                <th style="width:81px;">Expected Collection Date </th>
                                <th>Invoicing Currency</th>
                                <th>Amount in IC</th>
                                <th>Balance in IC</th>
                                <th>Balance in INR</th>
                                <th>Days of Due</th>
                                <th>Ageing of Overdue Debtors</th>
                                <th>Accounting Unit</th>

                            </c:if>
                            <c:if test="${access == 'BDM'}">
                                <th style="line-height: 1.5em; height: 3em;">Customer Name</th>
                                <th>Project ID</th>
                                <th style="line-height: 1.5em; height: 3em;">Project Name</th>
                                <th>Customer Contact Name</th>
                                <th style="word-break: keep-all;width:20px;">Customer Email ID</th>
                                <th>Customer Phone No</th>
                                <th>Invoice Number</th>
                                <th>Invoice Date</th>
                                <th>Credit Period</th>
                                <th>Due Date</th>
                                <th>Date of Submission</th>
                                <th style="width:81px;">Expected Collection Date </th>
                                <th>Invoicing Currency</th>
                                <th>Amount in IC</th>
                                <th>Balance in IC</th>
                                <th>Balance in INR</th>
                                <th>Days of Due</th>
                                <th>Ageing of Overdue Debtors</th>
                                <th width="50px">Business Leader</th>
                                <th>Accounting Unit</th>
                            </c:if>
                            <c:if test="${access == 'FULL'}">
                                <th>Customer Name</th>
                                <th>Business Leader</th>
                                <th>Customer Owner</th>
                                <th>Project ID</th>
                                <th>Project Name</th>
                                <th>Customer Contact Name</th>
                                <th style="word-break: keep-all;width:20px;">Customer Email ID</th>
                                <th>Customer Phone No</th>
                                <th>Invoice Number</th>
                                <th>Invoice Date</th>
                                <th>Credit Period</th>
                                <th>Due Date</th>
                                <th>Date of Submission</th>
                                <th style="width:81px;">Expected Collection Date </th>
                                <th>Invoicing Currency</th>
                                <th>Amount in IC</th>
                                <th>Balance in IC</th>
                                <th>Balance in INR</th>
                                <th>Days of Due</th>
                                <th>Ageing of Overdue Debtors</th>
                                <th>Accounting Unit</th>
                            </c:if>
                        </tr>

                    </thead>

                    <c:if test="${fn:length(custagingReport)!=0}">
                        <tbody>

                            <c:forEach items="${custagingReport}" var="item" varStatus="index">
                                <tr>
                            <input type="hidden" value="${item.date_of_report}" id="dateOfReport_${index.count}"  name="date_of_report" disabled />
                            <input type="hidden" value="${item.invoice_number}" id="invoiceNumber_${index.count}" name="invoice_number" disabled/>
                            <c:if test="${access != 'BUSINESS_LEADER'}">
                                <td>
                                    <input type="checkbox" class="checkList" value="${item.invoice_number}" name="custagingCheckList" id="checkList_${index.count}" /></td>
                                </c:if>
                                <c:if test="${access == 'BUSINESS_LEADER'}">    
                                <td style="text-transform: capitalize;" >${fn:toLowerCase(item.cust_name)}</td>
                                <td>${item.bdm}</td>
                                <td>${item.project_code}</td>
                                <td style="word-break: break-all;width:20px;hyphens: auto;">${item.project_name}</td>
                                <td>${item.client_manager}</td>
                                <td style="word-break: keep-all;width:20px;">${item.customer_email_id}</td>
                                <td>${item.customer_contact_number}</td>
                                <td>${item.invoice_number}</td>
                                <td>${item.invoice_date}</td>
                                <td>${item.credit_period}</td>
                                <td>${item.credit_due_date}</td>
                                <td>${item.invoice_date_submission_customer}</td>
                                <td>${item.expected_collection_date}</td>
                                <td>${item.invoicing_currency}</td>
                                <td>
                                    <span id="amount_in_ic_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.amount_in_ic != 0}">
                                            <script>convertToRupee("${item.amount_in_ic}",amount_in_ic_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <span id="balance_in_IC_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.balance_in_IC != 0}">
                                            <script>convertToRupee("${item.balance_in_IC}",balance_in_IC_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <span id="balance_in_INR_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.balance_in_INR != 0}">
                                            <script>convertToRupee("${item.balance_in_INR}",balance_in_INR_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${item.days_past_due}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.days_past_due == 0}">Not Due</c:when>
                                        <c:when test="${item.days_past_due > 0 &&  item.days_past_due < 31}">0 - 30 Days</c:when>
                                        <c:when test="${item.days_past_due > 30 &&  item.days_past_due < 61}">31 - 60 Days</c:when>
                                        <c:when test="${item.days_past_due > 60 &&  item.days_past_due < 91}">61 - 90 Days</c:when>
                                        <c:when test="${item.days_past_due > 90 &&  item.days_past_due < 181}">91 - 180 Days</c:when>
                                        <c:when test="${item.days_past_due > 180}">More than 180 Days</c:when>
                                        <c:otherwise>Advance</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${item.legal_entity}</td>
                            </c:if>
                            <c:if test="${access == 'BDM'}">
                                <td nowrap style="text-transform: capitalize;" style="line-height: 1.5em; height: 3em;" >${fn:toLowerCase(item.cust_name)}</td>
                                <td>${item.project_code}</td>
                                <td style="line-height: 1.5em; height: 3em;">${item.project_name}</td>
                                <td>${item.client_manager}</td>
                                <td style="word-break: keep-all;width:20px;">${item.customer_email_id}</td>
                                <td>${item.customer_contact_number}</td>
                                <td>${item.invoice_number}</td>
                                <td>${item.invoice_date}</td>
                                <td>${item.credit_period}</td>
                                <td>${item.credit_due_date}</td>
                                <c:choose>
                                    <c:when test="${maxDateOfReport == systemDate}">
                                        <td><input type="text" disabled  readonly value="${item.invoice_date_submission_customer}" name="invoiceDateSubmitCustomer" id="invoiceDateSubmitCustomer_${index.count}" class="datePicker_startdate" />
                                            <input type="hidden" disabled   value="${item.invoice_date_submission_customer}"  id="hidden-invoiceDateSubmitCustomer_${index.count}"/></td><!--11-->
                                        <td><input type="text" disabled   readonly value="${item.expected_collection_date}" name="expectedCollectionDate" id="expectedCollectionDate_${index.count}" class="datePicker_enddate" />
                                            <input type="hidden" disabled   value="${item.expected_collection_date}" id="hidden-expectedCollectionDate_${index.count}" /></td><!--12-->
                                        </c:when>
                                        <c:otherwise>
                                        <td>${item.invoice_date_submission_customer}</td><!--11-->
                                        <td>${item.expected_collection_date}</td><!--12-->
                                    </c:otherwise>
                                </c:choose>	
                                <td>${item.invoicing_currency}</td>
                                <td>
                                    <span id="amount_in_ic_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.amount_in_ic != 0}">
                                            <script>convertToRupee("${item.amount_in_ic}",amount_in_ic_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <span id="balance_in_IC_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.balance_in_IC != 0}">
                                            <script>convertToRupee("${item.balance_in_IC}",balance_in_IC_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <span id="balance_in_INR_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.balance_in_INR != 0}">
                                            <script>convertToRupee("${item.balance_in_INR}",balance_in_INR_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${item.days_past_due}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.days_past_due == 0}">Not Due</c:when>
                                        <c:when test="${item.days_past_due > 0 &&  item.days_past_due < 31}">0 - 30 Days</c:when>
                                        <c:when test="${item.days_past_due > 30 &&  item.days_past_due < 61}">31 - 60 Days</c:when>
                                        <c:when test="${item.days_past_due > 60 &&  item.days_past_due < 91}">61 - 90 Days</c:when>
                                        <c:when test="${item.days_past_due > 90 &&  item.days_past_due < 181}">91 - 180 Days</c:when>
                                        <c:when test="${item.days_past_due > 180}">More than 180 Days</c:when>
                                        <c:otherwise>Advance</c:otherwise>
                                    </c:choose>
                                </td>
                                <td width="50px">${item.business_leader}</td>
                                <td>${item.legal_entity}</td>
                            </c:if>
                            <c:if test="${access == 'FULL'}">    
                                <td style="text-transform: capitalize;" >${fn:toLowerCase(item.cust_name)}</td>
                                <td>${item.business_leader}</td>
                                <td>${item.bdm}</td>
                                <td>${item.project_code}</td>
                                <td>${item.project_name}</td>
                                <td>${item.client_manager}</td>
                                <td style="word-break: keep-all;width:20px;">${item.customer_email_id}</td>
                                <td>${item.customer_contact_number}</td>
                                <td>${item.invoice_number}</td>
                                <td>${item.invoice_date}</td>
                                <td>${item.credit_period}</td>
                                <td>${item.credit_due_date}</td>
                                <c:choose>
                                    <c:when test="${maxDateOfReport == systemDate}">
                                        <td><input type="text" disabled  readonly value="${item.invoice_date_submission_customer}" name="invoiceDateSubmitCustomer" id="invoiceDateSubmitCustomer_${index.count}" class="datePicker_startdate" />
                                            <input type="hidden" disabled  value="${item.invoice_date_submission_customer}"  id="hidden-invoiceDateSubmitCustomer_${index.count}"/></td><!--11-->
                                        <td><input type="text" disabled  readonly value="${item.expected_collection_date}" name="expectedCollectionDate" id="expectedCollectionDate_${index.count}" class="datePicker_enddate" />
                                            <input type="hidden" disabled   value="${item.expected_collection_date}" id="hidden-expectedCollectionDate_${index.count}" /></td><!--12-->
                                        </c:when>
                                        <c:otherwise>
                                        <td>${item.invoice_date_submission_customer}</td><!--11-->
                                        <td>${item.expected_collection_date}</td><!--12-->
                                    </c:otherwise>
                                </c:choose>	
                                <td>${item.invoicing_currency}</td>
                                <td>
                                    <span id="amount_in_ic_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.amount_in_ic != 0}">
                                            <script>convertToRupee("${item.amount_in_ic}",amount_in_ic_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <span id="balance_in_IC_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.balance_in_IC != 0}">
                                            <script>convertToRupee("${item.balance_in_IC}",balance_in_IC_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>
                                    <span id="balance_in_INR_${index.count}"></span>
                                    <c:choose>
                                        <c:when test="${item.balance_in_INR != 0}">
                                            <script>convertToRupee("${item.balance_in_INR}",balance_in_INR_${index.count})</script>
                                        </c:when>
                                    </c:choose>
                                </td>
                                <td>${item.days_past_due}</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${item.days_past_due == 0}">Not Due</c:when>
                                        <c:when test="${item.days_past_due > 0 &&  item.days_past_due < 31}">0 - 30 Days</c:when>
                                        <c:when test="${item.days_past_due > 30 &&  item.days_past_due < 61}">31 - 60 Days</c:when>
                                        <c:when test="${item.days_past_due > 60 &&  item.days_past_due < 91}">61 - 90 Days</c:when>
                                        <c:when test="${item.days_past_due > 90 &&  item.days_past_due < 181}">91 - 180 Days</c:when>
                                        <c:when test="${item.days_past_due > 180}">More than 180 Days</c:when>
                                        <c:otherwise>Advance</c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${item.legal_entity}</td>

                            </c:if>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </c:if>
                </table>
                <div>
                    <c:if test="${access != 'BUSINESS_LEADER' && fn:length(custagingReport) > 0 }">
                        <input type="button" value="Submit" id="submitButton" class="submitButton" />
                    </c:if>

                </div> 
            </form>           
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
</html>
