<%-- 
    Document   : teamallocationreportList.jsp
    Created on : 4 Mar, 2016, 7:47:49 PM
    Author     : 15332
--%>

<%@include file="header.jsp" %>
<head>
    <title>Project Release Report</title>
    <style type="text/css">
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
        .exportbutton,.gobutton{
            cursor: pointer;
        }
    </style>
    <script type="text/javascript">
        //        function filterRollout(){
        //            
        //            document.rolloutForm.action="rolloutlist.htm?dateDiff="+dateDiff+"&operators="+operator;
        //            $('#rolloutForm').submit();
        //        }
        function getSubSbuList(sbuId){
            var sbuId;
            if(sbuId==''){
                // sbuId = "9,10,11";
                sbuId = "2,5";
            }
            $.ajax({
                url:"getteamallocationSubSbuList.htm?id="+sbuId,
                dataType : "html",
                success:function(data){
                    $('#subSbu').length=0;
                    $('#subSbu').html(data);
                }
            });
        }
        
        function filterTeamAllocationreport(){
//            if($("#customerName").val() == null || $("#customerName").val() == ""){
//                alert("Please select customer name");
//            }else if ($("#projectName").val() == null || $("#projectName").val() == ""){
//                alert("Please select project name");
//            }else{
                document.teamAllocationReport.action="teamAllocationReport.htm";
                document.teamAllocationReport.submit();
//            }
        }
        
        function exportTeamAllocationreport(){
             
//           if($("#customerName").val() == null || $("#customerName").val() == ""){
//                alert("Please select customer name");
//            }else if ($("#projectName").val() == null || $("#projectName").val() == ""){
//                alert("Please select project name");
//            }else{
            document.teamAllocationReport.action="exportTeamAllocationReport.htm";
            document.teamAllocationReport.submit();
//            }
            
        }
        function loadProjectList(customerId){
            $.ajax({
                url : "loadProjectList.htm?customerId="+customerId,
                type : "POST",
                dataType : "html",
                success : function(data){
                    $("#projectName").html(data);
                }
            });
        }
    </script>
