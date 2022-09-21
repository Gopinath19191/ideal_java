<%-- 
    Document   : viewDetails
    Created on : 13 Aug, 2019, 2:47:35 PM
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
            padding: 0px 10px 0px 35px;
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
        #buttonExtension, #buttonIncrement, #buttonService, #buttonProbation{
            background: url(./images/download.png) no-repeat 8px 8px #316CA8;
            background-size: 14%;
        }
        #buttonMail{
            background: url(./images/mail.png) no-repeat 8px 8px #316CA8;
            background-size: 15%;
        }
    </style>
    <script type="text/javascript">
        
        $(document).ready(function() {
            $("#toDate").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd-mm-yy',
                minDate : 0
            });
            $("#eff_date").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd-mm-yy'
            });
            $("#prob_eff_date").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: 'dd-mm-yy'
            });          
            $("#buttonExtension").click(function (e){
                var toDate = $("#toDate").val();
                if(toDate == "" || toDate == null){
                    $("#toDate").css({"outline": "1px solid red"});
                    return false;
                }else{
                    location.href='getExtensionLetterDownload.htm?employee_id=${employee_details.employee_id}&toDate='+toDate;
                }
            });
            
            $("#buttonService").click(function (e){
                location.href='getServiceLetterDownload.htm?employee_id=${employee_details.employee_id}&type=${type}';
            });
            
            $("#buttonMail").click(function (e){
                location.href='triggerServiceLetter.htm?employee_id=${employee_details.employee_id}&type=${type}';
            });
            
            $("#buttonProbation").click(function (e){
                var toDate = $("#prob_eff_date").val();
                if(toDate == "" || toDate == null){
                    $("#prob_eff_date").css({"outline": "1px solid red"});
                    return false;
                }else{
                    location.href='getProbationLetter.htm?employee_id=${employee_details.employee_id}&type=${type}&toDate='+toDate;
                }
                
            });
            
            $("#buttonIncrement").click(function (e){
                var error = 0;
                var toDate = $("#toDate").val();
                var inc_amt = $("#inc_amount").val();
                var eff_date = $("#eff_date").val();
                if(toDate == "" || toDate == null){
                    $("#toDate").css({"outline": "1px solid red"});
                    error++
                }else if(inc_amt == "" || inc_amt == null){
                    $("#inc_amount").css({"outline": "1px solid red"});
                    error++
                }else if(eff_date == "" || eff_date == null){
                    $("#eff_date").css({"outline": "1px solid red"});
                    error++
                }
                if(error==0){
                    location.href='getIncrementLetterDownload.htm?employee_id=${employee_details.employee_id}&toDate='+toDate+'&inc_amount='+inc_amt+'&eff_date='+eff_date;
                }else{
                    return false;
                }
            });
        });
        
        
    </script>
    
    <body>
        <div class="container_inner"> 
            <div class="page_heading">View Employee Details</div>
            <div class="listLink">
                <img src="/ideal_attendance/images/icon_list.png" title="List Employee" alt="List Employee">
                <c:if test="${type=='p'}">
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getActiveEmployeeList.htm?type=${type}">Employee List</a>
                </c:if>
                <c:if test="${type!='p'}">
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getDcEmployeeList.htm?type=${type}">Employee List</a>
                </c:if>
                
            </div>
        </div>
        <form name="saveDetails" id="approveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
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
                                <input type="hidden" id="employee_id" name ="employee_id" value="${employee_details.employee_id}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Employee/Consultant ID:</label>
                            </td>
                            <td>
                                <p>${employee_details.employee_number}</p>
                                <input type="hidden" id="employee_number" name ="employee_number" value="${employee_details.employee_number}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Hinduja Tech Mail Id :</label>
                            </td>
                            <td>
                                <p>${employee_details.employee_official_mail_id}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Date Of Birth :</label>
                            </td>
                            <td>
                                <p>${employee_details.birth_date}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Date of Joining :</label>
                            </td>
                            <td>
                                <p>${employee_details.date_of_joining}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Designation :</label>
                            </td>
                            <td>
                                <p>${employee_details.designation}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Location :</label>
                            </td>
                            <td>
                                <p>${employee_details.base_location}</p>
                            </td>
                        </tr>
                        <c:if test="${type=='s'}">
                            <tr>
                                <td>
                                    <label>Released Date :</label>
                                </td>
                                <td>
                                    <p>${employee_details.configKey}</p>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${type=='e'}">
                            <tr>
                                <td>
                                    <label>Contract Start Date :</label>
                                </td>
                                <td>
                                    <p>${employee_details.fromDate}</p>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Contract End Date :</label>
                                </td>
                                <td>
                                    <input class="calender-field" type="text" name="toDate" id="toDate" value="${employee_details.toDate}" readonly/>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Increment Amount :</label>
                                </td>
                                <td>
                                    <input class="field" type="text" name="inc_amount" id="inc_amount" />
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <label>Increment Effective Date :</label>
                                </td>
                                <td>
                                    <input class="calender-field" type="text" name="effDate" id="eff_date" readonly/>
                                </td>
                            </tr>
                        </c:if>
                        <c:if test="${type=='p'}">
                            <tr>
                                <td>
                                    <label>Effective Date :</label>
                                </td>
                                <td>
                                    <input class="calender-field" type="text" name="effDate" id="prob_eff_date" readonly/>
                                </td>
                            </tr>
                        </c:if>
                    </table>
                </fieldset>
                
                <div class="buttonClass">
                    <c:if test="${type=='e'}">
                        <input type="button" name="buttonCancel" id="buttonExtension" value="Extension Letter" class="buttonDownload" />
                        <input type="button" name="buttonCancel" id="buttonIncrement" value="Increment Letter" class="buttonDownload" />
                    </c:if>
                    <c:if test="${type=='s'}">
                        <input type="button" name="buttonCancel" id="buttonService" value="Service Letter" class="buttonDownload" />
                        <input type="button" name="buttonCancel" id="buttonMail" value="Trigger Letter" class="buttonDownload" />
                    </c:if>
                    <c:if test="${type=='p'}">
                        <input type="button" name="buttonCancel" id="buttonProbation" value="Probation Letter" class="buttonDownload" />
                    </c:if>
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
