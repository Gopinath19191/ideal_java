function GuestBooking(val) {
    if (val == "Y") {
        $('#guestLabel').show();
        $('#guestOutput').show();
    } else {
        $('#guestLabel').hide();
        $('#guestOutput').hide();
    }
}
function approverSubmit() {
    var flag = true;
    if ($('#approverModule').val() == 606) {
        if ($('#printAction').val() != Print) {
            if ($('#amount_deposited').val() == "") {
                $('#amount_deposited_error').html("Please enter the amount");
                flag = false;
            } else {
                $('#amount_deposited_error').html("");
            }
            if ($('#currency_type').val() == "") {
                $('#currency_type_error').html("Please select currency");
                flag = false;
            } else {
                $('#currency_type_error').html("");
            }
        }
    }
    if ($('#remarks').val() == "") {
        $('#remarks_error').html("Please enter the remarks");
        flag = false;
    } else {
        $('#remarks_error').html("");
        if ($('#action').val() == "reject") {
            var con = confirm("Are you sure wnat to send back this Travel ? ");
            if (!con)
                flag = false;
        }
    }
    if (flag) {
        $("#submitDiv").hide();
    }
    return flag;
}

function calculateDateDiff() {
    var date1 = $('#travel_start_date').val();
    var date2 = $('#travel_end_date').val();
    if (date2 != '' && date1 != '') {
        date1 = date1.split('-');
        date2 = date2.split('-');
        firstDate = new Date(date1[2], date1[1], date1[0]);
        secondDate = new Date(date2[2], date2[1], date2[0]);
        var oneDay = 1000 * 60 * 60 * 24;
        var diffDays = Math.round(Math.abs((firstDate.getTime() - secondDate.getTime()) / (oneDay)));
        if (diffDays > 30)
            $('#travel_term').val("Long Term");
        else
            $('#travel_term').val("Short Term");
    }
}

function getDetailsForApproval(tpId, url) {
    $("#dashboardTpId").val(tpId);
}

function changeTab(tabValue, tab) {
    $(".ticket_tab").removeClass("active_tab");
    $(tab).addClass("active_tab");
    if (tabValue == "Ticket") {
        $('#TicketTab').show();
        $('#AttachmentTab').hide();
        $('#HotelTab').hide();
        $('#ConveyanceTab').hide();
        $('#VisaTab').hide();
    } else if (tabValue == "Hotel") {
        $('#HotelTab').show();
        $('#AttachmentTab').hide();
        $('#TicketTab').hide();
        $('#ConveyanceTab').hide();
        $('#VisaTab').hide();
    } else if (tabValue == "Conveyance") {
        $('#ConveyanceTab').show();
        $('#AttachmentTab').hide();
        $('#TicketTab').hide();
        $('#HotelTab').hide();
        $('#VisaTab').hide();
    } else if (tabValue == "Attachment") {
        $('#AttachmentTab').show();
        $('#HotelTab').hide();
        $('#TicketTab').hide();
        $('#ConveyanceTab').hide();
        $('#VisaTab').hide();
    } else {
        $('#VisaTab').show();
        $('#AttachmentTab').hide();
        $('#HotelTab').hide();
        $('#TicketTab').hide();
        $('#ConveyanceTab').hide();
    }
}

var myAutoComplete = null;
var myDatePick = null;
$(document).ready(function() {
    var base_path = $('#base_path').val();
    $("#submit_btn").click(function() {
        $('#action').val('Submitted');
    });
    $("#save_btn").click(function() {
        $('#action').val('Saved');
    });
    $("#cancel_btn").click(function() {
        $('#action').val('Cancel');
    });
    //Approval Page
    $("#rejectAction").click(function() {
        $('#action').val('reject');
    });

    myAutoComplete = function() {
        $(".autobox").autocomplete(base_path + "/ajaxsearch.htm?type=city", {
            minChars: 2,
            matchContains: true
        });
        $(".autobox").result(function(event, data) {
            if (data) {
                $(this).parent().find(".hiddenbox").val(data[1]);
            }
        });
    }
    myDatePick = function() {
        $('.datePick').datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'dd-mm-yy',
            defaultDate: new Date()
        });
    }
    myAutoComplete();
    myDatePick();
});