</head>
<body>
    <div id="container">
        <div class="container_inner">
            <div class="page_heading">
                Project Allocation Report
            </div>
        </div>
        <form name="teamAllocationReport" name="teamAllocationReport" action="#" method="POST">
            <div class="tabletools">
                <table>
                    <tr>
                        <td>
                            <label>Project-Sbu</label>
                            <select id="sbu" name="sbu"  class="searchtext" onchange="getSubSbuList(this.value);" style="width: 175px;margin: 0px;">
                                <option value="">- Select -</option>
                                <c:forEach items="${sbuList}" var="sbuList" varStatus="i">
                                    <option value="${sbuList.sbuId}" ${sbuList.sbuId eq selectSbu ? 'selected':''}>${sbuList.sbu}</option>
                                </c:forEach>
                            </select>
                       </td>
                        <td>
                            <label for="sub-Sbu">Project-Sub-Sbu</label>
                            <select id="subSbu" name="subSbu" style="width: 175px;margin: 0px;">
                                <option value="">- Select -</option>
                                <c:forEach items="${subSbuList}" var="subSbu" varStatus="i">
                                    <option value="${subSbu.sbuId}" ${subSbu.sbuId eq selectSubSbu ? 'selected':''} >${subSbu.sbu}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td>
                            <label for="customerName">Customer Name</label>
                            <select id="customerName" name="customerName" style="width: 175px;margin: 0px;" onchange="loadProjectList(this.value);">
                                <option value="">--Select--</option>
                                <c:forEach items="${customerList}" var="custValue" varStatus="i">
                                    <option value="${custValue.customerId}" ${custValue.customerId eq selectCustomer?'selected':''}>${custValue.customerName}</option>
                                </c:forEach>
                            </select>
                        </td>
                         
                    </tr>
                    <tr>
                        <td><label for="projectName">Project : </label>
                            <select id="projectName" name="projectName" class="searchtext" style="width: 175px;margin: 0px;">
                                <option value="">- Select -</option>
                                <c:forEach items="${projectLists}" var="prjlist" varStatus="i">
                                    <option value="${prjlist.projectId}" ${prjlist.projectId eq selectPrj ? 'selected':''} > ${prjlist.prjName} </option>
                                </c:forEach>
                            </select>
                        </td>
                        

                        <!--                        <td>
                                                    <label for="Date Difference">Date Difference</label>
                                                    <select id="operators" name="operators" style="width: 40px;float: left;">
                                                        <option value="1" ${"1" eq selectOperator ? 'selected':''}>=</option>
                                                        <option value="2" ${"2" eq selectOperator ? 'selected':''}>></option>
                                                        <option value="3" ${"3" eq selectOperator ? 'selected':''}>>=</option>
                                                        <option value="4" ${"4" eq selectOperator ? 'selected':''}><</option>
                                                        <option value="5" ${"5" eq selectOperator ? 'selected':''}><=</option>
                                                    </select>
                                                    <input type="text" id="dateDiff" name="dateDiff" class="textbox_new" style="width: 50px;height: 17px;" maxlength="3" value="${enteredDateDiff}"/>
                                                </td>-->

                        
                        <td>
                            <label for="pmName" >PM :</label>
                            <select id="pmName" name="pmName" class="searchtext" style="width: 175px;margin: 0px;">
                                <option value="">- Select -</option>
                                <c:forEach items="${pmNameList}" var="pmlist" varStatus="i">
                                    <option value="${pmlist.pmId}" ${pmlist.pmId eq selectPm ? 'selected':''}>${pmlist.pmName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <!-- 
                        <td>
                            <label for="customerName">Customer Name</label>
                            <select id="customerName" name="customerName" style="width: 175px;margin: 0px;">
                                <option value="">--Select--</option>
                        <c:forEach items="${customerList}" var="custValue" varStatus="i">
                            <option value="${custValue.customerId}" ${custValue.customerId eq selectCustomer?'selected':''}>${custValue.customerName}</option>
                        </c:forEach>
                    </select>
                </td>-->


                        <td>
                            <input type="button" id="go" name="go" value="Go" class="gobutton" onclick="filterTeamAllocationreport();">
                            &nbsp;&nbsp;&nbsp;
                            <input type="button" id="export" name="export" value="Export" class="exportbutton"  onclick="exportTeamAllocationreport();">
                        </td>
                    </tr>
                </table>
            </div>
        </form>
        <div id="datadisplay">
            <table>
               <thead>
                 <th> <label>Employee SBU </label> </th>
                 <th> <label>Employee Sub SBU </label> </th>
                 <th> <label>Customer Name </label>  </th>
                 <th> <label>Project Code </label>  </th>
                 <th> <label>Project Name </label>  </th>
                 <th> <label>Allocation Start Date </label> </th>
                 <th> <label>Allocation End Date  </label> </th>
                 <th> <label>Number of days from today to Allocation end date </label> </th>
                 <th><label>Employee Name </label> </th>
                 <th><label>Employment Status </label> </th>
                 <th> <label>PM Name </label> </th>
                
                <!--                <th> <label>Billable Status </label> </th>-->


                <!--                <th> <label>Billing Hours </label> </th>
                                <th> <label>Billable Rate </label> </th>-->
                </thead>

                <c:if test="${fn:length(rolloutList)>0}">
                    <c:forEach items="${rolloutList}" var="listdata" varStatus="rpritr">
                        <c:if test="${rpritr.index%2 ==0}">
                            <c:set var="rowClass" value="row-odd"></c:set>
                        </c:if>
                        <c:if test="${rpritr.index%2!=0}">
                            <c:set var="rowClass" value="row-even"></c:set>
                        </c:if>
                        <tr class="${rowClass}">
                            <td>${listdata.sbu}</td>
                            <td>${listdata.subSbu}</td>
                            <td>${listdata.customerName}</td>
                            <td>${listdata.projectCode}</td>
                            <td style="word-break: break-all;">${listdata.projectName}</td>
                            <td>${listdata.prjStartDate}</td>
                            <td>${listdata.prjEndDate}</td>
                            <td>${listdata.datediff}</td>
                            <td>${listdata.empNumber} - ${listdata.employeeName}</td>
                            <td>${listdata.empStatus}</td> 
                            <td>${listdata.pmName}</td>
                            </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${fn:length(rolloutList)==0}">
                    <tr  class="row-odd">
                        <td colspan="12"  style="text-align: center;">
                            No data to display
                        </td>
                    </tr>
                </c:if>
            </table>
        </div>
    </div>
</body>
</html>
