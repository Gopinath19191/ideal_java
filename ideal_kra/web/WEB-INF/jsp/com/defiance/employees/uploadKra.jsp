<%-- 
    Document   : bulkUpload
    Created on : 2 Mar, 2016, 6:57:46 PM
    Author     : 16113
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee KRA</title>  
        <link href="${pageContext.request.contextPath}/css/bulkcss.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript">       
          
            $(document).ready(function() {
                $("#errordiv").fadeOut(10000);
                $("#successdiv").fadeOut(10000);
            } );
            function updateKra(){
               
                var fileFullname = document.getElementById("fileAttachment").value;                
                var financialYear = document.getElementById("financialYear").value;     
                var quarterId = document.getElementById("quarterId").value;     
                if(fileFullname==""){
                    alert("Please select a file");
                }
                else{
                    var arr = fileFullname.split(".");
                    var extention = arr[arr.length-1].toLowerCase(); 
                    if(extention == "xls"){                    
                        $('#bulkUploadKra').attr("action", "updateKraByExcel.htm?financialYear="+financialYear+"&quarterId="+quarterId);            
                        document.bulkUploadKra.submit();				            
                    }
                    else{
                        alert("Please upload .xls file type alone");
                    }
                }
            }
            function resetFunctionBand(){
                document.getElementById("bulkUploadKra").reset();
            }
            function resetFunctionRM(){
                document.getElementById("bulkUploadRM").reset();
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
            .lable,.template,.fileBrowsed,.button{
                width: 336px;
                float: left;
                margin: 0px;
                border:1px solid #CEDFF1;
                background-color: #FFF;
            }
            .template,.fileBrowsed{
                padding:15px 5px 10px 5px;
                height: 32px;
            }
            .button{
                padding:10px 5px;
                height: 37px;
                width: 230px;
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
            #table_header th{
                background: url(../images/box-strip.jpg) repeat-x scroll top center #C9D4DA;
                border: 1px solid #BDC9D1;
                text-align: center;
                padding:3px;
            }
            #table_header tr td{
                padding:3px 3px 3px 10px;
                font-size: 12px;
                margin-left:10px;
                text-align: left;
            }
            .file_upload{
                margin-bottom:150px;
            }
            #errordiv, #successdiv{
               left:0px;
               text-align: center;
               margin-top:20px; 
            }
        </style>
    </head>


    <body >

        <div id="main_container">
            <div class="center_content" style="padding: 30px 0px 10px 0px;">
                <div class="page_heading">
                    KRA Upload
                    <div class="listLink">
                        <img src="${pageContext.request.contextPath}/images/icon_list.png">
                        <a href="rmListKra.htm" target="_self" style="text-decoration:none;color: #4C83B3;">List KRA</a>
                    </div>
                    <div class="listLink" >
                    <img src="${pageContext.request.contextPath}/images/add_1.png">
                    <a target="_self" style="text-decoration:none;color: #4C83B3;" href="generalInfo.htm?actionName=employeeKra&page=1&associateId=">Add Individual KRA</a>
                </div>
                </div>
            </div> 

            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:20px;">
                    <ul class="notice_page_ul">
                        <li>For uploading KRA please make sure to use the respective templates</li>
                        <li>Don't edit the header of the templates</li>
                        <li>The data should be in the same format as mentioned in the header of the template</li>
                        <li>Make sure the template file format should be .xls</li>
                    </ul>
                </div>
            </div>
            <div id="errordiv">
                <span style=" color: red;width:940;text-align: center;">${returnMsg}</span>
            </div>
            <div id="successdiv">
                <span style="color: green;width:940;text-align: center;">${successMsg}</span>
            </div>
            <div class="upload table">
                <div class="tabledisplay"> 
                    <div class="page_heading" style="padding-left:10px;font-size: 15px;">Download KRA Templates</div>
                    <table id="table_header">
                        <tr style="font-weight: bold">
                            <th>Contributers</th>
                            <th>Template</th>
                        </tr>
                        <tr>
                            <td>BUH</td>
                            <td><a href="downloadFile.htm?fileName=kra_buh"> BUH Template.xls </a></td>
                        </tr>
                        <tr>
                            <td>Sales</td>
                            <td><a href="downloadFile.htm?fileName=kra_sales"> Sales Template.xls </a></td>
                        </tr>
                        <tr>
                            <td>Practice Head</td>
                            <td><a href="downloadFile.htm?fileName=kra_practicehead"> Practice Head Template.xls </a></td>
                        </tr>
                        <tr>
                            <td>SUB Practice and Project Managers</td>
                            <td><a href="downloadFile.htm?fileName=kra_subpractice"> SUB Practice and Project Managers Template.xls </a></td>
                        </tr>
                        <tr>
                            <td>Onsite Co-ordinators</td>
                            <td><a href="downloadFile.htm?fileName=kra_onsite">Onsite_Co-ordinators Template.xls </a></td>
                        </tr>
                        <tr>
                            <td>Individual Contributors</td>
                            <td><a href="downloadFile.htm?fileName=kra_contributors">Individual Contributors Template.xls </a></td>
                        </tr>
                        <tr>
                            <td>Biz Operations</td>
                            <td><a href="downloadFile.htm?fileName=kra_operations">Biz Operations Template.xls </a></td>
                        </tr>
                        <tr>
                            <td>Functional Heads</td>
                            <td><a href="downloadFile.htm?fileName=kra_functionalhead">Functional Heads Template.xls </a></td>
                        </tr>
                    </table>
                    <form method="post"  name="bulkUploadKra" id="bulkUploadKra" enctype="multipart/form-data">
                        <div class="page_heading" style="padding:10px;font-size: 15px;">Upload KRA</div>
                        <div class="file_upload"> 
                            <div class="template">
                                <input type="hidden" id="employeeId" name="employeeId" value="${employeeId}" readonly/>
                                <label class="label">Financial Year </label>
                                <select class="financial_year" id="financialYear" name="year">
                                    <c:forEach items="${testFin}" var="testFin" varStatus="i">
                                        <option value="${testFin}" ${testFin eq current_financial_year ? 'selected="selected"':''}>${testFin}</option>
                                    </c:forEach>
                                </select>
                                <label class="label">Quarter </label>
                                <select class="quarter" name="quarterId" id="quarterId">
                                    <c:forEach items="${quarterList}" var="quarterList" varStatus="i">
                                        <option value="${quarterList.quarter_id}" ${quarterList.quarter_id eq current_quarter ? 'selected="selected"':''}>${quarterList.quarter_name}</option>
                                    </c:forEach>
                                </select>

                            </div>
                            <div class="fileBrowsed">
                                <input type="file" value="" id="fileAttachment" name="fileAttachment">
                            </div>
                            <div class="button">
                                <!--<input type="button" class="upload_bttn" value="Upload" onclick="updateKra()">-->  
                            </div>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>
