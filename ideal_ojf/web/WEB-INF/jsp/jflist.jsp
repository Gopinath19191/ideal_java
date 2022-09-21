<%@include file="header.jsp"%>
<head>
    <title>Joining Formalities</title>
    <%--<script src="../../../../javascript/datatables.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="css/dataTables.css">
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.2/css/jquery.dataTables.css">
    <script type="text/javascript" src="javascript/datatables.js"></script>
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="//cdn.datatables.net/1.10.2/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
        function changeStructureDesc(selectedValue) {
            if (selectedValue != "") {
                $.ajax({
                    url: 'getCompanyStructureX.htm',
                    type: "POST",
                    async: false,
                    data: ({structureId: selectedValue}),
                    success: function(ajaxObj) {
                        var arr = ajaxObj.split(":");
                        var mySelect = document.getElementById("practiceGroupSearch");
                        mySelect.options.length = 0;
                        mySelect.options[0] = new Option("--Select Practice Group--", "");
                        var count = 1;
                        for (var i = 0; i < arr.length; i++) {
                            var out = arr[i].split(",");
                            if (out != '') {
                                if ($('#practice').val() != "" && $('#practice').val() == out[0]) {
                                    document.getElementById("practiceGroupSearch").options[count + i] = new Option(out[1], out[0]);
                                    document.getElementById("practiceGroupSearch").options[count + i].selected = true;
                                } else {
                                    document.getElementById("practiceGroupSearch").options[count + i] = new Option(out[1], out[0]);
                                }
                            }
                        }
                    }
                });
            }
        }
    </script>
    <!--    <script type="text/javascript">
            $(document).ready(function() {
                $("#candidateDataTable").dataTable();
            });
        </script>-->
    <style type="text/css">
        .container_inner {
            margin: 30px;
        }
    </style>
</head>
<body>
    <%@include file="menu.jsp" %>
    <div class="searchBox">
        <form action="begin.htm" method="post" name="searchForm"><%--id=""--%>
            <table width="100%" border="0" class="searchBoxInner">
                <tr><td>
                        <input type="hidden" value="${fn:split(filterData.practiceGroupSearch,'%')[0]}" id="practice"/>
                    </td></tr>
                <tr>
                    <td align="right">Candidate Ref No</td>
                    <td><input type="text"  name="candidateRefNumberSearch" id="candidateRefNumberSearch" value="${fn:split(filterData.candidateRefNumberSearch,'%')[0]}" /></td>
                    <td align="right">Candidate name </td>
                    <td><input type="text" name="candidateNameSearch" id="candidateNameSearch" value="${fn:split(filterData.candidateNameSearch,'%')[0]}" /></td>
                    <td align="center">Status</td>
                    <td>
                        <select name="statusSearch" id="statusSearch">
                            <option value="">Select Status</option>
                            <c:forEach items="${statusList}" var="statusDetails" varStatus="index">
                                <c:choose>
                                    <c:when test="${fn:split(filterData.statusSearch,'%')[0]==statusDetails.key}">
                                        <option title="${statusDetails.value}" value="${statusDetails.key}" selected>${statusDetails.value}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option title="${statusDetails.value}" value="${statusDetails.key}">${statusDetails.value}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td align="right">Company Structure</td>
                    <td>
                        <select name="structureNameSearch" id="structureNameSearch" onchange="changeStructureDesc(this.value)">
                            <option value="">Select Company Structure </option>
                            <c:forEach items="${levelOneStructure}" var="cs" varStatus="index">
                                <c:choose>
                                    <c:when test="${fn:split(filterData.structureNameSearch,'%')[0]==cs.structureId}">
                                        <option title="${cs.structureName}" value="${cs.structureId}" selected >${cs.structureName}</option>
                                    </c:when>
                                    <c:otherwise>
                                        <option title="${cs.structureName}" value="${cs.structureId}">${cs.structureName}</option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </select>
                    </td>
                    <td align="right">Practice Group</td>
                    <td>
                        <select name="practiceGroupSearch" id="practiceGroupSearch">
                            <option value="">Select Practice Group</option>
                        </select>
                    </td>

                    <td colspan="6" align="center">
                        <input type="submit" align="center" class="gobutton" name="search" value="Go" />
                        <input type="submit" align="center" class="exportbutton" name="search" value="Export" />
                    </td>
                </tr>

            </table>
        </form>
    </div>
    <div id="container">
       
        <div id="datadisplay">
            <center><font color="${colour}" size="bold" style="font-size: medium">${successMsg}</font></center>
                <%--<netui-data:dataGrid dataSource="candidateList" name="candidateData" styleClassPrefix="row" tagId="recruitmentList">--%>
            <table id="candidateDataTable">
                <thead>
                    <tr>
                        <th>Candidate Ref No.</th>
                        <th>Candidate Name</th>
                        <th>Business Unit</th>
                        <th>Practice Group</th>
                        <th>E-mail Id</th>
                        <th>Joining Formalities Status</th>
                        <th>Actions </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${candidateList}" var="candidate">
                        <tr>
                            <td><c:out value="${candidate.refnumber}"/></td>
                            <td><c:out value="${candidate.firstName}"/></td>
                            <td><c:out value="${candidate.structureName}"/></td>
                            <td><c:out value="${candidate.practiceGroup}"/></td>
                            <td><c:out value="${candidate.personalEmailId1}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${candidate.status==0}">
                                        <a href="javascript:void(0)" onClick="MailAndEdit(${candidate.jfId}, 'newEmployeeAdd.htm', 'triggerMail',${candidate.status});">Trigger Mail To Candidate</a> 
                                    </c:when>
                                    <c:when test="${candidate.status==1 || candidate.status==2}">
                                        Joining Formalities Initiated
                                    </c:when>
                                    <c:when test="${candidate.status==3}">
                                        <a href="javascript:void(0)" onClick="CheckJoinerDetails(${candidate.trackNumber}, 'joineraddstepone.htm');">Joining Formalities Completed check details</a>
                                    </c:when>
                                    <c:when test="${candidate.status==4}">