function addTicket(rowObject, id) {
    var base_path = $('#base_path').val();
    var theRow = $(rowObject).parent().parent();
    var rowData = theRow.html();
    var ticketCount = $('#ticketCount').val();
    cnt = parseInt(ticketCount) + 1;
    $('#ticketCount').val(cnt);
    rowData = rowData.replace("ticket_id_" + id, "ticket_id_" + cnt);
    rowData = rowData.replace("from_country_" + id, "from_country_" + cnt);
    rowData = rowData.replace("to_country_" + id, "to_country_" + cnt);
    rowData = rowData.replace("from_city_" + id, "from_city_" + cnt);
    rowData = rowData.replace("to_city_" + id, "to_city_" + cnt);
    rowData = rowData.replace("from_city_id_" + id, "from_city_id_" + cnt);
    rowData = rowData.replace("to_city_id_" + id, "to_city_id_" + cnt);
    rowData = rowData.replace("travel_date_" + id, "travel_date_" + cnt);
    rowData = rowData.replace("travel_preference_" + id, "travel_preference_" + cnt);
    rowData = rowData.replace("travel_mode_" + id, "travel_mode_" + cnt);
    rowData = rowData.replace("ticket_remarks_" + id, "ticket_remarks_" + cnt);
    rowData = rowData.replace("from_country_error_" + id, "from_country_error_" + cnt);
    rowData = rowData.replace("to_country_error_" + id, "to_country_error_" + cnt);
    rowData = rowData.replace("from_city_error_" + id, "from_city_error_" + cnt);
    rowData = rowData.replace("to_city_error_" + id, "to_city_error_" + cnt);
    rowData = rowData.replace("travel_date_error_" + id, "travel_date_error_" + cnt);
    rowData = rowData.replace("travel_preference_error_" + id, "travel_preference_error_" + cnt);
    rowData = rowData.replace("travel_mode_error_" + id, "travel_mode_error_" + cnt);
    rowData = rowData.replace("ticket_remarks_error_" + id, "ticket_remarks_error_" + cnt);
    rowData = rowData.replace("ticketdeleted_" + id, "ticketdeleted_" + cnt);
    rowData = rowData.replace("ticket_actionItems_" + id, "ticket_actionItems_" + cnt);
    rowData = rowData.replace("addTicket(this," + id, "addTicket(this," + cnt);
    rowData = rowData.replace("hasDatepicker", "");
    rowData = rowData.replace('<img onclick="return removeTicketTR(' + id + ')" src="' + base_path + '/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">', '');
    rowData = rowData.replace('<IMG style="CURSOR: pointer" title=Remove onclick="return removeTicketTR(' + id + ')" alt=Remove src="' + base_path + '/css/images/tm_delete.png">', '');
    rowData = rowData.replace('<a href="javascript:;" onclick="removeRow(this)"><img src="' + base_path + '/css/images/tm_delete.png"></a>', '');
    rowData = rowData.replace('<A onclick=removeRow(this) href="javascript:;"><IMG src="' + base_path + '/css/images/tm_delete.png"></A>', '');
    $(theRow).after("<tr id=tr_ticket_" + cnt + ">" + rowData + "</tr>");
    $("#ticket_id_" + cnt).val('');
    $("#from_country_" + cnt).val('');
    $("#to_country_" + cnt).val('');
    $("#from_city_" + cnt).val('');
    $("#to_city_" + cnt).val('');
    $("#from_city_id_" + cnt).val('');
    $("#to_city_id_" + cnt).val('');
    $("#travel_date_" + cnt).val('');
    $("#travel_preference_" + cnt).val('');
    $("#travel_mode_" + cnt).val('');
    $("#ticket_remarks_" + cnt).val('');
    $("#from_country_error_" + cnt).html("");
    $("#to_country_error_" + cnt).html("");
    $("#from_city_error_" + cnt).html("");
    $("#to_city_error_" + cnt).html("");
    $("#travel_date_error_" + cnt).html("");
    $("#travel_preference_error_" + cnt).html("");
    $("#travel_mode_error_" + cnt).html("");
    $("#ticket_remarks_error_" + cnt).html("");
    $("#ticket_actionItems_" + cnt).append('<a href="javascript:;" onClick="removeRow(this)"><img src="' + base_path + '/css/images/tm_delete.png" /></a>');
    myDatePick();
    myAutoComplete();
}

