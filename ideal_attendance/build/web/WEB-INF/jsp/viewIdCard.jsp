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
            background: url(./images/download.png) no-repeat 8px 8px #316CA8;
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
            
            $("#buttonDownload").click(function (e){
                var error = validateSubmit();
                if(error==0){
                    location.href='getIdDislay.htm?employee_id=${employee_details.employee_id}';
                }else{
                    return false;
                }
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
            <div class="page_heading">View Employee Details</div>
        </div>
        <form name="saveDetails" id="approveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
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
                        <tr>
                            <td>
                                <label>Residential Address :</label>
                            </td>
                            <td>
                                <p>${employee_details.address}</p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Blood Group :</label>
                            </td>
                            <td>
                                <p>${employee_details.blood_group}</p>
                                <input type="hidden" id="blood_group" name ="configKey" value="${employee_details.blood_group}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Emergency Contact:</label>
                            </td>
                            <td>
                                <p><input type="hidden" id="emergency_id" name ="configKey" value="${employee_details.configKey}"/></p>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Name :</label>
                            </td>
                            <td>
                                <p>${employee_details.emergency_name}</p>
                                <input type="hidden" id="emergency_name" name ="emergency_name" value="${employee_details.emergency_name}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Relationship :</label>
                            </td>
                            <td>
                                <p>${employee_details.emergency_relationship}</p>
                                <input type="hidden" id="emergency_relationship" name ="emergency_relationship" value="${employee_details.emergency_relationship}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Phone Number :</label>
                            </td>
                            <td>
                                <p>${employee_details.emergency_number}</p>
                                <input type="hidden" id="emergency_number" name ="emergency_number" value="${employee_details.emergency_number}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Photo :</label>
                            </td>
                            <td>
                                <input type="hidden" id="photo_name" value="${employee_details.configValue}"/>
                                <c:if test="${employee_details.configValue == '' || employee_details.configValue == null}">
                                    <p>No Photo Available</p>
                                </c:if>
                                <c:if test="${ employee_details.configValue != '' && employee_details.configValue != null}">
                                    <div class="">
                                        <img src="http://ideal.hindujatech.com/uploads/id_card_photo/${employee_details.configValue}" title="Employee Photo" alt="Employee Photo" height="150px" width="150px">
                                    </div>
                                </c:if>
                                    <p class="photo_error">No Photo found Please share white background photo to HR</p>
                            </td>
                        </tr>
                    </table>
                </fieldset>
                <div class="">
                    <p>To modify your personal details/photos please reach out to your HR Business Partner.</p>
                </div>
                <div class="buttonClass">
                    <input type="button" name="buttonDownload" id="buttonDownload" value="View ID Card" class="buttonDownload" />
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
