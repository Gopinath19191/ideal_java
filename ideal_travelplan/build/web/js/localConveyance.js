/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

$(function() {
    $("#submitBtn").click(function(){
        $('#action').val('submit');
    });
    $("#cancelBtn").click(function(){
        $('#action').val('cancel');
    });
    $("#saveBtn").click(function(){
        $('#action').val('save');
    });
    
    $('#projectTravel').change(function() {
        if($("#projectTravel").val()=='Y'){
            $("#project").removeAttr("disabled");
        }
        else if($("#projectTravel").val()=='N'){
            $("#project").attr("disabled", "disabled");
        }else{
            $("#project").removeAttr("disabled");
            $("#project").val("--Selct Project--");
        }
    });
    $('#project').change(function() {
        if($("#project").val() == -1){
            $('#otherProjects').val(" ");
            $("#otherProjects").css("display","table-row");
                  
        }
        else{
            $('#otherProjects').val(" ");
            $("#otherProjects").css("display","none");
        }
    });
    $('#guestBooking').change(function() {
        if($("#guestBooking").val() =='Y' ){
            $("#travelGuestName").css("display","table-row");
        }
        else{
            $("#travelGuestName").val(" ");
            $("#travelGuestName").css("display","none");
        }
    });
  
    if($('#onwardPickUpPoint').val() == -3 || $('#onwardPickUpPoint').val() == -2 || $('#onwardPickUpPoint').val() == -1){
        if($('#onwardPickUpPoint').val() == -3){
            $('#pickUpPlace').html("Customer Office Name");
        }
        else if($('#onwardPickUpPoint').val() == -2){
            $('#pickUpPlace').html("Hotel Name");
        }
        else{
            $('#pickUpPlace').html("Door Name");
        }

        $("#pickUpDetails").css("display","table-row");
        $("#pickUpLandmark").css("display","table-row");
    }
    else{
        $('#onwardPickupDoorNo').val(" ");
        $('#onwardPickupStreetName').val(" ");
        $('#onwardPickupArea').val(" ");
        $('#onwardPickupLandMark').val(" ");
        
        $("#pickUpDetails").css("display","none");
        $("#pickUpLandmark").css("display","none");
    }
    if($('#onwardDropJourney').val() == -3 || $('#onwardDropJourney').val() == -2 || $('#onwardDropJourney').val() == -1){
        if($('#onwardDropJourney').val() == -3){
            $('#dropPlace').html("Customer Office Name");
        }
        else if($('#onwardDropJourney').val() == -2){
            $('#dropPlace').html("Hotel Name");
        }
        else{
            $('#dropPlace').html("Door Name");
        }
        $("#dropDetails").css("display","table-row");
        $("#dropLandmark").css("display","table-row");
    }
    else{
        $('#onwardDropDoorNo').val(" ");
        $('#onwardDropStreetName').val(" ");
        $('#onwardDropArea').val(" ");
        $('#onwardDropLandMark').val(" ");
            
        $("#dropDetails").css("display","none");
        $("#dropLandmark").css("display","none");
    }
    if($('#returnPickUpPoint').val() == -3 || $('#returnPickUpPoint').val() == -2 || $('#returnPickUpPoint').val() == -1){
        if($('#returnPickUpPoint').val() == -3){
            $('#returnPickup').html("Customer Office Name");
        }
        else if($('#returnPickUpPoint').val() == -2){
            $('#returnPickup').html("Hotel Name");
        }
        else{
            $('#returnPickup').html("Door Name");
        }
        $("#returnPickUpDate").css("display","table-row");
        $("#returnPickUpTime").css("display","table-row");
        $("#returnPickupDetails").css("display","table-row");
        $("#returnPickupLandMark1").css("display","table-row");
    }
    else{
        $('#returnPickupDoorNo').val("");
        $('#returnPickupStreetName').val("");
        $('#returnPickupLandMark').val("");
        $('#returnPickupArea').val("");
        
        $("#returnPickupDetails").css("display","none");
        $("#returnPickupLandMark1").css("display","none");
      
    }
    if($('#returnDropJourney').val() == -3 || $('#returnDropJourney').val() == -2 || $('#returnDropJourney').val() == -1){
        if($('#returnDropJourney').val() == -3){
            $('#returnDrop').html("Customer Office Name");
        }
        else if($('#returnDropJourney').val() == -2){
            $('#returnDrop').html("Hotel Name");
        }
        else{
            $('#returnDrop').html("Door Name");
        }
        $("#returnDropDoorNo").css("display","table-row");
        $("#returnDropStreetName").css("display","table-row");
        $("#returnDropArea").css("display","table-row");
        $("#returnDropLandMark").css("display","table-row");
    }
    else{
        $('#returnDropDoorNo').val("");
        $('#returnDropStreetName').val("");
        $('#returnDropArea').val("");
        $('#returnDropLandMark').val("");

        $("#returnDropDetails").css("display","none");
        $("#returnDropLandmark").css("display","none");
    }

    if($("#seperateCab").attr("checked")==true){
        
        $("#seperatePickupDetails").css("display","table-row");
        $("#seperateDropDetails").css("display","table-row");
        if($('#returnPickUpPoint').val() == -3 || $('#returnPickUpPoint').val() == -2 || $('#returnPickUpPoint').val() == -1){
            if($('#returnPickUpPoint').val() == -3){
                $('#returnPickup').html("Customer Office Name");
            }
            else if($('#returnPickUpPoint').val() == -2){
                $('#returnPickup').html("Hotel Name");
            }
            else{
                $('#returnPickup').html("Door Name");
            }
            $("#returnPickUpDate").css("display","table-row");
            $("#returnPickUpTime").css("display","table-row");
            $("#returnPickupDetails").css("display","table-row");
            $("#returnPickupLandMark1").css("display","table-row");
        }
        if($('#returnDropJourney').val() == -3 || $('#returnDropJourney').val() == -2 || $('#returnDropJourney').val() == -1){
            if($('#returnDropJourney').val() == -3){
                $('#returnDrop').html("Customer Office Name");
            }
            else if($('#returnDropJourney').val() == -2){
                $('#returnDrop').html("Hotel Name");
            }
            else{
                $('#returnDrop').html("Door Name");
            }
            $("#returnDropDetails").css("display","table-row");
            $("#returnDropLandmark").css("display","table-row");
        }
    }

    $("#link11").click(function(){
        var url =$(this).attr("href");
        $('#right-pane').load(url);
    });

//    function changeContent(){
//        document.getElementById('link1').load('/localconveyance/getHistoryDetails.htm?page=1&newLCNumber=60');
//
//    }
});