function addHotel(rowObject, id) {
    var base_path = $('#base_path').val();
    var theRow = $(rowObject).parent().parent();
    var rowData = theRow.html();
    var hotelCount = $('#hotelCount').val();
    cnt = parseInt(hotelCount) + 1;
    $('#hotelCount').val(cnt);
    rowData = rowData.replace("hotel_id_" + id, "hotel_id_" + cnt);
    rowData = rowData.replace("country_" + id, "country_" + cnt);
    rowData = rowData.replace("city_" + id, "city_" + cnt);
    rowData = rowData.replace("city_id_" + id, "city_id_" + cnt);
    rowData = rowData.replace("from_date_" + id, "from_date_" + cnt);
    rowData = rowData.replace("to_date_" + id, "to_date_" + cnt);
    rowData = rowData.replace("location_" + id, "location_" + cnt);
    rowData = rowData.replace("hotel_remarks_" + id, "hotel_remarks_" + cnt);
    rowData = rowData.replace("country_errror_" + id, "country_errror_" + cnt);
    rowData = rowData.replace("city_errror_" + id, "city_errror_" + cnt);
    rowData = rowData.replace("from_date_errror_" + id, "from_date_errror_" + cnt);
    rowData = rowData.replace("to_date_errror_" + id, "to_date_" + cnt);
    rowData = rowData.replace("location_errror_" + id, "location_errror_" + cnt);
    rowData = rowData.replace("hotel_remarks_errror_" + id, "hotel_remarks_errror_" + cnt);
    rowData = rowData.replace("hoteldeleted_" + id, "hoteldeleted_" + cnt);
    rowData = rowData.replace("hotel_actionItems_" + id, "hotel_actionItems_" + cnt);
    rowData = rowData.replace("addHotel(this," + id, "addHotel(this," + cnt);
    rowData = rowData.replace("hasDatepicker", "");
    rowData = rowData.replace("hasDatepicker", "");
    rowData = rowData.replace('<img onclick="return removeHotelTR(' + id + ')" src="' + base_path + '/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">', '');
    rowData = rowData.replace('<IMG style="CURSOR: pointer" title=Remove onclick="return removeHotelTR(' + id + ')" alt=Remove src="' + base_path + '/css/images/tm_delete.png">', '');
    rowData = rowData.replace('<a href="javascript:;" onclick="removeRow(this)"><img src="' + base_path + '/css/images/tm_delete.png"></a>', '');
    rowData = rowData.replace('<A onclick=removeRow(this) href="javascript:;"><IMG src="' + base_path + '/css/images/tm_delete.png"></A>', '');
    $(theRow).after("<tr id=tr_hotel_" + cnt + ">" + rowData + "</tr>");
    $("#hotel_id_" + cnt).val('');
    $("#country_" + cnt).val('');
    $("#city_" + cnt).val('');
    $("#city_id_" + cnt).val('');
    $("#from_date_" + cnt).val('');
    $("#to_date_" + cnt).val('');
    $("#location_" + cnt).val('');
    $("#hotel_remarks_" + cnt).val('');
    $("#country_errror_" + cnt).html("");
    $("#city_errror_" + cnt).html("");
    $("#from_date_errror_" + cnt).html("");
    $("#to_date_errror_" + cnt).html("");
    $("#location_errror_" + cnt).html("");
    $("#hotel_remarks_errror_" + cnt).html("");
    $("#hotel_actionItems_" + cnt).append('<a href="javascript:;" onClick="removeRow(this)"><img src="' + base_path + '/css/images/tm_delete.png" /></a>');
    myDatePick();
    myAutoComplete();
}

function addConveyance(rowObject, id) {
    var base_path = $('#base_path').val();
    var theRow = $(rowObject).parent().parent();
    var rowData = theRow.html();
    var conveyanceCount = $('#conveyanceCount').val();
    cnt = parseInt(conveyanceCount) + 1;
    $('#conveyanceCount').val(cnt);
    rowData = rowData.replace("conveyance_id_" + id, "conveyance_id_" + cnt);
    rowData = rowData.replace("conveyance_country_" + id, "conveyance_country_" + cnt);
    rowData = rowData.replace("conveyance_city_id_" + id, "conveyance_city_id_" + cnt);
    rowData = rowData.replace("conveyance_city_" + id, "conveyance_city_" + cnt);
    rowData = rowData.replace("pickup_" + id, "pickup_" + cnt);
    rowData = rowData.replace("dropto_" + id, "dropto_" + cnt);
    rowData = rowData.replace("start_date_" + id, "start_date_" + cnt);
    rowData = rowData.replace("end_date_" + id, "end_date_" + cnt);
    rowData = rowData.replace("time_hr_" + id, "time_hr_" + cnt);
    rowData = rowData.replace("time_min_" + id, "time_min_" + cnt);
    rowData = rowData.replace("conveyance_remarks_" + id, "conveyance_remarks_" + cnt);
    rowData = rowData.replace("conveyance_country_error_" + id, "conveyance_country_error_" + cnt);
    rowData = rowData.replace("conveyance_city_error_" + id, "conveyance_city_error_" + cnt);
    rowData = rowData.replace("pickup_error_" + id, "pickup_error_" + cnt);
    rowData = rowData.replace("dropto_error_" + id, "dropto_error_" + cnt);
    rowData = rowData.replace("start_date_error_" + id, "start_date_error_" + cnt);
    rowData = rowData.replace("end_date_error_" + id, "end_date_error_" + cnt);
    rowData = rowData.replace("travel_time_error_" + id, "travel_time_error_" + cnt);
    rowData = rowData.replace("conveyance_remarks_error_" + id, "conveyance_remarks_error_" + cnt);
    rowData = rowData.replace("conveyancedeleted_" + id, "conveyancedeleted_" + cnt);
    rowData = rowData.replace("conveyance_actionItems_" + id, "conveyance_actionItems_" + cnt);
    rowData = rowData.replace("addConveyance(this," + id, "addConveyance(this," + cnt);
    rowData = rowData.replace("hasDatepicker", "");
    rowData = rowData.replace("hasDatepicker", "");
    rowData = rowData.replace('<img onclick="return removeConveyanceTR(' + id + ')" src="' + base_path + '/css/images/tm_delete.png" alt="Remove" title="Remove" style="cursor:pointer;">', '');
    rowData = rowData.replace('<IMG style="CURSOR: pointer" title=Remove onclick="return removeConveyanceTR(' + id + ')" alt=Remove src="' + base_path + '/css/images/tm_delete.png">', '');
    rowData = rowData.replace('<a href="javascript:;" onclick="removeRow(this)"><img src="' + base_path + '/css/images/tm_delete.png"></a>', '');
    rowData = rowData.replace('<A onclick=removeRow(this) href="javascript:;"><IMG src="' + base_path + '/css/images/tm_delete.png"></A>', '');
    $(theRow).after("<tr id=tr_conveyance_" + cnt + ">" + rowData + "</tr>");
    $("#conveyance_id_" + cnt).val('');
    $("#conveyance_country_" + cnt).val('');
    $("#conveyance_city_id_" + cnt).val('');
    $("#conveyance_city_" + cnt).val('');
    $("#pickup_" + cnt).val('');
    $("#dropto_" + cnt).val('');
    $("#start_date_" + cnt).val('');
    $("#end_date_" + cnt).val('');
    $("#time_hr_" + cnt).val('');
    $("#time_min_" + cnt).val('');
    $("#conveyance_remarks_" + cnt).val('');
    $("#conveyance_country_error_" + cnt).html("");
    $("#conveyance_city_error_" + cnt).html("");
    $("#pickup_error_" + cnt).html("");
    $("#dropto_error_" + cnt).html("");
    $("#start_date_error_" + cnt).html("");
    $("#end_date_error_" + cnt).html("");
    $("#travel_time_error_" + cnt).html("");
    $("#conveyance_remarks_error_" + cnt).html("");
    $("#conveyance_actionItems_" + cnt).append('<a href="javascript:;" onClick="removeRow(this)"><img src="' + base_path + '/css/images/tm_delete.png" /></a>');
    myDatePick();
    myAutoComplete();
}

