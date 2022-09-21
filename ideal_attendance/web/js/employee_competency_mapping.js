$(document).ready(function(){
    $(".skill_list").hide();
    //var competency_skill={};
    var overall_skill_list=[];
    $(".remove_row").hide();
    var add_row_index=1;
    $(".skill").select2({
        placeholder: '--Select Skill--',
        closeOnSelect: false
    });
    
    $(document).on('input','.checknumber', function (event) { 
        this.value = this.value.replace(/[^0-9]/g, '');
    });
    
    $(document).on('click',"#add_span",function(){
        $("#add_span").css("pointer-events", "none");
        var index=++add_row_index;
        var add_div='<div id="competency_mapping_div_'+index+'" class="competency_mapping_div">';
        add_div +='<div class="row">';
        add_div +='<div class="col-lg-2">';
        add_div +='<div class="col-lg-12 form-group pd-0"><select class="form-control unit validation_field" name="unit[]"  id="unit_'+index+'">'+unit_option_string+'</select>';
        add_div +='</div></div>'
        add_div +='<div class="col-lg-2"><div class="form-group" >';
        add_div +='<select class="form-control select2 sbu validation_field" name="sbu[]"  id="sbu_'+index+'"><option value="">-- Select Practice --</option></select>';
        add_div +='</div></div>';
        add_div +='<div class="col-lg-2"><div class="form-group">';
        add_div +='<select class="form-control select2 sub_sbu validation_field" name="sub_sbu[]"  id="sub_sbu_'+index+'"><option value="">-- Select Sub Practice --</option></select>';
        add_div +='</div></div>';
        add_div +='<div class="col-lg-2"><div class="form-group">';
        add_div +='<select class="form-control select2 competency validation_field" name="competency[]"  id="competency_'+index+'"><option value="">-- Select Competency --</option></select>';
        add_div +='</div></div>';
        add_div +='<div class="col-lg-3"><div class="form-group">';
        add_div +='<select class="form-control select2 skill validation_field" multiple name="skill[]"  id="skill_'+index+'"><option value="">-- Select Skill --</option></select>';
        add_div +='</div></div>';
        add_div +='<div class="col-lg-1"><div class="form-group">';
        add_div +='<i class="fa fa-close tx-20-force mg-t-10 mg-l-10 text-danger remove_row cursor-pointer" id="remove_'+index+'"></i>';
        add_div +='</div></div>';
        add_div +='</div>'
        $("#main_div").append(add_div);
        $(".remove_row").show();
        $("#add_span").css("pointer-events", "auto");
        $("#skill_"+index).select2({
            placeholder: '--Select Skill--',
            closeOnSelect: false
        });
    })
    
    $(document).on('click',".remove_row",function(){
        $(".preloader").show()
        var remove_id= this.id;
        var remove_row_number=remove_id.substring(remove_id.lastIndexOf('_') + 1);
        var com_var=$("#competency_"+remove_row_number).val();
        if($("#skill_"+remove_row_number).val()){
            var skill_array=$("#skill_"+remove_row_number).val();
            $.each(skill_array, function (key, value) {
                var remov_index=overall_skill_list.lastIndexOf(value)
                overall_skill_list.splice(remov_index);
            });
        }
        if(com_var){
            $(".competency_list_row_"+com_var).remove();
            update_skill_list(com_var,remove_row_number);
        }
        if($('#skill_list_table tbody tr').length==0){
            $('.skill_list').hide(); 
        }
        $("#competency_mapping_div_"+remove_row_number).remove();
        if($(".competency_mapping_div").length>1){
            $(".remove_row").show();
        }else{
            $(".remove_row").hide();
        }
        $(".preloader").hide()
    });
    $(document).on('change','.unit',function(){ 
        var remove_id= this.id;
        var remove_row_number=remove_id.substring(remove_id.lastIndexOf('_') + 1);
        if($("#skill_"+remove_row_number).val()){
            var com_val=$("#competency_"+remove_row_number).val();
            var array_index=0;
            var skill_array=$("#skill_"+remove_row_number).val();
            $.each(skill_array, function (key, value) {
                skill_list(com_val,value,'remove',remove_row_number);
                array_index++;
                if(array_index==skill_array.length){
                    update_skill_list(com_val,remove_row_number);
                }
            });
        }
        if(this.value){
            $.ajax({
                type: "POST",
                data:{
                    unit_id:this.value
                },
                url: rootpath+'/unit_dynamic.htm',
                beforeSend: function () {
                    $(".preloader").show()
                    $("#sbu_"+remove_row_number).empty(); 
                    $("#sub_sbu_"+remove_row_number).empty(); 
                    $("#competency_"+remove_row_number).empty(); 
                    $("#skill_"+remove_row_number).empty(); 
                },
                success: function (result)
                {
                    $("#sbu_"+remove_row_number).append('<option value="">--Select Practice--</option>'); 
                    $("#sbu_"+remove_row_number).append(result);
                    $("#sub_sbu_"+remove_row_number).append('<option value="">--Select Sub Practice--</option>'); 
                    $("#competency_"+remove_row_number).append('<option value="">--Select Competency--</option>'); 
                    $("#skill_"+remove_row_number).empty();
                    $("#skill_"+remove_row_number).select2('destroy');
                    $("#skill_"+remove_row_number).select2({
                        placeholder: '--Select Skill',
                        closeOnSelect: false
                    });
                    $(".preloader").hide()
                }
            });
        }
        else{
                
            $("#sbu_"+remove_row_number).empty(); 
            $("#sub_sbu_"+remove_row_number).empty(); 
            $("#competency_"+remove_row_number).empty(); 
            $("#skill_"+remove_row_number).empty();
            $("#skill_"+remove_row_number).select2('destroy'); 
            $("#sbu_"+remove_row_number).append('<option value="">--Select Practice--</option>'); 
            $("#sub_sbu_"+remove_row_number).append('<option value="">--Select Sub Practice--</option>'); 
            $("#competency_"+remove_row_number).append('<option value="">--Select Competency--</option>'); 
            $("#skill_"+remove_row_number).select2({
                placeholder: '--Select Skill',
                closeOnSelect: false
            });
            $(".fstElement").addClass('form-control pd-0');
        }
    });
    
    $(document).on('change','.sbu',function(){
        var remove_id= this.id;
        var remove_row_number=remove_id.substring(remove_id.lastIndexOf('_') + 1);
        if($("#skill_"+remove_row_number).val()){
            var com_val=$("#competency_"+remove_row_number).val();
            var array_index=0;
            var skill_array=$("#skill_"+remove_row_number).val();
            $.each(skill_array, function (key, value) {
                skill_list(com_val,value,'remove',remove_row_number);
                array_index++;
                if(array_index==skill_array.length){
                    update_skill_list(com_val,remove_row_number);
                }
            });
        }
        if(this.value){
            $.ajax({
                type: "POST",
                data:{
                    unit_id:this.value
                },
                url: rootpath+'/unit_dynamic.htm',
                beforeSend: function () {
                    $("#sub_sbu_"+remove_row_number).empty(); 
                    $("#competency_"+remove_row_number).empty(); 
                    $("#skill_"+remove_row_number).empty(); 
                    $(".preloader").show()
                },
                success: function (result)
                {
                    $("#sub_sbu_"+remove_row_number).append('<option value="">--Select Sub Practice--</option>'); 
                    $("#sub_sbu_"+remove_row_number).append(result);
                    $("#competency_"+remove_row_number).append('<option value="">--Select Competency--</option>'); 
                    $("#skill_"+remove_row_number).empty();
                    $("#skill_"+remove_row_number).select2('destroy');
                    $("#skill_"+remove_row_number).select2({
                        placeholder: '--Select Skill',
                        closeOnSelect: false
                    });
                    $(".preloader").hide()
                }
            });
        }
        else{
                
            $("#sub_sbu_"+remove_row_number).empty(); 
            $("#competency_"+remove_row_number).empty(); 
            $("#skill_"+remove_row_number).empty();
            $("#skill_"+remove_row_number).select2('destroy')
            $("#sub_sbu_"+remove_row_number).append('<option value="">--Select Sub Practice--</option>'); 
            $("#competency_"+remove_row_number).append('<option value="">--Select Competency--</option>'); 
            $("#skill_"+remove_row_number).select2({
                placeholder: '--Select Skill',
                closeOnSelect: false
            });
        }
    });
    $(document).on('change','.sub_sbu',function(){
        var remove_id= this.id;
        var remove_row_number=remove_id.substring(remove_id.lastIndexOf('_') + 1);
        if($("#skill_"+remove_row_number).val()){
            var com_val=$("#competency_"+remove_row_number).val();
            var array_index=0;
            var skill_array=$("#skill_"+remove_row_number).val();
            $.each(skill_array, function (key, value) {
                skill_list(com_val,value,'remove',remove_row_number);
                array_index++;
                if(array_index==skill_array.length){
                    update_skill_list(com_val,remove_row_number);
                }
            });
        }
        if(this.value){
            $.ajax({
                type: "POST",
                data:{
                    unit_id:this.value
                },
                url: rootpath+'/competency_dynamic.htm',
                beforeSend: function () {
                    $("#competency_"+remove_row_number).empty(); 
                    $("#skill_"+remove_row_number).empty(); 
                    $(".preloader").show()
                },
                success: function (result)
                {
                    $("#competency_"+remove_row_number).append(result); 
                    $("#skill_"+remove_row_number).empty();
                    $("#skill_"+remove_row_number).select2('destroy');
                    $("#skill_"+remove_row_number).select2({
                        placeholder: '--Select Skill',
                        closeOnSelect: false
                    });
                    $(".preloader").hide()
                }
            });
        }
        else{
            $("#competency_"+remove_row_number).empty(); 
            $("#competency_"+remove_row_number).append('<option value="">--Select Competency--</option>'); 
            $("#skill_"+remove_row_number).empty();
            $("#skill_"+remove_row_number).select2('destroy')
            $("#skill_"+remove_row_number).select2({
                placeholder: '--Select Skill--',
                closeOnSelect: false
            });
        }
    });
    $(document).on('change','.competency',function(){
        var remove_id= this.id;
        var remove_row_number=remove_id.substring(remove_id.lastIndexOf('_') + 1);
        if($("#skill_"+remove_row_number).val()){
            var com_val=$("#"+remove_id).attr('old_value');
            var array_index=0;
            var skill_array=$("#skill_"+remove_row_number).val();
            $.each(skill_array, function (key, value) {
                skill_list(com_val,value,'remove',remove_row_number);
                array_index++;
                if(array_index==skill_array.length){
                    update_skill_list(com_val,remove_row_number);
                }
            });
        }
        $("#"+remove_id).attr('old_value', this.value)
        if(this.value){
            $.ajax({
                type: "POST",
                data:{
                    competency_id:this.value,
                    sub_sbu_id:$("#sub_sbu_"+remove_row_number).val()
                },
                url: rootpath+'/skill_dynamic.htm',
                beforeSend: function () {
                    $("#skill_"+remove_row_number).empty(); 
                    $(".preloader").show()
                },
                success: function (result)
                {
                    $("#skill_"+remove_row_number).append(result)
                    $("#skill_"+remove_row_number).select2('destroy');
                    $("#skill_"+remove_row_number).select2({
                        placeholder: '--Select Skill',
                        closeOnSelect: false
                    });
                    $(".fstElement").addClass('form-control pd-0');
                    $(".preloader").hide()
                }
            });
        }
    });
    $(document).on('select2:unselecting','.skill', function (e) {
        var remove_id= this.id;
        var remove_row_number=remove_id.substring(remove_id.lastIndexOf('_') + 1);
        var competency=$("#competency_"+remove_row_number).val();
        //        if(competency_skill.hasOwnProperty(competency)){
        skill_list(competency,e.params.args.data.id,'remove',remove_row_number)
        update_skill_list(competency,remove_row_number);
    //        }
    });
    $(document).on('select2:select','.skill', function (e) {
        var remove_id= this.id;
        var remove_row_number=remove_id.substring(remove_id.lastIndexOf('_') + 1);
        var  skill_array=$("#"+this.id).val();
        var competency=$("#competency_"+remove_row_number).val();
        //        console.log(skill_array);
        //        console.log(overall_skill_list);
        if(skill_array.length>=1){
            $.each(skill_array, function (key, value) {
                if(overall_skill_list.indexOf(value)== -1){
                    skill_list(competency,value,'add',remove_row_number)
                }
            });
        }
    //        else{
    ////            competency_skill[competency]=[];
    //            skill_list(competency,skill_array[0],'add',remove_row_number)
    //        }
    });
    $(document).on('click',"#ok_btn_success",function(){
        location.href="employeeCompetencyView.htm"
    })
    
    $(document).on("click",'#submit_data',function(){
        var error=false;
        var error_msg="";
        $(".validation_field").each(function(){
            if(!this.value){
                error=true;
                error_msg='Please Fill All Mandatory Fields';
                return;
            }
        });
        $(".experience_validation_field").each(function(){
            if(!this.value){
                error=true;
                error_msg='Please Fill All Experience Fields';
                return;
            }
        });
        if(error){
            $("#msg_text_failure").html(error_msg);
            $('#modaldemo5').modal('toggle');
            $('#modaldemo5').modal('show');
            $('#modaldemo5').modal('hide');
            return false;
        }else{
            var competency_skill=[];
            $(".experience_validation_field").each(function(){
                var compete_var=$(this).attr('competency');
                var skill_var =$(this).attr('skill');
                if(compete_var && skill_var )
                    competency_skill.push({
                        "skill":skill_var,
                        "experience":this.value
                    });
            });
            $('#submit_data').css('cursor', 'no-drop');
            $('#submit_data').prop('disabled', true);
            $.ajax({
                type: "POST",
                data: {
                    'competency_skill':JSON.stringify(competency_skill)
                },
                url: rootpath+'/save_employee_competency.htm',
                beforeSend: function () {
                    $(".preloader").show()
                },
                success: function (result)
                {
                    if(result=='SUCCESS'){
                        $("#msg_text_success").html("Data Submitted Successfully!!!");
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
                    //                        $.each(competency_skill,function(key,value){
                    //                                competency_skill[key]=[];
                    //                        });
                    }
                    $(".preloader").hide()
                               
                }
            });
        }
    });
    
    function skill_list(comp_var,skill_var,type,row_index){
        if(type=='add'){
            $(".skill_list").show(); 
            overall_skill_list.push(skill_var);
            //competency_skill[comp_var]=[];
            var add_row='<tr id="skill_list_row_'+skill_var+'" class="competency_list_row_'+comp_var+'">';
            add_row +='<td><span>'+$("#skill_"+row_index+" option[value='"+skill_var+"']" ).text()+'</span></td>';
            add_row +='<td><input type="text" skill="'+skill_var+'" competency="'+comp_var+'" id="experience_'+skill_var+'" class="form-control select2 checknumber experience_validation_field" maxlength=3></td>';
            add_row +='</tr>'
            $('#skill_list_table tbody').append(add_row);
            
        }
        else{
            $("#skill_list_row_"+skill_var).remove();
            overall_skill_list.pop(skill_var);
            if($('#skill_list_table tbody tr').length==0){
                $('.skill_list').hide(); 
            }
        }
    //         console.log(competency_skill);
    //         console.log(overall_skill_list);
    }
    
    
    function update_skill_list(comp_var,row_index){
        $(".competency").each(function(){
            var remove_id= this.id;
            var remove_row_number=remove_id.substring(remove_id.lastIndexOf('_') + 1);
            if(this.value==comp_var && remove_row_number != row_index){
                var  skill_array=$("#skill_"+remove_row_number).val();
                $.each(skill_array, function (key, value) {
                    if(overall_skill_list.indexOf(value)== -1){
                        skill_list(comp_var,value,'add',remove_row_number);
                    }
                });
            }
        })
    }
    
});

