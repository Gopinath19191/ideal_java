<%-- 
    Document   : dunningCustomerMapping
    Created on : 22 Sep, 2020, 7:13:33 PM
    Author     : 16221
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Dunning Report Configuration</title>
        <style>
            table,td{
                border:1px solid #CEDFF1;
                border-collapse:collapse;
                text-align:center;
                color:#666666;
            }
            #tabledetails{
                margin-bottom: 100px;
            }
            #tabledetails th{
                background: url(images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;
                font-weight: bolder;
                font-size: 11px;
                /* padding: 5px 10px; */
                padding-top: 3px;
                border: 1px solid #CEDFF1;
            }
            #tabledetails table tr td {
                background: none repeat scroll 0 0;
                border: 1px solid #CEDFF1;
                font-size: 12px;
            }
            #tab{
                width: 100%;
            }
            text{
                border:1px solid #bfbfbf;
            }
            .page_heading{
                margin-top: 30px;
            }
            #header{
                background: url(../images/box-strip.jpg) repeat-x scroll top center #E2E8EC;
                border: 1px solid #BDC9D1;
                margin: 10px 0;
                height:70px;
                text-align:left;
                padding:20px 20px;
            }
            .gobutton {
                background: none repeat scroll 0 0 #316ca8;
                border: 1px solid #BCCFEA;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                width: 50px;
                margin-left: 10px;
                border-radius: 5px;
                cursor: pointer;
            }
            .exportbutton{
                float:right;
                margin-left:10px;
                border-radius: 5px;
                cursor: pointer;
            }
            #export_report{
                padding-top:15px;
            }
            #export{
                width: 70px;
            }
            .container_inner{
                margin:0px;
            }
            #label{
                font-weight: bold;
                margin-left: 5px;
                margin-right: 5px;
                color:#666666;
            }
            #footer{
                height:35px;				
                text-align:center;
            }
            .qualitysave {
                background: url(images/icon_btn_save.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
            }
            .qualitysubmit {
/*                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;*/
                height: 30px;
                padding: 0 10px 0 30px;
                width: 45px;
                border-radius: 5px;
                cursor: not-allowed;
                margin:0px;
            }
            .plainButton{
                background:#316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                margin: 0 0 0 15px;
                padding: 0 10px;
                width: auto;
                float: right;
                cursor:pointer;
            }
            .plainButton{
                float: none;
                width: auto;
                display: inline-block;
                line-height: initial;
                border-radius: 5px;
                text-align: center;
                margin: 10px;
                font-weight:normal;
                height: 25px;
            }
            #content{
                border:1px ;
                background: none;
            }
            p.error{
                background-color: transparent;
                margin: 0px;
                padding: 0px;
                line-height: 20px;
                text-align: center;
                color:red;
            }
            .odd {
                background-color: #FFFFFF;
            }
            .even {
                background-color: #EFF4FB;
            }
            .sucess-message{
                color:green;
            }
            #msg{
                margin-bottom:10px;
            }
            /********custom select box*******/


            .close{
                border:none;
                padding:10px;
                border-radius:10px;
                font-size:30px;
                color:#0000FF;
                background-color:#6699FF;
            }
            .alertboxWrap{
                background-color: rgba(0,0,0,0.3);
                width:100%;
                height:100%;
                position:absolute;
                top:0px;
                left:0px;
                display:none;
            }
            .alertbox{
                z-index:10;
                width:300px;
                padding:0px;
                border:blue 1px solid;
                background-color:#b3c6ff;
                position:absolute;
                top:20%;
                left:40%;
            }
            p{
                margin:0px;
                padding:0px;
            }
            .alertbox p:first-child{
                background: -webkit-linear-gradient(left, #b3ccff,#4D94DB,#CCE0F5); /* For Safari 5.1 to 6.0 */
                background: -o-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Opera 11.1 to 12.0 */
                background: -moz-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Firefox 3.6 to 15 */
                float:left;
                padding:0px 245px 2px 0px;
                float:left;
            }
            .alertbox p:nth-of-type(2){
                padding:30px 10px 20px 10px;
                text-align:center;
            }
            button{
                margin:0px;
                padding:0px;
            }
            #x{
                width:15px;
                float:right;
            }
            .alertbox  button:nth-of-type(3){
                width:55px;
                padding:2px;
                margin:0px 30px 20px 10px;
                float:right;
            }
            .alertbox button:nth-of-type(2){
                width:60px;
                padding:2px;
                margin:0px 10px 20px 30px;
                float:left;
            }