function addAttachment(rowObject) {
    var base_path = $('#base_path').val();
    var theRow = $(rowObject).parent().parent();
    var rowData = "";
    var attachmentCount = $('#attachmentCount').val();
    cnt = parseInt(attachmentCount) + 1;
    $('#attachmentCount').val(cnt);
    rowData += '<input type="hidden" value="0" name="attachmentdeleted_' + cnt + '" id="attachmentdeleted_' + cnt + '" />';
    rowData += '<td align="center"><input type="file" name="attach_doc_' + cnt + '" id="attach_doc_' + cnt + '" class="filebox" size="20" ></td>';
    rowData += '<td align="center">';
    rowData += '<img onClick="addAttachment(this)" src="' + base_path + '/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
    rowData += '&nbsp;<a href="javascript:;" onClick="deleteRow(' + cnt + ')"><img src="' + base_path + '/css/images/tm_delete.png" /></a>';
    rowData += '</td>';
    $(theRow).after("<tr id=tr_attachment_" + cnt + ">" + rowData + "</tr>");
}

function deleteRow(rowId) {
    $('#attachmentdeleted_' + rowId).val(1);
    $('#tr_attachment_' + rowId).remove();
}

function deleteAttachment(rowId) {
    var con = confirm("Are you sure want to delete ?")
    if (con) {
        $('#attach_del_' + rowId).val(1);
        $('#attachTR_' + rowId).hide();
    }
}

function removeRow(rowObject) {
    var row = $(rowObject).parent().parent();
    $(row).remove();
    return false;
}

function loadProject(value) {
    var path = $('#base_path').val();
    if (value == -1) {
        $('#othersRow').show();
        $("#customerOthersLabel").css('visibility', 'visible');
        $("#customerOthers").css('visibility', 'visible');
    } else {
        $('#othersRow').hide();
        $("#customerOthersLabel").css('visibility', 'hidden');
        $("#customerOthers").css('visibility', 'hidden');
    }
    if (value != "") {
        jQuery.ajax({
            url: path + '/loadProject.htm',
            type: "POST",
            dataType: "text",
            async: false,
            data: ({
                customer_id: value
            }),
            success: function(ajaxObj) {
                var arr = ajaxObj.split(":");
                var mySelect = document.getElementById("project_id");
                mySelect.options.length = 0;
                mySelect.options[0] = new Option("--select project--", "");
                var count = 1;
                for (var i = 0; i < arr.length; i++) {
                    var out = arr[i].split(",");
                    if (out != '') {
                        document.getElementById("project_id").options[count + i] = new Option(out[1], out[0]);
                        document.getElementById("project_id").options[count + i].title = out[1];
                    }
                }
            }
        });
    }
}

