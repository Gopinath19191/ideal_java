<%-- 
    Document   : benchReport
    Created on : Feb 20, 2012, 11:51:37 AM
    Author     : 14619
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="header.jsp" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bench Reports</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
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
            $('#reportPage').attr("action", "getBenchReportXL.htm");
            document.reportPage.submit();
        }
        function getFilterList(){
            $('#reportPage').attr("action", "benchReport.htm");
            document.reportPage.submit();
            startLoading();
        }
        $(document).ready(function(){
            $("#from_date").datepicker({changeMonth: true, changeYear: true, disabled : true, dateFormat: 'yy-mm-dd' });
            $("#to_date").datepicker({changeMonth: true, changeYear: true, disabled : true, dateFormat: 'yy-mm-dd' });
        });
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Bench Report Summary
                </div>
            </div>

            <div class="tabletools" >
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <table id="searchForm">
                        <tbody>
                            <tr>
                                <td width="150px">
                                    <label for="sbuFilter" ><b>SBU :</b> </label>
                                    <select id="sbuFilter" name="sbuFilter" onchange="getSubSbu(this.value);" class="textbox_new" style="width: 145px;">
                                        <option value="All" >-- All --</option>
                                        <c:forEach items="${sbuMap}" var="sbu" varStatus="sbuitr">
                                            <c:set var="selSbu" value="" ></c:set>
                                            <c:if test="${sbu.key==filterData.sbuFilter}">
                                                <c:set var="selSbu" value="selected='selected'" ></c:set>
                                            </c:if>
                                            <option ${selSbu} value="${sbu.key}">${sbu.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td id="subPractice" width="150px">
                                    <label for="subSbu" ><b>Sub SBU</b></label>
                                    <select name="subSbu" id="subSbu" class=" textbox_new"   onchange="setSbu(this.value,'sbuFilter');" style="width: 145px;" >
                                        <option  value="All">--Select--</option>
                                        <c:forEach items="${subSbu}" var="subSbu" varStatus="i">
                                            <option  value="${subSbu.id}" ${subSbu.id eq filterData.subSbu ? 'selected="selected"':''}>${subSbu.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td width="150px">
                                    <label for="bandFilter" ><b>Band :</b> </label>
                                    <select id="bandFilter" name="bandFilter" class="textbox_new" style="width: 145px;">
                                        <option value="" >-- All --</option>
                                        <c:forEach items="${bandMap}" var="band" varStatus="banditr">
                                            <c:if test="${band.key eq filterData.bandFilter}">
                                            <c:set var="selBand" value="selected" ></c:set>
                                            </c:if>
                                            <c:if test="${band.key ne filterData.bandFilter}">
                                            <c:set var="selBand" value="" ></c:set>
                                            </c:if>
                                            <option ${selBand} value="${band.key}" >${band.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                               <td style="padding-left: 25px;" >
                                    <input class="gobutton" align="middle" onclick="getFilterList()" type="submit" value="Go"/>
                                    <input class="exportbutton" align="middle" type="button" style="margin-left: 7px;" onclick="getExcelReport()" value="Export"/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
            <div id="datadisplay" style="overflow-x:scroll;">
                <table style="font-size: 10px;">
                    <tbody>
                    <th>Employee Number</th>
                    <th>Employee Name</th>
                    <th>Employee Status</th>
                    <th>RM Name</th>
                    <th>Joining Date</th>
                    <th>SBU</th>
                    <th>Sub SBU</th>
                    <th>Band</th>
                    <th>DT Exp(YRS)</th>
                    <!--                   <th>Last Allocated Date</th>-->
                    <th>Last Billed Date</th>
                    <th>Last Billed hrs</th>
                    <th>Last Project Details</th>
                    <th>Bench Days</th>
                    </tbody>
                    <tbody>
                        <c:if test="${fn:length(bench)>0}">
                        <% int i = 0;
                            int s = 0;
                        %>
                        <c:forEach items="${bench}" var="item">
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
                                    ${item.empStatus}
                                </td>
                                <td>
                                    ${item.rmName}
                                </td>
                                <td>
                                    ${item.joiningDate}
                                </td>
                                <td>
                                    ${item.sbu}
                                </td>
                                <td>
                                    ${item.subSbu}
                                </td>
                                <td>
                                    ${item.band}
                                </td>
                                <td>
                                    ${item.dtExp}
                                </td>
                                <!--                                    <td>
                                ${item.lastAllocatedDate}
                            </td>-->
                                <td>
                                    ${item.lastBilledDate}
                                </td>
                                <td>
                                    ${item.lastBilledHrs}
                                </td>
                                <td>
                                    ${item.lastProjectId}
                                </td>
                                <td>
                                    ${item.benchDays}
                                </td>
                            </tr>
                            <% i = i + 1;%>
                        </c:forEach>
                        </c:if>
                            <c:if test="${fn:length(bench)==0}">
                              <tr  class="row-odd">
                                <td colspan="13" style="text-align: center;">
                                    No data to display
                                </td>
                            </tr>
                            </c:if>
                    </tbody>
                    </tbody>
                </table>
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
