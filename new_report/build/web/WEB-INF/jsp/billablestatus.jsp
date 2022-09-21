<%-- 
    Document   : billablestatus
    Created on : Oct 13, 2011, 5:20:42 PM
    Author     : 14053
--%>
<%@include file="header.jsp" %>
<head>
    <title>Billable status report</title>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>--%>
    <%--<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-ui-1.8.16.custom.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui-1.8.16.custom.css" type="text/css"/>--%>
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

        <%--  $(document).ready(function() {
                $( ".jQcalendarStDate").datepicker({
                  changeMonth: true,
                changeYear: true,
                  dateFormat:"dd-M-yy",
                  altField: "#startDate",
                  altFormat: "yy-m-dd"
                  }
              );
                $(".jQcalendarEdDate").datepicker({
                  changeMonth: true,
                changeYear: true,
                  dateFormat:"dd-M-yy",
                  minDate:$(".jQcalendarStDate").val(),
                  altField: "#endDate",
                  altFormat: "yy-m-dd"
                  }
                  );

                $( ".toDate").datepicker( "option", "dateFormat", $( this ).val() );
        });--%>
            function submitForm(){
                document.getElementById("reportForm").action="billablereport.htm";
                document.reportForm.submit();
                startLoading();
            }
            function exportToExcel(){

                document.getElementById("reportForm").action="billableexport.htm";
                document.reportForm.submit();
            }
    </script>
</head>
<body onload="">
    <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Billable Status
            </div>
        </div>

        <form name="reportForm" id="reportForm" action="billablereport.htm" method="POST">

            <div class="tabletools">
                <table id="searchForm">
                    <tr>

                        <td width="33%">
                            <label for="sbuFilter" ><b>SBU : </b></label>
                            <select id="sbuFilter" name="sbuFilter" onchange="submitForm()" class="textbox_new">
                                <option value="" >-- All --</option>
                                <c:forEach items="${sbuMap}" var="sbu" varStatus="sbuitr">
                                    <c:set var="selSbu" value="" ></c:set>
                                    <c:if test="${sbu.key==filterData.sbuFilter}">
                                        <c:set var="selSbu" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selSbu} value="${sbu.key}">${sbu.value}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td width="33%">
                            <label for="monthFilter" ><b>Joined Month :</b> </label>
                            <select id="monthFilter" name="monthFilter" onchange="submitForm()" class="textbox_new">
                                <option value="" >-- All --</option>
                                <c:forEach items="${joinedMonthsMap}" var="mnth" varStatus="mnthitr">
                                    <c:set var="selMnth" value="" ></c:set>
                                    <c:if test="${mnth.key==filterData.monthFilter}">
                                        <c:set var="selMnth" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option  ${selMnth} value="${mnth.key}">${mnth.value}</option>
                                </c:forEach>
                            </select>
                        </td>

                        <td width="33%">
                            <label for="yearFilter" ><b>Joined Year : </b></label>
                            <select id="yearFilter" name="yearFilter" onchange="submitForm()" class="textbox_new">
                                <option value="" >-- All --</option>
                                <c:forEach items="${joinedYearsMap}" var="yr" varStatus="yritr">
                                    <c:set var="selYr" value="" ></c:set>
                                    <c:if test="${yr.key==filterData.yearFilter}">
                                        <c:set var="selYr" value="selected='selected'" ></c:set>
                                    </c:if>
                                    <option ${selYr} value="${yr.key}">${yr.value}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><center><input type="button" align="middle" style="float: none" value="Export" onclick="exportToExcel()" class="exportbutton" /> </center> </td>
                    </tr>
                    <%--  <tr>

                        <td width="33%">
                            <label for="startDate" >From Date : </label>
                            <input type="text" class="jQcalendarStDate" >
                            <input type="hidden" id="startDate" name="startDate"  >
                        </td>
                        <td width="33%">
                            <label for="endDate" >To Date : </label>
                            <input type="text" class="jQcalendarEdDate" >
                            <input type="hidden" id="endDate" name="endDate"  >
                        </td>
                    </tr>--%>
                </table>
            </div>
            <%--<input type="text" value="${EMP_ID}" >--%>
        </form>

        <div id="datadisplay">

            <table>
                <thead>
                <th>
                    Emp Id
                </th>
                <th>
                    Employee Name
                </th>
                <th>
                    Emp Status
                </th>
                <th>
                    PM Name
                </th>
                <th width="90px">
                    Joined Date
                </th>
                <th width="90px">
                    Released Date
                </th>
                <th>
                    SBU
                </th>
                <th>
                    Sub SBU
                </th>
                <th>
                    Project Code
                </th>
                <th>
                    Project Name
                </th>
                <th>
                    Customer Name
                </th>
                <th>
                    Billable Status
                </th>
                </thead>
                <c:if test="${fn:length(billableData)>0}">
                    <c:forEach items="${billableData}" var="rprt" varStatus="rpritr">
                        <c:if test="${rpritr.index%2 ==0}">
                            <c:set var="rowClass" value="row-odd"></c:set>
                        </c:if>
                        <c:if test="${rpritr.index%2!=0}">
                            <c:set var="rowClass" value="row-even"></c:set>
                        </c:if>
                        <tr class="${rowClass}">
                            <td>
                                ${rprt.empNumber}
                            </td>
                            <td>
                                ${rprt.empName}
                            </td>
                            <td>
                                ${rprt.empStatus}
                            </td>
                            <td>
                                ${rprt.pmName}
                            </td>
                            <td>
                                ${rprt.joinedDate}
                            </td>
                            <td>
                                ${rprt.releasedDate}
                            </td>
                            <td>
                                ${rprt.sbuName}
                            </td>
                            <td>
                                ${rprt.subSbu}
                            </td>
                            <td>
                                ${rprt.projectCode}
                            </td>
                            <td>
                                ${rprt.projectName}
                            </td>
                            <td>
                                ${rprt.custName}
                            </td>
                            <td>
                                ${rprt.billStatus}
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </table>
            <c:if test="${fn:length(billableData)==0}">
                <table>
                    <tr class="row-odd">
                        <td colspan="12" style="text-align: center;">
                            No data to display
                        </td>
                    </tr>

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