<!--                                        <a href="javascript:void(0)" onClick="CheckJoinerDetails(${candidate.trackNumber}, 'joineraddstepone.htm');">Send Back to employee</a>-->
                                        Send Back to employee
                                    </c:when>
                                    <c:when test="${candidate.status==5}">
                                        <a href="javascript:void(0)" onClick="CheckJoinerDetails(${candidate.trackNumber}, 'joineraddstepone.htm');">Data Verified</a>
                                    </c:when>
                                    <c:when test="${candidate.status==6}">
                                        <a  href="javascript:void(0)" onClick="CheckJoinerDetails(${candidate.trackNumber}, 'joineraddstepone.htm');">JF Data Added to iDeal -- ${candidate.joinerEmpId}</a>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <%--<c:if test="${candidate.status < 3}">--%>
                                <a href="javascript:void(0)" onclick="MailAndEdit(${candidate.jfId}, 'newEmployeeAdd.htm', 'editMode',${candidate.status});" title="Edit" style="text-align:center"><img src="css/images/edit-icon.png" ></a>    
                                <%--</c:if>--%>
                                <c:choose>
                                    <c:when test="${candidate.status>=3 && candidate.status!=4}"> 

                                        <a href="javascript:void(0)" onClick="printDetails(${candidate.jfId}, 'printAllPages.jsp');" title="Print" style="text-align:center"><img src="css/images/print_image.png"></a>
                                        </c:when>
                                        <c:otherwise>
                                            <%--<netui-data:spanCell value="" />--%>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${candidate.status != 6 && candidate.status != 0 && candidate.rrfRes !=null && candidate.rrfRes !=0}">
                                        <a href="javascript:void(0)" onClick="printDetails(${candidate.jfId}, 'revertRRF.htm');"  title="Revert RRF" style="text-align:center"><img src="css/images/revert.png"></a>  
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${candidate.status == 6 && candidate.empStatus == 0}">
                                        <a href="javascript:void(0)" onClick="triggerJoiningMail(${candidate.jfId});"  title="Trigger Joiner Mail" style="text-align:center"><img src="css/images/mail.png"></a>  
                                        </c:when>
                                    </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <%--         </netui-data:rows>
   <netui-data:configurePager disableDefaultPager="true" pageSize="15" pagerFormat="firstPrevNextLast" pageHref="com/defiance/ideal/joiningForm/begin.do"/>

    <tr><td class="pagerRow" colspan="10"><netui-data:renderPager/></td></tr> 

</netui-data:dataGrid>--%>
        </div>
    </div>
    <form name="mailAndEdit" id="mailAndEdit" action="" method="POST">
        <input type="hidden" name="jfId" id="jfId" value="">
        <input type="hidden" name="buttonStatus" id="buttonStatus" value="">
        <input type="hidden" name="canStatus" id="canStatus" value="">
    </form>
    <form name="checkJoinerDetails" id="checkJoinerDetails" action="" method="POST">
        <input type="hidden" name="trackNumber" id="trackNumber" value="">
    </form>
    <script type="text/javascript">
        function MailAndEdit(jfId, url, buttonStatus, canStatus) {
            document.mailAndEdit.jfId.value = jfId;
            document.mailAndEdit.buttonStatus.value = buttonStatus;
            document.mailAndEdit.canStatus.value = canStatus;
            document.mailAndEdit.action = url;
            document.mailAndEdit.submit();
        }

        function CheckJoinerDetails(trackNumber, url) {
        <%--alert(trackNumber+"--"+url);--%>
                document.checkJoinerDetails.trackNumber.value = trackNumber;
                document.checkJoinerDetails.action = url;
                document.checkJoinerDetails.submit();
            }

            function printDetails(jfId, url) {
        <%--alert(jfId+"--"+url);--%>
                document.mailAndEdit.jfId.value = jfId;
                document.mailAndEdit.action = url;
                document.mailAndEdit.submit();
            }
            function triggerJoiningMail(jfId) {
                window.location.href = "${pageContext.request.contextPath}/triggerJoiningMail.htm?jfId="+jfId;
            }

            $(document).ready(function() {
                changeStructureDesc(${fn:split(filterData.structureNameSearch,'%')[0]});
                //setTimeout('selectPractice()',2000);
            });

            function selectPractice() {
                alert("fsfs");
                //document.getElementById("practiceGroupSearch").options[${fn:split(filterData.practiceGroupSearch,'%')[0]}].selected = true;
            }
            $(document).ready(function() {
                $('#candidateDataTable').dataTable({
                    "pagingType": "full_numbers",
                    bFilter: false,
                    bInfo: false,
                    "bLengthChange": false
                });
            });

    </script>
</body>
