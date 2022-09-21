<%@include file="header.jsp"  %>
<%@include file="joineraddcommonscripts.jsp"  %>
<title>Employee Details - Step 3 of 4</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/stepthree.js"></script>
<style>
    .textbox{
        height:20px;
        width: 200px;
    }
</style>
<script type="text/javascript">


    function checkValue(selectObject) {
        if (selectObject.checked) {
            $(selectObject).parent().find('.lifeBox').val('1');
            var counter = 0;
            for (var i = 1; i <= parseInt($('#dependent_recordCount').val()); i++) {
                if ($('#DEP_TR_deleted_' + i).val() == 0) {
                    if ((document.getElementById('medicalInsuranceCheck_' + i).checked)) {
                        counter++;
                    }
                }
            }
            if (counter > 6) {
                alert("Only six memebers are allowed for Medical Insurance");
                selectObject.checked = false;
                $(selectObject).parent().find('.lifeBox').val('0');
            }
        } else {
            $(selectObject).parent().find('.lifeBox').val('0');
        }
    }
    
    function checkNomineeValue(selectObject) {
        if (selectObject.checked) {
            $(selectObject).parent().find('.lifeBox').val('1');
            var counter = 0;
            for (var i = 1; i <= parseInt($('#dependent_recordCount').val()); i++) {
                if ($('#DEP_TR_deleted_' + i).val() == 0) {
                    if ((document.getElementById('nomineeCheck_' + i).checked)) {
                        counter++;
                    }
                }
            }
            if (counter > 2) {
                alert("Only Two Nominee are allowed");
                selectObject.checked = false;
                $(selectObject).parent().find('.lifeBox').val('0');
            }
        } else {
            $(selectObject).parent().find('.lifeBox').val('0');
        }
    }

    $(document).ready(function() {

        $("#dlDateOfIssue").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
            yearRange: '1900:' + new Date().getFullYear()
//            minDate: "-60Y", maxDate: "0",
//            onSelect: function(dateText, inst) {
//                $("#dlDateOfExpiry").datepicker("option" ,"minDate", new Date(dateText.split("-")[2], --dateText.split("-")[1] ,dateText.split("-")[0]));
//            }
        });

        $("#dlDateOfExpiry").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
            yearRange: '1900:' + (new Date().getFullYear() + 100)
        });

        $(".dateOfIssue").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
            yearRange: '1900:' + new Date().getFullYear()
        });

        $(".dateOfExpiry").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
            yearRange: '1900:' + (new Date().getFullYear() + 100)
        });

        $(".dobRelation").datepicker({
            changeYear: true,
            changeMonth: true,
            dateFormat: 'dd-mm-yy',
            showButtonPanel: true,
//            minDate: "-100Y", maxDate: "0",
            yearRange: '1900:' + (new Date).getFullYear(),
            currentText: 'Now'
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

</script>
<body class="ext-gecko ext-gecko3" >
    <div id="container">
        <%@include file="menu.jsp" %>
        <div class="LabelHeader">Employee Details - Step 3 of 4</div>
        <div class="notice_page">
            <div style="float:left;"><img alt="Information" title="Information" src="${pageContext.request.contextPath}/css/images/icon_alert.png" /></div>
            <div style="padding-left:20px;">
                <ul class="notice_page_ul">
                    <li>Fields marked in <font color="red" size="3">*</font> are mandatory</li>
                    <li>Dependent Details & Emergency Contacts sections are mandatory</li>
                    <li>Please validate the data before submit & It will be subjected to verify</li>
                </ul>
            </div>
        </div>
        <input type="hidden" value="${pageContext.request.contextPath}" id="base_path" />
        <c:choose>
            <c:when test="${groupId!=joinerGroupId}">
                <form action="${pageContext.request.contextPath}/joinerFormAddOrUpdateStepFour.htm" method="POST" name="joinerFormOne" id="idealJavaForm" class="idealJavaForm" enctype="multipart/form-data"  onSubmit="return formValidate();">
                </c:when>
                <c:otherwise>
                    <form action="${pageContext.request.contextPath}/joinerFormAddOrUpdateStepFour.htm" method="POST" name="joinerFormOne" id="idealJavaForm" class="idealJavaForm" enctype="multipart/form-data" onSubmit="return formValidate();" >
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="jfId" value ="${employeeData.jfId}" id="jfId"/>
                <input type="hidden" name="trackNumber" value ="${trackNumber}" id="trackNumber"/>

                <div id="commonforms">
                    <div class="commonformheader">Dependent Details</div>
                    <table width="100%">
                        <tr>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${!empty(familyMemberDetails)}">
                                        <input type="hidden" name="dependent_recordCount" id="dependent_recordCount" value="${fn:length(familyMemberDetails)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="dependent_recordCount" id="dependent_recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table width="100%" border="0" cellspacing="5">
                                    <tr class="headerCenter">
                                        <td width="19%" valign="top">Name<font color="red" size="3">*</font></td>
                                        <td width="19%" valign="top">Relationship<font color="red" size="3">*</font></td>
                                        <td width="19%" valign="top">Date of Birth<font color="red" size="3">*</font></td>
                                        <td width="19%" valign="top">Occupation</td>
                                        <td width="19%" valign="top">Medical Insurance<font color="red" size="3">*</font></td>
                                        <td width="19%" valign="top">Nominee<font color="red" size="3">*</font></td>
                                        <th width="5%">&nbsp;</th>
                                    </tr>

                                    <c:forEach items="${familyMemberDetails}" var="familyMemberDetail" varStatus="fmindex">
                                        <tr id="DEP_TR_${fmindex.count}">
                                        <input type="hidden" name="jfFamId" id="jfFamId_${fmindex.count}" value="${familyMemberDetail.jfFamIdDb}" />
                                        <input type="hidden" name="DEP_TR_deleted" id="DEP_TR_deleted_${fmindex.count}" value="0" />
                                        <td valign="top" align="left">
                                            <input style="width:75%" type="text" name="nameOfRelation" id="nameOfRelation_${fmindex.count}" class="" value="${familyMemberDetail.nameOfRelationDb}" />
                                            <span style="color:red;display:none;" id="nameOfRelation_error_${fmindex.count}">*required</span>
                                            <div id = "nameOfRelation_err_${fmindex.count}" style="color:red;display:none;">Only alphanumeric are allowed</div>
                                        </td>
                                        <td valign="top" align="left">
                                            <select style="width:75%" name="relationShip" id="relationShip_${fmindex.count}" >
                                                <option  value="">-- Select Relationship --</option>
                                                <c:forEach items="${relationShips}" var="relationShipValue" varStatus="relationShipindex">
                                                    <c:choose>
                                                        <c:when test="${familyMemberDetail.relationShipDb==relationShipindex.index}">
                                                            <option value="${relationShipindex.index}" selected="selected" >${relationShipValue}</option>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <option value="${relationShipindex.index}">${relationShipValue}</option>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none;" id="relationShip_error_${fmindex.count}">*required</span>
                                        </td>
                                        <td valign="top" align="left">
                                            <input style="width:75%" type="text" name="dobRelation" id="dobRelation_${fmindex.count}" class="dobRelation" value="${familyMemberDetail.dobRelationDb}" readonly />
                                            <span style="color:red;display:none;" id="dobRelation_error_${fmindex.count}">*required</span>
                                        </td>
                                        <td valign="top" align="left">
                                            <input style="width:75%" type="text" name="occupationOfRel" id="occupationOfRel_${fmindex.count}"  value="${familyMemberDetail.occupationOfRelDb}" />
                                            <span style="color:red;display:none;" id="occupationOfRel_error_${fmindex.count}">*required</span>
                                            <div id = "occupationOfRel_err_${fmindex.count}" style="color:red;display:none;">Only alphanumeric are allowed</div>
                                        </td>
                                        <td valign="top" align="left">
                                            <input type="checkbox" name="medicalInsuranceCheck" id="medicalInsuranceCheck_${fmindex.count}" <c:if test="${familyMemberDetail.medicalInsuranceDb==1}">checked</c:if> onclick="checkValue(this)"/>
                                            <input type="hidden" name="medicalInsurance" class="lifeBox" id="medicalInsurance_${fmindex.count}"  value="${familyMemberDetail.medicalInsuranceDb}" />
                                            <span style="color:red;display:none;" id="medicalInsurance_error_${fmindex.count}">*required</span>
                                        </td>
                                        <td valign="top" align="left">
                                            <input type="checkbox" name="lifeInsuranceCheck" id="nomineeCheck_${fmindex.count}" <c:if test="${familyMemberDetail.lifeInsuranceDb==1}">checked</c:if> onclick="checkNomineeValue(this)"/>
                                            <input type="hidden" name="lifeInsurance" class="lifeBox" id="nominee_${fmindex.count}"  value="${familyMemberDetail.lifeInsuranceDb}" />
                                            <span style="color:red;display:none;" id="nomineeCheck_error_${fmindex.count}">*required</span>
                                        </td>
                                        <td valign="top" align="left">
                                            <c:if test="${fmindex.count != 1}">
                                                <img alt="remove" src="${pageContext.request.contextPath}/css/images/delete-icon.png" onclick="deleteRow('DEP_TR',${fmindex.count}, 1)" />
                                            </c:if>
                                        </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty(familyMemberDetails)}">
                            <tr id="DEP_TR_1">
                            <input type="hidden" name="jfFamId" id="jfFamId_1" value="" />
                            <input type="hidden" name="DEP_TR_deleted" id="DEP_TR_deleted_1" value="0" />
                            <td valign="top" align="left">
                                <input type="text" style="width:75%" name="nameOfRelation" id="nameOfRelation_1" />
                                <span style="color:red;display:none;" id="nameOfRelation_error_1">*required</span>
                                <div id = "nameOfRelation_err_1" style="color:red;display:none;">Only alphanumeric are allowed</div>
                            </td>
                            <td valign="top" align="left">
                                <select style="width:75%" name="relationShip" id="relationShip_1" >
                                    <option  value="">-- Select Relationship --</option>
                                    <c:forEach items="${relationShips}" var="relationShipValue" varStatus="relationShipindex">
                                        <option value="${relationShipindex.index}">${relationShipValue}</option>
                                    </c:forEach>
                                </select>
                                <span style="color:red;display:none;" id="relationShip_error_1">*required</span>
                            </td>
                            <td valign="top" align="left">
                                <input style="width:75%" type="text" name="dobRelation" id="dobRelation_1" readonly class="dobRelation" />
                                <span style="color:red;display:none;" id="dobRelation_error_1">*required</span>
                            </td>
                            <td valign="top" align="left">
                                <input style="width:75%" type="text" name="occupationOfRel" id="occupationOfRel_1"  />
                                <span style="color:red;display:none;" id="occupationOfRel_error_1">*required</span>
                                <div id = "occupationOfRel_err_1" style="color:red;display:none;">Only alphanumeric are allowed</div>
                            </td>
                            <td valign="top" align="left">
                                <input type="checkbox" name="medicalInsuranceCheck" id="medicalInsuranceCheck_1" onclick="checkValue(this)" />
                                <input type="hidden" name="medicalInsurance" class="lifeBox" id="medicalInsurance_1" value="0" />
                                <span style="color:red;display:none;" id="medicalInsurance_error_1">*required</span>
                            </td>
                            <td valign="top" align="left">
                                <input type="checkbox" name="lifeInsuranceCheck" id="nomineeCheck_1" onclick="checkNomineeValue(this)" />
                                <input type="hidden" name="lifeInsurance" class="lifeBox" id="nominee_1" value="0" />
                                <span style="color:red;display:none;" id="nomineeCheck_error_1">*required</span>
                            </td>
                            </tr>
                        </c:if>
                    </table>
                    <table cellspacing="5">
                        <tr>
                            <td><input type="button" class="addmore" onclick="addRow_Dependent();" value="Add"  /></td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    </table>
                </div>
                <div id="commonforms">
                    <div class="commonformheader">Passport Details</div>
                    <table>
                        <tr>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${!empty(passportDetails)}">
                                        <input type="hidden" name="passport_recordCount" id="passport_recordCount" value="${fn:length(passportDetails)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="passport_recordCount" id="passport_recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table  style="width:100%;" border="0" cellspacing="5">
                                    <tr class="headerCenter">
                                        <td width="20%" valign="top">Passport Number</td>
                                        <td width="15%" valign="top">Date of Issue</td>
                                        <td width="15%" valign="top">Date of Expiry</td>
                                        <td width="20%" valign="top">Place of Issue</td>
                                        <td width="23%" valign="top">Attachment</td>
                                        <td width="7%">&nbsp;</td>
                                    </tr>
                                    <c:forEach items="${passportDetails}" var="passportDetail" varStatus="passportStatus">
                                        <tr id="PASS_TR_${passportStatus.count}">
                                        <input type="hidden" name="passportId" id="passportId_${passportStatus.count}" value="${passportDetail.passportIdX}" />
                                        <input type="hidden" name="PASS_TR_deleted" id="PASS_TR_deleted_${passportStatus.count}" value="0" />
                                        <td valign="top" align="left">
                                            <input type="text" style="width:80%" name="passportNumber" id="passportNumber_${passportStatus.count}" maxlength="20" value="${passportDetail.passportNumberX}" />
                                            <span style="color:red;display:none;" id="passportNumber_error_${passportStatus.count}">*required</span>
                                            <div style="display:none;color: red;" id="passport_number_err_${passportStatus.count}">Only AlphaNumerics are allowed</div>
                                        </td>
                                        <td valign="top" align="left">
                                            <input type="text" style="width:80%" name="passDateOfIssue" class="dateOfIssue" id="passDateOfIssue_${passportStatus.count}" readonly value="${passportDetail.passDateOfIssueX}" />
                                            <span style="color:red;display:none;" id="passDateOfIssue_error_${passportStatus.count}">*required</span>
                                        </td>
                                        <td valign="top" align="left">
                                            <input type="text" style="width:80%" name="passDateOfExpiry" class="dateOfExpiry" id="passDateOfExpiry_${passportStatus.count}" readonly value="${passportDetail.passDateOfExpiryX}" />
                                            <span style="color:red;display:none;" id="passDateOfExpiry_error_${passportStatus.count}">*required</span>
                                        </td>
                                        <td valign="top" align="left">
                                            <input type="text" style="width:80%" name="passPlaceOfIssue" id="passPlaceOfIssue_${passportStatus.count}" value="${passportDetail.passPlaceOfIssueX}" />
                                            <span style="color:red;display:none;" id="passPlaceOfIssue_error_${passportStatus.count}">*required</span>
                                            <div style="display:none;color: red;" id="placeOfIssue_err_${passportStatus.count}">Only Alphabets are allowed</div>
                                        </td>
                                        <td valign="top" align="left">
                                            <input type="file" size="20" name="passAttachment_${passportStatus.count}" id="passAttachment_${passportStatus.count}" ><br>
                                            <c:choose>
                                                <c:when test="${passportDetail.passAttachmentX != '' && passportDetail.passAttachmentX != 'NULL'}">
                                                    <input type="hidden" name="passAttachmentXY" id="passAttachmentXY_${passportStatus.count}" value="${passportDetail.passAttachmentX}" />
                                                    <c:set var="DOCS" value="${fn:split(passportDetail.passAttachmentX, '~~')}" />
                                                    <a href="fileDownload.htm?fileName=${passportDetail.passAttachmentX}"><c:out value="${DOCS[2]}"></c:out></a>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="hidden" name="passAttachmentXY" id="passAttachmentXY_${passportStatus.count}" value="" />
                                                </c:otherwise>
                                            </c:choose>
                                            <span style="color:red;display:none;" id="passAttachment_error_${passportStatus.count}">*required</span>
                                            <div style="color:red;display:none;" id = "passAttachment_err_${passportStatus.count}">.zip, .jar, .war & .rar files are not accepted</div>
                                        </td>
                                        <td valign="top" align="left">
                                            <c:if test="${passportStatus.count > 1 }">
                                                <img alt="Remove" src="${pageContext.request.contextPath}/css/images/delete-icon.png" onclick="deleteRow('PASS_TR',${passportStatus.count}, 1)" />
                                            </c:if>
                                        </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty(passportDetails)}">
                            <tr id="PASS_TR_1">
                            <input type="hidden" name="passportId" id="passportId_1" value="" />
                            <input type="hidden" name="PASS_TR_deleted" id="PASS_TR_deleted_1" value="0" />
                            <td valign="top" align="left">
                                <input type="text" style="width:80%" name="passportNumber" id="passportNumber_1" maxlength="20" />
                                <span style="color:red;display:none;" id="passportNumber_error_1">*required</span>
                                <div style="display:none;color: red;" id="passport_number_err_1">Only AlphaNumerics are allowed</div>
                            </td>
                            <td valign="top" align="left">
                                <input type="text" style="width:80%" name="passDateOfIssue" class="dateOfIssue" id="passDateOfIssue_1" readonly value="" />
                                <span style="color:red;display:none;" id="passDateOfIssue_error_1">*required</span>
                            </td>
                            <td valign="top" align="left">
                                <input type="text" style="width:80%" name="passDateOfExpiry" class="dateOfExpiry" id="passDateOfExpiry_1" readonly value="" />
                                <span style="color:red;display:none;" id="passDateOfExpiry_error_1">*required</span>
                            </td>
                            <td valign="top" align="left">
                                <input type="text" style="width:80%" name="passPlaceOfIssue" id="passPlaceOfIssue_1" value="" />
                                <span style="color:red;display:none;" id="passPlaceOfIssue_error_1">*required</span>
                                <div style="display:none;color: red;" id="placeOfIssue_err_1">Only Alphabets are allowed</div>
                            </td>
                            <td valign="top" align="left">
                                <input type="hidden" name="passAttachmentXY" value="" id="passAttachmentXY_1" />
                                <input type="file" size="20" name="passAttachment_1" id="passAttachment_1" >
                                <span style="color:red;display:none;" id="passAttachment_error_1">*required</span>
                                <div style="color:red;display:none;" id = "passAttachment_err_1">.zip, .jar, .war & .rar files are not accepted</div>
                            </td>
                            </tr>
                        </c:if>
                    </table>
                    <table cellspacing="5">
                        <tr>
                            <td><input type="button" class="addmore" onclick="addRow_Passport();" value="Add"  /></td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    </table>
                </div>
                <div id="commonforms">
                    <div class="commonformheader">Visa Details (Provide only Valid Visa)</div>
                    <table>
                        <tr>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${!empty(visaDetails)}">
                                        <input type="hidden" name="visa_recordCount" id="visa_recordCount" value="${fn:length(visaDetails)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="visa_recordCount" id="visa_recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table width="100%" border="0" cellspacing="5">
                                    <tr class="headerCenter">
                                        <td width="15%" valign="top"><font color="red"></font>Reference Number</td>
                                        <td width="14%" valign="top"><font color="red"></font>Country</td>
                                        <td width="14%" valign="top"><font color="red"></font>Type</td>
                                        <td width="12%" valign="top"><font color="red"></font>Date of Issue</td>
                                        <td width="12%" valign="top"><font color="red"></font>Date of Expiry</td>
                                        <td width="15%" valign="top"><font color="red"></font>Place of Issue</td>
                                        <td width="14%" valign="top"><font color="red"></font>Visit / Entries</td>
                                        <th width="4%">&nbsp;</th>
                                    </tr>
                                    <c:forEach items="${visaDetails}" var="visaDetail" varStatus="visaIndex">
                                        <tr id="VISA_TR_${visaIndex.count}">
                                        <input type="hidden" name="jfVisaId" id="jfVisaId_${visaIndex.count}" value="${visaDetail.jfVisaIdDb}" />
                                        <input type="hidden" name="VISA_TR_deleted" id="VISA_TR_deleted_${visaIndex.count}" value="0" />
                                        <td valign="top">
                                            <input type="text" name="referenceNumber" id="referenceNumber_${visaIndex.count}" style="width:95%" value="${visaDetail.referenceNumberDb}" />
                                            <span style="color:red;display:none;" id="referenceNumber_error_${visaIndex.count}">*required</span>
                                            <div id="referenceNumber_err_${visaIndex.count}" style="color:red;display: none;">Only AlphaNumberics are allowed</div>
                                        </td>
                                        <td valign="top">
                                            <select name="visaCountry" id="visaCountry_${visaIndex.count}" style="width:95%">
                                                <option value="" >--Select Country--</option>
                                                <c:forEach items="${countryList}" var="item">
                                                    <c:set var="selval" value="" ></c:set>
                                                    <c:if test="${item.countryId == visaDetail.visaCountryDb}">
                                                        <c:set var="selval" value="selected=selected" ></c:set>
                                                    </c:if>
                                                    <option ${selval} value="${item.countryId}" >${item.countryName}</option>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none;" id="visaCountry_error_${visaIndex.count}">*required</span>
                                        </td>
                                        <td valign="top">
                                            <select name="visaType" id="visaType_${visaIndex.count}" style="width:95%">
                                                <option value="" >--Select Visa Type--</option>
                                                <c:forEach items="${visaList}" var="item">
                                                    <c:set var="selval" value="" ></c:set>
                                                    <c:if test="${item.visaId == visaDetail.visaTypeDb}">
                                                        <c:set var="selval" value="selected=selected" ></c:set>
                                                    </c:if>
                                                    <option ${selval} value="${item.visaId}" >${item.visaName}</option>
                                                </c:forEach>
                                            </select>
                                            <span style="color:red;display:none;" id="visaType_error_${visaIndex.count}">*required</span>
                                        </td>
                                        <td valign="top">
                                            <input type="text" name="visaDateOfIssue" id="visaDateOfIssue_${visaIndex.count}" value="${visaDetail.visaDateOfIssueDb}" readonly class="dateOfIssue" style="width:95%" />
                                            <span style="color:red;display:none;" id="visaDateOfIssue_error_${visaIndex.count}">*required</span>
                                        </td>
                                        <td valign="top">
                                            <input type="text" name="visaDateOfExpiry" id="visaDateOfExpiry_${visaIndex.count}" value="${visaDetail.visaDateOfExpiryDb}" readonly class="dateOfExpiry" style="width:95%" />
                                            <span style="color:red;display:none;" id="visaDateOfExpiry_error_${visaIndex.count}">*required</span>
                                        </td>
                                        <td valign="top">
                                            <input type="text" name="placeOfIssue" id="placeOfIssue_${visaIndex.count}" style="width:95%" value="${visaDetail.placeOfIssueDb} " />
                                            <span style="color:red;display:none;" id="placeOfIssue_error_${visaIndex.count}">*required</span>
                                            <div id="place_issue_err_${visaIndex.count}" style="color:red;display: none;">Only Alphabets are allowed</div>
                                        </td>
                                        <td valign="top">
                                            <select name="entries" id="entries_${visaIndex.count}" style="width:95%">
                                                <option value="" >--Select Entries--</option>
                                                <option ${(visaDetail.entriesDb == '0')?'selected':''} value="0">Single</option>
                                                <option ${(visaDetail.entriesDb == '1')?'selected':''} value="1">Multiple</option>
                                            </select>
                                            <span style="color:red;display:none;" id="entries_error_${visaIndex.count}">*required</span>
                                        </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty(visaDetails)}">
                            <tr id="VISA_TR_1">
                            <input type="hidden" name="jfVisaId" id="jfVisaId_1" value="" />
                            <input type="hidden" name="VISA_TR_deleted" id="VISA_TR_deleted_1" value="0" />
                            <td valign="top">
                                <input type="text" name="referenceNumber" id="referenceNumber_1" style="width:95%" value="" />
                                <span style="color:red;display:none;" id="referenceNumber_error_1">*required</span>
                                <div id="referenceNumber_err_1" style="color:red;display: none;">Only AlphaNumberics are allowed</div>
                            </td>
                            <td valign="top">
                                <select name="visaCountry" id="visaCountry_1" style="width:95%">
                                    <option value="" >--Select Country--</option>
                                    <c:forEach items="${countryList}" var="item">
                                        <option value="${item.countryId}" >${item.countryName}</option>
                                    </c:forEach>
                                </select>
                                <span style="color:red;display:none;" id="visaCountry_error_1">*required</span>
                            </td>
                            <td valign="top">
                                <select name="visaType" id="visaType_1" style="width:95%">
                                    <option value="" >--Select Visa Type--</option>
                                    <c:forEach items="${visaList}" var="item">
                                        <option value="${item.visaId}" >${item.visaName}</option>
                                    </c:forEach>
                                </select>
                                <span style="color:red;display:none;" id="visaType_error_1">*required</span>
                            </td>
                            <td valign="top">
                                <input type="text" name="visaDateOfIssue" id="visaDateOfIssue_1" readonly class="dateOfIssue" style="width:95%" />
                                <span style="color:red;display:none;" id="visaDateOfIssue_error_1">*required</span>
                            </td>
                            <td valign="top">
                                <input type="text" name="visaDateOfExpiry" id="visaDateOfExpiry_1" readonly class="dateOfExpiry" style="width:95%" />
                                <span style="color:red;display:none;" id="visaDateOfExpiry_error_1">*required</span>
                            </td>
                            <td valign="top">
                                <input type="text" name="placeOfIssue" id="placeOfIssue_1" style="width:95%" />
                                <span style="color:red;display:none;" id="placeOfIssue_error_1">*required</span>
                                <div id="place_issue_err_1" style="color:red;display: none;">Only Alphabets are allowed</div>
                            </td>
                            <td valign="top">
                                <select name="entries" id="entries_1" style="width:95%">
                                    <option value="" >--Select Entries--</option>
                                    <option value="0">Single</option>
                                    <option value="1">Multiple</option>
                                </select>
                                <span style="color:red;display:none;" id="entries_error_1">*required</span>
                            </td>
                            </tr>
                        </c:if>
                    </table>
                    <table cellspacing="5">
                        <tr>
                            <td><input type="button" class="addmore" onclick="addRow_Visa();" value="Add"  /></td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    </table>
                </div>
                <div id="commonforms">
                    <div class="commonformheader">Driving License</div>
                    <table>
                        <tr>
                            <td colspan="3">
                                <table  style="width:100%;" cellspacing="5">
                                    <tr class="headerCenter">
                                        <td width="20%" valign="top"><font color="red"></font>License Number</td>
                                        <td width="15%" valign="top"><font color="red"></font>Date of Issue</td>
                                        <td width="15%" valign="top"><font color="red"></font>Date of Expiry</td>
                                        <td width="20%" valign="top"><font color="red"></font>Place of Issue</td>
                                        <td width="30%" valign="top"><font color="red"></font>Attachment</td>
                                    </tr>
                                    <tr>
                                        <td valign="top" align="left">
                                            <input style="width:80%" type="text" name="dlNumber" id="dlNumber" maxlength="20"  value="${joinerData.dlNumber}"/>
                                            <span id="dlNumber_error" style="color:red;display: none;">*required</span>
                                            <div id="dlNumber_err" style="color:red;display: none;">Only AlphaNumberics are allowed</div>
                                        </td>
                                        <td valign="top" align="left">
                                            <input style="width:80%" type="text" name="dlDateOfIssue" id="dlDateOfIssue" readonly value="${joinerData.dlDateOfIssue}"/>
                                            <span id="dlDateOfIssue_error" style="color:red;display: none;">*required</span>
                                        </td>
                                        <td valign="top" align="left">
                                            <input style="width:80%" type="text" name="dlDateOfExpiry" id="dlDateOfExpiry"  readonly  value="${joinerData.dlDateOfExpiry}"/>
                                            <span id="dlDateOfExpiry_error" style="color:red;display: none;">*required</span>
                                        </td>
                                        <td valign="top" align="left">
                                            <input style="width:80%" type="text" name="dlPlaceOfIssue" id="dlPlaceOfIssue"  class="" value="${joinerData.dlPlaceOfIssue}"/>
                                            <span id="dlPlaceOfIssue_error" style="color:red;display: none;">*required</span>
                                            <div id="dlPlaceOfIssue_err" style="color:red;display: none;">Only AlphaNumberics are allowed</div>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${joinerData.dlFileName!=null}">
                                                    <input type="file" name="dlProof" id="dlProof" value=""/><br>
                                                    <input type="hidden" name="dlProofID" id="dlProofID" value="${joinerData.dlFileId}" />
                                                    <br><a href="fileDownload.htm?fileName=${joinerData.dlFileName}&fileType=${joinerData.dlFileType}">${fn:split(joinerData.dlFileName,'~~')[2]}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <input type="file" name="dlProof" id="dlProof" value=""/>
                                                </c:otherwise>
                                            </c:choose>
                                            <span id="dlProof_error" style="color:red;display: none;">*required</span>
                                            <div id = "dlProof_err" style="color:red;display: none;">.zip, .jar, .war & .rar files are not accepted</div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
                <div id="commonforms">
                    <div class="commonformheader">Emergency Contacts</div>
                    <table>
                        <tr>
                            <td colspan="3">
                                <c:choose>
                                    <c:when test="${!empty(emergencyDetails)}">
                                        <input type="hidden" name="visa_recordCount" id="emergency_recordCount" value="${fn:length(emergencyDetails)}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input type="hidden" name="visa_recordCount" id="emergency_recordCount" value="1" />
                                    </c:otherwise>
                                </c:choose>
                                <table width="100%" border="0" cellspacing="5">
                                    <tr class="headerCenter">
                                        <th width="20%">Name<font color="red" size="3">*</font></th>
                                        <th width="19%">Relationship<font color="red" size="3">*</font></th>
                                        <th width="19%">Home Phone Number</th>
                                        <th width="19%">Mobile Number<font color="red" size="3">*</font></th>
                                        <th width="19%">Work Phone Number</th>
                                    </tr>
                                    <c:forEach items="${emergencyDetails}" var="data" varStatus="dataStatus">
                                        <tr id="EMERGENCY_TR_${dataStatus.count}">
                                        <input type="hidden" name="emergencyId" id="emergencyId_${dataStatus.count}" value="${data.emergencyIdX}" />
                                        <input type="hidden" name="EMERGENCY_TR_deleted" id="EMERGENCY_TR_deleted_${dataStatus.count}" value="0" />
                                        <td valign ="top" align="left">
                                            <input type="text" style="width:80%" maxlength="25" id="name_${dataStatus.count}" name="name" value="${data.nameX}" style="width:95%" maxlength="100" />
                                            <span style="color:red;display:none;" id="name_error_${dataStatus.count}">*required</span>
                                            <div id="name_err_${dataStatus.count}" style="color:red;display: none;">Only Alphabets are allowed</div>
                                        </td>
                                        <td valign ="top" align="left">
                                            <input type="text" style="width:80%" maxlength="25" name="relationship" id="relationship_${dataStatus.count}" style="width:95%" value="${data.relationshipX}" maxlength="100" />
                                            <span style="color:red;display:none;" id="relationship_error_${dataStatus.count}">*required</span>
                                            <div id="relationship_err_${dataStatus.count}" style="color:red;display: none;">Only Alphabets are allowed</div>
                                        </td>
                                        <td valign ="top" align="left">
                                            <input type="text" style="width:80%" maxlength="15" id="home_phone_number_${dataStatus.count}" name="home_phone_number" value="${data.home_phone_numberX}" style="width:95%" maxlength="15" />
                                            <span style="color:red;display:none;" id="home_phone_number_error_${dataStatus.count}">*required</span>
                                            <div id="home_phone_number_err_${dataStatus.count}" style="color:red;display: none;">Only Numerics are allowed</div>
                                        </td>
                                        <td valign ="top" align="left">
                                            <input type="text" style="width:80%" maxlength="15" id="mobile_number_${dataStatus.count}" name="mobile_number" value="${data.mobile_numberX}" style="width:95%" maxlength="10" />
                                            <span style="color:red;display:none;" id="mobile_number_error_${dataStatus.count}">*required</span>
                                            <div id="mobile_number_err_${dataStatus.count}" style="color:red;display: none;">Only Numerics are allowed</div>
                                        </td>
                                        <td valign ="top" align="left">
                                            <input type="text" style="width:80%" maxlength="15" id="work_phone_number_${dataStatus.count}" name="work_phone_number" value="${data.work_phone_numberX}" style="width:95%" maxlength="10" />
                                            <span style="color:red;display:none;" id="work_phone_number_error_${dataStatus.count}">*required</span>
                                            <div id="work_phone_number_err_${dataStatus.count}" style="color:red;display: none;">Only Numerics are allowed</div>
                                        </td>
                            </tr>
                        </c:forEach>
                        <c:if test="${empty(emergencyDetails)}">
                            <tr id="EMERGENCY_TR_1">
                            <input type="hidden" name="emergencyId" id="emergencyId_1" value="" />
                            <input type="hidden" name="EMERGENCY_TR_deleted" id="EMERGENCY_TR_deleted_1" value="0" />
                            <td valign ="top" align="left">
                                <input type="text" style="width:80%" maxlength="25" id="name_1" name="name" value="" style="width:95%" maxlength="100" />
                                <span style="color:red;display:none;" id="name_error_1">*required</span>
                                <div id="name_err_1" style="color:red;display: none;">Only Alphabets are allowed</div>
                            </td>
                            <td valign ="top" align="left">
                                <input type="text" style="width:80%" maxlength="25" name="relationship" id="relationship_1" style="width:95%" maxlength="100" />
                                <span style="color:red;display:none;" id="relationship_error_1">*required</span>
                                <div id="relationship_err_1" style="color:red;display: none;">Only Alphabets are allowed</div>
                            </td>
                            <td valign ="top" align="left">
                                <input type="text" style="width:80%" maxlength="15" id="home_phone_number_1" name="home_phone_number" value="" style="width:95%" maxlength="15" />
                                <span style="color:red;display:none;" id="home_phone_number_error_1">*required</span>
                                <div id="home_phone_number_err_1" style="color:red;display: none;">Only Numerics are allowed</div>
                            </td>
                            <td valign ="top" align="left">
                                <input type="text" style="width:80%" maxlength="15" id="mobile_number_1" name="mobile_number" value="" style="width:95%" maxlength="10" />
                                <span style="color:red;display:none;" id="mobile_number_error_1">*required</span>
                                <div id="mobile_number_err_1" style="color:red;display: none;">Only Numerics are allowed</div>
                            </td>
                            <td valign ="top" align="left">
                                <input type="text" style="width:80%" maxlength="15" id="work_phone_number_1" name="work_phone_number" value="" style="width:95%" maxlength="10" />
                                <span style="color:red;display:none;" id="work_phone_number_error_1">*required</span>
                                <div id="work_phone_number_err_1" style="color:red;display: none;">Only Numerics are allowed</div>
                            </td>
                            </tr>
                        </c:if>
                    </table>
                    <table cellspacing="5">
                        <tr>
                            <td><input type="button" class="addmore" onclick="addRow_Emergency();" value="Add"  /></td>
                        </tr>
                    </table>
                    </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <c:if test="${printStatus!='yes'}">
                                <input type="button" class="backbutton"  name="backButton" value="Back" onclick="redirectBack()">
                                <input type="submit" id="continuebutton2" class="continuebutton"  value="Save &amp; Continue" >
                            </c:if>
                        </td>
                    </tr>
                    </table>
                </div>
            </form>
            <form action="${pageContext.request.contextPath}/joinerFormAddOrUpdateStepTwo.htm" method="POST" name="backForm" id="backForm">
                <input type="hidden" name="jfId" value ="${employeeData.jfId}" id="jfId"/>
                <input type="hidden" name="backButton" value="Back">
            </form>
    </div>
</body>
