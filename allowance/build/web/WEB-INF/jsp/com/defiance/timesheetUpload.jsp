<%-- 
    Document   : timesheetUpload
    Created on : 12 May, 2022, 4:06:39 PM
    Author     : 16221
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Timesheet Upload</title>  
        <style>
/*            .tabletools{
                background-color: #ECE6D2;
                border: 1px solid #DDD6B7;
                margin: 10px 0;
                padding: 10px;
                color: #000000;
            }*/
            table{
                border-collapse: collapse;
                width:100%
            }
            table th{
                background: url(images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;
                color:#666666;
                height:20px;
            }
            table td{
                background:#FFFFFF;
                color:#666666;
                height:20px;
            }
            table tr td,table tr th{
                border: 1px solid #CEDFF1;
                text-align: center;
                width: 200px;
                padding:5px 10px;
            }
            .qualitysubmit {
                padding-left: 20px;
                background: url(images/icon_btn_submit.png) no-repeat 8px 8px #316CA8;
                width: 95px;
                height: 32px;
                font-family: Arial;
                font-weight: bold;
                font-size: 13px;
                color: #FFFFFF;
                text-align: center;
                border: 1px solid #4492BF;
                cursor: pointer;
            }
            .error{
                text-align: center;
                color:red;
            }
            .upload_bttn{
                background: #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-family: Arial;
                font-size: 13px;
                font-weight: bold;
                height: 25px;
                text-align: center;
                width: 70px;
            }
        </style>
        <script>
            function updateFile(){
                var fileFullname = document.getElementById("fileAttachment").value;                
         
                if(fileFullname==""){
                    alert("Please select a file");
                }
                else{
                    var arr = fileFullname.split(".");
                    var extention = arr[arr.length-1].toLowerCase(); 
                    if(extention == "xls"){  
                        //startLoading();
                        $('#uploadFile').attr("action", "readExcelFile.htm");            
                        document.uploadFile.submit();				            
                    }
                    else{
                        alert("Upload unsuccessful, template is not valid");
                    }
                }
            }  
        </script>
    </head>
    <body>
        <div>
            <div class="container_inner">
                <div class="page_heading">
                    Timesheet Upload
                </div>
            </div>
            <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:20px;">
                    <ul class="notice_page_ul">
                        <li>While uploading please make sure you use the same templates</li>
                        <li>Make sure you don't edit the header of the templates</li>
                        <li>The data entered should be in the same format as mentioned in the header of the template</li>                    </ul>
                </div>
            </div>
            <div id="errordiv"> <span style="color: green">${successMsg}</span></div>
            <form method="post"  name="uploadFile" id="uploadFile" enctype="multipart/form-data">
                <table>
                    <thead>
                        <tr style="font-weight: bold">
                            <th>Module</th>
                            <th>Template</th>
                            <th>Import</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr style="font-weight: bold">
                            <td>
                                <div class="lable">
                                    Toyota Timesheet Upload
                                </div>
                            </td>
                            <td>
                                <div class="template">
                                    <a href="downloadFile.htm"> Timsheet Template </a>
                               </div>
                            </td>
                            <td>
                                <div class="fileBrowsed">
                                    <input type="file" value="" id="fileAttachment" name="fileAttachment">
                                </div>
                            </td>
                            <td>
                                <div class="buttons">
                                    <input type="button" class="upload_bttn" value="Upload" onclick="updateFile()">
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
        </div>
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
            var loaderStartHeight = $("html").height();
            loadingDivObj.height = "1000px";
            if (ns4) {
                loadingDivObj.visibility = "visible";
                $("#loadingImage").attr("tabindex", -1).focus();
            } else if (ns6 || ie4)
                loadingDivObj.display = "block";
            $("#loadingImage").attr("tabindex", -1).focus();
        }
    </script>
</html>