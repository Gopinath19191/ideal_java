<%-- 
    Document   : reimbursement_report
    Created on : 17 Apr, 2020, 4:54:11 PM
    Author     : 16221
--%>

<%@include file="header.jsp" %>
<html>
    <head>
        <title>Reimbursements Report </title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
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
        span.ui-icon.ui-icon-circle-triangle-w, span.ui-icon.ui-icon-circle-triangle-e {
            text-indent: 0px;
            font-size: 8px;
            width: 22px;
            padding: 3px 0px 0px 0px;
        }
    </style>
    <script type="text/javascript">
        function getExcelReport(){
            var type = $("#reimbursement_type").val();
            var status = $("#status").val();
            var from_date = $("#fromDate").val();
            var to_date = $("#toDate").val();
            $('#reportPage').attr("action", "reimbursementReportXL.htm?type="+type+"&status="+status+"&from_date="+from_date+"&to_date="+to_date);
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
                    minDate : '2015-01-01',
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
                    Reimbursements Report
                </div>
            </div>
            <div class="tabletools">
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <table style="width: 100%">
                        <tr>
                        <td width="" style="color: #666;"><b>Reimbursement Type :</b> 
                            <select id="reimbursement_type" name="reimbursement_type">
                                <option value="">All</option>
                                <c:forEach items="${reimbursementList}" var="reimbursementList" varStatus="monthitr">
                                    <option value="${reimbursementList.reimbursement_id}">${reimbursementList.reimbursement_type}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td width="" style="color: #666;"><b>Approval Status :</b> 
                            <select id="status" name="status">
                                <c:forEach items="${statusList}" var="statusList" >
                                    <option value="${statusList.key}">${statusList.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td >
                            <input class="exportbutton" align="middle" type="button" onclick="return getExcelReport()" value="Export"/>
                        </td>
                        </tr>
                        <tr>
                            <td width="" style="color: #666;">
                                <b>From Date:</b> 
                                <input class="calender-field" type="text" placeholder="  YYYY-MM-DD" name="from_date" id="fromDate" readonly/>
                            </td>
                            <td width="" style="color: #666;">
                                <b>To Date :</b> 
                                <input class="calender-field" type="text" placeholder="YYYY-MM-DD" name="to_date" id="toDate" readonly />
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

