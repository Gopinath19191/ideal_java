<%-- 
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Travel Report</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />        
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
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
    </style>

    <script type="text/javascript">
        $(document).ready(function() {
            $("#from_date").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'dd-mm-yy'});
            $("#to_date").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'dd-mm-yy'});
            $("#project_search").autocomplete("travelReport_search.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });

            var value = document.getElementById("sbu").value;
            var sbu = value;
            if (value === "All" || value === '')
            {
                //sbu = "9,10,11";
                sbu = "2,5";
            }

            $.ajax(
                    {
                        url: "getSubSbuList.htm?id=" + sbu,
                        dataType: "html",
                        success: function(data) {
                            $('#subSbu').length = 0;
                            $('#subSbu').html(data);
                        }
                    });


        });

        function getSubSbu(value)
        {
            var sbuValue = document.getElementById("sbu").value;
            if (sbuValue === "All" || sbuValue === '')
            {
                //sbuValue = "9,10,11";
                sbuValue = "2,5";
                
            }

            $.ajax(
                    {
                        url: "getSubSbuList.htm?id=" + sbuValue,
                        dataType: "html",
                        success: function(data) {
                            $('#subSbu').length = 0;
                            $('#subSbu').html(data);
                        }
                    });
        }
        function getData() {

            var isValidInput = doValidate();
            var from_date = $('#from_date').val();
            var dateAr = from_date.split('-');
            var fromDate = dateAr[2] + '-' + dateAr[1] + '-' + dateAr[0];
//            alert(fromDate);
            var to_date = $('#to_date').val();
            var dateAr1 = to_date.split('-');
            var toDate = dateAr1[2] + '-' + dateAr1[1] + '-' + dateAr1[0];
//            alert(toDate);
//            $('#from_date').valueOf(fromDate);
//            $('#to_date').valueOf(toDate);
            if (isValidInput === 1) {
                $('#travelReportPage').attr("action", "travelReport.htm?fromDate=" + fromDate + "&toDate=" + toDate);
                document.travelReportPage.submit();
                startLoading();
            } else {
                alert("Please enter Date Range");
            }
        }

        function doValidate() {
            var fromDate = document.getElementById("from_date").value;
            var toDate = document.getElementById("to_date").value;
            var validData;

            if ((fromDate === null || fromDate === '') || (toDate === null || toDate === '')) {
                validData = 0;
            } else {
                validData = 1;
            }
            return validData;
        }

        function travelReportExport() {
            var isValidInput = doValidate();
            var from_date = $('#from_date').val();
            var dateAr = from_date.split('-');
            var fromDate = dateAr[2] + '-' + dateAr[1] + '-' + dateAr[0];
//            alert(fromDate);
            var to_date = $('#to_date').val();
            var dateAr1 = to_date.split('-');
            var toDate = dateAr1[2] + '-' + dateAr1[1] + '-' + dateAr1[0];
//            alert(toDate);
//            $('#from_date').valueOf(fromDate);
//            $('#to_date').valueOf(toDate);
            if (isValidInput === 1) {
                $('#travelReportPage').attr("action", "travelReportXL.htm?fromDate=" + fromDate + "&toDate=" + toDate);
                document.travelReportPage.submit();
                //startLoading();
            } else {
                alert("Please enter SBU and Date");
            }
        }

        $(document).ajaxComplete(function() {
            var subSbuValue = document.getElementById("subSbu");
            var selectedSubSbu = document.getElementById("selectedSubSbu").value;
            subSbuValue.value = selectedSubSbu;
        });


    </script>
