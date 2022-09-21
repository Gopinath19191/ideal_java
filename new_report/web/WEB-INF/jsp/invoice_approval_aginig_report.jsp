<%-- 
    Document   : invoice_approval_aginig_report
    Created on : 7 Apr, 2020, 2:08:22 PM
    Author     : 16221
--%>
<%@include file="header.jsp" %>
<html>
    <head>
        <title>Invoice Approval Aging Report </title>
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
    </style>
    <script type="text/javascript">
        function getExcelReport(){
            var month = $("#filter_month").val();
            var year = $("#filter_year").val();
            $('#reportPage').attr("action", "invoiceAgingReportXL.htm?month="+month+"&year="+year);
            document.reportPage.submit();
        }
        
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Invoice Approval Aging Report
                </div>
            </div>
            <div class="tabletools">
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <table style="width: 100%">
                        <td width="" style="color: #666;"><b>Month</b> 
                            <select id="filter_month" name="filter_month">
                                <c:forEach items="${monthList}" var="month" varStatus="monthitr">
                                    <c:set var="selMonth" value="" ></c:set>
                                    <c:if test="${month.key==filterData.month}">
                                        <c:set var="selMonth" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selMonth} value="${month.key}">${month.value}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td width="" style="color: #666;"><b>Year :</b> 
                            <select id="filter_year" name="filter_year">
                                <c:forEach items="${yearList}" var="year" varStatus="yearitr">
                                    <c:set var="selYear" value="" ></c:set>
                                    <c:if test="${year.key==filterData.year}">
                                        <c:set var="selYear" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selYear} value="${year.key}">${year.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td >
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

