<%-- 
    Document   : utilizationReport
    Created on : 24 Jun, 2022, 10:24:30 AM
    Author     : 18128
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/header.jsp" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Utilization Report</title>
        <style type="text/css">
            form label{
                float: none;
                display: inline;
                font-weight: bold;
            }
            #selectMonth,#selectYear{
                width:100px;
                margin-right:25px;
            }
            .exportbutton{
                border-radius: 10px;
                cursor: pointer;
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
              Utilization Report
            </div>           
                <form action="utilizationReportDownload.htm" id="utilizationReport" name="utilizationReport" method="POST">
                    <div class="tabletools">
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
                        <input type="button" class="exportbutton" id="exportUtilizationReport" name="export" value="Export" onclick="downloadExcel();"/>
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
                url: 'utilizationReportSearch.htm?monthId='+monthId+'&yearId='+yearId,
                success: function(response) {
                    stopLoading();
                    $("#utilizationReport").submit();
                }
            });
        }       
    </script>
</html>


