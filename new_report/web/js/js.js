/* 
 * Auther: Sureshkumar Natarajan(14032)
 */
$(document).ready(function(){
    tabDisplayOnResourceType();
//    window.document.getElementById('rightMenu').css("height",document.body.scrollHeight);
});
 
$(function() {
    $( "#tabs" ).tabs();
    $( "#tabs h3" ).hide();
    $('#resourceType').change(tabDisplayOnResourceType);

    $('#companyStructureId').change(function(){
        if($(this).val()=="12"){
            $('.subPracticeGroupContainer').addClass("noValidate");
            $('.subPracticeGroupContainer').hide();
        }else{
            $('.subPracticeGroupContainer').removeClass("noValidate");
            $('.subPracticeGroupContainer').show();
        }
        $.ajax({
            url:"getPracticeGroups.htm?id="+$(this).val(),
            dataType: "html",//important
            success:function(data){
                // $('#practiceGroup').html("");
                $('#practiceGroup').html(""+data);
                $('#practiceGroup').change();
            }
        });
    });
    $('#practiceGroup').change(function(){
        $.ajax({
            url:"getSubPracticeGroups.htm?id="+$(this).val(),
            dataType: "html",
            success:function(data){
                $('#subPracticeGroup').html(data);
                setToolTipForSelects();
            }
        });
    });
    $('#countryId').change(function(){
        $.ajax({
            url:"getRegions.htm?id="+$(this).val(),
            dataType: "html",
            success:function(data){
                $('#regionId').html(data);
                $('#regionId').change();
            }
        });
    });
    $('#regionId').change(function(){
        $.ajax({
            url:"getCities.htm?id="+$('#countryId').val()+"&subId="+$(this).val(),
            dataType: "html",
            success:function(data){
                $('#cityId').html(data);
            }
        });
    });
    $('#customerId').change(function(){
        var customerId = $("#customerId").val();
        $('.othercust').hide();
        if(customerId != -1){
            $('#otherCustomer').removeClass("required");
            $('#otherCustomer').val("");
            $.ajax({
                url:"getCustomerSiteLocationForId.htm?id="+$(this).val(),
                dataType: "html",
                success:function(data){
                    data=$.trim(data);
                    $('#customerSitelocation').html((data==null || data=="null")?"--":data);
                }
            });
             
        }
        else if(customerId == -1){
            $('#otherCustomer').addClass("required");
            $('.othercust').show();
        }
    });
    
    $('.resources').change(function(){
        selectedValue=$(this).val();
        selectedElement=$(this);
        $('.resources option[value="'+selectedValue+'"]').each(function(){
            
            $(this).removeAttr('disabled');
            if($(this).attr('value')){            
                if(!$(this).parent().is(selectedElement)){
                    $(this).attr('disabled', 'disabled');
                }
            }
        });//not(this).remove();
    });
    
    $('#hourlyRate').blur(function(){
        $('#marginValuePercentage').val(getMarginValuePercentage($(this).val(),$('#ctc').val()));        
    });
    $('#proposedCtc').blur(function(){
        $('#marginValuePercentage').val(getMarginValuePercentage($('#hourlyRate').val(),$(this).val()));
    });

    $('#proposedBandId').change(function(){
        $.ajax({
            url:"getCTCforBand.htm?bandId="+$(this).val(),
            dataType: "html",
            success:function(data){
                data=$.trim(data);
                $('#ctc').val((data==null || data=="null")?"0":data);
                $('#hourlyRate').blur();
            }
        });
    });
    $('#moveSelectedResources').click(moveSelectedResources);
    $('#reMoveSelectedResources').click(reMoveSelectedResources);
    $('#internalNumber').keyup(function(){
        if($(this).val()!=''){
            $("#empIdsTemp,#empIds").removeAttr('disabled');
        }else{
            $("#empIdsTemp,#empIds").attr('disabled','disabled');
        }
    });
    
    $('.maxlength').each(function(){
        $(this).keyup(maxLengthChecker);
        $(this).mouseup(maxLengthChecker);
    });
    
    
    setToolTipForSelects();
    
    
    
    $('#customerId').change();//set cust location on load;
    $('#proposedBandId').change();//set ctc onload. 
    //hide element operation for corporate
    if($('#companyStructureId').val()=="12"){
        $('.subPracticeGroupContainer').addClass("noValidate");
        $('.subPracticeGroupContainer').hide();
    }else{
        $('.subPracticeGroupContainer').removeClass("noValidate");
        $('.subPracticeGroupContainer').show();
    }
    
});

