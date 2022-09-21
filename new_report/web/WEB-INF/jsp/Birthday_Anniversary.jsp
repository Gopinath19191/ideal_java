<%--
    Document   : myjsp
    Created on : Apr 10, 2013, 10:17:36 AM
    Author     : 9000337
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <%@include file="header.jsp" %>
    <html>
        <head>
            <title>Employee Birthday Report </title>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.datepick.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.core.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.ui.widget.js"></script>
            <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
            <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/jquery.datepick.css" />
        </head>
        <style type="text/css">
            #loadingDiv img{ border: none; }
            #loadingDiv{ opacity: 0.8;filter: alpha(opacity = 80); ZOOM: 1}
        </style>
        <script type="text/javascript">

            $(document).ready(function(){
                $("#reportPage").validate();
            });
            function getExcelReport(){
                if($('.baformSelectedDate').val()==''){
                    alert("Please select fromdate to export");
                    return false;
                }
                if($('.batoSelectedDate').val()==''){
                    alert("Please select todate to export");
                    return false;
                }
                if( $('#birthDay').is(":not(:checked)") && $('#anniversary').is(":not(:checked)") ) {
                    alert("Please check the checkbox to continue export");
                    return false;
                }else{
                    $('#reportPage').attr("action", "excelexportBirthday_Anniversary.htm");
                    document.reportPage.submit();
                }
            }
            function getFilterList(){
                if( $('#birthDay').is(":not(:checked)") && $('#anniversary').is(":not(:checked)") ) {
                    alert("Please check the checkbox to continue");
                    return false;
                }else{
                    $('#reportPage').attr("action", "getBirthdayAnniversary.htm");
                    document.reportPage.submit();
                }
                startLoading();
            }
            $(document).ready(function(){
                $("#baformSelectedDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    disabled : true,
                    dateFormat: 'dd-mm-yy',
                    onClose: function(dateText) {
                        $('#batoSelectedDate').datepicker("option","minDate", new Date(dateText.split("-")[2],dateText.split("-")[1]-1,dateText.split("-")[0]));
                    }});
            });

            $(document).ready(function(){
                $("#batoSelectedDate").datepicker({
                    changeMonth: true,
                    changeYear: true,
                    disabled : true,
                    dateFormat: 'dd-mm-yy',
                    onClose: function( dateText ) {
                        $( "#baformSelectedDate" ).datepicker( "option", "maxDate", new Date(dateText.split("-")[2],dateText.split("-")[1]-1,dateText.split("-")[0]));
                    }

                });
            });
        </script>

        <body>
            <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
            <div id="container">
                <div class="container_inner">
                    <div class="page_heading">
                        Employee Birthday and Anniversary Report
                    </div>
                </div>
                <div class="tabletools">
                    <form action="#" name="reportPage" method="post" id="reportPage" >
                        <table id="searchForm">
                            <tbody>
                                <tr>
                                    <td>
                                        Form date
                                    </td>
                                    <td>
                                        <input type="text" value="${filterDTO.fromSelectedDate}"  name="fromSelectedDate" class="baformSelectedDate required" id="baformSelectedDate" readonly/>
                                    </td>
                                    <td>
                                        To date
                                    </td>
                                    <td>
                                        <input type="text" value="${filterDTO.toSelectedDate}"  name="toSelectedDate" class="batoSelectedDate" id="batoSelectedDate" readonly/>
                                    </td>
                                    <td>
                                        <c:forEach items="${filterDTO.birthday_Anniversary}" var="i" >
                                            <c:if test="${i=='B'}">
                                                <c:set var="selB" value="checked='checked'" ></c:set>
                                            </c:if>
                                            <c:if test="${i=='A'}">
                                                <c:set var="selA" value="checked='checked'" ></c:set>
                                            </c:if>


                                        </c:forEach>
                                        <input type="checkbox" ${selB} id="birthDay"  name="birthday_Anniversary"  class="baFlagSelect" value="B" >BIRTHDAY
                                        <input type="checkbox" ${selA} id="anniversary" name="birthday_Anniversary"  class="baFlagSelect" value="A">ANNIVERSARY
                                    </td>
                                    <td>
                                        <input class="gobutton" align="middle" onclick="return getFilterList()"  style="height: 23px;" type="submit" value="Go"/>
                                    </td>
                                    <td>
                                        <input class="exportbutton" align="middle" type="button" style="margin-left: 20px;" onclick="return getExcelReport()" value="Export"/>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </form>
                </div>
                <div id="datadisplay">
                    <table style="font-size: 10px;">
                        <tbody>
                        <th>Employee ID</th>
                        <th>Employee Name</th>
                        <th>SBU</th>
                        <th>Work_email_address</th>
                        <th>Birthday</th>
                        <th>DOJ</th>
                        <th>Anniversary</th>
                        <th>Status</th>
                        </tbody>
                        <tbody>
                            <c:if test="${fn:length(birthdaylist)>0}">
                                <% int i = 0;
                                            int s = 0;%>

                                <c:forEach items="${birthdaylist}" var="item" varStatus="rpritr">
                                    <% s = i % 2;
                                                if (s == 0) {%>
                                    <tr class="row-odd">
                                        <% } else {%>
                                    <tr class="row-even">

                                        <%}%>
                                    <tr>
                                        <td>${item.employee_number}</td>
                                        <td>${item.employeeName}</td>
                                        <td>${item.sbu}</td>
                                        <td>${item.work_Email_Address}</td>
                                        <td>${item.birthDate}</td>
                                        <td>${item.doj}--</td>
                                        <td>${item.anniversary}--</td>
                                        <td>${item.status}</td>
                                    </tr>
                                    <% i = i + 1;%>
                                </c:forEach>
                            </c:if>
                            <c:if test="${fn:length(anniversarylist)>0}">
                                <% int j = 0;
                                            int t = 0;%>

                                <c:forEach items="${anniversarylist}" var="item" varStatus="rpritr">
                                    <% t = j % 2;
                                                if (t == 0) {%>
                                    <tr class="row-odd">
                                        <% } else {%>
                                    <tr class="row-even">

                                        <%}%>
                                    <tr>
                                        <td>${item.employee_number}</td>
                                        <td>${item.employeeName}</td>
                                        <td>${item.sbu}</td>
                                        <td>${item.work_Email_Address}</td>
                                        <td>${item.birthDate}--</td>
                                        <td>${item.doj}</td>
                                        <td>${item.anniversary}years</td>
                                        <td>${item.status}</td>
                                    </tr>
                                    <% j = j + 1;%>
                                </c:forEach>
                            </c:if>
                            <c:if test="${fn:length(birthdaylist)==0&&fn:length(anniversarylist)==0}">
                                <tr class="row-odd">
                                    <td colspan="7" style="text-align: center;">
                                        No data to display
                                    </td>
                                </tr>
                            </c:if>

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