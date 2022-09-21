<%--
    Document   : home
    Created on : Sep 23, 2011, 1:13:24 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Accrual Status Report</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
    <style type="text/css">
        #loadingDiv img{ border: none; }
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
    </style>
    <script type="text/javascript">
        function submitForm(){
            document.getElementById("reportForm").action="accrualreport.htm";
            document.reportForm.submit();
            startLoading();
        }
        function exportToExcel(){

            document.getElementById("reportForm").action="accrualexport.htm";
            document.reportForm.submit();
        }

        function loadAjaxData(){
            
        }
        
    </script>
</head>
<body>
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Accrual Status
            </div>
        </div>
        <form name="reportForm" id="reportForm" action="accrualreport.htm" method="POST">
            <div class="tabletools">
                <table>
                    <tr>
                        <td >
                            <label for="monthFilter" ><b>Month :</b> </label>
                            <select id="monthFilter" name="monthFilter" onchange="submitForm()">
                                <%--<option value="" >-- Select Month --</option>--%>
                                <c:forEach items="${monthsMap}" var="mnth" varStatus="mnthitr">
                                    <c:set var="selMnth" value="" ></c:set>
                                    <c:if test="${mnth.key==filterData.monthFilter}">
                                        <c:set var="selMnth" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option  ${selMnth} value="${mnth.key}">${mnth.value}</option>
                                </c:forEach>
                            </select>
                        </td><td>

                            <label for="yearFilter" ><b>Year :</b> </label>
                            <select id="yearFilter" name="yearFilter" onchange="submitForm()">
                                <%--<option value="" >-- Select Year --</option>--%>
                                <c:forEach items="${yearsMap}" var="yr" varStatus="yritr">
                                    <c:set var="selYr" value="" ></c:set>
                                    <c:if test="${yr.key==filterData.yearFilter}">
                                        <c:set var="selYr" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selYr} value="${yr.key}">${yr.value}</option>
                                </c:forEach>
                            </select>

                        </td>
                        <td >
                            <label for="sbuFilter" ><b>SBU :</b> </label>
                            <select id="sbuFilter" name="sbuFilter" onchange="submitForm();">
                                <option value="All" >-- Select SBU --</option>
                                <c:forEach items="${sbuMap}" var="sbu" varStatus="sbuitr">
                                    <c:set var="selSbu" value="" ></c:set>
                                    <c:if test="${sbu.key==filterData.sbuFilter}">
                                        <c:set var="selSbu" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selSbu} value="${sbu.key}">${sbu.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td> <center> <input type="button" align="middle" style="float: none" value="Export" onclick="exportToExcel()" class="exportbutton" /> </center> </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="statsFilter" ><b>Project Status :</b> </label>
                            <select id="statsFilter" name="statsFilter" onchange="submitForm()">
                                <option value="" >-- Select Project Status --</option>
                                <c:forEach items="${pjctStatsMap}" var="psm" varStatus="psitr">
                                    <c:set var="selPs" value="" ></c:set>
                                    <c:if test="${psm.key==filterData.statsFilter}">
                                        <c:set var="selPs" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selPs} value="${psm.key}">${psm.value}</option>
                                </c:forEach>
                            </select>
                        </td>

                    </tr>
                </table>
            </div>
        </form>

        <div id="datadisplay">

            <table>
                <thead>
                <th>
                    Project Code
                </th>
                <th>
                    Project Name
                </th>
                <th>
                    PM name
                </th>
                <th>
                    SBU
                </th>
                <th>
                    Sub SBU
                </th>
                <th>
                    Accrual Status
                </th>
                </thead>
                <c:if test="${fn:length(accrualData)!=0}">
                    <c:forEach items="${accrualData}" var="rprt" varStatus="rpritr">
                        <c:if test="${rpritr.index%2 ==0}">
                            <c:set var="rowClass" value="row-even"></c:set>
                        </c:if>
                        <c:if test="${rpritr.index%2!=0}">
                            <c:set var="rowClass" value="row-odd"></c:set>
                        </c:if>

                        <tr class="${rowClass}">
                            <td>
                                ${rprt.projectCode}
                            </td>
                            <td>
                                ${rprt.projectName}
                            </td>
                            <td>
                                ${rprt.pmName}
                            </td>
                            <td>
                                ${rprt.sbu}
                            </td>
                            <td>
                                ${rprt.subSbu}
                            </td>
                            <td>
                                <c:if test="${rprt.accrualCount==rprt.totalTeamCount}">
                                    <c:set var="selColor" value="green" ></c:set>
                                </c:if>
                                <c:if test="${rprt.accrualCount!=rprt.totalTeamCount}">
                                    <c:set var="selColor" value="red" ></c:set>
                                </c:if>
                                <c:if test="${rprt.accrualCount==0 && rprt.totalTeamCount ==0}">
                                    <c:set var="selColor" value="red" ></c:set>
                                </c:if>
                                <span style="color:${selColor}">${rprt.accrualCount} out of ${rprt.totalTeamCount} Accrued</span>
                                <%-- ${rprt.accrualStatus} --%>
                            </td>
                            <%--<td>
                                ${rprt.invEffort}
                            </td>--%>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${fn:length(accrualData)==0}">
                    <tr class="row-odd">
                        <td colspan="12" style="text-align: center;">
                            No data to display
                        </td>
                    </tr>
                </c:if>
            </table>

        </div>

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
</body>