function setToolTipForSelects(){
    $('select option').each(function(){
        $(this).attr("title",$(this).text());
    });
}
function maxLengthChecker(){
    maxlen=$(this).attr("maxlength");
    content=$(this).val();
    if(content.length>maxlen){
        $(this).val(content.substring(0, maxlen));
    }
}

function getMarginValuePercentage(hourlyRate, ctc){
    //    alert(hourlyRate);
    //    alert(ctc);
    houlyRalte=parseFloat(hourlyRate);
    ctc=parseFloat(ctc);
    //    result =Math.round(hourlyRate-(ctc/(12*21*9))/hourlyRate*100*100)/100;
    result =Math.round(((hourlyRate-ctc/(12*21*9))/hourlyRate*100)*100)/100;
    //    alert(result);
    return (!isFinite(result)||result<0)?0:result;    
}

function addAnAttachment(){
    nextFileId=parseInt($('#lastAttachmentId').val())+1;
    $('#fileAttachments').append('<div id="box_'+nextFileId+'"><input type="file" class="required" id="file_'+nextFileId+'" name="file'+nextFileId+'"/> <a href="javascript:void(0);" onclick="$(\'#box_'+nextFileId+'\').remove();">Remove</a></div>');
    $('#lastAttachmentId').val(nextFileId);
}

function addRequestSubmitAction(formElementId){
    result=validateAddRequestOnSave(formElementId);
    if(result){        
        $('#request_details_div, #profile_details_div, #customer_details_div, #job_details_div').each(function(){
            tabId=this.id;
            $('#'+this.id+' .invalid').removeClass("invalid");
            if(result){
                $('#'+this.id+' .required').not('#'+this.id+' .noValidate .required').each(function(){
                    //                    $(this).removeClass("invalid");
                    if((!$(this).attr('value') || $(this).val()=='')){
                        //                        console.log($(this).attr('value')+':'+$(this).val()+"---"+$(this).attr('id')+"---"+$(this).is(':visible'));
                        $(this).addClass("invalid");
                        ////$(this).focus();it makes problem with datepicker 
                        $( "#tabs" ).tabs('select', '#'+tabId);//.show();
                        $('#errormessage').text("Marked fields value cannot be empty!");
                        result=false;
                        return;
                    }
                });
            }
        });        
    }
    //    result=result && confirm("Are you sure want to submit?");
    //    if(result) {
    //        $('.submit_block').hide();
    //        document.forms[formElementId].submit();
    //    }
    if(result) {
        confirmAndSubmit("Are you sure want to submit?",function(){
            $('#'+formElementId).submit();
        });
    }
    return false;
}
    
