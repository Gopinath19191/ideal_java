$(document).ready(function() {
    $("#employeeName").autocomplete("ajaxsearch.htm", {
        minChars: 1,
        width: 350,
        matchContains: true
    });
    $("#employeeName").result(function(event, data, formatted) {
        if (data) {
            $("#employeeId").val(data[1]);
        }
    });
});

function addRow_Certification(currentRow) {
    var rowId = parseInt($('#recordCount').val()) + 1;
    $('#recordCount').val(rowId);
    var tr = '';
    tr += '<tr id="TR_' + rowId + '">';
    tr += '   <input type="hidden" name="certificationId_' + rowId + '" id="certificationId_' + rowId + '" value="" /> ';
    tr += '   <input type="hidden" name="deletedTR_' + rowId + '" id="deletedTR_' + rowId + '" value="0" /> ';
    tr += '   <td valign="top" align="center">';
    tr += '	<input type="text" style="width:95%" maxlength="50" name="qualification_' + rowId + '" id="qualification_' + rowId + '" /><span style="color:red;display:none;" id="qualification_error_' + rowId + '">*required</span>';
    tr += '   </td>';
    tr += '   <td valign="top" align="center"><input maxlength="15" style="width:95%" name="year_of_passing_' + rowId + '" id="year_of_passing_' + rowId + '" value="" type="text"><span style="color: red; display: none;" id="year_of_passing_error_' + rowId + '">*required</span></td>';
    tr += '   <td valign="top" align="center">';
    tr += '	<input type="text" maxlength="100" style="width:95%" name="institution_' + rowId + '" id="institution_' + rowId + '" /><span style="color:red;display:none;" id="institution_error_' + rowId + '">*required</span>';
    tr += '   </td>';
    tr += '   <td valign="top" align="center"><input maxlength="5" style="width:95%" name="percentage_' + rowId + '" id="percentage_' + rowId + '" type="text" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" ><span style="color: red; display: none;" id="percentage_error_' + rowId + '">*required</span></td>  ';
    tr += '   <td valign="top" align="center"><input maxlength="100"  style="width:95%" name="remarks_' + rowId + '" id="remarks_' + rowId + '" type="text"><span style="color: red; display: none;" id="remarks_error_' + rowId + '">*required</span></td>  ';
    tr += '   <td valign="top" align="center">';
    tr += '       <img alt="Add" src="' + $('#base_path').val() + '/images/add.gif" onclick="addRow_Certification(' + rowId + ');" />';
    tr += '       <img alt="Remove" src="' + $('#base_path').val() + '/images/delete.gif" onclick="deleteRow(' + rowId + ',0)" />';
    tr += '   </td>';
    tr += '</tr>';
    tr = tr.replace(/selected="selected"/gi, "");
    tr = tr.replace(/selected/gi, "");
    $('#TR_' + currentRow).after(tr);
}
function addRow_Kra(currentRow) {
    var rowId = parseInt($('#recordCount').val()) + 1;
    $('#recordCount').val(rowId);
    var tr = '';
    tr += '<tr id="TR_' + rowId + '">';
    tr += '<input type="hidden" name="certificationId_' + rowId + '" id="certificationId_' + rowId + '" value="" /> ';
    tr += '<input type="hidden" name="deletedTR_' + rowId + '" id="deletedTR_' + rowId + '" value="0" /> ';
    tr += '<td>';
    tr += '<textarea rows="4" cols="50" name="qualification_' + rowId + '" id="qualification_' + rowId + '"></textarea><span style="color:red;display:none;" id="qualification_error_' + rowId + '">*required</span>';
    tr += '</td>';
    tr += '<td>';
    tr += '<input type="text"   id="krauom_' + rowId + '" name="krauom_' + rowId + '" value=""/><span style="color:red;display:none;" id="krauom_error_' + rowId + '">*required</span>';
    tr += '</td>';
    tr += '<td>';
    tr += '<input type="text"   id="kratarget_' + rowId + '" name="kratarget_' + rowId + '" value=""/><span style="color:red;display:none;" id="kratarget__error_' + rowId + '">*required</span>';
    tr += '</td>';
    tr += '<td><input name="percentage_' + rowId + '" id="percentage_' + rowId + '" type="text" class="checkval" onChange="calculateTotalWeightage()" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" ><span style="color: red; display: none;" id="percentage_error_' + rowId + '">*required</span></td>  ';
    tr += '<td>';
    tr += '<img class="addRemove" alt="Add" src="/ideal_kra/images/tm_add.png" onclick="addRow_Kra(' + rowId + ');" />  ';
    tr += '<img class="addRemove" alt="Remove" src="/ideal_kra/images/tm_delete.png" onclick="deleteRow(' + rowId + ',0)" />';
    tr += '</td>';
    tr += '</tr>';
    tr = tr.replace(/selected="selected"/gi, "");
    tr = tr.replace(/selected/gi, "");
    $('#TR_' + currentRow).after(tr);
}



