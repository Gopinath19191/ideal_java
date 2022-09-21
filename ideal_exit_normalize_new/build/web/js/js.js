
function enableAddressDetails(addressType){
//    alert("test data");
    $("#addressDetails").show();
////    $.ajax({
////        url:"getEmployeeAddress.htm?addressType="+addressType,
////        dataType: "json",//important
////        success:function(data){
////            alert("asdasdasdasd");
////            alert('data'+ data);
//////            $('#addressLine1').val(item.addressLine1);
////        }
////    });
//         $.ajax({
//            url: './getEmployeeAddress.htm',
//            type: "POST",
//            async: false,
//            data: ({addressType:addressType}),
//            success: function(ajaxObj) {
//                $('#addressLine1').val(ajaxObj.split("~~")[0].trim());
//                $('#addressLine2').val(ajaxObj.split("~~")[1].trim());
//                $('#zipCode').val(ajaxObj.split("~~")[2].trim());
//            }
//        });
    $.getJSON(("getEmployeeAddress.htm?addressType="+addressType), function(json) {
//        alert("test::"+json.country);
        $.each(json.empAddress, function(i,item){
            $('#addressLine1').val(item.addressLine1);
        });
    });
}

function getAddressDetails(){
	
    $("#empAddDetails").text($("#addressType :selected").text());
	
}
function textLimit(field,maxlen) {
    if(field.value.length >= maxlen){
        while(field.value.length > maxlen){
            field.value=field.value.replace(/.$/,'');
        }
        $('#errormessage').html('Please enter ' + maxlen +  ' characters');
    //        alert('your input has been truncated!');
    }
}