function showProjectOthers(value) {
    if (value == -1) {
        $('#othersRow').show();
        $("#projectOthersLabel").css('visibility', 'visible');
        $("#projectOthers").css('visibility', 'visible');
    } else {
        $("#projectOthersLabel").css('visibility', 'hidden');
        $("#projectOthers").css('visibility', 'hidden');
        if ($('#customer_id').val() != -1) {
            $('#othersRow').hide();
        }
    }
}

function removeTicketTR(val) {
    var con = confirm("Are you sure want to delete?");
    if (con) {
        $('#ticketdeleted_' + val).val("1");
        $('#tr_ticket_' + val).hide();
    }
    return false;
}

function removeHotelTR(val) {
    var con = confirm("Are you sure want to delete?");
    if (con) {
        $('#hoteldeleted_' + val).val("1");
        $('#tr_hotel_' + val).hide();
    }
    return false;
}

function removeConveyanceTR(val) {
    var con = confirm("Are you sure want to delete?");
    if (con) {
        $('#conveyancedeleted_' + val).val("1");
        $('#tr_conveyance_' + val).hide();
    }
    return false;
}

function validateTravelSubmit() {
    var flag = true;

    if ($("#action").val() == 'Submitted' || $("#action").val() == 'Saved') {

        if ($('#travel_requested_date').val() == "") {
            $('#travel_requested_date_error').html("Please enter Request Date");
            flag = false;
        } else {
            $('#travel_requested_date_error').html("");
        }
        if ($('#travel_billable').val() == "") {
            $('#travel_billable_error').html("Please select Billable");
            flag = false;
        } else {
            if ($('#travel_billable').val() == "Y") {
                if ($('#customer_id').val() == "") {
                    $('#customer_id_error').html("Please select Customer");
                    flag = false;
                } else {
                    $('#customer_id_error').html("");
                }
                if ($('#customer_id').val() == "-1" && $('#customer_others').val() == "") {
                    $('#customer_others_error').html("Please enter Customer");
                    flag = false;
                } else {
                    $('#customer_others_error').html("");
                }
                if ($('#project_id').val() == "") {
                    $('#project_id_error').html("Please select Project");
                    flag = false;
                } else {
                    $('#project_id_error').html("");
                }
                if ($('#project_id').val() == "-1" && $('#project_others').val() == "") {
                    $('#project_others_error').html("Please enter Project");
                    flag = false;
                } else {
                    $('#project_others_error').html("");
                }
            }
            $('#travel_billable_error').html("");
        }
        if ($('#client_reimbursable').val() == "") {
            $('#client_reimbursable_error').html("Please select Reimbursable");
            flag = false;
        } else {
            $('#client_reimbursable_error').html("");
        }
        if ($('#travel_purpose').val() == "") {
            $('#travel_purpose_error').html("Please enter Travel Purpose");
            flag = false;
        } else {
            $('#travel_purpose_error').html("");
        }
        if ($('#system').val() != 'G') {
            if ($('#mobileNo').val() == "") {
                $('#mobileNo_error').html("Please enter Contact No.");
                flag = false;
            } else {
                $('#mobileNo_error').html("");
            }
        }
        if ($('#travel_start_date').val() == "") {
            $('#travel_start_date_error').html("Please select Travel Start Date");
            flag = false;
        } else if ($('#travel_start_date').val() != "" && $('#travel_end_date').val() != "") {
            var start_date = $('#travel_start_date').val();
            var start_day = start_date.split("-")[0];
            var start_month = start_date.split("-")[1];
            var start_year = start_date.split("-")[2];
            //alert(start_day+" "+start_month+" "+start_year);
            var end_date = $('#travel_end_date').val();
            var end_day = end_date.split("-")[0];
            var end_month = end_date.split("-")[1];
            var end_year = end_date.split("-")[2];
            if (start_year > end_year) {
                $('#travel_start_date_error').html("Travel start date should not be greater than travel end date.");
                flag = false;
            } else if (start_month > end_month && start_year == end_year) {
                $('#travel_start_date_error').html("Travel start date should not be greater than travel end date.");
                flag = false;
            } else if (start_day > end_day && start_month == end_month && start_year == end_year) {
                $('#travel_start_date_error').html("Travel start date should not be greater than travel end date.");
                flag = false;
            }
        } else {
            $('#travel_start_date_error').html("");
        }
        if ($('#travel_end_date').val() == "") {
            $('#travel_end_date_error').html("Please select Travel End Date");
            flag = false;
        } else {
            $('#travel_end_date_error').html("");
        }
        if ($('#advanceRequired').val() != "" && $('#currency_type').val() == "") {
            $('#currency_type_error').html("Please select Currency Type");
            flag = false;
        } else {
            $('#currency_type_error').html("");
        }
        if ($('#guest_booking').val() == "") {
            $('#guest_booking_error').html("Please select guest booking");
            flag = false;
        } else {
            $('#guest_booking_error').html("");
            if ($('#guest_booking').val() == "Y" && $('#guest_name').val() == "") {
                $('#guest_name_error').html("Please enter guest name");
                flag = false;
            } else {
                $('#guest_name_error').html("");
            }
        }
        var ticketFlag = validateTicket();
//        alert(ticketFlag);
        if (!ticketFlag) {
            changeTab('Ticket');
            $('#TicketSpan').addClass("active_tab");
            return false;
        }
        var hotelFlag = validateHotel();
        if (!hotelFlag) {
            changeTab('Hotel');
            $('#HotelSpan').addClass("active_tab");
            return false;
        }
//        alert("Inside ValidateTravelSubmit.");
//        var visaFlag = validateVisa();
//        if (!visaFlag) {
//            changeTab('Visa');
//            $('#VisaSpan').addClass("active_tab");
//            return false;
//        }
        var conveyanceFlag = validateConveyance();
        if (!conveyanceFlag) {
            changeTab('Conveyance');
            $('#ConveyanceSpan').addClass("active_tab");
            return false;
        }
        if (flag && ticketFlag && hotelFlag && conveyanceFlag && visaFlag) {
            $('#submitDiv').hide();
            return true;
        } else {
            return false;
        }
    } else {

        if ($('#cancel_remarks').val() == "") {
            $('#cancel_remarks_error').html("Please enter cancel remarks");
            return false;
        }
        var con = confirm("Are you sure want to cancel the request ?");
        if (con)
            $('#submitDiv').hide();
        else
            return false;

    }
}