function deleteRow(row, val) {
    if (val == 1) {
        var con = confirm("Are you sure want to delete ?");
        if (con) {
            $('#TR_' + row).hide();
            $('#percentage_' + row).val("");
            $('#deletedTR_' + row).val(1);
             calculateTotalWeightage();
            return true;
        } else {
            return false;
        }
    } else {
        $('#TR_' + row).hide();
        $('#percentage_' + row).val("");
        $('#deletedTR_' + row).val(1);
        calculateTotalWeightage();
        return true;
    }
    
}
function searchKra(){
    var year = $("#financialYear").val();
    var quarterId = $("#quarterId").val();
    $('#employeeKraForm').attr("action", "generalInfo.htm?actionName=employeeKra&page=1&financeYear="+year+"&quarter="+quarterId);
    document.employeeKraForm.submit();
    return true;
}

function searchEmployeeKra(){
    var year = $("#financialYear").val();
    var quarterId = $("#quarterId").val();
    var employeeId = $("#associateId").val();
    if(employeeId == null || employeeId == ""){
        alert("Please Select Employee");
        return false;
    }else{
        $('#employeeKraForm').attr("action", "generalInfo.htm?actionName=employeeKra&page=1&financeYear="+year+"&quarter="+quarterId+"&associateId="+employeeId);
        document.employeeKraForm.submit();
        startLoading();
        return true;
    }
}

function getRmKraExcelExport(){
    //var employeeId= $("#employeeId").val();
    var financialYear = $("#financeYear").val();
    var quarterId = $("#quarter").val();
    $('#employeeKraForm').attr("action", "getRmKraExcelExport.htm?financialYear="+financialYear+"&quarter="+quarterId);
    document.employeeKraForm.submit();
    return true;
}
function getreportKraExcelExport(){
    //var employeeId= $("#employeeId").val();
    var financialYear = $("#financeYear").val();
    var quarterId = $("#quarter").val();
    $('#employeeKraForm').attr("action", "getReportKraExcelExport.htm?financialYear="+financialYear+"&quarter="+quarterId);
    document.employeeKraForm.submit();
    return true;
}
function getExcelExport(){
    var employeeId= $("#employeeId").val();
    var financialYear = $("#financialYear").val();
    var quarterId = $("#quarterId").val();
    $('#employeeKraForm').attr("action", "getDataExcel.htm?employeeId="+employeeId+"&financialYear="+financialYear+"&quarter="+quarterId);
    document.employeeKraForm.submit();
    return true;
}
function searchEmployeeKraToCopy(){
    var employeeId= $("#employeeId").val();
    var financialYear = $("#financialYear").val();
    var copyQuarterId = $("#copyQuarterId").val();
    var quarterId = $("#quarterId").val();
    var kraId   =  $("#kraId").val();
    $('#employeeKraForm').attr("action", "copyKra.htm?employeeId="+employeeId+"&financialYear="+financialYear+"&quarter="+quarterId+"&kraId="+kraId+"&copyQuarterId="+copyQuarterId);
    document.employeeKraForm.submit();
    return true;
    
    
}
function copyKra(){    
//    var employeeId= $("#employeeId").val();
//    var financialYear = $("#financialYear").val();
//    var quarterId = $("#quarterId").val();
//    var kraId = $("#kraId").val();
    $('#employeeKraForm').attr("action", "copyKra.htm?kraId="+kraId+"&reportingManager="+1+"&edit="+1);
    document.employeeKraForm.submit();
    return true;
}

