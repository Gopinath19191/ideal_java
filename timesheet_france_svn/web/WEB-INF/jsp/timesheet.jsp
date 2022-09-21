<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    a {
        text-decoration: none;
    }
    .required {
        font-weight: bold;
        color :red;
    }
    .qualitysave {
        background: url("images/icon_btn_save.png") no-repeat scroll 8px 8px #316CA8;
        border: 1px solid #4492BF;
        color: #FFFFFF;
        font-weight: bold;
        height: 30px;
        margin: 0 15px 0 15px;
        padding: 0 10px 0 30px;
    }
    #timesheet .tableStructure tr th{
        padding:3px;
    }
    .role_ul{
       margin: 0px 0px 0px 20px;
       font-size: 12px;
       color: #666666;
       font-weight: bold;
   }
/*   .hours_detalis{
       width: 96.75%; 
       border: 1px solid #CEDFF1;
       background: url(images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;
       border-radius: 5px; 
       margin: 0px 5px 0px 5px; 
       padding: 10px; 
       background-size: 100% 100%;
       font-weight: bold;
       color:#666666;
       cursor: pointer;
   }*/
</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery_1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/wtooltip.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
//        $("#hours_explanation").click(function(){
//            $("#hours_desc").toggle();
//        });
        $("#buttonSave").click(function(){
            var validResult = validateSaveForm();
            if(validResult == true){
                startLoading();
                $("#saveStatus").val('s');
                $('#tmisheetFilterPage').attr("action", "timesheetSave_New.htm");
                document.tmisheetFilterPage.submit();
            }
        });
        $("#buttonSubmit").click(function(){
           
            var validResult = validateForm();
            if(validResult == true){
                startLoading();
                $("#saveStatus").val('m');
                $('#tmisheetFilterPage').attr("action", "timesheetSave_New.htm");
                document.tmisheetFilterPage.submit();
            }
            
        });
        
        $(document.getElementsByName('res_check')).click(function(){
            if($(".checkVal").length == $("#checkAll:checked").length) {
            } else {
                $("#checkAll").removeAttr("checked");
            }		 
        });
    });
function validateForm() {   
     $("#buttonSubmit").hide();   
     $("#buttonSave").hide();
     
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
    var year_count_WFH_entry = $('#year_count').val();
    var month_count_WFH_entry = $('#month_count').val();
    var year_count_WFH_policy = $('#WFH_Year_count').val();
    var month_count_WFH_policy = $('#WFH_Month_count').val();
    var inExceptionList = $('#inExceptionList').val();
            
    $(".checkval").each(function() {
        if($('#'+this.id).is(':checked')) {
            
            checkCount++;
            var id_arr = (this.id).split("_");
            var resId = id_arr[1];
            var hrs = 0;
            var mins = 0;
            var graceMins = 00;
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
            if($('#project_'+resId).val() == "") {
                var DivHeight = $("html").height();
                $("#projectNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                });
                $('#projectSpan_'+resId).html("* required");
                flag++;
                return false;
            }
            var p = /^(?!0+$)[a-zA-Z0-9]+$/;
            if(p.test($('#worked_hours_'+resId).val()) == false && p.test($('#worked_minutes_'+resId).val()) == false)
            {
                var DivHeight = $("html").height();
                $("#emptyEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                });
                flag++;
                return false;
            }
            if($('#worked_hours_'+resId).val() > 23) {
                var DivHeight = $("html").height();
                $("#24hrsEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                });
                flag++;
                return false;
            }
            if($('#worked_minutes_'+resId).val() > 59){
                var DivHeight = $("html").height();
                $("#60MinEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                });
                flag++;
                return false;
            }
            if( a > 0 && b < 1)
            {
                var DivHeight = $("html").height();
                $("#remarksEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                });
                flag++;
                return false;
            }
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
                    var DivHeight = $("html").height();
                    $("#policyError").css({
                        "height":DivHeight,
                        "display":"block"
                    });
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
                        $("#buttonSubmit").show();
                        $("#buttonSave").show();
                        
                    });
                    flag++;
                    return false;
                }
                if(year_count_WFH_entry > year_count_WFH_policy){
                    var DivHeight = $("html").height();
                    $("#policyError").css({
                        "height":DivHeight,
                        "display":"block"
                    });
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
                        $("#buttonSubmit").show();
                        $("#buttonSave").show();
                    });
                    flag++;
                    return false;
                }
            }                        
            
            if( countToCheckDataEntered != perDayCount){
                var DivHeight = $("html").height();
                $("#onceSubmitNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                    $(".perDayCheck_"+r).focus();
                });
                flag++;
                return false;
            }
            
            var totalMinutes = (hrs*60)+mins;
            var hours = parseInt(totalMinutes/60);
            hours = hours.toString().length == 1 ? '0'+hours : hours;
            var minutes = parseInt(totalMinutes%60);
            minutes = minutes.toString().length == 1 ? '0'+minutes : minutes;
            var timesheetHours = hours+'.'+minutes;            
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
                var DivHeight = $("html").height();
                $("#24hrsEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                    $('#worked_hours_'+resId).focus();  
                });
                flag++;
                return false;
            }
            
