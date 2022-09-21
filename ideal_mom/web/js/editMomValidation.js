function updateMomActions(){
    var actualCloseDate = tableNullCheck("closeDate");
    var error = 0;
    if(actualCloseDate>0){
       document.getElementById("closeDate").innerHTML="Please Select Actual Close Date";
       document.getElementById("closeDate").style.display = 'block';
       error++;
       return false;
    } else{
        document.getElementById("closeDate").style.display = 'none';
    }
    if(error==0){
        return true;
    }else{
        return false;
    }
}

function updateActionValidation(){
    
var agenda = tableNullCheck("agendaText");
    var discussion = tableNullCheck("discussionText");
    var error=0;
    if(agenda>0){
        document.getElementById("agendaTextArea").innerHTML="Please Enter Agenda Points";
        document.getElementById("agendaTextArea").style.color = 'red';
        document.getElementById("agendaTextArea").style.display = 'block';
        error++;
        return false;
    } else{
        document.getElementById("agendaTextArea").style.display = 'none';
    }
    
    if(discussion>0){
        document.getElementById("discussTextArea").innerHTML="Please Enter Discussion Points";
        document.getElementById("discussTextArea").style.color = 'red';
        document.getElementById("discussTextArea").style.display = 'block';
        error++;
        return false;
    } else{
        document.getElementById("discussTextArea").style.display = 'none';
    }
    var action=[];
    var row=$('#count_row').val();
          var i=0;
               $('.check_box').each(function(){
              if($(this). attr("checked") == true){
//                  alert($(this).val());

                action[i]= $(this).val();
                console.log(action);
                console.log($(this).val());
               
                i++;
              }
               });
               console.log(action);
                $('.actionid').val(action);
                for(var i=1;i<=row;i++){
                 if ($('#action_point_'+i).val().length > 0){
                       if ($('#members_ActionPoint_'+i).val().length > 0){
                           document.getElementById("actionPointOwner").style.display = 'none';
                       }
                        else{
                           document.getElementById("actionPointOwner").innerHTML="Please Enter the action owner name/id.";
                           document.getElementById("actionPointOwner").style.color = 'red';
                           document.getElementById("actionPointOwner").style.display = 'block';
                           error++;
                           return false;
                           console.log("actionPointOwner");
                       }
                    }
                else{
                       document.getElementById("actionPoint").innerHTML="Please Enter the action owner name/id.";
                           document.getElementById("actionPoint").style.color = 'red';
                           document.getElementById("actionPoint").style.display = 'block';
//                           document.getElementById("actionPoint").style.display = 'none';
                    }
          
            if ($('#members_ActionPoint_'+i).val().length > 0){
                 if ($('#actionDate_'+i).val().length > 0){
                 document.getElementById("plannedClosedDate").style.display = 'none';
             }
                 else{
                 document.getElementById("plannedClosedDate").innerHTML="Please Enter the action planned closed date.";
                document.getElementById("plannedClosedDate").style.color = 'red';
                document.getElementById("plannedClosedDate").style.display = 'block';
                error++;
                return false;
                console.log("plannedClosedDate");
            }
            } else{
                    document.getElementById("plannedClosedDate").style.display = 'none';
                }
        
        
//        else{
//             document.getElementById("checkbox4").style.display = 'none';
//        }
           }
   
    if(error==0){
        return true;
    }else{
        return false;
    } 

}
function updateMomValidation(){

 var planned_start_time= parseInt(document.formMomDetails.planned_start_time.value);
   var planned_end_time = parseInt(document.formMomDetails.planned_end_time.value);
    var actual_start_time = parseInt(document.formMomDetails.actual_start_time.value);
    var  actual_end_time = parseInt(document.formMomDetails.actual_end_time.value);
    plan_start_end_TimeValidate(planned_start_time,planned_end_time);
    actual_start_end_TimeValidate(actual_start_time,actual_end_time);
//    var chatPanel = $("#tested");
//
//       if(!chatPanel.is(':hidden'))
//        {
////           var memberpresent = tableNullCheck("members_present");
////           var spanpresent = tableNullCheck("as-selection-item");
//             var spanpresent=$('.as-selection-item').html();
//            console.log(memberpresent);
//            console.log(spanpresent);
////           var memberabsent = tableNullCheck("members_absent");
////            console.log(memberabsent);
//             if(spanpresent==null){
//                document.getElementById("memberpresent").innerHTML=" Enter Present Members";
//                document.getElementById("memberpresent").style.color = 'red';
//                document.getElementById("memberpresent").style.display = 'block';
//                error++;
//                return false;
//            } else{
//                document.getElementById("memberpresent").style.display = 'none';
//            }
//        }
//   
////});
////});
//       else if(chatPanel.is(':hidden')){
//            var memberPresent = tableNullCheck("members_Present");
////            var memberAbsent = tableNullCheck("members_Absent");
//            console.log(memberPresent);
//            console.log(memberAbsent);
//            if(memberPresent> 0){
//                document.getElementById("memberPresent").innerHTML="Please Enter Present Members";
//                document.getElementById("memberPresent").style.color = 'red';
//                document.getElementById("memberPresent").style.display = 'block';
//                error++;
//                return false;
//            } else{
//                document.getElementById("memberPresent").style.display = 'none';
//            }
//        }
       
    var agenda = tableNullCheck("agendaText");
    var discussion = tableNullCheck("discussionText");
//    var actions = tableNullCheck("action_point");
//    var actionOwner = tableNullCheck("members_ActionPoint");
//    var planCloseDate = tableNullCheck("datepicker");
//    var comments = tableNullCheck("track_comments");
    var error=0;
    
//    if(venue == null || venue == ""){
//        document.getElementById("venue").innerHTML="Please Enter Meeting Attended Venue";
//        document.getElementById("venue").style.color = 'red';
//        document.getElementById("venue").style.display = 'block';
//        mom_venue.focus();
//        error++;
//        return false;
//    } else{
//        document.getElementById("venue").style.display = 'none';
//    }
 if(document.formMomDetails.planned_start_time.value == null || document.formMomDetails.planned_start_time.value == ""){
        document.getElementById("planned").innerHTML="Please Enter Planned Start time";
        document.getElementById("planned").style.color = 'red';
        document.getElementById("planned").style.display = 'block';
        $('#planned_start_time').focus();
        error++;
        return false;
    } else{
        document.getElementById("planned").style.display = 'none';
    }
    
    if(document.formMomDetails.planned_end_time.value == null || document.formMomDetails.planned_end_time.value == ""){
        document.getElementById("planned_end").innerHTML="Please Enter Planned end time";
        document.getElementById("planned_end").style.color = 'red';
        document.getElementById("planned_end").style.display = 'block';
        $('#planned_end_time').focus();
        error++;
        return false;
    } else{
        document.getElementById("planned_end").style.display = 'none';
    }
//    if(planned_start >= planned_end){
//             document.getElementById("planned_end").innerHTML="Please Enter valid Planned Start time";
//        }
   if(document.formMomDetails.actual_start_time.value == null || document.formMomDetails.actual_start_time.value == ""){
        document.getElementById("actual_start").innerHTML="Please Enter actual start time";
        document.getElementById("actual_start").style.color = 'red';
        document.getElementById("actual_start").style.display = 'block';
        $('#actual_start_time').focus();
        error++;
        return false;
    } else{
        document.getElementById("actual_start").style.display = 'none';
    } 
    if(document.formMomDetails.actual_end_time.value == null || document.formMomDetails.actual_end_time.value == ""){
        document.getElementById("actual_end").innerHTML="Please Enter actual end time";
        document.getElementById("actual_end").style.color = 'red';
        document.getElementById("actual_end").style.display = 'block';
        $('#actual_end_time').focus();
        error++;
        return false;
    } else{
        document.getElementById("actual_end").style.display = 'none';
    }
     duplicateCheck();
    if(memberPresent> 0){
        document.getElementById("memberPresent").innerHTML="Please Enter Present Members";
        document.getElementById("memberPresent").style.color = 'red';
        document.getElementById("memberPresent").style.display = 'block';
        error++;
        return false;
    } else{
        document.getElementById("memberPresent").style.display = 'none';
    }
    
//    if(memberAbsent> 0){
//        document.getElementById("memberAbsent").innerHTML="Please Enter Absent Members";
//        document.getElementById("memberAbsent").style.color = 'red';
//        document.getElementById("memberAbsent").style.display = 'block';
//        error++;
//        return false;
//    } else{
//        document.getElementById("memberAbsent").style.display = 'none';
//    }
    
    if(agenda>0){
        document.getElementById("agendaTextArea").innerHTML="Please Enter Agenda Points";
        document.getElementById("agendaTextArea").style.color = 'red';
        document.getElementById("agendaTextArea").style.display = 'block';
        error++;
        return false;
    } else{
        document.getElementById("agendaTextArea").style.display = 'none';
    }
    
    if(discussion>0){
        document.getElementById("discussTextArea").innerHTML="Please Enter Discussion Points";
        document.getElementById("discussTextArea").style.color = 'red';
        document.getElementById("discussTextArea").style.display = 'block';
        error++;
        return false;
    } else{
        document.getElementById("discussTextArea").style.display = 'none';
    }
   var action=[];
    var row=$('#count_row').val();
          var i=0;
               $('.check_box').each(function(){
              if($(this). attr("checked") == true){
//                  alert($(this).val());

                action[i]= $(this).val();
                console.log(action);
                console.log($(this).val());
               
                i++;
              }
               });
               console.log(action);
                $('.actionid').val(action);
                for(var i=1;i<=row;i++){
            if ($('#action_point_'+i).val().length > 0){
                  if ($('#members_ActionPoint_'+i).val().length > 0){
                      document.getElementById("actionPointOwner").style.display = 'none';
                  }
      
      
//    if(actions>0){
                else{
                    document.getElementById("actionPointOwner").innerHTML="Please Enter the action owner name/id.";
                    document.getElementById("actionPointOwner").style.color = 'red';
                    document.getElementById("actionPointOwner").style.display = 'block';
                    error++;
                    return false;
                }
            }
        else{
                document.getElementById("actionPointOwner").style.display = 'none';
            }
          
    if ($('#members_ActionPoint_'+i).val().length > 0){
         if ($('#actionDate_'+i).val().length > 0){
         document.getElementById("plannedClosedDate").style.display = 'none';
     }
         else{
         document.getElementById("plannedClosedDate").innerHTML="Please Enter the action planned closed date.";
        document.getElementById("plannedClosedDate").style.color = 'red';
        document.getElementById("plannedClosedDate").style.display = 'block';
        error++;
        return false;
    }
    } else{
            document.getElementById("plannedClosedDate").style.display = 'none';
        }
//    if(comments>0){
//        document.getElementById("actionPoint").innerHTML="Please Enter the action point tracking comments,";
//        document.getElementById("actionPoint").style.color = 'red';
//        document.getElementById("actionPoint").style.display = 'block';
//        error++;
//        return false;
//    } else{
//        document.getElementById("actionPoint").style.display = 'none';
//    }
                }
   if(!plan_start_end_TimeValidate(planned_start_time,planned_end_time)){
        error++;
    }
    if(!actual_start_end_TimeValidate(actual_start_time,actual_end_time)){
        error++;
    }
    
    if( !duplicateCheck()){
        error++;
    }  
    if(!sameFieldDuplicate("members_Present","memberPresent")){
        error++;
    }
    if(!sameFieldDuplicate("members_Absent","memberAbsent")){
        error++;
    }
   
    if(error==0){
        return true;
    }else{
        return false;
    }  
//    return error;
//    $('#status').val('o');
//    $('#formMomEdit').attr("action", "insertMom.htm");
//    document.formMomEdit.submit();    
//    startLoading();
}

