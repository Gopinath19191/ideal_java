<%-- 
    Document   : eCardView
    Created on : 3 Feb, 2022, 11:24:37 AM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Pick Me Cards</title>
        <style>
            fieldset legend {
                font-size: 140%;
                color: #0F68B3;
                padding: 4px;
                background: #fff;
                font-weight: bold;
            }
            fieldset {
                border: solid 1.5px #0F68B3;
                margin: 10px;
                border-radius: 5px;
                padding: 10px;
            }
            #content{
                float: left;
                width: 898px;
                background: #FFF;
                border: 1px solid #CADFF2;
                padding: 20px;
                margin: 10px 20px 20px 20px;
            }
            form label {
                width: 160px;
                text-align: left;
                font-weight: bold;
                color: #666666;
            }
            form input[type=text] {
                background: url(images/text-bg.gif) repeat-x scroll center top #FFFFFF;
                border: 1px solid #C4D1E0;
                font-family: arial;
                font-size: 13px;
                height: 20px;
                width: 225px;
                box-sizing: content-box;
                display: inline-block;
                padding: 2px 2px 1px 3px;
                color: #000000;
            }
            form select {
                background: url(images/text-bg.gif) repeat-x scroll center top #FFFFFF;
                border: 1px solid #C4D1E0;
                font-family: arial;
                font-size: 13px;
                height: 20px;
                width: 225px;
                box-sizing: content-box;
                display: inline-block;
                padding: 2px 2px 1px 3px;
                color: #000000;
            }
            form label {
                width: 160px;
                text-align: left;
                font-weight: bold;
                color: #666666;
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
            .goToList {
                color: #4C83B3;
                float: right;
                font-size: 12px;
                font-weight: bold;
                margin: 20px 40px 10px;
            }
            .page_heading {
                color: #666666;
                font-size: 18px;
                font-weight: bold;
                display: inline-block;
                width: 35%;
                padding: 25px 0px 0px 0px;
                margin: 0px 0px 10px 0px;
            }
            table tr td{
                padding: 5px;
            }
        </style>
        <script>
            var jsonArr = [];
            $(document).ready(function(){
                $(".successMessage").fadeOut(5000);
                
                $("#employee_search").autocomplete("getEmployeeSearch.htm", {
                    minChars: 1,
                    width: 350,
                    matchContains: true
                });
                
                $(".qualitysubmit").click(function(){
                    var card_id = $("#card_id").val();
                    var given_to = $("#given_to").val();
                    var created_by = $("#created_by").val();
                    var comments = $("#comments").val();
                    
                    if(card_id=='' || card_id == 'null'){
                        $("#card_id").css("border","1px solid #bfbfbf");
                        $(".error").html('Please select card Type');
                        return false;
                    }else if(given_to=='' || given_to == 'null'){
                        $("#employee_search").css("border","1px solid #bfbfbf");
                        $(".error").html('Please select a person');
                        return false;
                    }else if(given_to==created_by ){
                        $("#employee_search").css("border","1px solid #bfbfbf");
                        $(".error").html("Can't give card for self");
                        return false;
                    }else if(comments=="" || comments == 'null'){
                        $("#employee_search").css("border","1px solid #bfbfbf");
                        $(".error").html("Please type appreciation Comments");
                        return false;
                    }else{
                        $(".error").html('');
                        $('#addEcard').attr("action", "giveEcard.htm");
                        $('.qualitysubmit').hide();
                        return true;
                    }
                    
                });
                
                $("#employee_search").result(function(event, data, formatted) {
                    if (data.length>0) {
                        $("#given_to").val(data[1]);
                        $("#rm_name").val(data[4]);
                        $("#emp_band").val(data[2]);
                        $("#emp_des").val(data[3]);
                        $("#emp_unit").val(data[5]);
                        $("#emp_subunit").val(data[6]);
                    }else{
                        $("#given_to").val("");
                        $("#rm_name").val("");
                        $("#emp_band").val("");
                        $("#emp_des").val("");
                        $("#emp_unit").val("");
                        $("#emp_subunit").val("");
                    }
                    
                });

            });
            function setImage(val){
                if(val!=''){
                    var option = $('option:selected', val).attr('fileId');
                    //alert(option+"+"+val.value);
                    $("#file_name").val(option);
                    $(".pickcard_image").css("display","none");
                    document.getElementById("pick_card_image_"+val.value).style.display = "block";
                }else{
                    $(".pickcard_image").css("display","none");
                }

            }
        </script>
    </head>
    <body>
        <div>
            <div class="container_inner">
                <div class="page_heading">
                    Pick Me Cards
                </div>
                <div class="goToList">
                    <img src="/allowance/images/icon_list.png" title="List Procurement" alt="List Procurement">
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getPickMeCardList.htm?mycard=0">Given Card List</a>
                    <img src="/allowance/images/icon_list.png" title="List Procurement" alt="List Procurement">
                    <a style="text-decoration:none;color: #4C83B3;" target="_self" href="getPickMeCardList.htm?mycard=1">Received Card List</a>
                </div>
            </div>
            <div class="successMessage">${submit_status}</div>
            <form name="addEcard" id="addEcard" method="post" action="#">
                <div id="content">
                    <fieldset id ="procurementDetails">
                        <legend>Pick Me Card - Details</legend>
                        <table>
                            <tr>
                                <td><label>Card Type</label></td>
                                <td>${card_details.card_name}</td>
                            </tr>
                            <c:if test="${list_type=='0'}">
                                <tr>
                                    <td><label>Given To</label></td>
                                    <td>${card_details.employee_name}</td>
                                </tr>
                                <tr>
                                    <td><label>RM Name</label></td>
                                    <td>${card_details.rm_name}</td>
                                </tr>
                                <tr>
                                    <td><label>Band</label></td>
                                    <td>${card_details.band_name}</td>
                                </tr>
                                <tr>
                                    <td><label>Designation</label></td>
                                    <td>${card_details.designation}</td>
                                </tr>
                                <tr>
                                    <td><label>Unit</label></td>
                                    <td>${card_details.unit_name}</td>
                                </tr>
                                <tr>
                                    <td><label>Sub Unit</label></td>
                                    <td>${card_details.sub_unit_name}</td>
                                </tr>
                                <tr>
                                    <td><label>Given on</label></td>
                                    <td>${card_details.given_on}</td>
                                </tr>
                            </c:if>
                            <c:if test="${list_type=='1'}">
                                <tr>
                                    <td><label>Received From</label></td>
                                    <td>${card_details.created_by}</td>
                                </tr>
                                <tr>
                                    <td><label>Received on</label></td>
                                    <td>${card_details.given_on}</td>
                                </tr>
                            </c:if>
                            <tr>
                                <td><label>Appreciation Comments</label></td>
                                <td>${card_details.comments}</td>
                            </tr>
                            <tr>
                                <td><label>e Card</label></td>
                                <td>
                                    <a href="attachmentDownload.htm?fileName=${card_details.file_name}&fileType=jpg" target="_blank" name="file" id="attachedFileValue">${card_details.card_name}</a>
                                </td>
                            </tr>
                        </table>
                    </fieldset>
                </div>
            </form>
        </div>
    </body>
</html>