//            if((total_attend_hrs > 0 && timesheetHours < total_attend_hrs) && dummy == 1 && status!='r'){total_available_hrs
            if((total_attend_hrs > 0 && timesheetHours < total_attend_hrs) && dummy == 1 && status!='r'){
                if((attendance_hrs=="---" || attendance_hrs=='') ){     
                    if((timesheetHours < halfDay_available_hrs) && dummy == 1 && status!='r'){
                        var DivHeight = $("html").height();                        
                        $("#halfDayEntryError").css({
                            "height":DivHeight,
                            "display":"block"
                        }); 
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
                            $("#buttonSubmit").show();
                            $("#buttonSave").show();
                            $('#worked_hours_'+resId).focus();  
                        });
                        flag++;
                        return false;
                    }else{
                    }
                }else{
                    if((total_attend_hrs > 0 && timesheetHours < halfDay_available_hrs) && dummy == 1 && status!='r'){
                        var DivHeight = $("html").height();               
                        $("#attendanceHrsNotification").css({
                            "height":DivHeight,
                            "display":"block"
                        });
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
                            $("#buttonSubmit").show();
                            $("#buttonSave").show();
                            $('#worked_hours_'+resId).focus();  
                        });
                        flag++;
                        return false;
                    }else{
                        
                    }
                }
            }            
            
            if((total_attend_hrs > 0 && timesheetHours < total_available_hrs) && (dummy == 0) && (status!='r')){
//                alert("skip validation111111111111");
                if((attendance_hrs=="---" || attendance_hrs=='') && (weekDay != 'Y') && (holidayDescription == '')){                    
                    var DivHeight = $("html").height();
                    $("#availableHrsNotification").css({
                        "height":DivHeight,
                        "display":"block"
                    });
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
                        $("#buttonSubmit").show();
                        $("#buttonSave").show();
                        $('#worked_hours_'+resId).focus();  
                    });
                    flag++;
                    return false;
                }else if(attendance_hrs != '---' && attendance_hrs != ''){  
//                    alert("skip validation2222222222");
                    var DivHeight = $("html").height();
                    $("#availableHrsNotification").css({
                        "height":DivHeight,
                        "display":"block"
                    });
                    $("#availableHrsNotificationFocus").attr('tabindex',-1).focus();
                    
//                    $("#attendanceHrsNotification").css({
//                        "height":DivHeight,
//                        "display":"block"
//                    });
//                    $("#attendanceHrsNotificationFocus").attr('tabindex',-1).focus();
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
                        $("#buttonSubmit").show();
                        $("#buttonSave").show();
                    });
                    flag++;
                    return false;
                }                
            }
            var attnMin = (parseInt(attMin)+parseInt(graceMins));
            if(attnMin >=60){
                attnMin = attnMin/60;
            }else{
                attnMin = attnMin/100;
            }
            var totalHours = parseFloat(attend_hrs)+parseFloat(attnMin);
            if((timesheetHours < total_available_hrs) && (totalHours < total_available_hrs) && (dummy == 0) && (status!='r') && (weekDay != 'Y') && (holidayDescription == '')){
                var DivHeight = $("html").height();
                $("#availableHrsNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
                $("#availableHrsNotificationFocus").attr('tabindex',-1).focus();
//                $("#regularizeHrsNotification").css({
//                    "height":DivHeight,
//                    "display":"block"
//                });
//                $("#regularizeHrsNotificationFocus").attr('tabindex',-1).focus();
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
                $("#regularizeHrsNotification").css({
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
                $("body").on('click','#availableHrsNotificationYes',function(){
                    $("#availableHrsNotification").css({
                        "display":"none" 
                    });
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                });
                flag++;
                return false;
            }
            
            if((attendance_hrs=="---" || attendance_hrs=='') && regularize == 0 && dummy == 0 && status!='r'){
                var DivHeight = $("html").height();
                $("#regularizeReasonNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                    $('#regularize_'+commonIdFromLoop).focus();
                });
                flag++;
                return false;
            }
                  
//            var avlGraceHrs = (parseInt(avlHrs * 1)+parseInt(graceHrs)); 
//            if((timesheetHours > avlGraceHrs) && regularize == 0 && dummy == 0 && status!='r'){
//                var DivHeight = $("html").height();
////                alert("hereeeeeeee it has prob");
//                $("#regularizeReasonNotification").css({
//                    "height":DivHeight,
//                    "display":"block"
//                });
//                $("#regularizeReasonNotificationFocus").attr('tabindex',-1).focus();
//                $("#remarksEntryError").css({
//                    "display":"none" 
//                });
//                $("#emptyEntryError").css({
//                    "display":"none" 
//                });
//                $("#saveRejectNotification").css({
//                    "display":"none" 
//                });
//                $("#checkSelect").css({
//                    "display":"none" 
//                });
//                $("#60MinEntryError").css({
//                    "display":"none" 
//                });
//                $("#24hrsEntryError").css({
//                    "display":"none" 
//                });
//                $("#halfDayEntryError").css({
//                    "display":"none" 
//                });
//                $("#onceSubmitNotification").css({
//                    "display":"none" 
//                });
//                $("#availableHrsNotification").css({
//                    "display":"none" 
//                });
//                $("#attendanceHrsNotification").css({
//                    "display":"none" 
//                });
//                $("#regularizeHrsNotification").css({
//                    "display":"none" 
//                });
//                $("#projectNotification").css({
//                    "display":"none" 
//                });
//                $("#monthEntryError").css({
//                    "display":"none" 
//                });
//                $("#policyError").css({
//                    "display":"none" 
//                });
//                $("#policyNotification").css({
//                    "display":"none" 
//                });
//                $("body").on('click','#regularizeReasonNotificationYes',function(){
//                    $("#regularizeReasonNotification").css({
//                        "display":"none" 
//                    });
//                    $('#regularize_'+commonIdFromLoop).focus();
//                    $("#buttonSubmit").show();
//                    $("#buttonSave").show();
//                });
//                flag++;
//                return false;
//            }                        
            //changes done by gopinath
            
//            if((total_attend_hrs > 0 && timesheetHours > total_available_hrs && timesheetHours < totalHours) && (dummy == 0) && (status!='r')){
////                alert("skip validation333333331"+totalHours);
//                if((attendance_hrs=="---" || attendance_hrs=='') && (weekDay != 'Y') && (holidayDescription == '')){                    
//                    var DivHeight = $("html").height();
//                    $("#availableHrsNotification").css({
//                        "height":DivHeight,
//                        "display":"block"
//                    });
//                    $("#availableHrsNotificationFocus").attr('tabindex',-1).focus();
//                    $("#remarksEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#emptyEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#saveRejectNotification").css({
//                        "display":"none" 
//                    });
//                    $("#checkSelect").css({
//                        "display":"none" 
//                    });
//                    $("#60MinEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#24hrsEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#halfDayEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#onceSubmitNotification").css({
//                        "display":"none" 
//                    });
//                    $("#attendanceHrsNotification").css({
//                        "display":"none" 
//                    });
//                    $("#regularizeHrsNotification").css({
//                        "display":"none" 
//                    });
//                    $("#regularizeReasonNotification").css({
//                        "display":"none" 
//                    });
//                    $("#projectNotification").css({
//                        "display":"none" 
//                    });
//                    $("#monthEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#policyError").css({
//                        "display":"none" 
//                    });
//                    $("#policyNotification").css({
//                        "display":"none" 
//                    });
//                    $("body").on('click','#availableHrsNotificationYes',function(){
//                        $("#availableHrsNotification").css({
//                            "display":"none" 
//                        });
//                        $("#buttonSubmit").show();
//                        $("#buttonSave").show();
//                        $('#worked_hours_'+resId).focus();  
//                    });
//                    flag++;
//                    return false;
//                }else if(attendance_hrs != '---' && attendance_hrs != ''){  
////                    alert("skip validation4444444");
//                    var DivHeight = $("html").height();
//                    $("#attendanceHrsNotification").css({
//                        "height":DivHeight,
//                        "display":"block"
//                    });
//                    $("#attendanceHrsNotificationFocus").attr('tabindex',-1).focus();
//                    $("#remarksEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#emptyEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#saveRejectNotification").css({
//                        "display":"none" 
//                    });
//                    $("#checkSelect").css({
//                        "display":"none" 
//                    });
//                    $("#60MinEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#24hrsEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#halfDayEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#onceSubmitNotification").css({
//                        "display":"none" 
//                    });
//                    $("#availableHrsNotification").css({
//                        "display":"none" 
//                    });
//                    $("#regularizeHrsNotification").css({
//                        "display":"none" 
//                    });
//                    $("#regularizeReasonNotification").css({
//                        "display":"none" 
//                    });
//                    $("#projectNotification").css({
//                        "display":"none" 
//                    });
//                    $("#monthEntryError").css({
//                        "display":"none" 
//                    });
//                    $("#policyError").css({
//                        "display":"none" 
//                    });
//                    $("#policyNotification").css({
//                        "display":"none" 
//                    });
//                    $("body").on('click','#attendanceHrsNotificationYes',function(){
//                        $("#attendanceHrsNotification").css({
//                            "display":"none" 
//                        });
//                        $('#worked_hours_'+resId).focus();  
//                        $("#buttonSubmit").show();
//                        $("#buttonSave").show();
//                    });
//                    flag++;
//                    return false;
//                }                
//            }
            if((timesheetHours > dummy_attendance_hrs) && regularize == 0 && dummy == 0 && status!='r'){
                var DivHeight = $("html").height();
                $("#regularizeReasonNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSubmit").show();
                    $("#buttonSave").show();
                });
                flag++;
                return false;
            }
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
        }
    });
    $('#selectedRows').val(JSON.stringify(jsonArr));
    if(checkCount > 0 ) {
        if(flag > 0){
            return false;
        }
        else{
            return true;
        }
    } else {
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
            $("#buttonSubmit").show();
            $("#buttonSave").show();
        });
        flag++;
        return false;
    }
    $('#buttonTable').hide();
}
function validateSaveForm(){
$("#buttonSave").hide();
$("#buttonSubmit").hide();  
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
            var statusString = $('#status_'+resId).val();            
          
            if(statusString =='r'){
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
                    $("#buttonSave").show();
                    $("#buttonSubmit").show();  
                });
                flag++;
                return false;
            }
            $('#projectSpan_'+resId).html('');
            if($('#project_'+resId).val() == '') {
                var DivHeight = $("html").height();
                $("#projectNotification").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSave").show();
                    $("#buttonSubmit").show();  
                });
                $('#projectSpan_'+resId).html("* required");
                flag++;
                return false;
            }
            var p = /^(?!0+$)[a-zA-Z0-9]+$/;
            if(p.test($('#worked_hours_'+resId).val()) == false && p.test($('#worked_minutes_'+resId).val()) == false)
            {
                var DivHeight = $("html").height();
                $("#emptyEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSave").show();
                    $("#buttonSubmit").show();  
                });
                flag++;
                return false;
            }
            
            if($('#worked_hours_'+resId).val() > 23) {
                var DivHeight = $("html").height();
                $("#24hrsEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSave").show();
                    $("#buttonSubmit").show();  
                });
                flag++;
                return false;
            }
            if($('#worked_minutes_'+resId).val() > 59) {
                var DivHeight = $("html").height();
                $("#60MinEntryError").css({
                    "height":DivHeight,
                    "display":"block"
                });
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
                    $("#buttonSave").show();
                    $("#buttonSubmit").show();  
                });
                flag++;
                return false;
            }
            var worked_hours = ($('#worked_hours_'+resId).val().length > 0 ?  $('#worked_hours_'+resId).val() : "00")
            + ":" +
            ($('#worked_minutes_'+resId).val().length > 0 ?  $('#worked_minutes_'+resId).val() : "00");