function blockNonNumbers(obj, e, allowDecimal, allowNegative) {
    var key;
    var isCtrl = false;
    var keychar;
    var reg;

    if(window.event) {
        key = e.keyCode;
        isCtrl = window.event.ctrlKey
    }
    else if(e.which) {
        key = e.which;
        isCtrl = e.ctrlKey;
    }

    if (isNaN(key)) return true;

    keychar = String.fromCharCode(key);
    // check for backspace or delete, or if Ctrl was pressed
    if (key == 8 || isCtrl)
    {
        return true;
    }

    reg = /\d/;
    var isFirstN = allowNegative ? keychar == '-' && obj.value.indexOf('-') == -1 : false;
    var isFirstD = allowDecimal ? keychar == '.' && obj.value.indexOf('.') == -1 : false;

    return isFirstN || isFirstD || reg.test(keychar);
}

function plan_start_end_TimeValidate(planned_start_time,planned_end_time){
    var error=0;
  var a=$('#planned_start_time').val();
  var b=a.split(":");
  var c=b[0];
 var x = parseInt(c);
  var d=b[1];
  var y=parseInt(d);
  var planned_start_time= x* 60 + y;
  var e=$('#planned_end_time').val();
  var f=e.split(":");
  var g=f[0];
  var w = parseInt(g);
  var h=f[1];
   var z=parseInt(h);
  var planned_end_time= w * 60 + z;
  
    if((parseInt(planned_start_time) >= parseInt(planned_end_time)))
    {   
        $('#plannedEndTimeTemp').text("Please ensure that planned start time should be less than end time.");
         $('#plannedEndTimeTemp').show();
         error++;
         return false;
            }
        
       else {
        $('#plannedEndTimeTemp').hide();
        return true;
    }
    if(error==0){
        return true;
    }else{
        return false;
    }
}
    
