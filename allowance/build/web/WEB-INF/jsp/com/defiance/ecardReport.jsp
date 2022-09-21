<%-- 
    Document   : ecardReport
    Created on : 15 Feb, 2022, 6:56:13 PM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <title>Pick Me Card Report </title>
    </head>
    <style type="text/css">
        
        #loadingDiv img{ border: none; }
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
        .gobutton{
            border: 1px solid #BCCFEA;
            background: none repeat scroll 0 0 #316ca8;
            color: #FFFFFF;
            float: left;
            font-weight: bold;
            height: 32px;
            margin: 0px;
            width: 50px;
        }
        input[type=text] {
            width:85px;
        }
        .tabletools {
            background: url(../images/box-strip.jpg) repeat-x scroll top center #E2E8EC;
            border: 1px solid #BDC9D1;
            margin: 10px 0;
            padding: 10px;
        }
    </style>
    <script type="text/javascript">
        function getExcelReport(){
            $('#reportPage').attr("action", "excelDownload.htm");
            document.reportPage.submit();
        }
        $(document).ready(function(){
            var today = new Date();
            var yesterday = new Date(today);
            yesterday.setDate(today.getDate()-1)
            $("#fromDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                    minDate : '2022-01-01',
                    maxDate: yesterday,
                    onSelect: function () {
                        var date_format = $("#fromDate").val().split('-');
                        var from_date_new = date_format[0]+'-'+date_format[1]+'-'+date_format[2];
                        $("#toDate").datepicker( "destroy" );
                        $("#toDate").datepicker({
                            changeMonth: true,
                            changeYear: true,
                            dateFormat: 'yy-mm-dd',
                            minDate: new Date(from_date_new),
                            maxDate: yesterday
                        });
                        $('#toDate').val('');
                    }
                });
                
                var date_format=$("#fromDate").val().split('-');
                var from_date_new=date_format[2]+'-'+date_format[1]+'-'+date_format[0];
                $("#toDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    dateFormat: 'yy-mm-dd',
                    minDate : new Date(from_date_new),
                    maxDate: yesterday
                });
        });
        
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Pick Me Card Report
                </div>
            </div>
            <div class="tabletools">
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <table style="width: 80%">
                        <tr>
                            <td width="" style="color: #666;"><b>Unit Name :</b> 
                                <select id="unit_name" name="unit_name">
                                    <option value="">All</option>
                                    <c:forEach items="${unit_name}" var="unitList">
                                        <option value="${unitList.last_id}">${unitList.unit_name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td width="" style="color: #666;">
                                <b>From Date:</b> 
                                <input class="calender-field" type="text" placeholder="YYYY-MM-DD" name="from_date" id="fromDate" readonly/>
                            </td>
                            <td width="" style="color: #666;">
                                <b>To Date :</b> 
                                <input class="calender-field" type="text" placeholder="YYYY-MM-DD" name="to_date" id="toDate" readonly />
                            </td>
                            <td>
                                <input class="exportbutton" align="middle" type="button" onclick="return getExcelReport()" value="Export"/>
                            </td>
                        </tr>
                        

                    </table>
                </form>
            </div>
            <div id="datadisplay">

            </div>
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
        </div>
    </body>
</html>

