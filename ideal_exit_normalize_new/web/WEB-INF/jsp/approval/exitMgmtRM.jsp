<%-- 
Document   : exitMgmtRM
Created on : Oct 7, 2011, 2:50:14 PM
Author     : 14583
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <script type="text/javascript">               
            var exitTriggerDate;
            var exitTriggerDateFrom;
            var exitTriggerMonthFrom;
            var exitTriggerYearFrom;
            var lastWorkingDate;
            var lastWorkingDateFrom;
            var lastWorkingMonthFrom;
            var lastWorkingYearFrom;
            var noticePeriodDate;
            var balanceDays;
            var flag;
            function calculateDays() {
                if ($("#exitTriggerDate").val() != '') {
                    exitTriggerDate = ($("#exitTriggerDate").val());
                    exitTriggerDateFrom = exitTriggerDate.split("-")[0];
                    exitTriggerMonthFrom = exitTriggerDate.split("-")[1];
                    exitTriggerYearFrom = exitTriggerDate.split("-")[2];
                    lastWorkingDate = $("#lastWorkingDate").val();
                    lastWorkingDateFrom = lastWorkingDate.split("-", 1);
                    lastWorkingMonthFrom = lastWorkingDate.split("-", 2)[1];
                    lastWorkingYearFrom = lastWorkingDate.split("-", 3)[2];
                }
            }
            $(document).ready(function() {
                $.validator.addMethod("rmcommentsRegex", function(value, element) {
                    return this.optional(element) || /^[a-zA-Z0-9 \&\-\,]+$/i.test(value);
                }, "Comments must contain only AlphaNummeric.");

                $("#formRMApproval").validate({
                    rules: {
                        rmComments: {
                            minlength: 5,
                            rmcommentsRegex: true
                        }
                    },
                    
                    submitHandler: function(form) {
                        var exitApproveRejectedValue = $("#ApproveRejectId").val();
                        if(exitApproveRejectedValue=='Reject'){
                            form.submit();
                            startLoading();
                        }
                        if(exitApproveRejectedValue=='Approve'){
                            if(validateAddForm()){
                                if(dateValidation())
                                {
                                    if(($('#rmDiv').css('display') != 'none') && ($('#rm_justification').val() != '')){

                                        //alert("test");

                                        //$('#rmComments').val($("#rm_justification option:selected").text());  
                                    }
                                    form.submit();
                                    startLoading();
                                }
                            }

                        }
                    }
                });
                
                
                function  validateAddForm(){
                    if($('#approveId').val() == 'Approve')
                        return true;
                    else
                        return false;   
                }
                
                
                
                $('#rejectId').click(function() {
                    $('#dialog').css("visibility", "visible").dialog({
                        modal: true,
                        buttons: {
                            Ok: function() {
                                if ($('#dummyVal').val() == '')
                                {
                                    $('#dummyVal').addClass('error');
                                }
                                else {
                                    $(this).dialog("close");
                                    $('#reasonRejection').val($('#dummyVal').val());
                                    $('#ApproveRejectId').val('Reject');
                                    reject();
                                    startLoading();
                                    document.getElementsByName("buttonName").val('Reject');
                                }
                            },
                            Cancel: function() {
                                $(this).dialog("close");
                                $('#reasonRejection').val($('#dummyVal').val());
                            }
                        }
                    });

                });
                
                $('#approveId').click(function() {
                    $('#ApproveRejectId').val('Approve');
                });
                var dateTest = new Date();
                calculateDays();
               
                $(function() {
                    calculateDays();
                    var exitTriggerDate = ($("#exitTriggerDate").val());
                    var exitTriggerDateFrom = exitTriggerDate.split("-", 1);
                    var exitTriggerMonthFrom = exitTriggerDate.split("-", 2)[1];
                    var exitTriggerYearFrom = exitTriggerDate.split("-", 3)[2];
                    var lastDate = new Date(exitTriggerYearFrom+"-"+exitTriggerMonthFrom+"-"+exitTriggerDateFrom);
//                    lastDate.setDate(exitTriggerDateFrom);
//                    lastDate.setMonth(exitTriggerMonthFrom-1);    
//                    lastDate.setFullYear(exitTriggerYearFrom);
                    lastDate.setDate(lastDate.getDate() + 89);
                    var disabledDays = new Array();
                    <c:forEach items="${cmpHolidays}" var="company" varStatus="status"> 
                        disabledDays.push("${company.cmpHoliday}");
                    </c:forEach>
                        function companyHoliday(date) {
                            //var m = date.getMonth(), d = date.getDate(), y = date.getFullYear(); 
                            //var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
                            //return [ disabledDays.indexOf(string) == -1 ]
                            try{
                                var string = jQuery.datepicker.formatDate('yy-mm-dd', date);
                                var length = disabledDays.length;
                                for(var i = 0; i < length; i++) {
                                    if(disabledDays[i] == string)
                                        return [false];
                                }
                                return [true];
                            }catch(err){
                               // alert(err);
                            }
                            
                        }
                                
                        //                        var displayDates = function(date){
                        //                            var string = jQuery.datepicker.formatDate('dd-mm-yy', date);
                        //                            var length = enabledDays.length;
                        //                            for(var i = 0; i < length; i++) {
                        //                                if(enabledDays[i] == string)
                        //                                    return [true];
                        //                            }
                        //                            return [false];
                        //
                        //                        }
                                
                                
                                
                                
                        function noWeekendsOrHolidays(date) {
                            var noWeekend = jQuery.datepicker.noWeekends(date);
                            return noWeekend[0] ? companyHoliday(date) : noWeekend;
                        }
                                
                        $("#lastWorkingDate").datepicker({
                            changeMonth: false,
                            changeYear: false,
                            showButtonPanel: false,
                            dateFormat: 'dd-mm-yy',
                            yearRange: "-0:+0",
                            minDate : 0,
                            maxDate : lastDate,
                            constrainInput: true,
                            beforeShowDay: noWeekendsOrHolidays,
                            onClose: function() {
                                var no_of_days = calculateDaysServed($("#exitTriggerDate").val(), $(this).val());
                                $("#daysServed").val(no_of_days);
                                calculateBalanceNoticePeriod();
                            }
                        });
                   
                        $("#lastWorkingDate").val('');
                        $("#daysServed").val('');
                        $('#balNoticePeriod').val('');
                                
                    });
                });
                function days_between(date1, date2) {
                    // The number of milliseconds in one day
                            
                    var ONE_DAY = 1000 * 60 * 60 * 24

                    // Convert both dates to milliseconds
                    var date1_ms = date1.getTime()
                    var date2_ms = date2.getTime()

                    // Calculate the difference in milliseconds
                    var difference_ms = Math.abs(date1_ms - date2_ms)

                    // Convert back to days and return

                    return Math.round(difference_ms / ONE_DAY);
                }

                function calculateDaysServed() {
                    var exitTriggerDate = ($("#exitTriggerDate").val());
                    var exitTriggerDateFrom = exitTriggerDate.split("-", 1);
                    var exitTriggerMonthFrom = exitTriggerDate.split("-", 2)[1];
                    var exitTriggerYearFrom = exitTriggerDate.split("-", 3)[2];
                    var lastWorkingDate = $("#lastWorkingDate").val();
                    var lastWorkingDateFrom = lastWorkingDate.split("-", 1);
                    var lastWorkingMonthFrom = lastWorkingDate.split("-", 2)[1];
                    var lastWorkingYearFrom = lastWorkingDate.split("-", 3)[2];
                            
                    var a = days_between(new Date(lastWorkingYearFrom, lastWorkingMonthFrom - 1, lastWorkingDateFrom), new Date(exitTriggerYearFrom, exitTriggerMonthFrom - 1, exitTriggerDateFrom))
                    var d = new Date(lastWorkingDateFrom+'-'+lastWorkingMonthFrom+'-'+lastWorkingYearFrom);
                    var n = d.getDay();
                    if (isNaN(a)) {
                        a = 0;
                    }
                    /* CALCULATING THE DATE DIFFERENCE */
                    else{
                        return a + 1 ;
                    }
                            
                }
                function calculateBalanceNoticePeriod() {
                    var daysServed;
                    if (isNaN($("#daysServed").val()) || $.trim($("#daysServed").val()) == '') {
                        daysServed = 0;
                    } else {
                        daysServed = $("#daysServed").val();
                    }
                    var balance = 90 - daysServed;
                    $('#balNoticePeriod').val(balance);
                    if(balance==0){
                        $('#noticeWaiverValue').hide();
                        $('#noticeWaiverValue1').show();
                        $('#noticeWaiverValue1').val('');
                        $("#rmDiv").hide();
                    }
                    else{
                        $('#noticeWaiverValue').show();
                        $('#noticeWaiverValue1').hide();
                        $('#noticeWaiverValue').val('');
                        $("#rmDiv").hide();
                    }
                }
                function reject()
                {
                    $('#exitTriggerDate').removeAttr("class");
                    $('#lastWorkingDate').removeAttr("class");
                    $('#daysServed').removeAttr("class");
                    $('#balNoticePeriod').removeAttr("class");
                    $('#noticeWaiverValue').removeAttr("class");
                    $('#noticeWaiverValue1').removeAttr("class");
                    $("#rmComments").removeAttr("class");
                    <%--$('#formRMApproval').attr("action", "listRegnSubmittedEmp.htm");--%>
                    $('#formRMApproval').submit();
                }
                function fileDownload(fileName, fileType, referenceName, referenceId, fileId, moduleName) {
                    $("#fileName").val(fileName);
                    $("#fileType").val(fileType);
                    $("#referenceName").val(referenceName);
                    $("#referenceId").val(referenceId);
                    $("#fileId").val(fileId);
                    $("#moduleName").val(moduleName);
                    document.getElementById('fileDownloadForm').action = 'approvalFileDownload.htm';
                    document.getElementById('fileDownloadForm').submit();
                    //$('#fileDownloadForm').submit();
                }
                function rmActions(element) {
                    var selectionValue = element;
                    if (selectionValue == '0' || selectionValue == '1') {
                        $("#rmDiv").show();
                    }
                    else if (selectionValue == '2' || selectionValue == '3' || selectionValue == '') {
                        $("#rmDiv").hide();
                    }

                }
            function dateValidation(){
                var currentDateWithMonth = new Date();
                var lastDayOfThisMonth = new Date(currentDateWithMonth.getFullYear(), currentDateWithMonth.getMonth() + 1, 0);
                var selectedDateWithMonth = $('#lastWorkingDate').datepicker('getDate');
                var currentDate = (currentDateWithMonth.getDate());
                var currentMonth = (currentDateWithMonth.getMonth());
                var selectedDate = (selectedDateWithMonth.getDate());
                var selectedMonth = (selectedDateWithMonth.getMonth());
                var lastDate = (lastDayOfThisMonth.getDate());
                var lastBeforeDate = (lastDayOfThisMonth.getDate()-1);
                var lastBeforeDate1 = (lastDayOfThisMonth.getDate()-2);
              //  var lastBeforeDate2= (lastDayOfThisMonth.getDate()-3);
                //var lastBeforeDate3 = (lastDayOfThisMonth.getDate()-4);
                if((currentMonth == selectedMonth && currentDate == lastBeforeDate) || 
                    (currentMonth == selectedMonth && currentDate == lastDate) ||
                    (currentMonth == selectedMonth && currentDate == lastBeforeDate1)) {
                    if(selectedDate == lastDate || selectedDate == lastBeforeDate || selectedDate == lastBeforeDate1)
                    {
                        //alert("selected date "+selectedDateWithMonth);
                        $("#contactHR").show();
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                else
                {
                    return true;
                }
            }
        </script>
        <style type="text/css">
            #dialog_link{
                background-color:  #15428B;
                font-family: Arial,Helvetica,sans-serif;
                font-size: 12px;
                font-weight: bold;
                height: 30px;
            }
            .table tr td{
                width: 15%;
            }
        </style>
    </head>
    <body onload="changeTabClass('rmApp');" >
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 100;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <span class="topheading">EPM (Exit Process Management)</span>
        <div class="generalFormContent">
            <%@include file="/WEB-INF/jsp/menu.jsp" %>
            <form  action="saveRMApprovedEmpData.htm" name="formRMApproval" id="formRMApproval" method="post" enctype="multipart/form-data">
                <div class="qpdSearch" style="height: auto;background-color: #FFF;">
                    <table width="100%" id="formTable" border="0">
                        <tbody>
                            <tr>
                                <td align="right" colspan="4" width="100%" height="5">
                                    <div id="errormessage" class="error-message">${Result}</div>
                                </td>
                            </tr>
                            <fmt:formatDate value="${exitEmpInfo.resignedDate}" var="resignedDate" pattern="dd-MM-yyyy" />
                            <fmt:formatDate value="${exitEmpInfo.empDoj}" var="empDoj" pattern="dd-MM-yyyy" />
                            <c:choose>
                                <c:when test="${rmActionData ne null && exitEmpInfo ne null && exitEmpInfo.rmApprovedDate ne null}">
                                    <tr>
                                        <td>
                                            <label class="headLabel">DEC</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.employeeNumber}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Name</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.employeeName}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Band</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${exitEmpInfo.band}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Sub Band</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.subBand}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">SBU</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${exitEmpInfo.practiceGroup}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Reporting Manager Name</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.rmName}</span>
                                            <input type="hidden" name="rmId" id="rmId" value="${exitEmpInfo.rmId}" readonly>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">DOJ</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${empDoj}</span>
                                        </td>
