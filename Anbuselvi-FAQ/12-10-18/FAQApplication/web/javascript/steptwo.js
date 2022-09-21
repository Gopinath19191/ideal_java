function addRow_Employer() {
    if (employer_validation(1)) {
        var trVal = "'EMP_TR'";
        var currentRow = $('#employer_recordCount').val();
        //alert(currentRow);
        var rowId = parseInt($('#employer_recordCount').val()) + 1;
        $('#employer_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="EMP_TR_' + rowId + '">';
        tr += '   <input type="hidden" name="jfPreEmpId" id="jfPreEmpId_' + rowId + '" value="" /> ';
        tr += '   <input type="hidden" name="EMP_TR_deleted" id="EMP_TR_deleted_' + rowId + '" value="0" /> ';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="namePrevEmp" id="namePrevEmp_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="namePrevEmp_error_' + rowId + '">*required</span>';
        tr += ' <div id = "prevEmpName_' + rowId + '" style="display:none;"><font color="red">Olny Alphabets are allowed</font></div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="nameAddPrevEmp" id="nameAddPrevEmp_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="nameAddPrevEmp_error_' + rowId + '">*required</span>';
        tr += ' <div id = "prevEmpAdd_' + rowId + '" style="display:none;"><font color="red">Olny Alpha Numerics are allowed</font></div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="dateOfJoin" id="dateOfJoin_' + rowId + '" class="dateOfJoin" readonly />';
        tr += '	<span style="color:red;display:none;" id="dateOfJoin_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="desigOnJoin" id="desigOnJoin_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="desigOnJoin_error_' + rowId + '">*required</span>';
        tr += ' <div id = "prevEmpDesigOnJoin_' + rowId + '" style="display:none;"><font color="red">Olny Alpha Numerics are allowed</font></div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="salaryOnJoin" id="salaryOnJoin_' + rowId + '" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,2,false);" maxlength="9" />';
        tr += '	<span style="color:red;display:none;" id="salOnJoin_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="dateOfRel" id="dateOfRel_' + rowId + '" class="dateOfRel" readonly />';
        tr += '	<span style="color:red;display:none;" id="dateOfRel_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="desigOnRel" id="desigOnRel_' + rowId + '" />';
        tr += '	<span style="color:red;display:none;" id="desigOnRel_error_' + rowId + '">*required</span>';
        tr += ' <div id = "prevEmpDesigOnRel_' + rowId + '" style="display:none;"><font color="red">Olny Alpha Numerics are allowed</font></div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:95%" name="salOnRel" id="salOnRel_' + rowId + '" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,2,false);" maxlength="9" />';
        tr += '	<span style="color:red;display:none;" id="salOnRel_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="exp_year" id="exp_year_' + rowId + '" style="width:48%">';
        tr += $('#exp_year_1').html();
        tr += '	</select>';
        tr += '	<select name="exp_month" id="exp_month_' + rowId + '" style="width:48%">';
        tr += $('#exp_month_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="exp_year_error_' + rowId + '">*required</span>';
        tr += '	<span style="color:red;display:none;" id="exp_month_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '       <input type="hidden" name="exp_attachmentXY" id="exp_attachmentXY_' + rowId + '" value="" >';
        tr += '       <input name="exp_attachment_' + rowId + '" id="exp_attachment_' + rowId + '" size="8" type="file">';
        tr += '       <span style="color:red;display:none;" id="exp_attachment_error_' + rowId + '">*required</span>';
        tr += ' <div id="exp_attachment_err_' + rowId + '" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>';
        tr += '   </td>';
        tr += '   <td valign="top" align="center">';
        tr += '       <img src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr += '<script>';
        tr += '   $(".dateOfRel").datepicker({changeMonth: true, changeYear: true, disabled : true, maxDate: new Date,dateFormat:"dd-mm-yy",showButtonPanel: true });';
        tr += '   $(".dateOfJoin").datepicker({changeMonth: true, changeYear: true, disabled : true, maxDate: new Date,dateFormat:"dd-mm-yy",showButtonPanel: true,minDate: "-60Y" });';
        tr += '</script>';
        tr = tr.replace(/selected="selected"/gi, "");
        tr = tr.replace(/selected/gi, "");
        $('#EMP_TR_' + currentRow).after(tr);
    }
}

function employer_validation(res) {
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#employer_recordCount').val()); i++) {
        if ($('#EMP_TR_deleted_' + i).val() == 0) {
            if ($('#namePrevEmp_' + i).val() != '' && $('#nameAddPrevEmp_' + i).val() != '' && $('#dateOfJoin_' + i).val() != '' && $('#desigOnJoin_' + i).val() != '' && $('#salaryOnJoin_' + i).val() != '' && $('#desigOnRel_' + i).val() != '' && $('#salOnRel_' + i).val() != '' && $('#exp_year_' + i).val() != '' && $('#exp_month_' + i).val() != '') {
//                alert("inside if");
                $('#namePrevEmp_error_' + i).hide();
                $('#prevEmpName_' + i).hide();
                $('#nameAddPrevEmp_error_' + i).hide();
                $('#prevEmpAdd_' + i).hide();
                $('#dateOfJoin_error_' + i).hide();
                $('#desigOnJoin_error_' + i).hide();
                $('#prevEmpDesigOnJoin_' + i).hide();
                $('#salOnJoin_error_' + i).hide();
                $('#desigOnRel_error_' + i).hide();
                $('#prevEmpDesigOnRel_' + i).hide();
                $('#salOnRel_error_' + i).hide();
                $('#exp_year_error_' + i).hide();
                $('#exp_month_error_' + i).hide();
                if (!$('#namePrevEmp_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
//                    alert("Only Alphabets are allowed");
//                    $("#prevEmpName_" + i).html("<font color='red'>Olny Alphabets are allowed</font>");
                    $('#prevEmpName_' + i).show();
                    return false;
                }
                if (!$('#nameAddPrevEmp_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
//                    alert("Only Alpha Numerics are allowed");
//                    $("#prevEmpAdd_" + i).html("<font color='red'>Olny Alpha Numerics are allowed</font>");
                    $('#prevEmpAdd_' + i).show();
                    return false;
                }
                if (!$('#desigOnJoin_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
//                    alert("Only Alpha Numerics are allowed");
                    $('#prevEmpDesigOnJoin_' + i).show();
                    return false;
                }
                if (!$('#desigOnRel_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
//                    alert("Only Alpha Numerics are allowed");
                    $('#prevEmpDesigOnRel_' + i).show();
                    return false;
                }
            } else if ($('#namePrevEmp_' + i).val() != '' || $('#nameAddPrevEmp_' + i).val() != '' || $('#dateOfJoin_' + i).val() != '' || $('#desigOnJoin_' + i).val() != '' || $('#salaryOnJoin_' + i).val() == '' || $('#desigOnRel_' + i).val() != '' || $('#salOnRel_' + i).val() != '' || $('#exp_year_' + i).val() != '' || $('#exp_month_' + i).val() != '') {
                if ($('#namePrevEmp_' + i).val() == '') {
                    $('#prevEmpName_' + i).hide();
                    $('#namePrevEmp_error_' + i).show();
                }
                else
                    $('#namePrevEmp_error_' + i).hide();
                if ($('#nameAddPrevEmp_' + i).val() == '') {
                    $('#prevEmpAdd_' + i).hide();
                    $('#nameAddPrevEmp_error_' + i).show();
                }
                else
                    $('#nameAddPrevEmp_error_' + i).hide();
                if ($('#dateOfJoin_' + i).val() == '')
                    $('#dateOfJoin_error_' + i).show();
                else
                    $('#dateOfJoin_error_' + i).hide();
                if ($('#desigOnJoin_' + i).val() == '') {
                    $('#prevEmpDesigOnJoin_' + i).hide();
                    $('#desigOnJoin_error_' + i).show();
                }
                else
                    $('#desigOnJoin_error_' + i).hide();
                if ($('#salaryOnJoin_' + i).val() == '')
                    $('#salOnJoin_error_' + i).show();
                else
                    $('#salOnJoin_error_' + i).hide();
                if ($('#desigOnRel_' + i).val() == '') {
                    $('#prevEmpDesigOnRel_' + i).hide();
                    $('#desigOnRel_error_' + i).show();
                }
                else
                    $('#desigOnRel_error_' + i).hide();
                if ($('#salOnRel_' + i).val() == '')
                    $('#salOnRel_error_' + i).show();
                else
                    $('#salOnRel_error_' + i).hide();
                if ($('#exp_year_' + i).val() == '' || $('#exp_month_' + i).val() == '')
                    $('#exp_month_error_' + i).show();
                else
                    $('#exp_month_error_' + i).hide();
//                if ($('#exp_month_' + i).val() == '')
//                    $('#exp_month_error_' + i).show();
//                else
//                    $('#exp_month_error_' + i).hide();
                flagCount++;
            } else {
                if (res == 1) {
                    $('#namePrevEmp_error_' + i).show();
                    $('#nameAddPrevEmp_error_' + i).show();
                    $('#dateOfJoin_error_' + i).show();
                    $('#desigOnJoin_error_' + i).show();
                    $('#salOnJoin_error_' + i).show();
                    $('#desigOnRel_error_' + i).show();
                    $('#salOnRel_error_' + i).show();
                    $('#exp_year_error_' + i).show();
                    $('#exp_month_error_' + i).show();
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

function addRow_Certification() {
    if (certification_validation(1)) {
        var trVal = "'CER_TR'";
        var currentRow = $('#certification_recordCount').val();
        var rowId = parseInt($('#certification_recordCount').val()) + 1;
        $('#certification_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="CER_TR_' + rowId + '">';
        tr += '   <input type="hidden" name="certificationId" id="certificationId_' + rowId + '" value="" /> ';
        tr += '   <input type="hidden" name="CER_TR_deleted" id="CER_TR_deleted_' + rowId + '" value="0" /> ';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:100%" name="cert_qualification" id="cert_qualification_' + rowId + '" /><span style="color:red;display:none;" id="cert_qualification_error_' + rowId + '">*required</span>';
        tr += '<div style="color:red;display:none;" id = "cert_qualification_err_' + rowId + '">Only alphanumerics are allowed</div>';
        tr += '   </td>';
        tr += '   <td valign="top"><input style="width:100%" name="cert_year_of_passing" id="cert_year_of_passing_' + rowId + '" style="width:80px;" value="" type="text"><span style="color: red; display: none;" id="cert_year_of_passing_error_' + rowId + '">*required</span>';
        tr += '<div style="color:red;display:none;" id = "cert_year_of_passing_err_' + rowId + '">Enter valid year</div></td>';
        tr += '   <td valign="top">';
        tr += '	<input type="text" style="width:100%" name="cert_institution" id="cert_institution_' + rowId + '" /><span style="color:red;display:none;" id="cert_institution_error_' + rowId + '">*required</span>';
        tr += '<div style="color:red;display:none;" id = "cert_institution_err_' + rowId + '">Only alphanumerics are allowed</div>';
        tr += '   </td>';
        tr += '   <td valign="top"><input style="width:100%" name="cert_percentage" id="cert_percentage_' + rowId + '" style="width:80px;" type="text"><span style="color: red; display: none;" id="cert_percentage_error_' + rowId + '">*required</span>  ';
        tr += '<div style="color:red;display:none;" id = "cert_percentage_err_' + rowId + '">Enter valid percentage</div></td>';
        tr += '   <td valign="top"><input style="width:100%" name="cert_remarks" id="cert_remarks_' + rowId + '" style="width:200px;" type="text"><span style="color: red; display: none;" id="cert_remarks_error_' + rowId + '">*required</span>  ';
        tr += '<div style="color:red;display:none;" id = "cert_remarks_err_' + rowId + '">Only alphanumerics are allowed</div></td>';
        tr += '   <td valign="top">';
        tr += '       <input type="hidden" name="cert_attachmentXY" value="" id="cert_attachmentXY_' + rowId + '" >';
        tr += '       <input name="cert_attachment_' + rowId + '" id="cert_attachment_' + rowId + '" size="8" type="file"><br><span style="color: red; display: none;" id="cert_attachment_error_' + rowId + '">*required</span>';
        tr += '       <div id="cert_attachment_err_' + rowId + '" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '       <img src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr = tr.replace(/selected="selected"/gi, "");
        tr = tr.replace(/selected/gi, "");
        $('#CER_TR_' + currentRow).after(tr);
    }
}

function certification_validation(res) {
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#certification_recordCount').val()); i++) {
        if ($('#CER_TR_deleted_' + i).val() == 0) {
            if ($('#cert_qualification_' + i).val() != '' && $('#cert_year_of_passing_' + i).val() != '' && $('#cert_institution_' + i).val() != '' && $('#cert_percentage_' + i).val() != '') {
                $('#cert_qualification_error_' + i).hide();
                $('#cert_qualification_err_' + i).hide();
                $('#cert_year_of_passing_error_' + i).hide();
                $('#cert_year_of_passing_err_' + i).hide();
                $('#cert_institution_error_' + i).hide();
                $('#cert_institution_err_' + i).hide();
                $('#cert_percentage_error_' + i).hide();
                $('#cert_percentage_err_' + i).hide();
                if (!$('#cert_qualification_' + i).val().match(/^[a-zA-Z0-9,-. ]+$/)) {
                    $('#cert_qualification_err_' + i).show();
                    flagCount++;
                } else {
                    $('#cert_qualification_err_' + i).hide();
                }
                var currentYear = new Date().getFullYear();
                if ($('#cert_year_of_passing_' + i).val() > currentYear || $('#cert_year_of_passing_' + i).val() < 1900 || !$('#cert_year_of_passing_' + i).val().match(/^[0-9 ]+$/)) {
                    $('#cert_year_of_passing_err_' + i).show();
                    flagCount++;
                } else {
                    $('#cert_year_of_passing_err_' + i).hide();
                }
                if (!$('#cert_institution_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
                    $('#cert_institution_err_' + i).show();
                    flagCount++;
                } else {
                    $('#cert_institution_err_' + i).hide();
                }
                if (!$('#cert_percentage_' + i).val().match(/^[0-9.]+$/)) {
                    $('#cert_percentage_err_' + i).show();
                    flagCount++;
                } else {
                    $('#cert_percentage_err_' + i).hide();
                }
                if ($('#cert_attachmentXY_' + i).val() == '' && $('#cert_attachment_' + i).val() == '') {
                    $('#cert_attachment_error_' + i).show();
                    flagCount++;
                } else {
                    $('#cert_attachment_error_' + i).hide();
                }
            } else if ($('#cert_qualification_' + i).val() != '' || $('#cert_year_of_passing_' + i).val() != '' || $('#cert_institution_' + i).val() != '' || $('#cert_percentage_' + i).val() != '' || $('#cert_attachmentXY_' + i).val() != '') {
                if ($('#cert_qualification_' + i).val() == '') {
                    $('#cert_qualification_error_' + i).show();
                    $('#cert_qualification_err_' + i).hide();
                }
                else
                    $('#cert_qualification_error_' + i).hide();
                if ($('#cert_year_of_passing_' + i).val() == '') {
                    $('#cert_year_of_passing_error_' + i).show();
                    $('#cert_year_of_passing_err_' + i).hide();
                }
                else
                    $('#cert_year_of_passing_error_' + i).hide();
                if ($('#cert_institution_' + i).val() == '') {
                    $('#cert_institution_error_' + i).show();
                    $('#cert_institution_err_' + i).hide();
                }
                else
                    $('#cert_institution_error_' + i).hide();
                if ($('#cert_percentage_' + i).val() == '') {
                    $('#cert_percentage_error_' + i).show();
                    $('#cert_percentage_err_' + i).hide();
                }
                else
                    $('#cert_percentage_error_' + i).hide();
                if ($('#cert_attachmentXY_' + i).val() == '')
                    $('#cert_attachment_error_' + i).show();
                else
                    $('#cert_attachment_error_' + i).hide();
                flagCount++;
            } else {
                if (res == 1) {
                    $('#cert_qualification_error_' + i).show();
                    $('#cert_year_of_passing_error_' + i).show();
                    $('#cert_institution_error_' + i).show();
                    $('#cert_percentage_error_' + i).show();
                    $('#cert_attachment_error_' + i).show();
                    flagCount++;
                }
            }

            if ($('#cert_attachment_' + i).val() != '') {
                //alert($('#cert_attachment_' + i).val());
                var fileName = $('#cert_attachment_' + i).val();
                if (fileName != null) {
                    var dot = fileName.lastIndexOf(".");
                    var extension = fileName.substr(dot, fileName.length);
                    if (extension == '.zip' || extension == '.rar' || extension == '.jar' || extension == '.war') {
                        $('#cert_attachment_err_' + i).show();
                        flagCount++;
                    } else {
                        $('#cert_attachment_err_' + i).hide();
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

function addRow_Skill() {
    if (skill_validation(1)) {
        var trVal = "'SK_TR'";
        var currentRow = $('#skill_recordCount').val();
        var rowId = parseInt($('#skill_recordCount').val()) + 1;
        $('#skill_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="SK_TR_' + rowId + '">';
        tr += '   <input type="hidden" name="skillId" id="skillId_' + rowId + '" value="" /> ';
        tr += '   <input type="hidden" name="SK_TR_deleted" id="SK_TR_deleted_' + rowId + '" value="0" /> ';
        tr += '   <td valign="top">';
        tr += '	<select name="skill_category" id="skill_category_' + rowId + '" style="width:100%" >';
        tr += $('#skill_category_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="skill_category_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="stream" id="stream_' + rowId + '" onchange="changeSkill(this.value,' + rowId + ');" style="width:100%">';
        tr += $('#streamFirst').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="stream_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="skill" id="skill_' + rowId + '" style="width:100%">';
        tr += $('#skill_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="skill_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="skill_type" id="skill_type_' + rowId + '" style="width:100%">';
        tr += $('#skill_type_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="skill_type_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '	<select name="skill_year" id="skill_year_' + rowId + '" style="width:45%">';
        tr += $('#skill_year_1').html();
        tr += '	</select>';
        tr += '	<select name="skill_month" id="skill_month_' + rowId + '" style="width:45%">';
        tr += $('#skill_month_1').html();
        tr += '	</select>';
        tr += '	<span style="color:red;display:none;" id="skill_month_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top" align="center">';
        tr += '       <img src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow(' + trVal + ',' + rowId + ',0)" />';
        tr += '   </td>';
        tr += '</tr>';
        tr = tr.replace(/selected="selected"/gi, "");
        tr = tr.replace(/selected/gi, "");
        $('#SK_TR_' + currentRow).after(tr);
    }
}

function skill_validation(res) {
    var flagCount = 0;
    for (var i = 1; i <= parseInt($('#skill_recordCount').val()); i++) {
        if ($('#SK_TR_deleted_' + i).val() == 0) {
            if ($('#skill_category_' + i).val() != '' && $('#stream_' + i).val() != '' && $('#skill_' + i).val() != '' && $('#skill_type_' + i).val() != '' && $('#skill_year_' + i).val() != '' && $('#skill_month_' + i).val() != '') {
                $('#skill_category_error_' + i).hide();
                $('#stream_error_' + i).hide();
                $('#skill_error_' + i).hide();
                $('#skill_type_error_' + i).hide();
                $('#skill_month_error_' + i).hide();
            } else if ($('#skill_category_' + i).val() != '' || $('#stream_' + i).val() != '' || $('#skill_' + i).val() != '' || $('#skill_type_' + i).val() != '' || $('#skill_year_' + i).val() != '' || $('#skill_month_' + i).val() != '') {
                if ($('#skill_category_' + i).val() == '')
                    $('#skill_category_error_' + i).show();
                else
                    $('#skill_category_error_' + i).hide();
                if ($('#stream_' + i).val() == '')
                    $('#stream_error_' + i).show();
                else
                    $('#stream_error_' + i).hide();
                if ($('#skill_' + i).val() == '')
                    $('#skill_error_' + i).show();
                else
                    $('#skill_error_' + i).hide();
                if ($('#skill_type_' + i).val() == '')
                    $('#skill_type_error_' + i).show();
                else
                    $('#skill_type_error_' + i).hide();
                if ($('#skill_year_' + i).val() == '')
                    $('#skill_month_error_' + i).show();
                else
                    $('#skill_month_error_' + i).hide();
                if ($('#skill_month_' + i).val() == '')
                    $('#skill_month_error_' + i).show();
                else
                    $('#skill_month_error_' + i).hide();
                flagCount++;
            } else {
                if (res == 1) {
                    $('#skill_category_error_' + i).show();
                    $('#stream_error_' + i).show();
                    $('#skill_error_' + i).show();
                    $('#skill_type_error_' + i).show();
                    $('#skill_month_error_' + i).show();
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

function addRow_Education() {
    if (education_validation(1)) {
        var trVal = "'EDU_TR'";
        var currentRow = $('#education_recordCount').val();
        var rowId = parseInt($('#education_recordCount').val()) + 1;
        $('#education_recordCount').val(rowId);
        var tr = '';
        tr += '<tr id="EDU_TR_' + rowId + '">';
        tr += '   <input type="hidden" name="educationId" id="educationId_' + rowId + '" value="" /> ';
        tr += '   <input type="hidden" name="EDU_TR_deleted" id="EDU_TR_deleted_' + rowId + '" value="0" /> ';
        tr += '   <td valign="top">';
        tr += '	<select name="degree" id="degree_' + rowId + '" onchange="change(this.value,' + rowId + ');" style="width:100%">';
        tr += $('#degree_1').html();
        tr += '	</select>';
        tr += '       <span style="color:red;display:none;" id="degree_error_' + rowId + '">*required</span>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '       <span id="span1_qualification_' + rowId + '"><select name="qualification" id="qualification_' + rowId + '" style="width:100%">';
        tr += $('#qualificationFirst').html();
        tr += '	</select></span>';
        tr += '	<span style="display:none" id="span2_qualification_' + rowId + '" ><input type="text" name="qualification1" id="qualification1_' + rowId + '" style="width:100%" /></span>';
        tr += '       <span style="color:red;display:none;" id="qualification_error_' + rowId + '">*required</span>';
        tr += ' <div id = "qualificationErr_' + rowId + '" style="display:none;"><font style = "color: red;">Only Alpha Numerics are allowed</font></div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '       <input name="year_of_passing" id="year_of_passing_' + rowId + '" style="width:100%" value="" type="text" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,2,false);" maxlength="4" >';
        tr += '       <span style="color:red;display:none;" id="year_of_passing_error_' + rowId + '">*required</span>';
        tr += ' <div id = "yearOfPassing_err_' + rowId + '" style="display:none;"><font style = "color: red;">Enter valid Year</font></div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '       <span id="span1_institution_' + rowId + '"><select name="institution" id="institution_' + rowId + '" style="width:100%">'
        tr += $('#institutionFirst').html();
        tr += '	</select></span>';
        tr += '	<span style="display:none" id="span2_institution_' + rowId + '" ><input type="text" name="institution1" id="institution1_' + rowId + '" style="width:100%"/></span>';
        tr += '       <span style="color:red;display:none;" id="institution_error_' + rowId + '">*required</span>';
        tr += ' <div id = "institution_err_' + rowId + '" style = "display: none;"><font style = "color:red;">Only Alphanumerics are allowed</font></div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '       <span id="span1_university_' + rowId + '"><select name="university" id="university_' + rowId + '" style="width:100%">'
        tr += $('#universityFirst').html();
        tr += '	</select></span>';
        tr += '	<span style="display:none" id="span2_university_' + rowId + '" ><input type="text" name="university1" id="university1_' + rowId + '" style="width:100%"/></span>';
        tr += '       <span style="color:red;display:none;" id="university_error_' + rowId + '">*required</span>';
        tr += ' <div id = "university_err_' + rowId + '" style = "display: none;"><font style = "color:red;">Only Alphanumerics are allowed</font></div>';
        tr += '   </td>';
        tr += '   <td valign="top">';
        tr += '       <input name="percentage" id="percentage_' + rowId + '" style="width:100%" type="text" onkeypress="return blockNonNumbers(this, event, true, false);" onkeyup="return extractNumber(this,2,false);" maxlength="5" >';
        tr += '       <span style="color:red;display:none;" id="percentage_error_' + rowId + '">*required</span>';
        tr += '   </td>  ';
        tr += '   <td valign="top">';
        tr += '       <input name="remarks" id="remarks_' + rowId + '" style="width:100%" type="text">';
        tr += '   </td>  ';
        tr += '   <td valign="top">';
        tr += '       <input type="hidden" name="attachmentXY" id="attachmentXY_' + rowId + '" value="" >';
        tr += '       <input name="attachment_' + rowId + '" id="attachment_' + rowId + '" size="8" type="file">';
        tr += '       <span style="color:red;display:none;" id="attachment_error_' + rowId + '">*required</span>';
        tr += ' <div id="attachment_err_' + rowId + '" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>';
        tr += '   </td>';
        if (rowId > 2) {
            tr += '   <td valign="top">';
            tr += '       <img src="' + $('#base_path').val() + '/css/images/delete-icon.png" onclick="deleteRow(' + trVal + ',' + rowId + ',0)" />';
            tr += '   </td>';
        }
        tr += '</tr>';
        tr = tr.replace(/selected="selected"/gi, "");
        tr = tr.replace(/selected/gi, "");
        $('#EDU_TR_' + currentRow).after(tr);
    }
}

function education_validation(res) {
    var flagCount = 0;
    var eduDegree = "";
    for (var i = 1; i <= parseInt($('#education_recordCount').val()); i++) {
//        alert("record"+i);
        if ($('#EDU_TR_deleted_' + i).val() == 0) {
//            alert("record"+i);
            if ($('#degree_' + i).val() != '') {
                if (i == parseInt($('#education_recordCount').val())) {
//                    alert("inside if");
                    eduDegree += $('#degree_' + i).val();
//                    alert(eduDegree);
                }
                else {
//                    alert("inside else");
                    eduDegree += $('#degree_' + i).val() + ",";
//                    alert(eduDegree);
                }
            }
            if ($('#degree_' + i).val() != '' && $('#year_of_passing_' + i).val() != '' && $('#percentage_' + i).val() != '') {

                $('#degree_error_' + i).hide();
                $('#year_of_passing_error_' + i).hide();
                $('#percentage_error_' + i).hide();
                $('#attachment_error_' + i).hide();
                var year = new Date().getFullYear();
                if ($('#year_of_passing_' + i).val() > year || $('#year_of_passing_' + i).val() < 1900) {
                    $('#yearOfPassing_err_' + i).show();
                    flagCount++;
                } else {
                    $('#yearOfPassing_err_' + i).hide();
                }
                if ($('#degree_' + i).val() == 10 || $('#degree_' + i).val() == 12) {
                    if ($('#qualification1_' + i).val() == '' && $('#degree_' + i).val() == 12) {
                        $('#qualificationErr_' + i).hide();
                        $('#qualification_error_' + i).show();
                        flagCount++;
                    } else if (!$('#qualification1_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
                        $('#qualification_error_' + i).hide();
                        $('#qualificationErr_' + i).show();
                        flagCount++;
                    } else {
                        $('#qualification_error_' + i).hide();
                        $('#qualificationErr_' + i).hide();
                    }
                    if ($('#institution1_' + i).val() == '') {
                        $('#institution_err_' + i).hide();
                        $('#institution_error_' + i).show();
                        flagCount++;
                    } else if (!$('#institution1_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
                        $('#institution_error_' + i).hide();
                        $('#institution_err_' + i).show();
                        flagCount++;
                    } else {
                        $('#institution_err_' + i).hide();
                        $('#institution_error_' + i).hide();
                    }
                    if ($('#university1_' + i).val() == '') {
                        $('#university_err_' + i).hide();
                        $('#university_error_' + i).show();
                        flagCount++;
                    } else if (!$('#university1_' + i).val().match(/^[a-zA-Z0-9-., ]+$/)) {
                        $('#university_error_' + i).hide();
                        $('#university_err_' + i).show();
                        flagCount++;
                    }
                    else {
                        $('#university_err_' + i).hide();
                        $('#university_error_' + i).hide();
                    }
                } else {
                    if ($('#qualification_' + i).val() == '') {
                        $('#qualification_error_' + i).show();
                        flagCount++;
                    } else
                        $('#qualification_error_' + i).hide();
                    //if( $('#institution_'+i).val() == '') { $('#institution_error_'+i).show(); flagCount++; } else $('#institution_error_'+i).hide();
                    if ($('#university_' + i).val() == '') {
                        $('#university_error_' + i).show();
                        flagCount++;
                    } else
                        $('#university_error_' + i).hide();
                }
                if ($('#attachmentXY_' + i).val() == '' && $('#attachment_' + i).val() == '') {
                    $('#attachment_err_' + i).hide();
                    $('#attachment_error_' + i).show();
                    flagCount++;
                } else {
                    $('#attachment_error_' + i).hide();
                }
                if (parseInt($('#percentage_' + i).val()) > 100) {
                    alert("Not a valid percentage");
                    $('#percentage_' + i).focus();
                    $('#percentage_' + i).select();
                    return false;
                }
                var eduDegreeArr = eduDegree.split(",");
                var len = eduDegreeArr.length;
                for (k = 0; k < len; k++) {
                    if (parseInt(k) + 1 != i) {
                        if (eduDegreeArr[k] == $('#degree_' + i).val() && $('#degree_' + i).val() != 'ug' && $('#degree_' + i).val() != 'pg' && $('#degree_' + i).val() != 'd') {
                            alert("You should not enter X Std / +2 more than once");
                            return false;
                        }
                    }
                }
            } else if ($('#degree_' + i).val() != '' || $('#qualification_' + i).val() != '' || $('#year_of_passing_' + i).val() != '' || $('#institution_' + i).val() != '' || $('#university_' + i).val() != '' || $('#percentage_' + i).val() != '' || $('#attachment_' + i).val() != '') {

                if ($('#degree_' + i).val() == '')
                    $('#degree_error_' + i).show();
                else
                    $('#degree_error_' + i).hide();
                if ($('#year_of_passing_' + i).val() == '')
                    $('#year_of_passing_error_' + i).show();
                else
                    $('#year_of_passing_error_' + i).hide();
                if ($('#percentage_' + i).val() == '')
                    $('#percentage_error_' + i).show();
                else
                    $('#percentage_error_' + i).hide();
                if ($('#attachment_' + i).val() == '') {

                    $('#attachment_error_' + i).show();
                }
                else
                    $('#attachment_error_' + i).hide();
                if ($('#degree_' + i).val() == 10 || $('#degree_' + i).val() == 12) {
                    if ($('#qualification1_' + i).val() == '')
                        $('#qualification_error_' + i).show();
                    else
                        $('#qualification_error_' + i).hide();
                    if ($('#institution1_' + i).val() == '')
                        $('#institution_error_' + i).show();
                    else
                        $('#institution_error_' + i).hide();
                    if ($('#university1_' + i).val() == '')
                        $('#university_error_' + i).show();
                    else
                        $('#university_error_' + i).hide();
                } else {
                    if ($('#qualification_' + i).val() == '')
                        $('#qualification_error_' + i).show();
                    else
                        $('#qualification_error_' + i).hide();
                    //if( $('#institution_'+i).val() == '') $('#institution_error_'+i).show(); else $('#institution_error_'+i).hide();
                    if ($('#university_' + i).val() == '')
                        $('#university_error_' + i).show();
                    else
                        $('#university_error_' + i).hide();
                }
                flagCount++;
            } else {
                if (res == 1) {
                    $('#degree_error_' + i).show();
                    $('#qualification_error_' + i).show();
                    $('#year_of_passing_error_' + i).show();
                    //$('#institution_error_'+i).show();
                    $('#university_error_' + i).show();
                    $('#percentage_error_' + i).show();
                    $('#attachment_error_' + i).show();
                    flagCount++;
                }
            }

            if ($('#attachment_' + i).val() != '') {
//                alert("attachment");
                var fileName = $('#attachment_' + i).val();
                if (fileName != null) {
                    var dot = fileName.lastIndexOf(".");
                    var extension = fileName.substr(dot, fileName.length);
//                    alert(extension);
                    if (extension == '.zip' || extension == '.rar' || extension == '.jar' || extension == '.war') {
//                        alert("error" + i);
                        $('#attachment_err_' + i).show();
                        flagCount++;
                    } else {
                        $('#attachment_err_' + i).hide();
                    }
                }
            }

        }
    }
    $('#eduDegree').val(eduDegree);
    if (flagCount == 0)
        return true;
    else
        return false;
}

function change(val, row_id) {
    if (val == 10 || val == 12) {
        $('#span1_qualification_' + row_id).hide();
        $('#span2_qualification_' + row_id).show();
        $('#span1_institution_' + row_id).hide();
        $('#span2_institution_' + row_id).show();
        $('#span1_university_' + row_id).hide();
        $('#span2_university_' + row_id).show();
    } else {
        $('#span1_qualification_' + row_id).show();
        $('#span2_qualification_' + row_id).hide();
        $('#span1_institution_' + row_id).show();
        $('#span2_institution_' + row_id).hide();
        $('#span1_university_' + row_id).show();
        $('#span2_university_' + row_id).hide();
    }
}

function deleteRow(tr, row, val) {

    if (val == 1) {
        var con = confirm("Are you sure want to delete ?");
        if (con) {
            $('#' + tr + '_' + row).hide();
            $('#' + tr + '_deleted_' + row).val(1);
//            $('#education_recordCount').val(row-1);
            return true;
        } else {
            return false;
        }
    } else {
        $('#' + tr + '_' + row).hide();
        $('#' + tr + '_deleted_' + row).val(1);
//        alert($('#' + tr + '_deleted_' + row).val());
//        $('#education_recordCount').val(row-1);
        return true;
    }
}

function changeSkill(id, row_id) {
    $.ajax({
        type: 'POST',
        url: 'changeskill.htm',
        data: 'selectedstream=' + id,
        success: function(ajaxObj) {
            var arr = ajaxObj.split(":");
            var mySelect = document.getElementById("skill_" + row_id);
            mySelect.options.length = 0;
            mySelect.options[0] = new Option("--Select Skill--", "");
            var count = 1;
            for (var i = 0; i < arr.length; i++) {
                var out = arr[i].split(",");
                if (out != '') {
                    if ($('#selected_skill_' + row_id).val() == out[0]) {
                        document.getElementById("skill_" + row_id).options[count + i] = new Option(out[1], out[0]);
                        document.getElementById("skill_" + row_id).options[count + i].selected = true;
                    } else {
                        document.getElementById("skill_" + row_id).options[count + i] = new Option(out[1], out[0]);
                    }
                }
            }
        },
        async: false
    });

}

function form_validate() { // val '1' => Mandatory '0' => Non Mandatory
    var flagCount = 0;
    if (certification_validation(0) && skill_validation(1) && employer_validation(0) && education_validation(1)) {
        var eduDegree = $('#eduDegree').val();
        if (eduDegree.search('10') >= 0 && (eduDegree.search('12') >= 0 || eduDegree.search('d') >= 0)) {
            //return true;
        } else {
            alert("X Std & Either +2 Or Diploma are mandatory");
            //return false;
            flagCount++;
        }
    } else {
        flagCount++;
        //return false;
    }
    var emp_exp = 0;
    var skill_exp = 0;
    //alert(parseInt($('#employer_recordCount').val()));
    for (var i = 1; i <= parseInt($('#employer_recordCount').val()); i++) {
        if ($('#EMP_TR_deleted_' + i).val() == 0) {
            var x = parseInt($('#exp_year_' + i).val()) * 12;
            //alert(x);
            var y = parseInt($('#exp_month_' + i).val());
            //alert(y);
            var z = x + y;
            // alert(z);
            emp_exp = emp_exp + z;
        }
    }
    //alert(emp_exp);
    //alert(parseInt($('#skill_recordCount').val()));
    for (var i = 1; i <= parseInt($('#skill_recordCount').val()); i++) {
        if ($('#SK_TR_deleted_' + i).val() == 0) {
            var x = parseInt($('#skill_year_' + i).val()) * 12;
            //alert(x);
            var y = parseInt($('#skill_month_' + i).val());
            //alert(y);
            var z = x + y;
            //alert(z);
            skill_exp = skill_exp + z;
        }
    }
    //alert(skill_exp);

//    if (skill_exp > emp_exp) {
//        alert("The total experience should not exceed the employee actual experience.");
//        flagCount++;
//    }

    if (flagCount > 0) {
        return false;
    } else {
        return true;
    }

}