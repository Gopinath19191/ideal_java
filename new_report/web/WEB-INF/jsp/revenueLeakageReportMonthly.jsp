<%-- 
    Document   : revenueLeakageReportMonthly
    Created on : 15 Jul, 2020, 11:32:12 AM
    Author     : 16221
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Revenue Leakage Monthly</title>
        <style type="text/css">
            form label{
                float: none;
                display: inline;
                font-weight: bold;
            }
            #selectMonth,#selectYear, #selectCustomer,#selectPractice,#selectUnit{
                width:200px;
                margin-right:25px;
            }
            .exportbutton{
                border-radius: 10px;
                cursor: pointer;
                margin:5px;
            }
        </style>
    </head>
    <body>
        <div class="container_inner" style="margin: 35px auto 0px;height:600px;width: 1000px;">
            <div id="loadingDiv" style="position:absolute;width:100%;height:600px;background-color:#777777;display: block; top: 0pt; left: 0pt; ">
                <div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center">
                    <img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait
                </div>
            </div>
            <div class="page_heading">
                Revenue Leakage Report
            </div>
            <form action="effortsLeakageMonthlyDownload.htm" id="effortsLekageFinance" name="effortsLekageFinance" method="POST">
                <div class="tabletools">
                    <input type="hidden" value="${userType}" id="userType" >
                    <label>Select Month :</label>
                    <select id ="selectMonth" name="monthId">
                        <c:forEach items="${monthList}" var="month">
                            <option value="${month.key}" ${month.key==selectedMonth?'selected':''}>${month.value}</option>
                        </c:forEach>
                    </select>
                    <label>Select Year :</label>
                    <select id ="selectYear" name="yearId">
                        <c:forEach items="${yearList}" var="year">
                            <option value="${year.key}" ${year.key==selectedYear?'selected':''}>${year.value}</option>
                        </c:forEach>
                    </select>
                    <c:if test="${userType=='1'}">
                        <input type="hidden" value="" id="selectCustomer" >
                        <input type="hidden" value="" id="selectUnit" >
                        <input type="hidden" value="" id="selectPractice" >
                    </c:if>
                    <c:if test="${userType=='0'}">
                        <label>Select Customer :</label>
                        <select id ="selectCustomer" name="customerId">
                            <option value="">-- Select Customer --</option>
                            <c:forEach items="${CustomerList}" var="customer">
                                <option value="${customer.customer_id}" ${customer.customer_id==selectedMonth?'selected':''}>${customer.customer_name}</option>
                            </c:forEach>
                        </select>
                        <br>
                        <label>Select Unit :</label>
                        <select id ="selectUnit" name="unit_id" onchange="getPractice(this.value)" >
                            <option value="">--Select Unit--</option>
                            <c:forEach items="${unitList}" var="unit">
                                <option value="${unit.unit_id}" ${unit.unit_id==selectedMonth?'selected':''}>${unit.unit_name}</option>
                            </c:forEach>
                        </select>
                        <label>Select Practice :</label>
                        <select id ="selectPractice" name="sub_unit_id">
                            <option value="">--Select Practice--</option>
                            <c:forEach items="${subUnitList}" var="sub_unit">
                                <option value="${sub_unit.sub_unit_id}" ${sub_unit.sub_unit_id==selectedYear?'selected':''}>${sub_unit.sub_unit_name}</option>
                            </c:forEach>
                        </select>
                    </c:if>

                    <input type="button" class="exportbutton" id="exportFinance" name="export" value="Export" onclick="unbilledDownloadExcel();"/>
                </div>
            </form>
        </div>
    </body>
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
            var loaderStartHeight = $("html").height();
            loadingDivObj.height = loaderStartHeight + "px";
            if (ns4) {
                loadingDivObj.visibility = "visible";
                $("#loadingImage").attr("tabindex", -1).focus();
            } else if (ns6 || ie4)
                loadingDivObj.display = "block";
            $("#loadingImage").attr("tabindex", -1).focus();
        }
        
        function downloadExcel(){
            startLoading();
            var monthId = $("#selectMonth").val();
            var yearId = $("#selectYear").val();
            $.ajax({
                type: 'POST',
                url: 'projectLeakageSearch.htm?monthId='+monthId+'&yearId='+yearId,
                success: function(response) {
                    stopLoading();
                    $("#effortsLekageQuality").submit();
                }
            });
        }
        function unbilledDownloadExcel(){
            startLoading();
            var monthId = $("#selectMonth").val();
            var yearId = $("#selectYear").val();
            var customer_id = $("#selectCustomer").val();
            var unitId = $("#selectUnit").val();
            var subUnitId = $("#selectPractice").val();
            var userType = $("#userType").val();
            $.ajax({
                type: 'POST',
                url: 'effortsLeakageMonthlySearch.htm?monthId='+monthId+'&yearId='+yearId+'&customerId='+customer_id+'&unitId='+unitId+'&subUnitId='+subUnitId+'&userType='+userType,
                success: function(response) {
                    stopLoading();
                    $("#effortsLekageFinance").submit();
                }
            });
        }
        function getPractice(selectedValue) {
            $("#selectPractice").val('');
            if(selectedValue != "") {
                $.ajax({                   
                    url: './getSUbUnit.htm',
                    type: "post",
                    async: false,
                    data: ({parent_id:selectedValue}),
                    dataType:'html',
                    success: function(ajaxObj) {
                        $("#selectPractice").html('').html(ajaxObj);
                    }
                });
            };
        };
    </script>
</html>