<!--                                        <td>
                                            <label class="headLabel">Date of Resignation</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${resignedDate}</span>
                                        </td>-->
                                        <td>
                                            <label class="headLabel">Contact Address</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.empAddress}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Contact Number</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${exitEmpInfo.contactNo}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Reasons For Leaving</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.leavingReason}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Email Id</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${exitEmpInfo.personalEmail}</span>
                                        </td>
                                         <td>
                                            <label class="headLabel">No of days served</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${rmActionData.daysServed}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Last Working Date</label>
                                        </td>
                                        <td>
                                            <span class="displayText">
                                                <fmt:formatDate value="${rmActionData.lastWorkingDate}" pattern="dd-MM-yyyy" var="lastWorkingDate" /> ${lastWorkingDate}
                                            </span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Balance notice period</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${rmActionData.balNoticePeriod}</span>
                                            <input type="hidden" name="balNoticePeriod" id="balNoticePeriod" value="${rmActionData.balNoticePeriod}" readonly>

                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Exit Trigger Date</label>
                                        </td>
                                        <td >
                                            <span class="displayText">
                                                <fmt:formatDate value="${rmActionData.exitTriggerDate}" pattern="dd-MM-yyyy" var="exitTriggerDate" /> ${exitTriggerDate}
                                            </span>
                                        </td>

                                        
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Notice Waiver</label>
                                        </td>
                                        <td>
                                            <span class="displayText">
                                                <c:forEach items="${noticeWaiverValueList}" var="noticeWaiverValues" varStatus="index" >
                                                    <c:if test="${rmActionData.noticeWaiverValue==index.index}">
                                                        ${noticeWaiverValues}
                                                    </c:if>
                                                </c:forEach>
                                            </span>
                                        </td>
                                        <c:if test="${rmActionData.rmComments != null}">
                                            <td>
                                                <label class="headLabel">RM Justification</label>                                      
                                            </td>
                                            <td>
                                                <span class="displayText">${rmActionData.rmComments}</span>
                                            </td>
                                        </c:if>
                                    </tr>
                                    <c:if test="${fileDetails != null}">
                                        <tr>
                                            <td>
                                                <label class="headLabel">Attachment</label>
                                            </td>
                                            <td>
                                                <c:set var="fileNameArray" value="${fn:split(fileDetails.fileName,'~~')}"></c:set>
                                                <a href="javascript:void(0)" onclick="fileDownload('${fileDetails.fileName}', '${fileDetails.fileType}', '${fileDetails.referenceName}', '${fileDetails.referenceId}', '${fileDetails.fileId}', '${fileDetails.moduleName}')">${fileNameArray[2]}</a>
                                            </td>
                                        </tr>
                                    </c:if>
                                    <tr>
                                        <td colspan="4">
                                            <input type="hidden" name="empId" id="empId" value="${exitEmpInfo.empId}" readonly />
                                            <input type="hidden" name="exitEmpId" id="exitEmpId" value="${exitEmpInfo.exitEmpId}" readonly />
                                            <input type="hidden" name="moduleId" id="moduleId" value="${menuDetails.rm_approvalId}" readonly />
                                            <input type="button" name="btnBack" id="btnBack" value="Back" class="backbutton" onclick="back()">
                                        </td>
                                    </tr>
                                </c:when>
                                <c:when test="${exitEmpInfo ne null}">
                                    <tr>
                                        <td width="15%">
                                            <label class="headLabel">DEC</label>
                                        </td>
                                        <td width="25%" >
                                            <span class="displayText">${exitEmpInfo.employeeNumber}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Name</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.employeeName}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Band</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${exitEmpInfo.band}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Sub Band</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.subBand}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">SBU</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${exitEmpInfo.practiceGroup}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Reporting Manager Name</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.rmName}</span>
                                            <input type="hidden" name="rmId" id="rmId" value="${exitEmpInfo.rmId}" readonly>
                                            <input type="hidden" name="resEmpId" id="resEmpId" value="${exitEmpInfo.resEmpId}" readonly>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">DOJ</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${empDoj}</span>
                                        </td>