//            var roleVal;
//            if($('#role_'+resId).val() == '--Role--'){
//                roleVal = null;
//            }else{
//                roleVal = $('#role_'+resId).val();
//            }
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
        }
    });
    $('#selectedRows').val(JSON.stringify(jsonArr));
    if(checkCount > 0 ) {
        if(flag > 0){
            return false;
        }
        else{            
            return true;
        }
    } else {
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
            $("#buttonSave").show();
            $("#buttonSubmit").show();
        });
        return false;
    }
    $('#buttonTable').hide();
}
</script>

<table width="100%">
    <tr>
        <td width="30%" align="left" style="padding-left:10px;">
            <c:choose>
                <c:when test="${previous_month != '' && previous_year != ''}">
                    <a href="javascript:;" onClick="loadTimesheetPrevious(${previous_month},${previous_year},'${status}')"><h4><< Précédent</h4></a>
                </c:when>
                <c:otherwise>
                    <h4><< Précédent</h4>
                </c:otherwise>
            </c:choose>

        </td>
        <td align="center"><h2><font color="orange">${Month} ${Year}</font></h2></td>
        <td width="30%" align="right" style="padding-right:10px;">
            <c:choose>
                <c:when test="${next_month != '' && next_year != ''}">
                   <a href="javascript:;" onClick="loadTimesheetNext(${next_month},${next_year},'${status}')"><h4>Prochain >></h4></a>
                </c:when>
                <c:otherwise>
                    <h4>Prochain >></h4>
                </c:otherwise>
            </c:choose>
        </td>
    </tr>
