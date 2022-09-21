<%-- 
    Document   : timesheetHours
    Created on : 7 May, 2015, 7:25:05 PM
    Author     : 16047
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="header.jsp" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Timesheet Hours Report</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.5.custom.min.js"></script>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.5.custom.css" type="text/css"/>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
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
                overflow-x:scroll;
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
            #reportForm label{
                width: auto;
            }

            .paging {
                margin:10px 0 10px 18px;
                font-size : 11px;
            }

            .paging a {
                border: 1px solid #BBCFEA;
                padding: 3px 5px;
                color : #4884B8;
                text-decoration: none;
                font-weight: bold;
            }

            a.active {
                border: 1px solid #A4A5A5;
                color: #5A5B5B;
                font-weight: bold;
            }

            a.inactive {
                border: 1px solid #DEE8F4;
                color : #A3C5DE;
                font-weight: bold;
            }

        </style>
        <script type="text/javascript">
            function changeProject() {
                $('#nameFilter').find('option').remove()
                $("#nameFilter option").removeAttr("selected");
                var empId = $("#empId").val();
                //alert(empId);
                var projectDept = $("#projectDept").val();
                //alert(projectDept);
                var projectType = $("#projectType").val();
                $.post("getprjajax.htm", {empId: empId, projectDept: projectDept, projectType: projectType},
                function(data) {
                    //alert("muni");
                    var opt = document.createElement("OPTION");
                    opt.text = "--All--";
                    opt.value = "";
                    document.getElementById("nameFilter").options.add(opt);
                    //alert(data)
                    var typ = data.split("|");
                    for (var i = 0; i < typ.length - 1; i = i + 2) {
                        var optn = document.createElement("OPTION");
                        optn.text = $.trim(typ[i + 1]);
                        optn.value = $.trim(typ[(i)]);
                        document.getElementById("nameFilter").options.add(optn);
                    }

                });
            }

            function changeEmployees() {
                $('#employeeNameFilter').find('option').remove()
                $("#employeeNameFilter option").removeAttr("selected");
                var projId = $("#nameFilter").val();
                $.post("getempajax.htm", {id: projId},
                function(data) {
                    var opt = document.createElement("OPTION");
                    opt.text = "--All--";
                    opt.value = "";
                    document.getElementById("employeeNameFilter").options.add(opt);
                    var typ = data.split("|");
                    for (var i = 0; i < typ.length - 1; i = i + 2) {
                        var optn = document.createElement("OPTION");
                        optn.text = $.trim(typ[i + 1]);
                        optn.value = $.trim(typ[(i)]);
                        document.getElementById("employeeNameFilter").options.add(optn);
                    }
                });
            }
            function submitForm() {
                 if ($("#nameFilter").val() == "" || $("#nameFilter").val() == null) {
                   alert("Please select any project.");
                } else {
                document.getElementById("reportForm").action = "timesheethoursreport.htm";
                document.reportForm.submit();
                startLoading();
                }
            }
            function exportToExcel() {
                if ($("#nameFilter").val() == "" || $("#nameFilter").val() == null) {
                    alert("Please select any project.");
                } else {
                    document.getElementById("reportForm").action = "timesheethoursexport.htm";
                    document.reportForm.submit();
                }
            }
        </script>
    </head>
    <body>
