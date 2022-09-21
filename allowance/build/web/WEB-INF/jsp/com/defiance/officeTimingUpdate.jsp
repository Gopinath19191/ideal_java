<%-- 
    Document   : officeTimingUpdate
    Created on : 23 May, 2020, 5:49:11 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Office Timing Selection</title>
        <style>
            table,td{
                border:1px solid #CEDFF1;
                border-collapse:collapse;
                text-align:center;
                color:#666666;
            }
            
            #tabledetails th, #dataDetails th{
                background: url(images/table-header-strip.jpg) repeat-x scroll top center #EFF4FB;
                font-weight: bolder;
                font-size: 11px;
                padding: 5px 10px; 
                padding-top: 3px;
                border: 1px solid #CEDFF1;
            }
            #tabledetails table tr td, #dataDetails table tr td{
                background: none repeat scroll 0 0;
                border: 1px solid #CEDFF1;
                padding: 8px;
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
                height:30px;
                padding: 16px;
                border-collapse:collapse;
                text-align:left;
                padding:20px 20px;
                line-height: 3;
            }
            .gobutton {
                background: none repeat scroll 0 0 #316ca8;
                border: 1px solid #BCCFEA;
                color: #FFFFFF;
                float: right;
                font-weight: bold;
                height: 32px;
                width: 50px;
                margin:0px;
                border-radius: 5px;
                cursor: pointer;
            }
            .exportbutton{
                float:right;
                margin-left:10px;
                border-radius: 5px;
                cursor: pointer;
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
                background: url(images/icon_btn_submit.png) no-repeat scroll 8px 8px #316CA8;
                border: 1px solid #4492BF;
                color: #FFFFFF;
                font-weight: bold;
                height: 30px;
                /*margin: 0 0 0 15px;*/
                padding: 0 10px 0 30px;
                border-radius: 5px;
                cursor: pointer;
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
            }
            .error{
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
            input[type=text] {
                border:1px solid #C4D1E0;
                /*background: white url('../images/text-bg.gif') repeat;*/
                font-family: 'Arial';
                width:100px;
                text-align: center;
            }
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
        </style>
        <script>
            var jsonArr = [];
            $(document).ready(function(){
                $(".successMessage").fadeOut(5000);
                $(".qualitysubmit").click(function(e){
                    e.preventDefault();
                    var timing=$("#role_id").val();
                    if(timing=='' || timing == 'null'){
                        $("#role_id").css("border","1px solid #bfbfbf");
                        $(".error").html('Please select prefered Office Timing');
                        return false;
                    }else{
                        $(".alertboxWrap").show();    
                    }
                    
                });
                	
                		
                $("#submitData").click(function(){
                    $('#saveDetails').attr("action", "updateOfficeTiming.htm");
                    $('.qualitysubmit').hide();
                    $('.exportbutton').hide();
                    $('.gobutton').hide();
                    $(".alertboxWrap").hide();
                });
                $("#cancelSubmit").click(function(){
                    $(".alertboxWrap").hide();
                    return false;
                });

            });

        </script>
    </head>
    <body>
        <div id="content">
            <div class="container_inner">
                <div class="page_heading">
                    Office Timing Selection
                </div>
            </div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:10px;padding-top: 1px;">
                    <ul class="notice_page_ul">
                        <li>Office Timing 08:00 AM to 05:30 PM - default timing no approval required from reporting manager</li>
                        <li>Office Timing 07:30 AM to 05:00 PM - approval required from reporting manager</li>
                        <li>Office Timing 09:00 AM to 06:30 PM - approval required from reporting manager</li>
                        <li>People working in shift's need to follow the respective shift timing </li>
                    </ul>
                </div>
            </div>
            <div class="successMessage">${submit_status}</div>
            <div class="error"></div>
            <form name="saveDetails" id="saveDetails" method="post" action="#">
                <div id="tabledetails">
                    <table id="tab">
                        <tr>
                            <th style="width: 200px;">Select your Office Timing</th>
                            <th style="width: 210px;">
                                <select id="role_id" name="role_id" style="width:200px;">
                                    <option value="">-- Select --</option>
                                    <c:forEach items="${OfficeTiming}" var="OfficeTiming" varStatus="i">
                                        <option value="${(OfficeTiming.role_id)}">${OfficeTiming.role_name}</option>
                                    </c:forEach>
                                </select>
                            </th>
                            <th style="width: 100px;"><input type="submit" value="Submit" class="qualitysubmit" name="submit" /></th>
                        </tr>
                    </table>
                </div>
                <div id="dataDetails">
                    <div class="page_heading" style="margin-bottom:10px;">
                        Previous Timing Details
                    </div>
                    <table id="tab">
                        <tr>
                            <th style="width: 100px;">Preferred Timing</th>
                            <th style="width: 100px;">Submitted On</th>
                            <th style="width: 300px;">Approved By</th>
                            <th style="width: 100px;">Approved On</th>
                            <th style="width: 100px;">Status</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(selectedList)!=0}">
                                <c:forEach items="${selectedList}" var="details" varStatus="i">
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even">
                                            </c:when>
                                            <c:otherwise>
                                            <tr class="odd">
                                            </c:otherwise>
                                        </c:choose>
                                        <td>
                                            ${details.role_name}
                                        </td>
                                        <td>
                                             ${details.cut_off_date}
                                        </td>
                                        <td>
                                            ${details.employee_name}
                                        </td>
                                        <td>
                                            ${details.startDate}
                                        </td>
                                        <td>
                                            ${details.status}
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${fn:length(selectedList)==0}">
                                    <tr class="odd">
                                        <td colspan="5" style="font-weight: bold;">No data to display</td>
                                    </tr>
                                </c:if>
                            </c:otherwise>
                        </c:choose>
                    </table>

                </div>
                <div class="alertboxWrap">
                    <div id="alert" style="width:100%;background: rgb(119, 119, 119);background: rgba(119, 119, 119, 0.5); color:#000;">
                        <div id="alertBoxFocus" style="position: absolute;z-index: 150;bottom: 30%;left:34%;text-align: center;font-size: 14px;background-color: #fff;width: auto;height:auto;padding: 20px;border-radius: 5px;margin-top: 150px;">
                            <p style="padding:10px;margin:10px;">07:30 AM to 05:00 PM and 09:00 AM to 06:30 PM will go for RM approval.<br> Are you sure you want to Submit?<br><br>Click YES to submit, NO to edit the data</p><button class="plainButton" id="submitData">YES</button><button class="plainButton" id="cancelSubmit">NO</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>

