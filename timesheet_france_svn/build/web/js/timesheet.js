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

function stopLoading() {
//    var loadingDivObj=(document.all);
//    var ns4=document.layers;
//    var ns6=document.getElementById&&!document.all;
//    var ie4=document.all;
//    if (ns4) {
//        loadingDivObj.visibility = "hidden";
//    }
//    else if (ns6 || ie4)
//        loadingDivObj.display = "none";
    setTimeout(function(){
        $("#loadingDiv").hide();
    },100);
    
}

function startLoading() {
//    var loadingDivObj=(document.all);
//    var ns4=document.layers;
//    var ns6=document.getElementById&&!document.all;
//    var ie4=document.all;
//    if (ns4) {
//        loadingDivObj.visibility = "visible";
//    }
//    else if (ns6 || ie4)
//        loadingDivObj.display = "block";
    setTimeout(function(){
        $("#loadingDiv").show();
    },100);
    
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
    $('#year').val(yearNew);
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
                    f.options[0] = new Option ("--Role--","--Role--");
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
        f.options[0] = new Option ("--Role--","--Role--");
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
    rowDataVal = rowDataVal.replace("office_hours_"+id,"office_hours_"+cnt);
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
    f.options[0] = new Option ("--Role--","--Role--");
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


function exportBtn(){
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

function exportPdfBtn(){
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
        $('#tmisheetFilterPage').attr("action", "exportTimesheetPdfReport.htm?year="+year+"&month="+month+"&employee_id="+employee_id);
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

function loadTimesheetNext(value,yearNew,status){
    $("#loadingDiv").show();
    setTimeout(function() {
        loadTimesheet_New(value,yearNew,status);
    }, 100);
}

function loadTimesheetPrevious(value,yearNew,status){
    $("#loadingDiv").show();
    setTimeout(function() {
        loadTimesheet_New(value,yearNew,status);
    }, 100);
}

function loadTimesheet_New(value,yearNew,status) {
    if(status == undefined){
        status = $('#status').val();
    }
    if(yearNew == undefined){
        yearNew = $('#year').val();
    }
    var path = $('#base_path').val();
    employee_id = $('#employee_id').val();
    year = $('#year').val();
    if(undefined  != yearNew )
        year = yearNew;
//    openLoader();
    loadMonth(year);
    $('#month').val(value);
    $('#year').val(yearNew);
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
//                closeLoader();
//                stopLoading();
                $("#loadingDiv").hide();
            }
        });
    } else {
        $('#timesheet').html("");
//        closeLoader();
        $("#loadingDiv").hide();
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