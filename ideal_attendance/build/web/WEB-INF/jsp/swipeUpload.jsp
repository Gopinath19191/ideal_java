<%-- 
    Document   : bulkUpload
    Created on : 2 Mar, 2016, 6:57:46 PM
    Author     : 16113
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Attendance Upload</title>  
        <link href="${pageContext.request.contextPath}/css/bulkcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">       
          
            function updateBandDesign(){
                var fileFullname = document.getElementById("fileAttachment1").value;                
         
                if(fileFullname==""){
                    alert("Please select a file");
                }
                else{
                    var arr = fileFullname.split(".");
                    var extention = arr[arr.length-1].toLowerCase(); 
                    if(extention == "xls" || extention == "xlsx"){                    
                        $('#bulkUploadBand').attr("action", "updateEmployeeBandDesigByExcel.htm?updateFile=band");            
                        document.bulkUploadBand.submit();				            
                    }
                    else{
                        alert("Upload unsuccessful, template is not valid");
                     
                    }
                      
                }
                 
            }
            function updateRM(){
               
                var fileFullname = document.getElementById("fileAttachment2").value;                
         
                if(fileFullname==""){
                    alert("Please select a file");
                }
                else{
                    var arr = fileFullname.split(".");
                    var extention = arr[arr.length-1].toLowerCase(); 
                    if(extention == "xls" || extention == "xlsx"){                    
                        $('#bulkUploadRM').attr("action", "updateEmployeeMangerByExcel.htm?updateFile=RM");            
                        document.bulkUploadRM.submit();				            
                    }
                    else{
                        alert("Upload unsuccessful, template is not valid");
                     
                    }
                      
                }
                 
            }
            function resetFunctionBand(){
                document.getElementById("bulkUploadBand").reset();
            }
            function resetFunctionRM(){
                document.getElementById("bulkUploadRM").reset();
            }
         
        </script>  
    </head>


    <body >

        <div id="main_container">
            <div class="center_content" style="padding-top: 30px">
                <div class="page_heading">
                    Attendance Upload
                </div>
            </div> 
            <div class="notice_page">
                <div style="float:left; padding-left: 5px;padding-top: 5px;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div>
                    <ul class="notice_page_ul">
                        <li>For uploading please make sure date format should be dd-mm-yyyy format</li>
                        <li>Location should be Chennai/Pune/Bangalore/Customer</li>
                        <li>Make sure you don't edit the header of the templates</li>
                        <li>The data entered should be in the same format as mentioned in the header of the template</li>
                    </ul>
                </div>
            </div>
            <br>
            <div id="errordiv"><span style="color: red;">${returnMsg}</span></div>
            <div id="errordiv"><span style="color: green">${successMsg}</span></div>
            <div class="upload table">
                <div class="tabledisplay"> 
                    <table>
                        <thead>
                            <tr>
                                <td>Location</td>
                                <td>Template</td>
                                <td>Import</td>
                                <td>Action</td>
                            </tr>
                        </thead>
                    </table>
                    <form method="post"  name="bulkUploadBand" id="bulkUploadBand" enctype="multipart/form-data">
                        <div class="lable">
                            Chennai And Pune
                        </div>
                        <div class="template">
                             <a href="downloadFile.htm?fileName=chennai"> Template.xls </a>
                        </div>
                        <div class="fileBrowsed">
                            <input type="file" value="" id="fileAttachment1" name="fileAttachment1">
                        </div>
                        <div class="buttons">
                            <input class="upload_button" type="button" value="Upload" onclick="updateBandDesign()">
                            <input class="cancel_button"type="button" value="Cancel" onclick="resetFunction()">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
