/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*  jQuery there is a way to wait until everything on the page has been completely loaded so we will wrap our scripts with this idiom */
$(document).ready(function() {
    
    // var unitId = '1';
    //alert(unitId);
        
    $("#faq_unit_id").change(getQuestionCategory);  
     
//$("#satisfied").change(satisfied);
        
});

function hideRemarks(){
    $('#rating_toggle').hide();
    $('#rating_toggle1').hide(); 
}
function getQuestionCategory(){    
        
    var unitId = $("#faq_unit_id").val();
    //alert(unitId);
    $.ajax({
        type:"GET",
        url:"getAllQuestionCategory.htm",
        async: false,
        data:({
            faq_unit_id:unitId
        }),
        success : function(response) {
            $("#question_category_id").html('').html(response);
        },
        error : function(res) {
            alert("error");
        }
   
    });            

}
function areSatisfied(){    
    //alert("inside satisfied method");
    var satisfied = $("#satisfied").val();
    //alert(satisfied);
    
    if(satisfied == "n"){
        $('#rating_toggle').hide();
        $('#rating_toggle1').show();     
        
        
    }else if(satisfied == "y"){
        $('#rating_toggle').show();
        $('#rating_toggle1').hide();
    }  

}

function listQuestionAnswer(){    
        
    var unitId = $("#faq_unit_id").val();
    var questionCategoryId = $("#question_category_id").val();    
    $.ajax({
        type:"GET",
        url:"listQuestionAnswer.htm",        
        data:({
            faq_unit_id:unitId,
            questionCategoryId:questionCategoryId
        }),
        success : function(response) {            
        },
        error : function(res) {
            alert("error");
        }
   
    });            

}

/* To Add Querstion and Answer TextBox */
var index = [];
// Array starts with 0 but the id start with 0 so push a dummy value
index.push(0);
// Push 1 at index 1 since one child element is already created
index.push(1)

function addkid(){
    $("#file1").css("margin-left","-77px");
    //var div = document.getElementById('addform');
    var id = getID();
    var rId = id-1;
    // alert("id"+id);
    // alert("rId"+rId);
    
    $('#totalQANumber').val(id);
    
    //div.setAttribute("id","Div_"+id);    
    var row = '<tr id = "ques_label_'+ id +'">'+        
    '<td><label id="label1"><b>Question</b><span style="color:red;"> * </span></label></td>'+
    '<td><textarea type="text" class="dropdown" id="question'+ id +'" name="question'+ id +'" style="min-width:225px; min-height:30px; max-width:305px; max-height:50px;"></textarea></td>'+
    '<td><input type="button" value="Add" id="button" onclick="addkid()" class="buttoncss"/>'+
    '<input type="button" id="rem_kid()_' + id + '" onclick="remkid('+id+')" value="Remove"  class="rem"/></td>'+
    '</tr>'+
    '<tr id = "Ans_label_'+ id +'">'+
    '<td><label id="label1"><b>Answer</b><span style="color:red;"></span></label></td>'+
    '<td><textarea type="text" class="dropdown" id="answer'+ id +'" name="answer'+ id +'" style="min-width:225px; min-height:30px; max-width:305px; max-height:50px;"></textarea></td>'+
    '<td><label id="label2">File Upload</label></td>'+
    '<td><input type="file"   value="choose file" id="file" accept="file_extension|audio/*|video/*|image/*|media_type"></td>'+
    '</tr>';	
    //div.innerHTML=row;
    //document.getElementById('addform').appendChild(div);
    $('#addform').find('tbody').append( row );
//$("#questionblock").append(row);    
}
function getID(){
    var emptyIndex = index.indexOf(-1);
    if (emptyIndex != -1){
        index[emptyIndex] = emptyIndex     
        return emptyIndex
    } else {
        emptyIndex = index.length
        index.push(emptyIndex)
        return emptyIndex
    }
}
function remkid(id) {
    // use the id arugment to get the div element using unique id set in addkid
    try{
        var element = document.getElementById("ques_label_"+id)
        element.parentNode.removeChild(element);
        var element = document.getElementById("Ans_label_"+id)
        element.parentNode.removeChild(element);
        //index[id] = -1;
        index.splice(id, 1);
        //id number is = index of the array so we set to -1 to indicate its empty
        console.log(index);
    }
    catch(err){
    
        alert(err)
    
    }
}

