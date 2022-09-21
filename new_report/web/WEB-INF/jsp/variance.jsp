<%@include file="header.jsp" %>
<html>
    <head>
        <title>PM Variance Report </title>
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
            
            $('#variancePage').attr("action", "getVarianceXL.htm");
            
            document.variancePage.submit();
        }
        function getFilterList(){
            $('#variancePage').attr("action", "variance.htm");
//            if($("#employee_search").val() == 'Search by Employee Number or First Name or Last name'){
//                document.getElementById("employee_id").value = "";
//            }
            document.variancePage.submit();
            startLoading();
        }
        //var data = [ {text:'Link A', url:'ajaxsearch.htm'}, {text:'Link B', url: '/page2'} ];
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
            //$("#employee_id").autocompleteArray(["Allen","Albert","Alberto","Alladin"]);
        });
        function getEmpList(prjId){
    
    $.ajax({
        type: 'POST',
        url: 'getEmployeeList.htm?prjId='+prjId,
        dataType: "html",  
        success: function(response) {
            $('#empName').html(response);
        }
    }); 
}
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                   PM Variance Report
                </div>
            </div>
            <div class="tabletools">
                <form action="#" name="variancePage" method="post" id="variancePage">
                    <table id="searchForm">
                 <tbody>
                   <tr>
                    <input type="hidden" id="prjId" name="prjId" value="${requsetorDTO.projectId}" />
                    
                    <tr>
                     <td width="12%" style="color: #666;"><b>Project Name</b><span style="color:red;">*</span></td>
                    <td><select id="projectName" name="projectName" style="margin-top: 5px; width: 150px;" onchange="getEmpList(this.value);">

                            <option value="">-Select Project -</option>
                            <c:forEach items="${projectList}" var="prjList" varStatus="i">
                                <option value="${prjList.projectId}" ${prjList.projectId eq requsetorDTO.projectId ? 'selected="selected"' : ''}>${prjList.projectCode}--${prjList.projectName}</option>
                            </c:forEach>
                            <option value="Non_Project_Activity" ${requsetorDTO.projectId eq 'Non_Project_Activity' ? 'selected="selected"' : ''}>Non Project Activity</option>
                        </select>
                    </td>
                    <td width="10%"></td>
                    <td width="12%" style="color: #666;"><b>Employee Name</b></td>
                    <td><select id="empName" name="empName" style="margin-top: 5px;width: 150px;">

                            <option value="">- Select Employee  -</option>
                            <c:forEach items="${employeeList}" var="empList" varStatus="i">
                                <option title="${empList.employeeNumber}  ${empList.empName}" value="${empList.empId}" ${requsetorDTO.empId eq empList.empId ? 'selected="selected"' : ''}>${empList.employeeNumber}  ${empList.empName}</option>
                            </c:forEach>
                        </select>
                    </td>
                       </tr>     
                       <tr>
                       <td style="color: #666;"><b>Month :</b></td>
                                <td width="18%">
                                    <select id="filter_month" name="filter_month">
                                        
                                        <c:forEach items="${monthsList}" var="month" varStatus="monthitr">
                                            <c:set var="selMonth" value="" ></c:set>
                                            <c:if test="${month.key==filterData.filter_month}">
                                                <c:set var="selMonth" value="selected='selected'" ></c:set>
                                            </c:if>
                                            <option ${selMonth} value="${month.key}">${month.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                 <td width="10%"></td>
                                <td style="color: #666;"><b>Year :</b> </td>
                                <td width="18%">
                                    <select id="filter_year" name="filter_year">
                                        <!--<option value="All">--Select Year--</option>-->
                                        <c:forEach items="${yearsList}" var="year" varStatus="yearitr">
                                            <c:set var="selYear" value="" ></c:set>
                                            <c:if test="${year.key==filterData.filter_year}">
                                                <c:set var="selYear" value="selected='selected'" ></c:set>
                                            </c:if>
                                            <option ${selYear} value="${year.key}">${year.value}</option>
                                        </c:forEach>
                                    </select>
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
                
                <table style="font-size: 10px;">
                   
                    <tbody>
                        
                        <c:if test="${fn:length(variance)>0}">
                             <tbody>
                    <th>Project Name</th>
                      <th>Employee Name</th>
                    <th> Period</th>
<!--                    <th> Attendance Hours</th>
                    <th> Regularization Hours</th>-->
                    <th>Total Worked Hours</th>
                    <th> Total Approved Hours</th>
                    <th>Total Accrued Hours</th>
                    </tbody>
                            <% int i = 0;
                                int s = 0;
                            %>
                            <c:forEach items="${variance}" var="item">
                              
                                <% s = i % 2;
                                    if (s == 0) {%>
                                <tr class="row-odd">
                                    <% } else {%>
                                <tr class="row-even">
                                    <%}%>
                                     <td>${item.project_id}</td>
                                    <td>${item.employeeNumber}-${item.employeeName}</td>
                                    <td>${item.monthName}</td>
<!--                                     <td>--</td>
                                     <td>--</td>-->
                                     <td>${item.totalWorkedHours}</td>
                                     <td>${item.totalApprovedHours}</td>
                                     <td>${item.totalAccruedHours}</td>
                                </tr>
                                <% i = i + 1;%>
                            </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(variance)==0}">
                            <table>
                    <th>Project Name</th>
                    <th> Period</th>
<!--                    <th> Attendance Hours</th>
                    <th> Regularization Hours</th>-->
                    <th>Total Worked Hours</th>
                    <th> Total Approved Hours</th>
                    <th>Total Accrued Hours</th>
                   
                            <tr class="row-odd">
                                <td colspan="7" style="text-align: center;">
                                    No data to display
                                </td>
                            </tr>
                             </table>
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
