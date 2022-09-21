<%-- 
    Document   : bulkUpload
    Created on : 17 Nov, 2017, 6:57:46 PM
    Author     : 16113
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>OJF Template Upload</title>  
        <link href="${pageContext.request.contextPath}/css/bulkcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">       
          
                
            function uploadOjfDetails(){
                
                var fileFullname = document.getElementById("fileAttachment3").value;
                if(fileFullname==""){
                    alert("Please select a file");
                }
                else{
                    var arr = fileFullname.split(".");
                    var extention = arr[arr.length-1].toLowerCase(); 
                    if(extention == "xls"){                    
                        $('#uploadOjf').attr("action", "uploadOjfDetailsByExcel.htm");            
                        document.uploadOjf.submit();                     
                    }
                    else{
                        alert("Upload unsuccessful, template is not valid (use noly .xls");                       
                     
                    }
                      
                }
                 
            }          
            
            function resetFunctionOJF(){
                document.getElementById("uploadOjf").reset();
            }
         
        </script>  
        <style>
            .tabletools{
                background-color: #ECE6D2;
                border: 1px solid #DDD6B7;
                margin: 10px 0;
                padding: 10px;
                color: #000000;
            }
            td{
                border: 1px solid #CEDFF1;
                text-align: center;
                width: 200px;
            }
            .lable,.template,.fileBrowsed,.buttons{
                width: 250px;
                float: left;
                height: 20px;
                margin: 0px;
                border-bottom: 1px solid #CEDFF1;
                border-left: 1px solid #CEDFF1;
                padding: 10px 5px; 
            }
            .buttons{
                border-right: 1px solid #CEDFF1;
                text-align: center;
            }
            .cancel,.upload_bttn{
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
            .filetrInnerWrap{
                font-size: 13px;
                color: #666666;
                font-weight: bold;
                vertical-align: top;
                line-height: 1.6;
            }
            .notice_page {
                padding: 10px;
                border:1px solid #ddd6b7;
                background-color: #ece6d2;   
                background-repeat:no-repeat;
                margin-bottom: 10px;
            }

            .notice_page_ul li{
                margin-left : 40px;
                list-style: url(../BulkUpload/images/tick.png);
                font-size: 12px;
                color : #666666;
                font-weight: bold;
                vertical-align: top;
            }
            .notice_page_ul{
                margin: 0px;
                padding: 0px;
            }
        </style>
    </head>


    <body >

        <div id="main_container" style="overflow-x: hidden;">
            <div class="center_content" style="padding: 30px 0px 10px 0px;">
                <div class="page_heading">
                    OJF Template Upload
                </div>
            </div> 

            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:20px;">
                    <ul class="notice_page_ul">
                        <li>For uploding please make sure you use the same respective module templates</li>
                        <li>Make sure you dont edit the header of the templates</li>
                        <li>The data entered should be in the same format as mentioned in the header of the template</li>                       
                    </ul>
                </div>
            </div>
            <br>
            <div id="errordiv">
                <span style=" color: red;">${returnMsg}</span>
            </div>
            <div id="errordiv"> <span style="color: green">${successMsg}</span></div>
            <div class="upload table">
                <div class="tabledisplay"> 
                    <table RULES=NONE FRAME=BOX>
                        <thead>
                            <tr style="font-weight: bold">
                                <td>Module</td>
                                <td>Template</td>
                                <td>Import</td>
                                <td>Action</td>
                            </tr>
                        </thead>
                    </table>
                   
                    <form method="post"  name="uploadOjf" id="uploadOjf" enctype="multipart/form-data">
                        <div class="lable">
                             Ojf Details upload
                        </div>
                        <div class="template">
                             <a href="downloadFile.htm?fileName=OJF"> OJF Details Template.xls </a>
                        </div>
                        <div class="fileBrowsed">
                            <input type="file" value="" id="fileAttachment3" name="fileAttachment3">
                        </div>
                        <div class="buttons">
                            <input type="button" class="upload_bttn" value="Upload" onclick="uploadOjfDetails()">
                            <input type="button" class ="cancel" value="Cancel" onclick="resetFunctionOJF()">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
