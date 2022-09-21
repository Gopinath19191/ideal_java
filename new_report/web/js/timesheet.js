/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function openLoader(){
    dimensions = windowDimensions();
    myWidth = dimensions[0];
    myHeight = dimensions[1];
    myWidth = 1000;
    $('#loader').css({
        'width' : myWidth,
        'height' : myHeight,
        'opacity' : '0.9',
        'z-index':'9100'
    });
    $('#loader').show();
}

function windowDimensions() {
    if (document.compatMode && document.compatMode != "BackCompat") {
        x = document.documentElement.clientWidth;
    } else {
        x = document.body.clientWidth;
    }
    y = document.body.clientHeight;

    return [x,y];
}

function closeLoader(){
    $('#loader').hide();
    $('#loader').css('');
}

function loadMonth(value){
    var path = $('#base_path').val();
    var employee_id = $('#employee_id').val();
    if(value != ""){
        jQuery.ajax({
            url: path+'/loadMonth.htm',
            type: "POST",
            async: false,
            data: ({
                year:value,
                employee_id:employee_id
            }),
            success: function(ajaxObj) { 
                var arr = ajaxObj.split(":");
                var mySelect = document.getElementById("month");
                mySelect.options.length = 0;
                mySelect.options[0] = new Option ("--select month--","");
                var count=1;
                for(var i=0;i<arr.length;i++) {
                    var out = arr[i].split(",");
                    if(out!='') {
                        document.getElementById("month").options[count+i] = new Option (out[1],out[0]);
                        document.getElementById("month").options[count+i].title = out[1];
                    }
                }
                var mySelectx = document.getElementById("project");
                mySelectx.options.length = 0;
                mySelectx.options[0] = new Option ("--select project--","");
            }
        });
    }
}

function loadProject(value){
    var path = $('#base_path').val();
    var employee_id = $('#employee_id').val();
    var year = $('#year').val();
    if(value != ""){
        jQuery.ajax({
            url: path+'/loadProject.htm',
            type: "POST",
            async: false,
            data: ({
                month:value,
                employee_id:employee_id,
                year:year
            }),
            success: function(ajaxObj) { 
                var arr = ajaxObj.split(":");
                var mySelect = document.getElementById("project");
                mySelect.options.length = 0;
                mySelect.options[0] = new Option ("--select project--","");
                var count=1;
                for(var i=0;i<arr.length;i++) {
                    var out = arr[i].split(",");
                    if(out!='') {
                        document.getElementById("project").options[count+i] = new Option (out[1],out[0]);
                        document.getElementById("project").options[count+i].title = out[1];
                    }
                }
            }
        });
    }
}

function loadTimesheet(value,yearNew,status) {
    var path = $('#base_path').val();
    employee_id = $('#employee_id').val();
    year = $('#year').val();
    if(undefined  != yearNew )
        year = yearNew;
    openLoader();
    $('#year').val(year);
    loadMonth(year);
    $('#month').val(value);
    if(value != ""){
        jQuery.ajax({
            url: path+'/loadTimesheet.htm',
            type: "POST",
            async: false,
            data: ({
                month:value,
                employee_id:employee_id,
                year:year,
                status:status
            }),
            success: function(ajaxObj) { 
                $('#timesheet').html(ajaxObj);
                closeLoader();
            }
        });
    } else {
        $('#timesheet').html("");
        closeLoader();
    }
    $("#successDiv").fadeOut("slow");
}

function loadTask(value,id) {
    var path = $('#base_path').val();
    project = $('#project_'+id).val();
    if(value != ""){
        jQuery.ajax({
            url: path+'/loadTask.htm',
            type: "POST",
            async: false,
            data: ({
                phase:value,
                project:project
            }),
            success: function(ajaxObj) { 
                var arr = ajaxObj.split(":");
                var mySelect = document.getElementById("task_"+id);
                mySelect.options.length = 0;
                mySelect.options[0] = new Option ("Others","Others");
                var count=1;
                for(var i=0;i<arr.length;i++) {
                    var out = arr[i].split(",");
                    if(out!='') {
                        document.getElementById("task_"+id).options[count+i] = new Option (out[1],out[0]);
                        document.getElementById("task_"+id).options[count+i].title =  out[1];
                    }
                }
            }
        });
    }
}


function loadAllActivities(value,employee_id,entry_date,row_id) {
    var time_date = entry_date.split('-');
    entry_date = time_date[0]+'-'+time_date[1]+'-'+time_date[2];
    
    var path = $('#base_path').val();
    if(value != ""){
        jQuery.ajax({
            url: path+'/loadAllActivities.htm',
            type: "POST",
            async: false,
            data: ({
                project:value,
                employee:employee_id,
                entry_date:entry_date
            }),
            success: function(ajaxObj) {
                if(value != "Non_Project_Activity") {
                    document.getElementById("role_"+row_id).disabled = false;
                    var result = ajaxObj.split("@#@");
                    var arr = result[0].split(":");
                    var mySelect = document.getElementById("role_"+row_id);
                    mySelect.options.length = 0;
                    //                    if(parseInt(arr.length)-1 > 1) {
                    //                        mySelect.options[0] = new Option ("--Role--");
                    //                        var count=1;
                    //                    } else {
                    //                        var count=0;
                    //                    }
                    var count=0;
                    for(var i=0;i<arr.length;i++) {
                        var out = arr[i].split(",");
                        if(out!='') {
                            document.getElementById("role_"+row_id).options[count+i] = new Option (out[1],out[0]);
                            document.getElementById("role_"+row_id).options[count+i].title = out[1];
                        }
                    }

                    var arr1 = result[1].split(":");
                    //                    var mySelect1 = document.getElementById("work_location_"+row_id);
                    //                    mySelect1.options.length = 0;
                    if(parseInt(arr.length)-1 > 1) {
                        //                        mySelect1.options[0] = new Option ("--loc--","");
                        var count1=1;
                    } else {
                        var count1=0;
                    }
                    for(var i=0;i<arr1.length;i++) {
                        var out1 = arr1[i].split(",");
                        if(out1!='') {
                        //                            document.getElementById("work_location_"+row_id).options[count1+i] = new Option (out1[1],out1[0]);
                        //                            document.getElementById("work_location_"+row_id).options[count1+i].title = out1[1];
                        }
                    }
                    //                    if(count1 == 0) {
                    //                        var out1 = arr1[0].split(",");
                    //                        if(parseInt(arr.length) != 0) {
                    //                            if(out1[0] == "o")
                    //                                $('#worked_hours_'+row_id).val("");
                    //                        }
                    //                    }

                    var arr2 = result[2].split(":");
                    var mySelect2 = document.getElementById("phase_"+row_id);
                    mySelect2.options.length = 0;
                    mySelect2.options[0] = new Option ("Others","Others");
                    var count2=1;
                    for(var i=0;i<arr2.length;i++) {
                        var out2 = arr2[i].split(",");
                        if(out2!='') {
                            document.getElementById("phase_"+row_id).options[count2+i] = new Option (out2[1],out2[0]);
                            document.getElementById("phase_"+row_id).options[count2+i].title = out2[1];
                        }
                    }
                    document.getElementById("phase_"+row_id).options[arr2.length] = new Option ("Non Billable Activity","Non billable activity");
                    var mySelect4 = document.getElementById("task_"+row_id);
                    mySelect4.options.length = 0;
                    mySelect4.options[0] = new Option ("Others","Others");
                } else {
                    var f=document.getElementById("role_"+row_id);
                    if(f.length > 0) {
                        f.length = 0;
                    }                
                    f.options[0] = new Option ("--Role--");
                    document.getElementById("role_"+row_id).disabled = true;

                    //                    var mySelect1 = document.getElementById("work_location_"+row_id);
                    //                    mySelect1.options.length = 0;
                    //                    document.getElementById("work_location_"+row_id).options[0] = new Option ("Offshore","s");
                    //                    document.getElementById("work_location_"+row_id).options[1] = new Option ("Onsite","o");

                    var arr2 = ajaxObj.split(":");
                    var mySelect2 = document.getElementById("phase_"+row_id);
                    mySelect2.options.length = 0;
                    mySelect2.options[0] = new Option ("Others","Others");
                    var count2=1;
                    for(var i=0;i<arr2.length;i++) {
                        var out2 = arr2[i].split(",");
                        if(out2!='') {
                            document.getElementById("phase_"+row_id).options[count2+i] = new Option (out2[1],out2[0]);
                            document.getElementById("phase_"+row_id).options[count2+i].title = out2[1];
                        }
                    }
                    
                    var mySelect4 = document.getElementById("task_"+row_id);
                    mySelect4.options.length = 0;
                    mySelect4.options[0] = new Option ("Others","Others");
                }
            }
        });
    }else{
        var f=document.getElementById("role_"+row_id);
        if(f.length > 0) {
            f.length = 0;
        }                
        f.options[0] = new Option ("--Role--");
        document.getElementById("role_"+row_id).disabled = false;
    }
}

