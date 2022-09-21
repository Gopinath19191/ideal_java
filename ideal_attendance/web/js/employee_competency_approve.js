$(document).ready(function(){
    
     $(document).on('click',"#ok_btn_success",function(){
        location.href="list_page.htm"
    })
    
    $(document).on("click",'#submit_data',function(){
        var error=false;
        var error_msg="";
        $(".validation_field").each(function(){
            if(!this.value){
                error=true;
                return;
            }
        });
        
        if(error){
            $("#msg_text_failure").html("Please Select All Proficiency");
            $('#modaldemo5').modal('toggle');
            $('#modaldemo5').modal('show');
            $('#modaldemo5').modal('hide');
            return false;
        }else{
            $('#submit_data').css('cursor', 'no-drop');
            $('#submit_data').prop('disabled', true);
            var formdata=$("#employee_competency_approve").serializeArray();
            var data = {};
            var form_data=[];
            data['master_id']=$("#table_template").attr("emp_com_mas_id");
            $(formdata ).each(function(index, obj){
                form_data.push({"row_id":obj.name.split("_")[1],"experience":obj.value});
            });
            data["proficiency"]=form_data;
            $.ajax({
                type: "POST",
                dataType: "json",
                data: {
                    "proficiency":JSON.stringify(data)
                    },
                url: rootpath+'/save_competency_approve.htm',
                 beforeSend: function () {
                    $(".preloader").show()
                },
                success: function (result)
                {
                    if(result=='SUCCESS'){
                        $("#msg_text_success").html("Data Saved Successfully!!!");
                        $('#modaldemo3').modal({
                            backdrop: 'static', 
                            keyboard: false
                        });
                        $('#modaldemo3').modal('toggle');
                        $('#modaldemo3').modal('show');
                        $('#modaldemo3').modal('hide');
                    }
                    else{
                        $("#msg_text_failure").html("Something went wrong!! Please try again!!");
                        $('#modaldemo5').modal('toggle');
                        $('#modaldemo5').modal('show');
                        $('#modaldemo5').modal('hide');
                        $('#submit_data').css('cursor', 'pointer');
                        $('#submit_data').prop('disabled', false);
                    }
                    $(".preloader").hide()
                               
                }
            });
            return false;
        }
    });
    
});