function addRequestSaveAction(formElementId){
    if(validateAddRequestOnSave()){
        $('.submit_block').hide();
        document.forms[formElementId].submit();
    } 
}
function validateAddRequestOnSave(){
    result=true;
    $('#request_details_div, #profile_details_div, #customer_details_div, #job_details_div').each(function(){
        tabId=this.id;
        $('#'+this.id+' .invalid').removeClass("invalid");
        if(result){
            $('#'+this.id+' .numeric').not('#'+this.id+' .noValidate .numeric').each(function(){
                $(this).removeClass("invalid");
                if(isNaN($(this).val())){
                    //                    console.log($(this).attr('value')+':'+$(this).val()+"---"+$(this).attr('id')+"---"+$(this).is(':visible'));
                    $(this).addClass("invalid");
                    ////$(this).focus(); //it makes problem with datepicker 
                    $( "#tabs" ).tabs('select', '#'+tabId);//.show();
                    $('#errormessage').text("Marked fields value should be numeric!");
                    result=false;
                    return;
                }
            });
        }
    });
    if(result){
        var files=$('#fileAttachments input[type=file]').each(function(){
            iFile=$(this);
            $(this).removeClass("invalid");
            $('#fileAttachments input[type=file]').not(this).each(function(){
                $(this).removeClass("invalid");
                if(!$(this).val() || $(this).val()=='' || $(iFile).val()==$(this).val()){
                    $(this).addClass("invalid");
                    $( "#tabs" ).tabs('select', '#customer_details_div');//.show();
                    $('#errormessage').text("Marked fields value should be unique and non empty!");
                    result=false;
                    return;
                }
            });
            if(!result){
                return;
            }
        });
    }
    if(result){        
        $('#requestedNoOfResources').removeClass('invalid');
        temp=$.trim($('#requestedNoOfResources').val());
        temp=temp==''?1:parseFloat(temp);
        if(temp<1){
            $('#requestedNoOfResources').addClass('invalid');
            $('#errormessage').text("Invalid data");
            result=false;
        }
    }
    if(result){   
        $('#relevantExperienceFrom').removeClass('invalid');
        temp=$.trim($('#relevantExperienceFrom').val());
        temp=temp==''?1:parseInt(temp);
        if(temp<0){
            $('#relevantExperienceFrom').addClass('invalid');
            $('#errormessage').text("Invalid data");
            result=false;
        }
    }
    if(result){   
        $('#relevantExperienceTo').removeClass('invalid');
        temp=$.trim($('#relevantExperienceTo').val());
        temp=temp==''?1:parseInt(temp);
        if(temp<parseInt($.trim($('#relevantExperienceFrom').val()))){
            $('#relevantExperienceTo').addClass('invalid');
            $('#errormessage').text("Invalid data range!");
            result=false;
        }        
    }
    return result;
}
    
var tabDisplayOnResourceType=function tabDisplayOnResourceType(){
    if($('#resourceType').val()!='b'){	
        $( "#customer_bill" ).hide();
        $( "#customer_bill" ).addClass('noValidate');
    }else{
        $( "#customer_bill" ).show();
        $( "#customer_bill" ).removeClass('noValidate');
    }
}

function pmoValidation(inputId,inputValue,outputId,requestedNoOfResources,totalChildCount,pmoPendingCount){
    if($('#empIds option').length==0){
        loadBenchedEmployees();
    }
    if(inputValue=='1'){
        $("#pmoValidate").hide();
        $("#pmoSendBack").show();
        $("#internalNumber").val('');
        $("#externalNumber").val('');
        $("#"+outputId).text("Validate");
        $("#"+outputId).attr("class", "savebutton");
        if($("#pmoRemarks").val()!=''){
    //            $("#"+inputId).hide();
    //            $("#"+outputId).hide();
    //            document.pmoApproval.submit();
    }
    }else if(inputValue=='2'){
        $("#pmoValidate").show();
        $("#pmoSendBack").show();
        $("#"+inputId).text("Submit");
        $("#"+inputId).attr("class", "rrfsubmitbutton");
        if($("#pmoRemarks").val()!='' && ($("#internalNumber").val()!='' && $('#empIds option').length>=$("#internalNumber").val() || $("#externalNumber").val()!='') 
            && ($("#internal").attr('checked') || $("#external").attr('checked'))
            && $("#empIds").val()!=''){
            //            $("#"+inputId).hide();
            //            $("#"+outputId).hide();
            $('#empIds option').each(function(){
                $(this).attr("selected", "selected");
            });
        //            document.pmoApproval.submit();
        }
    }
    $("#status").val(inputValue);
    if(pmoFunctionValidate(requestedNoOfResources,totalChildCount,pmoPendingCount)){
        $("#"+inputId).hide();
        $("#"+outputId).hide();
        document.pmoApproval.submit();
        return true;
    }
    else{
        return false;
    }
}

