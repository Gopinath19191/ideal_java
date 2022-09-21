<%-- 
    Document   : triggerBonafied
    Created on : 23 Aug, 2019, 4:08:37 PM
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
        .buttonDownload, .buttonMail{
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
        #buttonMail{
            background: url(./images/mail.png) no-repeat 8px 8px #316CA8;
            background-size: 19%;
        }
        #buttonDownload{
            background: url(./images/download.png) no-repeat 8px 8px #316CA8;
            background-size: 19%;
        }
    </style>
    <script type="text/javascript">
        
        $(document).ready(function() {
            $("#deliveryDate").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd-mm-yy',
                minDate : 0
            });
            
            $("#buttonApprove").click(function (e) {
                var status=$("#approveStatus").val();
                $("#status").val(status);
                validateSubmit();
            });
            
            $("#buttonReject").click(function (e) {
                var status=$("#rejectStatus").val();
                $("#status").val(status);
                validateSubmit();
            });
            
            
        });
        
        function getBonafiedDownload() {
            $('#approveDetails').attr("action", "downloadBonafied.htm");
            document.getElementById("approveDetails").submit();
        }
        function triggerBonafiedLetter(){
            $('#approveDetails').attr("action", "triggerBonafiedtoEmployee.htm");
            document.getElementById("approveDetails").submit();
        }
        
        
    </script>
    
    <body>
        <div class="container_inner"> 
            <div class="page_heading">View Employee Details</div>
            <div class="listLink">
                <img src="/ideal_attendance/images/icon_list.png" title="List Procurement" alt="List Procurement">
                <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getEmployeeIdCardList.htm?type=b">Employee List</a>
            </div>
        </div>
        <form action="#" name="saveDetails" id="approveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
            <div id="content">
                <fieldset id ="procurementDetails">
                    <legend>Employee Details</legend>
                    <table id="procurement_table">
                        <tr>
                            <td>
                                <label>Employee Name :</label>
                            </td>
                            <td>
                                <p>${employee_details.employee_name}</p>
                                <input type="hidden" name = "employee_name" value="${employee_details.employee_name}">
                                <input type="hidden" name = "employee_id" value="${employee_details.employee_id}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Employee/Consultant ID:</label>
                            </td>
                            <td>
                                <p>${employee_details.employee_number}</p>
                                <input type="hidden" name = "employee_number" value="${employee_details.employee_number}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Date of Joining :</label>
                            </td>
                            <td>
                                <p>${employee_details.date_of_joining}</p>
                                <input type="hidden" name = "date_of_joining" value="${employee_details.date_of_joining}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Designation :</label>
                            </td>
                            <td>
                                <p>${employee_details.designation}</p>
                                <input type="hidden" name = "designation" value="${employee_details.designation}">
                                <input type="hidden" name = "gender" value="${employee_details.gender}">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Residential Address :</label>
                            </td>
                            <td>
                                <textarea type="text" id="deliveryAddress" name="address" style="min-width:450px;max-width: 225px;height:40px;max-height: 100px;">${employee_details.address}</textarea>
                            </td>
                        </tr>
                        
                    </table>
                </fieldset>
                
                <div class="buttonClass">
                    <input type="button" name="buttonCancel" id="buttonDownload" value="Download" class="buttonDownload" onclick="getBonafiedDownload();"/>
                    <input type="button" name="buttonCancel" id="buttonMail" value=" Trigger Mail" class="buttonMail" onclick="triggerBonafiedLetter();"/>
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