function  TimesheetCheckAll(idValue){  
    var c = new Array();
    //    c = document.getElementsByTagName('input');
    c = document.querySelectorAll('.checkval');    
    
    if($('#'+idValue).is(':checked')){        
        for (var i = 0; i < c.length; i++){
           
            var weekDayIdVal = $('#weekEnd_'+(1+i)).val();
            var statusText = $('#status_'+(1+i)).val();
            var holiday_description = $('#holiday_description_'+(1+i)).val();            
            if(weekDayIdVal == 'Y'){              
                if(statusText == 's'){
                    document.querySelectorAll('.checkval')[i].checked = true;            
                }else{
                    document.querySelectorAll('.checkval')[i].checked = false; 
                }
            }else{            
                if(holiday_description == ''){
                    if((c[i].disabled) == false){
                        document.querySelectorAll('.checkval')[i].checked = true; 
                    }                      
                }else{
                    if((c[i].disabled) == false){
                        document.querySelectorAll('.checkval')[i].checked = false; 
                    }
                }
            }
        }        
        calculateAllTotalWorkedHours();
    }
    else
    {
        for (var i = 0; i < c.length; i++){
            if (c[i].type == 'checkbox')
                c[i].checked = false;
        }
        calculateTotalWorkedHours();
    } 
}

function calculateTotalAvailableWorkedHours(counterVar) {
    var totaltsHrs=0;
    var totaltsMins=0;
    var count = 0;
    $(".checkval").each(function() {
        if($('#'+this.id).is(':checked')) {
            time = $(".avail_hrs_"+counterVar).val();
            aval_split = time.split(":");
            avalHrs = aval_split[0];
            avalMin = aval_split[1];
            if(avalHrs != "") {
                totaltsHrs += parseFloat(avalHrs);
                
            }
            if(avalMin != "") {
                totaltsMins += parseFloat(avalMin);
            }
        }
    });
    totalAddiMins =0;
    if(totaltsMins >= 60) {
        totalAddiMins = parseInt(totaltsMins / 60);
        totaltsMins = parseFloat(totaltsMins % 60);
    }
    if(totaltsMins <= 9) {
        totaltsMins = "0"+totaltsMins;
    }
    
    $("#avail_hours").val(parseFloat(totaltsHrs+totalAddiMins));
    $("#avail_minutes").val(totaltsMins);
}

function calculateTotalWorkedHours() {
    
    var totaltsHrs=0;
    var totaltsMins=0;
    $(".checkval").each(function() {
        
        if($('#'+this.id).is(':checked')) {
            
            var chkId = this.id;
            chkIdSpt = chkId.split("_");
            counterVar = chkIdSpt[1];
            tsHrs = $("#worked_hours_"+counterVar).val();
            tsMins = $("#worked_minutes_"+counterVar).val();
            if(tsHrs != "") {
                totaltsHrs += parseFloat(tsHrs);
            }
            if(tsMins != "") {
                totaltsMins += parseFloat(tsMins);
            }
        }
    });
    totalAddiMins =0;
    if(totaltsMins >= 60) {
        totalAddiMins = parseInt(totaltsMins / 60);
        totaltsMins = parseFloat(totaltsMins % 60);
    }
    if(totaltsMins <= 9) {
        totaltsMins = "0"+totaltsMins;
    }
    
    $("#worked_hours").val(parseFloat(totaltsHrs+totalAddiMins));
    $("#worked_minutes").val(totaltsMins);
}

function calculateAllTotalWorkedHours() {
    var totaltsHrs=0;
    var totaltsMins=0;
    var ln = $(".checkval").length;
    
    for(var i=1; i<=ln; i++){
        tsHrs = $("#worked_hours_"+i).val();
        tsMins = $("#worked_minutes_"+i).val();
        if(tsHrs != "") {
            totaltsHrs += parseFloat(tsHrs);
        }
        if(tsMins != "") {
            totaltsMins += parseFloat(tsMins);
        }
    }
    totalAddiMins =0;
    if(totaltsMins >= 60) {
        totalAddiMins = parseInt(totaltsMins / 60);
        totaltsMins = parseFloat(totaltsMins % 60);
    }
    if(totaltsMins <= 9) {
        totaltsMins = "0"+totaltsMins;
    }
    
    $("#worked_hours").val(parseFloat(totaltsHrs+totalAddiMins));
    $("#worked_minutes").val(totaltsMins);
}

function calculateTotalApprovedHours() {
   
    var totaltsHrs=0;
    var totaltsMins=0;
    $(".checkval").each(function() {
        
        if($('#'+this.id).is(':checked')) {
            
            var chkId = this.id;
            chkIdSpt = chkId.split("_");
            counterVar = chkIdSpt[2];
            tsHrs = $("#appHrs_"+counterVar).val();
            tsMins = $("#appMins_"+counterVar).val();
            if(tsHrs != "") {
                totaltsHrs += parseFloat(tsHrs);
            }
            if(tsMins != "") {
                totaltsMins += parseFloat(tsMins);
            }
        }
    });
    totalAddiMins =0;
    if(totaltsMins >= 60) {
        totalAddiMins = parseInt(totaltsMins / 60);
        totaltsMins = parseFloat(totaltsMins % 60);
    }
    if(totaltsMins <= 9) {
        totaltsMins = "0"+totaltsMins;
    }
    
    $("#worked_hours").val(parseFloat(totaltsHrs+totalAddiMins));
    $("#worked_minutes").val(totaltsMins);
}
    