function pmoFunctionValidate(requestedNoOfResources,totalChildCount,pmoPending){
    //    alert(totalChildCount);
    var internal =$("#internalNumber").val();
    var external =$("#externalNumber").val();
    var pmoPendingCount ;
    //    if(totalChildCount!=requestedNoOfResources){
    //        pmoPendingCount = parseInt(requestedNoOfResources) - parseInt(totalChildCount);
    //    }else{
    //        pmoPendingCount = pmoPending;
    //    }
    pmoPendingCount = pmoPending;
    //    alert("--"+totalChildCount+"---"+requestedNoOfResources+"--"+pmoPendingCount+"---"+pmoPending+"__________________")
    var totalnumber;
    if(internal!='' && external!=''){
        totalnumber = parseInt(internal) + parseInt(external);
    }
    //    alert("---"+internal+"--"+external+"--"+totalnumber+"---"+requestedNoOfResources+"---"+totalChildCount);
    if(!$("#internal").attr('checked') && !$("#external").attr('checked') && $("#validate").text()=='Submit'){
        alert("Please Click any one allocation type to proceed further....")
        return false;
    }
    if($("#internal").attr('checked') && (internal=='' || internal==0) && $("#validate").text()=='Submit'){
        alert("Please enter the valid internal number...")
        return false;
    }
    if($("#external").attr('checked') && (external=='' || external==0) && $("#validate").text()=='Submit'){
        alert("Please enter the valid external number...")
        return false;
    }
    if((internal=='' || internal==0) && (external=='' || external==0)&& $("#validate").text()=='Submit'){
        alert("Please enter the internal/external number...")
        return false;
    }
    if(internal!='' && isNaN(internal)){
        alert("Please enter valid number..")
        return false;
    }
    if(external!='' && isNaN(external)){
        alert("Please enter valid number..")
        return false;
    }
    if($("#pmoRemarks").val()=='' && ($("#validate").text()=='Submit' || $("#sendBack").text()=='Send Back')){
        alert("Please enter the remarks")
        return false;
    }
    if(totalChildCount==0){
        if(internal!='' && internal>requestedNoOfResources){
            alert("Internal Number should be less than or equal to requested number of resources..")
            return false;
        }
        if(external!='' && external>requestedNoOfResources){
            alert("External Number should be less than or equal to requested number of resources..")
            return false;
        }
        if(totalnumber>requestedNoOfResources){
            alert("Sum of Internal/External Number should be less than or equal to requested number of resources..")
            return false;
        }
    }else if(totalChildCount!=0){
        if(internal!='' && internal>pmoPendingCount){
            alert("Internal Number should be less than or equal to Pmo Pending Count..")
            return false;
        }
        if(external!='' && external>pmoPendingCount){
            alert("External Number should be less than or equal to Pmo Pending Count..")
            return false;
        }
        if(totalnumber>pmoPendingCount){
            alert("Sum of Internal/External Number should be less than or equal to Pmo Pending Count..")
            return false;
        }
    }
    if(internal!='' && $("#empIds option").length<internal){
        alert("Please allocate minimum "+internal+ " number of resources for the internal allocation.")
        return false;
    }
    return true;
}

function enableNumberOfResource(inputId,outputBox){
    if($("#"+inputId).attr('checked')){
        $("#"+outputBox).removeAttr('disabled');
    }else{
        $("#"+outputBox).val("");
        $("#"+outputBox).attr('disabled','disabled');
        if(inputId=='internal'){
            $('#empIdsTemp,#empIds').attr('disabled','disabled');
            $("#empIds option").appendTo('#empIdsTemp');
        }
    }
    
}

function resourceValidation(inputValue,pendindValue,inputId){
    if(inputValue<=0 || inputValue>pendindValue)
    {
        if(inputId == '')
        {
            $("#"+inputId).attr('disabled','disabled');
            $("#submitBtn").attr('disabled','disabled');
            $("#resendBtn").attr('disabled','disabled');
        }
    }
    else{
        $("#"+inputId).removeAttr('disabled');
        $("#submitBtn").removeAttr('disabled');
        $("#resendBtn").removeAttr('disabled');
    }
}

