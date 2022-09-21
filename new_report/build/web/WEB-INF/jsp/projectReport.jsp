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
        <title>Project Report</title>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
    </head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
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
        function setprojectId(){
            if($('#project_search').val()==''){
                $('#project_id').val("");
            }
        }

        function getData(){
            var str = document.getElementById("project_search").value;
            if(str.trim() != ''){
                $('#projectReportPage').attr("action", "projectReport.htm");
                document.projectReportPage.submit();
                startLoading();
            }else{
                alert("Please enter valid project code/name");
                document.getElementById("project_search").value ="";
            }
        }
        function getProjectReportXL(){
            var str = document.getElementById("project_search").value;
            var index = str.indexOf("-");
            if(index != -1){
                $('#projectReportPage').attr("action", "projectReportExport.htm");
                document.projectReportPage.submit();
            }else{
                alert("Please enter valid project code/name");

            }
        }
    </script>
</head>
<body>
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 250px; left: 425px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Project Report
            </div>
        </div>
        <div class="tabletools">
            <form action="#" name="projectReportPage" method="post" id="projectReportPage" onsubmit="javascript:getData()">
                <table id="searchForm" >
                    <tbody>
                        <tr>
                            <td valign="top">
                                <label for="projectCodeName" style="width: 146px;"><b>Project Code/Name: </b></label>
                            </td>
                            <td width="250px">
                                <%
                                            java.text.NumberFormat nf = java.text.NumberFormat.getInstance();
                                            nf.setMaximumFractionDigits(2);
                                            nf.setGroupingUsed(false);
                                            double value, decimal;
                                            java.util.StringTokenizer st = null;
                                            java.text.SimpleDateFormat sdf = null;
                                            java.util.Date dt = null;
                                            String projectString = (String) request.getAttribute("projectString");
                                            String projectUniqueId = (String) request.getAttribute("projectUniqueId");
                                            if (projectString == null) {
                                                projectString = "Search by Project Id or Project Name";
                                                projectUniqueId = " ";
                                            }

                                %>
