<%-- 
    Document   : editProcurement
    Created on : 4 Dec, 2017, 2:48:09 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Purchase Request</title>
    </head>
    <style>
        #loadingDiv img
        {
            border: none; 
        }

        #loadingDiv
        {
            opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1;
        }
    </style>
    <script type="text/javascript">
        
        $(document).ready(function() {
            $("#deliveryDate").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd-mm-yy',
                minDate : 0
            });
            
            $("#buttonSave").click(function (e) {
                $("#status").val('s');
                validateSubmit();
//                $("#saveDetails").submit();
//                startLoading();
            });
            $("#buttonSubmit").click(function (e) {
                $("#status").val('m');
                validateSubmit();
//                $("#saveDetails").submit();
//                startLoading();
            });
        });
        
        function validateSubmit(){
            var error = 0;
            var proc_type = document.getElementById("procurementType").value.trim();
            var bill_type = document.getElementById("BillableType").value;
            var serv_type = document.getElementById("ServiceType").value;
            var order_type = document.getElementById("OrderType").value;
            var rec_contact_no = document.getElementById("recipientContactNumber").value.trim();
            var delivery_date = document.getElementById("deliveryDate").value;
            var location = document.getElementById("deliveryLocation").value.trim();
            var address = document.getElementById("deliveryAddress").value.trim();
//            var remarks = document.getElementById("remarksField").value.trim();
            var crry = document.getElementById("currency").value.trim();
//            var mailCheck = tableEmailCheck("recipient_mail");
            var itemdes = tableNullCheck("iteam_desc");
            var itemqty = tableNullCheck("iteam_qnty");
            var itemamt = tableNullCheck("checkval");
//            var attachtype = tableNullCheck("doctype");
//            var attachdesc = tableNullCheck("docdesc");
//            var attachfile = tableNullCheck("docfile");
            
//            if((attachtype=="1" && attachdesc=="1" &&attachfile=="1")){
//                $(".doctype").css({"outline": "none"});
//                $(".docdesc").css({"outline": "none"});
//                $(".docfile").css({"outline": "none"});
//            }else if(attachtype=="0" && attachdesc=="0" &&attachfile=="0"){
//                
//            }else{
//                $("#attachmentTypeError").css({"display": "block"});
//                $("#attachmentTypeError").css({"padding": "0px 0px 10px 20px"});
//                error++;
//            }
            
//            if(remarks=="" || remarks==null){
//                $("#remarksField").css({"outline": "1px solid red"});
//                $("#remarksError").css({"display": "block"});
//                error++;
//            }else{
//                $("#remarksField").css({"outline": "none"});
//                $("#remarksError").css({"display": "none"});
//            }
            if(crry=="" || crry==null){
                $("#currency").css({"outline": "1px solid red"});
                $("#currencyError").css({"display": "block"});
                error++;
            }else{
                $("#currency").css({"outline": "none"});
                $("#currencyError").css({"display": "none"});
            }
            if(proc_type=="" || proc_type==null){
                $("#procurementType").css({"outline": "1px solid red"});
                $("#procurementTypeError").css({"display": "block"});
                error++;
            }else{
                $("#procurementType").css({"outline": "none"});
                $("#procurementTypeError").css({"display": "none"});
            }
            
            if(bill_type=="" || bill_type==null){
                $("#BillableType").css({"outline": "1px solid red"});
                $("#billableTypeError").css({"display": "block"});
                error++;
            }else{
                $("#BillableType").css({"outline": "none"});
                $("#billableTypeError").css({"display": "none"});
            }
            
            if(serv_type=="" || serv_type==null){
                $("#ServiceType").css({"outline": "1px solid red"});
                $("#serviceTypeError").css({"display": "block"});
                error++;
            }else{
                $("#ServiceType").css({"outline": "none"});
                $("#serviceTypeError").css({"display": "none"});
            }
            
            if(order_type=="" || order_type==null){
                $("#OrderType").css({"outline": "1px solid red"});
                $("#orderTypeError").css({"display": "block"});
                error++;
            }else{
                $("#OrderType").css({"outline": "none"});
                $("#orderTypeError").css({"display": "none"});
            }
            
            if(rec_contact_no=="" || rec_contact_no==null){
                $("#recipientContactNumber").css({"outline": "1px solid red"});
                $("#recipientNumberError").css({"display": "block"});
                error++;
            }else{
                $("#recipientContactNumber").css({"outline": "none"});
                $("#recipientNumberError").css({"display": "none"});
            }
            
            if(delivery_date=="" || delivery_date==null){
                $("#deliveryDate").css({"outline": "1px solid red"});
                $("#deliveryDateError").css({"display": "block"});
                error++;
            }else{
                $("#deliveryDate").css({"outline": "none"});
                $("#deliveryDateError").css({"display": "none"});
            }
            
            if(location=="" || location==null){
                $("#deliveryLocation").css({"outline": "1px solid red"});
                $("#deliveryLocationError").css({"display": "block"});
                error++;
            }else{
                $("#deliveryLocation").css({"outline": "none"});
                $("#deliveryLocationError").css({"display": "none"});
            }
            if(address=="" || address==null){
                $("#deliveryAddress").css({"outline": "1px solid red"});
                $("#deliveryAddressError").css({"display": "block"});
                error++;
            }else{
                $("#deliveryAddress").css({"outline": "none"});
                $("#deliveryAddressError").css({"display": "none"});
            }
//            if(mailCheck==0){
//                $("#recipientEmailId").css({"outline": "none"});
//                $("#recipientEmailTypeError").css({"display": "none"});
//            }else{
//                $("#recipientEmailId").css({"outline": "1px solid red"});
//                $("#recipientEmailTypeError").css({"display": "block"});
//                error++;
//            }
            if(itemdes==0){
                $("#iteamDetailsError").css({"display": "none"});
            }else{
                $("#iteamDetailsError").css({"padding": "0px 0px 10px 20px"});
                $("#iteamDetailsError").css({"display": "block"});
                error++;
            }
            if(itemqty==0){
                $("#iteamDetailsError").css({"display": "none"});
            }else{
                $("#iteamDetailsError").css({"padding": "0px 0px 10px 20px"});
                $("#iteamDetailsError").css({"display": "block"});
                error++;
            }
            if(itemamt==0){
                $("#iteamDetailsError").css({"display": "none"});
            }else{
                $("#iteamDetailsError").css({"padding": "0px 0px 10px 20px"});
                $("#iteamDetailsError").css({"display": "block"});
                error++;
            }
            if(error==0){
                $("#status").val('m');
                $("#buttonCancel").css({"display": "none"});
                $("#buttonSave").css({"display": "none"});
                $("#buttonSubmit").css({"display": "none"});
                $("#saveDetails").submit();
                startLoading();
            }else{
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
                    $(".errorText").css({"display": "none"});
                } else {
                    document.querySelectorAll("." + cl_name)[i].style.outline = "1px solid red";
                    $(".errorText").show();
                    $(".errorText").css({"display": "block"});
                    EmailError++;
                }
            }
            return EmailError;
        }
        
        
        function tableNullCheck(class_name) {
            var NullError = 0;
            var class_length = document.querySelectorAll("." + class_name).length;
            for (var i = 0; i < class_length; i++) {
                var class_name_val = document.querySelectorAll("." + class_name)[i].value;
                var class_name_val_length = $.trim(class_name_val).length;
                var deleted = document.getElementById("deletedTR_"+[i+1]).value;
                if (class_name_val_length > 0) {
                    document.querySelectorAll("." + class_name)[i].style.outline = "none";
                } else {
                    if(deleted==0){
                        document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                        NullError++;   
                    }
                }
            }
            return NullError;
        }
        
        function addRowItems(currentRow) {
            var rowId = parseInt($('#itemsCount').val()) + 1;
            $('#itemsCount').val(rowId);
            var tr = '';
            tr+='<tr id="TR_'+rowId+'" class="rowCount">';
            tr+='<input type="hidden" name="iteams_ids" id="iteam_id_'+rowId+'" value="0" />';
            tr+='<input type="hidden" name="iteams_deleted" id="deletedTR_'+rowId+'" value="0" />';
            tr+='<td><textarea rows="4" cols="50" id="itemDescription_'+rowId+'" class="iteam_desc" name="iteams_description" style="width:547px; min-width: 547px;max-width: 547px;" onpaste="return false"></textarea></td>';
            tr+='<td style="width:57px;"><input type="text" id="quantityCount_'+rowId+'" class="iteam_qnty" name="iteams_quantity" style="width:50px;" onpaste="return false" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);"/></td>';
            tr+='<td style="width:107px;"><input type="text" class="checkval" id="quantityAmount_'+rowId+'" name="iteams_amount" style="width:100px;" onpaste="return false" onChange="calculateTotal()" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);"/></td>';
            tr+='<td style="text-align:center;"><img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRowItems('+rowId+');" />';
            tr+='<img class="addRemove" alt="Remove" src="${pageContext.request.contextPath}/images/tm_delete.png" onclick="deleteIteamRow(' + rowId + ',0)" /></td>';
            tr+='</tr>';
            tr = tr.replace(/selected="selected"/gi, "");
            tr = tr.replace(/selected/gi, "");
            $('#TR_' + currentRow).after(tr);
        }
        
        function deleteIteamRow(row, val) {
            var rowId = parseInt($('#itemsCount').val()) - 1;
            $('#itemsCount').val(rowId);
            $('#quantityAmount_' + row).val("");
            $('#deletedTR_'+row).val("1");
            $('#TR_' + row).hide();
            calculateTotal();
            return true;
        }
        
        function calculateTotal() {
            var totalt=0;
            $(".checkval").each(function() {
                var chkId = this.id;
                chkIdSpt = chkId.split('_');
                counterVar = chkIdSpt[1];
                tsHrs = $("#quantityAmount_"+counterVar).val();
                if(tsHrs != "") {
                    totalt += parseFloat(tsHrs);
                }
             });

            $("#total").val(parseFloat(totalt));
        }
        
        function setAddress(val){
            var address = $("#deliveryLocation").val();
            if(address != "0"){
                var option = $('option:selected', val).attr('addressid');
                $("#deliveryAddress").val(option);
                $("#deliveryAddress").attr("readonly", true);
            }else{
                $("#deliveryAddress").val("");
                $("#deliveryAddress").attr("readonly", false);
            }
        }
        
        function addRowAttachment(currentRow) {
            var rowId = parseInt($('#attachmentCount').val()) + 1;
            $('#attachmentCount').val(rowId);
            var tr = '';
            tr+='<tr id="TA_'+rowId+'">';
            tr+='<input type="hidden" name="attachments_id" id="attachment_id_'+rowId+'" value="0" />';
            tr+='<input type="hidden" name="attachments_deleted" id="deletedTA_'+rowId+'" value="0" />';
            tr+='<td>';
            tr+='<select id="DocumentType_'+rowId+'" name="attachments_type">';
            tr+='<option value="">-- Select Document Type --</option>';
            tr+='<c:forEach items="${DocumentType}" var="DocumentType" varStatus="i">';
            tr+='<option value="${DocumentType.document_id}">${DocumentType.document_type}</option>';
            tr+='</c:forEach>';
            tr+='</select>';
            tr+='</td>';
            tr+='<td><textarea rows="4" cols="50" Style="width:230px;" id="attachmentDescription_'+rowId+'" name="attachments_description" onpaste="return false"></textarea></td>';
            tr+='<td><input type="file" id="fileAttachment_'+rowId+'" name="attachmentValue'+rowId+'" multiple="true"></td>';
            tr+='<td style="text-align:center;"><img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRowAttachment(1);" />';
            tr+='<img class="addRemove" alt="Remove" src="${pageContext.request.contextPath}/images/tm_delete.png" onclick="deleteRowAttachment(' + rowId + ',0)" /></td>';
            tr+='</tr>';
            tr = tr.replace(/selected="selected"/gi, "");
            tr = tr.replace(/selected/gi, "");
            $('#TA_' + currentRow).after(tr);
        }
        
        function deleteRowAttachment(row, val) {
            var rowId = parseInt($('#attachmentCount').val()) - 1;
            $('#attachmentCount').val(rowId);
            $('#deletedTA_'+row).val("1");
            $('#TA_' + row).hide();
            return true;
        }
    </script>
    
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; ">
            <div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center">
                <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
            </div>
        </div>
        <div class="container_inner"> 
            <div class="page_heading">Edit Purchase Request</div>
            <div class="listLink">
                <img src="/ideal_attendance/images/icon_list.png" title="List Procurement" alt="List Procurement">
                <a style="text-decoration:none;color: #4C83B3;" target="_self" href="procurementList.htm">Purchase Request List</a>
            </div>
        </div>
        <form action="updateProcurement.htm" name="saveDetails" id="saveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
            <div id="content">
                <input type="hidden" id="pp_id" name="id" value="${result.get(0).id}"/>
                <input type="hidden" id="pp_code" name="pp_code" value="${result.get(0).pp_code}"/>
                <input type="hidden" id="status" name="status" value="${result.get(0).status}"/>
                <fieldset id ="procurementDetails">
                    <legend>Purchase Request Details</legend>
                    <table id="procurement_table">
                        <tr>
                            <td>
                                <label>Requestor Name</label><font color="red">*</font>
                            </td>
                            <td>
                                <input type="hidden" id="requestorId" name="requestorId" value="${employeeDetails.get(0).employee_id}"/>
                                <input type="text" id="requestorName" name="requestor_name" value="${employeeDetails.get(0).employee_name}" readonly/>
                            </td>
                            <td>
                                <label>Request Date</label><font color="red">*</font>
                            </td>
                            <td>
                                <c:if test="${result.get(0).status=='r' || result.get(0).status=='j'}">
                                    <input type="text" id="requestorDate" name="requested_date" value="${result.get(0).requested_date}" readonly/>
                                </c:if>
                                <c:if test="${result.get(0).status=='s' || result.get(0).status==null}">
                                    <input type="text" id="requestorDate" name="requested_date" value="${employeeDetails.get(0).request_date}" readonly/>
                                </c:if>
                                
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Unit</label><font color="red">*</font>
                            </td>
                            <td>
                                <input type="hidden" id="unitId" name="sbuId" value="${employeeDetails.get(0).sbu_id}"/>
                                <input type="text" id="unitName" name="sbu_name" value="${employeeDetails.get(0).sbu_name}" disabled="disabled"/>
                            </td>
                            <td>
                                <label>Sub Unit</label><font color="red">*</font>
                            </td>
                            <td>
                                <input type="hidden" id="subUnitId" name="subSbuId" value="${employeeDetails.get(0).sub_sbu_id}"/>
                                <input type="text" id="subUnitName" name="sub_sbu_name" value="${employeeDetails.get(0).sub_sbu_name}" disabled="disabled"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Purchase Type</label><font color="red">*</font>
                            </td>
                            <td>
                                <select id="procurementType" name="procurement_type_id">
                                    <option value="">-- Select Type --</option>
                                    <c:forEach items="${procurementType}" var="procurementType" varStatus="i">
                                        <option value="${procurementType.procurement_type_id}" ${(procurementType.procurement_type_id==result.get(0).procurement_type_id) ? 'selected':''}>${procurementType.procurement_type_name}</option>
                                    </c:forEach>
                                </select>
                                <p id="procurementTypeError" class="errorMessage">Please select Procurement Type</p>
                            </td>
                            <td>
                                <label>Billable Type</label><font color="red">*</font>
                            </td>
                            <td>
                                <select id="BillableType" name="billable_id">
                                    <option value="">-- Select Type --</option>
                                    <c:forEach items="${BillableType}" var="BillableType" varStatus="i">
                                        <option value="${BillableType.billable_id}" ${(BillableType.billable_id==result.get(0).billable_id) ? 'selected':''}>${BillableType.billable_name}</option>
                                    </c:forEach>
                                </select>
                                <p id="billableTypeError" class="errorMessage">Please select Billable Type</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Service Type</label><font color="red">*</font>
                            </td>
                            <td>
                                <select id="ServiceType" name="service_id">
                                    <option value="">-- Select Type --</option>
                                    <c:forEach items="${ServiceType}" var="ServiceType" varStatus="i">
                                        <option value="${ServiceType.service_id}" ${(ServiceType.service_id==result.get(0).service_id) ? 'selected':''}>${ServiceType.service_type}</option>
                                    </c:forEach>
                                </select>
                                <p id="serviceTypeError" class="errorMessage">Please select Service Type</p>
                            </td>
                            <td>
                                <label>Order Type</label><font color="red">*</font>
                            </td>
                            <td>
                                <select id="OrderType" name="order_id">
                                    <option value="">-- Select Type --</option>
                                    <c:forEach items="${OrderType}" var="OrderType" varStatus="i">
                                        <option value="${OrderType.order_id}" ${(OrderType.order_id==result.get(0).order_id) ? 'selected':''}>${OrderType.order_name}</option>
                                    </c:forEach>
                                </select>
                                <p id="orderTypeError" class="errorMessage">Please select Order Type</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Contact Number</label><font color="red">*</font>
                            </td>
                            <td>
                                <c:if test="${result.get(0).status==null}">
                                    <input type="text" id="recipientContactNumber" name="recipient_contact_number" onkeypress="return blockNonNumbers(this, event, true, false);"/>
                                </c:if>
                                <c:if test="${result.get(0).status!=null}">
                                    <input type="text" id="recipientContactNumber" name="recipient_contact_number" onkeypress="return blockNonNumbers(this, event, true, false);" value="${result.get(0).recipient_contact_number}"/>
                                </c:if>
                                <p id="recipientNumberError" class="errorMessage">Recipient Contact Number can't be empty</p>
                            </td>
                            <td>
                                <label>Expected Delivery Date</label><font color="red">*</font>
                            </td>
                            <td>
                                <c:if test="${result.get(0).status==null}">
                                    <input class="calender-field" type="text" name="expected_delivery_date" id="deliveryDate" readonly/>
                                </c:if>
                                <c:if test="${result.get(0).status!=null}">
                                    <input class="calender-field" type="text" name="expected_delivery_date" id="deliveryDate" value="${result.get(0).expected_delivery_date}" readonly/>
                                </c:if>
                                <p id="deliveryDateError" class="errorMessage">Please select the Expected Delivery Date</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Delivery Location</label><font color="red">*</font>
                            </td>
                            <td>
                                <select id="deliveryLocation" name="delivery_location_name" onchange="setAddress(this);">
                                    <option value="">-- Select Location --</option>
                                    <c:forEach items="${DeliveryLocation}" var="DeliveryLocation" varStatus="i">
                                        <option value="${DeliveryLocation.location_id}" ${(DeliveryLocation.location_id==result.get(0).delivery_location_id) ? 'selected':''} addressId="${DeliveryLocation.delivery_address}">${DeliveryLocation.location_name}</option>
                                    </c:forEach>
                                    <option value="0" ${result.get(0).delivery_location_id==0 ? 'selected':''}>Others</option>
                                </select>
                                <p id="deliveryLocationError" class="errorMessage">Delivery Location can't be empty</p>
                            </td>
                            <td>
                                <label>Delivery Address</label><font color="red">*</font>
                            </td>
                            <td>
                                <c:if test="${result.get(0).status==null}">
                                    <textarea type="text" id="deliveryAddress" name="delivery_address" style="min-width:225px;max-width: 225px;height:40px;max-height:100px;" onpaste="return false"></textarea>
                                </c:if>
                                <c:if test="${result.get(0).status!=null}">
                                    <textarea type="text" id="deliveryAddress" name="delivery_address" style="min-width:225px;max-width: 225px;height:40px;max-height:100px;" onpaste="return false">${result.get(0).delivery_address}</textarea>
                                </c:if>
                                <p id="deliveryAddressError" class="errorMessage">Delivery Address can't be empty</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Remarks</label>
                            </td>
                            <td>
                                <textarea type="text" id="remarksField" name="remarks" style="min-width:225px;max-width: 225px;max-height:100px;">${result.get(0).remarks}</textarea>
                                <p id="remarksError" class="errorMessage">Remarks field can't be empty</p>
                            </td>
                            <td>
                                <label>Currency</label><font color="red">*</font>
                            </td>
                            <td>
                                <select id="currency" name="currency_id">
                                    <option value="">-- Select Type --</option>
                                    <c:forEach items="${currency}" var="currency" varStatus="i">
                                        <option value="${currency.currency_id}" ${(currency.currency_id==result.get(0).currency_id) ? 'selected':''}>${currency.currency_name}</option>
                                    </c:forEach>
                                </select>
                                <p id="currencyError" class="errorMessage">Please select Currency</p>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset id ="orderingDetails">
                    <legend>Ordering / Items Details</legend>
                    <c:choose>
                        <c:when test="${!empty(IteamDetails)}">
                            <input type="hidden" value="${fn:length(IteamDetails)}" id="itemsCount"/>
                        </c:when>
                        <c:otherwise>
                            <input type="hidden" value="1" id="itemsCount"/>
                        </c:otherwise>
                    </c:choose>
                    <p id="iteamDetailsError" class="errorMessage">Please fill the Items details</p>
                    <table class="tableStructure">
                        <tbody>
                            <tr>
                                <th>Description<font color="red">*</font></th>
                                <th>Quantity<font color="red">*</font></th>
                                <th>Envisaged Amount<font color="red">*</font></th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${IteamDetails}" var="data" varStatus="dataStatus">
                                <tr id="TR_${dataStatus.count}" class="rowCount">
                                    <input type="hidden" name="iteams_ids" id="iteam_id_${dataStatus.count}" value="${data.iteam_id}" />
                                    <input type="hidden" name="iteams_deleted" id="deletedTR_${dataStatus.count}" value="0" />
                                    <td><textarea rows="4" cols="50" id="itemDescription_${dataStatus.count}" class="iteam_desc" name="iteams_description" style="width:547px; min-width: 547px;max-width: 547px;">${data.iteam_description}</textarea></td>
                                    <td style="width:57px;"><input type="text" id="quantityCount_${dataStatus.count}" class="iteam_qnty" name="iteams_quantity" style="width:50px;" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" value="${data.iteam_quantity}"/></td>
                                    <td style="width:107px;"><input type="text" class="checkval" id="quantityAmount_${dataStatus.count}" name="iteams_amount" style="width:100px;" onChange="calculateTotal()" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" value="${data.iteam_amount}"/></td>
                                    <td style="text-align:center;">
                                        <img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRowItems(1);" />
                                        <c:if test="${dataStatus.count !=1}">
                                            <img class="addRemove" alt="Remove" src="${pageContext.request.contextPath}/images/tm_delete.png" onclick="deleteIteamRow(${dataStatus.count},1)" />
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty(IteamDetails)}">
                                <tr id="TR_1" class="rowCount">
                                    <input type="hidden" name="iteams_ids" id="iteam_id_1" value="0" />
                                    <input type="hidden" name="iteams_deleted" id="deletedTR_1" value="0" />
                                    <td><textarea rows="4" cols="50" id="itemDescription_1" class="iteam_desc" name="iteams_description" style="width:547px; min-width: 547px;max-width: 547px;" onpaste="return false"></textarea></td>
                                    <td style="width:57px;"><input type="text" id="quantityCount_1" class="iteam_qnty" name="iteams_quantity" style="width:50px;" onpaste="return false" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);"/></td>
                                    <td style="width:107px;"><input type="text" class="checkval" id="quantityAmount_1" name="iteams_amount" style="width:100px;" onpaste="return false" onChange="calculateTotal()" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);"/></td>
                                    <td style="text-align:center;"><img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRowItems(1);" /></td>
                                </tr>
                            </c:if>
                            <tr>
                                <th colspan="2" style="text-align: right">Total :</th>
                                <th style="margin: 0px; padding: 0px;"><input type="text" id="total" name="total" readonly style="width:100px;" value="${result.get(0).total}"/></th>
                                <th></th>
                            </tr>
                        </tbody>
                    </table>
                </fieldset>
                <fieldset id ="attachmentsDetails">
                    <legend>Attachments</legend>
                    <input type="hidden" value="1" id="attachmentCount"/>
                    <p id="attachmentTypeError" class="errorMessage">Please fill all the fields</p>
                    <table class="tableStructure">
                        <tbody>
                            <tr>
                                <th>Document Type</th>
                                <th>Description</th>
                                <th>Attachment</th>
                                <th>Action</th>
                            </tr>
                            <c:forEach items="${AttachmentDetails}" var="attach" varStatus="attachStatus">
                                <tr id="TA_${attachStatus.count}">
                                    <input type="hidden" name="attachments_id" id="attachment_id_${attachStatus.count}" value="${attach.attachment_id}" />
                                    <input type="hidden" name="attachments_deleted" id="deletedTA_${attachStatus.count}" value="0" />
                                    <td>
                                        <select id="DocumentType_${attachStatus.count}" name="attachments_type">
                                        <option value="">-- Select Document Type --</option>
                                        <c:forEach items="${DocumentType}" var="DocumentType" varStatus="i">
                                            <option value="${DocumentType.document_id}" ${(DocumentType.document_id==attach.attachment_type) ? 'selected':''}>${DocumentType.document_type}</option>
                                        </c:forEach>
                                    </select>
                                    </td>
                                    <td><textarea rows="4" cols="50" Style="width:230px; min-width:230px;max-width:230px;" id="attachmentDescription_${attachStatus.count}" name="attachments_description" onpaste="return false">${attach.attachment_description}</textarea></td>
                                    <td>
                                        <input type="file" multiple="true" id="fileAttachment_${attachStatus.count}" name="attachmentValue${attachStatus.count}">
                                        <c:if test="${attach.attachment_name == null}">
                                            No file Found                  
                                        </c:if>
                                        <c:if test="${attach.attachment_name != null}">
                                            <a href="attachmentDownload.htm?fileName=${attach.file_name}&fileType=${attach.file_type}" target="_blank" name="file" id="attachedFileValue">${attach.attachment_name}</a>
                                        </c:if>
                                    </td>
                                    <td style="text-align:center;">
                                        <img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRowAttachment(1);" />
                                        <c:if test="${attachStatus.count !=1}">
                                            <img class="addRemove" alt="Remove" src="${pageContext.request.contextPath}/images/tm_delete.png" onclick="deleteRowAttachment(${attachStatus.count},1)" />
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty(AttachmentDetails)}">
                                <tr id="TA_1">
                                    <input type="hidden" name="attachments_id" id="attachment_id_1" value="0" />
                                    <input type="hidden" name="attachments_deleted" id="deletedTA_1" value="0" />
                                    <td>
                                        <select id="DocumentType_1" name="attachments_type">
                                        <option value="">-- Select Document Type --</option>
                                        <c:forEach items="${DocumentType}" var="DocumentType" varStatus="i">
                                            <option value="${DocumentType.document_id}">${DocumentType.document_type}</option>
                                        </c:forEach>
                                    </select>
                                    </td>
                                    <td><textarea rows="4" cols="50" Style="width:230px; min-width:230px;max-width:230px;" id="attachmentDescription_1" name="attachments_description" onpaste="return false"></textarea></td>
                                    <td><input type="file" id="fileAttachment_1" name="attachmentValue1"></td>
                                    <td style="text-align:center;"><img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRowAttachment(1);" /></td>
                                </tr>
                            </c:if>
                            
                        </tbody>
                    </table>
                </fieldset>
                <div class="buttonClass">
                    <input type="button" name="buttonCancel" id="buttonCancel" value="Cancel" class="cancelbutton" onclick="javascript: location.href='procurementList.htm'"/>
                    <c:if test="${result.get(0).status==null}">
                        <input type="button" name="buttonSave" id="buttonSave" value="Save" class="savebutton"/>
                    </c:if>
                    <input type="button" name="buttonSubmit" id="buttonSubmit" value="Submit" class="submitbutton"/>
                </div>
            </div>
            
        </form>
        
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

        stopLoading();

        function stopLoading(){
            if(ns4){loadingDivObj.visibility="hidden";}
            else if (ns6||ie4) loadingDivObj.display="none";
        }

        function startLoading(){
            var loaderStartHeight = $("html").height();
            loadingDivObj.height = loaderStartHeight + "px";
            if (ns4) {
                loadingDivObj.visibility = "visible";
                $("#loadingImage").attr("tabindex", -1).focus();
            } else if (ns6 || ie4)
                loadingDivObj.display = "block";
            $("#loadingImage").attr("tabindex", -1).focus();
        }
    </script>
</html>