</table>

<input type="hidden" id="totalCount" value="${fn:length(timeSheetObj)}" />
    <!--<div class="hours_detalis" id="hours_explanation">Hours Description - Click to view</div>-->
    <div class="hours_description" id="hours_desc" style="width: 98.75%; border: 1px solid #CEDFF1;background: url(images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;border-radius: 5px; margin: 0px 5px 10px 5px; padding: 10px 0px; background-size: 100% 100%;">
        <div>
            <ul class="notice_page_ul">
                <li>Heures de travail: correspondent au temps minimum requis pour les associés à la présence au bureau, comprenant l'heure du déjeuner. Ces données sont automatiquement remplies par le système en fonction de l'emplacement.</li>
                <li>Heures de présence: correspondent aux données d’entrée et de sortie pour chaque associé. Ces données sont automatiquement remplies à partir du système de carte à balayage</li>                                                                       
                <li>Heures disponibles: c'est le temps de travail productif. Les associés sont censés concentrer leurs efforts autant ou plus que ce temps de travail productif. Ces données sont automatiquement remplies par le système en fonction de l'emplacement.</li>
                <li>Heures de feuille de temps: correspondent aux efforts réels de l'associé. Les heures de feuille de temps sont censées être égales ou supérieures aux heures disponibles. Ces données doivent être entrées manuellement par l'associé.</li>
            </ul>
        </div>
    </div>