<!--        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>-->
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Timesheet Hours Report
                </div>
            </div>
            <form name="reportForm" id="reportForm" action="timesheethoursreport.htm" method="POST">
                <div class="tabletools" >
                    <table>
                        <tr>
                            <td>
                                <input type="hidden" id="page" name="page" value="1" />
                                <input type="hidden" name="empId" id="empId" value="${EMPID}"/>
                                <input type="hidden" name="projectDept" id="projectDept" value="${projectDept}"/>
                                <label for="projectTypeFilter" style="width: 73px;"><b> Project Type:</b></label>
                                <select id="projectType" name="projectType" class="textbox_new" onchange="changeProject();">
                                    <option value="">All</option>
                                    <c:forEach items="${projectTypes}" var="types" varStatus="typesitr">
                                        <c:set var="selType" value=""></c:set>
                                        <c:if test="${types.key==filterData.projectType}">
                                            <c:set var="selType" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selType} value="${types.key}">${types.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="projectFilter" style="width: 73px;"><b> Project Name:</b></label>
    <!--                            <input type="text" id="nameFilter" name="nameFilter" value="${filterData.nameFilter}" size="50"/>-->
                                <select id="nameFilter" name="nameFilter" class="textbox_new" onchange="changeEmployees();">
                                    <option value="" >-- Select --</option>
                                    <c:forEach items="${projects}" var="proj" varStatus="projitr">
                                        <c:set var="selProj" value="" ></c:set>
                                        <c:if test="${proj.projId==filterData.nameFilter}">
                                            <c:set var="selProj" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selProj} value="${proj.projId}">${proj.projName}</option>
                                    </c:forEach>
                                </select>

                            </td>
                            <!--                            <td>
                                                            <label for="sbuFilter" style="width: 73px;"><b>SBU : </b></label>
                                                            <select id="sbuFilter" name="sbuFilter" onchange="getSubSbu(this.value);" class="textbox_new">
                                                                <option value="" >-- Select --</option>
                            <c:forEach items="${sbuMap}" var="sbu" varStatus="sbuitr">
                                <c:set var="selSbu" value="" ></c:set>
                                <c:if test="${sbu.key==filterData.sbuFilter}">
                                    <c:set var="selSbu" value="selected='selected'" ></c:set>
                                </c:if>
                                <option ${selSbu} value="${sbu.key}">${sbu.value}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td id="subPractice" >
                        <label for="subSbu" style="width: 73px;" ><b>Sub SBU</b></label>
                        <select name="subSbu" id="subSbu" class="textbox_new"  onchange="setSbu(this.value, 'sbuFilter');" >
                            <option  value="All">--Select--</option>
                            <c:forEach items="${subSbu}" var="subSbu" varStatus="i">
                                <option  value="${subSbu.id}" ${subSbu.id eq filterData.subSbu ? 'selected="selected"':''}>${subSbu.name}</option>
                            </c:forEach>
                        </select>
                    </td>-->
                            <td>
                                <label for="employeeFilter" style="width: 73px;"><b> Employees:</b></label>
                                <select id="employeeNameFilter" name="employeeNameFilter" class="textbox_new">
                                    <option value="" >-- Select --</option>
                                    <c:forEach items="${employeeList}" var="emp" varStatus="empitr">
                                        <c:set var="selEmp" value="" ></c:set>
                                        <c:if test="${emp.empId==filterData.employeeNameFilter}">
                                            <c:set var="selEmp" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selEmp} value="${emp.empId}">${emp.employeeName}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>


                            <td>
                                <label for="monthFilter" style="width: 73px;"><b>Month :</b> </label>
                                <select id="monthFilter" name="monthFilter" class="textbox_new">
                                    <option value="" >-- Select --</option>
                                    <c:forEach items="${timesheetMonthsMap}" var="mnth" varStatus="mnthitr">
                                        <c:set var="selMnth" value="" ></c:set>
                                        <c:if test="${mnth.key==filterData.monthFilter}">
                                            <c:set var="selMnth" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option  ${selMnth} value="${mnth.key}">${mnth.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <label for="yearFilter" style="width: 73px;"><b>Year :</b> </label>
                                <select id="yearFilter" name="yearFilter" class="textbox_new">
                                    <option value="" >-- Select --</option>
                                    <c:forEach items="${timesheetYearsMap}" var="yr" varStatus="yritr">
                                        <c:set var="selYr" value="" ></c:set>
                                        <c:if test="${yr.key==filterData.yearFilter}">
                                            <c:set var="selYr" value="selected='selected'" ></c:set>
                                        </c:if>
                                        <option ${selYr} value="${yr.key}">${yr.value}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td style="padding-left: 77px"><input type="button" name="submitButton" style="float: none;;margin-right: 15px;" value="Go" onclick="submitForm()" class="gobutton" />
                                <input type="button" name="exportButton" style="float: none;" value="Export" onclick="exportToExcel()" class="exportbutton" /> </td>
                        </tr>
                    </table>
                </div>
            </form>
            <div id="datadisplay">
                <c:if test="${fn:length(timesheetHoursData)>0}">
                    <table style="font-size: 10px;">
                        <thead>
                        <th>
                            Employee Number
                        </th>
                        <th>
                            Employee Name
                        </th>
                        <th>
                            SBU
                        </th>
                        <th>
                            Sub SBU
                        </th>
                        <th>
                            Project Start Date
                        </th>
                        <th>
                            Project End Date
                        </th>
                        <th>
                            Total Worked Hours
                        </th>
                        <th>
                            Total Approved Hours
                        </th>
                        <th>
                            Total Accrued Hours
                        </th>
                        </thead>
                        <c:forEach items="${timesheetHoursData}" var="rprt" varStatus="rpritr">
                            <c:if test="${rpritr.index%2 ==0}">
                                <c:set var="rowClass" value="row-odd"></c:set>
                            </c:if>
                            <c:if test="${rpritr.index%2!=0}">
                                <c:set var="rowClass" value="row-even"></c:set>
                            </c:if>
                            <tr class="${rowClass}">
                                <td>
                                    ${rprt.employeeNumber}
                                </td>
                                <td>
                                    ${rprt.employeeName}
                                </td>
                                <td>
                                    ${rprt.sbu}
                                </td>
                                <td>
                                    ${rprt.subSbu}
                                </td>
                                <td>
                                    ${rprt.startDate}
                                </td>
                                <td>
                                    ${rprt.endDate}
                                </td>
                                <td>
                                    ${rprt.totalWorkedHours}
                                </td>
                                <td>
                                    ${rprt.totalApprovedHours}
                                </td>
                                <td>
                                    ${rprt.totalAccruedHours}
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
                <c:if test="${fn:length(timesheetHoursData)==0}">
                    <table>
                        <tr class="row-odd">
                            <td style="text-align: center;" colspan="16">
                                No data to display
                            </td>
                        </tr>
                    </table>
                </c:if>
                <cn:if test="${paging[0] > 1}">
                    <%@include file="/WEB-INF/jsp/paging.jsp" %>
                </cn:if>
            </div>
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
                $('#reportForm').submit();
            }
        </script>
    </body>
</html>
