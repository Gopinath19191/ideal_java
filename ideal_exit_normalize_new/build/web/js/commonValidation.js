//$(document).ready(function() {
//                $(".dueClass").click(
//                function(){
//                    $(this).parent().find(".dueClass").removeClass("error");
//                    if($(this).val()!=2){
//                        $(this).parent().parent().find(".dueCommentsClass").removeClass("required error");
//                    }
//                });
//            });
//function reject()
//{
// $('#formNSClearance').attr("action", "listRegnSubmittedEmp.htm");
// $('#formNSClearance').submit();
//}
//function disableSubmit(saveButtonId,submitButtonId,backButtonId,btnType)
//{
//    if(btnType=='Save'){
//    $("*").removeClass("required error");
//    $("#"+saveButtonId).hide();
//    $("#"+submitButtonId).hide();
//    $("#"+backButtonId).hide();
//    }
//    validateComments(saveButtonId,submitButtonId,backButtonId,btnType);
//}
////function validateComments(saveButtonId,submitButtonId,backButtonId,btnType){
////                    if(btnType !='Save'){
////                        var counter = 0;
////                        $(".dueCommentsClass").each(
////                        function(){
////                            if($(this).parent().parent().find(".dueClass:checked").val()==2){
////                                $(this).addClass("required");
////                                counter++;
////                            }
////                            else{
////                                $(this).removeClass("required");
////                            }
////                        }
////                    );
////                        $("#formNSClearance").validate();
////                        if($("#formNSClearance").valid()){
////                            $("#"+saveButtonId).hide();
////                            $("#"+submitButtonId).hide();
////                            $("#"+backButtonId).hide();
////                    }
////                    }}