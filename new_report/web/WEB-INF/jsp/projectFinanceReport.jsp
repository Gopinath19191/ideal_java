<%-- 
    Document   : projectReport
    Created on : Aug 30, 2012, 3:51:57 PM
    Author     : 15261
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Finance Report</title>
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
            $("#from_date").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'yy-mm-dd'});
            $("#to_date").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'yy-mm-dd'});
            $("#po_from_date").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'yy-mm-dd', minDate: '2015-07-22',
                onClose: function() {
                    var lastDate = new Date();
                    $("#po_to_date").removeAttr("disabled");
                    var fromDate = $("#po_from_date").val();
                    var fromDateYearFrom = fromDate.split("-", 1);
                    var fromDateMonthFrom = fromDate.split("-", 2)[1] - 1;
                    var fromDateFrom = fromDate.split("-", 3)[2];
                    lastDate.setDate(fromDateFrom);
                    lastDate.setMonth(fromDateMonthFrom);
                    lastDate.setFullYear(fromDateYearFrom);
                    $("#po_to_date").datepicker("option", "minDate", lastDate);
                }
            });
            $("#po_to_date").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'yy-mm-dd'});
            $("#project_search").autocomplete("projectFinanceReport_search.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });

            $("#customer_search").autocomplete("projectFinanceReport_customer_search.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });
            $("#pm_search").autocomplete("projectFinanceReport_pm_search.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });
            var value = document.getElementById("sbu").value;
            var sbu = value;
            if (value == "All" || value == '')
            {
                // sbu="9,10,11";
                sbu = "2,5"; // Changed the sbu migrate id
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
//            var rbuValue = document.getElementById("rbu").value;
//            if(rbuValue=="All" || rbuValue=='')
//            {
//                rbuValue="6,7,8";
//            }            
//            $.ajax(
//            {
//                url:"getSubRbuList.htm?id="+rbuValue,
//                dataType: "html",
//                success:function(data){
//                    $('#subRbu').length=0;
//                    $('#subRbu').html(data);
//                }
//            });

        });

        function getSubSbu(value)
        {
            var sbuValue = document.getElementById("sbu").value;
            if (sbuValue == "All" || sbuValue == '')
            {
                // sbuValue="9,10,11";
                sbu = "2,5"; // Changed the sbu migrate id
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
//        function getSubRbu(value)
//        {        	
//            var rbuValue = document.getElementById("rbu").value;
//            if(rbuValue=="All" || rbuValue=='')
//            {
//                rbuValue="6,7,8";
//            }            
//            $.ajax(
//            {
//                url:"getSubRbuList.htm?id="+rbuValue,
//                dataType: "html",
//                success:function(data){
//                    $('#subRbu').length=0;
//                    $('#subRbu').html(data);
//                }
//            });
//        }
        function getData() {
            var errCount = 0;
            var fromDateArr = $("#from_date").val().split("-");
            var fromDateYear = fromDateArr[0];
            var fromDateMonth = fromDateArr[1];
            var fromDateDay = fromDateArr[2];
            var toDateArr = $("#to_date").val().split("-");
            var toDateYear = toDateArr[0];
            var toDateMonth = toDateArr[1];
            var toDateDay = toDateArr[2];
            if (fromDateYear > toDateYear) {
                errCount++;
                alert("Project Start Date should be lesser than the Project End Date.");
            } else if (fromDateMonth > toDateMonth && fromDateYear == toDateYear) {
                errCount++;
                alert("Project Start Date should be lesser than the Project End Date.");
            } else if (fromDateDay > toDateDay && fromDateMonth == toDateMonth && fromDateYear == toDateYear) {
                errCount++;
                alert("Project Start Date should be lesser than the Project End Date.");
            } else {

            }
            var poFromDateArr = $("#po_from_date").val().split("-");
            var poFromDateYear = poFromDateArr[0];
            //alert("fromDateYear "+fromDateYear);
            var poFromDateMonth = poFromDateArr[1];
            //alert("fromDateMonth "+fromDateMonth);
            var poFromDateDay = poFromDateArr[2];
            //alert("fromDateDay "+fromDateDay);
            var poToDateArr = $("#po_to_date").val().split("-");
            var poToDateYear = poToDateArr[0];
            //alert("toDateYear "+toDateYear);
            var poToDateMonth = poToDateArr[1];
            //alert("toDateMonth "+toDateMonth);
            var poToDateDay = poToDateArr[2];
            //alert("toDateDay "+toDateDay);
            if (poFromDateYear > poToDateYear) {
                errCount++;
                alert("PO Start Date should be lesser than the PO End Date.");
            } else if (poFromDateMonth > poToDateMonth && poFromDateYear == poToDateYear) {
                errCount++;
                alert("PO Start Date should be lesser than the PO End Date.");
            } else if (poFromDateDay > poToDateDay && poFromDateMonth == poToDateMonth && poFromDateYear == poToDateYear) {
                errCount++;
                alert("PO Start Date should be lesser than the PO End Date.");
            } else {

            }

            if (errCount == 0) {
                $('#projectFinanceReportPage').attr("action", "projectFinanceReport.htm");
                document.projectFinanceReportPage.submit();
                startLoading();
            }
        }

        function doValidate() {
            var fromDate = document.getElementById("from_date").value;
            var toDate = document.getElementById("to_date").value;
            var project_search = document.getElementById("project_search").value;
            var customer_search = document.getElementById("customer_search").value;
            var pm_search = document.getElementById("pm_search").value;

            var validData = 0;

            if ((fromDate == null || fromDate == '')) {
                validData = 0;
            } else {
                validData = 1;
                return validData;
            }
            if ((toDate == null || toDate == '')) {
                validData = 0;
            } else {
                validData = 1;
                return validData;
            }

            if ((project_search == null || project_search == '')) {
                validData = 0;
            } else {
                validData = 1;
                return validData;
            }
            if ((customer_search == null || customer_search == '')) {
                validData = 0;
            } else {
                validData = 1;
                return validData;
            }
            if ((pm_search == null || pm_search == '')) {
                validData = 0;
            } else {
                validData = 1;
                return validData;
            }

            return validData;
        }

        function projectFinanceReportExport() {
            $('#projectFinanceReportPage').attr("action", "projectFinanceReportXL.htm");
            document.projectFinanceReportPage.submit();
        }

        $(document).ajaxComplete(function() {
//            var subRbuValue = document.getElementById("subRbu");                        
//            var selectedSubRbu = document.getElementById("selectedSubRbu").value;            
//            subRbuValue.value= selectedSubRbu;

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
                Project Finance Report
            </div>
        </div>
        <div class="tabletools">
            <form action="#" name="projectFinanceReportPage" method="post" id="projectFinanceReportPage" onsubmit="javascript:getData()">                    
                <%
                    String selectedSubSbu = (String) request.getAttribute("selectedSubSbu");
                    String selectedSubRbu = (String) request.getAttribute("selectedSubRbu");
                %>                    
                <input type="hidden" id="selectedSubRbu" name="selectedSubRbu" value="<%=selectedSubRbu%>"/>
                <input type="hidden" id="selectedSubSbu" name="selectedSubSbu" value="<%=selectedSubSbu%>"/>
                <table id="searchForm" >
                    <tbody>
                    <input type="hidden" id="page" name="page" value="${paging[0] > 1 ? paging[1]:'1'}" />
                    <tr>
                        <td width="130px">
                            <label for="Project Code/Name" style="width: 70px;"><b>Project Code/Name: </b></label>                                    
                        </td>
                        <td>
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
                        <td width="130px">
                            <label for="Customer Code/Name" style="width: 70px;"><b>Customer Code/Name: </b></label>                                    
                        </td>
                        <td>
                            <%
                                String customerString = (String) request.getAttribute("customerString");
                                if (customerString == null) {
                                    customerString = "";
                                }
                            %>
                            <input type="text" id="customer_search" name="customer_search" style="width:300px;color: #666666;"  onfocus="if (this.value == 'Search by Customer Id or Customer Name')
                this.value = '';" onblur="if (this.value == '')
                this.value = 'Search by Customer Id or Customer Name';"  value="<%=customerString%>"/>										
                        </td>
                    </tr>
                    <!--                        <tr>                                                                
                                                <td width="130px">
                                                    <label for="RBU" style="width: 70px;"><b>RBU: </b></label>                                    
                                                </td>
                                                <td>
                                                    <select id="rbu" name="rbu" class="textbox_new" onchange="getSubRbu(this.value);">
                                                        <option value="" >-- All --</option>
                    <%
                        java.util.List<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails> rbuList = (java.util.List) request.getAttribute("rbuList");
                        if (rbuList != null) {
                            java.util.Iterator<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails> rbuItr = rbuList.iterator();
                            com.defiance.ideal.reports.dto.ProjectFinanceReportDetails td = null;
                            String selectedRbuId = (String) request.getAttribute("selectedRbuId");
                            while (rbuItr.hasNext()) {
                                td = rbuItr.next();
                                if (td.getRbu_id().equalsIgnoreCase(selectedRbuId)) {
                                    out.println("<option value='" + td.getRbu_id() + "' selected>" + td.getName() + "</option>");
                                } else {
                                    out.println("<option value='" + td.getRbu_id() + "'>" + td.getName() + "</option>");
                                }
                            }
                        }

                    %>
                </select> 	
            </td>                                
            <td width="130px">
                <label for="Sub RBU" style="width: 70px;"><b>Sub RBU: </b></label>                                    
            </td>
            <td>
                <select id="subRbu" name="subRbu" class="textbox_new" >                                        
                </select>										
            </td>                                                                
        </tr>-->
                    <tr>                                                                
                        <td width="130px">
                            <label for="Sbu" style="width: 70px;"><b>SBU: </b></label>                                    
                        </td>
                        <td>
                            <select id="sbu" name="sbu" class="textbox_new" onchange="getSubSbu(this.value);">
                                <option value="" >-- All --</option>
                                <%
                                    java.util.List<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails> sbuList = (java.util.List) request.getAttribute("sbuList");
                                    if (sbuList != null) {
                                        java.util.Iterator<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails> sbuItr = sbuList.iterator();
                                        com.defiance.ideal.reports.dto.ProjectFinanceReportDetails td = null;
                                        String selectedSbuId = (String) request.getAttribute("selectedSbuId");
                                        while (sbuItr.hasNext()) {
                                            td = sbuItr.next();
                                            if (td.getSbu_id().equalsIgnoreCase(selectedSbuId)) {
                                                out.println("<option value='" + td.getSbu_id() + "' selected>" + td.getName() + "</option>");
                                            } else {
                                                out.println("<option value='" + td.getSbu_id() + "'>" + td.getName() + "</option>");
                                            }
                                        }
                                    }

                                %>
                            </select>                                	
                        </td>                                
                        <td width="130px">
                            <label for="Sub SBU" style="width: 70px;"><b>Sub SBU: </b></label>                                    
                        </td>
                        <td>
                            <select id="subSbu" name="subSbu" class="textbox_new" >                                        
                            </select>																			
                        </td>                                                                
                    </tr>
                    <tr>                                                                
                        <td width="130px">
                            <label for="Project Start Date" style="width: 70px;"><b>Project Start Date:</b></label>                                    
                        </td>
                        <td>
                            <%
                                String fromDate = (String) request.getAttribute("fromDate");
                                if (fromDate == null) {
                                    fromDate = "";
                                }
                            %>
                            <input type="text" id="from_date" name="from_date"   readonly  value="<%=fromDate%>"/>
                        </td>                                
                        <td width="130px">
                            <label for="Project End Date" style="width: 70px;"><b>Project End Date: </b></label>                                    
                        </td>
                        <td>
                            <%
                                String toDate = (String) request.getAttribute("toDate");
                                if (toDate == null) {
                                    toDate = "";
                                }
                            %>
                            <input type="text" id="to_date" name="to_date"  readonly value="<%=toDate%>"/>

                    </tr>




                    <tr>
                        <td width="130px">
                            <label for="Project Manager" style="width: 70px;"><b>Project Manager: </b></label>                                    
                        </td>




                        <td>										
                            <%
                                String pmString = (String) request.getAttribute("pmString");
                                if (pmString == null) {
                                    pmString = "";
                                }
                            %>
                            <input type="text" id="pm_search" name="pm_search" style="width:300px;color: #666666;"  onfocus="if (this.value == 'Search by Employee Id or Employee Name')
                this.value = '';" onblur="if (this.value == '')
                this.value = 'Search by Employee Id or Employee Name';"  value="<%=pmString%>"/>
                        </td>  

                        <td width="130px">
                            <label for="ProjectModel" style="width: 70px;"><b>Project Model: </b></label>                                    
                        </td>
                        <td>
                            <select id="pjt_model" name="pjt_model" class="textbox_new" onchange="getprojectmodel(this.value);">
                                <option value="" >-- All --</option>
                                <c:forEach items="${projectModelList}" var="projectModelListValues" varStatus="index" >
                                    <option value="${projectModelListValues.projectModelKey}" ${projectModelListValues.projectModelKey == selectedPjtModel ? 'selected' : ''}>${projectModelListValues.projectModelValue}</option>

                                </c:forEach>

                        </td>   

                    </tr>
                    <tr>                                                                
                        <td width="130px">
                            <label for="PO Start Date" style="width: 70px;"><b>PO Start Date:</b></label>                                    
                        </td>
                        <td>
                            <%
                                String poFromDate = (String) request.getAttribute("poFromDate");
                                if (poFromDate == null) {
                                    poFromDate = "";
                                }
                            %>
                            <input type="text" id="po_from_date" name="po_from_date"   readonly  value="<%=poFromDate%>"/>
                        </td>                                
                        <td width="130px">
                            <label for="PO End Date" style="width: 70px;"><b>PO End Date: </b></label>                                    
                        </td>
                        <td>
                            <%
                                String poToDate = (String) request.getAttribute("poToDate");
                                if (poToDate == null) {
                                    poToDate = "";
                                }
                                String disableVal = "disabled";
                                if (poToDate != null && !"".equals(poToDate)) {
                                    disableVal = "";
                                }
                            %>
                            <input type="text" id="po_to_date" name="po_to_date"  readonly value="<%=poToDate%>" <%=disableVal%>/>

                    </tr>
                </table>
            </form>
            <br>
            <table style="font-size: 10px;" width="10%">
                <tbody align="left">
                    <tr>
                        <td align="left"><input class="gobutton" type="button" style="width:70px;" name="submit" onclick="getData()"  value="Go"/></td>
                        <td align="left"> <input class="exportbutton"  type="button" onclick="projectFinanceReportExport()" value="Export"/></td>
                    </tr>
                </tbody>
            </table>

        </div>
        <br>
        <div id="datadisplay">
            <table style="font-size: 10px;" width="100%">
                <tbody>
                <th align="center">Project Code</th>
                <th align="center">PO Number</th>
                <th align="center">PO Date</th>
                <!--                <th align="center">Customer Group</th>-->
                <th align="center">Customer Code</th>
                <th align="center">Customer Name</th>
                <!--                <th align="center">RBU</th>
                                <th align="center">Sub RBU</th>                        -->
                <!--                <th align="center">Region</th>-->
                <!--                <th align="center">Country</th>-->
                <!--                <th align="center">Registered Address</th>-->
                <!--                <th align="center">Customer Contact</th>-->
                <!--                <th align="center">Customer Phone No.</th>-->
                <!--                <th align="center">BDM ID</th>                        -->
                <!--                <th align="center">Defiance Contact</th>-->
                <!--                <th align="center">Defiance Contact No.</th>-->
                <th align="center">SBU</th>
                <th align="center">Sub SBU</th>
                <th align="center">Project Name</th>
                <th align="center">Pricing Model</th>
                <th align="center">Currency</th>
                <th align="center">PO Amount</th>
				<th align="center">SO Value</th>
                <!--                <th align="center">Credit Period</th>-->
                <th align="center">Project Start Date</th>
                <th align="center">Project End Date</th>
                <!--                <th align="center">Remarks</th>-->
                <th align="center">Project Status</th>                        
                <th align="center">Project Manager</th>
                <th align="center">Billing UOM</th>
                <th align="center">Project Model</th>

                </tbody>
                <tbody>
                    <%
                        java.util.List<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails> detailsList = (java.util.List<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails>) request.getAttribute("reportDetailsList");
                        if (detailsList != null) {
                            java.util.Iterator<com.defiance.ideal.reports.dto.ProjectFinanceReportDetails> itr = detailsList.iterator();
                            com.defiance.ideal.reports.dto.ProjectFinanceReportDetails pd = null;
                            while (itr.hasNext()) {
                                pd = itr.next();

                                out.println("<tr>");
                                out.println("<td>");
                                out.println(pd.getProject_code());
                                out.println("</td>");
                                if (pd.getPoNumber() == null) {
                                    out.println("<td></td>");
                                } else {
                                    out.println("<td>" + pd.getPoNumber() + "</td>");
                                }
                                if (pd.getPoDate() == null) {
                                    out.println("<td></td>");
                                } else {
                                    out.println("<td>" + pd.getPoDate() + "</td>");
                                }
//                                if (pd.getCustomerGroup() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getCustomerGroup() + "</td>");
//                                }
                                if (pd.getCustomer_code() == null) {
                                    out.println("<td></td>");
                                } else {
                                    out.println("<td>" + pd.getCustomer_code() + "</td>");
                                }
                                if (pd.getCustomer_name() == null) {
                                    out.println("<td></td>");
                                } else {
                                    out.println("<td>" + pd.getCustomer_name() + "</td>");
                                }
//                                if (pd.getRbu() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getRbu() + "</td>");
//                                }
//                                if (pd.getSubRbu() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getSubRbu() + "</td>");
//                                }
//                                if (pd.getRegion() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getRegion() + "</td>");
//                                }
//                                if (pd.getCountry() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getCountry() + "</td>");
//                                }
//                                if (pd.getRegisteredAddress() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getRegisteredAddress() + "</td>");
//                                }
//                                if (pd.getCustomerContact() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getCustomerContact() + "</td>");
//                                }
//                                if (pd.getCustomerPhoneNo() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getCustomerPhoneNo() + "</td>");
//                                }
//                                if (pd.getBdmId() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getBdmId() + "</td>");
//                                }
//                                if (pd.getDefianceContact() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getDefianceContact() + "</td>");
//                                }
//                                if (pd.getDefianceContactNo() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getDefianceContactNo() + "</td>");
//                                }
                                out.println("<td>" + pd.getSbu() + "</td>");
                                if (pd.getSubSbu() == null) {
                                    out.println("<td></td>");
                                } else {
                                    out.println("<td>" + pd.getSubSbu() + "</td>");
                                }
                                out.println("<td>" + pd.getProject_name() + "</td>");
                                out.println("<td>" + pd.getPricingModel() + "</td>");
                                out.println("<td>" + pd.getCurrency() + "</td>");
                                out.println("<td>" + pd.getPoAmount() + "</td>");
								out.println("<td>" + pd.getCreditPeriod() + "</td>");
//                                if (pd.getCreditPeriod() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getCreditPeriod() + "</td>");
//                                }
                                out.println("<td>" + pd.getProjectStartDate() + "</td>");
                                out.println("<td>" + pd.getProjectEndDate() + "</td>");
//                                if (pd.getRemarks() == null) {
//                                    out.println("<td></td>");
//                                } else {
//                                    out.println("<td>" + pd.getRemarks() + "</td>");
//                                }
                                out.println("<td>" + pd.getProjectStatus() + "</td>");
                                out.println("<td>" + pd.getProjectManager() + "</td>");
                                out.println("<td>" + pd.getBillingUOM() + "</td>");
                                out.println("<td>" + pd.getPrjModel() + "</td>");
                                out.println("</tr>");


                            }

                        }

                    %>
                </tbody>
            </table>
            <cn:if test="${paging[0] > 1}">
                <%@include file="/WEB-INF/jsp/paging.jsp" %>
            </cn:if>
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
        function loadForm(page) {

            $('#page').val(page);
            //alert("In side Load form " + $('#page').val());
            $('#projectFinanceReportPage').attr("action", "projectFinanceReport.htm");
            //$("#submit").click();
            document.projectFinanceReportPage.submit();
        }
        </script>
    </div>
</body>
</html>
