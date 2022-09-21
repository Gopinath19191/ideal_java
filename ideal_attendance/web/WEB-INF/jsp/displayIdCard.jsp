<%-- 
    Document   : displayIdCard
    Created on : 9 Dec, 2021, 4:08:55 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Details</title>
    </head>
    <style>
        #procurement_table tr td {
            padding: 5px;
        }
        .buttonDownload{
            padding: 0px 10px 0px 30px;
            height: 32px;
            font-family: Arial;
            font-weight: bold;
            font-size: 13px;
            color: #FFFFFF;
            text-align: center;
            border: 1px solid #4492BF;
            cursor: pointer;
            border-radius: 10px;
        }
        #buttonDownload{
            background: #316CA8;
            background-size: 19%;
        }
        #buttonUpdate{
            background: #316CA8;
            background-size: 19%;
        }
        .photo_error{
            display: none;
            color: red;
        }
    </style>
    <script type="text/javascript">
        
        $(document).ready(function() {
            $(".display_id").css({"display": "none"});
            $(".display_none").css({"display": "none"});
            if(/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(navigator.userAgent)){
                $(".display_id").css({"display": "none"});
                $(".display_none").css({"display": "block"});
            }else{
                $(".display_id").css({"display": "block"});
                $(".display_none").css({"display": "none"});
            }
            
            $("#buttonDownload").click(function (e){
                location.href='getEmployeeIdCardDownload.htm?employee_id=${employee_id}';
            });
        });
        
        function validateSubmit(){
            var error = 0;
            var attachment = 0;
            var bld_grp = $("#blood_group").val();
            var emg_nam = $("#emergency_name").val();
            var emg_rls = $("#emergency_relationship").val();
            var emg_phn = $("#emergency_number").val();
            var pht_nam = $("#photo_name").val();
            if(bld_grp=="" || bld_grp==null){
                $(".photo_error").css({"display": "block"});
                error++;
            }else if(emg_nam=="" || emg_nam==null){
                $(".photo_error").css({"display": "block"});
                error++;
            }else if(emg_rls=="" || emg_rls==null){
                $(".photo_error").css({"display": "block"});
                error++;
            }else if(emg_phn=="" || emg_phn==null){
                $(".photo_error").css({"display": "block"});
                error++;
            }else if(pht_nam == "" || pht_nam == null){
                $(".photo_error").css({"display": "block"});
                error++;
            }
            return error;
        }
        
        
    </script>
    
    <body>
        <div class="container_inner"> 
            <div class="page_heading">View Employee e-ID Card</div>
        </div>
        <form name="saveDetails" id="approveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
            <div id="content">
                <div class="display_id">
                    <iframe src="http://ideal.hindujatech.com/uploads/id_card_photo/${file_name}#toolbar=0&navpanes=0&scrollbar=0" width="100%" height="350px"></iframe>
                </div>
                <div class="display_none">
                    <p>This feature is not available in hand-held devices</p>
                </div>
                <div class="buttonClass">
                    <input type="button" name="buttonDownload" id="buttonDownload" value="Back" class="buttonDownload" />
                </div>
            </div>
        </form>
        
    </body>
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
</html>