function validateTravelAdvanceSubmit() {
    var flag = true;
    if ($("#action").val() == 'Submitted' || $("#action").val() == 'Saved') {
        if ($('#travelAdvanceEndDate').val() == "") {
            $('#travelAdvanceEndDate_error').html("Please enter Travel End Date");
            flag = false;
        } else {
            $('#travelAdvanceEndDate_error').html("");
        }
        if ($('#furtherAdvance').val() == "") {
            $('#furtherAdvance_error').html("Please enter Amount");
            flag = false;
        } else {
            $('#furtherAdvance_error').html("");
        }
        if ($('#advanceRemarks').val() == "") {
            $('#advanceRemarks_error').html("Please enter Remarks");
            flag = false;
        } else {
            $('#advanceRemarks_error').html("");
        }
        return flag;
    }
}


function validateTicket() {
    var flag = true;
    if ($('#travelType').val() == "I") {
        for (var i = 1; i <= parseInt($('#ticketCount').val()); i++) {
//        alert("Muni");
//        alert($('#ticketdeleted_'+i).val());
            if ($('#ticketdeleted_' + i) != null && $('#ticketdeleted_' + i).val() == 0) {
                if ($('#travel_date_' + i).val() == '' || $('#travel_mode_' + i).val() == '' || $('#to_city_id_' + i).val() == '' || $('#to_city_' + i).val() == '' || $('#from_city_id_' + i).val() == '' || $('#from_city_' + i).val() == '' || $('#from_country_' + i).val() == '' || $('#to_country_' + i).val() == '' || $('#travel_preference_' + i).val() == '') {
                    if ($('#travel_date_' + i).val() == "") {
                        $('#travel_date_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#travel_date_error_' + i).html("");
                    }
                    if ($('#travel_mode_' + i).val() == "") {
                        $('#travel_mode_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#travel_mode_error_' + i).html("");
                    }
                    if ($('#travel_preference_' + i).val() == "") {
                        $('#travel_preference_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#travel_preference_error_' + i).html("");
                    }
                    if ($('#travelType').val() == "I") {
                        if ($('#from_country_' + i).val() == "" || $('#from_country_' + i).val() == 0) {
                            $('#from_country_error_' + i).html("Required");
                            flag = false;
                        } else {
                            $('#from_country_error_' + i).html("");
                        }
                        if ($('#to_country_' + i).val() == "" || $('#to_country_' + i).val() == 0) {
                            $('#to_country_error_' + i).html("Required");
                            flag = false;
                        } else {
                            $('#to_country_error_' + i).html("");
                        }
                    }
                    if ($('#from_city_' + i).val() == "" && ($('#from_city_id_' + i).val() == "" || $('#from_city_id_' + i).val() == 0)) {
                        $('#from_city_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#from_city_error_' + i).html("");
                    }
                    if ($('#to_city_' + i).val() == "" && ($('#to_city_id_' + i).val() == "" || $('#to_city_id_' + i).val() == 0)) {
                        $('#to_city_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#to_city_error_' + i).html("");
                    }
                } else {
                    $('#travel_date_error_' + i).html("");
                    $('#travel_mode_error_' + i).html("");
                    $('#travel_preference_error_' + i).html("");
                    $('#from_country_error_' + i).html("");
                    $('#to_country_error_' + i).html("");
                    $('#from_city_error_' + i).html("");
                    $('#to_city_error_' + i).html("");
                }
            }
        }
    }
    return flag;
}

