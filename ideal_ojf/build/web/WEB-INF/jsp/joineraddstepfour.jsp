<%@include file="header.jsp" %>
<%@include file="joineraddcommonscripts.jsp" %>
<script type="text/javascript">

    $(document).ready(function() {
        $("#doj").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
            yearRange: '1900:' + new Date().getFullYear()
//                    minDate: "0"
        });
        $("#joinerDoj").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
            yearRange: '1900:' + new Date().getFullYear()
//                    minDate: "0"
        });
        var d = new Date;
        var currentDate = d.getDate() + '-' + parseInt(d.getMonth() + 1) + '-' + d.getFullYear();
        $("#joinerDoj").val(currentDate);
        $('#personsAtCompanyCount').val($("#personsAtCompany tr").length - 2);
        $('#refFrmEarlierCompanyCount').val($("#refFrmEarlierCompany tr").length - 2);
    <c:if test="${fn:length(mailCcList)!=0}">
        var mailCcData = eval(<json:array name="items" var="mailCcValues" items="${mailCcList}">
            <json:object>
                <json:property name="id" value="${mailCcValues.empId}"/>
                <json:property name="value" value="${mailCcValues.employeeName}-${mailCcValues.employeeNumber}"/>
            </json:object>
        </json:array>);</c:if>
                        $("#dataVerifiedMailCc").autoSuggest('${pageContext.request.contextPath}/getEmployeeNameOfHr.htm', {selectedItemProp: "value", asHtmlID:"dataVerifiedMailCc", searchObjProps: "value", selectionLimit:2, selectedValuesProp:"id"<c:if test="${fn:length(mailCcList)!=0}">, preFill:mailCcData</c:if>});
                        $('#as-values-dataVerifiedMailCc').attr("class", "required");
    <c:if test="${fn:length(dataApprovedByList)!=0}">
                var dataApprovedBy = eval(<json:array name="items" var="dataApprovedByValues" items="${dataApprovedByList}">
            <json:object>
                <json:property name="id" value="${dataApprovedByValues.empId}"/>
                <json:property name="value" value="${dataApprovedByValues.employeeName}-${dataApprovedByValues.employeeNumber}"/>
            </json:object>
        </json:array>);</c:if>
                        $("#dataApprovedBy").autoSuggest('${pageContext.request.contextPath}/getEmployeeName.htm', {selectedItemProp: "value", asHtmlID:"dataApprovedBy", searchObjProps: "value", selectionLimit:1, selectedValuesProp:"id"<c:if test="${fn:length(dataApprovedByList)!=0}">, preFill:dataApprovedBy</c:if>});
                        $('#as-values-dataApprovedBy').attr("class", "required");
            });
            $(function() {
                $('#moveSelectedDocuments').click(function() {
                    $('#docsTemp option:selected').each(function() {
                        $(this).appendTo('#docs');
                    });
                });
                $('#reMoveSelectedDocuments').click(function() {
                    $('#docs option:selected').each(function() {
                        $(this).appendTo('#docsTemp');
                    });
                });
            });
            function redirectBack() {
                var result = confirm("Entered Data will be lost. Do you wish to continue?");
                if (result) {
                    $('#backForm').submit();
                } else {
                    return false;
                }
            }
            function checkEmptyValue(countId, mandatoryValueId, tableName, empId, errorMessage, deleteStatusId)
            {
                var count = document.getElementById(countId).value;
                var nameAdd = document.getElementById(mandatoryValueId + count).value;
                var deleteStatus = document.getElementById(deleteStatusId + count).value;
                if (nameAdd.length == 0 && deleteStatus != 'deleted') {
                    alert("Enter " + errorMessage + " to add more rows");
                    document.getElementById(mandatoryValueId + count).focus();
                } else {
                    addRow(tableName, empId);
                }
            }

            function professional_validation() {
//               alert($('#referrerPrev_count').val());
                var flagCount = 0;
                var recCount = 0;
                for (var i = 1; i <= parseInt($('#referrerPrev_count').val()); i++) {
//                    alert(i);
                    if ($('#PRF_TR_deleted' + i).val() == 0) {
//                        alert(i);
//                        alert($('#referrerPrev_count').val());
                        if ($('#refFrmEarName' + i).val() != '' && $('#refFrmEarCmpName' + i).val() != '' && $('#refFrmEarAddressAndTel' + i).val() != '' && $('#refFrmEarDesignation' + i).val() != '' && $('#refFrmEarNoOfYearsKnown' + i).val() != '') {
//                            alert($('#refFrmEarAddressAndTel' + i).val());
                            $('#refFrmEarName_error_' + i).hide();
                            $('#refFrmEarName_err_' + i).hide();
                            $('#refFrmEarCmpName_error_' + i).hide();
                            $('#refFrmEarCmpName_err_' + i).hide();
                            $('#refFrmEarAddressAndTel_error_' + i).hide();
                            $('#refFrmEarAddressAndTel_err_' + i).hide();
                            $('#refFrmEarDesignation_error_' + i).hide();
                            $('#refFrmEarDesignation_err_' + i).hide();
                            $('#refFrmEarNoOfYearsKnown_error_' + i).hide();
                            $('#refFrmEarNoOfYearsKnown_err_' + i).hide();
                            if (!$('#refFrmEarName' + i).val().match(/^[a-zA-Z. ]+$/)) {
//            alert("inside if");
                                $('#refFrmEarName_err_' + i).show();
                                flagCount++;
                            } else {

                                $('#refFrmEarName_err_' + i).hide();
                            }

                            if (!$('#refFrmEarCmpName' + i).val().match(/^[a-zA-Z-,. ]+$/)) {
                                $('#refFrmEarCmpName_err_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarCmpName_err_' + i).hide();
                            }

                            if (!$('#refFrmEarDesignation' + i).val().match(/^[a-zA-Z0-9,-. ]+$/)) {
                                $('#refFrmEarDesignation_err_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarDesignation_err_' + i).hide();
                            }
                            if (!$('#refFrmEarAddressAndTel' + i).val().match(/^[0-9]+$/)) {
                                $('#refFrmEarAddressAndTel_err_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarAddressAndTel_err_' + i).hide();
                            }

                            if (!$('#refFrmEarNoOfYearsKnown' + i).val().match(/^[0-9]+$/)) {
                                $('#refFrmEarNoOfYearsKnown_err_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarNoOfYearsKnown_err_' + i).hide();
                            }

                        }

                        else if ($('#refFrmEarName' + i).val() == '' || $('#refFrmEarCmpName' + i).val() == '' || $('#refFrmEarAddressAndTel' + i).val() == '' || $('#refFrmEarDesignation' + i).val() == '' || $('#refFrmEarNoOfYearsKnown' + i).val() == '') {
//                            alert($('#refFrmEarName' + i).val());
                            if ($('#refFrmEarName' + i).val() == '') {
                                $('#refFrmEarName_err_' + i).hide();
                                $('#refFrmEarName_error_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarName_error_' + i).hide();
                            }

                            if ($('#refFrmEarCmpName' + i).val() == '') {
                                $('#refFrmEarCmpName_err_' + i).hide();
                                $('#refFrmEarCmpName_error_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarCmpName_error_' + i).hide();
                            }

                            if ($('#refFrmEarAddressAndTel' + i).val() == '') {
                                $('#refFrmEarAddressAndTel_err_' + i).hide();
                                $('#refFrmEarAddressAndTel_error_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarAddressAndTel_error_' + i).hide();
                            }

                            if ($('#refFrmEarDesignation' + i).val() == '') {
                                $('#refFrmEarDesignation_err_' + i).hide();
                                $('#refFrmEarDesignation_error_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarDesignation_error_' + i).hide();
                            }

                            if ($('#refFrmEarNoOfYearsKnown' + i).val() == '') {
                                $('#refFrmEarNoOfYearsKnown_err_' + i).hide();
                                $('#refFrmEarNoOfYearsKnown_error_' + i).show();
                                flagCount++;
                            } else {
                                $('#refFrmEarNoOfYearsKnown_error_' + i).hide();
                            }

                        }
                        recCount++;
                    }
                }

//            for (var i = 0; i < parseInt($('#friends_recordCount').val()); i++) {
//                if(!$('#nameOfPersonAtCom'+i).val().match(/^[a-zA-Z,-. ]+$/) && $('#nameOfPersonAtCom'+i).val() != ''){
//                   // alert("inside if");
//                    $('#name_err'+i).show();
//                    flagCount++;
//                }else{
//                    $('#name_err'+i).hide();
//                }
//                
//                if(!$('#companyAndDep'+i).val().match(/^[a-zA-Z,-. ]+$/) && $('#companyAndDep'+i).val() != ''){
//                    $('#company_err'+i).show();
//                    flagCount++;
//                }else{
//                    $('#company_err'+i).hide();
//                }
//                
//                if(!$('#pacRelation'+i).val().match(/^[a-zA-Z ]+$/) && $('#pacRelation'+i).val() != ''){
//                    $('#pacRelation_err'+i).show();
//                    flagCount++;
//                }else{
//                    $('#pacRelation_err'+i).hide();
//                }
//            }

//              if(recCount < 2){
//                  alert("Two Professional Details are Mandatory.");
//                  flagCount++;
//              }
                $('#recCount').val(recCount);
                if (flagCount == 0) {
//                    alert("inside if");
                    return true;
                } else {
                    return false;
                }

            }

            function personsknown_company_validation() {
                var flagCount = 0;
                var recCount = 0;
                for (var i = 1; i <= parseInt($('#friends_recordCount').val()); i++) {
                    if ($('#PRF_TR_deleted' + i).val() == 0) {
//                    alert(i);
                        if (!$('#nameOfPersonAtCom' + i).val().match(/^[a-zA-Z,-. ]+$/) && $('#nameOfPersonAtCom' + i).val() != '') {
                            $('#name_err' + i).show();
                            flagCount++;
                        } else {
                            $('#name_err' + i).hide();
                        }
                        if (!$('#companyAndDep' + i).val().match(/^[a-zA-Z,-. ]+$/) && $('#companyAndDep' + i).val() != '') {
                            $('#company_err' + i).show();
                            flagCount++;
                        } else {
                            $('#company_err' + i).hide();
                        }
                        if (!$('#pacRelation' + i).val().match(/^[a-zA-Z ]+$/) && $('#pacRelation' + i).val() != '') {
                            $('#pacRelation_err' + i).show();
                            flagCount++;
                        } else {
                            $('#pacRelation_err' + i).hide();
                        }
                    }
                }
                if (flagCount == 0) {
//                    alert("inside if");
                    return true;
                } else {
                    return false;
                }

            }

            function validation() {
                if (professional_validation() && personsknown_company_validation()) {
                    if ($('#recCount').val() < 2) {
                        alert("Two Professional Details are Mandatory.");
                        return false;
                    } else {
                        $("#continuebutton3").hide();
                        $("#continuebutton4").hide();
                        $("#addEmployeeToIdeal").hide();
                        return true;
                    }
                } else {
                    return false;
                }
            }

    </script>
    <title>Employee Details - Step 4 of 4</title>
    <body class="ext-gecko ext-gecko3">
        <div id="container">
        <%@include file="menu.jsp" %>
        <div class="LabelHeader">Employee Details - Step 4 of 4</div>
        <div class="notice_page">
            <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" /></div>
            <div style="padding-left:20px;">
                <ul class="notice_page_ul">
                    <li>Fields marked in <font color="red" size="3">*</font> are mandatory</li>
                    <li>Two professional references are mandatory</li>
                    <li>Please validate the data before submit & It will be subjected to verify</li>
                </ul>
            </div>
        </div>
        <%--<c:if test="${joinerData.status==5}">
        <form action="${pageContext.request.contextPath}/SyncStatusCheck.htm" method="POST" name="joinerFormOne" id="idealJavaForm" class="idealJavaForm" onsubmit="return validation();">
        </c:if>--%>
        <%--<c:if test="${joinerData.status!=5}">--%>
        <form action="${pageContext.request.contextPath}/finalSubmit.htm" method="POST" name="joinerFormOne" id="idealJavaForm" class="idealJavaForm" onsubmit="return validation();">
            <%--</c:if>--%>
            <input type="hidden" name="jfId" value ="${employeeData.jfId}" id="jfId"/>
            <input type="hidden" name="trackNumber" value ="${trackNumber}" id="trackNumber"/>
            <input type="hidden" value="${pageContext.request.contextPath}" id="base_path" />
            <c:choose>
                <c:when test="${!empty(referrerDTDetails)}">
                    <input type="hidden" name="friends_recordCount" id="friends_recordCount" value="${fn:length(referrerDTDetails)}" />
                </c:when>
                <c:otherwise>
                    <input type="hidden" name="friends_recordCount" id="friends_recordCount" value="1" />
                </c:otherwise>
            </c:choose>

            <div id="commonforms">
                <div class="commonformheader">Friends/Relatives/Acquaintance working in Hindujatech</div>
                <table width="100%">
                    <tr>
                        <td colspan="3" width="100%">
                            <table class="multiAddTable" id="personsAtCompany" width="100%" cellspacing="5" border="0">
                                <tr>
                                    <td width="30%"><b>Name</b></td>
                                    <td width="30%"><b>Company/Department</b></td>
                                    <td width="30%"><b>Relationship</b></td>
                                </tr>
                                <c:if test="${empty(referrerDTDetails)}">
                                    <tr id="REL_TR_1">
                                        <td>
                                            <input type="text" name="nameOfPersonAtCom" id="nameOfPersonAtCom1" class="" />
                                            <div id="name_err1" style="color:red;display: none">Only Alphabets are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="companyAndDep" id="companyAndDep1" class="" />
                                            <div id="company_err1" style="color:red;display: none">Only AlphaNumerics are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="pacRelation" id="pacRelation1" />
                                            <div id="pacRelation_err1" style="color:red;display: none">Only Alphabets are allowed</div>
                                        </td>
                                        <td align="left"><input type="hidden" name="personsAtCompanyStatus" id="personsAtCompanyStatus1" value="undeleted" />
                                            <input type="hidden" name="jfPerCompId" id="jfPerCompId1" value="0" />
                                            <input type="hidden" name="REL_TR_deleted" id="REL_TR_deleted1" value="0" />
                                            <%--<c:if test="${printStatus!='yes'}">
                                                <img alt="remove" src="${pageContext.request.contextPath}/css/images/delete-icon.png" onclick="deleteRow_REL('REL_TR',${rfdtindex.count},1)" />
                                            </c:if>--%>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:forEach items="${referrerDTDetails}" var="referrerDTDetail" varStatus="rfdtindex">
                                    <tr id="REL_TR_${rfdtindex.count}">
                                        <td>
                                            <input type="text" name="nameOfPersonAtCom" id="nameOfPersonAtCom${rfdtindex.count}" class="" value="${referrerDTDetail.nameOfPersonAtComDb}" />
                                            <div id="name_err${rfdtindex.count}" style="color:red;display: none">Only Alphabets are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="companyAndDep" id="companyAndDep${rfdtindex.count}" class="" value="${referrerDTDetail.companyAndDepDb}" />
                                            <div id="company_err${rfdtindex.count}" style="color:red;display: none">Only AlphaNumerics are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="pacRelation" id="pacRelation${rfdtindex.count}"  value="${referrerDTDetail.pacRelationDb}"/>
                                            <div id="pacRelation_err${rfdtindex.count}" style="color:red;display: none">Only Alphabets are allowed</div>
                                        </td>
                                        <td>
                                            <input type="hidden" name="personsAtCompanyStatus" id="personsAtCompanyStatus${rfdtindex.count}" value="undeleted" />
                                            <input type="hidden" name="jfPerCompId" id="jfPerCompId${rfdtindex.count}" value="${referrerDTDetail.jfPerCompIdDb}" />
                                            <input type="hidden" name="REL_TR_deleted" id="REL_TR_deleted${rfdtindex.count}" value="0" />
                                            <c:if test="${printStatus!='yes' && rfdtindex.count!=1}">
                                                <img alt="remove" src="${pageContext.request.contextPath}/css/images/delete-icon.png" onclick="deleteRow_REL('REL_TR',${rfdtindex.count}, 1)" />
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <table width="100%" cellspacing="5">
                                <tr>
                                    <c:if test="${printStatus!='yes'}">
                                        <td><input type="button" class="addmore" onclick="addRow_Relatives();" value="Add"  /></td>
                                        </c:if>
                                </tr>
                            </table>
                            <input type="hidden" id="personsAtCompanyCount" name="personsAtCompanyCount" value="" />
                        </td>
                    </tr>
                </table>
            </div>
            <div id="commonforms">
                <div class="commonformheader">Provide two professional references</div>
                <table>
                    <tr>
                        <td colspan="3" width="100%">
                            <c:choose>
                                <c:when test="${!empty(referrerPrevCompDetails)}">
                                    <input type="hidden" name="referrerPrev_count" id="referrerPrev_count" value="${fn:length(referrerPrevCompDetails)}" />
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="referrerPrev_count" id="referrerPrev_count" value="1" />
                                </c:otherwise>
                            </c:choose>
                            <table class="multiAddTable" id="refFrmEarlierCompany" width="100%" border="0" cellspacing="5">
                                <tr style="font-weight: bold;">
                                    <td>Name<font color="red" size="3">*</font></td>
                                    <td>Working At<font color="red" size="3">*</font></td>
                                    <td>Telephone No.<font color="red" size="3">*</font></td>
                                    <td>Designation/Role<font color="red" size="3">*</font></td>
                                    <td>No of years known<font color="red" size="3">*</font></td>
                                    <td>Email Id<font color="red" size="3">*</font></td>
                                </tr>
                                <input type = "hidden" name="recCount" id = "recCount" value = ""/>
                                <c:if test="${empty(referrerPrevCompDetails)}">

                                    <tr id = "PRF_TR_1">
                                        <td>
                                            <input type="text" name="refFrmEarName" id="refFrmEarName1" />
                                            <div id="refFrmEarName_error_1" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarName_err_1" style="color:red;display: none;">Only Alphabets are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarCmpName" id="refFrmEarCmpName1" />
                                            <div id="refFrmEarCmpName_error_1" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarCmpName_err_1" style="color:red;display: none;">Only Alphabets are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarAddressAndTel" id="refFrmEarAddressAndTel1"  maxlength="20" style="width:100px"/>
                                            <div id="refFrmEarAddressAndTel_error_1" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarAddressAndTel_err_1" style="color:red;display: none;">*Invalid Number</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarDesignation" id="refFrmEarDesignation1"  />
                                            <div id="refFrmEarDesignation_error_1" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarDesignation_err_1" style="color:red;display: none;">Only AlphaNumerics are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarNoOfYearsKnown" id="refFrmEarNoOfYearsKnown1"  maxlength="2" style="width:100px"/>
                                            <div id="refFrmEarNoOfYearsKnown_error_1" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarNoOfYearsKnown_err_1" style="color:red;display: none;">*Invalid Number</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarEmailId" id="refFrmEarEmailId1"/>
                                            <div id="refFrmEarEmailId_error_1" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarEmailId_err_1" style="color:red;display: none;">*Invalid Email Id</div>
                                        </td>
                                        <td>
                                            <input type="hidden" name="refFrmEarlierCompanyStatus" id="refFrmEarlierCompanyStatus1" value="undeleted" />
                                            <input type="hidden" name="jfRefEarCompId" id="jfRefEarCompId1" value="0" />
                                            <input type="hidden" name="PRF_TR_deleted" id="PRF_TR_deleted1" value="0" />
                                        </td>
                                    </tr>
                                </c:if>



                                <c:forEach items="${referrerPrevCompDetails}" var="referrerPrevCompDetail" varStatus="rpcindex">
                                    <tr id = "PRF_TR_${rpcindex.count}">
                                        <td>
                                            <input type="text" name="refFrmEarName" id="refFrmEarName${rpcindex.count}"  value="${referrerPrevCompDetail.refFrmEarNameDb}" />
                                            <div id="refFrmEarName_error_${rpcindex.count}" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarName_err_${rpcindex.count}" style="color:red;display: none;">Only Alphabets are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarCmpName" id="refFrmEarCmpName${rpcindex.count}"  value="${referrerPrevCompDetail.refFrmEarCmpNameDb}" />
                                            <div id="refFrmEarCmpName_error_${rpcindex.count}" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarCmpName_err_${rpcindex.count}" style="color:red;display: none;">Only Alphabets are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarAddressAndTel" id="refFrmEarAddressAndTel${rpcindex.count}"  maxlength="20" value="${referrerPrevCompDetail.refFrmEarAddressAndTelDb}" style="width:100px"/>
                                            <div id="refFrmEarAddressAndTel_error_${rpcindex.count}" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarAddressAndTel_err_${rpcindex.count}" style="color:red;display: none;">*Invalid Number</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarDesignation" id="refFrmEarDesignation${rpcindex.count}"  value="${referrerPrevCompDetail.refFrmEarDesignationDb}" />
                                            <div id="refFrmEarDesignation_error_${rpcindex.count}" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarDesignation_err_${rpcindex.count}" style="color:red;display: none;">Only AlphaNumerics are allowed</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarNoOfYearsKnown" id="refFrmEarNoOfYearsKnown${rpcindex.count}"  maxlength="2" value="${referrerPrevCompDetail.refFrmEarNoOfYearsKnownDb}" style="width:100px"/>
                                            <div id="refFrmEarNoOfYearsKnown_error_${rpcindex.count}" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarNoOfYearsKnown_err_${rpcindex.count}" style="color:red;display: none;">*Invalid Number</div>
                                        </td>
                                        <td>
                                            <input type="text" name="refFrmEarEmailId" id="refFrmEarEmailId${rpcindex.count}"  value="${referrerPrevCompDetail.refFrmEarEmailIdDb}" />
                                            <div id="refFrmEarEmailId_error_${rpcindex.count}" style="color:red;display: none;">*required</div>
                                            <div id="refFrmEarEmailId_err_${rpcindex.count}" style="color:red;display: none;">*Invalid Email Id</div>
                                        </td>
                                        <td>
                                            <input type="hidden" name="refFrmEarlierCompanyStatus" id="refFrmEarlierCompanyStatus${rpcindex.count}" value="undeleted" />
                                            <input type="hidden" name="jfRefEarCompId" id="jfRefEarCompId${rpcindex.count}" value="${referrerPrevCompDetail.jfRefEarCompIdDb}" />
                                            <input type="hidden" name="PRF_TR_deleted" id="PRF_TR_deleted${rpcindex.count}" value="0" />
                                            <c:if test="${printStatus!='yes' && rpcindex.count!=1}">

                                                <img alt="remove" src="${pageContext.request.contextPath}/css/images/delete-icon.png" onclick="deleteRow_PRF('PRF_TR',${rpcindex.count}, 1)" />
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <table width="100%" cellspacing="5">
                                <tr>
                                    <c:if test="${printStatus!='yes'}">
                                        <td><input type="button" class="addmore" onclick="addRow_Professional();" value="Add"  /></td>
                                        </c:if>
                                </tr>
                            </table>
                            <input type="hidden" id="refFrmEarlierCompanyCount" name="refFrmEarlierCompanyCount" value="" />
                        </td>
                    </tr>
                </table>
            </div>
            <div id="commonforms">
                <c:if test="${(groupId!=5 && groupId!=24)}">
                    <div class="commonformheader">Self Declaration</div>
                    <table width="98%" cellspacing="5">
                        <tr>
                            <td colspan="3">
                                <p><input type="checkbox" style="width:30px;" name="selfDeclaration" id="selfDeclaration"  align="left" onclick="hideErrorMessage();" /> I do hereby declare that the information furnished as above by me is true and correct to the best of my knowledge
                                    and belief. If any information furnished by me is proved to be incorrect and false, management may take appropriate
                                    action against me including termination of services.</p>
                            </td>
                        </tr>
                    </table>
                </c:if>
                <table width="100%" cellspacing="5">
                    <tr>
                        <td colspan="3" align="center">
                            <input name="employeeType" id="employeeType" type="hidden" value="${employeeType}" />
                            <span id="alertMessage" style="display:none;color: red"></span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <table id="sendBackTable" align="center" width="80%" border="0" style="display: none;" >
                                <tr>
                                    <td width="100%">
                                        <label for="Reason" class="requiredLabel">Reason</label>
                                        <textarea name="sendBackReason" class="required" id="sendBackReason" rows="4" style="width:300px;"></textarea>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <table id="dojTable" align="center" width="80%" border="0" style="display: none;" >
                                <tr>
                                    <td>
                                        <label for="middleName" class="requiredLabel">Date Of Verification</label>
                                        <input type="text" value ="" class="required" name="doj" id="doj" readonly  />
                                    </td>
                                    <td>
                                        <label for="middleName" class="requiredLabel">Data Verified By</label>
                                        <input type="text" name="dataVerifiedMailCc" value ="" id="dataVerifiedMailCc"  />
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <c:if test="${(groupId==5 || groupId==24) && joinerData.status==4}">
                        <tr>
                            <td colspan="6" align="right">
                                <table align="center" width="40%" border="0">
                                    <tr>
                                        <td align="center">
                                            <label for="sendBackReason" class="requiredLabel">Send Back Reason</label>
                                            ${joinerData.sendBackReason}
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${(groupId==5 || groupId==24) && joinerData.status==5}">
                        <input type="hidden" value="${joinerData.joinerEmpId}" id="contractemployeeId" name="contractemployeeId" />
                        <input type="hidden" value="${joinerData.empCategory}" id="employeeCategory" name="employeeCategory" />
                        <tr>
                            <td colspan="3" align="center">
                                <table align="center" width="95%" border="1" style="border-collapse: collapse;position: relative;right: 10px;" cellspacing="5" cellpadding="10">
                                    <tr>
                                        <td>
                                            <label for="middleName" class="requiredLabel"><b>Date of Verification</b></label>
                                            ${joinerData.doj}
                                        </td>
                                        <td>
                                            <label for="middleName" class="requiredLabel"><b>Data Verified by</b></label>
                                            ${dataVerifiedMailCC}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2">
                                            <table border="0" width="98%" cellspacing="5">
                                                <tr>
                                                    <td width="20%" align="left"><b>Check e-mail</b></td>
                                                    <td width="25%" align="left" style="font-weight: bold;">
                                                        <input type="checkbox" class="required" style="width: 69px;" name="checkJoinerWorkEmail"  id="checkEmail"
                                                               onclick="checkWorkEmail(document.getElementById('joinerWorkEmail').value)" />
                                                    </td>
                                                    <td align="center"></td>
                                                    <td width="20%" align="left"><b>Joiner Work Email</b></td>
                                                    <td align="left">
                                                        <input type="text" style="width: 300px;" name="joinerWorkEmail" id="joinerWorkEmail" value="${fn:trim(joinerData.firstName)}.${fn:trim(joinerData.lastName)}@hindujatech.com" onchange="checkWorkEmail(this.value)" />
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left"><b>Joiner DOJ</b></td>
                                                    <td align="left">
                                                        <input type="text" name="joinerDoj" id="joinerDoj" value="" readonly />
                                                    </td>
                                                    <td align="center"></td>
                                                    <td align="left"><b>Employment Type</b><font color="red" size="3">*</font></td>
                                                    <td align="left">
                                                        <select class="required" name="employeementType" id="employeementType" >
                                                            <option value="">--Select Employement Type--</option>
                                                            <c:forEach items="${employementTypeList}" var="employementTypeValues" varStatus="index" >
                                                                <option value=${employementTypeValues.configKey}>${employementTypeValues.configValue}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left"><b>Joining Location</b><font color="red" size="3">*</font></td>
                                                    <td align="left">
                                                        <select class="required" name="joiningLocation" id="joiningLocation" >
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${cmpLocationList}" var="cmpLocationValues" varStatus="index" >
                                                                <option value=${cmpLocationValues.cmpLocationId}>${cmpLocationValues.cmpLocationName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td align="center"></td>
                                                    <td><b>Current Location</b></td>
                                                    <td align="left">
                                                        <select name="currentLocation" id="currentLocation" >
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${cmpLocationList}" var="cmpLocationValues" varStatus="index" >
                                                                <option value=${cmpLocationValues.cmpLocationId}>${cmpLocationValues.cmpLocationName}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="left"><b>Billable</b><font color="red" size="3">*</font></td>
                                                    <td align="left">
                                                        <select class="required" name="empBillable" id="empBillable" >
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${empBillableList}" var="empBillableValues" varStatus="index" >
                                                                    <c:set value=""  var="selected"></c:set>
                                                                  <c:out value="${joinerMasterDataBillable}"> </c:out>	 
                                                                  <option value=${empBillableValues.configKey} ${empBillableValues.configKey == joinerMasterDataBillable ? 'selected="selected"' : ''}>${empBillableValues.configValue}</option>
                                                                   </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td align="center"></td>
                                                    <td align="left"><label class="requiredLabel" for="dataApprovedBy" style="width: 125px;"><b>Data Approved by</b></label></td>
                                                    <td align="left">
                                                        <input type="text" name="dataApprovedBy" value ="" id="dataApprovedBy" />
                                                    </td>
                                                </tr>



                                                <tr>

                                                    <td align="left"><label for="documentList" style="width: 125px;"><b>Document List</b></label></td>
                                                    <td align="left" id="documentsList">
                                                        <select  id="docsTemp" name="docsTemp" multiple="" size="6" style="background: none !important;">
                                                            <c:forEach items="${documentList}" var="documentValues" varStatus="index" >
                                                                <option value=${documentValues.documentType}>${documentValues.documentName}</option>
                                                            </c:forEach>
                                                            <!--                                                            <option>Xstd</option>
                                                                                                                        <option>12th/Deploma</option>
                                                                                                                        <option>Graduation</option>
                                                                                                                        <option>Post Graduation</option>-->
                                                        </select>

                                                        <br/><span id="loadingInfo"></span>
                                                    </td>
                                                    <td align="left" style="position: relative;right: 9px;">
                                                        <button type="button" id="moveSelectedDocuments">&gt;</button><br>
                                                        <button type="button" id="reMoveSelectedDocuments">&lt;</button>
                                                    </td>
                                                    <td align="left"><label for="documentList" style="width: 125px;"><b>Pending Documents</b></label></td>
                                                    <td align="left" >
                                                        <select  id="docs" name="docs" multiple="" size="6" style="background: none !important;">
                                                        </select>

                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td colspan="2" align="center">
                                            <span id="errorOnCheckEmail"></span>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </c:if>
                    <tr>
                        <td colspan="3" align="center">
                            <c:if test="${(groupId==5 || groupId==24) && joinerData.status==6}">
                                <table align="center" width="100%" border="1" style="border-collapse: collapse;" cellspacing="5">
                                    <tr>
                                        <td width="30%">
                                            <table width="100%">
                                                <tr>
                                                    <td width="50%"><b>Date of Verification </b></td>
                                                    <td>${joinerData.doj}</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="30%">
                                            <table width="100%">
                                                <tr>
                                                    <td width="40%"><b>Data Verified by </b></td>
                                                    <td>${dataVerifiedMailCC}</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td width="40%">
                                            <table width="100%">
                                                <tr>
                                                    <td width="35%"><b>Data Approved by </b></td>
                                                    <td>${dataApprovedBy}</td>
                                                </tr>
                                            </table>
                                        </td>

                                    </tr>
                                    <tr>
                                        <td>
                                            <table width="100%">
                                                <tr>
                                                    <td width="50%"><b>Joiner DOJ</b></td>
                                                    <td>${joinerMasterData.joinerDoj}</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td>
                                            <table width="100%">
                                                <tr>
                                                    <td width="40%"><b>Employee Type</b></td>
                                                    <td>${joinerMasterData.employeementType}</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td>
                                            <table width="100%">
                                                <tr>
                                                    <td width="35%"><b>Joining Location</b></td>
                                                    <td>${joinerMasterData.joiningLocation}</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <table width="100%">
                                                <tr>
                                                    <td width="50%"><b>Billable</b></td>
                                                    <td>${joinerMasterData.empBillable}</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td>
                                            <table width="100%">
                                                <tr>
                                                    <td width="40%"><b>RRF Id</b></td>
                                                    <td>${rrfRes}</td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td>
                                            <table width="100%">
                                                <tr>
                                                    <td width="35%"><b>Current Location</b></td>
                                                    <td>${joinerMasterData.currentLocation}</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                    <c:if test="${!empty(pendingDocuments)}">
                                        <tr>
                                            <td>
                                                <table width="100%">
                                                    <tr>
                                                        <td width="35%"><b>Pending Documents</b></td>

                                                    </tr>
                                                </table>
                                            </td>
                                            <td  colspan ="2">
                                                <table width="100%">
                                                    <tr>

                                                        <td>
                                                            <c:forEach items="${pendingDocuments}" var="pendingDocumentName" varStatus = "index">
                                                                <c:if test="${index.count > 1}">,&nbsp;</c:if>
                                                                ${pendingDocumentName.documentName}
                                                            </c:forEach>
                                                        </td>

                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </c:if>
                                </table>
                            </c:if>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <c:if test="${printStatus!='yes' && (groupId!=5 && groupId!=24)}">
                                <input type="button" class="backbutton"  name="backButton" value="Back" onclick="redirectBack()">
                                <input type="submit" class="savebutton" id="continuebutton3"  name="buttonType" value="Save" onclick="return checkSelfDeclaration();">
                                <input type="submit" class="submitbutton" id="continuebutton4"  name="buttonType" value="Submit" onclick="return checkSelfDeclaration();">
                            </c:if>
                            <c:if test="${(groupId==5 || groupId==24) && (joinerData.status==3 || joinerData.status==4)}">
                                <input type="button" class="backbutton"  name="backButton" value="Back" onclick="redirectBack()">
                                <c:if test="${(groupId==5 || groupId==24) && joinerData.status==3}">
                                    <input type="submit" class="submitbutton"  name="buttonType" value="Data Verified" onclick="return enableDOJandHr();" >
                                    <input type="submit" class="submitbutton" id = "send_back_to_employee" name="buttonType" value="Send back to Employee" onclick="return enableSendBackReason();">
                                </c:if>
                            </c:if>
                            <c:if test="${(groupId==5 || groupId==24) && joinerData.status==5}">
                                <input type="button" class="backbutton"  name="backButton" value="Back" onclick="redirectBack()">
                                <input type="submit" class="addtoidealbutton" id="addEmployeeToIdeal" name="buttonType" value="Add Employee to iDeal" onclick="disbleRequiredData()">
                            </c:if>
                            <c:if test="${(groupId==5 || groupId==24) && joinerData.status==6}">
                                <input type="button" class="backbutton"  name="backButton" value="Back" onclick="redirectBack()">
                            </c:if>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
        <form action="${pageContext.request.contextPath}/joinerFormAddOrUpdateStepThree.htm" method="POST" name="backForm" id="backForm">
            <input type="hidden" name="backButton" value="Back">
        </form>
    </div>
</body>
<script type="text/javascript">
            function checkSelfDeclaration() {
                //                alert("inside selfDeclaration");
                if (!(document.getElementById("selfDeclaration").checked)) {
                    $("#alertMessage").show();
                    $("#alertMessage").html("Select the Self Declaration Check Box");
                    $("#selfDeclaration").focus();
                    return false;
                } else {
                    disbleRequiredData();
                    //                    alert("inside 2");
                    return true;
                }

            }

            function hideErrorMessage() {
                $("#alertMessage").hide();
            }

            function printInfo() {
                window.location = 'printInfo.do';
            }

            $(document).ready(function() {
                $("#idealJavaForm").validate({
                    errorElement: "div",
                    errorClass: "customError"
                });
            });
            function enableDOJandHr() {
                document.getElementById('dojTable').style.display = "block";
                document.getElementById('sendBackTable').style.display = "none";
                $('#sendBackReason').attr("class", "");
                $('#doj').attr("class", "required");
                $('#as-values-dataVerifiedMailCc').attr("class", "required");
                return true;
            }
            function disbleRequiredData() {
                $('#doj').attr("class", "");
                $('#as-values-dataVerifiedMailCc').attr("class", "");
                $('#sendBackReason').attr("class", "");
            }

            function enableSendBackReason() {
                document.getElementById('sendBackTable').style.display = "block";
                document.getElementById('dojTable').style.display = "none";
                $('#doj').attr("class", "");
                $('#as-values-dataVerifiedMailCc').attr("class", "");
                $('#sendBackReason').attr("class", "required");
                return true;
            }

        function checkWorkEmail(selectedValue) {
                if (selectedValue != "") {
                    var employee_id = $('#contractemployeeId').val();
                    var employee_category = $('#employeeCategory').val();
                    $.ajax({
                        url: '${pageContext.request.contextPath}/checkWorkEmailAddress.htm',
                        type: "POST",
                        async: false,
                        data: ({workEmailId: selectedValue, employeeCategory: employee_category, contractemployeeId: employee_id}),
                        success: function(ajaxObj) {
                            var result = eval(ajaxObj);
                            if (result != 0) {
                                $('#errorOnCheckEmail').attr("style", "color:red;font-weight: bold;");
                                $('#errorOnCheckEmail').text("Email Already Exists.Please Change the Joiner Work Email Address");
                                $("#checkEmail").attr("checked", false);
                            } else {
                                $('#errorOnCheckEmail').attr("style", "color:green;font-weight: bold;");
                                $('#errorOnCheckEmail').text("New Email Address.Good to go");
                                $("#checkEmail").attr("checked", "true");
                            }
                        }
                    });
                }
                return false;
            }



</script>