function duplicateRow(rowObject,id) {
    var base_path = $('#base_path').val();
    var theRow = $(rowObject).parent().parent();
    var rowData = $(rowObject).parent().parent().html();
    var totalCount = $('#totalCount').val();
    var dateValue = $('#timesheet_date_'+id).val();
    var dayCount = $('.dayCount_'+dateValue).val();
    
    cnt = parseInt(totalCount)+1;
    dayCnt = parseInt(dayCount)+1;
    
    $('.dayCount_'+dateValue).val(dayCnt);
    var rowDataVal = rowData.replace("project_"+id,"project_"+cnt);
    rowDataVal = rowDataVal.replace("shift_"+id,"shift_"+cnt);
    rowDataVal = rowDataVal.replace("role_"+id,"role_"+cnt);
    //    rowDataVal = rowDataVal.replace("work_location_"+id,"work_location_"+cnt);
    rowDataVal = rowDataVal.replace("phase_"+id,"phase_"+cnt);
    rowDataVal = rowDataVal.replace("task_"+id,"task_"+cnt);
    rowDataVal = rowDataVal.replace("worked_hours_"+id,"worked_hours_"+cnt);
    rowDataVal = rowDataVal.replace("worked_minutes_"+id,"worked_minutes_"+cnt);
    rowDataVal = rowDataVal.replace("regularize_"+id,"regularize_"+cnt);
    rowDataVal = rowDataVal.replace("available_hours_"+id,"available_hours_"+cnt);
    rowDataVal = rowDataVal.replace("attended_hours_"+id,"attended_hours_"+cnt);
    rowDataVal = rowDataVal.replace("remarks_"+id,"remarks_"+cnt);
    rowDataVal = rowDataVal.replace("actionItems_"+id,"actionItems_"+cnt);
    rowDataVal = rowDataVal.replace("check_"+id,"check_"+cnt);
    rowDataVal = rowDataVal.replace("statusText_"+id,"statusText_"+cnt);
    rowDataVal = rowDataVal.replace("status_"+id,"status_"+cnt);
    rowDataVal = rowDataVal.replace("duplicateRow(this,"+id,"duplicateRow(this,"+cnt);
    rowDataVal = rowDataVal.replace(","+id+")",","+cnt+")");
    rowDataVal = rowDataVal.replace(","+id+")",","+cnt+")");
    rowDataVal = rowDataVal.replace(","+id+")",","+cnt+")");
    rowDataVal = rowDataVal.replace("#@"+id+"@#","#@"+cnt+"@#");
    rowDataVal = rowDataVal.replace("#@"+id+"@#","#@"+cnt+"@#");
    rowDataVal = rowDataVal.replace('<a href="javascript:;" onclick="removeRow(this)"><img src="/iDeal_timesheet/images/tm_delete.png"></a>','');
    rowDataVal = rowDataVal.replace('<A onclick=removeRow(this) href="javascript:;"><IMG src="/iDeal_timesheet/images/tm_delete.png"></A>','');
    rowDataVal = rowDataVal.replace("projectSpan_"+id,"projectSpan_"+cnt);
    rowDataVal = rowDataVal.replace("remarksSpan_"+id,"remarksSpan_"+cnt);
    rowDataVal = rowDataVal.replace("viewComments_"+id,"viewComments_"+cnt);
    rowDataVal = rowDataVal.replace("dayCount_"+id,"dayCount_"+cnt);
    rowDataVal = rowDataVal.replace("roleSpan_"+id,"roleSpan_"+cnt);
    //    rowDataVal = rowDataVal.replace("locationSpan_"+id,"locationSpan_"+cnt);
    rowDataVal = rowDataVal.replace("timesheet_date_"+id,"timesheet_date_"+cnt);
    rowDataVal = rowDataVal.replace("half_day_leave_"+id,"half_day_leave_"+cnt);
    rowDataVal = rowDataVal.replace("res_autoId_"+id,"res_autoId_"+cnt);
    $(theRow).after("<tr id=tr_"+cnt+">"+rowDataVal+"</tr>");
    
    $("#project_"+cnt).attr('disabled',false);
    $("#phase_"+cnt).attr('disabled',false);
    $("#project_"+cnt).val('');
    $("#status_"+cnt).val('');
    $("#half_day_leave_"+cnt).val('');
    $("#phase_"+cnt).val('Others');
    $("#shift_"+cnt).attr('disabled',false);
    $("#check_"+cnt).attr('disabled',false);
    $("#task_"+cnt).val('Others');
    $("#task_"+cnt).attr('disabled',false);
    $("#worked_hours_"+cnt).val('');
    $("#worked_hours_"+cnt).attr('disabled',false);
    $("#worked_minutes_"+cnt).val('');
    $("#worked_minutes_"+cnt).attr('disabled',false);
    $("#regularize_"+cnt).val('');
    $("#regularize_"+cnt).attr('disabled',false);
    var f=document.getElementById("role_"+cnt);
    f.options[0] = new Option ("--Role--");
    $("#role_"+cnt).attr('disabled',false);
    $("#remarks_"+cnt).val('');
    $("#remarks_"+cnt).attr('disabled',false);
    $("#res_autoId_"+cnt).val('');
    $("#res_autoId_"+cnt).attr('disabled',false);
    $("#statusText_"+cnt).html("<font color='red'><b>To be Submitted</b></font>");
    $("#actionItems_"+cnt).html('');    
    $('#viewComments_'+cnt).html('');  
    $("#actionItems_"+cnt).append('<a href="javascript:;" onClick="duplicateRow(this,'+cnt+')" style="text-decoration:none;margin-right:3px;"><img src="'+base_path+'/images/tm_add.png" /></a>');
    $("#actionItems_"+cnt).append('<a href="javascript:;" onClick="removeRow(this)"><img src="'+base_path+'/images/tm_delete.png" /></a>');
    $('#totalCount').val(cnt);
}

function removeRow(rowObject){
    var row = $(rowObject).parent().parent();
    $(row).remove();
    calculateTotalWorkedHours();
    return false;
}

function hideErrorDiv(){
    $("#errorDisplayDiv").hide();
}

