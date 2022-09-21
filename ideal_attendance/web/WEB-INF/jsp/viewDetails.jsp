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
        .buttonDownload, .buttonMail, .buttonUpdate{
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
            background-size: 11%;
        }
        #buttonDownload{
            background: url(./images/download.png) no-repeat 8px 8px #316CA8;
            background-size: 19%;
        }
        #buttonUpdate{
            background: url(./images/icon_btn_submit.png) no-repeat 8px 8px #316CA8;
            background-size: 12%;
        }
    </style>
    <script type="text/javascript">
        
        $(document).ready(function() {
            
            $("#buttonUpdate").click(function (e) {
                var error = validateSubmit();
                if(error==0){
                    $("#approveDetails").submit();
                    startLoading();
                }else{
                    return false;
                }
            });                
            $("#buttonDownload").click(function (e){
                var error = validateSubmit();
                if(error==0){
                    location.href='getPdfDownload.htm?employee_id=${employee_details.employee_id}';
                }else{
                    return false;
                }
            });
            $("#buttonMail").click(function (e){
                var error = validateSubmit();
                if(error==0){
                    location.href='triggerMailtoFacility.htm?employee_id=${employee_details.employee_id}';
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
                $("#blood_group").css({"outline": "1px solid red"});
                error++;
            }else if(emg_nam=="" || emg_nam==null){
                $("#emergency_name").css({"outline": "1px solid red"});
                error++;
            }else if(emg_rls=="" || emg_rls==null){
                $("#emergency_relationship").css({"outline": "1px solid red"});
                error++;
            }else if(emg_phn=="" || emg_phn==null){
                $("#emergency_number").css({"outline": "1px solid red"});
                error++;
            }
            
            if(pht_nam == "" || pht_nam == null){
                $("#attached_photo").css({"outline": "1px solid red"});
                var fileExt = $('#attached_photo').val().split('.').pop().toLowerCase();
                if (fileExt.length > 0) {
                    if ($.inArray(fileExt, ['jpg', 'png', 'jpeg']) == -1) {
                        $("#attached_photo").css({"display": "block"});
                        $("#attached_photo").css({"outline": "1px solid red"});
                        error++;
                    } else {
                        $("#attached_photo").css({"outline": "none"});
                        $("#attachFileerrormessage").css({"display": "none"});
                    }
                }else{
                    $("#attached_photo").css({"display": "block"});
                    $("#attached_photo").css({"outline": "1px solid red"});
                    error++;
                }
            }
            return error;
        }
        
        
    </script>
    
    <body>
        <div class="container_inner"> 
            <div class="page_heading">View Employee Details</div>
            <div class="listLink">
                <img src="/ideal_attendance/images/icon_list.png" title="List Employee" alt="List Employee">
                <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getEmployeeIdCardList.htm?type=i">Employee List</a>
            </div>
        </div>
        <form action="updateEmployeeDetails.htm" name="saveDetails" id="approveDetails" method="post" enctype="multipart/form-data" autocomplete="off">
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
                                <select name="blood_group" id="blood_group">
                                    <option value="">--Select--</option>
                                    <c:forEach items="${blood_group}" var="blood_group" varStatus="i">
                                        <option value="${blood_group}" ${employee_details.blood_group == blood_group ? 'selected':''}>${blood_group}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Emergency Contact Details:</label>
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
                                <input type="text" id="emergency_name" name ="emergency_name" value="${employee_details.emergency_name}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Relationship :</label>
                            </td>
                            <td>
                                <input type="text" id="emergency_relationship" name="emergency_relationship" value="${employee_details.emergency_relationship}"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <label>Phone Number :</label>
                            </td>
                            <td>
                                <input type="text" id="emergency_number" name="emergency_number" value="${employee_details.emergency_number}"/>
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
                                <input type="file" name="attached_photo" id="attached_photo" class="filebox" size="20" />
                            </td>
                        </tr>
                    </table>
                </fieldset>
                
                <div class="buttonClass">
                    <input type="button" name="buttonCancel" id="buttonDownload" value="Download" class="buttonDownload" />
                    <input type="button" name="buttonUpdate" id="buttonUpdate" value="Update Details" class="buttonUpdate"/>
                    <input type="button" name="buttonCancel" id="buttonMail" value="Trigger Mail to Admin" class="buttonMail"/>
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