function setBillDuration(){
    try{
        if($("#billEndDate").datepicker("getDate") && $("#billStartDate").datepicker("getDate")){
            $('#billingDuration').text(($("#billEndDate").datepicker("getDate").getTime()-$("#billStartDate").datepicker("getDate").getTime())/(1000*60*60*24)+1+" Days");
        }
    }catch(e){}
}

function loadBenchedEmployees(){
    $('#loadingInfo').text('Loading list...');
    $.getJSON(("getBenchedEmployees.htm?ignore="+new Date().getTime()), function(json) {
        $('#benchedEmployees #empIdsTemp').html("");
        $.each(json.items, function(i,item){
            $('#benchedEmployees #empIdsTemp').append('<option value="'+item.empId+'" title="'+item.empNumber+'-'+item.empName+'">'+item.empNumber+'-'+item.empName+'</option>');
        });        
        $('#loadingInfo').text('');
    });
}

var moveSelectedResources=function(){
    $('#empIdsTemp option:selected').each(function(){
        $(this).appendTo('#empIds');
    });
}
var reMoveSelectedResources=function(){
    $('#empIds option:selected').each(function(){          
        $(this).appendTo('#empIdsTemp');
    });    
}
function getIndiRequestDetails(masterReqId,actionName,totalCountInChild,requestedNoOfResources,pmoPending,pmoPending1,pmoPending2,buhPending,rmgPending,hrHeadPending,pmoInternalSuggestionCount,reqInternalAcceptedCount,hrrepPending,hrrepPendingAction){
    $("#masterReqId").val(masterReqId);
    $("#totalCountInChild").val(totalCountInChild);
    $("#requestedNoOfResources").val(requestedNoOfResources);
    $("#pmoPending").val(pmoPending);
    $("#pmoPending1").val(pmoPending1);
    $("#pmoPending2").val(pmoPending2);
    $("#buhPending").val(buhPending);
    $("#rmgPending").val(rmgPending);
    $("#hrHeadPending").val(hrHeadPending);
    $("#hrrepPending").val(hrrepPending);
    $("#hrrepPendingAction").val(hrrepPendingAction);
    $("#pmoInternalSuggestionCount").val(pmoInternalSuggestionCount);
    $("#reqInternalAcceptedCount").val(reqInternalAcceptedCount);
    document.getIndividualRequestDetails.action =actionName;
    document.getIndividualRequestDetails.submit();
}
function getdataForEdit(masterReqId,actionName){
    $("#reqId").val(masterReqId);
    document.getIndividualRequestDetails.action =actionName;
    document.getIndividualRequestDetails.submit();
}

function validateAcceptInternalResources(isRejected,remarks){
    $('#reqApproveForm .invalid').removeClass("invalid");
    if(remarks==''){
        alert("Please enter remarks");
        $('#requestorInternalComments').addClass("invalid");
        return false;
    }
    msg="";
    if(isRejected){
        msg='Are you sure want to reject all resources?';
    //        document.forms['reqApproveForm'].reset();
    //        $('.resources option[value=""]').attr("selected","selected");
    //        result=confirm('Are you sure want to reject all resources?');
        
    }else{
        reslut=false;
        $('.resources').each(function(){
            $(this).removeClass("invalid");
            if($(this).val()!=''){
                result=true;
            }else{
                $(this).addClass("invalid");
            }
        });
        if(!result){
            alert('please select atleast one resource');
        }else{
            msg='Are you sure want to submit?';
        }
    //        result= result && confirm('Are you sure want to submit?');
    }
    confirmAndSubmit(msg,function(){
        if(isRejected){
            $('.resources').attr("disabled","disabled");
        }
        $('#reqApproveForm .invalid').removeClass("invalid");
        alert("");
        document.forms['reqApproveForm'].submit();
    });
    //    if(result) {
    //        if(isRejected){
    //            $('.resources').attr("disabled","disabled");
    //        }
    //        $('#reqApproveForm .invalid').removeClass("invalid");
    //        alert("");
    //        document.forms['reqApproveForm'].submit();
    //    //        $("#remarksForm").submit();
    //    }
    return false;
}

