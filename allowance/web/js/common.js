$(document).ready(function(){
    $("#employeeName").autocomplete("ajaxsearch.htm", {
        minChars: 1,
        width: 350,
        matchContains: true
    });
     $("#employeeName").result(function(event, data, formatted) {
        if (data) {
            $("#employeeId").val(data[1]);
        }
    });
});

function loadForm(page){
    document.userform.page.value = page;
    document.userform.submit();
}

function refreshEmployee() {
    $('#employeeId').val("");
    $('#employeeName').val("");
}

function checkUnique(value,type,objectId,res) {
    $.post('checkUnique.htm', {
            value:value,
            type:type,
            id:$('#'+objectId).val()
        },
        function(ajaxObj) {
            if(ajaxObj >= 1) {
                alert(res+" number already exists.Please check once again");
                document.getElementById(objectId).select();
                return false;
            }
        }
    );
}
function getTeamMemebrs(){
    var cus=$("#customer").val();                   
    if(cus==''||cus=='null')
        {
            $("#error").html('Please select customer');
        }
    else{
            $('#getDetails').attr("action", "getEmployeeDetails.htm");
            document.getDetails.submit();                       
        }     
};