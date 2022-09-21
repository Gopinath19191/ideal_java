<%@include file="header.jsp" %>
<html>
    <head>
        <title>FB Project Completion Percentage</title>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.4.2.min.js"></script>
         <script type="text/javascript" src="${pageContext.request.contextPath}/js/filterOnChange.js"></script>
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
            $('#reportPage').attr("action", "getProjectCompletionXL.htm");
            document.reportPage.submit();
        }
        function getFilterList(){
            $('#reportPage').attr("action", "projectcompletion.htm");
            document.reportPage.submit();
            startLoading();
        }
    </script>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    FB Project Completion Percentage
                </div>
            </div>

            <div class="tabletools" >
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <table id="searchForm">
                        <tbody>
                            <tr>
                                <td>
                                    <label for="sbuFilter"  style="width: 112px;"><b>SBU :</b> </label>
                                    <select id="sbuFilter" name="sbuFilter" onchange="getSubSbu(this.value);">
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
                                <td id="subPractice" >
                                    <label for="subSbu" style="width: 60px"><b>Sub SBU</b></label>
                                    <select name="subSbu" id="subSbu" class="selectbox"  style="width: 210px;" onchange="setSbu(this.value,'sbuFilter');" >
                                        <option  value="All">--Select--</option>
                                        <c:forEach items="${subSbu}" var="subSbu" varStatus="i">
                                            <option  value="${subSbu.id}" ${subSbu.id eq filterData.subSbu ? 'selected="selected"':''}>${subSbu.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <label for="statusFilter" ><b>Status :</b> </label>
                                    <select id="statusFilter" name="statusFilter">
                                        <option value="" >-- All --</option>
                                        <c:forEach items="${statusMap}" var="status" varStatus="statusitr">
                                            <c:set var="selStat" value="" ></c:set>
                                            <c:if test="${status.key==filterData.statusFilter}">
                                                <c:set var="selStat" value="selected='selected'" ></c:set>
                                            </c:if>
                                            <option ${selStat} value="${status.key}">${status.value}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <!--                                <td><label>Project Code</label>
                                                                    <input type="text" name="projectcode" id="projectcode" value="${result.projectcode}"/></td>-->
                           
                      
                                <td>
                                    <input class="gobutton" align="middle" onclick="getFilterList()" type="submit" value="Go"/>
                                    <input class="exportbutton" align="middle" type="button" style="margin-left: 20px;" onclick="getExcelReport()" value="Export"/>
                                </td>
                            </tr>
                        </tbody></table>
                </form>
            </div>
            <div id="datadisplay">
                <table style="font-size: 10px;">
                    <tbody>

                    <th>Code</th>
                    <th>Project Name</th>
                    <th>Planned Start Date</th>
                    <th>Planned End Date</th>
                    <th> SBU </th>
                    <th> Sub SBU </th>
                    <th> Customer Name </th>
                    <th>Status</th>
                    <th> PO Value </th>
                    <th>Accrued So Far</th>
                    <th>Currency</th>
                    <th>Completion(%)</th>

                    </tbody>
                   
                    
                    <tbody>
                          <c:if test="${fn:length(project)!=0}">
                        <% int i = 0;
                            int s = 0;
                        %>
                        <c:forEach items="${project}" var="item">
                            <% s = i % 2;
                                       if (s == 0) {%>
                            <tr class="row-odd">
                                <% } else {%>
                            <tr class="row-even">
                                <%}%>
                                <td>
                                    ${item.code}
                                </td>
                                <td>
                                    ${item.name}
                                </td>
                                <td>
                                    ${item.plannedStartDate}
                                </td>
                                <td>
                                    ${item.plannedEndDate}
                                </td>
                                <td>
                                    ${item.sbu}
                                </td>
                                <td>
                                    ${item.subSbu}
                                </td>
                                <td>
                                    ${item.customerName}
                                </td>
                                <td>
                                    ${item.status}
                                </td>
                                <td>
                                    ${item.poValue}
                                </td>
                                <td>
                                    ${item.accruedSoFar}
                                </td>
                                <td>
                                    ${item.currency}
                                </td>
                                <td>
                                    ${item.completion}
                                </td>
                            </tr>
                            <% i = i + 1;%>
                        </c:forEach>
                    </tbody>
                    </c:if>
                    <c:if test="${fn:length(project)==0}">
                        <tr class="row-odd" >
                            <td colspan="12" style="text-align: center">
                                 No data to display
                            </td>
                        </tr>
                       
                    </c:if>
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