function getExcelReport(){
    //    alert("Excel Export");
    $('#reportPage').attr("action", "getRequestExcel.htm");
    document.reportPage.submit();
     
}

function getFilterList(){
    $('#reportPage').attr("action", "generateReport.htm");
    document.reportPage.submit();
    startLoading();
}
function getExcelReport1(Value){
    //    alert("Excel Export1");
    $.ajax({
        url:"getPracticeGroups.htm?id="+Value,
        dataType: "html",//important
        success:function(data){
            //            alert('data'+ data);
            $('#reportpracticeGroup').html(""+data);
        }
    });
}
//function validateResource()
//{
//    if($("#buhremarks").val()==''){
//        alert("Please enter the remarks");
//        $("#submitBtn").attr('disabled','disabled');
//        $("#resendBtn").attr('disabled','disabled');
//        return false;
//    }
//}


//function buhValidation(Value,buhPending,userEntered)
//{
//    if(userEntered<=0 || userEntered>buhPending)
//    {
//        alert("Enter Valid Number Of Resources");
//    }
//    else
//    {
//        if($("#buhremarks").val()!=''){
//            $('#buhApproval').attr("action", "submitRequestDetails.htm");
//            document.buhApproval.btn.value=Value;
//            document.buhApproval.submit();
//        }
//        else
//        {
//            alert("Enter the Remarks");
//        }
//    }
//}
function approverValidation(Value,buhPending,userEntered)
{
    $("#btn").val(Value);
    var dataPat = '[\.]';
    var matchArray = userEntered.match(dataPat);
    if (matchArray == null)
    {
        if(userEntered<=0 || userEntered>buhPending  ||  isNaN(userEntered) )
        {
            alert("Enter Valid Number Of Resources");
        }
        else
        {
            if($("#approverRemarks").val()!='' ){
                $('#approverAction').attr("action", "submitRequestDetails.htm");
                document.approverAction.submit();
            }
            else
            {
                alert("Enter the Remarks");
            }
        }
    }else{
        alert("Enter Valid Number Of Resources");
    }
  
}
function hrapproverValidation(Value,buhPending,userEntered)
{
    $("#btn").val(Value);
    var proCtc = $("#proposedCtc").val();
    var dataPat = '[\.]';
    var matchArray = userEntered.match(dataPat);
    var currency = $("#currencyId").val();
    if (matchArray == null)
    {
        
        if(userEntered > buhPending || userEntered == 0)
        {
            alert("Enter Valid Number Of Resources");
        }
        else
        {
            var ctcmatch = proCtc.match(dataPat);
            if(ctcmatch == null){
                if($("#proposedCtc").val()=='' || $("#proposedCtc").val().length<6 || isNaN($("#proposedCtc").val() ))
                {
                    alert("CTC requires miminum 6 digits");
                }
                if(currency == ''){
                    alert("Choose the valid currency");
                }
                else
                {
                    if($("#approverRemarks").val()!='' ){
                        $('#approverAction').attr("action", "submitRequestDetails.htm");
                        document.approverAction.submit();
                    }
                    else
                    {
                        alert("Enter the Remarks");
                    }
                }
            }
            else{
                alert("CTC requires miminum 6 digits");
            }
        }
    }else{
        alert("Enter Valid Number Of Resources");
    }
}
function resendValidation(Value,buhPending,userEntered)
{
    $("#btn").val(Value);
    var dataPat = '[\.]';
    var matchArray = userEntered.match(dataPat);
    if (matchArray == null)
    {
        if(userEntered!=buhPending  )
        {
            alert("Enter Resource Equal To Pending Count");
        }
        else
        {
            if($("#approverRemarks").val()!='' ){
                $('#approverAction').attr("action", "submitRequestDetails.htm");
                document.approverAction.submit();
            }
            else
            {
                alert("Enter the Remarks");
            }
        }
    }else{
        alert("Enter Valid Number Of Resources");
    }
}
function hrresendValidation(Value,buhPending,userEntered)
{
    $("#btn").val(Value);
    var proCtc = $("#proposedCtc").val();
    var dataPat = '[\.]';
    var matchArray = userEntered.match(dataPat);
    var currency = $("#currencyId").val();
    if (matchArray == null)
    {
        if(userEntered!=buhPending)
        {
            alert("Enter Valid Number Of Resources");
        }
        else
        {
            var ctcmatch = proCtc.match(dataPat);
            if(ctcmatch == null){
                if($("#proposedCtc").val()=='' || $("#proposedCtc").val().length<6 || isNaN($("#proposedCtc").val() ))

                {
                    alert("CTC requires miminum 6 digits");
                }
                if(currency == ''){
                    alert("Choose the valid currency");
                }
                else
                {
                    if($("#approverRemarks").val()!='' ){
                        $('#approverAction').attr("action", "submitRequestDetails.htm");
                        document.approverAction.submit();
                    }
                    else
                    {
                        alert("Enter the Remarks");
                    }
                }
            }
           
            else{
                alert("CTC requires miminum 6 digits");
            }
        }
    }else{
        alert("Enter Valid Number Of Resources");
    }
}
function approverValidationForCorporate(Value){
    $("#btn").val(Value);
    if($("#approverRemarks").val()!=''){
        $('#approverAction').attr("action", "submitRequestDetails.htm");
        document.approverAction.submit();
    }else{
        alert("Enter the Remarks");
    }
}