function validateHotel() {
    var flag = true;
    if ($('#travelType').val() == "I") {
        for (var i = 1; i <= parseInt($('#hotelCount').val()); i++) {
            if ($('#hoteldeleted_' + i) != null && $('#hoteldeleted_' + i).val() == 0) {
                if ($('#from_date_' + i).val() != '' || $('#to_date_' + i).val() != '' || $('#city_id_' + i).val() != '' || $('#city_' + i).val() != '' || $('#country_' + i).val() != '' || $('#location_' + i).val() != '') {
                    if ($('#from_date_' + i).val() == "") {
                        $('#from_date_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#from_date_error_' + i).html("");
                    }
                    if ($('#to_date_' + i).val() == "") {
                        $('#to_date_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#to_date_error_' + i).html("");
                    }
                    if ($('#system').val() != 'G') {
                        if ($('#location_' + i).val() == "") {
                            $('#location_error_' + i).html("Required");
                            flag = false;
                        } else {
                            $('#location_error_' + i).html("");
                        }
                    }
                    if ($('#travelType').val() == "I") {
                        if ($('#country_' + i).val() == "" || $('#country_' + i).val() == 0) {
                            $('#country_error_' + i).html("Required");
                            flag = false;
                        } else {
                            $('#country_error_' + i).html("");
                        }
                    }
                    if ($('#city_' + i).val() == "" && ($('#city_id_' + i).val() == "" || $('#city_id_' + i).val() == 0)) {
                        $('#city_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#city_error_' + i).html("");
                    }
                } else {
                    $('#from_date_error_' + i).html("");
                    $('#to_date_error_' + i).html("");
                    $('#location_error_' + i).html("");
                    $('#country_error_' + i).html("");
                    $('#city_error_' + i).html("");
                }
            }
        }
    }
    return flag;
}

function validateVisa() {
    alert("Inside Validate Visa.");
    var flag = true;
    if ($('#travelType').val() == "I") {
        if ($('#reference_number').val() == "" || $('#reference_number').val() == null) {
            $('#reference_number_error').html("Required");
            flag = false;
        }
        if ($('#visa_type').val() == "" || $('#visa_type').val() == null) {
            $('#visa_type_error').html("Required");
            flag = false;
        }
        if ($('#valid_from').val() == "" || $('#valid_from').val() == null) {
            $('#valid_from_error').html("Required");
            flag = false;
        }
        if ($('#valid_to').val() == "" || $('#valid_to').val() == null) {
            $('#valid_to_error').html("Required");
            flag = false;
        }
        if ($('#country_issue').val() == "" || $('#country_issue').val() == null) {
            $('#country_issue_error').html("Required");
            flag = false;
        }
        if ($('#place_of_issue').val() == "" || $('#place_of_issue').val() == null) {
            $('#place_of_issue_error').html("Required");
            flag = false;
        }
        if ($('#visa_visit').val() == "" || $('#visa_visit').val() == null) {
            $('#visa_visit_error').html("Required");
            flag = false;
        }
        if ($('#visa_remarks').val() == "" || $('#visa_remarks').val() == null) {
            $('#visa_remarks_error').html("Required");
            flag = false;
        }
    }
    return flag;
}