function annualAppraisal(){
    var employeeId= $("#employeeId").val();
    var financialYear = $("#financialYear").val();
    var quarterId = $("#quarterId").val();
    var kraId   =  $("#kraId").val();
//    alert("annualAppraisal.htm?employeeId="+employeeId+"&financialYear="+financialYear+"&quarter="+quarterId+"&kraId="+kraId);
    $('#employeeKraForm').attr("action", "annualAppraisal.htm?employeeId="+employeeId+"&financialYear="+financialYear+"&quarter="+quarterId+"&kraId="+kraId);
    document.employeeKraForm.submit();
    return true;
}

function acceptKra(){
    var kraId= $("#kraId").val();
    $('#employeeKraForm').attr("action", "acceptKra.htm?kra_id="+kraId);
    document.employeeKraForm.submit();
    return true;
}

function rmFilter(){
    var year = $("#financeYear").val();
    var quarterId = $("#quarter").val();
    $('#employeeKraForm').attr("action", "rmListKra.htm");
    document.employeeKraForm.submit();
    return true;
}
function reportFilter(){
    var year = $("#financeYear").val();
    var quarterId = $("#quarter").val();
    $('#employeeKraForm').attr("action", "reportListKra.htm");
    document.employeeKraForm.submit();
    return true;
}

function loadForm(actionName, page) {
    document.menuFormDetails.actionName.value = actionName;
    document.menuFormDetails.page.value = page;
    document.menuFormDetails.submit();
}






function alpha(value) {
    if (value != "") {
        return /^[A-Za-z\-\. ]+$/.test(value);
    } else {
        return true;
    }
}

function alphaNumeric(value) {
    if (value != "") {
        return /^[A-Za-z0-9]+$/.test(value);
    } else {
        return true;
    }
}

function alphaSpace(value) {
    if (value != "") {
        return /^[A-Za-z ]+$/.test(value);
    } else {
        return true;
    }
}

function phone(value) {
    if (value != "") {
        return /^[0-9\- ]+$/.test(value);
    } else {
        return true;
    }
}

