<%@include file="header.jsp" %>
<html>
    <head>
        <title>Project List</title>
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
            $('#reportPage').attr("action", "getDataXL.htm");
            document.reportPage.submit();
        }
        function getFilterList(){
            $('#reportPage').attr("action", "filter.htm");
            document.reportPage.submit();
            startLoading();
        }
        
        function getSubSbu1(value)
        {
            
            var subSbu;
            if(value=="DTS" || value=="5")
            {
                subSbu="5";
            }else if(value=="PES" || value=="2")
            {
                subSbu="2";
            }
            //    else if(value=="ERP" || value=="11")
            //    {
            //        subSbu="11";
            //    }
            else if(value=="All" || value=='')
            {
                subSbu="2,5";
            }
            $.ajax(
            {
                url:"getSubPracticeGroup.htm?id="+subSbu,
                dataType: "html",
                success:function(data){
                    $('#subSbu').length=0;
                    $('#subSbu').html(data);
                }
            });
        }
    </script>
    <style>
        #datadisplay table tr td {
            padding: 1px 0px;
            vertical-align: top;
        }

    </style>
    <body>
        <div id="loadingDiv" style="position:absolute;width:100%;height:100%;background-color:#777777;display: block; top: 0pt; left: 0pt; "><div  style="z-index: 90;position: absolute; z-index: 150; top: 248px; left: 610px;text-align:center"><img src="${pageContext.request.contextPath}/css/images/loading.gif" id="loadingImage"><br>Loading Page...Please wait</div></div>
        <div id="container">
            <div class="container_inner">
                <div class="page_heading">
                    Project Reports
                </div>
            </div>

            <div class="tabletools" >
                <form action="#" name="reportPage" method="post" id="reportPage">
                    <table id="searchForm">
                        <tbody>
                            <tr>
                                <td>
                                    <label for="sbu" ><b>SBU</b></label>