<table class="roleWorkLocTable" style="width: 99%;margin: 0px auto; border: 1px solid #BDC9D1;background: url(images/box-strip.jpg) repeat-x scroll top center;border-radius: 5px; background-size: 100% 100%;">
   <tbody>
   </tbody>
</table>

<table class="tableStructure" border="0">
    <tr class="headerCenter">
        <th style ="width:25px;padding: 3px;"><input type="checkbox" name='checkall' id="checkAll" onClick="TimesheetCheckAll(this.id)" /></th>
        <th style="width : 8%">Date</th>
        <th width="14%">Projet<span style="color:red; margin-left:3px;">*</span></th>
        <th width="10%">Décalage</th>
        <th width="10%">Rôle</th>
        <th width="10%">Phase</th>
        <th width="10%">Tâche</th>
        <th width="5%">heures de travail (HH:MM)</th>
        <th width="5%">Heures de présence (HH:MM)</th>
        <th width="5%">Heures disponibles (HH:MM)</th>
        <th width="12%">Heures de la feuille de temps (HH:MM)<span style="color:red;margin-left:3px;">*</span></th>
        <th width="8%">Régularisation</th>
        <th width="15%">Remarques</th>
        <th width="8%">Statut</th>
        <th width="3%" class="actionToSave">Action</th>
    </tr>
    <input type="hidden" value="${year_count}" id="year_count">
    <input type="hidden" value="${month_count}" id="month_count">
    <input type="hidden" value="${WFH_Month}" id="WFH_Month_count">
    <input type="hidden" value="${WFH_Year}" id="WFH_Year_count">
    <input type="hidden" value="${inExceptionList}" id="inExceptionList">
    
    <c:forEach items="${timeSheetObj}" var="Resultset" varStatus="index">
        <c:if test="${Resultset.status=='a' || Resultset.status=='r'}">
            <script type="text/javascript">
                $(document).ready(function() {
                    $("#viewComments_"+${index.count}).wTooltip({
                        content: "${Resultset.rejected_remarks}",
                        fadeIn: 600,
                        fadeOut: "slow"
                    });
                });
            </script>
        </c:if>        
        <c:set var="disabledField" value=""></c:set>        
        <c:if test="${Resultset.leave=='1'}">
            <c:set var="disabledField" value="disabled"></c:set>
        </c:if>
        <c:if test="${(Resultset.available_hours=='00:00')}">
            <c:set var="disabledField" value="disabled"></c:set>
        </c:if>
        <c:if test="${(Resultset.status=='a') || (Resultset.status=='m')}">            
            <c:set var="disabledField" value="disabled"></c:set>
        </c:if>
        <c:if test="${editable==0}">
            <c:set var="disabledField" value="disabled"></c:set>
        </c:if>
        <tr id="tr_${index.count}" >
        <input ${disabledField} type="hidden" value="#@${index.count}@#" name="res_hiddenId" />
        <input ${disabledField} type="hidden" value="${Resultset.timesheet_id}" name="res_autoId" id="res_autoId_${index.count}" />
        <input ${disabledField} type="hidden" value="${Resultset.leave_status}" name="half_day_leave" id="half_day_leave_${index.count}" />
        <input ${disabledField} type="hidden" class="timeClass${Resultset.timesheet_date}" value="${Resultset.timesheet_date}" name="res_timesheet_date" id="timesheet_date_${index.count}" />
        <input ${disabledField} type="hidden" value="${Resultset.week_day}" id="week_day_${index.count}" />
        <input ${disabledField} type="hidden" id="employee_id" value="${employee_id}">
        <input ${disabledField} type="hidden" value="${Resultset.status}" id="status_${index.count}">
        <input ${disabledField} type="hidden" value="${Resultset.isWeekEnd}" id="weekEnd_${index.count}">
        <input ${disabledField} type="hidden" value="${Resultset.holiday_description}" id="holiday_description_${index.count}">
        <input type="hidden" class="dayCount_${Resultset.timesheet_date}" value="1" />
        <td><input ${disabledField} type="checkbox" name="res_check" id="check_${index.count}" value="#@${index.count}@#" class="${(disabledField == 'disabled' || Resultset.isWeekEnd == 'Y' )?'checkval':'checkval checkbox'} perDayCheck_${Resultset.timesheet_date}" onClick="calculateTotalWorkedHours(); calculateTotalAvailableWorkedHours(${Resultset.timesheet_date})"/></td>
        <td style="padding: 0px; text-align: center;">
            <c:choose>
                <c:when test="${Resultset.holiday_description != ''}">
                    <script type="text/javascript">
                        $(document).ready(function() {
                            $("#holiday_"+${index.count}).wTooltip({
                                content: "${Resultset.holiday_description}",
                                fadeIn: 600,
                                fadeOut: "slow"
                            });
                        });
                    </script>
                    <span id="holiday_${index.count}">
                        ${Resultset.date_display}
                        <img src="${pageContext.request.contextPath}/images/holiday.png" />
                    </span>
                </c:when>
                <c:otherwise>
                    ${Resultset.date_display}
                </c:otherwise>                             
            </c:choose>
        </td>
        <td style="text-align:center;">
            <select ${disabledField} name="res_project" id="project_${index.count}" style="width:100%; text-align:center" onChange="loadAllActivities(this.value,${employee_id},'${Resultset.timesheet_datex}',${index.count})">
                <option value="">--Projet--</option>
                <c:forEach  items="${Resultset.projectMap}" var="prjList" >
                    <option title="${prjList.value}" ${(Resultset.project_id == prjList.key)?'selected':''} value="${prjList.key}">${prjList.value}</option>
                </c:forEach>
                <option title="Non Project Activity" ${(Resultset.project_id == 'Non_Project_Activity')?'selected':''} value="Non_Project_Activity">Non Project Activity</option>
            </select>
            <span class="required" id="projectSpan_${index.count}"></span>
        </td>
        <td style="text-align:center;">
            <select ${disabledField} name="res_shift" id="shift_${index.count}" style="width:100%">
                <c:forEach  items="${shift_list}" var="shiftList" >
                    <option title="${shiftList.configuration_value}" ${(Resultset.shift == shiftList.configuration_key)?'selected':''} value="${shiftList.configuration_key}">${shiftList.configuration_value}</option>
                </c:forEach>
            </select>
        </td>
        <td>
            <select ${disabledField} ${(Resultset.project_id  == 'Non_Project_Activity')?'disabled':''} name="res_role" id="role_${index.count}" style="width:100%" >
                <c:choose>
                    <c:when test="${(fn:length(Resultset.roleMap)) > 0}">
                        <c:forEach  items="${Resultset.roleMap}" var="roleList" >
                            <option title="${roleList.value}" ${(Resultset.role_id == roleList.key)?'selected':''} value="${roleList.key}">${roleList.value}</option>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <option value="--Role--">--Rôle--</option>
                    </c:otherwise>
                </c:choose>
            </select>
            <span class="required" id="roleSpan_${index.count}"></span>
        </td>
                 
        <td style="text-align:center;">
            <select ${disabledField} name="res_phase" id="phase_${index.count}" style="width:100%" onChange="loadTask(this.value,${index.count})">
                <option value="Others">Autres</option>
                <c:forEach  items="${Resultset.phaseMap}" var="phaseList" >
                    <option title="${phaseList.value}" ${(Resultset.phase_id == phaseList.key)?'selected':''} value="${phaseList.key}">${phaseList.value}</option>
                </c:forEach>
                <option title="Non Billable Activity" ${(Resultset.phase_id == 'Non billable activity')?'selected':''} value="Non billable activity">Non Billable Activity</option>
            </select>
        </td>
        <td style="text-align:center;">
            <select ${disabledField} name="res_task" id="task_${index.count}" style="width:100%" >
                <option value="Others">Autres</option>
                <c:forEach  items="${Resultset.taskMap}" var="taskList" >
                    <option title="${taskList.value}" ${(Resultset.task_id == taskList.key)?'selected':''} value="${taskList.key}">${taskList.value}</option>
                </c:forEach>
            </select>
        </td>
        
        <c:choose>
            <c:when test="${((Resultset.attendance_hours < Resultset.available_hours))&&(Resultset.isWeekEnd != 'Y')&&(Resultset.leave !='1')&&(Resultset.holiday_description == '')}">           
                <td style="text-align: center" bgcolor="#ffeacc" >
                </c:when>
                <c:otherwise>
                <td style="text-align: center">
                </c:otherwise>
            </c:choose>           
            <input type="text" name="res_office_hours" class="office_hrs_${Resultset.timesheet_date}" disabled style="width:40px; text-align:center;" id="office_hours_${index.count}" value="${Resultset.office_hours}">           
        </td>
        
        <c:choose>
            <c:when test="${((Resultset.attendance_hours < Resultset.available_hours))&&(Resultset.isWeekEnd != 'Y')&&(Resultset.leave !='1')&&(Resultset.holiday_description == '')}">           
                <td style="text-align: center" bgcolor="#ffeacc" >
                </c:when>
                <c:otherwise>
                <td style="text-align: center">
                </c:otherwise>
            </c:choose>
            <input type="text" name="res_attended_hours" disabled style="width:60px; text-align:center;" id="attended_hours_${index.count}" value="${Resultset.attendance_hours}">
        </td>
        
        <c:choose>
            <c:when test="${((Resultset.attendance_hours < Resultset.available_hours))&&(Resultset.isWeekEnd != 'Y')&&(Resultset.leave !='1')&&(Resultset.holiday_description == '')}">           
                <td style="text-align: center" bgcolor="#ffeacc" >
                </c:when>
                <c:otherwise>
                <td style="text-align: center">
                </c:otherwise>
            </c:choose>           
            <input type="text" name="res_available_hours" class="avail_hrs_${Resultset.timesheet_date}" disabled style="width:40px; text-align:center;" id="available_hours_${index.count}" value="${Resultset.available_hours}">           
        </td>
        
        <td style="text-align:center;">
            <input ${disabledField} type="text" class="capHours" name="res_worked_hours" id="worked_hours_${index.count}" value="${Resultset.worked_hours}" style="width:21px;" maxlength="2" onChange="calculateTotalWorkedHours()" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,0,false);" />&nbsp;:
            <input ${disabledField} type="text" class="capMins" name="res_worked_minutes" id="worked_minutes_${index.count}" value="${Resultset.worked_minutes}" style="width:21px;" maxlength="2" onChange="calculateTotalWorkedHours()" onkeypress="return blockNonNumbers(this, event, false, false);" onkeyup="return extractNumber(this,0,false);" />
        </td>
        <td>
            <select ${disabledField} name="res_shift" id="regularize_${index.count}" style="width:75px" onchange="policyConfigurationPopUp(this.id,this.value)">
                <option value="">--Raison--</option>
                <c:forEach  items="${Resultset.reg_reason_list}" var="regular_reason_List" >
                    <option title="${regular_reason_List.configuration_value}" ${(Resultset.regularization_reason == regular_reason_List.configuration_value)?'selected':''} value="${regular_reason_List.configuration_value}">${regular_reason_List.configuration_value}</option>
                </c:forEach>
            </select>
        </td>   
        <td style="text-align:center;">
            <textarea ${disabledField} type="text" name="res_remarks" id="remarks_${index.count}" cols="12" rows="1" style="resize:vertical; max-height: 100px;" maxlength="200">${Resultset.remarks}</textarea>
            <span class="required" id="remarksSpan_${index.count}"></span>
            <c:if test="${Resultset.status =='r'}">
            <span id="viewComments_${index.count}" style ="color:red;cursor:pointer;">RM/PM Remarques</span>
            </c:if>
        </td>
        <td style="text-align:left;">
            <span id="statusText_${index.count}">
                ${Resultset.status_text}
            </span><br>

        </td>
        
        <td id="actionItems_${index.count}"style="padding:2px ;text-align:left;" class="actionToSave">
            <c:if test="${Resultset.status !='a'}">
                <c:if test="${Resultset.status !='m'}">
                    <c:if test="${!(Resultset.leave =='1' && Resultset.leave_status =='0')}">    
                        <c:if test="${Resultset.isSubmitted == 'N'}">
                            <c:if test="${((Resultset.available_hours != '00:00'))}">
                                <c:if test="${editable != 0}">
                                    <a href="javascript:;" id="add_${index.count}"onClick="duplicateRow(this,${index.count})" style="text-decoration:none;"><img src="${pageContext.request.contextPath}/images/tm_add.png" /></a>
                                </c:if>
                            </c:if>
                        </c:if>
                    </c:if>
                </c:if>
            </c:if >
            <c:if test="${Resultset.status =='s'}">
                <a onClick="removeSavedData(this.id)" id="${Resultset.timesheet_id}"> <img src="/iDeal_timesheet/images/icon-delete.png" /></a>
            </c:if>
        </td>
    </tr>
