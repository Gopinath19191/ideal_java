<%--
    Document   : apply
    Created on : Mar 13, 2012, 12:12:20 PM
    Author     : 15065
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
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
                float: none;
                font-weight: bold;
                height: 32px;
                width: 50px;
                margin-left:25px;
                border-radius: 5px;
                cursor: pointer;
            }
            .exportbutton{
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
                margin: 0 0 0 15px;
                padding: 0 10px 0 30px;
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
                padding:10px 10px 0px 10px;
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


            .close
            {
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
            .alertbox
            {
                z-index:10;
                width:300px;
                padding:0px;
                border:blue 1px solid;
                background-color:#b3c6ff;
                position:absolute;
                top:20%;
                left:40%;
            }
            p
            {
                margin:0px;
                padding:0px;
            }
            .alertbox p:first-child
            {
                background: -webkit-linear-gradient(left, #b3ccff,#4D94DB,#CCE0F5); /* For Safari 5.1 to 6.0 */
                background: -o-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Opera 11.1 to 12.0 */
                background: -moz-linear-gradient(left,#b3ccff,#4D94DB,#CCE0F5); /* For Firefox 3.6 to 15 */
                float:left;
                padding:0px 245px 2px 0px;
                float:left;
            }
            .alertbox p:nth-of-type(2)
            {
                padding:30px 10px 20px 10px;
                text-align:center;
            }
            button
            {
                margin:0px;
                padding:0px;
            }
            #x
            {
                width:15px;
                float:right;
            }
            .alertbox  button:nth-of-type(3)
            {
                width:55px;
                padding:2px;
                margin:0px 30px 20px 10px;
                float:right;
            }
            .alertbox button:nth-of-type(2)
            {
                width:60px;
                padding:2px;
                margin:0px 10px 20px 30px;
                float:left;
            }
            input[type=text] {
                border:1px solid #C4D1E0;
                background: white url('../images/text-bg.gif') repeat;
                font-family: 'Arial';
                width:25px;
                text-align: center;
            }
            .listLink{
                float: right;
                color: #4C83B3;
                font-weight: bold;
                font-size: 12px;
                margin-right: 10px;
            }
            .notice_page{
                margin:10px 0px;
            }
        </style>


        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.validate.js"></script>
        <script type="text/javascript">
            function getEmployeeList(){
                var yr=$("#selYear").val();
                
                if(yr==''){
                    $(".error").html('Please select Year');
                    return false;
                }else{
                    $('#getDetails').attr("action", "employeeViewAllowance.htm");
                    document.getDetails.submit();                       
                }

            };
            
            function getExcelReport(){
                var yr=$("#selYear").val();
                if(yr==''){
                    $(".error").html('Please select Year');
                    return false;
                }else{
                    $('#getDetails').attr("action", "getExcelReport.htm");
                    document.getDetails.submit();                       
                }

            };

        </script>
    </head>
    <body>
        <div align="center"><font color="green" size="3">${success_msg}</font></div>
        <div class="container_inner">
            <div class="page_heading">
                Allowance Report
            </div>
            <div class="notice_page">
                <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/images/icon_alert.png" /></div>
                <div style="padding-left:20px;">
                    <ul class="notice_page_ul">
                        <li>Allowance are automatically applied on approval of timesheet</li>
                        <li>Please Fill your Timesheet without fail</li>
                        <li>Please fill the Timesheet for every month before last date of the same</li>
                    </ul>
                </div>
            </div>
        </div>        
       <div id="content">
            <div id="header">
                <form name="getDetails" id="getDetails" method="post" action="#">
                    <label id="label">Year: </label>
                    <select name="selYear" id="selYear" size="1" style="width:10%">
                        <option align="center" value="">-- Year --</option>
                        <c:forEach items="${yearsList}" var="yearsList" varStatus="sbuitr">
                            <option value="${yearsList.key}"${(filterData.year == yearsList.value) ? 'selected':''}>${yearsList.value}</option>
                        </c:forEach>
                    </select>    
                    
                    <input type="hidden" name="processed" id="processed" value="1"/>
                    <input type="submit" class="gobutton" id="go" name="submit"  value="Go" onClick="return getEmployeeList();"/>
                    <!--<input type="submit" class="exportbutton" id="export" name="export"  value="Export" onClick="return getExcelReport();"/>-->
                </form>
            </div>
            <div class="error"></div>
            <form name="saveDetails" id="saveDetails" method="post" action="#">
                <div id="tabledetails">
                    <table id="tab">
                        <tr>
                            <th rowspan="2" style="width: 85px;">Month</th>
                            <th colspan="4" style="width: 85px;">HTL Location & Customer Location <30KM from HTL Location</th>
                            <th colspan="4" style="width: 85px;">Customer Location >30KM from HTL Location</th>
                            <th rowspan="2" style="width: 70px;">Holiday/ Week End Details</th>
                            <th colspan="3" style="width: 50px;">Transport Details</th>
                            <th rowspan="2" style="width: 40px;">Hardship Allowance</th>
                            <th rowspan="2" style="width: 40px;">Shift Allowance</th>
                            <th rowspan="2" style="width: 40px;">Holiday Allowance</th>
                            <th rowspan="2" style="width: 40px;">Transport Allowance</th>
                            <th rowspan="2">Total Amount</th>
                            <th rowspan="2">Remarks</th>
                        </tr>
                        <tr>
                            <th style="width: 40px;">General Shift</th>
                            <th style="width: 40px;">First Shift</th>
                            <th style="width: 40px;">Second Shift</th>
                            <th style="width: 40px;">Third Shift</th>
                            <th style="width: 40px;">General Shift</th>
                            <th style="width: 40px;">First Shift</th>
                            <th style="width: 40px;">Second Shift</th>
                            <th style="width: 40px;">Third Shift</th>
                            <th style="width: 40px;">One Way</th>
                            <th style="width: 40px;">Two Way</th>
                            <th style="width: 40px;">No Cab</th>
                        </tr>
                        <c:choose>
                            <c:when test="${fn:length(details)!=0}">
                                <input type="hidden" name="selYear" value="${filterData.year}"/>
                                <c:forEach items="${details}" var="details" varStatus="i">
                                    <c:choose>
                                        <c:when test="${(i.count) % 2 == 0}">
                                            <tr class="even">
                                        </c:when>
                                        <c:otherwise>
                                            <tr class="odd">
                                        </c:otherwise>
                                    </c:choose>
                                        <td>
                                            ${details.month}
                                        </td>
                                        <td>
                                            ${details.company_general}
                                        </td>
                                        <td>
                                            ${details.company_shift_I}
                                        </td>
                                        <td>
                                            ${details.company_shift_II}
                                        </td>
                                        <td>
                                            ${details.company_shift_III}
                                        </td>
                                        <td>
                                            ${details.cust_general}
                                        </td>
                                        <td>
                                            ${details.cust_shift_I}
                                        </td>
                                        <td>
                                            ${details.cust_shift_II}
                                        </td>
                                        <td>
                                            ${details.cust_shift_III}
                                        </td>
                                        <td>
                                            ${details.weekend_holidays_entered}
                                        </td>
                                        <td>
                                            ${details.one_way}
                                        </td>
                                        <td>
                                            ${details.two_way}
                                        </td>
                                        <td>
                                            ${details.no_cab}
                                        </td>
                                        <td>
                                            ${details.hardship_amount}
                                        </td>
                                        <td>
                                            ${details.shift_amount}
                                        </td>
                                        <td>
                                            ${details.holiday_amount}
                                        </td>
                                        <td>
                                            ${details.transport_amount}
                                        </td>
                                        <td>
                                            ${details.total_amount}
                                        </td>
                                        <td>
                                            <textarea rows="1" class="remarks_${details.employee_id}" id="remarks_${details.employee_id}" name="remarks" cols="7" 
                                                      style="max-height: 50px; max-width: 200px;" readonly value="${details.remarks}">${details.remarks}</textarea>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <c:if test="${fn:length(details)==0}">
                                    <tr class="odd">
                                        <td colspan="19" style="font-weight: bold;">No data to display</td>
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