function actual_start_end_TimeValidate(actual_start_time,actual_end_time){
    var error=0;
       var error=0;
  var a=$('#actual_start_time').val();
  var b=a.split(":");
  var c=b[0];
  var x = parseInt(c);
  var d=b[1];
  var y=parseInt(d);
  var actual_start_time= x * 60 + y;
  var e=$('#actual_end_time').val();
  var f=e.split(":");
  var g=f[0];
  var w = parseInt(g);
  var h=f[1];
  var z=parseInt(h);
  var actual_end_time= w* 60 + z;
    if((parseInt(actual_start_time) >= parseInt(actual_end_time)))
    {   
        $('#actualEndTimeTemp').text("Please ensure that actual start time should be less than end time.");
        $('#actualEndTimeTemp').show();
        //                alert("Please ensure that start time should be less than end time.");
        error++;
        return false;
            
        }
       else {
        $('#actualEndTimeTemp').hide();
        return true;
    }
    if(error==0){
        return true;
    }else{
        return false;
    }
}

function tableNullCheck(class_name) {
    var NullError = 0;
    var class_length = document.querySelectorAll("." + class_name).length;
    for (var i = 0; i < class_length; i++) {
        var class_name_val = document.querySelectorAll("." + class_name)[i].value;
        var class_name_val_length = $.trim(class_name_val).length;
        if (class_name_val_length > 0) {
            document.querySelectorAll("." + class_name)[i].style.outline = "none";
        } else {
            document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
            NullError++;
        }
    }
    return NullError;
}

