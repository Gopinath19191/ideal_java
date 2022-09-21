function addRow_Dependent() {
    if (dependent_validation(1)) {
        var trVal = "'DEP_TR'";
        var currentRow = $('#dependent_recordCount').val();
        var rowId = parseInt($('#dependent_recordCount').val()) + 1;
        $('#dependent_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="DEP_TR_' + rowId + '">';
        tr += '   <input type="hidden" name="jfFamId" id="jfFamId_' + rowId + '" value="" /> ';
        tr += '   <input type="hidden" name="DEP_TR_deleted" id="DEP_TR_deleted_' + rowId + '" value="0" /> ';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:75%" name="nameOfRelation" id="nameOfRelation_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="nameOfRelation_error_' + rowId + '">*required</span>';
        tr += '<div id = "nameOfRelation_err_' + rowId + '" style="color:red;display:none;">Only alphanumeric are allowed</div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="relationShip" id="relationShip_' + rowId + '" style="width:75%">';
        tr += $('#relationShip_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="relationShip_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:75%" name="dobRelation" id="dobRelation_' + rowId + '" class="dobRelation" readonly />';
        tr += '	<span style="color:red;display:none;" id="dobRelation_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:75%" name="occupationOfRel" id="occupationOfRel_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="occupationOfRel_error_' + rowId + '">*required</span>';
        tr += '<div id = "occupationOfRel_err_' + rowId + '" style="color:red;display:none;">Only alphanumeric are allowed</div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="checkbox" name="medicalInsuranceCheck" id="medicalInsuranceCheck_' + rowId + '" onclick="checkValue(this)" />';
        tr += '	<input type="hidden" name="medicalInsurance" class="lifeBox" id="medicalInsurance_' + rowId + '"  value="0" />';
        tr += '	<span style="color:red;display:none;" id="medicalInsurance_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="checkbox" name="lifeInsuranceCheck" id="nomineeCheck_' + rowId + '" onclick="checkNomineeValue(this)" />';
        tr += '	<input type="hidden" name="lifeInsurance" class="lifeBox" id="nominee_' + rowId + '"  value="0" />';
        tr += '	<span style="color:red;display:none;" id="nomineeCheck_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top" align="center">';
        tr += '       <img alt="remove" align="left" src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr += '<script>';
        tr += '   $(".dobRelation").datepicker({changeMonth: true, changeYear: true, disabled : true, maxDate: new Date,dateFormat:"dd-mm-yy",minDate: "-100Y",yearRange: "1950:' + (new Date).getFullYear() + '",currentText: "Now" });';
        tr += '</script>';
        tr = tr.replace(/selected="selected"/gi, "");
        $('#DEP_TR_' + currentRow).after(tr);
    }
}

function dependent_validation(res) {
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#dependent_recordCount').val()); i++) {
        if ($('#DEP_TR_deleted_' + i).val() == 0) {
            if ($('#nameOfRelation_' + i).val() != '' && $('#relationShip_' + i).val() != '' && $('#dobRelation_' + i).val() != '') {
                $('#nameOfRelation_error_' + i).hide();
                $('#nameOfRelation_err_' + i).hide();
                $('#relationShip_error_' + i).hide();
                $('#dobRelation_error_' + i).hide();
                $('#occupationOfRel_err_'+i).hide();
                if(!$('#nameOfRelation_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)){
                    $('#nameOfRelation_err_' + i).show();
                    flagCount++;
                }else{
                    $('#nameOfRelation_err_' + i).hide();
                }
                if($('#occupationOfRel_'+i).val() != '' && !$('#occupationOfRel_'+i).val().match(/^[a-zA-Z0-9-., ]/)){
                    $('#occupationOfRel_err_'+i).show();
                    flagCount++;
                }else{
                    $('#occupationOfRel_err_'+i).hide();
                }
            } else if ($('#nameOfRelation_' + i).val() != '' || $('#relationShip_' + i).val() != '' || $('#dobRelation_' + i).val() != '') {
                if ($('#nameOfRelation_' + i).val() == ''){
                    $('#nameOfRelation_err_' + i).hide();
                    $('#nameOfRelation_error_' + i).show();
                }
                else
                    $('#nameOfRelation_error_' + i).hide();
                if ($('#relationShip_' + i).val() == '')
                    $('#relationShip_error_' + i).show();
                else
                    $('#relationShip_error_' + i).hide();
                if ($('#dobRelation_' + i).val() == '')
                    $('#dobRelation_error_' + i).show();
                else
                    $('#dobRelation_error_' + i).hide();
                flagCount++;
            } else {
                if (res == 1) {
                    $('#nameOfRelation_error_' + i).show();
                    $('#relationShip_error_' + i).show();
                    $('#dobRelation_error_' + i).show();
                    flagCount++;
                }
            }
        }
    }
    if (flagCount == 0)
        return true;
    else
        return false;
}

function addRow_Visa() {
    if (visa_validation(1)) {
        var trVal = "'VISA_TR'";
        var currentRow = $('#visa_recordCount').val();
        var rowId = parseInt($('#visa_recordCount').val()) + 1;
        $('#visa_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="VISA_TR_' + rowId + '">';
        tr += '   <input type="hidden" name="jfVisaId" id="jfVisaId_' + rowId + '" value="" /> ';
        tr += '   <input type="hidden" name="VISA_TR_deleted" id="VISA_TR_deleted_' + rowId + '" value="0" /> ';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="referenceNumber" id="referenceNumber_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="referenceNumber_error_' + rowId + '">*required</span>';
        tr += '<div id="referenceNumber_err_' + rowId + '" style="color:red;display: none;">Only AlphaNumberics are allowed</div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="visaCountry" id="visaCountry_' + rowId + '" style="width:95%">';
        tr += $('#visaCountry_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="visaCountry_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="visaType" id="visaType_' + rowId + '" style="width:95%">';
        tr += $('#visaType_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="visaType_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="visaDateOfIssue" id="visaDateOfIssue_' + rowId + '" class="dateOfIssue" readonly />';
        tr += '	<span style="color:red;display:none;" id="visaDateOfIssue_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="visaDateOfExpiry" id="visaDateOfExpiry_' + rowId + '" class="dateOfExpiry" readonly />';
        tr += '	<span style="color:red;display:none;" id="visaDateOfExpiry_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="placeOfIssue" id="placeOfIssue_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="placeOfIssue_error_' + rowId + '">*required</span>';
        tr += '<div id="place_issue_err_' + rowId + '" style="color:red;display: none;">Only Alphabets are allowed</div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="entries" id="entries_' + rowId + '" style="width:95%">';
        tr += $('#entries_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="entries_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top" align="center">';
        tr += '       <img alt="remove" align="left" src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr += '<script>';
        tr += '   $(".dateOfIssue").datepicker({changeMonth: true, changeYear: true, disabled : true, maxDate: new Date,dateFormat:"dd-mm-yy",minDate: "-60Y" });';
        tr += '   $(".dateOfExpiry").datepicker({changeMonth: true, changeYear: true, disabled : true, maxDate: "40Y",dateFormat:"dd-mm-yy",minDate: "-60Y" });';
        tr += '</script>';
        tr = tr.replace(/selected="selected"/gi, "");
        $('#VISA_TR_' + currentRow).after(tr);
    }
}

function visa_validation(res) {
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#visa_recordCount').val()); i++) {
        if ($('#VISA_TR_deleted_' + i).val() == 0) {
            if ($('#referenceNumber_' + i).val() != '' && $('#visaCountry_' + i).val() != '' && $('#visaType_' + i).val() != '' && $('#visaDateOfIssue_' + i).val() != '' && $('#visaDateOfExpiry_' + i).val() != '' && $('#placeOfIssue_' + i).val() != '' && $('#entries_' + i).val() != '') {
//                alert("inside if");
                $('#referenceNumber_error_' + i).hide();
                $('#visaCountry_error_' + i).hide();
                $('#visaType_error_' + i).hide();
                $('#visaDateOfIssue_error_' + i).hide();
                $('#visaDateOfExpiry_error_' + i).hide();
                $('#entries_error_' + i).hide();
                $('#place_issue_err_' + i).hide();
                $('#placeOfIssue_error_' + i).hide();
                if (!$('#referenceNumber_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
                    alert("ref");
                    $('#referenceNumber_err_' + i).show();
                    flagCount++;
                } else {
                    $('#referenceNumber_err_' + i).hide();
                }

                if (!$('#placeOfIssue_' + i).val().match(/^[a-zA-Z-., ]+$/)) {
                    alert("place");
                    $('#place_issue_err_' + i).show();
                    flagCount++;
                } else {
                    $('#place_issue_err_' + i).hide();
                }
            } else if ($('#referenceNumber_' + i).val() != '' || $('#visaCountry_' + i).val() != '' || $('#visaType_' + i).val() != '' || $('#visaDateOfIssue_' + i).val() != '' || $('#visaDateOfExpiry_' + i).val() != '' || $('#placeOfIssue_' + i).val() != '' || $('#entries_' + i).val() != '') {
                if ($('#referenceNumber_' + i).val() == '') {
                    $('#referenceNumber_err_' + i).hide();
                    $('#referenceNumber_error_' + i).show();
                }
                else
                    $('#referenceNumber_error_' + i).hide();
                if ($('#placeOfIssue_' + i).val() == '') {
                    $('#place_issue_err_' + i).hide();
                    $('#placeOfIssue_error_' + i).show();
                } else {
//                    $('#place_issue_err_' + i).hide();
                    $('#placeOfIssue_error_' + i).hide();
                }

                if ($('#visaCountry_' + i).val() == '')
                    $('#visaCountry_error_' + i).show();
                else
                    $('#visaCountry_error_' + i).hide();
                if ($('#visaType_' + i).val() == '')
                    $('#visaType_error_' + i).show();
                else
                    $('#visaType_error_' + i).hide();
                if ($('#visaDateOfIssue_' + i).val() == '')
                    $('#visaDateOfIssue_error_' + i).show();
                else
                    $('#visaDateOfIssue_error_' + i).hide();
                if ($('#visaDateOfExpiry_' + i).val() == '')
                    $('#visaDateOfExpiry_error_' + i).show();
                else
                    $('#visaDateOfExpiry_error_' + i).hide();
                if ($('#entries_' + i).val() == '')
                    $('#entries_error_' + i).show();
                else
                    $('#entries_error_' + i).hide();
                flagCount++;
            } else {
                if (res == 1) {
                    $('#referenceNumber_error_' + i).show();
                    $('#visaCountry_error_' + i).show();
                    $('#visaType_error_' + i).show();
                    $('#visaDateOfIssue_error_' + i).show();
                    $('#visaDateOfExpiry_error_' + i).show();
                    $('#entries_error_' + i).show();
                    flagCount++;
                }
            }
        }
    }
    if (flagCount == 0) {
//        alert("true");
        return true;
    }
    else
        return false;
}

function addRow_Passport() {
    if (passport_validation(1)) {
        var trVal = "'PASS_TR'";
        var currentRow = $('#passport_recordCount').val();
        var rowId = parseInt($('#passport_recordCount').val()) + 1;
        $('#passport_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="PASS_TR_' + rowId + '">';
        tr += '   <input type="hidden" name="passportId" id="passportId_' + rowId + '" value="" /> ';
        tr += '   <input type="hidden" name="PASS_TR_deleted" id="PASS_TR_deleted_' + rowId + '" value="0" /> ';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="passportNumber" id="passportNumber_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="passportNumber_error_' + rowId + '">*required</span>';
        tr += '<div style="display:none;color: red;" id="passport_number_err_' + rowId + '">Only AlphaNumerics are allowed</div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="passDateOfIssue" id="passDateOfIssue_' + rowId + '" class="dateOfIssue" readonly />';
        tr += '	<span style="color:red;display:none;" id="passDateOfIssue_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="passDateOfExpiry" id="passDateOfExpiry_' + rowId + '" class="dateOfExpiry" readonly />';
        tr += '	<span style="color:red;display:none;" id="passDateOfExpiry_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="passPlaceOfIssue" id="passPlaceOfIssue_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="passPlaceOfIssue_error_' + rowId + '">*required</span>';
        tr += '<div style="display:none;color: red;" id="placeOfIssue_err_' + rowId + '">Only Alphabets are allowed</div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '       <input type="hidden" name="passAttachmentXY" value="" id="passAttachmentXY_' + rowId + '" >';
        tr += '       <input name="passAttachment_' + rowId + '" id="passAttachment_' + rowId + '" size="20" type="file"><br><span style="color: red; display: none;" id="passAttachment_error_' + rowId + '">*required</span>';
        tr += '<div style="color:red;display:none;" id = "passAttachment_err_' + rowId + '">.zip, .jar, .war & .rar files are not accepted</div>';
        tr += '   </td>';
        tr += '   <td valign="top" align="center">';
        tr += '       <img alt="remove" align="left" src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr += '<script>';
        tr += '   $(".dateOfIssue").datepicker({changeMonth: true, changeYear: true, disabled : true, maxDate: new Date,dateFormat:"dd-mm-yy",minDate: "-60Y" });';
        tr += '   $(".dateOfExpiry").datepicker({changeMonth: true, changeYear: true, disabled : true, maxDate: "40Y",dateFormat:"dd-mm-yy",minDate: "-60Y" });';
        tr += '</script>';
        tr = tr.replace(/selected="selected"/gi, "");
        $('#PASS_TR_' + currentRow).after(tr);
    }
}

function passport_validation(res) {
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#passport_recordCount').val()); i++) {
        if ($('#PASS_TR_deleted_' + i).val() == 0) {
            if ($('#passportNumber_' + i).val() != '' && $('#passDateOfIssue_' + i).val() != '' && $('#passDateOfExpiry_' + i).val() != '' && $('#passPlaceOfIssue_' + i).val() != '') {
                $('#passportNumber_error_' + i).hide();
                $('#passDateOfIssue_error_' + i).hide();
                $('#passDateOfExpiry_error_' + i).hide();
                $('#passPlaceOfIssue_error_' + i).hide();

                if ($('#passAttachmentXY_' + i).val() == '' && $('#passAttachment_' + i).val() == '') {
                    $('#passAttachment_error_' + i).show();
                    $('#passAttachment_err_' + i).hide();
                    flagCount++;
                } else {
                    $('#passAttachment_error_' + i).hide();
                }

                if (!$('#passportNumber_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
                    $('#passport_number_err_' + i).show();
                    flagCount++;
                } else {
                    $('#passport_number_err_' + i).hide();
                }

                if (!$('#passPlaceOfIssue_' + i).val().match(/^[a-zA-Z-., ]+$/)) {
                    $('#placeOfIssue_err_' + i).show();
                    flagCount++;
                } else {
                    $('#placeOfIssue_err_' + i).hide();
                }

                var passportIssueDate = document.getElementById('passDateOfIssue_' + i).value.split("-");
                var passportExpiryDate = document.getElementById('passDateOfExpiry_' + i).value.split("-");
                if (passportIssueDate[2] >= passportExpiryDate[2]) {
                    if (passportIssueDate[1] >= passportExpiryDate[1]) {
                        alert("Invalid Passport Issued / Expiry Date");
                        document.getElementById('passDateOfExpiry_' + i).focus();
                        return false;
                    }
                }
            } else if ($('#passportNumber_' + i).val() != '' || $('#passDateOfIssue_' + i).val() != '' || $('#passDateOfExpiry_' + i).val() != '' || $('#passPlaceOfIssue_' + i).val() != '' || $('#passAttachmentXY_' + i).val() != '') {
                if ($('#passportNumber_' + i).val() == '') {
                    $('#passport_number_err_' + i).hide();
                    $('#passportNumber_error_' + i).show();
                }
                else
                    $('#passportNumber_error_' + i).hide();
                if ($('#passDateOfIssue_' + i).val() == '')
                    $('#passDateOfIssue_error_' + i).show();
                else
                    $('#passDateOfIssue_error_' + i).hide();
                if ($('#passDateOfExpiry_' + i).val() == '')
                    $('#passDateOfExpiry_error_' + i).show();
                else
                    $('#passDateOfExpiry_error_' + i).hide();
                if ($('#passPlaceOfIssue_' + i).val() == '') {
                    $('#placeOfIssue_err_' + i).hide();
                    $('#passPlaceOfIssue_error_' + i).show();
                }
                else
                    $('#passPlaceOfIssue_error_' + i).hide();
                if ($('#passAttachmentXY_' + i).val() == '') {
                    $('#passAttachment_error_' + i).show();
                    $('#passAttachment_err_' + i).hide();
                }
                else
                    $('#passAttachment_error_' + i).hide();
                flagCount++;
            } else {
                if (res == 1) {
                    $('#passportNumber_error_' + i).show();
                    $('#passDateOfIssue_error_' + i).show();
                    $('#passDateOfExpiry_error_' + i).show();
                    $('#passPlaceOfIssue_error_' + i).show();
                    $('#passAttachment_error_' + i).show();
                    flagCount++;
                }
            }

            if ($('#passAttachment_' + i).val() != '') {
                var fileName = $('#passAttachment_' + i).val();
                if (fileName != null) {
                    var dot = fileName.lastIndexOf(".");
                    var extension = fileName.substr(dot, fileName.length);
                    if (extension == '.zip' || extension == '.rar' || extension == '.jar' || extension == '.war') {
                        $('#passAttachment_err_' + i).show();
                        return false;
                    } else {
                        $('#passAttachment_err_' + i).hide();
                        return true;
                    }
                }
            }
        }
    }
    if (flagCount == 0)
        return true;
    else
        return false;
}

function addRow_Emergency() {
    if (emergency_validation(1)) {
        var trVal = "'EMERGENCY_TR'";
        var currentRow = $('#emergency_recordCount').val();
        var rowId = parseInt($('#emergency_recordCount').val()) + 1;
        $('#emergency_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="EMERGENCY_TR_' + rowId + '">';
        tr += '   <input type="hidden" name="emergencyId" id="emergencyId_' + rowId + '" value="" /> ';
        tr += '   <input type="hidden" name="EMERGENCY_TR_deleted" id="EMERGENCY_TR_deleted_' + rowId + '" value="0" /> ';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="name" id="name_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="name_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="relationship" id="relationship_' + rowId + '" class="dateOfIssue" />';
        tr += '	<span style="color:red;display:none;" id="relationship_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="home_phone_number" id="home_phone_number_' + rowId + '" class="dateOfExpiry" />';
        tr += '	<span style="color:red;display:none;" id="home_phone_number_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="mobile_number" id="mobile_number_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="mobile_number_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:80%" name="work_phone_number" id="work_phone_number_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="work_phone_number_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top" align="center">';
        tr += '       <img alt="remove" align="left" src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr = tr.replace(/selected="selected"/gi, "");
        $('#EMERGENCY_TR_' + currentRow).after(tr);
    }
}

function emergency_validation(res) {
    var flagCount = 0;
    var recordCount = 0;
    for (var i = 1; i <= parseInt($('#emergency_recordCount').val()); i++) {
        if ($('#EMERGENCY_TR_deleted_' + i).val() == 0) {
            if ($('#name_' + i).val() != '' && $('#relationship_' + i).val() != '' && $('#mobile_number_' + i).val() != '') {
                $('#name_error_' + i).hide();
                $('#relationship_error_' + i).hide();
                $('#mobile_number_error_' + i).hide();
                $('#name_err_' + i).hide();
                $('#relationship_err_' + i).hide();
                $('#mobile_number_err' + i).hide();

                if (!$('#name_' + i).val().match(/^[a-zA-Z-., ]+$/)) {
                    $('#name_err_' + i).show();
                    flagCount++;
                } else {
                    $('#name_err_' + i).hide();
                }

                if (!$('#relationship_' + i).val().match(/^[a-zA-Z-., ]+$/)) {
                    $('#relationship_err_' + i).show();
                    flagCount++;
                } else {
                    $('#relationship_err_' + i).hide();
                }



                if (!$('#mobile_number_' + i).val().match(/^[0-9 ]+$/)) {
                    $('#mobile_number_err_' + i).show();
                    flagCount++;
                } else {
                    $('#mobile_number_err_' + i).hide();
                }





            }
            else if ($('#name_' + i).val() != '' || $('#relationship_' + i).val() != '' || $('#mobile_number_' + i).val() != '') {
                if ($('#name_' + i).val() == '') {
                    $('#name_err_' + i).hide();
                    $('#name_error_' + i).show();
                }
                else
                    $('#name_error_' + i).hide();
                if ($('#relationship_' + i).val() == '') {
                    $('#relationship_err_' + i).hide();
                    $('#relationship_error_' + i).show();
                }
                else
                    $('#relationship_error_' + i).hide();
                if ($('#mobile_number_' + i).val() == '') {
                    $('#mobile_number_err_' + i).hide();
                    $('#mobile_number_error_' + i).show();
                }
                else
                    $('#mobile_number_error_' + i).hide();

                flagCount++;
            } else {

                if (res == 1 || res == 2) {
                    $('#name_error_' + i).show();
                    $('#relationship_error_' + i).show();
                    $('#mobile_number_error_' + i).show();
                    flagCount++;
                }
            }

            if ($('#home_phone_number_' + i).val() != '') {
                if (!$('#home_phone_number_' + i).val().match(/^[0-9 ]+$/)) {
                    $('#home_phone_number_err_' + i).show();
                    flagCount++;
                } else {
                    $('#home_phone_number_err_' + i).hide();
                }
            }
            if ($('#work_phone_number_' + i).val() != '') {

                if (!$('#work_phone_number_' + i).val().match(/^[0-9 ]+$/)) {
                    $('#work_phone_number_err_' + i).show();
                    flagCount++;
                } else {
                    $('#work_phone_number_err_' + i).hide();
                }
            }

            if ($('#home_phone_number_' + i).val() == '') {
                $('#home_phone_number_err_' + i).hide();
            }
            if ($('#work_phone_number_' + i).val() == '') {
                $('#work_phone_number_err_' + i).hide();
            }

        }
    }
    if (flagCount == 0)
        return true;
    else
        return false;
}

function licence_validation() {
    var flagCount = 0;
    if ($('#dlNumber').val() != '' && $('#dlDateOfIssue').val() != '' && $('#dlDateOfExpiry').val() != '' && $('#dlPlaceOfIssue').val() != '') {
        
        $('#dlNumber_error').hide();
        $('#dlNumber_err').hide();
        $('#dlDateOfIssue_error').hide()
        $('#dlDateOfExpiry_error').hide();
        $('#dlPlaceOfIssue_error').hide();
        $('#dlPlaceOfIssue_err').hide();
        $('#dlProof_error').hide();
        $('#dlProof_err').hide();

        if (!$('#dlNumber').val().match(/^[a-zA-Z0-9-.,_ ]+$/)) {
            $('#dlNumber_err').show();
            flagCount++;
        } else {
            $('#dlNumber_err').hide();
        }
//        var dlIssueDate = document.getElementById('dlDateOfIssu').value.split("-");
//        var dlExpiryDate = document.getElementById('dlDateOfExpiry').value.split("-");
//        if (dlIssueDate[2] >= dlExpiryDate[2]) {
//            if (dlIssueDate[1] >= dlExpiryDate[1]) {
//                alert("Invalid Licence Issued / Expiry Date");
//                document.getElementById('dlDateOfExpiry').focus();
//                return false;
//            }
//        }

        if (!$('#dlPlaceOfIssue').val().match(/^[a-zA-Z0-9-., ]+$/)) {
            $('#dlPlaceOfIssue_err').show();
            flagCount++;
        } else {
            $('#dlPlaceOfIssue_err').hide();
        }

        if ($('#dlProofID').val() == '' && $('#dlProof').val() == '') {
            $('#dlProof_err').hide();
            $('#dlProof_error').show();
            flagCount++;
        } else {
            $('#dlProof_error').hide();
        }

    }

    else if ($('#dlNumber').val() != '' || $('#dlDateOfIssue').val() != '' || $('#dlDateOfExpiry').val() != '' || $('#dlPlaceOfIssue').val() != '' || $('#dlProof').val() != '') {
        
        if ($('#dlNumber').val() == '') {
            $('#dlNumber_err').hide();
            $('#dlNumber_error').show();
            flagCount++;
        } else {
            $('#dlNumber_error').hide();
        }
        if ($('#dlDateOfIssue').val() == '') {
            $('#dlDateOfIssue_error').show();
            flagCount++;
        } else {
            $('#dlDateOfIssue_error').hide();
        }
        if ($('#dlDateOfExpiry').val() == '') {
            $('#dlDateOfExpiry_error').show();
            flagCount++;
        } else {
            $('#dlDateOfExpiry_error').hide();
        }
        if ($('#dlPlaceOfIssue').val() == '') {
            $('#dlPlaceOfIssue_err').hide();
            $('#dlPlaceOfIssue_error').show();
            flagCount++;
        } else {
            $('#dlPlaceOfIssue_error').hide();
        }
        if ($('#dlProofID').val() == '' && $('#dlProof').val() == '') {
            $('#dlProof_err').hide();
            $('#dlProof_error').show();
            flagCount++;
        } else {
            $('#dlProof_error').hide();
        }
    } else {

    }

    if ($('#dlProof').val() != '') {
        
        var fileName = $('#dlProof').val();
        if (fileName != null) {
            var dot = fileName.lastIndexOf(".");
            var extension = fileName.substr(dot, fileName.length);
            if (extension == '.zip' || extension == '.rar' || extension == '.jar' || extension == '.war') {
                $('#dlProof_error').hide();
                $('#dlProof_err').show();
                flagCount++;
            } else {
                $('#dlProof_err').hide();
            }
        }
    }

    if (flagCount == 0)
        return true;
    else
        return false;

}

function deleteRow(tr, row, val) {
    if (val == 1) {
        var con = confirm("Are you sure want to delete ?");
        if (con) {
            $('#' + tr + '_' + row).hide();
            $('#' + tr + '_deleted_' + row).val(1);
            return true;
        } else {
            return false;
        }
    } else {
        $('#' + tr + '_' + row).hide();
        $('#' + tr + '_deleted_' + row).val(1);
        return true;
    }
}

function formValidate() {
    // val '1' => Mandatory '0' => Non Mandatory
    if (dependent_validation(1) && emergency_validation(1) && passport_validation(0) && visa_validation(0) && licence_validation()) {
        if (document.getElementById('dlDateOfIssue').value != "") {
//            alert("inside formValidate");
            var licenseIssueDate = document.getElementById('dlDateOfIssue').value.split("-");
            var licenseExpiryDate = document.getElementById('dlDateOfExpiry').value.split("-");
            if (licenseIssueDate[2] >= licenseExpiryDate[2]) {
                if (licenseIssueDate[1] >= licenseExpiryDate[1]) {
                    alert("Invalid License Issued / Expiry Date");
                    document.getElementById('dlDateOfExpiry').focus();
                    return false;
                }
            }
        }
        $("#continuebutton2").hide();
        return true;
    }
    else
        return false;
}