function returnType(value){
    if(value == '0'){
        document.getElementById("seperatePickupDetails").style.display='none';
        document.getElementById("seperateDropDetails").style.display='none';
        document.getElementById("returnPickupDetails").style.display='none';
        document.getElementById("returnPickupLandMark1").style.display='none';
        document.getElementById("returnDropDetails").style.display='none';
        document.getElementById("returnDropLandmark").style.display='none';
        $('#returnPickUpPoint').val(" ");
        $('#returnDropJourney').val(" ");
        $('#returnPickupDoorNo').val(" ");
        $('#returnPickupStreetName').val(" ");
        $('#returnPickupArea').val(" ");
        $('#returnPickupLandMark').val(" ");
        $('#returnDropDoorNo').val(" ");
        $('#returnDropStreetName').val(" ");
        $('#returnDropArea').val(" ");
        $('#returnDropLandMark').val(" ");
    }
    else{
        document.getElementById("seperatePickupDetails").style.display='table-row';
        document.getElementById("seperateDropDetails").style.display='table-row';
        document.getElementById("returnPickUpTime").style.display='table-row';
        document.getElementById("returnPickUpDate").style.display='table-row';
    }
}

function selectTravelDetails(value,details,landMark,pickupLabel){
    if(value == '-1'){
        $('#'+pickupLabel).html("Door No");
    }else if(value == '-2')
    {
        $('#'+pickupLabel).html("Hotel Name");
    }
    else if(value == '-3'){
        $('#'+pickupLabel).html("Customer Office Name");
    }
    
    if(value=='-1' || value =='-2' || value == '-3'){
        document.getElementById(details).style.display='table-row';
        document.getElementById(landMark).style.display='table-row';
    }
    else{
        document.getElementById(details).style.display='none';
        document.getElementById(landMark).style.display='none';
        if(details=='pickUpDetails'){
            $('#onwardPickupDoorNo').val(" ");
            $('#onwardPickupStreetName').val(" ");
            $('#onwardPickupArea').val(" ");
            $('#onwardPickupLandMark').val(" ");
        }
        if(details=='dropDetails'){
            $('#onwardDropDoorNo').val(" ");
            $('#onwardDropStreetName').val(" ");
            $('#onwardDropArea').val(" ");
            $('#onwardDropLandMark').val(" ");
        }
        if(details=='returnPickupDetails'){
            $('#returnPickupDoorNo').val(" ");
            $('#returnPickupStreetName').val(" ");
            $('#returnPickupArea').val(" ");
            $('#returnPickupLandMark').val(" ");
        }
        if(details=='returnDropDetails'){
            $('#returnDropDoorNo').val(" ");
            $('#returnDropStreetName').val(" ");
            $('#returnDropArea').val(" ");
            $('#returnDropLandMark').val(" ");
        }
    }
}