/*            input[type=text] {
                border:1px solid #C4D1E0;
                background: white url('../images/text-bg.gif') repeat;
                font-family: 'Arial';
                width:145px;
            }*/
            .listLink{
                float: right;
                color: #4C83B3;
                font-weight: bold;
                font-size: 12px;
                margin-right: 10px;
            }
            .successMessage{
                text-align: center;
                font-weight: bold;
                color:green;
                font-size: medium;
            }
            .notice_page{
                margin-top: 10px;
            }
            input{
                width: 12px;
            }
            .status_select{
                width:50px;
            }
        </style>
        <script>
            $(document).ready(function(){
                
                $(".successMessage").fadeOut(5000);
                $("#selectall").click(function () {
                    $('.check').attr('checked', this.checked); 
                });
                $(".check").click(function(){		 
                    if($(".check").length == $(".check:checked").length) {

                    } else {
                        $("#selectall").removeAttr("checked");
                    }		 
                });
            });
            
            function enableRow(selectedValue){
                if($("#check_"+selectedValue).is(':checked')){
                    $("#status_select_"+selectedValue).removeAttr("disabled");
                    $("#update_"+selectedValue).removeAttr("disabled");
                    $("#bdm_id_"+selectedValue).removeAttr("disabled");
                    $("#update_"+selectedValue).css("cursor","pointer");
                }else{
                    $("#status_select_"+selectedValue).attr("disabled","disabled");
                    $("#update_"+selectedValue).attr("disabled","disabled");
                    $("#bdm_id_"+selectedValue).attr("disabled","disabled");
                    $("#update_"+selectedValue).css("cursor","not-allowed");
                }
                
            }
            function updateDunningRecord(selectedValue) {
                var status = $("#status_select_"+selectedValue).val();   
                var bdm_id = $("#bdm_id_"+selectedValue).val();
                var customer_id = selectedValue;
                if(customer_id != "" && customer_id != 'null') {
                    if(bdm_id != "" && bdm_id != 'null') {
                        $('#getDunningStatus').attr("action", "updateDunningStatus.htm?customer_id="+customer_id+"&bdm_id="+bdm_id+"&status="+status);
                        document.getDunningStatus.submit();
                    }else{
                        $('#bdm_error_'+selectedValue).html('Please Select BDM');
                    }
                };
            };
            function getDunningStatusList() {
                var status = $("#dunning_status").val();   
                var bdm_id = $("#dunning_bdm_name").val();
                $('#getDunningStatus').attr("action", "dunningStatusList.htm?status="+status+"&bdm_id="+bdm_id);
                document.getDunningStatus.submit();
            };
            function getDunningExport(){
                $('#getDunningStatus').attr("action", "dunningExport.htm");
                document.getDunningStatus.submit();
            }
            
        </script>
    </head>
    <body>
        <div id="content">
            <div class="container_inner">
                <div class="page_heading">
                    Dunning Report Configuration
                </div>
            </div>
            <div id="header">
                <label id="label">Dunning Status : </label>
                <select id="dunning_status" name="dunning_status" style="width:15%" >
                    <option value="">-- Select Status --</option>
                    <option value="y" ${(selected_status == "y")?'selected':''}>Run</option>
                    <option value="n" ${(selected_status == "n")?'selected':''}>Stop</option>
                </select>
                <label id="label">BDM Name : </label>
                <select id="dunning_bdm_name" name="bdm_name">
                    <option value="">-- Select BDM --</option>
                    <c:forEach items="${bdm_list}" var="bdm_list">
                        <option value="${(bdm_list.bdm_id)}"${(bdm_list.bdm_id == selected_bdm_id) ? 'selected':''}>${bdm_list.bdm_name}</option>
                    </c:forEach>
                </select>
                <input type="submit" class="gobutton" id="go" name="submit"  value="Go" onClick="return getDunningStatusList();"/>
                <div id="export_report">
                    <label id="label">Export Dunning Report as on Date: </label>
                    <input type="submit" class="gobutton" id="export" name="export"  value="Export" onClick="return getDunningExport();"/>
                </div>
                
            </div>
            <div class="error"></div>
            <form name="getDunningStatus" id="getDunningStatus" method="post" action="#">
                <div id="tabledetails">
                    <table id="tab">
                        <tr>
                            <th style="width: 10px;"></th>
                            <th style="width: 50px;">Customer Code</th>
                            <th style="width: 150px;">Customer Name</th>
                            <th style="width: 50px;">Dunning Status</th>
                            <th style="width: 100px;">BDM Name</th>
                            <th style="width: 100px;">Action</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(customer_list)!=0}">
                                <c:forEach items="${customer_list}" var="details" varStatus="i">
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even">
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="odd">
                                        </c:otherwise>
                                    </c:choose>
                                        <td>
                                            <input type="checkbox" name="checkbox" class="check" value="${details.customer_id}" id="check_${details.customer_id}" onclick="enableRow(this.value);"/>
                                        </td>
                                        <td>
                                            ${details.customer_code}
                                        </td>
                                        <td>
                                            ${details.customer_name}
                                        </td>
                                        <td>
                                            <select name="dunning_status" disabled class="status_select" id="status_select_${details.customer_id}">
                                                <option value="y" ${(details.dunning_status=="y")?'selected':''}>Run</option>
                                                <option value="n" ${(details.dunning_status=="n")?'selected':''}>Stop</option>
                                            </select> 
                                        </td>
                                        <td>
                                            <p id="bdm_error_${details.customer_id}" class="error"></p>
                                            <select name="bdm_id" id="bdm_id_${details.customer_id}" disabled>
                                                <option value="">-- Select BDM --</option>
                                                <c:forEach items="${bdm_list}" var="bdm_list">
                                                    <option value="${(bdm_list.bdm_id)}"${(bdm_list.bdm_id == details.bdm_id) ? 'selected':''}>${bdm_list.bdm_name}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td>
                                            <input type="text" value="Update" disabled class="qualitysubmit" id="update_${details.customer_id}" name="update" onclick="updateDunningRecord(${details.customer_id})"/>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${fn:length(dunningReport)==0}">
                                    <tr class="odd">
                                        <td colspan="6" style="font-weight: bold;">No data to display</td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </table>
                </div>
            </form>
            
        </div>
    </body>
</html>
