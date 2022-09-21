<%-- 
    Document   : triggerDeputation
    Created on : 30 Aug, 2019, 3:40:40 PM
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
            $("#effectiveDate").datepicker({
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
        
        function getDeputationDownload() {
            var customer = $("#customer_name").val();
            var customer_hr = $("#customer_hr").val();
            var customer_location = $("#customer_location").val();
            var department = $("#department").val();
            var ht_representative = tableNullCheck("ht_representative");
            var error = 0;
            if(customer=="" || customer==null){
                $("#customer_name").css({"outline": "1px solid red"});
                error++;
            }else{
                $("#customer_name").css({"outline": "none"});
            }
            
            if(customer_hr=="" || customer_hr==null){
                $("#customer_hr").css({"outline": "1px solid red"});
                error++;
            }else{
                $("#customer_hr").css({"outline": "none"});
            }
            
            if(customer_location=="" || customer_location==null){
                $("#customer_location").css({"outline": "1px solid red"});
                error++;
            }else{
                $("#customer_location").css({"outline": "none"});
            }
            
            if(department=="" || department==null){
                $("#department").css({"outline": "1px solid red"});
                error++;
            }else{
                $("#department").css({"outline": "none"});
            }
            
            if(ht_representative>0){
                error++;
            }else{
                
            }
            
            if(error==0){
                $('#approveDetails').attr("action", "downloadDeputation.htm");
                document.getElementById("approveDetails").submit();    
            }else{
                return false;
            }
            
        }
        function triggerDeputationLetter(){
            var customer = $("#customer_name").val();
            var customer_hr = $("#customer_hr").val();
            var customer_location = $("#customer_location").val();
            var department = $("#department").val();
            var ht_representative = tableNullCheck("ht_representative");
            var error = 0;
            if(customer=="" || customer==null){
                $("#customer_name").css({"outline": "1px solid red"});
                error++;
            }else{
                $("#customer_name").css({"outline": "none"});
            }
            
            if(customer_hr=="" || customer_hr==null){
                $("#customer_hr").css({"outline": "1px solid red"});
                error++;
            }else{
                $("#customer_hr").css({"outline": "none"});
            }
            
            if(customer_location=="" || customer_location==null){
                $("#customer_location").css({"outline": "1px solid red"});
                error++;
            }else{
                $("#customer_location").css({"outline": "none"});
            }
            
            if(department=="" || department==null){
                $("#department").css({"outline": "1px solid red"});
                error++;
            }else{
                $("#department").css({"outline": "none"});
            }
            
            if(ht_representative>0){
                error++;
            }else{
                
            }
            
            if(error==0){
                $('#approveDetails').attr("action", "downloadDeputation.htm");
                document.getElementById("approveDetails").submit();    
            }else{
                return false;
            }
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
                                <label>Customer Name :</label>
                            </td>
                            <td>
                                <select name="customer_name" id="customer_name">
                                    <option value="">--Select Customer</option>
                                    <c:forEach items="${customer_details}" var="customer_details">
                                        <option value="${customer_details.customer_name}">${customer_details.customer_name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Customer HR :</label>
                            </td>
                            <td>
                                <select name="customer_hr" id="customer_hr">
                                    <option value="">--Select HR</option>
                                    <c:forEach items="${customer_hr_name}" var="customer_hr_name">
                                        <option value="${customer_hr_name.customer_hr}">${customer_hr_name.customer_hr}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Customer Location :</label>
                            </td>
                            <td>
                                <select name="customer_location" id="customer_location">
                                    <option value="">--Select Location</option>
                                    <c:forEach items="${customer_locations}" var="customer_locations">
                                        <option value="${customer_locations.customer_location}">${customer_locations.customer_location}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Customer Departments :</label>
                            </td>
                            <td>
                                <select name="department" id="department">
                                    <option value="">--Select Department</option>
                                    <c:forEach items="${customer_depatrments}" var="customer_depatrments">
                                        <option value="${customer_depatrments.department}">${customer_depatrments.department}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Effective Date :</label>
                            </td>
                            <td>
                                <input class="calender-field" type="text" name="effective_date" id="effectiveDate" readonly/>
                            </td>
                        </tr>
                        
                        <tr>
                            <td>
                                <label>Hinduja Representative :</label>
                            </td>
                            <td>
                                <c:forEach items="${ht_representative}" var="ht_representative" varStatus="dataStatus">
                                    <input type="text" style="width:600px;margin-bottom: 5px;" class="ht_representative" name ="ht_representative_name" id ="ht_representative_${dataStatus.count}" value="${ht_representative.ht_representative}"/><br>
                                </c:forEach>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                
                <div class="buttonClass">
                    <input type="button" name="buttonCancel" id="buttonDownload" value="Download" class="buttonDownload" onclick="getDeputationDownload();"/>
                    <input type="button" name="buttonCancel" id="buttonMail" value=" Trigger Mail" class="buttonMail" onclick="triggerDeputationLetter();"/>
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

