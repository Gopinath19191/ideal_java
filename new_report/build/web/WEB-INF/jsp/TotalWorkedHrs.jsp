<%@include file="header.jsp" %>
<head>
    <title>Non Project Activity Worked Hours</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
    <!--    <style type="text/css">
            #loadingDiv img{ border: none; }
            #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
        </style>-->
    <script type="text/javascript">
        function submitForm(){
            var reportType = document.getElementById("reportFilter").value;
            if(reportType == " "){
                alert("Before Submit choose any one of the report type");                
            }
            else{
                document.getElementById("reportForm").action="associatetimesheetreport.htm";
                document.reportForm.submit();
                startLoading();
            }
        }
        function exportToExcel(){
            var reportType = document.getElementById("reportFilter").value;
            if(reportType == " " ){
                alert("Before Export choose any one of the report type");
            }
            else{
                document.getElementById("reportForm").action="associatetimesheetreportexport.htm";
                document.reportForm.submit();
            }
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
                Total Worked Hours
            </div>
        </div>
        <form name="reportForm" id="reportForm"  method="POST">
            <div class="tabletools">
                <table>
                    <tr>
                        <td>
                            <label for="monthFilter" ><b>Month :</b> </label>
                            <select id="monthFilter" name="monthFilter" >
                                <%--<option value="" >-- Select Month --</option>--%>
                                <c:forEach items="${monthsMap}" var="mnth" varStatus="mnthitr">
                                    <c:set var="selMnth" value="" ></c:set>
                                    ${mnth.key==accrualData.monthFilter}
                                    <c:if test="${mnth.key==accrualData.monthFilter}">
                                        <c:set var="selMnth" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option  ${selMnth} value="${mnth.key}">${mnth.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="yearFilter" ><b>Year :</b> </label>
                            <select id="yearFilter" name="yearFilter" >
                                <%--<option value="" >-- Select Year --</option>--%>
                                <c:forEach items="${yearsMap}" var="yr" varStatus="yritr">
                                    <c:set var="selYr" value="" ></c:set>
                                    <c:if test="${yr.key==accrualData.yearFilter}">
                                        <c:set var="selYr" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selYr} value="${yr.key}">${yr.value}</option>
                                </c:forEach>
                            </select>

                        </td>
<!--                        <td>
                            <label for="sbuFilter" ><b>SBU :</b> </label>
                            <select id="sbuFilter" name="sbuFilter" >
                                <option value="All" >-- Select SBU --</option>
                                <c:forEach items="${sbuMap}" var="sbu" varStatus="sbuitr">
                                    <c:set var="selSbu" value="" ></c:set>
                                    <c:if test="${sbu.key==accrualData.sbuId}">
                                        <c:set var="selSbu" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selSbu} value="${sbu.key}">${sbu.value}</option>
                                </c:forEach>
                            </select>
                        </td>-->
                    </tr>
                    <tr>
                        <td> <label for="reportFilter" ><b>Report :</b> </label>
                            <select id="reportFilter" name="reportFilter" >
                                <option value=" " >-- Select Report --</option>
                                <c:forEach items="${reportNameMap}" var="reportNamelist" varStatus="reportNameListitr">
                                    <c:set var="selReport" value="" ></c:set>
                                    <c:if test="${reportNamelist.configkey==accrualData.reportFilter}">
                                        <c:set var="selReport" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selReport} value="${reportNamelist.configkey}"> ${reportNamelist.configValue} </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td> <center> <input class="gobutton" onclick="submitForm()" style="height: 23px;" type="button"  value="Go"/> </center> 
                    <center> <input type="button" align="middle" style="float: none" value="Export" onclick="exportToExcel()" class="exportbutton" /> </center> </td>
                    </tr>
                </table>
            </div>
        </form>
        <div id="datadisplay" style="overflow-x:scroll ">
            <table>
                <thead>
                <th>
                    Employee Number
                </th>
                <th>
                    Employee Name
                </th>
                <th>
                    RM
                </th>
                <th>
                    Employment Status
                </th>
                <th>
                    Project Code
                </th>
                <th>
                    Phase
                </th>
                <th>
                    SBU
                </th>
                <th>
                    Sub SBU
                </th>
                <th>
                    1
                </th>
                <th>
                    2
                </th>
                <th>
                    3
                </th>
                <th>
                    4
                </th>
                <th>
                    5
                </th>
                <th>
                    6
                </th>
                <th>
                    7
                </th>
                <th>
                    8
                </th>
                <th>
                    9
                </th>
                <th>
                    10
                </th>
                <th>
                    11
                </th>
                <th>
                    12
                </th>
                <th>
                    13
                </th>
                <th>
                    14
                </th>
                <th>
                    15
                </th>
                <th>
                    16
                </th>
                <th>
                    17
                </th>
                <th>
                    18
                </th>
                <th>
                    19
                </th>
                <th>
                    20
                </th>
                <th>
                    21
                </th>
                <th>
                    22
                </th>
                <th>
                    23
                </th>
                <th>
                    24
                </th>
                <th>
                    25
                </th>
                <th>
                    26
                </th>
                <th>
                    27
                </th>
                <th>
                    28
                </th>
                <th>
                    29
                </th>
                <th>
                    30
                </th>
                <th>
                    31
                </th>
                <th>
                    Worked Hours
                </th>
                <th>
                    Approved Hours
                </th>
                </thead>
                <tbody>
                    <c:if test="${fn:length(filterData)!=0}">
                        <c:forEach items="${filterData}" var="filterData" varStatus="rpritr">
                            <tr>
                                <td>
                                    ${filterData.employeeNumber} 
                                </td>
                                <td>
                                    ${filterData.employeeName} 
                                </td>
                                <td>
                                    ${filterData.rm} 
                                </td>
                                <td>
                                    ${filterData.employmentStatus} 
                                </td>
                                <td>
                                    ${filterData.projectCode} 
                                </td>
                                <td>
                                    ${filterData.phase} 
                                </td>
                                <td>
                                    ${filterData.sbu} 
                                </td>
                                <td>
                                    ${filterData.subSbu} 
                                </td>
                                <td>
                                    ${filterData.day1}
                                </td>
                                <td>
                                    ${filterData.day2}  
                                </td>
                                <td>
                                    ${filterData.day3}
                                </td>
                                <td>
                                    ${filterData.day4}
                                </td>
                                <td>
                                    ${filterData.day5}
                                </td>
                                <td>
                                    ${filterData.day6}
                                </td>
                                <td>
                                    ${filterData.day7}
                                </td>
                                <td>
                                    ${filterData.day8}
                                </td>
                                <td>
                                    ${filterData.day9}
                                </td>
                                <td>
                                    ${filterData.day10}
                                </td>
                                <td>
                                    ${filterData.day11}
                                </td>
                                <td>
                                    ${filterData.day12}
                                </td>
                                <td>
                                    ${filterData.day13}
                                </td>
                                <td>
                                    ${filterData.day14}
                                </td>
                                <td>
                                    ${filterData.day15}
                                </td>
                                <td>
                                    ${filterData.day16}
                                </td>
                                <td>
                                    ${filterData.day17}
                                </td>
                                <td>
                                    ${filterData.day18}
                                </td>
                                <td>
                                    ${filterData.day19}
                                </td>
                                <td>
                                    ${filterData.day20}
                                </td>
                                <td>
                                    ${filterData.day21}
                                </td>
                                <td>
                                    ${filterData.day22}
                                </td>
                                <td>
                                    ${filterData.day23}
                                </td>
                                <td>
                                    ${filterData.day24}
                                </td>
                                <td>
                                    ${filterData.day25}
                                </td>
                                <td>
                                    ${filterData.day26}
                                </td>
                                <td>
                                    ${filterData.day27}
                                </td>
                                <td>
                                    ${filterData.day28}
                                </td>
                                <td>    
                                    ${filterData.day29}
                                </td>
                                <td>
                                    ${filterData.day30}
                                </td>
                                <td>
                                    ${filterData.day31}
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${filterData.worked_hours != null}">
                                            ${filterData.worked_hours} 
                                        </c:when>
                                        <c:otherwise>
                                            0.00
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${filterData.approved_hours != null}">
                                            ${filterData.approved_hours} 
                                        </c:when>
                                        <c:otherwise>
                                            0.00
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:if>
                    <c:if test="${fn:length(filterData)==0}">
                        <tr class="row-odd">
                            <td colspan="48" style="text-align: center;">
                                No data to display
                            </td>
                        </tr>
                    </c:if>
                </tbody>
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