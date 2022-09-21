<%-- 
    Document   : ndaMailTrigger
    Created on : 21 Aug, 2019, 2:56:36 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Employee Details</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
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
            background: url(./images/download.png) no-repeat 8px 8px #316CA8;
            background-size: 21%;
        }
        #buttonDownload{
            background: url(./images/download.png) no-repeat 8px 8px #316CA8;
            background-size: 25%;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            
            
        });
        function getNdaDownload() {
            $('#approveDetails').attr("action", "downloadNda.htm");
//            document.approveDetails.submit();
            document.getElementById("approveDetails").submit();
        } 
        
        function getFormBDownload() {
            $('#approveDetails').attr("action", "downloadFormB.htm");
//            document.approveDetails.submit();
            document.getElementById("approveDetails").submit();
        } 
    
        
        
    </script>
    
    <body>
        <div class="container_inner"> 
            <div class="page_heading">View Employee Details</div>
            <div class="listLink">
                <img src="/ideal_attendance/images/icon_list.png" title="List Procurement" alt="List Procurement">
                <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getEmployeeIdCardList.htm?type=n">Employee List</a>
            </div>
        </div>
        <form name="generateNda" action="#" id="approveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
            <div id="content">
                <fieldset id ="procurementDetails">
                    <legend>Employee Details</legend>
                    <table id="procurement_table">
                        <tr>
                            <td>
                                <label>Full Name in Block Letters :</label>
                            </td>
                            <td>
                                <p>${employee_details.employee_name}</p>
                                <input type="hidden" name = "employee_id" value="${employee_details.employee_id}">
                            </td>
                            <td>
                                <label>Employee/Consultant ID:</label>
                            </td>
                            <td>
                                <p>${employee_details.employee_number}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Date Of Birth :</label>
                            </td>
                            <td>
                                <p>${employee_details.birth_date}</p>
                            </td>
                            <td>
                                <label>Date of Joining :</label>
                            </td>
                            <td>
                                <p>${employee_details.date_of_joining}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Father Name :</label>
                            </td>
                            <td>
                                <input type="text" name ="father_name" value="${employee_details.father_name}"/>
                            </td>
                            <td>
                                <label>Age :</label>
                            </td>
                            <td>
                                <input type="text" name ="age" value="${employee_details.age}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Nature of work :</label>
                            </td>
                            <td>
                                <input type="text" name ="nature_of_work" value="-"/>
                            </td>
                            <td>
                                <label>Area of work :</label>
                            </td>
                            <td>
                                <input type="text" name ="area_of_work" value="-"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Number of workers to be engaged :</label>
                            </td>
                            <td>
                                <input type="text" name ="no_of_workers_engaged" value="-"/>
                            </td>
                            <td>
                                <label>ESI Code No./Insurance Details :</label>
                            </td>
                            <td>
                                <input type="text" name ="insurance_details" value="-"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>PF Code No :</label>
                            </td>
                            <td>
                                <input type="text" name ="pf_code_no" value="-"/>
                            </td>
                            <td>
                                <label>Sub Contract Name :</label>
                            </td>
                            <td>
                                <input type="text" name ="sub_contrac_name" value="-"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Sub Contract ESI Code No. :</label>
                            </td>
                            <td>
                                <input type="text" name ="sub_contract_esi_code" value="-"/>
                            </td>
                            <td>
                                <label>Sub Contract PF Code No. :</label>
                            </td>
                            <td>
                                <input type="text" name ="sub_contract_pf_code" value="-"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>ESI & PF (UAN) No./ Address :</label>
                            </td>
                            <td colspan="3">
                                <input type="text" name ="esi_pf_code" value=" - Hinduja Tech limited,Chennai-32"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Residential Address :</label>
                            </td>
                            <td colspan="3">
                                <textarea type="text" id="deliveryAddress" name="address" style="min-width:450px;max-width: 225px;height:40px;max-height: 100px;">${employee_details.address}</textarea>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                
                <div class="buttonClass">
                    <input type="button" name="buttonCancel" id="buttonDownload" value="NDA" class="buttonDownload" onclick="getNdaDownload();"/>
                    <input type="button" name="buttonCancel" id="buttonMail" value="Form B" class="buttonMail" onclick="getFormBDownload();"/>
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