function changeTravelPoints(cityId){
    if($('#city').val() == -1){
        $('#cityOthers').val(" ");
        $("#cityOthers").css("display","table-row");
    }
    else{
        $("#cityOthers").val(" ");
        $("#cityOthers").css("display","none");
    }
    $.ajax({
        url:"getCityTravelPoints.htm?id="+$("#city").val(),
        dataType: "html",//important
        success:function(data){
            $('#onwardPickUpPoint').html(""+data);
            $('#onwardDropJourney').html(""+data);
            $('#returnPickUpPoint').html(""+data);
            $('#returnDropJourney').html(""+data);
        }
    });
}

function formValidate(){
    if($("#action").val() == "submit" || $("#action").val() == "save" ) {
        if($('#guestBooking').val() == ''){
            alertText("Please Choose Guest Booking");
            $('#guestBooking').focus();
            return false;
        }
        if($('#guestBooking').val() == 'y'){
            if($('#guestName').val() == ''){
                alertText("Please Enter Guest Name");
                $('#guestName').focus();
                return false;
            }
        }
        if($('#projectTravel').val() == ''){
            alertText("Please Choose Project Travel");
            $('#projectTravel').focus();
            return false;
        }
        if($('#client_reimbursable').val() == ''){
            alertText("Please Choose Client Reimbursable Type");
            $('#client_reimbursable').focus();
            return false;
        }
        if($('#projectTravel').val() == 'y'){
            if($('#project').val()==''){
                alertText("Please Choose the project");
                $('#project').focus();
                return false;
            }
            else if($('#project').val()=='-1'){
                if($('#projectOthers').val()==''){
                    alertText("Please Enter the project name");
                    $('#projectOthers').focus();
                    return false;
                }
            }
        }
        if($('#travelPurpose').val()==''){
            alertText("Please Enter Travel Purpose");
            $('#travelPurpose').focus();
            return false;
        }
        if($('#city').val() == ''){
            alertText("Please Choose the City");
            $('#city').focus();
            return false;
        }
        if($('#city').val() == '-1'){
            alertText("Please Enter the City");
            $('#cityOthers').focus();
            return false;
        }
        if($('#onwardPickUpPoint').val() == ''){
            alertText("Please Choose the onward pick-up point");
            $('#onwardPickUpPoint').focus();
            return false;
        }
        if($('#onwardPickUpDate').val()==''){
            alertText("Please choose the onward pick up date");
            $('#onwardPickUpDate').focus();
            return false;
        }
        if($('#onwardPickupHrs').val() < 0 || $('#onwardPickupHrs').val() >23 || $('#onwardPickupHrs').val() == ''){
            alertText("Please Enter pick up Hrs between 00 And 23 ");
            $('#onwardPickupHrs').focus();
            return false;
        }
        if($('#onwardPickupMins').val() < 0 || $('#onwardPickupMins').val() >60 || $('#onwardPickupMins').val()== ''){
            alertText("Please Enter pick up Mins between 00 And 60 ");
            $('#onwardPickupMins').focus();
            return false;
        }
        if($('#onwardDropJourney').val()==''){
            alertText("Please choose the onward drop point");
            $('#onwardDropJourney').focus();
            return false;
        }
        if($('#onwardPickUpPoint').val()==$('#onwardDropJourney').val()){
            alertText("Pick-up and drop point cannot be the same");
            $('#onwardDropJourney').focus();
            return false;
        }
        if($('#seperateCab').is(':checked')) {
            if($('#returnPickUpPoint').val()==''){
                alertText("Please choose the return pick-up point");
                $('#returnPickUpPoint').focus();
                return false;
            }
            if($('#returnPickUpDate').val()==''){
                alertText("Please choose the return pick up date");
                $('#returnPickUpDate').focus();
                return false;
            }
            if($('#returnPickupHrs').val() < 0 || $('#returnPickupHrs').val() >23 || $('#returnPickupHrs').val() == ''){
                alertText("Please Enter pick up Hrs between 00 And 23 ");
                $('#returnPickupHrs').focus();
                return false;
            }
            if($('#returnPickupMins').val() < 0 || $('#returnPickupMins').val() >60 || $('#returnPickupMins').val()== ''){
                alertText("Please Enter pick up Mins between 00 And 60 ");
                $('#returnPickupMins').focus();
                return false;
            }
            if($('#returnDropJourney').val()==''){
                alertText("Please choose the return drop point");
                $('#returnPickUpPoint').focus();
                return false;
            }
            if($('#returnPickUpPoint').val() == $('#returnDropJourney').val()){
                alertText("Pick-up and Drop point cannot be the same")
            }
        }
        if($('#system').val() != 'G') {
            if($('#mobileNumber').val()==''){
                alertText("Please enter the mobile number");
                $('#mobileNumber').focus();
                return false;
            }
        }
        if($('#guestBooking').val()=='n'){
            $('#guestName').val("");
        }
        if($('#project').val()!='-1'){
            $('#projectOthers').val("");
        }
        if($('#city').val() != '-1'){
            $('#cityOthers').val("");
        }
    } else {
        if($('#cancel_remarks').val() == "") {
            $('#cancel_remarks_error').html("Please enter cancel remarks");
            return false;
        }
        var con = confirm("Are you sure want to cancel the request ?");
        if(!con)
            return false;
    }
    $('#saveBtn').css("display","none");
    $('#submitBtn').css("display","none");
    $('#cancelBtn').css("display","none");
}

