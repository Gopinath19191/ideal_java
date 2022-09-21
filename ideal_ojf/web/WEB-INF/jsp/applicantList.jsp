<%@include file="header.jsp"%>
<head>
    <title>Applicant Details</title>
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
    
    <style type="text/css">
        .container_inner {
            margin: 30px;
        }
        .headerstyle{
            margin-left: 10px;
            font-weight: bold;
            font-size: 14px;
        }
    </style>
</head>
<body>  
    <%@include file="menu.jsp" %>
    <div class="searchBox" style="margin-top:20px">
        <div><span class="headerstyle">Applicants List</span></div>
    </div>
    <div id="container">
        
        <div id="datadisplay">
            <center><font color="${colour}" size="bold" style="font-size: medium">${successMsg}</font></center>
                <%--<netui-data:dataGrid dataSource="candidateList" name="candidateData" styleClassPrefix="row" tagId="recruitmentList">--%>
            <table id="candidateDataTable">
                <thead>
                    <tr style="font-size: 14px;">
                        <th>Candidate Ref No.</th>
                        <th>Candidate Name</th>
                        <th>Designation</th>
                        <th>Band</th>
                        <th>Sub Band</th>
<!--                         <th>Structure</th>
                          <th>Practice Group</th>-->
                        <th>Date of Birth</th>
                        <th>E-mail Id</th>
                        <th>Structure</th>                        
                        <th> Group</th>
                        <th>Sub Group</th>
                        <th>Actions </th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${applicantsList}" var="applicant" varStatus="indxz">
                       
                        <tr class="${indxz.index%2==0?'oddrow':'altrow'}" id="row${indxz.index}" style="font-size: 13px;">
                            <td><c:out value="${applicant.rrf_id}"/></td>
                            <td><c:out value="${applicant.firstName} ${applicant.middleName} ${applicant.lastName}"/></td>
                            <td><c:out value="${applicant.designation}"/></td>
                            <td><c:out value="${applicant.band}"/></td>
                            <td><c:out value="${applicant.subBand}"/></td>
                            <td><c:out value="${applicant.dateOfBirth}"/></td>
                            <td><c:out value="${applicant.personalEmailId1}"/></td>
                            <td><c:out value="${applicant.structureName}"/></td>
                            <td><c:out value="${applicant.practiceGroup}"/></td>
                            <td><c:out value="${applicant.subPracticeGroup}"/></td>
                            <td style="text-align:center;">                                
                                <a href="editApplicantInOjf.htm?rrfId=${applicant.rrf_id}&appId=${applicant.id}" title="Edit" style="text-align:center"><img src="css/images/edit-icon.png" ></a>    
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
