<%@include file="header.jsp" %>
<html>
    <head>
        <title>Earned Leave Report </title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cake.generic.css" />
    </head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
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
            $('#reportPage').attr("action", "getEarnedLeaveXL.htm");
            if($("#employee_search").val() == 'Search by Employee Number or First Name or Last name'){
                document.getElementById("employee_id").value = "";
            }
            document.reportPage.submit();
        }
        function getFilterList(){
            $('#reportPage').attr("action", "earnedleave.htm");
            if($("#employee_search").val() == 'Search by Employee Number or First Name or Last name'){
                document.getElementById("employee_id").value = "";
            }
            document.reportPage.submit();
            startLoading();
        }

        $(document).ready(function(){
            $("#employee_search").autocomplete("ajaxsearch.htm", {
                minChars: 1,
                width: 350,
                matchContains: true
            });
            $("#employee_search").result(function(event, data, formatted) {
                if (data) {
                    $("#employee_id").val(data[1]);
                }
            });
        });

    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="container_inner">
                    <div class="page_heading">
                        Earned Leave Report
                    </div>
                </div>

            </div>
            <div class="tabletools">
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <table id="searchForm">
                        <tbody>
                            <tr>
                                <td>
                                    <input type="text" id="employee_search" name="employee_search" style="width:350px;color: #666666;" value="${filterData.employee_id != '' || filterData.employee_id != null?empName:'Search by Employee Number or First Name or Last name'}" onfocus="if(this.value=='Search by Employee Number or First Name or Last name') this.value='';" onblur="if(this.value=='') this.value='Search by Employee Number or First Name or Last name';" />
                                    <input type="hidden" id="employee_id" name="employee_id" value ="${filterData.employee_id}"/>
                                </td>
                                <td>
                                    <input class="gobutton" align="middle" onclick="getFilterList()" type="submit" value="Go"/>
                                </td>
                                <td>
                                    <input class="exportbutton" align="middle" type="button" style="margin-left: 20px;" onclick="getExcelReport()" value="Export"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="datadisplay">
                <c:if test="${fn:length(leave)>0}">
                    <table style="font-size: 10px;">
                        <tbody>
                        <th>Employee Number</th>
                        <th>Employee Name</th>
                        <th>Reporting Manager</th>
                        <th>Company Structure</th>
                        <th>Company Structure SubGroup</th>
                        <th>Earned Leave</th>
                        </tbody>
                        <tbody>
                            <% int i = 0;
                                int s = 0;
                            %>
                            <c:forEach items="${leave}" var="item">
                                <% s = i % 2;
                                    if (s == 0) {%>
                                <tr class="row-odd">
                                    <% } else {%>
                                <tr class="row-even">
                                    <%}%>
                                    <td>
                                        ${item.empNumber}
                                    </td>
                                    <td>
                                        ${item.empName}
                                    </td>
                                    <td>
                                        ${item.rmName}
                                    </td>
                                    <td>
                                        ${item.unit}
                                    </td>
                                    <td>
                                        ${item.cmpStructSubGroup}
                                    </td>
                                    <td>
                                        ${item.earnedLeave}
                                    </td>
                                </tr>
                                <% i = i + 1;%>
                            </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${fn:length(leave)==0}">
                    <table>
                        <tr class="row-odd">
                            <td colspan="6" style="text-align: center;">
                                   No data to display
                            </td>
                        </tr>
                    </table>
                </c:if>
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