function cancelRequestFormValidate(){
    result=true;
    $('#cancelRequestForm .required').each(function(){
        $(this).removeClass("invalid");
        if((!$(this).attr('value') || $(this).val()=='')){
            $(this).addClass("invalid");
            $(this).focus();
            $('#errormessage').text("Marked field(s) value cannot be empty!");
            result=false;
            return;
        }
    });
    
    if(result) {
        confirmAndSubmit('Are you sure want to cancel the request?',function(){
            $('#cancelRequestForm').removeAttr("onsubmit");
            $('#cancelRequestForm').submit();
        });
    }
    
    return false;
     
}
//
//function validDigit(Value)
//{
//    alert(Value);
//    if(Value.length<6 || Value=='' || isNaN(Value))
//    {
//        alert("CTC requires miminum 6 digits");
//    }
//}

function confirmAndSubmit(msg,yesCallback,noCallback){
    var randNum = Math.ceil(Math.random()*1000);
    $('body').append('<div id="dialog_'+randNum+'" title="Confirm">'+msg+'</div>');
    $( "#dialog_"+randNum+":ui-dialog" ).dialog( "destroy" );
    $('#dialog_'+randNum).dialog({
        resizable: false,
        height:140,
        modal: true,
        buttons: {
            Yes: function() {
                //                $(formElement).children('.submit_block').hide(); consider later
                //                $(formElement).submit();
                $( this ).dialog( "close" );
                yesCallback();
                wait();
                
            },
            Cancel: function() {
                $( this ).dialog( "close" );
                $('#dialog_'+randNum).remove();
                noCallback();   
            }
        }
    });
}

function wait(msg){
    if(!msg){
        msg="Please wait.."
    }
    var randNum = Math.ceil(Math.random()*1000);
    $('body').append('<div id="dialog_'+randNum+'" title="Loading...">'+msg+'</div>');
    $( "#dialog_"+randNum+":ui-dialog" ).dialog( "destroy" );
    $('#dialog_'+randNum).dialog({
        resizable: false,
        height:100,
        modal: true
    });
}

//Hr Rep Action -- CR

//function checkAll(id)
//{
//    alert("Value"+id);
//    var c = new Array();
//    c = document.getElementsByTagName('input');
//    if( $('#'+id).is(':checked'))
//    {
//
//        <c:forEach items='${childRequestDetails}' var='childDetails' varStatus='i'>
//                        <c:if test='${childDetails.childStatus==12 || childDetails.childStatus==13}'>
//                                alert('${childDetails.childStatus}');
//                        </c:if>
//          </c:forEach>
////        for (var i = 0; i < c.length; i++)
////        {
////            if (c[i].type == 'checkbox')
////            {
////                c[i].checked = true;
////            }
////        }
//    }
//    else{
//        for (var i = 0; i < c.length; i++)
//        {
//            if (c[i].type == 'checkbox')
//            {
//                c[i].checked = false;
//            }
//        }
//    }
//}