<!--                                <input type="text" id="project_search" name="project_search" style="width:300px;color: #666666;"  onfocus="if(this.value=='Search by Project Id or Project Name') this.value='';" value="<%=projectString%>"/>-->
                                <input type="text" id="project_search" name="project_search" style="width:342px;color: #717171;" value="<%=projectString%>" onfocus="if(this.value=='Search by Project Id or Project Name') this.value='';" onblur="if(this.value=='') this.value='Search by Project Id or Project Name';" onchange="setprojectId();" >
                                <input type="hidden" id="project_id" name="project_id" value="<%=projectUniqueId%>"/>
                                <script>
                                    $(document).ready(function(){
                                        $("#project_search").autocomplete("project_search.htm");
                                        $("#project_search").result(function(event, data, formatted) {
                                            //alert("::::");
                                            if (data) {
                                                //  alert(data)
                                                $("#project_id").val(data[1]);
                                            }
                                        });
                                    });
                                </script>
                            </td>
                            <td valign="top">
                                <b> Customer Name</b>
                            </td>
                            <td valign="top">
                                <%
                                            String customerName = (String) request.getAttribute("customer_name");
                                            if (customerName != null) {
                                                out.print(customerName);
                                            }
                                %>
                            </td>
                            <td valign="top">
                                <b> Approval Status</b>
                            </td>
                            <td valign="top">
                                <%
                                            String projectApproveStatus = (String) request.getAttribute("project_approve_status");
                                            if (projectApproveStatus != null) {
                                                out.print(projectApproveStatus);
                                            }
                                %>
                            </td>
                        </tr>
                        <tr><td colspan="6" height="12px"></td></tr>
                        <tr>
                            <td>
                                <b>Project Manager</b>
                            </td>
                            <td width="250px">
                                <%
                                            String name = (String) request.getAttribute("project_manager_name");
                                            if (name != null) {
                                                out.print(name);
                                            }
                                %>
                            </td>
                            <td nowrap>
                                <b>Project Start Date</b>
                            </td>
                            <td width="150px" >
                                <%
                                            String startDate = (String) request.getAttribute("project_start_date");
                                            if (startDate != null) {
                                                try {
                                                    sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                                    dt = sdf.parse(startDate);
                                                    sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                                                    out.print(sdf.format(dt));
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    ;
                                                }
                                            }
                                %>
                            </td>
                            <td>
                                <b>Project End Date</b>
                            </td>
                            <td>
                                <%
                                            String endDate = (String) request.getAttribute("project_end_date");
                                            if (endDate != null) {
                                                try {
                                                    sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                                    dt = sdf.parse(endDate);
                                                    sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                                                    out.print(sdf.format(dt));
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                    ;
                                                }
                                            }
                                %>
                            </td>
                        </tr>
                        <tr><td colspan="6" height="12px"></td></tr>
                        <tr>
                            <td>
                                <b>Project Status</b>
                            </td>
                            <td>
                                <%
                                            String status = (String) request.getAttribute("configuration_value");
                                            if (status != null) {
                                                out.print(status);
                                            }
                                %>
                            </td>
                            <td nowrap>
                                <b>Total Efforts</b>
                            </td>
                            <td>
                                <%
                                            String total_efforts = (String) request.getAttribute("total_effort");
                                            if (total_efforts != null) {
                                                value = java.lang.Double.valueOf(total_efforts);
                                                st = new java.util.StringTokenizer(total_efforts, ".");
                                                if (st.countTokens() > 1) {
                                                    st.nextToken();
                                                    decimal = Double.valueOf(st.nextToken());
                                                    if (decimal != 0) {
                                                        nf.setMinimumFractionDigits(2);
                                                    } else {
                                                        nf.setMinimumFractionDigits(0);
                                                    }
                                                }
                                                out.print(nf.format(value));
                                            }
                                %>
                            </td>
                            <td>
                                <b>Accrued So Far</b>
                            </td>
                            <td>
                                <%
                                            String accrued_efforts = (String) request.getAttribute("accrued_effort");
                                            if (accrued_efforts != null) {
                                                value = java.lang.Double.valueOf(accrued_efforts);
                                                st = new java.util.StringTokenizer(accrued_efforts, ".");
                                                if (st.countTokens() > 1) {
                                                    st.nextToken();
                                                    decimal = Double.valueOf(st.nextToken());
                                                    if (decimal != 0) {
                                                        nf.setMinimumFractionDigits(2);
                                                    } else {
                                                        nf.setMinimumFractionDigits(0);
                                                    }
                                                }
                                                out.print(nf.format(value));
                                            }
                                            String efforts_uom = (String) request.getAttribute("efforts_uom");
                                            System.out.println("efforts_uom     "+efforts_uom);
                                            if (efforts_uom != null && efforts_uom != "") {
                                                out.print("   (" + efforts_uom + ")");
                                            }
                                            else {
                                                out.print(" ");
                                                }
                                %>
                            </td>
                        </tr>
                        <tr><td colspan="6" height="12px"></td></tr>
                        <tr>
                            <td nowrap>
                                <b>Currency</b>
                            </td>
                            <td width="250px">
                                <%
                                            String poCurrency = (String) request.getAttribute("currency_code");
                                            if (poCurrency != null) {
                                                out.print(poCurrency);
                                            }
                                %>
                            </td>
                            <td nowrap>
                                <b>PO Value</b>
                            </td>
                            <td width="250px">
                                <%
                                            String poValue = (String) request.getAttribute("poValueStr");
                                            if (poValue != null) {
                                                out.print(poValue);
                                            }
                                %>
                            </td>
                            <td nowrap>
                                <b>Total Accrued Amount</b>
                            </td>
                            <td width="150px" >
                                <%
                                            String billableAmtStr = (String) request.getAttribute("billableAmtStr");
                                            if (billableAmtStr != null) {
                                                out.print(billableAmtStr);
                                            }
                                %>
                            </td>
                            <td>
                            </td>
                            <td>
                            </td>
                        </tr>
                        <tr><td colspan="6" height="12px"></td></tr>
                </table></form>
            <table style="font-size: 10px;" width="10%">
                <tbody align="left">
                    <tr>

                        <td align="left"> <input class="gobutton"  onclick="getData()" style="height: 32px;" type="submit" value="Go"/></td>
                        <td align="left"> <input class="exportbutton"  type="button" style="margin-left: 20px;" onclick="getProjectReportXL()" value="Export"/></td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div id="datadisplay">
            <table style="font-size: 10px;" width="100%">
                <tbody>
                <th align="center">Associate Id</th>
                <th align="center">Associate Name</th>
                <th align="center">Allocation Start Date</th>
                <th align="center">Allocation End Date</th>
                <th align="center">Role</th>
                <th align="center">Allocated Hours</th>
                <th align="center">Billed&nbsp;<%=efforts_uom%></th>
                </tbody>
                <tbody>
                    <%
                                java.util.List list = (java.util.List) request.getAttribute("associateList");
                                if (list != null) {
                                    java.util.Iterator<com.defiance.ideal.reports.dto.ProjectDetails> itr = list.iterator();
                                    com.defiance.ideal.reports.dto.ProjectDetails pd = null;
                                    try {
                                        if (itr.hasNext() == true) {
                                            while (itr.hasNext()) {
                                                pd = itr.next();
                                                out.println("<tr>");
                                                out.println("<td align =\"center\">" + pd.getEmployee_number() + "</td>");
                                                out.println("<td align =\"center\">" + pd.getFirst_name() + " " + pd.getLast_name() + "</td>");
                                                sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                                dt = sdf.parse(pd.getStart_date());
                                                sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                                                out.println("<td align =\"center\">" + sdf.format(dt) + "</td>");
                                                sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                                                dt = sdf.parse(pd.getEnd_date());
                                                sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
                                                out.println("<td align =\"center\">" + sdf.format(dt) + "</td>");
                                                out.println("<td align =\"center\">" + pd.getRole_description() + "</td>");
                                                if (pd.getBilling_hours() != null) {
                                                    value = java.lang.Double.valueOf(pd.getBilling_hours());
                                                    st = new java.util.StringTokenizer(pd.getBilling_hours(), ".");
                                                    if (st.countTokens() > 1) {
                                                        st.nextToken();
                                                        decimal = Double.valueOf(st.nextToken());
                                                        if (decimal != 0) {
                                                            nf.setMinimumFractionDigits(2);
                                                        } else {
                                                            nf.setMinimumFractionDigits(0);
                                                        }
                                                    }
                                                    out.println("<td align =\"center\">" + nf.format(value) + "</td>");
                                                }
                                                if (pd.getAllocated_hours() != null) {
                                                    value = java.lang.Double.valueOf(pd.getAllocated_hours());
                                                    st = new java.util.StringTokenizer(pd.getAllocated_hours(), ".");
                                                    if (st.countTokens() > 1) {
                                                        st.nextToken();
                                                        decimal = Double.valueOf(st.nextToken());
                                                        if (decimal != 0) {
                                                            nf.setMinimumFractionDigits(2);
                                                        } else {
                                                            nf.setMinimumFractionDigits(0);
                                                        }
                                                    }
                                                    out.println("<td align =\"center\">" + nf.format(value) + "</td>");
                                                } else if (pd.getAllocated_hours() == null) {
                                                     out.println("<td align =\"center\">" +" " + "</td>");
                                                }
                                                out.println("</tr>");
                                            }
                                        } else {
                                            out.println("<tr>");
                                            out.println("<td colspan =\"7\" align =\"center\">" + "No data to display" + "</td>");
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