</head>
<body onload="doLoad()">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 250px; left: 425px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Travel Report In Ideal Report
            </div>
        </div>
        <div class="tabletools">
            <form action="#" name="travelReportPage" method="post" id="travelReportPage" onsubmit="javascript:getData()">
                <%
                    String selectedSubSbu = (String) request.getAttribute("selectedSubSbu");
                    System.out.println("selectedSubSbu:in jsp:" + selectedSubSbu);
                %>
                <input type="hidden" id="selectedSubSbu" name="selectedSubSbu" value="<%=selectedSubSbu%>"/>
                <table id="searchForm" >
                    <tbody>
                        <tr>
                            <td width="130px">
                                <label for="sbu" style="width: 70px;"><b>SBU: </b></label>

                            </td>
                            <td align="left" width="170px" >
                                <select id="sbu" name="sbu" class="textbox_new" onchange="getSubSbu(this.value);">
                                    <option value="" >-- All --</option>
                                    <%
                                        java.util.List<com.defiance.ideal.reports.dto.TravelReportDetails> sbuList = (java.util.List) request.getAttribute("sbuList");
                                        java.util.Iterator<com.defiance.ideal.reports.dto.TravelReportDetails> sbuItr = sbuList.iterator();
                                        com.defiance.ideal.reports.dto.TravelReportDetails td = null;
                                        String selectedSbuId = (String) request.getAttribute("selectedSbuId");
                                        while (sbuItr.hasNext()) {
                                            td = sbuItr.next();
                                            if (td.getSbu_id().equalsIgnoreCase(selectedSbuId)) {
                                                out.println("<option value='" + td.getSbu_id() + "' selected>" + td.getName() + "</option>");
                                            } else {
                                                out.println("<option value='" + td.getSbu_id() + "'>" + td.getName() + "</option>");
                                            }
                                        }
                                    %>
                                </select>
                            </td>
                            <td>
                                <label for="subSbu" style="width: 70px;">Sub SBU: </label>
                            </td>
                            <td align="left" width="170px">
                                <select id="subSbu" name="subSbu" class="textbox_new" >
                                </select>
                            </td>
                        </tr>
                        <tr><td height="10px"></td></tr>
                        <tr>
                            <td width="130px">
                                <label for="fromDate" style="width: 70px;"><b>From Date :</b></label>
                            </td>
                            <td align="left" width="170px" >
                                <%
                                    String fromDate = (String) request.getAttribute("fromDate");
                                    if (fromDate == null) {
                                        fromDate = "";
                                    } else {
                                        String[] dateAr = fromDate.split("-");
                                        fromDate = dateAr[2] + '-' + dateAr[1] + '-' + dateAr[0];
                                    }
                                %>
                                <input type="text" id="from_date" name="from_date"   readonly  value="<%=fromDate%>"/>
                            </td>
                            <td>
                                <label for="toDate" style="width: 70px;"><b>To Date :</b></label>
                            </td>
                            <td align="left" width="170px">
                                <%
                                    String toDate = (String) request.getAttribute("toDate");
                                    if (toDate == null) {
                                        toDate = "";
                                    } else {
                                        String[] dateAr1 = toDate.split("-");
                                        toDate = dateAr1[2] + '-' + dateAr1[1] + '-' + dateAr1[0];
                                    }
                                %>
                                <input type="text" id="to_date" name="to_date"  readonly value="<%=toDate%>"/>
                            </td>
                        </tr>
                        <tr><td height="10px"></td></tr>
                        <tr>
                            <td valign="top">
                                <label for="projectCodeName" style="width: 70px;">Project Code/Name: </label>
                            </td>
                            <td width="450px" colspan="3">
                                <%
                                    String projectString = (String) request.getAttribute("projectString");
                                    if (projectString == null) {
                                        projectString = "";
                                    }
                                %>
                                <input type="text" id="project_search" name="project_search" style="width:300px;color: #666666;"  onfocus="if (this.value == 'Search by Project Id or Project Name')
                this.value = '';" onblur="if (this.value == '')
                this.value = 'Search by Project Id or Project Name';"  value="<%=projectString%>"/>
                            </td>

                            <td>
                                <label for="statusValue" style="width: 70px;">Status:</label>
                            </td>
                            <td>
                                <select id="status_search" name ="status_search" >
                                    <option value="all">select any status</option>
                                    <option value="b" ${status eq 'b' ? 'selected="selected"':''}>Pending with BUH</option>
                                    <option value="r" ${status eq 'r' ? 'selected="selected"':''}>Pending with RM</option>
                                    <option value="f" ${status eq 'f' ? 'selected="selected"':''}>Pending with Finance</option>
                                    <option value="o" ${status eq 'o' ? 'selected="selected"':''}>Rejected</option>
                                    <option value="x" ${status eq 'x' ? 'selected="selected"':''}>Cancelled</option>
                                    <option value="s" ${status eq 's' ? 'selected="selected"':''}>Saved</option>
                                    <option value="c" ${status eq 'c' ? 'selected="selected"':''}>Pending with CFO</option>
                                    <option value="m" ${status eq 'm' ? 'selected="selected"':''}>Pending with MD</option>
                                    <option value="a" ${status eq 'a' ? 'selected="selected"':''}>Pending with Admin</option>
                                    <option value="t" ${status eq 't' ? 'selected="selected"':''}>Pending with Treasury</option>
                                    <option value="tc" ${status eq 'tc' ? 'selected="selected"':''}>Ticket Booked</option>
                                    <option value="mn" ${status eq 'mn' ? 'selected="selected"':''}>Money Deposited</option>
                                </select>
                            </td>
                        </tr>
                </table></form>
            <br>
            <table style="font-size: 10px;" width="10%">
                <tbody align="left">
                    <tr>
                        <td align="left"><input class="gobutton" type="button" style="width:70px; height: 32px" onclick="getData()"  value="Go"/></td>
                        <td align="left"> <input class="exportbutton"  type="button" onclick="travelReportExport()" value="Export"/></td>
                    </tr>
                </tbody>
            </table>

        </div>
        <br>
        <div id="datadisplay">
            <table style="font-size: 10px;" width="100%">
                <tbody>
                <th align="center">TP Reference No</th>
                <th align="center">Employee Number</th>
                <th align="center">Employee Name</th>
                <th align="center">Travel Type</th>
                <th align="center">SBU</th>
                <th align="center">Travel Request Date</th>
                <th align="center">Travel Start Date</th>
                <th align="center">Travel End Date</th>
                <th align="center">Project Travel</th>
                <th align="center">Client Reimbursement</th>
                <th align="center">Customer Name</th>
                <th align="center">Project Id</th>
                <th align="center">Project Name</th>
                <!--                <th align="center">TP Approved Date </th>-->
                <th align="center">Travel From</th>
                <th align="center">Travel To</th>
                <!--                <th align="center">Travel Purpose</th>-->
                <th align="center">Status</th>
                <th align="center">RM Action Date</th>
                <th align="center">BUH Action Date</th>
                <th align="center">MD Action Date</th>
                <th align="center">Finance Action Date</th>
                <th align="center">CFO Action Date</th>
                <th align="center">Admin Action Date</th>
                <th align="center">Treasury Action Date</th>
                </tbody>
                <tbody>
                    <%
                        java.util.List list = (java.util.List) request.getAttribute("travelReportList");
                        if (list != null) {
                            java.util.Iterator<com.defiance.ideal.reports.dto.TravelReportDetails> itr = list.iterator();
                            com.defiance.ideal.reports.dto.TravelReportDetails tr = null;
                            String loopProjectCode = null, loopProjectName = null;
                            try {
                                while (itr.hasNext()) {
                                    tr = itr.next();
                                    out.println("<tr>");
                                    out.println("<td align =\"center\">" + tr.getTravel_requestid() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getEmployee_number() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getFirst_name() + " " + tr.getLast_name() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getTravelType() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getSbu_name() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getRequest_date() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getTravel_startdate() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getTravel_enddate() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getProjectTravel() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getClientReimbursement() + "</td>");
                                    out.println("<td align =\"center\">" + tr.getCustomerName() + "</td>");
                                    loopProjectCode = tr.getProject_code();
                                    if (loopProjectCode == null) {
                                        loopProjectCode = "";
                                    }
                                    out.println("<td align =\"center\">" + loopProjectCode + "</td>");
                                    loopProjectName = tr.getProject_name();
                                    if (loopProjectName == null) {
                                        loopProjectName = "";
                                    }
                                    out.println("<td align =\"center\">" + loopProjectName + "</td>");
//                                            out.println("<td align =\"center\">" + tr.getApproved_date() + "</td>");
                                    if (tr.getDestination_from() != null) {
                                        out.println("<td align =\"center\">" + tr.getDestination_from() + "</td>");
                                    } else {
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    if (tr.getDestination_to() != null) {
                                        out.println("<td align =\"center\">" + tr.getDestination_to() + "</td>");
                                    } else {
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    out.println("<td align =\"center\">" + tr.getStatus() + "</td>");
                                    if(tr.getRmActionDate() != null){
                                        out.println("<td align =\"center\">"+tr.getRmActionDate()+"</td>");
                                    }else{
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    if(tr.getBuhActionDate() != null){
                                        out.println("<td align =\"center\">"+tr.getBuhActionDate()+"</td>");
                                    }else{
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    if(tr.getMdActionDate() != null){
                                        out.println("<td align =\"center\">"+tr.getMdActionDate()+"</td>");
                                    }else{
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    if(tr.getFinanceActionDate() != null){
                                        out.println("<td align =\"center\">"+tr.getFinanceActionDate()+"</td>");
                                    }else{
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    if(tr.getCfoActionDate() != null){
                                        out.println("<td align =\"center\">"+tr.getCfoActionDate()+"</td>");
                                    }else{
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    if(tr.getAdminActionDate() != null){
                                        out.println("<td align =\"center\">"+tr.getAdminActionDate()+"</td>");
                                    }else{
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    if(tr.getTreasuryActionDate() != null){
                                        out.println("<td align =\"center\">"+tr.getTreasuryActionDate()+"</td>");
                                    }else{
                                        out.println("<td align =\"center\">" + " -- " + "</td>");
                                    }
                                    out.println("</tr>");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    %>

                </tbody>
            </table>
        </div>

        <script type="text/javascript">
        var loadingDivObj = (document.all);
        var ns4 = document.layers;
        var ns6 = document.getElementById && !document.all;
        var ie4 = document.all;
        if (ns4) {
            loadingDivObj = document.loadingDiv;
        } else if (ns6) {
            loadingDivObj = document.getElementById("loadingDiv").style;
        } else if (ie4) {
            loadingDivObj = document.all.loadingDiv.style;
        }

        stopLoading();

        function stopLoading() {
            if (ns4) {
                loadingDivObj.visibility = "hidden";
            }
            else if (ns6 || ie4)
                loadingDivObj.display = "none";
        }

        function startLoading() {
            if (ns4) {
                loadingDivObj.visibility = "visible";
            }
            else if (ns6 || ie4)
                loadingDivObj.display = "block";
        }
        </script>
    </div>
</body>
</html>
