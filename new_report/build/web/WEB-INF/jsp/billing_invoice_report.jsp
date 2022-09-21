<%-- 
    Document   : billing_invoice_report
    Created on : 5 Jan, 2016, 11:22:41 AM
    Author     : 16047
--%>

<%@include file="header.jsp" %>
<html>
    <head>
        <title>Billing Accrual Report</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
    </head>
    <style type="text/css">
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
            //employee Search
            $("#employee_search").autocomplete("baEmpAjaxsearch.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });
            $("#employee_search").result(function(event, data, formatted) {
                if (data) {
                    $("#employee_id").val(data[1]);
                }
            });

            //Project Search
            $("#project_search").autocomplete("projectAjaxsearch.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });
            $("#project_search").result(function(event, data, formatted) {
                if (data) {
                    $("#projectId").val(data[1]);
                }
            });

            //Customer Search
            $("#customer_search").autocomplete("customerAjaxsearch.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });
            $("#customer_search").result(function(event, data, formatted) {
                if (data) {
                    $("#customerId").val(data[1]);
                }
            });

            $("#accruedFrom").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "dd-mm-yy"
            }
            );
            $("#accruedTo").datepicker({
                changeMonth: true,
                changeYear: true,
                dateFormat: "dd-mm-yy"
            }
            );
        });

//        function setEmpPrjCustIds() {
//            if ($("#employee_search").val() == '' || $("#employee_search").val() == null) {
//                document.getElementById("employee_id").value = "";
//            }
//            if ($("#project_search").val() == '' || $("#project_search").val() == null) {
//                document.getElementById("projectId").value = "";
//            }
//            if ($("#customer_search").val() == '' || $("#customer_search").val() == null) {
//                document.getElementById("customerId").value = "";
//            }
//        }

        function getFilterList() {
            if ($("#employee_search").val() == '' || $("#employee_search").val() == null) {
                document.getElementById("employee_id").value = "";
            }
            if ($("#project_search").val() == '' || $("#project_search").val() == null) {
                document.getElementById("projectId").value = "";
            }
            if ($("#customer_search").val() == '' || $("#customer_search").val() == null) {
                document.getElementById("customerId").value = "";
            }
            document.getElementById("billinginvoicereportPage").action = "billingInvoiceReport.htm";
            document.billinginvoicereportPage.submit();
        }

        function getExcelReport() {
            if ($("#employee_search").val() == '' || $("#employee_search").val() == null) {
                document.getElementById("employee_id").value = "";
            }
            if ($("#project_search").val() == '' || $("#project_search").val() == null) {
                document.getElementById("projectId").value = "";
            }
            if ($("#customer_search").val() == '' || $("#customer_search").val() == null) {
                document.getElementById("customerId").value = "";
            }
            document.getElementById("billinginvoicereportPage").action = "billingInvoiceReportExport.htm";
            document.billinginvoicereportPage.submit();
        }

    </script>
    <body>
