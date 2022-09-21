/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

    
function getSubSbu(value)
{
	//alert("MUNI");
    var subSbu;
    if(value=="TS" || value=="5")
    {
        subSbu="5";
    }else if(value=="PES" || value=="2")
    {
        subSbu="2";
    }
//    else if(value=="ERP" || value=="11")
//    {
//        subSbu="11";
//    }
    else if(value=="All" || value=='')
    {
        subSbu="2,5";
    }     
    $.ajax(
    {
        url:"getSubPracticeGroup.htm?id="+subSbu,
        dataType: "html",
        success:function(data){
            $('#subSbu').length=0;
            $('#subSbu').html(data);
        }
    });
}
        
//function setSbu(Value,id)
//{
//    if(id=='sbu'){
//        if((Value >=25 && Value <=32) ||(Value>=43 && Value<=49))
//        {
//            $('#'+id).val("Engineering");
//        }
//        else if((Value >=33 && Value <=34) ||(Value>=50 && Value<=62))
//        {
//            $('#'+id).val("IT");
//        }
//        else if(Value >=35 && Value <= 36 ||(Value>=63 && Value<=77))
//        {
//            $('#'+id).val("ERP");
//        }
//        else{
//            $('#'+id).val("All");
//        }
//    }
//    else if(id=='sbuFilter')
//    {
//        if((Value >=25 && Value <=32) ||(Value>=43 && Value<=49))
//        {
//            $('#'+id).val("9");
//        }
//        else if((Value >=33 && Value <=34) ||(Value>=50 && Value<=62))
//        {
//            $('#'+id).val("10");
//        }
//        else if(Value >=35 && Value <= 36 ||(Value>=63 && Value<=77))
//        {
//            $('#'+id).val("11");
//        }
//        else{
//            $('#'+id).val("All");
//        }
//    }
//}

function setSbu(Value,id)
{
   // alert("MUNI");
    if(id=='sbu'){
        //alert(Value);
        if(Value==3 || Value==4 || Value==30)
        {
            $('#'+id).val("PES");
        }
        else if(Value==6 || Value == 7 || Value==8 || Value == 9 || Value == 10 || Value == 11)
        {
            $('#'+id).val("TS");
        }
        else{
            $('#'+id).val("All");
        }
    }
    else if(id=='sbuFilter')
    {
        if(Value==3 || Value==4 || Value==30)
        {
            $('#'+id).val("2");
        }
        else if(Value==6 || Value == 7 || Value==8 || Value == 9 || Value == 10 || Value == 11)
        {
            $('#'+id).val("5");
        }
        else{
            $('#'+id).val("All");
        }
    }
}