function openModel()
{

    //1. get and set function unit id /name to hidden field.

    var faq_unit_id = $("#faq_unit_id").val();
    
     
    if(faq_unit_id != ""){
        $("#currentUnit").val(faq_unit_id);
        var currentUnit = $("#currentUnit").val();
    
        
        //2. open bootstrap model through jquery code.
        $('#myModal').modal('show');
        $("#unit").css("display", "none");
    }else {
        $("#unit").css("display", "block");
    }
    
   
}
function addQuesCategory() {
    
    var newQuesCategory = $("#newQuesCategory").val();
    
    ///alert("newQuesCategory"+newQuesCategory);
    
    var currentUnit = $("#currentUnit").val();
    
    // alert("currentUnit"+currentUnit);
    
    $.ajax({
        type: "POST",
        url: "addQuestionCategory.htm",
        async: false,
        data:({
            currentUnit:currentUnit,
            newQuesCategory:newQuesCategory
        }),
        success : function(response) {
            $("#question_category_id").html('').html(response);
        },

        error: function() {
            alert('Error');
        }
    });
    $('#myModal').modal('hide'); 
}
function validate(){ 
    alert("inside validate method");
    var error = 0;
    if($('.select4').val()== ""){
        document.getElementById("error").style.display="block";
        //document.getElementById("select4").style.border= "1px solid red";
        $(".select4").addClass("has-error");
        $(".select5").removeClass("has-error");
        error++;
    }else if ($('.select5').val()== "" && $('.select4').val()!= ""){
    
        document.getElementById("error").style.display="block";
        $(".select4").removeClass("has-error");
        $(".select5").addClass("has-error");
        error++;
    
    }else if ($('.select5').val()!= ""){
        $(".select5").removeClass("has-error");
        //document.getElementById("error").style.display="none";
        //$(".select4").removeClass("has-error");
        //error++;
        
    }
    //alert(index.length);
    else if(index.length>2)
    {
        //alert(index.length);
        
        for(var i=2;i<index.length;i++){
            //alert($("#question2").val());
            //alert((question[2].name).val());
            //            if($("#question"+i+"").val() == "" ||$("#question"+i+"").val() == undefined && ($('.select5').val()!= "")){
            if($("#question"+i).val() == "" && ($('.select5').val()!= "")){
                $("#question"+i).addClass("has-error");
                document.getElementById("error").style.display="block";
                
                error++;
            }
            else if($("#answer"+i).val() == "" && $("#question"+i).val() != "" ){
                $("#answer"+i).addClass("has-error");
                document.getElementById("error").style.display="block";
                error++;
            }
            else if($("#question"+i).val() != "" ){
		
                $("#question"+i).removeClass("has-error");
                //error++;
		
            }
            if($("#answer"+i).val() != ""){
                $("#answer"+i).removeClass("has-error");
                document.getElementById("error").style.display="none";
                //error++;
            }
        }
    }

    

    if(error==0){
        alert("here");
        $('#formFaqDetails').attr("action", "updateFaq.htm");
        document.formFaqDetails.submit();
        return true;
    }else{
        return false;
    }


}
//function validate(){ 
//    alert("inside validate method");
//    var error = 0;
//    if($('.select4').val()== ""){
//        document.getElementById("error").style.display="block";
//        //document.getElementById("select4").style.border= "1px solid red";
//        $(".select4").addClass("has-error");
//        $(".select5").removeClass("has-error");
//        error++;
//    }else if ($('.select5').val()== ""){
//        $(".select5").addClass("has-error");
//        document.getElementById("error").style.display="none";
//        $(".select4").removeClass("has-error");
//        error++;
//        
//    }
//    //alert("Inside isEmpty method "+error);
//    
//    
////    if(index.length>2)
////    {
////	
////        for(var i=2;i<index.length;i++){
////            if($("#question["+i+"].name").val() == ""){
////                $("#question["+i+"].name").addClass("has-error");
////                error++;
////            }
////            if($("#answer["+i+"].name").val() == ""){
////                $("#answer["+i+"].name").addClass("has-error");
////                error++;
////            }
////            if($("#question["+i+"].name").val() != ""){
////		
////                $("#question["+i+"].name").removeClass("has-error");
////                error++;
////		
////            }
////            if($("#answer["+i+"].name").val() != ""){
////                $("#answer["+i+"].name").removeClass("has-error");
////                error++;
////            }
////        }
////    }//answer['+ rId +'].name
//    //question['+ rId +'].name
//
//    if(error==0){
//        alert("here");
//        $('#formFaqDetails').attr("action", "updateFaq.htm");
//        document.formFaqDetails.submit();
//        return true;
//    }else{
//        return false;
//    }
//
//}


function avoidSpace(event) {
    var k = event ? event.which : window.event.keyCode;
    if (k == 32) return false;
}
function space(event) {
    var k = event ? event.which : window.event.keyCode;
    if (k == 32) return false;
}

   