function validateForm() {   

    var jsonArr = [];
    
    var flag = 0;
    var checkCount = 0;
    var values = new Array();
    var attend_hrs = 0;
    var attend_min = 0;
    var total_attend_hrs = 0;
    var total_available_hrs = 0;
    var halfDay_available_hrs = 0;
    var available_hours = 0;
    var year = $('#year').val();
    var month = $('#month').val(); 
    var weekDay = 0;      
    var holidayDescription ;
    //
    var year_count_WFH_entry = $('#year_count').val();
    var month_count_WFH_entry = $('#month_count').val();
    var year_count_WFH_policy = $('#WFH_Year_count').val();
    var month_count_WFH_policy = $('#WFH_Month_count').val();
    var inExceptionList = $('#inExceptionList').val();
    var inDailyExceptionList = $('#inDailyExceptionList').val();
    //
            
    $(".checkval").each(function() {
        if($('#'+this.id).is(':checked')) {
            
            checkCount++;
            var id_arr = (this.id).split("_");
            var resId = id_arr[1];
            var hrs = 0;
            var mins = 0;
            var graceMins = 15;
            var graceHrs = 1;
            var regularize = 0;
            var commonIdFromLoop = 0;
            var temp = $('#remarks_'+resId).val();
            temp = temp.replace(/ /g, '');
            var a = $('#regularize_'+resId).val().length;
            var b = temp.length;
            weekDay = $('#weekEnd_'+resId).val();
            holidayDescription = $('#holiday_description_'+resId).val();
            
            $('#projectSpan_'+resId).html('');
            //Mandate project
         
            //
            if($('#project_'+resId).val() == "") {
                //                $("body").on('click','input.submitbutton',function(){
                //                    if($('#project_'+resId).val() == "") {
                var DivHeight = $("html").height();
                $("#projectNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#rprojectNotificationFocus").attr('tabindex',-1).focus();
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#projectNotificationYes',function(){
                    $("#projectNotification").css({
                        "display":"none" 
                    });
                    $('#project_'+resId).focus();
                });
                //                });
                $('#projectSpan_'+resId).html("* required");
                flag++;
                return false;
            }
            var p = /^(?!0+$)[a-zA-Z0-9]+$/;
            if(p.test($('#worked_hours_'+resId).val()) == false && p.test($('#worked_minutes_'+resId).val()) == false)
            {
                //'0' as input enterd validation
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if(p.test($('#worked_hours_'+resId).val()) == false && p.test($('#worked_minutes_'+resId).val()) == false)
                //                    {
                $("#emptyEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#emptyEntryErrorFocus").attr('tabindex',-1).focus();
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#emptyEntryErrorYes',function(){
                    $("#emptyEntryError").css({
                        "display":"none" 
                    });
                    $('#worked_hours_'+resId).focus();
                });
                //                });
                flag++;
                return false;
            }
            if($('#worked_hours_'+resId).val() > 23) {
                //More than 24 hours validation
                //                $("body").on('click','input.submitbutton',function(){
                //                    if($('#worked_hours_'+resId).val() > 23) {
                var DivHeight = $("html").height();
                $("#24hrsEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#24hrsEntryErrorFocus").attr('tabindex',-1).focus();
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#24hrsEntryErrorYes',function(){
                    $("#24hrsEntryError").css({
                        "display":"none" 
                    });
                    $('#worked_hours_'+resId).focus();  
                });
                //                });
                flag++;
                return false;
            }
            if($('#worked_minutes_'+resId).val() > 59){
                //More than 60 min validation
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if($('#worked_minutes_'+resId).val() > 59){
                $("#60MinEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#60MinEntryErrorFocus").attr('tabindex',-1).focus();
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#60MinEntryErrorYes',function(){
                    $("#60MinEntryError").css({
                        "display":"none" 
                    });
                    $('#worked_minutes_'+resId).focus();
                });
                //                });
                flag++;
                return false;
            }
            if( a > 0 && b < 1)
            {
                // remarks mandatory validation 
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if( a > 0 && b < 1)
                //                    {
                $("#remarksEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#remarksEntryErrorFocus").attr('tabindex',-1).focus();
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#remarksEntryErrorYes',function(){
                    $("#remarksEntryError").css({
                        "display":"none" 
                    });
                    $('#remarks_'+resId).focus();  
                });
                //                });
                flag++;
                return false;
            }
            //
            //
            if(jQuery.inArray($('#timesheet_date_'+resId).val(), values)== -1) {
                var r = $('#timesheet_date_'+resId).val();
                var perDayCount = $(".timeClass"+r).length;
                var countToCheckDataEntered = $(".perDayCheck_"+r+":checked").length;
                var halfDayCount = 0;
                var dummy = 0;
                var attendance_hrs = 0;
                var dummy_attendance_hrs = 0;
                var avlHrs =0;
                var WFH_perDay_count = 0;
                $(".timeClass"+$('#timesheet_date_'+resId).val()).each(function() { 
                    values.push($('#timesheet_date_'+resId).val());                     
                    var commonId = (this.id).split("_")[2];
                    commonIdFromLoop = commonId;
                    
                    var halfDayVal = $('#half_day_leave_'+commonId).val();
                    
                    hrs += parseInt($('#worked_hours_'+commonId).val() != '' ? ($('#worked_hours_'+commonId).val()) : 0, 10);
                    mins += parseInt($('#worked_minutes_'+commonId).val() != '' ? ($('#worked_minutes_'+commonId).val()) : 0, 10);
                    
                    regularize += parseInt($('#regularize_'+commonId).val() != '' ? 1 : 0);                    
                    
                    if($('#regularize_'+commonId).val() == 'Work From Home'){                        
                        WFH_perDay_count++;
                    }
                    
                    attendance_hrs = $('#attended_hours_'+commonId).val();
                    
                    available_hours = $('#available_hours_'+commonId).val();
                    
                    available_hours = available_hours.split(":");
                    avlHrs = available_hours[0];
                    
                    if(attendance_hrs=="---" || attendance_hrs==''){
                        attend_hrs = available_hours[0];
                        attend_min = available_hours[1];
                        attMin = attend_min;
                    }
                    else{
                        var attend_value = attendance_hrs.split(":");
                        attend_hrs = attend_value[0];
                        attend_min = attend_value[1];
                        attMin = attend_value[1];
                        dummy_attendance_hrs = attend_value[0]+'.'+attend_value[1];
                    }
                    
                    if(halfDayVal !=''){
                        halfDayCount++;
                    }
                    
                    if(halfDayCount > 0){
                        halfDayCount--;
                        perDayCount--;
                        dummy++;
                    }
                    
                   
                });
            }                        
            
            if((inExceptionList != 'Y') && (WFH_perDay_count>0)){
                month_count_WFH_entry++;
                year_count_WFH_entry++;
                
                if(month_count_WFH_entry > month_count_WFH_policy){                    
                    //WFH error for month message
                    //                    $("body").on('click','input.submitbutton',function(){
                    var DivHeight = $("html").height();
                    //                        if(month_count_WFH_entry > month_count_WFH_policy){  
                    $("#policyError").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                    //                        }
                    $("#policyErrorFocus").attr('tabindex',-1).focus();
                    $("#remarksEntryError").css({
                        "display":"none" 
                    });
                    $("#saveRejectNotification").css({
                        "display":"none" 
                    });
                    $("#emptyEntryError").css({
                        "display":"none" 
                    });
                    $("#24hrsEntryError").css({
                        "display":"none" 
                    });
                    $("#checkSelect").css({
                        "display":"none" 
                    });
                    $("#60MinEntryError").css({
                        "display":"none" 
                    });
                    $("#halfDayEntryError").css({
                        "display":"none" 
                    });
                    $("#availableHrsNotification").css({
                        "display":"none" 
                    });
                    $("#attendanceHrsNotification").css({
                        "display":"none" 
                    });
                    $("#regularizeHrsNotification").css({
                        "display":"none" 
                    });
                    $("#regularizeReasonNotification").css({
                        "display":"none" 
                    });
                    $("#projectNotification").css({
                        "display":"none" 
                    });
                    $("#monthEntryError").css({
                        "display":"none" 
                    });
                    $("#onceSubmitNotification").css({
                        "display":"none" 
                    });
                    $("#policyNotification").css({
                        "display":"none" 
                    });
                    $("body").on('click','#policyErrorYes',function(){
                        $("#policyError").css({
                            "display":"none" 
                        });                            
                    });
                    //                    });
                    flag++;
                    return false;
                }
                if(year_count_WFH_entry > year_count_WFH_policy){
                    //WFH error message
                    //                    $("body").on('click','input.submitbutton',function(){
                    var DivHeight = $("html").height();
                    //                        if(year_count_WFH_entry > year_count_WFH_policy){  
                    $("#policyError").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                    //                        }
                    $("#policyErrorFocus").attr('tabindex',-1).focus();
                    $("#remarksEntryError").css({
                        "display":"none" 
                    });
                    $("#saveRejectNotification").css({
                        "display":"none" 
                    });
                    $("#emptyEntryError").css({
                        "display":"none" 
                    });
                    $("#24hrsEntryError").css({
                        "display":"none" 
                    });
                    $("#checkSelect").css({
                        "display":"none" 
                    });
                    $("#60MinEntryError").css({
                        "display":"none" 
                    });
                    $("#halfDayEntryError").css({
                        "display":"none" 
                    });
                    $("#availableHrsNotification").css({
                        "display":"none" 
                    });
                    $("#attendanceHrsNotification").css({
                        "display":"none" 
                    });
                    $("#regularizeHrsNotification").css({
                        "display":"none" 
                    });
                    $("#regularizeReasonNotification").css({
                        "display":"none" 
                    });
                    $("#projectNotification").css({
                        "display":"none" 
                    });
                    $("#monthEntryError").css({
                        "display":"none" 
                    });
                    $("#onceSubmitNotification").css({
                        "display":"none" 
                    });
                    $("#policyNotification").css({
                        "display":"none" 
                    });
                    $("body").on('click','#policyErrorYes',function(){
                        $("#policyError").css({
                            "display":"none" 
                        });                            
                    });
                    //                    });
                    flag++;
                    return false;
                }
            }                        
            
            if( countToCheckDataEntered != perDayCount){
                //once submit per day validation
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if( countToCheckDataEntered != perDayCount){
                $("#onceSubmitNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#onceSubmitNotificationFocus").attr('tabindex',-1).focus();
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#onceSubmitNotificationYes',function(){
                    $("#onceSubmitNotification").css({
                        "display":"none" 
                    });
                    $(".perDayCheck_"+r).focus();
                });
                //                });
                flag++;
                return false;
            }
            
            var totalMinutes = (hrs*60)+mins;
            var hours = parseInt(totalMinutes/60);
            hours = hours.toString().length == 1 ? '0'+hours : hours;
            var minutes = parseInt(totalMinutes%60);
            minutes = minutes.toString().length == 1 ? '0'+minutes : minutes;
            var timesheetHours = hours+'.'+minutes;            
            
            //var perDay = parseFloat(hrs,10) + parseFloat(mins,10);
            if(attend_min>60){
                attend_min = attend_min/60;
            }else{
                attend_min = attend_min/100;
            }
            
            if( available_hours[1] >= 60){
                available_hours[1] = available_hours[1]/60;
            }else{
                available_hours[1] = available_hours[1]/100;
            }
            
            total_attend_hrs = parseFloat(attend_hrs,10) + parseFloat(attend_min,10);
            total_available_hrs = parseFloat(available_hours[0],10) + parseFloat(available_hours[1],10);
            
            var tempHrs = (parseInt(available_hours[0]*60))+parseInt(available_hours[1]); 
            var tepHours = tempHrs/2;
            var h = parseInt(tepHours/60);
            var m = parseInt(tepHours%60);
            h = h.toString().length == 1 ? '0'+h : h;
            m = m.toString().length == 1 ? '0'+m : m;
            halfDay_available_hrs = (h +'.'+ m);
                        
            var status = $('#status_'+resId).val();
            
            if( timesheetHours >= 24){
                //More than 24 hours validation
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if( timesheetHours >= 24){
                $("#24hrsEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //    }
                $("#24hrsEntryErrorFocus").attr('tabindex',-1).focus();
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#24hrsEntryErrorYes',function(){
                    $("#24hrsEntryError").css({
                        "display":"none" 
                    });
                    $('#worked_hours_'+resId).focus();  
                });
                //                });
                flag++;
                return false;
            }
            
            if((total_attend_hrs > 0 && timesheetHours < total_attend_hrs) && dummy == 1 && status!='r')
            {               
                //For half day must enter attendance hours
                
                if((attendance_hrs=="---" || attendance_hrs=='') ){     
                    if((timesheetHours < halfDay_available_hrs) && dummy == 1 && status!='r'){
                        //                        $("body").on('click','input.submitbutton',function(){
                        var DivHeight = $("html").height();                        
                        //                            if((timesheetHours < halfDay_available_hrs) && dummy == 1 && status!='r'){
                        $("#halfDayEntryError").css({
                            "height":DivHeight,
                            "display":"block"
                        }); 
                        //                            }
                        $("#halfDayEntryErrorFocus").attr('tabindex',-1).focus();
                        $("#remarksEntryError").css({
                            "display":"none" 
                        });
                        $("#emptyEntryError").css({
                            "display":"none" 
                        });
                        $("#saveRejectNotification").css({
                            "display":"none" 
                        });
                        $("#checkSelect").css({
                            "display":"none" 
                        });
                        $("#60MinEntryError").css({
                            "display":"none" 
                        });
                        $("#24hrsEntryError").css({
                            "display":"none" 
                        });                        
                        $("#onceSubmitNotification").css({
                            "display":"none" 
                        });
                        $("#availableHrsNotification").css({
                            "display":"none" 
                        });
                        $("#attendanceHrsNotification").css({
                            "display":"none" 
                        });
                        $("#regularizeHrsNotification").css({
                            "display":"none" 
                        });
                        $("#regularizeReasonNotification").css({
                            "display":"none" 
                        });
                        $("#projectNotification").css({
                            "display":"none" 
                        });
                        $("#monthEntryError").css({
                            "display":"none" 
                        });
                        $("#policyError").css({
                            "display":"none" 
                        });
                        $("#policyNotification").css({
                            "display":"none" 
                        });
                        $("body").on('click','#halfDayEntryErrorYes',function(){
                            $("#halfDayEntryError").css({
                                "display":"none" 
                            });
                            $('#worked_hours_'+resId).focus();  
                        });
                        //                        });
                        flag++;
                        return false;
                    }else{
                        
                    }
                }else{
                    
                    if((total_attend_hrs > 0 && timesheetHours < total_attend_hrs) && dummy == 1 && status!='r'){
                        //                        $("body").on('click','input.submitbutton',function(){
                        var DivHeight = $("html").height();               
                        //                            if((total_attend_hrs > 0 && timesheetHours < total_attend_hrs) && dummy == 1 && status!='r'){
                        $("#attendanceHrsNotification").css({
                            "height":DivHeight,
                            "display":"block"
                        });
                        //                            }
                        $("#attendanceHrsNotificationFocus").attr('tabindex',-1).focus();
                        $("#remarksEntryError").css({
                            "display":"none" 
                        });
                        $("#saveRejectNotification").css({
                            "display":"none" 
                        });
                        $("#emptyEntryError").css({
                            "display":"none" 
                        });
                        $("#checkSelect").css({
                            "display":"none" 
                        });
                        $("#60MinEntryError").css({
                            "display":"none" 
                        });
                        $("#24hrsEntryError").css({
                            "display":"none" 
                        });
                        $("#halfDayEntryError").css({
                            "display":"none" 
                        });
                        $("#onceSubmitNotification").css({
                            "display":"none" 
                        });
                        $("#availableHrsNotification").css({
                            "display":"none" 
                        });
                        $("#regularizeHrsNotification").css({
                            "display":"none" 
                        });
                        $("#regularizeReasonNotification").css({
                            "display":"none" 
                        });
                        $("#projectNotification").css({
                            "display":"none" 
                        });
                        $("#monthEntryError").css({
                            "display":"none" 
                        });
                        $("#policyError").css({
                            "display":"none" 
                        });
                        $("#policyNotification").css({
                            "display":"none" 
                        });
                        $("body").on('click','#attendanceHrsNotificationYes',function(){
                            $("#attendanceHrsNotification").css({
                                "display":"none" 
                            });
                            $('#worked_hours_'+resId).focus();  
                        });
                        //                        });
                        flag++;
                        return false;
                    }else{
                        
                    }
                }
            }            
            
            if((total_attend_hrs > 0 && timesheetHours < total_attend_hrs) && (dummy == 0) && (status!='r')){
                
                if((attendance_hrs=="---" || attendance_hrs=='') && (weekDay != 'Y') && (holidayDescription == '')){                    
                    //Enter Available hours in worked hours notification
                    //                    $("body").on('click','input.submitbutton',function(){
                    var DivHeight = $("html").height();
                    //                        if((attendance_hrs=="---" || attendance_hrs=='') && (weekDay != 'Y') && (holidayDescription == '')){
                    $("#availableHrsNotification").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                    //                        }
                    $("#availableHrsNotificationFocus").attr('tabindex',-1).focus();
                    $("#remarksEntryError").css({
                        "display":"none" 
                    });
                    $("#emptyEntryError").css({
                        "display":"none" 
                    });
                    $("#saveRejectNotification").css({
                        "display":"none" 
                    });
                    $("#checkSelect").css({
                        "display":"none" 
                    });
                    $("#60MinEntryError").css({
                        "display":"none" 
                    });
                    $("#24hrsEntryError").css({
                        "display":"none" 
                    });
                    $("#halfDayEntryError").css({
                        "display":"none" 
                    });
                    $("#onceSubmitNotification").css({
                        "display":"none" 
                    });
                    $("#attendanceHrsNotification").css({
                        "display":"none" 
                    });
                    $("#regularizeHrsNotification").css({
                        "display":"none" 
                    });
                    $("#regularizeReasonNotification").css({
                        "display":"none" 
                    });
                    $("#projectNotification").css({
                        "display":"none" 
                    });
                    $("#monthEntryError").css({
                        "display":"none" 
                    });
                    $("#policyError").css({
                        "display":"none" 
                    });
                    $("#policyNotification").css({
                        "display":"none" 
                    });
                    $("body").on('click','#availableHrsNotificationYes',function(){
                        $("#availableHrsNotification").css({
                            "display":"none" 
                        });
                        $('#worked_hours_'+resId).focus();  
                    });
                    //                    });
                    flag++;
                    return false;
                }
                else if(attendance_hrs != '---' && attendance_hrs != '' && inDailyExceptionList!= 'YES'){                    
                    //Enter attendance hours                     
                    //                    $("body").on('click','input.submitbutton',function(){
                    var DivHeight = $("html").height();
                    //                        if((attendance_hrs != '---' && attendance_hrs != '')){
                    $("#attendanceHrsNotification").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                    //  }
                    $("#attendanceHrsNotificationFocus").attr('tabindex',-1).focus();
                    $("#remarksEntryError").css({
                        "display":"none" 
                    });
                    $("#emptyEntryError").css({
                        "display":"none" 
                    });
                    $("#saveRejectNotification").css({
                        "display":"none" 
                    });
                    $("#checkSelect").css({
                        "display":"none" 
                    });
                    $("#60MinEntryError").css({
                        "display":"none" 
                    });
                    $("#24hrsEntryError").css({
                        "display":"none" 
                    });
                    $("#halfDayEntryError").css({
                        "display":"none" 
                    });
                    $("#onceSubmitNotification").css({
                        "display":"none" 
                    });
                    $("#availableHrsNotification").css({
                        "display":"none" 
                    });
                    $("#regularizeHrsNotification").css({
                        "display":"none" 
                    });
                    $("#regularizeReasonNotification").css({
                        "display":"none" 
                    });
                    $("#projectNotification").css({
                        "display":"none" 
                    });
                    $("#monthEntryError").css({
                        "display":"none" 
                    });
                    $("#policyError").css({
                        "display":"none" 
                    });
                    $("#policyNotification").css({
                        "display":"none" 
                    });
                    $("body").on('click','#attendanceHrsNotificationYes',function(){
                        $("#attendanceHrsNotification").css({
                            "display":"none" 
                        });
                        $('#worked_hours_'+resId).focus();  
                    });
                    //                    });
                    flag++;
                    return false;
                }                
            }
            
            //attMin Available hrs
            var attnMin = (parseInt(attMin)+parseInt(graceMins));
            if(attnMin >=60){
                attnMin = attnMin/60;
            }else{
                attnMin = attnMin/100;
            }
                
            var totalHours = parseFloat(attend_hrs)+parseFloat(attnMin);
                       
            //per day timesheet hours
            //totalHours attendance+15 min 
            if((timesheetHours < total_available_hrs) && (totalHours < total_available_hrs) && (dummy == 0) && (status!='r') && (weekDay != 'Y') && (holidayDescription == '')){
                // Must regularize hours validation
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if((timesheetHours < total_available_hrs) && (totalHours < total_available_hrs) && (dummy == 0) && (status!='r') && (weekDay != 'Y') && (holidayDescription == '')){
                $("#regularizeHrsNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#regularizeHrsNotificationFocus").attr('tabindex',-1).focus();
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#regularizeHrsNotificationYes',function(){
                    $("#regularizeHrsNotification").css({
                        "display":"none" 
                    });
                });
                //                });
                flag++;
                return false;
            }
            
            if((attendance_hrs=="---" || attendance_hrs=='') && regularize == 0 && dummy == 0 && status!='r'){
                //Select regularization reason notification
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if((attendance_hrs=="---" || attendance_hrs=='')&&regularize == 0 && dummy == 0 && status!='r'){
                $("#regularizeReasonNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#regularizeReasonNotificationFocus").attr('tabindex',-1).focus();
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#regularizeReasonNotificationYes',function(){
                    $("#regularizeReasonNotification").css({
                        "display":"none" 
                    });
                    $('#regularize_'+commonIdFromLoop).focus();
                });
                //                });
                flag++;
                return false;
            }
                  
            var avlGraceHrs = (parseInt(avlHrs * 1)+parseInt(graceHrs)); 
            
            if((dummy_attendance_hrs > avlGraceHrs ) && regularize == 0 && dummy == 0 && status!='r' && inDailyExceptionList!== 'YES'){
                //Select regularization reason notification        
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if((dummy_attendance_hrs > avlGraceHrs)&& regularize == 0 && dummy == 0 && status!='r'){
                $("#regularizeReasonNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#regularizeReasonNotificationFocus").attr('tabindex',-1).focus();
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#regularizeReasonNotificationYes',function(){
                    $("#regularizeReasonNotification").css({
                        "display":"none" 
                    });
                    $('#regularize_'+commonIdFromLoop).focus();
                });
                //                });
                flag++;
                return false;
            }                        
            
            if((timesheetHours > dummy_attendance_hrs) && regularize == 0 && dummy == 0 && status!='r' && inDailyExceptionList!== 'YES'){
                
                //Select regularization reason notification
                //                $("body").on('click','input.submitbutton',function(){
                var DivHeight = $("html").height();
                //                    if((timesheetHours > dummy_attendance_hrs) && regularize == 0 && dummy == 0 && status!='r'){
                $("#regularizeReasonNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#regularizeReasonNotificationFocus").attr('tabindex',-1).focus();
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#regularizeReasonNotificationYes',function(){
                    $("#regularizeReasonNotification").css({
                        "display":"none" 
                    });
                    $('#regularize_'+commonIdFromLoop).focus();
                });
                //                });
                flag++;
                return false;
            }
            // building JSON array for timesheet insert starts
            var worked_hours = ($('#worked_hours_'+resId).val().length > 0 ?  $('#worked_hours_'+resId).val() : "00")
            + ":" +
            ($('#worked_minutes_'+resId).val().length > 0 ?  $('#worked_minutes_'+resId).val() : "00");
        
            jsonArr.push({
                timesheet_id : $('#res_autoId_'+resId).val(),
                timesheet_date : year + '-' + month + '-' + $('#timesheet_date_'+resId).val(),
                project_id : $('#project_'+resId).val(),
                shift : $('#shift_'+resId).val(),
                role_id : $('#role_'+resId).val(),
                phase_id : $('#phase_'+resId).val(),
                task_id : $('#task_'+resId).val(),
                worked_hours : worked_hours,
                remarks : $('#remarks_'+resId).val(),
                regularization_reason : $('#regularize_'+resId).val()
            });
        // building JSON array for timesheet insert ends
        }
    });
    
    // building JSON array for timesheet insert starts
    $('#selectedRows').val(JSON.stringify(jsonArr));
    // building JSON array for timesheet insert ends
    if(checkCount > 0 ) {
        if(flag > 0){
            return false;
        }
        else{
            return true;
        }
    } else {
        //Select check box error
        //        $("body").on('click','input.submitbutton',function(){
        var DivHeight = $("html").height();
        $("#checkSelect").css({
            "height":DivHeight,
            "display":"block"
        });
        $("#checkSelectFocus").attr('tabindex',-1).focus();
        $("#emptyEntryError").css({
            "display":"none" 
        });
        $("#saveRejectNotification").css({
            "display":"none" 
        });
        $("#remarksEntryError").css({
            "display":"none" 
        });
        $("#24hrsEntryError").css({
            "display":"none" 
        });
        $("#60MinEntryError").css({
            "display":"none" 
        });
        $("#halfDayEntryError").css({
            "display":"none" 
        });
        $("#onceSubmitNotification").css({
            "display":"none" 
        });
        $("#availableHrsNotification").css({
            "display":"none" 
        });
        $("#attendanceHrsNotification").css({
            "display":"none" 
        });
        $("#regularizeHrsNotification").css({
            "display":"none" 
        });
        $("#regularizeReasonNotification").css({
            "display":"none" 
        });
        $("#projectNotification").css({
            "display":"none" 
        });
        $("#monthEntryError").css({
            "display":"none" 
        });
        $("#policyError").css({
            "display":"none" 
        });
        $("#policyNotification").css({
            "display":"none" 
        });
        $("body").on('click','#checkSelectYes',function(){
            $("#checkSelect").css({
                "display":"none" 
            });
        });
        //        });
        flag++;
        return false;
    }
    $('#buttonTable').hide();
}
function validateSaveForm(){
    var jsonArr = [];
    
    var flag = 0;
    var checkCount = 0;
    var year = $('#year').val();
    var month = $('#month').val(); 
    
    $(".checkval").each(function() {
        
        if($('#'+this.id+':checked').length > 0) {
           
            checkCount++;
            var id_arr = (this.id).split("_");
            var resId = id_arr[1];
            // Rejected entries cannot be saved
            var statusString = $('#status_'+resId).val();            
          
            if(statusString =='r'){
                // alert("succws"+statusString); 
                //                $("body").on('click','input.qualitysave',function(){
                var DivHeight = $("html").height();
                   
                $("#saveRejectNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                  
                $("#saveRejectFocus").attr('tabindex',-1).focus();
                    
                $("#projectNotification").css({
                    "display":"none" 
                });
                    
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#saveRejectYes',function(){
                    $("#saveRejectNotification").css({
                        "display":"none" 
                    });
                    $('#status_'+resId).focus();
                });
                //                });
                flag++;
                return false;
            }
            //To select project
            $('#projectSpan_'+resId).html('');
            
            if($('#project_'+resId).val() == '') {
                
                //                $("body").on('click','input.qualitysave',function(){
                var DivHeight = $("html").height();
                //                    if($('#project_'+resId).val() == '') {
                $("#projectNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#rprojectNotificationFocus").attr('tabindex',-1).focus();
                    
                    
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                    
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#projectNotificationYes',function(){
                    $("#projectNotification").css({
                        "display":"none" 
                    });
                    $('#project_'+resId).focus();
                });
                //                });
                $('#projectSpan_'+resId).html("* required");
                flag++;
                return false;
            }
            var p = /^(?!0+$)[a-zA-Z0-9]+$/;
            if(p.test($('#worked_hours_'+resId).val()) == false && p.test($('#worked_minutes_'+resId).val()) == false)
            {
                //'0' as input enterd validation
                //                $("body").on('click','input.qualitysave',function(){
                var DivHeight = $("html").height();
                //                    if(p.test($('#worked_hours_'+resId).val()) == false && p.test($('#worked_minutes_'+resId).val()) == false)
                //                    {
                $("#emptyEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#emptyEntryErrorFocus").attr('tabindex',-1).focus();
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#emptyEntryErrorYes',function(){
                    $("#emptyEntryError").css({
                        "display":"none" 
                    });
                    $('#worked_hours_'+resId).focus();
                });
                //                });
                flag++;
                return false;
            }
            
            if($('#worked_hours_'+resId).val() > 23) {
                //More than 24 hours validation
                //                $("body").on('click','input.qualitysave',function(){
                var DivHeight = $("html").height();
                //                    if($('#worked_hours_'+resId).val() > 23) {
                $("#24hrsEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#24hrsEntryErrorFocus").attr('tabindex',-1).focus();
                    
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#60MinEntryError").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#24hrsEntryErrorYes',function(){
                    $("#24hrsEntryError").css({
                        "display":"none" 
                    });
                    $('#worked_hours_'+resId).focus();  
                });
                //                });
                flag++;
                return false;
            }
            if($('#worked_minutes_'+resId).val() > 59) {
                //More than 60 min validation
                //                $("body").on('click','input.qualitysave',function(){
                var DivHeight = $("html").height();
                //                    if($('#worked_minutes_'+resId).val() > 59) {
                $("#60MinEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
                //                    }
                $("#60MinEntryErrorFocus").attr('tabindex',-1).focus();
                $("#emptyEntryError").css({
                    "display":"none" 
                });
                $("#remarksEntryError").css({
                    "display":"none" 
                });
                $("#saveRejectNotification").css({
                    "display":"none" 
                });
                $("#24hrsEntryError").css({
                    "display":"none" 
                });
                $("#checkSelect").css({
                    "display":"none" 
                });
                $("#halfDayEntryError").css({
                    "display":"none" 
                });
                $("#onceSubmitNotification").css({
                    "display":"none" 
                });
                $("#availableHrsNotification").css({
                    "display":"none" 
                });
                $("#attendanceHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeHrsNotification").css({
                    "display":"none" 
                });
                $("#regularizeReasonNotification").css({
                    "display":"none" 
                });
                $("#projectNotification").css({
                    "display":"none" 
                });
                $("#monthEntryError").css({
                    "display":"none" 
                });
                $("#policyError").css({
                    "display":"none" 
                });
                $("#policyNotification").css({
                    "display":"none" 
                });
                $("body").on('click','#60MinEntryErrorYes',function(){
                    $("#60MinEntryError").css({
                        "display":"none" 
                    });
                    $('#worked_minutes_'+resId).focus();
                });
                //                });
                flag++;
                return false;
            }
            
            // building JSON array for timesheet insert starts
            var worked_hours = ($('#worked_hours_'+resId).val().length > 0 ?  $('#worked_hours_'+resId).val() : "00")
            + ":" +
            ($('#worked_minutes_'+resId).val().length > 0 ?  $('#worked_minutes_'+resId).val() : "00");
            var roleVal;
            if($('#role_'+resId).val() == '--Role--'){
                roleVal = null;
            }else{
                roleVal = $('#role_'+resId).val();
            }
            jsonArr.push({
                timesheet_id : $('#res_autoId_'+resId).val(),
                timesheet_date : year + '-' + month + '-' + $('#timesheet_date_'+resId).val(),
                project_id : $('#project_'+resId).val(),
                shift : $('#shift_'+resId).val(),                
                role_id : roleVal,
                phase_id : $('#phase_'+resId).val(),
                task_id : $('#task_'+resId).val(),
                worked_hours : worked_hours,
                remarks : $('#remarks_'+resId).val(),
                regularization_reason : $('#regularize_'+resId).val()
            });
        // building JSON array for timesheet insert ends
        }
    });
    
    // building JSON array for timesheet insert starts
    $('#selectedRows').val(JSON.stringify(jsonArr));
    // building JSON array for timesheet insert ends

    if(checkCount > 0 ) {
        if(flag > 0){
            return false;
        }
        else{            
            return true;
        }
    } else {
        //Select checkbox error
        //        $("body").on('click','input.qualitysave',function(){
        var DivHeight = $("html").height();
        $("#checkSelect").css({
            "height":DivHeight,
            "display":"block"
        });
        $("#checkSelectFocus").attr('tabindex',-1).focus();
        $("#emptyEntryError").css({
            "display":"none" 
        });
        $("#remarksEntryError").css({
            "display":"none" 
        });
        $("#saveRejectNotification").css({
            "display":"none" 
        });
        $("#24hrsEntryError").css({
            "display":"none" 
        });
        $("#60MinEntryError").css({
            "display":"none" 
        });
        $("#halfDayEntryError").css({
            "display":"none" 
        });
        $("#onceSubmitNotification").css({
            "display":"none" 
        });
        $("#availableHrsNotification").css({
            "display":"none" 
        });
        $("#attendanceHrsNotification").css({
            "display":"none" 
        });
        $("#regularizeHrsNotification").css({
            "display":"none" 
        });
        $("#regularizeReasonNotification").css({
            "display":"none" 
        });
        $("#projectNotification").css({
            "display":"none" 
        });
        $("#monthEntryError").css({
            "display":"none" 
        });
        $("#policyError").css({
            "display":"none" 
        });
        $("#policyNotification").css({
            "display":"none" 
        });
        $("body").on('click','#checkSelectYes',function(){
            $("#checkSelect").css({
                "display":"none" 
            });
        });
        //        });
        return false;
    }
    $('#buttonTable').hide();
}
function exportBtn()
{
    var year = $('#year').val();
    var month = $('#month').val();
    var employee_id= $('#employee_id').val();    
    
    if($('#month').val() == ''){        
        $("body").on('click','input.exportbutton',function(){
            var DivHeight = $("html").height();
           
            if($('#month').val() == ''){
                $("#monthEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
            }
            $("#monthEntryErrorFocus").attr('tabindex',-1).focus();
            $("#24hrsEntryError").css({
                "display":"none" 
            });
            $("#saveRejectNotification").css({
                "display":"none" 
            });
            $("#checkSelect").css({
                "display":"none" 
            });
            $("#60MinEntryError").css({
                "display":"none" 
            });
            $("#halfDayEntryError").css({
                "display":"none" 
            });
            $("#onceSubmitNotification").css({
                "display":"none" 
            });
            $("#availableHrsNotification").css({
                "display":"none" 
            });
            $("#attendanceHrsNotification").css({
                "display":"none" 
            });
            $("#regularizeHrsNotification").css({
                "display":"none" 
            });
            $("#regularizeReasonNotification").css({
                "display":"none" 
            });
            $("#emptyEntryError").css({
                "display":"none" 
            });
            $("#remarksEntryError").css({
                "display":"none" 
            });
                    
            $("#projectNotification").css({
                "display":"none" 
            });
            $("#policyError").css({
                "display":"none" 
            });
            $("body").on('click','#monthEntryErrorYes',function(){
                $("#monthEntryError").css({
                    "display":"none" 
                });                       
            });
        });
    }else{
        $('#tmisheetFilterPage').attr("action", "exportTimesheetReport.htm?year="+year+"&month="+month+"&employee_id="+employee_id);
        document.tmisheetFilterPage.submit();
    }    
}
function loadHrs(value,id) {
    if(value=="o") {
        $('#worked_hours_'+id).val('');
        $('#worked_minutes_'+id).val('');
    } else {
        $('#worked_hours_'+id).val('');
        $('#worked_minutes_'+id).val('');
    }
}

   
function loadTimesheet_New(value,yearNew,status) {
    if(status == undefined){
        status = $('#status').val();
    }
    var path = $('#base_path').val();
    employee_id = $('#employee_id').val();
    year = $('#year').val();
    if(undefined  != yearNew )
        year = yearNew;
    openLoader();
    
    loadMonth(year);
    $('#month').val(value);
    if(value != ""){
        jQuery.ajax({
            url: path+'/loadTimesheet_New.htm',
            type: "POST",
            async: false,
            data: ({
                month:value,
                employee_id:employee_id,
                year:year,
                status:status
            }),
            success: function(ajaxObj) { 
                $('#timesheet').html(ajaxObj);
                closeLoader();
            }
        });
    } else {
        $('#timesheet').html("");
        closeLoader();
    }
    if(status == 'a' || status == 'm'){
        $('.actionToSave').css("display","none");
    }
    $("#successDiv").fadeOut("slow");
    //                    
    
    loadRoleAndLocation(value,year);
}
function loadMonth(value,status){
    var path = $('#base_path').val();
    var employee_id = $('#employee_id').val();
    status = $('#status').val();
    if(value != ""){
        jQuery.ajax({
            url: path+'/loadMonth.htm',
            type: "POST",
            async: false,
            data: ({
                year:value,
                employee_id:employee_id,
                status:status
            }),
            success: function(ajaxObj) { 
                var arr = ajaxObj.split(":");
                var mySelect = document.getElementById("month");
                mySelect.options.length = 0;
                mySelect.options[0] = new Option ("--select month--","");
                var count=1;
                for(var i=0;i<arr.length;i++) {
                    var out = arr[i].split(",");
                    if(out!='') {
                        document.getElementById("month").options[count+i] = new Option (out[1],out[0]);
                        document.getElementById("month").options[count+i].title = out[1];
                    }
                }
            //                var mySelectx1 = document.getElementById("project");
            //                mySelectx1.options.length = 0;
            //                mySelectx1.options[0] = new Option ("--select project--","");
            }
        });
    }
}

function loadRoleAndLocation(value,yearValue){
    var path = $('#base_path').val();
    var employee_id = $('#employee_id').val();
    var year = $('#year').val();
    if(year == null)
        year = yearValue;
    if(value != ""){
        jQuery.ajax({
            url: path+'/loadEmployeeRoleAndLocation.htm',
            type: "POST",
            async: false,
            data: ({
                month:value,
                employee_id:employee_id,
                year:year
            }),
            success: function(ajaxObj) { 
                if(ajaxObj != ''){
                    var arr = ajaxObj.split(";");
                    for(var i = 0; i < arr.length; i++) {
                        if(arr[i].length > 0)
                        {                        
                            var eachSplitedData = arr[i].split(":");
                            var newText = eachSplitedData[0]+"<span style='font-weight:normal'> in </span> ";
                            newText += eachSplitedData[1]+"<span style='font-weight:normal'> from </span> ";
                            newText += eachSplitedData[2]+"<span style='font-weight:normal'> to </span> ";
                            newText += eachSplitedData[3]+"<span style='font-weight:normal'> working </span> ";
                            newText += eachSplitedData[4];
                            //
                            var appendRow ='<tr style="height:20px;margin: 0px;padding: 0px;" >';
                            appendRow +='<td style="width:auto;padding: 0px 0px 0px 20px;" class="role_ul">';
                            appendRow +='<img src="/iDeal_timesheet/images/tick.png" style="padding: 0px 10px 0px 0px;"/>';
                            appendRow += newText+'</td></tr>';
                            $(".roleWorkLocTable tbody").append(appendRow);
                        }
                    }
                }else{
                    $('.roleWorkLocTable').hide();
                }
            }
        });
    }
}

function removeSavedData(idValue){
    if(idValue != ""){
        jQuery.ajax({
            url: 'removeSavedItems.htm',
            type: "POST",
            async: false,
            data: ({
                value : idValue,
                month : $('#month').val(),
                year : $('#year').val(),
                employee_id : $('#employee_id').val()
            }),
            success: function(ajaxObj) { 
                var v = ajaxObj.split('@@');
                loadTimesheet_New(v[0],v[1]);
            }
        });
    }
}

function refreshData(pageToRefresh){
    
    if( pageToRefresh == 'Index'){
       
        $('#status').val('');
            
        var TodayDate = new Date();
        var m = TodayDate.getMonth();
        m = parseInt(m)+1;
        var y = TodayDate.getFullYear();
        $('#year').val(y);
        jQuery.ajax({
            url: 'clearSearch.htm',
            type: "POST",
            async: false,
            data: ({
                pageToRefresh : pageToRefresh,
                month : m,
                year : y,
                employee_id : $('#employee_id').val()
            }),
            success: function(ajaxObj) { 
                var v = ajaxObj.split('@@');
                loadTimesheet_New(v[0],v[1]);
            }
        });
    }
}

function policyConfigurationPopUp(idValue,value){
    if(value != ''){
        if(value == 'Work From Home'){                        
            var DivHeight = $("html").height();
            if(value == 'Work From Home'){                
                $("#policyNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
            }
        }
        $("#policyNotificationFocus").attr('tabindex',-1).focus();
        $("#emptyEntryError").css({
            "display":"none" 
        });
        $("#remarksEntryError").css({
            "display":"none" 
        });
        $("#saveRejectNotification").css({
            "display":"none" 
        });
        $("#24hrsEntryError").css({
            "display":"none" 
        });
        $("#60MinEntryError").css({
            "display":"none" 
        });
        $("#halfDayEntryError").css({
            "display":"none" 
        });
        $("#onceSubmitNotification").css({
            "display":"none" 
        });
        $("#availableHrsNotification").css({
            "display":"none" 
        });
        $("#attendanceHrsNotification").css({
            "display":"none" 
        });
        $("#regularizeHrsNotification").css({
            "display":"none" 
        });
        $("#regularizeReasonNotification").css({
            "display":"none" 
        });
        $("#projectNotification").css({
            "display":"none" 
        });
        $("#monthEntryError").css({
            "display":"none" 
        });
        $("#policyError").css({
            "display":"none" 
        });
        $("#checkSelect").css({
            "display":"none" 
        });
        $("body").on('click','#policyNotificationYes',function(){
            $("#policyNotification").css({
                "display":"none" 
            });
            $('#'+idValue).focus();
        });
           
        return false;
        
    }
}