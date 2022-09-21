<%-- 
    Document   : exitReport
    Created on : 25 Feb, 2013, 4:42:56 PM
    Author     : 14578
--%>
<%@include file="header.jsp" %>
<head>
    <title>Exit Triggered Report</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
    <script type="text/javascript">
        $(document).ready(function(){
            $("#exit_triggerDate").datepicker({changeMonth: true, changeYear: true, disabled : true, dateFormat: 'dd-mm-yy' ,maxDate: new Date() });
        });
        function getExcelReport(){
            $('#exitReportForm').attr("action", "exportExitReport.htm");
            document.exitReportForm.submit();
        }
        function getFilterList(){
            $('#exitReportForm').attr("action", "exitReport.htm");
            document.exitReportForm.submit();
            startLoading();
        }
    </script>
    <style>
        #scrollit {
            overflow:scroll;
            height: 501px;
            width: 100%;
        }
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
    </style>
</head>
<body>
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Exit_Report
            </div>
        </div>
        <form id="exitReportForm" name="exitReportForm" method="POST">
            <div class="tabletools" style="width: 977px;">
                <table>
                    <tr>
                        <td> <label for="triggerDate" class=""><b>Employee Released Date >=</b></label></td>
                        <td> <input type="text" id="exit_triggerDate" name="exit_triggerDate" class="textbox_new" value="${exitDate}"/></td>
                        <td><input class="gobutton" type="submit" id="submitBtn" name="submitBtn" onclick="getFilterList();" value="Go"/> </td>
                        <td><input class="exportbutton" type="button" id="exportBtn" name="exportBtn" onclick="getExcelReport();" value="Export"/></td>
                    </tr>
                </table>
            </div>
        </form>
        <div id="datadisplay">
            <div id="scrollit">
                <table>
                    <thead>
                        <tr>
                            <th>Employee Name</th>
                            <th>Contact Number</th>
                            <th>Email ID</th>
                            <th>RM Name</th>
                            <th>Last Billed Date</th>
                            <th>Customer Name</th>
                            <th>Resigned Date</th>
                            <th>Last Working Date</th>
                            <th>BU</th>
                            <th>Band</th>
                            <th>Sub Band</th>
                            <th>Exit Status</th>
                            <th>Leaving Reason</th>
                            <th>Leave Balance</th>
                            <th>Balance Notice Period</th>
                            <th>Notice Waiver</th>
                            <th>RM Clearance</th>
                            <th>Admin Clearance</th>
                            <th>NS Clearance</th>
                            <th>Finance Clearance</th>
                            <th>Exit Survey Clearance</th>
                        </tr>
                    </thead>
                    <c:forEach items="${exitReport}" var="exitReport" varStatus="i">
                        <tbody>
                            <tr>
                                <td>${exitReport.employeeNumber}-${exitReport.employeeName}</td>
                                <td>${exitReport.contactNumber}</td>
                                <td>${exitReport.emailId}</td>
                                <td>${exitReport.rmNumber} - ${exitReport.rmName}</td>
                                <td>${exitReport.lastBilledDate}</td>
                                <td>${exitReport.customerName}</td>
                                <td>${exitReport.resignedDate}</td>
                                <td>${exitReport.lastWorkingDate}</td>
                                <td>${exitReport.practiceGroup}</td>
                                <td>${exitReport.bandName}</td>
                                <td>${exitReport.subBandName}</td>
                                <td>${exitReport.exitStatus}</td>
                                <td>${exitReport.leavingReason}</td>
                                <td>${exitReport.leaveBalance}</td>
                                <td>${exitReport.balanceNoticePeriod}</td>
                                <td>${exitReport.noticeWaiver}</td>
                                <td>${exitReport.rmCleared}</td>
                                <td>${exitReport.adminCleared}</td>
                                <td>${exitReport.nsCleared}</td>
                                <td>${exitReport.financeCleared}</td>
                                <td>${exitReport.exitSurveyClearance}</td>
                            </tr>
                        </tbody>
                    </c:forEach>
                </table></div>
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