<!--                                    <select name="sbu" id="sbu" class="selectbox textbox_new" style="width: 95px;" onchange="getSubSbu1(this.value);">
                                        <c:if test="${result.sbu=='All'}">
                                            <c:set var="selAll" value="selected='selected'"></c:set>
                                        </c:if>
                                        <c:if test="${result.sbu=='PES'}">
                                            <c:set var="selEngg" value="selected='selected'"></c:set>
                                        </c:if>
                                        <c:if test="${result.sbu=='DTS'}">
                                            <c:set var="selIt" value="selected='selected'"></c:set>
                                        </c:if>
                                        <option ${selAll} value="All">All</option>
                                        <option ${selEngg} value="PES">PES</option>
                                        <option ${selIt} value="DTS">DTS</option>
                                    </select>-->
                                    <select name="sbu" id="sbu" class="selectbox textbox_new" style="width: 95px;" onchange="getSubSbu1(this.value);">
                                        <option  value="All">All</option>
                                        <c:forEach items="${sbu}" var="Sbu" varStatus="i">
                                            <option  value="${Sbu.name}" ${Sbu.name eq result.sbu ? 'selected="selected"':''}>${Sbu.name}</option>
                                        </c:forEach>
                                    </select>


                                    <label for="subSbu" style="width: 60px"><b>Sub SBU</b></label>
                                    <select name="subSbu" id="subSbu" class="selectbox"  style="width: 210px;" onchange="setSbu(this.value,'sbu');" >
                                        <option  value="All">--Select--</option>
                                        <c:forEach items="${subSbu}" var="subSbu" varStatus="i">
                                            <option  value="${subSbu.id}" ${subSbu.id eq result.subSbu ? 'selected="selected"':''}>${subSbu.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <!--                                <td><label>Project Code</label>
                                                                    <input type="text" name="projectcode" id="projectcode" value="${result.projectcode}"/></td>-->
                            </tr>
                            <tr><td><label><b>Project Status</b></label>
                                    <c:forEach items="${result.status}" var="i">
                                        <c:if test="${i=='e'}">
                                            <c:set var="selE" value="checked='checked'" ></c:set>
                                        </c:if>
                                        <c:if test="${i=='y'}">
                                            <c:set var="selY" value="checked='checked'" ></c:set>
                                        </c:if>
                                        <c:if test="${i=='h'}">
                                            <c:set var="selH" value="checked='checked'" ></c:set>
                                        </c:if>
                                        <c:if test="${i=='c'}">
                                            <c:set var="selC" value="checked='checked'" ></c:set>
                                        </c:if>
                                        <c:if test="${i=='d'}">
                                            <c:set var="selD" value="checked='checked'" ></c:set>
                                        </c:if>
                                    </c:forEach>
                                    <input type="checkbox" ${selE} name="status" style="width: 20px" id="active"  value="e">
                                    Active

                                    <input type="checkbox" ${selY} name="status" style="width: 20px;margin-left: 73px;" id="yettostart" value="y">Yet To Start

                                    <input type="checkbox" ${selH} name="status" id="hold" style="width: 20px;margin-left: 73px;" value="h">On Hold
                                    <input type="checkbox" ${selD} name="status" id="delivered" style="width: 20px;margin-left: 73px;" value="d">Delivered
                                    <input type="checkbox" ${selC} name="status" id="closed" style="width: 20px;margin-left: 73px;" value="c">Closed </td></tr>

                            <tr>
                                <td colspan="2"> <label><b>Delivery Ownership</b></label>
                                    <c:forEach items="${result.ownership}" var="itm">
                                        <c:if test="${itm=='df'}">
                                            <c:set var="selDM" value="checked='checked'" ></c:set>
                                        </c:if>
                                        <c:if test="${itm=='cr'}">
                                            <c:set var="selCM" value="checked='checked'" ></c:set>
                                        </c:if>
                                    </c:forEach>
                                    <input type="checkbox" ${selDM} name="ownership" style="width: 20px" value="df" id="df">Hinduja Tech Managed
                                    <input type="checkbox" ${selCM} name="ownership" style="width: 20px" value="cr" id="cr"> Customer Managed 
                                </td>
                            </tr>

                            <tr><td>
                                    <input class="gobutton" onclick="getFilterList()" type="submit" value="Go"/>
                                    <input class="exportbutton" type="button" style="margin-left: 20px;" onclick="getExcelReport()" value="Export"/>
                                </td>
                            </tr>

                        </tbody></table>
                </form>
            </div>          
            <div id="datadisplay" style="overflow-x: scroll;">
                <table style="font-size: 10px;">
                    <tbody>
                    <th>ID</th>
                    <th>Project Name</th>
                    <th>Start Date</th>
                    <th>End Date</th>
                    <!--<th>Actual Start Date</th>-->
                    <!--<th>Actual End Date</th>-->
                    <th>Customer Name</th>
                    <th>Sales Person</th>
                    <th>PM</th>
                    <th>SBU</th>
                    <th>Sub SBU</th>
                    <th>Type</th>
                    <th>Model</th>
                    <th>Ownership</th>
                    <th>Billable Efforts</th>
                    <th>UOM</th>
                    <!--<th>Actual Efforts</th>-->
                    <!--<th>Actual Efforts UOM</th>-->
                    <th>E.Model</th>
                    <th>Pricing</th>
                    <th>Status</th>
                    <th>Created</th>
                    <th>Closed</th>
                    <th>Phases</th>
                    <th>Tasks</th>
                    <th>Team Count</th>
                    </tbody>
                    <tbody>
                        <c:if test="${fn:length(list)>0}">
                            <% int i = 0;
                                int s = 0;
                            %>
                            <c:forEach items="${list}" var="item">
                                <% s = i % 2;
                                    if (s == 0) {%>
                                <tr class="row-odd">
                                    <% } else {%>
                                <tr class="row-even">
                                    <%}%>
                                    <td>
                                        ${item.code}
                                    </td>
                                    <td >
                                        ${item.name}
                                    </td>
                                    <td>
                                        ${item.start_date}
                                    </td>
                                    <td>
                                        ${item.end_date}
                                    </td>
                                    <!--<td>-->
                                        ${item.actual_start_date}
                                    <!--</td>-->
                                    <!--<td>-->
                                        ${item.actual_end_date}
                                    <!--</td>-->
                                    <td>
                                        ${item.customer_name}
                                    </td>
                                    <td>
                                        ${item.salesPerson}
                                    </td>
                                    <td>
                                        ${item.manager}
                                    </td>
                                    <td>
                                        ${item.sbu}
                                    </td>
                                    <td>
                                        ${item.subSbu}
                                    </td>
                                    <td>
                                        ${item.project_type}
                                    </td>
                                    <td>
                                        ${item.billable}
                                    </td>
                                    <td>
                                        ${item.ownership}
                                    </td>
                                    <td>
                                        ${item.billable_efforts}
                                    </td>
                                    <td>
                                        ${item.billing_uom}
                                    </td>
                                    <td>
                                        ${item.model}
                                    </td>
                                    <td>
                                        ${item.pricing}
                                    </td>
                                    <td>
                                        ${item.status}
                                    </td>
                                    <td>
                                        ${item.created}
                                    </td>
                                    <td>
                                        ${item.closed}
                                    </td>
                                    <td>
                                        ${item.phase}
                                    </td>
                                    <td>
                                        ${item.tasks}
                                    </td>
                                    <td>
                                        ${item.team_count}
                                    </td>
                                </tr>
                                <% i = i + 1;%>
                            </c:forEach>
                        </c:if>
                        <c:if test="${fn:length(list)==0}">
                            <tr class="row-odd">
                                <td style="text-align: center;" colspan="22">
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
</html>