function script(value) {
    if (value != "") {
        return /^[/^[A-Za-z0-9\-\_\.\#\@\*\&\%\'\"\,\(\)\[\] ]+$/.test(value);
    } else {
        return true;
    }
}

function validateCertificate() {
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#recordCount').val()); i++) {
        if ($('#deletedTR_' + i).val() == 0) {
            if ($('#qualification_' + i).val() != '' && $('#year_of_passing_' + i).val() != '' && $('#institution_' + i).val() != '' && $('#percentage_' + i).val() != '') {
                $('#qualification_error_' + i).hide();
                $('#year_of_passing_error_' + i).hide();
                $('#institution_error_' + i).hide();
                $('#percentage_error_' + i).hide();
                if (parseInt($('#percentage_' + i).val()) > 100 || parseInt($('#percentage_' + i).val()) == 0) {
                    alert("Not a valid percentage");
                    $('#percentage_' + i).focus();
                    $('#percentage_' + i).select();
                    return false;
                }
                if (!script($('#institution_' + i).val())) {
                    alert("Script not allowed");
                    document.getElementById('institution_' + i).focus();
                    document.getElementById('institution_' + i).select();
                    return false;
                }
                if (!script($('#remarks_' + i).val())) {
                    alert("Script not allowed");
                    document.getElementById('remarks_' + i).focus();
                    document.getElementById('remarks_' + i).select();
                    return false;
                }
            } else if ($('#qualification_' + i).val() != '' || $('#year_of_passing_' + i).val() != '' || $('#institution_' + i).val() != '' || $('#percentage_' + i).val() != '') {
                if ($('#qualification_' + i).val() == '')
                    $('#qualification_error_' + i).show();
                else
                    $('#qualification_error_' + i).hide();
                if ($('#year_of_passing_' + i).val() == '')
                    $('#year_of_passing_error_' + i).show();
                else
                    $('#year_of_passing_error_' + i).hide();
                if ($('#institution_' + i).val() == '')
                    $('#institution_error_' + i).show();
                else
                    $('#institution_error_' + i).hide();
                if ($('#percentage_' + i).val() == '')
                    $('#percentage_error_' + i).show();
                else
                    $('#percentage_error_' + i).hide();
                flagCount++;
            } else {
                $('#qualification_error_' + i).show();
                $('#year_of_passing_error_' + i).show();
                $('#institution_error_' + i).show();
                $('#percentage_error_' + i).show();
                flagCount++;
            }
        }
    }
    if (flagCount == 0)
        return true;
    else
        return false;
}

function validateKra(submitValue) {
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#recordCount').val()); i++) {
        if ($('#deletedTR_' + i).val() == 0) {
            if ($('#qualification_' + i).val() != '' && $('#krauom_' + i).val() != '' && $('#kratarget_' + i).val() != '' && $('#percentage_' + i).val() != '') {
                $('#qualification_error_' + i).hide();
                $('#krauom_error_' + i).hide();
                $('#kratarget_error_' + i).hide();
                $('#percentage_error_' + i).hide();
                if (parseInt($('#percentage_' + i).val()) > 100 || parseInt($('#percentage_' + i).val()) == 0) {
                    alert("Not a valid percentage");
                    $('#percentage_' + i).focus();
                    $('#percentage_' + i).select();
                    return false;
                }
                 
                
            } else if ($('#qualification_' + i).val() != '' || $('#krauom_' + i).val() != '' || $('#kratarget_' + i).val() != '' || $('#percentage_' + i).val() != '') {
                if ($('#qualification_' + i).val() == '')
                    $('#qualification_error_' + i).show();
                else
                    $('#qualification_error_' + i).hide();
                
                if ($('#krauom_' + i).val() == '')
                    $('#krauom_error_' + i).show();
                else
                    $('#krauom_error_' + i).hide();
                
                if ($('#kratarget_' + i).val() == '')
                    $('#kratarget_error_' + i).show();
                else
                    $('#kratarget_error_' + i).hide();
                
                if ($('#percentage_' + i).val() == '')
                    $('#percentage_error_' + i).show();
                else
                    $('#percentage_error_' + i).hide();
                flagCount++;
            } else {
                $('#qualification_error_' + i).show();
                $('#krauom_error_' + i).show();
                 $('#kratarget_error_' + i).show();
                $('#percentage_error_' + i).show();
                flagCount++;
            }
            //total calc
            
        }
    }
    if (flagCount == 0)
        {
            var t=$('#total').val();
          
           if(submitValue=='m') {
               if(t!=100){
                   alert("Total Weightage should be 100%");
                   return false;
               }else{
                    document.employeeKraForm.actionValue.value=submitValue;
                    startLoading();
                    $('#employeeKraForm').attr("action", "employeeKraSubmit.htm");
                    document.employeeKraForm.submit();
                    return true;
               }
              
           }else{
                document.employeeKraForm.actionValue.value=submitValue;
                startLoading();
                $('#employeeKraForm').attr("action", "employeeKraSubmit.htm");
                document.employeeKraForm.submit();
                return true;
           }
        }
        
    else
        return false;
}

function rmValidateKra(submitValue){
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#recordCount').val()); i++) {
        if ($('#rm_remarks_' + i).val() != ''){
            
        }
        else{
            flagCount++;
            alert("Please provide Remarks against each KRA Description");
            return false;
        }
    }
    if(flagCount==0){
        document.employeeKraForm.actionValue.value=submitValue;
        $('#employeeKraForm').attr("action", "updateRmRemarks.htm");
        document.employeeKraForm.submit();
        return true;
    }
}

function calculateTotalWeightage() {
    var totaltsHrs=0;
   // var value = this.id.value;  
    $(".checkval").each(function() {
            var chkId = this.id;
            chkIdSpt = chkId.split('_');
            counterVar = chkIdSpt[1];
            tsHrs = $("#percentage_"+counterVar).val();
           
            if(tsHrs != "") {
                totaltsHrs += parseFloat(tsHrs);
            }
        
     });
     
    $("#total").val(parseFloat(totaltsHrs ));
    
    
}





function refreshEmployee() {
    $('#employeeId').val("");
    $('#employeeName').val("");
}

function checkUnique(value, type, objectId, res) {
    $.post('checkUnique.htm', {
        value: value,
        type: type,
        id: $('#' + objectId).val()
    },
    function(ajaxObj) {
        if (ajaxObj >= 1) {
            alert(res + " number already exists.Please check once again");
            document.getElementById(objectId).select();
            return false;
        }
    }
    );
}