/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
              
function getEmpList(prjId,selectedList){
    var list = $('#approveType').val();
    $.ajax({
        type: 'POST',
        url: 'getEmployeeList.htm?prjId='+prjId+'&selectedList='+selectedList,
        dataType: "html",  
        success: function(response) {
            $('#empName').html(response);
        }
    }); 
}

function getPrjDetails(prjId){    
    var list = $('#approveType').val();
    $.ajax({
        type: 'POST',
        url: 'getPrjDetails.htm?prjId='+prjId+'&list='+list,
        dataType: "html",  
        success: function(response) {
            $('#prjDetails').html(response);            
        }
    }); 
}

function getProjectStartYear(empId){
    var prjId = $('#projectName').val();
    $.ajax({
        type: 'POST',
        url: 'getProjectAssigenedYear.htm?empId='+empId+'&prjId='+prjId,
        dataType: "html",   
        success: function(response) {
            $('#year').html(response);
        }
    }); 
}
 
function filterData(prjName,fromDate,toDate,regularName)
{
    var DivHeight = $("html").height();
    if(prjName !="" && fromDate!="" && toDate!=""){
            startLoading();
            $('#timesheetApproval').attr("action", "filterData.htm");
            document.timesheetApproval.submit();
        }else if(prjName =="" ){
              //alert("Please select project name");
             
            $("#checkProject").css({
                "height":DivHeight,
                "display":"block"
            });
            $("#checkProjectYes").click(function(){
                $("#checkProject").css({
                    "display":"none" 
                });
                $('#projectName').focus();  
            });
           $("#checkProjectFocus").attr('tabindex',-1).focus();
        }
        else if(fromDate =="" ){
            // alert("Please select From Date");
            $("#checkFromDate").css({
                "height":DivHeight,
                "display":"block"
            });
            $("#checkFromDateYes").click(function(){
                $("#checkFromDate").css({
                    "display":"none" 
                });
                  $('#fromDate').focus();  
            });
           $("#checkFromDateFocus").attr('tabindex',-1).focus();
        }
        else if(toDate =="" ){
            // alert("Please select To Date");
            $("#checkToDate").css({
                "height":DivHeight,
                "display":"block"
            });
            $("#checkToDateYes").click(function(){
                $("#checkToDate").css({
                    "display":"none" 
                });
                  $('#toDate').focus();  
            });
         $("#checkToDateFocus").attr('tabindex',-1).focus();  
        }else{}
}

//function formSubmit(submitValue){
//    var ck = new Array();
//    var len=0;
//    var chklength=0;
//    var hrsLength =0;
//    ck = document.getElementsByName("chk_data");
//    
//    for (var i = 0; i < ck.length; i++){
//        if ($('#chk_data_'+i).is(':checked')){
//            chklength = chklength + 1;
//            if(chklength > 0){
//                var appHours = $('#appHrs_'+i).val();
//                var appMinutes = $('#appMins_'+i).val();
//                if((appHours != '') || (appMinutes != '')){
//                    if(isNaN(appHours) || isNaN(appMinutes))
//                    {
//                        hrsLength = hrsLength+1;
//                    }
//                    else{
//                        len = len+1;        
//                    }
//                }
//    
//                if(hrsLength >0){
//                    alert("Approved Hours fields value should be numeric!");
//                }
//                else{
//                    if(len > 0){
//                        document.timesheetApproval.actionValue.value=submitValue;
//                        $('#timesheetApproval').attr("action", "approverSubmit.htm");
//                        document.timesheetApproval.submit();
//                    }
//                    else{ 
//                         alert("Approved Hours Column should not be empty!");
////                        alert("Please select check-box before submit the Time sheet");
//                    }
//                }
//            }
//            else{
//                 alert("Please select check-box before submit the Time sheet");
//            }
//        }
//    }
//
//    if(chklength == 0){
//        alert("Please select check-box before submit the Time sheet");
//    }
//}


