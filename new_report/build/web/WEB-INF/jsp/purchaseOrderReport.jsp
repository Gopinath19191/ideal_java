<%-- 
    Document   : purchaseorderjsp
    Created on : Jan 25, 2013, 4:56:33 PM
    Author     : 15332
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
        <title>Purchase Order Report</title>
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
            $("#from_date").datepicker({changeMonth: true, changeYear: true, disabled: true, dateFormat: 'yy-mm-dd', minDate: '2015-07-22',
                onClose: function() {
                    var lastDate = new Date();
                    $("#to_date").removeAttr("disabled");
                    var fromDate = $("#from_date").val();
                    var fromDateYearFrom = fromDate.split("-", 1);
                    var fromDateMonthFrom = fromDate.split("-", 2)[1] - 1;
                    var fromDateFrom = fromDate.split("-", 3)[2];
                    lastDate.setDate(fromDateFrom);
                    lastDate.setMonth(fromDateMonthFrom);
                    lastDate.setFullYear(fromDateYearFrom);
                    $("#to_date").datepicker("option", "minDate", lastDate);
                }

            });

            $(function() {
                $("#to_date").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    disabled: true,
                    dateFormat: 'yy-mm-dd'
                });
            })


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
                sbu = "9,10,11";
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
            var rbuValue = document.getElementById("rbu").value;
            if (rbuValue == "All" || rbuValue == '')
            {
                rbuValue = "6,7,8";
            }
            $.ajax(
                    {
                        url: "getSubRbuList.htm?id=" + rbuValue,
                        dataType: "html",
                        success: function(data) {
                            $('#subRbu').length = 0;
                            $('#subRbu').html(data);
                        }
                    });

        });

        function getData() {
            var flag = 0;
            $('#purchaseOrderReportPage').attr("action", "purchaseOrderReport.htm");
            if ($("#from_date").val() != "" || $("#to_date").val() != "") {
                if ($("#from_date").val() == "") {
                    flag = 1;
                    alert("Please enter the from date");
                } else if ($("#to_date").val() == "") {
                    flag = 1;
                    alert("Please enter the to date");
                }
            }
            if (flag == 0) {
                document.purchaseOrderReportPage.submit();
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

        function purchaseOrderReportExport() {
            $('#purchaseOrderReportPage').attr("action", "purchaseOrderReportXL.htm");
            document.purchaseOrderReportPage.submit();
        }

        $(document).ajaxComplete(function() {
            var subRbuValue = document.getElementById("subRbu");
            var selectedSubRbu = document.getElementById("selectedSubRbu").value;
            subRbuValue.value = selectedSubRbu;

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
                Purchase Order Report
            </div>
        </div>
        <div class="tabletools">
            <form action="#" name="purchaseOrderReportPage" method="post" id="purchaseOrderReportPage" onsubmit="javascript:getData()">                    
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
                    <tr>                                                                
                        <td width="130px">
                            <label for="Project Start Date" style="width: 70px;"><b>Start Date:</b></label>                                    
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
                            <label for="Project End Date" style="width: 70px;"><b>End Date: </b></label>                                    
                        </td>
                        <td>
                            <%
                                                                    String toDate = (String) request.getAttribute("toDate");
                                                                    if (toDate == null) {
                                                                            toDate = "";
                                                                    }
                                                                    String disableVal = "disabled";
                                                                    if(toDate != null && !"".equals(toDate)){
                                                                        disableVal = "";
                                                                    }
                            %>
                            <input type="text" id="to_date" name="to_date"  readonly value="<%=toDate%>" <%=disableVal%>/>

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


                    </tr>
                </table>
            </form>
            <br>
            <table style="font-size: 10px;" width="10%">
                <tbody align="left">
                    <tr>
                        <td align="left"><input class="gobutton" type="button" style="width:70px; height: 32px" onclick="getData()" name="submit"  value="Go"/></td>
                        <td align="left"> <input class="exportbutton"  type="button" onclick="purchaseOrderReportExport()" value="Export"/></td>
                    </tr>
                </tbody>
            </table>

        </div>
        <br>
        <div id="datadisplay">
            <table style="font-size: 10px;" width="100%">
                <tbody>
                <th align="center">Customer</th>
                <th align="center">PID_Num</th>
                <th align="center">PID_Description</th>
                <th align="center">SBU</th>
                <th align="center">BDM</th>
                <th align="center">PM</th>
                <th align="center">PO_Reference_Number</th>
                <th align="center">PO_Date</th>
                <th align="center">Currency</th>
                <th align="center">PO_Value</th>
                <!--            <th align="center">Start Date</th>
                                                <th align="center">End Date</th>   -->
                </tbody>
                <tbody>
                    <%
                                                java.util.List<com.defiance.ideal.reports.dto.PurchaseOrderReportDetails> detailsList = (java.util.List<com.defiance.ideal.reports.dto.PurchaseOrderReportDetails>) request.getAttribute("purchaseOrderReportDetails");
                                                if (detailsList != null) {
                                                        java.util.Iterator<com.defiance.ideal.reports.dto.PurchaseOrderReportDetails> itr = detailsList.iterator();
                                                        com.defiance.ideal.reports.dto.PurchaseOrderReportDetails pd = null;
                                                        while (itr.hasNext()) {
                                                                pd = itr.next();

                                                                out.println("<tr>");
                                                                if (pd.getCustomer() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getCustomer() + "</td>");
                                                                }
                                                                if (pd.getPid_Num() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getPid_Num() + "</td>");
                                                                }
                                                                if (pd.getPid_Description() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getPid_Description() + "</td>");
                                                                }
                                                                if (pd.getSbu() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getSbu() + "</td>");
                                                                }

                                                                if (pd.getBdm() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getBdm() + "</td>");
                                                                }
                                                                if (pd.getProjectManager() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getProjectManager() + "</td>");
                                                                }
                                                                if (pd.getPo_Reference_Number() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getPo_Reference_Number() + "</td>");
                                                                }
                                                                if (pd.getPo_Date() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getPo_Date() + "</td>");
                                                                }

                                                                if (pd.getCurrencies() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getCurrencies() + "</td>");
                                                                }
                                                                if (pd.getPo_Value() == null) {
                                                                        out.println("<td></td>");
                                                                } else {
                                                                        out.println("<td>" + pd.getPo_Value() + "</td>");
                                                                }



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
            $('#purchaseOrderReportPage').attr("action", "purchaseOrderReport.htm");
            //$("#submit").click();
            document.purchaseOrderReportPage.submit();
        }
        </script>
    </div>
</body>
</html>