<!--                                        <td>
                                            <label class="headLabel">Date of Resignation</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${resignedDate}</span>
                                        </td>-->
                                        <td>
                                            <label class="headLabel">Contact Address</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.empAddress}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Contact Number</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${exitEmpInfo.contactNo}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Reasons For Leaving</label>
                                        </td>
                                        <td>
                                            <span class="displayText">${exitEmpInfo.leavingReason}</span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Email Id</label>
                                        </td>
                                        <td >
                                            <span class="displayText">${exitEmpInfo.personalEmail}</span>
                                        </td>
                                        <td>
                                            <label class="headLabel">Last Working Date</label>
                                        </td>
                                        <td >
                                            <input type="text" name="lastWorkingDate" id="lastWorkingDate"  readonly value="" class="required date-picker compare"  />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Exit Trigger Date</label>
                                        </td>
                                        <td >
                                            <input type="text" name="exitTriggerDate" id="exitTriggerDate" value="${resignedDate}" readonly value="" class="required date-picker compare" />
                                        </td>
                                         <td>
                                            <label class="headLabel">No of days served</label>
                                        </td>
                                        <td>
                                            <input type="text" name="daysServed" id="daysServed" class="required displayText" readonly />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <label class="headLabel">Balance notice period</label>
                                        </td>
                                        <td>
                                            <input type="text" name="balNoticePeriod"  id="balNoticePeriod" class="required displayText" readonly />
                                        </td>                                      
                                    </tr>
                                    <tr>

                                        <td>
                                            <label class="headLabel">Notice Waiver</label>
                                        </td>
                                        <td  width="45%" style="text-align:left; width:150px;" >
                                            <select  name="noticeWaiverValue"  id="noticeWaiverValue" class="selectbox required displayText" onchange="rmActions(this.value)">
                                                <option value="">Select Notice Waiver</option>
                                                <c:forEach items="${noticeWaiverValueList}" var="noticeWaiverValues" varStatus="index" >
                                                    <option value="${index.index}">${noticeWaiverValues}</option>
                                                </c:forEach>
                                            </select>
                                            <select  name="noticeWaiverValue"  id="noticeWaiverValue1" class="selectbox required displayText" onchange="rmActions(this.value)" style="display:none;">
                                                <option value="">Select Notice Waiver</option>
                                                <option value="3">Not Applicable</option>
                                            </select>
                                        </td>


                                    </tr>

                                    <tr id="rmDiv" style="display:none;">
                                        <td><label class='headLabel'>RM Justification</label></td>
                                        <td><textarea cols="25" rows="1" id="rmComments" name="rmComments" maxlength="250"  minlength="0" onblur="textLimit(this,250);" onkeyup="textLimit(this,250)" class="formTextArea required  minlength maxlength resizableTextArea ui-resizable"${disabledStatus}>${rmFormData.rmComments}</textarea></td>                                        
                                        <td><label class='headLabel'>Attach Document</label></td>
                                        <td><input type='file' name='file' class="required displayText" size='50' id = 'fileUpload'/></td>
                                    </tr>
                                    <tr id="contactHR" style="display:none;">
                                        <td colspan="4" style="alignment-adjust: middle; color: red; padding-left: 220px;">Payroll has been closed for the selected month, please contact HR for further info</td>
                                    </tr>
                                </tbody>
                            </table>


                            <table width="100%" border="0" align="center" class="buttonAlignment">
                                <tbody>
                                    <tr>
                                        <td align="center">
                                            <input type="hidden" name="empId" id="empId" value="${exitEmpInfo.empId}" readonly />
                                            <input type="hidden" name="exitEmpId" id="exitEmpId" value="${exitEmpInfo.exitEmpId}" readonly />
                                            <input type="hidden" name="moduleId" id="moduleId" value="${menuDetails.rm_approvalId}" readonly />
                                            <input type="hidden" name="ApproveReject" id="ApproveRejectId" />
                                            <input type="submit" name="buttonName" id="approveId" value="Approve"  class="submitbutton"/>
                                            <input type="button" name="buttonName"  id="rejectId" value="Reject" class="cancelbutton" >
                                        </td>
                                    </tr>
                                </c:when>
                            </c:choose>
                        </tbody>
                    </table>
                </div>
                <textarea style="visibility: hidden" name="reasonRejection" id="reasonRejection"  ></textarea>
                <div id="dialog" style="visibility: hidden" title="Rejection Reason"  >
                    <textarea name="dummyVal" id="dummyVal"  rows="5" cols="25"></textarea>
                </div>
            </form>

            <form action="" method="post" name="fileDownloadForm" id="fileDownloadForm">
                <input class="fileUplaodData" type="hidden" name="fileName" id="fileName" />
                <input class="fileUplaodData" type="hidden" name="fileType" id="fileType" />
                <input class="fileUplaodData" type="hidden" name="referenceName" id="referenceName" />
                <input class="fileUplaodData" type="hidden" name="referenceId" id="referenceId" />
                <input class="fileUplaodData" type="hidden" name="fileId" id="fileId" />
                <input class="fileUplaodData" type="hidden" name="moduleName" id="moduleName" />
            </form>

        </div>
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
            stopLoading();
            function stopLoading() {
                if (ns4) {
                    loadingDivObj.visibility = "hidden";
                }
                else if (ns6 || ie4)
                    loadingDivObj.display = "none";
            }
            function startLoading() {
                if (ns4) {
                    loadingDivObj.visibility = "visible";
                }
                else if (ns6 || ie4)
                    loadingDivObj.display = "block";
            }
        </script>							
    </body>
</html>
