<%-- 
    Document   : dunningReportView
    Created on : 21 Sep, 2020, 3:52:51 PM
    Author     : 16221
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Dunning Report</title>
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
                padding: 5px;
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
                text-align:left;
                padding:20px 20px;
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
                background: none;
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
                background: white url('../images/text-bg.gif') repeat;
                font-family: 'Arial';
                width:145px;
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
            
            function getDunningDate(selectedValue) {
                var mnth = $("#month").val();   
                var year = $("#year").val();
                $("#selected_date").val('');
                if(mnth != "" && year != "") {
                    $.ajax({                   
                        url: './getDunningDate.htm',
                        type: "post",
                        async: false,
                        data: ({month:mnth, year:year}),
                        dataType:'html',
                        success: function(ajaxObj) {
                            $("#selected_date").html('').html(ajaxObj);
                        }
                    });
                };
            };
            function getDunningList() {
                var month=$("#month").val();   
                var year=$("#year").val();
                var date=$("#selected_date").val();
                var type=$("#user_type").val();
                if(year == '' || year == 'null'){
                    $(".error").html('Please select Year');
                    return false;
                }else if(month == '' || month == 'null'){
                    $(".error").html('Please select Month');
                    return false;
                }else{
                    $('#getDunningReport').attr("action", "dunningReportList.htm?month="+month+"&year="+year+"&date="+date+"&user_type="+type);
                    document.getDunningReport.submit();                       
                }
            };
            
        </script>
    </head>
    <body>
        <div id="content">
            <div class="container_inner">
                <div class="page_heading">
                    Dunning Report
                </div>
            </div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:10px;padding-top: 1px;">
                    <ul class="notice_page_ul">
                        <li>Will List the Customer who's report sent on specific day</li>
                    </ul>
                </div>
            </div>
            <div id="header">
                <form name="getDunningReport" id="getDunningReport" method="post" action="#">
                    <input type="hidden" id="user_type" name="user_type" value="${user_type}"/>
                </form>
                <label id="label">Month : </label>
                <select id="month" name="currentMonth" style="width:15%" onchange="getDunningDate(this.value)">
                    <option value="">-- Select Month --</option>
                    <c:forEach items="${monthList}" var="month" varStatus="i">
                        <option value="${(month.key)}" ${(month.key==currentMonth) ? 'selected':''}>${month.value}</option>
                    </c:forEach>
                </select>
                <label id="label">Year : </label>
                <select id="year" name="currentYear" style="width:15%" onchange="getDunningDate(this.value)">
                    <option value="">-- Select Year --</option>
                    <c:forEach items="${yearList}" var="year">
                        <option value="${(year.key)}"${(year.key==currentYear) ? 'selected':''}>${year.value}</option>
                    </c:forEach>
                </select>
                <label id="label">Dunning Report Date: </label>
                <select id="selected_date" name="selected_date" style="width:15%">
                    <option value="">-- Select Date --</option>
                    <c:forEach items="${dunning_date}" var="dunning_date">
                        <option value="${(dunning_date.dunningDate)}"${(dunning_date.dunningDate == selected_date) ? 'selected':''}>${dunning_date.dunningDate}</option>
                    </c:forEach>
                </select>
                <input type="submit" class="gobutton" id="go" name="submit"  value="Go" onClick="return getDunningList();"/>
            </div>
            <div class="error"></div>
            <div id="tabledetails">
                <table id="tab">
                    <tr>
                        <th style="width: 50px;">Customer Code</th>
                        <th style="width: 150px;">Customer Name</th>
                        <th style="width: 50px;">Entity Name</th>
                        <th style="width: 100px;">Dunning File</th>
                        <th style="width: 100px;">Excel File</th>
                        <th style="width: 100px;">Invoice Zip</th>
                    </tr>
                    <c:choose>
                        <c:when test="${fn:length(dunningReport)!=0}">
                            <c:forEach items="${dunningReport}" var="details" varStatus="i">
                                <c:choose>
                                    <c:when test="${(i.count) % 2 == 0}">
                                        <tr class="even">
                                    </c:when>
                                    <c:otherwise>
                                        <tr class="odd">
                                    </c:otherwise>
                                </c:choose>
                                    <td>
                                        ${details.customer_code}
                                    </td>
                                    <td>
                                        ${details.customer_name}
                                    </td>
                                    <td>
                                        ${details.entity_name}
                                    </td>
                                    <td>
                                        <a href="dunningFileDownload.htm?fileName=${details.pdf_file_name}&fileType=pdf&dunningFolder=${details.dunningDate}" target="_blank" name="file">Dunning File</a>
                                    </td>
                                    <td>
                                        <a href="dunningFileDownload.htm?fileName=${details.excel_file_name}&fileType=xlsx&dunningFolder=${details.dunningDate}" target="_blank" name="file">Excel Report</a>
                                    </td>
                                    <td>
                                        <a href="dunningFileDownload.htm?fileName=${details.zip_folder_name}&fileType=zip&dunningFolder=${details.dunningDate}" target="_blank" name="file">Invoice Zip</a>
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
        </div>
    </body>
</html>
