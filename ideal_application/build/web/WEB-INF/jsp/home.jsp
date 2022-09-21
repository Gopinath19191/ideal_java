<%-- 
    Document   : home
    Created on : Sep 23, 2011, 1:13:24 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Attendance Report ----------</title>
    <style type="text/css">
        #loadingDiv img{ border: none; }
        
        #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}

        #contentWrapper{
            width:90%;
            margin-left:5%;
            border:1px solid #ccc;
            float:left;
        }
        .contentGroup{
            width:30%;
            float:left;
            padding:1%;
        }
        .contentLabel{
            width:49%;
            float:left;
        }
        .contentField{
            width:49%;
            float:left;
        }
        
        #datadisplay{
            overflow-x:scroll;
        }
        #datadisplay table td{
            border:1px solid #ccc;
        }
        #datadisplay table{
            border-collapse: collapse;
        }
        #datadisplay table th.dayClass{
            width:40px;
        }
        /* #datadisplay table th.weekEndClass, */
        #datadisplay table td.weekEndClass{
            background-color: #eee;
            background-image: none;
        }
        #datadisplay table td.effortChanged{
            background-color: #FF8888;
            background-image: none;
        }
        .effortChangedDiv{
            background-color: #FF8888;
            background-image: none;
            width:3px;
            height:7px;
            float:left;
        }
        #effortsLegend{
            margin-left:-310px;
        }
        #datadisplay table tr.even{
            background-color: #D0DEF0;
        }
        #datadisplay table tr.odd{
            background-color: transparent;
        }
    </style>
    <script type="text/javascript">
        function submitForm(){
            document.getElementById("reportForm").action="timesheetreport.htm";
            document.reportForm.submit();
            startLoading();
        }
        function exportToExcel(){
            
            document.getElementById("reportForm").action="excelexport.htm";
            document.reportForm.submit();
        }

    </script>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div>
            <p class="paging_details"><span class="heading">Attendance</span> </p>
        </div>
        <form name="reportForm" id="reportForm" action="timesheetreport.htm" method="POST">
            <%--${filterData.monthFilter}
            ${filterData.yearFilter}
            ${filterData.projectFilter}--%>
            <div class="tabletools1">
              <table>
                    <tr>
                        <td width="33%">
                            <label for="monthFilter" >Month : </label>
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
                        </td><td width="33%">

                            <label for="yearFilter" >Year : </label>
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

                        </td><td width="33%">

                            <label for="projectFilter" >Project : </label>

                            <select id="projectFilter" name="projectFilter" onchange="submitForm()">
                                <option value=" ">-- Select Project --</option>
                                <c:forEach items="${projectList}" var="pjct" varStatus="pjitr">

                         <c:set var="selPjct" value="" ></c:set>
                            <c:if test="${pjct.key==filterData.projectFilter}">
                                <c:set var="selPjct" value="selected='selected'" ></c:set>
                            </c:if>

                        <option ${selPjct} value="${pjct.key}">${pjct.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td> <center> <input type="button" align="middle" style="float: none" value="Export to Excel" onclick="exportToExcel()" class="toolfind" /> </center> </td></tr>
                        <tr>
                            <td colspan="2">
                                * <b>N</b> - Timesheet not Submitted&nbsp;&nbsp;&nbsp;<b>N/A</b> - Not Allocated to Project&nbsp;&nbsp;&nbsp;<b>L</b> - Leave&nbsp;&nbsp;&nbsp;
                            </td>
                            <td>
                                <div id="effortsLegend"><div class="effortChangedDiv"></div>&nbsp;- Efforts modified</div>
                            </td>
                        </tr>
                     </table>
            </div>
            <%--<input type="text" value="${EMP_ID}" >--%>
        </form>

        <div id="datadisplay">
            <c:if test="${fn:length(associateData)>0}">
                <table>
                    <thead>
                    <th>
                        Emp. Id
                    </th>
                    <th>
                        Emp. Name
                    </th>
                    <c:forEach var="day" begin="1" end="${filterData.lastDate}" varStatus="dayitr">
                        <fmt:parseDate  var="dateHeader"  value="${filterData.yearFilter}-${filterData.monthFilter}-${(dayitr.index)}" pattern="yyyy-MM-dd"/>
                        <fmt:formatDate var="dayHeader"  value="${dateHeader}"  pattern="E" />
                        
                        <c:choose>
                            <c:when test="${dayHeader=='Sun' || dayHeader=='Sat'}">
                                <th class="dayClass weekEndClass"> ${dayitr.index} </th>
                            </c:when>
                            <c:otherwise>
                                <th class="dayClass"> ${dayitr.index} </th>
                            </c:otherwise>
                        </c:choose>
                                
                    </c:forEach>
                    <th>
                        Total Hrs Subm.
                    </th>
                    <th>
                        Total Hrs Appr.
                    </th>
                    <th>
                        Accrued effort
                    </th>
                   <%-- <th>
                        Invoiced effort
                    </th>--%>
                    </thead>
                    <c:forEach items="${associateData}" var="rprt" varStatus="rpritr">
                        <c:if test="${rpritr.index%2 ==0}">
                            <c:set var="rowClass" value="even"></c:set>
                        </c:if>
                        <c:if test="${rpritr.index%2!=0}">
                            <c:set var="rowClass" value="odd"></c:set>
                        </c:if>
                        
                        <tr class="${rowClass}">
                            <td>
                                ${rprt.employeeNumber}
                            </td>
                            <td>
                                ${rprt.employeeName}
                            </td>
                            <c:forEach items="${rprt.workDetails}" var="wrkDet" varStatus="wrkitr">
                                <c:if test="${wrkDet.approvType=='FALSE'}">
                                            <c:set var="reducedClass" value="effortChanged"></c:set>
                                </c:if>
                                <c:if test="${wrkDet.approvType=='TRUE'}">
                                            <c:set var="reducedClass" value=""></c:set>
                                </c:if>

                                <fmt:parseDate  var="dateData"  value="${filterData.yearFilter}-${filterData.monthFilter}-${(wrkitr.index)+1}" pattern="yyyy-MM-dd"/>
                                <fmt:formatDate var="dayData"  value="${dateData}"  pattern="E" />
                                <c:choose>
                                    <c:when test="${dayData=='Sun' || dayData=='Sat'}">
                                        <td class="dayClass weekEndClass ${reducedClass}">
                                            ${wrkDet.workedHrs}
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="dayClass ${reducedClass}">
                                            ${wrkDet.workedHrs}
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <td>
                                ${rprt.totalWorkedHrs}
                            </td>
                            <td>
                                ${rprt.totalApprovedHrs}
                            </td>
                            <td>
                                ${rprt.accrEffort}
                            </td>
                            <%--<td>
                                ${rprt.invEffort}
                            </td>--%>
                        </tr>
                    </c:forEach>

                    
                </table>
                </c:if>
            
                <c:if test="${fn:length(associateData)==0}">
                     <table>
                        <thead>
                            <th>
                                No data to display
                            </th>
                        </thead>
                     </table>
                </c:if>
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