<!--        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>-->
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Billing Accrual Report
                </div>
            </div>
            <div class="tabletools">
                <form action="#" name="billinginvoicereportPage" method="post" id="billinginvoicereportPage" >
                    <table id="searchForm" width="100%">
                        <tbody>
                        <input type="hidden" id="page" name="page" value="${paging[0] > 1 ? paging[1]:'1'}" />
                        <tr class="filetrInnerWrap">
                            <td>
                                <label for = "employeeName" style="width:92px;margin-top:5px;padding-right:4px;font-weight:bolder;"><b>Employee Name</b></label>
                                <input type="text" name="employee_search" id="employee_search" value="${filterData.employee_id != '' || filterData.employee_id != null?empName:''}"/>
                                <input type="hidden" id="employee_id" name="employee_id" value ="${filterData.employee_id}"/>
                            </td>
                            <td>
                                <label for = "projectName" style="width:78px;margin:5px 0 0 10px;padding-right:4px;font-weight:bolder;"><b>Project Name</b></label>
                                <input type="text" name="project_search" id="project_search" value="${filterData.projectId != '' || filterData.projectId != null?projectName:''}"/>
                                <input type="hidden" name="projectId" id="projectId" value ="${filterData.projectId}"/>
                            </td>
                            <td>
                                <label for = "customer" style="width:58px;margin:5px 0 0 10px;padding-right:4px;font-weight:bolder;"><b>Customer</b></label>
                                <input type="text" name="customer_search" id="customer_search" value="${filterData.customerId != '' || filterData.customerId != null?customerName:''}"/>
                                <input type="hidden" name="customerId" id="customerId" value="${filterData.customerId}"/>
                            </td>
                        </tr>
                        <tr class="filetrInnerWrap" style="padding:12px 0 0 0;">
                            <td>
                                <label for = "projectSbu" style="width:68px;margin-top:5px;padding-right:4px;font-weight:bolder;"><b>Project SBU</b></label>
                                <select name="projectSbu" id="projectSbu" >
                                    <option value="">All</option>
                                    <c:forEach items="${prjSbu}" var="psbu" varStatus="psbuitr">
                                        <c:set var="selpsbu" value="" ></c:set>
                                        <c:if test="${psbu.key==filterData.projectSbu}">
                                            <c:set var="selpsbu" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selpsbu} value="${psbu.key}">${psbu.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for = "employeeSbu" style="width:82px;padding-right:4px;margin:5px 0 0 10px;font-weight:bolder;"><b>Employee SBU</b></label>
                                <select name="employeeSbu" id="employeeSbu" >
                                    <option value="">All</option>
                                    <c:forEach items="${empSbu}" var="esbu" varStatus="esbuitr">
                                        <c:set var="selesbu" value="" ></c:set>
                                        <c:if test="${esbu.key==filterData.employeeSbu}">
                                            <c:set var="selesbu" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selesbu} value="${esbu.key}">${esbu.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr class="filetrInnerWrap" style="padding:12px 0 0 0;">
                            <td>
                                <label for = "billingYear" style="width:62px;padding-right:4px;margin-top:5px;font-weight:bolder;float:left;"><b>Billing Year</b></label>
                                <select name="billingYear" id="billingYear">
                                    <option value="">-Select Year-</option>
                                    <c:forEach items="${yearList}" var="year" varStatus="yearitr">
                                        <c:set var="selyear" value="" ></c:set>
                                        <c:if test="${year.yearName==filterData.billingYear}">
                                            <c:set var="selyear" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selyear} value="${year.yearName}">${year.yearName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <div class="checkbox">
                                    <label for = "billingMonth" style="width:72px;margin-top:5px;font-weight:bolder;float:left;"><b>Billing Month</b></label>
                                    <c:forEach items="${monthList}" var="month" varStatus="monthitr">
                                        <c:set var="selmonth" value="" ></c:set>
                                        <c:forEach items="${filterData.billingMonth}" var="billMonth" varStatus="billMonthitr">
                                            <c:if test="${month.monthId==billMonth}">
                                                <c:set var="selmonth" value="checked='checked'" ></c:set>
                                            </c:if>
                                        </c:forEach>
                                        <input ${selmonth} type="checkbox" id="billingMonth_${monthitr.index}" name="billingMonth" value="${month.monthId}">${month.monthName}
                                    </c:forEach>
                                </div>
                            </td>
                        </tr>
                        <tr class="filetrInnerWrap" style="padding:12px 0 0 0;">
                            <td>
                                <label for = "accrualSno" style="width:72px;padding-right:4px;margin-top:5px;font-weight:bolder;"><b>Accrual S.no</b></label>
                                <select name="operator" id="operator">
                                    <option value="">-Select Option-</option>
                                    <c:forEach items="${operatorsList}" var="operators" varStatus="operatorsitr">
                                        <c:set var="selopt" value="" ></c:set>
                                        <c:if test="${operators.operatorId==filterData.operator}">
                                            <c:set var="selopt" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selopt} value="${operators.operatorId}">${operators.operatorName}</option>
                                    </c:forEach>
                                </select>
                                <!--                            </td>
                                                            <td>-->
                                <select name="accrualSno" id="accrualSno">
                                    <option value="">-Select Accrual S.no-</option>
                                    <c:forEach items="${accrualSnoList}" var="accrualsno" varStatus="accrualsnoitr">
                                        <c:set var="selaccrualsno" value="" ></c:set>
                                        <c:if test="${accrualsno.accrualId==filterData.accrualSno}">
                                            <c:set var="selaccrualsno" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selaccrualsno} value="${accrualsno.accrualId}">${accrualsno.accrualNo}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for = "accruedFrom" style="width:80px;padding-right:4px;margin:5px 0 0 0px;font-weight:bolder;"><b>Accrued From</b></label>
                                <input type="text" name="accruedFrom" id="accruedFrom" value="${filterData.accruedFrom}"/>
                            </td>
                            <td>
                                <label for = "accruedTo" style="width:64px;padding-right:4px;margin:5px 0 0 10px;font-weight:bolder;"><b>Accrued To</b></label>
                                <input type="text" name="accruedTo" id="accruedTo" value="${filterData.accruedTo}"/>
                            </td>


                        </tr>
                        <tr>
                            <td>
                                <input type="submit" value="Go" class="gobutton" onclick="getFilterList()" name="go"/>
                                <!--                            </td>
                                                            <td>-->
                                <input class="exportbutton"  type="submit"  onclick="getExcelReport()" value="Export" style="position: relative;left: 10px;"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="datadisplay" style="overflow-x:scroll ">
                <table style="font-size: 10px;">
                    <thead>
                    <th>Accrual S.No</th>
                    <th>Accrued On</th>
                    <th>Invoice Ref.No</th>
                    <th>Invoice Ref Date</th>
                    <th>Project Code</th>
                    <th>Project Name</th>
                    <th>Pricing</th>
                    <th>Efforts Uom</th>
                    <th>Customer Name</th>
                    <th>RBU</th>
                    <th>Project SBU</th>
                    <th>Project Sub SBU</th>
                    <th>Employee Number</th>
                    <th>Employee Name</th>
                    <th>Employee SBU</th>
                    <th>Employee Sub SBU</th>
                    <th>Project Manager</th>
                    <th>Sales Person</th>
                    <th>Role</th>
                    <th>Working Place</th>
                    <th>Billing Rate</th>
                    <th>Billing Month</th>
                    <th>Billing Year</th>
                    <th>Billable Effort</th>
                    <th>Billable Amount</th>
                    <th>Currency</th>
                    </thead>
                    <c:if test="${fn:length(billingInvoiceList)>0}">
                        <c:forEach items="${billingInvoiceList}" var="invoice" varStatus="invoiceitr">
                            <c:if test="${invoiceitr.index%2 ==0}">
                                <c:set var="rowClass" value="row-odd"></c:set>
                            </c:if>
                            <c:if test="${invoiceitr.index%2!=0}">
                                <c:set var="rowClass" value="row-even"></c:set>
                            </c:if>
                            <tr class="${rowClass}">
                                <td>
                                    ${invoice.accrualSno}
                                </td>
                                <td>
                                    ${invoice.submittedOn}
                                </td>
                                <td>
                                    ${invoice.invoiceRefNo}
                                </td>
                                <td>
                                    ${invoice.submittedOn}
                                </td>
                                <td>
                                    ${invoice.projectCode}
                                </td>
                                <td>
                                    ${invoice.projectName}
                                </td>
                                <td>
                                    ${invoice.pricing}
                                </td>
                                <td>
                                    ${invoice.effortsUom}
                                </td>
                                <td>
                                    ${invoice.customerName}
                                </td>
                                <td>
                                    ${invoice.rbu}
                                </td>
                                <td>
                                    ${invoice.projectSbu}
                                </td>
                                <td>
                                    ${invoice.projectSubSbu}
                                </td>
                                <td>
                                    ${invoice.employeeNumber}
                                </td>
                                <td>
                                    ${invoice.employeeName}
                                </td>
                                <td>
                                    ${invoice.employeeSbu}
                                </td>
                                <td>
                                    ${invoice.employeeSubSbu}
                                </td>
                                <td>
                                    ${invoice.projectManager}
                                </td>
                                <td>
                                    ${invoice.salesPerson}
                                </td>
                                <td>
                                    ${invoice.role}
                                </td>
                                <td>
                                    ${invoice.workingPlace}
                                </td>
                                <td>
                                    ${invoice.billingRate}
                                </td>
                                <td>
                                    ${invoice.billingMonth}
                                </td>
                                <td>
                                    ${invoice.billingYear}
                                </td>
                                <td>
                                    ${invoice.billableEffort}
                                </td>
                                <td>
                                    ${invoice.billableAmount}
                                </td>
                                <td>
                                    ${invoice.currency}
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                </table>
                <cn:if test="${paging[0] > 1}">
                    <%@include file="/WEB-INF/jsp/paging.jsp" %>
                </cn:if>
            </div>
                <script type="text/javascript">
                    function loadForm(page) {
				// alert("In side Load form "+page);
				$('#page').val(page);
				$('#billinginvoicereportPage').attr("action", "billingInvoiceReport.htm");
				document.billinginvoicereportPage.submit();
			}
                </script>
        </div>
    </body>
</html>