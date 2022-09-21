<%-- 
    Document   : buhApprove
    Created on : 5 Dec, 2017, 9:53:04 AM
    Author     : 16221
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Procurement</title>
    </head>
    <style>
        #procurement_table tr td {
            padding: 5px;
        }
        #loadingDiv img{
            border: none; 
        }

        #loadingDiv{
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
            
            $("#buttonApprove").click(function (e) {
                var status=$("#approveStatus").val();
                $("#status").val(status);
                validateSubmit();
            });
            
            $("#buttonReject").click(function (e) {
                var status=$("#rejectStatus").val();
                $("#status").val(status);
                validateSubmit();
            });
            
            
        });
        
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
        
        function validateSubmit(){
            var error = 0;
            var comments = document.getElementById("rmComments").value.trim();
            if(comments=="" || comments==null){
                $("#rmComments").css({"outline": "1px solid red"});
                $("#rmCommentsError").css({"display": "block"});
                error++;
            }else{
                $("#rmComments").css({"outline": "none"});
                $("#rmCommentsError").css({"display": "none"});
            }
            if(error==0){
                $("#buttonCancel").css({"display": "none"});
                $("#buttonApprove").css({"display": "none"});
                $("#buttonReject").css({"display": "none"});
                $("#approveDetails").submit();
                startLoading();
            }else{
                return false;
            }
        }
        
        
    </script>
    
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; ">
            <div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center">
                <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
            </div>
        </div>
        <div class="container_inner"> 
            <div class="page_heading">View Purchase Request - ${procurementDetails.get(0).pp_code}</div>
            <div class="listLink">
                <img src="/ideal_attendance/images/icon_list.png" title="List Procurement" alt="List Procurement">
                <a style="text-decoration:none;color: #4C83B3;" target="_self" href="procurementBUHList.htm?status=${procurementDetails.get(0).status}">Purchase Approver List</a>
            </div>
        </div>
        <form action="approveBUHProcurement.htm" name="saveDetails" id="approveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
            <div id="content">
                <input type="hidden" id="pp_id" name="id" value="${procurementDetails.get(0).id}"/>
                <input type="hidden" id="pp_code" name="pp_code" value="${procurementDetails.get(0).pp_code}"/>
                <input type="hidden" id="status" name="status" value="${procurementDetails.get(0).status}"/>
                <fieldset id ="procurementDetails">
                    <legend>Purchase Request Details</legend>
                    <table id="procurement_table">
                        <tr>
                            <td>
                                <label>PR Code</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).pp_code}</p>
                            </td>
                            <td>
                                <label>Purchase Request Status</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).status_name}</p>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                                <label>Requestor Name</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).employee_name}</p>
                            </td>
                            <td>
                                <label>Request Date</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).requested_date}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Contact Number</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).recipient_contact_number}</p>
                            </td>
                            <td>
                                <label>Expected Delivery Date</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).expected_delivery_date}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Unit</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).sbu_name}</p>
                            </td>
                            <td>
                                <label>Sub Unit</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).sub_sbu_name}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Purchase Type</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).procurement_type_name}</p>
                            </td>
                            <td>
                                <label>Billable Type</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).billable_name}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Service Type</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).service_type}</p>
                            </td>
                            <td>
                                <label>Order Type</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).order_name}</p>
                            </td>
                        </tr>
                                                
                        <tr>
                            <td>
                                <label>Delivery Location</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).delivery_location_name}</p>
                            </td>
                            <td>
                                <label>Delivery Address</label><font color="red">*</font>
                            </td>
                            <td>
                                <p style="width:200px;word-break: break-all;">${procurementDetails.get(0).delivery_address}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Remarks</label>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).remarks}</p>
                            </td>
                            <td>
                                <label>Currency</label><font color="red">*</font>
                            </td>
                            <td>
                                <p>${procurementDetails.get(0).currency_name}</p>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <fieldset id ="orderingDetails">
                    <legend>Ordering / Items Details</legend>
                    <table class="tableStructure">
                        <tbody>
                            <tr>
                                <th>Description<font color="red">*</font></th>
                                <th>Quantity<font color="red">*</font></th>
                                <th>Envisaged Amount<font color="red">*</font></th>
                            </tr>
                            <c:forEach items="${IteamDetails}" var="data" varStatus="dataStatus">
                                <tr id="TR_${dataStatus.count}" class="rowCount">
                                    <input type="hidden" name="iteams_ids" id="iteam_id_${dataStatus.count}" value="${data.iteam_id}" />
                                    <input type="hidden" name="iteams_deleted" id="deletedTR_${dataStatus.count}" value="0" />
                                    <td><textarea rows="4" cols="50" id="itemDescription_${dataStatus.count}" class="iteam_desc" name="iteams_description" readonly="true" style="width:547px; min-width: 547px;max-width: 547px;">${data.iteam_description}</textarea></td>
                                    <td style="width:57px;"><input type="text" id="quantityCount_${dataStatus.count}" class="iteam_qnty" name="iteams_quantity" style="width:50px;" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" value="${data.iteam_quantity}"/></td>
                                    <td style="width:107px;"><input type="text" class="checkval" id="quantityAmount_${dataStatus.count}" name="iteams_amount" style="width:100px;" onChange="calculateTotal()" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" value="${data.iteam_amount}"/></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <th colspan="2" style="text-align: right">Total :</th>
                                <th style="margin: 0px; padding: 0px;"><input type="text" id="total" name="total" readonly style="width:100px;" value="${procurementDetails.get(0).total}"/></th>
                            </tr>
                        </tbody>
                    </table>
                </fieldset>
                <fieldset id ="attachmentsDetails">
                    <legend>Attachments</legend>
                    <table class="tableStructure">
                        <tbody>
                            <tr>
                                <th>Document Type</th>
                                <th>Description</th>
                                <th>Attachment</th>
                            </tr>
                            <c:forEach items="${AttachmentDetails}" var="attach" varStatus="attachStatus">
                                <tr id="TA_${attachStatus.count}">
                                    <td>
                                        <p>${attach.document_type}</p>
                                    </td>
                                    <td><p>${attach.attachment_description}</p></td>
                                    <td>
                                        <a href="attachmentDownload.htm?fileName=${attach.file_name}&fileType=${attach.file_type}" target="_blank" name="file" id="attachedFileValue">${attach.attachment_name}</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty(AttachmentDetails)}">
                                <tr id="TA_1">
                                    <td colspan="3" style="text-align: Center">No data to display</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </fieldset>
                <c:if test="${result.get(0).status!='s'}">
                    <fieldset>
                        <legend>Approver Details</legend>
                        <table class="tableStructure">
                            <tbody>
                                <tr>
                                    <th></th>
                                    <th>Approver Name</th>
                                    <th>Approved Date</th>
                                    <th>Approver Comments</th>
                                </tr>
                                <tr>
                                    <td><label>Reporting Manager</label></td>
                                    <td>${procurementDetails.get(0).rm_name}</td>
                                    <td>${procurementDetails.get(0).rm_approved_date}</td>
                                    <td>${procurementDetails.get(0).rm_comments}</td>
                                </tr>
                                <tr>
                                    <td><label>BUH</label></td>
                                    <td>${procurementDetails.get(0).buh_name}</td>
                                    <td>${procurementDetails.get(0).buh_approved_date}</td>
                                    <td>${procurementDetails.get(0).buh_comments}</td>
                                </tr>
                            </tbody>
                        </table>
                    </fieldset>
                </c:if>
                <c:if test="${procurementDetails.get(0).status=='a'}">
                    <div class="approverComments">
                        <label>Approver Comments :</label>
                        <input type="text" name ="remarks" id="rmComments"/>
                        <input type="hidden" name="approverType" name="approverType" value="${approverType}"/>
                        <p id="rmCommentsError" class="errorMessage">Please enter comments</p>
                    </div>
                </c:if>
                
                <div class="buttonClass">
                    <input type="hidden" id="approveStatus" value="e"/>
                    <input type="hidden" id="rejectStatus" value="j"/>
                    <input type="button" name="buttonCancel" id="buttonCancel" value="Back" class="cancelbutton" onclick="javascript: location.href='procurementBUHList.htm?status=${procurementDetails.get(0).status}'"/>
                    <c:if test="${procurementDetails.get(0).status=='a'}">
                        <input type="button" name="buttonApprove" id="buttonApprove" value="Approve" class="submitbutton"/>
                        <input type="button" name="buttonReject" id="buttonReject" value="Send Back" class="rejectbutton"/>
                    </c:if>
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
