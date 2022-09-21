/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
              
function getEmpList(prjId,selectedList){
    var list = $('#approveType').val();
    $.ajax({
        type: 'POST',
        url: 'getEmployeeList.htm?prjId='+prjId+'&selectedList='+selectedList,
        dataType: "html",   // <====
        success: function(response) {
            $('#empName').html(response);
        }
    }); 
}

function filterData(prjName,fromDate,toDate,regularName)
{   
    if(prjName !="" ||  fromDate != "" ||  toDate != ""){
    $('#timesheetApproval').attr("action", "filterData.htm");
    document.timesheetApproval.submit();
    }
    else{
        alert("Please select project name");
    }
}

function formSubmit(submitValue){
    var ck = new Array();
    var len=0;
    ck = document.getElementsByName("chk_data");
    for (var i = 0; i < ck.length; i++){
        if ($('#chk_data_'+i).is(':checked')){
            len = len+1;
        }
    }
    if(len > 0){
         
        document.timesheetApproval.actionValue.value=submitValue;
        $('#timesheetApproval').attr("action", "approverSubmit.htm");
        document.timesheetApproval.submit();
         
    }
    else{ 
        alert("Please choose data to submit");
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

function loadForm(page)   
{
    document.timesheetApproval.page.value = page;
    document.timesheetApproval.submit();
}



