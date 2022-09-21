<%@include file="header.jsp" %>
<html>
    <head>
        <title>Leave Report</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
    </head>
    <style type="text/css">
        #loadingDiv img{ border: none; }
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
        .gobutton{
            border: 1px solid #BCCFEA;
            background: none repeat scroll 0 0 #316ca8;
            color: #FFFFFF;
            float: right;
            font-weight: bold;
            height: 28px;
            margin: 0px;
            width: 70px;
            padding:1px 4px 1px 1px;
        }
        .hiperlink{
            padding:1px 4px 1px 14px;        
            text-decoration:none;
            color:#15428B;
            float: right;
        }
    </style>
    <script type="text/javascript">
        
        function loadForm(page) {
                $('#page').val(page);
                $('#monthlyLeaveReport').attr("action", "monthlyLeaveReport.htm");
                document.monthlyLeaveReport.submit();
            }
            function getsubsbu(){
                var subSbu = $('#sbu').val();
            $.ajax(
                    {
                        url: "getSubPractice.htm?id=" + subSbu,
                        dataType: "html",
                        success: function(data) {
                            $('#subSbu').length = 0;
                            $('#subSbu').html(data);
                        }
                    });
            }
            
            function getFilterList(){
            var selyear = $('#selectYear').val();
            var selmonth = $('#selectMonth').val();
            if(selyear == ''){
                alert("please select Year");
                return false;
            }else if(selmonth == ''){
                alert("please select Month");
                return false;
            }else{
                $('#monthlyLeaveReport').attr("action", "monthlyLeaveReport.htm");
                document.monthlyLeaveReport.submit();
                //return true;
            }
            }
            function getExportAll(){
                $('#monthlyLeaveReport').attr("action", "exportLeaveReport.htm");
                document.monthlyLeaveReport.submit();
            }
            
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Monthly Leave Report
                </div>
            </div>
            <form name="monthlyLeaveReport" method="post" id="monthlyLeaveReport" action="monthlyLeaveReport.htm">
            <div class="tabletools">
                <table>
                    <tr><td>
                            <label style="width: 110px"><b>SBU :</b></label>
                            <select class="leaveselect" id ="sbu" name="sbu" onchange="getsubsbu();">
                                <option value="">--select--</option>                                
                                <c:forEach items="${sbuList}" var="sbulist">
                                    <option value="${sbulist.id}" ${sbulist.id == selectedSbu?'selected':'' }>${sbulist.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label style="width: 110px"><b>Year :</b></label>
                            <select class="leaveselect" id ="selectYear" name="selectYear">
                                <option value="">--Year--</option>
                                <c:forEach items="${yearList}" var="year">
                                    <option value="${year.key}" ${year.key==selectedYear?'selected':''}>${year.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label style="width: 110px"><b>Sub SBU :</b></label>
                            <select class="leaveselect" id ="subSbu" name="subSbu">
                                <option value="">--select--</option>
                                <c:forEach items="${ssubList}" var="ssublist">
                                     <option value="${ssublist.id}" ${ssublist.id == selectedSubSbu?'selected':'' }>${ssublist.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label style="width: 110px"><b>Month :</b></label>
                            <select class="leaveselect" id="selectMonth" name="selectMonth">
                                <option value="">--Month--</option>
                                <c:forEach items="${monthList}" var="month">
                                    <option value="${month.key}" ${month.key==selectedMonth?'selected':''}>${month.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <input type="button" class="leavegoButtonNew" id="goSearch" name="go" value="Go" onclick="getFilterList()"/>
                        </td>
                        <td>
                            <input type="button" class="leaveexportbutton" id="exportData" name="exportAll" value="Export" onclick="getExportAll()"/>                            
                        </td>
                    </tr>
                </table>
                    </div>
            <div id="datadisplay">
                 <input id="page" name="page" value="1" type="hidden">
                <table style="font-size: 13px;">                   
                    <th>Employee Name</th>
                    <th>Opening Balance</th>
                    <th>LOP Days</th>
                    <th>Accrued</th>
                    <th>Taken</th>
                    <th>Canceled</th>
                    <th>Arrear</th>
                    <th>Encashed</th>
                    <th>Closing Balance</th>                   
                    <tbody>
                        <c:if test="${fn:length(filterdata)!=0}">
                            <% int i = 0;
                                int s = 0;
                            %>
                             <c:forEach items="${filterdata}" var="data">
                            <% s = i % 2;
                                if (s == 0) {%>
                            <tr class="row-odd">
                                <% } else {%>
                            <tr class="row-even">
                                <%}%> 
                                <td>${data.employee_name}</td>
                                <td>${data.opening_balence}</td>
                                <td>${data.lop_days}</td>
                                <td>${data.leave_accrued}</td>
                                <td>${data.leave_taken}</td>                                
                                <td>${data.leave_cancelled}</td>
                                <td>${data.leave_arrear}</td>
                                <td>${data.leave_encashed}</td>
                                <td>${data.closing_balence}</td>
                            </tr>
                            <% i = i + 1;%>
                            </c:forEach>                                
                        </c:if>
                        <c:if test="${fn:length(filterdata)==0}">
                        <tr class="row-odd" >
                            <td colspan="9" style="text-align: center">
                                 No data to display.
                            </td>
                        </tr>                       
                    </c:if>
                    </tbody>
                </table>
                <c:if test="${paging[0] > 1}">
                     <%@include file="/WEB-INF/jsp/paging.jsp" %>
                </c:if>
            </div>
        </form>
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