</c:forEach>
<c:choose>
    <c:when test="${fn:length(timeSheetObj) != 0}">
        <tr class="headerCenter">
            <th colspan="10" align="right">Total heures</th>
            <td style="text-align:center;">
                <input type="text" name="worked_hours" id="worked_hours" value="" style="width:23px;" readonly />&nbsp;:
                <input type="text" name="worked_minutes" id="worked_minutes" value="" style="width:23px;" readonly />
            </td>
            <th colspan="4"></th>
        </tr>
    </c:when>
    <c:otherwise>
        <tr class="odd">
            <td colspan="15" style="text-align: center">
                <script>
                    document.getElementById("checkAll").style.visibility = "hidden";  
                </script>
               Aucune donnée à afficher
            </td>
        </tr>
    </c:otherwise>
</c:choose>
</table>
<c:if test="${fn:length(timeSheetObj) != 0}">
    <div class="actionToSave" style="text-align:center;margin:0 0 20px 20px;" >
        <input type="hidden" name="saveStatus" id="saveStatus" value="s"/>
        <input type="button" name="buttonSave" id="buttonSave" value="Enregistrer" class="qualitysave"/>
        <input type="button" name="buttonSubmit" id="buttonSubmit" value="Soumettre" class="submitbutton"/>    
        <input type="hidden" name="selectedRows" id="selectedRows" value=""/>
    </div>
</c:if>