function formSubmit(submitValue){
//    alert(":::::");
  var temp;
    var checkedCount = 0;
    var emptyCount = 0;
    var hrsCount=0;
    var minsCount=0;
    var remarksEmptyCount = 0;
    var regReasonCount=0;
    var ck = new Array();
    ck = document.getElementsByName("chk_data");
    $(":checkbox").each(function() {
        if($('#'+this.id).attr('checked')) {
            checkedCount++;
            var id_arr = (this.id).split("~~");
            var resId = id_arr[0].split("_");
            var idValue = resId[1];
            temp=resId;
            if(idValue != 'all'){
                var appHrs = $('#appHrs_'+resId[2]).val();
                var appMins= $('#appMins_'+resId[2]).val();
                if(appHrs != '' && appMins !=''){
                    var hrs = ($('#appHrs_'+resId[2]).val().length > 0 ? $('#appHrs_'+resId[2]).val() : '00');
                    var mins = ($('#appMins_'+resId[2]).val().length > 0 ? $('#appMins_'+resId[2]).val() : '00');

                    hrs = hrs.length == 1 ? '0'+hrs : hrs;
                    mins = mins.length == 1 ? '0'+mins : mins;

                    $('#appHrs_'+resId[2]).val(hrs);
                    $('#appMins_'+resId[2]).val(mins);
                }
                var p = /^(?!0+$)[a-zA-Z0-9]+$/;
                var appHrs;
               
                if(p.test($('#appHrs_'+resId[2]).val()) == false && p.test($('#appMins_'+resId[2]).val()) == false)
                {
                    emptyCount ++;
                }
                if(($('#appHrs_'+resId[2]).val())>23 )
                {
                    hrsCount++;
                }
                if(($('#appMins_'+resId[2]).val())>59 )
                {
                    minsCount++;
                }
                
                if($('#remarks_'+resId[2]).val() == ''){
                    remarksEmptyCount++;
                }
                if($('#regReason_'+resId[2]).val() != ''){
                   
                    regReasonCount ++;
                }
            }
        }
    });
    if(checkedCount > 0)
    {
        
        var DivHeight = $("html").height();
        if(submitValue == 'r'){
            if(remarksEmptyCount > 0 ){
                //alert("Enter remarks to send back the Timesheet");
            $("#checkRemarks").css({
            "height":DivHeight,
            "display":"block"
            });
            $("#checkRemarksYes").click(function(){
                $("#checkRemarks").css({
                    "display":"none" 
                });
               $('#remarks_'+temp[2]).focus(); 
            });
            $("#checkRemarksFocus").attr('tabindex',-1).focus(); 
            }
       
            else
            {
                document.timesheetApproval.actionValue.value=submitValue;
                $('#timesheetApproval').attr("action", "approverSubmit.htm");
                document.getElementById("approve").style.display='none';
                document.getElementById("reject").style.display='none';
                document.getElementById("approve1").style.display='none';
                document.getElementById("reject1").style.display='none';
                document.getElementById("save").style.display='none';
                document.getElementById("save1").style.display='none';
                document.timesheetApproval.submit();
            }
        }
        
        if(submitValue == 'a'){
            
            if(emptyCount > 0 ){
               // alert("Approved Hours should not be empty/'00' !");
            $("#checkemptyCount").css({
            "height":DivHeight,
            "display":"block"
            });
            $("#checkemptyCountYes").click(function(){
                $("#checkemptyCount").css({
                    "display":"none" 
                });
                 $('#appHrs_'+temp[2]).focus(); 
            });
            $("#checkemptyCountFocus").attr('tabindex',-1).focus(); 
          }
       
             
         if(hrsCount > 0 ){
               // alert("Approved Hours should not be greater than or equal to 24 ");
            $("#checkhrsCount").css({
            "height":DivHeight,
            "display":"block"
            });
            $("#checkhrsCountYes").click(function(){
                $("#checkhrsCount").css({
                    "display":"none" 
                });
                 $('#appHrs_'+temp[2]).focus(); 
            });
            $("#checkhrsCountFocus").attr('tabindex',-1).focus(); 
              
             }
             if(minsCount > 0 ){
                //alert("Approved Minutes should not be greater than 59 ");
            $("#checkminsCount").css({
            "height":DivHeight,
            "display":"block"
            });
            $("#checkminsCountYes").click(function(){
                $("#checkminsCount").css({
                    "display":"none" 
                });
                 $('#appMins_'+temp[2]).focus(); 
            });
            $("#checkminsCountFocus").attr('tabindex',-1).focus(); 
              
             }
    
            else
            {
               
                if(regReasonCount > 0){
                //alert("You have selected regularization entries !");
                $("#confirmBox").css({
                "height":DivHeight,
                "display":"block"
                });
                $("#confirmBoxYes").click(function(){
                    $("#confirmBox").css({
                        "display":"none" 
                    });
                        if(emptyCount == 0 && minsCount ==0 && hrsCount==0){
                        document.timesheetApproval.actionValue.value=submitValue;
                       $('#timesheetApproval').attr("action", "approverSubmit.htm");
                       document.getElementById("approve").style.display='none';
                       document.getElementById("reject").style.display='none';
                       document.getElementById("approve1").style.display='none';
                       document.getElementById("reject1").style.display='none';
                       document.getElementById("save").style.display='none';
                       document.getElementById("save1").style.display='none';
                       document.timesheetApproval.submit();
                        }else{
                            return false;
                        }
                });
                 $("#confirmBoxFocus").attr('tabindex',-1).focus(); 
                $("#confirmBoxNo").click(function(){
                    $("#confirmBox").css({
                        "display":"none" 
                    });
                        return false;
                });
                 $("#confirmBoxFocus").attr('tabindex',-1).focus(); 
          
              
             
               
//                    var retVal =  confirm("You have selected regularization entries too.\n Are you sure to approve regularization entries ?");
//                    if( retVal == true )
//                    {
//                        if(emptyCount == 0 && minsCount ==0 && hrsCount==0){
//                        document.timesheetApproval.actionValue.value=submitValue;
//                       $('#timesheetApproval').attr("action", "approverSubmit.htm");
//                       document.getElementById("approve").style.display='none';
//                       document.getElementById("reject").style.display='none';
//                       document.getElementById("approve1").style.display='none';
//                       document.getElementById("reject1").style.display='none';
//                       document.getElementById("save").style.display='none';
//                       document.getElementById("save1").style.display='none';
//                       document.timesheetApproval.submit();
//                        }else{
//                            return false;
//                        }
//                    }
//                     else{
//                        return false;
//                     }
                }               
                else{
                    if(emptyCount == 0 && minsCount == 0 && hrsCount == 0){
                        document.timesheetApproval.actionValue.value = submitValue;
                        $('#timesheetApproval').attr("action", "approverSubmit.htm");
                        document.getElementById("approve").style.display='none';
                        document.getElementById("reject").style.display='none';
                        document.getElementById("approve1").style.display='none';
                        document.getElementById("reject1").style.display='none';
                        document.getElementById("save").style.display='none';
                        document.getElementById("save1").style.display='none';
                        document.timesheetApproval.submit();
                          
                    }
                }
            }
        }
    }
    else{
         var DivHeight = $("html").height();
      //  alert("Please select check-box before submit the Time sheet");
      $("#checkBoxApprove").css({
            "height":DivHeight,
            "display":"block"
            });
            $("#checkBoxApproveYes").click(function(){
                $("#checkBoxApprove").css({
                    "display":"none" 
                });
                  $('#chk_all').focus(); 
             
            });
            $("#checkBoxApproveFocus").attr('tabindex',-1).focus(); 
      
    }
    
}


