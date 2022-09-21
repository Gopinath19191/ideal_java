<%-- 
    Document   : redirectPage
    Created on : 19 Feb, 2022, 8:16:59 AM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Pick Me Card Report </title>
    </head>
    <style type="text/css">
        body{
            width:100%;
            background: linear-gradient(to top,rgba(196,209,224,0.25), rgba(196,209,224,.85)),url(/allowance/images/ee_bg.jpg);
            background-repeat: no-repeat;
            background-size: 100% 100%;
            width: auto;
            height: auto;
            padding-bottom: 50px;
            background-attachment: fixed;
            min-height: 100vh;
        }
        #main_panel{
            width:1000px;
            margin-left: auto;
            margin-right: auto; 
        }
        #content {
            float: left;
            width: 898px;
            background: #FFF;
            border: 1px solid #CADFF2;
            padding: 20px;
            margin: 10px 20px 20px 20px;
        }
        form label {
            width: 160px;
            text-align: left;
            font-weight: bold;
            color: #666666;
        }
        textarea {
            border: 1px solid #C4D1E0;
            float: left;
            font-family: arial;
            font-size: 12px;
            height: 20px;
            min-height: 20px;
            max-height: 20px;
        }
        #view_action, #not_willing{
            display: none;
        }
    </style>
    <script type="text/javascript">
        function enableContent(){
            var type = $("#willingness").val();
            if(type=='y'){
                $("#view_action").css({"display": "block"});
                $("#not_willing").css({"display": "none"});
            }else if(type=='n'){
                $("#not_willing").css({"display": "block"});
                $("#view_action").css({"display": "none"});
            }else{
                $("#view_action").css({"display": "none"});
                $("#not_willing").css({"display": "none"});
            }
        }
        function addRowAttachment(currentRow) {
            var rowId = parseInt($('#row_count').val()) + 1;
            $('#row_count').val(rowId);
            var tr = '';
            tr+='<tr id="TA_'+rowId+'">';
            tr+='<input type="hidden" name="row_count" id="row_count_1" value="1" />';
            tr+='<td>';
            tr+='<select class="area_interest" id="area_interest" name="area_interest">';
            tr+='<option value="">-- Select --</option>';
            tr+='<c:forEach items="${areaList}" var="areaList">';
            tr+='<option value="${areaList.interest_id}">${areaList.interest_name}</option>';
            tr+='</c:forEach>';
            tr+='</select>';
            tr+='</td><td>';
            tr+='<select  class="current_level" name="current_level">';
            tr+='<option value="">-- Select --</option>';
            tr+='<c:forEach items="${interest_level}" var="interest_level">';
            tr+='<option value="${interest_level.interest_id}">${interest_level.interest_name}</option>';
            tr+='</c:forEach>';
            tr+='</select></td>';
            tr+='<td><textarea class="description" rows="4" cols="50" Style="width:400px; min-width:400px;max-width:400px;" name="description" onpaste="return false"></textarea></td>';
            tr+='<td style="text-align:center;"><img class="addRemove" alt="Add" src="${pageContext.request.contextPath}/images/tm_add.png" onclick="addRowAttachment('+rowId+');" />';
            tr+='<img class="addRemove" alt="Remove" src="${pageContext.request.contextPath}/images/tm_delete.png" onclick="deleteRowAttachment(' + rowId + ',0)" /></td>';
            tr+='</tr>';
            tr = tr.replace(/selected="selected"/gi, "");
            tr = tr.replace(/selected/gi, "");
            $('#TA_' + currentRow).after(tr);
        }
        function deleteRowAttachment(row, val) {
            $('#TA_' + row).remove();
            return true;
        }
        function tableNullCheck(class_name) {
            var NullError = 0;
            var class_length = document.querySelectorAll("." + class_name).length;
            for (var i = 0; i < class_length; i++) {
                var class_name_val = document.querySelectorAll("." + class_name)[i].value;
                var class_name_val_length = $.trim(class_name_val).length;
                if (class_name_val_length > 0) {
                    document.querySelectorAll("." + class_name)[i].style.outline = "none";
                } else {
                    document.querySelectorAll("." + class_name)[i].style.outline = "1px solid red";
                    NullError++;
                }
            }
            return NullError;
        }
        $(document).ready(function(){
            parent.location.href = "http://ideal.hindujatech.com/";
            enableContent();
           $(".submitbutton").click(function(){
               var selection = $("#willingness").val();
               if(selection=='y'){
                    var error = 0;
                    var extend_hobby = document.getElementById("extend_hobby").value;
                    var commitment = document.getElementById("commitment").value;
                    var leadership = document.getElementById("leadership").value;
                    var area = tableNullCheck("area_interest");
                    var level = tableNullCheck("current_level");
                    var desc = tableNullCheck("description");
                    
                    if(extend_hobby=="" || extend_hobby==null){
                        $("#extend_hobby").css({"outline": "1px solid red"});
                        error++;
                    }else{
                        $("#extend_hobby").css({"outline": "none"});
                    }
                    if(commitment=="" || commitment==null){
                        $("#commitment").css({"outline": "1px solid red"});
                        error++;
                    }else{
                        $("#commitment").css({"outline": "none"});
                    }

                    if(leadership=="" || leadership==null){
                        $("#leadership").css({"outline": "1px solid red"});
                        error++;
                    }else{
                        $("#leadership").css({"outline": "none"});
                    }
                    
                    if(area==0){
                    }else{
                        error++;
                    }
                    if(level==0){
                    }else{
                        error++;
                    }
                    if(desc==0){
                    }else{
                        error++;
                    }
                    if(error==0){
                        $("#willingness").css({"outline": "none"});
                        $('#addEmployeeEngagement').attr("action", "submitEmployeeEngagement.htm");
                        $('.submitbutton').hide();
                        return true;
                    }else{
                        return false;
                    }
                    
               }else if(selection=='n'){
                    $("#willingness").css({"outline": "none"});
                    var reason = document.getElementById("reason").value.trim();
                    if(reason=="" || reason==null){
                        $("#reason").css({"outline": "1px solid red"});
                        return false;
                    }else{
                        $("#reason").css({"outline": "none"});
                        $('#addEmployeeEngagement').attr("action", "submitEmployeeEngagement.htm");
                        $('.submitbutton').hide();
                        return true;
                    }
                    
               }else{
                   $("#willingness").css({"outline": "1px solid red"});
                   return false;
               }
                
            });
        });
        
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="main_panel">
            <div class="container_inner">
                <div class="page_heading">
                    <p style="color:green;font-weight: bold;">Data Submitted Successfully!!!</p>
                </div>
            </div>
            
        </div>
        <script type="text/javascript">
            var loadingDivObj=(document.all);
            var ns4=document.layers;
            var ns6=document.getElementById&&!document.all;
            var ie4=document.all;
            if (ns4){
                loadingDivObj=document.loadingDiv;
            }else if (ns6){
                loadingDivObj=document.getElementById("loadingDiv").style;
            }else if (ie4){
                loadingDivObj=document.all.loadingDiv.style;
            }

            stopLoading();

            function stopLoading(){
                if(ns4){loadingDivObj.visibility="hidden";}
                else if (ns6||ie4) loadingDivObj.display="none";
            }

            function startLoading(){
                if(ns4){loadingDivObj.visibility="visible";}
                else if (ns6||ie4) loadingDivObj.display="block";
            }
        </script>
    </body>
</html>