function approveFormValidate(){
    if($('#remarks').val()==''){
        alert("Please Enter Remarks before Submit");
        $('#remarks').focus();
        return false;
    }
    $('#approveAction').css("display","none");
    $('#sendBackAction').css("display","none");
}

function addAttachment(rowObject) {
    var base_path = $('#base_path').val();
    var theRow = $(rowObject).parent().parent();
    var rowData = "";
    var attachmentCount = $('#attachmentCount').val();
    cnt = parseInt(attachmentCount)+1;
    $('#attachmentCount').val(cnt);
    rowData +=  '<input type="hidden" value="0" name="attachmentdeleted_'+cnt+'" id="attachmentdeleted_'+cnt+'" />';
    rowData +=  '<td align="center"><input type="file" name="attach_doc_'+cnt+'" id="attach_doc_'+cnt+'" class="filebox" size="20" ></td>';
    rowData +=  '<td align="center">';
    rowData +=      '<img onClick="addAttachment(this)" src="'+base_path+'/css/images/tm_add.png" alt="Add" title="Add" style="cursor:pointer;">';
    rowData +=      '&nbsp;<a href="javascript:;" onClick="deleteRow('+cnt+')"><img src="'+base_path+'/css/images/tm_delete.png" /></a>';
    rowData +=  '</td>';
    $(theRow).after("<tr id=tr_attachment_"+cnt+">"+rowData+"</tr>");
}

function deleteRow( rowId ) {
    $('#attachmentdeleted_'+rowId).val(1);
    $('#tr_attachment_'+rowId).remove();
}

function deleteAttachment( rowId ) {
    var con = confirm("Are you sure want to delete ?")
    if(con) {
        $('#attach_del_'+rowId).val(1);
        $('#attachTR_'+rowId).hide();
    }
}

function alertText(message){
    $('#errormessage').text(message);
}