function  chkAll(idValue){
    var c = new Array();
    c = document.getElementsByTagName('input');
    if($('#'+idValue).is(':checked')){
        for (var i = 0; i < c.length; i++){
            if (c[i].type == 'checkbox')
                c[i].checked = true;
        }
    }
    else
    {
        for (var i = 0; i < c.length; i++){
            if (c[i].type == 'checkbox')
                c[i].checked = false;
        }
    }  
    calculateTotalApprovedHours();
}

function formSave(){
    var jsonArr = [];
     var DivHeight = $("html").height();
    var checkedCount = 0;
    
    $(":checkbox").each(function() {
        if($('#'+this.id).attr('checked')) {
            checkedCount++;
            var id_arr = (this.id).split("~~");
            var resId = id_arr[0].split("_");
            var idValue = resId[1];
            if(idValue != 'all'){
                var idValue = $('#chk_data_'+resId[2]).val();
                var id = idValue.split("~~");
                var hrs = ($('#appHrs_'+resId[2]).val().length > 0 ? $('#appHrs_'+resId[2]).val() : '00');
                var mins = ($('#appMins_'+resId[2]).val().length > 0 ? $('#appMins_'+resId[2]).val() : '00');
            
                hrs = (hrs.length == 1 ? '0'+hrs : hrs)+':'+(mins.length == 1 ? '0'+mins : mins);
                jsonArr.push({
                    approvedHrs : hrs,
                    approvedMins : (mins.length == 1 ? '0'+mins : mins),
                    timeSheetRemarks : $('#remarks_'+resId[2]).val(),
                    actionValue : 'm',
                    checkedId : id[0]
                });
            }
        }
    });
    //
    if(checkedCount > 0){
        $('#selectedRows').val(JSON.stringify(jsonArr));
        $('#timesheetApproval').attr("action", "approvalSave.htm");
        document.timesheetApproval.submit();
    }else{
        //alert('Please select checkbox');
        
        $("#checkBoxSave").css({
            "height":DivHeight,
            "display":"block"
            });
            $("#checkBoxSaveYes").click(function(){
                $("#checkBoxSave").css({
                    "display":"none" 
                });
               
            });
            $("#checkBoxSaveFocus").attr('tabindex',-1).focus(); 
    }
    //
}

function loadForm(page)   
{
    document.timesheetApproval.page.value = page;
    document.timesheetApproval.submit();
}


//function validateHrs(approvedValue,WorkedValue,number)
//{
//    var ck = new Array();
//    alert("approvedValue---"+approvedValue+"----WorkedValue----"+WorkedValue+"--${rpritr.index}---"+number);
//    var chked =0;
//    var notchked =0;
//    ck = document.getElementsByName("chk_data");
//    
//    //    for (var i = 0; i < ck.length; i++){
//       
//    if(approvedValue > WorkedValue || approvedValue == 00 || approvedValue== 0 || approvedValue == '')
//    {
////        if ($('#chk_data_'+number).is(':checked')){
//            alert("Approved Hours Should be below Worked Hours And above 0");
//            chked = chked + 1;
//            $('#appHrs_'+number).addClass("required");
//            $('#appMins_'+number).addClass("required");
//        }
//        else{
//           
//            $('#appHrs_'+number).removeClass("required");
//            $('#appMins_'+number).removeClass("required");
//        }
//    
//    
//    //    }
//    if(chked > 0){
//        document.getElementById("approve").style.display="none";
//        document.getElementById("reject").style.display="none";
//    }
//    else{
//        document.getElementById("approve").style.display="block";
//        document.getElementById("reject").style.display="block";
//    }
//}