function validateConveyance() {
    var flag = true;
    for (var i = 1; i <= parseInt($('#conveyanceCount').val()); i++) {
        if ($('#conveyancedeleted_' + i) != null && $('#conveyancedeleted_' + i).val() == 0) {
            if ($('#pickup_' + i).val() != '' || $('#dropto_' + i).val() != '' || $('#conveyance_city_id_' + i).val() != '' || $('#conveyance_country_' + i).val() != '' || $('#conveyance_city_' + i).val() != '' || $('#start_date_' + i).val() != '' || $('#end_date_' + i).val() != '' || $('#time_hr_' + i).val() != '' || $('#time_min_' + i).val() != '') {
                if ($('#pickup_' + i).val() == "") {
                    $('#pickup_error_' + i).html("Required");
                    flag = false;
                } else {
                    $('#pickup_error_' + i).html("");
                }
                if ($('#dropto_' + i).val() == "") {
                    $('#dropto_error_' + i).html("Required");
                    flag = false;
                } else {
                    $('#dropto_error_' + i).html("");
                }
                if ($('#start_date_' + i).val() == "") {
                    $('#start_date_error_' + i).html("Required");
                    flag = false;
                } else {
                    $('#start_date_error_' + i).html("");
                }
                if ($('#end_date_' + i).val() == "") {
                    $('#end_date_error_' + i).html("Required");
                    flag = false;
                } else {
                    $('#end_date_error_' + i).html("");
                }
                if ($('#time_hr_' + i).val() == "" && $('#time_min_' + i).val() == "") {
                    $('#travel_time_error_' + i).html("Required");
                    flag = false;
                } else {
                    $('#travel_time_error_' + i).html("");
                }
                if ($('#travelType').val() == "I") {
                    if ($('#conveyance_country_' + i).val() == "" || $('#conveyance_country_' + i).val() == 0) {
                        $('#conveyance_country_error_' + i).html("Required");
                        flag = false;
                    } else {
                        $('#conveyance_country_error_' + i).html("");
                    }
                }
                if ($('#conveyance_city_' + i).val() == "" && ($('#conveyance_city_id_' + i).val() == "" || $('#conveyance_city_id_' + i).val() == 0)) {
                    $('#conveyance_city_error_' + i).html("Required");
                    flag = false;
                } else {
                    $('#conveyance_city_error_' + i).html("");
                }
            } else {
                $('#pickup_error_' + i).html("");
                $('#dropto_error_' + i).html("");
                $('#start_date_error_' + i).html("");
                $('#end_date_error_' + i).html("");
                $('#travel_time_error_' + i).html("");
                $('#conveyance_country_error_' + i).html("");
                $('#conveyance_city_error_' + i).html("");
            }
        }
    }
    return flag;
}
function validateEmpGenericDetails() {
    var flag = true;
    if ($('#surName').val() == '') {
        $('#surName').focus();
        $("#surName_error").html("Please enter sur name");
        flag = false;
    } else {
        $("#surName_error").html("");
    }
    if ($('#givenName').val() == '') {
        $('#givenName').focus();
        $("#givenName_error").html("Please enter given name");
        flag = false;
    } else {
        $("#givenName_error").html("");
    }
    if ($('#nationality').val() == '') {
        $("#nationality_error").html("Please select nationality");
        $('#givenName').focus();
        flag = false;
    } else {
        $("#nationality_error").html("");
    }
    if ($('#passportNumber').val() == '') {
        $("#passportNumber_error").html("Please enter passport number");
        $('#passportNumber').focus();
        flag = false;
    } else {
        $("#passportNumber_error").html("");
    }
    if ($('#issuePlace').val() == '') {
        $("#issuePlace_error").html("Please enter place of issue");
        $('#issuePlace').focus();
        flag = false;
    } else {
        $("#issuePlace_error").html("");
    }
    if ($('#issuedDate').val() == '') {
        $("#issuedDate_error").html("Please select issued date");
        $('#issuedDate').focus();
        flag = false;
    } else {
        $("#issuedDate_error").html("");
    }
    if ($('#expiryDate').val() == '') {
        $("#expiryDate_error").html("Please select expiry date");
        $('#expiryDate').focus();
        flag = false;
    } else {
        $("#expiryDate_error").html("");
    }
    if ($('#birthPlace').val() == '') {
        $("#birthPlace_error").html("Please enter place of birth");
        $('#birthPlace').focus();
        flag = false;
    } else {
        $("#birthPlace_error").html("");
    }
    if ($('#ecnrStatus').val() == '') {
        $("#ecnrStatus_error").html("Please select ecnr status");
        $('#ecnrStatus').focus();
        flag = false;
    } else {
        $("#ecnrStatus_error").html("");
    }
    if ($('#nominee').val() == '') {
        $("#nominee_error").html("Please enter nominee name");
        $('#nominee').focus();
        flag = false;
    } else {
        $("#nominee_error").html("");
    }
    if ($('#relationShip').val() == '') {
        $("#relationShip_error").html("Please enter relationship");
        $('#relationShip').focus();
        flag = false;
    } else {
        $("#relationShip_error").html("");
    }
    if ($('#doorNo').val() == '') {
        $("#doorNo_error").html("Please enter door no");
        $('#doorNo').focus();
        flag = false;
    } else {
        $("#doorNo_error").html("");
    }
    if ($('#streetName').val() == '') {
        $("#streetName_error").html("Please enter street name");
        $('#streetName').focus();
        flag = false;
    } else {
        $("#streetName_error").html("");
    }
    if ($('#area').val() == '') {
        $("#area_error").html("Please enter area");
        $('#area').focus();
        flag = false;
    } else {
        $("#area_error").html("");
    }
    if ($('#place').val() == '') {
        $("#place_error").html("Please enter place");
        $('#place').focus();
        flag = false;
    } else {
        $("#place_error").html("");
    }
    if ($('#system').val() != "G") {
        if ($('#mobileNo').val() == '') {
            $("#mobileNo_error").html("Please enter contact number");
            $('#mobileNo').focus();
            flag = false;
        } else {
            $("#mobileNo_error").html("");
        }
    }
    if ($('#mealPreference').val() == '') {
        $("#mealPreference_error").html("Please enter Prefered Meal");
        $('#mealPreference').focus();
        flag = false;
    } else {
        $("#mealPreference_error").html("");
    }
    if ($('#alternateMailId').val() == '') {
        $("#alternateMailId_error").html("Please enter alternate mail ID");
        $('#alternateMailId').focus();
        flag = false;
    } else {
        $("#alternateMailId_error").html("");
    }
    if (!flag)
        return false;
}

function alertText(message) {
    $('#errormessage').text(message);
}