function repAction()
{
    //    alert("Here Comes:::");
    var masterReqId = document.hrRepAction.masterReqId.value;
    var c =" ";
    for (var i=0; i < document.hrRepAction.childReqIds.length; i++)
    {
        if ( !$("#childReqId"+i).is(':disabled') ) {
            if (document.hrRepAction.childReqIds[i].checked)
            {
                c = c+ document.hrRepAction.childReqIds[i].value+"," ;
            //                alert(c);
            }
        }
    }
    //    alert("---"+masterReqId);
    document.hrRepAction.parentId.value = masterReqId;
    //    alert("2---***"+document.hrRepAction.parentId.value);
    document.hrRepAction.childArray.value = c;
    document.hrRepAction.action="submitRequestDetails.htm";
    document.hrRepAction.submit();

}
function enablechkbox(Value)
{
    if(document.getElementById("childReqIds"+Value).checked){
        document.getElementById("hrRepRemarks"+Value).disabled = false;
        document.getElementById("fileName"+Value).disabled = false;
    }
    else{
        document.getElementById("hrRepRemarks"+Value).disabled = true;
        document.getElementById("fileName"+Value).disabled = true;
    }
    document.getElementById("submitBtn").disabled = false;
}

function hrRepSubmit(Value,chkLen)
{
 
    $("#btn").val(Value);
    var total="";
    var remark = 0;
    var remId =0;
    var fileId=0;
    if(chkLen==1){
        if(document.getElementById("childReqIds"+chkLen).checked)
        {
            chkValue=Value;
            total +=document.getElementById("childReqIds"+chkLen).value;
            remark = document.getElementById("childReqIds"+chkLen).value;
       
            if(document.getElementById("hrRepRemarks"+remark).value == "" || document.getElementById("hrRepRemarks"+remark).value.length>250)
            {
                alert("Please Enter Remarks With Limited Character(Character Limitation is 200 characters)");
                remId = remId+(remark);
                if(document.getElementById("fileName"+remark).value == "")
                {
                    alert("Please Attach Relevant File");
                    fileId = fileId+remark;
                }
            }
            if(document.getElementById("fileName"+remark).value == "")
            {
                alert("Please Attach Relevant File");
                fileId = fileId+remark;
            }
        }
        
    }
    for(var i=0; i < document.hrRepAction.childReqIds.length; i++){
        if(document.hrRepAction.childReqIds[i].checked)
        {
            if(total=='')
            {
                total +=document.hrRepAction.childReqIds[i].value;
                remark = document.hrRepAction.childReqIds[i].value;
            }
            else{
                total +=","+document.hrRepAction.childReqIds[i].value ;
                remark = document.hrRepAction.childReqIds[i].value;
            }
            //if(document.hrRepAction.hrRepRemarks2.value == "")
            if(document.getElementById("hrRepRemarks"+remark).value == "" || document.getElementById("hrRepRemarks"+remark).value.length>250)
            {
                alert("Please Enter Remarks With Limited Character(Character Limitation is 200 characters)");
                remId = remId+(remark);
                if(document.getElementById("fileName"+remark).value == "")
                {
                    alert("Please Attach Relevant File");
                    fileId = fileId+remark;
                }
            }
            if(document.getElementById("fileName"+remark).value == "")
            {
                alert("Please Attach Relevant File");
                fileId = fileId+remark;
            }
        }
    }
    
    if(total != '')
    {

        if(remId==0 && fileId==0)
        {
            document.hrRepAction.parentId.value=total;
            //            alert("parentId***s"+document.hrRepAction.parentId.value);
            $('#hrRepAction').attr("action", "submitRequestDetails.htm");
            document.hrRepAction.submit();
        }
    }
    else{
        alert("Please Select Any One Of the CheckBox");
    //        document.getElementById("submitBtn").disabled = true;
    }
}

function copyRequest(){
    document.copyRequestForm.submit();
}


function alert(message){
    $('#errormessage').text(message);
}