function duplicateCheck() {
    var error=0;
//    var present_inputs=document.querySelectorAll(".members_Present").value;
//    var absent_inputs=document.querySelectorAll(".members_Absent").value;
     var present_inputs=$(".members_Present").val();
     var present_name=[];
     present_name =present_inputs.split(";");
     present_name.pop();
     console.log("checking");
     console.log(present_name);
        var p =[];
        p=present_name[0];
     var absent_inputs=$(".members_Absent").val();
     var absent_name=[];
      absent_name = absent_inputs.split(";");
      absent_name.pop();
            var a=[];
            a=absent_name[0];
    for(var i = 0; i < present_name.length; i++){
       for(var j = 0; j < absent_name.length; j++){
            console.log(a);
            console.log(p);
            if(present_name[i] == absent_name[j]){
                $("#absent_span").html("Employee is already available in Members Present.");
                $("#absent_span").show();
                error++;
            }else {
                $("#absent_span").hide();
            }
        }
    }
    if(error==0){
        return true;
    }else{
        return false;
    }
}


function sameFieldDuplicate(pName,pError,pDelete){
    var members= $("."+pName).map(function() {
        return $(this).val();
    }).get();
//    console.log(members);
    var sorted_arr = members.slice().sort(); 
    var results = [];
    var j =0;
    var k=0;
    for (var i = 0; i < sorted_arr.length - 1; i++) {
         j=i+1;
         k=j+1;
//         console.log(sorted_arr[j]);
//         console.log(sorted_arr[i]);
//         console.log($("#"+pDelete+j).val());
//         console.log($("#"+pDelete+k).val());
        if (sorted_arr[i+1] == sorted_arr[i] &&$("#"+pDelete+j).val()!=1 && $("#"+pDelete+k).val()!=1) {
             $("#"+pError).html("Employee is already selected.");
         $("#"+pError).show();
        return false;
//        break;
        }
    }
        $("#"+pError).hide();
        return true;
}