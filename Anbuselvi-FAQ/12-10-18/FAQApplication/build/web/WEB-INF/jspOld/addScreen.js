/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*  jQuery there is a way to wait until everything on the page has been completely loaded so we will wrap our scripts with this idiom */
$(document).ready(function() {
    var unitId = '1';
    alert(unitId);
        
    $("#faq_unit_id").change(getQuestionCategory);  
        
});

function getQuestionCategory(){
    
    //    var faqUnit = $("#faqUnit option:selected");        
    var unitId = $("#faq_unit_id").val();
    alert(unitId);
    //var unitId = document.getElementById("#faqUnit").value;
    //alert("faqUnit:"+unitId);
    $.ajax({
        type:"GET",
        //        url:"./getAllQuestionCategory.htm",
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

function addRow(tableID,qaLen) {
    
    alert(tableID);
    alert(qaLen);
    var table = document.getElementById(tableID);

    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount-1);

    var cell1 = row.insertCell(0);
    var element1 = document.createElement("th");
    element1.name = "Question";
            
    //
    var para = document.createElement("th");
    var node = document.createTextNode("This is new.");
    para.appendChild(node);
    //
    element1.type = "checkbox";
    element1.name="chkbox[]";
    cell1.appendChild(element1);

    var cell2 = row.insertCell(1);
    cell2.innerHTML = rowCount + 1;

    var cell3 = row.insertCell(2);
    var element2 = document.createElement("input");
    element2.type = "text";
    var length=(table.rows.length)-1;
    element2.name = "operationParameterses["+length+"].name";
    cell3.appendChild(element2);

//            var table = document.getElementById(tableID);
//
//            var rowCount = table.rows.length;
//            var row = table.insertRow(rowCount);
//
//            var cell1 = row.insertCell(0);
//            var element1 = document.createElement("input");
//            element1.type = "text";
//            var length=(table.rows.length)-1;
//            element1.name="question["+length+"].name";
//            cell1.appendChild(element1);
//            
//            var cell2 = row.insertCell(1);
//            var element2 = document.createElement("input");
//            element2.type = "text";            
//            element2.name = "answer["+length+"].name";
//            cell2.appendChild(element2);

}

function deleteRow(tableID) {
    
    try {
        var table = document.getElementById(tableID);
        var rowCount = table.rows.length;

        for(var i=0; i<rowCount; i++) {
            var row = table.rows[i];
            var chkbox = row.cells[0].childNodes[0];
            if(null != chkbox && true == chkbox.checked) {
                table.deleteRow(i);
                rowCount--;
                i--;
            }
        }
    }catch(e) {
        alert(e);
    }
    
    
    
//    try {
//        var table = document.getElementById(tableID);
//        var rowCount = table.rows.length;                
//                
//        table.deleteRow(rowCount-1);
//
//    }catch(e) {
//        alert(e);
//    }
}